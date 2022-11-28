package kotlinx.coroutines.flow;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class FlowKt__TransformKt$runningFold$$inlined$unsafeFlow$1 implements Flow<R> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Object f12070a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ Flow f12071b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ Function3 f12072c;

    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__TransformKt$runningFold$$inlined$unsafeFlow$1", f = "Transform.kt", i = {0, 0, 0}, l = {114, 115}, m = "collect", n = {"this", "$this$runningFold_u24lambda_u2d8", "accumulator"}, s = {"L$0", "L$1", "L$2"})
    /* renamed from: kotlinx.coroutines.flow.FlowKt__TransformKt$runningFold$$inlined$unsafeFlow$1$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static final class AnonymousClass1 extends ContinuationImpl {

        /* renamed from: a  reason: collision with root package name */
        /* synthetic */ Object f12073a;

        /* renamed from: b  reason: collision with root package name */
        int f12074b;

        /* renamed from: d  reason: collision with root package name */
        Object f12076d;

        /* renamed from: e  reason: collision with root package name */
        Object f12077e;

        /* renamed from: f  reason: collision with root package name */
        Object f12078f;

        public AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.f12073a = obj;
            this.f12074b |= Integer.MIN_VALUE;
            return FlowKt__TransformKt$runningFold$$inlined$unsafeFlow$1.this.collect(null, this);
        }
    }

    public FlowKt__TransformKt$runningFold$$inlined$unsafeFlow$1(Object obj, Flow flow, Function3 function3) {
        this.f12070a = obj;
        this.f12071b = flow;
        this.f12072c = function3;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x007a A[RETURN] */
    /* JADX WARN: Type inference failed for: r2v1, types: [T, java.lang.Object] */
    @Override // kotlinx.coroutines.flow.Flow
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object collect(@NotNull FlowCollector<? super R> flowCollector, @NotNull Continuation<? super Unit> continuation) {
        AnonymousClass1 anonymousClass1;
        Object coroutine_suspended;
        int i2;
        FlowKt__TransformKt$runningFold$$inlined$unsafeFlow$1 flowKt__TransformKt$runningFold$$inlined$unsafeFlow$1;
        FlowCollector flowCollector2;
        Ref.ObjectRef objectRef;
        Flow flow;
        FlowKt__TransformKt$runningFold$1$1 flowKt__TransformKt$runningFold$1$1;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            int i3 = anonymousClass1.f12074b;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                anonymousClass1.f12074b = i3 - Integer.MIN_VALUE;
                Object obj = anonymousClass1.f12073a;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = anonymousClass1.f12074b;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                    ?? r2 = this.f12070a;
                    objectRef2.element = r2;
                    anonymousClass1.f12076d = this;
                    anonymousClass1.f12077e = flowCollector;
                    anonymousClass1.f12078f = objectRef2;
                    anonymousClass1.f12074b = 1;
                    if (flowCollector.emit(r2, anonymousClass1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    flowKt__TransformKt$runningFold$$inlined$unsafeFlow$1 = this;
                    flowCollector2 = flowCollector;
                    objectRef = objectRef2;
                } else if (i2 != 1) {
                    if (i2 == 2) {
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    objectRef = (Ref.ObjectRef) anonymousClass1.f12078f;
                    flowCollector2 = (FlowCollector) anonymousClass1.f12077e;
                    flowKt__TransformKt$runningFold$$inlined$unsafeFlow$1 = (FlowKt__TransformKt$runningFold$$inlined$unsafeFlow$1) anonymousClass1.f12076d;
                    ResultKt.throwOnFailure(obj);
                }
                flow = flowKt__TransformKt$runningFold$$inlined$unsafeFlow$1.f12071b;
                flowKt__TransformKt$runningFold$1$1 = new FlowKt__TransformKt$runningFold$1$1(objectRef, flowKt__TransformKt$runningFold$$inlined$unsafeFlow$1.f12072c, flowCollector2);
                anonymousClass1.f12076d = null;
                anonymousClass1.f12077e = null;
                anonymousClass1.f12078f = null;
                anonymousClass1.f12074b = 2;
                if (flow.collect(flowKt__TransformKt$runningFold$1$1, anonymousClass1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            }
        }
        anonymousClass1 = new AnonymousClass1(continuation);
        Object obj2 = anonymousClass1.f12073a;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = anonymousClass1.f12074b;
        if (i2 != 0) {
        }
        flow = flowKt__TransformKt$runningFold$$inlined$unsafeFlow$1.f12071b;
        flowKt__TransformKt$runningFold$1$1 = new FlowKt__TransformKt$runningFold$1$1(objectRef, flowKt__TransformKt$runningFold$$inlined$unsafeFlow$1.f12072c, flowCollector2);
        anonymousClass1.f12076d = null;
        anonymousClass1.f12077e = null;
        anonymousClass1.f12078f = null;
        anonymousClass1.f12074b = 2;
        if (flow.collect(flowKt__TransformKt$runningFold$1$1, anonymousClass1) == coroutine_suspended) {
        }
        return Unit.INSTANCE;
    }
}
