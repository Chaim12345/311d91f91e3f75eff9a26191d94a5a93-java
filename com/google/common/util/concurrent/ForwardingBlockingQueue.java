package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.ForwardingQueue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
@CanIgnoreReturnValue
@GwtIncompatible
/* loaded from: classes2.dex */
public abstract class ForwardingBlockingQueue<E> extends ForwardingQueue<E> implements BlockingQueue<E> {
    @Override // java.util.concurrent.BlockingQueue
    public int drainTo(Collection<? super E> collection) {
        return h().drainTo(collection);
    }

    @Override // java.util.concurrent.BlockingQueue
    public int drainTo(Collection<? super E> collection, int i2) {
        return h().drainTo(collection, i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingQueue
    /* renamed from: i */
    public abstract BlockingQueue h();

    @Override // java.util.concurrent.BlockingQueue
    public boolean offer(E e2, long j2, TimeUnit timeUnit) {
        return h().offer(e2, j2, timeUnit);
    }

    @Override // java.util.concurrent.BlockingQueue
    public E poll(long j2, TimeUnit timeUnit) {
        return (E) h().poll(j2, timeUnit);
    }

    @Override // java.util.concurrent.BlockingQueue
    public void put(E e2) {
        h().put(e2);
    }

    @Override // java.util.concurrent.BlockingQueue
    public int remainingCapacity() {
        return h().remainingCapacity();
    }

    @Override // java.util.concurrent.BlockingQueue
    public E take() {
        return (E) h().take();
    }
}
