package com.a.a.e.a.a;

import java.util.List;

final class a {
  static com.a.a.b.a a(List<b> paramList) {
    int j = (paramList.size() << 1) - 1;
    int i = j;
    if (((b)paramList.get(paramList.size() - 1)).b() == null)
      i = j - 1; 
    com.a.a.b.a a1 = new com.a.a.b.a(i * 12);
    int k = ((b)paramList.get(0)).b().a();
    j = 11;
    i = 0;
    while (j >= 0) {
      if ((1 << j & k) != 0)
        a1.b(i); 
      i++;
      j--;
    } 
    for (k = 1; k < paramList.size(); k++) {
      b b = paramList.get(k);
      int m = b.a().a();
      for (j = 11; j >= 0; j--) {
        if ((1 << j & m) != 0)
          a1.b(i); 
        i++;
      } 
      j = i;
      if (b.b() != null) {
        int n = b.b().a();
        m = 11;
        while (true) {
          j = i;
          if (m >= 0) {
            if ((1 << m & n) != 0)
              a1.b(i); 
            i++;
            m--;
            continue;
          } 
          break;
        } 
      } 
      i = j;
    } 
    return a1;
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\e\a\a\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */