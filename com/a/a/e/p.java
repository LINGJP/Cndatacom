package com.a.a.e;

import com.a.a.a;
import com.a.a.b.a;
import com.a.a.d;
import com.a.a.e;
import com.a.a.f;
import com.a.a.i;
import com.a.a.l;
import com.a.a.m;
import com.a.a.n;
import com.a.a.o;
import java.util.Arrays;
import java.util.Map;

public abstract class p extends k {
  static final int[] b = new int[] { 1, 1, 1 };
  
  static final int[] c = new int[] { 1, 1, 1, 1, 1 };
  
  static final int[][] d;
  
  static final int[][] e = new int[20][];
  
  private final StringBuilder a = new StringBuilder(20);
  
  private final o f = new o();
  
  private final g g = new g();
  
  static {
    System.arraycopy(d, 0, e, 0, 10);
    while (i < 20) {
      arrayOfInt1 = d[i - 10];
      arrayOfInt2 = new int[arrayOfInt1.length];
      for (int j = 0; j < arrayOfInt1.length; j++)
        arrayOfInt2[j] = arrayOfInt1[arrayOfInt1.length - j - 1]; 
      e[i] = arrayOfInt2;
      i++;
    } 
  }
  
  static int a(a parama, int[] paramArrayOfint, int paramInt, int[][] paramArrayOfint1) {
    a(parama, paramInt, paramArrayOfint);
    int m = paramArrayOfint1.length;
    int i = 122;
    int j = -1;
    paramInt = 0;
    while (paramInt < m) {
      int i1 = a(paramArrayOfint, paramArrayOfint1[paramInt], 179);
      int n = i;
      if (i1 < i) {
        j = paramInt;
        n = i1;
      } 
      paramInt++;
      i = n;
    } 
    if (j >= 0)
      return j; 
    throw i.a();
  }
  
  static boolean a(CharSequence paramCharSequence) {
    int m = paramCharSequence.length();
    boolean bool = false;
    if (m == 0)
      return false; 
    int i = m - 2;
    int j = 0;
    while (i >= 0) {
      int n = paramCharSequence.charAt(i) - 48;
      if (n < 0 || n > 9)
        throw f.a(); 
      j += n;
      i -= 2;
    } 
    j *= 3;
    for (i = m - 1; i >= 0; i -= 2) {
      m = paramCharSequence.charAt(i) - 48;
      if (m < 0 || m > 9)
        throw f.a(); 
      j += m;
    } 
    if (j % 10 == 0)
      bool = true; 
    return bool;
  }
  
  static int[] a(a parama) {
    int[] arrayOfInt2 = new int[b.length];
    int[] arrayOfInt1 = null;
    boolean bool = false;
    int i = 0;
    while (!bool) {
      Arrays.fill(arrayOfInt2, 0, b.length, 0);
      arrayOfInt1 = a(parama, i, false, b, arrayOfInt2);
      int j = arrayOfInt1[0];
      i = arrayOfInt1[1];
      int m = j - i - j;
      if (m >= 0)
        bool = parama.a(m, j, false); 
    } 
    return arrayOfInt1;
  }
  
  static int[] a(a parama, int paramInt, boolean paramBoolean, int[] paramArrayOfint) {
    return a(parama, paramInt, paramBoolean, paramArrayOfint, new int[paramArrayOfint.length]);
  }
  
  private static int[] a(a parama, int paramInt, boolean paramBoolean, int[] paramArrayOfint1, int[] paramArrayOfint2) {
    int n = paramArrayOfint1.length;
    int i1 = parama.a();
    if (paramBoolean) {
      paramInt = parama.d(paramInt);
    } else {
      paramInt = parama.c(paramInt);
    } 
    int i = paramInt;
    int m = 0;
    int j = paramInt;
    paramInt = i;
    i = m;
    while (j < i1) {
      boolean bool1 = parama.a(j);
      boolean bool = true;
      if (bool1 ^ paramBoolean) {
        paramArrayOfint2[i] = paramArrayOfint2[i] + 1;
        m = paramInt;
      } else {
        int i2 = n - 1;
        if (i == i2) {
          if (a(paramArrayOfint2, paramArrayOfint1, 179) < 122)
            return new int[] { paramInt, j }; 
          m = paramInt + paramArrayOfint2[0] + paramArrayOfint2[1];
          paramInt = n - 2;
          System.arraycopy(paramArrayOfint2, 2, paramArrayOfint2, 0, paramInt);
          paramArrayOfint2[paramInt] = 0;
          paramArrayOfint2[i2] = 0;
          paramInt = i - 1;
          i = m;
        } else {
          m = i + 1;
          i = paramInt;
          paramInt = m;
        } 
        paramArrayOfint2[paramInt] = 1;
        if (!paramBoolean) {
          paramBoolean = bool;
        } else {
          paramBoolean = false;
        } 
        m = i;
        i = paramInt;
      } 
      j++;
      paramInt = m;
    } 
    throw i.a();
  }
  
  protected abstract int a(a parama, int[] paramArrayOfint, StringBuilder paramStringBuilder);
  
  public m a(int paramInt, a parama, Map<e, ?> paramMap) {
    return a(paramInt, parama, a(parama), paramMap);
  }
  
  public m a(int paramInt, a parama, int[] paramArrayOfint, Map<e, ?> paramMap) {
    com.a.a.p p1;
    if (paramMap == null) {
      paramMap = null;
    } else {
      p1 = (com.a.a.p)paramMap.get(e.i);
    } 
    if (p1 != null)
      p1.a(new o((paramArrayOfint[0] + paramArrayOfint[1]) / 2.0F, paramInt)); 
    StringBuilder stringBuilder = this.a;
    stringBuilder.setLength(0);
    int i = a(parama, paramArrayOfint, stringBuilder);
    if (p1 != null)
      p1.a(new o(i, paramInt)); 
    int[] arrayOfInt = a(parama, i);
    if (p1 != null)
      p1.a(new o((arrayOfInt[0] + arrayOfInt[1]) / 2.0F, paramInt)); 
    i = arrayOfInt[1];
    int j = i - arrayOfInt[0] + i;
    if (j >= parama.a() || !parama.a(i, j, false))
      throw i.a(); 
    String str = stringBuilder.toString();
    if (!a(str))
      throw d.a(); 
    float f1 = (paramArrayOfint[1] + paramArrayOfint[0]) / 2.0F;
    float f2 = (arrayOfInt[1] + arrayOfInt[0]) / 2.0F;
    a a1 = b();
    float f3 = paramInt;
    m m = new m(str, null, new o[] { new o(f1, f3), new o(f2, f3) }a1);
    try {
      m m1 = this.f.a(paramInt, parama, arrayOfInt[1]);
      m.a(n.h, m1.a());
      m.a(m1.e());
      m.a(m1.c());
    } catch (l l) {}
    if (a1 == a.h || a1 == a.o) {
      String str1 = this.g.a(str);
      if (str1 != null)
        m.a(n.g, str1); 
    } 
    return m;
  }
  
  boolean a(String paramString) {
    return a(paramString);
  }
  
  int[] a(a parama, int paramInt) {
    return a(parama, paramInt, false, b);
  }
  
  abstract a b();
  
  static {
    int i = 10;
    int[] arrayOfInt1 = { 2, 2, 2, 1 };
    int[] arrayOfInt2 = { 1, 4, 1, 1 };
    int[] arrayOfInt3 = { 1, 1, 3, 2 };
    int[] arrayOfInt4 = { 1, 2, 3, 1 };
    int[] arrayOfInt5 = { 1, 1, 1, 4 };
    int[] arrayOfInt6 = { 1, 3, 1, 2 };
    int[] arrayOfInt7 = { 3, 1, 1, 2 };
    d = new int[][] { { 3, 2, 1, 1 }, arrayOfInt1, { 2, 1, 2, 2 }, arrayOfInt2, arrayOfInt3, arrayOfInt4, arrayOfInt5, arrayOfInt6, { 1, 2, 1, 3 }, arrayOfInt7 };
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\e\p.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */