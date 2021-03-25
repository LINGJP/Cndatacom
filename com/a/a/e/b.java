package com.a.a.e;

import com.a.a.a;
import com.a.a.b.a;
import com.a.a.d;
import com.a.a.e;
import com.a.a.f;
import com.a.a.i;
import com.a.a.m;
import com.a.a.o;
import java.util.ArrayList;
import java.util.Map;

public final class b extends k {
  static final int[][] a;
  
  static {
    int[] arrayOfInt1 = { 2, 1, 2, 2, 2, 2 };
    int[] arrayOfInt2 = { 2, 2, 1, 2, 1, 3 };
    int[] arrayOfInt3 = { 1, 1, 2, 2, 3, 2 };
    int[] arrayOfInt4 = { 1, 2, 2, 2, 3, 1 };
    int[] arrayOfInt5 = { 3, 2, 1, 2, 2, 1 };
    int[] arrayOfInt6 = { 1, 1, 2, 1, 3, 3 };
    int[] arrayOfInt7 = { 3, 3, 2, 1, 1, 1 };
    int[] arrayOfInt8 = { 1, 2, 1, 1, 2, 4 };
    int[] arrayOfInt9 = { 1, 2, 1, 1, 4, 2 };
    int[] arrayOfInt10 = { 1, 2, 1, 2, 4, 1 };
    int[] arrayOfInt11 = { 1, 1, 1, 3, 4, 1 };
    int[] arrayOfInt12 = { 2, 1, 1, 2, 3, 2 };
    a = new int[][] { 
        arrayOfInt1, { 2, 2, 2, 1, 2, 2 }, { 2, 2, 2, 2, 2, 1 }, { 1, 2, 1, 2, 2, 3 }, { 1, 2, 1, 3, 2, 2 }, { 1, 3, 1, 2, 2, 2 }, { 1, 2, 2, 2, 1, 3 }, { 1, 2, 2, 3, 1, 2 }, { 1, 3, 2, 2, 1, 2 }, arrayOfInt2, 
        { 2, 2, 1, 3, 1, 2 }, { 2, 3, 1, 2, 1, 2 }, arrayOfInt3, { 1, 2, 2, 1, 3, 2 }, arrayOfInt4, { 1, 1, 3, 2, 2, 2 }, { 1, 2, 3, 1, 2, 2 }, { 1, 2, 3, 2, 2, 1 }, { 2, 2, 3, 2, 1, 1 }, { 2, 2, 1, 1, 3, 2 }, 
        { 2, 2, 1, 2, 3, 1 }, { 2, 1, 3, 2, 1, 2 }, { 2, 2, 3, 1, 1, 2 }, { 3, 1, 2, 1, 3, 1 }, { 3, 1, 1, 2, 2, 2 }, { 3, 2, 1, 1, 2, 2 }, arrayOfInt5, { 3, 1, 2, 2, 1, 2 }, { 3, 2, 2, 1, 1, 2 }, { 3, 2, 2, 2, 1, 1 }, 
        { 2, 1, 2, 1, 2, 3 }, { 2, 1, 2, 3, 2, 1 }, { 2, 3, 2, 1, 2, 1 }, { 1, 1, 1, 3, 2, 3 }, { 1, 3, 1, 1, 2, 3 }, { 1, 3, 1, 3, 2, 1 }, { 1, 1, 2, 3, 1, 3 }, { 1, 3, 2, 1, 1, 3 }, { 1, 3, 2, 3, 1, 1 }, { 2, 1, 1, 3, 1, 3 }, 
        { 2, 3, 1, 1, 1, 3 }, { 2, 3, 1, 3, 1, 1 }, arrayOfInt6, { 1, 1, 2, 3, 3, 1 }, { 1, 3, 2, 1, 3, 1 }, { 1, 1, 3, 1, 2, 3 }, { 1, 1, 3, 3, 2, 1 }, { 1, 3, 3, 1, 2, 1 }, { 3, 1, 3, 1, 2, 1 }, { 2, 1, 1, 3, 3, 1 }, 
        { 2, 3, 1, 1, 3, 1 }, { 2, 1, 3, 1, 1, 3 }, { 2, 1, 3, 3, 1, 1 }, { 2, 1, 3, 1, 3, 1 }, { 3, 1, 1, 1, 2, 3 }, { 3, 1, 1, 3, 2, 1 }, { 3, 3, 1, 1, 2, 1 }, { 3, 1, 2, 1, 1, 3 }, { 3, 1, 2, 3, 1, 1 }, arrayOfInt7, 
        { 3, 1, 4, 1, 1, 1 }, { 2, 2, 1, 4, 1, 1 }, { 4, 3, 1, 1, 1, 1 }, { 1, 1, 1, 2, 2, 4 }, { 1, 1, 1, 4, 2, 2 }, arrayOfInt8, { 1, 2, 1, 4, 2, 1 }, { 1, 4, 1, 1, 2, 2 }, { 1, 4, 1, 2, 2, 1 }, { 1, 1, 2, 2, 1, 4 }, 
        { 1, 1, 2, 4, 1, 2 }, { 1, 2, 2, 1, 1, 4 }, { 1, 2, 2, 4, 1, 1 }, { 1, 4, 2, 1, 1, 2 }, { 1, 4, 2, 2, 1, 1 }, { 2, 4, 1, 2, 1, 1 }, { 2, 2, 1, 1, 1, 4 }, { 4, 1, 3, 1, 1, 1 }, { 2, 4, 1, 1, 1, 2 }, { 1, 3, 4, 1, 1, 1 }, 
        { 1, 1, 1, 2, 4, 2 }, arrayOfInt9, arrayOfInt10, { 1, 1, 4, 2, 1, 2 }, { 1, 2, 4, 1, 1, 2 }, { 1, 2, 4, 2, 1, 1 }, { 4, 1, 1, 2, 1, 2 }, { 4, 2, 1, 1, 1, 2 }, { 4, 2, 1, 2, 1, 1 }, { 2, 1, 2, 1, 4, 1 }, 
        { 2, 1, 4, 1, 2, 1 }, { 4, 1, 2, 1, 2, 1 }, { 1, 1, 1, 1, 4, 3 }, arrayOfInt11, { 1, 3, 1, 1, 4, 1 }, { 1, 1, 4, 1, 1, 3 }, { 1, 1, 4, 3, 1, 1 }, { 4, 1, 1, 1, 1, 3 }, { 4, 1, 1, 3, 1, 1 }, { 1, 1, 3, 1, 4, 1 }, 
        { 1, 1, 4, 1, 3, 1 }, { 3, 1, 1, 1, 4, 1 }, { 4, 1, 1, 1, 3, 1 }, { 2, 1, 1, 4, 1, 2 }, { 2, 1, 1, 2, 1, 4 }, arrayOfInt12, { 2, 3, 3, 1, 1, 1, 2 } };
  }
  
  private static int a(a parama, int[] paramArrayOfint, int paramInt) {
    a(parama, paramInt, paramArrayOfint);
    int i = 64;
    int j = -1;
    paramInt = 0;
    while (paramInt < a.length) {
      int n = a(paramArrayOfint, a[paramInt], 179);
      int m = i;
      if (n < i) {
        j = paramInt;
        m = n;
      } 
      paramInt++;
      i = m;
    } 
    if (j >= 0)
      return j; 
    throw i.a();
  }
  
  private static int[] a(a parama) {
    int n = parama.a();
    int j = parama.c(0);
    int[] arrayOfInt = new int[6];
    int i1 = arrayOfInt.length;
    int i = j;
    boolean bool = false;
    int m = 0;
    while (j < n) {
      int i2;
      if (parama.a(j) ^ bool) {
        arrayOfInt[m] = arrayOfInt[m] + 1;
        i2 = i;
      } else {
        int i4 = i1 - 1;
        if (m == i4) {
          int i5 = 64;
          i2 = 103;
          int i6 = -1;
          while (i2 <= 105) {
            int i8 = a(arrayOfInt, a[i2], 179);
            int i7 = i5;
            if (i8 < i5) {
              i6 = i2;
              i7 = i8;
            } 
            i2++;
            i5 = i7;
          } 
          if (i6 >= 0 && parama.a(Math.max(0, i - (j - i) / 2), i, false))
            return new int[] { i, j, i6 }; 
          i2 = i + arrayOfInt[0] + arrayOfInt[1];
          i = i1 - 2;
          System.arraycopy(arrayOfInt, 2, arrayOfInt, 0, i);
          arrayOfInt[i] = 0;
          arrayOfInt[i4] = 0;
          i = m - 1;
        } else {
          m++;
          i2 = i;
          i = m;
        } 
        arrayOfInt[i] = 1;
        int i3 = bool ^ true;
        m = i;
      } 
      j++;
      i = i2;
    } 
    throw i.a();
  }
  
  public m a(int paramInt, a parama, Map<e, ?> paramMap) {
    Object object1;
    Object object2;
    boolean bool3;
    Object object3;
    if (paramMap != null && paramMap.containsKey(e.h)) {
      bool3 = true;
    } else {
      bool3 = false;
    } 
    int[] arrayOfInt1 = a(parama);
    int i1 = arrayOfInt1[2];
    switch (i1) {
      default:
        throw f.a();
      case 105:
        i = 99;
        break;
      case 104:
        i = 100;
        break;
      case 103:
        i = 101;
        break;
    } 
    StringBuilder stringBuilder = new StringBuilder(20);
    ArrayList<Byte> arrayList = new ArrayList(20);
    int i3 = arrayOfInt1[0];
    int m = arrayOfInt1[1];
    int[] arrayOfInt2 = new int[6];
    int j = i;
    boolean bool4 = false;
    boolean bool1 = false;
    int i2 = 0;
    int n = 0;
    boolean bool2 = false;
    int i = 1;
    while (true) {
      int i6 = n;
      if (!bool1) {
        i3 = a(parama, arrayOfInt2, m);
        arrayList.add(Byte.valueOf((byte)i3));
        if (i3 != 106)
          i = 1; 
        Object object5 = object1;
        Object object4 = object2;
        if (i3 != 106) {
          int i8 = object2 + 1;
          int i9 = object1 + i8 * i3;
        } 
        i5 = arrayOfInt2.length;
        n = m;
        int i7;
        for (i7 = 0; i7 < i5; i7++)
          n += arrayOfInt2[i7]; 
        switch (i3) {
          default:
            switch (j) {
              default:
                break;
              case 101:
              
              case 100:
              
              case 99:
                if (i3 < 100) {
                  if (i3 < 10)
                    stringBuilder.append('0'); 
                  stringBuilder.append(i3);
                } else {
                  i7 = i;
                  if (i3 != 106)
                    i7 = 0; 
                  if (i3 != 106) {
                    switch (i3) {
                      default:
                        i = i7;
                        break;
                      case 102:
                        i = i7;
                        if (bool3) {
                          if (stringBuilder.length() == 0) {
                            stringBuilder.append("]C1");
                            i = i7;
                            break;
                          } 
                          stringBuilder.append('\035');
                          i = i7;
                        } 
                        break;
                      case 101:
                        i = 0;
                        j = 101;
                        break;
                      case 100:
                        i = 0;
                        j = 100;
                        break;
                    } 
                  } else {
                    i = 0;
                    bool1 = true;
                    break;
                  } 
                } 
                i5 = 0;
                i7 = i;
                i = i5;
                break;
            } 
            if (i2 != 0)
              if (j == 101) {
                j = 100;
              } else {
                j = 101;
              }  
            continue;
          case 103:
          case 104:
          case 105:
            throw f.a();
        } 
      } else {
        int i7 = parama.d(m);
        if (!parama.a(i7, Math.min(parama.a(), (i7 - i3) / 2 + i7), false))
          throw i.a(); 
        if ((i5 - object2 * object3) % 103 != object3)
          throw d.a(); 
        m = stringBuilder.length();
        if (m == 0)
          throw i.a(); 
        if (m > 0 && i != 0)
          if (j == 99) {
            stringBuilder.delete(m - 2, m);
          } else {
            stringBuilder.delete(m - 1, m);
          }  
        float f1 = (arrayOfInt1[1] + arrayOfInt1[0]) / 2.0F;
        float f2 = (i7 + i3) / 2.0F;
        i7 = arrayList.size();
        byte[] arrayOfByte = new byte[i7];
        for (i = 0; i < i7; i++)
          arrayOfByte[i] = ((Byte)arrayList.get(i)).byteValue(); 
        String str = stringBuilder.toString();
        float f3 = paramInt;
        o o1 = new o(f1, f3);
        o o2 = new o(f2, f3);
        a a1 = a.e;
        return new m(str, arrayOfByte, new o[] { o1, o2 }, a1);
      } 
      int i5 = 0;
      int i4 = i;
      i = i5;
      break;
      i1 = i3;
      i3 = m;
      m = n;
      object3 = SYNTHETIC_LOCAL_VARIABLE_18;
      i2 = i;
      n = i1;
      object1 = SYNTHETIC_LOCAL_VARIABLE_19;
      object2 = SYNTHETIC_LOCAL_VARIABLE_17;
      object = SYNTHETIC_LOCAL_VARIABLE_8;
    } 
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\e\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */