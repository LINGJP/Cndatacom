package android.support.constraint;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.constraint.solver.widgets.ConstraintWidget;
import android.support.constraint.solver.widgets.Helper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.util.Arrays;

public abstract class ConstraintHelper extends View {
  protected int mCount;
  
  protected Helper mHelperWidget;
  
  protected int[] mIds = new int[32];
  
  private String mReferenceIds;
  
  protected boolean mUseViewMeasure = false;
  
  protected Context myContext;
  
  public ConstraintHelper(Context paramContext) {
    super(paramContext);
    this.myContext = paramContext;
    init((AttributeSet)null);
  }
  
  public ConstraintHelper(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    this.myContext = paramContext;
    init(paramAttributeSet);
  }
  
  public ConstraintHelper(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    this.myContext = paramContext;
    init(paramAttributeSet);
  }
  
  private void addID(String paramString) {
    if (paramString == null)
      return; 
    if (this.myContext == null)
      return; 
    paramString = paramString.trim();
    try {
      j = R.id.class.getField(paramString).getInt(null);
    } catch (Exception exception) {
      j = 0;
    } 
    int i = j;
    if (!j)
      i = this.myContext.getResources().getIdentifier(paramString, "id", this.myContext.getPackageName()); 
    int j = i;
    if (i == 0) {
      j = i;
      if (isInEditMode()) {
        j = i;
        if (getParent() instanceof ConstraintLayout) {
          Object object = ((ConstraintLayout)getParent()).getDesignInformation(0, paramString);
          j = i;
          if (object != null) {
            j = i;
            if (object instanceof Integer)
              j = ((Integer)object).intValue(); 
          } 
        } 
      } 
    } 
    if (j != 0) {
      setTag(j, (Object)null);
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Could not find id of \"");
    stringBuilder.append(paramString);
    stringBuilder.append("\"");
    Log.w("ConstraintHelper", stringBuilder.toString());
  }
  
  private void setIds(String paramString) {
    if (paramString == null)
      return; 
    for (int i = 0;; i = j + 1) {
      int j = paramString.indexOf(',', i);
      if (j == -1) {
        addID(paramString.substring(i));
        return;
      } 
      addID(paramString.substring(i, j));
    } 
  }
  
  public int[] getReferencedIds() {
    return Arrays.copyOf(this.mIds, this.mCount);
  }
  
  protected void init(AttributeSet paramAttributeSet) {
    if (paramAttributeSet != null) {
      TypedArray typedArray = getContext().obtainStyledAttributes(paramAttributeSet, R.styleable.ConstraintLayout_Layout);
      int j = typedArray.getIndexCount();
      for (int i = 0; i < j; i++) {
        int k = typedArray.getIndex(i);
        if (k == R.styleable.ConstraintLayout_Layout_constraint_referenced_ids) {
          this.mReferenceIds = typedArray.getString(k);
          setIds(this.mReferenceIds);
        } 
      } 
    } 
  }
  
  public void onDraw(Canvas paramCanvas) {}
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    if (this.mUseViewMeasure) {
      super.onMeasure(paramInt1, paramInt2);
      return;
    } 
    setMeasuredDimension(0, 0);
  }
  
  public void setReferencedIds(int[] paramArrayOfint) {
    int i = 0;
    this.mCount = 0;
    while (i < paramArrayOfint.length) {
      setTag(paramArrayOfint[i], (Object)null);
      i++;
    } 
  }
  
  public void setTag(int paramInt, Object paramObject) {
    if (this.mCount + 1 > this.mIds.length)
      this.mIds = Arrays.copyOf(this.mIds, this.mIds.length * 2); 
    this.mIds[this.mCount] = paramInt;
    this.mCount++;
  }
  
  public void updatePostLayout(ConstraintLayout paramConstraintLayout) {}
  
  public void updatePostMeasure(ConstraintLayout paramConstraintLayout) {}
  
  public void updatePreLayout(ConstraintLayout paramConstraintLayout) {
    if (isInEditMode())
      setIds(this.mReferenceIds); 
    if (this.mHelperWidget == null)
      return; 
    this.mHelperWidget.removeAllIds();
    for (int i = 0; i < this.mCount; i++) {
      View view = paramConstraintLayout.getViewById(this.mIds[i]);
      if (view != null)
        this.mHelperWidget.add(paramConstraintLayout.getViewWidget(view)); 
    } 
  }
  
  public void validateParams() {
    if (this.mHelperWidget == null)
      return; 
    ViewGroup.LayoutParams layoutParams = getLayoutParams();
    if (layoutParams instanceof ConstraintLayout.LayoutParams)
      ((ConstraintLayout.LayoutParams)layoutParams).widget = (ConstraintWidget)this.mHelperWidget; 
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\android\support\constraint\ConstraintHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */