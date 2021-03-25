package com.a.a.c.a;

final class b {
  private final int a;
  
  private final byte[] b;
  
  private b(int paramInt, byte[] paramArrayOfbyte) {
    this.a = paramInt;
    this.b = paramArrayOfbyte;
  }
  
  static b[] a(byte[] paramArrayOfbyte, e parame) {
    e.b b1 = parame.g();
    e.a[] arrayOfA = b1.b();
    int k = arrayOfA.length;
    int i = 0;
    int j = 0;
    while (i < k) {
      j += arrayOfA[i].a();
      i++;
    } 
    b[] arrayOfB = new b[j];
    int m = arrayOfA.length;
    j = 0;
    i = 0;
    while (j < m) {
      e.a a = arrayOfA[j];
      k = 0;
      while (k < a.a()) {
        int i3 = a.b();
        arrayOfB[i] = new b(i3, new byte[b1.a() + i3]);
        k++;
        i++;
      } 
      j++;
    } 
    int i1 = (arrayOfB[0]).b.length - b1.a();
    int i2 = i1 - 1;
    k = 0;
    j = 0;
    while (k < i2) {
      m = 0;
      while (m < i) {
        (arrayOfB[m]).b[k] = paramArrayOfbyte[j];
        m++;
        j++;
      } 
      k++;
    } 
    if (parame.a() == 24) {
      k = 1;
    } else {
      k = 0;
    } 
    if (k != 0) {
      m = 8;
    } else {
      m = i;
    } 
    int n = 0;
    while (n < m) {
      (arrayOfB[n]).b[i2] = paramArrayOfbyte[j];
      n++;
      j++;
    } 
    i2 = (arrayOfB[0]).b.length;
    m = j;
    for (j = i1; j < i2; j++) {
      n = 0;
      while (n < i) {
        if (k != 0 && n > 7) {
          i1 = j - 1;
        } else {
          i1 = j;
        } 
        (arrayOfB[n]).b[i1] = paramArrayOfbyte[m];
        n++;
        m++;
      } 
    } 
    if (m != paramArrayOfbyte.length)
      throw new IllegalArgumentException(); 
    return arrayOfB;
  }
  
  int a() {
    return this.a;
  }
  
  byte[] b() {
    return this.b;
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\c\a\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */