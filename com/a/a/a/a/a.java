package com.a.a.a.a;

import com.a.a.b.b;
import com.a.a.b.e;
import com.a.a.f;

public final class a {
  private static final int[] a = new int[] { 0, 104, 240, 408, 608 };
  
  private static final int[] b = new int[] { 
      0, 128, 288, 480, 704, 960, 1248, 1568, 1920, 2304, 
      2720, 3168, 3648, 4160, 4704, 5280, 5888, 6528, 7200, 7904, 
      8640, 9408, 10208, 11040, 11904, 12800, 13728, 14688, 15680, 16704, 
      17760, 18848, 19968 };
  
  private static final int[] c = new int[] { 0, 17, 40, 51, 76 };
  
  private static final int[] d = new int[] { 
      0, 21, 48, 60, 88, 120, 156, 196, 240, 230, 
      272, 316, 364, 416, 470, 528, 588, 652, 720, 790, 
      864, 940, 1020, 920, 992, 1066, 1144, 1224, 1306, 1392, 
      1480, 1570, 1664 };
  
  private static final String[] e = new String[] { 
      "CTRL_PS", " ", "A", "B", "C", "D", "E", "F", "G", "H", 
      "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", 
      "S", "T", "U", "V", "W", "X", "Y", "Z", "CTRL_LL", "CTRL_ML", 
      "CTRL_DL", "CTRL_BS" };
  
  private static final String[] f = new String[] { 
      "CTRL_PS", " ", "a", "b", "c", "d", "e", "f", "g", "h", 
      "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", 
      "s", "t", "u", "v", "w", "x", "y", "z", "CTRL_US", "CTRL_ML", 
      "CTRL_DL", "CTRL_BS" };
  
  private static final String[] g = new String[] { 
      "CTRL_PS", " ", "\001", "\002", "\003", "\004", "\005", "\006", "\007", "\b", 
      "\t", "\n", "\013", "\f", "\r", "\033", "\034", "\035", "\036", "\037", 
      "@", "\\", "^", "_", "`", "|", "~", "", "CTRL_LL", "CTRL_UL", 
      "CTRL_PL", "CTRL_BS" };
  
  private static final String[] h = new String[] { 
      "", "\r", "\r\n", ". ", ", ", ": ", "!", "\"", "#", "$", 
      "%", "&", "'", "(", ")", "*", "+", ",", "-", ".", 
      "/", ":", ";", "<", "=", ">", "?", "[", "]", "{", 
      "}", "CTRL_UL" };
  
  private static final String[] i = new String[] { 
      "CTRL_PS", " ", "0", "1", "2", "3", "4", "5", "6", "7", 
      "8", "9", ",", ".", "CTRL_UL", "CTRL_US" };
  
  private int j;
  
  private int k;
  
  private com.a.a.a.a l;
  
  private int m;
  
  private static int a(boolean[] paramArrayOfboolean, int paramInt1, int paramInt2) {
    int j = paramInt1;
    int i = 0;
    while (j < paramInt1 + paramInt2) {
      int k = i << 1;
      i = k;
      if (paramArrayOfboolean[j])
        i = k + 1; 
      j++;
    } 
    return i;
  }
  
  private static a a(char paramChar) {
    if (paramChar != 'B') {
      if (paramChar != 'D') {
        if (paramChar != 'P') {
          switch (paramChar) {
            default:
              return a.a;
            case 'M':
              return a.c;
            case 'L':
              break;
          } 
          return a.b;
        } 
        return a.e;
      } 
      return a.d;
    } 
    return a.f;
  }
  
  private static String a(a parama, int paramInt) {
    switch (null.a[parama.ordinal()]) {
      default:
        return "";
      case 5:
        return i[paramInt];
      case 4:
        return h[paramInt];
      case 3:
        return g[paramInt];
      case 2:
        return f[paramInt];
      case 1:
        break;
    } 
    return e[paramInt];
  }
  
  private String a(boolean[] paramArrayOfboolean) {
    int k = this.k * this.l.b() - this.m;
    if (k > paramArrayOfboolean.length)
      throw f.a(); 
    a a1 = a.a;
    a a2 = a.a;
    StringBuilder stringBuilder = new StringBuilder(20);
    boolean bool = false;
    int j = 0;
    int i = 0;
    while (true) {
      boolean bool1 = false;
      boolean bool2 = false;
      a a3 = a1;
      int n = i;
      int m = j;
      while (!bool) {
        boolean bool3;
        boolean bool4;
        boolean bool5 = true;
        if (bool1) {
          bool4 = true;
          a1 = a3;
        } else {
          a1 = a2;
          bool4 = bool2;
        } 
        if (m) {
          if (k - n < 5)
            break; 
          bool3 = a(paramArrayOfboolean, n, 5);
          m = n + 5;
          j = bool3;
          i = m;
          if (bool3 == 0) {
            if (k - m < 11)
              break; 
            j = a(paramArrayOfboolean, m, 11) + 31;
            i = m + 11;
          } 
          bool3 = false;
          while (true) {
            if (bool3 < j) {
              if (k - i < 8) {
                j = bool5;
                break;
              } 
              stringBuilder.append((char)a(paramArrayOfboolean, i, 8));
              i += 8;
              bool3++;
              continue;
            } 
            j = bool;
            break;
          } 
          bool = false;
          bool3 = j;
          j = bool;
        } else if (a2 == a.f) {
          if (k - n < 8)
            break; 
          j = a(paramArrayOfboolean, n, 8);
          i = n + 8;
          stringBuilder.append((char)j);
          bool3 = bool;
          j = m;
        } else {
          if (a2 == a.d) {
            i = 4;
          } else {
            i = 5;
          } 
          if (k - n < i)
            break; 
          j = a(paramArrayOfboolean, n, i);
          n += i;
          String str = a(a2, j);
          if (str.startsWith("CTRL_")) {
            a3 = a(str.charAt(5));
            bool3 = bool;
            a2 = a3;
            j = m;
            i = n;
            if (str.charAt(6) == 'S')
              if (str.charAt(5) == 'B') {
                bool1 = true;
                j = 1;
                bool3 = bool;
                a2 = a3;
                i = n;
              } else {
                bool1 = true;
                bool3 = bool;
                a2 = a3;
                j = m;
                i = n;
              }  
          } else {
            stringBuilder.append(str);
            i = n;
            j = m;
            bool3 = bool;
          } 
        } 
        bool = bool3;
        m = j;
        n = i;
        a3 = a1;
        bool2 = bool4;
        if (bool4) {
          a2 = a1;
          bool = bool3;
        } 
      } 
      break;
    } 
    return stringBuilder.toString();
  }
  
  private boolean[] a(b paramb) {
    boolean[] arrayOfBoolean;
    if (this.l.c()) {
      if (this.l.a() > a.length)
        throw f.a(); 
      arrayOfBoolean = new boolean[a[this.l.a()]];
      this.j = c[this.l.a()];
    } else {
      if (this.l.a() > b.length)
        throw f.a(); 
      arrayOfBoolean = new boolean[b[this.l.a()]];
      this.j = d[this.l.a()];
    } 
    int j = this.l.a();
    int i = paramb.e();
    int k = 0;
    int m = 0;
    while (j != 0) {
      int n = 0;
      int i1 = 0;
      while (true) {
        int i2 = i * 2;
        if (n < i2 - 4) {
          int i3 = n / 2 + m;
          arrayOfBoolean[k + n] = paramb.a(m + i1, i3);
          arrayOfBoolean[i2 + k - 4 + n] = paramb.a(i3, m + i - 1 - i1);
          i1 = (i1 + 1) % 2;
          n++;
          continue;
        } 
        n = i2 + 1;
        i1 = 0;
        while (n > 5) {
          int i3 = i2 - n;
          int i4 = n / 2 + m - 1;
          arrayOfBoolean[i * 4 + k - 8 + i3 + 1] = paramb.a(m + i - 1 - i1, i4);
          arrayOfBoolean[i * 6 + k - 12 + i3 + 1] = paramb.a(i4, m + i1);
          i1 = (i1 + 1) % 2;
          n--;
        } 
        break;
      } 
      m += 2;
      k += i * 8 - 16;
      j--;
      i -= 4;
    } 
    return arrayOfBoolean;
  }
  
  private static b b(b paramb) {
    int i = (paramb.d() - 1) / 2 / 16 * 2 + 1;
    b b1 = new b(paramb.d() - i, paramb.e() - i);
    i = 0;
    int j = 0;
    while (i < paramb.d()) {
      if ((paramb.d() / 2 - i) % 16 != 0) {
        int k = 0;
        int m = 0;
        while (k < paramb.e()) {
          if ((paramb.d() / 2 - k) % 16 != 0) {
            if (paramb.a(i, k))
              b1.b(j, m); 
            m++;
          } 
          k++;
        } 
        j++;
      } 
      i++;
    } 
    return b1;
  }
  
  private boolean[] b(boolean[] paramArrayOfboolean) {
    // Byte code:
    //   0: aload_0
    //   1: getfield l : Lcom/a/a/a/a;
    //   4: invokevirtual a : ()I
    //   7: iconst_2
    //   8: if_icmpgt -> 25
    //   11: aload_0
    //   12: bipush #6
    //   14: putfield k : I
    //   17: getstatic com/a/a/b/b/a.c : Lcom/a/a/b/b/a;
    //   20: astore #11
    //   22: goto -> 88
    //   25: aload_0
    //   26: getfield l : Lcom/a/a/a/a;
    //   29: invokevirtual a : ()I
    //   32: bipush #8
    //   34: if_icmpgt -> 51
    //   37: aload_0
    //   38: bipush #8
    //   40: putfield k : I
    //   43: getstatic com/a/a/b/b/a.g : Lcom/a/a/b/b/a;
    //   46: astore #11
    //   48: goto -> 88
    //   51: aload_0
    //   52: getfield l : Lcom/a/a/a/a;
    //   55: invokevirtual a : ()I
    //   58: bipush #22
    //   60: if_icmpgt -> 77
    //   63: aload_0
    //   64: bipush #10
    //   66: putfield k : I
    //   69: getstatic com/a/a/b/b/a.b : Lcom/a/a/b/b/a;
    //   72: astore #11
    //   74: goto -> 88
    //   77: aload_0
    //   78: bipush #12
    //   80: putfield k : I
    //   83: getstatic com/a/a/b/b/a.a : Lcom/a/a/b/b/a;
    //   86: astore #11
    //   88: aload_0
    //   89: getfield l : Lcom/a/a/a/a;
    //   92: invokevirtual b : ()I
    //   95: istore #8
    //   97: aload_0
    //   98: getfield l : Lcom/a/a/a/a;
    //   101: invokevirtual c : ()Z
    //   104: ifeq -> 147
    //   107: getstatic com/a/a/a/a/a.a : [I
    //   110: aload_0
    //   111: getfield l : Lcom/a/a/a/a;
    //   114: invokevirtual a : ()I
    //   117: iaload
    //   118: aload_0
    //   119: getfield j : I
    //   122: aload_0
    //   123: getfield k : I
    //   126: imul
    //   127: isub
    //   128: istore_2
    //   129: getstatic com/a/a/a/a/a.c : [I
    //   132: aload_0
    //   133: getfield l : Lcom/a/a/a/a;
    //   136: invokevirtual a : ()I
    //   139: iaload
    //   140: iload #8
    //   142: isub
    //   143: istore_3
    //   144: goto -> 184
    //   147: getstatic com/a/a/a/a/a.b : [I
    //   150: aload_0
    //   151: getfield l : Lcom/a/a/a/a;
    //   154: invokevirtual a : ()I
    //   157: iaload
    //   158: aload_0
    //   159: getfield j : I
    //   162: aload_0
    //   163: getfield k : I
    //   166: imul
    //   167: isub
    //   168: istore_2
    //   169: getstatic com/a/a/a/a/a.d : [I
    //   172: aload_0
    //   173: getfield l : Lcom/a/a/a/a;
    //   176: invokevirtual a : ()I
    //   179: iaload
    //   180: iload #8
    //   182: isub
    //   183: istore_3
    //   184: aload_0
    //   185: getfield j : I
    //   188: newarray int
    //   190: astore #12
    //   192: iconst_0
    //   193: istore #4
    //   195: aload_0
    //   196: getfield j : I
    //   199: istore #6
    //   201: iconst_1
    //   202: istore #5
    //   204: iload #4
    //   206: iload #6
    //   208: if_icmpge -> 282
    //   211: iconst_1
    //   212: istore #6
    //   214: iload #5
    //   216: aload_0
    //   217: getfield k : I
    //   220: if_icmpgt -> 273
    //   223: aload_1
    //   224: aload_0
    //   225: getfield k : I
    //   228: iload #4
    //   230: imul
    //   231: aload_0
    //   232: getfield k : I
    //   235: iadd
    //   236: iload #5
    //   238: isub
    //   239: iload_2
    //   240: iadd
    //   241: baload
    //   242: ifeq -> 258
    //   245: aload #12
    //   247: iload #4
    //   249: aload #12
    //   251: iload #4
    //   253: iaload
    //   254: iload #6
    //   256: iadd
    //   257: iastore
    //   258: iload #6
    //   260: iconst_1
    //   261: ishl
    //   262: istore #6
    //   264: iload #5
    //   266: iconst_1
    //   267: iadd
    //   268: istore #5
    //   270: goto -> 214
    //   273: iload #4
    //   275: iconst_1
    //   276: iadd
    //   277: istore #4
    //   279: goto -> 195
    //   282: new com/a/a/b/b/c
    //   285: dup
    //   286: aload #11
    //   288: invokespecial <init> : (Lcom/a/a/b/b/a;)V
    //   291: aload #12
    //   293: iload_3
    //   294: invokevirtual a : ([II)V
    //   297: aload_0
    //   298: iconst_0
    //   299: putfield m : I
    //   302: aload_0
    //   303: getfield k : I
    //   306: iload #8
    //   308: imul
    //   309: newarray boolean
    //   311: astore_1
    //   312: iconst_0
    //   313: istore #4
    //   315: iconst_0
    //   316: istore_2
    //   317: iload #4
    //   319: iload #8
    //   321: if_icmpge -> 487
    //   324: iconst_1
    //   325: aload_0
    //   326: getfield k : I
    //   329: iconst_1
    //   330: isub
    //   331: ishl
    //   332: istore #6
    //   334: iconst_0
    //   335: istore #5
    //   337: iconst_0
    //   338: istore_3
    //   339: iconst_0
    //   340: istore #9
    //   342: iload #5
    //   344: aload_0
    //   345: getfield k : I
    //   348: if_icmpge -> 478
    //   351: aload #12
    //   353: iload #4
    //   355: iaload
    //   356: iload #6
    //   358: iand
    //   359: iload #6
    //   361: if_icmpne -> 370
    //   364: iconst_1
    //   365: istore #10
    //   367: goto -> 373
    //   370: iconst_0
    //   371: istore #10
    //   373: iload_3
    //   374: aload_0
    //   375: getfield k : I
    //   378: iconst_1
    //   379: isub
    //   380: if_icmpne -> 417
    //   383: iload #10
    //   385: iload #9
    //   387: if_icmpne -> 394
    //   390: invokestatic a : ()Lcom/a/a/f;
    //   393: athrow
    //   394: iload_2
    //   395: iconst_1
    //   396: iadd
    //   397: istore #7
    //   399: aload_0
    //   400: aload_0
    //   401: getfield m : I
    //   404: iconst_1
    //   405: iadd
    //   406: putfield m : I
    //   409: iconst_0
    //   410: istore_2
    //   411: iconst_0
    //   412: istore #9
    //   414: goto -> 458
    //   417: iload #9
    //   419: iload #10
    //   421: if_icmpne -> 431
    //   424: iload_3
    //   425: iconst_1
    //   426: iadd
    //   427: istore_3
    //   428: goto -> 437
    //   431: iload #10
    //   433: istore #9
    //   435: iconst_1
    //   436: istore_3
    //   437: aload_1
    //   438: aload_0
    //   439: getfield k : I
    //   442: iload #4
    //   444: imul
    //   445: iload #5
    //   447: iadd
    //   448: iload_2
    //   449: isub
    //   450: iload #10
    //   452: bastore
    //   453: iload_2
    //   454: istore #7
    //   456: iload_3
    //   457: istore_2
    //   458: iload #6
    //   460: iconst_1
    //   461: iushr
    //   462: istore #6
    //   464: iload #5
    //   466: iconst_1
    //   467: iadd
    //   468: istore #5
    //   470: iload_2
    //   471: istore_3
    //   472: iload #7
    //   474: istore_2
    //   475: goto -> 342
    //   478: iload #4
    //   480: iconst_1
    //   481: iadd
    //   482: istore #4
    //   484: goto -> 317
    //   487: aload_1
    //   488: areturn
    //   489: invokestatic a : ()Lcom/a/a/f;
    //   492: athrow
    //   493: astore_1
    //   494: goto -> 489
    // Exception table:
    //   from	to	target	type
    //   282	297	493	com/a/a/b/b/d
  }
  
  public e a(com.a.a.a.a parama) {
    this.l = parama;
    b b = parama.d();
    if (!this.l.c())
      b = b(this.l.d()); 
    return new e(null, a(b(a(b))), null, null);
  }
  
  private enum a {
    a, b, c, d, e, f;
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\a\a\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */