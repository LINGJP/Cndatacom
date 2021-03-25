package com.cndatacom.cnportalclient;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;
import com.cndatacom.cnportalclient.utils.AppInfoUtils;
import com.cndatacom.cnportalclient.utils.CdcUtils;
import com.cndatacom.cnportalclient.utils.ExLog;
import com.cndatacom.cnportalclient.utils.ToastUtils;
import com.stub.StubApp;

public class CampusApplication extends Application {
  private int a = 0;
  
  private Thread.UncaughtExceptionHandler b = CampusApplication$$Lambda$0.a;
  
  private void a() {
    String str = AppInfoUtils.getCurrentProcessName();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("CampusApplication processName : ");
    stringBuilder.append(str);
    ExLog.i(new String[] { stringBuilder.toString() });
    if (!TextUtils.isEmpty(str) && str.equals(getPackageName()))
      b(); 
  }
  
  private void b() {
    registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks(this) {
          public void onActivityCreated(Activity param1Activity, Bundle param1Bundle) {}
          
          public void onActivityDestroyed(Activity param1Activity) {}
          
          public void onActivityPaused(Activity param1Activity) {}
          
          public void onActivityResumed(Activity param1Activity) {}
          
          public void onActivitySaveInstanceState(Activity param1Activity, Bundle param1Bundle) {}
          
          public void onActivityStarted(Activity param1Activity) {
            CampusApplication.a(this.a);
          }
          
          public void onActivityStopped(Activity param1Activity) {
            CampusApplication.b(this.a);
            if (CampusApplication.c(this.a) <= 0)
              ToastUtils.showSafetyToast(StubApp.getOrigApplicationContext(this.a.getApplicationContext())); 
          }
        });
  }
  
  public void onCreate() {
    super.onCreate();
    CdcUtils.setContext((Context)this);
    Thread.setDefaultUncaughtExceptionHandler(this.b);
    if (Build.VERSION.SDK_INT < 23)
      System.setProperty("http.keepAlive", "false"); 
    ExLog.i(new String[] { "CampusApplication start ..." });
    a();
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\CampusApplication.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */