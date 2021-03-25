package com.a.a.c;

import com.a.a.b.b;
import com.a.a.b.e;
import com.a.a.b.g;
import com.a.a.c;
import com.a.a.c.a.d;
import com.a.a.c.b.a;
import com.a.a.e;
import com.a.a.i;
import com.a.a.k;
import com.a.a.m;
import com.a.a.n;
import com.a.a.o;
import java.util.List;
import java.util.Map;

public final class a implements k {
  private static final o[] a = new o[0];
  
  private final d b = new d();
  
  private static int a(int[] paramArrayOfint, b paramb) {
    int j = paramb.d();
    int i = paramArrayOfint[0];
    int m = paramArrayOfint[1];
    while (i < j && paramb.a(i, m))
      i++; 
    if (i == j)
      throw i.a(); 
    i -= paramArrayOfint[0];
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
    int n = arrayOfInt1[0];
    int i1 = (arrayOfInt2[0] - n + 1) / j;
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
  
  public m a(c paramc, Map<e, ?> paramMap) {
    e e;
    o[] arrayOfO;
    if (paramMap != null && paramMap.containsKey(e.b)) {
      b b = a(paramc.c());
      e = this.b.a(b);
      arrayOfO = a;
    } else {
      g g = (new a(e.c())).a();
      e = this.b.a(g.d());
      arrayOfO = g.e();
    } 
    m m = new m(e.b(), e.a(), arrayOfO, com.a.a.a.f);
    List list = e.c();
    if (list != null)
      m.a(n.c, list); 
    String str = e.d();
    if (str != null)
      m.a(n.d, str); 
    return m;
  }
  
  public void a() {}
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\c\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */