package com.cndatacom.cnportalclient.utils;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PreferencesUtils {
  public static String PREFERENCE_NAME = "app-config";
  
  private PreferencesUtils() {
    throw new AssertionError();
  }
  
  public static boolean contains(Context paramContext, String paramString) {
    return paramContext.getSharedPreferences(PREFERENCE_NAME, 0).contains(paramString);
  }
  
  public static boolean getBoolean(Context paramContext, String paramString) {
    return getBoolean(paramContext, paramString, false);
  }
  
  public static boolean getBoolean(Context paramContext, String paramString, boolean paramBoolean) {
    return paramContext.getSharedPreferences(PREFERENCE_NAME, 0).getBoolean(paramString, paramBoolean);
  }
  
  public static SharedPreferences.Editor getEditor(Context paramContext) {
    return paramContext.getSharedPreferences(PREFERENCE_NAME, 0).edit();
  }
  
  public static float getFloat(Context paramContext, String paramString) {
    return getFloat(paramContext, paramString, -1.0F);
  }
  
  public static float getFloat(Context paramContext, String paramString, float paramFloat) {
    return paramContext.getSharedPreferences(PREFERENCE_NAME, 0).getFloat(paramString, paramFloat);
  }
  
  public static int getInt(Context paramContext, String paramString) {
    return getInt(paramContext, paramString, -1);
  }
  
  public static int getInt(Context paramContext, String paramString, int paramInt) {
    return paramContext.getSharedPreferences(PREFERENCE_NAME, 0).getInt(paramString, paramInt);
  }
  
  public static long getLong(Context paramContext, String paramString) {
    return getLong(paramContext, paramString, -1L);
  }
  
  public static long getLong(Context paramContext, String paramString, long paramLong) {
    return paramContext.getSharedPreferences(PREFERENCE_NAME, 0).getLong(paramString, paramLong);
  }
  
  public static JSONArray getObjects(Context paramContext, LinkedHashMap<String, Object> paramLinkedHashMap) {
    JSONArray jSONArray = new JSONArray();
    try {
      SharedPreferences sharedPreferences = paramContext.getSharedPreferences(PREFERENCE_NAME, 0);
      for (Map.Entry<String, Object> entry : paramLinkedHashMap.entrySet()) {
        JSONObject jSONObject = new JSONObject();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(entry.getKey());
        stringBuilder.append("");
        String str = stringBuilder.toString();
        entry = (Map.Entry<String, Object>)entry.getValue();
        if (!sharedPreferences.contains(str)) {
          jSONObject.put(str, entry);
        } else if (entry instanceof Integer) {
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append(entry);
          stringBuilder1.append("");
          jSONObject.put(str, sharedPreferences.getInt(str, Integer.parseInt(stringBuilder1.toString())));
        } else if (entry instanceof Long) {
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append(entry);
          stringBuilder1.append("");
          jSONObject.put(str, sharedPreferences.getLong(str, Long.parseLong(stringBuilder1.toString())));
        } else if (entry instanceof Float) {
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append(entry);
          stringBuilder1.append("");
          jSONObject.put(str, sharedPreferences.getFloat(str, Float.parseFloat(stringBuilder1.toString())));
        } else if (entry instanceof Boolean) {
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append(entry);
          stringBuilder1.append("");
          jSONObject.put(str, sharedPreferences.getBoolean(str, Boolean.parseBoolean(stringBuilder1.toString())));
        } else if (entry instanceof String) {
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append(entry);
          stringBuilder1.append("");
          jSONObject.put(str, sharedPreferences.getString(str, stringBuilder1.toString()));
        } 
        jSONArray.put(jSONObject);
      } 
    } catch (JSONException jSONException) {
      jSONException.printStackTrace();
    } 
    return jSONArray;
  }
  
  public static SharedPreferences getSharedPreferences(Context paramContext) {
    return paramContext.getSharedPreferences(PREFERENCE_NAME, 0);
  }
  
  public static String getString(Context paramContext, String paramString) {
    return getString(paramContext, paramString, null);
  }
  
  public static String getString(Context paramContext, String paramString1, String paramString2) {
    return paramContext.getSharedPreferences(PREFERENCE_NAME, 0).getString(paramString1, paramString2);
  }
  
  public static boolean putBoolean(Context paramContext, String paramString, boolean paramBoolean) {
    SharedPreferences.Editor editor = paramContext.getSharedPreferences(PREFERENCE_NAME, 0).edit();
    editor.putBoolean(paramString, paramBoolean);
    return editor.commit();
  }
  
  public static boolean putFloat(Context paramContext, String paramString, float paramFloat) {
    SharedPreferences.Editor editor = paramContext.getSharedPreferences(PREFERENCE_NAME, 0).edit();
    editor.putFloat(paramString, paramFloat);
    return editor.commit();
  }
  
  public static boolean putInt(Context paramContext, String paramString, int paramInt) {
    SharedPreferences.Editor editor = paramContext.getSharedPreferences(PREFERENCE_NAME, 0).edit();
    editor.putInt(paramString, paramInt);
    return editor.commit();
  }
  
  public static boolean putLong(Context paramContext, String paramString, long paramLong) {
    SharedPreferences.Editor editor = paramContext.getSharedPreferences(PREFERENCE_NAME, 0).edit();
    editor.putLong(paramString, paramLong);
    return editor.commit();
  }
  
  public static boolean putObjects(Context paramContext, LinkedHashMap<String, Object> paramLinkedHashMap) {
    SharedPreferences.Editor editor = paramContext.getSharedPreferences(PREFERENCE_NAME, 0).edit();
    for (Map.Entry<String, Object> entry : paramLinkedHashMap.entrySet()) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(entry.getKey());
      stringBuilder.append("");
      String str = stringBuilder.toString();
      entry = (Map.Entry<String, Object>)entry.getValue();
      if (entry instanceof Integer) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(entry);
        stringBuilder1.append("");
        editor.putInt(str, Integer.parseInt(stringBuilder1.toString()));
        continue;
      } 
      if (entry instanceof Long) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(entry);
        stringBuilder1.append("");
        editor.putLong(str, Long.parseLong(stringBuilder1.toString()));
        continue;
      } 
      if (entry instanceof Float) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(entry);
        stringBuilder1.append("");
        editor.putFloat(str, Float.parseFloat(stringBuilder1.toString()));
        continue;
      } 
      if (entry instanceof Boolean) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(entry);
        stringBuilder1.append("");
        editor.putBoolean(str, Boolean.parseBoolean(stringBuilder1.toString()));
        continue;
      } 
      if (entry instanceof String) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(entry);
        stringBuilder1.append("");
        editor.putString(str, stringBuilder1.toString());
      } 
    } 
    return editor.commit();
  }
  
  public static boolean putString(Context paramContext, String paramString1, String paramString2) {
    SharedPreferences.Editor editor = paramContext.getSharedPreferences(PREFERENCE_NAME, 0).edit();
    editor.putString(paramString1, paramString2);
    return editor.commit();
  }
  
  public static boolean remove(Context paramContext, String paramString) {
    SharedPreferences.Editor editor = paramContext.getSharedPreferences(PREFERENCE_NAME, 0).edit();
    editor.remove(paramString);
    return editor.commit();
  }
  
  public static boolean removes(Context paramContext, String[] paramArrayOfString) {
    String str = PREFERENCE_NAME;
    int i = 0;
    SharedPreferences.Editor editor = paramContext.getSharedPreferences(str, 0).edit();
    if (paramArrayOfString != null && paramArrayOfString.length > 0)
      while (i < paramArrayOfString.length) {
        editor.remove(paramArrayOfString[i]);
        i++;
      }  
    return editor.commit();
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclien\\utils\PreferencesUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */