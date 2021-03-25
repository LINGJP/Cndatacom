package com.cndatacom.cnportalclient.model;

import android.text.TextUtils;
import com.cndatacom.cnportalclient.utils.CdcUtils;
import java.util.Random;

public class CodeResultInfo extends CodeCheckInfo {
  private int a = -1;
  
  private String b = "";
  
  private CodeResultInfo() {
    this.b = a(8);
  }
  
  private String a(int paramInt) {
    Random random = new Random();
    StringBuffer stringBuffer = new StringBuffer();
    for (int i = 0; i < paramInt; i++)
      stringBuffer.append("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".charAt(random.nextInt("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".length()))); 
    return stringBuffer.toString().toLowerCase();
  }
  
  public static String madeIPKeyString(String paramString) {
    return CdcUtils.byteArrayToHex(CdcUtils.md5(paramString.getBytes())).toLowerCase();
  }
  
  public static CodeResultInfo newInstance(CodeCheckInfo paramCodeCheckInfo) {
    CodeResultInfo codeResultInfo = new CodeResultInfo();
    codeResultInfo.setTicket(paramCodeCheckInfo.getTicket());
    codeResultInfo.setCmd(paramCodeCheckInfo.getCmd());
    codeResultInfo.setArgs(paramCodeCheckInfo.getArgs());
    return codeResultInfo;
  }
  
  public String getRandomNum() {
    return this.b;
  }
  
  public int getResult() {
    return this.a;
  }
  
  public boolean isHelloCode() {
    return "hello".equals(getCmd());
  }
  
  public boolean isUpload() {
    return !TextUtils.isEmpty(getTicket());
  }
  
  public String madeKeyString(String paramString, int paramInt) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(getRandomNum());
    stringBuilder.append(paramString);
    stringBuilder.append(":");
    stringBuilder.append(paramInt);
    return CdcUtils.byteArrayToHex(CdcUtils.md5(stringBuilder.toString().getBytes())).toLowerCase();
  }
  
  public String madeRandomKeyString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(getRandomNum());
    stringBuilder.append(getArgs());
    return CdcUtils.byteArrayToHex(CdcUtils.md5(stringBuilder.toString().getBytes())).toLowerCase();
  }
  
  public String madeXmlString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("<ticket>");
    stringBuilder.append(getTicket());
    stringBuilder.append("</ticket><cmd>");
    stringBuilder.append(getCmd());
    stringBuilder.append("</cmd><result>");
    stringBuilder.append(this.a);
    stringBuilder.append("</result>");
    return stringBuilder.toString();
  }
  
  public void setResult(int paramInt) {
    this.a = paramInt;
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\model\CodeResultInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */