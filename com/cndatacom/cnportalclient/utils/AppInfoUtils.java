package com.cndatacom.cnportalclient.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

public class AppInfoUtils {
  public static String getAppName(Context paramContext) {
    return paramContext.getResources().getString(2131492904);
  }
  
  public static String getCurrentProcessName() {
    // Byte code:
    //   0: invokestatic myPid : ()I
    //   3: istore_0
    //   4: aconst_null
    //   5: astore_3
    //   6: new java/lang/StringBuilder
    //   9: dup
    //   10: invokespecial <init> : ()V
    //   13: astore_1
    //   14: aload_1
    //   15: ldc '/proc/'
    //   17: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   20: pop
    //   21: aload_1
    //   22: iload_0
    //   23: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   26: pop
    //   27: aload_1
    //   28: ldc '/cmdline'
    //   30: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   33: pop
    //   34: new java/io/BufferedReader
    //   37: dup
    //   38: new java/io/FileReader
    //   41: dup
    //   42: aload_1
    //   43: invokevirtual toString : ()Ljava/lang/String;
    //   46: invokespecial <init> : (Ljava/lang/String;)V
    //   49: invokespecial <init> : (Ljava/io/Reader;)V
    //   52: astore_2
    //   53: aload_2
    //   54: astore_1
    //   55: aload_2
    //   56: invokevirtual readLine : ()Ljava/lang/String;
    //   59: astore #4
    //   61: aload #4
    //   63: astore_3
    //   64: aload_2
    //   65: astore_1
    //   66: aload #4
    //   68: invokestatic isEmpty : (Ljava/lang/CharSequence;)Z
    //   71: ifne -> 82
    //   74: aload_2
    //   75: astore_1
    //   76: aload #4
    //   78: invokevirtual trim : ()Ljava/lang/String;
    //   81: astore_3
    //   82: aload_2
    //   83: ifnull -> 97
    //   86: aload_2
    //   87: invokevirtual close : ()V
    //   90: aload_3
    //   91: areturn
    //   92: astore_1
    //   93: aload_1
    //   94: invokevirtual printStackTrace : ()V
    //   97: aload_3
    //   98: areturn
    //   99: astore_3
    //   100: goto -> 112
    //   103: astore_1
    //   104: aload_3
    //   105: astore_2
    //   106: goto -> 140
    //   109: astore_3
    //   110: aconst_null
    //   111: astore_2
    //   112: aload_2
    //   113: astore_1
    //   114: aload_3
    //   115: invokevirtual printStackTrace : ()V
    //   118: aload_2
    //   119: ifnull -> 133
    //   122: aload_2
    //   123: invokevirtual close : ()V
    //   126: aconst_null
    //   127: areturn
    //   128: astore_1
    //   129: aload_1
    //   130: invokevirtual printStackTrace : ()V
    //   133: aconst_null
    //   134: areturn
    //   135: astore_3
    //   136: aload_1
    //   137: astore_2
    //   138: aload_3
    //   139: astore_1
    //   140: aload_2
    //   141: ifnull -> 156
    //   144: aload_2
    //   145: invokevirtual close : ()V
    //   148: goto -> 156
    //   151: astore_2
    //   152: aload_2
    //   153: invokevirtual printStackTrace : ()V
    //   156: aload_1
    //   157: athrow
    // Exception table:
    //   from	to	target	type
    //   6	53	109	java/lang/Throwable
    //   6	53	103	finally
    //   55	61	99	java/lang/Throwable
    //   55	61	135	finally
    //   66	74	99	java/lang/Throwable
    //   66	74	135	finally
    //   76	82	99	java/lang/Throwable
    //   76	82	135	finally
    //   86	90	92	java/io/IOException
    //   114	118	135	finally
    //   122	126	128	java/io/IOException
    //   144	148	151	java/io/IOException
  }
  
  public static int getNavigationBarHeight(Context paramContext) {
    Resources resources = paramContext.getResources();
    return resources.getDimensionPixelSize(resources.getIdentifier("navigation_bar_height", "dimen", "android"));
  }
  
  public static int getStatusBarHeight(Context paramContext) {
    int i = paramContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
    return (i > 0) ? paramContext.getResources().getDimensionPixelSize(i) : 0;
  }
  
  public static String getVerName(Context paramContext) {
    try {
      return (paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0)).versionName;
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      nameNotFoundException.printStackTrace();
      return "";
    } 
  }
  
  public static int getVersionCode(Context paramContext) {
    try {
      return (paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0)).versionCode;
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      nameNotFoundException.printStackTrace();
      return 0;
    } 
  }
  
  public static boolean isApkInDebug(Context paramContext) {
    boolean bool = false;
    try {
      int i = (paramContext.getApplicationInfo()).flags;
      if ((i & 0x2) != 0)
        bool = true; 
      return bool;
    } catch (Exception exception) {
      exception.printStackTrace();
      return false;
    } 
  }
  
  public static boolean isAppInstalled(Context paramContext, String paramString) {
    try {
      PackageInfo packageInfo = paramContext.getPackageManager().getPackageInfo(paramString, 0);
      if (packageInfo != null)
        return true; 
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
    return false;
  }
  
  public static boolean isPad(Context paramContext) {
    return (((paramContext.getResources().getConfiguration()).screenLayout & 0xF) >= 3);
  }
  
  public static boolean isShowNavigationBar(@NonNull Activity paramActivity) {
    ViewGroup viewGroup = (ViewGroup)paramActivity.getWindow().getDecorView();
    if (viewGroup != null)
      for (int i = 0; i < viewGroup.getChildCount(); i++) {
        viewGroup.getChildAt(i).getContext().getPackageName();
        if (viewGroup.getChildAt(i).getId() != -1 && "navigationBarBackground".equals(paramActivity.getResources().getResourceEntryName(viewGroup.getChildAt(i).getId())))
          return true; 
      }  
    return false;
  }
  
  public static boolean runInstalledApp(Context paramContext, String paramString) {
    Intent intent = paramContext.getPackageManager().getLaunchIntentForPackage(paramString);
    if (intent != null) {
      paramContext.startActivity(intent.addFlags(268435456));
      return true;
    } 
    return false;
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclien\\utils\AppInfoUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */