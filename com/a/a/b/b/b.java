package com.a.a.b.b;

final class b {
  private final a a;
  
  private final int[] b;
  
  b(a parama, int[] paramArrayOfint) {
    if (paramArrayOfint.length == 0)
      throw new IllegalArgumentException(); 
    this.a = parama;
    int j = paramArrayOfint.length;
    int i = 1;
    if (j > 1 && paramArrayOfint[0] == 0) {
      while (i < j && paramArrayOfint[i] == 0)
        i++; 
      if (i == j) {
        this.b = (parama.a()).b;
        return;
      } 
      this.b = new int[j - i];
      System.arraycopy(paramArrayOfint, i, this.b, 0, this.b.length);
      return;
    } 
    this.b = paramArrayOfint;
  }
  
  int a() {
    return this.b.length - 1;
  }
  
  int a(int paramInt) {
    return this.b[this.b.length - 1 - paramInt];
  }
  
  b a(int paramInt1, int paramInt2) {
    if (paramInt1 < 0)
      throw new IllegalArgumentException(); 
    if (paramInt2 == 0)
      return this.a.a(); 
    int i = this.b.length;
    int[] arrayOfInt = new int[paramInt1 + i];
    for (paramInt1 = 0; paramInt1 < i; paramInt1++)
      arrayOfInt[paramInt1] = this.a.c(this.b[paramInt1], paramInt2); 
    return new b(this.a, arrayOfInt);
  }
  
  b a(b paramb) {
    if (!this.a.equals(paramb.a))
      throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field"); 
    if (b())
      return paramb; 
    if (paramb.b())
      return this; 
    int[] arrayOfInt2 = this.b;
    int[] arrayOfInt4 = paramb.b;
    int[] arrayOfInt3 = arrayOfInt2;
    int[] arrayOfInt1 = arrayOfInt4;
    if (arrayOfInt2.length > arrayOfInt4.length) {
      arrayOfInt3 = arrayOfInt4;
      arrayOfInt1 = arrayOfInt2;
    } 
    arrayOfInt2 = new int[arrayOfInt1.length];
    int j = arrayOfInt1.length - arrayOfInt3.length;
    System.arraycopy(arrayOfInt1, 0, arrayOfInt2, 0, j);
    for (int i = j; i < arrayOfInt1.length; i++)
      arrayOfInt2[i] = a.b(arrayOfInt3[i - j], arrayOfInt1[i]); 
    return new b(this.a, arrayOfInt2);
  }
  
  int b(int paramInt) {
    int j = 0;
    if (paramInt == 0)
      return a(0); 
    int k = this.b.length;
    int i = 1;
    if (paramInt == 1) {
      int[] arrayOfInt = this.b;
      k = arrayOfInt.length;
      i = 0;
      for (paramInt = j; paramInt < k; paramInt++)
        i = a.b(i, arrayOfInt[paramInt]); 
      return i;
    } 
    j = this.b[0];
    while (i < k) {
      j = a.b(this.a.c(paramInt, j), this.b[i]);
      i++;
    } 
    return j;
  }
  
  b b(b paramb) {
    if (!this.a.equals(paramb.a))
      throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field"); 
    if (b() || paramb.b())
      return this.a.a(); 
    int[] arrayOfInt2 = this.b;
    int j = arrayOfInt2.length;
    int[] arrayOfInt1 = paramb.b;
    int k = arrayOfInt1.length;
    int[] arrayOfInt3 = new int[j + k - 1];
    for (int i = 0; i < j; i++) {
      int n = arrayOfInt2[i];
      for (int m = 0; m < k; m++) {
        int i1 = i + m;
        arrayOfInt3[i1] = a.b(arrayOfInt3[i1], this.a.c(n, arrayOfInt1[m]));
      } 
    } 
    return new b(this.a, arrayOfInt3);
  }
  
  boolean b() {
    int[] arrayOfInt = this.b;
    boolean bool = false;
    if (arrayOfInt[0] == 0)
      bool = true; 
    return bool;
  }
  
  b c(int paramInt) {
    if (paramInt == 0)
      return this.a.a(); 
    if (paramInt == 1)
      return this; 
    int j = this.b.length;
    int[] arrayOfInt = new int[j];
    for (int i = 0; i < j; i++)
      arrayOfInt[i] = this.a.c(this.b[i], paramInt); 
    return new b(this.a, arrayOfInt);
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder(a() * 8);
    for (int i = a(); i >= 0; i--) {
      int j = a(i);
      if (j != 0) {
        int k;
        if (j < 0) {
          stringBuilder.append(" - ");
          k = -j;
        } else {
          k = j;
          if (stringBuilder.length() > 0) {
            stringBuilder.append(" + ");
            k = j;
          } 
        } 
        if (i == 0 || k != 1) {
          k = this.a.b(k);
          if (k == 0) {
            stringBuilder.append('1');
          } else if (k == 1) {
            stringBuilder.append('a');
          } else {
            stringBuilder.append("a^");
            stringBuilder.append(k);
          } 
        } 
        if (i != 0)
          if (i == 1) {
            stringBuilder.append('x');
          } else {
            stringBuilder.append("x^");
            stringBuilder.append(i);
          }  
      } 
    } 
    return stringBuilder.toString();
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\b\b\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */