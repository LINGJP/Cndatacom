package com.cndatacom.cnportalclient.constant;

import android.text.TextUtils;
import com.cndatacom.cnportalclient.http.CdcHttpClient;
import com.cndatacom.cnportalclient.model.ReturnUIResult;

public final class UiErrCode {
  public static final String CONFIG_URL_ERROR = "300309";
  
  public static final String DECODE_ERROR = "200114";
  
  public static final String ENCODE_ERROR = "200113";
  
  public static final String ERR_TYPE_PORTAL = "140";
  
  public static final String IP_MAC_ERROR = "200112";
  
  public static final String KEEP_TIMEOUT_ERROR = "200117";
  
  public static final String NETWORK_ERROR = "300108";
  
  public static final String PARSER_ERROR = "200115";
  
  public static final String REVERSE_NOTIFY_ERROR = "200118";
  
  public static final String SERVER_ERROR = "300308";
  
  public static final String SHARED_ERROR = "200119";
  
  public static final String VPN_CLOSED_ERROR = "200122";
  
  public static final String VPN_CREATE_ERROR = "200124";
  
  public static final String VPN_SERVICE_KILLED_ERROR = "200121";
  
  private static String a(String paramString1, String paramString2) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramString1);
    for (int i = 3 - paramString2.length(); i > 0; i--)
      stringBuilder.append('0'); 
    stringBuilder.append(paramString2);
    return stringBuilder.toString();
  }
  
  public static boolean isProtocolError(String paramString) {
    return paramString.startsWith("140");
  }
  
  public static String makePortalErrorCode(String paramString) {
    return a("140", paramString);
  }
  
  public static ReturnUIResult makeUiErrorCode(CdcHttpClient.Result paramResult) {
    ReturnUIResult returnUIResult = new ReturnUIResult();
    if (!TextUtils.isEmpty(paramResult.getExtraCode())) {
      returnUIResult.setErrorCode(paramResult.getExtraCode());
      return returnUIResult;
    } 
    if (!paramResult.isOk()) {
      int i = paramResult.getHttpCode();
      if (400 <= i && i <= 600) {
        returnUIResult.setErrorCode("300308");
        return returnUIResult;
      } 
      returnUIResult.setErrorCode("300108");
      return returnUIResult;
    } 
    if ("0".equals(paramResult.getCdcCode())) {
      returnUIResult.setErrorCode("0");
      return returnUIResult;
    } 
    returnUIResult.setErrorCode(a("140", paramResult.getCdcCode()));
    returnUIResult.setSubErrorCode(paramResult.getCdcSubCode());
    return returnUIResult;
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\constant\UiErrCode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */