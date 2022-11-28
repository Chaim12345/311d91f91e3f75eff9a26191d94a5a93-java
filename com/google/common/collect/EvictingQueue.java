package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Queue;
@Beta
@GwtCompatible
/* loaded from: classes2.dex */
public final class EvictingQueue<E> extends ForwardingQueue<E> implements Serializable {
    private static final long serialVersionUID = 0;
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    final int f8510a;
    private final Queue<E> delegate;

    private EvictingQueue(int i2) {
        Preconditions.checkArgument(i2 >= 0, "maxSize (%s) must >= 0", i2);
        this.delegate = new ArrayDeque(i2);
        this.f8510a = i2;
    }

    public static <E> EvictingQueue<E> create(int i2) {
        return new EvictingQueue<>(i2);
    }

    @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Queue
    @CanIgnoreReturnValue
    public boolean add(E e2) {
        Preconditions.checkNotNull(e2);
        if (this.f8510a == 0) {
            return true;
        }
        if (size() == this.f8510a) {
            this.delegate.remove();
        }
        this.delegate.add(e2);
        return true;
    }

    @Override // com.google.common.collect.ForwardingCollection, java.util.Collection
    @CanIgnoreReturnValue
    public boolean addAll(Collection<? extends E> collection) {
        int size = collection.size();
        if (size >= this.f8510a) {
            clear();
            return Iterables.addAll(this, Iterables.skip(collection, size - this.f8510a));
        }
        return b(collection);
    }

    @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        return delegate().contains(Preconditions.checkNotNull(obj));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingQueue, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
    /* renamed from: h */
    public Queue delegate() {
        return this.delegate;
    }

    @Override // com.google.common.collect.ForwardingQueue, java.util.Queue
    @CanIgnoreReturnValue
    public boolean offer(E e2) {
        return add(e2);
    }

    public int remainingCapacity() {
        return this.f8510a - size();
    }

    @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
    @CanIgnoreReturnValue
    public boolean remove(Object obj) {
        return delegate().remove(Preconditions.checkNotNull(obj));
    }
}
