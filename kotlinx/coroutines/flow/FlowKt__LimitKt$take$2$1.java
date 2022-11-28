package kotlinx.coroutines.flow;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class FlowKt__LimitKt$take$2$1<T> implements FlowCollector {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Ref.IntRef f11869a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ int f11870b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ FlowCollector f11871c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FlowKt__LimitKt$take$2$1(Ref.IntRef intRef, int i2, FlowCollector flowCollector) {
        this.f11869a = intRef;
        this.f11870b = i2;
        this.f11871c = flowCollector;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0038  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object emit(T t2, @NotNull Continuation<? super Unit> continuation) {
        FlowKt__LimitKt$take$2$1$emit$1 flowKt__LimitKt$take$2$1$emit$1;
        Object coroutine_suspended;
        int i2;
        Object emitAbort$FlowKt__LimitKt;
        if (continuation instanceof FlowKt__LimitKt$take$2$1$emit$1) {
            flowKt__LimitKt$take$2$1$emit$1 = (FlowKt__LimitKt$take$2$1$emit$1) continuation;
            int i3 = flowKt__LimitKt$take$2$1$emit$1.f11874c;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                flowKt__LimitKt$take$2$1$emit$1.f11874c = i3 - Integer.MIN_VALUE;
                Object obj = flowKt__LimitKt$take$2$1$emit$1.f11872a;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = flowKt__LimitKt$take$2$1$emit$1.f11874c;
                if (i2 == 0) {
                    if (i2 == 1) {
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    } else if (i2 == 2) {
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    } else {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }
                ResultKt.throwOnFailure(obj);
                Ref.IntRef intRef = this.f11869a;
                int i4 = intRef.element + 1;
                intRef.element = i4;
                if (i4 < this.f11870b) {
                    FlowCollector flowCollector = this.f11871c;
                    flowKt__LimitKt$take$2$1$emit$1.f11874c = 1;
                    if (flowCollector.emit(t2, flowKt__LimitKt$take$2$1$emit$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return Unit.INSTANCE;
                }
                FlowCollector flowCollector2 = this.f11871c;
                flowKt__LimitKt$take$2$1$emit$1.f11874c = 2;
                emitAbort$FlowKt__LimitKt = FlowKt__LimitKt.emitAbort$FlowKt__LimitKt(flowCollector2, t2, flowKt__LimitKt$take$2$1$emit$1);
                if (emitAbort$FlowKt__LimitKt == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            }
        }
        flowKt__LimitKt$take$2$1$emit$1 = new FlowKt__LimitKt$take$2$1$emit$1(this, continuation);
        Object obj2 = flowKt__LimitKt$take$2$1$emit$1.f11872a;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = flowKt__LimitKt$take$2$1$emit$1.f11874c;
        if (i2 == 0) {
        }
    }
}
