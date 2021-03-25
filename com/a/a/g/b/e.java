package com.a.a.g.b;

import com.a.a.i;
import com.a.a.o;
import com.a.a.p;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class e {
  private final com.a.a.b.b a;
  
  private final List<d> b;
  
  private boolean c;
  
  private final int[] d;
  
  private final p e;
  
  public e(com.a.a.b.b paramb, p paramp) {
    this.a = paramb;
    this.b = new ArrayList<d>();
    this.d = new int[5];
    this.e = paramp;
  }
  
  private float a(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    com.a.a.b.b b1 = this.a;
    int k = b1.e();
    int[] arrayOfInt = a();
    int i;
    for (i = paramInt1; i >= 0 && b1.a(paramInt2, i); i--)
      arrayOfInt[2] = arrayOfInt[2] + 1; 
    float f = Float.NaN;
    int j = i;
    if (i < 0)
      return Float.NaN; 
    while (j >= 0 && !b1.a(paramInt2, j) && arrayOfInt[1] <= paramInt3) {
      arrayOfInt[1] = arrayOfInt[1] + 1;
      j--;
    } 
    if (j >= 0) {
      if (arrayOfInt[1] > paramInt3)
        return Float.NaN; 
      while (j >= 0 && b1.a(paramInt2, j) && arrayOfInt[0] <= paramInt3) {
        arrayOfInt[0] = arrayOfInt[0] + 1;
        j--;
      } 
      if (arrayOfInt[0] > paramInt3)
        return Float.NaN; 
      while (++paramInt1 < k && b1.a(paramInt2, paramInt1)) {
        arrayOfInt[2] = arrayOfInt[2] + 1;
        paramInt1++;
      } 
      i = paramInt1;
      if (paramInt1 == k)
        return Float.NaN; 
      while (i < k && !b1.a(paramInt2, i) && arrayOfInt[3] < paramInt3) {
        arrayOfInt[3] = arrayOfInt[3] + 1;
        i++;
      } 
      if (i != k) {
        if (arrayOfInt[3] >= paramInt3)
          return Float.NaN; 
        while (i < k && b1.a(paramInt2, i) && arrayOfInt[4] < paramInt3) {
          arrayOfInt[4] = arrayOfInt[4] + 1;
          i++;
        } 
        if (arrayOfInt[4] >= paramInt3)
          return Float.NaN; 
        if (Math.abs(arrayOfInt[0] + arrayOfInt[1] + arrayOfInt[2] + arrayOfInt[3] + arrayOfInt[4] - paramInt4) * 5 >= paramInt4 * 2)
          return Float.NaN; 
        if (a(arrayOfInt))
          f = a(arrayOfInt, i); 
        return f;
      } 
      return Float.NaN;
    } 
    return Float.NaN;
  }
  
  private static float a(int[] paramArrayOfint, int paramInt) {
    return (paramInt - paramArrayOfint[4] - paramArrayOfint[3]) - paramArrayOfint[2] / 2.0F;
  }
  
  protected static boolean a(int[] paramArrayOfint) {
    boolean bool2 = false;
    int i = 0;
    int j = 0;
    while (i < 5) {
      int k = paramArrayOfint[i];
      if (k == 0)
        return false; 
      j += k;
      i++;
    } 
    if (j < 7)
      return false; 
    i = (j << 8) / 7;
    j = i / 2;
    boolean bool1 = bool2;
    if (Math.abs(i - (paramArrayOfint[0] << 8)) < j) {
      bool1 = bool2;
      if (Math.abs(i - (paramArrayOfint[1] << 8)) < j) {
        bool1 = bool2;
        if (Math.abs(i * 3 - (paramArrayOfint[2] << 8)) < j * 3) {
          bool1 = bool2;
          if (Math.abs(i - (paramArrayOfint[3] << 8)) < j) {
            bool1 = bool2;
            if (Math.abs(i - (paramArrayOfint[4] << 8)) < j)
              bool1 = true; 
          } 
        } 
      } 
    } 
    return bool1;
  }
  
  private int[] a() {
    this.d[0] = 0;
    this.d[1] = 0;
    this.d[2] = 0;
    this.d[3] = 0;
    this.d[4] = 0;
    return this.d;
  }
  
  private float b(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    com.a.a.b.b b1 = this.a;
    int k = b1.d();
    int[] arrayOfInt = a();
    int i;
    for (i = paramInt1; i >= 0 && b1.a(i, paramInt2); i--)
      arrayOfInt[2] = arrayOfInt[2] + 1; 
    float f = Float.NaN;
    int j = i;
    if (i < 0)
      return Float.NaN; 
    while (j >= 0 && !b1.a(j, paramInt2) && arrayOfInt[1] <= paramInt3) {
      arrayOfInt[1] = arrayOfInt[1] + 1;
      j--;
    } 
    if (j >= 0) {
      if (arrayOfInt[1] > paramInt3)
        return Float.NaN; 
      while (j >= 0 && b1.a(j, paramInt2) && arrayOfInt[0] <= paramInt3) {
        arrayOfInt[0] = arrayOfInt[0] + 1;
        j--;
      } 
      if (arrayOfInt[0] > paramInt3)
        return Float.NaN; 
      while (++paramInt1 < k && b1.a(paramInt1, paramInt2)) {
        arrayOfInt[2] = arrayOfInt[2] + 1;
        paramInt1++;
      } 
      i = paramInt1;
      if (paramInt1 == k)
        return Float.NaN; 
      while (i < k && !b1.a(i, paramInt2) && arrayOfInt[3] < paramInt3) {
        arrayOfInt[3] = arrayOfInt[3] + 1;
        i++;
      } 
      if (i != k) {
        if (arrayOfInt[3] >= paramInt3)
          return Float.NaN; 
        while (i < k && b1.a(i, paramInt2) && arrayOfInt[4] < paramInt3) {
          arrayOfInt[4] = arrayOfInt[4] + 1;
          i++;
        } 
        if (arrayOfInt[4] >= paramInt3)
          return Float.NaN; 
        if (Math.abs(arrayOfInt[0] + arrayOfInt[1] + arrayOfInt[2] + arrayOfInt[3] + arrayOfInt[4] - paramInt4) * 5 >= paramInt4)
          return Float.NaN; 
        if (a(arrayOfInt))
          f = a(arrayOfInt, i); 
        return f;
      } 
      return Float.NaN;
    } 
    return Float.NaN;
  }
  
  private int b() {
    if (this.b.size() <= 1)
      return 0; 
    d d = null;
    for (d d1 : this.b) {
      if (d1.d() >= 2) {
        if (d == null) {
          d = d1;
          continue;
        } 
        this.c = true;
        return (int)(Math.abs(d.a() - d1.a()) - Math.abs(d.b() - d1.b())) / 2;
      } 
    } 
    return 0;
  }
  
  private boolean c() {
    int j = this.b.size();
    Iterator<d> iterator = this.b.iterator();
    float f2 = 0.0F;
    boolean bool = false;
    int i = 0;
    float f1 = 0.0F;
    while (iterator.hasNext()) {
      d d = iterator.next();
      if (d.d() >= 2) {
        i++;
        f1 += d.c();
      } 
    } 
    if (i < 3)
      return false; 
    float f3 = f1 / j;
    iterator = this.b.iterator();
    while (iterator.hasNext())
      f2 += Math.abs(((d)iterator.next()).c() - f3); 
    if (f2 <= f1 * 0.05F)
      bool = true; 
    return bool;
  }
  
  private d[] d() {
    int i = this.b.size();
    if (i < 3)
      throw i.a(); 
    float f = 0.0F;
    if (i > 3) {
      Iterator<d> iterator = this.b.iterator();
      float f2 = 0.0F;
      float f1;
      for (f1 = 0.0F; iterator.hasNext(); f1 += f4 * f4) {
        float f4 = ((d)iterator.next()).c();
        f2 += f4;
      } 
      float f3 = i;
      f2 /= f3;
      f1 = (float)Math.sqrt((f1 / f3 - f2 * f2));
      Collections.sort(this.b, new b(f2));
      f1 = Math.max(0.2F * f2, f1);
      for (i = 0; i < this.b.size() && this.b.size() > 3; i = j + 1) {
        int j = i;
        if (Math.abs(((d)this.b.get(i)).c() - f2) > f1) {
          this.b.remove(i);
          j = i - 1;
        } 
      } 
    } 
    if (this.b.size() > 3) {
      Iterator<d> iterator = this.b.iterator();
      float f1;
      for (f1 = f; iterator.hasNext(); f1 += ((d)iterator.next()).c());
      f1 /= this.b.size();
      Collections.sort(this.b, new a(f1));
      this.b.subList(3, this.b.size()).clear();
    } 
    return new d[] { this.b.get(0), this.b.get(1), this.b.get(2) };
  }
  
  final f a(Map<com.a.a.e, ?> paramMap) {
    int j;
    if (paramMap != null && paramMap.containsKey(com.a.a.e.d)) {
      j = 1;
    } else {
      j = 0;
    } 
    int m = this.a.e();
    int n = this.a.d();
    int i = m * 3 / 228;
    if (i < 3 || j)
      i = 3; 
    int[] arrayOfInt = new int[5];
    int k = i - 1;
    boolean bool = false;
    while (k < m && !bool) {
      arrayOfInt[0] = 0;
      arrayOfInt[1] = 0;
      arrayOfInt[2] = 0;
      arrayOfInt[3] = 0;
      arrayOfInt[4] = 0;
      j = 0;
      int i2 = 0;
      int i1 = i;
      i = i2;
      while (j < n) {
        if (this.a.a(j, k)) {
          i2 = i;
          if ((i & 0x1) == 1)
            i2 = i + 1; 
          arrayOfInt[i2] = arrayOfInt[i2] + 1;
          i = i2;
        } else if ((i & 0x1) == 0) {
          if (i == 4) {
            if (a(arrayOfInt)) {
              if (a(arrayOfInt, k, j)) {
                boolean bool1;
                if (this.c) {
                  bool1 = c();
                  i = k;
                } else {
                  i1 = b();
                  i = k;
                  bool1 = bool;
                  if (i1 > arrayOfInt[2]) {
                    i = k + i1 - arrayOfInt[2] - 2;
                    j = n - 1;
                    bool1 = bool;
                  } 
                } 
                arrayOfInt[0] = 0;
                arrayOfInt[1] = 0;
                arrayOfInt[2] = 0;
                arrayOfInt[3] = 0;
                arrayOfInt[4] = 0;
                i2 = 0;
                i1 = 2;
                k = i;
                i = i2;
                bool = bool1;
              } else {
                arrayOfInt[0] = arrayOfInt[2];
                arrayOfInt[1] = arrayOfInt[3];
                arrayOfInt[2] = arrayOfInt[4];
                arrayOfInt[3] = 1;
                arrayOfInt[4] = 0;
                i = 3;
              } 
            } else {
              arrayOfInt[0] = arrayOfInt[2];
              arrayOfInt[1] = arrayOfInt[3];
              arrayOfInt[2] = arrayOfInt[4];
              arrayOfInt[3] = 1;
              arrayOfInt[4] = 0;
              i = 3;
            } 
          } else {
            arrayOfInt[++i] = arrayOfInt[i] + 1;
          } 
        } else {
          arrayOfInt[i] = arrayOfInt[i] + 1;
        } 
        j++;
      } 
      if (a(arrayOfInt) && a(arrayOfInt, k, n)) {
        i = arrayOfInt[0];
        if (this.c)
          bool = c(); 
      } else {
        i = i1;
      } 
      k += i;
    } 
    d[] arrayOfD = d();
    o.a((o[])arrayOfD);
    return new f(arrayOfD);
  }
  
  protected final boolean a(int[] paramArrayOfint, int paramInt1, int paramInt2) {
    boolean bool = false;
    int i = paramArrayOfint[0] + paramArrayOfint[1] + paramArrayOfint[2] + paramArrayOfint[3] + paramArrayOfint[4];
    paramInt2 = (int)a(paramArrayOfint, paramInt2);
    float f = a(paramInt1, paramInt2, paramArrayOfint[2], i);
    if (!Float.isNaN(f)) {
      float f1 = b(paramInt2, (int)f, paramArrayOfint[2], i);
      if (!Float.isNaN(f1)) {
        float f2 = i / 7.0F;
        paramInt1 = 0;
        while (true) {
          paramInt2 = bool;
          if (paramInt1 < this.b.size()) {
            d d = this.b.get(paramInt1);
            if (d.a(f2, f, f1)) {
              this.b.set(paramInt1, d.b(f, f1, f2));
              paramInt2 = 1;
              break;
            } 
            paramInt1++;
            continue;
          } 
          break;
        } 
        if (paramInt2 == 0) {
          d d = new d(f1, f, f2);
          this.b.add(d);
          if (this.e != null)
            this.e.a(d); 
        } 
        return true;
      } 
    } 
    return false;
  }
  
  private static final class a implements Serializable, Comparator<d> {
    private final float a;
    
    private a(float param1Float) {
      this.a = param1Float;
    }
    
    public int a(d param1d1, d param1d2) {
      if (param1d2.d() == param1d1.d()) {
        float f1 = Math.abs(param1d2.c() - this.a);
        float f2 = Math.abs(param1d1.c() - this.a);
        return (f1 < f2) ? 1 : ((f1 == f2) ? 0 : -1);
      } 
      return param1d2.d() - param1d1.d();
    }
  }
  
  private static final class b implements Serializable, Comparator<d> {
    private final float a;
    
    private b(float param1Float) {
      this.a = param1Float;
    }
    
    public int a(d param1d1, d param1d2) {
      float f1 = Math.abs(param1d2.c() - this.a);
      float f2 = Math.abs(param1d1.c() - this.a);
      return (f1 < f2) ? -1 : ((f1 == f2) ? 0 : 1);
    }
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\g\b\e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */