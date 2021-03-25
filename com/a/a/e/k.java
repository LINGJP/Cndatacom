package com.a.a.e;

import com.a.a.b.a;
import com.a.a.c;
import com.a.a.e;
import com.a.a.i;
import com.a.a.k;
import com.a.a.l;
import com.a.a.m;
import com.a.a.n;
import com.a.a.o;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public abstract class k implements k {
  protected static int a(int[] paramArrayOfint1, int[] paramArrayOfint2, int paramInt) {
    int i1 = paramArrayOfint1.length;
    int n = 0;
    int m = 0;
    int i = 0;
    int j = 0;
    while (m < i1) {
      i += paramArrayOfint1[m];
      j += paramArrayOfint2[m];
      m++;
    } 
    if (i < j)
      return Integer.MAX_VALUE; 
    int i2 = (i << 8) / j;
    m = 0;
    for (j = n; j < i1; j++) {
      n = paramArrayOfint1[j] << 8;
      int i3 = paramArrayOfint2[j] * i2;
      if (n > i3) {
        n -= i3;
      } else {
        n = i3 - n;
      } 
      if (n > paramInt * i2 >> 8)
        return Integer.MAX_VALUE; 
      m += n;
    } 
    return m / i;
  }
  
  protected static void a(a parama, int paramInt, int[] paramArrayOfint) {
    int n = paramArrayOfint.length;
    Arrays.fill(paramArrayOfint, 0, n, 0);
    int i1 = parama.a();
    if (paramInt >= i1)
      throw i.a(); 
    int i = parama.a(paramInt) ^ true;
    int m = 0;
    int j = paramInt;
    paramInt = m;
    while (true) {
      m = paramInt;
      if (j < i1) {
        if (parama.a(j) ^ i) {
          paramArrayOfint[paramInt] = paramArrayOfint[paramInt] + 1;
        } else {
          if (++paramInt == n) {
            m = paramInt;
            break;
          } 
          paramArrayOfint[paramInt] = 1;
          if (i == 0) {
            i = 1;
          } else {
            i = 0;
          } 
        } 
        j++;
        continue;
      } 
      break;
    } 
    if (m != n && (m != n - 1 || j != i1))
      throw i.a(); 
  }
  
  private m b(c paramc, Map<e, ?> paramMap) {
    byte b;
    int n = paramc.a();
    int m = paramc.b();
    a a = new a(n);
    if (paramMap != null && paramMap.containsKey(e.d)) {
      i = 1;
    } else {
      i = 0;
    } 
    if (i) {
      j = 8;
    } else {
      j = 5;
    } 
    int i2 = Math.max(1, m >> j);
    if (i) {
      b = m;
    } else {
      b = 15;
    } 
    int i1 = 0;
    int j = m;
    int i = n;
    n = i1;
    label74: while (true) {
      if (n < b) {
        i1 = n + 1;
        int i3 = i1 >> 1;
        if ((n & 0x1) == 0) {
          n = 1;
        } else {
          n = 0;
        } 
        if (n != 0) {
          n = i3;
        } else {
          n = -i3;
        } 
        i3 = n * i2 + (m >> 1);
        if (i3 >= 0 && i3 < j) {
          try {
            a a1 = paramc.a(i3, a);
            n = 0;
            while (true) {
              if (n < 2) {
                Map<e, ?> map = paramMap;
                if (n == 1) {
                  a1.c();
                  map = paramMap;
                  if (paramMap != null) {
                    map = paramMap;
                    if (paramMap.containsKey(e.i)) {
                      map = new EnumMap<e, Object>(e.class);
                      map.putAll(paramMap);
                      map.remove(e.i);
                    } 
                  } 
                } 
                try {
                  m m1 = a(i3, a1, map);
                  if (n == 1) {
                    m1.a(n.b, Integer.valueOf(180));
                    o[] arrayOfO = m1.c();
                    if (arrayOfO != null) {
                      float f = i;
                      try {
                        float f1 = arrayOfO[0].a();
                        try {
                          arrayOfO[0] = new o(f - f1 - 1.0F, arrayOfO[0].b());
                          try {
                            arrayOfO[1] = new o(f - arrayOfO[1].a() - 1.0F, arrayOfO[1].b());
                            return m1;
                          } catch (l null) {}
                        } catch (l null) {}
                      } catch (l l) {}
                    } else {
                      return (m)l;
                    } 
                  } else {
                    return (m)l;
                  } 
                } catch (l l) {}
                n++;
                paramMap = map;
                continue;
              } 
              n = j;
              a = a1;
              j = i;
              i = n;
              n = i1;
              i1 = i;
              i = j;
              j = i1;
              continue label74;
            } 
          } catch (i i4) {
            n = i;
            i = j;
            j = n;
            continue;
          } 
          break;
        } 
      } 
      throw i.a();
    } 
  }
  
  protected static void b(a parama, int paramInt, int[] paramArrayOfint) {
    int i = paramArrayOfint.length;
    boolean bool = parama.a(paramInt);
    while (paramInt > 0 && i >= 0) {
      int j = paramInt - 1;
      paramInt = j;
      if (parama.a(j) != bool) {
        i--;
        if (!bool) {
          bool = true;
          paramInt = j;
          continue;
        } 
        bool = false;
        paramInt = j;
      } 
    } 
    if (i >= 0)
      throw i.a(); 
    a(parama, paramInt + 1, paramArrayOfint);
  }
  
  public abstract m a(int paramInt, a parama, Map<e, ?> paramMap);
  
  public m a(c paramc, Map<e, ?> paramMap) {
    try {
      return b(paramc, paramMap);
    } catch (i i1) {
      int i;
      o[] arrayOfO;
      boolean bool = false;
      if (paramMap != null && paramMap.containsKey(e.d)) {
        i = 1;
      } else {
        i = 0;
      } 
      if (i && paramc.d()) {
        paramc = paramc.e();
        m m = b(paramc, paramMap);
        Map map = m.e();
        int j = 270;
        i = j;
        if (map != null) {
          i = j;
          if (map.containsKey(n.b))
            i = (((Integer)map.get(n.b)).intValue() + 270) % 360; 
        } 
        m.a(n.b, Integer.valueOf(i));
        arrayOfO = m.c();
        if (arrayOfO != null) {
          j = paramc.b();
          for (i = bool; i < arrayOfO.length; i++)
            arrayOfO[i] = new o(j - arrayOfO[i].b() - 1.0F, arrayOfO[i].a()); 
        } 
        return m;
      } 
      throw arrayOfO;
    } 
  }
  
  public void a() {}
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\e\k.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */