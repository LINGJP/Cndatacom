package com.a.a.g.b;

import com.a.a.o;

public final class a extends o {
  private final float a;
  
  a(float paramFloat1, float paramFloat2, float paramFloat3) {
    super(paramFloat1, paramFloat2);
    this.a = paramFloat3;
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
  
  a b(float paramFloat1, float paramFloat2, float paramFloat3) {
    return new a((a() + paramFloat2) / 2.0F, (b() + paramFloat1) / 2.0F, (this.a + paramFloat3) / 2.0F);
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\g\b\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */