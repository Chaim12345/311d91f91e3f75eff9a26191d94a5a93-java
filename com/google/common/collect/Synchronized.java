package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.collect.Table;
import com.google.j2objc.annotations.RetainedWith;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Queue;
import java.util.RandomAccess;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* JADX INFO: Access modifiers changed from: package-private */
@GwtCompatible(emulated = true)
/* loaded from: classes2.dex */
public final class Synchronized {

    /* loaded from: classes2.dex */
    private static class SynchronizedAsMap<K, V> extends SynchronizedMap<K, Collection<V>> {
        private static final long serialVersionUID = 0;
        @NullableDecl

        /* renamed from: f  reason: collision with root package name */
        transient Set f9022f;
        @NullableDecl

        /* renamed from: g  reason: collision with root package name */
        transient Collection f9023g;

        SynchronizedAsMap(Map map, @NullableDecl Object obj) {
            super(map, obj);
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMap, java.util.Map
        public boolean containsValue(Object obj) {
            return values().contains(obj);
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMap, java.util.Map
        public Set<Map.Entry<K, Collection<V>>> entrySet() {
            Set<Map.Entry<K, Collection<V>>> set;
            synchronized (this.f9043b) {
                if (this.f9022f == null) {
                    this.f9022f = new SynchronizedAsMapEntries(b().entrySet(), this.f9043b);
                }
                set = this.f9022f;
            }
            return set;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMap, java.util.Map
        public Collection<V> get(Object obj) {
            Collection<V> typePreservingCollection;
            synchronized (this.f9043b) {
                Collection collection = (Collection) super.get(obj);
                typePreservingCollection = collection == null ? null : Synchronized.typePreservingCollection(collection, this.f9043b);
            }
            return typePreservingCollection;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMap, java.util.Map
        public Collection<Collection<V>> values() {
            Collection<Collection<V>> collection;
            synchronized (this.f9043b) {
                if (this.f9023g == null) {
                    this.f9023g = new SynchronizedAsMapValues(b().values(), this.f9043b);
                }
                collection = this.f9023g;
            }
            return collection;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class SynchronizedAsMapEntries<K, V> extends SynchronizedSet<Map.Entry<K, Collection<V>>> {
        private static final long serialVersionUID = 0;

        SynchronizedAsMapEntries(Set set, @NullableDecl Object obj) {
            super(set, obj);
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            boolean l2;
            synchronized (this.f9043b) {
                l2 = Maps.l(b(), obj);
            }
            return l2;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedCollection, java.util.Collection, java.util.Set
        public boolean containsAll(Collection<?> collection) {
            boolean c2;
            synchronized (this.f9043b) {
                c2 = Collections2.c(b(), collection);
            }
            return c2;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedSet, java.util.Collection, java.util.Set
        public boolean equals(Object obj) {
            boolean a2;
            if (obj == this) {
                return true;
            }
            synchronized (this.f9043b) {
                a2 = Sets.a(b(), obj);
            }
            return a2;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, Collection<V>>> iterator() {
            return new TransformedIterator<Map.Entry<K, Collection<V>>, Map.Entry<K, Collection<V>>>(super.iterator()) { // from class: com.google.common.collect.Synchronized.SynchronizedAsMapEntries.1
                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.google.common.collect.TransformedIterator
                /* renamed from: b */
                public Map.Entry a(final Map.Entry entry) {
                    return new ForwardingMapEntry<K, Collection<V>>() { // from class: com.google.common.collect.Synchronized.SynchronizedAsMapEntries.1.1
                        /* JADX INFO: Access modifiers changed from: protected */
                        @Override // com.google.common.collect.ForwardingMapEntry, com.google.common.collect.ForwardingObject
                        /* renamed from: a */
                        public Map.Entry delegate() {
                            return entry;
                        }

                        @Override // com.google.common.collect.ForwardingMapEntry, java.util.Map.Entry
                        public Collection<V> getValue() {
                            return Synchronized.typePreservingCollection((Collection) entry.getValue(), SynchronizedAsMapEntries.this.f9043b);
                        }
                    };
                }
            };
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            boolean t2;
            synchronized (this.f9043b) {
                t2 = Maps.t(b(), obj);
            }
            return t2;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedCollection, java.util.Collection, java.util.Set
        public boolean removeAll(Collection<?> collection) {
            boolean removeAll;
            synchronized (this.f9043b) {
                removeAll = Iterators.removeAll(b().iterator(), collection);
            }
            return removeAll;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedCollection, java.util.Collection, java.util.Set
        public boolean retainAll(Collection<?> collection) {
            boolean retainAll;
            synchronized (this.f9043b) {
                retainAll = Iterators.retainAll(b().iterator(), collection);
            }
            return retainAll;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedCollection, java.util.Collection, java.util.Set
        public Object[] toArray() {
            Object[] d2;
            synchronized (this.f9043b) {
                d2 = ObjectArrays.d(b());
            }
            return d2;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedCollection, java.util.Collection, java.util.Set
        public <T> T[] toArray(T[] tArr) {
            T[] tArr2;
            synchronized (this.f9043b) {
                tArr2 = (T[]) ObjectArrays.e(b(), tArr);
            }
            return tArr2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class SynchronizedAsMapValues<V> extends SynchronizedCollection<Collection<V>> {
        private static final long serialVersionUID = 0;

        SynchronizedAsMapValues(Collection collection, @NullableDecl Object obj) {
            super(collection, obj);
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Collection<V>> iterator() {
            return new TransformedIterator<Collection<V>, Collection<V>>(super.iterator()) { // from class: com.google.common.collect.Synchronized.SynchronizedAsMapValues.1
                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.google.common.collect.TransformedIterator
                /* renamed from: b */
                public Collection a(Collection collection) {
                    return Synchronized.typePreservingCollection(collection, SynchronizedAsMapValues.this.f9043b);
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes2.dex */
    public static class SynchronizedBiMap<K, V> extends SynchronizedMap<K, V> implements BiMap<K, V> {
        private static final long serialVersionUID = 0;
        @RetainedWith
        @NullableDecl
        private transient BiMap<V, K> inverse;
        @NullableDecl
        private transient Set<V> valueSet;

        private SynchronizedBiMap(BiMap<K, V> biMap, @NullableDecl Object obj, @NullableDecl BiMap<V, K> biMap2) {
            super(biMap, obj);
            this.inverse = biMap2;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.Synchronized.SynchronizedMap
        /* renamed from: c */
        public BiMap b() {
            return (BiMap) super.b();
        }

        @Override // com.google.common.collect.BiMap
        public V forcePut(K k2, V v) {
            V v2;
            synchronized (this.f9043b) {
                v2 = (V) b().forcePut(k2, v);
            }
            return v2;
        }

        @Override // com.google.common.collect.BiMap
        public BiMap<V, K> inverse() {
            BiMap<V, K> biMap;
            synchronized (this.f9043b) {
                if (this.inverse == null) {
                    this.inverse = new SynchronizedBiMap(b().inverse(), this.f9043b, this);
                }
                biMap = this.inverse;
            }
            return biMap;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMap, java.util.Map
        public Set<V> values() {
            Set<V> set;
            synchronized (this.f9043b) {
                if (this.valueSet == null) {
                    this.valueSet = Synchronized.r(b().values(), this.f9043b);
                }
                set = this.valueSet;
            }
            return set;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes2.dex */
    public static class SynchronizedCollection<E> extends SynchronizedObject implements Collection<E> {
        private static final long serialVersionUID = 0;

        private SynchronizedCollection(Collection<E> collection, @NullableDecl Object obj) {
            super(collection, obj);
        }

        @Override // java.util.Collection
        public boolean add(E e2) {
            boolean add;
            synchronized (this.f9043b) {
                add = b().add(e2);
            }
            return add;
        }

        @Override // java.util.Collection
        public boolean addAll(Collection<? extends E> collection) {
            boolean addAll;
            synchronized (this.f9043b) {
                addAll = b().addAll(collection);
            }
            return addAll;
        }

        Collection b() {
            return (Collection) super.a();
        }

        @Override // java.util.Collection
        public void clear() {
            synchronized (this.f9043b) {
                b().clear();
            }
        }

        public boolean contains(Object obj) {
            boolean contains;
            synchronized (this.f9043b) {
                contains = b().contains(obj);
            }
            return contains;
        }

        public boolean containsAll(Collection<?> collection) {
            boolean containsAll;
            synchronized (this.f9043b) {
                containsAll = b().containsAll(collection);
            }
            return containsAll;
        }

        @Override // java.util.Collection
        public boolean isEmpty() {
            boolean isEmpty;
            synchronized (this.f9043b) {
                isEmpty = b().isEmpty();
            }
            return isEmpty;
        }

        public Iterator<E> iterator() {
            return b().iterator();
        }

        public boolean remove(Object obj) {
            boolean remove;
            synchronized (this.f9043b) {
                remove = b().remove(obj);
            }
            return remove;
        }

        public boolean removeAll(Collection<?> collection) {
            boolean removeAll;
            synchronized (this.f9043b) {
                removeAll = b().removeAll(collection);
            }
            return removeAll;
        }

        public boolean retainAll(Collection<?> collection) {
            boolean retainAll;
            synchronized (this.f9043b) {
                retainAll = b().retainAll(collection);
            }
            return retainAll;
        }

        @Override // java.util.Collection
        public int size() {
            int size;
            synchronized (this.f9043b) {
                size = b().size();
            }
            return size;
        }

        public Object[] toArray() {
            Object[] array;
            synchronized (this.f9043b) {
                array = b().toArray();
            }
            return array;
        }

        public <T> T[] toArray(T[] tArr) {
            T[] tArr2;
            synchronized (this.f9043b) {
                tArr2 = (T[]) b().toArray(tArr);
            }
            return tArr2;
        }
    }

    /* loaded from: classes2.dex */
    private static final class SynchronizedDeque<E> extends SynchronizedQueue<E> implements Deque<E> {
        private static final long serialVersionUID = 0;

        SynchronizedDeque(Deque deque, @NullableDecl Object obj) {
            super(deque, obj);
        }

        @Override // java.util.Deque
        public void addFirst(E e2) {
            synchronized (this.f9043b) {
                c().addFirst(e2);
            }
        }

        @Override // java.util.Deque
        public void addLast(E e2) {
            synchronized (this.f9043b) {
                c().addLast(e2);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.Synchronized.SynchronizedQueue
        /* renamed from: d */
        public Deque c() {
            return (Deque) super.b();
        }

        @Override // java.util.Deque
        public Iterator<E> descendingIterator() {
            Iterator<E> descendingIterator;
            synchronized (this.f9043b) {
                descendingIterator = c().descendingIterator();
            }
            return descendingIterator;
        }

        @Override // java.util.Deque
        public E getFirst() {
            E e2;
            synchronized (this.f9043b) {
                e2 = (E) c().getFirst();
            }
            return e2;
        }

        @Override // java.util.Deque
        public E getLast() {
            E e2;
            synchronized (this.f9043b) {
                e2 = (E) c().getLast();
            }
            return e2;
        }

        @Override // java.util.Deque
        public boolean offerFirst(E e2) {
            boolean offerFirst;
            synchronized (this.f9043b) {
                offerFirst = c().offerFirst(e2);
            }
            return offerFirst;
        }

        @Override // java.util.Deque
        public boolean offerLast(E e2) {
            boolean offerLast;
            synchronized (this.f9043b) {
                offerLast = c().offerLast(e2);
            }
            return offerLast;
        }

        @Override // java.util.Deque
        public E peekFirst() {
            E e2;
            synchronized (this.f9043b) {
                e2 = (E) c().peekFirst();
            }
            return e2;
        }

        @Override // java.util.Deque
        public E peekLast() {
            E e2;
            synchronized (this.f9043b) {
                e2 = (E) c().peekLast();
            }
            return e2;
        }

        @Override // java.util.Deque
        public E pollFirst() {
            E e2;
            synchronized (this.f9043b) {
                e2 = (E) c().pollFirst();
            }
            return e2;
        }

        @Override // java.util.Deque
        public E pollLast() {
            E e2;
            synchronized (this.f9043b) {
                e2 = (E) c().pollLast();
            }
            return e2;
        }

        @Override // java.util.Deque
        public E pop() {
            E e2;
            synchronized (this.f9043b) {
                e2 = (E) c().pop();
            }
            return e2;
        }

        @Override // java.util.Deque
        public void push(E e2) {
            synchronized (this.f9043b) {
                c().push(e2);
            }
        }

        @Override // java.util.Deque
        public E removeFirst() {
            E e2;
            synchronized (this.f9043b) {
                e2 = (E) c().removeFirst();
            }
            return e2;
        }

        @Override // java.util.Deque
        public boolean removeFirstOccurrence(Object obj) {
            boolean removeFirstOccurrence;
            synchronized (this.f9043b) {
                removeFirstOccurrence = c().removeFirstOccurrence(obj);
            }
            return removeFirstOccurrence;
        }

        @Override // java.util.Deque
        public E removeLast() {
            E e2;
            synchronized (this.f9043b) {
                e2 = (E) c().removeLast();
            }
            return e2;
        }

        @Override // java.util.Deque
        public boolean removeLastOccurrence(Object obj) {
            boolean removeLastOccurrence;
            synchronized (this.f9043b) {
                removeLastOccurrence = c().removeLastOccurrence(obj);
            }
            return removeLastOccurrence;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @GwtIncompatible
    /* loaded from: classes2.dex */
    public static class SynchronizedEntry<K, V> extends SynchronizedObject implements Map.Entry<K, V> {
        private static final long serialVersionUID = 0;

        SynchronizedEntry(Map.Entry entry, @NullableDecl Object obj) {
            super(entry, obj);
        }

        Map.Entry b() {
            return (Map.Entry) super.a();
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            boolean equals;
            synchronized (this.f9043b) {
                equals = b().equals(obj);
            }
            return equals;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            K k2;
            synchronized (this.f9043b) {
                k2 = (K) b().getKey();
            }
            return k2;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            V v;
            synchronized (this.f9043b) {
                v = (V) b().getValue();
            }
            return v;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            int hashCode;
            synchronized (this.f9043b) {
                hashCode = b().hashCode();
            }
            return hashCode;
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            V v2;
            synchronized (this.f9043b) {
                v2 = (V) b().setValue(v);
            }
            return v2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class SynchronizedList<E> extends SynchronizedCollection<E> implements List<E> {
        private static final long serialVersionUID = 0;

        SynchronizedList(List list, @NullableDecl Object obj) {
            super(list, obj);
        }

        @Override // java.util.List
        public void add(int i2, E e2) {
            synchronized (this.f9043b) {
                b().add(i2, e2);
            }
        }

        @Override // java.util.List
        public boolean addAll(int i2, Collection<? extends E> collection) {
            boolean addAll;
            synchronized (this.f9043b) {
                addAll = b().addAll(i2, collection);
            }
            return addAll;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.Synchronized.SynchronizedCollection
        /* renamed from: c */
        public List b() {
            return (List) super.b();
        }

        @Override // java.util.Collection, java.util.List
        public boolean equals(Object obj) {
            boolean equals;
            if (obj == this) {
                return true;
            }
            synchronized (this.f9043b) {
                equals = b().equals(obj);
            }
            return equals;
        }

        @Override // java.util.List
        public E get(int i2) {
            E e2;
            synchronized (this.f9043b) {
                e2 = (E) b().get(i2);
            }
            return e2;
        }

        @Override // java.util.Collection, java.util.List
        public int hashCode() {
            int hashCode;
            synchronized (this.f9043b) {
                hashCode = b().hashCode();
            }
            return hashCode;
        }

        @Override // java.util.List
        public int indexOf(Object obj) {
            int indexOf;
            synchronized (this.f9043b) {
                indexOf = b().indexOf(obj);
            }
            return indexOf;
        }

        @Override // java.util.List
        public int lastIndexOf(Object obj) {
            int lastIndexOf;
            synchronized (this.f9043b) {
                lastIndexOf = b().lastIndexOf(obj);
            }
            return lastIndexOf;
        }

        @Override // java.util.List
        public ListIterator<E> listIterator() {
            return b().listIterator();
        }

        @Override // java.util.List
        public ListIterator<E> listIterator(int i2) {
            return b().listIterator(i2);
        }

        @Override // java.util.List
        public E remove(int i2) {
            E e2;
            synchronized (this.f9043b) {
                e2 = (E) b().remove(i2);
            }
            return e2;
        }

        @Override // java.util.List
        public E set(int i2, E e2) {
            E e3;
            synchronized (this.f9043b) {
                e3 = (E) b().set(i2, e2);
            }
            return e3;
        }

        @Override // java.util.List
        public List<E> subList(int i2, int i3) {
            List<E> list;
            synchronized (this.f9043b) {
                list = Synchronized.list(b().subList(i2, i3), this.f9043b);
            }
            return list;
        }
    }

    /* loaded from: classes2.dex */
    private static class SynchronizedListMultimap<K, V> extends SynchronizedMultimap<K, V> implements ListMultimap<K, V> {
        private static final long serialVersionUID = 0;

        SynchronizedListMultimap(ListMultimap listMultimap, @NullableDecl Object obj) {
            super(listMultimap, obj);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.Synchronized.SynchronizedMultimap
        /* renamed from: c */
        public ListMultimap b() {
            return (ListMultimap) super.b();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public /* bridge */ /* synthetic */ Collection get(Object obj) {
            return get((SynchronizedListMultimap<K, V>) obj);
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public List<V> get(K k2) {
            List<V> list;
            synchronized (this.f9043b) {
                list = Synchronized.list(b().get((ListMultimap) k2), this.f9043b);
            }
            return list;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public List<V> removeAll(Object obj) {
            List<V> removeAll;
            synchronized (this.f9043b) {
                removeAll = b().removeAll(obj);
            }
            return removeAll;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public /* bridge */ /* synthetic */ Collection replaceValues(Object obj, Iterable iterable) {
            return replaceValues((SynchronizedListMultimap<K, V>) obj, iterable);
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public List<V> replaceValues(K k2, Iterable<? extends V> iterable) {
            List<V> replaceValues;
            synchronized (this.f9043b) {
                replaceValues = b().replaceValues((ListMultimap) k2, (Iterable) iterable);
            }
            return replaceValues;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class SynchronizedMap<K, V> extends SynchronizedObject implements Map<K, V> {
        private static final long serialVersionUID = 0;
        @NullableDecl

        /* renamed from: c  reason: collision with root package name */
        transient Set f9028c;
        @NullableDecl

        /* renamed from: d  reason: collision with root package name */
        transient Collection f9029d;
        @NullableDecl

        /* renamed from: e  reason: collision with root package name */
        transient Set f9030e;

        SynchronizedMap(Map map, @NullableDecl Object obj) {
            super(map, obj);
        }

        Map b() {
            return (Map) super.a();
        }

        @Override // java.util.Map
        public void clear() {
            synchronized (this.f9043b) {
                b().clear();
            }
        }

        @Override // java.util.Map
        public boolean containsKey(Object obj) {
            boolean containsKey;
            synchronized (this.f9043b) {
                containsKey = b().containsKey(obj);
            }
            return containsKey;
        }

        public boolean containsValue(Object obj) {
            boolean containsValue;
            synchronized (this.f9043b) {
                containsValue = b().containsValue(obj);
            }
            return containsValue;
        }

        public Set<Map.Entry<K, V>> entrySet() {
            Set<Map.Entry<K, V>> set;
            synchronized (this.f9043b) {
                if (this.f9030e == null) {
                    this.f9030e = Synchronized.r(b().entrySet(), this.f9043b);
                }
                set = this.f9030e;
            }
            return set;
        }

        @Override // java.util.Map
        public boolean equals(Object obj) {
            boolean equals;
            if (obj == this) {
                return true;
            }
            synchronized (this.f9043b) {
                equals = b().equals(obj);
            }
            return equals;
        }

        public V get(Object obj) {
            V v;
            synchronized (this.f9043b) {
                v = (V) b().get(obj);
            }
            return v;
        }

        @Override // java.util.Map
        public int hashCode() {
            int hashCode;
            synchronized (this.f9043b) {
                hashCode = b().hashCode();
            }
            return hashCode;
        }

        @Override // java.util.Map
        public boolean isEmpty() {
            boolean isEmpty;
            synchronized (this.f9043b) {
                isEmpty = b().isEmpty();
            }
            return isEmpty;
        }

        @Override // java.util.Map
        public Set<K> keySet() {
            Set<K> set;
            synchronized (this.f9043b) {
                if (this.f9028c == null) {
                    this.f9028c = Synchronized.r(b().keySet(), this.f9043b);
                }
                set = this.f9028c;
            }
            return set;
        }

        @Override // java.util.Map
        public V put(K k2, V v) {
            V v2;
            synchronized (this.f9043b) {
                v2 = (V) b().put(k2, v);
            }
            return v2;
        }

        @Override // java.util.Map
        public void putAll(Map<? extends K, ? extends V> map) {
            synchronized (this.f9043b) {
                b().putAll(map);
            }
        }

        @Override // java.util.Map
        public V remove(Object obj) {
            V v;
            synchronized (this.f9043b) {
                v = (V) b().remove(obj);
            }
            return v;
        }

        @Override // java.util.Map
        public int size() {
            int size;
            synchronized (this.f9043b) {
                size = b().size();
            }
            return size;
        }

        public Collection<V> values() {
            Collection<V> collection;
            synchronized (this.f9043b) {
                if (this.f9029d == null) {
                    this.f9029d = Synchronized.collection(b().values(), this.f9043b);
                }
                collection = this.f9029d;
            }
            return collection;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class SynchronizedMultimap<K, V> extends SynchronizedObject implements Multimap<K, V> {
        private static final long serialVersionUID = 0;
        @NullableDecl

        /* renamed from: c  reason: collision with root package name */
        transient Set f9031c;
        @NullableDecl

        /* renamed from: d  reason: collision with root package name */
        transient Collection f9032d;
        @NullableDecl

        /* renamed from: e  reason: collision with root package name */
        transient Collection f9033e;
        @NullableDecl

        /* renamed from: f  reason: collision with root package name */
        transient Map f9034f;
        @NullableDecl

        /* renamed from: g  reason: collision with root package name */
        transient Multiset f9035g;

        SynchronizedMultimap(Multimap multimap, @NullableDecl Object obj) {
            super(multimap, obj);
        }

        @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public Map<K, Collection<V>> asMap() {
            Map<K, Collection<V>> map;
            synchronized (this.f9043b) {
                if (this.f9034f == null) {
                    this.f9034f = new SynchronizedAsMap(b().asMap(), this.f9043b);
                }
                map = this.f9034f;
            }
            return map;
        }

        Multimap b() {
            return (Multimap) super.a();
        }

        @Override // com.google.common.collect.Multimap
        public void clear() {
            synchronized (this.f9043b) {
                b().clear();
            }
        }

        @Override // com.google.common.collect.Multimap
        public boolean containsEntry(Object obj, Object obj2) {
            boolean containsEntry;
            synchronized (this.f9043b) {
                containsEntry = b().containsEntry(obj, obj2);
            }
            return containsEntry;
        }

        @Override // com.google.common.collect.Multimap
        public boolean containsKey(Object obj) {
            boolean containsKey;
            synchronized (this.f9043b) {
                containsKey = b().containsKey(obj);
            }
            return containsKey;
        }

        @Override // com.google.common.collect.Multimap
        public boolean containsValue(Object obj) {
            boolean containsValue;
            synchronized (this.f9043b) {
                containsValue = b().containsValue(obj);
            }
            return containsValue;
        }

        @Override // com.google.common.collect.Multimap
        public Collection<Map.Entry<K, V>> entries() {
            Collection<Map.Entry<K, V>> collection;
            synchronized (this.f9043b) {
                if (this.f9033e == null) {
                    this.f9033e = Synchronized.typePreservingCollection(b().entries(), this.f9043b);
                }
                collection = this.f9033e;
            }
            return collection;
        }

        @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public boolean equals(Object obj) {
            boolean equals;
            if (obj == this) {
                return true;
            }
            synchronized (this.f9043b) {
                equals = b().equals(obj);
            }
            return equals;
        }

        public Collection<V> get(K k2) {
            Collection<V> typePreservingCollection;
            synchronized (this.f9043b) {
                typePreservingCollection = Synchronized.typePreservingCollection(b().get(k2), this.f9043b);
            }
            return typePreservingCollection;
        }

        @Override // com.google.common.collect.Multimap
        public int hashCode() {
            int hashCode;
            synchronized (this.f9043b) {
                hashCode = b().hashCode();
            }
            return hashCode;
        }

        @Override // com.google.common.collect.Multimap
        public boolean isEmpty() {
            boolean isEmpty;
            synchronized (this.f9043b) {
                isEmpty = b().isEmpty();
            }
            return isEmpty;
        }

        @Override // com.google.common.collect.Multimap
        public Set<K> keySet() {
            Set<K> set;
            synchronized (this.f9043b) {
                if (this.f9031c == null) {
                    this.f9031c = Synchronized.typePreservingSet(b().keySet(), this.f9043b);
                }
                set = this.f9031c;
            }
            return set;
        }

        @Override // com.google.common.collect.Multimap
        public Multiset<K> keys() {
            Multiset<K> multiset;
            synchronized (this.f9043b) {
                if (this.f9035g == null) {
                    this.f9035g = Synchronized.l(b().keys(), this.f9043b);
                }
                multiset = this.f9035g;
            }
            return multiset;
        }

        @Override // com.google.common.collect.Multimap
        public boolean put(K k2, V v) {
            boolean put;
            synchronized (this.f9043b) {
                put = b().put(k2, v);
            }
            return put;
        }

        @Override // com.google.common.collect.Multimap
        public boolean putAll(Multimap<? extends K, ? extends V> multimap) {
            boolean putAll;
            synchronized (this.f9043b) {
                putAll = b().putAll(multimap);
            }
            return putAll;
        }

        @Override // com.google.common.collect.Multimap
        public boolean putAll(K k2, Iterable<? extends V> iterable) {
            boolean putAll;
            synchronized (this.f9043b) {
                putAll = b().putAll(k2, iterable);
            }
            return putAll;
        }

        @Override // com.google.common.collect.Multimap
        public boolean remove(Object obj, Object obj2) {
            boolean remove;
            synchronized (this.f9043b) {
                remove = b().remove(obj, obj2);
            }
            return remove;
        }

        public Collection<V> removeAll(Object obj) {
            Collection<V> removeAll;
            synchronized (this.f9043b) {
                removeAll = b().removeAll(obj);
            }
            return removeAll;
        }

        public Collection<V> replaceValues(K k2, Iterable<? extends V> iterable) {
            Collection<V> replaceValues;
            synchronized (this.f9043b) {
                replaceValues = b().replaceValues(k2, iterable);
            }
            return replaceValues;
        }

        @Override // com.google.common.collect.Multimap
        public int size() {
            int size;
            synchronized (this.f9043b) {
                size = b().size();
            }
            return size;
        }

        @Override // com.google.common.collect.Multimap
        public Collection<V> values() {
            Collection<V> collection;
            synchronized (this.f9043b) {
                if (this.f9032d == null) {
                    this.f9032d = Synchronized.collection(b().values(), this.f9043b);
                }
                collection = this.f9032d;
            }
            return collection;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class SynchronizedMultiset<E> extends SynchronizedCollection<E> implements Multiset<E> {
        private static final long serialVersionUID = 0;
        @NullableDecl

        /* renamed from: c  reason: collision with root package name */
        transient Set f9036c;
        @NullableDecl

        /* renamed from: d  reason: collision with root package name */
        transient Set f9037d;

        SynchronizedMultiset(Multiset multiset, @NullableDecl Object obj) {
            super(multiset, obj);
        }

        @Override // com.google.common.collect.Multiset
        public int add(E e2, int i2) {
            int add;
            synchronized (this.f9043b) {
                add = b().add(e2, i2);
            }
            return add;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.Synchronized.SynchronizedCollection
        /* renamed from: c */
        public Multiset b() {
            return (Multiset) super.b();
        }

        @Override // com.google.common.collect.Multiset
        public int count(Object obj) {
            int count;
            synchronized (this.f9043b) {
                count = b().count(obj);
            }
            return count;
        }

        @Override // com.google.common.collect.Multiset
        public Set<E> elementSet() {
            Set<E> set;
            synchronized (this.f9043b) {
                if (this.f9036c == null) {
                    this.f9036c = Synchronized.typePreservingSet(b().elementSet(), this.f9043b);
                }
                set = this.f9036c;
            }
            return set;
        }

        @Override // com.google.common.collect.Multiset
        public Set<Multiset.Entry<E>> entrySet() {
            Set<Multiset.Entry<E>> set;
            synchronized (this.f9043b) {
                if (this.f9037d == null) {
                    this.f9037d = Synchronized.typePreservingSet(b().entrySet(), this.f9043b);
                }
                set = this.f9037d;
            }
            return set;
        }

        @Override // java.util.Collection, com.google.common.collect.Multiset
        public boolean equals(Object obj) {
            boolean equals;
            if (obj == this) {
                return true;
            }
            synchronized (this.f9043b) {
                equals = b().equals(obj);
            }
            return equals;
        }

        @Override // java.util.Collection, com.google.common.collect.Multiset
        public int hashCode() {
            int hashCode;
            synchronized (this.f9043b) {
                hashCode = b().hashCode();
            }
            return hashCode;
        }

        @Override // com.google.common.collect.Multiset
        public int remove(Object obj, int i2) {
            int remove;
            synchronized (this.f9043b) {
                remove = b().remove(obj, i2);
            }
            return remove;
        }

        @Override // com.google.common.collect.Multiset
        public int setCount(E e2, int i2) {
            int count;
            synchronized (this.f9043b) {
                count = b().setCount(e2, i2);
            }
            return count;
        }

        @Override // com.google.common.collect.Multiset
        public boolean setCount(E e2, int i2, int i3) {
            boolean count;
            synchronized (this.f9043b) {
                count = b().setCount(e2, i2, i3);
            }
            return count;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @GwtIncompatible
    @VisibleForTesting
    /* loaded from: classes2.dex */
    public static class SynchronizedNavigableMap<K, V> extends SynchronizedSortedMap<K, V> implements NavigableMap<K, V> {
        private static final long serialVersionUID = 0;
        @NullableDecl

        /* renamed from: f  reason: collision with root package name */
        transient NavigableSet f9038f;
        @NullableDecl

        /* renamed from: g  reason: collision with root package name */
        transient NavigableMap f9039g;
        @NullableDecl

        /* renamed from: h  reason: collision with root package name */
        transient NavigableSet f9040h;

        SynchronizedNavigableMap(NavigableMap navigableMap, @NullableDecl Object obj) {
            super(navigableMap, obj);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> ceilingEntry(K k2) {
            Map.Entry<K, V> nullableSynchronizedEntry;
            synchronized (this.f9043b) {
                nullableSynchronizedEntry = Synchronized.nullableSynchronizedEntry(c().ceilingEntry(k2), this.f9043b);
            }
            return nullableSynchronizedEntry;
        }

        @Override // java.util.NavigableMap
        public K ceilingKey(K k2) {
            K k3;
            synchronized (this.f9043b) {
                k3 = (K) c().ceilingKey(k2);
            }
            return k3;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.Synchronized.SynchronizedSortedMap
        /* renamed from: d */
        public NavigableMap c() {
            return (NavigableMap) super.b();
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> descendingKeySet() {
            synchronized (this.f9043b) {
                NavigableSet<K> navigableSet = this.f9038f;
                if (navigableSet == null) {
                    NavigableSet<K> p2 = Synchronized.p(c().descendingKeySet(), this.f9043b);
                    this.f9038f = p2;
                    return p2;
                }
                return navigableSet;
            }
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> descendingMap() {
            synchronized (this.f9043b) {
                NavigableMap<K, V> navigableMap = this.f9039g;
                if (navigableMap == null) {
                    NavigableMap<K, V> n2 = Synchronized.n(c().descendingMap(), this.f9043b);
                    this.f9039g = n2;
                    return n2;
                }
                return navigableMap;
            }
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> firstEntry() {
            Map.Entry<K, V> nullableSynchronizedEntry;
            synchronized (this.f9043b) {
                nullableSynchronizedEntry = Synchronized.nullableSynchronizedEntry(c().firstEntry(), this.f9043b);
            }
            return nullableSynchronizedEntry;
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> floorEntry(K k2) {
            Map.Entry<K, V> nullableSynchronizedEntry;
            synchronized (this.f9043b) {
                nullableSynchronizedEntry = Synchronized.nullableSynchronizedEntry(c().floorEntry(k2), this.f9043b);
            }
            return nullableSynchronizedEntry;
        }

        @Override // java.util.NavigableMap
        public K floorKey(K k2) {
            K k3;
            synchronized (this.f9043b) {
                k3 = (K) c().floorKey(k2);
            }
            return k3;
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> headMap(K k2, boolean z) {
            NavigableMap<K, V> n2;
            synchronized (this.f9043b) {
                n2 = Synchronized.n(c().headMap(k2, z), this.f9043b);
            }
            return n2;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedSortedMap, java.util.SortedMap, java.util.NavigableMap
        public SortedMap<K, V> headMap(K k2) {
            return headMap(k2, false);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> higherEntry(K k2) {
            Map.Entry<K, V> nullableSynchronizedEntry;
            synchronized (this.f9043b) {
                nullableSynchronizedEntry = Synchronized.nullableSynchronizedEntry(c().higherEntry(k2), this.f9043b);
            }
            return nullableSynchronizedEntry;
        }

        @Override // java.util.NavigableMap
        public K higherKey(K k2) {
            K k3;
            synchronized (this.f9043b) {
                k3 = (K) c().higherKey(k2);
            }
            return k3;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMap, java.util.Map
        public Set<K> keySet() {
            return navigableKeySet();
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> lastEntry() {
            Map.Entry<K, V> nullableSynchronizedEntry;
            synchronized (this.f9043b) {
                nullableSynchronizedEntry = Synchronized.nullableSynchronizedEntry(c().lastEntry(), this.f9043b);
            }
            return nullableSynchronizedEntry;
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> lowerEntry(K k2) {
            Map.Entry<K, V> nullableSynchronizedEntry;
            synchronized (this.f9043b) {
                nullableSynchronizedEntry = Synchronized.nullableSynchronizedEntry(c().lowerEntry(k2), this.f9043b);
            }
            return nullableSynchronizedEntry;
        }

        @Override // java.util.NavigableMap
        public K lowerKey(K k2) {
            K k3;
            synchronized (this.f9043b) {
                k3 = (K) c().lowerKey(k2);
            }
            return k3;
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> navigableKeySet() {
            synchronized (this.f9043b) {
                NavigableSet<K> navigableSet = this.f9040h;
                if (navigableSet == null) {
                    NavigableSet<K> p2 = Synchronized.p(c().navigableKeySet(), this.f9043b);
                    this.f9040h = p2;
                    return p2;
                }
                return navigableSet;
            }
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> pollFirstEntry() {
            Map.Entry<K, V> nullableSynchronizedEntry;
            synchronized (this.f9043b) {
                nullableSynchronizedEntry = Synchronized.nullableSynchronizedEntry(c().pollFirstEntry(), this.f9043b);
            }
            return nullableSynchronizedEntry;
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> pollLastEntry() {
            Map.Entry<K, V> nullableSynchronizedEntry;
            synchronized (this.f9043b) {
                nullableSynchronizedEntry = Synchronized.nullableSynchronizedEntry(c().pollLastEntry(), this.f9043b);
            }
            return nullableSynchronizedEntry;
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> subMap(K k2, boolean z, K k3, boolean z2) {
            NavigableMap<K, V> n2;
            synchronized (this.f9043b) {
                n2 = Synchronized.n(c().subMap(k2, z, k3, z2), this.f9043b);
            }
            return n2;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedSortedMap, java.util.SortedMap, java.util.NavigableMap
        public SortedMap<K, V> subMap(K k2, K k3) {
            return subMap(k2, true, k3, false);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> tailMap(K k2, boolean z) {
            NavigableMap<K, V> n2;
            synchronized (this.f9043b) {
                n2 = Synchronized.n(c().tailMap(k2, z), this.f9043b);
            }
            return n2;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedSortedMap, java.util.SortedMap, java.util.NavigableMap
        public SortedMap<K, V> tailMap(K k2) {
            return tailMap(k2, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @GwtIncompatible
    @VisibleForTesting
    /* loaded from: classes2.dex */
    public static class SynchronizedNavigableSet<E> extends SynchronizedSortedSet<E> implements NavigableSet<E> {
        private static final long serialVersionUID = 0;
        @NullableDecl

        /* renamed from: c  reason: collision with root package name */
        transient NavigableSet f9041c;

        SynchronizedNavigableSet(NavigableSet navigableSet, @NullableDecl Object obj) {
            super(navigableSet, obj);
        }

        @Override // java.util.NavigableSet
        public E ceiling(E e2) {
            E e3;
            synchronized (this.f9043b) {
                e3 = (E) d().ceiling(e2);
            }
            return e3;
        }

        @Override // java.util.NavigableSet
        public Iterator<E> descendingIterator() {
            return d().descendingIterator();
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> descendingSet() {
            synchronized (this.f9043b) {
                NavigableSet<E> navigableSet = this.f9041c;
                if (navigableSet == null) {
                    NavigableSet<E> p2 = Synchronized.p(d().descendingSet(), this.f9043b);
                    this.f9041c = p2;
                    return p2;
                }
                return navigableSet;
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.Synchronized.SynchronizedSortedSet
        /* renamed from: e */
        public NavigableSet d() {
            return (NavigableSet) super.c();
        }

        @Override // java.util.NavigableSet
        public E floor(E e2) {
            E e3;
            synchronized (this.f9043b) {
                e3 = (E) d().floor(e2);
            }
            return e3;
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> headSet(E e2, boolean z) {
            NavigableSet<E> p2;
            synchronized (this.f9043b) {
                p2 = Synchronized.p(d().headSet(e2, z), this.f9043b);
            }
            return p2;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedSortedSet, java.util.SortedSet, java.util.NavigableSet
        public SortedSet<E> headSet(E e2) {
            return headSet(e2, false);
        }

        @Override // java.util.NavigableSet
        public E higher(E e2) {
            E e3;
            synchronized (this.f9043b) {
                e3 = (E) d().higher(e2);
            }
            return e3;
        }

        @Override // java.util.NavigableSet
        public E lower(E e2) {
            E e3;
            synchronized (this.f9043b) {
                e3 = (E) d().lower(e2);
            }
            return e3;
        }

        @Override // java.util.NavigableSet
        public E pollFirst() {
            E e2;
            synchronized (this.f9043b) {
                e2 = (E) d().pollFirst();
            }
            return e2;
        }

        @Override // java.util.NavigableSet
        public E pollLast() {
            E e2;
            synchronized (this.f9043b) {
                e2 = (E) d().pollLast();
            }
            return e2;
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> subSet(E e2, boolean z, E e3, boolean z2) {
            NavigableSet<E> p2;
            synchronized (this.f9043b) {
                p2 = Synchronized.p(d().subSet(e2, z, e3, z2), this.f9043b);
            }
            return p2;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedSortedSet, java.util.SortedSet, java.util.NavigableSet
        public SortedSet<E> subSet(E e2, E e3) {
            return subSet(e2, true, e3, false);
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> tailSet(E e2, boolean z) {
            NavigableSet<E> p2;
            synchronized (this.f9043b) {
                p2 = Synchronized.p(d().tailSet(e2, z), this.f9043b);
            }
            return p2;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedSortedSet, java.util.SortedSet, java.util.NavigableSet
        public SortedSet<E> tailSet(E e2) {
            return tailSet(e2, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class SynchronizedObject implements Serializable {
        @GwtIncompatible
        private static final long serialVersionUID = 0;

        /* renamed from: a  reason: collision with root package name */
        final Object f9042a;

        /* renamed from: b  reason: collision with root package name */
        final Object f9043b;

        SynchronizedObject(Object obj, @NullableDecl Object obj2) {
            this.f9042a = Preconditions.checkNotNull(obj);
            this.f9043b = obj2 == null ? this : obj2;
        }

        @GwtIncompatible
        private void writeObject(ObjectOutputStream objectOutputStream) {
            synchronized (this.f9043b) {
                objectOutputStream.defaultWriteObject();
            }
        }

        Object a() {
            return this.f9042a;
        }

        public String toString() {
            String obj;
            synchronized (this.f9043b) {
                obj = this.f9042a.toString();
            }
            return obj;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class SynchronizedQueue<E> extends SynchronizedCollection<E> implements Queue<E> {
        private static final long serialVersionUID = 0;

        SynchronizedQueue(Queue queue, @NullableDecl Object obj) {
            super(queue, obj);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.Synchronized.SynchronizedCollection
        /* renamed from: c */
        public Queue b() {
            return (Queue) super.b();
        }

        @Override // java.util.Queue
        public E element() {
            E e2;
            synchronized (this.f9043b) {
                e2 = (E) b().element();
            }
            return e2;
        }

        @Override // java.util.Queue
        public boolean offer(E e2) {
            boolean offer;
            synchronized (this.f9043b) {
                offer = b().offer(e2);
            }
            return offer;
        }

        @Override // java.util.Queue
        public E peek() {
            E e2;
            synchronized (this.f9043b) {
                e2 = (E) b().peek();
            }
            return e2;
        }

        @Override // java.util.Queue
        public E poll() {
            E e2;
            synchronized (this.f9043b) {
                e2 = (E) b().poll();
            }
            return e2;
        }

        @Override // java.util.Queue
        public E remove() {
            E e2;
            synchronized (this.f9043b) {
                e2 = (E) b().remove();
            }
            return e2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class SynchronizedRandomAccessList<E> extends SynchronizedList<E> implements RandomAccess {
        private static final long serialVersionUID = 0;

        SynchronizedRandomAccessList(List list, @NullableDecl Object obj) {
            super(list, obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class SynchronizedSet<E> extends SynchronizedCollection<E> implements Set<E> {
        private static final long serialVersionUID = 0;

        SynchronizedSet(Set set, @NullableDecl Object obj) {
            super(set, obj);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.Synchronized.SynchronizedCollection
        /* renamed from: c */
        public Set b() {
            return (Set) super.b();
        }

        public boolean equals(Object obj) {
            boolean equals;
            if (obj == this) {
                return true;
            }
            synchronized (this.f9043b) {
                equals = b().equals(obj);
            }
            return equals;
        }

        @Override // java.util.Collection, java.util.Set
        public int hashCode() {
            int hashCode;
            synchronized (this.f9043b) {
                hashCode = b().hashCode();
            }
            return hashCode;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class SynchronizedSetMultimap<K, V> extends SynchronizedMultimap<K, V> implements SetMultimap<K, V> {
        private static final long serialVersionUID = 0;
        @NullableDecl

        /* renamed from: h  reason: collision with root package name */
        transient Set f9044h;

        SynchronizedSetMultimap(SetMultimap setMultimap, @NullableDecl Object obj) {
            super(setMultimap, obj);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.Synchronized.SynchronizedMultimap
        /* renamed from: c */
        public SetMultimap b() {
            return (SetMultimap) super.b();
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Multimap
        public Set<Map.Entry<K, V>> entries() {
            Set<Map.Entry<K, V>> set;
            synchronized (this.f9043b) {
                if (this.f9044h == null) {
                    this.f9044h = Synchronized.r(b().entries(), this.f9043b);
                }
                set = this.f9044h;
            }
            return set;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public /* bridge */ /* synthetic */ Collection get(Object obj) {
            return get((SynchronizedSetMultimap<K, V>) obj);
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public Set<V> get(K k2) {
            Set<V> r2;
            synchronized (this.f9043b) {
                r2 = Synchronized.r(b().get((SetMultimap) k2), this.f9043b);
            }
            return r2;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public Set<V> removeAll(Object obj) {
            Set<V> removeAll;
            synchronized (this.f9043b) {
                removeAll = b().removeAll(obj);
            }
            return removeAll;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public /* bridge */ /* synthetic */ Collection replaceValues(Object obj, Iterable iterable) {
            return replaceValues((SynchronizedSetMultimap<K, V>) obj, iterable);
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public Set<V> replaceValues(K k2, Iterable<? extends V> iterable) {
            Set<V> replaceValues;
            synchronized (this.f9043b) {
                replaceValues = b().replaceValues((SetMultimap) k2, (Iterable) iterable);
            }
            return replaceValues;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class SynchronizedSortedMap<K, V> extends SynchronizedMap<K, V> implements SortedMap<K, V> {
        private static final long serialVersionUID = 0;

        SynchronizedSortedMap(SortedMap sortedMap, @NullableDecl Object obj) {
            super(sortedMap, obj);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.Synchronized.SynchronizedMap
        /* renamed from: c */
        public SortedMap b() {
            return (SortedMap) super.b();
        }

        @Override // java.util.SortedMap
        public Comparator<? super K> comparator() {
            Comparator<? super K> comparator;
            synchronized (this.f9043b) {
                comparator = b().comparator();
            }
            return comparator;
        }

        @Override // java.util.SortedMap
        public K firstKey() {
            K k2;
            synchronized (this.f9043b) {
                k2 = (K) b().firstKey();
            }
            return k2;
        }

        public SortedMap<K, V> headMap(K k2) {
            SortedMap<K, V> t2;
            synchronized (this.f9043b) {
                t2 = Synchronized.t(b().headMap(k2), this.f9043b);
            }
            return t2;
        }

        @Override // java.util.SortedMap
        public K lastKey() {
            K k2;
            synchronized (this.f9043b) {
                k2 = (K) b().lastKey();
            }
            return k2;
        }

        public SortedMap<K, V> subMap(K k2, K k3) {
            SortedMap<K, V> t2;
            synchronized (this.f9043b) {
                t2 = Synchronized.t(b().subMap(k2, k3), this.f9043b);
            }
            return t2;
        }

        public SortedMap<K, V> tailMap(K k2) {
            SortedMap<K, V> t2;
            synchronized (this.f9043b) {
                t2 = Synchronized.t(b().tailMap(k2), this.f9043b);
            }
            return t2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class SynchronizedSortedSet<E> extends SynchronizedSet<E> implements SortedSet<E> {
        private static final long serialVersionUID = 0;

        SynchronizedSortedSet(SortedSet sortedSet, @NullableDecl Object obj) {
            super(sortedSet, obj);
        }

        @Override // java.util.SortedSet
        public Comparator<? super E> comparator() {
            Comparator<? super E> comparator;
            synchronized (this.f9043b) {
                comparator = c().comparator();
            }
            return comparator;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.Synchronized.SynchronizedSet
        /* renamed from: d */
        public SortedSet c() {
            return (SortedSet) super.b();
        }

        @Override // java.util.SortedSet
        public E first() {
            E e2;
            synchronized (this.f9043b) {
                e2 = (E) c().first();
            }
            return e2;
        }

        public SortedSet<E> headSet(E e2) {
            SortedSet<E> sortedSet;
            synchronized (this.f9043b) {
                sortedSet = Synchronized.sortedSet(c().headSet(e2), this.f9043b);
            }
            return sortedSet;
        }

        @Override // java.util.SortedSet
        public E last() {
            E e2;
            synchronized (this.f9043b) {
                e2 = (E) c().last();
            }
            return e2;
        }

        public SortedSet<E> subSet(E e2, E e3) {
            SortedSet<E> sortedSet;
            synchronized (this.f9043b) {
                sortedSet = Synchronized.sortedSet(c().subSet(e2, e3), this.f9043b);
            }
            return sortedSet;
        }

        public SortedSet<E> tailSet(E e2) {
            SortedSet<E> sortedSet;
            synchronized (this.f9043b) {
                sortedSet = Synchronized.sortedSet(c().tailSet(e2), this.f9043b);
            }
            return sortedSet;
        }
    }

    /* loaded from: classes2.dex */
    private static class SynchronizedSortedSetMultimap<K, V> extends SynchronizedSetMultimap<K, V> implements SortedSetMultimap<K, V> {
        private static final long serialVersionUID = 0;

        SynchronizedSortedSetMultimap(SortedSetMultimap sortedSetMultimap, @NullableDecl Object obj) {
            super(sortedSetMultimap, obj);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.Synchronized.SynchronizedSetMultimap
        /* renamed from: d */
        public SortedSetMultimap c() {
            return (SortedSetMultimap) super.b();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.Synchronized.SynchronizedSetMultimap, com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public /* bridge */ /* synthetic */ Collection get(Object obj) {
            return get((SynchronizedSortedSetMultimap<K, V>) obj);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.Synchronized.SynchronizedSetMultimap, com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public /* bridge */ /* synthetic */ Set get(Object obj) {
            return get((SynchronizedSortedSetMultimap<K, V>) obj);
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedSetMultimap, com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public SortedSet<V> get(K k2) {
            SortedSet<V> sortedSet;
            synchronized (this.f9043b) {
                sortedSet = Synchronized.sortedSet(c().get((SortedSetMultimap) k2), this.f9043b);
            }
            return sortedSet;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedSetMultimap, com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public SortedSet<V> removeAll(Object obj) {
            SortedSet<V> removeAll;
            synchronized (this.f9043b) {
                removeAll = c().removeAll(obj);
            }
            return removeAll;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.Synchronized.SynchronizedSetMultimap, com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public /* bridge */ /* synthetic */ Collection replaceValues(Object obj, Iterable iterable) {
            return replaceValues((SynchronizedSortedSetMultimap<K, V>) obj, iterable);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.Synchronized.SynchronizedSetMultimap, com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public /* bridge */ /* synthetic */ Set replaceValues(Object obj, Iterable iterable) {
            return replaceValues((SynchronizedSortedSetMultimap<K, V>) obj, iterable);
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedSetMultimap, com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public SortedSet<V> replaceValues(K k2, Iterable<? extends V> iterable) {
            SortedSet<V> replaceValues;
            synchronized (this.f9043b) {
                replaceValues = c().replaceValues((SortedSetMultimap) k2, (Iterable) iterable);
            }
            return replaceValues;
        }

        @Override // com.google.common.collect.SortedSetMultimap
        public Comparator<? super V> valueComparator() {
            Comparator<? super V> valueComparator;
            synchronized (this.f9043b) {
                valueComparator = c().valueComparator();
            }
            return valueComparator;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class SynchronizedTable<R, C, V> extends SynchronizedObject implements Table<R, C, V> {
        SynchronizedTable(Table table, Object obj) {
            super(table, obj);
        }

        Table b() {
            return (Table) super.a();
        }

        @Override // com.google.common.collect.Table
        public Set<Table.Cell<R, C, V>> cellSet() {
            Set<Table.Cell<R, C, V>> r2;
            synchronized (this.f9043b) {
                r2 = Synchronized.r(b().cellSet(), this.f9043b);
            }
            return r2;
        }

        @Override // com.google.common.collect.Table
        public void clear() {
            synchronized (this.f9043b) {
                b().clear();
            }
        }

        @Override // com.google.common.collect.Table
        public Map<R, V> column(@NullableDecl C c2) {
            Map<R, V> j2;
            synchronized (this.f9043b) {
                j2 = Synchronized.j(b().column(c2), this.f9043b);
            }
            return j2;
        }

        @Override // com.google.common.collect.Table
        public Set<C> columnKeySet() {
            Set<C> r2;
            synchronized (this.f9043b) {
                r2 = Synchronized.r(b().columnKeySet(), this.f9043b);
            }
            return r2;
        }

        @Override // com.google.common.collect.Table
        public Map<C, Map<R, V>> columnMap() {
            Map<C, Map<R, V>> j2;
            synchronized (this.f9043b) {
                j2 = Synchronized.j(Maps.transformValues(b().columnMap(), new Function<Map<R, V>, Map<R, V>>() { // from class: com.google.common.collect.Synchronized.SynchronizedTable.2
                    @Override // com.google.common.base.Function
                    public /* bridge */ /* synthetic */ Object apply(Object obj) {
                        return apply((Map) ((Map) obj));
                    }

                    public Map<R, V> apply(Map<R, V> map) {
                        return Synchronized.j(map, SynchronizedTable.this.f9043b);
                    }
                }), this.f9043b);
            }
            return j2;
        }

        @Override // com.google.common.collect.Table
        public boolean contains(@NullableDecl Object obj, @NullableDecl Object obj2) {
            boolean contains;
            synchronized (this.f9043b) {
                contains = b().contains(obj, obj2);
            }
            return contains;
        }

        @Override // com.google.common.collect.Table
        public boolean containsColumn(@NullableDecl Object obj) {
            boolean containsColumn;
            synchronized (this.f9043b) {
                containsColumn = b().containsColumn(obj);
            }
            return containsColumn;
        }

        @Override // com.google.common.collect.Table
        public boolean containsRow(@NullableDecl Object obj) {
            boolean containsRow;
            synchronized (this.f9043b) {
                containsRow = b().containsRow(obj);
            }
            return containsRow;
        }

        @Override // com.google.common.collect.Table
        public boolean containsValue(@NullableDecl Object obj) {
            boolean containsValue;
            synchronized (this.f9043b) {
                containsValue = b().containsValue(obj);
            }
            return containsValue;
        }

        @Override // com.google.common.collect.Table
        public boolean equals(@NullableDecl Object obj) {
            boolean equals;
            if (this == obj) {
                return true;
            }
            synchronized (this.f9043b) {
                equals = b().equals(obj);
            }
            return equals;
        }

        @Override // com.google.common.collect.Table
        public V get(@NullableDecl Object obj, @NullableDecl Object obj2) {
            V v;
            synchronized (this.f9043b) {
                v = (V) b().get(obj, obj2);
            }
            return v;
        }

        @Override // com.google.common.collect.Table
        public int hashCode() {
            int hashCode;
            synchronized (this.f9043b) {
                hashCode = b().hashCode();
            }
            return hashCode;
        }

        @Override // com.google.common.collect.Table
        public boolean isEmpty() {
            boolean isEmpty;
            synchronized (this.f9043b) {
                isEmpty = b().isEmpty();
            }
            return isEmpty;
        }

        @Override // com.google.common.collect.Table
        public V put(@NullableDecl R r2, @NullableDecl C c2, @NullableDecl V v) {
            V v2;
            synchronized (this.f9043b) {
                v2 = (V) b().put(r2, c2, v);
            }
            return v2;
        }

        @Override // com.google.common.collect.Table
        public void putAll(Table<? extends R, ? extends C, ? extends V> table) {
            synchronized (this.f9043b) {
                b().putAll(table);
            }
        }

        @Override // com.google.common.collect.Table
        public V remove(@NullableDecl Object obj, @NullableDecl Object obj2) {
            V v;
            synchronized (this.f9043b) {
                v = (V) b().remove(obj, obj2);
            }
            return v;
        }

        @Override // com.google.common.collect.Table
        public Map<C, V> row(@NullableDecl R r2) {
            Map<C, V> j2;
            synchronized (this.f9043b) {
                j2 = Synchronized.j(b().row(r2), this.f9043b);
            }
            return j2;
        }

        @Override // com.google.common.collect.Table
        public Set<R> rowKeySet() {
            Set<R> r2;
            synchronized (this.f9043b) {
                r2 = Synchronized.r(b().rowKeySet(), this.f9043b);
            }
            return r2;
        }

        @Override // com.google.common.collect.Table
        public Map<R, Map<C, V>> rowMap() {
            Map<R, Map<C, V>> j2;
            synchronized (this.f9043b) {
                j2 = Synchronized.j(Maps.transformValues(b().rowMap(), new Function<Map<C, V>, Map<C, V>>() { // from class: com.google.common.collect.Synchronized.SynchronizedTable.1
                    @Override // com.google.common.base.Function
                    public /* bridge */ /* synthetic */ Object apply(Object obj) {
                        return apply((Map) ((Map) obj));
                    }

                    public Map<C, V> apply(Map<C, V> map) {
                        return Synchronized.j(map, SynchronizedTable.this.f9043b);
                    }
                }), this.f9043b);
            }
            return j2;
        }

        @Override // com.google.common.collect.Table
        public int size() {
            int size;
            synchronized (this.f9043b) {
                size = b().size();
            }
            return size;
        }

        @Override // com.google.common.collect.Table
        public Collection<V> values() {
            Collection<V> collection;
            synchronized (this.f9043b) {
                collection = Synchronized.collection(b().values(), this.f9043b);
            }
            return collection;
        }
    }

    private Synchronized() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <E> Collection<E> collection(Collection<E> collection, @NullableDecl Object obj) {
        return new SynchronizedCollection(collection, obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BiMap g(BiMap biMap, @NullableDecl Object obj) {
        return ((biMap instanceof SynchronizedBiMap) || (biMap instanceof ImmutableBiMap)) ? biMap : new SynchronizedBiMap(biMap, obj, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Deque h(Deque deque, @NullableDecl Object obj) {
        return new SynchronizedDeque(deque, obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ListMultimap i(ListMultimap listMultimap, @NullableDecl Object obj) {
        return ((listMultimap instanceof SynchronizedListMultimap) || (listMultimap instanceof BaseImmutableMultimap)) ? listMultimap : new SynchronizedListMultimap(listMultimap, obj);
    }

    @VisibleForTesting
    static Map j(Map map, @NullableDecl Object obj) {
        return new SynchronizedMap(map, obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Multimap k(Multimap multimap, @NullableDecl Object obj) {
        return ((multimap instanceof SynchronizedMultimap) || (multimap instanceof BaseImmutableMultimap)) ? multimap : new SynchronizedMultimap(multimap, obj);
    }

    static Multiset l(Multiset multiset, @NullableDecl Object obj) {
        return ((multiset instanceof SynchronizedMultiset) || (multiset instanceof ImmutableMultiset)) ? multiset : new SynchronizedMultiset(multiset, obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <E> List<E> list(List<E> list, @NullableDecl Object obj) {
        return list instanceof RandomAccess ? new SynchronizedRandomAccessList(list, obj) : new SynchronizedList(list, obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @GwtIncompatible
    public static NavigableMap m(NavigableMap navigableMap) {
        return n(navigableMap, null);
    }

    @GwtIncompatible
    static NavigableMap n(NavigableMap navigableMap, @NullableDecl Object obj) {
        return new SynchronizedNavigableMap(navigableMap, obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @GwtIncompatible
    public static <K, V> Map.Entry<K, V> nullableSynchronizedEntry(@NullableDecl Map.Entry<K, V> entry, @NullableDecl Object obj) {
        if (entry == null) {
            return null;
        }
        return new SynchronizedEntry(entry, obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @GwtIncompatible
    public static NavigableSet o(NavigableSet navigableSet) {
        return p(navigableSet, null);
    }

    @GwtIncompatible
    static NavigableSet p(NavigableSet navigableSet, @NullableDecl Object obj) {
        return new SynchronizedNavigableSet(navigableSet, obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Queue q(Queue queue, @NullableDecl Object obj) {
        return queue instanceof SynchronizedQueue ? queue : new SynchronizedQueue(queue, obj);
    }

    @VisibleForTesting
    static Set r(Set set, @NullableDecl Object obj) {
        return new SynchronizedSet(set, obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SetMultimap s(SetMultimap setMultimap, @NullableDecl Object obj) {
        return ((setMultimap instanceof SynchronizedSetMultimap) || (setMultimap instanceof BaseImmutableMultimap)) ? setMultimap : new SynchronizedSetMultimap(setMultimap, obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <E> SortedSet<E> sortedSet(SortedSet<E> sortedSet, @NullableDecl Object obj) {
        return new SynchronizedSortedSet(sortedSet, obj);
    }

    static SortedMap t(SortedMap sortedMap, @NullableDecl Object obj) {
        return new SynchronizedSortedMap(sortedMap, obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <E> Collection<E> typePreservingCollection(Collection<E> collection, @NullableDecl Object obj) {
        return collection instanceof SortedSet ? sortedSet((SortedSet) collection, obj) : collection instanceof Set ? r((Set) collection, obj) : collection instanceof List ? list((List) collection, obj) : collection(collection, obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <E> Set<E> typePreservingSet(Set<E> set, @NullableDecl Object obj) {
        return set instanceof SortedSet ? sortedSet((SortedSet) set, obj) : r(set, obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SortedSetMultimap u(SortedSetMultimap sortedSetMultimap, @NullableDecl Object obj) {
        return sortedSetMultimap instanceof SynchronizedSortedSetMultimap ? sortedSetMultimap : new SynchronizedSortedSetMultimap(sortedSetMultimap, obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Table v(Table table, Object obj) {
        return new SynchronizedTable(table, obj);
    }
}
