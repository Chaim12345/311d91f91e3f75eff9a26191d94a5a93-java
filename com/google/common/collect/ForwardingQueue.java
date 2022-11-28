package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Queue;
@GwtCompatible
/* loaded from: classes2.dex */
public abstract class ForwardingQueue<E> extends ForwardingCollection<E> implements Queue<E> {
    @Override // java.util.Queue
    public E element() {
        return (E) delegate().element();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
    /* renamed from: h */
    public abstract Queue delegate();

    @CanIgnoreReturnValue
    public boolean offer(E e2) {
        return delegate().offer(e2);
    }

    @Override // java.util.Queue
    public E peek() {
        return (E) delegate().peek();
    }

    @Override // java.util.Queue
    @CanIgnoreReturnValue
    public E poll() {
        return (E) delegate().poll();
    }

    @Override // java.util.Queue
    @CanIgnoreReturnValue
    public E remove() {
        return (E) delegate().remove();
    }
}
