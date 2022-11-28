package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.NoSuchElementException;
@GwtCompatible
/* loaded from: classes2.dex */
abstract class AbstractIndexedListIterator<E> extends UnmodifiableListIterator<E> {
    private int position;
    private final int size;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractIndexedListIterator(int i2) {
        this(i2, 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractIndexedListIterator(int i2, int i3) {
        Preconditions.checkPositionIndex(i3, i2);
        this.size = i2;
        this.position = i3;
    }

    protected abstract Object get(int i2);

    @Override // java.util.Iterator, java.util.ListIterator
    public final boolean hasNext() {
        return this.position < this.size;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.position > 0;
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final E next() {
        if (hasNext()) {
            int i2 = this.position;
            this.position = i2 + 1;
            return (E) get(i2);
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.position;
    }

    @Override // java.util.ListIterator
    public final E previous() {
        if (hasPrevious()) {
            int i2 = this.position - 1;
            this.position = i2;
            return (E) get(i2);
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.position - 1;
    }
}
