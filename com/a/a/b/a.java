package com.a.a.b;

public final class a {
  private int[] a = new int[1];
  
  private int b = 0;
  
  public a() {}
  
  public a(int paramInt) {}
  
  private static int[] e(int paramInt) {
    return new int[paramInt + 31 >> 5];
  }
  
  public int a() {
    return this.b;
  }
  
  public boolean a(int paramInt) {
    return ((1 << (paramInt & 0x1F) & this.a[paramInt >> 5]) != 0);
  }
  
  public boolean a(int paramInt1, int paramInt2, boolean paramBoolean) {
    if (paramInt2 < paramInt1)
      throw new IllegalArgumentException(); 
    if (paramInt2 == paramInt1)
      return true; 
    int k = paramInt2 - 1;
    int j = paramInt1 >> 5;
    int m = k >> 5;
    int i;
    for (i = j; i <= m; i++) {
      boolean bool;
      if (i > j) {
        paramInt2 = 0;
      } else {
        paramInt2 = paramInt1 & 0x1F;
      } 
      if (i < m) {
        n = 31;
      } else {
        n = k & 0x1F;
      } 
      if (paramInt2 == 0 && n == 31) {
        paramInt2 = -1;
      } else {
        bool = false;
        int i1 = paramInt2;
        while (true) {
          paramInt2 = bool;
          if (i1 <= n) {
            bool |= 1 << i1;
            i1++;
            continue;
          } 
          break;
        } 
      } 
      int n = this.a[i];
      if (paramBoolean) {
        bool = paramInt2;
      } else {
        bool = false;
      } 
      if ((n & paramInt2) != bool)
        return false; 
    } 
    return true;
  }
  
  public void b() {
    int j = this.a.length;
    for (int i = 0; i < j; i++)
      this.a[i] = 0; 
  }
  
  public void b(int paramInt) {
    int[] arrayOfInt = this.a;
    int i = paramInt >> 5;
    arrayOfInt[i] = 1 << (paramInt & 0x1F) | arrayOfInt[i];
  }
  
  public int c(int paramInt) {
    if (paramInt >= this.b)
      return this.b; 
    int j = paramInt >> 5;
    int i = ((1 << (paramInt & 0x1F)) - 1 ^ 0xFFFFFFFF) & this.a[j];
    paramInt = j;
    while (i == 0) {
      if (++paramInt == this.a.length)
        return this.b; 
      i = this.a[paramInt];
    } 
    i = (paramInt << 5) + Integer.numberOfTrailingZeros(i);
    paramInt = i;
    if (i > this.b)
      paramInt = this.b; 
    return paramInt;
  }
  
  public void c() {
    int[] arrayOfInt = new int[this.a.length];
    int j = this.b;
    for (int i = 0; i < j; i++) {
      if (a(j - i - 1)) {
        int k = i >> 5;
        arrayOfInt[k] = 1 << (i & 0x1F) | arrayOfInt[k];
      } 
    } 
    this.a = arrayOfInt;
  }
  
  public int d(int paramInt) {
    if (paramInt >= this.b)
      return this.b; 
    int j = paramInt >> 5;
    int i = ((1 << (paramInt & 0x1F)) - 1 ^ 0xFFFFFFFF) & (this.a[j] ^ 0xFFFFFFFF);
    paramInt = j;
    while (i == 0) {
      if (++paramInt == this.a.length)
        return this.b; 
      i = this.a[paramInt] ^ 0xFFFFFFFF;
    } 
    i = (paramInt << 5) + Integer.numberOfTrailingZeros(i);
    paramInt = i;
    if (i > this.b)
      paramInt = this.b; 
    return paramInt;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder(this.b);
    for (int i = 0; i < this.b; i++) {
      byte b;
      if ((i & 0x7) == 0)
        stringBuilder.append(' '); 
      if (a(i)) {
        b = 88;
      } else {
        b = 46;
      } 
      stringBuilder.append(b);
    } 
    return stringBuilder.toString();
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\b\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */