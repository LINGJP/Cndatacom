package com.a.a.f.a;

import com.a.a.b.b;
import com.a.a.b.e;
import com.a.a.d;
import com.a.a.f;
import com.a.a.f.a.a.a;

public final class c {
  private final a a = new a();
  
  private static void a(int[] paramArrayOfint, int paramInt) {
    if (paramArrayOfint.length < 4)
      throw f.a(); 
    int i = paramArrayOfint[0];
    if (i > paramArrayOfint.length)
      throw f.a(); 
    if (i == 0) {
      if (paramInt < paramArrayOfint.length) {
        paramArrayOfint[0] = paramArrayOfint.length - paramInt;
        return;
      } 
      throw f.a();
    } 
  }
  
  private void a(int[] paramArrayOfint1, int[] paramArrayOfint2, int paramInt) {
    if (paramArrayOfint2.length > paramInt / 2 + 3 || paramInt < 0 || paramInt > 512)
      throw d.a(); 
    this.a.a(paramArrayOfint1, paramInt, paramArrayOfint2);
  }
  
  public e a(b paramb) {
    a a1 = new a(paramb);
    int[] arrayOfInt = a1.a();
    if (arrayOfInt.length == 0)
      throw f.a(); 
    int i = 1 << a1.c() + 1;
    a(arrayOfInt, a1.b(), i);
    a(arrayOfInt, i);
    return b.a(arrayOfInt);
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\f\a\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */