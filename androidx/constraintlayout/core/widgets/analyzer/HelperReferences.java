package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.Barrier;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.analyzer.DependencyNode;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class HelperReferences extends WidgetRun {
    public HelperReferences(ConstraintWidget constraintWidget) {
        super(constraintWidget);
    }

    private void addDependency(DependencyNode dependencyNode) {
        this.start.f1982f.add(dependencyNode);
        dependencyNode.f1983g.add(this.start);
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void applyToWidget() {
        ConstraintWidget constraintWidget = this.f2003a;
        if (constraintWidget instanceof Barrier) {
            int barrierType = ((Barrier) constraintWidget).getBarrierType();
            if (barrierType == 0 || barrierType == 1) {
                this.f2003a.setX(this.start.value);
            } else {
                this.f2003a.setY(this.start.value);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void c() {
        WidgetRun widgetRun;
        ConstraintWidget constraintWidget = this.f2003a;
        if (constraintWidget instanceof Barrier) {
            this.start.delegateToWidgetRun = true;
            Barrier barrier = (Barrier) constraintWidget;
            int barrierType = barrier.getBarrierType();
            boolean allowsGoneWidget = barrier.getAllowsGoneWidget();
            int i2 = 0;
            if (barrierType == 0) {
                this.start.f1978b = DependencyNode.Type.LEFT;
                while (i2 < barrier.mWidgetsCount) {
                    ConstraintWidget constraintWidget2 = barrier.mWidgets[i2];
                    if (allowsGoneWidget || constraintWidget2.getVisibility() != 8) {
                        DependencyNode dependencyNode = constraintWidget2.horizontalRun.start;
                        dependencyNode.f1982f.add(this.start);
                        this.start.f1983g.add(dependencyNode);
                    }
                    i2++;
                }
            } else if (barrierType != 1) {
                if (barrierType == 2) {
                    this.start.f1978b = DependencyNode.Type.TOP;
                    while (i2 < barrier.mWidgetsCount) {
                        ConstraintWidget constraintWidget3 = barrier.mWidgets[i2];
                        if (allowsGoneWidget || constraintWidget3.getVisibility() != 8) {
                            DependencyNode dependencyNode2 = constraintWidget3.verticalRun.start;
                            dependencyNode2.f1982f.add(this.start);
                            this.start.f1983g.add(dependencyNode2);
                        }
                        i2++;
                    }
                } else if (barrierType != 3) {
                    return;
                } else {
                    this.start.f1978b = DependencyNode.Type.BOTTOM;
                    while (i2 < barrier.mWidgetsCount) {
                        ConstraintWidget constraintWidget4 = barrier.mWidgets[i2];
                        if (allowsGoneWidget || constraintWidget4.getVisibility() != 8) {
                            DependencyNode dependencyNode3 = constraintWidget4.verticalRun.end;
                            dependencyNode3.f1982f.add(this.start);
                            this.start.f1983g.add(dependencyNode3);
                        }
                        i2++;
                    }
                }
                addDependency(this.f2003a.verticalRun.start);
                widgetRun = this.f2003a.verticalRun;
                addDependency(widgetRun.end);
            } else {
                this.start.f1978b = DependencyNode.Type.RIGHT;
                while (i2 < barrier.mWidgetsCount) {
                    ConstraintWidget constraintWidget5 = barrier.mWidgets[i2];
                    if (allowsGoneWidget || constraintWidget5.getVisibility() != 8) {
                        DependencyNode dependencyNode4 = constraintWidget5.horizontalRun.end;
                        dependencyNode4.f1982f.add(this.start);
                        this.start.f1983g.add(dependencyNode4);
                    }
                    i2++;
                }
            }
            addDependency(this.f2003a.horizontalRun.start);
            widgetRun = this.f2003a.horizontalRun;
            addDependency(widgetRun.end);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void d() {
        this.f2004b = null;
        this.start.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public boolean h() {
        return false;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun, androidx.constraintlayout.core.widgets.analyzer.Dependency
    public void update(Dependency dependency) {
        Barrier barrier = (Barrier) this.f2003a;
        int barrierType = barrier.getBarrierType();
        int i2 = 0;
        int i3 = -1;
        for (DependencyNode dependencyNode : this.start.f1983g) {
            int i4 = dependencyNode.value;
            if (i3 == -1 || i4 < i3) {
                i3 = i4;
            }
            if (i2 < i4) {
                i2 = i4;
            }
        }
        if (barrierType == 0 || barrierType == 2) {
            this.start.resolve(i3 + barrier.getMargin());
        } else {
            this.start.resolve(i2 + barrier.getMargin());
        }
    }
}
