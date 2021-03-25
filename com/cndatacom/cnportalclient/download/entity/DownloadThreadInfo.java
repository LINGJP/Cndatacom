package com.cndatacom.cnportalclient.download.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class DownloadThreadInfo implements Parcelable {
  public static final Parcelable.Creator<DownloadThreadInfo> CREATOR = new Parcelable.Creator<DownloadThreadInfo>() {
      public DownloadThreadInfo createFromParcel(Parcel param1Parcel) {
        return new DownloadThreadInfo(param1Parcel);
      }
      
      public DownloadThreadInfo[] newArray(int param1Int) {
        return new DownloadThreadInfo[param1Int];
      }
    };
  
  private String a;
  
  private int b;
  
  private int c;
  
  private int d;
  
  public DownloadThreadInfo() {}
  
  protected DownloadThreadInfo(Parcel paramParcel) {
    this.a = paramParcel.readString();
    this.b = paramParcel.readInt();
    this.c = paramParcel.readInt();
    this.d = paramParcel.readInt();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public int getDownloadedSize() {
    return this.d;
  }
  
  public String getTaskId() {
    return this.a;
  }
  
  public int getThreadId() {
    return this.c;
  }
  
  public int getThreadNum() {
    return this.b;
  }
  
  public void setDownloadedSize(int paramInt) {
    this.d = paramInt;
  }
  
  public void setTaskId(String paramString) {
    this.a = paramString;
  }
  
  public void setThreadId(int paramInt) {
    this.c = paramInt;
  }
  
  public void setThreadNum(int paramInt) {
    this.b = paramInt;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.a);
    paramParcel.writeInt(this.b);
    paramParcel.writeInt(this.c);
    paramParcel.writeInt(this.d);
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\download\entity\DownloadThreadInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */