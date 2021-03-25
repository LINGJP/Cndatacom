package com.cndatacom.cnportalclient.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.view.Window;

public class AppBarUtil {
  public static int getNavigationBarHeight(Context paramContext) {
    Resources resources = paramContext.getResources();
    return resources.getDimensionPixelSize(resources.getIdentifier("navigation_bar_height", "dimen", "android"));
  }
  
  public static int getStatusBarHeight(Context paramContext) {
    int i = paramContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
    return (i > 0) ? paramContext.getResources().getDimensionPixelSize(i) : 0;
  }
  
  public static void initBar(Window paramWindow) {
    if (Build.VERSION.SDK_INT >= 21) {
      paramWindow.clearFlags(67108864);
      paramWindow.getDecorView().setSystemUiVisibility(1024);
      paramWindow.setStatusBarColor(0);
      paramWindow.setNavigationBarColor(-16777216);
    } 
  }
  
  public static boolean isAddStatusBar() {
    return (Build.VERSION.SDK_INT >= 21);
  }
  
  public static boolean isShowNavigationBar(@NonNull Activity paramActivity) {
    ViewGroup viewGroup = (ViewGroup)paramActivity.getWindow().getDecorView();
    if (viewGroup != null)
      for (int i = 0; i < viewGroup.getChildCount(); i++) {
        viewGroup.getChildAt(i).getContext().getPackageName();
        if (viewGroup.getChildAt(i).getId() != -1 && "navigationBarBackground".equals(paramActivity.getResources().getResourceEntryName(viewGroup.getChildAt(i).getId())))
          return true; 
      }  
    return false;
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclien\\utils\AppBarUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */