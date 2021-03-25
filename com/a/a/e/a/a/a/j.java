package com.a.a.e.a.a.a;

import com.a.a.b.a;

public abstract class j {
  private final a a;
  
  private final s b;
  
  j(a parama) {
    this.a = parama;
    this.b = new s(parama);
  }
  
  public static j a(a parama) {
    StringBuilder stringBuilder;
    if (parama.a(1))
      return new g(parama); 
    if (!parama.a(2))
      return new k(parama); 
    switch (s.a(parama, 1, 4)) {
      default:
        switch (s.a(parama, 1, 5)) {
          default:
            switch (s.a(parama, 1, 7)) {
              default:
                stringBuilder = new StringBuilder();
                stringBuilder.append("unknown decoder: ");
                stringBuilder.append(parama);
                throw new IllegalStateException(stringBuilder.toString());
              case 63:
                return new e(parama, "320", "17");
              case 62:
                return new e(parama, "310", "17");
              case 61:
                return new e(parama, "320", "15");
              case 60:
                return new e(parama, "310", "15");
              case 59:
                return new e(parama, "320", "13");
              case 58:
                return new e(parama, "310", "13");
              case 57:
                return new e(parama, "320", "11");
              case 56:
                break;
            } 
            return new e(parama, "310", "11");
          case 13:
            return new d(parama);
          case 12:
            break;
        } 
        return new c(parama);
      case 5:
        return new b(parama);
      case 4:
        break;
    } 
    return new a(parama);
  }
  
  public abstract String a();
  
  protected final a b() {
    return this.a;
  }
  
  protected final s c() {
    return this.b;
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\e\a\a\a\j.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */