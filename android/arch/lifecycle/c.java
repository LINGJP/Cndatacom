package android.arch.lifecycle;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

public abstract class c {
  @MainThread
  @NonNull
  public abstract b a();
  
  @MainThread
  public abstract void a(@NonNull d paramd);
  
  @MainThread
  public abstract void b(@NonNull d paramd);
  
  public enum a {
    ON_ANY, ON_CREATE, ON_DESTROY, ON_PAUSE, ON_RESUME, ON_START, ON_STOP;
    
    static {
      ON_PAUSE = new a("ON_PAUSE", 3);
      ON_STOP = new a("ON_STOP", 4);
      ON_DESTROY = new a("ON_DESTROY", 5);
      ON_ANY = new a("ON_ANY", 6);
      $VALUES = new a[] { ON_CREATE, ON_START, ON_RESUME, ON_PAUSE, ON_STOP, ON_DESTROY, ON_ANY };
    }
  }
  
  public enum b {
    a, b, c, d, e;
    
    public boolean a(@NonNull b param1b) {
      return (compareTo(param1b) >= 0);
    }
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\android\arch\lifecycle\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */