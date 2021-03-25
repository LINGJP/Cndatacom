package com.a.a.e.a;

public class b {
  private final int a;
  
  private final int b;
  
  public b(int paramInt1, int paramInt2) {
    this.a = paramInt1;
    this.b = paramInt2;
  }
  
  public final int a() {
    return this.a;
  }
  
  public final int b() {
    return this.b;
  }
  
  public final boolean equals(Object paramObject) {
    boolean bool = paramObject instanceof b;
    boolean bool1 = false;
    if (!bool)
      return false; 
    paramObject = paramObject;
    bool = bool1;
    if (this.a == ((b)paramObject).a) {
      bool = bool1;
      if (this.b == ((b)paramObject).b)
        bool = true; 
    } 
    return bool;
  }
  
  public final int hashCode() {
    return this.a ^ this.b;
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(this.a);
    stringBuilder.append("(");
    stringBuilder.append(this.b);
    stringBuilder.append(')');
    return stringBuilder.toString();
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\e\a\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */