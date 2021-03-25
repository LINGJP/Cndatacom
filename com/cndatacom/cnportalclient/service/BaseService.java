package com.cndatacom.cnportalclient.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Parcelable;
import android.text.TextUtils;
import com.cndatacom.cnportalclient.MainActivity;
import com.cndatacom.cnportalclient.collection.CollectionHelper;
import com.cndatacom.cnportalclient.http.CdcProtocol;
import com.cndatacom.cnportalclient.http.PortalConn;
import com.cndatacom.cnportalclient.model.CollectionInfo;
import com.cndatacom.cnportalclient.model.InnerState;
import com.cndatacom.cnportalclient.model.MessageResult;
import com.cndatacom.cnportalclient.model.ReturnUIResult;
import com.cndatacom.cnportalclient.notify.CDCManagerHelper;
import com.cndatacom.cnportalclient.receiver.AppReceiver;
import com.cndatacom.cnportalclient.share.ShareAppInfo;
import com.cndatacom.cnportalclient.share.ShareHelper;
import com.cndatacom.cnportalclient.utils.CdcUtils;
import com.cndatacom.cnportalclient.utils.ExLog;
import com.cndatacom.cnportalclient.utils.PreferencesUtils;
import com.stub.StubApp;
import org.json.JSONObject;

public abstract class BaseService {
  public static final String ACTION_REVERSE_NOTIFY = "com.cndatacom.campus.netcore.holdservice.action.notify";
  
  public static final String ACTION_SHARED = "com.cndatacom.campus.netcore.holdservice.action.shared";
  
  private ServiceInterface a;
  
  private String b = "";
  
  private String c = "";
  
  private WifiManager.MulticastLock d;
  
  private WifiManager.WifiLock e;
  
  private void a(Service paramService) {
    try {
      WifiManager wifiManager = (WifiManager)StubApp.getOrigApplicationContext(paramService.getApplicationContext()).getSystemService("wifi");
      this.d = wifiManager.createMulticastLock("com.cndatacom.multicast.lock");
      this.d.acquire();
      this.e = wifiManager.createWifiLock(3, "com.cndatacom.wifi.lock");
      this.e.acquire();
      return;
    } catch (Exception exception) {
      exception.printStackTrace();
      return;
    } 
  }
  
  private void a(Context paramContext) {
    this.b = a();
    CollectionHelper.cutCollectionInfo("holdinfo", "uploadinfo");
    if (TextUtils.isEmpty(this.c))
      this.c = PreferencesUtils.getString(paramContext, "account", ""); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("initCollectionData account ->");
    stringBuilder.append(this.c);
    ExLog.d(new String[] { stringBuilder.toString() });
    CollectionHelper.addCollectionInfo("holdinfo", CollectionInfo.madeDefCollectionInfo(paramContext, this.b, this.c, InnerState.load()));
    CollectionHelper collectionHelper = CollectionHelper.getInstance();
    collectionHelper.setCallBack(new CollectionHelper.CallBack(this, paramContext) {
          public void collection() {
            BaseService.a(this.b, this.a);
          }
        });
    collectionHelper.start();
  }
  
  private void a(Context paramContext, Service paramService) {
    Notification.Builder builder;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(b());
    stringBuilder.append(" initNotification ...");
    ExLog.i(new String[] { stringBuilder.toString() });
    PendingIntent pendingIntent = PendingIntent.getActivity(paramContext, 0, new Intent(paramContext, MainActivity.class), 0);
    if (Build.VERSION.SDK_INT >= 26) {
      ((NotificationManager)paramContext.getSystemService("notification")).createNotificationChannel(new NotificationChannel("HoldChannel", "HoldChannelName", 4));
      builder = new Notification.Builder(paramContext, "HoldChannel");
    } else {
      builder = new Notification.Builder(paramContext);
    } 
    paramService.startForeground(18, builder.setShowWhen(false).setAutoCancel(false).setContentTitle(paramContext.getResources().getString(2131492904)).setContentIntent(pendingIntent).setSmallIcon(2131361792).setLargeIcon(BitmapFactory.decodeResource(paramContext.getResources(), 2131361792)).build());
  }
  
  private void b(Context paramContext) {
    InnerState innerState = InnerState.load();
    if (TextUtils.isEmpty(this.c))
      this.c = PreferencesUtils.getString(paramContext, "account", ""); 
    CollectionHelper.updateCollectionInfo("holdinfo", this.b, this.c, innerState);
  }
  
  private void c(Context paramContext) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(b());
    stringBuilder.append(" initCDCMessage ...");
    ExLog.i(new String[] { stringBuilder.toString() });
    InnerState innerState = InnerState.load();
    JSONObject jSONObject = innerState.getConfig(new String[] { "config" }).optJSONObject("notify");
    if (jSONObject != null) {
      if (jSONObject.isNull("register"))
        return; 
      String str = jSONObject.optString("register", "");
      if (!TextUtils.isEmpty(str)) {
        String[] arrayOfString = str.split("//");
        if (arrayOfString.length > 1) {
          String[] arrayOfString1 = arrayOfString[1].split(":");
          if (arrayOfString1.length > 1) {
            String str1 = arrayOfString1[0];
            int i = CdcUtils.toInt(arrayOfString1[1], -1);
            if (i > 0) {
              CDCManagerHelper cDCManagerHelper = CDCManagerHelper.newInstance();
              cDCManagerHelper.init(str1, i);
              cDCManagerHelper.setCDCMessageCallBack(new CDCManagerHelper.CDCMessageCallBack(this, innerState, paramContext) {
                    public void termCallBack() {
                      CdcProtocol.reqireTerm(this.a, "8");
                      Intent intent = new Intent("com.cndatacom.campus.netcore.holdservice.action.notify");
                      ReturnUIResult returnUIResult = new ReturnUIResult();
                      returnUIResult.setErrorCode("200118");
                      intent.putExtra("error", (Parcelable)returnUIResult);
                      this.b.sendBroadcast(intent, "com.cndatacom.campus.permissions.PORTAL_RECEIVER");
                      CollectionHelper.updateCollectionInfo("holdinfo", BaseService.b(this.c), "1007");
                      if (BaseService.a(this.c) != null) {
                        BaseService.a(this.c).releaseService(this.b);
                        BaseService.a(this.c).stopService(this.b);
                      } 
                    }
                  });
            } 
          } 
        } 
      } 
      return;
    } 
  }
  
  private void d() {
    if (this.d != null && this.d.isHeld())
      this.d.release(); 
    if (this.e != null && this.e.isHeld())
      this.e.release(); 
  }
  
  private void d(Context paramContext) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(b());
    stringBuilder.append(" initShareData ...");
    ExLog.i(new String[] { stringBuilder.toString() });
    ShareHelper.newInstance(paramContext).setShareCallBack(new ShareHelper.ShareCallBack(this, paramContext) {
          public void sharedCallBack(int param1Int, ShareAppInfo param1ShareAppInfo) {
            ReturnUIResult returnUIResult;
            CdcProtocol.reqireTerm(InnerState.load(), "6");
            Intent intent = new Intent("com.cndatacom.campus.netcore.holdservice.action.shared");
            switch (param1Int) {
              case 1:
                intent.putExtra("error", (Parcelable)param1ShareAppInfo);
                break;
              case 0:
                returnUIResult = new ReturnUIResult();
                returnUIResult.setErrorCode("200119");
                intent.putExtra("error", (Parcelable)returnUIResult);
                break;
            } 
            this.a.sendBroadcast(intent, "com.cndatacom.campus.permissions.PORTAL_RECEIVER");
            CollectionHelper.updateCollectionInfo("holdinfo", BaseService.b(this.b), "1004");
            if (BaseService.a(this.b) != null) {
              BaseService.a(this.b).releaseService(this.a);
              BaseService.a(this.b).stopService(this.a);
            } 
          }
        });
    ShareHelper.newInstance(paramContext).start();
  }
  
  protected abstract String a();
  
  protected abstract String b();
  
  protected abstract ServiceInterface c();
  
  public void handleAuth(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4) {
    PortalConn.onAuth(paramContext, paramString1, paramString2, paramString3, paramString4, InnerState.load(), new PortalConn.LoginEvent(this, paramContext, paramString2) {
          public void error(ReturnUIResult param1ReturnUIResult) {
            AppReceiver.sendServiceReceiver(this.a, 13, param1ReturnUIResult);
          }
          
          public void needVerifyCode(boolean param1Boolean) {
            MessageResult messageResult = new MessageResult();
            messageResult.setType(15);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(param1Boolean);
            stringBuilder.append("");
            messageResult.setData(stringBuilder.toString());
            AppReceiver.sendServiceReceiver(this.a, messageResult);
          }
          
          public void progress(int param1Int) {
            MessageResult messageResult = new MessageResult();
            messageResult.setType(10);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(param1Int);
            stringBuilder.append("");
            messageResult.setData(stringBuilder.toString());
            AppReceiver.sendServiceReceiver(this.a, messageResult);
          }
          
          public void success() {
            BaseService.a(this.c, this.b);
            MessageResult messageResult = new MessageResult();
            messageResult.setType(11);
            messageResult.setData(this.b);
            AppReceiver.sendServiceReceiver(this.a, messageResult);
            if (BaseService.a(this.c) != null)
              BaseService.a(this.c).loginSuccess(this.a); 
          }
          
          public void ticket(boolean param1Boolean) {
            MessageResult messageResult = new MessageResult();
            messageResult.setType(16);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(param1Boolean);
            stringBuilder.append("");
            messageResult.setData(stringBuilder.toString());
            AppReceiver.sendServiceReceiver(this.a, messageResult);
          }
        });
  }
  
  public void handleReceive(Context paramContext, MessageResult paramMessageResult) {
    JSONObject jSONObject;
    switch (paramMessageResult.getType()) {
      default:
        return;
      case 5:
        CollectionHelper.updateCollectionInfo("holdinfo", this.b, "1006");
      case 4:
        if (this.a != null) {
          this.a.releaseService(paramContext);
          this.a.stopService(paramContext);
        } 
        CdcProtocol.reqireTerm(InnerState.load(), "1");
        try {
          ReturnUIResult returnUIResult = new ReturnUIResult();
          jSONObject = new JSONObject(paramMessageResult.getData());
          returnUIResult.setErrorCode(jSONObject.optString("errorCode", "0"));
          returnUIResult.setSubErrorCode(jSONObject.optString("subErrorCode", ""));
          returnUIResult.setExtern(jSONObject.optString("extern", ""));
          AppReceiver.sendServiceReceiver(paramContext, 14, returnUIResult);
          return;
        } catch (Exception exception) {
          exception.printStackTrace();
          return;
        } 
      case 2:
        if (this.a != null) {
          this.a.releaseService((Context)exception);
          this.a.stopService((Context)exception);
          return;
        } 
        return;
      case 1:
        break;
    } 
    try {
      jSONObject = new JSONObject(jSONObject.getData());
      handleAuth((Context)exception, jSONObject.optString("oldAccount", ""), jSONObject.optString("account", ""), jSONObject.optString("password", ""), jSONObject.optString("vertifyCode", ""));
      return;
    } catch (Exception exception1) {
      exception1.printStackTrace();
    } 
  }
  
  public void initData(Context paramContext) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(b());
    stringBuilder.append(" initData ...");
    ExLog.i(new String[] { stringBuilder.toString() });
    d(paramContext);
    c(paramContext);
    a(paramContext);
  }
  
  public void onStartCommand(Context paramContext, Service paramService) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(b());
    stringBuilder.append(" onStartCommand ...");
    ExLog.i(new String[] { stringBuilder.toString() });
    this.a = c();
    this.b = "";
    this.c = "";
    a(paramService);
    a(paramContext, paramService);
  }
  
  public void release(Context paramContext) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(b());
    stringBuilder.append(" release ...");
    ExLog.i(new String[] { stringBuilder.toString() });
    d();
    CDCManagerHelper.newInstance().release();
    ShareHelper.newInstance(paramContext).release();
    CollectionHelper.getInstance().release();
  }
  
  public void stopForeground(Service paramService) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(b());
    stringBuilder.append(" stopForeground ...");
    ExLog.i(new String[] { stringBuilder.toString() });
    if (Build.VERSION.SDK_INT >= 26)
      paramService.stopForeground(true); 
  }
  
  public static interface ServiceInterface {
    void loginSuccess(Context param1Context);
    
    void releaseService(Context param1Context);
    
    void stopService(Context param1Context);
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\service\BaseService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */