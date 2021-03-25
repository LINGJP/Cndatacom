package com.cndatacom.cnportalclient.constant;

public class NetCheckCode {
  public class MessageCode {
    public static final int CHECK_ITEM_AUTHSERVER = 1004;
    
    public static final int CHECK_ITEM_MANAGER = 1005;
    
    public static final int CHECK_ITEM_PORTAL = 1002;
    
    public static final int CHECK_ITEM_ROUTE = 1003;
    
    public static final int CHECK_ITEM_WIFI = 1001;
    
    public MessageCode(NetCheckCode this$0) {}
  }
  
  public class NetCheckItem {
    public static final int CHECK_ITEM_AUTHSERVER = 4;
    
    public static final int CHECK_ITEM_MANAGER = 5;
    
    public static final int CHECK_ITEM_PORTAL = 2;
    
    public static final int CHECK_ITEM_ROUTE = 3;
    
    public static final int CHECK_ITEM_WIFI = 1;
    
    public NetCheckItem(NetCheckCode this$0) {}
  }
  
  public class NetCheckType {
    public static final String NET_CHECK_BEGIN = "1";
    
    public static final String NET_CHECK_RESTART = "3";
    
    public static final String NET_CHECK_STOP = "2";
    
    public NetCheckType(NetCheckCode this$0) {}
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\constant\NetCheckCode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */