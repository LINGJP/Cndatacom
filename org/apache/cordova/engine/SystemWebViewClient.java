package org.apache.cordova.engine;

import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.webkit.ClientCertRequest;
import android.webkit.HttpAuthHandler;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.io.IOException;
import java.util.Hashtable;
import org.apache.cordova.AuthenticationToken;
import org.apache.cordova.CordovaClientCertRequest;
import org.apache.cordova.CordovaHttpAuthHandler;
import org.apache.cordova.CordovaResourceApi;
import org.apache.cordova.ICordovaClientCertRequest;
import org.apache.cordova.ICordovaHttpAuthHandler;
import org.apache.cordova.LOG;
import org.apache.cordova.PluginManager;

public class SystemWebViewClient extends WebViewClient {
  private static final String TAG = "SystemWebViewClient";
  
  private Hashtable<String, AuthenticationToken> authenticationTokens = new Hashtable<String, AuthenticationToken>();
  
  private boolean doClearHistory = false;
  
  boolean isCurrentlyLoading;
  
  protected final SystemWebViewEngine parentEngine;
  
  public SystemWebViewClient(SystemWebViewEngine paramSystemWebViewEngine) {
    this.parentEngine = paramSystemWebViewEngine;
  }
  
  private static boolean needsKitKatContentUrlFix(Uri paramUri) {
    return (Build.VERSION.SDK_INT >= 19 && "content".equals(paramUri.getScheme()));
  }
  
  private static boolean needsSpecialsInAssetUrlFix(Uri paramUri) {
    if (CordovaResourceApi.getUriType(paramUri) != 1)
      return false; 
    if (paramUri.getQuery() == null) {
      if (paramUri.getFragment() != null)
        return true; 
      if (!paramUri.toString().contains("%"))
        return false; 
      switch (Build.VERSION.SDK_INT) {
        default:
          return false;
        case 14:
        case 15:
          break;
      } 
      return true;
    } 
    return true;
  }
  
  public void clearAuthenticationTokens() {
    this.authenticationTokens.clear();
  }
  
  public AuthenticationToken getAuthenticationToken(String paramString1, String paramString2) {
    AuthenticationToken authenticationToken2 = this.authenticationTokens.get(paramString1.concat(paramString2));
    AuthenticationToken authenticationToken1 = authenticationToken2;
    if (authenticationToken2 == null) {
      authenticationToken1 = this.authenticationTokens.get(paramString1);
      AuthenticationToken authenticationToken = authenticationToken1;
      if (authenticationToken1 == null)
        authenticationToken = this.authenticationTokens.get(paramString2); 
      authenticationToken1 = authenticationToken;
      if (authenticationToken == null)
        authenticationToken1 = this.authenticationTokens.get(""); 
    } 
    return authenticationToken1;
  }
  
  public void onPageFinished(WebView paramWebView, String paramString) {
    super.onPageFinished(paramWebView, paramString);
    if (!this.isCurrentlyLoading && !paramString.startsWith("about:"))
      return; 
    this.isCurrentlyLoading = false;
    if (this.doClearHistory) {
      paramWebView.clearHistory();
      this.doClearHistory = false;
    } 
    this.parentEngine.client.onPageFinishedLoading(paramString);
  }
  
  public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap) {
    super.onPageStarted(paramWebView, paramString, paramBitmap);
    this.isCurrentlyLoading = true;
    this.parentEngine.bridge.reset();
    this.parentEngine.client.onPageStarted(paramString);
  }
  
  @TargetApi(21)
  public void onReceivedClientCertRequest(WebView paramWebView, ClientCertRequest paramClientCertRequest) {
    PluginManager pluginManager = this.parentEngine.pluginManager;
    if (pluginManager != null && pluginManager.onReceivedClientCertRequest(null, (ICordovaClientCertRequest)new CordovaClientCertRequest(paramClientCertRequest))) {
      this.parentEngine.client.clearLoadTimeoutTimer();
      return;
    } 
    super.onReceivedClientCertRequest(paramWebView, paramClientCertRequest);
  }
  
  public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2) {
    if (!this.isCurrentlyLoading)
      return; 
    LOG.d("SystemWebViewClient", "CordovaWebViewClient.onReceivedError: Error code=%s Description=%s URL=%s", new Object[] { Integer.valueOf(paramInt), paramString1, paramString2 });
    if (paramInt == -10) {
      this.parentEngine.client.clearLoadTimeoutTimer();
      if (paramWebView.canGoBack()) {
        paramWebView.goBack();
        return;
      } 
      super.onReceivedError(paramWebView, paramInt, paramString1, paramString2);
    } 
    this.parentEngine.client.onReceivedError(paramInt, paramString1, paramString2);
  }
  
  public void onReceivedHttpAuthRequest(WebView paramWebView, HttpAuthHandler paramHttpAuthHandler, String paramString1, String paramString2) {
    AuthenticationToken authenticationToken = getAuthenticationToken(paramString1, paramString2);
    if (authenticationToken != null) {
      paramHttpAuthHandler.proceed(authenticationToken.getUserName(), authenticationToken.getPassword());
      return;
    } 
    PluginManager pluginManager = this.parentEngine.pluginManager;
    if (pluginManager != null && pluginManager.onReceivedHttpAuthRequest(null, (ICordovaHttpAuthHandler)new CordovaHttpAuthHandler(paramHttpAuthHandler), paramString1, paramString2)) {
      this.parentEngine.client.clearLoadTimeoutTimer();
      return;
    } 
    super.onReceivedHttpAuthRequest(paramWebView, paramHttpAuthHandler, paramString1, paramString2);
  }
  
  @TargetApi(8)
  public void onReceivedSslError(WebView paramWebView, SslErrorHandler paramSslErrorHandler, SslError paramSslError) {
    String str = this.parentEngine.cordova.getActivity().getPackageName();
    PackageManager packageManager = this.parentEngine.cordova.getActivity().getPackageManager();
    try {
      if (((packageManager.getApplicationInfo(str, 128)).flags & 0x2) != 0) {
        paramSslErrorHandler.proceed();
        return;
      } 
      super.onReceivedSslError(paramWebView, paramSslErrorHandler, paramSslError);
      return;
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      super.onReceivedSslError(paramWebView, paramSslErrorHandler, paramSslError);
      return;
    } 
  }
  
  public AuthenticationToken removeAuthenticationToken(String paramString1, String paramString2) {
    return this.authenticationTokens.remove(paramString1.concat(paramString2));
  }
  
  public void setAuthenticationToken(AuthenticationToken paramAuthenticationToken, String paramString1, String paramString2) {
    String str = paramString1;
    if (paramString1 == null)
      str = ""; 
    paramString1 = paramString2;
    if (paramString2 == null)
      paramString1 = ""; 
    this.authenticationTokens.put(str.concat(paramString1), paramAuthenticationToken);
  }
  
  @TargetApi(11)
  public WebResourceResponse shouldInterceptRequest(WebView paramWebView, String paramString) {
    try {
      if (!this.parentEngine.pluginManager.shouldAllowRequest(paramString)) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("URL blocked by whitelist: ");
        stringBuilder.append(paramString);
        LOG.w("SystemWebViewClient", stringBuilder.toString());
        return new WebResourceResponse("text/plain", "UTF-8", null);
      } 
      CordovaResourceApi cordovaResourceApi = this.parentEngine.resourceApi;
      Uri uri1 = Uri.parse(paramString);
      Uri uri2 = cordovaResourceApi.remapUri(uri1);
      if (!uri1.equals(uri2) || needsSpecialsInAssetUrlFix(uri1) || needsKitKatContentUrlFix(uri1)) {
        CordovaResourceApi.OpenForReadResult openForReadResult = cordovaResourceApi.openForRead(uri2, true);
        return new WebResourceResponse(openForReadResult.mimeType, "UTF-8", openForReadResult.inputStream);
      } 
    } catch (IOException iOException) {
      if (!(iOException instanceof java.io.FileNotFoundException))
        LOG.e("SystemWebViewClient", "Error occurred while loading a file (returning a 404).", iOException); 
      return new WebResourceResponse("text/plain", "UTF-8", null);
    } 
    return null;
  }
  
  public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString) {
    return this.parentEngine.client.onNavigationAttempt(paramString);
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\org\apache\cordova\engine\SystemWebViewClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */