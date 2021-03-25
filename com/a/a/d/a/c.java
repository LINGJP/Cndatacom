package com.a.a.d.a;

import com.a.a.b.b;
import com.a.a.b.b.a;
import com.a.a.b.b.d;
import com.a.a.b.e;
import com.a.a.d;
import com.a.a.e;
import com.a.a.f;
import java.util.Map;

public final class c {
  private final com.a.a.b.b.c a = new com.a.a.b.b.c(a.h);
  
  private void a(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    byte b;
    int j = paramInt2 + paramInt3;
    if (paramInt4 == 0) {
      b = 1;
    } else {
      b = 2;
    } 
    int[] arrayOfInt = new int[j / b];
    boolean bool = false;
    int i;
    for (i = 0; i < j; i++) {
      if (paramInt4 == 0 || i % 2 == paramInt4 - 1)
        arrayOfInt[i / b] = paramArrayOfbyte[i + paramInt1] & 0xFF; 
    } 
    try {
      this.a.a(arrayOfInt, paramInt3 / b);
      for (paramInt3 = bool; paramInt3 < paramInt2; paramInt3++) {
        if (paramInt4 == 0 || paramInt3 % 2 == paramInt4 - 1)
          paramArrayOfbyte[paramInt3 + paramInt1] = (byte)arrayOfInt[paramInt3 / b]; 
      } 
      return;
    } catch (d d) {
      throw d.a();
    } 
  }
  
  public e a(b paramb, Map<e, ?> paramMap) {
    byte[] arrayOfByte2 = (new a(paramb)).a();
    a(arrayOfByte2, 0, 10, 10, 0);
    int i = arrayOfByte2[0] & 0xF;
    switch (i) {
      default:
        throw f.a();
      case 5:
        a(arrayOfByte2, 20, 68, 56, 1);
        a(arrayOfByte2, 20, 68, 56, 2);
        arrayOfByte1 = new byte[78];
        System.arraycopy(arrayOfByte2, 0, arrayOfByte1, 0, 10);
        System.arraycopy(arrayOfByte2, 20, arrayOfByte1, 10, arrayOfByte1.length - 10);
        return b.a(arrayOfByte1, i);
      case 2:
      case 3:
      case 4:
        break;
    } 
    a(arrayOfByte2, 20, 84, 40, 1);
    a(arrayOfByte2, 20, 84, 40, 2);
    byte[] arrayOfByte1 = new byte[94];
    System.arraycopy(arrayOfByte2, 0, arrayOfByte1, 0, 10);
    System.arraycopy(arrayOfByte2, 20, arrayOfByte1, 10, arrayOfByte1.length - 10);
    return b.a(arrayOfByte1, i);
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\d\a\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */