package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.Helper;
import androidx.constraintlayout.core.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.core.widgets.analyzer.WidgetRun;
import java.util.List;
/* loaded from: classes.dex */
public class HorizontalWidgetRun extends WidgetRun {
    private static int[] tempDimensions = new int[2];

    /* renamed from: androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f1984a;

        static {
            int[] iArr = new int[WidgetRun.RunType.values().length];
            f1984a = iArr;
            try {
                iArr[WidgetRun.RunType.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1984a[WidgetRun.RunType.END.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1984a[WidgetRun.RunType.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public HorizontalWidgetRun(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        this.start.f1978b = DependencyNode.Type.LEFT;
        this.end.f1978b = DependencyNode.Type.RIGHT;
        this.orientation = 0;
    }

    private void computeInsetRatio(int[] iArr, int i2, int i3, int i4, int i5, float f2, int i6) {
        int i7 = i3 - i2;
        int i8 = i5 - i4;
        if (i6 != -1) {
            if (i6 == 0) {
                iArr[0] = (int) ((i8 * f2) + 0.5f);
                iArr[1] = i8;
                return;
            } else if (i6 != 1) {
                return;
            } else {
                iArr[0] = i7;
                iArr[1] = (int) ((i7 * f2) + 0.5f);
                return;
            }
        }
        int i9 = (int) ((i8 * f2) + 0.5f);
        int i10 = (int) ((i7 / f2) + 0.5f);
        if (i9 <= i7) {
            iArr[0] = i9;
            iArr[1] = i8;
        } else if (i10 <= i8) {
            iArr[0] = i7;
            iArr[1] = i10;
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void applyToWidget() {
        DependencyNode dependencyNode = this.start;
        if (dependencyNode.resolved) {
            this.f2003a.setX(dependencyNode.value);
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void c() {
        ConstraintWidget parent;
        DependencyNode dependencyNode;
        DependencyNode dependencyNode2;
        int x;
        DependencyNode dependencyNode3;
        ConstraintAnchor constraintAnchor;
        List list;
        Object obj;
        DependencyNode dependencyNode4;
        DependencyNode dependencyNode5;
        DependencyNode dependencyNode6;
        int x2;
        DependencyNode dependencyNode7;
        DependencyNode dependencyNode8;
        int i2;
        ConstraintWidget parent2;
        ConstraintWidget constraintWidget = this.f2003a;
        if (constraintWidget.measured) {
            this.f2006d.resolve(constraintWidget.getWidth());
        }
        if (this.f2006d.resolved) {
            ConstraintWidget.DimensionBehaviour dimensionBehaviour = this.f2005c;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
            if (dimensionBehaviour == dimensionBehaviour2 && (parent = this.f2003a.getParent()) != null && (parent.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.FIXED || parent.getHorizontalDimensionBehaviour() == dimensionBehaviour2)) {
                a(this.start, parent.horizontalRun.start, this.f2003a.mLeft.getMargin());
                a(this.end, parent.horizontalRun.end, -this.f2003a.mRight.getMargin());
                return;
            }
        } else {
            ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour = this.f2003a.getHorizontalDimensionBehaviour();
            this.f2005c = horizontalDimensionBehaviour;
            if (horizontalDimensionBehaviour != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
                if (horizontalDimensionBehaviour == dimensionBehaviour3 && (parent2 = this.f2003a.getParent()) != null && (parent2.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.FIXED || parent2.getHorizontalDimensionBehaviour() == dimensionBehaviour3)) {
                    int width = (parent2.getWidth() - this.f2003a.mLeft.getMargin()) - this.f2003a.mRight.getMargin();
                    a(this.start, parent2.horizontalRun.start, this.f2003a.mLeft.getMargin());
                    a(this.end, parent2.horizontalRun.end, -this.f2003a.mRight.getMargin());
                    this.f2006d.resolve(width);
                    return;
                } else if (this.f2005c == ConstraintWidget.DimensionBehaviour.FIXED) {
                    this.f2006d.resolve(this.f2003a.getWidth());
                }
            }
        }
        DimensionDependency dimensionDependency = this.f2006d;
        if (dimensionDependency.resolved) {
            ConstraintWidget constraintWidget2 = this.f2003a;
            if (constraintWidget2.measured) {
                ConstraintAnchor[] constraintAnchorArr = constraintWidget2.mListAnchors;
                if (constraintAnchorArr[0].mTarget != null && constraintAnchorArr[1].mTarget != null) {
                    if (constraintWidget2.isInHorizontalChain()) {
                        this.start.f1979c = this.f2003a.mListAnchors[0].getMargin();
                        dependencyNode3 = this.end;
                        constraintAnchor = this.f2003a.mListAnchors[1];
                        dependencyNode3.f1979c = -constraintAnchor.getMargin();
                        return;
                    }
                    DependencyNode f2 = f(this.f2003a.mListAnchors[0]);
                    if (f2 != null) {
                        a(this.start, f2, this.f2003a.mListAnchors[0].getMargin());
                    }
                    DependencyNode f3 = f(this.f2003a.mListAnchors[1]);
                    if (f3 != null) {
                        a(this.end, f3, -this.f2003a.mListAnchors[1].getMargin());
                    }
                    this.start.delegateToWidgetRun = true;
                    this.end.delegateToWidgetRun = true;
                    return;
                }
                if (constraintAnchorArr[0].mTarget != null) {
                    dependencyNode5 = f(constraintAnchorArr[0]);
                    if (dependencyNode5 == null) {
                        return;
                    }
                    dependencyNode6 = this.start;
                    x2 = this.f2003a.mListAnchors[0].getMargin();
                } else if (constraintAnchorArr[1].mTarget != null) {
                    DependencyNode f4 = f(constraintAnchorArr[1]);
                    if (f4 != null) {
                        a(this.end, f4, -this.f2003a.mListAnchors[1].getMargin());
                        dependencyNode7 = this.start;
                        dependencyNode8 = this.end;
                        i2 = -this.f2006d.value;
                        a(dependencyNode7, dependencyNode8, i2);
                        return;
                    }
                    return;
                } else if ((constraintWidget2 instanceof Helper) || constraintWidget2.getParent() == null || this.f2003a.getAnchor(ConstraintAnchor.Type.CENTER).mTarget != null) {
                    return;
                } else {
                    dependencyNode5 = this.f2003a.getParent().horizontalRun.start;
                    dependencyNode6 = this.start;
                    x2 = this.f2003a.getX();
                }
                a(dependencyNode6, dependencyNode5, x2);
                dependencyNode7 = this.end;
                dependencyNode8 = this.start;
                i2 = this.f2006d.value;
                a(dependencyNode7, dependencyNode8, i2);
                return;
            }
        }
        if (this.f2005c == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            ConstraintWidget constraintWidget3 = this.f2003a;
            int i3 = constraintWidget3.mMatchConstraintDefaultWidth;
            if (i3 == 2) {
                ConstraintWidget parent3 = constraintWidget3.getParent();
                if (parent3 != null) {
                    DimensionDependency dimensionDependency2 = parent3.verticalRun.f2006d;
                    this.f2006d.f1983g.add(dimensionDependency2);
                    dimensionDependency2.f1982f.add(this.f2006d);
                    DimensionDependency dimensionDependency3 = this.f2006d;
                    dimensionDependency3.delegateToWidgetRun = true;
                    dimensionDependency3.f1982f.add(this.start);
                    list = this.f2006d.f1982f;
                    obj = this.end;
                    list.add(obj);
                }
            } else if (i3 == 3) {
                if (constraintWidget3.mMatchConstraintDefaultHeight == 3) {
                    this.start.updateDelegate = this;
                    this.end.updateDelegate = this;
                    VerticalWidgetRun verticalWidgetRun = constraintWidget3.verticalRun;
                    verticalWidgetRun.start.updateDelegate = this;
                    verticalWidgetRun.end.updateDelegate = this;
                    dimensionDependency.updateDelegate = this;
                    if (constraintWidget3.isInVerticalChain()) {
                        this.f2006d.f1983g.add(this.f2003a.verticalRun.f2006d);
                        this.f2003a.verticalRun.f2006d.f1982f.add(this.f2006d);
                        VerticalWidgetRun verticalWidgetRun2 = this.f2003a.verticalRun;
                        verticalWidgetRun2.f2006d.updateDelegate = this;
                        this.f2006d.f1983g.add(verticalWidgetRun2.start);
                        this.f2006d.f1983g.add(this.f2003a.verticalRun.end);
                        this.f2003a.verticalRun.start.f1982f.add(this.f2006d);
                        list = this.f2003a.verticalRun.end.f1982f;
                        obj = this.f2006d;
                        list.add(obj);
                    } else if (this.f2003a.isInHorizontalChain()) {
                        this.f2003a.verticalRun.f2006d.f1983g.add(this.f2006d);
                        list = this.f2006d.f1982f;
                        obj = this.f2003a.verticalRun.f2006d;
                        list.add(obj);
                    } else {
                        dependencyNode4 = this.f2003a.verticalRun.f2006d;
                    }
                } else {
                    DimensionDependency dimensionDependency4 = constraintWidget3.verticalRun.f2006d;
                    dimensionDependency.f1983g.add(dimensionDependency4);
                    dimensionDependency4.f1982f.add(this.f2006d);
                    this.f2003a.verticalRun.start.f1982f.add(this.f2006d);
                    this.f2003a.verticalRun.end.f1982f.add(this.f2006d);
                    DimensionDependency dimensionDependency5 = this.f2006d;
                    dimensionDependency5.delegateToWidgetRun = true;
                    dimensionDependency5.f1982f.add(this.start);
                    this.f2006d.f1982f.add(this.end);
                    this.start.f1983g.add(this.f2006d);
                    dependencyNode4 = this.end;
                }
                list = dependencyNode4.f1983g;
                obj = this.f2006d;
                list.add(obj);
            }
            dependencyNode3.f1979c = -constraintAnchor.getMargin();
            return;
        }
        ConstraintWidget constraintWidget4 = this.f2003a;
        ConstraintAnchor[] constraintAnchorArr2 = constraintWidget4.mListAnchors;
        if (constraintAnchorArr2[0].mTarget != null && constraintAnchorArr2[1].mTarget != null) {
            if (constraintWidget4.isInHorizontalChain()) {
                this.start.f1979c = this.f2003a.mListAnchors[0].getMargin();
                dependencyNode3 = this.end;
                constraintAnchor = this.f2003a.mListAnchors[1];
                dependencyNode3.f1979c = -constraintAnchor.getMargin();
                return;
            }
            DependencyNode f5 = f(this.f2003a.mListAnchors[0]);
            DependencyNode f6 = f(this.f2003a.mListAnchors[1]);
            if (f5 != null) {
                f5.addDependency(this);
            }
            if (f6 != null) {
                f6.addDependency(this);
            }
            this.f2008f = WidgetRun.RunType.CENTER;
            return;
        }
        if (constraintAnchorArr2[0].mTarget != null) {
            dependencyNode = f(constraintAnchorArr2[0]);
            if (dependencyNode == null) {
                return;
            }
            dependencyNode2 = this.start;
            x = this.f2003a.mListAnchors[0].getMargin();
        } else if (constraintAnchorArr2[1].mTarget != null) {
            DependencyNode f7 = f(constraintAnchorArr2[1]);
            if (f7 != null) {
                a(this.end, f7, -this.f2003a.mListAnchors[1].getMargin());
                b(this.start, this.end, -1, this.f2006d);
                return;
            }
            return;
        } else if ((constraintWidget4 instanceof Helper) || constraintWidget4.getParent() == null) {
            return;
        } else {
            dependencyNode = this.f2003a.getParent().horizontalRun.start;
            dependencyNode2 = this.start;
            x = this.f2003a.getX();
        }
        a(dependencyNode2, dependencyNode, x);
        b(this.end, this.start, 1, this.f2006d);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void d() {
        this.f2004b = null;
        this.start.clear();
        this.end.clear();
        this.f2006d.clear();
        this.f2007e = false;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    boolean h() {
        return this.f2005c != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || this.f2003a.mMatchConstraintDefaultWidth == 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void l() {
        this.f2007e = false;
        this.start.clear();
        this.start.resolved = false;
        this.end.clear();
        this.end.resolved = false;
        this.f2006d.resolved = false;
    }

    public String toString() {
        return "HorizontalRun " + this.f2003a.getDebugName();
    }

    /* JADX WARN: Code restructure failed: missing block: B:117:0x0296, code lost:
        if (r14 != 1) goto L130;
     */
    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun, androidx.constraintlayout.core.widgets.analyzer.Dependency
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void update(Dependency dependency) {
        DimensionDependency dimensionDependency;
        int i2;
        int e2;
        int e3;
        float f2;
        DimensionDependency dimensionDependency2;
        int e4;
        int e5;
        float f3;
        ConstraintWidget constraintWidget;
        float dimensionRatio;
        ConstraintWidget constraintWidget2;
        int i3 = AnonymousClass1.f1984a[this.f2008f.ordinal()];
        if (i3 == 1) {
            k(dependency);
        } else if (i3 == 2) {
            j(dependency);
        } else if (i3 == 3) {
            ConstraintWidget constraintWidget3 = this.f2003a;
            i(dependency, constraintWidget3.mLeft, constraintWidget3.mRight, 0);
            return;
        }
        if (!this.f2006d.resolved && this.f2005c == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            ConstraintWidget constraintWidget4 = this.f2003a;
            int i4 = constraintWidget4.mMatchConstraintDefaultWidth;
            if (i4 == 2) {
                ConstraintWidget parent = constraintWidget4.getParent();
                if (parent != null) {
                    if (parent.horizontalRun.f2006d.resolved) {
                        i2 = (int) ((dimensionDependency.value * this.f2003a.mMatchConstraintPercentWidth) + 0.5f);
                        this.f2006d.resolve(i2);
                    }
                }
            } else if (i4 == 3) {
                int i5 = constraintWidget4.mMatchConstraintDefaultHeight;
                if (i5 == 0 || i5 == 3) {
                    VerticalWidgetRun verticalWidgetRun = constraintWidget4.verticalRun;
                    DependencyNode dependencyNode = verticalWidgetRun.start;
                    DependencyNode dependencyNode2 = verticalWidgetRun.end;
                    boolean z = constraintWidget4.mLeft.mTarget != null;
                    boolean z2 = constraintWidget4.mTop.mTarget != null;
                    boolean z3 = constraintWidget4.mRight.mTarget != null;
                    boolean z4 = constraintWidget4.mBottom.mTarget != null;
                    int dimensionRatioSide = constraintWidget4.getDimensionRatioSide();
                    if (z && z2 && z3 && z4) {
                        float dimensionRatio2 = this.f2003a.getDimensionRatio();
                        if (dependencyNode.resolved && dependencyNode2.resolved) {
                            DependencyNode dependencyNode3 = this.start;
                            if (dependencyNode3.readyToSolve && this.end.readyToSolve) {
                                computeInsetRatio(tempDimensions, ((DependencyNode) dependencyNode3.f1983g.get(0)).value + this.start.f1979c, ((DependencyNode) this.end.f1983g.get(0)).value - this.end.f1979c, dependencyNode.value + dependencyNode.f1979c, dependencyNode2.value - dependencyNode2.f1979c, dimensionRatio2, dimensionRatioSide);
                                this.f2006d.resolve(tempDimensions[0]);
                                this.f2003a.verticalRun.f2006d.resolve(tempDimensions[1]);
                                return;
                            }
                            return;
                        }
                        DependencyNode dependencyNode4 = this.start;
                        if (dependencyNode4.resolved) {
                            DependencyNode dependencyNode5 = this.end;
                            if (dependencyNode5.resolved) {
                                if (!dependencyNode.readyToSolve || !dependencyNode2.readyToSolve) {
                                    return;
                                }
                                computeInsetRatio(tempDimensions, dependencyNode4.value + dependencyNode4.f1979c, dependencyNode5.value - dependencyNode5.f1979c, ((DependencyNode) dependencyNode.f1983g.get(0)).value + dependencyNode.f1979c, ((DependencyNode) dependencyNode2.f1983g.get(0)).value - dependencyNode2.f1979c, dimensionRatio2, dimensionRatioSide);
                                this.f2006d.resolve(tempDimensions[0]);
                                this.f2003a.verticalRun.f2006d.resolve(tempDimensions[1]);
                            }
                        }
                        DependencyNode dependencyNode6 = this.start;
                        if (!dependencyNode6.readyToSolve || !this.end.readyToSolve || !dependencyNode.readyToSolve || !dependencyNode2.readyToSolve) {
                            return;
                        }
                        computeInsetRatio(tempDimensions, ((DependencyNode) dependencyNode6.f1983g.get(0)).value + this.start.f1979c, ((DependencyNode) this.end.f1983g.get(0)).value - this.end.f1979c, ((DependencyNode) dependencyNode.f1983g.get(0)).value + dependencyNode.f1979c, ((DependencyNode) dependencyNode2.f1983g.get(0)).value - dependencyNode2.f1979c, dimensionRatio2, dimensionRatioSide);
                        this.f2006d.resolve(tempDimensions[0]);
                        dimensionDependency2 = this.f2003a.verticalRun.f2006d;
                        e2 = tempDimensions[1];
                    } else if (z && z3) {
                        if (!this.start.readyToSolve || !this.end.readyToSolve) {
                            return;
                        }
                        float dimensionRatio3 = this.f2003a.getDimensionRatio();
                        int i6 = ((DependencyNode) this.start.f1983g.get(0)).value + this.start.f1979c;
                        int i7 = ((DependencyNode) this.end.f1983g.get(0)).value - this.end.f1979c;
                        if (dimensionRatioSide == -1 || dimensionRatioSide == 0) {
                            e4 = e(i7 - i6, 0);
                            int i8 = (int) ((e4 * dimensionRatio3) + 0.5f);
                            e5 = e(i8, 1);
                            if (i8 != e5) {
                                f3 = e5 / dimensionRatio3;
                                e4 = (int) (f3 + 0.5f);
                            }
                            this.f2006d.resolve(e4);
                            this.f2003a.verticalRun.f2006d.resolve(e5);
                        } else if (dimensionRatioSide == 1) {
                            e4 = e(i7 - i6, 0);
                            int i9 = (int) ((e4 / dimensionRatio3) + 0.5f);
                            e5 = e(i9, 1);
                            if (i9 != e5) {
                                f3 = e5 * dimensionRatio3;
                                e4 = (int) (f3 + 0.5f);
                            }
                            this.f2006d.resolve(e4);
                            this.f2003a.verticalRun.f2006d.resolve(e5);
                        }
                    } else if (z2 && z4) {
                        if (!dependencyNode.readyToSolve || !dependencyNode2.readyToSolve) {
                            return;
                        }
                        float dimensionRatio4 = this.f2003a.getDimensionRatio();
                        int i10 = ((DependencyNode) dependencyNode.f1983g.get(0)).value + dependencyNode.f1979c;
                        int i11 = ((DependencyNode) dependencyNode2.f1983g.get(0)).value - dependencyNode2.f1979c;
                        if (dimensionRatioSide != -1) {
                            if (dimensionRatioSide == 0) {
                                e2 = e(i11 - i10, 1);
                                int i12 = (int) ((e2 * dimensionRatio4) + 0.5f);
                                e3 = e(i12, 0);
                                if (i12 != e3) {
                                    f2 = e3 / dimensionRatio4;
                                    e2 = (int) (f2 + 0.5f);
                                }
                                this.f2006d.resolve(e3);
                                dimensionDependency2 = this.f2003a.verticalRun.f2006d;
                            }
                        }
                        e2 = e(i11 - i10, 1);
                        int i13 = (int) ((e2 / dimensionRatio4) + 0.5f);
                        e3 = e(i13, 0);
                        if (i13 != e3) {
                            f2 = e3 * dimensionRatio4;
                            e2 = (int) (f2 + 0.5f);
                        }
                        this.f2006d.resolve(e3);
                        dimensionDependency2 = this.f2003a.verticalRun.f2006d;
                    }
                    dimensionDependency2.resolve(e2);
                } else {
                    int dimensionRatioSide2 = constraintWidget4.getDimensionRatioSide();
                    if (dimensionRatioSide2 != -1) {
                        if (dimensionRatioSide2 == 0) {
                            dimensionRatio = constraintWidget2.verticalRun.f2006d.value / this.f2003a.getDimensionRatio();
                            i2 = (int) (dimensionRatio + 0.5f);
                            this.f2006d.resolve(i2);
                        } else if (dimensionRatioSide2 != 1) {
                            i2 = 0;
                            this.f2006d.resolve(i2);
                        }
                    }
                    dimensionRatio = constraintWidget.verticalRun.f2006d.value * this.f2003a.getDimensionRatio();
                    i2 = (int) (dimensionRatio + 0.5f);
                    this.f2006d.resolve(i2);
                }
            }
        }
        DependencyNode dependencyNode7 = this.start;
        if (dependencyNode7.readyToSolve) {
            DependencyNode dependencyNode8 = this.end;
            if (dependencyNode8.readyToSolve) {
                if (dependencyNode7.resolved && dependencyNode8.resolved && this.f2006d.resolved) {
                    return;
                }
                if (!this.f2006d.resolved && this.f2005c == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    ConstraintWidget constraintWidget5 = this.f2003a;
                    if (constraintWidget5.mMatchConstraintDefaultWidth == 0 && !constraintWidget5.isInHorizontalChain()) {
                        int i14 = ((DependencyNode) this.start.f1983g.get(0)).value;
                        DependencyNode dependencyNode9 = this.start;
                        int i15 = i14 + dependencyNode9.f1979c;
                        int i16 = ((DependencyNode) this.end.f1983g.get(0)).value + this.end.f1979c;
                        dependencyNode9.resolve(i15);
                        this.end.resolve(i16);
                        this.f2006d.resolve(i16 - i15);
                        return;
                    }
                }
                if (!this.f2006d.resolved && this.f2005c == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && this.matchConstraintsType == 1 && this.start.f1983g.size() > 0 && this.end.f1983g.size() > 0) {
                    int min = Math.min((((DependencyNode) this.end.f1983g.get(0)).value + this.end.f1979c) - (((DependencyNode) this.start.f1983g.get(0)).value + this.start.f1979c), this.f2006d.wrapValue);
                    ConstraintWidget constraintWidget6 = this.f2003a;
                    int i17 = constraintWidget6.mMatchConstraintMaxWidth;
                    int max = Math.max(constraintWidget6.mMatchConstraintMinWidth, min);
                    if (i17 > 0) {
                        max = Math.min(i17, max);
                    }
                    this.f2006d.resolve(max);
                }
                if (this.f2006d.resolved) {
                    DependencyNode dependencyNode10 = (DependencyNode) this.start.f1983g.get(0);
                    DependencyNode dependencyNode11 = (DependencyNode) this.end.f1983g.get(0);
                    int i18 = dependencyNode10.value + this.start.f1979c;
                    int i19 = dependencyNode11.value + this.end.f1979c;
                    float horizontalBiasPercent = this.f2003a.getHorizontalBiasPercent();
                    if (dependencyNode10 == dependencyNode11) {
                        i18 = dependencyNode10.value;
                        i19 = dependencyNode11.value;
                        horizontalBiasPercent = 0.5f;
                    }
                    this.start.resolve((int) (i18 + 0.5f + (((i19 - i18) - this.f2006d.value) * horizontalBiasPercent)));
                    this.end.resolve(this.start.value + this.f2006d.value);
                }
            }
        }
    }
}
