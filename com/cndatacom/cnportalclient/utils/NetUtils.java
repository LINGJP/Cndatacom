package com.cndatacom.cnportalclient.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

public class NetUtils {
  public static final int WIFI_CLOSED = 1;
  
  public static final int WIFI_CONNECTED = 3;
  
  public static final int WIFI_OPENED = 2;
  
  public static int getWifiState(Context paramContext) {
    WifiManager wifiManager = (WifiManager)paramContext.getSystemService("wifi");
    return (wifiManager != null && wifiManager.getWifiState() == 3) ? (CdcUtils.isWifiConnected(paramContext) ? 3 : 2) : 1;
  }
  
  public static boolean isNetworkAvailable(Context paramContext) {
    ConnectivityManager connectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
    boolean bool = false;
    if (connectivityManager != null) {
      NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
      if (networkInfo != null && networkInfo.isConnected()) {
        if (networkInfo.getState() == NetworkInfo.State.CONNECTED)
          bool = true; 
        return bool;
      } 
    } 
    return false;
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclien\\utils\NetUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */