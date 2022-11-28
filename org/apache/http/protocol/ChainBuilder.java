package org.apache.http.protocol;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
/* loaded from: classes3.dex */
final class ChainBuilder<E> {
    private final LinkedList<E> list = new LinkedList<>();
    private final Map<Class<?>, E> uniqueClasses = new HashMap();

    private void ensureUnique(E e2) {
        E remove = this.uniqueClasses.remove(e2.getClass());
        if (remove != null) {
            this.list.remove(remove);
        }
        this.uniqueClasses.put(e2.getClass(), e2);
    }

    public ChainBuilder<E> addAllFirst(Collection<E> collection) {
        if (collection == null) {
            return this;
        }
        for (E e2 : collection) {
            addFirst(e2);
        }
        return this;
    }

    public ChainBuilder<E> addAllFirst(E... eArr) {
        if (eArr == null) {
            return this;
        }
        for (E e2 : eArr) {
            addFirst(e2);
        }
        return this;
    }

    public ChainBuilder<E> addAllLast(Collection<E> collection) {
        if (collection == null) {
            return this;
        }
        for (E e2 : collection) {
            addLast(e2);
        }
        return this;
    }

    public ChainBuilder<E> addAllLast(E... eArr) {
        if (eArr == null) {
            return this;
        }
        for (E e2 : eArr) {
            addLast(e2);
        }
        return this;
    }

    public ChainBuilder<E> addFirst(E e2) {
        if (e2 == null) {
            return this;
        }
        ensureUnique(e2);
        this.list.addFirst(e2);
        return this;
    }

    public ChainBuilder<E> addLast(E e2) {
        if (e2 == null) {
            return this;
        }
        ensureUnique(e2);
        this.list.addLast(e2);
        return this;
    }

    public LinkedList<E> build() {
        return new LinkedList<>(this.list);
    }
}
