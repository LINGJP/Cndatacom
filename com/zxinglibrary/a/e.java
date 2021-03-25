package com.zxinglibrary.a;

import android.annotation.SuppressLint;
import android.hardware.Camera;
import android.util.Log;

public final class e {
  private static final String a = "com.zxinglibrary.a.e";
  
  public static Camera a() {
    return a(-1);
  }
  
  @SuppressLint({"NewApi"})
  public static Camera a(int paramInt) {
    boolean bool;
    int j = Camera.getNumberOfCameras();
    if (j == 0) {
      Log.w(a, "No cameras!");
      return null;
    } 
    if (paramInt >= 0) {
      bool = true;
    } else {
      bool = false;
    } 
    int i = paramInt;
    if (!bool) {
      paramInt = 0;
      while (true) {
        i = paramInt;
        if (paramInt < j) {
          Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
          Camera.getCameraInfo(paramInt, cameraInfo);
          if (cameraInfo.facing == 0) {
            i = paramInt;
            break;
          } 
          paramInt++;
          continue;
        } 
        break;
      } 
    } 
    if (i < j) {
      String str = a;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Opening camera #");
      stringBuilder.append(i);
      Log.i(str, stringBuilder.toString());
      return Camera.open(i);
    } 
    if (bool) {
      String str = a;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Requested camera does not exist: ");
      stringBuilder.append(i);
      Log.w(str, stringBuilder.toString());
      return null;
    } 
    Log.i(a, "No camera facing back; returning camera #0");
    return Camera.open(0);
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\zxinglibrary\a\e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */