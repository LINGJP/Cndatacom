package com.a.a.g.a;

final class g {
  private static final int[][] a;
  
  private static final int[] b = new int[] { 
      0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 
      2, 3, 2, 3, 3, 4 };
  
  private final f c;
  
  private final byte d;
  
  private g(int paramInt) {
    this.c = f.a(paramInt >> 3 & 0x3);
    this.d = (byte)(paramInt & 0x7);
  }
  
  static int a(int paramInt1, int paramInt2) {
    paramInt1 ^= paramInt2;
    return b[paramInt1 & 0xF] + b[paramInt1 >>> 4 & 0xF] + b[paramInt1 >>> 8 & 0xF] + b[paramInt1 >>> 12 & 0xF] + b[paramInt1 >>> 16 & 0xF] + b[paramInt1 >>> 20 & 0xF] + b[paramInt1 >>> 24 & 0xF] + b[paramInt1 >>> 28 & 0xF];
  }
  
  static g b(int paramInt1, int paramInt2) {
    g g1 = c(paramInt1, paramInt2);
    return (g1 != null) ? g1 : c(paramInt1 ^ 0x5412, paramInt2 ^ 0x5412);
  }
  
  private static g c(int paramInt1, int paramInt2) {
    int[][] arrayOfInt = a;
    int m = arrayOfInt.length;
    int k = 0;
    int j = Integer.MAX_VALUE;
    int i;
    for (i = 0; k < m; i = i1) {
      int[] arrayOfInt1 = arrayOfInt[k];
      int i2 = arrayOfInt1[0];
      if (i2 == paramInt1 || i2 == paramInt2)
        return new g(arrayOfInt1[1]); 
      int i1 = a(paramInt1, i2);
      int n = j;
      if (i1 < j) {
        i = arrayOfInt1[1];
        n = i1;
      } 
      j = n;
      i1 = i;
      if (paramInt1 != paramInt2) {
        i2 = a(paramInt2, i2);
        j = n;
        i1 = i;
        if (i2 < n) {
          i1 = arrayOfInt1[1];
          j = i2;
        } 
      } 
      k++;
    } 
    return (j <= 3) ? new g(i) : null;
  }
  
  f a() {
    return this.c;
  }
  
  byte b() {
    return this.d;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = paramObject instanceof g;
    boolean bool1 = false;
    if (!bool)
      return false; 
    paramObject = paramObject;
    bool = bool1;
    if (this.c == ((g)paramObject).c) {
      bool = bool1;
      if (this.d == ((g)paramObject).d)
        bool = true; 
    } 
    return bool;
  }
  
  public int hashCode() {
    return this.c.ordinal() << 3 | this.d;
  }
  
  static {
    int[] arrayOfInt1 = { 21522, 0 };
    int[] arrayOfInt2 = { 17913, 4 };
    int[] arrayOfInt3 = { 32170, 10 };
    int[] arrayOfInt4 = { 30877, 11 };
    int[] arrayOfInt5 = { 25368, 13 };
    int[] arrayOfInt6 = { 26998, 15 };
    int[] arrayOfInt7 = { 6608, 19 };
    int[] arrayOfInt8 = { 1890, 20 };
    int[] arrayOfInt9 = { 3340, 22 };
    int[] arrayOfInt10 = { 16177, 26 };
    a = new int[][] { 
        arrayOfInt1, { 20773, 1 }, { 24188, 2 }, { 23371, 3 }, arrayOfInt2, { 16590, 5 }, { 20375, 6 }, { 19104, 7 }, { 30660, 8 }, { 29427, 9 }, 
        arrayOfInt3, arrayOfInt4, { 26159, 12 }, arrayOfInt5, { 27713, 14 }, arrayOfInt6, { 5769, 16 }, { 5054, 17 }, { 7399, 18 }, arrayOfInt7, 
        arrayOfInt8, { 597, 21 }, arrayOfInt9, { 2107, 23 }, { 13663, 24 }, { 12392, 25 }, arrayOfInt10, { 14854, 27 }, { 9396, 28 }, { 8579, 29 }, 
        { 11994, 30 }, { 11245, 31 } };
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\g\a\g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */