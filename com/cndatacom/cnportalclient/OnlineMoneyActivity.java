package com.cndatacom.cnportalclient;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.cndatacom.cnportalclient.model.InnerState;
import com.cndatacom.cnportalclient.plugin.ActivityManager;
import com.cndatacom.cnportalclient.utils.AppInfoUtils;
import com.cndatacom.cnportalclient.utils.CdcUtils;
import com.cndatacom.cnportalclient.utils.ExLog;
import com.cndatacom.cnportalclient.utils.PreferencesUtils;
import com.stub.StubApp;
import java.util.HashMap;

public class OnlineMoneyActivity extends AppCompatActivity {
  private WebView a;
  
  private ProgressBar b;
  
  private View.OnKeyListener c = new View.OnKeyListener(this) {
      public boolean onKey(View param1View, int param1Int, KeyEvent param1KeyEvent) {
        if (param1KeyEvent.getAction() == 0 && param1Int == 4 && OnlineMoneyActivity.b(this.a).canGoBack()) {
          OnlineMoneyActivity.b(this.a).goBack();
          return true;
        } 
        return false;
      }
    };
  
  static {
    StubApp.interface11(1516);
  }
  
  @SuppressLint({"NewApi", "JavascriptInterface"})
  private void a() {
    InnerState innerState = InnerState.load();
    Context context = StubApp.getOrigApplicationContext(getApplicationContext());
    String str2 = innerState.getFuncfgUrl("Recharge", null);
    String str1 = str2;
    if (TextUtils.isEmpty(str2)) {
      ExLog.i(new String[] { "OnlineMoneyActivity url == null" });
      str1 = "http://zsteduapp.10000.gd.cn/More/Recharge/index.html";
    } 
    str2 = PreferencesUtils.getString(context, "DialAccount", "");
    String str5 = PreferencesUtils.getString(context, "schoolID", "");
    String str3 = PreferencesUtils.getString(context, "domain", "");
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append(AppInfoUtils.getVersionCode(context));
    stringBuilder2.append("");
    String str6 = stringBuilder2.toString();
    String str7 = PreferencesUtils.getString(context, "area", "");
    String str8 = CdcUtils.getIP();
    String str4 = innerState.getClientId();
    StringBuilder stringBuilder3 = new StringBuilder();
    stringBuilder3.append(str1);
    stringBuilder3.append("?schoolid=");
    stringBuilder3.append(str5);
    stringBuilder3.append("&versions=");
    stringBuilder3.append(str6);
    stringBuilder3.append("&area=");
    stringBuilder3.append(str7);
    stringBuilder3.append("&clientid=");
    stringBuilder3.append(str4);
    stringBuilder3.append("&ip=");
    stringBuilder3.append(str8);
    stringBuilder3.append("&account=");
    stringBuilder3.append(str2);
    stringBuilder3.append("&domain=");
    stringBuilder3.append(str3);
    stringBuilder3.append("&ccmpid=cpb-factory-app-cz&num=A-020-%s@%s-100");
    str2 = String.format(stringBuilder3.toString(), new Object[] { str2, str3 });
    str1 = str2;
    if ("0".equals(str3))
      str1 = str2.replaceFirst("@0", ""); 
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append("OnlineMoneyActivity url : ");
    stringBuilder1.append(str1);
    ExLog.i(new String[] { stringBuilder1.toString() });
    WebSettings webSettings = this.a.getSettings();
    webSettings.setJavaScriptEnabled(true);
    webSettings.setDomStorageEnabled(true);
    webSettings.setAllowFileAccess(true);
    webSettings.setSupportZoom(false);
    webSettings.setBuiltInZoomControls(false);
    if (Build.VERSION.SDK_INT >= 11)
      webSettings.setDisplayZoomControls(false); 
    webSettings.setUseWideViewPort(true);
    webSettings.setLoadWithOverviewMode(true);
    webSettings.setCacheMode(2);
    webSettings.setAppCachePath(StubApp.getOrigApplicationContext(getApplicationContext()).getCacheDir().getAbsolutePath());
    this.a.clearCache(true);
    this.a.setFocusable(true);
    this.a.setFocusableInTouchMode(true);
    this.a.addJavascriptInterface(this, "elecallback");
    this.a.setOnKeyListener(this.c);
    this.a.removeJavascriptInterface("searchBoxJavaBridge_");
    this.a.removeJavascriptInterface("accessibility");
    this.a.removeJavascriptInterface("accessibilityTraversal");
    this.a.setWebViewClient(new WebViewClient(this) {
          public void onPageFinished(WebView param1WebView, String param1String) {
            super.onPageFinished(param1WebView, param1String);
            OnlineMoneyActivity.a(this.a).setVisibility(4);
          }
          
          public void onPageStarted(WebView param1WebView, String param1String, Bitmap param1Bitmap) {
            super.onPageStarted(param1WebView, param1String, param1Bitmap);
            OnlineMoneyActivity.a(this.a).setVisibility(0);
          }
          
          public boolean shouldOverrideUrlLoading(WebView param1WebView, String param1String) {
            if (param1String.startsWith("http") || param1String.startsWith("https") || param1String.startsWith("file")) {
              Uri uri = Uri.parse(param1String);
              if ("weixin".equals(uri.getScheme()))
                try {
                  Intent intent = new Intent("android.intent.action.VIEW", uri);
                  this.a.startActivity(intent);
                  return true;
                } catch (Exception exception) {
                  Toast.makeText((Context)this.a, "没有安装对应的支付软件", 1).show();
                  return true;
                }  
              HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
              hashMap.put("Referer", "http://wapgd.189.cn");
              exception.loadUrl(param1String, hashMap);
              return true;
            } 
            try {
              Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(param1String));
              this.a.startActivity(intent);
              return true;
            } catch (Exception exception) {
              Toast.makeText((Context)this.a, "没有安装对应的支付软件", 1).show();
              return true;
            } 
          }
        });
    this.a.setWebChromeClient(new WebChromeClient());
    this.a.loadUrl(str1);
  }
  
  private void b() {
    if (Build.VERSION.SDK_INT >= 21) {
      Window window = getWindow();
      window.clearFlags(67108864);
      window.getDecorView().setSystemUiVisibility(1024);
      window.setStatusBarColor(0);
      window.setNavigationBarColor(-16777216);
      View view = findViewById(2131165343);
      if (view == null)
        return; 
      view.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, AppInfoUtils.getStatusBarHeight(StubApp.getOrigApplicationContext(getApplicationContext()))));
      view.setBackgroundColor(ContextCompat.getColor(StubApp.getOrigApplicationContext(getApplicationContext()), 2130968666));
      view.setVisibility(0);
    } 
  }
  
  protected native void onCreate(Bundle paramBundle);
  
  protected void onDestroy() {
    ActivityManager.getInstance().removeActivity((Activity)this);
    super.onDestroy();
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\OnlineMoneyActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */