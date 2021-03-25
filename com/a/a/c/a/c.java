package com.a.a.c.a;

import com.a.a.b.e;
import com.a.a.f;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;

final class c {
  private static final char[] a = new char[] { 
      '*', '*', '*', ' ', '0', '1', '2', '3', '4', '5', 
      '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 
      'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 
      'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
  
  private static final char[] b = new char[] { 
      '!', '"', '#', '$', '%', '&', '\'', '(', ')', '*', 
      '+', ',', '-', '.', '/', ':', ';', '<', '=', '>', 
      '?', '@', '[', '\\', ']', '^', '_' };
  
  private static final char[] c = new char[] { 
      '*', '*', '*', ' ', '0', '1', '2', '3', '4', '5', 
      '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 
      'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 
      'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
  
  private static final char[] d = new char[] { 
      '\'', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 
      'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 
      'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '{', '|', '}', 
      '~', '' };
  
  private static int a(int paramInt1, int paramInt2) {
    paramInt1 -= paramInt2 * 149 % 255 + 1;
    return (paramInt1 >= 0) ? paramInt1 : (paramInt1 + 256);
  }
  
  static e a(byte[] paramArrayOfbyte) {
    com.a.a.b.c c1 = new com.a.a.b.c(paramArrayOfbyte);
    StringBuilder stringBuilder1 = new StringBuilder(100);
    StringBuilder stringBuilder2 = new StringBuilder(0);
    ArrayList<byte[]> arrayList2 = new ArrayList(1);
    a a = a.b;
    do {
      if (a == a.b) {
        a = a(c1, stringBuilder1, stringBuilder2);
      } else {
        switch (null.a[a.ordinal()]) {
          default:
            throw f.a();
          case 5:
            a(c1, stringBuilder1, (Collection<byte[]>)arrayList2);
            break;
          case 4:
            d(c1, stringBuilder1);
            break;
          case 3:
            c(c1, stringBuilder1);
            break;
          case 2:
            b(c1, stringBuilder1);
            break;
          case 1:
            a(c1, stringBuilder1);
            break;
        } 
        a = a.b;
      } 
    } while (a != a.a && c1.c() > 0);
    if (stringBuilder2.length() > 0)
      stringBuilder1.append(stringBuilder2.toString()); 
    String str = stringBuilder1.toString();
    ArrayList<byte[]> arrayList1 = arrayList2;
    if (arrayList2.isEmpty())
      arrayList1 = null; 
    return new e(paramArrayOfbyte, str, arrayList1, null);
  }
  
  private static a a(com.a.a.b.c paramc, StringBuilder paramStringBuilder1, StringBuilder paramStringBuilder2) {
    boolean bool = false;
    while (true) {
      boolean bool1;
      int i = paramc.a(8);
      if (i == 0)
        throw f.a(); 
      if (i <= 128) {
        bool1 = i;
        if (bool)
          bool1 = i + 128; 
        paramStringBuilder1.append((char)(bool1 - 1));
        return a.b;
      } 
      if (i == 129)
        return a.a; 
      if (i <= 229) {
        bool1 = i - 130;
        if (bool1 < 10)
          paramStringBuilder1.append('0'); 
        paramStringBuilder1.append(bool1);
        bool1 = bool;
      } else {
        if (i == 230)
          return a.c; 
        if (i == 231)
          return a.g; 
        if (i == 232) {
          paramStringBuilder1.append('\035');
          bool1 = bool;
        } else {
          bool1 = bool;
          if (i != 233)
            if (i == 234) {
              bool1 = bool;
            } else if (i == 235) {
              bool1 = true;
            } else if (i == 236) {
              paramStringBuilder1.append("[)>\03605\035");
              paramStringBuilder2.insert(0, "\036\004");
              bool1 = bool;
            } else if (i == 237) {
              paramStringBuilder1.append("[)>\03606\035");
              paramStringBuilder2.insert(0, "\036\004");
              bool1 = bool;
            } else {
              if (i == 238)
                return a.e; 
              if (i == 239)
                return a.d; 
              if (i == 240)
                return a.f; 
              if (i == 241) {
                bool1 = bool;
              } else {
                bool1 = bool;
                if (i >= 242)
                  if (i == 254) {
                    bool1 = bool;
                    if (paramc.c() != 0)
                      throw f.a(); 
                  } else {
                    throw f.a();
                  }  
              } 
            }  
        } 
      } 
      bool = bool1;
      if (paramc.c() <= 0)
        return a.b; 
    } 
  }
  
  private static void a(int paramInt1, int paramInt2, int[] paramArrayOfint) {
    paramInt1 = (paramInt1 << 8) + paramInt2 - 1;
    paramInt2 = paramInt1 / 1600;
    paramArrayOfint[0] = paramInt2;
    paramInt1 -= paramInt2 * 1600;
    paramInt2 = paramInt1 / 40;
    paramArrayOfint[1] = paramInt2;
    paramArrayOfint[2] = paramInt1 - paramInt2 * 40;
  }
  
  private static void a(com.a.a.b.c paramc, StringBuilder paramStringBuilder) {
    // Byte code:
    //   0: iconst_3
    //   1: newarray int
    //   3: astore #7
    //   5: iconst_0
    //   6: istore_3
    //   7: iconst_0
    //   8: istore #4
    //   10: aload_0
    //   11: invokevirtual c : ()I
    //   14: bipush #8
    //   16: if_icmpne -> 20
    //   19: return
    //   20: aload_0
    //   21: bipush #8
    //   23: invokevirtual a : (I)I
    //   26: istore #5
    //   28: iload #5
    //   30: sipush #254
    //   33: if_icmpne -> 37
    //   36: return
    //   37: iload #5
    //   39: aload_0
    //   40: bipush #8
    //   42: invokevirtual a : (I)I
    //   45: aload #7
    //   47: invokestatic a : (II[I)V
    //   50: iconst_0
    //   51: istore #5
    //   53: iload #5
    //   55: iconst_3
    //   56: if_icmpge -> 318
    //   59: aload #7
    //   61: iload #5
    //   63: iaload
    //   64: istore #6
    //   66: iload #4
    //   68: tableswitch default -> 100, 0 -> 248, 1 -> 213, 2 -> 137, 3 -> 104
    //   100: invokestatic a : ()Lcom/a/a/f;
    //   103: athrow
    //   104: iload_3
    //   105: ifeq -> 123
    //   108: aload_1
    //   109: iload #6
    //   111: sipush #224
    //   114: iadd
    //   115: i2c
    //   116: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   119: pop
    //   120: goto -> 229
    //   123: aload_1
    //   124: iload #6
    //   126: bipush #96
    //   128: iadd
    //   129: i2c
    //   130: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   133: pop
    //   134: goto -> 242
    //   137: iload #6
    //   139: getstatic com/a/a/c/a/c.b : [C
    //   142: arraylength
    //   143: if_icmpge -> 180
    //   146: getstatic com/a/a/c/a/c.b : [C
    //   149: iload #6
    //   151: caload
    //   152: istore_2
    //   153: iload_3
    //   154: ifeq -> 171
    //   157: aload_1
    //   158: iload_2
    //   159: sipush #128
    //   162: iadd
    //   163: i2c
    //   164: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   167: pop
    //   168: goto -> 229
    //   171: aload_1
    //   172: iload_2
    //   173: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   176: pop
    //   177: goto -> 242
    //   180: iload #6
    //   182: bipush #27
    //   184: if_icmpne -> 197
    //   187: aload_1
    //   188: bipush #29
    //   190: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   193: pop
    //   194: goto -> 242
    //   197: iload #6
    //   199: bipush #30
    //   201: if_icmpne -> 209
    //   204: iconst_1
    //   205: istore_3
    //   206: goto -> 242
    //   209: invokestatic a : ()Lcom/a/a/f;
    //   212: athrow
    //   213: iload_3
    //   214: ifeq -> 234
    //   217: aload_1
    //   218: iload #6
    //   220: sipush #128
    //   223: iadd
    //   224: i2c
    //   225: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   228: pop
    //   229: iconst_0
    //   230: istore_3
    //   231: goto -> 242
    //   234: aload_1
    //   235: iload #6
    //   237: i2c
    //   238: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   241: pop
    //   242: iconst_0
    //   243: istore #4
    //   245: goto -> 305
    //   248: iload #6
    //   250: iconst_3
    //   251: if_icmpge -> 263
    //   254: iload #6
    //   256: iconst_1
    //   257: iadd
    //   258: istore #4
    //   260: goto -> 305
    //   263: iload #6
    //   265: getstatic com/a/a/c/a/c.a : [C
    //   268: arraylength
    //   269: if_icmpge -> 314
    //   272: getstatic com/a/a/c/a/c.a : [C
    //   275: iload #6
    //   277: caload
    //   278: istore_2
    //   279: iload_3
    //   280: ifeq -> 299
    //   283: aload_1
    //   284: iload_2
    //   285: sipush #128
    //   288: iadd
    //   289: i2c
    //   290: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   293: pop
    //   294: iconst_0
    //   295: istore_3
    //   296: goto -> 305
    //   299: aload_1
    //   300: iload_2
    //   301: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   304: pop
    //   305: iload #5
    //   307: iconst_1
    //   308: iadd
    //   309: istore #5
    //   311: goto -> 53
    //   314: invokestatic a : ()Lcom/a/a/f;
    //   317: athrow
    //   318: aload_0
    //   319: invokevirtual c : ()I
    //   322: ifgt -> 326
    //   325: return
    //   326: goto -> 10
  }
  
  private static void a(com.a.a.b.c paramc, StringBuilder paramStringBuilder, Collection<byte[]> paramCollection) {
    int i = paramc.b() + 1;
    int k = paramc.a(8);
    int j = i + 1;
    i = a(k, i);
    if (i == 0) {
      i = paramc.c() / 8;
    } else if (i >= 250) {
      i = (i - 249) * 250 + a(paramc.a(8), j);
      j++;
    } 
    if (i < 0)
      throw f.a(); 
    byte[] arrayOfByte = new byte[i];
    k = 0;
    while (k < i) {
      if (paramc.c() < 8)
        throw f.a(); 
      arrayOfByte[k] = (byte)a(paramc.a(8), j);
      k++;
      j++;
    } 
    paramCollection.add(arrayOfByte);
    try {
      paramStringBuilder.append(new String(arrayOfByte, "ISO8859_1"));
      return;
    } catch (UnsupportedEncodingException unsupportedEncodingException) {
      paramStringBuilder = new StringBuilder();
      paramStringBuilder.append("Platform does not support required encoding: ");
      paramStringBuilder.append(unsupportedEncodingException);
      throw new IllegalStateException(paramStringBuilder.toString());
    } 
  }
  
  private static void b(com.a.a.b.c paramc, StringBuilder paramStringBuilder) {
    // Byte code:
    //   0: iconst_3
    //   1: newarray int
    //   3: astore #7
    //   5: iconst_0
    //   6: istore_3
    //   7: iconst_0
    //   8: istore #4
    //   10: aload_0
    //   11: invokevirtual c : ()I
    //   14: bipush #8
    //   16: if_icmpne -> 20
    //   19: return
    //   20: aload_0
    //   21: bipush #8
    //   23: invokevirtual a : (I)I
    //   26: istore #5
    //   28: iload #5
    //   30: sipush #254
    //   33: if_icmpne -> 37
    //   36: return
    //   37: iload #5
    //   39: aload_0
    //   40: bipush #8
    //   42: invokevirtual a : (I)I
    //   45: aload #7
    //   47: invokestatic a : (II[I)V
    //   50: iconst_0
    //   51: istore #5
    //   53: iload #5
    //   55: iconst_3
    //   56: if_icmpge -> 332
    //   59: aload #7
    //   61: iload #5
    //   63: iaload
    //   64: istore #6
    //   66: iload #4
    //   68: tableswitch default -> 100, 0 -> 262, 1 -> 227, 2 -> 151, 3 -> 104
    //   100: invokestatic a : ()Lcom/a/a/f;
    //   103: athrow
    //   104: iload #6
    //   106: getstatic com/a/a/c/a/c.d : [C
    //   109: arraylength
    //   110: if_icmpge -> 147
    //   113: getstatic com/a/a/c/a/c.d : [C
    //   116: iload #6
    //   118: caload
    //   119: istore_2
    //   120: iload_3
    //   121: ifeq -> 138
    //   124: aload_1
    //   125: iload_2
    //   126: sipush #128
    //   129: iadd
    //   130: i2c
    //   131: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   134: pop
    //   135: goto -> 243
    //   138: aload_1
    //   139: iload_2
    //   140: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   143: pop
    //   144: goto -> 256
    //   147: invokestatic a : ()Lcom/a/a/f;
    //   150: athrow
    //   151: iload #6
    //   153: getstatic com/a/a/c/a/c.b : [C
    //   156: arraylength
    //   157: if_icmpge -> 194
    //   160: getstatic com/a/a/c/a/c.b : [C
    //   163: iload #6
    //   165: caload
    //   166: istore_2
    //   167: iload_3
    //   168: ifeq -> 185
    //   171: aload_1
    //   172: iload_2
    //   173: sipush #128
    //   176: iadd
    //   177: i2c
    //   178: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   181: pop
    //   182: goto -> 243
    //   185: aload_1
    //   186: iload_2
    //   187: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   190: pop
    //   191: goto -> 256
    //   194: iload #6
    //   196: bipush #27
    //   198: if_icmpne -> 211
    //   201: aload_1
    //   202: bipush #29
    //   204: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   207: pop
    //   208: goto -> 256
    //   211: iload #6
    //   213: bipush #30
    //   215: if_icmpne -> 223
    //   218: iconst_1
    //   219: istore_3
    //   220: goto -> 256
    //   223: invokestatic a : ()Lcom/a/a/f;
    //   226: athrow
    //   227: iload_3
    //   228: ifeq -> 248
    //   231: aload_1
    //   232: iload #6
    //   234: sipush #128
    //   237: iadd
    //   238: i2c
    //   239: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   242: pop
    //   243: iconst_0
    //   244: istore_3
    //   245: goto -> 256
    //   248: aload_1
    //   249: iload #6
    //   251: i2c
    //   252: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   255: pop
    //   256: iconst_0
    //   257: istore #4
    //   259: goto -> 319
    //   262: iload #6
    //   264: iconst_3
    //   265: if_icmpge -> 277
    //   268: iload #6
    //   270: iconst_1
    //   271: iadd
    //   272: istore #4
    //   274: goto -> 319
    //   277: iload #6
    //   279: getstatic com/a/a/c/a/c.c : [C
    //   282: arraylength
    //   283: if_icmpge -> 328
    //   286: getstatic com/a/a/c/a/c.c : [C
    //   289: iload #6
    //   291: caload
    //   292: istore_2
    //   293: iload_3
    //   294: ifeq -> 313
    //   297: aload_1
    //   298: iload_2
    //   299: sipush #128
    //   302: iadd
    //   303: i2c
    //   304: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   307: pop
    //   308: iconst_0
    //   309: istore_3
    //   310: goto -> 319
    //   313: aload_1
    //   314: iload_2
    //   315: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   318: pop
    //   319: iload #5
    //   321: iconst_1
    //   322: iadd
    //   323: istore #5
    //   325: goto -> 53
    //   328: invokestatic a : ()Lcom/a/a/f;
    //   331: athrow
    //   332: aload_0
    //   333: invokevirtual c : ()I
    //   336: ifgt -> 340
    //   339: return
    //   340: goto -> 10
  }
  
  private static void c(com.a.a.b.c paramc, StringBuilder paramStringBuilder) {
    int[] arrayOfInt = new int[3];
    do {
      if (paramc.c() == 8)
        return; 
      int i = paramc.a(8);
      if (i == 254)
        return; 
      a(i, paramc.a(8), arrayOfInt);
      for (i = 0; i < 3; i++) {
        int j = arrayOfInt[i];
        if (j == 0) {
          paramStringBuilder.append('\r');
        } else if (j == 1) {
          paramStringBuilder.append('*');
        } else if (j == 2) {
          paramStringBuilder.append('>');
        } else if (j == 3) {
          paramStringBuilder.append(' ');
        } else if (j < 14) {
          paramStringBuilder.append((char)(j + 44));
        } else if (j < 40) {
          paramStringBuilder.append((char)(j + 51));
        } else {
          throw f.a();
        } 
      } 
    } while (paramc.c() > 0);
  }
  
  private static void d(com.a.a.b.c paramc, StringBuilder paramStringBuilder) {
    do {
      if (paramc.c() <= 16)
        return; 
      for (int i = 0; i < 4; i++) {
        int k = paramc.a(6);
        if (k == 31) {
          i = 8 - paramc.a();
          if (i != 8)
            paramc.a(i); 
          return;
        } 
        int j = k;
        if ((k & 0x20) == 0)
          j = k | 0x40; 
        paramStringBuilder.append((char)j);
      } 
    } while (paramc.c() > 0);
  }
  
  private enum a {
    a, b, c, d, e, f, g;
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\c\a\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */