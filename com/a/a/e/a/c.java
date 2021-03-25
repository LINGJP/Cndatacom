package com.a.a.e.a;

import com.a.a.o;

public final class c {
  private final int a;
  
  private final int[] b;
  
  private final o[] c;
  
  public c(int paramInt1, int[] paramArrayOfint, int paramInt2, int paramInt3, int paramInt4) {
    this.a = paramInt1;
    this.b = paramArrayOfint;
    float f1 = paramInt2;
    float f2 = paramInt4;
    this.c = new o[] { new o(f1, f2), new o(paramInt3, f2) };
  }
  
  public int a() {
    return this.a;
  }
  
  public int[] b() {
    return this.b;
  }
  
  public o[] c() {
    return this.c;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool1 = paramObject instanceof c;
    boolean bool = false;
    if (!bool1)
      return false; 
    paramObject = paramObject;
    if (this.a == ((c)paramObject).a)
      bool = true; 
    return bool;
  }
  
  public int hashCode() {
    return this.a;
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\e\a\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */