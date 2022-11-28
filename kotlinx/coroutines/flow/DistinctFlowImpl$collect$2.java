package kotlinx.coroutines.flow;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class DistinctFlowImpl$collect$2<T> implements FlowCollector {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ DistinctFlowImpl f11582a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ Ref.ObjectRef f11583b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ FlowCollector f11584c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DistinctFlowImpl$collect$2(DistinctFlowImpl distinctFlowImpl, Ref.ObjectRef objectRef, FlowCollector flowCollector) {
        this.f11582a = distinctFlowImpl;
        this.f11583b = objectRef;
        this.f11584c = flowCollector;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0031  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object emit(T t2, @NotNull Continuation<? super Unit> continuation) {
        DistinctFlowImpl$collect$2$emit$1 distinctFlowImpl$collect$2$emit$1;
        Object coroutine_suspended;
        int i2;
        if (continuation instanceof DistinctFlowImpl$collect$2$emit$1) {
            distinctFlowImpl$collect$2$emit$1 = (DistinctFlowImpl$collect$2$emit$1) continuation;
            int i3 = distinctFlowImpl$collect$2$emit$1.f11587c;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                distinctFlowImpl$collect$2$emit$1.f11587c = i3 - Integer.MIN_VALUE;
                Object obj = distinctFlowImpl$collect$2$emit$1.f11585a;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = distinctFlowImpl$collect$2$emit$1.f11587c;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    T t3 = (T) this.f11582a.keySelector.invoke(t2);
                    T t4 = this.f11583b.element;
                    if (t4 != NullSurrogateKt.NULL && this.f11582a.areEquivalent.invoke(t4, t3).booleanValue()) {
                        return Unit.INSTANCE;
                    }
                    this.f11583b.element = t3;
                    FlowCollector flowCollector = this.f11584c;
                    distinctFlowImpl$collect$2$emit$1.f11587c = 1;
                    if (flowCollector.emit(t2, distinctFlowImpl$collect$2$emit$1) == coroutine_suspended) {
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
        distinctFlowImpl$collect$2$emit$1 = new DistinctFlowImpl$collect$2$emit$1(this, continuation);
        Object obj2 = distinctFlowImpl$collect$2$emit$1.f11585a;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = distinctFlowImpl$collect$2$emit$1.f11587c;
        if (i2 != 0) {
        }
        return Unit.INSTANCE;
    }
}
