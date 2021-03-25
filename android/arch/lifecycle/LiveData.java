package android.arch.lifecycle;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.Map;

public abstract class LiveData<T> {
  private static final Object NOT_SET = new Object();
  
  static final int START_VERSION = -1;
  
  private int mActiveCount = 0;
  
  private volatile Object mData = NOT_SET;
  
  private final Object mDataLock = new Object();
  
  private boolean mDispatchInvalidated;
  
  private boolean mDispatchingValue;
  
  private android.arch.a.b.b<k<T>, b> mObservers = new android.arch.a.b.b();
  
  private volatile Object mPendingData = NOT_SET;
  
  private final Runnable mPostValueRunnable = new Runnable(this) {
      public void run() {
        synchronized (this.a.mDataLock) {
          Object object = this.a.mPendingData;
          LiveData.access$102(this.a, LiveData.NOT_SET);
          this.a.setValue(object);
          return;
        } 
      }
    };
  
  private int mVersion = -1;
  
  private static void assertMainThread(String paramString) {
    if (!android.arch.a.a.a.a().b()) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Cannot invoke ");
      stringBuilder.append(paramString);
      stringBuilder.append(" on a background");
      stringBuilder.append(" thread");
      throw new IllegalStateException(stringBuilder.toString());
    } 
  }
  
  private void considerNotify(b paramb) {
    if (!paramb.d)
      return; 
    if (!paramb.a()) {
      paramb.a(false);
      return;
    } 
    if (paramb.e >= this.mVersion)
      return; 
    paramb.e = this.mVersion;
    paramb.c.onChanged((T)this.mData);
  }
  
  private void dispatchingValue(@Nullable b paramb) {
    if (this.mDispatchingValue) {
      this.mDispatchInvalidated = true;
      return;
    } 
    this.mDispatchingValue = true;
    while (true) {
      b b1;
      this.mDispatchInvalidated = false;
      if (paramb != null) {
        considerNotify(paramb);
        b1 = null;
      } else {
        android.arch.a.b.b.d<Map.Entry> d = this.mObservers.c();
        while (true) {
          b1 = paramb;
          if (d.hasNext()) {
            considerNotify((b)((Map.Entry)d.next()).getValue());
            if (this.mDispatchInvalidated) {
              b1 = paramb;
              break;
            } 
            continue;
          } 
          break;
        } 
      } 
      paramb = b1;
      if (!this.mDispatchInvalidated) {
        this.mDispatchingValue = false;
        return;
      } 
    } 
  }
  
  @Nullable
  public T getValue() {
    Object object = this.mData;
    return (T)((object != NOT_SET) ? object : null);
  }
  
  int getVersion() {
    return this.mVersion;
  }
  
  public boolean hasActiveObservers() {
    return (this.mActiveCount > 0);
  }
  
  public boolean hasObservers() {
    return (this.mObservers.a() > 0);
  }
  
  @MainThread
  public void observe(@NonNull e parame, @NonNull k<T> paramk) {
    if (parame.getLifecycle().a() == c.b.a)
      return; 
    LifecycleBoundObserver lifecycleBoundObserver = new LifecycleBoundObserver(this, parame, paramk);
    b b1 = (b)this.mObservers.a(paramk, lifecycleBoundObserver);
    if (b1 != null && !b1.a(parame))
      throw new IllegalArgumentException("Cannot add the same observer with different lifecycles"); 
    if (b1 != null)
      return; 
    parame.getLifecycle().a(lifecycleBoundObserver);
  }
  
  @MainThread
  public void observeForever(@NonNull k<T> paramk) {
    a a = new a(this, paramk);
    b b1 = (b)this.mObservers.a(paramk, a);
    if (b1 != null && b1 instanceof LifecycleBoundObserver)
      throw new IllegalArgumentException("Cannot add the same observer with different lifecycles"); 
    if (b1 != null)
      return; 
    a.a(true);
  }
  
  protected void onActive() {}
  
  protected void onInactive() {}
  
  protected void postValue(T paramT) {
    synchronized (this.mDataLock) {
      boolean bool;
      if (this.mPendingData == NOT_SET) {
        bool = true;
      } else {
        bool = false;
      } 
      this.mPendingData = paramT;
      if (!bool)
        return; 
      android.arch.a.a.a.a().b(this.mPostValueRunnable);
      return;
    } 
  }
  
  @MainThread
  public void removeObserver(@NonNull k<T> paramk) {
    assertMainThread("removeObserver");
    b b1 = (b)this.mObservers.b(paramk);
    if (b1 == null)
      return; 
    b1.b();
    b1.a(false);
  }
  
  @MainThread
  public void removeObservers(@NonNull e parame) {
    assertMainThread("removeObservers");
    for (Map.Entry entry : this.mObservers) {
      if (((b)entry.getValue()).a(parame))
        removeObserver((k<T>)entry.getKey()); 
    } 
  }
  
  @MainThread
  protected void setValue(T paramT) {
    assertMainThread("setValue");
    this.mVersion++;
    this.mData = paramT;
    dispatchingValue(null);
  }
  
  class LifecycleBoundObserver extends b implements GenericLifecycleObserver {
    @NonNull
    final e a;
    
    LifecycleBoundObserver(LiveData this$0, @NonNull e param1e, k<T> param1k) {
      super(this$0, param1k);
      this.a = param1e;
    }
    
    public void a(e param1e, c.a param1a) {
      if (this.a.getLifecycle().a() == c.b.a) {
        this.b.removeObserver(this.c);
        return;
      } 
      a(a());
    }
    
    boolean a() {
      return this.a.getLifecycle().a().a(c.b.d);
    }
    
    boolean a(e param1e) {
      return (this.a == param1e);
    }
    
    void b() {
      this.a.getLifecycle().b(this);
    }
  }
  
  private class a extends b {
    a(LiveData this$0, k<T> param1k) {
      super(this$0, param1k);
    }
    
    boolean a() {
      return true;
    }
  }
  
  private abstract class b {
    final k<T> c;
    
    boolean d;
    
    int e = -1;
    
    b(LiveData this$0, k<T> param1k) {
      this.c = param1k;
    }
    
    void a(boolean param1Boolean) {
      if (param1Boolean == this.d)
        return; 
      this.d = param1Boolean;
      int i = this.f.mActiveCount;
      byte b1 = 1;
      if (i == 0) {
        i = 1;
      } else {
        i = 0;
      } 
      LiveData liveData = this.f;
      int j = liveData.mActiveCount;
      if (!this.d)
        b1 = -1; 
      LiveData.access$302(liveData, j + b1);
      if (i != 0 && this.d)
        this.f.onActive(); 
      if (this.f.mActiveCount == 0 && !this.d)
        this.f.onInactive(); 
      if (this.d)
        this.f.dispatchingValue(this); 
    }
    
    abstract boolean a();
    
    boolean a(e param1e) {
      return false;
    }
    
    void b() {}
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\android\arch\lifecycle\LiveData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */