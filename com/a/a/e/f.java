package com.a.a.e;

import com.a.a.a;
import com.a.a.b.a;

public final class f extends p {
  private final int[] a = new int[4];
  
  protected int a(a parama, int[] paramArrayOfint, StringBuilder paramStringBuilder) {
    int[] arrayOfInt = this.a;
    arrayOfInt[0] = 0;
    arrayOfInt[1] = 0;
    arrayOfInt[2] = 0;
    arrayOfInt[3] = 0;
    int k = parama.a();
    int i = paramArrayOfint[1];
    int j;
    for (j = 0; j < 4 && i < k; j++) {
      paramStringBuilder.append((char)(a(parama, arrayOfInt, i, d) + 48));
      int n = arrayOfInt.length;
      int m;
      for (m = 0; m < n; m++)
        i += arrayOfInt[m]; 
    } 
    i = a(parama, i, true, c)[1];
    for (j = 0; j < 4 && i < k; j++) {
      paramStringBuilder.append((char)(a(parama, arrayOfInt, i, d) + 48));
      int n = arrayOfInt.length;
      int m;
      for (m = 0; m < n; m++)
        i += arrayOfInt[m]; 
    } 
    return i;
  }
  
  a b() {
    return a.g;
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\e\f.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */