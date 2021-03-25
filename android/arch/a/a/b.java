package android.arch.a.a;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class b extends c {
  private final Object a = new Object();
  
  private ExecutorService b = Executors.newFixedThreadPool(2);
  
  @Nullable
  private volatile Handler c;
  
  public void a(Runnable paramRunnable) {
    this.b.execute(paramRunnable);
  }
  
  public void b(Runnable paramRunnable) {
    if (this.c == null)
      synchronized (this.a) {
        if (this.c == null)
          this.c = new Handler(Looper.getMainLooper()); 
      }  
    this.c.post(paramRunnable);
  }
  
  public boolean b() {
    return (Looper.getMainLooper().getThread() == Thread.currentThread());
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\android\arch\a\a\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */