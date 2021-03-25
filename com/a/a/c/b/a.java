package com.a.a.c.b;

import com.a.a.b.g;
import com.a.a.b.i;
import com.a.a.i;
import com.a.a.o;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class a {
  private final com.a.a.b.b a;
  
  private final com.a.a.b.a.b b;
  
  public a(com.a.a.b.b paramb) {
    this.a = paramb;
    this.b = new com.a.a.b.a.b(paramb);
  }
  
  private static int a(o paramo1, o paramo2) {
    return com.a.a.b.a.a.a(o.a(paramo1, paramo2));
  }
  
  private static com.a.a.b.b a(com.a.a.b.b paramb, o paramo1, o paramo2, o paramo3, o paramo4, int paramInt1, int paramInt2) {
    i i = i.a();
    float f1 = paramInt1 - 0.5F;
    float f2 = paramInt2 - 0.5F;
    return i.a(paramb, paramInt1, paramInt2, 0.5F, 0.5F, f1, 0.5F, f1, f2, 0.5F, f2, paramo1.a(), paramo1.b(), paramo4.a(), paramo4.b(), paramo3.a(), paramo3.b(), paramo2.a(), paramo2.b());
  }
  
  private o a(o paramo1, o paramo2, o paramo3, o paramo4, int paramInt) {
    float f2 = a(paramo1, paramo2);
    float f1 = paramInt;
    f2 /= f1;
    paramInt = a(paramo3, paramo4);
    float f4 = paramo4.a();
    float f5 = paramo3.a();
    float f3 = paramInt;
    f4 = (f4 - f5) / f3;
    f3 = (paramo4.b() - paramo3.b()) / f3;
    o o1 = new o(paramo4.a() + f4 * f2, paramo4.b() + f2 * f3);
    f1 = a(paramo1, paramo3) / f1;
    paramInt = a(paramo2, paramo4);
    f3 = paramo4.a();
    f4 = paramo2.a();
    f2 = paramInt;
    f3 = (f3 - f4) / f2;
    f2 = (paramo4.b() - paramo2.b()) / f2;
    paramo4 = new o(paramo4.a() + f3 * f1, paramo4.b() + f1 * f2);
    if (!a(o1))
      return a(paramo4) ? paramo4 : null; 
    if (!a(paramo4))
      return o1; 
    paramo1 = paramo4;
    if (Math.abs(b(paramo3, o1).c() - b(paramo2, o1).c()) <= Math.abs(b(paramo3, paramo4).c() - b(paramo2, paramo4).c()))
      paramo1 = o1; 
    return paramo1;
  }
  
  private o a(o paramo1, o paramo2, o paramo3, o paramo4, int paramInt1, int paramInt2) {
    float f1 = a(paramo1, paramo2) / paramInt1;
    int i = a(paramo3, paramo4);
    float f3 = paramo4.a();
    float f4 = paramo3.a();
    float f2 = i;
    f3 = (f3 - f4) / f2;
    f2 = (paramo4.b() - paramo3.b()) / f2;
    o o1 = new o(paramo4.a() + f3 * f1, paramo4.b() + f1 * f2);
    f1 = a(paramo1, paramo3) / paramInt2;
    i = a(paramo2, paramo4);
    f3 = paramo4.a();
    f4 = paramo2.a();
    f2 = i;
    f3 = (f3 - f4) / f2;
    f2 = (paramo4.b() - paramo2.b()) / f2;
    paramo1 = new o(paramo4.a() + f3 * f1, paramo4.b() + f1 * f2);
    return !a(o1) ? (a(paramo1) ? paramo1 : null) : (!a(paramo1) ? o1 : ((Math.abs(paramInt1 - b(paramo3, o1).c()) + Math.abs(paramInt2 - b(paramo2, o1).c()) <= Math.abs(paramInt1 - b(paramo3, paramo1).c()) + Math.abs(paramInt2 - b(paramo2, paramo1).c())) ? o1 : paramo1));
  }
  
  private static void a(Map<o, Integer> paramMap, o paramo) {
    Integer integer = paramMap.get(paramo);
    int i = 1;
    if (integer != null)
      i = 1 + integer.intValue(); 
    paramMap.put(paramo, Integer.valueOf(i));
  }
  
  private boolean a(o paramo) {
    return (paramo.a() >= 0.0F && paramo.a() < this.a.d() && paramo.b() > 0.0F && paramo.b() < this.a.e());
  }
  
  private a b(o paramo1, o paramo2) {
    boolean bool;
    int i3 = (int)paramo1.a();
    int n = (int)paramo1.b();
    int m = (int)paramo2.a();
    int k = (int)paramo2.b();
    int i = Math.abs(k - n);
    int j = Math.abs(m - i3);
    int i4 = 0;
    if (i > j) {
      bool = true;
    } else {
      bool = false;
    } 
    i = i3;
    j = n;
    int i2 = m;
    int i1 = k;
    if (bool) {
      j = i3;
      i = n;
      i1 = m;
      i2 = k;
    } 
    int i5 = Math.abs(i2 - i);
    int i6 = Math.abs(i1 - j);
    n = -i5 >> 1;
    byte b1 = -1;
    if (j < i1) {
      i3 = 1;
    } else {
      i3 = -1;
    } 
    if (i < i2)
      b1 = 1; 
    com.a.a.b.b b2 = this.a;
    if (bool) {
      k = j;
    } else {
      k = i;
    } 
    if (bool) {
      m = i;
    } else {
      m = j;
    } 
    boolean bool1 = b2.a(k, m);
    k = i4;
    while (true) {
      m = k;
      if (i != i2) {
        b2 = this.a;
        if (bool) {
          m = j;
        } else {
          m = i;
        } 
        if (bool) {
          i4 = i;
        } else {
          i4 = j;
        } 
        boolean bool3 = b2.a(m, i4);
        m = k;
        boolean bool2 = bool1;
        if (bool3 != bool1) {
          m = k + 1;
          bool2 = bool3;
        } 
        i4 = n + i6;
        k = j;
        n = i4;
        if (i4 > 0) {
          if (j == i1)
            break; 
          k = j + i3;
          n = i4 - i5;
        } 
        i += b1;
        j = k;
        k = m;
        bool1 = bool2;
        continue;
      } 
      break;
    } 
    return new a(paramo1, paramo2, m);
  }
  
  public g a() {
    o o1;
    o o4;
    o[] arrayOfO2 = this.b.a();
    o o5 = arrayOfO2[0];
    o o6 = arrayOfO2[1];
    o o7 = arrayOfO2[2];
    o o8 = arrayOfO2[3];
    ArrayList<a> arrayList = new ArrayList(4);
    arrayList.add(b(o5, o6));
    arrayList.add(b(o5, o7));
    arrayList.add(b(o6, o8));
    arrayList.add(b(o7, o8));
    o o3 = null;
    Collections.sort(arrayList, new b());
    a a1 = arrayList.get(0);
    a a2 = arrayList.get(1);
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    a((Map)hashMap, a1.a());
    a((Map)hashMap, a1.b());
    a((Map)hashMap, a2.a());
    a((Map)hashMap, a2.b());
    Iterator<Map.Entry> iterator = hashMap.entrySet().iterator();
    a a3 = null;
    a2 = a3;
    while (iterator.hasNext()) {
      Map.Entry entry = iterator.next();
      o1 = (o)entry.getKey();
      if (((Integer)entry.getValue()).intValue() == 2) {
        o4 = o1;
        continue;
      } 
      if (o3 == null) {
        o3 = o1;
        continue;
      } 
      o2 = o1;
    } 
    if (o3 == null || o4 == null || o2 == null)
      throw i.a(); 
    o[] arrayOfO1 = new o[3];
    arrayOfO1[0] = o3;
    arrayOfO1[1] = o4;
    arrayOfO1[2] = o2;
    o.a(arrayOfO1);
    o o9 = arrayOfO1[0];
    o o10 = arrayOfO1[1];
    o3 = arrayOfO1[2];
    if (!hashMap.containsKey(o5)) {
      o1 = o5;
    } else if (!hashMap.containsKey(o6)) {
      o1 = o6;
    } else if (!hashMap.containsKey(o7)) {
      o1 = o7;
    } else {
      o1 = o8;
    } 
    int k = b(o3, o1).c();
    int j = b(o9, o1).c();
    int i = k;
    if ((k & 0x1) == 1)
      i = k + 1; 
    k = i + 2;
    i = j;
    if ((j & 0x1) == 1)
      i = j + 1; 
    i += 2;
    if (k * 4 >= i * 7 || i * 4 >= k * 7) {
      o2 = o3;
      o4 = a(o10, o9, o3, o1, k, i);
      if (o4 != null)
        o1 = o4; 
      j = b(o2, o1).c();
      k = b(o9, o1).c();
      i = j;
      if ((j & 0x1) == 1)
        i = j + 1; 
      j = k;
      if ((k & 0x1) == 1)
        j = k + 1; 
      com.a.a.b.b b2 = a(this.a, o2, o10, o9, o1, i, j);
      return new g(b2, new o[] { o3, o10, o9, o1 });
    } 
    o o2 = a(o10, o9, o3, o1, Math.min(i, k));
    if (o2 != null)
      o1 = o2; 
    j = Math.max(b(o3, o1).c(), b(o9, o1).c()) + 1;
    i = j;
    if ((j & 0x1) == 1)
      i = j + 1; 
    com.a.a.b.b b1 = a(this.a, o3, o10, o9, o1, i, i);
    return new g(b1, new o[] { o3, o10, o9, o1 });
  }
  
  private static final class a {
    private final o a;
    
    private final o b;
    
    private final int c;
    
    private a(o param1o1, o param1o2, int param1Int) {
      this.a = param1o1;
      this.b = param1o2;
      this.c = param1Int;
    }
    
    o a() {
      return this.a;
    }
    
    o b() {
      return this.b;
    }
    
    public int c() {
      return this.c;
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(this.a);
      stringBuilder.append("/");
      stringBuilder.append(this.b);
      stringBuilder.append('/');
      stringBuilder.append(this.c);
      return stringBuilder.toString();
    }
  }
  
  private static final class b implements Serializable, Comparator<a> {
    private b() {}
    
    public int a(a.a param1a1, a.a param1a2) {
      return param1a1.c() - param1a2.c();
    }
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\c\b\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */