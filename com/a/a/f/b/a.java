package com.a.a.f.b;

import com.a.a.b.b;
import com.a.a.b.g;
import com.a.a.b.i;
import com.a.a.b.k;
import com.a.a.c;
import com.a.a.e;
import com.a.a.i;
import com.a.a.o;
import java.util.Arrays;
import java.util.Map;

public final class a {
  private static final int[] a = new int[] { 8, 1, 1, 1, 1, 1, 1, 3 };
  
  private static final int[] b = new int[] { 3, 1, 1, 1, 1, 1, 1, 8 };
  
  private static final int[] c = new int[] { 7, 1, 1, 3, 1, 1, 1, 2, 1 };
  
  private static final int[] d = new int[] { 1, 2, 1, 1, 1, 3, 1, 1, 7 };
  
  private final c e;
  
  public a(c paramc) {
    this.e = paramc;
  }
  
  private static float a(o[] paramArrayOfo) {
    return ((o.a(paramArrayOfo[0], paramArrayOfo[4]) + o.a(paramArrayOfo[1], paramArrayOfo[5])) / 34.0F + (o.a(paramArrayOfo[6], paramArrayOfo[2]) + o.a(paramArrayOfo[7], paramArrayOfo[3])) / 36.0F) / 2.0F;
  }
  
  private static int a(o paramo1, o paramo2, o paramo3, o paramo4, float paramFloat) {
    return ((com.a.a.b.a.a.a(o.a(paramo1, paramo2) / paramFloat) + com.a.a.b.a.a.a(o.a(paramo3, paramo4) / paramFloat) >> 1) + 8) / 17 * 17;
  }
  
  private static int a(int[] paramArrayOfint1, int[] paramArrayOfint2, int paramInt) {
    int n = paramArrayOfint1.length;
    int m = 0;
    int k = 0;
    int i = 0;
    int j = 0;
    while (k < n) {
      i += paramArrayOfint1[k];
      j += paramArrayOfint2[k];
      k++;
    } 
    if (i < j)
      return Integer.MAX_VALUE; 
    int i1 = (i << 8) / j;
    k = 0;
    for (j = m; j < n; j++) {
      m = paramArrayOfint1[j] << 8;
      int i2 = paramArrayOfint2[j] * i1;
      if (m > i2) {
        m -= i2;
      } else {
        m = i2 - m;
      } 
      if (m > paramInt * i1 >> 8)
        return Integer.MAX_VALUE; 
      k += m;
    } 
    return k / i;
  }
  
  private b a(o[] paramArrayOfo, int paramInt1, int paramInt2) {
    paramInt1 *= 8;
    paramInt2 *= 4;
    float f1 = paramInt1;
    float f2 = paramInt2;
    k k = k.a(0.0F, 0.0F, f1, 0.0F, 0.0F, f2, f1, f2, paramArrayOfo[12].a(), paramArrayOfo[12].b(), paramArrayOfo[14].a(), paramArrayOfo[14].b(), paramArrayOfo[13].a(), paramArrayOfo[13].b(), paramArrayOfo[15].a(), paramArrayOfo[15].b());
    return i.a().a(this.e.c(), paramInt1, paramInt2, k);
  }
  
  private static o a(o paramo1, o paramo2, o paramo3, o paramo4) {
    float f1 = paramo1.a() - paramo2.a();
    float f2 = paramo3.a() - paramo4.a();
    float f3 = paramo1.b() - paramo2.b();
    float f4 = paramo3.b() - paramo4.b();
    float f5 = paramo1.a() * paramo2.b() - paramo1.b() * paramo2.a();
    float f6 = paramo3.a() * paramo4.b() - paramo3.b() * paramo4.a();
    float f7 = f1 * f4 - f3 * f2;
    return (f7 == 0.0F) ? null : new o((f2 * f5 - f1 * f6) / f7, (f5 * f4 - f3 * f6) / f7);
  }
  
  private static void a(b paramb, o[] paramArrayOfo, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    o o1 = paramArrayOfo[paramInt1];
    float f1 = paramArrayOfo[paramInt1 + 4].a() - o1.a();
    float f3 = o1.a();
    float f4 = paramInt2;
    float f2 = paramInt4;
    f3 += f4 * f1 / f2;
    f1 = o1.a() + f1 * (paramInt3 + paramInt2) / f2;
    int j = Math.round((f3 + f1) / 2.0F);
    int k = Math.round(o1.b());
    int i;
    for (i = (int)Math.max(f3, f1) + 1; i < paramb.d() && (paramb.a(i - 1, k) || !paramb.a(i, k)); i++);
    paramInt3 = j;
    paramInt2 = k;
    for (paramInt4 = 0; paramInt4 == 0; paramInt4 = bool) {
      if (paramb.a(paramInt3, paramInt2)) {
        boolean bool1;
        paramInt4 = paramInt3 + i - j;
        if (!paramb.a(paramInt4, paramInt2) && !paramb.a(paramInt4 + 1, paramInt2)) {
          bool1 = true;
        } else {
          bool1 = false;
        } 
        int m = paramInt2 + paramInt5;
        paramInt4 = m;
        if (m > 0) {
          paramInt2 = m;
          paramInt4 = bool1;
          if (m >= paramb.e() - 1) {
            paramInt4 = m;
          } else {
            continue;
          } 
        } 
      } else {
        if (paramInt3 > 0 && paramb.a(paramInt3 - 1, paramInt2)) {
          paramInt3--;
          continue;
        } 
        if (paramInt3 < paramb.d() - 1) {
          int m = paramInt3 + 1;
          if (paramb.a(m, paramInt2)) {
            paramInt3 = m;
            continue;
          } 
        } 
        paramInt4 = paramInt2;
        if (paramInt2 != k)
          paramInt4 = paramInt2 - paramInt5; 
      } 
      boolean bool = true;
      paramInt2 = paramInt4;
    } 
    paramArrayOfo[paramInt1 + 8] = new o(paramInt3, paramInt2);
  }
  
  private static void a(b paramb, o[] paramArrayOfo, boolean paramBoolean) {
    byte b1;
    double d = Math.abs(paramArrayOfo[4].b() - paramArrayOfo[5].b());
    boolean bool = false;
    if (d < 20.0D) {
      b1 = 1;
    } else {
      b1 = 0;
    } 
    if (Math.abs(paramArrayOfo[6].b() - paramArrayOfo[7].b()) < 20.0D)
      bool = true; 
    if (b1 || bool)
      throw i.a(); 
    if (paramBoolean) {
      b1 = 1;
    } else {
      b1 = -1;
    } 
    a(paramb, paramArrayOfo, 0, 0, 8, 17, b1);
    if (paramBoolean) {
      b1 = -1;
    } else {
      b1 = 1;
    } 
    a(paramb, paramArrayOfo, 1, 0, 8, 17, b1);
    if (paramBoolean) {
      b1 = 1;
    } else {
      b1 = -1;
    } 
    a(paramb, paramArrayOfo, 2, 11, 7, 18, b1);
    if (paramBoolean) {
      b1 = -1;
    } else {
      b1 = 1;
    } 
    a(paramb, paramArrayOfo, 3, 11, 7, 18, b1);
    a(paramArrayOfo, 12, 4, 5, 8, 10, paramb);
    a(paramArrayOfo, 13, 4, 5, 9, 11, paramb);
    a(paramArrayOfo, 14, 6, 7, 8, 10, paramb);
    a(paramArrayOfo, 15, 6, 7, 9, 11, paramb);
  }
  
  private static void a(o[] paramArrayOfo, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, b paramb) {
    o o1 = a(paramArrayOfo[paramInt2], paramArrayOfo[paramInt3], paramArrayOfo[paramInt4], paramArrayOfo[paramInt5]);
    if (o1 == null)
      throw i.a(); 
    paramInt2 = Math.round(o1.a());
    paramInt3 = Math.round(o1.b());
    if (paramInt2 < 0 || paramInt2 >= paramb.d() || paramInt3 < 0 || paramInt3 >= paramb.e())
      throw i.a(); 
    paramArrayOfo[paramInt1] = o1;
  }
  
  private static int[] a(b paramb, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, int[] paramArrayOfint1, int[] paramArrayOfint2) {
    Arrays.fill(paramArrayOfint2, 0, paramArrayOfint2.length, 0);
    int n = paramArrayOfint1.length;
    int j = paramInt1;
    int i = j;
    int m = 0;
    int k = j;
    while (k < paramInt1 + paramInt3) {
      if (paramb.a(k, paramInt2) ^ paramBoolean) {
        paramArrayOfint2[m] = paramArrayOfint2[m] + 1;
        j = i;
      } else {
        int i1 = n - 1;
        if (m == i1) {
          if (a(paramArrayOfint2, paramArrayOfint1, 204) < 107)
            return new int[] { i, k }; 
          j = i + paramArrayOfint2[0] + paramArrayOfint2[1];
          i = n - 2;
          System.arraycopy(paramArrayOfint2, 2, paramArrayOfint2, 0, i);
          paramArrayOfint2[i] = 0;
          paramArrayOfint2[i1] = 0;
          i = m - 1;
        } else {
          m++;
          j = i;
          i = m;
        } 
        boolean bool = true;
        paramArrayOfint2[i] = 1;
        if (!paramBoolean) {
          paramBoolean = bool;
        } else {
          paramBoolean = false;
        } 
        m = i;
      } 
      k++;
      i = j;
    } 
    return null;
  }
  
  private static o[] a(b paramb, int paramInt) {
    boolean bool;
    int m = paramb.e();
    int k = paramb.d();
    o[] arrayOfO = new o[16];
    int[] arrayOfInt = new int[a.length];
    int i = 0;
    while (true) {
      bool = true;
      if (i < m) {
        int[] arrayOfInt1 = a(paramb, 0, i, k, false, a, arrayOfInt);
        if (arrayOfInt1 != null) {
          float f1 = arrayOfInt1[0];
          float f2 = i;
          arrayOfO[0] = new o(f1, f2);
          arrayOfO[4] = new o(arrayOfInt1[1], f2);
          boolean bool1 = true;
          break;
        } 
        i += paramInt;
        continue;
      } 
      j = 0;
      break;
    } 
    i = j;
    if (j) {
      i = m - 1;
      while (true) {
        if (i > 0) {
          int[] arrayOfInt1 = a(paramb, 0, i, k, false, a, arrayOfInt);
          if (arrayOfInt1 != null) {
            float f1 = arrayOfInt1[0];
            float f2 = i;
            arrayOfO[1] = new o(f1, f2);
            arrayOfO[5] = new o(arrayOfInt1[1], f2);
            i = 1;
            break;
          } 
          i -= paramInt;
          continue;
        } 
        i = 0;
        break;
      } 
    } 
    arrayOfInt = new int[c.length];
    int j = i;
    if (i != 0) {
      i = 0;
      while (true) {
        if (i < m) {
          int[] arrayOfInt1 = a(paramb, 0, i, k, false, c, arrayOfInt);
          if (arrayOfInt1 != null) {
            float f1 = arrayOfInt1[1];
            float f2 = i;
            arrayOfO[2] = new o(f1, f2);
            arrayOfO[6] = new o(arrayOfInt1[0], f2);
            j = 1;
            break;
          } 
          i += paramInt;
          continue;
        } 
        j = 0;
        break;
      } 
    } 
    if (j != 0) {
      i = m - 1;
      while (true) {
        if (i > 0) {
          int[] arrayOfInt1 = a(paramb, 0, i, k, false, c, arrayOfInt);
          if (arrayOfInt1 != null) {
            float f1 = arrayOfInt1[1];
            float f2 = i;
            arrayOfO[3] = new o(f1, f2);
            arrayOfO[7] = new o(arrayOfInt1[0], f2);
            paramInt = bool;
            break;
          } 
          i -= paramInt;
          continue;
        } 
        paramInt = 0;
        break;
      } 
    } else {
      paramInt = j;
    } 
    return (paramInt != 0) ? arrayOfO : null;
  }
  
  private static int b(o paramo1, o paramo2, o paramo3, o paramo4, float paramFloat) {
    return com.a.a.b.a.a.a(o.a(paramo1, paramo3) / paramFloat) + com.a.a.b.a.a.a(o.a(paramo2, paramo4) / paramFloat) >> 1;
  }
  
  private static o[] b(b paramb, int paramInt) {
    boolean bool;
    int m = paramb.e();
    int n = paramb.d() >> 1;
    o[] arrayOfO = new o[16];
    int[] arrayOfInt = new int[b.length];
    int k = m - 1;
    int i = k;
    while (true) {
      bool = false;
      if (i > 0) {
        int[] arrayOfInt1 = a(paramb, n, i, n, true, b, arrayOfInt);
        if (arrayOfInt1 != null) {
          float f1 = arrayOfInt1[1];
          float f2 = i;
          arrayOfO[0] = new o(f1, f2);
          arrayOfO[4] = new o(arrayOfInt1[0], f2);
          boolean bool1 = true;
          break;
        } 
        i -= paramInt;
        continue;
      } 
      j = 0;
      break;
    } 
    i = j;
    if (j) {
      i = 0;
      while (true) {
        if (i < m) {
          int[] arrayOfInt1 = a(paramb, n, i, n, true, b, arrayOfInt);
          if (arrayOfInt1 != null) {
            float f1 = arrayOfInt1[1];
            float f2 = i;
            arrayOfO[1] = new o(f1, f2);
            arrayOfO[5] = new o(arrayOfInt1[0], f2);
            i = 1;
            break;
          } 
          i += paramInt;
          continue;
        } 
        i = 0;
        break;
      } 
    } 
    arrayOfInt = new int[d.length];
    int j = i;
    if (i != 0)
      while (true) {
        if (k > 0) {
          int[] arrayOfInt1 = a(paramb, 0, k, n, false, d, arrayOfInt);
          if (arrayOfInt1 != null) {
            float f1 = arrayOfInt1[0];
            float f2 = k;
            arrayOfO[2] = new o(f1, f2);
            arrayOfO[6] = new o(arrayOfInt1[1], f2);
            j = 1;
            break;
          } 
          k -= paramInt;
          continue;
        } 
        j = 0;
        break;
      }  
    if (j != 0) {
      j = 0;
      while (true) {
        i = bool;
        if (j < m) {
          int[] arrayOfInt1 = a(paramb, 0, j, n, false, d, arrayOfInt);
          if (arrayOfInt1 != null) {
            float f1 = arrayOfInt1[0];
            float f2 = j;
            arrayOfO[3] = new o(f1, f2);
            arrayOfO[7] = new o(arrayOfInt1[1], f2);
            i = 1;
            break;
          } 
          j += paramInt;
          continue;
        } 
        break;
      } 
    } else {
      i = j;
    } 
    return (i != 0) ? arrayOfO : null;
  }
  
  public g a() {
    return a((Map<e, ?>)null);
  }
  
  public g a(Map<e, ?> paramMap) {
    b b = this.e.c();
    o[] arrayOfO = a(b, 8);
    if (arrayOfO == null) {
      o[] arrayOfO1 = b(b, 8);
      arrayOfO = arrayOfO1;
      if (arrayOfO1 != null) {
        a(b, arrayOfO1, true);
        arrayOfO = arrayOfO1;
      } 
    } else {
      a(b, arrayOfO, false);
    } 
    if (arrayOfO == null)
      throw i.a(); 
    float f = a(arrayOfO);
    if (f < 1.0F)
      throw i.a(); 
    int i = a(arrayOfO[12], arrayOfO[14], arrayOfO[13], arrayOfO[15], f);
    if (i < 1)
      throw i.a(); 
    return new g((new b(a(arrayOfO, i, Math.max(b(arrayOfO[12], arrayOfO[14], arrayOfO[13], arrayOfO[15], f), i)), i)).a(), new o[] { arrayOfO[5], arrayOfO[4], arrayOfO[6], arrayOfO[7] });
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\f\b\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */