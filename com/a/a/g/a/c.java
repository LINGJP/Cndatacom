package com.a.a.g.a;

abstract class c {
  private static final c[] a = new c[] { new a(), new b(), new c(), new d(), new e(), new f(), new g(), new h() };
  
  private c() {}
  
  static c a(int paramInt) {
    if (paramInt < 0 || paramInt > 7)
      throw new IllegalArgumentException(); 
    return a[paramInt];
  }
  
  final void a(com.a.a.b.b paramb, int paramInt) {
    for (int i = 0; i < paramInt; i++) {
      int j;
      for (j = 0; j < paramInt; j++) {
        if (a(i, j))
          paramb.c(j, i); 
      } 
    } 
  }
  
  abstract boolean a(int paramInt1, int paramInt2);
  
  private static final class a extends c {
    private a() {}
    
    boolean a(int param1Int1, int param1Int2) {
      return ((param1Int1 + param1Int2 & 0x1) == 0);
    }
  }
  
  private static final class b extends c {
    private b() {}
    
    boolean a(int param1Int1, int param1Int2) {
      return ((param1Int1 & 0x1) == 0);
    }
  }
  
  private static final class c extends c {
    private c() {}
    
    boolean a(int param1Int1, int param1Int2) {
      return (param1Int2 % 3 == 0);
    }
  }
  
  private static final class d extends c {
    private d() {}
    
    boolean a(int param1Int1, int param1Int2) {
      return ((param1Int1 + param1Int2) % 3 == 0);
    }
  }
  
  private static final class e extends c {
    private e() {}
    
    boolean a(int param1Int1, int param1Int2) {
      return (((param1Int1 >>> 1) + param1Int2 / 3 & 0x1) == 0);
    }
  }
  
  private static final class f extends c {
    private f() {}
    
    boolean a(int param1Int1, int param1Int2) {
      param1Int1 *= param1Int2;
      return ((param1Int1 & 0x1) + param1Int1 % 3 == 0);
    }
  }
  
  private static final class g extends c {
    private g() {}
    
    boolean a(int param1Int1, int param1Int2) {
      param1Int1 *= param1Int2;
      return (((param1Int1 & 0x1) + param1Int1 % 3 & 0x1) == 0);
    }
  }
  
  private static final class h extends c {
    private h() {}
    
    boolean a(int param1Int1, int param1Int2) {
      return (((param1Int1 + param1Int2 & 0x1) + param1Int1 * param1Int2 % 3 & 0x1) == 0);
    }
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\g\a\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */