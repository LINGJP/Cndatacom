package com.a.a.c.a;

import com.a.a.b.b;
import com.a.a.b.b.a;
import com.a.a.b.b.c;
import com.a.a.b.e;

public final class d {
  private final c a = new c(a.f);
  
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
    } catch (com.a.a.b.b.d d1) {
      throw com.a.a.d.a();
    } 
  }
  
  public e a(b paramb) {
    a a = new a(paramb);
    e e = a.a();
    b[] arrayOfB = b.a(a.b(), e);
    int k = arrayOfB.length;
    int m = arrayOfB.length;
    int i = 0;
    int j = 0;
    while (i < m) {
      j += arrayOfB[i].a();
      i++;
    } 
    byte[] arrayOfByte = new byte[j];
    for (i = 0; i < k; i++) {
      b b1 = arrayOfB[i];
      byte[] arrayOfByte1 = b1.b();
      m = b1.a();
      a(arrayOfByte1, m);
      for (j = 0; j < m; j++)
        arrayOfByte[j * k + i] = arrayOfByte1[j]; 
    } 
    return c.a(arrayOfByte);
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\c\a\d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */