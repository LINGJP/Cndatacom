package android.arch.lifecycle;

import android.support.annotation.Nullable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class a {
  static a a = new a();
  
  private final Map<Class, a> b = (Map)new HashMap<Class<?>, a>();
  
  private final Map<Class, Boolean> c = (Map)new HashMap<Class<?>, Boolean>();
  
  private a a(Class paramClass, @Nullable Method[] paramArrayOfMethod) {
    Class clazz = paramClass.getSuperclass();
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    if (clazz != null) {
      a a2 = b(clazz);
      if (a2 != null)
        hashMap.putAll(a2.b); 
    } 
    Class[] arrayOfClass = paramClass.getInterfaces();
    int j = arrayOfClass.length;
    int i;
    for (i = 0; i < j; i++) {
      for (Map.Entry<b, c.a> entry : (b(arrayOfClass[i])).b.entrySet())
        a((Map)hashMap, (b)entry.getKey(), (c.a)entry.getValue(), paramClass); 
    } 
    if (paramArrayOfMethod == null)
      paramArrayOfMethod = c(paramClass); 
    int k = paramArrayOfMethod.length;
    j = 0;
    boolean bool = false;
    while (j < k) {
      Method method = paramArrayOfMethod[j];
      l l = method.<l>getAnnotation(l.class);
      if (l != null) {
        Class[] arrayOfClass1 = method.getParameterTypes();
        if (arrayOfClass1.length > 0) {
          if (!arrayOfClass1[0].isAssignableFrom(e.class))
            throw new IllegalArgumentException("invalid parameter type. Must be one and instanceof LifecycleOwner"); 
          i = 1;
        } else {
          i = 0;
        } 
        c.a a2 = l.a();
        if (arrayOfClass1.length > 1) {
          if (!arrayOfClass1[1].isAssignableFrom(c.a.class))
            throw new IllegalArgumentException("invalid parameter type. second arg must be an event"); 
          if (a2 != c.a.ON_ANY)
            throw new IllegalArgumentException("Second arg is supported only for ON_ANY value"); 
          i = 2;
        } 
        if (arrayOfClass1.length > 2)
          throw new IllegalArgumentException("cannot have more than 2 params"); 
        a((Map)hashMap, new b(i, method), a2, paramClass);
        bool = true;
      } 
      j++;
    } 
    a a1 = new a((Map)hashMap);
    this.b.put(paramClass, a1);
    this.c.put(paramClass, Boolean.valueOf(bool));
    return a1;
  }
  
  private void a(Map<b, c.a> paramMap, b paramb, c.a parama, Class paramClass) {
    Method method;
    StringBuilder stringBuilder;
    c.a a1 = paramMap.get(paramb);
    if (a1 != null && parama != a1) {
      method = paramb.b;
      stringBuilder = new StringBuilder();
      stringBuilder.append("Method ");
      stringBuilder.append(method.getName());
      stringBuilder.append(" in ");
      stringBuilder.append(paramClass.getName());
      stringBuilder.append(" already declared with different @OnLifecycleEvent value: previous value ");
      stringBuilder.append(a1);
      stringBuilder.append(", new value ");
      stringBuilder.append(parama);
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
    if (a1 == null)
      method.put(stringBuilder, parama); 
  }
  
  private Method[] c(Class paramClass) {
    try {
      return paramClass.getDeclaredMethods();
    } catch (NoClassDefFoundError noClassDefFoundError) {
      throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", noClassDefFoundError);
    } 
  }
  
  boolean a(Class paramClass) {
    if (this.c.containsKey(paramClass))
      return ((Boolean)this.c.get(paramClass)).booleanValue(); 
    Method[] arrayOfMethod = c(paramClass);
    int j = arrayOfMethod.length;
    for (int i = 0; i < j; i++) {
      if ((l)arrayOfMethod[i].<l>getAnnotation(l.class) != null) {
        a(paramClass, arrayOfMethod);
        return true;
      } 
    } 
    this.c.put(paramClass, Boolean.valueOf(false));
    return false;
  }
  
  a b(Class paramClass) {
    a a1 = this.b.get(paramClass);
    return (a1 != null) ? a1 : a(paramClass, null);
  }
  
  static class a {
    final Map<c.a, List<a.b>> a;
    
    final Map<a.b, c.a> b;
    
    a(Map<a.b, c.a> param1Map) {
      this.b = param1Map;
      this.a = new HashMap<c.a, List<a.b>>();
      for (Map.Entry<a.b, c.a> entry : param1Map.entrySet()) {
        c.a a1 = (c.a)entry.getValue();
        List<a.b> list2 = this.a.get(a1);
        List<a.b> list1 = list2;
        if (list2 == null) {
          list1 = new ArrayList();
          this.a.put(a1, list1);
        } 
        list1.add((a.b)entry.getKey());
      } 
    }
    
    private static void a(List<a.b> param1List, e param1e, c.a param1a, Object param1Object) {
      if (param1List != null) {
        int i;
        for (i = param1List.size() - 1; i >= 0; i--)
          ((a.b)param1List.get(i)).a(param1e, param1a, param1Object); 
      } 
    }
    
    void a(e param1e, c.a param1a, Object param1Object) {
      a(this.a.get(param1a), param1e, param1a, param1Object);
      a(this.a.get(c.a.ON_ANY), param1e, param1a, param1Object);
    }
  }
  
  static class b {
    final int a;
    
    final Method b;
    
    b(int param1Int, Method param1Method) {
      this.a = param1Int;
      this.b = param1Method;
      this.b.setAccessible(true);
    }
    
    void a(e param1e, c.a param1a, Object param1Object) {
      try {
        switch (this.a) {
          case 2:
            this.b.invoke(param1Object, new Object[] { param1e, param1a });
            return;
          case 1:
            this.b.invoke(param1Object, new Object[] { param1e });
            return;
          case 0:
            this.b.invoke(param1Object, new Object[0]);
            return;
        } 
      } catch (InvocationTargetException invocationTargetException) {
        throw new RuntimeException("Failed to call observer method", invocationTargetException.getCause());
      } catch (IllegalAccessException illegalAccessException) {
        throw new RuntimeException(illegalAccessException);
      } 
    }
    
    public boolean equals(Object param1Object) {
      if (this == param1Object)
        return true; 
      if (param1Object != null) {
        if (getClass() != param1Object.getClass())
          return false; 
        param1Object = param1Object;
        return (this.a == ((b)param1Object).a && this.b.getName().equals(((b)param1Object).b.getName()));
      } 
      return false;
    }
    
    public int hashCode() {
      return this.a * 31 + this.b.getName().hashCode();
    }
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\android\arch\lifecycle\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */