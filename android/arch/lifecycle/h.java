package android.arch.lifecycle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class h {
  private static Map<Class, Integer> a = (Map)new HashMap<Class<?>, Integer>();
  
  private static Map<Class, List<Constructor<? extends b>>> b = (Map)new HashMap<Class<?>, List<Constructor<? extends b>>>();
  
  @NonNull
  static GenericLifecycleObserver a(Object paramObject) {
    if (paramObject instanceof FullLifecycleObserver)
      return new FullLifecycleObserverAdapter((FullLifecycleObserver)paramObject); 
    if (paramObject instanceof GenericLifecycleObserver)
      return (GenericLifecycleObserver)paramObject; 
    Class<?> clazz = paramObject.getClass();
    if (b(clazz) == 2) {
      List<Constructor<? extends b>> list = b.get(clazz);
      int j = list.size();
      int i = 0;
      if (j == 1)
        return new SingleGeneratedAdapterObserver(a(list.get(0), paramObject)); 
      b[] arrayOfB = new b[list.size()];
      while (i < list.size()) {
        arrayOfB[i] = a(list.get(i), paramObject);
        i++;
      } 
      return new CompositeGeneratedAdaptersObserver(arrayOfB);
    } 
    return new ReflectiveGenericLifecycleObserver(paramObject);
  }
  
  private static b a(Constructor<? extends b> paramConstructor, Object paramObject) {
    try {
      return paramConstructor.newInstance(new Object[] { paramObject });
    } catch (IllegalAccessException illegalAccessException) {
      throw new RuntimeException(illegalAccessException);
    } catch (InstantiationException instantiationException) {
      throw new RuntimeException(instantiationException);
    } catch (InvocationTargetException invocationTargetException) {
      throw new RuntimeException(invocationTargetException);
    } 
  }
  
  public static String a(String paramString) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramString.replace(".", "_"));
    stringBuilder.append("_LifecycleAdapter");
    return stringBuilder.toString();
  }
  
  @Nullable
  private static Constructor<? extends b> a(Class<?> paramClass) {
    try {
      String str1;
      Package package_ = paramClass.getPackage();
      String str2 = paramClass.getCanonicalName();
      if (package_ != null) {
        str1 = package_.getName();
      } else {
        str1 = "";
      } 
      if (!str1.isEmpty())
        str2 = str2.substring(str1.length() + 1); 
      str2 = a(str2);
      if (str1.isEmpty()) {
        str1 = str2;
      } else {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str1);
        stringBuilder.append(".");
        stringBuilder.append(str2);
        str1 = stringBuilder.toString();
      } 
      Constructor<?> constructor = Class.forName(str1).getDeclaredConstructor(new Class[] { paramClass });
      if (!constructor.isAccessible())
        constructor.setAccessible(true); 
      return (Constructor)constructor;
    } catch (ClassNotFoundException classNotFoundException) {
      return null;
    } catch (NoSuchMethodException noSuchMethodException) {
      throw new RuntimeException(noSuchMethodException);
    } 
  }
  
  private static int b(Class<?> paramClass) {
    if (a.containsKey(paramClass))
      return ((Integer)a.get(paramClass)).intValue(); 
    int i = c(paramClass);
    a.put(paramClass, Integer.valueOf(i));
    return i;
  }
  
  private static int c(Class<?> paramClass) {
    ArrayList<Constructor<? extends b>> arrayList;
    if (paramClass.getCanonicalName() == null)
      return 1; 
    Constructor<? extends b> constructor = a(paramClass);
    if (constructor != null) {
      b.put(paramClass, Collections.singletonList(constructor));
      return 2;
    } 
    if (a.a.a(paramClass))
      return 1; 
    Class<?> clazz = paramClass.getSuperclass();
    constructor = null;
    if (d(clazz)) {
      if (b(clazz) == 1)
        return 1; 
      arrayList = new ArrayList(b.get(clazz));
    } 
    for (Class<?> clazz1 : paramClass.getInterfaces()) {
      if (d(clazz1)) {
        if (b(clazz1) == 1)
          return 1; 
        ArrayList<Constructor<? extends b>> arrayList1 = arrayList;
        if (arrayList == null)
          arrayList1 = new ArrayList(); 
        arrayList1.addAll(b.get(clazz1));
        arrayList = arrayList1;
      } 
    } 
    if (arrayList != null) {
      b.put(paramClass, arrayList);
      return 2;
    } 
    return 1;
  }
  
  private static boolean d(Class<?> paramClass) {
    return (paramClass != null && d.class.isAssignableFrom(paramClass));
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\android\arch\lifecycle\h.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */