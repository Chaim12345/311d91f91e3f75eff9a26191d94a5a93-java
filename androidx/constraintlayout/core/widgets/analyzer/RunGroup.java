package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import java.util.ArrayList;
import java.util.Iterator;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class RunGroup {
    public static final int BASELINE = 2;
    public static final int END = 1;
    public static final int START = 0;
    public static int index;

    /* renamed from: a  reason: collision with root package name */
    WidgetRun f1985a;

    /* renamed from: c  reason: collision with root package name */
    int f1987c;
    public int position = 0;
    public boolean dual = false;

    /* renamed from: b  reason: collision with root package name */
    ArrayList f1986b = new ArrayList();

    public RunGroup(WidgetRun widgetRun, int i2) {
        this.f1985a = null;
        this.f1987c = 0;
        int i3 = index;
        this.f1987c = i3;
        index = i3 + 1;
        this.f1985a = widgetRun;
    }

    private boolean defineTerminalWidget(WidgetRun widgetRun, int i2) {
        DependencyNode dependencyNode;
        WidgetRun widgetRun2;
        DependencyNode dependencyNode2;
        WidgetRun widgetRun3;
        if (widgetRun.f2003a.isTerminalWidget[i2]) {
            for (Dependency dependency : widgetRun.start.f1982f) {
                if ((dependency instanceof DependencyNode) && (widgetRun3 = (dependencyNode2 = (DependencyNode) dependency).f1977a) != widgetRun && dependencyNode2 == widgetRun3.start) {
                    if (widgetRun instanceof ChainRun) {
                        Iterator it = ((ChainRun) widgetRun).f1975g.iterator();
                        while (it.hasNext()) {
                            defineTerminalWidget((WidgetRun) it.next(), i2);
                        }
                    } else if (!(widgetRun instanceof HelperReferences)) {
                        widgetRun.f2003a.isTerminalWidget[i2] = false;
                    }
                    defineTerminalWidget(dependencyNode2.f1977a, i2);
                }
            }
            for (Dependency dependency2 : widgetRun.end.f1982f) {
                if ((dependency2 instanceof DependencyNode) && (widgetRun2 = (dependencyNode = (DependencyNode) dependency2).f1977a) != widgetRun && dependencyNode == widgetRun2.start) {
                    if (widgetRun instanceof ChainRun) {
                        Iterator it2 = ((ChainRun) widgetRun).f1975g.iterator();
                        while (it2.hasNext()) {
                            defineTerminalWidget((WidgetRun) it2.next(), i2);
                        }
                    } else if (!(widgetRun instanceof HelperReferences)) {
                        widgetRun.f2003a.isTerminalWidget[i2] = false;
                    }
                    defineTerminalWidget(dependencyNode.f1977a, i2);
                }
            }
            return false;
        }
        return false;
    }

    private long traverseEnd(DependencyNode dependencyNode, long j2) {
        WidgetRun widgetRun = dependencyNode.f1977a;
        if (widgetRun instanceof HelperReferences) {
            return j2;
        }
        int size = dependencyNode.f1982f.size();
        long j3 = j2;
        for (int i2 = 0; i2 < size; i2++) {
            Dependency dependency = (Dependency) dependencyNode.f1982f.get(i2);
            if (dependency instanceof DependencyNode) {
                DependencyNode dependencyNode2 = (DependencyNode) dependency;
                if (dependencyNode2.f1977a != widgetRun) {
                    j3 = Math.min(j3, traverseEnd(dependencyNode2, dependencyNode2.f1979c + j2));
                }
            }
        }
        if (dependencyNode == widgetRun.end) {
            long wrapDimension = j2 - widgetRun.getWrapDimension();
            return Math.min(Math.min(j3, traverseEnd(widgetRun.start, wrapDimension)), wrapDimension - widgetRun.start.f1979c);
        }
        return j3;
    }

    private long traverseStart(DependencyNode dependencyNode, long j2) {
        WidgetRun widgetRun = dependencyNode.f1977a;
        if (widgetRun instanceof HelperReferences) {
            return j2;
        }
        int size = dependencyNode.f1982f.size();
        long j3 = j2;
        for (int i2 = 0; i2 < size; i2++) {
            Dependency dependency = (Dependency) dependencyNode.f1982f.get(i2);
            if (dependency instanceof DependencyNode) {
                DependencyNode dependencyNode2 = (DependencyNode) dependency;
                if (dependencyNode2.f1977a != widgetRun) {
                    j3 = Math.max(j3, traverseStart(dependencyNode2, dependencyNode2.f1979c + j2));
                }
            }
        }
        if (dependencyNode == widgetRun.start) {
            long wrapDimension = j2 + widgetRun.getWrapDimension();
            return Math.max(Math.max(j3, traverseStart(widgetRun.end, wrapDimension)), wrapDimension - widgetRun.end.f1979c);
        }
        return j3;
    }

    public void add(WidgetRun widgetRun) {
        this.f1986b.add(widgetRun);
    }

    public long computeWrapSize(ConstraintWidgetContainer constraintWidgetContainer, int i2) {
        long wrapDimension;
        WidgetRun widgetRun;
        long j2;
        long j3;
        WidgetRun widgetRun2 = this.f1985a;
        if (widgetRun2 instanceof ChainRun) {
            if (((ChainRun) widgetRun2).orientation != i2) {
                return 0L;
            }
        } else if (i2 == 0) {
            if (!(widgetRun2 instanceof HorizontalWidgetRun)) {
                return 0L;
            }
        } else if (!(widgetRun2 instanceof VerticalWidgetRun)) {
            return 0L;
        }
        DependencyNode dependencyNode = (i2 == 0 ? constraintWidgetContainer.horizontalRun : constraintWidgetContainer.verticalRun).start;
        DependencyNode dependencyNode2 = (i2 == 0 ? constraintWidgetContainer.horizontalRun : constraintWidgetContainer.verticalRun).end;
        boolean contains = widgetRun2.start.f1983g.contains(dependencyNode);
        boolean contains2 = this.f1985a.end.f1983g.contains(dependencyNode2);
        long wrapDimension2 = this.f1985a.getWrapDimension();
        if (!contains || !contains2) {
            if (contains) {
                DependencyNode dependencyNode3 = this.f1985a.start;
                j3 = traverseStart(dependencyNode3, dependencyNode3.f1979c);
                j2 = this.f1985a.start.f1979c + wrapDimension2;
            } else if (contains2) {
                DependencyNode dependencyNode4 = this.f1985a.end;
                long traverseEnd = traverseEnd(dependencyNode4, dependencyNode4.f1979c);
                j2 = (-this.f1985a.end.f1979c) + wrapDimension2;
                j3 = -traverseEnd;
            } else {
                WidgetRun widgetRun3 = this.f1985a;
                wrapDimension = widgetRun3.start.f1979c + widgetRun3.getWrapDimension();
                widgetRun = this.f1985a;
            }
            return Math.max(j3, j2);
        }
        long traverseStart = traverseStart(this.f1985a.start, 0L);
        long traverseEnd2 = traverseEnd(this.f1985a.end, 0L);
        long j4 = traverseStart - wrapDimension2;
        WidgetRun widgetRun4 = this.f1985a;
        int i3 = widgetRun4.end.f1979c;
        if (j4 >= (-i3)) {
            j4 += i3;
        }
        int i4 = widgetRun4.start.f1979c;
        long j5 = ((-traverseEnd2) - wrapDimension2) - i4;
        if (j5 >= i4) {
            j5 -= i4;
        }
        float biasPercent = widgetRun4.f2003a.getBiasPercent(i2);
        float f2 = (float) (biasPercent > 0.0f ? (((float) j5) / biasPercent) + (((float) j4) / (1.0f - biasPercent)) : 0L);
        long j6 = (f2 * biasPercent) + 0.5f + wrapDimension2 + (f2 * (1.0f - biasPercent)) + 0.5f;
        widgetRun = this.f1985a;
        wrapDimension = widgetRun.start.f1979c + j6;
        return wrapDimension - widgetRun.end.f1979c;
    }

    public void defineTerminalWidgets(boolean z, boolean z2) {
        if (z) {
            WidgetRun widgetRun = this.f1985a;
            if (widgetRun instanceof HorizontalWidgetRun) {
                defineTerminalWidget(widgetRun, 0);
            }
        }
        if (z2) {
            WidgetRun widgetRun2 = this.f1985a;
            if (widgetRun2 instanceof VerticalWidgetRun) {
                defineTerminalWidget(widgetRun2, 1);
            }
        }
    }
}
