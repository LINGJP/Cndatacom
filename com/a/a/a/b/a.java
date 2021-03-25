package com.a.a.a.b;

import com.a.a.b.a.b;
import com.a.a.b.b;
import com.a.a.b.i;
import com.a.a.i;
import com.a.a.o;

public final class a {
  private final b a;
  
  private boolean b;
  
  private int c;
  
  private int d;
  
  private int e;
  
  private int f;
  
  public a(b paramb) {
    this.a = paramb;
  }
  
  private int a(a parama1, a parama2) {
    float f3 = b(parama1, parama2);
    float f4 = (parama2.a - parama1.a) / f3;
    float f5 = (parama2.b - parama1.b) / f3;
    float f2 = parama1.a;
    float f1 = parama1.b;
    boolean bool2 = this.a.a(parama1.a, parama1.b);
    boolean bool1 = false;
    int j = 0;
    int i = 0;
    while (i < f3) {
      f2 += f4;
      f1 += f5;
      int k = j;
      if (this.a.a(com.a.a.b.a.a.a(f2), com.a.a.b.a.a.a(f1)) != bool2)
        k = j + 1; 
      i++;
      j = k;
    } 
    f1 = j / f3;
    if (f1 > 0.1F && f1 < 0.9F)
      return 0; 
    if (f1 <= 0.1F)
      bool1 = true; 
    return (bool1 == bool2) ? 1 : -1;
  }
  
  private a a(a parama, boolean paramBoolean, int paramInt1, int paramInt2) {
    int j = parama.a + paramInt1;
    int i;
    for (i = parama.b + paramInt2; a(j, i) && this.a.a(j, i) == paramBoolean; i += paramInt2)
      j += paramInt1; 
    int k = j - paramInt1;
    j = i - paramInt2;
    for (i = k; a(i, j) && this.a.a(i, j) == paramBoolean; i += paramInt1);
    i -= paramInt1;
    for (paramInt1 = j; a(i, paramInt1) && this.a.a(i, paramInt1) == paramBoolean; paramInt1 += paramInt2);
    return new a(i, paramInt1 - paramInt2);
  }
  
  private b a(b paramb, o paramo1, o paramo2, o paramo3, o paramo4) {
    int i;
    if (this.b) {
      i = this.c * 4 + 11;
    } else if (this.c <= 4) {
      i = this.c * 4 + 15;
    } else {
      i = this.c * 4 + ((this.c - 4) / 8 + 1) * 2 + 15;
    } 
    i i1 = i.a();
    float f = i - 0.5F;
    return i1.a(paramb, i, i, 0.5F, 0.5F, f, 0.5F, f, f, 0.5F, f, paramo1.a(), paramo1.b(), paramo4.a(), paramo4.b(), paramo3.a(), paramo3.b(), paramo2.a(), paramo2.b());
  }
  
  private void a(a[] paramArrayOfa) {
    int i = this.e * 2;
    int j = 0;
    int k = 0;
    a a1 = paramArrayOfa[0];
    a a2 = paramArrayOfa[1];
    int m = i + 1;
    boolean[] arrayOfBoolean2 = a(a1, a2, m);
    boolean[] arrayOfBoolean3 = a(paramArrayOfa[1], paramArrayOfa[2], m);
    boolean[] arrayOfBoolean4 = a(paramArrayOfa[2], paramArrayOfa[3], m);
    boolean[] arrayOfBoolean1 = a(paramArrayOfa[3], paramArrayOfa[0], m);
    if (arrayOfBoolean2[0] && arrayOfBoolean2[i]) {
      this.f = 0;
    } else if (arrayOfBoolean3[0] && arrayOfBoolean3[i]) {
      this.f = 1;
    } else if (arrayOfBoolean4[0] && arrayOfBoolean4[i]) {
      this.f = 2;
    } else if (arrayOfBoolean1[0] && arrayOfBoolean1[i]) {
      this.f = 3;
    } else {
      throw i.a();
    } 
    if (this.b) {
      boolean[] arrayOfBoolean = new boolean[28];
      for (i = 0; i < 7; i++) {
        j = i + 2;
        arrayOfBoolean[i] = arrayOfBoolean2[j];
        arrayOfBoolean[i + 7] = arrayOfBoolean3[j];
        arrayOfBoolean[i + 14] = arrayOfBoolean4[j];
        arrayOfBoolean[i + 21] = arrayOfBoolean1[j];
      } 
      arrayOfBoolean2 = new boolean[28];
      i = k;
      while (true) {
        arrayOfBoolean1 = arrayOfBoolean2;
        if (i < 28) {
          arrayOfBoolean2[i] = arrayOfBoolean[(this.f * 7 + i) % 28];
          i++;
          continue;
        } 
        break;
      } 
    } else {
      boolean[] arrayOfBoolean = new boolean[40];
      for (i = 0; i < 11; i++) {
        if (i < 5) {
          k = i + 2;
          arrayOfBoolean[i] = arrayOfBoolean2[k];
          arrayOfBoolean[i + 10] = arrayOfBoolean3[k];
          arrayOfBoolean[i + 20] = arrayOfBoolean4[k];
          arrayOfBoolean[i + 30] = arrayOfBoolean1[k];
        } 
        if (i > 5) {
          k = i + 2;
          arrayOfBoolean[i - 1] = arrayOfBoolean2[k];
          arrayOfBoolean[i + 9] = arrayOfBoolean3[k];
          arrayOfBoolean[i + 19] = arrayOfBoolean4[k];
          arrayOfBoolean[i + 29] = arrayOfBoolean1[k];
        } 
      } 
      arrayOfBoolean2 = new boolean[40];
      i = j;
      while (true) {
        arrayOfBoolean1 = arrayOfBoolean2;
        if (i < 40) {
          arrayOfBoolean2[i] = arrayOfBoolean[(this.f * 10 + i) % 40];
          i++;
          continue;
        } 
        break;
      } 
    } 
    a(arrayOfBoolean1, this.b);
    a(arrayOfBoolean1);
  }
  
  private void a(boolean[] paramArrayOfboolean) {
    byte b1;
    byte b2;
    if (this.b) {
      b1 = 2;
      b2 = 6;
    } else {
      b1 = 5;
      b2 = 11;
    } 
    int i;
    for (i = 0; i < b1; i++) {
      this.c <<= 1;
      if (paramArrayOfboolean[i])
        this.c++; 
    } 
    for (i = b1; i < b1 + b2; i++) {
      this.d <<= 1;
      if (paramArrayOfboolean[i])
        this.d++; 
    } 
    this.c++;
    this.d++;
  }
  
  private static void a(boolean[] paramArrayOfboolean, boolean paramBoolean) {
    // Byte code:
    //   0: iload_1
    //   1: ifeq -> 12
    //   4: bipush #7
    //   6: istore_3
    //   7: iconst_2
    //   8: istore_2
    //   9: goto -> 17
    //   12: bipush #10
    //   14: istore_3
    //   15: iconst_4
    //   16: istore_2
    //   17: iload_3
    //   18: newarray int
    //   20: astore #7
    //   22: iconst_0
    //   23: istore #4
    //   25: iconst_1
    //   26: istore #5
    //   28: iload #4
    //   30: iload_3
    //   31: if_icmpge -> 94
    //   34: iconst_1
    //   35: istore #6
    //   37: iload #5
    //   39: iconst_4
    //   40: if_icmpgt -> 85
    //   43: aload_0
    //   44: iconst_4
    //   45: iload #4
    //   47: imul
    //   48: iconst_4
    //   49: iadd
    //   50: iload #5
    //   52: isub
    //   53: baload
    //   54: ifeq -> 70
    //   57: aload #7
    //   59: iload #4
    //   61: aload #7
    //   63: iload #4
    //   65: iaload
    //   66: iload #6
    //   68: iadd
    //   69: iastore
    //   70: iload #6
    //   72: iconst_1
    //   73: ishl
    //   74: istore #6
    //   76: iload #5
    //   78: iconst_1
    //   79: iadd
    //   80: istore #5
    //   82: goto -> 37
    //   85: iload #4
    //   87: iconst_1
    //   88: iadd
    //   89: istore #4
    //   91: goto -> 25
    //   94: new com/a/a/b/b/c
    //   97: dup
    //   98: getstatic com/a/a/b/b/a.d : Lcom/a/a/b/b/a;
    //   101: invokespecial <init> : (Lcom/a/a/b/b/a;)V
    //   104: aload #7
    //   106: iload_3
    //   107: iload_2
    //   108: isub
    //   109: invokevirtual a : ([II)V
    //   112: iconst_0
    //   113: istore_3
    //   114: iload_3
    //   115: iload_2
    //   116: if_icmpge -> 183
    //   119: iconst_1
    //   120: istore #4
    //   122: iconst_1
    //   123: istore #5
    //   125: iload #4
    //   127: iconst_4
    //   128: if_icmpgt -> 176
    //   131: aload #7
    //   133: iload_3
    //   134: iaload
    //   135: iload #5
    //   137: iand
    //   138: iload #5
    //   140: if_icmpne -> 148
    //   143: iconst_1
    //   144: istore_1
    //   145: goto -> 150
    //   148: iconst_0
    //   149: istore_1
    //   150: aload_0
    //   151: iload_3
    //   152: iconst_4
    //   153: imul
    //   154: iconst_4
    //   155: iadd
    //   156: iload #4
    //   158: isub
    //   159: iload_1
    //   160: bastore
    //   161: iload #5
    //   163: iconst_1
    //   164: ishl
    //   165: istore #5
    //   167: iload #4
    //   169: iconst_1
    //   170: iadd
    //   171: istore #4
    //   173: goto -> 125
    //   176: iload_3
    //   177: iconst_1
    //   178: iadd
    //   179: istore_3
    //   180: goto -> 114
    //   183: return
    //   184: invokestatic a : ()Lcom/a/a/i;
    //   187: athrow
    //   188: astore_0
    //   189: goto -> 184
    // Exception table:
    //   from	to	target	type
    //   94	112	188	com/a/a/b/b/d
  }
  
  private boolean a(int paramInt1, int paramInt2) {
    return (paramInt1 >= 0 && paramInt1 < this.a.d() && paramInt2 > 0 && paramInt2 < this.a.e());
  }
  
  private boolean a(a parama1, a parama2, a parama3, a parama4) {
    parama1 = new a(parama1.a - 3, parama1.b + 3);
    parama2 = new a(parama2.a - 3, parama2.b - 3);
    parama3 = new a(parama3.a + 3, parama3.b - 3);
    parama4 = new a(parama4.a + 3, parama4.b + 3);
    int i = a(parama4, parama1);
    boolean bool = false;
    if (i == 0)
      return false; 
    if (a(parama1, parama2) != i)
      return false; 
    if (a(parama2, parama3) != i)
      return false; 
    if (a(parama3, parama4) == i)
      bool = true; 
    return bool;
  }
  
  private a[] a(a parama) {
    this.e = 1;
    a a1 = parama;
    a a2 = a1;
    a a3 = a2;
    int i4 = 1;
    while (this.e < 9) {
      a a6 = a(parama, i4, 1, -1);
      a a5 = a(a1, i4, 1, 1);
      a a4 = a(a2, i4, -1, 1);
      a a7 = a(a3, i4, -1, -1);
      if (this.e > 2) {
        double d = (b(a7, a6) * this.e / b(a3, parama) * (this.e + 2));
        if (d < 0.75D || d > 1.25D || !a(a6, a5, a4, a7))
          break; 
      } 
      i4 ^= 0x1;
      this.e++;
      a3 = a7;
      parama = a6;
      a1 = a5;
      a2 = a4;
    } 
    if (this.e != 5 && this.e != 7)
      throw i.a(); 
    if (this.e == 5) {
      i4 = 1;
    } else {
      i4 = 0;
    } 
    this.b = i4;
    float f1 = 1.5F / (this.e * 2 - 3);
    int i = parama.a;
    int m = a2.a;
    int j = parama.b;
    int k = a2.b;
    float f3 = a2.a;
    float f2 = (i - m) * f1;
    i = com.a.a.b.a.a.a(f3 - f2);
    f3 = a2.b;
    float f4 = (j - k) * f1;
    j = com.a.a.b.a.a.a(f3 - f4);
    k = com.a.a.b.a.a.a(parama.a + f2);
    m = com.a.a.b.a.a.a(parama.b + f4);
    int n = a1.a;
    int i3 = a3.a;
    int i1 = a1.b;
    int i2 = a3.b;
    f3 = a3.a;
    f2 = (n - i3) * f1;
    n = com.a.a.b.a.a.a(f3 - f2);
    f3 = a3.b;
    f1 *= (i1 - i2);
    i1 = com.a.a.b.a.a.a(f3 - f1);
    i2 = com.a.a.b.a.a.a(a1.a + f2);
    i3 = com.a.a.b.a.a.a(a1.b + f1);
    if (!a(k, m) || !a(i2, i3) || !a(i, j) || !a(n, i1))
      throw i.a(); 
    return new a[] { new a(k, m), new a(i2, i3), new a(i, j), new a(n, i1) };
  }
  
  private boolean[] a(a parama1, a parama2, int paramInt) {
    boolean[] arrayOfBoolean = new boolean[paramInt];
    float f1 = b(parama1, parama2);
    float f2 = f1 / (paramInt - 1);
    float f3 = (parama2.a - parama1.a) * f2 / f1;
    float f4 = f2 * (parama2.b - parama1.b) / f1;
    f2 = parama1.a;
    f1 = parama1.b;
    int i;
    for (i = 0; i < paramInt; i++) {
      arrayOfBoolean[i] = this.a.a(com.a.a.b.a.a.a(f2), com.a.a.b.a.a.a(f1));
      f2 += f3;
      f1 += f4;
    } 
    return arrayOfBoolean;
  }
  
  private static float b(a parama1, a parama2) {
    return com.a.a.b.a.a.a(parama1.a, parama1.b, parama2.a, parama2.b);
  }
  
  private a b() {
    o o1;
    o o2;
    o o3;
    o o4;
    try {
      o[] arrayOfO = (new b(this.a)).a();
      o2 = arrayOfO[0];
      o3 = arrayOfO[1];
      o4 = arrayOfO[2];
      o1 = arrayOfO[3];
    } catch (i i2) {
      int m = this.a.d() / 2;
      int i1 = this.a.e() / 2;
      int n = m + 7;
      int k = i1 - 7;
      o1 = a(new a(n, k), false, 1, -1).a();
      i1 += 7;
      o3 = a(new a(n, i1), false, 1, 1).a();
      m -= 7;
      o4 = a(new a(m, i1), false, -1, 1).a();
      o o = a(new a(m, k), false, -1, -1).a();
      o2 = o1;
      o1 = o;
    } 
    int i = com.a.a.b.a.a.a((o2.a() + o1.a() + o3.a() + o4.a()) / 4.0F);
    int j = com.a.a.b.a.a.a((o2.b() + o1.b() + o3.b() + o4.b()) / 4.0F);
    try {
      o[] arrayOfO = (new b(this.a, 15, i, j)).a();
      o3 = arrayOfO[0];
      o4 = arrayOfO[1];
      o2 = arrayOfO[2];
      o o = arrayOfO[3];
    } catch (i i1) {
      int m = i + 7;
      int k = j - 7;
      o3 = a(new a(m, k), false, 1, -1).a();
      j += 7;
      o4 = a(new a(m, j), false, 1, 1).a();
      i -= 7;
      o2 = a(new a(i, j), false, -1, 1).a();
      o o = a(new a(i, k), false, -1, -1).a();
    } 
  }
  
  private o[] b(a[] paramArrayOfa) {
    int j = this.c;
    if (this.c > 4) {
      i = 1;
    } else {
      i = 0;
    } 
    float f1 = (j * 2 + i + (this.c - 4) / 8) / this.e * 2.0F;
    int m = (paramArrayOfa[0]).a - (paramArrayOfa[2]).a;
    int k = -1;
    if (m > 0) {
      i = 1;
    } else {
      i = -1;
    } 
    int n = (paramArrayOfa[0]).b - (paramArrayOfa[2]).b;
    if (n > 0) {
      j = 1;
    } else {
      j = -1;
    } 
    float f3 = (paramArrayOfa[2]).a;
    float f2 = (m + i) * f1;
    m = com.a.a.b.a.a.a(f3 - f2);
    f3 = (paramArrayOfa[2]).b;
    float f4 = (n + j) * f1;
    n = com.a.a.b.a.a.a(f3 - f4);
    int i1 = com.a.a.b.a.a.a((paramArrayOfa[0]).a + f2);
    int i2 = com.a.a.b.a.a.a((paramArrayOfa[0]).b + f4);
    int i3 = (paramArrayOfa[1]).a - (paramArrayOfa[3]).a;
    if (i3 > 0) {
      i = 1;
    } else {
      i = -1;
    } 
    int i4 = (paramArrayOfa[1]).b - (paramArrayOfa[3]).b;
    j = k;
    if (i4 > 0)
      j = 1; 
    f3 = (paramArrayOfa[3]).a;
    f2 = (i3 + i) * f1;
    int i = com.a.a.b.a.a.a(f3 - f2);
    f3 = (paramArrayOfa[3]).b;
    f1 *= (i4 + j);
    j = com.a.a.b.a.a.a(f3 - f1);
    k = com.a.a.b.a.a.a((paramArrayOfa[1]).a + f2);
    i3 = com.a.a.b.a.a.a((paramArrayOfa[1]).b + f1);
    if (!a(i1, i2) || !a(k, i3) || !a(m, n) || !a(i, j))
      throw i.a(); 
    return new o[] { new o(i1, i2), new o(k, i3), new o(m, n), new o(i, j) };
  }
  
  public com.a.a.a.a a() {
    a[] arrayOfA = a(b());
    a(arrayOfA);
    o[] arrayOfO = b(arrayOfA);
    return new com.a.a.a.a(a(this.a, arrayOfO[this.f % 4], arrayOfO[(this.f + 3) % 4], arrayOfO[(this.f + 2) % 4], arrayOfO[(this.f + 1) % 4]), arrayOfO, this.b, this.d, this.c);
  }
  
  static final class a {
    final int a;
    
    final int b;
    
    a(int param1Int1, int param1Int2) {
      this.a = param1Int1;
      this.b = param1Int2;
    }
    
    o a() {
      return new o(this.a, this.b);
    }
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\a\b\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */