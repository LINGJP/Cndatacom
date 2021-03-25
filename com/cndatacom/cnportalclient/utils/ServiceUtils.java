package com.cndatacom.cnportalclient.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;

public class ServiceUtils {
  public static boolean isServiceRunning(Context paramContext, String paramString) {
    if (TextUtils.isEmpty(paramString))
      return false; 
    ArrayList arrayList = (ArrayList)((ActivityManager)paramContext.getSystemService("activity")).getRunningServices(2147483647);
    for (int i = 0; i < arrayList.size(); i++) {
      if (((ActivityManager.RunningServiceInfo)arrayList.get(i)).service.getClassName().toString().equals(paramString))
        return true; 
    } 
    return false;
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclien\\utils\ServiceUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */