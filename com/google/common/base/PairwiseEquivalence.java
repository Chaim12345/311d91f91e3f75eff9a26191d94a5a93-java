package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible(serializable = true)
/* loaded from: classes2.dex */
final class PairwiseEquivalence<T> extends Equivalence<Iterable<T>> implements Serializable {
    private static final long serialVersionUID = 1;

    /* renamed from: a  reason: collision with root package name */
    final Equivalence f8160a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PairwiseEquivalence(Equivalence equivalence) {
        this.f8160a = (Equivalence) Preconditions.checkNotNull(equivalence);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.base.Equivalence
    /* renamed from: c */
    public boolean a(Iterable iterable, Iterable iterable2) {
        Iterator<T> it = iterable.iterator();
        Iterator<T> it2 = iterable2.iterator();
        while (it.hasNext() && it2.hasNext()) {
            if (!this.f8160a.equivalent(it.next(), it2.next())) {
                return false;
            }
        }
        return (it.hasNext() || it2.hasNext()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.base.Equivalence
    /* renamed from: d */
    public int b(Iterable iterable) {
        Iterator<T> it = iterable.iterator();
        int i2 = 78721;
        while (it.hasNext()) {
            i2 = (i2 * 24943) + this.f8160a.hash(it.next());
        }
        return i2;
    }

    public boolean equals(@NullableDecl Object obj) {
        if (obj instanceof PairwiseEquivalence) {
            return this.f8160a.equals(((PairwiseEquivalence) obj).f8160a);
        }
        return false;
    }

    public int hashCode() {
        return this.f8160a.hashCode() ^ 1185147655;
    }

    public String toString() {
        return this.f8160a + ".pairwise()";
    }
}
