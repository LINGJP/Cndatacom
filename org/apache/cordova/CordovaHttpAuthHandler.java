package org.apache.cordova;

import android.webkit.HttpAuthHandler;

public class CordovaHttpAuthHandler implements ICordovaHttpAuthHandler {
  private final HttpAuthHandler handler;
  
  public CordovaHttpAuthHandler(HttpAuthHandler paramHttpAuthHandler) {
    this.handler = paramHttpAuthHandler;
  }
  
  public void cancel() {
    this.handler.cancel();
  }
  
  public void proceed(String paramString1, String paramString2) {
    this.handler.proceed(paramString1, paramString2);
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\org\apache\cordova\CordovaHttpAuthHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */