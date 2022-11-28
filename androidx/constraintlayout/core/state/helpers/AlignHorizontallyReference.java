package androidx.constraintlayout.core.state.helpers;

import androidx.constraintlayout.core.state.ConstraintReference;
import androidx.constraintlayout.core.state.HelperReference;
import androidx.constraintlayout.core.state.State;
import java.util.Iterator;
/* loaded from: classes.dex */
public class AlignHorizontallyReference extends HelperReference {
    private float mBias;

    public AlignHorizontallyReference(State state) {
        super(state, State.Helper.ALIGN_VERTICALLY);
        this.mBias = 0.5f;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0046 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0006 A[SYNTHETIC] */
    @Override // androidx.constraintlayout.core.state.HelperReference, androidx.constraintlayout.core.state.ConstraintReference, androidx.constraintlayout.core.state.Reference
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void apply() {
        Object obj;
        float f2;
        Iterator it = this.Y.iterator();
        while (it.hasNext()) {
            ConstraintReference constraints = this.W.constraints(it.next());
            constraints.clearHorizontal();
            Object obj2 = this.J;
            if (obj2 == null) {
                Object obj3 = this.K;
                if (obj3 != null) {
                    constraints.startToEnd(obj3);
                    obj = this.L;
                    if (obj == null) {
                        constraints.endToStart(obj);
                    } else {
                        Object obj4 = this.M;
                        if (obj4 == null) {
                            obj4 = State.PARENT;
                        }
                        constraints.endToEnd(obj4);
                    }
                    f2 = this.mBias;
                    if (f2 == 0.5f) {
                        constraints.horizontalBias(f2);
                    }
                } else {
                    obj2 = State.PARENT;
                }
            }
            constraints.startToStart(obj2);
            obj = this.L;
            if (obj == null) {
            }
            f2 = this.mBias;
            if (f2 == 0.5f) {
            }
        }
    }
}
