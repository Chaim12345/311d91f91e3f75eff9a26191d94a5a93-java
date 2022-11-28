package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.widgets.Chain;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes.dex */
public class WidgetGroup {
    private static final boolean DEBUG = false;

    /* renamed from: f  reason: collision with root package name */
    static int f1990f;

    /* renamed from: b  reason: collision with root package name */
    int f1992b;

    /* renamed from: d  reason: collision with root package name */
    int f1994d;

    /* renamed from: a  reason: collision with root package name */
    ArrayList f1991a = new ArrayList();

    /* renamed from: c  reason: collision with root package name */
    boolean f1993c = false;

    /* renamed from: e  reason: collision with root package name */
    ArrayList f1995e = null;
    private int moveTo = -1;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class MeasureResult {

        /* renamed from: a  reason: collision with root package name */
        WeakReference f1996a;

        /* renamed from: b  reason: collision with root package name */
        int f1997b;

        /* renamed from: c  reason: collision with root package name */
        int f1998c;

        /* renamed from: d  reason: collision with root package name */
        int f1999d;

        /* renamed from: e  reason: collision with root package name */
        int f2000e;

        /* renamed from: f  reason: collision with root package name */
        int f2001f;

        /* renamed from: g  reason: collision with root package name */
        int f2002g;

        public MeasureResult(WidgetGroup widgetGroup, ConstraintWidget constraintWidget, LinearSystem linearSystem, int i2) {
            this.f1996a = new WeakReference(constraintWidget);
            this.f1997b = linearSystem.getObjectVariableValue(constraintWidget.mLeft);
            this.f1998c = linearSystem.getObjectVariableValue(constraintWidget.mTop);
            this.f1999d = linearSystem.getObjectVariableValue(constraintWidget.mRight);
            this.f2000e = linearSystem.getObjectVariableValue(constraintWidget.mBottom);
            this.f2001f = linearSystem.getObjectVariableValue(constraintWidget.mBaseline);
            this.f2002g = i2;
        }

        public void apply() {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.f1996a.get();
            if (constraintWidget != null) {
                constraintWidget.setFinalFrame(this.f1997b, this.f1998c, this.f1999d, this.f2000e, this.f2001f, this.f2002g);
            }
        }
    }

    public WidgetGroup(int i2) {
        this.f1992b = -1;
        this.f1994d = 0;
        int i3 = f1990f;
        f1990f = i3 + 1;
        this.f1992b = i3;
        this.f1994d = i2;
    }

    private boolean contains(ConstraintWidget constraintWidget) {
        return this.f1991a.contains(constraintWidget);
    }

    private String getOrientationString() {
        int i2 = this.f1994d;
        return i2 == 0 ? "Horizontal" : i2 == 1 ? "Vertical" : i2 == 2 ? "Both" : "Unknown";
    }

    private int measureWrap(int i2, ConstraintWidget constraintWidget) {
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = constraintWidget.getDimensionBehaviour(i2);
        if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_PARENT || dimensionBehaviour == ConstraintWidget.DimensionBehaviour.FIXED) {
            return i2 == 0 ? constraintWidget.getWidth() : constraintWidget.getHeight();
        }
        return -1;
    }

    private int solverMeasure(LinearSystem linearSystem, ArrayList<ConstraintWidget> arrayList, int i2) {
        int objectVariableValue;
        ConstraintAnchor constraintAnchor;
        ConstraintWidgetContainer constraintWidgetContainer = (ConstraintWidgetContainer) arrayList.get(0).getParent();
        linearSystem.reset();
        constraintWidgetContainer.addToSolver(linearSystem, false);
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            arrayList.get(i3).addToSolver(linearSystem, false);
        }
        if (i2 == 0 && constraintWidgetContainer.mHorizontalChainsSize > 0) {
            Chain.applyChainConstraints(constraintWidgetContainer, linearSystem, arrayList, 0);
        }
        if (i2 == 1 && constraintWidgetContainer.mVerticalChainsSize > 0) {
            Chain.applyChainConstraints(constraintWidgetContainer, linearSystem, arrayList, 1);
        }
        try {
            linearSystem.minimize();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        this.f1995e = new ArrayList();
        for (int i4 = 0; i4 < arrayList.size(); i4++) {
            this.f1995e.add(new MeasureResult(this, arrayList.get(i4), linearSystem, i2));
        }
        if (i2 == 0) {
            objectVariableValue = linearSystem.getObjectVariableValue(constraintWidgetContainer.mLeft);
            constraintAnchor = constraintWidgetContainer.mRight;
        } else {
            objectVariableValue = linearSystem.getObjectVariableValue(constraintWidgetContainer.mTop);
            constraintAnchor = constraintWidgetContainer.mBottom;
        }
        int objectVariableValue2 = linearSystem.getObjectVariableValue(constraintAnchor);
        linearSystem.reset();
        return objectVariableValue2 - objectVariableValue;
    }

    public boolean add(ConstraintWidget constraintWidget) {
        if (this.f1991a.contains(constraintWidget)) {
            return false;
        }
        this.f1991a.add(constraintWidget);
        return true;
    }

    public void apply() {
        if (this.f1995e != null && this.f1993c) {
            for (int i2 = 0; i2 < this.f1995e.size(); i2++) {
                ((MeasureResult) this.f1995e.get(i2)).apply();
            }
        }
    }

    public void cleanup(ArrayList<WidgetGroup> arrayList) {
        int size = this.f1991a.size();
        if (this.moveTo != -1 && size > 0) {
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                WidgetGroup widgetGroup = arrayList.get(i2);
                if (this.moveTo == widgetGroup.f1992b) {
                    moveTo(this.f1994d, widgetGroup);
                }
            }
        }
        if (size == 0) {
            arrayList.remove(this);
        }
    }

    public void clear() {
        this.f1991a.clear();
    }

    public int getId() {
        return this.f1992b;
    }

    public int getOrientation() {
        return this.f1994d;
    }

    public boolean intersectWith(WidgetGroup widgetGroup) {
        for (int i2 = 0; i2 < this.f1991a.size(); i2++) {
            if (widgetGroup.contains((ConstraintWidget) this.f1991a.get(i2))) {
                return true;
            }
        }
        return false;
    }

    public boolean isAuthoritative() {
        return this.f1993c;
    }

    public int measureWrap(LinearSystem linearSystem, int i2) {
        if (this.f1991a.size() == 0) {
            return 0;
        }
        return solverMeasure(linearSystem, this.f1991a, i2);
    }

    public void moveTo(int i2, WidgetGroup widgetGroup) {
        Iterator it = this.f1991a.iterator();
        while (it.hasNext()) {
            ConstraintWidget constraintWidget = (ConstraintWidget) it.next();
            widgetGroup.add(constraintWidget);
            int id = widgetGroup.getId();
            if (i2 == 0) {
                constraintWidget.horizontalGroup = id;
            } else {
                constraintWidget.verticalGroup = id;
            }
        }
        this.moveTo = widgetGroup.f1992b;
    }

    public void setAuthoritative(boolean z) {
        this.f1993c = z;
    }

    public void setOrientation(int i2) {
        this.f1994d = i2;
    }

    public int size() {
        return this.f1991a.size();
    }

    public String toString() {
        Iterator it;
        String str = getOrientationString() + " [" + this.f1992b + "] <";
        while (this.f1991a.iterator().hasNext()) {
            str = str + " " + ((ConstraintWidget) it.next()).getDebugName();
        }
        return str + " >";
    }
}
