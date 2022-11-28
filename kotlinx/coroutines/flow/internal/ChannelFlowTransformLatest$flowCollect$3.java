package kotlinx.coroutines.flow.internal;

import java.util.concurrent.CancellationException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3", f = "Merge.kt", i = {}, l = {27}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class ChannelFlowTransformLatest$flowCollect$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;

    /* renamed from: a  reason: collision with root package name */
    int f12262a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ ChannelFlowTransformLatest f12263b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ FlowCollector f12264c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static final class AnonymousClass1<T> implements FlowCollector {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ Ref.ObjectRef f12265a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ CoroutineScope f12266b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ ChannelFlowTransformLatest f12267c;

        /* renamed from: d  reason: collision with root package name */
        final /* synthetic */ FlowCollector f12268d;

        /* JADX INFO: Access modifiers changed from: package-private */
        @DebugMetadata(c = "kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3$1$2", f = "Merge.kt", i = {}, l = {34}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3$1$2  reason: invalid class name */
        /* loaded from: classes3.dex */
        public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

            /* renamed from: a  reason: collision with root package name */
            int f12269a;

            /* renamed from: b  reason: collision with root package name */
            final /* synthetic */ ChannelFlowTransformLatest f12270b;

            /* renamed from: c  reason: collision with root package name */
            final /* synthetic */ FlowCollector f12271c;

            /* renamed from: d  reason: collision with root package name */
            final /* synthetic */ Object f12272d;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass2(ChannelFlowTransformLatest channelFlowTransformLatest, FlowCollector flowCollector, Object obj, Continuation continuation) {
                super(2, continuation);
                this.f12270b = channelFlowTransformLatest;
                this.f12271c = flowCollector;
                this.f12272d = obj;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new AnonymousClass2(this.f12270b, this.f12271c, this.f12272d, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                Object coroutine_suspended;
                Function3 function3;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i2 = this.f12269a;
                if (i2 == 0) {
                    ResultKt.throwOnFailure(obj);
                    function3 = this.f12270b.transform;
                    FlowCollector flowCollector = this.f12271c;
                    Object obj2 = this.f12272d;
                    this.f12269a = 1;
                    if (function3.invoke(flowCollector, obj2, this) == coroutine_suspended) {
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

        AnonymousClass1(Ref.ObjectRef objectRef, CoroutineScope coroutineScope, ChannelFlowTransformLatest channelFlowTransformLatest, FlowCollector flowCollector) {
            this.f12265a = objectRef;
            this.f12266b = coroutineScope;
            this.f12267c = channelFlowTransformLatest;
            this.f12268d = flowCollector;
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
        /* JADX WARN: Removed duplicated region for block: B:14:0x003b  */
        @Override // kotlinx.coroutines.flow.FlowCollector
        @Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object emit(T t2, @NotNull Continuation<? super Unit> continuation) {
            ChannelFlowTransformLatest$flowCollect$3$1$emit$1 channelFlowTransformLatest$flowCollect$3$1$emit$1;
            Object coroutine_suspended;
            int i2;
            AnonymousClass1<T> anonymousClass1;
            Job launch$default;
            if (continuation instanceof ChannelFlowTransformLatest$flowCollect$3$1$emit$1) {
                channelFlowTransformLatest$flowCollect$3$1$emit$1 = (ChannelFlowTransformLatest$flowCollect$3$1$emit$1) continuation;
                int i3 = channelFlowTransformLatest$flowCollect$3$1$emit$1.f12278f;
                if ((i3 & Integer.MIN_VALUE) != 0) {
                    channelFlowTransformLatest$flowCollect$3$1$emit$1.f12278f = i3 - Integer.MIN_VALUE;
                    Object obj = channelFlowTransformLatest$flowCollect$3$1$emit$1.f12276d;
                    coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    i2 = channelFlowTransformLatest$flowCollect$3$1$emit$1.f12278f;
                    if (i2 != 0) {
                        ResultKt.throwOnFailure(obj);
                        Job job = (Job) this.f12265a.element;
                        if (job != null) {
                            job.cancel((CancellationException) new ChildCancelledException());
                            channelFlowTransformLatest$flowCollect$3$1$emit$1.f12273a = this;
                            channelFlowTransformLatest$flowCollect$3$1$emit$1.f12274b = t2;
                            channelFlowTransformLatest$flowCollect$3$1$emit$1.f12275c = job;
                            channelFlowTransformLatest$flowCollect$3$1$emit$1.f12278f = 1;
                            if (job.join(channelFlowTransformLatest$flowCollect$3$1$emit$1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                        anonymousClass1 = this;
                    } else if (i2 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    } else {
                        Job job2 = (Job) channelFlowTransformLatest$flowCollect$3$1$emit$1.f12275c;
                        t2 = (T) channelFlowTransformLatest$flowCollect$3$1$emit$1.f12274b;
                        anonymousClass1 = (AnonymousClass1) channelFlowTransformLatest$flowCollect$3$1$emit$1.f12273a;
                        ResultKt.throwOnFailure(obj);
                    }
                    Ref.ObjectRef objectRef = anonymousClass1.f12265a;
                    launch$default = BuildersKt__Builders_commonKt.launch$default(anonymousClass1.f12266b, null, CoroutineStart.UNDISPATCHED, new AnonymousClass2(anonymousClass1.f12267c, anonymousClass1.f12268d, t2, null), 1, null);
                    objectRef.element = (T) launch$default;
                    return Unit.INSTANCE;
                }
            }
            channelFlowTransformLatest$flowCollect$3$1$emit$1 = new ChannelFlowTransformLatest$flowCollect$3$1$emit$1(this, continuation);
            Object obj2 = channelFlowTransformLatest$flowCollect$3$1$emit$1.f12276d;
            coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            i2 = channelFlowTransformLatest$flowCollect$3$1$emit$1.f12278f;
            if (i2 != 0) {
            }
            Ref.ObjectRef objectRef2 = anonymousClass1.f12265a;
            launch$default = BuildersKt__Builders_commonKt.launch$default(anonymousClass1.f12266b, null, CoroutineStart.UNDISPATCHED, new AnonymousClass2(anonymousClass1.f12267c, anonymousClass1.f12268d, t2, null), 1, null);
            objectRef2.element = (T) launch$default;
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChannelFlowTransformLatest$flowCollect$3(ChannelFlowTransformLatest channelFlowTransformLatest, FlowCollector flowCollector, Continuation continuation) {
        super(2, continuation);
        this.f12263b = channelFlowTransformLatest;
        this.f12264c = flowCollector;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        ChannelFlowTransformLatest$flowCollect$3 channelFlowTransformLatest$flowCollect$3 = new ChannelFlowTransformLatest$flowCollect$3(this.f12263b, this.f12264c, continuation);
        channelFlowTransformLatest$flowCollect$3.L$0 = obj;
        return channelFlowTransformLatest$flowCollect$3;
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((ChannelFlowTransformLatest$flowCollect$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.f12262a;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            ChannelFlowTransformLatest channelFlowTransformLatest = this.f12263b;
            Flow flow = channelFlowTransformLatest.f12258a;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(objectRef, (CoroutineScope) this.L$0, channelFlowTransformLatest, this.f12264c);
            this.f12262a = 1;
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
