package com.cndatacom.cnportalclient.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MessageResult implements Parcelable {
  public static final int ACTION_GET_LOGIN_INFO = 3;
  
  public static final int ACTION_LOGIN = 1;
  
  public static final int ACTION_STOP_SERVICE = 2;
  
  public static final int ACTION_STOP_SERVICE_AND_TERM = 4;
  
  public static final int ACTION_UPLOAD_STOP_SERVICE = 5;
  
  public static final int ACTION_VPN_DO_KEEP = 6;
  
  public static final Parcelable.Creator<MessageResult> CREATOR = new Parcelable.Creator<MessageResult>() {
      public MessageResult createFromParcel(Parcel param1Parcel) {
        return new MessageResult(param1Parcel);
      }
      
      public MessageResult[] newArray(int param1Int) {
        return new MessageResult[param1Int];
      }
    };
  
  public static final int LOGIN_ERROR = 13;
  
  public static final int LOGIN_ERROR_AND_CHECKNET = 14;
  
  public static final int LOGIN_PROGRESS = 10;
  
  public static final int LOGIN_SUCCESS = 11;
  
  public static final int LOGIN_TICKET = 16;
  
  public static final int LOGIN_VERIFY = 15;
  
  public static final int NONE = -1;
  
  public static final int SELF_LOGIN_SUCCESS = 12;
  
  private int a = -1;
  
  private String b;
  
  public MessageResult() {}
  
  protected MessageResult(Parcel paramParcel) {
    this.a = paramParcel.readInt();
    this.b = paramParcel.readString();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public String getData() {
    return this.b;
  }
  
  public int getType() {
    return this.a;
  }
  
  public void setData(String paramString) {
    this.b = paramString;
  }
  
  public void setType(int paramInt) {
    this.a = paramInt;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.a);
    paramParcel.writeString(this.b);
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\model\MessageResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */