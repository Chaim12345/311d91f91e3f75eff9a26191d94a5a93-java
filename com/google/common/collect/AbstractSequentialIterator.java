package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.NoSuchElementException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible
/* loaded from: classes2.dex */
public abstract class AbstractSequentialIterator<T> extends UnmodifiableIterator<T> {
    @NullableDecl
    private T nextOrNull;

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    public AbstractSequentialIterator(@NullableDecl Object obj) {
        this.nextOrNull = obj;
    }

    @NullableDecl
    protected abstract Object a(Object obj);

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.nextOrNull != null;
    }

    @Override // java.util.Iterator
    public final T next() {
        if (hasNext()) {
            try {
                T t2 = this.nextOrNull;
                this.nextOrNull = (T) a(t2);
                return t2;
            } catch (Throwable th) {
                this.nextOrNull = (T) a(this.nextOrNull);
                throw th;
            }
        }
        throw new NoSuchElementException();
    }
}
