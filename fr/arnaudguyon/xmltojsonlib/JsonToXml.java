package fr.arnaudguyon.xmltojsonlib;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.util.Xml;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlSerializer;

public class JsonToXml {
  private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
  
  private static final int DEFAULT_INDENTATION = 3;
  
  private HashSet<String> mForcedAttributes;
  
  private HashSet<String> mForcedContent;
  
  private JSONObject mJson;
  
  private JsonToXml(@NonNull JSONObject paramJSONObject, @NonNull HashSet<String> paramHashSet1, HashSet<String> paramHashSet2) {
    this.mJson = paramJSONObject;
    this.mForcedAttributes = paramHashSet1;
    this.mForcedContent = paramHashSet2;
  }
  
  private boolean isAttribute(String paramString) {
    return this.mForcedAttributes.contains(paramString);
  }
  
  private boolean isContent(String paramString) {
    return this.mForcedContent.contains(paramString);
  }
  
  private String nodeToXML(Node paramNode) {
    XmlSerializer xmlSerializer = Xml.newSerializer();
    StringWriter stringWriter = new StringWriter();
    try {
      xmlSerializer.setOutput(stringWriter);
      xmlSerializer.startDocument("UTF-8", Boolean.valueOf(true));
      nodeToXml(xmlSerializer, paramNode);
      xmlSerializer.endDocument();
      return stringWriter.toString();
    } catch (IOException iOException) {
      throw new RuntimeException(iOException);
    } 
  }
  
  private void nodeToXml(XmlSerializer paramXmlSerializer, Node paramNode) {
    String str = paramNode.getName();
    if (str != null) {
      paramXmlSerializer.startTag("", str);
      for (Node.Attribute attribute : paramNode.getAttributes())
        paramXmlSerializer.attribute("", attribute.mKey, attribute.mValue); 
      String str1 = paramNode.getContent();
      if (str1 != null)
        paramXmlSerializer.text(str1); 
    } 
    Iterator<Node> iterator = paramNode.getChildren().iterator();
    while (iterator.hasNext())
      nodeToXml(paramXmlSerializer, iterator.next()); 
    if (str != null)
      paramXmlSerializer.endTag("", str); 
  }
  
  private void prepareArray(Node paramNode, String paramString, JSONArray paramJSONArray) {
    int j = paramJSONArray.length();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramNode.getPath());
    stringBuilder.append("/");
    stringBuilder.append(paramString);
    String str = stringBuilder.toString();
    int i;
    for (i = 0; i < j; i++) {
      Node node = new Node(paramString, str);
      Object object = paramJSONArray.opt(i);
      if (object != null)
        if (object instanceof JSONObject) {
          prepareObject(node, (JSONObject)object);
        } else if (object instanceof JSONArray) {
          prepareArray(node, paramString, (JSONArray)object);
        } else {
          object = object.toString();
          node.setName(paramString);
          node.setContent((String)object);
        }  
      paramNode.addChild(node);
    } 
  }
  
  private void prepareObject(Node paramNode, JSONObject paramJSONObject) {
    Iterator<String> iterator = paramJSONObject.keys();
    while (iterator.hasNext()) {
      String str = iterator.next();
      Object object = paramJSONObject.opt(str);
      if (object != null) {
        if (object instanceof JSONObject) {
          object = object;
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append(paramNode.getPath());
          stringBuilder1.append("/");
          stringBuilder1.append(str);
          node = new Node(str, stringBuilder1.toString());
          paramNode.addChild(node);
          prepareObject(node, (JSONObject)object);
          continue;
        } 
        if (object instanceof JSONArray) {
          prepareArray(paramNode, (String)node, (JSONArray)object);
          continue;
        } 
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(paramNode.getPath());
        stringBuilder.append("/");
        stringBuilder.append((String)node);
        String str1 = stringBuilder.toString();
        if (object instanceof Double) {
          double d = ((Double)object).doubleValue();
          if (d % 1.0D == 0.0D) {
            object = Long.toString((long)d);
          } else {
            if (DECIMAL_FORMAT.getMaximumFractionDigits() == 0)
              DECIMAL_FORMAT.setMaximumFractionDigits(20); 
            object = DECIMAL_FORMAT.format(d);
          } 
        } else {
          object = object.toString();
        } 
        if (isAttribute(str1)) {
          paramNode.addAttribute((String)node, (String)object);
          continue;
        } 
        if (isContent(str1)) {
          paramNode.setContent((String)object);
          continue;
        } 
        Node node = new Node((String)node, paramNode.getPath());
        node.setContent((String)object);
        paramNode.addChild(node);
      } 
    } 
  }
  
  public String toFormattedString() {
    return toFormattedString(3);
  }
  
  public String toFormattedString(@IntRange(from = 0L) int paramInt) {
    String str = toString();
    try {
      StreamSource streamSource = new StreamSource(new StringReader(str));
      StreamResult streamResult = new StreamResult(new StringWriter());
      Transformer transformer = TransformerFactory.newInstance().newTransformer();
      transformer.setOutputProperty("indent", "yes");
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("");
      stringBuilder.append(paramInt);
      transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", stringBuilder.toString());
      transformer.transform(streamSource, streamResult);
      return streamResult.getWriter().toString();
    } catch (Exception exception) {
      throw new RuntimeException(exception);
    } 
  }
  
  public String toString() {
    Node node = new Node(null, "");
    prepareObject(node, this.mJson);
    return nodeToXML(node);
  }
  
  public static class Builder {
    private HashSet<String> mForcedAttributes = new HashSet<String>();
    
    private HashSet<String> mForcedContent = new HashSet<String>();
    
    private JSONObject mJson;
    
    public Builder(@NonNull InputStream param1InputStream) {
      this(FileReader.readFileFromInputStream(param1InputStream));
    }
    
    public Builder(String param1String) {
      try {
        this.mJson = new JSONObject(param1String);
        return;
      } catch (JSONException jSONException) {
        jSONException.printStackTrace();
        return;
      } 
    }
    
    public Builder(@NonNull JSONObject param1JSONObject) {
      this.mJson = param1JSONObject;
    }
    
    public JsonToXml build() {
      return new JsonToXml(this.mJson, this.mForcedAttributes, this.mForcedContent);
    }
    
    public Builder forceAttribute(String param1String) {
      this.mForcedAttributes.add(param1String);
      return this;
    }
    
    public Builder forceContent(String param1String) {
      this.mForcedContent.add(param1String);
      return this;
    }
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\fr\arnaudguyon\xmltojsonlib\JsonToXml.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */