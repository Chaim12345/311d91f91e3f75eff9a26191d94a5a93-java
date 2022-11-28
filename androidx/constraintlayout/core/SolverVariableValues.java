package androidx.constraintlayout.core;

import androidx.constraintlayout.core.ArrayRow;
import java.io.PrintStream;
import java.util.Arrays;
/* loaded from: classes.dex */
public class SolverVariableValues implements ArrayRow.ArrayRowVariables {
    private static final boolean DEBUG = false;
    private static final boolean HASH = true;
    private static float epsilon = 0.001f;

    /* renamed from: i  reason: collision with root package name */
    protected final Cache f1683i;
    private final ArrayRow mRow;
    private final int NONE = -1;
    private int SIZE = 16;
    private int HASH_SIZE = 16;

    /* renamed from: a  reason: collision with root package name */
    int[] f1675a = new int[16];

    /* renamed from: b  reason: collision with root package name */
    int[] f1676b = new int[16];

    /* renamed from: c  reason: collision with root package name */
    int[] f1677c = new int[16];

    /* renamed from: d  reason: collision with root package name */
    float[] f1678d = new float[16];

    /* renamed from: e  reason: collision with root package name */
    int[] f1679e = new int[16];

    /* renamed from: f  reason: collision with root package name */
    int[] f1680f = new int[16];

    /* renamed from: g  reason: collision with root package name */
    int f1681g = 0;

    /* renamed from: h  reason: collision with root package name */
    int f1682h = -1;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SolverVariableValues(ArrayRow arrayRow, Cache cache) {
        this.mRow = arrayRow;
        this.f1683i = cache;
        clear();
    }

    private void addToHashMap(SolverVariable solverVariable, int i2) {
        int[] iArr;
        int i3 = solverVariable.id % this.HASH_SIZE;
        int[] iArr2 = this.f1675a;
        int i4 = iArr2[i3];
        if (i4 == -1) {
            iArr2[i3] = i2;
        } else {
            while (true) {
                iArr = this.f1676b;
                if (iArr[i4] == -1) {
                    break;
                }
                i4 = iArr[i4];
            }
            iArr[i4] = i2;
        }
        this.f1676b[i2] = -1;
    }

    private void addVariable(int i2, SolverVariable solverVariable, float f2) {
        this.f1677c[i2] = solverVariable.id;
        this.f1678d[i2] = f2;
        this.f1679e[i2] = -1;
        this.f1680f[i2] = -1;
        solverVariable.addToRow(this.mRow);
        solverVariable.usageInRowCount++;
        this.f1681g++;
    }

    private void displayHash() {
        for (int i2 = 0; i2 < this.HASH_SIZE; i2++) {
            if (this.f1675a[i2] != -1) {
                String str = hashCode() + " hash [" + i2 + "] => ";
                int i3 = this.f1675a[i2];
                boolean z = false;
                while (!z) {
                    str = str + " " + this.f1677c[i3];
                    int[] iArr = this.f1676b;
                    if (iArr[i3] != -1) {
                        i3 = iArr[i3];
                    } else {
                        z = true;
                    }
                }
                System.out.println(str);
            }
        }
    }

    private int findEmptySlot() {
        for (int i2 = 0; i2 < this.SIZE; i2++) {
            if (this.f1677c[i2] == -1) {
                return i2;
            }
        }
        return -1;
    }

    private void increaseSize() {
        int i2 = this.SIZE * 2;
        this.f1677c = Arrays.copyOf(this.f1677c, i2);
        this.f1678d = Arrays.copyOf(this.f1678d, i2);
        this.f1679e = Arrays.copyOf(this.f1679e, i2);
        this.f1680f = Arrays.copyOf(this.f1680f, i2);
        this.f1676b = Arrays.copyOf(this.f1676b, i2);
        for (int i3 = this.SIZE; i3 < i2; i3++) {
            this.f1677c[i3] = -1;
            this.f1676b[i3] = -1;
        }
        this.SIZE = i2;
    }

    private void insertVariable(int i2, SolverVariable solverVariable, float f2) {
        int findEmptySlot = findEmptySlot();
        addVariable(findEmptySlot, solverVariable, f2);
        if (i2 != -1) {
            this.f1679e[findEmptySlot] = i2;
            int[] iArr = this.f1680f;
            iArr[findEmptySlot] = iArr[i2];
            iArr[i2] = findEmptySlot;
        } else {
            this.f1679e[findEmptySlot] = -1;
            if (this.f1681g > 0) {
                this.f1680f[findEmptySlot] = this.f1682h;
                this.f1682h = findEmptySlot;
            } else {
                this.f1680f[findEmptySlot] = -1;
            }
        }
        int[] iArr2 = this.f1680f;
        if (iArr2[findEmptySlot] != -1) {
            this.f1679e[iArr2[findEmptySlot]] = findEmptySlot;
        }
        addToHashMap(solverVariable, findEmptySlot);
    }

    private void removeFromHashMap(SolverVariable solverVariable) {
        int[] iArr;
        int i2 = solverVariable.id;
        int i3 = i2 % this.HASH_SIZE;
        int[] iArr2 = this.f1675a;
        int i4 = iArr2[i3];
        if (i4 == -1) {
            return;
        }
        if (this.f1677c[i4] == i2) {
            int[] iArr3 = this.f1676b;
            iArr2[i3] = iArr3[i4];
            iArr3[i4] = -1;
            return;
        }
        while (true) {
            iArr = this.f1676b;
            if (iArr[i4] == -1 || this.f1677c[iArr[i4]] == i2) {
                break;
            }
            i4 = iArr[i4];
        }
        int i5 = iArr[i4];
        if (i5 == -1 || this.f1677c[i5] != i2) {
            return;
        }
        iArr[i4] = iArr[i5];
        iArr[i5] = -1;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public void add(SolverVariable solverVariable, float f2, boolean z) {
        float f3 = epsilon;
        if (f2 <= (-f3) || f2 >= f3) {
            int indexOf = indexOf(solverVariable);
            if (indexOf == -1) {
                put(solverVariable, f2);
                return;
            }
            float[] fArr = this.f1678d;
            fArr[indexOf] = fArr[indexOf] + f2;
            float f4 = fArr[indexOf];
            float f5 = epsilon;
            if (f4 <= (-f5) || fArr[indexOf] >= f5) {
                return;
            }
            fArr[indexOf] = 0.0f;
            remove(solverVariable, z);
        }
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public void clear() {
        int i2 = this.f1681g;
        for (int i3 = 0; i3 < i2; i3++) {
            SolverVariable variable = getVariable(i3);
            if (variable != null) {
                variable.removeFromRow(this.mRow);
            }
        }
        for (int i4 = 0; i4 < this.SIZE; i4++) {
            this.f1677c[i4] = -1;
            this.f1676b[i4] = -1;
        }
        for (int i5 = 0; i5 < this.HASH_SIZE; i5++) {
            this.f1675a[i5] = -1;
        }
        this.f1681g = 0;
        this.f1682h = -1;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public boolean contains(SolverVariable solverVariable) {
        return indexOf(solverVariable) != -1;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public void display() {
        int i2 = this.f1681g;
        System.out.print("{ ");
        for (int i3 = 0; i3 < i2; i3++) {
            SolverVariable variable = getVariable(i3);
            if (variable != null) {
                PrintStream printStream = System.out;
                printStream.print(variable + " = " + getVariableValue(i3) + " ");
            }
        }
        System.out.println(" }");
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public void divideByAmount(float f2) {
        int i2 = this.f1681g;
        int i3 = this.f1682h;
        for (int i4 = 0; i4 < i2; i4++) {
            float[] fArr = this.f1678d;
            fArr[i3] = fArr[i3] / f2;
            i3 = this.f1680f[i3];
            if (i3 == -1) {
                return;
            }
        }
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public float get(SolverVariable solverVariable) {
        int indexOf = indexOf(solverVariable);
        if (indexOf != -1) {
            return this.f1678d[indexOf];
        }
        return 0.0f;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public int getCurrentSize() {
        return this.f1681g;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public SolverVariable getVariable(int i2) {
        int i3 = this.f1681g;
        if (i3 == 0) {
            return null;
        }
        int i4 = this.f1682h;
        for (int i5 = 0; i5 < i3; i5++) {
            if (i5 == i2 && i4 != -1) {
                return this.f1683i.f1656d[this.f1677c[i4]];
            }
            i4 = this.f1680f[i4];
            if (i4 == -1) {
                break;
            }
        }
        return null;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public float getVariableValue(int i2) {
        int i3 = this.f1681g;
        int i4 = this.f1682h;
        for (int i5 = 0; i5 < i3; i5++) {
            if (i5 == i2) {
                return this.f1678d[i4];
            }
            i4 = this.f1680f[i4];
            if (i4 == -1) {
                return 0.0f;
            }
        }
        return 0.0f;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public int indexOf(SolverVariable solverVariable) {
        int[] iArr;
        if (this.f1681g != 0 && solverVariable != null) {
            int i2 = solverVariable.id;
            int i3 = this.f1675a[i2 % this.HASH_SIZE];
            if (i3 == -1) {
                return -1;
            }
            if (this.f1677c[i3] == i2) {
                return i3;
            }
            while (true) {
                iArr = this.f1676b;
                if (iArr[i3] == -1 || this.f1677c[iArr[i3]] == i2) {
                    break;
                }
                i3 = iArr[i3];
            }
            if (iArr[i3] != -1 && this.f1677c[iArr[i3]] == i2) {
                return iArr[i3];
            }
        }
        return -1;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public void invert() {
        int i2 = this.f1681g;
        int i3 = this.f1682h;
        for (int i4 = 0; i4 < i2; i4++) {
            float[] fArr = this.f1678d;
            fArr[i3] = fArr[i3] * (-1.0f);
            i3 = this.f1680f[i3];
            if (i3 == -1) {
                return;
            }
        }
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public void put(SolverVariable solverVariable, float f2) {
        float f3 = epsilon;
        if (f2 > (-f3) && f2 < f3) {
            remove(solverVariable, true);
            return;
        }
        if (this.f1681g == 0) {
            addVariable(0, solverVariable, f2);
            addToHashMap(solverVariable, 0);
            this.f1682h = 0;
            return;
        }
        int indexOf = indexOf(solverVariable);
        if (indexOf != -1) {
            this.f1678d[indexOf] = f2;
            return;
        }
        if (this.f1681g + 1 >= this.SIZE) {
            increaseSize();
        }
        int i2 = this.f1681g;
        int i3 = this.f1682h;
        int i4 = -1;
        for (int i5 = 0; i5 < i2; i5++) {
            int[] iArr = this.f1677c;
            int i6 = iArr[i3];
            int i7 = solverVariable.id;
            if (i6 == i7) {
                this.f1678d[i3] = f2;
                return;
            }
            if (iArr[i3] < i7) {
                i4 = i3;
            }
            i3 = this.f1680f[i3];
            if (i3 == -1) {
                break;
            }
        }
        insertVariable(i4, solverVariable, f2);
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public float remove(SolverVariable solverVariable, boolean z) {
        int indexOf = indexOf(solverVariable);
        if (indexOf == -1) {
            return 0.0f;
        }
        removeFromHashMap(solverVariable);
        float f2 = this.f1678d[indexOf];
        if (this.f1682h == indexOf) {
            this.f1682h = this.f1680f[indexOf];
        }
        this.f1677c[indexOf] = -1;
        int[] iArr = this.f1679e;
        if (iArr[indexOf] != -1) {
            int[] iArr2 = this.f1680f;
            iArr2[iArr[indexOf]] = iArr2[indexOf];
        }
        int[] iArr3 = this.f1680f;
        if (iArr3[indexOf] != -1) {
            iArr[iArr3[indexOf]] = iArr[indexOf];
        }
        this.f1681g--;
        solverVariable.usageInRowCount--;
        if (z) {
            solverVariable.removeFromRow(this.mRow);
        }
        return f2;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public int sizeInBytes() {
        return 0;
    }

    public String toString() {
        StringBuilder sb;
        String str = hashCode() + " { ";
        int i2 = this.f1681g;
        for (int i3 = 0; i3 < i2; i3++) {
            SolverVariable variable = getVariable(i3);
            if (variable != null) {
                String str2 = str + variable + " = " + getVariableValue(i3) + " ";
                int indexOf = indexOf(variable);
                String str3 = str2 + "[p: ";
                if (this.f1679e[indexOf] != -1) {
                    sb = new StringBuilder();
                    sb.append(str3);
                    sb.append(this.f1683i.f1656d[this.f1677c[this.f1679e[indexOf]]]);
                } else {
                    sb = new StringBuilder();
                    sb.append(str3);
                    sb.append("none");
                }
                String str4 = sb.toString() + ", n: ";
                str = (this.f1680f[indexOf] != -1 ? str4 + this.f1683i.f1656d[this.f1677c[this.f1680f[indexOf]]] : str4 + "none") + "]";
            }
        }
        return str + " }";
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public float use(ArrayRow arrayRow, boolean z) {
        float f2 = get(arrayRow.f1648a);
        remove(arrayRow.f1648a, z);
        SolverVariableValues solverVariableValues = (SolverVariableValues) arrayRow.variables;
        int currentSize = solverVariableValues.getCurrentSize();
        int i2 = 0;
        int i3 = 0;
        while (i2 < currentSize) {
            int[] iArr = solverVariableValues.f1677c;
            if (iArr[i3] != -1) {
                add(this.f1683i.f1656d[iArr[i3]], solverVariableValues.f1678d[i3] * f2, z);
                i2++;
            }
            i3++;
        }
        return f2;
    }
}
