package com.cndatacom.cnportalclient.handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

public class WeakRefHandler<T> extends Handler {
  private WeakReference<T> a;
  
  private Handler.Callback b;
  
  public WeakRefHandler(Looper paramLooper, T paramT, Handler.Callback paramCallback) {
    super(paramLooper);
    this.b = paramCallback;
    this.a = new WeakReference<T>(paramT);
  }
  
  public T getT() {
    return this.a.get();
  }
  
  public void handleMessage(Message paramMessage) {
    if (isAlive() && this.b != null)
      this.b.handleMessage(paramMessage); 
  }
  
  public boolean isAlive() {
    return (this.a.get() != null);
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\handler\WeakRefHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */