package kotlinx.coroutines.flow;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__CountKt$count$4", f = "Count.kt", i = {0}, l = {31}, m = "emit", n = {"this"}, s = {"L$0"})
/* loaded from: classes3.dex */
public final class FlowKt__CountKt$count$4$emit$1 extends ContinuationImpl {

    /* renamed from: a  reason: collision with root package name */
    Object f11686a;

    /* renamed from: b  reason: collision with root package name */
    /* synthetic */ Object f11687b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ FlowKt__CountKt$count$4 f11688c;

    /* renamed from: d  reason: collision with root package name */
    int f11689d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowKt__CountKt$count$4$emit$1(FlowKt__CountKt$count$4 flowKt__CountKt$count$4, Continuation continuation) {
        super(continuation);
        this.f11688c = flowKt__CountKt$count$4;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.f11687b = obj;
        this.f11689d |= Integer.MIN_VALUE;
        return this.f11688c.emit(null, this);
    }
}
