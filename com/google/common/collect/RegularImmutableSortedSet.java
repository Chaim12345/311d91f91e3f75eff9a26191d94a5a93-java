package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* JADX INFO: Access modifiers changed from: package-private */
@GwtCompatible(emulated = true, serializable = true)
/* loaded from: classes2.dex */
public final class RegularImmutableSortedSet<E> extends ImmutableSortedSet<E> {

    /* renamed from: d  reason: collision with root package name */
    static final RegularImmutableSortedSet f8942d = new RegularImmutableSortedSet(ImmutableList.of(), Ordering.natural());
    @VisibleForTesting

    /* renamed from: c  reason: collision with root package name */
    final transient ImmutableList f8943c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RegularImmutableSortedSet(ImmutableList immutableList, Comparator comparator) {
        super(comparator);
        this.f8943c = immutableList;
    }

    private int unsafeBinarySearch(Object obj) {
        return Collections.binarySearch(this.f8943c, obj, u());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public int a(Object[] objArr, int i2) {
        return this.f8943c.a(objArr, i2);
    }

    @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
    public ImmutableList<E> asList() {
        return this.f8943c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public Object[] b() {
        return this.f8943c.b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public int c() {
        return this.f8943c.c();
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet
    public E ceiling(E e2) {
        int t2 = t(e2, true);
        if (t2 == size()) {
            return null;
        }
        return this.f8943c.get(t2);
    }

    @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(@NullableDecl Object obj) {
        if (obj != null) {
            try {
                return unsafeBinarySearch(obj) >= 0;
            } catch (ClassCastException unused) {
                return false;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean containsAll(Collection<?> collection) {
        if (collection instanceof Multiset) {
            collection = ((Multiset) collection).elementSet();
        }
        if (!SortedIterables.hasSameComparator(comparator(), collection) || collection.size() <= 1) {
            return super.containsAll(collection);
        }
        UnmodifiableIterator<E> it = iterator();
        Iterator<?> it2 = collection.iterator();
        if (!it.hasNext()) {
            return false;
        }
        Object next = it2.next();
        E next2 = it.next();
        while (true) {
            try {
                int p2 = p(next2, next);
                if (p2 < 0) {
                    if (!it.hasNext()) {
                        return false;
                    }
                    next2 = it.next();
                } else if (p2 == 0) {
                    if (!it2.hasNext()) {
                        return true;
                    }
                    next = it2.next();
                } else if (p2 > 0) {
                    break;
                }
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public int d() {
        return this.f8943c.d();
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet
    @GwtIncompatible
    public UnmodifiableIterator<E> descendingIterator() {
        return this.f8943c.reverse().iterator();
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0034 A[Catch: ClassCastException | NoSuchElementException -> 0x0046, TryCatch #0 {ClassCastException | NoSuchElementException -> 0x0046, blocks: (B:17:0x002a, B:18:0x002e, B:20:0x0034, B:22:0x003e), top: B:29:0x002a }] */
    @Override // com.google.common.collect.ImmutableSet, java.util.Collection, java.util.Set
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean equals(@NullableDecl Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            if (size() != set.size()) {
                return false;
            }
            if (isEmpty()) {
                return true;
            }
            if (SortedIterables.hasSameComparator(this.f8624a, set)) {
                Iterator<E> it = set.iterator();
                try {
                    UnmodifiableIterator<E> it2 = iterator();
                    while (it2.hasNext()) {
                        E next = it2.next();
                        E next2 = it.next();
                        if (next2 == null || p(next, next2) != 0) {
                            return false;
                        }
                        while (it2.hasNext()) {
                        }
                    }
                    return true;
                } catch (ClassCastException | NoSuchElementException unused) {
                    return false;
                }
            }
            return containsAll(set);
        }
        return false;
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.SortedSet
    public E first() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.f8943c.get(0);
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet
    public E floor(E e2) {
        int s2 = s(e2, true) - 1;
        if (s2 == -1) {
            return null;
        }
        return this.f8943c.get(s2);
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet
    public E higher(E e2) {
        int t2 = t(e2, false);
        if (t2 == size()) {
            return null;
        }
        return this.f8943c.get(t2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int indexOf(@NullableDecl Object obj) {
        if (obj == null) {
            return -1;
        }
        try {
            int binarySearch = Collections.binarySearch(this.f8943c, obj, u());
            if (binarySearch >= 0) {
                return binarySearch;
            }
            return -1;
        } catch (ClassCastException unused) {
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public boolean isPartialView() {
        return this.f8943c.isPartialView();
    }

    @Override // com.google.common.collect.ImmutableSortedSet, com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
    public UnmodifiableIterator<E> iterator() {
        return this.f8943c.iterator();
    }

    @Override // com.google.common.collect.ImmutableSortedSet
    ImmutableSortedSet k() {
        Comparator reverseOrder = Collections.reverseOrder(this.f8624a);
        return isEmpty() ? ImmutableSortedSet.l(reverseOrder) : new RegularImmutableSortedSet(this.f8943c.reverse(), reverseOrder);
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.SortedSet
    public E last() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.f8943c.get(size() - 1);
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet
    public E lower(E e2) {
        int s2 = s(e2, false) - 1;
        if (s2 == -1) {
            return null;
        }
        return this.f8943c.get(s2);
    }

    @Override // com.google.common.collect.ImmutableSortedSet
    ImmutableSortedSet m(Object obj, boolean z) {
        return r(0, s(obj, z));
    }

    @Override // com.google.common.collect.ImmutableSortedSet
    ImmutableSortedSet n(Object obj, boolean z, Object obj2, boolean z2) {
        return o(obj, z).m(obj2, z2);
    }

    @Override // com.google.common.collect.ImmutableSortedSet
    ImmutableSortedSet o(Object obj, boolean z) {
        return r(t(obj, z), size());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RegularImmutableSortedSet r(int i2, int i3) {
        return (i2 == 0 && i3 == size()) ? this : i2 < i3 ? new RegularImmutableSortedSet(this.f8943c.subList(i2, i3), this.f8624a) : ImmutableSortedSet.l(this.f8624a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int s(Object obj, boolean z) {
        int binarySearch = Collections.binarySearch(this.f8943c, Preconditions.checkNotNull(obj), comparator());
        return binarySearch >= 0 ? z ? binarySearch + 1 : binarySearch : ~binarySearch;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        return this.f8943c.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int t(Object obj, boolean z) {
        int binarySearch = Collections.binarySearch(this.f8943c, Preconditions.checkNotNull(obj), comparator());
        return binarySearch >= 0 ? z ? binarySearch : binarySearch + 1 : ~binarySearch;
    }

    Comparator u() {
        return this.f8624a;
    }
}
