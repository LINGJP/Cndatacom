package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;

public class Optimizer {
  static final int FLAG_CHAIN_DANGLING = 1;
  
  static final int FLAG_RECOMPUTE_BOUNDS = 2;
  
  static final int FLAG_USE_OPTIMIZE = 0;
  
  public static final int OPTIMIZATION_BARRIER = 2;
  
  public static final int OPTIMIZATION_CHAIN = 4;
  
  public static final int OPTIMIZATION_DIMENSIONS = 8;
  
  public static final int OPTIMIZATION_DIRECT = 1;
  
  public static final int OPTIMIZATION_GROUPS = 32;
  
  public static final int OPTIMIZATION_NONE = 0;
  
  public static final int OPTIMIZATION_RATIO = 16;
  
  public static final int OPTIMIZATION_STANDARD = 7;
  
  static boolean[] flags = new boolean[3];
  
  static void analyze(int paramInt, ConstraintWidget paramConstraintWidget) {
    int i;
    paramConstraintWidget.updateResolutionNodes();
    ResolutionAnchor resolutionAnchor1 = paramConstraintWidget.mLeft.getResolutionNode();
    ResolutionAnchor resolutionAnchor2 = paramConstraintWidget.mTop.getResolutionNode();
    ResolutionAnchor resolutionAnchor3 = paramConstraintWidget.mRight.getResolutionNode();
    ResolutionAnchor resolutionAnchor4 = paramConstraintWidget.mBottom.getResolutionNode();
    if ((paramInt & 0x8) == 8) {
      paramInt = 1;
    } else {
      paramInt = 0;
    } 
    if (paramConstraintWidget.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && optimizableMatchConstraint(paramConstraintWidget, 0)) {
      i = 1;
    } else {
      i = 0;
    } 
    if (resolutionAnchor1.type != 4 && resolutionAnchor3.type != 4)
      if (paramConstraintWidget.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.FIXED || (i && paramConstraintWidget.getVisibility() == 8)) {
        if (paramConstraintWidget.mLeft.mTarget == null && paramConstraintWidget.mRight.mTarget == null) {
          resolutionAnchor1.setType(1);
          resolutionAnchor3.setType(1);
          if (paramInt != 0) {
            resolutionAnchor3.dependsOn(resolutionAnchor1, 1, paramConstraintWidget.getResolutionWidth());
          } else {
            resolutionAnchor3.dependsOn(resolutionAnchor1, paramConstraintWidget.getWidth());
          } 
        } else if (paramConstraintWidget.mLeft.mTarget != null && paramConstraintWidget.mRight.mTarget == null) {
          resolutionAnchor1.setType(1);
          resolutionAnchor3.setType(1);
          if (paramInt != 0) {
            resolutionAnchor3.dependsOn(resolutionAnchor1, 1, paramConstraintWidget.getResolutionWidth());
          } else {
            resolutionAnchor3.dependsOn(resolutionAnchor1, paramConstraintWidget.getWidth());
          } 
        } else if (paramConstraintWidget.mLeft.mTarget == null && paramConstraintWidget.mRight.mTarget != null) {
          resolutionAnchor1.setType(1);
          resolutionAnchor3.setType(1);
          resolutionAnchor1.dependsOn(resolutionAnchor3, -paramConstraintWidget.getWidth());
          if (paramInt != 0) {
            resolutionAnchor1.dependsOn(resolutionAnchor3, -1, paramConstraintWidget.getResolutionWidth());
          } else {
            resolutionAnchor1.dependsOn(resolutionAnchor3, -paramConstraintWidget.getWidth());
          } 
        } else if (paramConstraintWidget.mLeft.mTarget != null && paramConstraintWidget.mRight.mTarget != null) {
          resolutionAnchor1.setType(2);
          resolutionAnchor3.setType(2);
          if (paramInt != 0) {
            paramConstraintWidget.getResolutionWidth().addDependent(resolutionAnchor1);
            paramConstraintWidget.getResolutionWidth().addDependent(resolutionAnchor3);
            resolutionAnchor1.setOpposite(resolutionAnchor3, -1, paramConstraintWidget.getResolutionWidth());
            resolutionAnchor3.setOpposite(resolutionAnchor1, 1, paramConstraintWidget.getResolutionWidth());
          } else {
            resolutionAnchor1.setOpposite(resolutionAnchor3, -paramConstraintWidget.getWidth());
            resolutionAnchor3.setOpposite(resolutionAnchor1, paramConstraintWidget.getWidth());
          } 
        } 
      } else if (i) {
        i = paramConstraintWidget.getWidth();
        resolutionAnchor1.setType(1);
        resolutionAnchor3.setType(1);
        if (paramConstraintWidget.mLeft.mTarget == null && paramConstraintWidget.mRight.mTarget == null) {
          if (paramInt != 0) {
            resolutionAnchor3.dependsOn(resolutionAnchor1, 1, paramConstraintWidget.getResolutionWidth());
          } else {
            resolutionAnchor3.dependsOn(resolutionAnchor1, i);
          } 
        } else if (paramConstraintWidget.mLeft.mTarget != null && paramConstraintWidget.mRight.mTarget == null) {
          if (paramInt != 0) {
            resolutionAnchor3.dependsOn(resolutionAnchor1, 1, paramConstraintWidget.getResolutionWidth());
          } else {
            resolutionAnchor3.dependsOn(resolutionAnchor1, i);
          } 
        } else if (paramConstraintWidget.mLeft.mTarget == null && paramConstraintWidget.mRight.mTarget != null) {
          if (paramInt != 0) {
            resolutionAnchor1.dependsOn(resolutionAnchor3, -1, paramConstraintWidget.getResolutionWidth());
          } else {
            resolutionAnchor1.dependsOn(resolutionAnchor3, -i);
          } 
        } else if (paramConstraintWidget.mLeft.mTarget != null && paramConstraintWidget.mRight.mTarget != null) {
          if (paramInt != 0) {
            paramConstraintWidget.getResolutionWidth().addDependent(resolutionAnchor1);
            paramConstraintWidget.getResolutionWidth().addDependent(resolutionAnchor3);
          } 
          if (paramConstraintWidget.mDimensionRatio == 0.0F) {
            resolutionAnchor1.setType(3);
            resolutionAnchor3.setType(3);
            resolutionAnchor1.setOpposite(resolutionAnchor3, 0.0F);
            resolutionAnchor3.setOpposite(resolutionAnchor1, 0.0F);
          } else {
            resolutionAnchor1.setType(2);
            resolutionAnchor3.setType(2);
            resolutionAnchor1.setOpposite(resolutionAnchor3, -i);
            resolutionAnchor3.setOpposite(resolutionAnchor1, i);
            paramConstraintWidget.setWidth(i);
          } 
        } 
      }  
    if (paramConstraintWidget.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && optimizableMatchConstraint(paramConstraintWidget, 1)) {
      i = 1;
    } else {
      i = 0;
    } 
    if (resolutionAnchor2.type != 4 && resolutionAnchor4.type != 4) {
      if (paramConstraintWidget.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.FIXED || (i != 0 && paramConstraintWidget.getVisibility() == 8)) {
        if (paramConstraintWidget.mTop.mTarget == null && paramConstraintWidget.mBottom.mTarget == null) {
          resolutionAnchor2.setType(1);
          resolutionAnchor4.setType(1);
          if (paramInt != 0) {
            resolutionAnchor4.dependsOn(resolutionAnchor2, 1, paramConstraintWidget.getResolutionHeight());
          } else {
            resolutionAnchor4.dependsOn(resolutionAnchor2, paramConstraintWidget.getHeight());
          } 
          if (paramConstraintWidget.mBaseline.mTarget != null) {
            paramConstraintWidget.mBaseline.getResolutionNode().setType(1);
            resolutionAnchor2.dependsOn(1, paramConstraintWidget.mBaseline.getResolutionNode(), -paramConstraintWidget.mBaselineDistance);
            return;
          } 
        } else if (paramConstraintWidget.mTop.mTarget != null && paramConstraintWidget.mBottom.mTarget == null) {
          resolutionAnchor2.setType(1);
          resolutionAnchor4.setType(1);
          if (paramInt != 0) {
            resolutionAnchor4.dependsOn(resolutionAnchor2, 1, paramConstraintWidget.getResolutionHeight());
          } else {
            resolutionAnchor4.dependsOn(resolutionAnchor2, paramConstraintWidget.getHeight());
          } 
          if (paramConstraintWidget.mBaselineDistance > 0) {
            paramConstraintWidget.mBaseline.getResolutionNode().dependsOn(1, resolutionAnchor2, paramConstraintWidget.mBaselineDistance);
            return;
          } 
        } else if (paramConstraintWidget.mTop.mTarget == null && paramConstraintWidget.mBottom.mTarget != null) {
          resolutionAnchor2.setType(1);
          resolutionAnchor4.setType(1);
          if (paramInt != 0) {
            resolutionAnchor2.dependsOn(resolutionAnchor4, -1, paramConstraintWidget.getResolutionHeight());
          } else {
            resolutionAnchor2.dependsOn(resolutionAnchor4, -paramConstraintWidget.getHeight());
          } 
          if (paramConstraintWidget.mBaselineDistance > 0) {
            paramConstraintWidget.mBaseline.getResolutionNode().dependsOn(1, resolutionAnchor2, paramConstraintWidget.mBaselineDistance);
            return;
          } 
        } else if (paramConstraintWidget.mTop.mTarget != null && paramConstraintWidget.mBottom.mTarget != null) {
          resolutionAnchor2.setType(2);
          resolutionAnchor4.setType(2);
          if (paramInt != 0) {
            resolutionAnchor2.setOpposite(resolutionAnchor4, -1, paramConstraintWidget.getResolutionHeight());
            resolutionAnchor4.setOpposite(resolutionAnchor2, 1, paramConstraintWidget.getResolutionHeight());
            paramConstraintWidget.getResolutionHeight().addDependent(resolutionAnchor2);
            paramConstraintWidget.getResolutionWidth().addDependent(resolutionAnchor4);
          } else {
            resolutionAnchor2.setOpposite(resolutionAnchor4, -paramConstraintWidget.getHeight());
            resolutionAnchor4.setOpposite(resolutionAnchor2, paramConstraintWidget.getHeight());
          } 
          if (paramConstraintWidget.mBaselineDistance > 0)
            paramConstraintWidget.mBaseline.getResolutionNode().dependsOn(1, resolutionAnchor2, paramConstraintWidget.mBaselineDistance); 
        } 
        return;
      } 
      if (i != 0) {
        i = paramConstraintWidget.getHeight();
        resolutionAnchor2.setType(1);
        resolutionAnchor4.setType(1);
        if (paramConstraintWidget.mTop.mTarget == null && paramConstraintWidget.mBottom.mTarget == null) {
          if (paramInt != 0) {
            resolutionAnchor4.dependsOn(resolutionAnchor2, 1, paramConstraintWidget.getResolutionHeight());
            return;
          } 
          resolutionAnchor4.dependsOn(resolutionAnchor2, i);
          return;
        } 
        if (paramConstraintWidget.mTop.mTarget != null && paramConstraintWidget.mBottom.mTarget == null) {
          if (paramInt != 0) {
            resolutionAnchor4.dependsOn(resolutionAnchor2, 1, paramConstraintWidget.getResolutionHeight());
            return;
          } 
          resolutionAnchor4.dependsOn(resolutionAnchor2, i);
          return;
        } 
        if (paramConstraintWidget.mTop.mTarget == null && paramConstraintWidget.mBottom.mTarget != null) {
          if (paramInt != 0) {
            resolutionAnchor2.dependsOn(resolutionAnchor4, -1, paramConstraintWidget.getResolutionHeight());
            return;
          } 
          resolutionAnchor2.dependsOn(resolutionAnchor4, -i);
          return;
        } 
        if (paramConstraintWidget.mTop.mTarget != null && paramConstraintWidget.mBottom.mTarget != null) {
          if (paramInt != 0) {
            paramConstraintWidget.getResolutionHeight().addDependent(resolutionAnchor2);
            paramConstraintWidget.getResolutionWidth().addDependent(resolutionAnchor4);
          } 
          if (paramConstraintWidget.mDimensionRatio == 0.0F) {
            resolutionAnchor2.setType(3);
            resolutionAnchor4.setType(3);
            resolutionAnchor2.setOpposite(resolutionAnchor4, 0.0F);
            resolutionAnchor4.setOpposite(resolutionAnchor2, 0.0F);
            return;
          } 
          resolutionAnchor2.setType(2);
          resolutionAnchor4.setType(2);
          resolutionAnchor2.setOpposite(resolutionAnchor4, -i);
          resolutionAnchor4.setOpposite(resolutionAnchor2, i);
          paramConstraintWidget.setHeight(i);
          if (paramConstraintWidget.mBaselineDistance > 0) {
            paramConstraintWidget.mBaseline.getResolutionNode().dependsOn(1, resolutionAnchor2, paramConstraintWidget.mBaselineDistance);
            return;
          } 
        } 
      } 
    } 
  }
  
  static boolean applyChainOptimized(ConstraintWidgetContainer paramConstraintWidgetContainer, LinearSystem paramLinearSystem, int paramInt1, int paramInt2, ChainHead paramChainHead) {
    // Byte code:
    //   0: aload #4
    //   2: getfield mFirst : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   5: astore #18
    //   7: aload #4
    //   9: getfield mLast : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   12: astore #19
    //   14: aload #4
    //   16: getfield mFirstVisibleWidget : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   19: astore #21
    //   21: aload #4
    //   23: getfield mLastVisibleWidget : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   26: astore #22
    //   28: aload #4
    //   30: getfield mHead : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   33: astore #20
    //   35: aload #4
    //   37: getfield mTotalWeight : F
    //   40: fstore #10
    //   42: aload #4
    //   44: getfield mFirstMatchConstraintWidget : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   47: astore #23
    //   49: aload #4
    //   51: getfield mLastMatchConstraintWidget : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   54: astore #4
    //   56: aload_0
    //   57: getfield mListDimensionBehaviors : [Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   60: iload_2
    //   61: aaload
    //   62: astore_0
    //   63: getstatic android/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour.WRAP_CONTENT : Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   66: astore_0
    //   67: iload_2
    //   68: ifne -> 151
    //   71: aload #20
    //   73: getfield mHorizontalChainStyle : I
    //   76: ifne -> 85
    //   79: iconst_1
    //   80: istore #11
    //   82: goto -> 88
    //   85: iconst_0
    //   86: istore #11
    //   88: aload #20
    //   90: getfield mHorizontalChainStyle : I
    //   93: iconst_1
    //   94: if_icmpne -> 103
    //   97: iconst_1
    //   98: istore #12
    //   100: goto -> 106
    //   103: iconst_0
    //   104: istore #12
    //   106: iload #11
    //   108: istore #14
    //   110: iload #12
    //   112: istore #13
    //   114: aload #20
    //   116: getfield mHorizontalChainStyle : I
    //   119: iconst_2
    //   120: if_icmpne -> 141
    //   123: iload #12
    //   125: istore #13
    //   127: iload #11
    //   129: istore #14
    //   131: iconst_1
    //   132: istore #11
    //   134: iload #14
    //   136: istore #12
    //   138: goto -> 214
    //   141: iconst_0
    //   142: istore #11
    //   144: iload #14
    //   146: istore #12
    //   148: goto -> 214
    //   151: aload #20
    //   153: getfield mVerticalChainStyle : I
    //   156: ifne -> 165
    //   159: iconst_1
    //   160: istore #11
    //   162: goto -> 168
    //   165: iconst_0
    //   166: istore #11
    //   168: aload #20
    //   170: getfield mVerticalChainStyle : I
    //   173: iconst_1
    //   174: if_icmpne -> 183
    //   177: iconst_1
    //   178: istore #12
    //   180: goto -> 186
    //   183: iconst_0
    //   184: istore #12
    //   186: iload #11
    //   188: istore #14
    //   190: iload #12
    //   192: istore #13
    //   194: aload #20
    //   196: getfield mVerticalChainStyle : I
    //   199: iconst_2
    //   200: if_icmpne -> 141
    //   203: iload #11
    //   205: istore #14
    //   207: iload #12
    //   209: istore #13
    //   211: goto -> 131
    //   214: aload #18
    //   216: astore #4
    //   218: iconst_0
    //   219: istore #16
    //   221: iconst_0
    //   222: istore #15
    //   224: iconst_0
    //   225: istore #14
    //   227: fconst_0
    //   228: fstore #9
    //   230: fconst_0
    //   231: fstore #7
    //   233: iload #14
    //   235: ifne -> 607
    //   238: iload #15
    //   240: istore #17
    //   242: fload #9
    //   244: fstore #5
    //   246: fload #7
    //   248: fstore #6
    //   250: aload #4
    //   252: invokevirtual getVisibility : ()I
    //   255: bipush #8
    //   257: if_icmpeq -> 381
    //   260: iload #15
    //   262: iconst_1
    //   263: iadd
    //   264: istore #17
    //   266: iload_2
    //   267: ifne -> 284
    //   270: fload #9
    //   272: aload #4
    //   274: invokevirtual getWidth : ()I
    //   277: i2f
    //   278: fadd
    //   279: fstore #5
    //   281: goto -> 295
    //   284: fload #9
    //   286: aload #4
    //   288: invokevirtual getHeight : ()I
    //   291: i2f
    //   292: fadd
    //   293: fstore #5
    //   295: fload #5
    //   297: fstore #6
    //   299: aload #4
    //   301: aload #21
    //   303: if_acmpeq -> 322
    //   306: fload #5
    //   308: aload #4
    //   310: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   313: iload_3
    //   314: aaload
    //   315: invokevirtual getMargin : ()I
    //   318: i2f
    //   319: fadd
    //   320: fstore #6
    //   322: fload #6
    //   324: fstore #5
    //   326: aload #4
    //   328: aload #22
    //   330: if_acmpeq -> 351
    //   333: fload #6
    //   335: aload #4
    //   337: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   340: iload_3
    //   341: iconst_1
    //   342: iadd
    //   343: aaload
    //   344: invokevirtual getMargin : ()I
    //   347: i2f
    //   348: fadd
    //   349: fstore #5
    //   351: fload #7
    //   353: aload #4
    //   355: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   358: iload_3
    //   359: aaload
    //   360: invokevirtual getMargin : ()I
    //   363: i2f
    //   364: fadd
    //   365: aload #4
    //   367: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   370: iload_3
    //   371: iconst_1
    //   372: iadd
    //   373: aaload
    //   374: invokevirtual getMargin : ()I
    //   377: i2f
    //   378: fadd
    //   379: fstore #6
    //   381: aload #4
    //   383: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   386: iload_3
    //   387: aaload
    //   388: astore_0
    //   389: iload #16
    //   391: istore #15
    //   393: aload #4
    //   395: invokevirtual getVisibility : ()I
    //   398: bipush #8
    //   400: if_icmpeq -> 500
    //   403: iload #16
    //   405: istore #15
    //   407: aload #4
    //   409: getfield mListDimensionBehaviors : [Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   412: iload_2
    //   413: aaload
    //   414: getstatic android/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT : Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   417: if_acmpne -> 500
    //   420: iload #16
    //   422: iconst_1
    //   423: iadd
    //   424: istore #15
    //   426: iload_2
    //   427: ifne -> 458
    //   430: aload #4
    //   432: getfield mMatchConstraintDefaultWidth : I
    //   435: ifeq -> 440
    //   438: iconst_0
    //   439: ireturn
    //   440: aload #4
    //   442: getfield mMatchConstraintMinWidth : I
    //   445: ifne -> 456
    //   448: aload #4
    //   450: getfield mMatchConstraintMaxWidth : I
    //   453: ifeq -> 486
    //   456: iconst_0
    //   457: ireturn
    //   458: aload #4
    //   460: getfield mMatchConstraintDefaultHeight : I
    //   463: ifeq -> 468
    //   466: iconst_0
    //   467: ireturn
    //   468: aload #4
    //   470: getfield mMatchConstraintMinHeight : I
    //   473: ifne -> 498
    //   476: aload #4
    //   478: getfield mMatchConstraintMaxHeight : I
    //   481: ifeq -> 486
    //   484: iconst_0
    //   485: ireturn
    //   486: aload #4
    //   488: getfield mDimensionRatio : F
    //   491: fconst_0
    //   492: fcmpl
    //   493: ifeq -> 500
    //   496: iconst_0
    //   497: ireturn
    //   498: iconst_0
    //   499: ireturn
    //   500: aload #4
    //   502: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   505: iload_3
    //   506: iconst_1
    //   507: iadd
    //   508: aaload
    //   509: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   512: astore_0
    //   513: aload_0
    //   514: ifnull -> 557
    //   517: aload_0
    //   518: getfield mOwner : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   521: astore_0
    //   522: aload_0
    //   523: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   526: iload_3
    //   527: aaload
    //   528: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   531: ifnull -> 557
    //   534: aload_0
    //   535: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   538: iload_3
    //   539: aaload
    //   540: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   543: getfield mOwner : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   546: aload #4
    //   548: if_acmpeq -> 554
    //   551: goto -> 557
    //   554: goto -> 559
    //   557: aconst_null
    //   558: astore_0
    //   559: aload_0
    //   560: ifnull -> 585
    //   563: iload #15
    //   565: istore #16
    //   567: iload #17
    //   569: istore #15
    //   571: aload_0
    //   572: astore #4
    //   574: fload #5
    //   576: fstore #9
    //   578: fload #6
    //   580: fstore #7
    //   582: goto -> 233
    //   585: iconst_1
    //   586: istore #14
    //   588: iload #15
    //   590: istore #16
    //   592: iload #17
    //   594: istore #15
    //   596: fload #5
    //   598: fstore #9
    //   600: fload #6
    //   602: fstore #7
    //   604: goto -> 233
    //   607: aload #18
    //   609: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   612: iload_3
    //   613: aaload
    //   614: invokevirtual getResolutionNode : ()Landroid/support/constraint/solver/widgets/ResolutionAnchor;
    //   617: astore #20
    //   619: aload #19
    //   621: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   624: astore_0
    //   625: iload_3
    //   626: iconst_1
    //   627: iadd
    //   628: istore #14
    //   630: aload_0
    //   631: iload #14
    //   633: aaload
    //   634: invokevirtual getResolutionNode : ()Landroid/support/constraint/solver/widgets/ResolutionAnchor;
    //   637: astore_0
    //   638: aload #20
    //   640: getfield target : Landroid/support/constraint/solver/widgets/ResolutionAnchor;
    //   643: ifnull -> 1854
    //   646: aload_0
    //   647: getfield target : Landroid/support/constraint/solver/widgets/ResolutionAnchor;
    //   650: ifnonnull -> 656
    //   653: goto -> 1854
    //   656: aload #20
    //   658: getfield target : Landroid/support/constraint/solver/widgets/ResolutionAnchor;
    //   661: getfield state : I
    //   664: iconst_1
    //   665: if_icmpne -> 1852
    //   668: aload_0
    //   669: getfield target : Landroid/support/constraint/solver/widgets/ResolutionAnchor;
    //   672: getfield state : I
    //   675: iconst_1
    //   676: if_icmpeq -> 682
    //   679: goto -> 1852
    //   682: iload #16
    //   684: ifle -> 696
    //   687: iload #16
    //   689: iload #15
    //   691: if_icmpeq -> 696
    //   694: iconst_0
    //   695: ireturn
    //   696: iload #11
    //   698: ifne -> 720
    //   701: iload #12
    //   703: ifne -> 720
    //   706: iload #13
    //   708: ifeq -> 714
    //   711: goto -> 720
    //   714: fconst_0
    //   715: fstore #5
    //   717: goto -> 770
    //   720: aload #21
    //   722: ifnull -> 741
    //   725: aload #21
    //   727: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   730: iload_3
    //   731: aaload
    //   732: invokevirtual getMargin : ()I
    //   735: i2f
    //   736: fstore #6
    //   738: goto -> 744
    //   741: fconst_0
    //   742: fstore #6
    //   744: fload #6
    //   746: fstore #5
    //   748: aload #22
    //   750: ifnull -> 770
    //   753: fload #6
    //   755: aload #22
    //   757: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   760: iload #14
    //   762: aaload
    //   763: invokevirtual getMargin : ()I
    //   766: i2f
    //   767: fadd
    //   768: fstore #5
    //   770: aload #20
    //   772: getfield target : Landroid/support/constraint/solver/widgets/ResolutionAnchor;
    //   775: getfield resolvedOffset : F
    //   778: fstore #8
    //   780: aload_0
    //   781: getfield target : Landroid/support/constraint/solver/widgets/ResolutionAnchor;
    //   784: getfield resolvedOffset : F
    //   787: fstore #6
    //   789: fload #8
    //   791: fload #6
    //   793: fcmpg
    //   794: ifge -> 810
    //   797: fload #6
    //   799: fload #8
    //   801: fsub
    //   802: fload #9
    //   804: fsub
    //   805: fstore #6
    //   807: goto -> 820
    //   810: fload #8
    //   812: fload #6
    //   814: fsub
    //   815: fload #9
    //   817: fsub
    //   818: fstore #6
    //   820: iload #16
    //   822: ifle -> 1136
    //   825: iload #16
    //   827: iload #15
    //   829: if_icmpne -> 1136
    //   832: aload #4
    //   834: invokevirtual getParent : ()Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   837: ifnull -> 858
    //   840: aload #4
    //   842: invokevirtual getParent : ()Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   845: getfield mListDimensionBehaviors : [Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   848: iload_2
    //   849: aaload
    //   850: getstatic android/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour.WRAP_CONTENT : Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   853: if_acmpne -> 858
    //   856: iconst_0
    //   857: ireturn
    //   858: fload #6
    //   860: fload #9
    //   862: fadd
    //   863: fload #7
    //   865: fsub
    //   866: fstore #6
    //   868: aload #18
    //   870: astore_0
    //   871: aload_0
    //   872: ifnull -> 1134
    //   875: getstatic android/support/constraint/solver/LinearSystem.sMetrics : Landroid/support/constraint/solver/Metrics;
    //   878: ifnull -> 932
    //   881: getstatic android/support/constraint/solver/LinearSystem.sMetrics : Landroid/support/constraint/solver/Metrics;
    //   884: astore #4
    //   886: aload #4
    //   888: aload #4
    //   890: getfield nonresolvedWidgets : J
    //   893: lconst_1
    //   894: lsub
    //   895: putfield nonresolvedWidgets : J
    //   898: getstatic android/support/constraint/solver/LinearSystem.sMetrics : Landroid/support/constraint/solver/Metrics;
    //   901: astore #4
    //   903: aload #4
    //   905: aload #4
    //   907: getfield resolvedWidgets : J
    //   910: lconst_1
    //   911: ladd
    //   912: putfield resolvedWidgets : J
    //   915: getstatic android/support/constraint/solver/LinearSystem.sMetrics : Landroid/support/constraint/solver/Metrics;
    //   918: astore #4
    //   920: aload #4
    //   922: aload #4
    //   924: getfield chainConnectionResolved : J
    //   927: lconst_1
    //   928: ladd
    //   929: putfield chainConnectionResolved : J
    //   932: aload_0
    //   933: getfield mNextChainWidget : [Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   936: iload_2
    //   937: aaload
    //   938: astore #4
    //   940: aload #4
    //   942: ifnonnull -> 957
    //   945: aload_0
    //   946: aload #19
    //   948: if_acmpne -> 954
    //   951: goto -> 957
    //   954: goto -> 1128
    //   957: fload #6
    //   959: iload #16
    //   961: i2f
    //   962: fdiv
    //   963: fstore #5
    //   965: fload #10
    //   967: fconst_0
    //   968: fcmpl
    //   969: ifle -> 1004
    //   972: aload_0
    //   973: getfield mWeight : [F
    //   976: iload_2
    //   977: faload
    //   978: ldc -1.0
    //   980: fcmpl
    //   981: ifne -> 990
    //   984: fconst_0
    //   985: fstore #5
    //   987: goto -> 1004
    //   990: aload_0
    //   991: getfield mWeight : [F
    //   994: iload_2
    //   995: faload
    //   996: fload #6
    //   998: fmul
    //   999: fload #10
    //   1001: fdiv
    //   1002: fstore #5
    //   1004: aload_0
    //   1005: invokevirtual getVisibility : ()I
    //   1008: bipush #8
    //   1010: if_icmpne -> 1016
    //   1013: fconst_0
    //   1014: fstore #5
    //   1016: fload #8
    //   1018: aload_0
    //   1019: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1022: iload_3
    //   1023: aaload
    //   1024: invokevirtual getMargin : ()I
    //   1027: i2f
    //   1028: fadd
    //   1029: fstore #7
    //   1031: aload_0
    //   1032: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1035: iload_3
    //   1036: aaload
    //   1037: invokevirtual getResolutionNode : ()Landroid/support/constraint/solver/widgets/ResolutionAnchor;
    //   1040: aload #20
    //   1042: getfield resolvedTarget : Landroid/support/constraint/solver/widgets/ResolutionAnchor;
    //   1045: fload #7
    //   1047: invokevirtual resolve : (Landroid/support/constraint/solver/widgets/ResolutionAnchor;F)V
    //   1050: aload_0
    //   1051: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1054: iload #14
    //   1056: aaload
    //   1057: invokevirtual getResolutionNode : ()Landroid/support/constraint/solver/widgets/ResolutionAnchor;
    //   1060: astore #18
    //   1062: aload #20
    //   1064: getfield resolvedTarget : Landroid/support/constraint/solver/widgets/ResolutionAnchor;
    //   1067: astore #21
    //   1069: fload #7
    //   1071: fload #5
    //   1073: fadd
    //   1074: fstore #5
    //   1076: aload #18
    //   1078: aload #21
    //   1080: fload #5
    //   1082: invokevirtual resolve : (Landroid/support/constraint/solver/widgets/ResolutionAnchor;F)V
    //   1085: aload_0
    //   1086: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1089: iload_3
    //   1090: aaload
    //   1091: invokevirtual getResolutionNode : ()Landroid/support/constraint/solver/widgets/ResolutionAnchor;
    //   1094: aload_1
    //   1095: invokevirtual addResolvedValue : (Landroid/support/constraint/solver/LinearSystem;)V
    //   1098: aload_0
    //   1099: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1102: iload #14
    //   1104: aaload
    //   1105: invokevirtual getResolutionNode : ()Landroid/support/constraint/solver/widgets/ResolutionAnchor;
    //   1108: aload_1
    //   1109: invokevirtual addResolvedValue : (Landroid/support/constraint/solver/LinearSystem;)V
    //   1112: fload #5
    //   1114: aload_0
    //   1115: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1118: iload #14
    //   1120: aaload
    //   1121: invokevirtual getMargin : ()I
    //   1124: i2f
    //   1125: fadd
    //   1126: fstore #8
    //   1128: aload #4
    //   1130: astore_0
    //   1131: goto -> 871
    //   1134: iconst_1
    //   1135: ireturn
    //   1136: fload #6
    //   1138: fconst_0
    //   1139: fcmpg
    //   1140: ifge -> 1152
    //   1143: iconst_1
    //   1144: istore #11
    //   1146: iconst_0
    //   1147: istore #12
    //   1149: iconst_0
    //   1150: istore #13
    //   1152: iload #11
    //   1154: ifeq -> 1405
    //   1157: aload #18
    //   1159: astore_0
    //   1160: fload #8
    //   1162: fload #6
    //   1164: fload #5
    //   1166: fsub
    //   1167: aload_0
    //   1168: iload_2
    //   1169: invokevirtual getBiasPercent : (I)F
    //   1172: fmul
    //   1173: fadd
    //   1174: fstore #5
    //   1176: aload_0
    //   1177: ifnull -> 1403
    //   1180: getstatic android/support/constraint/solver/LinearSystem.sMetrics : Landroid/support/constraint/solver/Metrics;
    //   1183: ifnull -> 1237
    //   1186: getstatic android/support/constraint/solver/LinearSystem.sMetrics : Landroid/support/constraint/solver/Metrics;
    //   1189: astore #4
    //   1191: aload #4
    //   1193: aload #4
    //   1195: getfield nonresolvedWidgets : J
    //   1198: lconst_1
    //   1199: lsub
    //   1200: putfield nonresolvedWidgets : J
    //   1203: getstatic android/support/constraint/solver/LinearSystem.sMetrics : Landroid/support/constraint/solver/Metrics;
    //   1206: astore #4
    //   1208: aload #4
    //   1210: aload #4
    //   1212: getfield resolvedWidgets : J
    //   1215: lconst_1
    //   1216: ladd
    //   1217: putfield resolvedWidgets : J
    //   1220: getstatic android/support/constraint/solver/LinearSystem.sMetrics : Landroid/support/constraint/solver/Metrics;
    //   1223: astore #4
    //   1225: aload #4
    //   1227: aload #4
    //   1229: getfield chainConnectionResolved : J
    //   1232: lconst_1
    //   1233: ladd
    //   1234: putfield chainConnectionResolved : J
    //   1237: aload_0
    //   1238: getfield mNextChainWidget : [Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   1241: iload_2
    //   1242: aaload
    //   1243: astore #4
    //   1245: aload #4
    //   1247: ifnonnull -> 1260
    //   1250: fload #5
    //   1252: fstore #6
    //   1254: aload_0
    //   1255: aload #19
    //   1257: if_acmpne -> 1393
    //   1260: iload_2
    //   1261: ifne -> 1274
    //   1264: aload_0
    //   1265: invokevirtual getWidth : ()I
    //   1268: i2f
    //   1269: fstore #6
    //   1271: goto -> 1281
    //   1274: aload_0
    //   1275: invokevirtual getHeight : ()I
    //   1278: i2f
    //   1279: fstore #6
    //   1281: fload #5
    //   1283: aload_0
    //   1284: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1287: iload_3
    //   1288: aaload
    //   1289: invokevirtual getMargin : ()I
    //   1292: i2f
    //   1293: fadd
    //   1294: fstore #5
    //   1296: aload_0
    //   1297: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1300: iload_3
    //   1301: aaload
    //   1302: invokevirtual getResolutionNode : ()Landroid/support/constraint/solver/widgets/ResolutionAnchor;
    //   1305: aload #20
    //   1307: getfield resolvedTarget : Landroid/support/constraint/solver/widgets/ResolutionAnchor;
    //   1310: fload #5
    //   1312: invokevirtual resolve : (Landroid/support/constraint/solver/widgets/ResolutionAnchor;F)V
    //   1315: aload_0
    //   1316: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1319: iload #14
    //   1321: aaload
    //   1322: invokevirtual getResolutionNode : ()Landroid/support/constraint/solver/widgets/ResolutionAnchor;
    //   1325: astore #18
    //   1327: aload #20
    //   1329: getfield resolvedTarget : Landroid/support/constraint/solver/widgets/ResolutionAnchor;
    //   1332: astore #21
    //   1334: fload #5
    //   1336: fload #6
    //   1338: fadd
    //   1339: fstore #5
    //   1341: aload #18
    //   1343: aload #21
    //   1345: fload #5
    //   1347: invokevirtual resolve : (Landroid/support/constraint/solver/widgets/ResolutionAnchor;F)V
    //   1350: aload_0
    //   1351: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1354: iload_3
    //   1355: aaload
    //   1356: invokevirtual getResolutionNode : ()Landroid/support/constraint/solver/widgets/ResolutionAnchor;
    //   1359: aload_1
    //   1360: invokevirtual addResolvedValue : (Landroid/support/constraint/solver/LinearSystem;)V
    //   1363: aload_0
    //   1364: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1367: iload #14
    //   1369: aaload
    //   1370: invokevirtual getResolutionNode : ()Landroid/support/constraint/solver/widgets/ResolutionAnchor;
    //   1373: aload_1
    //   1374: invokevirtual addResolvedValue : (Landroid/support/constraint/solver/LinearSystem;)V
    //   1377: fload #5
    //   1379: aload_0
    //   1380: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1383: iload #14
    //   1385: aaload
    //   1386: invokevirtual getMargin : ()I
    //   1389: i2f
    //   1390: fadd
    //   1391: fstore #6
    //   1393: aload #4
    //   1395: astore_0
    //   1396: fload #6
    //   1398: fstore #5
    //   1400: goto -> 1176
    //   1403: iconst_1
    //   1404: ireturn
    //   1405: iload #12
    //   1407: ifne -> 1415
    //   1410: iload #13
    //   1412: ifeq -> 1403
    //   1415: iload #12
    //   1417: ifeq -> 1430
    //   1420: fload #6
    //   1422: fload #5
    //   1424: fsub
    //   1425: fstore #7
    //   1427: goto -> 1446
    //   1430: fload #6
    //   1432: fstore #7
    //   1434: iload #13
    //   1436: ifeq -> 1446
    //   1439: fload #6
    //   1441: fload #5
    //   1443: fsub
    //   1444: fstore #7
    //   1446: fload #7
    //   1448: iload #15
    //   1450: iconst_1
    //   1451: iadd
    //   1452: i2f
    //   1453: fdiv
    //   1454: fstore #6
    //   1456: iload #13
    //   1458: ifeq -> 1486
    //   1461: iload #15
    //   1463: iconst_1
    //   1464: if_icmple -> 1480
    //   1467: fload #7
    //   1469: iload #15
    //   1471: iconst_1
    //   1472: isub
    //   1473: i2f
    //   1474: fdiv
    //   1475: fstore #6
    //   1477: goto -> 1486
    //   1480: fload #7
    //   1482: fconst_2
    //   1483: fdiv
    //   1484: fstore #6
    //   1486: aload #18
    //   1488: invokevirtual getVisibility : ()I
    //   1491: bipush #8
    //   1493: if_icmpeq -> 1506
    //   1496: fload #8
    //   1498: fload #6
    //   1500: fadd
    //   1501: fstore #5
    //   1503: goto -> 1510
    //   1506: fload #8
    //   1508: fstore #5
    //   1510: fload #5
    //   1512: fstore #7
    //   1514: iload #13
    //   1516: ifeq -> 1545
    //   1519: fload #5
    //   1521: fstore #7
    //   1523: iload #15
    //   1525: iconst_1
    //   1526: if_icmple -> 1545
    //   1529: aload #21
    //   1531: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1534: iload_3
    //   1535: aaload
    //   1536: invokevirtual getMargin : ()I
    //   1539: i2f
    //   1540: fload #8
    //   1542: fadd
    //   1543: fstore #7
    //   1545: aload #18
    //   1547: astore_0
    //   1548: fload #7
    //   1550: fstore #5
    //   1552: iload #12
    //   1554: ifeq -> 1588
    //   1557: aload #18
    //   1559: astore_0
    //   1560: fload #7
    //   1562: fstore #5
    //   1564: aload #21
    //   1566: ifnull -> 1588
    //   1569: fload #7
    //   1571: aload #21
    //   1573: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1576: iload_3
    //   1577: aaload
    //   1578: invokevirtual getMargin : ()I
    //   1581: i2f
    //   1582: fadd
    //   1583: fstore #5
    //   1585: aload #18
    //   1587: astore_0
    //   1588: aload_0
    //   1589: ifnull -> 1403
    //   1592: getstatic android/support/constraint/solver/LinearSystem.sMetrics : Landroid/support/constraint/solver/Metrics;
    //   1595: ifnull -> 1649
    //   1598: getstatic android/support/constraint/solver/LinearSystem.sMetrics : Landroid/support/constraint/solver/Metrics;
    //   1601: astore #4
    //   1603: aload #4
    //   1605: aload #4
    //   1607: getfield nonresolvedWidgets : J
    //   1610: lconst_1
    //   1611: lsub
    //   1612: putfield nonresolvedWidgets : J
    //   1615: getstatic android/support/constraint/solver/LinearSystem.sMetrics : Landroid/support/constraint/solver/Metrics;
    //   1618: astore #4
    //   1620: aload #4
    //   1622: aload #4
    //   1624: getfield resolvedWidgets : J
    //   1627: lconst_1
    //   1628: ladd
    //   1629: putfield resolvedWidgets : J
    //   1632: getstatic android/support/constraint/solver/LinearSystem.sMetrics : Landroid/support/constraint/solver/Metrics;
    //   1635: astore #4
    //   1637: aload #4
    //   1639: aload #4
    //   1641: getfield chainConnectionResolved : J
    //   1644: lconst_1
    //   1645: ladd
    //   1646: putfield chainConnectionResolved : J
    //   1649: aload_0
    //   1650: getfield mNextChainWidget : [Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   1653: iload_2
    //   1654: aaload
    //   1655: astore #4
    //   1657: aload #4
    //   1659: ifnonnull -> 1682
    //   1662: fload #5
    //   1664: fstore #7
    //   1666: aload_0
    //   1667: aload #19
    //   1669: if_acmpne -> 1675
    //   1672: goto -> 1682
    //   1675: fload #7
    //   1677: fstore #5
    //   1679: goto -> 1846
    //   1682: iload_2
    //   1683: ifne -> 1696
    //   1686: aload_0
    //   1687: invokevirtual getWidth : ()I
    //   1690: i2f
    //   1691: fstore #7
    //   1693: goto -> 1703
    //   1696: aload_0
    //   1697: invokevirtual getHeight : ()I
    //   1700: i2f
    //   1701: fstore #7
    //   1703: fload #5
    //   1705: fstore #8
    //   1707: aload_0
    //   1708: aload #21
    //   1710: if_acmpeq -> 1728
    //   1713: fload #5
    //   1715: aload_0
    //   1716: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1719: iload_3
    //   1720: aaload
    //   1721: invokevirtual getMargin : ()I
    //   1724: i2f
    //   1725: fadd
    //   1726: fstore #8
    //   1728: aload_0
    //   1729: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1732: iload_3
    //   1733: aaload
    //   1734: invokevirtual getResolutionNode : ()Landroid/support/constraint/solver/widgets/ResolutionAnchor;
    //   1737: aload #20
    //   1739: getfield resolvedTarget : Landroid/support/constraint/solver/widgets/ResolutionAnchor;
    //   1742: fload #8
    //   1744: invokevirtual resolve : (Landroid/support/constraint/solver/widgets/ResolutionAnchor;F)V
    //   1747: aload_0
    //   1748: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1751: iload #14
    //   1753: aaload
    //   1754: invokevirtual getResolutionNode : ()Landroid/support/constraint/solver/widgets/ResolutionAnchor;
    //   1757: aload #20
    //   1759: getfield resolvedTarget : Landroid/support/constraint/solver/widgets/ResolutionAnchor;
    //   1762: fload #8
    //   1764: fload #7
    //   1766: fadd
    //   1767: invokevirtual resolve : (Landroid/support/constraint/solver/widgets/ResolutionAnchor;F)V
    //   1770: aload_0
    //   1771: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1774: iload_3
    //   1775: aaload
    //   1776: invokevirtual getResolutionNode : ()Landroid/support/constraint/solver/widgets/ResolutionAnchor;
    //   1779: aload_1
    //   1780: invokevirtual addResolvedValue : (Landroid/support/constraint/solver/LinearSystem;)V
    //   1783: aload_0
    //   1784: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1787: iload #14
    //   1789: aaload
    //   1790: invokevirtual getResolutionNode : ()Landroid/support/constraint/solver/widgets/ResolutionAnchor;
    //   1793: aload_1
    //   1794: invokevirtual addResolvedValue : (Landroid/support/constraint/solver/LinearSystem;)V
    //   1797: fload #8
    //   1799: fload #7
    //   1801: aload_0
    //   1802: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1805: iload #14
    //   1807: aaload
    //   1808: invokevirtual getMargin : ()I
    //   1811: i2f
    //   1812: fadd
    //   1813: fadd
    //   1814: fstore #8
    //   1816: fload #8
    //   1818: fstore #7
    //   1820: aload #4
    //   1822: ifnull -> 1675
    //   1825: fload #8
    //   1827: fstore #5
    //   1829: aload #4
    //   1831: invokevirtual getVisibility : ()I
    //   1834: bipush #8
    //   1836: if_icmpeq -> 1846
    //   1839: fload #8
    //   1841: fload #6
    //   1843: fadd
    //   1844: fstore #5
    //   1846: aload #4
    //   1848: astore_0
    //   1849: goto -> 1588
    //   1852: iconst_0
    //   1853: ireturn
    //   1854: iconst_0
    //   1855: ireturn
  }
  
  static void checkMatchParent(ConstraintWidgetContainer paramConstraintWidgetContainer, LinearSystem paramLinearSystem, ConstraintWidget paramConstraintWidget) {
    if (paramConstraintWidgetContainer.mListDimensionBehaviors[0] != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT && paramConstraintWidget.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
      int i = paramConstraintWidget.mLeft.mMargin;
      int j = paramConstraintWidgetContainer.getWidth() - paramConstraintWidget.mRight.mMargin;
      paramConstraintWidget.mLeft.mSolverVariable = paramLinearSystem.createObjectVariable(paramConstraintWidget.mLeft);
      paramConstraintWidget.mRight.mSolverVariable = paramLinearSystem.createObjectVariable(paramConstraintWidget.mRight);
      paramLinearSystem.addEquality(paramConstraintWidget.mLeft.mSolverVariable, i);
      paramLinearSystem.addEquality(paramConstraintWidget.mRight.mSolverVariable, j);
      paramConstraintWidget.mHorizontalResolution = 2;
      paramConstraintWidget.setHorizontalDimension(i, j);
    } 
    if (paramConstraintWidgetContainer.mListDimensionBehaviors[1] != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT && paramConstraintWidget.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
      int i = paramConstraintWidget.mTop.mMargin;
      int j = paramConstraintWidgetContainer.getHeight() - paramConstraintWidget.mBottom.mMargin;
      paramConstraintWidget.mTop.mSolverVariable = paramLinearSystem.createObjectVariable(paramConstraintWidget.mTop);
      paramConstraintWidget.mBottom.mSolverVariable = paramLinearSystem.createObjectVariable(paramConstraintWidget.mBottom);
      paramLinearSystem.addEquality(paramConstraintWidget.mTop.mSolverVariable, i);
      paramLinearSystem.addEquality(paramConstraintWidget.mBottom.mSolverVariable, j);
      if (paramConstraintWidget.mBaselineDistance > 0 || paramConstraintWidget.getVisibility() == 8) {
        paramConstraintWidget.mBaseline.mSolverVariable = paramLinearSystem.createObjectVariable(paramConstraintWidget.mBaseline);
        paramLinearSystem.addEquality(paramConstraintWidget.mBaseline.mSolverVariable, paramConstraintWidget.mBaselineDistance + i);
      } 
      paramConstraintWidget.mVerticalResolution = 2;
      paramConstraintWidget.setVerticalDimension(i, j);
    } 
  }
  
  private static boolean optimizableMatchConstraint(ConstraintWidget paramConstraintWidget, int paramInt) {
    ConstraintWidget.DimensionBehaviour[] arrayOfDimensionBehaviour;
    if (paramConstraintWidget.mListDimensionBehaviors[paramInt] != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT)
      return false; 
    float f = paramConstraintWidget.mDimensionRatio;
    boolean bool = true;
    if (f != 0.0F) {
      arrayOfDimensionBehaviour = paramConstraintWidget.mListDimensionBehaviors;
      if (paramInt == 0) {
        paramInt = bool;
      } else {
        paramInt = 0;
      } 
      return (arrayOfDimensionBehaviour[paramInt] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) ? false : false;
    } 
    if (paramInt == 0) {
      if (((ConstraintWidget)arrayOfDimensionBehaviour).mMatchConstraintDefaultWidth != 0)
        return false; 
      if (((ConstraintWidget)arrayOfDimensionBehaviour).mMatchConstraintMinWidth != 0 || ((ConstraintWidget)arrayOfDimensionBehaviour).mMatchConstraintMaxWidth != 0)
        return false; 
    } else {
      return (((ConstraintWidget)arrayOfDimensionBehaviour).mMatchConstraintDefaultHeight != 0) ? false : ((((ConstraintWidget)arrayOfDimensionBehaviour).mMatchConstraintMinHeight == 0) ? (!(((ConstraintWidget)arrayOfDimensionBehaviour).mMatchConstraintMaxHeight != 0)) : false);
    } 
    return true;
  }
  
  static void setOptimizedWidget(ConstraintWidget paramConstraintWidget, int paramInt1, int paramInt2) {
    int i = paramInt1 * 2;
    int j = i + 1;
    (paramConstraintWidget.mListAnchors[i].getResolutionNode()).resolvedTarget = (paramConstraintWidget.getParent()).mLeft.getResolutionNode();
    (paramConstraintWidget.mListAnchors[i].getResolutionNode()).resolvedOffset = paramInt2;
    (paramConstraintWidget.mListAnchors[i].getResolutionNode()).state = 1;
    (paramConstraintWidget.mListAnchors[j].getResolutionNode()).resolvedTarget = paramConstraintWidget.mListAnchors[i].getResolutionNode();
    (paramConstraintWidget.mListAnchors[j].getResolutionNode()).resolvedOffset = paramConstraintWidget.getLength(paramInt1);
    (paramConstraintWidget.mListAnchors[j].getResolutionNode()).state = 1;
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\android\support\constraint\solver\widgets\Optimizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */