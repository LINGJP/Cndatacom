package android.arch.a.b;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class b<K, V> implements Iterable<Map.Entry<K, V>> {
  private c<K, V> a;
  
  private c<K, V> b;
  
  private WeakHashMap<f<K, V>, Boolean> c = new WeakHashMap<f<K, V>, Boolean>();
  
  private int d = 0;
  
  public int a() {
    return this.d;
  }
  
  protected c<K, V> a(K paramK) {
    c<K, V> c1;
    for (c1 = this.a; c1 != null; c1 = c1.c) {
      if (c1.a.equals(paramK))
        return c1; 
    } 
    return c1;
  }
  
  public V a(@NonNull K paramK, @NonNull V paramV) {
    c<K, V> c1 = a(paramK);
    if (c1 != null)
      return c1.b; 
    b(paramK, paramV);
    return null;
  }
  
  protected c<K, V> b(@NonNull K paramK, @NonNull V paramV) {
    c<K, V> c1 = new c<K, V>(paramK, paramV);
    this.d++;
    if (this.b == null) {
      this.a = c1;
      this.b = this.a;
      return c1;
    } 
    this.b.c = c1;
    c1.d = this.b;
    this.b = c1;
    return c1;
  }
  
  public V b(@NonNull K paramK) {
    c<K, V> c1 = a(paramK);
    if (c1 == null)
      return null; 
    this.d--;
    if (!this.c.isEmpty()) {
      Iterator<f<K, V>> iterator = this.c.keySet().iterator();
      while (iterator.hasNext())
        ((f<K, V>)iterator.next()).a_(c1); 
    } 
    if (c1.d != null) {
      c1.d.c = c1.c;
    } else {
      this.a = c1.c;
    } 
    if (c1.c != null) {
      c1.c.d = c1.d;
    } else {
      this.b = c1.d;
    } 
    c1.c = null;
    c1.d = null;
    return c1.b;
  }
  
  public Iterator<Map.Entry<K, V>> b() {
    b<K, V> b1 = new b<K, V>(this.b, this.a);
    this.c.put(b1, Boolean.valueOf(false));
    return b1;
  }
  
  public d c() {
    d d = new d();
    this.c.put(d, Boolean.valueOf(false));
    return d;
  }
  
  public Map.Entry<K, V> d() {
    return this.a;
  }
  
  public Map.Entry<K, V> e() {
    return this.b;
  }
  
  public boolean equals(Object<Map.Entry<K, V>> paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof b))
      return false; 
    b b1 = (b)paramObject;
    if (a() != b1.a())
      return false; 
    paramObject = (Object<Map.Entry<K, V>>)iterator();
    Iterator<Object> iterator = b1.iterator();
    while (paramObject.hasNext() && iterator.hasNext()) {
      Map.Entry entry = paramObject.next();
      Object object = iterator.next();
      if ((entry == null && object != null) || (entry != null && !entry.equals(object)))
        return false; 
    } 
    return (!paramObject.hasNext() && !iterator.hasNext());
  }
  
  @NonNull
  public Iterator<Map.Entry<K, V>> iterator() {
    a<K, V> a = new a<K, V>(this.a, this.b);
    this.c.put(a, Boolean.valueOf(false));
    return a;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("[");
    Iterator<Map.Entry<K, V>> iterator = iterator();
    while (iterator.hasNext()) {
      stringBuilder.append(((Map.Entry)iterator.next()).toString());
      if (iterator.hasNext())
        stringBuilder.append(", "); 
    } 
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
  
  static class a<K, V> extends e<K, V> {
    a(b.c<K, V> param1c1, b.c<K, V> param1c2) {
      super(param1c1, param1c2);
    }
    
    b.c<K, V> a(b.c<K, V> param1c) {
      return param1c.c;
    }
    
    b.c<K, V> b(b.c<K, V> param1c) {
      return param1c.d;
    }
  }
  
  private static class b<K, V> extends e<K, V> {
    b(b.c<K, V> param1c1, b.c<K, V> param1c2) {
      super(param1c1, param1c2);
    }
    
    b.c<K, V> a(b.c<K, V> param1c) {
      return param1c.d;
    }
    
    b.c<K, V> b(b.c<K, V> param1c) {
      return param1c.c;
    }
  }
  
  static class c<K, V> implements Map.Entry<K, V> {
    @NonNull
    final K a;
    
    @NonNull
    final V b;
    
    c<K, V> c;
    
    c<K, V> d;
    
    c(@NonNull K param1K, @NonNull V param1V) {
      this.a = param1K;
      this.b = param1V;
    }
    
    public boolean equals(Object param1Object) {
      if (param1Object == this)
        return true; 
      if (!(param1Object instanceof c))
        return false; 
      param1Object = param1Object;
      return (this.a.equals(((c)param1Object).a) && this.b.equals(((c)param1Object).b));
    }
    
    @NonNull
    public K getKey() {
      return this.a;
    }
    
    @NonNull
    public V getValue() {
      return this.b;
    }
    
    public V setValue(V param1V) {
      throw new UnsupportedOperationException("An entry modification is not supported");
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(this.a);
      stringBuilder.append("=");
      stringBuilder.append(this.b);
      return stringBuilder.toString();
    }
  }
  
  private class d implements f<K, V>, Iterator<Map.Entry<K, V>> {
    private b.c<K, V> b;
    
    private boolean c = true;
    
    private d(b this$0) {}
    
    public Map.Entry<K, V> a() {
      if (this.c) {
        this.c = false;
        this.b = b.a(this.a);
      } else {
        b.c c1;
        if (this.b != null) {
          c1 = this.b.c;
        } else {
          c1 = null;
        } 
        this.b = c1;
      } 
      return this.b;
    }
    
    public void a_(@NonNull b.c<K, V> param1c) {
      if (param1c == this.b) {
        boolean bool;
        this.b = this.b.d;
        if (this.b == null) {
          bool = true;
        } else {
          bool = false;
        } 
        this.c = bool;
      } 
    }
    
    public boolean hasNext() {
      boolean bool = this.c;
      boolean bool2 = false;
      boolean bool1 = false;
      if (bool) {
        if (b.a(this.a) != null)
          bool1 = true; 
        return bool1;
      } 
      bool1 = bool2;
      if (this.b != null) {
        bool1 = bool2;
        if (this.b.c != null)
          bool1 = true; 
      } 
      return bool1;
    }
  }
  
  private static abstract class e<K, V> implements f<K, V>, Iterator<Map.Entry<K, V>> {
    b.c<K, V> a;
    
    b.c<K, V> b;
    
    e(b.c<K, V> param1c1, b.c<K, V> param1c2) {
      this.a = param1c2;
      this.b = param1c1;
    }
    
    private b.c<K, V> b() {
      return (this.b == this.a || this.a == null) ? null : a(this.b);
    }
    
    abstract b.c<K, V> a(b.c<K, V> param1c);
    
    public Map.Entry<K, V> a() {
      b.c<K, V> c1 = this.b;
      this.b = b();
      return c1;
    }
    
    public void a_(@NonNull b.c<K, V> param1c) {
      if (this.a == param1c && param1c == this.b) {
        this.b = null;
        this.a = null;
      } 
      if (this.a == param1c)
        this.a = b(this.a); 
      if (this.b == param1c)
        this.b = b(); 
    }
    
    abstract b.c<K, V> b(b.c<K, V> param1c);
    
    public boolean hasNext() {
      return (this.b != null);
    }
  }
  
  static interface f<K, V> {
    void a_(@NonNull b.c<K, V> param1c);
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\android\arch\a\b\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */