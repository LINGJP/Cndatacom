package com.cndatacom.cnportalclient.download.utils;

import java.net.URLConnection;

public class HttpUtil {
  public static void seURLConnectiontHeader(URLConnection paramURLConnection) {
    paramURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.3) Gecko/2008092510 Ubuntu/8.04 (hardy) Firefox/3.0.3");
    paramURLConnection.setRequestProperty("Accept-Language", "en-us,en;q=0.7,zh-cn;q=0.3");
    paramURLConnection.setRequestProperty("Accept-Encoding", "utf-8");
    paramURLConnection.setRequestProperty("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.7");
    paramURLConnection.setRequestProperty("Keep-Alive", "300");
    paramURLConnection.setRequestProperty("connnection", "keep-alive");
    paramURLConnection.setRequestProperty("If-Modified-Since", "Fri, 02 Jan 2009 17:00:05 GMT");
    paramURLConnection.setRequestProperty("If-None-Match", "\"1261d8-4290-df64d224\"");
    paramURLConnection.setRequestProperty("Cache-conntrol", "max-age=0");
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\downloa\\utils\HttpUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */