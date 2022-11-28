package kotlinx.coroutines.flow;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.InlineMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1 implements Flow<T> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Flow f11790a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ Function4 f11791b;

    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1", f = "Errors.kt", i = {0, 0, 0, 0, 1, 1, 1, 1}, l = {117, 119}, m = "collect", n = {"this", "$this$retryWhen_u24lambda_u2d2", "attempt", "shallRetry", "this", "$this$retryWhen_u24lambda_u2d2", "cause", "attempt"}, s = {"L$0", "L$1", "J$0", "I$0", "L$0", "L$1", "L$2", "J$0"})
    /* renamed from: kotlinx.coroutines.flow.FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static final class AnonymousClass1 extends ContinuationImpl {

        /* renamed from: a  reason: collision with root package name */
        /* synthetic */ Object f11792a;

        /* renamed from: b  reason: collision with root package name */
        int f11793b;

        /* renamed from: d  reason: collision with root package name */
        Object f11795d;

        /* renamed from: e  reason: collision with root package name */
        Object f11796e;

        /* renamed from: f  reason: collision with root package name */
        Object f11797f;

        /* renamed from: g  reason: collision with root package name */
        long f11798g;

        /* renamed from: h  reason: collision with root package name */
        int f11799h;

        public AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.f11792a = obj;
            this.f11793b |= Integer.MIN_VALUE;
            return FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1.this.collect(null, this);
        }
    }

    public FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1(Flow flow, Function4 function4) {
        this.f11790a = flow;
        this.f11791b = function4;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x006e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00af  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x0077 -> B:31:0x00a9). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x0098 -> B:27:0x009b). Please submit an issue!!! */
    @Override // kotlinx.coroutines.flow.Flow
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object collect(@NotNull FlowCollector<? super T> flowCollector, @NotNull Continuation<? super Unit> continuation) {
        AnonymousClass1 anonymousClass1;
        Object coroutine_suspended;
        int i2;
        long j2;
        FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1 flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1;
        int i3;
        FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1 flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$12;
        FlowCollector flowCollector2;
        Throwable th;
        FlowCollector flowCollector3;
        Object catchImpl;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            int i4 = anonymousClass1.f11793b;
            if ((i4 & Integer.MIN_VALUE) != 0) {
                anonymousClass1.f11793b = i4 - Integer.MIN_VALUE;
                Object obj = anonymousClass1.f11792a;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = anonymousClass1.f11793b;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    j2 = 0;
                    flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1 = this;
                    flowCollector3 = flowCollector;
                    Flow flow = flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1.f11790a;
                    anonymousClass1.f11795d = flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1;
                    anonymousClass1.f11796e = flowCollector3;
                    anonymousClass1.f11797f = null;
                    anonymousClass1.f11798g = j2;
                    anonymousClass1.f11799h = 0;
                    anonymousClass1.f11793b = 1;
                    catchImpl = FlowKt.catchImpl(flow, flowCollector3, anonymousClass1);
                    if (catchImpl != coroutine_suspended) {
                    }
                } else if (i2 == 1) {
                    i3 = anonymousClass1.f11799h;
                    j2 = anonymousClass1.f11798g;
                    flowCollector2 = (FlowCollector) anonymousClass1.f11796e;
                    flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$12 = (FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1) anonymousClass1.f11795d;
                    ResultKt.throwOnFailure(obj);
                    th = (Throwable) obj;
                    if (th != null) {
                    }
                    flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1 = flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$12;
                    if (i3 == 0) {
                    }
                } else if (i2 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    j2 = anonymousClass1.f11798g;
                    Throwable th2 = (Throwable) anonymousClass1.f11797f;
                    flowCollector2 = (FlowCollector) anonymousClass1.f11796e;
                    flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$12 = (FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1) anonymousClass1.f11795d;
                    ResultKt.throwOnFailure(obj);
                    if (!((Boolean) obj).booleanValue()) {
                        j2++;
                        i3 = 1;
                        flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1 = flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$12;
                        if (i3 == 0) {
                            return Unit.INSTANCE;
                        }
                        flowCollector3 = flowCollector2;
                        Flow flow2 = flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1.f11790a;
                        anonymousClass1.f11795d = flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1;
                        anonymousClass1.f11796e = flowCollector3;
                        anonymousClass1.f11797f = null;
                        anonymousClass1.f11798g = j2;
                        anonymousClass1.f11799h = 0;
                        anonymousClass1.f11793b = 1;
                        catchImpl = FlowKt.catchImpl(flow2, flowCollector3, anonymousClass1);
                        if (catchImpl != coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        flowCollector2 = flowCollector3;
                        i3 = 0;
                        flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$12 = flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1;
                        obj = catchImpl;
                        th = (Throwable) obj;
                        if (th != null) {
                            Function4 function4 = flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$12.f11791b;
                            Long boxLong = Boxing.boxLong(j2);
                            anonymousClass1.f11795d = flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$12;
                            anonymousClass1.f11796e = flowCollector2;
                            anonymousClass1.f11797f = th;
                            anonymousClass1.f11798g = j2;
                            anonymousClass1.f11793b = 2;
                            InlineMarker.mark(6);
                            Object invoke = function4.invoke(flowCollector2, th, boxLong, anonymousClass1);
                            InlineMarker.mark(7);
                            if (invoke == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            obj = invoke;
                            th2 = th;
                            if (!((Boolean) obj).booleanValue()) {
                                throw th2;
                            }
                        }
                        flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1 = flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$12;
                        if (i3 == 0) {
                        }
                    }
                }
            }
        }
        anonymousClass1 = new AnonymousClass1(continuation);
        Object obj2 = anonymousClass1.f11792a;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = anonymousClass1.f11793b;
        if (i2 != 0) {
        }
    }
}
