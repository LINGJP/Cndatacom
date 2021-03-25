package com.a.a.g.a;

import com.a.a.b.b;
import com.a.a.b.b.a;
import com.a.a.b.b.c;
import com.a.a.b.b.d;
import com.a.a.d;
import java.util.Map;

public final class e {
  private final c a = new c(a.e);
  
  private void a(byte[] paramArrayOfbyte, int paramInt) {
    int j = paramArrayOfbyte.length;
    int[] arrayOfInt = new int[j];
    boolean bool = false;
    int i;
    for (i = 0; i < j; i++)
      arrayOfInt[i] = paramArrayOfbyte[i] & 0xFF; 
    i = paramArrayOfbyte.length;
    try {
      this.a.a(arrayOfInt, i - paramInt);
      for (i = bool; i < paramInt; i++)
        paramArrayOfbyte[i] = (byte)arrayOfInt[i]; 
      return;
    } catch (d d) {
      throw d.a();
    } 
  }
  
  public com.a.a.b.e a(b paramb, Map<com.a.a.e, ?> paramMap) {
    a a = new a(paramb);
    i i = a.b();
    f f = a.a().a();
    b[] arrayOfB = b.a(a.c(), i, f);
    int m = arrayOfB.length;
    int j = 0;
    int k = 0;
    while (j < m) {
      k += arrayOfB[j].a();
      j++;
    } 
    byte[] arrayOfByte = new byte[k];
    int n = arrayOfB.length;
    k = 0;
    j = 0;
    while (k < n) {
      b b1 = arrayOfB[k];
      byte[] arrayOfByte1 = b1.b();
      int i1 = b1.a();
      a(arrayOfByte1, i1);
      m = 0;
      while (m < i1) {
        arrayOfByte[j] = arrayOfByte1[m];
        m++;
        j++;
      } 
      k++;
    } 
    return d.a(arrayOfByte, i, f, paramMap);
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\g\a\e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */