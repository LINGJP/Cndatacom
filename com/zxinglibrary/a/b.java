package com.zxinglibrary.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.hardware.Camera;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import java.lang.reflect.Method;

final class b {
  private final Context a;
  
  private Point b;
  
  private Point c;
  
  b(Context paramContext) {
    this.a = paramContext;
  }
  
  Point a() {
    return this.c;
  }
  
  @SuppressLint({"NewApi"})
  void a(Camera paramCamera) {
    Camera.Parameters parameters = paramCamera.getParameters();
    Display display = ((WindowManager)this.a.getSystemService("window")).getDefaultDisplay();
    this.b = new Point(display.getWidth(), display.getHeight());
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append("Screen resolution: ");
    stringBuilder2.append(this.b);
    Log.i("CameraConfiguration", stringBuilder2.toString());
    Point point = new Point();
    point.x = this.b.x;
    point.y = this.b.y;
    if (this.b.x < this.b.y) {
      point.x = this.b.y;
      point.y = this.b.x;
    } 
    this.c = c.a(parameters, point);
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append("Camera resolution: ");
    stringBuilder1.append(this.c);
    Log.i("CameraConfiguration", stringBuilder1.toString());
  }
  
  void a(Camera paramCamera, int paramInt) {
    try {
      Method method = paramCamera.getClass().getMethod("setDisplayOrientation", new Class[] { int.class });
      if (method != null) {
        method.invoke(paramCamera, new Object[] { Integer.valueOf(paramInt) });
        return;
      } 
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
  }
  
  void a(Camera paramCamera, boolean paramBoolean) {
    Camera.Parameters parameters = paramCamera.getParameters();
    if (parameters == null) {
      Log.w("CameraConfiguration", "Device error: no camera parameters are available. Proceeding without configuration.");
      return;
    } 
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append("Initial camera parameters: ");
    stringBuilder2.append(parameters.flatten());
    Log.i("CameraConfiguration", stringBuilder2.toString());
    if (paramBoolean)
      Log.w("CameraConfiguration", "In camera config safe mode -- most settings will not be honored"); 
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.a);
    c.a(parameters, sharedPreferences.getBoolean("preferences_auto_focus", true), sharedPreferences.getBoolean("preferences_disable_continuous_focus", true), paramBoolean);
    parameters.setPreviewSize(this.c.x, this.c.y);
    a(paramCamera, 90);
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append("Final camera parameters: ");
    stringBuilder1.append(parameters.flatten());
    Log.i("CameraConfiguration", stringBuilder1.toString());
    paramCamera.setParameters(parameters);
    Camera.Size size = paramCamera.getParameters().getPreviewSize();
    if (size != null && (this.c.x != size.width || this.c.y != size.height)) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Camera said it supported preview size ");
      stringBuilder.append(this.c.x);
      stringBuilder.append('x');
      stringBuilder.append(this.c.y);
      stringBuilder.append(", but after setting it, preview size is ");
      stringBuilder.append(size.width);
      stringBuilder.append('x');
      stringBuilder.append(size.height);
      Log.w("CameraConfiguration", stringBuilder.toString());
      this.c.x = size.width;
      this.c.y = size.height;
    } 
  }
  
  Point b() {
    return this.b;
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\zxinglibrary\a\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */