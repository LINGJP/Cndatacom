package com.a.a.b;

import com.a.a.b;
import com.a.a.g;
import java.lang.reflect.Array;

public final class j extends h {
  private b a;
  
  public j(g paramg) {
    super(paramg);
  }
  
  private static int a(int paramInt1, int paramInt2, int paramInt3) {
    if (paramInt1 < paramInt2)
      return paramInt2; 
    paramInt2 = paramInt1;
    if (paramInt1 > paramInt3)
      paramInt2 = paramInt3; 
    return paramInt2;
  }
  
  private static void a(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, int paramInt3, int paramInt4, b paramb) {
    int i = paramInt2 * paramInt4 + paramInt1;
    int k = 0;
    while (k < 8) {
      int m;
      for (m = 0; m < 8; m++) {
        if ((paramArrayOfbyte[i + m] & 0xFF) <= paramInt3)
          paramb.b(paramInt1 + m, paramInt2 + k); 
      } 
      k++;
      i += paramInt4;
    } 
  }
  
  private static void a(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[][] paramArrayOfint, b paramb) {
    int i;
    for (i = 0; i < paramInt2; i++) {
      int m = i << 3;
      int n = paramInt4 - 8;
      int k = m;
      if (m > n)
        k = n; 
      for (m = 0; m < paramInt1; m++) {
        n = m << 3;
        int i1 = paramInt3 - 8;
        if (n > i1)
          n = i1; 
        int i3 = a(m, 2, paramInt1 - 3);
        int i4 = a(i, 2, paramInt2 - 3);
        i1 = -2;
        int i2 = 0;
        while (i1 <= 2) {
          int[] arrayOfInt = paramArrayOfint[i4 + i1];
          i2 += arrayOfInt[i3 - 2] + arrayOfInt[i3 - 1] + arrayOfInt[i3] + arrayOfInt[i3 + 1] + arrayOfInt[i3 + 2];
          i1++;
        } 
        a(paramArrayOfbyte, n, k, i2 / 25, paramInt3, paramb);
      } 
    } 
  }
  
  private static int[][] a(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    int[][] arrayOfInt = (int[][])Array.newInstance(int.class, new int[] { paramInt2, paramInt1 });
    int i;
    for (i = 0; i < paramInt2; i++) {
      int m = i << 3;
      int k = paramInt4 - 8;
      if (m > k)
        m = k; 
      int n;
      for (n = 0; n < paramInt1; n++) {
        int i1 = n << 3;
        int i2 = paramInt3 - 8;
        k = i1;
        if (i1 > i2)
          k = i2; 
        i1 = m * paramInt3 + k;
        k = 0;
        int i3 = 0;
        i2 = 0;
        int i4 = 255;
        while (k < 8) {
          int i6 = i2;
          i2 = i3;
          int i5 = 0;
          i3 = i6;
          while (i5 < 8) {
            int i8 = paramArrayOfbyte[i1 + i5] & 0xFF;
            int i9 = i2 + i8;
            i6 = i4;
            if (i8 < i4)
              i6 = i8; 
            i2 = i3;
            if (i8 > i3)
              i2 = i8; 
            i5++;
            i3 = i2;
            i2 = i9;
            i4 = i6;
          } 
          int i7 = k;
          i5 = i2;
          i6 = i1;
          if (i3 - i4 > 24) {
            i5 = i1;
            int i8 = k;
            label52: while (true) {
              k = i8 + 1;
              i1 = i5 + paramInt3;
              i7 = k;
              i5 = i2;
              i6 = i1;
              if (k < 8) {
                i6 = 0;
                i7 = i2;
                while (true) {
                  i8 = k;
                  i2 = i7;
                  i5 = i1;
                  if (i6 < 8) {
                    i7 += paramArrayOfbyte[i1 + i6] & 0xFF;
                    i6++;
                    continue;
                  } 
                  continue label52;
                } 
              } 
              break;
            } 
          } 
          k = i7 + 1;
          i1 = i6 + paramInt3;
          i2 = i3;
          i3 = i5;
        } 
        k = i3 >> 6;
        if (i2 - i4 <= 24) {
          i1 = i4 >> 1;
          k = i1;
          if (i > 0) {
            k = i1;
            if (n > 0) {
              k = i - 1;
              i2 = arrayOfInt[k][n];
              int[] arrayOfInt1 = arrayOfInt[i];
              i3 = n - 1;
              i2 = i2 + arrayOfInt1[i3] * 2 + arrayOfInt[k][i3] >> 2;
              k = i1;
              if (i4 < i2)
                k = i2; 
            } 
          } 
        } 
        arrayOfInt[i][n] = k;
      } 
    } 
    return arrayOfInt;
  }
  
  public b a(g paramg) {
    return new j(paramg);
  }
  
  public b b() {
    if (this.a != null)
      return this.a; 
    g g = a();
    int i = g.b();
    int k = g.c();
    if (i >= 40 && k >= 40) {
      byte[] arrayOfByte = g.a();
      int n = i >> 3;
      int m = n;
      if ((i & 0x7) != 0)
        m = n + 1; 
      int i1 = k >> 3;
      n = i1;
      if ((k & 0x7) != 0)
        n = i1 + 1; 
      int[][] arrayOfInt = a(arrayOfByte, m, n, i, k);
      b b1 = new b(i, k);
      a(arrayOfByte, m, n, i, k, arrayOfInt, b1);
      this.a = b1;
    } else {
      this.a = super.b();
    } 
    return this.a;
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\b\j.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */