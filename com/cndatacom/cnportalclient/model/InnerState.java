package com.cndatacom.cnportalclient.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.cndatacom.campus.netcore.DaMod;
import com.cndatacom.cnportalclient.constant.Constant;
import com.cndatacom.cnportalclient.utils.CdcUtils;
import com.cndatacom.cnportalclient.utils.ExLog;
import com.cndatacom.cnportalclient.utils.PreferencesUtils;
import java.util.Date;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class InnerState implements Parcelable {
  public static final Parcelable.Creator<InnerState> CREATOR;
  
  private static final JSONObject a = new JSONObject();
  
  private int b = 0;
  
  private String c = "";
  
  private String d = "";
  
  private String e = "";
  
  private Date f = new Date(0L);
  
  private String g = "";
  
  private String h = "";
  
  private String i = "";
  
  private NetInfo j = new NetInfo();
  
  private JSONObject k = a;
  
  private DaMod l = null;
  
  private String m = "";
  
  static {
    CREATOR = new Parcelable.Creator<InnerState>() {
        public InnerState createFromParcel(Parcel param1Parcel) {
          return new InnerState(param1Parcel);
        }
        
        public InnerState[] newArray(int param1Int) {
          return new InnerState[param1Int];
        }
      };
  }
  
  protected InnerState(Parcel paramParcel) {
    if (paramParcel != null) {
      this.b = paramParcel.readInt();
      this.c = paramParcel.readString();
      this.d = paramParcel.readString();
      if (this.d == null)
        this.d = ""; 
      this.e = paramParcel.readString();
      if (this.e == null)
        this.e = ""; 
      this.f = new Date(paramParcel.readLong());
      this.g = paramParcel.readString();
      if (this.g == null)
        this.g = ""; 
      this.h = paramParcel.readString();
      if (this.h == null)
        this.h = ""; 
      this.i = paramParcel.readString();
      if (this.i == null)
        this.i = ""; 
      this.j = new NetInfo(paramParcel);
      String str = paramParcel.readString();
      if (TextUtils.isEmpty(str)) {
        this.k = new JSONObject();
      } else {
        try {
          this.k = new JSONObject(str);
        } catch (JSONException jSONException) {
          ExLog.e(new String[] { "InnerState Exception: ", jSONException.getMessage() });
        } 
      } 
      this.m = paramParcel.readString();
      if (this.m == null)
        this.m = ""; 
    } 
    if (this.k == null)
      this.k = new JSONObject(); 
    if (this.m == null)
      this.m = ""; 
  }
  
  private InnerState a() {
    try {
      JSONObject jSONObject = getConfig(new String[] { "head" });
      if (TextUtils.isEmpty(getSchoolId(""))) {
        String str = PreferencesUtils.getString(CdcUtils.getContext(), "schoolID", "");
        if (!TextUtils.isEmpty(str))
          jSONObject.put("schoolID", str); 
        str = PreferencesUtils.getString(CdcUtils.getContext(), "area", "");
        if (!TextUtils.isEmpty(str))
          jSONObject.put("area", str); 
        str = PreferencesUtils.getString(CdcUtils.getContext(), "domain", "");
        if (!TextUtils.isEmpty(str)) {
          jSONObject.put("domain", str);
          return this;
        } 
      } 
    } catch (JSONException jSONException) {
      jSONException.printStackTrace();
    } 
    return this;
  }
  
  private String a(String paramString, JSONObject paramJSONObject) {
    StringBuilder stringBuilder1 = new StringBuilder();
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append("<");
    stringBuilder2.append(paramString);
    stringBuilder1.append(stringBuilder2.toString());
    if (paramJSONObject != null) {
      Iterator<String> iterator = paramJSONObject.keys();
      while (iterator.hasNext()) {
        String str = ((String)iterator.next()).trim();
        if (!paramString.equals(str)) {
          String str1 = paramJSONObject.optString(str, "").trim();
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append(" ");
          stringBuilder.append(str);
          stringBuilder.append("=\"");
          stringBuilder.append(str1);
          stringBuilder.append("\"");
          stringBuilder1.append(stringBuilder.toString());
        } 
      } 
    } 
    stringBuilder1.append("/>");
    return stringBuilder1.toString();
  }
  
  private void a(Context paramContext) {
    SharedPreferences.Editor editor = PreferencesUtils.getEditor(paramContext);
    editor.putString("wlanipname", getWlanIpName());
    editor.commit();
  }
  
  private void b(Context paramContext) {
    SharedPreferences sharedPreferences = paramContext.getSharedPreferences("index-temp-config", 0);
    SharedPreferences.Editor editor2 = sharedPreferences.edit();
    SharedPreferences.Editor editor1 = paramContext.getSharedPreferences("app-config", 0).edit();
    Iterator<String> iterator = sharedPreferences.getAll().keySet().iterator();
    while (iterator.hasNext())
      editor1.remove(iterator.next()); 
    editor2.clear();
    editor2.commit();
    editor1.commit();
  }
  
  private void c(Context paramContext) {
    JSONObject jSONObject = getConfig(new String[] { "head" });
    if (jSONObject != null) {
      Iterator<String> iterator = jSONObject.keys();
      SharedPreferences.Editor editor2 = PreferencesUtils.getEditor(paramContext);
      SharedPreferences.Editor editor1 = paramContext.getSharedPreferences("index-temp-config", 0).edit();
      while (iterator.hasNext()) {
        String str = ((String)iterator.next()).trim();
        if (!TextUtils.isEmpty(str)) {
          editor1.putString(str, "");
          editor2.putString(str, jSONObject.optString(str, "").trim());
        } 
      } 
      editor1.commit();
      editor2.commit();
    } 
  }
  
  private void d(Context paramContext) {
    try {
      JSONObject jSONObject = getConfig();
      if (jSONObject != null && jSONObject.has("config")) {
        jSONObject = jSONObject.getJSONObject("config");
        if (jSONObject != null && jSONObject.has("funcfg")) {
          jSONObject = jSONObject.getJSONObject("funcfg");
          SharedPreferences.Editor editor2 = PreferencesUtils.getEditor(paramContext);
          SharedPreferences.Editor editor1 = paramContext.getSharedPreferences("index-temp-config", 0).edit();
          Iterator<String> iterator = jSONObject.keys();
          while (iterator.hasNext()) {
            String str = ((String)iterator.next()).trim();
            if (!TextUtils.isEmpty(str)) {
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append("FUN_");
              stringBuilder.append(str);
              String str1 = stringBuilder.toString();
              editor1.putString(str1, "");
              editor2.putString(str1, a(str, jSONObject.getJSONObject(str)));
            } 
          } 
          editor1.commit();
          editor2.commit();
          return;
        } 
      } 
    } catch (JSONException jSONException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("saveFuncfgPreferences Exception :");
      stringBuilder.append(jSONException.toString());
      ExLog.e(new String[] { stringBuilder.toString() });
    } 
  }
  
  public static InnerState load() {
    /* monitor enter StringConstantExpression{"innerstate"} */
    Parcel parcel = null;
    try {
      byte[] arrayOfByte = CdcUtils.readFile("innerstate");
      if (arrayOfByte == null) {
        ExLog.i(new String[] { "InnerState.load readFile => null" });
      } else {
        Parcel parcel1 = Parcel.obtain();
        parcel1.unmarshall(CdcUtils.decrypt(arrayOfByte), 0, arrayOfByte.length);
        parcel1.setDataPosition(0);
        parcel = parcel1;
      } 
    } catch (Exception exception) {
      ExLog.e(new String[] { "InnerState.load exception: ", exception.getMessage() });
    } finally {}
    /* monitor exit StringConstantExpression{"innerstate"} */
    return ((InnerState)CREATOR.createFromParcel(parcel)).a();
  }
  
  public static InnerState obtain() {
    return ((InnerState)CREATOR.createFromParcel(null)).a();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public InnerState freeDaMod() {
    // Byte code:
    //   0: aload_0
    //   1: getfield l : Lcom/cndatacom/campus/netcore/DaMod;
    //   4: ifnull -> 43
    //   7: ldc_w 'damod'
    //   10: monitorenter
    //   11: aload_0
    //   12: getfield l : Lcom/cndatacom/campus/netcore/DaMod;
    //   15: ifnull -> 25
    //   18: aload_0
    //   19: getfield l : Lcom/cndatacom/campus/netcore/DaMod;
    //   22: invokevirtual Free : ()V
    //   25: aload_0
    //   26: aconst_null
    //   27: putfield l : Lcom/cndatacom/campus/netcore/DaMod;
    //   30: ldc_w 'damod'
    //   33: monitorexit
    //   34: aload_0
    //   35: areturn
    //   36: astore_1
    //   37: ldc_w 'damod'
    //   40: monitorexit
    //   41: aload_1
    //   42: athrow
    //   43: aload_0
    //   44: areturn
    // Exception table:
    //   from	to	target	type
    //   11	25	36	finally
    //   25	34	36	finally
    //   37	41	36	finally
  }
  
  public String getClientId() {
    // Byte code:
    //   0: aload_0
    //   1: getfield c : Ljava/lang/String;
    //   4: invokestatic isEmpty : (Ljava/lang/CharSequence;)Z
    //   7: ifeq -> 76
    //   10: ldc_w 'clientid'
    //   13: monitorenter
    //   14: aload_0
    //   15: getfield c : Ljava/lang/String;
    //   18: invokestatic isEmpty : (Ljava/lang/CharSequence;)Z
    //   21: ifeq -> 62
    //   24: ldc_w 'clientid'
    //   27: invokestatic readFileText : (Ljava/lang/String;)Ljava/lang/String;
    //   30: invokevirtual trim : ()Ljava/lang/String;
    //   33: astore_2
    //   34: aload_2
    //   35: astore_1
    //   36: aload_2
    //   37: invokestatic isEmpty : (Ljava/lang/CharSequence;)Z
    //   40: ifeq -> 57
    //   43: invokestatic randomUUID : ()Ljava/util/UUID;
    //   46: invokevirtual toString : ()Ljava/lang/String;
    //   49: astore_1
    //   50: aload_1
    //   51: ldc_w 'clientid'
    //   54: invokestatic writeFileText : (Ljava/lang/String;Ljava/lang/String;)V
    //   57: aload_0
    //   58: aload_1
    //   59: putfield c : Ljava/lang/String;
    //   62: ldc_w 'clientid'
    //   65: monitorexit
    //   66: goto -> 76
    //   69: astore_1
    //   70: ldc_w 'clientid'
    //   73: monitorexit
    //   74: aload_1
    //   75: athrow
    //   76: aload_0
    //   77: getfield c : Ljava/lang/String;
    //   80: areturn
    // Exception table:
    //   from	to	target	type
    //   14	34	69	finally
    //   36	57	69	finally
    //   57	62	69	finally
    //   62	66	69	finally
    //   70	74	69	finally
  }
  
  public JSONObject getConfig() {
    return this.k;
  }
  
  public JSONObject getConfig(String... paramVarArgs) {
    JSONObject jSONObject = this.k;
    if (paramVarArgs.length > 0) {
      String str = paramVarArgs[0];
      JSONObject jSONObject2 = jSONObject.optJSONObject(str);
      JSONObject jSONObject1 = jSONObject2;
      if (jSONObject2 == null) {
        jSONObject1 = new JSONObject();
        try {
          jSONObject.put(str, jSONObject1);
          return jSONObject1;
        } catch (JSONException jSONException) {
          jSONException.printStackTrace();
        } 
      } 
      return jSONObject1;
    } 
    return jSONObject;
  }
  
  public DaMod getDaMod() {
    // Byte code:
    //   0: aload_0
    //   1: getfield l : Lcom/cndatacom/campus/netcore/DaMod;
    //   4: ifnull -> 17
    //   7: aload_0
    //   8: getfield l : Lcom/cndatacom/campus/netcore/DaMod;
    //   11: invokevirtual isOk : ()Z
    //   14: ifne -> 106
    //   17: ldc_w 'damod'
    //   20: monitorenter
    //   21: aload_0
    //   22: getfield l : Lcom/cndatacom/campus/netcore/DaMod;
    //   25: ifnull -> 40
    //   28: aload_0
    //   29: getfield l : Lcom/cndatacom/campus/netcore/DaMod;
    //   32: invokevirtual isOk : ()Z
    //   35: istore_1
    //   36: iload_1
    //   37: ifne -> 102
    //   40: ldc_w 'damod'
    //   43: invokestatic readFile : (Ljava/lang/String;)[B
    //   46: astore_2
    //   47: aload_2
    //   48: ifnull -> 102
    //   51: aload_0
    //   52: new com/cndatacom/campus/netcore/DaMod
    //   55: dup
    //   56: aload_2
    //   57: invokespecial <init> : ([B)V
    //   60: putfield l : Lcom/cndatacom/campus/netcore/DaMod;
    //   63: aload_0
    //   64: getfield l : Lcom/cndatacom/campus/netcore/DaMod;
    //   67: invokevirtual isOk : ()Z
    //   70: ifne -> 102
    //   73: aload_0
    //   74: aconst_null
    //   75: putfield l : Lcom/cndatacom/campus/netcore/DaMod;
    //   78: goto -> 102
    //   81: astore_2
    //   82: iconst_2
    //   83: anewarray java/lang/String
    //   86: dup
    //   87: iconst_0
    //   88: ldc_w 'InnerState.getDaMod Exception: '
    //   91: aastore
    //   92: dup
    //   93: iconst_1
    //   94: aload_2
    //   95: invokevirtual getMessage : ()Ljava/lang/String;
    //   98: aastore
    //   99: invokestatic e : ([Ljava/lang/String;)V
    //   102: ldc_w 'damod'
    //   105: monitorexit
    //   106: aload_0
    //   107: getfield l : Lcom/cndatacom/campus/netcore/DaMod;
    //   110: areturn
    //   111: astore_2
    //   112: ldc_w 'damod'
    //   115: monitorexit
    //   116: aload_2
    //   117: athrow
    // Exception table:
    //   from	to	target	type
    //   21	36	111	finally
    //   40	47	81	java/lang/Exception
    //   40	47	111	finally
    //   51	78	81	java/lang/Exception
    //   51	78	111	finally
    //   82	102	111	finally
    //   102	106	111	finally
    //   112	116	111	finally
  }
  
  public Date getExpire() {
    return this.f;
  }
  
  public String getFuncfgUrl(String paramString1, String paramString2) {
    return getFuncfgUrl(paramString1, "url", paramString2);
  }
  
  public String getFuncfgUrl(String paramString1, String paramString2, String paramString3) {
    try {
      JSONObject jSONObject = getConfig();
      if (jSONObject != null && jSONObject.has("config")) {
        jSONObject = jSONObject.getJSONObject("config");
        if (jSONObject != null && jSONObject.has("funcfg")) {
          jSONObject = jSONObject.getJSONObject("funcfg");
          if (jSONObject != null && jSONObject.has(paramString1)) {
            JSONObject jSONObject1 = jSONObject.getJSONObject(paramString1);
            return jSONObject1.optString("enable", "0").trim().equals("0") ? "" : jSONObject1.optString(paramString2, paramString3).trim();
          } 
        } 
      } 
    } catch (JSONException jSONException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("getFuncfgUrl Exception :");
      stringBuilder.append(jSONException.toString());
      ExLog.e(new String[] { stringBuilder.toString() });
    } 
    return paramString3;
  }
  
  public String getGateway() {
    return this.m;
  }
  
  public NetInfo getNetInfo() {
    return this.j;
  }
  
  public String getSchoolId(String paramString) {
    return getConfig(new String[] { "head" }).optString("schoolID", paramString);
  }
  
  public int getState() {
    return this.b;
  }
  
  public String getTicket() {
    return this.d;
  }
  
  public String getUserAgent(boolean paramBoolean) {
    Context context = CdcUtils.getContext();
    if (paramBoolean) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("CCTP");
      stringBuilder1.append("/");
      stringBuilder1.append(Constant.getDefUserAgent());
      str = stringBuilder1.toString();
    } else {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("CCTP");
      stringBuilder1.append("/");
      stringBuilder1.append(Constant.getUserAgent());
      str = stringBuilder1.toString();
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(str);
    stringBuilder.append("/@");
    String str = stringBuilder.toString();
    try {
      return str.replace("@", String.valueOf((context.getPackageManager().getPackageInfo(context.getPackageName(), 0)).versionCode));
    } catch (Exception exception) {
      ExLog.e(new String[] { "getPortalUserAgent Exception", exception.toString() });
      return str.replace("@", "1");
    } 
  }
  
  public String getUserId() {
    return this.e;
  }
  
  public String getWlanIpName() {
    return this.i;
  }
  
  public String getWlanacip() {
    return this.h;
  }
  
  public String getWlanuserip() {
    return this.g;
  }
  
  public boolean isOnline() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("isOnline->state:");
    stringBuilder.append(this.b);
    stringBuilder.append(" ticket:");
    stringBuilder.append(this.d);
    stringBuilder.append(" expire:");
    stringBuilder.append(this.f.after(new Date()));
    ExLog.i(new String[] { stringBuilder.toString() });
    return (this.b == 3 && !TextUtils.isEmpty(this.d) && this.f.after(new Date()));
  }
  
  public JSONObject putConfig(String... paramVarArgs) {
    JSONObject jSONObject = this.k;
    int j = paramVarArgs.length;
    for (int i = 0; i < j; i++) {
      String str = paramVarArgs[i];
      Object object = jSONObject.opt(str);
      if (object == null) {
        object = new JSONObject();
        try {
          jSONObject.put(str, object);
          jSONObject = (JSONObject)object;
        } catch (JSONException jSONException) {
          ExLog.e(new String[] { "InnerState.putConfig Exception: ", jSONException.getMessage() });
          return null;
        } 
      } else if (object instanceof JSONObject) {
        jSONObject = (JSONObject)object;
      } else {
        return null;
      } 
    } 
    return jSONObject;
  }
  
  public InnerState save() {
    Parcel parcel = Parcel.obtain();
    writeToParcel(parcel, 1);
    /* monitor enter StringConstantExpression{"innerstate"} */
    try {
      CdcUtils.writeFile(CdcUtils.encryp(parcel.marshall()), "innerstate");
    } catch (Exception exception) {
      ExLog.e(new String[] { "InnerState.save Exception: ", exception.getMessage() });
    } finally {}
    /* monitor exit StringConstantExpression{"innerstate"} */
    return this;
  }
  
  public void savePreferences(Context paramContext) {
    b(paramContext);
    c(paramContext);
    d(paramContext);
    a(paramContext);
  }
  
  public InnerState setDaMod(DaMod paramDaMod) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual isOk : ()Z
    //   4: ifeq -> 43
    //   7: ldc_w 'damod'
    //   10: monitorenter
    //   11: aload_0
    //   12: getfield l : Lcom/cndatacom/campus/netcore/DaMod;
    //   15: ifnull -> 25
    //   18: aload_0
    //   19: getfield l : Lcom/cndatacom/campus/netcore/DaMod;
    //   22: invokevirtual Free : ()V
    //   25: aload_0
    //   26: aload_1
    //   27: putfield l : Lcom/cndatacom/campus/netcore/DaMod;
    //   30: ldc_w 'damod'
    //   33: monitorexit
    //   34: aload_0
    //   35: areturn
    //   36: astore_1
    //   37: ldc_w 'damod'
    //   40: monitorexit
    //   41: aload_1
    //   42: athrow
    //   43: aload_0
    //   44: areturn
    // Exception table:
    //   from	to	target	type
    //   11	25	36	finally
    //   25	34	36	finally
    //   37	41	36	finally
  }
  
  public InnerState setDaMod(byte[] paramArrayOfbyte) {
    try {
      CdcUtils.writeFile(paramArrayOfbyte, "damod");
    } catch (Exception exception) {
      ExLog.e(new String[] { "InnerState.setDaMod Exception: ", exception.getMessage() });
    } 
    return setDaMod(new DaMod(paramArrayOfbyte));
  }
  
  public InnerState setExpire(Date paramDate) {
    this.f = paramDate;
    return this;
  }
  
  public InnerState setGateway(String paramString) {
    this.m = paramString;
    return this;
  }
  
  public InnerState setNetInfo(NetInfo paramNetInfo) {
    this.j = paramNetInfo;
    if (this.j != null)
      setGateway(this.j.getGateway()); 
    return this;
  }
  
  public InnerState setState(int paramInt) {
    this.b = paramInt;
    return this;
  }
  
  public InnerState setTicket(String paramString) {
    this.d = paramString;
    return this;
  }
  
  public InnerState setUserId(String paramString) {
    this.e = paramString;
    return this;
  }
  
  public InnerState setWlanIpName(String paramString) {
    this.i = paramString;
    return this;
  }
  
  public InnerState setWlanacip(String paramString) {
    this.h = paramString;
    return this;
  }
  
  public InnerState setWlanuserip(String paramString) {
    this.g = paramString;
    return this;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.b);
    paramParcel.writeString(this.c);
    paramParcel.writeString(this.d);
    paramParcel.writeString(this.e);
    paramParcel.writeLong(this.f.getTime());
    paramParcel.writeString(this.g);
    paramParcel.writeString(this.h);
    paramParcel.writeString(this.i);
    this.j.writeToParcel(paramParcel, paramInt);
    paramParcel.writeString(this.k.toString());
    paramParcel.writeString(this.m);
  }
  
  public static final class STATE {
    public static final int NONE = 0;
    
    public static final int ONAUTHING = 2;
    
    public static final int ONLINE = 3;
    
    public static final int ONTERMED = 6;
    
    public static final int ONTERMING = 4;
    
    public static final int OTHER = 5;
    
    public static final int TICKET = 1;
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\model\InnerState.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */