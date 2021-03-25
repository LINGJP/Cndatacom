package android.arch.lifecycle;

import android.support.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class CompositeGeneratedAdaptersObserver implements GenericLifecycleObserver {
  private final b[] a;
  
  CompositeGeneratedAdaptersObserver(b[] paramArrayOfb) {
    this.a = paramArrayOfb;
  }
  
  public void a(e parame, c.a parama) {
    i i1 = new i();
    b[] arrayOfB = this.a;
    int j = arrayOfB.length;
    boolean bool = false;
    int i;
    for (i = 0; i < j; i++)
      arrayOfB[i].a(parame, parama, false, i1); 
    arrayOfB = this.a;
    j = arrayOfB.length;
    for (i = bool; i < j; i++)
      arrayOfB[i].a(parame, parama, true, i1); 
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\android\arch\lifecycle\CompositeGeneratedAdaptersObserver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */