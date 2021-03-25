package org.apache.cordova;

import org.json.JSONArray;
import org.json.JSONObject;

public class CallbackContext {
  private static final String LOG_TAG = "CordovaPlugin";
  
  private String callbackId;
  
  private int changingThreads;
  
  protected boolean finished;
  
  private CordovaWebView webView;
  
  public CallbackContext(String paramString, CordovaWebView paramCordovaWebView) {
    this.callbackId = paramString;
    this.webView = paramCordovaWebView;
  }
  
  public void error(int paramInt) {
    sendPluginResult(new PluginResult(PluginResult.Status.ERROR, paramInt));
  }
  
  public void error(String paramString) {
    sendPluginResult(new PluginResult(PluginResult.Status.ERROR, paramString));
  }
  
  public void error(JSONObject paramJSONObject) {
    sendPluginResult(new PluginResult(PluginResult.Status.ERROR, paramJSONObject));
  }
  
  public String getCallbackId() {
    return this.callbackId;
  }
  
  public boolean isChangingThreads() {
    return (this.changingThreads > 0);
  }
  
  public boolean isFinished() {
    return this.finished;
  }
  
  public void sendPluginResult(PluginResult paramPluginResult) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield finished : Z
    //   6: ifeq -> 61
    //   9: new java/lang/StringBuilder
    //   12: dup
    //   13: invokespecial <init> : ()V
    //   16: astore_2
    //   17: aload_2
    //   18: ldc 'Attempted to send a second callback for ID: '
    //   20: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   23: pop
    //   24: aload_2
    //   25: aload_0
    //   26: getfield callbackId : Ljava/lang/String;
    //   29: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   32: pop
    //   33: aload_2
    //   34: ldc '\\nResult was: '
    //   36: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   39: pop
    //   40: aload_2
    //   41: aload_1
    //   42: invokevirtual getMessage : ()Ljava/lang/String;
    //   45: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   48: pop
    //   49: ldc 'CordovaPlugin'
    //   51: aload_2
    //   52: invokevirtual toString : ()Ljava/lang/String;
    //   55: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)V
    //   58: aload_0
    //   59: monitorexit
    //   60: return
    //   61: aload_0
    //   62: aload_1
    //   63: invokevirtual getKeepCallback : ()Z
    //   66: iconst_1
    //   67: ixor
    //   68: putfield finished : Z
    //   71: aload_0
    //   72: monitorexit
    //   73: aload_0
    //   74: getfield webView : Lorg/apache/cordova/CordovaWebView;
    //   77: aload_1
    //   78: aload_0
    //   79: getfield callbackId : Ljava/lang/String;
    //   82: invokeinterface sendPluginResult : (Lorg/apache/cordova/PluginResult;Ljava/lang/String;)V
    //   87: return
    //   88: astore_1
    //   89: aload_0
    //   90: monitorexit
    //   91: aload_1
    //   92: athrow
    // Exception table:
    //   from	to	target	type
    //   2	60	88	finally
    //   61	73	88	finally
    //   89	91	88	finally
  }
  
  public void success() {
    sendPluginResult(new PluginResult(PluginResult.Status.OK));
  }
  
  public void success(int paramInt) {
    sendPluginResult(new PluginResult(PluginResult.Status.OK, paramInt));
  }
  
  public void success(String paramString) {
    sendPluginResult(new PluginResult(PluginResult.Status.OK, paramString));
  }
  
  public void success(JSONArray paramJSONArray) {
    sendPluginResult(new PluginResult(PluginResult.Status.OK, paramJSONArray));
  }
  
  public void success(JSONObject paramJSONObject) {
    sendPluginResult(new PluginResult(PluginResult.Status.OK, paramJSONObject));
  }
  
  public void success(byte[] paramArrayOfbyte) {
    sendPluginResult(new PluginResult(PluginResult.Status.OK, paramArrayOfbyte));
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\org\apache\cordova\CallbackContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */