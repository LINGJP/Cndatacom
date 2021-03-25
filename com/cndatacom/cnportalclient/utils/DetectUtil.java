package com.cndatacom.cnportalclient.utils;

import android.text.TextUtils;
import com.cndatacom.cnportalclient.model.InnerState;
import java.util.Random;
import java.util.TreeMap;
import org.json.JSONArray;
import org.json.JSONObject;

public class DetectUtil {
  private static String a = "detect.json";
  
  private static Random b = new Random();
  
  private static int c = -1;
  
  private static TreeMap<Integer, String> d = new TreeMap<Integer, String>();
  
  private static TreeMap<Integer, Double> e = new TreeMap<Integer, Double>();
  
  private static TreeMap<Double, Integer> f = new TreeMap<Double, Integer>();
  
  static {
    a();
  }
  
  private static void a() {
    d.clear();
    e.clear();
    f.clear();
    c = -1;
    try {
      String str = CdcUtils.readFileText(a).trim();
      if (!TextUtils.isEmpty(str)) {
        JSONObject jSONObject = new JSONObject(str);
        JSONArray jSONArray2 = jSONObject.getJSONArray("detect");
        boolean bool = false;
        int i;
        for (i = 0; i < jSONArray2.length(); i++)
          d.put(Integer.valueOf(i), jSONArray2.getString(i)); 
        JSONArray jSONArray1 = jSONObject.getJSONArray("weight");
        for (i = bool; i < jSONArray1.length(); i++)
          e.put(Integer.valueOf(i), Double.valueOf(Double.parseDouble(jSONArray1.getString(i)))); 
      } 
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
  }
  
  public static String getDetectUrl() {
    // Byte code:
    //   0: ldc com/cndatacom/cnportalclient/utils/DetectUtil
    //   2: monitorenter
    //   3: getstatic com/cndatacom/cnportalclient/utils/DetectUtil.d : Ljava/util/TreeMap;
    //   6: invokevirtual size : ()I
    //   9: ifne -> 18
    //   12: ldc com/cndatacom/cnportalclient/utils/DetectUtil
    //   14: monitorexit
    //   15: ldc ''
    //   17: areturn
    //   18: getstatic com/cndatacom/cnportalclient/utils/DetectUtil.f : Ljava/util/TreeMap;
    //   21: invokevirtual clear : ()V
    //   24: getstatic com/cndatacom/cnportalclient/utils/DetectUtil.e : Ljava/util/TreeMap;
    //   27: invokevirtual keySet : ()Ljava/util/Set;
    //   30: invokeinterface iterator : ()Ljava/util/Iterator;
    //   35: astore #5
    //   37: dconst_0
    //   38: dstore_2
    //   39: dconst_0
    //   40: dstore_0
    //   41: aload #5
    //   43: invokeinterface hasNext : ()Z
    //   48: ifeq -> 89
    //   51: aload #5
    //   53: invokeinterface next : ()Ljava/lang/Object;
    //   58: checkcast java/lang/Integer
    //   61: invokevirtual intValue : ()I
    //   64: istore #4
    //   66: dload_0
    //   67: getstatic com/cndatacom/cnportalclient/utils/DetectUtil.e : Ljava/util/TreeMap;
    //   70: iload #4
    //   72: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   75: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   78: checkcast java/lang/Double
    //   81: invokevirtual doubleValue : ()D
    //   84: dadd
    //   85: dstore_0
    //   86: goto -> 41
    //   89: getstatic com/cndatacom/cnportalclient/utils/DetectUtil.e : Ljava/util/TreeMap;
    //   92: invokevirtual keySet : ()Ljava/util/Set;
    //   95: invokeinterface iterator : ()Ljava/util/Iterator;
    //   100: astore #5
    //   102: aload #5
    //   104: invokeinterface hasNext : ()Z
    //   109: ifeq -> 172
    //   112: aload #5
    //   114: invokeinterface next : ()Ljava/lang/Object;
    //   119: checkcast java/lang/Integer
    //   122: invokevirtual intValue : ()I
    //   125: istore #4
    //   127: dload_2
    //   128: getstatic com/cndatacom/cnportalclient/utils/DetectUtil.e : Ljava/util/TreeMap;
    //   131: iload #4
    //   133: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   136: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   139: checkcast java/lang/Double
    //   142: invokevirtual doubleValue : ()D
    //   145: dload_0
    //   146: ddiv
    //   147: ldc2_w 100.0
    //   150: dmul
    //   151: dadd
    //   152: dstore_2
    //   153: getstatic com/cndatacom/cnportalclient/utils/DetectUtil.f : Ljava/util/TreeMap;
    //   156: dload_2
    //   157: invokestatic valueOf : (D)Ljava/lang/Double;
    //   160: iload #4
    //   162: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   165: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   168: pop
    //   169: goto -> 102
    //   172: getstatic com/cndatacom/cnportalclient/utils/DetectUtil.b : Ljava/util/Random;
    //   175: invokevirtual nextDouble : ()D
    //   178: dstore_0
    //   179: getstatic com/cndatacom/cnportalclient/utils/DetectUtil.f : Ljava/util/TreeMap;
    //   182: dload_0
    //   183: ldc2_w 100.0
    //   186: dmul
    //   187: dconst_1
    //   188: dadd
    //   189: invokestatic valueOf : (D)Ljava/lang/Double;
    //   192: iconst_1
    //   193: invokevirtual tailMap : (Ljava/lang/Object;Z)Ljava/util/NavigableMap;
    //   196: astore #5
    //   198: aload #5
    //   200: ifnull -> 272
    //   203: aload #5
    //   205: invokeinterface isEmpty : ()Z
    //   210: ifeq -> 216
    //   213: goto -> 272
    //   216: aload #5
    //   218: invokeinterface firstKey : ()Ljava/lang/Object;
    //   223: checkcast java/lang/Double
    //   226: invokevirtual doubleValue : ()D
    //   229: dstore_0
    //   230: getstatic com/cndatacom/cnportalclient/utils/DetectUtil.f : Ljava/util/TreeMap;
    //   233: dload_0
    //   234: invokestatic valueOf : (D)Ljava/lang/Double;
    //   237: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   240: checkcast java/lang/Integer
    //   243: invokevirtual intValue : ()I
    //   246: putstatic com/cndatacom/cnportalclient/utils/DetectUtil.c : I
    //   249: getstatic com/cndatacom/cnportalclient/utils/DetectUtil.d : Ljava/util/TreeMap;
    //   252: getstatic com/cndatacom/cnportalclient/utils/DetectUtil.c : I
    //   255: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   258: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   261: checkcast java/lang/String
    //   264: astore #5
    //   266: ldc com/cndatacom/cnportalclient/utils/DetectUtil
    //   268: monitorexit
    //   269: aload #5
    //   271: areturn
    //   272: ldc com/cndatacom/cnportalclient/utils/DetectUtil
    //   274: monitorexit
    //   275: ldc ''
    //   277: areturn
    //   278: astore #5
    //   280: ldc com/cndatacom/cnportalclient/utils/DetectUtil
    //   282: monitorexit
    //   283: aload #5
    //   285: athrow
    // Exception table:
    //   from	to	target	type
    //   3	12	278	finally
    //   18	37	278	finally
    //   41	86	278	finally
    //   89	102	278	finally
    //   102	169	278	finally
    //   172	198	278	finally
    //   203	213	278	finally
    //   216	266	278	finally
  }
  
  public static boolean hasDetectUrl() {
    // Byte code:
    //   0: ldc com/cndatacom/cnportalclient/utils/DetectUtil
    //   2: monitorenter
    //   3: getstatic com/cndatacom/cnportalclient/utils/DetectUtil.d : Ljava/util/TreeMap;
    //   6: invokevirtual isEmpty : ()Z
    //   9: istore_0
    //   10: ldc com/cndatacom/cnportalclient/utils/DetectUtil
    //   12: monitorexit
    //   13: iload_0
    //   14: iconst_1
    //   15: ixor
    //   16: ireturn
    //   17: astore_1
    //   18: ldc com/cndatacom/cnportalclient/utils/DetectUtil
    //   20: monitorexit
    //   21: aload_1
    //   22: athrow
    // Exception table:
    //   from	to	target	type
    //   3	10	17	finally
  }
  
  public static void removeDetectUrl() {
    // Byte code:
    //   0: ldc com/cndatacom/cnportalclient/utils/DetectUtil
    //   2: monitorenter
    //   3: getstatic com/cndatacom/cnportalclient/utils/DetectUtil.c : I
    //   6: iconst_m1
    //   7: if_icmpeq -> 36
    //   10: getstatic com/cndatacom/cnportalclient/utils/DetectUtil.d : Ljava/util/TreeMap;
    //   13: getstatic com/cndatacom/cnportalclient/utils/DetectUtil.c : I
    //   16: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   19: invokevirtual remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   22: pop
    //   23: getstatic com/cndatacom/cnportalclient/utils/DetectUtil.e : Ljava/util/TreeMap;
    //   26: getstatic com/cndatacom/cnportalclient/utils/DetectUtil.c : I
    //   29: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   32: invokevirtual remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   35: pop
    //   36: ldc com/cndatacom/cnportalclient/utils/DetectUtil
    //   38: monitorexit
    //   39: return
    //   40: astore_0
    //   41: ldc com/cndatacom/cnportalclient/utils/DetectUtil
    //   43: monitorexit
    //   44: aload_0
    //   45: athrow
    // Exception table:
    //   from	to	target	type
    //   3	36	40	finally
  }
  
  public static String replaceDetectUrl(String paramString1, String paramString2, String paramString3) {
    // Byte code:
    //   0: ldc com/cndatacom/cnportalclient/utils/DetectUtil
    //   2: monitorenter
    //   3: aload_0
    //   4: invokestatic isEmpty : (Ljava/lang/CharSequence;)Z
    //   7: ifne -> 57
    //   10: aload_1
    //   11: invokestatic isEmpty : (Ljava/lang/CharSequence;)Z
    //   14: ifne -> 57
    //   17: aload_1
    //   18: aload_2
    //   19: invokevirtual indexOf : (Ljava/lang/String;)I
    //   22: istore_3
    //   23: new java/lang/StringBuilder
    //   26: dup
    //   27: invokespecial <init> : ()V
    //   30: astore_2
    //   31: aload_2
    //   32: aload_0
    //   33: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   36: pop
    //   37: aload_2
    //   38: aload_1
    //   39: iload_3
    //   40: invokevirtual substring : (I)Ljava/lang/String;
    //   43: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   46: pop
    //   47: aload_2
    //   48: invokevirtual toString : ()Ljava/lang/String;
    //   51: astore_0
    //   52: ldc com/cndatacom/cnportalclient/utils/DetectUtil
    //   54: monitorexit
    //   55: aload_0
    //   56: areturn
    //   57: ldc com/cndatacom/cnportalclient/utils/DetectUtil
    //   59: monitorexit
    //   60: aload_1
    //   61: areturn
    //   62: astore_0
    //   63: ldc com/cndatacom/cnportalclient/utils/DetectUtil
    //   65: monitorexit
    //   66: aload_0
    //   67: athrow
    // Exception table:
    //   from	to	target	type
    //   3	52	62	finally
  }
  
  public static void saveDetectUrlData(InnerState paramInnerState) {
    /* monitor enter TypeReferenceDotClassExpression{ObjectType{com/cndatacom/cnportalclient/utils/DetectUtil}} */
    try {
      String str1 = paramInnerState.getFuncfgUrl("Detect", "weight", "");
      String str2 = paramInnerState.getFuncfgUrl("Detect", "");
      JSONObject jSONObject = new JSONObject();
      JSONArray jSONArray1 = new JSONArray();
      boolean bool = TextUtils.isEmpty(str2);
      byte b2 = 0;
      byte b1 = 0;
      if (!bool) {
        String[] arrayOfString = str2.split(",");
        for (int j = 0; j < arrayOfString.length; j++)
          jSONArray1.put(arrayOfString[j]); 
      } 
      JSONArray jSONArray2 = new JSONArray();
      int i = b2;
      if (!TextUtils.isEmpty(str1)) {
        String[] arrayOfString = str1.split(",");
        for (i = b1; i < arrayOfString.length; i++)
          jSONArray2.put(arrayOfString[i]); 
      } else {
        while (i < jSONArray1.length()) {
          jSONArray2.put(1);
          i++;
        } 
      } 
      jSONObject.put("detect", jSONArray1);
      jSONObject.put("weight", jSONArray2);
      CdcUtils.writeFileText(jSONObject.toString(), a);
      a();
    } catch (Exception exception) {
      exception.printStackTrace();
    } finally {}
    /* monitor exit TypeReferenceDotClassExpression{ObjectType{com/cndatacom/cnportalclient/utils/DetectUtil}} */
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclien\\utils\DetectUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */