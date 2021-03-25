package com.a.a.g.b;

import com.a.a.o;

public final class d extends o {
  private final float a;
  
  private int b;
  
  d(float paramFloat1, float paramFloat2, float paramFloat3) {
    this(paramFloat1, paramFloat2, paramFloat3, 1);
  }
  
  private d(float paramFloat1, float paramFloat2, float paramFloat3, int paramInt) {
    super(paramFloat1, paramFloat2);
    this.a = paramFloat3;
    this.b = paramInt;
  }
  
  boolean a(float paramFloat1, float paramFloat2, float paramFloat3) {
    paramFloat2 = Math.abs(paramFloat2 - b());
    boolean bool = false;
    if (paramFloat2 <= paramFloat1 && Math.abs(paramFloat3 - a()) <= paramFloat1) {
      paramFloat1 = Math.abs(paramFloat1 - this.a);
      if (paramFloat1 <= 1.0F || paramFloat1 <= this.a)
        bool = true; 
      return bool;
    } 
    return false;
  }
  
  d b(float paramFloat1, float paramFloat2, float paramFloat3) {
    int i = this.b + 1;
    float f1 = this.b;
    float f2 = a();
    float f3 = i;
    return new d((f1 * f2 + paramFloat2) / f3, (this.b * b() + paramFloat1) / f3, (this.b * this.a + paramFloat3) / f3, i);
  }
  
  public float c() {
    return this.a;
  }
  
  int d() {
    return this.b;
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\g\b\d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */