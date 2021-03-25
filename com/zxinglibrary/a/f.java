package com.zxinglibrary.a;

import android.graphics.Point;
import android.hardware.Camera;
import android.os.Handler;
import android.util.Log;

final class f implements Camera.PreviewCallback {
  private static final String a = "f";
  
  private final b b;
  
  private Handler c;
  
  private int d;
  
  f(b paramb) {
    this.b = paramb;
  }
  
  void a(Handler paramHandler, int paramInt) {
    this.c = paramHandler;
    this.d = paramInt;
  }
  
  public void onPreviewFrame(byte[] paramArrayOfbyte, Camera paramCamera) {
    Point point = this.b.a();
    Handler handler = this.c;
    if (point != null && handler != null) {
      handler.obtainMessage(this.d, point.x, point.y, paramArrayOfbyte).sendToTarget();
      this.c = null;
      return;
    } 
    Log.d(a, "Got preview callback, but no handler or resolution available");
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\zxinglibrary\a\f.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */