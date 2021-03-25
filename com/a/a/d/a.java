package com.a.a.d;

import com.a.a.b.b;
import com.a.a.b.e;
import com.a.a.c;
import com.a.a.d.a.c;
import com.a.a.e;
import com.a.a.i;
import com.a.a.k;
import com.a.a.m;
import com.a.a.n;
import com.a.a.o;
import java.util.Map;

public final class a implements k {
  private static final o[] a = new o[0];
  
  private final c b = new c();
  
  private static b a(b paramb) {
    int[] arrayOfInt = paramb.a();
    if (arrayOfInt == null)
      throw i.a(); 
    int j = arrayOfInt[0];
    int m = arrayOfInt[1];
    int n = arrayOfInt[2];
    int i1 = arrayOfInt[3];
    b b1 = new b(30, 33);
    for (int i = 0; i < 33; i++) {
      int i3 = (i * i1 + i1 / 2) / 33;
      for (int i2 = 0; i2 < 30; i2++) {
        if (paramb.a((i2 * n + n / 2 + (i & 0x1) * n / 2) / 30 + j, i3 + m))
          b1.b(i2, i); 
      } 
    } 
    return b1;
  }
  
  public m a(c paramc, Map<e, ?> paramMap) {
    if (paramMap != null && paramMap.containsKey(e.b)) {
      b b = a(paramc.c());
      e e = this.b.a(b, paramMap);
      o[] arrayOfO = a;
      m m = new m(e.b(), e.a(), arrayOfO, com.a.a.a.j);
      String str = e.d();
      if (str != null)
        m.a(n.d, str); 
      return m;
    } 
    throw i.a();
  }
  
  public void a() {}
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\d\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */