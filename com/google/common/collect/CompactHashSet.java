package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* JADX INFO: Access modifiers changed from: package-private */
@GwtIncompatible
/* loaded from: classes2.dex */
public class CompactHashSet<E> extends AbstractSet<E> implements Serializable {
    private static final int MAX_HASH_BUCKET_LENGTH = 9;
    @VisibleForTesting
    @NullableDecl

    /* renamed from: a  reason: collision with root package name */
    transient Object[] f8476a;
    @NullableDecl
    private transient int[] entries;
    private transient int metadata;
    private transient int size;
    @NullableDecl
    private transient Object table;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CompactHashSet() {
        i(3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CompactHashSet(int i2) {
        i(i2);
    }

    public static <E> CompactHashSet<E> create() {
        return new CompactHashSet<>();
    }

    public static <E> CompactHashSet<E> create(Collection<? extends E> collection) {
        CompactHashSet<E> createWithExpectedSize = createWithExpectedSize(collection.size());
        createWithExpectedSize.addAll(collection);
        return createWithExpectedSize;
    }

    @SafeVarargs
    public static <E> CompactHashSet<E> create(E... eArr) {
        CompactHashSet<E> createWithExpectedSize = createWithExpectedSize(eArr.length);
        Collections.addAll(createWithExpectedSize, eArr);
        return createWithExpectedSize;
    }

    private Set<E> createHashFloodingResistantDelegate(int i2) {
        return new LinkedHashSet(i2, 1.0f);
    }

    public static <E> CompactHashSet<E> createWithExpectedSize(int i2) {
        return new CompactHashSet<>(i2);
    }

    private int hashTableMask() {
        return (1 << (this.metadata & 31)) - 1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        int readInt = objectInputStream.readInt();
        if (readInt < 0) {
            throw new InvalidObjectException("Invalid size: " + readInt);
        }
        i(readInt);
        for (int i2 = 0; i2 < readInt; i2++) {
            add(objectInputStream.readObject());
        }
    }

    private void resizeMeMaybe(int i2) {
        int min;
        int length = this.entries.length;
        if (i2 <= length || (min = Math.min((int) LockFreeTaskQueueCore.MAX_CAPACITY_MASK, (Math.max(1, length >>> 1) + length) | 1)) == length) {
            return;
        }
        m(min);
    }

    @CanIgnoreReturnValue
    private int resizeTable(int i2, int i3, int i4, int i5) {
        Object a2 = CompactHashing.a(i3);
        int i6 = i3 - 1;
        if (i5 != 0) {
            CompactHashing.i(a2, i4 & i6, i5 + 1);
        }
        Object obj = this.table;
        int[] iArr = this.entries;
        for (int i7 = 0; i7 <= i2; i7++) {
            int h2 = CompactHashing.h(obj, i7);
            while (h2 != 0) {
                int i8 = h2 - 1;
                int i9 = iArr[i8];
                int b2 = CompactHashing.b(i9, i2) | i7;
                int i10 = b2 & i6;
                int h3 = CompactHashing.h(a2, i10);
                CompactHashing.i(a2, i10, h2);
                iArr[i8] = CompactHashing.d(b2, h3, i6);
                h2 = CompactHashing.c(i9, i2);
            }
        }
        this.table = a2;
        setHashTableMask(i6);
        return i6;
    }

    private void setHashTableMask(int i2) {
        this.metadata = CompactHashing.d(this.metadata, 32 - Integer.numberOfLeadingZeros(i2), 31);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(size());
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            objectOutputStream.writeObject(it.next());
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    @CanIgnoreReturnValue
    public boolean add(@NullableDecl E e2) {
        if (l()) {
            c();
        }
        Set e3 = e();
        if (e3 != null) {
            return e3.add(e2);
        }
        int[] iArr = this.entries;
        Object[] objArr = this.f8476a;
        int i2 = this.size;
        int i3 = i2 + 1;
        int d2 = Hashing.d(e2);
        int hashTableMask = hashTableMask();
        int i4 = d2 & hashTableMask;
        int h2 = CompactHashing.h(this.table, i4);
        if (h2 == 0) {
            if (i3 <= hashTableMask) {
                CompactHashing.i(this.table, i4, i3);
                resizeMeMaybe(i3);
                j(i2, e2, d2, hashTableMask);
                this.size = i3;
                h();
                return true;
            }
            hashTableMask = resizeTable(hashTableMask, CompactHashing.e(hashTableMask), d2, i2);
            resizeMeMaybe(i3);
            j(i2, e2, d2, hashTableMask);
            this.size = i3;
            h();
            return true;
        }
        int b2 = CompactHashing.b(d2, hashTableMask);
        int i5 = 0;
        while (true) {
            int i6 = h2 - 1;
            int i7 = iArr[i6];
            if (CompactHashing.b(i7, hashTableMask) == b2 && Objects.equal(e2, objArr[i6])) {
                return false;
            }
            int c2 = CompactHashing.c(i7, hashTableMask);
            i5++;
            if (c2 != 0) {
                h2 = c2;
            } else if (i5 >= 9) {
                return d().add(e2);
            } else {
                if (i3 <= hashTableMask) {
                    iArr[i6] = CompactHashing.d(i7, i3, hashTableMask);
                }
            }
        }
        resizeMeMaybe(i3);
        j(i2, e2, d2, hashTableMask);
        this.size = i3;
        h();
        return true;
    }

    int b(int i2, int i3) {
        return i2 - 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @CanIgnoreReturnValue
    public int c() {
        Preconditions.checkState(l(), "Arrays already allocated");
        int i2 = this.metadata;
        int j2 = CompactHashing.j(i2);
        this.table = CompactHashing.a(j2);
        setHashTableMask(j2 - 1);
        this.entries = new int[i2];
        this.f8476a = new Object[i2];
        return i2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public void clear() {
        if (l()) {
            return;
        }
        h();
        Set e2 = e();
        if (e2 != null) {
            this.metadata = Ints.constrainToRange(size(), 3, LockFreeTaskQueueCore.MAX_CAPACITY_MASK);
            e2.clear();
            this.table = null;
        } else {
            Arrays.fill(this.f8476a, 0, this.size, (Object) null);
            CompactHashing.g(this.table);
            Arrays.fill(this.entries, 0, this.size, 0);
        }
        this.size = 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(@NullableDecl Object obj) {
        if (l()) {
            return false;
        }
        Set e2 = e();
        if (e2 != null) {
            return e2.contains(obj);
        }
        int d2 = Hashing.d(obj);
        int hashTableMask = hashTableMask();
        int h2 = CompactHashing.h(this.table, d2 & hashTableMask);
        if (h2 == 0) {
            return false;
        }
        int b2 = CompactHashing.b(d2, hashTableMask);
        do {
            int i2 = h2 - 1;
            int i3 = this.entries[i2];
            if (CompactHashing.b(i3, hashTableMask) == b2 && Objects.equal(obj, this.f8476a[i2])) {
                return true;
            }
            h2 = CompactHashing.c(i3, hashTableMask);
        } while (h2 != 0);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    @CanIgnoreReturnValue
    public Set d() {
        Set<E> createHashFloodingResistantDelegate = createHashFloodingResistantDelegate(hashTableMask() + 1);
        int f2 = f();
        while (f2 >= 0) {
            createHashFloodingResistantDelegate.add((E) this.f8476a[f2]);
            f2 = g(f2);
        }
        this.table = createHashFloodingResistantDelegate;
        this.entries = null;
        this.f8476a = null;
        h();
        return createHashFloodingResistantDelegate;
    }

    @VisibleForTesting
    @NullableDecl
    Set e() {
        Object obj = this.table;
        if (obj instanceof Set) {
            return (Set) obj;
        }
        return null;
    }

    int f() {
        return isEmpty() ? -1 : 0;
    }

    int g(int i2) {
        int i3 = i2 + 1;
        if (i3 < this.size) {
            return i3;
        }
        return -1;
    }

    void h() {
        this.metadata += 32;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void i(int i2) {
        Preconditions.checkArgument(i2 >= 0, "Expected size must be >= 0");
        this.metadata = Ints.constrainToRange(i2, 1, LockFreeTaskQueueCore.MAX_CAPACITY_MASK);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<E> iterator() {
        Set e2 = e();
        return e2 != null ? e2.iterator() : new Iterator<E>() { // from class: com.google.common.collect.CompactHashSet.1

            /* renamed from: a  reason: collision with root package name */
            int f8477a;

            /* renamed from: b  reason: collision with root package name */
            int f8478b;

            /* renamed from: c  reason: collision with root package name */
            int f8479c = -1;

            {
                this.f8477a = CompactHashSet.this.metadata;
                this.f8478b = CompactHashSet.this.f();
            }

            private void checkForConcurrentModification() {
                if (CompactHashSet.this.metadata != this.f8477a) {
                    throw new ConcurrentModificationException();
                }
            }

            void a() {
                this.f8477a += 32;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.f8478b >= 0;
            }

            @Override // java.util.Iterator
            public E next() {
                checkForConcurrentModification();
                if (hasNext()) {
                    int i2 = this.f8478b;
                    this.f8479c = i2;
                    CompactHashSet compactHashSet = CompactHashSet.this;
                    E e3 = (E) compactHashSet.f8476a[i2];
                    this.f8478b = compactHashSet.g(i2);
                    return e3;
                }
                throw new NoSuchElementException();
            }

            @Override // java.util.Iterator
            public void remove() {
                checkForConcurrentModification();
                CollectPreconditions.e(this.f8479c >= 0);
                a();
                CompactHashSet compactHashSet = CompactHashSet.this;
                compactHashSet.remove(compactHashSet.f8476a[this.f8479c]);
                this.f8478b = CompactHashSet.this.b(this.f8478b, this.f8479c);
                this.f8479c = -1;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void j(int i2, @NullableDecl Object obj, int i3, int i4) {
        this.entries[i2] = CompactHashing.d(i3, 0, i4);
        this.f8476a[i2] = obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void k(int i2, int i3) {
        int size = size() - 1;
        if (i2 >= size) {
            this.f8476a[i2] = null;
            this.entries[i2] = 0;
            return;
        }
        Object[] objArr = this.f8476a;
        Object obj = objArr[size];
        objArr[i2] = obj;
        objArr[size] = null;
        int[] iArr = this.entries;
        iArr[i2] = iArr[size];
        iArr[size] = 0;
        int d2 = Hashing.d(obj) & i3;
        int h2 = CompactHashing.h(this.table, d2);
        int i4 = size + 1;
        if (h2 == i4) {
            CompactHashing.i(this.table, d2, i2 + 1);
            return;
        }
        while (true) {
            int i5 = h2 - 1;
            int i6 = this.entries[i5];
            int c2 = CompactHashing.c(i6, i3);
            if (c2 == i4) {
                this.entries[i5] = CompactHashing.d(i6, i2 + 1, i3);
                return;
            }
            h2 = c2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public boolean l() {
        return this.table == null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void m(int i2) {
        this.entries = Arrays.copyOf(this.entries, i2);
        this.f8476a = Arrays.copyOf(this.f8476a, i2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    @CanIgnoreReturnValue
    public boolean remove(@NullableDecl Object obj) {
        if (l()) {
            return false;
        }
        Set e2 = e();
        if (e2 != null) {
            return e2.remove(obj);
        }
        int hashTableMask = hashTableMask();
        int f2 = CompactHashing.f(obj, null, hashTableMask, this.table, this.entries, this.f8476a, null);
        if (f2 == -1) {
            return false;
        }
        k(f2, hashTableMask);
        this.size--;
        h();
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        Set e2 = e();
        return e2 != null ? e2.size() : this.size;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public Object[] toArray() {
        if (l()) {
            return new Object[0];
        }
        Set e2 = e();
        return e2 != null ? e2.toArray() : Arrays.copyOf(this.f8476a, this.size);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    @CanIgnoreReturnValue
    public <T> T[] toArray(T[] tArr) {
        if (!l()) {
            Set e2 = e();
            return e2 != null ? (T[]) e2.toArray(tArr) : (T[]) ObjectArrays.f(this.f8476a, 0, this.size, tArr);
        }
        if (tArr.length > 0) {
            tArr[0] = null;
        }
        return tArr;
    }

    public void trimToSize() {
        if (l()) {
            return;
        }
        Set e2 = e();
        if (e2 != null) {
            Set<E> createHashFloodingResistantDelegate = createHashFloodingResistantDelegate(size());
            createHashFloodingResistantDelegate.addAll(e2);
            this.table = createHashFloodingResistantDelegate;
            return;
        }
        int i2 = this.size;
        if (i2 < this.entries.length) {
            m(i2);
        }
        int j2 = CompactHashing.j(i2);
        int hashTableMask = hashTableMask();
        if (j2 < hashTableMask) {
            resizeTable(hashTableMask, j2, 0, 0);
        }
    }
}
