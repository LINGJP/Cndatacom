package com.a.a.b;

public final class c {
  private final byte[] a;
  
  private int b;
  
  private int c;
  
  public c(byte[] paramArrayOfbyte) {
    this.a = paramArrayOfbyte;
  }
  
  public int a() {
    return this.c;
  }
  
  public int a(int paramInt) {
    int i;
    if (paramInt < 1 || paramInt > 32 || paramInt > c())
      throw new IllegalArgumentException(String.valueOf(paramInt)); 
    if (this.c > 0) {
      int k = 8 - this.c;
      if (paramInt < k) {
        i = paramInt;
      } else {
        i = k;
      } 
      k -= i;
      k = (255 >> 8 - i << k & this.a[this.b]) >> k;
      int m = paramInt - i;
      this.c += i;
      paramInt = k;
      i = m;
      if (this.c == 8) {
        this.c = 0;
        this.b++;
        paramInt = k;
        i = m;
      } 
    } else {
      boolean bool = false;
      i = paramInt;
      paramInt = bool;
    } 
    int j = paramInt;
    if (i > 0) {
      while (i >= 8) {
        paramInt = paramInt << 8 | this.a[this.b] & 0xFF;
        this.b++;
        i -= 8;
      } 
      j = paramInt;
      if (i > 0) {
        j = 8 - i;
        j = paramInt << i | (255 >> j << j & this.a[this.b]) >> j;
        this.c += i;
      } 
    } 
    return j;
  }
  
  public int b() {
    return this.b;
  }
  
  public int c() {
    return (this.a.length - this.b) * 8 - this.c;
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\b\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */