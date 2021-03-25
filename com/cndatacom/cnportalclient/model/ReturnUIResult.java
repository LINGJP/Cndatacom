package com.cndatacom.cnportalclient.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

public class ReturnUIResult implements Parcelable {
  public static final Parcelable.Creator<ReturnUIResult> CREATOR = new Parcelable.Creator<ReturnUIResult>() {
      public ReturnUIResult createFromParcel(Parcel param1Parcel) {
        return new ReturnUIResult(param1Parcel);
      }
      
      public ReturnUIResult[] newArray(int param1Int) {
        return new ReturnUIResult[param1Int];
      }
    };
  
  private String a = "";
  
  private String b = "";
  
  private String c = "";
  
  public ReturnUIResult() {}
  
  protected ReturnUIResult(Parcel paramParcel) {
    this.a = paramParcel.readString();
    this.b = paramParcel.readString();
    this.c = paramParcel.readString();
  }
  
  public ReturnUIResult(String paramString) {
    this.a = paramString;
  }
  
  public ReturnUIResult(String paramString1, String paramString2) {
    this.a = paramString1;
    this.b = paramString2;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public String getErrorCode() {
    return this.a;
  }
  
  public String getExtern() {
    return this.c;
  }
  
  public String getSubErrorCode() {
    return this.b;
  }
  
  public boolean isSuccess() {
    return (!TextUtils.isEmpty(this.a) && this.a.equals("0"));
  }
  
  public void setErrorCode(String paramString) {
    this.a = paramString;
  }
  
  public ReturnUIResult setExtern(String paramString) {
    this.c = paramString;
    return this;
  }
  
  public void setSubErrorCode(String paramString) {
    this.b = paramString;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.a);
    paramParcel.writeString(this.b);
    paramParcel.writeString(this.c);
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\model\ReturnUIResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */