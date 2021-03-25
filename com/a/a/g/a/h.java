package com.a.a.g.a;

public enum h {
  a(new int[] { 0, 0, 0 }, 0),
  b(new int[] { 10, 12, 14 }, 1),
  c(new int[] { 9, 11, 13 }, 2),
  d(new int[] { 0, 0, 0 }, 3),
  e(new int[] { 8, 16, 16 }, 4),
  f(new int[] { 0, 0, 0 }, 7),
  g(new int[] { 8, 10, 12 }, 8),
  h(new int[] { 0, 0, 0 }, 5),
  i(new int[] { 0, 0, 0 }, 9),
  j(new int[] { 8, 10, 12 }, 13);
  
  private final int[] k;
  
  private final int l;
  
  h(int[] paramArrayOfint, int paramInt1) {
    this.k = paramArrayOfint;
    this.l = paramInt1;
  }
  
  public static h a(int paramInt) {
    switch (paramInt) {
      default:
        throw new IllegalArgumentException();
      case 13:
        return j;
      case 9:
        return i;
      case 8:
        return g;
      case 7:
        return f;
      case 5:
        return h;
      case 4:
        return e;
      case 3:
        return d;
      case 2:
        return c;
      case 1:
        return b;
      case 0:
        break;
    } 
    return a;
  }
  
  public int a(i parami) {
    int j = parami.a();
    if (j <= 9) {
      j = 0;
    } else if (j <= 26) {
      j = 1;
    } else {
      j = 2;
    } 
    return this.k[j];
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\g\a\h.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */