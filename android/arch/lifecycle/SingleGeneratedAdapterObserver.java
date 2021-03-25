package android.arch.lifecycle;

import android.support.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class SingleGeneratedAdapterObserver implements GenericLifecycleObserver {
  private final b a;
  
  SingleGeneratedAdapterObserver(b paramb) {
    this.a = paramb;
  }
  
  public void a(e parame, c.a parama) {
    this.a.a(parame, parama, false, null);
    this.a.a(parame, parama, true, null);
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\android\arch\lifecycle\SingleGeneratedAdapterObserver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */