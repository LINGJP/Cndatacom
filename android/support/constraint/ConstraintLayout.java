package android.support.constraint;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.constraint.solver.Metrics;
import android.support.constraint.solver.widgets.Analyzer;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.constraint.solver.widgets.ConstraintWidget;
import android.support.constraint.solver.widgets.ConstraintWidgetContainer;
import android.support.constraint.solver.widgets.Guideline;
import android.support.constraint.solver.widgets.ResolutionAnchor;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.HashMap;

public class ConstraintLayout extends ViewGroup {
  static final boolean ALLOWS_EMBEDDED = false;
  
  private static final boolean CACHE_MEASURED_DIMENSION = false;
  
  private static final boolean DEBUG = false;
  
  public static final int DESIGN_INFO_ID = 0;
  
  private static final String TAG = "ConstraintLayout";
  
  private static final boolean USE_CONSTRAINTS_HELPER = true;
  
  public static final String VERSION = "ConstraintLayout-1.1.3";
  
  SparseArray<View> mChildrenByIds = new SparseArray();
  
  private ArrayList<ConstraintHelper> mConstraintHelpers = new ArrayList<ConstraintHelper>(4);
  
  private ConstraintSet mConstraintSet = null;
  
  private int mConstraintSetId = -1;
  
  private HashMap<String, Integer> mDesignIds = new HashMap<String, Integer>();
  
  private boolean mDirtyHierarchy = true;
  
  private int mLastMeasureHeight = -1;
  
  int mLastMeasureHeightMode = 0;
  
  int mLastMeasureHeightSize = -1;
  
  private int mLastMeasureWidth = -1;
  
  int mLastMeasureWidthMode = 0;
  
  int mLastMeasureWidthSize = -1;
  
  ConstraintWidgetContainer mLayoutWidget = new ConstraintWidgetContainer();
  
  private int mMaxHeight = Integer.MAX_VALUE;
  
  private int mMaxWidth = Integer.MAX_VALUE;
  
  private Metrics mMetrics;
  
  private int mMinHeight = 0;
  
  private int mMinWidth = 0;
  
  private int mOptimizationLevel = 7;
  
  private final ArrayList<ConstraintWidget> mVariableDimensionsWidgets = new ArrayList<ConstraintWidget>(100);
  
  public ConstraintLayout(Context paramContext) {
    super(paramContext);
    init((AttributeSet)null);
  }
  
  public ConstraintLayout(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    init(paramAttributeSet);
  }
  
  public ConstraintLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramAttributeSet);
  }
  
  private final ConstraintWidget getTargetWidget(int paramInt) {
    if (paramInt == 0)
      return (ConstraintWidget)this.mLayoutWidget; 
    View view2 = (View)this.mChildrenByIds.get(paramInt);
    View view1 = view2;
    if (view2 == null) {
      view2 = findViewById(paramInt);
      view1 = view2;
      if (view2 != null) {
        view1 = view2;
        if (view2 != this) {
          view1 = view2;
          if (view2.getParent() == this) {
            onViewAdded(view2);
            view1 = view2;
          } 
        } 
      } 
    } 
    return (ConstraintWidget)((view1 == this) ? this.mLayoutWidget : ((view1 == null) ? null : ((LayoutParams)view1.getLayoutParams()).widget));
  }
  
  private void init(AttributeSet paramAttributeSet) {
    this.mLayoutWidget.setCompanionWidget(this);
    this.mChildrenByIds.put(getId(), this);
    this.mConstraintSet = null;
    if (paramAttributeSet != null) {
      TypedArray typedArray = getContext().obtainStyledAttributes(paramAttributeSet, R.styleable.ConstraintLayout_Layout);
      int j = typedArray.getIndexCount();
      int i = 0;
      while (true) {
        if (i < j) {
          int k = typedArray.getIndex(i);
          if (k == R.styleable.ConstraintLayout_Layout_android_minWidth) {
            this.mMinWidth = typedArray.getDimensionPixelOffset(k, this.mMinWidth);
          } else if (k == R.styleable.ConstraintLayout_Layout_android_minHeight) {
            this.mMinHeight = typedArray.getDimensionPixelOffset(k, this.mMinHeight);
          } else if (k == R.styleable.ConstraintLayout_Layout_android_maxWidth) {
            this.mMaxWidth = typedArray.getDimensionPixelOffset(k, this.mMaxWidth);
          } else if (k == R.styleable.ConstraintLayout_Layout_android_maxHeight) {
            this.mMaxHeight = typedArray.getDimensionPixelOffset(k, this.mMaxHeight);
          } else if (k == R.styleable.ConstraintLayout_Layout_layout_optimizationLevel) {
            this.mOptimizationLevel = typedArray.getInt(k, this.mOptimizationLevel);
          } else if (k == R.styleable.ConstraintLayout_Layout_constraintSet) {
            k = typedArray.getResourceId(k, 0);
            try {
              this.mConstraintSet = new ConstraintSet();
              this.mConstraintSet.load(getContext(), k);
            } catch (android.content.res.Resources.NotFoundException notFoundException) {
              this.mConstraintSet = null;
            } 
            this.mConstraintSetId = k;
          } 
          i++;
          continue;
        } 
        typedArray.recycle();
        this.mLayoutWidget.setOptimizationLevel(this.mOptimizationLevel);
        return;
      } 
    } 
    this.mLayoutWidget.setOptimizationLevel(this.mOptimizationLevel);
  }
  
  private void internalMeasureChildren(int paramInt1, int paramInt2) {
    int j = getPaddingTop() + getPaddingBottom();
    int k = getPaddingLeft() + getPaddingRight();
    int m = getChildCount();
    int i = 0;
    while (true) {
      int n = paramInt1;
      ConstraintLayout constraintLayout = this;
      if (i < m) {
        View view = constraintLayout.getChildAt(i);
        if (view.getVisibility() != 8) {
          LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
          ConstraintWidget constraintWidget = layoutParams.widget;
          if (!layoutParams.isGuideline && !layoutParams.isHelper) {
            int i1;
            int i2;
            int i3;
            constraintWidget.setVisibility(view.getVisibility());
            int i4 = layoutParams.width;
            int i5 = layoutParams.height;
            if (layoutParams.horizontalDimensionFixed || layoutParams.verticalDimensionFixed || (!layoutParams.horizontalDimensionFixed && layoutParams.matchConstraintDefaultWidth == 1) || layoutParams.width == -1 || (!layoutParams.verticalDimensionFixed && (layoutParams.matchConstraintDefaultHeight == 1 || layoutParams.height == -1))) {
              i1 = 1;
            } else {
              i1 = 0;
            } 
            if (i1) {
              boolean bool;
              if (i4 == 0) {
                i2 = getChildMeasureSpec(n, k, -2);
                i1 = 1;
              } else if (i4 == -1) {
                i2 = getChildMeasureSpec(n, k, -1);
                i1 = 0;
              } else {
                if (i4 == -2) {
                  i1 = 1;
                } else {
                  i1 = 0;
                } 
                i2 = getChildMeasureSpec(n, k, i4);
              } 
              if (i5 == 0) {
                i3 = getChildMeasureSpec(paramInt2, j, -2);
                n = 1;
              } else if (i5 == -1) {
                i3 = getChildMeasureSpec(paramInt2, j, -1);
                n = 0;
              } else {
                if (i5 == -2) {
                  n = 1;
                } else {
                  n = 0;
                } 
                i3 = getChildMeasureSpec(paramInt2, j, i5);
              } 
              view.measure(i2, i3);
              if (constraintLayout.mMetrics != null) {
                Metrics metrics = constraintLayout.mMetrics;
                metrics.measures++;
              } 
              if (i4 == -2) {
                bool = true;
              } else {
                bool = false;
              } 
              constraintWidget.setWidthWrapContent(bool);
              if (i5 == -2) {
                bool = true;
              } else {
                bool = false;
              } 
              constraintWidget.setHeightWrapContent(bool);
              i2 = view.getMeasuredWidth();
              i3 = view.getMeasuredHeight();
            } else {
              i1 = 0;
              n = 0;
              i3 = i5;
              i2 = i4;
            } 
            constraintWidget.setWidth(i2);
            constraintWidget.setHeight(i3);
            if (i1)
              constraintWidget.setWrapWidth(i2); 
            if (n != 0)
              constraintWidget.setWrapHeight(i3); 
            if (layoutParams.needsBaseline) {
              i1 = view.getBaseline();
              if (i1 != -1)
                constraintWidget.setBaselineDistance(i1); 
            } 
          } 
        } 
        i++;
        continue;
      } 
      break;
    } 
  }
  
  private void internalMeasureDimensions(int paramInt1, int paramInt2) {
    ConstraintLayout constraintLayout = this;
    int k = getPaddingTop() + getPaddingBottom();
    int j = getPaddingLeft() + getPaddingRight();
    int i = getChildCount();
    int m = 0;
    while (true) {
      long l = 1L;
      if (m < i) {
        View view = constraintLayout.getChildAt(m);
        if (view.getVisibility() != 8) {
          LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
          ConstraintWidget constraintWidget = layoutParams.widget;
          if (!layoutParams.isGuideline && !layoutParams.isHelper) {
            constraintWidget.setVisibility(view.getVisibility());
            int i2 = layoutParams.width;
            int i3 = layoutParams.height;
            if (i2 == 0 || i3 == 0) {
              constraintWidget.getResolutionWidth().invalidate();
              constraintWidget.getResolutionHeight().invalidate();
            } else {
              int i4;
              boolean bool1;
              boolean bool2;
              if (i2 == -2) {
                i4 = 1;
              } else {
                i4 = 0;
              } 
              int i5 = getChildMeasureSpec(paramInt1, j, i2);
              if (i3 == -2) {
                bool1 = true;
              } else {
                bool1 = false;
              } 
              view.measure(i5, getChildMeasureSpec(paramInt2, k, i3));
              if (constraintLayout.mMetrics != null) {
                Metrics metrics = constraintLayout.mMetrics;
                metrics.measures++;
              } 
              if (i2 == -2) {
                bool2 = true;
              } else {
                bool2 = false;
              } 
              constraintWidget.setWidthWrapContent(bool2);
              if (i3 == -2) {
                bool2 = true;
              } else {
                bool2 = false;
              } 
              constraintWidget.setHeightWrapContent(bool2);
              i2 = view.getMeasuredWidth();
              i3 = view.getMeasuredHeight();
              constraintWidget.setWidth(i2);
              constraintWidget.setHeight(i3);
              if (i4)
                constraintWidget.setWrapWidth(i2); 
              if (bool1)
                constraintWidget.setWrapHeight(i3); 
              if (layoutParams.needsBaseline) {
                i4 = view.getBaseline();
                if (i4 != -1)
                  constraintWidget.setBaselineDistance(i4); 
              } 
              if (layoutParams.horizontalDimensionFixed && layoutParams.verticalDimensionFixed) {
                constraintWidget.getResolutionWidth().resolve(i2);
                constraintWidget.getResolutionHeight().resolve(i3);
              } 
            } 
          } 
        } 
        m++;
        continue;
      } 
      constraintLayout.mLayoutWidget.solveGraph();
      int n = i;
      int i1 = 0;
      i = j;
      m = k;
      while (true) {
        int i2 = paramInt1;
        constraintLayout = this;
        if (i1 < n) {
          View view = constraintLayout.getChildAt(i1);
          if (view.getVisibility() != 8) {
            LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
            ConstraintWidget constraintWidget = layoutParams.widget;
            if (!layoutParams.isGuideline && !layoutParams.isHelper) {
              constraintWidget.setVisibility(view.getVisibility());
              int i4 = layoutParams.width;
              int i3 = layoutParams.height;
              if (i4 == 0 || i3 == 0) {
                int i5;
                boolean bool;
                ResolutionAnchor resolutionAnchor1 = constraintWidget.getAnchor(ConstraintAnchor.Type.LEFT).getResolutionNode();
                ResolutionAnchor resolutionAnchor2 = constraintWidget.getAnchor(ConstraintAnchor.Type.RIGHT).getResolutionNode();
                if (constraintWidget.getAnchor(ConstraintAnchor.Type.LEFT).getTarget() != null && constraintWidget.getAnchor(ConstraintAnchor.Type.RIGHT).getTarget() != null) {
                  i5 = 1;
                } else {
                  i5 = 0;
                } 
                ResolutionAnchor resolutionAnchor3 = constraintWidget.getAnchor(ConstraintAnchor.Type.TOP).getResolutionNode();
                ResolutionAnchor resolutionAnchor4 = constraintWidget.getAnchor(ConstraintAnchor.Type.BOTTOM).getResolutionNode();
                if (constraintWidget.getAnchor(ConstraintAnchor.Type.TOP).getTarget() != null && constraintWidget.getAnchor(ConstraintAnchor.Type.BOTTOM).getTarget() != null) {
                  bool = true;
                } else {
                  bool = false;
                } 
                if (i4 == 0 && i3 == 0 && i5 && bool) {
                  l = 1L;
                } else {
                  if (constraintLayout.mLayoutWidget.getHorizontalDimensionBehaviour() != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    k = 1;
                  } else {
                    k = 0;
                  } 
                  if (constraintLayout.mLayoutWidget.getVerticalDimensionBehaviour() != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    j = 1;
                  } else {
                    j = 0;
                  } 
                  if (k == 0)
                    constraintWidget.getResolutionWidth().invalidate(); 
                  if (j == 0)
                    constraintWidget.getResolutionHeight().invalidate(); 
                  if (i4 == 0) {
                    if (k != 0 && constraintWidget.isSpreadWidth() && i5 && resolutionAnchor1.isResolved() && resolutionAnchor2.isResolved()) {
                      i5 = (int)(resolutionAnchor2.getResolvedValue() - resolutionAnchor1.getResolvedValue());
                      constraintWidget.getResolutionWidth().resolve(i5);
                      i2 = getChildMeasureSpec(i2, i, i5);
                    } else {
                      i2 = getChildMeasureSpec(i2, i, -2);
                      k = 1;
                      i5 = 0;
                      int i7 = i;
                    } 
                  } else {
                    int i7 = i;
                    if (i4 == -1) {
                      i2 = getChildMeasureSpec(i2, i7, -1);
                      i5 = i4;
                    } else {
                      if (i4 == -2) {
                        i5 = 1;
                      } else {
                        i5 = 0;
                      } 
                      i7 = getChildMeasureSpec(i2, i7, i4);
                      i2 = k;
                      k = i5;
                      i5 = i2;
                      i2 = i7;
                      i7 = i;
                    } 
                  } 
                  int i6 = k;
                  k = 0;
                  i4 = i5;
                  i5 = i6;
                  i6 = i;
                } 
              } 
            } 
          } 
        } else {
          break;
        } 
        i1++;
      } 
      return;
    } 
  }
  
  private void setChildrenConstraints() {
    throw new RuntimeException("d2j fail translate: java.lang.RuntimeException: can not merge Z and I\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
  }
  
  private void setSelfDimensionBehaviour(int paramInt1, int paramInt2) {
    // Byte code:
    //   0: iload_1
    //   1: invokestatic getMode : (I)I
    //   4: istore #6
    //   6: iload_1
    //   7: invokestatic getSize : (I)I
    //   10: istore_1
    //   11: iload_2
    //   12: invokestatic getMode : (I)I
    //   15: istore_3
    //   16: iload_2
    //   17: invokestatic getSize : (I)I
    //   20: istore_2
    //   21: aload_0
    //   22: invokevirtual getPaddingTop : ()I
    //   25: istore #4
    //   27: aload_0
    //   28: invokevirtual getPaddingBottom : ()I
    //   31: istore #5
    //   33: aload_0
    //   34: invokevirtual getPaddingLeft : ()I
    //   37: istore #7
    //   39: aload_0
    //   40: invokevirtual getPaddingRight : ()I
    //   43: istore #8
    //   45: getstatic android/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour.FIXED : Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   48: astore #9
    //   50: getstatic android/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour.FIXED : Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   53: astore #10
    //   55: aload_0
    //   56: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   59: pop
    //   60: iload #6
    //   62: ldc_w -2147483648
    //   65: if_icmpeq -> 112
    //   68: iload #6
    //   70: ifeq -> 104
    //   73: iload #6
    //   75: ldc_w 1073741824
    //   78: if_icmpeq -> 86
    //   81: iconst_0
    //   82: istore_1
    //   83: goto -> 117
    //   86: aload_0
    //   87: getfield mMaxWidth : I
    //   90: iload_1
    //   91: invokestatic min : (II)I
    //   94: iload #7
    //   96: iload #8
    //   98: iadd
    //   99: isub
    //   100: istore_1
    //   101: goto -> 117
    //   104: getstatic android/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour.WRAP_CONTENT : Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   107: astore #9
    //   109: goto -> 81
    //   112: getstatic android/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour.WRAP_CONTENT : Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   115: astore #9
    //   117: iload_3
    //   118: ldc_w -2147483648
    //   121: if_icmpeq -> 166
    //   124: iload_3
    //   125: ifeq -> 158
    //   128: iload_3
    //   129: ldc_w 1073741824
    //   132: if_icmpeq -> 140
    //   135: iconst_0
    //   136: istore_2
    //   137: goto -> 171
    //   140: aload_0
    //   141: getfield mMaxHeight : I
    //   144: iload_2
    //   145: invokestatic min : (II)I
    //   148: iload #4
    //   150: iload #5
    //   152: iadd
    //   153: isub
    //   154: istore_2
    //   155: goto -> 171
    //   158: getstatic android/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour.WRAP_CONTENT : Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   161: astore #10
    //   163: goto -> 135
    //   166: getstatic android/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour.WRAP_CONTENT : Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   169: astore #10
    //   171: aload_0
    //   172: getfield mLayoutWidget : Landroid/support/constraint/solver/widgets/ConstraintWidgetContainer;
    //   175: iconst_0
    //   176: invokevirtual setMinWidth : (I)V
    //   179: aload_0
    //   180: getfield mLayoutWidget : Landroid/support/constraint/solver/widgets/ConstraintWidgetContainer;
    //   183: iconst_0
    //   184: invokevirtual setMinHeight : (I)V
    //   187: aload_0
    //   188: getfield mLayoutWidget : Landroid/support/constraint/solver/widgets/ConstraintWidgetContainer;
    //   191: aload #9
    //   193: invokevirtual setHorizontalDimensionBehaviour : (Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;)V
    //   196: aload_0
    //   197: getfield mLayoutWidget : Landroid/support/constraint/solver/widgets/ConstraintWidgetContainer;
    //   200: iload_1
    //   201: invokevirtual setWidth : (I)V
    //   204: aload_0
    //   205: getfield mLayoutWidget : Landroid/support/constraint/solver/widgets/ConstraintWidgetContainer;
    //   208: aload #10
    //   210: invokevirtual setVerticalDimensionBehaviour : (Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;)V
    //   213: aload_0
    //   214: getfield mLayoutWidget : Landroid/support/constraint/solver/widgets/ConstraintWidgetContainer;
    //   217: iload_2
    //   218: invokevirtual setHeight : (I)V
    //   221: aload_0
    //   222: getfield mLayoutWidget : Landroid/support/constraint/solver/widgets/ConstraintWidgetContainer;
    //   225: aload_0
    //   226: getfield mMinWidth : I
    //   229: aload_0
    //   230: invokevirtual getPaddingLeft : ()I
    //   233: isub
    //   234: aload_0
    //   235: invokevirtual getPaddingRight : ()I
    //   238: isub
    //   239: invokevirtual setMinWidth : (I)V
    //   242: aload_0
    //   243: getfield mLayoutWidget : Landroid/support/constraint/solver/widgets/ConstraintWidgetContainer;
    //   246: aload_0
    //   247: getfield mMinHeight : I
    //   250: aload_0
    //   251: invokevirtual getPaddingTop : ()I
    //   254: isub
    //   255: aload_0
    //   256: invokevirtual getPaddingBottom : ()I
    //   259: isub
    //   260: invokevirtual setMinHeight : (I)V
    //   263: return
  }
  
  private void updateHierarchy() {
    boolean bool1;
    int j = getChildCount();
    boolean bool2 = false;
    int i = 0;
    while (true) {
      bool1 = bool2;
      if (i < j) {
        if (getChildAt(i).isLayoutRequested()) {
          bool1 = true;
          break;
        } 
        i++;
        continue;
      } 
      break;
    } 
    if (bool1) {
      this.mVariableDimensionsWidgets.clear();
      setChildrenConstraints();
    } 
  }
  
  private void updatePostMeasures() {
    int j = getChildCount();
    boolean bool = false;
    int i;
    for (i = 0; i < j; i++) {
      View view = getChildAt(i);
      if (view instanceof Placeholder)
        ((Placeholder)view).updatePostMeasure(this); 
    } 
    j = this.mConstraintHelpers.size();
    if (j > 0)
      for (i = bool; i < j; i++)
        ((ConstraintHelper)this.mConstraintHelpers.get(i)).updatePostMeasure(this);  
  }
  
  public void addView(View paramView, int paramInt, ViewGroup.LayoutParams paramLayoutParams) {
    super.addView(paramView, paramInt, paramLayoutParams);
    if (Build.VERSION.SDK_INT < 14)
      onViewAdded(paramView); 
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
    return paramLayoutParams instanceof LayoutParams;
  }
  
  public void dispatchDraw(Canvas paramCanvas) {
    super.dispatchDraw(paramCanvas);
    if (isInEditMode()) {
      int j = getChildCount();
      float f1 = getWidth();
      float f2 = getHeight();
      int i;
      for (i = 0; i < j; i++) {
        View view = getChildAt(i);
        if (view.getVisibility() != 8) {
          Object object = view.getTag();
          if (object != null && object instanceof String) {
            object = ((String)object).split(",");
            if (object.length == 4) {
              int m = Integer.parseInt((String)object[0]);
              int i1 = Integer.parseInt((String)object[1]);
              int n = Integer.parseInt((String)object[2]);
              int k = Integer.parseInt((String)object[3]);
              m = (int)(m / 1080.0F * f1);
              i1 = (int)(i1 / 1920.0F * f2);
              n = (int)(n / 1080.0F * f1);
              k = (int)(k / 1920.0F * f2);
              object = new Paint();
              object.setColor(-65536);
              float f3 = m;
              float f4 = i1;
              float f5 = (m + n);
              paramCanvas.drawLine(f3, f4, f5, f4, (Paint)object);
              float f6 = (i1 + k);
              paramCanvas.drawLine(f5, f4, f5, f6, (Paint)object);
              paramCanvas.drawLine(f5, f6, f3, f6, (Paint)object);
              paramCanvas.drawLine(f3, f6, f3, f4, (Paint)object);
              object.setColor(-16711936);
              paramCanvas.drawLine(f3, f4, f5, f6, (Paint)object);
              paramCanvas.drawLine(f3, f6, f5, f4, (Paint)object);
            } 
          } 
        } 
      } 
    } 
  }
  
  public void fillMetrics(Metrics paramMetrics) {
    this.mMetrics = paramMetrics;
    this.mLayoutWidget.fillMetrics(paramMetrics);
  }
  
  protected LayoutParams generateDefaultLayoutParams() {
    return new LayoutParams(-2, -2);
  }
  
  public LayoutParams generateLayoutParams(AttributeSet paramAttributeSet) {
    return new LayoutParams(getContext(), paramAttributeSet);
  }
  
  protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
    return (ViewGroup.LayoutParams)new LayoutParams(paramLayoutParams);
  }
  
  public Object getDesignInformation(int paramInt, Object paramObject) {
    if (paramInt == 0 && paramObject instanceof String) {
      paramObject = paramObject;
      if (this.mDesignIds != null && this.mDesignIds.containsKey(paramObject))
        return this.mDesignIds.get(paramObject); 
    } 
    return null;
  }
  
  public int getMaxHeight() {
    return this.mMaxHeight;
  }
  
  public int getMaxWidth() {
    return this.mMaxWidth;
  }
  
  public int getMinHeight() {
    return this.mMinHeight;
  }
  
  public int getMinWidth() {
    return this.mMinWidth;
  }
  
  public int getOptimizationLevel() {
    return this.mLayoutWidget.getOptimizationLevel();
  }
  
  public View getViewById(int paramInt) {
    return (View)this.mChildrenByIds.get(paramInt);
  }
  
  public final ConstraintWidget getViewWidget(View paramView) {
    return (ConstraintWidget)((paramView == this) ? this.mLayoutWidget : ((paramView == null) ? null : ((LayoutParams)paramView.getLayoutParams()).widget));
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    paramInt3 = getChildCount();
    paramBoolean = isInEditMode();
    paramInt2 = 0;
    for (paramInt1 = 0; paramInt1 < paramInt3; paramInt1++) {
      View view = getChildAt(paramInt1);
      LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
      ConstraintWidget constraintWidget = layoutParams.widget;
      if ((view.getVisibility() != 8 || layoutParams.isGuideline || layoutParams.isHelper || paramBoolean) && !layoutParams.isInPlaceholder) {
        paramInt4 = constraintWidget.getDrawX();
        int i = constraintWidget.getDrawY();
        int j = constraintWidget.getWidth() + paramInt4;
        int k = constraintWidget.getHeight() + i;
        view.layout(paramInt4, i, j, k);
        if (view instanceof Placeholder) {
          view = ((Placeholder)view).getContent();
          if (view != null) {
            view.setVisibility(0);
            view.layout(paramInt4, i, j, k);
          } 
        } 
      } 
    } 
    paramInt3 = this.mConstraintHelpers.size();
    if (paramInt3 > 0)
      for (paramInt1 = paramInt2; paramInt1 < paramInt3; paramInt1++)
        ((ConstraintHelper)this.mConstraintHelpers.get(paramInt1)).updatePostLayout(this);  
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    int i;
    boolean bool;
    System.currentTimeMillis();
    int m = View.MeasureSpec.getMode(paramInt1);
    int n = View.MeasureSpec.getSize(paramInt1);
    int i1 = View.MeasureSpec.getMode(paramInt2);
    int i2 = View.MeasureSpec.getSize(paramInt2);
    int j = getPaddingLeft();
    int k = getPaddingTop();
    this.mLayoutWidget.setX(j);
    this.mLayoutWidget.setY(k);
    this.mLayoutWidget.setMaxWidth(this.mMaxWidth);
    this.mLayoutWidget.setMaxHeight(this.mMaxHeight);
    if (Build.VERSION.SDK_INT >= 17) {
      boolean bool1;
      ConstraintWidgetContainer constraintWidgetContainer = this.mLayoutWidget;
      if (getLayoutDirection() == 1) {
        bool1 = true;
      } else {
        bool1 = false;
      } 
      constraintWidgetContainer.setRtl(bool1);
    } 
    setSelfDimensionBehaviour(paramInt1, paramInt2);
    int i5 = this.mLayoutWidget.getWidth();
    int i4 = this.mLayoutWidget.getHeight();
    if (this.mDirtyHierarchy) {
      this.mDirtyHierarchy = false;
      updateHierarchy();
      i = 1;
    } else {
      i = 0;
    } 
    if ((this.mOptimizationLevel & 0x8) == 8) {
      bool = true;
    } else {
      bool = false;
    } 
    if (bool) {
      this.mLayoutWidget.preOptimize();
      this.mLayoutWidget.optimizeForDimensions(i5, i4);
      internalMeasureDimensions(paramInt1, paramInt2);
    } else {
      internalMeasureChildren(paramInt1, paramInt2);
    } 
    updatePostMeasures();
    if (getChildCount() > 0 && i)
      Analyzer.determineGroups(this.mLayoutWidget); 
    if (this.mLayoutWidget.mGroupsWrapOptimized) {
      if (this.mLayoutWidget.mHorizontalWrapOptimized && m == Integer.MIN_VALUE) {
        if (this.mLayoutWidget.mWrapFixedWidth < n)
          this.mLayoutWidget.setWidth(this.mLayoutWidget.mWrapFixedWidth); 
        this.mLayoutWidget.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
      } 
      if (this.mLayoutWidget.mVerticalWrapOptimized && i1 == Integer.MIN_VALUE) {
        if (this.mLayoutWidget.mWrapFixedHeight < i2)
          this.mLayoutWidget.setHeight(this.mLayoutWidget.mWrapFixedHeight); 
        this.mLayoutWidget.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
      } 
    } 
    if ((this.mOptimizationLevel & 0x20) == 32) {
      i = this.mLayoutWidget.getWidth();
      int i6 = this.mLayoutWidget.getHeight();
      if (this.mLastMeasureWidth != i && m == 1073741824)
        Analyzer.setPosition(this.mLayoutWidget.mWidgetGroups, 0, i); 
      if (this.mLastMeasureHeight != i6 && i1 == 1073741824)
        Analyzer.setPosition(this.mLayoutWidget.mWidgetGroups, 1, i6); 
      if (this.mLayoutWidget.mHorizontalWrapOptimized && this.mLayoutWidget.mWrapFixedWidth > n)
        Analyzer.setPosition(this.mLayoutWidget.mWidgetGroups, 0, n); 
      if (this.mLayoutWidget.mVerticalWrapOptimized && this.mLayoutWidget.mWrapFixedHeight > i2)
        Analyzer.setPosition(this.mLayoutWidget.mWidgetGroups, 1, i2); 
    } 
    if (getChildCount() > 0)
      solveLinearSystem("First pass"); 
    int i3 = this.mVariableDimensionsWidgets.size();
    n = k + getPaddingBottom();
    i1 = j + getPaddingRight();
    if (i3 > 0) {
      boolean bool1;
      boolean bool2;
      if (this.mLayoutWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
        bool1 = true;
      } else {
        bool1 = false;
      } 
      if (this.mLayoutWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      k = Math.max(this.mLayoutWidget.getWidth(), this.mMinWidth);
      i = Math.max(this.mLayoutWidget.getHeight(), this.mMinHeight);
      int i6 = 0;
      m = 0;
      j = 0;
      while (i6 < i3) {
        ConstraintWidget constraintWidget = this.mVariableDimensionsWidgets.get(i6);
        View view = (View)constraintWidget.getCompanionWidget();
        if (view != null) {
          LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
          if (!layoutParams.isHelper && !layoutParams.isGuideline) {
            int i7 = view.getVisibility();
            i2 = m;
            if (i7 != 8 && (!bool || !constraintWidget.getResolutionWidth().isResolved() || !constraintWidget.getResolutionHeight().isResolved())) {
              if (layoutParams.width == -2 && layoutParams.horizontalDimensionFixed) {
                m = getChildMeasureSpec(paramInt1, i1, layoutParams.width);
              } else {
                m = View.MeasureSpec.makeMeasureSpec(constraintWidget.getWidth(), 1073741824);
              } 
              if (layoutParams.height == -2 && layoutParams.verticalDimensionFixed) {
                i7 = getChildMeasureSpec(paramInt2, n, layoutParams.height);
              } else {
                i7 = View.MeasureSpec.makeMeasureSpec(constraintWidget.getHeight(), 1073741824);
              } 
              view.measure(m, i7);
              if (this.mMetrics != null) {
                Metrics metrics = this.mMetrics;
                metrics.additionalMeasures++;
              } 
              int i8 = view.getMeasuredWidth();
              i7 = view.getMeasuredHeight();
              m = k;
              if (i8 != constraintWidget.getWidth()) {
                constraintWidget.setWidth(i8);
                if (bool)
                  constraintWidget.getResolutionWidth().resolve(i8); 
                m = k;
                if (bool1) {
                  m = k;
                  if (constraintWidget.getRight() > k)
                    m = Math.max(k, constraintWidget.getRight() + constraintWidget.getAnchor(ConstraintAnchor.Type.RIGHT).getMargin()); 
                } 
                i2 = 1;
              } 
              k = i;
              if (i7 != constraintWidget.getHeight()) {
                constraintWidget.setHeight(i7);
                if (bool)
                  constraintWidget.getResolutionHeight().resolve(i7); 
                k = i;
                if (bool2) {
                  k = i;
                  if (constraintWidget.getBottom() > i)
                    k = Math.max(i, constraintWidget.getBottom() + constraintWidget.getAnchor(ConstraintAnchor.Type.BOTTOM).getMargin()); 
                } 
                i2 = 1;
              } 
              i = i2;
              if (layoutParams.needsBaseline) {
                i7 = view.getBaseline();
                i = i2;
                if (i7 != -1) {
                  i = i2;
                  if (i7 != constraintWidget.getBaselineDistance()) {
                    constraintWidget.setBaselineDistance(i7);
                    i = 1;
                  } 
                } 
              } 
              if (Build.VERSION.SDK_INT >= 11) {
                j = combineMeasuredStates(j, view.getMeasuredState());
                i2 = k;
                k = m;
                m = i;
              } else {
                i2 = k;
                k = m;
                m = i;
              } 
              continue;
            } 
          } 
        } 
        i2 = i;
        continue;
        i6++;
        i = i2;
      } 
      i2 = j;
      if (m != 0) {
        this.mLayoutWidget.setWidth(i5);
        this.mLayoutWidget.setHeight(i4);
        if (bool)
          this.mLayoutWidget.solveGraph(); 
        solveLinearSystem("2nd pass");
        if (this.mLayoutWidget.getWidth() < k) {
          this.mLayoutWidget.setWidth(k);
          j = 1;
        } else {
          j = 0;
        } 
        if (this.mLayoutWidget.getHeight() < i) {
          this.mLayoutWidget.setHeight(i);
          j = 1;
        } 
        if (j != 0)
          solveLinearSystem("3rd pass"); 
      } 
      m = 0;
      while (true) {
        i = i2;
        j = n;
        k = i1;
        if (m < i3) {
          ConstraintWidget constraintWidget = this.mVariableDimensionsWidgets.get(m);
          View view = (View)constraintWidget.getCompanionWidget();
          if (view != null && (view.getMeasuredWidth() != constraintWidget.getWidth() || view.getMeasuredHeight() != constraintWidget.getHeight()) && constraintWidget.getVisibility() != 8) {
            view.measure(View.MeasureSpec.makeMeasureSpec(constraintWidget.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(constraintWidget.getHeight(), 1073741824));
            if (this.mMetrics != null) {
              Metrics metrics = this.mMetrics;
              metrics.additionalMeasures++;
            } 
          } 
          m++;
          continue;
        } 
        break;
      } 
    } else {
      k = i1;
      j = n;
      i = 0;
    } 
    k = this.mLayoutWidget.getWidth() + k;
    j = this.mLayoutWidget.getHeight() + j;
    if (Build.VERSION.SDK_INT >= 11) {
      paramInt1 = resolveSizeAndState(k, paramInt1, i);
      i = resolveSizeAndState(j, paramInt2, i << 16);
      paramInt2 = Math.min(this.mMaxWidth, paramInt1 & 0xFFFFFF);
      i = Math.min(this.mMaxHeight, i & 0xFFFFFF);
      paramInt1 = paramInt2;
      if (this.mLayoutWidget.isWidthMeasuredTooSmall())
        paramInt1 = paramInt2 | 0x1000000; 
      paramInt2 = i;
      if (this.mLayoutWidget.isHeightMeasuredTooSmall())
        paramInt2 = i | 0x1000000; 
      setMeasuredDimension(paramInt1, paramInt2);
      this.mLastMeasureWidth = paramInt1;
      this.mLastMeasureHeight = paramInt2;
      return;
    } 
    setMeasuredDimension(k, j);
    this.mLastMeasureWidth = k;
    this.mLastMeasureHeight = j;
  }
  
  public void onViewAdded(View paramView) {
    if (Build.VERSION.SDK_INT >= 14)
      super.onViewAdded(paramView); 
    ConstraintWidget constraintWidget = getViewWidget(paramView);
    if (paramView instanceof Guideline && !(constraintWidget instanceof Guideline)) {
      LayoutParams layoutParams = (LayoutParams)paramView.getLayoutParams();
      layoutParams.widget = (ConstraintWidget)new Guideline();
      layoutParams.isGuideline = true;
      ((Guideline)layoutParams.widget).setOrientation(layoutParams.orientation);
    } 
    if (paramView instanceof ConstraintHelper) {
      ConstraintHelper constraintHelper = (ConstraintHelper)paramView;
      constraintHelper.validateParams();
      ((LayoutParams)paramView.getLayoutParams()).isHelper = true;
      if (!this.mConstraintHelpers.contains(constraintHelper))
        this.mConstraintHelpers.add(constraintHelper); 
    } 
    this.mChildrenByIds.put(paramView.getId(), paramView);
    this.mDirtyHierarchy = true;
  }
  
  public void onViewRemoved(View paramView) {
    if (Build.VERSION.SDK_INT >= 14)
      super.onViewRemoved(paramView); 
    this.mChildrenByIds.remove(paramView.getId());
    ConstraintWidget constraintWidget = getViewWidget(paramView);
    this.mLayoutWidget.remove(constraintWidget);
    this.mConstraintHelpers.remove(paramView);
    this.mVariableDimensionsWidgets.remove(constraintWidget);
    this.mDirtyHierarchy = true;
  }
  
  public void removeView(View paramView) {
    super.removeView(paramView);
    if (Build.VERSION.SDK_INT < 14)
      onViewRemoved(paramView); 
  }
  
  public void requestLayout() {
    super.requestLayout();
    this.mDirtyHierarchy = true;
    this.mLastMeasureWidth = -1;
    this.mLastMeasureHeight = -1;
    this.mLastMeasureWidthSize = -1;
    this.mLastMeasureHeightSize = -1;
    this.mLastMeasureWidthMode = 0;
    this.mLastMeasureHeightMode = 0;
  }
  
  public void setConstraintSet(ConstraintSet paramConstraintSet) {
    this.mConstraintSet = paramConstraintSet;
  }
  
  public void setDesignInformation(int paramInt, Object paramObject1, Object paramObject2) {
    if (paramInt == 0 && paramObject1 instanceof String && paramObject2 instanceof Integer) {
      if (this.mDesignIds == null)
        this.mDesignIds = new HashMap<String, Integer>(); 
      String str = (String)paramObject1;
      paramInt = str.indexOf("/");
      paramObject1 = str;
      if (paramInt != -1)
        paramObject1 = str.substring(paramInt + 1); 
      paramInt = ((Integer)paramObject2).intValue();
      this.mDesignIds.put(paramObject1, Integer.valueOf(paramInt));
    } 
  }
  
  public void setId(int paramInt) {
    this.mChildrenByIds.remove(getId());
    super.setId(paramInt);
    this.mChildrenByIds.put(getId(), this);
  }
  
  public void setMaxHeight(int paramInt) {
    if (paramInt == this.mMaxHeight)
      return; 
    this.mMaxHeight = paramInt;
    requestLayout();
  }
  
  public void setMaxWidth(int paramInt) {
    if (paramInt == this.mMaxWidth)
      return; 
    this.mMaxWidth = paramInt;
    requestLayout();
  }
  
  public void setMinHeight(int paramInt) {
    if (paramInt == this.mMinHeight)
      return; 
    this.mMinHeight = paramInt;
    requestLayout();
  }
  
  public void setMinWidth(int paramInt) {
    if (paramInt == this.mMinWidth)
      return; 
    this.mMinWidth = paramInt;
    requestLayout();
  }
  
  public void setOptimizationLevel(int paramInt) {
    this.mLayoutWidget.setOptimizationLevel(paramInt);
  }
  
  public boolean shouldDelayChildPressedState() {
    return false;
  }
  
  protected void solveLinearSystem(String paramString) {
    this.mLayoutWidget.layout();
    if (this.mMetrics != null) {
      Metrics metrics = this.mMetrics;
      metrics.resolutions++;
    } 
  }
  
  public static class LayoutParams extends ViewGroup.MarginLayoutParams {
    public static final int BASELINE = 5;
    
    public static final int BOTTOM = 4;
    
    public static final int CHAIN_PACKED = 2;
    
    public static final int CHAIN_SPREAD = 0;
    
    public static final int CHAIN_SPREAD_INSIDE = 1;
    
    public static final int END = 7;
    
    public static final int HORIZONTAL = 0;
    
    public static final int LEFT = 1;
    
    public static final int MATCH_CONSTRAINT = 0;
    
    public static final int MATCH_CONSTRAINT_PERCENT = 2;
    
    public static final int MATCH_CONSTRAINT_SPREAD = 0;
    
    public static final int MATCH_CONSTRAINT_WRAP = 1;
    
    public static final int PARENT_ID = 0;
    
    public static final int RIGHT = 2;
    
    public static final int START = 6;
    
    public static final int TOP = 3;
    
    public static final int UNSET = -1;
    
    public static final int VERTICAL = 1;
    
    public int baselineToBaseline = -1;
    
    public int bottomToBottom = -1;
    
    public int bottomToTop = -1;
    
    public float circleAngle = 0.0F;
    
    public int circleConstraint = -1;
    
    public int circleRadius = 0;
    
    public boolean constrainedHeight = false;
    
    public boolean constrainedWidth = false;
    
    public String dimensionRatio = null;
    
    int dimensionRatioSide = 1;
    
    float dimensionRatioValue = 0.0F;
    
    public int editorAbsoluteX = -1;
    
    public int editorAbsoluteY = -1;
    
    public int endToEnd = -1;
    
    public int endToStart = -1;
    
    public int goneBottomMargin = -1;
    
    public int goneEndMargin = -1;
    
    public int goneLeftMargin = -1;
    
    public int goneRightMargin = -1;
    
    public int goneStartMargin = -1;
    
    public int goneTopMargin = -1;
    
    public int guideBegin = -1;
    
    public int guideEnd = -1;
    
    public float guidePercent = -1.0F;
    
    public boolean helped = false;
    
    public float horizontalBias = 0.5F;
    
    public int horizontalChainStyle = 0;
    
    boolean horizontalDimensionFixed = true;
    
    public float horizontalWeight = -1.0F;
    
    boolean isGuideline = false;
    
    boolean isHelper = false;
    
    boolean isInPlaceholder = false;
    
    public int leftToLeft = -1;
    
    public int leftToRight = -1;
    
    public int matchConstraintDefaultHeight = 0;
    
    public int matchConstraintDefaultWidth = 0;
    
    public int matchConstraintMaxHeight = 0;
    
    public int matchConstraintMaxWidth = 0;
    
    public int matchConstraintMinHeight = 0;
    
    public int matchConstraintMinWidth = 0;
    
    public float matchConstraintPercentHeight = 1.0F;
    
    public float matchConstraintPercentWidth = 1.0F;
    
    boolean needsBaseline = false;
    
    public int orientation = -1;
    
    int resolveGoneLeftMargin = -1;
    
    int resolveGoneRightMargin = -1;
    
    int resolvedGuideBegin;
    
    int resolvedGuideEnd;
    
    float resolvedGuidePercent;
    
    float resolvedHorizontalBias = 0.5F;
    
    int resolvedLeftToLeft = -1;
    
    int resolvedLeftToRight = -1;
    
    int resolvedRightToLeft = -1;
    
    int resolvedRightToRight = -1;
    
    public int rightToLeft = -1;
    
    public int rightToRight = -1;
    
    public int startToEnd = -1;
    
    public int startToStart = -1;
    
    public int topToBottom = -1;
    
    public int topToTop = -1;
    
    public float verticalBias = 0.5F;
    
    public int verticalChainStyle = 0;
    
    boolean verticalDimensionFixed = true;
    
    public float verticalWeight = -1.0F;
    
    ConstraintWidget widget = new ConstraintWidget();
    
    public LayoutParams(int param1Int1, int param1Int2) {
      super(param1Int1, param1Int2);
    }
    
    public LayoutParams(Context param1Context, AttributeSet param1AttributeSet) {
      // Byte code:
      //   0: aload_0
      //   1: aload_1
      //   2: aload_2
      //   3: invokespecial <init> : (Landroid/content/Context;Landroid/util/AttributeSet;)V
      //   6: aload_0
      //   7: iconst_m1
      //   8: putfield guideBegin : I
      //   11: aload_0
      //   12: iconst_m1
      //   13: putfield guideEnd : I
      //   16: aload_0
      //   17: ldc -1.0
      //   19: putfield guidePercent : F
      //   22: aload_0
      //   23: iconst_m1
      //   24: putfield leftToLeft : I
      //   27: aload_0
      //   28: iconst_m1
      //   29: putfield leftToRight : I
      //   32: aload_0
      //   33: iconst_m1
      //   34: putfield rightToLeft : I
      //   37: aload_0
      //   38: iconst_m1
      //   39: putfield rightToRight : I
      //   42: aload_0
      //   43: iconst_m1
      //   44: putfield topToTop : I
      //   47: aload_0
      //   48: iconst_m1
      //   49: putfield topToBottom : I
      //   52: aload_0
      //   53: iconst_m1
      //   54: putfield bottomToTop : I
      //   57: aload_0
      //   58: iconst_m1
      //   59: putfield bottomToBottom : I
      //   62: aload_0
      //   63: iconst_m1
      //   64: putfield baselineToBaseline : I
      //   67: aload_0
      //   68: iconst_m1
      //   69: putfield circleConstraint : I
      //   72: aload_0
      //   73: iconst_0
      //   74: putfield circleRadius : I
      //   77: aload_0
      //   78: fconst_0
      //   79: putfield circleAngle : F
      //   82: aload_0
      //   83: iconst_m1
      //   84: putfield startToEnd : I
      //   87: aload_0
      //   88: iconst_m1
      //   89: putfield startToStart : I
      //   92: aload_0
      //   93: iconst_m1
      //   94: putfield endToStart : I
      //   97: aload_0
      //   98: iconst_m1
      //   99: putfield endToEnd : I
      //   102: aload_0
      //   103: iconst_m1
      //   104: putfield goneLeftMargin : I
      //   107: aload_0
      //   108: iconst_m1
      //   109: putfield goneTopMargin : I
      //   112: aload_0
      //   113: iconst_m1
      //   114: putfield goneRightMargin : I
      //   117: aload_0
      //   118: iconst_m1
      //   119: putfield goneBottomMargin : I
      //   122: aload_0
      //   123: iconst_m1
      //   124: putfield goneStartMargin : I
      //   127: aload_0
      //   128: iconst_m1
      //   129: putfield goneEndMargin : I
      //   132: aload_0
      //   133: ldc 0.5
      //   135: putfield horizontalBias : F
      //   138: aload_0
      //   139: ldc 0.5
      //   141: putfield verticalBias : F
      //   144: aload_0
      //   145: aconst_null
      //   146: putfield dimensionRatio : Ljava/lang/String;
      //   149: aload_0
      //   150: fconst_0
      //   151: putfield dimensionRatioValue : F
      //   154: aload_0
      //   155: iconst_1
      //   156: putfield dimensionRatioSide : I
      //   159: aload_0
      //   160: ldc -1.0
      //   162: putfield horizontalWeight : F
      //   165: aload_0
      //   166: ldc -1.0
      //   168: putfield verticalWeight : F
      //   171: aload_0
      //   172: iconst_0
      //   173: putfield horizontalChainStyle : I
      //   176: aload_0
      //   177: iconst_0
      //   178: putfield verticalChainStyle : I
      //   181: aload_0
      //   182: iconst_0
      //   183: putfield matchConstraintDefaultWidth : I
      //   186: aload_0
      //   187: iconst_0
      //   188: putfield matchConstraintDefaultHeight : I
      //   191: aload_0
      //   192: iconst_0
      //   193: putfield matchConstraintMinWidth : I
      //   196: aload_0
      //   197: iconst_0
      //   198: putfield matchConstraintMinHeight : I
      //   201: aload_0
      //   202: iconst_0
      //   203: putfield matchConstraintMaxWidth : I
      //   206: aload_0
      //   207: iconst_0
      //   208: putfield matchConstraintMaxHeight : I
      //   211: aload_0
      //   212: fconst_1
      //   213: putfield matchConstraintPercentWidth : F
      //   216: aload_0
      //   217: fconst_1
      //   218: putfield matchConstraintPercentHeight : F
      //   221: aload_0
      //   222: iconst_m1
      //   223: putfield editorAbsoluteX : I
      //   226: aload_0
      //   227: iconst_m1
      //   228: putfield editorAbsoluteY : I
      //   231: aload_0
      //   232: iconst_m1
      //   233: putfield orientation : I
      //   236: aload_0
      //   237: iconst_0
      //   238: putfield constrainedWidth : Z
      //   241: aload_0
      //   242: iconst_0
      //   243: putfield constrainedHeight : Z
      //   246: aload_0
      //   247: iconst_1
      //   248: putfield horizontalDimensionFixed : Z
      //   251: aload_0
      //   252: iconst_1
      //   253: putfield verticalDimensionFixed : Z
      //   256: aload_0
      //   257: iconst_0
      //   258: putfield needsBaseline : Z
      //   261: aload_0
      //   262: iconst_0
      //   263: putfield isGuideline : Z
      //   266: aload_0
      //   267: iconst_0
      //   268: putfield isHelper : Z
      //   271: aload_0
      //   272: iconst_0
      //   273: putfield isInPlaceholder : Z
      //   276: aload_0
      //   277: iconst_m1
      //   278: putfield resolvedLeftToLeft : I
      //   281: aload_0
      //   282: iconst_m1
      //   283: putfield resolvedLeftToRight : I
      //   286: aload_0
      //   287: iconst_m1
      //   288: putfield resolvedRightToLeft : I
      //   291: aload_0
      //   292: iconst_m1
      //   293: putfield resolvedRightToRight : I
      //   296: aload_0
      //   297: iconst_m1
      //   298: putfield resolveGoneLeftMargin : I
      //   301: aload_0
      //   302: iconst_m1
      //   303: putfield resolveGoneRightMargin : I
      //   306: aload_0
      //   307: ldc 0.5
      //   309: putfield resolvedHorizontalBias : F
      //   312: aload_0
      //   313: new android/support/constraint/solver/widgets/ConstraintWidget
      //   316: dup
      //   317: invokespecial <init> : ()V
      //   320: putfield widget : Landroid/support/constraint/solver/widgets/ConstraintWidget;
      //   323: aload_0
      //   324: iconst_0
      //   325: putfield helped : Z
      //   328: aload_1
      //   329: aload_2
      //   330: getstatic android/support/constraint/R$styleable.ConstraintLayout_Layout : [I
      //   333: invokevirtual obtainStyledAttributes : (Landroid/util/AttributeSet;[I)Landroid/content/res/TypedArray;
      //   336: astore_1
      //   337: aload_1
      //   338: invokevirtual getIndexCount : ()I
      //   341: istore #7
      //   343: iconst_0
      //   344: istore #5
      //   346: iload #5
      //   348: iload #7
      //   350: if_icmpge -> 2041
      //   353: aload_1
      //   354: iload #5
      //   356: invokevirtual getIndex : (I)I
      //   359: istore #6
      //   361: getstatic android/support/constraint/ConstraintLayout$LayoutParams$Table.map : Landroid/util/SparseIntArray;
      //   364: iload #6
      //   366: invokevirtual get : (I)I
      //   369: tableswitch default -> 588, 0 -> 2032, 1 -> 2018, 2 -> 1982, 3 -> 1965, 4 -> 1919, 5 -> 1902, 6 -> 1885, 7 -> 1868, 8 -> 1832, 9 -> 1796, 10 -> 1760, 11 -> 1724, 12 -> 1688, 13 -> 1652, 14 -> 1616, 15 -> 1580, 16 -> 1544, 17 -> 1508, 18 -> 1472, 19 -> 1436, 20 -> 1400, 21 -> 1383, 22 -> 1366, 23 -> 1349, 24 -> 1332, 25 -> 1315, 26 -> 1298, 27 -> 1281, 28 -> 1264, 29 -> 1247, 30 -> 1230, 31 -> 1198, 32 -> 1166, 33 -> 1125, 34 -> 1084, 35 -> 1063, 36 -> 1022, 37 -> 981, 38 -> 960, 39 -> 2032, 40 -> 2032, 41 -> 2032, 42 -> 2032, 43 -> 588, 44 -> 687, 45 -> 670, 46 -> 653, 47 -> 639, 48 -> 625, 49 -> 608, 50 -> 591
      //   588: goto -> 2032
      //   591: aload_0
      //   592: aload_1
      //   593: iload #6
      //   595: aload_0
      //   596: getfield editorAbsoluteY : I
      //   599: invokevirtual getDimensionPixelOffset : (II)I
      //   602: putfield editorAbsoluteY : I
      //   605: goto -> 2032
      //   608: aload_0
      //   609: aload_1
      //   610: iload #6
      //   612: aload_0
      //   613: getfield editorAbsoluteX : I
      //   616: invokevirtual getDimensionPixelOffset : (II)I
      //   619: putfield editorAbsoluteX : I
      //   622: goto -> 2032
      //   625: aload_0
      //   626: aload_1
      //   627: iload #6
      //   629: iconst_0
      //   630: invokevirtual getInt : (II)I
      //   633: putfield verticalChainStyle : I
      //   636: goto -> 2032
      //   639: aload_0
      //   640: aload_1
      //   641: iload #6
      //   643: iconst_0
      //   644: invokevirtual getInt : (II)I
      //   647: putfield horizontalChainStyle : I
      //   650: goto -> 2032
      //   653: aload_0
      //   654: aload_1
      //   655: iload #6
      //   657: aload_0
      //   658: getfield verticalWeight : F
      //   661: invokevirtual getFloat : (IF)F
      //   664: putfield verticalWeight : F
      //   667: goto -> 2032
      //   670: aload_0
      //   671: aload_1
      //   672: iload #6
      //   674: aload_0
      //   675: getfield horizontalWeight : F
      //   678: invokevirtual getFloat : (IF)F
      //   681: putfield horizontalWeight : F
      //   684: goto -> 2032
      //   687: aload_0
      //   688: aload_1
      //   689: iload #6
      //   691: invokevirtual getString : (I)Ljava/lang/String;
      //   694: putfield dimensionRatio : Ljava/lang/String;
      //   697: aload_0
      //   698: ldc_w NaN
      //   701: putfield dimensionRatioValue : F
      //   704: aload_0
      //   705: iconst_m1
      //   706: putfield dimensionRatioSide : I
      //   709: aload_0
      //   710: getfield dimensionRatio : Ljava/lang/String;
      //   713: ifnull -> 2032
      //   716: aload_0
      //   717: getfield dimensionRatio : Ljava/lang/String;
      //   720: invokevirtual length : ()I
      //   723: istore #8
      //   725: aload_0
      //   726: getfield dimensionRatio : Ljava/lang/String;
      //   729: bipush #44
      //   731: invokevirtual indexOf : (I)I
      //   734: istore #6
      //   736: iload #6
      //   738: ifle -> 803
      //   741: iload #6
      //   743: iload #8
      //   745: iconst_1
      //   746: isub
      //   747: if_icmpge -> 803
      //   750: aload_0
      //   751: getfield dimensionRatio : Ljava/lang/String;
      //   754: iconst_0
      //   755: iload #6
      //   757: invokevirtual substring : (II)Ljava/lang/String;
      //   760: astore_2
      //   761: aload_2
      //   762: ldc_w 'W'
      //   765: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
      //   768: ifeq -> 779
      //   771: aload_0
      //   772: iconst_0
      //   773: putfield dimensionRatioSide : I
      //   776: goto -> 794
      //   779: aload_2
      //   780: ldc_w 'H'
      //   783: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
      //   786: ifeq -> 794
      //   789: aload_0
      //   790: iconst_1
      //   791: putfield dimensionRatioSide : I
      //   794: iload #6
      //   796: iconst_1
      //   797: iadd
      //   798: istore #6
      //   800: goto -> 806
      //   803: iconst_0
      //   804: istore #6
      //   806: aload_0
      //   807: getfield dimensionRatio : Ljava/lang/String;
      //   810: bipush #58
      //   812: invokevirtual indexOf : (I)I
      //   815: istore #9
      //   817: iload #9
      //   819: iflt -> 932
      //   822: iload #9
      //   824: iload #8
      //   826: iconst_1
      //   827: isub
      //   828: if_icmpge -> 932
      //   831: aload_0
      //   832: getfield dimensionRatio : Ljava/lang/String;
      //   835: iload #6
      //   837: iload #9
      //   839: invokevirtual substring : (II)Ljava/lang/String;
      //   842: astore_2
      //   843: aload_0
      //   844: getfield dimensionRatio : Ljava/lang/String;
      //   847: iload #9
      //   849: iconst_1
      //   850: iadd
      //   851: invokevirtual substring : (I)Ljava/lang/String;
      //   854: astore #10
      //   856: aload_2
      //   857: invokevirtual length : ()I
      //   860: ifle -> 2032
      //   863: aload #10
      //   865: invokevirtual length : ()I
      //   868: ifle -> 2032
      //   871: aload_2
      //   872: invokestatic parseFloat : (Ljava/lang/String;)F
      //   875: fstore_3
      //   876: aload #10
      //   878: invokestatic parseFloat : (Ljava/lang/String;)F
      //   881: fstore #4
      //   883: fload_3
      //   884: fconst_0
      //   885: fcmpl
      //   886: ifle -> 2032
      //   889: fload #4
      //   891: fconst_0
      //   892: fcmpl
      //   893: ifle -> 2032
      //   896: aload_0
      //   897: getfield dimensionRatioSide : I
      //   900: iconst_1
      //   901: if_icmpne -> 918
      //   904: aload_0
      //   905: fload #4
      //   907: fload_3
      //   908: fdiv
      //   909: invokestatic abs : (F)F
      //   912: putfield dimensionRatioValue : F
      //   915: goto -> 2032
      //   918: aload_0
      //   919: fload_3
      //   920: fload #4
      //   922: fdiv
      //   923: invokestatic abs : (F)F
      //   926: putfield dimensionRatioValue : F
      //   929: goto -> 2032
      //   932: aload_0
      //   933: getfield dimensionRatio : Ljava/lang/String;
      //   936: iload #6
      //   938: invokevirtual substring : (I)Ljava/lang/String;
      //   941: astore_2
      //   942: aload_2
      //   943: invokevirtual length : ()I
      //   946: ifle -> 2032
      //   949: aload_0
      //   950: aload_2
      //   951: invokestatic parseFloat : (Ljava/lang/String;)F
      //   954: putfield dimensionRatioValue : F
      //   957: goto -> 2032
      //   960: aload_0
      //   961: fconst_0
      //   962: aload_1
      //   963: iload #6
      //   965: aload_0
      //   966: getfield matchConstraintPercentHeight : F
      //   969: invokevirtual getFloat : (IF)F
      //   972: invokestatic max : (FF)F
      //   975: putfield matchConstraintPercentHeight : F
      //   978: goto -> 2032
      //   981: aload_0
      //   982: aload_1
      //   983: iload #6
      //   985: aload_0
      //   986: getfield matchConstraintMaxHeight : I
      //   989: invokevirtual getDimensionPixelSize : (II)I
      //   992: putfield matchConstraintMaxHeight : I
      //   995: goto -> 2032
      //   998: aload_1
      //   999: iload #6
      //   1001: aload_0
      //   1002: getfield matchConstraintMaxHeight : I
      //   1005: invokevirtual getInt : (II)I
      //   1008: bipush #-2
      //   1010: if_icmpne -> 2032
      //   1013: aload_0
      //   1014: bipush #-2
      //   1016: putfield matchConstraintMaxHeight : I
      //   1019: goto -> 2032
      //   1022: aload_0
      //   1023: aload_1
      //   1024: iload #6
      //   1026: aload_0
      //   1027: getfield matchConstraintMinHeight : I
      //   1030: invokevirtual getDimensionPixelSize : (II)I
      //   1033: putfield matchConstraintMinHeight : I
      //   1036: goto -> 2032
      //   1039: aload_1
      //   1040: iload #6
      //   1042: aload_0
      //   1043: getfield matchConstraintMinHeight : I
      //   1046: invokevirtual getInt : (II)I
      //   1049: bipush #-2
      //   1051: if_icmpne -> 2032
      //   1054: aload_0
      //   1055: bipush #-2
      //   1057: putfield matchConstraintMinHeight : I
      //   1060: goto -> 2032
      //   1063: aload_0
      //   1064: fconst_0
      //   1065: aload_1
      //   1066: iload #6
      //   1068: aload_0
      //   1069: getfield matchConstraintPercentWidth : F
      //   1072: invokevirtual getFloat : (IF)F
      //   1075: invokestatic max : (FF)F
      //   1078: putfield matchConstraintPercentWidth : F
      //   1081: goto -> 2032
      //   1084: aload_0
      //   1085: aload_1
      //   1086: iload #6
      //   1088: aload_0
      //   1089: getfield matchConstraintMaxWidth : I
      //   1092: invokevirtual getDimensionPixelSize : (II)I
      //   1095: putfield matchConstraintMaxWidth : I
      //   1098: goto -> 2032
      //   1101: aload_1
      //   1102: iload #6
      //   1104: aload_0
      //   1105: getfield matchConstraintMaxWidth : I
      //   1108: invokevirtual getInt : (II)I
      //   1111: bipush #-2
      //   1113: if_icmpne -> 2032
      //   1116: aload_0
      //   1117: bipush #-2
      //   1119: putfield matchConstraintMaxWidth : I
      //   1122: goto -> 2032
      //   1125: aload_0
      //   1126: aload_1
      //   1127: iload #6
      //   1129: aload_0
      //   1130: getfield matchConstraintMinWidth : I
      //   1133: invokevirtual getDimensionPixelSize : (II)I
      //   1136: putfield matchConstraintMinWidth : I
      //   1139: goto -> 2032
      //   1142: aload_1
      //   1143: iload #6
      //   1145: aload_0
      //   1146: getfield matchConstraintMinWidth : I
      //   1149: invokevirtual getInt : (II)I
      //   1152: bipush #-2
      //   1154: if_icmpne -> 2032
      //   1157: aload_0
      //   1158: bipush #-2
      //   1160: putfield matchConstraintMinWidth : I
      //   1163: goto -> 2032
      //   1166: aload_0
      //   1167: aload_1
      //   1168: iload #6
      //   1170: iconst_0
      //   1171: invokevirtual getInt : (II)I
      //   1174: putfield matchConstraintDefaultHeight : I
      //   1177: aload_0
      //   1178: getfield matchConstraintDefaultHeight : I
      //   1181: iconst_1
      //   1182: if_icmpne -> 2032
      //   1185: ldc_w 'ConstraintLayout'
      //   1188: ldc_w 'layout_constraintHeight_default="wrap" is deprecated.\\nUse layout_height="WRAP_CONTENT" and layout_constrainedHeight="true" instead.'
      //   1191: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
      //   1194: pop
      //   1195: goto -> 2032
      //   1198: aload_0
      //   1199: aload_1
      //   1200: iload #6
      //   1202: iconst_0
      //   1203: invokevirtual getInt : (II)I
      //   1206: putfield matchConstraintDefaultWidth : I
      //   1209: aload_0
      //   1210: getfield matchConstraintDefaultWidth : I
      //   1213: iconst_1
      //   1214: if_icmpne -> 2032
      //   1217: ldc_w 'ConstraintLayout'
      //   1220: ldc_w 'layout_constraintWidth_default="wrap" is deprecated.\\nUse layout_width="WRAP_CONTENT" and layout_constrainedWidth="true" instead.'
      //   1223: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
      //   1226: pop
      //   1227: goto -> 2032
      //   1230: aload_0
      //   1231: aload_1
      //   1232: iload #6
      //   1234: aload_0
      //   1235: getfield verticalBias : F
      //   1238: invokevirtual getFloat : (IF)F
      //   1241: putfield verticalBias : F
      //   1244: goto -> 2032
      //   1247: aload_0
      //   1248: aload_1
      //   1249: iload #6
      //   1251: aload_0
      //   1252: getfield horizontalBias : F
      //   1255: invokevirtual getFloat : (IF)F
      //   1258: putfield horizontalBias : F
      //   1261: goto -> 2032
      //   1264: aload_0
      //   1265: aload_1
      //   1266: iload #6
      //   1268: aload_0
      //   1269: getfield constrainedHeight : Z
      //   1272: invokevirtual getBoolean : (IZ)Z
      //   1275: putfield constrainedHeight : Z
      //   1278: goto -> 2032
      //   1281: aload_0
      //   1282: aload_1
      //   1283: iload #6
      //   1285: aload_0
      //   1286: getfield constrainedWidth : Z
      //   1289: invokevirtual getBoolean : (IZ)Z
      //   1292: putfield constrainedWidth : Z
      //   1295: goto -> 2032
      //   1298: aload_0
      //   1299: aload_1
      //   1300: iload #6
      //   1302: aload_0
      //   1303: getfield goneEndMargin : I
      //   1306: invokevirtual getDimensionPixelSize : (II)I
      //   1309: putfield goneEndMargin : I
      //   1312: goto -> 2032
      //   1315: aload_0
      //   1316: aload_1
      //   1317: iload #6
      //   1319: aload_0
      //   1320: getfield goneStartMargin : I
      //   1323: invokevirtual getDimensionPixelSize : (II)I
      //   1326: putfield goneStartMargin : I
      //   1329: goto -> 2032
      //   1332: aload_0
      //   1333: aload_1
      //   1334: iload #6
      //   1336: aload_0
      //   1337: getfield goneBottomMargin : I
      //   1340: invokevirtual getDimensionPixelSize : (II)I
      //   1343: putfield goneBottomMargin : I
      //   1346: goto -> 2032
      //   1349: aload_0
      //   1350: aload_1
      //   1351: iload #6
      //   1353: aload_0
      //   1354: getfield goneRightMargin : I
      //   1357: invokevirtual getDimensionPixelSize : (II)I
      //   1360: putfield goneRightMargin : I
      //   1363: goto -> 2032
      //   1366: aload_0
      //   1367: aload_1
      //   1368: iload #6
      //   1370: aload_0
      //   1371: getfield goneTopMargin : I
      //   1374: invokevirtual getDimensionPixelSize : (II)I
      //   1377: putfield goneTopMargin : I
      //   1380: goto -> 2032
      //   1383: aload_0
      //   1384: aload_1
      //   1385: iload #6
      //   1387: aload_0
      //   1388: getfield goneLeftMargin : I
      //   1391: invokevirtual getDimensionPixelSize : (II)I
      //   1394: putfield goneLeftMargin : I
      //   1397: goto -> 2032
      //   1400: aload_0
      //   1401: aload_1
      //   1402: iload #6
      //   1404: aload_0
      //   1405: getfield endToEnd : I
      //   1408: invokevirtual getResourceId : (II)I
      //   1411: putfield endToEnd : I
      //   1414: aload_0
      //   1415: getfield endToEnd : I
      //   1418: iconst_m1
      //   1419: if_icmpne -> 2032
      //   1422: aload_0
      //   1423: aload_1
      //   1424: iload #6
      //   1426: iconst_m1
      //   1427: invokevirtual getInt : (II)I
      //   1430: putfield endToEnd : I
      //   1433: goto -> 2032
      //   1436: aload_0
      //   1437: aload_1
      //   1438: iload #6
      //   1440: aload_0
      //   1441: getfield endToStart : I
      //   1444: invokevirtual getResourceId : (II)I
      //   1447: putfield endToStart : I
      //   1450: aload_0
      //   1451: getfield endToStart : I
      //   1454: iconst_m1
      //   1455: if_icmpne -> 2032
      //   1458: aload_0
      //   1459: aload_1
      //   1460: iload #6
      //   1462: iconst_m1
      //   1463: invokevirtual getInt : (II)I
      //   1466: putfield endToStart : I
      //   1469: goto -> 2032
      //   1472: aload_0
      //   1473: aload_1
      //   1474: iload #6
      //   1476: aload_0
      //   1477: getfield startToStart : I
      //   1480: invokevirtual getResourceId : (II)I
      //   1483: putfield startToStart : I
      //   1486: aload_0
      //   1487: getfield startToStart : I
      //   1490: iconst_m1
      //   1491: if_icmpne -> 2032
      //   1494: aload_0
      //   1495: aload_1
      //   1496: iload #6
      //   1498: iconst_m1
      //   1499: invokevirtual getInt : (II)I
      //   1502: putfield startToStart : I
      //   1505: goto -> 2032
      //   1508: aload_0
      //   1509: aload_1
      //   1510: iload #6
      //   1512: aload_0
      //   1513: getfield startToEnd : I
      //   1516: invokevirtual getResourceId : (II)I
      //   1519: putfield startToEnd : I
      //   1522: aload_0
      //   1523: getfield startToEnd : I
      //   1526: iconst_m1
      //   1527: if_icmpne -> 2032
      //   1530: aload_0
      //   1531: aload_1
      //   1532: iload #6
      //   1534: iconst_m1
      //   1535: invokevirtual getInt : (II)I
      //   1538: putfield startToEnd : I
      //   1541: goto -> 2032
      //   1544: aload_0
      //   1545: aload_1
      //   1546: iload #6
      //   1548: aload_0
      //   1549: getfield baselineToBaseline : I
      //   1552: invokevirtual getResourceId : (II)I
      //   1555: putfield baselineToBaseline : I
      //   1558: aload_0
      //   1559: getfield baselineToBaseline : I
      //   1562: iconst_m1
      //   1563: if_icmpne -> 2032
      //   1566: aload_0
      //   1567: aload_1
      //   1568: iload #6
      //   1570: iconst_m1
      //   1571: invokevirtual getInt : (II)I
      //   1574: putfield baselineToBaseline : I
      //   1577: goto -> 2032
      //   1580: aload_0
      //   1581: aload_1
      //   1582: iload #6
      //   1584: aload_0
      //   1585: getfield bottomToBottom : I
      //   1588: invokevirtual getResourceId : (II)I
      //   1591: putfield bottomToBottom : I
      //   1594: aload_0
      //   1595: getfield bottomToBottom : I
      //   1598: iconst_m1
      //   1599: if_icmpne -> 2032
      //   1602: aload_0
      //   1603: aload_1
      //   1604: iload #6
      //   1606: iconst_m1
      //   1607: invokevirtual getInt : (II)I
      //   1610: putfield bottomToBottom : I
      //   1613: goto -> 2032
      //   1616: aload_0
      //   1617: aload_1
      //   1618: iload #6
      //   1620: aload_0
      //   1621: getfield bottomToTop : I
      //   1624: invokevirtual getResourceId : (II)I
      //   1627: putfield bottomToTop : I
      //   1630: aload_0
      //   1631: getfield bottomToTop : I
      //   1634: iconst_m1
      //   1635: if_icmpne -> 2032
      //   1638: aload_0
      //   1639: aload_1
      //   1640: iload #6
      //   1642: iconst_m1
      //   1643: invokevirtual getInt : (II)I
      //   1646: putfield bottomToTop : I
      //   1649: goto -> 2032
      //   1652: aload_0
      //   1653: aload_1
      //   1654: iload #6
      //   1656: aload_0
      //   1657: getfield topToBottom : I
      //   1660: invokevirtual getResourceId : (II)I
      //   1663: putfield topToBottom : I
      //   1666: aload_0
      //   1667: getfield topToBottom : I
      //   1670: iconst_m1
      //   1671: if_icmpne -> 2032
      //   1674: aload_0
      //   1675: aload_1
      //   1676: iload #6
      //   1678: iconst_m1
      //   1679: invokevirtual getInt : (II)I
      //   1682: putfield topToBottom : I
      //   1685: goto -> 2032
      //   1688: aload_0
      //   1689: aload_1
      //   1690: iload #6
      //   1692: aload_0
      //   1693: getfield topToTop : I
      //   1696: invokevirtual getResourceId : (II)I
      //   1699: putfield topToTop : I
      //   1702: aload_0
      //   1703: getfield topToTop : I
      //   1706: iconst_m1
      //   1707: if_icmpne -> 2032
      //   1710: aload_0
      //   1711: aload_1
      //   1712: iload #6
      //   1714: iconst_m1
      //   1715: invokevirtual getInt : (II)I
      //   1718: putfield topToTop : I
      //   1721: goto -> 2032
      //   1724: aload_0
      //   1725: aload_1
      //   1726: iload #6
      //   1728: aload_0
      //   1729: getfield rightToRight : I
      //   1732: invokevirtual getResourceId : (II)I
      //   1735: putfield rightToRight : I
      //   1738: aload_0
      //   1739: getfield rightToRight : I
      //   1742: iconst_m1
      //   1743: if_icmpne -> 2032
      //   1746: aload_0
      //   1747: aload_1
      //   1748: iload #6
      //   1750: iconst_m1
      //   1751: invokevirtual getInt : (II)I
      //   1754: putfield rightToRight : I
      //   1757: goto -> 2032
      //   1760: aload_0
      //   1761: aload_1
      //   1762: iload #6
      //   1764: aload_0
      //   1765: getfield rightToLeft : I
      //   1768: invokevirtual getResourceId : (II)I
      //   1771: putfield rightToLeft : I
      //   1774: aload_0
      //   1775: getfield rightToLeft : I
      //   1778: iconst_m1
      //   1779: if_icmpne -> 2032
      //   1782: aload_0
      //   1783: aload_1
      //   1784: iload #6
      //   1786: iconst_m1
      //   1787: invokevirtual getInt : (II)I
      //   1790: putfield rightToLeft : I
      //   1793: goto -> 2032
      //   1796: aload_0
      //   1797: aload_1
      //   1798: iload #6
      //   1800: aload_0
      //   1801: getfield leftToRight : I
      //   1804: invokevirtual getResourceId : (II)I
      //   1807: putfield leftToRight : I
      //   1810: aload_0
      //   1811: getfield leftToRight : I
      //   1814: iconst_m1
      //   1815: if_icmpne -> 2032
      //   1818: aload_0
      //   1819: aload_1
      //   1820: iload #6
      //   1822: iconst_m1
      //   1823: invokevirtual getInt : (II)I
      //   1826: putfield leftToRight : I
      //   1829: goto -> 2032
      //   1832: aload_0
      //   1833: aload_1
      //   1834: iload #6
      //   1836: aload_0
      //   1837: getfield leftToLeft : I
      //   1840: invokevirtual getResourceId : (II)I
      //   1843: putfield leftToLeft : I
      //   1846: aload_0
      //   1847: getfield leftToLeft : I
      //   1850: iconst_m1
      //   1851: if_icmpne -> 2032
      //   1854: aload_0
      //   1855: aload_1
      //   1856: iload #6
      //   1858: iconst_m1
      //   1859: invokevirtual getInt : (II)I
      //   1862: putfield leftToLeft : I
      //   1865: goto -> 2032
      //   1868: aload_0
      //   1869: aload_1
      //   1870: iload #6
      //   1872: aload_0
      //   1873: getfield guidePercent : F
      //   1876: invokevirtual getFloat : (IF)F
      //   1879: putfield guidePercent : F
      //   1882: goto -> 2032
      //   1885: aload_0
      //   1886: aload_1
      //   1887: iload #6
      //   1889: aload_0
      //   1890: getfield guideEnd : I
      //   1893: invokevirtual getDimensionPixelOffset : (II)I
      //   1896: putfield guideEnd : I
      //   1899: goto -> 2032
      //   1902: aload_0
      //   1903: aload_1
      //   1904: iload #6
      //   1906: aload_0
      //   1907: getfield guideBegin : I
      //   1910: invokevirtual getDimensionPixelOffset : (II)I
      //   1913: putfield guideBegin : I
      //   1916: goto -> 2032
      //   1919: aload_0
      //   1920: aload_1
      //   1921: iload #6
      //   1923: aload_0
      //   1924: getfield circleAngle : F
      //   1927: invokevirtual getFloat : (IF)F
      //   1930: ldc_w 360.0
      //   1933: frem
      //   1934: putfield circleAngle : F
      //   1937: aload_0
      //   1938: getfield circleAngle : F
      //   1941: fconst_0
      //   1942: fcmpg
      //   1943: ifge -> 2032
      //   1946: aload_0
      //   1947: ldc_w 360.0
      //   1950: aload_0
      //   1951: getfield circleAngle : F
      //   1954: fsub
      //   1955: ldc_w 360.0
      //   1958: frem
      //   1959: putfield circleAngle : F
      //   1962: goto -> 2032
      //   1965: aload_0
      //   1966: aload_1
      //   1967: iload #6
      //   1969: aload_0
      //   1970: getfield circleRadius : I
      //   1973: invokevirtual getDimensionPixelSize : (II)I
      //   1976: putfield circleRadius : I
      //   1979: goto -> 2032
      //   1982: aload_0
      //   1983: aload_1
      //   1984: iload #6
      //   1986: aload_0
      //   1987: getfield circleConstraint : I
      //   1990: invokevirtual getResourceId : (II)I
      //   1993: putfield circleConstraint : I
      //   1996: aload_0
      //   1997: getfield circleConstraint : I
      //   2000: iconst_m1
      //   2001: if_icmpne -> 2032
      //   2004: aload_0
      //   2005: aload_1
      //   2006: iload #6
      //   2008: iconst_m1
      //   2009: invokevirtual getInt : (II)I
      //   2012: putfield circleConstraint : I
      //   2015: goto -> 2032
      //   2018: aload_0
      //   2019: aload_1
      //   2020: iload #6
      //   2022: aload_0
      //   2023: getfield orientation : I
      //   2026: invokevirtual getInt : (II)I
      //   2029: putfield orientation : I
      //   2032: iload #5
      //   2034: iconst_1
      //   2035: iadd
      //   2036: istore #5
      //   2038: goto -> 346
      //   2041: aload_1
      //   2042: invokevirtual recycle : ()V
      //   2045: aload_0
      //   2046: invokevirtual validate : ()V
      //   2049: return
      //   2050: astore_2
      //   2051: goto -> 2032
      //   2054: astore_2
      //   2055: goto -> 998
      //   2058: astore_2
      //   2059: goto -> 1039
      //   2062: astore_2
      //   2063: goto -> 1101
      //   2066: astore_2
      //   2067: goto -> 1142
      // Exception table:
      //   from	to	target	type
      //   871	883	2050	java/lang/NumberFormatException
      //   896	915	2050	java/lang/NumberFormatException
      //   918	929	2050	java/lang/NumberFormatException
      //   949	957	2050	java/lang/NumberFormatException
      //   981	995	2054	java/lang/Exception
      //   1022	1036	2058	java/lang/Exception
      //   1084	1098	2062	java/lang/Exception
      //   1125	1139	2066	java/lang/Exception
    }
    
    public LayoutParams(LayoutParams param1LayoutParams) {
      super(param1LayoutParams);
      this.guideBegin = param1LayoutParams.guideBegin;
      this.guideEnd = param1LayoutParams.guideEnd;
      this.guidePercent = param1LayoutParams.guidePercent;
      this.leftToLeft = param1LayoutParams.leftToLeft;
      this.leftToRight = param1LayoutParams.leftToRight;
      this.rightToLeft = param1LayoutParams.rightToLeft;
      this.rightToRight = param1LayoutParams.rightToRight;
      this.topToTop = param1LayoutParams.topToTop;
      this.topToBottom = param1LayoutParams.topToBottom;
      this.bottomToTop = param1LayoutParams.bottomToTop;
      this.bottomToBottom = param1LayoutParams.bottomToBottom;
      this.baselineToBaseline = param1LayoutParams.baselineToBaseline;
      this.circleConstraint = param1LayoutParams.circleConstraint;
      this.circleRadius = param1LayoutParams.circleRadius;
      this.circleAngle = param1LayoutParams.circleAngle;
      this.startToEnd = param1LayoutParams.startToEnd;
      this.startToStart = param1LayoutParams.startToStart;
      this.endToStart = param1LayoutParams.endToStart;
      this.endToEnd = param1LayoutParams.endToEnd;
      this.goneLeftMargin = param1LayoutParams.goneLeftMargin;
      this.goneTopMargin = param1LayoutParams.goneTopMargin;
      this.goneRightMargin = param1LayoutParams.goneRightMargin;
      this.goneBottomMargin = param1LayoutParams.goneBottomMargin;
      this.goneStartMargin = param1LayoutParams.goneStartMargin;
      this.goneEndMargin = param1LayoutParams.goneEndMargin;
      this.horizontalBias = param1LayoutParams.horizontalBias;
      this.verticalBias = param1LayoutParams.verticalBias;
      this.dimensionRatio = param1LayoutParams.dimensionRatio;
      this.dimensionRatioValue = param1LayoutParams.dimensionRatioValue;
      this.dimensionRatioSide = param1LayoutParams.dimensionRatioSide;
      this.horizontalWeight = param1LayoutParams.horizontalWeight;
      this.verticalWeight = param1LayoutParams.verticalWeight;
      this.horizontalChainStyle = param1LayoutParams.horizontalChainStyle;
      this.verticalChainStyle = param1LayoutParams.verticalChainStyle;
      this.constrainedWidth = param1LayoutParams.constrainedWidth;
      this.constrainedHeight = param1LayoutParams.constrainedHeight;
      this.matchConstraintDefaultWidth = param1LayoutParams.matchConstraintDefaultWidth;
      this.matchConstraintDefaultHeight = param1LayoutParams.matchConstraintDefaultHeight;
      this.matchConstraintMinWidth = param1LayoutParams.matchConstraintMinWidth;
      this.matchConstraintMaxWidth = param1LayoutParams.matchConstraintMaxWidth;
      this.matchConstraintMinHeight = param1LayoutParams.matchConstraintMinHeight;
      this.matchConstraintMaxHeight = param1LayoutParams.matchConstraintMaxHeight;
      this.matchConstraintPercentWidth = param1LayoutParams.matchConstraintPercentWidth;
      this.matchConstraintPercentHeight = param1LayoutParams.matchConstraintPercentHeight;
      this.editorAbsoluteX = param1LayoutParams.editorAbsoluteX;
      this.editorAbsoluteY = param1LayoutParams.editorAbsoluteY;
      this.orientation = param1LayoutParams.orientation;
      this.horizontalDimensionFixed = param1LayoutParams.horizontalDimensionFixed;
      this.verticalDimensionFixed = param1LayoutParams.verticalDimensionFixed;
      this.needsBaseline = param1LayoutParams.needsBaseline;
      this.isGuideline = param1LayoutParams.isGuideline;
      this.resolvedLeftToLeft = param1LayoutParams.resolvedLeftToLeft;
      this.resolvedLeftToRight = param1LayoutParams.resolvedLeftToRight;
      this.resolvedRightToLeft = param1LayoutParams.resolvedRightToLeft;
      this.resolvedRightToRight = param1LayoutParams.resolvedRightToRight;
      this.resolveGoneLeftMargin = param1LayoutParams.resolveGoneLeftMargin;
      this.resolveGoneRightMargin = param1LayoutParams.resolveGoneRightMargin;
      this.resolvedHorizontalBias = param1LayoutParams.resolvedHorizontalBias;
      this.widget = param1LayoutParams.widget;
    }
    
    public LayoutParams(ViewGroup.LayoutParams param1LayoutParams) {
      super(param1LayoutParams);
    }
    
    public void reset() {
      if (this.widget != null)
        this.widget.reset(); 
    }
    
    @TargetApi(17)
    public void resolveLayoutDirection(int param1Int) {
      // Byte code:
      //   0: aload_0
      //   1: getfield leftMargin : I
      //   4: istore_3
      //   5: aload_0
      //   6: getfield rightMargin : I
      //   9: istore #4
      //   11: aload_0
      //   12: iload_1
      //   13: invokespecial resolveLayoutDirection : (I)V
      //   16: aload_0
      //   17: iconst_m1
      //   18: putfield resolvedRightToLeft : I
      //   21: aload_0
      //   22: iconst_m1
      //   23: putfield resolvedRightToRight : I
      //   26: aload_0
      //   27: iconst_m1
      //   28: putfield resolvedLeftToLeft : I
      //   31: aload_0
      //   32: iconst_m1
      //   33: putfield resolvedLeftToRight : I
      //   36: aload_0
      //   37: iconst_m1
      //   38: putfield resolveGoneLeftMargin : I
      //   41: aload_0
      //   42: iconst_m1
      //   43: putfield resolveGoneRightMargin : I
      //   46: aload_0
      //   47: aload_0
      //   48: getfield goneLeftMargin : I
      //   51: putfield resolveGoneLeftMargin : I
      //   54: aload_0
      //   55: aload_0
      //   56: getfield goneRightMargin : I
      //   59: putfield resolveGoneRightMargin : I
      //   62: aload_0
      //   63: aload_0
      //   64: getfield horizontalBias : F
      //   67: putfield resolvedHorizontalBias : F
      //   70: aload_0
      //   71: aload_0
      //   72: getfield guideBegin : I
      //   75: putfield resolvedGuideBegin : I
      //   78: aload_0
      //   79: aload_0
      //   80: getfield guideEnd : I
      //   83: putfield resolvedGuideEnd : I
      //   86: aload_0
      //   87: aload_0
      //   88: getfield guidePercent : F
      //   91: putfield resolvedGuidePercent : F
      //   94: aload_0
      //   95: invokevirtual getLayoutDirection : ()I
      //   98: istore_1
      //   99: iconst_0
      //   100: istore_2
      //   101: iconst_1
      //   102: iload_1
      //   103: if_icmpne -> 111
      //   106: iconst_1
      //   107: istore_1
      //   108: goto -> 113
      //   111: iconst_0
      //   112: istore_1
      //   113: iload_1
      //   114: ifeq -> 349
      //   117: aload_0
      //   118: getfield startToEnd : I
      //   121: iconst_m1
      //   122: if_icmpeq -> 138
      //   125: aload_0
      //   126: aload_0
      //   127: getfield startToEnd : I
      //   130: putfield resolvedRightToLeft : I
      //   133: iconst_1
      //   134: istore_1
      //   135: goto -> 159
      //   138: iload_2
      //   139: istore_1
      //   140: aload_0
      //   141: getfield startToStart : I
      //   144: iconst_m1
      //   145: if_icmpeq -> 159
      //   148: aload_0
      //   149: aload_0
      //   150: getfield startToStart : I
      //   153: putfield resolvedRightToRight : I
      //   156: goto -> 133
      //   159: aload_0
      //   160: getfield endToStart : I
      //   163: iconst_m1
      //   164: if_icmpeq -> 177
      //   167: aload_0
      //   168: aload_0
      //   169: getfield endToStart : I
      //   172: putfield resolvedLeftToRight : I
      //   175: iconst_1
      //   176: istore_1
      //   177: aload_0
      //   178: getfield endToEnd : I
      //   181: iconst_m1
      //   182: if_icmpeq -> 195
      //   185: aload_0
      //   186: aload_0
      //   187: getfield endToEnd : I
      //   190: putfield resolvedLeftToLeft : I
      //   193: iconst_1
      //   194: istore_1
      //   195: aload_0
      //   196: getfield goneStartMargin : I
      //   199: iconst_m1
      //   200: if_icmpeq -> 211
      //   203: aload_0
      //   204: aload_0
      //   205: getfield goneStartMargin : I
      //   208: putfield resolveGoneRightMargin : I
      //   211: aload_0
      //   212: getfield goneEndMargin : I
      //   215: iconst_m1
      //   216: if_icmpeq -> 227
      //   219: aload_0
      //   220: aload_0
      //   221: getfield goneEndMargin : I
      //   224: putfield resolveGoneLeftMargin : I
      //   227: iload_1
      //   228: ifeq -> 241
      //   231: aload_0
      //   232: fconst_1
      //   233: aload_0
      //   234: getfield horizontalBias : F
      //   237: fsub
      //   238: putfield resolvedHorizontalBias : F
      //   241: aload_0
      //   242: getfield isGuideline : Z
      //   245: ifeq -> 445
      //   248: aload_0
      //   249: getfield orientation : I
      //   252: iconst_1
      //   253: if_icmpne -> 445
      //   256: aload_0
      //   257: getfield guidePercent : F
      //   260: ldc -1.0
      //   262: fcmpl
      //   263: ifeq -> 289
      //   266: aload_0
      //   267: fconst_1
      //   268: aload_0
      //   269: getfield guidePercent : F
      //   272: fsub
      //   273: putfield resolvedGuidePercent : F
      //   276: aload_0
      //   277: iconst_m1
      //   278: putfield resolvedGuideBegin : I
      //   281: aload_0
      //   282: iconst_m1
      //   283: putfield resolvedGuideEnd : I
      //   286: goto -> 445
      //   289: aload_0
      //   290: getfield guideBegin : I
      //   293: iconst_m1
      //   294: if_icmpeq -> 319
      //   297: aload_0
      //   298: aload_0
      //   299: getfield guideBegin : I
      //   302: putfield resolvedGuideEnd : I
      //   305: aload_0
      //   306: iconst_m1
      //   307: putfield resolvedGuideBegin : I
      //   310: aload_0
      //   311: ldc -1.0
      //   313: putfield resolvedGuidePercent : F
      //   316: goto -> 445
      //   319: aload_0
      //   320: getfield guideEnd : I
      //   323: iconst_m1
      //   324: if_icmpeq -> 445
      //   327: aload_0
      //   328: aload_0
      //   329: getfield guideEnd : I
      //   332: putfield resolvedGuideBegin : I
      //   335: aload_0
      //   336: iconst_m1
      //   337: putfield resolvedGuideEnd : I
      //   340: aload_0
      //   341: ldc -1.0
      //   343: putfield resolvedGuidePercent : F
      //   346: goto -> 445
      //   349: aload_0
      //   350: getfield startToEnd : I
      //   353: iconst_m1
      //   354: if_icmpeq -> 365
      //   357: aload_0
      //   358: aload_0
      //   359: getfield startToEnd : I
      //   362: putfield resolvedLeftToRight : I
      //   365: aload_0
      //   366: getfield startToStart : I
      //   369: iconst_m1
      //   370: if_icmpeq -> 381
      //   373: aload_0
      //   374: aload_0
      //   375: getfield startToStart : I
      //   378: putfield resolvedLeftToLeft : I
      //   381: aload_0
      //   382: getfield endToStart : I
      //   385: iconst_m1
      //   386: if_icmpeq -> 397
      //   389: aload_0
      //   390: aload_0
      //   391: getfield endToStart : I
      //   394: putfield resolvedRightToLeft : I
      //   397: aload_0
      //   398: getfield endToEnd : I
      //   401: iconst_m1
      //   402: if_icmpeq -> 413
      //   405: aload_0
      //   406: aload_0
      //   407: getfield endToEnd : I
      //   410: putfield resolvedRightToRight : I
      //   413: aload_0
      //   414: getfield goneStartMargin : I
      //   417: iconst_m1
      //   418: if_icmpeq -> 429
      //   421: aload_0
      //   422: aload_0
      //   423: getfield goneStartMargin : I
      //   426: putfield resolveGoneLeftMargin : I
      //   429: aload_0
      //   430: getfield goneEndMargin : I
      //   433: iconst_m1
      //   434: if_icmpeq -> 445
      //   437: aload_0
      //   438: aload_0
      //   439: getfield goneEndMargin : I
      //   442: putfield resolveGoneRightMargin : I
      //   445: aload_0
      //   446: getfield endToStart : I
      //   449: iconst_m1
      //   450: if_icmpne -> 613
      //   453: aload_0
      //   454: getfield endToEnd : I
      //   457: iconst_m1
      //   458: if_icmpne -> 613
      //   461: aload_0
      //   462: getfield startToStart : I
      //   465: iconst_m1
      //   466: if_icmpne -> 613
      //   469: aload_0
      //   470: getfield startToEnd : I
      //   473: iconst_m1
      //   474: if_icmpne -> 613
      //   477: aload_0
      //   478: getfield rightToLeft : I
      //   481: iconst_m1
      //   482: if_icmpeq -> 514
      //   485: aload_0
      //   486: aload_0
      //   487: getfield rightToLeft : I
      //   490: putfield resolvedRightToLeft : I
      //   493: aload_0
      //   494: getfield rightMargin : I
      //   497: ifgt -> 548
      //   500: iload #4
      //   502: ifle -> 548
      //   505: aload_0
      //   506: iload #4
      //   508: putfield rightMargin : I
      //   511: goto -> 548
      //   514: aload_0
      //   515: getfield rightToRight : I
      //   518: iconst_m1
      //   519: if_icmpeq -> 548
      //   522: aload_0
      //   523: aload_0
      //   524: getfield rightToRight : I
      //   527: putfield resolvedRightToRight : I
      //   530: aload_0
      //   531: getfield rightMargin : I
      //   534: ifgt -> 548
      //   537: iload #4
      //   539: ifle -> 548
      //   542: aload_0
      //   543: iload #4
      //   545: putfield rightMargin : I
      //   548: aload_0
      //   549: getfield leftToLeft : I
      //   552: iconst_m1
      //   553: if_icmpeq -> 581
      //   556: aload_0
      //   557: aload_0
      //   558: getfield leftToLeft : I
      //   561: putfield resolvedLeftToLeft : I
      //   564: aload_0
      //   565: getfield leftMargin : I
      //   568: ifgt -> 613
      //   571: iload_3
      //   572: ifle -> 613
      //   575: aload_0
      //   576: iload_3
      //   577: putfield leftMargin : I
      //   580: return
      //   581: aload_0
      //   582: getfield leftToRight : I
      //   585: iconst_m1
      //   586: if_icmpeq -> 613
      //   589: aload_0
      //   590: aload_0
      //   591: getfield leftToRight : I
      //   594: putfield resolvedLeftToRight : I
      //   597: aload_0
      //   598: getfield leftMargin : I
      //   601: ifgt -> 613
      //   604: iload_3
      //   605: ifle -> 613
      //   608: aload_0
      //   609: iload_3
      //   610: putfield leftMargin : I
      //   613: return
    }
    
    public void validate() {
      this.isGuideline = false;
      this.horizontalDimensionFixed = true;
      this.verticalDimensionFixed = true;
      if (this.width == -2 && this.constrainedWidth) {
        this.horizontalDimensionFixed = false;
        this.matchConstraintDefaultWidth = 1;
      } 
      if (this.height == -2 && this.constrainedHeight) {
        this.verticalDimensionFixed = false;
        this.matchConstraintDefaultHeight = 1;
      } 
      if (this.width == 0 || this.width == -1) {
        this.horizontalDimensionFixed = false;
        if (this.width == 0 && this.matchConstraintDefaultWidth == 1) {
          this.width = -2;
          this.constrainedWidth = true;
        } 
      } 
      if (this.height == 0 || this.height == -1) {
        this.verticalDimensionFixed = false;
        if (this.height == 0 && this.matchConstraintDefaultHeight == 1) {
          this.height = -2;
          this.constrainedHeight = true;
        } 
      } 
      if (this.guidePercent != -1.0F || this.guideBegin != -1 || this.guideEnd != -1) {
        this.isGuideline = true;
        this.horizontalDimensionFixed = true;
        this.verticalDimensionFixed = true;
        if (!(this.widget instanceof Guideline))
          this.widget = (ConstraintWidget)new Guideline(); 
        ((Guideline)this.widget).setOrientation(this.orientation);
      } 
    }
    
    private static class Table {
      public static final int ANDROID_ORIENTATION = 1;
      
      public static final int LAYOUT_CONSTRAINED_HEIGHT = 28;
      
      public static final int LAYOUT_CONSTRAINED_WIDTH = 27;
      
      public static final int LAYOUT_CONSTRAINT_BASELINE_CREATOR = 43;
      
      public static final int LAYOUT_CONSTRAINT_BASELINE_TO_BASELINE_OF = 16;
      
      public static final int LAYOUT_CONSTRAINT_BOTTOM_CREATOR = 42;
      
      public static final int LAYOUT_CONSTRAINT_BOTTOM_TO_BOTTOM_OF = 15;
      
      public static final int LAYOUT_CONSTRAINT_BOTTOM_TO_TOP_OF = 14;
      
      public static final int LAYOUT_CONSTRAINT_CIRCLE = 2;
      
      public static final int LAYOUT_CONSTRAINT_CIRCLE_ANGLE = 4;
      
      public static final int LAYOUT_CONSTRAINT_CIRCLE_RADIUS = 3;
      
      public static final int LAYOUT_CONSTRAINT_DIMENSION_RATIO = 44;
      
      public static final int LAYOUT_CONSTRAINT_END_TO_END_OF = 20;
      
      public static final int LAYOUT_CONSTRAINT_END_TO_START_OF = 19;
      
      public static final int LAYOUT_CONSTRAINT_GUIDE_BEGIN = 5;
      
      public static final int LAYOUT_CONSTRAINT_GUIDE_END = 6;
      
      public static final int LAYOUT_CONSTRAINT_GUIDE_PERCENT = 7;
      
      public static final int LAYOUT_CONSTRAINT_HEIGHT_DEFAULT = 32;
      
      public static final int LAYOUT_CONSTRAINT_HEIGHT_MAX = 37;
      
      public static final int LAYOUT_CONSTRAINT_HEIGHT_MIN = 36;
      
      public static final int LAYOUT_CONSTRAINT_HEIGHT_PERCENT = 38;
      
      public static final int LAYOUT_CONSTRAINT_HORIZONTAL_BIAS = 29;
      
      public static final int LAYOUT_CONSTRAINT_HORIZONTAL_CHAINSTYLE = 47;
      
      public static final int LAYOUT_CONSTRAINT_HORIZONTAL_WEIGHT = 45;
      
      public static final int LAYOUT_CONSTRAINT_LEFT_CREATOR = 39;
      
      public static final int LAYOUT_CONSTRAINT_LEFT_TO_LEFT_OF = 8;
      
      public static final int LAYOUT_CONSTRAINT_LEFT_TO_RIGHT_OF = 9;
      
      public static final int LAYOUT_CONSTRAINT_RIGHT_CREATOR = 41;
      
      public static final int LAYOUT_CONSTRAINT_RIGHT_TO_LEFT_OF = 10;
      
      public static final int LAYOUT_CONSTRAINT_RIGHT_TO_RIGHT_OF = 11;
      
      public static final int LAYOUT_CONSTRAINT_START_TO_END_OF = 17;
      
      public static final int LAYOUT_CONSTRAINT_START_TO_START_OF = 18;
      
      public static final int LAYOUT_CONSTRAINT_TOP_CREATOR = 40;
      
      public static final int LAYOUT_CONSTRAINT_TOP_TO_BOTTOM_OF = 13;
      
      public static final int LAYOUT_CONSTRAINT_TOP_TO_TOP_OF = 12;
      
      public static final int LAYOUT_CONSTRAINT_VERTICAL_BIAS = 30;
      
      public static final int LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE = 48;
      
      public static final int LAYOUT_CONSTRAINT_VERTICAL_WEIGHT = 46;
      
      public static final int LAYOUT_CONSTRAINT_WIDTH_DEFAULT = 31;
      
      public static final int LAYOUT_CONSTRAINT_WIDTH_MAX = 34;
      
      public static final int LAYOUT_CONSTRAINT_WIDTH_MIN = 33;
      
      public static final int LAYOUT_CONSTRAINT_WIDTH_PERCENT = 35;
      
      public static final int LAYOUT_EDITOR_ABSOLUTEX = 49;
      
      public static final int LAYOUT_EDITOR_ABSOLUTEY = 50;
      
      public static final int LAYOUT_GONE_MARGIN_BOTTOM = 24;
      
      public static final int LAYOUT_GONE_MARGIN_END = 26;
      
      public static final int LAYOUT_GONE_MARGIN_LEFT = 21;
      
      public static final int LAYOUT_GONE_MARGIN_RIGHT = 23;
      
      public static final int LAYOUT_GONE_MARGIN_START = 25;
      
      public static final int LAYOUT_GONE_MARGIN_TOP = 22;
      
      public static final int UNUSED = 0;
      
      public static final SparseIntArray map = new SparseIntArray();
      
      static {
        map.append(R.styleable.ConstraintLayout_Layout_layout_constraintLeft_toLeftOf, 8);
        map.append(R.styleable.ConstraintLayout_Layout_layout_constraintLeft_toRightOf, 9);
        map.append(R.styleable.ConstraintLayout_Layout_layout_constraintRight_toLeftOf, 10);
        map.append(R.styleable.ConstraintLayout_Layout_layout_constraintRight_toRightOf, 11);
        map.append(R.styleable.ConstraintLayout_Layout_layout_constraintTop_toTopOf, 12);
        map.append(R.styleable.ConstraintLayout_Layout_layout_constraintTop_toBottomOf, 13);
        map.append(R.styleable.ConstraintLayout_Layout_layout_constraintBottom_toTopOf, 14);
        map.append(R.styleable.ConstraintLayout_Layout_layout_constraintBottom_toBottomOf, 15);
        map.append(R.styleable.ConstraintLayout_Layout_layout_constraintBaseline_toBaselineOf, 16);
        map.append(R.styleable.ConstraintLayout_Layout_layout_constraintCircle, 2);
        map.append(R.styleable.ConstraintLayout_Layout_layout_constraintCircleRadius, 3);
        map.append(R.styleable.ConstraintLayout_Layout_layout_constraintCircleAngle, 4);
        map.append(R.styleable.ConstraintLayout_Layout_layout_editor_absoluteX, 49);
        map.append(R.styleable.ConstraintLayout_Layout_layout_editor_absoluteY, 50);
        map.append(R.styleable.ConstraintLayout_Layout_layout_constraintGuide_begin, 5);
        map.append(R.styleable.ConstraintLayout_Layout_layout_constraintGuide_end, 6);
        map.append(R.styleable.ConstraintLayout_Layout_layout_constraintGuide_percent, 7);
        map.append(R.styleable.ConstraintLayout_Layout_android_orientation, 1);
        map.append(R.styleable.ConstraintLayout_Layout_layout_constraintStart_toEndOf, 17);
        map.append(R.styleable.ConstraintLayout_Layout_layout_constraintStart_toStartOf, 18);
        map.append(R.styleable.ConstraintLayout_Layout_layout_constraintEnd_toStartOf, 19);
        map.append(R.styleable.ConstraintLayout_Layout_layout_constraintEnd_toEndOf, 20);
        map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginLeft, 21);
        map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginTop, 22);
        map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginRight, 23);
        map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginBottom, 24);
        map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginStart, 25);
        map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginEnd, 26);
        map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_bias, 29);
        map.append(R.styleable.ConstraintLayout_Layout_layout_constraintVertical_bias, 30);
        map.append(R.styleable.ConstraintLayout_Layout_layout_constraintDimensionRatio, 44);
        map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_weight, 45);
        map.append(R.styleable.ConstraintLayout_Layout_layout_constraintVertical_weight, 46);
        map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_chainStyle, 47);
        map.append(R.styleable.ConstraintLayout_Layout_layout_constraintVertical_chainStyle, 48);
        map.append(R.styleable.ConstraintLayout_Layout_layout_constrainedWidth, 27);
        map.append(R.styleable.ConstraintLayout_Layout_layout_constrainedHeight, 28);
        map.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_default, 31);
        map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_default, 32);
        map.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_min, 33);
        map.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_max, 34);
        map.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_percent, 35);
        map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_min, 36);
        map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_max, 37);
        map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_percent, 38);
        map.append(R.styleable.ConstraintLayout_Layout_layout_constraintLeft_creator, 39);
        map.append(R.styleable.ConstraintLayout_Layout_layout_constraintTop_creator, 40);
        map.append(R.styleable.ConstraintLayout_Layout_layout_constraintRight_creator, 41);
        map.append(R.styleable.ConstraintLayout_Layout_layout_constraintBottom_creator, 42);
        map.append(R.styleable.ConstraintLayout_Layout_layout_constraintBaseline_creator, 43);
      }
    }
  }
  
  private static class Table {
    public static final int ANDROID_ORIENTATION = 1;
    
    public static final int LAYOUT_CONSTRAINED_HEIGHT = 28;
    
    public static final int LAYOUT_CONSTRAINED_WIDTH = 27;
    
    public static final int LAYOUT_CONSTRAINT_BASELINE_CREATOR = 43;
    
    public static final int LAYOUT_CONSTRAINT_BASELINE_TO_BASELINE_OF = 16;
    
    public static final int LAYOUT_CONSTRAINT_BOTTOM_CREATOR = 42;
    
    public static final int LAYOUT_CONSTRAINT_BOTTOM_TO_BOTTOM_OF = 15;
    
    public static final int LAYOUT_CONSTRAINT_BOTTOM_TO_TOP_OF = 14;
    
    public static final int LAYOUT_CONSTRAINT_CIRCLE = 2;
    
    public static final int LAYOUT_CONSTRAINT_CIRCLE_ANGLE = 4;
    
    public static final int LAYOUT_CONSTRAINT_CIRCLE_RADIUS = 3;
    
    public static final int LAYOUT_CONSTRAINT_DIMENSION_RATIO = 44;
    
    public static final int LAYOUT_CONSTRAINT_END_TO_END_OF = 20;
    
    public static final int LAYOUT_CONSTRAINT_END_TO_START_OF = 19;
    
    public static final int LAYOUT_CONSTRAINT_GUIDE_BEGIN = 5;
    
    public static final int LAYOUT_CONSTRAINT_GUIDE_END = 6;
    
    public static final int LAYOUT_CONSTRAINT_GUIDE_PERCENT = 7;
    
    public static final int LAYOUT_CONSTRAINT_HEIGHT_DEFAULT = 32;
    
    public static final int LAYOUT_CONSTRAINT_HEIGHT_MAX = 37;
    
    public static final int LAYOUT_CONSTRAINT_HEIGHT_MIN = 36;
    
    public static final int LAYOUT_CONSTRAINT_HEIGHT_PERCENT = 38;
    
    public static final int LAYOUT_CONSTRAINT_HORIZONTAL_BIAS = 29;
    
    public static final int LAYOUT_CONSTRAINT_HORIZONTAL_CHAINSTYLE = 47;
    
    public static final int LAYOUT_CONSTRAINT_HORIZONTAL_WEIGHT = 45;
    
    public static final int LAYOUT_CONSTRAINT_LEFT_CREATOR = 39;
    
    public static final int LAYOUT_CONSTRAINT_LEFT_TO_LEFT_OF = 8;
    
    public static final int LAYOUT_CONSTRAINT_LEFT_TO_RIGHT_OF = 9;
    
    public static final int LAYOUT_CONSTRAINT_RIGHT_CREATOR = 41;
    
    public static final int LAYOUT_CONSTRAINT_RIGHT_TO_LEFT_OF = 10;
    
    public static final int LAYOUT_CONSTRAINT_RIGHT_TO_RIGHT_OF = 11;
    
    public static final int LAYOUT_CONSTRAINT_START_TO_END_OF = 17;
    
    public static final int LAYOUT_CONSTRAINT_START_TO_START_OF = 18;
    
    public static final int LAYOUT_CONSTRAINT_TOP_CREATOR = 40;
    
    public static final int LAYOUT_CONSTRAINT_TOP_TO_BOTTOM_OF = 13;
    
    public static final int LAYOUT_CONSTRAINT_TOP_TO_TOP_OF = 12;
    
    public static final int LAYOUT_CONSTRAINT_VERTICAL_BIAS = 30;
    
    public static final int LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE = 48;
    
    public static final int LAYOUT_CONSTRAINT_VERTICAL_WEIGHT = 46;
    
    public static final int LAYOUT_CONSTRAINT_WIDTH_DEFAULT = 31;
    
    public static final int LAYOUT_CONSTRAINT_WIDTH_MAX = 34;
    
    public static final int LAYOUT_CONSTRAINT_WIDTH_MIN = 33;
    
    public static final int LAYOUT_CONSTRAINT_WIDTH_PERCENT = 35;
    
    public static final int LAYOUT_EDITOR_ABSOLUTEX = 49;
    
    public static final int LAYOUT_EDITOR_ABSOLUTEY = 50;
    
    public static final int LAYOUT_GONE_MARGIN_BOTTOM = 24;
    
    public static final int LAYOUT_GONE_MARGIN_END = 26;
    
    public static final int LAYOUT_GONE_MARGIN_LEFT = 21;
    
    public static final int LAYOUT_GONE_MARGIN_RIGHT = 23;
    
    public static final int LAYOUT_GONE_MARGIN_START = 25;
    
    public static final int LAYOUT_GONE_MARGIN_TOP = 22;
    
    public static final int UNUSED = 0;
    
    public static final SparseIntArray map = new SparseIntArray();
    
    static {
      map.append(R.styleable.ConstraintLayout_Layout_layout_constraintLeft_toLeftOf, 8);
      map.append(R.styleable.ConstraintLayout_Layout_layout_constraintLeft_toRightOf, 9);
      map.append(R.styleable.ConstraintLayout_Layout_layout_constraintRight_toLeftOf, 10);
      map.append(R.styleable.ConstraintLayout_Layout_layout_constraintRight_toRightOf, 11);
      map.append(R.styleable.ConstraintLayout_Layout_layout_constraintTop_toTopOf, 12);
      map.append(R.styleable.ConstraintLayout_Layout_layout_constraintTop_toBottomOf, 13);
      map.append(R.styleable.ConstraintLayout_Layout_layout_constraintBottom_toTopOf, 14);
      map.append(R.styleable.ConstraintLayout_Layout_layout_constraintBottom_toBottomOf, 15);
      map.append(R.styleable.ConstraintLayout_Layout_layout_constraintBaseline_toBaselineOf, 16);
      map.append(R.styleable.ConstraintLayout_Layout_layout_constraintCircle, 2);
      map.append(R.styleable.ConstraintLayout_Layout_layout_constraintCircleRadius, 3);
      map.append(R.styleable.ConstraintLayout_Layout_layout_constraintCircleAngle, 4);
      map.append(R.styleable.ConstraintLayout_Layout_layout_editor_absoluteX, 49);
      map.append(R.styleable.ConstraintLayout_Layout_layout_editor_absoluteY, 50);
      map.append(R.styleable.ConstraintLayout_Layout_layout_constraintGuide_begin, 5);
      map.append(R.styleable.ConstraintLayout_Layout_layout_constraintGuide_end, 6);
      map.append(R.styleable.ConstraintLayout_Layout_layout_constraintGuide_percent, 7);
      map.append(R.styleable.ConstraintLayout_Layout_android_orientation, 1);
      map.append(R.styleable.ConstraintLayout_Layout_layout_constraintStart_toEndOf, 17);
      map.append(R.styleable.ConstraintLayout_Layout_layout_constraintStart_toStartOf, 18);
      map.append(R.styleable.ConstraintLayout_Layout_layout_constraintEnd_toStartOf, 19);
      map.append(R.styleable.ConstraintLayout_Layout_layout_constraintEnd_toEndOf, 20);
      map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginLeft, 21);
      map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginTop, 22);
      map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginRight, 23);
      map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginBottom, 24);
      map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginStart, 25);
      map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginEnd, 26);
      map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_bias, 29);
      map.append(R.styleable.ConstraintLayout_Layout_layout_constraintVertical_bias, 30);
      map.append(R.styleable.ConstraintLayout_Layout_layout_constraintDimensionRatio, 44);
      map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_weight, 45);
      map.append(R.styleable.ConstraintLayout_Layout_layout_constraintVertical_weight, 46);
      map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_chainStyle, 47);
      map.append(R.styleable.ConstraintLayout_Layout_layout_constraintVertical_chainStyle, 48);
      map.append(R.styleable.ConstraintLayout_Layout_layout_constrainedWidth, 27);
      map.append(R.styleable.ConstraintLayout_Layout_layout_constrainedHeight, 28);
      map.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_default, 31);
      map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_default, 32);
      map.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_min, 33);
      map.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_max, 34);
      map.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_percent, 35);
      map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_min, 36);
      map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_max, 37);
      map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_percent, 38);
      map.append(R.styleable.ConstraintLayout_Layout_layout_constraintLeft_creator, 39);
      map.append(R.styleable.ConstraintLayout_Layout_layout_constraintTop_creator, 40);
      map.append(R.styleable.ConstraintLayout_Layout_layout_constraintRight_creator, 41);
      map.append(R.styleable.ConstraintLayout_Layout_layout_constraintBottom_creator, 42);
      map.append(R.styleable.ConstraintLayout_Layout_layout_constraintBaseline_creator, 43);
    }
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\android\support\constraint\ConstraintLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */