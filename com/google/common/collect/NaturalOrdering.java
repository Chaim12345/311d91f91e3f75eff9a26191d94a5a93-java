package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* JADX INFO: Access modifiers changed from: package-private */
@GwtCompatible(serializable = true)
/* loaded from: classes2.dex */
public final class NaturalOrdering extends Ordering<Comparable> implements Serializable {

    /* renamed from: a  reason: collision with root package name */
    static final NaturalOrdering f8900a = new NaturalOrdering();
    private static final long serialVersionUID = 0;
    @NullableDecl
    private transient Ordering<Comparable> nullsFirst;
    @NullableDecl
    private transient Ordering<Comparable> nullsLast;

    private NaturalOrdering() {
    }

    private Object readResolve() {
        return f8900a;
    }

    @Override // com.google.common.collect.Ordering, java.util.Comparator
    public int compare(Comparable comparable, Comparable comparable2) {
        Preconditions.checkNotNull(comparable);
        Preconditions.checkNotNull(comparable2);
        return comparable.compareTo(comparable2);
    }

    @Override // com.google.common.collect.Ordering
    public <S extends Comparable> Ordering<S> nullsFirst() {
        Ordering<S> ordering = (Ordering<S>) this.nullsFirst;
        if (ordering == null) {
            Ordering<S> nullsFirst = super.nullsFirst();
            this.nullsFirst = nullsFirst;
            return nullsFirst;
        }
        return ordering;
    }

    @Override // com.google.common.collect.Ordering
    public <S extends Comparable> Ordering<S> nullsLast() {
        Ordering<S> ordering = (Ordering<S>) this.nullsLast;
        if (ordering == null) {
            Ordering<S> nullsLast = super.nullsLast();
            this.nullsLast = nullsLast;
            return nullsLast;
        }
        return ordering;
    }

    @Override // com.google.common.collect.Ordering
    public <S extends Comparable> Ordering<S> reverse() {
        return ReverseNaturalOrdering.f8948a;
    }

    public String toString() {
        return "Ordering.natural()";
    }
}
