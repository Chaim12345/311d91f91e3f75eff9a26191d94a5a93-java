package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Ordering;
import java.io.Serializable;
import java.util.List;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* JADX INFO: Access modifiers changed from: package-private */
@GwtCompatible(serializable = true)
/* loaded from: classes2.dex */
public final class ExplicitOrdering<T> extends Ordering<T> implements Serializable {
    private static final long serialVersionUID = 0;

    /* renamed from: a  reason: collision with root package name */
    final ImmutableMap f8511a;

    ExplicitOrdering(ImmutableMap immutableMap) {
        this.f8511a = immutableMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ExplicitOrdering(List list) {
        this(Maps.n(list));
    }

    private int rank(T t2) {
        Integer num = (Integer) this.f8511a.get(t2);
        if (num != null) {
            return num.intValue();
        }
        throw new Ordering.IncomparableValueException(t2);
    }

    @Override // com.google.common.collect.Ordering, java.util.Comparator
    public int compare(T t2, T t3) {
        return rank(t2) - rank(t3);
    }

    @Override // java.util.Comparator
    public boolean equals(@NullableDecl Object obj) {
        if (obj instanceof ExplicitOrdering) {
            return this.f8511a.equals(((ExplicitOrdering) obj).f8511a);
        }
        return false;
    }

    public int hashCode() {
        return this.f8511a.hashCode();
    }

    public String toString() {
        return "Ordering.explicit(" + this.f8511a.keySet() + ")";
    }
}
