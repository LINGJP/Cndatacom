package com.cndatacom.cnportalclient;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cndatacom.cnportalclient.model.InnerState;
import com.cndatacom.cnportalclient.plugin.ActivityManager;
import com.cndatacom.cnportalclient.utils.AndroidBug5497WorkaroundUtils;
import com.cndatacom.cnportalclient.utils.AppInfoUtils;
import com.cndatacom.cnportalclient.utils.NetUtils;
import com.cndatacom.cnportalclient.utils.PreferencesUtils;
import com.cndatacom.cnportalclient.widget.RetryLinearLayout;
import com.stub.StubApp;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PWebViewActivity extends BaseActivity {
  private LinearLayout a;
  
  private WebView b;
  
  private ValueCallback<Uri> c;
  
  private ValueCallback<Uri[]> d;
  
  private LinearLayout e;
  
  private final int f = 0;
  
  private final int g = 1;
  
  private final int h = 2;
  
  private final int i = 3;
  
  private final int j = 4;
  
  private boolean k = false;
  
  private final int l = 30000;
  
  private final int m = 0;
  
  private final int n = -1;
  
  private int o = 0;
  
  private String p = "";
  
  private TextView q;
  
  private String r;
  
  private TextView s;
  
  private boolean t = false;
  
  private void a(int paramInt, boolean paramBoolean) {
    this.o = 0;
    Message message = Message.obtain();
    message.what = 3;
    message.obj = Boolean.valueOf(paramBoolean);
    this.mUIHandler.sendMessageDelayed(message, paramInt);
  }
  
  private void a(ValueCallback<Uri> paramValueCallback) {
    this.c = paramValueCallback;
    Intent intent = new Intent("android.intent.action.GET_CONTENT");
    intent.addCategory("android.intent.category.OPENABLE");
    intent.setType("image/*");
    startActivityForResult(Intent.createChooser(intent, "File Chooser"), 1);
  }
  
  private void a(String paramString) {
    try {
      JSONObject jSONObject = new JSONObject(paramString);
      (new AsyncTask<String, Integer, String>(this, jSONObject.optString("moduletype", ""), jSONObject.optString("moduleid", "")) {
          protected String a(String... param1VarArgs) {
            Context context = StubApp.getOrigApplicationContext(this.c.getApplicationContext());
            InnerState innerState = InnerState.load();
            String str = PreferencesUtils.getString(context, "Local_SplashAD_Key", "[]");
            try {
              JSONArray jSONArray = new JSONArray(str);
              JSONObject jSONObject = new JSONObject();
              jSONObject.put("account", PreferencesUtils.getString(context, "account", ""));
              jSONObject.put("ostype", "4");
              jSONObject.put("cid", innerState.getSchoolId(""));
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append(AppInfoUtils.getVersionCode(context));
              stringBuilder.append("");
              jSONObject.put("version", stringBuilder.toString());
              jSONObject.put("clientid", innerState.getClientId());
              jSONObject.put("stattime", (new SimpleDateFormat("yyyy-MM-dd")).format(new Date()));
              jSONObject.put("count", 1);
              jSONObject.put("type", this.a);
              jSONObject.put("moduleId", this.b);
              jSONArray.put(jSONObject);
              PreferencesUtils.putString(context, "Local_SplashAD_Key", jSONArray.toString());
            } catch (JSONException jSONException) {
              jSONException.printStackTrace();
            } 
            return null;
          }
        }).execute((Object[])new String[] { "" });
      return;
    } catch (JSONException jSONException) {
      jSONException.printStackTrace();
      return;
    } 
  }
  
  private void b(ValueCallback<Uri[]> paramValueCallback) {
    this.d = paramValueCallback;
    Intent intent1 = new Intent("android.intent.action.GET_CONTENT");
    intent1.addCategory("android.intent.category.OPENABLE");
    intent1.setType("image/*");
    Intent intent2 = new Intent("android.intent.action.CHOOSER");
    intent2.putExtra("android.intent.extra.INTENT", (Parcelable)intent1);
    intent2.putExtra("android.intent.extra.TITLE", "Image Chooser");
    startActivityForResult(intent2, 2);
  }
  
  private void c() {
    if (!this.mUIHandler.hasMessages(3)) {
      g();
      a(1500, true);
    } 
  }
  
  private void d() {
    WebSettings webSettings = this.b.getSettings();
    webSettings.setJavaScriptEnabled(true);
    webSettings.setDomStorageEnabled(true);
    webSettings.setAppCacheEnabled(true);
    webSettings.setCacheMode(-1);
    webSettings.setMediaPlaybackRequiresUserGesture(false);
    this.b.addJavascriptInterface(new JavaScriptInterface(this), "AppClient");
    this.b.removeJavascriptInterface("searchBoxJavaBridge_");
    this.b.removeJavascriptInterface("accessibility");
    this.b.removeJavascriptInterface("accessibilityTraversal");
    this.b.setWebViewClient(new WebViewClient(this) {
          public void onPageFinished(WebView param1WebView, String param1String) {
            super.onPageFinished(param1WebView, param1String);
            PWebViewActivity.c(this.a);
            if (TextUtils.isEmpty(PWebViewActivity.d(this.a)))
              PWebViewActivity.e(this.a).setText(param1WebView.getTitle()); 
          }
          
          public void onPageStarted(WebView param1WebView, String param1String, Bitmap param1Bitmap) {
            super.onPageStarted(param1WebView, param1String, param1Bitmap);
            PWebViewActivity.b(this.a);
          }
          
          public void onReceivedError(WebView param1WebView, int param1Int, String param1String1, String param1String2) {
            super.onReceivedError(param1WebView, param1Int, param1String1, param1String2);
          }
          
          @TargetApi(23)
          public void onReceivedHttpError(WebView param1WebView, WebResourceRequest param1WebResourceRequest, WebResourceResponse param1WebResourceResponse) {
            super.onReceivedHttpError(param1WebView, param1WebResourceRequest, param1WebResourceResponse);
          }
          
          public boolean shouldOverrideUrlLoading(WebView param1WebView, String param1String) {
            if (param1String.startsWith("http:") || param1String.startsWith("https:"))
              param1WebView.loadUrl(param1String); 
            return true;
          }
        });
    this.b.setWebChromeClient(new WebChromeClient(this) {
          public boolean onJsAlert(WebView param1WebView, String param1String1, String param1String2, JsResult param1JsResult) {
            (new AlertDialog.Builder((Context)this.a)).setTitle("温馨提示").setMessage(param1String2).setCancelable(false).setPositiveButton("确定", new DialogInterface.OnClickListener(this, param1JsResult) {
                  public void onClick(DialogInterface param2DialogInterface, int param2Int) {
                    this.a.confirm();
                    param2DialogInterface.dismiss();
                  }
                }).create().show();
            return true;
          }
          
          public boolean onJsConfirm(WebView param1WebView, String param1String1, String param1String2, JsResult param1JsResult) {
            (new AlertDialog.Builder((Context)this.a)).setTitle("温馨提示").setMessage(param1String2).setCancelable(false).setPositiveButton("确定", new DialogInterface.OnClickListener(this, param1JsResult) {
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
          
          public boolean onShowFileChooser(WebView param1WebView, ValueCallback<Uri[]> param1ValueCallback, WebChromeClient.FileChooserParams param1FileChooserParams) {
            PWebViewActivity.b(this.a, param1ValueCallback);
            return true;
          }
          
          public void openFileChooser(ValueCallback<Uri> param1ValueCallback) {
            PWebViewActivity.a(this.a, param1ValueCallback);
          }
          
          public void openFileChooser(ValueCallback<Uri> param1ValueCallback, String param1String) {
            PWebViewActivity.a(this.a, param1ValueCallback);
          }
          
          public void openFileChooser(ValueCallback<Uri> param1ValueCallback, String param1String1, String param1String2) {
            PWebViewActivity.a(this.a, param1ValueCallback);
          }
        });
  }
  
  private boolean e() {
    if (NetUtils.getWifiState(this.mContext) != 3) {
      f();
      i();
      return false;
    } 
    this.k = true;
    this.mWorkerHandler.removeMessages(4);
    this.mWorkerHandler.sendEmptyMessageDelayed(4, 30000L);
    return true;
  }
  
  private void f() {
    this.k = false;
    this.mWorkerHandler.removeMessages(4);
  }
  
  private void g() {
    this.mUIHandler.sendEmptyMessage(0);
  }
  
  private void h() {
    if (this.o != -1)
      this.mUIHandler.sendEmptyMessage(1); 
  }
  
  private void i() {
    this.o = -1;
    this.mUIHandler.sendEmptyMessage(2);
  }
  
  protected void a(Bundle paramBundle) {
    paramBundle = getIntent().getExtras();
    this.p = paramBundle.getString("url", "");
    this.r = paramBundle.getString("titleName", "");
    ((RelativeLayout)findViewById(2131165220)).setOnClickListener(new View.OnClickListener(this) {
          public void onClick(View param1View) {
            this.a.onBackPressed();
          }
        });
    this.a = (LinearLayout)findViewById(2131165284);
    this.s = (TextView)findViewById(2131165256);
    this.q = (TextView)findViewById(2131165356);
    if (TextUtils.isEmpty(this.r)) {
      this.q.setText("");
    } else {
      this.q.setText(this.r);
    } 
    this.b = (WebView)findViewById(2131165367);
    d();
    this.e = (LinearLayout)findViewById(2131165254);
    ((RetryLinearLayout)findViewById(2131165308)).setOnClickListener(new View.OnClickListener(this) {
          public void onClick(View param1View) {
            PWebViewActivity.a(this.a);
          }
        });
    g();
    a(1000, false);
    AndroidBug5497WorkaroundUtils.assistActivity((Activity)this);
    ActivityManager.getInstance().addActivity((Activity)this);
  }
  
  protected void a(Message paramMessage) {
    switch (paramMessage.what) {
      default:
        return;
      case 3:
        if (!e())
          return; 
        if (paramMessage.obj != null && paramMessage.obj instanceof Boolean) {
          if (!((Boolean)paramMessage.obj).booleanValue()) {
            this.b.loadUrl(this.p);
            return;
          } 
          this.b.reload();
          return;
        } 
        return;
      case 2:
        this.a.setVisibility(4);
        this.b.setVisibility(4);
        this.e.setVisibility(0);
        return;
      case 1:
        this.a.setVisibility(4);
        this.b.setVisibility(0);
        this.e.setVisibility(4);
        return;
      case 0:
        break;
    } 
    this.s.setText(2131492962);
    this.a.setVisibility(0);
    this.b.setVisibility(4);
    this.e.setVisibility(4);
  }
  
  protected int b() {
    return 2131296298;
  }
  
  protected void b(Message paramMessage) {
    if (paramMessage.what != 4)
      return; 
    if (this.k) {
      this.k = false;
      i();
    } 
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
    Uri uri;
    if (paramInt1 == 1) {
      if (this.c == null)
        return; 
      if (paramIntent == null || paramInt2 != -1) {
        paramIntent = null;
      } else {
        uri = paramIntent.getData();
      } 
      this.c.onReceiveValue(uri);
      this.c = null;
      return;
    } 
    if (paramInt1 == 2) {
      if (this.d == null)
        return; 
      if (uri == null || paramInt2 != -1) {
        uri = null;
      } else {
        uri = uri.getData();
      } 
      if (uri != null) {
        this.d.onReceiveValue(new Uri[] { uri });
      } else {
        this.d.onReceiveValue(new Uri[0]);
      } 
      this.d = null;
    } 
  }
  
  public void onBackPressed() {
    if (this.t && this.b.canGoBack()) {
      runOnUiThread(new Runnable(this) {
            public void run() {
              StringBuilder stringBuilder1 = new StringBuilder();
              stringBuilder1.append("DeviceBackEvent");
              stringBuilder1.append("()");
              String str = stringBuilder1.toString();
              WebView webView = PWebViewActivity.f(this.a);
              StringBuilder stringBuilder2 = new StringBuilder();
              stringBuilder2.append("javascript:");
              stringBuilder2.append(str);
              webView.loadUrl(stringBuilder2.toString());
            }
          });
      return;
    } 
    if (this.b.canGoBack()) {
      this.b.goBack();
      return;
    } 
    super.onBackPressed();
  }
  
  public void onDestroy() {
    if (this.b != null)
      this.b.destroy(); 
    super.onDestroy();
  }
  
  public void onPause() {
    super.onPause();
    if (this.b != null)
      this.b.onPause(); 
  }
  
  public void onResume() {
    super.onResume();
    if (this.b != null)
      this.b.onResume(); 
  }
  
  public class JavaScriptInterface {
    public JavaScriptInterface(PWebViewActivity this$0) {}
    
    @JavascriptInterface
    public void back() {
      this.a.runOnUiThread(new Runnable(this) {
            public void run() {
              if (PWebViewActivity.f(this.a.a).canGoBack())
                PWebViewActivity.f(this.a.a).goBack(); 
            }
          });
    }
    
    @JavascriptInterface
    public void execute(String param1String1, String param1String2) {
      byte b;
      if (param1String1.hashCode() == -1655441078 && param1String1.equals("moduleclickinfo")) {
        b = 0;
      } else {
        b = -1;
      } 
      if (b != 0)
        return; 
      PWebViewActivity.a(this.a, param1String2);
    }
    
    @JavascriptInterface
    public void exit() {
      this.a.finish();
    }
    
    @JavascriptInterface
    public void openInAppBrowser(String param1String) {
      Intent intent = new Intent();
      intent.setAction("android.intent.action.VIEW");
      intent.setData(Uri.parse(param1String));
      this.a.startActivity(intent.addFlags(268435456));
    }
    
    @JavascriptInterface
    public void overrideBackBtn() {
      PWebViewActivity.a(this.a, true);
    }
    
    @JavascriptInterface
    public void showAlertView(String param1String) {
      this.a.runOnUiThread(new Runnable(this, param1String) {
            public void run() {
              PWebViewActivity.i(this.b.a).setText(this.a);
            }
          });
    }
    
    @JavascriptInterface
    public void showErrorView(String param1String) {
      PWebViewActivity.g(this.a);
      PWebViewActivity.h(this.a);
      if (!TextUtils.isEmpty(param1String))
        try {
          showAlertView((new JSONObject(param1String)).optString("msg", "未知错误"));
          return;
        } catch (JSONException jSONException) {
          jSONException.printStackTrace();
        }  
    }
    
    @JavascriptInterface
    public void showSuccessView() {
      PWebViewActivity.g(this.a);
      PWebViewActivity.c(this.a);
    }
  }
  
  class null implements Runnable {
    null(PWebViewActivity this$0) {}
    
    public void run() {
      if (PWebViewActivity.f(this.a.a).canGoBack())
        PWebViewActivity.f(this.a.a).goBack(); 
    }
  }
  
  class null implements Runnable {
    null(PWebViewActivity this$0, String param1String) {}
    
    public void run() {
      PWebViewActivity.i(this.b.a).setText(this.a);
    }
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\PWebViewActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */