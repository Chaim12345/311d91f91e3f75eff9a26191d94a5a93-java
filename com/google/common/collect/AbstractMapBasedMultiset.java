package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* JADX INFO: Access modifiers changed from: package-private */
@GwtCompatible(emulated = true)
/* loaded from: classes2.dex */
public abstract class AbstractMapBasedMultiset<E> extends AbstractMultiset<E> implements Serializable {
    @GwtIncompatible
    private static final long serialVersionUID = 0;

    /* renamed from: a  reason: collision with root package name */
    transient ObjectCountHashMap f8410a;

    /* renamed from: b  reason: collision with root package name */
    transient long f8411b;

    /* loaded from: classes2.dex */
    abstract class Itr<T> implements Iterator<T> {

        /* renamed from: a  reason: collision with root package name */
        int f8414a;

        /* renamed from: b  reason: collision with root package name */
        int f8415b = -1;

        /* renamed from: c  reason: collision with root package name */
        int f8416c;

        Itr() {
            this.f8414a = AbstractMapBasedMultiset.this.f8410a.b();
            this.f8416c = AbstractMapBasedMultiset.this.f8410a.f8906d;
        }

        private void checkForConcurrentModification() {
            if (AbstractMapBasedMultiset.this.f8410a.f8906d != this.f8416c) {
                throw new ConcurrentModificationException();
            }
        }

        abstract Object a(int i2);

        @Override // java.util.Iterator
        public boolean hasNext() {
            checkForConcurrentModification();
            return this.f8414a >= 0;
        }

        @Override // java.util.Iterator
        public T next() {
            if (hasNext()) {
                T t2 = (T) a(this.f8414a);
                int i2 = this.f8414a;
                this.f8415b = i2;
                this.f8414a = AbstractMapBasedMultiset.this.f8410a.j(i2);
                return t2;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            AbstractMapBasedMultiset abstractMapBasedMultiset;
            checkForConcurrentModification();
            CollectPreconditions.e(this.f8415b != -1);
            AbstractMapBasedMultiset.this.f8411b -= abstractMapBasedMultiset.f8410a.l(this.f8415b);
            this.f8414a = AbstractMapBasedMultiset.this.f8410a.k(this.f8414a, this.f8415b);
            this.f8415b = -1;
            this.f8416c = AbstractMapBasedMultiset.this.f8410a.f8906d;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractMapBasedMultiset(int i2) {
        f(i2);
    }

    @GwtIncompatible
    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        int h2 = Serialization.h(objectInputStream);
        f(3);
        Serialization.g(this, objectInputStream, h2);
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.defaultWriteObject();
        Serialization.k(this, objectOutputStream);
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public final int add(@NullableDecl E e2, int i2) {
        if (i2 == 0) {
            return count(e2);
        }
        Preconditions.checkArgument(i2 > 0, "occurrences cannot be negative: %s", i2);
        int f2 = this.f8410a.f(e2);
        if (f2 == -1) {
            this.f8410a.put(e2, i2);
            this.f8411b += i2;
            return 0;
        }
        int e3 = this.f8410a.e(f2);
        long j2 = i2;
        long j3 = e3 + j2;
        Preconditions.checkArgument(j3 <= 2147483647L, "too many occurrences: %s", j3);
        this.f8410a.n(f2, (int) j3);
        this.f8411b += j2;
        return e3;
    }

    @Override // com.google.common.collect.AbstractMultiset
    final int b() {
        return this.f8410a.o();
    }

    @Override // com.google.common.collect.AbstractMultiset
    final Iterator c() {
        return new AbstractMapBasedMultiset<E>.Itr<E>() { // from class: com.google.common.collect.AbstractMapBasedMultiset.1
            @Override // com.google.common.collect.AbstractMapBasedMultiset.Itr
            Object a(int i2) {
                return AbstractMapBasedMultiset.this.f8410a.d(i2);
            }
        };
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection
    public final void clear() {
        this.f8410a.clear();
        this.f8411b = 0L;
    }

    @Override // com.google.common.collect.Multiset
    public final int count(@NullableDecl Object obj) {
        return this.f8410a.get(obj);
    }

    @Override // com.google.common.collect.AbstractMultiset
    final Iterator d() {
        return new AbstractMapBasedMultiset<E>.Itr<Multiset.Entry<E>>() { // from class: com.google.common.collect.AbstractMapBasedMultiset.2
            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.google.common.collect.AbstractMapBasedMultiset.Itr
            /* renamed from: b */
            public Multiset.Entry a(int i2) {
                return AbstractMapBasedMultiset.this.f8410a.c(i2);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public void e(Multiset multiset) {
        Preconditions.checkNotNull(multiset);
        int b2 = this.f8410a.b();
        while (b2 >= 0) {
            multiset.add(this.f8410a.d(b2), this.f8410a.e(b2));
            b2 = this.f8410a.j(b2);
        }
    }

    abstract void f(int i2);

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.google.common.collect.Multiset
    public final Iterator<E> iterator() {
        return Multisets.f(this);
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public final int remove(@NullableDecl Object obj, int i2) {
        if (i2 == 0) {
            return count(obj);
        }
        Preconditions.checkArgument(i2 > 0, "occurrences cannot be negative: %s", i2);
        int f2 = this.f8410a.f(obj);
        if (f2 == -1) {
            return 0;
        }
        int e2 = this.f8410a.e(f2);
        if (e2 > i2) {
            this.f8410a.n(f2, e2 - i2);
        } else {
            this.f8410a.l(f2);
            i2 = e2;
        }
        this.f8411b -= i2;
        return e2;
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public final int setCount(@NullableDecl E e2, int i2) {
        CollectPreconditions.b(i2, "count");
        ObjectCountHashMap objectCountHashMap = this.f8410a;
        int remove = i2 == 0 ? objectCountHashMap.remove(e2) : objectCountHashMap.put(e2, i2);
        this.f8411b += i2 - remove;
        return remove;
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    public final boolean setCount(@NullableDecl E e2, int i2, int i3) {
        long j2;
        CollectPreconditions.b(i2, "oldCount");
        CollectPreconditions.b(i3, "newCount");
        int f2 = this.f8410a.f(e2);
        if (f2 == -1) {
            if (i2 != 0) {
                return false;
            }
            if (i3 > 0) {
                this.f8410a.put(e2, i3);
                this.f8411b += i3;
            }
            return true;
        } else if (this.f8410a.e(f2) != i2) {
            return false;
        } else {
            ObjectCountHashMap objectCountHashMap = this.f8410a;
            if (i3 == 0) {
                objectCountHashMap.l(f2);
                j2 = this.f8411b - i2;
            } else {
                objectCountHashMap.n(f2, i3);
                j2 = this.f8411b + (i3 - i2);
            }
            this.f8411b = j2;
            return true;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    public final int size() {
        return Ints.saturatedCast(this.f8411b);
    }
}
