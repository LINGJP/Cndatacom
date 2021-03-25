package com.a.a.e.a.a;

import com.a.a.a;
import com.a.a.b.a;
import com.a.a.e;
import com.a.a.e.a.a;
import com.a.a.e.a.a.a.j;
import com.a.a.e.a.b;
import com.a.a.e.a.c;
import com.a.a.e.a.f;
import com.a.a.i;
import com.a.a.m;
import com.a.a.o;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class d extends a {
  private static final int[] a = new int[] { 7, 5, 4, 3, 1 };
  
  private static final int[] b = new int[] { 4, 20, 52, 104, 204 };
  
  private static final int[] c = new int[] { 0, 348, 1388, 2948, 3988 };
  
  private static final int[][] d;
  
  private static final int[][] e;
  
  private static final int[][] f;
  
  private final List<b> g = new ArrayList<b>(11);
  
  private final List<c> h = new ArrayList<c>();
  
  private final int[] i = new int[2];
  
  private boolean j = false;
  
  static {
    int[] arrayOfInt1 = { 1, 8, 4, 1 };
    int[] arrayOfInt2 = { 2, 6, 5, 1 };
    d = new int[][] { arrayOfInt1, { 3, 6, 4, 1 }, { 3, 4, 6, 1 }, { 3, 2, 8, 1 }, arrayOfInt2, { 2, 2, 9, 1 } };
    arrayOfInt1 = new int[] { 20, 60, 180, 118, 143, 7, 21, 63 };
    arrayOfInt2 = new int[] { 189, 145, 13, 39, 117, 140, 209, 205 };
    int[] arrayOfInt3 = { 62, 186, 136, 197, 169, 85, 44, 132 };
    int[] arrayOfInt4 = { 16, 48, 144, 10, 30, 90, 59, 177 };
    int[] arrayOfInt5 = { 134, 191, 151, 31, 93, 68, 204, 190 };
    int[] arrayOfInt6 = { 120, 149, 25, 75, 14, 42, 126, 167 };
    int[] arrayOfInt7 = { 79, 26, 78, 23, 69, 207, 199, 175 };
    int[] arrayOfInt8 = { 103, 98, 83, 38, 114, 131, 182, 124 };
    int[] arrayOfInt9 = { 161, 61, 183, 127, 170, 88, 53, 159 };
    e = new int[][] { 
        { 1, 3, 9, 27, 81, 32, 96, 77 }, arrayOfInt1, arrayOfInt2, { 193, 157, 49, 147, 19, 57, 171, 91 }, arrayOfInt3, { 185, 133, 188, 142, 4, 12, 36, 108 }, { 113, 128, 173, 97, 80, 29, 87, 50 }, { 150, 28, 84, 41, 123, 158, 52, 156 }, { 46, 138, 203, 187, 139, 206, 196, 166 }, { 76, 17, 51, 153, 37, 111, 122, 155 }, 
        { 43, 129, 176, 106, 107, 110, 119, 146 }, arrayOfInt4, { 109, 116, 137, 200, 178, 112, 125, 164 }, { 70, 210, 208, 202, 184, 130, 179, 115 }, arrayOfInt5, { 148, 22, 66, 198, 172, 94, 71, 2 }, { 6, 18, 54, 162, 64, 192, 154, 40 }, arrayOfInt6, arrayOfInt7, arrayOfInt8, 
        arrayOfInt9, { 55, 165, 73, 8, 24, 72, 5, 15 }, { 45, 135, 194, 160, 58, 174, 100, 89 } };
    arrayOfInt1 = new int[] { 0, 0 };
    arrayOfInt2 = new int[] { 0, 0, 1, 1, 2, 2, 3, 4, 4 };
    f = new int[][] { arrayOfInt1, { 0, 1, 1 }, { 0, 2, 1, 3 }, { 0, 4, 1, 3, 2 }, { 0, 4, 1, 3, 3, 5 }, { 0, 4, 1, 3, 4, 5, 5 }, { 0, 0, 1, 1, 2, 2, 3, 3 }, arrayOfInt2, { 0, 0, 1, 1, 2, 2, 3, 4, 5, 5 }, { 
          0, 0, 1, 1, 2, 3, 3, 4, 4, 5, 
          5 } };
  }
  
  private static int a(a parama, int paramInt) {
    return parama.a(paramInt) ? parama.c(parama.d(paramInt)) : parama.d(parama.c(paramInt));
  }
  
  private c a(a parama, int paramInt, boolean paramBoolean) {
    int i;
    int j;
    int k;
    if (paramBoolean) {
      for (i = this.i[0] - 1; i >= 0 && !parama.a(i); i--);
      j = i + 1;
      k = this.i[0] - j;
      i = this.i[1];
    } else {
      j = this.i[0];
      i = parama.d(this.i[1] + 1);
      k = i - this.i[1];
    } 
    int[] arrayOfInt = b();
    System.arraycopy(arrayOfInt, 0, arrayOfInt, 1, arrayOfInt.length - 1);
    arrayOfInt[0] = k;
    try {
      k = a(arrayOfInt, d);
      return new c(k, new int[] { j, i }, j, i, paramInt);
    } catch (i i1) {
      return null;
    } 
  }
  
  static m a(List<b> paramList) {
    String str = j.a(a.a(paramList)).a();
    o[] arrayOfO1 = ((b)paramList.get(0)).c().c();
    o[] arrayOfO2 = ((b)paramList.get(paramList.size() - 1)).c().c();
    o o1 = arrayOfO1[0];
    o o2 = arrayOfO1[1];
    o o3 = arrayOfO2[0];
    o o4 = arrayOfO2[1];
    a a1 = a.n;
    return new m(str, null, new o[] { o1, o2, o3, o4 }, a1);
  }
  
  private List<b> a(List<c> paramList, int paramInt) {
    while (true) {
      if (paramInt < this.h.size()) {
        c c = this.h.get(paramInt);
        this.g.clear();
        int j = paramList.size();
        for (int i = 0; i < j; i++)
          this.g.addAll(((c)paramList.get(i)).a()); 
        this.g.addAll(c.a());
        if (b(this.g)) {
          if (h())
            return this.g; 
          ArrayList<c> arrayList = new ArrayList();
          arrayList.addAll(paramList);
          arrayList.add(c);
          try {
            return a(arrayList, paramInt + 1);
          } catch (i i1) {}
        } 
        paramInt++;
        continue;
      } 
      throw i.a();
    } 
  }
  
  private List<b> a(boolean paramBoolean) {
    if (this.h.size() > 25) {
      this.h.clear();
      return null;
    } 
    this.g.clear();
    if (paramBoolean)
      Collections.reverse(this.h); 
    try {
      List<b> list = a(new ArrayList<c>(), 0);
    } catch (i i) {
      i = null;
    } 
    if (paramBoolean)
      Collections.reverse(this.h); 
    return (List<b>)i;
  }
  
  private void a(int paramInt) {
    boolean bool2;
    boolean bool3;
    boolean bool4;
    int j = a(f());
    int k = a(g());
    int m = j + k - paramInt;
    int i = 0;
    paramInt = 0;
    if ((j & 0x1) == 1) {
      bool4 = true;
    } else {
      bool4 = false;
    } 
    if ((k & 0x1) == 0) {
      bool3 = true;
    } else {
      bool3 = false;
    } 
    if (j > 13) {
      bool2 = false;
      bool1 = true;
    } else {
      if (j < 4) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      bool1 = false;
    } 
    if (k > 13) {
      paramInt = 1;
    } else {
      i = paramInt;
      if (k < 4)
        i = 1; 
      paramInt = 0;
    } 
    if (m == 1) {
      if (bool4) {
        if (bool3)
          throw i.a(); 
      } else {
        if (!bool3)
          throw i.a(); 
        paramInt = 1;
        if (bool2) {
          if (bool1)
            throw i.a(); 
          a(f(), d());
        } 
      } 
    } else {
      if (m == -1) {
        if (bool4) {
          if (bool3)
            throw i.a(); 
        } else {
          if (!bool3)
            throw i.a(); 
          i = 1;
          if (bool2) {
            if (bool1)
              throw i.a(); 
            a(f(), d());
          } 
        } 
      } else if (m == 0) {
        if (bool4) {
          if (!bool3)
            throw i.a(); 
          if (j < k) {
            paramInt = 1;
          } else {
            i = 1;
            bool1 = true;
          } 
        } else {
          if (bool3)
            throw i.a(); 
          if (bool2) {
            if (bool1)
              throw i.a(); 
            a(f(), d());
          } 
        } 
      } else {
        throw i.a();
      } 
      bool2 = true;
      if (bool2) {
        if (bool1)
          throw i.a(); 
        a(f(), d());
      } 
    } 
    boolean bool1 = true;
  }
  
  private void a(int paramInt, boolean paramBoolean) {
    boolean bool2;
    boolean bool3 = false;
    int i = 0;
    boolean bool1 = false;
    while (true) {
      bool2 = bool3;
      if (i < this.h.size()) {
        c c = this.h.get(i);
        if (c.b() > paramInt) {
          bool2 = c.a(this.g);
          break;
        } 
        bool1 = c.a(this.g);
        i++;
        continue;
      } 
      break;
    } 
    if (!bool2) {
      if (bool1)
        return; 
      if (a(this.g, this.h))
        return; 
      this.h.add(i, new c(this.g, paramInt, paramBoolean));
      a(this.g, this.h);
      return;
    } 
  }
  
  private static void a(List<b> paramList, List<c> paramList1) {
    Iterator<c> iterator = paramList1.iterator();
    while (true) {
      boolean bool;
      if (iterator.hasNext()) {
        c c = iterator.next();
        if (c.a().size() == paramList.size())
          continue; 
        Iterator<b> iterator1 = c.a().iterator();
        while (true) {
          boolean bool2 = iterator1.hasNext();
          boolean bool1 = false;
          bool = true;
          if (bool2) {
            b b = iterator1.next();
            Iterator<b> iterator2 = paramList.iterator();
            while (true) {
              if (iterator2.hasNext()) {
                if (b.equals(iterator2.next()))
                  break; 
                continue;
              } 
              bool = false;
              break;
            } 
            if (!bool) {
              bool = bool1;
            } else {
              continue;
            } 
          } else {
            bool = true;
            break;
          } 
          if (bool)
            iterator.remove(); 
        } 
      } else {
        break;
      } 
      if (bool)
        iterator.remove(); 
    } 
  }
  
  private static boolean a(c paramc, boolean paramBoolean1, boolean paramBoolean2) {
    return (paramc.a() != 0 || !paramBoolean1 || !paramBoolean2);
  }
  
  private static boolean a(Iterable<b> paramIterable, Iterable<c> paramIterable1) {
    Iterator<c> iterator = paramIterable1.iterator();
    while (true) {
      boolean bool1 = iterator.hasNext();
      boolean bool = false;
      if (bool1) {
        boolean bool2;
        c c = iterator.next();
        Iterator<b> iterator1 = paramIterable.iterator();
        while (true) {
          if (iterator1.hasNext()) {
            boolean bool3;
            b b = iterator1.next();
            Iterator<b> iterator2 = c.a().iterator();
            while (true) {
              if (iterator2.hasNext()) {
                if (b.equals(iterator2.next())) {
                  boolean bool4 = true;
                  break;
                } 
                continue;
              } 
              bool3 = false;
              break;
            } 
            if (!bool3) {
              bool3 = bool;
              break;
            } 
            continue;
          } 
          bool2 = true;
          break;
        } 
        if (bool2)
          return true; 
        continue;
      } 
      return false;
    } 
  }
  
  private void b(a parama, List<b> paramList, int paramInt) {
    int[] arrayOfInt = b();
    arrayOfInt[0] = 0;
    arrayOfInt[1] = 0;
    arrayOfInt[2] = 0;
    arrayOfInt[3] = 0;
    int i1 = parama.a();
    if (paramInt < 0)
      if (paramList.isEmpty()) {
        paramInt = 0;
      } else {
        paramInt = ((b)paramList.get(paramList.size() - 1)).c().b()[1];
      }  
    if (paramList.size() % 2 != 0) {
      i = 1;
    } else {
      i = 0;
    } 
    int k = i;
    if (this.j)
      k = i ^ 0x1; 
    int i = 0;
    while (paramInt < i1) {
      i = parama.a(paramInt) ^ true;
      if (i == 0)
        break; 
      paramInt++;
    } 
    int j = paramInt;
    int n = 0;
    int m = paramInt;
    paramInt = j;
    j = i;
    while (m < i1) {
      if (parama.a(m) ^ j) {
        arrayOfInt[n] = arrayOfInt[n] + 1;
        i = paramInt;
      } else {
        if (n == 3) {
          if (k != 0)
            c(arrayOfInt); 
          if (b(arrayOfInt)) {
            this.i[0] = paramInt;
            this.i[1] = m;
            return;
          } 
          if (k != 0)
            c(arrayOfInt); 
          i = paramInt + arrayOfInt[0] + arrayOfInt[1];
          arrayOfInt[0] = arrayOfInt[2];
          arrayOfInt[1] = arrayOfInt[3];
          arrayOfInt[2] = 0;
          arrayOfInt[3] = 0;
          paramInt = n - 1;
        } else {
          n++;
          i = paramInt;
          paramInt = n;
        } 
        arrayOfInt[paramInt] = 1;
        if (j == 0) {
          j = 1;
          n = paramInt;
        } else {
          j = 0;
          n = paramInt;
        } 
      } 
      m++;
      paramInt = i;
    } 
    throw i.a();
  }
  
  private static boolean b(List<b> paramList) {
    for (int[] arrayOfInt : f) {
      if (paramList.size() <= arrayOfInt.length) {
        int i = 0;
        while (true) {
          if (i < paramList.size()) {
            if (((b)paramList.get(i)).c().a() != arrayOfInt[i]) {
              i = 0;
              break;
            } 
            i++;
            continue;
          } 
          i = 1;
          break;
        } 
        if (i != 0)
          return true; 
      } 
    } 
    return false;
  }
  
  private static void c(int[] paramArrayOfint) {
    int j = paramArrayOfint.length;
    for (int i = 0; i < j / 2; i++) {
      int k = paramArrayOfint[i];
      int m = j - i - 1;
      paramArrayOfint[i] = paramArrayOfint[m];
      paramArrayOfint[m] = k;
    } 
  }
  
  private boolean h() {
    List<b> list = this.g;
    boolean bool = false;
    b b = list.get(0);
    b b1 = b.a();
    b b2 = b.b();
    if (b2 == null)
      return false; 
    int j = b2.b();
    int k = 1;
    int i = 2;
    while (k < this.g.size()) {
      b b4 = this.g.get(k);
      int m = j + b4.a().b();
      int n = i + 1;
      b b3 = b4.b();
      j = m;
      i = n;
      if (b3 != null) {
        j = m + b3.b();
        i = n + 1;
      } 
      k++;
    } 
    if ((i - 4) * 211 + j % 211 == b1.a())
      bool = true; 
    return bool;
  }
  
  b a(a parama, List<b> paramList, int paramInt) {
    // Byte code:
    //   0: aload_2
    //   1: invokeinterface size : ()I
    //   6: iconst_2
    //   7: irem
    //   8: ifne -> 17
    //   11: iconst_1
    //   12: istore #7
    //   14: goto -> 20
    //   17: iconst_0
    //   18: istore #7
    //   20: iload #7
    //   22: istore #8
    //   24: aload_0
    //   25: getfield j : Z
    //   28: ifeq -> 37
    //   31: iload #7
    //   33: iconst_1
    //   34: ixor
    //   35: istore #8
    //   37: iconst_m1
    //   38: istore #5
    //   40: iconst_1
    //   41: istore #4
    //   43: aload_0
    //   44: aload_1
    //   45: aload_2
    //   46: iload #5
    //   48: invokespecial b : (Lcom/a/a/b/a;Ljava/util/List;I)V
    //   51: aload_0
    //   52: aload_1
    //   53: iload_3
    //   54: iload #8
    //   56: invokespecial a : (Lcom/a/a/b/a;IZ)Lcom/a/a/e/a/c;
    //   59: astore #9
    //   61: aload #9
    //   63: ifnonnull -> 85
    //   66: aload_1
    //   67: aload_0
    //   68: getfield i : [I
    //   71: iconst_0
    //   72: iaload
    //   73: invokestatic a : (Lcom/a/a/b/a;I)I
    //   76: istore #5
    //   78: iload #4
    //   80: istore #6
    //   82: goto -> 88
    //   85: iconst_0
    //   86: istore #6
    //   88: iload #6
    //   90: istore #4
    //   92: iload #6
    //   94: ifne -> 43
    //   97: aload_0
    //   98: aload_1
    //   99: aload #9
    //   101: iload #8
    //   103: iconst_1
    //   104: invokevirtual a : (Lcom/a/a/b/a;Lcom/a/a/e/a/c;ZZ)Lcom/a/a/e/a/b;
    //   107: astore #10
    //   109: aload_2
    //   110: invokeinterface isEmpty : ()Z
    //   115: ifne -> 145
    //   118: aload_2
    //   119: aload_2
    //   120: invokeinterface size : ()I
    //   125: iconst_1
    //   126: isub
    //   127: invokeinterface get : (I)Ljava/lang/Object;
    //   132: checkcast com/a/a/e/a/a/b
    //   135: invokevirtual d : ()Z
    //   138: ifeq -> 145
    //   141: invokestatic a : ()Lcom/a/a/i;
    //   144: athrow
    //   145: aload_0
    //   146: aload_1
    //   147: aload #9
    //   149: iload #8
    //   151: iconst_0
    //   152: invokevirtual a : (Lcom/a/a/b/a;Lcom/a/a/e/a/c;ZZ)Lcom/a/a/e/a/b;
    //   155: astore_1
    //   156: goto -> 161
    //   159: aconst_null
    //   160: astore_1
    //   161: new com/a/a/e/a/a/b
    //   164: dup
    //   165: aload #10
    //   167: aload_1
    //   168: aload #9
    //   170: iconst_1
    //   171: invokespecial <init> : (Lcom/a/a/e/a/b;Lcom/a/a/e/a/b;Lcom/a/a/e/a/c;Z)V
    //   174: areturn
    //   175: astore_1
    //   176: goto -> 159
    // Exception table:
    //   from	to	target	type
    //   145	156	175	com/a/a/i
  }
  
  b a(a parama, c paramc, boolean paramBoolean1, boolean paramBoolean2) {
    int[] arrayOfInt2 = c();
    arrayOfInt2[0] = 0;
    arrayOfInt2[1] = 0;
    arrayOfInt2[2] = 0;
    arrayOfInt2[3] = 0;
    arrayOfInt2[4] = 0;
    arrayOfInt2[5] = 0;
    arrayOfInt2[6] = 0;
    arrayOfInt2[7] = 0;
    if (paramBoolean2) {
      b(parama, paramc.b()[0], arrayOfInt2);
    } else {
      a(parama, paramc.b()[1], arrayOfInt2);
      i = arrayOfInt2.length - 1;
      int i2 = 0;
      while (i2 < i) {
        int i3 = arrayOfInt2[i2];
        arrayOfInt2[i2] = arrayOfInt2[i];
        arrayOfInt2[i] = i3;
        i2++;
        i--;
      } 
    } 
    float f1 = a(arrayOfInt2) / 17;
    float f2 = (paramc.b()[1] - paramc.b()[0]) / 15.0F;
    if (Math.abs(f1 - f2) / f2 > 0.3F)
      throw i.a(); 
    int[] arrayOfInt1 = f();
    int[] arrayOfInt3 = g();
    float[] arrayOfFloat1 = d();
    float[] arrayOfFloat2 = e();
    int j;
    for (j = 0; j < arrayOfInt2.length; j++) {
      f2 = arrayOfInt2[j] * 1.0F / f1;
      int i2 = (int)(0.5F + f2);
      if (i2 < 1) {
        if (f2 < 0.3F)
          throw i.a(); 
        i = 1;
      } else {
        i = i2;
        if (i2 > 8) {
          if (f2 > 8.7F)
            throw i.a(); 
          i = 8;
        } 
      } 
      i2 = j >> 1;
      if ((j & 0x1) == 0) {
        arrayOfInt1[i2] = i;
        arrayOfFloat1[i2] = f2 - i;
      } else {
        arrayOfInt3[i2] = i;
        arrayOfFloat2[i2] = f2 - i;
      } 
    } 
    a(17);
    j = paramc.a();
    if (paramBoolean1) {
      i = 0;
    } else {
      i = 2;
    } 
    int i1 = j * 4 + i + (paramBoolean2 ^ true) - 1;
    int k = arrayOfInt1.length - 1;
    int i = 0;
    j = 0;
    while (k >= 0) {
      int i2 = i;
      if (a(paramc, paramBoolean1, paramBoolean2)) {
        i2 = e[i1][k * 2];
        i2 = i + arrayOfInt1[k] * i2;
      } 
      j += arrayOfInt1[k];
      k--;
      i = i2;
    } 
    int m = arrayOfInt3.length - 1;
    for (k = 0; m >= 0; k = i2) {
      int i2 = k;
      if (a(paramc, paramBoolean1, paramBoolean2)) {
        i2 = e[i1][m * 2 + 1];
        i2 = k + arrayOfInt3[m] * i2;
      } 
      k = arrayOfInt3[m];
      m--;
    } 
    if ((j & 0x1) != 0 || j > 13 || j < 4)
      throw i.a(); 
    j = (13 - j) / 2;
    int n = a[j];
    m = f.a(arrayOfInt1, n, true);
    n = f.a(arrayOfInt3, 9 - n, false);
    return new b(m * b[j] + n + c[j], i + k);
  }
  
  public m a(int paramInt, a parama, Map<e, ?> paramMap) {
    this.g.clear();
    this.j = false;
    try {
      return a(a(paramInt, parama));
    } catch (i i) {
      this.g.clear();
      this.j = true;
      return a(a(paramInt, parama));
    } 
  }
  
  List<b> a(int paramInt, a parama) {
    try {
      while (true) {
        b b = a(parama, this.g, paramInt);
        this.g.add(b);
      } 
    } catch (i i) {
      if (this.g.isEmpty())
        throw i; 
      if (h())
        return this.g; 
      boolean bool = this.h.isEmpty();
      a(paramInt, false);
      if ((bool ^ true) != 0) {
        List<b> list = a(false);
        if (list != null)
          return list; 
        list = a(true);
        if (list != null)
          return list; 
      } 
      throw i.a();
    } 
  }
  
  public void a() {
    this.g.clear();
    this.h.clear();
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\e\a\a\d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */