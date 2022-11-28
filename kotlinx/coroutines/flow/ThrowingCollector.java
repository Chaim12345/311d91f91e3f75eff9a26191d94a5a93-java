package kotlinx.coroutines.flow;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class ThrowingCollector implements FlowCollector<Object> {
    @JvmField
    @NotNull

    /* renamed from: e  reason: collision with root package name */
    public final Throwable f12238e;

    public ThrowingCollector(@NotNull Throwable th) {
        this.f12238e = th;
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    @Nullable
    public Object emit(@Nullable Object obj, @NotNull Continuation<? super Unit> continuation) {
        throw this.f12238e;
    }
}
