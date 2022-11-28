package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.Barrier;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.core.widgets.Guideline;
import androidx.constraintlayout.core.widgets.HelperWidget;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
/* loaded from: classes.dex */
public class DependencyGraph {
    private static final boolean USE_GROUPS = true;
    private ConstraintWidgetContainer container;
    private ConstraintWidgetContainer mContainer;
    private boolean mNeedBuildGraph = true;
    private boolean mNeedRedoMeasures = true;
    private ArrayList<WidgetRun> mRuns = new ArrayList<>();
    private ArrayList<RunGroup> runGroups = new ArrayList<>();
    private BasicMeasure.Measurer mMeasurer = null;
    private BasicMeasure.Measure mMeasure = new BasicMeasure.Measure();

    /* renamed from: a  reason: collision with root package name */
    ArrayList f1976a = new ArrayList();

    public DependencyGraph(ConstraintWidgetContainer constraintWidgetContainer) {
        this.container = constraintWidgetContainer;
        this.mContainer = constraintWidgetContainer;
    }

    private void applyGroup(DependencyNode dependencyNode, int i2, int i3, DependencyNode dependencyNode2, ArrayList<RunGroup> arrayList, RunGroup runGroup) {
        WidgetRun widgetRun = dependencyNode.f1977a;
        if (widgetRun.f2004b == null) {
            ConstraintWidgetContainer constraintWidgetContainer = this.container;
            if (widgetRun == constraintWidgetContainer.horizontalRun || widgetRun == constraintWidgetContainer.verticalRun) {
                return;
            }
            if (runGroup == null) {
                runGroup = new RunGroup(widgetRun, i3);
                arrayList.add(runGroup);
            }
            widgetRun.f2004b = runGroup;
            runGroup.add(widgetRun);
            for (Dependency dependency : widgetRun.start.f1982f) {
                if (dependency instanceof DependencyNode) {
                    applyGroup((DependencyNode) dependency, i2, 0, dependencyNode2, arrayList, runGroup);
                }
            }
            for (Dependency dependency2 : widgetRun.end.f1982f) {
                if (dependency2 instanceof DependencyNode) {
                    applyGroup((DependencyNode) dependency2, i2, 1, dependencyNode2, arrayList, runGroup);
                }
            }
            if (i2 == 1 && (widgetRun instanceof VerticalWidgetRun)) {
                for (Dependency dependency3 : ((VerticalWidgetRun) widgetRun).baseline.f1982f) {
                    if (dependency3 instanceof DependencyNode) {
                        applyGroup((DependencyNode) dependency3, i2, 2, dependencyNode2, arrayList, runGroup);
                    }
                }
            }
            for (DependencyNode dependencyNode3 : widgetRun.start.f1983g) {
                if (dependencyNode3 == dependencyNode2) {
                    runGroup.dual = true;
                }
                applyGroup(dependencyNode3, i2, 0, dependencyNode2, arrayList, runGroup);
            }
            for (DependencyNode dependencyNode4 : widgetRun.end.f1983g) {
                if (dependencyNode4 == dependencyNode2) {
                    runGroup.dual = true;
                }
                applyGroup(dependencyNode4, i2, 1, dependencyNode2, arrayList, runGroup);
            }
            if (i2 == 1 && (widgetRun instanceof VerticalWidgetRun)) {
                for (DependencyNode dependencyNode5 : ((VerticalWidgetRun) widgetRun).baseline.f1983g) {
                    applyGroup(dependencyNode5, i2, 2, dependencyNode2, arrayList, runGroup);
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:39:0x0074, code lost:
        if (r2.mMatchConstraintDefaultHeight == 0) goto L30;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private boolean basicMeasureWidgets(ConstraintWidgetContainer constraintWidgetContainer) {
        int i2;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour;
        int i3;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2;
        DependencyGraph dependencyGraph;
        ConstraintWidget constraintWidget;
        DimensionDependency dimensionDependency;
        int height;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour3;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour4;
        Iterator<ConstraintWidget> it = constraintWidgetContainer.mChildren.iterator();
        while (it.hasNext()) {
            ConstraintWidget next = it.next();
            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = next.mListDimensionBehaviors;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour5 = dimensionBehaviourArr[0];
            ConstraintWidget.DimensionBehaviour dimensionBehaviour6 = dimensionBehaviourArr[1];
            if (next.getVisibility() != 8) {
                if (next.mMatchConstraintPercentWidth < 1.0f && dimensionBehaviour5 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    next.mMatchConstraintDefaultWidth = 2;
                }
                if (next.mMatchConstraintPercentHeight < 1.0f && dimensionBehaviour6 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    next.mMatchConstraintDefaultHeight = 2;
                }
                if (next.getDimensionRatio() > 0.0f) {
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour7 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                    if (dimensionBehaviour5 == dimensionBehaviour7 && (dimensionBehaviour6 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || dimensionBehaviour6 == ConstraintWidget.DimensionBehaviour.FIXED)) {
                        next.mMatchConstraintDefaultWidth = 3;
                    } else {
                        if (dimensionBehaviour6 != dimensionBehaviour7 || (dimensionBehaviour5 != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT && dimensionBehaviour5 != ConstraintWidget.DimensionBehaviour.FIXED)) {
                            if (dimensionBehaviour5 == dimensionBehaviour7 && dimensionBehaviour6 == dimensionBehaviour7) {
                                if (next.mMatchConstraintDefaultWidth == 0) {
                                    next.mMatchConstraintDefaultWidth = 3;
                                }
                            }
                        }
                        next.mMatchConstraintDefaultHeight = 3;
                    }
                }
                ConstraintWidget.DimensionBehaviour dimensionBehaviour8 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (dimensionBehaviour5 == dimensionBehaviour8 && next.mMatchConstraintDefaultWidth == 1 && (next.mLeft.mTarget == null || next.mRight.mTarget == null)) {
                    dimensionBehaviour5 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                }
                ConstraintWidget.DimensionBehaviour dimensionBehaviour9 = dimensionBehaviour5;
                if (dimensionBehaviour6 == dimensionBehaviour8 && next.mMatchConstraintDefaultHeight == 1 && (next.mTop.mTarget == null || next.mBottom.mTarget == null)) {
                    dimensionBehaviour6 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                }
                ConstraintWidget.DimensionBehaviour dimensionBehaviour10 = dimensionBehaviour6;
                HorizontalWidgetRun horizontalWidgetRun = next.horizontalRun;
                horizontalWidgetRun.f2005c = dimensionBehaviour9;
                int i4 = next.mMatchConstraintDefaultWidth;
                horizontalWidgetRun.matchConstraintsType = i4;
                VerticalWidgetRun verticalWidgetRun = next.verticalRun;
                verticalWidgetRun.f2005c = dimensionBehaviour10;
                int i5 = next.mMatchConstraintDefaultHeight;
                verticalWidgetRun.matchConstraintsType = i5;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour11 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
                if ((dimensionBehaviour9 == dimensionBehaviour11 || dimensionBehaviour9 == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviour9 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) && (dimensionBehaviour10 == dimensionBehaviour11 || dimensionBehaviour10 == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviour10 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)) {
                    int width = next.getWidth();
                    if (dimensionBehaviour9 == dimensionBehaviour11) {
                        i2 = (constraintWidgetContainer.getWidth() - next.mLeft.mMargin) - next.mRight.mMargin;
                        dimensionBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
                    } else {
                        i2 = width;
                        dimensionBehaviour = dimensionBehaviour9;
                    }
                    int height2 = next.getHeight();
                    if (dimensionBehaviour10 == dimensionBehaviour11) {
                        i3 = (constraintWidgetContainer.getHeight() - next.mTop.mMargin) - next.mBottom.mMargin;
                        dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.FIXED;
                    } else {
                        i3 = height2;
                        dimensionBehaviour2 = dimensionBehaviour10;
                    }
                    dependencyGraph = this;
                    constraintWidget = next;
                } else {
                    if (dimensionBehaviour9 == dimensionBehaviour8 && (dimensionBehaviour10 == (dimensionBehaviour4 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) || dimensionBehaviour10 == ConstraintWidget.DimensionBehaviour.FIXED)) {
                        if (i4 == 3) {
                            if (dimensionBehaviour10 == dimensionBehaviour4) {
                                measure(next, dimensionBehaviour4, 0, dimensionBehaviour4, 0);
                            }
                            i3 = next.getHeight();
                            i2 = (int) ((i3 * next.mDimensionRatio) + 0.5f);
                            dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.FIXED;
                            dependencyGraph = this;
                            constraintWidget = next;
                            dimensionBehaviour = dimensionBehaviour2;
                        } else if (i4 == 1) {
                            measure(next, dimensionBehaviour4, 0, dimensionBehaviour10, 0);
                            dimensionDependency = next.horizontalRun.f2006d;
                            height = next.getWidth();
                            dimensionDependency.wrapValue = height;
                        } else if (i4 == 2) {
                            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr2 = constraintWidgetContainer.mListDimensionBehaviors;
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour12 = dimensionBehaviourArr2[0];
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour13 = ConstraintWidget.DimensionBehaviour.FIXED;
                            if (dimensionBehaviour12 == dimensionBehaviour13 || dimensionBehaviourArr2[0] == dimensionBehaviour11) {
                                i3 = next.getHeight();
                                dependencyGraph = this;
                                constraintWidget = next;
                                dimensionBehaviour = dimensionBehaviour13;
                                i2 = (int) ((next.mMatchConstraintPercentWidth * constraintWidgetContainer.getWidth()) + 0.5f);
                                dimensionBehaviour2 = dimensionBehaviour10;
                            }
                        } else {
                            ConstraintAnchor[] constraintAnchorArr = next.mListAnchors;
                            if (constraintAnchorArr[0].mTarget == null || constraintAnchorArr[1].mTarget == null) {
                                i2 = 0;
                                i3 = 0;
                                dependencyGraph = this;
                                constraintWidget = next;
                                dimensionBehaviour = dimensionBehaviour4;
                                dimensionBehaviour2 = dimensionBehaviour10;
                            }
                        }
                    }
                    if (dimensionBehaviour10 == dimensionBehaviour8 && (dimensionBehaviour9 == (dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) || dimensionBehaviour9 == ConstraintWidget.DimensionBehaviour.FIXED)) {
                        if (i5 == 3) {
                            if (dimensionBehaviour9 == dimensionBehaviour3) {
                                measure(next, dimensionBehaviour3, 0, dimensionBehaviour3, 0);
                            }
                            i2 = next.getWidth();
                            float f2 = next.mDimensionRatio;
                            if (next.getDimensionRatioSide() == -1) {
                                f2 = 1.0f / f2;
                            }
                            i3 = (int) ((i2 * f2) + 0.5f);
                            dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.FIXED;
                            dependencyGraph = this;
                            constraintWidget = next;
                            dimensionBehaviour = dimensionBehaviour2;
                        } else if (i5 == 1) {
                            measure(next, dimensionBehaviour9, 0, dimensionBehaviour3, 0);
                            dimensionDependency = next.verticalRun.f2006d;
                            height = next.getHeight();
                            dimensionDependency.wrapValue = height;
                        } else if (i5 == 2) {
                            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr3 = constraintWidgetContainer.mListDimensionBehaviors;
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour14 = dimensionBehaviourArr3[1];
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour15 = ConstraintWidget.DimensionBehaviour.FIXED;
                            if (dimensionBehaviour14 == dimensionBehaviour15 || dimensionBehaviourArr3[1] == dimensionBehaviour11) {
                                float f3 = next.mMatchConstraintPercentHeight;
                                i2 = next.getWidth();
                                dependencyGraph = this;
                                constraintWidget = next;
                                dimensionBehaviour = dimensionBehaviour9;
                                dimensionBehaviour2 = dimensionBehaviour15;
                                i3 = (int) ((f3 * constraintWidgetContainer.getHeight()) + 0.5f);
                            }
                        } else {
                            ConstraintAnchor[] constraintAnchorArr2 = next.mListAnchors;
                            if (constraintAnchorArr2[2].mTarget == null || constraintAnchorArr2[3].mTarget == null) {
                                i2 = 0;
                                i3 = 0;
                                dependencyGraph = this;
                                constraintWidget = next;
                                dimensionBehaviour = dimensionBehaviour3;
                                dimensionBehaviour2 = dimensionBehaviour10;
                            }
                        }
                    }
                    if (dimensionBehaviour9 == dimensionBehaviour8 && dimensionBehaviour10 == dimensionBehaviour8) {
                        if (i4 == 1 || i5 == 1) {
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour16 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                            measure(next, dimensionBehaviour16, 0, dimensionBehaviour16, 0);
                            next.horizontalRun.f2006d.wrapValue = next.getWidth();
                            dimensionDependency = next.verticalRun.f2006d;
                            height = next.getHeight();
                            dimensionDependency.wrapValue = height;
                        } else if (i5 == 2 && i4 == 2) {
                            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr4 = constraintWidgetContainer.mListDimensionBehaviors;
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour17 = dimensionBehaviourArr4[0];
                            dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.FIXED;
                            if (dimensionBehaviour17 == dimensionBehaviour2 && dimensionBehaviourArr4[1] == dimensionBehaviour2) {
                                float f4 = next.mMatchConstraintPercentWidth;
                                float f5 = next.mMatchConstraintPercentHeight;
                                i2 = (int) ((f4 * constraintWidgetContainer.getWidth()) + 0.5f);
                                i3 = (int) ((f5 * constraintWidgetContainer.getHeight()) + 0.5f);
                                dependencyGraph = this;
                                constraintWidget = next;
                                dimensionBehaviour = dimensionBehaviour2;
                            }
                        }
                    }
                }
                dependencyGraph.measure(constraintWidget, dimensionBehaviour, i2, dimensionBehaviour2, i3);
                next.horizontalRun.f2006d.resolve(next.getWidth());
                next.verticalRun.f2006d.resolve(next.getHeight());
            }
            next.measured = true;
        }
        return false;
    }

    private int computeWrap(ConstraintWidgetContainer constraintWidgetContainer, int i2) {
        int size = this.f1976a.size();
        long j2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            j2 = Math.max(j2, ((RunGroup) this.f1976a.get(i3)).computeWrapSize(constraintWidgetContainer, i2));
        }
        return (int) j2;
    }

    private void displayGraph() {
        Iterator<WidgetRun> it = this.mRuns.iterator();
        String str = "digraph {\n";
        while (it.hasNext()) {
            str = generateDisplayGraph(it.next(), str);
        }
        String str2 = str + "\n}\n";
        System.out.println("content:<<\n" + str2 + "\n>>");
    }

    private void findGroup(WidgetRun widgetRun, int i2, ArrayList<RunGroup> arrayList) {
        for (Dependency dependency : widgetRun.start.f1982f) {
            if (dependency instanceof DependencyNode) {
                applyGroup((DependencyNode) dependency, i2, 0, widgetRun.end, arrayList, null);
            } else if (dependency instanceof WidgetRun) {
                applyGroup(((WidgetRun) dependency).start, i2, 0, widgetRun.end, arrayList, null);
            }
        }
        for (Dependency dependency2 : widgetRun.end.f1982f) {
            if (dependency2 instanceof DependencyNode) {
                applyGroup((DependencyNode) dependency2, i2, 1, widgetRun.start, arrayList, null);
            } else if (dependency2 instanceof WidgetRun) {
                applyGroup(((WidgetRun) dependency2).end, i2, 1, widgetRun.start, arrayList, null);
            }
        }
        if (i2 == 1) {
            for (Dependency dependency3 : ((VerticalWidgetRun) widgetRun).baseline.f1982f) {
                if (dependency3 instanceof DependencyNode) {
                    applyGroup((DependencyNode) dependency3, i2, 2, null, arrayList, null);
                }
            }
        }
    }

    private String generateChainDisplayGraph(ChainRun chainRun, String str) {
        int i2 = chainRun.orientation;
        StringBuilder sb = new StringBuilder("subgraph ");
        sb.append("cluster_");
        sb.append(chainRun.f2003a.getDebugName());
        sb.append(i2 == 0 ? "_h" : "_v");
        sb.append(" {\n");
        Iterator it = chainRun.f1975g.iterator();
        String str2 = "";
        while (it.hasNext()) {
            WidgetRun widgetRun = (WidgetRun) it.next();
            sb.append(widgetRun.f2003a.getDebugName());
            sb.append(i2 == 0 ? "_HORIZONTAL" : "_VERTICAL");
            sb.append(";\n");
            str2 = generateDisplayGraph(widgetRun, str2);
        }
        sb.append("}\n");
        return str + str2 + ((Object) sb);
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x00e4, code lost:
        if (r1.f1983g.isEmpty() == false) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00e6, code lost:
        r2.append("\n");
        r2.append(r0.name());
        r2.append(" -> ");
        r0 = r1.name();
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0153, code lost:
        if (r1.f1983g.isEmpty() == false) goto L50;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private String generateDisplayGraph(WidgetRun widgetRun, String str) {
        String str2;
        boolean z;
        DependencyNode dependencyNode = widgetRun.start;
        DependencyNode dependencyNode2 = widgetRun.end;
        StringBuilder sb = new StringBuilder(str);
        if ((widgetRun instanceof HelperReferences) || !dependencyNode.f1982f.isEmpty() || (!dependencyNode2.f1982f.isEmpty() || !dependencyNode.f1983g.isEmpty()) || !dependencyNode2.f1983g.isEmpty()) {
            sb.append(nodeDefinition(widgetRun));
            boolean isCenteredConnection = isCenteredConnection(dependencyNode, dependencyNode2);
            String generateDisplayNode = generateDisplayNode(dependencyNode2, isCenteredConnection, generateDisplayNode(dependencyNode, isCenteredConnection, str));
            boolean z2 = widgetRun instanceof VerticalWidgetRun;
            if (z2) {
                generateDisplayNode = generateDisplayNode(((VerticalWidgetRun) widgetRun).baseline, isCenteredConnection, generateDisplayNode);
            }
            if ((widgetRun instanceof HorizontalWidgetRun) || (((z = widgetRun instanceof ChainRun)) && ((ChainRun) widgetRun).orientation == 0)) {
                ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour = widgetRun.f2003a.getHorizontalDimensionBehaviour();
                if (horizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.FIXED || horizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    if (dependencyNode.f1983g.isEmpty() || !dependencyNode2.f1983g.isEmpty()) {
                        if (dependencyNode.f1983g.isEmpty()) {
                        }
                    }
                    sb.append("\n");
                    sb.append(dependencyNode2.name());
                    sb.append(" -> ");
                    String name = dependencyNode.name();
                    sb.append(name);
                    sb.append("\n");
                } else if (horizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && widgetRun.f2003a.getDimensionRatio() > 0.0f) {
                    sb.append("\n");
                    sb.append(widgetRun.f2003a.getDebugName());
                    sb.append("_HORIZONTAL -> ");
                    sb.append(widgetRun.f2003a.getDebugName());
                    str2 = "_VERTICAL;\n";
                    sb.append(str2);
                }
            } else if (z2 || (z && ((ChainRun) widgetRun).orientation == 1)) {
                ConstraintWidget.DimensionBehaviour verticalDimensionBehaviour = widgetRun.f2003a.getVerticalDimensionBehaviour();
                if (verticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.FIXED || verticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    if (dependencyNode.f1983g.isEmpty() || !dependencyNode2.f1983g.isEmpty()) {
                        if (dependencyNode.f1983g.isEmpty()) {
                        }
                    }
                    sb.append("\n");
                    sb.append(dependencyNode2.name());
                    sb.append(" -> ");
                    String name2 = dependencyNode.name();
                    sb.append(name2);
                    sb.append("\n");
                } else if (verticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && widgetRun.f2003a.getDimensionRatio() > 0.0f) {
                    sb.append("\n");
                    sb.append(widgetRun.f2003a.getDebugName());
                    sb.append("_VERTICAL -> ");
                    sb.append(widgetRun.f2003a.getDebugName());
                    str2 = "_HORIZONTAL;\n";
                    sb.append(str2);
                }
            }
            return widgetRun instanceof ChainRun ? generateChainDisplayGraph((ChainRun) widgetRun, generateDisplayNode) : sb.toString();
        }
        return str;
    }

    private String generateDisplayNode(DependencyNode dependencyNode, boolean z, String str) {
        StringBuilder sb = new StringBuilder(str);
        Iterator it = dependencyNode.f1983g.iterator();
        while (it.hasNext()) {
            String str2 = ("\n" + dependencyNode.name()) + " -> " + ((DependencyNode) it.next()).name();
            if (dependencyNode.f1979c > 0 || z || (dependencyNode.f1977a instanceof HelperReferences)) {
                String str3 = str2 + "[";
                if (dependencyNode.f1979c > 0) {
                    str3 = str3 + "label=\"" + dependencyNode.f1979c + "\"";
                    if (z) {
                        str3 = str3 + ",";
                    }
                }
                if (z) {
                    str3 = str3 + " style=dashed ";
                }
                if (dependencyNode.f1977a instanceof HelperReferences) {
                    str3 = str3 + " style=bold,color=gray ";
                }
                str2 = str3 + "]";
            }
            sb.append(str2 + "\n");
        }
        return sb.toString();
    }

    private boolean isCenteredConnection(DependencyNode dependencyNode, DependencyNode dependencyNode2) {
        int i2 = 0;
        for (DependencyNode dependencyNode3 : dependencyNode.f1983g) {
            if (dependencyNode3 != dependencyNode2) {
                i2++;
            }
        }
        int i3 = 0;
        for (DependencyNode dependencyNode4 : dependencyNode2.f1983g) {
            if (dependencyNode4 != dependencyNode) {
                i3++;
            }
        }
        return i2 > 0 && i3 > 0;
    }

    private void measure(ConstraintWidget constraintWidget, ConstraintWidget.DimensionBehaviour dimensionBehaviour, int i2, ConstraintWidget.DimensionBehaviour dimensionBehaviour2, int i3) {
        BasicMeasure.Measure measure = this.mMeasure;
        measure.horizontalBehavior = dimensionBehaviour;
        measure.verticalBehavior = dimensionBehaviour2;
        measure.horizontalDimension = i2;
        measure.verticalDimension = i3;
        this.mMeasurer.measure(constraintWidget, measure);
        constraintWidget.setWidth(this.mMeasure.measuredWidth);
        constraintWidget.setHeight(this.mMeasure.measuredHeight);
        constraintWidget.setHasBaseline(this.mMeasure.measuredHasBaseline);
        constraintWidget.setBaselineDistance(this.mMeasure.measuredBaseline);
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00c0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private String nodeDefinition(WidgetRun widgetRun) {
        String str;
        String str2;
        String str3;
        boolean z = widgetRun instanceof VerticalWidgetRun;
        String debugName = widgetRun.f2003a.getDebugName();
        StringBuilder sb = new StringBuilder(debugName);
        ConstraintWidget constraintWidget = widgetRun.f2003a;
        ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour = !z ? constraintWidget.getHorizontalDimensionBehaviour() : constraintWidget.getVerticalDimensionBehaviour();
        RunGroup runGroup = widgetRun.f2004b;
        sb.append(!z ? "_HORIZONTAL" : "_VERTICAL");
        sb.append(" [shape=none, label=<");
        sb.append("<TABLE BORDER=\"0\" CELLSPACING=\"0\" CELLPADDING=\"2\">");
        sb.append("  <TR>");
        sb.append("    <TD ");
        boolean z2 = widgetRun.start.resolved;
        if (z) {
            if (z2) {
                sb.append(" BGCOLOR=\"green\"");
            }
            str = " PORT=\"TOP\" BORDER=\"1\">T</TD>";
        } else {
            if (z2) {
                sb.append(" BGCOLOR=\"green\"");
            }
            str = " PORT=\"LEFT\" BORDER=\"1\">L</TD>";
        }
        sb.append(str);
        sb.append("    <TD BORDER=\"1\" ");
        boolean z3 = widgetRun.f2006d.resolved;
        if (z3 && !widgetRun.f2003a.measured) {
            str2 = " BGCOLOR=\"green\" ";
        } else if (!z3) {
            if (widgetRun.f2003a.measured) {
                str2 = " BGCOLOR=\"yellow\" ";
            }
            if (horizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                sb.append("style=\"dashed\"");
            }
            sb.append(">");
            sb.append(debugName);
            if (runGroup != null) {
                sb.append(" [");
                sb.append(runGroup.f1987c + 1);
                sb.append("/");
                sb.append(RunGroup.index);
                sb.append("]");
            }
            sb.append(" </TD>");
            sb.append("    <TD ");
            if (z) {
                if (widgetRun.end.resolved) {
                    sb.append(" BGCOLOR=\"green\"");
                }
                str3 = " PORT=\"RIGHT\" BORDER=\"1\">R</TD>";
            } else {
                if (((VerticalWidgetRun) widgetRun).baseline.resolved) {
                    sb.append(" BGCOLOR=\"green\"");
                }
                sb.append(" PORT=\"BASELINE\" BORDER=\"1\">b</TD>");
                sb.append("    <TD ");
                if (widgetRun.end.resolved) {
                    sb.append(" BGCOLOR=\"green\"");
                }
                str3 = " PORT=\"BOTTOM\" BORDER=\"1\">B</TD>";
            }
            sb.append(str3);
            sb.append("  </TR></TABLE>");
            sb.append(">];\n");
            return sb.toString();
        } else {
            str2 = " BGCOLOR=\"lightgray\" ";
        }
        sb.append(str2);
        if (horizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
        }
        sb.append(">");
        sb.append(debugName);
        if (runGroup != null) {
        }
        sb.append(" </TD>");
        sb.append("    <TD ");
        if (z) {
        }
        sb.append(str3);
        sb.append("  </TR></TABLE>");
        sb.append(">];\n");
        return sb.toString();
    }

    public void buildGraph() {
        buildGraph(this.mRuns);
        this.f1976a.clear();
        RunGroup.index = 0;
        findGroup(this.container.horizontalRun, 0, this.f1976a);
        findGroup(this.container.verticalRun, 1, this.f1976a);
        this.mNeedBuildGraph = false;
    }

    public void buildGraph(ArrayList<WidgetRun> arrayList) {
        WidgetRun guidelineReference;
        arrayList.clear();
        this.mContainer.horizontalRun.d();
        this.mContainer.verticalRun.d();
        arrayList.add(this.mContainer.horizontalRun);
        arrayList.add(this.mContainer.verticalRun);
        Iterator<ConstraintWidget> it = this.mContainer.mChildren.iterator();
        HashSet hashSet = null;
        while (it.hasNext()) {
            ConstraintWidget next = it.next();
            if (next instanceof Guideline) {
                guidelineReference = new GuidelineReference(next);
            } else {
                if (next.isInHorizontalChain()) {
                    if (next.horizontalChainRun == null) {
                        next.horizontalChainRun = new ChainRun(next, 0);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(next.horizontalChainRun);
                } else {
                    arrayList.add(next.horizontalRun);
                }
                if (next.isInVerticalChain()) {
                    if (next.verticalChainRun == null) {
                        next.verticalChainRun = new ChainRun(next, 1);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(next.verticalChainRun);
                } else {
                    arrayList.add(next.verticalRun);
                }
                if (next instanceof HelperWidget) {
                    guidelineReference = new HelperReferences(next);
                }
            }
            arrayList.add(guidelineReference);
        }
        if (hashSet != null) {
            arrayList.addAll(hashSet);
        }
        Iterator<WidgetRun> it2 = arrayList.iterator();
        while (it2.hasNext()) {
            it2.next().d();
        }
        Iterator<WidgetRun> it3 = arrayList.iterator();
        while (it3.hasNext()) {
            WidgetRun next2 = it3.next();
            if (next2.f2003a != this.mContainer) {
                next2.c();
            }
        }
    }

    public void defineTerminalWidgets(ConstraintWidget.DimensionBehaviour dimensionBehaviour, ConstraintWidget.DimensionBehaviour dimensionBehaviour2) {
        if (this.mNeedBuildGraph) {
            buildGraph();
            Iterator<ConstraintWidget> it = this.container.mChildren.iterator();
            boolean z = false;
            while (it.hasNext()) {
                ConstraintWidget next = it.next();
                boolean[] zArr = next.isTerminalWidget;
                zArr[0] = true;
                zArr[1] = true;
                if (next instanceof Barrier) {
                    z = true;
                }
            }
            if (z) {
                return;
            }
            Iterator it2 = this.f1976a.iterator();
            while (it2.hasNext()) {
                RunGroup runGroup = (RunGroup) it2.next();
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                runGroup.defineTerminalWidgets(dimensionBehaviour == dimensionBehaviour3, dimensionBehaviour2 == dimensionBehaviour3);
            }
        }
    }

    public boolean directMeasure(boolean z) {
        boolean z2;
        boolean z3 = true;
        boolean z4 = z & true;
        if (this.mNeedBuildGraph || this.mNeedRedoMeasures) {
            Iterator<ConstraintWidget> it = this.container.mChildren.iterator();
            while (it.hasNext()) {
                ConstraintWidget next = it.next();
                next.ensureWidgetRuns();
                next.measured = false;
                next.horizontalRun.l();
                next.verticalRun.l();
            }
            this.container.ensureWidgetRuns();
            ConstraintWidgetContainer constraintWidgetContainer = this.container;
            constraintWidgetContainer.measured = false;
            constraintWidgetContainer.horizontalRun.l();
            this.container.verticalRun.l();
            this.mNeedRedoMeasures = false;
        }
        if (basicMeasureWidgets(this.mContainer)) {
            return false;
        }
        this.container.setX(0);
        this.container.setY(0);
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = this.container.getDimensionBehaviour(0);
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = this.container.getDimensionBehaviour(1);
        if (this.mNeedBuildGraph) {
            buildGraph();
        }
        int x = this.container.getX();
        int y = this.container.getY();
        this.container.horizontalRun.start.resolve(x);
        this.container.verticalRun.start.resolve(y);
        measureWidgets();
        ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (dimensionBehaviour == dimensionBehaviour3 || dimensionBehaviour2 == dimensionBehaviour3) {
            if (z4) {
                Iterator<WidgetRun> it2 = this.mRuns.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    } else if (!it2.next().h()) {
                        z4 = false;
                        break;
                    }
                }
            }
            if (z4 && dimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                this.container.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                ConstraintWidgetContainer constraintWidgetContainer2 = this.container;
                constraintWidgetContainer2.setWidth(computeWrap(constraintWidgetContainer2, 0));
                ConstraintWidgetContainer constraintWidgetContainer3 = this.container;
                constraintWidgetContainer3.horizontalRun.f2006d.resolve(constraintWidgetContainer3.getWidth());
            }
            if (z4 && dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                this.container.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                ConstraintWidgetContainer constraintWidgetContainer4 = this.container;
                constraintWidgetContainer4.setHeight(computeWrap(constraintWidgetContainer4, 1));
                ConstraintWidgetContainer constraintWidgetContainer5 = this.container;
                constraintWidgetContainer5.verticalRun.f2006d.resolve(constraintWidgetContainer5.getHeight());
            }
        }
        ConstraintWidgetContainer constraintWidgetContainer6 = this.container;
        ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidgetContainer6.mListDimensionBehaviors;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = dimensionBehaviourArr[0];
        ConstraintWidget.DimensionBehaviour dimensionBehaviour5 = ConstraintWidget.DimensionBehaviour.FIXED;
        if (dimensionBehaviour4 == dimensionBehaviour5 || dimensionBehaviourArr[0] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
            int width = constraintWidgetContainer6.getWidth() + x;
            this.container.horizontalRun.end.resolve(width);
            this.container.horizontalRun.f2006d.resolve(width - x);
            measureWidgets();
            ConstraintWidgetContainer constraintWidgetContainer7 = this.container;
            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr2 = constraintWidgetContainer7.mListDimensionBehaviors;
            if (dimensionBehaviourArr2[1] == dimensionBehaviour5 || dimensionBehaviourArr2[1] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                int height = constraintWidgetContainer7.getHeight() + y;
                this.container.verticalRun.end.resolve(height);
                this.container.verticalRun.f2006d.resolve(height - y);
            }
            measureWidgets();
            z2 = true;
        } else {
            z2 = false;
        }
        Iterator<WidgetRun> it3 = this.mRuns.iterator();
        while (it3.hasNext()) {
            WidgetRun next2 = it3.next();
            if (next2.f2003a != this.container || next2.f2007e) {
                next2.applyToWidget();
            }
        }
        Iterator<WidgetRun> it4 = this.mRuns.iterator();
        while (it4.hasNext()) {
            WidgetRun next3 = it4.next();
            if (z2 || next3.f2003a != this.container) {
                if (!next3.start.resolved || ((!next3.end.resolved && !(next3 instanceof GuidelineReference)) || (!next3.f2006d.resolved && !(next3 instanceof ChainRun) && !(next3 instanceof GuidelineReference)))) {
                    z3 = false;
                    break;
                }
            }
        }
        this.container.setHorizontalDimensionBehaviour(dimensionBehaviour);
        this.container.setVerticalDimensionBehaviour(dimensionBehaviour2);
        return z3;
    }

    public boolean directMeasureSetup(boolean z) {
        if (this.mNeedBuildGraph) {
            Iterator<ConstraintWidget> it = this.container.mChildren.iterator();
            while (it.hasNext()) {
                ConstraintWidget next = it.next();
                next.ensureWidgetRuns();
                next.measured = false;
                HorizontalWidgetRun horizontalWidgetRun = next.horizontalRun;
                horizontalWidgetRun.f2006d.resolved = false;
                horizontalWidgetRun.f2007e = false;
                horizontalWidgetRun.l();
                VerticalWidgetRun verticalWidgetRun = next.verticalRun;
                verticalWidgetRun.f2006d.resolved = false;
                verticalWidgetRun.f2007e = false;
                verticalWidgetRun.l();
            }
            this.container.ensureWidgetRuns();
            ConstraintWidgetContainer constraintWidgetContainer = this.container;
            constraintWidgetContainer.measured = false;
            HorizontalWidgetRun horizontalWidgetRun2 = constraintWidgetContainer.horizontalRun;
            horizontalWidgetRun2.f2006d.resolved = false;
            horizontalWidgetRun2.f2007e = false;
            horizontalWidgetRun2.l();
            VerticalWidgetRun verticalWidgetRun2 = this.container.verticalRun;
            verticalWidgetRun2.f2006d.resolved = false;
            verticalWidgetRun2.f2007e = false;
            verticalWidgetRun2.l();
            buildGraph();
        }
        if (basicMeasureWidgets(this.mContainer)) {
            return false;
        }
        this.container.setX(0);
        this.container.setY(0);
        this.container.horizontalRun.start.resolve(0);
        this.container.verticalRun.start.resolve(0);
        return true;
    }

    public boolean directMeasureWithOrientation(boolean z, int i2) {
        boolean z2;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour;
        DimensionDependency dimensionDependency;
        int height;
        boolean z3 = true;
        boolean z4 = z & true;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = this.container.getDimensionBehaviour(0);
        ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = this.container.getDimensionBehaviour(1);
        int x = this.container.getX();
        int y = this.container.getY();
        if (z4 && (dimensionBehaviour2 == (dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) || dimensionBehaviour3 == dimensionBehaviour)) {
            Iterator<WidgetRun> it = this.mRuns.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                WidgetRun next = it.next();
                if (next.orientation == i2 && !next.h()) {
                    z4 = false;
                    break;
                }
            }
            if (i2 == 0) {
                if (z4 && dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    this.container.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                    ConstraintWidgetContainer constraintWidgetContainer = this.container;
                    constraintWidgetContainer.setWidth(computeWrap(constraintWidgetContainer, 0));
                    ConstraintWidgetContainer constraintWidgetContainer2 = this.container;
                    dimensionDependency = constraintWidgetContainer2.horizontalRun.f2006d;
                    height = constraintWidgetContainer2.getWidth();
                    dimensionDependency.resolve(height);
                }
            } else if (z4 && dimensionBehaviour3 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                this.container.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                ConstraintWidgetContainer constraintWidgetContainer3 = this.container;
                constraintWidgetContainer3.setHeight(computeWrap(constraintWidgetContainer3, 1));
                ConstraintWidgetContainer constraintWidgetContainer4 = this.container;
                dimensionDependency = constraintWidgetContainer4.verticalRun.f2006d;
                height = constraintWidgetContainer4.getHeight();
                dimensionDependency.resolve(height);
            }
        }
        ConstraintWidgetContainer constraintWidgetContainer5 = this.container;
        if (i2 == 0) {
            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidgetContainer5.mListDimensionBehaviors;
            if (dimensionBehaviourArr[0] == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviourArr[0] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                int width = constraintWidgetContainer5.getWidth() + x;
                this.container.horizontalRun.end.resolve(width);
                this.container.horizontalRun.f2006d.resolve(width - x);
                z2 = true;
            }
            z2 = false;
        } else {
            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr2 = constraintWidgetContainer5.mListDimensionBehaviors;
            if (dimensionBehaviourArr2[1] == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviourArr2[1] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                int height2 = constraintWidgetContainer5.getHeight() + y;
                this.container.verticalRun.end.resolve(height2);
                this.container.verticalRun.f2006d.resolve(height2 - y);
                z2 = true;
            }
            z2 = false;
        }
        measureWidgets();
        Iterator<WidgetRun> it2 = this.mRuns.iterator();
        while (it2.hasNext()) {
            WidgetRun next2 = it2.next();
            if (next2.orientation == i2 && (next2.f2003a != this.container || next2.f2007e)) {
                next2.applyToWidget();
            }
        }
        Iterator<WidgetRun> it3 = this.mRuns.iterator();
        while (it3.hasNext()) {
            WidgetRun next3 = it3.next();
            if (next3.orientation == i2 && (z2 || next3.f2003a != this.container)) {
                if (!next3.start.resolved || !next3.end.resolved || (!(next3 instanceof ChainRun) && !next3.f2006d.resolved)) {
                    z3 = false;
                    break;
                }
            }
        }
        this.container.setHorizontalDimensionBehaviour(dimensionBehaviour2);
        this.container.setVerticalDimensionBehaviour(dimensionBehaviour3);
        return z3;
    }

    public void invalidateGraph() {
        this.mNeedBuildGraph = true;
    }

    public void invalidateMeasures() {
        this.mNeedRedoMeasures = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x00b2 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0008 A[ADDED_TO_REGION, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void measureWidgets() {
        DimensionDependency dimensionDependency;
        int width;
        DimensionDependency dimensionDependency2;
        int width2;
        DimensionDependency dimensionDependency3;
        Iterator<ConstraintWidget> it = this.container.mChildren.iterator();
        while (it.hasNext()) {
            ConstraintWidget next = it.next();
            if (!next.measured) {
                ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = next.mListDimensionBehaviors;
                boolean z = false;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = dimensionBehaviourArr[0];
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = dimensionBehaviourArr[1];
                int i2 = next.mMatchConstraintDefaultWidth;
                int i3 = next.mMatchConstraintDefaultHeight;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                boolean z2 = dimensionBehaviour == dimensionBehaviour3 || (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && i2 == 1);
                if (dimensionBehaviour2 == dimensionBehaviour3 || (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && i3 == 1)) {
                    z = true;
                }
                DimensionDependency dimensionDependency4 = next.horizontalRun.f2006d;
                boolean z3 = dimensionDependency4.resolved;
                DimensionDependency dimensionDependency5 = next.verticalRun.f2006d;
                boolean z4 = dimensionDependency5.resolved;
                if (z3 && z4) {
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = ConstraintWidget.DimensionBehaviour.FIXED;
                    measure(next, dimensionBehaviour4, dimensionDependency4.value, dimensionBehaviour4, dimensionDependency5.value);
                } else if (z3 && z) {
                    measure(next, ConstraintWidget.DimensionBehaviour.FIXED, dimensionDependency4.value, dimensionBehaviour3, dimensionDependency5.value);
                    if (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        dimensionDependency2 = next.verticalRun.f2006d;
                        width2 = next.getHeight();
                        dimensionDependency2.wrapValue = width2;
                        if (!next.measured) {
                            dimensionDependency3.resolve(next.getBaselineDistance());
                        }
                    } else {
                        dimensionDependency = next.verticalRun.f2006d;
                        width = next.getHeight();
                        dimensionDependency.resolve(width);
                    }
                } else {
                    if (z4 && z2) {
                        measure(next, dimensionBehaviour3, dimensionDependency4.value, ConstraintWidget.DimensionBehaviour.FIXED, dimensionDependency5.value);
                        if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                            dimensionDependency2 = next.horizontalRun.f2006d;
                            width2 = next.getWidth();
                            dimensionDependency2.wrapValue = width2;
                        } else {
                            dimensionDependency = next.horizontalRun.f2006d;
                            width = next.getWidth();
                            dimensionDependency.resolve(width);
                        }
                    }
                    if (!next.measured && (dimensionDependency3 = next.verticalRun.f1988g) != null) {
                        dimensionDependency3.resolve(next.getBaselineDistance());
                    }
                }
                next.measured = true;
                if (!next.measured) {
                }
            }
        }
    }

    public void setMeasurer(BasicMeasure.Measurer measurer) {
        this.mMeasurer = measurer;
    }
}
