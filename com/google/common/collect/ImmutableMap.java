package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotMock;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.RetainedWith;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@DoNotMock("Use ImmutableMap.of or another implementation")
@GwtCompatible(emulated = true, serializable = true)
/* loaded from: classes2.dex */
public abstract class ImmutableMap<K, V> implements Map<K, V>, Serializable {

    /* renamed from: a  reason: collision with root package name */
    static final Map.Entry[] f8563a = new Map.Entry[0];
    @RetainedWith
    @LazyInit
    private transient ImmutableSet<Map.Entry<K, V>> entrySet;
    @RetainedWith
    @LazyInit
    private transient ImmutableSet<K> keySet;
    @LazyInit
    private transient ImmutableSetMultimap<K, V> multimapView;
    @RetainedWith
    @LazyInit
    private transient ImmutableCollection<V> values;

    @DoNotMock
    /* loaded from: classes2.dex */
    public static class Builder<K, V> {
        @NullableDecl

        /* renamed from: a  reason: collision with root package name */
        Comparator f8565a;

        /* renamed from: b  reason: collision with root package name */
        Object[] f8566b;

        /* renamed from: c  reason: collision with root package name */
        int f8567c;

        /* renamed from: d  reason: collision with root package name */
        boolean f8568d;

        public Builder() {
            this(4);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder(int i2) {
            this.f8566b = new Object[i2 * 2];
            this.f8567c = 0;
            this.f8568d = false;
        }

        private void ensureCapacity(int i2) {
            int i3 = i2 * 2;
            Object[] objArr = this.f8566b;
            if (i3 > objArr.length) {
                this.f8566b = Arrays.copyOf(objArr, ImmutableCollection.Builder.a(objArr.length, i3));
                this.f8568d = false;
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void a() {
            int i2;
            if (this.f8565a != null) {
                if (this.f8568d) {
                    this.f8566b = Arrays.copyOf(this.f8566b, this.f8567c * 2);
                }
                Map.Entry[] entryArr = new Map.Entry[this.f8567c];
                int i3 = 0;
                while (true) {
                    i2 = this.f8567c;
                    if (i3 >= i2) {
                        break;
                    }
                    Object[] objArr = this.f8566b;
                    int i4 = i3 * 2;
                    entryArr[i3] = new AbstractMap.SimpleImmutableEntry(objArr[i4], objArr[i4 + 1]);
                    i3++;
                }
                Arrays.sort(entryArr, 0, i2, Ordering.from(this.f8565a).onResultOf(Maps.C()));
                for (int i5 = 0; i5 < this.f8567c; i5++) {
                    int i6 = i5 * 2;
                    this.f8566b[i6] = entryArr[i5].getKey();
                    this.f8566b[i6 + 1] = entryArr[i5].getValue();
                }
            }
        }

        public ImmutableMap<K, V> build() {
            a();
            this.f8568d = true;
            return RegularImmutableMap.j(this.f8567c, this.f8566b);
        }

        @CanIgnoreReturnValue
        @Beta
        public Builder<K, V> orderEntriesByValue(Comparator<? super V> comparator) {
            Preconditions.checkState(this.f8565a == null, "valueComparator was already set");
            this.f8565a = (Comparator) Preconditions.checkNotNull(comparator, "valueComparator");
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<K, V> put(K k2, V v) {
            ensureCapacity(this.f8567c + 1);
            CollectPreconditions.a(k2, v);
            Object[] objArr = this.f8566b;
            int i2 = this.f8567c;
            objArr[i2 * 2] = k2;
            objArr[(i2 * 2) + 1] = v;
            this.f8567c = i2 + 1;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<K, V> put(Map.Entry<? extends K, ? extends V> entry) {
            return put(entry.getKey(), entry.getValue());
        }

        @CanIgnoreReturnValue
        @Beta
        public Builder<K, V> putAll(Iterable<? extends Map.Entry<? extends K, ? extends V>> iterable) {
            if (iterable instanceof Collection) {
                ensureCapacity(this.f8567c + ((Collection) iterable).size());
            }
            for (Map.Entry<? extends K, ? extends V> entry : iterable) {
                put(entry);
            }
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<K, V> putAll(Map<? extends K, ? extends V> map) {
            return putAll(map.entrySet());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static abstract class IteratorBasedImmutableMap<K, V> extends ImmutableMap<K, V> {
        @Override // com.google.common.collect.ImmutableMap
        ImmutableSet c() {
            return new ImmutableMapEntrySet<K, V>() { // from class: com.google.common.collect.ImmutableMap.IteratorBasedImmutableMap.1EntrySetImpl
                @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
                public UnmodifiableIterator<Map.Entry<K, V>> iterator() {
                    return IteratorBasedImmutableMap.this.j();
                }

                @Override // com.google.common.collect.ImmutableMapEntrySet
                ImmutableMap j() {
                    return IteratorBasedImmutableMap.this;
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableMap
        public ImmutableSet d() {
            return new ImmutableMapKeySet(this);
        }

        @Override // com.google.common.collect.ImmutableMap
        ImmutableCollection e() {
            return new ImmutableMapValues(this);
        }

        @Override // com.google.common.collect.ImmutableMap, java.util.Map
        public /* bridge */ /* synthetic */ Set entrySet() {
            return super.entrySet();
        }

        abstract UnmodifiableIterator j();

        @Override // com.google.common.collect.ImmutableMap, java.util.Map
        public /* bridge */ /* synthetic */ Set keySet() {
            return super.keySet();
        }

        @Override // com.google.common.collect.ImmutableMap, java.util.Map, com.google.common.collect.BiMap
        public /* bridge */ /* synthetic */ Collection values() {
            return super.values();
        }
    }

    /* loaded from: classes2.dex */
    private final class MapViewOfValuesAsSingletonSets extends IteratorBasedImmutableMap<K, ImmutableSet<V>> {
        private MapViewOfValuesAsSingletonSets() {
        }

        @Override // com.google.common.collect.ImmutableMap, java.util.Map
        public boolean containsKey(@NullableDecl Object obj) {
            return ImmutableMap.this.containsKey(obj);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableMap.IteratorBasedImmutableMap, com.google.common.collect.ImmutableMap
        public ImmutableSet d() {
            return ImmutableMap.this.keySet();
        }

        @Override // com.google.common.collect.ImmutableMap
        boolean g() {
            return ImmutableMap.this.g();
        }

        @Override // com.google.common.collect.ImmutableMap, java.util.Map
        public ImmutableSet<V> get(@NullableDecl Object obj) {
            Object obj2 = ImmutableMap.this.get(obj);
            if (obj2 == null) {
                return null;
            }
            return ImmutableSet.of(obj2);
        }

        @Override // com.google.common.collect.ImmutableMap
        boolean h() {
            return ImmutableMap.this.h();
        }

        @Override // com.google.common.collect.ImmutableMap, java.util.Map
        public int hashCode() {
            return ImmutableMap.this.hashCode();
        }

        @Override // com.google.common.collect.ImmutableMap.IteratorBasedImmutableMap
        UnmodifiableIterator j() {
            final UnmodifiableIterator<Map.Entry<K, V>> it = ImmutableMap.this.entrySet().iterator();
            return new UnmodifiableIterator<Map.Entry<K, ImmutableSet<V>>>(this) { // from class: com.google.common.collect.ImmutableMap.MapViewOfValuesAsSingletonSets.1
                @Override // java.util.Iterator
                public boolean hasNext() {
                    return it.hasNext();
                }

                @Override // java.util.Iterator
                public Map.Entry<K, ImmutableSet<V>> next() {
                    final Map.Entry entry = (Map.Entry) it.next();
                    return new AbstractMapEntry<K, ImmutableSet<V>>(this) { // from class: com.google.common.collect.ImmutableMap.MapViewOfValuesAsSingletonSets.1.1
                        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
                        public K getKey() {
                            return (K) entry.getKey();
                        }

                        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
                        public ImmutableSet<V> getValue() {
                            return ImmutableSet.of(entry.getValue());
                        }
                    };
                }
            };
        }

        @Override // java.util.Map
        public int size() {
            return ImmutableMap.this.size();
        }
    }

    /* loaded from: classes2.dex */
    static class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        private final Object[] keys;
        private final Object[] values;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(boolean z, String str, Map.Entry entry, Map.Entry entry2) {
        if (!z) {
            throw b(str, entry, entry2);
        }
    }

    static IllegalArgumentException b(String str, Object obj, Object obj2) {
        return new IllegalArgumentException("Multiple entries with same " + str + ": " + obj + " and " + obj2);
    }

    public static <K, V> Builder<K, V> builder() {
        return new Builder<>();
    }

    @Beta
    public static <K, V> Builder<K, V> builderWithExpectedSize(int i2) {
        CollectPreconditions.b(i2, "expectedSize");
        return new Builder<>(i2);
    }

    @Beta
    public static <K, V> ImmutableMap<K, V> copyOf(Iterable<? extends Map.Entry<? extends K, ? extends V>> iterable) {
        Builder builder = new Builder(iterable instanceof Collection ? ((Collection) iterable).size() : 4);
        builder.putAll(iterable);
        return builder.build();
    }

    public static <K, V> ImmutableMap<K, V> copyOf(Map<? extends K, ? extends V> map) {
        if ((map instanceof ImmutableMap) && !(map instanceof SortedMap)) {
            ImmutableMap<K, V> immutableMap = (ImmutableMap) map;
            if (!immutableMap.h()) {
                return immutableMap;
            }
        }
        return copyOf(map.entrySet());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map.Entry f(Object obj, Object obj2) {
        CollectPreconditions.a(obj, obj2);
        return new AbstractMap.SimpleImmutableEntry(obj, obj2);
    }

    public static <K, V> ImmutableMap<K, V> of() {
        return RegularImmutableMap.f8931c;
    }

    public static <K, V> ImmutableMap<K, V> of(K k2, V v) {
        CollectPreconditions.a(k2, v);
        return RegularImmutableMap.j(1, new Object[]{k2, v});
    }

    public static <K, V> ImmutableMap<K, V> of(K k2, V v, K k3, V v2) {
        CollectPreconditions.a(k2, v);
        CollectPreconditions.a(k3, v2);
        return RegularImmutableMap.j(2, new Object[]{k2, v, k3, v2});
    }

    public static <K, V> ImmutableMap<K, V> of(K k2, V v, K k3, V v2, K k4, V v3) {
        CollectPreconditions.a(k2, v);
        CollectPreconditions.a(k3, v2);
        CollectPreconditions.a(k4, v3);
        return RegularImmutableMap.j(3, new Object[]{k2, v, k3, v2, k4, v3});
    }

    public static <K, V> ImmutableMap<K, V> of(K k2, V v, K k3, V v2, K k4, V v3, K k5, V v4) {
        CollectPreconditions.a(k2, v);
        CollectPreconditions.a(k3, v2);
        CollectPreconditions.a(k4, v3);
        CollectPreconditions.a(k5, v4);
        return RegularImmutableMap.j(4, new Object[]{k2, v, k3, v2, k4, v3, k5, v4});
    }

    public static <K, V> ImmutableMap<K, V> of(K k2, V v, K k3, V v2, K k4, V v3, K k5, V v4, K k6, V v5) {
        CollectPreconditions.a(k2, v);
        CollectPreconditions.a(k3, v2);
        CollectPreconditions.a(k4, v3);
        CollectPreconditions.a(k5, v4);
        CollectPreconditions.a(k6, v5);
        return RegularImmutableMap.j(5, new Object[]{k2, v, k3, v2, k4, v3, k5, v4, k6, v5});
    }

    public ImmutableSetMultimap<K, V> asMultimap() {
        if (isEmpty()) {
            return ImmutableSetMultimap.of();
        }
        ImmutableSetMultimap<K, V> immutableSetMultimap = this.multimapView;
        if (immutableSetMultimap == null) {
            ImmutableSetMultimap<K, V> immutableSetMultimap2 = new ImmutableSetMultimap<>(new MapViewOfValuesAsSingletonSets(), size(), null);
            this.multimapView = immutableSetMultimap2;
            return immutableSetMultimap2;
        }
        return immutableSetMultimap;
    }

    abstract ImmutableSet c();

    @Override // java.util.Map
    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public boolean containsKey(@NullableDecl Object obj) {
        return get(obj) != null;
    }

    @Override // java.util.Map
    public boolean containsValue(@NullableDecl Object obj) {
        return values().contains(obj);
    }

    abstract ImmutableSet d();

    abstract ImmutableCollection e();

    @Override // java.util.Map
    public ImmutableSet<Map.Entry<K, V>> entrySet() {
        ImmutableSet<Map.Entry<K, V>> immutableSet = this.entrySet;
        if (immutableSet == null) {
            ImmutableSet<Map.Entry<K, V>> c2 = c();
            this.entrySet = c2;
            return c2;
        }
        return immutableSet;
    }

    @Override // java.util.Map
    public boolean equals(@NullableDecl Object obj) {
        return Maps.m(this, obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean g() {
        return false;
    }

    @Override // java.util.Map
    public abstract V get(@NullableDecl Object obj);

    @Override // java.util.Map
    public final V getOrDefault(@NullableDecl Object obj, @NullableDecl V v) {
        V v2 = get(obj);
        return v2 != null ? v2 : v;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean h();

    @Override // java.util.Map
    public int hashCode() {
        return Sets.b(entrySet());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public UnmodifiableIterator i() {
        final UnmodifiableIterator<Map.Entry<K, V>> it = entrySet().iterator();
        return new UnmodifiableIterator<K>(this) { // from class: com.google.common.collect.ImmutableMap.1
            @Override // java.util.Iterator
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override // java.util.Iterator
            public K next() {
                return (K) ((Map.Entry) it.next()).getKey();
            }
        };
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.Map
    public ImmutableSet<K> keySet() {
        ImmutableSet<K> immutableSet = this.keySet;
        if (immutableSet == null) {
            ImmutableSet<K> d2 = d();
            this.keySet = d2;
            return d2;
        }
        return immutableSet;
    }

    @Override // java.util.Map
    @CanIgnoreReturnValue
    @Deprecated
    public final V put(K k2, V v) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @Deprecated
    public final void putAll(Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @CanIgnoreReturnValue
    @Deprecated
    public final V remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        return Maps.x(this);
    }

    @Override // java.util.Map
    public ImmutableCollection<V> values() {
        ImmutableCollection<V> immutableCollection = this.values;
        if (immutableCollection == null) {
            ImmutableCollection<V> e2 = e();
            this.values = e2;
            return e2;
        }
        return immutableCollection;
    }
}
