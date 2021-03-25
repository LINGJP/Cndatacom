package com.cndatacom.cnportalclient.share;

import android.os.Parcel;
import android.os.Parcelable;

public class ShareAppInfo implements Parcelable {
  public static final Parcelable.Creator<ShareAppInfo> CREATOR = new Parcelable.Creator<ShareAppInfo>() {
      public ShareAppInfo createFromParcel(Parcel param1Parcel) {
        return new ShareAppInfo(param1Parcel);
      }
      
      public ShareAppInfo[] newArray(int param1Int) {
        return new ShareAppInfo[param1Int];
      }
    };
  
  String a = "";
  
  String b = "";
  
  String c = "";
  
  String d = "";
  
  String e = "";
  
  public ShareAppInfo() {}
  
  protected ShareAppInfo(Parcel paramParcel) {
    this.a = paramParcel.readString();
    this.b = paramParcel.readString();
    this.c = paramParcel.readString();
    this.d = paramParcel.readString();
    this.e = paramParcel.readString();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public String getAppName() {
    return this.b;
  }
  
  public String getNameContains() {
    return this.c;
  }
  
  public String getPackagename() {
    return this.a;
  }
  
  public String getPackagenameContains() {
    return this.d;
  }
  
  public String getWifinameContains() {
    return this.e;
  }
  
  public void setAppName(String paramString) {
    this.b = paramString;
  }
  
  public void setNameContains(String paramString) {
    this.c = paramString;
  }
  
  public void setPackagename(String paramString) {
    this.a = paramString;
  }
  
  public void setPackagenameContains(String paramString) {
    this.d = paramString;
  }
  
  public void setWifinameContains(String paramString) {
    this.e = paramString;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.a);
    paramParcel.writeString(this.b);
    paramParcel.writeString(this.c);
    paramParcel.writeString(this.d);
    paramParcel.writeString(this.e);
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\share\ShareAppInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */