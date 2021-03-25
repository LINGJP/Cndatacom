package com.cndatacom.cnportalclient.collection;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import com.cndatacom.cnportalclient.http.CdcProtocol;
import com.cndatacom.cnportalclient.model.CollectionFileInfo;
import com.cndatacom.cnportalclient.model.CollectionInfo;
import com.cndatacom.cnportalclient.model.InnerState;
import com.cndatacom.cnportalclient.utils.DateUtils;
import com.cndatacom.cnportalclient.utils.ExLog;
import com.cndatacom.cnportalclient.utils.PreferencesUtils;
import java.util.Date;
import java.util.Vector;

public class CollectionHelper {
  private static CollectionHelper e;
  
  private HandlerThread a = new HandlerThread("handleCollectionData", 0);
  
  private final int b = 0;
  
  private final int c = 60000;
  
  private final int d = 300000;
  
  private CallBack f;
  
  public Handler mWorkerHandler;
  
  private CollectionHelper() {
    this.a.start();
    this.mWorkerHandler = new Handler(this.a.getLooper(), new Handler.Callback(this) {
          private void a(Message param1Message) {
            if (param1Message.what != 0)
              return; 
            if (CollectionHelper.a(this.a) != null)
              CollectionHelper.a(this.a).collection(); 
            this.a.mWorkerHandler.sendEmptyMessageDelayed(0, 300000L);
          }
          
          public boolean handleMessage(Message param1Message) {
            a(param1Message);
            return true;
          }
        });
  }
  
  public static void addCollectionInfo(String paramString, CollectionInfo paramCollectionInfo) {
    CollectionFileInfo.load(paramString).addCollectionInfo(paramCollectionInfo).save(paramString);
  }
  
  public static void cutCollectionInfo(String paramString1, String paramString2) {
    CollectionFileInfo collectionFileInfo1 = CollectionFileInfo.load(paramString1);
    CollectionFileInfo collectionFileInfo2 = CollectionFileInfo.load(paramString2);
    Vector vector = collectionFileInfo1.getCollectionInfos();
    if (vector != null && vector.size() > 0) {
      collectionFileInfo1.setCollectionInfos(new Vector()).save(paramString1);
      collectionFileInfo2.appendCollectionInfos(vector);
      collectionFileInfo2.save(paramString2);
    } 
  }
  
  public static CollectionHelper getInstance() {
    if (e == null)
      e = new CollectionHelper(); 
    return e;
  }
  
  public static void updateCollectionInfo(String paramString1, String paramString2, String paramString3) {
    CollectionFileInfo collectionFileInfo = CollectionFileInfo.load(paramString1);
    Vector<CollectionInfo> vector = collectionFileInfo.getCollectionInfos();
    if (vector != null && vector.size() > 0)
      for (int i = 0; i < vector.size(); i++) {
        CollectionInfo collectionInfo = vector.get(i);
        if (collectionInfo.getId().equals(paramString2)) {
          collectionInfo.setExitcode(paramString3);
          collectionFileInfo.save(paramString1);
          return;
        } 
      }  
  }
  
  public static void updateCollectionInfo(String paramString1, String paramString2, String paramString3, InnerState paramInnerState) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("updateCollectionInfo filePath ->");
    stringBuilder.append(paramString1);
    String str = stringBuilder.toString();
    int i = 0;
    ExLog.d(new String[] { str });
    CollectionFileInfo collectionFileInfo = CollectionFileInfo.load(paramString1);
    Vector<CollectionInfo> vector = collectionFileInfo.getCollectionInfos();
    if (vector != null && vector.size() > 0)
      while (i < vector.size()) {
        CollectionInfo collectionInfo = vector.get(i);
        if (collectionInfo.getId().equals(paramString2)) {
          collectionInfo.setStoptime(DateUtils.parse2YYYYMMDDHHMMSS(new Date()));
          if (TextUtils.isEmpty(collectionInfo.getAccount()) && !TextUtils.isEmpty(paramString3))
            collectionInfo.setAccount(paramString3); 
          if (TextUtils.isEmpty(collectionInfo.getCid()) && !TextUtils.isEmpty(paramInnerState.getSchoolId("")))
            collectionInfo.setCid(paramInnerState.getSchoolId("")); 
          collectionFileInfo.save(paramString1);
          return;
        } 
        i++;
      }  
  }
  
  public static void uploadCollectionInfo(Context paramContext, String paramString1, long paramLong, String paramString2) {
    String str = PreferencesUtils.getString(paramContext, "COLLECTION_LAST_UPLOAD_TIME", "");
    boolean bool1 = TextUtils.isEmpty(str);
    boolean bool = true;
    if (!bool1 && DateUtils.parse2Date(str).getTime() + paramLong * 1000L >= System.currentTimeMillis())
      bool = false; 
    if (bool) {
      CollectionFileInfo collectionFileInfo = CollectionFileInfo.load(paramString2);
      CdcProtocol.uploadCollectionInfo(paramString1, collectionFileInfo, new CollectionFileInfo.CallBack(collectionFileInfo, paramString2, paramContext, "COLLECTION_LAST_UPLOAD_TIME") {
            public void uploadSuccess() {
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append("uploadCollectionInfo uploadSuccess ->");
              stringBuilder.append(DateUtils.parse2YYYYMMDDHHMMSS(new Date()));
              ExLog.d(new String[] { stringBuilder.toString() });
              this.a.setCollectionInfos(new Vector()).save(this.b);
              PreferencesUtils.putString(this.c, this.d, DateUtils.parse2YYYYMMDDHHMMSS(new Date()));
            }
          });
    } 
  }
  
  public void release() {
    if (this.mWorkerHandler != null)
      this.mWorkerHandler.removeCallbacksAndMessages(null); 
    if (this.a != null)
      this.a.quit(); 
    e = null;
  }
  
  public void setCallBack(CallBack paramCallBack) {
    this.f = paramCallBack;
  }
  
  public void start() {
    this.mWorkerHandler.sendEmptyMessageDelayed(0, 60000L);
  }
  
  public static interface CallBack {
    void collection();
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\collection\CollectionHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */