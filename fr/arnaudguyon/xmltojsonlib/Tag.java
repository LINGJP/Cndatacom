package fr.arnaudguyon.xmltojsonlib;

import java.util.ArrayList;
import java.util.HashMap;

public class Tag {
  private ArrayList<Tag> mChildren = new ArrayList<Tag>();
  
  private String mContent;
  
  private String mName;
  
  private String mPath;
  
  Tag(String paramString1, String paramString2) {
    this.mPath = paramString1;
    this.mName = paramString2;
  }
  
  void addChild(Tag paramTag) {
    this.mChildren.add(paramTag);
  }
  
  Tag getChild(int paramInt) {
    return (paramInt >= 0 && paramInt < this.mChildren.size()) ? this.mChildren.get(paramInt) : null;
  }
  
  ArrayList<Tag> getChildren() {
    return this.mChildren;
  }
  
  int getChildrenCount() {
    return this.mChildren.size();
  }
  
  String getContent() {
    return this.mContent;
  }
  
  HashMap<String, ArrayList<Tag>> getGroupedElements() {
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    for (Tag tag : this.mChildren) {
      String str = tag.getName();
      ArrayList<Tag> arrayList2 = (ArrayList)hashMap.get(str);
      ArrayList<Tag> arrayList1 = arrayList2;
      if (arrayList2 == null) {
        arrayList1 = new ArrayList();
        hashMap.put(str, arrayList1);
      } 
      arrayList1.add(tag);
    } 
    return (HashMap)hashMap;
  }
  
  String getName() {
    return this.mName;
  }
  
  String getPath() {
    return this.mPath;
  }
  
  boolean hasChildren() {
    return (this.mChildren.size() > 0);
  }
  
  void setContent(String paramString) {
    char c2 = Character.MIN_VALUE;
    char c1 = c2;
    if (paramString != null) {
      int i = 0;
      while (true) {
        c1 = c2;
        if (i < paramString.length()) {
          c1 = paramString.charAt(i);
          if (c1 != ' ' && c1 != '\n') {
            c1 = '\001';
            break;
          } 
          i++;
          continue;
        } 
        break;
      } 
    } 
    if (c1 != '\000')
      this.mContent = paramString; 
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Tag: ");
    stringBuilder.append(this.mName);
    stringBuilder.append(", ");
    stringBuilder.append(this.mChildren.size());
    stringBuilder.append(" children, Content: ");
    stringBuilder.append(this.mContent);
    return stringBuilder.toString();
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\fr\arnaudguyon\xmltojsonlib\Tag.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */