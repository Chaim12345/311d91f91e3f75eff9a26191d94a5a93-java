package kotlinx.coroutines.channels;

import java.util.concurrent.locks.ReentrantLock;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.channels.AbstractChannel;
import kotlinx.coroutines.channels.AbstractSendChannel;
import kotlinx.coroutines.internal.AtomicKt;
import kotlinx.coroutines.internal.OnUndeliveredElementKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.UndeliveredElementException;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public class ArrayChannel<E> extends AbstractChannel<E> {
    @NotNull
    private Object[] buffer;
    private final int capacity;
    private int head;
    @NotNull
    private final ReentrantLock lock;
    @NotNull
    private final BufferOverflow onBufferOverflow;
    @NotNull
    private volatile /* synthetic */ int size;

    /* loaded from: classes3.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[BufferOverflow.values().length];
            iArr[BufferOverflow.SUSPEND.ordinal()] = 1;
            iArr[BufferOverflow.DROP_LATEST.ordinal()] = 2;
            iArr[BufferOverflow.DROP_OLDEST.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public ArrayChannel(int i2, @NotNull BufferOverflow bufferOverflow, @Nullable Function1<? super E, Unit> function1) {
        super(function1);
        this.capacity = i2;
        this.onBufferOverflow = bufferOverflow;
        if (!(i2 >= 1)) {
            throw new IllegalArgumentException(("ArrayChannel capacity must be at least 1, but " + i2 + " was specified").toString());
        }
        this.lock = new ReentrantLock();
        Object[] objArr = new Object[Math.min(i2, 8)];
        ArraysKt___ArraysJvmKt.fill$default(objArr, AbstractChannelKt.EMPTY, 0, 0, 6, (Object) null);
        this.buffer = objArr;
        this.size = 0;
    }

    private final void enqueueElement(int i2, E e2) {
        if (i2 < this.capacity) {
            ensureCapacity(i2);
            Object[] objArr = this.buffer;
            objArr[(this.head + i2) % objArr.length] = e2;
            return;
        }
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(this.onBufferOverflow == BufferOverflow.DROP_OLDEST)) {
                throw new AssertionError();
            }
        }
        Object[] objArr2 = this.buffer;
        int i3 = this.head;
        objArr2[i3 % objArr2.length] = null;
        objArr2[(i2 + i3) % objArr2.length] = e2;
        this.head = (i3 + 1) % objArr2.length;
    }

    private final void ensureCapacity(int i2) {
        Object[] objArr = this.buffer;
        if (i2 >= objArr.length) {
            int min = Math.min(objArr.length * 2, this.capacity);
            Object[] objArr2 = new Object[min];
            for (int i3 = 0; i3 < i2; i3++) {
                Object[] objArr3 = this.buffer;
                objArr2[i3] = objArr3[(this.head + i3) % objArr3.length];
            }
            ArraysKt___ArraysJvmKt.fill((Symbol[]) objArr2, AbstractChannelKt.EMPTY, i2, min);
            this.buffer = objArr2;
            this.head = 0;
        }
    }

    private final Symbol updateBufferSize(int i2) {
        if (i2 < this.capacity) {
            this.size = i2 + 1;
            return null;
        }
        int i3 = WhenMappings.$EnumSwitchMapping$0[this.onBufferOverflow.ordinal()];
        if (i3 != 1) {
            if (i3 != 2) {
                if (i3 == 3) {
                    return null;
                }
                throw new NoWhenBranchMatchedException();
            }
            return AbstractChannelKt.OFFER_SUCCESS;
        }
        return AbstractChannelKt.OFFER_FAILED;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    @Nullable
    public Object c(@NotNull Send send) {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            return super.c(send);
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    @NotNull
    protected String d() {
        return "(buffer:capacity=" + this.capacity + ",size=" + this.size + ')';
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected final boolean h() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    public final boolean i() {
        return this.size == this.capacity && this.onBufferOverflow == BufferOverflow.SUSPEND;
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel, kotlinx.coroutines.channels.ReceiveChannel
    public boolean isClosedForReceive() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            return super.isClosedForReceive();
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel, kotlinx.coroutines.channels.ReceiveChannel
    public boolean isEmpty() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            return t();
        } finally {
            reentrantLock.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    @NotNull
    public Object j(Object obj, @NotNull SelectInstance selectInstance) {
        Object performAtomicTrySelect;
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            int i2 = this.size;
            Closed f2 = f();
            if (f2 != null) {
                return f2;
            }
            Symbol updateBufferSize = updateBufferSize(i2);
            if (updateBufferSize != null) {
                return updateBufferSize;
            }
            if (i2 == 0) {
                do {
                    AbstractSendChannel.TryOfferDesc b2 = b(obj);
                    performAtomicTrySelect = selectInstance.performAtomicTrySelect(b2);
                    if (performAtomicTrySelect == null) {
                        this.size = i2;
                        ReceiveOrClosed<? super E> result = b2.getResult();
                        Unit unit = Unit.INSTANCE;
                        reentrantLock.unlock();
                        Intrinsics.checkNotNull(result);
                        ReceiveOrClosed<? super E> receiveOrClosed = result;
                        receiveOrClosed.completeResumeReceive(obj);
                        return receiveOrClosed.getOfferResult();
                    } else if (performAtomicTrySelect != AbstractChannelKt.OFFER_FAILED) {
                    }
                } while (performAtomicTrySelect == AtomicKt.RETRY_ATOMIC);
                if (performAtomicTrySelect != SelectKt.getALREADY_SELECTED() && !(performAtomicTrySelect instanceof Closed)) {
                    throw new IllegalStateException(("performAtomicTrySelect(describeTryOffer) returned " + performAtomicTrySelect).toString());
                }
                this.size = i2;
                return performAtomicTrySelect;
            }
            if (selectInstance.trySelect()) {
                enqueueElement(i2, obj);
                return AbstractChannelKt.OFFER_SUCCESS;
            }
            this.size = i2;
            return SelectKt.getALREADY_SELECTED();
        } finally {
            reentrantLock.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    @NotNull
    public Object offerInternal(Object obj) {
        ReceiveOrClosed m2;
        Symbol tryResumeReceive;
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            int i2 = this.size;
            Closed f2 = f();
            if (f2 != null) {
                return f2;
            }
            Symbol updateBufferSize = updateBufferSize(i2);
            if (updateBufferSize != null) {
                return updateBufferSize;
            }
            if (i2 == 0) {
                do {
                    m2 = m();
                    if (m2 != null) {
                        if (m2 instanceof Closed) {
                            this.size = i2;
                            return m2;
                        }
                        Intrinsics.checkNotNull(m2);
                        tryResumeReceive = m2.tryResumeReceive(obj, null);
                    }
                } while (tryResumeReceive == null);
                if (DebugKt.getASSERTIONS_ENABLED()) {
                    if (!(tryResumeReceive == CancellableContinuationImplKt.RESUME_TOKEN)) {
                        throw new AssertionError();
                    }
                }
                this.size = i2;
                Unit unit = Unit.INSTANCE;
                reentrantLock.unlock();
                m2.completeResumeReceive(obj);
                return m2.getOfferResult();
            }
            enqueueElement(i2, obj);
            return AbstractChannelKt.OFFER_SUCCESS;
        } finally {
            reentrantLock.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.channels.AbstractChannel
    public boolean p(@NotNull Receive receive) {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            return super.p(receive);
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    protected final boolean r() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.channels.AbstractChannel
    public final boolean s() {
        return this.size == 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.channels.AbstractChannel
    public void u(boolean z) {
        Function1 function1 = this.f11318a;
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            int i2 = this.size;
            UndeliveredElementException undeliveredElementException = null;
            for (int i3 = 0; i3 < i2; i3++) {
                Object obj = this.buffer[this.head];
                if (function1 != null && obj != AbstractChannelKt.EMPTY) {
                    undeliveredElementException = OnUndeliveredElementKt.callUndeliveredElementCatchingException(function1, obj, undeliveredElementException);
                }
                Object[] objArr = this.buffer;
                int i4 = this.head;
                objArr[i4] = AbstractChannelKt.EMPTY;
                this.head = (i4 + 1) % objArr.length;
            }
            this.size = 0;
            Unit unit = Unit.INSTANCE;
            reentrantLock.unlock();
            super.u(z);
            if (undeliveredElementException != null) {
                throw undeliveredElementException;
            }
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    @Nullable
    protected Object y() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            int i2 = this.size;
            if (i2 == 0) {
                Object f2 = f();
                if (f2 == null) {
                    f2 = AbstractChannelKt.POLL_FAILED;
                }
                return f2;
            }
            Object[] objArr = this.buffer;
            int i3 = this.head;
            Object obj = objArr[i3];
            Send send = null;
            objArr[i3] = null;
            this.size = i2 - 1;
            Object obj2 = AbstractChannelKt.POLL_FAILED;
            if (i2 == this.capacity) {
                Send send2 = null;
                while (true) {
                    Send n2 = n();
                    if (n2 == null) {
                        send = send2;
                        break;
                    }
                    Intrinsics.checkNotNull(n2);
                    Symbol tryResumeSend = n2.tryResumeSend(null);
                    if (tryResumeSend != null) {
                        if (DebugKt.getASSERTIONS_ENABLED()) {
                            if (!(tryResumeSend == CancellableContinuationImplKt.RESUME_TOKEN)) {
                                throw new AssertionError();
                            }
                        }
                        obj2 = n2.getPollResult();
                        r6 = true;
                        send = n2;
                    } else {
                        n2.undeliveredElement();
                        send2 = n2;
                    }
                }
            }
            if (obj2 != AbstractChannelKt.POLL_FAILED && !(obj2 instanceof Closed)) {
                this.size = i2;
                Object[] objArr2 = this.buffer;
                objArr2[(this.head + i2) % objArr2.length] = obj2;
            }
            this.head = (this.head + 1) % this.buffer.length;
            Unit unit = Unit.INSTANCE;
            if (r6) {
                Intrinsics.checkNotNull(send);
                send.completeResumeSend();
            }
            return obj;
        } finally {
            reentrantLock.unlock();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x009b A[Catch: all -> 0x00c4, TRY_LEAVE, TryCatch #0 {all -> 0x00c4, blocks: (B:3:0x0005, B:5:0x0009, B:7:0x000f, B:10:0x0015, B:12:0x0029, B:14:0x0033, B:30:0x0081, B:32:0x0085, B:34:0x0089, B:40:0x00ab, B:35:0x0095, B:37:0x009b, B:15:0x0043, B:17:0x0047, B:19:0x004b, B:21:0x0051, B:24:0x005d, B:27:0x0065, B:28:0x007f), top: B:48:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00bb  */
    @Override // kotlinx.coroutines.channels.AbstractChannel
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected Object z(@NotNull SelectInstance selectInstance) {
        boolean z;
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            int i2 = this.size;
            if (i2 == 0) {
                Object f2 = f();
                if (f2 == null) {
                    f2 = AbstractChannelKt.POLL_FAILED;
                }
                return f2;
            }
            Object[] objArr = this.buffer;
            int i3 = this.head;
            Object obj = objArr[i3];
            Object obj2 = null;
            objArr[i3] = null;
            this.size = i2 - 1;
            Object obj3 = AbstractChannelKt.POLL_FAILED;
            if (i2 == this.capacity) {
                while (true) {
                    AbstractChannel.TryPollDesc o2 = o();
                    Object performAtomicTrySelect = selectInstance.performAtomicTrySelect(o2);
                    if (performAtomicTrySelect != null) {
                        if (performAtomicTrySelect == AbstractChannelKt.POLL_FAILED) {
                            break;
                        } else if (performAtomicTrySelect != AtomicKt.RETRY_ATOMIC) {
                            if (performAtomicTrySelect == SelectKt.getALREADY_SELECTED()) {
                                this.size = i2;
                                this.buffer[this.head] = obj;
                                return performAtomicTrySelect;
                            } else if (!(performAtomicTrySelect instanceof Closed)) {
                                throw new IllegalStateException(("performAtomicTrySelect(describeTryOffer) returned " + performAtomicTrySelect).toString());
                            } else {
                                z = true;
                                obj3 = performAtomicTrySelect;
                                obj2 = obj3;
                            }
                        }
                    } else {
                        Object result = o2.getResult();
                        Intrinsics.checkNotNull(result);
                        obj3 = ((Send) result).getPollResult();
                        z = true;
                        obj2 = result;
                        break;
                    }
                }
                if (obj3 == AbstractChannelKt.POLL_FAILED && !(obj3 instanceof Closed)) {
                    this.size = i2;
                    Object[] objArr2 = this.buffer;
                    objArr2[(this.head + i2) % objArr2.length] = obj3;
                } else if (!selectInstance.trySelect()) {
                    this.size = i2;
                    this.buffer[this.head] = obj;
                    return SelectKt.getALREADY_SELECTED();
                }
                this.head = (this.head + 1) % this.buffer.length;
                Unit unit = Unit.INSTANCE;
                if (z) {
                    Intrinsics.checkNotNull(obj2);
                    ((Send) obj2).completeResumeSend();
                }
                return obj;
            }
            z = false;
            if (obj3 == AbstractChannelKt.POLL_FAILED) {
            }
            if (!selectInstance.trySelect()) {
            }
            this.head = (this.head + 1) % this.buffer.length;
            Unit unit2 = Unit.INSTANCE;
            if (z) {
            }
            return obj;
        } finally {
            reentrantLock.unlock();
        }
    }
}
