package com.cndatacom.cnportalclient.constant;

import com.cndatacom.cnportalclient.utils.ExLog;

public class FromWhereCode {
  public static final int CHECKNET_GOON = 1;
  
  public static final int CHECKNET_NONE = 0;
  
  public static final int CHECKNET_TERM = 2;
  
  public static void printFromWhereDes(int paramInt, String paramString) {
    switch (paramInt) {
      default:
        stringBuilder = new StringBuilder();
        stringBuilder.append("网络环境检测，调用");
        stringBuilder.append(paramString);
        ExLog.d(new String[] { stringBuilder.toString() });
        return;
      case 2:
        stringBuilder = new StringBuilder();
        stringBuilder.append("网络环境检测，下线调用");
        stringBuilder.append(paramString);
        ExLog.d(new String[] { stringBuilder.toString() });
        return;
      case 1:
        break;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("网络环境检测，忽略Wifi信号强弱调用");
    stringBuilder.append(paramString);
    ExLog.d(new String[] { stringBuilder.toString() });
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\constant\FromWhereCode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */