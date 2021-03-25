package com.cndatacom.cnportalclient.emulator;

import android.os.Build;
import android.text.TextUtils;
import com.cndatacom.cnportalclient.model.InnerState;
import com.cndatacom.cnportalclient.utils.ExLog;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class EmulatorUtils {
  private static EmulatorInfo a(InnerState paramInnerState) {
    try {
      JSONObject jSONObject = paramInnerState.getConfig(new String[] { "config" }).optJSONObject("emulator");
      if (jSONObject == null)
        return null; 
      EmulatorInfo emulatorInfo = new EmulatorInfo();
      ArrayList<EmulatorInfo.CheckItem> arrayList = new ArrayList();
      ArrayList<EmulatorInfo.ExecItem> arrayList1 = new ArrayList();
      ArrayList<EmulatorInfo.SureItem> arrayList2 = new ArrayList();
      emulatorInfo.setCount(jSONObject.optInt("count", 0));
      Object object2 = jSONObject.get("sure-item");
      if (object2 != null)
        if (object2 instanceof JSONObject) {
          a(arrayList2, (JSONObject)object2);
        } else if (object2 instanceof org.json.JSONArray) {
          object2 = object2;
          for (int i = 0; i < object2.length(); i++)
            a(arrayList2, object2.getJSONObject(i)); 
        }  
      object2 = jSONObject.get("exec-item");
      if (object2 != null)
        if (object2 instanceof JSONObject) {
          b(arrayList1, (JSONObject)object2);
        } else if (object2 instanceof org.json.JSONArray) {
          object2 = object2;
          for (int i = 0; i < object2.length(); i++)
            b(arrayList1, object2.getJSONObject(i)); 
        }  
      Object object1 = jSONObject.get("check-item");
      if (object1 != null)
        if (object1 instanceof JSONObject) {
          c(arrayList, (JSONObject)object1);
        } else if (object1 instanceof org.json.JSONArray) {
          object1 = object1;
          for (int i = 0; i < object1.length(); i++)
            c(arrayList, object1.getJSONObject(i)); 
        }  
      emulatorInfo.setSureItems(arrayList2);
      emulatorInfo.setCheckItems(arrayList);
      emulatorInfo.setExecItems(arrayList1);
      return emulatorInfo;
    } catch (Exception exception) {
      exception.getMessage();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("EmulatorUtils getEmulatorInfo ->");
      stringBuilder.append(exception.getMessage());
      ExLog.e(new String[] { stringBuilder.toString() });
      return null;
    } 
  }
  
  private static String a(BufferedInputStream paramBufferedInputStream) {
    if (paramBufferedInputStream == null)
      return ""; 
    byte[] arrayOfByte = new byte[512];
    StringBuilder stringBuilder = new StringBuilder();
    try {
      int i;
      do {
        i = paramBufferedInputStream.read(arrayOfByte);
        if (i <= 0)
          continue; 
        stringBuilder.append(new String(arrayOfByte, 0, i));
      } while (i >= 512);
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
    return stringBuilder.toString();
  }
  
  private static String a(String paramString) {
    // Byte code:
    //   0: ldc 'android.os.SystemProperties'
    //   2: invokestatic forName : (Ljava/lang/String;)Ljava/lang/Class;
    //   5: ldc 'get'
    //   7: iconst_1
    //   8: anewarray java/lang/Class
    //   11: dup
    //   12: iconst_0
    //   13: ldc java/lang/String
    //   15: aastore
    //   16: invokevirtual getMethod : (Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   19: aconst_null
    //   20: iconst_1
    //   21: anewarray java/lang/Object
    //   24: dup
    //   25: iconst_0
    //   26: aload_0
    //   27: aastore
    //   28: invokevirtual invoke : (Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   31: astore_0
    //   32: aload_0
    //   33: ifnull -> 44
    //   36: aload_0
    //   37: checkcast java/lang/String
    //   40: astore_0
    //   41: goto -> 46
    //   44: aconst_null
    //   45: astore_0
    //   46: aload_0
    //   47: ifnull -> 65
    //   50: aload_0
    //   51: ldc 'unknown'
    //   53: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   56: istore_1
    //   57: iload_1
    //   58: ifeq -> 65
    //   61: aconst_null
    //   62: areturn
    //   63: aload_0
    //   64: areturn
    //   65: aload_0
    //   66: areturn
    //   67: astore_0
    //   68: aconst_null
    //   69: areturn
    //   70: astore_2
    //   71: goto -> 63
    // Exception table:
    //   from	to	target	type
    //   0	32	67	java/lang/Exception
    //   0	32	67	finally
    //   36	41	67	java/lang/Exception
    //   36	41	67	finally
    //   50	57	67	java/lang/Exception
    //   50	57	70	finally
  }
  
  private static void a(List<EmulatorInfo.Filter> paramList, Object paramObject) {
    if (paramObject != null) {
      if (paramObject instanceof JSONObject) {
        d(paramList, (JSONObject)paramObject);
        return;
      } 
      if (paramObject instanceof org.json.JSONArray) {
        paramObject = paramObject;
        for (int i = 0; i < paramObject.length(); i++)
          d(paramList, paramObject.getJSONObject(i)); 
      } 
    } 
  }
  
  private static void a(List<EmulatorInfo.SureItem> paramList, JSONObject paramJSONObject) {
    EmulatorInfo.SureItem sureItem = new EmulatorInfo.SureItem();
    String str = paramJSONObject.optString("name", "");
    if (!TextUtils.isEmpty(str))
      sureItem.setName(str.trim()); 
    str = paramJSONObject.optString("contains", "");
    if (!TextUtils.isEmpty(str))
      sureItem.setContains(str.trim()); 
    if (!paramJSONObject.isNull("sure-filter")) {
      ArrayList<EmulatorInfo.Filter> arrayList = new ArrayList();
      a(arrayList, paramJSONObject.get("sure-filter"));
      sureItem.setFilters(arrayList);
    } 
    paramList.add(sureItem);
  }
  
  private static boolean a(EmulatorInfo paramEmulatorInfo) {
    return c(paramEmulatorInfo) ? true : b(paramEmulatorInfo);
  }
  
  private static String b(String paramString) {
    BufferedInputStream bufferedInputStream = null;
    try {
    
    } catch (Exception exception) {
    
    } finally {
      BufferedOutputStream bufferedOutputStream2 = null;
      BufferedOutputStream bufferedOutputStream1 = bufferedOutputStream2;
      if (bufferedOutputStream1 != null)
        try {
          bufferedOutputStream1.close();
        } catch (IOException null) {
          iOException.printStackTrace();
        }  
      if (bufferedInputStream != null)
        try {
          bufferedInputStream.close();
        } catch (IOException iOException) {
          iOException.printStackTrace();
        }  
      if (bufferedOutputStream2 != null)
        bufferedOutputStream2.destroy(); 
    } 
    BufferedOutputStream bufferedOutputStream = null;
    paramString = null;
    if (bufferedOutputStream != null)
      try {
        bufferedOutputStream.close();
      } catch (IOException iOException1) {
        iOException1.printStackTrace();
      }  
    if (paramString != null)
      try {
        paramString.close();
      } catch (IOException iOException1) {
        iOException1.printStackTrace();
      }  
    if (iOException != null)
      iOException.destroy(); 
    return null;
  }
  
  private static void b(List<EmulatorInfo.ExecItem> paramList, JSONObject paramJSONObject) {
    if (paramJSONObject == null)
      return; 
    EmulatorInfo.ExecItem execItem = new EmulatorInfo.ExecItem();
    execItem.setName(paramJSONObject.optString("name", "").trim());
    if (!paramJSONObject.isNull("exec-filter")) {
      ArrayList<EmulatorInfo.Filter> arrayList = new ArrayList();
      a(arrayList, paramJSONObject.get("exec-filter"));
      execItem.setFilters(arrayList);
    } 
    paramList.add(execItem);
  }
  
  private static boolean b(EmulatorInfo paramEmulatorInfo) {
    int j;
    List<EmulatorInfo.CheckItem> list1 = paramEmulatorInfo.getCheckItems();
    ArrayList<EmulatorInfo.CheckItem> arrayList1 = new ArrayList();
    ArrayList<EmulatorInfo.CheckItem> arrayList2 = new ArrayList();
    List<EmulatorInfo.ExecItem> list = paramEmulatorInfo.getExecItems();
    if (list1 != null && list1.size() > 0) {
      int n = 0;
      int m = 0;
      label111: while (true) {
        j = m;
        if (n < list1.size()) {
          EmulatorInfo.CheckItem checkItem = list1.get(n);
          String str = checkItem.getName();
          List<EmulatorInfo.Filter> list2 = checkItem.getFilters();
          if (list2 != null && list2.size() > 0) {
            String str1 = Build.MODEL;
            int i1 = Build.VERSION.SDK_INT;
            for (j = 0; j < list2.size(); j++) {
              EmulatorInfo.Filter filter = list2.get(j);
              if (str1.equals(filter.getModel()) && i1 == filter.getVersion()) {
                StringBuilder stringBuilder1 = new StringBuilder();
                stringBuilder1.append(str);
                stringBuilder1.append(" 项不检测  手机型号:");
                stringBuilder1.append(str1);
                stringBuilder1.append(" 版本号:");
                stringBuilder1.append(i1);
                ExLog.d(new String[] { stringBuilder1.toString() });
                j = 1;
                continue label111;
              } 
            } 
          } 
          j = 0;
          if (j != 0) {
            j = m;
          } else {
            arrayList1.add(checkItem);
            if (!TextUtils.isEmpty(checkItem.getEqualsItemName()))
              arrayList2.add(checkItem); 
            String str1 = a(str);
            StringBuilder stringBuilder1 = new StringBuilder();
            stringBuilder1.append(str);
            stringBuilder1.append(":");
            stringBuilder1.append(str1);
            ExLog.d(new String[] { stringBuilder1.toString() });
            if (TextUtils.isEmpty(str1)) {
              j = m + 1;
            } else {
              String str2 = checkItem.getContains();
              j = m;
              if (!TextUtils.isEmpty(str2)) {
                String[] arrayOfString = str2.split(",");
                j = 0;
                while (j < arrayOfString.length) {
                  int i1 = m;
                  if (str1.contains(arrayOfString[j]))
                    i1 = m + 1; 
                  j++;
                  m = i1;
                } 
                j = m;
              } 
            } 
          } 
          n++;
          m = j;
          continue;
        } 
        break;
      } 
    } else {
      j = 0;
    } 
    int i = j;
    if (arrayList2 != null) {
      i = j;
      if (arrayList2.size() > 0) {
        i = j;
        if (arrayList1 != null) {
          i = j;
          if (arrayList1.size() > 0) {
            int m = 0;
            while (true) {
              i = j;
              if (m < arrayList2.size()) {
                EmulatorInfo.CheckItem checkItem = arrayList2.get(m);
                int n = 0;
                while (true) {
                  i = j;
                  if (n < arrayList1.size()) {
                    EmulatorInfo.CheckItem checkItem1 = arrayList1.get(n);
                    if (checkItem.getName().equals(checkItem1.getName())) {
                      String str1 = a(checkItem.getName());
                      String str2 = a(checkItem1.getName());
                      StringBuilder stringBuilder1 = new StringBuilder();
                      stringBuilder1.append(checkItem.getName());
                      stringBuilder1.append("与");
                      stringBuilder1.append(checkItem1.getName());
                      stringBuilder1.append("equals result：");
                      stringBuilder1.append(str1);
                      stringBuilder1.append("/");
                      stringBuilder1.append(str2);
                      ExLog.d(new String[] { stringBuilder1.toString() });
                      i = j;
                      if (!TextUtils.isEmpty(str1)) {
                        i = j;
                        if (!TextUtils.isEmpty(str2)) {
                          i = j;
                          if (!str2.equals(str1))
                            i = j + 1; 
                        } 
                      } 
                      break;
                    } 
                    n++;
                    continue;
                  } 
                  break;
                } 
                m++;
                j = i;
                continue;
              } 
              break;
            } 
          } 
        } 
      } 
    } 
    int k = i;
    if (list != null) {
      k = i;
      if (list.size() > 0) {
        j = 0;
        label110: while (true) {
          k = i;
          if (j < list.size()) {
            EmulatorInfo.ExecItem execItem = list.get(j);
            String str = execItem.getName();
            list1 = (List)execItem.getFilters();
            if (list1 != null && list1.size() > 0) {
              String str1 = Build.MODEL;
              int m = Build.VERSION.SDK_INT;
              for (k = 0; k < list1.size(); k++) {
                EmulatorInfo.Filter filter = (EmulatorInfo.Filter)list1.get(k);
                if (str1.equals(filter.getModel()) && m == filter.getVersion()) {
                  StringBuilder stringBuilder1 = new StringBuilder();
                  stringBuilder1.append(str);
                  stringBuilder1.append(" 项不检测  手机型号:");
                  stringBuilder1.append(str1);
                  stringBuilder1.append(" 版本号:");
                  stringBuilder1.append(m);
                  ExLog.d(new String[] { stringBuilder1.toString() });
                  k = 1;
                  continue label110;
                } 
              } 
            } 
            k = 0;
            if (k != 0) {
              k = i;
            } else {
              String str1 = b(str);
              StringBuilder stringBuilder1 = new StringBuilder();
              stringBuilder1.append(str);
              stringBuilder1.append(":");
              stringBuilder1.append(str1);
              ExLog.d(new String[] { stringBuilder1.toString() });
              k = i;
              if (TextUtils.isEmpty(str1))
                k = i + 1; 
            } 
            j++;
            i = k;
            continue;
          } 
          break;
        } 
      } 
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("emulator result->");
    stringBuilder.append(k);
    stringBuilder.append("/");
    stringBuilder.append(paramEmulatorInfo.getCount());
    ExLog.i(new String[] { stringBuilder.toString() });
    return (k > paramEmulatorInfo.getCount());
  }
  
  private static void c(List<EmulatorInfo.CheckItem> paramList, JSONObject paramJSONObject) {
    if (paramJSONObject == null)
      return; 
    EmulatorInfo.CheckItem checkItem = new EmulatorInfo.CheckItem();
    String str = paramJSONObject.optString("name", "");
    if (!TextUtils.isEmpty(str))
      checkItem.setName(str.trim()); 
    str = paramJSONObject.optString("contains", "");
    if (!TextUtils.isEmpty(str))
      checkItem.setContains(str.trim()); 
    str = paramJSONObject.optString("equals-check-item", "");
    if (!TextUtils.isEmpty(str))
      checkItem.setEqualsItemName(str.trim()); 
    if (!paramJSONObject.isNull("check-filter")) {
      ArrayList<EmulatorInfo.Filter> arrayList = new ArrayList();
      a(arrayList, paramJSONObject.get("check-filter"));
      checkItem.setFilters(arrayList);
    } 
    paramList.add(checkItem);
  }
  
  private static boolean c(EmulatorInfo paramEmulatorInfo) {
    List<EmulatorInfo.SureItem> list = paramEmulatorInfo.getSureItems();
    if (list != null && list.size() > 0) {
      int i;
      label38: for (i = 0; i < list.size(); i++) {
        EmulatorInfo.SureItem sureItem = list.get(i);
        String str = sureItem.getName();
        List<EmulatorInfo.Filter> list1 = sureItem.getFilters();
        if (list1 != null && list1.size() > 0) {
          String str1 = Build.MODEL;
          int m = Build.VERSION.SDK_INT;
          for (int k = 0; k < list1.size(); k++) {
            EmulatorInfo.Filter filter = list1.get(k);
            if (str1.equals(filter.getModel()) && m == filter.getVersion()) {
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append(str);
              stringBuilder.append(" 项不检测  手机型号:");
              stringBuilder.append(str1);
              stringBuilder.append(" 版本号:");
              stringBuilder.append(m);
              ExLog.d(new String[] { stringBuilder.toString() });
              k = 1;
              continue label38;
            } 
          } 
        } 
        int j = 0;
        if (!j) {
          String str2 = a(str);
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append(str);
          stringBuilder.append(":");
          stringBuilder.append(str2);
          ExLog.d(new String[] { stringBuilder.toString() });
          if (TextUtils.isEmpty(str2))
            return true; 
          String str1 = sureItem.getContains();
          if (!TextUtils.isEmpty(str1)) {
            String[] arrayOfString = str1.split(",");
            for (j = 0; j < arrayOfString.length; j++) {
              if (str2.contains(arrayOfString[j]))
                return true; 
            } 
          } 
        } 
      } 
    } 
    return false;
  }
  
  private static void d(List<EmulatorInfo.Filter> paramList, JSONObject paramJSONObject) {
    if (paramJSONObject == null)
      return; 
    EmulatorInfo.Filter filter = new EmulatorInfo.Filter();
    filter.setModel(paramJSONObject.optString("model", "").trim());
    String str = paramJSONObject.optString("version", "").trim();
    if (!TextUtils.isEmpty(str) && TextUtils.isDigitsOnly(str)) {
      filter.setVersion(Integer.parseInt(str));
      paramList.add(filter);
    } 
  }
  
  public static boolean isEmulator(InnerState paramInnerState) {
    EmulatorInfo emulatorInfo = a(paramInnerState);
    return (emulatorInfo != null) ? a(emulatorInfo) : false;
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\emulator\EmulatorUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */