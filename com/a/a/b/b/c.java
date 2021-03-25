package com.a.a.b.b;

public final class c {
  private final a a;
  
  public c(a parama) {
    this.a = parama;
  }
  
  private int[] a(b paramb) {
    int k = paramb.a();
    int j = 0;
    int i = 1;
    if (k == 1)
      return new int[] { paramb.a(1) }; 
    int[] arrayOfInt = new int[k];
    while (i < this.a.c() && j < k) {
      int m = j;
      if (paramb.b(i) == 0) {
        arrayOfInt[j] = this.a.c(i);
        m = j + 1;
      } 
      i++;
      j = m;
    } 
    if (j != k)
      throw new d("Error locator degree does not match number of roots"); 
    return arrayOfInt;
  }
  
  private int[] a(b paramb, int[] paramArrayOfint) {
    int j = paramArrayOfint.length;
    int[] arrayOfInt = new int[j];
    for (int i = 0; i < j; i++) {
      int n = this.a.c(paramArrayOfint[i]);
      int k = 0;
      int m;
      for (m = 1; k < j; m = i1) {
        int i1 = m;
        if (i != k) {
          i1 = this.a.c(paramArrayOfint[k], n);
          if ((i1 & 0x1) == 0) {
            i1 |= 0x1;
          } else {
            i1 &= 0xFFFFFFFE;
          } 
          i1 = this.a.c(m, i1);
        } 
        k++;
      } 
      arrayOfInt[i] = this.a.c(paramb.b(n), this.a.c(m));
      if (this.a.d() != 0)
        arrayOfInt[i] = this.a.c(arrayOfInt[i], n); 
    } 
    return arrayOfInt;
  }
  
  private b[] a(b paramb1, b paramb2, int paramInt) {
    b b2 = paramb1;
    b b1 = paramb2;
    if (paramb1.a() < paramb2.a()) {
      b1 = paramb1;
      b2 = paramb2;
    } 
    b b3 = this.a.a();
    b b4 = this.a.b();
    paramb2 = b2;
    paramb1 = b1;
    b1 = b4;
    b2 = b3;
    while (paramb1.a() >= paramInt / 2) {
      if (paramb1.b())
        throw new d("r_{i-1} was zero"); 
      b3 = this.a.a();
      int i = paramb1.a(paramb1.a());
      i = this.a.c(i);
      while (paramb2.a() >= paramb1.a() && !paramb2.b()) {
        int j = paramb2.a() - paramb1.a();
        int k = this.a.c(paramb2.a(paramb2.a()), i);
        b3 = b3.a(this.a.a(j, k));
        paramb2 = paramb2.a(paramb1.a(j, k));
      } 
      b2 = b3.b(b1).a(b2);
      b3 = paramb1;
      paramb1 = paramb2;
      paramb2 = b2;
      b2 = b1;
      b1 = paramb2;
      paramb2 = b3;
    } 
    paramInt = b1.a(0);
    if (paramInt == 0)
      throw new d("sigmaTilde(0) was zero"); 
    paramInt = this.a.c(paramInt);
    return new b[] { b1.c(paramInt), paramb1.c(paramInt) };
  }
  
  public void a(int[] paramArrayOfint, int paramInt) {
    b b1 = new b(this.a, paramArrayOfint);
    int[] arrayOfInt3 = new int[paramInt];
    boolean bool2 = false;
    int i = 0;
    boolean bool1 = true;
    while (i < paramInt) {
      int j = b1.b(this.a.a(this.a.d() + i));
      arrayOfInt3[arrayOfInt3.length - 1 - i] = j;
      if (j != 0)
        bool1 = false; 
      i++;
    } 
    if (bool1)
      return; 
    b1 = new b(this.a, arrayOfInt3);
    b[] arrayOfB = a(this.a.a(paramInt, 1), b1, paramInt);
    b1 = arrayOfB[0];
    b b2 = arrayOfB[1];
    int[] arrayOfInt1 = a(b1);
    int[] arrayOfInt2 = a(b2, arrayOfInt1);
    for (paramInt = bool2; paramInt < arrayOfInt1.length; paramInt++) {
      i = paramArrayOfint.length - 1 - this.a.b(arrayOfInt1[paramInt]);
      if (i < 0)
        throw new d("Bad error location"); 
      paramArrayOfint[i] = a.b(paramArrayOfint[i], arrayOfInt2[paramInt]);
    } 
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\b\b\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */