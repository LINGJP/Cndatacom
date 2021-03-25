package org.apache.cordova;

import android.view.KeyEvent;
import android.view.View;
import android.webkit.ValueCallback;

public interface CordovaWebViewEngine {
  boolean canGoBack();
  
  void clearCache();
  
  void clearHistory();
  
  void destroy();
  
  void evaluateJavascript(String paramString, ValueCallback<String> paramValueCallback);
  
  ICordovaCookieManager getCookieManager();
  
  CordovaWebView getCordovaWebView();
  
  String getUrl();
  
  View getView();
  
  boolean goBack();
  
  void init(CordovaWebView paramCordovaWebView, CordovaInterface paramCordovaInterface, Client paramClient, CordovaResourceApi paramCordovaResourceApi, PluginManager paramPluginManager, NativeToJsMessageQueue paramNativeToJsMessageQueue);
  
  void loadUrl(String paramString, boolean paramBoolean);
  
  void setPaused(boolean paramBoolean);
  
  void stopLoading();
  
  public static interface Client {
    void clearLoadTimeoutTimer();
    
    Boolean onDispatchKeyEvent(KeyEvent param1KeyEvent);
    
    boolean onNavigationAttempt(String param1String);
    
    void onPageFinishedLoading(String param1String);
    
    void onPageStarted(String param1String);
    
    void onReceivedError(int param1Int, String param1String1, String param1String2);
  }
  
  public static interface EngineView {
    CordovaWebView getCordovaWebView();
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\org\apache\cordova\CordovaWebViewEngine.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */