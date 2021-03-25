package com.a.a.e.a.a;

import com.a.a.e.a.c;

final class b {
  private final boolean a;
  
  private final com.a.a.e.a.b b;
  
  private final com.a.a.e.a.b c;
  
  private final c d;
  
  b(com.a.a.e.a.b paramb1, com.a.a.e.a.b paramb2, c paramc, boolean paramBoolean) {
    this.b = paramb1;
    this.c = paramb2;
    this.d = paramc;
    this.a = paramBoolean;
  }
  
  private static int a(Object paramObject) {
    return (paramObject == null) ? 0 : paramObject.hashCode();
  }
  
  private static boolean a(Object paramObject1, Object paramObject2) {
    return (paramObject1 == null) ? ((paramObject2 == null)) : paramObject1.equals(paramObject2);
  }
  
  com.a.a.e.a.b a() {
    return this.b;
  }
  
  com.a.a.e.a.b b() {
    return this.c;
  }
  
  c c() {
    return this.d;
  }
  
  public boolean d() {
    return (this.c == null);
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = paramObject instanceof b;
    boolean bool1 = false;
    if (!bool)
      return false; 
    paramObject = paramObject;
    bool = bool1;
    if (a(this.b, ((b)paramObject).b)) {
      bool = bool1;
      if (a(this.c, ((b)paramObject).c)) {
        bool = bool1;
        if (a(this.d, ((b)paramObject).d))
          bool = true; 
      } 
    } 
    return bool;
  }
  
  public int hashCode() {
    return a(this.b) ^ a(this.c) ^ a(this.d);
  }
  
  public String toString() {
    Integer integer;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("[ ");
    stringBuilder.append(this.b);
    stringBuilder.append(" , ");
    stringBuilder.append(this.c);
    stringBuilder.append(" : ");
    if (this.d == null) {
      String str = "null";
    } else {
      integer = Integer.valueOf(this.d.a());
    } 
    stringBuilder.append(integer);
    stringBuilder.append(" ]");
    return stringBuilder.toString();
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\e\a\a\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */