package kotlinx.coroutines.flow;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.IndexedValue;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class FlowKt__TransformKt$withIndex$1$1<T> implements FlowCollector {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ FlowCollector f12098a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ Ref.IntRef f12099b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FlowKt__TransformKt$withIndex$1$1(FlowCollector flowCollector, Ref.IntRef intRef) {
        this.f12098a = flowCollector;
        this.f12099b = intRef;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0031  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object emit(T t2, @NotNull Continuation<? super Unit> continuation) {
        FlowKt__TransformKt$withIndex$1$1$emit$1 flowKt__TransformKt$withIndex$1$1$emit$1;
        Object coroutine_suspended;
        int i2;
        if (continuation instanceof FlowKt__TransformKt$withIndex$1$1$emit$1) {
            flowKt__TransformKt$withIndex$1$1$emit$1 = (FlowKt__TransformKt$withIndex$1$1$emit$1) continuation;
            int i3 = flowKt__TransformKt$withIndex$1$1$emit$1.f12102c;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                flowKt__TransformKt$withIndex$1$1$emit$1.f12102c = i3 - Integer.MIN_VALUE;
                Object obj = flowKt__TransformKt$withIndex$1$1$emit$1.f12100a;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = flowKt__TransformKt$withIndex$1$1$emit$1.f12102c;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    FlowCollector flowCollector = this.f12098a;
                    Ref.IntRef intRef = this.f12099b;
                    int i4 = intRef.element;
                    intRef.element = i4 + 1;
                    if (i4 < 0) {
                        throw new ArithmeticException("Index overflow has happened");
                    }
                    IndexedValue indexedValue = new IndexedValue(i4, t2);
                    flowKt__TransformKt$withIndex$1$1$emit$1.f12102c = 1;
                    if (flowCollector.emit(indexedValue, flowKt__TransformKt$withIndex$1$1$emit$1) == coroutine_suspended) {
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
        flowKt__TransformKt$withIndex$1$1$emit$1 = new FlowKt__TransformKt$withIndex$1$1$emit$1(this, continuation);
        Object obj2 = flowKt__TransformKt$withIndex$1$1$emit$1.f12100a;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = flowKt__TransformKt$withIndex$1$1$emit$1.f12102c;
        if (i2 != 0) {
        }
        return Unit.INSTANCE;
    }
}
