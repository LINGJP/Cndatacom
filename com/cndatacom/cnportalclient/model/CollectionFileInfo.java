package com.cndatacom.cnportalclient.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.cndatacom.cnportalclient.utils.CdcUtils;
import com.cndatacom.cnportalclient.utils.ExLog;
import java.util.Vector;

public class CollectionFileInfo implements Parcelable {
  public static final Parcelable.Creator<CollectionFileInfo> CREATOR;
  
  public static final String FILEPATH_APP_INFO = "appinfo";
  
  public static final String FILEPATH_HOLD_INFO = "holdinfo";
  
  public static final String FILEPATH_UPLOAD_INFO = "uploadinfo";
  
  private static byte[] a = new byte[1];
  
  private Vector<CollectionInfo> b = new Vector<CollectionInfo>();
  
  static {
    CREATOR = new Parcelable.Creator<CollectionFileInfo>() {
        public CollectionFileInfo createFromParcel(Parcel param1Parcel) {
          return new CollectionFileInfo(param1Parcel);
        }
        
        public CollectionFileInfo[] newArray(int param1Int) {
          return new CollectionFileInfo[param1Int];
        }
      };
  }
  
  protected CollectionFileInfo(Parcel paramParcel) {
    if (paramParcel != null)
      paramParcel.readTypedList(this.b, CollectionInfo.CREATOR); 
  }
  
  public static CollectionFileInfo load(String paramString) {
    byte[] arrayOfByte = a;
    /* monitor enter ClassFileLocalVariableReferenceExpression{type=ObjectType{B, dimension=1}, name=null} */
    String str = null;
    try {
      StringBuilder stringBuilder;
      byte[] arrayOfByte1 = CdcUtils.readFile(paramString);
      if (arrayOfByte1 == null) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("CollectionFileInfo.load readFile => filePath:");
        stringBuilder.append(paramString);
        stringBuilder.append(" null");
        ExLog.i(new String[] { stringBuilder.toString() });
        paramString = str;
      } else {
        Parcel parcel = Parcel.obtain();
        parcel.unmarshall(CdcUtils.decrypt((byte[])stringBuilder), 0, stringBuilder.length);
        parcel.setDataPosition(0);
      } 
    } catch (Exception exception) {
      ExLog.e(new String[] { "CollectionFileInfo.load exception: ", exception.getMessage() });
      String str1 = str;
    } finally {}
    /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{B, dimension=1}, name=null} */
    return (CollectionFileInfo)CREATOR.createFromParcel((Parcel)paramString);
  }
  
  public CollectionFileInfo addCollectionInfo(CollectionInfo paramCollectionInfo) {
    if (this.b == null)
      this.b = new Vector<CollectionInfo>(); 
    this.b.add(paramCollectionInfo);
    print("addCollectionInfo", this.b);
    return this;
  }
  
  public CollectionFileInfo appendCollectionInfos(Vector<CollectionInfo> paramVector) {
    if (this.b == null)
      this.b = new Vector<CollectionInfo>(); 
    this.b.addAll(paramVector);
    print("appendCollectionInfos", this.b);
    return this;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public Vector<CollectionInfo> getCollectionInfos() {
    return this.b;
  }
  
  public void print(CollectionInfo paramCollectionInfo) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(" collection info -> id:");
    stringBuilder.append(paramCollectionInfo.getId());
    stringBuilder.append(" account:");
    stringBuilder.append(paramCollectionInfo.getAccount());
    stringBuilder.append(" cid:");
    stringBuilder.append(paramCollectionInfo.getCid());
    stringBuilder.append(" clientid:");
    stringBuilder.append(paramCollectionInfo.getClientid());
    stringBuilder.append(" exitcode:");
    stringBuilder.append(paramCollectionInfo.getExitcode());
    stringBuilder.append(" process:");
    stringBuilder.append(paramCollectionInfo.getProcess());
    stringBuilder.append(" starttime:");
    stringBuilder.append(paramCollectionInfo.getStarttime());
    stringBuilder.append(" stoptime:");
    stringBuilder.append(paramCollectionInfo.getStoptime());
    stringBuilder.append(" version:");
    stringBuilder.append(paramCollectionInfo.getVersion());
    ExLog.d(new String[] { stringBuilder.toString() });
  }
  
  public void print(String paramString, Vector<CollectionInfo> paramVector) {
    if (paramVector != null && paramVector.size() > 0)
      for (int i = 0; i < paramVector.size(); i++) {
        CollectionInfo collectionInfo = paramVector.get(i);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(paramString);
        stringBuilder.append(" collection info -> id:");
        stringBuilder.append(collectionInfo.getId());
        stringBuilder.append(" account:");
        stringBuilder.append(collectionInfo.getAccount());
        stringBuilder.append(" cid:");
        stringBuilder.append(collectionInfo.getCid());
        stringBuilder.append(" clientid:");
        stringBuilder.append(collectionInfo.getClientid());
        stringBuilder.append(" exitcode:");
        stringBuilder.append(collectionInfo.getExitcode());
        stringBuilder.append(" process:");
        stringBuilder.append(collectionInfo.getProcess());
        stringBuilder.append(" starttime:");
        stringBuilder.append(collectionInfo.getStarttime());
        stringBuilder.append(" stoptime:");
        stringBuilder.append(collectionInfo.getStoptime());
        stringBuilder.append(" version:");
        stringBuilder.append(collectionInfo.getVersion());
        ExLog.d(new String[] { stringBuilder.toString() });
      }  
  }
  
  public CollectionFileInfo save(String paramString) {
    Parcel parcel = Parcel.obtain();
    writeToParcel(parcel, 1);
    byte[] arrayOfByte = a;
    /* monitor enter ClassFileLocalVariableReferenceExpression{type=ObjectType{B, dimension=1}, name=null} */
    try {
      CdcUtils.writeFile(CdcUtils.encryp(parcel.marshall()), paramString);
    } catch (Exception exception) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(exception.getMessage());
      stringBuilder.append(" filePath->");
      stringBuilder.append(paramString);
      ExLog.e(new String[] { "CollectionFileInfo.save Exception: ", stringBuilder.toString() });
    } finally {}
    /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{B, dimension=1}, name=null} */
    return this;
  }
  
  public CollectionFileInfo setCollectionInfos(Vector<CollectionInfo> paramVector) {
    this.b = paramVector;
    return this;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    if (paramParcel != null) {
      print("updateCollectionInfo writeToParcel", this.b);
      paramParcel.writeTypedList(this.b);
    } 
  }
  
  public static interface CallBack {
    void uploadSuccess();
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\model\CollectionFileInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */