package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
@GwtCompatible(serializable = true)
/* loaded from: classes2.dex */
final class CompoundOrdering<T> extends Ordering<T> implements Serializable {
    private static final long serialVersionUID = 0;

    /* renamed from: a  reason: collision with root package name */
    final Comparator[] f8484a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CompoundOrdering(Iterable iterable) {
        this.f8484a = (Comparator[]) Iterables.c(iterable, new Comparator[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CompoundOrdering(Comparator comparator, Comparator comparator2) {
        this.f8484a = new Comparator[]{comparator, comparator2};
    }

    @Override // com.google.common.collect.Ordering, java.util.Comparator
    public int compare(T t2, T t3) {
        int i2 = 0;
        while (true) {
            Comparator[] comparatorArr = this.f8484a;
            if (i2 >= comparatorArr.length) {
                return 0;
            }
            int compare = comparatorArr[i2].compare(t2, t3);
            if (compare != 0) {
                return compare;
            }
            i2++;
        }
    }

    @Override // java.util.Comparator
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CompoundOrdering) {
            return Arrays.equals(this.f8484a, ((CompoundOrdering) obj).f8484a);
        }
        return false;
    }

    public int hashCode() {
        return Arrays.hashCode(this.f8484a);
    }

    public String toString() {
        return "Ordering.compound(" + Arrays.toString(this.f8484a) + ")";
    }
}
