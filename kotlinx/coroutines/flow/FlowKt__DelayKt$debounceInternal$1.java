package kotlinx.coroutines.flow;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.selects.SelectBuilderImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__DelayKt$debounceInternal$1", f = "Delay.kt", i = {0, 0, 0, 0, 1, 1, 1, 1}, l = {222, 355}, m = "invokeSuspend", n = {"downstream", "values", "lastValue", "timeoutMillis", "downstream", "values", "lastValue", "timeoutMillis"}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3"})
/* loaded from: classes3.dex */
public final class FlowKt__DelayKt$debounceInternal$1 extends SuspendLambda implements Function3<CoroutineScope, FlowCollector<? super T>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;

    /* renamed from: a  reason: collision with root package name */
    Object f11692a;

    /* renamed from: b  reason: collision with root package name */
    Object f11693b;

    /* renamed from: c  reason: collision with root package name */
    int f11694c;

    /* renamed from: d  reason: collision with root package name */
    /* synthetic */ Object f11695d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ Function1 f11696e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ Flow f11697f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowKt__DelayKt$debounceInternal$1(Function1 function1, Flow flow, Continuation continuation) {
        super(3, continuation);
        this.f11696e = function1;
        this.f11697f = flow;
    }

    @Override // kotlin.jvm.functions.Function3
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @NotNull FlowCollector<? super T> flowCollector, @Nullable Continuation<? super Unit> continuation) {
        FlowKt__DelayKt$debounceInternal$1 flowKt__DelayKt$debounceInternal$1 = new FlowKt__DelayKt$debounceInternal$1(this.f11696e, this.f11697f, continuation);
        flowKt__DelayKt$debounceInternal$1.L$0 = coroutineScope;
        flowKt__DelayKt$debounceInternal$1.f11695d = flowCollector;
        return flowKt__DelayKt$debounceInternal$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(13:61|26|30|31|(3:33|(1:41)(1:37)|(2:39|40))|42|43|44|(1:46)|47|48|(1:50)|(1:52)(1:53)) */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0117, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0118, code lost:
        r13.handleBuilderException(r0);
     */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0100 A[Catch: all -> 0x0117, TryCatch #0 {all -> 0x0117, blocks: (B:49:0x00fc, B:51:0x0100, B:52:0x010a), top: B:64:0x00fc }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x012a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x012b  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:61:0x012b -> B:11:0x0072). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        FlowCollector flowCollector;
        FlowKt__DelayKt$debounceInternal$1 flowKt__DelayKt$debounceInternal$1;
        ReceiveChannel receiveChannel;
        Ref.ObjectRef objectRef;
        Ref.LongRef longRef;
        Object obj2;
        Object result;
        Object coroutine_suspended2;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.f11694c;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            ReceiveChannel produce$default = ProduceKt.produce$default((CoroutineScope) this.L$0, null, 0, new FlowKt__DelayKt$debounceInternal$1$values$1(this.f11697f, null), 3, null);
            flowCollector = (FlowCollector) this.f11695d;
            flowKt__DelayKt$debounceInternal$1 = this;
            receiveChannel = produce$default;
            objectRef = new Ref.ObjectRef();
        } else if (i2 == 1) {
            objectRef = (Ref.ObjectRef) this.f11692a;
            receiveChannel = (ReceiveChannel) this.f11695d;
            flowCollector = (FlowCollector) this.L$0;
            ResultKt.throwOnFailure(obj);
            longRef = (Ref.LongRef) this.f11693b;
            flowKt__DelayKt$debounceInternal$1 = this;
            objectRef.element = null;
            FlowKt__DelayKt$debounceInternal$1 flowKt__DelayKt$debounceInternal$12 = flowKt__DelayKt$debounceInternal$1;
            obj2 = coroutine_suspended;
            Ref.LongRef longRef2 = longRef;
            FlowCollector flowCollector2 = flowCollector;
            ReceiveChannel receiveChannel2 = receiveChannel;
            if (DebugKt.getASSERTIONS_ENABLED()) {
                if (!(objectRef.element == 0 || longRef2.element > 0)) {
                    throw new AssertionError();
                }
            }
            flowKt__DelayKt$debounceInternal$12.L$0 = flowCollector2;
            flowKt__DelayKt$debounceInternal$12.f11695d = receiveChannel2;
            flowKt__DelayKt$debounceInternal$12.f11692a = objectRef;
            flowKt__DelayKt$debounceInternal$12.f11693b = longRef2;
            flowKt__DelayKt$debounceInternal$12.f11694c = 2;
            SelectBuilderImpl selectBuilderImpl = new SelectBuilderImpl(flowKt__DelayKt$debounceInternal$12);
            if (objectRef.element != 0) {
                selectBuilderImpl.onTimeout(longRef2.element, new FlowKt__DelayKt$debounceInternal$1$3$1(flowCollector2, objectRef, null));
            }
            selectBuilderImpl.invoke(receiveChannel2.getOnReceiveCatching(), new FlowKt__DelayKt$debounceInternal$1$3$2(objectRef, flowCollector2, null));
            result = selectBuilderImpl.getResult();
            coroutine_suspended2 = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (result == coroutine_suspended2) {
                DebugProbesKt.probeCoroutineSuspended(flowKt__DelayKt$debounceInternal$12);
            }
            if (result != obj2) {
                return obj2;
            }
            coroutine_suspended = obj2;
            flowKt__DelayKt$debounceInternal$1 = flowKt__DelayKt$debounceInternal$12;
            receiveChannel = receiveChannel2;
            flowCollector = flowCollector2;
        } else if (i2 != 2) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            Ref.LongRef longRef3 = (Ref.LongRef) this.f11693b;
            ResultKt.throwOnFailure(obj);
            flowCollector = (FlowCollector) this.L$0;
            receiveChannel = (ReceiveChannel) this.f11695d;
            objectRef = (Ref.ObjectRef) this.f11692a;
            flowKt__DelayKt$debounceInternal$1 = this;
        }
        if (objectRef.element == NullSurrogateKt.DONE) {
            return Unit.INSTANCE;
        }
        longRef = new Ref.LongRef();
        Object obj3 = objectRef.element;
        if (obj3 != null) {
            Function1 function1 = flowKt__DelayKt$debounceInternal$1.f11696e;
            Symbol symbol = NullSurrogateKt.NULL;
            if (obj3 == symbol) {
                obj3 = null;
            }
            long longValue = ((Number) function1.invoke(obj3)).longValue();
            longRef.element = longValue;
            if (!(longValue >= 0)) {
                throw new IllegalArgumentException("Debounce timeout should not be negative".toString());
            }
            if (longValue == 0) {
                Object obj4 = objectRef.element;
                if (obj4 == symbol) {
                    obj4 = null;
                }
                flowKt__DelayKt$debounceInternal$1.L$0 = flowCollector;
                flowKt__DelayKt$debounceInternal$1.f11695d = receiveChannel;
                flowKt__DelayKt$debounceInternal$1.f11692a = objectRef;
                flowKt__DelayKt$debounceInternal$1.f11693b = longRef;
                flowKt__DelayKt$debounceInternal$1.f11694c = 1;
                if (flowCollector.emit(obj4, flowKt__DelayKt$debounceInternal$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                objectRef.element = null;
            }
        }
        FlowKt__DelayKt$debounceInternal$1 flowKt__DelayKt$debounceInternal$122 = flowKt__DelayKt$debounceInternal$1;
        obj2 = coroutine_suspended;
        Ref.LongRef longRef22 = longRef;
        FlowCollector flowCollector22 = flowCollector;
        ReceiveChannel receiveChannel22 = receiveChannel;
        if (DebugKt.getASSERTIONS_ENABLED()) {
        }
        flowKt__DelayKt$debounceInternal$122.L$0 = flowCollector22;
        flowKt__DelayKt$debounceInternal$122.f11695d = receiveChannel22;
        flowKt__DelayKt$debounceInternal$122.f11692a = objectRef;
        flowKt__DelayKt$debounceInternal$122.f11693b = longRef22;
        flowKt__DelayKt$debounceInternal$122.f11694c = 2;
        SelectBuilderImpl selectBuilderImpl2 = new SelectBuilderImpl(flowKt__DelayKt$debounceInternal$122);
        if (objectRef.element != 0) {
        }
        selectBuilderImpl2.invoke(receiveChannel22.getOnReceiveCatching(), new FlowKt__DelayKt$debounceInternal$1$3$2(objectRef, flowCollector22, null));
        result = selectBuilderImpl2.getResult();
        coroutine_suspended2 = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (result == coroutine_suspended2) {
        }
        if (result != obj2) {
        }
    }
}
