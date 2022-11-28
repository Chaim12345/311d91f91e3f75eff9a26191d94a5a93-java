package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Deque;
import java.util.Iterator;
@GwtIncompatible
/* loaded from: classes2.dex */
public abstract class ForwardingDeque<E> extends ForwardingQueue<E> implements Deque<E> {
    @Override // java.util.Deque
    public void addFirst(E e2) {
        h().addFirst(e2);
    }

    @Override // java.util.Deque
    public void addLast(E e2) {
        h().addLast(e2);
    }

    @Override // java.util.Deque
    public Iterator<E> descendingIterator() {
        return h().descendingIterator();
    }

    @Override // java.util.Deque
    public E getFirst() {
        return (E) h().getFirst();
    }

    @Override // java.util.Deque
    public E getLast() {
        return (E) h().getLast();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingQueue
    /* renamed from: i */
    public abstract Deque h();

    @Override // java.util.Deque
    @CanIgnoreReturnValue
    public boolean offerFirst(E e2) {
        return h().offerFirst(e2);
    }

    @Override // java.util.Deque
    @CanIgnoreReturnValue
    public boolean offerLast(E e2) {
        return h().offerLast(e2);
    }

    @Override // java.util.Deque
    public E peekFirst() {
        return (E) h().peekFirst();
    }

    @Override // java.util.Deque
    public E peekLast() {
        return (E) h().peekLast();
    }

    @Override // java.util.Deque
    @CanIgnoreReturnValue
    public E pollFirst() {
        return (E) h().pollFirst();
    }

    @Override // java.util.Deque
    @CanIgnoreReturnValue
    public E pollLast() {
        return (E) h().pollLast();
    }

    @Override // java.util.Deque
    @CanIgnoreReturnValue
    public E pop() {
        return (E) h().pop();
    }

    @Override // java.util.Deque
    public void push(E e2) {
        h().push(e2);
    }

    @Override // java.util.Deque
    @CanIgnoreReturnValue
    public E removeFirst() {
        return (E) h().removeFirst();
    }

    @Override // java.util.Deque
    @CanIgnoreReturnValue
    public boolean removeFirstOccurrence(Object obj) {
        return h().removeFirstOccurrence(obj);
    }

    @Override // java.util.Deque
    @CanIgnoreReturnValue
    public E removeLast() {
        return (E) h().removeLast();
    }

    @Override // java.util.Deque
    @CanIgnoreReturnValue
    public boolean removeLastOccurrence(Object obj) {
        return h().removeLastOccurrence(obj);
    }
}
