package com.a.a.d.a;

import com.a.a.b.e;
import java.text.DecimalFormat;
import java.text.NumberFormat;

final class b {
  private static final NumberFormat a = new DecimalFormat("000000000");
  
  private static final NumberFormat b = new DecimalFormat("000");
  
  private static final String[] c = new String[] { "\nABCDEFGHIJKLMNOPQRSTUVWXYZ￺\034\035\036￻ ￼\"#$%&'()*+,-./0123456789:￱￲￳￴￸", "`abcdefghijklmnopqrstuvwxyz￺\034\035\036￻{￼}~;<=>?[\\]^_ ,./:@!|￼￵￶￼￰￲￳￴￷", "ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖ×ØÙÚ￺\034\035\036ÛÜÝÞßª¬±²³µ¹º¼½¾￷ ￹￳￴￸", "àáâãäåæçèéêëìíîïðñòóôõö÷øùú￺\034\035\036￻ûüýþÿ¡¨«¯°´·¸»¿￷ ￲￹￴￸", "\000\001\002\003\004\005\006\007\b\t\n\013\f\r\016\017\020\021\022\023\024\025\026\027\030\031\032￺￼￼\033￻\034\035\036\037 ¢£¤¥¦§©­®¶￷ ￲￳￹￸", "\000\001\002\003\004\005\006\007\b\t\n\013\f\r\016\017\020\021\022\023\024\025\026\027\030\031\032\033\034\035\036\037 !\"#$%&'()*+,-./0123456789:;<=>?" };
  
  private static int a(int paramInt, byte[] paramArrayOfbyte) {
    int i = paramInt - 1;
    byte b1 = paramArrayOfbyte[i / 6];
    paramInt = 1;
    if ((1 << 5 - i % 6 & b1) == 0)
      paramInt = 0; 
    return paramInt;
  }
  
  private static int a(byte[] paramArrayOfbyte) {
    return a(paramArrayOfbyte, new byte[] { 53, 54, 43, 44, 45, 46, 47, 48, 37, 38 });
  }
  
  private static int a(byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2) {
    int i = 0;
    int j = 0;
    while (i < paramArrayOfbyte2.length) {
      j += a(paramArrayOfbyte2[i], paramArrayOfbyte1) << paramArrayOfbyte2.length - i - 1;
      i++;
    } 
    return j;
  }
  
  static e a(byte[] paramArrayOfbyte, int paramInt) {
    String str1;
    StringBuilder stringBuilder1 = new StringBuilder(144);
    switch (paramInt) {
      default:
        return new e(paramArrayOfbyte, stringBuilder1.toString(), null, String.valueOf(paramInt));
      case 5:
        stringBuilder1.append(a(paramArrayOfbyte, 1, 77));
      case 4:
        stringBuilder1.append(a(paramArrayOfbyte, 1, 93));
      case 2:
      case 3:
        break;
    } 
    if (paramInt == 2) {
      int i = d(paramArrayOfbyte);
      str1 = (new DecimalFormat("0000000000".substring(0, c(paramArrayOfbyte)))).format(i);
    } else {
      str1 = e(paramArrayOfbyte);
    } 
    String str2 = b.format(a(paramArrayOfbyte));
    String str3 = b.format(b(paramArrayOfbyte));
    stringBuilder1.append(a(paramArrayOfbyte, 10, 84));
    if (stringBuilder1.toString().startsWith("[)>\03601\035")) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str1);
      stringBuilder.append('\035');
      stringBuilder.append(str2);
      stringBuilder.append('\035');
      stringBuilder.append(str3);
      stringBuilder.append('\035');
      stringBuilder1.insert(9, stringBuilder.toString());
    } 
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append(str1);
    stringBuilder2.append('\035');
    stringBuilder2.append(str2);
    stringBuilder2.append('\035');
    stringBuilder2.append(str3);
    stringBuilder2.append('\035');
    stringBuilder1.insert(0, stringBuilder2.toString());
  }
  
  private static String a(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    StringBuilder stringBuilder = new StringBuilder();
    int k = paramInt1;
    int i = 0;
    int j = -1;
    int m;
    for (m = 0; k < paramInt1 + paramInt2; m = n) {
      byte b1;
      byte b2;
      byte b3;
      byte b4;
      char c = c[i].charAt(paramArrayOfbyte[k]);
      int n = i;
      switch (c) {
        default:
          stringBuilder.append(c);
          n = m;
          break;
        case '￻':
          n = paramArrayOfbyte[++k];
          b1 = paramArrayOfbyte[++k];
          b2 = paramArrayOfbyte[++k];
          b3 = paramArrayOfbyte[++k];
          b4 = paramArrayOfbyte[++k];
          stringBuilder.append(a.format(((n << 24) + (b1 << 18) + (b2 << 12) + (b3 << 6) + b4)));
          n = m;
          break;
        case '￸':
          n = 1;
        case '￷':
          n = 0;
        case '￹':
          j = -1;
          i = n;
          n = m;
          break;
        case '￶':
          j = 3;
          n = i;
          i = 0;
          break;
        case '￵':
          j = 2;
          n = i;
          i = 0;
          break;
        case '￰':
        case '￱':
        case '￲':
        case '￳':
        case '￴':
          m = c - 65520;
          j = 1;
          n = i;
          i = m;
          break;
      } 
      if (j == 0)
        i = n; 
      k++;
      j--;
    } 
    while (stringBuilder.length() > 0 && stringBuilder.charAt(stringBuilder.length() - 1) == '￼')
      stringBuilder.setLength(stringBuilder.length() - 1); 
    return stringBuilder.toString();
  }
  
  private static int b(byte[] paramArrayOfbyte) {
    return a(paramArrayOfbyte, new byte[] { 55, 56, 57, 58, 59, 60, 49, 50, 51, 52 });
  }
  
  private static int c(byte[] paramArrayOfbyte) {
    return a(paramArrayOfbyte, new byte[] { 39, 40, 41, 42, 31, 32 });
  }
  
  private static int d(byte[] paramArrayOfbyte) {
    return a(paramArrayOfbyte, new byte[] { 
          33, 34, 35, 36, 25, 26, 27, 28, 29, 30, 
          19, 20, 21, 22, 23, 24, 13, 14, 15, 16, 
          17, 18, 7, 8, 9, 10, 11, 12, 1, 2 });
  }
  
  private static String e(byte[] paramArrayOfbyte) {
    return String.valueOf(new char[] { c[0].charAt(a(paramArrayOfbyte, new byte[] { 39, 40, 41, 42, 31, 32 })), c[0].charAt(a(paramArrayOfbyte, new byte[] { 33, 34, 35, 36, 25, 26 })), c[0].charAt(a(paramArrayOfbyte, new byte[] { 27, 28, 29, 30, 19, 20 })), c[0].charAt(a(paramArrayOfbyte, new byte[] { 21, 22, 23, 24, 13, 14 })), c[0].charAt(a(paramArrayOfbyte, new byte[] { 15, 16, 17, 18, 7, 8 })), c[0].charAt(a(paramArrayOfbyte, new byte[] { 9, 10, 11, 12, 1, 2 })) });
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\d\a\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */