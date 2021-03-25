package com.a.a;

import com.a.a.b.a.a;

public class o {
  private final float a;
  
  private final float b;
  
  public o(float paramFloat1, float paramFloat2) {
    this.a = paramFloat1;
    this.b = paramFloat2;
  }
  
  public static float a(o paramo1, o paramo2) {
    return a.a(paramo1.a, paramo1.b, paramo2.a, paramo2.b);
  }
  
  private static float a(o paramo1, o paramo2, o paramo3) {
    float f1 = paramo2.a;
    float f2 = paramo2.b;
    return (paramo3.a - f1) * (paramo1.b - f2) - (paramo3.b - f2) * (paramo1.a - f1);
  }
  
  public static void a(o[] paramArrayOfo) {
    o o1;
    o o2;
    o o3;
    float f1 = a(paramArrayOfo[0], paramArrayOfo[1]);
    float f2 = a(paramArrayOfo[1], paramArrayOfo[2]);
    float f3 = a(paramArrayOfo[0], paramArrayOfo[2]);
    if (f2 >= f1 && f2 >= f3) {
      o3 = paramArrayOfo[0];
      o1 = paramArrayOfo[1];
      o2 = paramArrayOfo[2];
    } else if (f3 >= f2 && f3 >= f1) {
      o3 = paramArrayOfo[1];
      o1 = paramArrayOfo[0];
      o2 = paramArrayOfo[2];
    } else {
      o3 = paramArrayOfo[2];
      o1 = paramArrayOfo[0];
      o2 = paramArrayOfo[1];
    } 
    o o5 = o1;
    o o4 = o2;
    if (a(o1, o3, o2) < 0.0F) {
      o4 = o1;
      o5 = o2;
    } 
    paramArrayOfo[0] = o5;
    paramArrayOfo[1] = o3;
    paramArrayOfo[2] = o4;
  }
  
  public final float a() {
    return this.a;
  }
  
  public final float b() {
    return this.b;
  }
  
  public final boolean equals(Object paramObject) {
    boolean bool = paramObject instanceof o;
    boolean bool1 = false;
    if (bool) {
      paramObject = paramObject;
      bool = bool1;
      if (this.a == ((o)paramObject).a) {
        bool = bool1;
        if (this.b == ((o)paramObject).b)
          bool = true; 
      } 
      return bool;
    } 
    return false;
  }
  
  public final int hashCode() {
    return Float.floatToIntBits(this.a) * 31 + Float.floatToIntBits(this.b);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder(25);
    stringBuilder.append('(');
    stringBuilder.append(this.a);
    stringBuilder.append(',');
    stringBuilder.append(this.b);
    stringBuilder.append(')');
    return stringBuilder.toString();
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\o.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */