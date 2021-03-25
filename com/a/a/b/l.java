package com.a.a.b;

import com.a.a.e;
import java.util.Map;

public final class l {
  static {
    if ("SJIS".equalsIgnoreCase(a) || "EUC_JP".equalsIgnoreCase(a)) {
      bool = true;
    } else {
      bool = false;
    } 
    b = bool;
  }
  
  public static String a(byte[] paramArrayOfbyte, Map<e, ?> paramMap) {
    // Byte code:
    //   0: aload_0
    //   1: astore #29
    //   3: aload_1
    //   4: ifnull -> 26
    //   7: aload_1
    //   8: getstatic com/a/a/e.e : Lcom/a/a/e;
    //   11: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   16: checkcast java/lang/String
    //   19: astore_1
    //   20: aload_1
    //   21: ifnull -> 26
    //   24: aload_1
    //   25: areturn
    //   26: aload #29
    //   28: arraylength
    //   29: istore #27
    //   31: aload #29
    //   33: arraylength
    //   34: istore_2
    //   35: iconst_0
    //   36: istore #6
    //   38: iload_2
    //   39: iconst_3
    //   40: if_icmple -> 76
    //   43: aload #29
    //   45: iconst_0
    //   46: baload
    //   47: bipush #-17
    //   49: if_icmpne -> 76
    //   52: aload #29
    //   54: iconst_1
    //   55: baload
    //   56: bipush #-69
    //   58: if_icmpne -> 76
    //   61: aload #29
    //   63: iconst_2
    //   64: baload
    //   65: bipush #-65
    //   67: if_icmpne -> 76
    //   70: iconst_1
    //   71: istore #12
    //   73: goto -> 79
    //   76: iconst_0
    //   77: istore #12
    //   79: iconst_0
    //   80: istore_3
    //   81: iconst_0
    //   82: istore #14
    //   84: iconst_1
    //   85: istore #24
    //   87: iconst_1
    //   88: istore #5
    //   90: iconst_1
    //   91: istore #7
    //   93: iconst_0
    //   94: istore #8
    //   96: iconst_0
    //   97: istore #13
    //   99: iconst_0
    //   100: istore #25
    //   102: iconst_0
    //   103: istore #23
    //   105: iconst_0
    //   106: istore #22
    //   108: iconst_0
    //   109: istore #4
    //   111: iconst_0
    //   112: istore #10
    //   114: iconst_0
    //   115: istore #16
    //   117: iconst_0
    //   118: istore #15
    //   120: iload #14
    //   122: iload #27
    //   124: if_icmpge -> 822
    //   127: iload #24
    //   129: ifne -> 142
    //   132: iload #5
    //   134: ifne -> 142
    //   137: iload #7
    //   139: ifeq -> 822
    //   142: aload_0
    //   143: iload #14
    //   145: baload
    //   146: sipush #255
    //   149: iand
    //   150: istore #28
    //   152: iload #7
    //   154: istore #17
    //   156: iload #8
    //   158: istore_2
    //   159: iload #25
    //   161: istore #18
    //   163: iload #23
    //   165: istore #19
    //   167: iload #22
    //   169: istore #20
    //   171: iload #7
    //   173: ifeq -> 381
    //   176: iload #8
    //   178: ifle -> 235
    //   181: iload #28
    //   183: sipush #128
    //   186: iand
    //   187: ifne -> 211
    //   190: iload #8
    //   192: istore_2
    //   193: iconst_0
    //   194: istore #17
    //   196: iload #25
    //   198: istore #18
    //   200: iload #23
    //   202: istore #19
    //   204: iload #22
    //   206: istore #20
    //   208: goto -> 381
    //   211: iload #8
    //   213: iconst_1
    //   214: isub
    //   215: istore_2
    //   216: iload #7
    //   218: istore #17
    //   220: iload #25
    //   222: istore #18
    //   224: iload #23
    //   226: istore #19
    //   228: iload #22
    //   230: istore #20
    //   232: goto -> 381
    //   235: iload #7
    //   237: istore #17
    //   239: iload #8
    //   241: istore_2
    //   242: iload #25
    //   244: istore #18
    //   246: iload #23
    //   248: istore #19
    //   250: iload #22
    //   252: istore #20
    //   254: iload #28
    //   256: sipush #128
    //   259: iand
    //   260: ifeq -> 381
    //   263: iload #28
    //   265: bipush #64
    //   267: iand
    //   268: ifne -> 277
    //   271: iload #8
    //   273: istore_2
    //   274: goto -> 193
    //   277: iload #8
    //   279: iconst_1
    //   280: iadd
    //   281: istore_2
    //   282: iload #28
    //   284: bipush #32
    //   286: iand
    //   287: ifne -> 311
    //   290: iload #25
    //   292: iconst_1
    //   293: iadd
    //   294: istore #18
    //   296: iload #7
    //   298: istore #17
    //   300: iload #23
    //   302: istore #19
    //   304: iload #22
    //   306: istore #20
    //   308: goto -> 381
    //   311: iload_2
    //   312: iconst_1
    //   313: iadd
    //   314: istore_2
    //   315: iload #28
    //   317: bipush #16
    //   319: iand
    //   320: ifne -> 344
    //   323: iload #23
    //   325: iconst_1
    //   326: iadd
    //   327: istore #19
    //   329: iload #7
    //   331: istore #17
    //   333: iload #25
    //   335: istore #18
    //   337: iload #22
    //   339: istore #20
    //   341: goto -> 381
    //   344: iload_2
    //   345: iconst_1
    //   346: iadd
    //   347: istore #8
    //   349: iload #8
    //   351: istore_2
    //   352: iload #28
    //   354: bipush #8
    //   356: iand
    //   357: ifne -> 193
    //   360: iload #22
    //   362: iconst_1
    //   363: iadd
    //   364: istore #20
    //   366: iload #23
    //   368: istore #19
    //   370: iload #25
    //   372: istore #18
    //   374: iload #8
    //   376: istore_2
    //   377: iload #7
    //   379: istore #17
    //   381: iload #24
    //   383: istore #8
    //   385: iload #10
    //   387: istore #21
    //   389: iload #24
    //   391: ifeq -> 477
    //   394: iload #28
    //   396: bipush #127
    //   398: if_icmple -> 419
    //   401: iload #28
    //   403: sipush #160
    //   406: if_icmpge -> 419
    //   409: iconst_0
    //   410: istore #8
    //   412: iload #10
    //   414: istore #21
    //   416: goto -> 477
    //   419: iload #24
    //   421: istore #8
    //   423: iload #10
    //   425: istore #21
    //   427: iload #28
    //   429: sipush #159
    //   432: if_icmple -> 477
    //   435: iload #28
    //   437: sipush #192
    //   440: if_icmplt -> 467
    //   443: iload #28
    //   445: sipush #215
    //   448: if_icmpeq -> 467
    //   451: iload #24
    //   453: istore #8
    //   455: iload #10
    //   457: istore #21
    //   459: iload #28
    //   461: sipush #247
    //   464: if_icmpne -> 477
    //   467: iload #10
    //   469: iconst_1
    //   470: iadd
    //   471: istore #21
    //   473: iload #24
    //   475: istore #8
    //   477: iload_3
    //   478: istore #7
    //   480: iload #6
    //   482: istore #22
    //   484: iload #5
    //   486: istore #23
    //   488: iload #13
    //   490: istore #10
    //   492: iload #4
    //   494: istore #26
    //   496: iload #16
    //   498: istore #9
    //   500: iload #15
    //   502: istore #11
    //   504: iload #5
    //   506: ifeq -> 759
    //   509: iload #13
    //   511: ifle -> 600
    //   514: iload #28
    //   516: bipush #64
    //   518: if_icmplt -> 571
    //   521: iload #28
    //   523: bipush #127
    //   525: if_icmpeq -> 571
    //   528: iload #28
    //   530: sipush #252
    //   533: if_icmple -> 539
    //   536: goto -> 571
    //   539: iload #13
    //   541: iconst_1
    //   542: isub
    //   543: istore #10
    //   545: iload_3
    //   546: istore #7
    //   548: iload #6
    //   550: istore #22
    //   552: iload #5
    //   554: istore #23
    //   556: iload #4
    //   558: istore #26
    //   560: iload #16
    //   562: istore #9
    //   564: iload #15
    //   566: istore #11
    //   568: goto -> 759
    //   571: iconst_0
    //   572: istore #23
    //   574: iload_3
    //   575: istore #7
    //   577: iload #6
    //   579: istore #22
    //   581: iload #13
    //   583: istore #10
    //   585: iload #4
    //   587: istore #26
    //   589: iload #16
    //   591: istore #9
    //   593: iload #15
    //   595: istore #11
    //   597: goto -> 759
    //   600: iload #28
    //   602: sipush #128
    //   605: if_icmpeq -> 571
    //   608: iload #28
    //   610: sipush #160
    //   613: if_icmpeq -> 571
    //   616: iload #28
    //   618: sipush #239
    //   621: if_icmple -> 627
    //   624: goto -> 571
    //   627: iload #28
    //   629: sipush #160
    //   632: if_icmple -> 693
    //   635: iload #28
    //   637: sipush #224
    //   640: if_icmpge -> 693
    //   643: iload_3
    //   644: iconst_1
    //   645: iadd
    //   646: istore #7
    //   648: iload #16
    //   650: iconst_1
    //   651: iadd
    //   652: istore_3
    //   653: iload_3
    //   654: iload #4
    //   656: if_icmple -> 668
    //   659: iload_3
    //   660: istore #4
    //   662: iload #4
    //   664: istore_3
    //   665: goto -> 668
    //   668: iconst_0
    //   669: istore #11
    //   671: iload #6
    //   673: istore #22
    //   675: iload #5
    //   677: istore #23
    //   679: iload #13
    //   681: istore #10
    //   683: iload #4
    //   685: istore #26
    //   687: iload_3
    //   688: istore #9
    //   690: goto -> 759
    //   693: iload #28
    //   695: bipush #127
    //   697: if_icmple -> 747
    //   700: iload #13
    //   702: iconst_1
    //   703: iadd
    //   704: istore #10
    //   706: iload #15
    //   708: iconst_1
    //   709: iadd
    //   710: istore #11
    //   712: iload #11
    //   714: iload #6
    //   716: if_icmple -> 726
    //   719: iload #11
    //   721: istore #6
    //   723: goto -> 726
    //   726: iconst_0
    //   727: istore #9
    //   729: iload_3
    //   730: istore #7
    //   732: iload #6
    //   734: istore #22
    //   736: iload #5
    //   738: istore #23
    //   740: iload #4
    //   742: istore #26
    //   744: goto -> 759
    //   747: iconst_0
    //   748: istore #9
    //   750: iload_3
    //   751: istore #7
    //   753: iload #9
    //   755: istore_3
    //   756: goto -> 668
    //   759: iload #14
    //   761: iconst_1
    //   762: iadd
    //   763: istore #14
    //   765: iload #7
    //   767: istore_3
    //   768: iload #22
    //   770: istore #6
    //   772: iload #8
    //   774: istore #24
    //   776: iload #23
    //   778: istore #5
    //   780: iload #17
    //   782: istore #7
    //   784: iload_2
    //   785: istore #8
    //   787: iload #10
    //   789: istore #13
    //   791: iload #18
    //   793: istore #25
    //   795: iload #19
    //   797: istore #23
    //   799: iload #20
    //   801: istore #22
    //   803: iload #26
    //   805: istore #4
    //   807: iload #21
    //   809: istore #10
    //   811: iload #9
    //   813: istore #16
    //   815: iload #11
    //   817: istore #15
    //   819: goto -> 120
    //   822: iload #7
    //   824: istore_2
    //   825: iload #7
    //   827: ifeq -> 840
    //   830: iload #7
    //   832: istore_2
    //   833: iload #8
    //   835: ifle -> 840
    //   838: iconst_0
    //   839: istore_2
    //   840: iload #5
    //   842: ifeq -> 856
    //   845: iload #13
    //   847: ifle -> 856
    //   850: iconst_0
    //   851: istore #5
    //   853: goto -> 856
    //   856: iload_2
    //   857: ifeq -> 879
    //   860: iload #12
    //   862: ifne -> 876
    //   865: iload #25
    //   867: iload #23
    //   869: iadd
    //   870: iload #22
    //   872: iadd
    //   873: ifle -> 879
    //   876: ldc 'UTF8'
    //   878: areturn
    //   879: iload #5
    //   881: ifeq -> 905
    //   884: getstatic com/a/a/b/l.b : Z
    //   887: ifne -> 902
    //   890: iload #4
    //   892: iconst_3
    //   893: if_icmpge -> 902
    //   896: iload #6
    //   898: iconst_3
    //   899: if_icmplt -> 905
    //   902: ldc 'SJIS'
    //   904: areturn
    //   905: iload #24
    //   907: ifeq -> 942
    //   910: iload #5
    //   912: ifeq -> 942
    //   915: iload #4
    //   917: iconst_2
    //   918: if_icmpne -> 926
    //   921: iload_3
    //   922: iconst_2
    //   923: if_icmpeq -> 936
    //   926: iload #10
    //   928: bipush #10
    //   930: imul
    //   931: iload #27
    //   933: if_icmplt -> 939
    //   936: ldc 'SJIS'
    //   938: areturn
    //   939: ldc 'ISO8859_1'
    //   941: areturn
    //   942: iload #24
    //   944: ifeq -> 950
    //   947: ldc 'ISO8859_1'
    //   949: areturn
    //   950: iload #5
    //   952: ifeq -> 958
    //   955: ldc 'SJIS'
    //   957: areturn
    //   958: iload_2
    //   959: ifeq -> 965
    //   962: ldc 'UTF8'
    //   964: areturn
    //   965: getstatic com/a/a/b/l.a : Ljava/lang/String;
    //   968: areturn
  }
  
  static {
    boolean bool;
  }
  
  private static final String a = System.getProperty("file.encoding");
  
  private static final boolean b;
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\b\l.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */