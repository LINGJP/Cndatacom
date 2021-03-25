package com.a.a.e.a.a.a;

import com.a.a.b.a;

abstract class i extends h {
  i(a parama) {
    super(parama);
  }
  
  protected abstract int a(int paramInt);
  
  protected abstract void a(StringBuilder paramStringBuilder, int paramInt);
  
  protected final void b(StringBuilder paramStringBuilder, int paramInt1, int paramInt2) {
    paramInt1 = c().a(paramInt1, paramInt2);
    a(paramStringBuilder, paramInt1);
    int j = a(paramInt1);
    paramInt2 = 100000;
    for (paramInt1 = 0; paramInt1 < 5; paramInt1++) {
      if (j / paramInt2 == 0)
        paramStringBuilder.append('0'); 
      paramInt2 /= 10;
    } 
    paramStringBuilder.append(j);
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\e\a\a\a\i.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */