package com.cndatacom.cnportalclient.utils;

import android.os.StatFs;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.net.URLConnection;
import java.security.MessageDigest;

public class FileUtils {
  public static boolean delFile(File paramFile) {
    boolean bool = paramFile.exists();
    int i = 0;
    if (!bool)
      return false; 
    if (paramFile.isFile())
      return paramFile.getAbsoluteFile().delete(); 
    File[] arrayOfFile = paramFile.listFiles();
    int j = arrayOfFile.length;
    while (i < j) {
      delFile(arrayOfFile[i]);
      i++;
    } 
    return paramFile.getAbsoluteFile().delete();
  }
  
  public static boolean delFile(File paramFile, int paramInt) {
    if (!paramFile.exists())
      return false; 
    long l1 = System.currentTimeMillis() - (paramInt * 24 * 60 * 60 * 1000);
    long l2 = paramFile.lastModified();
    if (paramFile.isFile()) {
      if (l2 <= l1)
        return paramFile.getAbsoluteFile().delete(); 
    } else {
      File[] arrayOfFile = paramFile.listFiles();
      int j = arrayOfFile.length;
      for (int i = 0; i < j; i++)
        delFile(arrayOfFile[i], paramInt); 
      if (l2 <= l1)
        return paramFile.getAbsoluteFile().delete(); 
    } 
    return false;
  }
  
  public static String getFileMD5(String paramString) {
    try {
      MessageDigest messageDigest = MessageDigest.getInstance("MD5");
      byte[] arrayOfByte = new byte[1024];
      FileInputStream fileInputStream = new FileInputStream(new File(paramString));
      while (true) {
        int i = fileInputStream.read(arrayOfByte);
        if (i != -1) {
          messageDigest.update(arrayOfByte, 0, i);
          continue;
        } 
        String str = (new BigInteger(1, messageDigest.digest())).toString(16);
        fileInputStream.close();
        return str.toLowerCase();
      } 
    } catch (Exception exception) {
      exception.printStackTrace();
      return "";
    } 
  }
  
  public static long getMemorySize(String paramString) {
    StatFs statFs = new StatFs(paramString);
    long l = statFs.getBlockSizeLong();
    return statFs.getBlockCountLong() * l;
  }
  
  public static String getMimeType(String paramString) {
    return URLConnection.getFileNameMap().getContentTypeFor(paramString);
  }
  
  public static boolean toZip(String paramString1, String paramString2) {
    // Byte code:
    //   0: new java/io/File
    //   3: dup
    //   4: aload_0
    //   5: invokespecial <init> : (Ljava/lang/String;)V
    //   8: astore #7
    //   10: aload #7
    //   12: invokevirtual exists : ()Z
    //   15: ifne -> 20
    //   18: iconst_0
    //   19: ireturn
    //   20: aconst_null
    //   21: astore #8
    //   23: aconst_null
    //   24: astore_0
    //   25: aconst_null
    //   26: astore #5
    //   28: aconst_null
    //   29: astore #6
    //   31: new java/io/File
    //   34: dup
    //   35: aload_1
    //   36: invokespecial <init> : (Ljava/lang/String;)V
    //   39: astore_1
    //   40: aload_1
    //   41: invokevirtual exists : ()Z
    //   44: ifeq -> 55
    //   47: aload_1
    //   48: invokevirtual getAbsoluteFile : ()Ljava/io/File;
    //   51: invokevirtual delete : ()Z
    //   54: pop
    //   55: aload #7
    //   57: invokevirtual listFiles : ()[Ljava/io/File;
    //   60: astore #9
    //   62: aload #9
    //   64: ifnull -> 441
    //   67: aload #9
    //   69: arraylength
    //   70: iconst_1
    //   71: if_icmpge -> 77
    //   74: goto -> 441
    //   77: new java/io/FileOutputStream
    //   80: dup
    //   81: aload_1
    //   82: invokespecial <init> : (Ljava/io/File;)V
    //   85: astore_1
    //   86: new java/util/zip/ZipOutputStream
    //   89: dup
    //   90: new java/io/BufferedOutputStream
    //   93: dup
    //   94: aload_1
    //   95: invokespecial <init> : (Ljava/io/OutputStream;)V
    //   98: invokespecial <init> : (Ljava/io/OutputStream;)V
    //   101: astore #6
    //   103: sipush #10240
    //   106: newarray byte
    //   108: astore #10
    //   110: aconst_null
    //   111: astore #5
    //   113: aload #5
    //   115: astore_0
    //   116: iconst_0
    //   117: istore_2
    //   118: iload_2
    //   119: aload #9
    //   121: arraylength
    //   122: if_icmpge -> 303
    //   125: aload #6
    //   127: new java/util/zip/ZipEntry
    //   130: dup
    //   131: aload #9
    //   133: iload_2
    //   134: aaload
    //   135: invokevirtual getName : ()Ljava/lang/String;
    //   138: invokespecial <init> : (Ljava/lang/String;)V
    //   141: invokevirtual putNextEntry : (Ljava/util/zip/ZipEntry;)V
    //   144: new java/io/FileInputStream
    //   147: dup
    //   148: aload #9
    //   150: iload_2
    //   151: aaload
    //   152: invokespecial <init> : (Ljava/io/File;)V
    //   155: astore #7
    //   157: new java/io/BufferedInputStream
    //   160: dup
    //   161: aload #7
    //   163: sipush #10240
    //   166: invokespecial <init> : (Ljava/io/InputStream;I)V
    //   169: astore #5
    //   171: aload #5
    //   173: aload #10
    //   175: iconst_0
    //   176: sipush #10240
    //   179: invokevirtual read : ([BII)I
    //   182: istore_3
    //   183: iload_3
    //   184: iconst_m1
    //   185: if_icmpeq -> 200
    //   188: aload #6
    //   190: aload #10
    //   192: iconst_0
    //   193: iload_3
    //   194: invokevirtual write : ([BII)V
    //   197: goto -> 171
    //   200: iload_2
    //   201: iconst_1
    //   202: iadd
    //   203: istore_2
    //   204: aload #5
    //   206: astore_0
    //   207: aload #7
    //   209: astore #5
    //   211: goto -> 118
    //   214: astore #8
    //   216: aload #5
    //   218: astore_0
    //   219: goto -> 248
    //   222: astore #8
    //   224: aload #5
    //   226: astore_0
    //   227: aload #8
    //   229: astore #5
    //   231: goto -> 267
    //   234: astore #8
    //   236: aload #5
    //   238: astore_0
    //   239: aload #8
    //   241: astore #5
    //   243: goto -> 286
    //   246: astore #8
    //   248: aload #7
    //   250: astore #5
    //   252: aload_1
    //   253: astore #7
    //   255: aload #6
    //   257: astore #10
    //   259: aload #8
    //   261: astore_1
    //   262: goto -> 682
    //   265: astore #5
    //   267: aload #7
    //   269: astore #8
    //   271: aload_1
    //   272: astore #7
    //   274: aload #8
    //   276: astore_1
    //   277: aload #6
    //   279: astore #9
    //   281: goto -> 554
    //   284: astore #5
    //   286: aload #7
    //   288: astore #8
    //   290: aload_1
    //   291: astore #7
    //   293: aload #8
    //   295: astore_1
    //   296: aload #6
    //   298: astore #9
    //   300: goto -> 612
    //   303: iconst_1
    //   304: istore #4
    //   306: aload_0
    //   307: astore #7
    //   309: goto -> 454
    //   312: astore #8
    //   314: aload_1
    //   315: astore #7
    //   317: aload #6
    //   319: astore #10
    //   321: aload #8
    //   323: astore_1
    //   324: goto -> 682
    //   327: astore #8
    //   329: aload_1
    //   330: astore #7
    //   332: aload #5
    //   334: astore_1
    //   335: aload #6
    //   337: astore #9
    //   339: aload #8
    //   341: astore #5
    //   343: goto -> 554
    //   346: astore #8
    //   348: aload_1
    //   349: astore #7
    //   351: aload #5
    //   353: astore_1
    //   354: aload #6
    //   356: astore #9
    //   358: aload #8
    //   360: astore #5
    //   362: goto -> 612
    //   365: astore #8
    //   367: aconst_null
    //   368: astore_0
    //   369: aload_1
    //   370: astore #7
    //   372: aload #6
    //   374: astore #10
    //   376: aload #8
    //   378: astore_1
    //   379: goto -> 682
    //   382: astore #5
    //   384: aconst_null
    //   385: astore_0
    //   386: aload_1
    //   387: astore #7
    //   389: aload #8
    //   391: astore_1
    //   392: aload #6
    //   394: astore #9
    //   396: goto -> 554
    //   399: astore #5
    //   401: aconst_null
    //   402: astore #8
    //   404: aload_1
    //   405: astore #7
    //   407: aload_0
    //   408: astore_1
    //   409: aload #6
    //   411: astore #9
    //   413: aload #8
    //   415: astore_0
    //   416: goto -> 612
    //   419: astore_0
    //   420: aload_1
    //   421: astore #7
    //   423: aload_0
    //   424: astore_1
    //   425: goto -> 533
    //   428: astore #5
    //   430: aload_1
    //   431: astore #7
    //   433: goto -> 546
    //   436: astore #5
    //   438: goto -> 764
    //   441: aconst_null
    //   442: astore_1
    //   443: aload_1
    //   444: astore_0
    //   445: aload_0
    //   446: astore #7
    //   448: iconst_0
    //   449: istore #4
    //   451: aload_0
    //   452: astore #5
    //   454: aload #6
    //   456: ifnull -> 467
    //   459: aload #6
    //   461: invokevirtual close : ()V
    //   464: goto -> 467
    //   467: aload #7
    //   469: ifnull -> 477
    //   472: aload #7
    //   474: invokevirtual close : ()V
    //   477: aload_1
    //   478: ifnull -> 485
    //   481: aload_1
    //   482: invokevirtual close : ()V
    //   485: aload #5
    //   487: ifnull -> 526
    //   490: aload #5
    //   492: invokevirtual close : ()V
    //   495: iload #4
    //   497: ireturn
    //   498: iconst_2
    //   499: anewarray java/lang/String
    //   502: dup
    //   503: iconst_0
    //   504: aload_0
    //   505: invokevirtual toString : ()Ljava/lang/String;
    //   508: aastore
    //   509: dup
    //   510: iconst_1
    //   511: ldc 'FileUtils toZip Exception'
    //   513: aastore
    //   514: invokestatic e : ([Ljava/lang/String;)V
    //   517: new java/lang/RuntimeException
    //   520: dup
    //   521: aload_0
    //   522: invokespecial <init> : (Ljava/lang/Throwable;)V
    //   525: athrow
    //   526: iload #4
    //   528: ireturn
    //   529: astore_1
    //   530: aconst_null
    //   531: astore #7
    //   533: aconst_null
    //   534: astore #10
    //   536: aconst_null
    //   537: astore_0
    //   538: goto -> 682
    //   541: astore #5
    //   543: aconst_null
    //   544: astore #7
    //   546: aconst_null
    //   547: astore #9
    //   549: aconst_null
    //   550: astore_0
    //   551: aload #8
    //   553: astore_1
    //   554: aload #7
    //   556: astore #6
    //   558: aload_1
    //   559: astore #8
    //   561: aload #9
    //   563: astore #10
    //   565: aload_0
    //   566: astore #11
    //   568: iconst_2
    //   569: anewarray java/lang/String
    //   572: dup
    //   573: iconst_0
    //   574: aload #5
    //   576: invokevirtual toString : ()Ljava/lang/String;
    //   579: aastore
    //   580: dup
    //   581: iconst_1
    //   582: ldc 'FileUtils toZip IOException'
    //   584: aastore
    //   585: invokestatic e : ([Ljava/lang/String;)V
    //   588: aload #7
    //   590: astore #6
    //   592: aload_1
    //   593: astore #8
    //   595: aload #9
    //   597: astore #10
    //   599: aload_0
    //   600: astore #11
    //   602: new java/lang/RuntimeException
    //   605: dup
    //   606: aload #5
    //   608: invokespecial <init> : (Ljava/lang/Throwable;)V
    //   611: athrow
    //   612: aload #7
    //   614: astore #6
    //   616: aload_1
    //   617: astore #8
    //   619: aload #9
    //   621: astore #10
    //   623: aload_0
    //   624: astore #11
    //   626: iconst_2
    //   627: anewarray java/lang/String
    //   630: dup
    //   631: iconst_0
    //   632: aload #5
    //   634: invokevirtual toString : ()Ljava/lang/String;
    //   637: aastore
    //   638: dup
    //   639: iconst_1
    //   640: ldc 'FileUtils toZip FileNotFoundException'
    //   642: aastore
    //   643: invokestatic e : ([Ljava/lang/String;)V
    //   646: aload #7
    //   648: astore #6
    //   650: aload_1
    //   651: astore #8
    //   653: aload #9
    //   655: astore #10
    //   657: aload_0
    //   658: astore #11
    //   660: new java/lang/RuntimeException
    //   663: dup
    //   664: aload #5
    //   666: invokespecial <init> : (Ljava/lang/Throwable;)V
    //   669: athrow
    //   670: astore_1
    //   671: aload #11
    //   673: astore_0
    //   674: aload #8
    //   676: astore #5
    //   678: aload #6
    //   680: astore #7
    //   682: aload #10
    //   684: ifnull -> 695
    //   687: aload #10
    //   689: invokevirtual close : ()V
    //   692: goto -> 695
    //   695: aload_0
    //   696: ifnull -> 703
    //   699: aload_0
    //   700: invokevirtual close : ()V
    //   703: aload #7
    //   705: ifnull -> 713
    //   708: aload #7
    //   710: invokevirtual close : ()V
    //   713: aload #5
    //   715: ifnull -> 754
    //   718: aload #5
    //   720: invokevirtual close : ()V
    //   723: goto -> 754
    //   726: iconst_2
    //   727: anewarray java/lang/String
    //   730: dup
    //   731: iconst_0
    //   732: aload_0
    //   733: invokevirtual toString : ()Ljava/lang/String;
    //   736: aastore
    //   737: dup
    //   738: iconst_1
    //   739: ldc 'FileUtils toZip Exception'
    //   741: aastore
    //   742: invokestatic e : ([Ljava/lang/String;)V
    //   745: new java/lang/RuntimeException
    //   748: dup
    //   749: aload_0
    //   750: invokespecial <init> : (Ljava/lang/Throwable;)V
    //   753: athrow
    //   754: aload_1
    //   755: athrow
    //   756: astore_0
    //   757: goto -> 498
    //   760: astore #5
    //   762: aconst_null
    //   763: astore_1
    //   764: aconst_null
    //   765: astore #9
    //   767: aconst_null
    //   768: astore #6
    //   770: aload_1
    //   771: astore #7
    //   773: aload_0
    //   774: astore_1
    //   775: aload #6
    //   777: astore_0
    //   778: goto -> 612
    //   781: astore_0
    //   782: goto -> 726
    // Exception table:
    //   from	to	target	type
    //   31	55	760	java/io/FileNotFoundException
    //   31	55	541	java/io/IOException
    //   31	55	529	finally
    //   55	62	760	java/io/FileNotFoundException
    //   55	62	541	java/io/IOException
    //   55	62	529	finally
    //   67	74	760	java/io/FileNotFoundException
    //   67	74	541	java/io/IOException
    //   67	74	529	finally
    //   77	86	760	java/io/FileNotFoundException
    //   77	86	541	java/io/IOException
    //   77	86	529	finally
    //   86	103	436	java/io/FileNotFoundException
    //   86	103	428	java/io/IOException
    //   86	103	419	finally
    //   103	110	399	java/io/FileNotFoundException
    //   103	110	382	java/io/IOException
    //   103	110	365	finally
    //   118	157	346	java/io/FileNotFoundException
    //   118	157	327	java/io/IOException
    //   118	157	312	finally
    //   157	171	284	java/io/FileNotFoundException
    //   157	171	265	java/io/IOException
    //   157	171	246	finally
    //   171	183	234	java/io/FileNotFoundException
    //   171	183	222	java/io/IOException
    //   171	183	214	finally
    //   188	197	234	java/io/FileNotFoundException
    //   188	197	222	java/io/IOException
    //   188	197	214	finally
    //   459	464	756	java/lang/Exception
    //   472	477	756	java/lang/Exception
    //   481	485	756	java/lang/Exception
    //   490	495	756	java/lang/Exception
    //   568	588	670	finally
    //   602	612	670	finally
    //   626	646	670	finally
    //   660	670	670	finally
    //   687	692	781	java/lang/Exception
    //   699	703	781	java/lang/Exception
    //   708	713	781	java/lang/Exception
    //   718	723	781	java/lang/Exception
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclien\\utils\FileUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */