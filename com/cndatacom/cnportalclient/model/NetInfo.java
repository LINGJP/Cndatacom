package com.cndatacom.cnportalclient.model;

import android.net.DhcpInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.cndatacom.cnportalclient.utils.CdcUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class NetInfo implements Parcelable {
  public static final Parcelable.Creator<NetInfo> CREATOR = new Parcelable.Creator<NetInfo>() {
      public NetInfo createFromParcel(Parcel param1Parcel) {
        return new NetInfo(param1Parcel);
      }
      
      public NetInfo[] newArray(int param1Int) {
        return new NetInfo[param1Int];
      }
    };
  
  public static final int INVALID_RSSI = -127;
  
  public static final int LOW_RSSI = -76;
  
  public static final int MAX_RSSI = 200;
  
  public static final int MIN_RSSI = -126;
  
  private String a;
  
  private String b;
  
  private String c;
  
  private String d;
  
  private int e;
  
  private int f;
  
  private int g;
  
  private String h;
  
  private String i;
  
  private String j;
  
  private int k;
  
  public NetInfo() {
    this.a = "";
    this.b = "";
    this.c = "";
    this.d = "";
    this.e = -127;
    this.f = -1;
    this.g = -1;
    this.h = "";
    this.i = "";
    this.j = "";
  }
  
  public NetInfo(WifiManager paramWifiManager) {
    String str;
    this.a = "";
    this.b = "";
    this.c = "";
    this.d = "";
    this.e = -127;
    this.f = -1;
    this.g = -1;
    this.h = "";
    this.i = "";
    this.j = "";
    WifiInfo wifiInfo = paramWifiManager.getConnectionInfo();
    this.e = wifiInfo.getRssi();
    this.f = wifiInfo.getLinkSpeed();
    this.g = wifiInfo.getNetworkId();
    this.a = wifiInfo.getSSID();
    if (TextUtils.isEmpty(this.a) || "<unknown ssid>".equals(this.a)) {
      this.a = "";
      List list = paramWifiManager.getConfiguredNetworks();
      if (!list.isEmpty())
        for (WifiConfiguration wifiConfiguration : list) {
          if (wifiConfiguration.networkId == this.g && !TextUtils.isEmpty(wifiConfiguration.SSID) && !"<unknown ssid>".equals(wifiConfiguration.SSID)) {
            this.a = wifiConfiguration.SSID;
            break;
          } 
        }  
    } 
    while (this.a.startsWith("\""))
      this.a = this.a.substring(1); 
    while (this.a.endsWith("\""))
      this.a = this.a.substring(0, this.a.length() - 1); 
    this.h = wifiInfo.getBSSID();
    if ("02:00:00:00:00:00".equals(this.h))
      for (ScanResult scanResult : paramWifiManager.getScanResults()) {
        if (this.a.equals(scanResult.SSID)) {
          this.h = scanResult.BSSID;
          break;
        } 
      }  
    String[] arrayOfString = CdcUtils.getNetworkInfo();
    int i = wifiInfo.getIpAddress();
    if (i != 0) {
      str = CdcUtils.intToIp(i);
    } else {
      str = arrayOfString[0];
    } 
    this.c = str;
    this.d = arrayOfString[1];
    this.b = arrayOfString[2];
    DhcpInfo dhcpInfo = paramWifiManager.getDhcpInfo();
    if (dhcpInfo != null) {
      this.i = CdcUtils.intToIp(dhcpInfo.gateway);
      this.j = CdcUtils.intToIp(dhcpInfo.netmask);
      this.k = Integer.bitCount(dhcpInfo.netmask);
    } 
    if (TextUtils.isEmpty(this.i) || "0.0.0.0".equals(this.i))
      this.i = a(); 
    if (TextUtils.isEmpty(this.j) || "0.0.0.0".equals(this.j)) {
      String[] arrayOfString1 = CdcUtils.getIpAddrMaskForInterfaces();
      this.j = arrayOfString1[0];
      this.k = Integer.parseInt(arrayOfString1[1]);
    } 
  }
  
  public NetInfo(Parcel paramParcel) {
    this.a = "";
    this.b = "";
    this.c = "";
    this.d = "";
    this.e = -127;
    this.f = -1;
    this.g = -1;
    this.h = "";
    this.i = "";
    this.j = "";
    if (paramParcel != null) {
      this.a = paramParcel.readString();
      this.b = paramParcel.readString();
      this.c = paramParcel.readString();
      this.d = paramParcel.readString();
      this.e = paramParcel.readInt();
      this.f = paramParcel.readInt();
      this.g = paramParcel.readInt();
      this.h = paramParcel.readString();
    } 
  }
  
  private String a() {
    try {
      return (new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("ip route list table 0").getInputStream()))).readLine().split("\\s+")[2];
    } catch (IOException iOException) {
      iOException.printStackTrace();
      return "";
    } 
  }
  
  public boolean checkIP(String paramString) {
    return ((!TextUtils.isEmpty(getIpv4()) && getIpv4().equals(paramString)) || (!TextUtils.isEmpty(getIpv6()) && getIpv6().equals(paramString)));
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(NetInfo paramNetInfo) {
    return equalsNetInfo(paramNetInfo, paramNetInfo.getGateway());
  }
  
  public boolean equalsNetInfo(NetInfo paramNetInfo, String paramString) {
    boolean bool1;
    boolean bool = TextUtils.isEmpty(this.c);
    boolean bool4 = false;
    if (!bool && !TextUtils.isEmpty(paramNetInfo.c) && this.c.equals(paramNetInfo.c)) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    boolean bool2 = bool1;
    if (!TextUtils.isEmpty(this.d)) {
      bool2 = bool1;
      if (!TextUtils.isEmpty(paramNetInfo.d)) {
        bool2 = bool1;
        if (this.d.equals(paramNetInfo.d))
          bool2 = true; 
      } 
    } 
    if (!TextUtils.isEmpty(paramString) && !TextUtils.isEmpty(this.i) && this.i.equals(paramString)) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    boolean bool3 = bool1;
    if (!TextUtils.isEmpty(this.h)) {
      bool3 = bool1;
      if (!TextUtils.isEmpty(paramNetInfo.h)) {
        bool3 = bool1;
        if (this.h.equals(paramNetInfo.h)) {
          bool3 = bool1;
          if (!"02:00:00:00:00:00".equals(this.h))
            bool3 = true; 
        } 
      } 
    } 
    bool = bool4;
    if (bool2) {
      bool = bool4;
      if (bool3)
        bool = true; 
    } 
    return bool;
  }
  
  public String getGateway() {
    return this.i;
  }
  
  public String getIpv4() {
    return this.c;
  }
  
  public String getIpv6() {
    return this.d;
  }
  
  public int getLinkSpeed() {
    return this.f;
  }
  
  public String getMac() {
    return this.b;
  }
  
  public String getMask() {
    return this.j;
  }
  
  public int getMaskPrefixLength() {
    return this.k;
  }
  
  public int getNetId() {
    return this.g;
  }
  
  public int getRSSI() {
    return this.e;
  }
  
  public String getSSID() {
    return this.a;
  }
  
  public String getWifiMac() {
    return this.h;
  }
  
  public boolean isLowRSSI() {
    return (getRSSI() <= -76);
  }
  
  public void setGateway(String paramString) {
    this.i = paramString;
  }
  
  public void setIpv4(String paramString) {
    this.c = paramString;
  }
  
  public void setIpv6(String paramString) {
    this.d = paramString;
  }
  
  public void setMac(String paramString) {
    this.b = paramString;
  }
  
  public void setNetId(int paramInt) {
    this.g = paramInt;
  }
  
  public void setSsid(String paramString) {
    this.a = paramString;
  }
  
  public void setWifiMac(String paramString) {
    this.h = paramString;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.a);
    paramParcel.writeString(this.b);
    paramParcel.writeString(this.c);
    paramParcel.writeString(this.d);
    paramParcel.writeInt(this.e);
    paramParcel.writeInt(this.f);
    paramParcel.writeInt(this.g);
    paramParcel.writeString(this.h);
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\model\NetInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */