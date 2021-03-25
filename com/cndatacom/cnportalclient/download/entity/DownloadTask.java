package com.cndatacom.cnportalclient.download.entity;

import android.os.Parcel;
import android.os.Parcelable;
import com.cndatacom.cnportalclient.download.thread.DownloadTaskThreadManager;
import java.util.Date;

public class DownloadTask implements Parcelable {
  public static final Parcelable.Creator<DownloadTask> CREATOR = new Parcelable.Creator<DownloadTask>() {
      public DownloadTask createFromParcel(Parcel param1Parcel) {
        return new DownloadTask(param1Parcel);
      }
      
      public DownloadTask[] newArray(int param1Int) {
        return new DownloadTask[param1Int];
      }
    };
  
  public static final int STATUS_CANCEL = 4;
  
  public static final int STATUS_DOWNLOADING = 2;
  
  public static final int STATUS_ERROR = 5;
  
  public static final int STATUS_FINISH = 6;
  
  public static final int STATUS_INT = 0;
  
  public static final int STATUS_PAUSE = 3;
  
  public static final int STATUS_WAIT = 1;
  
  private String a;
  
  private String b;
  
  private String c;
  
  private String d;
  
  private String e;
  
  private String f;
  
  private String g;
  
  private Date h;
  
  private int i = 0;
  
  private int j;
  
  private long k;
  
  private DownloadTaskThreadManager l;
  
  public DownloadTask() {}
  
  protected DownloadTask(Parcel paramParcel) {
    this.a = paramParcel.readString();
    this.b = paramParcel.readString();
    this.c = paramParcel.readString();
    this.d = paramParcel.readString();
    this.e = paramParcel.readString();
    this.f = paramParcel.readString();
    this.g = paramParcel.readString();
    this.i = paramParcel.readInt();
    this.j = paramParcel.readInt();
    this.k = paramParcel.readLong();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public Date getCreateTime() {
    return this.h;
  }
  
  public DownloadTaskThreadManager getDownloadTaskThreadManager() {
    return this.l;
  }
  
  public String getRootPath() {
    return this.f;
  }
  
  public int getStatus() {
    return this.i;
  }
  
  public String getTaskExt() {
    return this.c;
  }
  
  public long getTaskFileSize() {
    return this.k;
  }
  
  public String getTaskId() {
    return this.a;
  }
  
  public String getTaskName() {
    return this.b;
  }
  
  public String getTaskPath() {
    return this.d;
  }
  
  public String getTaskTempPath() {
    return this.e;
  }
  
  public String getTaskUrl() {
    return this.g;
  }
  
  public int getThreadNum() {
    return this.j;
  }
  
  public void setCreateTime(Date paramDate) {
    this.h = paramDate;
  }
  
  public void setDownloadTaskThreadManager(DownloadTaskThreadManager paramDownloadTaskThreadManager) {
    this.l = paramDownloadTaskThreadManager;
  }
  
  public void setRootPath(String paramString) {
    this.f = paramString;
  }
  
  public void setStatus(int paramInt) {
    this.i = paramInt;
  }
  
  public void setTaskExt(String paramString) {
    this.c = paramString;
  }
  
  public void setTaskFileSize(long paramLong) {
    this.k = paramLong;
  }
  
  public void setTaskId(String paramString) {
    this.a = paramString;
  }
  
  public void setTaskName(String paramString) {
    this.b = paramString;
  }
  
  public void setTaskPath(String paramString) {
    this.d = paramString;
  }
  
  public void setTaskTempPath(String paramString) {
    this.e = paramString;
  }
  
  public void setTaskUrl(String paramString) {
    this.g = paramString;
  }
  
  public void setThreadNum(int paramInt) {
    this.j = paramInt;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.a);
    paramParcel.writeString(this.b);
    paramParcel.writeString(this.c);
    paramParcel.writeString(this.d);
    paramParcel.writeString(this.e);
    paramParcel.writeString(this.f);
    paramParcel.writeString(this.g);
    paramParcel.writeInt(this.i);
    paramParcel.writeInt(this.j);
    paramParcel.writeLong(this.k);
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\download\entity\DownloadTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */