package com.a.a.g.b;

import com.a.a.i;
import com.a.a.p;
import java.util.ArrayList;
import java.util.List;

final class b {
  private final com.a.a.b.b a;
  
  private final List<a> b;
  
  private final int c;
  
  private final int d;
  
  private final int e;
  
  private final int f;
  
  private final float g;
  
  private final int[] h;
  
  private final p i;
  
  b(com.a.a.b.b paramb, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat, p paramp) {
    this.a = paramb;
    this.b = new ArrayList<a>(5);
    this.c = paramInt1;
    this.d = paramInt2;
    this.e = paramInt3;
    this.f = paramInt4;
    this.g = paramFloat;
    this.h = new int[3];
    this.i = paramp;
  }
  
  private float a(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    com.a.a.b.b b1 = this.a;
    int j = b1.e();
    int[] arrayOfInt = this.h;
    arrayOfInt[0] = 0;
    arrayOfInt[1] = 0;
    arrayOfInt[2] = 0;
    int i;
    for (i = paramInt1; i >= 0 && b1.a(paramInt2, i) && arrayOfInt[1] <= paramInt3; i--)
      arrayOfInt[1] = arrayOfInt[1] + 1; 
    float f = Float.NaN;
    if (i >= 0) {
      if (arrayOfInt[1] > paramInt3)
        return Float.NaN; 
      while (i >= 0 && !b1.a(paramInt2, i) && arrayOfInt[0] <= paramInt3) {
        arrayOfInt[0] = arrayOfInt[0] + 1;
        i--;
      } 
      if (arrayOfInt[0] > paramInt3)
        return Float.NaN; 
      while (++paramInt1 < j && b1.a(paramInt2, paramInt1) && arrayOfInt[1] <= paramInt3) {
        arrayOfInt[1] = arrayOfInt[1] + 1;
        paramInt1++;
      } 
      if (paramInt1 != j) {
        if (arrayOfInt[1] > paramInt3)
          return Float.NaN; 
        while (paramInt1 < j && !b1.a(paramInt2, paramInt1) && arrayOfInt[2] <= paramInt3) {
          arrayOfInt[2] = arrayOfInt[2] + 1;
          paramInt1++;
        } 
        if (arrayOfInt[2] > paramInt3)
          return Float.NaN; 
        if (Math.abs(arrayOfInt[0] + arrayOfInt[1] + arrayOfInt[2] - paramInt4) * 5 >= paramInt4 * 2)
          return Float.NaN; 
        if (a(arrayOfInt))
          f = a(arrayOfInt, paramInt1); 
        return f;
      } 
      return Float.NaN;
    } 
    return Float.NaN;
  }
  
  private static float a(int[] paramArrayOfint, int paramInt) {
    return (paramInt - paramArrayOfint[2]) - paramArrayOfint[1] / 2.0F;
  }
  
  private a a(int[] paramArrayOfint, int paramInt1, int paramInt2) {
    int i = paramArrayOfint[0];
    int j = paramArrayOfint[1];
    int k = paramArrayOfint[2];
    float f1 = a(paramArrayOfint, paramInt2);
    float f2 = a(paramInt1, (int)f1, paramArrayOfint[1] * 2, i + j + k);
    if (!Float.isNaN(f2)) {
      float f = (paramArrayOfint[0] + paramArrayOfint[1] + paramArrayOfint[2]) / 3.0F;
      for (a a1 : this.b) {
        if (a1.a(f, f2, f1))
          return a1.b(f2, f1, f); 
      } 
      a a = new a(f1, f2, f);
      this.b.add(a);
      if (this.i != null)
        this.i.a(a); 
    } 
    return null;
  }
  
  private boolean a(int[] paramArrayOfint) {
    float f1 = this.g;
    float f2 = f1 / 2.0F;
    int i;
    for (i = 0; i < 3; i++) {
      if (Math.abs(f1 - paramArrayOfint[i]) >= f2)
        return false; 
    } 
    return true;
  }
  
  a a() {
    int j = this.c;
    int k = this.f;
    int m = this.e + j;
    int n = this.d;
    int[] arrayOfInt = new int[3];
    for (int i = 0; i < k; i++) {
      if ((i & 0x1) == 0) {
        i1 = i + 1 >> 1;
      } else {
        i1 = -(i + 1 >> 1);
      } 
      int i4 = i1 + n + (k >> 1);
      arrayOfInt[0] = 0;
      arrayOfInt[1] = 0;
      arrayOfInt[2] = 0;
      int i2;
      for (i2 = j; i2 < m && !this.a.a(i2, i4); i2++);
      int i1 = 0;
      int i3;
      for (i3 = i2; i3 < m; i3++) {
        if (this.a.a(i3, i4)) {
          if (i1 == 1) {
            arrayOfInt[i1] = arrayOfInt[i1] + 1;
          } else if (i1 == 2) {
            if (a(arrayOfInt)) {
              a a = a(arrayOfInt, i4, i3);
              if (a != null)
                return a; 
            } 
            arrayOfInt[0] = arrayOfInt[2];
            arrayOfInt[1] = 1;
            arrayOfInt[2] = 0;
            i1 = 1;
          } else {
            arrayOfInt[++i1] = arrayOfInt[i1] + 1;
          } 
        } else {
          i2 = i1;
          if (i1 == 1)
            i2 = i1 + 1; 
          arrayOfInt[i2] = arrayOfInt[i2] + 1;
          i1 = i2;
        } 
      } 
      if (a(arrayOfInt)) {
        a a = a(arrayOfInt, i4, m);
        if (a != null)
          return a; 
      } 
    } 
    if (!this.b.isEmpty())
      return this.b.get(0); 
    throw i.a();
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\g\b\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */