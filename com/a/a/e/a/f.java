package com.a.a.e.a;

public final class f {
  private static int a(int paramInt1, int paramInt2) {
    int n;
    int k = paramInt1 - paramInt2;
    int i = k;
    int j = paramInt2;
    if (k > paramInt2) {
      j = k;
      i = paramInt2;
    } 
    paramInt2 = 1;
    int m = 1;
    k = paramInt1;
    paramInt1 = m;
    while (true) {
      m = paramInt2;
      n = paramInt1;
      if (k > j) {
        n = paramInt2 * k;
        paramInt2 = n;
        m = paramInt1;
        if (paramInt1 <= i) {
          paramInt2 = n / paramInt1;
          m = paramInt1 + 1;
        } 
        k--;
        paramInt1 = m;
        continue;
      } 
      break;
    } 
    while (n <= i) {
      m /= n;
      n++;
    } 
    return m;
  }
  
  public static int a(int[] paramArrayOfint, int paramInt, boolean paramBoolean) {
    int[] arrayOfInt = paramArrayOfint;
    int i2 = arrayOfInt.length;
    int k = arrayOfInt.length;
    int j = 0;
    int i = 0;
    while (j < k) {
      i += arrayOfInt[j];
      j++;
    } 
    int n = 0;
    j = 0;
    int i1 = 0;
    int m = i;
    i = j;
    while (true) {
      int i3 = i2 - 1;
      if (n < i3) {
        int i5 = 1 << n;
        i |= i5;
        int i4 = 1;
        while (i4 < paramArrayOfint[n]) {
          int i7 = m - i4;
          int i8 = i2 - n;
          int i6 = i8 - 2;
          k = a(i7 - 1, i6);
          j = k;
          if (paramBoolean) {
            j = k;
            if (i == 0) {
              int i9 = i8 - 1;
              j = k;
              if (i7 - i9 >= i9)
                j = k - a(i7 - i8, i6); 
            } 
          } 
          if (i8 - 1 > 1) {
            k = i7 - i6;
            i6 = 0;
            while (k > paramInt) {
              i6 += a(i7 - k - 1, i8 - 3);
              k--;
            } 
            k = j - i6 * (i3 - n);
          } else {
            k = j;
            if (i7 > paramInt)
              k = j - 1; 
          } 
          i1 += k;
          i4++;
          i &= i5 ^ 0xFFFFFFFF;
        } 
        m -= i4;
        n++;
        continue;
      } 
      return i1;
    } 
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\e\a\f.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */