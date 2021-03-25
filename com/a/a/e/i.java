package com.a.a.e;

import com.a.a.a;
import com.a.a.b.a;
import com.a.a.e;
import com.a.a.e.a.a.d;
import com.a.a.e.a.e;
import com.a.a.l;
import com.a.a.m;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public final class i extends k {
  private final k[] a;
  
  public i(Map<e, ?> paramMap) {
    boolean bool;
    Collection collection;
    if (paramMap == null) {
      collection = null;
    } else {
      collection = (Collection)paramMap.get(e.c);
    } 
    if (paramMap != null && paramMap.get(e.g) != null) {
      bool = true;
    } else {
      bool = false;
    } 
    ArrayList<j> arrayList = new ArrayList();
    if (collection != null) {
      if (collection.contains(a.h) || collection.contains(a.o) || collection.contains(a.g) || collection.contains(a.p))
        arrayList.add(new j(paramMap)); 
      if (collection.contains(a.c))
        arrayList.add(new c(bool)); 
      if (collection.contains(a.d))
        arrayList.add(new d()); 
      if (collection.contains(a.e))
        arrayList.add(new b()); 
      if (collection.contains(a.i))
        arrayList.add(new h()); 
      if (collection.contains(a.b))
        arrayList.add(new a()); 
      if (collection.contains(a.m))
        arrayList.add(new e()); 
      if (collection.contains(a.n))
        arrayList.add(new d()); 
    } 
    if (arrayList.isEmpty()) {
      arrayList.add(new j(paramMap));
      arrayList.add(new c());
      arrayList.add(new a());
      arrayList.add(new d());
      arrayList.add(new b());
      arrayList.add(new h());
      arrayList.add(new e());
      arrayList.add(new d());
    } 
    this.a = arrayList.<k>toArray(new k[arrayList.size()]);
  }
  
  public m a(int paramInt, a parama, Map<e, ?> paramMap) {
    k[] arrayOfK = this.a;
    int m = arrayOfK.length;
    int j = 0;
    while (true) {
      if (j < m) {
        k k1 = arrayOfK[j];
        try {
          return k1.a(paramInt, parama, paramMap);
        } catch (l l) {
          j++;
          continue;
        } 
      } 
      throw com.a.a.i.a();
    } 
  }
  
  public void a() {
    k[] arrayOfK = this.a;
    int m = arrayOfK.length;
    for (int j = 0; j < m; j++)
      arrayOfK[j].a(); 
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\e\i.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */