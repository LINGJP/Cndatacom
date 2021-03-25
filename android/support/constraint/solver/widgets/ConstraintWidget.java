package android.support.constraint.solver.widgets;

import android.support.constraint.solver.Cache;
import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.SolverVariable;
import java.util.ArrayList;

public class ConstraintWidget {
  protected static final int ANCHOR_BASELINE = 4;
  
  protected static final int ANCHOR_BOTTOM = 3;
  
  protected static final int ANCHOR_LEFT = 0;
  
  protected static final int ANCHOR_RIGHT = 1;
  
  protected static final int ANCHOR_TOP = 2;
  
  private static final boolean AUTOTAG_CENTER = false;
  
  public static final int CHAIN_PACKED = 2;
  
  public static final int CHAIN_SPREAD = 0;
  
  public static final int CHAIN_SPREAD_INSIDE = 1;
  
  public static float DEFAULT_BIAS = 0.5F;
  
  static final int DIMENSION_HORIZONTAL = 0;
  
  static final int DIMENSION_VERTICAL = 1;
  
  protected static final int DIRECT = 2;
  
  public static final int GONE = 8;
  
  public static final int HORIZONTAL = 0;
  
  public static final int INVISIBLE = 4;
  
  public static final int MATCH_CONSTRAINT_PERCENT = 2;
  
  public static final int MATCH_CONSTRAINT_RATIO = 3;
  
  public static final int MATCH_CONSTRAINT_RATIO_RESOLVED = 4;
  
  public static final int MATCH_CONSTRAINT_SPREAD = 0;
  
  public static final int MATCH_CONSTRAINT_WRAP = 1;
  
  protected static final int SOLVER = 1;
  
  public static final int UNKNOWN = -1;
  
  public static final int VERTICAL = 1;
  
  public static final int VISIBLE = 0;
  
  private static final int WRAP = -2;
  
  protected ArrayList<ConstraintAnchor> mAnchors = new ArrayList<ConstraintAnchor>();
  
  ConstraintAnchor mBaseline = new ConstraintAnchor(this, ConstraintAnchor.Type.BASELINE);
  
  int mBaselineDistance = 0;
  
  ConstraintWidgetGroup mBelongingGroup = null;
  
  ConstraintAnchor mBottom = new ConstraintAnchor(this, ConstraintAnchor.Type.BOTTOM);
  
  boolean mBottomHasCentered;
  
  ConstraintAnchor mCenter = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER);
  
  ConstraintAnchor mCenterX = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_X);
  
  ConstraintAnchor mCenterY = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_Y);
  
  private float mCircleConstraintAngle = 0.0F;
  
  private Object mCompanionWidget;
  
  private int mContainerItemSkip = 0;
  
  private String mDebugName = null;
  
  protected float mDimensionRatio = 0.0F;
  
  protected int mDimensionRatioSide = -1;
  
  int mDistToBottom;
  
  int mDistToLeft;
  
  int mDistToRight;
  
  int mDistToTop;
  
  private int mDrawHeight = 0;
  
  private int mDrawWidth = 0;
  
  private int mDrawX = 0;
  
  private int mDrawY = 0;
  
  boolean mGroupsToSolver = false;
  
  int mHeight = 0;
  
  float mHorizontalBiasPercent = DEFAULT_BIAS;
  
  boolean mHorizontalChainFixedPosition;
  
  int mHorizontalChainStyle = 0;
  
  ConstraintWidget mHorizontalNextWidget = null;
  
  public int mHorizontalResolution = -1;
  
  boolean mHorizontalWrapVisited;
  
  boolean mIsHeightWrapContent;
  
  boolean mIsWidthWrapContent;
  
  ConstraintAnchor mLeft = new ConstraintAnchor(this, ConstraintAnchor.Type.LEFT);
  
  boolean mLeftHasCentered;
  
  protected ConstraintAnchor[] mListAnchors = new ConstraintAnchor[] { this.mLeft, this.mRight, this.mTop, this.mBottom, this.mBaseline, this.mCenter };
  
  protected DimensionBehaviour[] mListDimensionBehaviors = new DimensionBehaviour[] { DimensionBehaviour.FIXED, DimensionBehaviour.FIXED };
  
  protected ConstraintWidget[] mListNextMatchConstraintsWidget = new ConstraintWidget[] { null, null };
  
  int mMatchConstraintDefaultHeight = 0;
  
  int mMatchConstraintDefaultWidth = 0;
  
  int mMatchConstraintMaxHeight = 0;
  
  int mMatchConstraintMaxWidth = 0;
  
  int mMatchConstraintMinHeight = 0;
  
  int mMatchConstraintMinWidth = 0;
  
  float mMatchConstraintPercentHeight = 1.0F;
  
  float mMatchConstraintPercentWidth = 1.0F;
  
  private int[] mMaxDimension = new int[] { Integer.MAX_VALUE, Integer.MAX_VALUE };
  
  protected int mMinHeight;
  
  protected int mMinWidth;
  
  protected ConstraintWidget[] mNextChainWidget = new ConstraintWidget[] { null, null };
  
  protected int mOffsetX = 0;
  
  protected int mOffsetY = 0;
  
  boolean mOptimizerMeasurable = false;
  
  boolean mOptimizerMeasured = false;
  
  ConstraintWidget mParent = null;
  
  int mRelX = 0;
  
  int mRelY = 0;
  
  ResolutionDimension mResolutionHeight;
  
  ResolutionDimension mResolutionWidth;
  
  float mResolvedDimensionRatio = 1.0F;
  
  int mResolvedDimensionRatioSide = -1;
  
  int[] mResolvedMatchConstraintDefault = new int[2];
  
  ConstraintAnchor mRight = new ConstraintAnchor(this, ConstraintAnchor.Type.RIGHT);
  
  boolean mRightHasCentered;
  
  ConstraintAnchor mTop = new ConstraintAnchor(this, ConstraintAnchor.Type.TOP);
  
  boolean mTopHasCentered;
  
  private String mType = null;
  
  float mVerticalBiasPercent = DEFAULT_BIAS;
  
  boolean mVerticalChainFixedPosition;
  
  int mVerticalChainStyle = 0;
  
  ConstraintWidget mVerticalNextWidget = null;
  
  public int mVerticalResolution = -1;
  
  boolean mVerticalWrapVisited;
  
  private int mVisibility = 0;
  
  float[] mWeight = new float[] { -1.0F, -1.0F };
  
  int mWidth = 0;
  
  private int mWrapHeight;
  
  private int mWrapWidth;
  
  protected int mX = 0;
  
  protected int mY = 0;
  
  public ConstraintWidget() {
    addAnchors();
  }
  
  public ConstraintWidget(int paramInt1, int paramInt2) {
    this(0, 0, paramInt1, paramInt2);
  }
  
  public ConstraintWidget(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    this.mX = paramInt1;
    this.mY = paramInt2;
    this.mWidth = paramInt3;
    this.mHeight = paramInt4;
    addAnchors();
    forceUpdateDrawPosition();
  }
  
  private void addAnchors() {
    this.mAnchors.add(this.mLeft);
    this.mAnchors.add(this.mTop);
    this.mAnchors.add(this.mRight);
    this.mAnchors.add(this.mBottom);
    this.mAnchors.add(this.mCenterX);
    this.mAnchors.add(this.mCenterY);
    this.mAnchors.add(this.mCenter);
    this.mAnchors.add(this.mBaseline);
  }
  
  private void applyConstraints(LinearSystem paramLinearSystem, boolean paramBoolean1, SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, DimensionBehaviour paramDimensionBehaviour, boolean paramBoolean2, ConstraintAnchor paramConstraintAnchor1, ConstraintAnchor paramConstraintAnchor2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat1, boolean paramBoolean3, boolean paramBoolean4, int paramInt5, int paramInt6, int paramInt7, float paramFloat2, boolean paramBoolean5) {
    // Byte code:
    //   0: aload_1
    //   1: aload #7
    //   3: invokevirtual createObjectVariable : (Ljava/lang/Object;)Landroid/support/constraint/solver/SolverVariable;
    //   6: astore #29
    //   8: aload_1
    //   9: aload #8
    //   11: invokevirtual createObjectVariable : (Ljava/lang/Object;)Landroid/support/constraint/solver/SolverVariable;
    //   14: astore #28
    //   16: aload_1
    //   17: aload #7
    //   19: invokevirtual getTarget : ()Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   22: invokevirtual createObjectVariable : (Ljava/lang/Object;)Landroid/support/constraint/solver/SolverVariable;
    //   25: astore #30
    //   27: aload_1
    //   28: aload #8
    //   30: invokevirtual getTarget : ()Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   33: invokevirtual createObjectVariable : (Ljava/lang/Object;)Landroid/support/constraint/solver/SolverVariable;
    //   36: astore #31
    //   38: aload_1
    //   39: getfield graphOptimizer : Z
    //   42: ifeq -> 128
    //   45: aload #7
    //   47: invokevirtual getResolutionNode : ()Landroid/support/constraint/solver/widgets/ResolutionAnchor;
    //   50: getfield state : I
    //   53: iconst_1
    //   54: if_icmpne -> 128
    //   57: aload #8
    //   59: invokevirtual getResolutionNode : ()Landroid/support/constraint/solver/widgets/ResolutionAnchor;
    //   62: getfield state : I
    //   65: iconst_1
    //   66: if_icmpne -> 128
    //   69: invokestatic getMetrics : ()Landroid/support/constraint/solver/Metrics;
    //   72: ifnull -> 89
    //   75: invokestatic getMetrics : ()Landroid/support/constraint/solver/Metrics;
    //   78: astore_3
    //   79: aload_3
    //   80: aload_3
    //   81: getfield resolvedWidgets : J
    //   84: lconst_1
    //   85: ladd
    //   86: putfield resolvedWidgets : J
    //   89: aload #7
    //   91: invokevirtual getResolutionNode : ()Landroid/support/constraint/solver/widgets/ResolutionAnchor;
    //   94: aload_1
    //   95: invokevirtual addResolvedValue : (Landroid/support/constraint/solver/LinearSystem;)V
    //   98: aload #8
    //   100: invokevirtual getResolutionNode : ()Landroid/support/constraint/solver/widgets/ResolutionAnchor;
    //   103: aload_1
    //   104: invokevirtual addResolvedValue : (Landroid/support/constraint/solver/LinearSystem;)V
    //   107: iload #15
    //   109: ifne -> 127
    //   112: iload_2
    //   113: ifeq -> 127
    //   116: aload_1
    //   117: aload #4
    //   119: aload #28
    //   121: iconst_0
    //   122: bipush #6
    //   124: invokevirtual addGreaterThan : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;II)V
    //   127: return
    //   128: invokestatic getMetrics : ()Landroid/support/constraint/solver/Metrics;
    //   131: ifnull -> 151
    //   134: invokestatic getMetrics : ()Landroid/support/constraint/solver/Metrics;
    //   137: astore #27
    //   139: aload #27
    //   141: aload #27
    //   143: getfield nonresolvedWidgets : J
    //   146: lconst_1
    //   147: ladd
    //   148: putfield nonresolvedWidgets : J
    //   151: aload #7
    //   153: invokevirtual isConnected : ()Z
    //   156: istore #24
    //   158: aload #8
    //   160: invokevirtual isConnected : ()Z
    //   163: istore #25
    //   165: aload_0
    //   166: getfield mCenter : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   169: invokevirtual isConnected : ()Z
    //   172: istore #26
    //   174: iload #24
    //   176: ifeq -> 185
    //   179: iconst_1
    //   180: istore #22
    //   182: goto -> 188
    //   185: iconst_0
    //   186: istore #22
    //   188: iload #22
    //   190: istore #21
    //   192: iload #25
    //   194: ifeq -> 203
    //   197: iload #22
    //   199: iconst_1
    //   200: iadd
    //   201: istore #21
    //   203: iload #21
    //   205: istore #22
    //   207: iload #26
    //   209: ifeq -> 218
    //   212: iload #21
    //   214: iconst_1
    //   215: iadd
    //   216: istore #22
    //   218: iload #14
    //   220: ifeq -> 229
    //   223: iconst_3
    //   224: istore #21
    //   226: goto -> 233
    //   229: iload #16
    //   231: istore #21
    //   233: getstatic android/support/constraint/solver/widgets/ConstraintWidget$1.$SwitchMap$android$support$constraint$solver$widgets$ConstraintWidget$DimensionBehaviour : [I
    //   236: aload #5
    //   238: invokevirtual ordinal : ()I
    //   241: iaload
    //   242: tableswitch default -> 272, 1 -> 272, 2 -> 272, 3 -> 272, 4 -> 278
    //   272: iconst_0
    //   273: istore #16
    //   275: goto -> 290
    //   278: iload #21
    //   280: iconst_4
    //   281: if_icmpne -> 287
    //   284: goto -> 272
    //   287: iconst_1
    //   288: istore #16
    //   290: aload_0
    //   291: getfield mVisibility : I
    //   294: bipush #8
    //   296: if_icmpne -> 308
    //   299: iconst_0
    //   300: istore #10
    //   302: iconst_0
    //   303: istore #16
    //   305: goto -> 308
    //   308: iload #20
    //   310: ifeq -> 368
    //   313: iload #24
    //   315: ifne -> 339
    //   318: iload #25
    //   320: ifne -> 339
    //   323: iload #26
    //   325: ifne -> 339
    //   328: aload_1
    //   329: aload #29
    //   331: iload #9
    //   333: invokevirtual addEquality : (Landroid/support/constraint/solver/SolverVariable;I)V
    //   336: goto -> 368
    //   339: iload #24
    //   341: ifeq -> 368
    //   344: iload #25
    //   346: ifne -> 368
    //   349: aload_1
    //   350: aload #29
    //   352: aload #30
    //   354: aload #7
    //   356: invokevirtual getMargin : ()I
    //   359: bipush #6
    //   361: invokevirtual addEquality : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;II)Landroid/support/constraint/solver/ArrayRow;
    //   364: pop
    //   365: goto -> 368
    //   368: iload #16
    //   370: ifne -> 447
    //   373: iload #6
    //   375: ifeq -> 431
    //   378: aload_1
    //   379: aload #28
    //   381: aload #29
    //   383: iconst_0
    //   384: iconst_3
    //   385: invokevirtual addEquality : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;II)Landroid/support/constraint/solver/ArrayRow;
    //   388: pop
    //   389: iload #11
    //   391: ifle -> 409
    //   394: aload_1
    //   395: aload #28
    //   397: aload #29
    //   399: iload #11
    //   401: bipush #6
    //   403: invokevirtual addGreaterThan : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;II)V
    //   406: goto -> 409
    //   409: iload #12
    //   411: ldc 2147483647
    //   413: if_icmpge -> 444
    //   416: aload_1
    //   417: aload #28
    //   419: aload #29
    //   421: iload #12
    //   423: bipush #6
    //   425: invokevirtual addLowerThan : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;II)V
    //   428: goto -> 444
    //   431: aload_1
    //   432: aload #28
    //   434: aload #29
    //   436: iload #10
    //   438: bipush #6
    //   440: invokevirtual addEquality : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;II)Landroid/support/constraint/solver/ArrayRow;
    //   443: pop
    //   444: goto -> 832
    //   447: iload #17
    //   449: bipush #-2
    //   451: if_icmpne -> 461
    //   454: iload #10
    //   456: istore #9
    //   458: goto -> 465
    //   461: iload #17
    //   463: istore #9
    //   465: iload #18
    //   467: istore #12
    //   469: iload #18
    //   471: bipush #-2
    //   473: if_icmpne -> 480
    //   476: iload #10
    //   478: istore #12
    //   480: iload #9
    //   482: ifle -> 509
    //   485: aload_1
    //   486: aload #28
    //   488: aload #29
    //   490: iload #9
    //   492: bipush #6
    //   494: invokevirtual addGreaterThan : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;II)V
    //   497: iload #10
    //   499: iload #9
    //   501: invokestatic max : (II)I
    //   504: istore #10
    //   506: goto -> 509
    //   509: iload #10
    //   511: istore #23
    //   513: iload #12
    //   515: ifle -> 539
    //   518: aload_1
    //   519: aload #28
    //   521: aload #29
    //   523: iload #12
    //   525: bipush #6
    //   527: invokevirtual addLowerThan : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;II)V
    //   530: iload #10
    //   532: iload #12
    //   534: invokestatic min : (II)I
    //   537: istore #23
    //   539: iload #21
    //   541: iconst_1
    //   542: if_icmpne -> 600
    //   545: iload_2
    //   546: ifeq -> 565
    //   549: aload_1
    //   550: aload #28
    //   552: aload #29
    //   554: iload #23
    //   556: bipush #6
    //   558: invokevirtual addEquality : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;II)Landroid/support/constraint/solver/ArrayRow;
    //   561: pop
    //   562: goto -> 725
    //   565: iload #15
    //   567: ifeq -> 585
    //   570: aload_1
    //   571: aload #28
    //   573: aload #29
    //   575: iload #23
    //   577: iconst_4
    //   578: invokevirtual addEquality : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;II)Landroid/support/constraint/solver/ArrayRow;
    //   581: pop
    //   582: goto -> 725
    //   585: aload_1
    //   586: aload #28
    //   588: aload #29
    //   590: iload #23
    //   592: iconst_1
    //   593: invokevirtual addEquality : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;II)Landroid/support/constraint/solver/ArrayRow;
    //   596: pop
    //   597: goto -> 725
    //   600: iload #21
    //   602: iconst_2
    //   603: if_icmpne -> 725
    //   606: aload #7
    //   608: invokevirtual getType : ()Landroid/support/constraint/solver/widgets/ConstraintAnchor$Type;
    //   611: getstatic android/support/constraint/solver/widgets/ConstraintAnchor$Type.TOP : Landroid/support/constraint/solver/widgets/ConstraintAnchor$Type;
    //   614: if_acmpeq -> 666
    //   617: aload #7
    //   619: invokevirtual getType : ()Landroid/support/constraint/solver/widgets/ConstraintAnchor$Type;
    //   622: getstatic android/support/constraint/solver/widgets/ConstraintAnchor$Type.BOTTOM : Landroid/support/constraint/solver/widgets/ConstraintAnchor$Type;
    //   625: if_acmpne -> 631
    //   628: goto -> 666
    //   631: aload_1
    //   632: aload_0
    //   633: getfield mParent : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   636: getstatic android/support/constraint/solver/widgets/ConstraintAnchor$Type.LEFT : Landroid/support/constraint/solver/widgets/ConstraintAnchor$Type;
    //   639: invokevirtual getAnchor : (Landroid/support/constraint/solver/widgets/ConstraintAnchor$Type;)Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   642: invokevirtual createObjectVariable : (Ljava/lang/Object;)Landroid/support/constraint/solver/SolverVariable;
    //   645: astore #5
    //   647: aload_1
    //   648: aload_0
    //   649: getfield mParent : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   652: getstatic android/support/constraint/solver/widgets/ConstraintAnchor$Type.RIGHT : Landroid/support/constraint/solver/widgets/ConstraintAnchor$Type;
    //   655: invokevirtual getAnchor : (Landroid/support/constraint/solver/widgets/ConstraintAnchor$Type;)Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   658: invokevirtual createObjectVariable : (Ljava/lang/Object;)Landroid/support/constraint/solver/SolverVariable;
    //   661: astore #27
    //   663: goto -> 698
    //   666: aload_1
    //   667: aload_0
    //   668: getfield mParent : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   671: getstatic android/support/constraint/solver/widgets/ConstraintAnchor$Type.TOP : Landroid/support/constraint/solver/widgets/ConstraintAnchor$Type;
    //   674: invokevirtual getAnchor : (Landroid/support/constraint/solver/widgets/ConstraintAnchor$Type;)Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   677: invokevirtual createObjectVariable : (Ljava/lang/Object;)Landroid/support/constraint/solver/SolverVariable;
    //   680: astore #5
    //   682: aload_1
    //   683: aload_0
    //   684: getfield mParent : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   687: getstatic android/support/constraint/solver/widgets/ConstraintAnchor$Type.BOTTOM : Landroid/support/constraint/solver/widgets/ConstraintAnchor$Type;
    //   690: invokevirtual getAnchor : (Landroid/support/constraint/solver/widgets/ConstraintAnchor$Type;)Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   693: invokevirtual createObjectVariable : (Ljava/lang/Object;)Landroid/support/constraint/solver/SolverVariable;
    //   696: astore #27
    //   698: aload_1
    //   699: aload_1
    //   700: invokevirtual createRow : ()Landroid/support/constraint/solver/ArrayRow;
    //   703: aload #28
    //   705: aload #29
    //   707: aload #27
    //   709: aload #5
    //   711: fload #19
    //   713: invokevirtual createRowDimensionRatio : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;F)Landroid/support/constraint/solver/ArrayRow;
    //   716: invokevirtual addConstraint : (Landroid/support/constraint/solver/ArrayRow;)V
    //   719: iconst_0
    //   720: istore #10
    //   722: goto -> 729
    //   725: iload #16
    //   727: istore #10
    //   729: iload #9
    //   731: istore #17
    //   733: iload #12
    //   735: istore #18
    //   737: iload #10
    //   739: istore #16
    //   741: iload #10
    //   743: ifeq -> 832
    //   746: iload #9
    //   748: istore #17
    //   750: iload #12
    //   752: istore #18
    //   754: iload #10
    //   756: istore #16
    //   758: iload #22
    //   760: iconst_2
    //   761: if_icmpeq -> 832
    //   764: iload #9
    //   766: istore #17
    //   768: iload #12
    //   770: istore #18
    //   772: iload #10
    //   774: istore #16
    //   776: iload #14
    //   778: ifne -> 832
    //   781: iload #9
    //   783: iload #23
    //   785: invokestatic max : (II)I
    //   788: istore #16
    //   790: iload #16
    //   792: istore #10
    //   794: iload #12
    //   796: ifle -> 808
    //   799: iload #12
    //   801: iload #16
    //   803: invokestatic min : (II)I
    //   806: istore #10
    //   808: aload_1
    //   809: aload #28
    //   811: aload #29
    //   813: iload #10
    //   815: bipush #6
    //   817: invokevirtual addEquality : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;II)Landroid/support/constraint/solver/ArrayRow;
    //   820: pop
    //   821: iconst_0
    //   822: istore #16
    //   824: iload #12
    //   826: istore #18
    //   828: iload #9
    //   830: istore #17
    //   832: aload #30
    //   834: astore #27
    //   836: iload #20
    //   838: ifeq -> 1478
    //   841: iload #15
    //   843: ifeq -> 849
    //   846: goto -> 1478
    //   849: iload #24
    //   851: ifne -> 885
    //   854: iload #25
    //   856: ifne -> 885
    //   859: iload #26
    //   861: ifne -> 885
    //   864: iload_2
    //   865: ifeq -> 878
    //   868: aload_1
    //   869: aload #4
    //   871: aload #28
    //   873: iconst_0
    //   874: iconst_5
    //   875: invokevirtual addGreaterThan : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;II)V
    //   878: aload #28
    //   880: astore #5
    //   882: goto -> 1462
    //   885: iload #24
    //   887: ifeq -> 912
    //   890: iload #25
    //   892: ifne -> 912
    //   895: iload_2
    //   896: ifeq -> 878
    //   899: aload_1
    //   900: aload #4
    //   902: aload #28
    //   904: iconst_0
    //   905: iconst_5
    //   906: invokevirtual addGreaterThan : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;II)V
    //   909: goto -> 878
    //   912: iload #24
    //   914: ifne -> 955
    //   917: iload #25
    //   919: ifeq -> 955
    //   922: aload_1
    //   923: aload #28
    //   925: aload #31
    //   927: aload #8
    //   929: invokevirtual getMargin : ()I
    //   932: ineg
    //   933: bipush #6
    //   935: invokevirtual addEquality : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;II)Landroid/support/constraint/solver/ArrayRow;
    //   938: pop
    //   939: iload_2
    //   940: ifeq -> 878
    //   943: aload_1
    //   944: aload #29
    //   946: aload_3
    //   947: iconst_0
    //   948: iconst_5
    //   949: invokevirtual addGreaterThan : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;II)V
    //   952: goto -> 878
    //   955: iconst_4
    //   956: istore #9
    //   958: iload #24
    //   960: ifeq -> 878
    //   963: iload #25
    //   965: ifeq -> 878
    //   968: iload #16
    //   970: ifeq -> 1208
    //   973: iload_2
    //   974: ifeq -> 996
    //   977: iload #11
    //   979: ifne -> 996
    //   982: aload_1
    //   983: aload #28
    //   985: aload #29
    //   987: iconst_0
    //   988: bipush #6
    //   990: invokevirtual addGreaterThan : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;II)V
    //   993: goto -> 996
    //   996: iload #21
    //   998: ifne -> 1104
    //   1001: iload #18
    //   1003: ifgt -> 1024
    //   1006: iload #17
    //   1008: ifle -> 1014
    //   1011: goto -> 1024
    //   1014: iconst_0
    //   1015: istore #9
    //   1017: bipush #6
    //   1019: istore #10
    //   1021: goto -> 1035
    //   1024: iconst_1
    //   1025: istore #11
    //   1027: iload #9
    //   1029: istore #10
    //   1031: iload #11
    //   1033: istore #9
    //   1035: aload_1
    //   1036: aload #29
    //   1038: aload #27
    //   1040: aload #7
    //   1042: invokevirtual getMargin : ()I
    //   1045: iload #10
    //   1047: invokevirtual addEquality : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;II)Landroid/support/constraint/solver/ArrayRow;
    //   1050: pop
    //   1051: aload_1
    //   1052: aload #28
    //   1054: aload #31
    //   1056: aload #8
    //   1058: invokevirtual getMargin : ()I
    //   1061: ineg
    //   1062: iload #10
    //   1064: invokevirtual addEquality : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;II)Landroid/support/constraint/solver/ArrayRow;
    //   1067: pop
    //   1068: iload #18
    //   1070: ifgt -> 1087
    //   1073: iload #17
    //   1075: ifle -> 1081
    //   1078: goto -> 1087
    //   1081: iconst_0
    //   1082: istore #10
    //   1084: goto -> 1090
    //   1087: iconst_1
    //   1088: istore #10
    //   1090: iconst_5
    //   1091: istore #12
    //   1093: iload #10
    //   1095: istore #11
    //   1097: iload #12
    //   1099: istore #10
    //   1101: goto -> 1217
    //   1104: iload #21
    //   1106: iconst_1
    //   1107: if_icmpne -> 1123
    //   1110: bipush #6
    //   1112: istore #10
    //   1114: iconst_1
    //   1115: istore #11
    //   1117: iconst_1
    //   1118: istore #9
    //   1120: goto -> 1217
    //   1123: iload #21
    //   1125: iconst_3
    //   1126: if_icmpne -> 1202
    //   1129: iload #9
    //   1131: istore #10
    //   1133: iload #14
    //   1135: ifne -> 1163
    //   1138: iload #9
    //   1140: istore #10
    //   1142: aload_0
    //   1143: getfield mResolvedDimensionRatioSide : I
    //   1146: iconst_m1
    //   1147: if_icmpeq -> 1163
    //   1150: iload #9
    //   1152: istore #10
    //   1154: iload #18
    //   1156: ifgt -> 1163
    //   1159: bipush #6
    //   1161: istore #10
    //   1163: aload_1
    //   1164: aload #29
    //   1166: aload #27
    //   1168: aload #7
    //   1170: invokevirtual getMargin : ()I
    //   1173: iload #10
    //   1175: invokevirtual addEquality : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;II)Landroid/support/constraint/solver/ArrayRow;
    //   1178: pop
    //   1179: aload_1
    //   1180: aload #28
    //   1182: aload #31
    //   1184: aload #8
    //   1186: invokevirtual getMargin : ()I
    //   1189: ineg
    //   1190: iload #10
    //   1192: invokevirtual addEquality : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;II)Landroid/support/constraint/solver/ArrayRow;
    //   1195: pop
    //   1196: iconst_5
    //   1197: istore #10
    //   1199: goto -> 1114
    //   1202: iconst_0
    //   1203: istore #11
    //   1205: goto -> 1211
    //   1208: iconst_1
    //   1209: istore #11
    //   1211: iconst_5
    //   1212: istore #10
    //   1214: iconst_0
    //   1215: istore #9
    //   1217: iload #11
    //   1219: ifeq -> 1340
    //   1222: aload #7
    //   1224: invokevirtual getMargin : ()I
    //   1227: istore #11
    //   1229: aload #8
    //   1231: invokevirtual getMargin : ()I
    //   1234: istore #12
    //   1236: iconst_1
    //   1237: istore #6
    //   1239: aload #27
    //   1241: astore #5
    //   1243: aload_1
    //   1244: aload #29
    //   1246: aload #27
    //   1248: iload #11
    //   1250: fload #13
    //   1252: aload #31
    //   1254: aload #28
    //   1256: iload #12
    //   1258: iload #10
    //   1260: invokevirtual addCentering : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;IFLandroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;II)V
    //   1263: aload #7
    //   1265: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1268: getfield mOwner : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   1271: instanceof android/support/constraint/solver/widgets/Barrier
    //   1274: istore #14
    //   1276: aload #8
    //   1278: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1281: getfield mOwner : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   1284: instanceof android/support/constraint/solver/widgets/Barrier
    //   1287: istore #15
    //   1289: iload #14
    //   1291: ifeq -> 1315
    //   1294: iload #15
    //   1296: ifne -> 1315
    //   1299: iload_2
    //   1300: istore #6
    //   1302: iconst_1
    //   1303: istore #14
    //   1305: iconst_5
    //   1306: istore #10
    //   1308: bipush #6
    //   1310: istore #11
    //   1312: goto -> 1356
    //   1315: aload #5
    //   1317: astore #27
    //   1319: iload #14
    //   1321: ifne -> 1340
    //   1324: aload #5
    //   1326: astore #27
    //   1328: iload #15
    //   1330: ifeq -> 1340
    //   1333: bipush #6
    //   1335: istore #10
    //   1337: goto -> 1350
    //   1340: iload_2
    //   1341: istore #6
    //   1343: iconst_5
    //   1344: istore #10
    //   1346: aload #27
    //   1348: astore #5
    //   1350: iload_2
    //   1351: istore #14
    //   1353: iconst_5
    //   1354: istore #11
    //   1356: iload #9
    //   1358: ifeq -> 1372
    //   1361: bipush #6
    //   1363: istore #11
    //   1365: bipush #6
    //   1367: istore #10
    //   1369: goto -> 1372
    //   1372: iload #16
    //   1374: ifne -> 1382
    //   1377: iload #6
    //   1379: ifne -> 1387
    //   1382: iload #9
    //   1384: ifeq -> 1402
    //   1387: aload_1
    //   1388: aload #29
    //   1390: aload #5
    //   1392: aload #7
    //   1394: invokevirtual getMargin : ()I
    //   1397: iload #10
    //   1399: invokevirtual addGreaterThan : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;II)V
    //   1402: iload #16
    //   1404: ifne -> 1412
    //   1407: iload #14
    //   1409: ifne -> 1417
    //   1412: iload #9
    //   1414: ifeq -> 1436
    //   1417: aload_1
    //   1418: aload #28
    //   1420: aload #31
    //   1422: aload #8
    //   1424: invokevirtual getMargin : ()I
    //   1427: ineg
    //   1428: iload #11
    //   1430: invokevirtual addLowerThan : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;II)V
    //   1433: goto -> 1436
    //   1436: aload #28
    //   1438: astore #7
    //   1440: aload #7
    //   1442: astore #5
    //   1444: iload_2
    //   1445: ifeq -> 882
    //   1448: aload_1
    //   1449: aload #29
    //   1451: aload_3
    //   1452: iconst_0
    //   1453: bipush #6
    //   1455: invokevirtual addGreaterThan : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;II)V
    //   1458: aload #7
    //   1460: astore #5
    //   1462: iload_2
    //   1463: ifeq -> 1477
    //   1466: aload_1
    //   1467: aload #4
    //   1469: aload #5
    //   1471: iconst_0
    //   1472: bipush #6
    //   1474: invokevirtual addGreaterThan : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;II)V
    //   1477: return
    //   1478: iload #22
    //   1480: iconst_2
    //   1481: if_icmpge -> 1509
    //   1484: iload_2
    //   1485: ifeq -> 1509
    //   1488: aload_1
    //   1489: aload #29
    //   1491: aload_3
    //   1492: iconst_0
    //   1493: bipush #6
    //   1495: invokevirtual addGreaterThan : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;II)V
    //   1498: aload_1
    //   1499: aload #4
    //   1501: aload #28
    //   1503: iconst_0
    //   1504: bipush #6
    //   1506: invokevirtual addGreaterThan : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;II)V
    //   1509: return
  }
  
  private boolean isChainHead(int paramInt) {
    paramInt *= 2;
    if ((this.mListAnchors[paramInt]).mTarget != null && (this.mListAnchors[paramInt]).mTarget.mTarget != this.mListAnchors[paramInt]) {
      ConstraintAnchor[] arrayOfConstraintAnchor = this.mListAnchors;
      if ((arrayOfConstraintAnchor[++paramInt]).mTarget != null && (this.mListAnchors[paramInt]).mTarget.mTarget == this.mListAnchors[paramInt])
        return true; 
    } 
    return false;
  }
  
  public void addToSolver(LinearSystem paramLinearSystem) {
    // Byte code:
    //   0: aload_0
    //   1: astore #21
    //   3: aload_1
    //   4: aload #21
    //   6: getfield mLeft : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   9: invokevirtual createObjectVariable : (Ljava/lang/Object;)Landroid/support/constraint/solver/SolverVariable;
    //   12: astore #23
    //   14: aload_1
    //   15: aload #21
    //   17: getfield mRight : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   20: invokevirtual createObjectVariable : (Ljava/lang/Object;)Landroid/support/constraint/solver/SolverVariable;
    //   23: astore #19
    //   25: aload_1
    //   26: aload #21
    //   28: getfield mTop : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   31: invokevirtual createObjectVariable : (Ljava/lang/Object;)Landroid/support/constraint/solver/SolverVariable;
    //   34: astore #22
    //   36: aload_1
    //   37: aload #21
    //   39: getfield mBottom : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   42: invokevirtual createObjectVariable : (Ljava/lang/Object;)Landroid/support/constraint/solver/SolverVariable;
    //   45: astore #18
    //   47: aload_1
    //   48: aload #21
    //   50: getfield mBaseline : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   53: invokevirtual createObjectVariable : (Ljava/lang/Object;)Landroid/support/constraint/solver/SolverVariable;
    //   56: astore #20
    //   58: aload #21
    //   60: getfield mParent : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   63: ifnull -> 351
    //   66: aload #21
    //   68: getfield mParent : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   71: ifnull -> 96
    //   74: aload #21
    //   76: getfield mParent : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   79: getfield mListDimensionBehaviors : [Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   82: iconst_0
    //   83: aaload
    //   84: getstatic android/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour.WRAP_CONTENT : Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   87: if_acmpne -> 96
    //   90: iconst_1
    //   91: istore #9
    //   93: goto -> 99
    //   96: iconst_0
    //   97: istore #9
    //   99: aload #21
    //   101: getfield mParent : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   104: ifnull -> 129
    //   107: aload #21
    //   109: getfield mParent : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   112: getfield mListDimensionBehaviors : [Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   115: iconst_1
    //   116: aaload
    //   117: getstatic android/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour.WRAP_CONTENT : Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   120: if_acmpne -> 129
    //   123: iconst_1
    //   124: istore #10
    //   126: goto -> 132
    //   129: iconst_0
    //   130: istore #10
    //   132: aload #21
    //   134: iconst_0
    //   135: invokespecial isChainHead : (I)Z
    //   138: ifeq -> 161
    //   141: aload #21
    //   143: getfield mParent : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   146: checkcast android/support/constraint/solver/widgets/ConstraintWidgetContainer
    //   149: aload #21
    //   151: iconst_0
    //   152: invokevirtual addChain : (Landroid/support/constraint/solver/widgets/ConstraintWidget;I)V
    //   155: iconst_1
    //   156: istore #11
    //   158: goto -> 167
    //   161: aload_0
    //   162: invokevirtual isInHorizontalChain : ()Z
    //   165: istore #11
    //   167: aload #21
    //   169: iconst_1
    //   170: invokespecial isChainHead : (I)Z
    //   173: ifeq -> 196
    //   176: aload #21
    //   178: getfield mParent : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   181: checkcast android/support/constraint/solver/widgets/ConstraintWidgetContainer
    //   184: aload #21
    //   186: iconst_1
    //   187: invokevirtual addChain : (Landroid/support/constraint/solver/widgets/ConstraintWidget;I)V
    //   190: iconst_1
    //   191: istore #12
    //   193: goto -> 202
    //   196: aload_0
    //   197: invokevirtual isInVerticalChain : ()Z
    //   200: istore #12
    //   202: iload #9
    //   204: ifeq -> 259
    //   207: aload #21
    //   209: getfield mVisibility : I
    //   212: bipush #8
    //   214: if_icmpeq -> 259
    //   217: aload #21
    //   219: getfield mLeft : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   222: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   225: ifnonnull -> 259
    //   228: aload #21
    //   230: getfield mRight : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   233: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   236: ifnonnull -> 259
    //   239: aload_1
    //   240: aload_1
    //   241: aload #21
    //   243: getfield mParent : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   246: getfield mRight : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   249: invokevirtual createObjectVariable : (Ljava/lang/Object;)Landroid/support/constraint/solver/SolverVariable;
    //   252: aload #19
    //   254: iconst_0
    //   255: iconst_1
    //   256: invokevirtual addGreaterThan : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;II)V
    //   259: iload #10
    //   261: ifeq -> 324
    //   264: aload #21
    //   266: getfield mVisibility : I
    //   269: bipush #8
    //   271: if_icmpeq -> 324
    //   274: aload #21
    //   276: getfield mTop : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   279: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   282: ifnonnull -> 324
    //   285: aload #21
    //   287: getfield mBottom : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   290: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   293: ifnonnull -> 324
    //   296: aload #21
    //   298: getfield mBaseline : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   301: ifnonnull -> 324
    //   304: aload_1
    //   305: aload_1
    //   306: aload #21
    //   308: getfield mParent : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   311: getfield mBottom : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   314: invokevirtual createObjectVariable : (Ljava/lang/Object;)Landroid/support/constraint/solver/SolverVariable;
    //   317: aload #18
    //   319: iconst_0
    //   320: iconst_1
    //   321: invokevirtual addGreaterThan : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;II)V
    //   324: iload #11
    //   326: istore #13
    //   328: iload #12
    //   330: istore #14
    //   332: iload #9
    //   334: istore #11
    //   336: iload #10
    //   338: istore #9
    //   340: iload #13
    //   342: istore #12
    //   344: iload #14
    //   346: istore #10
    //   348: goto -> 363
    //   351: iconst_0
    //   352: istore #11
    //   354: iconst_0
    //   355: istore #9
    //   357: iconst_0
    //   358: istore #12
    //   360: iconst_0
    //   361: istore #10
    //   363: aload #21
    //   365: getfield mWidth : I
    //   368: istore_3
    //   369: iload_3
    //   370: istore #4
    //   372: iload_3
    //   373: aload #21
    //   375: getfield mMinWidth : I
    //   378: if_icmpge -> 388
    //   381: aload #21
    //   383: getfield mMinWidth : I
    //   386: istore #4
    //   388: aload #21
    //   390: getfield mHeight : I
    //   393: istore #5
    //   395: iload #5
    //   397: istore_3
    //   398: iload #5
    //   400: aload #21
    //   402: getfield mMinHeight : I
    //   405: if_icmpge -> 414
    //   408: aload #21
    //   410: getfield mMinHeight : I
    //   413: istore_3
    //   414: aload #21
    //   416: getfield mListDimensionBehaviors : [Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   419: iconst_0
    //   420: aaload
    //   421: getstatic android/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT : Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   424: if_acmpeq -> 433
    //   427: iconst_1
    //   428: istore #13
    //   430: goto -> 436
    //   433: iconst_0
    //   434: istore #13
    //   436: aload #21
    //   438: getfield mListDimensionBehaviors : [Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   441: iconst_1
    //   442: aaload
    //   443: getstatic android/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT : Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   446: if_acmpeq -> 455
    //   449: iconst_1
    //   450: istore #14
    //   452: goto -> 458
    //   455: iconst_0
    //   456: istore #14
    //   458: aload #21
    //   460: aload #21
    //   462: getfield mDimensionRatioSide : I
    //   465: putfield mResolvedDimensionRatioSide : I
    //   468: aload #21
    //   470: aload #21
    //   472: getfield mDimensionRatio : F
    //   475: putfield mResolvedDimensionRatio : F
    //   478: aload #21
    //   480: getfield mMatchConstraintDefaultWidth : I
    //   483: istore #6
    //   485: aload #21
    //   487: getfield mMatchConstraintDefaultHeight : I
    //   490: istore #7
    //   492: aload #21
    //   494: getfield mDimensionRatio : F
    //   497: fstore_2
    //   498: iconst_4
    //   499: istore #8
    //   501: fload_2
    //   502: fconst_0
    //   503: fcmpl
    //   504: ifle -> 799
    //   507: aload #21
    //   509: getfield mVisibility : I
    //   512: bipush #8
    //   514: if_icmpeq -> 799
    //   517: iload #6
    //   519: istore #5
    //   521: aload #21
    //   523: getfield mListDimensionBehaviors : [Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   526: iconst_0
    //   527: aaload
    //   528: getstatic android/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT : Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   531: if_acmpne -> 546
    //   534: iload #6
    //   536: istore #5
    //   538: iload #6
    //   540: ifne -> 546
    //   543: iconst_3
    //   544: istore #5
    //   546: iload #7
    //   548: istore #6
    //   550: aload #21
    //   552: getfield mListDimensionBehaviors : [Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   555: iconst_1
    //   556: aaload
    //   557: getstatic android/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT : Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   560: if_acmpne -> 575
    //   563: iload #7
    //   565: istore #6
    //   567: iload #7
    //   569: ifne -> 575
    //   572: iconst_3
    //   573: istore #6
    //   575: aload #21
    //   577: getfield mListDimensionBehaviors : [Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   580: iconst_0
    //   581: aaload
    //   582: getstatic android/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT : Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   585: if_acmpne -> 629
    //   588: aload #21
    //   590: getfield mListDimensionBehaviors : [Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   593: iconst_1
    //   594: aaload
    //   595: getstatic android/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT : Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   598: if_acmpne -> 629
    //   601: iload #5
    //   603: iconst_3
    //   604: if_icmpne -> 629
    //   607: iload #6
    //   609: iconst_3
    //   610: if_icmpne -> 629
    //   613: aload #21
    //   615: iload #11
    //   617: iload #9
    //   619: iload #13
    //   621: iload #14
    //   623: invokevirtual setupDimensionRatio : (ZZZZ)V
    //   626: goto -> 790
    //   629: aload #21
    //   631: getfield mListDimensionBehaviors : [Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   634: iconst_0
    //   635: aaload
    //   636: getstatic android/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT : Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   639: if_acmpne -> 702
    //   642: iload #5
    //   644: iconst_3
    //   645: if_icmpne -> 702
    //   648: aload #21
    //   650: iconst_0
    //   651: putfield mResolvedDimensionRatioSide : I
    //   654: aload #21
    //   656: getfield mResolvedDimensionRatio : F
    //   659: aload #21
    //   661: getfield mHeight : I
    //   664: i2f
    //   665: fmul
    //   666: f2i
    //   667: istore #4
    //   669: aload #21
    //   671: getfield mListDimensionBehaviors : [Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   674: iconst_1
    //   675: aaload
    //   676: getstatic android/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT : Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   679: if_acmpeq -> 699
    //   682: iload_3
    //   683: istore #7
    //   685: iload #6
    //   687: istore #5
    //   689: iload #8
    //   691: istore_3
    //   692: iload #7
    //   694: istore #6
    //   696: goto -> 813
    //   699: goto -> 790
    //   702: aload #21
    //   704: getfield mListDimensionBehaviors : [Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   707: iconst_1
    //   708: aaload
    //   709: getstatic android/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT : Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   712: if_acmpne -> 790
    //   715: iload #6
    //   717: iconst_3
    //   718: if_icmpne -> 790
    //   721: aload #21
    //   723: iconst_1
    //   724: putfield mResolvedDimensionRatioSide : I
    //   727: aload #21
    //   729: getfield mDimensionRatioSide : I
    //   732: iconst_m1
    //   733: if_icmpne -> 748
    //   736: aload #21
    //   738: fconst_1
    //   739: aload #21
    //   741: getfield mResolvedDimensionRatio : F
    //   744: fdiv
    //   745: putfield mResolvedDimensionRatio : F
    //   748: aload #21
    //   750: getfield mResolvedDimensionRatio : F
    //   753: aload #21
    //   755: getfield mWidth : I
    //   758: i2f
    //   759: fmul
    //   760: f2i
    //   761: istore_3
    //   762: aload #21
    //   764: getfield mListDimensionBehaviors : [Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   767: iconst_0
    //   768: aaload
    //   769: getstatic android/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT : Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   772: if_acmpeq -> 787
    //   775: iload_3
    //   776: istore #6
    //   778: iload #5
    //   780: istore_3
    //   781: iconst_4
    //   782: istore #5
    //   784: goto -> 813
    //   787: goto -> 790
    //   790: iconst_1
    //   791: istore #7
    //   793: iload_3
    //   794: istore #8
    //   796: goto -> 827
    //   799: iload_3
    //   800: istore #5
    //   802: iload #6
    //   804: istore_3
    //   805: iload #5
    //   807: istore #6
    //   809: iload #7
    //   811: istore #5
    //   813: iconst_0
    //   814: istore #7
    //   816: iload #6
    //   818: istore #8
    //   820: iload #5
    //   822: istore #6
    //   824: iload_3
    //   825: istore #5
    //   827: aload #21
    //   829: getfield mResolvedMatchConstraintDefault : [I
    //   832: iconst_0
    //   833: iload #5
    //   835: iastore
    //   836: aload #21
    //   838: getfield mResolvedMatchConstraintDefault : [I
    //   841: iconst_1
    //   842: iload #6
    //   844: iastore
    //   845: iload #7
    //   847: ifeq -> 876
    //   850: aload #21
    //   852: getfield mResolvedDimensionRatioSide : I
    //   855: ifeq -> 870
    //   858: aload #21
    //   860: getfield mResolvedDimensionRatioSide : I
    //   863: iconst_m1
    //   864: if_icmpne -> 876
    //   867: goto -> 870
    //   870: iconst_1
    //   871: istore #13
    //   873: goto -> 879
    //   876: iconst_0
    //   877: istore #13
    //   879: aload #21
    //   881: getfield mListDimensionBehaviors : [Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   884: iconst_0
    //   885: aaload
    //   886: getstatic android/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour.WRAP_CONTENT : Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   889: if_acmpne -> 906
    //   892: aload #21
    //   894: instanceof android/support/constraint/solver/widgets/ConstraintWidgetContainer
    //   897: ifeq -> 906
    //   900: iconst_1
    //   901: istore #14
    //   903: goto -> 909
    //   906: iconst_0
    //   907: istore #14
    //   909: aload #21
    //   911: getfield mCenter : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   914: invokevirtual isConnected : ()Z
    //   917: iconst_1
    //   918: ixor
    //   919: istore #15
    //   921: aload #21
    //   923: getfield mHorizontalResolution : I
    //   926: iconst_2
    //   927: if_icmpeq -> 1067
    //   930: aload #21
    //   932: getfield mParent : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   935: ifnull -> 955
    //   938: aload_1
    //   939: aload #21
    //   941: getfield mParent : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   944: getfield mRight : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   947: invokevirtual createObjectVariable : (Ljava/lang/Object;)Landroid/support/constraint/solver/SolverVariable;
    //   950: astore #16
    //   952: goto -> 958
    //   955: aconst_null
    //   956: astore #16
    //   958: aload #21
    //   960: getfield mParent : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   963: ifnull -> 983
    //   966: aload_1
    //   967: aload #21
    //   969: getfield mParent : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   972: getfield mLeft : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   975: invokevirtual createObjectVariable : (Ljava/lang/Object;)Landroid/support/constraint/solver/SolverVariable;
    //   978: astore #17
    //   980: goto -> 986
    //   983: aconst_null
    //   984: astore #17
    //   986: aload #21
    //   988: aload_1
    //   989: iload #11
    //   991: aload #17
    //   993: aload #16
    //   995: aload #21
    //   997: getfield mListDimensionBehaviors : [Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   1000: iconst_0
    //   1001: aaload
    //   1002: iload #14
    //   1004: aload #21
    //   1006: getfield mLeft : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1009: aload #21
    //   1011: getfield mRight : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1014: aload #21
    //   1016: getfield mX : I
    //   1019: iload #4
    //   1021: aload #21
    //   1023: getfield mMinWidth : I
    //   1026: aload #21
    //   1028: getfield mMaxDimension : [I
    //   1031: iconst_0
    //   1032: iaload
    //   1033: aload #21
    //   1035: getfield mHorizontalBiasPercent : F
    //   1038: iload #13
    //   1040: iload #12
    //   1042: iload #5
    //   1044: aload #21
    //   1046: getfield mMatchConstraintMinWidth : I
    //   1049: aload #21
    //   1051: getfield mMatchConstraintMaxWidth : I
    //   1054: aload #21
    //   1056: getfield mMatchConstraintPercentWidth : F
    //   1059: iload #15
    //   1061: invokespecial applyConstraints : (Landroid/support/constraint/solver/LinearSystem;ZLandroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;ZLandroid/support/constraint/solver/widgets/ConstraintAnchor;Landroid/support/constraint/solver/widgets/ConstraintAnchor;IIIIFZZIIIFZ)V
    //   1064: goto -> 1067
    //   1067: aload #22
    //   1069: astore #16
    //   1071: aload #20
    //   1073: astore #17
    //   1075: aload_0
    //   1076: astore #20
    //   1078: aload #20
    //   1080: getfield mVerticalResolution : I
    //   1083: iconst_2
    //   1084: if_icmpne -> 1088
    //   1087: return
    //   1088: aload #20
    //   1090: getfield mListDimensionBehaviors : [Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   1093: iconst_1
    //   1094: aaload
    //   1095: getstatic android/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour.WRAP_CONTENT : Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   1098: if_acmpne -> 1115
    //   1101: aload #20
    //   1103: instanceof android/support/constraint/solver/widgets/ConstraintWidgetContainer
    //   1106: ifeq -> 1115
    //   1109: iconst_1
    //   1110: istore #11
    //   1112: goto -> 1118
    //   1115: iconst_0
    //   1116: istore #11
    //   1118: iload #7
    //   1120: ifeq -> 1147
    //   1123: aload #20
    //   1125: getfield mResolvedDimensionRatioSide : I
    //   1128: iconst_1
    //   1129: if_icmpeq -> 1141
    //   1132: aload #20
    //   1134: getfield mResolvedDimensionRatioSide : I
    //   1137: iconst_m1
    //   1138: if_icmpne -> 1147
    //   1141: iconst_1
    //   1142: istore #12
    //   1144: goto -> 1150
    //   1147: iconst_0
    //   1148: istore #12
    //   1150: aload #20
    //   1152: getfield mBaselineDistance : I
    //   1155: ifle -> 1248
    //   1158: aload #20
    //   1160: getfield mBaseline : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1163: invokevirtual getResolutionNode : ()Landroid/support/constraint/solver/widgets/ResolutionAnchor;
    //   1166: getfield state : I
    //   1169: iconst_1
    //   1170: if_icmpne -> 1188
    //   1173: aload #20
    //   1175: getfield mBaseline : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1178: invokevirtual getResolutionNode : ()Landroid/support/constraint/solver/widgets/ResolutionAnchor;
    //   1181: aload_1
    //   1182: invokevirtual addResolvedValue : (Landroid/support/constraint/solver/LinearSystem;)V
    //   1185: goto -> 1248
    //   1188: aload_1
    //   1189: astore #21
    //   1191: aload #21
    //   1193: aload #17
    //   1195: aload #16
    //   1197: aload_0
    //   1198: invokevirtual getBaselineDistance : ()I
    //   1201: bipush #6
    //   1203: invokevirtual addEquality : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;II)Landroid/support/constraint/solver/ArrayRow;
    //   1206: pop
    //   1207: aload #20
    //   1209: getfield mBaseline : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1212: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1215: ifnull -> 1248
    //   1218: aload #21
    //   1220: aload #17
    //   1222: aload #21
    //   1224: aload #20
    //   1226: getfield mBaseline : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1229: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1232: invokevirtual createObjectVariable : (Ljava/lang/Object;)Landroid/support/constraint/solver/SolverVariable;
    //   1235: iconst_0
    //   1236: bipush #6
    //   1238: invokevirtual addEquality : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;II)Landroid/support/constraint/solver/ArrayRow;
    //   1241: pop
    //   1242: iconst_0
    //   1243: istore #13
    //   1245: goto -> 1252
    //   1248: iload #15
    //   1250: istore #13
    //   1252: aload_1
    //   1253: astore #22
    //   1255: aload #16
    //   1257: astore #21
    //   1259: aload #20
    //   1261: getfield mParent : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   1264: ifnull -> 1285
    //   1267: aload #22
    //   1269: aload #20
    //   1271: getfield mParent : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   1274: getfield mBottom : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1277: invokevirtual createObjectVariable : (Ljava/lang/Object;)Landroid/support/constraint/solver/SolverVariable;
    //   1280: astore #16
    //   1282: goto -> 1288
    //   1285: aconst_null
    //   1286: astore #16
    //   1288: aload #20
    //   1290: getfield mParent : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   1293: ifnull -> 1314
    //   1296: aload #22
    //   1298: aload #20
    //   1300: getfield mParent : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   1303: getfield mTop : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1306: invokevirtual createObjectVariable : (Ljava/lang/Object;)Landroid/support/constraint/solver/SolverVariable;
    //   1309: astore #17
    //   1311: goto -> 1317
    //   1314: aconst_null
    //   1315: astore #17
    //   1317: aload #20
    //   1319: aload #22
    //   1321: iload #9
    //   1323: aload #17
    //   1325: aload #16
    //   1327: aload #20
    //   1329: getfield mListDimensionBehaviors : [Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   1332: iconst_1
    //   1333: aaload
    //   1334: iload #11
    //   1336: aload #20
    //   1338: getfield mTop : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1341: aload #20
    //   1343: getfield mBottom : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1346: aload #20
    //   1348: getfield mY : I
    //   1351: iload #8
    //   1353: aload #20
    //   1355: getfield mMinHeight : I
    //   1358: aload #20
    //   1360: getfield mMaxDimension : [I
    //   1363: iconst_1
    //   1364: iaload
    //   1365: aload #20
    //   1367: getfield mVerticalBiasPercent : F
    //   1370: iload #12
    //   1372: iload #10
    //   1374: iload #6
    //   1376: aload #20
    //   1378: getfield mMatchConstraintMinHeight : I
    //   1381: aload #20
    //   1383: getfield mMatchConstraintMaxHeight : I
    //   1386: aload #20
    //   1388: getfield mMatchConstraintPercentHeight : F
    //   1391: iload #13
    //   1393: invokespecial applyConstraints : (Landroid/support/constraint/solver/LinearSystem;ZLandroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;ZLandroid/support/constraint/solver/widgets/ConstraintAnchor;Landroid/support/constraint/solver/widgets/ConstraintAnchor;IIIIFZZIIIFZ)V
    //   1396: iload #7
    //   1398: ifeq -> 1457
    //   1401: aload_0
    //   1402: astore #16
    //   1404: aload #16
    //   1406: getfield mResolvedDimensionRatioSide : I
    //   1409: iconst_1
    //   1410: if_icmpne -> 1435
    //   1413: aload_1
    //   1414: aload #18
    //   1416: aload #21
    //   1418: aload #19
    //   1420: aload #23
    //   1422: aload #16
    //   1424: getfield mResolvedDimensionRatio : F
    //   1427: bipush #6
    //   1429: invokevirtual addRatio : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;FI)V
    //   1432: goto -> 1457
    //   1435: aload_1
    //   1436: aload #19
    //   1438: aload #23
    //   1440: aload #18
    //   1442: aload #21
    //   1444: aload #16
    //   1446: getfield mResolvedDimensionRatio : F
    //   1449: bipush #6
    //   1451: invokevirtual addRatio : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;FI)V
    //   1454: goto -> 1457
    //   1457: aload_0
    //   1458: astore #16
    //   1460: aload #16
    //   1462: getfield mCenter : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1465: invokevirtual isConnected : ()Z
    //   1468: ifeq -> 1510
    //   1471: aload_1
    //   1472: aload #16
    //   1474: aload #16
    //   1476: getfield mCenter : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1479: invokevirtual getTarget : ()Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1482: invokevirtual getOwner : ()Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   1485: aload #16
    //   1487: getfield mCircleConstraintAngle : F
    //   1490: ldc_w 90.0
    //   1493: fadd
    //   1494: f2d
    //   1495: invokestatic toRadians : (D)D
    //   1498: d2f
    //   1499: aload #16
    //   1501: getfield mCenter : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1504: invokevirtual getMargin : ()I
    //   1507: invokevirtual addCenterPoint : (Landroid/support/constraint/solver/widgets/ConstraintWidget;Landroid/support/constraint/solver/widgets/ConstraintWidget;FI)V
    //   1510: return
  }
  
  public boolean allowedInBarrier() {
    return (this.mVisibility != 8);
  }
  
  public void analyze(int paramInt) {
    Optimizer.analyze(paramInt, this);
  }
  
  public void connect(ConstraintAnchor.Type paramType1, ConstraintWidget paramConstraintWidget, ConstraintAnchor.Type paramType2) {
    connect(paramType1, paramConstraintWidget, paramType2, 0, ConstraintAnchor.Strength.STRONG);
  }
  
  public void connect(ConstraintAnchor.Type paramType1, ConstraintWidget paramConstraintWidget, ConstraintAnchor.Type paramType2, int paramInt) {
    connect(paramType1, paramConstraintWidget, paramType2, paramInt, ConstraintAnchor.Strength.STRONG);
  }
  
  public void connect(ConstraintAnchor.Type paramType1, ConstraintWidget paramConstraintWidget, ConstraintAnchor.Type paramType2, int paramInt, ConstraintAnchor.Strength paramStrength) {
    connect(paramType1, paramConstraintWidget, paramType2, paramInt, paramStrength, 0);
  }
  
  public void connect(ConstraintAnchor.Type paramType1, ConstraintWidget paramConstraintWidget, ConstraintAnchor.Type paramType2, int paramInt1, ConstraintAnchor.Strength paramStrength, int paramInt2) {
    ConstraintAnchor constraintAnchor1;
    ConstraintAnchor constraintAnchor2;
    ConstraintAnchor.Type type = ConstraintAnchor.Type.CENTER;
    boolean bool = false;
    if (paramType1 == type) {
      if (paramType2 == ConstraintAnchor.Type.CENTER) {
        constraintAnchor1 = getAnchor(ConstraintAnchor.Type.LEFT);
        constraintAnchor2 = getAnchor(ConstraintAnchor.Type.RIGHT);
        ConstraintAnchor constraintAnchor3 = getAnchor(ConstraintAnchor.Type.TOP);
        ConstraintAnchor constraintAnchor4 = getAnchor(ConstraintAnchor.Type.BOTTOM);
        bool = true;
        if ((constraintAnchor1 != null && constraintAnchor1.isConnected()) || (constraintAnchor2 != null && constraintAnchor2.isConnected())) {
          paramInt1 = 0;
        } else {
          connect(ConstraintAnchor.Type.LEFT, paramConstraintWidget, ConstraintAnchor.Type.LEFT, 0, paramStrength, paramInt2);
          connect(ConstraintAnchor.Type.RIGHT, paramConstraintWidget, ConstraintAnchor.Type.RIGHT, 0, paramStrength, paramInt2);
          paramInt1 = 1;
        } 
        if ((constraintAnchor3 != null && constraintAnchor3.isConnected()) || (constraintAnchor4 != null && constraintAnchor4.isConnected())) {
          bool = false;
        } else {
          connect(ConstraintAnchor.Type.TOP, paramConstraintWidget, ConstraintAnchor.Type.TOP, 0, paramStrength, paramInt2);
          connect(ConstraintAnchor.Type.BOTTOM, paramConstraintWidget, ConstraintAnchor.Type.BOTTOM, 0, paramStrength, paramInt2);
        } 
        if (paramInt1 != 0 && bool) {
          getAnchor(ConstraintAnchor.Type.CENTER).connect(paramConstraintWidget.getAnchor(ConstraintAnchor.Type.CENTER), 0, paramInt2);
          return;
        } 
        if (paramInt1 != 0) {
          getAnchor(ConstraintAnchor.Type.CENTER_X).connect(paramConstraintWidget.getAnchor(ConstraintAnchor.Type.CENTER_X), 0, paramInt2);
          return;
        } 
        if (bool) {
          getAnchor(ConstraintAnchor.Type.CENTER_Y).connect(paramConstraintWidget.getAnchor(ConstraintAnchor.Type.CENTER_Y), 0, paramInt2);
          return;
        } 
      } else {
        if (constraintAnchor2 == ConstraintAnchor.Type.LEFT || constraintAnchor2 == ConstraintAnchor.Type.RIGHT) {
          connect(ConstraintAnchor.Type.LEFT, paramConstraintWidget, (ConstraintAnchor.Type)constraintAnchor2, 0, paramStrength, paramInt2);
          connect(ConstraintAnchor.Type.RIGHT, paramConstraintWidget, (ConstraintAnchor.Type)constraintAnchor2, 0, paramStrength, paramInt2);
          getAnchor(ConstraintAnchor.Type.CENTER).connect(paramConstraintWidget.getAnchor((ConstraintAnchor.Type)constraintAnchor2), 0, paramInt2);
          return;
        } 
        if (constraintAnchor2 == ConstraintAnchor.Type.TOP || constraintAnchor2 == ConstraintAnchor.Type.BOTTOM) {
          connect(ConstraintAnchor.Type.TOP, paramConstraintWidget, (ConstraintAnchor.Type)constraintAnchor2, 0, paramStrength, paramInt2);
          connect(ConstraintAnchor.Type.BOTTOM, paramConstraintWidget, (ConstraintAnchor.Type)constraintAnchor2, 0, paramStrength, paramInt2);
          getAnchor(ConstraintAnchor.Type.CENTER).connect(paramConstraintWidget.getAnchor((ConstraintAnchor.Type)constraintAnchor2), 0, paramInt2);
          return;
        } 
      } 
    } else {
      if (constraintAnchor1 == ConstraintAnchor.Type.CENTER_X && (constraintAnchor2 == ConstraintAnchor.Type.LEFT || constraintAnchor2 == ConstraintAnchor.Type.RIGHT)) {
        constraintAnchor1 = getAnchor(ConstraintAnchor.Type.LEFT);
        constraintAnchor3 = paramConstraintWidget.getAnchor((ConstraintAnchor.Type)constraintAnchor2);
        constraintAnchor2 = getAnchor(ConstraintAnchor.Type.RIGHT);
        constraintAnchor1.connect(constraintAnchor3, 0, paramInt2);
        constraintAnchor2.connect(constraintAnchor3, 0, paramInt2);
        getAnchor(ConstraintAnchor.Type.CENTER_X).connect(constraintAnchor3, 0, paramInt2);
        return;
      } 
      if (constraintAnchor1 == ConstraintAnchor.Type.CENTER_Y && (constraintAnchor2 == ConstraintAnchor.Type.TOP || constraintAnchor2 == ConstraintAnchor.Type.BOTTOM)) {
        constraintAnchor1 = constraintAnchor3.getAnchor((ConstraintAnchor.Type)constraintAnchor2);
        getAnchor(ConstraintAnchor.Type.TOP).connect(constraintAnchor1, 0, paramInt2);
        getAnchor(ConstraintAnchor.Type.BOTTOM).connect(constraintAnchor1, 0, paramInt2);
        getAnchor(ConstraintAnchor.Type.CENTER_Y).connect(constraintAnchor1, 0, paramInt2);
        return;
      } 
      if (constraintAnchor1 == ConstraintAnchor.Type.CENTER_X && constraintAnchor2 == ConstraintAnchor.Type.CENTER_X) {
        getAnchor(ConstraintAnchor.Type.LEFT).connect(constraintAnchor3.getAnchor(ConstraintAnchor.Type.LEFT), 0, paramInt2);
        getAnchor(ConstraintAnchor.Type.RIGHT).connect(constraintAnchor3.getAnchor(ConstraintAnchor.Type.RIGHT), 0, paramInt2);
        getAnchor(ConstraintAnchor.Type.CENTER_X).connect(constraintAnchor3.getAnchor((ConstraintAnchor.Type)constraintAnchor2), 0, paramInt2);
        return;
      } 
      if (constraintAnchor1 == ConstraintAnchor.Type.CENTER_Y && constraintAnchor2 == ConstraintAnchor.Type.CENTER_Y) {
        getAnchor(ConstraintAnchor.Type.TOP).connect(constraintAnchor3.getAnchor(ConstraintAnchor.Type.TOP), 0, paramInt2);
        getAnchor(ConstraintAnchor.Type.BOTTOM).connect(constraintAnchor3.getAnchor(ConstraintAnchor.Type.BOTTOM), 0, paramInt2);
        getAnchor(ConstraintAnchor.Type.CENTER_Y).connect(constraintAnchor3.getAnchor((ConstraintAnchor.Type)constraintAnchor2), 0, paramInt2);
        return;
      } 
      ConstraintAnchor constraintAnchor4 = getAnchor((ConstraintAnchor.Type)constraintAnchor1);
      ConstraintAnchor constraintAnchor3 = constraintAnchor3.getAnchor((ConstraintAnchor.Type)constraintAnchor2);
      if (constraintAnchor4.isValidConnection(constraintAnchor3)) {
        if (constraintAnchor1 == ConstraintAnchor.Type.BASELINE) {
          constraintAnchor1 = getAnchor(ConstraintAnchor.Type.TOP);
          constraintAnchor2 = getAnchor(ConstraintAnchor.Type.BOTTOM);
          if (constraintAnchor1 != null)
            constraintAnchor1.reset(); 
          paramInt1 = bool;
          if (constraintAnchor2 != null) {
            constraintAnchor2.reset();
            paramInt1 = bool;
          } 
        } else if (constraintAnchor1 == ConstraintAnchor.Type.TOP || constraintAnchor1 == ConstraintAnchor.Type.BOTTOM) {
          constraintAnchor2 = getAnchor(ConstraintAnchor.Type.BASELINE);
          if (constraintAnchor2 != null)
            constraintAnchor2.reset(); 
          constraintAnchor2 = getAnchor(ConstraintAnchor.Type.CENTER);
          if (constraintAnchor2.getTarget() != constraintAnchor3)
            constraintAnchor2.reset(); 
          constraintAnchor1 = getAnchor((ConstraintAnchor.Type)constraintAnchor1).getOpposite();
          constraintAnchor2 = getAnchor(ConstraintAnchor.Type.CENTER_Y);
          if (constraintAnchor2.isConnected()) {
            constraintAnchor1.reset();
            constraintAnchor2.reset();
          } 
        } else if (constraintAnchor1 == ConstraintAnchor.Type.LEFT || constraintAnchor1 == ConstraintAnchor.Type.RIGHT) {
          constraintAnchor2 = getAnchor(ConstraintAnchor.Type.CENTER);
          if (constraintAnchor2.getTarget() != constraintAnchor3)
            constraintAnchor2.reset(); 
          constraintAnchor1 = getAnchor((ConstraintAnchor.Type)constraintAnchor1).getOpposite();
          constraintAnchor2 = getAnchor(ConstraintAnchor.Type.CENTER_X);
          if (constraintAnchor2.isConnected()) {
            constraintAnchor1.reset();
            constraintAnchor2.reset();
          } 
        } 
        constraintAnchor4.connect(constraintAnchor3, paramInt1, paramStrength, paramInt2);
        constraintAnchor3.getOwner().connectedTo(constraintAnchor4.getOwner());
      } 
    } 
  }
  
  public void connect(ConstraintAnchor paramConstraintAnchor1, ConstraintAnchor paramConstraintAnchor2, int paramInt) {
    connect(paramConstraintAnchor1, paramConstraintAnchor2, paramInt, ConstraintAnchor.Strength.STRONG, 0);
  }
  
  public void connect(ConstraintAnchor paramConstraintAnchor1, ConstraintAnchor paramConstraintAnchor2, int paramInt1, int paramInt2) {
    connect(paramConstraintAnchor1, paramConstraintAnchor2, paramInt1, ConstraintAnchor.Strength.STRONG, paramInt2);
  }
  
  public void connect(ConstraintAnchor paramConstraintAnchor1, ConstraintAnchor paramConstraintAnchor2, int paramInt1, ConstraintAnchor.Strength paramStrength, int paramInt2) {
    if (paramConstraintAnchor1.getOwner() == this)
      connect(paramConstraintAnchor1.getType(), paramConstraintAnchor2.getOwner(), paramConstraintAnchor2.getType(), paramInt1, paramStrength, paramInt2); 
  }
  
  public void connectCircularConstraint(ConstraintWidget paramConstraintWidget, float paramFloat, int paramInt) {
    immediateConnect(ConstraintAnchor.Type.CENTER, paramConstraintWidget, ConstraintAnchor.Type.CENTER, paramInt, 0);
    this.mCircleConstraintAngle = paramFloat;
  }
  
  public void connectedTo(ConstraintWidget paramConstraintWidget) {}
  
  public void createObjectVariables(LinearSystem paramLinearSystem) {
    paramLinearSystem.createObjectVariable(this.mLeft);
    paramLinearSystem.createObjectVariable(this.mTop);
    paramLinearSystem.createObjectVariable(this.mRight);
    paramLinearSystem.createObjectVariable(this.mBottom);
    if (this.mBaselineDistance > 0)
      paramLinearSystem.createObjectVariable(this.mBaseline); 
  }
  
  public void disconnectUnlockedWidget(ConstraintWidget paramConstraintWidget) {
    ArrayList<ConstraintAnchor> arrayList = getAnchors();
    int j = arrayList.size();
    for (int i = 0; i < j; i++) {
      ConstraintAnchor constraintAnchor = arrayList.get(i);
      if (constraintAnchor.isConnected() && constraintAnchor.getTarget().getOwner() == paramConstraintWidget && constraintAnchor.getConnectionCreator() == 2)
        constraintAnchor.reset(); 
    } 
  }
  
  public void disconnectWidget(ConstraintWidget paramConstraintWidget) {
    ArrayList<ConstraintAnchor> arrayList = getAnchors();
    int j = arrayList.size();
    for (int i = 0; i < j; i++) {
      ConstraintAnchor constraintAnchor = arrayList.get(i);
      if (constraintAnchor.isConnected() && constraintAnchor.getTarget().getOwner() == paramConstraintWidget)
        constraintAnchor.reset(); 
    } 
  }
  
  public void forceUpdateDrawPosition() {
    int i = this.mX;
    int j = this.mY;
    int k = this.mX;
    int m = this.mWidth;
    int n = this.mY;
    int i1 = this.mHeight;
    this.mDrawX = i;
    this.mDrawY = j;
    this.mDrawWidth = k + m - i;
    this.mDrawHeight = n + i1 - j;
  }
  
  public ConstraintAnchor getAnchor(ConstraintAnchor.Type paramType) {
    switch (paramType) {
      default:
        throw new AssertionError(paramType.name());
      case null:
        return null;
      case null:
        return this.mCenterY;
      case null:
        return this.mCenterX;
      case null:
        return this.mCenter;
      case null:
        return this.mBaseline;
      case MATCH_CONSTRAINT:
        return this.mBottom;
      case MATCH_PARENT:
        return this.mRight;
      case WRAP_CONTENT:
        return this.mTop;
      case FIXED:
        break;
    } 
    return this.mLeft;
  }
  
  public ArrayList<ConstraintAnchor> getAnchors() {
    return this.mAnchors;
  }
  
  public int getBaselineDistance() {
    return this.mBaselineDistance;
  }
  
  public float getBiasPercent(int paramInt) {
    return (paramInt == 0) ? this.mHorizontalBiasPercent : ((paramInt == 1) ? this.mVerticalBiasPercent : -1.0F);
  }
  
  public int getBottom() {
    return getY() + this.mHeight;
  }
  
  public Object getCompanionWidget() {
    return this.mCompanionWidget;
  }
  
  public int getContainerItemSkip() {
    return this.mContainerItemSkip;
  }
  
  public String getDebugName() {
    return this.mDebugName;
  }
  
  public DimensionBehaviour getDimensionBehaviour(int paramInt) {
    return (paramInt == 0) ? getHorizontalDimensionBehaviour() : ((paramInt == 1) ? getVerticalDimensionBehaviour() : null);
  }
  
  public float getDimensionRatio() {
    return this.mDimensionRatio;
  }
  
  public int getDimensionRatioSide() {
    return this.mDimensionRatioSide;
  }
  
  public int getDrawBottom() {
    return getDrawY() + this.mDrawHeight;
  }
  
  public int getDrawHeight() {
    return this.mDrawHeight;
  }
  
  public int getDrawRight() {
    return getDrawX() + this.mDrawWidth;
  }
  
  public int getDrawWidth() {
    return this.mDrawWidth;
  }
  
  public int getDrawX() {
    return this.mDrawX + this.mOffsetX;
  }
  
  public int getDrawY() {
    return this.mDrawY + this.mOffsetY;
  }
  
  public int getHeight() {
    return (this.mVisibility == 8) ? 0 : this.mHeight;
  }
  
  public float getHorizontalBiasPercent() {
    return this.mHorizontalBiasPercent;
  }
  
  public ConstraintWidget getHorizontalChainControlWidget() {
    ConstraintWidget constraintWidget;
    if (isInHorizontalChain()) {
      ConstraintWidget constraintWidget1 = this;
      ConstraintAnchor constraintAnchor = null;
      while (true) {
        constraintWidget = (ConstraintWidget)constraintAnchor;
        if (constraintAnchor == null) {
          constraintWidget = (ConstraintWidget)constraintAnchor;
          if (constraintWidget1 != null) {
            ConstraintWidget constraintWidget2;
            ConstraintAnchor constraintAnchor1;
            constraintWidget = (ConstraintWidget)constraintWidget1.getAnchor(ConstraintAnchor.Type.LEFT);
            if (constraintWidget == null) {
              constraintWidget = null;
            } else {
              constraintWidget = (ConstraintWidget)constraintWidget.getTarget();
            } 
            if (constraintWidget == null) {
              constraintWidget = null;
            } else {
              constraintWidget2 = constraintWidget.getOwner();
            } 
            if (constraintWidget2 == getParent())
              return constraintWidget1; 
            if (constraintWidget2 == null) {
              constraintAnchor1 = null;
            } else {
              constraintAnchor1 = constraintWidget2.getAnchor(ConstraintAnchor.Type.RIGHT).getTarget();
            } 
            if (constraintAnchor1 != null && constraintAnchor1.getOwner() != constraintWidget1) {
              ConstraintWidget constraintWidget3 = constraintWidget1;
              continue;
            } 
            constraintWidget1 = constraintWidget2;
            continue;
          } 
        } 
        break;
      } 
    } else {
      constraintWidget = null;
    } 
    return constraintWidget;
  }
  
  public int getHorizontalChainStyle() {
    return this.mHorizontalChainStyle;
  }
  
  public DimensionBehaviour getHorizontalDimensionBehaviour() {
    return this.mListDimensionBehaviors[0];
  }
  
  public int getInternalDrawBottom() {
    return this.mDrawY + this.mDrawHeight;
  }
  
  public int getInternalDrawRight() {
    return this.mDrawX + this.mDrawWidth;
  }
  
  int getInternalDrawX() {
    return this.mDrawX;
  }
  
  int getInternalDrawY() {
    return this.mDrawY;
  }
  
  public int getLeft() {
    return getX();
  }
  
  public int getLength(int paramInt) {
    return (paramInt == 0) ? getWidth() : ((paramInt == 1) ? getHeight() : 0);
  }
  
  public int getMaxHeight() {
    return this.mMaxDimension[1];
  }
  
  public int getMaxWidth() {
    return this.mMaxDimension[0];
  }
  
  public int getMinHeight() {
    return this.mMinHeight;
  }
  
  public int getMinWidth() {
    return this.mMinWidth;
  }
  
  public int getOptimizerWrapHeight() {
    int i = this.mHeight;
    int j = i;
    if (this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT) {
      if (this.mMatchConstraintDefaultHeight == 1) {
        i = Math.max(this.mMatchConstraintMinHeight, i);
      } else if (this.mMatchConstraintMinHeight > 0) {
        i = this.mMatchConstraintMinHeight;
        this.mHeight = i;
      } else {
        i = 0;
      } 
      j = i;
      if (this.mMatchConstraintMaxHeight > 0) {
        j = i;
        if (this.mMatchConstraintMaxHeight < i)
          j = this.mMatchConstraintMaxHeight; 
      } 
    } 
    return j;
  }
  
  public int getOptimizerWrapWidth() {
    int i = this.mWidth;
    int j = i;
    if (this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT) {
      if (this.mMatchConstraintDefaultWidth == 1) {
        i = Math.max(this.mMatchConstraintMinWidth, i);
      } else if (this.mMatchConstraintMinWidth > 0) {
        i = this.mMatchConstraintMinWidth;
        this.mWidth = i;
      } else {
        i = 0;
      } 
      j = i;
      if (this.mMatchConstraintMaxWidth > 0) {
        j = i;
        if (this.mMatchConstraintMaxWidth < i)
          j = this.mMatchConstraintMaxWidth; 
      } 
    } 
    return j;
  }
  
  public ConstraintWidget getParent() {
    return this.mParent;
  }
  
  int getRelativePositioning(int paramInt) {
    return (paramInt == 0) ? this.mRelX : ((paramInt == 1) ? this.mRelY : 0);
  }
  
  public ResolutionDimension getResolutionHeight() {
    if (this.mResolutionHeight == null)
      this.mResolutionHeight = new ResolutionDimension(); 
    return this.mResolutionHeight;
  }
  
  public ResolutionDimension getResolutionWidth() {
    if (this.mResolutionWidth == null)
      this.mResolutionWidth = new ResolutionDimension(); 
    return this.mResolutionWidth;
  }
  
  public int getRight() {
    return getX() + this.mWidth;
  }
  
  public WidgetContainer getRootWidgetContainer() {
    ConstraintWidget constraintWidget;
    for (constraintWidget = this; constraintWidget.getParent() != null; constraintWidget = constraintWidget.getParent());
    return (constraintWidget instanceof WidgetContainer) ? (WidgetContainer)constraintWidget : null;
  }
  
  protected int getRootX() {
    return this.mX + this.mOffsetX;
  }
  
  protected int getRootY() {
    return this.mY + this.mOffsetY;
  }
  
  public int getTop() {
    return getY();
  }
  
  public String getType() {
    return this.mType;
  }
  
  public float getVerticalBiasPercent() {
    return this.mVerticalBiasPercent;
  }
  
  public ConstraintWidget getVerticalChainControlWidget() {
    ConstraintWidget constraintWidget;
    if (isInVerticalChain()) {
      ConstraintWidget constraintWidget1 = this;
      ConstraintAnchor constraintAnchor = null;
      while (true) {
        constraintWidget = (ConstraintWidget)constraintAnchor;
        if (constraintAnchor == null) {
          constraintWidget = (ConstraintWidget)constraintAnchor;
          if (constraintWidget1 != null) {
            ConstraintWidget constraintWidget2;
            ConstraintAnchor constraintAnchor1;
            constraintWidget = (ConstraintWidget)constraintWidget1.getAnchor(ConstraintAnchor.Type.TOP);
            if (constraintWidget == null) {
              constraintWidget = null;
            } else {
              constraintWidget = (ConstraintWidget)constraintWidget.getTarget();
            } 
            if (constraintWidget == null) {
              constraintWidget = null;
            } else {
              constraintWidget2 = constraintWidget.getOwner();
            } 
            if (constraintWidget2 == getParent())
              return constraintWidget1; 
            if (constraintWidget2 == null) {
              constraintAnchor1 = null;
            } else {
              constraintAnchor1 = constraintWidget2.getAnchor(ConstraintAnchor.Type.BOTTOM).getTarget();
            } 
            if (constraintAnchor1 != null && constraintAnchor1.getOwner() != constraintWidget1) {
              ConstraintWidget constraintWidget3 = constraintWidget1;
              continue;
            } 
            constraintWidget1 = constraintWidget2;
            continue;
          } 
        } 
        break;
      } 
    } else {
      constraintWidget = null;
    } 
    return constraintWidget;
  }
  
  public int getVerticalChainStyle() {
    return this.mVerticalChainStyle;
  }
  
  public DimensionBehaviour getVerticalDimensionBehaviour() {
    return this.mListDimensionBehaviors[1];
  }
  
  public int getVisibility() {
    return this.mVisibility;
  }
  
  public int getWidth() {
    return (this.mVisibility == 8) ? 0 : this.mWidth;
  }
  
  public int getWrapHeight() {
    return this.mWrapHeight;
  }
  
  public int getWrapWidth() {
    return this.mWrapWidth;
  }
  
  public int getX() {
    return this.mX;
  }
  
  public int getY() {
    return this.mY;
  }
  
  public boolean hasAncestor(ConstraintWidget paramConstraintWidget) {
    ConstraintWidget constraintWidget2 = getParent();
    if (constraintWidget2 == paramConstraintWidget)
      return true; 
    ConstraintWidget constraintWidget1 = constraintWidget2;
    if (constraintWidget2 == paramConstraintWidget.getParent())
      return false; 
    while (constraintWidget1 != null) {
      if (constraintWidget1 == paramConstraintWidget)
        return true; 
      if (constraintWidget1 == paramConstraintWidget.getParent())
        return true; 
      constraintWidget1 = constraintWidget1.getParent();
    } 
    return false;
  }
  
  public boolean hasBaseline() {
    return (this.mBaselineDistance > 0);
  }
  
  public void immediateConnect(ConstraintAnchor.Type paramType1, ConstraintWidget paramConstraintWidget, ConstraintAnchor.Type paramType2, int paramInt1, int paramInt2) {
    getAnchor(paramType1).connect(paramConstraintWidget.getAnchor(paramType2), paramInt1, paramInt2, ConstraintAnchor.Strength.STRONG, 0, true);
  }
  
  public boolean isFullyResolved() {
    return ((this.mLeft.getResolutionNode()).state == 1 && (this.mRight.getResolutionNode()).state == 1 && (this.mTop.getResolutionNode()).state == 1 && (this.mBottom.getResolutionNode()).state == 1);
  }
  
  public boolean isHeightWrapContent() {
    return this.mIsHeightWrapContent;
  }
  
  public boolean isInHorizontalChain() {
    return ((this.mLeft.mTarget != null && this.mLeft.mTarget.mTarget == this.mLeft) || (this.mRight.mTarget != null && this.mRight.mTarget.mTarget == this.mRight));
  }
  
  public boolean isInVerticalChain() {
    return ((this.mTop.mTarget != null && this.mTop.mTarget.mTarget == this.mTop) || (this.mBottom.mTarget != null && this.mBottom.mTarget.mTarget == this.mBottom));
  }
  
  public boolean isInsideConstraintLayout() {
    ConstraintWidget constraintWidget2 = getParent();
    ConstraintWidget constraintWidget1 = constraintWidget2;
    if (constraintWidget2 == null)
      return false; 
    while (constraintWidget1 != null) {
      if (constraintWidget1 instanceof ConstraintWidgetContainer)
        return true; 
      constraintWidget1 = constraintWidget1.getParent();
    } 
    return false;
  }
  
  public boolean isRoot() {
    return (this.mParent == null);
  }
  
  public boolean isRootContainer() {
    return (this instanceof ConstraintWidgetContainer && (this.mParent == null || !(this.mParent instanceof ConstraintWidgetContainer)));
  }
  
  public boolean isSpreadHeight() {
    return (this.mMatchConstraintDefaultHeight == 0 && this.mDimensionRatio == 0.0F && this.mMatchConstraintMinHeight == 0 && this.mMatchConstraintMaxHeight == 0 && this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT);
  }
  
  public boolean isSpreadWidth() {
    int i = this.mMatchConstraintDefaultWidth;
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (i == 0) {
      bool1 = bool2;
      if (this.mDimensionRatio == 0.0F) {
        bool1 = bool2;
        if (this.mMatchConstraintMinWidth == 0) {
          bool1 = bool2;
          if (this.mMatchConstraintMaxWidth == 0) {
            bool1 = bool2;
            if (this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT)
              bool1 = true; 
          } 
        } 
      } 
    } 
    return bool1;
  }
  
  public boolean isWidthWrapContent() {
    return this.mIsWidthWrapContent;
  }
  
  public void reset() {
    this.mLeft.reset();
    this.mTop.reset();
    this.mRight.reset();
    this.mBottom.reset();
    this.mBaseline.reset();
    this.mCenterX.reset();
    this.mCenterY.reset();
    this.mCenter.reset();
    this.mParent = null;
    this.mCircleConstraintAngle = 0.0F;
    this.mWidth = 0;
    this.mHeight = 0;
    this.mDimensionRatio = 0.0F;
    this.mDimensionRatioSide = -1;
    this.mX = 0;
    this.mY = 0;
    this.mDrawX = 0;
    this.mDrawY = 0;
    this.mDrawWidth = 0;
    this.mDrawHeight = 0;
    this.mOffsetX = 0;
    this.mOffsetY = 0;
    this.mBaselineDistance = 0;
    this.mMinWidth = 0;
    this.mMinHeight = 0;
    this.mWrapWidth = 0;
    this.mWrapHeight = 0;
    this.mHorizontalBiasPercent = DEFAULT_BIAS;
    this.mVerticalBiasPercent = DEFAULT_BIAS;
    this.mListDimensionBehaviors[0] = DimensionBehaviour.FIXED;
    this.mListDimensionBehaviors[1] = DimensionBehaviour.FIXED;
    this.mCompanionWidget = null;
    this.mContainerItemSkip = 0;
    this.mVisibility = 0;
    this.mType = null;
    this.mHorizontalWrapVisited = false;
    this.mVerticalWrapVisited = false;
    this.mHorizontalChainStyle = 0;
    this.mVerticalChainStyle = 0;
    this.mHorizontalChainFixedPosition = false;
    this.mVerticalChainFixedPosition = false;
    this.mWeight[0] = -1.0F;
    this.mWeight[1] = -1.0F;
    this.mHorizontalResolution = -1;
    this.mVerticalResolution = -1;
    this.mMaxDimension[0] = Integer.MAX_VALUE;
    this.mMaxDimension[1] = Integer.MAX_VALUE;
    this.mMatchConstraintDefaultWidth = 0;
    this.mMatchConstraintDefaultHeight = 0;
    this.mMatchConstraintPercentWidth = 1.0F;
    this.mMatchConstraintPercentHeight = 1.0F;
    this.mMatchConstraintMaxWidth = Integer.MAX_VALUE;
    this.mMatchConstraintMaxHeight = Integer.MAX_VALUE;
    this.mMatchConstraintMinWidth = 0;
    this.mMatchConstraintMinHeight = 0;
    this.mResolvedDimensionRatioSide = -1;
    this.mResolvedDimensionRatio = 1.0F;
    if (this.mResolutionWidth != null)
      this.mResolutionWidth.reset(); 
    if (this.mResolutionHeight != null)
      this.mResolutionHeight.reset(); 
    this.mBelongingGroup = null;
    this.mOptimizerMeasurable = false;
    this.mOptimizerMeasured = false;
    this.mGroupsToSolver = false;
  }
  
  public void resetAllConstraints() {
    resetAnchors();
    setVerticalBiasPercent(DEFAULT_BIAS);
    setHorizontalBiasPercent(DEFAULT_BIAS);
    if (this instanceof ConstraintWidgetContainer)
      return; 
    if (getHorizontalDimensionBehaviour() == DimensionBehaviour.MATCH_CONSTRAINT)
      if (getWidth() == getWrapWidth()) {
        setHorizontalDimensionBehaviour(DimensionBehaviour.WRAP_CONTENT);
      } else if (getWidth() > getMinWidth()) {
        setHorizontalDimensionBehaviour(DimensionBehaviour.FIXED);
      }  
    if (getVerticalDimensionBehaviour() == DimensionBehaviour.MATCH_CONSTRAINT) {
      if (getHeight() == getWrapHeight()) {
        setVerticalDimensionBehaviour(DimensionBehaviour.WRAP_CONTENT);
        return;
      } 
      if (getHeight() > getMinHeight())
        setVerticalDimensionBehaviour(DimensionBehaviour.FIXED); 
    } 
  }
  
  public void resetAnchor(ConstraintAnchor paramConstraintAnchor) {
    if (getParent() != null && getParent() instanceof ConstraintWidgetContainer && ((ConstraintWidgetContainer)getParent()).handlesInternalConstraints())
      return; 
    ConstraintAnchor constraintAnchor1 = getAnchor(ConstraintAnchor.Type.LEFT);
    ConstraintAnchor constraintAnchor2 = getAnchor(ConstraintAnchor.Type.RIGHT);
    ConstraintAnchor constraintAnchor3 = getAnchor(ConstraintAnchor.Type.TOP);
    ConstraintAnchor constraintAnchor4 = getAnchor(ConstraintAnchor.Type.BOTTOM);
    ConstraintAnchor constraintAnchor5 = getAnchor(ConstraintAnchor.Type.CENTER);
    ConstraintAnchor constraintAnchor6 = getAnchor(ConstraintAnchor.Type.CENTER_X);
    ConstraintAnchor constraintAnchor7 = getAnchor(ConstraintAnchor.Type.CENTER_Y);
    if (paramConstraintAnchor == constraintAnchor5) {
      if (constraintAnchor1.isConnected() && constraintAnchor2.isConnected() && constraintAnchor1.getTarget() == constraintAnchor2.getTarget()) {
        constraintAnchor1.reset();
        constraintAnchor2.reset();
      } 
      if (constraintAnchor3.isConnected() && constraintAnchor4.isConnected() && constraintAnchor3.getTarget() == constraintAnchor4.getTarget()) {
        constraintAnchor3.reset();
        constraintAnchor4.reset();
      } 
      this.mHorizontalBiasPercent = 0.5F;
      this.mVerticalBiasPercent = 0.5F;
    } else if (paramConstraintAnchor == constraintAnchor6) {
      if (constraintAnchor1.isConnected() && constraintAnchor2.isConnected() && constraintAnchor1.getTarget().getOwner() == constraintAnchor2.getTarget().getOwner()) {
        constraintAnchor1.reset();
        constraintAnchor2.reset();
      } 
      this.mHorizontalBiasPercent = 0.5F;
    } else if (paramConstraintAnchor == constraintAnchor7) {
      if (constraintAnchor3.isConnected() && constraintAnchor4.isConnected() && constraintAnchor3.getTarget().getOwner() == constraintAnchor4.getTarget().getOwner()) {
        constraintAnchor3.reset();
        constraintAnchor4.reset();
      } 
      this.mVerticalBiasPercent = 0.5F;
    } else if (paramConstraintAnchor == constraintAnchor1 || paramConstraintAnchor == constraintAnchor2) {
      if (constraintAnchor1.isConnected() && constraintAnchor1.getTarget() == constraintAnchor2.getTarget())
        constraintAnchor5.reset(); 
    } else if ((paramConstraintAnchor == constraintAnchor3 || paramConstraintAnchor == constraintAnchor4) && constraintAnchor3.isConnected() && constraintAnchor3.getTarget() == constraintAnchor4.getTarget()) {
      constraintAnchor5.reset();
    } 
    paramConstraintAnchor.reset();
  }
  
  public void resetAnchors() {
    ConstraintWidget constraintWidget = getParent();
    if (constraintWidget != null && constraintWidget instanceof ConstraintWidgetContainer && ((ConstraintWidgetContainer)getParent()).handlesInternalConstraints())
      return; 
    int i = 0;
    int j = this.mAnchors.size();
    while (i < j) {
      ((ConstraintAnchor)this.mAnchors.get(i)).reset();
      i++;
    } 
  }
  
  public void resetAnchors(int paramInt) {
    ConstraintWidget constraintWidget = getParent();
    if (constraintWidget != null && constraintWidget instanceof ConstraintWidgetContainer && ((ConstraintWidgetContainer)getParent()).handlesInternalConstraints())
      return; 
    int i = 0;
    int j = this.mAnchors.size();
    while (i < j) {
      ConstraintAnchor constraintAnchor = this.mAnchors.get(i);
      if (paramInt == constraintAnchor.getConnectionCreator()) {
        if (constraintAnchor.isVerticalAnchor()) {
          setVerticalBiasPercent(DEFAULT_BIAS);
        } else {
          setHorizontalBiasPercent(DEFAULT_BIAS);
        } 
        constraintAnchor.reset();
      } 
      i++;
    } 
  }
  
  public void resetResolutionNodes() {
    for (int i = 0; i < 6; i++)
      this.mListAnchors[i].getResolutionNode().reset(); 
  }
  
  public void resetSolverVariables(Cache paramCache) {
    this.mLeft.resetSolverVariable(paramCache);
    this.mTop.resetSolverVariable(paramCache);
    this.mRight.resetSolverVariable(paramCache);
    this.mBottom.resetSolverVariable(paramCache);
    this.mBaseline.resetSolverVariable(paramCache);
    this.mCenter.resetSolverVariable(paramCache);
    this.mCenterX.resetSolverVariable(paramCache);
    this.mCenterY.resetSolverVariable(paramCache);
  }
  
  public void resolve() {}
  
  public void setBaselineDistance(int paramInt) {
    this.mBaselineDistance = paramInt;
  }
  
  public void setCompanionWidget(Object paramObject) {
    this.mCompanionWidget = paramObject;
  }
  
  public void setContainerItemSkip(int paramInt) {
    if (paramInt >= 0) {
      this.mContainerItemSkip = paramInt;
      return;
    } 
    this.mContainerItemSkip = 0;
  }
  
  public void setDebugName(String paramString) {
    this.mDebugName = paramString;
  }
  
  public void setDebugSolverName(LinearSystem paramLinearSystem, String paramString) {
    this.mDebugName = paramString;
    SolverVariable solverVariable4 = paramLinearSystem.createObjectVariable(this.mLeft);
    SolverVariable solverVariable3 = paramLinearSystem.createObjectVariable(this.mTop);
    SolverVariable solverVariable2 = paramLinearSystem.createObjectVariable(this.mRight);
    SolverVariable solverVariable1 = paramLinearSystem.createObjectVariable(this.mBottom);
    StringBuilder stringBuilder4 = new StringBuilder();
    stringBuilder4.append(paramString);
    stringBuilder4.append(".left");
    solverVariable4.setName(stringBuilder4.toString());
    StringBuilder stringBuilder3 = new StringBuilder();
    stringBuilder3.append(paramString);
    stringBuilder3.append(".top");
    solverVariable3.setName(stringBuilder3.toString());
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append(paramString);
    stringBuilder2.append(".right");
    solverVariable2.setName(stringBuilder2.toString());
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append(paramString);
    stringBuilder1.append(".bottom");
    solverVariable1.setName(stringBuilder1.toString());
    if (this.mBaselineDistance > 0) {
      SolverVariable solverVariable = paramLinearSystem.createObjectVariable(this.mBaseline);
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append(".baseline");
      solverVariable.setName(stringBuilder.toString());
    } 
  }
  
  public void setDimension(int paramInt1, int paramInt2) {
    this.mWidth = paramInt1;
    if (this.mWidth < this.mMinWidth)
      this.mWidth = this.mMinWidth; 
    this.mHeight = paramInt2;
    if (this.mHeight < this.mMinHeight)
      this.mHeight = this.mMinHeight; 
  }
  
  public void setDimensionRatio(float paramFloat, int paramInt) {
    this.mDimensionRatio = paramFloat;
    this.mDimensionRatioSide = paramInt;
  }
  
  public void setDimensionRatio(String paramString) {
    // Byte code:
    //   0: aload_1
    //   1: ifnull -> 261
    //   4: aload_1
    //   5: invokevirtual length : ()I
    //   8: ifne -> 14
    //   11: goto -> 261
    //   14: iconst_m1
    //   15: istore #6
    //   17: aload_1
    //   18: invokevirtual length : ()I
    //   21: istore #8
    //   23: aload_1
    //   24: bipush #44
    //   26: invokevirtual indexOf : (I)I
    //   29: istore #9
    //   31: iconst_0
    //   32: istore #7
    //   34: iload #6
    //   36: istore #4
    //   38: iload #7
    //   40: istore #5
    //   42: iload #9
    //   44: ifle -> 114
    //   47: iload #6
    //   49: istore #4
    //   51: iload #7
    //   53: istore #5
    //   55: iload #9
    //   57: iload #8
    //   59: iconst_1
    //   60: isub
    //   61: if_icmpge -> 114
    //   64: aload_1
    //   65: iconst_0
    //   66: iload #9
    //   68: invokevirtual substring : (II)Ljava/lang/String;
    //   71: astore #10
    //   73: aload #10
    //   75: ldc_w 'W'
    //   78: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   81: ifeq -> 90
    //   84: iconst_0
    //   85: istore #4
    //   87: goto -> 108
    //   90: iload #6
    //   92: istore #4
    //   94: aload #10
    //   96: ldc_w 'H'
    //   99: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   102: ifeq -> 108
    //   105: iconst_1
    //   106: istore #4
    //   108: iload #9
    //   110: iconst_1
    //   111: iadd
    //   112: istore #5
    //   114: aload_1
    //   115: bipush #58
    //   117: invokevirtual indexOf : (I)I
    //   120: istore #6
    //   122: iload #6
    //   124: iflt -> 219
    //   127: iload #6
    //   129: iload #8
    //   131: iconst_1
    //   132: isub
    //   133: if_icmpge -> 219
    //   136: aload_1
    //   137: iload #5
    //   139: iload #6
    //   141: invokevirtual substring : (II)Ljava/lang/String;
    //   144: astore #10
    //   146: aload_1
    //   147: iload #6
    //   149: iconst_1
    //   150: iadd
    //   151: invokevirtual substring : (I)Ljava/lang/String;
    //   154: astore_1
    //   155: aload #10
    //   157: invokevirtual length : ()I
    //   160: ifle -> 241
    //   163: aload_1
    //   164: invokevirtual length : ()I
    //   167: ifle -> 241
    //   170: aload #10
    //   172: invokestatic parseFloat : (Ljava/lang/String;)F
    //   175: fstore_2
    //   176: aload_1
    //   177: invokestatic parseFloat : (Ljava/lang/String;)F
    //   180: fstore_3
    //   181: fload_2
    //   182: fconst_0
    //   183: fcmpl
    //   184: ifle -> 241
    //   187: fload_3
    //   188: fconst_0
    //   189: fcmpl
    //   190: ifle -> 241
    //   193: iload #4
    //   195: iconst_1
    //   196: if_icmpne -> 209
    //   199: fload_3
    //   200: fload_2
    //   201: fdiv
    //   202: invokestatic abs : (F)F
    //   205: fstore_2
    //   206: goto -> 243
    //   209: fload_2
    //   210: fload_3
    //   211: fdiv
    //   212: invokestatic abs : (F)F
    //   215: fstore_2
    //   216: goto -> 243
    //   219: aload_1
    //   220: iload #5
    //   222: invokevirtual substring : (I)Ljava/lang/String;
    //   225: astore_1
    //   226: aload_1
    //   227: invokevirtual length : ()I
    //   230: ifle -> 241
    //   233: aload_1
    //   234: invokestatic parseFloat : (Ljava/lang/String;)F
    //   237: fstore_2
    //   238: goto -> 243
    //   241: fconst_0
    //   242: fstore_2
    //   243: fload_2
    //   244: fconst_0
    //   245: fcmpl
    //   246: ifle -> 260
    //   249: aload_0
    //   250: fload_2
    //   251: putfield mDimensionRatio : F
    //   254: aload_0
    //   255: iload #4
    //   257: putfield mDimensionRatioSide : I
    //   260: return
    //   261: aload_0
    //   262: fconst_0
    //   263: putfield mDimensionRatio : F
    //   266: return
    //   267: astore_1
    //   268: goto -> 241
    // Exception table:
    //   from	to	target	type
    //   170	181	267	java/lang/NumberFormatException
    //   199	206	267	java/lang/NumberFormatException
    //   209	216	267	java/lang/NumberFormatException
    //   233	238	267	java/lang/NumberFormatException
  }
  
  public void setDrawHeight(int paramInt) {
    this.mDrawHeight = paramInt;
  }
  
  public void setDrawOrigin(int paramInt1, int paramInt2) {
    this.mDrawX = paramInt1 - this.mOffsetX;
    this.mDrawY = paramInt2 - this.mOffsetY;
    this.mX = this.mDrawX;
    this.mY = this.mDrawY;
  }
  
  public void setDrawWidth(int paramInt) {
    this.mDrawWidth = paramInt;
  }
  
  public void setDrawX(int paramInt) {
    this.mDrawX = paramInt - this.mOffsetX;
    this.mX = this.mDrawX;
  }
  
  public void setDrawY(int paramInt) {
    this.mDrawY = paramInt - this.mOffsetY;
    this.mY = this.mDrawY;
  }
  
  public void setFrame(int paramInt1, int paramInt2, int paramInt3) {
    if (paramInt3 == 0) {
      setHorizontalDimension(paramInt1, paramInt2);
    } else if (paramInt3 == 1) {
      setVerticalDimension(paramInt1, paramInt2);
    } 
    this.mOptimizerMeasured = true;
  }
  
  public void setFrame(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    int i = paramInt3 - paramInt1;
    paramInt3 = paramInt4 - paramInt2;
    this.mX = paramInt1;
    this.mY = paramInt2;
    if (this.mVisibility == 8) {
      this.mWidth = 0;
      this.mHeight = 0;
      return;
    } 
    paramInt1 = i;
    if (this.mListDimensionBehaviors[0] == DimensionBehaviour.FIXED) {
      paramInt1 = i;
      if (i < this.mWidth)
        paramInt1 = this.mWidth; 
    } 
    paramInt2 = paramInt3;
    if (this.mListDimensionBehaviors[1] == DimensionBehaviour.FIXED) {
      paramInt2 = paramInt3;
      if (paramInt3 < this.mHeight)
        paramInt2 = this.mHeight; 
    } 
    this.mWidth = paramInt1;
    this.mHeight = paramInt2;
    if (this.mHeight < this.mMinHeight)
      this.mHeight = this.mMinHeight; 
    if (this.mWidth < this.mMinWidth)
      this.mWidth = this.mMinWidth; 
    this.mOptimizerMeasured = true;
  }
  
  public void setGoneMargin(ConstraintAnchor.Type paramType, int paramInt) {
    switch (paramType) {
      default:
        return;
      case MATCH_CONSTRAINT:
        this.mBottom.mGoneMargin = paramInt;
        return;
      case MATCH_PARENT:
        this.mRight.mGoneMargin = paramInt;
        return;
      case WRAP_CONTENT:
        this.mTop.mGoneMargin = paramInt;
        return;
      case FIXED:
        break;
    } 
    this.mLeft.mGoneMargin = paramInt;
  }
  
  public void setHeight(int paramInt) {
    this.mHeight = paramInt;
    if (this.mHeight < this.mMinHeight)
      this.mHeight = this.mMinHeight; 
  }
  
  public void setHeightWrapContent(boolean paramBoolean) {
    this.mIsHeightWrapContent = paramBoolean;
  }
  
  public void setHorizontalBiasPercent(float paramFloat) {
    this.mHorizontalBiasPercent = paramFloat;
  }
  
  public void setHorizontalChainStyle(int paramInt) {
    this.mHorizontalChainStyle = paramInt;
  }
  
  public void setHorizontalDimension(int paramInt1, int paramInt2) {
    this.mX = paramInt1;
    this.mWidth = paramInt2 - paramInt1;
    if (this.mWidth < this.mMinWidth)
      this.mWidth = this.mMinWidth; 
  }
  
  public void setHorizontalDimensionBehaviour(DimensionBehaviour paramDimensionBehaviour) {
    this.mListDimensionBehaviors[0] = paramDimensionBehaviour;
    if (paramDimensionBehaviour == DimensionBehaviour.WRAP_CONTENT)
      setWidth(this.mWrapWidth); 
  }
  
  public void setHorizontalMatchStyle(int paramInt1, int paramInt2, int paramInt3, float paramFloat) {
    this.mMatchConstraintDefaultWidth = paramInt1;
    this.mMatchConstraintMinWidth = paramInt2;
    this.mMatchConstraintMaxWidth = paramInt3;
    this.mMatchConstraintPercentWidth = paramFloat;
    if (paramFloat < 1.0F && this.mMatchConstraintDefaultWidth == 0)
      this.mMatchConstraintDefaultWidth = 2; 
  }
  
  public void setHorizontalWeight(float paramFloat) {
    this.mWeight[0] = paramFloat;
  }
  
  public void setLength(int paramInt1, int paramInt2) {
    if (paramInt2 == 0) {
      setWidth(paramInt1);
      return;
    } 
    if (paramInt2 == 1)
      setHeight(paramInt1); 
  }
  
  public void setMaxHeight(int paramInt) {
    this.mMaxDimension[1] = paramInt;
  }
  
  public void setMaxWidth(int paramInt) {
    this.mMaxDimension[0] = paramInt;
  }
  
  public void setMinHeight(int paramInt) {
    if (paramInt < 0) {
      this.mMinHeight = 0;
      return;
    } 
    this.mMinHeight = paramInt;
  }
  
  public void setMinWidth(int paramInt) {
    if (paramInt < 0) {
      this.mMinWidth = 0;
      return;
    } 
    this.mMinWidth = paramInt;
  }
  
  public void setOffset(int paramInt1, int paramInt2) {
    this.mOffsetX = paramInt1;
    this.mOffsetY = paramInt2;
  }
  
  public void setOrigin(int paramInt1, int paramInt2) {
    this.mX = paramInt1;
    this.mY = paramInt2;
  }
  
  public void setParent(ConstraintWidget paramConstraintWidget) {
    this.mParent = paramConstraintWidget;
  }
  
  void setRelativePositioning(int paramInt1, int paramInt2) {
    if (paramInt2 == 0) {
      this.mRelX = paramInt1;
      return;
    } 
    if (paramInt2 == 1)
      this.mRelY = paramInt1; 
  }
  
  public void setType(String paramString) {
    this.mType = paramString;
  }
  
  public void setVerticalBiasPercent(float paramFloat) {
    this.mVerticalBiasPercent = paramFloat;
  }
  
  public void setVerticalChainStyle(int paramInt) {
    this.mVerticalChainStyle = paramInt;
  }
  
  public void setVerticalDimension(int paramInt1, int paramInt2) {
    this.mY = paramInt1;
    this.mHeight = paramInt2 - paramInt1;
    if (this.mHeight < this.mMinHeight)
      this.mHeight = this.mMinHeight; 
  }
  
  public void setVerticalDimensionBehaviour(DimensionBehaviour paramDimensionBehaviour) {
    this.mListDimensionBehaviors[1] = paramDimensionBehaviour;
    if (paramDimensionBehaviour == DimensionBehaviour.WRAP_CONTENT)
      setHeight(this.mWrapHeight); 
  }
  
  public void setVerticalMatchStyle(int paramInt1, int paramInt2, int paramInt3, float paramFloat) {
    this.mMatchConstraintDefaultHeight = paramInt1;
    this.mMatchConstraintMinHeight = paramInt2;
    this.mMatchConstraintMaxHeight = paramInt3;
    this.mMatchConstraintPercentHeight = paramFloat;
    if (paramFloat < 1.0F && this.mMatchConstraintDefaultHeight == 0)
      this.mMatchConstraintDefaultHeight = 2; 
  }
  
  public void setVerticalWeight(float paramFloat) {
    this.mWeight[1] = paramFloat;
  }
  
  public void setVisibility(int paramInt) {
    this.mVisibility = paramInt;
  }
  
  public void setWidth(int paramInt) {
    this.mWidth = paramInt;
    if (this.mWidth < this.mMinWidth)
      this.mWidth = this.mMinWidth; 
  }
  
  public void setWidthWrapContent(boolean paramBoolean) {
    this.mIsWidthWrapContent = paramBoolean;
  }
  
  public void setWrapHeight(int paramInt) {
    this.mWrapHeight = paramInt;
  }
  
  public void setWrapWidth(int paramInt) {
    this.mWrapWidth = paramInt;
  }
  
  public void setX(int paramInt) {
    this.mX = paramInt;
  }
  
  public void setY(int paramInt) {
    this.mY = paramInt;
  }
  
  public void setupDimensionRatio(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4) {
    if (this.mResolvedDimensionRatioSide == -1)
      if (paramBoolean3 && !paramBoolean4) {
        this.mResolvedDimensionRatioSide = 0;
      } else if (!paramBoolean3 && paramBoolean4) {
        this.mResolvedDimensionRatioSide = 1;
        if (this.mDimensionRatioSide == -1)
          this.mResolvedDimensionRatio = 1.0F / this.mResolvedDimensionRatio; 
      }  
    if (this.mResolvedDimensionRatioSide == 0 && (!this.mTop.isConnected() || !this.mBottom.isConnected())) {
      this.mResolvedDimensionRatioSide = 1;
    } else if (this.mResolvedDimensionRatioSide == 1 && (!this.mLeft.isConnected() || !this.mRight.isConnected())) {
      this.mResolvedDimensionRatioSide = 0;
    } 
    if (this.mResolvedDimensionRatioSide == -1 && (!this.mTop.isConnected() || !this.mBottom.isConnected() || !this.mLeft.isConnected() || !this.mRight.isConnected()))
      if (this.mTop.isConnected() && this.mBottom.isConnected()) {
        this.mResolvedDimensionRatioSide = 0;
      } else if (this.mLeft.isConnected() && this.mRight.isConnected()) {
        this.mResolvedDimensionRatio = 1.0F / this.mResolvedDimensionRatio;
        this.mResolvedDimensionRatioSide = 1;
      }  
    if (this.mResolvedDimensionRatioSide == -1)
      if (paramBoolean1 && !paramBoolean2) {
        this.mResolvedDimensionRatioSide = 0;
      } else if (!paramBoolean1 && paramBoolean2) {
        this.mResolvedDimensionRatio = 1.0F / this.mResolvedDimensionRatio;
        this.mResolvedDimensionRatioSide = 1;
      }  
    if (this.mResolvedDimensionRatioSide == -1)
      if (this.mMatchConstraintMinWidth > 0 && this.mMatchConstraintMinHeight == 0) {
        this.mResolvedDimensionRatioSide = 0;
      } else if (this.mMatchConstraintMinWidth == 0 && this.mMatchConstraintMinHeight > 0) {
        this.mResolvedDimensionRatio = 1.0F / this.mResolvedDimensionRatio;
        this.mResolvedDimensionRatioSide = 1;
      }  
    if (this.mResolvedDimensionRatioSide == -1 && paramBoolean1 && paramBoolean2) {
      this.mResolvedDimensionRatio = 1.0F / this.mResolvedDimensionRatio;
      this.mResolvedDimensionRatioSide = 1;
    } 
  }
  
  public String toString() {
    String str;
    StringBuilder stringBuilder = new StringBuilder();
    if (this.mType != null) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("type: ");
      stringBuilder1.append(this.mType);
      stringBuilder1.append(" ");
      str = stringBuilder1.toString();
    } else {
      str = "";
    } 
    stringBuilder.append(str);
    if (this.mDebugName != null) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("id: ");
      stringBuilder1.append(this.mDebugName);
      stringBuilder1.append(" ");
      String str1 = stringBuilder1.toString();
    } else {
      str = "";
    } 
    stringBuilder.append(str);
    stringBuilder.append("(");
    stringBuilder.append(this.mX);
    stringBuilder.append(", ");
    stringBuilder.append(this.mY);
    stringBuilder.append(") - (");
    stringBuilder.append(this.mWidth);
    stringBuilder.append(" x ");
    stringBuilder.append(this.mHeight);
    stringBuilder.append(") wrap: (");
    stringBuilder.append(this.mWrapWidth);
    stringBuilder.append(" x ");
    stringBuilder.append(this.mWrapHeight);
    stringBuilder.append(")");
    return stringBuilder.toString();
  }
  
  public void updateDrawPosition() {
    int i = this.mX;
    int j = this.mY;
    int k = this.mX;
    int m = this.mWidth;
    int n = this.mY;
    int i1 = this.mHeight;
    this.mDrawX = i;
    this.mDrawY = j;
    this.mDrawWidth = k + m - i;
    this.mDrawHeight = n + i1 - j;
  }
  
  public void updateFromSolver(LinearSystem paramLinearSystem) {
    // Byte code:
    //   0: aload_1
    //   1: aload_0
    //   2: getfield mLeft : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   5: invokevirtual getObjectVariableValue : (Ljava/lang/Object;)I
    //   8: istore_3
    //   9: aload_1
    //   10: aload_0
    //   11: getfield mTop : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   14: invokevirtual getObjectVariableValue : (Ljava/lang/Object;)I
    //   17: istore #4
    //   19: aload_1
    //   20: aload_0
    //   21: getfield mRight : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   24: invokevirtual getObjectVariableValue : (Ljava/lang/Object;)I
    //   27: istore #5
    //   29: aload_1
    //   30: aload_0
    //   31: getfield mBottom : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   34: invokevirtual getObjectVariableValue : (Ljava/lang/Object;)I
    //   37: istore #6
    //   39: iload #5
    //   41: iload_3
    //   42: isub
    //   43: iflt -> 115
    //   46: iload #6
    //   48: iload #4
    //   50: isub
    //   51: iflt -> 115
    //   54: iload_3
    //   55: ldc_w -2147483648
    //   58: if_icmpeq -> 115
    //   61: iload_3
    //   62: ldc 2147483647
    //   64: if_icmpeq -> 115
    //   67: iload #4
    //   69: ldc_w -2147483648
    //   72: if_icmpeq -> 115
    //   75: iload #4
    //   77: ldc 2147483647
    //   79: if_icmpeq -> 115
    //   82: iload #5
    //   84: ldc_w -2147483648
    //   87: if_icmpeq -> 115
    //   90: iload #5
    //   92: ldc 2147483647
    //   94: if_icmpeq -> 115
    //   97: iload #6
    //   99: ldc_w -2147483648
    //   102: if_icmpeq -> 115
    //   105: iload #6
    //   107: istore_2
    //   108: iload #6
    //   110: ldc 2147483647
    //   112: if_icmpne -> 125
    //   115: iconst_0
    //   116: istore_2
    //   117: iconst_0
    //   118: istore_3
    //   119: iconst_0
    //   120: istore #4
    //   122: iconst_0
    //   123: istore #5
    //   125: aload_0
    //   126: iload_3
    //   127: iload #4
    //   129: iload #5
    //   131: iload_2
    //   132: invokevirtual setFrame : (IIII)V
    //   135: return
  }
  
  public void updateResolutionNodes() {
    for (int i = 0; i < 6; i++)
      this.mListAnchors[i].getResolutionNode().update(); 
  }
  
  public enum ContentAlignment {
    BEGIN, BOTTOM, END, LEFT, MIDDLE, RIGHT, TOP, VERTICAL_MIDDLE;
    
    static {
      BOTTOM = new ContentAlignment("BOTTOM", 5);
      LEFT = new ContentAlignment("LEFT", 6);
      RIGHT = new ContentAlignment("RIGHT", 7);
      $VALUES = new ContentAlignment[] { BEGIN, MIDDLE, END, TOP, VERTICAL_MIDDLE, BOTTOM, LEFT, RIGHT };
    }
  }
  
  public enum DimensionBehaviour {
    FIXED, MATCH_CONSTRAINT, MATCH_PARENT, WRAP_CONTENT;
    
    static {
      $VALUES = new DimensionBehaviour[] { FIXED, WRAP_CONTENT, MATCH_CONSTRAINT, MATCH_PARENT };
    }
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\android\support\constraint\solver\widgets\ConstraintWidget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */