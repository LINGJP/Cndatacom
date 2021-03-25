package com.cndatacom.cnportalclient.notify;

import com.cndatacom.campus.netcore.DaMod;
import com.cndatacom.cnportalclient.model.InnerState;
import com.cndatacom.cnportalclient.utils.ExLog;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class CDCMessageEntity {
  private final int a = 4;
  
  public short cmdType;
  
  public short cryptType;
  
  public ByteBuffer data = null;
  
  public int dataLength;
  
  void a(byte[] paramArrayOfbyte) {
    if (paramArrayOfbyte == null)
      return; 
    this.data = ByteBuffer.allocate(paramArrayOfbyte.length);
    this.data.put(paramArrayOfbyte);
  }
  
  public byte[] bytes() {
    this.dataLength = 0;
    byte[] arrayOfByte2 = this.data.array();
    byte[] arrayOfByte1 = arrayOfByte2;
    if (arrayOfByte2 != null) {
      arrayOfByte1 = arrayOfByte2;
      if (1 == this.cryptType) {
        DaMod daMod = InnerState.load().getDaMod();
        if (daMod == null || !daMod.isOk()) {
          arrayOfByte1 = "00000000-0000-0000-0000-000000000000".getBytes();
          arrayOfByte2 = "00000000-0000-0000-0000-000000000000".getBytes();
        } else {
          arrayOfByte2 = arrayOfByte1.Encode(this.data.array());
          arrayOfByte1.Encode(arrayOfByte2);
          arrayOfByte1 = arrayOfByte2;
          if (arrayOfByte2 == null)
            arrayOfByte1 = this.data.array(); 
          arrayOfByte2 = new byte[0];
        } 
        byte[] arrayOfByte = this.data.array();
        StringBuilder stringBuilder2 = new StringBuilder();
        int i;
        for (i = 0; i < arrayOfByte.length; i++) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append(arrayOfByte[i]);
          stringBuilder.append(" ");
          stringBuilder2.append(stringBuilder.toString());
        } 
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append("CDCMessager 未加密Ticket ->");
        stringBuilder3.append(stringBuilder2);
        ExLog.d(new String[] { stringBuilder3.toString() });
        stringBuilder2 = new StringBuilder();
        for (i = 0; i < arrayOfByte2.length; i++) {
          stringBuilder3 = new StringBuilder();
          stringBuilder3.append(arrayOfByte2[i]);
          stringBuilder3.append(" ");
          stringBuilder2.append(stringBuilder3.toString());
        } 
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("CDCMessager 解密Ticket ->");
        stringBuilder1.append(stringBuilder2);
        ExLog.d(new String[] { stringBuilder1.toString() });
      } 
      this.dataLength += arrayOfByte1.length;
    } 
    ByteBuffer byteBuffer = ByteBuffer.allocate(this.dataLength + 4);
    byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
    byteBuffer.putShort((short)this.dataLength);
    byteBuffer.put((byte)(this.cryptType & 0xFF));
    byteBuffer.put((byte)(this.cmdType & 0xFF));
    byteBuffer.order(ByteOrder.BIG_ENDIAN);
    byteBuffer.put(arrayOfByte1);
    return byteBuffer.array();
  }
  
  public short getErrorCode() {
    if (this.data != null && (this.data.array()).length >= 2) {
      this.data.position(0);
      this.data.order(ByteOrder.LITTLE_ENDIAN);
      short s = this.data.getShort();
      this.data.order(ByteOrder.BIG_ENDIAN);
      return s;
    } 
    return 0;
  }
  
  public short getTime() {
    if (this.data != null && (this.data.array()).length >= 4) {
      this.data.position(2);
      this.data.order(ByteOrder.LITTLE_ENDIAN);
      short s = this.data.getShort();
      this.data.order(ByteOrder.BIG_ENDIAN);
      return s;
    } 
    return 0;
  }
  
  public int parse(byte[] paramArrayOfbyte, int paramInt) {
    if (paramInt < 4)
      return 0; 
    ByteBuffer byteBuffer = ByteBuffer.allocate(paramInt);
    byteBuffer.put(paramArrayOfbyte);
    byteBuffer.position(0);
    byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
    this.dataLength = byteBuffer.getShort();
    if (this.dataLength <= 1024) {
      if (this.dataLength > paramInt)
        return 0; 
      byteBuffer.order(ByteOrder.BIG_ENDIAN);
      paramInt = byteBuffer.getShort();
      this.cryptType = (short)(paramInt >>> 8 & 0xFF);
      this.cmdType = (short)(paramInt & 0xFF);
      paramArrayOfbyte = new byte[this.dataLength];
      byteBuffer.get(paramArrayOfbyte);
      this.data = ByteBuffer.allocate(this.dataLength);
      this.data.put(paramArrayOfbyte);
      return this.dataLength + 4;
    } 
    return 0;
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\notify\CDCMessageEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */