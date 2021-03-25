package com.a.a.e;

import com.a.a.a;
import com.a.a.b.a;
import com.a.a.d;
import com.a.a.e;
import com.a.a.f;
import com.a.a.i;
import com.a.a.m;
import com.a.a.o;
import java.util.Arrays;
import java.util.Map;

public final class c extends k {
  static final int[] a;
  
  private static final char[] b = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%".toCharArray();
  
  private static final int c;
  
  private final boolean d;
  
  private final boolean e;
  
  private final StringBuilder f;
  
  private final int[] g;
  
  static {
    a = new int[] { 
        52, 289, 97, 352, 49, 304, 112, 37, 292, 100, 
        265, 73, 328, 25, 280, 88, 13, 268, 76, 28, 
        259, 67, 322, 19, 274, 82, 7, 262, 70, 22, 
        385, 193, 448, 145, 400, 208, 133, 388, 196, 148, 
        168, 162, 138, 42 };
    c = a[39];
  }
  
  public c() {
    this(false);
  }
  
  public c(boolean paramBoolean) {
    this(paramBoolean, false);
  }
  
  public c(boolean paramBoolean1, boolean paramBoolean2) {
    this.d = paramBoolean1;
    this.e = paramBoolean2;
    this.f = new StringBuilder(20);
    this.g = new int[9];
  }
  
  private static char a(int paramInt) {
    for (int i = 0; i < a.length; i++) {
      if (a[i] == paramInt)
        return b[i]; 
    } 
    throw i.a();
  }
  
  private static int a(int[] paramArrayOfint) {
    int j = paramArrayOfint.length;
    boolean bool = false;
    for (int i = 0;; i = m) {
      int i3 = paramArrayOfint.length;
      int n = 0;
      int m;
      for (m = Integer.MAX_VALUE; n < i3; m = i4) {
        int i5 = paramArrayOfint[n];
        int i4 = m;
        if (i5 < m) {
          i4 = m;
          if (i5 > i)
            i4 = i5; 
        } 
        n++;
      } 
      int i2 = 0;
      i = 0;
      int i1 = 0;
      for (n = 0; i2 < j; n = i3) {
        int i6 = paramArrayOfint[i2];
        int i5 = i;
        int i4 = i1;
        i3 = n;
        if (i6 > m) {
          i4 = i1 | 1 << j - 1 - i2;
          i5 = i + 1;
          i3 = n + i6;
        } 
        i2++;
        i = i5;
        i1 = i4;
      } 
      if (i == 3) {
        i2 = i;
        i = bool;
        while (i < j && i2 > 0) {
          int i4 = paramArrayOfint[i];
          i3 = i2;
          if (i4 > m) {
            i3 = i2 - 1;
            if (i4 << 1 >= n)
              return -1; 
          } 
          i++;
          i2 = i3;
        } 
        return i1;
      } 
      if (i <= 3)
        return -1; 
    } 
  }
  
  private static String a(CharSequence paramCharSequence) {
    int j = paramCharSequence.length();
    StringBuilder stringBuilder = new StringBuilder(j);
    for (int i = 0; i < j; i++) {
      char c1 = paramCharSequence.charAt(i);
      if (c1 == '+' || c1 == '$' || c1 == '%' || c1 == '/') {
        char c2 = paramCharSequence.charAt(++i);
        if (c1 != '+') {
          if (c1 != '/') {
            switch (c1) {
              default:
                c1 = Character.MIN_VALUE;
                break;
              case '%':
                if (c2 >= 'A' && c2 <= 'E') {
                  c1 = (char)(c2 - 38);
                  break;
                } 
                if (c2 >= 'F' && c2 <= 'W') {
                  c1 = (char)(c2 - 11);
                  break;
                } 
                throw f.a();
              case '$':
                if (c2 >= 'A' && c2 <= 'Z') {
                  c1 = (char)(c2 - 64);
                  break;
                } 
                throw f.a();
            } 
          } else if (c2 >= 'A' && c2 <= 'O') {
            c1 = (char)(c2 - 32);
          } else if (c2 == 'Z') {
            c1 = ':';
          } else {
            throw f.a();
          } 
        } else if (c2 >= 'A' && c2 <= 'Z') {
          c1 = (char)(c2 + 32);
        } else {
          throw f.a();
        } 
        stringBuilder.append(c1);
      } else {
        stringBuilder.append(c1);
      } 
    } 
    return stringBuilder.toString();
  }
  
  private static int[] a(a parama, int[] paramArrayOfint) {
    int n = parama.a();
    int m = parama.c(0);
    int i1 = paramArrayOfint.length;
    int i = m;
    boolean bool = false;
    int j = 0;
    while (m < n) {
      int i2;
      if (parama.a(m) ^ bool) {
        paramArrayOfint[j] = paramArrayOfint[j] + 1;
        i2 = i;
      } else {
        int i4 = i1 - 1;
        if (j == i4) {
          if (a(paramArrayOfint) == c && parama.a(Math.max(0, i - (m - i >> 1)), i, false))
            return new int[] { i, m }; 
          int i5 = i + paramArrayOfint[0] + paramArrayOfint[1];
          i = i1 - 2;
          System.arraycopy(paramArrayOfint, 2, paramArrayOfint, 0, i);
          paramArrayOfint[i] = 0;
          paramArrayOfint[i4] = 0;
          i = j - 1;
          j = i5;
        } else {
          int i5 = j + 1;
          j = i;
          i = i5;
        } 
        paramArrayOfint[i] = 1;
        int i3 = bool ^ true;
        i2 = j;
        j = i;
      } 
      m++;
      i = i2;
    } 
    throw i.a();
  }
  
  public m a(int paramInt, a parama, Map<e, ?> paramMap) {
    int[] arrayOfInt2 = this.g;
    Arrays.fill(arrayOfInt2, 0);
    StringBuilder stringBuilder = this.f;
    stringBuilder.setLength(0);
    int[] arrayOfInt1 = a(parama, arrayOfInt2);
    int i = parama.c(arrayOfInt1[1]);
    int j = parama.a();
    while (true) {
      a(parama, i, arrayOfInt2);
      int m = a(arrayOfInt2);
      if (m < 0)
        throw i.a(); 
      char c1 = a(m);
      stringBuilder.append(c1);
      int i1 = arrayOfInt2.length;
      int n = i;
      for (m = 0; m < i1; m++)
        n += arrayOfInt2[m]; 
      i1 = parama.c(n);
      if (c1 == '*') {
        String str;
        stringBuilder.setLength(stringBuilder.length() - 1);
        int i2 = arrayOfInt2.length;
        m = 0;
        n = 0;
        while (m < i2) {
          n += arrayOfInt2[m];
          m++;
        } 
        if (i1 != j && i1 - i - n >> 1 < n)
          throw i.a(); 
        if (this.d) {
          j = stringBuilder.length() - 1;
          m = 0;
          n = 0;
          while (m < j) {
            n += "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%".indexOf(this.f.charAt(m));
            m++;
          } 
          if (stringBuilder.charAt(j) != b[n % 43])
            throw d.a(); 
          stringBuilder.setLength(j);
        } 
        if (stringBuilder.length() == 0)
          throw i.a(); 
        if (this.e) {
          str = a(stringBuilder);
        } else {
          str = stringBuilder.toString();
        } 
        float f1 = (arrayOfInt1[1] + arrayOfInt1[0]) / 2.0F;
        float f2 = (i1 + i) / 2.0F;
        float f3 = paramInt;
        o o1 = new o(f1, f3);
        o o2 = new o(f2, f3);
        a a1 = a.c;
        return new m(str, null, new o[] { o1, o2 }, a1);
      } 
      i = i1;
    } 
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\e\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */