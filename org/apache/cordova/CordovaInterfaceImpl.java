package org.apache.cordova;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;

public class CordovaInterfaceImpl implements CordovaInterface {
  private static final String TAG = "CordovaInterfaceImpl";
  
  protected Activity activity;
  
  protected CordovaPlugin activityResultCallback;
  
  protected int activityResultRequestCode;
  
  protected boolean activityWasDestroyed = false;
  
  protected String initCallbackService;
  
  protected CallbackMap permissionResultCallbacks;
  
  protected PluginManager pluginManager;
  
  protected Bundle savedPluginState;
  
  protected ActivityResultHolder savedResult;
  
  protected ExecutorService threadPool;
  
  public CordovaInterfaceImpl(Activity paramActivity) {
    this(paramActivity, Executors.newCachedThreadPool());
  }
  
  public CordovaInterfaceImpl(Activity paramActivity, ExecutorService paramExecutorService) {
    this.activity = paramActivity;
    this.threadPool = paramExecutorService;
    this.permissionResultCallbacks = new CallbackMap();
  }
  
  public Activity getActivity() {
    return this.activity;
  }
  
  public Context getContext() {
    return (Context)this.activity;
  }
  
  public ExecutorService getThreadPool() {
    return this.threadPool;
  }
  
  public boolean hasPermission(String paramString) {
    return (Build.VERSION.SDK_INT >= 23) ? ((this.activity.checkSelfPermission(paramString) == 0)) : true;
  }
  
  public boolean onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
    String str;
    CordovaPlugin cordovaPlugin2 = this.activityResultCallback;
    CordovaPlugin cordovaPlugin1 = cordovaPlugin2;
    if (cordovaPlugin2 == null) {
      cordovaPlugin1 = cordovaPlugin2;
      if (this.initCallbackService != null) {
        this.savedResult = new ActivityResultHolder(paramInt1, paramInt2, paramIntent);
        cordovaPlugin1 = cordovaPlugin2;
        if (this.pluginManager != null) {
          cordovaPlugin2 = this.pluginManager.getPlugin(this.initCallbackService);
          cordovaPlugin1 = cordovaPlugin2;
          if (cordovaPlugin2 != null) {
            cordovaPlugin2.onRestoreStateForActivityResult(this.savedPluginState.getBundle(cordovaPlugin2.getServiceName()), new ResumeCallback(cordovaPlugin2.getServiceName(), this.pluginManager));
            cordovaPlugin1 = cordovaPlugin2;
          } 
        } 
      } 
    } 
    this.activityResultCallback = null;
    if (cordovaPlugin1 != null) {
      LOG.d("CordovaInterfaceImpl", "Sending activity result to plugin");
      this.initCallbackService = null;
      this.savedResult = null;
      cordovaPlugin1.onActivityResult(paramInt1, paramInt2, paramIntent);
      return true;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Got an activity result, but no plugin was registered to receive it");
    if (this.savedResult != null) {
      str = " yet!";
    } else {
      str = ".";
    } 
    stringBuilder.append(str);
    LOG.w("CordovaInterfaceImpl", stringBuilder.toString());
    return false;
  }
  
  public void onCordovaInit(PluginManager paramPluginManager) {
    this.pluginManager = paramPluginManager;
    if (this.savedResult != null) {
      onActivityResult(this.savedResult.requestCode, this.savedResult.resultCode, this.savedResult.intent);
      return;
    } 
    if (this.activityWasDestroyed) {
      this.activityWasDestroyed = false;
      if (paramPluginManager != null) {
        CoreAndroid coreAndroid = (CoreAndroid)paramPluginManager.getPlugin("CoreAndroid");
        if (coreAndroid != null) {
          JSONObject jSONObject = new JSONObject();
          try {
            jSONObject.put("action", "resume");
          } catch (JSONException jSONException) {
            LOG.e("CordovaInterfaceImpl", "Failed to create event message", (Throwable)jSONException);
          } 
          coreAndroid.sendResumeEvent(new PluginResult(PluginResult.Status.OK, jSONObject));
        } 
      } 
    } 
  }
  
  public Object onMessage(String paramString, Object paramObject) {
    if ("exit".equals(paramString))
      this.activity.finish(); 
    return null;
  }
  
  public void onRequestPermissionResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfint) {
    Pair<CordovaPlugin, Integer> pair = this.permissionResultCallbacks.getAndRemoveCallback(paramInt);
    if (pair != null)
      ((CordovaPlugin)pair.first).onRequestPermissionResult(((Integer)pair.second).intValue(), paramArrayOfString, paramArrayOfint); 
  }
  
  public void onSaveInstanceState(Bundle paramBundle) {
    if (this.activityResultCallback != null)
      paramBundle.putString("callbackService", this.activityResultCallback.getServiceName()); 
    if (this.pluginManager != null)
      paramBundle.putBundle("plugin", this.pluginManager.onSaveInstanceState()); 
  }
  
  public void requestPermission(CordovaPlugin paramCordovaPlugin, int paramInt, String paramString) {
    requestPermissions(paramCordovaPlugin, paramInt, new String[] { paramString });
  }
  
  @SuppressLint({"NewApi"})
  public void requestPermissions(CordovaPlugin paramCordovaPlugin, int paramInt, String[] paramArrayOfString) {
    paramInt = this.permissionResultCallbacks.registerCallback(paramCordovaPlugin, paramInt);
    getActivity().requestPermissions(paramArrayOfString, paramInt);
  }
  
  public void restoreInstanceState(Bundle paramBundle) {
    this.initCallbackService = paramBundle.getString("callbackService");
    this.savedPluginState = paramBundle.getBundle("plugin");
    this.activityWasDestroyed = true;
  }
  
  public void setActivityResultCallback(CordovaPlugin paramCordovaPlugin) {
    if (this.activityResultCallback != null)
      this.activityResultCallback.onActivityResult(this.activityResultRequestCode, 0, null); 
    this.activityResultCallback = paramCordovaPlugin;
  }
  
  public void setActivityResultRequestCode(int paramInt) {
    this.activityResultRequestCode = paramInt;
  }
  
  public void startActivityForResult(CordovaPlugin paramCordovaPlugin, Intent paramIntent, int paramInt) {
    setActivityResultCallback(paramCordovaPlugin);
    try {
      this.activity.startActivityForResult(paramIntent, paramInt);
      return;
    } catch (RuntimeException runtimeException) {
      this.activityResultCallback = null;
      throw runtimeException;
    } 
  }
  
  private static class ActivityResultHolder {
    private Intent intent;
    
    private int requestCode;
    
    private int resultCode;
    
    public ActivityResultHolder(int param1Int1, int param1Int2, Intent param1Intent) {
      this.requestCode = param1Int1;
      this.resultCode = param1Int2;
      this.intent = param1Intent;
    }
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\org\apache\cordova\CordovaInterfaceImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */