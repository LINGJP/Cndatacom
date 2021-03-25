package com.a.a.e;

import com.a.a.a;
import com.a.a.b.a;
import com.a.a.i;
import com.a.a.m;
import com.a.a.o;
import java.util.EnumMap;
import java.util.Map;

final class n {
  private static final int[] a = new int[] { 24, 20, 18, 17, 12, 6, 3, 10, 9, 5 };
  
  private final int[] b = new int[4];
  
  private final StringBuilder c = new StringBuilder();
  
  private static int a(int paramInt) {
    for (int i = 0; i < 10; i++) {
      if (paramInt == a[i])
        return i; 
    } 
    throw i.a();
  }
  
  private static int a(CharSequence paramCharSequence) {
    int k = paramCharSequence.length();
    int i = k - 2;
    int j = 0;
    while (i >= 0) {
      j += paramCharSequence.charAt(i) - 48;
      i -= 2;
    } 
    j *= 3;
    for (i = k - 1; i >= 0; i -= 2)
      j += paramCharSequence.charAt(i) - 48; 
    return j * 3 % 10;
  }
  
  private static Map<com.a.a.n, Object> a(String paramString) {
    if (paramString.length() != 5)
      return null; 
    paramString = b(paramString);
    if (paramString == null)
      return null; 
    EnumMap<com.a.a.n, Object> enumMap = new EnumMap<com.a.a.n, Object>(com.a.a.n.class);
    enumMap.put(com.a.a.n.f, paramString);
    return enumMap;
  }
  
  private static String b(String paramString) {
    String str;
    char c = paramString.charAt(0);
    if (c != '0') {
      if (c != '5') {
        if (c != '9') {
          str = "";
        } else {
          if ("90000".equals(paramString))
            return null; 
          if ("99991".equals(paramString))
            return "0.00"; 
          if ("99990".equals(paramString))
            return "Used"; 
          str = "";
        } 
      } else {
        str = "$";
      } 
    } else {
      str = "£";
    } 
    int j = Integer.parseInt(paramString.substring(1));
    int i = j / 100;
    j %= 100;
    if (j < 10) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("0");
      stringBuilder1.append(j);
      String str1 = stringBuilder1.toString();
    } else {
      paramString = String.valueOf(j);
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(str);
    stringBuilder.append(String.valueOf(i));
    stringBuilder.append('.');
    stringBuilder.append(paramString);
    return stringBuilder.toString();
  }
  
  int a(a parama, int[] paramArrayOfint, StringBuilder paramStringBuilder) {
    int[] arrayOfInt = this.b;
    arrayOfInt[0] = 0;
    arrayOfInt[1] = 0;
    arrayOfInt[2] = 0;
    arrayOfInt[3] = 0;
    int m = parama.a();
    int i = paramArrayOfint[1];
    int j = 0;
    int k;
    for (k = 0; j < 5 && i < m; k = i1) {
      int i2 = p.a(parama, arrayOfInt, i, p.e);
      paramStringBuilder.append((char)(i2 % 10 + 48));
      int i3 = arrayOfInt.length;
      int i1;
      for (i1 = 0; i1 < i3; i1++)
        i += arrayOfInt[i1]; 
      i1 = k;
      if (i2 >= 10)
        i1 = k | 1 << 4 - j; 
      if (j != 4)
        i = parama.d(parama.c(i)); 
      j++;
    } 
    if (paramStringBuilder.length() != 5)
      throw i.a(); 
    j = a(k);
    if (a(paramStringBuilder.toString()) != j)
      throw i.a(); 
    return i;
  }
  
  m a(int paramInt, a parama, int[] paramArrayOfint) {
    StringBuilder stringBuilder = this.c;
    stringBuilder.setLength(0);
    int i = a(parama, paramArrayOfint, stringBuilder);
    String str = stringBuilder.toString();
    Map<com.a.a.n, Object> map = a(str);
    float f1 = (paramArrayOfint[0] + paramArrayOfint[1]) / 2.0F;
    float f2 = paramInt;
    o o1 = new o(f1, f2);
    o o2 = new o(i, f2);
    a a1 = a.q;
    m m = new m(str, null, new o[] { o1, o2 }, a1);
    if (map != null)
      m.a(map); 
    return m;
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\e\n.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */