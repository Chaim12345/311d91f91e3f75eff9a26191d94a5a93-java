package kotlinx.coroutines.flow.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.sync.Semaphore;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class ChannelFlowMerge$collectTo$2<T> implements FlowCollector {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Job f12245a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ Semaphore f12246b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ ProducerScope f12247c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ SendingCollector f12248d;

    /* JADX INFO: Access modifiers changed from: package-private */
    @DebugMetadata(c = "kotlinx.coroutines.flow.internal.ChannelFlowMerge$collectTo$2$1", f = "Merge.kt", i = {}, l = {69}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: kotlinx.coroutines.flow.internal.ChannelFlowMerge$collectTo$2$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* renamed from: a  reason: collision with root package name */
        int f12249a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ Flow f12250b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ SendingCollector f12251c;

        /* renamed from: d  reason: collision with root package name */
        final /* synthetic */ Semaphore f12252d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Flow flow, SendingCollector sendingCollector, Semaphore semaphore, Continuation continuation) {
            super(2, continuation);
            this.f12250b = flow;
            this.f12251c = sendingCollector;
            this.f12252d = semaphore;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new AnonymousClass1(this.f12250b, this.f12251c, this.f12252d, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended;
            coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.f12249a;
            try {
                if (i2 == 0) {
                    ResultKt.throwOnFailure(obj);
                    Flow flow = this.f12250b;
                    SendingCollector sendingCollector = this.f12251c;
                    this.f12249a = 1;
                    if (flow.collect(sendingCollector, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    ResultKt.throwOnFailure(obj);
                }
                this.f12252d.release();
                return Unit.INSTANCE;
            } catch (Throwable th) {
                this.f12252d.release();
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ChannelFlowMerge$collectTo$2(Job job, Semaphore semaphore, ProducerScope producerScope, SendingCollector sendingCollector) {
        this.f12245a = job;
        this.f12246b = semaphore;
        this.f12247c = producerScope;
        this.f12248d = sendingCollector;
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation) {
        return emit((Flow) ((Flow) obj), (Continuation<? super Unit>) continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0039  */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object emit(@NotNull Flow<? extends T> flow, @NotNull Continuation<? super Unit> continuation) {
        ChannelFlowMerge$collectTo$2$emit$1 channelFlowMerge$collectTo$2$emit$1;
        Object coroutine_suspended;
        int i2;
        ChannelFlowMerge$collectTo$2<T> channelFlowMerge$collectTo$2;
        if (continuation instanceof ChannelFlowMerge$collectTo$2$emit$1) {
            channelFlowMerge$collectTo$2$emit$1 = (ChannelFlowMerge$collectTo$2$emit$1) continuation;
            int i3 = channelFlowMerge$collectTo$2$emit$1.f12257e;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                channelFlowMerge$collectTo$2$emit$1.f12257e = i3 - Integer.MIN_VALUE;
                Object obj = channelFlowMerge$collectTo$2$emit$1.f12255c;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = channelFlowMerge$collectTo$2$emit$1.f12257e;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    Job job = this.f12245a;
                    if (job != null) {
                        JobKt.ensureActive(job);
                    }
                    Semaphore semaphore = this.f12246b;
                    channelFlowMerge$collectTo$2$emit$1.f12253a = this;
                    channelFlowMerge$collectTo$2$emit$1.f12254b = flow;
                    channelFlowMerge$collectTo$2$emit$1.f12257e = 1;
                    if (semaphore.acquire(channelFlowMerge$collectTo$2$emit$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    channelFlowMerge$collectTo$2 = this;
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    flow = (Flow) channelFlowMerge$collectTo$2$emit$1.f12254b;
                    channelFlowMerge$collectTo$2 = (ChannelFlowMerge$collectTo$2) channelFlowMerge$collectTo$2$emit$1.f12253a;
                    ResultKt.throwOnFailure(obj);
                }
                BuildersKt__Builders_commonKt.launch$default(channelFlowMerge$collectTo$2.f12247c, null, null, new AnonymousClass1(flow, channelFlowMerge$collectTo$2.f12248d, channelFlowMerge$collectTo$2.f12246b, null), 3, null);
                return Unit.INSTANCE;
            }
        }
        channelFlowMerge$collectTo$2$emit$1 = new ChannelFlowMerge$collectTo$2$emit$1(this, continuation);
        Object obj2 = channelFlowMerge$collectTo$2$emit$1.f12255c;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = channelFlowMerge$collectTo$2$emit$1.f12257e;
        if (i2 != 0) {
        }
        BuildersKt__Builders_commonKt.launch$default(channelFlowMerge$collectTo$2.f12247c, null, null, new AnonymousClass1(flow, channelFlowMerge$collectTo$2.f12248d, channelFlowMerge$collectTo$2.f12246b, null), 3, null);
        return Unit.INSTANCE;
    }
}
