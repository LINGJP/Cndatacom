package android.support.constraint.solver.widgets;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConstraintWidgetGroup {
  public List<ConstraintWidget> mConstrainedGroup;
  
  public final int[] mGroupDimensions = new int[] { this.mGroupWidth, this.mGroupHeight };
  
  int mGroupHeight = -1;
  
  int mGroupWidth = -1;
  
  public boolean mSkipSolver = false;
  
  List<ConstraintWidget> mStartHorizontalWidgets = new ArrayList<ConstraintWidget>();
  
  List<ConstraintWidget> mStartVerticalWidgets = new ArrayList<ConstraintWidget>();
  
  List<ConstraintWidget> mUnresolvedWidgets = new ArrayList<ConstraintWidget>();
  
  HashSet<ConstraintWidget> mWidgetsToSetHorizontal = new HashSet<ConstraintWidget>();
  
  HashSet<ConstraintWidget> mWidgetsToSetVertical = new HashSet<ConstraintWidget>();
  
  List<ConstraintWidget> mWidgetsToSolve = new ArrayList<ConstraintWidget>();
  
  ConstraintWidgetGroup(List<ConstraintWidget> paramList) {
    this.mConstrainedGroup = paramList;
  }
  
  ConstraintWidgetGroup(List<ConstraintWidget> paramList, boolean paramBoolean) {
    this.mConstrainedGroup = paramList;
    this.mSkipSolver = paramBoolean;
  }
  
  private void getWidgetsToSolveTraversal(ArrayList<ConstraintWidget> paramArrayList, ConstraintWidget paramConstraintWidget) {
    if (paramConstraintWidget.mGroupsToSolver)
      return; 
    paramArrayList.add(paramConstraintWidget);
    paramConstraintWidget.mGroupsToSolver = true;
    if (paramConstraintWidget.isFullyResolved())
      return; 
    boolean bool = paramConstraintWidget instanceof Helper;
    byte b = 0;
    if (bool) {
      Helper helper = (Helper)paramConstraintWidget;
      int m = helper.mWidgetsCount;
      for (int k = 0; k < m; k++)
        getWidgetsToSolveTraversal(paramArrayList, helper.mWidgets[k]); 
    } 
    int j = paramConstraintWidget.mListAnchors.length;
    for (int i = b; i < j; i++) {
      ConstraintAnchor constraintAnchor = (paramConstraintWidget.mListAnchors[i]).mTarget;
      if (constraintAnchor != null) {
        ConstraintWidget constraintWidget = constraintAnchor.mOwner;
        if (constraintAnchor != null && constraintWidget != paramConstraintWidget.getParent())
          getWidgetsToSolveTraversal(paramArrayList, constraintWidget); 
      } 
    } 
  }
  
  private void updateResolvedDimension(ConstraintWidget paramConstraintWidget) {
    // Byte code:
    //   0: aload_1
    //   1: getfield mOptimizerMeasurable : Z
    //   4: ifeq -> 439
    //   7: aload_1
    //   8: invokevirtual isFullyResolved : ()Z
    //   11: ifeq -> 15
    //   14: return
    //   15: aload_1
    //   16: getfield mRight : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   19: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   22: astore #5
    //   24: iconst_0
    //   25: istore #4
    //   27: aload #5
    //   29: ifnull -> 37
    //   32: iconst_1
    //   33: istore_3
    //   34: goto -> 39
    //   37: iconst_0
    //   38: istore_3
    //   39: iload_3
    //   40: ifeq -> 55
    //   43: aload_1
    //   44: getfield mRight : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   47: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   50: astore #5
    //   52: goto -> 64
    //   55: aload_1
    //   56: getfield mLeft : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   59: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   62: astore #5
    //   64: aload #5
    //   66: ifnull -> 146
    //   69: aload #5
    //   71: getfield mOwner : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   74: getfield mOptimizerMeasured : Z
    //   77: ifne -> 89
    //   80: aload_0
    //   81: aload #5
    //   83: getfield mOwner : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   86: invokespecial updateResolvedDimension : (Landroid/support/constraint/solver/widgets/ConstraintWidget;)V
    //   89: aload #5
    //   91: getfield mType : Landroid/support/constraint/solver/widgets/ConstraintAnchor$Type;
    //   94: getstatic android/support/constraint/solver/widgets/ConstraintAnchor$Type.RIGHT : Landroid/support/constraint/solver/widgets/ConstraintAnchor$Type;
    //   97: if_acmpne -> 123
    //   100: aload #5
    //   102: getfield mOwner : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   105: getfield mX : I
    //   108: istore_2
    //   109: aload #5
    //   111: getfield mOwner : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   114: invokevirtual getWidth : ()I
    //   117: iload_2
    //   118: iadd
    //   119: istore_2
    //   120: goto -> 148
    //   123: aload #5
    //   125: getfield mType : Landroid/support/constraint/solver/widgets/ConstraintAnchor$Type;
    //   128: getstatic android/support/constraint/solver/widgets/ConstraintAnchor$Type.LEFT : Landroid/support/constraint/solver/widgets/ConstraintAnchor$Type;
    //   131: if_acmpne -> 146
    //   134: aload #5
    //   136: getfield mOwner : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   139: getfield mX : I
    //   142: istore_2
    //   143: goto -> 148
    //   146: iconst_0
    //   147: istore_2
    //   148: iload_3
    //   149: ifeq -> 165
    //   152: iload_2
    //   153: aload_1
    //   154: getfield mRight : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   157: invokevirtual getMargin : ()I
    //   160: isub
    //   161: istore_2
    //   162: goto -> 180
    //   165: iload_2
    //   166: aload_1
    //   167: getfield mLeft : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   170: invokevirtual getMargin : ()I
    //   173: aload_1
    //   174: invokevirtual getWidth : ()I
    //   177: iadd
    //   178: iadd
    //   179: istore_2
    //   180: aload_1
    //   181: iload_2
    //   182: aload_1
    //   183: invokevirtual getWidth : ()I
    //   186: isub
    //   187: iload_2
    //   188: invokevirtual setHorizontalDimension : (II)V
    //   191: aload_1
    //   192: getfield mBaseline : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   195: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   198: ifnull -> 270
    //   201: aload_1
    //   202: getfield mBaseline : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   205: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   208: astore #5
    //   210: aload #5
    //   212: getfield mOwner : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   215: getfield mOptimizerMeasured : Z
    //   218: ifne -> 230
    //   221: aload_0
    //   222: aload #5
    //   224: getfield mOwner : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   227: invokespecial updateResolvedDimension : (Landroid/support/constraint/solver/widgets/ConstraintWidget;)V
    //   230: aload #5
    //   232: getfield mOwner : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   235: getfield mY : I
    //   238: aload #5
    //   240: getfield mOwner : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   243: getfield mBaselineDistance : I
    //   246: iadd
    //   247: aload_1
    //   248: getfield mBaselineDistance : I
    //   251: isub
    //   252: istore_2
    //   253: aload_1
    //   254: iload_2
    //   255: aload_1
    //   256: getfield mHeight : I
    //   259: iload_2
    //   260: iadd
    //   261: invokevirtual setVerticalDimension : (II)V
    //   264: aload_1
    //   265: iconst_1
    //   266: putfield mOptimizerMeasured : Z
    //   269: return
    //   270: aload_1
    //   271: getfield mBottom : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   274: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   277: ifnull -> 283
    //   280: iconst_1
    //   281: istore #4
    //   283: iload #4
    //   285: ifeq -> 300
    //   288: aload_1
    //   289: getfield mBottom : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   292: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   295: astore #5
    //   297: goto -> 309
    //   300: aload_1
    //   301: getfield mTop : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   304: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   307: astore #5
    //   309: iload_2
    //   310: istore_3
    //   311: aload #5
    //   313: ifnull -> 390
    //   316: aload #5
    //   318: getfield mOwner : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   321: getfield mOptimizerMeasured : Z
    //   324: ifne -> 336
    //   327: aload_0
    //   328: aload #5
    //   330: getfield mOwner : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   333: invokespecial updateResolvedDimension : (Landroid/support/constraint/solver/widgets/ConstraintWidget;)V
    //   336: aload #5
    //   338: getfield mType : Landroid/support/constraint/solver/widgets/ConstraintAnchor$Type;
    //   341: getstatic android/support/constraint/solver/widgets/ConstraintAnchor$Type.BOTTOM : Landroid/support/constraint/solver/widgets/ConstraintAnchor$Type;
    //   344: if_acmpne -> 368
    //   347: aload #5
    //   349: getfield mOwner : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   352: getfield mY : I
    //   355: aload #5
    //   357: getfield mOwner : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   360: invokevirtual getHeight : ()I
    //   363: iadd
    //   364: istore_3
    //   365: goto -> 390
    //   368: iload_2
    //   369: istore_3
    //   370: aload #5
    //   372: getfield mType : Landroid/support/constraint/solver/widgets/ConstraintAnchor$Type;
    //   375: getstatic android/support/constraint/solver/widgets/ConstraintAnchor$Type.TOP : Landroid/support/constraint/solver/widgets/ConstraintAnchor$Type;
    //   378: if_acmpne -> 390
    //   381: aload #5
    //   383: getfield mOwner : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   386: getfield mY : I
    //   389: istore_3
    //   390: iload #4
    //   392: ifeq -> 408
    //   395: iload_3
    //   396: aload_1
    //   397: getfield mBottom : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   400: invokevirtual getMargin : ()I
    //   403: isub
    //   404: istore_2
    //   405: goto -> 423
    //   408: iload_3
    //   409: aload_1
    //   410: getfield mTop : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   413: invokevirtual getMargin : ()I
    //   416: aload_1
    //   417: invokevirtual getHeight : ()I
    //   420: iadd
    //   421: iadd
    //   422: istore_2
    //   423: aload_1
    //   424: iload_2
    //   425: aload_1
    //   426: invokevirtual getHeight : ()I
    //   429: isub
    //   430: iload_2
    //   431: invokevirtual setVerticalDimension : (II)V
    //   434: aload_1
    //   435: iconst_1
    //   436: putfield mOptimizerMeasured : Z
    //   439: return
  }
  
  void addWidgetsToSet(ConstraintWidget paramConstraintWidget, int paramInt) {
    if (paramInt == 0) {
      this.mWidgetsToSetHorizontal.add(paramConstraintWidget);
      return;
    } 
    if (paramInt == 1)
      this.mWidgetsToSetVertical.add(paramConstraintWidget); 
  }
  
  public List<ConstraintWidget> getStartWidgets(int paramInt) {
    return (paramInt == 0) ? this.mStartHorizontalWidgets : ((paramInt == 1) ? this.mStartVerticalWidgets : null);
  }
  
  Set<ConstraintWidget> getWidgetsToSet(int paramInt) {
    return (paramInt == 0) ? this.mWidgetsToSetHorizontal : ((paramInt == 1) ? this.mWidgetsToSetVertical : null);
  }
  
  List<ConstraintWidget> getWidgetsToSolve() {
    if (!this.mWidgetsToSolve.isEmpty())
      return this.mWidgetsToSolve; 
    int j = this.mConstrainedGroup.size();
    for (int i = 0; i < j; i++) {
      ConstraintWidget constraintWidget = this.mConstrainedGroup.get(i);
      if (!constraintWidget.mOptimizerMeasurable)
        getWidgetsToSolveTraversal((ArrayList<ConstraintWidget>)this.mWidgetsToSolve, constraintWidget); 
    } 
    this.mUnresolvedWidgets.clear();
    this.mUnresolvedWidgets.addAll(this.mConstrainedGroup);
    this.mUnresolvedWidgets.removeAll(this.mWidgetsToSolve);
    return this.mWidgetsToSolve;
  }
  
  void updateUnresolvedWidgets() {
    int j = this.mUnresolvedWidgets.size();
    for (int i = 0; i < j; i++)
      updateResolvedDimension(this.mUnresolvedWidgets.get(i)); 
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\android\support\constraint\solver\widgets\ConstraintWidgetGroup.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */