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
public final class FlowKt__ErrorsKt$catchImpl$2<T> implements FlowCollector {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ FlowCollector f11803a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ Ref.ObjectRef f11804b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FlowKt__ErrorsKt$catchImpl$2(FlowCollector flowCollector, Ref.ObjectRef objectRef) {
        this.f11803a = flowCollector;
        this.f11804b = objectRef;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0037  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object emit(T t2, @NotNull Continuation<? super Unit> continuation) {
        FlowKt__ErrorsKt$catchImpl$2$emit$1 flowKt__ErrorsKt$catchImpl$2$emit$1;
        Object coroutine_suspended;
        int i2;
        FlowKt__ErrorsKt$catchImpl$2<T> flowKt__ErrorsKt$catchImpl$2;
        if (continuation instanceof FlowKt__ErrorsKt$catchImpl$2$emit$1) {
            flowKt__ErrorsKt$catchImpl$2$emit$1 = (FlowKt__ErrorsKt$catchImpl$2$emit$1) continuation;
            int i3 = flowKt__ErrorsKt$catchImpl$2$emit$1.f11808d;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                flowKt__ErrorsKt$catchImpl$2$emit$1.f11808d = i3 - Integer.MIN_VALUE;
                Object obj = flowKt__ErrorsKt$catchImpl$2$emit$1.f11806b;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = flowKt__ErrorsKt$catchImpl$2$emit$1.f11808d;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    try {
                        FlowCollector flowCollector = this.f11803a;
                        flowKt__ErrorsKt$catchImpl$2$emit$1.f11805a = this;
                        flowKt__ErrorsKt$catchImpl$2$emit$1.f11808d = 1;
                        if (flowCollector.emit(t2, flowKt__ErrorsKt$catchImpl$2$emit$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } catch (Throwable t3) {
                        t3 = th;
                        flowKt__ErrorsKt$catchImpl$2 = this;
                        flowKt__ErrorsKt$catchImpl$2.f11804b.element = t3;
                        throw ((Throwable) t3);
                    }
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    flowKt__ErrorsKt$catchImpl$2 = (FlowKt__ErrorsKt$catchImpl$2) flowKt__ErrorsKt$catchImpl$2$emit$1.f11805a;
                    try {
                        ResultKt.throwOnFailure(obj);
                    } catch (Throwable t32) {
                        t32 = th;
                        flowKt__ErrorsKt$catchImpl$2.f11804b.element = t32;
                        throw ((Throwable) t32);
                    }
                }
                return Unit.INSTANCE;
            }
        }
        flowKt__ErrorsKt$catchImpl$2$emit$1 = new FlowKt__ErrorsKt$catchImpl$2$emit$1(this, continuation);
        Object obj2 = flowKt__ErrorsKt$catchImpl$2$emit$1.f11806b;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = flowKt__ErrorsKt$catchImpl$2$emit$1.f11808d;
        if (i2 != 0) {
        }
        return Unit.INSTANCE;
    }
}
