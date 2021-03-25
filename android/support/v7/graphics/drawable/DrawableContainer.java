package android.support.v7.graphics.drawable;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.SparseArray;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
class DrawableContainer extends Drawable implements Drawable.Callback {
  private static final boolean DEBUG = false;
  
  private static final boolean DEFAULT_DITHER = true;
  
  private static final String TAG = "DrawableContainer";
  
  private int mAlpha = 255;
  
  private Runnable mAnimationRunnable;
  
  private BlockInvalidateCallback mBlockInvalidateCallback;
  
  private int mCurIndex = -1;
  
  private Drawable mCurrDrawable;
  
  private DrawableContainerState mDrawableContainerState;
  
  private long mEnterAnimationEnd;
  
  private long mExitAnimationEnd;
  
  private boolean mHasAlpha;
  
  private Rect mHotspotBounds;
  
  private Drawable mLastDrawable;
  
  private int mLastIndex = -1;
  
  private boolean mMutated;
  
  private void initializeDrawableForDisplay(Drawable paramDrawable) {
    if (this.mBlockInvalidateCallback == null)
      this.mBlockInvalidateCallback = new BlockInvalidateCallback(); 
    paramDrawable.setCallback(this.mBlockInvalidateCallback.wrap(paramDrawable.getCallback()));
    try {
      if (this.mDrawableContainerState.mEnterFadeDuration <= 0 && this.mHasAlpha)
        paramDrawable.setAlpha(this.mAlpha); 
      if (this.mDrawableContainerState.mHasColorFilter) {
        paramDrawable.setColorFilter(this.mDrawableContainerState.mColorFilter);
      } else {
        if (this.mDrawableContainerState.mHasTintList)
          DrawableCompat.setTintList(paramDrawable, this.mDrawableContainerState.mTintList); 
        if (this.mDrawableContainerState.mHasTintMode)
          DrawableCompat.setTintMode(paramDrawable, this.mDrawableContainerState.mTintMode); 
      } 
      paramDrawable.setVisible(isVisible(), true);
      paramDrawable.setDither(this.mDrawableContainerState.mDither);
      paramDrawable.setState(getState());
      paramDrawable.setLevel(getLevel());
      paramDrawable.setBounds(getBounds());
      if (Build.VERSION.SDK_INT >= 23)
        paramDrawable.setLayoutDirection(getLayoutDirection()); 
      if (Build.VERSION.SDK_INT >= 19)
        paramDrawable.setAutoMirrored(this.mDrawableContainerState.mAutoMirrored); 
      Rect rect = this.mHotspotBounds;
      if (Build.VERSION.SDK_INT >= 21 && rect != null)
        paramDrawable.setHotspotBounds(rect.left, rect.top, rect.right, rect.bottom); 
      return;
    } finally {
      paramDrawable.setCallback(this.mBlockInvalidateCallback.unwrap());
    } 
  }
  
  @SuppressLint({"WrongConstant"})
  @TargetApi(23)
  private boolean needsMirroring() {
    return (isAutoMirrored() && getLayoutDirection() == 1);
  }
  
  static int resolveDensity(@Nullable Resources paramResources, int paramInt) {
    if (paramResources != null)
      paramInt = (paramResources.getDisplayMetrics()).densityDpi; 
    int i = paramInt;
    if (paramInt == 0)
      i = 160; 
    return i;
  }
  
  void animate(boolean paramBoolean) {
    // Byte code:
    //   0: iconst_1
    //   1: istore_3
    //   2: aload_0
    //   3: iconst_1
    //   4: putfield mHasAlpha : Z
    //   7: invokestatic uptimeMillis : ()J
    //   10: lstore #4
    //   12: aload_0
    //   13: getfield mCurrDrawable : Landroid/graphics/drawable/Drawable;
    //   16: ifnull -> 104
    //   19: aload_0
    //   20: getfield mEnterAnimationEnd : J
    //   23: lconst_0
    //   24: lcmp
    //   25: ifeq -> 109
    //   28: aload_0
    //   29: getfield mEnterAnimationEnd : J
    //   32: lload #4
    //   34: lcmp
    //   35: ifgt -> 57
    //   38: aload_0
    //   39: getfield mCurrDrawable : Landroid/graphics/drawable/Drawable;
    //   42: aload_0
    //   43: getfield mAlpha : I
    //   46: invokevirtual setAlpha : (I)V
    //   49: aload_0
    //   50: lconst_0
    //   51: putfield mEnterAnimationEnd : J
    //   54: goto -> 109
    //   57: aload_0
    //   58: getfield mEnterAnimationEnd : J
    //   61: lload #4
    //   63: lsub
    //   64: ldc2_w 255
    //   67: lmul
    //   68: l2i
    //   69: aload_0
    //   70: getfield mDrawableContainerState : Landroid/support/v7/graphics/drawable/DrawableContainer$DrawableContainerState;
    //   73: getfield mEnterFadeDuration : I
    //   76: idiv
    //   77: istore_2
    //   78: aload_0
    //   79: getfield mCurrDrawable : Landroid/graphics/drawable/Drawable;
    //   82: sipush #255
    //   85: iload_2
    //   86: isub
    //   87: aload_0
    //   88: getfield mAlpha : I
    //   91: imul
    //   92: sipush #255
    //   95: idiv
    //   96: invokevirtual setAlpha : (I)V
    //   99: iconst_1
    //   100: istore_2
    //   101: goto -> 111
    //   104: aload_0
    //   105: lconst_0
    //   106: putfield mEnterAnimationEnd : J
    //   109: iconst_0
    //   110: istore_2
    //   111: aload_0
    //   112: getfield mLastDrawable : Landroid/graphics/drawable/Drawable;
    //   115: ifnull -> 208
    //   118: aload_0
    //   119: getfield mExitAnimationEnd : J
    //   122: lconst_0
    //   123: lcmp
    //   124: ifeq -> 213
    //   127: aload_0
    //   128: getfield mExitAnimationEnd : J
    //   131: lload #4
    //   133: lcmp
    //   134: ifgt -> 165
    //   137: aload_0
    //   138: getfield mLastDrawable : Landroid/graphics/drawable/Drawable;
    //   141: iconst_0
    //   142: iconst_0
    //   143: invokevirtual setVisible : (ZZ)Z
    //   146: pop
    //   147: aload_0
    //   148: aconst_null
    //   149: putfield mLastDrawable : Landroid/graphics/drawable/Drawable;
    //   152: aload_0
    //   153: iconst_m1
    //   154: putfield mLastIndex : I
    //   157: aload_0
    //   158: lconst_0
    //   159: putfield mExitAnimationEnd : J
    //   162: goto -> 213
    //   165: aload_0
    //   166: getfield mExitAnimationEnd : J
    //   169: lload #4
    //   171: lsub
    //   172: ldc2_w 255
    //   175: lmul
    //   176: l2i
    //   177: aload_0
    //   178: getfield mDrawableContainerState : Landroid/support/v7/graphics/drawable/DrawableContainer$DrawableContainerState;
    //   181: getfield mExitFadeDuration : I
    //   184: idiv
    //   185: istore_2
    //   186: aload_0
    //   187: getfield mLastDrawable : Landroid/graphics/drawable/Drawable;
    //   190: iload_2
    //   191: aload_0
    //   192: getfield mAlpha : I
    //   195: imul
    //   196: sipush #255
    //   199: idiv
    //   200: invokevirtual setAlpha : (I)V
    //   203: iload_3
    //   204: istore_2
    //   205: goto -> 213
    //   208: aload_0
    //   209: lconst_0
    //   210: putfield mExitAnimationEnd : J
    //   213: iload_1
    //   214: ifeq -> 235
    //   217: iload_2
    //   218: ifeq -> 235
    //   221: aload_0
    //   222: aload_0
    //   223: getfield mAnimationRunnable : Ljava/lang/Runnable;
    //   226: lload #4
    //   228: ldc2_w 16
    //   231: ladd
    //   232: invokevirtual scheduleSelf : (Ljava/lang/Runnable;J)V
    //   235: return
  }
  
  @RequiresApi(21)
  public void applyTheme(@NonNull Resources.Theme paramTheme) {
    this.mDrawableContainerState.applyTheme(paramTheme);
  }
  
  @RequiresApi(21)
  public boolean canApplyTheme() {
    return this.mDrawableContainerState.canApplyTheme();
  }
  
  void clearMutated() {
    this.mDrawableContainerState.clearMutated();
    this.mMutated = false;
  }
  
  DrawableContainerState cloneConstantState() {
    return this.mDrawableContainerState;
  }
  
  public void draw(@NonNull Canvas paramCanvas) {
    if (this.mCurrDrawable != null)
      this.mCurrDrawable.draw(paramCanvas); 
    if (this.mLastDrawable != null)
      this.mLastDrawable.draw(paramCanvas); 
  }
  
  public int getAlpha() {
    return this.mAlpha;
  }
  
  public int getChangingConfigurations() {
    return super.getChangingConfigurations() | this.mDrawableContainerState.getChangingConfigurations();
  }
  
  public final Drawable.ConstantState getConstantState() {
    if (this.mDrawableContainerState.canConstantState()) {
      this.mDrawableContainerState.mChangingConfigurations = getChangingConfigurations();
      return this.mDrawableContainerState;
    } 
    return null;
  }
  
  @NonNull
  public Drawable getCurrent() {
    return this.mCurrDrawable;
  }
  
  int getCurrentIndex() {
    return this.mCurIndex;
  }
  
  public void getHotspotBounds(@NonNull Rect paramRect) {
    if (this.mHotspotBounds != null) {
      paramRect.set(this.mHotspotBounds);
      return;
    } 
    super.getHotspotBounds(paramRect);
  }
  
  public int getIntrinsicHeight() {
    return this.mDrawableContainerState.isConstantSize() ? this.mDrawableContainerState.getConstantHeight() : ((this.mCurrDrawable != null) ? this.mCurrDrawable.getIntrinsicHeight() : -1);
  }
  
  public int getIntrinsicWidth() {
    return this.mDrawableContainerState.isConstantSize() ? this.mDrawableContainerState.getConstantWidth() : ((this.mCurrDrawable != null) ? this.mCurrDrawable.getIntrinsicWidth() : -1);
  }
  
  public int getMinimumHeight() {
    return this.mDrawableContainerState.isConstantSize() ? this.mDrawableContainerState.getConstantMinimumHeight() : ((this.mCurrDrawable != null) ? this.mCurrDrawable.getMinimumHeight() : 0);
  }
  
  public int getMinimumWidth() {
    return this.mDrawableContainerState.isConstantSize() ? this.mDrawableContainerState.getConstantMinimumWidth() : ((this.mCurrDrawable != null) ? this.mCurrDrawable.getMinimumWidth() : 0);
  }
  
  public int getOpacity() {
    return (this.mCurrDrawable == null || !this.mCurrDrawable.isVisible()) ? -2 : this.mDrawableContainerState.getOpacity();
  }
  
  @RequiresApi(21)
  public void getOutline(@NonNull Outline paramOutline) {
    if (this.mCurrDrawable != null)
      this.mCurrDrawable.getOutline(paramOutline); 
  }
  
  public boolean getPadding(@NonNull Rect paramRect) {
    boolean bool;
    Rect rect = this.mDrawableContainerState.getConstantPadding();
    if (rect != null) {
      paramRect.set(rect);
      int i = rect.left;
      int j = rect.top;
      int k = rect.bottom;
      if ((rect.right | i | j | k) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
    } else if (this.mCurrDrawable != null) {
      bool = this.mCurrDrawable.getPadding(paramRect);
    } else {
      bool = super.getPadding(paramRect);
    } 
    if (needsMirroring()) {
      int i = paramRect.left;
      paramRect.left = paramRect.right;
      paramRect.right = i;
    } 
    return bool;
  }
  
  public void invalidateDrawable(@NonNull Drawable paramDrawable) {
    if (this.mDrawableContainerState != null)
      this.mDrawableContainerState.invalidateCache(); 
    if (paramDrawable == this.mCurrDrawable && getCallback() != null)
      getCallback().invalidateDrawable(this); 
  }
  
  public boolean isAutoMirrored() {
    return this.mDrawableContainerState.mAutoMirrored;
  }
  
  public boolean isStateful() {
    return this.mDrawableContainerState.isStateful();
  }
  
  public void jumpToCurrentState() {
    boolean bool;
    if (this.mLastDrawable != null) {
      this.mLastDrawable.jumpToCurrentState();
      this.mLastDrawable = null;
      this.mLastIndex = -1;
      bool = true;
    } else {
      bool = false;
    } 
    if (this.mCurrDrawable != null) {
      this.mCurrDrawable.jumpToCurrentState();
      if (this.mHasAlpha)
        this.mCurrDrawable.setAlpha(this.mAlpha); 
    } 
    if (this.mExitAnimationEnd != 0L) {
      this.mExitAnimationEnd = 0L;
      bool = true;
    } 
    if (this.mEnterAnimationEnd != 0L) {
      this.mEnterAnimationEnd = 0L;
      bool = true;
    } 
    if (bool)
      invalidateSelf(); 
  }
  
  @NonNull
  public Drawable mutate() {
    if (!this.mMutated && super.mutate() == this) {
      DrawableContainerState drawableContainerState = cloneConstantState();
      drawableContainerState.mutate();
      setConstantState(drawableContainerState);
      this.mMutated = true;
    } 
    return this;
  }
  
  protected void onBoundsChange(Rect paramRect) {
    if (this.mLastDrawable != null)
      this.mLastDrawable.setBounds(paramRect); 
    if (this.mCurrDrawable != null)
      this.mCurrDrawable.setBounds(paramRect); 
  }
  
  public boolean onLayoutDirectionChanged(int paramInt) {
    return this.mDrawableContainerState.setLayoutDirection(paramInt, getCurrentIndex());
  }
  
  protected boolean onLevelChange(int paramInt) {
    return (this.mLastDrawable != null) ? this.mLastDrawable.setLevel(paramInt) : ((this.mCurrDrawable != null) ? this.mCurrDrawable.setLevel(paramInt) : false);
  }
  
  protected boolean onStateChange(int[] paramArrayOfint) {
    return (this.mLastDrawable != null) ? this.mLastDrawable.setState(paramArrayOfint) : ((this.mCurrDrawable != null) ? this.mCurrDrawable.setState(paramArrayOfint) : false);
  }
  
  public void scheduleDrawable(@NonNull Drawable paramDrawable, @NonNull Runnable paramRunnable, long paramLong) {
    if (paramDrawable == this.mCurrDrawable && getCallback() != null)
      getCallback().scheduleDrawable(this, paramRunnable, paramLong); 
  }
  
  boolean selectDrawable(int paramInt) {
    if (paramInt == this.mCurIndex)
      return false; 
    long l = SystemClock.uptimeMillis();
    if (this.mDrawableContainerState.mExitFadeDuration > 0) {
      if (this.mLastDrawable != null)
        this.mLastDrawable.setVisible(false, false); 
      if (this.mCurrDrawable != null) {
        this.mLastDrawable = this.mCurrDrawable;
        this.mLastIndex = this.mCurIndex;
        this.mExitAnimationEnd = l + this.mDrawableContainerState.mExitFadeDuration;
      } else {
        this.mLastDrawable = null;
        this.mLastIndex = -1;
        this.mExitAnimationEnd = 0L;
      } 
    } else if (this.mCurrDrawable != null) {
      this.mCurrDrawable.setVisible(false, false);
    } 
    if (paramInt >= 0 && paramInt < this.mDrawableContainerState.mNumChildren) {
      Drawable drawable = this.mDrawableContainerState.getChild(paramInt);
      this.mCurrDrawable = drawable;
      this.mCurIndex = paramInt;
      if (drawable != null) {
        if (this.mDrawableContainerState.mEnterFadeDuration > 0)
          this.mEnterAnimationEnd = l + this.mDrawableContainerState.mEnterFadeDuration; 
        initializeDrawableForDisplay(drawable);
      } 
    } else {
      this.mCurrDrawable = null;
      this.mCurIndex = -1;
    } 
    if (this.mEnterAnimationEnd != 0L || this.mExitAnimationEnd != 0L) {
      if (this.mAnimationRunnable == null) {
        this.mAnimationRunnable = new Runnable() {
            public void run() {
              DrawableContainer.this.animate(true);
              DrawableContainer.this.invalidateSelf();
            }
          };
      } else {
        unscheduleSelf(this.mAnimationRunnable);
      } 
      animate(true);
    } 
    invalidateSelf();
    return true;
  }
  
  public void setAlpha(int paramInt) {
    if (!this.mHasAlpha || this.mAlpha != paramInt) {
      this.mHasAlpha = true;
      this.mAlpha = paramInt;
      if (this.mCurrDrawable != null) {
        if (this.mEnterAnimationEnd == 0L) {
          this.mCurrDrawable.setAlpha(paramInt);
          return;
        } 
        animate(false);
      } 
    } 
  }
  
  public void setAutoMirrored(boolean paramBoolean) {
    if (this.mDrawableContainerState.mAutoMirrored != paramBoolean) {
      this.mDrawableContainerState.mAutoMirrored = paramBoolean;
      if (this.mCurrDrawable != null)
        DrawableCompat.setAutoMirrored(this.mCurrDrawable, this.mDrawableContainerState.mAutoMirrored); 
    } 
  }
  
  public void setColorFilter(ColorFilter paramColorFilter) {
    this.mDrawableContainerState.mHasColorFilter = true;
    if (this.mDrawableContainerState.mColorFilter != paramColorFilter) {
      this.mDrawableContainerState.mColorFilter = paramColorFilter;
      if (this.mCurrDrawable != null)
        this.mCurrDrawable.setColorFilter(paramColorFilter); 
    } 
  }
  
  protected void setConstantState(DrawableContainerState paramDrawableContainerState) {
    this.mDrawableContainerState = paramDrawableContainerState;
    if (this.mCurIndex >= 0) {
      this.mCurrDrawable = paramDrawableContainerState.getChild(this.mCurIndex);
      if (this.mCurrDrawable != null)
        initializeDrawableForDisplay(this.mCurrDrawable); 
    } 
    this.mLastIndex = -1;
    this.mLastDrawable = null;
  }
  
  void setCurrentIndex(int paramInt) {
    selectDrawable(paramInt);
  }
  
  public void setDither(boolean paramBoolean) {
    if (this.mDrawableContainerState.mDither != paramBoolean) {
      this.mDrawableContainerState.mDither = paramBoolean;
      if (this.mCurrDrawable != null)
        this.mCurrDrawable.setDither(this.mDrawableContainerState.mDither); 
    } 
  }
  
  public void setEnterFadeDuration(int paramInt) {
    this.mDrawableContainerState.mEnterFadeDuration = paramInt;
  }
  
  public void setExitFadeDuration(int paramInt) {
    this.mDrawableContainerState.mExitFadeDuration = paramInt;
  }
  
  public void setHotspot(float paramFloat1, float paramFloat2) {
    if (this.mCurrDrawable != null)
      DrawableCompat.setHotspot(this.mCurrDrawable, paramFloat1, paramFloat2); 
  }
  
  public void setHotspotBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if (this.mHotspotBounds == null) {
      this.mHotspotBounds = new Rect(paramInt1, paramInt2, paramInt3, paramInt4);
    } else {
      this.mHotspotBounds.set(paramInt1, paramInt2, paramInt3, paramInt4);
    } 
    if (this.mCurrDrawable != null)
      DrawableCompat.setHotspotBounds(this.mCurrDrawable, paramInt1, paramInt2, paramInt3, paramInt4); 
  }
  
  public void setTintList(ColorStateList paramColorStateList) {
    this.mDrawableContainerState.mHasTintList = true;
    if (this.mDrawableContainerState.mTintList != paramColorStateList) {
      this.mDrawableContainerState.mTintList = paramColorStateList;
      DrawableCompat.setTintList(this.mCurrDrawable, paramColorStateList);
    } 
  }
  
  public void setTintMode(@NonNull PorterDuff.Mode paramMode) {
    this.mDrawableContainerState.mHasTintMode = true;
    if (this.mDrawableContainerState.mTintMode != paramMode) {
      this.mDrawableContainerState.mTintMode = paramMode;
      DrawableCompat.setTintMode(this.mCurrDrawable, paramMode);
    } 
  }
  
  public boolean setVisible(boolean paramBoolean1, boolean paramBoolean2) {
    boolean bool = super.setVisible(paramBoolean1, paramBoolean2);
    if (this.mLastDrawable != null)
      this.mLastDrawable.setVisible(paramBoolean1, paramBoolean2); 
    if (this.mCurrDrawable != null)
      this.mCurrDrawable.setVisible(paramBoolean1, paramBoolean2); 
    return bool;
  }
  
  public void unscheduleDrawable(@NonNull Drawable paramDrawable, @NonNull Runnable paramRunnable) {
    if (paramDrawable == this.mCurrDrawable && getCallback() != null)
      getCallback().unscheduleDrawable(this, paramRunnable); 
  }
  
  final void updateDensity(Resources paramResources) {
    this.mDrawableContainerState.updateDensity(paramResources);
  }
  
  static class BlockInvalidateCallback implements Drawable.Callback {
    private Drawable.Callback mCallback;
    
    public void invalidateDrawable(@NonNull Drawable param1Drawable) {}
    
    public void scheduleDrawable(@NonNull Drawable param1Drawable, @NonNull Runnable param1Runnable, long param1Long) {
      if (this.mCallback != null)
        this.mCallback.scheduleDrawable(param1Drawable, param1Runnable, param1Long); 
    }
    
    public void unscheduleDrawable(@NonNull Drawable param1Drawable, @NonNull Runnable param1Runnable) {
      if (this.mCallback != null)
        this.mCallback.unscheduleDrawable(param1Drawable, param1Runnable); 
    }
    
    public Drawable.Callback unwrap() {
      Drawable.Callback callback = this.mCallback;
      this.mCallback = null;
      return callback;
    }
    
    public BlockInvalidateCallback wrap(Drawable.Callback param1Callback) {
      this.mCallback = param1Callback;
      return this;
    }
  }
  
  static abstract class DrawableContainerState extends Drawable.ConstantState {
    boolean mAutoMirrored;
    
    boolean mCanConstantState;
    
    int mChangingConfigurations;
    
    boolean mCheckedConstantSize;
    
    boolean mCheckedConstantState;
    
    boolean mCheckedOpacity;
    
    boolean mCheckedPadding;
    
    boolean mCheckedStateful;
    
    int mChildrenChangingConfigurations;
    
    ColorFilter mColorFilter;
    
    int mConstantHeight;
    
    int mConstantMinimumHeight;
    
    int mConstantMinimumWidth;
    
    Rect mConstantPadding;
    
    boolean mConstantSize;
    
    int mConstantWidth;
    
    int mDensity;
    
    boolean mDither;
    
    SparseArray<Drawable.ConstantState> mDrawableFutures;
    
    Drawable[] mDrawables;
    
    int mEnterFadeDuration;
    
    int mExitFadeDuration;
    
    boolean mHasColorFilter;
    
    boolean mHasTintList;
    
    boolean mHasTintMode;
    
    int mLayoutDirection;
    
    boolean mMutated;
    
    int mNumChildren;
    
    int mOpacity;
    
    final DrawableContainer mOwner;
    
    Resources mSourceRes;
    
    boolean mStateful;
    
    ColorStateList mTintList;
    
    PorterDuff.Mode mTintMode;
    
    boolean mVariablePadding;
    
    DrawableContainerState(DrawableContainerState param1DrawableContainerState, DrawableContainer param1DrawableContainer, Resources param1Resources) {
      int i;
      this.mDensity = 160;
      byte b = 0;
      this.mVariablePadding = false;
      this.mConstantSize = false;
      this.mDither = true;
      this.mEnterFadeDuration = 0;
      this.mExitFadeDuration = 0;
      this.mOwner = param1DrawableContainer;
      if (param1Resources != null) {
        Resources resources = param1Resources;
      } else if (param1DrawableContainerState != null) {
        Resources resources = param1DrawableContainerState.mSourceRes;
      } else {
        param1DrawableContainer = null;
      } 
      this.mSourceRes = (Resources)param1DrawableContainer;
      if (param1DrawableContainerState != null) {
        i = param1DrawableContainerState.mDensity;
      } else {
        i = 0;
      } 
      this.mDensity = DrawableContainer.resolveDensity(param1Resources, i);
      if (param1DrawableContainerState != null) {
        this.mChangingConfigurations = param1DrawableContainerState.mChangingConfigurations;
        this.mChildrenChangingConfigurations = param1DrawableContainerState.mChildrenChangingConfigurations;
        this.mCheckedConstantState = true;
        this.mCanConstantState = true;
        this.mVariablePadding = param1DrawableContainerState.mVariablePadding;
        this.mConstantSize = param1DrawableContainerState.mConstantSize;
        this.mDither = param1DrawableContainerState.mDither;
        this.mMutated = param1DrawableContainerState.mMutated;
        this.mLayoutDirection = param1DrawableContainerState.mLayoutDirection;
        this.mEnterFadeDuration = param1DrawableContainerState.mEnterFadeDuration;
        this.mExitFadeDuration = param1DrawableContainerState.mExitFadeDuration;
        this.mAutoMirrored = param1DrawableContainerState.mAutoMirrored;
        this.mColorFilter = param1DrawableContainerState.mColorFilter;
        this.mHasColorFilter = param1DrawableContainerState.mHasColorFilter;
        this.mTintList = param1DrawableContainerState.mTintList;
        this.mTintMode = param1DrawableContainerState.mTintMode;
        this.mHasTintList = param1DrawableContainerState.mHasTintList;
        this.mHasTintMode = param1DrawableContainerState.mHasTintMode;
        if (param1DrawableContainerState.mDensity == this.mDensity) {
          if (param1DrawableContainerState.mCheckedPadding) {
            this.mConstantPadding = new Rect(param1DrawableContainerState.mConstantPadding);
            this.mCheckedPadding = true;
          } 
          if (param1DrawableContainerState.mCheckedConstantSize) {
            this.mConstantWidth = param1DrawableContainerState.mConstantWidth;
            this.mConstantHeight = param1DrawableContainerState.mConstantHeight;
            this.mConstantMinimumWidth = param1DrawableContainerState.mConstantMinimumWidth;
            this.mConstantMinimumHeight = param1DrawableContainerState.mConstantMinimumHeight;
            this.mCheckedConstantSize = true;
          } 
        } 
        if (param1DrawableContainerState.mCheckedOpacity) {
          this.mOpacity = param1DrawableContainerState.mOpacity;
          this.mCheckedOpacity = true;
        } 
        if (param1DrawableContainerState.mCheckedStateful) {
          this.mStateful = param1DrawableContainerState.mStateful;
          this.mCheckedStateful = true;
        } 
        Drawable[] arrayOfDrawable = param1DrawableContainerState.mDrawables;
        this.mDrawables = new Drawable[arrayOfDrawable.length];
        this.mNumChildren = param1DrawableContainerState.mNumChildren;
        SparseArray<Drawable.ConstantState> sparseArray = param1DrawableContainerState.mDrawableFutures;
        if (sparseArray != null) {
          this.mDrawableFutures = sparseArray.clone();
        } else {
          this.mDrawableFutures = new SparseArray(this.mNumChildren);
        } 
        int j = this.mNumChildren;
        for (i = b; i < j; i++) {
          if (arrayOfDrawable[i] != null) {
            Drawable.ConstantState constantState = arrayOfDrawable[i].getConstantState();
            if (constantState != null) {
              this.mDrawableFutures.put(i, constantState);
            } else {
              this.mDrawables[i] = arrayOfDrawable[i];
            } 
          } 
        } 
      } else {
        this.mDrawables = new Drawable[10];
        this.mNumChildren = 0;
      } 
    }
    
    private void createAllFutures() {
      if (this.mDrawableFutures != null) {
        int j = this.mDrawableFutures.size();
        for (int i = 0; i < j; i++) {
          int k = this.mDrawableFutures.keyAt(i);
          Drawable.ConstantState constantState = (Drawable.ConstantState)this.mDrawableFutures.valueAt(i);
          this.mDrawables[k] = prepareDrawable(constantState.newDrawable(this.mSourceRes));
        } 
        this.mDrawableFutures = null;
      } 
    }
    
    private Drawable prepareDrawable(Drawable param1Drawable) {
      if (Build.VERSION.SDK_INT >= 23)
        param1Drawable.setLayoutDirection(this.mLayoutDirection); 
      param1Drawable = param1Drawable.mutate();
      param1Drawable.setCallback(this.mOwner);
      return param1Drawable;
    }
    
    public final int addChild(Drawable param1Drawable) {
      int i = this.mNumChildren;
      if (i >= this.mDrawables.length)
        growArray(i, i + 10); 
      param1Drawable.mutate();
      param1Drawable.setVisible(false, true);
      param1Drawable.setCallback(this.mOwner);
      this.mDrawables[i] = param1Drawable;
      this.mNumChildren++;
      int j = this.mChildrenChangingConfigurations;
      this.mChildrenChangingConfigurations = param1Drawable.getChangingConfigurations() | j;
      invalidateCache();
      this.mConstantPadding = null;
      this.mCheckedPadding = false;
      this.mCheckedConstantSize = false;
      this.mCheckedConstantState = false;
      return i;
    }
    
    @RequiresApi(21)
    final void applyTheme(Resources.Theme param1Theme) {
      if (param1Theme != null) {
        createAllFutures();
        int j = this.mNumChildren;
        Drawable[] arrayOfDrawable = this.mDrawables;
        for (int i = 0; i < j; i++) {
          if (arrayOfDrawable[i] != null && arrayOfDrawable[i].canApplyTheme()) {
            arrayOfDrawable[i].applyTheme(param1Theme);
            this.mChildrenChangingConfigurations |= arrayOfDrawable[i].getChangingConfigurations();
          } 
        } 
        updateDensity(param1Theme.getResources());
      } 
    }
    
    @RequiresApi(21)
    public boolean canApplyTheme() {
      int j = this.mNumChildren;
      Drawable[] arrayOfDrawable = this.mDrawables;
      for (int i = 0; i < j; i++) {
        Drawable drawable = arrayOfDrawable[i];
        if (drawable != null) {
          if (drawable.canApplyTheme())
            return true; 
        } else {
          Drawable.ConstantState constantState = (Drawable.ConstantState)this.mDrawableFutures.get(i);
          if (constantState != null && constantState.canApplyTheme())
            return true; 
        } 
      } 
      return false;
    }
    
    public boolean canConstantState() {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield mCheckedConstantState : Z
      //   6: ifeq -> 18
      //   9: aload_0
      //   10: getfield mCanConstantState : Z
      //   13: istore_3
      //   14: aload_0
      //   15: monitorexit
      //   16: iload_3
      //   17: ireturn
      //   18: aload_0
      //   19: invokespecial createAllFutures : ()V
      //   22: aload_0
      //   23: iconst_1
      //   24: putfield mCheckedConstantState : Z
      //   27: aload_0
      //   28: getfield mNumChildren : I
      //   31: istore_2
      //   32: aload_0
      //   33: getfield mDrawables : [Landroid/graphics/drawable/Drawable;
      //   36: astore #4
      //   38: iconst_0
      //   39: istore_1
      //   40: iload_1
      //   41: iload_2
      //   42: if_icmpge -> 71
      //   45: aload #4
      //   47: iload_1
      //   48: aaload
      //   49: invokevirtual getConstantState : ()Landroid/graphics/drawable/Drawable$ConstantState;
      //   52: ifnonnull -> 64
      //   55: aload_0
      //   56: iconst_0
      //   57: putfield mCanConstantState : Z
      //   60: aload_0
      //   61: monitorexit
      //   62: iconst_0
      //   63: ireturn
      //   64: iload_1
      //   65: iconst_1
      //   66: iadd
      //   67: istore_1
      //   68: goto -> 40
      //   71: aload_0
      //   72: iconst_1
      //   73: putfield mCanConstantState : Z
      //   76: aload_0
      //   77: monitorexit
      //   78: iconst_1
      //   79: ireturn
      //   80: astore #4
      //   82: aload_0
      //   83: monitorexit
      //   84: aload #4
      //   86: athrow
      // Exception table:
      //   from	to	target	type
      //   2	14	80	finally
      //   18	38	80	finally
      //   45	60	80	finally
      //   71	76	80	finally
    }
    
    final void clearMutated() {
      this.mMutated = false;
    }
    
    protected void computeConstantSize() {
      this.mCheckedConstantSize = true;
      createAllFutures();
      int j = this.mNumChildren;
      Drawable[] arrayOfDrawable = this.mDrawables;
      this.mConstantHeight = -1;
      this.mConstantWidth = -1;
      int i = 0;
      this.mConstantMinimumHeight = 0;
      this.mConstantMinimumWidth = 0;
      while (i < j) {
        Drawable drawable = arrayOfDrawable[i];
        int k = drawable.getIntrinsicWidth();
        if (k > this.mConstantWidth)
          this.mConstantWidth = k; 
        k = drawable.getIntrinsicHeight();
        if (k > this.mConstantHeight)
          this.mConstantHeight = k; 
        k = drawable.getMinimumWidth();
        if (k > this.mConstantMinimumWidth)
          this.mConstantMinimumWidth = k; 
        k = drawable.getMinimumHeight();
        if (k > this.mConstantMinimumHeight)
          this.mConstantMinimumHeight = k; 
        i++;
      } 
    }
    
    final int getCapacity() {
      return this.mDrawables.length;
    }
    
    public int getChangingConfigurations() {
      return this.mChangingConfigurations | this.mChildrenChangingConfigurations;
    }
    
    public final Drawable getChild(int param1Int) {
      Drawable drawable = this.mDrawables[param1Int];
      if (drawable != null)
        return drawable; 
      if (this.mDrawableFutures != null) {
        int i = this.mDrawableFutures.indexOfKey(param1Int);
        if (i >= 0) {
          drawable = prepareDrawable(((Drawable.ConstantState)this.mDrawableFutures.valueAt(i)).newDrawable(this.mSourceRes));
          this.mDrawables[param1Int] = drawable;
          this.mDrawableFutures.removeAt(i);
          if (this.mDrawableFutures.size() == 0)
            this.mDrawableFutures = null; 
          return drawable;
        } 
      } 
      return null;
    }
    
    public final int getChildCount() {
      return this.mNumChildren;
    }
    
    public final int getConstantHeight() {
      if (!this.mCheckedConstantSize)
        computeConstantSize(); 
      return this.mConstantHeight;
    }
    
    public final int getConstantMinimumHeight() {
      if (!this.mCheckedConstantSize)
        computeConstantSize(); 
      return this.mConstantMinimumHeight;
    }
    
    public final int getConstantMinimumWidth() {
      if (!this.mCheckedConstantSize)
        computeConstantSize(); 
      return this.mConstantMinimumWidth;
    }
    
    public final Rect getConstantPadding() {
      if (this.mVariablePadding)
        return null; 
      if (this.mConstantPadding != null || this.mCheckedPadding)
        return this.mConstantPadding; 
      createAllFutures();
      Rect rect2 = new Rect();
      int j = this.mNumChildren;
      Drawable[] arrayOfDrawable = this.mDrawables;
      Rect rect1 = null;
      int i = 0;
      while (i < j) {
        Rect rect = rect1;
        if (arrayOfDrawable[i].getPadding(rect2)) {
          Rect rect3 = rect1;
          if (rect1 == null)
            rect3 = new Rect(0, 0, 0, 0); 
          if (rect2.left > rect3.left)
            rect3.left = rect2.left; 
          if (rect2.top > rect3.top)
            rect3.top = rect2.top; 
          if (rect2.right > rect3.right)
            rect3.right = rect2.right; 
          rect = rect3;
          if (rect2.bottom > rect3.bottom) {
            rect3.bottom = rect2.bottom;
            rect = rect3;
          } 
        } 
        i++;
        rect1 = rect;
      } 
      this.mCheckedPadding = true;
      this.mConstantPadding = rect1;
      return rect1;
    }
    
    public final int getConstantWidth() {
      if (!this.mCheckedConstantSize)
        computeConstantSize(); 
      return this.mConstantWidth;
    }
    
    public final int getEnterFadeDuration() {
      return this.mEnterFadeDuration;
    }
    
    public final int getExitFadeDuration() {
      return this.mExitFadeDuration;
    }
    
    public final int getOpacity() {
      if (this.mCheckedOpacity)
        return this.mOpacity; 
      createAllFutures();
      int k = this.mNumChildren;
      Drawable[] arrayOfDrawable = this.mDrawables;
      if (k > 0) {
        i = arrayOfDrawable[0].getOpacity();
      } else {
        i = -2;
      } 
      byte b = 1;
      int j = i;
      for (int i = b; i < k; i++)
        j = Drawable.resolveOpacity(j, arrayOfDrawable[i].getOpacity()); 
      this.mOpacity = j;
      this.mCheckedOpacity = true;
      return j;
    }
    
    public void growArray(int param1Int1, int param1Int2) {
      Drawable[] arrayOfDrawable = new Drawable[param1Int2];
      System.arraycopy(this.mDrawables, 0, arrayOfDrawable, 0, param1Int1);
      this.mDrawables = arrayOfDrawable;
    }
    
    void invalidateCache() {
      this.mCheckedOpacity = false;
      this.mCheckedStateful = false;
    }
    
    public final boolean isConstantSize() {
      return this.mConstantSize;
    }
    
    public final boolean isStateful() {
      boolean bool1;
      if (this.mCheckedStateful)
        return this.mStateful; 
      createAllFutures();
      int j = this.mNumChildren;
      Drawable[] arrayOfDrawable = this.mDrawables;
      boolean bool2 = false;
      int i = 0;
      while (true) {
        bool1 = bool2;
        if (i < j) {
          if (arrayOfDrawable[i].isStateful()) {
            bool1 = true;
            break;
          } 
          i++;
          continue;
        } 
        break;
      } 
      this.mStateful = bool1;
      this.mCheckedStateful = true;
      return bool1;
    }
    
    void mutate() {
      int j = this.mNumChildren;
      Drawable[] arrayOfDrawable = this.mDrawables;
      for (int i = 0; i < j; i++) {
        if (arrayOfDrawable[i] != null)
          arrayOfDrawable[i].mutate(); 
      } 
      this.mMutated = true;
    }
    
    public final void setConstantSize(boolean param1Boolean) {
      this.mConstantSize = param1Boolean;
    }
    
    public final void setEnterFadeDuration(int param1Int) {
      this.mEnterFadeDuration = param1Int;
    }
    
    public final void setExitFadeDuration(int param1Int) {
      this.mExitFadeDuration = param1Int;
    }
    
    final boolean setLayoutDirection(int param1Int1, int param1Int2) {
      int j = this.mNumChildren;
      Drawable[] arrayOfDrawable = this.mDrawables;
      int i = 0;
      boolean bool;
      for (bool = false; i < j; bool = bool1) {
        boolean bool1 = bool;
        if (arrayOfDrawable[i] != null) {
          boolean bool2;
          if (Build.VERSION.SDK_INT >= 23) {
            bool2 = arrayOfDrawable[i].setLayoutDirection(param1Int1);
          } else {
            bool2 = false;
          } 
          bool1 = bool;
          if (i == param1Int2)
            bool1 = bool2; 
        } 
        i++;
      } 
      this.mLayoutDirection = param1Int1;
      return bool;
    }
    
    public final void setVariablePadding(boolean param1Boolean) {
      this.mVariablePadding = param1Boolean;
    }
    
    final void updateDensity(Resources param1Resources) {
      if (param1Resources != null) {
        this.mSourceRes = param1Resources;
        int i = DrawableContainer.resolveDensity(param1Resources, this.mDensity);
        int j = this.mDensity;
        this.mDensity = i;
        if (j != i) {
          this.mCheckedConstantSize = false;
          this.mCheckedPadding = false;
        } 
      } 
    }
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\android\support\v7\graphics\drawable\DrawableContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */