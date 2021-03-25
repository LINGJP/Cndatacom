package com.cndatacom.cnportalclient.model;

public class CodeUploadArgsInfo {
  private String a = "";
  
  private int b;
  
  public CodeUploadArgsInfo(String paramString, int paramInt) {
    this.a = paramString;
    this.b = paramInt;
  }
  
  public static CodeUploadArgsInfo madeConnCodeUploadArgsInfo(int paramInt) {
    return new CodeUploadArgsInfo("conn", paramInt);
  }
  
  public static CodeUploadArgsInfo madeHelloCodeUploadArgsInfo(int paramInt) {
    return new CodeUploadArgsInfo("hello", paramInt);
  }
  
  public String madeXmlString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("<");
    stringBuilder.append(this.a);
    stringBuilder.append(">");
    stringBuilder.append(this.b);
    stringBuilder.append("</");
    stringBuilder.append(this.a);
    stringBuilder.append(">");
    return stringBuilder.toString();
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\model\CodeUploadArgsInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */