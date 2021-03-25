package com.a.a.c.a;

import com.a.a.f;

public final class e {
  private static final e[] a = h();
  
  private final int b;
  
  private final int c;
  
  private final int d;
  
  private final int e;
  
  private final int f;
  
  private final b g;
  
  private final int h;
  
  private e(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, b paramb) {
    this.b = paramInt1;
    this.c = paramInt2;
    this.d = paramInt3;
    this.e = paramInt4;
    this.f = paramInt5;
    this.g = paramb;
    paramInt3 = paramb.a();
    a[] arrayOfA = paramb.b();
    paramInt4 = arrayOfA.length;
    paramInt1 = 0;
    paramInt2 = 0;
    while (paramInt1 < paramInt4) {
      a a = arrayOfA[paramInt1];
      paramInt2 += a.a() * (a.b() + paramInt3);
      paramInt1++;
    } 
    this.h = paramInt2;
  }
  
  public static e a(int paramInt1, int paramInt2) {
    if ((paramInt1 & 0x1) != 0 || (paramInt2 & 0x1) != 0)
      throw f.a(); 
    for (e e1 : a) {
      if (e1.c == paramInt1 && e1.d == paramInt2)
        return e1; 
    } 
    throw f.a();
  }
  
  private static e[] h() {
    return new e[] { 
        new e(1, 10, 10, 8, 8, new b(5, new a(1, 3))), new e(2, 12, 12, 10, 10, new b(7, new a(1, 5))), new e(3, 14, 14, 12, 12, new b(10, new a(1, 8))), new e(4, 16, 16, 14, 14, new b(12, new a(1, 12))), new e(5, 18, 18, 16, 16, new b(14, new a(1, 18))), new e(6, 20, 20, 18, 18, new b(18, new a(1, 22))), new e(7, 22, 22, 20, 20, new b(20, new a(1, 30))), new e(8, 24, 24, 22, 22, new b(24, new a(1, 36))), new e(9, 26, 26, 24, 24, new b(28, new a(1, 44))), new e(10, 32, 32, 14, 14, new b(36, new a(1, 62))), 
        new e(11, 36, 36, 16, 16, new b(42, new a(1, 86))), new e(12, 40, 40, 18, 18, new b(48, new a(1, 114))), new e(13, 44, 44, 20, 20, new b(56, new a(1, 144))), new e(14, 48, 48, 22, 22, new b(68, new a(1, 174))), new e(15, 52, 52, 24, 24, new b(42, new a(2, 102))), new e(16, 64, 64, 14, 14, new b(56, new a(2, 140))), new e(17, 72, 72, 16, 16, new b(36, new a(4, 92))), new e(18, 80, 80, 18, 18, new b(48, new a(4, 114))), new e(19, 88, 88, 20, 20, new b(56, new a(4, 144))), new e(20, 96, 96, 22, 22, new b(68, new a(4, 174))), 
        new e(21, 104, 104, 24, 24, new b(56, new a(6, 136))), new e(22, 120, 120, 18, 18, new b(68, new a(6, 175))), new e(23, 132, 132, 20, 20, new b(62, new a(8, 163))), new e(24, 144, 144, 22, 22, new b(62, new a(8, 156), new a(2, 155))), new e(25, 8, 18, 6, 16, new b(7, new a(1, 5))), new e(26, 8, 32, 6, 14, new b(11, new a(1, 10))), new e(27, 12, 26, 10, 24, new b(14, new a(1, 16))), new e(28, 12, 36, 10, 16, new b(18, new a(1, 22))), new e(29, 16, 36, 14, 16, new b(24, new a(1, 32))), new e(30, 16, 48, 14, 22, new b(28, new a(1, 49))) };
  }
  
  public int a() {
    return this.b;
  }
  
  public int b() {
    return this.c;
  }
  
  public int c() {
    return this.d;
  }
  
  public int d() {
    return this.e;
  }
  
  public int e() {
    return this.f;
  }
  
  public int f() {
    return this.h;
  }
  
  b g() {
    return this.g;
  }
  
  public String toString() {
    return String.valueOf(this.b);
  }
  
  static final class a {
    private final int a;
    
    private final int b;
    
    private a(int param1Int1, int param1Int2) {
      this.a = param1Int1;
      this.b = param1Int2;
    }
    
    int a() {
      return this.a;
    }
    
    int b() {
      return this.b;
    }
  }
  
  static final class b {
    private final int a;
    
    private final e.a[] b;
    
    private b(int param1Int, e.a param1a) {
      this.a = param1Int;
      this.b = new e.a[] { param1a };
    }
    
    private b(int param1Int, e.a param1a1, e.a param1a2) {
      this.a = param1Int;
      this.b = new e.a[] { param1a1, param1a2 };
    }
    
    int a() {
      return this.a;
    }
    
    e.a[] b() {
      return this.b;
    }
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\c\a\e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */