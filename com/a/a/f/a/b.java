package com.a.a.f.a;

import com.a.a.b.e;
import com.a.a.f;
import java.math.BigInteger;

final class b {
  private static final char[] a = new char[] { 
      ';', '<', '>', '@', '[', '\\', '}', '_', '`', '~', 
      '!', '\r', '\t', ',', ':', '\n', '-', '.', '$', '/', 
      '"', '|', '*', '(', ')', '?', '{', '}', '\'' };
  
  private static final char[] b = new char[] { 
      '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
      '&', '\r', '\t', ',', ':', '#', '-', '.', '$', '/', 
      '+', '%', '*', '=', '^' };
  
  private static final BigInteger[] c = new BigInteger[16];
  
  static {
    c[0] = BigInteger.ONE;
    BigInteger bigInteger = BigInteger.valueOf(900L);
    c[1] = bigInteger;
    for (int i = 2; i < c.length; i++)
      c[i] = c[i - 1].multiply(bigInteger); 
  }
  
  private static int a(int paramInt1, int[] paramArrayOfint, int paramInt2, StringBuilder paramStringBuilder) {
    int i;
    if (paramInt1 == 901) {
      char[] arrayOfChar = new char[6];
      int[] arrayOfInt = new int[6];
      int j = paramArrayOfint[paramInt2];
      paramInt2++;
      i = 0;
      paramInt1 = 0;
      long l = 0L;
      while (paramInt2 < paramArrayOfint[0] && !i) {
        int k = paramInt1 + 1;
        arrayOfInt[paramInt1] = j;
        l = l * 900L + j;
        int m = paramInt2 + 1;
        j = paramArrayOfint[paramInt2];
        if (j == 900 || j == 901 || j == 902 || j == 924 || j == 928 || j == 923 || j == 922) {
          paramInt2 = m - 1;
          paramInt1 = k;
          i = 1;
          continue;
        } 
        if (k % 5 == 0 && k > 0) {
          for (paramInt1 = 0; paramInt1 < 6; paramInt1++) {
            arrayOfChar[5 - paramInt1] = (char)(int)(l % 256L);
            l >>= 8L;
          } 
          paramStringBuilder.append(arrayOfChar);
          paramInt1 = 0;
          paramInt2 = m;
          continue;
        } 
        paramInt1 = k;
        paramInt2 = m;
      } 
      if (paramInt2 == paramArrayOfint[0] && j < 900) {
        i = paramInt1 + 1;
        arrayOfInt[paramInt1] = j;
        paramInt1 = i;
      } 
      j = 0;
      while (true) {
        i = paramInt2;
        if (j < paramInt1) {
          paramStringBuilder.append((char)arrayOfInt[j]);
          j++;
          continue;
        } 
        break;
      } 
    } else if (paramInt1 == 924) {
      paramInt1 = paramInt2;
      paramInt2 = 0;
      boolean bool = false;
      long l = 0L;
      while (true) {
        i = paramInt1;
        if (paramInt1 < paramArrayOfint[0]) {
          i = paramInt1;
          if (!bool) {
            i = paramInt1 + 1;
            paramInt1 = paramArrayOfint[paramInt1];
            if (paramInt1 < 900) {
              paramInt2++;
              long l1 = paramInt1;
              paramInt1 = i;
              l = l * 900L + l1;
            } else if (paramInt1 == 900 || paramInt1 == 901 || paramInt1 == 902 || paramInt1 == 924 || paramInt1 == 928 || paramInt1 == 923 || paramInt1 == 922) {
              paramInt1 = i - 1;
              bool = true;
            } else {
              paramInt1 = i;
            } 
            if (paramInt2 % 5 == 0 && paramInt2 > 0) {
              char[] arrayOfChar = new char[6];
              for (paramInt2 = 0; paramInt2 < 6; paramInt2++) {
                arrayOfChar[5 - paramInt2] = (char)(int)(l & 0xFFL);
                l >>= 8L;
              } 
              paramStringBuilder.append(arrayOfChar);
              paramInt2 = 0;
            } 
            continue;
          } 
        } 
        break;
      } 
    } else {
      i = paramInt2;
    } 
    return i;
  }
  
  private static int a(int[] paramArrayOfint, int paramInt, StringBuilder paramStringBuilder) {
    int[] arrayOfInt1 = new int[paramArrayOfint[0] << 1];
    int[] arrayOfInt2 = new int[paramArrayOfint[0] << 1];
    boolean bool = false;
    int i = 0;
    while (true) {
      if (paramInt < paramArrayOfint[0] && !bool) {
        int j = paramInt + 1;
        paramInt = paramArrayOfint[paramInt];
        if (paramInt < 900) {
          arrayOfInt1[i] = paramInt / 30;
          arrayOfInt1[i + 1] = paramInt % 30;
          i += 2;
        } 
        if (paramInt != 913) {
          if (paramInt != 924) {
            switch (paramInt) {
              default:
                paramInt = j;
                continue;
              case 902:
                paramInt = j - 1;
                bool = true;
                continue;
              case 901:
                paramInt = j - 1;
                bool = true;
                continue;
              case 900:
                break;
            } 
            arrayOfInt1[i] = 900;
            i++;
          } 
          paramInt = j - 1;
        } else {
          arrayOfInt1[i] = 913;
          paramInt = j + 1;
          arrayOfInt2[i] = paramArrayOfint[j];
          i++;
          continue;
        } 
      } else {
        break;
      } 
      bool = true;
    } 
    a(arrayOfInt1, arrayOfInt2, i, paramStringBuilder);
    return paramInt;
  }
  
  static e a(int[] paramArrayOfint) {
    StringBuilder stringBuilder = new StringBuilder(100);
    int i = paramArrayOfint[1];
    int j = 2;
    while (j < paramArrayOfint[0]) {
      if (i != 913) {
        if (i != 924) {
          switch (i) {
            default:
              i = a(paramArrayOfint, j - 1, stringBuilder);
              break;
            case 902:
              i = b(paramArrayOfint, j, stringBuilder);
              break;
            case 901:
              i = a(i, paramArrayOfint, j, stringBuilder);
              break;
            case 900:
              i = a(paramArrayOfint, j, stringBuilder);
              break;
          } 
        } else {
          i = a(i, paramArrayOfint, j, stringBuilder);
        } 
      } else {
        i = a(i, paramArrayOfint, j, stringBuilder);
      } 
      if (i < paramArrayOfint.length) {
        j = i + 1;
        i = paramArrayOfint[i];
        continue;
      } 
      throw f.a();
    } 
    if (stringBuilder.length() == 0)
      throw f.a(); 
    return new e(null, stringBuilder.toString(), null, null);
  }
  
  private static String a(int[] paramArrayOfint, int paramInt) {
    BigInteger bigInteger = BigInteger.ZERO;
    for (int i = 0; i < paramInt; i++)
      bigInteger = bigInteger.add(c[paramInt - i - 1].multiply(BigInteger.valueOf(paramArrayOfint[i]))); 
    String str = bigInteger.toString();
    if (str.charAt(0) != '1')
      throw f.a(); 
    return str.substring(1);
  }
  
  private static void a(int[] paramArrayOfint1, int[] paramArrayOfint2, int paramInt, StringBuilder paramStringBuilder) {
    a a1 = a.a;
    a a2 = a.a;
    int i;
    for (i = 0; i < paramInt; i++) {
      boolean bool;
      a a;
      int j = paramArrayOfint1[i];
      switch (null.a[a1.ordinal()]) {
        default:
          a = a2;
          bool = false;
          a2 = a;
          break;
        case 6:
        
        case 5:
        
        case 4:
        
        case 3:
        
        case 2:
        
        case 1:
        
      } 
      if (bool)
        paramStringBuilder.append(bool); 
      continue;
    } 
  }
  
  private static int b(int[] paramArrayOfint, int paramInt, StringBuilder paramStringBuilder) {
    // Byte code:
    //   0: bipush #15
    //   2: newarray int
    //   4: astore #8
    //   6: iconst_0
    //   7: istore_3
    //   8: iconst_0
    //   9: istore #5
    //   11: iload_1
    //   12: istore #4
    //   14: iload #5
    //   16: istore_1
    //   17: iload #4
    //   19: aload_0
    //   20: iconst_0
    //   21: iaload
    //   22: if_icmpge -> 183
    //   25: iload_3
    //   26: ifne -> 183
    //   29: iload #4
    //   31: iconst_1
    //   32: iadd
    //   33: istore #6
    //   35: aload_0
    //   36: iload #4
    //   38: iaload
    //   39: istore #7
    //   41: iload #6
    //   43: aload_0
    //   44: iconst_0
    //   45: iaload
    //   46: if_icmpne -> 51
    //   49: iconst_1
    //   50: istore_3
    //   51: iload #7
    //   53: sipush #900
    //   56: if_icmpge -> 77
    //   59: aload #8
    //   61: iload_1
    //   62: iload #7
    //   64: iastore
    //   65: iload_1
    //   66: iconst_1
    //   67: iadd
    //   68: istore #5
    //   70: iload #6
    //   72: istore #4
    //   74: goto -> 143
    //   77: iload #7
    //   79: sipush #900
    //   82: if_icmpeq -> 132
    //   85: iload #7
    //   87: sipush #901
    //   90: if_icmpeq -> 132
    //   93: iload #7
    //   95: sipush #924
    //   98: if_icmpeq -> 132
    //   101: iload #7
    //   103: sipush #928
    //   106: if_icmpeq -> 132
    //   109: iload #7
    //   111: sipush #923
    //   114: if_icmpeq -> 132
    //   117: iload_1
    //   118: istore #5
    //   120: iload #6
    //   122: istore #4
    //   124: iload #7
    //   126: sipush #922
    //   129: if_icmpne -> 143
    //   132: iload #6
    //   134: iconst_1
    //   135: isub
    //   136: istore #4
    //   138: iconst_1
    //   139: istore_3
    //   140: iload_1
    //   141: istore #5
    //   143: iload #5
    //   145: bipush #15
    //   147: irem
    //   148: ifeq -> 166
    //   151: iload #7
    //   153: sipush #902
    //   156: if_icmpeq -> 166
    //   159: iload #5
    //   161: istore_1
    //   162: iload_3
    //   163: ifeq -> 180
    //   166: aload_2
    //   167: aload #8
    //   169: iload #5
    //   171: invokestatic a : ([II)Ljava/lang/String;
    //   174: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   177: pop
    //   178: iconst_0
    //   179: istore_1
    //   180: goto -> 17
    //   183: iload #4
    //   185: ireturn
  }
  
  private enum a {
    a, b, c, d, e, f;
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\f\a\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */