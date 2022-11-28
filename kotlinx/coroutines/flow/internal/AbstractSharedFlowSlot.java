package kotlinx.coroutines.flow.internal;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public abstract class AbstractSharedFlowSlot<F> {
    public abstract boolean allocateLocked(F f2);

    @NotNull
    public abstract Continuation<Unit>[] freeLocked(F f2);
}
