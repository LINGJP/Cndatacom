package com.cndatacom.cnportalclient.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

public class AndroidBug5497WorkaroundUtils {
  private View a;
  
  private int b;
  
  private FrameLayout.LayoutParams c;
  
  private AndroidBug5497WorkaroundUtils(Activity paramActivity) {
    this.a = ((FrameLayout)paramActivity.findViewById(16908290)).getChildAt(0);
    this.a.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener(this, paramActivity) {
          public void onGlobalLayout() {
            AndroidBug5497WorkaroundUtils.a(this.b, this.a);
          }
        });
    this.c = (FrameLayout.LayoutParams)this.a.getLayoutParams();
  }
  
  private int a() {
    Rect rect = new Rect();
    this.a.getWindowVisibleDisplayFrame(rect);
    return rect.bottom;
  }
  
  private void a(Activity paramActivity) {
    int i = a();
    if (i != this.b) {
      int j = AppInfoUtils.getNavigationBarHeight((Context)paramActivity);
      boolean bool = AppInfoUtils.isShowNavigationBar(paramActivity);
      int m = this.a.getRootView().getHeight();
      int k = m - i;
      if (k > m / 4 || bool) {
        if (k != 0)
          j = k; 
        this.c.height = m - j;
      } else {
        this.c.height = m;
      } 
      this.a.requestLayout();
      this.b = i;
    } 
  }
  
  public static void assistActivity(Activity paramActivity) {
    new AndroidBug5497WorkaroundUtils(paramActivity);
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclien\\utils\AndroidBug5497WorkaroundUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */