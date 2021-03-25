package android.arch.a.b;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import java.util.HashMap;
import java.util.Map;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class a<K, V> extends b<K, V> {
  private HashMap<K, b.c<K, V>> a = new HashMap<K, b.c<K, V>>();
  
  protected b.c<K, V> a(K paramK) {
    return this.a.get(paramK);
  }
  
  public V a(@NonNull K paramK, @NonNull V paramV) {
    b.c<K, V> c = a(paramK);
    if (c != null)
      return c.b; 
    this.a.put(paramK, b(paramK, paramV));
    return null;
  }
  
  public V b(@NonNull K paramK) {
    V v = super.b(paramK);
    this.a.remove(paramK);
    return v;
  }
  
  public boolean c(K paramK) {
    return this.a.containsKey(paramK);
  }
  
  public Map.Entry<K, V> d(K paramK) {
    return c(paramK) ? ((b.c)this.a.get(paramK)).d : null;
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\android\arch\a\b\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */