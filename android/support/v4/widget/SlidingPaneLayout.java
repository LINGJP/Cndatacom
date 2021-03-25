package android.support.v4.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class SlidingPaneLayout extends ViewGroup {
  private static final int DEFAULT_FADE_COLOR = -858993460;
  
  private static final int DEFAULT_OVERHANG_SIZE = 32;
  
  private static final int MIN_FLING_VELOCITY = 400;
  
  private static final String TAG = "SlidingPaneLayout";
  
  private boolean mCanSlide;
  
  private int mCoveredFadeColor;
  
  private boolean mDisplayListReflectionLoaded;
  
  final ViewDragHelper mDragHelper;
  
  private boolean mFirstLayout = true;
  
  private Method mGetDisplayList;
  
  private float mInitialMotionX;
  
  private float mInitialMotionY;
  
  boolean mIsUnableToDrag;
  
  private final int mOverhangSize;
  
  private PanelSlideListener mPanelSlideListener;
  
  private int mParallaxBy;
  
  private float mParallaxOffset;
  
  final ArrayList<DisableLayerRunnable> mPostedRunnables = new ArrayList<DisableLayerRunnable>();
  
  boolean mPreservedOpenState;
  
  private Field mRecreateDisplayList;
  
  private Drawable mShadowDrawableLeft;
  
  private Drawable mShadowDrawableRight;
  
  float mSlideOffset;
  
  int mSlideRange;
  
  View mSlideableView;
  
  private int mSliderFadeColor = -858993460;
  
  private final Rect mTmpRect = new Rect();
  
  public SlidingPaneLayout(@NonNull Context paramContext) {
    this(paramContext, (AttributeSet)null);
  }
  
  public SlidingPaneLayout(@NonNull Context paramContext, @Nullable AttributeSet paramAttributeSet) {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public SlidingPaneLayout(@NonNull Context paramContext, @Nullable AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    float f = (paramContext.getResources().getDisplayMetrics()).density;
    this.mOverhangSize = (int)(32.0F * f + 0.5F);
    setWillNotDraw(false);
    ViewCompat.setAccessibilityDelegate((View)this, new AccessibilityDelegate());
    ViewCompat.setImportantForAccessibility((View)this, 1);
    this.mDragHelper = ViewDragHelper.create(this, 0.5F, new DragHelperCallback());
    this.mDragHelper.setMinVelocity(f * 400.0F);
  }
  
  private boolean closePane(View paramView, int paramInt) {
    if (this.mFirstLayout || smoothSlideTo(0.0F, paramInt)) {
      this.mPreservedOpenState = false;
      return true;
    } 
    return false;
  }
  
  private void dimChildView(View paramView, float paramFloat, int paramInt) {
    LayoutParams layoutParams = (LayoutParams)paramView.getLayoutParams();
    if (paramFloat > 0.0F && paramInt != 0) {
      int i = (int)(((0xFF000000 & paramInt) >>> 24) * paramFloat);
      if (layoutParams.dimPaint == null)
        layoutParams.dimPaint = new Paint(); 
      layoutParams.dimPaint.setColorFilter((ColorFilter)new PorterDuffColorFilter(i << 24 | paramInt & 0xFFFFFF, PorterDuff.Mode.SRC_OVER));
      if (paramView.getLayerType() != 2)
        paramView.setLayerType(2, layoutParams.dimPaint); 
      invalidateChildRegion(paramView);
      return;
    } 
    if (paramView.getLayerType() != 0) {
      if (layoutParams.dimPaint != null)
        layoutParams.dimPaint.setColorFilter(null); 
      DisableLayerRunnable disableLayerRunnable = new DisableLayerRunnable(paramView);
      this.mPostedRunnables.add(disableLayerRunnable);
      ViewCompat.postOnAnimation((View)this, disableLayerRunnable);
    } 
  }
  
  private boolean openPane(View paramView, int paramInt) {
    if (this.mFirstLayout || smoothSlideTo(1.0F, paramInt)) {
      this.mPreservedOpenState = true;
      return true;
    } 
    return false;
  }
  
  private void parallaxOtherViews(float paramFloat) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual isLayoutRtlSupport : ()Z
    //   4: istore #8
    //   6: aload_0
    //   7: getfield mSlideableView : Landroid/view/View;
    //   10: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   13: checkcast android/support/v4/widget/SlidingPaneLayout$LayoutParams
    //   16: astore #10
    //   18: aload #10
    //   20: getfield dimWhenOffset : Z
    //   23: istore #9
    //   25: iconst_0
    //   26: istore #4
    //   28: iload #9
    //   30: ifeq -> 62
    //   33: iload #8
    //   35: ifeq -> 47
    //   38: aload #10
    //   40: getfield rightMargin : I
    //   43: istore_3
    //   44: goto -> 53
    //   47: aload #10
    //   49: getfield leftMargin : I
    //   52: istore_3
    //   53: iload_3
    //   54: ifgt -> 62
    //   57: iconst_1
    //   58: istore_3
    //   59: goto -> 64
    //   62: iconst_0
    //   63: istore_3
    //   64: aload_0
    //   65: invokevirtual getChildCount : ()I
    //   68: istore #7
    //   70: iload #4
    //   72: iload #7
    //   74: if_icmpge -> 199
    //   77: aload_0
    //   78: iload #4
    //   80: invokevirtual getChildAt : (I)Landroid/view/View;
    //   83: astore #10
    //   85: aload #10
    //   87: aload_0
    //   88: getfield mSlideableView : Landroid/view/View;
    //   91: if_acmpne -> 97
    //   94: goto -> 190
    //   97: fconst_1
    //   98: aload_0
    //   99: getfield mParallaxOffset : F
    //   102: fsub
    //   103: aload_0
    //   104: getfield mParallaxBy : I
    //   107: i2f
    //   108: fmul
    //   109: f2i
    //   110: istore #5
    //   112: aload_0
    //   113: fload_1
    //   114: putfield mParallaxOffset : F
    //   117: iload #5
    //   119: fconst_1
    //   120: fload_1
    //   121: fsub
    //   122: aload_0
    //   123: getfield mParallaxBy : I
    //   126: i2f
    //   127: fmul
    //   128: f2i
    //   129: isub
    //   130: istore #6
    //   132: iload #6
    //   134: istore #5
    //   136: iload #8
    //   138: ifeq -> 146
    //   141: iload #6
    //   143: ineg
    //   144: istore #5
    //   146: aload #10
    //   148: iload #5
    //   150: invokevirtual offsetLeftAndRight : (I)V
    //   153: iload_3
    //   154: ifeq -> 190
    //   157: iload #8
    //   159: ifeq -> 172
    //   162: aload_0
    //   163: getfield mParallaxOffset : F
    //   166: fconst_1
    //   167: fsub
    //   168: fstore_2
    //   169: goto -> 179
    //   172: fconst_1
    //   173: aload_0
    //   174: getfield mParallaxOffset : F
    //   177: fsub
    //   178: fstore_2
    //   179: aload_0
    //   180: aload #10
    //   182: fload_2
    //   183: aload_0
    //   184: getfield mCoveredFadeColor : I
    //   187: invokespecial dimChildView : (Landroid/view/View;FI)V
    //   190: iload #4
    //   192: iconst_1
    //   193: iadd
    //   194: istore #4
    //   196: goto -> 70
    //   199: return
  }
  
  private static boolean viewIsOpaque(View paramView) {
    if (paramView.isOpaque())
      return true; 
    if (Build.VERSION.SDK_INT >= 18)
      return false; 
    Drawable drawable = paramView.getBackground();
    return (drawable != null) ? ((drawable.getOpacity() == -1)) : false;
  }
  
  protected boolean canScroll(View paramView, boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3) {
    if (paramView instanceof ViewGroup) {
      ViewGroup viewGroup = (ViewGroup)paramView;
      int j = paramView.getScrollX();
      int k = paramView.getScrollY();
      int i;
      for (i = viewGroup.getChildCount() - 1; i >= 0; i--) {
        View view = viewGroup.getChildAt(i);
        int m = paramInt2 + j;
        if (m >= view.getLeft() && m < view.getRight()) {
          int n = paramInt3 + k;
          if (n >= view.getTop() && n < view.getBottom() && canScroll(view, true, paramInt1, m - view.getLeft(), n - view.getTop()))
            return true; 
        } 
      } 
    } 
    if (paramBoolean) {
      if (!isLayoutRtlSupport())
        paramInt1 = -paramInt1; 
      if (paramView.canScrollHorizontally(paramInt1))
        return true; 
    } 
    return false;
  }
  
  @Deprecated
  public boolean canSlide() {
    return this.mCanSlide;
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
    return (paramLayoutParams instanceof LayoutParams && super.checkLayoutParams(paramLayoutParams));
  }
  
  public boolean closePane() {
    return closePane(this.mSlideableView, 0);
  }
  
  public void computeScroll() {
    if (this.mDragHelper.continueSettling(true)) {
      if (!this.mCanSlide) {
        this.mDragHelper.abort();
        return;
      } 
      ViewCompat.postInvalidateOnAnimation((View)this);
    } 
  }
  
  void dispatchOnPanelClosed(View paramView) {
    if (this.mPanelSlideListener != null)
      this.mPanelSlideListener.onPanelClosed(paramView); 
    sendAccessibilityEvent(32);
  }
  
  void dispatchOnPanelOpened(View paramView) {
    if (this.mPanelSlideListener != null)
      this.mPanelSlideListener.onPanelOpened(paramView); 
    sendAccessibilityEvent(32);
  }
  
  void dispatchOnPanelSlide(View paramView) {
    if (this.mPanelSlideListener != null)
      this.mPanelSlideListener.onPanelSlide(paramView, this.mSlideOffset); 
  }
  
  public void draw(Canvas paramCanvas) {
    Drawable drawable;
    View view;
    super.draw(paramCanvas);
    if (isLayoutRtlSupport()) {
      drawable = this.mShadowDrawableRight;
    } else {
      drawable = this.mShadowDrawableLeft;
    } 
    if (getChildCount() > 1) {
      view = getChildAt(1);
    } else {
      view = null;
    } 
    if (view != null) {
      int i;
      int j;
      if (drawable == null)
        return; 
      int m = view.getTop();
      int n = view.getBottom();
      int k = drawable.getIntrinsicWidth();
      if (isLayoutRtlSupport()) {
        i = view.getRight();
        j = k + i;
      } else {
        j = view.getLeft();
        i = j;
        k = j - k;
        j = i;
        i = k;
      } 
      drawable.setBounds(i, m, j, n);
      drawable.draw(paramCanvas);
      return;
    } 
  }
  
  protected boolean drawChild(Canvas paramCanvas, View paramView, long paramLong) {
    LayoutParams layoutParams = (LayoutParams)paramView.getLayoutParams();
    int i = paramCanvas.save();
    if (this.mCanSlide && !layoutParams.slideable && this.mSlideableView != null) {
      paramCanvas.getClipBounds(this.mTmpRect);
      if (isLayoutRtlSupport()) {
        this.mTmpRect.left = Math.max(this.mTmpRect.left, this.mSlideableView.getRight());
      } else {
        this.mTmpRect.right = Math.min(this.mTmpRect.right, this.mSlideableView.getLeft());
      } 
      paramCanvas.clipRect(this.mTmpRect);
    } 
    boolean bool = super.drawChild(paramCanvas, paramView, paramLong);
    paramCanvas.restoreToCount(i);
    return bool;
  }
  
  protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
    return (ViewGroup.LayoutParams)new LayoutParams();
  }
  
  public ViewGroup.LayoutParams generateLayoutParams(AttributeSet paramAttributeSet) {
    return (ViewGroup.LayoutParams)new LayoutParams(getContext(), paramAttributeSet);
  }
  
  protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
    return (ViewGroup.LayoutParams)((paramLayoutParams instanceof ViewGroup.MarginLayoutParams) ? new LayoutParams((ViewGroup.MarginLayoutParams)paramLayoutParams) : new LayoutParams(paramLayoutParams));
  }
  
  @ColorInt
  public int getCoveredFadeColor() {
    return this.mCoveredFadeColor;
  }
  
  @Px
  public int getParallaxDistance() {
    return this.mParallaxBy;
  }
  
  @ColorInt
  public int getSliderFadeColor() {
    return this.mSliderFadeColor;
  }
  
  void invalidateChildRegion(View paramView) {
    if (Build.VERSION.SDK_INT >= 17) {
      ViewCompat.setLayerPaint(paramView, ((LayoutParams)paramView.getLayoutParams()).dimPaint);
      return;
    } 
    if (Build.VERSION.SDK_INT >= 16) {
      if (!this.mDisplayListReflectionLoaded) {
        try {
          this.mGetDisplayList = View.class.getDeclaredMethod("getDisplayList", (Class[])null);
        } catch (NoSuchMethodException noSuchMethodException) {
          Log.e("SlidingPaneLayout", "Couldn't fetch getDisplayList method; dimming won't work right.", noSuchMethodException);
        } 
        try {
          this.mRecreateDisplayList = View.class.getDeclaredField("mRecreateDisplayList");
          this.mRecreateDisplayList.setAccessible(true);
        } catch (NoSuchFieldException noSuchFieldException) {
          Log.e("SlidingPaneLayout", "Couldn't fetch mRecreateDisplayList field; dimming will be slow.", noSuchFieldException);
        } 
        this.mDisplayListReflectionLoaded = true;
      } 
      if (this.mGetDisplayList == null || this.mRecreateDisplayList == null) {
        paramView.invalidate();
        return;
      } 
      try {
        this.mRecreateDisplayList.setBoolean(paramView, true);
        this.mGetDisplayList.invoke(paramView, (Object[])null);
      } catch (Exception exception) {
        Log.e("SlidingPaneLayout", "Error refreshing display list state", exception);
      } 
    } 
    ViewCompat.postInvalidateOnAnimation((View)this, paramView.getLeft(), paramView.getTop(), paramView.getRight(), paramView.getBottom());
  }
  
  boolean isDimmed(View paramView) {
    boolean bool2 = false;
    if (paramView == null)
      return false; 
    LayoutParams layoutParams = (LayoutParams)paramView.getLayoutParams();
    boolean bool1 = bool2;
    if (this.mCanSlide) {
      bool1 = bool2;
      if (layoutParams.dimWhenOffset) {
        bool1 = bool2;
        if (this.mSlideOffset > 0.0F)
          bool1 = true; 
      } 
    } 
    return bool1;
  }
  
  boolean isLayoutRtlSupport() {
    return (ViewCompat.getLayoutDirection((View)this) == 1);
  }
  
  public boolean isOpen() {
    return (!this.mCanSlide || this.mSlideOffset == 1.0F);
  }
  
  public boolean isSlideable() {
    return this.mCanSlide;
  }
  
  protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    this.mFirstLayout = true;
  }
  
  protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    this.mFirstLayout = true;
    int j = this.mPostedRunnables.size();
    for (int i = 0; i < j; i++)
      ((DisableLayerRunnable)this.mPostedRunnables.get(i)).run(); 
    this.mPostedRunnables.clear();
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual getActionMasked : ()I
    //   4: istore #4
    //   6: aload_0
    //   7: getfield mCanSlide : Z
    //   10: istore #6
    //   12: iconst_1
    //   13: istore #5
    //   15: iload #6
    //   17: ifne -> 70
    //   20: iload #4
    //   22: ifne -> 70
    //   25: aload_0
    //   26: invokevirtual getChildCount : ()I
    //   29: iconst_1
    //   30: if_icmple -> 70
    //   33: aload_0
    //   34: iconst_1
    //   35: invokevirtual getChildAt : (I)Landroid/view/View;
    //   38: astore #7
    //   40: aload #7
    //   42: ifnull -> 70
    //   45: aload_0
    //   46: aload_0
    //   47: getfield mDragHelper : Landroid/support/v4/widget/ViewDragHelper;
    //   50: aload #7
    //   52: aload_1
    //   53: invokevirtual getX : ()F
    //   56: f2i
    //   57: aload_1
    //   58: invokevirtual getY : ()F
    //   61: f2i
    //   62: invokevirtual isViewUnder : (Landroid/view/View;II)Z
    //   65: iconst_1
    //   66: ixor
    //   67: putfield mPreservedOpenState : Z
    //   70: aload_0
    //   71: getfield mCanSlide : Z
    //   74: ifeq -> 280
    //   77: aload_0
    //   78: getfield mIsUnableToDrag : Z
    //   81: ifeq -> 92
    //   84: iload #4
    //   86: ifeq -> 92
    //   89: goto -> 280
    //   92: iload #4
    //   94: iconst_3
    //   95: if_icmpeq -> 271
    //   98: iload #4
    //   100: iconst_1
    //   101: if_icmpne -> 107
    //   104: goto -> 271
    //   107: iload #4
    //   109: ifeq -> 184
    //   112: iload #4
    //   114: iconst_2
    //   115: if_icmpeq -> 121
    //   118: goto -> 244
    //   121: aload_1
    //   122: invokevirtual getX : ()F
    //   125: fstore_3
    //   126: aload_1
    //   127: invokevirtual getY : ()F
    //   130: fstore_2
    //   131: fload_3
    //   132: aload_0
    //   133: getfield mInitialMotionX : F
    //   136: fsub
    //   137: invokestatic abs : (F)F
    //   140: fstore_3
    //   141: fload_2
    //   142: aload_0
    //   143: getfield mInitialMotionY : F
    //   146: fsub
    //   147: invokestatic abs : (F)F
    //   150: fstore_2
    //   151: fload_3
    //   152: aload_0
    //   153: getfield mDragHelper : Landroid/support/v4/widget/ViewDragHelper;
    //   156: invokevirtual getTouchSlop : ()I
    //   159: i2f
    //   160: fcmpl
    //   161: ifle -> 244
    //   164: fload_2
    //   165: fload_3
    //   166: fcmpl
    //   167: ifle -> 244
    //   170: aload_0
    //   171: getfield mDragHelper : Landroid/support/v4/widget/ViewDragHelper;
    //   174: invokevirtual cancel : ()V
    //   177: aload_0
    //   178: iconst_1
    //   179: putfield mIsUnableToDrag : Z
    //   182: iconst_0
    //   183: ireturn
    //   184: aload_0
    //   185: iconst_0
    //   186: putfield mIsUnableToDrag : Z
    //   189: aload_1
    //   190: invokevirtual getX : ()F
    //   193: fstore_2
    //   194: aload_1
    //   195: invokevirtual getY : ()F
    //   198: fstore_3
    //   199: aload_0
    //   200: fload_2
    //   201: putfield mInitialMotionX : F
    //   204: aload_0
    //   205: fload_3
    //   206: putfield mInitialMotionY : F
    //   209: aload_0
    //   210: getfield mDragHelper : Landroid/support/v4/widget/ViewDragHelper;
    //   213: aload_0
    //   214: getfield mSlideableView : Landroid/view/View;
    //   217: fload_2
    //   218: f2i
    //   219: fload_3
    //   220: f2i
    //   221: invokevirtual isViewUnder : (Landroid/view/View;II)Z
    //   224: ifeq -> 244
    //   227: aload_0
    //   228: aload_0
    //   229: getfield mSlideableView : Landroid/view/View;
    //   232: invokevirtual isDimmed : (Landroid/view/View;)Z
    //   235: ifeq -> 244
    //   238: iconst_1
    //   239: istore #4
    //   241: goto -> 247
    //   244: iconst_0
    //   245: istore #4
    //   247: aload_0
    //   248: getfield mDragHelper : Landroid/support/v4/widget/ViewDragHelper;
    //   251: aload_1
    //   252: invokevirtual shouldInterceptTouchEvent : (Landroid/view/MotionEvent;)Z
    //   255: ifne -> 268
    //   258: iload #4
    //   260: ifeq -> 265
    //   263: iconst_1
    //   264: ireturn
    //   265: iconst_0
    //   266: istore #5
    //   268: iload #5
    //   270: ireturn
    //   271: aload_0
    //   272: getfield mDragHelper : Landroid/support/v4/widget/ViewDragHelper;
    //   275: invokevirtual cancel : ()V
    //   278: iconst_0
    //   279: ireturn
    //   280: aload_0
    //   281: getfield mDragHelper : Landroid/support/v4/widget/ViewDragHelper;
    //   284: invokevirtual cancel : ()V
    //   287: aload_0
    //   288: aload_1
    //   289: invokespecial onInterceptTouchEvent : (Landroid/view/MotionEvent;)Z
    //   292: ireturn
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual isLayoutRtlSupport : ()Z
    //   4: istore #14
    //   6: iload #14
    //   8: ifeq -> 22
    //   11: aload_0
    //   12: getfield mDragHelper : Landroid/support/v4/widget/ViewDragHelper;
    //   15: iconst_2
    //   16: invokevirtual setEdgeTrackingEnabled : (I)V
    //   19: goto -> 30
    //   22: aload_0
    //   23: getfield mDragHelper : Landroid/support/v4/widget/ViewDragHelper;
    //   26: iconst_1
    //   27: invokevirtual setEdgeTrackingEnabled : (I)V
    //   30: iload #4
    //   32: iload_2
    //   33: isub
    //   34: istore #9
    //   36: iload #14
    //   38: ifeq -> 49
    //   41: aload_0
    //   42: invokevirtual getPaddingRight : ()I
    //   45: istore_2
    //   46: goto -> 54
    //   49: aload_0
    //   50: invokevirtual getPaddingLeft : ()I
    //   53: istore_2
    //   54: iload #14
    //   56: ifeq -> 68
    //   59: aload_0
    //   60: invokevirtual getPaddingLeft : ()I
    //   63: istore #4
    //   65: goto -> 74
    //   68: aload_0
    //   69: invokevirtual getPaddingRight : ()I
    //   72: istore #4
    //   74: aload_0
    //   75: invokevirtual getPaddingTop : ()I
    //   78: istore #11
    //   80: aload_0
    //   81: invokevirtual getChildCount : ()I
    //   84: istore #10
    //   86: aload_0
    //   87: getfield mFirstLayout : Z
    //   90: ifeq -> 122
    //   93: aload_0
    //   94: getfield mCanSlide : Z
    //   97: ifeq -> 113
    //   100: aload_0
    //   101: getfield mPreservedOpenState : Z
    //   104: ifeq -> 113
    //   107: fconst_1
    //   108: fstore #6
    //   110: goto -> 116
    //   113: fconst_0
    //   114: fstore #6
    //   116: aload_0
    //   117: fload #6
    //   119: putfield mSlideOffset : F
    //   122: iload_2
    //   123: istore_3
    //   124: iconst_0
    //   125: istore #5
    //   127: iload #5
    //   129: iload #10
    //   131: if_icmpge -> 427
    //   134: aload_0
    //   135: iload #5
    //   137: invokevirtual getChildAt : (I)Landroid/view/View;
    //   140: astore #15
    //   142: aload #15
    //   144: invokevirtual getVisibility : ()I
    //   147: bipush #8
    //   149: if_icmpne -> 155
    //   152: goto -> 418
    //   155: aload #15
    //   157: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   160: checkcast android/support/v4/widget/SlidingPaneLayout$LayoutParams
    //   163: astore #16
    //   165: aload #15
    //   167: invokevirtual getMeasuredWidth : ()I
    //   170: istore #12
    //   172: aload #16
    //   174: getfield slideable : Z
    //   177: ifeq -> 315
    //   180: aload #16
    //   182: getfield leftMargin : I
    //   185: istore #7
    //   187: aload #16
    //   189: getfield rightMargin : I
    //   192: istore #13
    //   194: iload #9
    //   196: iload #4
    //   198: isub
    //   199: istore #8
    //   201: iload_2
    //   202: iload #8
    //   204: aload_0
    //   205: getfield mOverhangSize : I
    //   208: isub
    //   209: invokestatic min : (II)I
    //   212: iload_3
    //   213: isub
    //   214: iload #7
    //   216: iload #13
    //   218: iadd
    //   219: isub
    //   220: istore #13
    //   222: aload_0
    //   223: iload #13
    //   225: putfield mSlideRange : I
    //   228: iload #14
    //   230: ifeq -> 243
    //   233: aload #16
    //   235: getfield rightMargin : I
    //   238: istore #7
    //   240: goto -> 250
    //   243: aload #16
    //   245: getfield leftMargin : I
    //   248: istore #7
    //   250: iload_3
    //   251: iload #7
    //   253: iadd
    //   254: iload #13
    //   256: iadd
    //   257: iload #12
    //   259: iconst_2
    //   260: idiv
    //   261: iadd
    //   262: iload #8
    //   264: if_icmple -> 272
    //   267: iconst_1
    //   268: istore_1
    //   269: goto -> 274
    //   272: iconst_0
    //   273: istore_1
    //   274: aload #16
    //   276: iload_1
    //   277: putfield dimWhenOffset : Z
    //   280: iload #13
    //   282: i2f
    //   283: aload_0
    //   284: getfield mSlideOffset : F
    //   287: fmul
    //   288: f2i
    //   289: istore #8
    //   291: iload #7
    //   293: iload #8
    //   295: iadd
    //   296: iload_3
    //   297: iadd
    //   298: istore_3
    //   299: aload_0
    //   300: iload #8
    //   302: i2f
    //   303: aload_0
    //   304: getfield mSlideRange : I
    //   307: i2f
    //   308: fdiv
    //   309: putfield mSlideOffset : F
    //   312: goto -> 351
    //   315: aload_0
    //   316: getfield mCanSlide : Z
    //   319: ifeq -> 349
    //   322: aload_0
    //   323: getfield mParallaxBy : I
    //   326: ifeq -> 349
    //   329: fconst_1
    //   330: aload_0
    //   331: getfield mSlideOffset : F
    //   334: fsub
    //   335: aload_0
    //   336: getfield mParallaxBy : I
    //   339: i2f
    //   340: fmul
    //   341: f2i
    //   342: istore #7
    //   344: iload_2
    //   345: istore_3
    //   346: goto -> 354
    //   349: iload_2
    //   350: istore_3
    //   351: iconst_0
    //   352: istore #7
    //   354: iload #14
    //   356: ifeq -> 378
    //   359: iload #9
    //   361: iload_3
    //   362: isub
    //   363: iload #7
    //   365: iadd
    //   366: istore #8
    //   368: iload #8
    //   370: iload #12
    //   372: isub
    //   373: istore #7
    //   375: goto -> 391
    //   378: iload_3
    //   379: iload #7
    //   381: isub
    //   382: istore #7
    //   384: iload #7
    //   386: iload #12
    //   388: iadd
    //   389: istore #8
    //   391: aload #15
    //   393: iload #7
    //   395: iload #11
    //   397: iload #8
    //   399: aload #15
    //   401: invokevirtual getMeasuredHeight : ()I
    //   404: iload #11
    //   406: iadd
    //   407: invokevirtual layout : (IIII)V
    //   410: iload_2
    //   411: aload #15
    //   413: invokevirtual getWidth : ()I
    //   416: iadd
    //   417: istore_2
    //   418: iload #5
    //   420: iconst_1
    //   421: iadd
    //   422: istore #5
    //   424: goto -> 127
    //   427: aload_0
    //   428: getfield mFirstLayout : Z
    //   431: ifeq -> 528
    //   434: aload_0
    //   435: getfield mCanSlide : Z
    //   438: ifeq -> 491
    //   441: aload_0
    //   442: getfield mParallaxBy : I
    //   445: ifeq -> 456
    //   448: aload_0
    //   449: aload_0
    //   450: getfield mSlideOffset : F
    //   453: invokespecial parallaxOtherViews : (F)V
    //   456: aload_0
    //   457: getfield mSlideableView : Landroid/view/View;
    //   460: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   463: checkcast android/support/v4/widget/SlidingPaneLayout$LayoutParams
    //   466: getfield dimWhenOffset : Z
    //   469: ifeq -> 520
    //   472: aload_0
    //   473: aload_0
    //   474: getfield mSlideableView : Landroid/view/View;
    //   477: aload_0
    //   478: getfield mSlideOffset : F
    //   481: aload_0
    //   482: getfield mSliderFadeColor : I
    //   485: invokespecial dimChildView : (Landroid/view/View;FI)V
    //   488: goto -> 520
    //   491: iconst_0
    //   492: istore_2
    //   493: iload_2
    //   494: iload #10
    //   496: if_icmpge -> 520
    //   499: aload_0
    //   500: aload_0
    //   501: iload_2
    //   502: invokevirtual getChildAt : (I)Landroid/view/View;
    //   505: fconst_0
    //   506: aload_0
    //   507: getfield mSliderFadeColor : I
    //   510: invokespecial dimChildView : (Landroid/view/View;FI)V
    //   513: iload_2
    //   514: iconst_1
    //   515: iadd
    //   516: istore_2
    //   517: goto -> 493
    //   520: aload_0
    //   521: aload_0
    //   522: getfield mSlideableView : Landroid/view/View;
    //   525: invokevirtual updateObscuredViewsVisibility : (Landroid/view/View;)V
    //   528: aload_0
    //   529: iconst_0
    //   530: putfield mFirstLayout : Z
    //   533: return
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    Object object;
    float f1;
    int k;
    int n;
    int m = View.MeasureSpec.getMode(paramInt1);
    int i = View.MeasureSpec.getSize(paramInt1);
    int j = View.MeasureSpec.getMode(paramInt2);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    if (m != 1073741824) {
      if (isInEditMode()) {
        if (m == Integer.MIN_VALUE) {
          k = i;
          n = j;
          paramInt1 = paramInt2;
        } else {
          k = i;
          n = j;
          paramInt1 = paramInt2;
          if (m == 0) {
            k = 300;
            n = j;
            paramInt1 = paramInt2;
          } 
        } 
      } else {
        throw new IllegalStateException("Width must have an exact value or MATCH_PARENT");
      } 
    } else {
      k = i;
      n = j;
      paramInt1 = paramInt2;
      if (j == 0)
        if (isInEditMode()) {
          k = i;
          n = j;
          paramInt1 = paramInt2;
          if (j == 0) {
            n = Integer.MIN_VALUE;
            paramInt1 = 300;
            k = i;
          } 
        } else {
          throw new IllegalStateException("Height must not be UNSPECIFIED");
        }  
    } 
    if (n != Integer.MIN_VALUE) {
      if (n != 1073741824) {
        paramInt1 = 0;
        paramInt2 = 0;
      } else {
        paramInt1 = paramInt1 - getPaddingTop() - getPaddingBottom();
        paramInt2 = paramInt1;
      } 
    } else {
      paramInt2 = paramInt1 - getPaddingTop() - getPaddingBottom();
      paramInt1 = 0;
    } 
    int i2 = k - getPaddingLeft() - getPaddingRight();
    int i3 = getChildCount();
    if (i3 > 2)
      Log.e("SlidingPaneLayout", "onMeasure: More than two child views are not supported."); 
    this.mSlideableView = null;
    m = i2;
    int i1 = 0;
    int i4 = 0;
    float f2 = 0.0F;
    for (i = paramInt1; i1 < i3; i = paramInt1) {
      int i6;
      View view = getChildAt(i1);
      LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
      if (view.getVisibility() == 8) {
        layoutParams.dimWhenOffset = false;
        Object object2 = object;
        paramInt1 = i;
        continue;
      } 
      Object object1 = object;
      if (layoutParams.weight > 0.0F) {
        f1 = object + layoutParams.weight;
        float f = f1;
        if (layoutParams.width == 0) {
          f = f1;
          paramInt1 = i;
          continue;
        } 
      } 
      paramInt1 = layoutParams.leftMargin + layoutParams.rightMargin;
      if (layoutParams.width == -2) {
        paramInt1 = View.MeasureSpec.makeMeasureSpec(i2 - paramInt1, -2147483648);
      } else if (layoutParams.width == -1) {
        paramInt1 = View.MeasureSpec.makeMeasureSpec(i2 - paramInt1, 1073741824);
      } else {
        paramInt1 = View.MeasureSpec.makeMeasureSpec(layoutParams.width, 1073741824);
      } 
      if (layoutParams.height == -2) {
        j = View.MeasureSpec.makeMeasureSpec(paramInt2, -2147483648);
      } else if (layoutParams.height == -1) {
        j = View.MeasureSpec.makeMeasureSpec(paramInt2, 1073741824);
      } else {
        j = View.MeasureSpec.makeMeasureSpec(layoutParams.height, 1073741824);
      } 
      view.measure(paramInt1, j);
      j = view.getMeasuredWidth();
      int i5 = view.getMeasuredHeight();
      paramInt1 = i;
      if (n == Integer.MIN_VALUE) {
        paramInt1 = i;
        if (i5 > i)
          paramInt1 = Math.min(i5, paramInt2); 
      } 
      m -= j;
      if (m < 0) {
        i6 = 1;
      } else {
        i6 = 0;
      } 
      layoutParams.slideable = i6;
      if (layoutParams.slideable)
        this.mSlideableView = view; 
      i4 = i6 | i4;
      continue;
      i1++;
      object = SYNTHETIC_LOCAL_VARIABLE_3;
    } 
    if (i4 != 0 || f1 > 0.0F) {
      j = i2 - this.mOverhangSize;
      for (n = 0; n < i3; n++) {
        View view = getChildAt(n);
        if (view.getVisibility() != 8) {
          LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
          if (view.getVisibility() != 8) {
            if (layoutParams.width == 0 && layoutParams.weight > 0.0F) {
              paramInt1 = 1;
            } else {
              paramInt1 = 0;
            } 
            if (paramInt1 != 0) {
              i1 = 0;
            } else {
              i1 = view.getMeasuredWidth();
            } 
            if (i4 != 0 && view != this.mSlideableView) {
              if (layoutParams.width < 0 && (i1 > j || layoutParams.weight > 0.0F)) {
                if (paramInt1 != 0) {
                  if (layoutParams.height == -2) {
                    paramInt1 = View.MeasureSpec.makeMeasureSpec(paramInt2, -2147483648);
                  } else if (layoutParams.height == -1) {
                    paramInt1 = View.MeasureSpec.makeMeasureSpec(paramInt2, 1073741824);
                  } else {
                    paramInt1 = View.MeasureSpec.makeMeasureSpec(layoutParams.height, 1073741824);
                  } 
                } else {
                  paramInt1 = View.MeasureSpec.makeMeasureSpec(view.getMeasuredHeight(), 1073741824);
                } 
                view.measure(View.MeasureSpec.makeMeasureSpec(j, 1073741824), paramInt1);
              } 
            } else if (layoutParams.weight > 0.0F) {
              if (layoutParams.width == 0) {
                if (layoutParams.height == -2) {
                  paramInt1 = View.MeasureSpec.makeMeasureSpec(paramInt2, -2147483648);
                } else if (layoutParams.height == -1) {
                  paramInt1 = View.MeasureSpec.makeMeasureSpec(paramInt2, 1073741824);
                } else {
                  paramInt1 = View.MeasureSpec.makeMeasureSpec(layoutParams.height, 1073741824);
                } 
              } else {
                paramInt1 = View.MeasureSpec.makeMeasureSpec(view.getMeasuredHeight(), 1073741824);
              } 
              if (i4 != 0) {
                int i5 = i2 - layoutParams.leftMargin + layoutParams.rightMargin;
                int i6 = View.MeasureSpec.makeMeasureSpec(i5, 1073741824);
                if (i1 != i5)
                  view.measure(i6, paramInt1); 
              } else {
                int i5 = Math.max(0, m);
                view.measure(View.MeasureSpec.makeMeasureSpec(i1 + (int)(layoutParams.weight * i5 / f1), 1073741824), paramInt1);
              } 
            } 
          } 
        } 
      } 
    } 
    setMeasuredDimension(k, i + getPaddingTop() + getPaddingBottom());
    this.mCanSlide = i4;
    if (this.mDragHelper.getViewDragState() != 0 && i4 == 0)
      this.mDragHelper.abort(); 
  }
  
  void onPanelDragged(int paramInt) {
    if (this.mSlideableView == null) {
      this.mSlideOffset = 0.0F;
      return;
    } 
    boolean bool = isLayoutRtlSupport();
    LayoutParams layoutParams = (LayoutParams)this.mSlideableView.getLayoutParams();
    int j = this.mSlideableView.getWidth();
    int i = paramInt;
    if (bool)
      i = getWidth() - paramInt - j; 
    if (bool) {
      paramInt = getPaddingRight();
    } else {
      paramInt = getPaddingLeft();
    } 
    if (bool) {
      j = layoutParams.rightMargin;
    } else {
      j = layoutParams.leftMargin;
    } 
    this.mSlideOffset = (i - paramInt + j) / this.mSlideRange;
    if (this.mParallaxBy != 0)
      parallaxOtherViews(this.mSlideOffset); 
    if (layoutParams.dimWhenOffset)
      dimChildView(this.mSlideableView, this.mSlideOffset, this.mSliderFadeColor); 
    dispatchOnPanelSlide(this.mSlideableView);
  }
  
  protected void onRestoreInstanceState(Parcelable paramParcelable) {
    if (!(paramParcelable instanceof SavedState)) {
      super.onRestoreInstanceState(paramParcelable);
      return;
    } 
    SavedState savedState = (SavedState)paramParcelable;
    super.onRestoreInstanceState(savedState.getSuperState());
    if (savedState.isOpen) {
      openPane();
    } else {
      closePane();
    } 
    this.mPreservedOpenState = savedState.isOpen;
  }
  
  protected Parcelable onSaveInstanceState() {
    boolean bool;
    SavedState savedState = new SavedState(super.onSaveInstanceState());
    if (isSlideable()) {
      bool = isOpen();
    } else {
      bool = this.mPreservedOpenState;
    } 
    savedState.isOpen = bool;
    return (Parcelable)savedState;
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (paramInt1 != paramInt3)
      this.mFirstLayout = true; 
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent) {
    if (!this.mCanSlide)
      return super.onTouchEvent(paramMotionEvent); 
    this.mDragHelper.processTouchEvent(paramMotionEvent);
    switch (paramMotionEvent.getActionMasked()) {
      default:
        return true;
      case 1:
        if (isDimmed(this.mSlideableView)) {
          float f3 = paramMotionEvent.getX();
          float f4 = paramMotionEvent.getY();
          float f5 = f3 - this.mInitialMotionX;
          float f6 = f4 - this.mInitialMotionY;
          int i = this.mDragHelper.getTouchSlop();
          if (f5 * f5 + f6 * f6 < (i * i) && this.mDragHelper.isViewUnder(this.mSlideableView, (int)f3, (int)f4)) {
            closePane(this.mSlideableView, 0);
            return true;
          } 
        } 
        return true;
      case 0:
        break;
    } 
    float f1 = paramMotionEvent.getX();
    float f2 = paramMotionEvent.getY();
    this.mInitialMotionX = f1;
    this.mInitialMotionY = f2;
    return true;
  }
  
  public boolean openPane() {
    return openPane(this.mSlideableView, 0);
  }
  
  public void requestChildFocus(View paramView1, View paramView2) {
    super.requestChildFocus(paramView1, paramView2);
    if (!isInTouchMode() && !this.mCanSlide) {
      boolean bool;
      if (paramView1 == this.mSlideableView) {
        bool = true;
      } else {
        bool = false;
      } 
      this.mPreservedOpenState = bool;
    } 
  }
  
  void setAllChildrenVisible() {
    int j = getChildCount();
    for (int i = 0; i < j; i++) {
      View view = getChildAt(i);
      if (view.getVisibility() == 4)
        view.setVisibility(0); 
    } 
  }
  
  public void setCoveredFadeColor(@ColorInt int paramInt) {
    this.mCoveredFadeColor = paramInt;
  }
  
  public void setPanelSlideListener(@Nullable PanelSlideListener paramPanelSlideListener) {
    this.mPanelSlideListener = paramPanelSlideListener;
  }
  
  public void setParallaxDistance(@Px int paramInt) {
    this.mParallaxBy = paramInt;
    requestLayout();
  }
  
  @Deprecated
  public void setShadowDrawable(Drawable paramDrawable) {
    setShadowDrawableLeft(paramDrawable);
  }
  
  public void setShadowDrawableLeft(@Nullable Drawable paramDrawable) {
    this.mShadowDrawableLeft = paramDrawable;
  }
  
  public void setShadowDrawableRight(@Nullable Drawable paramDrawable) {
    this.mShadowDrawableRight = paramDrawable;
  }
  
  @Deprecated
  public void setShadowResource(@DrawableRes int paramInt) {
    setShadowDrawable(getResources().getDrawable(paramInt));
  }
  
  public void setShadowResourceLeft(int paramInt) {
    setShadowDrawableLeft(ContextCompat.getDrawable(getContext(), paramInt));
  }
  
  public void setShadowResourceRight(int paramInt) {
    setShadowDrawableRight(ContextCompat.getDrawable(getContext(), paramInt));
  }
  
  public void setSliderFadeColor(@ColorInt int paramInt) {
    this.mSliderFadeColor = paramInt;
  }
  
  @Deprecated
  public void smoothSlideClosed() {
    closePane();
  }
  
  @Deprecated
  public void smoothSlideOpen() {
    openPane();
  }
  
  boolean smoothSlideTo(float paramFloat, int paramInt) {
    if (!this.mCanSlide)
      return false; 
    boolean bool = isLayoutRtlSupport();
    LayoutParams layoutParams = (LayoutParams)this.mSlideableView.getLayoutParams();
    if (bool) {
      paramInt = getPaddingRight();
      int i = layoutParams.rightMargin;
      int j = this.mSlideableView.getWidth();
      paramInt = (int)(getWidth() - (paramInt + i) + paramFloat * this.mSlideRange + j);
    } else {
      paramInt = (int)((getPaddingLeft() + layoutParams.leftMargin) + paramFloat * this.mSlideRange);
    } 
    if (this.mDragHelper.smoothSlideViewTo(this.mSlideableView, paramInt, this.mSlideableView.getTop())) {
      setAllChildrenVisible();
      ViewCompat.postInvalidateOnAnimation((View)this);
      return true;
    } 
    return false;
  }
  
  void updateObscuredViewsVisibility(View paramView) {
    int i;
    int j;
    byte b1;
    byte b2;
    byte b3;
    byte b4;
    boolean bool = isLayoutRtlSupport();
    if (bool) {
      i = getWidth() - getPaddingRight();
    } else {
      i = getPaddingLeft();
    } 
    if (bool) {
      j = getPaddingLeft();
    } else {
      j = getWidth() - getPaddingRight();
    } 
    int m = getPaddingTop();
    int n = getHeight();
    int i1 = getPaddingBottom();
    if (paramView != null && viewIsOpaque(paramView)) {
      b1 = paramView.getLeft();
      b2 = paramView.getRight();
      b3 = paramView.getTop();
      b4 = paramView.getBottom();
    } else {
      b1 = 0;
      b2 = 0;
      b3 = 0;
      b4 = 0;
    } 
    int i2 = getChildCount();
    int k;
    for (k = 0; k < i2; k++) {
      View view = getChildAt(k);
      if (view == paramView)
        return; 
      if (view.getVisibility() != 8) {
        if (bool) {
          i3 = j;
        } else {
          i3 = i;
        } 
        int i4 = Math.max(i3, view.getLeft());
        int i5 = Math.max(m, view.getTop());
        if (bool) {
          i3 = i;
        } else {
          i3 = j;
        } 
        int i3 = Math.min(i3, view.getRight());
        int i6 = Math.min(n - i1, view.getBottom());
        if (i4 >= b1 && i5 >= b3 && i3 <= b2 && i6 <= b4) {
          i3 = 4;
        } else {
          i3 = 0;
        } 
        view.setVisibility(i3);
      } 
    } 
  }
  
  class AccessibilityDelegate extends AccessibilityDelegateCompat {
    private final Rect mTmpRect = new Rect();
    
    private void copyNodeInfoNoChildren(AccessibilityNodeInfoCompat param1AccessibilityNodeInfoCompat1, AccessibilityNodeInfoCompat param1AccessibilityNodeInfoCompat2) {
      Rect rect = this.mTmpRect;
      param1AccessibilityNodeInfoCompat2.getBoundsInParent(rect);
      param1AccessibilityNodeInfoCompat1.setBoundsInParent(rect);
      param1AccessibilityNodeInfoCompat2.getBoundsInScreen(rect);
      param1AccessibilityNodeInfoCompat1.setBoundsInScreen(rect);
      param1AccessibilityNodeInfoCompat1.setVisibleToUser(param1AccessibilityNodeInfoCompat2.isVisibleToUser());
      param1AccessibilityNodeInfoCompat1.setPackageName(param1AccessibilityNodeInfoCompat2.getPackageName());
      param1AccessibilityNodeInfoCompat1.setClassName(param1AccessibilityNodeInfoCompat2.getClassName());
      param1AccessibilityNodeInfoCompat1.setContentDescription(param1AccessibilityNodeInfoCompat2.getContentDescription());
      param1AccessibilityNodeInfoCompat1.setEnabled(param1AccessibilityNodeInfoCompat2.isEnabled());
      param1AccessibilityNodeInfoCompat1.setClickable(param1AccessibilityNodeInfoCompat2.isClickable());
      param1AccessibilityNodeInfoCompat1.setFocusable(param1AccessibilityNodeInfoCompat2.isFocusable());
      param1AccessibilityNodeInfoCompat1.setFocused(param1AccessibilityNodeInfoCompat2.isFocused());
      param1AccessibilityNodeInfoCompat1.setAccessibilityFocused(param1AccessibilityNodeInfoCompat2.isAccessibilityFocused());
      param1AccessibilityNodeInfoCompat1.setSelected(param1AccessibilityNodeInfoCompat2.isSelected());
      param1AccessibilityNodeInfoCompat1.setLongClickable(param1AccessibilityNodeInfoCompat2.isLongClickable());
      param1AccessibilityNodeInfoCompat1.addAction(param1AccessibilityNodeInfoCompat2.getActions());
      param1AccessibilityNodeInfoCompat1.setMovementGranularities(param1AccessibilityNodeInfoCompat2.getMovementGranularities());
    }
    
    public boolean filter(View param1View) {
      return SlidingPaneLayout.this.isDimmed(param1View);
    }
    
    public void onInitializeAccessibilityEvent(View param1View, AccessibilityEvent param1AccessibilityEvent) {
      super.onInitializeAccessibilityEvent(param1View, param1AccessibilityEvent);
      param1AccessibilityEvent.setClassName(SlidingPaneLayout.class.getName());
    }
    
    public void onInitializeAccessibilityNodeInfo(View param1View, AccessibilityNodeInfoCompat param1AccessibilityNodeInfoCompat) {
      AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = AccessibilityNodeInfoCompat.obtain(param1AccessibilityNodeInfoCompat);
      super.onInitializeAccessibilityNodeInfo(param1View, accessibilityNodeInfoCompat);
      copyNodeInfoNoChildren(param1AccessibilityNodeInfoCompat, accessibilityNodeInfoCompat);
      accessibilityNodeInfoCompat.recycle();
      param1AccessibilityNodeInfoCompat.setClassName(SlidingPaneLayout.class.getName());
      param1AccessibilityNodeInfoCompat.setSource(param1View);
      ViewParent viewParent = ViewCompat.getParentForAccessibility(param1View);
      if (viewParent instanceof View)
        param1AccessibilityNodeInfoCompat.setParent((View)viewParent); 
      int j = SlidingPaneLayout.this.getChildCount();
      for (int i = 0; i < j; i++) {
        View view = SlidingPaneLayout.this.getChildAt(i);
        if (!filter(view) && view.getVisibility() == 0) {
          ViewCompat.setImportantForAccessibility(view, 1);
          param1AccessibilityNodeInfoCompat.addChild(view);
        } 
      } 
    }
    
    public boolean onRequestSendAccessibilityEvent(ViewGroup param1ViewGroup, View param1View, AccessibilityEvent param1AccessibilityEvent) {
      return !filter(param1View) ? super.onRequestSendAccessibilityEvent(param1ViewGroup, param1View, param1AccessibilityEvent) : false;
    }
  }
  
  private class DisableLayerRunnable implements Runnable {
    final View mChildView;
    
    DisableLayerRunnable(View param1View) {
      this.mChildView = param1View;
    }
    
    public void run() {
      if (this.mChildView.getParent() == SlidingPaneLayout.this) {
        this.mChildView.setLayerType(0, null);
        SlidingPaneLayout.this.invalidateChildRegion(this.mChildView);
      } 
      SlidingPaneLayout.this.mPostedRunnables.remove(this);
    }
  }
  
  private class DragHelperCallback extends ViewDragHelper.Callback {
    public int clampViewPositionHorizontal(View param1View, int param1Int1, int param1Int2) {
      SlidingPaneLayout.LayoutParams layoutParams = (SlidingPaneLayout.LayoutParams)SlidingPaneLayout.this.mSlideableView.getLayoutParams();
      if (SlidingPaneLayout.this.isLayoutRtlSupport()) {
        param1Int2 = SlidingPaneLayout.this.getWidth() - SlidingPaneLayout.this.getPaddingRight() + layoutParams.rightMargin + SlidingPaneLayout.this.mSlideableView.getWidth();
        int j = SlidingPaneLayout.this.mSlideRange;
        return Math.max(Math.min(param1Int1, param1Int2), param1Int2 - j);
      } 
      param1Int2 = SlidingPaneLayout.this.getPaddingLeft() + layoutParams.leftMargin;
      int i = SlidingPaneLayout.this.mSlideRange;
      return Math.min(Math.max(param1Int1, param1Int2), i + param1Int2);
    }
    
    public int clampViewPositionVertical(View param1View, int param1Int1, int param1Int2) {
      return param1View.getTop();
    }
    
    public int getViewHorizontalDragRange(View param1View) {
      return SlidingPaneLayout.this.mSlideRange;
    }
    
    public void onEdgeDragStarted(int param1Int1, int param1Int2) {
      SlidingPaneLayout.this.mDragHelper.captureChildView(SlidingPaneLayout.this.mSlideableView, param1Int2);
    }
    
    public void onViewCaptured(View param1View, int param1Int) {
      SlidingPaneLayout.this.setAllChildrenVisible();
    }
    
    public void onViewDragStateChanged(int param1Int) {
      if (SlidingPaneLayout.this.mDragHelper.getViewDragState() == 0) {
        if (SlidingPaneLayout.this.mSlideOffset == 0.0F) {
          SlidingPaneLayout.this.updateObscuredViewsVisibility(SlidingPaneLayout.this.mSlideableView);
          SlidingPaneLayout.this.dispatchOnPanelClosed(SlidingPaneLayout.this.mSlideableView);
          SlidingPaneLayout.this.mPreservedOpenState = false;
          return;
        } 
        SlidingPaneLayout.this.dispatchOnPanelOpened(SlidingPaneLayout.this.mSlideableView);
        SlidingPaneLayout.this.mPreservedOpenState = true;
      } 
    }
    
    public void onViewPositionChanged(View param1View, int param1Int1, int param1Int2, int param1Int3, int param1Int4) {
      SlidingPaneLayout.this.onPanelDragged(param1Int1);
      SlidingPaneLayout.this.invalidate();
    }
    
    public void onViewReleased(View param1View, float param1Float1, float param1Float2) {
      // Byte code:
      //   0: aload_1
      //   1: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
      //   4: checkcast android/support/v4/widget/SlidingPaneLayout$LayoutParams
      //   7: astore #6
      //   9: aload_0
      //   10: getfield this$0 : Landroid/support/v4/widget/SlidingPaneLayout;
      //   13: invokevirtual isLayoutRtlSupport : ()Z
      //   16: ifeq -> 109
      //   19: aload_0
      //   20: getfield this$0 : Landroid/support/v4/widget/SlidingPaneLayout;
      //   23: invokevirtual getPaddingRight : ()I
      //   26: aload #6
      //   28: getfield rightMargin : I
      //   31: iadd
      //   32: istore #5
      //   34: fload_2
      //   35: fconst_0
      //   36: fcmpg
      //   37: iflt -> 67
      //   40: iload #5
      //   42: istore #4
      //   44: fload_2
      //   45: fconst_0
      //   46: fcmpl
      //   47: ifne -> 79
      //   50: iload #5
      //   52: istore #4
      //   54: aload_0
      //   55: getfield this$0 : Landroid/support/v4/widget/SlidingPaneLayout;
      //   58: getfield mSlideOffset : F
      //   61: ldc 0.5
      //   63: fcmpl
      //   64: ifle -> 79
      //   67: iload #5
      //   69: aload_0
      //   70: getfield this$0 : Landroid/support/v4/widget/SlidingPaneLayout;
      //   73: getfield mSlideRange : I
      //   76: iadd
      //   77: istore #4
      //   79: aload_0
      //   80: getfield this$0 : Landroid/support/v4/widget/SlidingPaneLayout;
      //   83: getfield mSlideableView : Landroid/view/View;
      //   86: invokevirtual getWidth : ()I
      //   89: istore #5
      //   91: aload_0
      //   92: getfield this$0 : Landroid/support/v4/widget/SlidingPaneLayout;
      //   95: invokevirtual getWidth : ()I
      //   98: iload #4
      //   100: isub
      //   101: iload #5
      //   103: isub
      //   104: istore #4
      //   106: goto -> 173
      //   109: aload_0
      //   110: getfield this$0 : Landroid/support/v4/widget/SlidingPaneLayout;
      //   113: invokevirtual getPaddingLeft : ()I
      //   116: istore #4
      //   118: aload #6
      //   120: getfield leftMargin : I
      //   123: iload #4
      //   125: iadd
      //   126: istore #5
      //   128: fload_2
      //   129: fconst_0
      //   130: fcmpl
      //   131: ifgt -> 161
      //   134: iload #5
      //   136: istore #4
      //   138: fload_2
      //   139: fconst_0
      //   140: fcmpl
      //   141: ifne -> 173
      //   144: iload #5
      //   146: istore #4
      //   148: aload_0
      //   149: getfield this$0 : Landroid/support/v4/widget/SlidingPaneLayout;
      //   152: getfield mSlideOffset : F
      //   155: ldc 0.5
      //   157: fcmpl
      //   158: ifle -> 173
      //   161: iload #5
      //   163: aload_0
      //   164: getfield this$0 : Landroid/support/v4/widget/SlidingPaneLayout;
      //   167: getfield mSlideRange : I
      //   170: iadd
      //   171: istore #4
      //   173: aload_0
      //   174: getfield this$0 : Landroid/support/v4/widget/SlidingPaneLayout;
      //   177: getfield mDragHelper : Landroid/support/v4/widget/ViewDragHelper;
      //   180: iload #4
      //   182: aload_1
      //   183: invokevirtual getTop : ()I
      //   186: invokevirtual settleCapturedViewAt : (II)Z
      //   189: pop
      //   190: aload_0
      //   191: getfield this$0 : Landroid/support/v4/widget/SlidingPaneLayout;
      //   194: invokevirtual invalidate : ()V
      //   197: return
    }
    
    public boolean tryCaptureView(View param1View, int param1Int) {
      return SlidingPaneLayout.this.mIsUnableToDrag ? false : ((SlidingPaneLayout.LayoutParams)param1View.getLayoutParams()).slideable;
    }
  }
  
  public static class LayoutParams extends ViewGroup.MarginLayoutParams {
    private static final int[] ATTRS = new int[] { 16843137 };
    
    Paint dimPaint;
    
    boolean dimWhenOffset;
    
    boolean slideable;
    
    public float weight = 0.0F;
    
    public LayoutParams() {
      super(-1, -1);
    }
    
    public LayoutParams(int param1Int1, int param1Int2) {
      super(param1Int1, param1Int2);
    }
    
    public LayoutParams(@NonNull Context param1Context, @Nullable AttributeSet param1AttributeSet) {
      super(param1Context, param1AttributeSet);
      TypedArray typedArray = param1Context.obtainStyledAttributes(param1AttributeSet, ATTRS);
      this.weight = typedArray.getFloat(0, 0.0F);
      typedArray.recycle();
    }
    
    public LayoutParams(@NonNull LayoutParams param1LayoutParams) {
      super(param1LayoutParams);
      this.weight = param1LayoutParams.weight;
    }
    
    public LayoutParams(@NonNull ViewGroup.LayoutParams param1LayoutParams) {
      super(param1LayoutParams);
    }
    
    public LayoutParams(@NonNull ViewGroup.MarginLayoutParams param1MarginLayoutParams) {
      super(param1MarginLayoutParams);
    }
  }
  
  public static interface PanelSlideListener {
    void onPanelClosed(@NonNull View param1View);
    
    void onPanelOpened(@NonNull View param1View);
    
    void onPanelSlide(@NonNull View param1View, float param1Float);
  }
  
  static class SavedState extends AbsSavedState {
    public static final Parcelable.Creator<SavedState> CREATOR = (Parcelable.Creator<SavedState>)new Parcelable.ClassLoaderCreator<SavedState>() {
        public SlidingPaneLayout.SavedState createFromParcel(Parcel param2Parcel) {
          return new SlidingPaneLayout.SavedState(param2Parcel, null);
        }
        
        public SlidingPaneLayout.SavedState createFromParcel(Parcel param2Parcel, ClassLoader param2ClassLoader) {
          return new SlidingPaneLayout.SavedState(param2Parcel, null);
        }
        
        public SlidingPaneLayout.SavedState[] newArray(int param2Int) {
          return new SlidingPaneLayout.SavedState[param2Int];
        }
      };
    
    boolean isOpen;
    
    SavedState(Parcel param1Parcel, ClassLoader param1ClassLoader) {
      super(param1Parcel, param1ClassLoader);
      boolean bool;
      if (param1Parcel.readInt() != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      this.isOpen = bool;
    }
    
    SavedState(Parcelable param1Parcelable) {
      super(param1Parcelable);
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      throw new RuntimeException("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e1expr(TypeTransformer.java:496)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:713)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:698)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
    }
  }
  
  static final class null implements Parcelable.ClassLoaderCreator<SavedState> {
    public SlidingPaneLayout.SavedState createFromParcel(Parcel param1Parcel) {
      return new SlidingPaneLayout.SavedState(param1Parcel, null);
    }
    
    public SlidingPaneLayout.SavedState createFromParcel(Parcel param1Parcel, ClassLoader param1ClassLoader) {
      return new SlidingPaneLayout.SavedState(param1Parcel, null);
    }
    
    public SlidingPaneLayout.SavedState[] newArray(int param1Int) {
      return new SlidingPaneLayout.SavedState[param1Int];
    }
  }
  
  public static class SimplePanelSlideListener implements PanelSlideListener {
    public void onPanelClosed(View param1View) {}
    
    public void onPanelOpened(View param1View) {}
    
    public void onPanelSlide(View param1View, float param1Float) {}
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\android\support\v4\widget\SlidingPaneLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */