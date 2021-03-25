package com.cndatacom.cnportalclient.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

public class ToastUtils {
  private static Toast a;
  
  private static Toast b;
  
  private static void a(Context paramContext, String paramString, int paramInt1, int paramInt2, int paramInt3) {
    if (a != null) {
      a.cancel();
      a = null;
    } 
    a = Toast.makeText(paramContext, paramString, 0);
    a.setGravity(paramInt1, paramInt2, paramInt3);
    a.show();
  }
  
  public static void showBottomText(Context paramContext, String paramString) {
    a(paramContext, paramString, 17, 0, 0);
  }
  
  public static void showBottomText(Context paramContext, String paramString, int paramInt1, int paramInt2) {
    a(paramContext, paramString, 17, paramInt1, paramInt2);
  }
  
  public static void showSafetyToast(Context paramContext) {
    if (b != null) {
      b.cancel();
      b = null;
    } 
    View view = ((LayoutInflater)paramContext.getSystemService("layout_inflater")).inflate(2131296297, null);
    b = new Toast(paramContext);
    b.setView(view);
    b.setDuration(0);
    b.setGravity(17, 0, 0);
    b.show();
  }
  
  public static void showTopText(Context paramContext, String paramString) {
    a(paramContext, paramString, 17, 0, 0);
  }
  
  public static void showTopText(Context paramContext, String paramString, int paramInt1, int paramInt2) {
    a(paramContext, paramString, 17, paramInt1, paramInt2);
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclien\\utils\ToastUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */