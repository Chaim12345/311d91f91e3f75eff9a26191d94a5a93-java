package kotlinx.coroutines.flow;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlinx.coroutines.JobKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class CancellableFlowImpl$collect$2<T> implements FlowCollector {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ FlowCollector f11578a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CancellableFlowImpl$collect$2(FlowCollector flowCollector) {
        this.f11578a = flowCollector;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0031  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object emit(T t2, @NotNull Continuation<? super Unit> continuation) {
        CancellableFlowImpl$collect$2$emit$1 cancellableFlowImpl$collect$2$emit$1;
        Object coroutine_suspended;
        int i2;
        if (continuation instanceof CancellableFlowImpl$collect$2$emit$1) {
            cancellableFlowImpl$collect$2$emit$1 = (CancellableFlowImpl$collect$2$emit$1) continuation;
            int i3 = cancellableFlowImpl$collect$2$emit$1.f11581c;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                cancellableFlowImpl$collect$2$emit$1.f11581c = i3 - Integer.MIN_VALUE;
                Object obj = cancellableFlowImpl$collect$2$emit$1.f11579a;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = cancellableFlowImpl$collect$2$emit$1.f11581c;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    JobKt.ensureActive(cancellableFlowImpl$collect$2$emit$1.getContext());
                    FlowCollector flowCollector = this.f11578a;
                    cancellableFlowImpl$collect$2$emit$1.f11581c = 1;
                    if (flowCollector.emit(t2, cancellableFlowImpl$collect$2$emit$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }
        cancellableFlowImpl$collect$2$emit$1 = new CancellableFlowImpl$collect$2$emit$1(this, continuation);
        Object obj2 = cancellableFlowImpl$collect$2$emit$1.f11579a;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = cancellableFlowImpl$collect$2$emit$1.f11581c;
        if (i2 != 0) {
        }
        return Unit.INSTANCE;
    }
}
