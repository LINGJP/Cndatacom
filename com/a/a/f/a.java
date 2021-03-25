package com.a.a.f;

import com.a.a.b.b;
import com.a.a.b.e;
import com.a.a.b.g;
import com.a.a.c;
import com.a.a.e;
import com.a.a.f.a.c;
import com.a.a.f.b.a;
import com.a.a.i;
import com.a.a.k;
import com.a.a.m;
import com.a.a.o;
import java.util.Map;

public final class a implements k {
  private static final o[] a = new o[0];
  
  private final c b = new c();
  
  private static int a(int paramInt1, int paramInt2, b paramb) {
    int m = paramb.d();
    int j = 0;
    boolean bool = true;
    int i = paramInt1;
    paramInt1 = j;
    while (true) {
      j = m - 1;
      if (i < j && paramInt1 < 8) {
        j = i + 1;
        boolean bool1 = paramb.a(j, paramInt2);
        i = paramInt1;
        if (bool != bool1)
          i = paramInt1 + 1; 
        bool = bool1;
        paramInt1 = i;
        i = j;
        continue;
      } 
      break;
    } 
    if (i == j)
      throw i.a(); 
    return i;
  }
  
  private static int a(int[] paramArrayOfint, b paramb) {
    int i = paramArrayOfint[0];
    int j = paramArrayOfint[1];
    int m = paramb.d();
    while (i < m && paramb.a(i, j))
      i++; 
    if (i == m)
      throw i.a(); 
    i = i - paramArrayOfint[0] >>> 3;
    if (i == 0)
      throw i.a(); 
    return i;
  }
  
  private static b a(b paramb) {
    int[] arrayOfInt1 = paramb.b();
    int[] arrayOfInt2 = paramb.c();
    if (arrayOfInt1 == null || arrayOfInt2 == null)
      throw i.a(); 
    int j = a(arrayOfInt1, paramb);
    int m = arrayOfInt1[1];
    int i = arrayOfInt2[1];
    int n = a(arrayOfInt1[0], m, paramb);
    int i1 = (b(arrayOfInt1[0], m, paramb) - n + 1) / j;
    int i2 = (i - m + 1) / j;
    if (i1 <= 0 || i2 <= 0)
      throw i.a(); 
    int i3 = j >> 1;
    b b1 = new b(i1, i2);
    for (i = 0; i < i2; i++) {
      for (int i4 = 0; i4 < i1; i4++) {
        if (paramb.a(i4 * j + n + i3, i * j + m + i3))
          b1.b(i4, i); 
      } 
    } 
    return b1;
  }
  
  private static int b(int paramInt1, int paramInt2, b paramb) {
    int i = paramb.d();
    boolean bool = true;
    while (--i > paramInt1 && !paramb.a(i, paramInt2))
      i--; 
    int j = 0;
    int m = i;
    while (m > paramInt1 && j < 9) {
      boolean bool1 = paramb.a(--m, paramInt2);
      i = j;
      if (bool != bool1)
        i = j + 1; 
      bool = bool1;
      j = i;
    } 
    if (m == paramInt1)
      throw i.a(); 
    return m;
  }
  
  public m a(c paramc, Map<e, ?> paramMap) {
    e e;
    o[] arrayOfO;
    if (paramMap != null && paramMap.containsKey(e.b)) {
      b b = a(paramc.c());
      e = this.b.a(b);
      arrayOfO = a;
    } else {
      g g = (new a((c)e)).a();
      e = this.b.a(g.d());
      arrayOfO = g.e();
    } 
    return new m(e.b(), e.a(), arrayOfO, com.a.a.a.k);
  }
  
  public void a() {}
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\f\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */