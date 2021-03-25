package com.a.a.e;

import com.a.a.a;
import com.a.a.b.a;
import com.a.a.e;
import com.a.a.m;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public final class j extends k {
  private final p[] a;
  
  public j(Map<e, ?> paramMap) {
    Collection collection;
    if (paramMap == null) {
      paramMap = null;
    } else {
      collection = (Collection)paramMap.get(e.c);
    } 
    ArrayList<e> arrayList = new ArrayList();
    if (collection != null) {
      if (collection.contains(a.h)) {
        arrayList.add(new e());
      } else if (collection.contains(a.o)) {
        arrayList.add(new l());
      } 
      if (collection.contains(a.g))
        arrayList.add(new f()); 
      if (collection.contains(a.p))
        arrayList.add(new q()); 
    } 
    if (arrayList.isEmpty()) {
      arrayList.add(new e());
      arrayList.add(new f());
      arrayList.add(new q());
    } 
    this.a = arrayList.<p>toArray(new p[arrayList.size()]);
  }
  
  public m a(int paramInt, a parama, Map<e, ?> paramMap) {
    // Byte code:
    //   0: aload_2
    //   1: invokestatic a : (Lcom/a/a/b/a;)[I
    //   4: astore #8
    //   6: aload_0
    //   7: getfield a : [Lcom/a/a/e/p;
    //   10: astore #9
    //   12: aload #9
    //   14: arraylength
    //   15: istore #6
    //   17: iconst_0
    //   18: istore #5
    //   20: iconst_0
    //   21: istore #4
    //   23: iload #4
    //   25: iload #6
    //   27: if_icmpge -> 188
    //   30: aload #9
    //   32: iload #4
    //   34: aaload
    //   35: astore #7
    //   37: aload #7
    //   39: iload_1
    //   40: aload_2
    //   41: aload #8
    //   43: aload_3
    //   44: invokevirtual a : (ILcom/a/a/b/a;[ILjava/util/Map;)Lcom/a/a/m;
    //   47: astore #7
    //   49: aload #7
    //   51: invokevirtual d : ()Lcom/a/a/a;
    //   54: getstatic com/a/a/a.h : Lcom/a/a/a;
    //   57: if_acmpne -> 79
    //   60: aload #7
    //   62: invokevirtual a : ()Ljava/lang/String;
    //   65: iconst_0
    //   66: invokevirtual charAt : (I)C
    //   69: bipush #48
    //   71: if_icmpne -> 79
    //   74: iconst_1
    //   75: istore_1
    //   76: goto -> 81
    //   79: iconst_0
    //   80: istore_1
    //   81: aload_3
    //   82: ifnonnull -> 90
    //   85: aconst_null
    //   86: astore_2
    //   87: goto -> 103
    //   90: aload_3
    //   91: getstatic com/a/a/e.c : Lcom/a/a/e;
    //   94: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   99: checkcast java/util/Collection
    //   102: astore_2
    //   103: aload_2
    //   104: ifnull -> 123
    //   107: iload #5
    //   109: istore #4
    //   111: aload_2
    //   112: getstatic com/a/a/a.o : Lcom/a/a/a;
    //   115: invokeinterface contains : (Ljava/lang/Object;)Z
    //   120: ifeq -> 126
    //   123: iconst_1
    //   124: istore #4
    //   126: iload_1
    //   127: ifeq -> 176
    //   130: iload #4
    //   132: ifeq -> 176
    //   135: new com/a/a/m
    //   138: dup
    //   139: aload #7
    //   141: invokevirtual a : ()Ljava/lang/String;
    //   144: iconst_1
    //   145: invokevirtual substring : (I)Ljava/lang/String;
    //   148: aload #7
    //   150: invokevirtual b : ()[B
    //   153: aload #7
    //   155: invokevirtual c : ()[Lcom/a/a/o;
    //   158: getstatic com/a/a/a.o : Lcom/a/a/a;
    //   161: invokespecial <init> : (Ljava/lang/String;[B[Lcom/a/a/o;Lcom/a/a/a;)V
    //   164: astore_2
    //   165: aload_2
    //   166: aload #7
    //   168: invokevirtual e : ()Ljava/util/Map;
    //   171: invokevirtual a : (Ljava/util/Map;)V
    //   174: aload_2
    //   175: areturn
    //   176: aload #7
    //   178: areturn
    //   179: iload #4
    //   181: iconst_1
    //   182: iadd
    //   183: istore #4
    //   185: goto -> 23
    //   188: invokestatic a : ()Lcom/a/a/i;
    //   191: athrow
    //   192: astore #7
    //   194: goto -> 179
    // Exception table:
    //   from	to	target	type
    //   37	49	192	com/a/a/l
  }
  
  public void a() {
    p[] arrayOfP = this.a;
    int m = arrayOfP.length;
    for (int i = 0; i < m; i++)
      arrayOfP[i].a(); 
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\e\j.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */