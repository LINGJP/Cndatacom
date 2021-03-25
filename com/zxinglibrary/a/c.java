package com.zxinglibrary.a;

import android.graphics.Point;
import android.hardware.Camera;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public final class c {
  private static final Pattern a = Pattern.compile(";");
  
  public static Point a(Camera.Parameters paramParameters, Point paramPoint) {
    List<?> list = paramParameters.getSupportedPreviewSizes();
    if (list == null) {
      Log.w("CameraConfiguration", "Device returned no supported preview sizes; using default");
      Camera.Size size = paramParameters.getPreviewSize();
      if (size == null)
        throw new IllegalStateException("Parameters contained no preview size!"); 
      return new Point(size.width, size.height);
    } 
    list = new ArrayList(list);
    Collections.sort(list, new Comparator<Camera.Size>() {
          public int a(Camera.Size param1Size1, Camera.Size param1Size2) {
            int i = param1Size1.height * param1Size1.width;
            int j = param1Size2.height * param1Size2.width;
            return (j < i) ? -1 : ((j > i) ? 1 : 0);
          }
        });
    if (Log.isLoggable("CameraConfiguration", 4)) {
      StringBuilder stringBuilder1 = new StringBuilder();
      for (Camera.Size size : list) {
        stringBuilder1.append(size.width);
        stringBuilder1.append('x');
        stringBuilder1.append(size.height);
        stringBuilder1.append(' ');
      } 
      StringBuilder stringBuilder2 = new StringBuilder();
      stringBuilder2.append("Supported preview sizes: ");
      stringBuilder2.append(stringBuilder1);
      Log.i("CameraConfiguration", stringBuilder2.toString());
    } 
    double d = paramPoint.x / paramPoint.y;
    Iterator<?> iterator = list.iterator();
    while (true) {
      Point point2;
      boolean bool = iterator.hasNext();
      int i = 0;
      if (bool) {
        int m;
        Camera.Size size1 = (Camera.Size)iterator.next();
        int j = size1.width;
        int k = size1.height;
        if (j * k < 153600) {
          iterator.remove();
          continue;
        } 
        if (j < k)
          i = 1; 
        if (i) {
          m = k;
        } else {
          m = j;
        } 
        if (i) {
          i = j;
        } else {
          i = k;
        } 
        if (Math.abs(m / i - d) > 0.15D) {
          iterator.remove();
          continue;
        } 
        if (m == paramPoint.x && i == paramPoint.y) {
          point2 = new Point(j, k);
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append("Found preview size exactly matching screen size: ");
          stringBuilder1.append(point2);
          Log.i("CameraConfiguration", stringBuilder1.toString());
          return point2;
        } 
        continue;
      } 
      if (!list.isEmpty()) {
        Camera.Size size1 = (Camera.Size)list.get(0);
        point2 = new Point(size1.width, size1.height);
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Using largest suitable preview size: ");
        stringBuilder1.append(point2);
        Log.i("CameraConfiguration", stringBuilder1.toString());
        return point2;
      } 
      Camera.Size size = point2.getPreviewSize();
      if (size == null)
        throw new IllegalStateException("Parameters contained no preview size!"); 
      Point point1 = new Point(size.width, size.height);
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("No suitable preview sizes, using default: ");
      stringBuilder.append(point1);
      Log.i("CameraConfiguration", stringBuilder.toString());
      return point1;
    } 
  }
  
  private static String a(String paramString, Collection<String> paramCollection, String... paramVarArgs) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Requesting ");
    stringBuilder.append(paramString);
    stringBuilder.append(" value from among: ");
    stringBuilder.append(Arrays.toString((Object[])paramVarArgs));
    Log.i("CameraConfiguration", stringBuilder.toString());
    stringBuilder = new StringBuilder();
    stringBuilder.append("Supported ");
    stringBuilder.append(paramString);
    stringBuilder.append(" values: ");
    stringBuilder.append(paramCollection);
    Log.i("CameraConfiguration", stringBuilder.toString());
    if (paramCollection != null) {
      int j = paramVarArgs.length;
      for (int i = 0; i < j; i++) {
        String str = paramVarArgs[i];
        if (paramCollection.contains(str)) {
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append("Can set ");
          stringBuilder1.append(paramString);
          stringBuilder1.append(" to: ");
          stringBuilder1.append(str);
          Log.i("CameraConfiguration", stringBuilder1.toString());
          return str;
        } 
      } 
    } 
    Log.i("CameraConfiguration", "No supported values match");
    return null;
  }
  
  public static void a(Camera.Parameters paramParameters, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3) {
    String str1;
    List<String> list = paramParameters.getSupportedFocusModes();
    if (paramBoolean1) {
      if (paramBoolean3 || paramBoolean2) {
        str1 = a("focus mode", list, new String[] { "auto" });
      } else {
        str1 = a("focus mode", list, new String[] { "continuous-video", "auto" });
      } 
    } else {
      str1 = null;
    } 
    String str2 = str1;
    if (!paramBoolean3) {
      str2 = str1;
      if (str1 == null)
        str2 = a("focus mode", list, new String[] { "macro", "edof" }); 
    } 
    if (str2 != null) {
      StringBuilder stringBuilder;
      if (str2.equals(paramParameters.getFocusMode())) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Focus mode already set to ");
        stringBuilder.append(str2);
        Log.i("CameraConfiguration", stringBuilder.toString());
        return;
      } 
      stringBuilder.setFocusMode(str2);
    } 
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\zxinglibrary\a\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */