package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;

class Chain {
  private static final boolean DEBUG = false;
  
  static void applyChainConstraints(ConstraintWidgetContainer paramConstraintWidgetContainer, LinearSystem paramLinearSystem, int paramInt) {
    byte b;
    int i;
    ChainHead[] arrayOfChainHead;
    int j = 0;
    if (paramInt == 0) {
      i = paramConstraintWidgetContainer.mHorizontalChainsSize;
      arrayOfChainHead = paramConstraintWidgetContainer.mHorizontalChainsArray;
      b = 0;
    } else {
      b = 2;
      i = paramConstraintWidgetContainer.mVerticalChainsSize;
      arrayOfChainHead = paramConstraintWidgetContainer.mVerticalChainsArray;
    } 
    while (j < i) {
      ChainHead chainHead = arrayOfChainHead[j];
      chainHead.define();
      if (paramConstraintWidgetContainer.optimizeFor(4)) {
        if (!Optimizer.applyChainOptimized(paramConstraintWidgetContainer, paramLinearSystem, paramInt, b, chainHead))
          applyChainConstraints(paramConstraintWidgetContainer, paramLinearSystem, paramInt, b, chainHead); 
      } else {
        applyChainConstraints(paramConstraintWidgetContainer, paramLinearSystem, paramInt, b, chainHead);
      } 
      j++;
    } 
  }
  
  static void applyChainConstraints(ConstraintWidgetContainer paramConstraintWidgetContainer, LinearSystem paramLinearSystem, int paramInt1, int paramInt2, ChainHead paramChainHead) {
    // Byte code:
    //   0: aload #4
    //   2: getfield mFirst : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   5: astore #17
    //   7: aload #4
    //   9: getfield mLast : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   12: astore #16
    //   14: aload #4
    //   16: getfield mFirstVisibleWidget : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   19: astore #20
    //   21: aload #4
    //   23: getfield mLastVisibleWidget : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   26: astore #24
    //   28: aload #4
    //   30: getfield mHead : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   33: astore #19
    //   35: aload #4
    //   37: getfield mTotalWeight : F
    //   40: fstore #5
    //   42: aload #4
    //   44: getfield mFirstMatchConstraintWidget : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   47: astore #18
    //   49: aload #4
    //   51: getfield mLastMatchConstraintWidget : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   54: astore #18
    //   56: aload_0
    //   57: getfield mListDimensionBehaviors : [Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   60: iload_2
    //   61: aaload
    //   62: getstatic android/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour.WRAP_CONTENT : Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   65: if_acmpne -> 74
    //   68: iconst_1
    //   69: istore #13
    //   71: goto -> 77
    //   74: iconst_0
    //   75: istore #13
    //   77: iload_2
    //   78: ifne -> 172
    //   81: aload #19
    //   83: getfield mHorizontalChainStyle : I
    //   86: ifne -> 95
    //   89: iconst_1
    //   90: istore #8
    //   92: goto -> 98
    //   95: iconst_0
    //   96: istore #8
    //   98: aload #19
    //   100: getfield mHorizontalChainStyle : I
    //   103: iconst_1
    //   104: if_icmpne -> 113
    //   107: iconst_1
    //   108: istore #9
    //   110: goto -> 116
    //   113: iconst_0
    //   114: istore #9
    //   116: iload #9
    //   118: istore #10
    //   120: iload #8
    //   122: istore #11
    //   124: aload #19
    //   126: getfield mHorizontalChainStyle : I
    //   129: iconst_2
    //   130: if_icmpne -> 147
    //   133: iconst_1
    //   134: istore #10
    //   136: iload #9
    //   138: istore #11
    //   140: iload #8
    //   142: istore #12
    //   144: goto -> 162
    //   147: iconst_0
    //   148: istore #8
    //   150: iload #11
    //   152: istore #12
    //   154: iload #10
    //   156: istore #11
    //   158: iload #8
    //   160: istore #10
    //   162: aload #17
    //   164: astore #21
    //   166: iconst_0
    //   167: istore #8
    //   169: goto -> 227
    //   172: aload #19
    //   174: getfield mVerticalChainStyle : I
    //   177: ifne -> 186
    //   180: iconst_1
    //   181: istore #8
    //   183: goto -> 189
    //   186: iconst_0
    //   187: istore #8
    //   189: aload #19
    //   191: getfield mVerticalChainStyle : I
    //   194: iconst_1
    //   195: if_icmpne -> 204
    //   198: iconst_1
    //   199: istore #9
    //   201: goto -> 207
    //   204: iconst_0
    //   205: istore #9
    //   207: iload #9
    //   209: istore #10
    //   211: iload #8
    //   213: istore #11
    //   215: aload #19
    //   217: getfield mVerticalChainStyle : I
    //   220: iconst_2
    //   221: if_icmpne -> 147
    //   224: goto -> 133
    //   227: aconst_null
    //   228: astore #22
    //   230: iload #8
    //   232: ifne -> 614
    //   235: aload #21
    //   237: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   240: iload_3
    //   241: aaload
    //   242: astore #18
    //   244: iload #13
    //   246: ifne -> 263
    //   249: iload #10
    //   251: ifeq -> 257
    //   254: goto -> 263
    //   257: iconst_4
    //   258: istore #9
    //   260: goto -> 266
    //   263: iconst_1
    //   264: istore #9
    //   266: aload #18
    //   268: invokevirtual getMargin : ()I
    //   271: istore #15
    //   273: iload #15
    //   275: istore #14
    //   277: aload #18
    //   279: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   282: ifnull -> 309
    //   285: iload #15
    //   287: istore #14
    //   289: aload #21
    //   291: aload #17
    //   293: if_acmpeq -> 309
    //   296: iload #15
    //   298: aload #18
    //   300: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   303: invokevirtual getMargin : ()I
    //   306: iadd
    //   307: istore #14
    //   309: iload #10
    //   311: ifeq -> 335
    //   314: aload #21
    //   316: aload #17
    //   318: if_acmpeq -> 335
    //   321: aload #21
    //   323: aload #20
    //   325: if_acmpeq -> 335
    //   328: bipush #6
    //   330: istore #9
    //   332: goto -> 351
    //   335: iload #12
    //   337: ifeq -> 351
    //   340: iload #13
    //   342: ifeq -> 351
    //   345: iconst_4
    //   346: istore #9
    //   348: goto -> 351
    //   351: aload #18
    //   353: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   356: ifnull -> 435
    //   359: aload #21
    //   361: aload #20
    //   363: if_acmpne -> 389
    //   366: aload_1
    //   367: aload #18
    //   369: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   372: aload #18
    //   374: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   377: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   380: iload #14
    //   382: iconst_5
    //   383: invokevirtual addGreaterThan : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;II)V
    //   386: goto -> 410
    //   389: aload_1
    //   390: aload #18
    //   392: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   395: aload #18
    //   397: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   400: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   403: iload #14
    //   405: bipush #6
    //   407: invokevirtual addGreaterThan : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;II)V
    //   410: aload_1
    //   411: aload #18
    //   413: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   416: aload #18
    //   418: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   421: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   424: iload #14
    //   426: iload #9
    //   428: invokevirtual addEquality : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;II)Landroid/support/constraint/solver/ArrayRow;
    //   431: pop
    //   432: goto -> 435
    //   435: iload #13
    //   437: ifeq -> 520
    //   440: aload #21
    //   442: invokevirtual getVisibility : ()I
    //   445: bipush #8
    //   447: if_icmpeq -> 494
    //   450: aload #21
    //   452: getfield mListDimensionBehaviors : [Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   455: iload_2
    //   456: aaload
    //   457: getstatic android/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT : Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   460: if_acmpne -> 494
    //   463: aload_1
    //   464: aload #21
    //   466: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   469: iload_3
    //   470: iconst_1
    //   471: iadd
    //   472: aaload
    //   473: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   476: aload #21
    //   478: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   481: iload_3
    //   482: aaload
    //   483: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   486: iconst_0
    //   487: iconst_5
    //   488: invokevirtual addGreaterThan : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;II)V
    //   491: goto -> 494
    //   494: aload_1
    //   495: aload #21
    //   497: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   500: iload_3
    //   501: aaload
    //   502: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   505: aload_0
    //   506: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   509: iload_3
    //   510: aaload
    //   511: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   514: iconst_0
    //   515: bipush #6
    //   517: invokevirtual addGreaterThan : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;II)V
    //   520: aload #21
    //   522: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   525: iload_3
    //   526: iconst_1
    //   527: iadd
    //   528: aaload
    //   529: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   532: astore #23
    //   534: aload #22
    //   536: astore #18
    //   538: aload #23
    //   540: ifnull -> 596
    //   543: aload #23
    //   545: getfield mOwner : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   548: astore #23
    //   550: aload #22
    //   552: astore #18
    //   554: aload #23
    //   556: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   559: iload_3
    //   560: aaload
    //   561: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   564: ifnull -> 596
    //   567: aload #23
    //   569: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   572: iload_3
    //   573: aaload
    //   574: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   577: getfield mOwner : Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   580: aload #21
    //   582: if_acmpeq -> 592
    //   585: aload #22
    //   587: astore #18
    //   589: goto -> 596
    //   592: aload #23
    //   594: astore #18
    //   596: aload #18
    //   598: ifnull -> 608
    //   601: aload #18
    //   603: astore #21
    //   605: goto -> 611
    //   608: iconst_1
    //   609: istore #8
    //   611: goto -> 227
    //   614: aload #24
    //   616: ifnull -> 685
    //   619: aload #16
    //   621: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   624: astore #18
    //   626: iload_3
    //   627: iconst_1
    //   628: iadd
    //   629: istore #8
    //   631: aload #18
    //   633: iload #8
    //   635: aaload
    //   636: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   639: ifnull -> 685
    //   642: aload #24
    //   644: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   647: iload #8
    //   649: aaload
    //   650: astore #18
    //   652: aload_1
    //   653: aload #18
    //   655: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   658: aload #16
    //   660: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   663: iload #8
    //   665: aaload
    //   666: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   669: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   672: aload #18
    //   674: invokevirtual getMargin : ()I
    //   677: ineg
    //   678: iconst_5
    //   679: invokevirtual addLowerThan : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;II)V
    //   682: goto -> 685
    //   685: iload #13
    //   687: ifeq -> 735
    //   690: aload_0
    //   691: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   694: astore_0
    //   695: iload_3
    //   696: iconst_1
    //   697: iadd
    //   698: istore #8
    //   700: aload_1
    //   701: aload_0
    //   702: iload #8
    //   704: aaload
    //   705: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   708: aload #16
    //   710: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   713: iload #8
    //   715: aaload
    //   716: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   719: aload #16
    //   721: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   724: iload #8
    //   726: aaload
    //   727: invokevirtual getMargin : ()I
    //   730: bipush #6
    //   732: invokevirtual addGreaterThan : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;II)V
    //   735: aload #4
    //   737: getfield mWeightedMatchConstraintsWidgets : Ljava/util/ArrayList;
    //   740: astore_0
    //   741: aload_0
    //   742: ifnull -> 1038
    //   745: aload_0
    //   746: invokevirtual size : ()I
    //   749: istore #9
    //   751: iload #9
    //   753: iconst_1
    //   754: if_icmple -> 1038
    //   757: fload #5
    //   759: fstore #6
    //   761: aload #4
    //   763: getfield mHasUndefinedWeights : Z
    //   766: ifeq -> 789
    //   769: fload #5
    //   771: fstore #6
    //   773: aload #4
    //   775: getfield mHasComplexMatchWeights : Z
    //   778: ifne -> 789
    //   781: aload #4
    //   783: getfield mWidgetsMatchCount : I
    //   786: i2f
    //   787: fstore #6
    //   789: aconst_null
    //   790: astore #18
    //   792: iconst_0
    //   793: istore #8
    //   795: fconst_0
    //   796: fstore #7
    //   798: iload #8
    //   800: iload #9
    //   802: if_icmpge -> 1038
    //   805: aload_0
    //   806: iload #8
    //   808: invokevirtual get : (I)Ljava/lang/Object;
    //   811: checkcast android/support/constraint/solver/widgets/ConstraintWidget
    //   814: astore #21
    //   816: aload #21
    //   818: getfield mWeight : [F
    //   821: iload_2
    //   822: faload
    //   823: fstore #5
    //   825: fload #5
    //   827: fconst_0
    //   828: fcmpg
    //   829: ifge -> 878
    //   832: aload #4
    //   834: getfield mHasComplexMatchWeights : Z
    //   837: ifeq -> 872
    //   840: aload_1
    //   841: aload #21
    //   843: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   846: iload_3
    //   847: iconst_1
    //   848: iadd
    //   849: aaload
    //   850: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   853: aload #21
    //   855: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   858: iload_3
    //   859: aaload
    //   860: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   863: iconst_0
    //   864: iconst_4
    //   865: invokevirtual addEquality : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;II)Landroid/support/constraint/solver/ArrayRow;
    //   868: pop
    //   869: goto -> 915
    //   872: fconst_1
    //   873: fstore #5
    //   875: goto -> 878
    //   878: fload #5
    //   880: fconst_0
    //   881: fcmpl
    //   882: ifne -> 922
    //   885: aload_1
    //   886: aload #21
    //   888: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   891: iload_3
    //   892: iconst_1
    //   893: iadd
    //   894: aaload
    //   895: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   898: aload #21
    //   900: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   903: iload_3
    //   904: aaload
    //   905: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   908: iconst_0
    //   909: bipush #6
    //   911: invokevirtual addEquality : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;II)Landroid/support/constraint/solver/ArrayRow;
    //   914: pop
    //   915: fload #7
    //   917: fstore #5
    //   919: goto -> 1025
    //   922: aload #18
    //   924: ifnull -> 1021
    //   927: aload #18
    //   929: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   932: iload_3
    //   933: aaload
    //   934: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   937: astore #22
    //   939: aload #18
    //   941: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   944: astore #18
    //   946: iload_3
    //   947: iconst_1
    //   948: iadd
    //   949: istore #13
    //   951: aload #18
    //   953: iload #13
    //   955: aaload
    //   956: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   959: astore #18
    //   961: aload #21
    //   963: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   966: iload_3
    //   967: aaload
    //   968: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   971: astore #23
    //   973: aload #21
    //   975: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   978: iload #13
    //   980: aaload
    //   981: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   984: astore #25
    //   986: aload_1
    //   987: invokevirtual createRow : ()Landroid/support/constraint/solver/ArrayRow;
    //   990: astore #26
    //   992: aload #26
    //   994: fload #7
    //   996: fload #6
    //   998: fload #5
    //   1000: aload #22
    //   1002: aload #18
    //   1004: aload #23
    //   1006: aload #25
    //   1008: invokevirtual createRowEqualMatchDimensions : (FFFLandroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;)Landroid/support/constraint/solver/ArrayRow;
    //   1011: pop
    //   1012: aload_1
    //   1013: aload #26
    //   1015: invokevirtual addConstraint : (Landroid/support/constraint/solver/ArrayRow;)V
    //   1018: goto -> 1021
    //   1021: aload #21
    //   1023: astore #18
    //   1025: iload #8
    //   1027: iconst_1
    //   1028: iadd
    //   1029: istore #8
    //   1031: fload #5
    //   1033: fstore #7
    //   1035: goto -> 798
    //   1038: aload #20
    //   1040: ifnull -> 1260
    //   1043: aload #20
    //   1045: aload #24
    //   1047: if_acmpeq -> 1055
    //   1050: iload #10
    //   1052: ifeq -> 1260
    //   1055: aload #17
    //   1057: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1060: iload_3
    //   1061: aaload
    //   1062: astore #18
    //   1064: aload #16
    //   1066: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1069: astore_0
    //   1070: iload_3
    //   1071: iconst_1
    //   1072: iadd
    //   1073: istore #8
    //   1075: aload_0
    //   1076: iload #8
    //   1078: aaload
    //   1079: astore #21
    //   1081: aload #17
    //   1083: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1086: iload_3
    //   1087: aaload
    //   1088: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1091: ifnull -> 1112
    //   1094: aload #17
    //   1096: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1099: iload_3
    //   1100: aaload
    //   1101: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1104: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   1107: astore #4
    //   1109: goto -> 1115
    //   1112: aconst_null
    //   1113: astore #4
    //   1115: aload #16
    //   1117: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1120: iload #8
    //   1122: aaload
    //   1123: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1126: ifnull -> 1148
    //   1129: aload #16
    //   1131: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1134: iload #8
    //   1136: aaload
    //   1137: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1140: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   1143: astore #17
    //   1145: goto -> 1151
    //   1148: aconst_null
    //   1149: astore #17
    //   1151: aload #20
    //   1153: aload #24
    //   1155: if_acmpne -> 1177
    //   1158: aload #20
    //   1160: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1163: iload_3
    //   1164: aaload
    //   1165: astore #18
    //   1167: aload #20
    //   1169: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1172: iload #8
    //   1174: aaload
    //   1175: astore #21
    //   1177: aload #16
    //   1179: astore_0
    //   1180: aload #4
    //   1182: ifnull -> 2351
    //   1185: aload #16
    //   1187: astore_0
    //   1188: aload #17
    //   1190: ifnull -> 2351
    //   1193: iload_2
    //   1194: ifne -> 1207
    //   1197: aload #19
    //   1199: getfield mHorizontalBiasPercent : F
    //   1202: fstore #5
    //   1204: goto -> 1217
    //   1207: aload #19
    //   1209: getfield mVerticalBiasPercent : F
    //   1212: fstore #5
    //   1214: goto -> 1204
    //   1217: aload #18
    //   1219: invokevirtual getMargin : ()I
    //   1222: istore_2
    //   1223: aload #21
    //   1225: invokevirtual getMargin : ()I
    //   1228: istore #8
    //   1230: aload_1
    //   1231: aload #18
    //   1233: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   1236: aload #4
    //   1238: iload_2
    //   1239: fload #5
    //   1241: aload #17
    //   1243: aload #21
    //   1245: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   1248: iload #8
    //   1250: iconst_5
    //   1251: invokevirtual addCentering : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;IFLandroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;II)V
    //   1254: aload #16
    //   1256: astore_0
    //   1257: goto -> 2351
    //   1260: aload #17
    //   1262: astore #21
    //   1264: iload #12
    //   1266: ifeq -> 1766
    //   1269: aload #20
    //   1271: ifnull -> 1766
    //   1274: aload #4
    //   1276: getfield mWidgetsMatchCount : I
    //   1279: ifle -> 1301
    //   1282: aload #4
    //   1284: getfield mWidgetsCount : I
    //   1287: aload #4
    //   1289: getfield mWidgetsMatchCount : I
    //   1292: if_icmpne -> 1301
    //   1295: iconst_1
    //   1296: istore #10
    //   1298: goto -> 1304
    //   1301: iconst_0
    //   1302: istore #10
    //   1304: aload #20
    //   1306: astore #17
    //   1308: aload #17
    //   1310: astore #18
    //   1312: aload #16
    //   1314: astore_0
    //   1315: aload #18
    //   1317: ifnull -> 2351
    //   1320: aload #18
    //   1322: getfield mNextChainWidget : [Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   1325: iload_2
    //   1326: aaload
    //   1327: astore_0
    //   1328: aload_0
    //   1329: ifnull -> 1351
    //   1332: aload_0
    //   1333: invokevirtual getVisibility : ()I
    //   1336: bipush #8
    //   1338: if_icmpne -> 1351
    //   1341: aload_0
    //   1342: getfield mNextChainWidget : [Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   1345: iload_2
    //   1346: aaload
    //   1347: astore_0
    //   1348: goto -> 1328
    //   1351: aload_0
    //   1352: ifnonnull -> 1368
    //   1355: aload #18
    //   1357: aload #24
    //   1359: if_acmpne -> 1365
    //   1362: goto -> 1368
    //   1365: goto -> 1743
    //   1368: aload #18
    //   1370: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1373: iload_3
    //   1374: aaload
    //   1375: astore #22
    //   1377: aload #22
    //   1379: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   1382: astore #25
    //   1384: aload #22
    //   1386: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1389: ifnull -> 1405
    //   1392: aload #22
    //   1394: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1397: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   1400: astore #19
    //   1402: goto -> 1408
    //   1405: aconst_null
    //   1406: astore #19
    //   1408: aload #17
    //   1410: aload #18
    //   1412: if_acmpeq -> 1432
    //   1415: aload #17
    //   1417: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1420: iload_3
    //   1421: iconst_1
    //   1422: iadd
    //   1423: aaload
    //   1424: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   1427: astore #4
    //   1429: goto -> 1488
    //   1432: aload #19
    //   1434: astore #4
    //   1436: aload #18
    //   1438: aload #20
    //   1440: if_acmpne -> 1488
    //   1443: aload #19
    //   1445: astore #4
    //   1447: aload #17
    //   1449: aload #18
    //   1451: if_acmpne -> 1488
    //   1454: aload #21
    //   1456: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1459: iload_3
    //   1460: aaload
    //   1461: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1464: ifnull -> 1485
    //   1467: aload #21
    //   1469: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1472: iload_3
    //   1473: aaload
    //   1474: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1477: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   1480: astore #4
    //   1482: goto -> 1488
    //   1485: aconst_null
    //   1486: astore #4
    //   1488: aload #22
    //   1490: invokevirtual getMargin : ()I
    //   1493: istore #13
    //   1495: aload #18
    //   1497: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1500: astore #19
    //   1502: iload_3
    //   1503: iconst_1
    //   1504: iadd
    //   1505: istore #14
    //   1507: aload #19
    //   1509: iload #14
    //   1511: aaload
    //   1512: invokevirtual getMargin : ()I
    //   1515: istore #9
    //   1517: aload_0
    //   1518: ifnull -> 1552
    //   1521: aload_0
    //   1522: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1525: iload_3
    //   1526: aaload
    //   1527: astore #23
    //   1529: aload #23
    //   1531: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   1534: astore #19
    //   1536: aload #18
    //   1538: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1541: iload #14
    //   1543: aaload
    //   1544: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   1547: astore #22
    //   1549: goto -> 1596
    //   1552: aload #16
    //   1554: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1557: iload #14
    //   1559: aaload
    //   1560: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1563: astore #23
    //   1565: aload #23
    //   1567: ifnull -> 1580
    //   1570: aload #23
    //   1572: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   1575: astore #19
    //   1577: goto -> 1583
    //   1580: aconst_null
    //   1581: astore #19
    //   1583: aload #18
    //   1585: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1588: iload #14
    //   1590: aaload
    //   1591: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   1594: astore #22
    //   1596: iload #9
    //   1598: istore #8
    //   1600: aload #23
    //   1602: ifnull -> 1615
    //   1605: iload #9
    //   1607: aload #23
    //   1609: invokevirtual getMargin : ()I
    //   1612: iadd
    //   1613: istore #8
    //   1615: iload #13
    //   1617: istore #9
    //   1619: aload #17
    //   1621: ifnull -> 1640
    //   1624: iload #13
    //   1626: aload #17
    //   1628: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1631: iload #14
    //   1633: aaload
    //   1634: invokevirtual getMargin : ()I
    //   1637: iadd
    //   1638: istore #9
    //   1640: aload #25
    //   1642: ifnull -> 1740
    //   1645: aload #4
    //   1647: ifnull -> 1740
    //   1650: aload #19
    //   1652: ifnull -> 1740
    //   1655: aload #22
    //   1657: ifnull -> 1740
    //   1660: aload #18
    //   1662: aload #20
    //   1664: if_acmpne -> 1679
    //   1667: aload #20
    //   1669: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1672: iload_3
    //   1673: aaload
    //   1674: invokevirtual getMargin : ()I
    //   1677: istore #9
    //   1679: aload #18
    //   1681: aload #24
    //   1683: if_acmpne -> 1702
    //   1686: aload #24
    //   1688: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1691: iload #14
    //   1693: aaload
    //   1694: invokevirtual getMargin : ()I
    //   1697: istore #8
    //   1699: goto -> 1702
    //   1702: iload #10
    //   1704: ifeq -> 1714
    //   1707: bipush #6
    //   1709: istore #13
    //   1711: goto -> 1717
    //   1714: iconst_4
    //   1715: istore #13
    //   1717: aload_1
    //   1718: aload #25
    //   1720: aload #4
    //   1722: iload #9
    //   1724: ldc 0.5
    //   1726: aload #19
    //   1728: aload #22
    //   1730: iload #8
    //   1732: iload #13
    //   1734: invokevirtual addCentering : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;IFLandroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;II)V
    //   1737: goto -> 1743
    //   1740: goto -> 1365
    //   1743: aload #18
    //   1745: invokevirtual getVisibility : ()I
    //   1748: bipush #8
    //   1750: if_icmpeq -> 1760
    //   1753: aload #18
    //   1755: astore #17
    //   1757: goto -> 1760
    //   1760: aload_0
    //   1761: astore #18
    //   1763: goto -> 1312
    //   1766: iload #11
    //   1768: ifeq -> 2348
    //   1771: aload #20
    //   1773: ifnull -> 2348
    //   1776: aload #4
    //   1778: getfield mWidgetsMatchCount : I
    //   1781: ifle -> 1803
    //   1784: aload #4
    //   1786: getfield mWidgetsCount : I
    //   1789: aload #4
    //   1791: getfield mWidgetsMatchCount : I
    //   1794: if_icmpne -> 1803
    //   1797: iconst_1
    //   1798: istore #8
    //   1800: goto -> 1806
    //   1803: iconst_0
    //   1804: istore #8
    //   1806: aload #20
    //   1808: astore #4
    //   1810: aload #4
    //   1812: astore #17
    //   1814: aload #17
    //   1816: ifnull -> 2173
    //   1819: aload #17
    //   1821: getfield mNextChainWidget : [Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   1824: iload_2
    //   1825: aaload
    //   1826: astore_0
    //   1827: aload_0
    //   1828: ifnull -> 1850
    //   1831: aload_0
    //   1832: invokevirtual getVisibility : ()I
    //   1835: bipush #8
    //   1837: if_icmpne -> 1850
    //   1840: aload_0
    //   1841: getfield mNextChainWidget : [Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   1844: iload_2
    //   1845: aaload
    //   1846: astore_0
    //   1847: goto -> 1827
    //   1850: aload #17
    //   1852: aload #20
    //   1854: if_acmpeq -> 2150
    //   1857: aload #17
    //   1859: aload #24
    //   1861: if_acmpeq -> 2150
    //   1864: aload_0
    //   1865: ifnull -> 2150
    //   1868: aload_0
    //   1869: aload #24
    //   1871: if_acmpne -> 1879
    //   1874: aconst_null
    //   1875: astore_0
    //   1876: goto -> 1879
    //   1879: aload #17
    //   1881: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1884: iload_3
    //   1885: aaload
    //   1886: astore #18
    //   1888: aload #18
    //   1890: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   1893: astore #23
    //   1895: aload #18
    //   1897: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1900: ifnull -> 1913
    //   1903: aload #18
    //   1905: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1908: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   1911: astore #19
    //   1913: aload #4
    //   1915: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1918: astore #19
    //   1920: iload_3
    //   1921: iconst_1
    //   1922: iadd
    //   1923: istore #14
    //   1925: aload #19
    //   1927: iload #14
    //   1929: aaload
    //   1930: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   1933: astore #25
    //   1935: aload #18
    //   1937: invokevirtual getMargin : ()I
    //   1940: istore #13
    //   1942: aload #17
    //   1944: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1947: iload #14
    //   1949: aaload
    //   1950: invokevirtual getMargin : ()I
    //   1953: istore #10
    //   1955: aload_0
    //   1956: ifnull -> 2001
    //   1959: aload_0
    //   1960: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1963: iload_3
    //   1964: aaload
    //   1965: astore #22
    //   1967: aload #22
    //   1969: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   1972: astore #19
    //   1974: aload #22
    //   1976: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1979: ifnull -> 1995
    //   1982: aload #22
    //   1984: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   1987: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   1990: astore #18
    //   1992: goto -> 2045
    //   1995: aconst_null
    //   1996: astore #18
    //   1998: goto -> 2045
    //   2001: aload #17
    //   2003: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   2006: iload #14
    //   2008: aaload
    //   2009: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   2012: astore #22
    //   2014: aload #22
    //   2016: ifnull -> 2029
    //   2019: aload #22
    //   2021: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   2024: astore #19
    //   2026: goto -> 2032
    //   2029: aconst_null
    //   2030: astore #19
    //   2032: aload #17
    //   2034: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   2037: iload #14
    //   2039: aaload
    //   2040: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   2043: astore #18
    //   2045: iload #10
    //   2047: istore #9
    //   2049: aload #22
    //   2051: ifnull -> 2064
    //   2054: iload #10
    //   2056: aload #22
    //   2058: invokevirtual getMargin : ()I
    //   2061: iadd
    //   2062: istore #9
    //   2064: iload #13
    //   2066: istore #10
    //   2068: aload #4
    //   2070: ifnull -> 2089
    //   2073: iload #13
    //   2075: aload #4
    //   2077: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   2080: iload #14
    //   2082: aaload
    //   2083: invokevirtual getMargin : ()I
    //   2086: iadd
    //   2087: istore #10
    //   2089: iload #8
    //   2091: ifeq -> 2101
    //   2094: bipush #6
    //   2096: istore #13
    //   2098: goto -> 2104
    //   2101: iconst_4
    //   2102: istore #13
    //   2104: aload #23
    //   2106: ifnull -> 2147
    //   2109: aload #25
    //   2111: ifnull -> 2147
    //   2114: aload #19
    //   2116: ifnull -> 2147
    //   2119: aload #18
    //   2121: ifnull -> 2147
    //   2124: aload_1
    //   2125: aload #23
    //   2127: aload #25
    //   2129: iload #10
    //   2131: ldc 0.5
    //   2133: aload #19
    //   2135: aload #18
    //   2137: iload #9
    //   2139: iload #13
    //   2141: invokevirtual addCentering : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;IFLandroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;II)V
    //   2144: goto -> 2150
    //   2147: goto -> 2150
    //   2150: aload #17
    //   2152: invokevirtual getVisibility : ()I
    //   2155: bipush #8
    //   2157: if_icmpeq -> 2167
    //   2160: aload #17
    //   2162: astore #4
    //   2164: goto -> 2167
    //   2167: aload_0
    //   2168: astore #17
    //   2170: goto -> 1814
    //   2173: aload #20
    //   2175: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   2178: iload_3
    //   2179: aaload
    //   2180: astore #4
    //   2182: aload #21
    //   2184: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   2187: iload_3
    //   2188: aaload
    //   2189: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   2192: astore #18
    //   2194: aload #24
    //   2196: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   2199: astore_0
    //   2200: iload_3
    //   2201: iconst_1
    //   2202: iadd
    //   2203: istore_2
    //   2204: aload_0
    //   2205: iload_2
    //   2206: aaload
    //   2207: astore #17
    //   2209: aload #16
    //   2211: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   2214: iload_2
    //   2215: aaload
    //   2216: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   2219: astore_0
    //   2220: aload #18
    //   2222: ifnull -> 2299
    //   2225: aload #20
    //   2227: aload #24
    //   2229: if_acmpeq -> 2256
    //   2232: aload_1
    //   2233: aload #4
    //   2235: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   2238: aload #18
    //   2240: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   2243: aload #4
    //   2245: invokevirtual getMargin : ()I
    //   2248: iconst_5
    //   2249: invokevirtual addEquality : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;II)Landroid/support/constraint/solver/ArrayRow;
    //   2252: pop
    //   2253: goto -> 2299
    //   2256: aload_0
    //   2257: ifnull -> 2299
    //   2260: aload_1
    //   2261: aload #4
    //   2263: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   2266: aload #18
    //   2268: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   2271: aload #4
    //   2273: invokevirtual getMargin : ()I
    //   2276: ldc 0.5
    //   2278: aload #17
    //   2280: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   2283: aload_0
    //   2284: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   2287: aload #17
    //   2289: invokevirtual getMargin : ()I
    //   2292: iconst_5
    //   2293: invokevirtual addCentering : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;IFLandroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;II)V
    //   2296: goto -> 2299
    //   2299: aload_0
    //   2300: astore #4
    //   2302: aload #16
    //   2304: astore_0
    //   2305: aload #4
    //   2307: ifnull -> 2351
    //   2310: aload #16
    //   2312: astore_0
    //   2313: aload #20
    //   2315: aload #24
    //   2317: if_acmpeq -> 2351
    //   2320: aload_1
    //   2321: aload #17
    //   2323: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   2326: aload #4
    //   2328: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   2331: aload #17
    //   2333: invokevirtual getMargin : ()I
    //   2336: ineg
    //   2337: iconst_5
    //   2338: invokevirtual addEquality : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;II)Landroid/support/constraint/solver/ArrayRow;
    //   2341: pop
    //   2342: aload #16
    //   2344: astore_0
    //   2345: goto -> 2351
    //   2348: aload #16
    //   2350: astore_0
    //   2351: iload #12
    //   2353: ifne -> 2361
    //   2356: iload #11
    //   2358: ifeq -> 2563
    //   2361: aload #20
    //   2363: ifnull -> 2563
    //   2366: aload #20
    //   2368: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   2371: iload_3
    //   2372: aaload
    //   2373: astore #17
    //   2375: aload #24
    //   2377: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   2380: astore #4
    //   2382: iload_3
    //   2383: iconst_1
    //   2384: iadd
    //   2385: istore_2
    //   2386: aload #4
    //   2388: iload_2
    //   2389: aaload
    //   2390: astore #18
    //   2392: aload #17
    //   2394: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   2397: ifnull -> 2413
    //   2400: aload #17
    //   2402: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   2405: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   2408: astore #16
    //   2410: goto -> 2416
    //   2413: aconst_null
    //   2414: astore #16
    //   2416: aload #18
    //   2418: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   2421: ifnull -> 2437
    //   2424: aload #18
    //   2426: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   2429: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   2432: astore #4
    //   2434: goto -> 2440
    //   2437: aconst_null
    //   2438: astore #4
    //   2440: aload_0
    //   2441: aload #24
    //   2443: if_acmpeq -> 2478
    //   2446: aload_0
    //   2447: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   2450: iload_2
    //   2451: aaload
    //   2452: astore #4
    //   2454: aload #4
    //   2456: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   2459: ifnull -> 2475
    //   2462: aload #4
    //   2464: getfield mTarget : Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   2467: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   2470: astore #4
    //   2472: goto -> 2478
    //   2475: aconst_null
    //   2476: astore #4
    //   2478: aload #20
    //   2480: aload #24
    //   2482: if_acmpne -> 2503
    //   2485: aload #20
    //   2487: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   2490: iload_3
    //   2491: aaload
    //   2492: astore #17
    //   2494: aload #20
    //   2496: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   2499: iload_2
    //   2500: aaload
    //   2501: astore #18
    //   2503: aload #16
    //   2505: ifnull -> 2563
    //   2508: aload #4
    //   2510: ifnull -> 2563
    //   2513: aload #17
    //   2515: invokevirtual getMargin : ()I
    //   2518: istore_3
    //   2519: aload #24
    //   2521: ifnonnull -> 2527
    //   2524: goto -> 2530
    //   2527: aload #24
    //   2529: astore_0
    //   2530: aload_0
    //   2531: getfield mListAnchors : [Landroid/support/constraint/solver/widgets/ConstraintAnchor;
    //   2534: iload_2
    //   2535: aaload
    //   2536: invokevirtual getMargin : ()I
    //   2539: istore_2
    //   2540: aload_1
    //   2541: aload #17
    //   2543: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   2546: aload #16
    //   2548: iload_3
    //   2549: ldc 0.5
    //   2551: aload #4
    //   2553: aload #18
    //   2555: getfield mSolverVariable : Landroid/support/constraint/solver/SolverVariable;
    //   2558: iload_2
    //   2559: iconst_5
    //   2560: invokevirtual addCentering : (Landroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;IFLandroid/support/constraint/solver/SolverVariable;Landroid/support/constraint/solver/SolverVariable;II)V
    //   2563: return
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\android\support\constraint\solver\widgets\Chain.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */