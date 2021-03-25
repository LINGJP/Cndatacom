package org.apache.cordova.engine;

import android.webkit.JavascriptInterface;
import org.apache.cordova.CordovaBridge;
import org.apache.cordova.ExposedJsApi;

class SystemExposedJsApi implements ExposedJsApi {
  private final CordovaBridge bridge;
  
  SystemExposedJsApi(CordovaBridge paramCordovaBridge) {
    this.bridge = paramCordovaBridge;
  }
  
  @JavascriptInterface
  public String exec(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4) {
    return this.bridge.jsExec(paramInt, paramString1, paramString2, paramString3, paramString4);
  }
  
  @JavascriptInterface
  public String retrieveJsMessages(int paramInt, boolean paramBoolean) {
    return this.bridge.jsRetrieveJsMessages(paramInt, paramBoolean);
  }
  
  @JavascriptInterface
  public void setNativeToJsBridgeMode(int paramInt1, int paramInt2) {
    this.bridge.jsSetNativeToJsBridgeMode(paramInt1, paramInt2);
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\org\apache\cordova\engine\SystemExposedJsApi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */