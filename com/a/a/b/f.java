package com.a.a.b;

import com.a.a.i;

public final class f extends i {
  public b a(b paramb, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, float paramFloat9, float paramFloat10, float paramFloat11, float paramFloat12, float paramFloat13, float paramFloat14, float paramFloat15, float paramFloat16) {
    return a(paramb, paramInt1, paramInt2, k.a(paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramFloat5, paramFloat6, paramFloat7, paramFloat8, paramFloat9, paramFloat10, paramFloat11, paramFloat12, paramFloat13, paramFloat14, paramFloat15, paramFloat16));
  }
  
  public b a(b paramb, int paramInt1, int paramInt2, k paramk) {
    if (paramInt1 <= 0 || paramInt2 <= 0)
      throw i.a(); 
    b b1 = new b(paramInt1, paramInt2);
    float[] arrayOfFloat = new float[paramInt1 << 1];
    paramInt1 = 0;
    label27: while (true) {
      if (paramInt1 < paramInt2) {
        int m = arrayOfFloat.length;
        float f1 = paramInt1;
        int j;
        for (j = 0; j < m; j += 2) {
          arrayOfFloat[j] = (j >> 1) + 0.5F;
          arrayOfFloat[j + 1] = f1 + 0.5F;
        } 
        paramk.a(arrayOfFloat);
        a(paramb, arrayOfFloat);
        j = 0;
        while (true) {
          if (j < m) {
            try {
              if (paramb.a((int)arrayOfFloat[j], (int)arrayOfFloat[j + 1]))
                b1.b(j >> 1, paramInt1); 
              j += 2;
            } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
              throw i.a();
            } 
            continue;
          } 
          paramInt1++;
          continue label27;
        } 
        break;
      } 
      return b1;
    } 
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\b\f.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */