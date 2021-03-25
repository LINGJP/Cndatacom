package com.a.a;

public abstract class g {
  private final int a;
  
  private final int b;
  
  protected g(int paramInt1, int paramInt2) {
    this.a = paramInt1;
    this.b = paramInt2;
  }
  
  public abstract byte[] a();
  
  public abstract byte[] a(int paramInt, byte[] paramArrayOfbyte);
  
  public final int b() {
    return this.a;
  }
  
  public final int c() {
    return this.b;
  }
  
  public boolean d() {
    return false;
  }
  
  public g e() {
    throw new UnsupportedOperationException("This luminance source does not support rotation by 90 degrees.");
  }
  
  public final String toString() {
    byte[] arrayOfByte = new byte[this.a];
    StringBuilder stringBuilder = new StringBuilder(this.b * (this.a + 1));
    for (int i = 0; i < this.b; i++) {
      arrayOfByte = a(i, arrayOfByte);
      for (int j = 0; j < this.a; j++) {
        byte b;
        int k = arrayOfByte[j] & 0xFF;
        if (k < 64) {
          b = 35;
        } else if (k < 128) {
          b = 43;
        } else if (k < 192) {
          b = 46;
        } else {
          b = 32;
        } 
        stringBuilder.append(b);
      } 
      stringBuilder.append('\n');
    } 
    return stringBuilder.toString();
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */