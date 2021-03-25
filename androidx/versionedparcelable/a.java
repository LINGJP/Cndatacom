package androidx.versionedparcelable;

import android.os.Parcelable;
import android.support.annotation.RestrictTo;
import java.lang.reflect.InvocationTargetException;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public abstract class a {
  protected static <T extends c> T a(String paramString, a parama) {
    try {
      return (T)Class.forName(paramString, true, a.class.getClassLoader()).getDeclaredMethod("read", new Class[] { a.class }).invoke(null, new Object[] { parama });
    } catch (IllegalAccessException illegalAccessException) {
      throw new RuntimeException("VersionedParcel encountered IllegalAccessException", illegalAccessException);
    } catch (InvocationTargetException invocationTargetException) {
      if (invocationTargetException.getCause() instanceof RuntimeException)
        throw (RuntimeException)invocationTargetException.getCause(); 
      throw new RuntimeException("VersionedParcel encountered InvocationTargetException", invocationTargetException);
    } catch (NoSuchMethodException noSuchMethodException) {
      throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", noSuchMethodException);
    } catch (ClassNotFoundException classNotFoundException) {
      throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", classNotFoundException);
    } 
  }
  
  private static Class a(Class<? extends c> paramClass) {
    return Class.forName(String.format("%s.%sParcelizer", new Object[] { paramClass.getPackage().getName(), paramClass.getSimpleName() }), false, paramClass.getClassLoader());
  }
  
  protected static <T extends c> void a(T paramT, a parama) {
    try {
      c(paramT).getDeclaredMethod("write", new Class[] { paramT.getClass(), a.class }).invoke(null, new Object[] { paramT, parama });
      return;
    } catch (IllegalAccessException illegalAccessException) {
      throw new RuntimeException("VersionedParcel encountered IllegalAccessException", illegalAccessException);
    } catch (InvocationTargetException invocationTargetException) {
      if (invocationTargetException.getCause() instanceof RuntimeException)
        throw (RuntimeException)invocationTargetException.getCause(); 
      throw new RuntimeException("VersionedParcel encountered InvocationTargetException", invocationTargetException);
    } catch (NoSuchMethodException noSuchMethodException) {
      throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", noSuchMethodException);
    } catch (ClassNotFoundException classNotFoundException) {
      throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", classNotFoundException);
    } 
  }
  
  private void b(c paramc) {
    try {
      Class clazz = a((Class)paramc.getClass());
      a(clazz.getName());
      return;
    } catch (ClassNotFoundException classNotFoundException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(paramc.getClass().getSimpleName());
      stringBuilder.append(" does not have a Parcelizer");
      throw new RuntimeException(stringBuilder.toString(), classNotFoundException);
    } 
  }
  
  private static <T extends c> Class c(T paramT) {
    return a((Class)paramT.getClass());
  }
  
  protected abstract void a(int paramInt);
  
  public void a(int paramInt1, int paramInt2) {
    c(paramInt2);
    a(paramInt1);
  }
  
  protected abstract void a(Parcelable paramParcelable);
  
  public void a(Parcelable paramParcelable, int paramInt) {
    c(paramInt);
    a(paramParcelable);
  }
  
  protected void a(c paramc) {
    if (paramc == null) {
      a((String)null);
      return;
    } 
    b(paramc);
    a a1 = c();
    a(paramc, a1);
    a1.b();
  }
  
  protected abstract void a(String paramString);
  
  public void a(String paramString, int paramInt) {
    c(paramInt);
    a(paramString);
  }
  
  public void a(boolean paramBoolean1, boolean paramBoolean2) {}
  
  protected abstract void a(byte[] paramArrayOfbyte);
  
  public void a(byte[] paramArrayOfbyte, int paramInt) {
    c(paramInt);
    a(paramArrayOfbyte);
  }
  
  public boolean a() {
    return false;
  }
  
  public int b(int paramInt1, int paramInt2) {
    return !b(paramInt2) ? paramInt1 : d();
  }
  
  public <T extends Parcelable> T b(T paramT, int paramInt) {
    return !b(paramInt) ? paramT : g();
  }
  
  public String b(String paramString, int paramInt) {
    return !b(paramInt) ? paramString : e();
  }
  
  protected abstract void b();
  
  protected abstract boolean b(int paramInt);
  
  public byte[] b(byte[] paramArrayOfbyte, int paramInt) {
    return !b(paramInt) ? paramArrayOfbyte : f();
  }
  
  protected abstract a c();
  
  protected abstract void c(int paramInt);
  
  protected abstract int d();
  
  protected abstract String e();
  
  protected abstract byte[] f();
  
  protected abstract <T extends Parcelable> T g();
  
  protected <T extends c> T h() {
    String str = e();
    return (str == null) ? null : a(str, c());
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\androidx\versionedparcelable\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */