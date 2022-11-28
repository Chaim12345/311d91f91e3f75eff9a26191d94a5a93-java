package androidx.constraintlayout.core.state.helpers;

import androidx.constraintlayout.core.state.HelperReference;
import androidx.constraintlayout.core.state.State;
/* loaded from: classes.dex */
public class ChainReference extends HelperReference {
    protected float Z;
    protected State.Chain a0;

    public ChainReference(State state, State.Helper helper) {
        super(state, helper);
        this.Z = 0.5f;
        this.a0 = State.Chain.SPREAD;
    }

    @Override // androidx.constraintlayout.core.state.ConstraintReference
    public ChainReference bias(float f2) {
        this.Z = f2;
        return this;
    }

    public float getBias() {
        return this.Z;
    }

    public State.Chain getStyle() {
        return State.Chain.SPREAD;
    }

    public ChainReference style(State.Chain chain) {
        this.a0 = chain;
        return this;
    }
}
