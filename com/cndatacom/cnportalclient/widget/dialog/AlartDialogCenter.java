package com.cndatacom.cnportalclient.widget.dialog;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class AlartDialogCenter extends RelativeLayout {
  private boolean a = false;
  
  public AlartDialogCenter(Context paramContext) {
    super(paramContext);
  }
  
  public AlartDialogCenter(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
  }
  
  public AlartDialogCenter(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  protected void dispatchDraw(Canvas paramCanvas) {
    if (!this.a) {
      GradientDrawable gradientDrawable = new GradientDrawable();
      gradientDrawable.setColor(-1);
      gradientDrawable.setCornerRadii(new float[] { 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F });
      gradientDrawable.setStroke(0, 0);
      setBackgroundDrawable((Drawable)gradientDrawable);
      this.a = true;
    } 
    super.dispatchDraw(paramCanvas);
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\widget\dialog\AlartDialogCenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */