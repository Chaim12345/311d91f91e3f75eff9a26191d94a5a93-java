package kotlinx.coroutines.flow;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.internal.SafeCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class SubscribedFlowCollector<T> implements FlowCollector<T> {
    @NotNull
    private final Function2<FlowCollector<? super T>, Continuation<? super Unit>, Object> action;
    @NotNull
    private final FlowCollector<T> collector;

    /* JADX WARN: Multi-variable type inference failed */
    public SubscribedFlowCollector(@NotNull FlowCollector<? super T> flowCollector, @NotNull Function2<? super FlowCollector<? super T>, ? super Continuation<? super Unit>, ? extends Object> function2) {
        this.collector = flowCollector;
        this.action = function2;
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    @Nullable
    public Object emit(T t2, @NotNull Continuation<? super Unit> continuation) {
        return this.collector.emit(t2, continuation);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x007a  */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r2v1, types: [kotlinx.coroutines.flow.internal.SafeCollector] */
    /* JADX WARN: Type inference failed for: r2v4, types: [boolean] */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object onSubscription(@NotNull Continuation<? super Unit> continuation) {
        SubscribedFlowCollector$onSubscription$1 subscribedFlowCollector$onSubscription$1;
        Object coroutine_suspended;
        ?? r2;
        SafeCollector safeCollector;
        SubscribedFlowCollector<T> subscribedFlowCollector;
        try {
            if (continuation instanceof SubscribedFlowCollector$onSubscription$1) {
                subscribedFlowCollector$onSubscription$1 = (SubscribedFlowCollector$onSubscription$1) continuation;
                int i2 = subscribedFlowCollector$onSubscription$1.f12234e;
                if ((i2 & Integer.MIN_VALUE) != 0) {
                    subscribedFlowCollector$onSubscription$1.f12234e = i2 - Integer.MIN_VALUE;
                    Object obj = subscribedFlowCollector$onSubscription$1.f12232c;
                    coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    r2 = subscribedFlowCollector$onSubscription$1.f12234e;
                    if (r2 != 0) {
                        ResultKt.throwOnFailure(obj);
                        safeCollector = new SafeCollector(this.collector, subscribedFlowCollector$onSubscription$1.getContext());
                        Function2<FlowCollector<? super T>, Continuation<? super Unit>, Object> function2 = this.action;
                        subscribedFlowCollector$onSubscription$1.f12230a = this;
                        subscribedFlowCollector$onSubscription$1.f12231b = safeCollector;
                        subscribedFlowCollector$onSubscription$1.f12234e = 1;
                        if (function2.invoke(safeCollector, subscribedFlowCollector$onSubscription$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        subscribedFlowCollector = this;
                    } else if (r2 != 1) {
                        if (r2 == 2) {
                            ResultKt.throwOnFailure(obj);
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    } else {
                        safeCollector = (SafeCollector) subscribedFlowCollector$onSubscription$1.f12231b;
                        subscribedFlowCollector = (SubscribedFlowCollector) subscribedFlowCollector$onSubscription$1.f12230a;
                        ResultKt.throwOnFailure(obj);
                    }
                    safeCollector.releaseIntercepted();
                    FlowCollector<T> flowCollector = subscribedFlowCollector.collector;
                    r2 = flowCollector instanceof SubscribedFlowCollector;
                    if (r2 == 0) {
                        subscribedFlowCollector$onSubscription$1.f12230a = null;
                        subscribedFlowCollector$onSubscription$1.f12231b = null;
                        subscribedFlowCollector$onSubscription$1.f12234e = 2;
                        if (((SubscribedFlowCollector) flowCollector).onSubscription(subscribedFlowCollector$onSubscription$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        return Unit.INSTANCE;
                    }
                    return Unit.INSTANCE;
                }
            }
            if (r2 != 0) {
            }
            safeCollector.releaseIntercepted();
            FlowCollector<T> flowCollector2 = subscribedFlowCollector.collector;
            r2 = flowCollector2 instanceof SubscribedFlowCollector;
            if (r2 == 0) {
            }
        } catch (Throwable th) {
            r2.releaseIntercepted();
            throw th;
        }
        subscribedFlowCollector$onSubscription$1 = new SubscribedFlowCollector$onSubscription$1(this, continuation);
        Object obj2 = subscribedFlowCollector$onSubscription$1.f12232c;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = subscribedFlowCollector$onSubscription$1.f12234e;
    }
}
