package com.a.a;

public final class j extends g {
  private final byte[] a;
  
  private final int b;
  
  private final int c;
  
  private final int d;
  
  private final int e;
  
  public j(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, boolean paramBoolean) {
    super(paramInt5, paramInt6);
    if (paramInt3 + paramInt5 > paramInt1 || paramInt4 + paramInt6 > paramInt2)
      throw new IllegalArgumentException("Crop rectangle does not fit within image data."); 
    this.a = paramArrayOfbyte;
    this.b = paramInt1;
    this.c = paramInt2;
    this.d = paramInt3;
    this.e = paramInt4;
    if (paramBoolean)
      a(paramInt5, paramInt6); 
  }
  
  private void a(int paramInt1, int paramInt2) {
    byte[] arrayOfByte = this.a;
    int i = this.e * this.b + this.d;
    int k = 0;
    while (k < paramInt2) {
      int i1 = paramInt1 / 2;
      int m = i + paramInt1 - 1;
      int n = i;
      while (n < i1 + i) {
        byte b = arrayOfByte[n];
        arrayOfByte[n] = arrayOfByte[m];
        arrayOfByte[m] = b;
        n++;
        m--;
      } 
      k++;
      i += this.b;
    } 
  }
  
  public byte[] a() {
    int m = b();
    int n = c();
    if (m == this.b && n == this.c)
      return this.a; 
    int i1 = m * n;
    byte[] arrayOfByte1 = new byte[i1];
    int k = this.e * this.b + this.d;
    int i2 = this.b;
    int i = 0;
    if (m == i2) {
      System.arraycopy(this.a, k, arrayOfByte1, 0, i1);
      return arrayOfByte1;
    } 
    byte[] arrayOfByte2 = this.a;
    while (i < n) {
      System.arraycopy(arrayOfByte2, k, arrayOfByte1, i * m, m);
      k += this.b;
      i++;
    } 
    return arrayOfByte1;
  }
  
  public byte[] a(int paramInt, byte[] paramArrayOfbyte) {
    StringBuilder stringBuilder;
    if (paramInt < 0 || paramInt >= c()) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("Requested row is outside the image: ");
      stringBuilder.append(paramInt);
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
    int i = b();
    if (stringBuilder != null) {
      byte[] arrayOfByte1;
      StringBuilder stringBuilder1 = stringBuilder;
      if (stringBuilder.length < i) {
        arrayOfByte1 = new byte[i];
        int i4 = this.e;
        int i5 = this.b;
        int i6 = this.d;
        System.arraycopy(this.a, (paramInt + i4) * i5 + i6, arrayOfByte1, 0, i);
        return arrayOfByte1;
      } 
      int i1 = this.e;
      int i2 = this.b;
      int i3 = this.d;
      System.arraycopy(this.a, (paramInt + i1) * i2 + i3, arrayOfByte1, 0, i);
      return arrayOfByte1;
    } 
    byte[] arrayOfByte = new byte[i];
    int k = this.e;
    int m = this.b;
    int n = this.d;
    System.arraycopy(this.a, (paramInt + k) * m + n, arrayOfByte, 0, i);
    return arrayOfByte;
  }
  
  public int[] f() {
    int m = b() / 2;
    int n = c() / 2;
    int[] arrayOfInt = new int[m * n];
    byte[] arrayOfByte = this.a;
    int k = this.e * this.b + this.d;
    for (int i = 0; i < n; i++) {
      for (int i1 = 0; i1 < m; i1++)
        arrayOfInt[i * m + i1] = (arrayOfByte[i1 * 2 + k] & 0xFF) * 65793 | 0xFF000000; 
      k += this.b * 2;
    } 
    return arrayOfInt;
  }
  
  public int g() {
    return b() / 2;
  }
  
  public int h() {
    return c() / 2;
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\j.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */