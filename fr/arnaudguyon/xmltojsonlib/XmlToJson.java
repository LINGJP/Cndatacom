package fr.arnaudguyon.xmltojsonlib;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Matcher;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class XmlToJson {
  private static final String DEFAULT_CONTENT_NAME = "content";
  
  private static final boolean DEFAULT_EMPTY_BOOLEAN = false;
  
  private static final double DEFAULT_EMPTY_DOUBLE = 0.0D;
  
  private static final int DEFAULT_EMPTY_INTEGER = 0;
  
  private static final long DEFAULT_EMPTY_LONG = 0L;
  
  private static final String DEFAULT_EMPTY_STRING = "";
  
  private static final String DEFAULT_ENCODING = "utf-8";
  
  private static final String DEFAULT_INDENTATION = "   ";
  
  private static final String TAG = "XmlToJson";
  
  private HashMap<String, String> mAttributeNameReplacements;
  
  private HashMap<String, String> mContentNameReplacements;
  
  private HashMap<String, Class> mForceClassForPath;
  
  private HashSet<String> mForceListPaths;
  
  private String mIndentationPattern = "   ";
  
  private String mInputEncoding;
  
  private InputStream mInputStreamSource;
  
  private JSONObject mJsonObject;
  
  private HashSet<String> mSkippedAttributes = new HashSet<String>();
  
  private HashSet<String> mSkippedTags = new HashSet<String>();
  
  private StringReader mStringSource;
  
  private XmlToJson(Builder paramBuilder) {
    this.mStringSource = paramBuilder.mStringSource;
    this.mInputStreamSource = paramBuilder.mInputStreamSource;
    this.mInputEncoding = paramBuilder.mInputEncoding;
    this.mForceListPaths = paramBuilder.mForceListPaths;
    this.mAttributeNameReplacements = paramBuilder.mAttributeNameReplacements;
    this.mContentNameReplacements = paramBuilder.mContentNameReplacements;
    this.mForceClassForPath = paramBuilder.mForceClassForPath;
    this.mSkippedAttributes = paramBuilder.mSkippedAttributes;
    this.mSkippedTags = paramBuilder.mSkippedTags;
    this.mJsonObject = convertToJSONObject();
  }
  
  private JSONObject convertTagToJson(Tag paramTag, boolean paramBoolean) {
    JSONObject jSONObject = new JSONObject();
    if (paramTag.getContent() != null) {
      String str = paramTag.getPath();
      putContent(str, jSONObject, getContentNameReplacement(str, "content"), paramTag.getContent());
    } 
    try {
      for (ArrayList<Tag> arrayList : paramTag.getGroupedElements().values()) {
        Tag tag;
        if (arrayList.size() == 1) {
          tag = arrayList.get(0);
          if (isForcedList(tag)) {
            JSONArray jSONArray1 = new JSONArray();
            jSONArray1.put(convertTagToJson(tag, true));
            jSONObject.put(tag.getName(), jSONArray1);
            continue;
          } 
          if (tag.hasChildren()) {
            JSONObject jSONObject1 = convertTagToJson(tag, false);
            jSONObject.put(tag.getName(), jSONObject1);
            continue;
          } 
          putContent(tag.getPath(), jSONObject, tag.getName(), tag.getContent());
          continue;
        } 
        JSONArray jSONArray = new JSONArray();
        Iterator<Tag> iterator = tag.iterator();
        while (iterator.hasNext())
          jSONArray.put(convertTagToJson(iterator.next(), true)); 
        jSONObject.put(((Tag)tag.get(0)).getName(), jSONArray);
      } 
      return jSONObject;
    } catch (JSONException jSONException) {
      jSONException.printStackTrace();
      return null;
    } 
  }
  
  @Nullable
  private JSONObject convertToJSONObject() {
    try {
      Tag tag = new Tag("", "xml");
      XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
      xmlPullParserFactory.setNamespaceAware(false);
      XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();
      setInput(xmlPullParser);
      for (int i = xmlPullParser.getEventType(); i != 0; i = xmlPullParser.next());
      readTags(tag, xmlPullParser);
      unsetInput();
      return convertTagToJson(tag, false);
    } catch (XmlPullParserException|java.io.IOException xmlPullParserException) {
      xmlPullParserException.printStackTrace();
      return null;
    } 
  }
  
  private void format(JSONObject paramJSONObject, StringBuilder paramStringBuilder, String paramString) {
    Iterator<String> iterator = paramJSONObject.keys();
    while (iterator.hasNext()) {
      String str = iterator.next();
      paramStringBuilder.append(paramString);
      paramStringBuilder.append(this.mIndentationPattern);
      paramStringBuilder.append("\"");
      paramStringBuilder.append(str);
      paramStringBuilder.append("\": ");
      Object object = paramJSONObject.opt(str);
      if (object instanceof JSONObject) {
        object = object;
        paramStringBuilder.append(paramString);
        paramStringBuilder.append("{\n");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(paramString);
        stringBuilder.append(this.mIndentationPattern);
        format((JSONObject)object, paramStringBuilder, stringBuilder.toString());
        paramStringBuilder.append(paramString);
        paramStringBuilder.append(this.mIndentationPattern);
        paramStringBuilder.append("}");
      } else if (object instanceof JSONArray) {
        object = object;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(paramString);
        stringBuilder.append(this.mIndentationPattern);
        formatArray((JSONArray)object, paramStringBuilder, stringBuilder.toString());
      } else {
        formatValue(object, paramStringBuilder);
      } 
      if (iterator.hasNext()) {
        paramStringBuilder.append(",\n");
        continue;
      } 
      paramStringBuilder.append("\n");
    } 
  }
  
  private void formatArray(JSONArray paramJSONArray, StringBuilder paramStringBuilder, String paramString) {
    paramStringBuilder.append("[\n");
    int i;
    for (i = 0; i < paramJSONArray.length(); i++) {
      Object object = paramJSONArray.opt(i);
      if (object instanceof JSONObject) {
        object = object;
        paramStringBuilder.append(paramString);
        paramStringBuilder.append(this.mIndentationPattern);
        paramStringBuilder.append("{\n");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(paramString);
        stringBuilder.append(this.mIndentationPattern);
        format((JSONObject)object, paramStringBuilder, stringBuilder.toString());
        paramStringBuilder.append(paramString);
        paramStringBuilder.append(this.mIndentationPattern);
        paramStringBuilder.append("}");
      } else if (object instanceof JSONArray) {
        object = object;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(paramString);
        stringBuilder.append(this.mIndentationPattern);
        formatArray((JSONArray)object, paramStringBuilder, stringBuilder.toString());
      } else {
        formatValue(object, paramStringBuilder);
      } 
      if (i < paramJSONArray.length() - 1)
        paramStringBuilder.append(","); 
      paramStringBuilder.append("\n");
    } 
    paramStringBuilder.append(paramString);
    paramStringBuilder.append("]");
  }
  
  private void formatValue(Object paramObject, StringBuilder paramStringBuilder) {
    if (paramObject instanceof String) {
      paramObject = ((String)paramObject).replaceAll("\\\\", "\\\\\\\\").replaceAll("\"", Matcher.quoteReplacement("\\\"")).replaceAll("/", "\\\\/").replaceAll("\n", "\\\\n").replaceAll("\t", "\\\\t").replaceAll("\r", "\\\\r");
      paramStringBuilder.append("\"");
      paramStringBuilder.append((String)paramObject);
      paramStringBuilder.append("\"");
      return;
    } 
    if (paramObject instanceof Long) {
      paramStringBuilder.append(paramObject);
      return;
    } 
    if (paramObject instanceof Integer) {
      paramStringBuilder.append(paramObject);
      return;
    } 
    if (paramObject instanceof Boolean) {
      paramStringBuilder.append(paramObject);
      return;
    } 
    if (paramObject instanceof Double) {
      paramStringBuilder.append(paramObject);
      return;
    } 
    paramStringBuilder.append(paramObject.toString());
  }
  
  private String getAttributeNameReplacement(String paramString1, String paramString2) {
    paramString1 = this.mAttributeNameReplacements.get(paramString1);
    return (paramString1 != null) ? paramString1 : paramString2;
  }
  
  private String getContentNameReplacement(String paramString1, String paramString2) {
    paramString1 = this.mContentNameReplacements.get(paramString1);
    return (paramString1 != null) ? paramString1 : paramString2;
  }
  
  private boolean isForcedList(Tag paramTag) {
    String str = paramTag.getPath();
    return this.mForceListPaths.contains(str);
  }
  
  private void putContent(String paramString1, JSONObject paramJSONObject, String paramString2, String paramString3) {
    try {
      String str;
      Class clazz = this.mForceClassForPath.get(paramString1);
      if (clazz == null) {
        str = paramString3;
        if (paramString3 == null)
          str = ""; 
        paramJSONObject.put(paramString2, str);
        return;
      } 
      if (str == Integer.class)
        try {
          paramJSONObject.put(paramString2, Integer.valueOf(Integer.parseInt(paramString3)));
          return;
        } catch (NumberFormatException null) {
          paramJSONObject.put(paramString2, 0);
          return;
        }  
      if (numberFormatException == Long.class)
        try {
          paramJSONObject.put(paramString2, Long.valueOf(Long.parseLong(paramString3)));
          return;
        } catch (NumberFormatException null) {
          paramJSONObject.put(paramString2, 0L);
          return;
        }  
      if (numberFormatException == Double.class)
        try {
          paramJSONObject.put(paramString2, Double.valueOf(Double.parseDouble(paramString3)));
          return;
        } catch (NumberFormatException numberFormatException) {
          paramJSONObject.put(paramString2, 0.0D);
          return;
        }  
      if (numberFormatException == Boolean.class) {
        if (paramString3 == null) {
          paramJSONObject.put(paramString2, false);
          return;
        } 
        if (paramString3.equalsIgnoreCase("true")) {
          paramJSONObject.put(paramString2, true);
          return;
        } 
        if (paramString3.equalsIgnoreCase("false")) {
          paramJSONObject.put(paramString2, false);
          return;
        } 
        paramJSONObject.put(paramString2, false);
      } 
      return;
    } catch (JSONException jSONException) {
      return;
    } 
  }
  
  private void readTags(Tag paramTag, XmlPullParser paramXmlPullParser) {
    label32: while (true) {
      int i;
      try {
        i = paramXmlPullParser.next();
        if (i == 2) {
          String str1 = paramXmlPullParser.getName();
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append(paramTag.getPath());
          stringBuilder1.append("/");
          stringBuilder1.append(str1);
          String str2 = stringBuilder1.toString();
          boolean bool = this.mSkippedTags.contains(str2);
          Tag tag = new Tag(str2, str1);
          if (!bool)
            paramTag.addChild(tag); 
          int k = paramXmlPullParser.getAttributeCount();
          for (int j = 0;; j++) {
            if (j < k) {
              String str3 = paramXmlPullParser.getAttributeName(j);
              str2 = paramXmlPullParser.getAttributeValue(j);
              StringBuilder stringBuilder2 = new StringBuilder();
              stringBuilder2.append(paramTag.getPath());
              stringBuilder2.append("/");
              stringBuilder2.append(tag.getName());
              stringBuilder2.append("/");
              stringBuilder2.append(str3);
              String str4 = stringBuilder2.toString();
              if (!this.mSkippedAttributes.contains(str4)) {
                Tag tag1 = new Tag(str4, getAttributeNameReplacement(str4, str3));
                tag1.setContent(str2);
                tag.addChild(tag1);
              } 
            } else {
              readTags(tag, paramXmlPullParser);
              if (i == 1)
                return; 
              continue label32;
            } 
          } 
        } 
        if (i == 4) {
          paramTag.setContent(paramXmlPullParser.getText());
          continue;
        } 
      } catch (XmlPullParserException|java.io.IOException|NullPointerException xmlPullParserException) {
        xmlPullParserException.printStackTrace();
        return;
      } 
      if (i == 3)
        return; 
      if (i == 1)
        break; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("unknown xml eventType ");
      stringBuilder.append(i);
      Log.i("XmlToJson", stringBuilder.toString());
      continue;
    } 
  }
  
  private void setInput(XmlPullParser paramXmlPullParser) {
    if (this.mStringSource != null)
      try {
        paramXmlPullParser.setInput(this.mStringSource);
        return;
      } catch (XmlPullParserException xmlPullParserException) {
        xmlPullParserException.printStackTrace();
        return;
      }  
    try {
      xmlPullParserException.setInput(this.mInputStreamSource, this.mInputEncoding);
      return;
    } catch (XmlPullParserException xmlPullParserException1) {
      xmlPullParserException1.printStackTrace();
      return;
    } 
  }
  
  private void unsetInput() {
    if (this.mStringSource != null)
      this.mStringSource.close(); 
  }
  
  public String toFormattedString() {
    if (this.mJsonObject != null) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("{\n");
      format(this.mJsonObject, stringBuilder, "");
      stringBuilder.append("}\n");
      return stringBuilder.toString();
    } 
    return null;
  }
  
  public String toFormattedString(@Nullable String paramString) {
    if (paramString == null) {
      this.mIndentationPattern = "   ";
    } else {
      this.mIndentationPattern = paramString;
    } 
    return toFormattedString();
  }
  
  @Nullable
  public JSONObject toJson() {
    return this.mJsonObject;
  }
  
  public String toString() {
    return (this.mJsonObject != null) ? this.mJsonObject.toString() : null;
  }
  
  public static class Builder {
    private HashMap<String, String> mAttributeNameReplacements = new HashMap<String, String>();
    
    private HashMap<String, String> mContentNameReplacements = new HashMap<String, String>();
    
    private HashMap<String, Class> mForceClassForPath = new HashMap<String, Class<?>>();
    
    private HashSet<String> mForceListPaths = new HashSet<String>();
    
    private String mInputEncoding = "utf-8";
    
    private InputStream mInputStreamSource;
    
    private HashSet<String> mSkippedAttributes = new HashSet<String>();
    
    private HashSet<String> mSkippedTags = new HashSet<String>();
    
    private StringReader mStringSource;
    
    public Builder(@NonNull InputStream param1InputStream, @Nullable String param1String) {
      this.mInputStreamSource = param1InputStream;
      if (param1String == null)
        param1String = "utf-8"; 
      this.mInputEncoding = param1String;
    }
    
    public Builder(@NonNull String param1String) {
      this.mStringSource = new StringReader(param1String);
    }
    
    public XmlToJson build() {
      return new XmlToJson(this);
    }
    
    public Builder forceBooleanForPath(@NonNull String param1String) {
      this.mForceClassForPath.put(param1String, Boolean.class);
      return this;
    }
    
    public Builder forceDoubleForPath(@NonNull String param1String) {
      this.mForceClassForPath.put(param1String, Double.class);
      return this;
    }
    
    public Builder forceIntegerForPath(@NonNull String param1String) {
      this.mForceClassForPath.put(param1String, Integer.class);
      return this;
    }
    
    public Builder forceList(@NonNull String param1String) {
      this.mForceListPaths.add(param1String);
      return this;
    }
    
    public Builder forceLongForPath(@NonNull String param1String) {
      this.mForceClassForPath.put(param1String, Long.class);
      return this;
    }
    
    public Builder setAttributeName(@NonNull String param1String1, @NonNull String param1String2) {
      this.mAttributeNameReplacements.put(param1String1, param1String2);
      return this;
    }
    
    public Builder setContentName(@NonNull String param1String1, @NonNull String param1String2) {
      this.mContentNameReplacements.put(param1String1, param1String2);
      return this;
    }
    
    public Builder skipAttribute(@NonNull String param1String) {
      this.mSkippedAttributes.add(param1String);
      return this;
    }
    
    public Builder skipTag(@NonNull String param1String) {
      this.mSkippedTags.add(param1String);
      return this;
    }
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\fr\arnaudguyon\xmltojsonlib\XmlToJson.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */