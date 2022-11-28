package kotlinx.coroutines.flow;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__CountKt", f = "Count.kt", i = {0}, l = {18}, m = "count", n = {"i"}, s = {"L$0"})
/* loaded from: classes3.dex */
public final class FlowKt__CountKt$count$1<T> extends ContinuationImpl {

    /* renamed from: a  reason: collision with root package name */
    Object f11677a;

    /* renamed from: b  reason: collision with root package name */
    /* synthetic */ Object f11678b;

    /* renamed from: c  reason: collision with root package name */
    int f11679c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FlowKt__CountKt$count$1(Continuation continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.f11678b = obj;
        this.f11679c |= Integer.MIN_VALUE;
        return FlowKt.count(null, this);
    }
}
