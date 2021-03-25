package com.a.a.f.b;

import com.a.a.i;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class b {
  private static final float[] a = new float[com.a.a.f.a.a.a.length * 8];
  
  private final com.a.a.b.b b;
  
  private final int c;
  
  private final int d;
  
  static {
    int j = 0;
    int i = 0;
    while (j < com.a.a.f.a.a.a.length) {
      int n = com.a.a.f.a.a.a[j];
      int k = n & 0x1;
      int m = 0;
      while (m < 8) {
        float f = 0.0F;
        while (true) {
          int i1 = n & 0x1;
          if (i1 == k) {
            f++;
            n >>= 1;
            continue;
          } 
          arrayOfFloat[j][8 - m - 1] = f / 17.0F;
          m++;
          k = i1;
        } 
      } 
      for (k = 0; k < 8; k++) {
        a[i] = arrayOfFloat[j][k];
        i++;
      } 
      j++;
    } 
  }
  
  public b(com.a.a.b.b paramb, int paramInt) {
    this.b = paramb;
    this.c = paramInt / 17;
    this.d = paramInt;
  }
  
  private static int a(int paramInt) {
    if (paramInt == 0)
      return -1; 
    int i = 0;
    byte b2 = 0;
    boolean bool = true;
    for (byte b1 = 0; i < 17; b1 = b3) {
      byte b3;
      byte b4;
      boolean bool1;
      if ((1 << i & paramInt) > 0) {
        bool1 = bool;
        b3 = b1;
        if (!bool) {
          b3 = b1 + 1;
          bool1 = true;
        } 
        if (b3 % 2 == 0) {
          b4 = b2 + 1;
        } else {
          b4 = b2 - 1;
        } 
      } else {
        b4 = b2;
        bool1 = bool;
        b3 = b1;
        if (bool) {
          bool1 = false;
          b3 = b1;
          b4 = b2;
        } 
      } 
      i++;
      b2 = b4;
      bool = bool1;
    } 
    return (b2 + 9) % 9;
  }
  
  private int a(List<List<Integer>> paramList, List<Integer> paramList1) {
    paramList1.clear();
    HashMap<Object, Object> hashMap1 = new HashMap<Object, Object>();
    HashMap<Object, Object> hashMap2 = new HashMap<Object, Object>();
    HashMap<Object, Object> hashMap3 = new HashMap<Object, Object>();
    int i = 0;
    int j = -1;
    while (true) {
      List<List<Integer>> list = paramList;
      int k = i + 2;
      if (k < paramList.size()) {
        byte b1;
        byte b2;
        byte b3;
        hashMap3.clear();
        if (((Integer)((List<Integer>)list.get(i)).get(0)).intValue() != 0) {
          m = com.a.a.f.a.a.a(((Integer)((List<Integer>)list.get(i)).get(0)).intValue());
        } else {
          m = -1;
        } 
        int n = i + 1;
        if (((Integer)((List<Integer>)list.get(n)).get(0)).intValue() != 0) {
          b1 = com.a.a.f.a.a.a(((Integer)((List<Integer>)list.get(n)).get(0)).intValue());
        } else {
          b1 = -1;
        } 
        if (((Integer)((List<Integer>)list.get(k)).get(0)).intValue() != 0) {
          b2 = com.a.a.f.a.a.a(((Integer)((List<Integer>)list.get(k)).get(0)).intValue());
        } else {
          b2 = -1;
        } 
        if (((Integer)((List<Integer>)list.get(i)).get(((List)list.get(i)).size() - 1)).intValue() != 0) {
          b3 = com.a.a.f.a.a.a(((Integer)((List<Integer>)list.get(i)).get(((List)list.get(i)).size() - 1)).intValue());
        } else {
          b3 = -1;
        } 
        if (((Integer)((List<Integer>)list.get(n)).get(((List)list.get(n)).size() - 1)).intValue() != 0) {
          n = com.a.a.f.a.a.a(((Integer)((List<Integer>)list.get(n)).get(((List)list.get(n)).size() - 1)).intValue());
        } else {
          n = -1;
        } 
        if (((Integer)((List<Integer>)list.get(k)).get(((List)list.get(k)).size() - 1)).intValue() != 0) {
          k = com.a.a.f.a.a.a(((Integer)((List<Integer>)list.get(k)).get(((List)list.get(k)).size() - 1)).intValue());
        } else {
          k = -1;
        } 
        if (m != -1 && b1 != -1) {
          int i2 = b1 % 30;
          int i1 = m % 30 * 3 + i2 % 3;
          i2 /= 3;
          hashMap1.put(Integer.valueOf(i1), Integer.valueOf(((Integer)a((Integer)hashMap1.get(Integer.valueOf(i1)), Integer.valueOf(0))).intValue() + 1));
          hashMap2.put(Integer.valueOf(i2), Integer.valueOf(((Integer)a((Integer)hashMap2.get(Integer.valueOf(i2)), Integer.valueOf(0))).intValue() + 1));
        } 
        if (n != -1 && k != -1) {
          int i2 = k % 30;
          int i1 = n % 30 * 3 + i2 % 3;
          i2 /= 3;
          hashMap1.put(Integer.valueOf(i1), Integer.valueOf(((Integer)a((Integer)hashMap1.get(Integer.valueOf(i1)), Integer.valueOf(0))).intValue() + 1));
          hashMap2.put(Integer.valueOf(i2), Integer.valueOf(((Integer)a((Integer)hashMap2.get(Integer.valueOf(i2)), Integer.valueOf(0))).intValue() + 1));
        } 
        if (m != -1) {
          m /= 30;
          hashMap3.put(Integer.valueOf(m), Integer.valueOf(((Integer)a((Integer)hashMap3.get(Integer.valueOf(m)), Integer.valueOf(0))).intValue() + 1));
        } 
        if (b1 != -1) {
          m = b1 / 30;
          hashMap3.put(Integer.valueOf(m), Integer.valueOf(((Integer)a((Integer)hashMap3.get(Integer.valueOf(m)), Integer.valueOf(0))).intValue() + 1));
        } 
        if (b2 != -1) {
          m = b2 / 30;
          hashMap3.put(Integer.valueOf(m), Integer.valueOf(((Integer)a((Integer)hashMap3.get(Integer.valueOf(m)), Integer.valueOf(0))).intValue() + 1));
        } 
        if (b3 != -1) {
          m = b3 / 30;
          hashMap3.put(Integer.valueOf(m), Integer.valueOf(((Integer)a((Integer)hashMap3.get(Integer.valueOf(m)), Integer.valueOf(0))).intValue() + 1));
        } 
        if (n != -1) {
          m = n / 30;
          hashMap3.put(Integer.valueOf(m), Integer.valueOf(((Integer)a((Integer)hashMap3.get(Integer.valueOf(m)), Integer.valueOf(0))).intValue() + 1));
        } 
        if (k != -1) {
          m = k / 30;
          hashMap3.put(Integer.valueOf(m), Integer.valueOf(((Integer)a((Integer)hashMap3.get(Integer.valueOf(m)), Integer.valueOf(0))).intValue() + 1));
        } 
        int m = a((Map)hashMap3).b();
        if (++j < m)
          while (j < m) {
            paramList1.add(Integer.valueOf(i));
            paramList1.add(Integer.valueOf(i));
            paramList1.add(Integer.valueOf(i));
            j++;
          }  
        i += 3;
        j = m;
        continue;
      } 
      for (i = 0; i < paramList1.size(); i++) {
        list = new ArrayList<List<Integer>>();
        for (j = 0; j < this.c; j++)
          list.add(Integer.valueOf(0)); 
        paramList.add(((Integer)paramList1.get(i)).intValue() + i, list);
      } 
      return a((Map)hashMap1).b() + 1;
    } 
  }
  
  private static com.a.a.b.b a(List<List<Integer>> paramList, int paramInt1, int paramInt2) {
    com.a.a.b.b b1 = new com.a.a.b.b(paramInt1, paramInt2);
    for (paramInt1 = 0; paramInt1 < paramList.size(); paramInt1++) {
      for (paramInt2 = 0; paramInt2 < ((List)paramList.get(paramInt1)).size(); paramInt2++) {
        for (int i = 0; i < 17; i++) {
          if ((1 << 17 - i - 1 & ((Integer)((List<Integer>)paramList.get(paramInt1)).get(paramInt2)).intValue()) > 0)
            b1.b(paramInt2 * 17 + i, paramInt1); 
        } 
      } 
    } 
    return b1;
  }
  
  private static a a(Map<Integer, Integer> paramMap) {
    a a = new a();
    Iterator<Map.Entry> iterator = paramMap.entrySet().iterator();
    int i = 0;
    while (iterator.hasNext()) {
      Map.Entry entry = iterator.next();
      if (((Integer)entry.getValue()).intValue() > i) {
        i = ((Integer)entry.getValue()).intValue();
        a.a(((Integer)entry.getKey()).intValue());
        a.a(false);
        continue;
      } 
      if (((Integer)entry.getValue()).intValue() == i)
        a.a(true); 
    } 
    return a;
  }
  
  private static <T> T a(T paramT1, T paramT2) {
    T t = paramT1;
    if (paramT1 == null)
      t = paramT2; 
    return t;
  }
  
  private List<Integer> a(List<List<Integer>> paramList) {
    ArrayList<Integer> arrayList = new ArrayList();
    if (paramList.size() > 1)
      for (int j = 0; j < paramList.size() - 1; j = i1) {
        int i1;
        int m = 0;
        int k = -1;
        while (m < ((List)paramList.get(j)).size() && k == -1) {
          int i2 = a(((Integer)((List<Integer>)paramList.get(j)).get(m)).intValue());
          if (i2 != -1)
            k = i2; 
          m++;
        } 
        if (j == 0 && k > 0) {
          arrayList.add(Integer.valueOf(0));
          if (k > 3)
            arrayList.add(Integer.valueOf(0)); 
        } 
        m = 0;
        int n = -1;
        while (true) {
          i1 = j + 1;
          if (m < ((List)paramList.get(i1)).size() && n == -1) {
            i1 = a(((Integer)((List<Integer>)paramList.get(i1)).get(m)).intValue());
            if (i1 != -1)
              n = i1; 
            m++;
            continue;
          } 
          break;
        } 
        if ((k + 3) % 9 != n && k != -1 && n != -1) {
          arrayList.add(Integer.valueOf(i1));
          if (k == n)
            arrayList.add(Integer.valueOf(i1)); 
        } 
      }  
    for (int i = 0; i < arrayList.size(); i++) {
      ArrayList<Integer> arrayList1 = new ArrayList();
      for (int j = 0; j < this.c; j++)
        arrayList1.add(Integer.valueOf(0)); 
      paramList.add(((Integer)arrayList.get(i)).intValue() + i, arrayList1);
    } 
    return arrayList;
  }
  
  private List<List<Map<Integer, Integer>>> a(int[][] paramArrayOfint1, int[][] paramArrayOfint2) {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore #11
    //   9: aload #11
    //   11: new java/util/ArrayList
    //   14: dup
    //   15: invokespecial <init> : ()V
    //   18: invokeinterface add : (Ljava/lang/Object;)Z
    //   23: pop
    //   24: aload #11
    //   26: iconst_0
    //   27: invokeinterface get : (I)Ljava/lang/Object;
    //   32: checkcast java/util/List
    //   35: aload_0
    //   36: getfield c : I
    //   39: invokestatic b : (Ljava/util/List;I)V
    //   42: new java/util/HashMap
    //   45: dup
    //   46: invokespecial <init> : ()V
    //   49: astore #12
    //   51: iconst_0
    //   52: istore #7
    //   54: iconst_m1
    //   55: istore_3
    //   56: iconst_0
    //   57: istore #5
    //   59: iload #7
    //   61: aload_1
    //   62: arraylength
    //   63: if_icmpge -> 805
    //   66: aload #12
    //   68: invokeinterface clear : ()V
    //   73: iconst_0
    //   74: istore #4
    //   76: iload #4
    //   78: aload_1
    //   79: iload #7
    //   81: aaload
    //   82: arraylength
    //   83: if_icmpge -> 159
    //   86: aload_2
    //   87: iload #7
    //   89: aaload
    //   90: iload #4
    //   92: iaload
    //   93: iconst_m1
    //   94: if_icmpeq -> 150
    //   97: aload #12
    //   99: aload_2
    //   100: iload #7
    //   102: aaload
    //   103: iload #4
    //   105: iaload
    //   106: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   109: aload #12
    //   111: aload_2
    //   112: iload #7
    //   114: aaload
    //   115: iload #4
    //   117: iaload
    //   118: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   121: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   126: iconst_0
    //   127: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   130: invokestatic a : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   133: checkcast java/lang/Integer
    //   136: invokevirtual intValue : ()I
    //   139: iconst_1
    //   140: iadd
    //   141: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   144: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   149: pop
    //   150: iload #4
    //   152: iconst_1
    //   153: iadd
    //   154: istore #4
    //   156: goto -> 76
    //   159: iload_3
    //   160: istore #8
    //   162: iload #5
    //   164: istore #6
    //   166: aload #12
    //   168: invokeinterface isEmpty : ()Z
    //   173: ifne -> 789
    //   176: aload #12
    //   178: invokestatic a : (Ljava/util/Map;)Lcom/a/a/f/b/b$a;
    //   181: astore #13
    //   183: aload #13
    //   185: invokevirtual a : ()Z
    //   188: istore #10
    //   190: aload #13
    //   192: invokevirtual b : ()I
    //   195: istore #6
    //   197: iload #10
    //   199: ifeq -> 205
    //   202: iload_3
    //   203: istore #6
    //   205: iload_3
    //   206: iconst_3
    //   207: iadd
    //   208: bipush #9
    //   210: irem
    //   211: istore #9
    //   213: iload #6
    //   215: istore #4
    //   217: iload #6
    //   219: iload #9
    //   221: if_icmpeq -> 236
    //   224: iload #6
    //   226: istore #4
    //   228: iload_3
    //   229: iconst_m1
    //   230: if_icmpeq -> 236
    //   233: iload_3
    //   234: istore #4
    //   236: iload #4
    //   238: ifne -> 246
    //   241: iload_3
    //   242: iconst_m1
    //   243: if_icmpeq -> 258
    //   246: iload_3
    //   247: istore #8
    //   249: iload #5
    //   251: istore #6
    //   253: iload_3
    //   254: iconst_m1
    //   255: if_icmpeq -> 789
    //   258: iload #5
    //   260: istore #6
    //   262: iload #4
    //   264: iload #9
    //   266: if_icmpne -> 340
    //   269: iload #5
    //   271: istore #6
    //   273: iload_3
    //   274: iconst_m1
    //   275: if_icmpeq -> 340
    //   278: iload #5
    //   280: iconst_1
    //   281: iadd
    //   282: istore #5
    //   284: aload #11
    //   286: invokeinterface size : ()I
    //   291: istore #8
    //   293: iload #5
    //   295: iconst_1
    //   296: iadd
    //   297: istore #9
    //   299: iload #5
    //   301: istore #6
    //   303: iload #8
    //   305: iload #9
    //   307: if_icmpge -> 340
    //   310: aload #11
    //   312: iload #9
    //   314: invokestatic a : (Ljava/util/List;I)V
    //   317: aload #11
    //   319: iload #5
    //   321: invokeinterface get : (I)Ljava/lang/Object;
    //   326: checkcast java/util/List
    //   329: aload_0
    //   330: getfield c : I
    //   333: invokestatic b : (Ljava/util/List;I)V
    //   336: iload #5
    //   338: istore #6
    //   340: iload #6
    //   342: istore #5
    //   344: iload #4
    //   346: iload_3
    //   347: bipush #6
    //   349: iadd
    //   350: bipush #9
    //   352: irem
    //   353: if_icmpne -> 422
    //   356: iload #6
    //   358: istore #5
    //   360: iload_3
    //   361: iconst_m1
    //   362: if_icmpeq -> 422
    //   365: iload #6
    //   367: iconst_2
    //   368: iadd
    //   369: istore_3
    //   370: aload #11
    //   372: invokeinterface size : ()I
    //   377: istore #6
    //   379: iload_3
    //   380: iconst_1
    //   381: iadd
    //   382: istore #8
    //   384: iload_3
    //   385: istore #5
    //   387: iload #6
    //   389: iload #8
    //   391: if_icmpge -> 422
    //   394: aload #11
    //   396: iload #8
    //   398: invokestatic a : (Ljava/util/List;I)V
    //   401: aload #11
    //   403: iload_3
    //   404: invokeinterface get : (I)Ljava/lang/Object;
    //   409: checkcast java/util/List
    //   412: aload_0
    //   413: getfield c : I
    //   416: invokestatic b : (Ljava/util/List;I)V
    //   419: iload_3
    //   420: istore #5
    //   422: iconst_0
    //   423: istore_3
    //   424: iload_3
    //   425: aload_1
    //   426: iload #7
    //   428: aaload
    //   429: arraylength
    //   430: if_icmpge -> 781
    //   433: aload_2
    //   434: iload #7
    //   436: aaload
    //   437: iload_3
    //   438: iaload
    //   439: iconst_m1
    //   440: if_icmpeq -> 774
    //   443: aload_2
    //   444: iload #7
    //   446: aaload
    //   447: iload_3
    //   448: iaload
    //   449: iload #4
    //   451: if_icmpne -> 531
    //   454: aload #11
    //   456: iload #5
    //   458: invokeinterface get : (I)Ljava/lang/Object;
    //   463: checkcast java/util/List
    //   466: iload_3
    //   467: invokeinterface get : (I)Ljava/lang/Object;
    //   472: checkcast java/util/Map
    //   475: astore #13
    //   477: aload #13
    //   479: aload_1
    //   480: iload #7
    //   482: aaload
    //   483: iload_3
    //   484: iaload
    //   485: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   488: aload #13
    //   490: aload_1
    //   491: iload #7
    //   493: aaload
    //   494: iload_3
    //   495: iaload
    //   496: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   499: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   504: iconst_0
    //   505: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   508: invokestatic a : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   511: checkcast java/lang/Integer
    //   514: invokevirtual intValue : ()I
    //   517: iconst_1
    //   518: iadd
    //   519: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   522: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   527: pop
    //   528: goto -> 774
    //   531: aload_2
    //   532: iload #7
    //   534: aaload
    //   535: iload_3
    //   536: iaload
    //   537: iload #4
    //   539: iconst_3
    //   540: iadd
    //   541: bipush #9
    //   543: irem
    //   544: if_icmpne -> 676
    //   547: aload #11
    //   549: invokeinterface size : ()I
    //   554: istore #6
    //   556: iload #5
    //   558: iconst_2
    //   559: iadd
    //   560: istore #8
    //   562: iload #6
    //   564: iload #8
    //   566: if_icmpge -> 597
    //   569: aload #11
    //   571: iload #8
    //   573: invokestatic a : (Ljava/util/List;I)V
    //   576: aload #11
    //   578: iload #5
    //   580: iconst_1
    //   581: iadd
    //   582: invokeinterface get : (I)Ljava/lang/Object;
    //   587: checkcast java/util/List
    //   590: aload_0
    //   591: getfield c : I
    //   594: invokestatic b : (Ljava/util/List;I)V
    //   597: aload #11
    //   599: iload #5
    //   601: iconst_1
    //   602: iadd
    //   603: invokeinterface get : (I)Ljava/lang/Object;
    //   608: checkcast java/util/List
    //   611: iload_3
    //   612: invokeinterface get : (I)Ljava/lang/Object;
    //   617: checkcast java/util/Map
    //   620: astore #13
    //   622: aload #13
    //   624: aload_1
    //   625: iload #7
    //   627: aaload
    //   628: iload_3
    //   629: iaload
    //   630: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   633: aload #13
    //   635: aload_1
    //   636: iload #7
    //   638: aaload
    //   639: iload_3
    //   640: iaload
    //   641: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   644: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   649: iconst_0
    //   650: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   653: invokestatic a : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   656: checkcast java/lang/Integer
    //   659: invokevirtual intValue : ()I
    //   662: iconst_1
    //   663: iadd
    //   664: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   667: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   672: pop
    //   673: goto -> 774
    //   676: aload_2
    //   677: iload #7
    //   679: aaload
    //   680: iload_3
    //   681: iaload
    //   682: iload #4
    //   684: bipush #6
    //   686: iadd
    //   687: bipush #9
    //   689: irem
    //   690: if_icmpne -> 774
    //   693: iload #5
    //   695: ifle -> 774
    //   698: aload #11
    //   700: iload #5
    //   702: iconst_1
    //   703: isub
    //   704: invokeinterface get : (I)Ljava/lang/Object;
    //   709: checkcast java/util/List
    //   712: iload_3
    //   713: invokeinterface get : (I)Ljava/lang/Object;
    //   718: checkcast java/util/Map
    //   721: astore #13
    //   723: aload #13
    //   725: aload_1
    //   726: iload #7
    //   728: aaload
    //   729: iload_3
    //   730: iaload
    //   731: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   734: aload #13
    //   736: aload_1
    //   737: iload #7
    //   739: aaload
    //   740: iload_3
    //   741: iaload
    //   742: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   745: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   750: iconst_0
    //   751: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   754: invokestatic a : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   757: checkcast java/lang/Integer
    //   760: invokevirtual intValue : ()I
    //   763: iconst_1
    //   764: iadd
    //   765: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   768: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   773: pop
    //   774: iload_3
    //   775: iconst_1
    //   776: iadd
    //   777: istore_3
    //   778: goto -> 424
    //   781: iload #5
    //   783: istore #6
    //   785: iload #4
    //   787: istore #8
    //   789: iload #7
    //   791: iconst_1
    //   792: iadd
    //   793: istore #7
    //   795: iload #8
    //   797: istore_3
    //   798: iload #6
    //   800: istore #5
    //   802: goto -> 59
    //   805: aload #11
    //   807: areturn
  }
  
  private static void a(List<List<Map<Integer, Integer>>> paramList, int paramInt) {
    int i;
    for (i = paramInt; i < paramList.size(); i++)
      paramList.remove(i); 
    for (i = paramList.size(); i < paramInt; i++)
      paramList.add(new ArrayList<Map<Integer, Integer>>()); 
  }
  
  private void a(int[][] paramArrayOfint1, int[][] paramArrayOfint2, List<Float> paramList) {
    if (this.c > paramList.size())
      throw i.a(); 
    int i = 0;
    while (i < this.b.e()) {
      paramArrayOfint1[i] = new int[this.c];
      paramArrayOfint2[i] = new int[this.c];
      Arrays.fill(paramArrayOfint2[i], 0, (paramArrayOfint2[i]).length, -1);
      ArrayList<Integer> arrayList = new ArrayList();
      int k = 2;
      arrayList.add(Integer.valueOf(2));
      int m;
      for (m = 1; k < this.b.d(); m = i1) {
        int i1;
        if (this.b.a(k, i)) {
          i1 = m;
          if (!m) {
            arrayList.add(Integer.valueOf(0));
            i1 = 1;
          } 
        } else {
          i1 = m;
          if (m) {
            arrayList.add(Integer.valueOf(0));
            i1 = 0;
          } 
        } 
        m = arrayList.size() - 1;
        arrayList.set(m, Integer.valueOf(((Integer)arrayList.get(m)).intValue() + 1));
        k++;
      } 
      int[] arrayOfInt = new int[this.c];
      arrayOfInt[0] = 0;
      int j = 0;
      int n = 1;
      m = 0;
      while (j < arrayList.size() && n < this.c) {
        int i1 = m + ((Integer)arrayList.get(j)).intValue();
        k = j;
        int i2 = n;
        m = i1;
        if (i1 > ((Float)paramList.get(n - 1)).floatValue()) {
          k = j;
          if (j % 2 == 1)
            k = j + 1; 
          m = i1;
          if (k < arrayList.size())
            m = ((Integer)arrayList.get(k)).intValue(); 
          arrayOfInt[n] = k;
          i2 = n + 1;
        } 
        j = k + 1;
        n = i2;
      } 
      float[][] arrayOfFloat = (float[][])Array.newInstance(float.class, new int[] { this.c, 8 });
      j = 0;
      while (true) {
        m = 0;
        if (j < this.c) {
          n = arrayOfInt[j];
          if (j == this.c - 1) {
            k = arrayList.size();
          } else {
            k = arrayOfInt[j + 1];
          } 
          int i1 = k - n;
          if (i1 >= 7 && i1 <= 9) {
            float f1 = 0.0F;
            for (k = m; k < Math.min(8, i1); k++)
              f1 += ((Integer)arrayList.get(n + k)).intValue(); 
            if (i1 == 7) {
              for (k = 0; k < i1; k++)
                arrayOfFloat[j][k] = ((Integer)arrayList.get(n + k)).intValue() / ((Float)paramList.get(j)).floatValue(); 
              arrayOfFloat[j][7] = (((Float)paramList.get(j)).floatValue() - f1) / ((Float)paramList.get(j)).floatValue();
            } else {
              for (k = 0; k < (arrayOfFloat[j]).length; k++)
                arrayOfFloat[j][k] = ((Integer)arrayList.get(n + k)).intValue() / f1; 
            } 
            k = 0;
            m = 0;
            float f2;
            for (f2 = Float.MAX_VALUE; k < com.a.a.f.a.a.a.length; f2 = f) {
              n = 0;
              f1 = 0.0F;
              while (n < 8) {
                float f3 = a[k * 8 + n] - arrayOfFloat[j][n];
                f1 += f3 * f3;
                n++;
              } 
              float f = f2;
              if (f1 < f2) {
                m = com.a.a.f.a.a.a[k];
                f = f1;
              } 
              k++;
            } 
            paramArrayOfint1[i][j] = m;
            paramArrayOfint2[i][j] = a(m);
          } 
          j++;
          continue;
        } 
        i++;
      } 
    } 
  }
  
  private List<Float> b() {
    float f1;
    if (this.c > 0) {
      f1 = this.b.d() / this.c;
    } else {
      f1 = this.b.d();
    } 
    ArrayList<Float> arrayList = new ArrayList();
    int[] arrayOfInt = new int[this.b.d()];
    int i = 2;
    int j = 0;
    int k;
    for (k = 1; i < this.b.d(); k = m) {
      int n;
      int m;
      for (m = 0; m < this.b.e(); m++) {
        if (this.b.a(i, m))
          arrayOfInt[i] = arrayOfInt[i] + 1; 
      } 
      if (arrayOfInt[i] == this.b.e()) {
        n = j;
        m = k;
        if (!k) {
          float f = (i - j);
          double d1 = f;
          double d2 = f1;
          if (d1 > 0.75D * d2) {
            while (f > d2 * 1.5D) {
              arrayList.add(Float.valueOf(f1));
              f -= f1;
            } 
            arrayList.add(Float.valueOf(f));
            j = i;
            k = 1;
          } 
          n = j;
          m = k;
        } 
      } else {
        n = j;
        m = k;
        if (k) {
          m = 0;
          n = j;
        } 
      } 
      i++;
      j = n;
    } 
    float f2;
    for (f2 = (this.b.d() - j); f2 > f1 * 1.5D; f2 -= f1)
      arrayList.add(Float.valueOf(f1)); 
    arrayList.add(Float.valueOf(f2));
    return arrayList;
  }
  
  private static void b(List<Map<Integer, Integer>> paramList, int paramInt) {
    int i;
    for (i = paramInt; i < paramList.size(); i++)
      paramList.remove(i); 
    for (i = paramList.size(); i < paramInt; i++)
      paramList.add(new HashMap<Integer, Integer>()); 
  }
  
  private static void c(List<List<Integer>> paramList, int paramInt) {
    int i;
    for (i = paramInt; i < paramList.size(); i++)
      paramList.remove(i); 
    for (i = paramList.size(); i < paramInt; i++)
      paramList.add(new ArrayList<Integer>()); 
  }
  
  private static void d(List<Integer> paramList, int paramInt) {
    int i;
    for (i = paramInt; i < paramList.size(); i++)
      paramList.remove(i); 
    for (i = paramList.size(); i < paramInt; i++)
      paramList.add(Integer.valueOf(0)); 
  }
  
  public com.a.a.b.b a() {
    List<Float> list = b();
    int[][] arrayOfInt1 = new int[this.b.e()][];
    int[][] arrayOfInt2 = new int[this.b.e()][];
    a(arrayOfInt1, arrayOfInt2, list);
    list = (List)a(arrayOfInt1, arrayOfInt2);
    ArrayList<List<Integer>> arrayList = new ArrayList();
    c(arrayList, list.size());
    for (int i = 0; i < list.size(); i++) {
      d(arrayList.get(i), ((List)list.get(i)).size());
      for (int j = 0; j < ((List)list.get(i)).size(); j++) {
        if (!((Map)((List<Map>)list.get(i)).get(j)).isEmpty())
          ((List<Integer>)arrayList.get(i)).set(j, Integer.valueOf(a(((List<Map<Integer, Integer>>)list.get(i)).get(j)).b())); 
      } 
    } 
    c(arrayList, a(arrayList, a(arrayList)));
    return a(arrayList, this.d, arrayList.size());
  }
  
  static {
    float[][] arrayOfFloat = (float[][])Array.newInstance(float.class, new int[] { com.a.a.f.a.a.a.length, 8 });
  }
  
  private static class a {
    private boolean a;
    
    private int b;
    
    private a() {}
    
    void a(int param1Int) {
      this.b = param1Int;
    }
    
    void a(boolean param1Boolean) {
      this.a = param1Boolean;
    }
    
    boolean a() {
      return this.a;
    }
    
    int b() {
      return this.b;
    }
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\f\b\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */