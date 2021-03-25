package android.support.constraint;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

public class Group extends ConstraintHelper {
  public Group(Context paramContext) {
    super(paramContext);
  }
  
  public Group(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
  }
  
  public Group(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  protected void init(AttributeSet paramAttributeSet) {
    super.init(paramAttributeSet);
    this.mUseViewMeasure = false;
  }
  
  public void updatePostLayout(ConstraintLayout paramConstraintLayout) {
    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams)getLayoutParams();
    layoutParams.widget.setWidth(0);
    layoutParams.widget.setHeight(0);
  }
  
  public void updatePreLayout(ConstraintLayout paramConstraintLayout) {
    float f;
    int j = getVisibility();
    if (Build.VERSION.SDK_INT >= 21) {
      f = getElevation();
    } else {
      f = 0.0F;
    } 
    for (int i = 0; i < this.mCount; i++) {
      View view = paramConstraintLayout.getViewById(this.mIds[i]);
      if (view != null) {
        view.setVisibility(j);
        if (f > 0.0F && Build.VERSION.SDK_INT >= 21)
          view.setElevation(f); 
      } 
    } 
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\android\support\constraint\Group.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */