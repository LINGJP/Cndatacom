package org.apache.cordova.engine;

import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import java.util.Arrays;
import org.apache.cordova.CordovaDialogsHelper;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.LOG;

public class SystemWebChromeClient extends WebChromeClient {
  private static final int FILECHOOSER_RESULTCODE = 5173;
  
  private static final String LOG_TAG = "SystemWebChromeClient";
  
  private long MAX_QUOTA = 104857600L;
  
  private Context appContext;
  
  private CordovaDialogsHelper dialogsHelper;
  
  private View mCustomView;
  
  private WebChromeClient.CustomViewCallback mCustomViewCallback;
  
  private View mVideoProgressView;
  
  protected final SystemWebViewEngine parentEngine;
  
  public SystemWebChromeClient(SystemWebViewEngine paramSystemWebViewEngine) {
    this.parentEngine = paramSystemWebViewEngine;
    this.appContext = paramSystemWebViewEngine.webView.getContext();
    this.dialogsHelper = new CordovaDialogsHelper(this.appContext);
  }
  
  public void destroyLastDialog() {
    this.dialogsHelper.destroyLastDialog();
  }
  
  public View getVideoLoadingProgressView() {
    if (this.mVideoProgressView == null) {
      LinearLayout linearLayout = new LinearLayout(this.parentEngine.getView().getContext());
      linearLayout.setOrientation(1);
      RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
      layoutParams.addRule(13);
      linearLayout.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
      ProgressBar progressBar = new ProgressBar(this.parentEngine.getView().getContext());
      LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(-2, -2);
      layoutParams1.gravity = 17;
      progressBar.setLayoutParams((ViewGroup.LayoutParams)layoutParams1);
      linearLayout.addView((View)progressBar);
      this.mVideoProgressView = (View)linearLayout;
    } 
    return this.mVideoProgressView;
  }
  
  public void onConsoleMessage(String paramString1, int paramInt, String paramString2) {
    if (Build.VERSION.SDK_INT == 7) {
      LOG.d("SystemWebChromeClient", "%s: Line %d : %s", new Object[] { paramString2, Integer.valueOf(paramInt), paramString1 });
      super.onConsoleMessage(paramString1, paramInt, paramString2);
    } 
  }
  
  @TargetApi(8)
  public boolean onConsoleMessage(ConsoleMessage paramConsoleMessage) {
    if (paramConsoleMessage.message() != null)
      LOG.d("SystemWebChromeClient", "%s: Line %d : %s", new Object[] { paramConsoleMessage.sourceId(), Integer.valueOf(paramConsoleMessage.lineNumber()), paramConsoleMessage.message() }); 
    return super.onConsoleMessage(paramConsoleMessage);
  }
  
  public void onExceededDatabaseQuota(String paramString1, String paramString2, long paramLong1, long paramLong2, long paramLong3, WebStorage.QuotaUpdater paramQuotaUpdater) {
    LOG.d("SystemWebChromeClient", "onExceededDatabaseQuota estimatedSize: %d  currentQuota: %d  totalUsedQuota: %d", new Object[] { Long.valueOf(paramLong2), Long.valueOf(paramLong1), Long.valueOf(paramLong3) });
    paramQuotaUpdater.updateQuota(this.MAX_QUOTA);
  }
  
  public void onGeolocationPermissionsShowPrompt(String paramString, GeolocationPermissions.Callback paramCallback) {
    super.onGeolocationPermissionsShowPrompt(paramString, paramCallback);
    paramCallback.invoke(paramString, true, false);
    CordovaPlugin cordovaPlugin = this.parentEngine.pluginManager.getPlugin("Geolocation");
    if (cordovaPlugin != null && !cordovaPlugin.hasPermisssion())
      cordovaPlugin.requestPermissions(0); 
  }
  
  public void onHideCustomView() {
    this.parentEngine.getCordovaWebView().hideCustomView();
  }
  
  public boolean onJsAlert(WebView paramWebView, String paramString1, String paramString2, final JsResult result) {
    this.dialogsHelper.showAlert(paramString2, new CordovaDialogsHelper.Result() {
          public void gotResult(boolean param1Boolean, String param1String) {
            if (param1Boolean) {
              result.confirm();
              return;
            } 
            result.cancel();
          }
        });
    return true;
  }
  
  public boolean onJsConfirm(WebView paramWebView, String paramString1, String paramString2, final JsResult result) {
    this.dialogsHelper.showConfirm(paramString2, new CordovaDialogsHelper.Result() {
          public void gotResult(boolean param1Boolean, String param1String) {
            if (param1Boolean) {
              result.confirm();
              return;
            } 
            result.cancel();
          }
        });
    return true;
  }
  
  public boolean onJsPrompt(WebView paramWebView, String paramString1, String paramString2, String paramString3, final JsPromptResult result) {
    String str = this.parentEngine.bridge.promptOnJsPrompt(paramString1, paramString2, paramString3);
    if (str != null) {
      result.confirm(str);
    } else {
      this.dialogsHelper.showPrompt(paramString2, paramString3, new CordovaDialogsHelper.Result() {
            public void gotResult(boolean param1Boolean, String param1String) {
              if (param1Boolean) {
                result.confirm(param1String);
                return;
              } 
              result.cancel();
            }
          });
    } 
    return true;
  }
  
  @TargetApi(21)
  public void onPermissionRequest(PermissionRequest paramPermissionRequest) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("onPermissionRequest: ");
    stringBuilder.append(Arrays.toString((Object[])paramPermissionRequest.getResources()));
    LOG.d("SystemWebChromeClient", stringBuilder.toString());
    paramPermissionRequest.grant(paramPermissionRequest.getResources());
  }
  
  public void onShowCustomView(View paramView, WebChromeClient.CustomViewCallback paramCustomViewCallback) {
    this.parentEngine.getCordovaWebView().showCustomView(paramView, paramCustomViewCallback);
  }
  
  @TargetApi(21)
  public boolean onShowFileChooser(WebView paramWebView, final ValueCallback<Uri[]> filePathsCallback, WebChromeClient.FileChooserParams paramFileChooserParams) {
    Intent intent = paramFileChooserParams.createIntent();
    try {
      this.parentEngine.cordova.startActivityForResult(new CordovaPlugin() {
            public void onActivityResult(int param1Int1, int param1Int2, Intent param1Intent) {
              Uri[] arrayOfUri = WebChromeClient.FileChooserParams.parseResult(param1Int2, param1Intent);
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append("Receive file chooser URL: ");
              stringBuilder.append(arrayOfUri);
              LOG.d("SystemWebChromeClient", stringBuilder.toString());
              filePathsCallback.onReceiveValue(arrayOfUri);
            }
          }intent, 5173);
    } catch (ActivityNotFoundException activityNotFoundException) {
      LOG.w("No activity found to handle file chooser intent.", (Throwable)activityNotFoundException);
      filePathsCallback.onReceiveValue(null);
    } 
    return true;
  }
  
  public void openFileChooser(ValueCallback<Uri> paramValueCallback) {
    openFileChooser(paramValueCallback, "*/*");
  }
  
  public void openFileChooser(ValueCallback<Uri> paramValueCallback, String paramString) {
    openFileChooser(paramValueCallback, paramString, null);
  }
  
  public void openFileChooser(final ValueCallback<Uri> uploadMsg, String paramString1, String paramString2) {
    Intent intent = new Intent("android.intent.action.GET_CONTENT");
    intent.addCategory("android.intent.category.OPENABLE");
    intent.setType("*/*");
    this.parentEngine.cordova.startActivityForResult(new CordovaPlugin() {
          public void onActivityResult(int param1Int1, int param1Int2, Intent param1Intent) {
            Uri uri;
            if (param1Intent == null || param1Int2 != -1) {
              param1Intent = null;
            } else {
              uri = param1Intent.getData();
            } 
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Receive file chooser URL: ");
            stringBuilder.append(uri);
            LOG.d("SystemWebChromeClient", stringBuilder.toString());
            uploadMsg.onReceiveValue(uri);
          }
        }intent, 5173);
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\org\apache\cordova\engine\SystemWebChromeClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */