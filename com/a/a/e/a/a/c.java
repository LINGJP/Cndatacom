package com.a.a.e.a.a;

import java.util.ArrayList;
import java.util.List;

final class c {
  private final List<b> a;
  
  private final int b;
  
  private final boolean c;
  
  c(List<b> paramList, int paramInt, boolean paramBoolean) {
    this.a = new ArrayList<b>(paramList);
    this.b = paramInt;
    this.c = paramBoolean;
  }
  
  List<b> a() {
    return this.a;
  }
  
  boolean a(List<b> paramList) {
    return this.a.equals(paramList);
  }
  
  int b() {
    return this.b;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = paramObject instanceof c;
    boolean bool1 = false;
    if (!bool)
      return false; 
    paramObject = paramObject;
    bool = bool1;
    if (this.a.equals(paramObject.a())) {
      bool = bool1;
      if (this.c == ((c)paramObject).c)
        bool = true; 
    } 
    return bool;
  }
  
  public int hashCode() {
    return this.a.hashCode() ^ Boolean.valueOf(this.c).hashCode();
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{ ");
    stringBuilder.append(this.a);
    stringBuilder.append(" }");
    return stringBuilder.toString();
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\a\a\e\a\a\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */