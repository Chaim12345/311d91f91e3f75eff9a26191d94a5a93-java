package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.Comparator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* JADX INFO: Access modifiers changed from: package-private */
@GwtCompatible(serializable = true)
/* loaded from: classes2.dex */
public final class ComparatorOrdering<T> extends Ordering<T> implements Serializable {
    private static final long serialVersionUID = 0;

    /* renamed from: a  reason: collision with root package name */
    final Comparator f8482a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ComparatorOrdering(Comparator comparator) {
        this.f8482a = (Comparator) Preconditions.checkNotNull(comparator);
    }

    @Override // com.google.common.collect.Ordering, java.util.Comparator
    public int compare(T t2, T t3) {
        return this.f8482a.compare(t2, t3);
    }

    @Override // java.util.Comparator
    public boolean equals(@NullableDecl Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ComparatorOrdering) {
            return this.f8482a.equals(((ComparatorOrdering) obj).f8482a);
        }
        return false;
    }

    public int hashCode() {
        return this.f8482a.hashCode();
    }

    public String toString() {
        return this.f8482a.toString();
    }
}
