package com.cndatacom.cnportalclient.http;

import android.text.TextUtils;
import com.cndatacom.cnportalclient.model.InnerState;
import com.cndatacom.cnportalclient.utils.ExLog;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

public class CdcHttpClient {
  public static final String AREA = "area";
  
  public static final String DOMAIN = "domain";
  
  public static final String HEAD_OBJ = "head";
  
  public static final String SCHOOLID = "schoolID";
  
  private static final byte[] a = "00000000-0000-0000-0000-000000000000".getBytes();
  
  private static final Pattern b = Pattern.compile("<meta(\\s+)http-equiv=(['\"])?refresh[^>]*url=([^\"^']+)(['\"])[^>]*>", 2);
  
  private static final Pattern c = Pattern.compile("<script type=[^>]+>location.href=(['\"])([^\"^']+)(['\"])[^<]*</script>", 2);
  
  private InnerState d = null;
  
  public CdcHttpClient(InnerState paramInnerState) {
    this.d = paramInnerState;
  }
  
  private Result a(String paramString1, byte[] paramArrayOfbyte, int paramInt, String paramString2, boolean paramBoolean1, boolean paramBoolean2) {
    // Byte code:
    //   0: aload_2
    //   1: astore #13
    //   3: aload #13
    //   5: ifnull -> 23
    //   8: aload #13
    //   10: arraylength
    //   11: ifne -> 17
    //   14: goto -> 23
    //   17: iconst_0
    //   18: istore #8
    //   20: goto -> 26
    //   23: iconst_1
    //   24: istore #8
    //   26: iload_3
    //   27: ifgt -> 36
    //   30: iconst_1
    //   31: istore #9
    //   33: goto -> 39
    //   36: iload_3
    //   37: istore #9
    //   39: ldc 'index.cgi'
    //   41: astore #13
    //   43: iconst_0
    //   44: istore_3
    //   45: iconst_0
    //   46: istore #7
    //   48: aconst_null
    //   49: astore #17
    //   51: aconst_null
    //   52: astore #15
    //   54: iload #8
    //   56: istore #10
    //   58: iload_3
    //   59: iload #9
    //   61: if_icmpge -> 2189
    //   64: aload_1
    //   65: invokestatic isEmpty : (Ljava/lang/CharSequence;)Z
    //   68: ifne -> 2189
    //   71: aload_1
    //   72: aload #13
    //   74: invokevirtual contains : (Ljava/lang/CharSequence;)Z
    //   77: istore #12
    //   79: iload #12
    //   81: ifeq -> 119
    //   84: invokestatic getDetectUrl : ()Ljava/lang/String;
    //   87: aload_1
    //   88: aload #13
    //   90: invokestatic replaceDetectUrl : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   93: astore #14
    //   95: aload #14
    //   97: astore_1
    //   98: goto -> 119
    //   101: astore #17
    //   103: aload #15
    //   105: astore #14
    //   107: aconst_null
    //   108: astore #16
    //   110: aload_1
    //   111: astore #15
    //   113: aload #17
    //   115: astore_1
    //   116: goto -> 2027
    //   119: new com/cndatacom/cnportalclient/http/CdcHttpClient$Result
    //   122: dup
    //   123: aload_0
    //   124: invokespecial <init> : (Lcom/cndatacom/cnportalclient/http/CdcHttpClient;)V
    //   127: astore #14
    //   129: aload #14
    //   131: astore #19
    //   133: new java/net/URL
    //   136: dup
    //   137: aload_1
    //   138: invokespecial <init> : (Ljava/lang/String;)V
    //   141: invokevirtual openConnection : ()Ljava/net/URLConnection;
    //   144: checkcast java/net/HttpURLConnection
    //   147: astore #16
    //   149: aload #14
    //   151: astore #19
    //   153: iconst_2
    //   154: anewarray java/lang/String
    //   157: dup
    //   158: iconst_0
    //   159: ldc 'REQ => '
    //   161: aastore
    //   162: dup
    //   163: iconst_1
    //   164: aload_1
    //   165: aastore
    //   166: invokestatic i : ([Ljava/lang/String;)V
    //   169: aload #14
    //   171: astore #19
    //   173: aload #16
    //   175: sipush #30000
    //   178: invokevirtual setConnectTimeout : (I)V
    //   181: aload #14
    //   183: astore #19
    //   185: aload #16
    //   187: sipush #30000
    //   190: invokevirtual setReadTimeout : (I)V
    //   193: aload #14
    //   195: astore #19
    //   197: aload #16
    //   199: iconst_0
    //   200: invokevirtual setInstanceFollowRedirects : (Z)V
    //   203: aload #14
    //   205: astore #19
    //   207: aload #16
    //   209: iconst_0
    //   210: invokevirtual setUseCaches : (Z)V
    //   213: aload #14
    //   215: astore #19
    //   217: aload #16
    //   219: iconst_0
    //   220: invokevirtual setAllowUserInteraction : (Z)V
    //   223: aload #14
    //   225: astore #19
    //   227: aload #16
    //   229: ldc 'Connection'
    //   231: ldc 'close'
    //   233: invokevirtual setRequestProperty : (Ljava/lang/String;Ljava/lang/String;)V
    //   236: aload #14
    //   238: astore #19
    //   240: aload #16
    //   242: ldc 'Accept'
    //   244: ldc 'text/html,text/xml,application/xhtml+xml,application/x-javascript,*/*'
    //   246: invokevirtual setRequestProperty : (Ljava/lang/String;Ljava/lang/String;)V
    //   249: aload #14
    //   251: astore #19
    //   253: aload_0
    //   254: aload #16
    //   256: ldc 'CDC-SchoolId'
    //   258: ldc 'schoolID'
    //   260: invokespecial d : (Ljava/net/HttpURLConnection;Ljava/lang/String;Ljava/lang/String;)V
    //   263: aload #14
    //   265: astore #19
    //   267: aload_0
    //   268: aload #16
    //   270: ldc 'CDC-Domain'
    //   272: ldc 'domain'
    //   274: invokespecial d : (Ljava/net/HttpURLConnection;Ljava/lang/String;Ljava/lang/String;)V
    //   277: aload #14
    //   279: astore #19
    //   281: aload_0
    //   282: aload #16
    //   284: ldc 'CDC-Area'
    //   286: ldc 'area'
    //   288: invokespecial d : (Ljava/net/HttpURLConnection;Ljava/lang/String;Ljava/lang/String;)V
    //   291: aload #14
    //   293: astore #19
    //   295: aload #16
    //   297: ldc 'Client-ID'
    //   299: aload_0
    //   300: getfield d : Lcom/cndatacom/cnportalclient/model/InnerState;
    //   303: invokevirtual getClientId : ()Ljava/lang/String;
    //   306: invokevirtual setRequestProperty : (Ljava/lang/String;Ljava/lang/String;)V
    //   309: aload #14
    //   311: astore #19
    //   313: aload_0
    //   314: aload_1
    //   315: invokespecial a : (Ljava/lang/String;)V
    //   318: iload #10
    //   320: ifeq -> 364
    //   323: aload #14
    //   325: astore #19
    //   327: aload #16
    //   329: ldc 'User-Agent'
    //   331: aload_0
    //   332: getfield d : Lcom/cndatacom/cnportalclient/model/InnerState;
    //   335: iconst_1
    //   336: invokevirtual getUserAgent : (Z)Ljava/lang/String;
    //   339: invokevirtual setRequestProperty : (Ljava/lang/String;Ljava/lang/String;)V
    //   342: aload #14
    //   344: astore #19
    //   346: aload #16
    //   348: ldc 'GET'
    //   350: invokevirtual setRequestMethod : (Ljava/lang/String;)V
    //   353: goto -> 698
    //   356: astore #17
    //   358: aload_1
    //   359: astore #15
    //   361: goto -> 113
    //   364: aload #14
    //   366: astore #19
    //   368: aload #16
    //   370: ldc 'User-Agent'
    //   372: aload_0
    //   373: getfield d : Lcom/cndatacom/cnportalclient/model/InnerState;
    //   376: iconst_0
    //   377: invokevirtual getUserAgent : (Z)Ljava/lang/String;
    //   380: invokevirtual setRequestProperty : (Ljava/lang/String;Ljava/lang/String;)V
    //   383: aload #14
    //   385: astore #19
    //   387: aload #16
    //   389: ldc 'POST'
    //   391: invokevirtual setRequestMethod : (Ljava/lang/String;)V
    //   394: aload #14
    //   396: astore #19
    //   398: aload #16
    //   400: iconst_1
    //   401: invokevirtual setDoOutput : (Z)V
    //   404: iload #5
    //   406: ifeq -> 415
    //   409: aconst_null
    //   410: astore #15
    //   412: goto -> 428
    //   415: aload #14
    //   417: astore #19
    //   419: aload_0
    //   420: getfield d : Lcom/cndatacom/cnportalclient/model/InnerState;
    //   423: invokevirtual getDaMod : ()Lcom/cndatacom/campus/netcore/DaMod;
    //   426: astore #15
    //   428: aload #15
    //   430: ifnull -> 537
    //   433: aload #14
    //   435: astore #19
    //   437: aload #15
    //   439: invokevirtual isOk : ()Z
    //   442: ifne -> 448
    //   445: goto -> 537
    //   448: aload #14
    //   450: astore #19
    //   452: aload #15
    //   454: invokevirtual getId : ()Ljava/lang/String;
    //   457: astore #18
    //   459: aload #14
    //   461: astore #19
    //   463: aload #15
    //   465: aload_2
    //   466: invokevirtual Encode : ([B)[B
    //   469: astore #20
    //   471: aload #20
    //   473: ifnull -> 490
    //   476: aload #20
    //   478: astore #17
    //   480: aload #14
    //   482: astore #19
    //   484: aload #20
    //   486: arraylength
    //   487: ifne -> 550
    //   490: aload #14
    //   492: astore #19
    //   494: aload #14
    //   496: ldc ''
    //   498: invokestatic a : (Lcom/cndatacom/cnportalclient/http/CdcHttpClient$Result;Ljava/lang/String;)Ljava/lang/String;
    //   501: pop
    //   502: aload #14
    //   504: astore #19
    //   506: aload #14
    //   508: ldc ''
    //   510: invokestatic b : (Lcom/cndatacom/cnportalclient/http/CdcHttpClient$Result;Ljava/lang/String;)Ljava/lang/String;
    //   513: pop
    //   514: aload #14
    //   516: astore #19
    //   518: aload #14
    //   520: ldc '200113'
    //   522: invokestatic c : (Lcom/cndatacom/cnportalclient/http/CdcHttpClient$Result;Ljava/lang/String;)Ljava/lang/String;
    //   525: pop
    //   526: aload #14
    //   528: astore #21
    //   530: aload #16
    //   532: astore #18
    //   534: goto -> 2196
    //   537: ldc '00000000-0000-0000-0000-000000000000'
    //   539: astore #18
    //   541: aload #14
    //   543: astore #19
    //   545: getstatic com/cndatacom/cnportalclient/http/CdcHttpClient.a : [B
    //   548: astore #17
    //   550: aload #14
    //   552: astore #19
    //   554: aload_0
    //   555: aload #16
    //   557: ldc 'Algo-ID'
    //   559: aload #18
    //   561: invokespecial c : (Ljava/net/HttpURLConnection;Ljava/lang/String;Ljava/lang/String;)V
    //   564: aload #14
    //   566: astore #19
    //   568: aload #17
    //   570: invokestatic md5 : ([B)[B
    //   573: invokestatic byteArrayToHex : ([B)Ljava/lang/String;
    //   576: astore #18
    //   578: aload #14
    //   580: astore #19
    //   582: aload #16
    //   584: ldc 'CDC-Checksum'
    //   586: aload #18
    //   588: invokevirtual setRequestProperty : (Ljava/lang/String;Ljava/lang/String;)V
    //   591: aload #14
    //   593: astore #19
    //   595: iconst_2
    //   596: anewarray java/lang/String
    //   599: dup
    //   600: iconst_0
    //   601: ldc 'CDC-Checksum: '
    //   603: aastore
    //   604: dup
    //   605: iconst_1
    //   606: aload #18
    //   608: aastore
    //   609: invokestatic d : ([Ljava/lang/String;)V
    //   612: aload #14
    //   614: astore #19
    //   616: aload #16
    //   618: ldc 'Content-Length'
    //   620: aload #17
    //   622: arraylength
    //   623: invokestatic valueOf : (I)Ljava/lang/String;
    //   626: invokevirtual setRequestProperty : (Ljava/lang/String;Ljava/lang/String;)V
    //   629: aload #14
    //   631: astore #19
    //   633: iconst_2
    //   634: anewarray java/lang/String
    //   637: dup
    //   638: iconst_0
    //   639: ldc 'Content-Length: '
    //   641: aastore
    //   642: dup
    //   643: iconst_1
    //   644: aload #17
    //   646: arraylength
    //   647: invokestatic valueOf : (I)Ljava/lang/String;
    //   650: aastore
    //   651: invokestatic d : ([Ljava/lang/String;)V
    //   654: aload #14
    //   656: astore #19
    //   658: aload #16
    //   660: invokevirtual getOutputStream : ()Ljava/io/OutputStream;
    //   663: astore #18
    //   665: aload #14
    //   667: astore #19
    //   669: aload #18
    //   671: aload #17
    //   673: invokevirtual write : ([B)V
    //   676: aload #14
    //   678: astore #19
    //   680: aload #18
    //   682: invokevirtual flush : ()V
    //   685: aload #14
    //   687: astore #19
    //   689: aload #18
    //   691: invokevirtual close : ()V
    //   694: aload #15
    //   696: astore #17
    //   698: aload #14
    //   700: astore #19
    //   702: aload #14
    //   704: aload #16
    //   706: invokevirtual getResponseCode : ()I
    //   709: invokestatic a : (Lcom/cndatacom/cnportalclient/http/CdcHttpClient$Result;I)I
    //   712: pop
    //   713: aload #14
    //   715: astore #19
    //   717: iconst_2
    //   718: anewarray java/lang/String
    //   721: dup
    //   722: iconst_0
    //   723: ldc '  ResponseCode: '
    //   725: aastore
    //   726: dup
    //   727: iconst_1
    //   728: aload #14
    //   730: invokestatic a : (Lcom/cndatacom/cnportalclient/http/CdcHttpClient$Result;)I
    //   733: invokestatic valueOf : (I)Ljava/lang/String;
    //   736: aastore
    //   737: invokestatic i : ([Ljava/lang/String;)V
    //   740: aload #14
    //   742: astore #19
    //   744: aload_0
    //   745: aload #16
    //   747: ldc 'schoolID'
    //   749: invokespecial b : (Ljava/net/HttpURLConnection;Ljava/lang/String;)V
    //   752: aload #14
    //   754: astore #19
    //   756: aload_0
    //   757: aload #16
    //   759: ldc 'domain'
    //   761: invokespecial b : (Ljava/net/HttpURLConnection;Ljava/lang/String;)V
    //   764: aload #14
    //   766: astore #19
    //   768: aload_0
    //   769: aload #16
    //   771: ldc 'area'
    //   773: invokespecial b : (Ljava/net/HttpURLConnection;Ljava/lang/String;)V
    //   776: aload #14
    //   778: astore #19
    //   780: aload #14
    //   782: invokevirtual isOk : ()Z
    //   785: istore #12
    //   787: iload #12
    //   789: ifeq -> 1773
    //   792: aload #14
    //   794: astore #19
    //   796: aload_0
    //   797: aload #16
    //   799: ldc_w 'Server-Tag'
    //   802: invokespecial a : (Ljava/net/HttpURLConnection;Ljava/lang/String;)Ljava/lang/String;
    //   805: astore #15
    //   807: aload #14
    //   809: astore #19
    //   811: aload #15
    //   813: invokevirtual length : ()I
    //   816: istore #8
    //   818: iload #8
    //   820: ifle -> 845
    //   823: aload #14
    //   825: astore #19
    //   827: iconst_2
    //   828: anewarray java/lang/String
    //   831: dup
    //   832: iconst_0
    //   833: ldc_w '  Server-Tag: '
    //   836: aastore
    //   837: dup
    //   838: iconst_1
    //   839: aload #15
    //   841: aastore
    //   842: invokestatic i : ([Ljava/lang/String;)V
    //   845: aload #14
    //   847: astore #19
    //   849: aload_0
    //   850: aload #16
    //   852: ldc 'Content-Length'
    //   854: ldc ''
    //   856: invokespecial a : (Ljava/net/HttpURLConnection;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   859: astore #15
    //   861: aload #14
    //   863: astore #19
    //   865: iconst_2
    //   866: anewarray java/lang/String
    //   869: dup
    //   870: iconst_0
    //   871: ldc_w '  Content-Length: '
    //   874: aastore
    //   875: dup
    //   876: iconst_1
    //   877: aload #15
    //   879: aastore
    //   880: invokestatic i : ([Ljava/lang/String;)V
    //   883: aload #14
    //   885: astore #19
    //   887: aload #15
    //   889: iconst_m1
    //   890: invokestatic toInt : (Ljava/lang/String;I)I
    //   893: istore #11
    //   895: iload #11
    //   897: istore #8
    //   899: iload #11
    //   901: ifge -> 909
    //   904: sipush #4096
    //   907: istore #8
    //   909: aload #14
    //   911: astore #19
    //   913: iload #8
    //   915: newarray byte
    //   917: astore #20
    //   919: aload #14
    //   921: astore #19
    //   923: aload #16
    //   925: invokevirtual getInputStream : ()Ljava/io/InputStream;
    //   928: astore #15
    //   930: iload #8
    //   932: ifle -> 2242
    //   935: aload #14
    //   937: astore #19
    //   939: aload #15
    //   941: aload #20
    //   943: aload #20
    //   945: arraylength
    //   946: iload #8
    //   948: isub
    //   949: iload #8
    //   951: invokevirtual read : ([BII)I
    //   954: istore #11
    //   956: iload #11
    //   958: ifge -> 964
    //   961: goto -> 2242
    //   964: iload #8
    //   966: iload #11
    //   968: isub
    //   969: istore #8
    //   971: goto -> 930
    //   974: astore #18
    //   976: aload #14
    //   978: astore #19
    //   980: iconst_2
    //   981: anewarray java/lang/String
    //   984: dup
    //   985: iconst_0
    //   986: ldc_w 'httpclient read Exception: '
    //   989: aastore
    //   990: dup
    //   991: iconst_1
    //   992: aload #18
    //   994: invokevirtual getMessage : ()Ljava/lang/String;
    //   997: aastore
    //   998: invokestatic w : ([Ljava/lang/String;)V
    //   1001: goto -> 1004
    //   1004: aload #13
    //   1006: astore #18
    //   1008: aload #14
    //   1010: astore #19
    //   1012: aload #15
    //   1014: invokevirtual close : ()V
    //   1017: iload #10
    //   1019: ifeq -> 1213
    //   1022: aload #14
    //   1024: astore #19
    //   1026: new java/lang/String
    //   1029: dup
    //   1030: aload #20
    //   1032: aload #4
    //   1034: invokespecial <init> : ([BLjava/lang/String;)V
    //   1037: astore #20
    //   1039: iload_3
    //   1040: iload #9
    //   1042: if_icmpge -> 2245
    //   1045: aload #14
    //   1047: astore #19
    //   1049: getstatic com/cndatacom/cnportalclient/http/CdcHttpClient.b : Ljava/util/regex/Pattern;
    //   1052: aload #20
    //   1054: invokevirtual matcher : (Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   1057: astore_1
    //   1058: aload #14
    //   1060: astore #19
    //   1062: aload_1
    //   1063: invokevirtual find : ()Z
    //   1066: ifeq -> 1109
    //   1069: aload #14
    //   1071: astore #19
    //   1073: aload_1
    //   1074: iconst_3
    //   1075: invokevirtual group : (I)Ljava/lang/String;
    //   1078: invokevirtual trim : ()Ljava/lang/String;
    //   1081: astore_1
    //   1082: aload_1
    //   1083: astore #15
    //   1085: aload #14
    //   1087: astore #19
    //   1089: iconst_2
    //   1090: anewarray java/lang/String
    //   1093: dup
    //   1094: iconst_0
    //   1095: ldc_w 'FIND META URL => '
    //   1098: aastore
    //   1099: dup
    //   1100: iconst_1
    //   1101: aload_1
    //   1102: aastore
    //   1103: invokestatic i : ([Ljava/lang/String;)V
    //   1106: goto -> 1173
    //   1109: aload #14
    //   1111: astore #19
    //   1113: getstatic com/cndatacom/cnportalclient/http/CdcHttpClient.c : Ljava/util/regex/Pattern;
    //   1116: aload #20
    //   1118: invokevirtual matcher : (Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   1121: astore_1
    //   1122: aload #14
    //   1124: astore #19
    //   1126: aload_1
    //   1127: invokevirtual find : ()Z
    //   1130: ifeq -> 2245
    //   1133: aload #14
    //   1135: astore #19
    //   1137: aload_1
    //   1138: iconst_2
    //   1139: invokevirtual group : (I)Ljava/lang/String;
    //   1142: invokevirtual trim : ()Ljava/lang/String;
    //   1145: astore_1
    //   1146: aload_1
    //   1147: astore #15
    //   1149: aload #14
    //   1151: astore #19
    //   1153: iconst_2
    //   1154: anewarray java/lang/String
    //   1157: dup
    //   1158: iconst_0
    //   1159: ldc_w 'FIND SCRIPT URL => '
    //   1162: aastore
    //   1163: dup
    //   1164: iconst_1
    //   1165: aload_1
    //   1166: aastore
    //   1167: invokestatic i : ([Ljava/lang/String;)V
    //   1170: goto -> 1173
    //   1173: aload_1
    //   1174: astore #15
    //   1176: aload #14
    //   1178: astore #19
    //   1180: aload_1
    //   1181: invokestatic isEmpty : (Ljava/lang/CharSequence;)Z
    //   1184: ifeq -> 2250
    //   1187: aload_1
    //   1188: astore #15
    //   1190: aload #14
    //   1192: astore #19
    //   1194: aload #14
    //   1196: aload #20
    //   1198: invokestatic d : (Lcom/cndatacom/cnportalclient/http/CdcHttpClient$Result;Ljava/lang/String;)Ljava/lang/String;
    //   1201: pop
    //   1202: aload #14
    //   1204: astore #21
    //   1206: aload #16
    //   1208: astore #18
    //   1210: goto -> 2196
    //   1213: aload_1
    //   1214: astore #15
    //   1216: aload #14
    //   1218: astore #19
    //   1220: aload_0
    //   1221: aload #16
    //   1223: ldc_w 'Error-Code'
    //   1226: invokespecial a : (Ljava/net/HttpURLConnection;Ljava/lang/String;)Ljava/lang/String;
    //   1229: astore #22
    //   1231: aload_1
    //   1232: astore #15
    //   1234: aload #14
    //   1236: astore #19
    //   1238: iconst_2
    //   1239: anewarray java/lang/String
    //   1242: dup
    //   1243: iconst_0
    //   1244: ldc_w '  Error-Code: '
    //   1247: aastore
    //   1248: dup
    //   1249: iconst_1
    //   1250: aload #22
    //   1252: aastore
    //   1253: invokestatic i : ([Ljava/lang/String;)V
    //   1256: aload_1
    //   1257: astore #15
    //   1259: aload #14
    //   1261: astore #19
    //   1263: aload #22
    //   1265: invokestatic isEmpty : (Ljava/lang/CharSequence;)Z
    //   1268: ifeq -> 1298
    //   1271: aload_1
    //   1272: astore #15
    //   1274: aload #14
    //   1276: astore #19
    //   1278: aload #14
    //   1280: sipush #310
    //   1283: invokestatic a : (Lcom/cndatacom/cnportalclient/http/CdcHttpClient$Result;I)I
    //   1286: pop
    //   1287: aload #14
    //   1289: astore #21
    //   1291: aload #16
    //   1293: astore #18
    //   1295: goto -> 2196
    //   1298: aload_1
    //   1299: astore #15
    //   1301: aload #14
    //   1303: astore #19
    //   1305: aload #14
    //   1307: aload #22
    //   1309: invokestatic a : (Lcom/cndatacom/cnportalclient/http/CdcHttpClient$Result;Ljava/lang/String;)Ljava/lang/String;
    //   1312: pop
    //   1313: aload_1
    //   1314: astore #15
    //   1316: aload #14
    //   1318: astore #19
    //   1320: aload #14
    //   1322: aload_0
    //   1323: aload #16
    //   1325: ldc_w 'Sub-Error'
    //   1328: invokespecial a : (Ljava/net/HttpURLConnection;Ljava/lang/String;)Ljava/lang/String;
    //   1331: invokestatic b : (Lcom/cndatacom/cnportalclient/http/CdcHttpClient$Result;Ljava/lang/String;)Ljava/lang/String;
    //   1334: pop
    //   1335: aload_1
    //   1336: astore #15
    //   1338: aload #14
    //   1340: astore #19
    //   1342: iconst_2
    //   1343: anewarray java/lang/String
    //   1346: dup
    //   1347: iconst_0
    //   1348: ldc_w '  Sub-Error: '
    //   1351: aastore
    //   1352: dup
    //   1353: iconst_1
    //   1354: aload #14
    //   1356: invokestatic b : (Lcom/cndatacom/cnportalclient/http/CdcHttpClient$Result;)Ljava/lang/String;
    //   1359: aastore
    //   1360: invokestatic i : ([Ljava/lang/String;)V
    //   1363: aload_1
    //   1364: astore #15
    //   1366: aload #14
    //   1368: astore #19
    //   1370: ldc_w '105'
    //   1373: aload #22
    //   1375: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1378: ifeq -> 1414
    //   1381: aload_1
    //   1382: astore #15
    //   1384: aload #14
    //   1386: astore #19
    //   1388: aload_0
    //   1389: getfield d : Lcom/cndatacom/cnportalclient/model/InnerState;
    //   1392: invokevirtual getConfig : ()Lorg/json/JSONObject;
    //   1395: ldc_w 'Last-Login'
    //   1398: aload_0
    //   1399: aload #16
    //   1401: ldc_w 'Last-Login'
    //   1404: invokespecial a : (Ljava/net/HttpURLConnection;Ljava/lang/String;)Ljava/lang/String;
    //   1407: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1410: pop
    //   1411: goto -> 1437
    //   1414: aload_1
    //   1415: astore #15
    //   1417: aload #14
    //   1419: astore #19
    //   1421: aload_0
    //   1422: getfield d : Lcom/cndatacom/cnportalclient/model/InnerState;
    //   1425: invokevirtual getConfig : ()Lorg/json/JSONObject;
    //   1428: ldc_w 'Last-Login'
    //   1431: ldc ''
    //   1433: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1436: pop
    //   1437: aload_1
    //   1438: astore #15
    //   1440: aload #14
    //   1442: astore #19
    //   1444: ldc_w '200'
    //   1447: aload #22
    //   1449: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1452: ifeq -> 1535
    //   1455: iload #7
    //   1457: iconst_1
    //   1458: iadd
    //   1459: istore #7
    //   1461: iload #7
    //   1463: iconst_3
    //   1464: if_icmple -> 1498
    //   1467: aload_1
    //   1468: astore #15
    //   1470: aload #14
    //   1472: astore #19
    //   1474: iconst_1
    //   1475: anewarray java/lang/String
    //   1478: dup
    //   1479: iconst_0
    //   1480: ldc_w 'algo count is surpass'
    //   1483: aastore
    //   1484: invokestatic e : ([Ljava/lang/String;)V
    //   1487: aload #14
    //   1489: astore #21
    //   1491: aload #16
    //   1493: astore #18
    //   1495: goto -> 2196
    //   1498: aload_1
    //   1499: astore #15
    //   1501: aload #14
    //   1503: astore #19
    //   1505: aload_0
    //   1506: getfield d : Lcom/cndatacom/cnportalclient/model/InnerState;
    //   1509: aload #20
    //   1511: invokevirtual setDaMod : ([B)Lcom/cndatacom/cnportalclient/model/InnerState;
    //   1514: invokevirtual getDaMod : ()Lcom/cndatacom/campus/netcore/DaMod;
    //   1517: astore #17
    //   1519: iload #5
    //   1521: ifeq -> 2273
    //   1524: aload #14
    //   1526: astore #21
    //   1528: aload #16
    //   1530: astore #18
    //   1532: goto -> 2196
    //   1535: aload #14
    //   1537: astore #21
    //   1539: aload #16
    //   1541: astore #18
    //   1543: iload #6
    //   1545: ifeq -> 2196
    //   1548: aload_1
    //   1549: astore #15
    //   1551: aload #14
    //   1553: astore #19
    //   1555: aload #14
    //   1557: astore #21
    //   1559: aload #16
    //   1561: astore #18
    //   1563: ldc_w '0'
    //   1566: aload #22
    //   1568: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1571: ifeq -> 2196
    //   1574: aload #17
    //   1576: ifnull -> 1695
    //   1579: aload_1
    //   1580: astore #15
    //   1582: aload #14
    //   1584: astore #19
    //   1586: aload #17
    //   1588: invokevirtual isOk : ()Z
    //   1591: ifeq -> 1695
    //   1594: aload_1
    //   1595: astore #15
    //   1597: aload #14
    //   1599: astore #19
    //   1601: aload #14
    //   1603: aload #17
    //   1605: aload #20
    //   1607: invokevirtual Decode : ([B)[B
    //   1610: invokestatic a : (Lcom/cndatacom/cnportalclient/http/CdcHttpClient$Result;[B)[B
    //   1613: pop
    //   1614: aload_1
    //   1615: astore #15
    //   1617: aload #14
    //   1619: astore #19
    //   1621: aload #14
    //   1623: invokestatic c : (Lcom/cndatacom/cnportalclient/http/CdcHttpClient$Result;)[B
    //   1626: ifnull -> 1653
    //   1629: aload_1
    //   1630: astore #15
    //   1632: aload #14
    //   1634: astore #19
    //   1636: aload #14
    //   1638: astore #21
    //   1640: aload #16
    //   1642: astore #18
    //   1644: aload #14
    //   1646: invokestatic c : (Lcom/cndatacom/cnportalclient/http/CdcHttpClient$Result;)[B
    //   1649: arraylength
    //   1650: ifne -> 2196
    //   1653: aload_1
    //   1654: astore #15
    //   1656: aload #14
    //   1658: astore #19
    //   1660: aload #14
    //   1662: ldc ''
    //   1664: invokestatic b : (Lcom/cndatacom/cnportalclient/http/CdcHttpClient$Result;Ljava/lang/String;)Ljava/lang/String;
    //   1667: pop
    //   1668: aload_1
    //   1669: astore #15
    //   1671: aload #14
    //   1673: astore #19
    //   1675: aload #14
    //   1677: ldc_w '200114'
    //   1680: invokestatic c : (Lcom/cndatacom/cnportalclient/http/CdcHttpClient$Result;Ljava/lang/String;)Ljava/lang/String;
    //   1683: pop
    //   1684: aload #14
    //   1686: astore #21
    //   1688: aload #16
    //   1690: astore #18
    //   1692: goto -> 2196
    //   1695: aload_1
    //   1696: astore #15
    //   1698: aload #14
    //   1700: astore #19
    //   1702: iconst_1
    //   1703: anewarray java/lang/String
    //   1706: dup
    //   1707: iconst_0
    //   1708: ldc_w 'daMod error'
    //   1711: aastore
    //   1712: invokestatic e : ([Ljava/lang/String;)V
    //   1715: aload_1
    //   1716: astore #15
    //   1718: aload #14
    //   1720: astore #19
    //   1722: aload #14
    //   1724: ldc ''
    //   1726: invokestatic b : (Lcom/cndatacom/cnportalclient/http/CdcHttpClient$Result;Ljava/lang/String;)Ljava/lang/String;
    //   1729: pop
    //   1730: aload_1
    //   1731: astore #15
    //   1733: aload #14
    //   1735: astore #19
    //   1737: aload #14
    //   1739: ldc_w '200114'
    //   1742: invokestatic c : (Lcom/cndatacom/cnportalclient/http/CdcHttpClient$Result;Ljava/lang/String;)Ljava/lang/String;
    //   1745: pop
    //   1746: aload #14
    //   1748: astore #21
    //   1750: aload #16
    //   1752: astore #18
    //   1754: goto -> 2196
    //   1757: astore #17
    //   1759: goto -> 1764
    //   1762: astore #17
    //   1764: aload_1
    //   1765: astore #15
    //   1767: aload #17
    //   1769: astore_1
    //   1770: goto -> 2027
    //   1773: aload #13
    //   1775: astore #18
    //   1777: aload #14
    //   1779: astore #19
    //   1781: aload #14
    //   1783: invokestatic d : (Lcom/cndatacom/cnportalclient/http/CdcHttpClient$Result;)Z
    //   1786: istore #12
    //   1788: iload #12
    //   1790: ifeq -> 1823
    //   1793: aload_1
    //   1794: astore #15
    //   1796: aload #14
    //   1798: astore #19
    //   1800: aload_0
    //   1801: aload #16
    //   1803: ldc_w 'Location'
    //   1806: aconst_null
    //   1807: invokespecial a : (Ljava/net/HttpURLConnection;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   1810: astore_1
    //   1811: aload #18
    //   1813: astore #13
    //   1815: goto -> 1931
    //   1818: astore #17
    //   1820: goto -> 1767
    //   1823: aload #18
    //   1825: astore #13
    //   1827: aload #13
    //   1829: astore #22
    //   1831: aload_1
    //   1832: astore #20
    //   1834: iload_3
    //   1835: istore #8
    //   1837: aload #14
    //   1839: astore #19
    //   1841: aload #14
    //   1843: astore #21
    //   1845: aload #16
    //   1847: astore #18
    //   1849: aload_1
    //   1850: aload #13
    //   1852: invokevirtual contains : (Ljava/lang/CharSequence;)Z
    //   1855: ifeq -> 2196
    //   1858: aload #13
    //   1860: astore #22
    //   1862: aload_1
    //   1863: astore #20
    //   1865: iload_3
    //   1866: istore #8
    //   1868: aload #14
    //   1870: astore #19
    //   1872: aload #14
    //   1874: astore #21
    //   1876: aload #16
    //   1878: astore #18
    //   1880: invokestatic hasDetectUrl : ()Z
    //   1883: ifeq -> 2196
    //   1886: aload #13
    //   1888: astore #22
    //   1890: aload_1
    //   1891: astore #20
    //   1893: iload_3
    //   1894: istore #8
    //   1896: aload #14
    //   1898: astore #19
    //   1900: invokestatic removeDetectUrl : ()V
    //   1903: aload #13
    //   1905: astore #22
    //   1907: aload_1
    //   1908: astore #20
    //   1910: iload_3
    //   1911: istore #8
    //   1913: aload #14
    //   1915: astore #19
    //   1917: aload #14
    //   1919: astore #21
    //   1921: aload #16
    //   1923: astore #18
    //   1925: invokestatic hasDetectUrl : ()Z
    //   1928: ifeq -> 2196
    //   1931: aload #13
    //   1933: astore #22
    //   1935: aload_1
    //   1936: astore #20
    //   1938: iload_3
    //   1939: istore #8
    //   1941: aload #14
    //   1943: astore #19
    //   1945: aload #16
    //   1947: invokevirtual disconnect : ()V
    //   1950: iload_3
    //   1951: iconst_1
    //   1952: iadd
    //   1953: istore_3
    //   1954: aload #14
    //   1956: astore #15
    //   1958: goto -> 58
    //   1961: astore #15
    //   1963: aload #22
    //   1965: astore #13
    //   1967: aload #20
    //   1969: astore_1
    //   1970: iload #8
    //   1972: istore_3
    //   1973: goto -> 2017
    //   1976: astore #15
    //   1978: aload #18
    //   1980: astore #13
    //   1982: goto -> 2017
    //   1985: astore #15
    //   1987: goto -> 2017
    //   1990: astore #15
    //   1992: goto -> 2014
    //   1995: astore #16
    //   1997: goto -> 2006
    //   2000: astore_1
    //   2001: goto -> 2173
    //   2004: astore #16
    //   2006: aload #15
    //   2008: astore #14
    //   2010: aload #16
    //   2012: astore #15
    //   2014: aconst_null
    //   2015: astore #16
    //   2017: aload #15
    //   2019: astore #17
    //   2021: aload_1
    //   2022: astore #15
    //   2024: aload #17
    //   2026: astore_1
    //   2027: aload #14
    //   2029: astore #19
    //   2031: aload_1
    //   2032: invokevirtual printStackTrace : ()V
    //   2035: aload #14
    //   2037: astore #19
    //   2039: iconst_2
    //   2040: anewarray java/lang/String
    //   2043: dup
    //   2044: iconst_0
    //   2045: ldc_w 'httpclient Exception: '
    //   2048: aastore
    //   2049: dup
    //   2050: iconst_1
    //   2051: aload_1
    //   2052: invokevirtual getMessage : ()Ljava/lang/String;
    //   2055: aastore
    //   2056: invokestatic e : ([Ljava/lang/String;)V
    //   2059: aload #14
    //   2061: astore #19
    //   2063: aload #15
    //   2065: aload #13
    //   2067: invokevirtual contains : (Ljava/lang/CharSequence;)Z
    //   2070: ifeq -> 2136
    //   2073: aload #14
    //   2075: astore #19
    //   2077: invokestatic hasDetectUrl : ()Z
    //   2080: ifeq -> 2136
    //   2083: aload #14
    //   2085: astore #19
    //   2087: invokestatic removeDetectUrl : ()V
    //   2090: aload #14
    //   2092: astore #19
    //   2094: invokestatic hasDetectUrl : ()Z
    //   2097: ifeq -> 2136
    //   2100: aload #14
    //   2102: astore #19
    //   2104: aload_0
    //   2105: aload #15
    //   2107: aload_2
    //   2108: iload #9
    //   2110: aload #4
    //   2112: iload #5
    //   2114: iload #6
    //   2116: invokespecial a : (Ljava/lang/String;[BILjava/lang/String;ZZ)Lcom/cndatacom/cnportalclient/http/CdcHttpClient$Result;
    //   2119: astore_1
    //   2120: aload #14
    //   2122: ifnonnull -> 2134
    //   2125: new com/cndatacom/cnportalclient/http/CdcHttpClient$Result
    //   2128: dup
    //   2129: aload_0
    //   2130: invokespecial <init> : (Lcom/cndatacom/cnportalclient/http/CdcHttpClient;)V
    //   2133: pop
    //   2134: aload_1
    //   2135: areturn
    //   2136: iload_3
    //   2137: istore #7
    //   2139: aload #14
    //   2141: astore_1
    //   2142: aload #16
    //   2144: astore_2
    //   2145: aload #14
    //   2147: ifnonnull -> 2225
    //   2150: new com/cndatacom/cnportalclient/http/CdcHttpClient$Result
    //   2153: dup
    //   2154: aload_0
    //   2155: invokespecial <init> : (Lcom/cndatacom/cnportalclient/http/CdcHttpClient;)V
    //   2158: astore_1
    //   2159: iload_3
    //   2160: istore #7
    //   2162: aload #16
    //   2164: astore_2
    //   2165: goto -> 2225
    //   2168: astore_1
    //   2169: aload #19
    //   2171: astore #15
    //   2173: aload #15
    //   2175: ifnonnull -> 2187
    //   2178: new com/cndatacom/cnportalclient/http/CdcHttpClient$Result
    //   2181: dup
    //   2182: aload_0
    //   2183: invokespecial <init> : (Lcom/cndatacom/cnportalclient/http/CdcHttpClient;)V
    //   2186: pop
    //   2187: aload_1
    //   2188: athrow
    //   2189: aconst_null
    //   2190: astore #18
    //   2192: aload #15
    //   2194: astore #21
    //   2196: iload_3
    //   2197: istore #7
    //   2199: aload #21
    //   2201: astore_1
    //   2202: aload #18
    //   2204: astore_2
    //   2205: aload #21
    //   2207: ifnonnull -> 2225
    //   2210: new com/cndatacom/cnportalclient/http/CdcHttpClient$Result
    //   2213: dup
    //   2214: aload_0
    //   2215: invokespecial <init> : (Lcom/cndatacom/cnportalclient/http/CdcHttpClient;)V
    //   2218: astore_1
    //   2219: aload #18
    //   2221: astore_2
    //   2222: iload_3
    //   2223: istore #7
    //   2225: aload_2
    //   2226: ifnull -> 2233
    //   2229: aload_2
    //   2230: invokevirtual disconnect : ()V
    //   2233: aload_1
    //   2234: iload #7
    //   2236: invokestatic b : (Lcom/cndatacom/cnportalclient/http/CdcHttpClient$Result;I)I
    //   2239: pop
    //   2240: aload_1
    //   2241: areturn
    //   2242: goto -> 1004
    //   2245: aconst_null
    //   2246: astore_1
    //   2247: goto -> 1173
    //   2250: aload #18
    //   2252: astore #13
    //   2254: goto -> 1931
    //   2257: astore_1
    //   2258: goto -> 2263
    //   2261: astore #13
    //   2263: aload #18
    //   2265: astore #13
    //   2267: aconst_null
    //   2268: astore #15
    //   2270: goto -> 2027
    //   2273: iload_3
    //   2274: iconst_1
    //   2275: isub
    //   2276: istore_3
    //   2277: goto -> 2250
    // Exception table:
    //   from	to	target	type
    //   64	79	2004	java/lang/Exception
    //   64	79	2000	finally
    //   84	95	101	java/lang/Exception
    //   84	95	2000	finally
    //   119	129	1995	java/lang/Exception
    //   119	129	2000	finally
    //   133	149	1990	java/lang/Exception
    //   133	149	2168	finally
    //   153	169	1985	java/lang/Exception
    //   153	169	2168	finally
    //   173	181	1985	java/lang/Exception
    //   173	181	2168	finally
    //   185	193	1985	java/lang/Exception
    //   185	193	2168	finally
    //   197	203	1985	java/lang/Exception
    //   197	203	2168	finally
    //   207	213	1985	java/lang/Exception
    //   207	213	2168	finally
    //   217	223	1985	java/lang/Exception
    //   217	223	2168	finally
    //   227	236	1985	java/lang/Exception
    //   227	236	2168	finally
    //   240	249	1985	java/lang/Exception
    //   240	249	2168	finally
    //   253	263	1985	java/lang/Exception
    //   253	263	2168	finally
    //   267	277	1985	java/lang/Exception
    //   267	277	2168	finally
    //   281	291	1985	java/lang/Exception
    //   281	291	2168	finally
    //   295	309	1985	java/lang/Exception
    //   295	309	2168	finally
    //   313	318	1985	java/lang/Exception
    //   313	318	2168	finally
    //   327	342	356	java/lang/Exception
    //   327	342	2168	finally
    //   346	353	356	java/lang/Exception
    //   346	353	2168	finally
    //   368	383	1985	java/lang/Exception
    //   368	383	2168	finally
    //   387	394	1985	java/lang/Exception
    //   387	394	2168	finally
    //   398	404	1985	java/lang/Exception
    //   398	404	2168	finally
    //   419	428	1985	java/lang/Exception
    //   419	428	2168	finally
    //   437	445	356	java/lang/Exception
    //   437	445	2168	finally
    //   452	459	356	java/lang/Exception
    //   452	459	2168	finally
    //   463	471	356	java/lang/Exception
    //   463	471	2168	finally
    //   484	490	356	java/lang/Exception
    //   484	490	2168	finally
    //   494	502	356	java/lang/Exception
    //   494	502	2168	finally
    //   506	514	356	java/lang/Exception
    //   506	514	2168	finally
    //   518	526	356	java/lang/Exception
    //   518	526	2168	finally
    //   545	550	1985	java/lang/Exception
    //   545	550	2168	finally
    //   554	564	1985	java/lang/Exception
    //   554	564	2168	finally
    //   568	578	1985	java/lang/Exception
    //   568	578	2168	finally
    //   582	591	1985	java/lang/Exception
    //   582	591	2168	finally
    //   595	612	1985	java/lang/Exception
    //   595	612	2168	finally
    //   616	629	1985	java/lang/Exception
    //   616	629	2168	finally
    //   633	654	1985	java/lang/Exception
    //   633	654	2168	finally
    //   658	665	1985	java/lang/Exception
    //   658	665	2168	finally
    //   669	676	1985	java/lang/Exception
    //   669	676	2168	finally
    //   680	685	1985	java/lang/Exception
    //   680	685	2168	finally
    //   689	694	1985	java/lang/Exception
    //   689	694	2168	finally
    //   702	713	1985	java/lang/Exception
    //   702	713	2168	finally
    //   717	740	1985	java/lang/Exception
    //   717	740	2168	finally
    //   744	752	1985	java/lang/Exception
    //   744	752	2168	finally
    //   756	764	1985	java/lang/Exception
    //   756	764	2168	finally
    //   768	776	1985	java/lang/Exception
    //   768	776	2168	finally
    //   780	787	1985	java/lang/Exception
    //   780	787	2168	finally
    //   796	807	1762	java/lang/Exception
    //   796	807	2168	finally
    //   811	818	1762	java/lang/Exception
    //   811	818	2168	finally
    //   827	845	356	java/lang/Exception
    //   827	845	2168	finally
    //   849	861	1762	java/lang/Exception
    //   849	861	2168	finally
    //   865	883	1762	java/lang/Exception
    //   865	883	2168	finally
    //   887	895	1762	java/lang/Exception
    //   887	895	2168	finally
    //   913	919	1762	java/lang/Exception
    //   913	919	2168	finally
    //   923	930	1762	java/lang/Exception
    //   923	930	2168	finally
    //   939	956	974	java/lang/Exception
    //   939	956	2168	finally
    //   980	1001	1757	java/lang/Exception
    //   980	1001	2168	finally
    //   1012	1017	1757	java/lang/Exception
    //   1012	1017	2168	finally
    //   1026	1039	2257	java/lang/Exception
    //   1026	1039	2168	finally
    //   1049	1058	2257	java/lang/Exception
    //   1049	1058	2168	finally
    //   1062	1069	2257	java/lang/Exception
    //   1062	1069	2168	finally
    //   1073	1082	2257	java/lang/Exception
    //   1073	1082	2168	finally
    //   1089	1106	1818	java/lang/Exception
    //   1089	1106	2168	finally
    //   1113	1122	2257	java/lang/Exception
    //   1113	1122	2168	finally
    //   1126	1133	2257	java/lang/Exception
    //   1126	1133	2168	finally
    //   1137	1146	2257	java/lang/Exception
    //   1137	1146	2168	finally
    //   1153	1170	1818	java/lang/Exception
    //   1153	1170	2168	finally
    //   1180	1187	1818	java/lang/Exception
    //   1180	1187	2168	finally
    //   1194	1202	1818	java/lang/Exception
    //   1194	1202	2168	finally
    //   1220	1231	1818	java/lang/Exception
    //   1220	1231	2168	finally
    //   1238	1256	1818	java/lang/Exception
    //   1238	1256	2168	finally
    //   1263	1271	1818	java/lang/Exception
    //   1263	1271	2168	finally
    //   1278	1287	1818	java/lang/Exception
    //   1278	1287	2168	finally
    //   1305	1313	1818	java/lang/Exception
    //   1305	1313	2168	finally
    //   1320	1335	1818	java/lang/Exception
    //   1320	1335	2168	finally
    //   1342	1363	1818	java/lang/Exception
    //   1342	1363	2168	finally
    //   1370	1381	1818	java/lang/Exception
    //   1370	1381	2168	finally
    //   1388	1411	1818	java/lang/Exception
    //   1388	1411	2168	finally
    //   1421	1437	1818	java/lang/Exception
    //   1421	1437	2168	finally
    //   1444	1455	1818	java/lang/Exception
    //   1444	1455	2168	finally
    //   1474	1487	1818	java/lang/Exception
    //   1474	1487	2168	finally
    //   1505	1519	1818	java/lang/Exception
    //   1505	1519	2168	finally
    //   1563	1574	1818	java/lang/Exception
    //   1563	1574	2168	finally
    //   1586	1594	1818	java/lang/Exception
    //   1586	1594	2168	finally
    //   1601	1614	1818	java/lang/Exception
    //   1601	1614	2168	finally
    //   1621	1629	1818	java/lang/Exception
    //   1621	1629	2168	finally
    //   1644	1653	1818	java/lang/Exception
    //   1644	1653	2168	finally
    //   1660	1668	1818	java/lang/Exception
    //   1660	1668	2168	finally
    //   1675	1684	1818	java/lang/Exception
    //   1675	1684	2168	finally
    //   1702	1715	1818	java/lang/Exception
    //   1702	1715	2168	finally
    //   1722	1730	1818	java/lang/Exception
    //   1722	1730	2168	finally
    //   1737	1746	1818	java/lang/Exception
    //   1737	1746	2168	finally
    //   1781	1788	1976	java/lang/Exception
    //   1781	1788	2168	finally
    //   1800	1811	1818	java/lang/Exception
    //   1800	1811	2168	finally
    //   1849	1858	1961	java/lang/Exception
    //   1849	1858	2168	finally
    //   1880	1886	1961	java/lang/Exception
    //   1880	1886	2168	finally
    //   1900	1903	1961	java/lang/Exception
    //   1900	1903	2168	finally
    //   1925	1931	1961	java/lang/Exception
    //   1925	1931	2168	finally
    //   1945	1950	1961	java/lang/Exception
    //   1945	1950	2168	finally
    //   2031	2035	2168	finally
    //   2039	2059	2168	finally
    //   2063	2073	2168	finally
    //   2077	2083	2168	finally
    //   2087	2090	2168	finally
    //   2094	2100	2168	finally
    //   2104	2120	2168	finally
  }
  
  private String a(HttpURLConnection paramHttpURLConnection, String paramString) {
    return a(paramHttpURLConnection, paramString, "");
  }
  
  private String a(HttpURLConnection paramHttpURLConnection, String paramString1, String paramString2) {
    String str = paramHttpURLConnection.getHeaderField(paramString1);
    return (str == null) ? paramString2 : str.trim();
  }
  
  private void a(String paramString) {
    int i;
    boolean bool;
    boolean bool1 = paramString.contains("userip");
    byte b = 0;
    if (bool1 || paramString.contains("wlanuserip")) {
      i = 1;
    } else {
      i = 0;
    } 
    if (paramString.contains("wlanacip") || paramString.contains("wlanacname")) {
      bool = true;
    } else {
      bool = false;
    } 
    if (bool || i) {
      paramString = paramString.substring(paramString.lastIndexOf("?") + 1);
      this.d.setWlanIpName(paramString);
      String[] arrayOfString = paramString.split("&");
      for (i = b; i < arrayOfString.length; i++) {
        String str = arrayOfString[i];
        if (str.contains("wlanuserip")) {
          str = str.replaceAll("wlanuserip", "").replaceAll("=", "").replaceAll("\\s*", "").trim();
          if (!TextUtils.isEmpty(str))
            this.d.setWlanuserip(str); 
        } else if (str.contains("userip")) {
          str = str.replaceAll("userip", "").replaceAll("=", "").replaceAll("\\s*", "").trim();
          if (!TextUtils.isEmpty(str))
            this.d.setWlanuserip(str); 
        } else if (str.contains("wlanacip")) {
          str = str.replaceAll("wlanacip", "").replaceAll("=", "").replaceAll("\\s*", "").trim();
          if (!TextUtils.isEmpty(str))
            this.d.setWlanacip(str); 
        } else if (str.contains("wlanacname")) {
          str = str.replaceAll("wlanacname", "").replaceAll("=", "").replaceAll("\\s*", "").trim();
          if (!TextUtils.isEmpty(str))
            this.d.setWlanacip(str); 
        } 
      } 
    } 
  }
  
  private void b(HttpURLConnection paramHttpURLConnection, String paramString) {
    b(paramHttpURLConnection, paramString, paramString);
  }
  
  private void b(HttpURLConnection paramHttpURLConnection, String paramString1, String paramString2) {
    String str = paramHttpURLConnection.getHeaderField(paramString1);
    if (str != null) {
      str = str.trim();
      if (str.length() > 0) {
        JSONObject jSONObject = this.d.putConfig(new String[] { "head" });
        if (jSONObject != null)
          try {
            jSONObject.put(paramString2, str);
            ExLog.i(new String[] { paramString1, ": ", str });
            return;
          } catch (JSONException jSONException) {
            return;
          }  
      } 
    } 
  }
  
  private void c(HttpURLConnection paramHttpURLConnection, String paramString1, String paramString2) {
    if (!TextUtils.isEmpty(paramString2)) {
      paramHttpURLConnection.setRequestProperty(paramString1, paramString2);
      ExLog.i(new String[] { "  PROP ", paramString1, " = ", paramString2 });
    } 
  }
  
  private void d(HttpURLConnection paramHttpURLConnection, String paramString1, String paramString2) {
    c(paramHttpURLConnection, paramString1, this.d.getConfig(new String[] { "head" }).optString(paramString2, null));
  }
  
  public Result post(String paramString, byte[] paramArrayOfbyte) {
    return post(paramString, paramArrayOfbyte, 20);
  }
  
  public Result post(String paramString, byte[] paramArrayOfbyte, int paramInt) {
    return a(paramString, paramArrayOfbyte, paramInt, "utf-8", false, true);
  }
  
  public Result post(String paramString, byte[] paramArrayOfbyte, boolean paramBoolean) {
    return a(paramString, paramArrayOfbyte, 20, "utf-8", paramBoolean, true);
  }
  
  public Result post(String paramString, byte[] paramArrayOfbyte, boolean paramBoolean1, boolean paramBoolean2) {
    return a(paramString, paramArrayOfbyte, 20, "utf-8", paramBoolean1, paramBoolean2);
  }
  
  public Result query(String paramString) {
    return query(paramString, 20, "utf-8");
  }
  
  public Result query(String paramString1, int paramInt, String paramString2) {
    return a(paramString1, null, paramInt, paramString2, false, true);
  }
  
  public Result query(String paramString1, String paramString2) {
    return a(paramString1, null, 20, paramString2, false, true);
  }
  
  public class Result {
    private String b = null;
    
    private byte[] c = null;
    
    private int d = 0;
    
    private int e = 0;
    
    private String f = "";
    
    private String g = "";
    
    private String h = "";
    
    public Result(CdcHttpClient this$0) {}
    
    private boolean a() {
      return (this.d == 301 || this.d == 302 || this.d == 303 || this.d == 307);
    }
    
    public String getBody() {
      if (this.b == null)
        if (this.c != null) {
          try {
            this.b = new String(this.c, "UTF-8");
          } catch (UnsupportedEncodingException unsupportedEncodingException) {
            this.b = "";
          } 
        } else {
          this.b = "";
        }  
      return this.b;
    }
    
    public String getCdcCode() {
      return this.f;
    }
    
    public String getCdcSubCode() {
      return this.g;
    }
    
    public byte[] getData() {
      if (this.c == null)
        if (this.b != null) {
          try {
            this.c = this.b.getBytes("UTF-8");
          } catch (UnsupportedEncodingException unsupportedEncodingException) {
            this.c = new byte[0];
          } 
        } else {
          this.c = new byte[0];
        }  
      return this.c;
    }
    
    public String getExtraCode() {
      return this.h;
    }
    
    public int getHttpCode() {
      return this.d;
    }
    
    public boolean hasJump() {
      return (this.e > 0);
    }
    
    public boolean isFailCode() {
      return (this.d == 0 || !isOk());
    }
    
    public boolean isOk() {
      return (this.d >= 200 && this.d < 300);
    }
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\http\CdcHttpClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */