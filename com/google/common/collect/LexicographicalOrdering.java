package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible(serializable = true)
/* loaded from: classes2.dex */
final class LexicographicalOrdering<T> extends Ordering<Iterable<T>> implements Serializable {
    private static final long serialVersionUID = 0;

    /* renamed from: a  reason: collision with root package name */
    final Comparator f8668a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LexicographicalOrdering(Comparator comparator) {
        this.f8668a = comparator;
    }

    public int compare(Iterable<T> iterable, Iterable<T> iterable2) {
        Iterator<T> it = iterable2.iterator();
        for (T t2 : iterable) {
            if (!it.hasNext()) {
                return 1;
            }
            int compare = this.f8668a.compare(t2, it.next());
            if (compare != 0) {
                return compare;
            }
        }
        return it.hasNext() ? -1 : 0;
    }

    @Override // com.google.common.collect.Ordering, java.util.Comparator
    public /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
        return compare((Iterable) ((Iterable) obj), (Iterable) ((Iterable) obj2));
    }

    @Override // java.util.Comparator
    public boolean equals(@NullableDecl Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof LexicographicalOrdering) {
            return this.f8668a.equals(((LexicographicalOrdering) obj).f8668a);
        }
        return false;
    }

    public int hashCode() {
        return this.f8668a.hashCode() ^ 2075626741;
    }

    public String toString() {
        return this.f8668a + ".lexicographical()";
    }
}
