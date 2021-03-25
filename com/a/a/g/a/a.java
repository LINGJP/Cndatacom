package com.a.a.g.a;

import com.a.a.b.b;
import com.a.a.f;

final class a {
  private final b a;
  
  private i b;
  
  private g c;
  
  a(b paramb) {
    int j = paramb.e();
    if (j < 21 || (j & 0x3) != 1)
      throw f.a(); 
    this.a = paramb;
  }
  
  private int a(int paramInt1, int paramInt2, int paramInt3) {
    return this.a.a(paramInt1, paramInt2) ? (paramInt3 << 1 | 0x1) : (paramInt3 << 1);
  }
  
  g a() {
    if (this.c != null)
      return this.c; 
    boolean bool = false;
    int j = 0;
    int k = 0;
    while (j < 6) {
      k = a(j, 8, k);
      j++;
    } 
    k = a(8, 7, a(8, 8, a(7, 8, k)));
    for (j = 5; j >= 0; j--)
      k = a(8, j, k); 
    int n = this.a.e();
    int m = n - 1;
    j = bool;
    while (m >= n - 7) {
      j = a(8, m, j);
      m--;
    } 
    for (m = n - 8; m < n; m++)
      j = a(m, 8, j); 
    this.c = g.b(k, j);
    if (this.c != null)
      return this.c; 
    throw f.a();
  }
  
  i b() {
    if (this.b != null)
      return this.b; 
    int m = this.a.e();
    int j = m - 17 >> 2;
    if (j <= 6)
      return i.b(j); 
    int n = m - 11;
    byte b1 = 5;
    boolean bool = false;
    j = 5;
    int k = 0;
    while (j >= 0) {
      for (int i2 = m - 9; i2 >= n; i2--)
        k = a(i2, j, k); 
      j--;
    } 
    i i1 = i.c(k);
    j = b1;
    k = bool;
    if (i1 != null) {
      j = b1;
      k = bool;
      if (i1.d() == m) {
        this.b = i1;
        return i1;
      } 
    } 
    while (j >= 0) {
      for (int i2 = m - 9; i2 >= n; i2--)
        k = a(j, i2, k); 
      j--;
    } 
    i1 = i.c(k);
    if (i1 != null && i1.d() == m) {
      this.b = i1;
      return i1;
    } 
    throw f.a();
  }
  
  byte[] c() {
    g g1 = a();
    i i4 = b();
    c c = c.a(g1.b());
    int i3 = this.a.e();
    c.a(this.a, i3);
    b b1 = i4.e();
    byte[] arrayOfByte = new byte[i4.c()];
    int i2 = i3 - 1;
    int n = i2;
    int j = 0;
    int i1 = 1;
    int k = 0;
    int m = 0;
    while (n > 0) {
      int i6 = n;
      if (n == 6)
        i6 = n - 1; 
      int i5 = 0;
      n = m;
      m = i5;
      while (m < i3) {
        int i7;
        if (i1) {
          i7 = i2 - m;
        } else {
          i7 = m;
        } 
        int i8 = 0;
        int i9 = n;
        while (i8 < 2) {
          int i11 = i6 - i8;
          int i10 = j;
          n = i9;
          i5 = k;
          if (!b1.a(i11, i7)) {
            n = i9 + 1;
            k <<= 1;
            if (this.a.a(i11, i7))
              k |= 0x1; 
            if (n == 8) {
              arrayOfByte[j] = (byte)k;
              i10 = j + 1;
              n = 0;
              i5 = 0;
            } else {
              i5 = k;
              i10 = j;
            } 
          } 
          i8++;
          j = i10;
          i9 = n;
          k = i5;
        } 
        m++;
        n = i9;
      } 
      i1 ^= 0x1;
      i5 = i6 - 2;
      m = n;
      n = i5;
    } 
    if (j != i4.c())
      throw f.a(); 
    return arrayOfByte;
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\g\a\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */