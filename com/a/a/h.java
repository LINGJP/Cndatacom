package com.a.a;

import com.a.a.a.b;
import com.a.a.c.a;
import com.a.a.d.a;
import com.a.a.e.i;
import com.a.a.f.a;
import com.a.a.g.a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public final class h implements k {
  private Map<e, ?> a;
  
  private k[] b;
  
  private m b(c paramc) {
    if (this.b != null) {
      k[] arrayOfK = this.b;
      int j = arrayOfK.length;
      int i = 0;
      while (true) {
        if (i < j) {
          k k1 = arrayOfK[i];
          try {
            return k1.a(paramc, this.a);
          } catch (l l) {
            i++;
            continue;
          } 
        } 
        throw i.a();
      } 
    } 
    throw i.a();
  }
  
  public m a(c paramc) {
    if (this.b == null)
      a((Map<e, ?>)null); 
    return b(paramc);
  }
  
  public m a(c paramc, Map<e, ?> paramMap) {
    a(paramMap);
    return b(paramc);
  }
  
  public void a() {
    if (this.b != null) {
      k[] arrayOfK = this.b;
      int j = arrayOfK.length;
      for (int i = 0; i < j; i++)
        arrayOfK[i].a(); 
    } 
  }
  
  public void a(Map<e, ?> paramMap) {
    boolean bool1;
    Collection collection;
    this.a = paramMap;
    boolean bool2 = false;
    if (paramMap != null && paramMap.containsKey(e.d)) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    if (paramMap == null) {
      collection = null;
    } else {
      collection = (Collection)paramMap.get(e.c);
    } 
    ArrayList<i> arrayList = new ArrayList();
    if (collection != null) {
      if (collection.contains(a.o) || collection.contains(a.p) || collection.contains(a.h) || collection.contains(a.g) || collection.contains(a.b) || collection.contains(a.c) || collection.contains(a.d) || collection.contains(a.e) || collection.contains(a.i) || collection.contains(a.m) || collection.contains(a.n))
        bool2 = true; 
      if (bool2 && !bool1)
        arrayList.add(new i(paramMap)); 
      if (collection.contains(a.l))
        arrayList.add(new a()); 
      if (collection.contains(a.f))
        arrayList.add(new a()); 
      if (collection.contains(a.a))
        arrayList.add(new b()); 
      if (collection.contains(a.k))
        arrayList.add(new a()); 
      if (collection.contains(a.j))
        arrayList.add(new a()); 
      if (bool2 && bool1)
        arrayList.add(new i(paramMap)); 
    } 
    if (arrayList.isEmpty()) {
      if (!bool1)
        arrayList.add(new i(paramMap)); 
      arrayList.add(new a());
      arrayList.add(new a());
      arrayList.add(new b());
      arrayList.add(new a());
      arrayList.add(new a());
      if (bool1)
        arrayList.add(new i(paramMap)); 
    } 
    this.b = arrayList.<k>toArray(new k[arrayList.size()]);
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\h.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */