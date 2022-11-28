package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.primitives.Ints;
import java.util.Comparator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* JADX INFO: Access modifiers changed from: package-private */
@GwtIncompatible
/* loaded from: classes2.dex */
public final class RegularImmutableSortedMultiset<E> extends ImmutableSortedMultiset<E> {
    private static final long[] ZERO_CUMULATIVE_COUNTS = {0};

    /* renamed from: c  reason: collision with root package name */
    static final ImmutableSortedMultiset f8940c = new RegularImmutableSortedMultiset(Ordering.natural());
    @VisibleForTesting

    /* renamed from: b  reason: collision with root package name */
    final transient RegularImmutableSortedSet f8941b;
    private final transient long[] cumulativeCounts;
    private final transient int length;
    private final transient int offset;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RegularImmutableSortedMultiset(RegularImmutableSortedSet regularImmutableSortedSet, long[] jArr, int i2, int i3) {
        this.f8941b = regularImmutableSortedSet;
        this.cumulativeCounts = jArr;
        this.offset = i2;
        this.length = i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RegularImmutableSortedMultiset(Comparator comparator) {
        this.f8941b = ImmutableSortedSet.l(comparator);
        this.cumulativeCounts = ZERO_CUMULATIVE_COUNTS;
        this.offset = 0;
        this.length = 0;
    }

    private int getCount(int i2) {
        long[] jArr = this.cumulativeCounts;
        int i3 = this.offset;
        return (int) (jArr[(i3 + i2) + 1] - jArr[i3 + i2]);
    }

    @Override // com.google.common.collect.Multiset
    public int count(@NullableDecl Object obj) {
        int indexOf = this.f8941b.indexOf(obj);
        if (indexOf >= 0) {
            return getCount(indexOf);
        }
        return 0;
    }

    @Override // com.google.common.collect.ImmutableSortedMultiset, com.google.common.collect.ImmutableMultiset, com.google.common.collect.Multiset
    public ImmutableSortedSet<E> elementSet() {
        return this.f8941b;
    }

    @Override // com.google.common.collect.ImmutableMultiset
    Multiset.Entry f(int i2) {
        return Multisets.immutableEntry(this.f8941b.asList().get(i2), getCount(i2));
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> firstEntry() {
        if (isEmpty()) {
            return null;
        }
        return f(0);
    }

    ImmutableSortedMultiset h(int i2, int i3) {
        Preconditions.checkPositionIndexes(i2, i3, this.length);
        return i2 == i3 ? ImmutableSortedMultiset.g(comparator()) : (i2 == 0 && i3 == this.length) ? this : new RegularImmutableSortedMultiset(this.f8941b.r(i2, i3), this.cumulativeCounts, this.offset + i2, i3 - i2);
    }

    @Override // com.google.common.collect.ImmutableSortedMultiset, com.google.common.collect.SortedMultiset
    public ImmutableSortedMultiset<E> headMultiset(E e2, BoundType boundType) {
        return h(0, this.f8941b.s(e2, Preconditions.checkNotNull(boundType) == BoundType.CLOSED));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.ImmutableSortedMultiset, com.google.common.collect.SortedMultiset
    public /* bridge */ /* synthetic */ SortedMultiset headMultiset(Object obj, BoundType boundType) {
        return headMultiset((RegularImmutableSortedMultiset<E>) obj, boundType);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public boolean isPartialView() {
        return this.offset > 0 || this.length < this.cumulativeCounts.length - 1;
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> lastEntry() {
        if (isEmpty()) {
            return null;
        }
        return f(this.length - 1);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    public int size() {
        long[] jArr = this.cumulativeCounts;
        int i2 = this.offset;
        return Ints.saturatedCast(jArr[this.length + i2] - jArr[i2]);
    }

    @Override // com.google.common.collect.ImmutableSortedMultiset, com.google.common.collect.SortedMultiset
    public ImmutableSortedMultiset<E> tailMultiset(E e2, BoundType boundType) {
        return h(this.f8941b.t(e2, Preconditions.checkNotNull(boundType) == BoundType.CLOSED), this.length);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.ImmutableSortedMultiset, com.google.common.collect.SortedMultiset
    public /* bridge */ /* synthetic */ SortedMultiset tailMultiset(Object obj, BoundType boundType) {
        return tailMultiset((RegularImmutableSortedMultiset<E>) obj, boundType);
    }
}
