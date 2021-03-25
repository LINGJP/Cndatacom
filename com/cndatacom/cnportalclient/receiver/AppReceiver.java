package com.cndatacom.cnportalclient.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import com.cndatacom.cnportalclient.model.MessageResult;
import com.cndatacom.cnportalclient.model.ReturnUIResult;
import org.json.JSONObject;

public class AppReceiver {
  public static final String ACTION_BUNDLEKEY = "com.cndatacom.campus.receiver.action.bundle.key";
  
  public static final int ACTION_CODE_SERVICE = 1;
  
  public static final String ACTION_DATA_KEY = "com.cndatacom.campus.receiver.action.data.key";
  
  public static final String ACTION_QRCODE_AUTH_ERROR = "com.cndatacom.campus.netcore.qrcode.action.autherror";
  
  public static final String RECEIVER_ACTION = "com.cndatacom.campus.receiver.action";
  
  public static final String RECEIVER_PERMISSION = "com.cndatacom.campus.permissions.PORTAL_RECEIVER";
  
  private BroadcastReceiver a;
  
  private IntentFilter b = new IntentFilter();
  
  private AppReceiverListener c;
  
  public AppReceiver() {
    this.b.addAction("com.cndatacom.campus.receiver.action");
    this.b.addAction("com.cndatacom.campus.netcore.keepservice.CHANGED");
    this.b.addAction("com.cndatacom.campus.netcore.holdservice.action.notify");
    this.b.addAction("com.cndatacom.campus.netcore.holdservice.action.shared");
    this.b.addAction("com.cndatacom.campus.netcore.qrcode.action.autherror");
  }
  
  public static void sendReceiver(Context paramContext, int paramInt) {
    sendReceiver(paramContext, paramInt, null, null);
  }
  
  public static void sendReceiver(Context paramContext, int paramInt, String paramString, Bundle paramBundle) {
    Intent intent = new Intent("com.cndatacom.campus.receiver.action");
    intent.putExtra("com.cndatacom.campus.receiver.action.code.key", paramInt);
    if (!TextUtils.isEmpty(paramString) && paramBundle != null)
      intent.putExtra(paramString, paramBundle); 
    intent.setFlags(32);
    paramContext.sendBroadcast(intent, "com.cndatacom.campus.permissions.PORTAL_RECEIVER");
  }
  
  public static void sendServiceReceiver(Context paramContext, int paramInt) {
    MessageResult messageResult = new MessageResult();
    messageResult.setType(paramInt);
    sendServiceReceiver(paramContext, messageResult);
  }
  
  public static void sendServiceReceiver(Context paramContext, int paramInt, ReturnUIResult paramReturnUIResult) {
    try {
      JSONObject jSONObject = new JSONObject();
      jSONObject.put("errorCode", paramReturnUIResult.getErrorCode());
      jSONObject.put("subErrorCode", paramReturnUIResult.getSubErrorCode());
      jSONObject.put("extern", paramReturnUIResult.getExtern());
      MessageResult messageResult = new MessageResult();
      messageResult.setType(paramInt);
      messageResult.setData(jSONObject.toString());
      Bundle bundle = new Bundle();
      bundle.putParcelable("com.cndatacom.campus.receiver.action.data.key", (Parcelable)messageResult);
      sendReceiver(paramContext, 1, "com.cndatacom.campus.receiver.action.bundle.key", bundle);
      return;
    } catch (Exception exception) {
      exception.printStackTrace();
      return;
    } 
  }
  
  public static void sendServiceReceiver(Context paramContext, MessageResult paramMessageResult) {
    Bundle bundle = new Bundle();
    bundle.putParcelable("com.cndatacom.campus.receiver.action.data.key", (Parcelable)paramMessageResult);
    sendReceiver(paramContext, 1, "com.cndatacom.campus.receiver.action.bundle.key", bundle);
  }
  
  public void registerReceiver(Context paramContext) {
    this.a = new BroadcastReceiver(this) {
        public void onReceive(Context param1Context, Intent param1Intent) {
          if (AppReceiver.a(this.a) != null) {
            int i = param1Intent.getIntExtra("com.cndatacom.campus.receiver.action.code.key", -1);
            AppReceiver.a(this.a).onReceive(param1Context, param1Intent, i);
          } 
        }
      };
    paramContext.registerReceiver(this.a, this.b, "com.cndatacom.campus.permissions.PORTAL_RECEIVER", null);
  }
  
  public void setReceiverListener(AppReceiverListener paramAppReceiverListener) {
    this.c = paramAppReceiverListener;
  }
  
  public void unregisterReceiver(Context paramContext) {
    if (this.a != null)
      paramContext.unregisterReceiver(this.a); 
  }
  
  public static interface AppReceiverListener {
    void onReceive(Context param1Context, Intent param1Intent, int param1Int);
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\receiver\AppReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */