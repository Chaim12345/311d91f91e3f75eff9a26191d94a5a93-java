package kotlinx.coroutines.flow;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class FlowKt__CountKt$count$4<T> implements FlowCollector {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Function2 f11684a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ Ref.IntRef f11685b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FlowKt__CountKt$count$4(Function2 function2, Ref.IntRef intRef) {
        this.f11684a = function2;
        this.f11685b = intRef;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x004e  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object emit(T t2, @NotNull Continuation<? super Unit> continuation) {
        FlowKt__CountKt$count$4$emit$1 flowKt__CountKt$count$4$emit$1;
        Object obj;
        Object coroutine_suspended;
        int i2;
        FlowKt__CountKt$count$4<T> flowKt__CountKt$count$4;
        if (continuation instanceof FlowKt__CountKt$count$4$emit$1) {
            flowKt__CountKt$count$4$emit$1 = (FlowKt__CountKt$count$4$emit$1) continuation;
            int i3 = flowKt__CountKt$count$4$emit$1.f11689d;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                flowKt__CountKt$count$4$emit$1.f11689d = i3 - Integer.MIN_VALUE;
                obj = flowKt__CountKt$count$4$emit$1.f11687b;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = flowKt__CountKt$count$4$emit$1.f11689d;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    Function2 function2 = this.f11684a;
                    flowKt__CountKt$count$4$emit$1.f11686a = this;
                    flowKt__CountKt$count$4$emit$1.f11689d = 1;
                    obj = function2.invoke(t2, flowKt__CountKt$count$4$emit$1);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    flowKt__CountKt$count$4 = this;
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    flowKt__CountKt$count$4 = (FlowKt__CountKt$count$4) flowKt__CountKt$count$4$emit$1.f11686a;
                    ResultKt.throwOnFailure(obj);
                }
                if (((Boolean) obj).booleanValue()) {
                    flowKt__CountKt$count$4.f11685b.element++;
                }
                return Unit.INSTANCE;
            }
        }
        flowKt__CountKt$count$4$emit$1 = new FlowKt__CountKt$count$4$emit$1(this, continuation);
        obj = flowKt__CountKt$count$4$emit$1.f11687b;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = flowKt__CountKt$count$4$emit$1.f11689d;
        if (i2 != 0) {
        }
        if (((Boolean) obj).booleanValue()) {
        }
        return Unit.INSTANCE;
    }
}
