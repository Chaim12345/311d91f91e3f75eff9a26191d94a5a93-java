package androidx.constraintlayout.core.state.helpers;

import androidx.constraintlayout.core.state.ConstraintReference;
import androidx.constraintlayout.core.state.State;
import java.util.Iterator;
/* loaded from: classes.dex */
public class HorizontalChainReference extends ChainReference {

    /* renamed from: androidx.constraintlayout.core.state.helpers.HorizontalChainReference$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f1927a;

        static {
            int[] iArr = new int[State.Chain.values().length];
            f1927a = iArr;
            try {
                iArr[State.Chain.SPREAD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1927a[State.Chain.SPREAD_INSIDE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1927a[State.Chain.PACKED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public HorizontalChainReference(State state) {
        super(state, State.Helper.HORIZONTAL_CHAIN);
    }

    @Override // androidx.constraintlayout.core.state.HelperReference, androidx.constraintlayout.core.state.ConstraintReference, androidx.constraintlayout.core.state.Reference
    public void apply() {
        ConstraintReference endToEnd;
        ConstraintReference startToEnd;
        Iterator it = this.Y.iterator();
        while (it.hasNext()) {
            this.W.constraints(it.next()).clearHorizontal();
        }
        Iterator it2 = this.Y.iterator();
        ConstraintReference constraintReference = null;
        ConstraintReference constraintReference2 = null;
        while (it2.hasNext()) {
            ConstraintReference constraints = this.W.constraints(it2.next());
            if (constraintReference2 == null) {
                Object obj = this.J;
                if (obj != null) {
                    startToEnd = constraints.startToStart(obj);
                } else {
                    Object obj2 = this.K;
                    if (obj2 != null) {
                        startToEnd = constraints.startToEnd(obj2);
                    } else {
                        constraints.startToStart(State.PARENT);
                        constraintReference2 = constraints;
                    }
                }
                startToEnd.margin(this.f1882j);
                constraintReference2 = constraints;
            }
            if (constraintReference != null) {
                constraintReference.endToStart(constraints.getKey());
                constraints.startToEnd(constraintReference.getKey());
            }
            constraintReference = constraints;
        }
        if (constraintReference != null) {
            Object obj3 = this.L;
            if (obj3 != null) {
                endToEnd = constraintReference.endToStart(obj3);
            } else {
                Object obj4 = this.M;
                if (obj4 != null) {
                    endToEnd = constraintReference.endToEnd(obj4);
                } else {
                    constraintReference.endToEnd(State.PARENT);
                }
            }
            endToEnd.margin(this.f1883k);
        }
        if (constraintReference2 == null) {
            return;
        }
        float f2 = this.Z;
        if (f2 != 0.5f) {
            constraintReference2.horizontalBias(f2);
        }
        int i2 = AnonymousClass1.f1927a[this.a0.ordinal()];
        if (i2 == 1) {
            constraintReference2.setHorizontalChainStyle(0);
        } else if (i2 == 2) {
            constraintReference2.setHorizontalChainStyle(1);
        } else if (i2 != 3) {
        } else {
            constraintReference2.setHorizontalChainStyle(2);
        }
    }
}
