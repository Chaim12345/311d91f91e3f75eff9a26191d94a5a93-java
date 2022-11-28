package kotlinx.coroutines.flow;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "kotlinx.coroutines.flow.StateFlowImpl", f = "StateFlow.kt", i = {0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2}, l = {386, 398, 403}, m = "collect", n = {"this", "collector", "slot", "this", "collector", "slot", "collectorJob", "newState", "this", "collector", "slot", "collectorJob", "oldState"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4"})
/* loaded from: classes3.dex */
public final class StateFlowImpl$collect$1 extends ContinuationImpl {

    /* renamed from: a  reason: collision with root package name */
    Object f12221a;

    /* renamed from: b  reason: collision with root package name */
    Object f12222b;

    /* renamed from: c  reason: collision with root package name */
    Object f12223c;

    /* renamed from: d  reason: collision with root package name */
    Object f12224d;

    /* renamed from: e  reason: collision with root package name */
    Object f12225e;

    /* renamed from: f  reason: collision with root package name */
    /* synthetic */ Object f12226f;

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ StateFlowImpl f12227g;

    /* renamed from: h  reason: collision with root package name */
    int f12228h;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StateFlowImpl$collect$1(StateFlowImpl stateFlowImpl, Continuation continuation) {
        super(continuation);
        this.f12227g = stateFlowImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.f12226f = obj;
        this.f12228h |= Integer.MIN_VALUE;
        return this.f12227g.collect(null, this);
    }
}
