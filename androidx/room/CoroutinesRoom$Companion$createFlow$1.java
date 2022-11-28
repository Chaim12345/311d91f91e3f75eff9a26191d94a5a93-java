package androidx.room;

import androidx.room.InvalidationTracker;
import java.util.Set;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelIterator;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000*\u0010\u0012\f\u0012\n \u0002*\u0004\u0018\u00018\u00008\u00000\u0001H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"R", "Lkotlinx/coroutines/flow/FlowCollector;", "kotlin.jvm.PlatformType", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "androidx.room.CoroutinesRoom$Companion$createFlow$1", f = "CoroutinesRoom.kt", i = {0, 0, 0, 0, 0}, l = {75}, m = "invokeSuspend", n = {"$this$flow", "observerChannel", "observer", "flowContext", "queryContext"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4"})
/* loaded from: classes.dex */
public final class CoroutinesRoom$Companion$createFlow$1 extends SuspendLambda implements Function2<FlowCollector<? super R>, Continuation<? super Unit>, Object> {

    /* renamed from: a  reason: collision with root package name */
    Object f3838a;

    /* renamed from: b  reason: collision with root package name */
    Object f3839b;

    /* renamed from: c  reason: collision with root package name */
    Object f3840c;

    /* renamed from: d  reason: collision with root package name */
    Object f3841d;

    /* renamed from: e  reason: collision with root package name */
    Object f3842e;

    /* renamed from: f  reason: collision with root package name */
    int f3843f;

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ String[] f3844g;

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ boolean f3845h;

    /* renamed from: i  reason: collision with root package name */
    final /* synthetic */ RoomDatabase f3846i;

    /* renamed from: j  reason: collision with root package name */
    final /* synthetic */ Callable f3847j;
    private FlowCollector p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u0002\"\u0004\b\u0000\u0010\u0000*\u00020\u0001H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"R", "Lkotlinx/coroutines/CoroutineScope;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    @DebugMetadata(c = "androidx.room.CoroutinesRoom$Companion$createFlow$1$1", f = "CoroutinesRoom.kt", i = {0, 1, 1, 1}, l = {80, 82}, m = "invokeSuspend", n = {"$this$withContext", "$this$withContext", "signal", "result"}, s = {"L$0", "L$0", "L$1", "L$3"})
    /* renamed from: androidx.room.CoroutinesRoom$Companion$createFlow$1$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* renamed from: a  reason: collision with root package name */
        Object f3848a;

        /* renamed from: b  reason: collision with root package name */
        Object f3849b;

        /* renamed from: c  reason: collision with root package name */
        Object f3850c;

        /* renamed from: d  reason: collision with root package name */
        Object f3851d;

        /* renamed from: e  reason: collision with root package name */
        int f3852e;

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ FlowCollector f3854g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ CoroutinesRoom$Companion$createFlow$1$observer$1 f3855h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ Channel f3856i;

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ CoroutineContext f3857j;
        private CoroutineScope p$;

        /* JADX INFO: Access modifiers changed from: package-private */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u0002\"\u0004\b\u0000\u0010\u0000*\u00020\u0001H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"R", "Lkotlinx/coroutines/CoroutineScope;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
        @DebugMetadata(c = "androidx.room.CoroutinesRoom$Companion$createFlow$1$1$1", f = "CoroutinesRoom.kt", i = {0}, l = {82}, m = "invokeSuspend", n = {"$this$withContext"}, s = {"L$0"})
        /* renamed from: androidx.room.CoroutinesRoom$Companion$createFlow$1$1$1  reason: invalid class name and collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C00081 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

            /* renamed from: a  reason: collision with root package name */
            Object f3858a;

            /* renamed from: b  reason: collision with root package name */
            int f3859b;

            /* renamed from: d  reason: collision with root package name */
            final /* synthetic */ Object f3861d;
            private CoroutineScope p$;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00081(Object obj, Continuation continuation) {
                super(2, continuation);
                this.f3861d = obj;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                C00081 c00081 = new C00081(this.f3861d, completion);
                c00081.p$ = (CoroutineScope) obj;
                return c00081;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00081) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                Object coroutine_suspended;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i2 = this.f3859b;
                if (i2 == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = this.p$;
                    FlowCollector flowCollector = AnonymousClass1.this.f3854g;
                    Object obj2 = this.f3861d;
                    this.f3858a = coroutineScope;
                    this.f3859b = 1;
                    if (flowCollector.emit(obj2, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    CoroutineScope coroutineScope2 = (CoroutineScope) this.f3858a;
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(FlowCollector flowCollector, CoroutinesRoom$Companion$createFlow$1$observer$1 coroutinesRoom$Companion$createFlow$1$observer$1, Channel channel, CoroutineContext coroutineContext, Continuation continuation) {
            super(2, continuation);
            this.f3854g = flowCollector;
            this.f3855h = coroutinesRoom$Companion$createFlow$1$observer$1;
            this.f3856i = channel;
            this.f3857j = coroutineContext;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.f3854g, this.f3855h, this.f3856i, this.f3857j, completion);
            anonymousClass1.p$ = (CoroutineScope) obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x005a A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:20:0x005b  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x0066 A[Catch: all -> 0x009f, TRY_LEAVE, TryCatch #1 {all -> 0x009f, blocks: (B:17:0x004e, B:21:0x005e, B:23:0x0066), top: B:36:0x004e }] */
        /* JADX WARN: Removed duplicated region for block: B:27:0x008f  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x008d -> B:36:0x004e). Please submit an issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended;
            AnonymousClass1 anonymousClass1;
            CoroutineScope coroutineScope;
            ChannelIterator it;
            Object hasNext;
            coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.f3852e;
            try {
            } catch (Throwable th) {
                th = th;
                anonymousClass1 = this;
            }
            try {
                if (i2 == 0) {
                    ResultKt.throwOnFailure(obj);
                    coroutineScope = this.p$;
                    CoroutinesRoom$Companion$createFlow$1.this.f3846i.getInvalidationTracker().addObserver(this.f3855h);
                    it = this.f3856i.iterator();
                } else if (i2 == 1) {
                    it = (ChannelIterator) this.f3849b;
                    CoroutineScope coroutineScope2 = (CoroutineScope) this.f3848a;
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope3 = coroutineScope2;
                    anonymousClass1 = this;
                    if (!((Boolean) obj).booleanValue()) {
                        Object call = CoroutinesRoom$Companion$createFlow$1.this.f3847j.call();
                        CoroutineContext coroutineContext = anonymousClass1.f3857j;
                        C00081 c00081 = new C00081(call, null);
                        anonymousClass1.f3848a = coroutineScope3;
                        anonymousClass1.f3849b = (Unit) it.next();
                        anonymousClass1.f3850c = it;
                        anonymousClass1.f3851d = call;
                        anonymousClass1.f3852e = 2;
                        if (BuildersKt.withContext(coroutineContext, c00081, anonymousClass1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        coroutineScope = coroutineScope3;
                        anonymousClass1.f3848a = coroutineScope;
                        anonymousClass1.f3849b = it;
                        anonymousClass1.f3852e = 1;
                        hasNext = it.hasNext(anonymousClass1);
                        if (hasNext != coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        coroutineScope3 = coroutineScope;
                        obj = hasNext;
                        if (!((Boolean) obj).booleanValue()) {
                            CoroutinesRoom$Companion$createFlow$1.this.f3846i.getInvalidationTracker().removeObserver(anonymousClass1.f3855h);
                            return Unit.INSTANCE;
                        }
                    }
                } else if (i2 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    it = (ChannelIterator) this.f3850c;
                    Unit unit = (Unit) this.f3849b;
                    CoroutineScope coroutineScope4 = (CoroutineScope) this.f3848a;
                    ResultKt.throwOnFailure(obj);
                    coroutineScope = coroutineScope4;
                }
                anonymousClass1.f3848a = coroutineScope;
                anonymousClass1.f3849b = it;
                anonymousClass1.f3852e = 1;
                hasNext = it.hasNext(anonymousClass1);
                if (hasNext != coroutine_suspended) {
                }
            } catch (Throwable th2) {
                th = th2;
                CoroutinesRoom$Companion$createFlow$1.this.f3846i.getInvalidationTracker().removeObserver(anonymousClass1.f3855h);
                throw th;
            }
            anonymousClass1 = this;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CoroutinesRoom$Companion$createFlow$1(String[] strArr, boolean z, RoomDatabase roomDatabase, Callable callable, Continuation continuation) {
        super(2, continuation);
        this.f3844g = strArr;
        this.f3845h = z;
        this.f3846i = roomDatabase;
        this.f3847j = callable;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        CoroutinesRoom$Companion$createFlow$1 coroutinesRoom$Companion$createFlow$1 = new CoroutinesRoom$Companion$createFlow$1(this.f3844g, this.f3845h, this.f3846i, this.f3847j, completion);
        coroutinesRoom$Companion$createFlow$1.p$ = (FlowCollector) obj;
        return coroutinesRoom$Companion$createFlow$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Continuation<? super Unit> continuation) {
        return ((CoroutinesRoom$Companion$createFlow$1) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference failed for: r10v0, types: [java.lang.Object, androidx.room.CoroutinesRoom$Companion$createFlow$1$observer$1] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.f3843f;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = this.p$;
            final Channel Channel$default = ChannelKt.Channel$default(-1, null, null, 6, null);
            final String[] strArr = this.f3844g;
            ?? r10 = new InvalidationTracker.Observer(this, strArr) { // from class: androidx.room.CoroutinesRoom$Companion$createFlow$1$observer$1
                @Override // androidx.room.InvalidationTracker.Observer
                public void onInvalidated(@NotNull Set<String> tables) {
                    Intrinsics.checkParameterIsNotNull(tables, "tables");
                    Channel$default.offer(Unit.INSTANCE);
                }
            };
            Channel$default.offer(Unit.INSTANCE);
            CoroutineContext context = getContext();
            CoroutineDispatcher transactionDispatcher = this.f3845h ? CoroutinesRoomKt.getTransactionDispatcher(this.f3846i) : CoroutinesRoomKt.getQueryDispatcher(this.f3846i);
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(flowCollector, r10, Channel$default, context, null);
            this.f3838a = flowCollector;
            this.f3839b = Channel$default;
            this.f3840c = r10;
            this.f3841d = context;
            this.f3842e = transactionDispatcher;
            this.f3843f = 1;
            if (BuildersKt.withContext(transactionDispatcher, anonymousClass1, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i2 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            CoroutineDispatcher coroutineDispatcher = (CoroutineDispatcher) this.f3842e;
            CoroutineContext coroutineContext = (CoroutineContext) this.f3841d;
            CoroutinesRoom$Companion$createFlow$1$observer$1 coroutinesRoom$Companion$createFlow$1$observer$1 = (CoroutinesRoom$Companion$createFlow$1$observer$1) this.f3840c;
            Channel channel = (Channel) this.f3839b;
            FlowCollector flowCollector2 = (FlowCollector) this.f3838a;
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
