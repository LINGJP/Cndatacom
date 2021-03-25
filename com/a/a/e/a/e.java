package com.a.a.e.a;

import com.a.a.a;
import com.a.a.b.a;
import com.a.a.i;
import com.a.a.m;
import com.a.a.o;
import com.a.a.p;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class e extends a {
  private static final int[] a = new int[] { 1, 10, 34, 70, 126 };
  
  private static final int[] b = new int[] { 4, 20, 48, 81 };
  
  private static final int[] c = new int[] { 0, 161, 961, 2015, 2715 };
  
  private static final int[] d = new int[] { 0, 336, 1036, 1516 };
  
  private static final int[] e = new int[] { 8, 6, 4, 3, 1 };
  
  private static final int[] f = new int[] { 2, 4, 6, 8 };
  
  private static final int[][] g;
  
  private final List<d> h = new ArrayList<d>();
  
  private final List<d> i = new ArrayList<d>();
  
  static {
    int[] arrayOfInt1 = { 3, 8, 2, 1 };
    int[] arrayOfInt2 = { 3, 5, 5, 1 };
    int[] arrayOfInt3 = { 3, 3, 7, 1 };
    int[] arrayOfInt4 = { 2, 7, 4, 1 };
    int[] arrayOfInt5 = { 2, 3, 8, 1 };
    int[] arrayOfInt6 = { 1, 5, 7, 1 };
    g = new int[][] { arrayOfInt1, arrayOfInt2, arrayOfInt3, { 3, 1, 9, 1 }, arrayOfInt4, { 2, 5, 6, 1 }, arrayOfInt5, arrayOfInt6, { 1, 3, 9, 1 } };
  }
  
  private b a(a parama, c paramc, boolean paramBoolean) {
    int[] arrayOfInt3 = c();
    arrayOfInt3[0] = 0;
    arrayOfInt3[1] = 0;
    arrayOfInt3[2] = 0;
    arrayOfInt3[3] = 0;
    arrayOfInt3[4] = 0;
    arrayOfInt3[5] = 0;
    arrayOfInt3[6] = 0;
    arrayOfInt3[7] = 0;
    if (paramBoolean) {
      b(parama, paramc.b()[0], arrayOfInt3);
    } else {
      a(parama, paramc.b()[1] + 1, arrayOfInt3);
      int i1 = arrayOfInt3.length - 1;
      j = 0;
      while (j < i1) {
        int i2 = arrayOfInt3[j];
        arrayOfInt3[j] = arrayOfInt3[i1];
        arrayOfInt3[i1] = i2;
        j++;
        i1--;
      } 
    } 
    if (paramBoolean) {
      j = 16;
    } else {
      j = 15;
    } 
    float f = a(arrayOfInt3) / j;
    int[] arrayOfInt1 = f();
    int[] arrayOfInt2 = g();
    float[] arrayOfFloat1 = d();
    float[] arrayOfFloat2 = e();
    int k;
    for (k = 0; k < arrayOfInt3.length; k++) {
      int i1;
      float f1 = arrayOfInt3[k] / f;
      int i2 = (int)(0.5F + f1);
      if (i2 < 1) {
        i1 = 1;
      } else {
        i1 = i2;
        if (i2 > 8)
          i1 = 8; 
      } 
      i2 = k >> 1;
      if ((k & 0x1) == 0) {
        arrayOfInt1[i2] = i1;
        arrayOfFloat1[i2] = f1 - i1;
      } else {
        arrayOfInt2[i2] = i1;
        arrayOfFloat2[i2] = f1 - i1;
      } 
    } 
    a(paramBoolean, j);
    k = arrayOfInt1.length - 1;
    int j = 0;
    int i = 0;
    while (k >= 0) {
      j = j * 9 + arrayOfInt1[k];
      i += arrayOfInt1[k];
      k--;
    } 
    int m = arrayOfInt2.length - 1;
    int n = 0;
    k = 0;
    while (m >= 0) {
      n = n * 9 + arrayOfInt2[m];
      k += arrayOfInt2[m];
      m--;
    } 
    j += n * 3;
    if (paramBoolean) {
      if ((i & 0x1) != 0 || i > 12 || i < 4)
        throw i.a(); 
      i = (12 - i) / 2;
      m = e[i];
      k = f.a(arrayOfInt1, m, false);
      m = f.a(arrayOfInt2, 9 - m, true);
      return new b(k * a[i] + m + c[i], j);
    } 
    if ((k & 0x1) != 0 || k > 10 || k < 4)
      throw i.a(); 
    i = (10 - k) / 2;
    k = f[i];
    m = f.a(arrayOfInt1, k, true);
    return new b(f.a(arrayOfInt2, 9 - k, false) * b[i] + m + d[i], j);
  }
  
  private c a(a parama, int paramInt, boolean paramBoolean, int[] paramArrayOfint) {
    boolean bool = parama.a(paramArrayOfint[0]);
    int i;
    for (i = paramArrayOfint[0] - 1; i >= 0 && parama.a(i) ^ bool; i--);
    int k = i + 1;
    i = paramArrayOfint[0];
    int[] arrayOfInt = b();
    System.arraycopy(arrayOfInt, 0, arrayOfInt, 1, arrayOfInt.length - 1);
    arrayOfInt[0] = i - k;
    int m = a(arrayOfInt, g);
    int j = paramArrayOfint[1];
    if (paramBoolean) {
      i = parama.a();
      j = parama.a() - 1 - j;
      i = i - 1 - k;
    } else {
      i = k;
    } 
    return new c(m, new int[] { k, paramArrayOfint[1] }, i, j, paramInt);
  }
  
  private d a(a parama, boolean paramBoolean, int paramInt, Map<com.a.a.e, ?> paramMap) {
    try {
      p p;
      int[] arrayOfInt = a(parama, 0, paramBoolean);
      c c = a(parama, paramInt, paramBoolean, arrayOfInt);
      if (paramMap == null) {
        paramMap = null;
      } else {
        p = (p)paramMap.get(com.a.a.e.i);
      } 
      if (p != null) {
        float f2 = (arrayOfInt[0] + arrayOfInt[1]) / 2.0F;
        float f1 = f2;
        if (paramBoolean)
          f1 = (parama.a() - 1) - f2; 
        p.a(new o(f1, paramInt));
      } 
      b b = a(parama, c, true);
      null = a(parama, c, false);
      return new d(b.a() * 1597 + null.a(), b.b() + null.b() * 4, c);
    } catch (i i) {
      return null;
    } 
  }
  
  private static m a(d paramd1, d paramd2) {
    String str2 = String.valueOf(paramd1.a() * 4537077L + paramd2.a());
    StringBuilder stringBuilder = new StringBuilder(14);
    int i;
    for (i = 13 - str2.length(); i > 0; i--)
      stringBuilder.append('0'); 
    stringBuilder.append(str2);
    i = 0;
    int j = 0;
    while (i < 13) {
      int m = stringBuilder.charAt(i) - 48;
      int k = m;
      if ((i & 0x1) == 0)
        k = m * 3; 
      j += k;
      i++;
    } 
    j = 10 - j % 10;
    i = j;
    if (j == 10)
      i = 0; 
    stringBuilder.append(i);
    o[] arrayOfO2 = paramd1.c().c();
    o[] arrayOfO1 = paramd2.c().c();
    String str1 = String.valueOf(stringBuilder.toString());
    o o1 = arrayOfO2[0];
    o o3 = arrayOfO2[1];
    o o4 = arrayOfO1[0];
    o o2 = arrayOfO1[1];
    a a1 = a.m;
    return new m(str1, null, new o[] { o1, o3, o4, o2 }, a1);
  }
  
  private static void a(Collection<d> paramCollection, d paramd) {
    boolean bool1;
    if (paramd == null)
      return; 
    boolean bool2 = false;
    Iterator<d> iterator = paramCollection.iterator();
    while (true) {
      bool1 = bool2;
      if (iterator.hasNext()) {
        d d1 = iterator.next();
        if (d1.a() == paramd.a()) {
          d1.e();
          bool1 = true;
          break;
        } 
        continue;
      } 
      break;
    } 
    if (!bool1)
      paramCollection.add(paramd); 
  }
  
  private void a(boolean paramBoolean, int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual f : ()[I
    //   4: invokestatic a : ([I)I
    //   7: istore #10
    //   9: aload_0
    //   10: invokevirtual g : ()[I
    //   13: invokestatic a : ([I)I
    //   16: istore #11
    //   18: iload #10
    //   20: iload #11
    //   22: iadd
    //   23: iload_2
    //   24: isub
    //   25: istore #12
    //   27: iconst_0
    //   28: istore #6
    //   30: iconst_0
    //   31: istore #9
    //   33: iload #10
    //   35: iconst_1
    //   36: iand
    //   37: iload_1
    //   38: if_icmpne -> 47
    //   41: iconst_1
    //   42: istore #8
    //   44: goto -> 50
    //   47: iconst_0
    //   48: istore #8
    //   50: iload #11
    //   52: iconst_1
    //   53: iand
    //   54: iconst_1
    //   55: if_icmpne -> 64
    //   58: iconst_1
    //   59: istore #7
    //   61: goto -> 67
    //   64: iconst_0
    //   65: istore #7
    //   67: iload_1
    //   68: ifeq -> 165
    //   71: iload #10
    //   73: bipush #12
    //   75: if_icmple -> 85
    //   78: iconst_0
    //   79: istore_3
    //   80: iconst_1
    //   81: istore_2
    //   82: goto -> 100
    //   85: iload #10
    //   87: iconst_4
    //   88: if_icmpge -> 96
    //   91: iconst_1
    //   92: istore_3
    //   93: goto -> 98
    //   96: iconst_0
    //   97: istore_3
    //   98: iconst_0
    //   99: istore_2
    //   100: iload #11
    //   102: bipush #12
    //   104: if_icmple -> 121
    //   107: iload_3
    //   108: istore #4
    //   110: iload_2
    //   111: istore #5
    //   113: iconst_1
    //   114: istore_3
    //   115: iload #6
    //   117: istore_2
    //   118: goto -> 229
    //   121: iload #11
    //   123: iconst_4
    //   124: if_icmpge -> 147
    //   127: iload_2
    //   128: istore #4
    //   130: iload_3
    //   131: istore_2
    //   132: iload #4
    //   134: istore_3
    //   135: iconst_1
    //   136: istore #6
    //   138: iload_3
    //   139: istore #5
    //   141: iload_2
    //   142: istore #4
    //   144: goto -> 157
    //   147: iload_3
    //   148: istore #4
    //   150: iload_2
    //   151: istore #5
    //   153: iload #9
    //   155: istore #6
    //   157: iconst_0
    //   158: istore_3
    //   159: iload #6
    //   161: istore_2
    //   162: goto -> 229
    //   165: iload #10
    //   167: bipush #11
    //   169: if_icmple -> 179
    //   172: iconst_0
    //   173: istore_2
    //   174: iconst_1
    //   175: istore_3
    //   176: goto -> 194
    //   179: iload #10
    //   181: iconst_5
    //   182: if_icmpge -> 190
    //   185: iconst_1
    //   186: istore_2
    //   187: goto -> 192
    //   190: iconst_0
    //   191: istore_2
    //   192: iconst_0
    //   193: istore_3
    //   194: iload #11
    //   196: bipush #10
    //   198: if_icmple -> 210
    //   201: iload_3
    //   202: istore #5
    //   204: iload_2
    //   205: istore #4
    //   207: goto -> 113
    //   210: iload #9
    //   212: istore #6
    //   214: iload_3
    //   215: istore #5
    //   217: iload_2
    //   218: istore #4
    //   220: iload #11
    //   222: iconst_4
    //   223: if_icmpge -> 157
    //   226: goto -> 135
    //   229: iload #12
    //   231: iconst_1
    //   232: if_icmpne -> 269
    //   235: iload #8
    //   237: ifeq -> 255
    //   240: iload #7
    //   242: ifeq -> 249
    //   245: invokestatic a : ()Lcom/a/a/i;
    //   248: athrow
    //   249: iconst_1
    //   250: istore #5
    //   252: goto -> 355
    //   255: iload #7
    //   257: ifne -> 264
    //   260: invokestatic a : ()Lcom/a/a/i;
    //   263: athrow
    //   264: iconst_1
    //   265: istore_3
    //   266: goto -> 355
    //   269: iload #12
    //   271: iconst_m1
    //   272: if_icmpne -> 309
    //   275: iload #8
    //   277: ifeq -> 295
    //   280: iload #7
    //   282: ifeq -> 289
    //   285: invokestatic a : ()Lcom/a/a/i;
    //   288: athrow
    //   289: iconst_1
    //   290: istore #4
    //   292: goto -> 355
    //   295: iload #7
    //   297: ifne -> 304
    //   300: invokestatic a : ()Lcom/a/a/i;
    //   303: athrow
    //   304: iconst_1
    //   305: istore_2
    //   306: goto -> 355
    //   309: iload #12
    //   311: ifne -> 435
    //   314: iload #8
    //   316: ifeq -> 346
    //   319: iload #7
    //   321: ifne -> 328
    //   324: invokestatic a : ()Lcom/a/a/i;
    //   327: athrow
    //   328: iload #10
    //   330: iload #11
    //   332: if_icmpge -> 341
    //   335: iconst_1
    //   336: istore #4
    //   338: goto -> 264
    //   341: iconst_1
    //   342: istore_2
    //   343: goto -> 249
    //   346: iload #7
    //   348: ifeq -> 355
    //   351: invokestatic a : ()Lcom/a/a/i;
    //   354: athrow
    //   355: iload #4
    //   357: ifeq -> 380
    //   360: iload #5
    //   362: ifeq -> 369
    //   365: invokestatic a : ()Lcom/a/a/i;
    //   368: athrow
    //   369: aload_0
    //   370: invokevirtual f : ()[I
    //   373: aload_0
    //   374: invokevirtual d : ()[F
    //   377: invokestatic a : ([I[F)V
    //   380: iload #5
    //   382: ifeq -> 396
    //   385: aload_0
    //   386: invokevirtual f : ()[I
    //   389: aload_0
    //   390: invokevirtual d : ()[F
    //   393: invokestatic b : ([I[F)V
    //   396: iload_2
    //   397: ifeq -> 419
    //   400: iload_3
    //   401: ifeq -> 408
    //   404: invokestatic a : ()Lcom/a/a/i;
    //   407: athrow
    //   408: aload_0
    //   409: invokevirtual g : ()[I
    //   412: aload_0
    //   413: invokevirtual d : ()[F
    //   416: invokestatic a : ([I[F)V
    //   419: iload_3
    //   420: ifeq -> 434
    //   423: aload_0
    //   424: invokevirtual g : ()[I
    //   427: aload_0
    //   428: invokevirtual e : ()[F
    //   431: invokestatic b : ([I[F)V
    //   434: return
    //   435: invokestatic a : ()Lcom/a/a/i;
    //   438: athrow
  }
  
  private int[] a(a parama, int paramInt, int paramBoolean) {
    int[] arrayOfInt = b();
    arrayOfInt[0] = 0;
    arrayOfInt[1] = 0;
    arrayOfInt[2] = 0;
    arrayOfInt[3] = 0;
    int n = parama.a();
    int i1 = 0;
    while (paramInt < n) {
      i1 = parama.a(paramInt) ^ true;
      if (paramBoolean == i1)
        break; 
      paramInt++;
    } 
    int m = paramInt;
    int j = 0;
    int k = paramInt;
    paramInt = m;
    int i = i1;
    while (k < n) {
      if (parama.a(k) ^ i) {
        arrayOfInt[j] = arrayOfInt[j] + 1;
      } else {
        if (j == 3) {
          if (b(arrayOfInt))
            return new int[] { paramInt, k }; 
          paramInt += arrayOfInt[0] + arrayOfInt[1];
          arrayOfInt[0] = arrayOfInt[2];
          arrayOfInt[1] = arrayOfInt[3];
          arrayOfInt[2] = 0;
          arrayOfInt[3] = 0;
          j--;
        } else {
          j++;
        } 
        arrayOfInt[j] = 1;
        if (i == 0) {
          boolean bool = true;
        } else {
          boolean bool = false;
        } 
      } 
      k++;
    } 
    throw i.a();
  }
  
  private static boolean b(d paramd1, d paramd2) {
    int k = paramd1.b();
    int m = paramd2.b();
    int j = paramd1.c().a() * 9 + paramd2.c().a();
    int i = j;
    if (j > 72)
      i = j - 1; 
    j = i;
    if (i > 8)
      j = i - 1; 
    return ((k + m * 16) % 79 == j);
  }
  
  public m a(int paramInt, a parama, Map<com.a.a.e, ?> paramMap) {
    d d2 = a(parama, false, paramInt, paramMap);
    a(this.h, d2);
    parama.c();
    d d1 = a(parama, true, paramInt, paramMap);
    a(this.i, d1);
    parama.c();
    int i = this.h.size();
    for (paramInt = 0; paramInt < i; paramInt++) {
      d d = this.h.get(paramInt);
      if (d.d() > 1) {
        int k = this.i.size();
        int j;
        for (j = 0; j < k; j++) {
          d1 = this.i.get(j);
          if (d1.d() > 1 && b(d, d1))
            return a(d, d1); 
        } 
      } 
    } 
    throw i.a();
  }
  
  public void a() {
    this.h.clear();
    this.i.clear();
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\e\a\e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */