package com.cndatacom.campus.netcore;

import java.io.UnsupportedEncodingException;

public class DaMod {
  private long a = 0L;
  
  static {
    System.loadLibrary("daproxy");
  }
  
  public DaMod(byte[] paramArrayOfbyte) {
    this.a = load(paramArrayOfbyte);
  }
  
  private native String aid(long paramLong);
  
  private native byte[] dec(long paramLong, byte[] paramArrayOfbyte);
  
  private native byte[] enc(long paramLong, byte[] paramArrayOfbyte);
  
  private native void free(long paramLong);
  
  private native String key(long paramLong);
  
  private native long load(byte[] paramArrayOfbyte);
  
  private native String prev(long paramLong);
  
  public String Decode(String paramString) {
    if (this.a == 0L)
      return ""; 
    try {
      return new String(dec(this.a, paramString.getBytes("UTF-8")), "UTF-8");
    } catch (UnsupportedEncodingException unsupportedEncodingException) {
      return "";
    } 
  }
  
  public byte[] Decode(byte[] paramArrayOfbyte) {
    return (this.a == 0L) ? new byte[0] : dec(this.a, paramArrayOfbyte);
  }
  
  public String Encode(String paramString) {
    if (this.a == 0L)
      return ""; 
    try {
      return new String(enc(this.a, paramString.getBytes("UTF-8")), "UTF-8");
    } catch (UnsupportedEncodingException unsupportedEncodingException) {
      return "";
    } 
  }
  
  public byte[] Encode(byte[] paramArrayOfbyte) {
    return (this.a == 0L) ? new byte[0] : enc(this.a, paramArrayOfbyte);
  }
  
  public void Free() {
    if (this.a != 0L) {
      free(this.a);
      this.a = 0L;
    } 
  }
  
  public boolean Load(byte[] paramArrayOfbyte) {
    Free();
    this.a = load(paramArrayOfbyte);
    return (this.a != 0L);
  }
  
  protected void finalize() {
    Free();
    super.finalize();
  }
  
  public String getId() {
    return (this.a == 0L) ? "" : aid(this.a);
  }
  
  public String getKey() {
    return (this.a == 0L) ? "" : key(this.a);
  }
  
  public String getPrefix() {
    return (this.a == 0L) ? "" : prev(this.a);
  }
  
  public boolean isOk() {
    return (this.a != 0L);
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\campus\netcore\DaMod.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */