package com.cndatacom.cnportalclient.constant;

import android.text.TextUtils;
import com.cndatacom.cnportalclient.model.InnerState;
import java.util.HashMap;
import java.util.Map;

public class Constant {
  public static final String ACCOUNT = "account";
  
  public static final String APK_MD5_KEY = "ApkMD5Key";
  
  public static final String APPCONFIG_SPNAME = "app-config";
  
  public static final String BATTERY_TIPS = "BatteryTips";
  
  public static final String COLLECTION_KEY = "Collection";
  
  public static final String INDEXTEMPCONFIG_SPNAME = "index-temp-config";
  
  public static final String LOCAL_SPLASH_AD_KEY = "Local_SplashAD_Key";
  
  public static final String LOGIN_SYSTEMTIMESTRING = "loginSystemTimeString";
  
  public static final String PRIVACY_KEY = "PrivacyKey";
  
  public static final String SELFCONNECT = "selfConnect";
  
  public static final String SHARE_APP_JSON_LIST = "ShareAppJsonList";
  
  public static final String TAG = "CDCLogs";
  
  public static final String TEMP_LOGIN_ACCOUNT_KEY = "TempLoginAccount";
  
  public static final String TEMP_LOGIN_PASSWORD_KEY = "TempLoginPassword";
  
  public static final String TEMP_LOGIN_VERTIFYCODE_KEY = "TempLoginVertifyCode";
  
  public static final String TESTFORWARDURL = "http://192.168.142.242/forward.cgi";
  
  public static final String TICKET = "ticket";
  
  public static final String TUNNEL_ADDRESS_KEY = "TunnelAddress";
  
  public static final String UserExperience_KEY = "userExperience";
  
  public static final boolean VERSION = true;
  
  public static final String VERSION_GD = "gd";
  
  public static final String VERSION_HN = "hn";
  
  public static final String VERSION_KEY = "hn";
  
  public static final String VPN_KEY = "Vpn";
  
  public static final String VPN_TUNNEL_IP = "10.68.99.246";
  
  private static Map<String, String> a = new HashMap<String, String>();
  
  private static Map<String, String> b = new HashMap<String, String>();
  
  private static Map<String, Integer> c = new HashMap<String, Integer>();
  
  private static Map<String, String> d = new HashMap<String, String>();
  
  private static Map<String, String> e = new HashMap<String, String>();
  
  static {
    a.put("gd", "Android5");
    b.put("gd", "Android5_vpn");
    a.put("hn", "Android4");
    b.put("hn", "Android4_vpn");
    c.put("gd", Integer.valueOf(2131099751));
    c.put("hn", Integer.valueOf(2131099751));
    d.put("gd", "province/GD/policy.html");
    d.put("hn", "province/HuNan/policy.html");
    e.put("gd", "province/GD/userService.html");
    e.put("hn", "province/HuNan/userService.html");
  }
  
  public static String getDefUserAgent() {
    return a.get("hn");
  }
  
  public static String getPolicyDialogHtml() {
    return d.get("hn");
  }
  
  public static int getSplashFooterImg() {
    return ((Integer)c.get("hn")).intValue();
  }
  
  public static String getUserAgent() {
    String str = InnerState.load().getFuncfgUrl("Vpn", "enable", "");
    return (!TextUtils.isEmpty(str) && "1".equals(str)) ? b.get("hn") : a.get("hn");
  }
  
  public static String getUserServiceDialogHtml() {
    return e.get("hn");
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\constant\Constant.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */