package com.a.a.g.a;

import com.a.a.b.c;
import com.a.a.b.e;
import com.a.a.b.l;
import com.a.a.e;
import com.a.a.f;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

final class d {
  private static final char[] a = new char[] { 
      '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
      'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 
      'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 
      'U', 'V', 'W', 'X', 'Y', 'Z', ' ', '$', '%', '*', 
      '+', '-', '.', '/', ':' };
  
  private static char a(int paramInt) {
    if (paramInt >= a.length)
      throw f.a(); 
    return a[paramInt];
  }
  
  private static int a(c paramc) {
    int i = paramc.a(8);
    if ((i & 0x80) == 0)
      return i & 0x7F; 
    if ((i & 0xC0) == 128)
      return paramc.a(8) | (i & 0x3F) << 8; 
    if ((i & 0xE0) == 192)
      return paramc.a(16) | (i & 0x1F) << 16; 
    throw f.a();
  }
  
  static e a(byte[] paramArrayOfbyte, i parami, f paramf, Map<e, ?> paramMap) {
    c c = new c(paramArrayOfbyte);
    StringBuilder stringBuilder = new StringBuilder(50);
    ArrayList<byte[]> arrayList = new ArrayList(1);
    f f1 = null;
    com.a.a.b.d d1 = null;
    boolean bool = false;
    while (true) {
      try {
        h h1;
        if (c.c() < 4) {
          h1 = h.a;
        } else {
          h1 = h.a(c.a(4));
        } 
        boolean bool1 = bool;
        com.a.a.b.d d2 = d1;
        if (h1 != h.a)
          if (h1 == h.h || h1 == h.i) {
            bool1 = true;
            d2 = d1;
          } else if (h1 == h.d) {
            if (c.c() < 16)
              throw f.a(); 
            c.a(16);
            bool1 = bool;
            d2 = d1;
          } else if (h1 == h.f) {
            d1 = com.a.a.b.d.a(a(c));
            bool1 = bool;
            d2 = d1;
            if (d1 == null)
              throw f.a(); 
          } else if (h1 == h.j) {
            int j = c.a(4);
            int k = c.a(h1.a(parami));
            bool1 = bool;
            d2 = d1;
            if (j == 1) {
              a(c, stringBuilder, k);
              bool1 = bool;
              d2 = d1;
            } 
          } else {
            int j = c.a(h1.a(parami));
            if (h1 == h.b) {
              c(c, stringBuilder, j);
              bool1 = bool;
              d2 = d1;
            } else if (h1 == h.c) {
              a(c, stringBuilder, j, bool);
              bool1 = bool;
              d2 = d1;
            } else if (h1 == h.e) {
              a(c, stringBuilder, j, d1, (Collection<byte[]>)arrayList, paramMap);
              bool1 = bool;
              d2 = d1;
            } else if (h1 == h.g) {
              b(c, stringBuilder, j);
              bool1 = bool;
              d2 = d1;
            } else {
              throw f.a();
            } 
          }  
        h h2 = h.a;
        bool = bool1;
        d1 = d2;
        if (h1 == h2) {
          String str1;
          String str2 = stringBuilder.toString();
          ArrayList<byte[]> arrayList1 = arrayList;
          if (arrayList.isEmpty())
            arrayList1 = null; 
          if (paramf == null) {
            paramf = f1;
          } else {
            str1 = paramf.toString();
          } 
          return new e(paramArrayOfbyte, str2, arrayList1, str1);
        } 
      } catch (IllegalArgumentException illegalArgumentException) {
        throw f.a();
      } 
    } 
  }
  
  private static void a(c paramc, StringBuilder paramStringBuilder, int paramInt) {
    if (paramInt * 13 > paramc.c())
      throw f.a(); 
    byte[] arrayOfByte = new byte[paramInt * 2];
    int i = 0;
    while (paramInt > 0) {
      int j = paramc.a(13);
      j = j % 96 | j / 96 << 8;
      if (j < 959) {
        j += 41377;
      } else {
        j += 42657;
      } 
      arrayOfByte[i] = (byte)(j >> 8 & 0xFF);
      arrayOfByte[i + 1] = (byte)(j & 0xFF);
      i += 2;
      paramInt--;
    } 
    try {
      paramStringBuilder.append(new String(arrayOfByte, "GB2312"));
      return;
    } catch (UnsupportedEncodingException unsupportedEncodingException) {
      throw f.a();
    } 
  }
  
  private static void a(c paramc, StringBuilder paramStringBuilder, int paramInt, com.a.a.b.d paramd, Collection<byte[]> paramCollection, Map<e, ?> paramMap) {
    String str;
    if (paramInt << 3 > paramc.c())
      throw f.a(); 
    byte[] arrayOfByte = new byte[paramInt];
    int i;
    for (i = 0; i < paramInt; i++)
      arrayOfByte[i] = (byte)paramc.a(8); 
    if (paramd == null) {
      str = l.a(arrayOfByte, paramMap);
    } else {
      str = paramd.name();
    } 
    try {
      paramStringBuilder.append(new String(arrayOfByte, str));
      paramCollection.add(arrayOfByte);
      return;
    } catch (UnsupportedEncodingException unsupportedEncodingException) {
      throw f.a();
    } 
  }
  
  private static void a(c paramc, StringBuilder paramStringBuilder, int paramInt, boolean paramBoolean) {
    int i = paramStringBuilder.length();
    while (paramInt > 1) {
      if (paramc.c() < 11)
        throw f.a(); 
      int j = paramc.a(11);
      paramStringBuilder.append(a(j / 45));
      paramStringBuilder.append(a(j % 45));
      paramInt -= 2;
    } 
    if (paramInt == 1) {
      if (paramc.c() < 6)
        throw f.a(); 
      paramStringBuilder.append(a(paramc.a(6)));
    } 
    if (paramBoolean)
      for (paramInt = i; paramInt < paramStringBuilder.length(); paramInt++) {
        if (paramStringBuilder.charAt(paramInt) == '%') {
          if (paramInt < paramStringBuilder.length() - 1) {
            i = paramInt + 1;
            if (paramStringBuilder.charAt(i) == '%') {
              paramStringBuilder.deleteCharAt(i);
              continue;
            } 
          } 
          paramStringBuilder.setCharAt(paramInt, '\035');
        } 
        continue;
      }  
  }
  
  private static void b(c paramc, StringBuilder paramStringBuilder, int paramInt) {
    if (paramInt * 13 > paramc.c())
      throw f.a(); 
    byte[] arrayOfByte = new byte[paramInt * 2];
    int i = 0;
    while (paramInt > 0) {
      int j = paramc.a(13);
      j = j % 192 | j / 192 << 8;
      if (j < 7936) {
        j += 33088;
      } else {
        j += 49472;
      } 
      arrayOfByte[i] = (byte)(j >> 8);
      arrayOfByte[i + 1] = (byte)j;
      i += 2;
      paramInt--;
    } 
    try {
      paramStringBuilder.append(new String(arrayOfByte, "SJIS"));
      return;
    } catch (UnsupportedEncodingException unsupportedEncodingException) {
      throw f.a();
    } 
  }
  
  private static void c(c paramc, StringBuilder paramStringBuilder, int paramInt) {
    while (paramInt >= 3) {
      if (paramc.c() < 10)
        throw f.a(); 
      int i = paramc.a(10);
      if (i >= 1000)
        throw f.a(); 
      paramStringBuilder.append(a(i / 100));
      paramStringBuilder.append(a(i / 10 % 10));
      paramStringBuilder.append(a(i % 10));
      paramInt -= 3;
    } 
    if (paramInt == 2) {
      if (paramc.c() < 7)
        throw f.a(); 
      paramInt = paramc.a(7);
      if (paramInt >= 100)
        throw f.a(); 
      paramStringBuilder.append(a(paramInt / 10));
      paramStringBuilder.append(a(paramInt % 10));
      return;
    } 
    if (paramInt == 1) {
      if (paramc.c() < 4)
        throw f.a(); 
      paramInt = paramc.a(4);
      if (paramInt >= 10)
        throw f.a(); 
      paramStringBuilder.append(a(paramInt));
    } 
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\g\a\d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */