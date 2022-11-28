package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Comparator;
import java.util.SortedSet;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible
/* loaded from: classes2.dex */
public abstract class ForwardingSortedSet<E> extends ForwardingSet<E> implements SortedSet<E> {
    private int unsafeCompare(@NullableDecl Object obj, @NullableDecl Object obj2) {
        Comparator<? super E> comparator = comparator();
        return comparator == null ? ((Comparable) obj).compareTo(obj2) : comparator.compare(obj, obj2);
    }

    @Override // java.util.SortedSet
    public Comparator<? super E> comparator() {
        return h().comparator();
    }

    @Override // java.util.SortedSet
    public E first() {
        return (E) h().first();
    }

    @Override // java.util.SortedSet
    public SortedSet<E> headSet(E e2) {
        return h().headSet(e2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingSet
    /* renamed from: j */
    public abstract SortedSet h();

    @Override // java.util.SortedSet
    public E last() {
        return (E) h().last();
    }

    @Override // java.util.SortedSet
    public SortedSet<E> subSet(E e2, E e3) {
        return h().subSet(e2, e3);
    }

    @Override // java.util.SortedSet
    public SortedSet<E> tailSet(E e2) {
        return h().tailSet(e2);
    }
}
