package com.cndatacom.cnportalclient.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class IpUtil {
  private static StringBuilder a(int paramInt) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramInt % 2);
    for (paramInt /= 2; paramInt > 0; paramInt /= 2)
      stringBuilder.append(paramInt % 2); 
    return stringBuilder;
  }
  
  public static Long getBeginIpLong(String paramString1, String paramString2) {
    return Long.valueOf(getIpFromString(paramString1).longValue() & getIpFromString(getMaskByMaskBit(paramString2)).longValue());
  }
  
  public static String getBeginIpStr(String paramString1, String paramString2) {
    return getIpFromLong(getBeginIpLong(paramString1, paramString2));
  }
  
  public static Long getEndIpLong(String paramString1, String paramString2) {
    return Long.valueOf(getBeginIpLong(paramString1, paramString2).longValue() + (getIpFromString(getMaskByMaskBit(paramString2)).longValue() ^ 0xFFFFFFFFFFFFFFFFL));
  }
  
  public static String getEndIpStr(String paramString1, String paramString2) {
    return getIpFromLong(getEndIpLong(paramString1, paramString2));
  }
  
  public static int getIpCount(String paramString) {
    return BigDecimal.valueOf(Math.pow(2.0D, (32 - Integer.parseInt(paramString)))).setScale(0, 1).intValue();
  }
  
  public static String getIpFromLong(Long paramLong) {
    long l1 = (paramLong.longValue() & 0xFF000000L) / 16777216L;
    long l2 = (paramLong.longValue() & 0xFF0000L) / 65536L;
    long l3 = (paramLong.longValue() & 0xFF00L) / 256L;
    long l4 = paramLong.longValue();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(String.valueOf(l1));
    stringBuilder.append(".");
    stringBuilder.append(String.valueOf(l2));
    stringBuilder.append(".");
    stringBuilder.append(String.valueOf(l3));
    stringBuilder.append(".");
    stringBuilder.append(String.valueOf(l4 & 0xFFL));
    return stringBuilder.toString();
  }
  
  public static Long getIpFromString(String paramString) {
    long l1 = Long.valueOf(0L).longValue();
    long l2 = Long.parseLong(paramString.substring(0, paramString.indexOf('.')));
    paramString = paramString.substring(paramString.indexOf('.') + 1, paramString.length());
    l1 = Long.valueOf(l1 * 256L + l2).longValue();
    l2 = Long.parseLong(paramString.substring(0, paramString.indexOf('.')));
    paramString = paramString.substring(paramString.indexOf(".") + 1, paramString.length());
    l1 = Long.valueOf(l1 * 256L + l2).longValue();
    l2 = Long.parseLong(paramString.substring(0, paramString.indexOf('.')));
    paramString = paramString.substring(paramString.indexOf('.') + 1, paramString.length());
    return Long.valueOf(Long.valueOf(l1 * 256L + l2).longValue() * 256L + Long.parseLong(paramString));
  }
  
  public static String getMaskByMaskBit(String paramString) {
    return "".equals(paramString) ? "error, maskBit is null !" : getMaskMap(paramString);
  }
  
  public static String getMaskMap(String paramString) {
    return "1".equals(paramString) ? "128.0.0.0" : ("2".equals(paramString) ? "192.0.0.0" : ("3".equals(paramString) ? "224.0.0.0" : ("4".equals(paramString) ? "240.0.0.0" : ("5".equals(paramString) ? "248.0.0.0" : ("6".equals(paramString) ? "252.0.0.0" : ("7".equals(paramString) ? "254.0.0.0" : ("8".equals(paramString) ? "255.0.0.0" : ("9".equals(paramString) ? "255.128.0.0" : ("10".equals(paramString) ? "255.192.0.0" : ("11".equals(paramString) ? "255.224.0.0" : ("12".equals(paramString) ? "255.240.0.0" : ("13".equals(paramString) ? "255.248.0.0" : ("14".equals(paramString) ? "255.252.0.0" : ("15".equals(paramString) ? "255.254.0.0" : ("16".equals(paramString) ? "255.255.0.0" : ("17".equals(paramString) ? "255.255.128.0" : ("18".equals(paramString) ? "255.255.192.0" : ("19".equals(paramString) ? "255.255.224.0" : ("20".equals(paramString) ? "255.255.240.0" : ("21".equals(paramString) ? "255.255.248.0" : ("22".equals(paramString) ? "255.255.252.0" : ("23".equals(paramString) ? "255.255.254.0" : ("24".equals(paramString) ? "255.255.255.0" : ("25".equals(paramString) ? "255.255.255.128" : ("26".equals(paramString) ? "255.255.255.192" : ("27".equals(paramString) ? "255.255.255.224" : ("28".equals(paramString) ? "255.255.255.240" : ("29".equals(paramString) ? "255.255.255.248" : ("30".equals(paramString) ? "255.255.255.252" : ("31".equals(paramString) ? "255.255.255.254" : ("32".equals(paramString) ? "255.255.255.255" : "-1")))))))))))))))))))))))))))))));
  }
  
  public static int getNetMask(String paramString) {
    String[] arrayOfString = paramString.split("\\.");
    int i = 0;
    int j = 0;
    while (i < arrayOfString.length) {
      String str = a(Integer.parseInt(arrayOfString[i])).reverse().toString();
      int m = 0;
      int k = 0;
      while (m < str.length()) {
        m = str.indexOf('1', m);
        if (m == -1)
          break; 
        k++;
        m++;
      } 
      j += k;
      i++;
    } 
    return j;
  }
  
  public static int getPoolMax(int paramInt) {
    return (paramInt <= 0 || paramInt >= 32) ? 0 : ((int)Math.pow(2.0D, (32 - paramInt)) - 2);
  }
  
  public static String intToIP(int paramInt) {
    StringBuffer stringBuffer = new StringBuffer("");
    stringBuffer.append(String.valueOf(paramInt >>> 24));
    stringBuffer.append(".");
    stringBuffer.append(String.valueOf((0xFFFFFF & paramInt) >>> 16));
    stringBuffer.append(".");
    stringBuffer.append(String.valueOf((0xFFFF & paramInt) >>> 8));
    stringBuffer.append(".");
    stringBuffer.append(String.valueOf(paramInt & 0xFF));
    return stringBuffer.toString();
  }
  
  public static double ipToDouble(String paramString) {
    String[] arrayOfString = paramString.split("\\.");
    double d1 = Double.parseDouble(arrayOfString[0]);
    double d2 = Double.parseDouble(arrayOfString[1]);
    double d3 = Double.parseDouble(arrayOfString[2]);
    double d4 = Double.parseDouble(arrayOfString[3]);
    return d1 * Math.pow(256.0D, 3.0D) + d2 * Math.pow(256.0D, 2.0D) + d3 * 256.0D + d4;
  }
  
  public static int ipToInt(String paramString) {
    String[] arrayOfString = paramString.split("\\.");
    return (Integer.parseInt(arrayOfString[0]) << 24) + (Integer.parseInt(arrayOfString[1]) << 16) + (Integer.parseInt(arrayOfString[2]) << 8) + Integer.parseInt(arrayOfString[3]);
  }
  
  public static boolean isIP(String paramString) {
    return Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}").matcher(paramString).matches();
  }
  
  public static boolean isInRange(String paramString1, String paramString2) {
    String[] arrayOfString = paramString1.split("\\.");
    boolean bool = false;
    int i = Integer.parseInt(arrayOfString[0]);
    int j = Integer.parseInt(arrayOfString[1]);
    int k = Integer.parseInt(arrayOfString[2]);
    int m = Integer.parseInt(arrayOfString[3]);
    int n = -1 << 32 - Integer.parseInt(paramString2.replaceAll(".*/", ""));
    arrayOfString = paramString2.replaceAll("/.*", "").split("\\.");
    int i1 = Integer.parseInt(arrayOfString[0]);
    int i2 = Integer.parseInt(arrayOfString[1]);
    int i3 = Integer.parseInt(arrayOfString[2]);
    if (((m | i << 24 | j << 16 | k << 8) & n) == ((Integer.parseInt(arrayOfString[3]) | i3 << 8 | i1 << 24 | i2 << 16) & n))
      bool = true; 
    return bool;
  }
  
  public static List<String> parseAllIpMaskRange(String paramString1, String paramString2) {
    ArrayList<String> arrayList = new ArrayList();
    if ("32".equals(paramString2)) {
      arrayList.add(paramString1);
      return arrayList;
    } 
    return parseIpRange(getBeginIpStr(paramString1, paramString2), getEndIpStr(paramString1, paramString2));
  }
  
  public static List<String> parseIpMaskRange(String paramString1, String paramString2) {
    StringBuilder stringBuilder1;
    StringBuilder stringBuilder2;
    ArrayList<String> arrayList = new ArrayList();
    if ("32".equals(paramString2)) {
      arrayList.add(paramString1);
      return arrayList;
    } 
    String str = getBeginIpStr(paramString1, paramString2);
    paramString1 = getEndIpStr(paramString1, paramString2);
    if (!"31".equals(paramString2)) {
      StringBuilder stringBuilder3 = new StringBuilder();
      stringBuilder3.append(str.split("\\.")[0]);
      stringBuilder3.append(".");
      stringBuilder3.append(str.split("\\.")[1]);
      stringBuilder3.append(".");
      stringBuilder3.append(str.split("\\.")[2]);
      stringBuilder3.append(".");
      String str1 = stringBuilder3.toString();
      StringBuilder stringBuilder4 = new StringBuilder();
      stringBuilder4.append(paramString1.split("\\.")[0]);
      stringBuilder4.append(".");
      stringBuilder4.append(paramString1.split("\\.")[1]);
      stringBuilder4.append(".");
      stringBuilder4.append(paramString1.split("\\.")[2]);
      stringBuilder4.append(".");
      String str2 = stringBuilder4.toString();
      StringBuilder stringBuilder5 = new StringBuilder();
      stringBuilder5.append(str1);
      stringBuilder5.append(Integer.parseInt(str.split("\\.")[3]) + 1);
      str1 = stringBuilder5.toString();
      stringBuilder2 = new StringBuilder();
      stringBuilder2.append(str2);
      stringBuilder2.append(Integer.parseInt(paramString1.split("\\.")[3]) - 1);
      paramString1 = stringBuilder2.toString();
    } else {
      stringBuilder1 = stringBuilder2;
    } 
    return parseIpRange((String)stringBuilder1, paramString1);
  }
  
  public static List<String> parseIpRange(String paramString1, String paramString2) {
    ArrayList<String> arrayList = new ArrayList();
    String[] arrayOfString1 = paramString1.split("\\.");
    String[] arrayOfString2 = paramString2.split("\\.");
    int[] arrayOfInt1 = new int[4];
    int[] arrayOfInt2 = new int[4];
    int i;
    for (i = 0; i < 4; i++) {
      arrayOfInt1[i] = Integer.parseInt(arrayOfString1[i]);
      arrayOfInt2[i] = Integer.parseInt(arrayOfString2[i]);
    } 
    int j = arrayOfInt1[0];
    while (j <= arrayOfInt2[0]) {
      if (j == arrayOfInt1[0]) {
        i = arrayOfInt1[1];
      } else {
        i = 0;
      } 
      while (true) {
        int k;
        if (j == arrayOfInt2[0]) {
          k = arrayOfInt2[1];
        } else {
          k = 255;
        } 
        if (i <= k) {
          if (i == arrayOfInt1[1]) {
            k = arrayOfInt1[2];
          } else {
            k = 0;
          } 
          while (true) {
            int m;
            if (i == arrayOfInt2[1]) {
              m = arrayOfInt2[2];
            } else {
              m = 255;
            } 
            if (k <= m) {
              if (k == arrayOfInt1[2]) {
                m = arrayOfInt1[3];
              } else {
                m = 0;
              } 
              while (true) {
                char c;
                if (k == arrayOfInt2[2]) {
                  c = arrayOfInt2[3];
                } else {
                  c = 'ÿ';
                } 
                if (m <= c) {
                  StringBuilder stringBuilder = new StringBuilder();
                  stringBuilder.append(j);
                  stringBuilder.append(".");
                  stringBuilder.append(i);
                  stringBuilder.append(".");
                  stringBuilder.append(k);
                  stringBuilder.append(".");
                  stringBuilder.append(m);
                  arrayList.add(stringBuilder.toString());
                  m++;
                  continue;
                } 
                k++;
              } 
              break;
            } 
            i++;
          } 
          break;
        } 
        j++;
      } 
    } 
    return arrayList;
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclien\\utils\IpUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */