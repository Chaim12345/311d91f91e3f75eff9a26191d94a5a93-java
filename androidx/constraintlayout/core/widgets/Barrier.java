package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.SolverVariable;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import java.util.HashMap;
/* loaded from: classes.dex */
public class Barrier extends HelperWidget {
    public static final int BOTTOM = 3;
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int TOP = 2;
    private static final boolean USE_RELAX_GONE = false;
    private static final boolean USE_RESOLUTION = true;
    private int mBarrierType = 0;
    private boolean mAllowsGoneWidget = true;
    private int mMargin = 0;
    boolean L = false;

    public Barrier() {
    }

    public Barrier(String str) {
        setDebugName(str);
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public void addToSolver(LinearSystem linearSystem, boolean z) {
        Object[] objArr;
        boolean z2;
        SolverVariable solverVariable;
        ConstraintAnchor constraintAnchor;
        int i2;
        int i3;
        int i4;
        SolverVariable solverVariable2;
        int i5;
        ConstraintAnchor[] constraintAnchorArr = this.mListAnchors;
        constraintAnchorArr[0] = this.mLeft;
        constraintAnchorArr[2] = this.mTop;
        constraintAnchorArr[1] = this.mRight;
        constraintAnchorArr[3] = this.mBottom;
        int i6 = 0;
        while (true) {
            objArr = this.mListAnchors;
            if (i6 >= objArr.length) {
                break;
            }
            objArr[i6].f1947b = linearSystem.createObjectVariable(objArr[i6]);
            i6++;
        }
        int i7 = this.mBarrierType;
        if (i7 < 0 || i7 >= 4) {
            return;
        }
        ConstraintAnchor constraintAnchor2 = objArr[i7];
        if (!this.L) {
            allSolved();
        }
        if (this.L) {
            this.L = false;
            int i8 = this.mBarrierType;
            if (i8 == 0 || i8 == 1) {
                linearSystem.addEquality(this.mLeft.f1947b, this.f1957i);
                solverVariable2 = this.mRight.f1947b;
                i5 = this.f1957i;
            } else if (i8 != 2 && i8 != 3) {
                return;
            } else {
                linearSystem.addEquality(this.mTop.f1947b, this.f1958j);
                solverVariable2 = this.mBottom.f1947b;
                i5 = this.f1958j;
            }
            linearSystem.addEquality(solverVariable2, i5);
            return;
        }
        for (int i9 = 0; i9 < this.mWidgetsCount; i9++) {
            ConstraintWidget constraintWidget = this.mWidgets[i9];
            if ((this.mAllowsGoneWidget || constraintWidget.allowedInBarrier()) && ((((i3 = this.mBarrierType) == 0 || i3 == 1) && constraintWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mLeft.mTarget != null && constraintWidget.mRight.mTarget != null) || (((i4 = this.mBarrierType) == 2 || i4 == 3) && constraintWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mTop.mTarget != null && constraintWidget.mBottom.mTarget != null))) {
                z2 = true;
                break;
            }
        }
        z2 = false;
        boolean z3 = this.mLeft.hasCenteredDependents() || this.mRight.hasCenteredDependents();
        boolean z4 = this.mTop.hasCenteredDependents() || this.mBottom.hasCenteredDependents();
        int i10 = !z2 && (((i2 = this.mBarrierType) == 0 && z3) || ((i2 == 2 && z4) || ((i2 == 1 && z3) || (i2 == 3 && z4)))) ? 5 : 4;
        for (int i11 = 0; i11 < this.mWidgetsCount; i11++) {
            ConstraintWidget constraintWidget2 = this.mWidgets[i11];
            if (this.mAllowsGoneWidget || constraintWidget2.allowedInBarrier()) {
                SolverVariable createObjectVariable = linearSystem.createObjectVariable(constraintWidget2.mListAnchors[this.mBarrierType]);
                ConstraintAnchor[] constraintAnchorArr2 = constraintWidget2.mListAnchors;
                int i12 = this.mBarrierType;
                constraintAnchorArr2[i12].f1947b = createObjectVariable;
                int i13 = (constraintAnchorArr2[i12].mTarget == null || constraintAnchorArr2[i12].mTarget.mOwner != this) ? 0 : constraintAnchorArr2[i12].mMargin + 0;
                if (i12 == 0 || i12 == 2) {
                    linearSystem.addLowerBarrier(constraintAnchor2.f1947b, createObjectVariable, this.mMargin - i13, z2);
                } else {
                    linearSystem.addGreaterBarrier(constraintAnchor2.f1947b, createObjectVariable, this.mMargin + i13, z2);
                }
                linearSystem.addEquality(constraintAnchor2.f1947b, createObjectVariable, this.mMargin + i13, i10);
            }
        }
        int i14 = this.mBarrierType;
        if (i14 == 0) {
            linearSystem.addEquality(this.mRight.f1947b, this.mLeft.f1947b, 0, 8);
            linearSystem.addEquality(this.mLeft.f1947b, this.mParent.mRight.f1947b, 0, 4);
            solverVariable = this.mLeft.f1947b;
            constraintAnchor = this.mParent.mLeft;
        } else if (i14 == 1) {
            linearSystem.addEquality(this.mLeft.f1947b, this.mRight.f1947b, 0, 8);
            linearSystem.addEquality(this.mLeft.f1947b, this.mParent.mLeft.f1947b, 0, 4);
            solverVariable = this.mLeft.f1947b;
            constraintAnchor = this.mParent.mRight;
        } else if (i14 == 2) {
            linearSystem.addEquality(this.mBottom.f1947b, this.mTop.f1947b, 0, 8);
            linearSystem.addEquality(this.mTop.f1947b, this.mParent.mBottom.f1947b, 0, 4);
            solverVariable = this.mTop.f1947b;
            constraintAnchor = this.mParent.mTop;
        } else if (i14 != 3) {
            return;
        } else {
            linearSystem.addEquality(this.mTop.f1947b, this.mBottom.f1947b, 0, 8);
            linearSystem.addEquality(this.mTop.f1947b, this.mParent.mTop.f1947b, 0, 4);
            solverVariable = this.mTop.f1947b;
            constraintAnchor = this.mParent.mBottom;
        }
        linearSystem.addEquality(solverVariable, constraintAnchor.f1947b, 0, 0);
    }

    public boolean allSolved() {
        int i2;
        ConstraintAnchor.Type type;
        ConstraintAnchor.Type type2;
        ConstraintAnchor.Type type3;
        int i3;
        int i4;
        int i5 = 0;
        boolean z = true;
        while (true) {
            i2 = this.mWidgetsCount;
            if (i5 >= i2) {
                break;
            }
            ConstraintWidget constraintWidget = this.mWidgets[i5];
            if ((this.mAllowsGoneWidget || constraintWidget.allowedInBarrier()) && ((((i3 = this.mBarrierType) == 0 || i3 == 1) && !constraintWidget.isResolvedHorizontally()) || (((i4 = this.mBarrierType) == 2 || i4 == 3) && !constraintWidget.isResolvedVertically()))) {
                z = false;
            }
            i5++;
        }
        if (!z || i2 <= 0) {
            return false;
        }
        int i6 = 0;
        boolean z2 = false;
        for (int i7 = 0; i7 < this.mWidgetsCount; i7++) {
            ConstraintWidget constraintWidget2 = this.mWidgets[i7];
            if (this.mAllowsGoneWidget || constraintWidget2.allowedInBarrier()) {
                if (!z2) {
                    int i8 = this.mBarrierType;
                    if (i8 == 0) {
                        type3 = ConstraintAnchor.Type.LEFT;
                    } else if (i8 == 1) {
                        type3 = ConstraintAnchor.Type.RIGHT;
                    } else if (i8 == 2) {
                        type3 = ConstraintAnchor.Type.TOP;
                    } else {
                        if (i8 == 3) {
                            type3 = ConstraintAnchor.Type.BOTTOM;
                        }
                        z2 = true;
                    }
                    i6 = constraintWidget2.getAnchor(type3).getFinalValue();
                    z2 = true;
                }
                int i9 = this.mBarrierType;
                if (i9 == 0) {
                    type2 = ConstraintAnchor.Type.LEFT;
                } else {
                    if (i9 == 1) {
                        type = ConstraintAnchor.Type.RIGHT;
                    } else if (i9 == 2) {
                        type2 = ConstraintAnchor.Type.TOP;
                    } else if (i9 == 3) {
                        type = ConstraintAnchor.Type.BOTTOM;
                    }
                    i6 = Math.max(i6, constraintWidget2.getAnchor(type).getFinalValue());
                }
                i6 = Math.min(i6, constraintWidget2.getAnchor(type2).getFinalValue());
            }
        }
        int i10 = i6 + this.mMargin;
        int i11 = this.mBarrierType;
        if (i11 == 0 || i11 == 1) {
            setFinalHorizontal(i10, i10);
        } else {
            setFinalVertical(i10, i10);
        }
        this.L = true;
        return true;
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public boolean allowedInBarrier() {
        return true;
    }

    @Deprecated
    public boolean allowsGoneWidget() {
        return this.mAllowsGoneWidget;
    }

    @Override // androidx.constraintlayout.core.widgets.HelperWidget, androidx.constraintlayout.core.widgets.ConstraintWidget
    public void copy(ConstraintWidget constraintWidget, HashMap<ConstraintWidget, ConstraintWidget> hashMap) {
        super.copy(constraintWidget, hashMap);
        Barrier barrier = (Barrier) constraintWidget;
        this.mBarrierType = barrier.mBarrierType;
        this.mAllowsGoneWidget = barrier.mAllowsGoneWidget;
        this.mMargin = barrier.mMargin;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void e() {
        for (int i2 = 0; i2 < this.mWidgetsCount; i2++) {
            ConstraintWidget constraintWidget = this.mWidgets[i2];
            if (this.mAllowsGoneWidget || constraintWidget.allowedInBarrier()) {
                int i3 = this.mBarrierType;
                if (i3 == 0 || i3 == 1) {
                    constraintWidget.d(0, true);
                } else if (i3 == 2 || i3 == 3) {
                    constraintWidget.d(1, true);
                }
            }
        }
    }

    public boolean getAllowsGoneWidget() {
        return this.mAllowsGoneWidget;
    }

    public int getBarrierType() {
        return this.mBarrierType;
    }

    public int getMargin() {
        return this.mMargin;
    }

    public int getOrientation() {
        int i2 = this.mBarrierType;
        if (i2 == 0 || i2 == 1) {
            return 0;
        }
        return (i2 == 2 || i2 == 3) ? 1 : -1;
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public boolean isResolvedHorizontally() {
        return this.L;
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public boolean isResolvedVertically() {
        return this.L;
    }

    public void setAllowsGoneWidget(boolean z) {
        this.mAllowsGoneWidget = z;
    }

    public void setBarrierType(int i2) {
        this.mBarrierType = i2;
    }

    public void setMargin(int i2) {
        this.mMargin = i2;
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public String toString() {
        String str = "[Barrier] " + getDebugName() + " {";
        for (int i2 = 0; i2 < this.mWidgetsCount; i2++) {
            ConstraintWidget constraintWidget = this.mWidgets[i2];
            if (i2 > 0) {
                str = str + ", ";
            }
            str = str + constraintWidget.getDebugName();
        }
        return str + "}";
    }
}
