package androidx.constraintlayout.core.widgets.analyzer;
/* loaded from: classes.dex */
class BaselineDimensionDependency extends DimensionDependency {
    public BaselineDimensionDependency(WidgetRun widgetRun) {
        super(widgetRun);
    }

    public void update(DependencyNode dependencyNode) {
        WidgetRun widgetRun = this.f1977a;
        ((VerticalWidgetRun) widgetRun).baseline.f1979c = widgetRun.f2003a.getBaselineDistance();
        this.resolved = true;
    }
}
