package com.cndatacom.cnportalclient.http;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Xml;
import com.cndatacom.cnportalclient.constant.UiErrCode;
import com.cndatacom.cnportalclient.model.ADInfo;
import com.cndatacom.cnportalclient.model.CodeCheckInfo;
import com.cndatacom.cnportalclient.model.CodeResultInfo;
import com.cndatacom.cnportalclient.model.CodeUploadArgsInfo;
import com.cndatacom.cnportalclient.model.CollectionFileInfo;
import com.cndatacom.cnportalclient.model.CollectionInfo;
import com.cndatacom.cnportalclient.model.InnerState;
import com.cndatacom.cnportalclient.model.NetInfo;
import com.cndatacom.cnportalclient.model.ReturnUIResult;
import com.cndatacom.cnportalclient.share.ShareAppInfo;
import com.cndatacom.cnportalclient.share.ShareUtils;
import com.cndatacom.cnportalclient.utils.AppInfoUtils;
import com.cndatacom.cnportalclient.utils.CdcUtils;
import com.cndatacom.cnportalclient.utils.DateUtils;
import com.cndatacom.cnportalclient.utils.DetectUtil;
import com.cndatacom.cnportalclient.utils.ExLog;
import com.cndatacom.cnportalclient.utils.FileUtils;
import com.cndatacom.cnportalclient.utils.NetUtils;
import com.cndatacom.cnportalclient.utils.PreferencesUtils;
import fr.arnaudguyon.xmltojsonlib.XmlToJson;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public final class CdcProtocol {
  public static final int DETECTED_CAMPUS = 1;
  
  public static final int DETECTED_OTHER_DIRECT = 2;
  
  public static final int DETECTED_OTHER_JUMP = 3;
  
  public static final int FIRST_KEEP_DELAY = 5000;
  
  public static final String TERM_REASON_EXCEPTION = "7";
  
  public static final String TERM_REASON_EXIT = "2";
  
  public static final String TERM_REASON_KEEP_FAILD = "3";
  
  public static final String TERM_REASON_KEEP_JUMP = "4";
  
  public static final String TERM_REASON_MANAGE = "8";
  
  public static final String TERM_REASON_MANUAL = "1";
  
  public static final String TERM_REASON_SHARE = "6";
  
  public static final String TERM_REASON_START = "5";
  
  public static final String TERM_REASON_UNKNOW = "0";
  
  private static final String[] a = new String[] { "http://www.189.cn", "http://www.sina.com.cn", "http://www.qq.com", "http://www.baidu.com", "" };
  
  private static int a(Context paramContext, InnerState paramInnerState, String paramString, boolean paramBoolean) {
    CdcHttpClient.Result result = (new CdcHttpClient(paramInnerState)).query(paramString, "gbk");
    if (result.isFailCode())
      return result.getHttpCode(); 
    String str = result.getBody();
    int i = str.indexOf("<!--//config.campus.js.chinatelecom.com");
    if (i < 0)
      return 3; 
    int j = "<!--//config.campus.js.chinatelecom.com".length();
    int k = str.lastIndexOf("//config.campus.js.chinatelecom.com-->");
    if (k < 0)
      return 3; 
    str = str.substring(i + j, k).trim();
    if (TextUtils.isEmpty(str))
      return 1; 
    str = str.replaceAll("&amp;", "&").replaceAll("&", "&amp;").trim();
    ExLog.d(new String[] { "detectConfig body: ", str });
    if (paramBoolean) {
      try {
        JSONObject jSONObject = new JSONObject((new XmlToJson.Builder(str)).build().toJson().toString().replaceAll("&amp;", "&").trim());
        paramInnerState.getConfig().remove("config");
        if (jSONObject.has("config"))
          paramInnerState.getConfig().put("config", jSONObject.get("config")); 
        DetectUtil.saveDetectUrlData(paramInnerState);
        String str1 = getOneLevelConfig(paramInnerState, "detect-url", "");
        if (!TextUtils.isEmpty(str1)) {
          PreferencesUtils.putString(paramContext, "detect-url", str1);
        } else {
          str1 = paramInnerState.getFuncfgUrl("DetectUrl", "");
          if (!TextUtils.isEmpty(str1))
            PreferencesUtils.putString(paramContext, "detect-url", str1); 
        } 
      } catch (Exception exception) {
        ExLog.w(new String[] { "detectConfig exception: ", exception.getMessage() });
      } 
      ExLog.d(new String[] { paramInnerState.getConfig().toString() });
    } 
    return 1;
  }
  
  private static ReturnUIResult a(Context paramContext, InnerState paramInnerState, byte[] paramArrayOfbyte) {
    ReturnUIResult returnUIResult = new ReturnUIResult();
    XmlPullParser xmlPullParser = a(paramArrayOfbyte);
    if (xmlPullParser != null)
      try {
        Object object1;
        int i = xmlPullParser.getEventType();
        byte[] arrayOfByte = null;
        paramArrayOfbyte = arrayOfByte;
        Object object3 = paramArrayOfbyte;
        Object object2 = paramArrayOfbyte;
        paramArrayOfbyte = arrayOfByte;
        while (true) {
          String str;
          if (object1 != true) {
            Object object4 = object1;
            arrayOfByte = paramArrayOfbyte;
            Object object5 = object2;
            Object object6 = object3;
            if (object1 == 2) {
              int j;
              byte b;
              Object object;
              ArrayList<Object> arrayList1;
              String str1;
              String str2;
              ArrayList<Object> arrayList2;
              String str3 = xmlPullParser.getName();
              switch (str3.hashCode()) {
                case 638260096:
                  if (str3.equals("logout-delay")) {
                    byte b1 = 6;
                    break;
                  } 
                case 481552209:
                  if (str3.equals("against-interval")) {
                    byte b1 = 5;
                    break;
                  } 
                case 395941468:
                  if (str3.equals("app-check")) {
                    byte b1 = 12;
                    break;
                  } 
                case 226683488:
                  if (str3.equals("keep-retry")) {
                    boolean bool = true;
                    break;
                  } 
                case 96801:
                  if (str3.equals("app")) {
                    byte b1 = 11;
                    break;
                  } 
                case -57861337:
                  if (str3.equals("keep-url")) {
                    byte b1 = 2;
                    break;
                  } 
                case -309518737:
                  if (str3.equals("process")) {
                    byte b1 = 8;
                    break;
                  } 
                case -453774229:
                  if (str3.equals("domain-config")) {
                    byte b1 = 10;
                    break;
                  } 
                case -836029914:
                  if (str3.equals("userid")) {
                    boolean bool = false;
                    break;
                  } 
                case -1064834623:
                  if (str3.equals("against")) {
                    byte b1 = 7;
                    break;
                  } 
                case -1183029106:
                  if (str3.equals("term-url")) {
                    byte b1 = 3;
                    break;
                  } 
                case -1323526104:
                  if (str3.equals("driver")) {
                    byte b1 = 9;
                    break;
                  } 
                case -2038351996:
                  if (str3.equals("user-config")) {
                    byte b1 = 4;
                    break;
                  } 
                default:
                  j = -1;
                  break;
              } 
              object4 = object1;
              arrayOfByte = paramArrayOfbyte;
              object5 = object2;
              object6 = object3;
              switch (j) {
                case 12:
                  arrayOfByte = paramArrayOfbyte;
                  if (paramArrayOfbyte == null)
                    arrayList1 = new ArrayList(); 
                  str = xmlPullParser.getAttributeValue(null, "name-contains");
                  str2 = xmlPullParser.getAttributeValue(null, "packagename-contains");
                  object5 = xmlPullParser.getAttributeValue(null, "wifiname-contains");
                  object6 = new ShareAppInfo();
                  object6.setNameContains(str);
                  object6.setPackagenameContains(str2);
                  object6.setWifinameContains((String)object5);
                  arrayList1.add(object6);
                  object4 = object1;
                  object5 = object2;
                  object6 = object3;
                case 11:
                  str2 = str;
                  if (str == null)
                    arrayList2 = new ArrayList(); 
                  str = xmlPullParser.getAttributeValue(null, "appname");
                  str3 = xmlPullParser.nextText();
                  object4 = object1;
                  arrayList1 = arrayList2;
                  object5 = object2;
                  object6 = object3;
                  if (!TextUtils.isEmpty(str3)) {
                    ShareAppInfo shareAppInfo = new ShareAppInfo();
                    shareAppInfo.setAppName(str);
                    shareAppInfo.setPackagename(str3);
                    arrayList2.add(shareAppInfo);
                    object4 = object1;
                    ArrayList<Object> arrayList = arrayList2;
                    object5 = object2;
                    object6 = object3;
                  } 
                case 8:
                case 9:
                  object4 = object1;
                  str1 = str;
                  object5 = object2;
                  object6 = object3;
                case 7:
                  object6 = new JSONObject();
                  paramInnerState.putConfig(new String[] { "user-config" }).put(str3, object6);
                  object4 = object1;
                  str1 = str;
                  object5 = object2;
                case 5:
                case 6:
                  object4 = object1;
                  str1 = str;
                  object5 = object2;
                  object6 = object3;
                case 4:
                  object5 = paramInnerState.putConfig(new String[] { str3 });
                  object4 = object1;
                  str1 = str;
                  object6 = object3;
                case 3:
                  paramInnerState.putConfig(new String[] { "term" }).put("url", xmlPullParser.nextText());
                  b = -1;
                  str1 = str;
                  object5 = object2;
                  object6 = object3;
                case 2:
                  paramInnerState.putConfig(new String[] { "keep" }).put("url", xmlPullParser.nextText());
                  b = -1;
                  str1 = str;
                  object5 = object2;
                  object6 = object3;
                case 1:
                  paramInnerState.putConfig(new String[] { "keep" }).put("retry", CdcUtils.toInt(xmlPullParser.nextText(), 3));
                  b = -1;
                  str1 = str;
                  object5 = object2;
                  object6 = object3;
                case 0:
                  paramInnerState.setUserId(xmlPullParser.nextText());
                  b = -1;
                  str1 = str;
                  object5 = object2;
                  object6 = object3;
                case 10:
                  if (b == -1) {
                    j = xmlPullParser.getEventType();
                    break;
                  } 
                  j = xmlPullParser.next();
                  break;
                default:
                  object = object1;
                  str1 = str;
                  object5 = object2;
                  object6 = object3;
              } 
              continue;
            } 
          } 
          a(paramInnerState, 65000L);
          ShareUtils.saveShareAppInfo(paramContext, (List)str);
          returnUIResult.setErrorCode("0");
          paramInnerState.save();
          return returnUIResult;
          object1 = SYNTHETIC_LOCAL_VARIABLE_3;
          paramArrayOfbyte = arrayOfByte;
          object2 = SYNTHETIC_LOCAL_VARIABLE_10;
          object3 = SYNTHETIC_LOCAL_VARIABLE_11;
        } 
      } catch (Exception exception) {
        ExLog.e(new String[] { "parseAuthXml exception: ", exception.getMessage() });
      }  
    returnUIResult.setErrorCode("200115");
    return returnUIResult;
  }
  
  private static String a(String paramString, InnerState paramInnerState) {
    String str1;
    String str2 = paramString;
    try {
      Object object = paramInnerState.getConfig(new String[] { "config", "account" }).get("modify");
      str2 = paramString;
      if (object instanceof JSONObject) {
        str2 = paramString;
        return a(paramString, (JSONObject)object);
      } 
      str2 = paramString;
      str1 = paramString;
      if (object instanceof JSONArray) {
        str2 = paramString;
        object = object;
        str1 = paramString;
        if (object != null) {
          int i = 0;
          while (true) {
            str2 = paramString;
            str1 = paramString;
            if (i < object.length()) {
              str2 = paramString;
              JSONObject jSONObject = object.optJSONObject(i);
              if (jSONObject != null) {
                str2 = paramString;
                paramString = a(paramString, jSONObject);
              } 
              i++;
              continue;
            } 
            break;
          } 
        } 
      } 
    } catch (Exception exception) {
      exception.printStackTrace();
      str1 = str2;
    } 
    return str1;
  }
  
  private static String a(String paramString, JSONObject paramJSONObject) {
    String str1;
    if (paramJSONObject == null)
      return paramString; 
    String str3 = paramJSONObject.optString("type");
    String str2 = paramJSONObject.optString("value");
    if (TextUtils.isEmpty(str2)) {
      paramJSONObject = null;
    } else {
      str1 = str2;
      if (!str2.startsWith("@")) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("@");
        stringBuilder.append(str2);
        str1 = stringBuilder.toString();
      } 
    } 
    if ("del-domain".equals(str3)) {
      if (str1 != null) {
        str2 = paramString;
        if (paramString.endsWith(str1))
          return TextUtils.split(paramString, "@")[0]; 
      } else {
        return TextUtils.split(paramString, "@")[0];
      } 
    } else {
      str2 = paramString;
      if (str1 != null) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(TextUtils.split(paramString, "@")[0]);
        stringBuilder.append(str1);
        str2 = stringBuilder.toString();
      } 
    } 
    return str2;
  }
  
  private static XmlPullParser a() {
    XmlPullParser xmlPullParser = Xml.newPullParser();
    try {
      xmlPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", false);
      xmlPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-docdecl", false);
      xmlPullParser.setFeature(Xml.FEATURE_RELAXED, true);
      return xmlPullParser;
    } catch (XmlPullParserException xmlPullParserException) {
      return xmlPullParser;
    } 
  }
  
  private static XmlPullParser a(String paramString) {
    XmlPullParser xmlPullParser = Xml.newPullParser();
    try {
      xmlPullParser.setInput(new StringReader(paramString));
      return xmlPullParser;
    } catch (XmlPullParserException xmlPullParserException) {
      return xmlPullParser;
    } 
  }
  
  private static XmlPullParser a(byte[] paramArrayOfbyte) {
    XmlPullParser xmlPullParser = a();
    try {
      xmlPullParser.setInput(new ByteArrayInputStream(paramArrayOfbyte), "UTF-8");
      return xmlPullParser;
    } catch (XmlPullParserException xmlPullParserException) {
      return xmlPullParser;
    } 
  }
  
  private static void a(InnerState paramInnerState, long paramLong) {
    if (paramLong > 0L)
      paramInnerState.setExpire(new Date((new Date()).getTime() + paramLong)); 
  }
  
  private static void a(InnerState paramInnerState, String paramString) {
    a(paramInnerState, (CdcUtils.toInt(paramString, 0) * 1000));
  }
  
  private static void a(String paramString1, JSONObject paramJSONObject, String paramString2) {
    try {
      Object object;
      XmlPullParser xmlPullParser = XmlPullParserFactory.newInstance().newPullParser();
      xmlPullParser.setInput(new ByteArrayInputStream(paramString1.getBytes()), "UTF-8");
      int i = xmlPullParser.getEventType();
      JSONArray jSONArray = new JSONArray();
      paramString1 = null;
      while (true) {
        if (i != 1) {
          String str = xmlPullParser.getName();
          Object object1 = object;
          if (i != 0) {
            switch (i) {
              case 3:
                object1 = object;
                if ("client".equals(str)) {
                  object1 = object;
                  if (object != null) {
                    jSONArray.put(object);
                    object1 = object;
                  } 
                } 
                continue;
              case 2:
                if ("client".equals(str)) {
                  object1 = new JSONObject();
                } else if ("ticket".equals(str)) {
                  object1 = xmlPullParser.nextText();
                  object.put(str, object1);
                  if (paramString2.equals(object1)) {
                    object.put("isSelf", "1");
                    object1 = object;
                  } else {
                    object.put("isSelf", "0");
                    object1 = object;
                  } 
                } else if ("ostag".equals(str)) {
                  object.put(str, xmlPullParser.nextText());
                  object1 = object;
                } else if ("hostname".equals(str)) {
                  object.put(str, xmlPullParser.nextText());
                  object1 = object;
                } else if ("login-time".equals(str)) {
                  object.put(str, xmlPullParser.nextText());
                  object1 = object;
                } else if ("ostype".equals(str)) {
                  object.put(str, xmlPullParser.nextText());
                  object1 = object;
                } else {
                  object1 = object;
                  if ("mac".equals(str)) {
                    object.put(str, xmlPullParser.nextText());
                    object1 = object;
                  } 
                } 
                continue;
            } 
            object1 = object;
          } 
          continue;
        } 
        paramJSONObject.put("client", jSONArray);
        return;
        i = xmlPullParser.next();
        object = SYNTHETIC_LOCAL_VARIABLE_4;
      } 
    } catch (Exception exception) {
      exception.printStackTrace();
      return;
    } 
  }
  
  private static boolean a(CdcHttpClient.Result paramResult) {
    return (paramResult.isOk() && "0".equals(paramResult.getCdcCode()));
  }
  
  public static ReturnUIResult authOtherClient(Context paramContext, InnerState paramInnerState, String paramString1, String paramString2, String paramString3, String paramString4) {
    ReturnUIResult returnUIResult = new ReturnUIResult();
    if (TextUtils.isEmpty(paramString1)) {
      returnUIResult.setErrorCode("300309");
      return returnUIResult;
    } 
    if (TextUtils.isEmpty(paramString2) || TextUtils.isEmpty(paramString3)) {
      returnUIResult.setErrorCode(UiErrCode.makePortalErrorCode("100"));
      return returnUIResult;
    } 
    String str = a(paramString2, paramInnerState);
    CdcRequest cdcRequest = new CdcRequest(paramInnerState);
    cdcRequest.addItem("userid", str).addItem("passwd", paramString3).addItem("ticket", paramString4);
    return UiErrCode.makeUiErrorCode((new CdcHttpClient(paramInnerState)).post(paramString1, cdcRequest.toBytes(), false, false));
  }
  
  public static int detectConfig(Context paramContext, InnerState paramInnerState) {
    int k;
    String str = PreferencesUtils.getString(paramContext, "detect-url", "");
    if (!TextUtils.isEmpty(str))
      a[a.length - 1] = str; 
    int j = 0;
    int i = -1;
    while (true) {
      k = i;
      if (j < a.length) {
        str = a[j];
        if (TextUtils.isEmpty(str)) {
          k = i;
        } else {
          i = a(paramContext, paramInnerState, str, true);
          k = i;
          if (i != 1) {
            k = i;
            if (i != 2) {
              k = i;
              if (i == 3)
                return i; 
            } else {
              break;
            } 
          } else {
            break;
          } 
        } 
        j++;
        i = k;
        continue;
      } 
      break;
    } 
    return k;
  }
  
  public static ADInfo getAdvData(Context paramContext, InnerState paramInnerState) {
    String str2 = paramInnerState.getFuncfgUrl("SplashAndDialogAD", null);
    if (TextUtils.isEmpty(str2)) {
      ExLog.i(new String[] { "SplashAndDialogAD url is null" });
      return null;
    } 
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    hashMap.put("ostype", "4");
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(AppInfoUtils.getVersionCode(paramContext));
    stringBuilder.append("");
    hashMap.put("version", stringBuilder.toString());
    hashMap.put("dialupaccount", PreferencesUtils.getString(paramContext, "account", ""));
    hashMap.put("cid", paramInnerState.getSchoolId(""));
    hashMap.put("clientid", paramInnerState.getClientId());
    hashMap.put("adtype", "1");
    HttpClient.Result result = (new HttpClient()).get(str2, (Map<String, String>)null, (Map)hashMap);
    String str1 = result.getDataString();
    if (result.isSuccessful()) {
      try {
        JSONObject jSONObject = new JSONObject(str1);
        if (jSONObject.optInt("status", -1) == 0 && jSONObject.has("data")) {
          JSONArray jSONArray = jSONObject.getJSONArray("data");
          if (jSONArray != null && jSONArray.length() > 0) {
            JSONObject jSONObject1 = jSONArray.getJSONObject(0);
            ADInfo aDInfo = new ADInfo();
            aDInfo.setHref(jSONObject1.optString("href", ""));
            aDInfo.setUrl(jSONObject1.optString("src", ""));
            aDInfo.setType(jSONObject.optInt("type", 0));
            aDInfo.setAdid(jSONObject.optString("adid", ""));
            return aDInfo;
          } 
        } 
      } catch (Exception exception) {
        exception.printStackTrace();
        return null;
      } 
    } else {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("getAdvUrl result ->");
      stringBuilder1.append((String)exception);
      ExLog.i(new String[] { stringBuilder1.toString() });
    } 
    return null;
  }
  
  public static ReturnUIResult getDaMod(InnerState paramInnerState, String paramString) {
    ReturnUIResult returnUIResult = new ReturnUIResult();
    CdcHttpClient.Result result = (new CdcHttpClient(paramInnerState)).post(paramString, (new CdcRequest(paramInnerState)).addItem("host-name", Build.HOST).addNetInfo().addItem("ostag", Build.MODEL).toBytes(), true);
    if ("200".equals(result.getCdcCode())) {
      returnUIResult.setErrorCode("0");
      return returnUIResult;
    } 
    return UiErrCode.makeUiErrorCode(result);
  }
  
  public static String getOneLevelConfig(InnerState paramInnerState, String paramString1, String paramString2) {
    return paramInnerState.getConfig(new String[] { "config" }).optString(paramString1, paramString2);
  }
  
  public static void getSchoolInfo(Context paramContext, String paramString, InnerState paramInnerState) {
    String str1 = PreferencesUtils.getString(CdcUtils.getContext(), "schoolID", "");
    String str2 = DateUtils.parse2YYYYMMDD(new Date());
    String str3 = PreferencesUtils.getString(paramContext, "EXPIRED_KEY", "");
    if (TextUtils.isEmpty(str1) || (!TextUtils.isEmpty(str3) && str2.compareTo(str3) > 0)) {
      str1 = paramInnerState.getFuncfgUrl("SchoolInfoURL", null);
      if (TextUtils.isEmpty(str1)) {
        ExLog.i(new String[] { "getSchoolInfo url is null" });
        return;
      } 
      try {
        String[] arrayOfString = CdcUtils.getNetworkInfo();
        HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
        hashMap.put("ostype", "4");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(AppInfoUtils.getVersionCode(paramContext));
        stringBuilder.append("");
        hashMap.put("version", stringBuilder.toString());
        hashMap.put("account", paramString);
        hashMap.put("ticket", paramInnerState.getTicket());
        hashMap.put("gateway", paramInnerState.getGateway());
        hashMap.put("ipv4", arrayOfString[0]);
        hashMap.put("ipv6", arrayOfString[1]);
        HttpClient.Result result = (new HttpClient()).get(str1, (Map<String, String>)null, (Map)hashMap);
        if (result.isSuccessful()) {
          JSONObject jSONObject = new JSONObject(result.getDataString());
          if (jSONObject.optInt("code", -1) == 0) {
            String str4 = jSONObject.optString("schoolid", "");
            str1 = jSONObject.optString("domain", "");
            String str5 = jSONObject.optString("area", "");
            PreferencesUtils.putString(paramContext, "schoolID", str4);
            PreferencesUtils.putString(paramContext, "area", str1);
            PreferencesUtils.putString(paramContext, "domain", str5);
            PreferencesUtils.putString(paramContext, "EXPIRED_KEY", jSONObject.optString("expired", ""));
            return;
          } 
        } 
      } catch (Exception exception) {
        exception.printStackTrace();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("getSchoolInfo error ->");
        stringBuilder.append(exception.getMessage());
        ExLog.e(new String[] { stringBuilder.toString() });
      } 
    } 
  }
  
  public static ReturnUIResult getState(Context paramContext, InnerState paramInnerState) {
    ReturnUIResult returnUIResult = new ReturnUIResult();
    String str = getOneLevelConfig(paramInnerState, "state-url", null);
    if (TextUtils.isEmpty(str)) {
      returnUIResult.setErrorCode("300309");
      return returnUIResult;
    } 
    CdcHttpClient.Result result = (new CdcHttpClient(paramInnerState)).post(str, (new CdcRequest(paramInnerState)).addTicket().toBytes());
    if (a(result))
      return a(paramContext, paramInnerState, result.getData()); 
    if ("14".equals(result.getCdcCode()) || "300".equals(result.getCdcCode()) || "301".equals(result.getCdcCode())) {
      returnUIResult.setErrorCode(result.getCdcCode());
      return returnUIResult;
    } 
    return UiErrCode.makeUiErrorCode(result);
  }
  
  public static int getStateInterval(InnerState paramInnerState) {
    int j = CdcUtils.toInt(getOneLevelConfig(paramInnerState, "state-interval", null), 5);
    int i = j;
    if (j <= 0)
      i = 5; 
    return i * 1000;
  }
  
  public static ReturnUIResult getTicket(Context paramContext, InnerState paramInnerState) {
    // Byte code:
    //   0: new com/cndatacom/cnportalclient/model/ReturnUIResult
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore #7
    //   9: aconst_null
    //   10: astore_3
    //   11: aload_1
    //   12: ldc_w 'ticket-url'
    //   15: aconst_null
    //   16: invokestatic getOneLevelConfig : (Lcom/cndatacom/cnportalclient/model/InnerState;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   19: astore #4
    //   21: aload #4
    //   23: invokestatic isEmpty : (Ljava/lang/CharSequence;)Z
    //   26: ifeq -> 40
    //   29: aload #7
    //   31: ldc_w '300309'
    //   34: invokevirtual setErrorCode : (Ljava/lang/String;)V
    //   37: aload #7
    //   39: areturn
    //   40: aload_1
    //   41: invokevirtual getNetInfo : ()Lcom/cndatacom/cnportalclient/model/NetInfo;
    //   44: astore #5
    //   46: aload #5
    //   48: invokevirtual getIpv4 : ()Ljava/lang/String;
    //   51: invokestatic isEmpty : (Ljava/lang/CharSequence;)Z
    //   54: ifeq -> 79
    //   57: aload #5
    //   59: invokevirtual getMac : ()Ljava/lang/String;
    //   62: invokestatic isEmpty : (Ljava/lang/CharSequence;)Z
    //   65: ifeq -> 79
    //   68: aload #7
    //   70: ldc_w '200112'
    //   73: invokevirtual setErrorCode : (Ljava/lang/String;)V
    //   76: aload #7
    //   78: areturn
    //   79: new com/cndatacom/cnportalclient/http/CdcHttpClient
    //   82: dup
    //   83: aload_1
    //   84: invokespecial <init> : (Lcom/cndatacom/cnportalclient/model/InnerState;)V
    //   87: aload #4
    //   89: new com/cndatacom/cnportalclient/http/CdcProtocol$CdcRequest
    //   92: dup
    //   93: aload_1
    //   94: invokespecial <init> : (Lcom/cndatacom/cnportalclient/model/InnerState;)V
    //   97: ldc_w 'host-name'
    //   100: getstatic android/os/Build.MODEL : Ljava/lang/String;
    //   103: invokevirtual addItem : (Ljava/lang/String;Ljava/lang/String;)Lcom/cndatacom/cnportalclient/http/CdcProtocol$CdcRequest;
    //   106: invokevirtual addNetInfo : ()Lcom/cndatacom/cnportalclient/http/CdcProtocol$CdcRequest;
    //   109: ldc_w 'ostag'
    //   112: getstatic android/os/Build.MODEL : Ljava/lang/String;
    //   115: invokevirtual addItem : (Ljava/lang/String;Ljava/lang/String;)Lcom/cndatacom/cnportalclient/http/CdcProtocol$CdcRequest;
    //   118: ldc_w 'gwip'
    //   121: aload_1
    //   122: invokevirtual getGateway : ()Ljava/lang/String;
    //   125: invokevirtual addItem : (Ljava/lang/String;Ljava/lang/String;)Lcom/cndatacom/cnportalclient/http/CdcProtocol$CdcRequest;
    //   128: invokevirtual toBytes : ()[B
    //   131: invokevirtual post : (Ljava/lang/String;[B)Lcom/cndatacom/cnportalclient/http/CdcHttpClient$Result;
    //   134: astore #4
    //   136: aload #4
    //   138: invokestatic a : (Lcom/cndatacom/cnportalclient/http/CdcHttpClient$Result;)Z
    //   141: ifeq -> 412
    //   144: aload #4
    //   146: invokevirtual getData : ()[B
    //   149: invokestatic a : ([B)Lorg/xmlpull/v1/XmlPullParser;
    //   152: astore #6
    //   154: aload #6
    //   156: ifnull -> 388
    //   159: aconst_null
    //   160: astore #4
    //   162: aload #4
    //   164: astore #5
    //   166: aload #6
    //   168: invokeinterface getEventType : ()I
    //   173: istore_2
    //   174: aload_3
    //   175: astore #4
    //   177: iload_2
    //   178: iconst_1
    //   179: if_icmpeq -> 321
    //   182: iload_2
    //   183: iconst_2
    //   184: if_icmpne -> 280
    //   187: ldc_w 'ticket'
    //   190: aload #6
    //   192: invokeinterface getName : ()Ljava/lang/String;
    //   197: invokevirtual equals : (Ljava/lang/Object;)Z
    //   200: ifeq -> 252
    //   203: aload #6
    //   205: invokeinterface nextText : ()Ljava/lang/String;
    //   210: astore #4
    //   212: iconst_2
    //   213: anewarray java/lang/String
    //   216: dup
    //   217: iconst_0
    //   218: ldc_w 'getTicket => '
    //   221: aastore
    //   222: dup
    //   223: iconst_1
    //   224: aload #4
    //   226: aastore
    //   227: invokestatic i : ([Ljava/lang/String;)V
    //   230: aload #4
    //   232: astore_3
    //   233: aload #5
    //   235: astore #4
    //   237: goto -> 162
    //   240: astore #6
    //   242: aload #4
    //   244: astore_3
    //   245: aload #6
    //   247: astore #4
    //   249: goto -> 297
    //   252: ldc_w 'expire'
    //   255: aload #6
    //   257: invokeinterface getName : ()Ljava/lang/String;
    //   262: invokevirtual equals : (Ljava/lang/Object;)Z
    //   265: ifeq -> 280
    //   268: aload #6
    //   270: invokeinterface nextText : ()Ljava/lang/String;
    //   275: astore #4
    //   277: goto -> 162
    //   280: aload #6
    //   282: invokeinterface next : ()I
    //   287: pop
    //   288: aload #5
    //   290: astore #4
    //   292: goto -> 162
    //   295: astore #4
    //   297: iconst_2
    //   298: anewarray java/lang/String
    //   301: dup
    //   302: iconst_0
    //   303: ldc_w 'getTicket exception: '
    //   306: aastore
    //   307: dup
    //   308: iconst_1
    //   309: aload #4
    //   311: invokevirtual getMessage : ()Ljava/lang/String;
    //   314: aastore
    //   315: invokestatic w : ([Ljava/lang/String;)V
    //   318: aload_3
    //   319: astore #4
    //   321: aload #4
    //   323: invokestatic isEmpty : (Ljava/lang/CharSequence;)Z
    //   326: ifne -> 375
    //   329: aload #5
    //   331: invokestatic isEmpty : (Ljava/lang/CharSequence;)Z
    //   334: ifne -> 375
    //   337: aload_1
    //   338: aload #4
    //   340: invokevirtual setTicket : (Ljava/lang/String;)Lcom/cndatacom/cnportalclient/model/InnerState;
    //   343: pop
    //   344: aload_1
    //   345: aload #5
    //   347: invokestatic a : (Lcom/cndatacom/cnportalclient/model/InnerState;Ljava/lang/String;)V
    //   350: aload_1
    //   351: invokevirtual save : ()Lcom/cndatacom/cnportalclient/model/InnerState;
    //   354: pop
    //   355: aload_0
    //   356: ldc_w 'ticket'
    //   359: aload #4
    //   361: invokestatic putString : (Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Z
    //   364: pop
    //   365: aload #7
    //   367: ldc '0'
    //   369: invokevirtual setErrorCode : (Ljava/lang/String;)V
    //   372: aload #7
    //   374: areturn
    //   375: iconst_1
    //   376: anewarray java/lang/String
    //   379: dup
    //   380: iconst_0
    //   381: ldc_w 'ticket data parser error'
    //   384: aastore
    //   385: invokestatic e : ([Ljava/lang/String;)V
    //   388: iconst_1
    //   389: anewarray java/lang/String
    //   392: dup
    //   393: iconst_0
    //   394: ldc_w 'ticket respone result error'
    //   397: aastore
    //   398: invokestatic e : ([Ljava/lang/String;)V
    //   401: aload #7
    //   403: ldc_w '200115'
    //   406: invokevirtual setErrorCode : (Ljava/lang/String;)V
    //   409: aload #7
    //   411: areturn
    //   412: aload #4
    //   414: invokestatic makeUiErrorCode : (Lcom/cndatacom/cnportalclient/http/CdcHttpClient$Result;)Lcom/cndatacom/cnportalclient/model/ReturnUIResult;
    //   417: areturn
    // Exception table:
    //   from	to	target	type
    //   166	174	295	java/lang/Exception
    //   187	212	295	java/lang/Exception
    //   212	230	240	java/lang/Exception
    //   252	277	295	java/lang/Exception
    //   280	288	295	java/lang/Exception
  }
  
  public static String getVerifyCode(InnerState paramInnerState, String paramString1, String paramString2) {
    String str1 = paramInnerState.getFuncfgUrl("QueryAuthCode", null);
    if (TextUtils.isEmpty(str1))
      return null; 
    long l = System.currentTimeMillis();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramString2);
    stringBuilder.append(l);
    stringBuilder.append("Eshore!@#");
    String str2 = CdcUtils.byteArrayToHex(CdcUtils.md5(stringBuilder.toString().getBytes())).toUpperCase(Locale.US);
    JSONObject jSONObject = new JSONObject();
    try {
      StringBuilder stringBuilder3 = new StringBuilder();
      stringBuilder3.append("");
      stringBuilder3.append(paramString2);
      jSONObject.put("schoolid", stringBuilder3.toString());
      StringBuilder stringBuilder2 = new StringBuilder();
      stringBuilder2.append("");
      stringBuilder2.append(paramString1);
      jSONObject.put("username", stringBuilder2.toString());
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("");
      stringBuilder1.append(l);
      jSONObject.put("timestamp", stringBuilder1.toString());
      stringBuilder1 = new StringBuilder();
      stringBuilder1.append("");
      stringBuilder1.append(str2);
      jSONObject.put("authenticator", stringBuilder1.toString());
    } catch (JSONException jSONException) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("getVerifyCode params exception : ");
      stringBuilder1.append(jSONException.getMessage());
      ExLog.w(new String[] { stringBuilder1.toString() });
    } 
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    hashMap.put("Content-Type", "application/json");
    HttpClient.Result result = (new HttpClient()).post(str1, (Map)hashMap, jSONObject.toString().getBytes());
    if (result.isSuccessful()) {
      String str = result.getDataString();
      try {
        JSONObject jSONObject1 = new JSONObject(str);
        if (jSONObject1 != null && jSONObject1.has("rescode"))
          return jSONObject1.optString("rescode", ""); 
        ExLog.w(new String[] { "getVerifyCode xml parser exception" });
        return null;
      } catch (JSONException jSONException) {
        ExLog.w(new String[] { "getVerifyCode exception: ", jSONException.getMessage() });
      } 
    } 
    return null;
  }
  
  public static boolean isUsefulNet(Context paramContext, InnerState paramInnerState) {
    if (!NetUtils.isNetworkAvailable(paramContext))
      return false; 
    String str = PreferencesUtils.getString(paramContext, "detect-url", "");
    if (!TextUtils.isEmpty(str))
      a[a.length - 1] = str; 
    for (int i = 0; i < a.length; i++) {
      str = a[i];
      if (!TextUtils.isEmpty(str)) {
        int j = a(paramContext, paramInnerState, str, false);
        if (j != 2) {
          if (j == 3)
            return true; 
        } else {
          return true;
        } 
      } 
    } 
    return false;
  }
  
  public static JSONObject onCheckUpdate(Context paramContext, InnerState paramInnerState) {
    JSONObject jSONObject2 = new JSONObject();
    String str = paramInnerState.getFuncfgUrl("UpdateCheck", "");
    if (TextUtils.isEmpty(str))
      return jSONObject2; 
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    hashMap.put("areaCode", "zs");
    hashMap.put("osType", "androidphone");
    hashMap.put("dialType", "3");
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(AppInfoUtils.getVersionCode(paramContext));
    stringBuilder.append("");
    hashMap.put("verno", stringBuilder.toString());
    hashMap.put("CID", paramInnerState.getSchoolId(""));
    hashMap.put("account", PreferencesUtils.getString(paramContext, "account", ""));
    HttpClient.Result result = (new HttpClient()).get(str, (Map<String, String>)null, (Map)hashMap);
    JSONObject jSONObject1 = jSONObject2;
    if (result.isSuccessful())
      jSONObject1 = (new XmlToJson.Builder(result.getDataString())).build().toJson(); 
    return jSONObject1;
  }
  
  public static String onCheckVerifyCode(InnerState paramInnerState, String paramString1, String paramString2) {
    String str1 = paramInnerState.getFuncfgUrl("QueryVerificateCodeStatus", null);
    if (TextUtils.isEmpty(str1))
      return null; 
    long l = System.currentTimeMillis();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramString2);
    stringBuilder.append(l);
    stringBuilder.append("Eshore!@#");
    String str2 = CdcUtils.byteArrayToHex(CdcUtils.md5(stringBuilder.toString().getBytes())).toUpperCase(Locale.US);
    JSONObject jSONObject = new JSONObject();
    try {
      StringBuilder stringBuilder3 = new StringBuilder();
      stringBuilder3.append("");
      stringBuilder3.append(paramString2);
      jSONObject.put("schoolid", stringBuilder3.toString());
      StringBuilder stringBuilder2 = new StringBuilder();
      stringBuilder2.append("");
      stringBuilder2.append(paramString1);
      jSONObject.put("username", stringBuilder2.toString());
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("");
      stringBuilder1.append(l);
      jSONObject.put("timestamp", stringBuilder1.toString());
      stringBuilder1 = new StringBuilder();
      stringBuilder1.append("");
      stringBuilder1.append(str2);
      jSONObject.put("authenticator", stringBuilder1.toString());
    } catch (JSONException jSONException) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("onCheckVerifyCode params exception : ");
      stringBuilder1.append(jSONException.getMessage());
      ExLog.w(new String[] { stringBuilder1.toString() });
    } 
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    hashMap.put("Content-Type", "application/json");
    HttpClient.Result result = (new HttpClient()).post(str1, (Map)hashMap, jSONObject.toString().getBytes());
    if (result.isSuccessful()) {
      String str = result.getDataString();
      try {
        JSONObject jSONObject1 = new JSONObject(str);
        if (jSONObject1 != null && jSONObject1.has("rescode"))
          return jSONObject1.optString("rescode", ""); 
        ExLog.w(new String[] { "onCheckVerifyCode xml parser exception" });
        return null;
      } catch (JSONException jSONException) {
        ExLog.w(new String[] { "onCheckVerifyCode exception: ", jSONException.getMessage() });
      } 
    } 
    return null;
  }
  
  public static JSONObject onQueryClient(Context paramContext, InnerState paramInnerState) {
    try {
      JSONObject jSONObject = new JSONObject();
      String str = getOneLevelConfig(paramInnerState, "query-url", null);
      if (!TextUtils.isEmpty(str)) {
        CdcHttpClient.Result result = (new CdcHttpClient(paramInnerState)).post(str, (new CdcRequest(paramInnerState)).toBytes());
        if (a(result)) {
          a(new String(result.getData()), jSONObject, paramInnerState.getTicket());
          if (jSONObject.has("client"))
            return jSONObject; 
          jSONObject.put("errorCode", "200115");
          jSONObject.put("subCode", "");
          return jSONObject;
        } 
        ReturnUIResult returnUIResult = UiErrCode.makeUiErrorCode(result);
        jSONObject.put("errorCode", returnUIResult.getErrorCode());
        jSONObject.put("subCode", returnUIResult.getSubErrorCode());
        return jSONObject;
      } 
      jSONObject.put("errorCode", "300309");
      jSONObject.put("subCode", "");
      return jSONObject;
    } catch (Exception exception) {
      exception.printStackTrace();
      return null;
    } 
  }
  
  public static ReturnUIResult reqireAuth(Context paramContext, InnerState paramInnerState, String paramString1, String paramString2, String paramString3) {
    ReturnUIResult returnUIResult = new ReturnUIResult();
    String str = getOneLevelConfig(paramInnerState, "auth-url", null);
    if (TextUtils.isEmpty(str)) {
      returnUIResult.setErrorCode("300309");
      return returnUIResult;
    } 
    if (TextUtils.isEmpty(paramString1) || TextUtils.isEmpty(paramString2)) {
      returnUIResult.setErrorCode(UiErrCode.makePortalErrorCode("100"));
      return returnUIResult;
    } 
    paramString1 = a(paramString1, paramInnerState);
    CdcRequest cdcRequest = new CdcRequest(paramInnerState);
    cdcRequest.addTicket().addItem("userid", paramString1).addItem("passwd", paramString2);
    if (!TextUtils.isEmpty(paramString3))
      cdcRequest.addItem("verify", paramString3); 
    CdcHttpClient.Result result = (new CdcHttpClient(paramInnerState)).post(str, cdcRequest.toBytes());
    if (a(result))
      return a(paramContext, paramInnerState, result.getData()); 
    if ("105".equals(result.getCdcCode())) {
      ReturnUIResult returnUIResult1 = UiErrCode.makeUiErrorCode(result);
      returnUIResult1.setExtern(paramInnerState.getConfig().optString("Last-Login", "用户账号受限"));
      return returnUIResult1;
    } 
    if ("300".equals(result.getCdcCode()) || "301".equals(result.getCdcCode())) {
      returnUIResult.setErrorCode(result.getCdcCode());
      return returnUIResult;
    } 
    return UiErrCode.makeUiErrorCode(result);
  }
  
  public static KeepResult reqireKeep(InnerState paramInnerState, List<CodeUploadArgsInfo> paramList, ConcurrentHashMap<String, CodeResultInfo> paramConcurrentHashMap, Vector<String> paramVector) {
    String str;
    KeepResult keepResult = new KeepResult(paramInnerState);
    if (TextUtils.isEmpty(KeepResult.a(keepResult)))
      return KeepResult.a(keepResult, "300309"); 
    CdcRequest cdcRequest = new CdcRequest(paramInnerState);
    if (paramList != null && paramList.size() > 0) {
      StringBuilder stringBuilder2 = new StringBuilder();
      int i;
      for (i = 0; i < paramList.size(); i++)
        stringBuilder2.append(((CodeUploadArgsInfo)paramList.get(i)).madeXmlString()); 
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("reqireKeep excheck-args: ");
      stringBuilder1.append(stringBuilder2.toString());
      ExLog.d(new String[] { stringBuilder1.toString() });
      cdcRequest.addItem("excheck-args", stringBuilder2.toString());
    } 
    if (paramConcurrentHashMap != null && !paramConcurrentHashMap.isEmpty()) {
      Iterator<Map.Entry> iterator = paramConcurrentHashMap.entrySet().iterator();
      while (iterator.hasNext()) {
        CodeResultInfo codeResultInfo = (CodeResultInfo)((Map.Entry)iterator.next()).getValue();
        if ("pass".equals(codeResultInfo.getCmd()) && paramVector.contains(CodeResultInfo.madeIPKeyString(codeResultInfo.getArgs())))
          codeResultInfo.setResult(1); 
        if (codeResultInfo.isUpload()) {
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append("reqireKeep excheck-resp: ");
          stringBuilder1.append(codeResultInfo.getArgs());
          stringBuilder1.append("-");
          stringBuilder1.append(codeResultInfo.madeXmlString());
          ExLog.d(new String[] { stringBuilder1.toString() });
          cdcRequest.addItem("excheck-resp", codeResultInfo.madeXmlString());
        } 
      } 
    } 
    CdcHttpClient.Result result = (new CdcHttpClient(paramInnerState)).post(KeepResult.a(keepResult), cdcRequest.addTicket().addNetInfo().toBytes(), 1);
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("reqireKeep cdcRequest: ");
    stringBuilder.append(cdcRequest.toString());
    ExLog.d(new String[] { stringBuilder.toString() });
    if (a(result)) {
      str = new String(result.getData());
      XmlPullParser xmlPullParser = a(str);
      stringBuilder = new StringBuilder();
      stringBuilder.append("reqireKeep data: ");
      stringBuilder.append(str);
      ExLog.d(new String[] { stringBuilder.toString() });
      if (xmlPullParser != null)
        try {
          String str1;
          ArrayList<String> arrayList1 = new ArrayList();
          ArrayList<String> arrayList2 = new ArrayList();
          int i = xmlPullParser.getEventType();
          stringBuilder = null;
          while (true) {
            if (i != 1) {
              if (i == 2) {
                if ("interval".equals(xmlPullParser.getName())) {
                  i = CdcUtils.toInt(xmlPullParser.nextText(), 300);
                  paramInnerState.putConfig(new String[] { "keep" }).put("interval", i);
                  KeepResult.a(keepResult, i);
                  str = str1;
                } else if ("zexcheck".equals(xmlPullParser.getName())) {
                  CodeCheckInfo codeCheckInfo = new CodeCheckInfo();
                } else if ("ticket".equals(xmlPullParser.getName())) {
                  str = str1;
                  if (str1 != null) {
                    str1.setTicket(xmlPullParser.nextText());
                    str = str1;
                  } 
                } else if ("cmd".equals(xmlPullParser.getName())) {
                  str = str1;
                  if (str1 != null) {
                    str1.setCmd(xmlPullParser.nextText());
                    str = str1;
                  } 
                } else {
                  str = str1;
                  if ("args".equals(xmlPullParser.getName())) {
                    str = str1;
                    if (str1 != null) {
                      str1.setArgs(xmlPullParser.nextText());
                      str = str1;
                    } 
                  } 
                } 
                continue;
              } 
              str = str1;
              if (i == 3) {
                str = str1;
                if ("zexcheck".equals(xmlPullParser.getName())) {
                  str = str1;
                  if (str1 != null) {
                    if (str1.isUdpCode()) {
                      arrayList1.add(str1);
                    } else {
                      arrayList2.add(str1);
                    } 
                    str = null;
                  } 
                } 
              } 
              continue;
            } 
            if (arrayList1 != null && arrayList1.size() > 0)
              keepResult.setUdpCodeCheckInfos((List)arrayList1); 
            if (arrayList2 != null && arrayList2.size() > 0)
              keepResult.setOtherCodeCheckInfos((List)arrayList2); 
            StringBuilder stringBuilder1 = new StringBuilder();
            stringBuilder1.append("interval:");
            stringBuilder1.append(KeepResult.b(keepResult));
            stringBuilder1.append(" ");
            str = stringBuilder1.toString();
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("retry:");
            stringBuilder2.append(KeepResult.c(keepResult));
            stringBuilder2.append("");
            ExLog.i(new String[] { "reqireKeep -> ", str, stringBuilder2.toString() });
            a(paramInnerState, KeepResult.b(keepResult) * KeepResult.c(keepResult));
            return KeepResult.a(keepResult, "0");
            i = xmlPullParser.next();
            str1 = str;
          } 
        } catch (Exception exception) {
          ExLog.e(new String[] { "reqireKeep exception: ", exception.getMessage() });
        }  
      return KeepResult.a(keepResult, "200115");
    } 
    if (str.hasJump())
      return KeepResult.a(keepResult, "200117"); 
    ReturnUIResult returnUIResult = UiErrCode.makeUiErrorCode((CdcHttpClient.Result)str);
    KeepResult.a(keepResult, returnUIResult.getErrorCode());
    keepResult.setSubError(returnUIResult.getSubErrorCode());
    return keepResult;
  }
  
  public static ReturnUIResult reqireTerm(InnerState paramInnerState, String paramString) {
    return reqireTerm(paramInnerState, paramString, 5000L, true);
  }
  
  public static ReturnUIResult reqireTerm(InnerState paramInnerState, String paramString, long paramLong) {
    return reqireTerm(paramInnerState, paramString, 5000L, true);
  }
  
  public static ReturnUIResult reqireTerm(InnerState paramInnerState, String paramString, long paramLong, boolean paramBoolean) {
    ExLog.i(new String[] { "reqireTerm reason: ", paramString });
    ReturnUIResult returnUIResult = new ReturnUIResult();
    String str = paramInnerState.getConfig(new String[] { "term" }).optString("url", null);
    if (TextUtils.isEmpty(str)) {
      returnUIResult.setErrorCode("300309");
      return returnUIResult;
    } 
    CdcRequest cdcRequest = new CdcRequest(paramInnerState);
    cdcRequest.addTicket().addItem("reason", paramString);
    CdcHttpClient cdcHttpClient = new CdcHttpClient(paramInnerState);
    CdcHttpClient.Result result2 = cdcHttpClient.post(str, cdcRequest.toBytes(), false, false);
    CdcHttpClient.Result result1 = result2;
    if (!result2.isOk())
      result1 = cdcHttpClient.post(str, cdcRequest.toBytes()); 
    if (result1.isOk() && (result1.getCdcCode().equals("0") || "14".equals(result1.getCdcCode()))) {
      try {
        Thread.sleep(paramLong);
      } catch (InterruptedException interruptedException) {
        interruptedException.printStackTrace();
      } 
      if (paramBoolean)
        paramInnerState.setTicket("").setExpire(new Date()).save(); 
      returnUIResult.setErrorCode("0");
      return returnUIResult;
    } 
    return UiErrCode.makeUiErrorCode((CdcHttpClient.Result)interruptedException);
  }
  
  public static ReturnUIResult reqireTerm(InnerState paramInnerState, String paramString1, String paramString2) {
    ReturnUIResult returnUIResult = new ReturnUIResult();
    String str = paramInnerState.getConfig(new String[] { "term" }).optString("url", null);
    if (TextUtils.isEmpty(str)) {
      returnUIResult.setErrorCode("300309");
      return returnUIResult;
    } 
    return reqireTerm(paramInnerState, str, paramString1, paramString2);
  }
  
  public static ReturnUIResult reqireTerm(InnerState paramInnerState, String paramString1, String paramString2, String paramString3) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("termclient ticket:");
    stringBuilder.append(paramString2);
    stringBuilder.append(" reason:");
    stringBuilder.append(paramString3);
    ExLog.i(new String[] { stringBuilder.toString() });
    ReturnUIResult returnUIResult = new ReturnUIResult();
    CdcRequest cdcRequest = new CdcRequest(paramInnerState);
    cdcRequest.addItem("ticket", paramString2).addItem("reason", paramString3);
    CdcHttpClient cdcHttpClient = new CdcHttpClient(paramInnerState);
    CdcHttpClient.Result result2 = cdcHttpClient.post(paramString1, cdcRequest.toBytes(), false, false);
    CdcHttpClient.Result result1 = result2;
    if (!result2.isOk())
      result1 = cdcHttpClient.post(paramString1, cdcRequest.toBytes()); 
    if (result1.isOk() && (result1.getCdcCode().equals("0") || "14".equals(result1.getCdcCode()))) {
      returnUIResult.setErrorCode("0");
      return returnUIResult;
    } 
    return UiErrCode.makeUiErrorCode(result1);
  }
  
  public static ReturnUIResult reqireTerm(InnerState paramInnerState, String paramString, boolean paramBoolean) {
    return reqireTerm(paramInnerState, paramString, 5000L, paramBoolean);
  }
  
  public static void uploadCollectionInfo(String paramString, CollectionFileInfo paramCollectionFileInfo, CollectionFileInfo.CallBack paramCallBack) {
    if (TextUtils.isEmpty(paramString)) {
      ExLog.i(new String[] { "uploadCollectionInfo url is null" });
      return;
    } 
    Vector<CollectionInfo> vector = paramCollectionFileInfo.getCollectionInfos();
    if (vector == null || vector.size() == 0) {
      ExLog.i(new String[] { "uploadCollectionInfo data is null" });
      return;
    } 
    try {
      JSONArray jSONArray = new JSONArray();
      for (int i = 0; i < vector.size(); i++) {
        JSONObject jSONObject = new JSONObject();
        CollectionInfo collectionInfo = vector.get(i);
        jSONObject.put("account", collectionInfo.getAccount());
        jSONObject.put("clientid", collectionInfo.getClientid());
        jSONObject.put("cid", collectionInfo.getCid());
        jSONObject.put("ostype", collectionInfo.getOstype());
        jSONObject.put("version", collectionInfo.getVersion());
        jSONObject.put("process", collectionInfo.getProcess());
        jSONObject.put("exitcode", collectionInfo.getExitcode());
        jSONObject.put("starttime", collectionInfo.getStarttime());
        jSONObject.put("stoptime", collectionInfo.getStoptime());
        jSONArray.put(jSONObject);
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("uploadCollectionInfo data->");
        stringBuilder1.append(jSONObject.toString());
        ExLog.d(new String[] { stringBuilder1.toString() });
      } 
      HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
      hashMap.put("Content-Type", "application/json");
      HttpClient.Result result = (new HttpClient()).post(paramString, (Map)hashMap, jSONArray.toString().getBytes());
      String str = result.getDataString();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("uploadCollectionInfo ->");
      stringBuilder.append(str);
      ExLog.d(new String[] { stringBuilder.toString() });
      if (result != null && result.isSuccessful() && (new JSONObject(str)).optInt("code", -1) == 0) {
        if (paramCallBack != null) {
          paramCallBack.uploadSuccess();
          return;
        } 
      } else {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("uploadCollectionInfo httpcode ->");
        stringBuilder1.append(result.getHttpCode());
        ExLog.e(new String[] { stringBuilder1.toString() });
        return;
      } 
    } catch (Exception exception) {
      exception.printStackTrace();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("uploadCollectionInfo error ->");
      stringBuilder.append(exception.getMessage());
      ExLog.e(new String[] { stringBuilder.toString() });
      return;
    } 
  }
  
  public static void uploadLog(Context paramContext, InnerState paramInnerState, String paramString) {
    String str3 = paramInnerState.getFuncfgUrl("SubErrorData", null);
    if (TextUtils.isEmpty(str3)) {
      ExLog.e(new String[] { "uploadLog url is null" });
      return;
    } 
    String str2 = (new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA)).format(new Date());
    if (str2.equals(PreferencesUtils.getString(paramContext, "isUpload", "none"))) {
      ExLog.i(new String[] { "uploadLog has done" });
      return;
    } 
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    hashMap.put("ErrType", "android");
    hashMap.put("ErrCode", "10000");
    hashMap.put("Account", paramString);
    hashMap.put("IPAddr", CdcUtils.getIP());
    hashMap.put("CID", paramInnerState.getSchoolId("361"));
    hashMap.put("ClientID", Build.SERIAL);
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append(AppInfoUtils.getVersionCode(paramContext));
    stringBuilder1.append("");
    hashMap.put("Version", stringBuilder1.toString());
    stringBuilder1 = new StringBuilder();
    if (hashMap != null && !hashMap.isEmpty())
      for (String str : hashMap.keySet()) {
        if (stringBuilder1.length() != 0)
          stringBuilder1.append("&"); 
        try {
          StringBuilder stringBuilder3 = new StringBuilder();
          stringBuilder3.append(str);
          stringBuilder3.append("=");
          StringBuilder stringBuilder4 = new StringBuilder();
          stringBuilder4.append((String)hashMap.get(str));
          stringBuilder4.append("");
          stringBuilder3.append(URLEncoder.encode(stringBuilder4.toString(), "utf-8"));
          stringBuilder1.append(stringBuilder3.toString());
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
          unsupportedEncodingException.printStackTrace();
        } 
      }  
    String str1 = String.format("%s?%s", new Object[] { str3, stringBuilder1.toString() });
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append("REQ =>");
    stringBuilder2.append(str1);
    ExLog.i(new String[] { stringBuilder2.toString() });
    File file = CdcUtils.openFileDir(paramContext, ExLog.LOG_DIR);
    if (file != null) {
      File[] arrayOfFile = file.listFiles();
      int i;
      for (i = 0; i < arrayOfFile.length; i++) {
        File file2 = arrayOfFile[i];
        if (file2.isFile() && file2.getName().endsWith(".zip"))
          file2.getAbsoluteFile().delete(); 
      } 
      StringBuilder stringBuilder5 = new StringBuilder();
      stringBuilder5.append(file.getPath());
      stringBuilder5.append(File.separator);
      String str5 = stringBuilder5.toString();
      StringBuilder stringBuilder4 = new StringBuilder();
      stringBuilder4.append(str5);
      stringBuilder4.append("CDCLogs.zip");
      String str4 = stringBuilder4.toString();
      File file1 = new File(str4);
      if (file1.exists())
        file1.getAbsoluteFile().delete(); 
      if (!FileUtils.toZip(str5, str4)) {
        ExLog.e(new String[] { "uploadLog files to zip is error" });
        return;
      } 
      HashMap<Object, Object> hashMap1 = new HashMap<Object, Object>();
      hashMap1.put("CDCLogs.zip", str4);
      HttpClient.Result result = (new HttpClient()).uploadFile(str1, null, (Map)hashMap1);
      if (result.isSuccessful()) {
        PreferencesUtils.putString(paramContext, "isUpload", str2);
        File file2 = new File(str5);
        if (file2.exists()) {
          File[] arrayOfFile1 = file2.listFiles();
          for (i = 0; i < arrayOfFile1.length; i++) {
            if (arrayOfFile1[i].exists())
              arrayOfFile1[i].getAbsoluteFile().delete(); 
          } 
        } 
        file2 = new File(str4);
        if (file2.exists())
          file2.getAbsoluteFile().delete(); 
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("uploadLog files success ->");
        stringBuilder.append(result.getDataString());
        ExLog.i(new String[] { stringBuilder.toString() });
        return;
      } 
      StringBuilder stringBuilder3 = new StringBuilder();
      stringBuilder3.append("uploadLog files error ->");
      stringBuilder3.append(result.getDataString());
      ExLog.e(new String[] { stringBuilder3.toString() });
      return;
    } 
    ExLog.e(new String[] { "uploadLog files dir is no permission" });
  }
  
  public static void uploadPhoneMarketingData(Context paramContext, String paramString, InnerState paramInnerState) {
    boolean bool;
    String str1 = paramInnerState.getFuncfgUrl("PhoneMarketingData", null);
    if (TextUtils.isEmpty(str1)) {
      ExLog.i(new String[] { "uploadPhoneMarketingData url is null" });
      return;
    } 
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    String str2 = paramInnerState.getSchoolId("361");
    hashMap.put("Account", paramString);
    hashMap.put("UpdateTime", (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA)).format(new Date()));
    hashMap.put("IsFirstTime", "1");
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append(AppInfoUtils.getVersionCode(paramContext));
    stringBuilder2.append("");
    hashMap.put("Versions", stringBuilder2.toString());
    hashMap.put("Language", "2052_936");
    hashMap.put("DialUptype", "3");
    String[] arrayOfString = CdcUtils.getNetworkInfo();
    if (!TextUtils.isEmpty(arrayOfString[0]) && !TextUtils.isEmpty(arrayOfString[1])) {
      bool = true;
    } else if (!TextUtils.isEmpty(arrayOfString[1])) {
      bool = true;
    } else {
      bool = true;
    } 
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append(bool);
    stringBuilder1.append("");
    hashMap.put("IPv6", stringBuilder1.toString());
    hashMap.put("ClientID", paramInnerState.getClientId());
    if (paramInnerState.getNetInfo() != null)
      hashMap.put("WifiMac", paramInnerState.getNetInfo().getWifiMac()); 
    hashMap.put("IP", CdcUtils.getIP());
    hashMap.put("CID", str2);
    hashMap.put("areaCode", "zs");
    stringBuilder1 = new StringBuilder();
    stringBuilder1.append("android");
    stringBuilder1.append(Build.VERSION.RELEASE);
    hashMap.put("osType", stringBuilder1.toString());
    HttpClient.Result result = (new HttpClient()).get(str1, (Map<String, String>)null, (Map)hashMap);
    if (result != null && result.isSuccessful()) {
      String str = result.getDataString();
      StringBuilder stringBuilder4 = new StringBuilder();
      stringBuilder4.append("uploadPhoneMarketingData ->");
      stringBuilder4.append(str);
      ExLog.d(new String[] { stringBuilder4.toString() });
      StringBuilder stringBuilder3 = new StringBuilder();
      stringBuilder3.append("uploadPhoneMarketingData httpcode ->");
      stringBuilder3.append(result.getHttpCode());
      ExLog.i(new String[] { stringBuilder3.toString() });
      return;
    } 
    stringBuilder2 = new StringBuilder();
    stringBuilder2.append("uploadPhoneMarketingData httpcode ->");
    stringBuilder2.append(result.getHttpCode());
    ExLog.e(new String[] { stringBuilder2.toString() });
  }
  
  public static void uploadSSIDData(Context paramContext, String paramString, InnerState paramInnerState) {
    NetInfo netInfo = paramInnerState.getNetInfo();
    String str1 = paramInnerState.getFuncfgUrl("PostSSID", null);
    if (TextUtils.isEmpty(str1)) {
      ExLog.i(new String[] { "uploadSSIDData url is null" });
      return;
    } 
    String str2 = paramInnerState.getTicket();
    String str3 = paramInnerState.getSchoolId("");
    try {
      JSONObject jSONObject = new JSONObject();
      jSONObject.put("account", paramString);
      jSONObject.put("ticket", str2);
      jSONObject.put("clientid", paramInnerState.getClientId());
      jSONObject.put("ostype", "2");
      jSONObject.put("mac", netInfo.getWifiMac());
      jSONObject.put("ssid", netInfo.getSSID());
      jSONObject.put("version", AppInfoUtils.getVersionCode(paramContext));
      jSONObject.put("cid", str3);
      String str = CdcUtils.des3EncodeECB(jSONObject.toString().getBytes("utf-8"));
      HttpClient.Result result = (new HttpClient()).post(str1, str.getBytes());
      if (result != null && result.isSuccessful()) {
        paramString = result.getDataString();
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("uploadSSIDData ->");
        stringBuilder2.append(paramString);
        ExLog.d(new String[] { stringBuilder2.toString() });
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("uploadSSIDData httpcode ->");
        stringBuilder1.append(result.getHttpCode());
        ExLog.i(new String[] { stringBuilder1.toString() });
        return;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("uploadSSIDData httpcode ->");
      stringBuilder.append(result.getHttpCode());
      ExLog.e(new String[] { stringBuilder.toString() });
      return;
    } catch (Exception exception) {
      exception.printStackTrace();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("uploadSSIDData error ->");
      stringBuilder.append(exception.getMessage());
      ExLog.e(new String[] { stringBuilder.toString() });
      return;
    } 
  }
  
  private static class CdcRequest {
    private static final ThreadLocal<SimpleDateFormat> b = new ThreadLocal<SimpleDateFormat>() {
        protected SimpleDateFormat a() {
          return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
      };
    
    InnerState a;
    
    private StringBuilder c = new StringBuilder(1024);
    
    private String d = null;
    
    public CdcRequest() {
      this.c.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><request>");
    }
    
    public CdcRequest(InnerState param1InnerState) {
      this.a = param1InnerState;
      this.c.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><request>");
      addItem("user-agent", param1InnerState.getUserAgent(false));
      addItem("client-id", param1InnerState.getClientId());
      addItem("local-time", ((SimpleDateFormat)b.get()).format(new Date()));
    }
    
    private void a() {
      if (this.d == null) {
        StringBuilder stringBuilder = this.c;
        stringBuilder.append("</request>");
        this.d = stringBuilder.toString();
      } 
    }
    
    public CdcRequest addItem(String param1String1, String param1String2) {
      StringBuilder stringBuilder = this.c;
      stringBuilder.append('<');
      stringBuilder.append(param1String1);
      stringBuilder.append('>');
      stringBuilder.append(param1String2);
      stringBuilder.append("</");
      stringBuilder.append(param1String1);
      stringBuilder.append('>');
      return this;
    }
    
    public CdcRequest addNetInfo() {
      NetInfo netInfo = this.a.getNetInfo();
      return addItem("ipv4", netInfo.getIpv4()).addItem("ipv6", netInfo.getIpv6()).addItem("mac", netInfo.getMac());
    }
    
    public CdcRequest addTicket() {
      return addItem("ticket", this.a.getTicket());
    }
    
    public byte[] toBytes() {
      a();
      try {
        return this.d.getBytes("UTF-8");
      } catch (Exception exception) {
        ExLog.e(new String[] { "CdcRequest.toBytes Exception: ", exception.getMessage() });
        return null;
      } 
    }
    
    public String toString() {
      a();
      return this.d;
    }
  }
  
  static final class null extends ThreadLocal<SimpleDateFormat> {
    protected SimpleDateFormat a() {
      return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
  }
  
  public static class KeepResult {
    private String a;
    
    private int b;
    
    private long c;
    
    private String d = null;
    
    private String e = null;
    
    private List<CodeCheckInfo> f;
    
    private List<CodeCheckInfo> g;
    
    private KeepResult(InnerState param1InnerState) {
      JSONObject jSONObject = param1InnerState.getConfig(new String[] { "keep" });
      this.a = jSONObject.optString("url", null);
      a(jSONObject.optInt("retry", 3));
      b(jSONObject.optInt("interval", 300));
    }
    
    private KeepResult a(String param1String) {
      this.d = param1String;
      return this;
    }
    
    private void a(int param1Int) {
      int i = param1Int;
      if (param1Int < 0)
        i = 3; 
      this.b = i;
    }
    
    private void b(int param1Int) {
      int i = param1Int;
      if (param1Int < 0)
        i = 300; 
      this.c = (i * 1000);
    }
    
    public String getError() {
      return this.d;
    }
    
    public long getInterval() {
      return this.c;
    }
    
    public List<CodeCheckInfo> getOtherCodeCheckInfos() {
      return this.g;
    }
    
    public int getRetry() {
      return this.b;
    }
    
    public String getSubError() {
      return this.e;
    }
    
    public List<CodeCheckInfo> getUdpCodeCheckInfos() {
      return this.f;
    }
    
    public void setOtherCodeCheckInfos(List<CodeCheckInfo> param1List) {
      this.g = param1List;
    }
    
    public KeepResult setSubError(String param1String) {
      this.e = param1String;
      return this;
    }
    
    public void setUdpCodeCheckInfos(List<CodeCheckInfo> param1List) {
      this.f = param1List;
    }
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\http\CdcProtocol.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */