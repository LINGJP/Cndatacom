package com.cndatacom.cnportalclient.model;

public class CodeCheckInfo {
  private String a = "";
  
  private String b = "";
  
  private String c = "";
  
  public String getArgs() {
    return this.c;
  }
  
  public String getCmd() {
    return this.b;
  }
  
  public String getTicket() {
    return this.a;
  }
  
  public boolean isUdpCode() {
    return ("pass".equals(this.b) || "hello".equals(this.b));
  }
  
  public void setArgs(String paramString) {
    this.c = paramString;
  }
  
  public void setCmd(String paramString) {
    this.b = paramString;
  }
  
  public void setTicket(String paramString) {
    this.a = paramString;
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\model\CodeCheckInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */