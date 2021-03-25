package com.cndatacom.cnportalclient.http;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import com.cndatacom.cnportalclient.battery.BatteryUtils;
import com.cndatacom.cnportalclient.battery.BrandSpec;
import com.cndatacom.cnportalclient.collection.CollectionHelper;
import com.cndatacom.cnportalclient.emulator.EmulatorUtils;
import com.cndatacom.cnportalclient.model.ADInfo;
import com.cndatacom.cnportalclient.model.InnerState;
import com.cndatacom.cnportalclient.model.NetInfo;
import com.cndatacom.cnportalclient.model.ReturnUIResult;
import com.cndatacom.cnportalclient.plugin.ActivityManager;
import com.cndatacom.cnportalclient.plugin.PortalMananger;
import com.cndatacom.cnportalclient.share.ShareAppInfo;
import com.cndatacom.cnportalclient.utils.AppInfoUtils;
import com.cndatacom.cnportalclient.utils.CdcUtils;
import com.cndatacom.cnportalclient.utils.DensityUtils;
import com.cndatacom.cnportalclient.utils.ExLog;
import com.cndatacom.cnportalclient.utils.ImageUtil;
import com.cndatacom.cnportalclient.utils.NetUtils;
import com.cndatacom.cnportalclient.utils.PreferencesUtils;
import com.cndatacom.cnportalclient.utils.ToastUtils;
import com.cndatacom.cnportalclient.widget.dialog.AlartDialogLeftButton;
import com.cndatacom.cnportalclient.widget.dialog.AlartDialogRightButton;
import com.stub.StubApp;
import java.io.File;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PortalConn {
  private static int a(String paramString, InnerState paramInnerState) {
    paramString = CdcProtocol.onCheckVerifyCode(paramInnerState, paramString, paramInnerState.getSchoolId(""));
    return (!TextUtils.isEmpty(paramString) && paramString.equals("11062000")) ? -1 : 0;
  }
  
  private static String a(long paramLong) {
    long l1 = paramLong / 1000L;
    paramLong = l1 / 3600L;
    l1 %= 3600L;
    long l2 = l1 / 60L;
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append(paramLong);
    stringBuilder1.append("");
    String str1 = stringBuilder1.toString();
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append(l2);
    stringBuilder2.append("");
    String str2 = stringBuilder2.toString();
    StringBuilder stringBuilder3 = new StringBuilder();
    stringBuilder3.append(l1 % 60L);
    stringBuilder3.append("");
    return String.format("%s:%s:%s", new Object[] { str1, str2, stringBuilder3.toString() });
  }
  
  private static void a(Context paramContext, String paramString) {
    InnerState innerState = InnerState.load();
    innerState.setNetInfo(new NetInfo());
    innerState.freeDaMod();
    String str1 = innerState.getTicket();
    ReturnUIResult returnUIResult = CdcProtocol.getDaMod(innerState, CdcProtocol.getOneLevelConfig(innerState, "ticket-url", null));
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("handleTermOldVersionByUserAgent->errorCode:");
    stringBuilder.append(returnUIResult.getErrorCode());
    stringBuilder.append(",suberrorCode:");
    stringBuilder.append(returnUIResult.getSubErrorCode());
    ExLog.i(new String[] { stringBuilder.toString() });
    if (!returnUIResult.isSuccess())
      return; 
    String str2 = innerState.getConfig(new String[] { "term" }).optString("url", null);
    stringBuilder = new StringBuilder();
    stringBuilder.append("handleTermOldVersionByUserAgent->termUrl:");
    stringBuilder.append(str2);
    ExLog.i(new String[] { stringBuilder.toString() });
    if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str1) && CdcProtocol.reqireTerm(innerState, str2, str1, "5").isSuccess()) {
      try {
        Thread.sleep(5000L);
      } catch (InterruptedException interruptedException) {
        interruptedException.printStackTrace();
      } 
      PreferencesUtils.putString(paramContext, "oldUserAgent", paramString);
    } 
    innerState.freeDaMod();
  }
  
  private static void a(Context paramContext, String paramString1, String paramString2, String paramString3, InnerState paramInnerState, LoginEvent paramLoginEvent) {
    paramLoginEvent.progress(60);
    ReturnUIResult returnUIResult = CdcProtocol.reqireAuth(paramContext, paramInnerState, paramString1, paramString2, paramString3);
    paramLoginEvent.progress(80);
    if (returnUIResult.isSuccess()) {
      paramLoginEvent.success();
      return;
    } 
    paramLoginEvent.error(returnUIResult);
    if (!returnUIResult.getErrorCode().equals("300308")) {
      if (returnUIResult.getErrorCode().equals("300108"))
        return; 
      if (!returnUIResult.getErrorCode().equals("300") && !returnUIResult.getErrorCode().equals("301"))
        paramLoginEvent.ticket(false); 
    } 
  }
  
  private static void a(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4) {
    SharedPreferences sharedPreferences = paramContext.getSharedPreferences(paramString1, 0);
    String str1 = sharedPreferences.getString(paramString2, "");
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append("handleTermOldVersion->oldTicket:");
    stringBuilder1.append(str1);
    ExLog.i(new String[] { stringBuilder1.toString() });
    String str3 = sharedPreferences.getString(paramString3, "");
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append("handleTermOldVersion->ticketUrl:");
    stringBuilder2.append(str3);
    ExLog.i(new String[] { stringBuilder2.toString() });
    if (TextUtils.isEmpty(str3))
      return; 
    String str4 = str3.replaceAll("&amp;", "&").trim();
    InnerState innerState = InnerState.obtain();
    innerState.setNetInfo(new NetInfo());
    innerState.freeDaMod();
    ReturnUIResult returnUIResult = CdcProtocol.getDaMod(innerState, str4);
    StringBuilder stringBuilder3 = new StringBuilder();
    stringBuilder3.append("handleTermOldVersion->errorCode:");
    stringBuilder3.append(returnUIResult.getErrorCode());
    stringBuilder3.append(",suberrorCode:");
    stringBuilder3.append(returnUIResult.getSubErrorCode());
    ExLog.i(new String[] { stringBuilder3.toString() });
    if (!returnUIResult.isSuccess())
      return; 
    SharedPreferences.Editor editor = sharedPreferences.edit();
    String str2 = sharedPreferences.getString(paramString4, "");
    stringBuilder3 = new StringBuilder();
    stringBuilder3.append("handleTermOldVersion->termUrl:");
    stringBuilder3.append(str2);
    ExLog.i(new String[] { stringBuilder3.toString() });
    if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str1) && CdcProtocol.reqireTerm(innerState, str2.replaceAll("&amp;", "&").trim(), str1, "5").isSuccess()) {
      try {
        Thread.sleep(5000L);
      } catch (InterruptedException interruptedException) {
        interruptedException.printStackTrace();
      } 
      editor.putString(paramString3, "");
      editor.putString(paramString2, "");
      editor.putString(paramString4, "");
    } 
    editor.commit();
    innerState.freeDaMod();
  }
  
  private static void a(CordovaInterface paramCordovaInterface) {
    Context context = StubApp.getOrigApplicationContext(paramCordovaInterface.getActivity().getApplicationContext());
    if ("hn".equals("gd")) {
      a(context, "CDCLogs", "ticket", "ticket-url", "term-url");
    } else if ("hn".equals("hn")) {
      a(context, "TrineaAndroidCommon", "ticket", "ticket-url", "term-url");
    } 
    String str1 = PreferencesUtils.getString(context, "oldUserAgent", "").replaceAll("\\s*", "");
    String str2 = PreferencesUtils.getString(context, "userAgent", "").replaceAll("\\s*", "");
    if (!TextUtils.isEmpty(str1) && !TextUtils.isEmpty(str2) && !str2.equals(str1))
      a(context, str2); 
  }
  
  private static void a(CordovaInterface paramCordovaInterface, InnerState paramInnerState) {
    if (EmulatorUtils.isEmulator(paramInnerState)) {
      ExLog.i(new String[] { "emulator check result -> true" });
      paramCordovaInterface.getActivity().runOnUiThread(new Runnable(paramCordovaInterface) {
            public void run() {
              Handler handler = new Handler(Looper.getMainLooper());
              handler.post(new Runnable(this) {
                    public void run() {
                      Toast.makeText(StubApp.getOrigApplicationContext(this.a.a.getActivity().getApplicationContext()), "不支持该设备", 1).show();
                    }
                  });
              handler.postDelayed(new Runnable(this) {
                    public void run() {
                      PortalConn.exitApp();
                    }
                  },  1000L);
            }
          });
    } 
  }
  
  private static void a(CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView, int paramInt1, int paramInt2, int paramInt3, String paramString1, String paramString2, String paramString3) {
    try {
      JSONObject jSONObject = new JSONObject();
      jSONObject.put("type", paramInt1);
      jSONObject.put("oldState", paramInt2);
      jSONObject.put("newState", paramInt3);
      jSONObject.put("ErrorCode", paramString1);
      if (paramString2 == null)
        paramString2 = ""; 
      jSONObject.put("subErrCode", paramString2);
      if (!TextUtils.isEmpty(paramString3))
        jSONObject.put("extern", paramString3); 
      a(paramCordovaInterface, paramCordovaWebView, jSONObject);
      return;
    } catch (JSONException jSONException) {
      jSONException.printStackTrace();
      return;
    } 
  }
  
  private static void a(CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView, int paramInt, ReturnUIResult paramReturnUIResult) {
    a(paramCordovaInterface, paramCordovaWebView, 51, 1, paramInt, paramReturnUIResult.getErrorCode(), paramReturnUIResult.getSubErrorCode(), paramReturnUIResult.getExtern());
  }
  
  private static void a(CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView, int paramInt, ReturnUIResult paramReturnUIResult, NetInfo paramNetInfo) {
    String str = getNetWorkJsonInfo(paramNetInfo);
    a(paramCordovaInterface, paramCordovaWebView, 51, 33, paramInt, paramReturnUIResult.getErrorCode(), paramReturnUIResult.getSubErrorCode(), str);
  }
  
  private static void a(CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView, Handler paramHandler, int paramInt1, int paramInt2) {
    int i;
    boolean bool;
    switch (paramInt1) {
      default:
        i = 0;
        break;
      case 4:
        bool = paramHandler.hasMessages(1005);
        i = bool ^ true;
        break;
      case 3:
        bool = paramHandler.hasMessages(1004);
        i = bool ^ true;
        break;
      case 2:
        bool = paramHandler.hasMessages(1003);
        i = bool ^ true;
        break;
      case 1:
        bool = paramHandler.hasMessages(1002);
        i = bool ^ true;
        break;
    } 
    if (i != 0)
      return; 
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append("setCheckItemResult");
    stringBuilder1.append("(%s,%s)");
    String str1 = stringBuilder1.toString();
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append(paramInt1);
    stringBuilder2.append("");
    String str2 = stringBuilder2.toString();
    StringBuilder stringBuilder3 = new StringBuilder();
    stringBuilder3.append(paramInt2);
    stringBuilder3.append("");
    a(paramCordovaInterface, paramCordovaWebView, String.format(str1, new Object[] { str2, stringBuilder3.toString() }));
    try {
      Thread.sleep(1000L);
      return;
    } catch (InterruptedException interruptedException) {
      interruptedException.printStackTrace();
      return;
    } 
  }
  
  private static void a(CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView, InnerState paramInnerState1, PortalMananger paramPortalMananger, InnerState paramInnerState2) {
    if (!TextUtils.isEmpty(paramInnerState2.getTicket())) {
      ExLog.i(new String[] { "portalWifiHandle ->term ticket" });
      CdcProtocol.reqireTerm(paramInnerState2, "5", false);
    } 
    queryTicket(paramCordovaInterface, paramCordovaWebView, paramInnerState1, paramPortalMananger, true);
  }
  
  private static void a(CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView, PortalMananger paramPortalMananger, Context paramContext, InnerState paramInnerState, ReturnUIResult paramReturnUIResult) {
    PreferencesUtils.putString(paramContext, "loginSystemTimeString", "0");
    paramInnerState.setState(6).save();
    if (paramReturnUIResult.isSuccess()) {
      queryTicket(paramCordovaInterface, paramCordovaWebView, paramInnerState, paramPortalMananger, true);
      return;
    } 
    if (paramPortalMananger != null)
      paramPortalMananger.onTermSuccessAndCheckNet(); 
  }
  
  private static void a(CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView, PortalMananger paramPortalMananger, InnerState paramInnerState, NetInfo paramNetInfo, int paramInt) {
    Context context = StubApp.getOrigApplicationContext(paramCordovaInterface.getActivity().getApplicationContext());
    NetInfo netInfo = paramInnerState.getNetInfo();
    netInfo.setGateway(paramInnerState.getGateway());
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("net wifi info : ");
    stringBuilder.append(getNetWorkJsonInfo(paramNetInfo));
    ExLog.i(new String[] { stringBuilder.toString() });
    stringBuilder = new StringBuilder();
    stringBuilder.append("local wifi info : ");
    stringBuilder.append(getNetWorkJsonInfo(netInfo));
    ExLog.i(new String[] { stringBuilder.toString() });
    if (paramNetInfo.equalsNetInfo(netInfo, paramInnerState.getGateway())) {
      paramInnerState.setNetInfo(paramNetInfo);
      a(paramCordovaInterface, paramInnerState);
      ReturnUIResult returnUIResult = CdcProtocol.getState(context, paramInnerState);
      stringBuilder = new StringBuilder();
      stringBuilder.append("usefulNetHandle state errorcode : ");
      stringBuilder.append(returnUIResult.getErrorCode());
      stringBuilder.append(" and suberrorcode : ");
      stringBuilder.append(returnUIResult.getSubErrorCode());
      ExLog.i(new String[] { stringBuilder.toString() });
      if (returnUIResult.getErrorCode().equals("0")) {
        if (PreferencesUtils.getString(context, "selfConnect", "1").equals("1") && paramInt != 2) {
          onCheckNetCallBackToJs(paramCordovaInterface, paramCordovaWebView, 31, "200104", getNetWorkJsonInfo(paramNetInfo));
          handleAuthSuccess(paramPortalMananger, paramCordovaInterface, paramCordovaWebView, PreferencesUtils.getString(context, "account", ""), paramInnerState, true);
          return;
        } 
        CdcProtocol.reqireTerm(paramInnerState, "5");
      } 
      onCheckNetCallBackToJs(paramCordovaInterface, paramCordovaWebView, 31, "200104", getNetWorkJsonInfo(paramNetInfo));
      if (paramPortalMananger != null)
        paramPortalMananger.onCheckState(); 
      return;
    } 
    onCheckNetCallBackToJs(paramCordovaInterface, paramCordovaWebView, 32, "200102", getNetWorkJsonInfo(paramNetInfo));
  }
  
  private static void a(CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView, String paramString) {
    paramCordovaInterface.getActivity().runOnUiThread(new Runnable(paramCordovaWebView, paramString) {
          public void run() {
            CordovaWebView cordovaWebView = this.a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("javascript:");
            stringBuilder.append(this.b);
            cordovaWebView.loadUrl(stringBuilder.toString());
          }
        });
  }
  
  private static void a(CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView, JSONObject paramJSONObject) {
    StringBuilder stringBuilder = new StringBuilder("var event=document.createEvent('Events'); event.initEvent('cndatacomEvent', false, false); ");
    stringBuilder.append("event.extra=");
    stringBuilder.append(paramJSONObject.toString());
    stringBuilder.append(";");
    stringBuilder.append("document.dispatchEvent(event);");
    a(paramCordovaInterface, paramCordovaWebView, stringBuilder.toString());
  }
  
  private static boolean a() {
    return (new HttpClient()).get("http://zsteduapp.10000.gd.cn").isSuccessful();
  }
  
  private static boolean a(NetInfo paramNetInfo, InnerState paramInnerState) {
    return paramNetInfo.checkIP(paramInnerState.getWlanuserip());
  }
  
  private static boolean a(String paramString, InnerState paramInnerState, LoginEvent paramLoginEvent) {
    if (a(paramString, paramInnerState) != 0) {
      paramLoginEvent.progress(50);
      paramLoginEvent.error(null);
      paramLoginEvent.needVerifyCode(true);
      return true;
    } 
    return false;
  }
  
  private static void b(CordovaInterface paramCordovaInterface) {
    Activity activity = paramCordovaInterface.getActivity();
    if (PreferencesUtils.getBoolean(StubApp.getOrigApplicationContext(activity.getApplicationContext()), "BatteryTips", true) && !BatteryUtils.isIgnoringBatteryOptimizations(activity)) {
      PreferencesUtils.putBoolean(StubApp.getOrigApplicationContext(activity.getApplicationContext()), "BatteryTips", false);
      View view = LayoutInflater.from(paramCordovaInterface.getContext()).inflate(2131296291, null);
      AlertDialog alertDialog = (new AlertDialog.Builder((Context)paramCordovaInterface.getActivity(), 2131558400)).setView(view).create();
      alertDialog.getWindow().setFlags(1024, 1024);
      alertDialog.setCanceledOnTouchOutside(false);
      alertDialog.setCancelable(false);
      alertDialog.show();
      ((TextView)view.findViewById(2131165357)).setText(paramCordovaInterface.getContext().getResources().getString(2131492914));
      ((TextView)view.findViewById(2131165352)).setText(paramCordovaInterface.getContext().getResources().getString(2131492913));
      ((TextView)view.findViewById(2131165279)).setText(paramCordovaInterface.getContext().getResources().getString(2131492940));
      ((AlartDialogLeftButton)view.findViewById(2131165214)).setOnClickListener(new View.OnClickListener(alertDialog) {
            public void onClick(View param1View) {
              this.a.dismiss();
            }
          });
      ((TextView)view.findViewById(2131165311)).setText(paramCordovaInterface.getContext().getResources().getString(2131492912));
      ((AlartDialogRightButton)view.findViewById(2131165215)).setOnClickListener(new View.OnClickListener(alertDialog, paramCordovaInterface) {
            public void onClick(View param1View) {
              this.a.dismiss();
              BrandSpec.OPER.batteryOptimizations(this.b.getActivity());
            }
          });
    } 
  }
  
  private static void b(CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView, int paramInt, ReturnUIResult paramReturnUIResult, NetInfo paramNetInfo) {
    String str = getNetWorkJsonInfo(paramNetInfo);
    a(paramCordovaInterface, paramCordovaWebView, 51, 12, paramInt, paramReturnUIResult.getErrorCode(), paramReturnUIResult.getSubErrorCode(), str);
  }
  
  private static boolean c(Context paramContext, InnerState paramInnerState) {
    return CdcProtocol.getTicket(paramContext, paramInnerState).isSuccess();
  }
  
  private static InnerState d(Context paramContext, InnerState paramInnerState) {
    NetInfo netInfo;
    switch (CdcProtocol.detectConfig(paramContext, paramInnerState)) {
      default:
        return null;
      case 2:
      case 3:
        paramInnerState = InnerState.load();
        netInfo = new NetInfo((WifiManager)paramContext.getSystemService("wifi"));
        if (paramInnerState.getNetInfo().equals(netInfo))
          return paramInnerState; 
      case 1:
        break;
    } 
    return paramInnerState;
  }
  
  private static void e(Context paramContext, InnerState paramInnerState) {
    String str3 = paramInnerState.getFuncfgUrl("Collection", "");
    if (TextUtils.isEmpty(str3)) {
      ExLog.i(new String[] { "uploadCollectionInfo url is null" });
      return;
    } 
    String str2 = paramInnerState.getFuncfgUrl("Collection", "interval", "");
    String str1 = str2;
    if (TextUtils.isEmpty(str2))
      str1 = "18000"; 
    CollectionHelper.uploadCollectionInfo(paramContext, str3, Long.parseLong(str1), "uploadinfo");
  }
  
  public static void exitApp() {
    ActivityManager.getInstance().exit();
  }
  
  private static void f(Context paramContext, InnerState paramInnerState) {
    ADInfo aDInfo = CdcProtocol.getAdvData(paramContext, paramInnerState);
    if (aDInfo == null)
      return; 
    Display display = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
    DisplayMetrics displayMetrics = new DisplayMetrics();
    display.getMetrics(displayMetrics);
    int i = displayMetrics.widthPixels;
    int j = displayMetrics.heightPixels;
    String str1 = aDInfo.getUrl();
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append("CDCLogs");
    stringBuilder1.append(File.separator);
    stringBuilder1.append("AD");
    File file = CdcUtils.openFileDir(paramContext, stringBuilder1.toString());
    if (file == null)
      return; 
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append(str1.hashCode());
    stringBuilder2.append(".jpg");
    String str3 = stringBuilder2.toString();
    StringBuilder stringBuilder3 = new StringBuilder();
    stringBuilder3.append(file.getPath());
    stringBuilder3.append(File.separator);
    stringBuilder3.append(str3);
    String str2 = stringBuilder3.toString();
    if ((new File(str2)).exists())
      return; 
    Bitmap bitmap = ImageUtil.loadImageFormUrl(paramContext, str1, i, j);
    if (bitmap != null)
      ImageUtil.writeBitmapToFile(str2, bitmap, 100); 
  }
  
  public static void forceExitApp() {
    exitApp();
    Process.killProcess(Process.myPid());
    System.exit(0);
  }
  
  private static void g(Context paramContext, InnerState paramInnerState) {
    NetInfo netInfo = paramInnerState.getNetInfo();
    if (TextUtils.isEmpty(netInfo.getSSID()))
      return; 
    String str2 = PreferencesUtils.getString(paramContext, "AddressHistory", "");
    String str1 = str2;
    if (TextUtils.isEmpty(str2))
      str1 = (new JSONArray()).toString(); 
    try {
      JSONArray jSONArray2 = new JSONArray();
      JSONObject jSONObject = new JSONObject();
      jSONObject.put("SSID", netInfo.getSSID());
      jSONObject.put("IPV4", netInfo.getIpv4());
      jSONObject.put("IPV6", netInfo.getIpv6());
      jSONObject.put("MAC", netInfo.getMac());
      jSONObject.put("NETID", netInfo.getNetId());
      jSONArray2.put(jSONObject);
      JSONArray jSONArray1 = new JSONArray(str1);
      for (int i = 0;; i++) {
        if (i < jSONArray1.length()) {
          jSONObject = jSONArray1.getJSONObject(i);
          if (!jSONObject.optString("SSID", "").equals(netInfo.getSSID()))
            jSONArray2.put(jSONObject); 
        } else {
          String str = jSONArray2.toString();
          if (!TextUtils.isEmpty(str)) {
            PreferencesUtils.putString(paramContext, "AddressHistory", str);
            return;
          } 
          return;
        } 
      } 
    } catch (JSONException jSONException) {
      jSONException.printStackTrace();
    } 
  }
  
  public static void getCacheData(CallbackContext paramCallbackContext, CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView, String paramString, boolean paramBoolean) {
    String str2 = PreferencesUtils.getString(paramCordovaWebView.getContext(), paramString, "");
    String str1 = str2;
    if (paramBoolean)
      str1 = CdcUtils.decrypString(str2); 
    paramCallbackContext.success(str1);
  }
  
  public static String getNetWorkJsonInfo(NetInfo paramNetInfo) {
    try {
      JSONObject jSONObject = new JSONObject();
      if (paramNetInfo == null) {
        jSONObject.put("ssid", "");
        jSONObject.put("strength", 0);
      } else {
        String str2 = paramNetInfo.getSSID();
        String str1 = str2;
        if (TextUtils.isEmpty(str2))
          str1 = "未知SSID"; 
        jSONObject.put("ssid", str1);
        if (paramNetInfo.isLowRSSI()) {
          jSONObject.put("strength", 1);
        } else {
          jSONObject.put("strength", 2);
        } 
        jSONObject.put("ipv4", paramNetInfo.getIpv4());
        jSONObject.put("ipv6", paramNetInfo.getIpv6());
        jSONObject.put("mac", paramNetInfo.getMac());
        jSONObject.put("wifiMac", paramNetInfo.getWifiMac());
        jSONObject.put("netId", paramNetInfo.getNetId());
        jSONObject.put("gateway", paramNetInfo.getGateway());
      } 
      return jSONObject.toString();
    } catch (JSONException jSONException) {
      jSONException.printStackTrace();
      return "";
    } 
  }
  
  public static void handleAuthFail(CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView, InnerState paramInnerState, ReturnUIResult paramReturnUIResult) {
    paramInnerState.setState(0).save();
    if (paramReturnUIResult != null)
      a(paramCordovaInterface, paramCordovaWebView, 3, paramReturnUIResult); 
  }
  
  public static void handleAuthSuccess(PortalMananger paramPortalMananger, CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView, String paramString, InnerState paramInnerState, boolean paramBoolean) {
    String str = paramString;
    if (paramPortalMananger != null) {
      paramPortalMananger.setLastNetInfo(paramInnerState.getNetInfo());
      paramPortalMananger.removeCheckState();
    } 
    Context context = StubApp.getOrigApplicationContext(paramCordovaInterface.getActivity().getApplicationContext());
    paramInnerState.setState(3).save();
    if (!TextUtils.isEmpty(paramString) && !"null".equals(str)) {
      PreferencesUtils.putString(context, "account", str);
      paramString = str;
    } else {
      paramString = PreferencesUtils.getString(context, "account", "");
    } 
    long l2 = Long.parseLong(PreferencesUtils.getString(context, "loginSystemTimeString", "0"));
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append("loginSystemTime ->");
    stringBuilder1.append(l2);
    ExLog.i(new String[] { stringBuilder1.toString() });
    long l1 = 0L;
    if (l2 != 0L) {
      long l = System.currentTimeMillis();
      stringBuilder1 = new StringBuilder();
      stringBuilder1.append("curSystemTime ->");
      stringBuilder1.append(l);
      ExLog.i(new String[] { stringBuilder1.toString() });
      if (l > l2)
        l1 = l - l2; 
    } else {
      stringBuilder1 = new StringBuilder();
      stringBuilder1.append(System.currentTimeMillis());
      stringBuilder1.append("");
      PreferencesUtils.putString(context, "loginSystemTimeString", stringBuilder1.toString());
    } 
    ReturnUIResult returnUIResult = new ReturnUIResult();
    returnUIResult.setErrorCode("200201");
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append(l1);
    stringBuilder2.append("");
    returnUIResult.setExtern(stringBuilder2.toString());
    stringBuilder2 = new StringBuilder();
    stringBuilder2.append("onlineTime ->");
    stringBuilder2.append(l1);
    stringBuilder2.append("/");
    stringBuilder2.append(a(l1));
    ExLog.i(new String[] { stringBuilder2.toString() });
    a(paramCordovaInterface, paramCordovaWebView, 2, returnUIResult);
    paramPortalMananger.startService(paramBoolean);
    b(paramCordovaInterface);
    g(context, paramInnerState);
    paramCordovaInterface.getThreadPool().execute(new Runnable(context, paramString, paramInnerState) {
          public void run() {
            CdcProtocol.getSchoolInfo(this.a, this.b, this.c);
            CdcProtocol.uploadLog(this.a, this.c, this.b);
            CdcProtocol.uploadSSIDData(this.a, this.b, this.c);
            CdcProtocol.uploadPhoneMarketingData(this.a, this.b, this.c);
            PortalConn.a(this.a, this.c);
            PortalConn.b(this.a, this.c);
          }
        });
  }
  
  public static void needVerifyCodeCallBackToJs(CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView, boolean paramBoolean) {
    String str1;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("isShowVerifyCodeLabel");
    stringBuilder.append("(%s)");
    String str2 = stringBuilder.toString();
    if (paramBoolean == true) {
      str1 = "1";
    } else {
      str1 = "0";
    } 
    a(paramCordovaInterface, paramCordovaWebView, String.format(str2, new Object[] { str1 }));
  }
  
  public static void netWorkCheckFailCallBackToJs(CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView, NetInfo paramNetInfo, int paramInt) {
    if (400 <= paramInt && paramInt <= 600) {
      paramInt = 300308;
    } else {
      paramInt = 300108;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramInt);
    stringBuilder.append("");
    onCheckNetCallBackToJs(paramCordovaInterface, paramCordovaWebView, 32, stringBuilder.toString(), getNetWorkJsonInfo(paramNetInfo));
  }
  
  public static void netWorkCheckFailCallBackToJs(CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView, String paramString) {
    onCheckNetCallBackToJs(paramCordovaInterface, paramCordovaWebView, 32, paramString, null);
  }
  
  public static void netWorkCheckFailCallBackToJs(CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView, String paramString, NetInfo paramNetInfo) {
    onCheckNetCallBackToJs(paramCordovaInterface, paramCordovaWebView, 32, paramString, getNetWorkJsonInfo(paramNetInfo));
  }
  
  public static void netWorkWifiWeakCallBackToJs(CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView, NetInfo paramNetInfo) {
    onCheckNetCallBackToJs(paramCordovaInterface, paramCordovaWebView, 32, "200103", getNetWorkJsonInfo(paramNetInfo));
  }
  
  public static void noNetWorkCallBackToJs(CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView, int paramInt) {
    int i = NetUtils.getWifiState(StubApp.getOrigApplicationContext(paramCordovaInterface.getActivity().getApplicationContext()));
    if (i == 2) {
      if (paramInt == 0)
        onCheckNetProgressCallBackToJs(paramCordovaInterface, paramCordovaWebView, "200108", 40); 
      onCheckNetCallBackToJs(paramCordovaInterface, paramCordovaWebView, 32, "200101", null);
      return;
    } 
    if (i == 1)
      onCheckNetCallBackToJs(paramCordovaInterface, paramCordovaWebView, 32, "200100", null); 
  }
  
  public static void onAuth(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, InnerState paramInnerState, LoginEvent paramLoginEvent) {
    paramLoginEvent.progress(30);
    if ((TextUtils.isEmpty(paramString1) || !paramString1.equals(paramString2)) && TextUtils.isEmpty(paramString4) && a(paramString2, paramInnerState, paramLoginEvent))
      return; 
    a(paramContext, paramString2, paramString3, paramString4, paramInnerState, paramLoginEvent);
  }
  
  public static void onAuthProgressCallBackToJs(CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView, int paramInt) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramInt);
    stringBuilder.append("");
    String str = stringBuilder.toString();
    a(paramCordovaInterface, paramCordovaWebView, 1, (new ReturnUIResult()).setExtern(str));
  }
  
  public static void onCheckNet(CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView, InnerState paramInnerState, NetInfo paramNetInfo, int paramInt, PortalMananger paramPortalMananger) {
    paramPortalMananger.stopService("1008");
    a(paramCordovaInterface);
    if (paramInt == 0 || paramInt == 1)
      onCheckNetProgressCallBackToJs(paramCordovaInterface, paramCordovaWebView, "200111", 80); 
    int i = CdcProtocol.detectConfig(StubApp.getOrigApplicationContext(paramCordovaInterface.getActivity().getApplicationContext()), paramInnerState);
    switch (i) {
      default:
        netWorkCheckFailCallBackToJs(paramCordovaInterface, paramCordovaWebView, paramNetInfo, i);
        return;
      case 2:
      case 3:
        a(paramCordovaInterface, paramCordovaWebView, paramPortalMananger, InnerState.load(), paramNetInfo, paramInt);
        return;
      case 1:
        break;
    } 
    a(paramCordovaInterface, paramInnerState);
    InnerState innerState = InnerState.load();
    paramInnerState.setNetInfo(paramNetInfo);
    paramInnerState.save();
    paramInnerState.savePreferences(StubApp.getOrigApplicationContext(paramCordovaInterface.getActivity().getApplicationContext()));
    a(paramCordovaInterface, paramCordovaWebView, paramInnerState, paramPortalMananger, innerState);
  }
  
  public static void onCheckNetCallBackToJs(CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView, int paramInt, String paramString1, String paramString2) {
    a(paramCordovaInterface, paramCordovaWebView, 51, 33, paramInt, paramString1, null, paramString2);
  }
  
  public static void onCheckNetProgressCallBackToJs(CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView, String paramString, int paramInt) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramInt);
    stringBuilder.append("");
    onCheckNetCallBackToJs(paramCordovaInterface, paramCordovaWebView, 33, paramString, stringBuilder.toString());
  }
  
  public static void onCheckUpdate(CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView, InnerState paramInnerState, CallbackContext paramCallbackContext, boolean paramBoolean) {
    Context context = StubApp.getOrigApplicationContext(paramCordovaInterface.getActivity().getApplicationContext());
    JSONObject jSONObject = new JSONObject();
    try {
      JSONObject jSONObject1 = CdcProtocol.onCheckUpdate(context, paramInnerState);
      jSONObject.put("type", "Android");
      if (jSONObject1.has("response") && !TextUtils.isEmpty(jSONObject1.getString("response"))) {
        jSONObject1 = jSONObject1.getJSONObject("response");
        jSONObject.put("data", jSONObject1);
        if (jSONObject1.has("pkg")) {
          JSONObject jSONObject2 = jSONObject1.getJSONObject("pkg");
          String str1 = jSONObject2.optString("version", "");
          String str2 = jSONObject2.optString("md5", "");
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append(str1);
          stringBuilder.append("_");
          stringBuilder.append("ApkMD5Key");
          PreferencesUtils.putString(context, stringBuilder.toString(), str2);
          if (!TextUtils.isEmpty(str1)) {
            StringBuilder stringBuilder1 = new StringBuilder();
            stringBuilder1.append("popUpdateWindow");
            stringBuilder1.append("(%s)");
            a(paramCordovaInterface, paramCordovaWebView, String.format(stringBuilder1.toString(), new Object[] { jSONObject.toString() }));
            return;
          } 
        } 
      } 
      if (paramBoolean) {
        paramCordovaInterface.getActivity().runOnUiThread(new Runnable(context) {
              public void run() {
                ToastUtils.showBottomText(this.a, "当前已是最新版本");
              }
            });
        return;
      } 
    } catch (JSONException jSONException) {
      jSONException.printStackTrace();
    } 
  }
  
  public static void onGetVerificatioCode(CordovaInterface paramCordovaInterface, CallbackContext paramCallbackContext, CordovaWebView paramCordovaWebView, String paramString, InnerState paramInnerState) {
    String str = CdcProtocol.getVerifyCode(paramInnerState, paramString, paramInnerState.getSchoolId(""));
    if (!TextUtils.isEmpty(str) && str.equals("0")) {
      paramCallbackContext.success("");
      return;
    } 
    paramCallbackContext.error("");
  }
  
  public static void onKeepChangeCallBackToJs(CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView, ReturnUIResult paramReturnUIResult, NetInfo paramNetInfo) {
    String str = getNetWorkJsonInfo(paramNetInfo);
    a(paramCordovaInterface, paramCordovaWebView, 51, 2, 3, paramReturnUIResult.getErrorCode(), paramReturnUIResult.getSubErrorCode(), str);
  }
  
  public static void onOnlineServer(CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView, CallbackContext paramCallbackContext) {
    Context context = StubApp.getOrigApplicationContext(paramCordovaInterface.getActivity().getApplicationContext());
    String[] arrayOfString = new String[5];
    arrayOfString[0] = "com.tencent.mobileqq";
    arrayOfString[1] = "com.tencent.qqlite";
    arrayOfString[2] = "com.tencent.minihd.qq";
    arrayOfString[3] = "com.tencent.mobileqqi";
    arrayOfString[4] = "com.tencent.tim";
    for (int i = 0; i < arrayOfString.length; i++) {
      if (AppInfoUtils.isAppInstalled(context, arrayOfString[i])) {
        paramCallbackContext.success();
        break;
      } 
    } 
    paramCallbackContext.error("");
  }
  
  public static void onQRcodeCallBackToJs(CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView, int paramInt, ReturnUIResult paramReturnUIResult) {
    try {
      String str;
      JSONObject jSONObject = new JSONObject();
      jSONObject.put("type", 51);
      jSONObject.put("oldState", 1);
      jSONObject.put("newState", paramInt);
      jSONObject.put("ErrorCode", paramReturnUIResult.getErrorCode());
      if (paramReturnUIResult.getSubErrorCode() != null) {
        str = paramReturnUIResult.getSubErrorCode();
      } else {
        str = "";
      } 
      jSONObject.put("subErrCode", str);
      if (!TextUtils.isEmpty(paramReturnUIResult.getExtern()))
        jSONObject.put("extern", paramReturnUIResult.getExtern()); 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("HandleQRCodeFailMsg");
      stringBuilder.append("(%s)");
      a(paramCordovaInterface, paramCordovaWebView, String.format(stringBuilder.toString(), new Object[] { jSONObject.toString() }));
      return;
    } catch (JSONException jSONException) {
      jSONException.printStackTrace();
      return;
    } 
  }
  
  public static void onSetPhoneCSSStyle(CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView) {
    try {
      Context context = StubApp.getOrigApplicationContext(paramCordovaInterface.getActivity().getApplicationContext());
      JSONObject jSONObject = new JSONObject();
      new DisplayMetrics();
      DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
      int i = displayMetrics.widthPixels;
      int j = displayMetrics.heightPixels;
      jSONObject.put("deviceWidth", i);
      jSONObject.put("deviceHeight", j);
      if (AppInfoUtils.isPad(context)) {
        jSONObject.put("phoneType", "androidPad");
      } else {
        jSONObject.put("phoneType", "android");
      } 
      jSONObject.put("appText", AppInfoUtils.getAppName(context));
      jSONObject.put("appVersion", AppInfoUtils.getVerName(context));
      String str2 = context.getResources().getString(2131492948);
      String str1 = str2;
      if (TextUtils.isEmpty(str2))
        str1 = ""; 
      jSONObject.put("appCopyright", str1);
      if (Build.VERSION.SDK_INT >= 21) {
        jSONObject.put("fillStatusBar", true);
      } else {
        jSONObject.put("fillStatusBar", false);
      } 
      jSONObject.put("statusBarHeight", DensityUtils.px2dip(context, AppInfoUtils.getStatusBarHeight(context)));
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("changePhoneCSSStyle");
      stringBuilder.append("(%s)");
      a(paramCordovaInterface, paramCordovaWebView, String.format(stringBuilder.toString(), new Object[] { jSONObject.toString() }));
      return;
    } catch (JSONException jSONException) {
      jSONException.printStackTrace();
      return;
    } 
  }
  
  public static void onSharedChangeCallBackToJs(CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView, ShareAppInfo paramShareAppInfo) {
    try {
      JSONObject jSONObject = new JSONObject();
      jSONObject.put("appName", paramShareAppInfo.getAppName());
      jSONObject.put("packageName", paramShareAppInfo.getPackagename());
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("SharedCallBackFun");
      stringBuilder.append("(%s)");
      a(paramCordovaInterface, paramCordovaWebView, String.format(stringBuilder.toString(), new Object[] { jSONObject.toString() }));
      return;
    } catch (Exception exception) {
      exception.printStackTrace();
      return;
    } 
  }
  
  public static void onTerm(PortalMananger paramPortalMananger, CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView, String paramString, InnerState paramInnerState) {
    Context context = StubApp.getOrigApplicationContext(paramCordovaInterface.getActivity().getApplicationContext());
    ReturnUIResult returnUIResult = CdcProtocol.reqireTerm(paramInnerState, paramString);
    a(paramCordovaInterface, paramCordovaWebView, paramPortalMananger, context, paramInnerState, returnUIResult);
    b(paramCordovaInterface, paramCordovaWebView, 12, returnUIResult, paramInnerState.getNetInfo());
  }
  
  public static void onTermOther(CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView, CallbackContext paramCallbackContext, String paramString1, String paramString2, InnerState paramInnerState) {
    ReturnUIResult returnUIResult = CdcProtocol.reqireTerm(paramInnerState, paramString1, paramString2);
    if (returnUIResult.isSuccess()) {
      paramCallbackContext.success();
      return;
    } 
    try {
      JSONObject jSONObject = new JSONObject();
      jSONObject.put("errorCode", returnUIResult.getErrorCode());
      jSONObject.put("subCode", returnUIResult.getSubErrorCode());
      paramCallbackContext.error(jSONObject);
      return;
    } catch (Exception exception) {
      exception.printStackTrace();
      return;
    } 
  }
  
  public static void permissionSuccessCallBackToJs(CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView, String paramString) {
    try {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("PermissionSuccessCallBack");
      stringBuilder.append("('%s')");
      a(paramCordovaInterface, paramCordovaWebView, String.format(stringBuilder.toString(), new Object[] { paramString }));
      return;
    } catch (Exception exception) {
      exception.printStackTrace();
      return;
    } 
  }
  
  public static void queryTicket(CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView, InnerState paramInnerState, PortalMananger paramPortalMananger, boolean paramBoolean) {
    ReturnUIResult returnUIResult = CdcProtocol.getTicket(StubApp.getOrigApplicationContext(paramCordovaInterface.getActivity().getApplicationContext()), paramInnerState);
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("ticket errorcode : ");
    stringBuilder.append(returnUIResult.getErrorCode());
    stringBuilder.append(" and ticket suberrorcode : ");
    stringBuilder.append(returnUIResult.getSubErrorCode());
    ExLog.i(new String[] { stringBuilder.toString() });
    if (returnUIResult.isSuccess()) {
      if (paramBoolean)
        onCheckNetCallBackToJs(paramCordovaInterface, paramCordovaWebView, 31, "200104", getNetWorkJsonInfo(paramInnerState.getNetInfo())); 
      if (paramPortalMananger != null) {
        paramPortalMananger.onCheckState();
        return;
      } 
    } else {
      a(paramCordovaInterface, paramCordovaWebView, 32, returnUIResult, paramInnerState.getNetInfo());
    } 
  }
  
  public static void runCheckNetTool(CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView, Handler paramHandler, int paramInt) {
    Context context = StubApp.getOrigApplicationContext(paramCordovaInterface.getActivity().getApplicationContext());
    if (paramInt != 1) {
      if (paramInt != 5) {
        InnerState innerState = d(context, InnerState.obtain());
        if (paramInt == 2) {
          if (innerState == null) {
            ExLog.e(new String[] { "runCheckNetTool ->校园无线检测有问题" });
            a(paramCordovaInterface, paramCordovaWebView, paramHandler, 2, -1);
            return;
          } 
          if (paramInt == 2) {
            a(paramCordovaInterface, paramCordovaWebView, paramHandler, 2, 0);
            return;
          } 
        } else {
          InnerState innerState1 = innerState;
          if (innerState == null)
            innerState1 = InnerState.obtain(); 
          NetInfo netInfo = new NetInfo((WifiManager)context.getSystemService("wifi"));
          innerState1.setNetInfo(netInfo);
          if (paramInt == 3) {
            if (!a(netInfo, innerState1)) {
              ExLog.e(new String[] { "runCheckNetTool ->路由检测有问题" });
              a(paramCordovaInterface, paramCordovaWebView, paramHandler, 3, -1);
              return;
            } 
            a(paramCordovaInterface, paramCordovaWebView, paramHandler, 3, 0);
            return;
          } 
          if (paramInt == 4) {
            if (!c(context, innerState1)) {
              ExLog.e(new String[] { "runCheckNetTool ->认证服务器不正确" });
              a(paramCordovaInterface, paramCordovaWebView, paramHandler, 4, -1);
              return;
            } 
            a(paramCordovaInterface, paramCordovaWebView, paramHandler, 4, 0);
            return;
          } 
        } 
      } else {
        if (!a()) {
          ExLog.e(new String[] { "runCheckNetTool ->客户端服务器不正确" });
          a(paramCordovaInterface, paramCordovaWebView, paramHandler, 5, -1);
        } else {
          a(paramCordovaInterface, paramCordovaWebView, paramHandler, 5, 0);
        } 
        ExLog.i(new String[] { "runCheckNetTool -> success and finish " });
        return;
      } 
    } else {
      if (!CdcUtils.isWifiConnected(context)) {
        ExLog.e(new String[] { "runCheckNetTool ->无线开关检测有问题" });
        a(paramCordovaInterface, paramCordovaWebView, paramHandler, 1, -1);
        return;
      } 
      a(paramCordovaInterface, paramCordovaWebView, paramHandler, 1, 0);
    } 
  }
  
  public static void runManagerInternetTool(CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView, CallbackContext paramCallbackContext, InnerState paramInnerState) {
    JSONObject jSONObject = CdcProtocol.onQueryClient(StubApp.getOrigApplicationContext(paramCordovaInterface.getActivity().getApplicationContext()), paramInnerState);
    if (jSONObject != null)
      try {
        if (jSONObject.has("client")) {
          paramCallbackContext.success(jSONObject);
          return;
        } 
        paramCallbackContext.error(jSONObject);
        return;
      } catch (Exception exception) {
        exception.printStackTrace();
      }  
  }
  
  public static void setCacheData(CallbackContext paramCallbackContext, CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView, String paramString1, String paramString2, boolean paramBoolean) {
    Context context = paramCordovaWebView.getContext();
    String str = paramString2;
    if (paramBoolean)
      str = CdcUtils.encrypString(paramString2); 
    if (PreferencesUtils.putString(context, paramString1, String.valueOf(str))) {
      paramCallbackContext.success("");
      return;
    } 
    paramCallbackContext.error(-1);
  }
  
  public static interface LoginEvent {
    void error(ReturnUIResult param1ReturnUIResult);
    
    void needVerifyCode(boolean param1Boolean);
    
    void progress(int param1Int);
    
    void success();
    
    void ticket(boolean param1Boolean);
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\http\PortalConn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */