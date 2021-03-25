package com.cndatacom.cnportalclient.http;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.security.cert.X509Certificate;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

public class HttpClient {
  private final int a = 300;
  
  private final int b = 301;
  
  private final int c = 302;
  
  private final int d = 303;
  
  private final int e = 307;
  
  private final int f = 308;
  
  private final int g = 30000;
  
  private final int h = 30000;
  
  private Result a(String paramString, Map<String, String> paramMap, byte[] paramArrayOfbyte, int paramInt) {
    return paramString.startsWith("https://") ? b(paramString, paramMap, paramArrayOfbyte, paramInt) : c(paramString, paramMap, paramArrayOfbyte, paramInt);
  }
  
  private String a(HttpURLConnection paramHttpURLConnection, String paramString1, String paramString2) {
    String str = paramHttpURLConnection.getHeaderField(paramString1);
    return (str == null) ? paramString2 : str.trim();
  }
  
  private Result b(String paramString, Map<String, String> paramMap, byte[] paramArrayOfbyte, int paramInt) {
    // Byte code:
    //   0: iload #4
    //   2: ifgt -> 23
    //   5: aconst_null
    //   6: astore #7
    //   8: iconst_0
    //   9: istore #4
    //   11: iconst_1
    //   12: istore #5
    //   14: aload_1
    //   15: astore #8
    //   17: aload #7
    //   19: astore_1
    //   20: goto -> 39
    //   23: aload_1
    //   24: astore #8
    //   26: aconst_null
    //   27: astore_1
    //   28: iconst_0
    //   29: istore #6
    //   31: iload #4
    //   33: istore #5
    //   35: iload #6
    //   37: istore #4
    //   39: iload #4
    //   41: iload #5
    //   43: if_icmpge -> 795
    //   46: aload_1
    //   47: astore #9
    //   49: aload #8
    //   51: invokestatic isEmpty : (Ljava/lang/CharSequence;)Z
    //   54: ifne -> 795
    //   57: aload_1
    //   58: astore #9
    //   60: new com/cndatacom/cnportalclient/http/HttpClient$Result
    //   63: dup
    //   64: aload_0
    //   65: invokespecial <init> : (Lcom/cndatacom/cnportalclient/http/HttpClient;)V
    //   68: astore #7
    //   70: ldc 'SSL'
    //   72: invokestatic getInstance : (Ljava/lang/String;)Ljavax/net/ssl/SSLContext;
    //   75: astore_1
    //   76: new com/cndatacom/cnportalclient/http/HttpClient$IgnoreSSLTrustManager
    //   79: dup
    //   80: invokespecial <init> : ()V
    //   83: astore #9
    //   85: new java/security/SecureRandom
    //   88: dup
    //   89: invokespecial <init> : ()V
    //   92: astore #10
    //   94: aload_1
    //   95: aconst_null
    //   96: iconst_1
    //   97: anewarray javax/net/ssl/TrustManager
    //   100: dup
    //   101: iconst_0
    //   102: aload #9
    //   104: aastore
    //   105: aload #10
    //   107: invokevirtual init : ([Ljavax/net/ssl/KeyManager;[Ljavax/net/ssl/TrustManager;Ljava/security/SecureRandom;)V
    //   110: aload_1
    //   111: invokevirtual getSocketFactory : ()Ljavax/net/ssl/SSLSocketFactory;
    //   114: astore_1
    //   115: new java/net/URL
    //   118: dup
    //   119: aload #8
    //   121: invokespecial <init> : (Ljava/lang/String;)V
    //   124: invokevirtual openConnection : ()Ljava/net/URLConnection;
    //   127: checkcast javax/net/ssl/HttpsURLConnection
    //   130: astore #9
    //   132: new java/lang/StringBuilder
    //   135: dup
    //   136: invokespecial <init> : ()V
    //   139: astore #10
    //   141: aload #10
    //   143: ldc 'HttpsClient REQ => '
    //   145: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   148: pop
    //   149: aload #10
    //   151: aload #8
    //   153: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   156: pop
    //   157: iconst_1
    //   158: anewarray java/lang/String
    //   161: dup
    //   162: iconst_0
    //   163: aload #10
    //   165: invokevirtual toString : ()Ljava/lang/String;
    //   168: aastore
    //   169: invokestatic i : ([Ljava/lang/String;)V
    //   172: aload #9
    //   174: sipush #30000
    //   177: invokevirtual setConnectTimeout : (I)V
    //   180: aload #9
    //   182: sipush #30000
    //   185: invokevirtual setReadTimeout : (I)V
    //   188: aload #9
    //   190: iconst_0
    //   191: invokevirtual setInstanceFollowRedirects : (Z)V
    //   194: aload #9
    //   196: iconst_0
    //   197: invokevirtual setUseCaches : (Z)V
    //   200: aload #9
    //   202: iconst_0
    //   203: invokevirtual setAllowUserInteraction : (Z)V
    //   206: aload #9
    //   208: ldc 'Connection'
    //   210: ldc 'Keep-Alive'
    //   212: invokevirtual setRequestProperty : (Ljava/lang/String;Ljava/lang/String;)V
    //   215: aload #9
    //   217: ldc 'Charset'
    //   219: ldc 'UTF-8'
    //   221: invokevirtual setRequestProperty : (Ljava/lang/String;Ljava/lang/String;)V
    //   224: aload #9
    //   226: ldc 'accept'
    //   228: ldc '*/*'
    //   230: invokevirtual setRequestProperty : (Ljava/lang/String;Ljava/lang/String;)V
    //   233: aload #9
    //   235: new com/cndatacom/cnportalclient/http/HttpClient$1
    //   238: dup
    //   239: aload_0
    //   240: invokespecial <init> : (Lcom/cndatacom/cnportalclient/http/HttpClient;)V
    //   243: invokevirtual setHostnameVerifier : (Ljavax/net/ssl/HostnameVerifier;)V
    //   246: aload #9
    //   248: aload_1
    //   249: invokevirtual setSSLSocketFactory : (Ljavax/net/ssl/SSLSocketFactory;)V
    //   252: aload_2
    //   253: ifnull -> 318
    //   256: aload_2
    //   257: invokeinterface isEmpty : ()Z
    //   262: ifne -> 318
    //   265: aload_2
    //   266: invokeinterface keySet : ()Ljava/util/Set;
    //   271: invokeinterface iterator : ()Ljava/util/Iterator;
    //   276: astore_1
    //   277: aload_1
    //   278: invokeinterface hasNext : ()Z
    //   283: ifeq -> 318
    //   286: aload_1
    //   287: invokeinterface next : ()Ljava/lang/Object;
    //   292: checkcast java/lang/String
    //   295: astore #8
    //   297: aload #9
    //   299: aload #8
    //   301: aload_2
    //   302: aload #8
    //   304: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   309: checkcast java/lang/String
    //   312: invokevirtual setRequestProperty : (Ljava/lang/String;Ljava/lang/String;)V
    //   315: goto -> 277
    //   318: aload_3
    //   319: ifnull -> 408
    //   322: aload #9
    //   324: ldc 'POST'
    //   326: invokevirtual setRequestMethod : (Ljava/lang/String;)V
    //   329: aload #9
    //   331: iconst_1
    //   332: invokevirtual setDoOutput : (Z)V
    //   335: aload #9
    //   337: ldc 'Content-Length'
    //   339: aload_3
    //   340: arraylength
    //   341: invokestatic valueOf : (I)Ljava/lang/String;
    //   344: invokevirtual setRequestProperty : (Ljava/lang/String;Ljava/lang/String;)V
    //   347: new java/lang/StringBuilder
    //   350: dup
    //   351: invokespecial <init> : ()V
    //   354: astore_1
    //   355: aload_1
    //   356: ldc 'Content-Length: '
    //   358: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   361: pop
    //   362: aload_1
    //   363: aload_3
    //   364: arraylength
    //   365: invokestatic valueOf : (I)Ljava/lang/String;
    //   368: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   371: pop
    //   372: iconst_1
    //   373: anewarray java/lang/String
    //   376: dup
    //   377: iconst_0
    //   378: aload_1
    //   379: invokevirtual toString : ()Ljava/lang/String;
    //   382: aastore
    //   383: invokestatic i : ([Ljava/lang/String;)V
    //   386: aload #9
    //   388: invokevirtual getOutputStream : ()Ljava/io/OutputStream;
    //   391: astore_1
    //   392: aload_1
    //   393: aload_3
    //   394: invokevirtual write : ([B)V
    //   397: aload_1
    //   398: invokevirtual flush : ()V
    //   401: aload_1
    //   402: invokevirtual close : ()V
    //   405: goto -> 415
    //   408: aload #9
    //   410: ldc 'GET'
    //   412: invokevirtual setRequestMethod : (Ljava/lang/String;)V
    //   415: aload #7
    //   417: aload #9
    //   419: invokevirtual getResponseCode : ()I
    //   422: invokestatic a : (Lcom/cndatacom/cnportalclient/http/HttpClient$Result;I)I
    //   425: pop
    //   426: new java/lang/StringBuilder
    //   429: dup
    //   430: invokespecial <init> : ()V
    //   433: astore_1
    //   434: aload_1
    //   435: ldc 'ResponseCode: '
    //   437: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   440: pop
    //   441: aload_1
    //   442: aload #7
    //   444: invokestatic a : (Lcom/cndatacom/cnportalclient/http/HttpClient$Result;)I
    //   447: invokestatic valueOf : (I)Ljava/lang/String;
    //   450: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   453: pop
    //   454: iconst_1
    //   455: anewarray java/lang/String
    //   458: dup
    //   459: iconst_0
    //   460: aload_1
    //   461: invokevirtual toString : ()Ljava/lang/String;
    //   464: aastore
    //   465: invokestatic i : ([Ljava/lang/String;)V
    //   468: aload #7
    //   470: invokevirtual isRedirect : ()Z
    //   473: ifeq -> 504
    //   476: aload_0
    //   477: aload #9
    //   479: ldc 'Location'
    //   481: aconst_null
    //   482: invokespecial a : (Ljava/net/HttpURLConnection;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   485: astore #8
    //   487: aload #9
    //   489: invokevirtual disconnect : ()V
    //   492: iload #4
    //   494: iconst_1
    //   495: iadd
    //   496: istore #4
    //   498: aload #7
    //   500: astore_1
    //   501: goto -> 39
    //   504: aload #7
    //   506: invokevirtual isSuccessful : ()Z
    //   509: ifeq -> 521
    //   512: aload #9
    //   514: invokevirtual getInputStream : ()Ljava/io/InputStream;
    //   517: astore_1
    //   518: goto -> 527
    //   521: aload #9
    //   523: invokevirtual getErrorStream : ()Ljava/io/InputStream;
    //   526: astore_1
    //   527: aload_1
    //   528: ifnull -> 587
    //   531: new java/io/ByteArrayOutputStream
    //   534: dup
    //   535: invokespecial <init> : ()V
    //   538: astore_2
    //   539: sipush #1024
    //   542: newarray byte
    //   544: astore_3
    //   545: aload_1
    //   546: aload_3
    //   547: invokevirtual read : ([B)I
    //   550: istore #4
    //   552: iload #4
    //   554: iconst_m1
    //   555: if_icmpeq -> 569
    //   558: aload_2
    //   559: aload_3
    //   560: iconst_0
    //   561: iload #4
    //   563: invokevirtual write : ([BII)V
    //   566: goto -> 545
    //   569: aload_2
    //   570: invokevirtual close : ()V
    //   573: aload_1
    //   574: invokevirtual close : ()V
    //   577: aload #7
    //   579: aload_2
    //   580: invokevirtual toByteArray : ()[B
    //   583: invokestatic a : (Lcom/cndatacom/cnportalclient/http/HttpClient$Result;[B)[B
    //   586: pop
    //   587: aload #7
    //   589: invokevirtual isFailCode : ()Z
    //   592: ifeq -> 653
    //   595: new java/lang/StringBuilder
    //   598: dup
    //   599: invokespecial <init> : ()V
    //   602: astore_1
    //   603: aload_1
    //   604: ldc_w 'HttpsClient httpcode = '
    //   607: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   610: pop
    //   611: aload_1
    //   612: aload #7
    //   614: invokevirtual isFailCode : ()Z
    //   617: invokevirtual append : (Z)Ljava/lang/StringBuilder;
    //   620: pop
    //   621: aload_1
    //   622: ldc_w ' error msg = '
    //   625: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   628: pop
    //   629: aload_1
    //   630: aload #7
    //   632: invokevirtual getDataString : ()Ljava/lang/String;
    //   635: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   638: pop
    //   639: iconst_1
    //   640: anewarray java/lang/String
    //   643: dup
    //   644: iconst_0
    //   645: aload_1
    //   646: invokevirtual toString : ()Ljava/lang/String;
    //   649: aastore
    //   650: invokestatic i : ([Ljava/lang/String;)V
    //   653: aload #7
    //   655: astore_1
    //   656: aload #9
    //   658: astore_3
    //   659: goto -> 797
    //   662: astore_2
    //   663: aload #9
    //   665: astore_3
    //   666: goto -> 676
    //   669: astore_1
    //   670: goto -> 779
    //   673: astore_2
    //   674: aconst_null
    //   675: astore_3
    //   676: aload #7
    //   678: astore_1
    //   679: goto -> 693
    //   682: astore_1
    //   683: aload #9
    //   685: astore #7
    //   687: goto -> 779
    //   690: astore_2
    //   691: aconst_null
    //   692: astore_3
    //   693: aload_1
    //   694: astore #9
    //   696: aload_2
    //   697: invokevirtual printStackTrace : ()V
    //   700: aload_1
    //   701: astore #9
    //   703: new java/lang/StringBuilder
    //   706: dup
    //   707: invokespecial <init> : ()V
    //   710: astore #7
    //   712: aload_1
    //   713: astore #9
    //   715: aload #7
    //   717: ldc_w 'HttpsClient Exception: '
    //   720: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   723: pop
    //   724: aload_1
    //   725: astore #9
    //   727: aload #7
    //   729: aload_2
    //   730: invokevirtual getMessage : ()Ljava/lang/String;
    //   733: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   736: pop
    //   737: aload_1
    //   738: astore #9
    //   740: iconst_1
    //   741: anewarray java/lang/String
    //   744: dup
    //   745: iconst_0
    //   746: aload #7
    //   748: invokevirtual toString : ()Ljava/lang/String;
    //   751: aastore
    //   752: invokestatic e : ([Ljava/lang/String;)V
    //   755: aload_1
    //   756: astore_2
    //   757: aload_3
    //   758: astore #7
    //   760: aload_1
    //   761: ifnonnull -> 818
    //   764: new com/cndatacom/cnportalclient/http/HttpClient$Result
    //   767: dup
    //   768: aload_0
    //   769: invokespecial <init> : (Lcom/cndatacom/cnportalclient/http/HttpClient;)V
    //   772: astore_2
    //   773: aload_3
    //   774: astore #7
    //   776: goto -> 818
    //   779: aload #7
    //   781: ifnonnull -> 793
    //   784: new com/cndatacom/cnportalclient/http/HttpClient$Result
    //   787: dup
    //   788: aload_0
    //   789: invokespecial <init> : (Lcom/cndatacom/cnportalclient/http/HttpClient;)V
    //   792: pop
    //   793: aload_1
    //   794: athrow
    //   795: aconst_null
    //   796: astore_3
    //   797: aload_1
    //   798: astore_2
    //   799: aload_3
    //   800: astore #7
    //   802: aload_1
    //   803: ifnonnull -> 818
    //   806: new com/cndatacom/cnportalclient/http/HttpClient$Result
    //   809: dup
    //   810: aload_0
    //   811: invokespecial <init> : (Lcom/cndatacom/cnportalclient/http/HttpClient;)V
    //   814: astore_2
    //   815: aload_3
    //   816: astore #7
    //   818: aload #7
    //   820: ifnull -> 828
    //   823: aload #7
    //   825: invokevirtual disconnect : ()V
    //   828: aload_2
    //   829: areturn
    // Exception table:
    //   from	to	target	type
    //   49	57	690	java/lang/Exception
    //   49	57	682	finally
    //   60	70	690	java/lang/Exception
    //   60	70	682	finally
    //   70	132	673	java/lang/Exception
    //   70	132	669	finally
    //   132	252	662	java/lang/Exception
    //   132	252	669	finally
    //   256	277	662	java/lang/Exception
    //   256	277	669	finally
    //   277	315	662	java/lang/Exception
    //   277	315	669	finally
    //   322	405	662	java/lang/Exception
    //   322	405	669	finally
    //   408	415	662	java/lang/Exception
    //   408	415	669	finally
    //   415	492	662	java/lang/Exception
    //   415	492	669	finally
    //   504	518	662	java/lang/Exception
    //   504	518	669	finally
    //   521	527	662	java/lang/Exception
    //   521	527	669	finally
    //   531	545	662	java/lang/Exception
    //   531	545	669	finally
    //   545	552	662	java/lang/Exception
    //   545	552	669	finally
    //   558	566	662	java/lang/Exception
    //   558	566	669	finally
    //   569	587	662	java/lang/Exception
    //   569	587	669	finally
    //   587	653	662	java/lang/Exception
    //   587	653	669	finally
    //   696	700	682	finally
    //   703	712	682	finally
    //   715	724	682	finally
    //   727	737	682	finally
    //   740	755	682	finally
  }
  
  private Result c(String paramString, Map<String, String> paramMap, byte[] paramArrayOfbyte, int paramInt) {
    // Byte code:
    //   0: aconst_null
    //   1: astore #11
    //   3: aconst_null
    //   4: astore #13
    //   6: aconst_null
    //   7: astore #12
    //   9: iload #4
    //   11: ifgt -> 32
    //   14: aconst_null
    //   15: astore #7
    //   17: iconst_0
    //   18: istore #4
    //   20: iconst_1
    //   21: istore #5
    //   23: aload_1
    //   24: astore #9
    //   26: aload #7
    //   28: astore_1
    //   29: goto -> 48
    //   32: aload_1
    //   33: astore #9
    //   35: aconst_null
    //   36: astore_1
    //   37: iconst_0
    //   38: istore #6
    //   40: iload #4
    //   42: istore #5
    //   44: iload #6
    //   46: istore #4
    //   48: aload #13
    //   50: astore #7
    //   52: aload_1
    //   53: astore #10
    //   55: iload #4
    //   57: iload #5
    //   59: if_icmpge -> 755
    //   62: aload_1
    //   63: astore #8
    //   65: aload #13
    //   67: astore #7
    //   69: aload_1
    //   70: astore #10
    //   72: aload #9
    //   74: invokestatic isEmpty : (Ljava/lang/CharSequence;)Z
    //   77: ifne -> 755
    //   80: aload_1
    //   81: astore #8
    //   83: new com/cndatacom/cnportalclient/http/HttpClient$Result
    //   86: dup
    //   87: aload_0
    //   88: invokespecial <init> : (Lcom/cndatacom/cnportalclient/http/HttpClient;)V
    //   91: astore #7
    //   93: new java/net/URL
    //   96: dup
    //   97: aload #9
    //   99: invokespecial <init> : (Ljava/lang/String;)V
    //   102: invokevirtual openConnection : ()Ljava/net/URLConnection;
    //   105: checkcast java/net/HttpURLConnection
    //   108: astore_1
    //   109: new java/lang/StringBuilder
    //   112: dup
    //   113: invokespecial <init> : ()V
    //   116: astore #8
    //   118: aload #8
    //   120: ldc_w 'HttpClient REQ => '
    //   123: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   126: pop
    //   127: aload #8
    //   129: aload #9
    //   131: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   134: pop
    //   135: iconst_1
    //   136: anewarray java/lang/String
    //   139: dup
    //   140: iconst_0
    //   141: aload #8
    //   143: invokevirtual toString : ()Ljava/lang/String;
    //   146: aastore
    //   147: invokestatic i : ([Ljava/lang/String;)V
    //   150: aload_1
    //   151: sipush #30000
    //   154: invokevirtual setConnectTimeout : (I)V
    //   157: aload_1
    //   158: sipush #30000
    //   161: invokevirtual setReadTimeout : (I)V
    //   164: aload_1
    //   165: iconst_0
    //   166: invokevirtual setInstanceFollowRedirects : (Z)V
    //   169: aload_1
    //   170: iconst_0
    //   171: invokevirtual setUseCaches : (Z)V
    //   174: aload_1
    //   175: iconst_0
    //   176: invokevirtual setAllowUserInteraction : (Z)V
    //   179: aload_1
    //   180: ldc 'Connection'
    //   182: ldc 'Keep-Alive'
    //   184: invokevirtual setRequestProperty : (Ljava/lang/String;Ljava/lang/String;)V
    //   187: aload_1
    //   188: ldc 'Charset'
    //   190: ldc 'UTF-8'
    //   192: invokevirtual setRequestProperty : (Ljava/lang/String;Ljava/lang/String;)V
    //   195: aload_1
    //   196: ldc 'accept'
    //   198: ldc '*/*'
    //   200: invokevirtual setRequestProperty : (Ljava/lang/String;Ljava/lang/String;)V
    //   203: aload_2
    //   204: ifnull -> 271
    //   207: aload_2
    //   208: invokeinterface isEmpty : ()Z
    //   213: ifne -> 271
    //   216: aload_2
    //   217: invokeinterface keySet : ()Ljava/util/Set;
    //   222: invokeinterface iterator : ()Ljava/util/Iterator;
    //   227: astore #8
    //   229: aload #8
    //   231: invokeinterface hasNext : ()Z
    //   236: ifeq -> 271
    //   239: aload #8
    //   241: invokeinterface next : ()Ljava/lang/Object;
    //   246: checkcast java/lang/String
    //   249: astore #9
    //   251: aload_1
    //   252: aload #9
    //   254: aload_2
    //   255: aload #9
    //   257: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   262: checkcast java/lang/String
    //   265: invokevirtual setRequestProperty : (Ljava/lang/String;Ljava/lang/String;)V
    //   268: goto -> 229
    //   271: aload_3
    //   272: ifnull -> 365
    //   275: aload_1
    //   276: ldc 'POST'
    //   278: invokevirtual setRequestMethod : (Ljava/lang/String;)V
    //   281: aload_1
    //   282: iconst_1
    //   283: invokevirtual setDoOutput : (Z)V
    //   286: aload_1
    //   287: ldc 'Content-Length'
    //   289: aload_3
    //   290: arraylength
    //   291: invokestatic valueOf : (I)Ljava/lang/String;
    //   294: invokevirtual setRequestProperty : (Ljava/lang/String;Ljava/lang/String;)V
    //   297: new java/lang/StringBuilder
    //   300: dup
    //   301: invokespecial <init> : ()V
    //   304: astore #8
    //   306: aload #8
    //   308: ldc 'Content-Length: '
    //   310: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   313: pop
    //   314: aload #8
    //   316: aload_3
    //   317: arraylength
    //   318: invokestatic valueOf : (I)Ljava/lang/String;
    //   321: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   324: pop
    //   325: iconst_1
    //   326: anewarray java/lang/String
    //   329: dup
    //   330: iconst_0
    //   331: aload #8
    //   333: invokevirtual toString : ()Ljava/lang/String;
    //   336: aastore
    //   337: invokestatic i : ([Ljava/lang/String;)V
    //   340: aload_1
    //   341: invokevirtual getOutputStream : ()Ljava/io/OutputStream;
    //   344: astore #8
    //   346: aload #8
    //   348: aload_3
    //   349: invokevirtual write : ([B)V
    //   352: aload #8
    //   354: invokevirtual flush : ()V
    //   357: aload #8
    //   359: invokevirtual close : ()V
    //   362: goto -> 371
    //   365: aload_1
    //   366: ldc 'GET'
    //   368: invokevirtual setRequestMethod : (Ljava/lang/String;)V
    //   371: aload #7
    //   373: aload_1
    //   374: invokevirtual getResponseCode : ()I
    //   377: invokestatic a : (Lcom/cndatacom/cnportalclient/http/HttpClient$Result;I)I
    //   380: pop
    //   381: new java/lang/StringBuilder
    //   384: dup
    //   385: invokespecial <init> : ()V
    //   388: astore #8
    //   390: aload #8
    //   392: ldc 'ResponseCode: '
    //   394: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   397: pop
    //   398: aload #8
    //   400: aload #7
    //   402: invokestatic a : (Lcom/cndatacom/cnportalclient/http/HttpClient$Result;)I
    //   405: invokestatic valueOf : (I)Ljava/lang/String;
    //   408: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   411: pop
    //   412: iconst_1
    //   413: anewarray java/lang/String
    //   416: dup
    //   417: iconst_0
    //   418: aload #8
    //   420: invokevirtual toString : ()Ljava/lang/String;
    //   423: aastore
    //   424: invokestatic i : ([Ljava/lang/String;)V
    //   427: aload #7
    //   429: invokevirtual isRedirect : ()Z
    //   432: ifeq -> 461
    //   435: aload_0
    //   436: aload_1
    //   437: ldc 'Location'
    //   439: aconst_null
    //   440: invokespecial a : (Ljava/net/HttpURLConnection;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   443: astore #9
    //   445: aload_1
    //   446: invokevirtual disconnect : ()V
    //   449: iload #4
    //   451: iconst_1
    //   452: iadd
    //   453: istore #4
    //   455: aload #7
    //   457: astore_1
    //   458: goto -> 48
    //   461: aload #7
    //   463: invokevirtual isSuccessful : ()Z
    //   466: ifeq -> 477
    //   469: aload_1
    //   470: invokevirtual getInputStream : ()Ljava/io/InputStream;
    //   473: astore_2
    //   474: goto -> 482
    //   477: aload_1
    //   478: invokevirtual getErrorStream : ()Ljava/io/InputStream;
    //   481: astore_2
    //   482: aload_2
    //   483: ifnull -> 545
    //   486: new java/io/ByteArrayOutputStream
    //   489: dup
    //   490: invokespecial <init> : ()V
    //   493: astore_3
    //   494: sipush #1024
    //   497: newarray byte
    //   499: astore #8
    //   501: aload_2
    //   502: aload #8
    //   504: invokevirtual read : ([B)I
    //   507: istore #5
    //   509: iload #5
    //   511: iconst_m1
    //   512: if_icmpeq -> 527
    //   515: aload_3
    //   516: aload #8
    //   518: iconst_0
    //   519: iload #5
    //   521: invokevirtual write : ([BII)V
    //   524: goto -> 501
    //   527: aload_3
    //   528: invokevirtual close : ()V
    //   531: aload_2
    //   532: invokevirtual close : ()V
    //   535: aload #7
    //   537: aload_3
    //   538: invokevirtual toByteArray : ()[B
    //   541: invokestatic a : (Lcom/cndatacom/cnportalclient/http/HttpClient$Result;[B)[B
    //   544: pop
    //   545: aload #7
    //   547: invokevirtual isFailCode : ()Z
    //   550: ifeq -> 611
    //   553: new java/lang/StringBuilder
    //   556: dup
    //   557: invokespecial <init> : ()V
    //   560: astore_2
    //   561: aload_2
    //   562: ldc_w 'HttpClient httpcode = '
    //   565: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   568: pop
    //   569: aload_2
    //   570: aload #7
    //   572: invokevirtual isFailCode : ()Z
    //   575: invokevirtual append : (Z)Ljava/lang/StringBuilder;
    //   578: pop
    //   579: aload_2
    //   580: ldc_w ' error msg = '
    //   583: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   586: pop
    //   587: aload_2
    //   588: aload #7
    //   590: invokevirtual getDataString : ()Ljava/lang/String;
    //   593: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   596: pop
    //   597: iconst_1
    //   598: anewarray java/lang/String
    //   601: dup
    //   602: iconst_0
    //   603: aload_2
    //   604: invokevirtual toString : ()Ljava/lang/String;
    //   607: aastore
    //   608: invokestatic e : ([Ljava/lang/String;)V
    //   611: aload #7
    //   613: astore #10
    //   615: aload_1
    //   616: astore #7
    //   618: goto -> 755
    //   621: astore_2
    //   622: aload_1
    //   623: astore_3
    //   624: goto -> 635
    //   627: astore_1
    //   628: goto -> 739
    //   631: astore_2
    //   632: aload #12
    //   634: astore_3
    //   635: aload #7
    //   637: astore_1
    //   638: goto -> 653
    //   641: astore_1
    //   642: aload #8
    //   644: astore #7
    //   646: goto -> 739
    //   649: astore_2
    //   650: aload #11
    //   652: astore_3
    //   653: aload_1
    //   654: astore #8
    //   656: aload_2
    //   657: invokevirtual printStackTrace : ()V
    //   660: aload_1
    //   661: astore #8
    //   663: new java/lang/StringBuilder
    //   666: dup
    //   667: invokespecial <init> : ()V
    //   670: astore #7
    //   672: aload_1
    //   673: astore #8
    //   675: aload #7
    //   677: ldc_w 'HttpClient Exception: '
    //   680: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   683: pop
    //   684: aload_1
    //   685: astore #8
    //   687: aload #7
    //   689: aload_2
    //   690: invokevirtual getMessage : ()Ljava/lang/String;
    //   693: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   696: pop
    //   697: aload_1
    //   698: astore #8
    //   700: iconst_1
    //   701: anewarray java/lang/String
    //   704: dup
    //   705: iconst_0
    //   706: aload #7
    //   708: invokevirtual toString : ()Ljava/lang/String;
    //   711: aastore
    //   712: invokestatic e : ([Ljava/lang/String;)V
    //   715: aload_3
    //   716: astore #8
    //   718: aload_1
    //   719: astore_2
    //   720: aload_1
    //   721: ifnonnull -> 780
    //   724: new com/cndatacom/cnportalclient/http/HttpClient$Result
    //   727: dup
    //   728: aload_0
    //   729: invokespecial <init> : (Lcom/cndatacom/cnportalclient/http/HttpClient;)V
    //   732: astore_2
    //   733: aload_3
    //   734: astore #8
    //   736: goto -> 780
    //   739: aload #7
    //   741: ifnonnull -> 753
    //   744: new com/cndatacom/cnportalclient/http/HttpClient$Result
    //   747: dup
    //   748: aload_0
    //   749: invokespecial <init> : (Lcom/cndatacom/cnportalclient/http/HttpClient;)V
    //   752: pop
    //   753: aload_1
    //   754: athrow
    //   755: aload #7
    //   757: astore #8
    //   759: aload #10
    //   761: astore_2
    //   762: aload #10
    //   764: ifnonnull -> 780
    //   767: new com/cndatacom/cnportalclient/http/HttpClient$Result
    //   770: dup
    //   771: aload_0
    //   772: invokespecial <init> : (Lcom/cndatacom/cnportalclient/http/HttpClient;)V
    //   775: astore_2
    //   776: aload #7
    //   778: astore #8
    //   780: aload #8
    //   782: ifnull -> 790
    //   785: aload #8
    //   787: invokevirtual disconnect : ()V
    //   790: aload_2
    //   791: iload #4
    //   793: invokestatic b : (Lcom/cndatacom/cnportalclient/http/HttpClient$Result;I)I
    //   796: pop
    //   797: aload_2
    //   798: areturn
    // Exception table:
    //   from	to	target	type
    //   72	80	649	java/lang/Exception
    //   72	80	641	finally
    //   83	93	649	java/lang/Exception
    //   83	93	641	finally
    //   93	109	631	java/lang/Exception
    //   93	109	627	finally
    //   109	203	621	java/lang/Exception
    //   109	203	627	finally
    //   207	229	621	java/lang/Exception
    //   207	229	627	finally
    //   229	268	621	java/lang/Exception
    //   229	268	627	finally
    //   275	362	621	java/lang/Exception
    //   275	362	627	finally
    //   365	371	621	java/lang/Exception
    //   365	371	627	finally
    //   371	449	621	java/lang/Exception
    //   371	449	627	finally
    //   461	474	621	java/lang/Exception
    //   461	474	627	finally
    //   477	482	621	java/lang/Exception
    //   477	482	627	finally
    //   486	501	621	java/lang/Exception
    //   486	501	627	finally
    //   501	509	621	java/lang/Exception
    //   501	509	627	finally
    //   515	524	621	java/lang/Exception
    //   515	524	627	finally
    //   527	545	621	java/lang/Exception
    //   527	545	627	finally
    //   545	611	621	java/lang/Exception
    //   545	611	627	finally
    //   656	660	641	finally
    //   663	672	641	finally
    //   675	684	641	finally
    //   687	697	641	finally
    //   700	715	641	finally
  }
  
  public Result get(String paramString) {
    return a(paramString, null, null, 20);
  }
  
  public Result get(String paramString, int paramInt) {
    return a(paramString, null, null, paramInt);
  }
  
  public Result get(String paramString, Map<String, String> paramMap) {
    return a(paramString, paramMap, null, 20);
  }
  
  public Result get(String paramString, Map<String, String> paramMap, int paramInt) {
    return a(paramString, paramMap, null, paramInt);
  }
  
  public Result get(String paramString, Map<String, String> paramMap1, Map<String, String> paramMap2) {
    return get(paramString, paramMap1, paramMap2, 20);
  }
  
  public Result get(String paramString, Map<String, String> paramMap1, Map<String, String> paramMap2, int paramInt) {
    StringBuilder stringBuilder = new StringBuilder();
    if (paramMap2 != null && !paramMap2.isEmpty())
      for (String str : paramMap2.keySet()) {
        if (stringBuilder.length() != 0)
          stringBuilder.append("&"); 
        try {
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append(str);
          stringBuilder1.append("=");
          StringBuilder stringBuilder2 = new StringBuilder();
          stringBuilder2.append(paramMap2.get(str));
          stringBuilder2.append("");
          stringBuilder1.append(URLEncoder.encode(stringBuilder2.toString(), "utf-8"));
          stringBuilder.append(stringBuilder1.toString());
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
          unsupportedEncodingException.printStackTrace();
        } 
      }  
    if (paramString.contains("?")) {
      paramString = String.format("%s&%s", new Object[] { paramString, stringBuilder.toString() });
    } else {
      paramString = String.format("%s?%s", new Object[] { paramString, stringBuilder.toString() });
    } 
    return a(paramString, paramMap1, null, paramInt);
  }
  
  public Result post(String paramString, Map<String, String> paramMap) {
    return post(paramString, paramMap, "".getBytes(), 20);
  }
  
  public Result post(String paramString, Map<String, String> paramMap, byte[] paramArrayOfbyte) {
    byte[] arrayOfByte = paramArrayOfbyte;
    if (paramArrayOfbyte == null)
      arrayOfByte = "".getBytes(); 
    return a(paramString, paramMap, arrayOfByte, 20);
  }
  
  public Result post(String paramString, Map<String, String> paramMap, byte[] paramArrayOfbyte, int paramInt) {
    byte[] arrayOfByte = paramArrayOfbyte;
    if (paramArrayOfbyte == null)
      arrayOfByte = "".getBytes(); 
    return a(paramString, paramMap, arrayOfByte, paramInt);
  }
  
  public Result post(String paramString, byte[] paramArrayOfbyte) {
    byte[] arrayOfByte = paramArrayOfbyte;
    if (paramArrayOfbyte == null)
      arrayOfByte = "".getBytes(); 
    return a(paramString, null, arrayOfByte, 20);
  }
  
  public Result post(String paramString, byte[] paramArrayOfbyte, int paramInt) {
    byte[] arrayOfByte = paramArrayOfbyte;
    if (paramArrayOfbyte == null)
      arrayOfByte = "".getBytes(); 
    return a(paramString, null, arrayOfByte, paramInt);
  }
  
  public Result uploadFile(String paramString, Map<String, String> paramMap) {
    return uploadFile(paramString, null, paramMap);
  }
  
  public Result uploadFile(String paramString, Map<String, String> paramMap1, Map<String, String> paramMap2) {
    // Byte code:
    //   0: new com/cndatacom/cnportalclient/http/HttpClient$Result
    //   3: dup
    //   4: aload_0
    //   5: invokespecial <init> : (Lcom/cndatacom/cnportalclient/http/HttpClient;)V
    //   8: astore #8
    //   10: new java/io/ByteArrayOutputStream
    //   13: dup
    //   14: invokespecial <init> : ()V
    //   17: astore #7
    //   19: new java/net/URL
    //   22: dup
    //   23: aload_1
    //   24: invokespecial <init> : (Ljava/lang/String;)V
    //   27: invokevirtual openConnection : ()Ljava/net/URLConnection;
    //   30: checkcast java/net/HttpURLConnection
    //   33: astore #5
    //   35: aload #5
    //   37: iconst_1
    //   38: invokevirtual setDoInput : (Z)V
    //   41: aload #5
    //   43: iconst_1
    //   44: invokevirtual setDoOutput : (Z)V
    //   47: aload #5
    //   49: ldc 'POST'
    //   51: invokevirtual setRequestMethod : (Ljava/lang/String;)V
    //   54: aload #5
    //   56: iconst_0
    //   57: invokevirtual setUseCaches : (Z)V
    //   60: aload #5
    //   62: sipush #30000
    //   65: invokevirtual setConnectTimeout : (I)V
    //   68: aload #5
    //   70: sipush #30000
    //   73: invokevirtual setReadTimeout : (I)V
    //   76: aload #5
    //   78: iconst_0
    //   79: invokevirtual setInstanceFollowRedirects : (Z)V
    //   82: aload #5
    //   84: iconst_0
    //   85: invokevirtual setAllowUserInteraction : (Z)V
    //   88: aload #5
    //   90: ldc 'Connection'
    //   92: ldc 'Keep-Alive'
    //   94: invokevirtual setRequestProperty : (Ljava/lang/String;Ljava/lang/String;)V
    //   97: aload #5
    //   99: ldc_w 'Accept'
    //   102: ldc '*/*'
    //   104: invokevirtual setRequestProperty : (Ljava/lang/String;Ljava/lang/String;)V
    //   107: aload #5
    //   109: ldc_w 'Accept-Encoding'
    //   112: ldc_w 'gzip, deflate'
    //   115: invokevirtual setRequestProperty : (Ljava/lang/String;Ljava/lang/String;)V
    //   118: aload #5
    //   120: ldc_w 'Cache-Control'
    //   123: ldc_w 'no-cache'
    //   126: invokevirtual setRequestProperty : (Ljava/lang/String;Ljava/lang/String;)V
    //   129: aload #5
    //   131: ldc_w 'Content-Type'
    //   134: ldc_w 'multipart/form-data; boundary=###--###'
    //   137: invokevirtual setRequestProperty : (Ljava/lang/String;Ljava/lang/String;)V
    //   140: aload #5
    //   142: ldc_w 'User-Agent'
    //   145: ldc_w 'Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30)'
    //   148: invokevirtual setRequestProperty : (Ljava/lang/String;Ljava/lang/String;)V
    //   151: aload #5
    //   153: invokevirtual connect : ()V
    //   156: new java/io/DataOutputStream
    //   159: dup
    //   160: aload #5
    //   162: invokevirtual getOutputStream : ()Ljava/io/OutputStream;
    //   165: invokespecial <init> : (Ljava/io/OutputStream;)V
    //   168: astore #6
    //   170: aload_2
    //   171: ifnull -> 333
    //   174: aload_2
    //   175: invokeinterface isEmpty : ()Z
    //   180: ifne -> 333
    //   183: aload_2
    //   184: invokeinterface entrySet : ()Ljava/util/Set;
    //   189: invokeinterface iterator : ()Ljava/util/Iterator;
    //   194: astore_1
    //   195: aload_1
    //   196: invokeinterface hasNext : ()Z
    //   201: ifeq -> 333
    //   204: aload_1
    //   205: invokeinterface next : ()Ljava/lang/Object;
    //   210: checkcast java/util/Map$Entry
    //   213: invokeinterface getKey : ()Ljava/lang/Object;
    //   218: checkcast java/lang/String
    //   221: astore #9
    //   223: aload_2
    //   224: aload #9
    //   226: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   231: checkcast java/lang/String
    //   234: astore #10
    //   236: aload #6
    //   238: ldc_w '--###--###\\r\\n'
    //   241: invokevirtual writeBytes : (Ljava/lang/String;)V
    //   244: new java/lang/StringBuilder
    //   247: dup
    //   248: invokespecial <init> : ()V
    //   251: astore #11
    //   253: aload #11
    //   255: ldc_w 'Content-Disposition: form-data; name="'
    //   258: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   261: pop
    //   262: aload #11
    //   264: aload #9
    //   266: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   269: pop
    //   270: aload #11
    //   272: ldc_w '"'
    //   275: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   278: pop
    //   279: aload #11
    //   281: ldc_w '\\r\\n'
    //   284: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   287: pop
    //   288: aload #6
    //   290: aload #11
    //   292: invokevirtual toString : ()Ljava/lang/String;
    //   295: invokevirtual writeBytes : (Ljava/lang/String;)V
    //   298: aload #6
    //   300: ldc_w '\\r\\n'
    //   303: invokevirtual writeBytes : (Ljava/lang/String;)V
    //   306: aload #6
    //   308: aload #10
    //   310: invokevirtual toString : ()Ljava/lang/String;
    //   313: ldc_w 'utf-8'
    //   316: invokestatic encode : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   319: invokevirtual writeBytes : (Ljava/lang/String;)V
    //   322: aload #6
    //   324: ldc_w '\\r\\n'
    //   327: invokevirtual writeBytes : (Ljava/lang/String;)V
    //   330: goto -> 195
    //   333: aload_3
    //   334: ifnull -> 643
    //   337: aload_3
    //   338: invokeinterface isEmpty : ()Z
    //   343: ifne -> 643
    //   346: aload_3
    //   347: invokeinterface entrySet : ()Ljava/util/Set;
    //   352: invokeinterface iterator : ()Ljava/util/Iterator;
    //   357: astore_1
    //   358: aload_1
    //   359: invokeinterface hasNext : ()Z
    //   364: ifeq -> 643
    //   367: aload_1
    //   368: invokeinterface next : ()Ljava/lang/Object;
    //   373: checkcast java/util/Map$Entry
    //   376: astore_3
    //   377: aload_3
    //   378: invokeinterface getKey : ()Ljava/lang/Object;
    //   383: checkcast java/lang/String
    //   386: astore_2
    //   387: aload_3
    //   388: invokeinterface getValue : ()Ljava/lang/Object;
    //   393: checkcast java/lang/String
    //   396: astore_3
    //   397: aload_3
    //   398: ifnonnull -> 404
    //   401: goto -> 358
    //   404: new java/io/File
    //   407: dup
    //   408: aload_3
    //   409: invokespecial <init> : (Ljava/lang/String;)V
    //   412: astore_3
    //   413: aload_3
    //   414: invokevirtual getName : ()Ljava/lang/String;
    //   417: astore #11
    //   419: aload_3
    //   420: invokevirtual getName : ()Ljava/lang/String;
    //   423: invokestatic getMimeType : (Ljava/lang/String;)Ljava/lang/String;
    //   426: astore #9
    //   428: new java/lang/StringBuffer
    //   431: dup
    //   432: invokespecial <init> : ()V
    //   435: astore #10
    //   437: aload #10
    //   439: ldc_w '\\r\\n'
    //   442: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   445: pop
    //   446: aload #10
    //   448: ldc_w '--'
    //   451: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   454: pop
    //   455: aload #10
    //   457: ldc_w '###--###'
    //   460: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   463: pop
    //   464: aload #10
    //   466: ldc_w '\\r\\n'
    //   469: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   472: pop
    //   473: new java/lang/StringBuilder
    //   476: dup
    //   477: invokespecial <init> : ()V
    //   480: astore #12
    //   482: aload #12
    //   484: ldc_w 'Content-Disposition: form-data; name="'
    //   487: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   490: pop
    //   491: aload #12
    //   493: aload_2
    //   494: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   497: pop
    //   498: aload #12
    //   500: ldc_w '"; filename="'
    //   503: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   506: pop
    //   507: aload #12
    //   509: aload #11
    //   511: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   514: pop
    //   515: aload #12
    //   517: ldc_w '"\\r\\n'
    //   520: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   523: pop
    //   524: aload #10
    //   526: aload #12
    //   528: invokevirtual toString : ()Ljava/lang/String;
    //   531: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   534: pop
    //   535: new java/lang/StringBuilder
    //   538: dup
    //   539: invokespecial <init> : ()V
    //   542: astore_2
    //   543: aload_2
    //   544: ldc_w 'Content-Type:'
    //   547: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   550: pop
    //   551: aload_2
    //   552: aload #9
    //   554: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   557: pop
    //   558: aload_2
    //   559: ldc_w '\\r\\n\\r\\n'
    //   562: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   565: pop
    //   566: aload #10
    //   568: aload_2
    //   569: invokevirtual toString : ()Ljava/lang/String;
    //   572: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   575: pop
    //   576: aload #6
    //   578: aload #10
    //   580: invokevirtual toString : ()Ljava/lang/String;
    //   583: invokevirtual getBytes : ()[B
    //   586: invokevirtual write : ([B)V
    //   589: new java/io/DataInputStream
    //   592: dup
    //   593: new java/io/FileInputStream
    //   596: dup
    //   597: aload_3
    //   598: invokespecial <init> : (Ljava/io/File;)V
    //   601: invokespecial <init> : (Ljava/io/InputStream;)V
    //   604: astore_2
    //   605: sipush #1024
    //   608: newarray byte
    //   610: astore_3
    //   611: aload_2
    //   612: aload_3
    //   613: invokevirtual read : ([B)I
    //   616: istore #4
    //   618: iload #4
    //   620: iconst_m1
    //   621: if_icmpeq -> 636
    //   624: aload #6
    //   626: aload_3
    //   627: iconst_0
    //   628: iload #4
    //   630: invokevirtual write : ([BII)V
    //   633: goto -> 611
    //   636: aload_2
    //   637: invokevirtual close : ()V
    //   640: goto -> 358
    //   643: aload #6
    //   645: ldc_w '--###--###--\\r\\n'
    //   648: invokevirtual writeBytes : (Ljava/lang/String;)V
    //   651: aload #6
    //   653: invokevirtual flush : ()V
    //   656: sipush #1024
    //   659: newarray byte
    //   661: astore_1
    //   662: aload #8
    //   664: aload #5
    //   666: invokevirtual getResponseCode : ()I
    //   669: invokestatic a : (Lcom/cndatacom/cnportalclient/http/HttpClient$Result;I)I
    //   672: pop
    //   673: aload #8
    //   675: invokevirtual isSuccessful : ()Z
    //   678: ifeq -> 697
    //   681: new java/io/BufferedInputStream
    //   684: dup
    //   685: aload #5
    //   687: invokevirtual getInputStream : ()Ljava/io/InputStream;
    //   690: invokespecial <init> : (Ljava/io/InputStream;)V
    //   693: astore_2
    //   694: goto -> 710
    //   697: new java/io/BufferedInputStream
    //   700: dup
    //   701: aload #5
    //   703: invokevirtual getErrorStream : ()Ljava/io/InputStream;
    //   706: invokespecial <init> : (Ljava/io/InputStream;)V
    //   709: astore_2
    //   710: aload_2
    //   711: ifnull -> 771
    //   714: aload_2
    //   715: aload_1
    //   716: invokevirtual read : ([B)I
    //   719: istore #4
    //   721: iload #4
    //   723: iconst_m1
    //   724: if_icmpeq -> 744
    //   727: aload #7
    //   729: aload_1
    //   730: iconst_0
    //   731: iload #4
    //   733: invokevirtual write : ([BII)V
    //   736: aload #7
    //   738: invokevirtual flush : ()V
    //   741: goto -> 714
    //   744: aload #8
    //   746: aload #7
    //   748: invokevirtual toByteArray : ()[B
    //   751: invokestatic a : (Lcom/cndatacom/cnportalclient/http/HttpClient$Result;[B)[B
    //   754: pop
    //   755: goto -> 771
    //   758: astore_1
    //   759: goto -> 948
    //   762: astore_3
    //   763: aload_2
    //   764: astore_1
    //   765: aload #6
    //   767: astore_2
    //   768: goto -> 844
    //   771: aload #6
    //   773: ifnull -> 781
    //   776: aload #6
    //   778: invokevirtual close : ()V
    //   781: aload_2
    //   782: ifnull -> 789
    //   785: aload_2
    //   786: invokevirtual close : ()V
    //   789: aload #7
    //   791: ifnull -> 799
    //   794: aload #7
    //   796: invokevirtual close : ()V
    //   799: aload #5
    //   801: ifnull -> 937
    //   804: aload #5
    //   806: invokevirtual disconnect : ()V
    //   809: aload #8
    //   811: areturn
    //   812: astore_1
    //   813: goto -> 828
    //   816: astore_3
    //   817: aconst_null
    //   818: astore_1
    //   819: aconst_null
    //   820: astore_2
    //   821: goto -> 844
    //   824: astore_1
    //   825: aconst_null
    //   826: astore #5
    //   828: aconst_null
    //   829: astore_2
    //   830: aconst_null
    //   831: astore #6
    //   833: goto -> 948
    //   836: astore_3
    //   837: aconst_null
    //   838: astore_1
    //   839: aconst_null
    //   840: astore_2
    //   841: aconst_null
    //   842: astore #5
    //   844: aload_3
    //   845: invokevirtual printStackTrace : ()V
    //   848: new java/lang/StringBuilder
    //   851: dup
    //   852: invokespecial <init> : ()V
    //   855: astore #6
    //   857: aload #6
    //   859: ldc_w 'HttpClient uploadFile Exception: '
    //   862: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   865: pop
    //   866: aload #6
    //   868: aload_3
    //   869: invokevirtual getMessage : ()Ljava/lang/String;
    //   872: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   875: pop
    //   876: iconst_1
    //   877: anewarray java/lang/String
    //   880: dup
    //   881: iconst_0
    //   882: aload #6
    //   884: invokevirtual toString : ()Ljava/lang/String;
    //   887: aastore
    //   888: invokestatic e : ([Ljava/lang/String;)V
    //   891: aload_2
    //   892: ifnull -> 902
    //   895: aload_2
    //   896: invokevirtual close : ()V
    //   899: goto -> 902
    //   902: aload_1
    //   903: ifnull -> 910
    //   906: aload_1
    //   907: invokevirtual close : ()V
    //   910: aload #7
    //   912: ifnull -> 920
    //   915: aload #7
    //   917: invokevirtual close : ()V
    //   920: aload #5
    //   922: ifnull -> 937
    //   925: aload #5
    //   927: invokevirtual disconnect : ()V
    //   930: aload #8
    //   932: areturn
    //   933: aload_1
    //   934: invokevirtual printStackTrace : ()V
    //   937: aload #8
    //   939: areturn
    //   940: astore_3
    //   941: aload_2
    //   942: astore #6
    //   944: aload_1
    //   945: astore_2
    //   946: aload_3
    //   947: astore_1
    //   948: aload #6
    //   950: ifnull -> 961
    //   953: aload #6
    //   955: invokevirtual close : ()V
    //   958: goto -> 961
    //   961: aload_2
    //   962: ifnull -> 969
    //   965: aload_2
    //   966: invokevirtual close : ()V
    //   969: aload #7
    //   971: ifnull -> 979
    //   974: aload #7
    //   976: invokevirtual close : ()V
    //   979: aload #5
    //   981: ifnull -> 996
    //   984: aload #5
    //   986: invokevirtual disconnect : ()V
    //   989: goto -> 996
    //   992: aload_2
    //   993: invokevirtual printStackTrace : ()V
    //   996: aload_1
    //   997: athrow
    //   998: astore_1
    //   999: aconst_null
    //   1000: astore_2
    //   1001: goto -> 948
    //   1004: astore_3
    //   1005: aload #6
    //   1007: astore_2
    //   1008: aconst_null
    //   1009: astore_1
    //   1010: goto -> 844
    //   1013: astore_1
    //   1014: goto -> 933
    //   1017: astore_2
    //   1018: goto -> 992
    // Exception table:
    //   from	to	target	type
    //   19	35	836	java/lang/Exception
    //   19	35	824	finally
    //   35	170	816	java/lang/Exception
    //   35	170	812	finally
    //   174	195	1004	java/lang/Exception
    //   174	195	998	finally
    //   195	330	1004	java/lang/Exception
    //   195	330	998	finally
    //   337	358	1004	java/lang/Exception
    //   337	358	998	finally
    //   358	397	1004	java/lang/Exception
    //   358	397	998	finally
    //   404	611	1004	java/lang/Exception
    //   404	611	998	finally
    //   611	618	1004	java/lang/Exception
    //   611	618	998	finally
    //   624	633	1004	java/lang/Exception
    //   624	633	998	finally
    //   636	640	1004	java/lang/Exception
    //   636	640	998	finally
    //   643	694	1004	java/lang/Exception
    //   643	694	998	finally
    //   697	710	1004	java/lang/Exception
    //   697	710	998	finally
    //   714	721	762	java/lang/Exception
    //   714	721	758	finally
    //   727	741	762	java/lang/Exception
    //   727	741	758	finally
    //   744	755	762	java/lang/Exception
    //   744	755	758	finally
    //   776	781	1013	java/lang/Exception
    //   785	789	1013	java/lang/Exception
    //   794	799	1013	java/lang/Exception
    //   804	809	1013	java/lang/Exception
    //   844	891	940	finally
    //   895	899	1013	java/lang/Exception
    //   906	910	1013	java/lang/Exception
    //   915	920	1013	java/lang/Exception
    //   925	930	1013	java/lang/Exception
    //   953	958	1017	java/lang/Exception
    //   965	969	1017	java/lang/Exception
    //   974	979	1017	java/lang/Exception
    //   984	989	1017	java/lang/Exception
  }
  
  public static class IgnoreSSLTrustManager implements X509TrustManager {
    public void checkClientTrusted(X509Certificate[] param1ArrayOfX509Certificate, String param1String) {}
    
    public void checkServerTrusted(X509Certificate[] param1ArrayOfX509Certificate, String param1String) {}
    
    public X509Certificate[] getAcceptedIssuers() {
      return new X509Certificate[0];
    }
  }
  
  public class Result {
    private byte[] b;
    
    private int c = 0;
    
    private int d = 0;
    
    public Result(HttpClient this$0) {}
    
    public byte[] getData() {
      return (this.b != null && this.b.length > 0) ? this.b : new byte[0];
    }
    
    public String getDataString() {
      if (this.b != null && this.b.length > 0)
        try {
          return new String(this.b, "utf-8");
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
          unsupportedEncodingException.printStackTrace();
        }  
      return new String();
    }
    
    public int getHttpCode() {
      return this.c;
    }
    
    public boolean hasJump() {
      return (this.d > 0);
    }
    
    public boolean isFailCode() {
      return (this.c == 0 || !isSuccessful());
    }
    
    public boolean isRedirect() {
      switch (this.c) {
        default:
          return false;
        case 300:
        case 301:
        case 302:
        case 303:
        case 307:
        case 308:
          break;
      } 
      return true;
    }
    
    public boolean isSuccessful() {
      return (this.c >= 200 && this.c < 300);
    }
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\http\HttpClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */