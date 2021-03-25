package android.arch.lifecycle;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

public class o {
  private final a a;
  
  private final p b;
  
  public o(@NonNull p paramp, @NonNull a parama) {
    this.a = parama;
    this.b = paramp;
  }
  
  @MainThread
  @NonNull
  public <T extends n> T a(@NonNull Class<T> paramClass) {
    String str = paramClass.getCanonicalName();
    if (str == null)
      throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels"); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("android.arch.lifecycle.ViewModelProvider.DefaultKey:");
    stringBuilder.append(str);
    return a(stringBuilder.toString(), paramClass);
  }
  
  @MainThread
  @NonNull
  public <T extends n> T a(@NonNull String paramString, @NonNull Class<T> paramClass) {
    n n = this.b.a(paramString);
    if (paramClass.isInstance(n))
      return (T)n; 
    paramClass = this.a.create((Class)paramClass);
    this.b.a(paramString, (n)paramClass);
    return (T)paramClass;
  }
  
  public static interface a {
    @NonNull
    <T extends n> T create(@NonNull Class<T> param1Class);
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\android\arch\lifecycle\o.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */