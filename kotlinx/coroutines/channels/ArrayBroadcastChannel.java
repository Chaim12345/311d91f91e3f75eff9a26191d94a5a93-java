package kotlinx.coroutines.channels;

import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.ranges.RangesKt___RangesKt;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.internal.ConcurrentKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class ArrayBroadcastChannel<E> extends AbstractSendChannel<E> implements BroadcastChannel<E> {
    @NotNull
    private volatile /* synthetic */ long _head;
    @NotNull
    private volatile /* synthetic */ int _size;
    @NotNull
    private volatile /* synthetic */ long _tail;
    @NotNull
    private final Object[] buffer;
    @NotNull
    private final ReentrantLock bufferLock;
    private final int capacity;
    @NotNull
    private final List<Subscriber<E>> subscribers;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class Subscriber<E> extends AbstractChannel<E> implements ReceiveChannel<E> {
        @NotNull
        private volatile /* synthetic */ long _subHead;
        @NotNull
        private final ArrayBroadcastChannel<E> broadcastChannel;
        @NotNull
        private final ReentrantLock subLock;

        public Subscriber(@NotNull ArrayBroadcastChannel<E> arrayBroadcastChannel) {
            super(null);
            this.broadcastChannel = arrayBroadcastChannel;
            this.subLock = new ReentrantLock();
            this._subHead = 0L;
        }

        private final boolean needsToCheckOfferWithoutLock() {
            if (e() != null) {
                return false;
            }
            return (s() && this.broadcastChannel.e() == null) ? false : true;
        }

        private final Object peekUnderLock() {
            long subHead = getSubHead();
            Closed e2 = this.broadcastChannel.e();
            if (subHead < this.broadcastChannel.getTail()) {
                Object elementAt = this.broadcastChannel.elementAt(subHead);
                Closed e3 = e();
                return e3 != null ? e3 : elementAt;
            } else if (e2 == null) {
                Closed e4 = e();
                return e4 == null ? AbstractChannelKt.POLL_FAILED : e4;
            } else {
                return e2;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final boolean checkOffer() {
            Closed closed;
            boolean z = false;
            while (true) {
                closed = null;
                if (!needsToCheckOfferWithoutLock() || !this.subLock.tryLock()) {
                    break;
                }
                try {
                    Object peekUnderLock = peekUnderLock();
                    if (peekUnderLock != AbstractChannelKt.POLL_FAILED) {
                        if (peekUnderLock instanceof Closed) {
                            closed = (Closed) peekUnderLock;
                            break;
                        }
                        ReceiveOrClosed m2 = m();
                        if (m2 != 0 && !(m2 instanceof Closed)) {
                            Symbol tryResumeReceive = m2.tryResumeReceive(peekUnderLock, null);
                            if (tryResumeReceive != null) {
                                if (DebugKt.getASSERTIONS_ENABLED()) {
                                    if (!(tryResumeReceive == CancellableContinuationImplKt.RESUME_TOKEN)) {
                                        throw new AssertionError();
                                    }
                                }
                                setSubHead(getSubHead() + 1);
                                this.subLock.unlock();
                                m2.completeResumeReceive(peekUnderLock);
                                z = true;
                            }
                        }
                    }
                } finally {
                    this.subLock.unlock();
                }
            }
            if (closed != null) {
                close(closed.closeCause);
            }
            return z;
        }

        @Override // kotlinx.coroutines.channels.AbstractSendChannel, kotlinx.coroutines.channels.SendChannel
        public boolean close(@Nullable Throwable th) {
            boolean close = super.close(th);
            if (close) {
                ArrayBroadcastChannel.o(this.broadcastChannel, null, this, 1, null);
                ReentrantLock reentrantLock = this.subLock;
                reentrantLock.lock();
                try {
                    setSubHead(this.broadcastChannel.getTail());
                    Unit unit = Unit.INSTANCE;
                } finally {
                    reentrantLock.unlock();
                }
            }
            return close;
        }

        public final long getSubHead() {
            return this._subHead;
        }

        @Override // kotlinx.coroutines.channels.AbstractSendChannel
        protected boolean h() {
            throw new IllegalStateException("Should not be used".toString());
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // kotlinx.coroutines.channels.AbstractSendChannel
        public boolean i() {
            throw new IllegalStateException("Should not be used".toString());
        }

        @Override // kotlinx.coroutines.channels.AbstractChannel
        protected boolean r() {
            return false;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // kotlinx.coroutines.channels.AbstractChannel
        public boolean s() {
            return getSubHead() >= this.broadcastChannel.getTail();
        }

        public final void setSubHead(long j2) {
            this._subHead = j2;
        }

        @Override // kotlinx.coroutines.channels.AbstractChannel
        @Nullable
        protected Object y() {
            boolean z;
            ReentrantLock reentrantLock = this.subLock;
            reentrantLock.lock();
            try {
                Object peekUnderLock = peekUnderLock();
                if ((peekUnderLock instanceof Closed) || peekUnderLock == AbstractChannelKt.POLL_FAILED) {
                    z = false;
                } else {
                    setSubHead(getSubHead() + 1);
                    z = true;
                }
                reentrantLock.unlock();
                Closed closed = peekUnderLock instanceof Closed ? (Closed) peekUnderLock : null;
                if (closed != null) {
                    close(closed.closeCause);
                }
                if (checkOffer() ? true : z) {
                    ArrayBroadcastChannel.o(this.broadcastChannel, null, null, 3, null);
                }
                return peekUnderLock;
            } catch (Throwable th) {
                reentrantLock.unlock();
                throw th;
            }
        }

        @Override // kotlinx.coroutines.channels.AbstractChannel
        @Nullable
        protected Object z(@NotNull SelectInstance selectInstance) {
            ReentrantLock reentrantLock = this.subLock;
            reentrantLock.lock();
            try {
                Object peekUnderLock = peekUnderLock();
                boolean z = false;
                if (!(peekUnderLock instanceof Closed) && peekUnderLock != AbstractChannelKt.POLL_FAILED) {
                    if (selectInstance.trySelect()) {
                        setSubHead(getSubHead() + 1);
                        z = true;
                    } else {
                        peekUnderLock = SelectKt.getALREADY_SELECTED();
                    }
                }
                reentrantLock.unlock();
                Closed closed = peekUnderLock instanceof Closed ? (Closed) peekUnderLock : null;
                if (closed != null) {
                    close(closed.closeCause);
                }
                if (checkOffer() ? true : z) {
                    ArrayBroadcastChannel.o(this.broadcastChannel, null, null, 3, null);
                }
                return peekUnderLock;
            } catch (Throwable th) {
                reentrantLock.unlock();
                throw th;
            }
        }
    }

    public ArrayBroadcastChannel(int i2) {
        super(null);
        this.capacity = i2;
        if (!(i2 >= 1)) {
            throw new IllegalArgumentException(("ArrayBroadcastChannel capacity must be at least 1, but " + i2 + " was specified").toString());
        }
        this.bufferLock = new ReentrantLock();
        this.buffer = new Object[i2];
        this._head = 0L;
        this._tail = 0L;
        this._size = 0;
        this.subscribers = ConcurrentKt.subscriberList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // kotlinx.coroutines.channels.BroadcastChannel
    /* renamed from: cancelInternal */
    public final boolean cancel(Throwable th) {
        boolean close = close(th);
        for (Subscriber<E> subscriber : this.subscribers) {
            subscriber.cancel(th);
        }
        return close;
    }

    private final void checkSubOffers() {
        boolean z;
        boolean z2 = false;
        loop0: while (true) {
            z = z2;
            for (Subscriber<E> subscriber : this.subscribers) {
                if (subscriber.checkOffer()) {
                    break;
                }
                z = true;
            }
            z2 = true;
        }
        if (z2 || !z) {
            o(this, null, null, 3, null);
        }
    }

    private final long computeMinHead() {
        long j2 = LongCompanionObject.MAX_VALUE;
        for (Subscriber<E> subscriber : this.subscribers) {
            j2 = RangesKt___RangesKt.coerceAtMost(j2, subscriber.getSubHead());
        }
        return j2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final E elementAt(long j2) {
        return (E) this.buffer[(int) (j2 % this.capacity)];
    }

    private final long getHead() {
        return this._head;
    }

    private final int getSize() {
        return this._size;
    }

    private static /* synthetic */ void getSubscribers$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long getTail() {
        return this._tail;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void o(ArrayBroadcastChannel arrayBroadcastChannel, Subscriber subscriber, Subscriber subscriber2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            subscriber = null;
        }
        if ((i2 & 2) != 0) {
            subscriber2 = null;
        }
        arrayBroadcastChannel.updateHead(subscriber, subscriber2);
    }

    private final void setHead(long j2) {
        this._head = j2;
    }

    private final void setSize(int i2) {
        this._size = i2;
    }

    private final void setTail(long j2) {
        this._tail = j2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:46:0x009f, code lost:
        throw new java.lang.AssertionError();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void updateHead(Subscriber<E> subscriber, Subscriber<E> subscriber2) {
        long coerceAtMost;
        Send n2;
        Symbol tryResumeSend;
        while (true) {
            ReentrantLock reentrantLock = this.bufferLock;
            reentrantLock.lock();
            if (subscriber != null) {
                try {
                    subscriber.setSubHead(getTail());
                    boolean isEmpty = this.subscribers.isEmpty();
                    this.subscribers.add(subscriber);
                    if (!isEmpty) {
                        return;
                    }
                } finally {
                    reentrantLock.unlock();
                }
            }
            if (subscriber2 != null) {
                this.subscribers.remove(subscriber2);
                if (getHead() != subscriber2.getSubHead()) {
                    return;
                }
            }
            long computeMinHead = computeMinHead();
            long tail = getTail();
            long head = getHead();
            coerceAtMost = RangesKt___RangesKt.coerceAtMost(computeMinHead, tail);
            if (coerceAtMost <= head) {
                return;
            }
            int size = getSize();
            while (head < coerceAtMost) {
                Object[] objArr = this.buffer;
                int i2 = this.capacity;
                objArr[(int) (head % i2)] = null;
                boolean z = size >= i2;
                head++;
                setHead(head);
                size--;
                setSize(size);
                if (z) {
                    do {
                        n2 = n();
                        if (n2 != null && !(n2 instanceof Closed)) {
                            Intrinsics.checkNotNull(n2);
                            tryResumeSend = n2.tryResumeSend(null);
                        }
                    } while (tryResumeSend == null);
                    if (DebugKt.getASSERTIONS_ENABLED()) {
                        if (!(tryResumeSend == CancellableContinuationImplKt.RESUME_TOKEN)) {
                            break;
                        }
                    }
                    this.buffer[(int) (tail % this.capacity)] = n2.getPollResult();
                    setSize(size + 1);
                    setTail(tail + 1);
                    Unit unit = Unit.INSTANCE;
                    reentrantLock.unlock();
                    n2.completeResumeSend();
                    checkSubOffers();
                    subscriber = null;
                    subscriber2 = null;
                }
            }
            return;
        }
    }

    @Override // kotlinx.coroutines.channels.BroadcastChannel
    public void cancel(@Nullable CancellationException cancellationException) {
        cancel(cancellationException);
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel, kotlinx.coroutines.channels.SendChannel
    public boolean close(@Nullable Throwable th) {
        if (super.close(th)) {
            checkSubOffers();
            return true;
        }
        return false;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    @NotNull
    protected String d() {
        return "(buffer:capacity=" + this.buffer.length + ",size=" + getSize() + ')';
    }

    public final int getCapacity() {
        return this.capacity;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected boolean h() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    public boolean i() {
        return getSize() >= this.capacity;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    @NotNull
    public Object j(Object obj, @NotNull SelectInstance selectInstance) {
        ReentrantLock reentrantLock = this.bufferLock;
        reentrantLock.lock();
        try {
            Closed f2 = f();
            if (f2 != null) {
                return f2;
            }
            int size = getSize();
            if (size >= this.capacity) {
                return AbstractChannelKt.OFFER_FAILED;
            }
            if (selectInstance.trySelect()) {
                long tail = getTail();
                this.buffer[(int) (tail % this.capacity)] = obj;
                setSize(size + 1);
                setTail(tail + 1);
                Unit unit = Unit.INSTANCE;
                reentrantLock.unlock();
                checkSubOffers();
                return AbstractChannelKt.OFFER_SUCCESS;
            }
            return SelectKt.getALREADY_SELECTED();
        } finally {
            reentrantLock.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    @NotNull
    public Object offerInternal(Object obj) {
        ReentrantLock reentrantLock = this.bufferLock;
        reentrantLock.lock();
        try {
            Closed f2 = f();
            if (f2 != null) {
                return f2;
            }
            int size = getSize();
            if (size >= this.capacity) {
                return AbstractChannelKt.OFFER_FAILED;
            }
            long tail = getTail();
            this.buffer[(int) (tail % this.capacity)] = obj;
            setSize(size + 1);
            setTail(tail + 1);
            Unit unit = Unit.INSTANCE;
            reentrantLock.unlock();
            checkSubOffers();
            return AbstractChannelKt.OFFER_SUCCESS;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.BroadcastChannel
    @NotNull
    public ReceiveChannel<E> openSubscription() {
        Subscriber subscriber = new Subscriber(this);
        o(this, subscriber, null, 2, null);
        return subscriber;
    }
}
