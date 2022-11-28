package androidx.constraintlayout.core;

import androidx.constraintlayout.core.ArrayRow;
import java.util.Arrays;
import java.util.Comparator;
/* loaded from: classes.dex */
public class PriorityGoalRow extends ArrayRow {
    private static final boolean DEBUG = false;
    private static final float epsilon = 1.0E-4f;
    private int TABLE_SIZE;
    private SolverVariable[] arrayGoals;

    /* renamed from: f  reason: collision with root package name */
    GoalVariableAccessor f1662f;
    private int numGoals;
    private SolverVariable[] sortArray;

    /* loaded from: classes.dex */
    class GoalVariableAccessor {

        /* renamed from: a  reason: collision with root package name */
        SolverVariable f1663a;

        public GoalVariableAccessor(PriorityGoalRow priorityGoalRow) {
        }

        public void add(SolverVariable solverVariable) {
            for (int i2 = 0; i2 < 9; i2++) {
                float[] fArr = this.f1663a.f1667c;
                fArr[i2] = fArr[i2] + solverVariable.f1667c[i2];
                if (Math.abs(fArr[i2]) < 1.0E-4f) {
                    this.f1663a.f1667c[i2] = 0.0f;
                }
            }
        }

        public boolean addToGoal(SolverVariable solverVariable, float f2) {
            boolean z = true;
            if (!this.f1663a.inGoal) {
                for (int i2 = 0; i2 < 9; i2++) {
                    float f3 = solverVariable.f1667c[i2];
                    if (f3 != 0.0f) {
                        float f4 = f3 * f2;
                        if (Math.abs(f4) < 1.0E-4f) {
                            f4 = 0.0f;
                        }
                        this.f1663a.f1667c[i2] = f4;
                    } else {
                        this.f1663a.f1667c[i2] = 0.0f;
                    }
                }
                return true;
            }
            for (int i3 = 0; i3 < 9; i3++) {
                float[] fArr = this.f1663a.f1667c;
                fArr[i3] = fArr[i3] + (solverVariable.f1667c[i3] * f2);
                if (Math.abs(fArr[i3]) < 1.0E-4f) {
                    this.f1663a.f1667c[i3] = 0.0f;
                } else {
                    z = false;
                }
            }
            if (z) {
                PriorityGoalRow.this.removeGoal(this.f1663a);
            }
            return false;
        }

        public void init(SolverVariable solverVariable) {
            this.f1663a = solverVariable;
        }

        public final boolean isNegative() {
            for (int i2 = 8; i2 >= 0; i2--) {
                float f2 = this.f1663a.f1667c[i2];
                if (f2 > 0.0f) {
                    return false;
                }
                if (f2 < 0.0f) {
                    return true;
                }
            }
            return false;
        }

        public final boolean isNull() {
            for (int i2 = 0; i2 < 9; i2++) {
                if (this.f1663a.f1667c[i2] != 0.0f) {
                    return false;
                }
            }
            return true;
        }

        public final boolean isSmallerThan(SolverVariable solverVariable) {
            int i2 = 8;
            while (true) {
                if (i2 < 0) {
                    break;
                }
                float f2 = solverVariable.f1667c[i2];
                float f3 = this.f1663a.f1667c[i2];
                if (f3 == f2) {
                    i2--;
                } else if (f3 < f2) {
                    return true;
                }
            }
            return false;
        }

        public void reset() {
            Arrays.fill(this.f1663a.f1667c, 0.0f);
        }

        public String toString() {
            String str = "[ ";
            if (this.f1663a != null) {
                for (int i2 = 0; i2 < 9; i2++) {
                    str = str + this.f1663a.f1667c[i2] + " ";
                }
            }
            return str + "] " + this.f1663a;
        }
    }

    public PriorityGoalRow(Cache cache) {
        super(cache);
        this.TABLE_SIZE = 128;
        this.arrayGoals = new SolverVariable[128];
        this.sortArray = new SolverVariable[128];
        this.numGoals = 0;
        this.f1662f = new GoalVariableAccessor(this);
    }

    private final void addToGoal(SolverVariable solverVariable) {
        int i2;
        int i3 = this.numGoals + 1;
        SolverVariable[] solverVariableArr = this.arrayGoals;
        if (i3 > solverVariableArr.length) {
            SolverVariable[] solverVariableArr2 = (SolverVariable[]) Arrays.copyOf(solverVariableArr, solverVariableArr.length * 2);
            this.arrayGoals = solverVariableArr2;
            this.sortArray = (SolverVariable[]) Arrays.copyOf(solverVariableArr2, solverVariableArr2.length * 2);
        }
        SolverVariable[] solverVariableArr3 = this.arrayGoals;
        int i4 = this.numGoals;
        solverVariableArr3[i4] = solverVariable;
        int i5 = i4 + 1;
        this.numGoals = i5;
        if (i5 > 1 && solverVariableArr3[i5 - 1].id > solverVariable.id) {
            int i6 = 0;
            while (true) {
                i2 = this.numGoals;
                if (i6 >= i2) {
                    break;
                }
                this.sortArray[i6] = this.arrayGoals[i6];
                i6++;
            }
            Arrays.sort(this.sortArray, 0, i2, new Comparator<SolverVariable>(this) { // from class: androidx.constraintlayout.core.PriorityGoalRow.1
                @Override // java.util.Comparator
                public int compare(SolverVariable solverVariable2, SolverVariable solverVariable3) {
                    return solverVariable2.id - solverVariable3.id;
                }
            });
            for (int i7 = 0; i7 < this.numGoals; i7++) {
                this.arrayGoals[i7] = this.sortArray[i7];
            }
        }
        solverVariable.inGoal = true;
        solverVariable.addToRow(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void removeGoal(SolverVariable solverVariable) {
        int i2 = 0;
        while (i2 < this.numGoals) {
            if (this.arrayGoals[i2] == solverVariable) {
                while (true) {
                    int i3 = this.numGoals;
                    if (i2 >= i3 - 1) {
                        this.numGoals = i3 - 1;
                        solverVariable.inGoal = false;
                        return;
                    }
                    SolverVariable[] solverVariableArr = this.arrayGoals;
                    int i4 = i2 + 1;
                    solverVariableArr[i2] = solverVariableArr[i4];
                    i2 = i4;
                }
            } else {
                i2++;
            }
        }
    }

    @Override // androidx.constraintlayout.core.ArrayRow, androidx.constraintlayout.core.LinearSystem.Row
    public void addError(SolverVariable solverVariable) {
        this.f1662f.init(solverVariable);
        this.f1662f.reset();
        solverVariable.f1667c[solverVariable.strength] = 1.0f;
        addToGoal(solverVariable);
    }

    @Override // androidx.constraintlayout.core.ArrayRow, androidx.constraintlayout.core.LinearSystem.Row
    public void clear() {
        this.numGoals = 0;
        this.f1649b = 0.0f;
    }

    @Override // androidx.constraintlayout.core.ArrayRow, androidx.constraintlayout.core.LinearSystem.Row
    public SolverVariable getPivotCandidate(LinearSystem linearSystem, boolean[] zArr) {
        int i2 = -1;
        for (int i3 = 0; i3 < this.numGoals; i3++) {
            SolverVariable solverVariable = this.arrayGoals[i3];
            if (!zArr[solverVariable.id]) {
                this.f1662f.init(solverVariable);
                GoalVariableAccessor goalVariableAccessor = this.f1662f;
                if (i2 == -1) {
                    if (!goalVariableAccessor.isNegative()) {
                    }
                    i2 = i3;
                } else {
                    if (!goalVariableAccessor.isSmallerThan(this.arrayGoals[i2])) {
                    }
                    i2 = i3;
                }
            }
        }
        if (i2 == -1) {
            return null;
        }
        return this.arrayGoals[i2];
    }

    @Override // androidx.constraintlayout.core.ArrayRow, androidx.constraintlayout.core.LinearSystem.Row
    public boolean isEmpty() {
        return this.numGoals == 0;
    }

    @Override // androidx.constraintlayout.core.ArrayRow
    public String toString() {
        String str = " goal -> (" + this.f1649b + ") : ";
        for (int i2 = 0; i2 < this.numGoals; i2++) {
            this.f1662f.init(this.arrayGoals[i2]);
            str = str + this.f1662f + " ";
        }
        return str;
    }

    @Override // androidx.constraintlayout.core.ArrayRow, androidx.constraintlayout.core.LinearSystem.Row
    public void updateFromRow(LinearSystem linearSystem, ArrayRow arrayRow, boolean z) {
        SolverVariable solverVariable = arrayRow.f1648a;
        if (solverVariable == null) {
            return;
        }
        ArrayRow.ArrayRowVariables arrayRowVariables = arrayRow.variables;
        int currentSize = arrayRowVariables.getCurrentSize();
        for (int i2 = 0; i2 < currentSize; i2++) {
            SolverVariable variable = arrayRowVariables.getVariable(i2);
            float variableValue = arrayRowVariables.getVariableValue(i2);
            this.f1662f.init(variable);
            if (this.f1662f.addToGoal(solverVariable, variableValue)) {
                addToGoal(variable);
            }
            this.f1649b += arrayRow.f1649b * variableValue;
        }
        removeGoal(solverVariable);
    }
}
