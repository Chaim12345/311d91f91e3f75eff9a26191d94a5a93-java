package androidx.lifecycle;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.MainCoroutineDispatcher;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelIterator;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u0002\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0001H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/flow/FlowCollector;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 4, 1})
@DebugMetadata(c = "androidx.lifecycle.FlowLiveDataConversions$asFlow$1", f = "FlowLiveData.kt", i = {0, 0, 0, 1, 1, 2, 2}, l = {96, 100, 101}, m = "invokeSuspend", n = {"$this$flow", "channel", "observer", "$this$flow", "observer", "$this$flow", "observer"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$0", "L$1"})
/* loaded from: classes.dex */
public final class FlowLiveDataConversions$asFlow$1 extends SuspendLambda implements Function2<FlowCollector<? super T>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;

    /* renamed from: a  reason: collision with root package name */
    Object f3191a;

    /* renamed from: b  reason: collision with root package name */
    Object f3192b;

    /* renamed from: c  reason: collision with root package name */
    int f3193c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ LiveData f3194d;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u0002\"\u0004\b\u0000\u0010\u0000*\u00020\u0001H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CoroutineScope;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 4, 1})
    @DebugMetadata(c = "androidx.lifecycle.FlowLiveDataConversions$asFlow$1$1", f = "FlowLiveData.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.lifecycle.FlowLiveDataConversions$asFlow$1$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* renamed from: a  reason: collision with root package name */
        int f3195a;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ Observer f3197c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Observer observer, Continuation continuation) {
            super(2, continuation);
            this.f3197c = observer;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
            Intrinsics.checkNotNullParameter(completion, "completion");
            return new AnonymousClass1(this.f3197c, completion);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.f3195a == 0) {
                ResultKt.throwOnFailure(obj);
                FlowLiveDataConversions$asFlow$1.this.f3194d.observeForever(this.f3197c);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u0002\"\u0004\b\u0000\u0010\u0000*\u00020\u0001H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CoroutineScope;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 4, 1})
    @DebugMetadata(c = "androidx.lifecycle.FlowLiveDataConversions$asFlow$1$2", f = "FlowLiveData.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.lifecycle.FlowLiveDataConversions$asFlow$1$2  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* renamed from: a  reason: collision with root package name */
        int f3198a;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ Observer f3200c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Observer observer, Continuation continuation) {
            super(2, continuation);
            this.f3200c = observer;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
            Intrinsics.checkNotNullParameter(completion, "completion");
            return new AnonymousClass2(this.f3200c, completion);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.f3198a == 0) {
                ResultKt.throwOnFailure(obj);
                FlowLiveDataConversions$asFlow$1.this.f3194d.removeObserver(this.f3200c);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowLiveDataConversions$asFlow$1(LiveData liveData, Continuation continuation) {
        super(2, continuation);
        this.f3194d = liveData;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkNotNullParameter(completion, "completion");
        FlowLiveDataConversions$asFlow$1 flowLiveDataConversions$asFlow$1 = new FlowLiveDataConversions$asFlow$1(this.f3194d, completion);
        flowLiveDataConversions$asFlow$1.L$0 = obj;
        return flowLiveDataConversions$asFlow$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Continuation<? super Unit> continuation) {
        return ((FlowLiveDataConversions$asFlow$1) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0097 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00a5 A[Catch: all -> 0x00d4, TRY_LEAVE, TryCatch #0 {all -> 0x00d4, blocks: (B:29:0x009d, B:31:0x00a5), top: B:45:0x009d }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00bc  */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4, types: [androidx.lifecycle.Observer] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:34:0x00b8 -> B:47:0x0089). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        FlowCollector flowCollector;
        Channel channel;
        Throwable th;
        FlowLiveDataConversions$asFlow$1 flowLiveDataConversions$asFlow$1;
        FlowCollector flowCollector2;
        Observer observer;
        ChannelIterator channelIterator;
        ChannelIterator channelIterator2;
        Object hasNext;
        Observer observer2;
        Observer observer3;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.f3193c;
        Observer observer4 = 1;
        try {
            try {
                if (i2 == 0) {
                    ResultKt.throwOnFailure(obj);
                    flowCollector = (FlowCollector) this.L$0;
                    final Channel Channel$default = ChannelKt.Channel$default(-1, null, null, 6, null);
                    Observer observer5 = new Observer<T>() { // from class: androidx.lifecycle.FlowLiveDataConversions$asFlow$1$observer$1
                        @Override // androidx.lifecycle.Observer
                        public final void onChanged(T t2) {
                            Channel.this.offer(t2);
                        }
                    };
                    MainCoroutineDispatcher immediate = Dispatchers.getMain().getImmediate();
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(observer5, null);
                    this.L$0 = flowCollector;
                    this.f3191a = Channel$default;
                    this.f3192b = observer5;
                    this.f3193c = 1;
                    if (BuildersKt.withContext(immediate, anonymousClass1, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    channel = Channel$default;
                    observer3 = observer5;
                } else if (i2 != 1) {
                    try {
                        if (i2 == 2) {
                            ChannelIterator channelIterator3 = (ChannelIterator) this.f3192b;
                            Observer observer6 = (Observer) this.f3191a;
                            FlowCollector flowCollector3 = (FlowCollector) this.L$0;
                            ResultKt.throwOnFailure(obj);
                            flowCollector2 = flowCollector3;
                            observer = observer6;
                            channelIterator = channelIterator3;
                            flowLiveDataConversions$asFlow$1 = this;
                            if (((Boolean) obj).booleanValue()) {
                            }
                        } else if (i2 != 3) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        } else {
                            ChannelIterator channelIterator4 = (ChannelIterator) this.f3192b;
                            Observer observer7 = (Observer) this.f3191a;
                            flowCollector = (FlowCollector) this.L$0;
                            ResultKt.throwOnFailure(obj);
                            channelIterator2 = channelIterator4;
                            observer2 = observer7;
                            flowLiveDataConversions$asFlow$1 = this;
                            observer4 = observer2;
                            flowLiveDataConversions$asFlow$1.L$0 = flowCollector;
                            flowLiveDataConversions$asFlow$1.f3191a = observer4;
                            flowLiveDataConversions$asFlow$1.f3192b = channelIterator2;
                            flowLiveDataConversions$asFlow$1.f3193c = 2;
                            hasNext = channelIterator2.hasNext(flowLiveDataConversions$asFlow$1);
                            if (hasNext == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            Observer observer8 = observer4;
                            channelIterator = channelIterator2;
                            obj = hasNext;
                            flowCollector2 = flowCollector;
                            observer = observer8;
                            try {
                                if (((Boolean) obj).booleanValue()) {
                                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain().getImmediate(), null, new AnonymousClass2(observer, null), 2, null);
                                    return Unit.INSTANCE;
                                }
                                Object next = channelIterator.next();
                                flowLiveDataConversions$asFlow$1.L$0 = flowCollector2;
                                flowLiveDataConversions$asFlow$1.f3191a = observer;
                                flowLiveDataConversions$asFlow$1.f3192b = channelIterator;
                                flowLiveDataConversions$asFlow$1.f3193c = 3;
                                if (flowCollector2.emit(next, flowLiveDataConversions$asFlow$1) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                channelIterator2 = channelIterator;
                                observer4 = observer;
                                flowCollector = flowCollector2;
                                flowLiveDataConversions$asFlow$1.L$0 = flowCollector;
                                flowLiveDataConversions$asFlow$1.f3191a = observer4;
                                flowLiveDataConversions$asFlow$1.f3192b = channelIterator2;
                                flowLiveDataConversions$asFlow$1.f3193c = 2;
                                hasNext = channelIterator2.hasNext(flowLiveDataConversions$asFlow$1);
                                if (hasNext == coroutine_suspended) {
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                observer4 = observer;
                                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain().getImmediate(), null, new AnonymousClass2(observer4, null), 2, null);
                                throw th;
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        flowLiveDataConversions$asFlow$1 = this;
                        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain().getImmediate(), null, new AnonymousClass2(observer4, null), 2, null);
                        throw th;
                    }
                } else {
                    channel = (Channel) this.f3191a;
                    flowCollector = (FlowCollector) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    observer3 = (Observer) this.f3192b;
                }
                flowLiveDataConversions$asFlow$1.L$0 = flowCollector;
                flowLiveDataConversions$asFlow$1.f3191a = observer4;
                flowLiveDataConversions$asFlow$1.f3192b = channelIterator2;
                flowLiveDataConversions$asFlow$1.f3193c = 2;
                hasNext = channelIterator2.hasNext(flowLiveDataConversions$asFlow$1);
                if (hasNext == coroutine_suspended) {
                }
            } catch (Throwable th4) {
                th = th4;
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain().getImmediate(), null, new AnonymousClass2(observer4, null), 2, null);
                throw th;
            }
            channelIterator2 = channel.iterator();
            observer2 = observer3;
            flowLiveDataConversions$asFlow$1 = this;
            observer4 = observer2;
        } catch (Throwable th5) {
            th = th5;
            observer4 = observer3;
            flowLiveDataConversions$asFlow$1 = this;
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain().getImmediate(), null, new AnonymousClass2(observer4, null), 2, null);
            throw th;
        }
    }
}
