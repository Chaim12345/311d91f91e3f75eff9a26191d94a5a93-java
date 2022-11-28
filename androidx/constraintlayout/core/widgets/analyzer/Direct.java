package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.widgets.Barrier;
import androidx.constraintlayout.core.widgets.ChainHead;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.core.widgets.Guideline;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes.dex */
public class Direct {
    private static final boolean APPLY_MATCH_PARENT = false;
    private static final boolean DEBUG = false;
    private static final boolean EARLY_TERMINATION = true;
    private static BasicMeasure.Measure measure = new BasicMeasure.Measure();
    private static int hcount = 0;
    private static int vcount = 0;

    private static boolean canMeasure(int i2, ConstraintWidget constraintWidget) {
        ConstraintWidget.DimensionBehaviour dimensionBehaviour;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2;
        ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour = constraintWidget.getHorizontalDimensionBehaviour();
        ConstraintWidget.DimensionBehaviour verticalDimensionBehaviour = constraintWidget.getVerticalDimensionBehaviour();
        ConstraintWidgetContainer constraintWidgetContainer = constraintWidget.getParent() != null ? (ConstraintWidgetContainer) constraintWidget.getParent() : null;
        if (constraintWidgetContainer != null) {
            constraintWidgetContainer.getHorizontalDimensionBehaviour();
            ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        if (constraintWidgetContainer != null) {
            constraintWidgetContainer.getVerticalDimensionBehaviour();
            ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        ConstraintWidget.DimensionBehaviour dimensionBehaviour5 = ConstraintWidget.DimensionBehaviour.FIXED;
        boolean z = horizontalDimensionBehaviour == dimensionBehaviour5 || constraintWidget.isResolvedHorizontally() || horizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || (horizontalDimensionBehaviour == (dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) && constraintWidget.mMatchConstraintDefaultWidth == 0 && constraintWidget.mDimensionRatio == 0.0f && constraintWidget.hasDanglingDimension(0)) || (horizontalDimensionBehaviour == dimensionBehaviour2 && constraintWidget.mMatchConstraintDefaultWidth == 1 && constraintWidget.hasResolvedTargets(0, constraintWidget.getWidth()));
        boolean z2 = verticalDimensionBehaviour == dimensionBehaviour5 || constraintWidget.isResolvedVertically() || verticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || (verticalDimensionBehaviour == (dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) && constraintWidget.mMatchConstraintDefaultHeight == 0 && constraintWidget.mDimensionRatio == 0.0f && constraintWidget.hasDanglingDimension(1)) || (horizontalDimensionBehaviour == dimensionBehaviour && constraintWidget.mMatchConstraintDefaultHeight == 1 && constraintWidget.hasResolvedTargets(1, constraintWidget.getHeight()));
        if (constraintWidget.mDimensionRatio <= 0.0f || !(z || z2)) {
            return z && z2;
        }
        return true;
    }

    private static void horizontalSolvingPass(int i2, ConstraintWidget constraintWidget, BasicMeasure.Measurer measurer, boolean z) {
        ConstraintAnchor constraintAnchor;
        ConstraintAnchor constraintAnchor2;
        ConstraintAnchor constraintAnchor3;
        ConstraintAnchor constraintAnchor4;
        ConstraintAnchor constraintAnchor5;
        if (constraintWidget.isHorizontalSolvingPassDone()) {
            return;
        }
        hcount++;
        if (!(constraintWidget instanceof ConstraintWidgetContainer) && constraintWidget.isMeasureRequested()) {
            int i3 = i2 + 1;
            if (canMeasure(i3, constraintWidget)) {
                ConstraintWidgetContainer.measure(i3, constraintWidget, measurer, new BasicMeasure.Measure(), BasicMeasure.Measure.SELF_DIMENSIONS);
            }
        }
        ConstraintAnchor anchor = constraintWidget.getAnchor(ConstraintAnchor.Type.LEFT);
        ConstraintAnchor anchor2 = constraintWidget.getAnchor(ConstraintAnchor.Type.RIGHT);
        int finalValue = anchor.getFinalValue();
        int finalValue2 = anchor2.getFinalValue();
        if (anchor.getDependents() != null && anchor.hasFinalValue()) {
            Iterator<ConstraintAnchor> it = anchor.getDependents().iterator();
            while (it.hasNext()) {
                ConstraintAnchor next = it.next();
                ConstraintWidget constraintWidget2 = next.mOwner;
                int i4 = i2 + 1;
                boolean canMeasure = canMeasure(i4, constraintWidget2);
                if (constraintWidget2.isMeasureRequested() && canMeasure) {
                    ConstraintWidgetContainer.measure(i4, constraintWidget2, measurer, new BasicMeasure.Measure(), BasicMeasure.Measure.SELF_DIMENSIONS);
                }
                ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour = constraintWidget2.getHorizontalDimensionBehaviour();
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (horizontalDimensionBehaviour != dimensionBehaviour || canMeasure) {
                    if (!constraintWidget2.isMeasureRequested()) {
                        ConstraintAnchor constraintAnchor6 = constraintWidget2.mLeft;
                        if (next == constraintAnchor6 && constraintWidget2.mRight.mTarget == null) {
                            int margin = constraintAnchor6.getMargin() + finalValue;
                            constraintWidget2.setFinalHorizontal(margin, constraintWidget2.getWidth() + margin);
                        } else {
                            ConstraintAnchor constraintAnchor7 = constraintWidget2.mRight;
                            if (next == constraintAnchor7 && constraintAnchor6.mTarget == null) {
                                int margin2 = finalValue - constraintAnchor7.getMargin();
                                constraintWidget2.setFinalHorizontal(margin2 - constraintWidget2.getWidth(), margin2);
                            } else if (next == constraintAnchor6 && (constraintAnchor3 = constraintAnchor7.mTarget) != null && constraintAnchor3.hasFinalValue() && !constraintWidget2.isInHorizontalChain()) {
                                solveHorizontalCenterConstraints(i4, measurer, constraintWidget2, z);
                            }
                        }
                        horizontalSolvingPass(i4, constraintWidget2, measurer, z);
                    }
                } else if (constraintWidget2.getHorizontalDimensionBehaviour() == dimensionBehaviour && constraintWidget2.mMatchConstraintMaxWidth >= 0 && constraintWidget2.mMatchConstraintMinWidth >= 0 && (constraintWidget2.getVisibility() == 8 || (constraintWidget2.mMatchConstraintDefaultWidth == 0 && constraintWidget2.getDimensionRatio() == 0.0f))) {
                    if (!constraintWidget2.isInHorizontalChain() && !constraintWidget2.isInVirtualLayout()) {
                        if (((next == constraintWidget2.mLeft && (constraintAnchor5 = constraintWidget2.mRight.mTarget) != null && constraintAnchor5.hasFinalValue()) || (next == constraintWidget2.mRight && (constraintAnchor4 = constraintWidget2.mLeft.mTarget) != null && constraintAnchor4.hasFinalValue())) && !constraintWidget2.isInHorizontalChain()) {
                            solveHorizontalMatchConstraint(i4, constraintWidget, measurer, constraintWidget2, z);
                        }
                    }
                }
            }
        }
        if (constraintWidget instanceof Guideline) {
            return;
        }
        if (anchor2.getDependents() != null && anchor2.hasFinalValue()) {
            Iterator<ConstraintAnchor> it2 = anchor2.getDependents().iterator();
            while (it2.hasNext()) {
                ConstraintAnchor next2 = it2.next();
                ConstraintWidget constraintWidget3 = next2.mOwner;
                int i5 = i2 + 1;
                boolean canMeasure2 = canMeasure(i5, constraintWidget3);
                if (constraintWidget3.isMeasureRequested() && canMeasure2) {
                    ConstraintWidgetContainer.measure(i5, constraintWidget3, measurer, new BasicMeasure.Measure(), BasicMeasure.Measure.SELF_DIMENSIONS);
                }
                boolean z2 = (next2 == constraintWidget3.mLeft && (constraintAnchor2 = constraintWidget3.mRight.mTarget) != null && constraintAnchor2.hasFinalValue()) || (next2 == constraintWidget3.mRight && (constraintAnchor = constraintWidget3.mLeft.mTarget) != null && constraintAnchor.hasFinalValue());
                ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour2 = constraintWidget3.getHorizontalDimensionBehaviour();
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (horizontalDimensionBehaviour2 != dimensionBehaviour2 || canMeasure2) {
                    if (!constraintWidget3.isMeasureRequested()) {
                        ConstraintAnchor constraintAnchor8 = constraintWidget3.mLeft;
                        if (next2 == constraintAnchor8 && constraintWidget3.mRight.mTarget == null) {
                            int margin3 = constraintAnchor8.getMargin() + finalValue2;
                            constraintWidget3.setFinalHorizontal(margin3, constraintWidget3.getWidth() + margin3);
                        } else {
                            ConstraintAnchor constraintAnchor9 = constraintWidget3.mRight;
                            if (next2 == constraintAnchor9 && constraintAnchor8.mTarget == null) {
                                int margin4 = finalValue2 - constraintAnchor9.getMargin();
                                constraintWidget3.setFinalHorizontal(margin4 - constraintWidget3.getWidth(), margin4);
                            } else if (z2 && !constraintWidget3.isInHorizontalChain()) {
                                solveHorizontalCenterConstraints(i5, measurer, constraintWidget3, z);
                            }
                        }
                        horizontalSolvingPass(i5, constraintWidget3, measurer, z);
                    }
                } else if (constraintWidget3.getHorizontalDimensionBehaviour() == dimensionBehaviour2 && constraintWidget3.mMatchConstraintMaxWidth >= 0 && constraintWidget3.mMatchConstraintMinWidth >= 0 && (constraintWidget3.getVisibility() == 8 || (constraintWidget3.mMatchConstraintDefaultWidth == 0 && constraintWidget3.getDimensionRatio() == 0.0f))) {
                    if (!constraintWidget3.isInHorizontalChain() && !constraintWidget3.isInVirtualLayout() && z2 && !constraintWidget3.isInHorizontalChain()) {
                        solveHorizontalMatchConstraint(i5, constraintWidget, measurer, constraintWidget3, z);
                    }
                }
            }
        }
        constraintWidget.markHorizontalSolvingPassDone();
    }

    public static String ls(int i2) {
        StringBuilder sb = new StringBuilder();
        for (int i3 = 0; i3 < i2; i3++) {
            sb.append("  ");
        }
        sb.append("+-(" + i2 + ") ");
        return sb.toString();
    }

    private static void solveBarrier(int i2, Barrier barrier, BasicMeasure.Measurer measurer, int i3, boolean z) {
        if (barrier.allSolved()) {
            int i4 = i2 + 1;
            if (i3 == 0) {
                horizontalSolvingPass(i4, barrier, measurer, z);
            } else {
                verticalSolvingPass(i4, barrier, measurer);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:103:0x01d6, code lost:
        if (r6[r23].mTarget.mOwner == r0) goto L93;
     */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0150  */
    /* JADX WARN: Type inference failed for: r2v24 */
    /* JADX WARN: Type inference failed for: r2v43 */
    /* JADX WARN: Type inference failed for: r2v7, types: [int, boolean] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean solveChain(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, int i2, int i3, ChainHead chainHead, boolean z, boolean z2, boolean z3) {
        int finalValue;
        int finalValue2;
        int finalValue3;
        int i4;
        int height;
        ConstraintWidget constraintWidget;
        boolean z4;
        if (z3) {
            return false;
        }
        if (i2 == 0) {
            if (!constraintWidgetContainer.isResolvedHorizontally()) {
                return false;
            }
        } else if (!constraintWidgetContainer.isResolvedVertically()) {
            return false;
        }
        boolean isRtl = constraintWidgetContainer.isRtl();
        ConstraintWidget first = chainHead.getFirst();
        ConstraintWidget last = chainHead.getLast();
        ConstraintWidget firstVisibleWidget = chainHead.getFirstVisibleWidget();
        ConstraintWidget lastVisibleWidget = chainHead.getLastVisibleWidget();
        ConstraintWidget head = chainHead.getHead();
        ConstraintAnchor constraintAnchor = first.mListAnchors[i3];
        int i5 = i3 + 1;
        ConstraintAnchor constraintAnchor2 = last.mListAnchors[i5];
        ConstraintAnchor constraintAnchor3 = constraintAnchor.mTarget;
        if (constraintAnchor3 == null || constraintAnchor2.mTarget == null || !constraintAnchor3.hasFinalValue() || !constraintAnchor2.mTarget.hasFinalValue() || firstVisibleWidget == null || lastVisibleWidget == null || (finalValue3 = (finalValue2 = constraintAnchor2.mTarget.getFinalValue() - lastVisibleWidget.mListAnchors[i5].getMargin()) - (finalValue = constraintAnchor.mTarget.getFinalValue() + firstVisibleWidget.mListAnchors[i3].getMargin())) <= 0) {
            return false;
        }
        BasicMeasure.Measure measure2 = new BasicMeasure.Measure();
        boolean z5 = false;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        ConstraintWidget constraintWidget2 = first;
        while (true) {
            ConstraintWidget constraintWidget3 = null;
            if (z5) {
                ConstraintWidget constraintWidget4 = first;
                if (i6 == 0 || i6 != i7 || finalValue3 < i8) {
                    return false;
                }
                int i9 = finalValue3 - i8;
                if (z) {
                    i9 /= i6 + 1;
                } else if (z2 && i6 > 2) {
                    i4 = 1;
                    i9 = (i9 / i6) - 1;
                    if (i6 != i4) {
                        int horizontalBiasPercent = (int) (finalValue + 0.5f + (i9 * (i2 == 0 ? head.getHorizontalBiasPercent() : head.getVerticalBiasPercent())));
                        if (i2 == 0) {
                            firstVisibleWidget.setFinalHorizontal(horizontalBiasPercent, firstVisibleWidget.getWidth() + horizontalBiasPercent);
                        } else {
                            firstVisibleWidget.setFinalVertical(horizontalBiasPercent, firstVisibleWidget.getHeight() + horizontalBiasPercent);
                        }
                        horizontalSolvingPass(1, firstVisibleWidget, constraintWidgetContainer.getMeasurer(), isRtl);
                        return true;
                    } else if (!z) {
                        if (z2) {
                            if (i6 == 2) {
                                if (i2 == 0) {
                                    firstVisibleWidget.setFinalHorizontal(finalValue, firstVisibleWidget.getWidth() + finalValue);
                                    lastVisibleWidget.setFinalHorizontal(finalValue2 - lastVisibleWidget.getWidth(), finalValue2);
                                    horizontalSolvingPass(1, firstVisibleWidget, constraintWidgetContainer.getMeasurer(), isRtl);
                                    horizontalSolvingPass(1, lastVisibleWidget, constraintWidgetContainer.getMeasurer(), isRtl);
                                    return true;
                                }
                                firstVisibleWidget.setFinalVertical(finalValue, firstVisibleWidget.getHeight() + finalValue);
                                lastVisibleWidget.setFinalVertical(finalValue2 - lastVisibleWidget.getHeight(), finalValue2);
                                verticalSolvingPass(1, firstVisibleWidget, constraintWidgetContainer.getMeasurer());
                                verticalSolvingPass(1, lastVisibleWidget, constraintWidgetContainer.getMeasurer());
                                return true;
                            }
                            return false;
                        }
                        return true;
                    } else {
                        int i10 = finalValue + i9;
                        ConstraintWidget constraintWidget5 = constraintWidget4;
                        boolean z6 = false;
                        ?? r2 = i4;
                        while (!z6) {
                            if (constraintWidget5.getVisibility() != 8) {
                                int margin = i10 + constraintWidget5.mListAnchors[i3].getMargin();
                                if (i2 == 0) {
                                    constraintWidget5.setFinalHorizontal(margin, constraintWidget5.getWidth() + margin);
                                    horizontalSolvingPass(1, constraintWidget5, constraintWidgetContainer.getMeasurer(), isRtl);
                                    height = constraintWidget5.getWidth();
                                } else {
                                    constraintWidget5.setFinalVertical(margin, constraintWidget5.getHeight() + margin);
                                    verticalSolvingPass(1, constraintWidget5, constraintWidgetContainer.getMeasurer());
                                    height = constraintWidget5.getHeight();
                                }
                                i10 = margin + height + constraintWidget5.mListAnchors[i5].getMargin() + i9;
                            } else if (i2 == 0) {
                                constraintWidget5.setFinalHorizontal(i10, i10);
                                horizontalSolvingPass(r2, constraintWidget5, constraintWidgetContainer.getMeasurer(), isRtl);
                            } else {
                                constraintWidget5.setFinalVertical(i10, i10);
                                verticalSolvingPass(r2, constraintWidget5, constraintWidgetContainer.getMeasurer());
                            }
                            constraintWidget5.addToSolver(linearSystem, false);
                            ConstraintAnchor constraintAnchor4 = constraintWidget5.mListAnchors[i5].mTarget;
                            if (constraintAnchor4 != null) {
                                constraintWidget = constraintAnchor4.mOwner;
                                ConstraintAnchor[] constraintAnchorArr = constraintWidget.mListAnchors;
                                if (constraintAnchorArr[i3].mTarget != null) {
                                }
                            }
                            constraintWidget = null;
                            if (constraintWidget != null) {
                                constraintWidget5 = constraintWidget;
                            } else {
                                z6 = true;
                            }
                            r2 = 1;
                        }
                        return r2;
                    }
                }
                i4 = 1;
                if (i6 != i4) {
                }
            } else if (!canMeasure(1, constraintWidget2)) {
                return false;
            } else {
                ConstraintWidget constraintWidget6 = first;
                if (constraintWidget2.mListDimensionBehaviors[i2] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    return false;
                }
                if (constraintWidget2.isMeasureRequested()) {
                    z4 = z5;
                    ConstraintWidgetContainer.measure(1, constraintWidget2, constraintWidgetContainer.getMeasurer(), measure2, BasicMeasure.Measure.SELF_DIMENSIONS);
                } else {
                    z4 = z5;
                }
                i8 = i8 + constraintWidget2.mListAnchors[i3].getMargin() + (i2 == 0 ? constraintWidget2.getWidth() : constraintWidget2.getHeight()) + constraintWidget2.mListAnchors[i5].getMargin();
                i7++;
                if (constraintWidget2.getVisibility() != 8) {
                    i6++;
                }
                ConstraintAnchor constraintAnchor5 = constraintWidget2.mListAnchors[i5].mTarget;
                if (constraintAnchor5 != null) {
                    ConstraintWidget constraintWidget7 = constraintAnchor5.mOwner;
                    ConstraintAnchor[] constraintAnchorArr2 = constraintWidget7.mListAnchors;
                    if (constraintAnchorArr2[i3].mTarget != null && constraintAnchorArr2[i3].mTarget.mOwner == constraintWidget2) {
                        constraintWidget3 = constraintWidget7;
                    }
                }
                if (constraintWidget3 != null) {
                    constraintWidget2 = constraintWidget3;
                    z5 = z4;
                } else {
                    z5 = true;
                }
                first = constraintWidget6;
            }
        }
    }

    private static void solveHorizontalCenterConstraints(int i2, BasicMeasure.Measurer measurer, ConstraintWidget constraintWidget, boolean z) {
        float horizontalBiasPercent = constraintWidget.getHorizontalBiasPercent();
        int finalValue = constraintWidget.mLeft.mTarget.getFinalValue();
        int finalValue2 = constraintWidget.mRight.mTarget.getFinalValue();
        int margin = constraintWidget.mLeft.getMargin() + finalValue;
        int margin2 = finalValue2 - constraintWidget.mRight.getMargin();
        if (finalValue == finalValue2) {
            horizontalBiasPercent = 0.5f;
        } else {
            finalValue = margin;
            finalValue2 = margin2;
        }
        int width = constraintWidget.getWidth();
        int i3 = (finalValue2 - finalValue) - width;
        if (finalValue > finalValue2) {
            i3 = (finalValue - finalValue2) - width;
        }
        int i4 = ((int) (i3 > 0 ? (horizontalBiasPercent * i3) + 0.5f : horizontalBiasPercent * i3)) + finalValue;
        int i5 = i4 + width;
        if (finalValue > finalValue2) {
            i5 = i4 - width;
        }
        constraintWidget.setFinalHorizontal(i4, i5);
        horizontalSolvingPass(i2 + 1, constraintWidget, measurer, z);
    }

    private static void solveHorizontalMatchConstraint(int i2, ConstraintWidget constraintWidget, BasicMeasure.Measurer measurer, ConstraintWidget constraintWidget2, boolean z) {
        float horizontalBiasPercent = constraintWidget2.getHorizontalBiasPercent();
        int finalValue = constraintWidget2.mLeft.mTarget.getFinalValue() + constraintWidget2.mLeft.getMargin();
        int finalValue2 = constraintWidget2.mRight.mTarget.getFinalValue() - constraintWidget2.mRight.getMargin();
        if (finalValue2 >= finalValue) {
            int width = constraintWidget2.getWidth();
            if (constraintWidget2.getVisibility() != 8) {
                int i3 = constraintWidget2.mMatchConstraintDefaultWidth;
                if (i3 == 2) {
                    if (!(constraintWidget instanceof ConstraintWidgetContainer)) {
                        constraintWidget = constraintWidget.getParent();
                    }
                    width = (int) (constraintWidget2.getHorizontalBiasPercent() * 0.5f * constraintWidget.getWidth());
                } else if (i3 == 0) {
                    width = finalValue2 - finalValue;
                }
                width = Math.max(constraintWidget2.mMatchConstraintMinWidth, width);
                int i4 = constraintWidget2.mMatchConstraintMaxWidth;
                if (i4 > 0) {
                    width = Math.min(i4, width);
                }
            }
            int i5 = finalValue + ((int) ((horizontalBiasPercent * ((finalValue2 - finalValue) - width)) + 0.5f));
            constraintWidget2.setFinalHorizontal(i5, width + i5);
            horizontalSolvingPass(i2 + 1, constraintWidget2, measurer, z);
        }
    }

    private static void solveVerticalCenterConstraints(int i2, BasicMeasure.Measurer measurer, ConstraintWidget constraintWidget) {
        float verticalBiasPercent = constraintWidget.getVerticalBiasPercent();
        int finalValue = constraintWidget.mTop.mTarget.getFinalValue();
        int finalValue2 = constraintWidget.mBottom.mTarget.getFinalValue();
        int margin = constraintWidget.mTop.getMargin() + finalValue;
        int margin2 = finalValue2 - constraintWidget.mBottom.getMargin();
        if (finalValue == finalValue2) {
            verticalBiasPercent = 0.5f;
        } else {
            finalValue = margin;
            finalValue2 = margin2;
        }
        int height = constraintWidget.getHeight();
        int i3 = (finalValue2 - finalValue) - height;
        if (finalValue > finalValue2) {
            i3 = (finalValue - finalValue2) - height;
        }
        int i4 = (int) (i3 > 0 ? (verticalBiasPercent * i3) + 0.5f : verticalBiasPercent * i3);
        int i5 = finalValue + i4;
        int i6 = i5 + height;
        if (finalValue > finalValue2) {
            i5 = finalValue - i4;
            i6 = i5 - height;
        }
        constraintWidget.setFinalVertical(i5, i6);
        verticalSolvingPass(i2 + 1, constraintWidget, measurer);
    }

    private static void solveVerticalMatchConstraint(int i2, ConstraintWidget constraintWidget, BasicMeasure.Measurer measurer, ConstraintWidget constraintWidget2) {
        float verticalBiasPercent = constraintWidget2.getVerticalBiasPercent();
        int finalValue = constraintWidget2.mTop.mTarget.getFinalValue() + constraintWidget2.mTop.getMargin();
        int finalValue2 = constraintWidget2.mBottom.mTarget.getFinalValue() - constraintWidget2.mBottom.getMargin();
        if (finalValue2 >= finalValue) {
            int height = constraintWidget2.getHeight();
            if (constraintWidget2.getVisibility() != 8) {
                int i3 = constraintWidget2.mMatchConstraintDefaultHeight;
                if (i3 == 2) {
                    if (!(constraintWidget instanceof ConstraintWidgetContainer)) {
                        constraintWidget = constraintWidget.getParent();
                    }
                    height = (int) (verticalBiasPercent * 0.5f * constraintWidget.getHeight());
                } else if (i3 == 0) {
                    height = finalValue2 - finalValue;
                }
                height = Math.max(constraintWidget2.mMatchConstraintMinHeight, height);
                int i4 = constraintWidget2.mMatchConstraintMaxHeight;
                if (i4 > 0) {
                    height = Math.min(i4, height);
                }
            }
            int i5 = finalValue + ((int) ((verticalBiasPercent * ((finalValue2 - finalValue) - height)) + 0.5f));
            constraintWidget2.setFinalVertical(i5, height + i5);
            verticalSolvingPass(i2 + 1, constraintWidget2, measurer);
        }
    }

    public static void solvingPass(ConstraintWidgetContainer constraintWidgetContainer, BasicMeasure.Measurer measurer) {
        int relativePercent;
        int relativePercent2;
        ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour = constraintWidgetContainer.getHorizontalDimensionBehaviour();
        ConstraintWidget.DimensionBehaviour verticalDimensionBehaviour = constraintWidgetContainer.getVerticalDimensionBehaviour();
        hcount = 0;
        vcount = 0;
        constraintWidgetContainer.resetFinalResolution();
        ArrayList<ConstraintWidget> children = constraintWidgetContainer.getChildren();
        int size = children.size();
        for (int i2 = 0; i2 < size; i2++) {
            children.get(i2).resetFinalResolution();
        }
        boolean isRtl = constraintWidgetContainer.isRtl();
        if (horizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.FIXED) {
            constraintWidgetContainer.setFinalHorizontal(0, constraintWidgetContainer.getWidth());
        } else {
            constraintWidgetContainer.setFinalLeft(0);
        }
        boolean z = false;
        boolean z2 = false;
        for (int i3 = 0; i3 < size; i3++) {
            ConstraintWidget constraintWidget = children.get(i3);
            if (constraintWidget instanceof Guideline) {
                Guideline guideline = (Guideline) constraintWidget;
                if (guideline.getOrientation() == 1) {
                    if (guideline.getRelativeBegin() != -1) {
                        relativePercent2 = guideline.getRelativeBegin();
                    } else if (guideline.getRelativeEnd() == -1 || !constraintWidgetContainer.isResolvedHorizontally()) {
                        if (constraintWidgetContainer.isResolvedHorizontally()) {
                            relativePercent2 = (int) ((guideline.getRelativePercent() * constraintWidgetContainer.getWidth()) + 0.5f);
                        }
                        z = true;
                    } else {
                        relativePercent2 = constraintWidgetContainer.getWidth() - guideline.getRelativeEnd();
                    }
                    guideline.setFinalValue(relativePercent2);
                    z = true;
                }
            } else if ((constraintWidget instanceof Barrier) && ((Barrier) constraintWidget).getOrientation() == 0) {
                z2 = true;
            }
        }
        if (z) {
            for (int i4 = 0; i4 < size; i4++) {
                ConstraintWidget constraintWidget2 = children.get(i4);
                if (constraintWidget2 instanceof Guideline) {
                    Guideline guideline2 = (Guideline) constraintWidget2;
                    if (guideline2.getOrientation() == 1) {
                        horizontalSolvingPass(0, guideline2, measurer, isRtl);
                    }
                }
            }
        }
        horizontalSolvingPass(0, constraintWidgetContainer, measurer, isRtl);
        if (z2) {
            for (int i5 = 0; i5 < size; i5++) {
                ConstraintWidget constraintWidget3 = children.get(i5);
                if (constraintWidget3 instanceof Barrier) {
                    Barrier barrier = (Barrier) constraintWidget3;
                    if (barrier.getOrientation() == 0) {
                        solveBarrier(0, barrier, measurer, 0, isRtl);
                    }
                }
            }
        }
        if (verticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.FIXED) {
            constraintWidgetContainer.setFinalVertical(0, constraintWidgetContainer.getHeight());
        } else {
            constraintWidgetContainer.setFinalTop(0);
        }
        boolean z3 = false;
        boolean z4 = false;
        for (int i6 = 0; i6 < size; i6++) {
            ConstraintWidget constraintWidget4 = children.get(i6);
            if (constraintWidget4 instanceof Guideline) {
                Guideline guideline3 = (Guideline) constraintWidget4;
                if (guideline3.getOrientation() == 0) {
                    if (guideline3.getRelativeBegin() != -1) {
                        relativePercent = guideline3.getRelativeBegin();
                    } else if (guideline3.getRelativeEnd() == -1 || !constraintWidgetContainer.isResolvedVertically()) {
                        if (constraintWidgetContainer.isResolvedVertically()) {
                            relativePercent = (int) ((guideline3.getRelativePercent() * constraintWidgetContainer.getHeight()) + 0.5f);
                        }
                        z3 = true;
                    } else {
                        relativePercent = constraintWidgetContainer.getHeight() - guideline3.getRelativeEnd();
                    }
                    guideline3.setFinalValue(relativePercent);
                    z3 = true;
                }
            } else if ((constraintWidget4 instanceof Barrier) && ((Barrier) constraintWidget4).getOrientation() == 1) {
                z4 = true;
            }
        }
        if (z3) {
            for (int i7 = 0; i7 < size; i7++) {
                ConstraintWidget constraintWidget5 = children.get(i7);
                if (constraintWidget5 instanceof Guideline) {
                    Guideline guideline4 = (Guideline) constraintWidget5;
                    if (guideline4.getOrientation() == 0) {
                        verticalSolvingPass(1, guideline4, measurer);
                    }
                }
            }
        }
        verticalSolvingPass(0, constraintWidgetContainer, measurer);
        if (z4) {
            for (int i8 = 0; i8 < size; i8++) {
                ConstraintWidget constraintWidget6 = children.get(i8);
                if (constraintWidget6 instanceof Barrier) {
                    Barrier barrier2 = (Barrier) constraintWidget6;
                    if (barrier2.getOrientation() == 1) {
                        solveBarrier(0, barrier2, measurer, 1, isRtl);
                    }
                }
            }
        }
        for (int i9 = 0; i9 < size; i9++) {
            ConstraintWidget constraintWidget7 = children.get(i9);
            if (constraintWidget7.isMeasureRequested() && canMeasure(0, constraintWidget7)) {
                ConstraintWidgetContainer.measure(0, constraintWidget7, measurer, measure, BasicMeasure.Measure.SELF_DIMENSIONS);
                if (!(constraintWidget7 instanceof Guideline)) {
                    horizontalSolvingPass(0, constraintWidget7, measurer, isRtl);
                } else if (((Guideline) constraintWidget7).getOrientation() != 0) {
                    horizontalSolvingPass(0, constraintWidget7, measurer, isRtl);
                }
                verticalSolvingPass(0, constraintWidget7, measurer);
            }
        }
    }

    private static void verticalSolvingPass(int i2, ConstraintWidget constraintWidget, BasicMeasure.Measurer measurer) {
        ConstraintAnchor constraintAnchor;
        ConstraintAnchor constraintAnchor2;
        ConstraintAnchor constraintAnchor3;
        ConstraintAnchor constraintAnchor4;
        ConstraintAnchor constraintAnchor5;
        if (constraintWidget.isVerticalSolvingPassDone()) {
            return;
        }
        vcount++;
        if (!(constraintWidget instanceof ConstraintWidgetContainer) && constraintWidget.isMeasureRequested()) {
            int i3 = i2 + 1;
            if (canMeasure(i3, constraintWidget)) {
                ConstraintWidgetContainer.measure(i3, constraintWidget, measurer, new BasicMeasure.Measure(), BasicMeasure.Measure.SELF_DIMENSIONS);
            }
        }
        ConstraintAnchor anchor = constraintWidget.getAnchor(ConstraintAnchor.Type.TOP);
        ConstraintAnchor anchor2 = constraintWidget.getAnchor(ConstraintAnchor.Type.BOTTOM);
        int finalValue = anchor.getFinalValue();
        int finalValue2 = anchor2.getFinalValue();
        if (anchor.getDependents() != null && anchor.hasFinalValue()) {
            Iterator<ConstraintAnchor> it = anchor.getDependents().iterator();
            while (it.hasNext()) {
                ConstraintAnchor next = it.next();
                ConstraintWidget constraintWidget2 = next.mOwner;
                int i4 = i2 + 1;
                boolean canMeasure = canMeasure(i4, constraintWidget2);
                if (constraintWidget2.isMeasureRequested() && canMeasure) {
                    ConstraintWidgetContainer.measure(i4, constraintWidget2, measurer, new BasicMeasure.Measure(), BasicMeasure.Measure.SELF_DIMENSIONS);
                }
                ConstraintWidget.DimensionBehaviour verticalDimensionBehaviour = constraintWidget2.getVerticalDimensionBehaviour();
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (verticalDimensionBehaviour != dimensionBehaviour || canMeasure) {
                    if (!constraintWidget2.isMeasureRequested()) {
                        ConstraintAnchor constraintAnchor6 = constraintWidget2.mTop;
                        if (next == constraintAnchor6 && constraintWidget2.mBottom.mTarget == null) {
                            int margin = constraintAnchor6.getMargin() + finalValue;
                            constraintWidget2.setFinalVertical(margin, constraintWidget2.getHeight() + margin);
                        } else {
                            ConstraintAnchor constraintAnchor7 = constraintWidget2.mBottom;
                            if (next == constraintAnchor7 && constraintAnchor7.mTarget == null) {
                                int margin2 = finalValue - constraintAnchor7.getMargin();
                                constraintWidget2.setFinalVertical(margin2 - constraintWidget2.getHeight(), margin2);
                            } else if (next == constraintAnchor6 && (constraintAnchor3 = constraintAnchor7.mTarget) != null && constraintAnchor3.hasFinalValue()) {
                                solveVerticalCenterConstraints(i4, measurer, constraintWidget2);
                            }
                        }
                        verticalSolvingPass(i4, constraintWidget2, measurer);
                    }
                } else if (constraintWidget2.getVerticalDimensionBehaviour() == dimensionBehaviour && constraintWidget2.mMatchConstraintMaxHeight >= 0 && constraintWidget2.mMatchConstraintMinHeight >= 0 && (constraintWidget2.getVisibility() == 8 || (constraintWidget2.mMatchConstraintDefaultHeight == 0 && constraintWidget2.getDimensionRatio() == 0.0f))) {
                    if (!constraintWidget2.isInVerticalChain() && !constraintWidget2.isInVirtualLayout()) {
                        if (((next == constraintWidget2.mTop && (constraintAnchor5 = constraintWidget2.mBottom.mTarget) != null && constraintAnchor5.hasFinalValue()) || (next == constraintWidget2.mBottom && (constraintAnchor4 = constraintWidget2.mTop.mTarget) != null && constraintAnchor4.hasFinalValue())) && !constraintWidget2.isInVerticalChain()) {
                            solveVerticalMatchConstraint(i4, constraintWidget, measurer, constraintWidget2);
                        }
                    }
                }
            }
        }
        if (constraintWidget instanceof Guideline) {
            return;
        }
        if (anchor2.getDependents() != null && anchor2.hasFinalValue()) {
            Iterator<ConstraintAnchor> it2 = anchor2.getDependents().iterator();
            while (it2.hasNext()) {
                ConstraintAnchor next2 = it2.next();
                ConstraintWidget constraintWidget3 = next2.mOwner;
                int i5 = i2 + 1;
                boolean canMeasure2 = canMeasure(i5, constraintWidget3);
                if (constraintWidget3.isMeasureRequested() && canMeasure2) {
                    ConstraintWidgetContainer.measure(i5, constraintWidget3, measurer, new BasicMeasure.Measure(), BasicMeasure.Measure.SELF_DIMENSIONS);
                }
                boolean z = (next2 == constraintWidget3.mTop && (constraintAnchor2 = constraintWidget3.mBottom.mTarget) != null && constraintAnchor2.hasFinalValue()) || (next2 == constraintWidget3.mBottom && (constraintAnchor = constraintWidget3.mTop.mTarget) != null && constraintAnchor.hasFinalValue());
                ConstraintWidget.DimensionBehaviour verticalDimensionBehaviour2 = constraintWidget3.getVerticalDimensionBehaviour();
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (verticalDimensionBehaviour2 != dimensionBehaviour2 || canMeasure2) {
                    if (!constraintWidget3.isMeasureRequested()) {
                        ConstraintAnchor constraintAnchor8 = constraintWidget3.mTop;
                        if (next2 == constraintAnchor8 && constraintWidget3.mBottom.mTarget == null) {
                            int margin3 = constraintAnchor8.getMargin() + finalValue2;
                            constraintWidget3.setFinalVertical(margin3, constraintWidget3.getHeight() + margin3);
                        } else {
                            ConstraintAnchor constraintAnchor9 = constraintWidget3.mBottom;
                            if (next2 == constraintAnchor9 && constraintAnchor8.mTarget == null) {
                                int margin4 = finalValue2 - constraintAnchor9.getMargin();
                                constraintWidget3.setFinalVertical(margin4 - constraintWidget3.getHeight(), margin4);
                            } else if (z && !constraintWidget3.isInVerticalChain()) {
                                solveVerticalCenterConstraints(i5, measurer, constraintWidget3);
                            }
                        }
                        verticalSolvingPass(i5, constraintWidget3, measurer);
                    }
                } else if (constraintWidget3.getVerticalDimensionBehaviour() == dimensionBehaviour2 && constraintWidget3.mMatchConstraintMaxHeight >= 0 && constraintWidget3.mMatchConstraintMinHeight >= 0 && (constraintWidget3.getVisibility() == 8 || (constraintWidget3.mMatchConstraintDefaultHeight == 0 && constraintWidget3.getDimensionRatio() == 0.0f))) {
                    if (!constraintWidget3.isInVerticalChain() && !constraintWidget3.isInVirtualLayout() && z && !constraintWidget3.isInVerticalChain()) {
                        solveVerticalMatchConstraint(i5, constraintWidget, measurer, constraintWidget3);
                    }
                }
            }
        }
        ConstraintAnchor anchor3 = constraintWidget.getAnchor(ConstraintAnchor.Type.BASELINE);
        if (anchor3.getDependents() != null && anchor3.hasFinalValue()) {
            int finalValue3 = anchor3.getFinalValue();
            Iterator<ConstraintAnchor> it3 = anchor3.getDependents().iterator();
            while (it3.hasNext()) {
                ConstraintAnchor next3 = it3.next();
                ConstraintWidget constraintWidget4 = next3.mOwner;
                int i6 = i2 + 1;
                boolean canMeasure3 = canMeasure(i6, constraintWidget4);
                if (constraintWidget4.isMeasureRequested() && canMeasure3) {
                    ConstraintWidgetContainer.measure(i6, constraintWidget4, measurer, new BasicMeasure.Measure(), BasicMeasure.Measure.SELF_DIMENSIONS);
                }
                if (constraintWidget4.getVerticalDimensionBehaviour() != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || canMeasure3) {
                    if (!constraintWidget4.isMeasureRequested() && next3 == constraintWidget4.mBaseline) {
                        constraintWidget4.setFinalBaseline(next3.getMargin() + finalValue3);
                        verticalSolvingPass(i6, constraintWidget4, measurer);
                    }
                }
            }
        }
        constraintWidget.markVerticalSolvingPassDone();
    }
}
