package androidx.constraintlayout.core.state;

import androidx.constraintlayout.core.state.State;
import androidx.constraintlayout.core.state.helpers.Facade;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.HelperWidget;
import java.util.ArrayList;
import java.util.Collections;
/* loaded from: classes.dex */
public class HelperReference extends ConstraintReference implements Facade {
    protected final State W;
    final State.Helper X;
    protected ArrayList Y;
    private HelperWidget mHelperWidget;

    public HelperReference(State state, State.Helper helper) {
        super(state);
        this.Y = new ArrayList();
        this.W = state;
        this.X = helper;
    }

    public HelperReference add(Object... objArr) {
        Collections.addAll(this.Y, objArr);
        return this;
    }

    @Override // androidx.constraintlayout.core.state.ConstraintReference, androidx.constraintlayout.core.state.Reference
    public void apply() {
    }

    @Override // androidx.constraintlayout.core.state.ConstraintReference, androidx.constraintlayout.core.state.Reference
    public ConstraintWidget getConstraintWidget() {
        return getHelperWidget();
    }

    public HelperWidget getHelperWidget() {
        return this.mHelperWidget;
    }

    public State.Helper getType() {
        return this.X;
    }

    public void setHelperWidget(HelperWidget helperWidget) {
        this.mHelperWidget = helperWidget;
    }
}
