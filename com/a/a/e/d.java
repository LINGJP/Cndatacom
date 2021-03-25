package com.a.a.e;

import com.a.a.a;
import com.a.a.b.a;
import com.a.a.e;
import com.a.a.f;
import com.a.a.i;
import com.a.a.m;
import com.a.a.o;
import java.util.Arrays;
import java.util.Map;

public final class d extends k {
  private static final char[] a = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".toCharArray();
  
  private static final int[] b = new int[] { 
      276, 328, 324, 322, 296, 292, 290, 336, 274, 266, 
      424, 420, 418, 404, 402, 394, 360, 356, 354, 308, 
      282, 344, 332, 326, 300, 278, 436, 434, 428, 422, 
      406, 410, 364, 358, 310, 314, 302, 468, 466, 458, 
      366, 374, 430, 294, 474, 470, 306, 350 };
  
  private static final int c = b[47];
  
  private final StringBuilder d = new StringBuilder(20);
  
  private final int[] e = new int[6];
  
  private static char a(int paramInt) {
    for (int i = 0; i < b.length; i++) {
      if (b[i] == paramInt)
        return a[i]; 
    } 
    throw i.a();
  }
  
  private static int a(int[] paramArrayOfint) {
    int n = paramArrayOfint.length;
    int m = paramArrayOfint.length;
    int i = 0;
    int j = 0;
    while (i < m) {
      j += paramArrayOfint[i];
      i++;
    } 
    m = 0;
    i = 0;
    while (m < n) {
      int i3 = (paramArrayOfint[m] << 8) * 9 / j;
      int i2 = i3 >> 8;
      int i1 = i2;
      if ((i3 & 0xFF) > 127)
        i1 = i2 + 1; 
      if (i1 < 1 || i1 > 4)
        return -1; 
      if ((m & 0x1) == 0) {
        for (i2 = 0; i2 < i1; i2++)
          i = i << 1 | 0x1; 
      } else {
        i <<= i1;
      } 
      m++;
    } 
    return i;
  }
  
  private static String a(CharSequence paramCharSequence) {
    int j = paramCharSequence.length();
    StringBuilder stringBuilder = new StringBuilder(j);
    for (int i = 0; i < j; i++) {
      char c = paramCharSequence.charAt(i);
      if (c >= 'a' && c <= 'd') {
        if (i >= j - 1)
          throw f.a(); 
        char c1 = paramCharSequence.charAt(++i);
        switch (c) {
          default:
            c = Character.MIN_VALUE;
            break;
          case 'd':
            if (c1 >= 'A' && c1 <= 'Z') {
              c = (char)(c1 + 32);
              break;
            } 
            throw f.a();
          case 'c':
            if (c1 >= 'A' && c1 <= 'O') {
              c = (char)(c1 - 32);
              break;
            } 
            if (c1 == 'Z') {
              c = ':';
              break;
            } 
            throw f.a();
          case 'b':
            if (c1 >= 'A' && c1 <= 'E') {
              c = (char)(c1 - 38);
              break;
            } 
            if (c1 >= 'F' && c1 <= 'W') {
              c = (char)(c1 - 11);
              break;
            } 
            throw f.a();
          case 'a':
            if (c1 >= 'A' && c1 <= 'Z') {
              c = (char)(c1 - 64);
              break;
            } 
            throw f.a();
        } 
        stringBuilder.append(c);
      } else {
        stringBuilder.append(c);
      } 
    } 
    return stringBuilder.toString();
  }
  
  private static void a(CharSequence paramCharSequence, int paramInt1, int paramInt2) {
    int j = paramInt1 - 1;
    int m = 0;
    int i = 1;
    while (j >= 0) {
      m += "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".indexOf(paramCharSequence.charAt(j)) * i;
      int n = i + 1;
      i = n;
      if (n > paramInt2)
        i = 1; 
      j--;
    } 
    if (paramCharSequence.charAt(paramInt1) != a[m % 47])
      throw com.a.a.d.a(); 
  }
  
  private int[] a(a parama) {
    int n = parama.a();
    int m = parama.c(0);
    Arrays.fill(this.e, 0);
    int[] arrayOfInt = this.e;
    int i1 = arrayOfInt.length;
    int i = m;
    boolean bool = false;
    int j = 0;
    while (m < n) {
      int i2;
      if (parama.a(m) ^ bool) {
        arrayOfInt[j] = arrayOfInt[j] + 1;
        i2 = i;
      } else {
        int i4 = i1 - 1;
        if (j == i4) {
          if (a(arrayOfInt) == c)
            return new int[] { i, m }; 
          int i5 = i + arrayOfInt[0] + arrayOfInt[1];
          i = i1 - 2;
          System.arraycopy(arrayOfInt, 2, arrayOfInt, 0, i);
          arrayOfInt[i] = 0;
          arrayOfInt[i4] = 0;
          i = j - 1;
          j = i5;
        } else {
          int i5 = j + 1;
          j = i;
          i = i5;
        } 
        arrayOfInt[i] = 1;
        int i3 = bool ^ true;
        i2 = j;
        j = i;
      } 
      m++;
      i = i2;
    } 
    throw i.a();
  }
  
  private static void b(CharSequence paramCharSequence) {
    int i = paramCharSequence.length();
    a(paramCharSequence, i - 2, 20);
    a(paramCharSequence, i - 1, 15);
  }
  
  public m a(int paramInt, a parama, Map<e, ?> paramMap) {
    int[] arrayOfInt1 = a(parama);
    int i = parama.c(arrayOfInt1[1]);
    int j = parama.a();
    int[] arrayOfInt2 = this.e;
    Arrays.fill(arrayOfInt2, 0);
    StringBuilder stringBuilder = this.d;
    stringBuilder.setLength(0);
    while (true) {
      a(parama, i, arrayOfInt2);
      int m = a(arrayOfInt2);
      if (m < 0)
        throw i.a(); 
      char c = a(m);
      stringBuilder.append(c);
      int i1 = arrayOfInt2.length;
      int n = i;
      for (m = 0; m < i1; m++)
        n += arrayOfInt2[m]; 
      m = parama.c(n);
      if (c == '*') {
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        if (m == j || !parama.a(m))
          throw i.a(); 
        if (stringBuilder.length() < 2)
          throw i.a(); 
        b(stringBuilder);
        stringBuilder.setLength(stringBuilder.length() - 2);
        String str = a(stringBuilder);
        float f1 = (arrayOfInt1[1] + arrayOfInt1[0]) / 2.0F;
        float f2 = (m + i) / 2.0F;
        float f3 = paramInt;
        o o1 = new o(f1, f3);
        o o2 = new o(f2, f3);
        a a1 = a.d;
        return new m(str, null, new o[] { o1, o2 }, a1);
      } 
      i = m;
    } 
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\e\d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */