package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible(emulated = true)
/* loaded from: classes2.dex */
public final class TreeMultiset<E> extends AbstractSortedMultiset<E> implements Serializable {
    @GwtIncompatible
    private static final long serialVersionUID = 1;
    private final transient AvlNode<E> header;
    private final transient GeneralRange<E> range;
    private final transient Reference<AvlNode<E>> rootReference;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.common.collect.TreeMultiset$4  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass4 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f9072a;

        static {
            int[] iArr = new int[BoundType.values().length];
            f9072a = iArr;
            try {
                iArr[BoundType.OPEN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9072a[BoundType.CLOSED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public enum Aggregate {
        SIZE { // from class: com.google.common.collect.TreeMultiset.Aggregate.1
            @Override // com.google.common.collect.TreeMultiset.Aggregate
            int a(AvlNode<?> avlNode) {
                return ((AvlNode) avlNode).elemCount;
            }

            @Override // com.google.common.collect.TreeMultiset.Aggregate
            long b(@NullableDecl AvlNode<?> avlNode) {
                if (avlNode == null) {
                    return 0L;
                }
                return ((AvlNode) avlNode).totalCount;
            }
        },
        DISTINCT { // from class: com.google.common.collect.TreeMultiset.Aggregate.2
            @Override // com.google.common.collect.TreeMultiset.Aggregate
            int a(AvlNode<?> avlNode) {
                return 1;
            }

            @Override // com.google.common.collect.TreeMultiset.Aggregate
            long b(@NullableDecl AvlNode<?> avlNode) {
                if (avlNode == null) {
                    return 0L;
                }
                return ((AvlNode) avlNode).distinctElements;
            }
        };

        abstract int a(AvlNode avlNode);

        abstract long b(@NullableDecl AvlNode avlNode);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class AvlNode<E> {
        private int distinctElements;
        @NullableDecl
        private final E elem;
        private int elemCount;
        private int height;
        @NullableDecl
        private AvlNode<E> left;
        @NullableDecl
        private AvlNode<E> pred;
        @NullableDecl
        private AvlNode<E> right;
        @NullableDecl
        private AvlNode<E> succ;
        private long totalCount;

        /* JADX WARN: Multi-variable type inference failed */
        AvlNode(@NullableDecl Object obj, int i2) {
            Preconditions.checkArgument(i2 > 0);
            this.elem = obj;
            this.elemCount = i2;
            this.totalCount = i2;
            this.distinctElements = 1;
            this.height = 1;
            this.left = null;
            this.right = null;
        }

        private AvlNode<E> addLeftChild(E e2, int i2) {
            AvlNode<E> avlNode = new AvlNode<>(e2, i2);
            this.left = avlNode;
            TreeMultiset.successor(this.pred, avlNode, this);
            this.height = Math.max(2, this.height);
            this.distinctElements++;
            this.totalCount += i2;
            return this;
        }

        private AvlNode<E> addRightChild(E e2, int i2) {
            AvlNode<E> avlNode = new AvlNode<>(e2, i2);
            this.right = avlNode;
            TreeMultiset.successor(this, avlNode, this.succ);
            this.height = Math.max(2, this.height);
            this.distinctElements++;
            this.totalCount += i2;
            return this;
        }

        private int balanceFactor() {
            return height(this.left) - height(this.right);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        @NullableDecl
        public AvlNode<E> ceiling(Comparator<? super E> comparator, E e2) {
            int compare = comparator.compare(e2, (E) this.elem);
            if (compare < 0) {
                AvlNode<E> avlNode = this.left;
                return avlNode == null ? this : (AvlNode) MoreObjects.firstNonNull(avlNode.ceiling(comparator, e2), this);
            } else if (compare == 0) {
                return this;
            } else {
                AvlNode<E> avlNode2 = this.right;
                if (avlNode2 == null) {
                    return null;
                }
                return avlNode2.ceiling(comparator, e2);
            }
        }

        private AvlNode<E> deleteMe() {
            int i2 = this.elemCount;
            this.elemCount = 0;
            TreeMultiset.successor(this.pred, this.succ);
            AvlNode<E> avlNode = this.left;
            if (avlNode == null) {
                return this.right;
            }
            AvlNode<E> avlNode2 = this.right;
            if (avlNode2 == null) {
                return avlNode;
            }
            if (avlNode.height >= avlNode2.height) {
                AvlNode<E> avlNode3 = this.pred;
                avlNode3.left = avlNode.removeMax(avlNode3);
                avlNode3.right = this.right;
                avlNode3.distinctElements = this.distinctElements - 1;
                avlNode3.totalCount = this.totalCount - i2;
                return avlNode3.rebalance();
            }
            AvlNode<E> avlNode4 = this.succ;
            avlNode4.right = avlNode2.removeMin(avlNode4);
            avlNode4.left = this.left;
            avlNode4.distinctElements = this.distinctElements - 1;
            avlNode4.totalCount = this.totalCount - i2;
            return avlNode4.rebalance();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        @NullableDecl
        public AvlNode<E> floor(Comparator<? super E> comparator, E e2) {
            int compare = comparator.compare(e2, (E) this.elem);
            if (compare > 0) {
                AvlNode<E> avlNode = this.right;
                return avlNode == null ? this : (AvlNode) MoreObjects.firstNonNull(avlNode.floor(comparator, e2), this);
            } else if (compare == 0) {
                return this;
            } else {
                AvlNode<E> avlNode2 = this.left;
                if (avlNode2 == null) {
                    return null;
                }
                return avlNode2.floor(comparator, e2);
            }
        }

        private static int height(@NullableDecl AvlNode<?> avlNode) {
            if (avlNode == null) {
                return 0;
            }
            return ((AvlNode) avlNode).height;
        }

        private AvlNode<E> rebalance() {
            int balanceFactor = balanceFactor();
            if (balanceFactor == -2) {
                if (this.right.balanceFactor() > 0) {
                    this.right = this.right.rotateRight();
                }
                return rotateLeft();
            } else if (balanceFactor != 2) {
                recomputeHeight();
                return this;
            } else {
                if (this.left.balanceFactor() < 0) {
                    this.left = this.left.rotateLeft();
                }
                return rotateRight();
            }
        }

        private void recompute() {
            recomputeMultiset();
            recomputeHeight();
        }

        private void recomputeHeight() {
            this.height = Math.max(height(this.left), height(this.right)) + 1;
        }

        private void recomputeMultiset() {
            this.distinctElements = TreeMultiset.o(this.left) + 1 + TreeMultiset.o(this.right);
            this.totalCount = this.elemCount + totalCount(this.left) + totalCount(this.right);
        }

        private AvlNode<E> removeMax(AvlNode<E> avlNode) {
            AvlNode<E> avlNode2 = this.right;
            if (avlNode2 == null) {
                return this.left;
            }
            this.right = avlNode2.removeMax(avlNode);
            this.distinctElements--;
            this.totalCount -= avlNode.elemCount;
            return rebalance();
        }

        private AvlNode<E> removeMin(AvlNode<E> avlNode) {
            AvlNode<E> avlNode2 = this.left;
            if (avlNode2 == null) {
                return this.right;
            }
            this.left = avlNode2.removeMin(avlNode);
            this.distinctElements--;
            this.totalCount -= avlNode.elemCount;
            return rebalance();
        }

        private AvlNode<E> rotateLeft() {
            Preconditions.checkState(this.right != null);
            AvlNode<E> avlNode = this.right;
            this.right = avlNode.left;
            avlNode.left = this;
            avlNode.totalCount = this.totalCount;
            avlNode.distinctElements = this.distinctElements;
            recompute();
            avlNode.recomputeHeight();
            return avlNode;
        }

        private AvlNode<E> rotateRight() {
            Preconditions.checkState(this.left != null);
            AvlNode<E> avlNode = this.left;
            this.left = avlNode.right;
            avlNode.right = this;
            avlNode.totalCount = this.totalCount;
            avlNode.distinctElements = this.distinctElements;
            recompute();
            avlNode.recomputeHeight();
            return avlNode;
        }

        private static long totalCount(@NullableDecl AvlNode<?> avlNode) {
            if (avlNode == null) {
                return 0L;
            }
            return ((AvlNode) avlNode).totalCount;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int count(Comparator<? super E> comparator, E e2) {
            int compare = comparator.compare(e2, (E) this.elem);
            if (compare < 0) {
                AvlNode<E> avlNode = this.left;
                if (avlNode == null) {
                    return 0;
                }
                return avlNode.count(comparator, e2);
            } else if (compare > 0) {
                AvlNode<E> avlNode2 = this.right;
                if (avlNode2 == null) {
                    return 0;
                }
                return avlNode2.count(comparator, e2);
            } else {
                return this.elemCount;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        AvlNode p(Comparator comparator, @NullableDecl Object obj, int i2, int[] iArr) {
            int compare = comparator.compare(obj, this.elem);
            if (compare < 0) {
                AvlNode<E> avlNode = this.left;
                if (avlNode == null) {
                    iArr[0] = 0;
                    return addLeftChild(obj, i2);
                }
                int i3 = avlNode.height;
                AvlNode<E> p2 = avlNode.p(comparator, obj, i2, iArr);
                this.left = p2;
                if (iArr[0] == 0) {
                    this.distinctElements++;
                }
                this.totalCount += i2;
                return p2.height == i3 ? this : rebalance();
            } else if (compare <= 0) {
                int i4 = this.elemCount;
                iArr[0] = i4;
                long j2 = i2;
                Preconditions.checkArgument(((long) i4) + j2 <= 2147483647L);
                this.elemCount += i2;
                this.totalCount += j2;
                return this;
            } else {
                AvlNode<E> avlNode2 = this.right;
                if (avlNode2 == null) {
                    iArr[0] = 0;
                    return addRightChild(obj, i2);
                }
                int i5 = avlNode2.height;
                AvlNode<E> p3 = avlNode2.p(comparator, obj, i2, iArr);
                this.right = p3;
                if (iArr[0] == 0) {
                    this.distinctElements++;
                }
                this.totalCount += i2;
                return p3.height == i5 ? this : rebalance();
            }
        }

        int q() {
            return this.elemCount;
        }

        Object r() {
            return this.elem;
        }

        AvlNode s(Comparator comparator, @NullableDecl Object obj, int i2, int[] iArr) {
            long j2;
            long j3;
            int compare = comparator.compare(obj, this.elem);
            if (compare < 0) {
                AvlNode<E> avlNode = this.left;
                if (avlNode == null) {
                    iArr[0] = 0;
                    return this;
                }
                this.left = avlNode.s(comparator, obj, i2, iArr);
                if (iArr[0] > 0) {
                    if (i2 >= iArr[0]) {
                        this.distinctElements--;
                        j3 = this.totalCount;
                        i2 = iArr[0];
                    } else {
                        j3 = this.totalCount;
                    }
                    this.totalCount = j3 - i2;
                }
                return iArr[0] == 0 ? this : rebalance();
            } else if (compare <= 0) {
                int i3 = this.elemCount;
                iArr[0] = i3;
                if (i2 >= i3) {
                    return deleteMe();
                }
                this.elemCount = i3 - i2;
                this.totalCount -= i2;
                return this;
            } else {
                AvlNode<E> avlNode2 = this.right;
                if (avlNode2 == null) {
                    iArr[0] = 0;
                    return this;
                }
                this.right = avlNode2.s(comparator, obj, i2, iArr);
                if (iArr[0] > 0) {
                    if (i2 >= iArr[0]) {
                        this.distinctElements--;
                        j2 = this.totalCount;
                        i2 = iArr[0];
                    } else {
                        j2 = this.totalCount;
                    }
                    this.totalCount = j2 - i2;
                }
                return rebalance();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        AvlNode t(Comparator comparator, @NullableDecl Object obj, int i2, int i3, int[] iArr) {
            int i4;
            int i5;
            int compare = comparator.compare(obj, this.elem);
            if (compare < 0) {
                AvlNode<E> avlNode = this.left;
                if (avlNode == null) {
                    iArr[0] = 0;
                    return (i2 != 0 || i3 <= 0) ? this : addLeftChild(obj, i3);
                }
                this.left = avlNode.t(comparator, obj, i2, i3, iArr);
                if (iArr[0] == i2) {
                    if (i3 != 0 || iArr[0] == 0) {
                        if (i3 > 0 && iArr[0] == 0) {
                            i5 = this.distinctElements + 1;
                        }
                        this.totalCount += i3 - iArr[0];
                    } else {
                        i5 = this.distinctElements - 1;
                    }
                    this.distinctElements = i5;
                    this.totalCount += i3 - iArr[0];
                }
                return rebalance();
            } else if (compare <= 0) {
                int i6 = this.elemCount;
                iArr[0] = i6;
                if (i2 == i6) {
                    if (i3 == 0) {
                        return deleteMe();
                    }
                    this.totalCount += i3 - i6;
                    this.elemCount = i3;
                }
                return this;
            } else {
                AvlNode<E> avlNode2 = this.right;
                if (avlNode2 == null) {
                    iArr[0] = 0;
                    return (i2 != 0 || i3 <= 0) ? this : addRightChild(obj, i3);
                }
                this.right = avlNode2.t(comparator, obj, i2, i3, iArr);
                if (iArr[0] == i2) {
                    if (i3 != 0 || iArr[0] == 0) {
                        if (i3 > 0 && iArr[0] == 0) {
                            i4 = this.distinctElements + 1;
                        }
                        this.totalCount += i3 - iArr[0];
                    } else {
                        i4 = this.distinctElements - 1;
                    }
                    this.distinctElements = i4;
                    this.totalCount += i3 - iArr[0];
                }
                return rebalance();
            }
        }

        public String toString() {
            return Multisets.immutableEntry(r(), q()).toString();
        }

        /* JADX WARN: Multi-variable type inference failed */
        AvlNode u(Comparator comparator, @NullableDecl Object obj, int i2, int[] iArr) {
            int i3;
            int i4;
            long j2;
            int i5;
            int i6;
            int compare = comparator.compare(obj, this.elem);
            if (compare < 0) {
                AvlNode<E> avlNode = this.left;
                if (avlNode == null) {
                    iArr[0] = 0;
                    return i2 > 0 ? addLeftChild(obj, i2) : this;
                }
                this.left = avlNode.u(comparator, obj, i2, iArr);
                if (i2 != 0 || iArr[0] == 0) {
                    if (i2 > 0 && iArr[0] == 0) {
                        i6 = this.distinctElements + 1;
                    }
                    j2 = this.totalCount;
                    i5 = iArr[0];
                } else {
                    i6 = this.distinctElements - 1;
                }
                this.distinctElements = i6;
                j2 = this.totalCount;
                i5 = iArr[0];
            } else if (compare <= 0) {
                iArr[0] = this.elemCount;
                if (i2 == 0) {
                    return deleteMe();
                }
                this.totalCount += i2 - i3;
                this.elemCount = i2;
                return this;
            } else {
                AvlNode<E> avlNode2 = this.right;
                if (avlNode2 == null) {
                    iArr[0] = 0;
                    return i2 > 0 ? addRightChild(obj, i2) : this;
                }
                this.right = avlNode2.u(comparator, obj, i2, iArr);
                if (i2 != 0 || iArr[0] == 0) {
                    if (i2 > 0 && iArr[0] == 0) {
                        i4 = this.distinctElements + 1;
                    }
                    j2 = this.totalCount;
                    i5 = iArr[0];
                } else {
                    i4 = this.distinctElements - 1;
                }
                this.distinctElements = i4;
                j2 = this.totalCount;
                i5 = iArr[0];
            }
            this.totalCount = j2 + (i2 - i5);
            return rebalance();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class Reference<T> {
        @NullableDecl
        private T value;

        private Reference() {
        }

        void a() {
            this.value = null;
        }

        public void checkAndSet(@NullableDecl T t2, T t3) {
            if (this.value != t2) {
                throw new ConcurrentModificationException();
            }
            this.value = t3;
        }

        @NullableDecl
        public T get() {
            return this.value;
        }
    }

    TreeMultiset(Reference reference, GeneralRange generalRange, AvlNode avlNode) {
        super(generalRange.b());
        this.rootReference = reference;
        this.range = generalRange;
        this.header = avlNode;
    }

    TreeMultiset(Comparator comparator) {
        super(comparator);
        this.range = GeneralRange.a(comparator);
        AvlNode<E> avlNode = new AvlNode<>(null, 1);
        this.header = avlNode;
        successor(avlNode, avlNode);
        this.rootReference = new Reference<>();
    }

    private long aggregateAboveRange(Aggregate aggregate, @NullableDecl AvlNode<E> avlNode) {
        long b2;
        long aggregateAboveRange;
        if (avlNode == null) {
            return 0L;
        }
        int compare = comparator().compare(this.range.h(), ((AvlNode) avlNode).elem);
        if (compare > 0) {
            return aggregateAboveRange(aggregate, ((AvlNode) avlNode).right);
        }
        if (compare == 0) {
            int i2 = AnonymousClass4.f9072a[this.range.g().ordinal()];
            if (i2 != 1) {
                if (i2 == 2) {
                    return aggregate.b(((AvlNode) avlNode).right);
                }
                throw new AssertionError();
            }
            b2 = aggregate.a(avlNode);
            aggregateAboveRange = aggregate.b(((AvlNode) avlNode).right);
        } else {
            b2 = aggregate.b(((AvlNode) avlNode).right) + aggregate.a(avlNode);
            aggregateAboveRange = aggregateAboveRange(aggregate, ((AvlNode) avlNode).left);
        }
        return b2 + aggregateAboveRange;
    }

    private long aggregateBelowRange(Aggregate aggregate, @NullableDecl AvlNode<E> avlNode) {
        long b2;
        long aggregateBelowRange;
        if (avlNode == null) {
            return 0L;
        }
        int compare = comparator().compare(this.range.f(), ((AvlNode) avlNode).elem);
        if (compare < 0) {
            return aggregateBelowRange(aggregate, ((AvlNode) avlNode).left);
        }
        if (compare == 0) {
            int i2 = AnonymousClass4.f9072a[this.range.e().ordinal()];
            if (i2 != 1) {
                if (i2 == 2) {
                    return aggregate.b(((AvlNode) avlNode).left);
                }
                throw new AssertionError();
            }
            b2 = aggregate.a(avlNode);
            aggregateBelowRange = aggregate.b(((AvlNode) avlNode).left);
        } else {
            b2 = aggregate.b(((AvlNode) avlNode).left) + aggregate.a(avlNode);
            aggregateBelowRange = aggregateBelowRange(aggregate, ((AvlNode) avlNode).right);
        }
        return b2 + aggregateBelowRange;
    }

    private long aggregateForEntries(Aggregate aggregate) {
        AvlNode<E> avlNode = this.rootReference.get();
        long b2 = aggregate.b(avlNode);
        if (this.range.i()) {
            b2 -= aggregateBelowRange(aggregate, avlNode);
        }
        return this.range.j() ? b2 - aggregateAboveRange(aggregate, avlNode) : b2;
    }

    public static <E extends Comparable> TreeMultiset<E> create() {
        return new TreeMultiset<>(Ordering.natural());
    }

    public static <E extends Comparable> TreeMultiset<E> create(Iterable<? extends E> iterable) {
        TreeMultiset<E> create = create();
        Iterables.addAll(create, iterable);
        return create;
    }

    public static <E> TreeMultiset<E> create(@NullableDecl Comparator<? super E> comparator) {
        return comparator == null ? new TreeMultiset<>(Ordering.natural()) : new TreeMultiset<>(comparator);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @NullableDecl
    public AvlNode<E> firstNode() {
        AvlNode<E> avlNode;
        if (this.rootReference.get() == null) {
            return null;
        }
        if (this.range.i()) {
            Object f2 = this.range.f();
            avlNode = this.rootReference.get().ceiling(comparator(), f2);
            if (avlNode == null) {
                return null;
            }
            if (this.range.e() == BoundType.OPEN && comparator().compare(f2, avlNode.r()) == 0) {
                avlNode = ((AvlNode) avlNode).succ;
            }
        } else {
            avlNode = ((AvlNode) this.header).succ;
        }
        if (avlNode == this.header || !this.range.c(avlNode.r())) {
            return null;
        }
        return avlNode;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @NullableDecl
    public AvlNode<E> lastNode() {
        AvlNode<E> avlNode;
        if (this.rootReference.get() == null) {
            return null;
        }
        if (this.range.j()) {
            Object h2 = this.range.h();
            avlNode = this.rootReference.get().floor(comparator(), h2);
            if (avlNode == null) {
                return null;
            }
            if (this.range.g() == BoundType.OPEN && comparator().compare(h2, avlNode.r()) == 0) {
                avlNode = ((AvlNode) avlNode).pred;
            }
        } else {
            avlNode = ((AvlNode) this.header).pred;
        }
        if (avlNode == this.header || !this.range.c(avlNode.r())) {
            return null;
        }
        return avlNode;
    }

    static int o(@NullableDecl AvlNode avlNode) {
        if (avlNode == null) {
            return 0;
        }
        return avlNode.distinctElements;
    }

    @GwtIncompatible
    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        Comparator comparator = (Comparator) objectInputStream.readObject();
        Serialization.a(AbstractSortedMultiset.class, "comparator").b(this, comparator);
        Serialization.a(TreeMultiset.class, "range").b(this, GeneralRange.a(comparator));
        Serialization.a(TreeMultiset.class, "rootReference").b(this, new Reference());
        AvlNode avlNode = new AvlNode(null, 1);
        Serialization.a(TreeMultiset.class, "header").b(this, avlNode);
        successor(avlNode, avlNode);
        Serialization.f(this, objectInputStream);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> void successor(AvlNode<T> avlNode, AvlNode<T> avlNode2) {
        ((AvlNode) avlNode).succ = avlNode2;
        ((AvlNode) avlNode2).pred = avlNode;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> void successor(AvlNode<T> avlNode, AvlNode<T> avlNode2, AvlNode<T> avlNode3) {
        successor(avlNode, avlNode2);
        successor(avlNode2, avlNode3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Multiset.Entry<E> wrapEntry(final AvlNode<E> avlNode) {
        return new Multisets.AbstractEntry<E>() { // from class: com.google.common.collect.TreeMultiset.1
            @Override // com.google.common.collect.Multiset.Entry
            public int getCount() {
                int q2 = avlNode.q();
                return q2 == 0 ? TreeMultiset.this.count(getElement()) : q2;
            }

            @Override // com.google.common.collect.Multiset.Entry
            public E getElement() {
                return (E) avlNode.r();
            }
        };
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(elementSet().comparator());
        Serialization.k(this, objectOutputStream);
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public int add(@NullableDecl E e2, int i2) {
        CollectPreconditions.b(i2, "occurrences");
        if (i2 == 0) {
            return count(e2);
        }
        Preconditions.checkArgument(this.range.c(e2));
        AvlNode<E> avlNode = this.rootReference.get();
        if (avlNode != null) {
            int[] iArr = new int[1];
            this.rootReference.checkAndSet(avlNode, avlNode.p(comparator(), e2, i2, iArr));
            return iArr[0];
        }
        comparator().compare(e2, e2);
        AvlNode<E> avlNode2 = new AvlNode<>(e2, i2);
        AvlNode<E> avlNode3 = this.header;
        successor(avlNode3, avlNode2, avlNode3);
        this.rootReference.checkAndSet(avlNode, avlNode2);
        return 0;
    }

    @Override // com.google.common.collect.AbstractMultiset
    int b() {
        return Ints.saturatedCast(aggregateForEntries(Aggregate.DISTINCT));
    }

    @Override // com.google.common.collect.AbstractMultiset
    Iterator c() {
        return Multisets.c(d());
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection
    public void clear() {
        if (this.range.i() || this.range.j()) {
            Iterators.c(d());
            return;
        }
        AvlNode<E> avlNode = ((AvlNode) this.header).succ;
        while (true) {
            AvlNode<E> avlNode2 = this.header;
            if (avlNode == avlNode2) {
                successor(avlNode2, avlNode2);
                this.rootReference.a();
                return;
            }
            AvlNode<E> avlNode3 = ((AvlNode) avlNode).succ;
            ((AvlNode) avlNode).elemCount = 0;
            ((AvlNode) avlNode).left = null;
            ((AvlNode) avlNode).right = null;
            ((AvlNode) avlNode).pred = null;
            ((AvlNode) avlNode).succ = null;
            avlNode = avlNode3;
        }
    }

    @Override // com.google.common.collect.AbstractSortedMultiset, com.google.common.collect.SortedMultiset, com.google.common.collect.SortedIterable
    public /* bridge */ /* synthetic */ Comparator comparator() {
        return super.comparator();
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    public /* bridge */ /* synthetic */ boolean contains(@NullableDecl Object obj) {
        return super.contains(obj);
    }

    @Override // com.google.common.collect.Multiset
    public int count(@NullableDecl Object obj) {
        try {
            AvlNode<E> avlNode = this.rootReference.get();
            if (this.range.c(obj) && avlNode != null) {
                return avlNode.count(comparator(), obj);
            }
        } catch (ClassCastException | NullPointerException unused) {
        }
        return 0;
    }

    @Override // com.google.common.collect.AbstractMultiset
    Iterator d() {
        return new Iterator<Multiset.Entry<E>>() { // from class: com.google.common.collect.TreeMultiset.2

            /* renamed from: a  reason: collision with root package name */
            AvlNode f9066a;
            @NullableDecl

            /* renamed from: b  reason: collision with root package name */
            Multiset.Entry f9067b;

            {
                this.f9066a = TreeMultiset.this.firstNode();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                if (this.f9066a == null) {
                    return false;
                }
                if (TreeMultiset.this.range.l(this.f9066a.r())) {
                    this.f9066a = null;
                    return false;
                }
                return true;
            }

            @Override // java.util.Iterator
            public Multiset.Entry<E> next() {
                if (hasNext()) {
                    Multiset.Entry<E> wrapEntry = TreeMultiset.this.wrapEntry(this.f9066a);
                    this.f9067b = wrapEntry;
                    this.f9066a = this.f9066a.succ == TreeMultiset.this.header ? null : this.f9066a.succ;
                    return wrapEntry;
                }
                throw new NoSuchElementException();
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Iterator
            public void remove() {
                CollectPreconditions.e(this.f9067b != null);
                TreeMultiset.this.setCount(this.f9067b.getElement(), 0);
                this.f9067b = null;
            }
        };
    }

    @Override // com.google.common.collect.AbstractSortedMultiset, com.google.common.collect.SortedMultiset
    public /* bridge */ /* synthetic */ SortedMultiset descendingMultiset() {
        return super.descendingMultiset();
    }

    @Override // com.google.common.collect.AbstractSortedMultiset, com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    public /* bridge */ /* synthetic */ NavigableSet elementSet() {
        return super.elementSet();
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    public /* bridge */ /* synthetic */ Set entrySet() {
        return super.entrySet();
    }

    @Override // com.google.common.collect.AbstractSortedMultiset, com.google.common.collect.SortedMultiset
    public /* bridge */ /* synthetic */ Multiset.Entry firstEntry() {
        return super.firstEntry();
    }

    @Override // com.google.common.collect.AbstractSortedMultiset
    Iterator g() {
        return new Iterator<Multiset.Entry<E>>() { // from class: com.google.common.collect.TreeMultiset.3

            /* renamed from: a  reason: collision with root package name */
            AvlNode f9069a;

            /* renamed from: b  reason: collision with root package name */
            Multiset.Entry f9070b = null;

            {
                this.f9069a = TreeMultiset.this.lastNode();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                if (this.f9069a == null) {
                    return false;
                }
                if (TreeMultiset.this.range.m(this.f9069a.r())) {
                    this.f9069a = null;
                    return false;
                }
                return true;
            }

            @Override // java.util.Iterator
            public Multiset.Entry<E> next() {
                if (hasNext()) {
                    Multiset.Entry<E> wrapEntry = TreeMultiset.this.wrapEntry(this.f9069a);
                    this.f9070b = wrapEntry;
                    this.f9069a = this.f9069a.pred == TreeMultiset.this.header ? null : this.f9069a.pred;
                    return wrapEntry;
                }
                throw new NoSuchElementException();
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Iterator
            public void remove() {
                CollectPreconditions.e(this.f9070b != null);
                TreeMultiset.this.setCount(this.f9070b.getElement(), 0);
                this.f9070b = null;
            }
        };
    }

    @Override // com.google.common.collect.SortedMultiset
    public SortedMultiset<E> headMultiset(@NullableDecl E e2, BoundType boundType) {
        return new TreeMultiset(this.rootReference, this.range.k(GeneralRange.n(comparator(), e2, boundType)), this.header);
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection
    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.google.common.collect.Multiset
    public Iterator<E> iterator() {
        return Multisets.f(this);
    }

    @Override // com.google.common.collect.AbstractSortedMultiset, com.google.common.collect.SortedMultiset
    public /* bridge */ /* synthetic */ Multiset.Entry lastEntry() {
        return super.lastEntry();
    }

    @Override // com.google.common.collect.AbstractSortedMultiset, com.google.common.collect.SortedMultiset
    public /* bridge */ /* synthetic */ Multiset.Entry pollFirstEntry() {
        return super.pollFirstEntry();
    }

    @Override // com.google.common.collect.AbstractSortedMultiset, com.google.common.collect.SortedMultiset
    public /* bridge */ /* synthetic */ Multiset.Entry pollLastEntry() {
        return super.pollLastEntry();
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public int remove(@NullableDecl Object obj, int i2) {
        CollectPreconditions.b(i2, "occurrences");
        if (i2 == 0) {
            return count(obj);
        }
        AvlNode<E> avlNode = this.rootReference.get();
        int[] iArr = new int[1];
        try {
            if (this.range.c(obj) && avlNode != null) {
                this.rootReference.checkAndSet(avlNode, avlNode.s(comparator(), obj, i2, iArr));
                return iArr[0];
            }
        } catch (ClassCastException | NullPointerException unused) {
        }
        return 0;
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public int setCount(@NullableDecl E e2, int i2) {
        CollectPreconditions.b(i2, "count");
        if (!this.range.c(e2)) {
            Preconditions.checkArgument(i2 == 0);
            return 0;
        }
        AvlNode<E> avlNode = this.rootReference.get();
        if (avlNode == null) {
            if (i2 > 0) {
                add(e2, i2);
            }
            return 0;
        }
        int[] iArr = new int[1];
        this.rootReference.checkAndSet(avlNode, avlNode.u(comparator(), e2, i2, iArr));
        return iArr[0];
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public boolean setCount(@NullableDecl E e2, int i2, int i3) {
        CollectPreconditions.b(i3, "newCount");
        CollectPreconditions.b(i2, "oldCount");
        Preconditions.checkArgument(this.range.c(e2));
        AvlNode<E> avlNode = this.rootReference.get();
        if (avlNode != null) {
            int[] iArr = new int[1];
            this.rootReference.checkAndSet(avlNode, avlNode.t(comparator(), e2, i2, i3, iArr));
            return iArr[0] == i2;
        } else if (i2 == 0) {
            if (i3 > 0) {
                add(e2, i3);
            }
            return true;
        } else {
            return false;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    public int size() {
        return Ints.saturatedCast(aggregateForEntries(Aggregate.SIZE));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.AbstractSortedMultiset, com.google.common.collect.SortedMultiset
    public /* bridge */ /* synthetic */ SortedMultiset subMultiset(@NullableDecl Object obj, BoundType boundType, @NullableDecl Object obj2, BoundType boundType2) {
        return super.subMultiset(obj, boundType, obj2, boundType2);
    }

    @Override // com.google.common.collect.SortedMultiset
    public SortedMultiset<E> tailMultiset(@NullableDecl E e2, BoundType boundType) {
        return new TreeMultiset(this.rootReference, this.range.k(GeneralRange.d(comparator(), e2, boundType)), this.header);
    }
}
