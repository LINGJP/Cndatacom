package com.a.a.e;

import com.a.a.a;
import com.a.a.b.a;
import com.a.a.i;
import com.a.a.n;
import com.a.a.o;
import java.util.EnumMap;
import java.util.Map;

final class m {
  private final int[] a = new int[4];
  
  private final StringBuilder b = new StringBuilder();
  
  private static Map<n, Object> a(String paramString) {
    if (paramString.length() != 2)
      return null; 
    EnumMap<n, Object> enumMap = new EnumMap<n, Object>(n.class);
    enumMap.put(n.e, Integer.valueOf(paramString));
    return enumMap;
  }
  
  int a(a parama, int[] paramArrayOfint, StringBuilder paramStringBuilder) {
    int[] arrayOfInt = this.a;
    arrayOfInt[0] = 0;
    arrayOfInt[1] = 0;
    arrayOfInt[2] = 0;
    arrayOfInt[3] = 0;
    int n = parama.a();
    int i = paramArrayOfint[1];
    int j = 0;
    int k;
    for (k = 0; j < 2 && i < n; k = i1) {
      int i2 = p.a(parama, arrayOfInt, i, p.e);
      paramStringBuilder.append((char)(i2 % 10 + 48));
      int i3 = arrayOfInt.length;
      int i1;
      for (i1 = 0; i1 < i3; i1++)
        i += arrayOfInt[i1]; 
      i1 = k;
      if (i2 >= 10)
        i1 = 1 << 1 - j | k; 
      if (j != 1)
        i = parama.d(parama.c(i)); 
      j++;
    } 
    if (paramStringBuilder.length() != 2)
      throw i.a(); 
    if (Integer.parseInt(paramStringBuilder.toString()) % 4 != k)
      throw i.a(); 
    return i;
  }
  
  com.a.a.m a(int paramInt, a parama, int[] paramArrayOfint) {
    StringBuilder stringBuilder = this.b;
    stringBuilder.setLength(0);
    int i = a(parama, paramArrayOfint, stringBuilder);
    String str = stringBuilder.toString();
    Map<n, Object> map = a(str);
    float f1 = (paramArrayOfint[0] + paramArrayOfint[1]) / 2.0F;
    float f2 = paramInt;
    o o1 = new o(f1, f2);
    o o2 = new o(i, f2);
    a a1 = a.q;
    com.a.a.m m1 = new com.a.a.m(str, null, new o[] { o1, o2 }, a1);
    if (map != null)
      m1.a(map); 
    return m1;
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\e\m.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */