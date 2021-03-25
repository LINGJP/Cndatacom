package com.a.a.c.a;

import com.a.a.b.b;
import com.a.a.f;

final class a {
  private final b a;
  
  private final b b;
  
  private final e c;
  
  a(b paramb) {
    int i = paramb.e();
    if (i < 8 || i > 144 || (i & 0x1) != 0)
      throw f.a(); 
    this.c = b(paramb);
    this.a = a(paramb);
    this.b = new b(this.a.d(), this.a.e());
  }
  
  private static e b(b paramb) {
    return e.a(paramb.e(), paramb.d());
  }
  
  int a(int paramInt1, int paramInt2) {
    int k = paramInt1 - 1;
    if (a(k, 0, paramInt1, paramInt2)) {
      i = 1;
    } else {
      i = 0;
    } 
    int j = i << 1;
    int i = j;
    if (a(k, 1, paramInt1, paramInt2))
      i = j | 0x1; 
    j = i << 1;
    i = j;
    if (a(k, 2, paramInt1, paramInt2))
      i = j | 0x1; 
    j = i << 1;
    i = j;
    if (a(0, paramInt2 - 2, paramInt1, paramInt2))
      i = j | 0x1; 
    j = i << 1;
    k = paramInt2 - 1;
    i = j;
    if (a(0, k, paramInt1, paramInt2))
      i = j | 0x1; 
    j = i << 1;
    i = j;
    if (a(1, k, paramInt1, paramInt2))
      i = j | 0x1; 
    j = i << 1;
    i = j;
    if (a(2, k, paramInt1, paramInt2))
      i = j | 0x1; 
    j = i << 1;
    i = j;
    if (a(3, k, paramInt1, paramInt2))
      i = j | 0x1; 
    return i;
  }
  
  b a(b paramb) {
    int j = this.c.b();
    int i = this.c.c();
    if (paramb.e() != j)
      throw new IllegalArgumentException("Dimension of bitMarix must match the version size"); 
    int k = this.c.d();
    int m = this.c.e();
    int n = j / k;
    int i1 = i / m;
    b b1 = new b(i1 * m, n * k);
    for (i = 0; i < n; i++) {
      for (j = 0; j < i1; j++) {
        int i2;
        for (i2 = 0; i2 < k; i2++) {
          int i3;
          for (i3 = 0; i3 < m; i3++) {
            if (paramb.a((m + 2) * j + 1 + i3, (k + 2) * i + 1 + i2))
              b1.b(j * m + i3, i * k + i2); 
          } 
        } 
      } 
    } 
    return b1;
  }
  
  e a() {
    return this.c;
  }
  
  boolean a(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    int i = paramInt1;
    int j = paramInt2;
    if (paramInt1 < 0) {
      i = paramInt1 + paramInt3;
      j = paramInt2 + 4 - (paramInt3 + 4 & 0x7);
    } 
    paramInt2 = i;
    paramInt1 = j;
    if (j < 0) {
      paramInt1 = j + paramInt4;
      paramInt2 = i + 4 - (paramInt4 + 4 & 0x7);
    } 
    this.b.b(paramInt1, paramInt2);
    return this.a.a(paramInt1, paramInt2);
  }
  
  int b(int paramInt1, int paramInt2) {
    if (a(paramInt1 - 3, 0, paramInt1, paramInt2)) {
      i = 1;
    } else {
      i = 0;
    } 
    int j = i << 1;
    int i = j;
    if (a(paramInt1 - 2, 0, paramInt1, paramInt2))
      i = j | 0x1; 
    j = i << 1;
    i = j;
    if (a(paramInt1 - 1, 0, paramInt1, paramInt2))
      i = j | 0x1; 
    j = i << 1;
    i = j;
    if (a(0, paramInt2 - 4, paramInt1, paramInt2))
      i = j | 0x1; 
    j = i << 1;
    i = j;
    if (a(0, paramInt2 - 3, paramInt1, paramInt2))
      i = j | 0x1; 
    j = i << 1;
    i = j;
    if (a(0, paramInt2 - 2, paramInt1, paramInt2))
      i = j | 0x1; 
    j = i << 1;
    int k = paramInt2 - 1;
    i = j;
    if (a(0, k, paramInt1, paramInt2))
      i = j | 0x1; 
    j = i << 1;
    i = j;
    if (a(1, k, paramInt1, paramInt2))
      i = j | 0x1; 
    return i;
  }
  
  int b(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    int n = paramInt1 - 2;
    int k = paramInt2 - 2;
    if (a(n, k, paramInt3, paramInt4)) {
      i = 1;
    } else {
      i = 0;
    } 
    int j = i << 1;
    int m = paramInt2 - 1;
    int i = j;
    if (a(n, m, paramInt3, paramInt4))
      i = j | 0x1; 
    j = i << 1;
    n = paramInt1 - 1;
    i = j;
    if (a(n, k, paramInt3, paramInt4))
      i = j | 0x1; 
    j = i << 1;
    i = j;
    if (a(n, m, paramInt3, paramInt4))
      i = j | 0x1; 
    j = i << 1;
    i = j;
    if (a(n, paramInt2, paramInt3, paramInt4))
      i = j | 0x1; 
    j = i << 1;
    i = j;
    if (a(paramInt1, k, paramInt3, paramInt4))
      i = j | 0x1; 
    j = i << 1;
    i = j;
    if (a(paramInt1, m, paramInt3, paramInt4))
      i = j | 0x1; 
    j = i << 1;
    i = j;
    if (a(paramInt1, paramInt2, paramInt3, paramInt4))
      i = j | 0x1; 
    return i;
  }
  
  byte[] b() {
    Object object1;
    Object object2;
    Object object3;
    Object object4;
    Object object5;
    Object object6;
    Object object7;
    byte[] arrayOfByte = new byte[this.c.f()];
    int i = this.a.e();
    int j = this.a.d();
    byte b1 = 4;
    boolean bool2 = false;
    boolean bool6 = false;
    boolean bool1 = false;
    boolean bool5 = false;
    boolean bool4 = false;
    boolean bool3 = false;
    while (true) {
      int i2;
      int i3;
      if (object3 == i && object2 == null && object7 == null) {
        arrayOfByte[object1] = (byte)a(i, j);
        int i4 = object3 - 2;
        m = object2 + 2;
        n = object1 + 1;
        boolean bool = true;
        Object object14 = object6;
        Object object15 = object5;
        Object object16 = object4;
        continue;
      } 
      int k = i - 2;
      if (object3 == k && object2 == null && (j & 0x3) != 0 && object6 == null) {
        arrayOfByte[object1] = (byte)b(i, j);
        k = object3 - 2;
        m = object2 + 2;
        n = object1 + 1;
        boolean bool = true;
        Object object14 = object7;
        Object object15 = object5;
        Object object16 = object4;
        continue;
      } 
      if (object3 == i + 4 && object2 == 2 && (j & 0x7) == 0 && object5 == null) {
        arrayOfByte[object1] = (byte)c(i, j);
        k = object3 - 2;
        m = object2 + 2;
        n = object1 + 1;
        boolean bool = true;
        Object object14 = object7;
        Object object15 = object6;
        Object object16 = object4;
        continue;
      } 
      Object object8 = object3;
      Object object9 = object2;
      Object object10 = object1;
      if (object3 == k) {
        object8 = object3;
        object9 = object2;
        object10 = object1;
        if (object2 == null) {
          object8 = object3;
          object9 = object2;
          object10 = object1;
          if ((j & 0x7) == 4) {
            object8 = object3;
            object9 = object2;
            object10 = object1;
            if (object4 == null) {
              arrayOfByte[object1] = (byte)d(i, j);
              k = object3 - 2;
              m = object2 + 2;
              n = object1 + 1;
              boolean bool = true;
              object10 = object7;
              Object object14 = object6;
              Object object15 = object5;
              continue;
            } 
          } 
        } 
      } 
      while (true) {
        Object object = object10;
        if (m < i) {
          object = object10;
          if (n >= 0) {
            object = object10;
            if (!this.b.a(n, m)) {
              arrayOfByte[object10] = (byte)b(m, n, i, j);
              k = object10 + 1;
            } 
          } 
        } 
        i2 = m - 2;
        i1 = n + 2;
        if (i2 >= 0) {
          m = i2;
          n = i1;
          int i4 = k;
          if (i1 >= j)
            break; 
          continue;
        } 
        break;
      } 
      int n = i2 + 1;
      i1 += 3;
      int m = k;
      k = i1;
      int i1 = n;
      while (true) {
        n = m;
        if (i1 >= 0) {
          n = m;
          if (k < j) {
            n = m;
            if (!this.b.a(k, i1)) {
              arrayOfByte[m] = (byte)b(i1, k, i, j);
              n = m + 1;
            } 
          } 
        } 
        i3 = i1 + 2;
        i2 = k - 2;
        if (i3 < i) {
          i1 = i3;
          k = i2;
          m = n;
          if (i2 < 0)
            break; 
          continue;
        } 
        break;
      } 
      k = i3 + 3;
      m = i2 + 1;
      Object object13 = object4;
      Object object12 = object5;
      Object object11 = object6;
      object10 = object7;
      continue;
      object3 = SYNTHETIC_LOCAL_VARIABLE_1;
      object2 = SYNTHETIC_LOCAL_VARIABLE_2;
      object7 = SYNTHETIC_LOCAL_VARIABLE_11;
      object1 = SYNTHETIC_LOCAL_VARIABLE_3;
      object6 = SYNTHETIC_LOCAL_VARIABLE_12;
      object5 = SYNTHETIC_LOCAL_VARIABLE_13;
      object4 = SYNTHETIC_LOCAL_VARIABLE_14;
      if (SYNTHETIC_LOCAL_VARIABLE_1 >= i) {
        object3 = SYNTHETIC_LOCAL_VARIABLE_1;
        object2 = SYNTHETIC_LOCAL_VARIABLE_2;
        object7 = SYNTHETIC_LOCAL_VARIABLE_11;
        object1 = SYNTHETIC_LOCAL_VARIABLE_3;
        object6 = SYNTHETIC_LOCAL_VARIABLE_12;
        object5 = SYNTHETIC_LOCAL_VARIABLE_13;
        object4 = SYNTHETIC_LOCAL_VARIABLE_14;
        if (SYNTHETIC_LOCAL_VARIABLE_2 >= j) {
          if (SYNTHETIC_LOCAL_VARIABLE_3 != this.c.f())
            throw f.a(); 
          return arrayOfByte;
        } 
      } 
    } 
  }
  
  int c(int paramInt1, int paramInt2) {
    int m = paramInt1 - 1;
    if (a(m, 0, paramInt1, paramInt2)) {
      i = 1;
    } else {
      i = 0;
    } 
    int j = i << 1;
    int k = paramInt2 - 1;
    int i = j;
    if (a(m, k, paramInt1, paramInt2))
      i = j | 0x1; 
    j = i << 1;
    m = paramInt2 - 3;
    i = j;
    if (a(0, m, paramInt1, paramInt2))
      i = j | 0x1; 
    j = i << 1;
    int n = paramInt2 - 2;
    i = j;
    if (a(0, n, paramInt1, paramInt2))
      i = j | 0x1; 
    j = i << 1;
    i = j;
    if (a(0, k, paramInt1, paramInt2))
      i = j | 0x1; 
    j = i << 1;
    i = j;
    if (a(1, m, paramInt1, paramInt2))
      i = j | 0x1; 
    j = i << 1;
    i = j;
    if (a(1, n, paramInt1, paramInt2))
      i = j | 0x1; 
    j = i << 1;
    i = j;
    if (a(1, k, paramInt1, paramInt2))
      i = j | 0x1; 
    return i;
  }
  
  int d(int paramInt1, int paramInt2) {
    if (a(paramInt1 - 3, 0, paramInt1, paramInt2)) {
      i = 1;
    } else {
      i = 0;
    } 
    int j = i << 1;
    int i = j;
    if (a(paramInt1 - 2, 0, paramInt1, paramInt2))
      i = j | 0x1; 
    j = i << 1;
    i = j;
    if (a(paramInt1 - 1, 0, paramInt1, paramInt2))
      i = j | 0x1; 
    j = i << 1;
    i = j;
    if (a(0, paramInt2 - 2, paramInt1, paramInt2))
      i = j | 0x1; 
    j = i << 1;
    int k = paramInt2 - 1;
    i = j;
    if (a(0, k, paramInt1, paramInt2))
      i = j | 0x1; 
    j = i << 1;
    i = j;
    if (a(1, k, paramInt1, paramInt2))
      i = j | 0x1; 
    j = i << 1;
    i = j;
    if (a(2, k, paramInt1, paramInt2))
      i = j | 0x1; 
    j = i << 1;
    i = j;
    if (a(3, k, paramInt1, paramInt2))
      i = j | 0x1; 
    return i;
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\c\a\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */