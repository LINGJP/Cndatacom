package com.a.a.e.a;

import com.a.a.e.k;
import com.a.a.i;

public abstract class a extends k {
  private final int[] a = new int[4];
  
  private final int[] b = new int[8];
  
  private final float[] c = new float[4];
  
  private final float[] d = new float[4];
  
  private final int[] e = new int[this.b.length / 2];
  
  private final int[] f = new int[this.b.length / 2];
  
  protected static int a(int[] paramArrayOfint) {
    int m = paramArrayOfint.length;
    int i = 0;
    int j = 0;
    while (i < m) {
      j += paramArrayOfint[i];
      i++;
    } 
    return j;
  }
  
  protected static int a(int[] paramArrayOfint, int[][] paramArrayOfint1) {
    for (int i = 0; i < paramArrayOfint1.length; i++) {
      if (a(paramArrayOfint, paramArrayOfint1[i], 115) < 51)
        return i; 
    } 
    throw i.a();
  }
  
  protected static void a(int[] paramArrayOfint, float[] paramArrayOffloat) {
    float f = paramArrayOffloat[0];
    int i = 1;
    int j = 0;
    while (i < paramArrayOfint.length) {
      float f1 = f;
      if (paramArrayOffloat[i] > f) {
        f1 = paramArrayOffloat[i];
        j = i;
      } 
      i++;
      f = f1;
    } 
    paramArrayOfint[j] = paramArrayOfint[j] + 1;
  }
  
  protected static void b(int[] paramArrayOfint, float[] paramArrayOffloat) {
    float f = paramArrayOffloat[0];
    int i = 1;
    int j = 0;
    while (i < paramArrayOfint.length) {
      float f1 = f;
      if (paramArrayOffloat[i] < f) {
        f1 = paramArrayOffloat[i];
        j = i;
      } 
      i++;
      f = f1;
    } 
    paramArrayOfint[j] = paramArrayOfint[j] - 1;
  }
  
  protected static boolean b(int[] paramArrayOfint) {
    boolean bool = false;
    int i = paramArrayOfint[0] + paramArrayOfint[1];
    int j = paramArrayOfint[2];
    int m = paramArrayOfint[3];
    float f = i / (j + i + m);
    if (f >= 0.7916667F && f <= 0.89285713F) {
      int n = Integer.MIN_VALUE;
      int i1 = paramArrayOfint.length;
      j = 0;
      for (i = Integer.MAX_VALUE; j < i1; i = i3) {
        int i2 = paramArrayOfint[j];
        m = n;
        if (i2 > n)
          m = i2; 
        int i3 = i;
        if (i2 < i)
          i3 = i2; 
        j++;
        n = m;
      } 
      if (n < i * 10)
        bool = true; 
      return bool;
    } 
    return false;
  }
  
  protected final int[] b() {
    return this.a;
  }
  
  protected final int[] c() {
    return this.b;
  }
  
  protected final float[] d() {
    return this.c;
  }
  
  protected final float[] e() {
    return this.d;
  }
  
  protected final int[] f() {
    return this.e;
  }
  
  protected final int[] g() {
    return this.f;
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\e\a\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */