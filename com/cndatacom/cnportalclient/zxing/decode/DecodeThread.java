package com.cndatacom.cnportalclient.zxing.decode;

import android.os.Handler;
import android.os.Looper;
import com.a.a.a;
import com.a.a.e;
import com.a.a.p;
import com.cndatacom.cnportalclient.CaptureActivity;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public final class DecodeThread extends Thread {
  public static final String BARCODE_BITMAP = "barcode_bitmap";
  
  public static final String BARCODE_SCALED_FACTOR = "barcode_scaled_factor";
  
  private final CaptureActivity a;
  
  private final Map<e, Object> b;
  
  private Handler c;
  
  private final CountDownLatch d;
  
  public DecodeThread(CaptureActivity paramCaptureActivity, Collection<a> paramCollection, Map<e, ?> paramMap, String paramString, p paramp) {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial <init> : ()V
    //   4: aload_0
    //   5: aload_1
    //   6: putfield a : Lcom/cndatacom/cnportalclient/CaptureActivity;
    //   9: aload_0
    //   10: new java/util/concurrent/CountDownLatch
    //   13: dup
    //   14: iconst_1
    //   15: invokespecial <init> : (I)V
    //   18: putfield d : Ljava/util/concurrent/CountDownLatch;
    //   21: aload_0
    //   22: new java/util/EnumMap
    //   25: dup
    //   26: ldc com/a/a/e
    //   28: invokespecial <init> : (Ljava/lang/Class;)V
    //   31: putfield b : Ljava/util/Map;
    //   34: aload_3
    //   35: ifnull -> 48
    //   38: aload_0
    //   39: getfield b : Ljava/util/Map;
    //   42: aload_3
    //   43: invokeinterface putAll : (Ljava/util/Map;)V
    //   48: aload_2
    //   49: ifnull -> 63
    //   52: aload_2
    //   53: astore_3
    //   54: aload_2
    //   55: invokeinterface isEmpty : ()Z
    //   60: ifeq -> 210
    //   63: aload_1
    //   64: invokestatic getDefaultSharedPreferences : (Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   67: astore_2
    //   68: ldc com/a/a/a
    //   70: invokestatic noneOf : (Ljava/lang/Class;)Ljava/util/EnumSet;
    //   73: astore_1
    //   74: aload_2
    //   75: ldc 'preferences_decode_1D_product'
    //   77: iconst_1
    //   78: invokeinterface getBoolean : (Ljava/lang/String;Z)Z
    //   83: ifeq -> 96
    //   86: aload_1
    //   87: getstatic com/cndatacom/cnportalclient/zxing/decode/DecodeFormatManager.PRODUCT_FORMATS : Ljava/util/Set;
    //   90: invokeinterface addAll : (Ljava/util/Collection;)Z
    //   95: pop
    //   96: aload_2
    //   97: ldc 'preferences_decode_1D_industrial'
    //   99: iconst_1
    //   100: invokeinterface getBoolean : (Ljava/lang/String;Z)Z
    //   105: ifeq -> 118
    //   108: aload_1
    //   109: getstatic com/cndatacom/cnportalclient/zxing/decode/DecodeFormatManager.a : Ljava/util/Set;
    //   112: invokeinterface addAll : (Ljava/util/Collection;)Z
    //   117: pop
    //   118: aload_2
    //   119: ldc 'preferences_decode_QR'
    //   121: iconst_1
    //   122: invokeinterface getBoolean : (Ljava/lang/String;Z)Z
    //   127: ifeq -> 140
    //   130: aload_1
    //   131: getstatic com/cndatacom/cnportalclient/zxing/decode/DecodeFormatManager.b : Ljava/util/Set;
    //   134: invokeinterface addAll : (Ljava/util/Collection;)Z
    //   139: pop
    //   140: aload_2
    //   141: ldc 'preferences_decode_Data_Matrix'
    //   143: iconst_1
    //   144: invokeinterface getBoolean : (Ljava/lang/String;Z)Z
    //   149: ifeq -> 162
    //   152: aload_1
    //   153: getstatic com/cndatacom/cnportalclient/zxing/decode/DecodeFormatManager.c : Ljava/util/Set;
    //   156: invokeinterface addAll : (Ljava/util/Collection;)Z
    //   161: pop
    //   162: aload_2
    //   163: ldc 'preferences_decode_Aztec'
    //   165: iconst_0
    //   166: invokeinterface getBoolean : (Ljava/lang/String;Z)Z
    //   171: ifeq -> 184
    //   174: aload_1
    //   175: getstatic com/cndatacom/cnportalclient/zxing/decode/DecodeFormatManager.d : Ljava/util/Set;
    //   178: invokeinterface addAll : (Ljava/util/Collection;)Z
    //   183: pop
    //   184: aload_1
    //   185: astore_3
    //   186: aload_2
    //   187: ldc 'preferences_decode_PDF417'
    //   189: iconst_0
    //   190: invokeinterface getBoolean : (Ljava/lang/String;Z)Z
    //   195: ifeq -> 210
    //   198: aload_1
    //   199: getstatic com/cndatacom/cnportalclient/zxing/decode/DecodeFormatManager.e : Ljava/util/Set;
    //   202: invokeinterface addAll : (Ljava/util/Collection;)Z
    //   207: pop
    //   208: aload_1
    //   209: astore_3
    //   210: aload_0
    //   211: getfield b : Ljava/util/Map;
    //   214: getstatic com/a/a/e.c : Lcom/a/a/e;
    //   217: aload_3
    //   218: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   223: pop
    //   224: aload #4
    //   226: ifnull -> 244
    //   229: aload_0
    //   230: getfield b : Ljava/util/Map;
    //   233: getstatic com/a/a/e.e : Lcom/a/a/e;
    //   236: aload #4
    //   238: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   243: pop
    //   244: aload_0
    //   245: getfield b : Ljava/util/Map;
    //   248: getstatic com/a/a/e.i : Lcom/a/a/e;
    //   251: aload #5
    //   253: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   258: pop
    //   259: new java/lang/StringBuilder
    //   262: dup
    //   263: invokespecial <init> : ()V
    //   266: astore_1
    //   267: aload_1
    //   268: ldc 'Hints: '
    //   270: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   273: pop
    //   274: aload_1
    //   275: aload_0
    //   276: getfield b : Ljava/util/Map;
    //   279: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   282: pop
    //   283: ldc 'DecodeThread'
    //   285: aload_1
    //   286: invokevirtual toString : ()Ljava/lang/String;
    //   289: invokestatic i : (Ljava/lang/String;Ljava/lang/String;)I
    //   292: pop
    //   293: return
  }
  
  public Handler getHandler() {
    try {
      this.d.await();
    } catch (InterruptedException interruptedException) {}
    return this.c;
  }
  
  public void run() {
    Looper.prepare();
    this.c = new DecodeHandler(this.a, this.b);
    this.d.countDown();
    Looper.loop();
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\zxing\decode\DecodeThread.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */