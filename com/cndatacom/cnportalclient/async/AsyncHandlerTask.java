package com.cndatacom.cnportalclient.async;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.cndatacom.cnportalclient.handler.WeakRefHandler;

public class AsyncHandlerTask {
  private WeakRefHandler a;
  
  private WeakRefHandler b;
  
  private HandlerThread c;
  
  public AsyncHandlerTask(WeakRefHandler paramWeakRefHandler) {
    this.a = paramWeakRefHandler;
    this.c = new HandlerThread("loadAsyncHandlerTaskData", 10);
    this.c.start();
    this.b = new WeakRefHandler(this.c.getLooper(), this.a.getT(), new Handler.Callback(this) {
          public boolean handleMessage(Message param1Message) {
            return true;
          }
        });
  }
  
  public AsyncHandlerTask(WeakRefHandler paramWeakRefHandler1, WeakRefHandler paramWeakRefHandler2) {
    this.a = paramWeakRefHandler1;
    this.b = paramWeakRefHandler2;
  }
  
  private void a() {
    if (this.c != null) {
      if (this.b != null)
        this.b.removeCallbacksAndMessages(null); 
      this.c.quit();
    } 
  }
  
  private void a(WeakRefHandler paramWeakRefHandler1, WeakRefHandler paramWeakRefHandler2, Task paramTask) {
    if (paramWeakRefHandler2 != null)
      paramWeakRefHandler2.post(new Runnable(this, paramWeakRefHandler2, paramTask, paramWeakRefHandler1) {
            public void run() {
              Object object;
              if (this.a.isAlive() && this.b != null) {
                object = this.b.a();
              } else {
                object = null;
              } 
              if (this.c != null)
                this.c.post(new Runnable(this, object) {
                      public void run() {
                        if (this.b.c.isAlive() && this.b.b != null)
                          this.b.b.a(this.a); 
                      }
                    }); 
              AsyncHandlerTask.a(this.d);
            }
          }); 
  }
  
  public void execute(Task paramTask) {
    a(this.a, this.b, paramTask);
  }
  
  public static abstract class Task<T> {
    protected abstract T a();
    
    protected void a(T param1T) {}
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\async\AsyncHandlerTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */