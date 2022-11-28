package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.MapMaker;
import com.google.common.math.IntMath;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
@Beta
@GwtIncompatible
/* loaded from: classes2.dex */
public abstract class Striped<L> {
    private static final int ALL_SET = -1;
    private static final int LARGE_LAZY_CUTOFF = 1024;
    private static final Supplier<ReadWriteLock> READ_WRITE_LOCK_SUPPLIER = new Supplier<ReadWriteLock>() { // from class: com.google.common.util.concurrent.Striped.5
        @Override // com.google.common.base.Supplier
        public ReadWriteLock get() {
            return new ReentrantReadWriteLock();
        }
    };
    private static final Supplier<ReadWriteLock> WEAK_SAFE_READ_WRITE_LOCK_SUPPLIER = new Supplier<ReadWriteLock>() { // from class: com.google.common.util.concurrent.Striped.6
        @Override // com.google.common.base.Supplier
        public ReadWriteLock get() {
            return new WeakSafeReadWriteLock();
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class CompactStriped<L> extends PowerOfTwoStriped<L> {
        private final Object[] array;

        private CompactStriped(int i2, Supplier<L> supplier) {
            super(i2);
            int i3 = 0;
            Preconditions.checkArgument(i2 <= 1073741824, "Stripes must be <= 2^30)");
            this.array = new Object[this.f9588a + 1];
            while (true) {
                Object[] objArr = this.array;
                if (i3 >= objArr.length) {
                    return;
                }
                objArr[i3] = supplier.get();
                i3++;
            }
        }

        @Override // com.google.common.util.concurrent.Striped
        public L getAt(int i2) {
            return (L) this.array[i2];
        }

        @Override // com.google.common.util.concurrent.Striped
        public int size() {
            return this.array.length;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes2.dex */
    public static class LargeLazyStriped<L> extends PowerOfTwoStriped<L> {

        /* renamed from: b  reason: collision with root package name */
        final ConcurrentMap f9585b;

        /* renamed from: c  reason: collision with root package name */
        final Supplier f9586c;

        /* renamed from: d  reason: collision with root package name */
        final int f9587d;

        LargeLazyStriped(int i2, Supplier supplier) {
            super(i2);
            int i3 = this.f9588a;
            this.f9587d = i3 == -1 ? Integer.MAX_VALUE : i3 + 1;
            this.f9586c = supplier;
            this.f9585b = new MapMaker().weakValues().makeMap();
        }

        @Override // com.google.common.util.concurrent.Striped
        public L getAt(int i2) {
            if (this.f9587d != Integer.MAX_VALUE) {
                Preconditions.checkElementIndex(i2, size());
            }
            L l2 = (L) this.f9585b.get(Integer.valueOf(i2));
            if (l2 != null) {
                return l2;
            }
            Object obj = this.f9586c.get();
            return (L) MoreObjects.firstNonNull(this.f9585b.putIfAbsent(Integer.valueOf(i2), obj), obj);
        }

        @Override // com.google.common.util.concurrent.Striped
        public int size() {
            return this.f9587d;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class PaddedLock extends ReentrantLock {
        PaddedLock() {
            super(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class PaddedSemaphore extends Semaphore {
        PaddedSemaphore(int i2) {
            super(i2, false);
        }
    }

    /* loaded from: classes2.dex */
    private static abstract class PowerOfTwoStriped<L> extends Striped<L> {

        /* renamed from: a  reason: collision with root package name */
        final int f9588a;

        PowerOfTwoStriped(int i2) {
            super();
            Preconditions.checkArgument(i2 > 0, "Stripes must be positive");
            this.f9588a = i2 > 1073741824 ? -1 : Striped.ceilToPowerOfTwo(i2) - 1;
        }

        @Override // com.google.common.util.concurrent.Striped
        final int d(Object obj) {
            return Striped.smear(obj.hashCode()) & this.f9588a;
        }

        @Override // com.google.common.util.concurrent.Striped
        public final L get(Object obj) {
            return getAt(d(obj));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes2.dex */
    public static class SmallLazyStriped<L> extends PowerOfTwoStriped<L> {

        /* renamed from: b  reason: collision with root package name */
        final AtomicReferenceArray f9589b;

        /* renamed from: c  reason: collision with root package name */
        final Supplier f9590c;

        /* renamed from: d  reason: collision with root package name */
        final int f9591d;

        /* renamed from: e  reason: collision with root package name */
        final ReferenceQueue f9592e;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static final class ArrayReference<L> extends WeakReference<L> {

            /* renamed from: a  reason: collision with root package name */
            final int f9593a;

            ArrayReference(Object obj, int i2, ReferenceQueue referenceQueue) {
                super(obj, referenceQueue);
                this.f9593a = i2;
            }
        }

        SmallLazyStriped(int i2, Supplier supplier) {
            super(i2);
            this.f9592e = new ReferenceQueue();
            int i3 = this.f9588a;
            int i4 = i3 == -1 ? Integer.MAX_VALUE : i3 + 1;
            this.f9591d = i4;
            this.f9589b = new AtomicReferenceArray(i4);
            this.f9590c = supplier;
        }

        private void drainQueue() {
            while (true) {
                Reference poll = this.f9592e.poll();
                if (poll == null) {
                    return;
                }
                ArrayReference arrayReference = (ArrayReference) poll;
                this.f9589b.compareAndSet(arrayReference.f9593a, arrayReference, null);
            }
        }

        @Override // com.google.common.util.concurrent.Striped
        public L getAt(int i2) {
            L l2;
            if (this.f9591d != Integer.MAX_VALUE) {
                Preconditions.checkElementIndex(i2, size());
            }
            ArrayReference arrayReference = (ArrayReference) this.f9589b.get(i2);
            L l3 = arrayReference == null ? null : arrayReference.get();
            if (l3 != null) {
                return l3;
            }
            L l4 = (L) this.f9590c.get();
            ArrayReference arrayReference2 = new ArrayReference(l4, i2, this.f9592e);
            while (!this.f9589b.compareAndSet(i2, arrayReference, arrayReference2)) {
                arrayReference = (ArrayReference) this.f9589b.get(i2);
                if (arrayReference == null) {
                    l2 = null;
                    continue;
                } else {
                    l2 = arrayReference.get();
                    continue;
                }
                if (l2 != null) {
                    return l2;
                }
            }
            drainQueue();
            return l4;
        }

        @Override // com.google.common.util.concurrent.Striped
        public int size() {
            return this.f9591d;
        }
    }

    /* loaded from: classes2.dex */
    private static final class WeakSafeCondition extends ForwardingCondition {
        private final Condition delegate;
        private final WeakSafeReadWriteLock strongReference;

        WeakSafeCondition(Condition condition, WeakSafeReadWriteLock weakSafeReadWriteLock) {
            this.delegate = condition;
            this.strongReference = weakSafeReadWriteLock;
        }

        @Override // com.google.common.util.concurrent.ForwardingCondition
        Condition a() {
            return this.delegate;
        }
    }

    /* loaded from: classes2.dex */
    private static final class WeakSafeLock extends ForwardingLock {
        private final Lock delegate;
        private final WeakSafeReadWriteLock strongReference;

        WeakSafeLock(Lock lock, WeakSafeReadWriteLock weakSafeReadWriteLock) {
            this.delegate = lock;
            this.strongReference = weakSafeReadWriteLock;
        }

        @Override // com.google.common.util.concurrent.ForwardingLock
        Lock a() {
            return this.delegate;
        }

        @Override // com.google.common.util.concurrent.ForwardingLock, java.util.concurrent.locks.Lock
        public Condition newCondition() {
            return new WeakSafeCondition(this.delegate.newCondition(), this.strongReference);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class WeakSafeReadWriteLock implements ReadWriteLock {
        private final ReadWriteLock delegate = new ReentrantReadWriteLock();

        WeakSafeReadWriteLock() {
        }

        @Override // java.util.concurrent.locks.ReadWriteLock
        public Lock readLock() {
            return new WeakSafeLock(this.delegate.readLock(), this);
        }

        @Override // java.util.concurrent.locks.ReadWriteLock
        public Lock writeLock() {
            return new WeakSafeLock(this.delegate.writeLock(), this);
        }
    }

    private Striped() {
    }

    static Striped c(int i2, Supplier supplier) {
        return new CompactStriped(i2, supplier);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int ceilToPowerOfTwo(int i2) {
        return 1 << IntMath.log2(i2, RoundingMode.CEILING);
    }

    private static <L> Striped<L> lazy(int i2, Supplier<L> supplier) {
        return i2 < 1024 ? new SmallLazyStriped(i2, supplier) : new LargeLazyStriped(i2, supplier);
    }

    public static Striped<Lock> lazyWeakLock(int i2) {
        return lazy(i2, new Supplier<Lock>() { // from class: com.google.common.util.concurrent.Striped.2
            @Override // com.google.common.base.Supplier
            public Lock get() {
                return new ReentrantLock(false);
            }
        });
    }

    public static Striped<ReadWriteLock> lazyWeakReadWriteLock(int i2) {
        return lazy(i2, WEAK_SAFE_READ_WRITE_LOCK_SUPPLIER);
    }

    public static Striped<Semaphore> lazyWeakSemaphore(int i2, final int i3) {
        return lazy(i2, new Supplier<Semaphore>() { // from class: com.google.common.util.concurrent.Striped.4
            @Override // com.google.common.base.Supplier
            public Semaphore get() {
                return new Semaphore(i3, false);
            }
        });
    }

    public static Striped<Lock> lock(int i2) {
        return c(i2, new Supplier<Lock>() { // from class: com.google.common.util.concurrent.Striped.1
            @Override // com.google.common.base.Supplier
            public Lock get() {
                return new PaddedLock();
            }
        });
    }

    public static Striped<ReadWriteLock> readWriteLock(int i2) {
        return c(i2, READ_WRITE_LOCK_SUPPLIER);
    }

    public static Striped<Semaphore> semaphore(int i2, final int i3) {
        return c(i2, new Supplier<Semaphore>() { // from class: com.google.common.util.concurrent.Striped.3
            @Override // com.google.common.base.Supplier
            public Semaphore get() {
                return new PaddedSemaphore(i3);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int smear(int i2) {
        int i3 = i2 ^ ((i2 >>> 20) ^ (i2 >>> 12));
        return (i3 >>> 4) ^ ((i3 >>> 7) ^ i3);
    }

    public Iterable<L> bulkGet(Iterable<?> iterable) {
        Object[] array = Iterables.toArray(iterable, Object.class);
        if (array.length == 0) {
            return ImmutableList.of();
        }
        int[] iArr = new int[array.length];
        for (int i2 = 0; i2 < array.length; i2++) {
            iArr[i2] = d(array[i2]);
        }
        Arrays.sort(iArr);
        int i3 = iArr[0];
        array[0] = getAt(i3);
        for (int i4 = 1; i4 < array.length; i4++) {
            int i5 = iArr[i4];
            if (i5 == i3) {
                array[i4] = array[i4 - 1];
            } else {
                array[i4] = getAt(i5);
                i3 = i5;
            }
        }
        return Collections.unmodifiableList(Arrays.asList(array));
    }

    abstract int d(Object obj);

    public abstract L get(Object obj);

    public abstract L getAt(int i2);

    public abstract int size();
}
