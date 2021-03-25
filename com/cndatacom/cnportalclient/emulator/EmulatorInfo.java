package com.cndatacom.cnportalclient.emulator;

import java.util.List;

public class EmulatorInfo {
  private int a;
  
  private List<CheckItem> b;
  
  private List<ExecItem> c;
  
  private List<SureItem> d;
  
  public List<CheckItem> getCheckItems() {
    return this.b;
  }
  
  public int getCount() {
    return this.a;
  }
  
  public List<ExecItem> getExecItems() {
    return this.c;
  }
  
  public List<SureItem> getSureItems() {
    return this.d;
  }
  
  public void setCheckItems(List<CheckItem> paramList) {
    this.b = paramList;
  }
  
  public void setCount(int paramInt) {
    this.a = paramInt;
  }
  
  public void setExecItems(List<ExecItem> paramList) {
    this.c = paramList;
  }
  
  public void setSureItems(List<SureItem> paramList) {
    this.d = paramList;
  }
  
  public static class CheckItem {
    private String a = "";
    
    private String b = "";
    
    private List<EmulatorInfo.Filter> c;
    
    private String d = "";
    
    public String getContains() {
      return this.b;
    }
    
    public String getEqualsItemName() {
      return this.d;
    }
    
    public List<EmulatorInfo.Filter> getFilters() {
      return this.c;
    }
    
    public String getName() {
      return this.a;
    }
    
    public void setContains(String param1String) {
      this.b = param1String;
    }
    
    public void setEqualsItemName(String param1String) {
      this.d = param1String;
    }
    
    public void setFilters(List<EmulatorInfo.Filter> param1List) {
      this.c = param1List;
    }
    
    public void setName(String param1String) {
      this.a = param1String;
    }
  }
  
  public static class ExecItem {
    private String a = "";
    
    private List<EmulatorInfo.Filter> b;
    
    public List<EmulatorInfo.Filter> getFilters() {
      return this.b;
    }
    
    public String getName() {
      return this.a;
    }
    
    public void setFilters(List<EmulatorInfo.Filter> param1List) {
      this.b = param1List;
    }
    
    public void setName(String param1String) {
      this.a = param1String;
    }
  }
  
  public static class Filter {
    private String a = "";
    
    private int b;
    
    public String getModel() {
      return this.a;
    }
    
    public int getVersion() {
      return this.b;
    }
    
    public void setModel(String param1String) {
      this.a = param1String;
    }
    
    public void setVersion(int param1Int) {
      this.b = param1Int;
    }
  }
  
  public static class SureItem {
    private String a = "";
    
    private String b = "";
    
    private List<EmulatorInfo.Filter> c;
    
    public String getContains() {
      return this.b;
    }
    
    public List<EmulatorInfo.Filter> getFilters() {
      return this.c;
    }
    
    public String getName() {
      return this.a;
    }
    
    public void setContains(String param1String) {
      this.b = param1String;
    }
    
    public void setFilters(List<EmulatorInfo.Filter> param1List) {
      this.c = param1List;
    }
    
    public void setName(String param1String) {
      this.a = param1String;
    }
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\emulator\EmulatorInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */