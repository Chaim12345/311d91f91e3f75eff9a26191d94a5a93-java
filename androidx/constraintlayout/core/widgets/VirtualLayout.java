package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import java.util.HashSet;
/* loaded from: classes.dex */
public class VirtualLayout extends HelperWidget {
    private int mPaddingTop = 0;
    private int mPaddingBottom = 0;
    private int mPaddingLeft = 0;
    private int mPaddingRight = 0;
    private int mPaddingStart = 0;
    private int mPaddingEnd = 0;
    private int mResolvedPaddingLeft = 0;
    private int mResolvedPaddingRight = 0;
    private boolean mNeedsCallFromSolver = false;
    private int mMeasuredWidth = 0;
    private int mMeasuredHeight = 0;
    protected BasicMeasure.Measure L = new BasicMeasure.Measure();
    BasicMeasure.Measurer M = null;

    public void applyRtl(boolean z) {
        int i2 = this.mPaddingStart;
        if (i2 > 0 || this.mPaddingEnd > 0) {
            if (z) {
                this.mResolvedPaddingLeft = this.mPaddingEnd;
                this.mResolvedPaddingRight = i2;
                return;
            }
            this.mResolvedPaddingLeft = i2;
            this.mResolvedPaddingRight = this.mPaddingEnd;
        }
    }

    public void captureWidgets() {
        for (int i2 = 0; i2 < this.mWidgetsCount; i2++) {
            ConstraintWidget constraintWidget = this.mWidgets[i2];
            if (constraintWidget != null) {
                constraintWidget.setInVirtualLayout(true);
            }
        }
    }

    public boolean contains(HashSet<ConstraintWidget> hashSet) {
        for (int i2 = 0; i2 < this.mWidgetsCount; i2++) {
            if (hashSet.contains(this.mWidgets[i2])) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void e(ConstraintWidget constraintWidget, ConstraintWidget.DimensionBehaviour dimensionBehaviour, int i2, ConstraintWidget.DimensionBehaviour dimensionBehaviour2, int i3) {
        while (this.M == null && getParent() != null) {
            this.M = ((ConstraintWidgetContainer) getParent()).getMeasurer();
        }
        BasicMeasure.Measure measure = this.L;
        measure.horizontalBehavior = dimensionBehaviour;
        measure.verticalBehavior = dimensionBehaviour2;
        measure.horizontalDimension = i2;
        measure.verticalDimension = i3;
        this.M.measure(constraintWidget, measure);
        constraintWidget.setWidth(this.L.measuredWidth);
        constraintWidget.setHeight(this.L.measuredHeight);
        constraintWidget.setHasBaseline(this.L.measuredHasBaseline);
        constraintWidget.setBaselineDistance(this.L.measuredBaseline);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean f() {
        ConstraintWidget constraintWidget = this.mParent;
        BasicMeasure.Measurer measurer = constraintWidget != null ? ((ConstraintWidgetContainer) constraintWidget).getMeasurer() : null;
        if (measurer == null) {
            return false;
        }
        int i2 = 0;
        while (true) {
            boolean z = true;
            if (i2 >= this.mWidgetsCount) {
                return true;
            }
            ConstraintWidget constraintWidget2 = this.mWidgets[i2];
            if (constraintWidget2 != null && !(constraintWidget2 instanceof Guideline)) {
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = constraintWidget2.getDimensionBehaviour(0);
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = constraintWidget2.getDimensionBehaviour(1);
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (dimensionBehaviour != dimensionBehaviour3 || constraintWidget2.mMatchConstraintDefaultWidth == 1 || dimensionBehaviour2 != dimensionBehaviour3 || constraintWidget2.mMatchConstraintDefaultHeight == 1) {
                    z = false;
                }
                if (!z) {
                    if (dimensionBehaviour == dimensionBehaviour3) {
                        dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    }
                    if (dimensionBehaviour2 == dimensionBehaviour3) {
                        dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    }
                    BasicMeasure.Measure measure = this.L;
                    measure.horizontalBehavior = dimensionBehaviour;
                    measure.verticalBehavior = dimensionBehaviour2;
                    measure.horizontalDimension = constraintWidget2.getWidth();
                    this.L.verticalDimension = constraintWidget2.getHeight();
                    measurer.measure(constraintWidget2, this.L);
                    constraintWidget2.setWidth(this.L.measuredWidth);
                    constraintWidget2.setHeight(this.L.measuredHeight);
                    constraintWidget2.setBaselineDistance(this.L.measuredBaseline);
                }
            }
            i2++;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void g(boolean z) {
        this.mNeedsCallFromSolver = z;
    }

    public int getMeasuredHeight() {
        return this.mMeasuredHeight;
    }

    public int getMeasuredWidth() {
        return this.mMeasuredWidth;
    }

    public int getPaddingBottom() {
        return this.mPaddingBottom;
    }

    public int getPaddingLeft() {
        return this.mResolvedPaddingLeft;
    }

    public int getPaddingRight() {
        return this.mResolvedPaddingRight;
    }

    public int getPaddingTop() {
        return this.mPaddingTop;
    }

    public void measure(int i2, int i3, int i4, int i5) {
    }

    public boolean needSolverPass() {
        return this.mNeedsCallFromSolver;
    }

    public void setMeasure(int i2, int i3) {
        this.mMeasuredWidth = i2;
        this.mMeasuredHeight = i3;
    }

    public void setPadding(int i2) {
        this.mPaddingLeft = i2;
        this.mPaddingTop = i2;
        this.mPaddingRight = i2;
        this.mPaddingBottom = i2;
        this.mPaddingStart = i2;
        this.mPaddingEnd = i2;
    }

    public void setPaddingBottom(int i2) {
        this.mPaddingBottom = i2;
    }

    public void setPaddingEnd(int i2) {
        this.mPaddingEnd = i2;
    }

    public void setPaddingLeft(int i2) {
        this.mPaddingLeft = i2;
        this.mResolvedPaddingLeft = i2;
    }

    public void setPaddingRight(int i2) {
        this.mPaddingRight = i2;
        this.mResolvedPaddingRight = i2;
    }

    public void setPaddingStart(int i2) {
        this.mPaddingStart = i2;
        this.mResolvedPaddingLeft = i2;
        this.mResolvedPaddingRight = i2;
    }

    public void setPaddingTop(int i2) {
        this.mPaddingTop = i2;
    }

    @Override // androidx.constraintlayout.core.widgets.HelperWidget, androidx.constraintlayout.core.widgets.Helper
    public void updateConstraints(ConstraintWidgetContainer constraintWidgetContainer) {
        captureWidgets();
    }
}
