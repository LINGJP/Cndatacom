package com.a.a.g.a;

public enum f {
  a(1),
  b(0),
  c(3),
  d(2);
  
  private static final f[] e;
  
  private final int f;
  
  static {
    e = new f[] { b, a, d, c };
  }
  
  f(int paramInt1) {
    this.f = paramInt1;
  }
  
  public static f a(int paramInt) {
    if (paramInt < 0 || paramInt >= e.length)
      throw new IllegalArgumentException(); 
    return e[paramInt];
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\g\a\f.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */