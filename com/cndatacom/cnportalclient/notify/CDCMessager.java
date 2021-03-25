package com.cndatacom.cnportalclient.notify;

import com.cndatacom.cnportalclient.utils.CdcUtils;
import com.cndatacom.cnportalclient.utils.ExLog;
import java.io.ByteArrayOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class CDCMessager {
  ByteArrayOutputStream a = new ByteArrayOutputStream();
  
  private String b;
  
  private int c;
  
  private DatagramSocket d;
  
  private IMessageRecipient e;
  
  private boolean f = true;
  
  private int g = 0;
  
  public CDCMessager(String paramString, int paramInt) {
    this.b = paramString;
    this.c = paramInt;
    try {
      this.d = new DatagramSocket();
      return;
    } catch (Exception exception) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("CDCMessager Exception ->");
      stringBuilder.append(exception.getMessage());
      ExLog.e(new String[] { stringBuilder.toString() });
      return;
    } 
  }
  
  private void a(byte[] paramArrayOfbyte, int paramInt) {
    this.a.write(paramArrayOfbyte, this.g, paramInt);
    this.g += paramInt;
    do {
      CDCMessageEntity cDCMessageEntity = new CDCMessageEntity();
      paramInt = cDCMessageEntity.parse(this.a.toByteArray(), this.g);
      if (paramInt > 0)
        this.e.onRecv(cDCMessageEntity); 
      this.g -= paramInt;
      if (this.g == 0) {
        this.a.reset();
        return;
      } 
      byte[] arrayOfByte = (byte[])this.a.toByteArray().clone();
      this.a.reset();
      this.a.write(arrayOfByte, paramInt, this.g);
    } while (this.g > 0);
  }
  
  public void close() {
    this.f = false;
    if (this.d != null) {
      this.d.close();
      this.d = null;
    } 
  }
  
  public void receive(IMessageRecipient paramIMessageRecipient) {
    this.e = paramIMessageRecipient;
    byte[] arrayOfByte = new byte[1024];
    DatagramPacket datagramPacket = new DatagramPacket(arrayOfByte, arrayOfByte.length);
    while (this.f) {
      try {
        ExLog.i(new String[] { "receive start" });
        this.d.receive(datagramPacket);
        if (datagramPacket.getLength() == 0)
          return; 
        a(datagramPacket.getData(), datagramPacket.getLength());
        ExLog.i(new String[] { "receive continue" });
      } catch (Exception exception) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("receive Exception ->");
        stringBuilder.append(exception.getMessage());
        ExLog.e(new String[] { stringBuilder.toString() });
      } 
    } 
  }
  
  public void send(short paramShort1, short paramShort2, String paramString) {
    CDCMessageEntity cDCMessageEntity = new CDCMessageEntity();
    cDCMessageEntity.cryptType = paramShort1;
    cDCMessageEntity.cmdType = paramShort2;
    cDCMessageEntity.a(CdcUtils.hexToByteArray(paramString));
    byte[] arrayOfByte = cDCMessageEntity.bytes();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("sendMessage encrypt : ");
    stringBuilder.append(paramShort1);
    stringBuilder.append(" cmd : ");
    stringBuilder.append(paramShort2);
    ExLog.i(new String[] { stringBuilder.toString() });
    try {
      InetSocketAddress inetSocketAddress = new InetSocketAddress(InetAddress.getByName(this.b), this.c);
      DatagramPacket datagramPacket = new DatagramPacket(arrayOfByte, arrayOfByte.length, inetSocketAddress);
      this.d.send(datagramPacket);
      return;
    } catch (Exception exception) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("sendMessage Exception ->");
      stringBuilder.append(exception.getMessage());
      ExLog.e(new String[] { stringBuilder.toString() });
      return;
    } 
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\notify\CDCMessager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */