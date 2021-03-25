package org.apache.cordova.inappbrowser;

import android.webkit.GeolocationPermissions;
import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebStorage;
import android.webkit.WebView;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.LOG;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

public class InAppChromeClient extends WebChromeClient {
  private String LOG_TAG = "InAppChromeClient";
  
  private long MAX_QUOTA = 104857600L;
  
  private CordovaWebView webView;
  
  public InAppChromeClient(CordovaWebView paramCordovaWebView) {
    this.webView = paramCordovaWebView;
  }
  
  public void onExceededDatabaseQuota(String paramString1, String paramString2, long paramLong1, long paramLong2, long paramLong3, WebStorage.QuotaUpdater paramQuotaUpdater) {
    LOG.d(this.LOG_TAG, "onExceededDatabaseQuota estimatedSize: %d  currentQuota: %d  totalUsedQuota: %d", new Object[] { Long.valueOf(paramLong2), Long.valueOf(paramLong1), Long.valueOf(paramLong3) });
    paramQuotaUpdater.updateQuota(this.MAX_QUOTA);
  }
  
  public void onGeolocationPermissionsShowPrompt(String paramString, GeolocationPermissions.Callback paramCallback) {
    super.onGeolocationPermissionsShowPrompt(paramString, paramCallback);
    paramCallback.invoke(paramString, true, false);
  }
  
  public boolean onJsPrompt(WebView paramWebView, String paramString1, String paramString2, String paramString3, JsPromptResult paramJsPromptResult) {
    if (paramString3 != null && paramString3.startsWith("gap"))
      if (paramString3.startsWith("gap-iab://")) {
        paramString1 = paramString3.substring(10);
        if (paramString1.startsWith("InAppBrowser")) {
          PluginResult pluginResult;
          if (paramString2 == null || paramString2.length() == 0) {
            pluginResult = new PluginResult(PluginResult.Status.OK, new JSONArray());
            this.webView.sendPluginResult(pluginResult, paramString1);
            paramJsPromptResult.confirm("");
            return true;
          } 
          try {
            pluginResult = new PluginResult(PluginResult.Status.OK, new JSONArray(paramString2));
          } catch (JSONException jSONException) {
            pluginResult = new PluginResult(PluginResult.Status.JSON_EXCEPTION, jSONException.getMessage());
          } 
          this.webView.sendPluginResult(pluginResult, paramString1);
          paramJsPromptResult.confirm("");
          return true;
        } 
      } else {
        String str = this.LOG_TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("InAppBrowser does not support Cordova API calls: ");
        stringBuilder.append(paramString1);
        stringBuilder.append(" ");
        stringBuilder.append(paramString3);
        LOG.w(str, stringBuilder.toString());
        paramJsPromptResult.cancel();
        return true;
      }  
    return false;
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\org\apache\cordova\inappbrowser\InAppChromeClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */