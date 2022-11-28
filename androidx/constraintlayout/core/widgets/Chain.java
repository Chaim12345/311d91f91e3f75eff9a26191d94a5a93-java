package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.ArrayRow;
import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.SolverVariable;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class Chain {
    private static final boolean DEBUG = false;
    public static final boolean USE_CHAIN_OPTIMIZATION = false;

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0031, code lost:
        if (r8 == 2) goto L322;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0042, code lost:
        if (r8 == 2) goto L322;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0044, code lost:
        r5 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0046, code lost:
        r5 = false;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:108:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x01bb  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x01d8  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0268 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:161:0x02c1 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0330  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x0339  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x034e  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x035e A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:223:0x03a5  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x03b0 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:236:0x03c3  */
    /* JADX WARN: Removed duplicated region for block: B:283:0x049b  */
    /* JADX WARN: Removed duplicated region for block: B:288:0x04d0  */
    /* JADX WARN: Removed duplicated region for block: B:293:0x04e3 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:298:0x04ef  */
    /* JADX WARN: Removed duplicated region for block: B:301:0x04fa  */
    /* JADX WARN: Removed duplicated region for block: B:302:0x04fd  */
    /* JADX WARN: Removed duplicated region for block: B:305:0x0503  */
    /* JADX WARN: Removed duplicated region for block: B:306:0x0506  */
    /* JADX WARN: Removed duplicated region for block: B:308:0x050a  */
    /* JADX WARN: Removed duplicated region for block: B:313:0x051a  */
    /* JADX WARN: Removed duplicated region for block: B:315:0x0520 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:326:0x03a7 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:337:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static void a(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, int i2, int i3, ChainHead chainHead) {
        boolean z;
        boolean z2;
        boolean z3;
        ArrayList arrayList;
        ConstraintWidget constraintWidget;
        ConstraintAnchor constraintAnchor;
        ConstraintAnchor constraintAnchor2;
        ConstraintAnchor constraintAnchor3;
        int i4;
        ConstraintWidget constraintWidget2;
        int i5;
        ConstraintWidget constraintWidget3;
        ConstraintAnchor constraintAnchor4;
        SolverVariable solverVariable;
        SolverVariable solverVariable2;
        ConstraintWidget constraintWidget4;
        ConstraintAnchor constraintAnchor5;
        ConstraintAnchor constraintAnchor6;
        ConstraintWidget constraintWidget5;
        SolverVariable solverVariable3;
        ConstraintWidget constraintWidget6;
        ConstraintWidget constraintWidget7;
        SolverVariable solverVariable4;
        int size;
        int i6;
        ArrayList arrayList2;
        int i7;
        boolean z4;
        boolean z5;
        ConstraintWidget constraintWidget8;
        int i8;
        int i9 = i2;
        ConstraintWidget constraintWidget9 = chainHead.f1929a;
        ConstraintWidget constraintWidget10 = chainHead.f1931c;
        ConstraintWidget constraintWidget11 = chainHead.f1930b;
        ConstraintWidget constraintWidget12 = chainHead.f1932d;
        ConstraintWidget constraintWidget13 = chainHead.f1933e;
        float f2 = chainHead.f1939k;
        boolean z6 = constraintWidgetContainer.mListDimensionBehaviors[i9] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (i9 == 0) {
            int i10 = constraintWidget13.D;
            z = i10 == 0;
            z2 = i10 == 1;
        } else {
            int i11 = constraintWidget13.E;
            z = i11 == 0;
            z2 = i11 == 1;
        }
        ConstraintWidget constraintWidget14 = constraintWidget9;
        boolean z7 = false;
        while (true) {
            if (z7) {
                break;
            }
            ConstraintAnchor constraintAnchor7 = constraintWidget14.mListAnchors[i3];
            int i12 = z3 ? 1 : 4;
            int margin = constraintAnchor7.getMargin();
            float f3 = f2;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour = constraintWidget14.mListDimensionBehaviors[i9];
            boolean z8 = z7;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
            if (dimensionBehaviour == dimensionBehaviour2 && constraintWidget14.mResolvedMatchConstraintDefault[i9] == 0) {
                z4 = z;
                z5 = true;
            } else {
                z4 = z;
                z5 = false;
            }
            ConstraintAnchor constraintAnchor8 = constraintAnchor7.mTarget;
            if (constraintAnchor8 != null && constraintWidget14 != constraintWidget9) {
                margin += constraintAnchor8.getMargin();
            }
            int i13 = margin;
            if (!z3 || constraintWidget14 == constraintWidget9 || constraintWidget14 == constraintWidget11) {
                constraintWidget8 = constraintWidget13;
            } else {
                constraintWidget8 = constraintWidget13;
                i12 = 8;
            }
            ConstraintAnchor constraintAnchor9 = constraintAnchor7.mTarget;
            ConstraintWidget constraintWidget15 = constraintWidget9;
            if (constraintAnchor9 != null) {
                if (constraintWidget14 == constraintWidget11) {
                    linearSystem.addGreaterThan(constraintAnchor7.f1947b, constraintAnchor9.f1947b, i13, 6);
                } else {
                    linearSystem.addGreaterThan(constraintAnchor7.f1947b, constraintAnchor9.f1947b, i13, 8);
                }
                if (z5 && !z3) {
                    i12 = 5;
                }
                linearSystem.addEquality(constraintAnchor7.f1947b, constraintAnchor7.mTarget.f1947b, i13, (constraintWidget14 == constraintWidget11 && z3 && constraintWidget14.isInBarrier(i9)) ? 5 : i12);
            }
            if (z6) {
                if (constraintWidget14.getVisibility() == 8 || constraintWidget14.mListDimensionBehaviors[i9] != dimensionBehaviour2) {
                    i8 = 0;
                } else {
                    ConstraintAnchor[] constraintAnchorArr = constraintWidget14.mListAnchors;
                    i8 = 0;
                    linearSystem.addGreaterThan(constraintAnchorArr[i3 + 1].f1947b, constraintAnchorArr[i3].f1947b, 0, 5);
                }
                linearSystem.addGreaterThan(constraintWidget14.mListAnchors[i3].f1947b, constraintWidgetContainer.mListAnchors[i3].f1947b, i8, 8);
            }
            ConstraintAnchor constraintAnchor10 = constraintWidget14.mListAnchors[i3 + 1].mTarget;
            if (constraintAnchor10 != null) {
                ConstraintWidget constraintWidget16 = constraintAnchor10.mOwner;
                ConstraintAnchor[] constraintAnchorArr2 = constraintWidget16.mListAnchors;
                if (constraintAnchorArr2[i3].mTarget != null && constraintAnchorArr2[i3].mTarget.mOwner == constraintWidget14) {
                    r22 = constraintWidget16;
                }
            }
            if (r22 != null) {
                constraintWidget14 = r22;
                z7 = z8;
            } else {
                z7 = true;
            }
            constraintWidget13 = constraintWidget8;
            f2 = f3;
            z = z4;
            constraintWidget9 = constraintWidget15;
        }
        ConstraintWidget constraintWidget17 = constraintWidget13;
        float f4 = f2;
        ConstraintWidget constraintWidget18 = constraintWidget9;
        boolean z9 = z;
        if (constraintWidget12 != null) {
            int i14 = i3 + 1;
            if (constraintWidget10.mListAnchors[i14].mTarget != null) {
                ConstraintAnchor constraintAnchor11 = constraintWidget12.mListAnchors[i14];
                if ((constraintWidget12.mListDimensionBehaviors[i9] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget12.mResolvedMatchConstraintDefault[i9] == 0) && !z3) {
                    ConstraintAnchor constraintAnchor12 = constraintAnchor11.mTarget;
                    if (constraintAnchor12.mOwner == constraintWidgetContainer) {
                        linearSystem.addEquality(constraintAnchor11.f1947b, constraintAnchor12.f1947b, -constraintAnchor11.getMargin(), 5);
                        linearSystem.addLowerThan(constraintAnchor11.f1947b, constraintWidget10.mListAnchors[i14].mTarget.f1947b, -constraintAnchor11.getMargin(), 6);
                        if (z6) {
                            int i15 = i3 + 1;
                            SolverVariable solverVariable5 = constraintWidgetContainer.mListAnchors[i15].f1947b;
                            ConstraintAnchor[] constraintAnchorArr3 = constraintWidget10.mListAnchors;
                            linearSystem.addGreaterThan(solverVariable5, constraintAnchorArr3[i15].f1947b, constraintAnchorArr3[i15].getMargin(), 8);
                        }
                        arrayList = chainHead.f1936h;
                        if (arrayList != null && (size = arrayList.size()) > 1) {
                            float f5 = (chainHead.f1943o || chainHead.f1945q) ? f4 : chainHead.f1938j;
                            float f6 = 0.0f;
                            float f7 = 0.0f;
                            ConstraintWidget constraintWidget19 = null;
                            i6 = 0;
                            while (i6 < size) {
                                ConstraintWidget constraintWidget20 = (ConstraintWidget) arrayList.get(i6);
                                float f8 = constraintWidget20.mWeight[i9];
                                if (f8 < f6) {
                                    if (chainHead.f1945q) {
                                        ConstraintAnchor[] constraintAnchorArr4 = constraintWidget20.mListAnchors;
                                        linearSystem.addEquality(constraintAnchorArr4[i3 + 1].f1947b, constraintAnchorArr4[i3].f1947b, 0, 4);
                                        arrayList2 = arrayList;
                                        i7 = size;
                                        i6++;
                                        size = i7;
                                        arrayList = arrayList2;
                                        f6 = 0.0f;
                                    } else {
                                        f8 = 1.0f;
                                        f6 = 0.0f;
                                    }
                                }
                                if (f8 == f6) {
                                    ConstraintAnchor[] constraintAnchorArr5 = constraintWidget20.mListAnchors;
                                    linearSystem.addEquality(constraintAnchorArr5[i3 + 1].f1947b, constraintAnchorArr5[i3].f1947b, 0, 8);
                                    arrayList2 = arrayList;
                                    i7 = size;
                                    i6++;
                                    size = i7;
                                    arrayList = arrayList2;
                                    f6 = 0.0f;
                                } else {
                                    if (constraintWidget19 != null) {
                                        ConstraintAnchor[] constraintAnchorArr6 = constraintWidget19.mListAnchors;
                                        SolverVariable solverVariable6 = constraintAnchorArr6[i3].f1947b;
                                        int i16 = i3 + 1;
                                        SolverVariable solverVariable7 = constraintAnchorArr6[i16].f1947b;
                                        ConstraintAnchor[] constraintAnchorArr7 = constraintWidget20.mListAnchors;
                                        arrayList2 = arrayList;
                                        SolverVariable solverVariable8 = constraintAnchorArr7[i3].f1947b;
                                        SolverVariable solverVariable9 = constraintAnchorArr7[i16].f1947b;
                                        i7 = size;
                                        ArrayRow createRow = linearSystem.createRow();
                                        createRow.createRowEqualMatchDimensions(f7, f5, f8, solverVariable6, solverVariable7, solverVariable8, solverVariable9);
                                        linearSystem.addConstraint(createRow);
                                    } else {
                                        arrayList2 = arrayList;
                                        i7 = size;
                                    }
                                    f7 = f8;
                                    constraintWidget19 = constraintWidget20;
                                    i6++;
                                    size = i7;
                                    arrayList = arrayList2;
                                    f6 = 0.0f;
                                }
                            }
                        }
                        if (constraintWidget11 == null && (constraintWidget11 == constraintWidget12 || z3)) {
                            ConstraintAnchor constraintAnchor13 = constraintWidget18.mListAnchors[i3];
                            int i17 = i3 + 1;
                            ConstraintAnchor constraintAnchor14 = constraintWidget10.mListAnchors[i17];
                            ConstraintAnchor constraintAnchor15 = constraintAnchor13.mTarget;
                            SolverVariable solverVariable10 = constraintAnchor15 != null ? constraintAnchor15.f1947b : null;
                            ConstraintAnchor constraintAnchor16 = constraintAnchor14.mTarget;
                            SolverVariable solverVariable11 = constraintAnchor16 != null ? constraintAnchor16.f1947b : null;
                            ConstraintAnchor constraintAnchor17 = constraintWidget11.mListAnchors[i3];
                            if (constraintWidget12 != null) {
                                constraintAnchor14 = constraintWidget12.mListAnchors[i17];
                            }
                            if (solverVariable10 != null && solverVariable11 != null) {
                                linearSystem.addCentering(constraintAnchor17.f1947b, solverVariable10, constraintAnchor17.getMargin(), i9 == 0 ? constraintWidget17.f1966r : constraintWidget17.f1967s, solverVariable11, constraintAnchor14.f1947b, constraintAnchor14.getMargin(), 7);
                            }
                        } else if (z9 || constraintWidget11 == null) {
                            int i18 = 8;
                            if (z2 && constraintWidget11 != null) {
                                int i19 = chainHead.f1938j;
                                boolean z10 = i19 <= 0 && chainHead.f1937i == i19;
                                ConstraintWidget constraintWidget21 = constraintWidget11;
                                constraintWidget = constraintWidget21;
                                while (constraintWidget != null) {
                                    ConstraintWidget constraintWidget22 = constraintWidget.I[i9];
                                    while (constraintWidget22 != null && constraintWidget22.getVisibility() == i18) {
                                        constraintWidget22 = constraintWidget22.I[i9];
                                    }
                                    if (constraintWidget == constraintWidget11 || constraintWidget == constraintWidget12 || constraintWidget22 == null) {
                                        constraintWidget2 = constraintWidget21;
                                        i5 = i18;
                                    } else {
                                        ConstraintWidget constraintWidget23 = constraintWidget22 == constraintWidget12 ? null : constraintWidget22;
                                        ConstraintAnchor constraintAnchor18 = constraintWidget.mListAnchors[i3];
                                        SolverVariable solverVariable12 = constraintAnchor18.f1947b;
                                        ConstraintAnchor constraintAnchor19 = constraintAnchor18.mTarget;
                                        if (constraintAnchor19 != null) {
                                            SolverVariable solverVariable13 = constraintAnchor19.f1947b;
                                        }
                                        int i20 = i3 + 1;
                                        SolverVariable solverVariable14 = constraintWidget21.mListAnchors[i20].f1947b;
                                        int margin2 = constraintAnchor18.getMargin();
                                        int margin3 = constraintWidget.mListAnchors[i20].getMargin();
                                        if (constraintWidget23 != null) {
                                            constraintAnchor4 = constraintWidget23.mListAnchors[i3];
                                            SolverVariable solverVariable15 = constraintAnchor4.f1947b;
                                            constraintWidget3 = constraintWidget23;
                                            ConstraintAnchor constraintAnchor20 = constraintAnchor4.mTarget;
                                            solverVariable2 = constraintAnchor20 != null ? constraintAnchor20.f1947b : null;
                                            solverVariable = solverVariable15;
                                        } else {
                                            constraintWidget3 = constraintWidget23;
                                            constraintAnchor4 = constraintWidget12.mListAnchors[i3];
                                            solverVariable = constraintAnchor4 != null ? constraintAnchor4.f1947b : null;
                                            solverVariable2 = constraintWidget.mListAnchors[i20].f1947b;
                                        }
                                        if (constraintAnchor4 != null) {
                                            margin3 += constraintAnchor4.getMargin();
                                        }
                                        int i21 = margin3;
                                        int margin4 = constraintWidget21.mListAnchors[i20].getMargin() + margin2;
                                        int i22 = z10 ? 8 : 4;
                                        if (solverVariable12 == null || solverVariable14 == null || solverVariable == null || solverVariable2 == null) {
                                            constraintWidget4 = constraintWidget3;
                                            constraintWidget2 = constraintWidget21;
                                            i5 = 8;
                                        } else {
                                            constraintWidget4 = constraintWidget3;
                                            constraintWidget2 = constraintWidget21;
                                            i5 = 8;
                                            linearSystem.addCentering(solverVariable12, solverVariable14, margin4, 0.5f, solverVariable, solverVariable2, i21, i22);
                                        }
                                        constraintWidget22 = constraintWidget4;
                                    }
                                    constraintWidget21 = constraintWidget.getVisibility() != i5 ? constraintWidget : constraintWidget2;
                                    constraintWidget = constraintWidget22;
                                    i18 = i5;
                                    i9 = i2;
                                }
                                ConstraintAnchor constraintAnchor21 = constraintWidget11.mListAnchors[i3];
                                constraintAnchor = constraintWidget18.mListAnchors[i3].mTarget;
                                int i23 = i3 + 1;
                                constraintAnchor2 = constraintWidget12.mListAnchors[i23];
                                constraintAnchor3 = constraintWidget10.mListAnchors[i23].mTarget;
                                if (constraintAnchor != null) {
                                    i4 = 5;
                                } else if (constraintWidget11 != constraintWidget12) {
                                    i4 = 5;
                                    linearSystem.addEquality(constraintAnchor21.f1947b, constraintAnchor.f1947b, constraintAnchor21.getMargin(), 5);
                                } else {
                                    i4 = 5;
                                    if (constraintAnchor3 != null) {
                                        linearSystem.addCentering(constraintAnchor21.f1947b, constraintAnchor.f1947b, constraintAnchor21.getMargin(), 0.5f, constraintAnchor2.f1947b, constraintAnchor3.f1947b, constraintAnchor2.getMargin(), 5);
                                    }
                                }
                                if (constraintAnchor3 != null && constraintWidget11 != constraintWidget12) {
                                    linearSystem.addEquality(constraintAnchor2.f1947b, constraintAnchor3.f1947b, -constraintAnchor2.getMargin(), i4);
                                }
                            }
                        } else {
                            int i24 = chainHead.f1938j;
                            boolean z11 = i24 > 0 && chainHead.f1937i == i24;
                            ConstraintWidget constraintWidget24 = constraintWidget11;
                            ConstraintWidget constraintWidget25 = constraintWidget24;
                            while (constraintWidget25 != null) {
                                ConstraintWidget constraintWidget26 = constraintWidget25.I[i9];
                                while (constraintWidget26 != null && constraintWidget26.getVisibility() == 8) {
                                    constraintWidget26 = constraintWidget26.I[i9];
                                }
                                if (constraintWidget26 != null || constraintWidget25 == constraintWidget12) {
                                    ConstraintAnchor constraintAnchor22 = constraintWidget25.mListAnchors[i3];
                                    SolverVariable solverVariable16 = constraintAnchor22.f1947b;
                                    ConstraintAnchor constraintAnchor23 = constraintAnchor22.mTarget;
                                    SolverVariable solverVariable17 = constraintAnchor23 != null ? constraintAnchor23.f1947b : null;
                                    if (constraintWidget24 != constraintWidget25) {
                                        constraintAnchor5 = constraintWidget24.mListAnchors[i3 + 1];
                                    } else {
                                        if (constraintWidget25 == constraintWidget11) {
                                            ConstraintAnchor[] constraintAnchorArr8 = constraintWidget18.mListAnchors;
                                            if (constraintAnchorArr8[i3].mTarget != null) {
                                                constraintAnchor5 = constraintAnchorArr8[i3].mTarget;
                                            } else {
                                                solverVariable17 = null;
                                            }
                                        }
                                        int margin5 = constraintAnchor22.getMargin();
                                        int i25 = i3 + 1;
                                        int margin6 = constraintWidget25.mListAnchors[i25].getMargin();
                                        if (constraintWidget26 == null) {
                                            constraintAnchor6 = constraintWidget26.mListAnchors[i3];
                                        } else {
                                            constraintAnchor6 = constraintWidget10.mListAnchors[i25].mTarget;
                                            if (constraintAnchor6 == null) {
                                                constraintWidget5 = constraintWidget26;
                                                solverVariable3 = null;
                                                SolverVariable solverVariable18 = constraintWidget25.mListAnchors[i25].f1947b;
                                                if (constraintAnchor6 != null) {
                                                    margin6 += constraintAnchor6.getMargin();
                                                }
                                                int margin7 = margin5 + constraintWidget24.mListAnchors[i25].getMargin();
                                                if (solverVariable16 != null || solverVariable17 == null || solverVariable3 == null || solverVariable18 == null) {
                                                    constraintWidget6 = constraintWidget5;
                                                } else {
                                                    if (constraintWidget25 == constraintWidget11) {
                                                        margin7 = constraintWidget11.mListAnchors[i3].getMargin();
                                                    }
                                                    int i26 = margin7;
                                                    constraintWidget6 = constraintWidget5;
                                                    constraintWidget7 = constraintWidget24;
                                                    linearSystem.addCentering(solverVariable16, solverVariable17, i26, 0.5f, solverVariable3, solverVariable18, constraintWidget25 == constraintWidget12 ? constraintWidget12.mListAnchors[i25].getMargin() : margin6, z11 ? 8 : 5);
                                                    if (constraintWidget25.getVisibility() != 8) {
                                                        constraintWidget25 = constraintWidget7;
                                                    }
                                                    constraintWidget24 = constraintWidget25;
                                                    constraintWidget25 = constraintWidget6;
                                                }
                                            }
                                        }
                                        solverVariable3 = constraintAnchor6.f1947b;
                                        constraintWidget5 = constraintWidget26;
                                        SolverVariable solverVariable182 = constraintWidget25.mListAnchors[i25].f1947b;
                                        if (constraintAnchor6 != null) {
                                        }
                                        int margin72 = margin5 + constraintWidget24.mListAnchors[i25].getMargin();
                                        if (solverVariable16 != null) {
                                        }
                                        constraintWidget6 = constraintWidget5;
                                    }
                                    solverVariable17 = constraintAnchor5.f1947b;
                                    int margin52 = constraintAnchor22.getMargin();
                                    int i252 = i3 + 1;
                                    int margin62 = constraintWidget25.mListAnchors[i252].getMargin();
                                    if (constraintWidget26 == null) {
                                    }
                                    solverVariable3 = constraintAnchor6.f1947b;
                                    constraintWidget5 = constraintWidget26;
                                    SolverVariable solverVariable1822 = constraintWidget25.mListAnchors[i252].f1947b;
                                    if (constraintAnchor6 != null) {
                                    }
                                    int margin722 = margin52 + constraintWidget24.mListAnchors[i252].getMargin();
                                    if (solverVariable16 != null) {
                                    }
                                    constraintWidget6 = constraintWidget5;
                                } else {
                                    constraintWidget6 = constraintWidget26;
                                }
                                constraintWidget7 = constraintWidget24;
                                if (constraintWidget25.getVisibility() != 8) {
                                }
                                constraintWidget24 = constraintWidget25;
                                constraintWidget25 = constraintWidget6;
                            }
                        }
                        if ((z9 && !z2) || constraintWidget11 == null || constraintWidget11 == constraintWidget12) {
                            return;
                        }
                        ConstraintAnchor[] constraintAnchorArr9 = constraintWidget11.mListAnchors;
                        ConstraintAnchor constraintAnchor24 = constraintAnchorArr9[i3];
                        if (constraintWidget12 == null) {
                            constraintWidget12 = constraintWidget11;
                        }
                        int i27 = i3 + 1;
                        ConstraintAnchor constraintAnchor25 = constraintWidget12.mListAnchors[i27];
                        ConstraintAnchor constraintAnchor26 = constraintAnchor24.mTarget;
                        solverVariable4 = constraintAnchor26 != null ? constraintAnchor26.f1947b : null;
                        ConstraintAnchor constraintAnchor27 = constraintAnchor25.mTarget;
                        SolverVariable solverVariable19 = constraintAnchor27 != null ? constraintAnchor27.f1947b : null;
                        if (constraintWidget10 != constraintWidget12) {
                            ConstraintAnchor constraintAnchor28 = constraintWidget10.mListAnchors[i27].mTarget;
                            solverVariable19 = constraintAnchor28 != null ? constraintAnchor28.f1947b : null;
                        }
                        if (constraintWidget11 == constraintWidget12) {
                            constraintAnchor24 = constraintAnchorArr9[i3];
                            constraintAnchor25 = constraintAnchorArr9[i27];
                        }
                        if (solverVariable4 == null || solverVariable19 == null) {
                            return;
                        }
                        linearSystem.addCentering(constraintAnchor24.f1947b, solverVariable4, constraintAnchor24.getMargin(), 0.5f, solverVariable19, constraintAnchor25.f1947b, constraintWidget12.mListAnchors[i27].getMargin(), 5);
                        return;
                    }
                }
                if (z3) {
                    ConstraintAnchor constraintAnchor29 = constraintAnchor11.mTarget;
                    if (constraintAnchor29.mOwner == constraintWidgetContainer) {
                        linearSystem.addEquality(constraintAnchor11.f1947b, constraintAnchor29.f1947b, -constraintAnchor11.getMargin(), 4);
                    }
                }
                linearSystem.addLowerThan(constraintAnchor11.f1947b, constraintWidget10.mListAnchors[i14].mTarget.f1947b, -constraintAnchor11.getMargin(), 6);
                if (z6) {
                }
                arrayList = chainHead.f1936h;
                if (arrayList != null) {
                    if (chainHead.f1943o) {
                    }
                    float f62 = 0.0f;
                    float f72 = 0.0f;
                    ConstraintWidget constraintWidget192 = null;
                    i6 = 0;
                    while (i6 < size) {
                    }
                }
                if (constraintWidget11 == null) {
                }
                if (z9) {
                }
                int i182 = 8;
                if (z2) {
                    int i192 = chainHead.f1938j;
                    if (i192 <= 0) {
                    }
                    ConstraintWidget constraintWidget212 = constraintWidget11;
                    constraintWidget = constraintWidget212;
                    while (constraintWidget != null) {
                    }
                    ConstraintAnchor constraintAnchor212 = constraintWidget11.mListAnchors[i3];
                    constraintAnchor = constraintWidget18.mListAnchors[i3].mTarget;
                    int i232 = i3 + 1;
                    constraintAnchor2 = constraintWidget12.mListAnchors[i232];
                    constraintAnchor3 = constraintWidget10.mListAnchors[i232].mTarget;
                    if (constraintAnchor != null) {
                    }
                    if (constraintAnchor3 != null) {
                        linearSystem.addEquality(constraintAnchor2.f1947b, constraintAnchor3.f1947b, -constraintAnchor2.getMargin(), i4);
                    }
                }
                if (z9) {
                }
                ConstraintAnchor[] constraintAnchorArr92 = constraintWidget11.mListAnchors;
                ConstraintAnchor constraintAnchor242 = constraintAnchorArr92[i3];
                if (constraintWidget12 == null) {
                }
                int i272 = i3 + 1;
                ConstraintAnchor constraintAnchor252 = constraintWidget12.mListAnchors[i272];
                ConstraintAnchor constraintAnchor262 = constraintAnchor242.mTarget;
                if (constraintAnchor262 != null) {
                }
                ConstraintAnchor constraintAnchor272 = constraintAnchor252.mTarget;
                if (constraintAnchor272 != null) {
                }
                if (constraintWidget10 != constraintWidget12) {
                }
                if (constraintWidget11 == constraintWidget12) {
                }
                if (solverVariable4 == null) {
                    return;
                }
                return;
            }
        }
        if (z6) {
        }
        arrayList = chainHead.f1936h;
        if (arrayList != null) {
        }
        if (constraintWidget11 == null) {
        }
        if (z9) {
        }
        int i1822 = 8;
        if (z2) {
        }
        if (z9) {
        }
        ConstraintAnchor[] constraintAnchorArr922 = constraintWidget11.mListAnchors;
        ConstraintAnchor constraintAnchor2422 = constraintAnchorArr922[i3];
        if (constraintWidget12 == null) {
        }
        int i2722 = i3 + 1;
        ConstraintAnchor constraintAnchor2522 = constraintWidget12.mListAnchors[i2722];
        ConstraintAnchor constraintAnchor2622 = constraintAnchor2422.mTarget;
        if (constraintAnchor2622 != null) {
        }
        ConstraintAnchor constraintAnchor2722 = constraintAnchor2522.mTarget;
        if (constraintAnchor2722 != null) {
        }
        if (constraintWidget10 != constraintWidget12) {
        }
        if (constraintWidget11 == constraintWidget12) {
        }
        if (solverVariable4 == null) {
        }
    }

    public static void applyChainConstraints(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, ArrayList<ConstraintWidget> arrayList, int i2) {
        ChainHead[] chainHeadArr;
        int i3;
        int i4;
        if (i2 == 0) {
            i4 = constraintWidgetContainer.mHorizontalChainsSize;
            chainHeadArr = constraintWidgetContainer.R;
            i3 = 0;
        } else {
            int i5 = constraintWidgetContainer.mVerticalChainsSize;
            chainHeadArr = constraintWidgetContainer.Q;
            i3 = 2;
            i4 = i5;
        }
        for (int i6 = 0; i6 < i4; i6++) {
            ChainHead chainHead = chainHeadArr[i6];
            chainHead.define();
            if (arrayList == null || arrayList.contains(chainHead.f1929a)) {
                a(constraintWidgetContainer, linearSystem, i2, i3, chainHead);
            }
        }
    }
}
