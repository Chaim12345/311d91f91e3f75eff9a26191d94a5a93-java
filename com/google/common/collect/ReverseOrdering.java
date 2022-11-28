package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* JADX INFO: Access modifiers changed from: package-private */
@GwtCompatible(serializable = true)
/* loaded from: classes2.dex */
public final class ReverseOrdering<T> extends Ordering<T> implements Serializable {
    private static final long serialVersionUID = 0;

    /* renamed from: a  reason: collision with root package name */
    final Ordering f8949a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReverseOrdering(Ordering ordering) {
        this.f8949a = (Ordering) Preconditions.checkNotNull(ordering);
    }

    @Override // com.google.common.collect.Ordering, java.util.Comparator
    public int compare(T t2, T t3) {
        return this.f8949a.compare(t3, t2);
    }

    @Override // java.util.Comparator
    public boolean equals(@NullableDecl Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ReverseOrdering) {
            return this.f8949a.equals(((ReverseOrdering) obj).f8949a);
        }
        return false;
    }

    public int hashCode() {
        return -this.f8949a.hashCode();
    }

    @Override // com.google.common.collect.Ordering
    public <E extends T> E max(Iterable<E> iterable) {
        return (E) this.f8949a.min(iterable);
    }

    @Override // com.google.common.collect.Ordering
    public <E extends T> E max(E e2, E e3) {
        return (E) this.f8949a.min(e2, e3);
    }

    @Override // com.google.common.collect.Ordering
    public <E extends T> E max(E e2, E e3, E e4, E... eArr) {
        return (E) this.f8949a.min(e2, e3, e4, eArr);
    }

    @Override // com.google.common.collect.Ordering
    public <E extends T> E max(Iterator<E> it) {
        return (E) this.f8949a.min(it);
    }

    @Override // com.google.common.collect.Ordering
    public <E extends T> E min(Iterable<E> iterable) {
        return (E) this.f8949a.max(iterable);
    }

    @Override // com.google.common.collect.Ordering
    public <E extends T> E min(E e2, E e3) {
        return (E) this.f8949a.max(e2, e3);
    }

    @Override // com.google.common.collect.Ordering
    public <E extends T> E min(E e2, E e3, E e4, E... eArr) {
        return (E) this.f8949a.max(e2, e3, e4, eArr);
    }

    @Override // com.google.common.collect.Ordering
    public <E extends T> E min(Iterator<E> it) {
        return (E) this.f8949a.max(it);
    }

    @Override // com.google.common.collect.Ordering
    public <S extends T> Ordering<S> reverse() {
        return this.f8949a;
    }

    public String toString() {
        return this.f8949a + ".reverse()";
    }
}
