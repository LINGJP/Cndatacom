package org.apache.cordova;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import org.json.JSONException;

public class PluginManager {
  private static final int SLOW_EXEC_WARNING_THRESHOLD;
  
  private static String TAG = "PluginManager";
  
  private final CordovaWebView app;
  
  private final CordovaInterface ctx;
  
  private final LinkedHashMap<String, PluginEntry> entryMap = new LinkedHashMap<String, PluginEntry>();
  
  private boolean isInitialized;
  
  private CordovaPlugin permissionRequester;
  
  private final LinkedHashMap<String, CordovaPlugin> pluginMap = new LinkedHashMap<String, CordovaPlugin>();
  
  static {
    byte b;
    if (Debug.isDebuggerConnected()) {
      b = 60;
    } else {
      b = 16;
    } 
    SLOW_EXEC_WARNING_THRESHOLD = b;
  }
  
  public PluginManager(CordovaWebView paramCordovaWebView, CordovaInterface paramCordovaInterface, Collection<PluginEntry> paramCollection) {
    this.ctx = paramCordovaInterface;
    this.app = paramCordovaWebView;
    setPluginEntries(paramCollection);
  }
  
  private CordovaPlugin instantiatePlugin(String paramString) {
    // Byte code:
    //   0: aload_1
    //   1: ifnull -> 97
    //   4: ldc ''
    //   6: aload_1
    //   7: invokevirtual equals : (Ljava/lang/Object;)Z
    //   10: ifne -> 97
    //   13: aload_1
    //   14: invokestatic forName : (Ljava/lang/String;)Ljava/lang/Class;
    //   17: astore_3
    //   18: goto -> 99
    //   21: iload_2
    //   22: ldc org/apache/cordova/CordovaPlugin
    //   24: aload_3
    //   25: invokevirtual isAssignableFrom : (Ljava/lang/Class;)Z
    //   28: iand
    //   29: ifeq -> 91
    //   32: aload_3
    //   33: invokevirtual newInstance : ()Ljava/lang/Object;
    //   36: checkcast org/apache/cordova/CordovaPlugin
    //   39: astore_3
    //   40: aload_3
    //   41: areturn
    //   42: aload_3
    //   43: invokevirtual printStackTrace : ()V
    //   46: getstatic java/lang/System.out : Ljava/io/PrintStream;
    //   49: astore_3
    //   50: new java/lang/StringBuilder
    //   53: dup
    //   54: invokespecial <init> : ()V
    //   57: astore #4
    //   59: aload #4
    //   61: ldc 'Error adding plugin '
    //   63: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   66: pop
    //   67: aload #4
    //   69: aload_1
    //   70: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   73: pop
    //   74: aload #4
    //   76: ldc '.'
    //   78: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   81: pop
    //   82: aload_3
    //   83: aload #4
    //   85: invokevirtual toString : ()Ljava/lang/String;
    //   88: invokevirtual println : (Ljava/lang/String;)V
    //   91: aconst_null
    //   92: areturn
    //   93: astore_3
    //   94: goto -> 42
    //   97: aconst_null
    //   98: astore_3
    //   99: aload_3
    //   100: ifnull -> 108
    //   103: iconst_1
    //   104: istore_2
    //   105: goto -> 21
    //   108: iconst_0
    //   109: istore_2
    //   110: goto -> 21
    // Exception table:
    //   from	to	target	type
    //   4	18	93	java/lang/Exception
    //   21	40	93	java/lang/Exception
  }
  
  private void startupPlugins() {
    for (PluginEntry pluginEntry : this.entryMap.values()) {
      if (pluginEntry.onload) {
        getPlugin(pluginEntry.service);
        continue;
      } 
      this.pluginMap.put(pluginEntry.service, null);
    } 
  }
  
  public void addService(String paramString1, String paramString2) {
    addService(new PluginEntry(paramString1, paramString2, false));
  }
  
  public void addService(PluginEntry paramPluginEntry) {
    this.entryMap.put(paramPluginEntry.service, paramPluginEntry);
    if (paramPluginEntry.plugin != null) {
      paramPluginEntry.plugin.privateInitialize(paramPluginEntry.service, this.ctx, this.app, this.app.getPreferences());
      this.pluginMap.put(paramPluginEntry.service, paramPluginEntry.plugin);
    } 
  }
  
  public void exec(String paramString1, String paramString2, String paramString3, String paramString4) {
    PluginResult pluginResult;
    StringBuilder stringBuilder;
    CordovaPlugin cordovaPlugin = getPlugin(paramString1);
    if (cordovaPlugin == null) {
      paramString2 = TAG;
      stringBuilder = new StringBuilder();
      stringBuilder.append("exec() call to unknown plugin: ");
      stringBuilder.append(paramString1);
      LOG.d(paramString2, stringBuilder.toString());
      pluginResult = new PluginResult(PluginResult.Status.CLASS_NOT_FOUND_EXCEPTION);
      this.app.sendPluginResult(pluginResult, paramString3);
      return;
    } 
    CallbackContext callbackContext = new CallbackContext(paramString3, this.app);
    try {
      long l = System.currentTimeMillis();
      boolean bool = cordovaPlugin.execute(paramString2, (String)stringBuilder, callbackContext);
      l = System.currentTimeMillis() - l;
      if (l > SLOW_EXEC_WARNING_THRESHOLD) {
        String str = TAG;
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("THREAD WARNING: exec() call to ");
        stringBuilder1.append((String)pluginResult);
        stringBuilder1.append(".");
        stringBuilder1.append(paramString2);
        stringBuilder1.append(" blocked the main thread for ");
        stringBuilder1.append(l);
        stringBuilder1.append("ms. Plugin should use CordovaInterface.getThreadPool().");
        LOG.w(str, stringBuilder1.toString());
      } 
      if (!bool) {
        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.INVALID_ACTION));
        return;
      } 
    } catch (JSONException jSONException) {
      callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.JSON_EXCEPTION));
    } catch (Exception exception) {
      LOG.e(TAG, "Uncaught exception from plugin", exception);
      callbackContext.error(exception.getMessage());
      return;
    } 
  }
  
  public CordovaPlugin getPlugin(String paramString) {
    CordovaPlugin cordovaPlugin2 = this.pluginMap.get(paramString);
    CordovaPlugin cordovaPlugin1 = cordovaPlugin2;
    if (cordovaPlugin2 == null) {
      PluginEntry pluginEntry = this.entryMap.get(paramString);
      if (pluginEntry == null)
        return null; 
      if (pluginEntry.plugin != null) {
        cordovaPlugin1 = pluginEntry.plugin;
      } else {
        cordovaPlugin1 = instantiatePlugin(((PluginEntry)cordovaPlugin1).pluginClass);
      } 
      cordovaPlugin1.privateInitialize(paramString, this.ctx, this.app, this.app.getPreferences());
      this.pluginMap.put(paramString, cordovaPlugin1);
    } 
    return cordovaPlugin1;
  }
  
  public Collection<PluginEntry> getPluginEntries() {
    return this.entryMap.values();
  }
  
  public void init() {
    LOG.d(TAG, "init()");
    this.isInitialized = true;
    onPause(false);
    onDestroy();
    this.pluginMap.clear();
    startupPlugins();
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration) {
    for (CordovaPlugin cordovaPlugin : this.pluginMap.values()) {
      if (cordovaPlugin != null)
        cordovaPlugin.onConfigurationChanged(paramConfiguration); 
    } 
  }
  
  public void onDestroy() {
    for (CordovaPlugin cordovaPlugin : this.pluginMap.values()) {
      if (cordovaPlugin != null)
        cordovaPlugin.onDestroy(); 
    } 
  }
  
  public void onNewIntent(Intent paramIntent) {
    for (CordovaPlugin cordovaPlugin : this.pluginMap.values()) {
      if (cordovaPlugin != null)
        cordovaPlugin.onNewIntent(paramIntent); 
    } 
  }
  
  public boolean onOverrideUrlLoading(String paramString) {
    for (PluginEntry pluginEntry : this.entryMap.values()) {
      CordovaPlugin cordovaPlugin = this.pluginMap.get(pluginEntry.service);
      if (cordovaPlugin != null && cordovaPlugin.onOverrideUrlLoading(paramString))
        return true; 
    } 
    return false;
  }
  
  public void onPause(boolean paramBoolean) {
    for (CordovaPlugin cordovaPlugin : this.pluginMap.values()) {
      if (cordovaPlugin != null)
        cordovaPlugin.onPause(paramBoolean); 
    } 
  }
  
  public boolean onReceivedClientCertRequest(CordovaWebView paramCordovaWebView, ICordovaClientCertRequest paramICordovaClientCertRequest) {
    for (CordovaPlugin cordovaPlugin : this.pluginMap.values()) {
      if (cordovaPlugin != null && cordovaPlugin.onReceivedClientCertRequest(this.app, paramICordovaClientCertRequest))
        return true; 
    } 
    return false;
  }
  
  public boolean onReceivedHttpAuthRequest(CordovaWebView paramCordovaWebView, ICordovaHttpAuthHandler paramICordovaHttpAuthHandler, String paramString1, String paramString2) {
    for (CordovaPlugin cordovaPlugin : this.pluginMap.values()) {
      if (cordovaPlugin != null && cordovaPlugin.onReceivedHttpAuthRequest(this.app, paramICordovaHttpAuthHandler, paramString1, paramString2))
        return true; 
    } 
    return false;
  }
  
  public void onReset() {
    for (CordovaPlugin cordovaPlugin : this.pluginMap.values()) {
      if (cordovaPlugin != null)
        cordovaPlugin.onReset(); 
    } 
  }
  
  public void onResume(boolean paramBoolean) {
    for (CordovaPlugin cordovaPlugin : this.pluginMap.values()) {
      if (cordovaPlugin != null)
        cordovaPlugin.onResume(paramBoolean); 
    } 
  }
  
  public Bundle onSaveInstanceState() {
    Bundle bundle = new Bundle();
    for (CordovaPlugin cordovaPlugin : this.pluginMap.values()) {
      if (cordovaPlugin != null) {
        Bundle bundle1 = cordovaPlugin.onSaveInstanceState();
        if (bundle1 != null)
          bundle.putBundle(cordovaPlugin.getServiceName(), bundle1); 
      } 
    } 
    return bundle;
  }
  
  public void onStart() {
    for (CordovaPlugin cordovaPlugin : this.pluginMap.values()) {
      if (cordovaPlugin != null)
        cordovaPlugin.onStart(); 
    } 
  }
  
  public void onStop() {
    for (CordovaPlugin cordovaPlugin : this.pluginMap.values()) {
      if (cordovaPlugin != null)
        cordovaPlugin.onStop(); 
    } 
  }
  
  public Object postMessage(String paramString, Object paramObject) {
    for (CordovaPlugin cordovaPlugin : this.pluginMap.values()) {
      if (cordovaPlugin != null) {
        Object object = cordovaPlugin.onMessage(paramString, paramObject);
        if (object != null)
          return object; 
      } 
    } 
    return this.ctx.onMessage(paramString, paramObject);
  }
  
  Uri remapUri(Uri paramUri) {
    for (CordovaPlugin cordovaPlugin : this.pluginMap.values()) {
      if (cordovaPlugin != null) {
        Uri uri = cordovaPlugin.remapUri(paramUri);
        if (uri != null)
          return uri; 
      } 
    } 
    return null;
  }
  
  public void setPluginEntries(Collection<PluginEntry> paramCollection) {
    if (this.isInitialized) {
      onPause(false);
      onDestroy();
      this.pluginMap.clear();
      this.entryMap.clear();
    } 
    Iterator<PluginEntry> iterator = paramCollection.iterator();
    while (iterator.hasNext())
      addService(iterator.next()); 
    if (this.isInitialized)
      startupPlugins(); 
  }
  
  public boolean shouldAllowBridgeAccess(String paramString) {
    for (PluginEntry pluginEntry : this.entryMap.values()) {
      CordovaPlugin cordovaPlugin = this.pluginMap.get(pluginEntry.service);
      if (cordovaPlugin != null) {
        Boolean bool = cordovaPlugin.shouldAllowBridgeAccess(paramString);
        if (bool != null)
          return bool.booleanValue(); 
      } 
    } 
    return paramString.startsWith("file://");
  }
  
  public boolean shouldAllowNavigation(String paramString) {
    for (PluginEntry pluginEntry : this.entryMap.values()) {
      CordovaPlugin cordovaPlugin = this.pluginMap.get(pluginEntry.service);
      if (cordovaPlugin != null) {
        Boolean bool = cordovaPlugin.shouldAllowNavigation(paramString);
        if (bool != null)
          return bool.booleanValue(); 
      } 
    } 
    return (paramString.startsWith("file://") || paramString.startsWith("about:blank"));
  }
  
  public boolean shouldAllowRequest(String paramString) {
    for (PluginEntry pluginEntry : this.entryMap.values()) {
      CordovaPlugin cordovaPlugin = this.pluginMap.get(pluginEntry.service);
      if (cordovaPlugin != null) {
        Boolean bool = cordovaPlugin.shouldAllowRequest(paramString);
        if (bool != null)
          return bool.booleanValue(); 
      } 
    } 
    return (!paramString.startsWith("blob:") && !paramString.startsWith("data:")) ? (paramString.startsWith("about:blank") ? true : (paramString.startsWith("https://ssl.gstatic.com/accessibility/javascript/android/") ? true : (paramString.startsWith("file://") ? (paramString.contains("/app_webview/") ^ true) : false))) : true;
  }
  
  public Boolean shouldOpenExternalUrl(String paramString) {
    for (PluginEntry pluginEntry : this.entryMap.values()) {
      CordovaPlugin cordovaPlugin = this.pluginMap.get(pluginEntry.service);
      if (cordovaPlugin != null) {
        Boolean bool = cordovaPlugin.shouldOpenExternalUrl(paramString);
        if (bool != null)
          return bool; 
      } 
    } 
    return Boolean.valueOf(false);
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\org\apache\cordova\PluginManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */