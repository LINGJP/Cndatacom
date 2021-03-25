package com.a.a;

import java.util.EnumMap;
import java.util.Map;

public final class m {
  private final String a;
  
  private final byte[] b;
  
  private o[] c;
  
  private final a d;
  
  private Map<n, Object> e;
  
  private final long f;
  
  public m(String paramString, byte[] paramArrayOfbyte, o[] paramArrayOfo, a parama) {
    this(paramString, paramArrayOfbyte, paramArrayOfo, parama, System.currentTimeMillis());
  }
  
  public m(String paramString, byte[] paramArrayOfbyte, o[] paramArrayOfo, a parama, long paramLong) {
    this.a = paramString;
    this.b = paramArrayOfbyte;
    this.c = paramArrayOfo;
    this.d = parama;
    this.e = null;
    this.f = paramLong;
  }
  
  public String a() {
    return this.a;
  }
  
  public void a(n paramn, Object paramObject) {
    if (this.e == null)
      this.e = new EnumMap<n, Object>(n.class); 
    this.e.put(paramn, paramObject);
  }
  
  public void a(Map<n, Object> paramMap) {
    if (paramMap != null) {
      if (this.e == null) {
        this.e = paramMap;
        return;
      } 
      this.e.putAll(paramMap);
    } 
  }
  
  public void a(o[] paramArrayOfo) {
    o[] arrayOfO = this.c;
    if (arrayOfO == null) {
      this.c = paramArrayOfo;
      return;
    } 
    if (paramArrayOfo != null && paramArrayOfo.length > 0) {
      o[] arrayOfO1 = new o[arrayOfO.length + paramArrayOfo.length];
      System.arraycopy(arrayOfO, 0, arrayOfO1, 0, arrayOfO.length);
      System.arraycopy(paramArrayOfo, 0, arrayOfO1, arrayOfO.length, paramArrayOfo.length);
      this.c = arrayOfO1;
    } 
  }
  
  public byte[] b() {
    return this.b;
  }
  
  public o[] c() {
    return this.c;
  }
  
  public a d() {
    return this.d;
  }
  
  public Map<n, Object> e() {
    return this.e;
  }
  
  public String toString() {
    return this.a;
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\m.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */