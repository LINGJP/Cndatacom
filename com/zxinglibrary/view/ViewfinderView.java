package com.zxinglibrary.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import com.a.a.o;
import com.zxinglibrary.R;
import com.zxinglibrary.a.d;
import java.util.ArrayList;
import java.util.List;

public final class ViewfinderView extends View {
  private static final int[] a = new int[] { 0, 64, 128, 192, 255, 192, 128, 64 };
  
  private d b;
  
  private final Paint c = new Paint(1);
  
  private Bitmap d;
  
  private final int e;
  
  private final int f;
  
  private final int g;
  
  private final int h;
  
  private final int i;
  
  private int j;
  
  private List<o> k;
  
  private List<o> l;
  
  public ViewfinderView(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    Resources resources = getResources();
    this.e = resources.getColor(R.color.viewfinder_mask);
    this.f = resources.getColor(R.color.result_view);
    this.g = resources.getColor(R.color.viewfinder_laser);
    this.h = resources.getColor(R.color.possible_result_points);
    this.i = resources.getColor(R.color.status_text);
    this.j = 0;
    this.k = new ArrayList<o>(5);
    this.l = null;
  }
  
  private void a(Canvas paramCanvas, Rect paramRect) {
    this.c.setColor(-1);
    this.c.setStrokeWidth(2.0F);
    this.c.setStyle(Paint.Style.STROKE);
    paramCanvas.drawRect(paramRect, this.c);
    this.c.setColor(getResources().getColor(R.color.viewfinder_mask));
    this.c.setStyle(Paint.Style.FILL);
    paramCanvas.drawRect((paramRect.left - 10), paramRect.top, paramRect.left, (paramRect.top + 30), this.c);
    paramCanvas.drawRect((paramRect.left - 10), (paramRect.top - 10), (paramRect.left + 30), paramRect.top, this.c);
    paramCanvas.drawRect(paramRect.right, paramRect.top, (paramRect.right + 10), (paramRect.top + 30), this.c);
    paramCanvas.drawRect((paramRect.right - 30), (paramRect.top - 10), (paramRect.right + 10), paramRect.top, this.c);
    paramCanvas.drawRect((paramRect.left - 10), (paramRect.bottom - 30), paramRect.left, paramRect.bottom, this.c);
    paramCanvas.drawRect((paramRect.left - 10), paramRect.bottom, (paramRect.left + 30), (paramRect.bottom + 10), this.c);
    paramCanvas.drawRect(paramRect.right, (paramRect.bottom - 30), (paramRect.right + 10), paramRect.bottom, this.c);
    paramCanvas.drawRect((paramRect.right - 30), paramRect.bottom, (paramRect.right + 10), (paramRect.bottom + 10), this.c);
  }
  
  private void a(Canvas paramCanvas, Rect paramRect, int paramInt) {
    String str1 = getResources().getString(R.string.viewfinderview_status_text1);
    String str2 = getResources().getString(R.string.viewfinderview_status_text2);
    this.c.setColor(this.i);
    this.c.setTextSize(45);
    paramCanvas.drawText(str1, ((paramInt - (int)this.c.measureText(str1)) / 2), (paramRect.top - 180), this.c);
    paramCanvas.drawText(str2, ((paramInt - (int)this.c.measureText(str2)) / 2), (paramRect.top - 180 + 60), this.c);
  }
  
  public void a(o paramo) {
    synchronized (this.k) {
      null.add(paramo);
      int i = null.size();
      if (i > 20)
        null.subList(0, i - 10).clear(); 
      return;
    } 
  }
  
  @SuppressLint({"DrawAllocation"})
  public void onDraw(Canvas paramCanvas) {
    // Byte code:
    //   0: aload_0
    //   1: getfield b : Lcom/zxinglibrary/a/d;
    //   4: ifnonnull -> 8
    //   7: return
    //   8: aload_0
    //   9: getfield b : Lcom/zxinglibrary/a/d;
    //   12: invokevirtual e : ()Landroid/graphics/Rect;
    //   15: astore #7
    //   17: aload_0
    //   18: getfield b : Lcom/zxinglibrary/a/d;
    //   21: invokevirtual f : ()Landroid/graphics/Rect;
    //   24: astore #8
    //   26: aload #7
    //   28: ifnull -> 574
    //   31: aload #8
    //   33: ifnonnull -> 37
    //   36: return
    //   37: aload_1
    //   38: invokevirtual getWidth : ()I
    //   41: istore #5
    //   43: aload_1
    //   44: invokevirtual getHeight : ()I
    //   47: istore #6
    //   49: aload_0
    //   50: getfield c : Landroid/graphics/Paint;
    //   53: astore #9
    //   55: aload_0
    //   56: getfield d : Landroid/graphics/Bitmap;
    //   59: ifnull -> 71
    //   62: aload_0
    //   63: getfield f : I
    //   66: istore #4
    //   68: goto -> 77
    //   71: aload_0
    //   72: getfield e : I
    //   75: istore #4
    //   77: aload #9
    //   79: iload #4
    //   81: invokevirtual setColor : (I)V
    //   84: iload #5
    //   86: i2f
    //   87: fstore_2
    //   88: aload_1
    //   89: fconst_0
    //   90: fconst_0
    //   91: fload_2
    //   92: aload #7
    //   94: getfield top : I
    //   97: i2f
    //   98: aload_0
    //   99: getfield c : Landroid/graphics/Paint;
    //   102: invokevirtual drawRect : (FFFFLandroid/graphics/Paint;)V
    //   105: aload_1
    //   106: fconst_0
    //   107: aload #7
    //   109: getfield top : I
    //   112: i2f
    //   113: aload #7
    //   115: getfield left : I
    //   118: i2f
    //   119: aload #7
    //   121: getfield bottom : I
    //   124: iconst_1
    //   125: iadd
    //   126: i2f
    //   127: aload_0
    //   128: getfield c : Landroid/graphics/Paint;
    //   131: invokevirtual drawRect : (FFFFLandroid/graphics/Paint;)V
    //   134: aload_1
    //   135: aload #7
    //   137: getfield right : I
    //   140: iconst_1
    //   141: iadd
    //   142: i2f
    //   143: aload #7
    //   145: getfield top : I
    //   148: i2f
    //   149: fload_2
    //   150: aload #7
    //   152: getfield bottom : I
    //   155: iconst_1
    //   156: iadd
    //   157: i2f
    //   158: aload_0
    //   159: getfield c : Landroid/graphics/Paint;
    //   162: invokevirtual drawRect : (FFFFLandroid/graphics/Paint;)V
    //   165: aload_1
    //   166: fconst_0
    //   167: aload #7
    //   169: getfield bottom : I
    //   172: iconst_1
    //   173: iadd
    //   174: i2f
    //   175: fload_2
    //   176: iload #6
    //   178: i2f
    //   179: aload_0
    //   180: getfield c : Landroid/graphics/Paint;
    //   183: invokevirtual drawRect : (FFFFLandroid/graphics/Paint;)V
    //   186: aload_0
    //   187: getfield d : Landroid/graphics/Bitmap;
    //   190: ifnull -> 219
    //   193: aload_0
    //   194: getfield c : Landroid/graphics/Paint;
    //   197: sipush #160
    //   200: invokevirtual setAlpha : (I)V
    //   203: aload_1
    //   204: aload_0
    //   205: getfield d : Landroid/graphics/Bitmap;
    //   208: aconst_null
    //   209: aload #7
    //   211: aload_0
    //   212: getfield c : Landroid/graphics/Paint;
    //   215: invokevirtual drawBitmap : (Landroid/graphics/Bitmap;Landroid/graphics/Rect;Landroid/graphics/Rect;Landroid/graphics/Paint;)V
    //   218: return
    //   219: aload_0
    //   220: aload_1
    //   221: aload #7
    //   223: invokespecial a : (Landroid/graphics/Canvas;Landroid/graphics/Rect;)V
    //   226: aload_0
    //   227: aload_1
    //   228: aload #7
    //   230: iload #5
    //   232: invokespecial a : (Landroid/graphics/Canvas;Landroid/graphics/Rect;I)V
    //   235: aload #7
    //   237: invokevirtual width : ()I
    //   240: i2f
    //   241: aload #8
    //   243: invokevirtual width : ()I
    //   246: i2f
    //   247: fdiv
    //   248: fstore_2
    //   249: aload #7
    //   251: invokevirtual height : ()I
    //   254: i2f
    //   255: aload #8
    //   257: invokevirtual height : ()I
    //   260: i2f
    //   261: fdiv
    //   262: fstore_3
    //   263: aload_0
    //   264: getfield k : Ljava/util/List;
    //   267: astore #9
    //   269: aload_0
    //   270: getfield l : Ljava/util/List;
    //   273: astore #8
    //   275: aload #7
    //   277: getfield left : I
    //   280: istore #4
    //   282: aload #7
    //   284: getfield top : I
    //   287: istore #5
    //   289: aload #9
    //   291: invokeinterface isEmpty : ()Z
    //   296: ifeq -> 307
    //   299: aload_0
    //   300: aconst_null
    //   301: putfield l : Ljava/util/List;
    //   304: goto -> 420
    //   307: aload_0
    //   308: new java/util/ArrayList
    //   311: dup
    //   312: iconst_5
    //   313: invokespecial <init> : (I)V
    //   316: putfield k : Ljava/util/List;
    //   319: aload_0
    //   320: aload #9
    //   322: putfield l : Ljava/util/List;
    //   325: aload_0
    //   326: getfield c : Landroid/graphics/Paint;
    //   329: sipush #160
    //   332: invokevirtual setAlpha : (I)V
    //   335: aload_0
    //   336: getfield c : Landroid/graphics/Paint;
    //   339: aload_0
    //   340: getfield h : I
    //   343: invokevirtual setColor : (I)V
    //   346: aload #9
    //   348: monitorenter
    //   349: aload #9
    //   351: invokeinterface iterator : ()Ljava/util/Iterator;
    //   356: astore #10
    //   358: aload #10
    //   360: invokeinterface hasNext : ()Z
    //   365: ifeq -> 417
    //   368: aload #10
    //   370: invokeinterface next : ()Ljava/lang/Object;
    //   375: checkcast com/a/a/o
    //   378: astore #11
    //   380: aload_1
    //   381: aload #11
    //   383: invokevirtual a : ()F
    //   386: fload_2
    //   387: fmul
    //   388: f2i
    //   389: iload #4
    //   391: iadd
    //   392: i2f
    //   393: aload #11
    //   395: invokevirtual b : ()F
    //   398: fload_3
    //   399: fmul
    //   400: f2i
    //   401: iload #5
    //   403: iadd
    //   404: i2f
    //   405: ldc 6.0
    //   407: aload_0
    //   408: getfield c : Landroid/graphics/Paint;
    //   411: invokevirtual drawCircle : (FFFLandroid/graphics/Paint;)V
    //   414: goto -> 358
    //   417: aload #9
    //   419: monitorexit
    //   420: aload #8
    //   422: ifnull -> 528
    //   425: aload_0
    //   426: getfield c : Landroid/graphics/Paint;
    //   429: bipush #80
    //   431: invokevirtual setAlpha : (I)V
    //   434: aload_0
    //   435: getfield c : Landroid/graphics/Paint;
    //   438: aload_0
    //   439: getfield h : I
    //   442: invokevirtual setColor : (I)V
    //   445: aload #8
    //   447: monitorenter
    //   448: aload #8
    //   450: invokeinterface iterator : ()Ljava/util/Iterator;
    //   455: astore #9
    //   457: aload #9
    //   459: invokeinterface hasNext : ()Z
    //   464: ifeq -> 516
    //   467: aload #9
    //   469: invokeinterface next : ()Ljava/lang/Object;
    //   474: checkcast com/a/a/o
    //   477: astore #10
    //   479: aload_1
    //   480: aload #10
    //   482: invokevirtual a : ()F
    //   485: fload_2
    //   486: fmul
    //   487: f2i
    //   488: iload #4
    //   490: iadd
    //   491: i2f
    //   492: aload #10
    //   494: invokevirtual b : ()F
    //   497: fload_3
    //   498: fmul
    //   499: f2i
    //   500: iload #5
    //   502: iadd
    //   503: i2f
    //   504: ldc 3.0
    //   506: aload_0
    //   507: getfield c : Landroid/graphics/Paint;
    //   510: invokevirtual drawCircle : (FFFLandroid/graphics/Paint;)V
    //   513: goto -> 457
    //   516: aload #8
    //   518: monitorexit
    //   519: goto -> 528
    //   522: astore_1
    //   523: aload #8
    //   525: monitorexit
    //   526: aload_1
    //   527: athrow
    //   528: aload_0
    //   529: ldc2_w 80
    //   532: aload #7
    //   534: getfield left : I
    //   537: bipush #6
    //   539: isub
    //   540: aload #7
    //   542: getfield top : I
    //   545: bipush #6
    //   547: isub
    //   548: aload #7
    //   550: getfield right : I
    //   553: bipush #6
    //   555: iadd
    //   556: aload #7
    //   558: getfield bottom : I
    //   561: bipush #6
    //   563: iadd
    //   564: invokevirtual postInvalidateDelayed : (JIIII)V
    //   567: return
    //   568: astore_1
    //   569: aload #9
    //   571: monitorexit
    //   572: aload_1
    //   573: athrow
    //   574: return
    // Exception table:
    //   from	to	target	type
    //   349	358	568	finally
    //   358	414	568	finally
    //   417	420	568	finally
    //   448	457	522	finally
    //   457	513	522	finally
    //   516	519	522	finally
    //   523	526	522	finally
    //   569	572	568	finally
  }
  
  public void setCameraManager(d paramd) {
    this.b = paramd;
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\zxinglibrary\view\ViewfinderView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */