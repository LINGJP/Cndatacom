package com.cndatacom.cnportalclient.utils;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.text.TextUtils;
import android.util.Base64;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class CdcUtils {
  private static final char[] a = new char[] { 
      '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
      'a', 'b', 'c', 'd', 'e', 'f' };
  
  private static volatile WeakReference<Context> b;
  
  private static final Object c = new Object();
  
  private static SecretKey d;
  
  private static final byte[] e = new byte[] { 
      3, 92, 47, 111, 17, 115, 110, 84, 0, 29, 
      113, 40, 75, 116, 4, 99 };
  
  private static String a(int paramInt) {
    int[] arrayOfInt = new int[4];
    int i = 0;
    while (true) {
      int j = arrayOfInt.length;
      boolean bool = true;
      if (i < j) {
        arrayOfInt[arrayOfInt.length - 1 - i] = -1 << 32 - paramInt >> i * 8 & 0xFF;
        i++;
        continue;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("");
      stringBuilder.append(arrayOfInt[0]);
      String str = stringBuilder.toString();
      for (paramInt = bool; paramInt < arrayOfInt.length; paramInt++) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(str);
        stringBuilder1.append(".");
        stringBuilder1.append(arrayOfInt[paramInt]);
        str = stringBuilder1.toString();
      } 
      return str;
    } 
  }
  
  private static SecretKey a() {
    if (d == null)
      try {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(Build.FINGERPRINT.getBytes());
        messageDigest.update(e);
        messageDigest.update(Build.SERIAL.getBytes());
        d = new SecretKeySpec(messageDigest.digest(), "AES");
      } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
        ExLog.e(new String[] { "getCryptKey exception: ", noSuchAlgorithmException.getMessage() });
      }  
    return d;
  }
  
  public static String byteArrayToHex(byte[] paramArrayOfbyte) {
    if (paramArrayOfbyte == null)
      return ""; 
    StringBuilder stringBuilder = new StringBuilder(paramArrayOfbyte.length * 2 + 1);
    int j = paramArrayOfbyte.length;
    for (int i = 0; i < j; i++) {
      byte b = paramArrayOfbyte[i];
      stringBuilder.append(a[b >>> 4 & 0xF]);
      stringBuilder.append(a[b & 0xF]);
    } 
    return stringBuilder.toString();
  }
  
  public static boolean checkPermission(String paramString, Context paramContext) {
    return (ContextCompat.checkSelfPermission(paramContext, paramString) == 0 && PermissionChecker.checkSelfPermission(paramContext, paramString) == 0);
  }
  
  public static String decrypString(String paramString) {
    if (!TextUtils.isEmpty(paramString))
      try {
        return new String(decrypt(Base64.decode(paramString, 0)), "UTF-8");
      } catch (UnsupportedEncodingException unsupportedEncodingException) {
        ExLog.e(new String[] { "decrypString exception: ", unsupportedEncodingException.getMessage() });
      }  
    return "";
  }
  
  public static byte[] decrypt(byte[] paramArrayOfbyte) {
    try {
      Cipher cipher = Cipher.getInstance("AES");
      cipher.init(2, a());
      return cipher.doFinal(paramArrayOfbyte);
    } catch (Exception exception) {
      ExLog.e(new String[] { "decrypt exception: ", exception.getMessage() });
      return new byte[0];
    } 
  }
  
  public static boolean delFile(String paramString) {
    try {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(getContext().getFilesDir());
      stringBuilder.append(File.separator);
      stringBuilder.append(paramString);
      File file = new File(stringBuilder.toString());
      if (file.exists())
        file.getAbsoluteFile().delete(); 
      return true;
    } catch (Exception exception) {
      exception.printStackTrace();
      return false;
    } 
  }
  
  public static byte[] des3DecodeCBC(byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, byte[] paramArrayOfbyte3) {
    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    cipher.init(2, new SecretKeySpec(paramArrayOfbyte1, 0, 16, "AES"), new IvParameterSpec(paramArrayOfbyte2, 0, 16));
    return cipher.doFinal(paramArrayOfbyte3);
  }
  
  public static String des3DecodeECB(String paramString) {
    byte[] arrayOfByte = new byte[24];
    arrayOfByte[0] = -69;
    arrayOfByte[1] = 113;
    arrayOfByte[2] = 39;
    arrayOfByte[3] = -127;
    arrayOfByte[4] = -57;
    arrayOfByte[5] = 0;
    arrayOfByte[6] = 67;
    arrayOfByte[7] = 10;
    arrayOfByte[8] = -63;
    arrayOfByte[9] = -121;
    arrayOfByte[10] = -52;
    arrayOfByte[11] = -46;
    arrayOfByte[12] = -3;
    arrayOfByte[13] = -85;
    arrayOfByte[14] = 24;
    arrayOfByte[15] = 120;
    arrayOfByte[16] = 71;
    arrayOfByte[17] = -54;
    arrayOfByte[18] = -5;
    arrayOfByte[19] = 63;
    arrayOfByte[20] = -110;
    arrayOfByte[21] = 54;
    arrayOfByte[22] = 9;
    arrayOfByte[23] = -105;
    arrayOfByte[4] = (byte)(arrayOfByte[4] ^ 0xAA);
    arrayOfByte[1] = (byte)(arrayOfByte[1] ^ 0x15);
    arrayOfByte[19] = (byte)(arrayOfByte[19] ^ 0x50);
    arrayOfByte[20] = (byte)(arrayOfByte[20] ^ 0xDE);
    arrayOfByte[15] = (byte)(arrayOfByte[15] ^ 0x1F);
    arrayOfByte[9] = (byte)(arrayOfByte[9] ^ 0xDE);
    arrayOfByte[22] = (byte)(arrayOfByte[22] ^ 0x45);
    arrayOfByte[6] = (byte)(arrayOfByte[6] ^ 0x24);
    arrayOfByte[0] = (byte)(arrayOfByte[0] ^ 0x8A);
    arrayOfByte[7] = (byte)(arrayOfByte[7] ^ 0x6B);
    arrayOfByte[17] = (byte)(arrayOfByte[17] ^ 0xFB);
    arrayOfByte[8] = (byte)(arrayOfByte[8] ^ 0x93);
    arrayOfByte[5] = (byte)(arrayOfByte[5] ^ 0x34);
    arrayOfByte[12] = (byte)(arrayOfByte[12] ^ 0xB4);
    arrayOfByte[23] = (byte)(arrayOfByte[23] ^ 0xD5);
    arrayOfByte[13] = (byte)(arrayOfByte[13] ^ 0xE5);
    arrayOfByte[11] = (byte)(arrayOfByte[11] ^ 0x85);
    arrayOfByte[16] = (byte)(0x11 ^ arrayOfByte[16]);
    arrayOfByte[2] = (byte)(arrayOfByte[2] ^ 0x44);
    arrayOfByte[3] = (byte)(arrayOfByte[3] ^ 0xF3);
    arrayOfByte[10] = (byte)(arrayOfByte[10] ^ 0xF4);
    arrayOfByte[21] = (byte)(arrayOfByte[21] ^ 0x7E);
    arrayOfByte[18] = (byte)(arrayOfByte[18] ^ 0xB7);
    arrayOfByte[14] = (byte)(arrayOfByte[14] ^ 0x6B);
    return new String(des3DecodeECB(arrayOfByte, Base64.decode(paramString, 2)), "utf-8");
  }
  
  public static byte[] des3DecodeECB(byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2) {
    DESedeKeySpec dESedeKeySpec = new DESedeKeySpec(paramArrayOfbyte1);
    SecretKey secretKey = SecretKeyFactory.getInstance("desede").generateSecret(dESedeKeySpec);
    Cipher cipher = Cipher.getInstance("desede/ECB/PKCS5Padding");
    cipher.init(2, secretKey);
    return cipher.doFinal(paramArrayOfbyte2);
  }
  
  public static byte[] des3EncodeCBC(byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, byte[] paramArrayOfbyte3) {
    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    cipher.init(1, new SecretKeySpec(paramArrayOfbyte1, 0, 16, "AES"), new IvParameterSpec(paramArrayOfbyte2, 0, 16));
    return cipher.doFinal(paramArrayOfbyte3);
  }
  
  public static String des3EncodeECB(byte[] paramArrayOfbyte) {
    byte[] arrayOfByte = new byte[24];
    arrayOfByte[0] = -69;
    arrayOfByte[1] = 113;
    arrayOfByte[2] = 39;
    arrayOfByte[3] = -127;
    arrayOfByte[4] = -57;
    arrayOfByte[5] = 0;
    arrayOfByte[6] = 67;
    arrayOfByte[7] = 10;
    arrayOfByte[8] = -63;
    arrayOfByte[9] = -121;
    arrayOfByte[10] = -52;
    arrayOfByte[11] = -46;
    arrayOfByte[12] = -3;
    arrayOfByte[13] = -85;
    arrayOfByte[14] = 24;
    arrayOfByte[15] = 120;
    arrayOfByte[16] = 71;
    arrayOfByte[17] = -54;
    arrayOfByte[18] = -5;
    arrayOfByte[19] = 63;
    arrayOfByte[20] = -110;
    arrayOfByte[21] = 54;
    arrayOfByte[22] = 9;
    arrayOfByte[23] = -105;
    arrayOfByte[4] = (byte)(arrayOfByte[4] ^ 0xAA);
    arrayOfByte[1] = (byte)(arrayOfByte[1] ^ 0x15);
    arrayOfByte[19] = (byte)(arrayOfByte[19] ^ 0x50);
    arrayOfByte[20] = (byte)(arrayOfByte[20] ^ 0xDE);
    arrayOfByte[15] = (byte)(arrayOfByte[15] ^ 0x1F);
    arrayOfByte[9] = (byte)(arrayOfByte[9] ^ 0xDE);
    arrayOfByte[22] = (byte)(arrayOfByte[22] ^ 0x45);
    arrayOfByte[6] = (byte)(arrayOfByte[6] ^ 0x24);
    arrayOfByte[0] = (byte)(arrayOfByte[0] ^ 0x8A);
    arrayOfByte[7] = (byte)(arrayOfByte[7] ^ 0x6B);
    arrayOfByte[17] = (byte)(arrayOfByte[17] ^ 0xFB);
    arrayOfByte[8] = (byte)(arrayOfByte[8] ^ 0x93);
    arrayOfByte[5] = (byte)(arrayOfByte[5] ^ 0x34);
    arrayOfByte[12] = (byte)(arrayOfByte[12] ^ 0xB4);
    arrayOfByte[23] = (byte)(arrayOfByte[23] ^ 0xD5);
    arrayOfByte[13] = (byte)(arrayOfByte[13] ^ 0xE5);
    arrayOfByte[11] = (byte)(arrayOfByte[11] ^ 0x85);
    arrayOfByte[16] = (byte)(0x11 ^ arrayOfByte[16]);
    arrayOfByte[2] = (byte)(arrayOfByte[2] ^ 0x44);
    arrayOfByte[3] = (byte)(arrayOfByte[3] ^ 0xF3);
    arrayOfByte[10] = (byte)(arrayOfByte[10] ^ 0xF4);
    arrayOfByte[21] = (byte)(arrayOfByte[21] ^ 0x7E);
    arrayOfByte[18] = (byte)(arrayOfByte[18] ^ 0xB7);
    arrayOfByte[14] = (byte)(arrayOfByte[14] ^ 0x6B);
    return Base64.encodeToString(des3EncodeECB(arrayOfByte, paramArrayOfbyte), 2);
  }
  
  public static byte[] des3EncodeECB(byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2) {
    DESedeKeySpec dESedeKeySpec = new DESedeKeySpec(paramArrayOfbyte1);
    SecretKey secretKey = SecretKeyFactory.getInstance("desede").generateSecret(dESedeKeySpec);
    Cipher cipher = Cipher.getInstance("desede/ECB/PKCS5Padding");
    cipher.init(1, secretKey);
    return cipher.doFinal(paramArrayOfbyte2);
  }
  
  public static byte[] encryp(byte[] paramArrayOfbyte) {
    try {
      Cipher cipher = Cipher.getInstance("AES");
      cipher.init(1, a());
      return cipher.doFinal(paramArrayOfbyte);
    } catch (Exception exception) {
      ExLog.e(new String[] { "encryp exception: ", exception.getMessage() });
      return new byte[0];
    } 
  }
  
  public static String encrypString(String paramString) {
    if (!TextUtils.isEmpty(paramString))
      try {
        return Base64.encodeToString(encryp(paramString.getBytes("UTF-8")), 0);
      } catch (Exception exception) {
        ExLog.e(new String[] { "encrypString exception: ", exception.getMessage() });
      }  
    return "";
  }
  
  public static Context getContext() {
    synchronized (c) {
      Context context;
      if (b == null) {
        context = null;
      } else {
        context = b.get();
      } 
      return context;
    } 
  }
  
  public static String getIP() {
    String[] arrayOfString = getNetworkInfo();
    String str = "";
    if (!TextUtils.isEmpty(arrayOfString[0]))
      return arrayOfString[0]; 
    if (!TextUtils.isEmpty(arrayOfString[1]))
      str = arrayOfString[1]; 
    return str;
  }
  
  public static String[] getIpAddrMaskForInterfaces() {
    String[] arrayOfString = new String[2];
    arrayOfString[0] = "";
    arrayOfString[1] = "";
    try {
      Iterator<InterfaceAddress> iterator = NetworkInterface.getByName("wlan0").getInterfaceAddresses().iterator();
      while (iterator.hasNext()) {
        InterfaceAddress interfaceAddress = iterator.next();
        if (interfaceAddress.getAddress() instanceof java.net.Inet4Address) {
          arrayOfString[0] = a(interfaceAddress.getNetworkPrefixLength());
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append(interfaceAddress.getNetworkPrefixLength());
          stringBuilder.append("");
          arrayOfString[1] = stringBuilder.toString();
          return arrayOfString;
        } 
      } 
    } catch (SocketException socketException) {
      socketException.printStackTrace();
    } 
    return arrayOfString;
  }
  
  public static String[] getNetworkInfo() {
    String[] arrayOfString = new String[3];
    arrayOfString[0] = "";
    arrayOfString[1] = "";
    arrayOfString[2] = "";
    try {
      NetworkInterface networkInterface = NetworkInterface.getByName("wlan0");
      if (networkInterface == null)
        return arrayOfString; 
      byte[] arrayOfByte = networkInterface.getHardwareAddress();
      if (arrayOfByte != null) {
        if (arrayOfByte.length < 6)
          return arrayOfString; 
        Enumeration<InetAddress> enumeration = networkInterface.getInetAddresses();
        String str2 = "";
        String str1 = "";
        arrayOfString[2] = String.format("%02x:%02x:%02x:%02x:%02x:%02x", new Object[] { Byte.valueOf(arrayOfByte[0]), Byte.valueOf(arrayOfByte[1]), Byte.valueOf(arrayOfByte[2]), Byte.valueOf(arrayOfByte[3]), Byte.valueOf(arrayOfByte[4]), Byte.valueOf(arrayOfByte[5]) }).toLowerCase();
        byte b1 = (byte)(arrayOfByte[0] + 2);
        byte b2 = arrayOfByte[1];
        byte b3 = arrayOfByte[2];
        byte b4 = arrayOfByte[3];
        byte b5 = arrayOfByte[4];
        byte b6 = arrayOfByte[5];
        while (enumeration.hasMoreElements()) {
          InetAddress inetAddress = enumeration.nextElement();
          if (inetAddress.isLoopbackAddress() || inetAddress.isLinkLocalAddress() || inetAddress.isAnyLocalAddress() || inetAddress.isMulticastAddress())
            continue; 
          if (inetAddress instanceof java.net.Inet4Address) {
            if ("".equals(arrayOfString[0]))
              arrayOfString[0] = inetAddress.getHostAddress(); 
            continue;
          } 
          if (inetAddress instanceof java.net.Inet6Address) {
            byte[] arrayOfByte1 = new byte[8];
            System.arraycopy(inetAddress.getAddress(), 8, arrayOfByte1, 0, 8);
            if (Arrays.equals(new byte[] { b1, b2, b3, -1, -2, b4, b5, b6 }, arrayOfByte1)) {
              str = inetAddress.getHostAddress().toLowerCase();
              int j = str.indexOf('%');
              str2 = str;
              if (j > 0)
                str2 = str.substring(0, j); 
              continue;
            } 
            String str = str.getHostAddress().toLowerCase();
            int i = str.indexOf('%');
            str1 = str;
            if (i > 0)
              str1 = str.substring(0, i); 
          } 
        } 
        if ("".equals(arrayOfString[1])) {
          if (str1.length() > 0)
            str2 = str1; 
          arrayOfString[1] = str2;
          return arrayOfString;
        } 
      } else {
        return arrayOfString;
      } 
    } catch (Exception exception) {
      ExLog.e(new String[] { "NetInfo(WifiInfo) Exception: ", exception.getMessage() });
    } 
    return arrayOfString;
  }
  
  public static byte[] hexToByteArray(String paramString) {
    if (paramString == null)
      return null; 
    int j = paramString.length();
    int i = 0;
    if (j == 0)
      return new byte[0]; 
    byte[] arrayOfByte = new byte[(paramString.length() + 1) / 2];
    j = 0;
    while (i < paramString.length()) {
      int k = (byte)paramString.charAt(i);
      if (i % 2 == 0) {
        if (k >= 65) {
          k = (k & 0xDF) - 55;
        } else {
          k -= 48;
        } 
        arrayOfByte[j] = (byte)k;
      } else {
        arrayOfByte[j] = (byte)(arrayOfByte[j] << 4);
        byte b = arrayOfByte[j];
        if (k >= 65) {
          k = (k & 0xDF) - 55;
        } else {
          k -= 48;
        } 
        arrayOfByte[j] = (byte)((byte)k | b);
        j++;
      } 
      i++;
    } 
    return arrayOfByte;
  }
  
  public static String intToIp(int paramInt) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramInt & 0xFF);
    stringBuilder.append(".");
    stringBuilder.append(paramInt >> 8 & 0xFF);
    stringBuilder.append(".");
    stringBuilder.append(paramInt >> 16 & 0xFF);
    stringBuilder.append(".");
    stringBuilder.append(paramInt >> 24 & 0xFF);
    return stringBuilder.toString();
  }
  
  public static boolean isLocationServiceEnable(Context paramContext) {
    LocationManager locationManager = (LocationManager)paramContext.getSystemService("location");
    boolean bool1 = locationManager.isProviderEnabled("gps");
    boolean bool2 = locationManager.isProviderEnabled("network");
    return (bool1 || bool2);
  }
  
  public static boolean isNetworkAvailable(Context paramContext) {
    ConnectivityManager connectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
    boolean bool = false;
    if (connectivityManager != null) {
      NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
      if (networkInfo != null && networkInfo.isConnected()) {
        if (networkInfo.getState() == NetworkInfo.State.CONNECTED)
          bool = true; 
        return bool;
      } 
    } 
    return false;
  }
  
  public static boolean isWifiConnected(Context paramContext) {
    Context context = paramContext;
    if (paramContext == null)
      context = getContext(); 
    NetworkInfo networkInfo = ((ConnectivityManager)context.getSystemService("connectivity")).getNetworkInfo(1);
    return (networkInfo != null && networkInfo.isConnected() && networkInfo.isAvailable() && networkInfo.getState() == NetworkInfo.State.CONNECTED);
  }
  
  public static byte[] md5(byte[] paramArrayOfbyte) {
    try {
      return MessageDigest.getInstance("MD5").digest(paramArrayOfbyte);
    } catch (Exception exception) {
      ExLog.e(new String[] { "CdcUtils.md5 Exception: ", exception.getMessage() });
      return null;
    } 
  }
  
  public static File openFileDir(Context paramContext, String paramString) {
    String str2 = paramContext.getFilesDir().getPath();
    String str1 = str2;
    if (!TextUtils.isEmpty(paramString)) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str2);
      stringBuilder.append(File.separator);
      stringBuilder.append(paramString);
      str1 = stringBuilder.toString();
    } 
    File file = new File(str1);
    return (!file.exists() && !file.mkdirs()) ? null : file;
  }
  
  public static byte[] readFile(String paramString) {
    try {
      FileInputStream fileInputStream = getContext().openFileInput(paramString);
      byte[] arrayOfByte = new byte[fileInputStream.available()];
      fileInputStream.read(arrayOfByte);
      int i = arrayOfByte.length;
      fileInputStream.close();
      return arrayOfByte;
    } catch (FileNotFoundException fileNotFoundException) {
      return null;
    } 
  }
  
  public static String readFileText(String paramString) {
    try {
      return new String(readFile(paramString), "UTF-8");
    } catch (Exception exception) {
      return "";
    } 
  }
  
  public static void setContext(Context paramContext) {
    synchronized (c) {
      b = new WeakReference<Context>(paramContext);
      return;
    } 
  }
  
  public static int toInt(String paramString, int paramInt) {
    try {
      return Integer.valueOf(paramString).intValue();
    } catch (NumberFormatException numberFormatException) {
      return paramInt;
    } 
  }
  
  public static void writeFile(byte[] paramArrayOfbyte, File paramFile, boolean paramBoolean) {
    if (!paramFile.getParentFile().exists())
      paramFile.getParentFile().mkdirs(); 
    FileOutputStream fileOutputStream = new FileOutputStream(paramFile, paramBoolean);
    fileOutputStream.write(paramArrayOfbyte);
    fileOutputStream.flush();
    fileOutputStream.close();
  }
  
  public static void writeFile(byte[] paramArrayOfbyte, String paramString) {
    FileOutputStream fileOutputStream = getContext().openFileOutput(paramString, 0);
    fileOutputStream.write(paramArrayOfbyte);
    fileOutputStream.flush();
    fileOutputStream.close();
  }
  
  public static void writeFileText(String paramString1, String paramString2) {
    try {
      FileOutputStream fileOutputStream = getContext().openFileOutput(paramString2, 0);
      fileOutputStream.write(paramString1.getBytes("UTF-8"));
      fileOutputStream.flush();
      fileOutputStream.close();
      return;
    } catch (Exception exception) {
      exception.printStackTrace();
      return;
    } 
  }
  
  public static boolean writeFileText(String paramString, File paramFile, boolean paramBoolean) {
    try {
      writeFile(paramString.getBytes("UTF-8"), paramFile, paramBoolean);
      return true;
    } catch (Exception exception) {
      return false;
    } 
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclien\\utils\CdcUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */