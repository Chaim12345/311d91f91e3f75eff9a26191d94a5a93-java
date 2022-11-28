package kotlinx.coroutines.flow;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.SharingStarted;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1", f = "Share.kt", i = {}, l = {214, 218, 219, 225}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class FlowKt__ShareKt$launchSharing$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: a  reason: collision with root package name */
    int f11984a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ SharingStarted f11985b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ Flow f11986c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ MutableSharedFlow f11987d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ Object f11988e;

    /* JADX INFO: Access modifiers changed from: package-private */
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1$1", f = "Share.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<Integer, Continuation<? super Boolean>, Object> {

        /* renamed from: a  reason: collision with root package name */
        int f11989a;

        /* renamed from: b  reason: collision with root package name */
        /* synthetic */ int f11990b;

        AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(continuation);
            anonymousClass1.f11990b = ((Number) obj).intValue();
            return anonymousClass1;
        }

        @Nullable
        public final Object invoke(int i2, @Nullable Continuation<? super Boolean> continuation) {
            return ((AnonymousClass1) create(Integer.valueOf(i2), continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Integer num, Continuation<? super Boolean> continuation) {
            return invoke(num.intValue(), continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.f11989a == 0) {
                ResultKt.throwOnFailure(obj);
                return Boxing.boxBoolean(this.f11990b > 0);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1$2", f = "Share.kt", i = {}, l = {227}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1$2  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<SharingCommand, Continuation<? super Unit>, Object> {

        /* renamed from: a  reason: collision with root package name */
        int f11991a;

        /* renamed from: b  reason: collision with root package name */
        /* synthetic */ Object f11992b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ Flow f11993c;

        /* renamed from: d  reason: collision with root package name */
        final /* synthetic */ MutableSharedFlow f11994d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ Object f11995e;

        /* renamed from: kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1$2$WhenMappings */
        /* loaded from: classes3.dex */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[SharingCommand.values().length];
                iArr[SharingCommand.START.ordinal()] = 1;
                iArr[SharingCommand.STOP.ordinal()] = 2;
                iArr[SharingCommand.STOP_AND_RESET_REPLAY_CACHE.ordinal()] = 3;
                $EnumSwitchMapping$0 = iArr;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Flow flow, MutableSharedFlow mutableSharedFlow, Object obj, Continuation continuation) {
            super(2, continuation);
            this.f11993c = flow;
            this.f11994d = mutableSharedFlow;
            this.f11995e = obj;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.f11993c, this.f11994d, this.f11995e, continuation);
            anonymousClass2.f11992b = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull SharingCommand sharingCommand, @Nullable Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(sharingCommand, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended;
            coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.f11991a;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                int i3 = WhenMappings.$EnumSwitchMapping$0[((SharingCommand) this.f11992b).ordinal()];
                if (i3 == 1) {
                    Flow flow = this.f11993c;
                    MutableSharedFlow mutableSharedFlow = this.f11994d;
                    this.f11991a = 1;
                    if (flow.collect(mutableSharedFlow, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (i3 == 3) {
                    Object obj2 = this.f11995e;
                    if (obj2 == SharedFlowKt.NO_VALUE) {
                        this.f11994d.resetReplayCache();
                    } else {
                        this.f11994d.tryEmit(obj2);
                    }
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
    public FlowKt__ShareKt$launchSharing$1(SharingStarted sharingStarted, Flow flow, MutableSharedFlow mutableSharedFlow, Object obj, Continuation continuation) {
        super(2, continuation);
        this.f11985b = sharingStarted;
        this.f11986c = flow;
        this.f11987d = mutableSharedFlow;
        this.f11988e = obj;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new FlowKt__ShareKt$launchSharing$1(this.f11985b, this.f11986c, this.f11987d, this.f11988e, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((FlowKt__ShareKt$launchSharing$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0068 A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        Flow flow;
        MutableSharedFlow mutableSharedFlow;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.f11984a;
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 == 2) {
                    ResultKt.throwOnFailure(obj);
                    flow = this.f11986c;
                    mutableSharedFlow = this.f11987d;
                    this.f11984a = 3;
                    if (flow.collect(mutableSharedFlow, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return Unit.INSTANCE;
                } else if (i2 != 3 && i2 != 4) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        }
        ResultKt.throwOnFailure(obj);
        SharingStarted sharingStarted = this.f11985b;
        SharingStarted.Companion companion = SharingStarted.Companion;
        if (sharingStarted == companion.getEagerly()) {
            Flow flow2 = this.f11986c;
            MutableSharedFlow mutableSharedFlow2 = this.f11987d;
            this.f11984a = 1;
            if (flow2.collect(mutableSharedFlow2, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (this.f11985b == companion.getLazily()) {
            StateFlow<Integer> subscriptionCount = this.f11987d.getSubscriptionCount();
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(null);
            this.f11984a = 2;
            if (FlowKt.first(subscriptionCount, anonymousClass1, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            flow = this.f11986c;
            mutableSharedFlow = this.f11987d;
            this.f11984a = 3;
            if (flow.collect(mutableSharedFlow, this) == coroutine_suspended) {
            }
        } else {
            Flow distinctUntilChanged = FlowKt.distinctUntilChanged(this.f11985b.command(this.f11987d.getSubscriptionCount()));
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.f11986c, this.f11987d, this.f11988e, null);
            this.f11984a = 4;
            if (FlowKt.collectLatest(distinctUntilChanged, anonymousClass2, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Unit.INSTANCE;
    }
}
