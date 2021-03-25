package android.support.v4.content;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public final class LocalBroadcastManager {
  private static final boolean DEBUG = false;
  
  static final int MSG_EXEC_PENDING_BROADCASTS = 1;
  
  private static final String TAG = "LocalBroadcastManager";
  
  private static LocalBroadcastManager mInstance;
  
  private static final Object mLock = new Object();
  
  private final HashMap<String, ArrayList<ReceiverRecord>> mActions = new HashMap<String, ArrayList<ReceiverRecord>>();
  
  private final Context mAppContext;
  
  private final Handler mHandler;
  
  private final ArrayList<BroadcastRecord> mPendingBroadcasts = new ArrayList<BroadcastRecord>();
  
  private final HashMap<BroadcastReceiver, ArrayList<ReceiverRecord>> mReceivers = new HashMap<BroadcastReceiver, ArrayList<ReceiverRecord>>();
  
  private LocalBroadcastManager(Context paramContext) {
    this.mAppContext = paramContext;
    this.mHandler = new Handler(paramContext.getMainLooper()) {
        public void handleMessage(Message param1Message) {
          if (param1Message.what != 1) {
            super.handleMessage(param1Message);
            return;
          } 
          LocalBroadcastManager.this.executePendingBroadcasts();
        }
      };
  }
  
  @NonNull
  public static LocalBroadcastManager getInstance(@NonNull Context paramContext) {
    synchronized (mLock) {
      if (mInstance == null)
        mInstance = new LocalBroadcastManager(paramContext.getApplicationContext()); 
      return mInstance;
    } 
  }
  
  void executePendingBroadcasts() {
    while (true) {
      HashMap<BroadcastReceiver, ArrayList<ReceiverRecord>> hashMap;
      BroadcastRecord broadcastRecord;
      synchronized (this.mReceivers) {
        int i = this.mPendingBroadcasts.size();
        if (i <= 0)
          return; 
        BroadcastRecord[] arrayOfBroadcastRecord = new BroadcastRecord[i];
        this.mPendingBroadcasts.toArray(arrayOfBroadcastRecord);
        this.mPendingBroadcasts.clear();
        for (i = 0; i < arrayOfBroadcastRecord.length; i++) {
          broadcastRecord = arrayOfBroadcastRecord[i];
          int k = broadcastRecord.receivers.size();
          for (int j = 0; j < k; j++) {
            ReceiverRecord receiverRecord = broadcastRecord.receivers.get(j);
            if (!receiverRecord.dead)
              receiverRecord.receiver.onReceive(this.mAppContext, broadcastRecord.intent); 
          } 
        } 
      } 
    } 
  }
  
  public void registerReceiver(@NonNull BroadcastReceiver paramBroadcastReceiver, @NonNull IntentFilter paramIntentFilter) {
    synchronized (this.mReceivers) {
      ReceiverRecord receiverRecord = new ReceiverRecord(paramIntentFilter, paramBroadcastReceiver);
      ArrayList<ReceiverRecord> arrayList2 = this.mReceivers.get(paramBroadcastReceiver);
      ArrayList<ReceiverRecord> arrayList1 = arrayList2;
      if (arrayList2 == null) {
        arrayList1 = new ArrayList(1);
        this.mReceivers.put(paramBroadcastReceiver, arrayList1);
      } 
      arrayList1.add(receiverRecord);
      for (int i = 0; i < paramIntentFilter.countActions(); i++) {
        String str = paramIntentFilter.getAction(i);
        arrayList1 = this.mActions.get(str);
        ArrayList<ReceiverRecord> arrayList = arrayList1;
        if (arrayList1 == null) {
          arrayList = new ArrayList<ReceiverRecord>(1);
          this.mActions.put(str, arrayList);
        } 
        arrayList.add(receiverRecord);
      } 
      return;
    } 
  }
  
  public boolean sendBroadcast(@NonNull Intent paramIntent) {
    int i;
    ArrayList<ReceiverRecord> arrayList;
    String str1;
    String str2;
    Uri uri;
    String str3;
    Set set;
    synchronized (this.mReceivers) {
      str1 = paramIntent.getAction();
      str2 = paramIntent.resolveTypeIfNeeded(this.mAppContext.getContentResolver());
      uri = paramIntent.getData();
      str3 = paramIntent.getScheme();
      set = paramIntent.getCategories();
      if ((paramIntent.getFlags() & 0x8) != 0) {
        i = 1;
      } else {
        i = 0;
      } 
      if (i) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Resolving type ");
        stringBuilder1.append(str2);
        stringBuilder1.append(" scheme ");
        stringBuilder1.append(str3);
        stringBuilder1.append(" of intent ");
        stringBuilder1.append(paramIntent);
        Log.v("LocalBroadcastManager", stringBuilder1.toString());
      } 
      arrayList = this.mActions.get(paramIntent.getAction());
      if (arrayList != null) {
        if (i) {
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append("Action list: ");
          stringBuilder1.append(arrayList);
          Log.v("LocalBroadcastManager", stringBuilder1.toString());
        } 
      } else {
        return false;
      } 
    } 
    StringBuilder stringBuilder = null;
    for (int j = 0; j < arrayList.size(); j++) {
      ReceiverRecord receiverRecord = arrayList.get(j);
      if (i) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Matching against filter ");
        stringBuilder1.append(receiverRecord.filter);
        Log.v("LocalBroadcastManager", stringBuilder1.toString());
      } 
      if (receiverRecord.broadcasting) {
        if (i)
          Log.v("LocalBroadcastManager", "  Filter's target already added"); 
      } else {
        IntentFilter intentFilter = receiverRecord.filter;
        StringBuilder stringBuilder1 = stringBuilder;
        int k = intentFilter.match(str1, str2, str3, uri, set, "LocalBroadcastManager");
        if (k >= 0) {
          if (i) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("  Filter matched!  match=0x");
            stringBuilder.append(Integer.toHexString(k));
            Log.v("LocalBroadcastManager", stringBuilder.toString());
          } 
          if (stringBuilder1 == null) {
            ArrayList arrayList1 = new ArrayList();
          } else {
            stringBuilder = stringBuilder1;
          } 
          stringBuilder.add(receiverRecord);
          receiverRecord.broadcasting = true;
        } else if (i) {
          String str;
          StringBuilder stringBuilder2;
          switch (k) {
            default:
              str = "unknown reason";
              stringBuilder2 = new StringBuilder();
              stringBuilder2.append("  Filter did not match: ");
              stringBuilder2.append(str);
              Log.v("LocalBroadcastManager", stringBuilder2.toString());
              break;
            case -1:
              str = "type";
              stringBuilder2 = new StringBuilder();
              stringBuilder2.append("  Filter did not match: ");
              stringBuilder2.append(str);
              Log.v("LocalBroadcastManager", stringBuilder2.toString());
              break;
            case -2:
              str = "data";
              stringBuilder2 = new StringBuilder();
              stringBuilder2.append("  Filter did not match: ");
              stringBuilder2.append(str);
              Log.v("LocalBroadcastManager", stringBuilder2.toString());
              break;
            case -3:
              str = "action";
              stringBuilder2 = new StringBuilder();
              stringBuilder2.append("  Filter did not match: ");
              stringBuilder2.append(str);
              Log.v("LocalBroadcastManager", stringBuilder2.toString());
              break;
            case -4:
              str = "category";
              stringBuilder2 = new StringBuilder();
              stringBuilder2.append("  Filter did not match: ");
              stringBuilder2.append(str);
              Log.v("LocalBroadcastManager", stringBuilder2.toString());
              break;
          } 
        } 
      } 
    } 
    if (stringBuilder != null) {
      for (i = 0; i < stringBuilder.size(); i++)
        ((ReceiverRecord)stringBuilder.get(i)).broadcasting = false; 
      this.mPendingBroadcasts.add(new BroadcastRecord(paramIntent, (ArrayList<ReceiverRecord>)stringBuilder));
      if (!this.mHandler.hasMessages(1))
        this.mHandler.sendEmptyMessage(1); 
      /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=SYNTHETIC_LOCAL_VARIABLE_11} */
      return true;
    } 
    /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=SYNTHETIC_LOCAL_VARIABLE_11} */
    return false;
  }
  
  public void sendBroadcastSync(@NonNull Intent paramIntent) {
    if (sendBroadcast(paramIntent))
      executePendingBroadcasts(); 
  }
  
  public void unregisterReceiver(@NonNull BroadcastReceiver paramBroadcastReceiver) {
    synchronized (this.mReceivers) {
      ArrayList<ReceiverRecord> arrayList = this.mReceivers.remove(paramBroadcastReceiver);
      if (arrayList == null)
        return; 
      for (int i = arrayList.size() - 1;; i--) {
        if (i >= 0) {
          ReceiverRecord receiverRecord = arrayList.get(i);
          receiverRecord.dead = true;
          for (int j = 0; j < receiverRecord.filter.countActions(); j++) {
            String str = receiverRecord.filter.getAction(j);
            ArrayList<ReceiverRecord> arrayList1 = this.mActions.get(str);
            if (arrayList1 != null) {
              int k;
              for (k = arrayList1.size() - 1;; k--) {
                if (k >= 0) {
                  ReceiverRecord receiverRecord1 = arrayList1.get(k);
                  if (receiverRecord1.receiver == paramBroadcastReceiver) {
                    receiverRecord1.dead = true;
                    arrayList1.remove(k);
                  } 
                } else {
                  if (arrayList1.size() <= 0)
                    this.mActions.remove(str); 
                  break;
                } 
              } 
            } 
          } 
        } else {
          return;
        } 
      } 
    } 
  }
  
  private static final class BroadcastRecord {
    final Intent intent;
    
    final ArrayList<LocalBroadcastManager.ReceiverRecord> receivers;
    
    BroadcastRecord(Intent param1Intent, ArrayList<LocalBroadcastManager.ReceiverRecord> param1ArrayList) {
      this.intent = param1Intent;
      this.receivers = param1ArrayList;
    }
  }
  
  private static final class ReceiverRecord {
    boolean broadcasting;
    
    boolean dead;
    
    final IntentFilter filter;
    
    final BroadcastReceiver receiver;
    
    ReceiverRecord(IntentFilter param1IntentFilter, BroadcastReceiver param1BroadcastReceiver) {
      this.filter = param1IntentFilter;
      this.receiver = param1BroadcastReceiver;
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder(128);
      stringBuilder.append("Receiver{");
      stringBuilder.append(this.receiver);
      stringBuilder.append(" filter=");
      stringBuilder.append(this.filter);
      if (this.dead)
        stringBuilder.append(" DEAD"); 
      stringBuilder.append("}");
      return stringBuilder.toString();
    }
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\android\support\v4\content\LocalBroadcastManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */