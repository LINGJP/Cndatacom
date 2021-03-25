package com.a.a.b.b;

public final class a {
  public static final a a = new a(4201, 4096, 1);
  
  public static final a b = new a(1033, 1024, 1);
  
  public static final a c = new a(67, 64, 1);
  
  public static final a d = new a(19, 16, 1);
  
  public static final a e = new a(285, 256, 0);
  
  public static final a f = new a(301, 256, 1);
  
  public static final a g = f;
  
  public static final a h = c;
  
  private int[] i;
  
  private int[] j;
  
  private b k;
  
  private b l;
  
  private final int m;
  
  private final int n;
  
  private final int o;
  
  private boolean p = false;
  
  public a(int paramInt1, int paramInt2, int paramInt3) {
    this.n = paramInt1;
    this.m = paramInt2;
    this.o = paramInt3;
    if (paramInt2 <= 0)
      e(); 
  }
  
  static int b(int paramInt1, int paramInt2) {
    return paramInt1 ^ paramInt2;
  }
  
  private void e() {
    this.i = new int[this.m];
    this.j = new int[this.m];
    int j = 0;
    int i = 1;
    while (j < this.m) {
      this.i[j] = i;
      int k = i << 1;
      i = k;
      if (k >= this.m)
        i = (k ^ this.n) & this.m - 1; 
      j++;
    } 
    for (i = 0; i < this.m - 1; i++)
      this.j[this.i[i]] = i; 
    this.k = new b(this, new int[] { 0 });
    this.l = new b(this, new int[] { 1 });
    this.p = true;
  }
  
  private void f() {
    if (!this.p)
      e(); 
  }
  
  int a(int paramInt) {
    f();
    return this.i[paramInt];
  }
  
  b a() {
    f();
    return this.k;
  }
  
  b a(int paramInt1, int paramInt2) {
    f();
    if (paramInt1 < 0)
      throw new IllegalArgumentException(); 
    if (paramInt2 == 0)
      return this.k; 
    int[] arrayOfInt = new int[paramInt1 + 1];
    arrayOfInt[0] = paramInt2;
    return new b(this, arrayOfInt);
  }
  
  int b(int paramInt) {
    f();
    if (paramInt == 0)
      throw new IllegalArgumentException(); 
    return this.j[paramInt];
  }
  
  b b() {
    f();
    return this.l;
  }
  
  public int c() {
    return this.m;
  }
  
  int c(int paramInt) {
    f();
    if (paramInt == 0)
      throw new ArithmeticException(); 
    return this.i[this.m - this.j[paramInt] - 1];
  }
  
  int c(int paramInt1, int paramInt2) {
    f();
    return (paramInt1 == 0 || paramInt2 == 0) ? 0 : this.i[(this.j[paramInt1] + this.j[paramInt2]) % (this.m - 1)];
  }
  
  public int d() {
    return this.o;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("GF(0x");
    stringBuilder.append(Integer.toHexString(this.n));
    stringBuilder.append(',');
    stringBuilder.append(this.m);
    stringBuilder.append(')');
    return stringBuilder.toString();
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\b\b\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */