package kotlinx.coroutines.flow;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__CountKt", f = "Count.kt", i = {0}, l = {30}, m = "count", n = {"i"}, s = {"L$0"})
/* loaded from: classes3.dex */
public final class FlowKt__CountKt$count$3<T> extends ContinuationImpl {

    /* renamed from: a  reason: collision with root package name */
    Object f11681a;

    /* renamed from: b  reason: collision with root package name */
    /* synthetic */ Object f11682b;

    /* renamed from: c  reason: collision with root package name */
    int f11683c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FlowKt__CountKt$count$3(Continuation continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.f11682b = obj;
        this.f11683c |= Integer.MIN_VALUE;
        return FlowKt.count(null, null, this);
    }
}
