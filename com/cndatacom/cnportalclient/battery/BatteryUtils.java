package com.cndatacom.cnportalclient.battery;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.PowerManager;

public class BatteryUtils {
  @TargetApi(23)
  public static boolean isIgnoringBatteryOptimizations(Activity paramActivity) {
    if (Build.VERSION.SDK_INT >= 23) {
      String str = paramActivity.getPackageName();
      return ((PowerManager)paramActivity.getSystemService("power")).isIgnoringBatteryOptimizations(str);
    } 
    return true;
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\battery\BatteryUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */