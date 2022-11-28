package kotlinx.coroutines.flow;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class FlowKt__TransformKt$runningReduce$1$1<T> implements FlowCollector {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Ref.ObjectRef f12090a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ Function3 f12091b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ FlowCollector f12092c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FlowKt__TransformKt$runningReduce$1$1(Ref.ObjectRef objectRef, Function3 function3, FlowCollector flowCollector) {
        this.f12090a = objectRef;
        this.f12091b = function3;
        this.f12092c = flowCollector;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0078 A[RETURN] */
    /* JADX WARN: Type inference failed for: r9v7 */
    @Override // kotlinx.coroutines.flow.FlowCollector
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object emit(T t2, @NotNull Continuation<? super Unit> continuation) {
        FlowKt__TransformKt$runningReduce$1$1$emit$1 flowKt__TransformKt$runningReduce$1$1$emit$1;
        Object coroutine_suspended;
        int i2;
        Ref.ObjectRef objectRef;
        FlowKt__TransformKt$runningReduce$1$1<T> flowKt__TransformKt$runningReduce$1$1;
        Ref.ObjectRef objectRef2;
        FlowCollector flowCollector;
        T t3;
        if (continuation instanceof FlowKt__TransformKt$runningReduce$1$1$emit$1) {
            flowKt__TransformKt$runningReduce$1$1$emit$1 = (FlowKt__TransformKt$runningReduce$1$1$emit$1) continuation;
            int i3 = flowKt__TransformKt$runningReduce$1$1$emit$1.f12097e;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                flowKt__TransformKt$runningReduce$1$1$emit$1.f12097e = i3 - Integer.MIN_VALUE;
                Object obj = flowKt__TransformKt$runningReduce$1$1$emit$1.f12095c;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = flowKt__TransformKt$runningReduce$1$1$emit$1.f12097e;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    objectRef = this.f12090a;
                    T t4 = objectRef.element;
                    if (t4 == NullSurrogateKt.NULL) {
                        flowKt__TransformKt$runningReduce$1$1 = this;
                        objectRef.element = t2;
                        flowCollector = flowKt__TransformKt$runningReduce$1$1.f12092c;
                        t3 = flowKt__TransformKt$runningReduce$1$1.f12090a.element;
                        flowKt__TransformKt$runningReduce$1$1$emit$1.f12093a = null;
                        flowKt__TransformKt$runningReduce$1$1$emit$1.f12094b = null;
                        flowKt__TransformKt$runningReduce$1$1$emit$1.f12097e = 2;
                        if (flowCollector.emit(t3, flowKt__TransformKt$runningReduce$1$1$emit$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        return Unit.INSTANCE;
                    }
                    Function3 function3 = this.f12091b;
                    flowKt__TransformKt$runningReduce$1$1$emit$1.f12093a = this;
                    flowKt__TransformKt$runningReduce$1$1$emit$1.f12094b = objectRef;
                    flowKt__TransformKt$runningReduce$1$1$emit$1.f12097e = 1;
                    Object invoke = function3.invoke(t4, t2, flowKt__TransformKt$runningReduce$1$1$emit$1);
                    if (invoke == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    flowKt__TransformKt$runningReduce$1$1 = this;
                    obj = invoke;
                    objectRef2 = objectRef;
                } else if (i2 != 1) {
                    if (i2 == 2) {
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    objectRef2 = (Ref.ObjectRef) flowKt__TransformKt$runningReduce$1$1$emit$1.f12094b;
                    flowKt__TransformKt$runningReduce$1$1 = (FlowKt__TransformKt$runningReduce$1$1) flowKt__TransformKt$runningReduce$1$1$emit$1.f12093a;
                    ResultKt.throwOnFailure(obj);
                }
                T t5 = obj;
                objectRef = objectRef2;
                t2 = t5;
                objectRef.element = t2;
                flowCollector = flowKt__TransformKt$runningReduce$1$1.f12092c;
                t3 = flowKt__TransformKt$runningReduce$1$1.f12090a.element;
                flowKt__TransformKt$runningReduce$1$1$emit$1.f12093a = null;
                flowKt__TransformKt$runningReduce$1$1$emit$1.f12094b = null;
                flowKt__TransformKt$runningReduce$1$1$emit$1.f12097e = 2;
                if (flowCollector.emit(t3, flowKt__TransformKt$runningReduce$1$1$emit$1) == coroutine_suspended) {
                }
                return Unit.INSTANCE;
            }
        }
        flowKt__TransformKt$runningReduce$1$1$emit$1 = new FlowKt__TransformKt$runningReduce$1$1$emit$1(this, continuation);
        Object obj2 = flowKt__TransformKt$runningReduce$1$1$emit$1.f12095c;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = flowKt__TransformKt$runningReduce$1$1$emit$1.f12097e;
        if (i2 != 0) {
        }
        T t52 = obj2;
        objectRef = objectRef2;
        t2 = t52;
        objectRef.element = t2;
        flowCollector = flowKt__TransformKt$runningReduce$1$1.f12092c;
        t3 = flowKt__TransformKt$runningReduce$1$1.f12090a.element;
        flowKt__TransformKt$runningReduce$1$1$emit$1.f12093a = null;
        flowKt__TransformKt$runningReduce$1$1$emit$1.f12094b = null;
        flowKt__TransformKt$runningReduce$1$1$emit$1.f12097e = 2;
        if (flowCollector.emit(t3, flowKt__TransformKt$runningReduce$1$1$emit$1) == coroutine_suspended) {
        }
        return Unit.INSTANCE;
    }
}
