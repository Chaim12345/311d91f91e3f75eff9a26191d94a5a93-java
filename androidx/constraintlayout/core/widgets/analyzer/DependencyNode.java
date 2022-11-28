package androidx.constraintlayout.core.widgets.analyzer;

import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class DependencyNode implements Dependency {

    /* renamed from: a  reason: collision with root package name */
    WidgetRun f1977a;

    /* renamed from: c  reason: collision with root package name */
    int f1979c;
    public int value;
    public Dependency updateDelegate = null;
    public boolean delegateToWidgetRun = false;
    public boolean readyToSolve = false;

    /* renamed from: b  reason: collision with root package name */
    Type f1978b = Type.UNKNOWN;

    /* renamed from: d  reason: collision with root package name */
    int f1980d = 1;

    /* renamed from: e  reason: collision with root package name */
    DimensionDependency f1981e = null;
    public boolean resolved = false;

    /* renamed from: f  reason: collision with root package name */
    List f1982f = new ArrayList();

    /* renamed from: g  reason: collision with root package name */
    List f1983g = new ArrayList();

    /* loaded from: classes.dex */
    enum Type {
        UNKNOWN,
        HORIZONTAL_DIMENSION,
        VERTICAL_DIMENSION,
        LEFT,
        RIGHT,
        TOP,
        BOTTOM,
        BASELINE
    }

    public DependencyNode(WidgetRun widgetRun) {
        this.f1977a = widgetRun;
    }

    public void addDependency(Dependency dependency) {
        this.f1982f.add(dependency);
        if (this.resolved) {
            dependency.update(dependency);
        }
    }

    public void clear() {
        this.f1983g.clear();
        this.f1982f.clear();
        this.resolved = false;
        this.value = 0;
        this.readyToSolve = false;
        this.delegateToWidgetRun = false;
    }

    public String name() {
        StringBuilder sb;
        String str;
        String debugName = this.f1977a.f2003a.getDebugName();
        Type type = this.f1978b;
        if (type == Type.LEFT || type == Type.RIGHT) {
            sb = new StringBuilder();
            sb.append(debugName);
            str = "_HORIZONTAL";
        } else {
            sb = new StringBuilder();
            sb.append(debugName);
            str = "_VERTICAL";
        }
        sb.append(str);
        String sb2 = sb.toString();
        return sb2 + ":" + this.f1978b.name();
    }

    public void resolve(int i2) {
        if (this.resolved) {
            return;
        }
        this.resolved = true;
        this.value = i2;
        for (Dependency dependency : this.f1982f) {
            dependency.update(dependency);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f1977a.f2003a.getDebugName());
        sb.append(":");
        sb.append(this.f1978b);
        sb.append("(");
        sb.append(this.resolved ? Integer.valueOf(this.value) : "unresolved");
        sb.append(") <t=");
        sb.append(this.f1983g.size());
        sb.append(":d=");
        sb.append(this.f1982f.size());
        sb.append(">");
        return sb.toString();
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.Dependency
    public void update(Dependency dependency) {
        for (DependencyNode dependencyNode : this.f1983g) {
            if (!dependencyNode.resolved) {
                return;
            }
        }
        this.readyToSolve = true;
        Dependency dependency2 = this.updateDelegate;
        if (dependency2 != null) {
            dependency2.update(this);
        }
        if (this.delegateToWidgetRun) {
            this.f1977a.update(this);
            return;
        }
        DependencyNode dependencyNode2 = null;
        int i2 = 0;
        for (DependencyNode dependencyNode3 : this.f1983g) {
            if (!(dependencyNode3 instanceof DimensionDependency)) {
                i2++;
                dependencyNode2 = dependencyNode3;
            }
        }
        if (dependencyNode2 != null && i2 == 1 && dependencyNode2.resolved) {
            DimensionDependency dimensionDependency = this.f1981e;
            if (dimensionDependency != null) {
                if (!dimensionDependency.resolved) {
                    return;
                }
                this.f1979c = this.f1980d * dimensionDependency.value;
            }
            resolve(dependencyNode2.value + this.f1979c);
        }
        Dependency dependency3 = this.updateDelegate;
        if (dependency3 != null) {
            dependency3.update(this);
        }
    }
}
