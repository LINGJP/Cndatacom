package androidx.versionedparcelable;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.RestrictTo;
import android.util.SparseIntArray;

@RestrictTo({RestrictTo.Scope.LIBRARY})
class b extends a {
  private final SparseIntArray a = new SparseIntArray();
  
  private final Parcel b;
  
  private final int c;
  
  private final int d;
  
  private final String e;
  
  private int f = -1;
  
  private int g = 0;
  
  b(Parcel paramParcel) {
    this(paramParcel, paramParcel.dataPosition(), paramParcel.dataSize(), "");
  }
  
  b(Parcel paramParcel, int paramInt1, int paramInt2, String paramString) {
    this.b = paramParcel;
    this.c = paramInt1;
    this.d = paramInt2;
    this.g = this.c;
    this.e = paramString;
  }
  
  private int d(int paramInt) {
    while (this.g < this.d) {
      this.b.setDataPosition(this.g);
      int i = this.b.readInt();
      int j = this.b.readInt();
      this.g += i;
      if (j == paramInt)
        return this.b.dataPosition(); 
    } 
    return -1;
  }
  
  public void a(int paramInt) {
    this.b.writeInt(paramInt);
  }
  
  public void a(Parcelable paramParcelable) {
    this.b.writeParcelable(paramParcelable, 0);
  }
  
  public void a(String paramString) {
    this.b.writeString(paramString);
  }
  
  public void a(byte[] paramArrayOfbyte) {
    if (paramArrayOfbyte != null) {
      this.b.writeInt(paramArrayOfbyte.length);
      this.b.writeByteArray(paramArrayOfbyte);
      return;
    } 
    this.b.writeInt(-1);
  }
  
  public void b() {
    if (this.f >= 0) {
      int i = this.a.get(this.f);
      int j = this.b.dataPosition();
      this.b.setDataPosition(i);
      this.b.writeInt(j - i);
      this.b.setDataPosition(j);
    } 
  }
  
  public boolean b(int paramInt) {
    paramInt = d(paramInt);
    if (paramInt == -1)
      return false; 
    this.b.setDataPosition(paramInt);
    return true;
  }
  
  protected a c() {
    int i;
    Parcel parcel = this.b;
    int j = this.b.dataPosition();
    if (this.g == this.c) {
      i = this.d;
    } else {
      i = this.g;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(this.e);
    stringBuilder.append("  ");
    return new b(parcel, j, i, stringBuilder.toString());
  }
  
  public void c(int paramInt) {
    b();
    this.f = paramInt;
    this.a.put(paramInt, this.b.dataPosition());
    a(0);
    a(paramInt);
  }
  
  public int d() {
    return this.b.readInt();
  }
  
  public String e() {
    return this.b.readString();
  }
  
  public byte[] f() {
    int i = this.b.readInt();
    if (i < 0)
      return null; 
    byte[] arrayOfByte = new byte[i];
    this.b.readByteArray(arrayOfByte);
    return arrayOfByte;
  }
  
  public <T extends Parcelable> T g() {
    return (T)this.b.readParcelable(getClass().getClassLoader());
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\androidx\versionedparcelable\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */