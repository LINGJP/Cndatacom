package com.a.a.b;

import com.a.a.b;
import com.a.a.g;
import com.a.a.i;

public class h extends b {
  private static final byte[] a = new byte[0];
  
  private byte[] b = a;
  
  private final int[] c = new int[32];
  
  public h(g paramg) {
    super(paramg);
  }
  
  private static int a(int[] paramArrayOfint) {
    int i3 = paramArrayOfint.length;
    int i2 = 0;
    int j = 0;
    int n = 0;
    int m = 0;
    int i = 0;
    while (j < i3) {
      int i4 = n;
      if (paramArrayOfint[j] > n) {
        i4 = paramArrayOfint[j];
        i = j;
      } 
      int i5 = m;
      if (paramArrayOfint[j] > m)
        i5 = paramArrayOfint[j]; 
      j++;
      n = i4;
      m = i5;
    } 
    n = 0;
    j = 0;
    int k = i2;
    while (k < i3) {
      int i4 = k - i;
      i2 = paramArrayOfint[k] * i4 * i4;
      i4 = n;
      if (i2 > n) {
        j = k;
        i4 = i2;
      } 
      k++;
      n = i4;
    } 
    n = j;
    k = i;
    if (i > j) {
      k = j;
      n = i;
    } 
    if (n - k <= i3 >> 4)
      throw i.a(); 
    i = n - 1;
    j = -1;
    int i1 = i;
    while (i > k) {
      i2 = i - k;
      i3 = i2 * i2 * (n - i) * (m - paramArrayOfint[i]);
      i2 = j;
      if (i3 > j) {
        i1 = i;
        i2 = i3;
      } 
      i--;
      j = i2;
    } 
    return i1 << 3;
  }
  
  private void a(int paramInt) {
    if (this.b.length < paramInt)
      this.b = new byte[paramInt]; 
    for (paramInt = 0; paramInt < 32; paramInt++)
      this.c[paramInt] = 0; 
  }
  
  public a a(int paramInt, a parama) {
    g g = a();
    int j = g.b();
    if (parama == null || parama.a() < j) {
      parama = new a(j);
    } else {
      parama.b();
    } 
    a(j);
    byte[] arrayOfByte = g.a(paramInt, this.b);
    int[] arrayOfInt = this.c;
    for (paramInt = 0; paramInt < j; paramInt++) {
      int m = (arrayOfByte[paramInt] & 0xFF) >> 3;
      arrayOfInt[m] = arrayOfInt[m] + 1;
    } 
    int k = a(arrayOfInt);
    byte b1 = arrayOfByte[0];
    paramInt = arrayOfByte[1] & 0xFF;
    int i = b1 & 0xFF;
    b1 = 1;
    while (b1 < j - 1) {
      int i1 = b1 + 1;
      int n = arrayOfByte[i1] & 0xFF;
      if ((paramInt << 2) - i - n >> 1 < k)
        parama.b(b1); 
      i = paramInt;
      int m = i1;
      paramInt = n;
    } 
    return parama;
  }
  
  public b a(g paramg) {
    return new h(paramg);
  }
  
  public b b() {
    g g = a();
    int j = g.b();
    int k = g.c();
    b b1 = new b(j, k);
    a(j);
    int[] arrayOfInt = this.c;
    int i;
    for (i = 1; i < 5; i++) {
      byte[] arrayOfByte1 = g.a(k * i / 5, this.b);
      int i1 = (j << 2) / 5;
      for (int n = j / 5; n < i1; n++) {
        int i2 = (arrayOfByte1[n] & 0xFF) >> 3;
        arrayOfInt[i2] = arrayOfInt[i2] + 1;
      } 
    } 
    int m = a(arrayOfInt);
    byte[] arrayOfByte = g.a();
    for (i = 0; i < k; i++) {
      for (int n = 0; n < j; n++) {
        if ((arrayOfByte[i * j + n] & 0xFF) < m)
          b1.b(n, i); 
      } 
    } 
    return b1;
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\b\h.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */