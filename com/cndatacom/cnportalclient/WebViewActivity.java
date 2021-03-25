package com.cndatacom.cnportalclient;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cndatacom.cnportalclient.plugin.ActivityManager;
import com.cndatacom.cnportalclient.utils.AppInfoUtils;
import com.stub.StubApp;

public class WebViewActivity extends AppCompatActivity {
  private TextView a;
  
  private ProgressBar b;
  
  private WebView c;
  
  private Context d;
  
  private Handler e;
  
  static {
    StubApp.interface11(1553);
  }
  
  private void a() {
    ((RelativeLayout)findViewById(2131165220)).setOnClickListener(new View.OnClickListener(this) {
          public void onClick(View param1View) {
            this.a.finish();
          }
        });
    this.a = (TextView)findViewById(2131165356);
    this.a.setText("");
    this.c = (WebView)findViewById(2131165367);
    this.b = (ProgressBar)findViewById(2131165285);
  }
  
  private void a(String paramString) {
    WebSettings webSettings = this.c.getSettings();
    webSettings.setJavaScriptEnabled(true);
    webSettings.setAllowFileAccess(true);
    this.c.setWebViewClient(new WebViewClient(this) {
          public void onPageFinished(WebView param1WebView, String param1String) {
            super.onPageFinished(param1WebView, param1String);
            WebViewActivity.a(this.a).setVisibility(4);
            WebViewActivity.b(this.a).setText(param1WebView.getTitle());
          }
          
          public void onPageStarted(WebView param1WebView, String param1String, Bitmap param1Bitmap) {
            super.onPageStarted(param1WebView, param1String, param1Bitmap);
            WebViewActivity.a(this.a).setVisibility(0);
          }
          
          public void onReceivedError(WebView param1WebView, int param1Int, String param1String1, String param1String2) {
            super.onReceivedError(param1WebView, param1Int, param1String1, param1String2);
            WebViewActivity.a(this.a).setVisibility(4);
          }
          
          public boolean shouldOverrideUrlLoading(WebView param1WebView, String param1String) {
            param1WebView.loadUrl(param1String);
            return true;
          }
        });
    this.c.setWebChromeClient(new WebChromeClient(this) {
          public boolean onJsAlert(WebView param1WebView, String param1String1, String param1String2, JsResult param1JsResult) {
            (new AlertDialog.Builder(WebViewActivity.c(this.a))).setTitle("温馨提示").setMessage(param1String2).setCancelable(false).setPositiveButton("确定", new DialogInterface.OnClickListener(this, param1JsResult) {
                  public void onClick(DialogInterface param2DialogInterface, int param2Int) {
                    this.a.confirm();
                    param2DialogInterface.dismiss();
                  }
                }).create().show();
            return true;
          }
          
          public boolean onJsConfirm(WebView param1WebView, String param1String1, String param1String2, JsResult param1JsResult) {
            (new AlertDialog.Builder(WebViewActivity.c(this.a))).setTitle("温馨提示").setMessage(param1String2).setCancelable(false).setPositiveButton("确定", new DialogInterface.OnClickListener(this, param1JsResult) {
                  public void onClick(DialogInterface param2DialogInterface, int param2Int) {
                    this.a.confirm();
                    param2DialogInterface.dismiss();
                  }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener(this, param1JsResult) {
                  public void onClick(DialogInterface param2DialogInterface, int param2Int) {
                    this.a.cancel();
                    param2DialogInterface.dismiss();
                  }
                }).create().show();
            return true;
          }
          
          public boolean onJsPrompt(WebView param1WebView, String param1String1, String param1String2, String param1String3, JsPromptResult param1JsPromptResult) {
            return super.onJsPrompt(param1WebView, param1String1, param1String2, param1String3, param1JsPromptResult);
          }
        });
    this.c.loadUrl(paramString);
  }
  
  private void b() {
    this.e = new Handler();
    this.d = StubApp.getOrigApplicationContext(getApplicationContext());
    String str = getIntent().getExtras().getString("url");
    this.e.postDelayed(new Runnable(this, str) {
          public void run() {
            WebViewActivity.a(this.b, this.a);
          }
        }1000L);
  }
  
  private void c() {
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
    if (this.c != null) {
      this.c.destroy();
      this.c = null;
    } 
    ActivityManager.getInstance().removeActivity((Activity)this);
    super.onDestroy();
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
    if (paramInt == 4 && this.c.canGoBack()) {
      this.c.goBack();
      return true;
    } 
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  protected void onPause() {
    super.onPause();
    if (this.c != null)
      this.c.onPause(); 
  }
  
  protected void onResume() {
    super.onResume();
    if (this.c != null)
      this.c.onResume(); 
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\WebViewActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */