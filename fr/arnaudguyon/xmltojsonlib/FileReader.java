package fr.arnaudguyon.xmltojsonlib;

import android.content.Context;
import android.support.annotation.NonNull;
import java.io.IOException;
import java.io.InputStream;

public class FileReader {
  public static String readFileFromAsset(@NonNull Context paramContext, @NonNull String paramString) {
    try {
      InputStream inputStream = paramContext.getAssets().open(paramString);
      paramString = readFileFromInputStream(inputStream);
      inputStream.close();
      return paramString;
    } catch (IOException iOException) {
      iOException.printStackTrace();
      return null;
    } 
  }
  
  public static String readFileFromInputStream(@NonNull InputStream paramInputStream) {
    // Byte code:
    //   0: new java/io/InputStreamReader
    //   3: dup
    //   4: aload_0
    //   5: invokespecial <init> : (Ljava/io/InputStream;)V
    //   8: astore_0
    //   9: new java/io/BufferedReader
    //   12: dup
    //   13: aload_0
    //   14: invokespecial <init> : (Ljava/io/Reader;)V
    //   17: astore_1
    //   18: new java/lang/StringBuilder
    //   21: dup
    //   22: invokespecial <init> : ()V
    //   25: astore_2
    //   26: aload_1
    //   27: invokevirtual readLine : ()Ljava/lang/String;
    //   30: astore_3
    //   31: aload_3
    //   32: ifnull -> 44
    //   35: aload_2
    //   36: aload_3
    //   37: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   40: pop
    //   41: goto -> 26
    //   44: aload_2
    //   45: invokevirtual toString : ()Ljava/lang/String;
    //   48: astore_2
    //   49: aload_1
    //   50: invokevirtual close : ()V
    //   53: aload_0
    //   54: invokevirtual close : ()V
    //   57: aload_2
    //   58: areturn
    //   59: astore_2
    //   60: aload_1
    //   61: invokevirtual close : ()V
    //   64: aload_0
    //   65: invokevirtual close : ()V
    //   68: aload_2
    //   69: athrow
    //   70: aload_1
    //   71: invokevirtual close : ()V
    //   74: aload_0
    //   75: invokevirtual close : ()V
    //   78: aconst_null
    //   79: areturn
    //   80: astore_2
    //   81: goto -> 70
    //   84: astore_1
    //   85: goto -> 53
    //   88: astore_0
    //   89: aload_2
    //   90: areturn
    //   91: astore_1
    //   92: goto -> 64
    //   95: astore_0
    //   96: goto -> 68
    //   99: astore_1
    //   100: goto -> 74
    //   103: astore_0
    //   104: goto -> 78
    // Exception table:
    //   from	to	target	type
    //   26	31	80	java/io/IOException
    //   26	31	59	finally
    //   35	41	80	java/io/IOException
    //   35	41	59	finally
    //   44	49	80	java/io/IOException
    //   44	49	59	finally
    //   49	53	84	java/io/IOException
    //   53	57	88	java/io/IOException
    //   60	64	91	java/io/IOException
    //   64	68	95	java/io/IOException
    //   70	74	99	java/io/IOException
    //   74	78	103	java/io/IOException
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\fr\arnaudguyon\xmltojsonlib\FileReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */