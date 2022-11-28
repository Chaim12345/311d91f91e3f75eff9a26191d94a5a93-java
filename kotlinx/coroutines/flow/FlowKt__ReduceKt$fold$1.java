package kotlinx.coroutines.flow;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ReduceKt", f = "Reduce.kt", i = {0}, l = {44}, m = "fold", n = {"accumulator"}, s = {"L$0"})
/* loaded from: classes3.dex */
public final class FlowKt__ReduceKt$fold$1<T, R> extends ContinuationImpl {

    /* renamed from: a  reason: collision with root package name */
    Object f11950a;

    /* renamed from: b  reason: collision with root package name */
    /* synthetic */ Object f11951b;

    /* renamed from: c  reason: collision with root package name */
    int f11952c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FlowKt__ReduceKt$fold$1(Continuation continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.f11951b = obj;
        this.f11952c |= Integer.MIN_VALUE;
        return FlowKt__ReduceKt.fold(null, null, null, this);
    }
}
