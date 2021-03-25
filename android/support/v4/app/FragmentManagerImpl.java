package android.support.v4.app;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.arch.lifecycle.p;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArraySet;
import android.support.v4.util.DebugUtils;
import android.support.v4.util.LogWriter;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

final class FragmentManagerImpl extends FragmentManager implements LayoutInflater.Factory2 {
  static final Interpolator ACCELERATE_CUBIC;
  
  static final Interpolator ACCELERATE_QUINT;
  
  static final int ANIM_DUR = 220;
  
  public static final int ANIM_STYLE_CLOSE_ENTER = 3;
  
  public static final int ANIM_STYLE_CLOSE_EXIT = 4;
  
  public static final int ANIM_STYLE_FADE_ENTER = 5;
  
  public static final int ANIM_STYLE_FADE_EXIT = 6;
  
  public static final int ANIM_STYLE_OPEN_ENTER = 1;
  
  public static final int ANIM_STYLE_OPEN_EXIT = 2;
  
  static boolean DEBUG = false;
  
  static final Interpolator DECELERATE_CUBIC;
  
  static final Interpolator DECELERATE_QUINT = (Interpolator)new DecelerateInterpolator(2.5F);
  
  static final String TAG = "FragmentManager";
  
  static final String TARGET_REQUEST_CODE_STATE_TAG = "android:target_req_state";
  
  static final String TARGET_STATE_TAG = "android:target_state";
  
  static final String USER_VISIBLE_HINT_TAG = "android:user_visible_hint";
  
  static final String VIEW_STATE_TAG = "android:view_state";
  
  static Field sAnimationListenerField;
  
  SparseArray<Fragment> mActive;
  
  final ArrayList<Fragment> mAdded = new ArrayList<Fragment>();
  
  ArrayList<Integer> mAvailBackStackIndices;
  
  ArrayList<BackStackRecord> mBackStack;
  
  ArrayList<FragmentManager.OnBackStackChangedListener> mBackStackChangeListeners;
  
  ArrayList<BackStackRecord> mBackStackIndices;
  
  FragmentContainer mContainer;
  
  ArrayList<Fragment> mCreatedMenus;
  
  int mCurState = 0;
  
  boolean mDestroyed;
  
  Runnable mExecCommit = new Runnable() {
      public void run() {
        FragmentManagerImpl.this.execPendingActions();
      }
    };
  
  boolean mExecutingActions;
  
  boolean mHavePendingDeferredStart;
  
  FragmentHostCallback mHost;
  
  private final CopyOnWriteArrayList<FragmentLifecycleCallbacksHolder> mLifecycleCallbacks = new CopyOnWriteArrayList<FragmentLifecycleCallbacksHolder>();
  
  boolean mNeedMenuInvalidate;
  
  int mNextFragmentIndex = 0;
  
  String mNoTransactionsBecause;
  
  Fragment mParent;
  
  ArrayList<OpGenerator> mPendingActions;
  
  ArrayList<StartEnterTransitionListener> mPostponedTransactions;
  
  @Nullable
  Fragment mPrimaryNav;
  
  FragmentManagerNonConfig mSavedNonConfig;
  
  SparseArray<Parcelable> mStateArray = null;
  
  Bundle mStateBundle = null;
  
  boolean mStateSaved;
  
  boolean mStopped;
  
  ArrayList<Fragment> mTmpAddedFragments;
  
  ArrayList<Boolean> mTmpIsPop;
  
  ArrayList<BackStackRecord> mTmpRecords;
  
  static {
    DECELERATE_CUBIC = (Interpolator)new DecelerateInterpolator(1.5F);
    ACCELERATE_QUINT = (Interpolator)new AccelerateInterpolator(2.5F);
    ACCELERATE_CUBIC = (Interpolator)new AccelerateInterpolator(1.5F);
  }
  
  private void addAddedFragments(ArraySet<Fragment> paramArraySet) {
    if (this.mCurState < 1)
      return; 
    int j = Math.min(this.mCurState, 3);
    int k = this.mAdded.size();
    for (int i = 0; i < k; i++) {
      Fragment fragment = this.mAdded.get(i);
      if (fragment.mState < j) {
        moveToState(fragment, j, fragment.getNextAnim(), fragment.getNextTransition(), false);
        if (fragment.mView != null && !fragment.mHidden && fragment.mIsNewlyAdded)
          paramArraySet.add(fragment); 
      } 
    } 
  }
  
  private void animateRemoveFragment(@NonNull final Fragment fragment, @NonNull AnimationOrAnimator paramAnimationOrAnimator, int paramInt) {
    final View viewToAnimate = fragment.mView;
    final ViewGroup container = fragment.mContainer;
    viewGroup.startViewTransition(view);
    fragment.setStateAfterAnimating(paramInt);
    if (paramAnimationOrAnimator.animation != null) {
      EndViewTransitionAnimator endViewTransitionAnimator = new EndViewTransitionAnimator(paramAnimationOrAnimator.animation, viewGroup, view);
      fragment.setAnimatingAway(fragment.mView);
      endViewTransitionAnimator.setAnimationListener(new AnimationListenerWrapper(getAnimationListener((Animation)endViewTransitionAnimator)) {
            public void onAnimationEnd(Animation param1Animation) {
              super.onAnimationEnd(param1Animation);
              container.post(new Runnable() {
                    public void run() {
                      if (fragment.getAnimatingAway() != null) {
                        fragment.setAnimatingAway(null);
                        FragmentManagerImpl.this.moveToState(fragment, fragment.getStateAfterAnimating(), 0, 0, false);
                      } 
                    }
                  });
            }
          });
      setHWLayerAnimListenerIfAlpha(view, paramAnimationOrAnimator);
      fragment.mView.startAnimation((Animation)endViewTransitionAnimator);
      return;
    } 
    Animator animator = paramAnimationOrAnimator.animator;
    fragment.setAnimator(paramAnimationOrAnimator.animator);
    animator.addListener((Animator.AnimatorListener)new AnimatorListenerAdapter() {
          public void onAnimationEnd(Animator param1Animator) {
            container.endViewTransition(viewToAnimate);
            param1Animator = fragment.getAnimator();
            fragment.setAnimator(null);
            if (param1Animator != null && container.indexOfChild(viewToAnimate) < 0)
              FragmentManagerImpl.this.moveToState(fragment, fragment.getStateAfterAnimating(), 0, 0, false); 
          }
        });
    animator.setTarget(fragment.mView);
    setHWLayerAnimListenerIfAlpha(fragment.mView, paramAnimationOrAnimator);
    animator.start();
  }
  
  private void burpActive() {
    if (this.mActive != null)
      for (int i = this.mActive.size() - 1; i >= 0; i--) {
        if (this.mActive.valueAt(i) == null)
          this.mActive.delete(this.mActive.keyAt(i)); 
      }  
  }
  
  private void checkStateLoss() {
    if (isStateSaved())
      throw new IllegalStateException("Can not perform this action after onSaveInstanceState"); 
    if (this.mNoTransactionsBecause != null) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Can not perform this action inside of ");
      stringBuilder.append(this.mNoTransactionsBecause);
      throw new IllegalStateException(stringBuilder.toString());
    } 
  }
  
  private void cleanupExec() {
    this.mExecutingActions = false;
    this.mTmpIsPop.clear();
    this.mTmpRecords.clear();
  }
  
  private void dispatchStateChange(int paramInt) {
    try {
      this.mExecutingActions = true;
      moveToState(paramInt, false);
      this.mExecutingActions = false;
      return;
    } finally {
      this.mExecutingActions = false;
    } 
  }
  
  private void endAnimatingAwayFragments() {
    int i;
    SparseArray<Fragment> sparseArray = this.mActive;
    int j = 0;
    if (sparseArray == null) {
      i = 0;
    } else {
      i = this.mActive.size();
    } 
    while (j < i) {
      Fragment fragment = (Fragment)this.mActive.valueAt(j);
      if (fragment != null)
        if (fragment.getAnimatingAway() != null) {
          int k = fragment.getStateAfterAnimating();
          View view = fragment.getAnimatingAway();
          Animation animation = view.getAnimation();
          if (animation != null) {
            animation.cancel();
            view.clearAnimation();
          } 
          fragment.setAnimatingAway(null);
          moveToState(fragment, k, 0, 0, false);
        } else if (fragment.getAnimator() != null) {
          fragment.getAnimator().end();
        }  
      j++;
    } 
  }
  
  private void ensureExecReady(boolean paramBoolean) {
    if (this.mExecutingActions)
      throw new IllegalStateException("FragmentManager is already executing transactions"); 
    if (this.mHost == null)
      throw new IllegalStateException("Fragment host has been destroyed"); 
    if (Looper.myLooper() != this.mHost.getHandler().getLooper())
      throw new IllegalStateException("Must be called from main thread of fragment host"); 
    if (!paramBoolean)
      checkStateLoss(); 
    if (this.mTmpRecords == null) {
      this.mTmpRecords = new ArrayList<BackStackRecord>();
      this.mTmpIsPop = new ArrayList<Boolean>();
    } 
    this.mExecutingActions = true;
    try {
      executePostponedTransaction(null, null);
      return;
    } finally {
      this.mExecutingActions = false;
    } 
  }
  
  private static void executeOps(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1, int paramInt1, int paramInt2) {
    while (paramInt1 < paramInt2) {
      BackStackRecord backStackRecord = paramArrayList.get(paramInt1);
      boolean bool1 = ((Boolean)paramArrayList1.get(paramInt1)).booleanValue();
      boolean bool = true;
      if (bool1) {
        backStackRecord.bumpBackStackNesting(-1);
        if (paramInt1 != paramInt2 - 1)
          bool = false; 
        backStackRecord.executePopOps(bool);
      } else {
        backStackRecord.bumpBackStackNesting(1);
        backStackRecord.executeOps();
      } 
      paramInt1++;
    } 
  }
  
  private void executeOpsTogether(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1, int paramInt1, int paramInt2) {
    int i = paramInt1;
    boolean bool1 = ((BackStackRecord)paramArrayList.get(i)).mReorderingAllowed;
    if (this.mTmpAddedFragments == null) {
      this.mTmpAddedFragments = new ArrayList<Fragment>();
    } else {
      this.mTmpAddedFragments.clear();
    } 
    this.mTmpAddedFragments.addAll(this.mAdded);
    Fragment fragment = getPrimaryNavigationFragment();
    int j = i;
    boolean bool = false;
    while (j < paramInt2) {
      BackStackRecord backStackRecord = paramArrayList.get(j);
      if (!((Boolean)paramArrayList1.get(j)).booleanValue()) {
        fragment = backStackRecord.expandOps(this.mTmpAddedFragments, fragment);
      } else {
        fragment = backStackRecord.trackAddedFragmentsInPop(this.mTmpAddedFragments, fragment);
      } 
      if (bool || backStackRecord.mAddToBackStack) {
        bool = true;
      } else {
        bool = false;
      } 
      j++;
    } 
    this.mTmpAddedFragments.clear();
    if (!bool1)
      FragmentTransition.startTransitions(this, paramArrayList, paramArrayList1, i, paramInt2, false); 
    executeOps(paramArrayList, paramArrayList1, paramInt1, paramInt2);
    if (bool1) {
      ArraySet<Fragment> arraySet = new ArraySet();
      addAddedFragments(arraySet);
      paramInt1 = postponePostponableTransactions(paramArrayList, paramArrayList1, i, paramInt2, arraySet);
      makeRemovedFragmentsInvisible(arraySet);
    } else {
      paramInt1 = paramInt2;
    } 
    j = i;
    if (paramInt1 != i) {
      j = i;
      if (bool1) {
        FragmentTransition.startTransitions(this, paramArrayList, paramArrayList1, i, paramInt1, true);
        moveToState(this.mCurState, true);
        j = i;
      } 
    } 
    while (j < paramInt2) {
      BackStackRecord backStackRecord = paramArrayList.get(j);
      if (((Boolean)paramArrayList1.get(j)).booleanValue() && backStackRecord.mIndex >= 0) {
        freeBackStackIndex(backStackRecord.mIndex);
        backStackRecord.mIndex = -1;
      } 
      backStackRecord.runOnCommitRunnables();
      j++;
    } 
    if (bool)
      reportBackStackChanged(); 
  }
  
  private void executePostponedTransaction(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mPostponedTransactions : Ljava/util/ArrayList;
    //   4: ifnonnull -> 12
    //   7: iconst_0
    //   8: istore_3
    //   9: goto -> 20
    //   12: aload_0
    //   13: getfield mPostponedTransactions : Ljava/util/ArrayList;
    //   16: invokevirtual size : ()I
    //   19: istore_3
    //   20: iconst_0
    //   21: istore #5
    //   23: iload_3
    //   24: istore #4
    //   26: iload #5
    //   28: istore_3
    //   29: iload_3
    //   30: iload #4
    //   32: if_icmpge -> 236
    //   35: aload_0
    //   36: getfield mPostponedTransactions : Ljava/util/ArrayList;
    //   39: iload_3
    //   40: invokevirtual get : (I)Ljava/lang/Object;
    //   43: checkcast android/support/v4/app/FragmentManagerImpl$StartEnterTransitionListener
    //   46: astore #7
    //   48: aload_1
    //   49: ifnull -> 107
    //   52: aload #7
    //   54: getfield mIsBack : Z
    //   57: ifne -> 107
    //   60: aload_1
    //   61: aload #7
    //   63: getfield mRecord : Landroid/support/v4/app/BackStackRecord;
    //   66: invokevirtual indexOf : (Ljava/lang/Object;)I
    //   69: istore #5
    //   71: iload #5
    //   73: iconst_m1
    //   74: if_icmpeq -> 107
    //   77: aload_2
    //   78: iload #5
    //   80: invokevirtual get : (I)Ljava/lang/Object;
    //   83: checkcast java/lang/Boolean
    //   86: invokevirtual booleanValue : ()Z
    //   89: ifeq -> 107
    //   92: aload #7
    //   94: invokevirtual cancelTransaction : ()V
    //   97: iload_3
    //   98: istore #6
    //   100: iload #4
    //   102: istore #5
    //   104: goto -> 224
    //   107: aload #7
    //   109: invokevirtual isReady : ()Z
    //   112: ifne -> 150
    //   115: iload_3
    //   116: istore #6
    //   118: iload #4
    //   120: istore #5
    //   122: aload_1
    //   123: ifnull -> 224
    //   126: iload_3
    //   127: istore #6
    //   129: iload #4
    //   131: istore #5
    //   133: aload #7
    //   135: getfield mRecord : Landroid/support/v4/app/BackStackRecord;
    //   138: aload_1
    //   139: iconst_0
    //   140: aload_1
    //   141: invokevirtual size : ()I
    //   144: invokevirtual interactsWith : (Ljava/util/ArrayList;II)Z
    //   147: ifeq -> 224
    //   150: aload_0
    //   151: getfield mPostponedTransactions : Ljava/util/ArrayList;
    //   154: iload_3
    //   155: invokevirtual remove : (I)Ljava/lang/Object;
    //   158: pop
    //   159: iload_3
    //   160: iconst_1
    //   161: isub
    //   162: istore #6
    //   164: iload #4
    //   166: iconst_1
    //   167: isub
    //   168: istore #5
    //   170: aload_1
    //   171: ifnull -> 219
    //   174: aload #7
    //   176: getfield mIsBack : Z
    //   179: ifne -> 219
    //   182: aload_1
    //   183: aload #7
    //   185: getfield mRecord : Landroid/support/v4/app/BackStackRecord;
    //   188: invokevirtual indexOf : (Ljava/lang/Object;)I
    //   191: istore_3
    //   192: iload_3
    //   193: iconst_m1
    //   194: if_icmpeq -> 219
    //   197: aload_2
    //   198: iload_3
    //   199: invokevirtual get : (I)Ljava/lang/Object;
    //   202: checkcast java/lang/Boolean
    //   205: invokevirtual booleanValue : ()Z
    //   208: ifeq -> 219
    //   211: aload #7
    //   213: invokevirtual cancelTransaction : ()V
    //   216: goto -> 224
    //   219: aload #7
    //   221: invokevirtual completeTransaction : ()V
    //   224: iload #6
    //   226: iconst_1
    //   227: iadd
    //   228: istore_3
    //   229: iload #5
    //   231: istore #4
    //   233: goto -> 29
    //   236: return
  }
  
  private Fragment findFragmentUnder(Fragment paramFragment) {
    ViewGroup viewGroup = paramFragment.mContainer;
    View view = paramFragment.mView;
    if (viewGroup != null) {
      if (view == null)
        return null; 
      for (int i = this.mAdded.indexOf(paramFragment) - 1; i >= 0; i--) {
        paramFragment = this.mAdded.get(i);
        if (paramFragment.mContainer == viewGroup && paramFragment.mView != null)
          return paramFragment; 
      } 
      return null;
    } 
    return null;
  }
  
  private void forcePostponedTransactions() {
    if (this.mPostponedTransactions != null)
      while (!this.mPostponedTransactions.isEmpty())
        ((StartEnterTransitionListener)this.mPostponedTransactions.remove(0)).completeTransaction();  
  }
  
  private boolean generateOpsForPendingActions(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mPendingActions : Ljava/util/ArrayList;
    //   6: astore #6
    //   8: iconst_0
    //   9: istore_3
    //   10: aload #6
    //   12: ifnull -> 102
    //   15: aload_0
    //   16: getfield mPendingActions : Ljava/util/ArrayList;
    //   19: invokevirtual size : ()I
    //   22: ifne -> 28
    //   25: goto -> 102
    //   28: aload_0
    //   29: getfield mPendingActions : Ljava/util/ArrayList;
    //   32: invokevirtual size : ()I
    //   35: istore #4
    //   37: iconst_0
    //   38: istore #5
    //   40: iload_3
    //   41: iload #4
    //   43: if_icmpge -> 76
    //   46: iload #5
    //   48: aload_0
    //   49: getfield mPendingActions : Ljava/util/ArrayList;
    //   52: iload_3
    //   53: invokevirtual get : (I)Ljava/lang/Object;
    //   56: checkcast android/support/v4/app/FragmentManagerImpl$OpGenerator
    //   59: aload_1
    //   60: aload_2
    //   61: invokeinterface generateOps : (Ljava/util/ArrayList;Ljava/util/ArrayList;)Z
    //   66: ior
    //   67: istore #5
    //   69: iload_3
    //   70: iconst_1
    //   71: iadd
    //   72: istore_3
    //   73: goto -> 40
    //   76: aload_0
    //   77: getfield mPendingActions : Ljava/util/ArrayList;
    //   80: invokevirtual clear : ()V
    //   83: aload_0
    //   84: getfield mHost : Landroid/support/v4/app/FragmentHostCallback;
    //   87: invokevirtual getHandler : ()Landroid/os/Handler;
    //   90: aload_0
    //   91: getfield mExecCommit : Ljava/lang/Runnable;
    //   94: invokevirtual removeCallbacks : (Ljava/lang/Runnable;)V
    //   97: aload_0
    //   98: monitorexit
    //   99: iload #5
    //   101: ireturn
    //   102: aload_0
    //   103: monitorexit
    //   104: iconst_0
    //   105: ireturn
    //   106: astore_1
    //   107: aload_0
    //   108: monitorexit
    //   109: aload_1
    //   110: athrow
    // Exception table:
    //   from	to	target	type
    //   2	8	106	finally
    //   15	25	106	finally
    //   28	37	106	finally
    //   46	69	106	finally
    //   76	99	106	finally
    //   102	104	106	finally
    //   107	109	106	finally
  }
  
  private static Animation.AnimationListener getAnimationListener(Animation paramAnimation) {
    try {
      if (sAnimationListenerField == null) {
        sAnimationListenerField = Animation.class.getDeclaredField("mListener");
        sAnimationListenerField.setAccessible(true);
      } 
      return (Animation.AnimationListener)sAnimationListenerField.get(paramAnimation);
    } catch (NoSuchFieldException noSuchFieldException) {
      Log.e("FragmentManager", "No field with the name mListener is found in Animation class", noSuchFieldException);
    } catch (IllegalAccessException illegalAccessException) {
      Log.e("FragmentManager", "Cannot access Animation's mListener field", illegalAccessException);
    } 
    return null;
  }
  
  static AnimationOrAnimator makeFadeAnimation(Context paramContext, float paramFloat1, float paramFloat2) {
    AlphaAnimation alphaAnimation = new AlphaAnimation(paramFloat1, paramFloat2);
    alphaAnimation.setInterpolator(DECELERATE_CUBIC);
    alphaAnimation.setDuration(220L);
    return new AnimationOrAnimator((Animation)alphaAnimation);
  }
  
  static AnimationOrAnimator makeOpenCloseAnimation(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    AnimationSet animationSet = new AnimationSet(false);
    ScaleAnimation scaleAnimation = new ScaleAnimation(paramFloat1, paramFloat2, paramFloat1, paramFloat2, 1, 0.5F, 1, 0.5F);
    scaleAnimation.setInterpolator(DECELERATE_QUINT);
    scaleAnimation.setDuration(220L);
    animationSet.addAnimation((Animation)scaleAnimation);
    AlphaAnimation alphaAnimation = new AlphaAnimation(paramFloat3, paramFloat4);
    alphaAnimation.setInterpolator(DECELERATE_CUBIC);
    alphaAnimation.setDuration(220L);
    animationSet.addAnimation((Animation)alphaAnimation);
    return new AnimationOrAnimator((Animation)animationSet);
  }
  
  private void makeRemovedFragmentsInvisible(ArraySet<Fragment> paramArraySet) {
    int j = paramArraySet.size();
    for (int i = 0; i < j; i++) {
      Fragment fragment = (Fragment)paramArraySet.valueAt(i);
      if (!fragment.mAdded) {
        View view = fragment.getView();
        fragment.mPostponedAlpha = view.getAlpha();
        view.setAlpha(0.0F);
      } 
    } 
  }
  
  static boolean modifiesAlpha(Animator paramAnimator) {
    PropertyValuesHolder[] arrayOfPropertyValuesHolder;
    if (paramAnimator == null)
      return false; 
    if (paramAnimator instanceof ValueAnimator) {
      arrayOfPropertyValuesHolder = ((ValueAnimator)paramAnimator).getValues();
      for (int i = 0; i < arrayOfPropertyValuesHolder.length; i++) {
        if ("alpha".equals(arrayOfPropertyValuesHolder[i].getPropertyName()))
          return true; 
      } 
    } else if (arrayOfPropertyValuesHolder instanceof AnimatorSet) {
      ArrayList<Animator> arrayList = ((AnimatorSet)arrayOfPropertyValuesHolder).getChildAnimations();
      for (int i = 0; i < arrayList.size(); i++) {
        if (modifiesAlpha(arrayList.get(i)))
          return true; 
      } 
    } 
    return false;
  }
  
  static boolean modifiesAlpha(AnimationOrAnimator paramAnimationOrAnimator) {
    List list;
    if (paramAnimationOrAnimator.animation instanceof AlphaAnimation)
      return true; 
    if (paramAnimationOrAnimator.animation instanceof AnimationSet) {
      list = ((AnimationSet)paramAnimationOrAnimator.animation).getAnimations();
      for (int i = 0; i < list.size(); i++) {
        if (list.get(i) instanceof AlphaAnimation)
          return true; 
      } 
      return false;
    } 
    return modifiesAlpha(((AnimationOrAnimator)list).animator);
  }
  
  private boolean popBackStackImmediate(String paramString, int paramInt1, int paramInt2) {
    execPendingActions();
    ensureExecReady(true);
    if (this.mPrimaryNav != null && paramInt1 < 0 && paramString == null) {
      FragmentManager fragmentManager = this.mPrimaryNav.peekChildFragmentManager();
      if (fragmentManager != null && fragmentManager.popBackStackImmediate())
        return true; 
    } 
    boolean bool = popBackStackState(this.mTmpRecords, this.mTmpIsPop, paramString, paramInt1, paramInt2);
    if (bool) {
      this.mExecutingActions = true;
      try {
        removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
      } finally {
        cleanupExec();
      } 
    } 
    doPendingDeferredStart();
    burpActive();
    return bool;
  }
  
  private int postponePostponableTransactions(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1, int paramInt1, int paramInt2, ArraySet<Fragment> paramArraySet) {
    int i = paramInt2 - 1;
    int j;
    for (j = paramInt2; i >= paramInt1; j = k) {
      boolean bool;
      BackStackRecord backStackRecord = paramArrayList.get(i);
      boolean bool1 = ((Boolean)paramArrayList1.get(i)).booleanValue();
      if (backStackRecord.isPostponed() && !backStackRecord.interactsWith(paramArrayList, i + 1, paramInt2)) {
        bool = true;
      } else {
        bool = false;
      } 
      int k = j;
      if (bool) {
        if (this.mPostponedTransactions == null)
          this.mPostponedTransactions = new ArrayList<StartEnterTransitionListener>(); 
        StartEnterTransitionListener startEnterTransitionListener = new StartEnterTransitionListener(backStackRecord, bool1);
        this.mPostponedTransactions.add(startEnterTransitionListener);
        backStackRecord.setOnStartPostponedListener(startEnterTransitionListener);
        if (bool1) {
          backStackRecord.executeOps();
        } else {
          backStackRecord.executePopOps(false);
        } 
        k = j - 1;
        if (i != k) {
          paramArrayList.remove(i);
          paramArrayList.add(k, backStackRecord);
        } 
        addAddedFragments(paramArraySet);
      } 
      i--;
    } 
    return j;
  }
  
  private void removeRedundantOperationsAndExecute(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1) {
    if (paramArrayList != null) {
      if (paramArrayList.isEmpty())
        return; 
      if (paramArrayList1 == null || paramArrayList.size() != paramArrayList1.size())
        throw new IllegalStateException("Internal error with the back stack records"); 
      executePostponedTransaction(paramArrayList, paramArrayList1);
      int k = paramArrayList.size();
      int i = 0;
      int j;
      for (j = 0; i < k; j = m) {
        int n = i;
        int m = j;
        if (!((BackStackRecord)paramArrayList.get(i)).mReorderingAllowed) {
          if (j != i)
            executeOpsTogether(paramArrayList, paramArrayList1, j, i); 
          j = i + 1;
          m = j;
          if (((Boolean)paramArrayList1.get(i)).booleanValue())
            while (true) {
              m = j;
              if (j < k) {
                m = j;
                if (((Boolean)paramArrayList1.get(j)).booleanValue()) {
                  m = j;
                  if (!((BackStackRecord)paramArrayList.get(j)).mReorderingAllowed) {
                    j++;
                    continue;
                  } 
                } 
              } 
              break;
            }  
          executeOpsTogether(paramArrayList, paramArrayList1, i, m);
          n = m - 1;
        } 
        i = n + 1;
      } 
      if (j != k)
        executeOpsTogether(paramArrayList, paramArrayList1, j, k); 
      return;
    } 
  }
  
  public static int reverseTransit(int paramInt) {
    char c = ' ';
    if (paramInt != 4097) {
      if (paramInt != 4099)
        return (paramInt != 8194) ? 0 : 4097; 
      c = 'ဃ';
    } 
    return c;
  }
  
  private static void setHWLayerAnimListenerIfAlpha(View paramView, AnimationOrAnimator paramAnimationOrAnimator) {
    if (paramView != null) {
      if (paramAnimationOrAnimator == null)
        return; 
      if (shouldRunOnHWLayer(paramView, paramAnimationOrAnimator)) {
        if (paramAnimationOrAnimator.animator != null) {
          paramAnimationOrAnimator.animator.addListener((Animator.AnimatorListener)new AnimatorOnHWLayerIfNeededListener(paramView));
          return;
        } 
        Animation.AnimationListener animationListener = getAnimationListener(paramAnimationOrAnimator.animation);
        paramView.setLayerType(2, null);
        paramAnimationOrAnimator.animation.setAnimationListener(new AnimateOnHWLayerIfNeededListener(paramView, animationListener));
      } 
      return;
    } 
  }
  
  private static void setRetaining(FragmentManagerNonConfig paramFragmentManagerNonConfig) {
    if (paramFragmentManagerNonConfig == null)
      return; 
    List<Fragment> list1 = paramFragmentManagerNonConfig.getFragments();
    if (list1 != null) {
      Iterator<Fragment> iterator = list1.iterator();
      while (iterator.hasNext())
        ((Fragment)iterator.next()).mRetaining = true; 
    } 
    List<FragmentManagerNonConfig> list = paramFragmentManagerNonConfig.getChildNonConfigs();
    if (list != null) {
      Iterator<FragmentManagerNonConfig> iterator = list.iterator();
      while (iterator.hasNext())
        setRetaining(iterator.next()); 
    } 
  }
  
  static boolean shouldRunOnHWLayer(View paramView, AnimationOrAnimator paramAnimationOrAnimator) {
    boolean bool = false;
    if (paramView != null) {
      if (paramAnimationOrAnimator == null)
        return false; 
      boolean bool1 = bool;
      if (Build.VERSION.SDK_INT >= 19) {
        bool1 = bool;
        if (paramView.getLayerType() == 0) {
          bool1 = bool;
          if (ViewCompat.hasOverlappingRendering(paramView)) {
            bool1 = bool;
            if (modifiesAlpha(paramAnimationOrAnimator))
              bool1 = true; 
          } 
        } 
      } 
      return bool1;
    } 
    return false;
  }
  
  private void throwException(RuntimeException paramRuntimeException) {
    Log.e("FragmentManager", paramRuntimeException.getMessage());
    Log.e("FragmentManager", "Activity state:");
    PrintWriter printWriter = new PrintWriter((Writer)new LogWriter("FragmentManager"));
    if (this.mHost != null) {
      try {
        this.mHost.onDump("  ", null, printWriter, new String[0]);
      } catch (Exception exception) {
        Log.e("FragmentManager", "Failed dumping state", exception);
      } 
    } else {
      try {
        dump("  ", null, (PrintWriter)exception, new String[0]);
      } catch (Exception exception1) {
        Log.e("FragmentManager", "Failed dumping state", exception1);
      } 
    } 
    throw paramRuntimeException;
  }
  
  public static int transitToStyleIndex(int paramInt, boolean paramBoolean) {
    return (paramInt != 4097) ? ((paramInt != 4099) ? ((paramInt != 8194) ? -1 : (paramBoolean ? 3 : 4)) : (paramBoolean ? 5 : 6)) : (paramBoolean ? 1 : 2);
  }
  
  void addBackStackState(BackStackRecord paramBackStackRecord) {
    if (this.mBackStack == null)
      this.mBackStack = new ArrayList<BackStackRecord>(); 
    this.mBackStack.add(paramBackStackRecord);
  }
  
  public void addFragment(Fragment paramFragment, boolean paramBoolean) {
    if (DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("add: ");
      stringBuilder.append(paramFragment);
      Log.v("FragmentManager", stringBuilder.toString());
    } 
    makeActive(paramFragment);
    if (!paramFragment.mDetached) {
      if (this.mAdded.contains(paramFragment)) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Fragment already added: ");
        stringBuilder.append(paramFragment);
        throw new IllegalStateException(stringBuilder.toString());
      } 
      synchronized (this.mAdded) {
        this.mAdded.add(paramFragment);
        paramFragment.mAdded = true;
        paramFragment.mRemoving = false;
        if (paramFragment.mView == null)
          paramFragment.mHiddenChanged = false; 
        if (paramFragment.mHasMenu && paramFragment.mMenuVisible)
          this.mNeedMenuInvalidate = true; 
        if (paramBoolean) {
          moveToState(paramFragment);
          return;
        } 
      } 
    } 
  }
  
  public void addOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener paramOnBackStackChangedListener) {
    if (this.mBackStackChangeListeners == null)
      this.mBackStackChangeListeners = new ArrayList<FragmentManager.OnBackStackChangedListener>(); 
    this.mBackStackChangeListeners.add(paramOnBackStackChangedListener);
  }
  
  public int allocBackStackIndex(BackStackRecord paramBackStackRecord) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   6: ifnull -> 111
    //   9: aload_0
    //   10: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   13: invokevirtual size : ()I
    //   16: ifgt -> 22
    //   19: goto -> 111
    //   22: aload_0
    //   23: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   26: aload_0
    //   27: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   30: invokevirtual size : ()I
    //   33: iconst_1
    //   34: isub
    //   35: invokevirtual remove : (I)Ljava/lang/Object;
    //   38: checkcast java/lang/Integer
    //   41: invokevirtual intValue : ()I
    //   44: istore_2
    //   45: getstatic android/support/v4/app/FragmentManagerImpl.DEBUG : Z
    //   48: ifeq -> 97
    //   51: new java/lang/StringBuilder
    //   54: dup
    //   55: invokespecial <init> : ()V
    //   58: astore_3
    //   59: aload_3
    //   60: ldc_w 'Adding back stack index '
    //   63: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   66: pop
    //   67: aload_3
    //   68: iload_2
    //   69: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   72: pop
    //   73: aload_3
    //   74: ldc_w ' with '
    //   77: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   80: pop
    //   81: aload_3
    //   82: aload_1
    //   83: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   86: pop
    //   87: ldc 'FragmentManager'
    //   89: aload_3
    //   90: invokevirtual toString : ()Ljava/lang/String;
    //   93: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   96: pop
    //   97: aload_0
    //   98: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   101: iload_2
    //   102: aload_1
    //   103: invokevirtual set : (ILjava/lang/Object;)Ljava/lang/Object;
    //   106: pop
    //   107: aload_0
    //   108: monitorexit
    //   109: iload_2
    //   110: ireturn
    //   111: aload_0
    //   112: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   115: ifnonnull -> 129
    //   118: aload_0
    //   119: new java/util/ArrayList
    //   122: dup
    //   123: invokespecial <init> : ()V
    //   126: putfield mBackStackIndices : Ljava/util/ArrayList;
    //   129: aload_0
    //   130: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   133: invokevirtual size : ()I
    //   136: istore_2
    //   137: getstatic android/support/v4/app/FragmentManagerImpl.DEBUG : Z
    //   140: ifeq -> 189
    //   143: new java/lang/StringBuilder
    //   146: dup
    //   147: invokespecial <init> : ()V
    //   150: astore_3
    //   151: aload_3
    //   152: ldc_w 'Setting back stack index '
    //   155: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   158: pop
    //   159: aload_3
    //   160: iload_2
    //   161: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   164: pop
    //   165: aload_3
    //   166: ldc_w ' to '
    //   169: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   172: pop
    //   173: aload_3
    //   174: aload_1
    //   175: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   178: pop
    //   179: ldc 'FragmentManager'
    //   181: aload_3
    //   182: invokevirtual toString : ()Ljava/lang/String;
    //   185: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   188: pop
    //   189: aload_0
    //   190: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   193: aload_1
    //   194: invokevirtual add : (Ljava/lang/Object;)Z
    //   197: pop
    //   198: aload_0
    //   199: monitorexit
    //   200: iload_2
    //   201: ireturn
    //   202: astore_1
    //   203: aload_0
    //   204: monitorexit
    //   205: aload_1
    //   206: athrow
    // Exception table:
    //   from	to	target	type
    //   2	19	202	finally
    //   22	97	202	finally
    //   97	109	202	finally
    //   111	129	202	finally
    //   129	189	202	finally
    //   189	200	202	finally
    //   203	205	202	finally
  }
  
  public void attachController(FragmentHostCallback paramFragmentHostCallback, FragmentContainer paramFragmentContainer, Fragment paramFragment) {
    if (this.mHost != null)
      throw new IllegalStateException("Already attached"); 
    this.mHost = paramFragmentHostCallback;
    this.mContainer = paramFragmentContainer;
    this.mParent = paramFragment;
  }
  
  public void attachFragment(Fragment paramFragment) {
    if (DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("attach: ");
      stringBuilder.append(paramFragment);
      Log.v("FragmentManager", stringBuilder.toString());
    } 
    if (paramFragment.mDetached) {
      paramFragment.mDetached = false;
      if (!paramFragment.mAdded) {
        if (this.mAdded.contains(paramFragment)) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Fragment already added: ");
          stringBuilder.append(paramFragment);
          throw new IllegalStateException(stringBuilder.toString());
        } 
        if (DEBUG) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("add from attach: ");
          stringBuilder.append(paramFragment);
          Log.v("FragmentManager", stringBuilder.toString());
        } 
        synchronized (this.mAdded) {
          this.mAdded.add(paramFragment);
          paramFragment.mAdded = true;
          if (paramFragment.mHasMenu && paramFragment.mMenuVisible) {
            this.mNeedMenuInvalidate = true;
            return;
          } 
        } 
      } 
    } 
  }
  
  public FragmentTransaction beginTransaction() {
    return new BackStackRecord(this);
  }
  
  void completeExecute(BackStackRecord paramBackStackRecord, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3) {
    if (paramBoolean1) {
      paramBackStackRecord.executePopOps(paramBoolean3);
    } else {
      paramBackStackRecord.executeOps();
    } 
    ArrayList<BackStackRecord> arrayList = new ArrayList(1);
    ArrayList<Boolean> arrayList1 = new ArrayList(1);
    arrayList.add(paramBackStackRecord);
    arrayList1.add(Boolean.valueOf(paramBoolean1));
    if (paramBoolean2)
      FragmentTransition.startTransitions(this, arrayList, arrayList1, 0, 1, true); 
    if (paramBoolean3)
      moveToState(this.mCurState, true); 
    if (this.mActive != null) {
      int j = this.mActive.size();
      int i;
      for (i = 0; i < j; i++) {
        Fragment fragment = (Fragment)this.mActive.valueAt(i);
        if (fragment != null && fragment.mView != null && fragment.mIsNewlyAdded && paramBackStackRecord.interactsWith(fragment.mContainerId)) {
          if (fragment.mPostponedAlpha > 0.0F)
            fragment.mView.setAlpha(fragment.mPostponedAlpha); 
          if (paramBoolean3) {
            fragment.mPostponedAlpha = 0.0F;
          } else {
            fragment.mPostponedAlpha = -1.0F;
            fragment.mIsNewlyAdded = false;
          } 
        } 
      } 
    } 
  }
  
  void completeShowHideFragment(final Fragment fragment) {
    if (fragment.mView != null) {
      AnimationOrAnimator animationOrAnimator = loadAnimation(fragment, fragment.getNextTransition(), fragment.mHidden ^ true, fragment.getNextTransitionStyle());
      if (animationOrAnimator != null && animationOrAnimator.animator != null) {
        animationOrAnimator.animator.setTarget(fragment.mView);
        if (fragment.mHidden) {
          if (fragment.isHideReplaced()) {
            fragment.setHideReplaced(false);
          } else {
            final ViewGroup container = fragment.mContainer;
            final View animatingView = fragment.mView;
            viewGroup.startViewTransition(view);
            animationOrAnimator.animator.addListener((Animator.AnimatorListener)new AnimatorListenerAdapter() {
                  public void onAnimationEnd(Animator param1Animator) {
                    container.endViewTransition(animatingView);
                    param1Animator.removeListener((Animator.AnimatorListener)this);
                    if (fragment.mView != null)
                      fragment.mView.setVisibility(8); 
                  }
                });
          } 
        } else {
          fragment.mView.setVisibility(0);
        } 
        setHWLayerAnimListenerIfAlpha(fragment.mView, animationOrAnimator);
        animationOrAnimator.animator.start();
      } else {
        boolean bool;
        if (animationOrAnimator != null) {
          setHWLayerAnimListenerIfAlpha(fragment.mView, animationOrAnimator);
          fragment.mView.startAnimation(animationOrAnimator.animation);
          animationOrAnimator.animation.start();
        } 
        if (fragment.mHidden && !fragment.isHideReplaced()) {
          bool = true;
        } else {
          bool = false;
        } 
        fragment.mView.setVisibility(bool);
        if (fragment.isHideReplaced())
          fragment.setHideReplaced(false); 
      } 
    } 
    if (fragment.mAdded && fragment.mHasMenu && fragment.mMenuVisible)
      this.mNeedMenuInvalidate = true; 
    fragment.mHiddenChanged = false;
    fragment.onHiddenChanged(fragment.mHidden);
  }
  
  public void detachFragment(Fragment paramFragment) {
    if (DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("detach: ");
      stringBuilder.append(paramFragment);
      Log.v("FragmentManager", stringBuilder.toString());
    } 
    if (!paramFragment.mDetached) {
      paramFragment.mDetached = true;
      if (paramFragment.mAdded) {
        if (DEBUG) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("remove from detach: ");
          stringBuilder.append(paramFragment);
          Log.v("FragmentManager", stringBuilder.toString());
        } 
        synchronized (this.mAdded) {
          this.mAdded.remove(paramFragment);
          if (paramFragment.mHasMenu && paramFragment.mMenuVisible)
            this.mNeedMenuInvalidate = true; 
          paramFragment.mAdded = false;
          return;
        } 
      } 
    } 
  }
  
  public void dispatchActivityCreated() {
    this.mStateSaved = false;
    this.mStopped = false;
    dispatchStateChange(2);
  }
  
  public void dispatchConfigurationChanged(Configuration paramConfiguration) {
    for (int i = 0; i < this.mAdded.size(); i++) {
      Fragment fragment = this.mAdded.get(i);
      if (fragment != null)
        fragment.performConfigurationChanged(paramConfiguration); 
    } 
  }
  
  public boolean dispatchContextItemSelected(MenuItem paramMenuItem) {
    if (this.mCurState < 1)
      return false; 
    for (int i = 0; i < this.mAdded.size(); i++) {
      Fragment fragment = this.mAdded.get(i);
      if (fragment != null && fragment.performContextItemSelected(paramMenuItem))
        return true; 
    } 
    return false;
  }
  
  public void dispatchCreate() {
    this.mStateSaved = false;
    this.mStopped = false;
    dispatchStateChange(1);
  }
  
  public boolean dispatchCreateOptionsMenu(Menu paramMenu, MenuInflater paramMenuInflater) {
    int i = this.mCurState;
    boolean bool1 = false;
    if (i < 1)
      return false; 
    ArrayList<Fragment> arrayList = null;
    i = 0;
    boolean bool2;
    for (bool2 = false; i < this.mAdded.size(); bool2 = bool) {
      Fragment fragment = this.mAdded.get(i);
      ArrayList<Fragment> arrayList1 = arrayList;
      boolean bool = bool2;
      if (fragment != null) {
        arrayList1 = arrayList;
        bool = bool2;
        if (fragment.performCreateOptionsMenu(paramMenu, paramMenuInflater)) {
          arrayList1 = arrayList;
          if (arrayList == null)
            arrayList1 = new ArrayList(); 
          arrayList1.add(fragment);
          bool = true;
        } 
      } 
      i++;
      arrayList = arrayList1;
    } 
    if (this.mCreatedMenus != null)
      for (i = bool1; i < this.mCreatedMenus.size(); i++) {
        Fragment fragment = this.mCreatedMenus.get(i);
        if (arrayList == null || !arrayList.contains(fragment))
          fragment.onDestroyOptionsMenu(); 
      }  
    this.mCreatedMenus = arrayList;
    return bool2;
  }
  
  public void dispatchDestroy() {
    this.mDestroyed = true;
    execPendingActions();
    dispatchStateChange(0);
    this.mHost = null;
    this.mContainer = null;
    this.mParent = null;
  }
  
  public void dispatchDestroyView() {
    dispatchStateChange(1);
  }
  
  public void dispatchLowMemory() {
    for (int i = 0; i < this.mAdded.size(); i++) {
      Fragment fragment = this.mAdded.get(i);
      if (fragment != null)
        fragment.performLowMemory(); 
    } 
  }
  
  public void dispatchMultiWindowModeChanged(boolean paramBoolean) {
    for (int i = this.mAdded.size() - 1; i >= 0; i--) {
      Fragment fragment = this.mAdded.get(i);
      if (fragment != null)
        fragment.performMultiWindowModeChanged(paramBoolean); 
    } 
  }
  
  void dispatchOnFragmentActivityCreated(@NonNull Fragment paramFragment, @Nullable Bundle paramBundle, boolean paramBoolean) {
    if (this.mParent != null) {
      FragmentManager fragmentManager = this.mParent.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentActivityCreated(paramFragment, paramBundle, true); 
    } 
    for (FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder : this.mLifecycleCallbacks) {
      if (!paramBoolean || fragmentLifecycleCallbacksHolder.mRecursive)
        fragmentLifecycleCallbacksHolder.mCallback.onFragmentActivityCreated(this, paramFragment, paramBundle); 
    } 
  }
  
  void dispatchOnFragmentAttached(@NonNull Fragment paramFragment, @NonNull Context paramContext, boolean paramBoolean) {
    if (this.mParent != null) {
      FragmentManager fragmentManager = this.mParent.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentAttached(paramFragment, paramContext, true); 
    } 
    for (FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder : this.mLifecycleCallbacks) {
      if (!paramBoolean || fragmentLifecycleCallbacksHolder.mRecursive)
        fragmentLifecycleCallbacksHolder.mCallback.onFragmentAttached(this, paramFragment, paramContext); 
    } 
  }
  
  void dispatchOnFragmentCreated(@NonNull Fragment paramFragment, @Nullable Bundle paramBundle, boolean paramBoolean) {
    if (this.mParent != null) {
      FragmentManager fragmentManager = this.mParent.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentCreated(paramFragment, paramBundle, true); 
    } 
    for (FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder : this.mLifecycleCallbacks) {
      if (!paramBoolean || fragmentLifecycleCallbacksHolder.mRecursive)
        fragmentLifecycleCallbacksHolder.mCallback.onFragmentCreated(this, paramFragment, paramBundle); 
    } 
  }
  
  void dispatchOnFragmentDestroyed(@NonNull Fragment paramFragment, boolean paramBoolean) {
    if (this.mParent != null) {
      FragmentManager fragmentManager = this.mParent.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentDestroyed(paramFragment, true); 
    } 
    for (FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder : this.mLifecycleCallbacks) {
      if (!paramBoolean || fragmentLifecycleCallbacksHolder.mRecursive)
        fragmentLifecycleCallbacksHolder.mCallback.onFragmentDestroyed(this, paramFragment); 
    } 
  }
  
  void dispatchOnFragmentDetached(@NonNull Fragment paramFragment, boolean paramBoolean) {
    if (this.mParent != null) {
      FragmentManager fragmentManager = this.mParent.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentDetached(paramFragment, true); 
    } 
    for (FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder : this.mLifecycleCallbacks) {
      if (!paramBoolean || fragmentLifecycleCallbacksHolder.mRecursive)
        fragmentLifecycleCallbacksHolder.mCallback.onFragmentDetached(this, paramFragment); 
    } 
  }
  
  void dispatchOnFragmentPaused(@NonNull Fragment paramFragment, boolean paramBoolean) {
    if (this.mParent != null) {
      FragmentManager fragmentManager = this.mParent.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentPaused(paramFragment, true); 
    } 
    for (FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder : this.mLifecycleCallbacks) {
      if (!paramBoolean || fragmentLifecycleCallbacksHolder.mRecursive)
        fragmentLifecycleCallbacksHolder.mCallback.onFragmentPaused(this, paramFragment); 
    } 
  }
  
  void dispatchOnFragmentPreAttached(@NonNull Fragment paramFragment, @NonNull Context paramContext, boolean paramBoolean) {
    if (this.mParent != null) {
      FragmentManager fragmentManager = this.mParent.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentPreAttached(paramFragment, paramContext, true); 
    } 
    for (FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder : this.mLifecycleCallbacks) {
      if (!paramBoolean || fragmentLifecycleCallbacksHolder.mRecursive)
        fragmentLifecycleCallbacksHolder.mCallback.onFragmentPreAttached(this, paramFragment, paramContext); 
    } 
  }
  
  void dispatchOnFragmentPreCreated(@NonNull Fragment paramFragment, @Nullable Bundle paramBundle, boolean paramBoolean) {
    if (this.mParent != null) {
      FragmentManager fragmentManager = this.mParent.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentPreCreated(paramFragment, paramBundle, true); 
    } 
    for (FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder : this.mLifecycleCallbacks) {
      if (!paramBoolean || fragmentLifecycleCallbacksHolder.mRecursive)
        fragmentLifecycleCallbacksHolder.mCallback.onFragmentPreCreated(this, paramFragment, paramBundle); 
    } 
  }
  
  void dispatchOnFragmentResumed(@NonNull Fragment paramFragment, boolean paramBoolean) {
    if (this.mParent != null) {
      FragmentManager fragmentManager = this.mParent.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentResumed(paramFragment, true); 
    } 
    for (FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder : this.mLifecycleCallbacks) {
      if (!paramBoolean || fragmentLifecycleCallbacksHolder.mRecursive)
        fragmentLifecycleCallbacksHolder.mCallback.onFragmentResumed(this, paramFragment); 
    } 
  }
  
  void dispatchOnFragmentSaveInstanceState(@NonNull Fragment paramFragment, @NonNull Bundle paramBundle, boolean paramBoolean) {
    if (this.mParent != null) {
      FragmentManager fragmentManager = this.mParent.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentSaveInstanceState(paramFragment, paramBundle, true); 
    } 
    for (FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder : this.mLifecycleCallbacks) {
      if (!paramBoolean || fragmentLifecycleCallbacksHolder.mRecursive)
        fragmentLifecycleCallbacksHolder.mCallback.onFragmentSaveInstanceState(this, paramFragment, paramBundle); 
    } 
  }
  
  void dispatchOnFragmentStarted(@NonNull Fragment paramFragment, boolean paramBoolean) {
    if (this.mParent != null) {
      FragmentManager fragmentManager = this.mParent.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentStarted(paramFragment, true); 
    } 
    for (FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder : this.mLifecycleCallbacks) {
      if (!paramBoolean || fragmentLifecycleCallbacksHolder.mRecursive)
        fragmentLifecycleCallbacksHolder.mCallback.onFragmentStarted(this, paramFragment); 
    } 
  }
  
  void dispatchOnFragmentStopped(@NonNull Fragment paramFragment, boolean paramBoolean) {
    if (this.mParent != null) {
      FragmentManager fragmentManager = this.mParent.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentStopped(paramFragment, true); 
    } 
    for (FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder : this.mLifecycleCallbacks) {
      if (!paramBoolean || fragmentLifecycleCallbacksHolder.mRecursive)
        fragmentLifecycleCallbacksHolder.mCallback.onFragmentStopped(this, paramFragment); 
    } 
  }
  
  void dispatchOnFragmentViewCreated(@NonNull Fragment paramFragment, @NonNull View paramView, @Nullable Bundle paramBundle, boolean paramBoolean) {
    if (this.mParent != null) {
      FragmentManager fragmentManager = this.mParent.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentViewCreated(paramFragment, paramView, paramBundle, true); 
    } 
    for (FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder : this.mLifecycleCallbacks) {
      if (!paramBoolean || fragmentLifecycleCallbacksHolder.mRecursive)
        fragmentLifecycleCallbacksHolder.mCallback.onFragmentViewCreated(this, paramFragment, paramView, paramBundle); 
    } 
  }
  
  void dispatchOnFragmentViewDestroyed(@NonNull Fragment paramFragment, boolean paramBoolean) {
    if (this.mParent != null) {
      FragmentManager fragmentManager = this.mParent.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentViewDestroyed(paramFragment, true); 
    } 
    for (FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder : this.mLifecycleCallbacks) {
      if (!paramBoolean || fragmentLifecycleCallbacksHolder.mRecursive)
        fragmentLifecycleCallbacksHolder.mCallback.onFragmentViewDestroyed(this, paramFragment); 
    } 
  }
  
  public boolean dispatchOptionsItemSelected(MenuItem paramMenuItem) {
    if (this.mCurState < 1)
      return false; 
    for (int i = 0; i < this.mAdded.size(); i++) {
      Fragment fragment = this.mAdded.get(i);
      if (fragment != null && fragment.performOptionsItemSelected(paramMenuItem))
        return true; 
    } 
    return false;
  }
  
  public void dispatchOptionsMenuClosed(Menu paramMenu) {
    if (this.mCurState < 1)
      return; 
    for (int i = 0; i < this.mAdded.size(); i++) {
      Fragment fragment = this.mAdded.get(i);
      if (fragment != null)
        fragment.performOptionsMenuClosed(paramMenu); 
    } 
  }
  
  public void dispatchPause() {
    dispatchStateChange(3);
  }
  
  public void dispatchPictureInPictureModeChanged(boolean paramBoolean) {
    for (int i = this.mAdded.size() - 1; i >= 0; i--) {
      Fragment fragment = this.mAdded.get(i);
      if (fragment != null)
        fragment.performPictureInPictureModeChanged(paramBoolean); 
    } 
  }
  
  public boolean dispatchPrepareOptionsMenu(Menu paramMenu) {
    int j = this.mCurState;
    int i = 0;
    if (j < 1)
      return false; 
    boolean bool;
    for (bool = false; i < this.mAdded.size(); bool = bool1) {
      Fragment fragment = this.mAdded.get(i);
      boolean bool1 = bool;
      if (fragment != null) {
        bool1 = bool;
        if (fragment.performPrepareOptionsMenu(paramMenu))
          bool1 = true; 
      } 
      i++;
    } 
    return bool;
  }
  
  public void dispatchResume() {
    this.mStateSaved = false;
    this.mStopped = false;
    dispatchStateChange(4);
  }
  
  public void dispatchStart() {
    this.mStateSaved = false;
    this.mStopped = false;
    dispatchStateChange(3);
  }
  
  public void dispatchStop() {
    this.mStopped = true;
    dispatchStateChange(2);
  }
  
  void doPendingDeferredStart() {
    if (this.mHavePendingDeferredStart) {
      this.mHavePendingDeferredStart = false;
      startPendingDeferredFragments();
    } 
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    // Byte code:
    //   0: new java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore #8
    //   9: aload #8
    //   11: aload_1
    //   12: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   15: pop
    //   16: aload #8
    //   18: ldc_w '    '
    //   21: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   24: pop
    //   25: aload #8
    //   27: invokevirtual toString : ()Ljava/lang/String;
    //   30: astore #8
    //   32: aload_0
    //   33: getfield mActive : Landroid/util/SparseArray;
    //   36: astore #9
    //   38: iconst_0
    //   39: istore #6
    //   41: aload #9
    //   43: ifnull -> 170
    //   46: aload_0
    //   47: getfield mActive : Landroid/util/SparseArray;
    //   50: invokevirtual size : ()I
    //   53: istore #7
    //   55: iload #7
    //   57: ifle -> 170
    //   60: aload_3
    //   61: aload_1
    //   62: invokevirtual print : (Ljava/lang/String;)V
    //   65: aload_3
    //   66: ldc_w 'Active Fragments in '
    //   69: invokevirtual print : (Ljava/lang/String;)V
    //   72: aload_3
    //   73: aload_0
    //   74: invokestatic identityHashCode : (Ljava/lang/Object;)I
    //   77: invokestatic toHexString : (I)Ljava/lang/String;
    //   80: invokevirtual print : (Ljava/lang/String;)V
    //   83: aload_3
    //   84: ldc_w ':'
    //   87: invokevirtual println : (Ljava/lang/String;)V
    //   90: iconst_0
    //   91: istore #5
    //   93: iload #5
    //   95: iload #7
    //   97: if_icmpge -> 170
    //   100: aload_0
    //   101: getfield mActive : Landroid/util/SparseArray;
    //   104: iload #5
    //   106: invokevirtual valueAt : (I)Ljava/lang/Object;
    //   109: checkcast android/support/v4/app/Fragment
    //   112: astore #9
    //   114: aload_3
    //   115: aload_1
    //   116: invokevirtual print : (Ljava/lang/String;)V
    //   119: aload_3
    //   120: ldc_w '  #'
    //   123: invokevirtual print : (Ljava/lang/String;)V
    //   126: aload_3
    //   127: iload #5
    //   129: invokevirtual print : (I)V
    //   132: aload_3
    //   133: ldc_w ': '
    //   136: invokevirtual print : (Ljava/lang/String;)V
    //   139: aload_3
    //   140: aload #9
    //   142: invokevirtual println : (Ljava/lang/Object;)V
    //   145: aload #9
    //   147: ifnull -> 161
    //   150: aload #9
    //   152: aload #8
    //   154: aload_2
    //   155: aload_3
    //   156: aload #4
    //   158: invokevirtual dump : (Ljava/lang/String;Ljava/io/FileDescriptor;Ljava/io/PrintWriter;[Ljava/lang/String;)V
    //   161: iload #5
    //   163: iconst_1
    //   164: iadd
    //   165: istore #5
    //   167: goto -> 93
    //   170: aload_0
    //   171: getfield mAdded : Ljava/util/ArrayList;
    //   174: invokevirtual size : ()I
    //   177: istore #7
    //   179: iload #7
    //   181: ifle -> 263
    //   184: aload_3
    //   185: aload_1
    //   186: invokevirtual print : (Ljava/lang/String;)V
    //   189: aload_3
    //   190: ldc_w 'Added Fragments:'
    //   193: invokevirtual println : (Ljava/lang/String;)V
    //   196: iconst_0
    //   197: istore #5
    //   199: iload #5
    //   201: iload #7
    //   203: if_icmpge -> 263
    //   206: aload_0
    //   207: getfield mAdded : Ljava/util/ArrayList;
    //   210: iload #5
    //   212: invokevirtual get : (I)Ljava/lang/Object;
    //   215: checkcast android/support/v4/app/Fragment
    //   218: astore #9
    //   220: aload_3
    //   221: aload_1
    //   222: invokevirtual print : (Ljava/lang/String;)V
    //   225: aload_3
    //   226: ldc_w '  #'
    //   229: invokevirtual print : (Ljava/lang/String;)V
    //   232: aload_3
    //   233: iload #5
    //   235: invokevirtual print : (I)V
    //   238: aload_3
    //   239: ldc_w ': '
    //   242: invokevirtual print : (Ljava/lang/String;)V
    //   245: aload_3
    //   246: aload #9
    //   248: invokevirtual toString : ()Ljava/lang/String;
    //   251: invokevirtual println : (Ljava/lang/String;)V
    //   254: iload #5
    //   256: iconst_1
    //   257: iadd
    //   258: istore #5
    //   260: goto -> 199
    //   263: aload_0
    //   264: getfield mCreatedMenus : Ljava/util/ArrayList;
    //   267: ifnull -> 363
    //   270: aload_0
    //   271: getfield mCreatedMenus : Ljava/util/ArrayList;
    //   274: invokevirtual size : ()I
    //   277: istore #7
    //   279: iload #7
    //   281: ifle -> 363
    //   284: aload_3
    //   285: aload_1
    //   286: invokevirtual print : (Ljava/lang/String;)V
    //   289: aload_3
    //   290: ldc_w 'Fragments Created Menus:'
    //   293: invokevirtual println : (Ljava/lang/String;)V
    //   296: iconst_0
    //   297: istore #5
    //   299: iload #5
    //   301: iload #7
    //   303: if_icmpge -> 363
    //   306: aload_0
    //   307: getfield mCreatedMenus : Ljava/util/ArrayList;
    //   310: iload #5
    //   312: invokevirtual get : (I)Ljava/lang/Object;
    //   315: checkcast android/support/v4/app/Fragment
    //   318: astore #9
    //   320: aload_3
    //   321: aload_1
    //   322: invokevirtual print : (Ljava/lang/String;)V
    //   325: aload_3
    //   326: ldc_w '  #'
    //   329: invokevirtual print : (Ljava/lang/String;)V
    //   332: aload_3
    //   333: iload #5
    //   335: invokevirtual print : (I)V
    //   338: aload_3
    //   339: ldc_w ': '
    //   342: invokevirtual print : (Ljava/lang/String;)V
    //   345: aload_3
    //   346: aload #9
    //   348: invokevirtual toString : ()Ljava/lang/String;
    //   351: invokevirtual println : (Ljava/lang/String;)V
    //   354: iload #5
    //   356: iconst_1
    //   357: iadd
    //   358: istore #5
    //   360: goto -> 299
    //   363: aload_0
    //   364: getfield mBackStack : Ljava/util/ArrayList;
    //   367: ifnull -> 474
    //   370: aload_0
    //   371: getfield mBackStack : Ljava/util/ArrayList;
    //   374: invokevirtual size : ()I
    //   377: istore #7
    //   379: iload #7
    //   381: ifle -> 474
    //   384: aload_3
    //   385: aload_1
    //   386: invokevirtual print : (Ljava/lang/String;)V
    //   389: aload_3
    //   390: ldc_w 'Back Stack:'
    //   393: invokevirtual println : (Ljava/lang/String;)V
    //   396: iconst_0
    //   397: istore #5
    //   399: iload #5
    //   401: iload #7
    //   403: if_icmpge -> 474
    //   406: aload_0
    //   407: getfield mBackStack : Ljava/util/ArrayList;
    //   410: iload #5
    //   412: invokevirtual get : (I)Ljava/lang/Object;
    //   415: checkcast android/support/v4/app/BackStackRecord
    //   418: astore #9
    //   420: aload_3
    //   421: aload_1
    //   422: invokevirtual print : (Ljava/lang/String;)V
    //   425: aload_3
    //   426: ldc_w '  #'
    //   429: invokevirtual print : (Ljava/lang/String;)V
    //   432: aload_3
    //   433: iload #5
    //   435: invokevirtual print : (I)V
    //   438: aload_3
    //   439: ldc_w ': '
    //   442: invokevirtual print : (Ljava/lang/String;)V
    //   445: aload_3
    //   446: aload #9
    //   448: invokevirtual toString : ()Ljava/lang/String;
    //   451: invokevirtual println : (Ljava/lang/String;)V
    //   454: aload #9
    //   456: aload #8
    //   458: aload_2
    //   459: aload_3
    //   460: aload #4
    //   462: invokevirtual dump : (Ljava/lang/String;Ljava/io/FileDescriptor;Ljava/io/PrintWriter;[Ljava/lang/String;)V
    //   465: iload #5
    //   467: iconst_1
    //   468: iadd
    //   469: istore #5
    //   471: goto -> 399
    //   474: aload_0
    //   475: monitorenter
    //   476: aload_0
    //   477: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   480: ifnull -> 571
    //   483: aload_0
    //   484: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   487: invokevirtual size : ()I
    //   490: istore #7
    //   492: iload #7
    //   494: ifle -> 571
    //   497: aload_3
    //   498: aload_1
    //   499: invokevirtual print : (Ljava/lang/String;)V
    //   502: aload_3
    //   503: ldc_w 'Back Stack Indices:'
    //   506: invokevirtual println : (Ljava/lang/String;)V
    //   509: iconst_0
    //   510: istore #5
    //   512: iload #5
    //   514: iload #7
    //   516: if_icmpge -> 571
    //   519: aload_0
    //   520: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   523: iload #5
    //   525: invokevirtual get : (I)Ljava/lang/Object;
    //   528: checkcast android/support/v4/app/BackStackRecord
    //   531: astore_2
    //   532: aload_3
    //   533: aload_1
    //   534: invokevirtual print : (Ljava/lang/String;)V
    //   537: aload_3
    //   538: ldc_w '  #'
    //   541: invokevirtual print : (Ljava/lang/String;)V
    //   544: aload_3
    //   545: iload #5
    //   547: invokevirtual print : (I)V
    //   550: aload_3
    //   551: ldc_w ': '
    //   554: invokevirtual print : (Ljava/lang/String;)V
    //   557: aload_3
    //   558: aload_2
    //   559: invokevirtual println : (Ljava/lang/Object;)V
    //   562: iload #5
    //   564: iconst_1
    //   565: iadd
    //   566: istore #5
    //   568: goto -> 512
    //   571: aload_0
    //   572: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   575: ifnull -> 614
    //   578: aload_0
    //   579: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   582: invokevirtual size : ()I
    //   585: ifle -> 614
    //   588: aload_3
    //   589: aload_1
    //   590: invokevirtual print : (Ljava/lang/String;)V
    //   593: aload_3
    //   594: ldc_w 'mAvailBackStackIndices: '
    //   597: invokevirtual print : (Ljava/lang/String;)V
    //   600: aload_3
    //   601: aload_0
    //   602: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   605: invokevirtual toArray : ()[Ljava/lang/Object;
    //   608: invokestatic toString : ([Ljava/lang/Object;)Ljava/lang/String;
    //   611: invokevirtual println : (Ljava/lang/String;)V
    //   614: aload_0
    //   615: monitorexit
    //   616: aload_0
    //   617: getfield mPendingActions : Ljava/util/ArrayList;
    //   620: ifnull -> 712
    //   623: aload_0
    //   624: getfield mPendingActions : Ljava/util/ArrayList;
    //   627: invokevirtual size : ()I
    //   630: istore #7
    //   632: iload #7
    //   634: ifle -> 712
    //   637: aload_3
    //   638: aload_1
    //   639: invokevirtual print : (Ljava/lang/String;)V
    //   642: aload_3
    //   643: ldc_w 'Pending Actions:'
    //   646: invokevirtual println : (Ljava/lang/String;)V
    //   649: iload #6
    //   651: istore #5
    //   653: iload #5
    //   655: iload #7
    //   657: if_icmpge -> 712
    //   660: aload_0
    //   661: getfield mPendingActions : Ljava/util/ArrayList;
    //   664: iload #5
    //   666: invokevirtual get : (I)Ljava/lang/Object;
    //   669: checkcast android/support/v4/app/FragmentManagerImpl$OpGenerator
    //   672: astore_2
    //   673: aload_3
    //   674: aload_1
    //   675: invokevirtual print : (Ljava/lang/String;)V
    //   678: aload_3
    //   679: ldc_w '  #'
    //   682: invokevirtual print : (Ljava/lang/String;)V
    //   685: aload_3
    //   686: iload #5
    //   688: invokevirtual print : (I)V
    //   691: aload_3
    //   692: ldc_w ': '
    //   695: invokevirtual print : (Ljava/lang/String;)V
    //   698: aload_3
    //   699: aload_2
    //   700: invokevirtual println : (Ljava/lang/Object;)V
    //   703: iload #5
    //   705: iconst_1
    //   706: iadd
    //   707: istore #5
    //   709: goto -> 653
    //   712: aload_3
    //   713: aload_1
    //   714: invokevirtual print : (Ljava/lang/String;)V
    //   717: aload_3
    //   718: ldc_w 'FragmentManager misc state:'
    //   721: invokevirtual println : (Ljava/lang/String;)V
    //   724: aload_3
    //   725: aload_1
    //   726: invokevirtual print : (Ljava/lang/String;)V
    //   729: aload_3
    //   730: ldc_w '  mHost='
    //   733: invokevirtual print : (Ljava/lang/String;)V
    //   736: aload_3
    //   737: aload_0
    //   738: getfield mHost : Landroid/support/v4/app/FragmentHostCallback;
    //   741: invokevirtual println : (Ljava/lang/Object;)V
    //   744: aload_3
    //   745: aload_1
    //   746: invokevirtual print : (Ljava/lang/String;)V
    //   749: aload_3
    //   750: ldc_w '  mContainer='
    //   753: invokevirtual print : (Ljava/lang/String;)V
    //   756: aload_3
    //   757: aload_0
    //   758: getfield mContainer : Landroid/support/v4/app/FragmentContainer;
    //   761: invokevirtual println : (Ljava/lang/Object;)V
    //   764: aload_0
    //   765: getfield mParent : Landroid/support/v4/app/Fragment;
    //   768: ifnull -> 791
    //   771: aload_3
    //   772: aload_1
    //   773: invokevirtual print : (Ljava/lang/String;)V
    //   776: aload_3
    //   777: ldc_w '  mParent='
    //   780: invokevirtual print : (Ljava/lang/String;)V
    //   783: aload_3
    //   784: aload_0
    //   785: getfield mParent : Landroid/support/v4/app/Fragment;
    //   788: invokevirtual println : (Ljava/lang/Object;)V
    //   791: aload_3
    //   792: aload_1
    //   793: invokevirtual print : (Ljava/lang/String;)V
    //   796: aload_3
    //   797: ldc_w '  mCurState='
    //   800: invokevirtual print : (Ljava/lang/String;)V
    //   803: aload_3
    //   804: aload_0
    //   805: getfield mCurState : I
    //   808: invokevirtual print : (I)V
    //   811: aload_3
    //   812: ldc_w ' mStateSaved='
    //   815: invokevirtual print : (Ljava/lang/String;)V
    //   818: aload_3
    //   819: aload_0
    //   820: getfield mStateSaved : Z
    //   823: invokevirtual print : (Z)V
    //   826: aload_3
    //   827: ldc_w ' mStopped='
    //   830: invokevirtual print : (Ljava/lang/String;)V
    //   833: aload_3
    //   834: aload_0
    //   835: getfield mStopped : Z
    //   838: invokevirtual print : (Z)V
    //   841: aload_3
    //   842: ldc_w ' mDestroyed='
    //   845: invokevirtual print : (Ljava/lang/String;)V
    //   848: aload_3
    //   849: aload_0
    //   850: getfield mDestroyed : Z
    //   853: invokevirtual println : (Z)V
    //   856: aload_0
    //   857: getfield mNeedMenuInvalidate : Z
    //   860: ifeq -> 883
    //   863: aload_3
    //   864: aload_1
    //   865: invokevirtual print : (Ljava/lang/String;)V
    //   868: aload_3
    //   869: ldc_w '  mNeedMenuInvalidate='
    //   872: invokevirtual print : (Ljava/lang/String;)V
    //   875: aload_3
    //   876: aload_0
    //   877: getfield mNeedMenuInvalidate : Z
    //   880: invokevirtual println : (Z)V
    //   883: aload_0
    //   884: getfield mNoTransactionsBecause : Ljava/lang/String;
    //   887: ifnull -> 910
    //   890: aload_3
    //   891: aload_1
    //   892: invokevirtual print : (Ljava/lang/String;)V
    //   895: aload_3
    //   896: ldc_w '  mNoTransactionsBecause='
    //   899: invokevirtual print : (Ljava/lang/String;)V
    //   902: aload_3
    //   903: aload_0
    //   904: getfield mNoTransactionsBecause : Ljava/lang/String;
    //   907: invokevirtual println : (Ljava/lang/String;)V
    //   910: return
    //   911: astore_1
    //   912: aload_0
    //   913: monitorexit
    //   914: aload_1
    //   915: athrow
    // Exception table:
    //   from	to	target	type
    //   476	492	911	finally
    //   497	509	911	finally
    //   519	562	911	finally
    //   571	614	911	finally
    //   614	616	911	finally
    //   912	914	911	finally
  }
  
  public void enqueueAction(OpGenerator paramOpGenerator, boolean paramBoolean) {
    // Byte code:
    //   0: iload_2
    //   1: ifne -> 8
    //   4: aload_0
    //   5: invokespecial checkStateLoss : ()V
    //   8: aload_0
    //   9: monitorenter
    //   10: aload_0
    //   11: getfield mDestroyed : Z
    //   14: ifne -> 61
    //   17: aload_0
    //   18: getfield mHost : Landroid/support/v4/app/FragmentHostCallback;
    //   21: ifnonnull -> 27
    //   24: goto -> 61
    //   27: aload_0
    //   28: getfield mPendingActions : Ljava/util/ArrayList;
    //   31: ifnonnull -> 45
    //   34: aload_0
    //   35: new java/util/ArrayList
    //   38: dup
    //   39: invokespecial <init> : ()V
    //   42: putfield mPendingActions : Ljava/util/ArrayList;
    //   45: aload_0
    //   46: getfield mPendingActions : Ljava/util/ArrayList;
    //   49: aload_1
    //   50: invokevirtual add : (Ljava/lang/Object;)Z
    //   53: pop
    //   54: aload_0
    //   55: invokevirtual scheduleCommit : ()V
    //   58: aload_0
    //   59: monitorexit
    //   60: return
    //   61: iload_2
    //   62: ifeq -> 68
    //   65: aload_0
    //   66: monitorexit
    //   67: return
    //   68: new java/lang/IllegalStateException
    //   71: dup
    //   72: ldc_w 'Activity has been destroyed'
    //   75: invokespecial <init> : (Ljava/lang/String;)V
    //   78: athrow
    //   79: astore_1
    //   80: aload_0
    //   81: monitorexit
    //   82: aload_1
    //   83: athrow
    // Exception table:
    //   from	to	target	type
    //   10	24	79	finally
    //   27	45	79	finally
    //   45	60	79	finally
    //   65	67	79	finally
    //   68	79	79	finally
    //   80	82	79	finally
  }
  
  void ensureInflatedFragmentView(Fragment paramFragment) {
    if (paramFragment.mFromLayout && !paramFragment.mPerformedCreateView) {
      paramFragment.performCreateView(paramFragment.performGetLayoutInflater(paramFragment.mSavedFragmentState), null, paramFragment.mSavedFragmentState);
      if (paramFragment.mView != null) {
        paramFragment.mInnerView = paramFragment.mView;
        paramFragment.mView.setSaveFromParentEnabled(false);
        if (paramFragment.mHidden)
          paramFragment.mView.setVisibility(8); 
        paramFragment.onViewCreated(paramFragment.mView, paramFragment.mSavedFragmentState);
        dispatchOnFragmentViewCreated(paramFragment, paramFragment.mView, paramFragment.mSavedFragmentState, false);
        return;
      } 
      paramFragment.mInnerView = null;
    } 
  }
  
  public boolean execPendingActions() {
    ensureExecReady(true);
    boolean bool = false;
    while (generateOpsForPendingActions(this.mTmpRecords, this.mTmpIsPop)) {
      this.mExecutingActions = true;
      try {
        removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
        cleanupExec();
      } finally {
        cleanupExec();
      } 
    } 
    doPendingDeferredStart();
    burpActive();
    return bool;
  }
  
  public void execSingleAction(OpGenerator paramOpGenerator, boolean paramBoolean) {
    if (paramBoolean && (this.mHost == null || this.mDestroyed))
      return; 
    ensureExecReady(paramBoolean);
    if (paramOpGenerator.generateOps(this.mTmpRecords, this.mTmpIsPop)) {
      this.mExecutingActions = true;
      try {
        removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
      } finally {
        cleanupExec();
      } 
    } 
    doPendingDeferredStart();
    burpActive();
  }
  
  public boolean executePendingTransactions() {
    boolean bool = execPendingActions();
    forcePostponedTransactions();
    return bool;
  }
  
  @Nullable
  public Fragment findFragmentById(int paramInt) {
    int i;
    for (i = this.mAdded.size() - 1; i >= 0; i--) {
      Fragment fragment = this.mAdded.get(i);
      if (fragment != null && fragment.mFragmentId == paramInt)
        return fragment; 
    } 
    if (this.mActive != null)
      for (i = this.mActive.size() - 1; i >= 0; i--) {
        Fragment fragment = (Fragment)this.mActive.valueAt(i);
        if (fragment != null && fragment.mFragmentId == paramInt)
          return fragment; 
      }  
    return null;
  }
  
  @Nullable
  public Fragment findFragmentByTag(@Nullable String paramString) {
    if (paramString != null)
      for (int i = this.mAdded.size() - 1; i >= 0; i--) {
        Fragment fragment = this.mAdded.get(i);
        if (fragment != null && paramString.equals(fragment.mTag))
          return fragment; 
      }  
    if (this.mActive != null && paramString != null)
      for (int i = this.mActive.size() - 1; i >= 0; i--) {
        Fragment fragment = (Fragment)this.mActive.valueAt(i);
        if (fragment != null && paramString.equals(fragment.mTag))
          return fragment; 
      }  
    return null;
  }
  
  public Fragment findFragmentByWho(String paramString) {
    if (this.mActive != null && paramString != null)
      for (int i = this.mActive.size() - 1; i >= 0; i--) {
        Fragment fragment = (Fragment)this.mActive.valueAt(i);
        if (fragment != null) {
          fragment = fragment.findFragmentByWho(paramString);
          if (fragment != null)
            return fragment; 
        } 
      }  
    return null;
  }
  
  public void freeBackStackIndex(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   6: iload_1
    //   7: aconst_null
    //   8: invokevirtual set : (ILjava/lang/Object;)Ljava/lang/Object;
    //   11: pop
    //   12: aload_0
    //   13: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   16: ifnonnull -> 30
    //   19: aload_0
    //   20: new java/util/ArrayList
    //   23: dup
    //   24: invokespecial <init> : ()V
    //   27: putfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   30: getstatic android/support/v4/app/FragmentManagerImpl.DEBUG : Z
    //   33: ifeq -> 68
    //   36: new java/lang/StringBuilder
    //   39: dup
    //   40: invokespecial <init> : ()V
    //   43: astore_2
    //   44: aload_2
    //   45: ldc_w 'Freeing back stack index '
    //   48: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   51: pop
    //   52: aload_2
    //   53: iload_1
    //   54: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   57: pop
    //   58: ldc 'FragmentManager'
    //   60: aload_2
    //   61: invokevirtual toString : ()Ljava/lang/String;
    //   64: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   67: pop
    //   68: aload_0
    //   69: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   72: iload_1
    //   73: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   76: invokevirtual add : (Ljava/lang/Object;)Z
    //   79: pop
    //   80: aload_0
    //   81: monitorexit
    //   82: return
    //   83: astore_2
    //   84: aload_0
    //   85: monitorexit
    //   86: aload_2
    //   87: athrow
    // Exception table:
    //   from	to	target	type
    //   2	30	83	finally
    //   30	68	83	finally
    //   68	82	83	finally
    //   84	86	83	finally
  }
  
  int getActiveFragmentCount() {
    return (this.mActive == null) ? 0 : this.mActive.size();
  }
  
  List<Fragment> getActiveFragments() {
    if (this.mActive == null)
      return null; 
    int j = this.mActive.size();
    ArrayList<Object> arrayList = new ArrayList(j);
    for (int i = 0; i < j; i++)
      arrayList.add(this.mActive.valueAt(i)); 
    return arrayList;
  }
  
  public FragmentManager.BackStackEntry getBackStackEntryAt(int paramInt) {
    return this.mBackStack.get(paramInt);
  }
  
  public int getBackStackEntryCount() {
    return (this.mBackStack != null) ? this.mBackStack.size() : 0;
  }
  
  @Nullable
  public Fragment getFragment(Bundle paramBundle, String paramString) {
    int i = paramBundle.getInt(paramString, -1);
    if (i == -1)
      return null; 
    Fragment fragment = (Fragment)this.mActive.get(i);
    if (fragment == null) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Fragment no longer exists for key ");
      stringBuilder.append(paramString);
      stringBuilder.append(": index ");
      stringBuilder.append(i);
      throwException(new IllegalStateException(stringBuilder.toString()));
    } 
    return fragment;
  }
  
  public List<Fragment> getFragments() {
    if (this.mAdded.isEmpty())
      return Collections.emptyList(); 
    synchronized (this.mAdded) {
      return (List)this.mAdded.clone();
    } 
  }
  
  LayoutInflater.Factory2 getLayoutInflaterFactory() {
    return this;
  }
  
  @Nullable
  public Fragment getPrimaryNavigationFragment() {
    return this.mPrimaryNav;
  }
  
  public void hideFragment(Fragment paramFragment) {
    if (DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("hide: ");
      stringBuilder.append(paramFragment);
      Log.v("FragmentManager", stringBuilder.toString());
    } 
    if (!paramFragment.mHidden) {
      paramFragment.mHidden = true;
      paramFragment.mHiddenChanged = true ^ paramFragment.mHiddenChanged;
    } 
  }
  
  public boolean isDestroyed() {
    return this.mDestroyed;
  }
  
  boolean isStateAtLeast(int paramInt) {
    return (this.mCurState >= paramInt);
  }
  
  public boolean isStateSaved() {
    return (this.mStateSaved || this.mStopped);
  }
  
  AnimationOrAnimator loadAnimation(Fragment paramFragment, int paramInt1, boolean paramBoolean, int paramInt2) {
    int i = paramFragment.getNextAnim();
    Animation animation = paramFragment.onCreateAnimation(paramInt1, paramBoolean, i);
    if (animation != null)
      return new AnimationOrAnimator(animation); 
    Animator animator = paramFragment.onCreateAnimator(paramInt1, paramBoolean, i);
    if (animator != null)
      return new AnimationOrAnimator(animator); 
    if (i != 0) {
      boolean bool = "anim".equals(this.mHost.getContext().getResources().getResourceTypeName(i));
      boolean bool2 = false;
      boolean bool1 = bool2;
      if (bool)
        try {
          Animation animation1 = AnimationUtils.loadAnimation(this.mHost.getContext(), i);
          if (animation1 != null)
            return new AnimationOrAnimator(animation1); 
          bool1 = true;
        } catch (android.content.res.Resources.NotFoundException notFoundException) {
          throw notFoundException;
        } catch (RuntimeException runtimeException) {
          bool1 = bool2;
        }  
      if (!bool1)
        try {
          animator = AnimatorInflater.loadAnimator(this.mHost.getContext(), i);
          if (animator != null)
            return new AnimationOrAnimator(animator); 
        } catch (RuntimeException runtimeException) {
          if (bool)
            throw runtimeException; 
          Animation animation1 = AnimationUtils.loadAnimation(this.mHost.getContext(), i);
          if (animation1 != null)
            return new AnimationOrAnimator(animation1); 
        }  
    } 
    if (paramInt1 == 0)
      return null; 
    paramInt1 = transitToStyleIndex(paramInt1, paramBoolean);
    if (paramInt1 < 0)
      return null; 
    switch (paramInt1) {
      default:
        paramInt1 = paramInt2;
        if (paramInt2 == 0) {
          paramInt1 = paramInt2;
          if (this.mHost.onHasWindowAnimations())
            paramInt1 = this.mHost.onGetWindowAnimations(); 
        } 
        break;
      case 6:
        return makeFadeAnimation(this.mHost.getContext(), 1.0F, 0.0F);
      case 5:
        return makeFadeAnimation(this.mHost.getContext(), 0.0F, 1.0F);
      case 4:
        return makeOpenCloseAnimation(this.mHost.getContext(), 1.0F, 1.075F, 1.0F, 0.0F);
      case 3:
        return makeOpenCloseAnimation(this.mHost.getContext(), 0.975F, 1.0F, 0.0F, 1.0F);
      case 2:
        return makeOpenCloseAnimation(this.mHost.getContext(), 1.0F, 0.975F, 1.0F, 0.0F);
      case 1:
        return makeOpenCloseAnimation(this.mHost.getContext(), 1.125F, 1.0F, 0.0F, 1.0F);
    } 
    return (AnimationOrAnimator)((paramInt1 == 0) ? null : null);
  }
  
  void makeActive(Fragment paramFragment) {
    if (paramFragment.mIndex >= 0)
      return; 
    int i = this.mNextFragmentIndex;
    this.mNextFragmentIndex = i + 1;
    paramFragment.setIndex(i, this.mParent);
    if (this.mActive == null)
      this.mActive = new SparseArray(); 
    this.mActive.put(paramFragment.mIndex, paramFragment);
    if (DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Allocated fragment index ");
      stringBuilder.append(paramFragment);
      Log.v("FragmentManager", stringBuilder.toString());
    } 
  }
  
  void makeInactive(Fragment paramFragment) {
    if (paramFragment.mIndex < 0)
      return; 
    if (DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Freeing fragment index ");
      stringBuilder.append(paramFragment);
      Log.v("FragmentManager", stringBuilder.toString());
    } 
    this.mActive.put(paramFragment.mIndex, null);
    paramFragment.initState();
  }
  
  void moveFragmentToExpectedState(Fragment paramFragment) {
    if (paramFragment == null)
      return; 
    int j = this.mCurState;
    int i = j;
    if (paramFragment.mRemoving)
      if (paramFragment.isInBackStack()) {
        i = Math.min(j, 1);
      } else {
        i = Math.min(j, 0);
      }  
    moveToState(paramFragment, i, paramFragment.getNextTransition(), paramFragment.getNextTransitionStyle(), false);
    if (paramFragment.mView != null) {
      Fragment fragment = findFragmentUnder(paramFragment);
      if (fragment != null) {
        View view = fragment.mView;
        ViewGroup viewGroup = paramFragment.mContainer;
        i = viewGroup.indexOfChild(view);
        j = viewGroup.indexOfChild(paramFragment.mView);
        if (j < i) {
          viewGroup.removeViewAt(j);
          viewGroup.addView(paramFragment.mView, i);
        } 
      } 
      if (paramFragment.mIsNewlyAdded && paramFragment.mContainer != null) {
        if (paramFragment.mPostponedAlpha > 0.0F)
          paramFragment.mView.setAlpha(paramFragment.mPostponedAlpha); 
        paramFragment.mPostponedAlpha = 0.0F;
        paramFragment.mIsNewlyAdded = false;
        AnimationOrAnimator animationOrAnimator = loadAnimation(paramFragment, paramFragment.getNextTransition(), true, paramFragment.getNextTransitionStyle());
        if (animationOrAnimator != null) {
          setHWLayerAnimListenerIfAlpha(paramFragment.mView, animationOrAnimator);
          if (animationOrAnimator.animation != null) {
            paramFragment.mView.startAnimation(animationOrAnimator.animation);
          } else {
            animationOrAnimator.animator.setTarget(paramFragment.mView);
            animationOrAnimator.animator.start();
          } 
        } 
      } 
    } 
    if (paramFragment.mHiddenChanged)
      completeShowHideFragment(paramFragment); 
  }
  
  void moveToState(int paramInt, boolean paramBoolean) {
    if (this.mHost == null && paramInt != 0)
      throw new IllegalStateException("No activity"); 
    if (!paramBoolean && paramInt == this.mCurState)
      return; 
    this.mCurState = paramInt;
    if (this.mActive != null) {
      int i = this.mAdded.size();
      for (paramInt = 0; paramInt < i; paramInt++)
        moveFragmentToExpectedState(this.mAdded.get(paramInt)); 
      i = this.mActive.size();
      for (paramInt = 0; paramInt < i; paramInt++) {
        Fragment fragment = (Fragment)this.mActive.valueAt(paramInt);
        if (fragment != null && (fragment.mRemoving || fragment.mDetached) && !fragment.mIsNewlyAdded)
          moveFragmentToExpectedState(fragment); 
      } 
      startPendingDeferredFragments();
      if (this.mNeedMenuInvalidate && this.mHost != null && this.mCurState == 4) {
        this.mHost.onSupportInvalidateOptionsMenu();
        this.mNeedMenuInvalidate = false;
      } 
    } 
  }
  
  void moveToState(Fragment paramFragment) {
    moveToState(paramFragment, this.mCurState, 0, 0, false);
  }
  
  void moveToState(Fragment paramFragment, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean) {
    // Byte code:
    //   0: aload_1
    //   1: getfield mAdded : Z
    //   4: istore #9
    //   6: iconst_1
    //   7: istore #7
    //   9: iconst_1
    //   10: istore #8
    //   12: iload #9
    //   14: ifeq -> 30
    //   17: aload_1
    //   18: getfield mDetached : Z
    //   21: ifeq -> 27
    //   24: goto -> 30
    //   27: goto -> 44
    //   30: iload_2
    //   31: istore #6
    //   33: iload #6
    //   35: istore_2
    //   36: iload #6
    //   38: iconst_1
    //   39: if_icmple -> 44
    //   42: iconst_1
    //   43: istore_2
    //   44: iload_2
    //   45: istore #6
    //   47: aload_1
    //   48: getfield mRemoving : Z
    //   51: ifeq -> 91
    //   54: iload_2
    //   55: istore #6
    //   57: iload_2
    //   58: aload_1
    //   59: getfield mState : I
    //   62: if_icmple -> 91
    //   65: aload_1
    //   66: getfield mState : I
    //   69: ifne -> 85
    //   72: aload_1
    //   73: invokevirtual isInBackStack : ()Z
    //   76: ifeq -> 85
    //   79: iconst_1
    //   80: istore #6
    //   82: goto -> 91
    //   85: aload_1
    //   86: getfield mState : I
    //   89: istore #6
    //   91: aload_1
    //   92: getfield mDeferStart : Z
    //   95: ifeq -> 117
    //   98: aload_1
    //   99: getfield mState : I
    //   102: iconst_3
    //   103: if_icmpge -> 117
    //   106: iload #6
    //   108: iconst_2
    //   109: if_icmple -> 117
    //   112: iconst_2
    //   113: istore_2
    //   114: goto -> 120
    //   117: iload #6
    //   119: istore_2
    //   120: aload_1
    //   121: getfield mState : I
    //   124: iload_2
    //   125: if_icmpgt -> 1363
    //   128: aload_1
    //   129: getfield mFromLayout : Z
    //   132: ifeq -> 143
    //   135: aload_1
    //   136: getfield mInLayout : Z
    //   139: ifne -> 143
    //   142: return
    //   143: aload_1
    //   144: invokevirtual getAnimatingAway : ()Landroid/view/View;
    //   147: ifnonnull -> 157
    //   150: aload_1
    //   151: invokevirtual getAnimator : ()Landroid/animation/Animator;
    //   154: ifnull -> 179
    //   157: aload_1
    //   158: aconst_null
    //   159: invokevirtual setAnimatingAway : (Landroid/view/View;)V
    //   162: aload_1
    //   163: aconst_null
    //   164: invokevirtual setAnimator : (Landroid/animation/Animator;)V
    //   167: aload_0
    //   168: aload_1
    //   169: aload_1
    //   170: invokevirtual getStateAfterAnimating : ()I
    //   173: iconst_0
    //   174: iconst_0
    //   175: iconst_1
    //   176: invokevirtual moveToState : (Landroid/support/v4/app/Fragment;IIIZ)V
    //   179: iload_2
    //   180: istore #4
    //   182: iload_2
    //   183: istore #6
    //   185: iload_2
    //   186: istore_3
    //   187: aload_1
    //   188: getfield mState : I
    //   191: tableswitch default -> 220, 0 -> 226, 1 -> 761, 2 -> 1223, 3 -> 1287
    //   220: iload_2
    //   221: istore #6
    //   223: goto -> 1976
    //   226: iload_2
    //   227: istore #4
    //   229: iload_2
    //   230: ifle -> 761
    //   233: getstatic android/support/v4/app/FragmentManagerImpl.DEBUG : Z
    //   236: ifeq -> 275
    //   239: new java/lang/StringBuilder
    //   242: dup
    //   243: invokespecial <init> : ()V
    //   246: astore #10
    //   248: aload #10
    //   250: ldc_w 'moveto CREATED: '
    //   253: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   256: pop
    //   257: aload #10
    //   259: aload_1
    //   260: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   263: pop
    //   264: ldc 'FragmentManager'
    //   266: aload #10
    //   268: invokevirtual toString : ()Ljava/lang/String;
    //   271: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   274: pop
    //   275: iload_2
    //   276: istore #4
    //   278: aload_1
    //   279: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   282: ifnull -> 416
    //   285: aload_1
    //   286: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   289: aload_0
    //   290: getfield mHost : Landroid/support/v4/app/FragmentHostCallback;
    //   293: invokevirtual getContext : ()Landroid/content/Context;
    //   296: invokevirtual getClassLoader : ()Ljava/lang/ClassLoader;
    //   299: invokevirtual setClassLoader : (Ljava/lang/ClassLoader;)V
    //   302: aload_1
    //   303: aload_1
    //   304: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   307: ldc 'android:view_state'
    //   309: invokevirtual getSparseParcelableArray : (Ljava/lang/String;)Landroid/util/SparseArray;
    //   312: putfield mSavedViewState : Landroid/util/SparseArray;
    //   315: aload_1
    //   316: aload_0
    //   317: aload_1
    //   318: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   321: ldc 'android:target_state'
    //   323: invokevirtual getFragment : (Landroid/os/Bundle;Ljava/lang/String;)Landroid/support/v4/app/Fragment;
    //   326: putfield mTarget : Landroid/support/v4/app/Fragment;
    //   329: aload_1
    //   330: getfield mTarget : Landroid/support/v4/app/Fragment;
    //   333: ifnull -> 350
    //   336: aload_1
    //   337: aload_1
    //   338: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   341: ldc 'android:target_req_state'
    //   343: iconst_0
    //   344: invokevirtual getInt : (Ljava/lang/String;I)I
    //   347: putfield mTargetRequestCode : I
    //   350: aload_1
    //   351: getfield mSavedUserVisibleHint : Ljava/lang/Boolean;
    //   354: ifnull -> 376
    //   357: aload_1
    //   358: aload_1
    //   359: getfield mSavedUserVisibleHint : Ljava/lang/Boolean;
    //   362: invokevirtual booleanValue : ()Z
    //   365: putfield mUserVisibleHint : Z
    //   368: aload_1
    //   369: aconst_null
    //   370: putfield mSavedUserVisibleHint : Ljava/lang/Boolean;
    //   373: goto -> 390
    //   376: aload_1
    //   377: aload_1
    //   378: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   381: ldc 'android:user_visible_hint'
    //   383: iconst_1
    //   384: invokevirtual getBoolean : (Ljava/lang/String;Z)Z
    //   387: putfield mUserVisibleHint : Z
    //   390: iload_2
    //   391: istore #4
    //   393: aload_1
    //   394: getfield mUserVisibleHint : Z
    //   397: ifne -> 416
    //   400: aload_1
    //   401: iconst_1
    //   402: putfield mDeferStart : Z
    //   405: iload_2
    //   406: istore #4
    //   408: iload_2
    //   409: iconst_2
    //   410: if_icmple -> 416
    //   413: iconst_2
    //   414: istore #4
    //   416: aload_1
    //   417: aload_0
    //   418: getfield mHost : Landroid/support/v4/app/FragmentHostCallback;
    //   421: putfield mHost : Landroid/support/v4/app/FragmentHostCallback;
    //   424: aload_1
    //   425: aload_0
    //   426: getfield mParent : Landroid/support/v4/app/Fragment;
    //   429: putfield mParentFragment : Landroid/support/v4/app/Fragment;
    //   432: aload_0
    //   433: getfield mParent : Landroid/support/v4/app/Fragment;
    //   436: ifnull -> 451
    //   439: aload_0
    //   440: getfield mParent : Landroid/support/v4/app/Fragment;
    //   443: getfield mChildFragmentManager : Landroid/support/v4/app/FragmentManagerImpl;
    //   446: astore #10
    //   448: goto -> 460
    //   451: aload_0
    //   452: getfield mHost : Landroid/support/v4/app/FragmentHostCallback;
    //   455: invokevirtual getFragmentManagerImpl : ()Landroid/support/v4/app/FragmentManagerImpl;
    //   458: astore #10
    //   460: aload_1
    //   461: aload #10
    //   463: putfield mFragmentManager : Landroid/support/v4/app/FragmentManagerImpl;
    //   466: aload_1
    //   467: getfield mTarget : Landroid/support/v4/app/Fragment;
    //   470: ifnull -> 583
    //   473: aload_0
    //   474: getfield mActive : Landroid/util/SparseArray;
    //   477: aload_1
    //   478: getfield mTarget : Landroid/support/v4/app/Fragment;
    //   481: getfield mIndex : I
    //   484: invokevirtual get : (I)Ljava/lang/Object;
    //   487: aload_1
    //   488: getfield mTarget : Landroid/support/v4/app/Fragment;
    //   491: if_acmpeq -> 560
    //   494: new java/lang/StringBuilder
    //   497: dup
    //   498: invokespecial <init> : ()V
    //   501: astore #10
    //   503: aload #10
    //   505: ldc_w 'Fragment '
    //   508: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   511: pop
    //   512: aload #10
    //   514: aload_1
    //   515: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   518: pop
    //   519: aload #10
    //   521: ldc_w ' declared target fragment '
    //   524: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   527: pop
    //   528: aload #10
    //   530: aload_1
    //   531: getfield mTarget : Landroid/support/v4/app/Fragment;
    //   534: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   537: pop
    //   538: aload #10
    //   540: ldc_w ' that does not belong to this FragmentManager!'
    //   543: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   546: pop
    //   547: new java/lang/IllegalStateException
    //   550: dup
    //   551: aload #10
    //   553: invokevirtual toString : ()Ljava/lang/String;
    //   556: invokespecial <init> : (Ljava/lang/String;)V
    //   559: athrow
    //   560: aload_1
    //   561: getfield mTarget : Landroid/support/v4/app/Fragment;
    //   564: getfield mState : I
    //   567: iconst_1
    //   568: if_icmpge -> 583
    //   571: aload_0
    //   572: aload_1
    //   573: getfield mTarget : Landroid/support/v4/app/Fragment;
    //   576: iconst_1
    //   577: iconst_0
    //   578: iconst_0
    //   579: iconst_1
    //   580: invokevirtual moveToState : (Landroid/support/v4/app/Fragment;IIIZ)V
    //   583: aload_0
    //   584: aload_1
    //   585: aload_0
    //   586: getfield mHost : Landroid/support/v4/app/FragmentHostCallback;
    //   589: invokevirtual getContext : ()Landroid/content/Context;
    //   592: iconst_0
    //   593: invokevirtual dispatchOnFragmentPreAttached : (Landroid/support/v4/app/Fragment;Landroid/content/Context;Z)V
    //   596: aload_1
    //   597: iconst_0
    //   598: putfield mCalled : Z
    //   601: aload_1
    //   602: aload_0
    //   603: getfield mHost : Landroid/support/v4/app/FragmentHostCallback;
    //   606: invokevirtual getContext : ()Landroid/content/Context;
    //   609: invokevirtual onAttach : (Landroid/content/Context;)V
    //   612: aload_1
    //   613: getfield mCalled : Z
    //   616: ifne -> 666
    //   619: new java/lang/StringBuilder
    //   622: dup
    //   623: invokespecial <init> : ()V
    //   626: astore #10
    //   628: aload #10
    //   630: ldc_w 'Fragment '
    //   633: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   636: pop
    //   637: aload #10
    //   639: aload_1
    //   640: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   643: pop
    //   644: aload #10
    //   646: ldc_w ' did not call through to super.onAttach()'
    //   649: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   652: pop
    //   653: new android/support/v4/app/SuperNotCalledException
    //   656: dup
    //   657: aload #10
    //   659: invokevirtual toString : ()Ljava/lang/String;
    //   662: invokespecial <init> : (Ljava/lang/String;)V
    //   665: athrow
    //   666: aload_1
    //   667: getfield mParentFragment : Landroid/support/v4/app/Fragment;
    //   670: ifnonnull -> 684
    //   673: aload_0
    //   674: getfield mHost : Landroid/support/v4/app/FragmentHostCallback;
    //   677: aload_1
    //   678: invokevirtual onAttachFragment : (Landroid/support/v4/app/Fragment;)V
    //   681: goto -> 692
    //   684: aload_1
    //   685: getfield mParentFragment : Landroid/support/v4/app/Fragment;
    //   688: aload_1
    //   689: invokevirtual onAttachFragment : (Landroid/support/v4/app/Fragment;)V
    //   692: aload_0
    //   693: aload_1
    //   694: aload_0
    //   695: getfield mHost : Landroid/support/v4/app/FragmentHostCallback;
    //   698: invokevirtual getContext : ()Landroid/content/Context;
    //   701: iconst_0
    //   702: invokevirtual dispatchOnFragmentAttached : (Landroid/support/v4/app/Fragment;Landroid/content/Context;Z)V
    //   705: aload_1
    //   706: getfield mIsCreated : Z
    //   709: ifne -> 743
    //   712: aload_0
    //   713: aload_1
    //   714: aload_1
    //   715: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   718: iconst_0
    //   719: invokevirtual dispatchOnFragmentPreCreated : (Landroid/support/v4/app/Fragment;Landroid/os/Bundle;Z)V
    //   722: aload_1
    //   723: aload_1
    //   724: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   727: invokevirtual performCreate : (Landroid/os/Bundle;)V
    //   730: aload_0
    //   731: aload_1
    //   732: aload_1
    //   733: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   736: iconst_0
    //   737: invokevirtual dispatchOnFragmentCreated : (Landroid/support/v4/app/Fragment;Landroid/os/Bundle;Z)V
    //   740: goto -> 756
    //   743: aload_1
    //   744: aload_1
    //   745: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   748: invokevirtual restoreChildFragmentState : (Landroid/os/Bundle;)V
    //   751: aload_1
    //   752: iconst_1
    //   753: putfield mState : I
    //   756: aload_1
    //   757: iconst_0
    //   758: putfield mRetaining : Z
    //   761: aload_0
    //   762: aload_1
    //   763: invokevirtual ensureInflatedFragmentView : (Landroid/support/v4/app/Fragment;)V
    //   766: iload #4
    //   768: istore #6
    //   770: iload #4
    //   772: iconst_1
    //   773: if_icmple -> 1223
    //   776: getstatic android/support/v4/app/FragmentManagerImpl.DEBUG : Z
    //   779: ifeq -> 818
    //   782: new java/lang/StringBuilder
    //   785: dup
    //   786: invokespecial <init> : ()V
    //   789: astore #10
    //   791: aload #10
    //   793: ldc_w 'moveto ACTIVITY_CREATED: '
    //   796: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   799: pop
    //   800: aload #10
    //   802: aload_1
    //   803: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   806: pop
    //   807: ldc 'FragmentManager'
    //   809: aload #10
    //   811: invokevirtual toString : ()Ljava/lang/String;
    //   814: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   817: pop
    //   818: aload_1
    //   819: getfield mFromLayout : Z
    //   822: ifne -> 1181
    //   825: aload_1
    //   826: getfield mContainerId : I
    //   829: ifeq -> 1034
    //   832: aload_1
    //   833: getfield mContainerId : I
    //   836: iconst_m1
    //   837: if_icmpne -> 890
    //   840: new java/lang/StringBuilder
    //   843: dup
    //   844: invokespecial <init> : ()V
    //   847: astore #10
    //   849: aload #10
    //   851: ldc_w 'Cannot create fragment '
    //   854: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   857: pop
    //   858: aload #10
    //   860: aload_1
    //   861: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   864: pop
    //   865: aload #10
    //   867: ldc_w ' for a container view with no id'
    //   870: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   873: pop
    //   874: aload_0
    //   875: new java/lang/IllegalArgumentException
    //   878: dup
    //   879: aload #10
    //   881: invokevirtual toString : ()Ljava/lang/String;
    //   884: invokespecial <init> : (Ljava/lang/String;)V
    //   887: invokespecial throwException : (Ljava/lang/RuntimeException;)V
    //   890: aload_0
    //   891: getfield mContainer : Landroid/support/v4/app/FragmentContainer;
    //   894: aload_1
    //   895: getfield mContainerId : I
    //   898: invokevirtual onFindViewById : (I)Landroid/view/View;
    //   901: checkcast android/view/ViewGroup
    //   904: astore #11
    //   906: aload #11
    //   908: astore #10
    //   910: aload #11
    //   912: ifnonnull -> 1037
    //   915: aload #11
    //   917: astore #10
    //   919: aload_1
    //   920: getfield mRestored : Z
    //   923: ifne -> 1037
    //   926: aload_1
    //   927: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   930: aload_1
    //   931: getfield mContainerId : I
    //   934: invokevirtual getResourceName : (I)Ljava/lang/String;
    //   937: astore #10
    //   939: goto -> 947
    //   942: ldc_w 'unknown'
    //   945: astore #10
    //   947: new java/lang/StringBuilder
    //   950: dup
    //   951: invokespecial <init> : ()V
    //   954: astore #12
    //   956: aload #12
    //   958: ldc_w 'No view found for id 0x'
    //   961: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   964: pop
    //   965: aload #12
    //   967: aload_1
    //   968: getfield mContainerId : I
    //   971: invokestatic toHexString : (I)Ljava/lang/String;
    //   974: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   977: pop
    //   978: aload #12
    //   980: ldc_w ' ('
    //   983: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   986: pop
    //   987: aload #12
    //   989: aload #10
    //   991: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   994: pop
    //   995: aload #12
    //   997: ldc_w ') for fragment '
    //   1000: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1003: pop
    //   1004: aload #12
    //   1006: aload_1
    //   1007: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1010: pop
    //   1011: aload_0
    //   1012: new java/lang/IllegalArgumentException
    //   1015: dup
    //   1016: aload #12
    //   1018: invokevirtual toString : ()Ljava/lang/String;
    //   1021: invokespecial <init> : (Ljava/lang/String;)V
    //   1024: invokespecial throwException : (Ljava/lang/RuntimeException;)V
    //   1027: aload #11
    //   1029: astore #10
    //   1031: goto -> 1037
    //   1034: aconst_null
    //   1035: astore #10
    //   1037: aload_1
    //   1038: aload #10
    //   1040: putfield mContainer : Landroid/view/ViewGroup;
    //   1043: aload_1
    //   1044: aload_1
    //   1045: aload_1
    //   1046: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   1049: invokevirtual performGetLayoutInflater : (Landroid/os/Bundle;)Landroid/view/LayoutInflater;
    //   1052: aload #10
    //   1054: aload_1
    //   1055: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   1058: invokevirtual performCreateView : (Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)V
    //   1061: aload_1
    //   1062: getfield mView : Landroid/view/View;
    //   1065: ifnull -> 1176
    //   1068: aload_1
    //   1069: aload_1
    //   1070: getfield mView : Landroid/view/View;
    //   1073: putfield mInnerView : Landroid/view/View;
    //   1076: aload_1
    //   1077: getfield mView : Landroid/view/View;
    //   1080: iconst_0
    //   1081: invokevirtual setSaveFromParentEnabled : (Z)V
    //   1084: aload #10
    //   1086: ifnull -> 1098
    //   1089: aload #10
    //   1091: aload_1
    //   1092: getfield mView : Landroid/view/View;
    //   1095: invokevirtual addView : (Landroid/view/View;)V
    //   1098: aload_1
    //   1099: getfield mHidden : Z
    //   1102: ifeq -> 1114
    //   1105: aload_1
    //   1106: getfield mView : Landroid/view/View;
    //   1109: bipush #8
    //   1111: invokevirtual setVisibility : (I)V
    //   1114: aload_1
    //   1115: aload_1
    //   1116: getfield mView : Landroid/view/View;
    //   1119: aload_1
    //   1120: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   1123: invokevirtual onViewCreated : (Landroid/view/View;Landroid/os/Bundle;)V
    //   1126: aload_0
    //   1127: aload_1
    //   1128: aload_1
    //   1129: getfield mView : Landroid/view/View;
    //   1132: aload_1
    //   1133: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   1136: iconst_0
    //   1137: invokevirtual dispatchOnFragmentViewCreated : (Landroid/support/v4/app/Fragment;Landroid/view/View;Landroid/os/Bundle;Z)V
    //   1140: aload_1
    //   1141: getfield mView : Landroid/view/View;
    //   1144: invokevirtual getVisibility : ()I
    //   1147: ifne -> 1164
    //   1150: aload_1
    //   1151: getfield mContainer : Landroid/view/ViewGroup;
    //   1154: ifnull -> 1164
    //   1157: iload #8
    //   1159: istore #5
    //   1161: goto -> 1167
    //   1164: iconst_0
    //   1165: istore #5
    //   1167: aload_1
    //   1168: iload #5
    //   1170: putfield mIsNewlyAdded : Z
    //   1173: goto -> 1181
    //   1176: aload_1
    //   1177: aconst_null
    //   1178: putfield mInnerView : Landroid/view/View;
    //   1181: aload_1
    //   1182: aload_1
    //   1183: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   1186: invokevirtual performActivityCreated : (Landroid/os/Bundle;)V
    //   1189: aload_0
    //   1190: aload_1
    //   1191: aload_1
    //   1192: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   1195: iconst_0
    //   1196: invokevirtual dispatchOnFragmentActivityCreated : (Landroid/support/v4/app/Fragment;Landroid/os/Bundle;Z)V
    //   1199: aload_1
    //   1200: getfield mView : Landroid/view/View;
    //   1203: ifnull -> 1214
    //   1206: aload_1
    //   1207: aload_1
    //   1208: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   1211: invokevirtual restoreViewState : (Landroid/os/Bundle;)V
    //   1214: aload_1
    //   1215: aconst_null
    //   1216: putfield mSavedFragmentState : Landroid/os/Bundle;
    //   1219: iload #4
    //   1221: istore #6
    //   1223: iload #6
    //   1225: istore_3
    //   1226: iload #6
    //   1228: iconst_2
    //   1229: if_icmple -> 1287
    //   1232: getstatic android/support/v4/app/FragmentManagerImpl.DEBUG : Z
    //   1235: ifeq -> 1274
    //   1238: new java/lang/StringBuilder
    //   1241: dup
    //   1242: invokespecial <init> : ()V
    //   1245: astore #10
    //   1247: aload #10
    //   1249: ldc_w 'moveto STARTED: '
    //   1252: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1255: pop
    //   1256: aload #10
    //   1258: aload_1
    //   1259: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1262: pop
    //   1263: ldc 'FragmentManager'
    //   1265: aload #10
    //   1267: invokevirtual toString : ()Ljava/lang/String;
    //   1270: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   1273: pop
    //   1274: aload_1
    //   1275: invokevirtual performStart : ()V
    //   1278: aload_0
    //   1279: aload_1
    //   1280: iconst_0
    //   1281: invokevirtual dispatchOnFragmentStarted : (Landroid/support/v4/app/Fragment;Z)V
    //   1284: iload #6
    //   1286: istore_3
    //   1287: iload_3
    //   1288: istore #6
    //   1290: iload_3
    //   1291: iconst_3
    //   1292: if_icmple -> 1976
    //   1295: getstatic android/support/v4/app/FragmentManagerImpl.DEBUG : Z
    //   1298: ifeq -> 1337
    //   1301: new java/lang/StringBuilder
    //   1304: dup
    //   1305: invokespecial <init> : ()V
    //   1308: astore #10
    //   1310: aload #10
    //   1312: ldc_w 'moveto RESUMED: '
    //   1315: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1318: pop
    //   1319: aload #10
    //   1321: aload_1
    //   1322: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1325: pop
    //   1326: ldc 'FragmentManager'
    //   1328: aload #10
    //   1330: invokevirtual toString : ()Ljava/lang/String;
    //   1333: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   1336: pop
    //   1337: aload_1
    //   1338: invokevirtual performResume : ()V
    //   1341: aload_0
    //   1342: aload_1
    //   1343: iconst_0
    //   1344: invokevirtual dispatchOnFragmentResumed : (Landroid/support/v4/app/Fragment;Z)V
    //   1347: aload_1
    //   1348: aconst_null
    //   1349: putfield mSavedFragmentState : Landroid/os/Bundle;
    //   1352: aload_1
    //   1353: aconst_null
    //   1354: putfield mSavedViewState : Landroid/util/SparseArray;
    //   1357: iload_3
    //   1358: istore #6
    //   1360: goto -> 1976
    //   1363: iload_2
    //   1364: istore #6
    //   1366: aload_1
    //   1367: getfield mState : I
    //   1370: iload_2
    //   1371: if_icmple -> 1976
    //   1374: aload_1
    //   1375: getfield mState : I
    //   1378: tableswitch default -> 1408, 1 -> 1759, 2 -> 1528, 3 -> 1471, 4 -> 1414
    //   1408: iload_2
    //   1409: istore #6
    //   1411: goto -> 1976
    //   1414: iload_2
    //   1415: iconst_4
    //   1416: if_icmpge -> 1471
    //   1419: getstatic android/support/v4/app/FragmentManagerImpl.DEBUG : Z
    //   1422: ifeq -> 1461
    //   1425: new java/lang/StringBuilder
    //   1428: dup
    //   1429: invokespecial <init> : ()V
    //   1432: astore #10
    //   1434: aload #10
    //   1436: ldc_w 'movefrom RESUMED: '
    //   1439: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1442: pop
    //   1443: aload #10
    //   1445: aload_1
    //   1446: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1449: pop
    //   1450: ldc 'FragmentManager'
    //   1452: aload #10
    //   1454: invokevirtual toString : ()Ljava/lang/String;
    //   1457: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   1460: pop
    //   1461: aload_1
    //   1462: invokevirtual performPause : ()V
    //   1465: aload_0
    //   1466: aload_1
    //   1467: iconst_0
    //   1468: invokevirtual dispatchOnFragmentPaused : (Landroid/support/v4/app/Fragment;Z)V
    //   1471: iload_2
    //   1472: iconst_3
    //   1473: if_icmpge -> 1528
    //   1476: getstatic android/support/v4/app/FragmentManagerImpl.DEBUG : Z
    //   1479: ifeq -> 1518
    //   1482: new java/lang/StringBuilder
    //   1485: dup
    //   1486: invokespecial <init> : ()V
    //   1489: astore #10
    //   1491: aload #10
    //   1493: ldc_w 'movefrom STARTED: '
    //   1496: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1499: pop
    //   1500: aload #10
    //   1502: aload_1
    //   1503: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1506: pop
    //   1507: ldc 'FragmentManager'
    //   1509: aload #10
    //   1511: invokevirtual toString : ()Ljava/lang/String;
    //   1514: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   1517: pop
    //   1518: aload_1
    //   1519: invokevirtual performStop : ()V
    //   1522: aload_0
    //   1523: aload_1
    //   1524: iconst_0
    //   1525: invokevirtual dispatchOnFragmentStopped : (Landroid/support/v4/app/Fragment;Z)V
    //   1528: iload_2
    //   1529: iconst_2
    //   1530: if_icmpge -> 1759
    //   1533: getstatic android/support/v4/app/FragmentManagerImpl.DEBUG : Z
    //   1536: ifeq -> 1575
    //   1539: new java/lang/StringBuilder
    //   1542: dup
    //   1543: invokespecial <init> : ()V
    //   1546: astore #10
    //   1548: aload #10
    //   1550: ldc_w 'movefrom ACTIVITY_CREATED: '
    //   1553: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1556: pop
    //   1557: aload #10
    //   1559: aload_1
    //   1560: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1563: pop
    //   1564: ldc 'FragmentManager'
    //   1566: aload #10
    //   1568: invokevirtual toString : ()Ljava/lang/String;
    //   1571: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   1574: pop
    //   1575: aload_1
    //   1576: getfield mView : Landroid/view/View;
    //   1579: ifnull -> 1605
    //   1582: aload_0
    //   1583: getfield mHost : Landroid/support/v4/app/FragmentHostCallback;
    //   1586: aload_1
    //   1587: invokevirtual onShouldSaveFragmentState : (Landroid/support/v4/app/Fragment;)Z
    //   1590: ifeq -> 1605
    //   1593: aload_1
    //   1594: getfield mSavedViewState : Landroid/util/SparseArray;
    //   1597: ifnonnull -> 1605
    //   1600: aload_0
    //   1601: aload_1
    //   1602: invokevirtual saveFragmentViewState : (Landroid/support/v4/app/Fragment;)V
    //   1605: aload_1
    //   1606: invokevirtual performDestroyView : ()V
    //   1609: aload_0
    //   1610: aload_1
    //   1611: iconst_0
    //   1612: invokevirtual dispatchOnFragmentViewDestroyed : (Landroid/support/v4/app/Fragment;Z)V
    //   1615: aload_1
    //   1616: getfield mView : Landroid/view/View;
    //   1619: ifnull -> 1726
    //   1622: aload_1
    //   1623: getfield mContainer : Landroid/view/ViewGroup;
    //   1626: ifnull -> 1726
    //   1629: aload_1
    //   1630: getfield mContainer : Landroid/view/ViewGroup;
    //   1633: aload_1
    //   1634: getfield mView : Landroid/view/View;
    //   1637: invokevirtual endViewTransition : (Landroid/view/View;)V
    //   1640: aload_1
    //   1641: getfield mView : Landroid/view/View;
    //   1644: invokevirtual clearAnimation : ()V
    //   1647: aload_0
    //   1648: getfield mCurState : I
    //   1651: ifle -> 1694
    //   1654: aload_0
    //   1655: getfield mDestroyed : Z
    //   1658: ifne -> 1694
    //   1661: aload_1
    //   1662: getfield mView : Landroid/view/View;
    //   1665: invokevirtual getVisibility : ()I
    //   1668: ifne -> 1694
    //   1671: aload_1
    //   1672: getfield mPostponedAlpha : F
    //   1675: fconst_0
    //   1676: fcmpl
    //   1677: iflt -> 1694
    //   1680: aload_0
    //   1681: aload_1
    //   1682: iload_3
    //   1683: iconst_0
    //   1684: iload #4
    //   1686: invokevirtual loadAnimation : (Landroid/support/v4/app/Fragment;IZI)Landroid/support/v4/app/FragmentManagerImpl$AnimationOrAnimator;
    //   1689: astore #10
    //   1691: goto -> 1697
    //   1694: aconst_null
    //   1695: astore #10
    //   1697: aload_1
    //   1698: fconst_0
    //   1699: putfield mPostponedAlpha : F
    //   1702: aload #10
    //   1704: ifnull -> 1715
    //   1707: aload_0
    //   1708: aload_1
    //   1709: aload #10
    //   1711: iload_2
    //   1712: invokespecial animateRemoveFragment : (Landroid/support/v4/app/Fragment;Landroid/support/v4/app/FragmentManagerImpl$AnimationOrAnimator;I)V
    //   1715: aload_1
    //   1716: getfield mContainer : Landroid/view/ViewGroup;
    //   1719: aload_1
    //   1720: getfield mView : Landroid/view/View;
    //   1723: invokevirtual removeView : (Landroid/view/View;)V
    //   1726: aload_1
    //   1727: aconst_null
    //   1728: putfield mContainer : Landroid/view/ViewGroup;
    //   1731: aload_1
    //   1732: aconst_null
    //   1733: putfield mView : Landroid/view/View;
    //   1736: aload_1
    //   1737: aconst_null
    //   1738: putfield mViewLifecycleOwner : Landroid/arch/lifecycle/e;
    //   1741: aload_1
    //   1742: getfield mViewLifecycleOwnerLiveData : Landroid/arch/lifecycle/j;
    //   1745: aconst_null
    //   1746: invokevirtual setValue : (Ljava/lang/Object;)V
    //   1749: aload_1
    //   1750: aconst_null
    //   1751: putfield mInnerView : Landroid/view/View;
    //   1754: aload_1
    //   1755: iconst_0
    //   1756: putfield mInLayout : Z
    //   1759: iload_2
    //   1760: istore #6
    //   1762: iload_2
    //   1763: iconst_1
    //   1764: if_icmpge -> 1976
    //   1767: aload_0
    //   1768: getfield mDestroyed : Z
    //   1771: ifeq -> 1823
    //   1774: aload_1
    //   1775: invokevirtual getAnimatingAway : ()Landroid/view/View;
    //   1778: ifnull -> 1800
    //   1781: aload_1
    //   1782: invokevirtual getAnimatingAway : ()Landroid/view/View;
    //   1785: astore #10
    //   1787: aload_1
    //   1788: aconst_null
    //   1789: invokevirtual setAnimatingAway : (Landroid/view/View;)V
    //   1792: aload #10
    //   1794: invokevirtual clearAnimation : ()V
    //   1797: goto -> 1823
    //   1800: aload_1
    //   1801: invokevirtual getAnimator : ()Landroid/animation/Animator;
    //   1804: ifnull -> 1823
    //   1807: aload_1
    //   1808: invokevirtual getAnimator : ()Landroid/animation/Animator;
    //   1811: astore #10
    //   1813: aload_1
    //   1814: aconst_null
    //   1815: invokevirtual setAnimator : (Landroid/animation/Animator;)V
    //   1818: aload #10
    //   1820: invokevirtual cancel : ()V
    //   1823: aload_1
    //   1824: invokevirtual getAnimatingAway : ()Landroid/view/View;
    //   1827: ifnonnull -> 1964
    //   1830: aload_1
    //   1831: invokevirtual getAnimator : ()Landroid/animation/Animator;
    //   1834: ifnull -> 1840
    //   1837: goto -> 1964
    //   1840: getstatic android/support/v4/app/FragmentManagerImpl.DEBUG : Z
    //   1843: ifeq -> 1882
    //   1846: new java/lang/StringBuilder
    //   1849: dup
    //   1850: invokespecial <init> : ()V
    //   1853: astore #10
    //   1855: aload #10
    //   1857: ldc_w 'movefrom CREATED: '
    //   1860: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1863: pop
    //   1864: aload #10
    //   1866: aload_1
    //   1867: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1870: pop
    //   1871: ldc 'FragmentManager'
    //   1873: aload #10
    //   1875: invokevirtual toString : ()Ljava/lang/String;
    //   1878: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   1881: pop
    //   1882: aload_1
    //   1883: getfield mRetaining : Z
    //   1886: ifne -> 1902
    //   1889: aload_1
    //   1890: invokevirtual performDestroy : ()V
    //   1893: aload_0
    //   1894: aload_1
    //   1895: iconst_0
    //   1896: invokevirtual dispatchOnFragmentDestroyed : (Landroid/support/v4/app/Fragment;Z)V
    //   1899: goto -> 1907
    //   1902: aload_1
    //   1903: iconst_0
    //   1904: putfield mState : I
    //   1907: aload_1
    //   1908: invokevirtual performDetach : ()V
    //   1911: aload_0
    //   1912: aload_1
    //   1913: iconst_0
    //   1914: invokevirtual dispatchOnFragmentDetached : (Landroid/support/v4/app/Fragment;Z)V
    //   1917: iload_2
    //   1918: istore #6
    //   1920: iload #5
    //   1922: ifne -> 1976
    //   1925: aload_1
    //   1926: getfield mRetaining : Z
    //   1929: ifne -> 1943
    //   1932: aload_0
    //   1933: aload_1
    //   1934: invokevirtual makeInactive : (Landroid/support/v4/app/Fragment;)V
    //   1937: iload_2
    //   1938: istore #6
    //   1940: goto -> 1976
    //   1943: aload_1
    //   1944: aconst_null
    //   1945: putfield mHost : Landroid/support/v4/app/FragmentHostCallback;
    //   1948: aload_1
    //   1949: aconst_null
    //   1950: putfield mParentFragment : Landroid/support/v4/app/Fragment;
    //   1953: aload_1
    //   1954: aconst_null
    //   1955: putfield mFragmentManager : Landroid/support/v4/app/FragmentManagerImpl;
    //   1958: iload_2
    //   1959: istore #6
    //   1961: goto -> 1976
    //   1964: aload_1
    //   1965: iload_2
    //   1966: invokevirtual setStateAfterAnimating : (I)V
    //   1969: iload #7
    //   1971: istore #6
    //   1973: goto -> 1976
    //   1976: aload_1
    //   1977: getfield mState : I
    //   1980: iload #6
    //   1982: if_icmpeq -> 2072
    //   1985: new java/lang/StringBuilder
    //   1988: dup
    //   1989: invokespecial <init> : ()V
    //   1992: astore #10
    //   1994: aload #10
    //   1996: ldc_w 'moveToState: Fragment state for '
    //   1999: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2002: pop
    //   2003: aload #10
    //   2005: aload_1
    //   2006: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   2009: pop
    //   2010: aload #10
    //   2012: ldc_w ' not updated inline; '
    //   2015: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2018: pop
    //   2019: aload #10
    //   2021: ldc_w 'expected state '
    //   2024: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2027: pop
    //   2028: aload #10
    //   2030: iload #6
    //   2032: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   2035: pop
    //   2036: aload #10
    //   2038: ldc_w ' found '
    //   2041: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2044: pop
    //   2045: aload #10
    //   2047: aload_1
    //   2048: getfield mState : I
    //   2051: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   2054: pop
    //   2055: ldc 'FragmentManager'
    //   2057: aload #10
    //   2059: invokevirtual toString : ()Ljava/lang/String;
    //   2062: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   2065: pop
    //   2066: aload_1
    //   2067: iload #6
    //   2069: putfield mState : I
    //   2072: return
    //   2073: astore #10
    //   2075: goto -> 942
    // Exception table:
    //   from	to	target	type
    //   926	939	2073	android/content/res/Resources$NotFoundException
  }
  
  public void noteStateNotSaved() {
    this.mSavedNonConfig = null;
    int i = 0;
    this.mStateSaved = false;
    this.mStopped = false;
    int j = this.mAdded.size();
    while (i < j) {
      Fragment fragment = this.mAdded.get(i);
      if (fragment != null)
        fragment.noteStateNotSaved(); 
      i++;
    } 
  }
  
  public View onCreateView(View paramView, String paramString, Context paramContext, AttributeSet paramAttributeSet) {
    Fragment fragment1;
    StringBuilder stringBuilder;
    if (!"fragment".equals(paramString))
      return null; 
    paramString = paramAttributeSet.getAttributeValue(null, "class");
    TypedArray typedArray = paramContext.obtainStyledAttributes(paramAttributeSet, FragmentTag.Fragment);
    int i = 0;
    String str2 = paramString;
    if (paramString == null)
      str2 = typedArray.getString(0); 
    int j = typedArray.getResourceId(1, -1);
    String str3 = typedArray.getString(2);
    typedArray.recycle();
    if (!Fragment.isSupportFragmentClass(this.mHost.getContext(), str2))
      return null; 
    if (paramView != null)
      i = paramView.getId(); 
    if (i == -1 && j == -1 && str3 == null) {
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramAttributeSet.getPositionDescription());
      stringBuilder.append(": Must specify unique android:id, android:tag, or have a parent with an id for ");
      stringBuilder.append(str2);
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
    if (j != -1) {
      Fragment fragment = findFragmentById(j);
    } else {
      paramString = null;
    } 
    String str1 = paramString;
    if (paramString == null) {
      str1 = paramString;
      if (str3 != null)
        fragment1 = findFragmentByTag(str3); 
    } 
    Fragment fragment2 = fragment1;
    if (fragment1 == null) {
      fragment2 = fragment1;
      if (i != -1)
        fragment2 = findFragmentById(i); 
    } 
    if (DEBUG) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("onCreateView: id=0x");
      stringBuilder.append(Integer.toHexString(j));
      stringBuilder.append(" fname=");
      stringBuilder.append(str2);
      stringBuilder.append(" existing=");
      stringBuilder.append(fragment2);
      Log.v("FragmentManager", stringBuilder.toString());
    } 
    if (fragment2 == null) {
      int k;
      fragment1 = this.mContainer.instantiate(paramContext, str2, null);
      fragment1.mFromLayout = true;
      if (j != 0) {
        k = j;
      } else {
        k = i;
      } 
      fragment1.mFragmentId = k;
      fragment1.mContainerId = i;
      fragment1.mTag = str3;
      fragment1.mInLayout = true;
      fragment1.mFragmentManager = this;
      fragment1.mHost = this.mHost;
      fragment1.onInflate(this.mHost.getContext(), paramAttributeSet, fragment1.mSavedFragmentState);
      addFragment(fragment1, true);
    } else {
      if (fragment2.mInLayout) {
        stringBuilder = new StringBuilder();
        stringBuilder.append(paramAttributeSet.getPositionDescription());
        stringBuilder.append(": Duplicate id 0x");
        stringBuilder.append(Integer.toHexString(j));
        stringBuilder.append(", tag ");
        stringBuilder.append(str3);
        stringBuilder.append(", or parent id 0x");
        stringBuilder.append(Integer.toHexString(i));
        stringBuilder.append(" with another fragment for ");
        stringBuilder.append(str2);
        throw new IllegalArgumentException(stringBuilder.toString());
      } 
      fragment2.mInLayout = true;
      fragment2.mHost = this.mHost;
      if (!fragment2.mRetaining)
        fragment2.onInflate(this.mHost.getContext(), paramAttributeSet, fragment2.mSavedFragmentState); 
      fragment1 = fragment2;
    } 
    if (this.mCurState < 1 && fragment1.mFromLayout) {
      moveToState(fragment1, 1, 0, 0, false);
    } else {
      moveToState(fragment1);
    } 
    if (fragment1.mView == null) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("Fragment ");
      stringBuilder.append(str2);
      stringBuilder.append(" did not create a view.");
      throw new IllegalStateException(stringBuilder.toString());
    } 
    if (j != 0)
      ((Fragment)stringBuilder).mView.setId(j); 
    if (((Fragment)stringBuilder).mView.getTag() == null)
      ((Fragment)stringBuilder).mView.setTag(str3); 
    return ((Fragment)stringBuilder).mView;
  }
  
  public View onCreateView(String paramString, Context paramContext, AttributeSet paramAttributeSet) {
    return onCreateView(null, paramString, paramContext, paramAttributeSet);
  }
  
  public void performPendingDeferredStart(Fragment paramFragment) {
    if (paramFragment.mDeferStart) {
      if (this.mExecutingActions) {
        this.mHavePendingDeferredStart = true;
        return;
      } 
      paramFragment.mDeferStart = false;
      moveToState(paramFragment, this.mCurState, 0, 0, false);
    } 
  }
  
  public void popBackStack() {
    enqueueAction(new PopBackStackState(null, -1, 0), false);
  }
  
  public void popBackStack(int paramInt1, int paramInt2) {
    if (paramInt1 < 0) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Bad id: ");
      stringBuilder.append(paramInt1);
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
    enqueueAction(new PopBackStackState(null, paramInt1, paramInt2), false);
  }
  
  public void popBackStack(@Nullable String paramString, int paramInt) {
    enqueueAction(new PopBackStackState(paramString, -1, paramInt), false);
  }
  
  public boolean popBackStackImmediate() {
    checkStateLoss();
    return popBackStackImmediate(null, -1, 0);
  }
  
  public boolean popBackStackImmediate(int paramInt1, int paramInt2) {
    checkStateLoss();
    execPendingActions();
    if (paramInt1 < 0) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Bad id: ");
      stringBuilder.append(paramInt1);
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
    return popBackStackImmediate(null, paramInt1, paramInt2);
  }
  
  public boolean popBackStackImmediate(@Nullable String paramString, int paramInt) {
    checkStateLoss();
    return popBackStackImmediate(paramString, -1, paramInt);
  }
  
  boolean popBackStackState(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1, String paramString, int paramInt1, int paramInt2) {
    byte b;
    if (this.mBackStack == null)
      return false; 
    if (paramString == null && paramInt1 < 0 && (paramInt2 & 0x1) == 0) {
      paramInt1 = this.mBackStack.size() - 1;
      if (paramInt1 < 0)
        return false; 
      paramArrayList.add(this.mBackStack.remove(paramInt1));
      paramArrayList1.add(Boolean.valueOf(true));
      return true;
    } 
    if (paramString != null || paramInt1 >= 0) {
      int i;
      for (i = this.mBackStack.size() - 1; i >= 0; i--) {
        BackStackRecord backStackRecord = this.mBackStack.get(i);
        if ((paramString != null && paramString.equals(backStackRecord.getName())) || (paramInt1 >= 0 && paramInt1 == backStackRecord.mIndex))
          break; 
      } 
      if (i < 0)
        return false; 
      b = i;
      if ((paramInt2 & 0x1) != 0)
        for (paramInt2 = i - 1;; paramInt2--) {
          b = paramInt2;
          if (paramInt2 >= 0) {
            BackStackRecord backStackRecord = this.mBackStack.get(paramInt2);
            if (paramString == null || !paramString.equals(backStackRecord.getName())) {
              b = paramInt2;
              if (paramInt1 >= 0) {
                b = paramInt2;
                if (paramInt1 == backStackRecord.mIndex)
                  continue; 
              } 
              break;
            } 
            continue;
          } 
          break;
        }  
    } else {
      b = -1;
    } 
    if (b == this.mBackStack.size() - 1)
      return false; 
    for (paramInt1 = this.mBackStack.size() - 1; paramInt1 > b; paramInt1--) {
      paramArrayList.add(this.mBackStack.remove(paramInt1));
      paramArrayList1.add(Boolean.valueOf(true));
    } 
    return true;
  }
  
  public void putFragment(Bundle paramBundle, String paramString, Fragment paramFragment) {
    if (paramFragment.mIndex < 0) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Fragment ");
      stringBuilder.append(paramFragment);
      stringBuilder.append(" is not currently in the FragmentManager");
      throwException(new IllegalStateException(stringBuilder.toString()));
    } 
    paramBundle.putInt(paramString, paramFragment.mIndex);
  }
  
  public void registerFragmentLifecycleCallbacks(FragmentManager.FragmentLifecycleCallbacks paramFragmentLifecycleCallbacks, boolean paramBoolean) {
    this.mLifecycleCallbacks.add(new FragmentLifecycleCallbacksHolder(paramFragmentLifecycleCallbacks, paramBoolean));
  }
  
  public void removeFragment(Fragment paramFragment) {
    if (DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("remove: ");
      stringBuilder.append(paramFragment);
      stringBuilder.append(" nesting=");
      stringBuilder.append(paramFragment.mBackStackNesting);
      Log.v("FragmentManager", stringBuilder.toString());
    } 
    boolean bool = paramFragment.isInBackStack();
    if (!paramFragment.mDetached || (bool ^ true) != 0)
      synchronized (this.mAdded) {
        this.mAdded.remove(paramFragment);
        if (paramFragment.mHasMenu && paramFragment.mMenuVisible)
          this.mNeedMenuInvalidate = true; 
        paramFragment.mAdded = false;
        paramFragment.mRemoving = true;
        return;
      }  
  }
  
  public void removeOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener paramOnBackStackChangedListener) {
    if (this.mBackStackChangeListeners != null)
      this.mBackStackChangeListeners.remove(paramOnBackStackChangedListener); 
  }
  
  void reportBackStackChanged() {
    if (this.mBackStackChangeListeners != null)
      for (int i = 0; i < this.mBackStackChangeListeners.size(); i++)
        ((FragmentManager.OnBackStackChangedListener)this.mBackStackChangeListeners.get(i)).onBackStackChanged();  
  }
  
  void restoreAllState(Parcelable<FragmentManagerNonConfig> paramParcelable, FragmentManagerNonConfig paramFragmentManagerNonConfig) {
    Parcelable<FragmentManagerNonConfig> parcelable;
    if (paramParcelable == null)
      return; 
    FragmentManagerState fragmentManagerState = (FragmentManagerState)paramParcelable;
    if (fragmentManagerState.mActive == null)
      return; 
    if (paramFragmentManagerNonConfig != null) {
      byte b;
      List<Fragment> list2 = paramFragmentManagerNonConfig.getFragments();
      List<FragmentManagerNonConfig> list = paramFragmentManagerNonConfig.getChildNonConfigs();
      List<p> list1 = paramFragmentManagerNonConfig.getViewModelStores();
      if (list2 != null) {
        b = list2.size();
      } else {
        b = 0;
      } 
      int j = 0;
      while (true) {
        List<FragmentManagerNonConfig> list4 = list;
        List<p> list3 = list1;
        if (j < b) {
          Fragment fragment = list2.get(j);
          if (DEBUG) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("restoreAllState: re-attaching retained ");
            stringBuilder.append(fragment);
            Log.v("FragmentManager", stringBuilder.toString());
          } 
          int k;
          for (k = 0; k < fragmentManagerState.mActive.length && (fragmentManagerState.mActive[k]).mIndex != fragment.mIndex; k++);
          if (k == fragmentManagerState.mActive.length) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Could not find active fragment with index ");
            stringBuilder.append(fragment.mIndex);
            throwException(new IllegalStateException(stringBuilder.toString()));
          } 
          parcelable = fragmentManagerState.mActive[k];
          ((FragmentState)parcelable).mInstance = fragment;
          fragment.mSavedViewState = null;
          fragment.mBackStackNesting = 0;
          fragment.mInLayout = false;
          fragment.mAdded = false;
          fragment.mTarget = null;
          if (((FragmentState)parcelable).mSavedFragmentState != null) {
            ((FragmentState)parcelable).mSavedFragmentState.setClassLoader(this.mHost.getContext().getClassLoader());
            fragment.mSavedViewState = ((FragmentState)parcelable).mSavedFragmentState.getSparseParcelableArray("android:view_state");
            fragment.mSavedFragmentState = ((FragmentState)parcelable).mSavedFragmentState;
          } 
          j++;
          continue;
        } 
        break;
      } 
    } else {
      parcelable = null;
      paramParcelable = parcelable;
    } 
    this.mActive = new SparseArray(fragmentManagerState.mActive.length);
    int i;
    for (i = 0; i < fragmentManagerState.mActive.length; i++) {
      FragmentState fragmentState = fragmentManagerState.mActive[i];
      if (fragmentState != null) {
        p p;
        if (parcelable != null && i < parcelable.size()) {
          fragment = (Fragment)parcelable.get(i);
        } else {
          fragment = null;
        } 
        if (paramParcelable != null && i < paramParcelable.size()) {
          p = (p)paramParcelable.get(i);
        } else {
          p = null;
        } 
        Fragment fragment = fragmentState.instantiate(this.mHost, this.mContainer, this.mParent, (FragmentManagerNonConfig)fragment, p);
        if (DEBUG) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("restoreAllState: active #");
          stringBuilder.append(i);
          stringBuilder.append(": ");
          stringBuilder.append(fragment);
          Log.v("FragmentManager", stringBuilder.toString());
        } 
        this.mActive.put(fragment.mIndex, fragment);
        fragmentState.mInstance = null;
      } 
    } 
    if (paramFragmentManagerNonConfig != null) {
      List<Fragment> list = paramFragmentManagerNonConfig.getFragments();
      if (list != null) {
        i = list.size();
      } else {
        i = 0;
      } 
      int j;
      for (j = 0; j < i; j++) {
        Fragment fragment = list.get(j);
        if (fragment.mTargetIndex >= 0) {
          fragment.mTarget = (Fragment)this.mActive.get(fragment.mTargetIndex);
          if (fragment.mTarget == null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Re-attaching retained fragment ");
            stringBuilder.append(fragment);
            stringBuilder.append(" target no longer exists: ");
            stringBuilder.append(fragment.mTargetIndex);
            Log.w("FragmentManager", stringBuilder.toString());
          } 
        } 
      } 
    } 
    this.mAdded.clear();
    if (fragmentManagerState.mAdded != null) {
      i = 0;
      while (i < fragmentManagerState.mAdded.length) {
        null = (Fragment)this.mActive.get(fragmentManagerState.mAdded[i]);
        if (null == null) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("No instantiated fragment for index #");
          stringBuilder.append(fragmentManagerState.mAdded[i]);
          throwException(new IllegalStateException(stringBuilder.toString()));
        } 
        null.mAdded = true;
        if (DEBUG) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("restoreAllState: added #");
          stringBuilder.append(i);
          stringBuilder.append(": ");
          stringBuilder.append(null);
          Log.v("FragmentManager", stringBuilder.toString());
        } 
        if (this.mAdded.contains(null))
          throw new IllegalStateException("Already added!"); 
        synchronized (this.mAdded) {
          this.mAdded.add(null);
          i++;
        } 
      } 
    } 
    if (fragmentManagerState.mBackStack != null) {
      this.mBackStack = new ArrayList<BackStackRecord>(fragmentManagerState.mBackStack.length);
      for (i = 0; i < fragmentManagerState.mBackStack.length; i++) {
        BackStackRecord backStackRecord = fragmentManagerState.mBackStack[i].instantiate(this);
        if (DEBUG) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("restoreAllState: back stack #");
          stringBuilder.append(i);
          stringBuilder.append(" (index ");
          stringBuilder.append(backStackRecord.mIndex);
          stringBuilder.append("): ");
          stringBuilder.append(backStackRecord);
          Log.v("FragmentManager", stringBuilder.toString());
          PrintWriter printWriter = new PrintWriter((Writer)new LogWriter("FragmentManager"));
          backStackRecord.dump("  ", printWriter, false);
          printWriter.close();
        } 
        this.mBackStack.add(backStackRecord);
        if (backStackRecord.mIndex >= 0)
          setBackStackIndex(backStackRecord.mIndex, backStackRecord); 
      } 
    } else {
      this.mBackStack = null;
    } 
    if (fragmentManagerState.mPrimaryNavActiveIndex >= 0)
      this.mPrimaryNav = (Fragment)this.mActive.get(fragmentManagerState.mPrimaryNavActiveIndex); 
    this.mNextFragmentIndex = fragmentManagerState.mNextFragmentIndex;
  }
  
  FragmentManagerNonConfig retainNonConfig() {
    setRetaining(this.mSavedNonConfig);
    return this.mSavedNonConfig;
  }
  
  Parcelable saveAllState() {
    forcePostponedTransactions();
    endAnimatingAwayFragments();
    execPendingActions();
    this.mStateSaved = true;
    BackStackState[] arrayOfBackStackState = null;
    this.mSavedNonConfig = null;
    if (this.mActive != null) {
      int[] arrayOfInt;
      StringBuilder stringBuilder;
      if (this.mActive.size() <= 0)
        return null; 
      int k = this.mActive.size();
      FragmentState[] arrayOfFragmentState = new FragmentState[k];
      boolean bool = false;
      int i = 0;
      int j = 0;
      while (i < k) {
        arrayOfInt = (int[])this.mActive.valueAt(i);
        if (arrayOfInt != null) {
          if (((Fragment)arrayOfInt).mIndex < 0) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Failure saving state: active ");
            stringBuilder.append(arrayOfInt);
            stringBuilder.append(" has cleared index: ");
            stringBuilder.append(((Fragment)arrayOfInt).mIndex);
            throwException(new IllegalStateException(stringBuilder.toString()));
          } 
          FragmentState fragmentState = new FragmentState((Fragment)arrayOfInt);
          arrayOfFragmentState[i] = fragmentState;
          if (((Fragment)arrayOfInt).mState > 0 && fragmentState.mSavedFragmentState == null) {
            fragmentState.mSavedFragmentState = saveFragmentBasicState((Fragment)arrayOfInt);
            if (((Fragment)arrayOfInt).mTarget != null) {
              if (((Fragment)arrayOfInt).mTarget.mIndex < 0) {
                StringBuilder stringBuilder1 = new StringBuilder();
                stringBuilder1.append("Failure saving state: ");
                stringBuilder1.append(arrayOfInt);
                stringBuilder1.append(" has target not in fragment manager: ");
                stringBuilder1.append(((Fragment)arrayOfInt).mTarget);
                throwException(new IllegalStateException(stringBuilder1.toString()));
              } 
              if (fragmentState.mSavedFragmentState == null)
                fragmentState.mSavedFragmentState = new Bundle(); 
              putFragment(fragmentState.mSavedFragmentState, "android:target_state", ((Fragment)arrayOfInt).mTarget);
              if (((Fragment)arrayOfInt).mTargetRequestCode != 0)
                fragmentState.mSavedFragmentState.putInt("android:target_req_state", ((Fragment)arrayOfInt).mTargetRequestCode); 
            } 
          } else {
            fragmentState.mSavedFragmentState = ((Fragment)arrayOfInt).mSavedFragmentState;
          } 
          if (DEBUG) {
            StringBuilder stringBuilder1 = new StringBuilder();
            stringBuilder1.append("Saved state of ");
            stringBuilder1.append(arrayOfInt);
            stringBuilder1.append(": ");
            stringBuilder1.append(fragmentState.mSavedFragmentState);
            Log.v("FragmentManager", stringBuilder1.toString());
          } 
          j = 1;
        } 
        i++;
      } 
      if (!j) {
        if (DEBUG)
          Log.v("FragmentManager", "saveAllState: no fragments!"); 
        return null;
      } 
      j = this.mAdded.size();
      if (j > 0) {
        int[] arrayOfInt1 = new int[j];
        i = 0;
        while (true) {
          arrayOfInt = arrayOfInt1;
          if (i < j) {
            arrayOfInt1[i] = ((Fragment)this.mAdded.get(i)).mIndex;
            if (arrayOfInt1[i] < 0) {
              StringBuilder stringBuilder1 = new StringBuilder();
              stringBuilder1.append("Failure saving state: active ");
              stringBuilder1.append(this.mAdded.get(i));
              stringBuilder1.append(" has cleared index: ");
              stringBuilder1.append(arrayOfInt1[i]);
              throwException(new IllegalStateException(stringBuilder1.toString()));
            } 
            if (DEBUG) {
              StringBuilder stringBuilder1 = new StringBuilder();
              stringBuilder1.append("saveAllState: adding fragment #");
              stringBuilder1.append(i);
              stringBuilder1.append(": ");
              stringBuilder1.append(this.mAdded.get(i));
              Log.v("FragmentManager", stringBuilder1.toString());
            } 
            i++;
            continue;
          } 
          break;
        } 
      } else {
        arrayOfInt = null;
      } 
      BackStackState[] arrayOfBackStackState1 = arrayOfBackStackState;
      if (this.mBackStack != null) {
        j = this.mBackStack.size();
        arrayOfBackStackState1 = arrayOfBackStackState;
        if (j > 0) {
          arrayOfBackStackState = new BackStackState[j];
          i = bool;
          while (true) {
            arrayOfBackStackState1 = arrayOfBackStackState;
            if (i < j) {
              arrayOfBackStackState[i] = new BackStackState(this.mBackStack.get(i));
              if (DEBUG) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("saveAllState: adding back stack #");
                stringBuilder.append(i);
                stringBuilder.append(": ");
                stringBuilder.append(this.mBackStack.get(i));
                Log.v("FragmentManager", stringBuilder.toString());
              } 
              i++;
              continue;
            } 
            break;
          } 
        } 
      } 
      FragmentManagerState fragmentManagerState = new FragmentManagerState();
      fragmentManagerState.mActive = arrayOfFragmentState;
      fragmentManagerState.mAdded = arrayOfInt;
      fragmentManagerState.mBackStack = (BackStackState[])stringBuilder;
      if (this.mPrimaryNav != null)
        fragmentManagerState.mPrimaryNavActiveIndex = this.mPrimaryNav.mIndex; 
      fragmentManagerState.mNextFragmentIndex = this.mNextFragmentIndex;
      saveNonConfig();
      return fragmentManagerState;
    } 
    return null;
  }
  
  Bundle saveFragmentBasicState(Fragment paramFragment) {
    if (this.mStateBundle == null)
      this.mStateBundle = new Bundle(); 
    paramFragment.performSaveInstanceState(this.mStateBundle);
    dispatchOnFragmentSaveInstanceState(paramFragment, this.mStateBundle, false);
    if (!this.mStateBundle.isEmpty()) {
      bundle2 = this.mStateBundle;
      this.mStateBundle = null;
    } else {
      bundle2 = null;
    } 
    if (paramFragment.mView != null)
      saveFragmentViewState(paramFragment); 
    Bundle bundle1 = bundle2;
    if (paramFragment.mSavedViewState != null) {
      bundle1 = bundle2;
      if (bundle2 == null)
        bundle1 = new Bundle(); 
      bundle1.putSparseParcelableArray("android:view_state", paramFragment.mSavedViewState);
    } 
    Bundle bundle2 = bundle1;
    if (!paramFragment.mUserVisibleHint) {
      bundle2 = bundle1;
      if (bundle1 == null)
        bundle2 = new Bundle(); 
      bundle2.putBoolean("android:user_visible_hint", paramFragment.mUserVisibleHint);
    } 
    return bundle2;
  }
  
  @Nullable
  public Fragment.SavedState saveFragmentInstanceState(Fragment paramFragment) {
    if (paramFragment.mIndex < 0) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Fragment ");
      stringBuilder.append(paramFragment);
      stringBuilder.append(" is not currently in the FragmentManager");
      throwException(new IllegalStateException(stringBuilder.toString()));
    } 
    int i = paramFragment.mState;
    Fragment fragment = null;
    if (i > 0) {
      Fragment.SavedState savedState;
      Bundle bundle = saveFragmentBasicState(paramFragment);
      paramFragment = fragment;
      if (bundle != null)
        savedState = new Fragment.SavedState(bundle); 
      return savedState;
    } 
    return null;
  }
  
  void saveFragmentViewState(Fragment paramFragment) {
    if (paramFragment.mInnerView == null)
      return; 
    if (this.mStateArray == null) {
      this.mStateArray = new SparseArray();
    } else {
      this.mStateArray.clear();
    } 
    paramFragment.mInnerView.saveHierarchyState(this.mStateArray);
    if (this.mStateArray.size() > 0) {
      paramFragment.mSavedViewState = this.mStateArray;
      this.mStateArray = null;
    } 
  }
  
  void saveNonConfig() {
    List<FragmentManagerNonConfig> list1;
    List<FragmentManagerNonConfig> list2;
    List<FragmentManagerNonConfig> list3;
    if (this.mActive != null) {
      ArrayList<Fragment> arrayList1 = null;
      ArrayList<Fragment> arrayList3 = arrayList1;
      ArrayList<Fragment> arrayList2 = arrayList3;
      int i = 0;
      while (true) {
        list3 = (List)arrayList1;
        list2 = (List)arrayList3;
        list1 = (List)arrayList2;
        if (i < this.mActive.size()) {
          ArrayList<Fragment> arrayList4;
          Fragment fragment = (Fragment)this.mActive.valueAt(i);
          list2 = (List)arrayList1;
          list3 = (List)arrayList3;
          ArrayList<Fragment> arrayList5 = arrayList2;
          if (fragment != null) {
            FragmentManagerNonConfig fragmentManagerNonConfig;
            list1 = (List)arrayList1;
            if (fragment.mRetainInstance) {
              byte b;
              list2 = (List)arrayList1;
              if (arrayList1 == null)
                list2 = new ArrayList(); 
              list2.add(fragment);
              if (fragment.mTarget != null) {
                b = fragment.mTarget.mIndex;
              } else {
                b = -1;
              } 
              fragment.mTargetIndex = b;
              list1 = list2;
              if (DEBUG) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("retainNonConfig: keeping retained ");
                stringBuilder.append(fragment);
                Log.v("FragmentManager", stringBuilder.toString());
                list1 = list2;
              } 
            } 
            if (fragment.mChildFragmentManager != null) {
              fragment.mChildFragmentManager.saveNonConfig();
              fragmentManagerNonConfig = fragment.mChildFragmentManager.mSavedNonConfig;
            } else {
              fragmentManagerNonConfig = fragment.mChildNonConfig;
            } 
            arrayList1 = arrayList3;
            if (arrayList3 == null) {
              arrayList1 = arrayList3;
              if (fragmentManagerNonConfig != null) {
                arrayList3 = new ArrayList<Fragment>(this.mActive.size());
                int j = 0;
                while (true) {
                  arrayList1 = arrayList3;
                  if (j < i) {
                    arrayList3.add(null);
                    j++;
                    continue;
                  } 
                  break;
                } 
              } 
            } 
            if (arrayList1 != null)
              arrayList1.add(fragmentManagerNonConfig); 
            arrayList3 = arrayList2;
            if (arrayList2 == null) {
              arrayList3 = arrayList2;
              if (fragment.mViewModelStore != null) {
                arrayList2 = new ArrayList<Fragment>(this.mActive.size());
                int j = 0;
                while (true) {
                  arrayList3 = arrayList2;
                  if (j < i) {
                    arrayList2.add(null);
                    j++;
                    continue;
                  } 
                  break;
                } 
              } 
            } 
            arrayList4 = (ArrayList)list1;
            list3 = (List)arrayList1;
            arrayList5 = arrayList3;
            if (arrayList3 != null) {
              arrayList3.add(fragment.mViewModelStore);
              arrayList5 = arrayList3;
              list3 = (List)arrayList1;
              arrayList4 = (ArrayList)list1;
            } 
          } 
          i++;
          arrayList1 = arrayList4;
          arrayList3 = (ArrayList)list3;
          arrayList2 = arrayList5;
          continue;
        } 
        break;
      } 
    } else {
      list3 = null;
      List<FragmentManagerNonConfig> list = list3;
      list1 = list;
      list2 = list;
    } 
    if (list3 == null && list2 == null && list1 == null) {
      this.mSavedNonConfig = null;
      return;
    } 
    this.mSavedNonConfig = new FragmentManagerNonConfig((List)list3, list2, (List)list1);
  }
  
  void scheduleCommit() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mPostponedTransactions : Ljava/util/ArrayList;
    //   6: astore #4
    //   8: iconst_0
    //   9: istore_3
    //   10: aload #4
    //   12: ifnull -> 96
    //   15: aload_0
    //   16: getfield mPostponedTransactions : Ljava/util/ArrayList;
    //   19: invokevirtual isEmpty : ()Z
    //   22: ifne -> 96
    //   25: iconst_1
    //   26: istore_1
    //   27: goto -> 30
    //   30: iload_3
    //   31: istore_2
    //   32: aload_0
    //   33: getfield mPendingActions : Ljava/util/ArrayList;
    //   36: ifnull -> 101
    //   39: iload_3
    //   40: istore_2
    //   41: aload_0
    //   42: getfield mPendingActions : Ljava/util/ArrayList;
    //   45: invokevirtual size : ()I
    //   48: iconst_1
    //   49: if_icmpne -> 101
    //   52: iconst_1
    //   53: istore_2
    //   54: goto -> 101
    //   57: aload_0
    //   58: getfield mHost : Landroid/support/v4/app/FragmentHostCallback;
    //   61: invokevirtual getHandler : ()Landroid/os/Handler;
    //   64: aload_0
    //   65: getfield mExecCommit : Ljava/lang/Runnable;
    //   68: invokevirtual removeCallbacks : (Ljava/lang/Runnable;)V
    //   71: aload_0
    //   72: getfield mHost : Landroid/support/v4/app/FragmentHostCallback;
    //   75: invokevirtual getHandler : ()Landroid/os/Handler;
    //   78: aload_0
    //   79: getfield mExecCommit : Ljava/lang/Runnable;
    //   82: invokevirtual post : (Ljava/lang/Runnable;)Z
    //   85: pop
    //   86: aload_0
    //   87: monitorexit
    //   88: return
    //   89: astore #4
    //   91: aload_0
    //   92: monitorexit
    //   93: aload #4
    //   95: athrow
    //   96: iconst_0
    //   97: istore_1
    //   98: goto -> 30
    //   101: iload_1
    //   102: ifne -> 57
    //   105: iload_2
    //   106: ifeq -> 86
    //   109: goto -> 57
    // Exception table:
    //   from	to	target	type
    //   2	8	89	finally
    //   15	25	89	finally
    //   32	39	89	finally
    //   41	52	89	finally
    //   57	86	89	finally
    //   86	88	89	finally
    //   91	93	89	finally
  }
  
  public void setBackStackIndex(int paramInt, BackStackRecord paramBackStackRecord) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   6: ifnonnull -> 20
    //   9: aload_0
    //   10: new java/util/ArrayList
    //   13: dup
    //   14: invokespecial <init> : ()V
    //   17: putfield mBackStackIndices : Ljava/util/ArrayList;
    //   20: aload_0
    //   21: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   24: invokevirtual size : ()I
    //   27: istore #4
    //   29: iload #4
    //   31: istore_3
    //   32: iload_1
    //   33: iload #4
    //   35: if_icmpge -> 109
    //   38: getstatic android/support/v4/app/FragmentManagerImpl.DEBUG : Z
    //   41: ifeq -> 96
    //   44: new java/lang/StringBuilder
    //   47: dup
    //   48: invokespecial <init> : ()V
    //   51: astore #5
    //   53: aload #5
    //   55: ldc_w 'Setting back stack index '
    //   58: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   61: pop
    //   62: aload #5
    //   64: iload_1
    //   65: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   68: pop
    //   69: aload #5
    //   71: ldc_w ' to '
    //   74: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   77: pop
    //   78: aload #5
    //   80: aload_2
    //   81: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   84: pop
    //   85: ldc 'FragmentManager'
    //   87: aload #5
    //   89: invokevirtual toString : ()Ljava/lang/String;
    //   92: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   95: pop
    //   96: aload_0
    //   97: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   100: iload_1
    //   101: aload_2
    //   102: invokevirtual set : (ILjava/lang/Object;)Ljava/lang/Object;
    //   105: pop
    //   106: goto -> 269
    //   109: iload_3
    //   110: iload_1
    //   111: if_icmpge -> 202
    //   114: aload_0
    //   115: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   118: aconst_null
    //   119: invokevirtual add : (Ljava/lang/Object;)Z
    //   122: pop
    //   123: aload_0
    //   124: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   127: ifnonnull -> 141
    //   130: aload_0
    //   131: new java/util/ArrayList
    //   134: dup
    //   135: invokespecial <init> : ()V
    //   138: putfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   141: getstatic android/support/v4/app/FragmentManagerImpl.DEBUG : Z
    //   144: ifeq -> 183
    //   147: new java/lang/StringBuilder
    //   150: dup
    //   151: invokespecial <init> : ()V
    //   154: astore #5
    //   156: aload #5
    //   158: ldc_w 'Adding available back stack index '
    //   161: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   164: pop
    //   165: aload #5
    //   167: iload_3
    //   168: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   171: pop
    //   172: ldc 'FragmentManager'
    //   174: aload #5
    //   176: invokevirtual toString : ()Ljava/lang/String;
    //   179: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   182: pop
    //   183: aload_0
    //   184: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   187: iload_3
    //   188: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   191: invokevirtual add : (Ljava/lang/Object;)Z
    //   194: pop
    //   195: iload_3
    //   196: iconst_1
    //   197: iadd
    //   198: istore_3
    //   199: goto -> 109
    //   202: getstatic android/support/v4/app/FragmentManagerImpl.DEBUG : Z
    //   205: ifeq -> 260
    //   208: new java/lang/StringBuilder
    //   211: dup
    //   212: invokespecial <init> : ()V
    //   215: astore #5
    //   217: aload #5
    //   219: ldc_w 'Adding back stack index '
    //   222: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   225: pop
    //   226: aload #5
    //   228: iload_1
    //   229: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   232: pop
    //   233: aload #5
    //   235: ldc_w ' with '
    //   238: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   241: pop
    //   242: aload #5
    //   244: aload_2
    //   245: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   248: pop
    //   249: ldc 'FragmentManager'
    //   251: aload #5
    //   253: invokevirtual toString : ()Ljava/lang/String;
    //   256: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   259: pop
    //   260: aload_0
    //   261: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   264: aload_2
    //   265: invokevirtual add : (Ljava/lang/Object;)Z
    //   268: pop
    //   269: aload_0
    //   270: monitorexit
    //   271: return
    //   272: astore_2
    //   273: aload_0
    //   274: monitorexit
    //   275: aload_2
    //   276: athrow
    // Exception table:
    //   from	to	target	type
    //   2	20	272	finally
    //   20	29	272	finally
    //   38	96	272	finally
    //   96	106	272	finally
    //   114	141	272	finally
    //   141	183	272	finally
    //   183	195	272	finally
    //   202	260	272	finally
    //   260	269	272	finally
    //   269	271	272	finally
    //   273	275	272	finally
  }
  
  public void setPrimaryNavigationFragment(Fragment paramFragment) {
    if (paramFragment != null && (this.mActive.get(paramFragment.mIndex) != paramFragment || (paramFragment.mHost != null && paramFragment.getFragmentManager() != this))) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Fragment ");
      stringBuilder.append(paramFragment);
      stringBuilder.append(" is not an active fragment of FragmentManager ");
      stringBuilder.append(this);
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
    this.mPrimaryNav = paramFragment;
  }
  
  public void showFragment(Fragment paramFragment) {
    if (DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("show: ");
      stringBuilder.append(paramFragment);
      Log.v("FragmentManager", stringBuilder.toString());
    } 
    if (paramFragment.mHidden) {
      paramFragment.mHidden = false;
      paramFragment.mHiddenChanged ^= 0x1;
    } 
  }
  
  void startPendingDeferredFragments() {
    if (this.mActive == null)
      return; 
    for (int i = 0; i < this.mActive.size(); i++) {
      Fragment fragment = (Fragment)this.mActive.valueAt(i);
      if (fragment != null)
        performPendingDeferredStart(fragment); 
    } 
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder(128);
    stringBuilder.append("FragmentManager{");
    stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    stringBuilder.append(" in ");
    if (this.mParent != null) {
      DebugUtils.buildShortClassTag(this.mParent, stringBuilder);
    } else {
      DebugUtils.buildShortClassTag(this.mHost, stringBuilder);
    } 
    stringBuilder.append("}}");
    return stringBuilder.toString();
  }
  
  public void unregisterFragmentLifecycleCallbacks(FragmentManager.FragmentLifecycleCallbacks paramFragmentLifecycleCallbacks) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mLifecycleCallbacks : Ljava/util/concurrent/CopyOnWriteArrayList;
    //   4: astore #4
    //   6: aload #4
    //   8: monitorenter
    //   9: iconst_0
    //   10: istore_2
    //   11: aload_0
    //   12: getfield mLifecycleCallbacks : Ljava/util/concurrent/CopyOnWriteArrayList;
    //   15: invokevirtual size : ()I
    //   18: istore_3
    //   19: iload_2
    //   20: iload_3
    //   21: if_icmpge -> 54
    //   24: aload_0
    //   25: getfield mLifecycleCallbacks : Ljava/util/concurrent/CopyOnWriteArrayList;
    //   28: iload_2
    //   29: invokevirtual get : (I)Ljava/lang/Object;
    //   32: checkcast android/support/v4/app/FragmentManagerImpl$FragmentLifecycleCallbacksHolder
    //   35: getfield mCallback : Landroid/support/v4/app/FragmentManager$FragmentLifecycleCallbacks;
    //   38: aload_1
    //   39: if_acmpne -> 64
    //   42: aload_0
    //   43: getfield mLifecycleCallbacks : Ljava/util/concurrent/CopyOnWriteArrayList;
    //   46: iload_2
    //   47: invokevirtual remove : (I)Ljava/lang/Object;
    //   50: pop
    //   51: goto -> 54
    //   54: aload #4
    //   56: monitorexit
    //   57: return
    //   58: astore_1
    //   59: aload #4
    //   61: monitorexit
    //   62: aload_1
    //   63: athrow
    //   64: iload_2
    //   65: iconst_1
    //   66: iadd
    //   67: istore_2
    //   68: goto -> 19
    // Exception table:
    //   from	to	target	type
    //   11	19	58	finally
    //   24	51	58	finally
    //   54	57	58	finally
    //   59	62	58	finally
  }
  
  private static class AnimateOnHWLayerIfNeededListener extends AnimationListenerWrapper {
    View mView;
    
    AnimateOnHWLayerIfNeededListener(View param1View, Animation.AnimationListener param1AnimationListener) {
      super(param1AnimationListener);
      this.mView = param1View;
    }
    
    @CallSuper
    public void onAnimationEnd(Animation param1Animation) {
      if (ViewCompat.isAttachedToWindow(this.mView) || Build.VERSION.SDK_INT >= 24) {
        this.mView.post(new Runnable() {
              public void run() {
                FragmentManagerImpl.AnimateOnHWLayerIfNeededListener.this.mView.setLayerType(0, null);
              }
            });
      } else {
        this.mView.setLayerType(0, null);
      } 
      super.onAnimationEnd(param1Animation);
    }
  }
  
  class null implements Runnable {
    public void run() {
      this.this$0.mView.setLayerType(0, null);
    }
  }
  
  private static class AnimationListenerWrapper implements Animation.AnimationListener {
    private final Animation.AnimationListener mWrapped;
    
    AnimationListenerWrapper(Animation.AnimationListener param1AnimationListener) {
      this.mWrapped = param1AnimationListener;
    }
    
    @CallSuper
    public void onAnimationEnd(Animation param1Animation) {
      if (this.mWrapped != null)
        this.mWrapped.onAnimationEnd(param1Animation); 
    }
    
    @CallSuper
    public void onAnimationRepeat(Animation param1Animation) {
      if (this.mWrapped != null)
        this.mWrapped.onAnimationRepeat(param1Animation); 
    }
    
    @CallSuper
    public void onAnimationStart(Animation param1Animation) {
      if (this.mWrapped != null)
        this.mWrapped.onAnimationStart(param1Animation); 
    }
  }
  
  private static class AnimationOrAnimator {
    public final Animation animation = null;
    
    public final Animator animator;
    
    AnimationOrAnimator(Animator param1Animator) {
      this.animator = param1Animator;
      if (param1Animator == null)
        throw new IllegalStateException("Animator cannot be null"); 
    }
    
    AnimationOrAnimator(Animation param1Animation) {
      this.animator = null;
      if (param1Animation == null)
        throw new IllegalStateException("Animation cannot be null"); 
    }
  }
  
  private static class AnimatorOnHWLayerIfNeededListener extends AnimatorListenerAdapter {
    View mView;
    
    AnimatorOnHWLayerIfNeededListener(View param1View) {
      this.mView = param1View;
    }
    
    public void onAnimationEnd(Animator param1Animator) {
      this.mView.setLayerType(0, null);
      param1Animator.removeListener((Animator.AnimatorListener)this);
    }
    
    public void onAnimationStart(Animator param1Animator) {
      this.mView.setLayerType(2, null);
    }
  }
  
  private static class EndViewTransitionAnimator extends AnimationSet implements Runnable {
    private boolean mAnimating = true;
    
    private final View mChild;
    
    private boolean mEnded;
    
    private final ViewGroup mParent;
    
    private boolean mTransitionEnded;
    
    EndViewTransitionAnimator(@NonNull Animation param1Animation, @NonNull ViewGroup param1ViewGroup, @NonNull View param1View) {
      super(false);
      this.mParent = param1ViewGroup;
      this.mChild = param1View;
      addAnimation(param1Animation);
      this.mParent.post(this);
    }
    
    public boolean getTransformation(long param1Long, Transformation param1Transformation) {
      this.mAnimating = true;
      if (this.mEnded)
        return this.mTransitionEnded ^ true; 
      if (!super.getTransformation(param1Long, param1Transformation)) {
        this.mEnded = true;
        OneShotPreDrawListener.add((View)this.mParent, this);
      } 
      return true;
    }
    
    public boolean getTransformation(long param1Long, Transformation param1Transformation, float param1Float) {
      this.mAnimating = true;
      if (this.mEnded)
        return this.mTransitionEnded ^ true; 
      if (!super.getTransformation(param1Long, param1Transformation, param1Float)) {
        this.mEnded = true;
        OneShotPreDrawListener.add((View)this.mParent, this);
      } 
      return true;
    }
    
    public void run() {
      if (!this.mEnded && this.mAnimating) {
        this.mAnimating = false;
        this.mParent.post(this);
        return;
      } 
      this.mParent.endViewTransition(this.mChild);
      this.mTransitionEnded = true;
    }
  }
  
  private static final class FragmentLifecycleCallbacksHolder {
    final FragmentManager.FragmentLifecycleCallbacks mCallback;
    
    final boolean mRecursive;
    
    FragmentLifecycleCallbacksHolder(FragmentManager.FragmentLifecycleCallbacks param1FragmentLifecycleCallbacks, boolean param1Boolean) {
      this.mCallback = param1FragmentLifecycleCallbacks;
      this.mRecursive = param1Boolean;
    }
  }
  
  static class FragmentTag {
    public static final int[] Fragment = new int[] { 16842755, 16842960, 16842961 };
    
    public static final int Fragment_id = 1;
    
    public static final int Fragment_name = 0;
    
    public static final int Fragment_tag = 2;
  }
  
  static interface OpGenerator {
    boolean generateOps(ArrayList<BackStackRecord> param1ArrayList, ArrayList<Boolean> param1ArrayList1);
  }
  
  private class PopBackStackState implements OpGenerator {
    final int mFlags;
    
    final int mId;
    
    final String mName;
    
    PopBackStackState(String param1String, int param1Int1, int param1Int2) {
      this.mName = param1String;
      this.mId = param1Int1;
      this.mFlags = param1Int2;
    }
    
    public boolean generateOps(ArrayList<BackStackRecord> param1ArrayList, ArrayList<Boolean> param1ArrayList1) {
      if (FragmentManagerImpl.this.mPrimaryNav != null && this.mId < 0 && this.mName == null) {
        FragmentManager fragmentManager = FragmentManagerImpl.this.mPrimaryNav.peekChildFragmentManager();
        if (fragmentManager != null && fragmentManager.popBackStackImmediate())
          return false; 
      } 
      return FragmentManagerImpl.this.popBackStackState(param1ArrayList, param1ArrayList1, this.mName, this.mId, this.mFlags);
    }
  }
  
  static class StartEnterTransitionListener implements Fragment.OnStartEnterTransitionListener {
    final boolean mIsBack;
    
    private int mNumPostponed;
    
    final BackStackRecord mRecord;
    
    StartEnterTransitionListener(BackStackRecord param1BackStackRecord, boolean param1Boolean) {
      this.mIsBack = param1Boolean;
      this.mRecord = param1BackStackRecord;
    }
    
    public void cancelTransaction() {
      this.mRecord.mManager.completeExecute(this.mRecord, this.mIsBack, false, false);
    }
    
    public void completeTransaction() {
      int i = this.mNumPostponed;
      int j = 0;
      if (i > 0) {
        i = 1;
      } else {
        i = 0;
      } 
      FragmentManagerImpl fragmentManagerImpl = this.mRecord.mManager;
      int k = fragmentManagerImpl.mAdded.size();
      while (j < k) {
        Fragment fragment = fragmentManagerImpl.mAdded.get(j);
        fragment.setOnStartEnterTransitionListener(null);
        if (i != 0 && fragment.isPostponed())
          fragment.startPostponedEnterTransition(); 
        j++;
      } 
      this.mRecord.mManager.completeExecute(this.mRecord, this.mIsBack, i ^ 0x1, true);
    }
    
    public boolean isReady() {
      return (this.mNumPostponed == 0);
    }
    
    public void onStartEnterTransition() {
      this.mNumPostponed--;
      if (this.mNumPostponed != 0)
        return; 
      this.mRecord.mManager.scheduleCommit();
    }
    
    public void startListening() {
      this.mNumPostponed++;
    }
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\android\support\v4\app\FragmentManagerImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */