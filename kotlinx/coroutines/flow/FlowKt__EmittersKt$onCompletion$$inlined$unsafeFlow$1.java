package kotlinx.coroutines.flow;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.InlineMarker;
import kotlinx.coroutines.flow.internal.SafeCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1 implements Flow<T> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Flow f11734a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ Function3 f11735b;

    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1", f = "Emitters.kt", i = {0, 0, 1, 2}, l = {114, 121, 128}, m = "collect", n = {"this", "$this$onCompletion_u24lambda_u2d2", "e", "sc"}, s = {"L$0", "L$1", "L$0", "L$0"})
    /* renamed from: kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static final class AnonymousClass1 extends ContinuationImpl {

        /* renamed from: a  reason: collision with root package name */
        /* synthetic */ Object f11736a;

        /* renamed from: b  reason: collision with root package name */
        int f11737b;

        /* renamed from: d  reason: collision with root package name */
        Object f11739d;

        /* renamed from: e  reason: collision with root package name */
        Object f11740e;

        public AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.f11736a = obj;
            this.f11737b |= Integer.MIN_VALUE;
            return FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1.this.collect(null, this);
        }
    }

    public FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1(Flow flow, Function3 function3) {
        this.f11734a = flow;
        this.f11735b = function3;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0086 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00ab A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:56:? A[RETURN, SYNTHETIC] */
    @Override // kotlinx.coroutines.flow.Flow
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object collect(@NotNull FlowCollector<? super T> flowCollector, @NotNull Continuation<? super Unit> continuation) {
        AnonymousClass1 anonymousClass1;
        Object coroutine_suspended;
        int i2;
        FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1 flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1;
        Object invokeSafely$FlowKt__EmittersKt;
        FlowCollector flowCollector2;
        SafeCollector safeCollector;
        Throwable th;
        SafeCollector safeCollector2;
        Object invoke;
        try {
            if (continuation instanceof AnonymousClass1) {
                anonymousClass1 = (AnonymousClass1) continuation;
                int i3 = anonymousClass1.f11737b;
                if ((i3 & Integer.MIN_VALUE) != 0) {
                    anonymousClass1.f11737b = i3 - Integer.MIN_VALUE;
                    Object obj = anonymousClass1.f11736a;
                    coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    i2 = anonymousClass1.f11737b;
                    if (i2 != 0) {
                        ResultKt.throwOnFailure(obj);
                        try {
                            Flow flow = this.f11734a;
                            anonymousClass1.f11739d = this;
                            anonymousClass1.f11740e = flowCollector;
                            anonymousClass1.f11737b = 1;
                            if (flow.collect(flowCollector, anonymousClass1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1 = this;
                            flowCollector2 = flowCollector;
                        } catch (Throwable th2) {
                            th = th2;
                            flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1 = this;
                            ThrowingCollector throwingCollector = new ThrowingCollector(th);
                            Function3 function3 = flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1.f11735b;
                            anonymousClass1.f11739d = th;
                            anonymousClass1.f11740e = null;
                            anonymousClass1.f11737b = 2;
                            invokeSafely$FlowKt__EmittersKt = FlowKt__EmittersKt.invokeSafely$FlowKt__EmittersKt(throwingCollector, function3, th, anonymousClass1);
                            if (invokeSafely$FlowKt__EmittersKt != coroutine_suspended) {
                            }
                        }
                    } else if (i2 != 1) {
                        if (i2 == 2) {
                            Throwable th3 = (Throwable) anonymousClass1.f11739d;
                            ResultKt.throwOnFailure(obj);
                            throw th3;
                        } else if (i2 == 3) {
                            safeCollector2 = (SafeCollector) anonymousClass1.f11739d;
                            try {
                                ResultKt.throwOnFailure(obj);
                                safeCollector2.releaseIntercepted();
                                return Unit.INSTANCE;
                            } catch (Throwable th4) {
                                th = th4;
                                safeCollector2.releaseIntercepted();
                                throw th;
                            }
                        } else {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                    } else {
                        FlowCollector flowCollector3 = (FlowCollector) anonymousClass1.f11740e;
                        flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1 = (FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1) anonymousClass1.f11739d;
                        try {
                            ResultKt.throwOnFailure(obj);
                            flowCollector2 = flowCollector3;
                        } catch (Throwable th5) {
                            th = th5;
                            ThrowingCollector throwingCollector2 = new ThrowingCollector(th);
                            Function3 function32 = flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1.f11735b;
                            anonymousClass1.f11739d = th;
                            anonymousClass1.f11740e = null;
                            anonymousClass1.f11737b = 2;
                            invokeSafely$FlowKt__EmittersKt = FlowKt__EmittersKt.invokeSafely$FlowKt__EmittersKt(throwingCollector2, function32, th, anonymousClass1);
                            if (invokeSafely$FlowKt__EmittersKt != coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            throw th;
                        }
                    }
                    safeCollector = new SafeCollector(flowCollector2, anonymousClass1.getContext());
                    Function3 function33 = flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1.f11735b;
                    anonymousClass1.f11739d = safeCollector;
                    anonymousClass1.f11740e = null;
                    anonymousClass1.f11737b = 3;
                    InlineMarker.mark(6);
                    invoke = function33.invoke(safeCollector, null, anonymousClass1);
                    InlineMarker.mark(7);
                    if (invoke != coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    safeCollector2 = safeCollector;
                    safeCollector2.releaseIntercepted();
                    return Unit.INSTANCE;
                }
            }
            Function3 function332 = flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1.f11735b;
            anonymousClass1.f11739d = safeCollector;
            anonymousClass1.f11740e = null;
            anonymousClass1.f11737b = 3;
            InlineMarker.mark(6);
            invoke = function332.invoke(safeCollector, null, anonymousClass1);
            InlineMarker.mark(7);
            if (invoke != coroutine_suspended) {
            }
        } catch (Throwable th6) {
            th = th6;
            safeCollector2 = safeCollector;
            safeCollector2.releaseIntercepted();
            throw th;
        }
        anonymousClass1 = new AnonymousClass1(continuation);
        Object obj2 = anonymousClass1.f11736a;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = anonymousClass1.f11737b;
        if (i2 != 0) {
        }
        safeCollector = new SafeCollector(flowCollector2, anonymousClass1.getContext());
    }
}
