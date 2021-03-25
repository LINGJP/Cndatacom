package com.cndatacom.cnportalclient.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class RRectangleLinearLayout extends LinearLayout {
  private boolean a = false;
  
  private boolean b = false;
  
  private String c = "#000000,80";
  
  private String d = "#000000,120";
  
  public RRectangleLinearLayout(Context paramContext) {
    super(paramContext);
  }
  
  public RRectangleLinearLayout(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
  }
  
  public RRectangleLinearLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  private int a(String paramString) {
    String[] arrayOfString;
    if (paramString.contains(",")) {
      arrayOfString = paramString.split(",");
      int i = Color.parseColor(arrayOfString[0]);
      return Color.argb(Integer.valueOf(arrayOfString[1]).intValue(), (0xFF0000 & i) >> 16, (0xFF00 & i) >> 8, i & 0xFF);
    } 
    return Color.parseColor((String)arrayOfString);
  }
  
  protected void dispatchDraw(Canvas paramCanvas) {
    if (!this.a) {
      int i;
      int j = a(this.c);
      if (this.b) {
        i = a(this.d);
      } else {
        i = j;
      } 
      GradientDrawable gradientDrawable = new GradientDrawable();
      gradientDrawable.setColor(i);
      gradientDrawable.setCornerRadii(new float[] { 15.0F, 15.0F, 15.0F, 15.0F, 15.0F, 15.0F, 15.0F, 15.0F });
      gradientDrawable.setStroke(1, j);
      setBackgroundDrawable((Drawable)gradientDrawable);
      this.a = true;
    } 
    super.dispatchDraw(paramCanvas);
  }
  
  public void setDefColorString(String paramString) {
    this.a = false;
    this.c = paramString;
  }
  
  public void setPressed(boolean paramBoolean) {
    this.a = false;
    this.b = paramBoolean;
    invalidate();
    super.setPressed(paramBoolean);
  }
  
  public void setPressedColorString(String paramString) {
    this.a = false;
    this.d = paramString;
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\widget\RRectangleLinearLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */