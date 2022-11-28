package kotlinx.coroutines.flow.internal;

import java.util.concurrent.atomic.AtomicInteger;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.collections.IndexedValue;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.YieldKt;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2", f = "Combine.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2}, l = {57, 79, 82}, m = "invokeSuspend", n = {"latestValues", "resultChannel", "lastReceivedEpoch", "remainingAbsentValues", "currentEpoch", "latestValues", "resultChannel", "lastReceivedEpoch", "remainingAbsentValues", "currentEpoch", "latestValues", "resultChannel", "lastReceivedEpoch", "remainingAbsentValues", "currentEpoch"}, s = {"L$0", "L$1", "L$2", "I$0", "I$1", "L$0", "L$1", "L$2", "I$0", "I$1", "L$0", "L$1", "L$2", "I$0", "I$1"})
/* loaded from: classes3.dex */
final class CombineKt$combineInternal$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;

    /* renamed from: a  reason: collision with root package name */
    Object f12285a;

    /* renamed from: b  reason: collision with root package name */
    Object f12286b;

    /* renamed from: c  reason: collision with root package name */
    int f12287c;

    /* renamed from: d  reason: collision with root package name */
    int f12288d;

    /* renamed from: e  reason: collision with root package name */
    int f12289e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ Flow[] f12290f;

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ Function0 f12291g;

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ Function3 f12292h;

    /* renamed from: i  reason: collision with root package name */
    final /* synthetic */ FlowCollector f12293i;

    /* JADX INFO: Access modifiers changed from: package-private */
    @DebugMetadata(c = "kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2$1", f = "Combine.kt", i = {}, l = {34}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* renamed from: a  reason: collision with root package name */
        int f12294a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ Flow[] f12295b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ int f12296c;

        /* renamed from: d  reason: collision with root package name */
        final /* synthetic */ AtomicInteger f12297d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ Channel f12298e;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2$1$1  reason: invalid class name and collision with other inner class name */
        /* loaded from: classes3.dex */
        public static final class C00501<T> implements FlowCollector {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ Channel f12299a;

            /* renamed from: b  reason: collision with root package name */
            final /* synthetic */ int f12300b;

            C00501(Channel channel, int i2) {
                this.f12299a = channel;
                this.f12300b = i2;
            }

            /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
            /* JADX WARN: Removed duplicated region for block: B:16:0x0038  */
            /* JADX WARN: Removed duplicated region for block: B:21:0x0055 A[RETURN] */
            @Override // kotlinx.coroutines.flow.FlowCollector
            @Nullable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final Object emit(T t2, @NotNull Continuation<? super Unit> continuation) {
                CombineKt$combineInternal$2$1$1$emit$1 combineKt$combineInternal$2$1$1$emit$1;
                Object coroutine_suspended;
                int i2;
                if (continuation instanceof CombineKt$combineInternal$2$1$1$emit$1) {
                    combineKt$combineInternal$2$1$1$emit$1 = (CombineKt$combineInternal$2$1$1$emit$1) continuation;
                    int i3 = combineKt$combineInternal$2$1$1$emit$1.f12303c;
                    if ((i3 & Integer.MIN_VALUE) != 0) {
                        combineKt$combineInternal$2$1$1$emit$1.f12303c = i3 - Integer.MIN_VALUE;
                        Object obj = combineKt$combineInternal$2$1$1$emit$1.f12301a;
                        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        i2 = combineKt$combineInternal$2$1$1$emit$1.f12303c;
                        if (i2 != 0) {
                            ResultKt.throwOnFailure(obj);
                            Channel channel = this.f12299a;
                            IndexedValue indexedValue = new IndexedValue(this.f12300b, t2);
                            combineKt$combineInternal$2$1$1$emit$1.f12303c = 1;
                            if (channel.send(indexedValue, combineKt$combineInternal$2$1$1$emit$1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else if (i2 != 1) {
                            if (i2 == 2) {
                                ResultKt.throwOnFailure(obj);
                                return Unit.INSTANCE;
                            }
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        } else {
                            ResultKt.throwOnFailure(obj);
                        }
                        combineKt$combineInternal$2$1$1$emit$1.f12303c = 2;
                        if (YieldKt.yield(combineKt$combineInternal$2$1$1$emit$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        return Unit.INSTANCE;
                    }
                }
                combineKt$combineInternal$2$1$1$emit$1 = new CombineKt$combineInternal$2$1$1$emit$1(this, continuation);
                Object obj2 = combineKt$combineInternal$2$1$1$emit$1.f12301a;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = combineKt$combineInternal$2$1$1$emit$1.f12303c;
                if (i2 != 0) {
                }
                combineKt$combineInternal$2$1$1$emit$1.f12303c = 2;
                if (YieldKt.yield(combineKt$combineInternal$2$1$1$emit$1) == coroutine_suspended) {
                }
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Flow[] flowArr, int i2, AtomicInteger atomicInteger, Channel channel, Continuation continuation) {
            super(2, continuation);
            this.f12295b = flowArr;
            this.f12296c = i2;
            this.f12297d = atomicInteger;
            this.f12298e = channel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new AnonymousClass1(this.f12295b, this.f12296c, this.f12297d, this.f12298e, continuation);
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
            AtomicInteger atomicInteger;
            coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.f12294a;
            try {
                if (i2 == 0) {
                    ResultKt.throwOnFailure(obj);
                    Flow[] flowArr = this.f12295b;
                    int i3 = this.f12296c;
                    Flow flow = flowArr[i3];
                    C00501 c00501 = new C00501(this.f12298e, i3);
                    this.f12294a = 1;
                    if (flow.collect(c00501, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    ResultKt.throwOnFailure(obj);
                }
                if (atomicInteger.decrementAndGet() == 0) {
                    SendChannel.DefaultImpls.close$default(this.f12298e, null, 1, null);
                }
                return Unit.INSTANCE;
            } finally {
                if (this.f12297d.decrementAndGet() == 0) {
                    SendChannel.DefaultImpls.close$default(this.f12298e, null, 1, null);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CombineKt$combineInternal$2(Flow[] flowArr, Function0 function0, Function3 function3, FlowCollector flowCollector, Continuation continuation) {
        super(2, continuation);
        this.f12290f = flowArr;
        this.f12291g = function0;
        this.f12292h = function3;
        this.f12293i = flowCollector;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        CombineKt$combineInternal$2 combineKt$combineInternal$2 = new CombineKt$combineInternal$2(this.f12290f, this.f12291g, this.f12292h, this.f12293i, continuation);
        combineKt$combineInternal$2.L$0 = obj;
        return combineKt$combineInternal$2;
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((CombineKt$combineInternal$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00ec A[LOOP:0: B:28:0x00ec->B:34:0x010f, LOOP_START, PHI: r3 r10 
      PHI: (r3v2 int) = (r3v1 int), (r3v3 int) binds: [B:25:0x00e7, B:34:0x010f] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r10v5 kotlin.collections.IndexedValue) = (r10v4 kotlin.collections.IndexedValue), (r10v18 kotlin.collections.IndexedValue) binds: [B:25:0x00e7, B:34:0x010f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Type inference failed for: r2v12, types: [int] */
    /* JADX WARN: Type inference failed for: r2v7, types: [int] */
    /* JADX WARN: Type inference failed for: r2v9, types: [int] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:41:0x0134 -> B:20:0x00c7). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:47:0x0169 -> B:45:0x0165). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        int length;
        Object[] objArr;
        byte[] bArr;
        CombineKt$combineInternal$2 combineKt$combineInternal$2;
        Channel channel;
        Object[] objArr2;
        Object obj2;
        CombineKt$combineInternal$2 combineKt$combineInternal$22;
        Channel channel2;
        byte b2;
        int i2;
        IndexedValue indexedValue;
        Object[] objArr3;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.f12289e;
        byte b3 = 0;
        int i4 = 2;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            length = this.f12290f.length;
            if (length == 0) {
                return Unit.INSTANCE;
            }
            objArr = new Object[length];
            ArraysKt___ArraysJvmKt.fill$default(objArr, NullSurrogateKt.UNINITIALIZED, 0, 0, 6, (Object) null);
            Channel Channel$default = ChannelKt.Channel$default(length, null, null, 6, null);
            AtomicInteger atomicInteger = new AtomicInteger(length);
            int i5 = 0;
            while (i5 < length) {
                int i6 = i5;
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass1(this.f12290f, i6, atomicInteger, Channel$default, null), 3, null);
                i5 = i6 + 1;
                atomicInteger = atomicInteger;
            }
            bArr = new byte[length];
            combineKt$combineInternal$2 = this;
            channel = Channel$default;
        } else if (i3 == 1) {
            ?? r2 = this.f12288d;
            i2 = this.f12287c;
            byte[] bArr2 = (byte[]) this.f12286b;
            channel2 = (Channel) this.f12285a;
            ResultKt.throwOnFailure(obj);
            obj2 = ((ChannelResult) obj).m1646unboximpl();
            objArr2 = (Object[]) this.L$0;
            combineKt$combineInternal$22 = this;
            b2 = r2;
            bArr = bArr2;
            indexedValue = (IndexedValue) ChannelResult.m1639getOrNullimpl(obj2);
            if (indexedValue != null) {
                return Unit.INSTANCE;
            }
            do {
                int index = indexedValue.getIndex();
                Object obj3 = objArr2[index];
                objArr2[index] = indexedValue.getValue();
                if (obj3 == NullSurrogateKt.UNINITIALIZED) {
                    i2--;
                }
                if (bArr[index] == b2) {
                    break;
                }
                bArr[index] = b2;
                indexedValue = (IndexedValue) ChannelResult.m1639getOrNullimpl(channel2.mo1628tryReceivePtdJZtk());
            } while (indexedValue != null);
            if (i2 != 0) {
                objArr3 = objArr2;
            } else {
                Object[] objArr4 = (Object[]) combineKt$combineInternal$22.f12291g.invoke();
                if (objArr4 == null) {
                    Function3 function3 = combineKt$combineInternal$22.f12292h;
                    FlowCollector flowCollector = combineKt$combineInternal$22.f12293i;
                    combineKt$combineInternal$22.L$0 = objArr2;
                    combineKt$combineInternal$22.f12285a = channel2;
                    combineKt$combineInternal$22.f12286b = bArr;
                    combineKt$combineInternal$22.f12287c = i2;
                    combineKt$combineInternal$22.f12288d = b2;
                    combineKt$combineInternal$22.f12289e = i4;
                    if (function3.invoke(flowCollector, objArr2, combineKt$combineInternal$22) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    length = i2;
                    b3 = b2;
                    channel = channel2;
                    combineKt$combineInternal$2 = combineKt$combineInternal$22;
                    objArr = objArr2;
                } else {
                    objArr3 = objArr2;
                    ArraysKt___ArraysJvmKt.copyInto$default(objArr2, objArr4, 0, 0, 0, 14, (Object) null);
                    Function3 function32 = combineKt$combineInternal$22.f12292h;
                    FlowCollector flowCollector2 = combineKt$combineInternal$22.f12293i;
                    combineKt$combineInternal$22.L$0 = objArr3;
                    combineKt$combineInternal$22.f12285a = channel2;
                    combineKt$combineInternal$22.f12286b = bArr;
                    combineKt$combineInternal$22.f12287c = i2;
                    combineKt$combineInternal$22.f12288d = b2;
                    combineKt$combineInternal$22.f12289e = 3;
                    if (function32.invoke(flowCollector2, objArr4, combineKt$combineInternal$22) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            }
            length = i2;
            objArr = objArr3;
            b3 = b2;
            channel = channel2;
            combineKt$combineInternal$2 = combineKt$combineInternal$22;
            i4 = 2;
        } else if (i3 == 2) {
            ?? r22 = this.f12288d;
            int i7 = this.f12287c;
            ResultKt.throwOnFailure(obj);
            length = i7;
            objArr = (Object[]) this.L$0;
            b3 = r22;
            bArr = (byte[]) this.f12286b;
            channel = (Channel) this.f12285a;
            combineKt$combineInternal$2 = this;
        } else if (i3 != 3) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            ?? r23 = this.f12288d;
            int i8 = this.f12287c;
            ResultKt.throwOnFailure(obj);
            length = i8;
            objArr = (Object[]) this.L$0;
            b3 = r23;
            bArr = (byte[]) this.f12286b;
            channel = (Channel) this.f12285a;
            combineKt$combineInternal$2 = this;
            i4 = 2;
        }
        byte b4 = (byte) (b3 + 1);
        combineKt$combineInternal$2.L$0 = objArr;
        combineKt$combineInternal$2.f12285a = channel;
        combineKt$combineInternal$2.f12286b = bArr;
        combineKt$combineInternal$2.f12287c = length;
        combineKt$combineInternal$2.f12288d = b4;
        combineKt$combineInternal$2.f12289e = 1;
        obj2 = channel.mo1627receiveCatchingJP2dKIU(combineKt$combineInternal$2);
        if (obj2 == coroutine_suspended) {
            return coroutine_suspended;
        }
        combineKt$combineInternal$22 = combineKt$combineInternal$2;
        objArr2 = objArr;
        channel2 = channel;
        b2 = b4;
        i2 = length;
        indexedValue = (IndexedValue) ChannelResult.m1639getOrNullimpl(obj2);
        if (indexedValue != null) {
        }
    }
}
