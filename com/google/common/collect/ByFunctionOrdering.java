package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* JADX INFO: Access modifiers changed from: package-private */
@GwtCompatible(serializable = true)
/* loaded from: classes2.dex */
public final class ByFunctionOrdering<F, T> extends Ordering<F> implements Serializable {
    private static final long serialVersionUID = 0;

    /* renamed from: a  reason: collision with root package name */
    final Function f8444a;

    /* renamed from: b  reason: collision with root package name */
    final Ordering f8445b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ByFunctionOrdering(Function function, Ordering ordering) {
        this.f8444a = (Function) Preconditions.checkNotNull(function);
        this.f8445b = (Ordering) Preconditions.checkNotNull(ordering);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.Ordering, java.util.Comparator
    public int compare(F f2, F f3) {
        return this.f8445b.compare(this.f8444a.apply(f2), this.f8444a.apply(f3));
    }

    @Override // java.util.Comparator
    public boolean equals(@NullableDecl Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ByFunctionOrdering) {
            ByFunctionOrdering byFunctionOrdering = (ByFunctionOrdering) obj;
            return this.f8444a.equals(byFunctionOrdering.f8444a) && this.f8445b.equals(byFunctionOrdering.f8445b);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.f8444a, this.f8445b);
    }

    public String toString() {
        return this.f8445b + ".onResultOf(" + this.f8444a + ")";
    }
}
