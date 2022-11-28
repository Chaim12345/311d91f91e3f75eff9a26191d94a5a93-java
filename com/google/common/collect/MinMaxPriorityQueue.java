package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.math.IntMath;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.j2objc.annotations.Weak;
import java.util.AbstractQueue;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@Beta
@GwtCompatible
/* loaded from: classes2.dex */
public final class MinMaxPriorityQueue<E> extends AbstractQueue<E> {
    private static final int DEFAULT_CAPACITY = 11;
    private static final int EVEN_POWERS_OF_TWO = 1431655765;
    private static final int ODD_POWERS_OF_TWO = -1431655766;
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    final int f8826a;
    private final MinMaxPriorityQueue<E>.Heap maxHeap;
    private final MinMaxPriorityQueue<E>.Heap minHeap;
    private int modCount;
    private Object[] queue;
    private int size;

    @Beta
    /* loaded from: classes2.dex */
    public static final class Builder<B> {
        private static final int UNSET_EXPECTED_SIZE = -1;
        private final Comparator<B> comparator;
        private int expectedSize;
        private int maximumSize;

        private Builder(Comparator<B> comparator) {
            this.expectedSize = -1;
            this.maximumSize = Integer.MAX_VALUE;
            this.comparator = (Comparator) Preconditions.checkNotNull(comparator);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public <T extends B> Ordering<T> ordering() {
            return Ordering.from(this.comparator);
        }

        public <T extends B> MinMaxPriorityQueue<T> create() {
            return create(Collections.emptySet());
        }

        public <T extends B> MinMaxPriorityQueue<T> create(Iterable<? extends T> iterable) {
            MinMaxPriorityQueue<T> minMaxPriorityQueue = new MinMaxPriorityQueue<>(this, MinMaxPriorityQueue.e(this.expectedSize, this.maximumSize, iterable));
            for (T t2 : iterable) {
                minMaxPriorityQueue.offer(t2);
            }
            return minMaxPriorityQueue;
        }

        @CanIgnoreReturnValue
        public Builder<B> expectedSize(int i2) {
            Preconditions.checkArgument(i2 >= 0);
            this.expectedSize = i2;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<B> maximumSize(int i2) {
            Preconditions.checkArgument(i2 > 0);
            this.maximumSize = i2;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class Heap {

        /* renamed from: a  reason: collision with root package name */
        final Ordering f8827a;
        @Weak
        @NullableDecl

        /* renamed from: b  reason: collision with root package name */
        Heap f8828b;

        Heap(Ordering ordering) {
            this.f8827a = ordering;
        }

        private int getGrandparentIndex(int i2) {
            return getParentIndex(getParentIndex(i2));
        }

        private int getLeftChildIndex(int i2) {
            return (i2 * 2) + 1;
        }

        private int getParentIndex(int i2) {
            return (i2 - 1) / 2;
        }

        private int getRightChildIndex(int i2) {
            return (i2 * 2) + 2;
        }

        private boolean verifyIndex(int i2) {
            if (getLeftChildIndex(i2) >= MinMaxPriorityQueue.this.size || c(i2, getLeftChildIndex(i2)) <= 0) {
                if (getRightChildIndex(i2) >= MinMaxPriorityQueue.this.size || c(i2, getRightChildIndex(i2)) <= 0) {
                    if (i2 <= 0 || c(i2, getParentIndex(i2)) <= 0) {
                        return i2 <= 2 || c(getGrandparentIndex(i2), i2) <= 0;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }

        void a(int i2, Object obj) {
            Heap heap;
            int e2 = e(i2, obj);
            if (e2 == i2) {
                e2 = i2;
                heap = this;
            } else {
                heap = this.f8828b;
            }
            heap.b(e2, obj);
        }

        @CanIgnoreReturnValue
        int b(int i2, Object obj) {
            while (i2 > 2) {
                int grandparentIndex = getGrandparentIndex(i2);
                Object d2 = MinMaxPriorityQueue.this.d(grandparentIndex);
                if (this.f8827a.compare(d2, obj) <= 0) {
                    break;
                }
                MinMaxPriorityQueue.this.queue[i2] = d2;
                i2 = grandparentIndex;
            }
            MinMaxPriorityQueue.this.queue[i2] = obj;
            return i2;
        }

        int c(int i2, int i3) {
            return this.f8827a.compare(MinMaxPriorityQueue.this.d(i2), MinMaxPriorityQueue.this.d(i3));
        }

        int d(int i2, Object obj) {
            int h2 = h(i2);
            if (h2 <= 0 || this.f8827a.compare(MinMaxPriorityQueue.this.d(h2), obj) >= 0) {
                return e(i2, obj);
            }
            MinMaxPriorityQueue.this.queue[i2] = MinMaxPriorityQueue.this.d(h2);
            MinMaxPriorityQueue.this.queue[h2] = obj;
            return h2;
        }

        int e(int i2, Object obj) {
            int rightChildIndex;
            if (i2 == 0) {
                MinMaxPriorityQueue.this.queue[0] = obj;
                return 0;
            }
            int parentIndex = getParentIndex(i2);
            Object d2 = MinMaxPriorityQueue.this.d(parentIndex);
            if (parentIndex != 0 && (rightChildIndex = getRightChildIndex(getParentIndex(parentIndex))) != parentIndex && getLeftChildIndex(rightChildIndex) >= MinMaxPriorityQueue.this.size) {
                Object d3 = MinMaxPriorityQueue.this.d(rightChildIndex);
                if (this.f8827a.compare(d3, d2) < 0) {
                    parentIndex = rightChildIndex;
                    d2 = d3;
                }
            }
            if (this.f8827a.compare(d2, obj) >= 0) {
                MinMaxPriorityQueue.this.queue[i2] = obj;
                return i2;
            }
            MinMaxPriorityQueue.this.queue[i2] = d2;
            MinMaxPriorityQueue.this.queue[parentIndex] = obj;
            return parentIndex;
        }

        int f(int i2) {
            while (true) {
                int i3 = i(i2);
                if (i3 <= 0) {
                    return i2;
                }
                MinMaxPriorityQueue.this.queue[i2] = MinMaxPriorityQueue.this.d(i3);
                i2 = i3;
            }
        }

        int g(int i2, int i3) {
            if (i2 >= MinMaxPriorityQueue.this.size) {
                return -1;
            }
            Preconditions.checkState(i2 > 0);
            int min = Math.min(i2, MinMaxPriorityQueue.this.size - i3) + i3;
            for (int i4 = i2 + 1; i4 < min; i4++) {
                if (c(i4, i2) < 0) {
                    i2 = i4;
                }
            }
            return i2;
        }

        int h(int i2) {
            return g(getLeftChildIndex(i2), 2);
        }

        int i(int i2) {
            int leftChildIndex = getLeftChildIndex(i2);
            if (leftChildIndex < 0) {
                return -1;
            }
            return g(getLeftChildIndex(leftChildIndex), 4);
        }

        int j(Object obj) {
            int rightChildIndex;
            int parentIndex = getParentIndex(MinMaxPriorityQueue.this.size);
            if (parentIndex != 0 && (rightChildIndex = getRightChildIndex(getParentIndex(parentIndex))) != parentIndex && getLeftChildIndex(rightChildIndex) >= MinMaxPriorityQueue.this.size) {
                Object d2 = MinMaxPriorityQueue.this.d(rightChildIndex);
                if (this.f8827a.compare(d2, obj) < 0) {
                    MinMaxPriorityQueue.this.queue[rightChildIndex] = obj;
                    MinMaxPriorityQueue.this.queue[MinMaxPriorityQueue.this.size] = d2;
                    return rightChildIndex;
                }
            }
            return MinMaxPriorityQueue.this.size;
        }

        MoveDesc k(int i2, int i3, Object obj) {
            int d2 = d(i3, obj);
            if (d2 == i3) {
                return null;
            }
            MinMaxPriorityQueue minMaxPriorityQueue = MinMaxPriorityQueue.this;
            Object d3 = d2 < i2 ? minMaxPriorityQueue.d(i2) : minMaxPriorityQueue.d(getParentIndex(i2));
            if (this.f8828b.b(d2, obj) < i2) {
                return new MoveDesc(obj, d3);
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class MoveDesc<E> {

        /* renamed from: a  reason: collision with root package name */
        final Object f8830a;

        /* renamed from: b  reason: collision with root package name */
        final Object f8831b;

        MoveDesc(Object obj, Object obj2) {
            this.f8830a = obj;
            this.f8831b = obj2;
        }
    }

    /* loaded from: classes2.dex */
    private class QueueIterator implements Iterator<E> {
        private boolean canRemove;
        private int cursor;
        private int expectedModCount;
        @NullableDecl
        private Queue<E> forgetMeNot;
        @NullableDecl
        private E lastFromForgetMeNot;
        private int nextCursor;
        @NullableDecl
        private List<E> skipMe;

        private QueueIterator() {
            this.cursor = -1;
            this.nextCursor = -1;
            this.expectedModCount = MinMaxPriorityQueue.this.modCount;
        }

        private void checkModCount() {
            if (MinMaxPriorityQueue.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }

        private boolean foundAndRemovedExactReference(Iterable<E> iterable, E e2) {
            Iterator<E> it = iterable.iterator();
            while (it.hasNext()) {
                if (it.next() == e2) {
                    it.remove();
                    return true;
                }
            }
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private void nextNotInSkipMe(int i2) {
            if (this.nextCursor < i2) {
                if (this.skipMe != null) {
                    while (i2 < MinMaxPriorityQueue.this.size() && foundAndRemovedExactReference(this.skipMe, MinMaxPriorityQueue.this.d(i2))) {
                        i2++;
                    }
                }
                this.nextCursor = i2;
            }
        }

        private boolean removeExact(Object obj) {
            for (int i2 = 0; i2 < MinMaxPriorityQueue.this.size; i2++) {
                if (MinMaxPriorityQueue.this.queue[i2] == obj) {
                    MinMaxPriorityQueue.this.g(i2);
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            checkModCount();
            nextNotInSkipMe(this.cursor + 1);
            if (this.nextCursor >= MinMaxPriorityQueue.this.size()) {
                Queue<E> queue = this.forgetMeNot;
                return (queue == null || queue.isEmpty()) ? false : true;
            }
            return true;
        }

        @Override // java.util.Iterator
        public E next() {
            checkModCount();
            nextNotInSkipMe(this.cursor + 1);
            if (this.nextCursor < MinMaxPriorityQueue.this.size()) {
                int i2 = this.nextCursor;
                this.cursor = i2;
                this.canRemove = true;
                return (E) MinMaxPriorityQueue.this.d(i2);
            }
            if (this.forgetMeNot != null) {
                this.cursor = MinMaxPriorityQueue.this.size();
                E poll = this.forgetMeNot.poll();
                this.lastFromForgetMeNot = poll;
                if (poll != null) {
                    this.canRemove = true;
                    return poll;
                }
            }
            throw new NoSuchElementException("iterator moved past last element in queue.");
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Iterator
        public void remove() {
            CollectPreconditions.e(this.canRemove);
            checkModCount();
            this.canRemove = false;
            this.expectedModCount++;
            if (this.cursor >= MinMaxPriorityQueue.this.size()) {
                Preconditions.checkState(removeExact(this.lastFromForgetMeNot));
                this.lastFromForgetMeNot = null;
                return;
            }
            MoveDesc g2 = MinMaxPriorityQueue.this.g(this.cursor);
            if (g2 != null) {
                if (this.forgetMeNot == null) {
                    this.forgetMeNot = new ArrayDeque();
                    this.skipMe = new ArrayList(3);
                }
                if (!foundAndRemovedExactReference(this.skipMe, g2.f8830a)) {
                    this.forgetMeNot.add(g2.f8830a);
                }
                if (!foundAndRemovedExactReference(this.forgetMeNot, g2.f8831b)) {
                    this.skipMe.add(g2.f8831b);
                }
            }
            this.cursor--;
            this.nextCursor--;
        }
    }

    private MinMaxPriorityQueue(Builder<? super E> builder, int i2) {
        Ordering ordering = builder.ordering();
        MinMaxPriorityQueue<E>.Heap heap = new Heap(ordering);
        this.minHeap = heap;
        MinMaxPriorityQueue<E>.Heap heap2 = new Heap(ordering.reverse());
        this.maxHeap = heap2;
        heap.f8828b = heap2;
        heap2.f8828b = heap;
        this.f8826a = ((Builder) builder).maximumSize;
        this.queue = new Object[i2];
    }

    private int calculateNewCapacity() {
        int length = this.queue.length;
        return capAtMaximumSize(length < 64 ? (length + 1) * 2 : IntMath.checkedMultiply(length / 2, 3), this.f8826a);
    }

    private static int capAtMaximumSize(int i2, int i3) {
        return Math.min(i2 - 1, i3) + 1;
    }

    public static <E extends Comparable<E>> MinMaxPriorityQueue<E> create() {
        return new Builder(Ordering.natural()).create();
    }

    public static <E extends Comparable<E>> MinMaxPriorityQueue<E> create(Iterable<? extends E> iterable) {
        return new Builder(Ordering.natural()).create(iterable);
    }

    @VisibleForTesting
    static int e(int i2, int i3, Iterable iterable) {
        if (i2 == -1) {
            i2 = 11;
        }
        if (iterable instanceof Collection) {
            i2 = Math.max(i2, ((Collection) iterable).size());
        }
        return capAtMaximumSize(i2, i3);
    }

    public static Builder<Comparable> expectedSize(int i2) {
        return new Builder(Ordering.natural()).expectedSize(i2);
    }

    @VisibleForTesting
    static boolean f(int i2) {
        int i3 = ~(~(i2 + 1));
        Preconditions.checkState(i3 > 0, "negative index");
        return (EVEN_POWERS_OF_TWO & i3) > (i3 & ODD_POWERS_OF_TWO);
    }

    private MoveDesc<E> fillHole(int i2, E e2) {
        MinMaxPriorityQueue<E>.Heap heapForIndex = heapForIndex(i2);
        int f2 = heapForIndex.f(i2);
        int b2 = heapForIndex.b(f2, e2);
        if (b2 == f2) {
            return heapForIndex.k(i2, f2, e2);
        }
        if (b2 < i2) {
            return new MoveDesc<>(e2, d(i2));
        }
        return null;
    }

    private int getMaxElementIndex() {
        int i2 = this.size;
        if (i2 != 1) {
            return (i2 == 2 || this.maxHeap.c(1, 2) <= 0) ? 1 : 2;
        }
        return 0;
    }

    private void growIfNeeded() {
        if (this.size > this.queue.length) {
            Object[] objArr = new Object[calculateNewCapacity()];
            Object[] objArr2 = this.queue;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.queue = objArr;
        }
    }

    private MinMaxPriorityQueue<E>.Heap heapForIndex(int i2) {
        return f(i2) ? this.minHeap : this.maxHeap;
    }

    public static Builder<Comparable> maximumSize(int i2) {
        return new Builder(Ordering.natural()).maximumSize(i2);
    }

    public static <B> Builder<B> orderedBy(Comparator<B> comparator) {
        return new Builder<>(comparator);
    }

    private E removeAndGet(int i2) {
        E e2 = (E) d(i2);
        g(i2);
        return e2;
    }

    @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection, java.util.Queue
    @CanIgnoreReturnValue
    public boolean add(E e2) {
        offer(e2);
        return true;
    }

    @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection
    @CanIgnoreReturnValue
    public boolean addAll(Collection<? extends E> collection) {
        boolean z = false;
        for (E e2 : collection) {
            offer(e2);
            z = true;
        }
        return z;
    }

    @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection
    public void clear() {
        for (int i2 = 0; i2 < this.size; i2++) {
            this.queue[i2] = null;
        }
        this.size = 0;
    }

    public Comparator<? super E> comparator() {
        return this.minHeap.f8827a;
    }

    Object d(int i2) {
        return this.queue[i2];
    }

    /* JADX WARN: Multi-variable type inference failed */
    @VisibleForTesting
    @CanIgnoreReturnValue
    MoveDesc g(int i2) {
        Preconditions.checkPositionIndex(i2, this.size);
        this.modCount++;
        int i3 = this.size - 1;
        this.size = i3;
        if (i3 == i2) {
            this.queue[i3] = null;
            return null;
        }
        Object d2 = d(i3);
        int j2 = heapForIndex(this.size).j(d2);
        if (j2 == i2) {
            this.queue[this.size] = null;
            return null;
        }
        Object d3 = d(this.size);
        this.queue[this.size] = null;
        MoveDesc fillHole = fillHole(i2, d3);
        return j2 < i2 ? fillHole == null ? new MoveDesc(d2, d3) : new MoveDesc(d2, fillHole.f8831b) : fillHole;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return new QueueIterator();
    }

    @Override // java.util.Queue
    @CanIgnoreReturnValue
    public boolean offer(E e2) {
        Preconditions.checkNotNull(e2);
        this.modCount++;
        int i2 = this.size;
        this.size = i2 + 1;
        growIfNeeded();
        heapForIndex(i2).a(i2, e2);
        return this.size <= this.f8826a || pollLast() != e2;
    }

    @Override // java.util.Queue
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return (E) d(0);
    }

    public E peekFirst() {
        return peek();
    }

    public E peekLast() {
        if (isEmpty()) {
            return null;
        }
        return (E) d(getMaxElementIndex());
    }

    @Override // java.util.Queue
    @CanIgnoreReturnValue
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        return removeAndGet(0);
    }

    @CanIgnoreReturnValue
    public E pollFirst() {
        return poll();
    }

    @CanIgnoreReturnValue
    public E pollLast() {
        if (isEmpty()) {
            return null;
        }
        return removeAndGet(getMaxElementIndex());
    }

    @CanIgnoreReturnValue
    public E removeFirst() {
        return remove();
    }

    @CanIgnoreReturnValue
    public E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return removeAndGet(getMaxElementIndex());
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public int size() {
        return this.size;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public Object[] toArray() {
        int i2 = this.size;
        Object[] objArr = new Object[i2];
        System.arraycopy(this.queue, 0, objArr, 0, i2);
        return objArr;
    }
}
