package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
/* loaded from: classes.dex */
public abstract class WidgetRun implements Dependency {

    /* renamed from: a  reason: collision with root package name */
    ConstraintWidget f2003a;

    /* renamed from: b  reason: collision with root package name */
    RunGroup f2004b;

    /* renamed from: c  reason: collision with root package name */
    protected ConstraintWidget.DimensionBehaviour f2005c;
    public int matchConstraintsType;

    /* renamed from: d  reason: collision with root package name */
    DimensionDependency f2006d = new DimensionDependency(this);
    public int orientation = 0;

    /* renamed from: e  reason: collision with root package name */
    boolean f2007e = false;
    public DependencyNode start = new DependencyNode(this);
    public DependencyNode end = new DependencyNode(this);

    /* renamed from: f  reason: collision with root package name */
    protected RunType f2008f = RunType.NONE;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: androidx.constraintlayout.core.widgets.analyzer.WidgetRun$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f2009a;

        static {
            int[] iArr = new int[ConstraintAnchor.Type.values().length];
            f2009a = iArr;
            try {
                iArr[ConstraintAnchor.Type.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2009a[ConstraintAnchor.Type.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2009a[ConstraintAnchor.Type.TOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2009a[ConstraintAnchor.Type.BASELINE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f2009a[ConstraintAnchor.Type.BOTTOM.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* loaded from: classes.dex */
    enum RunType {
        NONE,
        START,
        END,
        CENTER
    }

    public WidgetRun(ConstraintWidget constraintWidget) {
        this.f2003a = constraintWidget;
    }

    private void resolveDimension(int i2, int i3) {
        DimensionDependency dimensionDependency;
        int e2;
        int i4 = this.matchConstraintsType;
        if (i4 != 0) {
            if (i4 == 1) {
                int e3 = e(this.f2006d.wrapValue, i2);
                dimensionDependency = this.f2006d;
                e2 = Math.min(e3, i3);
                dimensionDependency.resolve(e2);
            } else if (i4 != 2) {
                if (i4 != 3) {
                    return;
                }
                ConstraintWidget constraintWidget = this.f2003a;
                WidgetRun widgetRun = constraintWidget.horizontalRun;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = widgetRun.f2005c;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (dimensionBehaviour == dimensionBehaviour2 && widgetRun.matchConstraintsType == 3) {
                    VerticalWidgetRun verticalWidgetRun = constraintWidget.verticalRun;
                    if (verticalWidgetRun.f2005c == dimensionBehaviour2 && verticalWidgetRun.matchConstraintsType == 3) {
                        return;
                    }
                }
                if (i2 == 0) {
                    widgetRun = constraintWidget.verticalRun;
                }
                if (widgetRun.f2006d.resolved) {
                    float dimensionRatio = constraintWidget.getDimensionRatio();
                    this.f2006d.resolve(i2 == 1 ? (int) ((widgetRun.f2006d.value / dimensionRatio) + 0.5f) : (int) ((dimensionRatio * widgetRun.f2006d.value) + 0.5f));
                    return;
                }
                return;
            } else {
                ConstraintWidget parent = this.f2003a.getParent();
                if (parent == null) {
                    return;
                }
                DimensionDependency dimensionDependency2 = (i2 == 0 ? parent.horizontalRun : parent.verticalRun).f2006d;
                if (!dimensionDependency2.resolved) {
                    return;
                }
                ConstraintWidget constraintWidget2 = this.f2003a;
                i3 = (int) ((dimensionDependency2.value * (i2 == 0 ? constraintWidget2.mMatchConstraintPercentWidth : constraintWidget2.mMatchConstraintPercentHeight)) + 0.5f);
            }
        }
        dimensionDependency = this.f2006d;
        e2 = e(i3, i2);
        dimensionDependency.resolve(e2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(DependencyNode dependencyNode, DependencyNode dependencyNode2, int i2) {
        dependencyNode.f1983g.add(dependencyNode2);
        dependencyNode.f1979c = i2;
        dependencyNode2.f1982f.add(dependencyNode);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void applyToWidget();

    /* JADX INFO: Access modifiers changed from: protected */
    public final void b(DependencyNode dependencyNode, DependencyNode dependencyNode2, int i2, DimensionDependency dimensionDependency) {
        dependencyNode.f1983g.add(dependencyNode2);
        dependencyNode.f1983g.add(this.f2006d);
        dependencyNode.f1980d = i2;
        dependencyNode.f1981e = dimensionDependency;
        dependencyNode2.f1982f.add(dependencyNode);
        dimensionDependency.f1982f.add(dependencyNode);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void c();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void d();

    /* JADX INFO: Access modifiers changed from: protected */
    public final int e(int i2, int i3) {
        int max;
        if (i3 == 0) {
            ConstraintWidget constraintWidget = this.f2003a;
            int i4 = constraintWidget.mMatchConstraintMaxWidth;
            max = Math.max(constraintWidget.mMatchConstraintMinWidth, i2);
            if (i4 > 0) {
                max = Math.min(i4, i2);
            }
            if (max == i2) {
                return i2;
            }
        } else {
            ConstraintWidget constraintWidget2 = this.f2003a;
            int i5 = constraintWidget2.mMatchConstraintMaxHeight;
            max = Math.max(constraintWidget2.mMatchConstraintMinHeight, i2);
            if (i5 > 0) {
                max = Math.min(i5, i2);
            }
            if (max == i2) {
                return i2;
            }
        }
        return max;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final DependencyNode f(ConstraintAnchor constraintAnchor) {
        WidgetRun widgetRun;
        WidgetRun widgetRun2;
        ConstraintAnchor constraintAnchor2 = constraintAnchor.mTarget;
        if (constraintAnchor2 == null) {
            return null;
        }
        ConstraintWidget constraintWidget = constraintAnchor2.mOwner;
        int i2 = AnonymousClass1.f2009a[constraintAnchor2.mType.ordinal()];
        if (i2 != 1) {
            if (i2 == 2) {
                widgetRun2 = constraintWidget.horizontalRun;
            } else if (i2 == 3) {
                widgetRun = constraintWidget.verticalRun;
            } else if (i2 == 4) {
                return constraintWidget.verticalRun.baseline;
            } else {
                if (i2 != 5) {
                    return null;
                }
                widgetRun2 = constraintWidget.verticalRun;
            }
            return widgetRun2.end;
        }
        widgetRun = constraintWidget.horizontalRun;
        return widgetRun.start;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final DependencyNode g(ConstraintAnchor constraintAnchor, int i2) {
        ConstraintAnchor constraintAnchor2 = constraintAnchor.mTarget;
        if (constraintAnchor2 == null) {
            return null;
        }
        ConstraintWidget constraintWidget = constraintAnchor2.mOwner;
        WidgetRun widgetRun = i2 == 0 ? constraintWidget.horizontalRun : constraintWidget.verticalRun;
        int i3 = AnonymousClass1.f2009a[constraintAnchor2.mType.ordinal()];
        if (i3 != 1) {
            if (i3 != 2) {
                if (i3 != 3) {
                    if (i3 != 5) {
                        return null;
                    }
                }
            }
            return widgetRun.end;
        }
        return widgetRun.start;
    }

    public long getWrapDimension() {
        DimensionDependency dimensionDependency = this.f2006d;
        if (dimensionDependency.resolved) {
            return dimensionDependency.value;
        }
        return 0L;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean h();

    /* JADX INFO: Access modifiers changed from: protected */
    public void i(Dependency dependency, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i2) {
        DependencyNode dependencyNode;
        DependencyNode f2 = f(constraintAnchor);
        DependencyNode f3 = f(constraintAnchor2);
        if (f2.resolved && f3.resolved) {
            int margin = f2.value + constraintAnchor.getMargin();
            int margin2 = f3.value - constraintAnchor2.getMargin();
            int i3 = margin2 - margin;
            if (!this.f2006d.resolved && this.f2005c == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                resolveDimension(i2, i3);
            }
            DimensionDependency dimensionDependency = this.f2006d;
            if (dimensionDependency.resolved) {
                if (dimensionDependency.value == i3) {
                    this.start.resolve(margin);
                    dependencyNode = this.end;
                } else {
                    ConstraintWidget constraintWidget = this.f2003a;
                    float horizontalBiasPercent = i2 == 0 ? constraintWidget.getHorizontalBiasPercent() : constraintWidget.getVerticalBiasPercent();
                    if (f2 == f3) {
                        margin = f2.value;
                        margin2 = f3.value;
                        horizontalBiasPercent = 0.5f;
                    }
                    this.start.resolve((int) (margin + 0.5f + (((margin2 - margin) - this.f2006d.value) * horizontalBiasPercent)));
                    dependencyNode = this.end;
                    margin2 = this.start.value + this.f2006d.value;
                }
                dependencyNode.resolve(margin2);
            }
        }
    }

    public boolean isCenterConnection() {
        int size = this.start.f1983g.size();
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            if (((DependencyNode) this.start.f1983g.get(i3)).f1977a != this) {
                i2++;
            }
        }
        int size2 = this.end.f1983g.size();
        for (int i4 = 0; i4 < size2; i4++) {
            if (((DependencyNode) this.end.f1983g.get(i4)).f1977a != this) {
                i2++;
            }
        }
        return i2 >= 2;
    }

    public boolean isDimensionResolved() {
        return this.f2006d.resolved;
    }

    public boolean isResolved() {
        return this.f2007e;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void j(Dependency dependency) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void k(Dependency dependency) {
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.Dependency
    public void update(Dependency dependency) {
    }

    public long wrapSize(int i2) {
        int i3;
        DimensionDependency dimensionDependency = this.f2006d;
        if (dimensionDependency.resolved) {
            long j2 = dimensionDependency.value;
            if (isCenterConnection()) {
                i3 = this.start.f1979c - this.end.f1979c;
            } else if (i2 != 0) {
                return j2 - this.end.f1979c;
            } else {
                i3 = this.start.f1979c;
            }
            return j2 + i3;
        }
        return 0L;
    }
}
