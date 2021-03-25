package com.a.a.f.a.a;

import com.a.a.d;

public final class a {
  private final b a = b.a;
  
  private int[] a(c paramc) {
    int k = paramc.a();
    int[] arrayOfInt = new int[k];
    int i = 1;
    int j;
    for (j = 0; i < this.a.c() && j < k; j = m) {
      int m = j;
      if (paramc.b(i) == 0) {
        arrayOfInt[j] = this.a.c(i);
        m = j + 1;
      } 
      i++;
    } 
    if (j != k)
      throw d.a(); 
    return arrayOfInt;
  }
  
  private int[] a(c paramc1, c paramc2, int[] paramArrayOfint) {
    int j = paramc2.a();
    int[] arrayOfInt = new int[j];
    int i;
    for (i = 1; i <= j; i++)
      arrayOfInt[j - i] = this.a.d(i, paramc2.a(i)); 
    paramc2 = new c(this.a, arrayOfInt);
    j = paramArrayOfint.length;
    arrayOfInt = new int[j];
    for (i = 0; i < j; i++) {
      int m = this.a.c(paramArrayOfint[i]);
      int k = this.a.c(0, paramc1.b(m));
      m = this.a.c(paramc2.b(m));
      arrayOfInt[i] = this.a.d(k, m);
    } 
    return arrayOfInt;
  }
  
  private c[] a(c paramc1, c paramc2, int paramInt) {
    c c2 = paramc1;
    c c1 = paramc2;
    if (paramc1.a() < paramc2.a()) {
      c1 = paramc1;
      c2 = paramc2;
    } 
    c c3 = this.a.a();
    c c4 = this.a.b();
    paramc2 = c2;
    paramc1 = c1;
    c1 = c4;
    c2 = c3;
    while (paramc1.a() >= paramInt / 2) {
      if (paramc1.b())
        throw d.a(); 
      c3 = this.a.a();
      int i = paramc1.a(paramc1.a());
      i = this.a.c(i);
      while (paramc2.a() >= paramc1.a() && !paramc2.b()) {
        int j = paramc2.a() - paramc1.a();
        int k = this.a.d(paramc2.a(paramc2.a()), i);
        c3 = c3.a(this.a.a(j, k));
        paramc2 = paramc2.b(paramc1.a(j, k));
      } 
      c2 = c3.c(c1).b(c2).c();
      c3 = paramc1;
      paramc1 = paramc2;
      paramc2 = c2;
      c2 = c1;
      c1 = paramc2;
      paramc2 = c3;
    } 
    paramInt = c1.a(0);
    if (paramInt == 0)
      throw d.a(); 
    paramInt = this.a.c(paramInt);
    return new c[] { c1.c(paramInt), paramc1.c(paramInt) };
  }
  
  public void a(int[] paramArrayOfint1, int paramInt, int[] paramArrayOfint2) {
    c c = new c(this.a, paramArrayOfint1);
    int[] arrayOfInt = new int[paramInt];
    boolean bool = false;
    int i = paramInt;
    int j = 0;
    while (i > 0) {
      int k = c.b(this.a.a(i));
      arrayOfInt[paramInt - i] = k;
      if (k != 0)
        j = 1; 
      i--;
    } 
    if (j) {
      c = this.a.b();
      j = paramArrayOfint2.length;
      for (i = 0; i < j; i++) {
        int k = paramArrayOfint2[i];
        k = this.a.a(paramArrayOfint1.length - 1 - k);
        c = c.c(new c(this.a, new int[] { this.a.c(0, k), 1 }));
      } 
      c c1 = new c(this.a, arrayOfInt);
      c[] arrayOfC = a(this.a.a(paramInt, 1), c1, paramInt);
      c1 = arrayOfC[0];
      c c2 = arrayOfC[1];
      int[] arrayOfInt2 = a(c1);
      int[] arrayOfInt1 = a(c2, c1, arrayOfInt2);
      for (paramInt = bool; paramInt < arrayOfInt2.length; paramInt++) {
        i = paramArrayOfint1.length - 1 - this.a.b(arrayOfInt2[paramInt]);
        if (i < 0)
          throw d.a(); 
        paramArrayOfint1[i] = this.a.c(paramArrayOfint1[i], arrayOfInt1[paramInt]);
      } 
    } 
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\f\a\a\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */