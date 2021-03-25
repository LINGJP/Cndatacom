package org.apache.cordova;

import android.content.Context;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CoreAndroid extends CordovaPlugin {
  public static final String PLUGIN_NAME = "CoreAndroid";
  
  protected static final String TAG = "CordovaApp";
  
  private CallbackContext messageChannel;
  
  private final Object messageChannelLock = new Object();
  
  private PluginResult pendingResume;
  
  public static Object getBuildConfigValue(Context paramContext, String paramString) {
    try {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(paramContext.getPackageName());
      stringBuilder.append(".BuildConfig");
      return Class.forName(stringBuilder.toString()).getField(paramString).get(null);
    } catch (ClassNotFoundException classNotFoundException) {
      LOG.d("CordovaApp", "Unable to get the BuildConfig, is this built with ANT?");
      classNotFoundException.printStackTrace();
      return null;
    } catch (NoSuchFieldException noSuchFieldException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append(" is not a valid field. Check your build.gradle");
      LOG.d("CordovaApp", stringBuilder.toString());
      return null;
    } catch (IllegalAccessException illegalAccessException) {
      LOG.d("CordovaApp", "Illegal Access Exception: Let's print a stack trace.");
      illegalAccessException.printStackTrace();
      return null;
    } 
  }
  
  private void sendEventMessage(String paramString) {
    JSONObject jSONObject = new JSONObject();
    try {
      jSONObject.put("action", paramString);
    } catch (JSONException jSONException) {
      LOG.e("CordovaApp", "Failed to create event message", (Throwable)jSONException);
    } 
    sendEventMessage(new PluginResult(PluginResult.Status.OK, jSONObject));
  }
  
  private void sendEventMessage(PluginResult paramPluginResult) {
    paramPluginResult.setKeepCallback(true);
    if (this.messageChannel != null)
      this.messageChannel.sendPluginResult(paramPluginResult); 
  }
  
  public void backHistory() {
    this.cordova.getActivity().runOnUiThread(new Runnable() {
          public void run() {
            CoreAndroid.this.webView.backHistory();
          }
        });
  }
  
  public void clearCache() {
    this.cordova.getActivity().runOnUiThread(new Runnable() {
          public void run() {
            CoreAndroid.this.webView.clearCache(true);
          }
        });
  }
  
  public void clearHistory() {
    this.cordova.getActivity().runOnUiThread(new Runnable() {
          public void run() {
            CoreAndroid.this.webView.clearHistory();
          }
        });
  }
  
  public boolean execute(String paramString, JSONArray paramJSONArray, CallbackContext paramCallbackContext) {
    PluginResult.Status status = PluginResult.Status.OK;
    try {
      if (paramString.equals("clearCache")) {
        clearCache();
      } else if (paramString.equals("show")) {
        this.cordova.getActivity().runOnUiThread(new Runnable() {
              public void run() {
                CoreAndroid.this.webView.getPluginManager().postMessage("spinner", "stop");
              }
            });
      } else if (paramString.equals("loadUrl")) {
        loadUrl(paramJSONArray.getString(0), paramJSONArray.optJSONObject(1));
      } else if (!paramString.equals("cancelLoadUrl")) {
        if (paramString.equals("clearHistory")) {
          clearHistory();
        } else if (paramString.equals("backHistory")) {
          backHistory();
        } else if (paramString.equals("overrideButton")) {
          overrideButton(paramJSONArray.getString(0), paramJSONArray.getBoolean(1));
        } else if (paramString.equals("overrideBackbutton")) {
          overrideBackbutton(paramJSONArray.getBoolean(0));
        } else if (paramString.equals("exitApp")) {
          exitApp();
        } else if (paramString.equals("messageChannel")) {
          synchronized (this.messageChannelLock) {
            this.messageChannel = paramCallbackContext;
            if (this.pendingResume != null) {
              sendEventMessage(this.pendingResume);
              this.pendingResume = null;
            } 
            return true;
          } 
        } 
      } 
      paramCallbackContext.sendPluginResult(new PluginResult(status, ""));
      return true;
    } catch (JSONException jSONException) {
      paramCallbackContext.sendPluginResult(new PluginResult(PluginResult.Status.JSON_EXCEPTION));
      return false;
    } 
  }
  
  public void exitApp() {
    this.webView.getPluginManager().postMessage("exit", null);
  }
  
  public void fireJavascriptEvent(String paramString) {
    sendEventMessage(paramString);
  }
  
  public boolean isBackbuttonOverridden() {
    return this.webView.isButtonPlumbedToJs(4);
  }
  
  public void loadUrl(String paramString, JSONObject paramJSONObject) {
    // Byte code:
    //   0: new java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore #12
    //   9: aload #12
    //   11: ldc_w 'App.loadUrl('
    //   14: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   17: pop
    //   18: aload #12
    //   20: aload_1
    //   21: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   24: pop
    //   25: aload #12
    //   27: ldc_w ','
    //   30: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   33: pop
    //   34: aload #12
    //   36: aload_2
    //   37: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   40: pop
    //   41: aload #12
    //   43: ldc_w ')'
    //   46: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   49: pop
    //   50: ldc_w 'App'
    //   53: aload #12
    //   55: invokevirtual toString : ()Ljava/lang/String;
    //   58: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)V
    //   61: new java/util/HashMap
    //   64: dup
    //   65: invokespecial <init> : ()V
    //   68: astore #12
    //   70: iconst_0
    //   71: istore_3
    //   72: iconst_0
    //   73: istore #4
    //   75: aload_2
    //   76: ifnull -> 380
    //   79: aload_2
    //   80: invokevirtual names : ()Lorg/json/JSONArray;
    //   83: astore #13
    //   85: iconst_0
    //   86: istore_3
    //   87: iconst_0
    //   88: istore #7
    //   90: iconst_0
    //   91: istore #6
    //   93: iload #4
    //   95: aload #13
    //   97: invokevirtual length : ()I
    //   100: if_icmpge -> 377
    //   103: aload #13
    //   105: iload #4
    //   107: invokevirtual getString : (I)Ljava/lang/String;
    //   110: astore #14
    //   112: aload #14
    //   114: ldc_w 'wait'
    //   117: invokevirtual equals : (Ljava/lang/Object;)Z
    //   120: ifeq -> 142
    //   123: aload_2
    //   124: aload #14
    //   126: invokevirtual getInt : (Ljava/lang/String;)I
    //   129: istore #5
    //   131: iload #7
    //   133: istore #8
    //   135: iload #6
    //   137: istore #9
    //   139: goto -> 357
    //   142: aload #14
    //   144: ldc_w 'openexternal'
    //   147: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   150: ifeq -> 171
    //   153: aload_2
    //   154: aload #14
    //   156: invokevirtual getBoolean : (Ljava/lang/String;)Z
    //   159: istore #8
    //   161: iload_3
    //   162: istore #5
    //   164: iload #6
    //   166: istore #9
    //   168: goto -> 357
    //   171: aload #14
    //   173: ldc_w 'clearhistory'
    //   176: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   179: ifeq -> 200
    //   182: aload_2
    //   183: aload #14
    //   185: invokevirtual getBoolean : (Ljava/lang/String;)Z
    //   188: istore #9
    //   190: iload_3
    //   191: istore #5
    //   193: iload #7
    //   195: istore #8
    //   197: goto -> 357
    //   200: aload_2
    //   201: aload #14
    //   203: invokevirtual get : (Ljava/lang/String;)Ljava/lang/Object;
    //   206: astore #15
    //   208: aload #15
    //   210: ifnonnull -> 227
    //   213: iload_3
    //   214: istore #5
    //   216: iload #7
    //   218: istore #8
    //   220: iload #6
    //   222: istore #9
    //   224: goto -> 357
    //   227: aload #15
    //   229: invokevirtual getClass : ()Ljava/lang/Class;
    //   232: ldc java/lang/String
    //   234: invokevirtual equals : (Ljava/lang/Object;)Z
    //   237: ifeq -> 267
    //   240: aload #12
    //   242: aload #14
    //   244: aload #15
    //   246: checkcast java/lang/String
    //   249: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   252: pop
    //   253: iload_3
    //   254: istore #5
    //   256: iload #7
    //   258: istore #8
    //   260: iload #6
    //   262: istore #9
    //   264: goto -> 357
    //   267: aload #15
    //   269: invokevirtual getClass : ()Ljava/lang/Class;
    //   272: ldc_w java/lang/Boolean
    //   275: invokevirtual equals : (Ljava/lang/Object;)Z
    //   278: ifeq -> 308
    //   281: aload #12
    //   283: aload #14
    //   285: aload #15
    //   287: checkcast java/lang/Boolean
    //   290: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   293: pop
    //   294: iload_3
    //   295: istore #5
    //   297: iload #7
    //   299: istore #8
    //   301: iload #6
    //   303: istore #9
    //   305: goto -> 357
    //   308: iload_3
    //   309: istore #5
    //   311: iload #7
    //   313: istore #8
    //   315: iload #6
    //   317: istore #9
    //   319: aload #15
    //   321: invokevirtual getClass : ()Ljava/lang/Class;
    //   324: ldc_w java/lang/Integer
    //   327: invokevirtual equals : (Ljava/lang/Object;)Z
    //   330: ifeq -> 357
    //   333: aload #12
    //   335: aload #14
    //   337: aload #15
    //   339: checkcast java/lang/Integer
    //   342: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   345: pop
    //   346: iload #6
    //   348: istore #9
    //   350: iload #7
    //   352: istore #8
    //   354: iload_3
    //   355: istore #5
    //   357: iload #4
    //   359: iconst_1
    //   360: iadd
    //   361: istore #4
    //   363: iload #5
    //   365: istore_3
    //   366: iload #8
    //   368: istore #7
    //   370: iload #9
    //   372: istore #6
    //   374: goto -> 93
    //   377: goto -> 386
    //   380: iconst_0
    //   381: istore #7
    //   383: iconst_0
    //   384: istore #6
    //   386: iload_3
    //   387: ifle -> 417
    //   390: aload_0
    //   391: monitorenter
    //   392: iload_3
    //   393: i2l
    //   394: lstore #10
    //   396: aload_0
    //   397: lload #10
    //   399: invokevirtual wait : (J)V
    //   402: aload_0
    //   403: monitorexit
    //   404: goto -> 417
    //   407: astore_2
    //   408: aload_0
    //   409: monitorexit
    //   410: aload_2
    //   411: athrow
    //   412: astore_2
    //   413: aload_2
    //   414: invokevirtual printStackTrace : ()V
    //   417: aload_0
    //   418: getfield webView : Lorg/apache/cordova/CordovaWebView;
    //   421: aload_1
    //   422: iload #7
    //   424: iload #6
    //   426: aload #12
    //   428: invokeinterface showWebPage : (Ljava/lang/String;ZZLjava/util/Map;)V
    //   433: return
    // Exception table:
    //   from	to	target	type
    //   390	392	412	java/lang/InterruptedException
    //   396	404	407	finally
    //   408	410	407	finally
    //   410	412	412	java/lang/InterruptedException
  }
  
  public void onDestroy() {}
  
  public void overrideBackbutton(boolean paramBoolean) {
    LOG.i("App", "WARNING: Back Button Default Behavior will be overridden.  The backbutton event will be fired!");
    this.webView.setButtonPlumbedToJs(4, paramBoolean);
  }
  
  public void overrideButton(String paramString, boolean paramBoolean) {
    LOG.i("App", "WARNING: Volume Button Default Behavior will be overridden.  The volume event will be fired!");
    if (paramString.equals("volumeup")) {
      this.webView.setButtonPlumbedToJs(24, paramBoolean);
      return;
    } 
    if (paramString.equals("volumedown")) {
      this.webView.setButtonPlumbedToJs(25, paramBoolean);
      return;
    } 
    if (paramString.equals("menubutton"))
      this.webView.setButtonPlumbedToJs(82, paramBoolean); 
  }
  
  public void pluginInitialize() {}
  
  public void sendResumeEvent(PluginResult paramPluginResult) {
    synchronized (this.messageChannelLock) {
      if (this.messageChannel != null) {
        sendEventMessage(paramPluginResult);
      } else {
        this.pendingResume = paramPluginResult;
      } 
      return;
    } 
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\org\apache\cordova\CoreAndroid.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */