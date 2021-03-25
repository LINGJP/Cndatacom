package com.a.a.g.b;

import com.a.a.b.a.a;
import com.a.a.b.b;
import com.a.a.b.g;
import com.a.a.b.i;
import com.a.a.b.k;
import com.a.a.e;
import com.a.a.i;
import com.a.a.o;
import com.a.a.p;
import java.util.Map;

public class c {
  private final b a;
  
  private p b;
  
  public c(b paramb) {
    this.a = paramb;
  }
  
  private float a(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    float f1;
    float f2 = b(paramInt1, paramInt2, paramInt3, paramInt4);
    paramInt3 = paramInt1 - paramInt3 - paramInt1;
    boolean bool = false;
    if (paramInt3 < 0) {
      f1 = paramInt1 / (paramInt1 - paramInt3);
      paramInt3 = 0;
    } else if (paramInt3 >= this.a.d()) {
      f1 = (this.a.d() - 1 - paramInt1) / (paramInt3 - paramInt1);
      paramInt3 = this.a.d() - 1;
    } else {
      f1 = 1.0F;
    } 
    float f3 = paramInt2;
    paramInt4 = (int)(f3 - (paramInt4 - paramInt2) * f1);
    if (paramInt4 < 0) {
      f1 = f3 / (paramInt2 - paramInt4);
      paramInt4 = bool;
    } else if (paramInt4 >= this.a.e()) {
      f1 = (this.a.e() - 1 - paramInt2) / (paramInt4 - paramInt2);
      paramInt4 = this.a.e() - 1;
    } else {
      f1 = 1.0F;
    } 
    return f2 + b(paramInt1, paramInt2, (int)(paramInt1 + (paramInt3 - paramInt1) * f1), paramInt4) - 1.0F;
  }
  
  private float a(o paramo1, o paramo2) {
    float f1 = a((int)paramo1.a(), (int)paramo1.b(), (int)paramo2.a(), (int)paramo2.b());
    float f2 = a((int)paramo2.a(), (int)paramo2.b(), (int)paramo1.a(), (int)paramo1.b());
    return Float.isNaN(f1) ? (f2 / 7.0F) : (Float.isNaN(f2) ? (f1 / 7.0F) : ((f1 + f2) / 14.0F));
  }
  
  private static int a(o paramo1, o paramo2, o paramo3, float paramFloat) {
    int i = (a.a(o.a(paramo1, paramo2) / paramFloat) + a.a(o.a(paramo1, paramo3) / paramFloat) >> 1) + 7;
    int j = i & 0x3;
    if (j != 0) {
      switch (j) {
        default:
          return i;
        case 3:
          throw i.a();
        case 2:
          break;
      } 
      return i - 1;
    } 
    return i + 1;
  }
  
  private static b a(b paramb, k paramk, int paramInt) {
    return i.a().a(paramb, paramInt, paramInt, paramk);
  }
  
  private static k a(o paramo1, o paramo2, o paramo3, o paramo4, int paramInt) {
    float f1;
    float f2;
    float f3;
    float f4 = paramInt - 3.5F;
    if (paramo4 != null) {
      f2 = paramo4.a();
      f3 = paramo4.b();
      f1 = f4 - 3.0F;
    } else {
      f2 = paramo2.a();
      float f6 = paramo1.a();
      float f7 = paramo3.a();
      f1 = paramo2.b();
      f3 = paramo1.b();
      float f5 = paramo3.b();
      f2 = f2 - f6 + f7;
      f3 = f1 - f3 + f5;
      f1 = f4;
    } 
    return k.a(3.5F, 3.5F, f4, 3.5F, f1, f1, 3.5F, f4, paramo1.a(), paramo1.b(), paramo2.a(), paramo2.b(), f2, f3, paramo3.a(), paramo3.b());
  }
  
  private float b(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    // Byte code:
    //   0: iload #4
    //   2: iload_2
    //   3: isub
    //   4: invokestatic abs : (I)I
    //   7: iload_3
    //   8: iload_1
    //   9: isub
    //   10: invokestatic abs : (I)I
    //   13: if_icmple -> 22
    //   16: iconst_1
    //   17: istore #5
    //   19: goto -> 25
    //   22: iconst_0
    //   23: istore #5
    //   25: iload #5
    //   27: ifeq -> 48
    //   30: iload_1
    //   31: istore #6
    //   33: iload_3
    //   34: istore #8
    //   36: iload #4
    //   38: istore_1
    //   39: iload_2
    //   40: istore_3
    //   41: iload #6
    //   43: istore #4
    //   45: goto -> 63
    //   48: iload_1
    //   49: istore #6
    //   51: iload_3
    //   52: istore_1
    //   53: iload #4
    //   55: istore #8
    //   57: iload_2
    //   58: istore #4
    //   60: iload #6
    //   62: istore_3
    //   63: iload_1
    //   64: iload_3
    //   65: isub
    //   66: invokestatic abs : (I)I
    //   69: istore #15
    //   71: iload #8
    //   73: iload #4
    //   75: isub
    //   76: invokestatic abs : (I)I
    //   79: istore #16
    //   81: iload #15
    //   83: ineg
    //   84: istore #6
    //   86: iconst_m1
    //   87: istore #10
    //   89: iload_3
    //   90: iload_1
    //   91: if_icmpge -> 100
    //   94: iconst_1
    //   95: istore #9
    //   97: goto -> 103
    //   100: iconst_m1
    //   101: istore #9
    //   103: iload #4
    //   105: iload #8
    //   107: if_icmpge -> 113
    //   110: iconst_1
    //   111: istore #10
    //   113: iload_1
    //   114: iload #9
    //   116: iadd
    //   117: istore #11
    //   119: iload #4
    //   121: istore_2
    //   122: iload #6
    //   124: iconst_1
    //   125: ishr
    //   126: istore #7
    //   128: iconst_0
    //   129: istore #6
    //   131: iload_3
    //   132: istore_1
    //   133: iload #5
    //   135: istore #12
    //   137: iload_1
    //   138: iload #11
    //   140: if_icmpeq -> 283
    //   143: iload #12
    //   145: ifeq -> 154
    //   148: iload_2
    //   149: istore #13
    //   151: goto -> 157
    //   154: iload_1
    //   155: istore #13
    //   157: iload #12
    //   159: ifeq -> 168
    //   162: iload_1
    //   163: istore #14
    //   165: goto -> 171
    //   168: iload_2
    //   169: istore #14
    //   171: iload #6
    //   173: iconst_1
    //   174: if_icmpne -> 183
    //   177: iconst_1
    //   178: istore #17
    //   180: goto -> 186
    //   183: iconst_0
    //   184: istore #17
    //   186: iload #6
    //   188: istore #5
    //   190: iload #17
    //   192: aload_0
    //   193: getfield a : Lcom/a/a/b/b;
    //   196: iload #13
    //   198: iload #14
    //   200: invokevirtual a : (II)Z
    //   203: if_icmpne -> 227
    //   206: iload #6
    //   208: iconst_2
    //   209: if_icmpne -> 221
    //   212: iload_1
    //   213: iload_2
    //   214: iload_3
    //   215: iload #4
    //   217: invokestatic a : (IIII)F
    //   220: freturn
    //   221: iload #6
    //   223: iconst_1
    //   224: iadd
    //   225: istore #5
    //   227: iload #7
    //   229: iload #16
    //   231: iadd
    //   232: istore #13
    //   234: iload_2
    //   235: istore #6
    //   237: iload #13
    //   239: istore #7
    //   241: iload #13
    //   243: ifle -> 268
    //   246: iload_2
    //   247: iload #8
    //   249: if_icmpne -> 255
    //   252: goto -> 287
    //   255: iload_2
    //   256: iload #10
    //   258: iadd
    //   259: istore #6
    //   261: iload #13
    //   263: iload #15
    //   265: isub
    //   266: istore #7
    //   268: iload_1
    //   269: iload #9
    //   271: iadd
    //   272: istore_1
    //   273: iload #6
    //   275: istore_2
    //   276: iload #5
    //   278: istore #6
    //   280: goto -> 137
    //   283: iload #6
    //   285: istore #5
    //   287: iload #5
    //   289: iconst_2
    //   290: if_icmpne -> 304
    //   293: iload #11
    //   295: iload #8
    //   297: iload_3
    //   298: iload #4
    //   300: invokestatic a : (IIII)F
    //   303: freturn
    //   304: ldc NaN
    //   306: freturn
  }
  
  protected final float a(o paramo1, o paramo2, o paramo3) {
    return (a(paramo1, paramo2) + a(paramo1, paramo3)) / 2.0F;
  }
  
  protected final g a(f paramf) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual b : ()Lcom/a/a/g/b/d;
    //   4: astore #15
    //   6: aload_1
    //   7: invokevirtual c : ()Lcom/a/a/g/b/d;
    //   10: astore #16
    //   12: aload_1
    //   13: invokevirtual a : ()Lcom/a/a/g/b/d;
    //   16: astore #17
    //   18: aload_0
    //   19: aload #15
    //   21: aload #16
    //   23: aload #17
    //   25: invokevirtual a : (Lcom/a/a/o;Lcom/a/a/o;Lcom/a/a/o;)F
    //   28: fstore_2
    //   29: fload_2
    //   30: fconst_1
    //   31: fcmpg
    //   32: ifge -> 39
    //   35: invokestatic a : ()Lcom/a/a/i;
    //   38: athrow
    //   39: aload #15
    //   41: aload #16
    //   43: aload #17
    //   45: fload_2
    //   46: invokestatic a : (Lcom/a/a/o;Lcom/a/a/o;Lcom/a/a/o;F)I
    //   49: istore #11
    //   51: iload #11
    //   53: invokestatic a : (I)Lcom/a/a/g/a/i;
    //   56: astore #18
    //   58: aload #18
    //   60: invokevirtual d : ()I
    //   63: istore #10
    //   65: aconst_null
    //   66: astore #14
    //   68: aload #14
    //   70: astore_1
    //   71: aload #18
    //   73: invokevirtual b : ()[I
    //   76: arraylength
    //   77: ifle -> 225
    //   80: aload #16
    //   82: invokevirtual a : ()F
    //   85: fstore_3
    //   86: aload #15
    //   88: invokevirtual a : ()F
    //   91: fstore #4
    //   93: aload #17
    //   95: invokevirtual a : ()F
    //   98: fstore #5
    //   100: aload #16
    //   102: invokevirtual b : ()F
    //   105: fstore #6
    //   107: aload #15
    //   109: invokevirtual b : ()F
    //   112: fstore #7
    //   114: aload #17
    //   116: invokevirtual b : ()F
    //   119: fstore #8
    //   121: fconst_1
    //   122: ldc 3.0
    //   124: iload #10
    //   126: bipush #7
    //   128: isub
    //   129: i2f
    //   130: fdiv
    //   131: fsub
    //   132: fstore #9
    //   134: aload #15
    //   136: invokevirtual a : ()F
    //   139: fload_3
    //   140: fload #4
    //   142: fsub
    //   143: fload #5
    //   145: fadd
    //   146: aload #15
    //   148: invokevirtual a : ()F
    //   151: fsub
    //   152: fload #9
    //   154: fmul
    //   155: fadd
    //   156: f2i
    //   157: istore #12
    //   159: aload #15
    //   161: invokevirtual b : ()F
    //   164: fload #9
    //   166: fload #6
    //   168: fload #7
    //   170: fsub
    //   171: fload #8
    //   173: fadd
    //   174: aload #15
    //   176: invokevirtual b : ()F
    //   179: fsub
    //   180: fmul
    //   181: fadd
    //   182: f2i
    //   183: istore #13
    //   185: iconst_4
    //   186: istore #10
    //   188: aload #14
    //   190: astore_1
    //   191: iload #10
    //   193: bipush #16
    //   195: if_icmpgt -> 225
    //   198: iload #10
    //   200: i2f
    //   201: fstore_3
    //   202: aload_0
    //   203: fload_2
    //   204: iload #12
    //   206: iload #13
    //   208: fload_3
    //   209: invokevirtual a : (FIIF)Lcom/a/a/g/b/a;
    //   212: astore_1
    //   213: goto -> 225
    //   216: iload #10
    //   218: iconst_1
    //   219: ishl
    //   220: istore #10
    //   222: goto -> 188
    //   225: aload #15
    //   227: aload #16
    //   229: aload #17
    //   231: aload_1
    //   232: iload #11
    //   234: invokestatic a : (Lcom/a/a/o;Lcom/a/a/o;Lcom/a/a/o;Lcom/a/a/o;I)Lcom/a/a/b/k;
    //   237: astore #14
    //   239: aload_0
    //   240: getfield a : Lcom/a/a/b/b;
    //   243: aload #14
    //   245: iload #11
    //   247: invokestatic a : (Lcom/a/a/b/b;Lcom/a/a/b/k;I)Lcom/a/a/b/b;
    //   250: astore #14
    //   252: aload_1
    //   253: ifnonnull -> 279
    //   256: iconst_3
    //   257: anewarray com/a/a/o
    //   260: astore_1
    //   261: aload_1
    //   262: iconst_0
    //   263: aload #17
    //   265: aastore
    //   266: aload_1
    //   267: iconst_1
    //   268: aload #15
    //   270: aastore
    //   271: aload_1
    //   272: iconst_2
    //   273: aload #16
    //   275: aastore
    //   276: goto -> 303
    //   279: iconst_4
    //   280: anewarray com/a/a/o
    //   283: dup
    //   284: iconst_0
    //   285: aload #17
    //   287: aastore
    //   288: dup
    //   289: iconst_1
    //   290: aload #15
    //   292: aastore
    //   293: dup
    //   294: iconst_2
    //   295: aload #16
    //   297: aastore
    //   298: dup
    //   299: iconst_3
    //   300: aload_1
    //   301: aastore
    //   302: astore_1
    //   303: new com/a/a/b/g
    //   306: dup
    //   307: aload #14
    //   309: aload_1
    //   310: invokespecial <init> : (Lcom/a/a/b/b;[Lcom/a/a/o;)V
    //   313: areturn
    //   314: astore_1
    //   315: goto -> 216
    // Exception table:
    //   from	to	target	type
    //   202	213	314	com/a/a/i
  }
  
  public final g a(Map<e, ?> paramMap) {
    p p1;
    if (paramMap == null) {
      p1 = null;
    } else {
      p1 = (p)paramMap.get(e.i);
    } 
    this.b = p1;
    return a((new e(this.a, this.b)).a(paramMap));
  }
  
  protected final a a(float paramFloat1, int paramInt1, int paramInt2, float paramFloat2) {
    int j = (int)(paramFloat2 * paramFloat1);
    int i = Math.max(0, paramInt1 - j);
    paramInt1 = Math.min(this.a.d() - 1, paramInt1 + j) - i;
    paramFloat2 = paramInt1;
    float f = 3.0F * paramFloat1;
    if (paramFloat2 < f)
      throw i.a(); 
    int k = Math.max(0, paramInt2 - j);
    paramInt2 = Math.min(this.a.e() - 1, paramInt2 + j) - k;
    if (paramInt2 < f)
      throw i.a(); 
    return (new b(this.a, i, k, paramInt1, paramInt2, paramFloat1, this.b)).a();
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\g\b\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */