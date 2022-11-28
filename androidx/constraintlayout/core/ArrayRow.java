package androidx.constraintlayout.core;

import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.SolverVariable;
import com.google.firebase.crashlytics.internal.common.IdManager;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class ArrayRow implements LinearSystem.Row {
    private static final boolean DEBUG = false;
    private static final boolean FULL_NEW_CHECK = false;

    /* renamed from: c  reason: collision with root package name */
    boolean f1650c;
    public ArrayRowVariables variables;

    /* renamed from: a  reason: collision with root package name */
    SolverVariable f1648a = null;

    /* renamed from: b  reason: collision with root package name */
    float f1649b = 0.0f;

    /* renamed from: d  reason: collision with root package name */
    ArrayList f1651d = new ArrayList();

    /* renamed from: e  reason: collision with root package name */
    boolean f1652e = false;

    /* loaded from: classes.dex */
    public interface ArrayRowVariables {
        void add(SolverVariable solverVariable, float f2, boolean z);

        void clear();

        boolean contains(SolverVariable solverVariable);

        void display();

        void divideByAmount(float f2);

        float get(SolverVariable solverVariable);

        int getCurrentSize();

        SolverVariable getVariable(int i2);

        float getVariableValue(int i2);

        int indexOf(SolverVariable solverVariable);

        void invert();

        void put(SolverVariable solverVariable, float f2);

        float remove(SolverVariable solverVariable, boolean z);

        int sizeInBytes();

        float use(ArrayRow arrayRow, boolean z);
    }

    public ArrayRow() {
    }

    public ArrayRow(Cache cache) {
        this.variables = new ArrayLinkedVariables(this, cache);
    }

    private boolean isNew(SolverVariable solverVariable, LinearSystem linearSystem) {
        return solverVariable.usageInRowCount <= 1;
    }

    private SolverVariable pickPivotInVariables(boolean[] zArr, SolverVariable solverVariable) {
        SolverVariable.Type type;
        int currentSize = this.variables.getCurrentSize();
        SolverVariable solverVariable2 = null;
        float f2 = 0.0f;
        for (int i2 = 0; i2 < currentSize; i2++) {
            float variableValue = this.variables.getVariableValue(i2);
            if (variableValue < 0.0f) {
                SolverVariable variable = this.variables.getVariable(i2);
                if ((zArr == null || !zArr[variable.id]) && variable != solverVariable && (((type = variable.f1668d) == SolverVariable.Type.SLACK || type == SolverVariable.Type.ERROR) && variableValue < f2)) {
                    f2 = variableValue;
                    solverVariable2 = variable;
                }
            }
        }
        return solverVariable2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ArrayRow a(SolverVariable solverVariable, int i2) {
        this.variables.put(solverVariable, i2);
        return this;
    }

    public ArrayRow addError(LinearSystem linearSystem, int i2) {
        this.variables.put(linearSystem.createErrorVariable(i2, "ep"), 1.0f);
        this.variables.put(linearSystem.createErrorVariable(i2, "em"), -1.0f);
        return this;
    }

    @Override // androidx.constraintlayout.core.LinearSystem.Row
    public void addError(SolverVariable solverVariable) {
        int i2 = solverVariable.strength;
        float f2 = 1.0f;
        if (i2 != 1) {
            if (i2 == 2) {
                f2 = 1000.0f;
            } else if (i2 == 3) {
                f2 = 1000000.0f;
            } else if (i2 == 4) {
                f2 = 1.0E9f;
            } else if (i2 == 5) {
                f2 = 1.0E12f;
            }
        }
        this.variables.put(solverVariable, f2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean b(LinearSystem linearSystem) {
        boolean z;
        SolverVariable c2 = c(linearSystem);
        if (c2 == null) {
            z = true;
        } else {
            j(c2);
            z = false;
        }
        if (this.variables.getCurrentSize() == 0) {
            this.f1652e = true;
        }
        return z;
    }

    SolverVariable c(LinearSystem linearSystem) {
        int currentSize = this.variables.getCurrentSize();
        SolverVariable solverVariable = null;
        boolean z = false;
        boolean z2 = false;
        float f2 = 0.0f;
        float f3 = 0.0f;
        SolverVariable solverVariable2 = null;
        for (int i2 = 0; i2 < currentSize; i2++) {
            float variableValue = this.variables.getVariableValue(i2);
            SolverVariable variable = this.variables.getVariable(i2);
            if (variable.f1668d == SolverVariable.Type.UNRESTRICTED) {
                if (solverVariable == null || f2 > variableValue) {
                    z = isNew(variable, linearSystem);
                    f2 = variableValue;
                    solverVariable = variable;
                } else if (!z && isNew(variable, linearSystem)) {
                    f2 = variableValue;
                    solverVariable = variable;
                    z = true;
                }
            } else if (solverVariable == null && variableValue < 0.0f) {
                if (solverVariable2 == null || f3 > variableValue) {
                    z2 = isNew(variable, linearSystem);
                    f3 = variableValue;
                    solverVariable2 = variable;
                } else if (!z2 && isNew(variable, linearSystem)) {
                    f3 = variableValue;
                    solverVariable2 = variable;
                    z2 = true;
                }
            }
        }
        return solverVariable != null ? solverVariable : solverVariable2;
    }

    @Override // androidx.constraintlayout.core.LinearSystem.Row
    public void clear() {
        this.variables.clear();
        this.f1648a = null;
        this.f1649b = 0.0f;
    }

    public ArrayRow createRowDimensionRatio(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f2) {
        this.variables.put(solverVariable, -1.0f);
        this.variables.put(solverVariable2, 1.0f);
        this.variables.put(solverVariable3, f2);
        this.variables.put(solverVariable4, -f2);
        return this;
    }

    public ArrayRow createRowEqualDimension(float f2, float f3, float f4, SolverVariable solverVariable, int i2, SolverVariable solverVariable2, int i3, SolverVariable solverVariable3, int i4, SolverVariable solverVariable4, int i5) {
        if (f3 == 0.0f || f2 == f4) {
            this.f1649b = ((-i2) - i3) + i4 + i5;
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable2, -1.0f);
            this.variables.put(solverVariable4, 1.0f);
            this.variables.put(solverVariable3, -1.0f);
        } else {
            float f5 = (f2 / f3) / (f4 / f3);
            this.f1649b = ((-i2) - i3) + (i4 * f5) + (i5 * f5);
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable2, -1.0f);
            this.variables.put(solverVariable4, f5);
            this.variables.put(solverVariable3, -f5);
        }
        return this;
    }

    public ArrayRow createRowEqualMatchDimensions(float f2, float f3, float f4, SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4) {
        this.f1649b = 0.0f;
        if (f3 == 0.0f || f2 == f4) {
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable2, -1.0f);
            this.variables.put(solverVariable4, 1.0f);
            this.variables.put(solverVariable3, -1.0f);
        } else if (f2 == 0.0f) {
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable2, -1.0f);
        } else if (f4 == 0.0f) {
            this.variables.put(solverVariable3, 1.0f);
            this.variables.put(solverVariable4, -1.0f);
        } else {
            float f5 = (f2 / f3) / (f4 / f3);
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable2, -1.0f);
            this.variables.put(solverVariable4, f5);
            this.variables.put(solverVariable3, -f5);
        }
        return this;
    }

    public ArrayRow createRowEquals(SolverVariable solverVariable, int i2) {
        ArrayRowVariables arrayRowVariables;
        float f2;
        if (i2 < 0) {
            this.f1649b = i2 * (-1);
            arrayRowVariables = this.variables;
            f2 = 1.0f;
        } else {
            this.f1649b = i2;
            arrayRowVariables = this.variables;
            f2 = -1.0f;
        }
        arrayRowVariables.put(solverVariable, f2);
        return this;
    }

    public ArrayRow createRowEquals(SolverVariable solverVariable, SolverVariable solverVariable2, int i2) {
        boolean z = false;
        if (i2 != 0) {
            if (i2 < 0) {
                i2 *= -1;
                z = true;
            }
            this.f1649b = i2;
        }
        if (z) {
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable2, -1.0f);
        } else {
            this.variables.put(solverVariable, -1.0f);
            this.variables.put(solverVariable2, 1.0f);
        }
        return this;
    }

    public ArrayRow createRowGreaterThan(SolverVariable solverVariable, int i2, SolverVariable solverVariable2) {
        this.f1649b = i2;
        this.variables.put(solverVariable, -1.0f);
        return this;
    }

    public ArrayRow createRowGreaterThan(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, int i2) {
        boolean z = false;
        if (i2 != 0) {
            if (i2 < 0) {
                i2 *= -1;
                z = true;
            }
            this.f1649b = i2;
        }
        if (z) {
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable2, -1.0f);
            this.variables.put(solverVariable3, -1.0f);
        } else {
            this.variables.put(solverVariable, -1.0f);
            this.variables.put(solverVariable2, 1.0f);
            this.variables.put(solverVariable3, 1.0f);
        }
        return this;
    }

    public ArrayRow createRowLowerThan(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, int i2) {
        boolean z = false;
        if (i2 != 0) {
            if (i2 < 0) {
                i2 *= -1;
                z = true;
            }
            this.f1649b = i2;
        }
        if (z) {
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable2, -1.0f);
            this.variables.put(solverVariable3, 1.0f);
        } else {
            this.variables.put(solverVariable, -1.0f);
            this.variables.put(solverVariable2, 1.0f);
            this.variables.put(solverVariable3, -1.0f);
        }
        return this;
    }

    public ArrayRow createRowWithAngle(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f2) {
        this.variables.put(solverVariable3, 0.5f);
        this.variables.put(solverVariable4, 0.5f);
        this.variables.put(solverVariable, -0.5f);
        this.variables.put(solverVariable2, -0.5f);
        this.f1649b = -f2;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ArrayRow d(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, float f2, SolverVariable solverVariable3, SolverVariable solverVariable4, int i3) {
        float f3;
        int i4;
        if (solverVariable2 == solverVariable3) {
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable4, 1.0f);
            this.variables.put(solverVariable2, -2.0f);
            return this;
        }
        if (f2 == 0.5f) {
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable2, -1.0f);
            this.variables.put(solverVariable3, -1.0f);
            this.variables.put(solverVariable4, 1.0f);
            if (i2 > 0 || i3 > 0) {
                i4 = (-i2) + i3;
                f3 = i4;
            }
            return this;
        } else if (f2 <= 0.0f) {
            this.variables.put(solverVariable, -1.0f);
            this.variables.put(solverVariable2, 1.0f);
            f3 = i2;
        } else if (f2 < 1.0f) {
            float f4 = 1.0f - f2;
            this.variables.put(solverVariable, f4 * 1.0f);
            this.variables.put(solverVariable2, f4 * (-1.0f));
            this.variables.put(solverVariable3, (-1.0f) * f2);
            this.variables.put(solverVariable4, 1.0f * f2);
            if (i2 > 0 || i3 > 0) {
                f3 = ((-i2) * f4) + (i3 * f2);
            }
            return this;
        } else {
            this.variables.put(solverVariable4, -1.0f);
            this.variables.put(solverVariable3, 1.0f);
            i4 = -i3;
            f3 = i4;
        }
        this.f1649b = f3;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ArrayRow e(SolverVariable solverVariable, int i2) {
        this.f1648a = solverVariable;
        float f2 = i2;
        solverVariable.computedValue = f2;
        this.f1649b = f2;
        this.f1652e = true;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ArrayRow f(SolverVariable solverVariable, SolverVariable solverVariable2, float f2) {
        this.variables.put(solverVariable, -1.0f);
        this.variables.put(solverVariable2, f2);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g() {
        float f2 = this.f1649b;
        if (f2 < 0.0f) {
            this.f1649b = f2 * (-1.0f);
            this.variables.invert();
        }
    }

    @Override // androidx.constraintlayout.core.LinearSystem.Row
    public SolverVariable getKey() {
        return this.f1648a;
    }

    @Override // androidx.constraintlayout.core.LinearSystem.Row
    public SolverVariable getPivotCandidate(LinearSystem linearSystem, boolean[] zArr) {
        return pickPivotInVariables(zArr, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean h() {
        SolverVariable solverVariable = this.f1648a;
        return solverVariable != null && (solverVariable.f1668d == SolverVariable.Type.UNRESTRICTED || this.f1649b >= 0.0f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean i(SolverVariable solverVariable) {
        return this.variables.contains(solverVariable);
    }

    @Override // androidx.constraintlayout.core.LinearSystem.Row
    public void initFromRow(LinearSystem.Row row) {
        if (row instanceof ArrayRow) {
            ArrayRow arrayRow = (ArrayRow) row;
            this.f1648a = null;
            this.variables.clear();
            for (int i2 = 0; i2 < arrayRow.variables.getCurrentSize(); i2++) {
                this.variables.add(arrayRow.variables.getVariable(i2), arrayRow.variables.getVariableValue(i2), true);
            }
        }
    }

    @Override // androidx.constraintlayout.core.LinearSystem.Row
    public boolean isEmpty() {
        return this.f1648a == null && this.f1649b == 0.0f && this.variables.getCurrentSize() == 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void j(SolverVariable solverVariable) {
        SolverVariable solverVariable2 = this.f1648a;
        if (solverVariable2 != null) {
            this.variables.put(solverVariable2, -1.0f);
            this.f1648a.f1665a = -1;
            this.f1648a = null;
        }
        float remove = this.variables.remove(solverVariable, true) * (-1.0f);
        this.f1648a = solverVariable;
        if (remove == 1.0f) {
            return;
        }
        this.f1649b /= remove;
        this.variables.divideByAmount(remove);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int k() {
        return (this.f1648a != null ? 4 : 0) + 4 + 4 + this.variables.sizeInBytes();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00b6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public String l() {
        StringBuilder sb;
        boolean z;
        float variableValue;
        int i2;
        StringBuilder sb2;
        String str;
        StringBuilder sb3;
        if (this.f1648a == null) {
            sb = new StringBuilder();
            sb.append("");
            sb.append("0");
        } else {
            sb = new StringBuilder();
            sb.append("");
            sb.append(this.f1648a);
        }
        String str2 = sb.toString() + " = ";
        if (this.f1649b != 0.0f) {
            str2 = str2 + this.f1649b;
            z = true;
        } else {
            z = false;
        }
        int currentSize = this.variables.getCurrentSize();
        for (int i3 = 0; i3 < currentSize; i3++) {
            SolverVariable variable = this.variables.getVariable(i3);
            if (variable != null && (this.variables.getVariableValue(i3)) != 0.0f) {
                String solverVariable = variable.toString();
                if (!z) {
                    if (variableValue < 0.0f) {
                        sb2 = new StringBuilder();
                        sb2.append(str2);
                        str = "- ";
                        sb2.append(str);
                        str2 = sb2.toString();
                        variableValue *= -1.0f;
                    }
                    if (variableValue == 1.0f) {
                        sb3 = new StringBuilder();
                    } else {
                        sb3 = new StringBuilder();
                        sb3.append(str2);
                        sb3.append(variableValue);
                        str2 = " ";
                    }
                    sb3.append(str2);
                    sb3.append(solverVariable);
                    str2 = sb3.toString();
                    z = true;
                } else if (i2 > 0) {
                    str2 = str2 + " + ";
                    if (variableValue == 1.0f) {
                    }
                    sb3.append(str2);
                    sb3.append(solverVariable);
                    str2 = sb3.toString();
                    z = true;
                } else {
                    sb2 = new StringBuilder();
                    sb2.append(str2);
                    str = " - ";
                    sb2.append(str);
                    str2 = sb2.toString();
                    variableValue *= -1.0f;
                    if (variableValue == 1.0f) {
                    }
                    sb3.append(str2);
                    sb3.append(solverVariable);
                    str2 = sb3.toString();
                    z = true;
                }
            }
        }
        if (z) {
            return str2;
        }
        return str2 + IdManager.DEFAULT_VERSION_NAME;
    }

    public SolverVariable pickPivot(SolverVariable solverVariable) {
        return pickPivotInVariables(null, solverVariable);
    }

    public void reset() {
        this.f1648a = null;
        this.variables.clear();
        this.f1649b = 0.0f;
        this.f1652e = false;
    }

    public String toString() {
        return l();
    }

    @Override // androidx.constraintlayout.core.LinearSystem.Row
    public void updateFromFinalVariable(LinearSystem linearSystem, SolverVariable solverVariable, boolean z) {
        if (solverVariable == null || !solverVariable.isFinalValue) {
            return;
        }
        this.f1649b += solverVariable.computedValue * this.variables.get(solverVariable);
        this.variables.remove(solverVariable, z);
        if (z) {
            solverVariable.removeFromRow(this);
        }
        if (LinearSystem.SIMPLIFY_SYNONYMS && this.variables.getCurrentSize() == 0) {
            this.f1652e = true;
            linearSystem.hasSimpleDefinition = true;
        }
    }

    @Override // androidx.constraintlayout.core.LinearSystem.Row
    public void updateFromRow(LinearSystem linearSystem, ArrayRow arrayRow, boolean z) {
        this.f1649b += arrayRow.f1649b * this.variables.use(arrayRow, z);
        if (z) {
            arrayRow.f1648a.removeFromRow(this);
        }
        if (LinearSystem.SIMPLIFY_SYNONYMS && this.f1648a != null && this.variables.getCurrentSize() == 0) {
            this.f1652e = true;
            linearSystem.hasSimpleDefinition = true;
        }
    }

    public void updateFromSynonymVariable(LinearSystem linearSystem, SolverVariable solverVariable, boolean z) {
        if (solverVariable == null || !solverVariable.f1671g) {
            return;
        }
        float f2 = this.variables.get(solverVariable);
        this.f1649b += solverVariable.f1673i * f2;
        this.variables.remove(solverVariable, z);
        if (z) {
            solverVariable.removeFromRow(this);
        }
        this.variables.add(linearSystem.f1661e.f1656d[solverVariable.f1672h], f2, z);
        if (LinearSystem.SIMPLIFY_SYNONYMS && this.variables.getCurrentSize() == 0) {
            this.f1652e = true;
            linearSystem.hasSimpleDefinition = true;
        }
    }

    @Override // androidx.constraintlayout.core.LinearSystem.Row
    public void updateFromSystem(LinearSystem linearSystem) {
        if (linearSystem.f1658b.length == 0) {
            return;
        }
        boolean z = false;
        while (!z) {
            int currentSize = this.variables.getCurrentSize();
            for (int i2 = 0; i2 < currentSize; i2++) {
                SolverVariable variable = this.variables.getVariable(i2);
                if (variable.f1665a != -1 || variable.isFinalValue || variable.f1671g) {
                    this.f1651d.add(variable);
                }
            }
            int size = this.f1651d.size();
            if (size > 0) {
                for (int i3 = 0; i3 < size; i3++) {
                    SolverVariable solverVariable = (SolverVariable) this.f1651d.get(i3);
                    if (solverVariable.isFinalValue) {
                        updateFromFinalVariable(linearSystem, solverVariable, true);
                    } else if (solverVariable.f1671g) {
                        updateFromSynonymVariable(linearSystem, solverVariable, true);
                    } else {
                        updateFromRow(linearSystem, linearSystem.f1658b[solverVariable.f1665a], true);
                    }
                }
                this.f1651d.clear();
            } else {
                z = true;
            }
        }
        if (LinearSystem.SIMPLIFY_SYNONYMS && this.f1648a != null && this.variables.getCurrentSize() == 0) {
            this.f1652e = true;
            linearSystem.hasSimpleDefinition = true;
        }
    }
}
