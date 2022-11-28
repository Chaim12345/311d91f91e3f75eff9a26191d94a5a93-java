package kotlinx.coroutines.flow;

import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class SubscribedSharedFlow<T> implements SharedFlow<T> {
    @NotNull
    private final Function2<FlowCollector<? super T>, Continuation<? super Unit>, Object> action;
    @NotNull
    private final SharedFlow<T> sharedFlow;

    /* JADX WARN: Multi-variable type inference failed */
    public SubscribedSharedFlow(@NotNull SharedFlow<? extends T> sharedFlow, @NotNull Function2<? super FlowCollector<? super T>, ? super Continuation<? super Unit>, ? extends Object> function2) {
        this.sharedFlow = sharedFlow;
        this.action = function2;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0031  */
    @Override // kotlinx.coroutines.flow.SharedFlow, kotlinx.coroutines.flow.Flow
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object collect(@NotNull FlowCollector<? super T> flowCollector, @NotNull Continuation<?> continuation) {
        SubscribedSharedFlow$collect$1 subscribedSharedFlow$collect$1;
        Object coroutine_suspended;
        int i2;
        if (continuation instanceof SubscribedSharedFlow$collect$1) {
            subscribedSharedFlow$collect$1 = (SubscribedSharedFlow$collect$1) continuation;
            int i3 = subscribedSharedFlow$collect$1.f12237c;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                subscribedSharedFlow$collect$1.f12237c = i3 - Integer.MIN_VALUE;
                Object obj = subscribedSharedFlow$collect$1.f12235a;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = subscribedSharedFlow$collect$1.f12237c;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    SharedFlow<T> sharedFlow = this.sharedFlow;
                    SubscribedFlowCollector subscribedFlowCollector = new SubscribedFlowCollector(flowCollector, this.action);
                    subscribedSharedFlow$collect$1.f12237c = 1;
                    if (sharedFlow.collect(subscribedFlowCollector, subscribedSharedFlow$collect$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    ResultKt.throwOnFailure(obj);
                }
                throw new KotlinNothingValueException();
            }
        }
        subscribedSharedFlow$collect$1 = new SubscribedSharedFlow$collect$1(this, continuation);
        Object obj2 = subscribedSharedFlow$collect$1.f12235a;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = subscribedSharedFlow$collect$1.f12237c;
        if (i2 != 0) {
        }
        throw new KotlinNothingValueException();
    }

    @Override // kotlinx.coroutines.flow.SharedFlow
    @NotNull
    public List<T> getReplayCache() {
        return this.sharedFlow.getReplayCache();
    }
}
