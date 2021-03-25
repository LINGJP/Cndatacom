package org.apache.cordova.whitelist;

import android.content.Context;
import org.apache.cordova.ConfigXmlParser;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.LOG;
import org.apache.cordova.Whitelist;
import org.xmlpull.v1.XmlPullParser;

public class WhitelistPlugin extends CordovaPlugin {
  private static final String LOG_TAG = "WhitelistPlugin";
  
  private Whitelist allowedIntents;
  
  private Whitelist allowedNavigations;
  
  private Whitelist allowedRequests;
  
  public WhitelistPlugin() {}
  
  public WhitelistPlugin(Context paramContext) {
    this(new Whitelist(), new Whitelist(), null);
    (new CustomConfigXmlParser()).parse(paramContext);
  }
  
  public WhitelistPlugin(Whitelist paramWhitelist1, Whitelist paramWhitelist2, Whitelist paramWhitelist3) {
    Whitelist whitelist = paramWhitelist3;
    if (paramWhitelist3 == null) {
      whitelist = new Whitelist();
      whitelist.addWhiteListEntry("file:///*", false);
      whitelist.addWhiteListEntry("data:*", false);
    } 
    this.allowedNavigations = paramWhitelist1;
    this.allowedIntents = paramWhitelist2;
    this.allowedRequests = whitelist;
  }
  
  public WhitelistPlugin(XmlPullParser paramXmlPullParser) {
    this(new Whitelist(), new Whitelist(), null);
    (new CustomConfigXmlParser()).parse(paramXmlPullParser);
  }
  
  public Whitelist getAllowedIntents() {
    return this.allowedIntents;
  }
  
  public Whitelist getAllowedNavigations() {
    return this.allowedNavigations;
  }
  
  public Whitelist getAllowedRequests() {
    return this.allowedRequests;
  }
  
  public void pluginInitialize() {
    if (this.allowedNavigations == null) {
      this.allowedNavigations = new Whitelist();
      this.allowedIntents = new Whitelist();
      this.allowedRequests = new Whitelist();
      (new CustomConfigXmlParser()).parse(this.webView.getContext());
    } 
  }
  
  public void setAllowedIntents(Whitelist paramWhitelist) {
    this.allowedIntents = paramWhitelist;
  }
  
  public void setAllowedNavigations(Whitelist paramWhitelist) {
    this.allowedNavigations = paramWhitelist;
  }
  
  public void setAllowedRequests(Whitelist paramWhitelist) {
    this.allowedRequests = paramWhitelist;
  }
  
  public Boolean shouldAllowNavigation(String paramString) {
    return this.allowedNavigations.isUrlWhiteListed(paramString) ? Boolean.valueOf(true) : null;
  }
  
  public Boolean shouldAllowRequest(String paramString) {
    return (Boolean.TRUE == shouldAllowNavigation(paramString)) ? Boolean.valueOf(true) : (this.allowedRequests.isUrlWhiteListed(paramString) ? Boolean.valueOf(true) : null);
  }
  
  public Boolean shouldOpenExternalUrl(String paramString) {
    return this.allowedIntents.isUrlWhiteListed(paramString) ? Boolean.valueOf(true) : null;
  }
  
  private class CustomConfigXmlParser extends ConfigXmlParser {
    private CustomConfigXmlParser() {}
    
    public void handleEndTag(XmlPullParser param1XmlPullParser) {}
    
    public void handleStartTag(XmlPullParser param1XmlPullParser) {
      String str1;
      String str2 = param1XmlPullParser.getName();
      if (str2.equals("content")) {
        str1 = param1XmlPullParser.getAttributeValue(null, "src");
        WhitelistPlugin.this.allowedNavigations.addWhiteListEntry(str1, false);
        return;
      } 
      if (str2.equals("allow-navigation")) {
        str1 = str1.getAttributeValue(null, "href");
        if ("*".equals(str1)) {
          WhitelistPlugin.this.allowedNavigations.addWhiteListEntry("http://*/*", false);
          WhitelistPlugin.this.allowedNavigations.addWhiteListEntry("https://*/*", false);
          WhitelistPlugin.this.allowedNavigations.addWhiteListEntry("data:*", false);
          return;
        } 
        WhitelistPlugin.this.allowedNavigations.addWhiteListEntry(str1, false);
        return;
      } 
      if (str2.equals("allow-intent")) {
        str1 = str1.getAttributeValue(null, "href");
        WhitelistPlugin.this.allowedIntents.addWhiteListEntry(str1, false);
        return;
      } 
      if (str2.equals("access")) {
        boolean bool1;
        str2 = str1.getAttributeValue(null, "origin");
        String str = str1.getAttributeValue(null, "subdomains");
        str1 = str1.getAttributeValue(null, "launch-external");
        boolean bool3 = true;
        boolean bool2 = true;
        if (str1 != null) {
          bool1 = true;
        } else {
          bool1 = false;
        } 
        if (str2 != null) {
          if (bool1) {
            LOG.w("WhitelistPlugin", "Found <access launch-external> within config.xml. Please use <allow-intent> instead.");
            Whitelist whitelist1 = WhitelistPlugin.this.allowedIntents;
            if (str == null || str.compareToIgnoreCase("true") != 0)
              bool2 = false; 
            whitelist1.addWhiteListEntry(str2, bool2);
            return;
          } 
          if ("*".equals(str2)) {
            WhitelistPlugin.this.allowedRequests.addWhiteListEntry("http://*/*", false);
            WhitelistPlugin.this.allowedRequests.addWhiteListEntry("https://*/*", false);
            return;
          } 
          Whitelist whitelist = WhitelistPlugin.this.allowedRequests;
          if (str != null && str.compareToIgnoreCase("true") == 0) {
            bool2 = bool3;
          } else {
            bool2 = false;
          } 
          whitelist.addWhiteListEntry(str2, bool2);
        } 
      } 
    }
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\org\apache\cordova\whitelist\WhitelistPlugin.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */