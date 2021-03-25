package com.a.a.e;

import com.a.a.e;
import com.a.a.i;
import com.a.a.m;
import com.a.a.o;
import java.util.Arrays;
import java.util.Map;

public final class a extends k {
  static final char[] a = "0123456789-$:/.+ABCD".toCharArray();
  
  static final int[] b = new int[] { 
      3, 6, 9, 96, 18, 66, 33, 36, 48, 72, 
      12, 24, 69, 81, 84, 21, 26, 41, 11, 14 };
  
  private static final char[] c = new char[] { 'A', 'B', 'C', 'D' };
  
  private final StringBuilder d = new StringBuilder(20);
  
  private int[] e = new int[80];
  
  private int f = 0;
  
  private void a(com.a.a.b.a parama) {
    this.f = 0;
    int j = parama.d(0);
    int m = parama.a();
    if (j >= m)
      throw i.a(); 
    boolean bool = true;
    int i = 0;
    while (j < m) {
      if (parama.a(j) ^ bool) {
        i++;
      } else {
        b(i);
        int n = bool ^ true;
        i = 1;
      } 
      j++;
    } 
    b(i);
  }
  
  static boolean a(char[] paramArrayOfchar, char paramChar) {
    if (paramArrayOfchar != null) {
      int j = paramArrayOfchar.length;
      for (int i = 0; i < j; i++) {
        if (paramArrayOfchar[i] == paramChar)
          return true; 
      } 
    } 
    return false;
  }
  
  private int b() {
    for (int i = 1; i < this.f; i += 2) {
      int j = c(i);
      if (j != -1 && a(c, a[j])) {
        j = i;
        int m = 0;
        while (j < i + 7) {
          m += this.e[j];
          j++;
        } 
        if (i == 1 || this.e[i - 1] >= m / 2)
          return i; 
      } 
    } 
    throw i.a();
  }
  
  private void b(int paramInt) {
    this.e[this.f] = paramInt;
    this.f++;
    if (this.f >= this.e.length) {
      int[] arrayOfInt = new int[this.f * 2];
      System.arraycopy(this.e, 0, arrayOfInt, 0, this.f);
      this.e = arrayOfInt;
    } 
  }
  
  private int c(int paramInt) {
    int i4 = paramInt + 7;
    if (i4 >= this.f)
      return -1; 
    int[] arrayOfInt = this.e;
    int i3 = Integer.MAX_VALUE;
    boolean bool = false;
    int j = paramInt;
    int n = Integer.MAX_VALUE;
    int i;
    for (i = 0; j < i4; i = i7) {
      int i6 = arrayOfInt[j];
      int i5 = n;
      if (i6 < n)
        i5 = i6; 
      int i7 = i;
      if (i6 > i)
        i7 = i6; 
      j += 2;
      n = i5;
    } 
    int i2 = (n + i) / 2;
    i = paramInt + 1;
    j = 0;
    n = i3;
    while (i < i4) {
      int i6 = arrayOfInt[i];
      int i5 = n;
      if (i6 < n)
        i5 = i6; 
      int i7 = j;
      if (i6 > j)
        i7 = i6; 
      i += 2;
      n = i5;
      j = i7;
    } 
    int i1 = (n + j) / 2;
    j = 0;
    int m = 128;
    i = 0;
    while (true) {
      n = bool;
      if (j < 7) {
        int i5;
        if ((j & 0x1) == 0) {
          i5 = i2;
        } else {
          i5 = i1;
        } 
        m >>= 1;
        n = i;
        if (arrayOfInt[paramInt + j] > i5)
          n = i | m; 
        j++;
        i = n;
        continue;
      } 
      break;
    } 
    while (n < b.length) {
      if (b[n] == i)
        return n; 
      n++;
    } 
    return -1;
  }
  
  public m a(int paramInt, com.a.a.b.a parama, Map<e, ?> paramMap) {
    int n;
    Arrays.fill(this.e, 0);
    a(parama);
    int j = b();
    this.d.setLength(0);
    int i = j;
    do {
      int i3 = c(i);
      if (i3 == -1)
        throw i.a(); 
      this.d.append((char)i3);
      n = i + 8;
      if (this.d.length() > 1 && a(c, a[i3]))
        break; 
      i = n;
    } while (n < this.f);
    int[] arrayOfInt = this.e;
    int i1 = n - 1;
    int i2 = arrayOfInt[i1];
    i = -8;
    int m = 0;
    while (i < -1) {
      m += this.e[n + i];
      i++;
    } 
    if (n < this.f && i2 < m / 2)
      throw i.a(); 
    a(j);
    for (i = 0; i < this.d.length(); i++)
      this.d.setCharAt(i, a[this.d.charAt(i)]); 
    char c = this.d.charAt(0);
    if (!a(c, c))
      throw i.a(); 
    c = this.d.charAt(this.d.length() - 1);
    if (!a(c, c))
      throw i.a(); 
    if (this.d.length() <= 3)
      throw i.a(); 
    this.d.deleteCharAt(this.d.length() - 1);
    this.d.deleteCharAt(0);
    m = 0;
    i = 0;
    while (m < j) {
      i += this.e[m];
      m++;
    } 
    float f1 = i;
    while (j < i1) {
      i += this.e[j];
      j++;
    } 
    float f2 = i;
    String str = this.d.toString();
    float f3 = paramInt;
    o o1 = new o(f1, f3);
    o o2 = new o(f2, f3);
    com.a.a.a a1 = com.a.a.a.b;
    return new m(str, null, new o[] { o1, o2 }, a1);
  }
  
  void a(int paramInt) {
    int[] arrayOfInt1 = new int[4];
    arrayOfInt1[0] = 0;
    arrayOfInt1[1] = 0;
    arrayOfInt1[2] = 0;
    arrayOfInt1[3] = 0;
    int[] arrayOfInt2 = new int[4];
    arrayOfInt2[0] = 0;
    arrayOfInt2[1] = 0;
    arrayOfInt2[2] = 0;
    arrayOfInt2[3] = 0;
    int n = this.d.length() - 1;
    int m = 0;
    int j = paramInt;
    for (int i = 0;; i++) {
      int i2 = b[this.d.charAt(i)];
      int i1;
      for (i1 = 6; i1 >= 0; i1--) {
        int i3 = (i1 & 0x1) + (i2 & 0x1) * 2;
        arrayOfInt1[i3] = arrayOfInt1[i3] + this.e[j + i1];
        arrayOfInt2[i3] = arrayOfInt2[i3] + 1;
        i2 >>= 1;
      } 
      if (i >= n) {
        int[] arrayOfInt3 = new int[4];
        int[] arrayOfInt4 = new int[4];
        i1 = 0;
        while (true) {
          i = m;
          j = paramInt;
          if (i1 < 2) {
            arrayOfInt4[i1] = 0;
            i = i1 + 2;
            arrayOfInt4[i] = (arrayOfInt1[i1] << 8) / arrayOfInt2[i1] + (arrayOfInt1[i] << 8) / arrayOfInt2[i] >> 1;
            arrayOfInt3[i1] = arrayOfInt4[i];
            arrayOfInt3[i] = (arrayOfInt1[i] * 512 + 384) / arrayOfInt2[i];
            i1++;
            continue;
          } 
          break;
        } 
        while (true) {
          i1 = b[this.d.charAt(i)];
          for (paramInt = 6; paramInt >= 0; paramInt--) {
            i2 = (paramInt & 0x1) + (i1 & 0x1) * 2;
            m = this.e[j + paramInt] << 8;
            if (m < arrayOfInt4[i2] || m > arrayOfInt3[i2])
              throw i.a(); 
            i1 >>= 1;
          } 
          if (i >= n)
            return; 
          j += 8;
          i++;
        } 
        break;
      } 
      j += 8;
    } 
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\e\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */