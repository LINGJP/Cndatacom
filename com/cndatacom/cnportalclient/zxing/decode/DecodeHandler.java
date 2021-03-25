package com.cndatacom.cnportalclient.zxing.decode;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.a.a.e;
import com.a.a.h;
import com.a.a.j;
import com.cndatacom.cnportalclient.CaptureActivity;
import java.io.ByteArrayOutputStream;
import java.util.Map;

public final class DecodeHandler extends Handler {
  private static final String a = "DecodeHandler";
  
  private final CaptureActivity b;
  
  private final h c = new h();
  
  private boolean d = true;
  
  DecodeHandler(CaptureActivity paramCaptureActivity, Map<e, Object> paramMap) {
    this.c.a(paramMap);
    this.b = paramCaptureActivity;
  }
  
  private static void a(j paramj, Bundle paramBundle) {
    int[] arrayOfInt = paramj.f();
    int i = paramj.g();
    Bitmap bitmap = Bitmap.createBitmap(arrayOfInt, 0, i, i, paramj.h(), Bitmap.Config.ARGB_8888);
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
    paramBundle.putByteArray("barcode_bitmap", byteArrayOutputStream.toByteArray());
    paramBundle.putFloat("barcode_scaled_factor", i / paramj.b());
  }
  
  private void a(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    // Byte code:
    //   0: invokestatic currentTimeMillis : ()J
    //   3: lstore #6
    //   5: aload_1
    //   6: arraylength
    //   7: newarray byte
    //   9: astore #10
    //   11: iconst_0
    //   12: istore #4
    //   14: iload #4
    //   16: iload_3
    //   17: if_icmpge -> 70
    //   20: iconst_0
    //   21: istore #5
    //   23: iload #5
    //   25: iload_2
    //   26: if_icmpge -> 61
    //   29: aload #10
    //   31: iload #5
    //   33: iload_3
    //   34: imul
    //   35: iload_3
    //   36: iadd
    //   37: iload #4
    //   39: isub
    //   40: iconst_1
    //   41: isub
    //   42: aload_1
    //   43: iload #4
    //   45: iload_2
    //   46: imul
    //   47: iload #5
    //   49: iadd
    //   50: baload
    //   51: bastore
    //   52: iload #5
    //   54: iconst_1
    //   55: iadd
    //   56: istore #5
    //   58: goto -> 23
    //   61: iload #4
    //   63: iconst_1
    //   64: iadd
    //   65: istore #4
    //   67: goto -> 14
    //   70: aload_0
    //   71: getfield b : Lcom/cndatacom/cnportalclient/CaptureActivity;
    //   74: invokevirtual getCameraManager : ()Lcom/zxinglibrary/a/d;
    //   77: aload #10
    //   79: iload_3
    //   80: iload_2
    //   81: invokevirtual a : ([BII)Lcom/a/a/j;
    //   84: astore #10
    //   86: aload #10
    //   88: ifnull -> 144
    //   91: new com/a/a/c
    //   94: dup
    //   95: new com/a/a/b/j
    //   98: dup
    //   99: aload #10
    //   101: invokespecial <init> : (Lcom/a/a/g;)V
    //   104: invokespecial <init> : (Lcom/a/a/b;)V
    //   107: astore_1
    //   108: aload_0
    //   109: getfield c : Lcom/a/a/h;
    //   112: aload_1
    //   113: invokevirtual a : (Lcom/a/a/c;)Lcom/a/a/m;
    //   116: astore_1
    //   117: aload_0
    //   118: getfield c : Lcom/a/a/h;
    //   121: invokevirtual a : ()V
    //   124: goto -> 146
    //   127: astore_1
    //   128: aload_0
    //   129: getfield c : Lcom/a/a/h;
    //   132: invokevirtual a : ()V
    //   135: aload_1
    //   136: athrow
    //   137: aload_0
    //   138: getfield c : Lcom/a/a/h;
    //   141: invokevirtual a : ()V
    //   144: aconst_null
    //   145: astore_1
    //   146: aload_0
    //   147: getfield b : Lcom/cndatacom/cnportalclient/CaptureActivity;
    //   150: invokevirtual getHandler : ()Landroid/os/Handler;
    //   153: astore #11
    //   155: aload_1
    //   156: ifnull -> 257
    //   159: invokestatic currentTimeMillis : ()J
    //   162: lstore #8
    //   164: getstatic com/cndatacom/cnportalclient/zxing/decode/DecodeHandler.a : Ljava/lang/String;
    //   167: astore #12
    //   169: new java/lang/StringBuilder
    //   172: dup
    //   173: invokespecial <init> : ()V
    //   176: astore #13
    //   178: aload #13
    //   180: ldc 'Found barcode in '
    //   182: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   185: pop
    //   186: aload #13
    //   188: lload #8
    //   190: lload #6
    //   192: lsub
    //   193: invokevirtual append : (J)Ljava/lang/StringBuilder;
    //   196: pop
    //   197: aload #13
    //   199: ldc ' ms'
    //   201: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   204: pop
    //   205: aload #12
    //   207: aload #13
    //   209: invokevirtual toString : ()Ljava/lang/String;
    //   212: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   215: pop
    //   216: aload #11
    //   218: ifnull -> 272
    //   221: aload #11
    //   223: ldc 2131165246
    //   225: aload_1
    //   226: invokestatic obtain : (Landroid/os/Handler;ILjava/lang/Object;)Landroid/os/Message;
    //   229: astore_1
    //   230: new android/os/Bundle
    //   233: dup
    //   234: invokespecial <init> : ()V
    //   237: astore #11
    //   239: aload #10
    //   241: aload #11
    //   243: invokestatic a : (Lcom/a/a/j;Landroid/os/Bundle;)V
    //   246: aload_1
    //   247: aload #11
    //   249: invokevirtual setData : (Landroid/os/Bundle;)V
    //   252: aload_1
    //   253: invokevirtual sendToTarget : ()V
    //   256: return
    //   257: aload #11
    //   259: ifnull -> 272
    //   262: aload #11
    //   264: ldc 2131165245
    //   266: invokestatic obtain : (Landroid/os/Handler;I)Landroid/os/Message;
    //   269: invokevirtual sendToTarget : ()V
    //   272: return
    //   273: astore_1
    //   274: goto -> 137
    // Exception table:
    //   from	to	target	type
    //   108	117	273	com/a/a/l
    //   108	117	127	finally
  }
  
  public void handleMessage(Message paramMessage) {
    if (!this.d)
      return; 
    if (paramMessage.what == 2131165244) {
      a((byte[])paramMessage.obj, paramMessage.arg1, paramMessage.arg2);
      return;
    } 
    if (paramMessage.what == 2131165305) {
      this.d = false;
      Looper.myLooper().quit();
    } 
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\zxing\decode\DecodeHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */