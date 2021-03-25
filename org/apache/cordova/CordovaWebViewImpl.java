package org.apache.cordova;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.widget.FrameLayout;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.cordova.engine.SystemWebViewEngine;
import org.json.JSONException;
import org.json.JSONObject;

public class CordovaWebViewImpl implements CordovaWebView {
  public static final String TAG = "CordovaWebViewImpl";
  
  private CoreAndroid appPlugin;
  
  private Set<Integer> boundKeyCodes = new HashSet<Integer>();
  
  private CordovaInterface cordova;
  
  protected final CordovaWebViewEngine engine;
  
  private EngineClient engineClient = new EngineClient();
  
  private boolean hasPausedEver;
  
  private int loadUrlTimeout = 0;
  
  String loadedUrl;
  
  private View mCustomView;
  
  private WebChromeClient.CustomViewCallback mCustomViewCallback;
  
  private NativeToJsMessageQueue nativeToJsMessageQueue;
  
  private PluginManager pluginManager;
  
  private CordovaPreferences preferences;
  
  private CordovaResourceApi resourceApi;
  
  public CordovaWebViewImpl(CordovaWebViewEngine paramCordovaWebViewEngine) {
    this.engine = paramCordovaWebViewEngine;
  }
  
  public static CordovaWebViewEngine createEngine(Context paramContext, CordovaPreferences paramCordovaPreferences) {
    String str = paramCordovaPreferences.getString("webview", SystemWebViewEngine.class.getCanonicalName());
    try {
      return Class.forName(str).getConstructor(new Class[] { Context.class, CordovaPreferences.class }).newInstance(new Object[] { paramContext, paramCordovaPreferences });
    } catch (Exception exception) {
      throw new RuntimeException("Failed to create webview. ", exception);
    } 
  }
  
  private void sendJavascriptEvent(String paramString) {
    if (this.appPlugin == null)
      this.appPlugin = (CoreAndroid)this.pluginManager.getPlugin("CoreAndroid"); 
    if (this.appPlugin == null) {
      LOG.w("CordovaWebViewImpl", "Unable to fire event without existing plugin");
      return;
    } 
    this.appPlugin.fireJavascriptEvent(paramString);
  }
  
  public boolean backHistory() {
    return this.engine.goBack();
  }
  
  public boolean canGoBack() {
    return this.engine.canGoBack();
  }
  
  public void clearCache() {
    this.engine.clearCache();
  }
  
  @Deprecated
  public void clearCache(boolean paramBoolean) {
    this.engine.clearCache();
  }
  
  public void clearHistory() {
    this.engine.clearHistory();
  }
  
  public Context getContext() {
    return this.engine.getView().getContext();
  }
  
  public ICordovaCookieManager getCookieManager() {
    return this.engine.getCookieManager();
  }
  
  public CordovaWebViewEngine getEngine() {
    return this.engine;
  }
  
  public PluginManager getPluginManager() {
    return this.pluginManager;
  }
  
  public CordovaPreferences getPreferences() {
    return this.preferences;
  }
  
  public CordovaResourceApi getResourceApi() {
    return this.resourceApi;
  }
  
  public String getUrl() {
    return this.engine.getUrl();
  }
  
  public View getView() {
    return this.engine.getView();
  }
  
  public void handleDestroy() {
    if (!isInitialized())
      return; 
    this.loadUrlTimeout++;
    this.pluginManager.onDestroy();
    loadUrl("about:blank");
    this.engine.destroy();
    hideCustomView();
  }
  
  public void handlePause(boolean paramBoolean) {
    if (!isInitialized())
      return; 
    this.hasPausedEver = true;
    this.pluginManager.onPause(paramBoolean);
    sendJavascriptEvent("pause");
    if (!paramBoolean)
      this.engine.setPaused(true); 
  }
  
  public void handleResume(boolean paramBoolean) {
    if (!isInitialized())
      return; 
    this.engine.setPaused(false);
    this.pluginManager.onResume(paramBoolean);
    if (this.hasPausedEver)
      sendJavascriptEvent("resume"); 
  }
  
  public void handleStart() {
    if (!isInitialized())
      return; 
    this.pluginManager.onStart();
  }
  
  public void handleStop() {
    if (!isInitialized())
      return; 
    this.pluginManager.onStop();
  }
  
  @Deprecated
  public void hideCustomView() {
    if (this.mCustomView == null)
      return; 
    LOG.d("CordovaWebViewImpl", "Hiding Custom View");
    this.mCustomView.setVisibility(8);
    ((ViewGroup)this.engine.getView().getParent()).removeView(this.mCustomView);
    this.mCustomView = null;
    this.mCustomViewCallback.onCustomViewHidden();
    this.engine.getView().setVisibility(0);
  }
  
  public void init(CordovaInterface paramCordovaInterface) {
    init(paramCordovaInterface, new ArrayList<PluginEntry>(), new CordovaPreferences());
  }
  
  @SuppressLint({"Assert"})
  public void init(CordovaInterface paramCordovaInterface, List<PluginEntry> paramList, CordovaPreferences paramCordovaPreferences) {
    if (this.cordova != null)
      throw new IllegalStateException(); 
    this.cordova = paramCordovaInterface;
    this.preferences = paramCordovaPreferences;
    this.pluginManager = new PluginManager(this, this.cordova, paramList);
    this.resourceApi = new CordovaResourceApi(this.engine.getView().getContext(), this.pluginManager);
    this.nativeToJsMessageQueue = new NativeToJsMessageQueue();
    this.nativeToJsMessageQueue.addBridgeMode(new NativeToJsMessageQueue.NoOpBridgeMode());
    this.nativeToJsMessageQueue.addBridgeMode(new NativeToJsMessageQueue.LoadUrlBridgeMode(this.engine, paramCordovaInterface));
    if (paramCordovaPreferences.getBoolean("DisallowOverscroll", false))
      this.engine.getView().setOverScrollMode(2); 
    this.engine.init(this, paramCordovaInterface, this.engineClient, this.resourceApi, this.pluginManager, this.nativeToJsMessageQueue);
    this.pluginManager.addService("CoreAndroid", "org.apache.cordova.CoreAndroid");
    this.pluginManager.init();
  }
  
  public boolean isButtonPlumbedToJs(int paramInt) {
    return this.boundKeyCodes.contains(Integer.valueOf(paramInt));
  }
  
  @Deprecated
  public boolean isCustomViewShowing() {
    return (this.mCustomView != null);
  }
  
  public boolean isInitialized() {
    return (this.cordova != null);
  }
  
  public void loadUrl(String paramString) {
    loadUrlIntoView(paramString, true);
  }
  
  public void loadUrlIntoView(final String url, final boolean _recreatePlugins) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(">>> loadUrl(");
    stringBuilder.append(url);
    stringBuilder.append(")");
    LOG.d("CordovaWebViewImpl", stringBuilder.toString());
    if (url.equals("about:blank") || url.startsWith("javascript:")) {
      this.engine.loadUrl(url, false);
      return;
    } 
    if (_recreatePlugins || this.loadedUrl == null) {
      _recreatePlugins = true;
    } else {
      _recreatePlugins = false;
    } 
    if (_recreatePlugins) {
      if (this.loadedUrl != null) {
        this.appPlugin = null;
        this.pluginManager.init();
      } 
      this.loadedUrl = url;
    } 
    final int currentLoadUrlTimeout = this.loadUrlTimeout;
    final int loadUrlTimeoutValue = this.preferences.getInteger("LoadUrlTimeoutValue", 20000);
    final Runnable timeoutCheck = new Runnable() {
        public void run() {
          // Byte code:
          //   0: aload_0
          //   1: monitorenter
          //   2: aload_0
          //   3: aload_0
          //   4: getfield val$loadUrlTimeoutValue : I
          //   7: i2l
          //   8: invokevirtual wait : (J)V
          //   11: aload_0
          //   12: monitorexit
          //   13: goto -> 26
          //   16: astore_1
          //   17: aload_0
          //   18: monitorexit
          //   19: aload_1
          //   20: athrow
          //   21: astore_1
          //   22: aload_1
          //   23: invokevirtual printStackTrace : ()V
          //   26: aload_0
          //   27: getfield this$0 : Lorg/apache/cordova/CordovaWebViewImpl;
          //   30: invokestatic access$100 : (Lorg/apache/cordova/CordovaWebViewImpl;)I
          //   33: aload_0
          //   34: getfield val$currentLoadUrlTimeout : I
          //   37: if_icmpne -> 59
          //   40: aload_0
          //   41: getfield this$0 : Lorg/apache/cordova/CordovaWebViewImpl;
          //   44: invokestatic access$200 : (Lorg/apache/cordova/CordovaWebViewImpl;)Lorg/apache/cordova/CordovaInterface;
          //   47: invokeinterface getActivity : ()Landroid/app/Activity;
          //   52: aload_0
          //   53: getfield val$loadError : Ljava/lang/Runnable;
          //   56: invokevirtual runOnUiThread : (Ljava/lang/Runnable;)V
          //   59: return
          // Exception table:
          //   from	to	target	type
          //   0	2	21	java/lang/InterruptedException
          //   2	13	16	finally
          //   17	19	16	finally
          //   19	21	21	java/lang/InterruptedException
        }
      };
    this.cordova.getActivity().runOnUiThread(new Runnable() {
          public void run() {
            if (loadUrlTimeoutValue > 0)
              CordovaWebViewImpl.this.cordova.getThreadPool().execute(timeoutCheck); 
            CordovaWebViewImpl.this.engine.loadUrl(url, _recreatePlugins);
          }
        });
  }
  
  public void onNewIntent(Intent paramIntent) {
    if (this.pluginManager != null)
      this.pluginManager.onNewIntent(paramIntent); 
  }
  
  public Object postMessage(String paramString, Object paramObject) {
    return this.pluginManager.postMessage(paramString, paramObject);
  }
  
  @Deprecated
  public void sendJavascript(String paramString) {
    this.nativeToJsMessageQueue.addJavaScript(paramString);
  }
  
  public void sendPluginResult(PluginResult paramPluginResult, String paramString) {
    this.nativeToJsMessageQueue.addPluginResult(paramPluginResult, paramString);
  }
  
  public void setButtonPlumbedToJs(int paramInt, boolean paramBoolean) {
    if (paramInt != 4 && paramInt != 82) {
      StringBuilder stringBuilder;
      switch (paramInt) {
        default:
          stringBuilder = new StringBuilder();
          stringBuilder.append("Unsupported keycode: ");
          stringBuilder.append(paramInt);
          throw new IllegalArgumentException(stringBuilder.toString());
        case 24:
        case 25:
          break;
      } 
    } 
    if (paramBoolean) {
      this.boundKeyCodes.add(Integer.valueOf(paramInt));
      return;
    } 
    this.boundKeyCodes.remove(Integer.valueOf(paramInt));
  }
  
  @Deprecated
  public void showCustomView(View paramView, WebChromeClient.CustomViewCallback paramCustomViewCallback) {
    LOG.d("CordovaWebViewImpl", "showing Custom View");
    if (this.mCustomView != null) {
      paramCustomViewCallback.onCustomViewHidden();
      return;
    } 
    this.mCustomView = paramView;
    this.mCustomViewCallback = paramCustomViewCallback;
    ViewGroup viewGroup = (ViewGroup)this.engine.getView().getParent();
    viewGroup.addView(paramView, (ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-1, -1, 17));
    this.engine.getView().setVisibility(8);
    viewGroup.setVisibility(0);
    viewGroup.bringToFront();
  }
  
  public void showWebPage(String paramString, boolean paramBoolean1, boolean paramBoolean2, Map<String, Object> paramMap) {
    LOG.d("CordovaWebViewImpl", "showWebPage(%s, %b, %b, HashMap)", new Object[] { paramString, Boolean.valueOf(paramBoolean1), Boolean.valueOf(paramBoolean2) });
    if (paramBoolean2)
      this.engine.clearHistory(); 
    if (!paramBoolean1)
      if (this.pluginManager.shouldAllowNavigation(paramString)) {
        loadUrlIntoView(paramString, true);
      } else {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("showWebPage: Refusing to load URL into webview since it is not in the <allow-navigation> whitelist. URL=");
        stringBuilder.append(paramString);
        LOG.w("CordovaWebViewImpl", stringBuilder.toString());
      }  
    if (!this.pluginManager.shouldOpenExternalUrl(paramString).booleanValue()) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("showWebPage: Refusing to send intent for URL since it is not in the <allow-intent> whitelist. URL=");
      stringBuilder.append(paramString);
      LOG.w("CordovaWebViewImpl", stringBuilder.toString());
      return;
    } 
    try {
      Intent intent = new Intent("android.intent.action.VIEW");
      intent.addCategory("android.intent.category.BROWSABLE");
      Uri uri = Uri.parse(paramString);
      if ("file".equals(uri.getScheme())) {
        intent.setDataAndType(uri, this.resourceApi.getMimeType(uri));
      } else {
        intent.setData(uri);
      } 
      this.cordova.getActivity().startActivity(intent);
      return;
    } catch (ActivityNotFoundException activityNotFoundException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Error loading url ");
      stringBuilder.append(paramString);
      LOG.e("CordovaWebViewImpl", stringBuilder.toString(), (Throwable)activityNotFoundException);
      return;
    } 
  }
  
  public void stopLoading() {
    this.loadUrlTimeout++;
  }
  
  protected class EngineClient implements CordovaWebViewEngine.Client {
    public void clearLoadTimeoutTimer() {
      CordovaWebViewImpl.access$108(CordovaWebViewImpl.this);
    }
    
    public Boolean onDispatchKeyEvent(KeyEvent param1KeyEvent) {
      boolean bool;
      int i = param1KeyEvent.getKeyCode();
      if (i == 4) {
        bool = true;
      } else {
        bool = false;
      } 
      if (param1KeyEvent.getAction() == 0) {
        if (bool && CordovaWebViewImpl.this.mCustomView != null)
          return Boolean.valueOf(true); 
        if (CordovaWebViewImpl.this.boundKeyCodes.contains(Integer.valueOf(i)))
          return Boolean.valueOf(true); 
        if (bool)
          return Boolean.valueOf(CordovaWebViewImpl.this.engine.canGoBack()); 
      } else if (param1KeyEvent.getAction() == 1) {
        if (bool && CordovaWebViewImpl.this.mCustomView != null) {
          CordovaWebViewImpl.this.hideCustomView();
          return Boolean.valueOf(true);
        } 
        if (CordovaWebViewImpl.this.boundKeyCodes.contains(Integer.valueOf(i))) {
          String str;
          if (i != 4) {
            if (i != 82) {
              if (i != 84) {
                switch (i) {
                  default:
                    param1KeyEvent = null;
                    break;
                  case 25:
                    str = "volumedownbutton";
                    break;
                  case 24:
                    str = "volumeupbutton";
                    break;
                } 
              } else {
                str = "searchbutton";
              } 
            } else {
              str = "menubutton";
            } 
          } else {
            str = "backbutton";
          } 
          if (str != null) {
            CordovaWebViewImpl.this.sendJavascriptEvent(str);
            return Boolean.valueOf(true);
          } 
        } else if (bool) {
          return Boolean.valueOf(CordovaWebViewImpl.this.engine.goBack());
        } 
      } 
      return null;
    }
    
    public boolean onNavigationAttempt(String param1String) {
      if (CordovaWebViewImpl.this.pluginManager.onOverrideUrlLoading(param1String))
        return true; 
      if (CordovaWebViewImpl.this.pluginManager.shouldAllowNavigation(param1String))
        return false; 
      if (CordovaWebViewImpl.this.pluginManager.shouldOpenExternalUrl(param1String).booleanValue()) {
        CordovaWebViewImpl.this.showWebPage(param1String, true, false, null);
        return true;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Blocked (possibly sub-frame) navigation to non-allowed URL: ");
      stringBuilder.append(param1String);
      LOG.w("CordovaWebViewImpl", stringBuilder.toString());
      return true;
    }
    
    public void onPageFinishedLoading(String param1String) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("onPageFinished(");
      stringBuilder.append(param1String);
      stringBuilder.append(")");
      LOG.d("CordovaWebViewImpl", stringBuilder.toString());
      clearLoadTimeoutTimer();
      CordovaWebViewImpl.this.pluginManager.postMessage("onPageFinished", param1String);
      if (CordovaWebViewImpl.this.engine.getView().getVisibility() != 0)
        (new Thread(new Runnable() {
              public void run() {
                try {
                  Thread.sleep(2000L);
                  CordovaWebViewImpl.this.cordova.getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                          CordovaWebViewImpl.this.pluginManager.postMessage("spinner", "stop");
                        }
                      });
                  return;
                } catch (InterruptedException interruptedException) {
                  return;
                } 
              }
            })).start(); 
      if (param1String.equals("about:blank"))
        CordovaWebViewImpl.this.pluginManager.postMessage("exit", null); 
    }
    
    public void onPageStarted(String param1String) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("onPageDidNavigate(");
      stringBuilder.append(param1String);
      stringBuilder.append(")");
      LOG.d("CordovaWebViewImpl", stringBuilder.toString());
      CordovaWebViewImpl.this.boundKeyCodes.clear();
      CordovaWebViewImpl.this.pluginManager.onReset();
      CordovaWebViewImpl.this.pluginManager.postMessage("onPageStarted", param1String);
    }
    
    public void onReceivedError(int param1Int, String param1String1, String param1String2) {
      clearLoadTimeoutTimer();
      JSONObject jSONObject = new JSONObject();
      try {
        jSONObject.put("errorCode", param1Int);
        jSONObject.put("description", param1String1);
        jSONObject.put("url", param1String2);
      } catch (JSONException jSONException) {
        jSONException.printStackTrace();
      } 
      CordovaWebViewImpl.this.pluginManager.postMessage("onReceivedError", jSONObject);
    }
  }
  
  class null implements Runnable {
    public void run() {
      try {
        Thread.sleep(2000L);
        CordovaWebViewImpl.this.cordova.getActivity().runOnUiThread(new Runnable() {
              public void run() {
                CordovaWebViewImpl.this.pluginManager.postMessage("spinner", "stop");
              }
            });
        return;
      } catch (InterruptedException interruptedException) {
        return;
      } 
    }
  }
  
  class null implements Runnable {
    public void run() {
      CordovaWebViewImpl.this.pluginManager.postMessage("spinner", "stop");
    }
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\org\apache\cordova\CordovaWebViewImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */