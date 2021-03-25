package com.a.a.e.a.a.a;

import com.a.a.b.a;
import com.a.a.i;

final class e extends i {
  private final String a;
  
  private final String b;
  
  e(a parama, String paramString1, String paramString2) {
    super(parama);
    this.a = paramString2;
    this.b = paramString1;
  }
  
  private void c(StringBuilder paramStringBuilder, int paramInt) {
    int j = c().a(paramInt, 16);
    if (j == 38400)
      return; 
    paramStringBuilder.append('(');
    paramStringBuilder.append(this.a);
    paramStringBuilder.append(')');
    paramInt = j % 32;
    int k = j / 32;
    j = k % 12 + 1;
    k /= 12;
    if (k / 10 == 0)
      paramStringBuilder.append('0'); 
    paramStringBuilder.append(k);
    if (j / 10 == 0)
      paramStringBuilder.append('0'); 
    paramStringBuilder.append(j);
    if (paramInt / 10 == 0)
      paramStringBuilder.append('0'); 
    paramStringBuilder.append(paramInt);
  }
  
  protected int a(int paramInt) {
    return paramInt % 100000;
  }
  
  public String a() {
    if (b().a() != 84)
      throw i.a(); 
    StringBuilder stringBuilder = new StringBuilder();
    b(stringBuilder, 8);
    b(stringBuilder, 48, 20);
    c(stringBuilder, 68);
    return stringBuilder.toString();
  }
  
  protected void a(StringBuilder paramStringBuilder, int paramInt) {
    paramInt /= 100000;
    paramStringBuilder.append('(');
    paramStringBuilder.append(this.b);
    paramStringBuilder.append(paramInt);
    paramStringBuilder.append(')');
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\e\a\a\a\e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */