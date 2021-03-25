package android.arch.lifecycle;

import java.util.HashMap;
import java.util.Iterator;

public class p {
  private final HashMap<String, n> a = new HashMap<String, n>();
  
  final n a(String paramString) {
    return this.a.get(paramString);
  }
  
  public final void a() {
    Iterator<n> iterator = this.a.values().iterator();
    while (iterator.hasNext())
      ((n)iterator.next()).onCleared(); 
    this.a.clear();
  }
  
  final void a(String paramString, n paramn) {
    n n1 = this.a.put(paramString, paramn);
    if (n1 != null)
      n1.onCleared(); 
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\android\arch\lifecycle\p.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */