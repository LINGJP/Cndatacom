package com.cndatacom.cnportalclient.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.LruCache;
import android.widget.ImageView;
import com.cndatacom.cnportalclient.async.AsyncHandlerTask;
import com.cndatacom.cnportalclient.http.HttpClient;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageUtil {
  private static LruCache<String, Bitmap> a = a();
  
  private static int a(BitmapFactory.Options paramOptions, int paramInt1, int paramInt2) {
    int i = b(paramOptions, paramInt1, paramInt2);
    if (i <= 8) {
      paramInt1 = 1;
      while (true) {
        paramInt2 = paramInt1;
        if (paramInt1 < i) {
          paramInt1 <<= 1;
          continue;
        } 
        break;
      } 
    } else {
      paramInt2 = (i + 7) / 8 * 8;
    } 
    return paramInt2;
  }
  
  private static Bitmap a(Context paramContext, String paramString1, String paramString2, int paramInt1, int paramInt2) {
    Bitmap bitmap2 = a(paramString1, paramInt1, paramInt2);
    Bitmap bitmap1 = bitmap2;
    if (bitmap2 == null) {
      Bitmap bitmap = loadImageFormUrl(paramContext, paramString2, paramInt1, paramInt2);
      bitmap1 = bitmap;
      if (bitmap != null) {
        writeBitmapToFile(paramString1, bitmap, 100);
        bitmap1 = bitmap;
      } 
    } 
    return bitmap1;
  }
  
  private static Bitmap a(String paramString, int paramInt1, int paramInt2) {
    try {
      BitmapFactory.Options options = new BitmapFactory.Options();
      options.inJustDecodeBounds = false;
      options.inSampleSize = a(options, -1, paramInt1 * paramInt2 / 2);
      options.inJustDecodeBounds = false;
      try {
        return BitmapFactory.decodeFile(paramString, options);
      } catch (OutOfMemoryError outOfMemoryError) {
        outOfMemoryError.printStackTrace();
      } 
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
    return null;
  }
  
  private static Bitmap a(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    try {
      paramInt1 = paramInt1 * paramInt2 / 2;
      BitmapFactory.Options options = new BitmapFactory.Options();
      options.inJustDecodeBounds = true;
      BitmapFactory.decodeByteArray(paramArrayOfbyte, 0, paramArrayOfbyte.length, options);
      options.inSampleSize = a(options, -1, paramInt1);
      options.inJustDecodeBounds = false;
      return BitmapFactory.decodeByteArray(paramArrayOfbyte, 0, paramArrayOfbyte.length, options);
    } catch (Exception exception) {
      exception.printStackTrace();
      return null;
    } 
  }
  
  private static LruCache<String, Bitmap> a() {
    return new LruCache<String, Bitmap>((int)Runtime.getRuntime().maxMemory() / 8) {
        protected int a(String param1String, Bitmap param1Bitmap) {
          return param1Bitmap.getRowBytes() * param1Bitmap.getHeight();
        }
      };
  }
  
  private static int b(BitmapFactory.Options paramOptions, int paramInt1, int paramInt2) {
    int i;
    int j;
    double d1 = paramOptions.outWidth;
    double d2 = paramOptions.outHeight;
    if (paramInt2 == -1) {
      i = 1;
    } else {
      i = (int)Math.ceil(Math.sqrt(d1 * d2 / paramInt2));
    } 
    if (paramInt1 == -1) {
      j = 128;
    } else {
      double d = paramInt1;
      j = (int)Math.min(Math.floor(d1 / d), Math.floor(d2 / d));
    } 
    return (j < i) ? i : ((paramInt2 == -1 && paramInt1 == -1) ? 1 : ((paramInt1 == -1) ? i : j));
  }
  
  public static String bitmapToBase64(Bitmap paramBitmap) {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: aload_0
    //   3: ifnull -> 119
    //   6: new java/io/ByteArrayOutputStream
    //   9: dup
    //   10: invokespecial <init> : ()V
    //   13: astore_2
    //   14: aload_2
    //   15: astore_1
    //   16: aload_0
    //   17: getstatic android/graphics/Bitmap$CompressFormat.JPEG : Landroid/graphics/Bitmap$CompressFormat;
    //   20: bipush #100
    //   22: aload_2
    //   23: invokevirtual compress : (Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   26: pop
    //   27: aload_2
    //   28: astore_1
    //   29: aload_2
    //   30: invokevirtual flush : ()V
    //   33: aload_2
    //   34: astore_1
    //   35: aload_2
    //   36: invokevirtual close : ()V
    //   39: aload_2
    //   40: astore_1
    //   41: aload_2
    //   42: invokevirtual toByteArray : ()[B
    //   45: iconst_0
    //   46: invokestatic encodeToString : ([BI)Ljava/lang/String;
    //   49: astore_0
    //   50: aload_2
    //   51: astore_1
    //   52: goto -> 121
    //   55: astore_0
    //   56: goto -> 68
    //   59: astore_0
    //   60: aconst_null
    //   61: astore_1
    //   62: goto -> 97
    //   65: astore_0
    //   66: aconst_null
    //   67: astore_2
    //   68: aload_2
    //   69: astore_1
    //   70: aload_0
    //   71: invokevirtual printStackTrace : ()V
    //   74: aload_2
    //   75: ifnull -> 94
    //   78: aload_2
    //   79: invokevirtual flush : ()V
    //   82: aload_2
    //   83: invokevirtual close : ()V
    //   86: goto -> 94
    //   89: astore_0
    //   90: aload_0
    //   91: invokevirtual printStackTrace : ()V
    //   94: aconst_null
    //   95: areturn
    //   96: astore_0
    //   97: aload_1
    //   98: ifnull -> 117
    //   101: aload_1
    //   102: invokevirtual flush : ()V
    //   105: aload_1
    //   106: invokevirtual close : ()V
    //   109: goto -> 117
    //   112: astore_1
    //   113: aload_1
    //   114: invokevirtual printStackTrace : ()V
    //   117: aload_0
    //   118: athrow
    //   119: aconst_null
    //   120: astore_0
    //   121: aload_1
    //   122: ifnull -> 140
    //   125: aload_1
    //   126: invokevirtual flush : ()V
    //   129: aload_1
    //   130: invokevirtual close : ()V
    //   133: aload_0
    //   134: areturn
    //   135: astore_1
    //   136: aload_1
    //   137: invokevirtual printStackTrace : ()V
    //   140: aload_0
    //   141: areturn
    // Exception table:
    //   from	to	target	type
    //   6	14	65	java/io/IOException
    //   6	14	59	finally
    //   16	27	55	java/io/IOException
    //   16	27	96	finally
    //   29	33	55	java/io/IOException
    //   29	33	96	finally
    //   35	39	55	java/io/IOException
    //   35	39	96	finally
    //   41	50	55	java/io/IOException
    //   41	50	96	finally
    //   70	74	96	finally
    //   78	86	89	java/io/IOException
    //   101	109	112	java/io/IOException
    //   125	133	135	java/io/IOException
  }
  
  public static void loadImage(Context paramContext, String paramString1, String paramString2, ImageView paramImageView, int paramInt1, int paramInt2, AsyncHandlerTask paramAsyncHandlerTask, ImageCallback paramImageCallback) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramString1.hashCode());
    stringBuilder.append("");
    String str = stringBuilder.toString();
    if (paramImageView != null && paramImageView.getTag() != null && paramImageView.getTag().equals(str))
      return; 
    paramImageView.setTag(str);
    paramAsyncHandlerTask.execute(new AsyncHandlerTask.Task<Bitmap>(paramContext, paramString1, paramString2, str, paramInt1, paramInt2, paramImageView, paramImageCallback) {
          protected void a(Bitmap param1Bitmap) {
            super.a(param1Bitmap);
            if (this.g == null)
              return; 
            if (param1Bitmap != null && this.g.getTag() != null && this.g.getTag().equals(this.d)) {
              if (this.h != null) {
                this.h.success(param1Bitmap);
                return;
              } 
              this.g.setImageDrawable((Drawable)new BitmapDrawable(param1Bitmap));
              return;
            } 
            this.g.setTag(null);
          }
          
          protected Bitmap b() {
            return ImageUtil.loadImageFormCache(this.a, this.b, this.c, this.d, this.e, this.f);
          }
        });
  }
  
  public static Bitmap loadImageFormCache(Context paramContext, String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2) {
    Bitmap bitmap1;
    if (a.get(paramString3) != null) {
      bitmap1 = (Bitmap)a.get(paramString3);
    } else {
      bitmap1 = null;
    } 
    Bitmap bitmap2 = bitmap1;
    if (bitmap1 == null) {
      Bitmap bitmap = a(paramContext, paramString1, paramString2, paramInt1, paramInt2);
      bitmap2 = bitmap;
      if (bitmap != null) {
        a.put(paramString3, bitmap);
        bitmap2 = bitmap;
      } 
    } 
    return bitmap2;
  }
  
  public static Bitmap loadImageFormUrl(Context paramContext, String paramString, int paramInt1, int paramInt2) {
    if (paramString == null)
      return null; 
    HttpClient.Result result = (new HttpClient()).get(paramString);
    return (result.isSuccessful() && result.getData() != null) ? (((result.getData()).length == 0) ? null : a(result.getData(), paramInt1, paramInt2)) : null;
  }
  
  public static void release() {
    if (a != null)
      a = a(); 
  }
  
  public static void writeBitmapToFile(String paramString, Bitmap paramBitmap, int paramInt) {
    try {
      File file = new File(paramString);
      if (!file.getParentFile().exists())
        file.getParentFile().mkdirs(); 
      BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
      paramBitmap.compress(Bitmap.CompressFormat.JPEG, paramInt, bufferedOutputStream);
      bufferedOutputStream.flush();
      bufferedOutputStream.close();
      return;
    } catch (IOException iOException) {
      iOException.printStackTrace();
      return;
    } 
  }
  
  public static interface ImageCallback {
    void success(Bitmap param1Bitmap);
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclien\\utils\ImageUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */