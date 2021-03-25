package com.cndatacom.cnportalclient.utils;

import android.util.Base64;
import android.util.Log;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class ExLog {
  public static final String LOG_DIR;
  
  public static final String LOG_TAG = "CDCLogs";
  
  private static final Object a = new Object();
  
  private static SimpleDateFormat b = new SimpleDateFormat("yyyyMMdd", Locale.CHINA);
  
  private static final ThreadLocal<SimpleDateFormat> c = new ThreadLocal<SimpleDateFormat>() {
      protected SimpleDateFormat a() {
        return new SimpleDateFormat("HH:mm:ss.SSS");
      }
    };
  
  private static ThreadLocal<StringBuilder> d = new ThreadLocal<StringBuilder>() {
      protected StringBuilder a() {
        return new StringBuilder(1024);
      }
    };
  
  private static String a(String paramString, String[] paramArrayOfString) {
    StringBuilder stringBuilder2 = d.get();
    Date date = new Date();
    int i = 0;
    stringBuilder2.setLength(0);
    stringBuilder2.append(((SimpleDateFormat)c.get()).format(date));
    stringBuilder2.append(paramString);
    int j = stringBuilder2.length();
    int k = paramArrayOfString.length;
    while (i < k) {
      stringBuilder2.append(paramArrayOfString[i]);
      i++;
    } 
    i = stringBuilder2.length();
    paramString = stringBuilder2.toString();
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append(b.format(date));
    stringBuilder1.append(".2log");
    String str = stringBuilder1.toString();
    Object object = a;
    /* monitor enter ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
    try {
      String str1 = Base64.encodeToString(RSAUtils.encryptByPublicKey(paramString.getBytes()), 2);
      File file = CdcUtils.openFileDir(CdcUtils.getContext(), LOG_DIR);
      if (file != null) {
        File file1 = new File(file, str);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str1);
        stringBuilder.append("\r\n");
        CdcUtils.writeFileText(stringBuilder.toString(), file1, true);
      } 
    } catch (Exception exception) {
      exception.printStackTrace();
    } finally {}
    /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
    return paramString.substring(j, i);
  }
  
  public static void cleanup() {
    Date date = new Date();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(b.format(new Date(date.getTime() - 259200000L)));
    stringBuilder.append(".2log");
    String str = stringBuilder.toString();
    File file = CdcUtils.openFileDir(CdcUtils.getContext(), LOG_DIR);
    if (file != null) {
      File[] arrayOfFile = file.listFiles(new ExLog$$Lambda$0(str));
      if (arrayOfFile != null) {
        int j = arrayOfFile.length;
        for (int i = 0; i < j; i++)
          arrayOfFile[i].delete(); 
      } 
    } 
  }
  
  public static void d(String... paramVarArgs) {
    if (AppInfoUtils.isApkInDebug(CdcUtils.getContext()))
      Log.d("CDCLogs", a(" [DEBUG] ", paramVarArgs)); 
  }
  
  public static void e(String... paramVarArgs) {
    String str = a(" [INFO ] ", paramVarArgs);
    if (AppInfoUtils.isApkInDebug(CdcUtils.getContext()))
      Log.e("CDCLogs", str); 
  }
  
  public static void i(String... paramVarArgs) {
    String str = a(" [INFO ] ", paramVarArgs);
    if (AppInfoUtils.isApkInDebug(CdcUtils.getContext()))
      Log.i("CDCLogs", str); 
  }
  
  public static void w(String... paramVarArgs) {
    String str = a(" [INFO ] ", paramVarArgs);
    if (AppInfoUtils.isApkInDebug(CdcUtils.getContext()))
      Log.w("CDCLogs", str); 
  }
  
  static {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("CDCLogs");
    stringBuilder.append(File.separator);
    stringBuilder.append("Log");
    LOG_DIR = stringBuilder.toString();
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclien\\utils\ExLog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */