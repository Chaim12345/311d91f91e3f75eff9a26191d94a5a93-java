package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.Helper;
import androidx.constraintlayout.core.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.core.widgets.analyzer.WidgetRun;
/* loaded from: classes.dex */
public class VerticalWidgetRun extends WidgetRun {
    public DependencyNode baseline;

    /* renamed from: g  reason: collision with root package name */
    DimensionDependency f1988g;

    /* renamed from: androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f1989a;

        static {
            int[] iArr = new int[WidgetRun.RunType.values().length];
            f1989a = iArr;
            try {
                iArr[WidgetRun.RunType.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1989a[WidgetRun.RunType.END.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1989a[WidgetRun.RunType.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public VerticalWidgetRun(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        DependencyNode dependencyNode = new DependencyNode(this);
        this.baseline = dependencyNode;
        this.f1988g = null;
        this.start.f1978b = DependencyNode.Type.TOP;
        this.end.f1978b = DependencyNode.Type.BOTTOM;
        dependencyNode.f1978b = DependencyNode.Type.BASELINE;
        this.orientation = 1;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void applyToWidget() {
        DependencyNode dependencyNode = this.start;
        if (dependencyNode.resolved) {
            this.f2003a.setY(dependencyNode.value);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:115:0x02e7, code lost:
        if (r9.f2003a.hasBaseline() != false) goto L102;
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x02e9, code lost:
        r0 = r9.baseline;
        r1 = r9.start;
        r2 = r9.f1988g;
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x0342, code lost:
        if (r0.f2005c == r1) goto L128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x0374, code lost:
        if (r9.f2003a.hasBaseline() != false) goto L102;
     */
    /* JADX WARN: Code restructure failed: missing block: B:156:0x03eb, code lost:
        if (r0.f2005c == r1) goto L128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x03ed, code lost:
        r0.f2006d.f1982f.add(r9.f2006d);
        r9.f2006d.f1983g.add(r9.f2003a.horizontalRun.f2006d);
        r9.f2006d.updateDelegate = r9;
     */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0411  */
    /* JADX WARN: Removed duplicated region for block: B:172:? A[RETURN, SYNTHETIC] */
    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    void c() {
        ConstraintWidget parent;
        HorizontalWidgetRun horizontalWidgetRun;
        DependencyNode dependencyNode;
        DependencyNode dependencyNode2;
        DimensionDependency dimensionDependency;
        WidgetRun widgetRun;
        DependencyNode dependencyNode3;
        DependencyNode dependencyNode4;
        int i2;
        ConstraintWidget parent2;
        ConstraintWidget constraintWidget = this.f2003a;
        if (constraintWidget.measured) {
            this.f2006d.resolve(constraintWidget.getHeight());
        }
        if (!this.f2006d.resolved) {
            this.f2005c = this.f2003a.getVerticalDimensionBehaviour();
            if (this.f2003a.hasBaseline()) {
                this.f1988g = new BaselineDimensionDependency(this);
            }
            ConstraintWidget.DimensionBehaviour dimensionBehaviour = this.f2005c;
            if (dimensionBehaviour != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_PARENT && (parent2 = this.f2003a.getParent()) != null && parent2.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.FIXED) {
                    int height = (parent2.getHeight() - this.f2003a.mTop.getMargin()) - this.f2003a.mBottom.getMargin();
                    a(this.start, parent2.verticalRun.start, this.f2003a.mTop.getMargin());
                    a(this.end, parent2.verticalRun.end, -this.f2003a.mBottom.getMargin());
                    this.f2006d.resolve(height);
                    return;
                } else if (this.f2005c == ConstraintWidget.DimensionBehaviour.FIXED) {
                    this.f2006d.resolve(this.f2003a.getHeight());
                }
            }
        } else if (this.f2005c == ConstraintWidget.DimensionBehaviour.MATCH_PARENT && (parent = this.f2003a.getParent()) != null && parent.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.FIXED) {
            a(this.start, parent.verticalRun.start, this.f2003a.mTop.getMargin());
            a(this.end, parent.verticalRun.end, -this.f2003a.mBottom.getMargin());
            return;
        }
        DimensionDependency dimensionDependency2 = this.f2006d;
        boolean z = dimensionDependency2.resolved;
        if (z) {
            ConstraintWidget constraintWidget2 = this.f2003a;
            if (constraintWidget2.measured) {
                ConstraintAnchor[] constraintAnchorArr = constraintWidget2.mListAnchors;
                if (constraintAnchorArr[2].mTarget != null && constraintAnchorArr[3].mTarget != null) {
                    if (constraintWidget2.isInVerticalChain()) {
                        this.start.f1979c = this.f2003a.mListAnchors[2].getMargin();
                        this.end.f1979c = -this.f2003a.mListAnchors[3].getMargin();
                    } else {
                        DependencyNode f2 = f(this.f2003a.mListAnchors[2]);
                        if (f2 != null) {
                            a(this.start, f2, this.f2003a.mListAnchors[2].getMargin());
                        }
                        DependencyNode f3 = f(this.f2003a.mListAnchors[3]);
                        if (f3 != null) {
                            a(this.end, f3, -this.f2003a.mListAnchors[3].getMargin());
                        }
                        this.start.delegateToWidgetRun = true;
                        this.end.delegateToWidgetRun = true;
                    }
                    if (!this.f2003a.hasBaseline()) {
                        return;
                    }
                } else if (constraintAnchorArr[2].mTarget != null) {
                    DependencyNode f4 = f(constraintAnchorArr[2]);
                    if (f4 == null) {
                        return;
                    }
                    a(this.start, f4, this.f2003a.mListAnchors[2].getMargin());
                    a(this.end, this.start, this.f2006d.value);
                    if (!this.f2003a.hasBaseline()) {
                        return;
                    }
                } else if (constraintAnchorArr[3].mTarget != null) {
                    DependencyNode f5 = f(constraintAnchorArr[3]);
                    if (f5 != null) {
                        a(this.end, f5, -this.f2003a.mListAnchors[3].getMargin());
                        a(this.start, this.end, -this.f2006d.value);
                    }
                    if (!this.f2003a.hasBaseline()) {
                        return;
                    }
                } else if (constraintAnchorArr[4].mTarget != null) {
                    DependencyNode f6 = f(constraintAnchorArr[4]);
                    if (f6 != null) {
                        a(this.baseline, f6, 0);
                        a(this.start, this.baseline, -this.f2003a.getBaselineDistance());
                        dependencyNode3 = this.end;
                        dependencyNode4 = this.start;
                        i2 = this.f2006d.value;
                        a(dependencyNode3, dependencyNode4, i2);
                        return;
                    }
                    return;
                } else if ((constraintWidget2 instanceof Helper) || constraintWidget2.getParent() == null || this.f2003a.getAnchor(ConstraintAnchor.Type.CENTER).mTarget != null) {
                    return;
                } else {
                    a(this.start, this.f2003a.getParent().verticalRun.start, this.f2003a.getY());
                    a(this.end, this.start, this.f2006d.value);
                    if (!this.f2003a.hasBaseline()) {
                        return;
                    }
                }
                dependencyNode3 = this.baseline;
                dependencyNode4 = this.start;
                i2 = this.f2003a.getBaselineDistance();
                a(dependencyNode3, dependencyNode4, i2);
                return;
            }
        }
        if (z || this.f2005c != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            dimensionDependency2.addDependency(this);
        } else {
            ConstraintWidget constraintWidget3 = this.f2003a;
            int i3 = constraintWidget3.mMatchConstraintDefaultHeight;
            if (i3 == 2) {
                ConstraintWidget parent3 = constraintWidget3.getParent();
                if (parent3 != null) {
                    widgetRun = parent3.verticalRun;
                    DimensionDependency dimensionDependency3 = widgetRun.f2006d;
                    this.f2006d.f1983g.add(dimensionDependency3);
                    dimensionDependency3.f1982f.add(this.f2006d);
                    DimensionDependency dimensionDependency4 = this.f2006d;
                    dimensionDependency4.delegateToWidgetRun = true;
                    dimensionDependency4.f1982f.add(this.start);
                    this.f2006d.f1982f.add(this.end);
                }
            } else if (i3 == 3 && !constraintWidget3.isInVerticalChain()) {
                ConstraintWidget constraintWidget4 = this.f2003a;
                if (constraintWidget4.mMatchConstraintDefaultWidth != 3) {
                    widgetRun = constraintWidget4.horizontalRun;
                    DimensionDependency dimensionDependency32 = widgetRun.f2006d;
                    this.f2006d.f1983g.add(dimensionDependency32);
                    dimensionDependency32.f1982f.add(this.f2006d);
                    DimensionDependency dimensionDependency42 = this.f2006d;
                    dimensionDependency42.delegateToWidgetRun = true;
                    dimensionDependency42.f1982f.add(this.start);
                    this.f2006d.f1982f.add(this.end);
                }
            }
        }
        ConstraintWidget constraintWidget5 = this.f2003a;
        ConstraintAnchor[] constraintAnchorArr2 = constraintWidget5.mListAnchors;
        if (constraintAnchorArr2[2].mTarget == null || constraintAnchorArr2[3].mTarget == null) {
            if (constraintAnchorArr2[2].mTarget != null) {
                DependencyNode f7 = f(constraintAnchorArr2[2]);
                if (f7 != null) {
                    a(this.start, f7, this.f2003a.mListAnchors[2].getMargin());
                    b(this.end, this.start, 1, this.f2006d);
                    if (this.f2003a.hasBaseline()) {
                        b(this.baseline, this.start, 1, this.f1988g);
                    }
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = this.f2005c;
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                    if (dimensionBehaviour2 == dimensionBehaviour3 && this.f2003a.getDimensionRatio() > 0.0f) {
                        horizontalWidgetRun = this.f2003a.horizontalRun;
                    }
                }
            } else if (constraintAnchorArr2[3].mTarget != null) {
                DependencyNode f8 = f(constraintAnchorArr2[3]);
                if (f8 != null) {
                    a(this.end, f8, -this.f2003a.mListAnchors[3].getMargin());
                    b(this.start, this.end, -1, this.f2006d);
                }
            } else if (constraintAnchorArr2[4].mTarget != null) {
                DependencyNode f9 = f(constraintAnchorArr2[4]);
                if (f9 != null) {
                    a(this.baseline, f9, 0);
                    b(this.start, this.baseline, -1, this.f1988g);
                    dependencyNode = this.end;
                    dependencyNode2 = this.start;
                    dimensionDependency = this.f2006d;
                }
            } else if (!(constraintWidget5 instanceof Helper) && constraintWidget5.getParent() != null) {
                a(this.start, this.f2003a.getParent().verticalRun.start, this.f2003a.getY());
                b(this.end, this.start, 1, this.f2006d);
                if (this.f2003a.hasBaseline()) {
                    b(this.baseline, this.start, 1, this.f1988g);
                }
                ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = this.f2005c;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour5 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (dimensionBehaviour4 == dimensionBehaviour5 && this.f2003a.getDimensionRatio() > 0.0f) {
                    horizontalWidgetRun = this.f2003a.horizontalRun;
                }
            }
            if (this.f2006d.f1983g.size() != 0) {
                this.f2006d.readyToSolve = true;
                return;
            }
            return;
        } else if (constraintWidget5.isInVerticalChain()) {
            this.start.f1979c = this.f2003a.mListAnchors[2].getMargin();
            this.end.f1979c = -this.f2003a.mListAnchors[3].getMargin();
        } else {
            DependencyNode f10 = f(this.f2003a.mListAnchors[2]);
            DependencyNode f11 = f(this.f2003a.mListAnchors[3]);
            if (f10 != null) {
                f10.addDependency(this);
            }
            if (f11 != null) {
                f11.addDependency(this);
            }
            this.f2008f = WidgetRun.RunType.CENTER;
        }
        b(dependencyNode, dependencyNode2, 1, dimensionDependency);
        if (this.f2006d.f1983g.size() != 0) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void d() {
        this.f2004b = null;
        this.start.clear();
        this.end.clear();
        this.baseline.clear();
        this.f2006d.clear();
        this.f2007e = false;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    boolean h() {
        return this.f2005c != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || this.f2003a.mMatchConstraintDefaultHeight == 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void l() {
        this.f2007e = false;
        this.start.clear();
        this.start.resolved = false;
        this.end.clear();
        this.end.resolved = false;
        this.baseline.clear();
        this.baseline.resolved = false;
        this.f2006d.resolved = false;
    }

    public String toString() {
        return "VerticalRun " + this.f2003a.getDebugName();
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun, androidx.constraintlayout.core.widgets.analyzer.Dependency
    public void update(Dependency dependency) {
        DimensionDependency dimensionDependency;
        int i2;
        ConstraintWidget constraintWidget;
        float dimensionRatio;
        ConstraintWidget constraintWidget2;
        int i3 = AnonymousClass1.f1989a[this.f2008f.ordinal()];
        if (i3 == 1) {
            k(dependency);
        } else if (i3 == 2) {
            j(dependency);
        } else if (i3 == 3) {
            ConstraintWidget constraintWidget3 = this.f2003a;
            i(dependency, constraintWidget3.mTop, constraintWidget3.mBottom, 1);
            return;
        }
        DimensionDependency dimensionDependency2 = this.f2006d;
        if (dimensionDependency2.readyToSolve && !dimensionDependency2.resolved && this.f2005c == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            ConstraintWidget constraintWidget4 = this.f2003a;
            int i4 = constraintWidget4.mMatchConstraintDefaultHeight;
            if (i4 == 2) {
                ConstraintWidget parent = constraintWidget4.getParent();
                if (parent != null) {
                    if (parent.verticalRun.f2006d.resolved) {
                        i2 = (int) ((dimensionDependency.value * this.f2003a.mMatchConstraintPercentHeight) + 0.5f);
                        this.f2006d.resolve(i2);
                    }
                }
            } else if (i4 == 3 && constraintWidget4.horizontalRun.f2006d.resolved) {
                int dimensionRatioSide = constraintWidget4.getDimensionRatioSide();
                if (dimensionRatioSide != -1) {
                    if (dimensionRatioSide == 0) {
                        dimensionRatio = constraintWidget2.horizontalRun.f2006d.value * this.f2003a.getDimensionRatio();
                        i2 = (int) (dimensionRatio + 0.5f);
                        this.f2006d.resolve(i2);
                    } else if (dimensionRatioSide != 1) {
                        i2 = 0;
                        this.f2006d.resolve(i2);
                    }
                }
                dimensionRatio = constraintWidget.horizontalRun.f2006d.value / this.f2003a.getDimensionRatio();
                i2 = (int) (dimensionRatio + 0.5f);
                this.f2006d.resolve(i2);
            }
        }
        DependencyNode dependencyNode = this.start;
        if (dependencyNode.readyToSolve) {
            DependencyNode dependencyNode2 = this.end;
            if (dependencyNode2.readyToSolve) {
                if (dependencyNode.resolved && dependencyNode2.resolved && this.f2006d.resolved) {
                    return;
                }
                if (!this.f2006d.resolved && this.f2005c == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    ConstraintWidget constraintWidget5 = this.f2003a;
                    if (constraintWidget5.mMatchConstraintDefaultWidth == 0 && !constraintWidget5.isInVerticalChain()) {
                        int i5 = ((DependencyNode) this.start.f1983g.get(0)).value;
                        DependencyNode dependencyNode3 = this.start;
                        int i6 = i5 + dependencyNode3.f1979c;
                        int i7 = ((DependencyNode) this.end.f1983g.get(0)).value + this.end.f1979c;
                        dependencyNode3.resolve(i6);
                        this.end.resolve(i7);
                        this.f2006d.resolve(i7 - i6);
                        return;
                    }
                }
                if (!this.f2006d.resolved && this.f2005c == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && this.matchConstraintsType == 1 && this.start.f1983g.size() > 0 && this.end.f1983g.size() > 0) {
                    int i8 = (((DependencyNode) this.end.f1983g.get(0)).value + this.end.f1979c) - (((DependencyNode) this.start.f1983g.get(0)).value + this.start.f1979c);
                    DimensionDependency dimensionDependency3 = this.f2006d;
                    int i9 = dimensionDependency3.wrapValue;
                    if (i8 < i9) {
                        dimensionDependency3.resolve(i8);
                    } else {
                        dimensionDependency3.resolve(i9);
                    }
                }
                if (this.f2006d.resolved && this.start.f1983g.size() > 0 && this.end.f1983g.size() > 0) {
                    DependencyNode dependencyNode4 = (DependencyNode) this.start.f1983g.get(0);
                    DependencyNode dependencyNode5 = (DependencyNode) this.end.f1983g.get(0);
                    int i10 = dependencyNode4.value + this.start.f1979c;
                    int i11 = dependencyNode5.value + this.end.f1979c;
                    float verticalBiasPercent = this.f2003a.getVerticalBiasPercent();
                    if (dependencyNode4 == dependencyNode5) {
                        i10 = dependencyNode4.value;
                        i11 = dependencyNode5.value;
                        verticalBiasPercent = 0.5f;
                    }
                    this.start.resolve((int) (i10 + 0.5f + (((i11 - i10) - this.f2006d.value) * verticalBiasPercent)));
                    this.end.resolve(this.start.value + this.f2006d.value);
                }
            }
        }
    }
}
