package com.a.a.b;

public abstract class i {
  private static i a = new f();
  
  public static i a() {
    return a;
  }
  
  protected static void a(b paramb, float[] paramArrayOffloat) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual d : ()I
    //   4: istore #4
    //   6: aload_0
    //   7: invokevirtual e : ()I
    //   10: istore #5
    //   12: iconst_0
    //   13: istore_3
    //   14: iconst_1
    //   15: istore_2
    //   16: iload_3
    //   17: aload_1
    //   18: arraylength
    //   19: if_icmpge -> 149
    //   22: iload_2
    //   23: ifeq -> 149
    //   26: aload_1
    //   27: iload_3
    //   28: faload
    //   29: f2i
    //   30: istore_2
    //   31: iload_3
    //   32: iconst_1
    //   33: iadd
    //   34: istore #6
    //   36: aload_1
    //   37: iload #6
    //   39: faload
    //   40: f2i
    //   41: istore #7
    //   43: iload_2
    //   44: iconst_m1
    //   45: if_icmplt -> 145
    //   48: iload_2
    //   49: iload #4
    //   51: if_icmpgt -> 145
    //   54: iload #7
    //   56: iconst_m1
    //   57: if_icmplt -> 145
    //   60: iload #7
    //   62: iload #5
    //   64: if_icmple -> 70
    //   67: goto -> 145
    //   70: iload_2
    //   71: iconst_m1
    //   72: if_icmpne -> 84
    //   75: aload_1
    //   76: iload_3
    //   77: fconst_0
    //   78: fastore
    //   79: iconst_1
    //   80: istore_2
    //   81: goto -> 103
    //   84: iload_2
    //   85: iload #4
    //   87: if_icmpne -> 101
    //   90: aload_1
    //   91: iload_3
    //   92: iload #4
    //   94: iconst_1
    //   95: isub
    //   96: i2f
    //   97: fastore
    //   98: goto -> 79
    //   101: iconst_0
    //   102: istore_2
    //   103: iload #7
    //   105: iconst_m1
    //   106: if_icmpne -> 119
    //   109: aload_1
    //   110: iload #6
    //   112: fconst_0
    //   113: fastore
    //   114: iconst_1
    //   115: istore_2
    //   116: goto -> 138
    //   119: iload #7
    //   121: iload #5
    //   123: if_icmpne -> 138
    //   126: aload_1
    //   127: iload #6
    //   129: iload #5
    //   131: iconst_1
    //   132: isub
    //   133: i2f
    //   134: fastore
    //   135: goto -> 114
    //   138: iload_3
    //   139: iconst_2
    //   140: iadd
    //   141: istore_3
    //   142: goto -> 16
    //   145: invokestatic a : ()Lcom/a/a/i;
    //   148: athrow
    //   149: aload_1
    //   150: arraylength
    //   151: iconst_2
    //   152: isub
    //   153: istore_3
    //   154: iconst_1
    //   155: istore_2
    //   156: iload_3
    //   157: iflt -> 287
    //   160: iload_2
    //   161: ifeq -> 287
    //   164: aload_1
    //   165: iload_3
    //   166: faload
    //   167: f2i
    //   168: istore_2
    //   169: iload_3
    //   170: iconst_1
    //   171: iadd
    //   172: istore #6
    //   174: aload_1
    //   175: iload #6
    //   177: faload
    //   178: f2i
    //   179: istore #7
    //   181: iload_2
    //   182: iconst_m1
    //   183: if_icmplt -> 283
    //   186: iload_2
    //   187: iload #4
    //   189: if_icmpgt -> 283
    //   192: iload #7
    //   194: iconst_m1
    //   195: if_icmplt -> 283
    //   198: iload #7
    //   200: iload #5
    //   202: if_icmple -> 208
    //   205: goto -> 283
    //   208: iload_2
    //   209: iconst_m1
    //   210: if_icmpne -> 222
    //   213: aload_1
    //   214: iload_3
    //   215: fconst_0
    //   216: fastore
    //   217: iconst_1
    //   218: istore_2
    //   219: goto -> 241
    //   222: iload_2
    //   223: iload #4
    //   225: if_icmpne -> 239
    //   228: aload_1
    //   229: iload_3
    //   230: iload #4
    //   232: iconst_1
    //   233: isub
    //   234: i2f
    //   235: fastore
    //   236: goto -> 217
    //   239: iconst_0
    //   240: istore_2
    //   241: iload #7
    //   243: iconst_m1
    //   244: if_icmpne -> 257
    //   247: aload_1
    //   248: iload #6
    //   250: fconst_0
    //   251: fastore
    //   252: iconst_1
    //   253: istore_2
    //   254: goto -> 276
    //   257: iload #7
    //   259: iload #5
    //   261: if_icmpne -> 276
    //   264: aload_1
    //   265: iload #6
    //   267: iload #5
    //   269: iconst_1
    //   270: isub
    //   271: i2f
    //   272: fastore
    //   273: goto -> 252
    //   276: iload_3
    //   277: iconst_2
    //   278: isub
    //   279: istore_3
    //   280: goto -> 156
    //   283: invokestatic a : ()Lcom/a/a/i;
    //   286: athrow
    //   287: return
  }
  
  public abstract b a(b paramb, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, float paramFloat9, float paramFloat10, float paramFloat11, float paramFloat12, float paramFloat13, float paramFloat14, float paramFloat15, float paramFloat16);
  
  public abstract b a(b paramb, int paramInt1, int paramInt2, k paramk);
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\b\i.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */