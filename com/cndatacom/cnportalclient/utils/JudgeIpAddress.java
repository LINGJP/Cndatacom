package com.cndatacom.cnportalclient.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class JudgeIpAddress {
  public static boolean isIpv4(String paramString) {
    try {
      InetAddress inetAddress = InetAddress.getByName(paramString);
      return (inetAddress instanceof java.net.Inet6Address) ? false : ((inetAddress instanceof java.net.Inet4Address) ? true : true);
    } catch (UnknownHostException unknownHostException) {
      unknownHostException.printStackTrace();
      return false;
    } 
  }
  
  public static boolean isIpv6(String paramString) {
    try {
      InetAddress inetAddress = InetAddress.getByName(paramString);
      return (inetAddress instanceof java.net.Inet6Address) ? true : (!(inetAddress instanceof java.net.Inet4Address));
    } catch (UnknownHostException unknownHostException) {
      unknownHostException.printStackTrace();
      return false;
    } 
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclien\\utils\JudgeIpAddress.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */