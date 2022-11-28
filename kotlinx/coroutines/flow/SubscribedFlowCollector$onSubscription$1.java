package kotlinx.coroutines.flow;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.apache.http.HttpStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "kotlinx.coroutines.flow.SubscribedFlowCollector", f = "Share.kt", i = {0, 0}, l = {HttpStatus.SC_INSUFFICIENT_SPACE_ON_RESOURCE, 423}, m = "onSubscription", n = {"this", "safeCollector"}, s = {"L$0", "L$1"})
/* loaded from: classes3.dex */
public final class SubscribedFlowCollector$onSubscription$1 extends ContinuationImpl {

    /* renamed from: a  reason: collision with root package name */
    Object f12230a;

    /* renamed from: b  reason: collision with root package name */
    Object f12231b;

    /* renamed from: c  reason: collision with root package name */
    /* synthetic */ Object f12232c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ SubscribedFlowCollector f12233d;

    /* renamed from: e  reason: collision with root package name */
    int f12234e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SubscribedFlowCollector$onSubscription$1(SubscribedFlowCollector subscribedFlowCollector, Continuation continuation) {
        super(continuation);
        this.f12233d = subscribedFlowCollector;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.f12232c = obj;
        this.f12234e |= Integer.MIN_VALUE;
        return this.f12233d.onSubscription(this);
    }
}
