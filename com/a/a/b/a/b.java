package com.a.a.b.a;

import com.a.a.i;
import com.a.a.o;

public final class b {
  private final com.a.a.b.b a;
  
  private final int b;
  
  private final int c;
  
  private final int d;
  
  private final int e;
  
  private final int f;
  
  private final int g;
  
  public b(com.a.a.b.b paramb) {
    this.a = paramb;
    this.b = paramb.e();
    this.c = paramb.d();
    this.d = this.c - 30 >> 1;
    this.e = this.c + 30 >> 1;
    this.g = this.b - 30 >> 1;
    this.f = this.b + 30 >> 1;
    if (this.g < 0 || this.d < 0 || this.f >= this.b || this.e >= this.c)
      throw i.a(); 
  }
  
  public b(com.a.a.b.b paramb, int paramInt1, int paramInt2, int paramInt3) {
    this.a = paramb;
    this.b = paramb.e();
    this.c = paramb.d();
    paramInt1 >>= 1;
    this.d = paramInt2 - paramInt1;
    this.e = paramInt2 + paramInt1;
    this.g = paramInt3 - paramInt1;
    this.f = paramInt3 + paramInt1;
    if (this.g < 0 || this.d < 0 || this.f >= this.b || this.e >= this.c)
      throw i.a(); 
  }
  
  private o a(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    int j = a.a(a.a(paramFloat1, paramFloat2, paramFloat3, paramFloat4));
    float f = j;
    paramFloat3 = (paramFloat3 - paramFloat1) / f;
    paramFloat4 = (paramFloat4 - paramFloat2) / f;
    int i;
    for (i = 0; i < j; i++) {
      f = i;
      int k = a.a(f * paramFloat3 + paramFloat1);
      int m = a.a(f * paramFloat4 + paramFloat2);
      if (this.a.a(k, m))
        return new o(k, m); 
    } 
    return null;
  }
  
  private boolean a(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean) {
    int i = paramInt1;
    if (paramBoolean) {
      while (paramInt1 <= paramInt2) {
        if (this.a.a(paramInt1, paramInt3))
          return true; 
        paramInt1++;
      } 
    } else {
      while (i <= paramInt2) {
        if (this.a.a(paramInt3, i))
          return true; 
        i++;
      } 
    } 
    return false;
  }
  
  private o[] a(o paramo1, o paramo2, o paramo3, o paramo4) {
    float f1 = paramo1.a();
    float f2 = paramo1.b();
    float f3 = paramo2.a();
    float f4 = paramo2.b();
    float f5 = paramo3.a();
    float f6 = paramo3.b();
    float f7 = paramo4.a();
    float f8 = paramo4.b();
    return (f1 < this.c / 2.0F) ? new o[] { new o(f7 - 1.0F, f8 + 1.0F), new o(f3 + 1.0F, f4 + 1.0F), new o(f5 - 1.0F, f6 - 1.0F), new o(f1 + 1.0F, f2 - 1.0F) } : new o[] { new o(f7 + 1.0F, f8 + 1.0F), new o(f3 + 1.0F, f4 - 1.0F), new o(f5 - 1.0F, f6 + 1.0F), new o(f1 - 1.0F, f2 - 1.0F) };
  }
  
  public o[] a() {
    int n;
    int i1;
    int i2;
    int i3;
    boolean bool2;
    int j = this.d;
    int m = this.e;
    int i = this.g;
    int k = this.f;
    boolean bool4 = false;
    boolean bool3 = true;
    int i4 = 1;
    boolean bool1 = false;
    while (true) {
      n = m;
      i2 = i;
      i1 = k;
      bool2 = bool4;
      i3 = j;
      if (i4) {
        boolean bool = true;
        n = 0;
        while (bool && m < this.c) {
          boolean bool5 = a(i, k, m, false);
          bool = bool5;
          if (bool5) {
            m++;
            n = 1;
            bool = bool5;
          } 
        } 
        if (m < this.c) {
          bool = true;
          while (bool && k < this.b) {
            boolean bool5 = a(j, m, k, true);
            bool = bool5;
            if (bool5) {
              k++;
              n = 1;
              bool = bool5;
            } 
          } 
          if (k < this.b) {
            bool = true;
            while (bool && j >= 0) {
              boolean bool5 = a(i, k, j, false);
              bool = bool5;
              if (bool5) {
                j--;
                n = 1;
                bool = bool5;
              } 
            } 
            if (j >= 0) {
              bool = true;
              while (bool && i >= 0) {
                boolean bool5 = a(j, m, i, true);
                bool = bool5;
                if (bool5) {
                  i--;
                  n = 1;
                  bool = bool5;
                } 
              } 
              if (i >= 0) {
                if (n != 0)
                  bool1 = true; 
                i4 = n;
                continue;
              } 
            } 
          } 
        } 
        bool2 = true;
        n = m;
        i2 = i;
        i1 = k;
        i3 = j;
      } 
      break;
    } 
    if (!bool2 && bool1) {
      o o2;
      o o3;
      o o4;
      j = n - i3;
      o o5 = null;
      o o1 = null;
      i = 1;
      while (true) {
        o2 = o1;
        if (i < j) {
          o1 = a(i3, (i1 - i), (i3 + i), i1);
          if (o1 != null) {
            o2 = o1;
            break;
          } 
          i++;
          continue;
        } 
        break;
      } 
      if (o2 == null)
        throw i.a(); 
      o1 = null;
      i = 1;
      while (true) {
        o3 = o1;
        if (i < j) {
          o1 = a(i3, (i2 + i), (i3 + i), i2);
          if (o1 != null) {
            o3 = o1;
            break;
          } 
          i++;
          continue;
        } 
        break;
      } 
      if (o3 == null)
        throw i.a(); 
      o1 = null;
      i = 1;
      while (true) {
        o4 = o1;
        if (i < j) {
          o1 = a(n, (i2 + i), (n - i), i2);
          if (o1 != null) {
            o4 = o1;
            break;
          } 
          i++;
          continue;
        } 
        break;
      } 
      o1 = o5;
      i = bool3;
      if (o4 == null)
        throw i.a(); 
      while (i < j) {
        o1 = a(n, (i1 - i), (n - i), i1);
        if (o1 != null)
          break; 
        i++;
      } 
      if (o1 == null)
        throw i.a(); 
      return a(o1, o2, o4, o3);
    } 
    throw i.a();
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\b\a\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */