package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.Iterator;
@GwtCompatible
/* loaded from: classes2.dex */
abstract class TransformedIterator<F, T> implements Iterator<T> {

    /* renamed from: a  reason: collision with root package name */
    final Iterator f9055a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TransformedIterator(Iterator it) {
        this.f9055a = (Iterator) Preconditions.checkNotNull(it);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Object a(Object obj);

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f9055a.hasNext();
    }

    @Override // java.util.Iterator
    public final T next() {
        return (T) a(this.f9055a.next());
    }

    @Override // java.util.Iterator
    public final void remove() {
        this.f9055a.remove();
    }
}
