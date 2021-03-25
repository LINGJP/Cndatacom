package org.apache.cordova;

import android.annotation.SuppressLint;
import java.security.SecureRandom;
import org.json.JSONArray;
import org.json.JSONException;

public class CordovaBridge {
  private static final String LOG_TAG = "CordovaBridge";
  
  private volatile int expectedBridgeSecret = -1;
  
  private NativeToJsMessageQueue jsMessageQueue;
  
  private PluginManager pluginManager;
  
  public CordovaBridge(PluginManager paramPluginManager, NativeToJsMessageQueue paramNativeToJsMessageQueue) {
    this.pluginManager = paramPluginManager;
    this.jsMessageQueue = paramNativeToJsMessageQueue;
  }
  
  private boolean verifySecret(String paramString, int paramInt) {
    if (!this.jsMessageQueue.isBridgeEnabled()) {
      if (paramInt == -1) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(paramString);
        stringBuilder.append(" call made before bridge was enabled.");
        LOG.d("CordovaBridge", stringBuilder.toString());
      } else {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Ignoring ");
        stringBuilder.append(paramString);
        stringBuilder.append(" from previous page load.");
        LOG.d("CordovaBridge", stringBuilder.toString());
      } 
      return false;
    } 
    if (this.expectedBridgeSecret < 0 || paramInt != this.expectedBridgeSecret) {
      LOG.e("CordovaBridge", "Bridge access attempt with wrong secret token, possibly from malicious code. Disabling exec() bridge!");
      clearBridgeSecret();
      throw new IllegalAccessException();
    } 
    return true;
  }
  
  void clearBridgeSecret() {
    this.expectedBridgeSecret = -1;
  }
  
  @SuppressLint({"TrulyRandom"})
  int generateBridgeSecret() {
    this.expectedBridgeSecret = (new SecureRandom()).nextInt(2147483647);
    return this.expectedBridgeSecret;
  }
  
  public boolean isSecretEstablished() {
    return (this.expectedBridgeSecret != -1);
  }
  
  public String jsExec(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4) {
    if (!verifySecret("exec()", paramInt))
      return null; 
    if (paramString4 == null)
      return "@Null arguments."; 
    this.jsMessageQueue.setPaused(true);
    try {
      CordovaResourceApi.jsThread = Thread.currentThread();
      this.pluginManager.exec(paramString1, paramString2, paramString3, paramString4);
      paramString1 = this.jsMessageQueue.popAndEncode(false);
      this.jsMessageQueue.setPaused(false);
      return paramString1;
    } catch (Throwable throwable) {
      throwable.printStackTrace();
      this.jsMessageQueue.setPaused(false);
      return "";
    } finally {}
    this.jsMessageQueue.setPaused(false);
    throw paramString1;
  }
  
  public String jsRetrieveJsMessages(int paramInt, boolean paramBoolean) {
    return !verifySecret("retrieveJsMessages()", paramInt) ? null : this.jsMessageQueue.popAndEncode(paramBoolean);
  }
  
  public void jsSetNativeToJsBridgeMode(int paramInt1, int paramInt2) {
    if (!verifySecret("setNativeToJsBridgeMode()", paramInt1))
      return; 
    this.jsMessageQueue.setBridgeMode(paramInt2);
  }
  
  public String promptOnJsPrompt(String paramString1, String paramString2, String paramString3) {
    if (paramString3 != null && paramString3.length() > 3 && paramString3.startsWith("gap:")) {
      try {
        JSONArray jSONArray = new JSONArray(paramString3.substring(4));
        paramString2 = jsExec(jSONArray.getInt(0), jSONArray.getString(1), jSONArray.getString(2), jSONArray.getString(3), paramString2);
        String str = paramString2;
        if (paramString2 == null)
          str = ""; 
        return str;
      } catch (JSONException jSONException) {
        jSONException.printStackTrace();
      } catch (IllegalAccessException null) {
        illegalAccessException.printStackTrace();
      } 
      return "";
    } 
    if (paramString3 != null && paramString3.startsWith("gap_bridge_mode:")) {
      try {
        jsSetNativeToJsBridgeMode(Integer.parseInt(paramString3.substring(16)), Integer.parseInt(paramString2));
      } catch (NumberFormatException numberFormatException) {
        numberFormatException.printStackTrace();
      } catch (IllegalAccessException null) {
        illegalAccessException.printStackTrace();
      } 
      return "";
    } 
    if (paramString3 != null && paramString3.startsWith("gap_poll:")) {
      int i = Integer.parseInt(paramString3.substring(9));
      try {
        paramString2 = jsRetrieveJsMessages(i, "1".equals(paramString2));
        paramString1 = paramString2;
        if (paramString2 == null)
          paramString1 = ""; 
        return paramString1;
      } catch (IllegalAccessException illegalAccessException) {
        illegalAccessException.printStackTrace();
        return "";
      } 
    } 
    if (paramString3 != null && paramString3.startsWith("gap_init:")) {
      StringBuilder stringBuilder1;
      if (this.pluginManager.shouldAllowBridgeAccess((String)illegalAccessException)) {
        int i = Integer.parseInt(paramString3.substring(9));
        this.jsMessageQueue.setBridgeMode(i);
        i = generateBridgeSecret();
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("");
        stringBuilder1.append(i);
        return stringBuilder1.toString();
      } 
      StringBuilder stringBuilder2 = new StringBuilder();
      stringBuilder2.append("gap_init called from restricted origin: ");
      stringBuilder2.append((String)stringBuilder1);
      LOG.e("CordovaBridge", stringBuilder2.toString());
      return "";
    } 
    return null;
  }
  
  public void reset() {
    this.jsMessageQueue.reset();
    clearBridgeSecret();
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\org\apache\cordova\CordovaBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */