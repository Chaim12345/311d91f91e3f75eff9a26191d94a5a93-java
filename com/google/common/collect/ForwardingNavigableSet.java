package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.SortedSet;
@GwtIncompatible
/* loaded from: classes2.dex */
public abstract class ForwardingNavigableSet<E> extends ForwardingSortedSet<E> implements NavigableSet<E> {

    @Beta
    /* loaded from: classes2.dex */
    protected class StandardDescendingSet extends Sets.DescendingSet<E> {
        public StandardDescendingSet(ForwardingNavigableSet forwardingNavigableSet) {
            super(forwardingNavigableSet);
        }
    }

    @Override // java.util.NavigableSet
    public E ceiling(E e2) {
        return (E) j().ceiling(e2);
    }

    @Override // java.util.NavigableSet
    public Iterator<E> descendingIterator() {
        return j().descendingIterator();
    }

    @Override // java.util.NavigableSet
    public NavigableSet<E> descendingSet() {
        return j().descendingSet();
    }

    @Override // java.util.NavigableSet
    public E floor(E e2) {
        return (E) j().floor(e2);
    }

    @Override // java.util.NavigableSet
    public NavigableSet<E> headSet(E e2, boolean z) {
        return j().headSet(e2, z);
    }

    @Override // java.util.NavigableSet
    public E higher(E e2) {
        return (E) j().higher(e2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingSortedSet
    /* renamed from: k */
    public abstract NavigableSet j();

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    public SortedSet l(Object obj) {
        return headSet(obj, false);
    }

    @Override // java.util.NavigableSet
    public E lower(E e2) {
        return (E) j().lower(e2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    public SortedSet m(Object obj, Object obj2) {
        return subSet(obj, true, obj2, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    public SortedSet n(Object obj) {
        return tailSet(obj, true);
    }

    @Override // java.util.NavigableSet
    public E pollFirst() {
        return (E) j().pollFirst();
    }

    @Override // java.util.NavigableSet
    public E pollLast() {
        return (E) j().pollLast();
    }

    @Override // java.util.NavigableSet
    public NavigableSet<E> subSet(E e2, boolean z, E e3, boolean z2) {
        return j().subSet(e2, z, e3, z2);
    }

    @Override // java.util.NavigableSet
    public NavigableSet<E> tailSet(E e2, boolean z) {
        return j().tailSet(e2, z);
    }
}
