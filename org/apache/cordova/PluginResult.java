package org.apache.cordova;

import android.util.Base64;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class PluginResult {
  public static final int MESSAGE_TYPE_ARRAYBUFFER = 6;
  
  public static final int MESSAGE_TYPE_BINARYSTRING = 7;
  
  public static final int MESSAGE_TYPE_BOOLEAN = 4;
  
  public static final int MESSAGE_TYPE_JSON = 2;
  
  public static final int MESSAGE_TYPE_MULTIPART = 8;
  
  public static final int MESSAGE_TYPE_NULL = 5;
  
  public static final int MESSAGE_TYPE_NUMBER = 3;
  
  public static final int MESSAGE_TYPE_STRING = 1;
  
  public static String[] StatusMessages = new String[] { "No result", "OK", "Class not found", "Illegal access", "Instantiation error", "Malformed url", "IO error", "Invalid action", "JSON error", "Error" };
  
  private String encodedMessage;
  
  private boolean keepCallback;
  
  private final int messageType;
  
  private List<PluginResult> multipartMessages;
  
  private final int status;
  
  private String strMessage;
  
  public PluginResult(Status paramStatus) {
    this(paramStatus, StatusMessages[paramStatus.ordinal()]);
  }
  
  public PluginResult(Status paramStatus, float paramFloat) {
    this.keepCallback = false;
    this.status = paramStatus.ordinal();
    this.messageType = 3;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("");
    stringBuilder.append(paramFloat);
    this.encodedMessage = stringBuilder.toString();
  }
  
  public PluginResult(Status paramStatus, int paramInt) {
    this.keepCallback = false;
    this.status = paramStatus.ordinal();
    this.messageType = 3;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("");
    stringBuilder.append(paramInt);
    this.encodedMessage = stringBuilder.toString();
  }
  
  public PluginResult(Status paramStatus, String paramString) {
    boolean bool;
    this.keepCallback = false;
    this.status = paramStatus.ordinal();
    if (paramString == null) {
      bool = true;
    } else {
      bool = true;
    } 
    this.messageType = bool;
    this.strMessage = paramString;
  }
  
  public PluginResult(Status paramStatus, List<PluginResult> paramList) {
    this.keepCallback = false;
    this.status = paramStatus.ordinal();
    this.messageType = 8;
    this.multipartMessages = paramList;
  }
  
  public PluginResult(Status paramStatus, JSONArray paramJSONArray) {
    this.keepCallback = false;
    this.status = paramStatus.ordinal();
    this.messageType = 2;
    this.encodedMessage = paramJSONArray.toString();
  }
  
  public PluginResult(Status paramStatus, JSONObject paramJSONObject) {
    this.keepCallback = false;
    this.status = paramStatus.ordinal();
    this.messageType = 2;
    this.encodedMessage = paramJSONObject.toString();
  }
  
  public PluginResult(Status paramStatus, boolean paramBoolean) {
    this.keepCallback = false;
    this.status = paramStatus.ordinal();
    this.messageType = 4;
    this.encodedMessage = Boolean.toString(paramBoolean);
  }
  
  public PluginResult(Status paramStatus, byte[] paramArrayOfbyte) {
    this(paramStatus, paramArrayOfbyte, false);
  }
  
  public PluginResult(Status paramStatus, byte[] paramArrayOfbyte, boolean paramBoolean) {
    byte b;
    this.keepCallback = false;
    this.status = paramStatus.ordinal();
    if (paramBoolean) {
      b = 7;
    } else {
      b = 6;
    } 
    this.messageType = b;
    this.encodedMessage = Base64.encodeToString(paramArrayOfbyte, 2);
  }
  
  @Deprecated
  public String getJSONString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{\"status\":");
    stringBuilder.append(this.status);
    stringBuilder.append(",\"message\":");
    stringBuilder.append(getMessage());
    stringBuilder.append(",\"keepCallback\":");
    stringBuilder.append(this.keepCallback);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public boolean getKeepCallback() {
    return this.keepCallback;
  }
  
  public String getMessage() {
    if (this.encodedMessage == null)
      this.encodedMessage = JSONObject.quote(this.strMessage); 
    return this.encodedMessage;
  }
  
  public int getMessageType() {
    return this.messageType;
  }
  
  public PluginResult getMultipartMessage(int paramInt) {
    return this.multipartMessages.get(paramInt);
  }
  
  public int getMultipartMessagesSize() {
    return this.multipartMessages.size();
  }
  
  public int getStatus() {
    return this.status;
  }
  
  public String getStrMessage() {
    return this.strMessage;
  }
  
  public void setKeepCallback(boolean paramBoolean) {
    this.keepCallback = paramBoolean;
  }
  
  @Deprecated
  public String toCallbackString(String paramString) {
    return (this.status == Status.NO_RESULT.ordinal() && this.keepCallback) ? null : ((this.status == Status.OK.ordinal() || this.status == Status.NO_RESULT.ordinal()) ? toSuccessCallbackString(paramString) : toErrorCallbackString(paramString));
  }
  
  @Deprecated
  public String toErrorCallbackString(String paramString) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("cordova.callbackError('");
    stringBuilder.append(paramString);
    stringBuilder.append("', ");
    stringBuilder.append(getJSONString());
    stringBuilder.append(");");
    return stringBuilder.toString();
  }
  
  @Deprecated
  public String toSuccessCallbackString(String paramString) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("cordova.callbackSuccess('");
    stringBuilder.append(paramString);
    stringBuilder.append("',");
    stringBuilder.append(getJSONString());
    stringBuilder.append(");");
    return stringBuilder.toString();
  }
  
  public enum Status {
    CLASS_NOT_FOUND_EXCEPTION, ERROR, ILLEGAL_ACCESS_EXCEPTION, INSTANTIATION_EXCEPTION, INVALID_ACTION, IO_EXCEPTION, JSON_EXCEPTION, MALFORMED_URL_EXCEPTION, NO_RESULT, OK;
    
    static {
      IO_EXCEPTION = new Status("IO_EXCEPTION", 6);
      INVALID_ACTION = new Status("INVALID_ACTION", 7);
      JSON_EXCEPTION = new Status("JSON_EXCEPTION", 8);
      ERROR = new Status("ERROR", 9);
      $VALUES = new Status[] { NO_RESULT, OK, CLASS_NOT_FOUND_EXCEPTION, ILLEGAL_ACCESS_EXCEPTION, INSTANTIATION_EXCEPTION, MALFORMED_URL_EXCEPTION, IO_EXCEPTION, INVALID_ACTION, JSON_EXCEPTION, ERROR };
    }
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\org\apache\cordova\PluginResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */