package kotlinx.coroutines.flow;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__TransformKt$runningReduce$1$1", f = "Transform.kt", i = {0}, l = {125, 127}, m = "emit", n = {"this"}, s = {"L$0"})
/* loaded from: classes3.dex */
public final class FlowKt__TransformKt$runningReduce$1$1$emit$1 extends ContinuationImpl {

    /* renamed from: a  reason: collision with root package name */
    Object f12093a;

    /* renamed from: b  reason: collision with root package name */
    Object f12094b;

    /* renamed from: c  reason: collision with root package name */
    /* synthetic */ Object f12095c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ FlowKt__TransformKt$runningReduce$1$1 f12096d;

    /* renamed from: e  reason: collision with root package name */
    int f12097e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowKt__TransformKt$runningReduce$1$1$emit$1(FlowKt__TransformKt$runningReduce$1$1 flowKt__TransformKt$runningReduce$1$1, Continuation continuation) {
        super(continuation);
        this.f12096d = flowKt__TransformKt$runningReduce$1$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.f12095c = obj;
        this.f12097e |= Integer.MIN_VALUE;
        return this.f12096d.emit(null, this);
    }
}
