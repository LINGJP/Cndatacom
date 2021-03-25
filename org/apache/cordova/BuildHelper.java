package org.apache.cordova;

import android.content.Context;

public class BuildHelper {
  private static String TAG = "BuildHelper";
  
  public static Object getBuildConfigValue(Context paramContext, String paramString) {
    try {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(paramContext.getPackageName());
      stringBuilder.append(".BuildConfig");
      return Class.forName(stringBuilder.toString()).getField(paramString).get(null);
    } catch (ClassNotFoundException classNotFoundException) {
      LOG.d(TAG, "Unable to get the BuildConfig, is this built with ANT?");
      classNotFoundException.printStackTrace();
      return null;
    } catch (NoSuchFieldException noSuchFieldException) {
      String str = TAG;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append(" is not a valid field. Check your build.gradle");
      LOG.d(str, stringBuilder.toString());
      return null;
    } catch (IllegalAccessException illegalAccessException) {
      LOG.d(TAG, "Illegal Access Exception: Let's print a stack trace.");
      illegalAccessException.printStackTrace();
      return null;
    } 
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\org\apache\cordova\BuildHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */