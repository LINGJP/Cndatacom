package org.apache.cordova;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.webkit.WebChromeClient;
import java.util.List;
import java.util.Map;

public interface CordovaWebView {
  public static final String CORDOVA_VERSION = "7.0.0";
  
  boolean backHistory();
  
  boolean canGoBack();
  
  void clearCache();
  
  @Deprecated
  void clearCache(boolean paramBoolean);
  
  void clearHistory();
  
  Context getContext();
  
  ICordovaCookieManager getCookieManager();
  
  CordovaWebViewEngine getEngine();
  
  PluginManager getPluginManager();
  
  CordovaPreferences getPreferences();
  
  CordovaResourceApi getResourceApi();
  
  String getUrl();
  
  View getView();
  
  void handleDestroy();
  
  void handlePause(boolean paramBoolean);
  
  void handleResume(boolean paramBoolean);
  
  void handleStart();
  
  void handleStop();
  
  @Deprecated
  void hideCustomView();
  
  void init(CordovaInterface paramCordovaInterface, List<PluginEntry> paramList, CordovaPreferences paramCordovaPreferences);
  
  boolean isButtonPlumbedToJs(int paramInt);
  
  @Deprecated
  boolean isCustomViewShowing();
  
  boolean isInitialized();
  
  void loadUrl(String paramString);
  
  void loadUrlIntoView(String paramString, boolean paramBoolean);
  
  void onNewIntent(Intent paramIntent);
  
  Object postMessage(String paramString, Object paramObject);
  
  @Deprecated
  void sendJavascript(String paramString);
  
  void sendPluginResult(PluginResult paramPluginResult, String paramString);
  
  void setButtonPlumbedToJs(int paramInt, boolean paramBoolean);
  
  @Deprecated
  void showCustomView(View paramView, WebChromeClient.CustomViewCallback paramCustomViewCallback);
  
  void showWebPage(String paramString, boolean paramBoolean1, boolean paramBoolean2, Map<String, Object> paramMap);
  
  void stopLoading();
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\org\apache\cordova\CordovaWebView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */