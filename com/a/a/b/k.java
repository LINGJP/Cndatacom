package com.a.a.b;

public final class k {
  private final float a;
  
  private final float b;
  
  private final float c;
  
  private final float d;
  
  private final float e;
  
  private final float f;
  
  private final float g;
  
  private final float h;
  
  private final float i;
  
  private k(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, float paramFloat9) {
    this.a = paramFloat1;
    this.b = paramFloat4;
    this.c = paramFloat7;
    this.d = paramFloat2;
    this.e = paramFloat5;
    this.f = paramFloat8;
    this.g = paramFloat3;
    this.h = paramFloat6;
    this.i = paramFloat9;
  }
  
  public static k a(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8) {
    float f1 = paramFloat1 - paramFloat3 + paramFloat5 - paramFloat7;
    float f2 = paramFloat2 - paramFloat4 + paramFloat6 - paramFloat8;
    if (f1 == 0.0F && f2 == 0.0F)
      return new k(paramFloat3 - paramFloat1, paramFloat5 - paramFloat3, paramFloat1, paramFloat4 - paramFloat2, paramFloat6 - paramFloat4, paramFloat2, 0.0F, 0.0F, 1.0F); 
    float f3 = paramFloat3 - paramFloat5;
    float f4 = paramFloat7 - paramFloat5;
    paramFloat5 = paramFloat4 - paramFloat6;
    float f5 = paramFloat8 - paramFloat6;
    paramFloat6 = f3 * f5 - f4 * paramFloat5;
    f4 = (f5 * f1 - f4 * f2) / paramFloat6;
    paramFloat5 = (f3 * f2 - f1 * paramFloat5) / paramFloat6;
    return new k(paramFloat3 - paramFloat1 + f4 * paramFloat3, paramFloat5 * paramFloat7 + paramFloat7 - paramFloat1, paramFloat1, paramFloat4 - paramFloat2 + f4 * paramFloat4, paramFloat8 - paramFloat2 + paramFloat5 * paramFloat8, paramFloat2, f4, paramFloat5, 1.0F);
  }
  
  public static k a(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, float paramFloat9, float paramFloat10, float paramFloat11, float paramFloat12, float paramFloat13, float paramFloat14, float paramFloat15, float paramFloat16) {
    k k1 = b(paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramFloat5, paramFloat6, paramFloat7, paramFloat8);
    return a(paramFloat9, paramFloat10, paramFloat11, paramFloat12, paramFloat13, paramFloat14, paramFloat15, paramFloat16).a(k1);
  }
  
  public static k b(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8) {
    return a(paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramFloat5, paramFloat6, paramFloat7, paramFloat8).a();
  }
  
  k a() {
    return new k(this.e * this.i - this.f * this.h, this.f * this.g - this.d * this.i, this.d * this.h - this.e * this.g, this.c * this.h - this.b * this.i, this.a * this.i - this.c * this.g, this.b * this.g - this.a * this.h, this.b * this.f - this.c * this.e, this.c * this.d - this.a * this.f, this.a * this.e - this.b * this.d);
  }
  
  k a(k paramk) {
    float f1 = this.a;
    float f2 = paramk.a;
    float f3 = this.d;
    float f4 = paramk.b;
    float f5 = this.g;
    float f6 = paramk.c;
    float f7 = this.a;
    float f8 = paramk.d;
    float f9 = this.d;
    float f10 = paramk.e;
    float f11 = this.g;
    float f12 = paramk.f;
    float f13 = this.a;
    float f14 = paramk.g;
    float f15 = this.d;
    float f16 = paramk.h;
    float f17 = this.g;
    float f18 = paramk.i;
    float f19 = this.b;
    float f20 = paramk.a;
    float f21 = this.e;
    float f22 = paramk.b;
    float f23 = this.h;
    float f24 = paramk.c;
    float f25 = this.b;
    float f26 = paramk.d;
    float f27 = this.e;
    float f28 = paramk.e;
    float f29 = this.h;
    float f30 = paramk.f;
    float f31 = this.b;
    float f32 = paramk.g;
    float f33 = this.e;
    float f34 = paramk.h;
    float f35 = this.h;
    float f36 = paramk.i;
    float f37 = this.c;
    float f38 = paramk.a;
    float f39 = this.f;
    float f40 = paramk.b;
    float f41 = this.i;
    float f42 = paramk.c;
    float f43 = this.c;
    float f44 = paramk.d;
    float f45 = this.f;
    float f46 = paramk.e;
    float f47 = this.i;
    float f48 = paramk.f;
    float f49 = this.c;
    float f50 = paramk.g;
    float f51 = this.f;
    float f52 = paramk.h;
    return new k(f5 * f6 + f1 * f2 + f3 * f4, f11 * f12 + f7 * f8 + f9 * f10, f17 * f18 + f13 * f14 + f15 * f16, f23 * f24 + f19 * f20 + f21 * f22, f29 * f30 + f25 * f26 + f27 * f28, f35 * f36 + f31 * f32 + f33 * f34, f41 * f42 + f37 * f38 + f39 * f40, f47 * f48 + f43 * f44 + f45 * f46, this.i * paramk.i + f49 * f50 + f51 * f52);
  }
  
  public void a(float[] paramArrayOffloat) {
    int j = paramArrayOffloat.length;
    float f1 = this.a;
    float f2 = this.b;
    float f3 = this.c;
    float f4 = this.d;
    float f5 = this.e;
    float f6 = this.f;
    float f7 = this.g;
    float f8 = this.h;
    float f9 = this.i;
    int i;
    for (i = 0; i < j; i += 2) {
      float f10 = paramArrayOffloat[i];
      int m = i + 1;
      float f11 = paramArrayOffloat[m];
      float f12 = f3 * f10 + f6 * f11 + f9;
      paramArrayOffloat[i] = (f1 * f10 + f4 * f11 + f7) / f12;
      paramArrayOffloat[m] = (f10 * f2 + f11 * f5 + f8) / f12;
    } 
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\b\k.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */