package org.apache.cordova;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import java.util.concurrent.ExecutorService;

public interface CordovaInterface {
  Activity getActivity();
  
  Context getContext();
  
  ExecutorService getThreadPool();
  
  boolean hasPermission(String paramString);
  
  Object onMessage(String paramString, Object paramObject);
  
  void requestPermission(CordovaPlugin paramCordovaPlugin, int paramInt, String paramString);
  
  void requestPermissions(CordovaPlugin paramCordovaPlugin, int paramInt, String[] paramArrayOfString);
  
  void setActivityResultCallback(CordovaPlugin paramCordovaPlugin);
  
  void startActivityForResult(CordovaPlugin paramCordovaPlugin, Intent paramIntent, int paramInt);
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\org\apache\cordova\CordovaInterface.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */