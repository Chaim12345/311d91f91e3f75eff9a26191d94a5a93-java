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
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* JADX INFO: Access modifiers changed from: package-private */
@GwtIncompatible
/* loaded from: classes2.dex */
public class CompactHashMap<K, V> extends AbstractMap<K, V> implements Serializable {
    private static final int MAX_HASH_BUCKET_LENGTH = 9;
    private static final Object NOT_FOUND = new Object();
    @VisibleForTesting
    @NullableDecl

    /* renamed from: a  reason: collision with root package name */
    transient int[] f8462a;
    @VisibleForTesting
    @NullableDecl

    /* renamed from: b  reason: collision with root package name */
    transient Object[] f8463b;
    @VisibleForTesting
    @NullableDecl

    /* renamed from: c  reason: collision with root package name */
    transient Object[] f8464c;
    @NullableDecl
    private transient Set<Map.Entry<K, V>> entrySetView;
    @NullableDecl
    private transient Set<K> keySetView;
    private transient int metadata;
    private transient int size;
    @NullableDecl
    private transient Object table;
    @NullableDecl
    private transient Collection<V> valuesView;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class EntrySetView extends AbstractSet<Map.Entry<K, V>> {
        EntrySetView() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            CompactHashMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(@NullableDecl Object obj) {
            Map p2 = CompactHashMap.this.p();
            if (p2 != null) {
                return p2.entrySet().contains(obj);
            }
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                int indexOf = CompactHashMap.this.indexOf(entry.getKey());
                return indexOf != -1 && Objects.equal(CompactHashMap.this.f8464c[indexOf], entry.getValue());
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return CompactHashMap.this.q();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(@NullableDecl Object obj) {
            Map p2 = CompactHashMap.this.p();
            if (p2 != null) {
                return p2.entrySet().remove(obj);
            }
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                if (CompactHashMap.this.y()) {
                    return false;
                }
                int hashTableMask = CompactHashMap.this.hashTableMask();
                Object key = entry.getKey();
                Object value = entry.getValue();
                Object obj2 = CompactHashMap.this.table;
                CompactHashMap compactHashMap = CompactHashMap.this;
                int f2 = CompactHashing.f(key, value, hashTableMask, obj2, compactHashMap.f8462a, compactHashMap.f8463b, compactHashMap.f8464c);
                if (f2 == -1) {
                    return false;
                }
                CompactHashMap.this.x(f2, hashTableMask);
                CompactHashMap.g(CompactHashMap.this);
                CompactHashMap.this.t();
                return true;
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return CompactHashMap.this.size();
        }
    }

    /* loaded from: classes2.dex */
    private abstract class Itr<T> implements Iterator<T> {

        /* renamed from: a  reason: collision with root package name */
        int f8469a;

        /* renamed from: b  reason: collision with root package name */
        int f8470b;

        /* renamed from: c  reason: collision with root package name */
        int f8471c;

        private Itr() {
            this.f8469a = CompactHashMap.this.metadata;
            this.f8470b = CompactHashMap.this.r();
            this.f8471c = -1;
        }

        private void checkForConcurrentModification() {
            if (CompactHashMap.this.metadata != this.f8469a) {
                throw new ConcurrentModificationException();
            }
        }

        abstract Object a(int i2);

        void b() {
            this.f8469a += 32;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f8470b >= 0;
        }

        @Override // java.util.Iterator
        public T next() {
            checkForConcurrentModification();
            if (hasNext()) {
                int i2 = this.f8470b;
                this.f8471c = i2;
                T t2 = (T) a(i2);
                this.f8470b = CompactHashMap.this.s(this.f8470b);
                return t2;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            checkForConcurrentModification();
            CollectPreconditions.e(this.f8471c >= 0);
            b();
            CompactHashMap compactHashMap = CompactHashMap.this;
            compactHashMap.remove(compactHashMap.f8463b[this.f8471c]);
            this.f8470b = CompactHashMap.this.i(this.f8470b, this.f8471c);
            this.f8471c = -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class KeySetView extends AbstractSet<K> {
        KeySetView() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            CompactHashMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return CompactHashMap.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return CompactHashMap.this.w();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(@NullableDecl Object obj) {
            Map p2 = CompactHashMap.this.p();
            return p2 != null ? p2.keySet().remove(obj) : CompactHashMap.this.removeHelper(obj) != CompactHashMap.NOT_FOUND;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return CompactHashMap.this.size();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public final class MapEntry extends AbstractMapEntry<K, V> {
        @NullableDecl
        private final K key;
        private int lastKnownIndex;

        MapEntry(int i2) {
            this.key = (K) CompactHashMap.this.f8463b[i2];
            this.lastKnownIndex = i2;
        }

        private void updateLastKnownIndex() {
            int i2 = this.lastKnownIndex;
            if (i2 == -1 || i2 >= CompactHashMap.this.size() || !Objects.equal(this.key, CompactHashMap.this.f8463b[this.lastKnownIndex])) {
                this.lastKnownIndex = CompactHashMap.this.indexOf(this.key);
            }
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        @NullableDecl
        public K getKey() {
            return this.key;
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        @NullableDecl
        public V getValue() {
            Map p2 = CompactHashMap.this.p();
            if (p2 != null) {
                return (V) p2.get(this.key);
            }
            updateLastKnownIndex();
            int i2 = this.lastKnownIndex;
            if (i2 == -1) {
                return null;
            }
            return (V) CompactHashMap.this.f8464c[i2];
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public V setValue(V v) {
            Map p2 = CompactHashMap.this.p();
            if (p2 != null) {
                return (V) p2.put(this.key, v);
            }
            updateLastKnownIndex();
            int i2 = this.lastKnownIndex;
            if (i2 == -1) {
                CompactHashMap.this.put(this.key, v);
                return null;
            }
            Object[] objArr = CompactHashMap.this.f8464c;
            V v2 = (V) objArr[i2];
            objArr[i2] = v;
            return v2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class ValuesView extends AbstractCollection<V> {
        ValuesView() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            CompactHashMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return CompactHashMap.this.A();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return CompactHashMap.this.size();
        }
    }

    CompactHashMap() {
        u(3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CompactHashMap(int i2) {
        u(i2);
    }

    public static <K, V> CompactHashMap<K, V> create() {
        return new CompactHashMap<>();
    }

    public static <K, V> CompactHashMap<K, V> createWithExpectedSize(int i2) {
        return new CompactHashMap<>(i2);
    }

    static /* synthetic */ int g(CompactHashMap compactHashMap) {
        int i2 = compactHashMap.size;
        compactHashMap.size = i2 - 1;
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int hashTableMask() {
        return (1 << (this.metadata & 31)) - 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int indexOf(@NullableDecl Object obj) {
        if (y()) {
            return -1;
        }
        int d2 = Hashing.d(obj);
        int hashTableMask = hashTableMask();
        int h2 = CompactHashing.h(this.table, d2 & hashTableMask);
        if (h2 == 0) {
            return -1;
        }
        int b2 = CompactHashing.b(d2, hashTableMask);
        do {
            int i2 = h2 - 1;
            int i3 = this.f8462a[i2];
            if (CompactHashing.b(i3, hashTableMask) == b2 && Objects.equal(obj, this.f8463b[i2])) {
                return i2;
            }
            h2 = CompactHashing.c(i3, hashTableMask);
        } while (h2 != 0);
        return -1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        int readInt = objectInputStream.readInt();
        if (readInt < 0) {
            throw new InvalidObjectException("Invalid size: " + readInt);
        }
        u(readInt);
        for (int i2 = 0; i2 < readInt; i2++) {
            put(objectInputStream.readObject(), objectInputStream.readObject());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @NullableDecl
    public Object removeHelper(@NullableDecl Object obj) {
        if (y()) {
            return NOT_FOUND;
        }
        int hashTableMask = hashTableMask();
        int f2 = CompactHashing.f(obj, null, hashTableMask, this.table, this.f8462a, this.f8463b, null);
        if (f2 == -1) {
            return NOT_FOUND;
        }
        Object obj2 = this.f8464c[f2];
        x(f2, hashTableMask);
        this.size--;
        t();
        return obj2;
    }

    private void resizeMeMaybe(int i2) {
        int min;
        int length = this.f8462a.length;
        if (i2 <= length || (min = Math.min((int) LockFreeTaskQueueCore.MAX_CAPACITY_MASK, (Math.max(1, length >>> 1) + length) | 1)) == length) {
            return;
        }
        z(min);
    }

    @CanIgnoreReturnValue
    private int resizeTable(int i2, int i3, int i4, int i5) {
        Object a2 = CompactHashing.a(i3);
        int i6 = i3 - 1;
        if (i5 != 0) {
            CompactHashing.i(a2, i4 & i6, i5 + 1);
        }
        Object obj = this.table;
        int[] iArr = this.f8462a;
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
        Iterator q2 = q();
        while (q2.hasNext()) {
            Map.Entry entry = (Map.Entry) q2.next();
            objectOutputStream.writeObject(entry.getKey());
            objectOutputStream.writeObject(entry.getValue());
        }
    }

    Iterator A() {
        Map p2 = p();
        return p2 != null ? p2.values().iterator() : new CompactHashMap<K, V>.Itr<V>() { // from class: com.google.common.collect.CompactHashMap.3
            @Override // com.google.common.collect.CompactHashMap.Itr
            Object a(int i2) {
                return CompactHashMap.this.f8464c[i2];
            }
        };
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        if (y()) {
            return;
        }
        t();
        Map p2 = p();
        if (p2 != null) {
            this.metadata = Ints.constrainToRange(size(), 3, LockFreeTaskQueueCore.MAX_CAPACITY_MASK);
            p2.clear();
            this.table = null;
        } else {
            Arrays.fill(this.f8463b, 0, this.size, (Object) null);
            Arrays.fill(this.f8464c, 0, this.size, (Object) null);
            CompactHashing.g(this.table);
            Arrays.fill(this.f8462a, 0, this.size, 0);
        }
        this.size = 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(@NullableDecl Object obj) {
        Map p2 = p();
        return p2 != null ? p2.containsKey(obj) : indexOf(obj) != -1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsValue(@NullableDecl Object obj) {
        Map p2 = p();
        if (p2 != null) {
            return p2.containsValue(obj);
        }
        for (int i2 = 0; i2 < this.size; i2++) {
            if (Objects.equal(obj, this.f8464c[i2])) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = this.entrySetView;
        if (set == null) {
            Set<Map.Entry<K, V>> l2 = l();
            this.entrySetView = l2;
            return l2;
        }
        return set;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(@NullableDecl Object obj) {
        Map p2 = p();
        if (p2 != null) {
            return (V) p2.get(obj);
        }
        int indexOf = indexOf(obj);
        if (indexOf == -1) {
            return null;
        }
        h(indexOf);
        return (V) this.f8464c[indexOf];
    }

    void h(int i2) {
    }

    int i(int i2, int i3) {
        return i2 - 1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean isEmpty() {
        return size() == 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @CanIgnoreReturnValue
    public int j() {
        Preconditions.checkState(y(), "Arrays already allocated");
        int i2 = this.metadata;
        int j2 = CompactHashing.j(i2);
        this.table = CompactHashing.a(j2);
        setHashTableMask(j2 - 1);
        this.f8462a = new int[i2];
        this.f8463b = new Object[i2];
        this.f8464c = new Object[i2];
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    @VisibleForTesting
    @CanIgnoreReturnValue
    public Map k() {
        Map m2 = m(hashTableMask() + 1);
        int r2 = r();
        while (r2 >= 0) {
            m2.put(this.f8463b[r2], this.f8464c[r2]);
            r2 = s(r2);
        }
        this.table = m2;
        this.f8462a = null;
        this.f8463b = null;
        this.f8464c = null;
        t();
        return m2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        Set<K> set = this.keySetView;
        if (set == null) {
            Set<K> n2 = n();
            this.keySetView = n2;
            return n2;
        }
        return set;
    }

    Set l() {
        return new EntrySetView();
    }

    Map m(int i2) {
        return new LinkedHashMap(i2, 1.0f);
    }

    Set n() {
        return new KeySetView();
    }

    Collection o() {
        return new ValuesView();
    }

    @VisibleForTesting
    @NullableDecl
    Map p() {
        Object obj = this.table;
        if (obj instanceof Map) {
            return (Map) obj;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CanIgnoreReturnValue
    @NullableDecl
    public V put(@NullableDecl K k2, @NullableDecl V v) {
        int i2;
        if (y()) {
            j();
        }
        Map p2 = p();
        if (p2 != null) {
            return (V) p2.put(k2, v);
        }
        int[] iArr = this.f8462a;
        Object[] objArr = this.f8463b;
        Object[] objArr2 = this.f8464c;
        int i3 = this.size;
        int i4 = i3 + 1;
        int d2 = Hashing.d(k2);
        int hashTableMask = hashTableMask();
        int i5 = d2 & hashTableMask;
        int h2 = CompactHashing.h(this.table, i5);
        if (h2 == 0) {
            if (i4 <= hashTableMask) {
                CompactHashing.i(this.table, i5, i4);
                i2 = hashTableMask;
            }
            i2 = resizeTable(hashTableMask, CompactHashing.e(hashTableMask), d2, i3);
        } else {
            int b2 = CompactHashing.b(d2, hashTableMask);
            int i6 = 0;
            while (true) {
                int i7 = h2 - 1;
                int i8 = iArr[i7];
                if (CompactHashing.b(i8, hashTableMask) == b2 && Objects.equal(k2, objArr[i7])) {
                    V v2 = (V) objArr2[i7];
                    objArr2[i7] = v;
                    h(i7);
                    return v2;
                }
                int c2 = CompactHashing.c(i8, hashTableMask);
                i6++;
                if (c2 != 0) {
                    h2 = c2;
                } else if (i6 >= 9) {
                    return (V) k().put(k2, v);
                } else {
                    if (i4 <= hashTableMask) {
                        iArr[i7] = CompactHashing.d(i8, i4, hashTableMask);
                    }
                }
            }
        }
        resizeMeMaybe(i4);
        v(i3, k2, v, d2, i2);
        this.size = i4;
        t();
        return null;
    }

    Iterator q() {
        Map p2 = p();
        return p2 != null ? p2.entrySet().iterator() : new CompactHashMap<K, V>.Itr<Map.Entry<K, V>>() { // from class: com.google.common.collect.CompactHashMap.2
            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.google.common.collect.CompactHashMap.Itr
            /* renamed from: c */
            public Map.Entry a(int i2) {
                return new MapEntry(i2);
            }
        };
    }

    int r() {
        return isEmpty() ? -1 : 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CanIgnoreReturnValue
    @NullableDecl
    public V remove(@NullableDecl Object obj) {
        Map p2 = p();
        if (p2 != null) {
            return (V) p2.remove(obj);
        }
        V v = (V) removeHelper(obj);
        if (v == NOT_FOUND) {
            return null;
        }
        return v;
    }

    int s(int i2) {
        int i3 = i2 + 1;
        if (i3 < this.size) {
            return i3;
        }
        return -1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        Map p2 = p();
        return p2 != null ? p2.size() : this.size;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void t() {
        this.metadata += 32;
    }

    public void trimToSize() {
        if (y()) {
            return;
        }
        Map<? extends K, ? extends V> p2 = p();
        if (p2 != null) {
            Map m2 = m(size());
            m2.putAll(p2);
            this.table = m2;
            return;
        }
        int i2 = this.size;
        if (i2 < this.f8462a.length) {
            z(i2);
        }
        int j2 = CompactHashing.j(i2);
        int hashTableMask = hashTableMask();
        if (j2 < hashTableMask) {
            resizeTable(hashTableMask, j2, 0, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void u(int i2) {
        Preconditions.checkArgument(i2 >= 0, "Expected size must be >= 0");
        this.metadata = Ints.constrainToRange(i2, 1, LockFreeTaskQueueCore.MAX_CAPACITY_MASK);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void v(int i2, @NullableDecl Object obj, @NullableDecl Object obj2, int i3, int i4) {
        this.f8462a[i2] = CompactHashing.d(i3, 0, i4);
        this.f8463b[i2] = obj;
        this.f8464c[i2] = obj2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Collection<V> values() {
        Collection<V> collection = this.valuesView;
        if (collection == null) {
            Collection<V> o2 = o();
            this.valuesView = o2;
            return o2;
        }
        return collection;
    }

    Iterator w() {
        Map p2 = p();
        return p2 != null ? p2.keySet().iterator() : new CompactHashMap<K, V>.Itr<K>() { // from class: com.google.common.collect.CompactHashMap.1
            @Override // com.google.common.collect.CompactHashMap.Itr
            Object a(int i2) {
                return CompactHashMap.this.f8463b[i2];
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void x(int i2, int i3) {
        int size = size() - 1;
        if (i2 >= size) {
            this.f8463b[i2] = null;
            this.f8464c[i2] = null;
            this.f8462a[i2] = 0;
            return;
        }
        Object[] objArr = this.f8463b;
        Object obj = objArr[size];
        objArr[i2] = obj;
        Object[] objArr2 = this.f8464c;
        objArr2[i2] = objArr2[size];
        objArr[size] = null;
        objArr2[size] = null;
        int[] iArr = this.f8462a;
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
            int i6 = this.f8462a[i5];
            int c2 = CompactHashing.c(i6, i3);
            if (c2 == i4) {
                this.f8462a[i5] = CompactHashing.d(i6, i2 + 1, i3);
                return;
            }
            h2 = c2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public boolean y() {
        return this.table == null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void z(int i2) {
        this.f8462a = Arrays.copyOf(this.f8462a, i2);
        this.f8463b = Arrays.copyOf(this.f8463b, i2);
        this.f8464c = Arrays.copyOf(this.f8464c, i2);
    }
}
