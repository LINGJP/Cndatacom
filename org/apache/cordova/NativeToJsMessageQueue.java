package org.apache.cordova;

import java.util.ArrayList;
import java.util.LinkedList;

public class NativeToJsMessageQueue {
  static final boolean DISABLE_EXEC_CHAINING = false;
  
  private static final boolean FORCE_ENCODE_USING_EVAL = false;
  
  private static final String LOG_TAG = "JsMessageQueue";
  
  private static int MAX_PAYLOAD_SIZE = 524288000;
  
  private BridgeMode activeBridgeMode;
  
  private ArrayList<BridgeMode> bridgeModes = new ArrayList<BridgeMode>();
  
  private boolean paused;
  
  private final LinkedList<JsMessage> queue = new LinkedList<JsMessage>();
  
  private int calculatePackedMessageLength(JsMessage paramJsMessage) {
    int i = paramJsMessage.calculateEncodedLength();
    return String.valueOf(i).length() + i + 1;
  }
  
  private void enqueueMessage(JsMessage paramJsMessage) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield activeBridgeMode : Lorg/apache/cordova/NativeToJsMessageQueue$BridgeMode;
    //   6: ifnonnull -> 19
    //   9: ldc 'JsMessageQueue'
    //   11: ldc 'Dropping Native->JS message due to disabled bridge'
    //   13: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)V
    //   16: aload_0
    //   17: monitorexit
    //   18: return
    //   19: aload_0
    //   20: getfield queue : Ljava/util/LinkedList;
    //   23: aload_1
    //   24: invokevirtual add : (Ljava/lang/Object;)Z
    //   27: pop
    //   28: aload_0
    //   29: getfield paused : Z
    //   32: ifne -> 43
    //   35: aload_0
    //   36: getfield activeBridgeMode : Lorg/apache/cordova/NativeToJsMessageQueue$BridgeMode;
    //   39: aload_0
    //   40: invokevirtual onNativeToJsMessageAvailable : (Lorg/apache/cordova/NativeToJsMessageQueue;)V
    //   43: aload_0
    //   44: monitorexit
    //   45: return
    //   46: astore_1
    //   47: aload_0
    //   48: monitorexit
    //   49: aload_1
    //   50: athrow
    // Exception table:
    //   from	to	target	type
    //   2	18	46	finally
    //   19	43	46	finally
    //   43	45	46	finally
    //   47	49	46	finally
  }
  
  private void packMessage(JsMessage paramJsMessage, StringBuilder paramStringBuilder) {
    paramStringBuilder.append(paramJsMessage.calculateEncodedLength());
    paramStringBuilder.append(' ');
    paramJsMessage.encodeAsMessage(paramStringBuilder);
  }
  
  public void addBridgeMode(BridgeMode paramBridgeMode) {
    this.bridgeModes.add(paramBridgeMode);
  }
  
  public void addJavaScript(String paramString) {
    enqueueMessage(new JsMessage(paramString));
  }
  
  public void addPluginResult(PluginResult paramPluginResult, String paramString) {
    boolean bool;
    if (paramString == null) {
      LOG.e("JsMessageQueue", "Got plugin result with no callbackId", new Throwable());
      return;
    } 
    if (paramPluginResult.getStatus() == PluginResult.Status.NO_RESULT.ordinal()) {
      bool = true;
    } else {
      bool = false;
    } 
    boolean bool1 = paramPluginResult.getKeepCallback();
    if (bool && bool1)
      return; 
    enqueueMessage(new JsMessage(paramPluginResult, paramString));
  }
  
  public boolean isBridgeEnabled() {
    return (this.activeBridgeMode != null);
  }
  
  public boolean isEmpty() {
    return this.queue.isEmpty();
  }
  
  public String popAndEncode(boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield activeBridgeMode : Lorg/apache/cordova/NativeToJsMessageQueue$BridgeMode;
    //   6: ifnonnull -> 13
    //   9: aload_0
    //   10: monitorexit
    //   11: aconst_null
    //   12: areturn
    //   13: aload_0
    //   14: getfield activeBridgeMode : Lorg/apache/cordova/NativeToJsMessageQueue$BridgeMode;
    //   17: aload_0
    //   18: iload_1
    //   19: invokevirtual notifyOfFlush : (Lorg/apache/cordova/NativeToJsMessageQueue;Z)V
    //   22: aload_0
    //   23: getfield queue : Ljava/util/LinkedList;
    //   26: invokevirtual isEmpty : ()Z
    //   29: ifeq -> 36
    //   32: aload_0
    //   33: monitorexit
    //   34: aconst_null
    //   35: areturn
    //   36: aload_0
    //   37: getfield queue : Ljava/util/LinkedList;
    //   40: invokevirtual iterator : ()Ljava/util/Iterator;
    //   43: astore #6
    //   45: iconst_0
    //   46: istore #4
    //   48: iconst_0
    //   49: istore_2
    //   50: iconst_0
    //   51: istore_3
    //   52: aload #6
    //   54: invokeinterface hasNext : ()Z
    //   59: ifeq -> 101
    //   62: aload_0
    //   63: aload #6
    //   65: invokeinterface next : ()Ljava/lang/Object;
    //   70: checkcast org/apache/cordova/NativeToJsMessageQueue$JsMessage
    //   73: invokespecial calculatePackedMessageLength : (Lorg/apache/cordova/NativeToJsMessageQueue$JsMessage;)I
    //   76: istore #5
    //   78: iload_2
    //   79: ifle -> 179
    //   82: iload_3
    //   83: iload #5
    //   85: iadd
    //   86: getstatic org/apache/cordova/NativeToJsMessageQueue.MAX_PAYLOAD_SIZE : I
    //   89: if_icmple -> 179
    //   92: getstatic org/apache/cordova/NativeToJsMessageQueue.MAX_PAYLOAD_SIZE : I
    //   95: ifle -> 179
    //   98: goto -> 101
    //   101: new java/lang/StringBuilder
    //   104: dup
    //   105: iload_3
    //   106: invokespecial <init> : (I)V
    //   109: astore #6
    //   111: iload #4
    //   113: istore_3
    //   114: iload_3
    //   115: iload_2
    //   116: if_icmpge -> 142
    //   119: aload_0
    //   120: aload_0
    //   121: getfield queue : Ljava/util/LinkedList;
    //   124: invokevirtual removeFirst : ()Ljava/lang/Object;
    //   127: checkcast org/apache/cordova/NativeToJsMessageQueue$JsMessage
    //   130: aload #6
    //   132: invokespecial packMessage : (Lorg/apache/cordova/NativeToJsMessageQueue$JsMessage;Ljava/lang/StringBuilder;)V
    //   135: iload_3
    //   136: iconst_1
    //   137: iadd
    //   138: istore_3
    //   139: goto -> 114
    //   142: aload_0
    //   143: getfield queue : Ljava/util/LinkedList;
    //   146: invokevirtual isEmpty : ()Z
    //   149: ifne -> 160
    //   152: aload #6
    //   154: bipush #42
    //   156: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   159: pop
    //   160: aload #6
    //   162: invokevirtual toString : ()Ljava/lang/String;
    //   165: astore #6
    //   167: aload_0
    //   168: monitorexit
    //   169: aload #6
    //   171: areturn
    //   172: astore #6
    //   174: aload_0
    //   175: monitorexit
    //   176: aload #6
    //   178: athrow
    //   179: iload_3
    //   180: iload #5
    //   182: iadd
    //   183: istore_3
    //   184: iload_2
    //   185: iconst_1
    //   186: iadd
    //   187: istore_2
    //   188: goto -> 52
    // Exception table:
    //   from	to	target	type
    //   2	11	172	finally
    //   13	34	172	finally
    //   36	45	172	finally
    //   52	78	172	finally
    //   82	98	172	finally
    //   101	111	172	finally
    //   119	135	172	finally
    //   142	160	172	finally
    //   160	169	172	finally
    //   174	176	172	finally
  }
  
  public String popAndEncodeAsJs() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield queue : Ljava/util/LinkedList;
    //   6: invokevirtual size : ()I
    //   9: ifne -> 16
    //   12: aload_0
    //   13: monitorexit
    //   14: aconst_null
    //   15: areturn
    //   16: aload_0
    //   17: getfield queue : Ljava/util/LinkedList;
    //   20: invokevirtual iterator : ()Ljava/util/Iterator;
    //   23: astore #6
    //   25: iconst_0
    //   26: istore #5
    //   28: iconst_0
    //   29: istore_2
    //   30: iconst_0
    //   31: istore_3
    //   32: aload #6
    //   34: invokeinterface hasNext : ()Z
    //   39: ifeq -> 81
    //   42: aload #6
    //   44: invokeinterface next : ()Ljava/lang/Object;
    //   49: checkcast org/apache/cordova/NativeToJsMessageQueue$JsMessage
    //   52: invokevirtual calculateEncodedLength : ()I
    //   55: bipush #50
    //   57: iadd
    //   58: istore_1
    //   59: iload_2
    //   60: ifle -> 242
    //   63: iload_3
    //   64: iload_1
    //   65: iadd
    //   66: getstatic org/apache/cordova/NativeToJsMessageQueue.MAX_PAYLOAD_SIZE : I
    //   69: if_icmple -> 242
    //   72: getstatic org/apache/cordova/NativeToJsMessageQueue.MAX_PAYLOAD_SIZE : I
    //   75: ifle -> 242
    //   78: goto -> 81
    //   81: iload_2
    //   82: aload_0
    //   83: getfield queue : Ljava/util/LinkedList;
    //   86: invokevirtual size : ()I
    //   89: if_icmpne -> 253
    //   92: iconst_1
    //   93: istore_1
    //   94: goto -> 97
    //   97: iload_1
    //   98: ifeq -> 258
    //   101: iconst_0
    //   102: istore #4
    //   104: goto -> 107
    //   107: new java/lang/StringBuilder
    //   110: dup
    //   111: iload_3
    //   112: iload #4
    //   114: iadd
    //   115: invokespecial <init> : (I)V
    //   118: astore #6
    //   120: iload #5
    //   122: istore_3
    //   123: iload_3
    //   124: iload_2
    //   125: if_icmpge -> 187
    //   128: aload_0
    //   129: getfield queue : Ljava/util/LinkedList;
    //   132: invokevirtual removeFirst : ()Ljava/lang/Object;
    //   135: checkcast org/apache/cordova/NativeToJsMessageQueue$JsMessage
    //   138: astore #7
    //   140: iload_1
    //   141: ifeq -> 161
    //   144: iload_3
    //   145: iconst_1
    //   146: iadd
    //   147: iload_2
    //   148: if_icmpne -> 161
    //   151: aload #7
    //   153: aload #6
    //   155: invokevirtual encodeAsJsMessage : (Ljava/lang/StringBuilder;)V
    //   158: goto -> 265
    //   161: aload #6
    //   163: ldc 'try{'
    //   165: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   168: pop
    //   169: aload #7
    //   171: aload #6
    //   173: invokevirtual encodeAsJsMessage : (Ljava/lang/StringBuilder;)V
    //   176: aload #6
    //   178: ldc '}finally{'
    //   180: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   183: pop
    //   184: goto -> 265
    //   187: iload_1
    //   188: istore_3
    //   189: iload_1
    //   190: ifne -> 203
    //   193: aload #6
    //   195: ldc 'window.setTimeout(function(){cordova.require('cordova/plugin/android/polling').pollOnce();},0);'
    //   197: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   200: pop
    //   201: iload_1
    //   202: istore_3
    //   203: iload_3
    //   204: iload_2
    //   205: if_icmpge -> 223
    //   208: aload #6
    //   210: bipush #125
    //   212: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   215: pop
    //   216: iload_3
    //   217: iconst_1
    //   218: iadd
    //   219: istore_3
    //   220: goto -> 203
    //   223: aload #6
    //   225: invokevirtual toString : ()Ljava/lang/String;
    //   228: astore #6
    //   230: aload_0
    //   231: monitorexit
    //   232: aload #6
    //   234: areturn
    //   235: astore #6
    //   237: aload_0
    //   238: monitorexit
    //   239: aload #6
    //   241: athrow
    //   242: iload_3
    //   243: iload_1
    //   244: iadd
    //   245: istore_3
    //   246: iload_2
    //   247: iconst_1
    //   248: iadd
    //   249: istore_2
    //   250: goto -> 32
    //   253: iconst_0
    //   254: istore_1
    //   255: goto -> 97
    //   258: bipush #100
    //   260: istore #4
    //   262: goto -> 107
    //   265: iload_3
    //   266: iconst_1
    //   267: iadd
    //   268: istore_3
    //   269: goto -> 123
    // Exception table:
    //   from	to	target	type
    //   2	14	235	finally
    //   16	25	235	finally
    //   32	59	235	finally
    //   63	78	235	finally
    //   81	92	235	finally
    //   107	120	235	finally
    //   128	140	235	finally
    //   151	158	235	finally
    //   161	184	235	finally
    //   193	201	235	finally
    //   208	216	235	finally
    //   223	232	235	finally
    //   237	239	235	finally
  }
  
  public void reset() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield queue : Ljava/util/LinkedList;
    //   6: invokevirtual clear : ()V
    //   9: aload_0
    //   10: iconst_m1
    //   11: invokevirtual setBridgeMode : (I)V
    //   14: aload_0
    //   15: monitorexit
    //   16: return
    //   17: astore_1
    //   18: aload_0
    //   19: monitorexit
    //   20: aload_1
    //   21: athrow
    // Exception table:
    //   from	to	target	type
    //   2	16	17	finally
    //   18	20	17	finally
  }
  
  public void setBridgeMode(int paramInt) {
    // Byte code:
    //   0: iload_1
    //   1: iconst_m1
    //   2: if_icmplt -> 145
    //   5: iload_1
    //   6: aload_0
    //   7: getfield bridgeModes : Ljava/util/ArrayList;
    //   10: invokevirtual size : ()I
    //   13: if_icmplt -> 19
    //   16: goto -> 145
    //   19: iload_1
    //   20: ifge -> 28
    //   23: aconst_null
    //   24: astore_2
    //   25: goto -> 40
    //   28: aload_0
    //   29: getfield bridgeModes : Ljava/util/ArrayList;
    //   32: iload_1
    //   33: invokevirtual get : (I)Ljava/lang/Object;
    //   36: checkcast org/apache/cordova/NativeToJsMessageQueue$BridgeMode
    //   39: astore_2
    //   40: aload_2
    //   41: aload_0
    //   42: getfield activeBridgeMode : Lorg/apache/cordova/NativeToJsMessageQueue$BridgeMode;
    //   45: if_acmpeq -> 175
    //   48: new java/lang/StringBuilder
    //   51: dup
    //   52: invokespecial <init> : ()V
    //   55: astore #4
    //   57: aload #4
    //   59: ldc 'Set native->JS mode to '
    //   61: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   64: pop
    //   65: aload_2
    //   66: ifnonnull -> 75
    //   69: ldc 'null'
    //   71: astore_3
    //   72: goto -> 83
    //   75: aload_2
    //   76: invokevirtual getClass : ()Ljava/lang/Class;
    //   79: invokevirtual getSimpleName : ()Ljava/lang/String;
    //   82: astore_3
    //   83: aload #4
    //   85: aload_3
    //   86: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   89: pop
    //   90: ldc 'JsMessageQueue'
    //   92: aload #4
    //   94: invokevirtual toString : ()Ljava/lang/String;
    //   97: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)V
    //   100: aload_0
    //   101: monitorenter
    //   102: aload_0
    //   103: aload_2
    //   104: putfield activeBridgeMode : Lorg/apache/cordova/NativeToJsMessageQueue$BridgeMode;
    //   107: aload_2
    //   108: ifnull -> 137
    //   111: aload_2
    //   112: invokevirtual reset : ()V
    //   115: aload_0
    //   116: getfield paused : Z
    //   119: ifne -> 137
    //   122: aload_0
    //   123: getfield queue : Ljava/util/LinkedList;
    //   126: invokevirtual isEmpty : ()Z
    //   129: ifne -> 137
    //   132: aload_2
    //   133: aload_0
    //   134: invokevirtual onNativeToJsMessageAvailable : (Lorg/apache/cordova/NativeToJsMessageQueue;)V
    //   137: aload_0
    //   138: monitorexit
    //   139: return
    //   140: astore_2
    //   141: aload_0
    //   142: monitorexit
    //   143: aload_2
    //   144: athrow
    //   145: new java/lang/StringBuilder
    //   148: dup
    //   149: invokespecial <init> : ()V
    //   152: astore_2
    //   153: aload_2
    //   154: ldc 'Invalid NativeToJsBridgeMode: '
    //   156: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   159: pop
    //   160: aload_2
    //   161: iload_1
    //   162: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   165: pop
    //   166: ldc 'JsMessageQueue'
    //   168: aload_2
    //   169: invokevirtual toString : ()Ljava/lang/String;
    //   172: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)V
    //   175: return
    // Exception table:
    //   from	to	target	type
    //   102	107	140	finally
    //   111	137	140	finally
    //   137	139	140	finally
    //   141	143	140	finally
  }
  
  public void setPaused(boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: getfield paused : Z
    //   4: ifeq -> 25
    //   7: iload_1
    //   8: ifeq -> 25
    //   11: ldc 'JsMessageQueue'
    //   13: ldc 'nested call to setPaused detected.'
    //   15: new java/lang/Throwable
    //   18: dup
    //   19: invokespecial <init> : ()V
    //   22: invokestatic e : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   25: aload_0
    //   26: iload_1
    //   27: putfield paused : Z
    //   30: iload_1
    //   31: ifne -> 69
    //   34: aload_0
    //   35: monitorenter
    //   36: aload_0
    //   37: getfield queue : Ljava/util/LinkedList;
    //   40: invokevirtual isEmpty : ()Z
    //   43: ifne -> 61
    //   46: aload_0
    //   47: getfield activeBridgeMode : Lorg/apache/cordova/NativeToJsMessageQueue$BridgeMode;
    //   50: ifnull -> 61
    //   53: aload_0
    //   54: getfield activeBridgeMode : Lorg/apache/cordova/NativeToJsMessageQueue$BridgeMode;
    //   57: aload_0
    //   58: invokevirtual onNativeToJsMessageAvailable : (Lorg/apache/cordova/NativeToJsMessageQueue;)V
    //   61: aload_0
    //   62: monitorexit
    //   63: return
    //   64: astore_2
    //   65: aload_0
    //   66: monitorexit
    //   67: aload_2
    //   68: athrow
    //   69: return
    // Exception table:
    //   from	to	target	type
    //   36	61	64	finally
    //   61	63	64	finally
    //   65	67	64	finally
  }
  
  public static abstract class BridgeMode {
    public void notifyOfFlush(NativeToJsMessageQueue param1NativeToJsMessageQueue, boolean param1Boolean) {}
    
    public abstract void onNativeToJsMessageAvailable(NativeToJsMessageQueue param1NativeToJsMessageQueue);
    
    public void reset() {}
  }
  
  public static class EvalBridgeMode extends BridgeMode {
    private final CordovaInterface cordova;
    
    private final CordovaWebViewEngine engine;
    
    public EvalBridgeMode(CordovaWebViewEngine param1CordovaWebViewEngine, CordovaInterface param1CordovaInterface) {
      this.engine = param1CordovaWebViewEngine;
      this.cordova = param1CordovaInterface;
    }
    
    public void onNativeToJsMessageAvailable(final NativeToJsMessageQueue queue) {
      this.cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {
              String str = queue.popAndEncodeAsJs();
              if (str != null)
                NativeToJsMessageQueue.EvalBridgeMode.this.engine.evaluateJavascript(str, null); 
            }
          });
    }
  }
  
  class null implements Runnable {
    public void run() {
      String str = queue.popAndEncodeAsJs();
      if (str != null)
        this.this$0.engine.evaluateJavascript(str, null); 
    }
  }
  
  private static class JsMessage {
    final String jsPayloadOrCallbackId;
    
    final PluginResult pluginResult;
    
    JsMessage(String param1String) {
      if (param1String == null)
        throw new NullPointerException(); 
      this.jsPayloadOrCallbackId = param1String;
      this.pluginResult = null;
    }
    
    JsMessage(PluginResult param1PluginResult, String param1String) {
      if (param1String == null || param1PluginResult == null)
        throw new NullPointerException(); 
      this.jsPayloadOrCallbackId = param1String;
      this.pluginResult = param1PluginResult;
    }
    
    static int calculateEncodedLengthHelper(PluginResult param1PluginResult) {
      int i = param1PluginResult.getMessageType();
      if (i != 1) {
        int j;
        switch (i) {
          default:
            return param1PluginResult.getMessage().length();
          case 8:
            i = 0;
            j = 1;
            while (i < param1PluginResult.getMultipartMessagesSize()) {
              int k = calculateEncodedLengthHelper(param1PluginResult.getMultipartMessage(i));
              j += String.valueOf(k).length() + 1 + k;
              i++;
            } 
            return j;
          case 7:
            return param1PluginResult.getMessage().length() + 1;
          case 6:
            return param1PluginResult.getMessage().length() + 1;
          case 4:
          case 5:
            return 1;
          case 3:
            break;
        } 
        return param1PluginResult.getMessage().length() + 1;
      } 
      return param1PluginResult.getStrMessage().length() + 1;
    }
    
    static void encodeAsMessageHelper(StringBuilder param1StringBuilder, PluginResult param1PluginResult) {
      int i = param1PluginResult.getMessageType();
      if (i != 1) {
        int j = 0;
        switch (i) {
          default:
            param1StringBuilder.append(param1PluginResult.getMessage());
            return;
          case 8:
            param1StringBuilder.append('M');
            while (j < param1PluginResult.getMultipartMessagesSize()) {
              PluginResult pluginResult = param1PluginResult.getMultipartMessage(j);
              param1StringBuilder.append(String.valueOf(calculateEncodedLengthHelper(pluginResult)));
              param1StringBuilder.append(' ');
              encodeAsMessageHelper(param1StringBuilder, pluginResult);
              j++;
            } 
            return;
          case 7:
            param1StringBuilder.append('S');
            param1StringBuilder.append(param1PluginResult.getMessage());
            return;
          case 6:
            param1StringBuilder.append('A');
            param1StringBuilder.append(param1PluginResult.getMessage());
            return;
          case 5:
            param1StringBuilder.append('N');
            return;
          case 4:
            param1StringBuilder.append(param1PluginResult.getMessage().charAt(0));
            return;
          case 3:
            break;
        } 
        param1StringBuilder.append('n');
        param1StringBuilder.append(param1PluginResult.getMessage());
        return;
      } 
      param1StringBuilder.append('s');
      param1StringBuilder.append(param1PluginResult.getStrMessage());
    }
    
    void buildJsMessage(StringBuilder param1StringBuilder) {
      int i;
      int j;
      switch (this.pluginResult.getMessageType()) {
        default:
          param1StringBuilder.append(this.pluginResult.getMessage());
          return;
        case 8:
          j = this.pluginResult.getMultipartMessagesSize();
          for (i = 0; i < j; i++) {
            (new JsMessage(this.pluginResult.getMultipartMessage(i), this.jsPayloadOrCallbackId)).buildJsMessage(param1StringBuilder);
            if (i < j - 1)
              param1StringBuilder.append(","); 
          } 
          return;
        case 7:
          param1StringBuilder.append("atob('");
          param1StringBuilder.append(this.pluginResult.getMessage());
          param1StringBuilder.append("')");
          return;
        case 6:
          break;
      } 
      param1StringBuilder.append("cordova.require('cordova/base64').toArrayBuffer('");
      param1StringBuilder.append(this.pluginResult.getMessage());
      param1StringBuilder.append("')");
    }
    
    int calculateEncodedLength() {
      return (this.pluginResult == null) ? (this.jsPayloadOrCallbackId.length() + 1) : (String.valueOf(this.pluginResult.getStatus()).length() + 2 + 1 + this.jsPayloadOrCallbackId.length() + 1 + calculateEncodedLengthHelper(this.pluginResult));
    }
    
    void encodeAsJsMessage(StringBuilder param1StringBuilder) {
      boolean bool;
      if (this.pluginResult == null) {
        param1StringBuilder.append(this.jsPayloadOrCallbackId);
        return;
      } 
      int i = this.pluginResult.getStatus();
      if (i == PluginResult.Status.OK.ordinal() || i == PluginResult.Status.NO_RESULT.ordinal()) {
        bool = true;
      } else {
        bool = false;
      } 
      param1StringBuilder.append("cordova.callbackFromNative('");
      param1StringBuilder.append(this.jsPayloadOrCallbackId);
      param1StringBuilder.append("',");
      param1StringBuilder.append(bool);
      param1StringBuilder.append(",");
      param1StringBuilder.append(i);
      param1StringBuilder.append(",[");
      buildJsMessage(param1StringBuilder);
      param1StringBuilder.append("],");
      param1StringBuilder.append(this.pluginResult.getKeepCallback());
      param1StringBuilder.append(");");
    }
    
    void encodeAsMessage(StringBuilder param1StringBuilder) {
      byte b;
      if (this.pluginResult == null) {
        param1StringBuilder.append('J');
        param1StringBuilder.append(this.jsPayloadOrCallbackId);
        return;
      } 
      int j = this.pluginResult.getStatus();
      int i = PluginResult.Status.NO_RESULT.ordinal();
      boolean bool = false;
      if (j == i) {
        i = 1;
      } else {
        i = 0;
      } 
      if (j == PluginResult.Status.OK.ordinal())
        bool = true; 
      boolean bool1 = this.pluginResult.getKeepCallback();
      if (i != 0 || bool) {
        b = 83;
      } else {
        b = 70;
      } 
      param1StringBuilder.append(b);
      if (bool1) {
        b = 49;
      } else {
        b = 48;
      } 
      param1StringBuilder.append(b);
      param1StringBuilder.append(j);
      param1StringBuilder.append(' ');
      param1StringBuilder.append(this.jsPayloadOrCallbackId);
      param1StringBuilder.append(' ');
      encodeAsMessageHelper(param1StringBuilder, this.pluginResult);
    }
  }
  
  public static class LoadUrlBridgeMode extends BridgeMode {
    private final CordovaInterface cordova;
    
    private final CordovaWebViewEngine engine;
    
    public LoadUrlBridgeMode(CordovaWebViewEngine param1CordovaWebViewEngine, CordovaInterface param1CordovaInterface) {
      this.engine = param1CordovaWebViewEngine;
      this.cordova = param1CordovaInterface;
    }
    
    public void onNativeToJsMessageAvailable(final NativeToJsMessageQueue queue) {
      this.cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {
              String str = queue.popAndEncodeAsJs();
              if (str != null) {
                CordovaWebViewEngine cordovaWebViewEngine = NativeToJsMessageQueue.LoadUrlBridgeMode.this.engine;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("javascript:");
                stringBuilder.append(str);
                cordovaWebViewEngine.loadUrl(stringBuilder.toString(), false);
              } 
            }
          });
    }
  }
  
  class null implements Runnable {
    public void run() {
      String str = queue.popAndEncodeAsJs();
      if (str != null) {
        CordovaWebViewEngine cordovaWebViewEngine = this.this$0.engine;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("javascript:");
        stringBuilder.append(str);
        cordovaWebViewEngine.loadUrl(stringBuilder.toString(), false);
      } 
    }
  }
  
  public static class NoOpBridgeMode extends BridgeMode {
    public void onNativeToJsMessageAvailable(NativeToJsMessageQueue param1NativeToJsMessageQueue) {}
  }
  
  public static class OnlineEventsBridgeMode extends BridgeMode {
    private final OnlineEventsBridgeModeDelegate delegate;
    
    private boolean ignoreNextFlush;
    
    private boolean online;
    
    public OnlineEventsBridgeMode(OnlineEventsBridgeModeDelegate param1OnlineEventsBridgeModeDelegate) {
      this.delegate = param1OnlineEventsBridgeModeDelegate;
    }
    
    public void notifyOfFlush(NativeToJsMessageQueue param1NativeToJsMessageQueue, boolean param1Boolean) {
      if (param1Boolean && !this.ignoreNextFlush)
        this.online ^= 0x1; 
    }
    
    public void onNativeToJsMessageAvailable(final NativeToJsMessageQueue queue) {
      this.delegate.runOnUiThread(new Runnable() {
            public void run() {
              if (!queue.isEmpty()) {
                NativeToJsMessageQueue.OnlineEventsBridgeMode.access$202(NativeToJsMessageQueue.OnlineEventsBridgeMode.this, false);
                NativeToJsMessageQueue.OnlineEventsBridgeMode.this.delegate.setNetworkAvailable(NativeToJsMessageQueue.OnlineEventsBridgeMode.this.online);
              } 
            }
          });
    }
    
    public void reset() {
      this.delegate.runOnUiThread(new Runnable() {
            public void run() {
              NativeToJsMessageQueue.OnlineEventsBridgeMode.access$102(NativeToJsMessageQueue.OnlineEventsBridgeMode.this, false);
              NativeToJsMessageQueue.OnlineEventsBridgeMode.access$202(NativeToJsMessageQueue.OnlineEventsBridgeMode.this, true);
              NativeToJsMessageQueue.OnlineEventsBridgeMode.this.delegate.setNetworkAvailable(true);
            }
          });
    }
    
    public static interface OnlineEventsBridgeModeDelegate {
      void runOnUiThread(Runnable param2Runnable);
      
      void setNetworkAvailable(boolean param2Boolean);
    }
  }
  
  class null implements Runnable {
    public void run() {
      NativeToJsMessageQueue.OnlineEventsBridgeMode.access$102(this.this$0, false);
      NativeToJsMessageQueue.OnlineEventsBridgeMode.access$202(this.this$0, true);
      this.this$0.delegate.setNetworkAvailable(true);
    }
  }
  
  class null implements Runnable {
    public void run() {
      if (!queue.isEmpty()) {
        NativeToJsMessageQueue.OnlineEventsBridgeMode.access$202(this.this$0, false);
        this.this$0.delegate.setNetworkAvailable(this.this$0.online);
      } 
    }
  }
  
  public static interface OnlineEventsBridgeModeDelegate {
    void runOnUiThread(Runnable param1Runnable);
    
    void setNetworkAvailable(boolean param1Boolean);
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\org\apache\cordova\NativeToJsMessageQueue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */