package com.cndatacom.cnportalclient.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.cndatacom.cnportalclient.utils.AppInfoUtils;
import com.cndatacom.cnportalclient.utils.DateUtils;
import java.util.Date;

public class CollectionInfo implements Parcelable {
  public static final Parcelable.Creator<CollectionInfo> CREATOR = new Parcelable.Creator<CollectionInfo>() {
      public CollectionInfo createFromParcel(Parcel param1Parcel) {
        return new CollectionInfo(param1Parcel);
      }
      
      public CollectionInfo[] newArray(int param1Int) {
        return new CollectionInfo[param1Int];
      }
    };
  
  private String a = "";
  
  private String b = "";
  
  private String c = "";
  
  private String d = "";
  
  private String e = "";
  
  private String f = "";
  
  private String g = "";
  
  private String h = "";
  
  private String i = "";
  
  private String j = "";
  
  public CollectionInfo() {}
  
  protected CollectionInfo(Parcel paramParcel) {
    if (paramParcel != null) {
      this.a = paramParcel.readString();
      this.b = paramParcel.readString();
      this.c = paramParcel.readString();
      this.d = paramParcel.readString();
      this.e = paramParcel.readString();
      this.f = paramParcel.readString();
      this.g = paramParcel.readString();
      this.h = paramParcel.readString();
      this.i = paramParcel.readString();
      this.j = paramParcel.readString();
    } 
  }
  
  public static CollectionInfo madeDefCollectionInfo(Context paramContext, String paramString1, String paramString2, InnerState paramInnerState) {
    String str = AppInfoUtils.getCurrentProcessName();
    CollectionInfo collectionInfo = new CollectionInfo();
    collectionInfo.setId(paramString1);
    paramString1 = DateUtils.parse2YYYYMMDDHHMMSS(new Date());
    collectionInfo.setStarttime(paramString1);
    collectionInfo.setStoptime(paramString1);
    collectionInfo.setAccount(paramString2);
    collectionInfo.setClientid(paramInnerState.getClientId());
    collectionInfo.setCid(paramInnerState.getSchoolId(""));
    collectionInfo.setExitcode("1001");
    collectionInfo.setOstype("4");
    collectionInfo.setProcess(str);
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(AppInfoUtils.getVersionCode(paramContext));
    stringBuilder.append("");
    collectionInfo.setVersion(stringBuilder.toString());
    return collectionInfo;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public String getAccount() {
    return this.b;
  }
  
  public String getCid() {
    return this.d;
  }
  
  public String getClientid() {
    return this.c;
  }
  
  public String getExitcode() {
    return this.h;
  }
  
  public String getId() {
    return this.a;
  }
  
  public String getOstype() {
    return this.e;
  }
  
  public String getProcess() {
    return this.g;
  }
  
  public String getStarttime() {
    return this.i;
  }
  
  public String getStoptime() {
    return this.j;
  }
  
  public String getVersion() {
    return this.f;
  }
  
  public void setAccount(String paramString) {
    this.b = paramString;
  }
  
  public void setCid(String paramString) {
    this.d = paramString;
  }
  
  public void setClientid(String paramString) {
    this.c = paramString;
  }
  
  public void setExitcode(String paramString) {
    this.h = paramString;
  }
  
  public void setId(String paramString) {
    this.a = paramString;
  }
  
  public void setOstype(String paramString) {
    this.e = paramString;
  }
  
  public void setProcess(String paramString) {
    this.g = paramString;
  }
  
  public void setStarttime(String paramString) {
    this.i = paramString;
  }
  
  public void setStoptime(String paramString) {
    this.j = paramString;
  }
  
  public void setVersion(String paramString) {
    this.f = paramString;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    if (paramParcel != null) {
      paramParcel.writeString(this.a);
      paramParcel.writeString(this.b);
      paramParcel.writeString(this.c);
      paramParcel.writeString(this.d);
      paramParcel.writeString(this.e);
      paramParcel.writeString(this.f);
      paramParcel.writeString(this.g);
      paramParcel.writeString(this.h);
      paramParcel.writeString(this.i);
      paramParcel.writeString(this.j);
    } 
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\model\CollectionInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */