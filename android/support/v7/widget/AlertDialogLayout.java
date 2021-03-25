package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class AlertDialogLayout extends LinearLayoutCompat {
  public AlertDialogLayout(@Nullable Context paramContext) {
    super(paramContext);
  }
  
  public AlertDialogLayout(@Nullable Context paramContext, @Nullable AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
  }
  
  private void forceUniformWidth(int paramInt1, int paramInt2) {
    int j = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
    for (int i = 0; i < paramInt1; i++) {
      View view = getChildAt(i);
      if (view.getVisibility() != 8) {
        LinearLayoutCompat.LayoutParams layoutParams = (LinearLayoutCompat.LayoutParams)view.getLayoutParams();
        if (layoutParams.width == -1) {
          int k = layoutParams.height;
          layoutParams.height = view.getMeasuredHeight();
          measureChildWithMargins(view, j, 0, paramInt2, 0);
          layoutParams.height = k;
        } 
      } 
    } 
  }
  
  private static int resolveMinimumHeight(View paramView) {
    int i = ViewCompat.getMinimumHeight(paramView);
    if (i > 0)
      return i; 
    if (paramView instanceof ViewGroup) {
      ViewGroup viewGroup = (ViewGroup)paramView;
      if (viewGroup.getChildCount() == 1)
        return resolveMinimumHeight(viewGroup.getChildAt(0)); 
    } 
    return 0;
  }
  
  private void setChildFrame(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    paramView.layout(paramInt1, paramInt2, paramInt3 + paramInt1, paramInt4 + paramInt2);
  }
  
  private boolean tryOnMeasure(int paramInt1, int paramInt2) {
    int i1;
    byte b;
    int i4 = getChildCount();
    View view4 = null;
    View view1 = view4;
    View view2 = view1;
    int i = 0;
    View view3 = view1;
    while (i < i4) {
      view1 = getChildAt(i);
      if (view1.getVisibility() != 8) {
        j = view1.getId();
        if (j == R.id.topPanel) {
          view4 = view1;
        } else if (j == R.id.buttonPanel) {
          view3 = view1;
        } else if (j == R.id.contentPanel || j == R.id.customPanel) {
          if (view2 != null)
            return false; 
          view2 = view1;
        } else {
          return false;
        } 
      } 
      i++;
    } 
    int i6 = View.MeasureSpec.getMode(paramInt2);
    int n = View.MeasureSpec.getSize(paramInt2);
    int i5 = View.MeasureSpec.getMode(paramInt1);
    int k = getPaddingTop() + getPaddingBottom();
    if (view4 != null) {
      view4.measure(paramInt1, 0);
      k += view4.getMeasuredHeight();
      j = View.combineMeasuredStates(0, view4.getMeasuredState());
    } else {
      j = 0;
    } 
    if (view3 != null) {
      view3.measure(paramInt1, 0);
      i = resolveMinimumHeight(view3);
      i1 = view3.getMeasuredHeight() - i;
      k += i;
      j = View.combineMeasuredStates(j, view3.getMeasuredState());
    } else {
      i = 0;
      i1 = 0;
    } 
    if (view2 != null) {
      int i7;
      if (i6 == 0) {
        i7 = 0;
      } else {
        i7 = View.MeasureSpec.makeMeasureSpec(Math.max(0, n - k), i6);
      } 
      view2.measure(paramInt1, i7);
      b = view2.getMeasuredHeight();
      k += b;
      j = View.combineMeasuredStates(j, view2.getMeasuredState());
    } else {
      b = 0;
    } 
    int i2 = n - k;
    n = j;
    int i3 = i2;
    int m = k;
    if (view3 != null) {
      i1 = Math.min(i2, i1);
      n = i2;
      m = i;
      if (i1 > 0) {
        n = i2 - i1;
        m = i + i1;
      } 
      view3.measure(paramInt1, View.MeasureSpec.makeMeasureSpec(m, 1073741824));
      m = k - i + view3.getMeasuredHeight();
      i = View.combineMeasuredStates(j, view3.getMeasuredState());
      i3 = n;
      n = i;
    } 
    int j = n;
    i = m;
    if (view2 != null) {
      j = n;
      i = m;
      if (i3 > 0) {
        view2.measure(paramInt1, View.MeasureSpec.makeMeasureSpec(b + i3, i6));
        i = m - b + view2.getMeasuredHeight();
        j = View.combineMeasuredStates(n, view2.getMeasuredState());
      } 
    } 
    k = 0;
    for (m = 0; k < i4; m = n) {
      view1 = getChildAt(k);
      n = m;
      if (view1.getVisibility() != 8)
        n = Math.max(m, view1.getMeasuredWidth()); 
      k++;
    } 
    setMeasuredDimension(View.resolveSizeAndState(m + getPaddingLeft() + getPaddingRight(), paramInt1, j), View.resolveSizeAndState(i, paramInt2, 0));
    if (i5 != 1073741824)
      forceUniformWidth(i4, paramInt2); 
    return true;
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    int i = getPaddingLeft();
    int j = paramInt3 - paramInt1;
    int k = getPaddingRight();
    int m = getPaddingRight();
    paramInt1 = getMeasuredHeight();
    int n = getChildCount();
    int i1 = getGravity();
    paramInt3 = i1 & 0x70;
    if (paramInt3 != 16) {
      if (paramInt3 != 80) {
        paramInt1 = getPaddingTop();
      } else {
        paramInt1 = getPaddingTop() + paramInt4 - paramInt2 - paramInt1;
      } 
    } else {
      paramInt3 = getPaddingTop();
      paramInt1 = (paramInt4 - paramInt2 - paramInt1) / 2 + paramInt3;
    } 
    Drawable drawable = getDividerDrawable();
    if (drawable == null) {
      paramInt3 = 0;
    } else {
      paramInt3 = drawable.getIntrinsicHeight();
    } 
    paramInt4 = 0;
    while (paramInt4 < n) {
      View view = getChildAt(paramInt4);
      paramInt2 = paramInt1;
      if (view != null) {
        paramInt2 = paramInt1;
        if (view.getVisibility() != 8) {
          int i3 = view.getMeasuredWidth();
          int i4 = view.getMeasuredHeight();
          LinearLayoutCompat.LayoutParams layoutParams = (LinearLayoutCompat.LayoutParams)view.getLayoutParams();
          int i2 = layoutParams.gravity;
          paramInt2 = i2;
          if (i2 < 0)
            paramInt2 = i1 & 0x800007; 
          paramInt2 = GravityCompat.getAbsoluteGravity(paramInt2, ViewCompat.getLayoutDirection((View)this)) & 0x7;
          if (paramInt2 != 1) {
            if (paramInt2 != 5) {
              paramInt2 = layoutParams.leftMargin + i;
            } else {
              paramInt2 = j - k - i3 - layoutParams.rightMargin;
            } 
          } else {
            paramInt2 = (j - i - m - i3) / 2 + i + layoutParams.leftMargin - layoutParams.rightMargin;
          } 
          i2 = paramInt1;
          if (hasDividerBeforeChildAt(paramInt4))
            i2 = paramInt1 + paramInt3; 
          paramInt1 = i2 + layoutParams.topMargin;
          setChildFrame(view, paramInt2, paramInt1, i3, i4);
          paramInt2 = paramInt1 + i4 + layoutParams.bottomMargin;
        } 
      } 
      paramInt4++;
      paramInt1 = paramInt2;
    } 
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    if (!tryOnMeasure(paramInt1, paramInt2))
      super.onMeasure(paramInt1, paramInt2); 
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\android\support\v7\widget\AlertDialogLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */