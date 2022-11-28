package kotlinx.coroutines.flow;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.InlineMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class FlowKt__EmittersKt$unsafeTransform$1$1<T> implements FlowCollector {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Function3 f11778a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ FlowCollector f11779b;

    public FlowKt__EmittersKt$unsafeTransform$1$1(Function3<? super FlowCollector<? super R>, ? super T, ? super Continuation<? super Unit>, ? extends Object> function3, FlowCollector<? super R> flowCollector) {
        this.f11778a = function3;
        this.f11779b = flowCollector;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0031  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object emit(T t2, @NotNull Continuation<? super Unit> continuation) {
        FlowKt__EmittersKt$unsafeTransform$1$1$emit$1 flowKt__EmittersKt$unsafeTransform$1$1$emit$1;
        Object coroutine_suspended;
        int i2;
        if (continuation instanceof FlowKt__EmittersKt$unsafeTransform$1$1$emit$1) {
            flowKt__EmittersKt$unsafeTransform$1$1$emit$1 = (FlowKt__EmittersKt$unsafeTransform$1$1$emit$1) continuation;
            int i3 = flowKt__EmittersKt$unsafeTransform$1$1$emit$1.f11782c;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                flowKt__EmittersKt$unsafeTransform$1$1$emit$1.f11782c = i3 - Integer.MIN_VALUE;
                Object obj = flowKt__EmittersKt$unsafeTransform$1$1$emit$1.f11780a;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = flowKt__EmittersKt$unsafeTransform$1$1$emit$1.f11782c;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    Function3 function3 = this.f11778a;
                    FlowCollector flowCollector = this.f11779b;
                    flowKt__EmittersKt$unsafeTransform$1$1$emit$1.f11782c = 1;
                    if (function3.invoke(flowCollector, t2, flowKt__EmittersKt$unsafeTransform$1$1$emit$1) == coroutine_suspended) {
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
        flowKt__EmittersKt$unsafeTransform$1$1$emit$1 = new FlowKt__EmittersKt$unsafeTransform$1$1$emit$1(this, continuation);
        Object obj2 = flowKt__EmittersKt$unsafeTransform$1$1$emit$1.f11780a;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = flowKt__EmittersKt$unsafeTransform$1$1$emit$1.f11782c;
        if (i2 != 0) {
        }
        return Unit.INSTANCE;
    }

    @Nullable
    public final Object emit$$forInline(T t2, @NotNull Continuation<? super Unit> continuation) {
        InlineMarker.mark(4);
        new FlowKt__EmittersKt$unsafeTransform$1$1$emit$1(this, continuation);
        InlineMarker.mark(5);
        this.f11778a.invoke(this.f11779b, t2, continuation);
        return Unit.INSTANCE;
    }
}
