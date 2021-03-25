package com.a.a.g;

import com.a.a.b.b;
import com.a.a.b.e;
import com.a.a.b.g;
import com.a.a.c;
import com.a.a.e;
import com.a.a.g.a.e;
import com.a.a.g.b.c;
import com.a.a.i;
import com.a.a.k;
import com.a.a.m;
import com.a.a.n;
import com.a.a.o;
import java.util.List;
import java.util.Map;

public class a implements k {
  private static final o[] a = new o[0];
  
  private final e b = new e();
  
  private static float a(int[] paramArrayOfint, b paramb) {
    int n = paramb.e();
    int i1 = paramb.d();
    int j = paramArrayOfint[0];
    boolean bool = true;
    int i = paramArrayOfint[1];
    int m;
    for (m = 0; j < i1 && i < n; m = i2) {
      int i4;
      boolean bool1 = bool;
      int i2 = m;
      if (bool != paramb.a(j, i)) {
        i2 = m + 1;
        if (i2 == 5)
          break; 
        i4 = bool ^ true;
      } 
      j++;
      i++;
      int i3 = i4;
    } 
    if (j == i1 || i == n)
      throw i.a(); 
    return (j - paramArrayOfint[0]) / 7.0F;
  }
  
  private static b a(b paramb) {
    int[] arrayOfInt1 = paramb.b();
    int[] arrayOfInt2 = paramb.c();
    if (arrayOfInt1 == null || arrayOfInt2 == null)
      throw i.a(); 
    float f = a(arrayOfInt1, paramb);
    int m = arrayOfInt1[1];
    int i = arrayOfInt2[1];
    int n = arrayOfInt1[0];
    int j = arrayOfInt2[0];
    int i2 = i - m;
    i = j;
    if (i2 != j - n)
      i = n + i2; 
    int i1 = Math.round((i - n + 1) / f);
    i2 = Math.round((i2 + 1) / f);
    if (i1 <= 0 || i2 <= 0)
      throw i.a(); 
    if (i2 != i1)
      throw i.a(); 
    int i3 = (int)(f / 2.0F);
    b b1 = new b(i1, i2);
    for (i = 0; i < i2; i++) {
      int i4 = (int)(i * f);
      for (j = 0; j < i1; j++) {
        if (paramb.a((int)(j * f) + n + i3, i4 + m + i3))
          b1.b(j, i); 
      } 
    } 
    return b1;
  }
  
  public final m a(c paramc, Map<e, ?> paramMap) {
    e e1;
    o[] arrayOfO;
    if (paramMap != null && paramMap.containsKey(e.b)) {
      b b = a(paramc.c());
      e1 = this.b.a(b, paramMap);
      arrayOfO = a;
    } else {
      g g = (new c(e1.c())).a((Map)arrayOfO);
      e1 = this.b.a(g.d(), (Map)arrayOfO);
      arrayOfO = g.e();
    } 
    m m = new m(e1.b(), e1.a(), arrayOfO, com.a.a.a.l);
    List list = e1.c();
    if (list != null)
      m.a(n.c, list); 
    String str = e1.d();
    if (str != null)
      m.a(n.d, str); 
    return m;
  }
  
  public void a() {}
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\g\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */