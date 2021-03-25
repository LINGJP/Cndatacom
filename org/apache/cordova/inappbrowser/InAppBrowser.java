package org.apache.cordova.inappbrowser;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.HttpAuthHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.stub.StubApp;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.Config;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaHttpAuthHandler;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaPreferences;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.ICordovaHttpAuthHandler;
import org.apache.cordova.LOG;
import org.apache.cordova.PluginManager;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"SetJavaScriptEnabled"})
public class InAppBrowser extends CordovaPlugin {
  private static final String CLEAR_ALL_CACHE = "clearcache";
  
  private static final String CLEAR_SESSION_CACHE = "clearsessioncache";
  
  private static final String CLOSE_BUTTON_CAPTION = "closebuttoncaption";
  
  private static final String CLOSE_BUTTON_COLOR = "closebuttoncolor";
  
  private static final Boolean DEFAULT_HARDWARE_BACK = Boolean.valueOf(true);
  
  private static final String EXIT_EVENT = "exit";
  
  private static final int FILECHOOSER_REQUESTCODE = 1;
  
  private static final int FILECHOOSER_REQUESTCODE_LOLLIPOP = 2;
  
  private static final String FOOTER = "footer";
  
  private static final String FOOTER_COLOR = "footercolor";
  
  private static final String HARDWARE_BACK_BUTTON = "hardwareback";
  
  private static final String HIDDEN = "hidden";
  
  private static final String HIDE_NAVIGATION = "hidenavigationbuttons";
  
  private static final String HIDE_URL = "hideurlbar";
  
  private static final String LOAD_ERROR_EVENT = "loaderror";
  
  private static final String LOAD_START_EVENT = "loadstart";
  
  private static final String LOAD_STOP_EVENT = "loadstop";
  
  private static final String LOCATION = "location";
  
  protected static final String LOG_TAG = "InAppBrowser";
  
  private static final String MEDIA_PLAYBACK_REQUIRES_USER_ACTION = "mediaPlaybackRequiresUserAction";
  
  private static final String NAVIGATION_COLOR = "navigationbuttoncolor";
  
  private static final String NULL = "null";
  
  private static final String SELF = "_self";
  
  private static final String SHOULD_PAUSE = "shouldPauseOnSuspend";
  
  private static final String SYSTEM = "_system";
  
  private static final String TOOLBAR_COLOR = "toolbarcolor";
  
  private static final String USER_WIDE_VIEW_PORT = "useWideViewPort";
  
  private static final String ZOOM = "zoom";
  
  private static final List customizableOptions = Arrays.asList(new String[] { "closebuttoncaption", "toolbarcolor", "navigationbuttoncolor", "closebuttoncolor", "footercolor" });
  
  private CallbackContext callbackContext;
  
  private boolean clearAllCache = false;
  
  private boolean clearSessionCache = false;
  
  private String closeButtonCaption = "";
  
  private String closeButtonColor = "";
  
  private InAppBrowserDialog dialog;
  
  private EditText edittext;
  
  private String footerColor = "";
  
  private boolean hadwareBackButton = true;
  
  private boolean hideNavigationButtons = false;
  
  private boolean hideUrlBar = false;
  
  private WebView inAppWebView;
  
  private ValueCallback<Uri> mUploadCallback;
  
  private ValueCallback<Uri[]> mUploadCallbackLollipop;
  
  private boolean mediaPlaybackRequiresUserGesture = false;
  
  private String navigationButtonColor = "";
  
  private boolean openWindowHidden = false;
  
  private boolean shouldPauseInAppBrowser = false;
  
  private boolean showFooter = false;
  
  private boolean showLocationBar = true;
  
  private boolean showZoomControls = true;
  
  private int toolbarColor = -3355444;
  
  private boolean useWideViewPort = true;
  
  private InAppBrowser getInAppBrowser() {
    return this;
  }
  
  private boolean getShowLocationBar() {
    return this.showLocationBar;
  }
  
  private void goForward() {
    if (this.inAppWebView.canGoForward())
      this.inAppWebView.goForward(); 
  }
  
  private void injectDeferredObject(String paramString1, String paramString2) {
    if (this.inAppWebView != null) {
      final String finalScriptToInject = paramString1;
      if (paramString2 != null) {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(paramString1);
        paramString1 = jSONArray.toString();
        str = String.format(paramString2, new Object[] { paramString1.substring(1, paramString1.length() - 1) });
      } 
      this.cordova.getActivity().runOnUiThread(new Runnable() {
            @SuppressLint({"NewApi"})
            public void run() {
              if (Build.VERSION.SDK_INT < 19) {
                WebView webView = InAppBrowser.this.inAppWebView;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("javascript:");
                stringBuilder.append(finalScriptToInject);
                webView.loadUrl(stringBuilder.toString());
                return;
              } 
              InAppBrowser.this.inAppWebView.evaluateJavascript(finalScriptToInject, null);
            }
          });
      return;
    } 
    LOG.d("InAppBrowser", "Can't inject code into the system browser");
  }
  
  private void navigate(String paramString) {
    ((InputMethodManager)this.cordova.getActivity().getSystemService("input_method")).hideSoftInputFromWindow(this.edittext.getWindowToken(), 0);
    if (!paramString.startsWith("http") && !paramString.startsWith("file:")) {
      WebView webView = this.inAppWebView;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("http://");
      stringBuilder.append(paramString);
      webView.loadUrl(stringBuilder.toString());
    } else {
      this.inAppWebView.loadUrl(paramString);
    } 
    this.inAppWebView.requestFocus();
  }
  
  private HashMap<String, String> parseFeature(String paramString) {
    if (paramString.equals("null"))
      return null; 
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    StringTokenizer stringTokenizer = new StringTokenizer(paramString, ",");
    while (stringTokenizer.hasMoreElements()) {
      StringTokenizer stringTokenizer1 = new StringTokenizer(stringTokenizer.nextToken(), "=");
      if (stringTokenizer1.hasMoreElements()) {
        String str3 = stringTokenizer1.nextToken();
        String str2 = stringTokenizer1.nextToken();
        String str1 = str2;
        if (!customizableOptions.contains(str3)) {
          str1 = str2;
          if (!str2.equals("yes"))
            if (str2.equals("no")) {
              str1 = str2;
            } else {
              str1 = "yes";
            }  
        } 
        hashMap.put(str3, str1);
      } 
    } 
    return (HashMap)hashMap;
  }
  
  private void sendUpdate(JSONObject paramJSONObject, boolean paramBoolean) {
    sendUpdate(paramJSONObject, paramBoolean, PluginResult.Status.OK);
  }
  
