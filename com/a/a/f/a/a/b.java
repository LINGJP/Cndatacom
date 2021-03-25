package com.a.a.f.a.a;

public final class b {
  public static final b a = new b(929, 3);
  
  private final int[] b;
  
  private final int[] c;
  
  private final c d;
  
  private final c e;
  
  private final int f;
  
  public b(int paramInt1, int paramInt2) {
    this.f = paramInt1;
    this.b = new int[paramInt1];
    this.c = new int[paramInt1];
    int i = 0;
    int j = 1;
    while (i < paramInt1) {
      this.b[i] = j;
      j = j * paramInt2 % paramInt1;
      i++;
    } 
    for (paramInt2 = 0; paramInt2 < paramInt1 - 1; paramInt2++)
      this.c[this.b[paramInt2]] = paramInt2; 
    this.d = new c(this, new int[] { 0 });
    this.e = new c(this, new int[] { 1 });
  }
  
  int a(int paramInt) {
    return this.b[paramInt];
  }
  
  c a() {
    return this.d;
  }
  
  c a(int paramInt1, int paramInt2) {
    if (paramInt1 < 0)
      throw new IllegalArgumentException(); 
    if (paramInt2 == 0)
      return this.d; 
    int[] arrayOfInt = new int[paramInt1 + 1];
    arrayOfInt[0] = paramInt2;
    return new c(this, arrayOfInt);
  }
  
  int b(int paramInt) {
    if (paramInt == 0)
      throw new IllegalArgumentException(); 
    return this.c[paramInt];
  }
  
  int b(int paramInt1, int paramInt2) {
    return (paramInt1 + paramInt2) % this.f;
  }
  
  c b() {
    return this.e;
  }
  
  int c() {
    return this.f;
  }
  
  int c(int paramInt) {
    if (paramInt == 0)
      throw new ArithmeticException(); 
    return this.b[this.f - this.c[paramInt] - 1];
  }
  
  int c(int paramInt1, int paramInt2) {
    return (this.f + paramInt1 - paramInt2) % this.f;
  }
  
  int d(int paramInt1, int paramInt2) {
    return (paramInt1 == 0 || paramInt2 == 0) ? 0 : this.b[(this.c[paramInt1] + this.c[paramInt2]) % (this.f - 1)];
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\f\a\a\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */