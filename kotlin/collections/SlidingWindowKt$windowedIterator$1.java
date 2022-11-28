package kotlin.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.sequences.SequenceScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "kotlin.collections.SlidingWindowKt$windowedIterator$1", f = "SlidingWindow.kt", i = {0, 0, 0, 2, 2, 3, 3}, l = {34, 40, 49, 55, 58}, m = "invokeSuspend", n = {"$this$iterator", "buffer", "gap", "$this$iterator", "buffer", "$this$iterator", "buffer"}, s = {"L$0", "L$1", "I$0", "L$0", "L$1", "L$0", "L$1"})
/* loaded from: classes3.dex */
public final class SlidingWindowKt$windowedIterator$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super List<? extends T>>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;

    /* renamed from: a  reason: collision with root package name */
    Object f11079a;

    /* renamed from: b  reason: collision with root package name */
    Object f11080b;

    /* renamed from: c  reason: collision with root package name */
    int f11081c;

    /* renamed from: d  reason: collision with root package name */
    int f11082d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ int f11083e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ int f11084f;

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ Iterator f11085g;

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ boolean f11086h;

    /* renamed from: i  reason: collision with root package name */
    final /* synthetic */ boolean f11087i;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SlidingWindowKt$windowedIterator$1(int i2, int i3, Iterator it, boolean z, boolean z2, Continuation continuation) {
        super(2, continuation);
        this.f11083e = i2;
        this.f11084f = i3;
        this.f11085g = it;
        this.f11086h = z;
        this.f11087i = z2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        SlidingWindowKt$windowedIterator$1 slidingWindowKt$windowedIterator$1 = new SlidingWindowKt$windowedIterator$1(this.f11083e, this.f11084f, this.f11085g, this.f11086h, this.f11087i, continuation);
        slidingWindowKt$windowedIterator$1.L$0 = obj;
        return slidingWindowKt$windowedIterator$1;
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull SequenceScope<? super List<? extends T>> sequenceScope, @Nullable Continuation<? super Unit> continuation) {
        return ((SlidingWindowKt$windowedIterator$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00db A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0153  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x00a5 -> B:29:0x00a8). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:56:0x011c -> B:58:0x011f). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:69:0x014a -> B:71:0x014d). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        int coerceAtMost;
        int i2;
        RingBuffer ringBuffer;
        Iterator it;
        SequenceScope sequenceScope;
        SlidingWindowKt$windowedIterator$1 slidingWindowKt$windowedIterator$1;
        int i3;
        SequenceScope sequenceScope2;
        SlidingWindowKt$windowedIterator$1 slidingWindowKt$windowedIterator$12;
        ArrayList arrayList;
        Iterator it2;
        RingBuffer ringBuffer2;
        SequenceScope sequenceScope3;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i4 = this.f11082d;
        if (i4 == 0) {
            ResultKt.throwOnFailure(obj);
            SequenceScope sequenceScope4 = (SequenceScope) this.L$0;
            coerceAtMost = RangesKt___RangesKt.coerceAtMost(this.f11083e, 1024);
            i2 = this.f11084f - this.f11083e;
            if (i2 < 0) {
                ringBuffer = new RingBuffer(coerceAtMost);
                it = this.f11085g;
                sequenceScope = sequenceScope4;
                slidingWindowKt$windowedIterator$1 = this;
                while (it.hasNext()) {
                }
                if (slidingWindowKt$windowedIterator$1.f11087i) {
                }
                return Unit.INSTANCE;
            }
            ArrayList arrayList2 = new ArrayList(coerceAtMost);
            i3 = 0;
            sequenceScope2 = sequenceScope4;
            slidingWindowKt$windowedIterator$12 = this;
            arrayList = arrayList2;
            it2 = this.f11085g;
            while (it2.hasNext()) {
            }
            if (!arrayList.isEmpty()) {
                slidingWindowKt$windowedIterator$12.L$0 = null;
                slidingWindowKt$windowedIterator$12.f11079a = null;
                slidingWindowKt$windowedIterator$12.f11080b = null;
                slidingWindowKt$windowedIterator$12.f11082d = 2;
                if (sequenceScope2.yield(arrayList, slidingWindowKt$windowedIterator$12) == coroutine_suspended) {
                }
            }
            return Unit.INSTANCE;
        } else if (i4 == 1) {
            int i5 = this.f11081c;
            it2 = (Iterator) this.f11080b;
            arrayList = (ArrayList) this.f11079a;
            sequenceScope2 = (SequenceScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            slidingWindowKt$windowedIterator$12 = this;
            i2 = i5;
            if (slidingWindowKt$windowedIterator$12.f11086h) {
                arrayList = new ArrayList(slidingWindowKt$windowedIterator$12.f11083e);
            } else {
                arrayList.clear();
            }
            i3 = i2;
            while (it2.hasNext()) {
                Object next = it2.next();
                if (i3 > 0) {
                    i3--;
                } else {
                    arrayList.add(next);
                    if (arrayList.size() == slidingWindowKt$windowedIterator$12.f11083e) {
                        slidingWindowKt$windowedIterator$12.L$0 = sequenceScope2;
                        slidingWindowKt$windowedIterator$12.f11079a = arrayList;
                        slidingWindowKt$windowedIterator$12.f11080b = it2;
                        slidingWindowKt$windowedIterator$12.f11081c = i2;
                        slidingWindowKt$windowedIterator$12.f11082d = 1;
                        if (sequenceScope2.yield(arrayList, slidingWindowKt$windowedIterator$12) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        if (slidingWindowKt$windowedIterator$12.f11086h) {
                        }
                        i3 = i2;
                        while (it2.hasNext()) {
                        }
                    }
                }
            }
            if ((!arrayList.isEmpty()) && (slidingWindowKt$windowedIterator$12.f11087i || arrayList.size() == slidingWindowKt$windowedIterator$12.f11083e)) {
                slidingWindowKt$windowedIterator$12.L$0 = null;
                slidingWindowKt$windowedIterator$12.f11079a = null;
                slidingWindowKt$windowedIterator$12.f11080b = null;
                slidingWindowKt$windowedIterator$12.f11082d = 2;
                if (sequenceScope2.yield(arrayList, slidingWindowKt$windowedIterator$12) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Unit.INSTANCE;
        } else {
            if (i4 != 2) {
                if (i4 == 3) {
                    it = (Iterator) this.f11080b;
                    ringBuffer = (RingBuffer) this.f11079a;
                    sequenceScope = (SequenceScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    slidingWindowKt$windowedIterator$1 = this;
                    ringBuffer.removeFirst(slidingWindowKt$windowedIterator$1.f11084f);
                    while (it.hasNext()) {
                        ringBuffer.add((RingBuffer) it.next());
                        if (ringBuffer.isFull()) {
                            int size = ringBuffer.size();
                            int i6 = slidingWindowKt$windowedIterator$1.f11083e;
                            if (size >= i6) {
                                RandomAccess arrayList3 = slidingWindowKt$windowedIterator$1.f11086h ? ringBuffer : new ArrayList(ringBuffer);
                                slidingWindowKt$windowedIterator$1.L$0 = sequenceScope;
                                slidingWindowKt$windowedIterator$1.f11079a = ringBuffer;
                                slidingWindowKt$windowedIterator$1.f11080b = it;
                                slidingWindowKt$windowedIterator$1.f11082d = 3;
                                if (sequenceScope.yield(arrayList3, slidingWindowKt$windowedIterator$1) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                ringBuffer.removeFirst(slidingWindowKt$windowedIterator$1.f11084f);
                                while (it.hasNext()) {
                                }
                            } else {
                                ringBuffer = ringBuffer.expanded(i6);
                            }
                        }
                    }
                    if (slidingWindowKt$windowedIterator$1.f11087i) {
                        ringBuffer2 = ringBuffer;
                        sequenceScope3 = sequenceScope;
                        if (ringBuffer2.size() <= slidingWindowKt$windowedIterator$1.f11084f) {
                        }
                    }
                    return Unit.INSTANCE;
                } else if (i4 == 4) {
                    ringBuffer2 = (RingBuffer) this.f11079a;
                    sequenceScope3 = (SequenceScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    slidingWindowKt$windowedIterator$1 = this;
                    ringBuffer2.removeFirst(slidingWindowKt$windowedIterator$1.f11084f);
                    if (ringBuffer2.size() <= slidingWindowKt$windowedIterator$1.f11084f) {
                        RandomAccess arrayList4 = slidingWindowKt$windowedIterator$1.f11086h ? ringBuffer2 : new ArrayList(ringBuffer2);
                        slidingWindowKt$windowedIterator$1.L$0 = sequenceScope3;
                        slidingWindowKt$windowedIterator$1.f11079a = ringBuffer2;
                        slidingWindowKt$windowedIterator$1.f11080b = null;
                        slidingWindowKt$windowedIterator$1.f11082d = 4;
                        if (sequenceScope3.yield(arrayList4, slidingWindowKt$windowedIterator$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        ringBuffer2.removeFirst(slidingWindowKt$windowedIterator$1.f11084f);
                        if (ringBuffer2.size() <= slidingWindowKt$windowedIterator$1.f11084f) {
                            if (!ringBuffer2.isEmpty()) {
                                slidingWindowKt$windowedIterator$1.L$0 = null;
                                slidingWindowKt$windowedIterator$1.f11079a = null;
                                slidingWindowKt$windowedIterator$1.f11080b = null;
                                slidingWindowKt$windowedIterator$1.f11082d = 5;
                                if (sequenceScope3.yield(ringBuffer2, slidingWindowKt$windowedIterator$1) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            }
                            return Unit.INSTANCE;
                        }
                    }
                } else if (i4 != 5) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        }
    }
}
