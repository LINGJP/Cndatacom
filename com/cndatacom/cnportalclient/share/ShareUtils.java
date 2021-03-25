package com.cndatacom.cnportalclient.share;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.net.ConnectivityManager;
import android.text.TextUtils;
import com.cndatacom.cnportalclient.utils.AppInfoUtils;
import com.cndatacom.cnportalclient.utils.ExLog;
import com.cndatacom.cnportalclient.utils.PreferencesUtils;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class ShareUtils {
  private static ShareAppInfo a(Context paramContext, List<PackageInfo> paramList, String paramString1, String paramString2) {
    if (paramList != null) {
      int i;
      for (i = 0; i < paramList.size(); i++) {
        PackageInfo packageInfo = paramList.get(i);
        String str1 = packageInfo.packageName;
        String str2 = packageInfo.applicationInfo.loadLabel(paramContext.getPackageManager()).toString();
        ShareAppInfo shareAppInfo = new ShareAppInfo();
        shareAppInfo.setAppName(str2);
        shareAppInfo.setPackagename(str1);
        if (b(str2, paramString1))
          return shareAppInfo; 
        if (a(str1, paramString2))
          return shareAppInfo; 
      } 
    } 
    return null;
  }
  
  private static boolean a(String paramString1, String paramString2) {
    if (!TextUtils.isEmpty(paramString2)) {
      String[] arrayOfString = paramString2.split(",");
      for (int i = 0; i < arrayOfString.length; i++) {
        if (!TextUtils.isEmpty(paramString1) && paramString1.toLowerCase().contains(arrayOfString[i]))
          return true; 
      } 
    } 
    return false;
  }
  
  private static boolean b(String paramString1, String paramString2) {
    if (!TextUtils.isEmpty(paramString2)) {
      String[] arrayOfString = paramString2.split(",");
      for (int i = 0; i < arrayOfString.length; i++) {
        if (!TextUtils.isEmpty(paramString1) && paramString1.toLowerCase().contains(arrayOfString[i]))
          return true; 
      } 
    } 
    return false;
  }
  
  public static List<ShareAppInfo> getShareAppInfo(Context paramContext) {
    ArrayList<ShareAppInfo> arrayList = new ArrayList();
    try {
      String str = PreferencesUtils.getString(paramContext, "ShareAppJsonList");
      if (!TextUtils.isEmpty(str)) {
        JSONArray jSONArray = new JSONArray(str);
        for (int i = 0; i < jSONArray.length(); i++) {
          ShareAppInfo shareAppInfo = new ShareAppInfo();
          JSONObject jSONObject = jSONArray.getJSONObject(i);
          shareAppInfo.setAppName(jSONObject.optString("appName", ""));
          shareAppInfo.setNameContains(jSONObject.optString("nameContains", ""));
          shareAppInfo.setPackagename(jSONObject.optString("packagename", ""));
          shareAppInfo.setPackagenameContains(jSONObject.optString("packagenameContains", ""));
          shareAppInfo.setWifinameContains(jSONObject.optString("wifinameContains", ""));
          arrayList.add(shareAppInfo);
        } 
      } 
    } catch (Exception exception) {
      exception.getMessage();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("getShareAppInfo Exception ->");
      stringBuilder.append(exception.getMessage());
      ExLog.e(new String[] { stringBuilder.toString() });
    } 
    return arrayList;
  }
  
  public static ShareAppInfo hasShareApp(Context paramContext) {
    List<ShareAppInfo> list = getShareAppInfo(paramContext);
    if (list != null && list.size() > 0)
      for (int i = 0; i < list.size(); i++) {
        ShareAppInfo shareAppInfo = list.get(i);
        String str = shareAppInfo.getPackagename();
        if (!TextUtils.isEmpty(str)) {
          if (AppInfoUtils.isAppInstalled(paramContext, str))
            return shareAppInfo; 
        } else {
          shareAppInfo = a(paramContext, paramContext.getPackageManager().getInstalledPackages(0), shareAppInfo.getNameContains(), shareAppInfo.getPackagenameContains());
          if (shareAppInfo != null)
            return shareAppInfo; 
        } 
      }  
    return null;
  }
  
  public static boolean isNetworkSharing(Context paramContext) {
    ConnectivityManager connectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
    String[] arrayOfString = (String[])null;
    try {
      for (Method method : connectivityManager.getClass().getMethods()) {
        if (method.getName().equals("getTetheredIfaces")) {
          String[] arrayOfString2 = (String[])null;
          String[] arrayOfString1 = (String[])method.invoke(connectivityManager, new Object[0]);
          if (arrayOfString1 != null && arrayOfString1.length > 0) {
            int i = arrayOfString1.length;
            if (i > 0)
              return true; 
          } 
        } 
      } 
    } catch (Exception exception) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("isNetworkSharing Exception ->");
      stringBuilder.append(exception.getMessage());
      ExLog.e(new String[] { stringBuilder.toString() });
    } 
    return false;
  }
  
  public static void saveShareAppInfo(Context paramContext, List<ShareAppInfo> paramList) {
    try {
      JSONArray jSONArray = new JSONArray();
      if (paramList != null && paramList.size() > 0)
        for (int i = 0; i < paramList.size(); i++) {
          ShareAppInfo shareAppInfo = paramList.get(i);
          JSONObject jSONObject = new JSONObject();
          jSONObject.put("packagename", shareAppInfo.getPackagename());
          jSONObject.put("appName", shareAppInfo.getAppName());
          jSONObject.put("nameContains", shareAppInfo.getNameContains());
          jSONObject.put("packagenameContains", shareAppInfo.getPackagenameContains());
          jSONObject.put("wifinameContains", shareAppInfo.getWifinameContains());
          jSONArray.put(jSONObject);
        }  
      PreferencesUtils.putString(paramContext, "ShareAppJsonList", jSONArray.toString());
      return;
    } catch (Exception exception) {
      exception.getMessage();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("saveShareAppInfo Exception ->");
      stringBuilder.append(exception.getMessage());
      ExLog.e(new String[] { stringBuilder.toString() });
      return;
    } 
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\share\ShareUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */