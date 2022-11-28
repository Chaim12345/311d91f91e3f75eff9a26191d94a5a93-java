package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.RetainedWith;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible
/* loaded from: classes2.dex */
public final class HashBiMap<K, V> extends AbstractMap<K, V> implements BiMap<K, V>, Serializable {
    private static final int ABSENT = -1;
    private static final int ENDPOINT = -2;

    /* renamed from: a  reason: collision with root package name */
    transient Object[] f8539a;

    /* renamed from: b  reason: collision with root package name */
    transient Object[] f8540b;

    /* renamed from: c  reason: collision with root package name */
    transient int f8541c;

    /* renamed from: d  reason: collision with root package name */
    transient int f8542d;
    private transient Set<Map.Entry<K, V>> entrySet;
    @NullableDecl
    private transient int firstInInsertionOrder;
    private transient int[] hashTableKToV;
    private transient int[] hashTableVToK;
    @RetainedWith
    @NullableDecl
    @LazyInit
    private transient BiMap<V, K> inverse;
    private transient Set<K> keySet;
    @NullableDecl
    private transient int lastInInsertionOrder;
    private transient int[] nextInBucketKToV;
    private transient int[] nextInBucketVToK;
    private transient int[] nextInInsertionOrder;
    private transient int[] prevInInsertionOrder;
    private transient Set<V> valueSet;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public final class EntryForKey extends AbstractMapEntry<K, V> {
        @NullableDecl

        /* renamed from: a  reason: collision with root package name */
        final Object f8543a;

        /* renamed from: b  reason: collision with root package name */
        int f8544b;

        EntryForKey(int i2) {
            this.f8543a = HashBiMap.this.f8539a[i2];
            this.f8544b = i2;
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public K getKey() {
            return (K) this.f8543a;
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        @NullableDecl
        public V getValue() {
            updateIndex();
            int i2 = this.f8544b;
            if (i2 == -1) {
                return null;
            }
            return (V) HashBiMap.this.f8540b[i2];
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public V setValue(V v) {
            updateIndex();
            int i2 = this.f8544b;
            if (i2 == -1) {
                return (V) HashBiMap.this.put(this.f8543a, v);
            }
            V v2 = (V) HashBiMap.this.f8540b[i2];
            if (Objects.equal(v2, v)) {
                return v;
            }
            HashBiMap.this.replaceValueInEntry(this.f8544b, v, false);
            return v2;
        }

        void updateIndex() {
            int i2 = this.f8544b;
            if (i2 != -1) {
                HashBiMap hashBiMap = HashBiMap.this;
                if (i2 <= hashBiMap.f8541c && Objects.equal(hashBiMap.f8539a[i2], this.f8543a)) {
                    return;
                }
            }
            this.f8544b = HashBiMap.this.g(this.f8543a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class EntryForValue<K, V> extends AbstractMapEntry<V, K> {

        /* renamed from: a  reason: collision with root package name */
        final HashBiMap f8546a;

        /* renamed from: b  reason: collision with root package name */
        final Object f8547b;

        /* renamed from: c  reason: collision with root package name */
        int f8548c;

        EntryForValue(HashBiMap hashBiMap, int i2) {
            this.f8546a = hashBiMap;
            this.f8547b = hashBiMap.f8540b[i2];
            this.f8548c = i2;
        }

        private void updateIndex() {
            int i2 = this.f8548c;
            if (i2 != -1) {
                HashBiMap hashBiMap = this.f8546a;
                if (i2 <= hashBiMap.f8541c && Objects.equal(this.f8547b, hashBiMap.f8540b[i2])) {
                    return;
                }
            }
            this.f8548c = this.f8546a.i(this.f8547b);
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public V getKey() {
            return (V) this.f8547b;
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public K getValue() {
            updateIndex();
            int i2 = this.f8548c;
            if (i2 == -1) {
                return null;
            }
            return (K) this.f8546a.f8539a[i2];
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public K setValue(K k2) {
            updateIndex();
            int i2 = this.f8548c;
            if (i2 == -1) {
                return (K) this.f8546a.n(this.f8547b, k2, false);
            }
            K k3 = (K) this.f8546a.f8539a[i2];
            if (Objects.equal(k3, k2)) {
                return k2;
            }
            this.f8546a.replaceKeyInEntry(this.f8548c, k2, false);
            return k3;
        }
    }

    /* loaded from: classes2.dex */
    final class EntrySet extends View<K, V, Map.Entry<K, V>> {
        EntrySet() {
            super(HashBiMap.this);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.HashBiMap.View
        /* renamed from: b */
        public Map.Entry a(int i2) {
            return new EntryForKey(i2);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(@NullableDecl Object obj) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                Object key = entry.getKey();
                Object value = entry.getValue();
                int g2 = HashBiMap.this.g(key);
                return g2 != -1 && Objects.equal(value, HashBiMap.this.f8540b[g2]);
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        @CanIgnoreReturnValue
        public boolean remove(@NullableDecl Object obj) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                Object key = entry.getKey();
                Object value = entry.getValue();
                int d2 = Hashing.d(key);
                int h2 = HashBiMap.this.h(key, d2);
                if (h2 == -1 || !Objects.equal(value, HashBiMap.this.f8540b[h2])) {
                    return false;
                }
                HashBiMap.this.p(h2, d2);
                return true;
            }
            return false;
        }
    }

    /* loaded from: classes2.dex */
    static class Inverse<K, V> extends AbstractMap<V, K> implements BiMap<V, K>, Serializable {
        private final HashBiMap<K, V> forward;
        private transient Set<Map.Entry<V, K>> inverseEntrySet;

        Inverse(HashBiMap hashBiMap) {
            this.forward = hashBiMap;
        }

        @GwtIncompatible("serialization")
        private void readObject(ObjectInputStream objectInputStream) {
            objectInputStream.defaultReadObject();
            ((HashBiMap) this.forward).inverse = this;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public void clear() {
            this.forward.clear();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(@NullableDecl Object obj) {
            return this.forward.containsValue(obj);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsValue(@NullableDecl Object obj) {
            return this.forward.containsKey(obj);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<Map.Entry<V, K>> entrySet() {
            Set<Map.Entry<V, K>> set = this.inverseEntrySet;
            if (set == null) {
                InverseEntrySet inverseEntrySet = new InverseEntrySet(this.forward);
                this.inverseEntrySet = inverseEntrySet;
                return inverseEntrySet;
            }
            return set;
        }

        @Override // com.google.common.collect.BiMap
        @CanIgnoreReturnValue
        @NullableDecl
        public K forcePut(@NullableDecl V v, @NullableDecl K k2) {
            return (K) this.forward.n(v, k2, true);
        }

        @Override // java.util.AbstractMap, java.util.Map
        @NullableDecl
        public K get(@NullableDecl Object obj) {
            return (K) this.forward.k(obj);
        }

        @Override // com.google.common.collect.BiMap
        public BiMap<K, V> inverse() {
            return this.forward;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<V> keySet() {
            return this.forward.values();
        }

        @Override // java.util.AbstractMap, java.util.Map, com.google.common.collect.BiMap
        @CanIgnoreReturnValue
        @NullableDecl
        public K put(@NullableDecl V v, @NullableDecl K k2) {
            return (K) this.forward.n(v, k2, false);
        }

        @Override // java.util.AbstractMap, java.util.Map
        @CanIgnoreReturnValue
        @NullableDecl
        public K remove(@NullableDecl Object obj) {
            return (K) this.forward.r(obj);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int size() {
            return this.forward.f8541c;
        }

        @Override // java.util.AbstractMap, java.util.Map, com.google.common.collect.BiMap
        public Set<K> values() {
            return this.forward.keySet();
        }
    }

    /* loaded from: classes2.dex */
    static class InverseEntrySet<K, V> extends View<K, V, Map.Entry<V, K>> {
        InverseEntrySet(HashBiMap hashBiMap) {
            super(hashBiMap);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.HashBiMap.View
        /* renamed from: b */
        public Map.Entry a(int i2) {
            return new EntryForValue(this.f8552a, i2);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(@NullableDecl Object obj) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                Object key = entry.getKey();
                Object value = entry.getValue();
                int i2 = this.f8552a.i(key);
                return i2 != -1 && Objects.equal(this.f8552a.f8539a[i2], value);
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                Object key = entry.getKey();
                Object value = entry.getValue();
                int d2 = Hashing.d(key);
                int j2 = this.f8552a.j(key, d2);
                if (j2 == -1 || !Objects.equal(this.f8552a.f8539a[j2], value)) {
                    return false;
                }
                this.f8552a.q(j2, d2);
                return true;
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public final class KeySet extends View<K, V, K> {
        KeySet() {
            super(HashBiMap.this);
        }

        @Override // com.google.common.collect.HashBiMap.View
        Object a(int i2) {
            return HashBiMap.this.f8539a[i2];
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(@NullableDecl Object obj) {
            return HashBiMap.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(@NullableDecl Object obj) {
            int d2 = Hashing.d(obj);
            int h2 = HashBiMap.this.h(obj, d2);
            if (h2 != -1) {
                HashBiMap.this.p(h2, d2);
                return true;
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public final class ValueSet extends View<K, V, V> {
        ValueSet() {
            super(HashBiMap.this);
        }

        @Override // com.google.common.collect.HashBiMap.View
        Object a(int i2) {
            return HashBiMap.this.f8540b[i2];
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(@NullableDecl Object obj) {
            return HashBiMap.this.containsValue(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(@NullableDecl Object obj) {
            int d2 = Hashing.d(obj);
            int j2 = HashBiMap.this.j(obj, d2);
            if (j2 != -1) {
                HashBiMap.this.q(j2, d2);
                return true;
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static abstract class View<K, V, T> extends AbstractSet<T> {

        /* renamed from: a  reason: collision with root package name */
        final HashBiMap f8552a;

        View(HashBiMap hashBiMap) {
            this.f8552a = hashBiMap;
        }

        abstract Object a(int i2);

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            this.f8552a.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<T> iterator() {
            return new Iterator<T>() { // from class: com.google.common.collect.HashBiMap.View.1
                private int expectedModCount;
                private int index;
                private int indexToRemove = -1;
                private int remaining;

                {
                    this.index = View.this.f8552a.firstInInsertionOrder;
                    HashBiMap hashBiMap = View.this.f8552a;
                    this.expectedModCount = hashBiMap.f8542d;
                    this.remaining = hashBiMap.f8541c;
                }

                private void checkForComodification() {
                    if (View.this.f8552a.f8542d != this.expectedModCount) {
                        throw new ConcurrentModificationException();
                    }
                }

                @Override // java.util.Iterator
                public boolean hasNext() {
                    checkForComodification();
                    return this.index != -2 && this.remaining > 0;
                }

                @Override // java.util.Iterator
                public T next() {
                    if (hasNext()) {
                        T t2 = (T) View.this.a(this.index);
                        this.indexToRemove = this.index;
                        this.index = View.this.f8552a.nextInInsertionOrder[this.index];
                        this.remaining--;
                        return t2;
                    }
                    throw new NoSuchElementException();
                }

                @Override // java.util.Iterator
                public void remove() {
                    checkForComodification();
                    CollectPreconditions.e(this.indexToRemove != -1);
                    View.this.f8552a.o(this.indexToRemove);
                    int i2 = this.index;
                    HashBiMap hashBiMap = View.this.f8552a;
                    if (i2 == hashBiMap.f8541c) {
                        this.index = this.indexToRemove;
                    }
                    this.indexToRemove = -1;
                    this.expectedModCount = hashBiMap.f8542d;
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.f8552a.f8541c;
        }
    }

    private HashBiMap(int i2) {
        l(i2);
    }

    private int bucket(int i2) {
        return i2 & (this.hashTableKToV.length - 1);
    }

    public static <K, V> HashBiMap<K, V> create() {
        return create(16);
    }

    public static <K, V> HashBiMap<K, V> create(int i2) {
        return new HashBiMap<>(i2);
    }

    public static <K, V> HashBiMap<K, V> create(Map<? extends K, ? extends V> map) {
        HashBiMap<K, V> create = create(map.size());
        create.putAll(map);
        return create;
    }

    private static int[] createFilledWithAbsent(int i2) {
        int[] iArr = new int[i2];
        Arrays.fill(iArr, -1);
        return iArr;
    }

    private void deleteFromTableKToV(int i2, int i3) {
        Preconditions.checkArgument(i2 != -1);
        int bucket = bucket(i3);
        int[] iArr = this.hashTableKToV;
        if (iArr[bucket] == i2) {
            int[] iArr2 = this.nextInBucketKToV;
            iArr[bucket] = iArr2[i2];
            iArr2[i2] = -1;
            return;
        }
        int i4 = iArr[bucket];
        int i5 = this.nextInBucketKToV[i4];
        while (true) {
            int i6 = i5;
            int i7 = i4;
            i4 = i6;
            if (i4 == -1) {
                throw new AssertionError("Expected to find entry with key " + this.f8539a[i2]);
            } else if (i4 == i2) {
                int[] iArr3 = this.nextInBucketKToV;
                iArr3[i7] = iArr3[i2];
                iArr3[i2] = -1;
                return;
            } else {
                i5 = this.nextInBucketKToV[i4];
            }
        }
    }

    private void deleteFromTableVToK(int i2, int i3) {
        Preconditions.checkArgument(i2 != -1);
        int bucket = bucket(i3);
        int[] iArr = this.hashTableVToK;
        if (iArr[bucket] == i2) {
            int[] iArr2 = this.nextInBucketVToK;
            iArr[bucket] = iArr2[i2];
            iArr2[i2] = -1;
            return;
        }
        int i4 = iArr[bucket];
        int i5 = this.nextInBucketVToK[i4];
        while (true) {
            int i6 = i5;
            int i7 = i4;
            i4 = i6;
            if (i4 == -1) {
                throw new AssertionError("Expected to find entry with value " + this.f8540b[i2]);
            } else if (i4 == i2) {
                int[] iArr3 = this.nextInBucketVToK;
                iArr3[i7] = iArr3[i2];
                iArr3[i2] = -1;
                return;
            } else {
                i5 = this.nextInBucketVToK[i4];
            }
        }
    }

    private void ensureCapacity(int i2) {
        int[] iArr = this.nextInBucketKToV;
        if (iArr.length < i2) {
            int a2 = ImmutableCollection.Builder.a(iArr.length, i2);
            this.f8539a = Arrays.copyOf(this.f8539a, a2);
            this.f8540b = Arrays.copyOf(this.f8540b, a2);
            this.nextInBucketKToV = expandAndFillWithAbsent(this.nextInBucketKToV, a2);
            this.nextInBucketVToK = expandAndFillWithAbsent(this.nextInBucketVToK, a2);
            this.prevInInsertionOrder = expandAndFillWithAbsent(this.prevInInsertionOrder, a2);
            this.nextInInsertionOrder = expandAndFillWithAbsent(this.nextInInsertionOrder, a2);
        }
        if (this.hashTableKToV.length < i2) {
            int a3 = Hashing.a(i2, 1.0d);
            this.hashTableKToV = createFilledWithAbsent(a3);
            this.hashTableVToK = createFilledWithAbsent(a3);
            for (int i3 = 0; i3 < this.f8541c; i3++) {
                int bucket = bucket(Hashing.d(this.f8539a[i3]));
                int[] iArr2 = this.nextInBucketKToV;
                int[] iArr3 = this.hashTableKToV;
                iArr2[i3] = iArr3[bucket];
                iArr3[bucket] = i3;
                int bucket2 = bucket(Hashing.d(this.f8540b[i3]));
                int[] iArr4 = this.nextInBucketVToK;
                int[] iArr5 = this.hashTableVToK;
                iArr4[i3] = iArr5[bucket2];
                iArr5[bucket2] = i3;
            }
        }
    }

    private static int[] expandAndFillWithAbsent(int[] iArr, int i2) {
        int length = iArr.length;
        int[] copyOf = Arrays.copyOf(iArr, i2);
        Arrays.fill(copyOf, length, i2, -1);
        return copyOf;
    }

    private void insertIntoTableKToV(int i2, int i3) {
        Preconditions.checkArgument(i2 != -1);
        int bucket = bucket(i3);
        int[] iArr = this.nextInBucketKToV;
        int[] iArr2 = this.hashTableKToV;
        iArr[i2] = iArr2[bucket];
        iArr2[bucket] = i2;
    }

    private void insertIntoTableVToK(int i2, int i3) {
        Preconditions.checkArgument(i2 != -1);
        int bucket = bucket(i3);
        int[] iArr = this.nextInBucketVToK;
        int[] iArr2 = this.hashTableVToK;
        iArr[i2] = iArr2[bucket];
        iArr2[bucket] = i2;
    }

    private void moveEntryToIndex(int i2, int i3) {
        int i4;
        int i5;
        if (i2 == i3) {
            return;
        }
        int i6 = this.prevInInsertionOrder[i2];
        int i7 = this.nextInInsertionOrder[i2];
        setSucceeds(i6, i3);
        setSucceeds(i3, i7);
        Object[] objArr = this.f8539a;
        Object obj = objArr[i2];
        Object[] objArr2 = this.f8540b;
        Object obj2 = objArr2[i2];
        objArr[i3] = obj;
        objArr2[i3] = obj2;
        int bucket = bucket(Hashing.d(obj));
        int[] iArr = this.hashTableKToV;
        if (iArr[bucket] == i2) {
            iArr[bucket] = i3;
        } else {
            int i8 = iArr[bucket];
            int i9 = this.nextInBucketKToV[i8];
            while (true) {
                int i10 = i9;
                i4 = i8;
                i8 = i10;
                if (i8 == i2) {
                    break;
                }
                i9 = this.nextInBucketKToV[i8];
            }
            this.nextInBucketKToV[i4] = i3;
        }
        int[] iArr2 = this.nextInBucketKToV;
        iArr2[i3] = iArr2[i2];
        iArr2[i2] = -1;
        int bucket2 = bucket(Hashing.d(obj2));
        int[] iArr3 = this.hashTableVToK;
        if (iArr3[bucket2] == i2) {
            iArr3[bucket2] = i3;
        } else {
            int i11 = iArr3[bucket2];
            int i12 = this.nextInBucketVToK[i11];
            while (true) {
                int i13 = i12;
                i5 = i11;
                i11 = i13;
                if (i11 == i2) {
                    break;
                }
                i12 = this.nextInBucketVToK[i11];
            }
            this.nextInBucketVToK[i5] = i3;
        }
        int[] iArr4 = this.nextInBucketVToK;
        iArr4[i3] = iArr4[i2];
        iArr4[i2] = -1;
    }

    @GwtIncompatible
    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        int h2 = Serialization.h(objectInputStream);
        l(16);
        Serialization.c(this, objectInputStream, h2);
    }

    private void removeEntry(int i2, int i3, int i4) {
        Preconditions.checkArgument(i2 != -1);
        deleteFromTableKToV(i2, i3);
        deleteFromTableVToK(i2, i4);
        setSucceeds(this.prevInInsertionOrder[i2], this.nextInInsertionOrder[i2]);
        moveEntryToIndex(this.f8541c - 1, i2);
        Object[] objArr = this.f8539a;
        int i5 = this.f8541c;
        objArr[i5 - 1] = null;
        this.f8540b[i5 - 1] = null;
        this.f8541c = i5 - 1;
        this.f8542d++;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void replaceKeyInEntry(int i2, @NullableDecl K k2, boolean z) {
        Preconditions.checkArgument(i2 != -1);
        int d2 = Hashing.d(k2);
        int h2 = h(k2, d2);
        int i3 = this.lastInInsertionOrder;
        int i4 = -2;
        if (h2 != -1) {
            if (!z) {
                throw new IllegalArgumentException("Key already present in map: " + k2);
            }
            i3 = this.prevInInsertionOrder[h2];
            i4 = this.nextInInsertionOrder[h2];
            p(h2, d2);
            if (i2 == this.f8541c) {
                i2 = h2;
            }
        }
        if (i3 == i2) {
            i3 = this.prevInInsertionOrder[i2];
        } else if (i3 == this.f8541c) {
            i3 = h2;
        }
        if (i4 == i2) {
            h2 = this.nextInInsertionOrder[i2];
        } else if (i4 != this.f8541c) {
            h2 = i4;
        }
        setSucceeds(this.prevInInsertionOrder[i2], this.nextInInsertionOrder[i2]);
        deleteFromTableKToV(i2, Hashing.d(this.f8539a[i2]));
        this.f8539a[i2] = k2;
        insertIntoTableKToV(i2, Hashing.d(k2));
        setSucceeds(i3, i2);
        setSucceeds(i2, h2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void replaceValueInEntry(int i2, @NullableDecl V v, boolean z) {
        Preconditions.checkArgument(i2 != -1);
        int d2 = Hashing.d(v);
        int j2 = j(v, d2);
        if (j2 != -1) {
            if (!z) {
                throw new IllegalArgumentException("Value already present in map: " + v);
            }
            q(j2, d2);
            if (i2 == this.f8541c) {
                i2 = j2;
            }
        }
        deleteFromTableVToK(i2, Hashing.d(this.f8540b[i2]));
        this.f8540b[i2] = v;
        insertIntoTableVToK(i2, d2);
    }

    private void setSucceeds(int i2, int i3) {
        if (i2 == -2) {
            this.firstInInsertionOrder = i3;
        } else {
            this.nextInInsertionOrder[i2] = i3;
        }
        if (i3 == -2) {
            this.lastInInsertionOrder = i2;
        } else {
            this.prevInInsertionOrder[i3] = i2;
        }
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.defaultWriteObject();
        Serialization.i(this, objectOutputStream);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        Arrays.fill(this.f8539a, 0, this.f8541c, (Object) null);
        Arrays.fill(this.f8540b, 0, this.f8541c, (Object) null);
        Arrays.fill(this.hashTableKToV, -1);
        Arrays.fill(this.hashTableVToK, -1);
        Arrays.fill(this.nextInBucketKToV, 0, this.f8541c, -1);
        Arrays.fill(this.nextInBucketVToK, 0, this.f8541c, -1);
        Arrays.fill(this.prevInInsertionOrder, 0, this.f8541c, -1);
        Arrays.fill(this.nextInInsertionOrder, 0, this.f8541c, -1);
        this.f8541c = 0;
        this.firstInInsertionOrder = -2;
        this.lastInInsertionOrder = -2;
        this.f8542d++;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(@NullableDecl Object obj) {
        return g(obj) != -1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsValue(@NullableDecl Object obj) {
        return i(obj) != -1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = this.entrySet;
        if (set == null) {
            EntrySet entrySet = new EntrySet();
            this.entrySet = entrySet;
            return entrySet;
        }
        return set;
    }

    int f(@NullableDecl Object obj, int i2, int[] iArr, int[] iArr2, Object[] objArr) {
        int i3 = iArr[bucket(i2)];
        while (i3 != -1) {
            if (Objects.equal(objArr[i3], obj)) {
                return i3;
            }
            i3 = iArr2[i3];
        }
        return -1;
    }

    @Override // com.google.common.collect.BiMap
    @CanIgnoreReturnValue
    @NullableDecl
    public V forcePut(@NullableDecl K k2, @NullableDecl V v) {
        return (V) m(k2, v, true);
    }

    int g(@NullableDecl Object obj) {
        return h(obj, Hashing.d(obj));
    }

    @Override // java.util.AbstractMap, java.util.Map
    @NullableDecl
    public V get(@NullableDecl Object obj) {
        int g2 = g(obj);
        if (g2 == -1) {
            return null;
        }
        return (V) this.f8540b[g2];
    }

    int h(@NullableDecl Object obj, int i2) {
        return f(obj, i2, this.hashTableKToV, this.nextInBucketKToV, this.f8539a);
    }

    int i(@NullableDecl Object obj) {
        return j(obj, Hashing.d(obj));
    }

    @Override // com.google.common.collect.BiMap
    public BiMap<V, K> inverse() {
        BiMap<V, K> biMap = this.inverse;
        if (biMap == null) {
            Inverse inverse = new Inverse(this);
            this.inverse = inverse;
            return inverse;
        }
        return biMap;
    }

    int j(@NullableDecl Object obj, int i2) {
        return f(obj, i2, this.hashTableVToK, this.nextInBucketVToK, this.f8540b);
    }

    @NullableDecl
    Object k(@NullableDecl Object obj) {
        int i2 = i(obj);
        if (i2 == -1) {
            return null;
        }
        return this.f8539a[i2];
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        Set<K> set = this.keySet;
        if (set == null) {
            KeySet keySet = new KeySet();
            this.keySet = keySet;
            return keySet;
        }
        return set;
    }

    void l(int i2) {
        CollectPreconditions.b(i2, "expectedSize");
        int a2 = Hashing.a(i2, 1.0d);
        this.f8541c = 0;
        this.f8539a = new Object[i2];
        this.f8540b = new Object[i2];
        this.hashTableKToV = createFilledWithAbsent(a2);
        this.hashTableVToK = createFilledWithAbsent(a2);
        this.nextInBucketKToV = createFilledWithAbsent(i2);
        this.nextInBucketVToK = createFilledWithAbsent(i2);
        this.firstInInsertionOrder = -2;
        this.lastInInsertionOrder = -2;
        this.prevInInsertionOrder = createFilledWithAbsent(i2);
        this.nextInInsertionOrder = createFilledWithAbsent(i2);
    }

    @NullableDecl
    Object m(@NullableDecl Object obj, @NullableDecl Object obj2, boolean z) {
        int d2 = Hashing.d(obj);
        int h2 = h(obj, d2);
        if (h2 != -1) {
            Object obj3 = this.f8540b[h2];
            if (Objects.equal(obj3, obj2)) {
                return obj2;
            }
            replaceValueInEntry(h2, obj2, z);
            return obj3;
        }
        int d3 = Hashing.d(obj2);
        int j2 = j(obj2, d3);
        if (!z) {
            Preconditions.checkArgument(j2 == -1, "Value already present: %s", obj2);
        } else if (j2 != -1) {
            q(j2, d3);
        }
        ensureCapacity(this.f8541c + 1);
        Object[] objArr = this.f8539a;
        int i2 = this.f8541c;
        objArr[i2] = obj;
        this.f8540b[i2] = obj2;
        insertIntoTableKToV(i2, d2);
        insertIntoTableVToK(this.f8541c, d3);
        setSucceeds(this.lastInInsertionOrder, this.f8541c);
        setSucceeds(this.f8541c, -2);
        this.f8541c++;
        this.f8542d++;
        return null;
    }

    @NullableDecl
    Object n(@NullableDecl Object obj, @NullableDecl Object obj2, boolean z) {
        int d2 = Hashing.d(obj);
        int j2 = j(obj, d2);
        if (j2 != -1) {
            Object obj3 = this.f8539a[j2];
            if (Objects.equal(obj3, obj2)) {
                return obj2;
            }
            replaceKeyInEntry(j2, obj2, z);
            return obj3;
        }
        int i2 = this.lastInInsertionOrder;
        int d3 = Hashing.d(obj2);
        int h2 = h(obj2, d3);
        if (!z) {
            Preconditions.checkArgument(h2 == -1, "Key already present: %s", obj2);
        } else if (h2 != -1) {
            i2 = this.prevInInsertionOrder[h2];
            p(h2, d3);
        }
        ensureCapacity(this.f8541c + 1);
        Object[] objArr = this.f8539a;
        int i3 = this.f8541c;
        objArr[i3] = obj2;
        this.f8540b[i3] = obj;
        insertIntoTableKToV(i3, d3);
        insertIntoTableVToK(this.f8541c, d2);
        int i4 = i2 == -2 ? this.firstInInsertionOrder : this.nextInInsertionOrder[i2];
        setSucceeds(i2, this.f8541c);
        setSucceeds(this.f8541c, i4);
        this.f8541c++;
        this.f8542d++;
        return null;
    }

    void o(int i2) {
        p(i2, Hashing.d(this.f8539a[i2]));
    }

    void p(int i2, int i3) {
        removeEntry(i2, i3, Hashing.d(this.f8540b[i2]));
    }

    @Override // java.util.AbstractMap, java.util.Map, com.google.common.collect.BiMap
    @CanIgnoreReturnValue
    public V put(@NullableDecl K k2, @NullableDecl V v) {
        return (V) m(k2, v, false);
    }

    void q(int i2, int i3) {
        removeEntry(i2, Hashing.d(this.f8539a[i2]), i3);
    }

    @NullableDecl
    Object r(@NullableDecl Object obj) {
        int d2 = Hashing.d(obj);
        int j2 = j(obj, d2);
        if (j2 == -1) {
            return null;
        }
        Object obj2 = this.f8539a[j2];
        q(j2, d2);
        return obj2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CanIgnoreReturnValue
    @NullableDecl
    public V remove(@NullableDecl Object obj) {
        int d2 = Hashing.d(obj);
        int h2 = h(obj, d2);
        if (h2 == -1) {
            return null;
        }
        V v = (V) this.f8540b[h2];
        p(h2, d2);
        return v;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.f8541c;
    }

    @Override // java.util.AbstractMap, java.util.Map, com.google.common.collect.BiMap
    public Set<V> values() {
        Set<V> set = this.valueSet;
        if (set == null) {
            ValueSet valueSet = new ValueSet();
            this.valueSet = valueSet;
            return valueSet;
        }
        return set;
    }
}
