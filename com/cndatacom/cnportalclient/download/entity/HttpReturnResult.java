package com.cndatacom.cnportalclient.download.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class HttpReturnResult implements Parcelable {
  public static final Parcelable.Creator<HttpReturnResult> CREATOR = new Parcelable.Creator<HttpReturnResult>() {
      public HttpReturnResult createFromParcel(Parcel param1Parcel) {
        return new HttpReturnResult(param1Parcel);
      }
      
      public HttpReturnResult[] newArray(int param1Int) {
        return new HttpReturnResult[param1Int];
      }
    };
  
  public static final String ERROR_FILE_ZERO = "文件长度为0";
  
  public static final String ERROR_MEMORY = "内存空间不足";
  
  public static final String ERROR_MSG_NET = "网络异常";
  
  public static final String ERROR_MSG_NONET = "无网络";
  
  public static final String ERROR_MSG_NOWIFI = "非WIFI环境";
  
  public static final String ERROR_MSG_NULLDATA = "数据为空";
  
  public static final String ERROR_MSG_NULLURL = "地址不存在";
  
  public static final String ERROR_MSG_PARSE = "数据解析出错";
  
  public static final String ERROR_MSG_SERVER = "服务器异常";
  
  public static final int STATUS_ERROR_NONET = -1;
  
  public static final int STATUS_ERROR_NOWIFI = -2;
  
  public static final int STATUS_ERROR_PARSE = -3;
  
  private int a;
  
  private Object b;
  
  public HttpReturnResult() {}
  
  public HttpReturnResult(Parcel paramParcel) {
    this.a = paramParcel.readInt();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public String getErrorMsg() {
    String str = "";
    if (!isSuccessful()) {
      switch (this.a) {
        default:
          if (400 <= this.a && this.a <= 600)
            return "服务器异常"; 
          break;
        case -1:
          return "无网络";
        case -2:
          return "非WIFI环境";
        case -3:
          return "数据解析出错";
      } 
      str = "网络异常";
    } 
    return str;
  }
  
  public Object getResult() {
    return this.b;
  }
  
  public boolean isSuccessful() {
    return (this.a >= 200 && this.a < 300);
  }
  
  public void setResult(Object paramObject) {
    this.b = paramObject;
  }
  
  public void setStatus(int paramInt) {
    this.a = paramInt;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.a);
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\download\entity\HttpReturnResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */