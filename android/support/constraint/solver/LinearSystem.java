package android.support.constraint.solver;

import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.constraint.solver.widgets.ConstraintWidget;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;

public class LinearSystem {
  private static final boolean DEBUG = false;
  
  public static final boolean FULL_DEBUG = false;
  
  private static int POOL_SIZE = 1000;
  
  public static Metrics sMetrics;
  
  private int TABLE_SIZE = 32;
  
  public boolean graphOptimizer = false;
  
  private boolean[] mAlreadyTestedCandidates = new boolean[this.TABLE_SIZE];
  
  final Cache mCache;
  
  private Row mGoal;
  
  private int mMaxColumns = this.TABLE_SIZE;
  
  private int mMaxRows = this.TABLE_SIZE;
  
  int mNumColumns = 1;
  
  int mNumRows = 0;
  
  private SolverVariable[] mPoolVariables = new SolverVariable[POOL_SIZE];
  
  private int mPoolVariablesCount = 0;
  
  ArrayRow[] mRows = null;
  
  private final Row mTempGoal;
  
  private HashMap<String, SolverVariable> mVariables = null;
  
  int mVariablesID = 0;
  
  private ArrayRow[] tempClientsCopy = new ArrayRow[this.TABLE_SIZE];
  
  public LinearSystem() {
    this.mRows = new ArrayRow[this.TABLE_SIZE];
    releaseRows();
    this.mCache = new Cache();
    this.mGoal = new GoalRow(this.mCache);
    this.mTempGoal = new ArrayRow(this.mCache);
  }
  
  private SolverVariable acquireSolverVariable(SolverVariable.Type paramType, String paramString) {
    SolverVariable solverVariable1;
    SolverVariable solverVariable2 = this.mCache.solverVariablePool.acquire();
    if (solverVariable2 == null) {
      solverVariable2 = new SolverVariable(paramType, paramString);
      solverVariable2.setType(paramType, paramString);
      solverVariable1 = solverVariable2;
    } else {
      solverVariable2.reset();
      solverVariable2.setType((SolverVariable.Type)solverVariable1, paramString);
      solverVariable1 = solverVariable2;
    } 
    if (this.mPoolVariablesCount >= POOL_SIZE) {
      POOL_SIZE *= 2;
      this.mPoolVariables = Arrays.<SolverVariable>copyOf(this.mPoolVariables, POOL_SIZE);
    } 
    SolverVariable[] arrayOfSolverVariable = this.mPoolVariables;
    int i = this.mPoolVariablesCount;
    this.mPoolVariablesCount = i + 1;
    arrayOfSolverVariable[i] = solverVariable1;
    return solverVariable1;
  }
  
  private void addError(ArrayRow paramArrayRow) {
    paramArrayRow.addError(this, 0);
  }
  
  private final void addRow(ArrayRow paramArrayRow) {
    if (this.mRows[this.mNumRows] != null)
      this.mCache.arrayRowPool.release(this.mRows[this.mNumRows]); 
    this.mRows[this.mNumRows] = paramArrayRow;
    paramArrayRow.variable.definitionId = this.mNumRows;
    this.mNumRows++;
    paramArrayRow.variable.updateReferencesWithNewDefinition(paramArrayRow);
  }
  
  private void addSingleError(ArrayRow paramArrayRow, int paramInt) {
    addSingleError(paramArrayRow, paramInt, 0);
  }
  
  private void computeValues() {
    for (int i = 0; i < this.mNumRows; i++) {
      ArrayRow arrayRow = this.mRows[i];
      arrayRow.variable.computedValue = arrayRow.constantValue;
    } 
  }
  
  public static ArrayRow createRowCentering(LinearSystem paramLinearSystem, SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, int paramInt1, float paramFloat, SolverVariable paramSolverVariable3, SolverVariable paramSolverVariable4, int paramInt2, boolean paramBoolean) {
    ArrayRow arrayRow = paramLinearSystem.createRow();
    arrayRow.createRowCentering(paramSolverVariable1, paramSolverVariable2, paramInt1, paramFloat, paramSolverVariable3, paramSolverVariable4, paramInt2);
    if (paramBoolean)
      arrayRow.addError(paramLinearSystem, 4); 
    return arrayRow;
  }
  
  public static ArrayRow createRowDimensionPercent(LinearSystem paramLinearSystem, SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, SolverVariable paramSolverVariable3, float paramFloat, boolean paramBoolean) {
    ArrayRow arrayRow = paramLinearSystem.createRow();
    if (paramBoolean)
      paramLinearSystem.addError(arrayRow); 
    return arrayRow.createRowDimensionPercent(paramSolverVariable1, paramSolverVariable2, paramSolverVariable3, paramFloat);
  }
  
  public static ArrayRow createRowEquals(LinearSystem paramLinearSystem, SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, int paramInt, boolean paramBoolean) {
    ArrayRow arrayRow = paramLinearSystem.createRow();
    arrayRow.createRowEquals(paramSolverVariable1, paramSolverVariable2, paramInt);
    if (paramBoolean)
      paramLinearSystem.addSingleError(arrayRow, 1); 
    return arrayRow;
  }
  
  public static ArrayRow createRowGreaterThan(LinearSystem paramLinearSystem, SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, int paramInt, boolean paramBoolean) {
    SolverVariable solverVariable = paramLinearSystem.createSlackVariable();
    ArrayRow arrayRow = paramLinearSystem.createRow();
    arrayRow.createRowGreaterThan(paramSolverVariable1, paramSolverVariable2, solverVariable, paramInt);
    if (paramBoolean)
      paramLinearSystem.addSingleError(arrayRow, (int)(arrayRow.variables.get(solverVariable) * -1.0F)); 
    return arrayRow;
  }
  
  public static ArrayRow createRowLowerThan(LinearSystem paramLinearSystem, SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, int paramInt, boolean paramBoolean) {
    SolverVariable solverVariable = paramLinearSystem.createSlackVariable();
    ArrayRow arrayRow = paramLinearSystem.createRow();
    arrayRow.createRowLowerThan(paramSolverVariable1, paramSolverVariable2, solverVariable, paramInt);
    if (paramBoolean)
      paramLinearSystem.addSingleError(arrayRow, (int)(arrayRow.variables.get(solverVariable) * -1.0F)); 
    return arrayRow;
  }
  
  private SolverVariable createVariable(String paramString, SolverVariable.Type paramType) {
    if (sMetrics != null) {
      Metrics metrics = sMetrics;
      metrics.variables++;
    } 
    if (this.mNumColumns + 1 >= this.mMaxColumns)
      increaseTableSize(); 
    SolverVariable solverVariable = acquireSolverVariable(paramType, null);
    solverVariable.setName(paramString);
    this.mVariablesID++;
    this.mNumColumns++;
    solverVariable.id = this.mVariablesID;
    if (this.mVariables == null)
      this.mVariables = new HashMap<String, SolverVariable>(); 
    this.mVariables.put(paramString, solverVariable);
    this.mCache.mIndexedVariables[this.mVariablesID] = solverVariable;
    return solverVariable;
  }
  
  private void displayRows() {
    displaySolverVariables();
    String str = "";
    for (int i = 0; i < this.mNumRows; i++) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(str);
      stringBuilder1.append(this.mRows[i]);
      str = stringBuilder1.toString();
      stringBuilder1 = new StringBuilder();
      stringBuilder1.append(str);
      stringBuilder1.append("\n");
      str = stringBuilder1.toString();
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(str);
    stringBuilder.append(this.mGoal);
    stringBuilder.append("\n");
    str = stringBuilder.toString();
    System.out.println(str);
  }
  
  private void displaySolverVariables() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Display Rows (");
    stringBuilder.append(this.mNumRows);
    stringBuilder.append("x");
    stringBuilder.append(this.mNumColumns);
    stringBuilder.append(")\n");
    String str = stringBuilder.toString();
    System.out.println(str);
  }
  
  private int enforceBFS(Row paramRow) {
    int i = 0;
    while (true) {
      if (i < this.mNumRows) {
        if ((this.mRows[i]).variable.mType != SolverVariable.Type.UNRESTRICTED && (this.mRows[i]).constantValue < 0.0F) {
          i = 1;
          break;
        } 
        i++;
        continue;
      } 
      i = 0;
      break;
    } 
    if (i != 0) {
      boolean bool = false;
      for (i = 0; !bool; i = n) {
        if (sMetrics != null) {
          Metrics metrics = sMetrics;
          metrics.bfs++;
        } 
        int n = i + 1;
        int m = 0;
        i = -1;
        int j = -1;
        float f = Float.MAX_VALUE;
        int k;
        for (k = 0; m < this.mNumRows; k = i3) {
          float f1;
          int i1;
          int i2;
          int i3;
          paramRow = this.mRows[m];
          if (((ArrayRow)paramRow).variable.mType == SolverVariable.Type.UNRESTRICTED) {
            i1 = i;
            i2 = j;
            f1 = f;
            i3 = k;
          } else if (((ArrayRow)paramRow).isSimpleDefinition) {
            i1 = i;
            i2 = j;
            f1 = f;
            i3 = k;
          } else {
            i1 = i;
            i2 = j;
            f1 = f;
            i3 = k;
            if (((ArrayRow)paramRow).constantValue < 0.0F) {
              int i4;
              for (i4 = 1;; i4++) {
                i1 = i;
                i2 = j;
                f1 = f;
                i3 = k;
                if (i4 < this.mNumColumns) {
                  SolverVariable solverVariable = this.mCache.mIndexedVariables[i4];
                  float f2 = ((ArrayRow)paramRow).variables.get(solverVariable);
                  if (f2 <= 0.0F)
                    continue; 
                  i2 = k;
                  i3 = 0;
                  k = j;
                  i1 = i;
                  i = i3;
                  for (j = i2;; j = i2)
                    i++; 
                  i = k;
                  k = j;
                  j = i;
                  i = i1;
                  continue;
                } 
                break;
              } 
            } 
          } 
          m++;
          i = i1;
          j = i2;
          f = f1;
        } 
        if (i != -1) {
          paramRow = this.mRows[i];
          ((ArrayRow)paramRow).variable.definitionId = -1;
          if (sMetrics != null) {
            Metrics metrics = sMetrics;
            metrics.pivots++;
          } 
          paramRow.pivot(this.mCache.mIndexedVariables[j]);
          ((ArrayRow)paramRow).variable.definitionId = i;
          ((ArrayRow)paramRow).variable.updateReferencesWithNewDefinition((ArrayRow)paramRow);
        } else {
          bool = true;
        } 
        if (n > this.mNumColumns / 2)
          bool = true; 
      } 
      return i;
    } 
    return 0;
  }
  
  private String getDisplaySize(int paramInt) {
    paramInt *= 4;
    int i = paramInt / 1024;
    int j = i / 1024;
    if (j > 0) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("");
      stringBuilder1.append(j);
      stringBuilder1.append(" Mb");
      return stringBuilder1.toString();
    } 
    if (i > 0) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("");
      stringBuilder1.append(i);
      stringBuilder1.append(" Kb");
      return stringBuilder1.toString();
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("");
    stringBuilder.append(paramInt);
    stringBuilder.append(" bytes");
    return stringBuilder.toString();
  }
  
  private String getDisplayStrength(int paramInt) {
    return (paramInt == 1) ? "LOW" : ((paramInt == 2) ? "MEDIUM" : ((paramInt == 3) ? "HIGH" : ((paramInt == 4) ? "HIGHEST" : ((paramInt == 5) ? "EQUALITY" : ((paramInt == 6) ? "FIXED" : "NONE")))));
  }
  
  public static Metrics getMetrics() {
    return sMetrics;
  }
  
  private void increaseTableSize() {
    this.TABLE_SIZE *= 2;
    this.mRows = Arrays.<ArrayRow>copyOf(this.mRows, this.TABLE_SIZE);
    this.mCache.mIndexedVariables = Arrays.<SolverVariable>copyOf(this.mCache.mIndexedVariables, this.TABLE_SIZE);
    this.mAlreadyTestedCandidates = new boolean[this.TABLE_SIZE];
    this.mMaxColumns = this.TABLE_SIZE;
    this.mMaxRows = this.TABLE_SIZE;
    if (sMetrics != null) {
      Metrics metrics = sMetrics;
      metrics.tableSizeIncrease++;
      sMetrics.maxTableSize = Math.max(sMetrics.maxTableSize, this.TABLE_SIZE);
      sMetrics.lastTableSize = sMetrics.maxTableSize;
    } 
  }
  
  private final int optimize(Row paramRow, boolean paramBoolean) {
    if (sMetrics != null) {
      Metrics metrics = sMetrics;
      metrics.optimize++;
    } 
    int i;
    for (i = 0; i < this.mNumColumns; i++)
      this.mAlreadyTestedCandidates[i] = false; 
    boolean bool = false;
    for (i = 0; !bool; i = j) {
      if (sMetrics != null) {
        Metrics metrics = sMetrics;
        metrics.iterations++;
      } 
      int j = i + 1;
      if (j >= this.mNumColumns * 2)
        return j; 
      if (paramRow.getKey() != null)
        this.mAlreadyTestedCandidates[(paramRow.getKey()).id] = true; 
      SolverVariable solverVariable = paramRow.getPivotCandidate(this, this.mAlreadyTestedCandidates);
      if (solverVariable != null) {
        if (this.mAlreadyTestedCandidates[solverVariable.id])
          return j; 
        this.mAlreadyTestedCandidates[solverVariable.id] = true;
      } 
      if (solverVariable != null) {
        i = 0;
        int k = -1;
        for (float f = Float.MAX_VALUE; i < this.mNumRows; f = f1) {
          float f1;
          int m;
          ArrayRow arrayRow = this.mRows[i];
          if (arrayRow.variable.mType == SolverVariable.Type.UNRESTRICTED) {
            m = k;
            f1 = f;
          } else if (arrayRow.isSimpleDefinition) {
            m = k;
            f1 = f;
          } else {
            m = k;
            f1 = f;
            if (arrayRow.hasVariable(solverVariable)) {
              float f2 = arrayRow.variables.get(solverVariable);
              m = k;
              f1 = f;
              if (f2 < 0.0F) {
                f2 = -arrayRow.constantValue / f2;
                m = k;
                f1 = f;
                if (f2 < f) {
                  m = i;
                  f1 = f2;
                } 
              } 
            } 
          } 
          i++;
          k = m;
        } 
        if (k > -1) {
          ArrayRow arrayRow = this.mRows[k];
          arrayRow.variable.definitionId = -1;
          if (sMetrics != null) {
            Metrics metrics = sMetrics;
            metrics.pivots++;
          } 
          arrayRow.pivot(solverVariable);
          arrayRow.variable.definitionId = k;
          arrayRow.variable.updateReferencesWithNewDefinition(arrayRow);
          i = j;
          continue;
        } 
      } 
      bool = true;
    } 
    return i;
  }
  
  private void releaseRows() {
    for (int i = 0; i < this.mRows.length; i++) {
      ArrayRow arrayRow = this.mRows[i];
      if (arrayRow != null)
        this.mCache.arrayRowPool.release(arrayRow); 
      this.mRows[i] = null;
    } 
  }
  
  private final void updateRowFromVariables(ArrayRow paramArrayRow) {
    if (this.mNumRows > 0) {
      paramArrayRow.variables.updateFromSystem(paramArrayRow, this.mRows);
      if (paramArrayRow.variables.currentSize == 0)
        paramArrayRow.isSimpleDefinition = true; 
    } 
  }
  
  public void addCenterPoint(ConstraintWidget paramConstraintWidget1, ConstraintWidget paramConstraintWidget2, float paramFloat, int paramInt) {
    SolverVariable solverVariable3 = createObjectVariable(paramConstraintWidget1.getAnchor(ConstraintAnchor.Type.LEFT));
    SolverVariable solverVariable5 = createObjectVariable(paramConstraintWidget1.getAnchor(ConstraintAnchor.Type.TOP));
    SolverVariable solverVariable4 = createObjectVariable(paramConstraintWidget1.getAnchor(ConstraintAnchor.Type.RIGHT));
    SolverVariable solverVariable7 = createObjectVariable(paramConstraintWidget1.getAnchor(ConstraintAnchor.Type.BOTTOM));
    SolverVariable solverVariable1 = createObjectVariable(paramConstraintWidget2.getAnchor(ConstraintAnchor.Type.LEFT));
    SolverVariable solverVariable8 = createObjectVariable(paramConstraintWidget2.getAnchor(ConstraintAnchor.Type.TOP));
    SolverVariable solverVariable6 = createObjectVariable(paramConstraintWidget2.getAnchor(ConstraintAnchor.Type.RIGHT));
    SolverVariable solverVariable2 = createObjectVariable(paramConstraintWidget2.getAnchor(ConstraintAnchor.Type.BOTTOM));
    ArrayRow arrayRow2 = createRow();
    double d1 = paramFloat;
    double d2 = Math.sin(d1);
    double d3 = paramInt;
    arrayRow2.createRowWithAngle(solverVariable5, solverVariable7, solverVariable8, solverVariable2, (float)(d2 * d3));
    addConstraint(arrayRow2);
    ArrayRow arrayRow1 = createRow();
    arrayRow1.createRowWithAngle(solverVariable3, solverVariable4, solverVariable1, solverVariable6, (float)(Math.cos(d1) * d3));
    addConstraint(arrayRow1);
  }
  
  public void addCentering(SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, int paramInt1, float paramFloat, SolverVariable paramSolverVariable3, SolverVariable paramSolverVariable4, int paramInt2, int paramInt3) {
    ArrayRow arrayRow = createRow();
    arrayRow.createRowCentering(paramSolverVariable1, paramSolverVariable2, paramInt1, paramFloat, paramSolverVariable3, paramSolverVariable4, paramInt2);
    if (paramInt3 != 6)
      arrayRow.addError(this, paramInt3); 
    addConstraint(arrayRow);
  }
  
  public void addConstraint(ArrayRow paramArrayRow) {
    if (paramArrayRow == null)
      return; 
    if (sMetrics != null) {
      Metrics metrics = sMetrics;
      metrics.constraints++;
      if (paramArrayRow.isSimpleDefinition) {
        metrics = sMetrics;
        metrics.simpleconstraints++;
      } 
    } 
    if (this.mNumRows + 1 >= this.mMaxRows || this.mNumColumns + 1 >= this.mMaxColumns)
      increaseTableSize(); 
    boolean bool1 = false;
    boolean bool2 = false;
    if (!paramArrayRow.isSimpleDefinition) {
      updateRowFromVariables(paramArrayRow);
      if (paramArrayRow.isEmpty())
        return; 
      paramArrayRow.ensurePositiveConstant();
      bool1 = bool2;
      if (paramArrayRow.chooseSubject(this)) {
        SolverVariable solverVariable = createExtraVariable();
        paramArrayRow.variable = solverVariable;
        addRow(paramArrayRow);
        this.mTempGoal.initFromRow(paramArrayRow);
        optimize(this.mTempGoal, true);
        if (solverVariable.definitionId == -1) {
          if (paramArrayRow.variable == solverVariable) {
            solverVariable = paramArrayRow.pickPivot(solverVariable);
            if (solverVariable != null) {
              if (sMetrics != null) {
                Metrics metrics = sMetrics;
                metrics.pivots++;
              } 
              paramArrayRow.pivot(solverVariable);
            } 
          } 
          if (!paramArrayRow.isSimpleDefinition)
            paramArrayRow.variable.updateReferencesWithNewDefinition(paramArrayRow); 
          this.mNumRows--;
        } 
        bool1 = true;
      } 
      if (!paramArrayRow.hasKeyVariable())
        return; 
    } 
    if (!bool1)
      addRow(paramArrayRow); 
  }
  
  public ArrayRow addEquality(SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, int paramInt1, int paramInt2) {
    ArrayRow arrayRow = createRow();
    arrayRow.createRowEquals(paramSolverVariable1, paramSolverVariable2, paramInt1);
    if (paramInt2 != 6)
      arrayRow.addError(this, paramInt2); 
    addConstraint(arrayRow);
    return arrayRow;
  }
  
  public void addEquality(SolverVariable paramSolverVariable, int paramInt) {
    int i = paramSolverVariable.definitionId;
    if (paramSolverVariable.definitionId != -1) {
      ArrayRow arrayRow1 = this.mRows[i];
      if (arrayRow1.isSimpleDefinition) {
        arrayRow1.constantValue = paramInt;
        return;
      } 
      if (arrayRow1.variables.currentSize == 0) {
        arrayRow1.isSimpleDefinition = true;
        arrayRow1.constantValue = paramInt;
        return;
      } 
      arrayRow1 = createRow();
      arrayRow1.createRowEquals(paramSolverVariable, paramInt);
      addConstraint(arrayRow1);
      return;
    } 
    ArrayRow arrayRow = createRow();
    arrayRow.createRowDefinition(paramSolverVariable, paramInt);
    addConstraint(arrayRow);
  }
  
  public void addEquality(SolverVariable paramSolverVariable, int paramInt1, int paramInt2) {
    int i = paramSolverVariable.definitionId;
    if (paramSolverVariable.definitionId != -1) {
      ArrayRow arrayRow1 = this.mRows[i];
      if (arrayRow1.isSimpleDefinition) {
        arrayRow1.constantValue = paramInt1;
        return;
      } 
      arrayRow1 = createRow();
      arrayRow1.createRowEquals(paramSolverVariable, paramInt1);
      arrayRow1.addError(this, paramInt2);
      addConstraint(arrayRow1);
      return;
    } 
    ArrayRow arrayRow = createRow();
    arrayRow.createRowDefinition(paramSolverVariable, paramInt1);
    arrayRow.addError(this, paramInt2);
    addConstraint(arrayRow);
  }
  
  public void addGreaterBarrier(SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, boolean paramBoolean) {
    ArrayRow arrayRow = createRow();
    SolverVariable solverVariable = createSlackVariable();
    solverVariable.strength = 0;
    arrayRow.createRowGreaterThan(paramSolverVariable1, paramSolverVariable2, solverVariable, 0);
    if (paramBoolean)
      addSingleError(arrayRow, (int)(arrayRow.variables.get(solverVariable) * -1.0F), 1); 
    addConstraint(arrayRow);
  }
  
  public void addGreaterThan(SolverVariable paramSolverVariable, int paramInt) {
    ArrayRow arrayRow = createRow();
    SolverVariable solverVariable = createSlackVariable();
    solverVariable.strength = 0;
    arrayRow.createRowGreaterThan(paramSolverVariable, paramInt, solverVariable);
    addConstraint(arrayRow);
  }
  
  public void addGreaterThan(SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, int paramInt1, int paramInt2) {
    ArrayRow arrayRow = createRow();
    SolverVariable solverVariable = createSlackVariable();
    solverVariable.strength = 0;
    arrayRow.createRowGreaterThan(paramSolverVariable1, paramSolverVariable2, solverVariable, paramInt1);
    if (paramInt2 != 6)
      addSingleError(arrayRow, (int)(arrayRow.variables.get(solverVariable) * -1.0F), paramInt2); 
    addConstraint(arrayRow);
  }
  
  public void addLowerBarrier(SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, boolean paramBoolean) {
    ArrayRow arrayRow = createRow();
    SolverVariable solverVariable = createSlackVariable();
    solverVariable.strength = 0;
    arrayRow.createRowLowerThan(paramSolverVariable1, paramSolverVariable2, solverVariable, 0);
    if (paramBoolean)
      addSingleError(arrayRow, (int)(arrayRow.variables.get(solverVariable) * -1.0F), 1); 
    addConstraint(arrayRow);
  }
  
  public void addLowerThan(SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, int paramInt1, int paramInt2) {
    ArrayRow arrayRow = createRow();
    SolverVariable solverVariable = createSlackVariable();
    solverVariable.strength = 0;
    arrayRow.createRowLowerThan(paramSolverVariable1, paramSolverVariable2, solverVariable, paramInt1);
    if (paramInt2 != 6)
      addSingleError(arrayRow, (int)(arrayRow.variables.get(solverVariable) * -1.0F), paramInt2); 
    addConstraint(arrayRow);
  }
  
  public void addRatio(SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, SolverVariable paramSolverVariable3, SolverVariable paramSolverVariable4, float paramFloat, int paramInt) {
    ArrayRow arrayRow = createRow();
    arrayRow.createRowDimensionRatio(paramSolverVariable1, paramSolverVariable2, paramSolverVariable3, paramSolverVariable4, paramFloat);
    if (paramInt != 6)
      arrayRow.addError(this, paramInt); 
    addConstraint(arrayRow);
  }
  
  void addSingleError(ArrayRow paramArrayRow, int paramInt1, int paramInt2) {
    paramArrayRow.addSingleError(createErrorVariable(paramInt2, null), paramInt1);
  }
  
  public SolverVariable createErrorVariable(int paramInt, String paramString) {
    if (sMetrics != null) {
      Metrics metrics = sMetrics;
      metrics.errors++;
    } 
    if (this.mNumColumns + 1 >= this.mMaxColumns)
      increaseTableSize(); 
    SolverVariable solverVariable = acquireSolverVariable(SolverVariable.Type.ERROR, paramString);
    this.mVariablesID++;
    this.mNumColumns++;
    solverVariable.id = this.mVariablesID;
    solverVariable.strength = paramInt;
    this.mCache.mIndexedVariables[this.mVariablesID] = solverVariable;
    this.mGoal.addError(solverVariable);
    return solverVariable;
  }
  
  public SolverVariable createExtraVariable() {
    if (sMetrics != null) {
      Metrics metrics = sMetrics;
      metrics.extravariables++;
    } 
    if (this.mNumColumns + 1 >= this.mMaxColumns)
      increaseTableSize(); 
    SolverVariable solverVariable = acquireSolverVariable(SolverVariable.Type.SLACK, null);
    this.mVariablesID++;
    this.mNumColumns++;
    solverVariable.id = this.mVariablesID;
    this.mCache.mIndexedVariables[this.mVariablesID] = solverVariable;
    return solverVariable;
  }
  
  public SolverVariable createObjectVariable(Object paramObject) {
    SolverVariable solverVariable = null;
    if (paramObject == null)
      return null; 
    if (this.mNumColumns + 1 >= this.mMaxColumns)
      increaseTableSize(); 
    if (paramObject instanceof ConstraintAnchor) {
      ConstraintAnchor constraintAnchor = (ConstraintAnchor)paramObject;
      solverVariable = constraintAnchor.getSolverVariable();
      paramObject = solverVariable;
      if (solverVariable == null) {
        constraintAnchor.resetSolverVariable(this.mCache);
        paramObject = constraintAnchor.getSolverVariable();
      } 
      if (((SolverVariable)paramObject).id != -1 && ((SolverVariable)paramObject).id <= this.mVariablesID) {
        Object object = paramObject;
        if (this.mCache.mIndexedVariables[((SolverVariable)paramObject).id] == null) {
          if (((SolverVariable)paramObject).id != -1)
            paramObject.reset(); 
          this.mVariablesID++;
          this.mNumColumns++;
          ((SolverVariable)paramObject).id = this.mVariablesID;
          ((SolverVariable)paramObject).mType = SolverVariable.Type.UNRESTRICTED;
          this.mCache.mIndexedVariables[this.mVariablesID] = (SolverVariable)paramObject;
          return (SolverVariable)paramObject;
        } 
        return (SolverVariable)object;
      } 
    } else {
      return solverVariable;
    } 
    if (((SolverVariable)paramObject).id != -1)
      paramObject.reset(); 
    this.mVariablesID++;
    this.mNumColumns++;
    ((SolverVariable)paramObject).id = this.mVariablesID;
    ((SolverVariable)paramObject).mType = SolverVariable.Type.UNRESTRICTED;
    this.mCache.mIndexedVariables[this.mVariablesID] = (SolverVariable)paramObject;
    return (SolverVariable)paramObject;
  }
  
  public ArrayRow createRow() {
    ArrayRow arrayRow = this.mCache.arrayRowPool.acquire();
    if (arrayRow == null) {
      arrayRow = new ArrayRow(this.mCache);
    } else {
      arrayRow.reset();
    } 
    SolverVariable.increaseErrorId();
    return arrayRow;
  }
  
  public SolverVariable createSlackVariable() {
    if (sMetrics != null) {
      Metrics metrics = sMetrics;
      metrics.slackvariables++;
    } 
    if (this.mNumColumns + 1 >= this.mMaxColumns)
      increaseTableSize(); 
    SolverVariable solverVariable = acquireSolverVariable(SolverVariable.Type.SLACK, null);
    this.mVariablesID++;
    this.mNumColumns++;
    solverVariable.id = this.mVariablesID;
    this.mCache.mIndexedVariables[this.mVariablesID] = solverVariable;
    return solverVariable;
  }
  
  void displayReadableRows() {
    displaySolverVariables();
    String str1 = " #  ";
    for (int i = 0; i < this.mNumRows; i++) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str1);
      stringBuilder.append(this.mRows[i].toReadableString());
      str1 = stringBuilder.toString();
      stringBuilder = new StringBuilder();
      stringBuilder.append(str1);
      stringBuilder.append("\n #  ");
      str1 = stringBuilder.toString();
    } 
    String str2 = str1;
    if (this.mGoal != null) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str1);
      stringBuilder.append(this.mGoal);
      stringBuilder.append("\n");
      str2 = stringBuilder.toString();
    } 
    System.out.println(str2);
  }
  
  void displaySystemInformations() {
    int j = 0;
    int i;
    for (i = 0; j < this.TABLE_SIZE; i = m) {
      int m = i;
      if (this.mRows[j] != null)
        m = i + this.mRows[j].sizeInBytes(); 
      j++;
    } 
    j = 0;
    int k;
    for (k = 0; j < this.mNumRows; k = m) {
      int m = k;
      if (this.mRows[j] != null)
        m = k + this.mRows[j].sizeInBytes(); 
      j++;
    } 
    PrintStream printStream = System.out;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Linear System -> Table size: ");
    stringBuilder.append(this.TABLE_SIZE);
    stringBuilder.append(" (");
    stringBuilder.append(getDisplaySize(this.TABLE_SIZE * this.TABLE_SIZE));
    stringBuilder.append(") -- row sizes: ");
    stringBuilder.append(getDisplaySize(i));
    stringBuilder.append(", actual size: ");
    stringBuilder.append(getDisplaySize(k));
    stringBuilder.append(" rows: ");
    stringBuilder.append(this.mNumRows);
    stringBuilder.append("/");
    stringBuilder.append(this.mMaxRows);
    stringBuilder.append(" cols: ");
    stringBuilder.append(this.mNumColumns);
    stringBuilder.append("/");
    stringBuilder.append(this.mMaxColumns);
    stringBuilder.append(" ");
    stringBuilder.append(0);
    stringBuilder.append(" occupied cells, ");
    stringBuilder.append(getDisplaySize(0));
    printStream.println(stringBuilder.toString());
  }
  
  public void displayVariablesReadableRows() {
    displaySolverVariables();
    String str = "";
    int i = 0;
    while (i < this.mNumRows) {
      String str1 = str;
      if ((this.mRows[i]).variable.mType == SolverVariable.Type.UNRESTRICTED) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(str);
        stringBuilder1.append(this.mRows[i].toReadableString());
        str = stringBuilder1.toString();
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append(str);
        stringBuilder1.append("\n");
        str1 = stringBuilder1.toString();
      } 
      i++;
      str = str1;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(str);
    stringBuilder.append(this.mGoal);
    stringBuilder.append("\n");
    str = stringBuilder.toString();
    System.out.println(str);
  }
  
  public void fillMetrics(Metrics paramMetrics) {
    sMetrics = paramMetrics;
  }
  
  public Cache getCache() {
    return this.mCache;
  }
  
  Row getGoal() {
    return this.mGoal;
  }
  
  public int getMemoryUsed() {
    int i = 0;
    int j;
    for (j = 0; i < this.mNumRows; j = k) {
      int k = j;
      if (this.mRows[i] != null)
        k = j + this.mRows[i].sizeInBytes(); 
      i++;
    } 
    return j;
  }
  
  public int getNumEquations() {
    return this.mNumRows;
  }
  
  public int getNumVariables() {
    return this.mVariablesID;
  }
  
  public int getObjectVariableValue(Object paramObject) {
    paramObject = ((ConstraintAnchor)paramObject).getSolverVariable();
    return (paramObject != null) ? (int)(((SolverVariable)paramObject).computedValue + 0.5F) : 0;
  }
  
  ArrayRow getRow(int paramInt) {
    return this.mRows[paramInt];
  }
  
  float getValueFor(String paramString) {
    SolverVariable solverVariable = getVariable(paramString, SolverVariable.Type.UNRESTRICTED);
    return (solverVariable == null) ? 0.0F : solverVariable.computedValue;
  }
  
  SolverVariable getVariable(String paramString, SolverVariable.Type paramType) {
    if (this.mVariables == null)
      this.mVariables = new HashMap<String, SolverVariable>(); 
    SolverVariable solverVariable2 = this.mVariables.get(paramString);
    SolverVariable solverVariable1 = solverVariable2;
    if (solverVariable2 == null)
      solverVariable1 = createVariable(paramString, paramType); 
    return solverVariable1;
  }
  
  public void minimize() {
    if (sMetrics != null) {
      Metrics metrics = sMetrics;
      metrics.minimize++;
    } 
    if (this.graphOptimizer) {
      if (sMetrics != null) {
        Metrics metrics = sMetrics;
        metrics.graphOptimizer++;
      } 
      byte b = 0;
      int i = 0;
      while (true) {
        if (i < this.mNumRows) {
          if (!(this.mRows[i]).isSimpleDefinition) {
            i = b;
            break;
          } 
          i++;
          continue;
        } 
        i = 1;
        break;
      } 
      if (i == 0) {
        minimizeGoal(this.mGoal);
        return;
      } 
      if (sMetrics != null) {
        Metrics metrics = sMetrics;
        metrics.fullySolved++;
      } 
      computeValues();
      return;
    } 
    minimizeGoal(this.mGoal);
  }
  
  void minimizeGoal(Row paramRow) {
    if (sMetrics != null) {
      Metrics metrics = sMetrics;
      metrics.minimizeGoal++;
      sMetrics.maxVariables = Math.max(sMetrics.maxVariables, this.mNumColumns);
      sMetrics.maxRows = Math.max(sMetrics.maxRows, this.mNumRows);
    } 
    updateRowFromVariables((ArrayRow)paramRow);
    enforceBFS(paramRow);
    optimize(paramRow, false);
    computeValues();
  }
  
  public void reset() {
    int i;
    for (i = 0; i < this.mCache.mIndexedVariables.length; i++) {
      SolverVariable solverVariable = this.mCache.mIndexedVariables[i];
      if (solverVariable != null)
        solverVariable.reset(); 
    } 
    this.mCache.solverVariablePool.releaseAll(this.mPoolVariables, this.mPoolVariablesCount);
    this.mPoolVariablesCount = 0;
    Arrays.fill((Object[])this.mCache.mIndexedVariables, (Object)null);
    if (this.mVariables != null)
      this.mVariables.clear(); 
    this.mVariablesID = 0;
    this.mGoal.clear();
    this.mNumColumns = 1;
    for (i = 0; i < this.mNumRows; i++)
      (this.mRows[i]).used = false; 
    releaseRows();
    this.mNumRows = 0;
  }
  
  static interface Row {
    void addError(SolverVariable param1SolverVariable);
    
    void clear();
    
    SolverVariable getKey();
    
    SolverVariable getPivotCandidate(LinearSystem param1LinearSystem, boolean[] param1ArrayOfboolean);
    
    void initFromRow(Row param1Row);
    
    boolean isEmpty();
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\android\support\constraint\solver\LinearSystem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */