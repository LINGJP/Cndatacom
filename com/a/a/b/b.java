package com.a.a.b;

public final class b {
  private final int a;
  
  private final int b;
  
  private final int c;
  
  private final int[] d;
  
  public b(int paramInt) {
    this(paramInt, paramInt);
  }
  
  public b(int paramInt1, int paramInt2) {
    if (paramInt1 < 1 || paramInt2 < 1)
      throw new IllegalArgumentException("Both dimensions must be greater than 0"); 
    this.a = paramInt1;
    this.b = paramInt2;
    this.c = paramInt1 + 31 >> 5;
    this.d = new int[this.c * paramInt2];
  }
  
  public void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if (paramInt2 < 0 || paramInt1 < 0)
      throw new IllegalArgumentException("Left and top must be nonnegative"); 
    if (paramInt4 < 1 || paramInt3 < 1)
      throw new IllegalArgumentException("Height and width must be at least 1"); 
    int i = paramInt3 + paramInt1;
    paramInt4 += paramInt2;
    if (paramInt4 > this.b || i > this.a)
      throw new IllegalArgumentException("The region must fit inside the matrix"); 
    while (paramInt2 < paramInt4) {
      int j = this.c;
      for (paramInt3 = paramInt1; paramInt3 < i; paramInt3++) {
        int[] arrayOfInt = this.d;
        int k = (paramInt3 >> 5) + j * paramInt2;
        arrayOfInt[k] = arrayOfInt[k] | 1 << (paramInt3 & 0x1F);
      } 
      paramInt2++;
    } 
  }
  
  public boolean a(int paramInt1, int paramInt2) {
    int i = this.c;
    return ((this.d[paramInt2 * i + (paramInt1 >> 5)] >>> (paramInt1 & 0x1F) & 0x1) != 0);
  }
  
  public int[] a() {
    int j = this.a;
    int n = this.b;
    int k = -1;
    int m = -1;
    int i = 0;
    while (i < this.b) {
      int i1 = m;
      m = k;
      int i2 = 0;
      for (k = i1; i2 < this.c; k = i4) {
        int i7 = this.d[this.c * i + i2];
        int i5 = j;
        int i6 = m;
        int i3 = n;
        int i4 = k;
        if (i7 != 0) {
          i1 = n;
          if (i < n)
            i1 = i; 
          n = k;
          if (i > k)
            n = i; 
          int i8 = i2 * 32;
          byte b1 = 31;
          k = j;
          if (i8 < j) {
            for (k = 0; i7 << 31 - k == 0; k++);
            i3 = k + i8;
            k = j;
            if (i3 < j)
              k = i3; 
          } 
          i5 = k;
          i6 = m;
          i3 = i1;
          i4 = n;
          if (i8 + 31 > m) {
            for (j = b1; i7 >>> j == 0; j--);
            j = i8 + j;
            i5 = k;
            i6 = m;
            i3 = i1;
            i4 = n;
            if (j > m) {
              i6 = j;
              i4 = n;
              i3 = i1;
              i5 = k;
            } 
          } 
        } 
        i2++;
        j = i5;
        m = i6;
        n = i3;
      } 
      i++;
      i1 = k;
      k = m;
      m = i1;
    } 
    i = k - j;
    k = m - n;
    return (i < 0 || k < 0) ? null : new int[] { j, n, i, k };
  }
  
  public void b(int paramInt1, int paramInt2) {
    paramInt2 = paramInt2 * this.c + (paramInt1 >> 5);
    int[] arrayOfInt = this.d;
    arrayOfInt[paramInt2] = 1 << (paramInt1 & 0x1F) | arrayOfInt[paramInt2];
  }
  
  public int[] b() {
    int i;
    for (i = 0; i < this.d.length && this.d[i] == 0; i++);
    if (i == this.d.length)
      return null; 
    int k = i / this.c;
    int m = this.c;
    int n = this.d[i];
    int j;
    for (j = 0; n << 31 - j == 0; j++);
    return new int[] { (i % m << 5) + j, k };
  }
  
  public void c(int paramInt1, int paramInt2) {
    paramInt2 = paramInt2 * this.c + (paramInt1 >> 5);
    int[] arrayOfInt = this.d;
    arrayOfInt[paramInt2] = 1 << (paramInt1 & 0x1F) ^ arrayOfInt[paramInt2];
  }
  
  public int[] c() {
    int i;
    for (i = this.d.length - 1; i >= 0 && this.d[i] == 0; i--);
    if (i < 0)
      return null; 
    int k = i / this.c;
    int m = this.c;
    int n = this.d[i];
    int j;
    for (j = 31; n >>> j == 0; j--);
    return new int[] { (i % m << 5) + j, k };
  }
  
  public int d() {
    return this.a;
  }
  
  public int e() {
    return this.b;
  }
  
  public boolean equals(Object paramObject) {
    if (!(paramObject instanceof b))
      return false; 
    paramObject = paramObject;
    if (this.a == ((b)paramObject).a && this.b == ((b)paramObject).b && this.c == ((b)paramObject).c) {
      if (this.d.length != ((b)paramObject).d.length)
        return false; 
      for (int i = 0; i < this.d.length; i++) {
        if (this.d[i] != ((b)paramObject).d[i])
          return false; 
      } 
      return true;
    } 
    return false;
  }
  
  public int hashCode() {
    int j = ((this.a * 31 + this.a) * 31 + this.b) * 31 + this.c;
    int[] arrayOfInt = this.d;
    int k = arrayOfInt.length;
    for (int i = 0; i < k; i++)
      j = j * 31 + arrayOfInt[i]; 
    return j;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder(this.b * (this.a + 1));
    for (int i = 0; i < this.b; i++) {
      for (int j = 0; j < this.a; j++) {
        String str;
        if (a(j, i)) {
          str = "X ";
        } else {
          str = "  ";
        } 
        stringBuilder.append(str);
      } 
      stringBuilder.append('\n');
    } 
    return stringBuilder.toString();
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\b\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */