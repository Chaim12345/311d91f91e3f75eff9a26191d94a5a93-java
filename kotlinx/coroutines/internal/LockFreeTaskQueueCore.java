package kotlinx.coroutines.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.DebugKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class LockFreeTaskQueueCore<E> {
    public static final int ADD_CLOSED = 2;
    public static final int ADD_FROZEN = 1;
    public static final int ADD_SUCCESS = 0;
    public static final int CAPACITY_BITS = 30;
    public static final long CLOSED_MASK = 2305843009213693952L;
    public static final int CLOSED_SHIFT = 61;
    public static final long FROZEN_MASK = 1152921504606846976L;
    public static final int FROZEN_SHIFT = 60;
    public static final long HEAD_MASK = 1073741823;
    public static final int HEAD_SHIFT = 0;
    public static final int INITIAL_CAPACITY = 8;
    public static final int MAX_CAPACITY_MASK = 1073741823;
    public static final int MIN_ADD_SPIN_CAPACITY = 1024;
    public static final long TAIL_MASK = 1152921503533105152L;
    public static final int TAIL_SHIFT = 30;
    @NotNull
    private volatile /* synthetic */ Object _next = null;
    @NotNull
    private volatile /* synthetic */ long _state = 0;
    @NotNull
    private /* synthetic */ AtomicReferenceArray array;
    private final int capacity;
    private final int mask;
    private final boolean singleConsumer;
    @NotNull
    public static final Companion Companion = new Companion(null);
    @JvmField
    @NotNull
    public static final Symbol REMOVE_FROZEN = new Symbol("REMOVE_FROZEN");
    private static final /* synthetic */ AtomicReferenceFieldUpdater _next$FU = AtomicReferenceFieldUpdater.newUpdater(LockFreeTaskQueueCore.class, Object.class, "_next");
    private static final /* synthetic */ AtomicLongFieldUpdater _state$FU = AtomicLongFieldUpdater.newUpdater(LockFreeTaskQueueCore.class, "_state");

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int addFailReason(long j2) {
            return (j2 & LockFreeTaskQueueCore.CLOSED_MASK) != 0 ? 2 : 1;
        }

        public final long updateHead(long j2, int i2) {
            return wo(j2, LockFreeTaskQueueCore.HEAD_MASK) | (i2 << 0);
        }

        public final long updateTail(long j2, int i2) {
            return wo(j2, LockFreeTaskQueueCore.TAIL_MASK) | (i2 << 30);
        }

        public final <T> T withState(long j2, @NotNull Function2<? super Integer, ? super Integer, ? extends T> function2) {
            return function2.invoke(Integer.valueOf((int) ((LockFreeTaskQueueCore.HEAD_MASK & j2) >> 0)), Integer.valueOf((int) ((j2 & LockFreeTaskQueueCore.TAIL_MASK) >> 30)));
        }

        public final long wo(long j2, long j3) {
            return j2 & (~j3);
        }
    }

    /* loaded from: classes3.dex */
    public static final class Placeholder {
        @JvmField
        public final int index;

        public Placeholder(int i2) {
            this.index = i2;
        }
    }

    public LockFreeTaskQueueCore(int i2, boolean z) {
        this.capacity = i2;
        this.singleConsumer = z;
        int i3 = i2 - 1;
        this.mask = i3;
        this.array = new AtomicReferenceArray(i2);
        if (!(i3 <= 1073741823)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        if (!((i2 & i3) == 0)) {
            throw new IllegalStateException("Check failed.".toString());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final LockFreeTaskQueueCore<E> allocateNextCopy(long j2) {
        LockFreeTaskQueueCore<E> lockFreeTaskQueueCore = new LockFreeTaskQueueCore<>(this.capacity * 2, this.singleConsumer);
        int i2 = (int) ((HEAD_MASK & j2) >> 0);
        int i3 = (int) ((TAIL_MASK & j2) >> 30);
        while (true) {
            int i4 = this.mask;
            if ((i2 & i4) == (i3 & i4)) {
                lockFreeTaskQueueCore._state = Companion.wo(j2, FROZEN_MASK);
                return lockFreeTaskQueueCore;
            }
            Object obj = this.array.get(i4 & i2);
            if (obj == null) {
                obj = new Placeholder(i2);
            }
            lockFreeTaskQueueCore.array.set(lockFreeTaskQueueCore.mask & i2, obj);
            i2++;
        }
    }

    private final LockFreeTaskQueueCore<E> allocateOrGetNextCopy(long j2) {
        while (true) {
            LockFreeTaskQueueCore<E> lockFreeTaskQueueCore = (LockFreeTaskQueueCore) this._next;
            if (lockFreeTaskQueueCore != null) {
                return lockFreeTaskQueueCore;
            }
            _next$FU.compareAndSet(this, null, allocateNextCopy(j2));
        }
    }

    private final LockFreeTaskQueueCore<E> fillPlaceholder(int i2, E e2) {
        Object obj = this.array.get(this.mask & i2);
        if ((obj instanceof Placeholder) && ((Placeholder) obj).index == i2) {
            this.array.set(i2 & this.mask, e2);
            return this;
        }
        return null;
    }

    private final long markFrozen() {
        long j2;
        long j3;
        do {
            j2 = this._state;
            if ((j2 & FROZEN_MASK) != 0) {
                return j2;
            }
            j3 = j2 | FROZEN_MASK;
        } while (!_state$FU.compareAndSet(this, j2, j3));
        return j3;
    }

    private final LockFreeTaskQueueCore<E> removeSlowPath(int i2, int i3) {
        long j2;
        Companion companion;
        int i4;
        do {
            j2 = this._state;
            companion = Companion;
            i4 = (int) ((HEAD_MASK & j2) >> 0);
            if (DebugKt.getASSERTIONS_ENABLED()) {
                if (!(i4 == i2)) {
                    throw new AssertionError();
                }
            }
            if ((FROZEN_MASK & j2) != 0) {
                return next();
            }
        } while (!_state$FU.compareAndSet(this, j2, companion.updateHead(j2, i3)));
        this.array.set(this.mask & i4, null);
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x004e, code lost:
        return 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final int addLast(@NotNull E e2) {
        while (true) {
            long j2 = this._state;
            if ((3458764513820540928L & j2) != 0) {
                return Companion.addFailReason(j2);
            }
            Companion companion = Companion;
            int i2 = (int) ((HEAD_MASK & j2) >> 0);
            int i3 = (int) ((TAIL_MASK & j2) >> 30);
            int i4 = this.mask;
            if (((i3 + 2) & i4) == (i2 & i4)) {
                return 1;
            }
            if (!this.singleConsumer && this.array.get(i3 & i4) != null) {
                int i5 = this.capacity;
                if (i5 < 1024 || ((i3 - i2) & MAX_CAPACITY_MASK) > (i5 >> 1)) {
                    break;
                }
            } else if (_state$FU.compareAndSet(this, j2, companion.updateTail(j2, (i3 + 1) & MAX_CAPACITY_MASK))) {
                this.array.set(i3 & i4, e2);
                LockFreeTaskQueueCore<E> lockFreeTaskQueueCore = this;
                while ((lockFreeTaskQueueCore._state & FROZEN_MASK) != 0 && (lockFreeTaskQueueCore = lockFreeTaskQueueCore.next().fillPlaceholder(i3, e2)) != null) {
                }
                return 0;
            }
        }
    }

    public final boolean close() {
        long j2;
        do {
            j2 = this._state;
            if ((j2 & CLOSED_MASK) != 0) {
                return true;
            }
            if ((FROZEN_MASK & j2) != 0) {
                return false;
            }
        } while (!_state$FU.compareAndSet(this, j2, j2 | CLOSED_MASK));
        return true;
    }

    public final int getSize() {
        long j2 = this._state;
        return (((int) ((j2 & TAIL_MASK) >> 30)) - ((int) ((HEAD_MASK & j2) >> 0))) & MAX_CAPACITY_MASK;
    }

    public final boolean isClosed() {
        return (this._state & CLOSED_MASK) != 0;
    }

    public final boolean isEmpty() {
        long j2 = this._state;
        return ((int) ((HEAD_MASK & j2) >> 0)) == ((int) ((j2 & TAIL_MASK) >> 30));
    }

    @NotNull
    public final <R> List<R> map(@NotNull Function1<? super E, ? extends R> function1) {
        ArrayList arrayList = new ArrayList(this.capacity);
        long j2 = this._state;
        int i2 = (int) ((HEAD_MASK & j2) >> 0);
        int i3 = (int) ((j2 & TAIL_MASK) >> 30);
        while (true) {
            int i4 = this.mask;
            if ((i2 & i4) == (i3 & i4)) {
                return arrayList;
            }
            Object obj = (Object) this.array.get(i4 & i2);
            if (obj != 0 && !(obj instanceof Placeholder)) {
                arrayList.add(function1.invoke(obj));
            }
            i2++;
        }
    }

    @NotNull
    public final LockFreeTaskQueueCore<E> next() {
        return allocateOrGetNextCopy(markFrozen());
    }

    @Nullable
    public final Object removeFirstOrNull() {
        while (true) {
            long j2 = this._state;
            if ((FROZEN_MASK & j2) != 0) {
                return REMOVE_FROZEN;
            }
            Companion companion = Companion;
            int i2 = (int) ((HEAD_MASK & j2) >> 0);
            int i3 = (int) ((TAIL_MASK & j2) >> 30);
            int i4 = this.mask;
            if ((i3 & i4) == (i2 & i4)) {
                return null;
            }
            Object obj = this.array.get(i4 & i2);
            if (obj == null) {
                if (this.singleConsumer) {
                    return null;
                }
            } else if (obj instanceof Placeholder) {
                return null;
            } else {
                int i5 = (i2 + 1) & MAX_CAPACITY_MASK;
                if (_state$FU.compareAndSet(this, j2, companion.updateHead(j2, i5))) {
                    this.array.set(this.mask & i2, null);
                    return obj;
                } else if (this.singleConsumer) {
                    LockFreeTaskQueueCore<E> lockFreeTaskQueueCore = this;
                    do {
                        lockFreeTaskQueueCore = lockFreeTaskQueueCore.removeSlowPath(i2, i5);
                    } while (lockFreeTaskQueueCore != null);
                    return obj;
                }
            }
        }
    }
}
