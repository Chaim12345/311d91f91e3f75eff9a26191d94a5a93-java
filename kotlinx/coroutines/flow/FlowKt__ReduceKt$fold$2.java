package kotlinx.coroutines.flow;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class FlowKt__ReduceKt$fold$2<T> implements FlowCollector {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Ref.ObjectRef f11953a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ Function3 f11954b;

    public FlowKt__ReduceKt$fold$2(Ref.ObjectRef<R> objectRef, Function3<? super R, ? super T, ? super Continuation<? super R>, ? extends Object> function3) {
        this.f11953a = objectRef;
        this.f11954b = function3;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object emit(T t2, @NotNull Continuation<? super Unit> continuation) {
        FlowKt__ReduceKt$fold$2$emit$1 flowKt__ReduceKt$fold$2$emit$1;
        Object coroutine_suspended;
        int i2;
        Ref.ObjectRef objectRef;
        if (continuation instanceof FlowKt__ReduceKt$fold$2$emit$1) {
            flowKt__ReduceKt$fold$2$emit$1 = (FlowKt__ReduceKt$fold$2$emit$1) continuation;
            int i3 = flowKt__ReduceKt$fold$2$emit$1.f11958d;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                flowKt__ReduceKt$fold$2$emit$1.f11958d = i3 - Integer.MIN_VALUE;
                T t3 = (T) flowKt__ReduceKt$fold$2$emit$1.f11956b;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = flowKt__ReduceKt$fold$2$emit$1.f11958d;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(t3);
                    Ref.ObjectRef objectRef2 = this.f11953a;
                    Function3 function3 = this.f11954b;
                    T t4 = objectRef2.element;
                    flowKt__ReduceKt$fold$2$emit$1.f11955a = objectRef2;
                    flowKt__ReduceKt$fold$2$emit$1.f11958d = 1;
                    Object invoke = function3.invoke(t4, t2, flowKt__ReduceKt$fold$2$emit$1);
                    if (invoke == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    t3 = invoke;
                    objectRef = objectRef2;
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    objectRef = (Ref.ObjectRef) flowKt__ReduceKt$fold$2$emit$1.f11955a;
                    ResultKt.throwOnFailure(t3);
                }
                objectRef.element = t3;
                return Unit.INSTANCE;
            }
        }
        flowKt__ReduceKt$fold$2$emit$1 = new FlowKt__ReduceKt$fold$2$emit$1(this, continuation);
        T t32 = (T) flowKt__ReduceKt$fold$2$emit$1.f11956b;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = flowKt__ReduceKt$fold$2$emit$1.f11958d;
        if (i2 != 0) {
        }
        objectRef.element = t32;
        return Unit.INSTANCE;
    }

    @Nullable
    public final Object emit$$forInline(T t2, @NotNull Continuation<? super Unit> continuation) {
        InlineMarker.mark(4);
        new FlowKt__ReduceKt$fold$2$emit$1(this, continuation);
        InlineMarker.mark(5);
        Ref.ObjectRef objectRef = this.f11953a;
        objectRef.element = (T) this.f11954b.invoke(objectRef.element, t2, continuation);
        return Unit.INSTANCE;
    }
}