  private void sendUpdate(JSONObject paramJSONObject, boolean paramBoolean, PluginResult.Status paramStatus) {
    if (this.callbackContext != null) {
      PluginResult pluginResult = new PluginResult(paramStatus, paramJSONObject);
      pluginResult.setKeepCallback(paramBoolean);
      this.callbackContext.sendPluginResult(pluginResult);
      if (!paramBoolean)
        this.callbackContext = null; 
    } 
  }
  
  public boolean canGoBack() {
    return this.inAppWebView.canGoBack();
  }
  
  public void closeDialog() {
    this.cordova.getActivity().runOnUiThread(new Runnable() {
          public void run() {
            WebView webView = InAppBrowser.this.inAppWebView;
            if (webView == null)
              return; 
            webView.setWebViewClient(new WebViewClient() {
                  public void onPageFinished(WebView param2WebView, String param2String) {
                    if (InAppBrowser.this.dialog != null) {
                      InAppBrowser.this.dialog.dismiss();
                      InAppBrowser.access$002(InAppBrowser.this, (InAppBrowserDialog)null);
                    } 
                  }
                });
            webView.loadUrl("about:blank");
            try {
              JSONObject jSONObject = new JSONObject();
              jSONObject.put("type", "exit");
              InAppBrowser.this.sendUpdate(jSONObject, false);
              return;
            } catch (JSONException jSONException) {
              LOG.d("InAppBrowser", "Should never happen");
              return;
            } 
          }
        });
  }
  
