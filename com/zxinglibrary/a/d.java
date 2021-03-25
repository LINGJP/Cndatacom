package com.zxinglibrary.a;

import android.content.Context;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Handler;
import android.view.SurfaceHolder;
import com.a.a.j;

public final class d {
  private static final String a = "d";
  
  private final Context b;
  
  private final b c;
  
  private Camera d;
  
  private a e;
  
  private Rect f;
  
  private Rect g;
  
  private boolean h;
  
  private boolean i;
  
  private int j = -1;
  
  private int k;
  
  private int l;
  
  private final f m;
  
  public d(Context paramContext) {
    this.b = paramContext;
    this.c = new b(paramContext);
    this.m = new f(this.c);
  }
  
  public j a(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    Rect rect = f();
    return (rect == null) ? null : new j(paramArrayOfbyte, paramInt1, paramInt2, rect.left, rect.top, rect.width(), rect.height(), false);
  }
  
  public void a(int paramInt1, int paramInt2) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield h : Z
    //   6: ifeq -> 145
    //   9: aload_0
    //   10: getfield c : Lcom/zxinglibrary/a/b;
    //   13: invokevirtual b : ()Landroid/graphics/Point;
    //   16: astore #5
    //   18: iload_1
    //   19: istore_3
    //   20: iload_1
    //   21: aload #5
    //   23: getfield x : I
    //   26: if_icmple -> 35
    //   29: aload #5
    //   31: getfield x : I
    //   34: istore_3
    //   35: iload_2
    //   36: istore_1
    //   37: iload_2
    //   38: aload #5
    //   40: getfield y : I
    //   43: if_icmple -> 52
    //   46: aload #5
    //   48: getfield y : I
    //   51: istore_1
    //   52: aload #5
    //   54: getfield x : I
    //   57: iload_3
    //   58: isub
    //   59: iconst_2
    //   60: idiv
    //   61: istore_2
    //   62: aload #5
    //   64: getfield y : I
    //   67: iload_1
    //   68: isub
    //   69: iconst_2
    //   70: idiv
    //   71: istore #4
    //   73: aload_0
    //   74: new android/graphics/Rect
    //   77: dup
    //   78: iload_2
    //   79: iload #4
    //   81: iload_3
    //   82: iload_2
    //   83: iadd
    //   84: iload_1
    //   85: iload #4
    //   87: iadd
    //   88: invokespecial <init> : (IIII)V
    //   91: putfield f : Landroid/graphics/Rect;
    //   94: getstatic com/zxinglibrary/a/d.a : Ljava/lang/String;
    //   97: astore #5
    //   99: new java/lang/StringBuilder
    //   102: dup
    //   103: invokespecial <init> : ()V
    //   106: astore #6
    //   108: aload #6
    //   110: ldc 'Calculated manual framing rect: '
    //   112: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   115: pop
    //   116: aload #6
    //   118: aload_0
    //   119: getfield f : Landroid/graphics/Rect;
    //   122: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   125: pop
    //   126: aload #5
    //   128: aload #6
    //   130: invokevirtual toString : ()Ljava/lang/String;
    //   133: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   136: pop
    //   137: aload_0
    //   138: aconst_null
    //   139: putfield g : Landroid/graphics/Rect;
    //   142: goto -> 155
    //   145: aload_0
    //   146: iload_1
    //   147: putfield k : I
    //   150: aload_0
    //   151: iload_2
    //   152: putfield l : I
    //   155: aload_0
    //   156: monitorexit
    //   157: return
    //   158: astore #5
    //   160: aload_0
    //   161: monitorexit
    //   162: aload #5
    //   164: athrow
    // Exception table:
    //   from	to	target	type
    //   2	18	158	finally
    //   20	35	158	finally
    //   37	52	158	finally
    //   52	142	158	finally
    //   145	155	158	finally
  }
  
  public void a(Handler paramHandler, int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield d : Landroid/hardware/Camera;
    //   6: astore_3
    //   7: aload_3
    //   8: ifnull -> 35
    //   11: aload_0
    //   12: getfield i : Z
    //   15: ifeq -> 35
    //   18: aload_0
    //   19: getfield m : Lcom/zxinglibrary/a/f;
    //   22: aload_1
    //   23: iload_2
    //   24: invokevirtual a : (Landroid/os/Handler;I)V
    //   27: aload_3
    //   28: aload_0
    //   29: getfield m : Lcom/zxinglibrary/a/f;
    //   32: invokevirtual setOneShotPreviewCallback : (Landroid/hardware/Camera$PreviewCallback;)V
    //   35: aload_0
    //   36: monitorexit
    //   37: return
    //   38: astore_1
    //   39: aload_0
    //   40: monitorexit
    //   41: aload_1
    //   42: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	38	finally
    //   11	35	38	finally
  }
  
  public void a(SurfaceHolder paramSurfaceHolder) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield d : Landroid/hardware/Camera;
    //   6: astore_3
    //   7: aload_3
    //   8: astore_2
    //   9: aload_3
    //   10: ifnonnull -> 52
    //   13: aload_0
    //   14: getfield j : I
    //   17: iflt -> 31
    //   20: aload_0
    //   21: getfield j : I
    //   24: invokestatic a : (I)Landroid/hardware/Camera;
    //   27: astore_2
    //   28: goto -> 35
    //   31: invokestatic a : ()Landroid/hardware/Camera;
    //   34: astore_2
    //   35: aload_2
    //   36: ifnonnull -> 47
    //   39: new java/io/IOException
    //   42: dup
    //   43: invokespecial <init> : ()V
    //   46: athrow
    //   47: aload_0
    //   48: aload_2
    //   49: putfield d : Landroid/hardware/Camera;
    //   52: aload_2
    //   53: aload_1
    //   54: invokevirtual setPreviewDisplay : (Landroid/view/SurfaceHolder;)V
    //   57: aload_0
    //   58: getfield h : Z
    //   61: ifne -> 113
    //   64: aload_0
    //   65: iconst_1
    //   66: putfield h : Z
    //   69: aload_0
    //   70: getfield c : Lcom/zxinglibrary/a/b;
    //   73: aload_2
    //   74: invokevirtual a : (Landroid/hardware/Camera;)V
    //   77: aload_0
    //   78: getfield k : I
    //   81: ifle -> 113
    //   84: aload_0
    //   85: getfield l : I
    //   88: ifle -> 113
    //   91: aload_0
    //   92: aload_0
    //   93: getfield k : I
    //   96: aload_0
    //   97: getfield l : I
    //   100: invokevirtual a : (II)V
    //   103: aload_0
    //   104: iconst_0
    //   105: putfield k : I
    //   108: aload_0
    //   109: iconst_0
    //   110: putfield l : I
    //   113: aload_2
    //   114: invokevirtual getParameters : ()Landroid/hardware/Camera$Parameters;
    //   117: astore_1
    //   118: aload_1
    //   119: ifnonnull -> 127
    //   122: aconst_null
    //   123: astore_1
    //   124: goto -> 132
    //   127: aload_1
    //   128: invokevirtual flatten : ()Ljava/lang/String;
    //   131: astore_1
    //   132: aload_0
    //   133: getfield c : Lcom/zxinglibrary/a/b;
    //   136: aload_2
    //   137: iconst_0
    //   138: invokevirtual a : (Landroid/hardware/Camera;Z)V
    //   141: goto -> 231
    //   144: getstatic com/zxinglibrary/a/d.a : Ljava/lang/String;
    //   147: ldc 'Camera rejected parameters. Setting only minimal safe-mode parameters'
    //   149: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   152: pop
    //   153: getstatic com/zxinglibrary/a/d.a : Ljava/lang/String;
    //   156: astore_3
    //   157: new java/lang/StringBuilder
    //   160: dup
    //   161: invokespecial <init> : ()V
    //   164: astore #4
    //   166: aload #4
    //   168: ldc 'Resetting to saved camera params: '
    //   170: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   173: pop
    //   174: aload #4
    //   176: aload_1
    //   177: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   180: pop
    //   181: aload_3
    //   182: aload #4
    //   184: invokevirtual toString : ()Ljava/lang/String;
    //   187: invokestatic i : (Ljava/lang/String;Ljava/lang/String;)I
    //   190: pop
    //   191: aload_1
    //   192: ifnull -> 231
    //   195: aload_2
    //   196: invokevirtual getParameters : ()Landroid/hardware/Camera$Parameters;
    //   199: astore_3
    //   200: aload_3
    //   201: aload_1
    //   202: invokevirtual unflatten : (Ljava/lang/String;)V
    //   205: aload_2
    //   206: aload_3
    //   207: invokevirtual setParameters : (Landroid/hardware/Camera$Parameters;)V
    //   210: aload_0
    //   211: getfield c : Lcom/zxinglibrary/a/b;
    //   214: aload_2
    //   215: iconst_1
    //   216: invokevirtual a : (Landroid/hardware/Camera;Z)V
    //   219: goto -> 231
    //   222: getstatic com/zxinglibrary/a/d.a : Ljava/lang/String;
    //   225: ldc 'Camera rejected even safe-mode parameters! No configuration'
    //   227: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   230: pop
    //   231: aload_0
    //   232: monitorexit
    //   233: return
    //   234: astore_1
    //   235: aload_0
    //   236: monitorexit
    //   237: aload_1
    //   238: athrow
    //   239: astore_3
    //   240: goto -> 144
    //   243: astore_1
    //   244: goto -> 222
    // Exception table:
    //   from	to	target	type
    //   2	7	234	finally
    //   13	28	234	finally
    //   31	35	234	finally
    //   39	47	234	finally
    //   47	52	234	finally
    //   52	113	234	finally
    //   113	118	234	finally
    //   127	132	234	finally
    //   132	141	239	java/lang/RuntimeException
    //   132	141	234	finally
    //   144	191	234	finally
    //   195	205	234	finally
    //   205	219	243	java/lang/RuntimeException
    //   205	219	234	finally
    //   222	231	234	finally
  }
  
  public boolean a() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield d : Landroid/hardware/Camera;
    //   6: astore_2
    //   7: aload_2
    //   8: ifnull -> 16
    //   11: iconst_1
    //   12: istore_1
    //   13: goto -> 18
    //   16: iconst_0
    //   17: istore_1
    //   18: aload_0
    //   19: monitorexit
    //   20: iload_1
    //   21: ireturn
    //   22: astore_2
    //   23: aload_0
    //   24: monitorexit
    //   25: aload_2
    //   26: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	22	finally
  }
  
  public void b() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield d : Landroid/hardware/Camera;
    //   6: ifnull -> 31
    //   9: aload_0
    //   10: getfield d : Landroid/hardware/Camera;
    //   13: invokevirtual release : ()V
    //   16: aload_0
    //   17: aconst_null
    //   18: putfield d : Landroid/hardware/Camera;
    //   21: aload_0
    //   22: aconst_null
    //   23: putfield f : Landroid/graphics/Rect;
    //   26: aload_0
    //   27: aconst_null
    //   28: putfield g : Landroid/graphics/Rect;
    //   31: aload_0
    //   32: monitorexit
    //   33: return
    //   34: astore_1
    //   35: aload_0
    //   36: monitorexit
    //   37: aload_1
    //   38: athrow
    // Exception table:
    //   from	to	target	type
    //   2	31	34	finally
  }
  
  public void c() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield d : Landroid/hardware/Camera;
    //   6: astore_1
    //   7: aload_1
    //   8: ifnull -> 46
    //   11: aload_0
    //   12: getfield i : Z
    //   15: ifne -> 46
    //   18: aload_1
    //   19: invokevirtual startPreview : ()V
    //   22: aload_0
    //   23: iconst_1
    //   24: putfield i : Z
    //   27: aload_0
    //   28: new com/zxinglibrary/a/a
    //   31: dup
    //   32: aload_0
    //   33: getfield b : Landroid/content/Context;
    //   36: aload_0
    //   37: getfield d : Landroid/hardware/Camera;
    //   40: invokespecial <init> : (Landroid/content/Context;Landroid/hardware/Camera;)V
    //   43: putfield e : Lcom/zxinglibrary/a/a;
    //   46: aload_0
    //   47: monitorexit
    //   48: return
    //   49: astore_1
    //   50: aload_0
    //   51: monitorexit
    //   52: aload_1
    //   53: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	49	finally
    //   11	46	49	finally
  }
  
  public void d() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield e : Lcom/zxinglibrary/a/a;
    //   6: ifnull -> 21
    //   9: aload_0
    //   10: getfield e : Lcom/zxinglibrary/a/a;
    //   13: invokevirtual b : ()V
    //   16: aload_0
    //   17: aconst_null
    //   18: putfield e : Lcom/zxinglibrary/a/a;
    //   21: aload_0
    //   22: getfield d : Landroid/hardware/Camera;
    //   25: ifnull -> 56
    //   28: aload_0
    //   29: getfield i : Z
    //   32: ifeq -> 56
    //   35: aload_0
    //   36: getfield d : Landroid/hardware/Camera;
    //   39: invokevirtual stopPreview : ()V
    //   42: aload_0
    //   43: getfield m : Lcom/zxinglibrary/a/f;
    //   46: aconst_null
    //   47: iconst_0
    //   48: invokevirtual a : (Landroid/os/Handler;I)V
    //   51: aload_0
    //   52: iconst_0
    //   53: putfield i : Z
    //   56: aload_0
    //   57: monitorexit
    //   58: return
    //   59: astore_1
    //   60: aload_0
    //   61: monitorexit
    //   62: aload_1
    //   63: athrow
    // Exception table:
    //   from	to	target	type
    //   2	21	59	finally
    //   21	56	59	finally
  }
  
  public Rect e() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield f : Landroid/graphics/Rect;
    //   6: ifnonnull -> 155
    //   9: aload_0
    //   10: getfield d : Landroid/hardware/Camera;
    //   13: astore #5
    //   15: aload #5
    //   17: ifnonnull -> 24
    //   20: aload_0
    //   21: monitorexit
    //   22: aconst_null
    //   23: areturn
    //   24: aload_0
    //   25: getfield c : Lcom/zxinglibrary/a/b;
    //   28: invokevirtual b : ()Landroid/graphics/Point;
    //   31: astore #5
    //   33: aload #5
    //   35: ifnonnull -> 42
    //   38: aload_0
    //   39: monitorexit
    //   40: aconst_null
    //   41: areturn
    //   42: aload_0
    //   43: getfield b : Landroid/content/Context;
    //   46: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   49: invokevirtual getDisplayMetrics : ()Landroid/util/DisplayMetrics;
    //   52: getfield widthPixels : I
    //   55: i2d
    //   56: ldc2_w 0.6
    //   59: dmul
    //   60: d2i
    //   61: istore_1
    //   62: iload_1
    //   63: i2d
    //   64: ldc2_w 0.9
    //   67: dmul
    //   68: d2i
    //   69: istore_2
    //   70: aload #5
    //   72: getfield x : I
    //   75: iload_1
    //   76: isub
    //   77: iconst_2
    //   78: idiv
    //   79: istore_3
    //   80: aload #5
    //   82: getfield y : I
    //   85: iload_2
    //   86: isub
    //   87: iconst_2
    //   88: idiv
    //   89: istore #4
    //   91: aload_0
    //   92: new android/graphics/Rect
    //   95: dup
    //   96: iload_3
    //   97: iload #4
    //   99: iload_1
    //   100: iload_3
    //   101: iadd
    //   102: iload_2
    //   103: iload #4
    //   105: iadd
    //   106: invokespecial <init> : (IIII)V
    //   109: putfield f : Landroid/graphics/Rect;
    //   112: getstatic com/zxinglibrary/a/d.a : Ljava/lang/String;
    //   115: astore #5
    //   117: new java/lang/StringBuilder
    //   120: dup
    //   121: invokespecial <init> : ()V
    //   124: astore #6
    //   126: aload #6
    //   128: ldc 'Calculated framing rect: '
    //   130: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   133: pop
    //   134: aload #6
    //   136: aload_0
    //   137: getfield f : Landroid/graphics/Rect;
    //   140: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   143: pop
    //   144: aload #5
    //   146: aload #6
    //   148: invokevirtual toString : ()Ljava/lang/String;
    //   151: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   154: pop
    //   155: aload_0
    //   156: getfield f : Landroid/graphics/Rect;
    //   159: astore #5
    //   161: aload_0
    //   162: monitorexit
    //   163: aload #5
    //   165: areturn
    //   166: astore #5
    //   168: aload_0
    //   169: monitorexit
    //   170: aload #5
    //   172: athrow
    // Exception table:
    //   from	to	target	type
    //   2	15	166	finally
    //   24	33	166	finally
    //   42	62	166	finally
    //   70	155	166	finally
    //   155	161	166	finally
  }
  
  public Rect f() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield g : Landroid/graphics/Rect;
    //   6: ifnonnull -> 142
    //   9: aload_0
    //   10: invokevirtual e : ()Landroid/graphics/Rect;
    //   13: astore_1
    //   14: aload_1
    //   15: ifnonnull -> 22
    //   18: aload_0
    //   19: monitorexit
    //   20: aconst_null
    //   21: areturn
    //   22: new android/graphics/Rect
    //   25: dup
    //   26: aload_1
    //   27: invokespecial <init> : (Landroid/graphics/Rect;)V
    //   30: astore_1
    //   31: aload_0
    //   32: getfield c : Lcom/zxinglibrary/a/b;
    //   35: invokevirtual a : ()Landroid/graphics/Point;
    //   38: astore_2
    //   39: aload_0
    //   40: getfield c : Lcom/zxinglibrary/a/b;
    //   43: invokevirtual b : ()Landroid/graphics/Point;
    //   46: astore_3
    //   47: aload_2
    //   48: ifnull -> 138
    //   51: aload_3
    //   52: ifnonnull -> 58
    //   55: goto -> 138
    //   58: aload_1
    //   59: aload_1
    //   60: getfield left : I
    //   63: aload_2
    //   64: getfield y : I
    //   67: imul
    //   68: aload_3
    //   69: getfield x : I
    //   72: idiv
    //   73: putfield left : I
    //   76: aload_1
    //   77: aload_1
    //   78: getfield right : I
    //   81: aload_2
    //   82: getfield y : I
    //   85: imul
    //   86: aload_3
    //   87: getfield x : I
    //   90: idiv
    //   91: putfield right : I
    //   94: aload_1
    //   95: aload_1
    //   96: getfield top : I
    //   99: aload_2
    //   100: getfield x : I
    //   103: imul
    //   104: aload_3
    //   105: getfield y : I
    //   108: idiv
    //   109: putfield top : I
    //   112: aload_1
    //   113: aload_1
    //   114: getfield bottom : I
    //   117: aload_2
    //   118: getfield x : I
    //   121: imul
    //   122: aload_3
    //   123: getfield y : I
    //   126: idiv
    //   127: putfield bottom : I
    //   130: aload_0
    //   131: aload_1
    //   132: putfield g : Landroid/graphics/Rect;
    //   135: goto -> 142
    //   138: aload_0
    //   139: monitorexit
    //   140: aconst_null
    //   141: areturn
    //   142: aload_0
    //   143: getfield g : Landroid/graphics/Rect;
    //   146: astore_1
    //   147: aload_0
    //   148: monitorexit
    //   149: aload_1
    //   150: areturn
    //   151: astore_1
    //   152: aload_0
    //   153: monitorexit
    //   154: aload_1
    //   155: athrow
    // Exception table:
    //   from	to	target	type
    //   2	14	151	finally
    //   22	47	151	finally
    //   58	135	151	finally
    //   142	147	151	finally
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\zxinglibrary\a\d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */