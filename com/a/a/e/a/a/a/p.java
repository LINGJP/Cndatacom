package com.a.a.e.a.a.a;

final class p extends q {
  private final int a;
  
  private final int b;
  
  p(int paramInt1, int paramInt2, int paramInt3) {
    super(paramInt1);
    this.a = paramInt2;
    this.b = paramInt3;
    if (this.a < 0 || this.a > 10) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Invalid firstDigit: ");
      stringBuilder.append(paramInt2);
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
    if (this.b < 0 || this.b > 10) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Invalid secondDigit: ");
      stringBuilder.append(paramInt3);
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
  }
  
  int a() {
    return this.a;
  }
  
  int b() {
    return this.b;
  }
  
  boolean c() {
    return (this.a == 10);
  }
  
  boolean d() {
    return (this.b == 10);
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\e\a\a\a\p.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */