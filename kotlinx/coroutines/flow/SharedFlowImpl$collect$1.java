package kotlinx.coroutines.flow;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "kotlinx.coroutines.flow.SharedFlowImpl", f = "SharedFlow.kt", i = {0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2}, l = {373, 380, 383}, m = "collect$suspendImpl", n = {"this", "collector", "slot", "this", "collector", "slot", "collectorJob", "this", "collector", "slot", "collectorJob"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3"})
/* loaded from: classes3.dex */
public final class SharedFlowImpl$collect$1 extends ContinuationImpl {

    /* renamed from: a  reason: collision with root package name */
    Object f12201a;

    /* renamed from: b  reason: collision with root package name */
    Object f12202b;

    /* renamed from: c  reason: collision with root package name */
    Object f12203c;

    /* renamed from: d  reason: collision with root package name */
    Object f12204d;

    /* renamed from: e  reason: collision with root package name */
    /* synthetic */ Object f12205e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ SharedFlowImpl f12206f;

    /* renamed from: g  reason: collision with root package name */
    int f12207g;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SharedFlowImpl$collect$1(SharedFlowImpl sharedFlowImpl, Continuation continuation) {
        super(continuation);
        this.f12206f = sharedFlowImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.f12205e = obj;
        this.f12207g |= Integer.MIN_VALUE;
        return SharedFlowImpl.e(this.f12206f, null, this);
    }
}
