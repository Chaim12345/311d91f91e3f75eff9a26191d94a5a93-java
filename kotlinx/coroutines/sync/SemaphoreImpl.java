package kotlinx.coroutines.sync;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.internal.ConcurrentLinkedListKt;
import kotlinx.coroutines.internal.ConcurrentLinkedListNode;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.SegmentOrClosed;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class SemaphoreImpl implements Semaphore {
    @NotNull
    volatile /* synthetic */ int _availablePermits;
    @NotNull
    private volatile /* synthetic */ long deqIdx = 0;
    @NotNull
    private volatile /* synthetic */ long enqIdx = 0;
    @NotNull
    private volatile /* synthetic */ Object head;
    @NotNull
    private final Function1<Throwable, Unit> onCancellationRelease;
    private final int permits;
    @NotNull
    private volatile /* synthetic */ Object tail;
    private static final /* synthetic */ AtomicReferenceFieldUpdater head$FU = AtomicReferenceFieldUpdater.newUpdater(SemaphoreImpl.class, Object.class, "head");
    private static final /* synthetic */ AtomicLongFieldUpdater deqIdx$FU = AtomicLongFieldUpdater.newUpdater(SemaphoreImpl.class, "deqIdx");
    private static final /* synthetic */ AtomicReferenceFieldUpdater tail$FU = AtomicReferenceFieldUpdater.newUpdater(SemaphoreImpl.class, Object.class, "tail");
    private static final /* synthetic */ AtomicLongFieldUpdater enqIdx$FU = AtomicLongFieldUpdater.newUpdater(SemaphoreImpl.class, "enqIdx");

    /* renamed from: a  reason: collision with root package name */
    static final /* synthetic */ AtomicIntegerFieldUpdater f12401a = AtomicIntegerFieldUpdater.newUpdater(SemaphoreImpl.class, "_availablePermits");

    public SemaphoreImpl(int i2, int i3) {
        this.permits = i2;
        boolean z = true;
        if (!(i2 > 0)) {
            throw new IllegalArgumentException(("Semaphore should have at least 1 permit, but had " + i2).toString());
        }
        if (!((i3 < 0 || i3 > i2) ? false : z)) {
            throw new IllegalArgumentException(("The number of acquired permits should be in 0.." + i2).toString());
        }
        SemaphoreSegment semaphoreSegment = new SemaphoreSegment(0L, null, 2);
        this.head = semaphoreSegment;
        this.tail = semaphoreSegment;
        this._availablePermits = i2 - i3;
        this.onCancellationRelease = new SemaphoreImpl$onCancellationRelease$1(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object acquireSlowPath(Continuation<? super Unit> continuation) {
        Continuation intercepted;
        Object coroutine_suspended;
        Object coroutine_suspended2;
        intercepted = IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation);
        CancellableContinuationImpl orCreateCancellableContinuation = CancellableContinuationKt.getOrCreateCancellableContinuation(intercepted);
        while (true) {
            if (!addAcquireToQueue(orCreateCancellableContinuation)) {
                if (f12401a.getAndDecrement(this) > 0) {
                    orCreateCancellableContinuation.resume(Unit.INSTANCE, this.onCancellationRelease);
                    break;
                }
            } else {
                break;
            }
        }
        Object result = orCreateCancellableContinuation.getResult();
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (result == coroutine_suspended) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        coroutine_suspended2 = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return result == coroutine_suspended2 ? result : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v1, types: [kotlinx.coroutines.internal.ConcurrentLinkedListNode, kotlinx.coroutines.internal.Segment] */
    /* JADX WARN: Type inference failed for: r5v2, types: [kotlinx.coroutines.internal.Symbol] */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v4 */
    public final boolean addAcquireToQueue(CancellableContinuation<? super Unit> cancellableContinuation) {
        int i2;
        Object m1674constructorimpl;
        int i3;
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        boolean z;
        SemaphoreSegment semaphoreSegment = (SemaphoreSegment) this.tail;
        long andIncrement = enqIdx$FU.getAndIncrement(this);
        i2 = SemaphoreKt.SEGMENT_SIZE;
        long j2 = andIncrement / i2;
        do {
            Object obj = semaphoreSegment;
            while (true) {
                if (obj.getId() >= j2 && !obj.getRemoved()) {
                    break;
                }
                Object nextOrClosed = obj.getNextOrClosed();
                if (nextOrClosed == ConcurrentLinkedListKt.CLOSED) {
                    obj = ConcurrentLinkedListKt.CLOSED;
                    break;
                }
                ConcurrentLinkedListNode concurrentLinkedListNode = (Segment) ((ConcurrentLinkedListNode) nextOrClosed);
                if (concurrentLinkedListNode == null) {
                    concurrentLinkedListNode = SemaphoreKt.createSegment(obj.getId() + 1, obj);
                    if (obj.trySetNext(concurrentLinkedListNode)) {
                        if (obj.getRemoved()) {
                            obj.remove();
                        }
                    }
                }
                obj = concurrentLinkedListNode;
            }
            m1674constructorimpl = SegmentOrClosed.m1674constructorimpl(obj);
            if (SegmentOrClosed.m1679isClosedimpl(m1674constructorimpl)) {
                break;
            }
            Segment m1677getSegmentimpl = SegmentOrClosed.m1677getSegmentimpl(m1674constructorimpl);
            while (true) {
                Segment segment = (Segment) this.tail;
                if (segment.getId() >= m1677getSegmentimpl.getId()) {
                    break;
                } else if (!m1677getSegmentimpl.tryIncPointers$kotlinx_coroutines_core()) {
                    z = false;
                    continue;
                    break;
                } else if (tail$FU.compareAndSet(this, segment, m1677getSegmentimpl)) {
                    if (segment.decPointers$kotlinx_coroutines_core()) {
                        segment.remove();
                    }
                } else if (m1677getSegmentimpl.decPointers$kotlinx_coroutines_core()) {
                    m1677getSegmentimpl.remove();
                }
            }
            z = true;
            continue;
        } while (!z);
        SemaphoreSegment semaphoreSegment2 = (SemaphoreSegment) SegmentOrClosed.m1677getSegmentimpl(m1674constructorimpl);
        i3 = SemaphoreKt.SEGMENT_SIZE;
        int i4 = (int) (andIncrement % i3);
        if (semaphoreSegment2.f12407a.compareAndSet(i4, null, cancellableContinuation)) {
            cancellableContinuation.invokeOnCancellation(new CancelSemaphoreAcquisitionHandler(semaphoreSegment2, i4));
            return true;
        }
        symbol = SemaphoreKt.PERMIT;
        symbol2 = SemaphoreKt.TAKEN;
        if (semaphoreSegment2.f12407a.compareAndSet(i4, symbol, symbol2)) {
            cancellableContinuation.resume(Unit.INSTANCE, this.onCancellationRelease);
            return true;
        }
        if (DebugKt.getASSERTIONS_ENABLED()) {
            Object obj2 = semaphoreSegment2.f12407a.get(i4);
            symbol3 = SemaphoreKt.BROKEN;
            if (!(obj2 == symbol3)) {
                throw new AssertionError();
            }
        }
        return false;
    }

    private final boolean tryResumeAcquire(CancellableContinuation<? super Unit> cancellableContinuation) {
        Object tryResume = cancellableContinuation.tryResume(Unit.INSTANCE, null, this.onCancellationRelease);
        if (tryResume == null) {
            return false;
        }
        cancellableContinuation.completeResume(tryResume);
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v1, types: [kotlinx.coroutines.internal.ConcurrentLinkedListNode, kotlinx.coroutines.internal.Segment] */
    /* JADX WARN: Type inference failed for: r5v2, types: [kotlinx.coroutines.internal.Symbol] */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v4 */
    private final boolean tryResumeNextFromQueue() {
        int i2;
        Object m1674constructorimpl;
        int i3;
        int i4;
        Symbol symbol;
        Symbol symbol2;
        int i5;
        Symbol symbol3;
        Symbol symbol4;
        Symbol symbol5;
        boolean z;
        SemaphoreSegment semaphoreSegment = (SemaphoreSegment) this.head;
        long andIncrement = deqIdx$FU.getAndIncrement(this);
        i2 = SemaphoreKt.SEGMENT_SIZE;
        long j2 = andIncrement / i2;
        do {
            Object obj = semaphoreSegment;
            while (true) {
                if (obj.getId() >= j2 && !obj.getRemoved()) {
                    break;
                }
                Object nextOrClosed = obj.getNextOrClosed();
                if (nextOrClosed == ConcurrentLinkedListKt.CLOSED) {
                    obj = ConcurrentLinkedListKt.CLOSED;
                    break;
                }
                ConcurrentLinkedListNode concurrentLinkedListNode = (Segment) ((ConcurrentLinkedListNode) nextOrClosed);
                if (concurrentLinkedListNode == null) {
                    concurrentLinkedListNode = SemaphoreKt.createSegment(obj.getId() + 1, obj);
                    if (obj.trySetNext(concurrentLinkedListNode)) {
                        if (obj.getRemoved()) {
                            obj.remove();
                        }
                    }
                }
                obj = concurrentLinkedListNode;
            }
            m1674constructorimpl = SegmentOrClosed.m1674constructorimpl(obj);
            if (SegmentOrClosed.m1679isClosedimpl(m1674constructorimpl)) {
                break;
            }
            Segment m1677getSegmentimpl = SegmentOrClosed.m1677getSegmentimpl(m1674constructorimpl);
            while (true) {
                Segment segment = (Segment) this.head;
                if (segment.getId() >= m1677getSegmentimpl.getId()) {
                    break;
                } else if (!m1677getSegmentimpl.tryIncPointers$kotlinx_coroutines_core()) {
                    z = false;
                    continue;
                    break;
                } else if (head$FU.compareAndSet(this, segment, m1677getSegmentimpl)) {
                    if (segment.decPointers$kotlinx_coroutines_core()) {
                        segment.remove();
                    }
                } else if (m1677getSegmentimpl.decPointers$kotlinx_coroutines_core()) {
                    m1677getSegmentimpl.remove();
                }
            }
            z = true;
            continue;
        } while (!z);
        SemaphoreSegment semaphoreSegment2 = (SemaphoreSegment) SegmentOrClosed.m1677getSegmentimpl(m1674constructorimpl);
        semaphoreSegment2.cleanPrev();
        if (semaphoreSegment2.getId() > j2) {
            return false;
        }
        i4 = SemaphoreKt.SEGMENT_SIZE;
        int i6 = (int) (andIncrement % i4);
        symbol = SemaphoreKt.PERMIT;
        Object andSet = semaphoreSegment2.f12407a.getAndSet(i6, symbol);
        if (andSet != null) {
            symbol2 = SemaphoreKt.CANCELLED;
            if (andSet == symbol2) {
                return false;
            }
            return tryResumeAcquire((CancellableContinuation) andSet);
        }
        i5 = SemaphoreKt.MAX_SPIN_CYCLES;
        for (i3 = 0; i3 < i5; i3++) {
            Object obj2 = semaphoreSegment2.f12407a.get(i6);
            symbol5 = SemaphoreKt.TAKEN;
            if (obj2 == symbol5) {
                return true;
            }
        }
        symbol3 = SemaphoreKt.PERMIT;
        symbol4 = SemaphoreKt.BROKEN;
        return !semaphoreSegment2.f12407a.compareAndSet(i6, symbol3, symbol4);
    }

    @Override // kotlinx.coroutines.sync.Semaphore
    @Nullable
    public Object acquire(@NotNull Continuation<? super Unit> continuation) {
        Object coroutine_suspended;
        if (f12401a.getAndDecrement(this) > 0) {
            return Unit.INSTANCE;
        }
        Object acquireSlowPath = acquireSlowPath(continuation);
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return acquireSlowPath == coroutine_suspended ? acquireSlowPath : Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.sync.Semaphore
    public int getAvailablePermits() {
        return Math.max(this._availablePermits, 0);
    }

    @Override // kotlinx.coroutines.sync.Semaphore
    public void release() {
        while (true) {
            int i2 = this._availablePermits;
            if (!(i2 < this.permits)) {
                throw new IllegalStateException(("The number of released permits cannot be greater than " + this.permits).toString());
            }
            if (f12401a.compareAndSet(this, i2, i2 + 1) && (i2 >= 0 || tryResumeNextFromQueue())) {
                return;
            }
        }
    }

    @Override // kotlinx.coroutines.sync.Semaphore
    public boolean tryAcquire() {
        int i2;
        do {
            i2 = this._availablePermits;
            if (i2 <= 0) {
                return false;
            }
        } while (!f12401a.compareAndSet(this, i2, i2 - 1));
        return true;
    }
}
