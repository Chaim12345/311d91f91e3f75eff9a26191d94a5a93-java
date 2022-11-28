package androidx.constraintlayout.core;

import androidx.constraintlayout.core.SolverVariable;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import java.util.Arrays;
import java.util.HashMap;
/* loaded from: classes.dex */
public class LinearSystem {
    public static long ARRAY_ROW_CREATION = 0;
    public static final boolean DEBUG = false;
    private static final boolean DEBUG_CONSTRAINTS = false;
    public static final boolean FULL_DEBUG = false;
    public static final boolean MEASURE = false;
    public static long OPTIMIZED_ARRAY_ROW_CREATION = 0;
    public static boolean OPTIMIZED_ENGINE = false;
    private static int POOL_SIZE = 1000;
    public static boolean SIMPLIFY_SYNONYMS = true;
    public static boolean SKIP_COLUMNS = true;
    public static boolean USE_BASIC_SYNONYMS = true;
    public static boolean USE_DEPENDENCY_ORDERING = false;
    public static boolean USE_SYNONYMS = true;
    public static Metrics sMetrics;

    /* renamed from: b  reason: collision with root package name */
    ArrayRow[] f1658b;

    /* renamed from: e  reason: collision with root package name */
    final Cache f1661e;
    private Row mGoal;
    private Row mTempGoal;
    public boolean hasSimpleDefinition = false;

    /* renamed from: a  reason: collision with root package name */
    int f1657a = 0;
    private HashMap<String, SolverVariable> mVariables = null;
    private int TABLE_SIZE = 32;
    private int mMaxColumns = 32;
    public boolean graphOptimizer = false;
    public boolean newgraphOptimizer = false;
    private boolean[] mAlreadyTestedCandidates = new boolean[32];

    /* renamed from: c  reason: collision with root package name */
    int f1659c = 1;

    /* renamed from: d  reason: collision with root package name */
    int f1660d = 0;
    private int mMaxRows = 32;
    private SolverVariable[] mPoolVariables = new SolverVariable[POOL_SIZE];
    private int mPoolVariablesCount = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface Row {
        void addError(SolverVariable solverVariable);

        void clear();

        SolverVariable getKey();

        SolverVariable getPivotCandidate(LinearSystem linearSystem, boolean[] zArr);

        void initFromRow(Row row);

        boolean isEmpty();

        void updateFromFinalVariable(LinearSystem linearSystem, SolverVariable solverVariable, boolean z);

        void updateFromRow(LinearSystem linearSystem, ArrayRow arrayRow, boolean z);

        void updateFromSystem(LinearSystem linearSystem);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class ValuesRow extends ArrayRow {
        public ValuesRow(LinearSystem linearSystem, Cache cache) {
            this.variables = new SolverVariableValues(this, cache);
        }
    }

    public LinearSystem() {
        this.f1658b = null;
        this.f1658b = new ArrayRow[32];
        releaseRows();
        Cache cache = new Cache();
        this.f1661e = cache;
        this.mGoal = new PriorityGoalRow(cache);
        this.mTempGoal = OPTIMIZED_ENGINE ? new ValuesRow(this, cache) : new ArrayRow(cache);
    }

    private SolverVariable acquireSolverVariable(SolverVariable.Type type, String str) {
        SolverVariable solverVariable = (SolverVariable) this.f1661e.f1655c.acquire();
        if (solverVariable == null) {
            solverVariable = new SolverVariable(type, str);
        } else {
            solverVariable.reset();
        }
        solverVariable.setType(type, str);
        int i2 = this.mPoolVariablesCount;
        int i3 = POOL_SIZE;
        if (i2 >= i3) {
            int i4 = i3 * 2;
            POOL_SIZE = i4;
            this.mPoolVariables = (SolverVariable[]) Arrays.copyOf(this.mPoolVariables, i4);
        }
        SolverVariable[] solverVariableArr = this.mPoolVariables;
        int i5 = this.mPoolVariablesCount;
        this.mPoolVariablesCount = i5 + 1;
        solverVariableArr[i5] = solverVariable;
        return solverVariable;
    }

    private void addError(ArrayRow arrayRow) {
        arrayRow.addError(this, 0);
    }

    private final void addRow(ArrayRow arrayRow) {
        int i2;
        if (SIMPLIFY_SYNONYMS && arrayRow.f1652e) {
            arrayRow.f1648a.setFinalValue(this, arrayRow.f1649b);
        } else {
            ArrayRow[] arrayRowArr = this.f1658b;
            int i3 = this.f1660d;
            arrayRowArr[i3] = arrayRow;
            SolverVariable solverVariable = arrayRow.f1648a;
            solverVariable.f1665a = i3;
            this.f1660d = i3 + 1;
            solverVariable.updateReferencesWithNewDefinition(this, arrayRow);
        }
        if (SIMPLIFY_SYNONYMS && this.hasSimpleDefinition) {
            int i4 = 0;
            while (i4 < this.f1660d) {
                if (this.f1658b[i4] == null) {
                    System.out.println("WTF");
                }
                ArrayRow[] arrayRowArr2 = this.f1658b;
                if (arrayRowArr2[i4] != null && arrayRowArr2[i4].f1652e) {
                    ArrayRow arrayRow2 = arrayRowArr2[i4];
                    arrayRow2.f1648a.setFinalValue(this, arrayRow2.f1649b);
                    (OPTIMIZED_ENGINE ? this.f1661e.f1653a : this.f1661e.f1654b).release(arrayRow2);
                    this.f1658b[i4] = null;
                    int i5 = i4 + 1;
                    int i6 = i5;
                    while (true) {
                        i2 = this.f1660d;
                        if (i5 >= i2) {
                            break;
                        }
                        ArrayRow[] arrayRowArr3 = this.f1658b;
                        int i7 = i5 - 1;
                        arrayRowArr3[i7] = arrayRowArr3[i5];
                        if (arrayRowArr3[i7].f1648a.f1665a == i5) {
                            arrayRowArr3[i7].f1648a.f1665a = i7;
                        }
                        i6 = i5;
                        i5++;
                    }
                    if (i6 < i2) {
                        this.f1658b[i6] = null;
                    }
                    this.f1660d = i2 - 1;
                    i4--;
                }
                i4++;
            }
            this.hasSimpleDefinition = false;
        }
    }

    private void addSingleError(ArrayRow arrayRow, int i2) {
        a(arrayRow, i2, 0);
    }

    private void computeValues() {
        for (int i2 = 0; i2 < this.f1660d; i2++) {
            ArrayRow arrayRow = this.f1658b[i2];
            arrayRow.f1648a.computedValue = arrayRow.f1649b;
        }
    }

    public static ArrayRow createRowDimensionPercent(LinearSystem linearSystem, SolverVariable solverVariable, SolverVariable solverVariable2, float f2) {
        return linearSystem.createRow().f(solverVariable, solverVariable2, f2);
    }

    private SolverVariable createVariable(String str, SolverVariable.Type type) {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.variables++;
        }
        if (this.f1659c + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable acquireSolverVariable = acquireSolverVariable(type, null);
        acquireSolverVariable.setName(str);
        int i2 = this.f1657a + 1;
        this.f1657a = i2;
        this.f1659c++;
        acquireSolverVariable.id = i2;
        if (this.mVariables == null) {
            this.mVariables = new HashMap<>();
        }
        this.mVariables.put(str, acquireSolverVariable);
        this.f1661e.f1656d[this.f1657a] = acquireSolverVariable;
        return acquireSolverVariable;
    }

    private void displayRows() {
        displaySolverVariables();
        String str = "";
        for (int i2 = 0; i2 < this.f1660d; i2++) {
            str = (str + this.f1658b[i2]) + "\n";
        }
        System.out.println(str + this.mGoal + "\n");
    }

    private void displaySolverVariables() {
        System.out.println("Display Rows (" + this.f1660d + "x" + this.f1659c + ")\n");
    }

    private int enforceBFS(Row row) {
        boolean z;
        int i2 = 0;
        while (true) {
            if (i2 >= this.f1660d) {
                z = false;
                break;
            }
            ArrayRow[] arrayRowArr = this.f1658b;
            if (arrayRowArr[i2].f1648a.f1668d != SolverVariable.Type.UNRESTRICTED && arrayRowArr[i2].f1649b < 0.0f) {
                z = true;
                break;
            }
            i2++;
        }
        if (z) {
            boolean z2 = false;
            int i3 = 0;
            while (!z2) {
                Metrics metrics = sMetrics;
                if (metrics != null) {
                    metrics.bfs++;
                }
                i3++;
                float f2 = Float.MAX_VALUE;
                int i4 = -1;
                int i5 = -1;
                int i6 = 0;
                for (int i7 = 0; i7 < this.f1660d; i7++) {
                    ArrayRow arrayRow = this.f1658b[i7];
                    if (arrayRow.f1648a.f1668d != SolverVariable.Type.UNRESTRICTED && !arrayRow.f1652e && arrayRow.f1649b < 0.0f) {
                        int i8 = 9;
                        if (SKIP_COLUMNS) {
                            int currentSize = arrayRow.variables.getCurrentSize();
                            int i9 = 0;
                            while (i9 < currentSize) {
                                SolverVariable variable = arrayRow.variables.getVariable(i9);
                                float f3 = arrayRow.variables.get(variable);
                                if (f3 > 0.0f) {
                                    int i10 = 0;
                                    while (i10 < i8) {
                                        float f4 = variable.f1666b[i10] / f3;
                                        if ((f4 < f2 && i10 == i6) || i10 > i6) {
                                            i5 = variable.id;
                                            i6 = i10;
                                            i4 = i7;
                                            f2 = f4;
                                        }
                                        i10++;
                                        i8 = 9;
                                    }
                                }
                                i9++;
                                i8 = 9;
                            }
                        } else {
                            for (int i11 = 1; i11 < this.f1659c; i11++) {
                                SolverVariable solverVariable = this.f1661e.f1656d[i11];
                                float f5 = arrayRow.variables.get(solverVariable);
                                if (f5 > 0.0f) {
                                    for (int i12 = 0; i12 < 9; i12++) {
                                        float f6 = solverVariable.f1666b[i12] / f5;
                                        if ((f6 < f2 && i12 == i6) || i12 > i6) {
                                            i5 = i11;
                                            i6 = i12;
                                            i4 = i7;
                                            f2 = f6;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (i4 != -1) {
                    ArrayRow arrayRow2 = this.f1658b[i4];
                    arrayRow2.f1648a.f1665a = -1;
                    Metrics metrics2 = sMetrics;
                    if (metrics2 != null) {
                        metrics2.pivots++;
                    }
                    arrayRow2.j(this.f1661e.f1656d[i5]);
                    SolverVariable solverVariable2 = arrayRow2.f1648a;
                    solverVariable2.f1665a = i4;
                    solverVariable2.updateReferencesWithNewDefinition(this, arrayRow2);
                } else {
                    z2 = true;
                }
                if (i3 > this.f1659c / 2) {
                    z2 = true;
                }
            }
            return i3;
        }
        return 0;
    }

    private String getDisplaySize(int i2) {
        int i3 = i2 * 4;
        int i4 = i3 / 1024;
        int i5 = i4 / 1024;
        if (i5 > 0) {
            return "" + i5 + " Mb";
        } else if (i4 > 0) {
            return "" + i4 + " Kb";
        } else {
            return "" + i3 + " bytes";
        }
    }

    private String getDisplayStrength(int i2) {
        return i2 == 1 ? "LOW" : i2 == 2 ? "MEDIUM" : i2 == 3 ? "HIGH" : i2 == 4 ? "HIGHEST" : i2 == 5 ? "EQUALITY" : i2 == 8 ? "FIXED" : i2 == 6 ? "BARRIER" : "NONE";
    }

    public static Metrics getMetrics() {
        return sMetrics;
    }

    private void increaseTableSize() {
        int i2 = this.TABLE_SIZE * 2;
        this.TABLE_SIZE = i2;
        this.f1658b = (ArrayRow[]) Arrays.copyOf(this.f1658b, i2);
        Cache cache = this.f1661e;
        cache.f1656d = (SolverVariable[]) Arrays.copyOf(cache.f1656d, this.TABLE_SIZE);
        int i3 = this.TABLE_SIZE;
        this.mAlreadyTestedCandidates = new boolean[i3];
        this.mMaxColumns = i3;
        this.mMaxRows = i3;
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.tableSizeIncrease++;
            metrics.maxTableSize = Math.max(metrics.maxTableSize, i3);
            Metrics metrics2 = sMetrics;
            metrics2.lastTableSize = metrics2.maxTableSize;
        }
    }

    private final int optimize(Row row, boolean z) {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.optimize++;
        }
        for (int i2 = 0; i2 < this.f1659c; i2++) {
            this.mAlreadyTestedCandidates[i2] = false;
        }
        boolean z2 = false;
        int i3 = 0;
        while (!z2) {
            Metrics metrics2 = sMetrics;
            if (metrics2 != null) {
                metrics2.iterations++;
            }
            i3++;
            if (i3 >= this.f1659c * 2) {
                return i3;
            }
            if (row.getKey() != null) {
                this.mAlreadyTestedCandidates[row.getKey().id] = true;
            }
            SolverVariable pivotCandidate = row.getPivotCandidate(this, this.mAlreadyTestedCandidates);
            if (pivotCandidate != null) {
                boolean[] zArr = this.mAlreadyTestedCandidates;
                int i4 = pivotCandidate.id;
                if (zArr[i4]) {
                    return i3;
                }
                zArr[i4] = true;
            }
            if (pivotCandidate != null) {
                float f2 = Float.MAX_VALUE;
                int i5 = -1;
                for (int i6 = 0; i6 < this.f1660d; i6++) {
                    ArrayRow arrayRow = this.f1658b[i6];
                    if (arrayRow.f1648a.f1668d != SolverVariable.Type.UNRESTRICTED && !arrayRow.f1652e && arrayRow.i(pivotCandidate)) {
                        float f3 = arrayRow.variables.get(pivotCandidate);
                        if (f3 < 0.0f) {
                            float f4 = (-arrayRow.f1649b) / f3;
                            if (f4 < f2) {
                                i5 = i6;
                                f2 = f4;
                            }
                        }
                    }
                }
                if (i5 > -1) {
                    ArrayRow arrayRow2 = this.f1658b[i5];
                    arrayRow2.f1648a.f1665a = -1;
                    Metrics metrics3 = sMetrics;
                    if (metrics3 != null) {
                        metrics3.pivots++;
                    }
                    arrayRow2.j(pivotCandidate);
                    SolverVariable solverVariable = arrayRow2.f1648a;
                    solverVariable.f1665a = i5;
                    solverVariable.updateReferencesWithNewDefinition(this, arrayRow2);
                }
            } else {
                z2 = true;
            }
        }
        return i3;
    }

    private void releaseRows() {
        int i2 = 0;
        if (OPTIMIZED_ENGINE) {
            while (i2 < this.f1660d) {
                ArrayRow arrayRow = this.f1658b[i2];
                if (arrayRow != null) {
                    this.f1661e.f1653a.release(arrayRow);
                }
                this.f1658b[i2] = null;
                i2++;
            }
            return;
        }
        while (i2 < this.f1660d) {
            ArrayRow arrayRow2 = this.f1658b[i2];
            if (arrayRow2 != null) {
                this.f1661e.f1654b.release(arrayRow2);
            }
            this.f1658b[i2] = null;
            i2++;
        }
    }

    void a(ArrayRow arrayRow, int i2, int i3) {
        arrayRow.a(createErrorVariable(i3, null), i2);
    }

    public void addCenterPoint(ConstraintWidget constraintWidget, ConstraintWidget constraintWidget2, float f2, int i2) {
        ConstraintAnchor.Type type = ConstraintAnchor.Type.LEFT;
        SolverVariable createObjectVariable = createObjectVariable(constraintWidget.getAnchor(type));
        ConstraintAnchor.Type type2 = ConstraintAnchor.Type.TOP;
        SolverVariable createObjectVariable2 = createObjectVariable(constraintWidget.getAnchor(type2));
        ConstraintAnchor.Type type3 = ConstraintAnchor.Type.RIGHT;
        SolverVariable createObjectVariable3 = createObjectVariable(constraintWidget.getAnchor(type3));
        ConstraintAnchor.Type type4 = ConstraintAnchor.Type.BOTTOM;
        SolverVariable createObjectVariable4 = createObjectVariable(constraintWidget.getAnchor(type4));
        SolverVariable createObjectVariable5 = createObjectVariable(constraintWidget2.getAnchor(type));
        SolverVariable createObjectVariable6 = createObjectVariable(constraintWidget2.getAnchor(type2));
        SolverVariable createObjectVariable7 = createObjectVariable(constraintWidget2.getAnchor(type3));
        SolverVariable createObjectVariable8 = createObjectVariable(constraintWidget2.getAnchor(type4));
        ArrayRow createRow = createRow();
        double d2 = f2;
        double d3 = i2;
        createRow.createRowWithAngle(createObjectVariable2, createObjectVariable4, createObjectVariable6, createObjectVariable8, (float) (Math.sin(d2) * d3));
        addConstraint(createRow);
        ArrayRow createRow2 = createRow();
        createRow2.createRowWithAngle(createObjectVariable, createObjectVariable3, createObjectVariable5, createObjectVariable7, (float) (Math.cos(d2) * d3));
        addConstraint(createRow2);
    }

    public void addCentering(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, float f2, SolverVariable solverVariable3, SolverVariable solverVariable4, int i3, int i4) {
        ArrayRow createRow = createRow();
        createRow.d(solverVariable, solverVariable2, i2, f2, solverVariable3, solverVariable4, i3);
        if (i4 != 8) {
            createRow.addError(this, i4);
        }
        addConstraint(createRow);
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x009c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x009d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void addConstraint(ArrayRow arrayRow) {
        SolverVariable pickPivot;
        if (arrayRow == null) {
            return;
        }
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.constraints++;
            if (arrayRow.f1652e) {
                metrics.simpleconstraints++;
            }
        }
        boolean z = true;
        if (this.f1660d + 1 >= this.mMaxRows || this.f1659c + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        boolean z2 = false;
        if (!arrayRow.f1652e) {
            arrayRow.updateFromSystem(this);
            if (arrayRow.isEmpty()) {
                return;
            }
            arrayRow.g();
            if (arrayRow.b(this)) {
                SolverVariable createExtraVariable = createExtraVariable();
                arrayRow.f1648a = createExtraVariable;
                int i2 = this.f1660d;
                addRow(arrayRow);
                if (this.f1660d == i2 + 1) {
                    this.mTempGoal.initFromRow(arrayRow);
                    optimize(this.mTempGoal, true);
                    if (createExtraVariable.f1665a == -1) {
                        if (arrayRow.f1648a == createExtraVariable && (pickPivot = arrayRow.pickPivot(createExtraVariable)) != null) {
                            Metrics metrics2 = sMetrics;
                            if (metrics2 != null) {
                                metrics2.pivots++;
                            }
                            arrayRow.j(pickPivot);
                        }
                        if (!arrayRow.f1652e) {
                            arrayRow.f1648a.updateReferencesWithNewDefinition(this, arrayRow);
                        }
                        (OPTIMIZED_ENGINE ? this.f1661e.f1653a : this.f1661e.f1654b).release(arrayRow);
                        this.f1660d--;
                    }
                    if (arrayRow.h()) {
                        return;
                    }
                    z2 = z;
                }
            }
            z = false;
            if (arrayRow.h()) {
            }
        }
        if (z2) {
            return;
        }
        addRow(arrayRow);
    }

    public ArrayRow addEquality(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, int i3) {
        if (USE_BASIC_SYNONYMS && i3 == 8 && solverVariable2.isFinalValue && solverVariable.f1665a == -1) {
            solverVariable.setFinalValue(this, solverVariable2.computedValue + i2);
            return null;
        }
        ArrayRow createRow = createRow();
        createRow.createRowEquals(solverVariable, solverVariable2, i2);
        if (i3 != 8) {
            createRow.addError(this, i3);
        }
        addConstraint(createRow);
        return createRow;
    }

    public void addEquality(SolverVariable solverVariable, int i2) {
        ArrayRow createRow;
        if (USE_BASIC_SYNONYMS && solverVariable.f1665a == -1) {
            float f2 = i2;
            solverVariable.setFinalValue(this, f2);
            for (int i3 = 0; i3 < this.f1657a + 1; i3++) {
                SolverVariable solverVariable2 = this.f1661e.f1656d[i3];
                if (solverVariable2 != null && solverVariable2.f1671g && solverVariable2.f1672h == solverVariable.id) {
                    solverVariable2.setFinalValue(this, solverVariable2.f1673i + f2);
                }
            }
            return;
        }
        int i4 = solverVariable.f1665a;
        if (i4 != -1) {
            ArrayRow arrayRow = this.f1658b[i4];
            if (!arrayRow.f1652e) {
                if (arrayRow.variables.getCurrentSize() == 0) {
                    arrayRow.f1652e = true;
                } else {
                    createRow = createRow();
                    createRow.createRowEquals(solverVariable, i2);
                }
            }
            arrayRow.f1649b = i2;
            return;
        }
        createRow = createRow();
        createRow.e(solverVariable, i2);
        addConstraint(createRow);
    }

    public void addGreaterBarrier(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, boolean z) {
        ArrayRow createRow = createRow();
        SolverVariable createSlackVariable = createSlackVariable();
        createSlackVariable.strength = 0;
        createRow.createRowGreaterThan(solverVariable, solverVariable2, createSlackVariable, i2);
        addConstraint(createRow);
    }

    public void addGreaterThan(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, int i3) {
        ArrayRow createRow = createRow();
        SolverVariable createSlackVariable = createSlackVariable();
        createSlackVariable.strength = 0;
        createRow.createRowGreaterThan(solverVariable, solverVariable2, createSlackVariable, i2);
        if (i3 != 8) {
            a(createRow, (int) (createRow.variables.get(createSlackVariable) * (-1.0f)), i3);
        }
        addConstraint(createRow);
    }

    public void addLowerBarrier(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, boolean z) {
        ArrayRow createRow = createRow();
        SolverVariable createSlackVariable = createSlackVariable();
        createSlackVariable.strength = 0;
        createRow.createRowLowerThan(solverVariable, solverVariable2, createSlackVariable, i2);
        addConstraint(createRow);
    }

    public void addLowerThan(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, int i3) {
        ArrayRow createRow = createRow();
        SolverVariable createSlackVariable = createSlackVariable();
        createSlackVariable.strength = 0;
        createRow.createRowLowerThan(solverVariable, solverVariable2, createSlackVariable, i2);
        if (i3 != 8) {
            a(createRow, (int) (createRow.variables.get(createSlackVariable) * (-1.0f)), i3);
        }
        addConstraint(createRow);
    }

    public void addRatio(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f2, int i2) {
        ArrayRow createRow = createRow();
        createRow.createRowDimensionRatio(solverVariable, solverVariable2, solverVariable3, solverVariable4, f2);
        if (i2 != 8) {
            createRow.addError(this, i2);
        }
        addConstraint(createRow);
    }

    public void addSynonym(SolverVariable solverVariable, SolverVariable solverVariable2, int i2) {
        if (solverVariable.f1665a != -1 || i2 != 0) {
            addEquality(solverVariable, solverVariable2, i2, 8);
            return;
        }
        if (solverVariable2.f1671g) {
            solverVariable2 = this.f1661e.f1656d[solverVariable2.f1672h];
        }
        if (solverVariable.f1671g) {
            SolverVariable solverVariable3 = this.f1661e.f1656d[solverVariable.f1672h];
        } else {
            solverVariable.setSynonym(this, solverVariable2, 0.0f);
        }
    }

    void b(Row row) {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.minimizeGoal++;
            metrics.maxVariables = Math.max(metrics.maxVariables, this.f1659c);
            Metrics metrics2 = sMetrics;
            metrics2.maxRows = Math.max(metrics2.maxRows, this.f1660d);
        }
        enforceBFS(row);
        optimize(row, false);
        computeValues();
    }

    public SolverVariable createErrorVariable(int i2, String str) {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.errors++;
        }
        if (this.f1659c + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable acquireSolverVariable = acquireSolverVariable(SolverVariable.Type.ERROR, str);
        int i3 = this.f1657a + 1;
        this.f1657a = i3;
        this.f1659c++;
        acquireSolverVariable.id = i3;
        acquireSolverVariable.strength = i2;
        this.f1661e.f1656d[i3] = acquireSolverVariable;
        this.mGoal.addError(acquireSolverVariable);
        return acquireSolverVariable;
    }

    public SolverVariable createExtraVariable() {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.extravariables++;
        }
        if (this.f1659c + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable acquireSolverVariable = acquireSolverVariable(SolverVariable.Type.SLACK, null);
        int i2 = this.f1657a + 1;
        this.f1657a = i2;
        this.f1659c++;
        acquireSolverVariable.id = i2;
        this.f1661e.f1656d[i2] = acquireSolverVariable;
        return acquireSolverVariable;
    }

    public SolverVariable createObjectVariable(Object obj) {
        SolverVariable solverVariable = null;
        if (obj == null) {
            return null;
        }
        if (this.f1659c + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        if (obj instanceof ConstraintAnchor) {
            ConstraintAnchor constraintAnchor = (ConstraintAnchor) obj;
            solverVariable = constraintAnchor.getSolverVariable();
            if (solverVariable == null) {
                constraintAnchor.resetSolverVariable(this.f1661e);
                solverVariable = constraintAnchor.getSolverVariable();
            }
            int i2 = solverVariable.id;
            if (i2 == -1 || i2 > this.f1657a || this.f1661e.f1656d[i2] == null) {
                if (i2 != -1) {
                    solverVariable.reset();
                }
                int i3 = this.f1657a + 1;
                this.f1657a = i3;
                this.f1659c++;
                solverVariable.id = i3;
                solverVariable.f1668d = SolverVariable.Type.UNRESTRICTED;
                this.f1661e.f1656d[i3] = solverVariable;
            }
        }
        return solverVariable;
    }

    public ArrayRow createRow() {
        ArrayRow arrayRow;
        if (OPTIMIZED_ENGINE) {
            arrayRow = (ArrayRow) this.f1661e.f1653a.acquire();
            if (arrayRow == null) {
                arrayRow = new ValuesRow(this, this.f1661e);
                OPTIMIZED_ARRAY_ROW_CREATION++;
            }
            arrayRow.reset();
        } else {
            arrayRow = (ArrayRow) this.f1661e.f1654b.acquire();
            if (arrayRow == null) {
                arrayRow = new ArrayRow(this.f1661e);
                ARRAY_ROW_CREATION++;
            }
            arrayRow.reset();
        }
        SolverVariable.a();
        return arrayRow;
    }

    public SolverVariable createSlackVariable() {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.slackvariables++;
        }
        if (this.f1659c + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable acquireSolverVariable = acquireSolverVariable(SolverVariable.Type.SLACK, null);
        int i2 = this.f1657a + 1;
        this.f1657a = i2;
        this.f1659c++;
        acquireSolverVariable.id = i2;
        this.f1661e.f1656d[i2] = acquireSolverVariable;
        return acquireSolverVariable;
    }

    public void displayReadableRows() {
        displaySolverVariables();
        String str = " num vars " + this.f1657a + "\n";
        for (int i2 = 0; i2 < this.f1657a + 1; i2++) {
            SolverVariable solverVariable = this.f1661e.f1656d[i2];
            if (solverVariable != null && solverVariable.isFinalValue) {
                str = str + " $[" + i2 + "] => " + solverVariable + " = " + solverVariable.computedValue + "\n";
            }
        }
        String str2 = str + "\n";
        for (int i3 = 0; i3 < this.f1657a + 1; i3++) {
            SolverVariable[] solverVariableArr = this.f1661e.f1656d;
            SolverVariable solverVariable2 = solverVariableArr[i3];
            if (solverVariable2 != null && solverVariable2.f1671g) {
                str2 = str2 + " ~[" + i3 + "] => " + solverVariable2 + " = " + solverVariableArr[solverVariable2.f1672h] + " + " + solverVariable2.f1673i + "\n";
            }
        }
        String str3 = str2 + "\n\n #  ";
        for (int i4 = 0; i4 < this.f1660d; i4++) {
            str3 = (str3 + this.f1658b[i4].l()) + "\n #  ";
        }
        if (this.mGoal != null) {
            str3 = str3 + "Goal: " + this.mGoal + "\n";
        }
        System.out.println(str3);
    }

    public void displayVariablesReadableRows() {
        displaySolverVariables();
        String str = "";
        for (int i2 = 0; i2 < this.f1660d; i2++) {
            if (this.f1658b[i2].f1648a.f1668d == SolverVariable.Type.UNRESTRICTED) {
                str = (str + this.f1658b[i2].l()) + "\n";
            }
        }
        System.out.println(str + this.mGoal + "\n");
    }

    public void fillMetrics(Metrics metrics) {
        sMetrics = metrics;
    }

    public Cache getCache() {
        return this.f1661e;
    }

    public int getMemoryUsed() {
        int i2 = 0;
        for (int i3 = 0; i3 < this.f1660d; i3++) {
            ArrayRow[] arrayRowArr = this.f1658b;
            if (arrayRowArr[i3] != null) {
                i2 += arrayRowArr[i3].k();
            }
        }
        return i2;
    }

    public int getNumEquations() {
        return this.f1660d;
    }

    public int getNumVariables() {
        return this.f1657a;
    }

    public int getObjectVariableValue(Object obj) {
        SolverVariable solverVariable = ((ConstraintAnchor) obj).getSolverVariable();
        if (solverVariable != null) {
            return (int) (solverVariable.computedValue + 0.5f);
        }
        return 0;
    }

    public void minimize() {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.minimize++;
        }
        if (this.mGoal.isEmpty()) {
            computeValues();
            return;
        }
        if (this.graphOptimizer || this.newgraphOptimizer) {
            Metrics metrics2 = sMetrics;
            if (metrics2 != null) {
                metrics2.graphOptimizer++;
            }
            boolean z = false;
            int i2 = 0;
            while (true) {
                if (i2 >= this.f1660d) {
                    z = true;
                    break;
                } else if (!this.f1658b[i2].f1652e) {
                    break;
                } else {
                    i2++;
                }
            }
            if (z) {
                Metrics metrics3 = sMetrics;
                if (metrics3 != null) {
                    metrics3.fullySolved++;
                }
                computeValues();
                return;
            }
        }
        b(this.mGoal);
    }

    public void removeRow(ArrayRow arrayRow) {
        SolverVariable solverVariable;
        int i2;
        if (!arrayRow.f1652e || (solverVariable = arrayRow.f1648a) == null) {
            return;
        }
        int i3 = solverVariable.f1665a;
        if (i3 != -1) {
            while (true) {
                i2 = this.f1660d;
                if (i3 >= i2 - 1) {
                    break;
                }
                ArrayRow[] arrayRowArr = this.f1658b;
                int i4 = i3 + 1;
                SolverVariable solverVariable2 = arrayRowArr[i4].f1648a;
                if (solverVariable2.f1665a == i4) {
                    solverVariable2.f1665a = i3;
                }
                arrayRowArr[i3] = arrayRowArr[i4];
                i3 = i4;
            }
            this.f1660d = i2 - 1;
        }
        SolverVariable solverVariable3 = arrayRow.f1648a;
        if (!solverVariable3.isFinalValue) {
            solverVariable3.setFinalValue(this, arrayRow.f1649b);
        }
        (OPTIMIZED_ENGINE ? this.f1661e.f1653a : this.f1661e.f1654b).release(arrayRow);
    }

    public void reset() {
        Cache cache;
        int i2 = 0;
        while (true) {
            cache = this.f1661e;
            SolverVariable[] solverVariableArr = cache.f1656d;
            if (i2 >= solverVariableArr.length) {
                break;
            }
            SolverVariable solverVariable = solverVariableArr[i2];
            if (solverVariable != null) {
                solverVariable.reset();
            }
            i2++;
        }
        cache.f1655c.releaseAll(this.mPoolVariables, this.mPoolVariablesCount);
        this.mPoolVariablesCount = 0;
        Arrays.fill(this.f1661e.f1656d, (Object) null);
        HashMap<String, SolverVariable> hashMap = this.mVariables;
        if (hashMap != null) {
            hashMap.clear();
        }
        this.f1657a = 0;
        this.mGoal.clear();
        this.f1659c = 1;
        for (int i3 = 0; i3 < this.f1660d; i3++) {
            ArrayRow[] arrayRowArr = this.f1658b;
            if (arrayRowArr[i3] != null) {
                arrayRowArr[i3].f1650c = false;
            }
        }
        releaseRows();
        this.f1660d = 0;
        this.mTempGoal = OPTIMIZED_ENGINE ? new ValuesRow(this, this.f1661e) : new ArrayRow(this.f1661e);
    }
}
