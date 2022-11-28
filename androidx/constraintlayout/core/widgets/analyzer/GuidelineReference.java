package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.Guideline;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class GuidelineReference extends WidgetRun {
    public GuidelineReference(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        constraintWidget.horizontalRun.d();
        constraintWidget.verticalRun.d();
        this.orientation = ((Guideline) constraintWidget).getOrientation();
    }

    private void addDependency(DependencyNode dependencyNode) {
        this.start.f1982f.add(dependencyNode);
        dependencyNode.f1983g.add(this.start);
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void applyToWidget() {
        if (((Guideline) this.f2003a).getOrientation() == 1) {
            this.f2003a.setX(this.start.value);
        } else {
            this.f2003a.setY(this.start.value);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void c() {
        DependencyNode dependencyNode;
        WidgetRun widgetRun;
        DependencyNode dependencyNode2;
        Guideline guideline = (Guideline) this.f2003a;
        int relativeBegin = guideline.getRelativeBegin();
        int relativeEnd = guideline.getRelativeEnd();
        guideline.getRelativePercent();
        if (guideline.getOrientation() == 1) {
            DependencyNode dependencyNode3 = this.start;
            if (relativeBegin != -1) {
                dependencyNode3.f1983g.add(this.f2003a.mParent.horizontalRun.start);
                this.f2003a.mParent.horizontalRun.start.f1982f.add(this.start);
                dependencyNode2 = this.start;
            } else if (relativeEnd != -1) {
                dependencyNode3.f1983g.add(this.f2003a.mParent.horizontalRun.end);
                this.f2003a.mParent.horizontalRun.end.f1982f.add(this.start);
                dependencyNode2 = this.start;
                relativeBegin = -relativeEnd;
            } else {
                dependencyNode3.delegateToWidgetRun = true;
                dependencyNode3.f1983g.add(this.f2003a.mParent.horizontalRun.end);
                this.f2003a.mParent.horizontalRun.end.f1982f.add(this.start);
                addDependency(this.f2003a.horizontalRun.start);
                widgetRun = this.f2003a.horizontalRun;
            }
            dependencyNode2.f1979c = relativeBegin;
            addDependency(this.f2003a.horizontalRun.start);
            widgetRun = this.f2003a.horizontalRun;
        } else {
            DependencyNode dependencyNode4 = this.start;
            if (relativeBegin != -1) {
                dependencyNode4.f1983g.add(this.f2003a.mParent.verticalRun.start);
                this.f2003a.mParent.verticalRun.start.f1982f.add(this.start);
                dependencyNode = this.start;
            } else if (relativeEnd != -1) {
                dependencyNode4.f1983g.add(this.f2003a.mParent.verticalRun.end);
                this.f2003a.mParent.verticalRun.end.f1982f.add(this.start);
                dependencyNode = this.start;
                relativeBegin = -relativeEnd;
            } else {
                dependencyNode4.delegateToWidgetRun = true;
                dependencyNode4.f1983g.add(this.f2003a.mParent.verticalRun.end);
                this.f2003a.mParent.verticalRun.end.f1982f.add(this.start);
                addDependency(this.f2003a.verticalRun.start);
                widgetRun = this.f2003a.verticalRun;
            }
            dependencyNode.f1979c = relativeBegin;
            addDependency(this.f2003a.verticalRun.start);
            widgetRun = this.f2003a.verticalRun;
        }
        addDependency(widgetRun.end);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void d() {
        this.start.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public boolean h() {
        return false;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun, androidx.constraintlayout.core.widgets.analyzer.Dependency
    public void update(Dependency dependency) {
        DependencyNode dependencyNode = this.start;
        if (dependencyNode.readyToSolve && !dependencyNode.resolved) {
            this.start.resolve((int) ((((DependencyNode) dependencyNode.f1983g.get(0)).value * ((Guideline) this.f2003a).getRelativePercent()) + 0.5f));
        }
    }
}
