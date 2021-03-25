package org.apache.cordova;

public interface ExposedJsApi {
  String exec(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4);
  
  String retrieveJsMessages(int paramInt, boolean paramBoolean);
  
  void setNativeToJsBridgeMode(int paramInt1, int paramInt2);
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\org\apache\cordova\ExposedJsApi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */