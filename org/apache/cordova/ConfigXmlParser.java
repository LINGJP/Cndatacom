package org.apache.cordova;

import android.content.Context;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Pattern;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class ConfigXmlParser {
  private static String TAG = "ConfigXmlParser";
  
  boolean insideFeature = false;
  
  private String launchUrl = "file:///android_asset/www/index.html";
  
  boolean onload = false;
  
  String paramType = "";
  
  String pluginClass = "";
  
  private ArrayList<PluginEntry> pluginEntries = new ArrayList<PluginEntry>(20);
  
  private CordovaPreferences prefs = new CordovaPreferences();
  
  String service = "";
  
  private void setStartUrl(String paramString) {
    if (Pattern.compile("^[a-z-]+://").matcher(paramString).find()) {
      this.launchUrl = paramString;
      return;
    } 
    String str = paramString;
    if (paramString.charAt(0) == '/')
      str = paramString.substring(1); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("file:///android_asset/www/");
    stringBuilder.append(str);
    this.launchUrl = stringBuilder.toString();
  }
  
  public String getLaunchUrl() {
    return this.launchUrl;
  }
  
  public ArrayList<PluginEntry> getPluginEntries() {
    return this.pluginEntries;
  }
  
  public CordovaPreferences getPreferences() {
    return this.prefs;
  }
  
  public void handleEndTag(XmlPullParser paramXmlPullParser) {
    if (paramXmlPullParser.getName().equals("feature")) {
      this.pluginEntries.add(new PluginEntry(this.service, this.pluginClass, this.onload));
      this.service = "";
      this.pluginClass = "";
      this.insideFeature = false;
      this.onload = false;
    } 
  }
  
  public void handleStartTag(XmlPullParser paramXmlPullParser) {
    String str = paramXmlPullParser.getName();
    if (str.equals("feature")) {
      this.insideFeature = true;
      this.service = paramXmlPullParser.getAttributeValue(null, "name");
      return;
    } 
    if (this.insideFeature && str.equals("param")) {
      this.paramType = paramXmlPullParser.getAttributeValue(null, "name");
      if (this.paramType.equals("service")) {
        this.service = paramXmlPullParser.getAttributeValue(null, "value");
        return;
      } 
      if (this.paramType.equals("package") || this.paramType.equals("android-package")) {
        this.pluginClass = paramXmlPullParser.getAttributeValue(null, "value");
        return;
      } 
      if (this.paramType.equals("onload")) {
        this.onload = "true".equals(paramXmlPullParser.getAttributeValue(null, "value"));
        return;
      } 
    } else {
      String str1;
      if (str.equals("preference")) {
        str = paramXmlPullParser.getAttributeValue(null, "name").toLowerCase(Locale.ENGLISH);
        str1 = paramXmlPullParser.getAttributeValue(null, "value");
        this.prefs.set(str, str1);
        return;
      } 
      if (str.equals("content")) {
        str1 = str1.getAttributeValue(null, "src");
        if (str1 != null)
          setStartUrl(str1); 
      } 
    } 
  }
  
  public void parse(Context paramContext) {
    int j = paramContext.getResources().getIdentifier("config", "xml", paramContext.getClass().getPackage().getName());
    int i = j;
    if (j == 0) {
      j = paramContext.getResources().getIdentifier("config", "xml", paramContext.getPackageName());
      i = j;
      if (j == 0) {
        LOG.e(TAG, "res/xml/config.xml is missing!");
        return;
      } 
    } 
    parse((XmlPullParser)paramContext.getResources().getXml(i));
  }
  
  public void parse(XmlPullParser paramXmlPullParser) {
    int i = -1;
    while (i != 1) {
      if (i == 2) {
        handleStartTag(paramXmlPullParser);
      } else if (i == 3) {
        handleEndTag(paramXmlPullParser);
      } 
      try {
        int j = paramXmlPullParser.next();
        i = j;
      } catch (XmlPullParserException xmlPullParserException) {
        xmlPullParserException.printStackTrace();
      } catch (IOException iOException) {
        iOException.printStackTrace();
      } 
    } 
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\org\apache\cordova\ConfigXmlParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */