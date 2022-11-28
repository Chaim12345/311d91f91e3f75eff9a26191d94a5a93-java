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
public final class FlowKt__ReduceKt$reduce$2<T> implements FlowCollector {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Ref.ObjectRef f11970a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ Function3 f11971b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FlowKt__ReduceKt$reduce$2(Ref.ObjectRef objectRef, Function3 function3) {
        this.f11970a = objectRef;
        this.f11971b = function3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    /* JADX WARN: Type inference failed for: r8v5 */
    @Override // kotlinx.coroutines.flow.FlowCollector
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object emit(T t2, @NotNull Continuation<? super Unit> continuation) {
        FlowKt__ReduceKt$reduce$2$emit$1 flowKt__ReduceKt$reduce$2$emit$1;
        Object coroutine_suspended;
        int i2;
        Ref.ObjectRef objectRef;
        Ref.ObjectRef objectRef2;
        if (continuation instanceof FlowKt__ReduceKt$reduce$2$emit$1) {
            flowKt__ReduceKt$reduce$2$emit$1 = (FlowKt__ReduceKt$reduce$2$emit$1) continuation;
            int i3 = flowKt__ReduceKt$reduce$2$emit$1.f11975d;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                flowKt__ReduceKt$reduce$2$emit$1.f11975d = i3 - Integer.MIN_VALUE;
                Object obj = flowKt__ReduceKt$reduce$2$emit$1.f11973b;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = flowKt__ReduceKt$reduce$2$emit$1.f11975d;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    objectRef = this.f11970a;
                    T t3 = objectRef.element;
                    if (t3 != NullSurrogateKt.NULL) {
                        Function3 function3 = this.f11971b;
                        flowKt__ReduceKt$reduce$2$emit$1.f11972a = objectRef;
                        flowKt__ReduceKt$reduce$2$emit$1.f11975d = 1;
                        Object invoke = function3.invoke(t3, t2, flowKt__ReduceKt$reduce$2$emit$1);
                        if (invoke == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        obj = invoke;
                        objectRef2 = objectRef;
                    }
                    objectRef.element = t2;
                    return Unit.INSTANCE;
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    objectRef2 = (Ref.ObjectRef) flowKt__ReduceKt$reduce$2$emit$1.f11972a;
                    ResultKt.throwOnFailure(obj);
                }
                T t4 = obj;
                objectRef = objectRef2;
                t2 = t4;
                objectRef.element = t2;
                return Unit.INSTANCE;
            }
        }
        flowKt__ReduceKt$reduce$2$emit$1 = new FlowKt__ReduceKt$reduce$2$emit$1(this, continuation);
        Object obj2 = flowKt__ReduceKt$reduce$2$emit$1.f11973b;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = flowKt__ReduceKt$reduce$2$emit$1.f11975d;
        if (i2 != 0) {
        }
        T t42 = obj2;
        objectRef = objectRef2;
        t2 = t42;
        objectRef.element = t2;
        return Unit.INSTANCE;
    }
}
