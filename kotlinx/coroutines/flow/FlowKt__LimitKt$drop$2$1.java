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
public final class FlowKt__LimitKt$drop$2$1<T> implements FlowCollector {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Ref.IntRef f11852a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ int f11853b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ FlowCollector f11854c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FlowKt__LimitKt$drop$2$1(Ref.IntRef intRef, int i2, FlowCollector flowCollector) {
        this.f11852a = intRef;
        this.f11853b = i2;
        this.f11854c = flowCollector;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0031  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object emit(T t2, @NotNull Continuation<? super Unit> continuation) {
        FlowKt__LimitKt$drop$2$1$emit$1 flowKt__LimitKt$drop$2$1$emit$1;
        Object coroutine_suspended;
        int i2;
        if (continuation instanceof FlowKt__LimitKt$drop$2$1$emit$1) {
            flowKt__LimitKt$drop$2$1$emit$1 = (FlowKt__LimitKt$drop$2$1$emit$1) continuation;
            int i3 = flowKt__LimitKt$drop$2$1$emit$1.f11857c;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                flowKt__LimitKt$drop$2$1$emit$1.f11857c = i3 - Integer.MIN_VALUE;
                Object obj = flowKt__LimitKt$drop$2$1$emit$1.f11855a;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = flowKt__LimitKt$drop$2$1$emit$1.f11857c;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    Ref.IntRef intRef = this.f11852a;
                    int i4 = intRef.element;
                    if (i4 < this.f11853b) {
                        intRef.element = i4 + 1;
                        return Unit.INSTANCE;
                    }
                    FlowCollector flowCollector = this.f11854c;
                    flowKt__LimitKt$drop$2$1$emit$1.f11857c = 1;
                    if (flowCollector.emit(t2, flowKt__LimitKt$drop$2$1$emit$1) == coroutine_suspended) {
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
        flowKt__LimitKt$drop$2$1$emit$1 = new FlowKt__LimitKt$drop$2$1$emit$1(this, continuation);
        Object obj2 = flowKt__LimitKt$drop$2$1$emit$1.f11855a;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = flowKt__LimitKt$drop$2$1$emit$1.f11857c;
        if (i2 != 0) {
        }
        return Unit.INSTANCE;
    }
}