  public boolean execute(final String target, CordovaArgs paramCordovaArgs, final CallbackContext callbackContext) {
    final String url;
    if (target.equals("open")) {
      this.callbackContext = callbackContext;
      str = paramCordovaArgs.getString(0);
      String str1 = paramCordovaArgs.optString(1);
      if (str1 != null && !str1.equals("")) {
        target = str1;
        if (str1.equals("null")) {
          target = "_self";
          hashMap = parseFeature(paramCordovaArgs.optString(2));
          StringBuilder stringBuilder2 = new StringBuilder();
          stringBuilder2.append("target = ");
          stringBuilder2.append(target);
          LOG.d("InAppBrowser", stringBuilder2.toString());
          this.cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                  String str1;
                  String str2 = "";
                  if ("_self".equals(target)) {
                    LOG.d("InAppBrowser", "in self");
                    if (url.startsWith("javascript:")) {
                      bool2 = Boolean.valueOf(true);
                    } else {
                      bool2 = null;
                    } 
                    Boolean bool1 = bool2;
                    if (bool2 == null)
                      try {
                        bool1 = (Boolean)Config.class.getMethod("isUrlWhiteListed", new Class[] { String.class }).invoke(null, new Object[] { this.val$url });
                      } catch (NoSuchMethodException noSuchMethodException) {
                        LOG.d("InAppBrowser", noSuchMethodException.getLocalizedMessage());
                        Boolean bool = bool2;
                      } catch (IllegalAccessException illegalAccessException) {
                        LOG.d("InAppBrowser", illegalAccessException.getLocalizedMessage());
                        Boolean bool = bool2;
                      } catch (InvocationTargetException invocationTargetException) {
                        LOG.d("InAppBrowser", invocationTargetException.getLocalizedMessage());
                        bool1 = bool2;
                      }  
                    Boolean bool2 = bool1;
                    if (bool1 == null)
                      try {
                        PluginManager pluginManager = (PluginManager)InAppBrowser.this.webView.getClass().getMethod("getPluginManager", new Class[0]).invoke(InAppBrowser.this.webView, new Object[0]);
                        Boolean bool = (Boolean)pluginManager.getClass().getMethod("shouldAllowNavigation", new Class[] { String.class }).invoke(pluginManager, new Object[] { this.val$url });
                      } catch (NoSuchMethodException noSuchMethodException) {
                        LOG.d("InAppBrowser", noSuchMethodException.getLocalizedMessage());
                        Boolean bool = bool1;
                      } catch (IllegalAccessException illegalAccessException) {
                        LOG.d("InAppBrowser", illegalAccessException.getLocalizedMessage());
                        Boolean bool = bool1;
                      } catch (InvocationTargetException invocationTargetException) {
                        LOG.d("InAppBrowser", invocationTargetException.getLocalizedMessage());
                        bool2 = bool1;
                      }  
                    if (Boolean.TRUE.equals(bool2)) {
                      LOG.d("InAppBrowser", "loading in webview");
                      InAppBrowser.this.webView.loadUrl(url);
                      str1 = str2;
                    } else if (url.startsWith("tel:")) {
                      try {
                        LOG.d("InAppBrowser", "loading in dialer");
                        Intent intent = new Intent("android.intent.action.DIAL");
                        intent.setData(Uri.parse(url));
                        InAppBrowser.this.cordova.getActivity().startActivity(intent);
                        str1 = str2;
                      } catch (ActivityNotFoundException activityNotFoundException) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Error dialing ");
                        stringBuilder.append(url);
                        stringBuilder.append(": ");
                        stringBuilder.append(activityNotFoundException.toString());
                        LOG.e("InAppBrowser", stringBuilder.toString());
                        str1 = str2;
                      } 
                    } else {
                      LOG.d("InAppBrowser", "loading in InAppBrowser");
                      str1 = InAppBrowser.this.showWebPage(url, features);
                    } 
                  } else if ("_system".equals(target)) {
                    LOG.d("InAppBrowser", "in system");
                    str1 = InAppBrowser.this.openExternal(url);
                  } else {
                    LOG.d("InAppBrowser", "in blank");
                    str1 = InAppBrowser.this.showWebPage(url, features);
                  } 
                  PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, str1);
                  pluginResult.setKeepCallback(true);
                  callbackContext.sendPluginResult(pluginResult);
                }
              });
          return true;
        } 
        hashMap = parseFeature(hashMap.optString(2));
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("target = ");
        stringBuilder1.append(target);
        LOG.d("InAppBrowser", stringBuilder1.toString());
        this.cordova.getActivity().runOnUiThread(new Runnable() {
              public void run() {
                String str1;
                String str2 = "";
                if ("_self".equals(target)) {
                  LOG.d("InAppBrowser", "in self");
                  if (url.startsWith("javascript:")) {
                    bool2 = Boolean.valueOf(true);
                  } else {
                    bool2 = null;
                  } 
                  Boolean bool1 = bool2;
                  if (bool2 == null)
                    try {
                      bool1 = (Boolean)Config.class.getMethod("isUrlWhiteListed", new Class[] { String.class }).invoke(null, new Object[] { this.val$url });
                    } catch (NoSuchMethodException noSuchMethodException) {
                      LOG.d("InAppBrowser", noSuchMethodException.getLocalizedMessage());
                      Boolean bool = bool2;
                    } catch (IllegalAccessException illegalAccessException) {
                      LOG.d("InAppBrowser", illegalAccessException.getLocalizedMessage());
                      Boolean bool = bool2;
                    } catch (InvocationTargetException invocationTargetException) {
                      LOG.d("InAppBrowser", invocationTargetException.getLocalizedMessage());
                      bool1 = bool2;
                    }  
                  Boolean bool2 = bool1;
                  if (bool1 == null)
                    try {
                      PluginManager pluginManager = (PluginManager)InAppBrowser.this.webView.getClass().getMethod("getPluginManager", new Class[0]).invoke(InAppBrowser.this.webView, new Object[0]);
                      Boolean bool = (Boolean)pluginManager.getClass().getMethod("shouldAllowNavigation", new Class[] { String.class }).invoke(pluginManager, new Object[] { this.val$url });
                    } catch (NoSuchMethodException noSuchMethodException) {
                      LOG.d("InAppBrowser", noSuchMethodException.getLocalizedMessage());
                      Boolean bool = bool1;
                    } catch (IllegalAccessException illegalAccessException) {
                      LOG.d("InAppBrowser", illegalAccessException.getLocalizedMessage());
                      Boolean bool = bool1;
                    } catch (InvocationTargetException invocationTargetException) {
                      LOG.d("InAppBrowser", invocationTargetException.getLocalizedMessage());
                      bool2 = bool1;
                    }  
                  if (Boolean.TRUE.equals(bool2)) {
                    LOG.d("InAppBrowser", "loading in webview");
                    InAppBrowser.this.webView.loadUrl(url);
                    str1 = str2;
                  } else if (url.startsWith("tel:")) {
                    try {
                      LOG.d("InAppBrowser", "loading in dialer");
                      Intent intent = new Intent("android.intent.action.DIAL");
                      intent.setData(Uri.parse(url));
                      InAppBrowser.this.cordova.getActivity().startActivity(intent);
                      str1 = str2;
                    } catch (ActivityNotFoundException activityNotFoundException) {
                      StringBuilder stringBuilder = new StringBuilder();
                      stringBuilder.append("Error dialing ");
                      stringBuilder.append(url);
                      stringBuilder.append(": ");
                      stringBuilder.append(activityNotFoundException.toString());
                      LOG.e("InAppBrowser", stringBuilder.toString());
                      str1 = str2;
                    } 
                  } else {
                    LOG.d("InAppBrowser", "loading in InAppBrowser");
                    str1 = InAppBrowser.this.showWebPage(url, features);
                  } 
                } else if ("_system".equals(target)) {
                  LOG.d("InAppBrowser", "in system");
                  str1 = InAppBrowser.this.openExternal(url);
                } else {
                  LOG.d("InAppBrowser", "in blank");
                  str1 = InAppBrowser.this.showWebPage(url, features);
                } 
                PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, str1);
                pluginResult.setKeepCallback(true);
                callbackContext.sendPluginResult(pluginResult);
              }
            });
        return true;
      } 
    } else {
      PluginResult pluginResult;
      if (target.equals("close")) {
        closeDialog();
        return true;
      } 
      if (target.equals("injectScriptCode")) {
        target = null;
        if (hashMap.getBoolean(1))
          target = String.format("(function(){prompt(JSON.stringify([eval(%%s)]), 'gap-iab://%s')})()", new Object[] { callbackContext.getCallbackId() }); 
        injectDeferredObject(hashMap.getString(0), target);
        return true;
      } 
      if (target.equals("injectScriptFile")) {
        if (hashMap.getBoolean(1)) {
          target = String.format("(function(d) { var c = d.createElement('script'); c.src = %%s; c.onload = function() { prompt('', 'gap-iab://%s'); }; d.body.appendChild(c); })(document)", new Object[] { callbackContext.getCallbackId() });
        } else {
          target = "(function(d) { var c = d.createElement('script'); c.src = %s; d.body.appendChild(c); })(document)";
        } 
        injectDeferredObject(hashMap.getString(0), target);
        return true;
      } 
      if (target.equals("injectStyleCode")) {
        if (hashMap.getBoolean(1)) {
          target = String.format("(function(d) { var c = d.createElement('style'); c.innerHTML = %%s; d.body.appendChild(c); prompt('', 'gap-iab://%s');})(document)", new Object[] { callbackContext.getCallbackId() });
        } else {
          target = "(function(d) { var c = d.createElement('style'); c.innerHTML = %s; d.body.appendChild(c); })(document)";
        } 
        injectDeferredObject(hashMap.getString(0), target);
        return true;
      } 
      if (target.equals("injectStyleFile")) {
        if (hashMap.getBoolean(1)) {
          target = String.format("(function(d) { var c = d.createElement('link'); c.rel='stylesheet'; c.type='text/css'; c.href = %%s; d.head.appendChild(c); prompt('', 'gap-iab://%s');})(document)", new Object[] { callbackContext.getCallbackId() });
        } else {
          target = "(function(d) { var c = d.createElement('link'); c.rel='stylesheet'; c.type='text/css'; c.href = %s; d.head.appendChild(c); })(document)";
        } 
        injectDeferredObject(hashMap.getString(0), target);
        return true;
      } 
      if (target.equals("show")) {
        this.cordova.getActivity().runOnUiThread(new Runnable() {
              public void run() {
                InAppBrowser.this.dialog.show();
              }
            });
        pluginResult = new PluginResult(PluginResult.Status.OK);
        pluginResult.setKeepCallback(true);
        this.callbackContext.sendPluginResult(pluginResult);
        return true;
      } 
      if (pluginResult.equals("hide")) {
        this.cordova.getActivity().runOnUiThread(new Runnable() {
              public void run() {
                InAppBrowser.this.dialog.hide();
              }
            });
        pluginResult = new PluginResult(PluginResult.Status.OK);
        pluginResult.setKeepCallback(true);
        this.callbackContext.sendPluginResult(pluginResult);
        return true;
      } 
      return false;
    } 
    target = "_self";
    final HashMap<String, String> features = parseFeature(hashMap.optString(2));
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("target = ");
    stringBuilder.append(target);
    LOG.d("InAppBrowser", stringBuilder.toString());
    this.cordova.getActivity().runOnUiThread(new Runnable() {
          public void run() {
            String str1;
            String str2 = "";
            if ("_self".equals(target)) {
              LOG.d("InAppBrowser", "in self");
              if (url.startsWith("javascript:")) {
                bool2 = Boolean.valueOf(true);
              } else {
                bool2 = null;
              } 
              Boolean bool1 = bool2;
              if (bool2 == null)
                try {
                  bool1 = (Boolean)Config.class.getMethod("isUrlWhiteListed", new Class[] { String.class }).invoke(null, new Object[] { this.val$url });
                } catch (NoSuchMethodException noSuchMethodException) {
                  LOG.d("InAppBrowser", noSuchMethodException.getLocalizedMessage());
                  Boolean bool = bool2;
                } catch (IllegalAccessException illegalAccessException) {
                  LOG.d("InAppBrowser", illegalAccessException.getLocalizedMessage());
                  Boolean bool = bool2;
                } catch (InvocationTargetException invocationTargetException) {
                  LOG.d("InAppBrowser", invocationTargetException.getLocalizedMessage());
                  bool1 = bool2;
                }  
              Boolean bool2 = bool1;
              if (bool1 == null)
                try {
                  PluginManager pluginManager = (PluginManager)InAppBrowser.this.webView.getClass().getMethod("getPluginManager", new Class[0]).invoke(InAppBrowser.this.webView, new Object[0]);
                  Boolean bool = (Boolean)pluginManager.getClass().getMethod("shouldAllowNavigation", new Class[] { String.class }).invoke(pluginManager, new Object[] { this.val$url });
                } catch (NoSuchMethodException noSuchMethodException) {
                  LOG.d("InAppBrowser", noSuchMethodException.getLocalizedMessage());
                  Boolean bool = bool1;
                } catch (IllegalAccessException illegalAccessException) {
                  LOG.d("InAppBrowser", illegalAccessException.getLocalizedMessage());
                  Boolean bool = bool1;
                } catch (InvocationTargetException invocationTargetException) {
                  LOG.d("InAppBrowser", invocationTargetException.getLocalizedMessage());
                  bool2 = bool1;
                }  
              if (Boolean.TRUE.equals(bool2)) {
                LOG.d("InAppBrowser", "loading in webview");
                InAppBrowser.this.webView.loadUrl(url);
                str1 = str2;
              } else if (url.startsWith("tel:")) {
                try {
                  LOG.d("InAppBrowser", "loading in dialer");
                  Intent intent = new Intent("android.intent.action.DIAL");
                  intent.setData(Uri.parse(url));
                  InAppBrowser.this.cordova.getActivity().startActivity(intent);
                  str1 = str2;
                } catch (ActivityNotFoundException activityNotFoundException) {
                  StringBuilder stringBuilder = new StringBuilder();
                  stringBuilder.append("Error dialing ");
                  stringBuilder.append(url);
                  stringBuilder.append(": ");
                  stringBuilder.append(activityNotFoundException.toString());
                  LOG.e("InAppBrowser", stringBuilder.toString());
                  str1 = str2;
                } 
              } else {
                LOG.d("InAppBrowser", "loading in InAppBrowser");
                str1 = InAppBrowser.this.showWebPage(url, features);
              } 
            } else if ("_system".equals(target)) {
              LOG.d("InAppBrowser", "in system");
              str1 = InAppBrowser.this.openExternal(url);
            } else {
              LOG.d("InAppBrowser", "in blank");
              str1 = InAppBrowser.this.showWebPage(url, features);
            } 
            PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, str1);
            pluginResult.setKeepCallback(true);
            callbackContext.sendPluginResult(pluginResult);
          }
        });
    return true;
  }
  
  public void goBack() {
    if (this.inAppWebView.canGoBack())
      this.inAppWebView.goBack(); 
  }
  
  public boolean hardwareBack() {
    return this.hadwareBackButton;
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
    // Byte code:
    //   0: getstatic android/os/Build$VERSION.SDK_INT : I
    //   3: bipush #21
    //   5: if_icmplt -> 59
    //   8: ldc 'InAppBrowser'
    //   10: ldc_w 'onActivityResult (For Android >= 5.0)'
    //   13: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)V
    //   16: iload_1
    //   17: iconst_2
    //   18: if_icmpne -> 51
    //   21: aload_0
    //   22: getfield mUploadCallbackLollipop : Landroid/webkit/ValueCallback;
    //   25: ifnonnull -> 31
    //   28: goto -> 51
    //   31: aload_0
    //   32: getfield mUploadCallbackLollipop : Landroid/webkit/ValueCallback;
    //   35: iload_2
    //   36: aload_3
    //   37: invokestatic parseResult : (ILandroid/content/Intent;)[Landroid/net/Uri;
    //   40: invokeinterface onReceiveValue : (Ljava/lang/Object;)V
    //   45: aload_0
    //   46: aconst_null
    //   47: putfield mUploadCallbackLollipop : Landroid/webkit/ValueCallback;
    //   50: return
    //   51: aload_0
    //   52: iload_1
    //   53: iload_2
    //   54: aload_3
    //   55: invokespecial onActivityResult : (IILandroid/content/Intent;)V
    //   58: return
    //   59: ldc 'InAppBrowser'
    //   61: ldc_w 'onActivityResult (For Android < 5.0)'
    //   64: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)V
    //   67: iload_1
    //   68: iconst_1
    //   69: if_icmpne -> 138
    //   72: aload_0
    //   73: getfield mUploadCallback : Landroid/webkit/ValueCallback;
    //   76: ifnonnull -> 82
    //   79: goto -> 138
    //   82: aload_0
    //   83: getfield mUploadCallback : Landroid/webkit/ValueCallback;
    //   86: ifnonnull -> 90
    //   89: return
    //   90: aload_3
    //   91: ifnull -> 120
    //   94: aload_0
    //   95: getfield cordova : Lorg/apache/cordova/CordovaInterface;
    //   98: invokeinterface getActivity : ()Landroid/app/Activity;
    //   103: pop
    //   104: iload_2
    //   105: iconst_m1
    //   106: if_icmpeq -> 112
    //   109: goto -> 120
    //   112: aload_3
    //   113: invokevirtual getData : ()Landroid/net/Uri;
    //   116: astore_3
    //   117: goto -> 122
    //   120: aconst_null
    //   121: astore_3
    //   122: aload_0
    //   123: getfield mUploadCallback : Landroid/webkit/ValueCallback;
    //   126: aload_3
    //   127: invokeinterface onReceiveValue : (Ljava/lang/Object;)V
    //   132: aload_0
    //   133: aconst_null
    //   134: putfield mUploadCallback : Landroid/webkit/ValueCallback;
    //   137: return
    //   138: aload_0
    //   139: iload_1
    //   140: iload_2
    //   141: aload_3
    //   142: invokespecial onActivityResult : (IILandroid/content/Intent;)V
    //   145: return
  }
  
  public void onDestroy() {
    closeDialog();
  }
  
  public void onPause(boolean paramBoolean) {
    if (this.shouldPauseInAppBrowser)
      this.inAppWebView.onPause(); 
  }
  
  public void onReset() {
    closeDialog();
  }
  
  public void onResume(boolean paramBoolean) {
    if (this.shouldPauseInAppBrowser)
      this.inAppWebView.onResume(); 
  }
  
  public String openExternal(String paramString) {
    try {
      Intent intent = new Intent("android.intent.action.VIEW");
      Uri uri = Uri.parse(paramString);
      if ("file".equals(uri.getScheme())) {
        intent.setDataAndType(uri, this.webView.getResourceApi().getMimeType(uri));
      } else {
        intent.setData(uri);
      } 
      intent.putExtra("com.android.browser.application_id", this.cordova.getActivity().getPackageName());
      this.cordova.getActivity().startActivity(intent);
      return "";
    } catch (RuntimeException runtimeException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("InAppBrowser: Error loading url ");
      stringBuilder.append(paramString);
      stringBuilder.append(":");
      stringBuilder.append(runtimeException.toString());
      LOG.d("InAppBrowser", stringBuilder.toString());
      return runtimeException.toString();
    } 
  }
  
  public String showWebPage(final String url, HashMap<String, String> paramHashMap) {
    this.showLocationBar = true;
    this.showZoomControls = true;
    this.openWindowHidden = false;
    this.mediaPlaybackRequiresUserGesture = false;
    if (paramHashMap != null) {
      String str2 = paramHashMap.get("location");
      if (str2 != null)
        this.showLocationBar = str2.equals("yes"); 
      if (this.showLocationBar) {
        str2 = paramHashMap.get("hidenavigationbuttons");
        String str = paramHashMap.get("hideurlbar");
        if (str2 != null)
          this.hideNavigationButtons = str2.equals("yes"); 
        if (str != null)
          this.hideUrlBar = str.equals("yes"); 
      } 
      str2 = paramHashMap.get("zoom");
      if (str2 != null)
        this.showZoomControls = str2.equals("yes"); 
      str2 = paramHashMap.get("hidden");
      if (str2 != null)
        this.openWindowHidden = str2.equals("yes"); 
      str2 = paramHashMap.get("hardwareback");
      if (str2 != null) {
        this.hadwareBackButton = str2.equals("yes");
      } else {
        this.hadwareBackButton = DEFAULT_HARDWARE_BACK.booleanValue();
      } 
      str2 = paramHashMap.get("mediaPlaybackRequiresUserAction");
      if (str2 != null)
        this.mediaPlaybackRequiresUserGesture = str2.equals("yes"); 
      str2 = paramHashMap.get("clearcache");
      if (str2 != null) {
        this.clearAllCache = str2.equals("yes");
      } else {
        str2 = paramHashMap.get("clearsessioncache");
        if (str2 != null)
          this.clearSessionCache = str2.equals("yes"); 
      } 
      str2 = paramHashMap.get("shouldPauseOnSuspend");
      if (str2 != null)
        this.shouldPauseInAppBrowser = str2.equals("yes"); 
      str2 = paramHashMap.get("useWideViewPort");
      if (str2 != null)
        this.useWideViewPort = str2.equals("yes"); 
      str2 = paramHashMap.get("closebuttoncaption");
      if (str2 != null)
        this.closeButtonCaption = str2; 
      str2 = paramHashMap.get("closebuttoncolor");
      if (str2 != null)
        this.closeButtonColor = str2; 
      str2 = paramHashMap.get("toolbarcolor");
      if (str2 != null)
        this.toolbarColor = Color.parseColor(str2); 
      str2 = paramHashMap.get("navigationbuttoncolor");
      if (str2 != null)
        this.navigationButtonColor = str2; 
      str2 = paramHashMap.get("footer");
      if (str2 != null)
        this.showFooter = str2.equals("yes"); 
      String str1 = paramHashMap.get("footercolor");
      if (str1 != null)
        this.footerColor = str1; 
    } 
    Runnable runnable = new Runnable() {
        private View createCloseButton(int param1Int) {
          ImageButton imageButton;
          Resources resources = InAppBrowser.this.cordova.getActivity().getResources();
          if (InAppBrowser.this.closeButtonCaption != "") {
            TextView textView = new TextView((Context)InAppBrowser.this.cordova.getActivity());
            textView.setText(InAppBrowser.this.closeButtonCaption);
            textView.setTextSize(20.0F);
            if (InAppBrowser.this.closeButtonColor != "")
              textView.setTextColor(Color.parseColor(InAppBrowser.this.closeButtonColor)); 
            textView.setGravity(16);
            textView.setPadding(dpToPixels(10), 0, dpToPixels(10), 0);
          } else {
            imageButton = new ImageButton((Context)InAppBrowser.this.cordova.getActivity());
            Drawable drawable = resources.getDrawable(resources.getIdentifier("ic_action_remove", "drawable", InAppBrowser.this.cordova.getActivity().getPackageName()));
            if (InAppBrowser.this.closeButtonColor != "")
              imageButton.setColorFilter(Color.parseColor(InAppBrowser.this.closeButtonColor)); 
            imageButton.setImageDrawable(drawable);
            imageButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
            if (Build.VERSION.SDK_INT >= 16)
              imageButton.getAdjustViewBounds(); 
          } 
          RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -1);
          layoutParams.addRule(11);
          imageButton.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
          if (Build.VERSION.SDK_INT >= 16) {
            imageButton.setBackground(null);
          } else {
            imageButton.setBackgroundDrawable(null);
          } 
          imageButton.setContentDescription("Close Button");
          imageButton.setId(Integer.valueOf(param1Int).intValue());
          imageButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View param2View) {
                  InAppBrowser.this.closeDialog();
                }
              });
          return (View)imageButton;
        }
        
        private int dpToPixels(int param1Int) {
          return (int)TypedValue.applyDimension(1, param1Int, InAppBrowser.this.cordova.getActivity().getResources().getDisplayMetrics());
        }
        
        @SuppressLint({"NewApi"})
        public void run() {
          int i;
          boolean bool;
          if (InAppBrowser.this.dialog != null)
            InAppBrowser.this.dialog.dismiss(); 
          InAppBrowser.access$002(InAppBrowser.this, new InAppBrowserDialog((Context)InAppBrowser.this.cordova.getActivity(), 16973830));
          (InAppBrowser.this.dialog.getWindow().getAttributes()).windowAnimations = 16973826;
          InAppBrowser.this.dialog.requestWindowFeature(1);
          InAppBrowser.this.dialog.setCancelable(true);
          InAppBrowser.this.dialog.setInAppBroswer(InAppBrowser.this.getInAppBrowser());
          LinearLayout linearLayout = new LinearLayout((Context)InAppBrowser.this.cordova.getActivity());
          linearLayout.setOrientation(1);
          RelativeLayout relativeLayout1 = new RelativeLayout((Context)InAppBrowser.this.cordova.getActivity());
          relativeLayout1.setBackgroundColor(InAppBrowser.this.toolbarColor);
          relativeLayout1.setLayoutParams((ViewGroup.LayoutParams)new RelativeLayout.LayoutParams(-1, dpToPixels(44)));
          relativeLayout1.setPadding(dpToPixels(2), dpToPixels(2), dpToPixels(2), dpToPixels(2));
          relativeLayout1.setHorizontalGravity(3);
          relativeLayout1.setVerticalGravity(48);
          RelativeLayout relativeLayout2 = new RelativeLayout((Context)InAppBrowser.this.cordova.getActivity());
          relativeLayout2.setLayoutParams((ViewGroup.LayoutParams)new RelativeLayout.LayoutParams(-2, -2));
          relativeLayout2.setHorizontalGravity(3);
          relativeLayout2.setVerticalGravity(16);
          relativeLayout2.setId(Integer.valueOf(1).intValue());
          ImageButton imageButton1 = new ImageButton((Context)InAppBrowser.this.cordova.getActivity());
          RelativeLayout.LayoutParams layoutParams1 = new RelativeLayout.LayoutParams(-2, -1);
          layoutParams1.addRule(5);
          imageButton1.setLayoutParams((ViewGroup.LayoutParams)layoutParams1);
          imageButton1.setContentDescription("Back Button");
          imageButton1.setId(Integer.valueOf(2).intValue());
          Resources resources = InAppBrowser.this.cordova.getActivity().getResources();
          Drawable drawable1 = resources.getDrawable(resources.getIdentifier("ic_action_previous_item", "drawable", InAppBrowser.this.cordova.getActivity().getPackageName()));
          if (InAppBrowser.this.navigationButtonColor != "")
            imageButton1.setColorFilter(Color.parseColor(InAppBrowser.this.navigationButtonColor)); 
          if (Build.VERSION.SDK_INT >= 16) {
            imageButton1.setBackground(null);
          } else {
            imageButton1.setBackgroundDrawable(null);
          } 
          imageButton1.setImageDrawable(drawable1);
          imageButton1.setScaleType(ImageView.ScaleType.FIT_CENTER);
          imageButton1.setPadding(0, dpToPixels(10), 0, dpToPixels(10));
          if (Build.VERSION.SDK_INT >= 16)
            imageButton1.getAdjustViewBounds(); 
          imageButton1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View param2View) {
                  InAppBrowser.this.goBack();
                }
              });
          ImageButton imageButton2 = new ImageButton((Context)InAppBrowser.this.cordova.getActivity());
          RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -1);
          layoutParams3.addRule(1, 2);
          imageButton2.setLayoutParams((ViewGroup.LayoutParams)layoutParams3);
          imageButton2.setContentDescription("Forward Button");
          imageButton2.setId(Integer.valueOf(3).intValue());
          Drawable drawable2 = resources.getDrawable(resources.getIdentifier("ic_action_next_item", "drawable", InAppBrowser.this.cordova.getActivity().getPackageName()));
          if (InAppBrowser.this.navigationButtonColor != "")
            imageButton2.setColorFilter(Color.parseColor(InAppBrowser.this.navigationButtonColor)); 
          if (Build.VERSION.SDK_INT >= 16) {
            imageButton2.setBackground(null);
          } else {
            imageButton2.setBackgroundDrawable(null);
          } 
          imageButton2.setImageDrawable(drawable2);
          imageButton2.setScaleType(ImageView.ScaleType.FIT_CENTER);
          imageButton2.setPadding(0, dpToPixels(10), 0, dpToPixels(10));
          if (Build.VERSION.SDK_INT >= 16)
            imageButton2.getAdjustViewBounds(); 
          imageButton2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View param2View) {
                  InAppBrowser.this.goForward();
                }
              });
          InAppBrowser.access$902(InAppBrowser.this, new EditText((Context)InAppBrowser.this.cordova.getActivity()));
          RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
          layoutParams2.addRule(1, 1);
          layoutParams2.addRule(0, 5);
          InAppBrowser.this.edittext.setLayoutParams((ViewGroup.LayoutParams)layoutParams2);
          InAppBrowser.this.edittext.setId(Integer.valueOf(4).intValue());
          InAppBrowser.this.edittext.setSingleLine(true);
          InAppBrowser.this.edittext.setText(url);
          InAppBrowser.this.edittext.setInputType(16);
          InAppBrowser.this.edittext.setImeOptions(2);
          InAppBrowser.this.edittext.setInputType(0);
          InAppBrowser.this.edittext.setOnKeyListener(new View.OnKeyListener() {
                public boolean onKey(View param2View, int param2Int, KeyEvent param2KeyEvent) {
                  if (param2KeyEvent.getAction() == 0 && param2Int == 66) {
                    InAppBrowser.this.navigate(InAppBrowser.this.edittext.getText().toString());
                    return true;
                  } 
                  return false;
                }
              });
          relativeLayout1.addView(createCloseButton(5));
          RelativeLayout relativeLayout3 = new RelativeLayout((Context)InAppBrowser.this.cordova.getActivity());
          if (InAppBrowser.this.footerColor != "") {
            i = Color.parseColor(InAppBrowser.this.footerColor);
          } else {
            i = -3355444;
          } 
          relativeLayout3.setBackgroundColor(i);
          layoutParams3 = new RelativeLayout.LayoutParams(-1, dpToPixels(44));
          layoutParams3.addRule(12, -1);
          relativeLayout3.setLayoutParams((ViewGroup.LayoutParams)layoutParams3);
          if (InAppBrowser.this.closeButtonCaption != "")
            relativeLayout3.setPadding(dpToPixels(8), dpToPixels(8), dpToPixels(8), dpToPixels(8)); 
          relativeLayout3.setHorizontalGravity(3);
          relativeLayout3.setVerticalGravity(80);
          relativeLayout3.addView(createCloseButton(7));
          InAppBrowser.access$102(InAppBrowser.this, new WebView((Context)InAppBrowser.this.cordova.getActivity()));
          InAppBrowser.this.inAppWebView.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -1));
          InAppBrowser.this.inAppWebView.setId(Integer.valueOf(6).intValue());
          InAppBrowser.this.inAppWebView.setWebChromeClient(new InAppChromeClient(thatWebView) {
                public boolean onShowFileChooser(WebView param2WebView, ValueCallback<Uri[]> param2ValueCallback, WebChromeClient.FileChooserParams param2FileChooserParams) {
                  LOG.d("InAppBrowser", "File Chooser 5.0+");
                  if (InAppBrowser.this.mUploadCallbackLollipop != null)
                    InAppBrowser.this.mUploadCallbackLollipop.onReceiveValue(null); 
                  InAppBrowser.access$1202(InAppBrowser.this, param2ValueCallback);
                  Intent intent = new Intent("android.intent.action.GET_CONTENT");
                  intent.addCategory("android.intent.category.OPENABLE");
                  intent.setType("*/*");
                  InAppBrowser.this.cordova.startActivityForResult(InAppBrowser.this, Intent.createChooser(intent, "Select File"), 2);
                  return true;
                }
                
                public void openFileChooser(ValueCallback<Uri> param2ValueCallback, String param2String) {
                  LOG.d("InAppBrowser", "File Chooser 3.0+");
                  InAppBrowser.access$1302(InAppBrowser.this, param2ValueCallback);
                  Intent intent = new Intent("android.intent.action.GET_CONTENT");
                  intent.addCategory("android.intent.category.OPENABLE");
                  InAppBrowser.this.cordova.startActivityForResult(InAppBrowser.this, Intent.createChooser(intent, "Select File"), 1);
                }
                
                public void openFileChooser(ValueCallback<Uri> param2ValueCallback, String param2String1, String param2String2) {
                  LOG.d("InAppBrowser", "File Chooser 4.1+");
                  openFileChooser(param2ValueCallback, param2String1);
                }
              });
          InAppBrowser.InAppBrowserClient inAppBrowserClient = new InAppBrowser.InAppBrowserClient(thatWebView, InAppBrowser.this.edittext);
          InAppBrowser.this.inAppWebView.setWebViewClient(inAppBrowserClient);
          WebSettings webSettings = InAppBrowser.this.inAppWebView.getSettings();
          webSettings.setJavaScriptEnabled(true);
          webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
          webSettings.setBuiltInZoomControls(InAppBrowser.this.showZoomControls);
          webSettings.setPluginState(WebSettings.PluginState.ON);
          if (Build.VERSION.SDK_INT >= 17)
            webSettings.setMediaPlaybackRequiresUserGesture(InAppBrowser.this.mediaPlaybackRequiresUserGesture); 
          String str2 = InAppBrowser.this.preferences.getString("OverrideUserAgent", null);
          String str1 = InAppBrowser.this.preferences.getString("AppendUserAgent", null);
          if (str2 != null)
            webSettings.setUserAgentString(str2); 
          if (str1 != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(webSettings.getUserAgentString());
            stringBuilder.append(str1);
            webSettings.setUserAgentString(stringBuilder.toString());
          } 
          Bundle bundle = InAppBrowser.this.cordova.getActivity().getIntent().getExtras();
          if (bundle == null) {
            bool = true;
          } else {
            bool = bundle.getBoolean("InAppBrowserStorageEnabled", true);
          } 
          if (bool) {
            webSettings.setDatabasePath(StubApp.getOrigApplicationContext(InAppBrowser.this.cordova.getActivity().getApplicationContext()).getDir("inAppBrowserDB", 0).getPath());
            webSettings.setDatabaseEnabled(true);
          } 
          webSettings.setDomStorageEnabled(true);
          if (InAppBrowser.this.clearAllCache) {
            CookieManager.getInstance().removeAllCookie();
          } else if (InAppBrowser.this.clearSessionCache) {
            CookieManager.getInstance().removeSessionCookie();
          } 
          if (Build.VERSION.SDK_INT >= 21)
            CookieManager.getInstance().setAcceptThirdPartyCookies(InAppBrowser.this.inAppWebView, true); 
          InAppBrowser.this.inAppWebView.loadUrl(url);
          InAppBrowser.this.inAppWebView.setId(Integer.valueOf(6).intValue());
          InAppBrowser.this.inAppWebView.getSettings().setLoadWithOverviewMode(true);
          InAppBrowser.this.inAppWebView.getSettings().setUseWideViewPort(InAppBrowser.this.useWideViewPort);
          InAppBrowser.this.inAppWebView.requestFocus();
          InAppBrowser.this.inAppWebView.requestFocusFromTouch();
          relativeLayout2.addView((View)imageButton1);
          relativeLayout2.addView((View)imageButton2);
          if (!InAppBrowser.this.hideNavigationButtons)
            relativeLayout1.addView((View)relativeLayout2); 
          if (!InAppBrowser.this.hideUrlBar)
            relativeLayout1.addView((View)InAppBrowser.this.edittext); 
          if (InAppBrowser.this.getShowLocationBar())
            linearLayout.addView((View)relativeLayout1); 
          relativeLayout1 = new RelativeLayout((Context)InAppBrowser.this.cordova.getActivity());
          relativeLayout1.addView((View)InAppBrowser.this.inAppWebView);
          linearLayout.addView((View)relativeLayout1);
          if (InAppBrowser.this.showFooter)
            relativeLayout1.addView((View)relativeLayout3); 
          WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
          layoutParams.copyFrom(InAppBrowser.this.dialog.getWindow().getAttributes());
          layoutParams.width = -1;
          layoutParams.height = -1;
          InAppBrowser.this.dialog.setContentView((View)linearLayout);
          InAppBrowser.this.dialog.show();
          InAppBrowser.this.dialog.getWindow().setAttributes(layoutParams);
          if (InAppBrowser.this.openWindowHidden)
            InAppBrowser.this.dialog.hide(); 
        }
      };
    this.cordova.getActivity().runOnUiThread(runnable);
    return "";
  }
  
  public class InAppBrowserClient extends WebViewClient {
    EditText edittext;
    
    CordovaWebView webView;
    
    public InAppBrowserClient(CordovaWebView param1CordovaWebView, EditText param1EditText) {
      this.webView = param1CordovaWebView;
      this.edittext = param1EditText;
    }
    
    public void onPageFinished(WebView param1WebView, String param1String) {
      super.onPageFinished(param1WebView, param1String);
      if (Build.VERSION.SDK_INT >= 21) {
        CookieManager.getInstance().flush();
      } else {
        CookieSyncManager.getInstance().sync();
      } 
      param1WebView.clearFocus();
      param1WebView.requestFocus();
      try {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("type", "loadstop");
        jSONObject.put("url", param1String);
        InAppBrowser.this.sendUpdate(jSONObject, true);
        return;
      } catch (JSONException jSONException) {
        LOG.d("InAppBrowser", "Should never happen");
        return;
      } 
    }
    
    public void onPageStarted(WebView param1WebView, String param1String, Bitmap param1Bitmap) {
      super.onPageStarted(param1WebView, param1String, param1Bitmap);
      String str = param1String;
      if (!param1String.startsWith("http:")) {
        str = param1String;
        if (!param1String.startsWith("https:"))
          if (param1String.startsWith("file:")) {
            str = param1String;
          } else {
            LOG.e("InAppBrowser", "Possible Uncaught/Unknown URI");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("http://");
            stringBuilder.append(param1String);
            str = stringBuilder.toString();
          }  
      } 
      if (!str.equals(this.edittext.getText().toString()))
        this.edittext.setText(str); 
      try {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("type", "loadstart");
        jSONObject.put("url", str);
        InAppBrowser.this.sendUpdate(jSONObject, true);
        return;
      } catch (JSONException jSONException) {
        LOG.e("InAppBrowser", "URI passed in has caused a JSON error.");
        return;
      } 
    }
    
    public void onReceivedError(WebView param1WebView, int param1Int, String param1String1, String param1String2) {
      super.onReceivedError(param1WebView, param1Int, param1String1, param1String2);
      try {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("type", "loaderror");
        jSONObject.put("url", param1String2);
        jSONObject.put("code", param1Int);
        jSONObject.put("message", param1String1);
        InAppBrowser.this.sendUpdate(jSONObject, true, PluginResult.Status.ERROR);
        return;
      } catch (JSONException jSONException) {
        LOG.d("InAppBrowser", "Should never happen");
        return;
      } 
    }
    
    public void onReceivedHttpAuthRequest(WebView param1WebView, HttpAuthHandler param1HttpAuthHandler, String param1String1, String param1String2) {
      try {
        PluginManager pluginManager = (PluginManager)this.webView.getClass().getMethod("getPluginManager", new Class[0]).invoke(this.webView, new Object[0]);
      } catch (NoSuchMethodException noSuchMethodException) {
        LOG.d("InAppBrowser", noSuchMethodException.getLocalizedMessage());
        noSuchMethodException = null;
      } catch (IllegalAccessException illegalAccessException) {
        LOG.d("InAppBrowser", illegalAccessException.getLocalizedMessage());
      } catch (InvocationTargetException invocationTargetException1) {
        LOG.d("InAppBrowser", invocationTargetException1.getLocalizedMessage());
      } 
      InvocationTargetException invocationTargetException2 = invocationTargetException1;
      if (invocationTargetException1 == null)
        try {
          PluginManager pluginManager = (PluginManager)this.webView.getClass().getField("pluginManager").get(this.webView);
        } catch (NoSuchFieldException noSuchFieldException) {
          LOG.d("InAppBrowser", noSuchFieldException.getLocalizedMessage());
          InvocationTargetException invocationTargetException = invocationTargetException1;
        } catch (IllegalAccessException illegalAccessException) {
          LOG.d("InAppBrowser", illegalAccessException.getLocalizedMessage());
          invocationTargetException2 = invocationTargetException1;
        }  
      if (invocationTargetException2 != null && invocationTargetException2.onReceivedHttpAuthRequest(this.webView, (ICordovaHttpAuthHandler)new CordovaHttpAuthHandler(param1HttpAuthHandler), param1String1, param1String2))
        return; 
      super.onReceivedHttpAuthRequest(param1WebView, param1HttpAuthHandler, param1String1, param1String2);
    }
    
    public boolean shouldOverrideUrlLoading(WebView param1WebView, String param1String) {
      if (param1String.startsWith("tel:")) {
        try {
          Intent intent = new Intent("android.intent.action.DIAL");
          intent.setData(Uri.parse(param1String));
          InAppBrowser.this.cordova.getActivity().startActivity(intent);
          return true;
        } catch (ActivityNotFoundException activityNotFoundException) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Error dialing ");
          stringBuilder.append(param1String);
          stringBuilder.append(": ");
          stringBuilder.append(activityNotFoundException.toString());
          LOG.e("InAppBrowser", stringBuilder.toString());
        } 
      } else {
        if (param1String.startsWith("geo:") || param1String.startsWith("mailto:") || param1String.startsWith("market:") || param1String.startsWith("intent:")) {
          try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(param1String));
            InAppBrowser.this.cordova.getActivity().startActivity(intent);
            return true;
          } catch (ActivityNotFoundException activityNotFoundException) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Error with ");
            stringBuilder.append(param1String);
            stringBuilder.append(": ");
            stringBuilder.append(activityNotFoundException.toString());
            LOG.e("InAppBrowser", stringBuilder.toString());
          } 
          return false;
        } 
        if (param1String.startsWith("sms:"))
          try {
            String str;
            Intent intent = new Intent("android.intent.action.VIEW");
            int i = param1String.indexOf('?');
            if (i == -1) {
              str = param1String.substring(4);
            } else {
              String str1 = param1String.substring(4, i);
              String str2 = Uri.parse(param1String).getQuery();
              str = str1;
              if (str2 != null) {
                str = str1;
                if (str2.startsWith("body=")) {
                  intent.putExtra("sms_body", str2.substring(5));
                  str = str1;
                } 
              } 
            } 
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("sms:");
            stringBuilder.append(str);
            intent.setData(Uri.parse(stringBuilder.toString()));
            intent.putExtra("address", str);
            intent.setType("vnd.android-dir/mms-sms");
            InAppBrowser.this.cordova.getActivity().startActivity(intent);
            return true;
          } catch (ActivityNotFoundException activityNotFoundException) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Error sending sms ");
            stringBuilder.append(param1String);
            stringBuilder.append(":");
            stringBuilder.append(activityNotFoundException.toString());
            LOG.e("InAppBrowser", stringBuilder.toString());
          }  
      } 
      return false;
    }
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\org\apache\cordova\inappbrowser\InAppBrowser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */