package com.a.a.e;

import com.a.a.a;
import com.a.a.b.a;
import com.a.a.e;
import com.a.a.f;
import com.a.a.i;
import com.a.a.m;
import com.a.a.o;
import java.util.Map;

public final class h extends k {
  static final int[][] a;
  
  private static final int[] b = new int[] { 44, 24, 20, 18, 16, 14, 12, 10, 8, 6 };
  
  private static final int[] d = new int[] { 1, 1, 1, 1 };
  
  private static final int[] e = new int[] { 1, 1, 3 };
  
  private int c = -1;
  
  static {
    a = new int[][] { { 1, 1, 3, 3, 1 }, { 3, 1, 1, 1, 3 }, { 1, 3, 1, 1, 3 }, { 3, 3, 1, 1, 1 }, { 1, 1, 3, 1, 3 }, { 3, 1, 3, 1, 1 }, { 1, 3, 3, 1, 1 }, { 1, 1, 1, 3, 3 }, { 3, 1, 1, 3, 1 }, { 1, 3, 1, 3, 1 } };
  }
  
  private static int a(int[] paramArrayOfint) {
    int n = a.length;
    int j = 107;
    int m = -1;
    int i = 0;
    while (i < n) {
      int i2 = a(paramArrayOfint, a[i], 204);
      int i1 = j;
      if (i2 < j) {
        m = i;
        i1 = i2;
      } 
      i++;
      j = i1;
    } 
    if (m >= 0)
      return m; 
    throw i.a();
  }
  
  private void a(a parama, int paramInt) {
    int i = this.c * 10;
    while (i > 0 && --paramInt >= 0 && !parama.a(paramInt)) {
      i--;
      paramInt--;
    } 
    if (i != 0)
      throw i.a(); 
  }
  
  private static void a(a parama, int paramInt1, int paramInt2, StringBuilder paramStringBuilder) {
    int[] arrayOfInt1 = new int[10];
    int[] arrayOfInt2 = new int[5];
    int[] arrayOfInt3 = new int[5];
    label16: while (paramInt1 < paramInt2) {
      a(parama, paramInt1, arrayOfInt1);
      boolean bool = false;
      int i;
      for (i = 0; i < 5; i++) {
        int n = i << 1;
        arrayOfInt2[i] = arrayOfInt1[n];
        arrayOfInt3[i] = arrayOfInt1[n + 1];
      } 
      paramStringBuilder.append((char)(a(arrayOfInt2) + 48));
      paramStringBuilder.append((char)(a(arrayOfInt3) + 48));
      int m = arrayOfInt1.length;
      int j = paramInt1;
      i = bool;
      while (true) {
        paramInt1 = j;
        if (i < m) {
          j += arrayOfInt1[i];
          i++;
          continue;
        } 
        continue label16;
      } 
    } 
  }
  
  private static int c(a parama) {
    int i = parama.a();
    int j = parama.c(0);
    if (j == i)
      throw i.a(); 
    return j;
  }
  
  private static int[] c(a parama, int paramInt, int[] paramArrayOfint) {
    int n = paramArrayOfint.length;
    int[] arrayOfInt = new int[n];
    int i1 = parama.a();
    int i = paramInt;
    boolean bool = false;
    int m = 0;
    int j = paramInt;
    paramInt = i;
    i = m;
    while (j < i1) {
      if (parama.a(j) ^ bool) {
        arrayOfInt[i] = arrayOfInt[i] + 1;
        m = paramInt;
      } else {
        int i3 = n - 1;
        if (i == i3) {
          if (a(arrayOfInt, paramArrayOfint, 204) < 107)
            return new int[] { paramInt, j }; 
          m = paramInt + arrayOfInt[0] + arrayOfInt[1];
          paramInt = n - 2;
          System.arraycopy(arrayOfInt, 2, arrayOfInt, 0, paramInt);
          arrayOfInt[paramInt] = 0;
          arrayOfInt[i3] = 0;
          paramInt = i - 1;
          i = m;
        } else {
          m = i + 1;
          i = paramInt;
          paramInt = m;
        } 
        arrayOfInt[paramInt] = 1;
        int i2 = bool ^ true;
        m = i;
        i = paramInt;
      } 
      j++;
      paramInt = m;
    } 
    throw i.a();
  }
  
  public m a(int paramInt, a parama, Map<e, ?> paramMap) {
    int[] arrayOfInt1;
    int[] arrayOfInt3 = a(parama);
    int[] arrayOfInt2 = b(parama);
    StringBuilder stringBuilder = new StringBuilder(20);
    a(parama, arrayOfInt3[1], arrayOfInt2[0], stringBuilder);
    String str = stringBuilder.toString();
    if (paramMap != null) {
      int[] arrayOfInt = (int[])paramMap.get(e.f);
    } else {
      parama = null;
    } 
    a a1 = parama;
    if (parama == null)
      arrayOfInt1 = b; 
    int j = str.length();
    int m = arrayOfInt1.length;
    int i = 0;
    while (true) {
      if (i < m) {
        if (j == arrayOfInt1[i]) {
          i = 1;
          break;
        } 
        i++;
        continue;
      } 
      i = 0;
      break;
    } 
    if (i == 0)
      throw f.a(); 
    float f1 = arrayOfInt3[1];
    float f2 = paramInt;
    o o1 = new o(f1, f2);
    o o2 = new o(arrayOfInt2[0], f2);
    a a2 = a.i;
    return new m(str, null, new o[] { o1, o2 }, a2);
  }
  
  int[] a(a parama) {
    int[] arrayOfInt = c(parama, c(parama), d);
    this.c = arrayOfInt[1] - arrayOfInt[0] >> 2;
    a(parama, arrayOfInt[0]);
    return arrayOfInt;
  }
  
  int[] b(a parama) {
    parama.c();
    try {
      int[] arrayOfInt = c(parama, c(parama), e);
      a(parama, arrayOfInt[0]);
      int i = arrayOfInt[0];
      arrayOfInt[0] = parama.a() - arrayOfInt[1];
      arrayOfInt[1] = parama.a() - i;
      return arrayOfInt;
    } finally {
      parama.c();
    } 
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\e\h.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */