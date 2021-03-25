package android.arch.lifecycle;

class FullLifecycleObserverAdapter implements GenericLifecycleObserver {
  private final FullLifecycleObserver a;
  
  FullLifecycleObserverAdapter(FullLifecycleObserver paramFullLifecycleObserver) {
    this.a = paramFullLifecycleObserver;
  }
  
  public void a(e parame, c.a parama) {
    switch (null.a[parama.ordinal()]) {
      default:
        return;
      case 7:
        throw new IllegalArgumentException("ON_ANY must not been send by anybody");
      case 6:
        this.a.f(parame);
        return;
      case 5:
        this.a.e(parame);
        return;
      case 4:
        this.a.d(parame);
        return;
      case 3:
        this.a.c(parame);
        return;
      case 2:
        this.a.b(parame);
        return;
      case 1:
        break;
    } 
    this.a.a(parame);
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\android\arch\lifecycle\FullLifecycleObserverAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */