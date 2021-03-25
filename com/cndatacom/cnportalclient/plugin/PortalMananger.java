package com.cndatacom.cnportalclient.plugin;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.cndatacom.cnportalclient.CaptureActivity;
import com.cndatacom.cnportalclient.OnlineMoneyActivity;
import com.cndatacom.cnportalclient.PWebViewActivity;
import com.cndatacom.cnportalclient.WebViewActivity;
import com.cndatacom.cnportalclient.async.AsyncHandlerTask;
import com.cndatacom.cnportalclient.collection.CollectionHelper;
import com.cndatacom.cnportalclient.constant.Constant;
import com.cndatacom.cnportalclient.constant.FromWhereCode;
import com.cndatacom.cnportalclient.constant.UiErrCode;
import com.cndatacom.cnportalclient.download.entity.DownloadTask;
import com.cndatacom.cnportalclient.handler.WeakRefHandler;
import com.cndatacom.cnportalclient.http.CdcProtocol;
import com.cndatacom.cnportalclient.http.HttpClient;
import com.cndatacom.cnportalclient.http.PortalConn;
import com.cndatacom.cnportalclient.model.ADInfo;
import com.cndatacom.cnportalclient.model.CollectionInfo;
import com.cndatacom.cnportalclient.model.InnerState;
import com.cndatacom.cnportalclient.model.MessageResult;
import com.cndatacom.cnportalclient.model.NetInfo;
import com.cndatacom.cnportalclient.model.ReturnUIResult;
import com.cndatacom.cnportalclient.receiver.AppReceiver;
import com.cndatacom.cnportalclient.receiver.NetWorkReceiver;
import com.cndatacom.cnportalclient.service.HoldService;
import com.cndatacom.cnportalclient.service.HoldVpnService;
import com.cndatacom.cnportalclient.share.ShareAppInfo;
import com.cndatacom.cnportalclient.utils.AppInfoUtils;
import com.cndatacom.cnportalclient.utils.CdcUtils;
import com.cndatacom.cnportalclient.utils.ExLog;
import com.cndatacom.cnportalclient.utils.FileUtils;
import com.cndatacom.cnportalclient.utils.ImageUtil;
import com.cndatacom.cnportalclient.utils.PreferencesUtils;
import com.cndatacom.cnportalclient.utils.ServiceUtils;
import com.cndatacom.cnportalclient.utils.SpannableStringUtils;
import com.cndatacom.cnportalclient.utils.ToastUtils;
import com.cndatacom.cnportalclient.widget.RRectangleLinearLayout;
import com.cndatacom.cnportalclient.widget.dialog.AlartDialogCenterButton;
import com.cndatacom.cnportalclient.widget.dialog.AlartDialogLeftButton;
import com.cndatacom.cnportalclient.widget.dialog.AlartDialogRightButton;
import com.stub.StubApp;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PermissionHelper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PortalMananger extends CordovaPlugin {
  public static final String PORTAL_RECEIVER_PERMISSION = "com.cndatacom.campus.permissions.PORTAL_RECEIVER";
  
  private final int A = 0;
  
  private final int B = 1;
  
  private AlertDialog C;
  
  private boolean D = false;
  
  private boolean E = false;
  
  private NetWorkReceiver F = null;
  
  private AppReceiver G;
  
  private NetInfo H = null;
  
  private NetInfo I = null;
  
  private final String J = UUID.randomUUID().toString();
  
  private String K = "";
  
  private Runnable L = null;
  
  private Runnable M = new Runnable(this) {
      public void run() {
        PortalMananger.l(this.a);
      }
    };
  
  private DownloadApkManager a;
  
  private ProgressDialog b;
  
  private Dialog c;
  
  private Context d;
  
  private ImageView e;
  
  private ImageView f;
  
  private TextView g;
  
  private RRectangleLinearLayout h;
  
  private boolean i = false;
  
  private final int j = 5000;
  
  private final int k = 2000;
  
  private WeakRefHandler<CordovaPlugin> l;
  
  private HandlerThread m;
  
  private WeakRefHandler<CordovaPlugin> n;
  
  private final int o = 0;
  
  private final int p = 1;
  
  private final int q = 2;
  
  private final int r = 3;
  
  private final int s = 4;
  
  private final int t = 5;
  
  private final int u = 6;
  
  private final int v = 7;
  
  private final int w = 8;
  
  private final int x = 9;
  
  private final int y = 5000;
  
  private final int z = 60000;
  
  private WifiManager A() {
    return (WifiManager)StubApp.getOrigApplicationContext(this.cordova.getContext().getApplicationContext()).getSystemService("wifi");
  }
  
  private void a(int paramInt) {
    TextView textView = this.g;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramInt / 1000);
    stringBuilder.append("");
    textView.setText(stringBuilder.toString());
    if (paramInt < 0) {
      a(true);
      return;
    } 
    Message message = Message.obtain();
    message.what = 5;
    message.obj = Integer.valueOf(paramInt - 1000);
    this.n.sendMessageDelayed(message, 1000L);
  }
  
  private void a(Context paramContext) {
    if (CdcUtils.checkPermission("android.permission.ACCESS_COARSE_LOCATION", paramContext) && Build.VERSION.SDK_INT >= 28 && PreferencesUtils.getBoolean(paramContext, "LOCATION_SERVICE", true) && !CdcUtils.isLocationServiceEnable(paramContext)) {
      PreferencesUtils.putBoolean(paramContext, "LOCATION_SERVICE", false);
      p();
      return;
    } 
    o();
  }
  
  private void a(Context paramContext, String[] paramArrayOfString) {
    ArrayList<String> arrayList = new ArrayList();
    for (int i = 0; i < paramArrayOfString.length; i++) {
      String str = paramArrayOfString[i];
      if (!CdcUtils.checkPermission(str, paramContext))
        arrayList.add(str); 
    } 
    if (arrayList.size() == 0) {
      if (this.C != null && this.C.isShowing())
        this.C.dismiss(); 
      if (this.L != null) {
        this.n.post(this.L);
        this.L = null;
        return;
      } 
      PortalConn.permissionSuccessCallBackToJs(this.cordova, this.webView, paramArrayOfString[0]);
      return;
    } 
    this.n.post(new Runnable(this, arrayList, paramContext) {
          public void run() {
            PortalMananger.a(this.c, (Runnable)null);
            String[] arrayOfString = (String[])this.a.toArray((Object[])new String[this.a.size()]);
            PortalMananger.a(this.c, arrayOfString, this.c.cordova.getActivity(), this.b);
          }
        });
  }
  
  private void a(Message paramMessage) {
    int i = paramMessage.what;
    if (i != 5) {
      if (i != 9) {
        switch (i) {
          default:
            return;
          case 3:
            n().setVisibility(0);
            return;
          case 2:
            a(true);
            return;
          case 1:
            break;
        } 
        l();
        z();
        return;
      } 
      y();
      return;
    } 
    a(((Integer)paramMessage.obj).intValue());
  }
  
  private void a(InnerState paramInnerState) {
    String str2 = Build.ID;
    String str3 = Build.DISPLAY;
    String str4 = Build.PRODUCT;
    String str5 = Build.DEVICE;
    String str6 = Build.BOARD;
    String str7 = Build.CPU_ABI;
    String str8 = Build.CPU_ABI2;
    String str9 = Build.MANUFACTURER;
    String str10 = Build.BRAND;
    String str11 = Build.MODEL;
    String str12 = Build.BOOTLOADER;
    String str13 = Build.HARDWARE;
    String str14 = Build.SERIAL;
    String str15 = Build.TYPE;
    String str16 = Build.TAGS;
    String str17 = Build.FINGERPRINT;
    String str18 = Build.USER;
    String str19 = Build.HOST;
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append(Build.VERSION.SDK_INT);
    stringBuilder1.append("");
    String str20 = stringBuilder1.toString();
    String str21 = Build.VERSION.RELEASE;
    String str1 = paramInnerState.getClientId();
    String str22 = AppInfoUtils.getVerName(StubApp.getOrigApplicationContext(this.cordova.getActivity().getApplicationContext()));
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append("\r\nBuild.ApkMD5:");
    stringBuilder2.append(FileUtils.getFileMD5(this.cordova.getContext().getPackageCodePath()));
    ExLog.i(new String[] { 
          "\n==========Build==========", "\r\nBuild.ID:", str2, "\r\nBuild.DISPLAY:", str3, "\r\nBuild.PRODUCT:", str4, "\r\nBuild.DEVICE:", str5, "\r\nBuild.BOARD:", 
          str6, "\r\nBuild.CPU_ABI:", str7, "\r\nBuild.CPU_ABI2:", str8, "\r\nBuild.MANUFACTURER:", str9, "\r\nBuild.BRAND:", str10, "\r\nBuild.MODEL:", 
          str11, "\r\nBuild.BOOTLOADER:", str12, "\r\nBuild.HARDWARE:", str13, "\r\nBuild.SERIAL:", str14, "\r\nBuild.TYPE:", str15, "\r\nBuild.TAGS:", 
          str16, "\r\nBuild.FINGERPRINT:", str17, "\r\nBuild.USER:", str18, "\r\nBuild.HOST:", str19, "\r\nBuild.SDK_INT:", str20, "\r\nBuild.RELEASE:", 
          str21, "\r\nAPP.CLIENTID:", str1, "\r\nAPP.VERSION:", str22, stringBuilder2.toString() });
  }
  
  private void a(MessageResult paramMessageResult) {
    int i = paramMessageResult.getType();
    if (i != 3) {
      String str2;
      switch (i) {
        default:
          return;
        case 16:
          str2 = paramMessageResult.getData();
          PortalConn.queryTicket(this.cordova, this.webView, InnerState.load(), this, Boolean.valueOf(str2).booleanValue());
          return;
        case 15:
          str2 = str2.getData();
          PortalConn.needVerifyCodeCallBackToJs(this.cordova, this.webView, Boolean.valueOf(str2).booleanValue());
          return;
        case 14:
          this.l.postDelayed(new PortalMananger$$Lambda$8(this), 1000L);
        case 13:
          this.l.removeMessages(6);
          try {
            ReturnUIResult returnUIResult = new ReturnUIResult();
            JSONObject jSONObject = new JSONObject(str2.getData());
            returnUIResult.setErrorCode(jSONObject.optString("errorCode", "0"));
            returnUIResult.setSubErrorCode(jSONObject.optString("subErrorCode", ""));
            returnUIResult.setExtern(jSONObject.optString("extern", ""));
            PortalConn.handleAuthFail(this.cordova, this.webView, InnerState.load(), returnUIResult);
            return;
          } catch (Exception exception) {
            exception.printStackTrace();
            return;
          } 
        case 11:
          str1 = exception.getData();
          PortalConn.handleAuthSuccess(this, this.cordova, this.webView, str1, InnerState.load(), false);
        case 12:
          this.l.removeMessages(6);
          this.l.sendEmptyMessageDelayed(6, 60000L);
          return;
        case 10:
          break;
      } 
      String str1 = str1.getData();
      PortalConn.onAuthProgressCallBackToJs(this.cordova, this.webView, Integer.parseInt(str1));
      return;
    } 
    try {
      Context context = this.cordova.getContext();
      String str3 = PreferencesUtils.getString(context, "TempLoginAccount", "");
      String str2 = PreferencesUtils.getString(context, "TempLoginPassword", "");
      String str1 = str2;
      if (!TextUtils.isEmpty(str2))
        str1 = CdcUtils.decrypString(str2); 
      str2 = PreferencesUtils.getString(context, "TempLoginVertifyCode", "");
      PreferencesUtils.putString(this.cordova.getContext(), "TempLoginAccount", "");
      PreferencesUtils.putString(this.cordova.getContext(), "TempLoginPassword", "");
      PreferencesUtils.putString(this.cordova.getContext(), "TempLoginVertifyCode", "");
      String str4 = PreferencesUtils.getString(context, "account", "");
      JSONObject jSONObject = new JSONObject();
      jSONObject.put("oldAccount", str4);
      jSONObject.put("account", str3);
      jSONObject.put("password", str1);
      jSONObject.put("vertifyCode", str2);
      MessageResult messageResult = new MessageResult();
      messageResult.setType(1);
      messageResult.setData(jSONObject.toString());
      AppReceiver.sendServiceReceiver(this.cordova.getContext(), messageResult);
      return;
    } catch (Exception exception) {
      exception.printStackTrace();
      return;
    } 
  }
  
  private void a(PortalMananger paramPortalMananger, CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView, String paramString, int paramInt) {
    this.l.postDelayed(new Runnable(this, paramPortalMananger, paramCordovaInterface, paramCordovaWebView, paramString) {
          public void run() {
            InnerState innerState = InnerState.load();
            PortalConn.onTerm(this.a, this.b, this.c, this.d, innerState);
          }
        }paramInt);
  }
  
  private void a(String paramString1, String paramString2, String paramString3) {
    SharedPreferences sharedPreferences = this.d.getSharedPreferences(paramString1, 0);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    String str = sharedPreferences.getString(paramString3, "");
    if (!TextUtils.isEmpty(str)) {
      str = CdcUtils.encrypString(str);
      if (PreferencesUtils.putString(this.d, "DialPassword", str))
        editor.putString(paramString3, ""); 
    } 
    paramString3 = sharedPreferences.getString(paramString2, "");
    if (!TextUtils.isEmpty(paramString3)) {
      PreferencesUtils.putString(this.d, "account", paramString3);
      if (PreferencesUtils.putString(this.d, "DialAccount", paramString3))
        editor.putString(paramString2, ""); 
    } 
    editor.commit();
  }
  
  private void a(CordovaArgs paramCordovaArgs, PortalMananger paramPortalMananger, CallbackContext paramCallbackContext) {
    String str;
    InnerState innerState = InnerState.load();
    if (paramCordovaArgs != null && !paramCordovaArgs.isNull(0))
      try {
        JSONArray jSONArray = paramCordovaArgs.getJSONArray(0);
        String str1 = jSONArray.getString(0);
        str = jSONArray.getString(1);
        PortalConn.onTermOther(this.cordova, this.webView, paramCallbackContext, str1, str, innerState);
        return;
      } catch (JSONException jSONException) {
        jSONException.printStackTrace();
        return;
      }  
    innerState.setState(4).save();
    stopService("1005");
    a((PortalMananger)str, this.cordova, this.webView, "1", 1500);
  }
  
  private void a(boolean paramBoolean) {
    if (this.c != null && this.c.isShowing())
      this.c.dismiss(); 
    if (paramBoolean && this.D && !this.E)
      b(1000); 
  }
  
  private void a(boolean paramBoolean, int paramInt) {
    this.l.post(new Runnable(this, paramInt, paramBoolean) {
          public void run() {
            boolean bool;
            FromWhereCode.printFromWhereDes(this.a, "onCheckNet");
            if (this.a == 0)
              PortalConn.onCheckNetProgressCallBackToJs(this.c.cordova, this.c.webView, "200108", 40); 
            NetInfo netInfo = new NetInfo(PortalMananger.i(this.c));
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("checknet wifi info : ");
            stringBuilder.append(PortalConn.getNetWorkJsonInfo(netInfo));
            ExLog.i(new String[] { stringBuilder.toString() });
            if (TextUtils.isEmpty(netInfo.getIpv4()) && TextUtils.isEmpty(netInfo.getIpv6())) {
              ExLog.i(new String[] { "ip4/ip6地址没分配" });
              PortalConn.netWorkCheckFailCallBackToJs(this.c.cordova, this.c.webView, UiErrCode.makePortalErrorCode("10"), netInfo);
              return;
            } 
            InnerState innerState = InnerState.obtain();
            if (PortalMananger.m(this.c) != null && PortalMananger.m(this.c).equals(netInfo)) {
              bool = true;
            } else {
              bool = false;
            } 
            if (this.a == 0)
              PortalConn.onCheckNetProgressCallBackToJs(this.c.cordova, this.c.webView, "200109", 60); 
            if (this.b && netInfo.isLowRSSI() && !bool) {
              PortalMananger.a(this.c, netInfo);
              PortalConn.netWorkWifiWeakCallBackToJs(this.c.cordova, this.c.webView, netInfo);
              return;
            } 
            if (!bool)
              PortalMananger.a(this.c, (NetInfo)null); 
            if (Build.VERSION.SDK_INT >= 23) {
              ArrayList<String> arrayList = new ArrayList();
              if (PortalMananger.a(this.c, PortalMananger.d(this.c), "android.permission.ACCESS_COARSE_LOCATION"))
                arrayList.add("android.permission.ACCESS_COARSE_LOCATION"); 
              if (arrayList.size() > 0) {
                PortalMananger.n(this.c);
                return;
              } 
              if (CdcUtils.checkPermission("android.permission.ACCESS_COARSE_LOCATION", PortalMananger.d(this.c)) && Build.VERSION.SDK_INT >= 28 && PreferencesUtils.getBoolean(PortalMananger.d(this.c), "LOCATION_SERVICE", true) && !CdcUtils.isLocationServiceEnable(PortalMananger.d(this.c))) {
                PreferencesUtils.putBoolean(PortalMananger.d(this.c), "LOCATION_SERVICE", false);
                PortalMananger.o(this.c);
                return;
              } 
            } 
            PortalConn.onCheckNet(this.c.cordova, this.c.webView, innerState, netInfo, this.a, this.c);
          }
        });
  }
  
  private void a(String[] paramArrayOfString, Activity paramActivity, Context paramContext) {
    String str = "";
    int i;
    for (i = 0; i < paramArrayOfString.length; i++) {
      String str1 = paramArrayOfString[i];
      byte b = -1;
      if (str1.hashCode() == 463403621 && str1.equals("android.permission.CAMERA"))
        b = 0; 
      if (b == 0) {
        String str2;
        str1 = str;
        if (!TextUtils.isEmpty(str)) {
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append(str);
          stringBuilder1.append("、");
          str2 = stringBuilder1.toString();
        } 
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str2);
        stringBuilder.append(paramContext.getResources().getString(2131492939));
        str = stringBuilder.toString();
      } 
    } 
    if (!TextUtils.isEmpty(str)) {
      String str1 = paramContext.getResources().getString(2131492996).replace("%s%s%s", str).replace("%s%s", paramContext.getResources().getString(2131492904));
      if (this.C != null && this.C.isShowing()) {
        this.C.setMessage(str1);
        return;
      } 
      this.C = (new AlertDialog.Builder((Context)paramActivity)).setMessage(str1).setPositiveButton(2131492951, new DialogInterface.OnClickListener(this) {
            public void onClick(DialogInterface param1DialogInterface, int param1Int) {
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append("package:");
              stringBuilder.append(this.a.cordova.getActivity().getPackageName());
              Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse(stringBuilder.toString()));
              this.a.cordova.getActivity().startActivity(intent);
            }
          }).setNegativeButton(2131492960, new DialogInterface.OnClickListener(this) {
            public void onClick(DialogInterface param1DialogInterface, int param1Int) {}
          }).create();
      this.C.setCanceledOnTouchOutside(false);
      this.C.setCancelable(false);
      this.C.show();
    } 
  }
  
  private boolean a(Context paramContext, String paramString) {
    String str = paramString.substring(paramString.lastIndexOf("."), paramString.length());
    boolean bool = true;
    boolean bool1 = PreferencesUtils.getBoolean(paramContext, str, true);
    boolean bool2 = CdcUtils.checkPermission(paramString, paramContext);
    if (!bool1 || bool2)
      bool = false; 
    if (bool)
      PreferencesUtils.putBoolean(paramContext, str, false); 
    return bool;
  }
  
  private void b(int paramInt) {
    this.l.postDelayed(new PortalMananger$$Lambda$5(this), paramInt);
  }
  
  private void b(Context paramContext) {
    File file = CdcUtils.openFileDir(paramContext, ExLog.LOG_DIR);
    if (file != null) {
      File[] arrayOfFile = file.listFiles();
      for (int i = 0; i < arrayOfFile.length; i++) {
        File file3 = arrayOfFile[i];
        if (file3.isFile() && file3.getName().endsWith(".zip"))
          file3.getAbsoluteFile().delete(); 
      } 
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(file.getPath());
      stringBuilder1.append(File.separator);
      String str1 = stringBuilder1.toString();
      stringBuilder1 = new StringBuilder();
      stringBuilder1.append("CDCLogs_");
      stringBuilder1.append((new Date()).getTime());
      stringBuilder1.append(".zip");
      String str2 = stringBuilder1.toString();
      StringBuilder stringBuilder2 = new StringBuilder();
      stringBuilder2.append(str1);
      stringBuilder2.append(str2);
      str2 = stringBuilder2.toString();
      File file2 = new File(str2);
      if (file2.exists())
        file2.getAbsoluteFile().delete(); 
      if (!FileUtils.toZip(str1, str2)) {
        ExLog.e(new String[] { "share files to zip is error" });
        return;
      } 
      File file1 = new File(str2);
      if (!file1.exists())
        this.n.post(new Runnable(this) {
              public void run() {
                ToastUtils.showBottomText(PortalMananger.d(this.a), "文件不存在，日志分享失败!");
              }
            }); 
      try {
        Uri uri = FileProvider.getUriForFile(paramContext, paramContext.getPackageName(), file1);
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setFlags(268435456);
        intent.addFlags(3);
        intent.putExtra("android.intent.extra.STREAM", (Parcelable)uri);
        intent.setType("*/*");
        this.cordova.getActivity().startActivity(Intent.createChooser(intent, "日志分享"));
        return;
      } catch (Exception exception) {
        exception.printStackTrace();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("shareLog error:");
        stringBuilder.append(exception.getMessage());
        ExLog.e(new String[] { stringBuilder.toString() });
        this.n.post(new Runnable(this) {
              public void run() {
                ToastUtils.showBottomText(PortalMananger.d(this.a), "日志分享失败!");
              }
            });
      } 
    } 
  }
  
  private void b(Context paramContext, String paramString) {
    if (this.b != null && this.b.isShowing())
      this.b.dismiss(); 
    this.a.setDownloadListener(new DownloadApkManager.DownloadListener(this, paramContext, paramString) {
          public void taskDownloading(DownloadTask param1DownloadTask, int param1Int) {
            PortalMananger.c(this.c).post(new Runnable(this, param1Int, param1DownloadTask) {
                  public void run() {
                    if (PortalMananger.q(this.c.c) != null && !PortalMananger.q(this.c.c).isShowing())
                      PortalMananger.q(this.c.c).show(); 
                    PortalMananger.q(this.c.c).setProgress((int)(this.a / (float)this.b.getTaskFileSize() * 100.0F));
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("apk下载中:");
                    stringBuilder.append(PortalMananger.q(this.c.c).getProgress());
                    stringBuilder.append("%");
                    ExLog.i(new String[] { stringBuilder.toString() });
                  }
                });
          }
          
          public void taskError(DownloadTask param1DownloadTask, String param1String) {
            PortalMananger.c(this.c).post(new Runnable(this, param1String) {
                  public void run() {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("apk下载失败:");
                    stringBuilder.append(this.a);
                    ExLog.e(new String[] { stringBuilder.toString() });
                    ToastUtils.showBottomText(this.b.a, "下载失败");
                  }
                });
          }
          
          public void taskFinish(DownloadTask param1DownloadTask, int param1Int) {
            PortalMananger.c(this.c).post(new Runnable(this, param1DownloadTask) {
                  public void run() {
                    if (PortalMananger.q(this.b.c) != null && PortalMananger.q(this.b.c).isShowing()) {
                      PortalMananger.q(this.b.c).setProgress(0);
                      PortalMananger.q(this.b.c).dismiss();
                    } 
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("apk下载完成:");
                    stringBuilder.append(this.a.getTaskPath());
                    ExLog.i(new String[] { stringBuilder.toString() });
                    try {
                      Uri uri = FileProvider.getUriForFile(this.b.a, this.b.a.getPackageName(), new File(this.a.getTaskPath()));
                      Intent intent = new Intent("android.intent.action.VIEW");
                      intent.setFlags(268435456);
                      intent.addFlags(3);
                      intent.setDataAndType(uri, "application/vnd.android.package-archive");
                      this.b.a.startActivity(intent);
                      return;
                    } catch (Exception exception) {
                      ToastUtils.showBottomText(this.b.a, "安装失败");
                      Intent intent = new Intent();
                      intent.setAction("android.intent.action.VIEW");
                      intent.setData(Uri.parse(this.b.b));
                      this.b.c.cordova.getActivity().startActivity(intent.addFlags(268435456));
                      return;
                    } 
                  }
                });
          }
          
          public void taskWaiting(DownloadTask param1DownloadTask) {
            PortalMananger.c(this.c).post(new Runnable(this) {
                  public void run() {
                    if (PortalMananger.q(this.a.c) != null && !PortalMananger.q(this.a.c).isShowing()) {
                      PortalMananger.q(this.a.c).setProgress(0);
                      PortalMananger.q(this.a.c).show();
                    } 
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("apk开始下载:");
                    stringBuilder.append(PortalMananger.q(this.a.c).getProgress());
                    stringBuilder.append("%");
                    ExLog.i(new String[] { stringBuilder.toString() });
                  }
                });
          }
        });
    this.a.addApkTask(paramString);
  }
  
  private void b(Message paramMessage) {
    int i = paramMessage.what;
    if (i != 0) {
      if (i != 4) {
        String str1;
        StringBuilder stringBuilder1;
        boolean bool;
        Context context;
        String str2;
        StringBuilder stringBuilder2;
        switch (i) {
          default:
            switch (i) {
              default:
                return;
              case 1005:
                PortalConn.runCheckNetTool(this.cordova, this.webView, (Handler)this.l, 5);
                return;
              case 1004:
                PortalConn.runCheckNetTool(this.cordova, this.webView, (Handler)this.l, 4);
                return;
              case 1003:
                PortalConn.runCheckNetTool(this.cordova, this.webView, (Handler)this.l, 3);
                return;
              case 1002:
                PortalConn.runCheckNetTool(this.cordova, this.webView, (Handler)this.l, 2);
                return;
              case 1001:
                break;
            } 
            PortalConn.runCheckNetTool(this.cordova, this.webView, (Handler)this.l, 1);
            return;
          case 9:
            str1 = FileUtils.getFileMD5(this.cordova.getContext().getPackageCodePath());
            context = this.cordova.getContext();
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(AppInfoUtils.getVersionCode(this.cordova.getContext()));
            stringBuilder2.append("_");
            stringBuilder2.append("ApkMD5Key");
            str2 = PreferencesUtils.getString(context, stringBuilder2.toString());
            if (!TextUtils.isEmpty(str2) && !str1.equals(str2.toLowerCase())) {
              this.n.sendEmptyMessage(9);
              return;
            } 
            return;
          case 8:
            bool = CdcUtils.isWifiConnected(this.d);
            stringBuilder1 = new StringBuilder();
            stringBuilder1.append("onNetworkChange -> isConnected: ");
            stringBuilder1.append(bool);
            ExLog.i(new String[] { stringBuilder1.toString() });
            ExLog.i(new String[] { "onNetworkChange -> start" });
            removeCheckState();
            d(bool);
            return;
          case 7:
            if (!InnerState.load().isOnline()) {
              removeCheckState();
              onTermSuccessAndCheckNet();
              return;
            } 
            return;
          case 6:
            break;
        } 
        ExLog.d(new String[] { "check Hold(Vpn)Service is alive" });
        if (InnerState.load().isOnline()) {
          if (t()) {
            if (!ServiceUtils.isServiceRunning(this.cordova.getContext(), HoldVpnService.class.getName())) {
              ExLog.i(new String[] { "HoldVpnService is killed by phone" });
              this.l.post(new PortalMananger$$Lambda$0(this));
              return;
            } 
          } else if (!ServiceUtils.isServiceRunning(this.cordova.getContext(), HoldService.class.getName())) {
            ExLog.i(new String[] { "HoldService is killed" });
            this.l.post(new PortalMananger$$Lambda$1(this));
            return;
          } 
          this.l.sendEmptyMessageDelayed(6, 60000L);
          return;
        } 
      } else {
        m();
        return;
      } 
    } else {
      this.n.sendEmptyMessage(1);
    } 
  }
  
  private void b(InnerState paramInnerState) {
    CollectionHelper.cutCollectionInfo("appinfo", "uploadinfo");
    String str = PreferencesUtils.getString(this.cordova.getContext(), "account", "");
    CollectionHelper.addCollectionInfo("appinfo", CollectionInfo.madeDefCollectionInfo(this.cordova.getContext(), this.J, str, paramInnerState));
    CollectionHelper collectionHelper = CollectionHelper.getInstance();
    collectionHelper.setCallBack(new CollectionHelper.CallBack(this) {
          public void collection() {
            PortalMananger.a(this.a);
          }
        });
    collectionHelper.start();
  }
  
  private void b(String paramString1, String paramString2, String paramString3) {
    PreferencesUtils.putString(this.cordova.getContext(), "TempLoginAccount", paramString1);
    PreferencesUtils.putString(this.cordova.getContext(), "TempLoginPassword", CdcUtils.encrypString(paramString2));
    PreferencesUtils.putString(this.cordova.getContext(), "TempLoginVertifyCode", paramString3);
    if (t()) {
      c(false);
      return;
    } 
    HoldService.start(this.cordova.getContext(), false, this.K);
  }
  
  private void b(CordovaArgs paramCordovaArgs) {
    this.cordova.getThreadPool().execute(new PortalMananger$$Lambda$7(paramCordovaArgs));
  }
  
  private void b(boolean paramBoolean) {
    if (t()) {
      Intent intent = HoldVpnService.prepare(this.cordova.getContext());
      if (intent != null) {
        if (paramBoolean) {
          this.cordova.startActivityForResult(this, intent, 4);
          return;
        } 
        this.cordova.startActivityForResult(this, intent, 3);
        return;
      } 
      ToastUtils.showBottomText(this.cordova.getContext(), this.cordova.getContext().getResources().getString(2131492952));
      return;
    } 
    ToastUtils.showBottomText(this.cordova.getContext(), this.cordova.getContext().getResources().getString(2131493008));
  }
  
  private void c(InnerState paramInnerState) {
    Context context = StubApp.getOrigApplicationContext(this.cordova.getActivity().getApplicationContext());
    SharedPreferences.Editor editor = PreferencesUtils.getEditor(context);
    new DisplayMetrics();
    DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
    int i = displayMetrics.widthPixels;
    int j = displayMetrics.heightPixels;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(i);
    stringBuilder.append("");
    editor.putString("deviceWidth", stringBuilder.toString());
    stringBuilder = new StringBuilder();
    stringBuilder.append(j);
    stringBuilder.append("");
    editor.putString("deviceHeight", stringBuilder.toString());
    editor.putString("ostype", "2");
    editor.putString("dialuptype", "51");
    editor.putString("ccmpid", "cpb-factory-app-cz");
    editor.putString("recharge-num", "A-%s-%s@%s-100");
    stringBuilder = new StringBuilder();
    stringBuilder.append(AppInfoUtils.getVersionCode(context));
    stringBuilder.append("");
    editor.putString("version", stringBuilder.toString());
    editor.putString("ip", CdcUtils.getIP());
    editor.putString("clientId", paramInnerState.getClientId());
    editor.putString("userAgent", CdcUtils.encrypString(paramInnerState.getUserAgent(false)));
    editor.putString("OSVersion", Build.VERSION.RELEASE);
    editor.commit();
  }
  
  private void c(CordovaArgs paramCordovaArgs) {
    InnerState innerState = InnerState.load();
    PreferencesUtils.putString(this.cordova.getContext(), "loginSystemTimeString", "0");
    innerState.setState(2).save();
    try {
      JSONArray jSONArray = paramCordovaArgs.getJSONArray(0);
      String str2 = jSONArray.getString(0);
      String str3 = jSONArray.getString(1);
      String str1 = "";
      if (jSONArray.length() == 3)
        str1 = jSONArray.getString(2); 
      this.l.post(new Runnable(this, str2, str3, str1) {
            public void run() {
              PortalMananger.a(this.d, this.a, this.b, this.c);
            }
          });
      return;
    } catch (JSONException jSONException) {
      jSONException.printStackTrace();
      return;
    } 
  }
  
  private void c(boolean paramBoolean) {
    PreferencesUtils.putBoolean(this.cordova.getContext(), "vpnSelfConnect", paramBoolean);
    Intent intent = HoldVpnService.prepare(this.cordova.getContext());
    if (intent != null) {
      this.cordova.startActivityForResult(this, intent, 1);
      return;
    } 
    onActivityResult(1, -1, (Intent)null);
  }
  
  private void d(CordovaArgs paramCordovaArgs) {
    try {
      if (paramCordovaArgs.get(0) instanceof JSONObject) {
        JSONObject jSONObject = paramCordovaArgs.getJSONObject(0);
        if (jSONObject.has("hasWiFiTips") && jSONObject.getString("hasWiFiTips").equals("0")) {
          if (CdcUtils.isWifiConnected(null)) {
            a(false, 1);
            return;
          } 
          PortalConn.noNetWorkCallBackToJs(this.cordova, this.webView, 1);
          return;
        } 
      } 
      d(CdcUtils.isWifiConnected(null));
      return;
    } catch (JSONException jSONException) {
      jSONException.printStackTrace();
      return;
    } 
  }
  
  private void d(CordovaArgs paramCordovaArgs, CallbackContext paramCallbackContext) {
    try {
      JSONArray jSONArray = paramCordovaArgs.getJSONArray(0);
      String str1 = jSONArray.getString(0);
      String str2 = jSONArray.getString(1);
      if (TextUtils.isEmpty(str1)) {
        this.n.post(new Runnable(this) {
              public void run() {
                Toast.makeText(this.a.cordova.getContext(), "账号不能为空", 0).show();
              }
            });
        return;
      } 
      if (TextUtils.isEmpty(str2)) {
        this.n.post(new Runnable(this) {
              public void run() {
                Toast.makeText(this.a.cordova.getContext(), "密码不能为空", 0).show();
              }
            });
        return;
      } 
      if (Build.VERSION.SDK_INT >= 23 && !CdcUtils.checkPermission("android.permission.CAMERA", this.d)) {
        this.L = new Runnable(this, str1, str2) {
            public void run() {
              Intent intent = new Intent((Context)this.c.cordova.getActivity(), CaptureActivity.class);
              Bundle bundle = new Bundle();
              bundle.putString("account", this.a);
              bundle.putString("password", this.b);
              intent.putExtras(bundle);
              intent.setFlags(268435456);
              this.c.cordova.getActivity().startActivity(intent);
            }
          };
        PermissionHelper.requestPermission(this, 1, "android.permission.CAMERA");
        return;
      } 
      this.L = null;
      Intent intent = new Intent((Context)this.cordova.getActivity(), CaptureActivity.class);
      Bundle bundle = new Bundle();
      bundle.putString("account", str1);
      bundle.putString("password", str2);
      intent.putExtras(bundle);
      intent.setFlags(268435456);
      this.cordova.getActivity().startActivity(intent);
      return;
    } catch (Exception exception) {
      exception.printStackTrace();
      return;
    } 
  }
  
  private void d(boolean paramBoolean) {
    if (paramBoolean) {
      InnerState innerState = InnerState.load();
      NetInfo netInfo = new NetInfo(A());
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("checknet cur wifi info : ");
      stringBuilder.append(PortalConn.getNetWorkJsonInfo(netInfo));
      ExLog.i(new String[] { stringBuilder.toString() });
      if (TextUtils.isEmpty(netInfo.getIpv4()) && TextUtils.isEmpty(netInfo.getIpv6())) {
        PortalConn.noNetWorkCallBackToJs(this.cordova, this.webView, 0);
        return;
      } 
      ExLog.i(new String[] { "checknet start ->" });
      PortalConn.onCheckNetProgressCallBackToJs(this.cordova, this.webView, "200106", 0);
      PortalConn.onCheckNetProgressCallBackToJs(this.cordova, this.webView, "200107", 20);
      if (innerState.getState() == 2) {
        a(false, 0);
        return;
      } 
      a(true, 0);
      return;
    } 
    PortalConn.noNetWorkCallBackToJs(this.cordova, this.webView, 0);
  }
  
  private void e() {
    Context context = StubApp.getOrigApplicationContext(this.cordova.getActivity().getApplicationContext());
    String str2 = PreferencesUtils.getString(context, "oldUserAgent", "");
    String str1 = str2;
    if (TextUtils.isEmpty(str2))
      str1 = PreferencesUtils.getString(context, "userAgent", ""); 
    PreferencesUtils.putString(context, "oldUserAgent", str1);
  }
  
  private void e(CordovaArgs paramCordovaArgs) {
    this.cordova.getThreadPool().execute(new Runnable(this) {
          public void run() {
            PortalConn.onSetPhoneCSSStyle(this.a.cordova, this.a.webView);
          }
        });
  }
  
  private void e(CordovaArgs paramCordovaArgs, CallbackContext paramCallbackContext) {
    this.cordova.getThreadPool().execute(new PortalMananger$$Lambda$2(paramCordovaArgs, paramCallbackContext));
  }
  
  private void f() {
    InnerState innerState = InnerState.load();
    String str = PreferencesUtils.getString(this.cordova.getContext(), "account", "");
    CollectionHelper.updateCollectionInfo("appinfo", this.J, str, innerState);
  }
  
  private void f(CordovaArgs paramCordovaArgs) {
    try {
      Intent intent;
      String str = paramCordovaArgs.getString(0);
      if (str.equals("Wi-Fi")) {
        x();
        return;
      } 
      if (str.startsWith("http") || str.startsWith("mqqwpa")) {
        Intent intent1 = new Intent();
        intent1.setAction("android.intent.action.VIEW");
        intent1.setData(Uri.parse(str));
        this.cordova.getActivity().startActivity(intent1.addFlags(268435456));
        return;
      } 
      if (str.equals("Home-Page")) {
        intent = new Intent("android.intent.action.MAIN");
        intent.setFlags(268435456);
        intent.addCategory("android.intent.category.HOME");
        this.cordova.getActivity().startActivity(intent);
        return;
      } 
      if (intent.contains("uninstall")) {
        JSONArray jSONArray = new JSONArray((String)intent);
        if (jSONArray.length() == 2) {
          String str1 = jSONArray.getString(1);
          if (!TextUtils.isEmpty(str1)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("package:");
            stringBuilder.append(str1);
            intent = new Intent("android.intent.action.DELETE", Uri.parse(stringBuilder.toString()));
            this.cordova.getActivity().startActivity(intent);
            return;
          } 
        } 
      } else {
        Intent intent1;
        if (intent.equals("GOTO-GPS-PAGE")) {
          intent = new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
          this.cordova.getActivity().startActivity(intent);
          return;
        } 
        if (intent.equals("Online-Money")) {
          intent = new Intent((Context)this.cordova.getActivity(), OnlineMoneyActivity.class);
          this.cordova.getActivity().startActivity(intent);
          return;
        } 
        if (intent.equals("Share-Log")) {
          b(StubApp.getOrigApplicationContext(this.cordova.getActivity().getApplicationContext()));
          return;
        } 
        if (intent.equals("Permission-Page")) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("package:");
          stringBuilder.append(this.cordova.getActivity().getPackageName());
          intent1 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse(stringBuilder.toString()));
          this.cordova.getActivity().startActivity(intent1);
          return;
        } 
        if (intent1.equals("Vpnservice-Page")) {
          this.n.post(new Runnable(this) {
                public void run() {
                  PortalMananger.b(this.a, false);
                }
              });
          return;
        } 
        if (intent1.equals("Vpnservice-Page-ShowToast")) {
          this.n.post(new Runnable(this) {
                public void run() {
                  PortalMananger.b(this.a, true);
                }
              });
          return;
        } 
        if (intent1.equals("Campus-Info")) {
          w();
          return;
        } 
      } 
    } catch (JSONException jSONException) {
      jSONException.printStackTrace();
    } 
  }
  
  private void f(CordovaArgs paramCordovaArgs, CallbackContext paramCallbackContext) {
    this.cordova.getThreadPool().execute(new PortalMananger$$Lambda$3(this, paramCordovaArgs, paramCallbackContext));
  }
  
  private void g() {
    Context context3 = this.d;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("CDCLogs");
    stringBuilder.append(File.separator);
    stringBuilder.append("APK");
    File file3 = CdcUtils.openFileDir(context3, stringBuilder.toString());
    if (file3 != null && file3.exists())
      FileUtils.delFile(file3); 
    Context context2 = this.d;
    stringBuilder = new StringBuilder();
    stringBuilder.append("CDCLogs");
    stringBuilder.append(File.separator);
    stringBuilder.append("AD");
    File file2 = CdcUtils.openFileDir(context2, stringBuilder.toString());
    if (file2 != null && file2.exists())
      FileUtils.delFile(file2, 3); 
    Context context1 = this.d;
    stringBuilder = new StringBuilder();
    stringBuilder.append("CDCLogs");
    stringBuilder.append(File.separator);
    stringBuilder.append("IMAGE");
    File file1 = CdcUtils.openFileDir(context1, stringBuilder.toString());
    if (file1 != null && file1.exists())
      FileUtils.delFile(file1, 3); 
  }
  
  private void g(CordovaArgs paramCordovaArgs) {
    try {
      String str = paramCordovaArgs.getString(0);
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("tel:");
      stringBuilder.append(str);
      Intent intent = new Intent("android.intent.action.DIAL", Uri.parse(stringBuilder.toString()));
      intent.setFlags(268435456);
      this.cordova.getActivity().startActivity(intent.addFlags(268435456));
      return;
    } catch (JSONException jSONException) {
      jSONException.printStackTrace();
      return;
    } 
  }
  
  private void g(CordovaArgs paramCordovaArgs, CallbackContext paramCallbackContext) {
    this.cordova.getThreadPool().execute(new PortalMananger$$Lambda$4(this, paramCallbackContext));
  }
  
  private void h() {
    if ("hn".equals("gd")) {
      a("CDCLogs", "UID", "PID");
      return;
    } 
    if ("hn".equals("hn"))
      a("TrineaAndroidCommon", "UID", "PID"); 
  }
  
  private void h(CordovaArgs paramCordovaArgs, CallbackContext paramCallbackContext) {
    this.cordova.getThreadPool().execute(new PortalMananger$$Lambda$6(this, paramCordovaArgs, paramCallbackContext));
  }
  
  private void i() {
    if (Build.VERSION.SDK_INT >= 24) {
      ((ConnectivityManager)this.cordova.getActivity().getSystemService("connectivity")).requestNetwork((new NetworkRequest.Builder()).build(), new ConnectivityManager.NetworkCallback(this) {
            public void onAvailable(Network param1Network) {
              super.onAvailable(param1Network);
              if (!this.a.isInitFinish())
                return; 
              ExLog.i(new String[] { "receive onAvailable network" });
              this.a.onNetworkChange();
            }
            
            public void onLost(Network param1Network) {
              super.onLost(param1Network);
              if (!this.a.isInitFinish())
                return; 
              ExLog.i(new String[] { "receive onLost network" });
              this.a.onNetworkChange();
            }
          });
    } else {
      this.F = new NetWorkReceiver(this);
      IntentFilter intentFilter = new IntentFilter();
      intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
      intentFilter.addAction("android.net.wifi.STATE_CHANGE");
      intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
      this.cordova.getContext().registerReceiver((BroadcastReceiver)this.F, intentFilter, "com.cndatacom.campus.permissions.PORTAL_RECEIVER", null);
    } 
    this.G = new AppReceiver();
    this.G.setReceiverListener(new AppReceiver.AppReceiverListener(this) {
          public void onReceive(Context param1Context, Intent param1Intent, int param1Int) {
            String str = param1Intent.getAction();
            switch (str.hashCode()) {
              default:
                param1Int = -1;
                break;
              case 1105288245:
                if (str.equals("com.cndatacom.campus.receiver.action")) {
                  param1Int = 0;
                  break;
                } 
              case 622836004:
                if (str.equals("com.cndatacom.campus.netcore.keepservice.CHANGED")) {
                  param1Int = 1;
                  break;
                } 
              case -261436938:
                if (str.equals("com.cndatacom.campus.netcore.qrcode.action.autherror")) {
                  param1Int = 4;
                  break;
                } 
              case -1540798285:
                if (str.equals("com.cndatacom.campus.netcore.holdservice.action.shared")) {
                  param1Int = 3;
                  break;
                } 
              case -1676921961:
                if (str.equals("com.cndatacom.campus.netcore.holdservice.action.notify")) {
                  param1Int = 2;
                  break;
                } 
            } 
            switch (param1Int) {
              default:
                return;
              case 4:
                this.a.onQRCodeError((ReturnUIResult)param1Intent.getParcelableExtra("error"));
                return;
              case 3:
                this.a.onSharedChange(param1Intent.getParcelableExtra("error"));
                return;
              case 2:
                this.a.onReverseNotifyChange((ReturnUIResult)param1Intent.getParcelableExtra("error"));
                return;
              case 1:
                this.a.onKeepChange((ReturnUIResult)param1Intent.getParcelableExtra("error"));
                return;
              case 0:
                break;
            } 
            PortalMananger.b(this.a).post(new PortalMananger$3$$Lambda$0(this, param1Intent));
          }
        });
    this.G.registerReceiver(this.cordova.getContext());
  }
  
  private void i(CordovaArgs paramCordovaArgs, CallbackContext paramCallbackContext) {
    boolean bool = false;
    try {
      Object object = paramCordovaArgs.get(0);
      if (object instanceof JSONArray) {
        JSONArray jSONArray = (JSONArray)object;
        String str = jSONArray.getString(0);
        object = str;
        if (jSONArray.length() == 2) {
          bool = jSONArray.getBoolean(1);
          object = str;
        } 
      } else {
        object = object.toString();
      } 
      PortalConn.getCacheData(paramCallbackContext, this.cordova, this.webView, (String)object, bool);
      return;
    } catch (JSONException jSONException) {
      jSONException.printStackTrace();
      return;
    } 
  }
  
  private void j() {
    this.a = new DownloadApkManager(this.d);
    this.b = new ProgressDialog((Context)this.cordova.getActivity());
    this.b.setCanceledOnTouchOutside(false);
    this.b.setCancelable(false);
    this.b.setMax(100);
    this.b.setProgressStyle(1);
    this.b.setTitle("正在下载");
    this.n = new WeakRefHandler(Looper.getMainLooper(), this, new Handler.Callback(this) {
          public boolean handleMessage(Message param1Message) {
            PortalMananger.a(this.a, param1Message);
            return true;
          }
        });
    this.m = new HandlerThread("loadPortalManangerData", 0);
    this.m.start();
    this.l = new WeakRefHandler(this.m.getLooper(), this, new Handler.Callback(this) {
          public boolean handleMessage(Message param1Message) {
            PortalMananger.b(this.a, param1Message);
            return true;
          }
        });
  }
  
  private void j(CordovaArgs paramCordovaArgs, CallbackContext paramCallbackContext) {
    boolean bool = false;
    try {
      JSONArray jSONArray = paramCordovaArgs.getJSONArray(0);
      Object object = jSONArray.get(0);
      if (object instanceof JSONArray) {
        JSONArray jSONArray1 = (JSONArray)object;
        object = jSONArray1.getString(0);
        if (jSONArray1.length() == 2)
          bool = jSONArray1.getBoolean(1); 
      } else {
        object = object.toString();
        bool = false;
      } 
      String str = jSONArray.getString(1);
      PortalConn.setCacheData(paramCallbackContext, this.cordova, this.webView, (String)object, str, bool);
      return;
    } catch (JSONException jSONException) {
      jSONException.printStackTrace();
      return;
    } 
  }
  
  private void k() {
    if (this.n != null)
      this.n.removeCallbacksAndMessages(null); 
    if (this.l != null)
      this.l.removeCallbacksAndMessages(null); 
    if (this.m != null)
      this.m.quit(); 
  }
  
  private void k(CordovaArgs paramCordovaArgs, CallbackContext paramCallbackContext) {
    try {
      String str = paramCordovaArgs.getJSONArray(0).getString(0);
      this.l.post(new Runnable(this, paramCallbackContext, str) {
            public void run() {
              InnerState innerState = InnerState.load();
              PortalConn.onGetVerificatioCode(this.c.cordova, this.a, this.c.webView, this.b, innerState);
            }
          });
      return;
    } catch (JSONException jSONException) {
      jSONException.printStackTrace();
      return;
    } 
  }
  
  private void l() {
    if (this.cordova.getActivity().isFinishing())
      return; 
    if (this.c != null && this.c.isShowing())
      return; 
    n().setVisibility(4);
    this.c = new Dialog((Context)this.cordova.getActivity(), 2131558562);
    if (((this.cordova.getActivity().getWindow().getAttributes()).flags & 0x400) == 1024) {
      this.c.getWindow().setFlags(1024, 1024);
    } else if (Build.VERSION.SDK_INT >= 21) {
      this.c.getWindow().setFlags(67108864, 67108864);
    } 
    View view = LayoutInflater.from(this.d).inflate(2131296295, null, false);
    this.h = (RRectangleLinearLayout)view.findViewById(2131165211);
    this.h.setVisibility(8);
    if (Build.VERSION.SDK_INT >= 21) {
      int i = AppInfoUtils.getStatusBarHeight(this.d);
      RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)this.h.getLayoutParams();
      layoutParams.topMargin += i;
      this.h.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
    } 
    this.h.setOnClickListener(new View.OnClickListener(this) {
          public void onClick(View param1View) {
            PortalMananger.c(this.a).sendEmptyMessage(2);
          }
        });
    this.g = (TextView)view.findViewById(2131165210);
    this.e = (ImageView)view.findViewById(2131165209);
    this.f = (ImageView)view.findViewById(2131165262);
    this.f.setBackgroundResource(Constant.getSplashFooterImg());
    this.c.setContentView(view);
    this.c.setCancelable(false);
    this.c.show();
  }
  
  private void l(CordovaArgs paramCordovaArgs, CallbackContext paramCallbackContext) {
    this.cordova.getThreadPool().execute(new Runnable(this) {
          public void run() {
            InnerState innerState = InnerState.load();
            CollectionHelper.getInstance().release();
            CollectionHelper.updateCollectionInfo("appinfo", PortalMananger.p(this.a), "");
            this.a.stopService("1003");
            Context context = StubApp.getOrigApplicationContext(this.a.cordova.getActivity().getApplicationContext());
            PreferencesUtils.putString(context, "clickTerm", "1");
            if (CdcUtils.isWifiConnected(context) && innerState != null && innerState.isOnline())
              CdcProtocol.reqireTerm(innerState, "2", 0L); 
            PortalConn.forceExitApp();
          }
        });
  }
  
  private void m() {
    (new AsyncHandlerTask(this.n)).execute(new AsyncHandlerTask.Task(this) {
          protected Object a() {
            Bitmap bitmap;
            InnerState innerState = InnerState.load();
            ADInfo aDInfo = CdcProtocol.getAdvData(PortalMananger.d(this.a), innerState);
            if (aDInfo == null)
              return null; 
            Context context = StubApp.getOrigApplicationContext(this.a.cordova.getActivity().getApplicationContext());
            Display display = ((WindowManager)context.getSystemService("window")).getDefaultDisplay();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            display.getMetrics(displayMetrics);
            int i = displayMetrics.widthPixels;
            int j = displayMetrics.heightPixels;
            String str1 = aDInfo.getHref();
            String str2 = aDInfo.getAdid();
            String str3 = aDInfo.getUrl();
            StringBuilder stringBuilder1 = new StringBuilder();
            stringBuilder1.append("CDCLogs");
            stringBuilder1.append(File.separator);
            stringBuilder1.append("AD");
            File file = CdcUtils.openFileDir(context, stringBuilder1.toString());
            if (file == null)
              return null; 
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(str3.hashCode());
            stringBuilder2.append(".jpg");
            String str5 = stringBuilder2.toString();
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append(file.getPath());
            stringBuilder3.append(File.separator);
            stringBuilder3.append(str5);
            String str4 = stringBuilder3.toString();
            if (!(new File(str4)).exists()) {
              bitmap = ImageUtil.loadImageFormUrl(context, str3, i, j);
              if (bitmap != null)
                ImageUtil.writeBitmapToFile(str4, bitmap, 100); 
              return null;
            } 
            ImageUtil.loadImage(context, str4, str3, PortalMananger.e(this.a), i, j, new AsyncHandlerTask(PortalMananger.c(this.a)), new ImageUtil.ImageCallback(this, str2, context, (InnerState)bitmap, str1) {
                  public void success(Bitmap param2Bitmap) {
                    if (PortalMananger.f(this.e.a)) {
                      if (PortalMananger.e(this.e.a) == null)
                        return; 
                      PortalMananger.e(this.e.a).setOnClickListener(new View.OnClickListener(this) {
                            public void onClick(View param3View) {
                              if (!TextUtils.isEmpty(this.a.a) && !PortalMananger.g(this.a.e.a)) {
                                PortalMananger.a(this.a.e.a, true);
                                PortalMananger.b(this.a.e.a).post(new Runnable(this) {
                                      public void run() {
                                        String str = PreferencesUtils.getString(this.a.a.b, "Local_SplashAD_Key", "[]");
                                        try {
                                          JSONArray jSONArray = new JSONArray(str);
                                          JSONObject jSONObject = new JSONObject();
                                          jSONObject.put("account", PreferencesUtils.getString(this.a.a.b, "account", ""));
                                          jSONObject.put("ostype", "4");
                                          jSONObject.put("cid", this.a.a.c.getSchoolId(""));
                                          StringBuilder stringBuilder = new StringBuilder();
                                          stringBuilder.append(AppInfoUtils.getVersionCode(this.a.a.b));
                                          stringBuilder.append("");
                                          jSONObject.put("version", stringBuilder.toString());
                                          jSONObject.put("clientid", this.a.a.c.getClientId());
                                          jSONObject.put("stattime", (new SimpleDateFormat("yyyy-MM-dd")).format(new Date()));
                                          jSONObject.put("count", 1);
                                          jSONObject.put("type", 2);
                                          jSONObject.put("moduleId", this.a.a.a);
                                          jSONArray.put(jSONObject);
                                          PreferencesUtils.putString(this.a.a.b, "Local_SplashAD_Key", jSONArray.toString());
                                          return;
                                        } catch (JSONException jSONException) {
                                          jSONException.printStackTrace();
                                          return;
                                        } 
                                      }
                                    });
                              } 
                              if (TextUtils.isEmpty(this.a.d))
                                return; 
                              Intent intent = new Intent((Context)this.a.e.a.cordova.getActivity(), WebViewActivity.class);
                              intent.putExtra("url", this.a.d);
                              this.a.e.a.cordova.getActivity().startActivity(intent.addFlags(268435456));
                            }
                          });
                      PortalMananger.e(this.e.a).setImageDrawable((Drawable)new BitmapDrawable(param2Bitmap));
                      PortalMananger.c(this.e.a).removeMessages(2);
                      PortalMananger.h(this.e.a).setVisibility(0);
                      if (!PortalMananger.f(this.e.a))
                        return; 
                      Message message = Message.obtain();
                      message.what = 5;
                      message.obj = Integer.valueOf(5000);
                      PortalMananger.c(this.e.a).sendMessage(message);
                      return;
                    } 
                  }
                });
            return null;
          }
        });
  }
  
  private void m(CordovaArgs paramCordovaArgs, CallbackContext paramCallbackContext) {
    try {
      if (!paramCordovaArgs.getString(0).equals("2")) {
        this.l.sendEmptyMessageDelayed(1001, 500L);
        this.l.sendEmptyMessageDelayed(1002, 1500L);
        this.l.sendEmptyMessageDelayed(1003, 1500L);
        this.l.sendEmptyMessageDelayed(1004, 1500L);
        this.l.sendEmptyMessageDelayed(1005, 1500L);
        return;
      } 
      this.l.removeMessages(1001);
      this.l.removeMessages(1002);
      this.l.removeMessages(1003);
      this.l.removeMessages(1004);
      this.l.removeMessages(1005);
      return;
    } catch (JSONException jSONException) {
      jSONException.printStackTrace();
      return;
    } 
  }
  
  private View n() {
    try {
      return (View)this.webView.getClass().getMethod("getView", new Class[0]).invoke(this.webView, new Object[0]);
    } catch (Exception exception) {
      return (View)this.webView;
    } 
  }
  
  private void n(CordovaArgs paramCordovaArgs, CallbackContext paramCallbackContext) {
    this.cordova.getThreadPool().execute(new Runnable(this, paramCallbackContext) {
          public void run() {
            InnerState innerState = InnerState.load();
            PortalConn.runManagerInternetTool(this.b.cordova, this.b.webView, this.a, innerState);
          }
        });
  }
  
  private void o() {
    this.cordova.getThreadPool().execute(new Runnable(this) {
          public void run() {
            NetInfo netInfo = new NetInfo(PortalMananger.i(this.a));
            InnerState innerState = InnerState.obtain();
            PortalConn.onCheckNet(this.a.cordova, this.a.webView, innerState, netInfo, 0, this.a);
          }
        });
  }
  
  private void o(CordovaArgs paramCordovaArgs, CallbackContext paramCallbackContext) {
    // Byte code:
    //   0: iconst_0
    //   1: istore_3
    //   2: aload_1
    //   3: iconst_0
    //   4: invokevirtual getJSONArray : (I)Lorg/json/JSONArray;
    //   7: iconst_0
    //   8: invokevirtual getString : (I)Ljava/lang/String;
    //   11: astore #5
    //   13: aload_1
    //   14: iconst_0
    //   15: invokevirtual getJSONArray : (I)Lorg/json/JSONArray;
    //   18: iconst_1
    //   19: invokevirtual getString : (I)Ljava/lang/String;
    //   22: astore #6
    //   24: ldc ''
    //   26: astore_1
    //   27: aload #5
    //   29: invokevirtual hashCode : ()I
    //   32: istore #4
    //   34: iload #4
    //   36: ldc_w -1829188563
    //   39: if_icmpeq -> 69
    //   42: iload #4
    //   44: ldc_w 1472842069
    //   47: if_icmpeq -> 53
    //   50: goto -> 116
    //   53: aload #5
    //   55: ldc_w 'des3DecodeECB'
    //   58: invokevirtual equals : (Ljava/lang/Object;)Z
    //   61: ifeq -> 116
    //   64: iconst_1
    //   65: istore_3
    //   66: goto -> 118
    //   69: aload #5
    //   71: ldc_w 'des3EncodeECB'
    //   74: invokevirtual equals : (Ljava/lang/Object;)Z
    //   77: ifeq -> 116
    //   80: goto -> 118
    //   83: aload #6
    //   85: invokestatic des3DecodeECB : (Ljava/lang/String;)Ljava/lang/String;
    //   88: astore_1
    //   89: goto -> 104
    //   92: aload #6
    //   94: ldc_w 'utf-8'
    //   97: invokevirtual getBytes : (Ljava/lang/String;)[B
    //   100: invokestatic des3EncodeECB : ([B)Ljava/lang/String;
    //   103: astore_1
    //   104: aload_2
    //   105: aload_1
    //   106: invokevirtual success : (Ljava/lang/String;)V
    //   109: return
    //   110: astore_1
    //   111: aload_1
    //   112: invokevirtual printStackTrace : ()V
    //   115: return
    //   116: iconst_m1
    //   117: istore_3
    //   118: iload_3
    //   119: tableswitch default -> 140, 0 -> 92, 1 -> 83
    //   140: goto -> 104
    // Exception table:
    //   from	to	target	type
    //   2	24	110	java/lang/Exception
    //   27	34	110	java/lang/Exception
    //   53	64	110	java/lang/Exception
    //   69	80	110	java/lang/Exception
    //   83	89	110	java/lang/Exception
    //   92	104	110	java/lang/Exception
    //   104	109	110	java/lang/Exception
  }
  
  private void p() {
    View view = LayoutInflater.from(this.cordova.getContext()).inflate(2131296290, null);
    this.C = (new AlertDialog.Builder((Context)this.cordova.getActivity(), 2131558400)).setView(view).create();
    this.C.getWindow().setFlags(1024, 1024);
    this.C.setCanceledOnTouchOutside(false);
    this.C.setCancelable(false);
    this.C.show();
    ((TextView)view.findViewById(2131165357)).setText(this.cordova.getContext().getResources().getString(2131492910));
    ((TextView)view.findViewById(2131165352)).setText(this.cordova.getContext().getResources().getString(2131492909).replace("{appName}", this.cordova.getContext().getResources().getString(2131492904)));
    ((TextView)view.findViewById(2131165227)).setText(this.cordova.getContext().getResources().getString(2131492911));
    ((AlartDialogCenterButton)view.findViewById(2131165213)).setOnClickListener(new View.OnClickListener(this) {
          public void onClick(View param1View) {
            PortalMananger.j(this.a).dismiss();
            Intent intent = new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
            this.a.cordova.startActivityForResult(this.a, intent, 2);
          }
        });
    ((TextView)view.findViewById(2131165237)).setOnClickListener(new View.OnClickListener(this) {
          public void onClick(View param1View) {
            PortalMananger.j(this.a).dismiss();
            PortalMananger.k(this.a);
          }
        });
  }
  
  private void p(CordovaArgs paramCordovaArgs, CallbackContext paramCallbackContext) {
    this.cordova.getThreadPool().execute(new Runnable(this, paramCallbackContext) {
          public void run() {
            PortalConn.onOnlineServer(this.b.cordova, this.b.webView, this.a);
          }
        });
  }
  
  private void q() {
    if (PreferencesUtils.getString(this.d, "isFirstLaunch", "true").equals("false"))
      this.l.sendEmptyMessage(4); 
    this.n.sendEmptyMessageDelayed(2, 2000L);
    this.n.sendEmptyMessageDelayed(3, 500L);
  }
  
  private void q(CordovaArgs paramCordovaArgs, CallbackContext paramCallbackContext) {
    this.cordova.getThreadPool().execute(new Runnable(this, paramCordovaArgs, paramCallbackContext) {
          public void run() {
            boolean bool2 = true;
            boolean bool1 = bool2;
            try {
              if (this.a != null) {
                String str = this.a.getString(0);
                bool1 = bool2;
                if (!TextUtils.isEmpty(str)) {
                  boolean bool = "false".equals(str);
                  bool1 = bool2;
                  if (bool)
                    bool1 = false; 
                } 
              } 
            } catch (JSONException jSONException) {
              jSONException.printStackTrace();
              bool1 = bool2;
            } 
            InnerState innerState = InnerState.load();
            PortalConn.onCheckUpdate(this.c.cordova, this.c.webView, innerState, this.b, bool1);
          }
        });
  }
  
  private void r(CordovaArgs paramCordovaArgs, CallbackContext paramCallbackContext) {
    Context context = StubApp.getOrigApplicationContext(this.cordova.getActivity().getApplicationContext());
    if (paramCordovaArgs == null) {
      ToastUtils.showBottomText(context, "下载地址不能为空!");
      return;
    } 
    this.cordova.getThreadPool().execute(new Runnable(this, paramCordovaArgs, context) {
          public void run() {
            try {
              String str = this.a.getString(0);
              if (TextUtils.isEmpty(str)) {
                this.c.cordova.getActivity().runOnUiThread(new Runnable(this) {
                      public void run() {
                        ToastUtils.showBottomText(this.a.b, "下载地址不能为空!");
                      }
                    });
                return;
              } 
              if (Build.VERSION.SDK_INT >= 24) {
                PortalMananger.b(this.c, this.b, str);
                return;
              } 
              Intent intent = new Intent();
              intent.setAction("android.intent.action.VIEW");
              intent.setData(Uri.parse(str));
              this.c.cordova.getActivity().startActivity(intent.addFlags(268435456));
              return;
            } catch (JSONException jSONException) {
              jSONException.printStackTrace();
              return;
            } 
          }
        });
  }
  
  private boolean r() {
    return (this.c != null && this.c.isShowing());
  }
  
  private void s() {
    String str1;
    InnerState innerState = InnerState.load();
    ReturnUIResult returnUIResult = CdcProtocol.getState(StubApp.getOrigApplicationContext(this.cordova.getActivity().getApplicationContext()), innerState);
    byte b = 1;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("onCheckStateHandle state errorcode : ");
    stringBuilder.append(returnUIResult.getErrorCode());
    stringBuilder.append(" and suberrorcode : ");
    stringBuilder.append(returnUIResult.getSubErrorCode());
    ExLog.i(new String[] { stringBuilder.toString() });
    String str2 = returnUIResult.getErrorCode();
    int i = str2.hashCode();
    if (i != 48) {
      switch (i) {
        default:
          b = -1;
          break;
        case 50548:
          if (str2.equals("301")) {
            b = 2;
            break;
          } 
        case 50547:
          if (str2.equals("300"))
            break; 
      } 
    } else if (str2.equals("0")) {
      b = 0;
    } else {
    
    } 
    switch (b) {
      default:
        if ("14".equals(returnUIResult.getErrorCode()))
          break; 
        if (UiErrCode.isProtocolError(returnUIResult.getErrorCode()))
          PortalConn.handleAuthFail(this.cordova, this.webView, innerState, returnUIResult); 
        PortalConn.queryTicket(this.cordova, this.webView, innerState, this, false);
        return;
      case 1:
      case 2:
        this.l.postDelayed(this.M, CdcProtocol.getStateInterval(innerState));
        return;
      case 0:
        str1 = TextUtils.split(innerState.getUserId(), "@")[0];
        PortalConn.handleAuthSuccess(this, this.cordova, this.webView, str1, innerState, false);
        return;
    } 
    PortalConn.queryTicket(this.cordova, this.webView, innerState, this, false);
  }
  
  private boolean t() {
    String str = InnerState.load().getFuncfgUrl("Vpn", "enable", "");
    return (!TextUtils.isEmpty(str) && "1".equals(str));
  }
  
  private void u() {
    d(CdcUtils.isWifiConnected(null));
  }
  
  private void v() {
    View view = LayoutInflater.from(this.cordova.getContext()).inflate(2131296290, null);
    this.C = (new AlertDialog.Builder((Context)this.cordova.getActivity(), 2131558400)).setView(view).create();
    this.C.getWindow().setFlags(1024, 1024);
    this.C.setCanceledOnTouchOutside(false);
    this.C.setCancelable(false);
    this.C.show();
    ((TextView)view.findViewById(2131165357)).setText(this.cordova.getContext().getResources().getString(2131492908));
    ((TextView)view.findViewById(2131165352)).setText(this.cordova.getContext().getResources().getString(2131492907).replace("{appName}", this.cordova.getContext().getResources().getString(2131492904)));
    ((TextView)view.findViewById(2131165227)).setText(this.cordova.getContext().getResources().getString(2131492911));
    ((AlartDialogCenterButton)view.findViewById(2131165213)).setOnClickListener(new View.OnClickListener(this) {
          public void onClick(View param1View) {
            PortalMananger.j(this.a).dismiss();
            PermissionHelper.requestPermissions(this.a, 0, new String[] { "android.permission.ACCESS_COARSE_LOCATION" });
          }
        });
    ((TextView)view.findViewById(2131165237)).setOnClickListener(new View.OnClickListener(this) {
          public void onClick(View param1View) {
            PortalMananger.j(this.a).dismiss();
            PortalMananger.a(this.a, PortalMananger.d(this.a));
          }
        });
  }
  
  private void w() {
    String str1 = InnerState.load().getFuncfgUrl("Campus", "");
    String str2 = InnerState.load().getFuncfgUrl("CampusInterface", "");
    if (!TextUtils.isEmpty(str1)) {
      Context context = this.cordova.getContext();
      String str5 = PreferencesUtils.getString(context, "DialAccount", "");
      String str3 = PreferencesUtils.getString(context, "schoolID", "");
      StringBuilder stringBuilder3 = new StringBuilder();
      stringBuilder3.append(AppInfoUtils.getVersionCode(context));
      stringBuilder3.append("");
      String str4 = stringBuilder3.toString();
      StringBuilder stringBuilder1 = new StringBuilder();
      StringBuilder stringBuilder5 = new StringBuilder();
      stringBuilder5.append("account=");
      stringBuilder5.append(str5);
      stringBuilder1.append(stringBuilder5.toString());
      StringBuilder stringBuilder4 = new StringBuilder();
      stringBuilder4.append("&cid=");
      stringBuilder4.append(str3);
      stringBuilder1.append(stringBuilder4.toString());
      StringBuilder stringBuilder2 = new StringBuilder();
      stringBuilder2.append("&ostype=");
      stringBuilder2.append("4");
      stringBuilder1.append(stringBuilder2.toString());
      stringBuilder2 = new StringBuilder();
      stringBuilder2.append("&version=");
      stringBuilder2.append(str4);
      stringBuilder1.append(stringBuilder2.toString());
      stringBuilder2 = new StringBuilder();
      stringBuilder2.append("&infotype=");
      stringBuilder2.append("0");
      stringBuilder1.append(stringBuilder2.toString());
      stringBuilder2 = new StringBuilder();
      stringBuilder2.append("&ifurl=");
      stringBuilder2.append(str2);
      stringBuilder1.append(stringBuilder2.toString());
      if (str1.contains("?")) {
        str1 = String.format("%s&%s", new Object[] { str1, stringBuilder1.toString() });
      } else {
        str1 = String.format("%s?%s", new Object[] { str1, stringBuilder1.toString() });
      } 
      Intent intent = new Intent((Context)this.cordova.getActivity(), PWebViewActivity.class);
      intent.putExtra("url", str1);
      intent.putExtra("titleName", "校园通");
      this.cordova.getActivity().startActivity(intent.addFlags(268435456));
    } 
  }
  
  private void x() {
    Intent intent = new Intent("android.settings.WIFI_SETTINGS");
    this.cordova.startActivityForResult(this, intent, 0);
  }
  
  private void y() {
    View view = LayoutInflater.from(this.cordova.getContext()).inflate(2131296289, null);
    AlertDialog alertDialog = (new AlertDialog.Builder((Context)this.cordova.getActivity(), 2131558400)).setView(view).create();
    alertDialog.getWindow().setFlags(1024, 1024);
    alertDialog.setCanceledOnTouchOutside(false);
    alertDialog.setCancelable(false);
    alertDialog.show();
    ((TextView)view.findViewById(2131165357)).setText(this.cordova.getContext().getString(2131492949));
    ((TextView)view.findViewById(2131165352)).setText(this.cordova.getContext().getString(2131492950));
    ((TextView)view.findViewById(2131165227)).setText(this.cordova.getContext().getResources().getString(2131493073));
    ((AlartDialogCenterButton)view.findViewById(2131165213)).setOnClickListener(new View.OnClickListener(this, alertDialog) {
          public void onClick(View param1View) {
            this.a.dismiss();
            PortalConn.forceExitApp();
          }
        });
  }
  
  private void z() {
    if (!PreferencesUtils.getBoolean(this.cordova.getContext(), "PrivacyKey", true)) {
      q();
      return;
    } 
    View view = LayoutInflater.from(this.cordova.getContext()).inflate(2131296292, null);
    AlertDialog alertDialog = (new AlertDialog.Builder((Context)this.cordova.getActivity(), 2131558400)).setView(view).create();
    alertDialog.getWindow().setFlags(1024, 1024);
    alertDialog.setCanceledOnTouchOutside(false);
    alertDialog.setCancelable(false);
    alertDialog.show();
    ((TextView)view.findViewById(2131165357)).setText(this.cordova.getContext().getString(2131493052));
    ((TextView)view.findViewById(2131165352)).setText(this.cordova.getContext().getString(2131493051).replaceAll("\\{appName\\}", this.cordova.getContext().getResources().getString(2131492904)));
    CheckBox checkBox = (CheckBox)view.findViewById(2131165231);
    TextView textView = (TextView)view.findViewById(2131165233);
    String str1 = this.cordova.getContext().getString(2131493069);
    String str2 = this.cordova.getContext().getResources().getString(2131493050);
    String str3 = this.cordova.getContext().getString(2131493074);
    Context context = this.cordova.getContext();
    View.OnClickListener onClickListener = new View.OnClickListener(this) {
        public void onClick(View param1View) {
          int i = ((Integer)param1View.getTag()).intValue();
          if (i == 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("file:///android_asset/www/");
            stringBuilder.append(Constant.getPolicyDialogHtml());
            String str = stringBuilder.toString();
            Intent intent = new Intent((Context)this.a.cordova.getActivity(), WebViewActivity.class);
            intent.putExtra("url", str);
            intent.putExtra("title", "隐私政策");
            this.a.cordova.getActivity().startActivity(intent.addFlags(268435456));
            return;
          } 
          if (i == 1) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("file:///android_asset/www/");
            stringBuilder.append(Constant.getUserServiceDialogHtml());
            String str = stringBuilder.toString();
            Intent intent = new Intent((Context)this.a.cordova.getActivity(), WebViewActivity.class);
            intent.putExtra("url", str);
            intent.putExtra("title", "用户服务协议");
            this.a.cordova.getActivity().startActivity(intent.addFlags(268435456));
          } 
        }
      };
    SpannableStringUtils.bindLinkText(context, textView, str1, new String[] { str2, str3 }, onClickListener);
    ((TextView)view.findViewById(2131165279)).setText(this.cordova.getContext().getResources().getString(2131493049));
    ((AlartDialogLeftButton)view.findViewById(2131165214)).setOnClickListener(new View.OnClickListener(this, alertDialog) {
          public void onClick(View param1View) {
            this.a.dismiss();
            PortalConn.forceExitApp();
          }
        });
    ((TextView)view.findViewById(2131165311)).setText(this.cordova.getContext().getResources().getString(2131493048));
    ((AlartDialogRightButton)view.findViewById(2131165215)).setOnClickListener(new View.OnClickListener(this, checkBox, alertDialog) {
          public void onClick(View param1View) {
            if (!this.a.isChecked()) {
              ToastUtils.showBottomText(this.c.cordova.getContext(), this.c.cordova.getContext().getString(2131493070));
              return;
            } 
            this.b.dismiss();
            PreferencesUtils.putBoolean(this.c.cordova.getContext(), "PrivacyKey", false);
            PortalMananger.s(this.c);
          }
        });
  }
  
  public boolean execute(String paramString, CordovaArgs paramCordovaArgs, CallbackContext paramCallbackContext) {
    byte b;
    switch (paramString.hashCode()) {
      default:
        b = -1;
        break;
      case 1785537465:
        if (paramString.equals("onSetPhoneCSSStyle")) {
          b = 2;
          break;
        } 
      case 1684415522:
        if (paramString.equals("onVersionUpdate")) {
          b = 9;
          break;
        } 
      case 1639956106:
        if (paramString.equals("onIntentPage")) {
          b = 3;
          break;
        } 
      case 1369052181:
        if (paramString.equals("loadImage")) {
          b = 20;
          break;
        } 
      case 1088669764:
        if (paramString.equals("handleLocationMsg")) {
          b = 25;
          break;
        } 
      case 1036547698:
        if (paramString.equals("runInstalledApp")) {
          b = 17;
          break;
        } 
      case 985314314:
        if (paramString.equals("setCacheData")) {
          b = 5;
          break;
        } 
      case 808753211:
        if (paramString.equals("runManagerInternetTool")) {
          b = 12;
          break;
        } 
      case 514627535:
        if (paramString.equals("cryptoHelper")) {
          b = 13;
          break;
        } 
      case 247006579:
        if (paramString.equals("onExitApplication")) {
          b = 10;
          break;
        } 
      case 198661894:
        if (paramString.equals("onStartNetCheckTool")) {
          b = 11;
          break;
        } 
      case 11858548:
        if (paramString.equals("onCheckNet")) {
          b = 1;
          break;
        } 
      case 107332:
        if (paramString.equals("log")) {
          b = 16;
          break;
        } 
      case -226984076:
        if (paramString.equals("requestNeedPermission")) {
          b = 23;
          break;
        } 
      case -266054839:
        if (paramString.equals("onGetVerificatioCode")) {
          b = 4;
          break;
        } 
      case -378587785:
        if (paramString.equals("showToastText")) {
          b = 22;
          break;
        } 
      case -450908867:
        if (paramString.equals("openQRCodeView")) {
          b = 24;
          break;
        } 
      case -563678314:
        if (paramString.equals("getCacheData")) {
          b = 6;
          break;
        } 
      case -724651872:
        if (paramString.equals("hideOrShowOnlineService")) {
          b = 19;
          break;
        } 
      case -1012941077:
        if (paramString.equals("onTerm")) {
          b = 8;
          break;
        } 
      case -1013451555:
        if (paramString.equals("onCall")) {
          b = 15;
          break;
        } 
      case -1013491673:
        if (paramString.equals("onAuth")) {
          b = 7;
          break;
        } 
      case -1181150475:
        if (paramString.equals("onOnlineServer")) {
          b = 14;
          break;
        } 
      case -1211167628:
        if (paramString.equals("downloadApk")) {
          b = 18;
          break;
        } 
      case -1740298179:
        if (paramString.equals("InitMananger")) {
          b = 0;
          break;
        } 
      case -1794779278:
        if (paramString.equals("pingAppServer")) {
          b = 21;
          break;
        } 
    } 
    switch (b) {
      default:
        return super.execute(paramString, paramCordovaArgs, paramCallbackContext);
      case 24:
        this.l.post(new Runnable(this, paramCordovaArgs, paramCallbackContext) {
              public void run() {
                PortalMananger.d(this.c, this.a, this.b);
              }
            });
        return true;
      case 23:
        paramString = paramCordovaArgs.getString(0);
        if (Build.VERSION.SDK_INT >= 23) {
          PermissionHelper.requestPermission(this, 1, paramString);
          return true;
        } 
        PortalConn.permissionSuccessCallBackToJs(this.cordova, this.webView, paramString);
        return true;
      case 22:
        paramString = paramCordovaArgs.getString(0);
        this.n.post(new Runnable(this, paramString) {
              public void run() {
                ToastUtils.showBottomText(PortalMananger.d(this.b), this.a);
              }
            });
        return true;
      case 21:
        e(paramCordovaArgs, paramCallbackContext);
        return true;
      case 20:
        f(paramCordovaArgs, paramCallbackContext);
        return true;
      case 19:
        g(paramCordovaArgs, paramCallbackContext);
        return true;
      case 18:
        r(paramCordovaArgs, paramCallbackContext);
        return true;
      case 17:
        h(paramCordovaArgs, paramCallbackContext);
        return true;
      case 16:
        b(paramCordovaArgs);
        return true;
      case 15:
        g(paramCordovaArgs);
        return true;
      case 14:
        p(paramCordovaArgs, paramCallbackContext);
        return true;
      case 13:
        this.cordova.getThreadPool().execute(new Runnable(this, paramCordovaArgs, paramCallbackContext) {
              public void run() {
                PortalMananger.c(this.c, this.a, this.b);
              }
            });
        return true;
      case 12:
        n(paramCordovaArgs, paramCallbackContext);
        return true;
      case 11:
        this.cordova.getThreadPool().execute(new Runnable(this, paramCordovaArgs, paramCallbackContext) {
              public void run() {
                PortalMananger.b(this.c, this.a, this.b);
              }
            });
        return true;
      case 10:
        l(paramCordovaArgs, paramCallbackContext);
        return true;
      case 9:
        q(paramCordovaArgs, paramCallbackContext);
        return true;
      case 8:
        a(paramCordovaArgs, this, paramCallbackContext);
        return true;
      case 7:
        c(paramCordovaArgs);
        return true;
      case 6:
        i(paramCordovaArgs, paramCallbackContext);
        return true;
      case 5:
        this.cordova.getThreadPool().execute(new Runnable(this, paramCordovaArgs, paramCallbackContext) {
              public void run() {
                PortalMananger.a(this.c, this.a, this.b);
              }
            });
        return true;
      case 4:
        k(paramCordovaArgs, paramCallbackContext);
        return true;
      case 3:
        this.cordova.getThreadPool().execute(new Runnable(this, paramCordovaArgs) {
              public void run() {
                PortalMananger.a(this.b, this.a);
              }
            });
        return true;
      case 2:
        e(paramCordovaArgs);
        return true;
      case 1:
        d(paramCordovaArgs);
        return true;
      case 0:
        this.D = true;
        if (!r() && !this.E)
          b(0); 
        break;
      case 25:
        break;
    } 
    return true;
  }
  
  public boolean isInitFinish() {
    return this.E;
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt1 == 0 && paramInt2 == 0) {
      ExLog.i(new String[] { "wifi setting page closed" });
      u();
      return;
    } 
    if (paramInt1 == 1) {
      if (paramInt2 == -1) {
        this.K = UUID.randomUUID().toString();
        boolean bool = PreferencesUtils.getBoolean(this.cordova.getContext(), "vpnSelfConnect", false);
        HoldVpnService.start(this.d, bool, this.K);
        return;
      } 
      InnerState innerState = InnerState.load();
      PortalConn.handleAuthFail(this.cordova, this.webView, innerState, new ReturnUIResult("200123"));
      if (PreferencesUtils.getBoolean(this.cordova.getContext(), "vpnSelfConnect", false)) {
        this.l.post(new Runnable(this) {
              public void run() {
                CdcProtocol.reqireTerm(InnerState.load(), "1");
                this.a.onTermSuccessAndCheckNet();
              }
            });
        return;
      } 
    } else {
      if (paramInt1 == 2) {
        o();
        return;
      } 
      if (paramInt1 == 4)
        this.n.post(new Runnable(this) {
              public void run() {
                if (PortalMananger.r(this.a)) {
                  if (HoldVpnService.prepare(this.a.cordova.getContext()) != null) {
                    ToastUtils.showBottomText(this.a.cordova.getContext(), this.a.cordova.getContext().getResources().getString(2131493077));
                    return;
                  } 
                  ToastUtils.showBottomText(this.a.cordova.getContext(), this.a.cordova.getContext().getResources().getString(2131493078));
                  return;
                } 
                ToastUtils.showBottomText(this.a.cordova.getContext(), this.a.cordova.getContext().getResources().getString(2131493008));
              }
            }); 
    } 
  }
  
  public void onCheckState() {
    removeCheckState();
    PreferencesUtils.putString(StubApp.getOrigApplicationContext(this.cordova.getActivity().getApplicationContext()), "loginSystemTimeString", "0");
    this.l.post(this.M);
  }
  
  public void onDestroy() {
    if (this.C != null && this.C.isShowing())
      this.C.dismiss(); 
    if (this.F != null) {
      this.cordova.getContext().unregisterReceiver((BroadcastReceiver)this.F);
      this.F = null;
    } 
    if (this.G != null)
      this.G.unregisterReceiver(this.cordova.getContext()); 
    k();
    a(false);
    if (this.b != null)
      this.b.dismiss(); 
    if (this.a != null)
      this.a.release(); 
    ImageUtil.release();
    super.onDestroy();
  }
  
  public void onKeepChange(ReturnUIResult paramReturnUIResult) {
    this.l.post(new PortalMananger$$Lambda$9(this, paramReturnUIResult));
  }
  
  public void onNetworkChange() {
    if (this.l.hasMessages(8))
      this.l.removeMessages(8); 
    this.l.sendEmptyMessageDelayed(8, 5000L);
  }
  
  public void onQRCodeError(ReturnUIResult paramReturnUIResult) {
    if (!paramReturnUIResult.isSuccess())
      PortalConn.onQRcodeCallBackToJs(this.cordova, this.webView, 3, paramReturnUIResult); 
  }
  
  public void onRequestPermissionResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfint) {
    super.onRequestPermissionResult(paramInt, paramArrayOfString, paramArrayOfint);
    if (paramInt == 0) {
      a(this.d);
      return;
    } 
    if (paramInt == 1)
      a(this.d, paramArrayOfString); 
  }
  
  public void onResume(boolean paramBoolean) {}
  
  public void onReverseNotifyChange(ReturnUIResult paramReturnUIResult) {
    this.l.post(new PortalMananger$$Lambda$10(this, paramReturnUIResult));
  }
  
  public void onSharedChange(Object paramObject) {
    this.l.post(new PortalMananger$$Lambda$11(this, paramObject));
  }
  
  public void onTermSuccessAndCheckNet() {
    if (CdcUtils.isWifiConnected(null)) {
      a(false, 2);
      return;
    } 
    PortalConn.noNetWorkCallBackToJs(this.cordova, this.webView, 2);
  }
  
  protected void pluginInitialize() {
    super.pluginInitialize();
    this.d = StubApp.getOrigApplicationContext(this.cordova.getActivity().getApplicationContext());
    g();
    ExLog.cleanup();
    InnerState innerState = InnerState.load();
    e();
    c(innerState);
    h();
    a(innerState);
    b(innerState);
    j();
    i();
    this.l.sendEmptyMessage(9);
    this.l.sendEmptyMessage(0);
  }
  
  public void removeCheckState() {
    this.l.removeCallbacks(this.M);
  }
  
  public void setLastNetInfo(NetInfo paramNetInfo) {
    this.I = paramNetInfo;
  }
  
  public void startService(boolean paramBoolean) {
    if (paramBoolean)
      if (t()) {
        if (!ServiceUtils.isServiceRunning(this.cordova.getContext(), HoldVpnService.class.getName())) {
          c(paramBoolean);
          return;
        } 
      } else if (!ServiceUtils.isServiceRunning(this.cordova.getContext(), HoldService.class.getName())) {
        this.K = UUID.randomUUID().toString();
        HoldService.start(this.cordova.getContext(), paramBoolean, this.K);
      }  
  }
  
  public void stopService(String paramString) {
    this.l.removeMessages(6);
    CollectionHelper.updateCollectionInfo("holdinfo", this.K, paramString);
    if (t()) {
      HoldVpnService.stop(this.cordova.getContext());
      return;
    } 
    HoldService.stop(this.cordova.getContext());
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\plugin\PortalMananger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */