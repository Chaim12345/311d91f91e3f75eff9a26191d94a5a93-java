package kotlinx.coroutines.flow;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class FlowKt__TransformKt$runningFold$1$1<T> implements FlowCollector {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Ref.ObjectRef f12082a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ Function3 f12083b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ FlowCollector f12084c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FlowKt__TransformKt$runningFold$1$1(Ref.ObjectRef objectRef, Function3 function3, FlowCollector flowCollector) {
        this.f12082a = objectRef;
        this.f12083b = function3;
        this.f12084c = flowCollector;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x006f A[RETURN] */
    @Override // kotlinx.coroutines.flow.FlowCollector
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object emit(T t2, @NotNull Continuation<? super Unit> continuation) {
        FlowKt__TransformKt$runningFold$1$1$emit$1 flowKt__TransformKt$runningFold$1$1$emit$1;
        Object coroutine_suspended;
        int i2;
        FlowKt__TransformKt$runningFold$1$1<T> flowKt__TransformKt$runningFold$1$1;
        Ref.ObjectRef objectRef;
        FlowCollector flowCollector;
        T t3;
        if (continuation instanceof FlowKt__TransformKt$runningFold$1$1$emit$1) {
            flowKt__TransformKt$runningFold$1$1$emit$1 = (FlowKt__TransformKt$runningFold$1$1$emit$1) continuation;
            int i3 = flowKt__TransformKt$runningFold$1$1$emit$1.f12089e;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                flowKt__TransformKt$runningFold$1$1$emit$1.f12089e = i3 - Integer.MIN_VALUE;
                T t4 = (T) flowKt__TransformKt$runningFold$1$1$emit$1.f12087c;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = flowKt__TransformKt$runningFold$1$1$emit$1.f12089e;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(t4);
                    Ref.ObjectRef objectRef2 = this.f12082a;
                    Function3 function3 = this.f12083b;
                    T t5 = objectRef2.element;
                    flowKt__TransformKt$runningFold$1$1$emit$1.f12085a = this;
                    flowKt__TransformKt$runningFold$1$1$emit$1.f12086b = objectRef2;
                    flowKt__TransformKt$runningFold$1$1$emit$1.f12089e = 1;
                    Object invoke = function3.invoke(t5, t2, flowKt__TransformKt$runningFold$1$1$emit$1);
                    if (invoke == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    flowKt__TransformKt$runningFold$1$1 = this;
                    t4 = invoke;
                    objectRef = objectRef2;
                } else if (i2 != 1) {
                    if (i2 == 2) {
                        ResultKt.throwOnFailure(t4);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    objectRef = (Ref.ObjectRef) flowKt__TransformKt$runningFold$1$1$emit$1.f12086b;
                    flowKt__TransformKt$runningFold$1$1 = (FlowKt__TransformKt$runningFold$1$1) flowKt__TransformKt$runningFold$1$1$emit$1.f12085a;
                    ResultKt.throwOnFailure(t4);
                }
                objectRef.element = t4;
                flowCollector = flowKt__TransformKt$runningFold$1$1.f12084c;
                t3 = flowKt__TransformKt$runningFold$1$1.f12082a.element;
                flowKt__TransformKt$runningFold$1$1$emit$1.f12085a = null;
                flowKt__TransformKt$runningFold$1$1$emit$1.f12086b = null;
                flowKt__TransformKt$runningFold$1$1$emit$1.f12089e = 2;
                if (flowCollector.emit(t3, flowKt__TransformKt$runningFold$1$1$emit$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            }
        }
        flowKt__TransformKt$runningFold$1$1$emit$1 = new FlowKt__TransformKt$runningFold$1$1$emit$1(this, continuation);
        T t42 = (T) flowKt__TransformKt$runningFold$1$1$emit$1.f12087c;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = flowKt__TransformKt$runningFold$1$1$emit$1.f12089e;
        if (i2 != 0) {
        }
        objectRef.element = t42;
        flowCollector = flowKt__TransformKt$runningFold$1$1.f12084c;
        t3 = flowKt__TransformKt$runningFold$1$1.f12082a.element;
        flowKt__TransformKt$runningFold$1$1$emit$1.f12085a = null;
        flowKt__TransformKt$runningFold$1$1$emit$1.f12086b = null;
        flowKt__TransformKt$runningFold$1$1$emit$1.f12089e = 2;
        if (flowCollector.emit(t3, flowKt__TransformKt$runningFold$1$1$emit$1) == coroutine_suspended) {
        }
        return Unit.INSTANCE;
    }
}
