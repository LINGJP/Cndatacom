package fr.arnaudguyon.xmltojsonlib;

import java.util.ArrayList;

class Node {
  private ArrayList<Attribute> mAttributes = new ArrayList<Attribute>();
  
  private ArrayList<Node> mChildren = new ArrayList<Node>();
  
  private String mContent;
  
  private String mName;
  
  private String mPath;
  
  Node(String paramString1, String paramString2) {
    this.mName = paramString1;
    this.mPath = paramString2;
  }
  
  void addAttribute(String paramString1, String paramString2) {
    this.mAttributes.add(new Attribute(paramString1, paramString2));
  }
  
  void addChild(Node paramNode) {
    this.mChildren.add(paramNode);
  }
  
  ArrayList<Attribute> getAttributes() {
    return this.mAttributes;
  }
  
  ArrayList<Node> getChildren() {
    return this.mChildren;
  }
  
  String getContent() {
    return this.mContent;
  }
  
  String getName() {
    return this.mName;
  }
  
  String getPath() {
    return this.mPath;
  }
  
  void setContent(String paramString) {
    this.mContent = paramString;
  }
  
  void setName(String paramString) {
    this.mName = paramString;
  }
  
  class Attribute {
    String mKey;
    
    String mValue;
    
    Attribute(String param1String1, String param1String2) {
      this.mKey = param1String1;
      this.mValue = param1String2;
    }
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\fr\arnaudguyon\xmltojsonlib\Node.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */