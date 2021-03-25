package com.cndatacom.cnportalclient.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
  public static String parse2Date(long paramLong) {
    return parse2YYYYMMDDHHMMSS(new Date(paramLong));
  }
  
  public static Date parse2Date(String paramString) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    try {
      Date date2 = simpleDateFormat.parse(paramString);
      Date date1 = date2;
      if (date2 == null)
        return new Date(); 
    } catch (Exception exception) {
      exception.printStackTrace();
      Date date = new Date();
    } finally {}
    return (Date)paramString;
  }
  
  public static String parse2YYYYMMDD(Date paramDate) {
    Date date = paramDate;
    if (paramDate == null)
      date = new Date(); 
    return (new SimpleDateFormat("yyyy-MM-dd")).format(date);
  }
  
  public static String parse2YYYYMMDDHHMMSS(Date paramDate) {
    Date date = paramDate;
    if (paramDate == null)
      date = new Date(); 
    return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(date);
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclien\\utils\DateUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */