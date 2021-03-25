package org.apache.cordova;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.stub.StubApp;
import java.util.ArrayList;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class CordovaActivity extends Activity {
  private static int ACTIVITY_EXITING = 2;
  
  private static int ACTIVITY_RUNNING = 1;
  
  private static int ACTIVITY_STARTING = 0;
  
  public static String TAG = "CordovaActivity";
  
  protected CordovaWebView appView;
  
  protected CordovaInterfaceImpl cordovaInterface;
  
  protected boolean immersiveMode;
  
  protected boolean keepRunning = true;
  
  protected String launchUrl;
  
  protected ArrayList<PluginEntry> pluginEntries;
  
  protected CordovaPreferences preferences;
  
  protected void createViews() {
    this.appView.getView().setId(100);
    this.appView.getView().setLayoutParams((ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-1, -1));
    setContentView(this.appView.getView());
    if (this.preferences.contains("BackgroundColor"))
      try {
        int i = this.preferences.getInteger("BackgroundColor", -16777216);
        this.appView.getView().setBackgroundColor(i);
      } catch (NumberFormatException numberFormatException) {
        numberFormatException.printStackTrace();
      }  
    this.appView.getView().requestFocusFromTouch();
  }
  
  public void displayError(final String title, final String message, final String button, final boolean exit) {
    runOnUiThread(new Runnable() {
          public void run() {
            try {
              AlertDialog.Builder builder = new AlertDialog.Builder((Context)me);
              builder.setMessage(message);
              builder.setTitle(title);
              builder.setCancelable(false);
              builder.setPositiveButton(button, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface param2DialogInterface, int param2Int) {
                      param2DialogInterface.dismiss();
                      if (exit)
                        CordovaActivity.this.finish(); 
                    }
                  });
              builder.create();
              builder.show();
              return;
            } catch (Exception exception) {
              CordovaActivity.this.finish();
              return;
            } 
          }
        });
  }
  
  protected void init() {
    this.appView = makeWebView();
    createViews();
    if (!this.appView.isInitialized())
      this.appView.init(this.cordovaInterface, this.pluginEntries, this.preferences); 
    this.cordovaInterface.onCordovaInit(this.appView.getPluginManager());
    if ("media".equals(this.preferences.getString("DefaultVolumeStream", "").toLowerCase(Locale.ENGLISH)))
      setVolumeControlStream(3); 
  }
  
  protected void loadConfig() {
    ConfigXmlParser configXmlParser = new ConfigXmlParser();
    configXmlParser.parse((Context)this);
    this.preferences = configXmlParser.getPreferences();
    this.preferences.setPreferencesBundle(getIntent().getExtras());
    this.launchUrl = configXmlParser.getLaunchUrl();
    this.pluginEntries = configXmlParser.getPluginEntries();
    Config.parser = configXmlParser;
  }
  
  public void loadUrl(String paramString) {
    if (this.appView == null)
      init(); 
    this.keepRunning = this.preferences.getBoolean("KeepRunning", true);
    this.appView.loadUrlIntoView(paramString, true);
  }
  
  protected CordovaInterfaceImpl makeCordovaInterface() {
    return new CordovaInterfaceImpl(this) {
        public Object onMessage(String param1String, Object param1Object) {
          return CordovaActivity.this.onMessage(param1String, param1Object);
        }
      };
  }
  
  protected CordovaWebView makeWebView() {
    return new CordovaWebViewImpl(makeWebViewEngine());
  }
  
  protected CordovaWebViewEngine makeWebViewEngine() {
    return CordovaWebViewImpl.createEngine((Context)this, this.preferences);
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
    String str = TAG;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Incoming Result. Request code = ");
    stringBuilder.append(paramInt1);
    LOG.d(str, stringBuilder.toString());
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    this.cordovaInterface.onActivityResult(paramInt1, paramInt2, paramIntent);
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration) {
    super.onConfigurationChanged(paramConfiguration);
    if (this.appView == null)
      return; 
    PluginManager pluginManager = this.appView.getPluginManager();
    if (pluginManager != null)
      pluginManager.onConfigurationChanged(paramConfiguration); 
  }
  
  public void onCreate(Bundle paramBundle) {
    loadConfig();
    LOG.setLogLevel(this.preferences.getString("loglevel", "ERROR"));
    LOG.i(TAG, "Apache Cordova native platform version 7.0.0 is starting");
    LOG.d(TAG, "CordovaActivity.onCreate()");
    if (!this.preferences.getBoolean("ShowTitle", false))
      getWindow().requestFeature(1); 
    if (this.preferences.getBoolean("SetFullscreen", false)) {
      LOG.d(TAG, "The SetFullscreen configuration is deprecated in favor of Fullscreen, and will be removed in a future version.");
      this.preferences.set("Fullscreen", true);
    } 
    if (this.preferences.getBoolean("Fullscreen", false)) {
      if (Build.VERSION.SDK_INT >= 19 && !this.preferences.getBoolean("FullscreenNotImmersive", false)) {
        this.immersiveMode = true;
      } else {
        getWindow().setFlags(1024, 1024);
      } 
    } else {
      getWindow().setFlags(2048, 2048);
    } 
    super.onCreate(paramBundle);
    this.cordovaInterface = makeCordovaInterface();
    if (paramBundle != null)
      this.cordovaInterface.restoreInstanceState(paramBundle); 
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu) {
    if (this.appView != null)
      this.appView.getPluginManager().postMessage("onCreateOptionsMenu", paramMenu); 
    return super.onCreateOptionsMenu(paramMenu);
  }
  
  public void onDestroy() {
    LOG.d(TAG, "CordovaActivity.onDestroy()");
    super.onDestroy();
    if (this.appView != null)
      this.appView.handleDestroy(); 
  }
  
  public Object onMessage(String paramString, Object paramObject) {
    if ("onReceivedError".equals(paramString)) {
      JSONObject jSONObject = (JSONObject)paramObject;
      try {
        onReceivedError(jSONObject.getInt("errorCode"), jSONObject.getString("description"), jSONObject.getString("url"));
      } catch (JSONException jSONException) {
        jSONException.printStackTrace();
      } 
    } else if ("exit".equals(jSONException)) {
      finish();
    } 
    return null;
  }
  
  protected void onNewIntent(Intent paramIntent) {
    super.onNewIntent(paramIntent);
    if (this.appView != null)
      this.appView.onNewIntent(paramIntent); 
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem) {
    if (this.appView != null)
      this.appView.getPluginManager().postMessage("onOptionsItemSelected", paramMenuItem); 
    return true;
  }
  
  protected void onPause() {
    super.onPause();
    LOG.d(TAG, "Paused the activity.");
    if (this.appView != null) {
      boolean bool;
      if (this.keepRunning || this.cordovaInterface.activityResultCallback != null) {
        bool = true;
      } else {
        bool = false;
      } 
      this.appView.handlePause(bool);
    } 
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu) {
    if (this.appView != null)
      this.appView.getPluginManager().postMessage("onPrepareOptionsMenu", paramMenu); 
    return true;
  }
  
  public void onReceivedError(int paramInt, final String description, final String failingUrl) {
    final boolean exit;
    final String errorUrl = this.preferences.getString("errorUrl", null);
    if (str != null && !failingUrl.equals(str) && this.appView != null) {
      runOnUiThread(new Runnable() {
            public void run() {
              me.appView.showWebPage(errorUrl, false, true, null);
            }
          });
      return;
    } 
    if (paramInt != -2) {
      bool = true;
    } else {
      bool = false;
    } 
    runOnUiThread(new Runnable() {
          public void run() {
            if (exit) {
              me.appView.getView().setVisibility(8);
              CordovaActivity cordovaActivity = me;
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append(description);
              stringBuilder.append(" (");
              stringBuilder.append(failingUrl);
              stringBuilder.append(")");
              cordovaActivity.displayError("Application Error", stringBuilder.toString(), "OK", exit);
            } 
          }
        });
  }
  
  public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfint) {
    try {
      StubApp.interface22(paramInt, paramArrayOfString, paramArrayOfint);
      this.cordovaInterface.onRequestPermissionResult(paramInt, paramArrayOfString, paramArrayOfint);
      return;
    } catch (JSONException jSONException) {
      LOG.d(TAG, "JSONException: Parameters fed into the method are not valid");
      jSONException.printStackTrace();
      return;
    } 
  }
  
  protected void onResume() {
    super.onResume();
    LOG.d(TAG, "Resumed the activity.");
    if (this.appView == null)
      return; 
    getWindow().getDecorView().requestFocus();
    this.appView.handleResume(this.keepRunning);
  }
  
  protected void onSaveInstanceState(Bundle paramBundle) {
    this.cordovaInterface.onSaveInstanceState(paramBundle);
    super.onSaveInstanceState(paramBundle);
  }
  
  protected void onStart() {
    super.onStart();
    LOG.d(TAG, "Started the activity.");
    if (this.appView == null)
      return; 
    this.appView.handleStart();
  }
  
  protected void onStop() {
    super.onStop();
    LOG.d(TAG, "Stopped the activity.");
    if (this.appView == null)
      return; 
    this.appView.handleStop();
  }
  
  @SuppressLint({"InlinedApi"})
  public void onWindowFocusChanged(boolean paramBoolean) {
    super.onWindowFocusChanged(paramBoolean);
    if (paramBoolean && this.immersiveMode)
      getWindow().getDecorView().setSystemUiVisibility(5894); 
  }
  
  @SuppressLint({"NewApi"})
  public void startActivityForResult(Intent paramIntent, int paramInt, Bundle paramBundle) {
    this.cordovaInterface.setActivityResultRequestCode(paramInt);
    super.startActivityForResult(paramIntent, paramInt, paramBundle);
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\org\apache\cordova\CordovaActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */