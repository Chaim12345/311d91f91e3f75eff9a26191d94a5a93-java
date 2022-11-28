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
public final class FlowKt__LimitKt$dropWhile$1$1<T> implements FlowCollector {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Ref.BooleanRef f11858a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ FlowCollector f11859b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ Function2 f11860c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FlowKt__LimitKt$dropWhile$1$1(Ref.BooleanRef booleanRef, FlowCollector flowCollector, Function2 function2) {
        this.f11858a = booleanRef;
        this.f11859b = flowCollector;
        this.f11860c = function2;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x008b  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object emit(T t2, @NotNull Continuation<? super Unit> continuation) {
        FlowKt__LimitKt$dropWhile$1$1$emit$1 flowKt__LimitKt$dropWhile$1$1$emit$1;
        Object coroutine_suspended;
        int i2;
        FlowKt__LimitKt$dropWhile$1$1<T> flowKt__LimitKt$dropWhile$1$1;
        if (continuation instanceof FlowKt__LimitKt$dropWhile$1$1$emit$1) {
            flowKt__LimitKt$dropWhile$1$1$emit$1 = (FlowKt__LimitKt$dropWhile$1$1$emit$1) continuation;
            int i3 = flowKt__LimitKt$dropWhile$1$1$emit$1.f11865e;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                flowKt__LimitKt$dropWhile$1$1$emit$1.f11865e = i3 - Integer.MIN_VALUE;
                Object obj = flowKt__LimitKt$dropWhile$1$1$emit$1.f11863c;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = flowKt__LimitKt$dropWhile$1$1$emit$1.f11865e;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    if (this.f11858a.element) {
                        FlowCollector flowCollector = this.f11859b;
                        flowKt__LimitKt$dropWhile$1$1$emit$1.f11865e = 1;
                        if (flowCollector.emit(t2, flowKt__LimitKt$dropWhile$1$1$emit$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        return Unit.INSTANCE;
                    }
                    Function2 function2 = this.f11860c;
                    flowKt__LimitKt$dropWhile$1$1$emit$1.f11861a = this;
                    flowKt__LimitKt$dropWhile$1$1$emit$1.f11862b = t2;
                    flowKt__LimitKt$dropWhile$1$1$emit$1.f11865e = 2;
                    obj = function2.invoke(t2, flowKt__LimitKt$dropWhile$1$1$emit$1);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    flowKt__LimitKt$dropWhile$1$1 = this;
                    if (!((Boolean) obj).booleanValue()) {
                    }
                } else if (i2 == 1) {
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                } else if (i2 == 2) {
                    t2 = (T) flowKt__LimitKt$dropWhile$1$1$emit$1.f11862b;
                    flowKt__LimitKt$dropWhile$1$1 = (FlowKt__LimitKt$dropWhile$1$1) flowKt__LimitKt$dropWhile$1$1$emit$1.f11861a;
                    ResultKt.throwOnFailure(obj);
                    if (!((Boolean) obj).booleanValue()) {
                        return Unit.INSTANCE;
                    }
                    flowKt__LimitKt$dropWhile$1$1.f11858a.element = true;
                    FlowCollector flowCollector2 = flowKt__LimitKt$dropWhile$1$1.f11859b;
                    flowKt__LimitKt$dropWhile$1$1$emit$1.f11861a = null;
                    flowKt__LimitKt$dropWhile$1$1$emit$1.f11862b = null;
                    flowKt__LimitKt$dropWhile$1$1$emit$1.f11865e = 3;
                    if (flowCollector2.emit(t2, flowKt__LimitKt$dropWhile$1$1$emit$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (i2 != 3) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }
        flowKt__LimitKt$dropWhile$1$1$emit$1 = new FlowKt__LimitKt$dropWhile$1$1$emit$1(this, continuation);
        Object obj2 = flowKt__LimitKt$dropWhile$1$1$emit$1.f11863c;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = flowKt__LimitKt$dropWhile$1$1$emit$1.f11865e;
        if (i2 != 0) {
        }
        return Unit.INSTANCE;
    }
}
