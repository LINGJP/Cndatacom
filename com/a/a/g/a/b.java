package com.a.a.g.a;

final class b {
  private final int a;
  
  private final byte[] b;
  
  private b(int paramInt, byte[] paramArrayOfbyte) {
    this.a = paramInt;
    this.b = paramArrayOfbyte;
  }
  
  static b[] a(byte[] paramArrayOfbyte, i parami, f paramf) {
    if (paramArrayOfbyte.length != parami.c())
      throw new IllegalArgumentException(); 
    i.b b1 = parami.a(paramf);
    i.a[] arrayOfA = b1.b();
    int m = arrayOfA.length;
    int j = 0;
    int k = 0;
    while (j < m) {
      k += arrayOfA[j].a();
      j++;
    } 
    b[] arrayOfB = new b[k];
    int n = arrayOfA.length;
    m = 0;
    for (k = 0; m < n; k = j) {
      i.a a = arrayOfA[m];
      j = k;
      k = 0;
      while (k < a.a()) {
        int i4 = a.b();
        arrayOfB[j] = new b(i4, new byte[b1.a() + i4]);
        k++;
        j++;
      } 
      m++;
    } 
    m = (arrayOfB[0]).b.length;
    for (j = arrayOfB.length - 1; j >= 0 && (arrayOfB[j]).b.length != m; j--);
    int i2 = j + 1;
    int i1 = m - b1.a();
    m = 0;
    j = 0;
    while (m < i1) {
      n = 0;
      while (n < k) {
        (arrayOfB[n]).b[m] = paramArrayOfbyte[j];
        n++;
        j++;
      } 
      m++;
    } 
    n = i2;
    for (m = j; n < k; m++) {
      (arrayOfB[n]).b[i1] = paramArrayOfbyte[m];
      n++;
    } 
    int i3 = (arrayOfB[0]).b.length;
    for (j = i1; j < i3; j++) {
      n = 0;
      while (n < k) {
        if (n < i2) {
          i1 = j;
        } else {
          i1 = j + 1;
        } 
        (arrayOfB[n]).b[i1] = paramArrayOfbyte[m];
        n++;
        m++;
      } 
    } 
    return arrayOfB;
  }
  
  int a() {
    return this.a;
  }
  
  byte[] b() {
    return this.b;
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\g\a\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */