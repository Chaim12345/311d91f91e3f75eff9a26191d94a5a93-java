package kotlinx.coroutines.flow.internal;

import java.util.concurrent.CancellationException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.internal.ThreadContextKt;
import org.bouncycastle.tls.CipherSuite;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1", f = "Combine.kt", i = {0}, l = {129}, m = "invokeSuspend", n = {"second"}, s = {"L$0"})
/* loaded from: classes3.dex */
final class CombineKt$zipImpl$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;

    /* renamed from: a  reason: collision with root package name */
    int f12304a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ FlowCollector f12305b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ Flow f12306c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ Flow f12307d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ Function3 f12308e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static final class AnonymousClass1 extends Lambda implements Function1<Throwable, Unit> {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ CompletableJob f12309a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ FlowCollector f12310b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(CompletableJob completableJob, FlowCollector flowCollector) {
            super(1);
            this.f12309a = completableJob;
            this.f12310b = flowCollector;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
            invoke2(th);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable Throwable th) {
            if (this.f12309a.isActive()) {
                this.f12309a.cancel((CancellationException) new AbortFlowException(this.f12310b));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @DebugMetadata(c = "kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2", f = "Combine.kt", i = {}, l = {130}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<Unit, Continuation<? super Unit>, Object> {

        /* renamed from: a  reason: collision with root package name */
        int f12311a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ Flow f12312b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ CoroutineContext f12313c;

        /* renamed from: d  reason: collision with root package name */
        final /* synthetic */ Object f12314d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ ReceiveChannel f12315e;

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ FlowCollector f12316f;

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Function3 f12317g;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2$1  reason: invalid class name */
        /* loaded from: classes3.dex */
        public static final class AnonymousClass1<T> implements FlowCollector {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ CoroutineContext f12318a;

            /* renamed from: b  reason: collision with root package name */
            final /* synthetic */ Object f12319b;

            /* renamed from: c  reason: collision with root package name */
            final /* synthetic */ ReceiveChannel f12320c;

            /* renamed from: d  reason: collision with root package name */
            final /* synthetic */ FlowCollector f12321d;

            /* renamed from: e  reason: collision with root package name */
            final /* synthetic */ Function3 f12322e;

            /* JADX INFO: Access modifiers changed from: package-private */
            @DebugMetadata(c = "kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2$1$1", f = "Combine.kt", i = {}, l = {CipherSuite.TLS_RSA_WITH_CAMELLIA_256_CBC_SHA, CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA, CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2$1$1  reason: invalid class name and collision with other inner class name */
            /* loaded from: classes3.dex */
            public static final class C00511 extends SuspendLambda implements Function2<Unit, Continuation<? super Unit>, Object> {

                /* renamed from: a  reason: collision with root package name */
                Object f12323a;

                /* renamed from: b  reason: collision with root package name */
                int f12324b;

                /* renamed from: c  reason: collision with root package name */
                final /* synthetic */ ReceiveChannel f12325c;

                /* renamed from: d  reason: collision with root package name */
                final /* synthetic */ FlowCollector f12326d;

                /* renamed from: e  reason: collision with root package name */
                final /* synthetic */ Function3 f12327e;

                /* renamed from: f  reason: collision with root package name */
                final /* synthetic */ Object f12328f;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C00511(ReceiveChannel receiveChannel, FlowCollector flowCollector, Function3 function3, Object obj, Continuation continuation) {
                    super(2, continuation);
                    this.f12325c = receiveChannel;
                    this.f12326d = flowCollector;
                    this.f12327e = function3;
                    this.f12328f = obj;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new C00511(this.f12325c, this.f12326d, this.f12327e, this.f12328f, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull Unit unit, @Nullable Continuation<? super Unit> continuation) {
                    return ((C00511) create(unit, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Removed duplicated region for block: B:29:0x006e A[RETURN] */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public final Object invokeSuspend(@NotNull Object obj) {
                    Object coroutine_suspended;
                    Object mo1627receiveCatchingJP2dKIU;
                    FlowCollector flowCollector;
                    coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i2 = this.f12324b;
                    if (i2 == 0) {
                        ResultKt.throwOnFailure(obj);
                        ReceiveChannel receiveChannel = this.f12325c;
                        this.f12324b = 1;
                        mo1627receiveCatchingJP2dKIU = receiveChannel.mo1627receiveCatchingJP2dKIU(this);
                        if (mo1627receiveCatchingJP2dKIU == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else if (i2 != 1) {
                        if (i2 != 2) {
                            if (i2 == 3) {
                                ResultKt.throwOnFailure(obj);
                                return Unit.INSTANCE;
                            }
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                        flowCollector = (FlowCollector) this.f12323a;
                        this.f12323a = null;
                        this.f12324b = 3;
                        if (flowCollector.emit(obj, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        return Unit.INSTANCE;
                    } else {
                        ResultKt.throwOnFailure(obj);
                        mo1627receiveCatchingJP2dKIU = ((ChannelResult) obj).m1646unboximpl();
                    }
                    FlowCollector flowCollector2 = this.f12326d;
                    if (mo1627receiveCatchingJP2dKIU instanceof ChannelResult.Failed) {
                        Throwable m1638exceptionOrNullimpl = ChannelResult.m1638exceptionOrNullimpl(mo1627receiveCatchingJP2dKIU);
                        if (m1638exceptionOrNullimpl == null) {
                            throw new AbortFlowException(flowCollector2);
                        }
                        throw m1638exceptionOrNullimpl;
                    }
                    Function3 function3 = this.f12327e;
                    Object obj2 = this.f12328f;
                    if (mo1627receiveCatchingJP2dKIU == NullSurrogateKt.NULL) {
                        mo1627receiveCatchingJP2dKIU = null;
                    }
                    this.f12323a = flowCollector2;
                    this.f12324b = 2;
                    obj = function3.invoke(obj2, mo1627receiveCatchingJP2dKIU, this);
                    flowCollector = flowCollector2;
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    this.f12323a = null;
                    this.f12324b = 3;
                    if (flowCollector.emit(obj, this) == coroutine_suspended) {
                    }
                    return Unit.INSTANCE;
                }
            }

            AnonymousClass1(CoroutineContext coroutineContext, Object obj, ReceiveChannel receiveChannel, FlowCollector flowCollector, Function3 function3) {
                this.f12318a = coroutineContext;
                this.f12319b = obj;
                this.f12320c = receiveChannel;
                this.f12321d = flowCollector;
                this.f12322e = function3;
            }

            /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
            /* JADX WARN: Removed duplicated region for block: B:14:0x0031  */
            @Override // kotlinx.coroutines.flow.FlowCollector
            @Nullable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final Object emit(T1 t1, @NotNull Continuation<? super Unit> continuation) {
                CombineKt$zipImpl$1$1$2$1$emit$1 combineKt$zipImpl$1$1$2$1$emit$1;
                Object coroutine_suspended;
                int i2;
                if (continuation instanceof CombineKt$zipImpl$1$1$2$1$emit$1) {
                    combineKt$zipImpl$1$1$2$1$emit$1 = (CombineKt$zipImpl$1$1$2$1$emit$1) continuation;
                    int i3 = combineKt$zipImpl$1$1$2$1$emit$1.f12331c;
                    if ((i3 & Integer.MIN_VALUE) != 0) {
                        combineKt$zipImpl$1$1$2$1$emit$1.f12331c = i3 - Integer.MIN_VALUE;
                        Object obj = combineKt$zipImpl$1$1$2$1$emit$1.f12329a;
                        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        i2 = combineKt$zipImpl$1$1$2$1$emit$1.f12331c;
                        if (i2 != 0) {
                            ResultKt.throwOnFailure(obj);
                            CoroutineContext coroutineContext = this.f12318a;
                            Unit unit = Unit.INSTANCE;
                            Object obj2 = this.f12319b;
                            C00511 c00511 = new C00511(this.f12320c, this.f12321d, this.f12322e, t1, null);
                            combineKt$zipImpl$1$1$2$1$emit$1.f12331c = 1;
                            if (ChannelFlowKt.withContextUndispatched(coroutineContext, unit, obj2, c00511, combineKt$zipImpl$1$1$2$1$emit$1) == coroutine_suspended) {
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
                combineKt$zipImpl$1$1$2$1$emit$1 = new CombineKt$zipImpl$1$1$2$1$emit$1(this, continuation);
                Object obj3 = combineKt$zipImpl$1$1$2$1$emit$1.f12329a;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = combineKt$zipImpl$1$1$2$1$emit$1.f12331c;
                if (i2 != 0) {
                }
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Flow flow, CoroutineContext coroutineContext, Object obj, ReceiveChannel receiveChannel, FlowCollector flowCollector, Function3 function3, Continuation continuation) {
            super(2, continuation);
            this.f12312b = flow;
            this.f12313c = coroutineContext;
            this.f12314d = obj;
            this.f12315e = receiveChannel;
            this.f12316f = flowCollector;
            this.f12317g = function3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new AnonymousClass2(this.f12312b, this.f12313c, this.f12314d, this.f12315e, this.f12316f, this.f12317g, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull Unit unit, @Nullable Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(unit, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended;
            coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.f12311a;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                Flow flow = this.f12312b;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.f12313c, this.f12314d, this.f12315e, this.f12316f, this.f12317g);
                this.f12311a = 1;
                if (flow.collect(anonymousClass1, this) == coroutine_suspended) {
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

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CombineKt$zipImpl$1$1(FlowCollector flowCollector, Flow flow, Flow flow2, Function3 function3, Continuation continuation) {
        super(2, continuation);
        this.f12305b = flowCollector;
        this.f12306c = flow;
        this.f12307d = flow2;
        this.f12308e = function3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        CombineKt$zipImpl$1$1 combineKt$zipImpl$1$1 = new CombineKt$zipImpl$1$1(this.f12305b, this.f12306c, this.f12307d, this.f12308e, continuation);
        combineKt$zipImpl$1$1.L$0 = obj;
        return combineKt$zipImpl$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((CombineKt$zipImpl$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v12, types: [kotlinx.coroutines.channels.ReceiveChannel] */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v2, types: [kotlinx.coroutines.channels.ReceiveChannel] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        CompletableJob Job$default;
        ReceiveChannel receiveChannel;
        ReceiveChannel receiveChannel2;
        CoroutineContext plus;
        Unit unit;
        AnonymousClass2 anonymousClass2;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ReceiveChannel receiveChannel3 = this.f12304a;
        try {
            if (receiveChannel3 != 0) {
                if (receiveChannel3 == 1) {
                    receiveChannel2 = (ReceiveChannel) this.L$0;
                    try {
                        ResultKt.throwOnFailure(obj);
                        receiveChannel3 = receiveChannel2;
                    } catch (AbortFlowException e2) {
                        e = e2;
                    }
                    ReceiveChannel.DefaultImpls.cancel$default((ReceiveChannel) receiveChannel3, (CancellationException) null, 1, (Object) null);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            ReceiveChannel produce$default = ProduceKt.produce$default(coroutineScope, null, 0, new CombineKt$zipImpl$1$1$second$1(this.f12306c, null), 3, null);
            Job$default = JobKt__JobKt.Job$default((Job) null, 1, (Object) null);
            ((SendChannel) produce$default).invokeOnClose(new AnonymousClass1(Job$default, this.f12305b));
            try {
                CoroutineContext coroutineContext = coroutineScope.getCoroutineContext();
                Object threadContextElements = ThreadContextKt.threadContextElements(coroutineContext);
                plus = coroutineScope.getCoroutineContext().plus(Job$default);
                unit = Unit.INSTANCE;
                anonymousClass2 = new AnonymousClass2(this.f12307d, coroutineContext, threadContextElements, produce$default, this.f12305b, this.f12308e, null);
                this.L$0 = produce$default;
                this.f12304a = 1;
                receiveChannel = produce$default;
                try {
                } catch (AbortFlowException e3) {
                    e = e3;
                    receiveChannel2 = receiveChannel;
                    FlowExceptions_commonKt.checkOwnership(e, this.f12305b);
                    receiveChannel3 = receiveChannel2;
                    ReceiveChannel.DefaultImpls.cancel$default((ReceiveChannel) receiveChannel3, (CancellationException) null, 1, (Object) null);
                    return Unit.INSTANCE;
                } catch (Throwable th) {
                    th = th;
                    receiveChannel3 = receiveChannel;
                    ReceiveChannel.DefaultImpls.cancel$default((ReceiveChannel) receiveChannel3, (CancellationException) null, 1, (Object) null);
                    throw th;
                }
            } catch (AbortFlowException e4) {
                e = e4;
                receiveChannel = produce$default;
            } catch (Throwable th2) {
                th = th2;
                receiveChannel = produce$default;
            }
            if (ChannelFlowKt.withContextUndispatched$default(plus, unit, null, anonymousClass2, this, 4, null) == coroutine_suspended) {
                return coroutine_suspended;
            }
            receiveChannel3 = receiveChannel;
            ReceiveChannel.DefaultImpls.cancel$default((ReceiveChannel) receiveChannel3, (CancellationException) null, 1, (Object) null);
            return Unit.INSTANCE;
            FlowExceptions_commonKt.checkOwnership(e, this.f12305b);
            receiveChannel3 = receiveChannel2;
            ReceiveChannel.DefaultImpls.cancel$default((ReceiveChannel) receiveChannel3, (CancellationException) null, 1, (Object) null);
            return Unit.INSTANCE;
        } catch (Throwable th3) {
            th = th3;
        }
    }
}
