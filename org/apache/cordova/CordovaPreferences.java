package org.apache.cordova;

import android.os.Bundle;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CordovaPreferences {
  private Bundle preferencesBundleExtras;
  
  private HashMap<String, String> prefs = new HashMap<String, String>(20);
  
  public boolean contains(String paramString) {
    return (getString(paramString, null) != null);
  }
  
  public Map<String, String> getAll() {
    return this.prefs;
  }
  
  public boolean getBoolean(String paramString, boolean paramBoolean) {
    paramString = paramString.toLowerCase(Locale.ENGLISH);
    paramString = this.prefs.get(paramString);
    return (paramString != null) ? Boolean.parseBoolean(paramString) : paramBoolean;
  }
  
  public double getDouble(String paramString, double paramDouble) {
    paramString = paramString.toLowerCase(Locale.ENGLISH);
    paramString = this.prefs.get(paramString);
    return (paramString != null) ? Double.valueOf(paramString).doubleValue() : paramDouble;
  }
  
  public int getInteger(String paramString, int paramInt) {
    paramString = paramString.toLowerCase(Locale.ENGLISH);
    paramString = this.prefs.get(paramString);
    return (paramString != null) ? (int)Long.decode(paramString).longValue() : paramInt;
  }
  
  public String getString(String paramString1, String paramString2) {
    paramString1 = paramString1.toLowerCase(Locale.ENGLISH);
    paramString1 = this.prefs.get(paramString1);
    return (paramString1 != null) ? paramString1 : paramString2;
  }
  
  public void set(String paramString, double paramDouble) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("");
    stringBuilder.append(paramDouble);
    set(paramString, stringBuilder.toString());
  }
  
  public void set(String paramString, int paramInt) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("");
    stringBuilder.append(paramInt);
    set(paramString, stringBuilder.toString());
  }
  
  public void set(String paramString1, String paramString2) {
    this.prefs.put(paramString1.toLowerCase(Locale.ENGLISH), paramString2);
  }
  
  public void set(String paramString, boolean paramBoolean) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("");
    stringBuilder.append(paramBoolean);
    set(paramString, stringBuilder.toString());
  }
  
  public void setPreferencesBundle(Bundle paramBundle) {
    this.preferencesBundleExtras = paramBundle;
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\org\apache\cordova\CordovaPreferences.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */