package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimaps;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.RandomAccess;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* JADX INFO: Access modifiers changed from: package-private */
@GwtCompatible
/* loaded from: classes2.dex */
public abstract class AbstractMapBasedMultimap<K, V> extends AbstractMultimap<K, V> implements Serializable {
    private static final long serialVersionUID = 2447537837011683357L;
    private transient Map<K, Collection<V>> map;
    private transient int totalSize;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class AsMap extends Maps.ViewCachingAbstractMap<K, Collection<V>> {

        /* renamed from: a  reason: collision with root package name */
        final transient Map f8377a;

        /* loaded from: classes2.dex */
        class AsMapEntries extends Maps.EntrySet<K, Collection<V>> {
            AsMapEntries() {
            }

            @Override // com.google.common.collect.Maps.EntrySet
            Map a() {
                return AsMap.this;
            }

            @Override // com.google.common.collect.Maps.EntrySet, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(Object obj) {
                return Collections2.e(AsMap.this.f8377a.entrySet(), obj);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public Iterator<Map.Entry<K, Collection<V>>> iterator() {
                return new AsMapIterator();
            }

            @Override // com.google.common.collect.Maps.EntrySet, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean remove(Object obj) {
                if (contains(obj)) {
                    AbstractMapBasedMultimap.this.removeValuesForKey(((Map.Entry) obj).getKey());
                    return true;
                }
                return false;
            }
        }

        /* loaded from: classes2.dex */
        class AsMapIterator implements Iterator<Map.Entry<K, Collection<V>>> {

            /* renamed from: a  reason: collision with root package name */
            final Iterator f8380a;
            @NullableDecl

            /* renamed from: b  reason: collision with root package name */
            Collection f8381b;

            AsMapIterator() {
                this.f8380a = AsMap.this.f8377a.entrySet().iterator();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.f8380a.hasNext();
            }

            @Override // java.util.Iterator
            public Map.Entry<K, Collection<V>> next() {
                Map.Entry entry = (Map.Entry) this.f8380a.next();
                this.f8381b = (Collection) entry.getValue();
                return AsMap.this.b(entry);
            }

            @Override // java.util.Iterator
            public void remove() {
                CollectPreconditions.e(this.f8381b != null);
                this.f8380a.remove();
                AbstractMapBasedMultimap.this.totalSize -= this.f8381b.size();
                this.f8381b.clear();
                this.f8381b = null;
            }
        }

        AsMap(Map map) {
            this.f8377a = map;
        }

        Map.Entry b(Map.Entry entry) {
            Object key = entry.getKey();
            return Maps.immutableEntry(key, AbstractMapBasedMultimap.this.w(key, (Collection) entry.getValue()));
        }

        @Override // java.util.AbstractMap, java.util.Map
        public void clear() {
            if (this.f8377a == AbstractMapBasedMultimap.this.map) {
                AbstractMapBasedMultimap.this.clear();
            } else {
                Iterators.c(new AsMapIterator());
            }
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object obj) {
            return Maps.u(this.f8377a, obj);
        }

        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap
        protected Set createEntrySet() {
            return new AsMapEntries();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean equals(@NullableDecl Object obj) {
            return this == obj || this.f8377a.equals(obj);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Collection<V> get(Object obj) {
            Collection collection = (Collection) Maps.v(this.f8377a, obj);
            if (collection == null) {
                return null;
            }
            return AbstractMapBasedMultimap.this.w(obj, collection);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int hashCode() {
            return this.f8377a.hashCode();
        }

        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap, java.util.AbstractMap, java.util.Map
        public Set<K> keySet() {
            return AbstractMapBasedMultimap.this.keySet();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Collection<V> remove(Object obj) {
            Collection<? extends V> collection = (Collection) this.f8377a.remove(obj);
            if (collection == null) {
                return null;
            }
            Collection<V> p2 = AbstractMapBasedMultimap.this.p();
            p2.addAll(collection);
            AbstractMapBasedMultimap.this.totalSize -= collection.size();
            collection.clear();
            return p2;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int size() {
            return this.f8377a.size();
        }

        @Override // java.util.AbstractMap
        public String toString() {
            return this.f8377a.toString();
        }
    }

    /* loaded from: classes2.dex */
    private abstract class Itr<T> implements Iterator<T> {

        /* renamed from: a  reason: collision with root package name */
        final Iterator f8383a;
        @NullableDecl

        /* renamed from: b  reason: collision with root package name */
        Object f8384b = null;
        @NullableDecl

        /* renamed from: c  reason: collision with root package name */
        Collection f8385c = null;

        /* renamed from: d  reason: collision with root package name */
        Iterator f8386d = Iterators.g();

        Itr() {
            this.f8383a = AbstractMapBasedMultimap.this.map.entrySet().iterator();
        }

        abstract Object a(Object obj, Object obj2);

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f8383a.hasNext() || this.f8386d.hasNext();
        }

        @Override // java.util.Iterator
        public T next() {
            if (!this.f8386d.hasNext()) {
                Map.Entry entry = (Map.Entry) this.f8383a.next();
                this.f8384b = entry.getKey();
                Collection collection = (Collection) entry.getValue();
                this.f8385c = collection;
                this.f8386d = collection.iterator();
            }
            return (T) a(this.f8384b, this.f8386d.next());
        }

        @Override // java.util.Iterator
        public void remove() {
            this.f8386d.remove();
            if (this.f8385c.isEmpty()) {
                this.f8383a.remove();
            }
            AbstractMapBasedMultimap.m(AbstractMapBasedMultimap.this);
        }
    }

    /* loaded from: classes2.dex */
    private class KeySet extends Maps.KeySet<K, Collection<V>> {
        KeySet(Map map) {
            super(map);
        }

        @Override // com.google.common.collect.Maps.KeySet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            Iterators.c(iterator());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean containsAll(Collection<?> collection) {
            return a().keySet().containsAll(collection);
        }

        @Override // java.util.AbstractSet, java.util.Collection, java.util.Set
        public boolean equals(@NullableDecl Object obj) {
            return this == obj || a().keySet().equals(obj);
        }

        @Override // java.util.AbstractSet, java.util.Collection, java.util.Set
        public int hashCode() {
            return a().keySet().hashCode();
        }

        @Override // com.google.common.collect.Maps.KeySet, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            final Iterator<Map.Entry<K, V>> it = a().entrySet().iterator();
            return new Iterator<K>() { // from class: com.google.common.collect.AbstractMapBasedMultimap.KeySet.1
                @NullableDecl

                /* renamed from: a  reason: collision with root package name */
                Map.Entry f8389a;

                @Override // java.util.Iterator
                public boolean hasNext() {
                    return it.hasNext();
                }

                @Override // java.util.Iterator
                public K next() {
                    Map.Entry entry = (Map.Entry) it.next();
                    this.f8389a = entry;
                    return (K) entry.getKey();
                }

                @Override // java.util.Iterator
                public void remove() {
                    CollectPreconditions.e(this.f8389a != null);
                    Collection collection = (Collection) this.f8389a.getValue();
                    it.remove();
                    AbstractMapBasedMultimap.this.totalSize -= collection.size();
                    collection.clear();
                    this.f8389a = null;
                }
            };
        }

        @Override // com.google.common.collect.Maps.KeySet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            int i2;
            Collection collection = (Collection) a().remove(obj);
            if (collection != null) {
                i2 = collection.size();
                collection.clear();
                AbstractMapBasedMultimap.this.totalSize -= i2;
            } else {
                i2 = 0;
            }
            return i2 > 0;
        }
    }

    /* loaded from: classes2.dex */
    class NavigableAsMap extends AbstractMapBasedMultimap<K, V>.SortedAsMap implements NavigableMap<K, Collection<V>> {
        NavigableAsMap(NavigableMap navigableMap) {
            super(navigableMap);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, Collection<V>> ceilingEntry(K k2) {
            Map.Entry<K, V> ceilingEntry = d().ceilingEntry(k2);
            if (ceilingEntry == null) {
                return null;
            }
            return b(ceilingEntry);
        }

        @Override // java.util.NavigableMap
        public K ceilingKey(K k2) {
            return (K) d().ceilingKey(k2);
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> descendingKeySet() {
            return descendingMap().navigableKeySet();
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, Collection<V>> descendingMap() {
            return new NavigableAsMap(d().descendingMap());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedAsMap, com.google.common.collect.Maps.ViewCachingAbstractMap
        /* renamed from: e */
        public NavigableSet createKeySet() {
            return new NavigableKeySet(d());
        }

        Map.Entry f(Iterator it) {
            if (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                Collection p2 = AbstractMapBasedMultimap.this.p();
                p2.addAll((Collection) entry.getValue());
                it.remove();
                return Maps.immutableEntry(entry.getKey(), AbstractMapBasedMultimap.this.v(p2));
            }
            return null;
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, Collection<V>> firstEntry() {
            Map.Entry<K, V> firstEntry = d().firstEntry();
            if (firstEntry == null) {
                return null;
            }
            return b(firstEntry);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, Collection<V>> floorEntry(K k2) {
            Map.Entry<K, V> floorEntry = d().floorEntry(k2);
            if (floorEntry == null) {
                return null;
            }
            return b(floorEntry);
        }

        @Override // java.util.NavigableMap
        public K floorKey(K k2) {
            return (K) d().floorKey(k2);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedAsMap
        /* renamed from: g */
        public NavigableMap d() {
            return (NavigableMap) super.d();
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedAsMap, java.util.SortedMap, java.util.NavigableMap
        public NavigableMap<K, Collection<V>> headMap(K k2) {
            return headMap(k2, false);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, Collection<V>> headMap(K k2, boolean z) {
            return new NavigableAsMap(d().headMap(k2, z));
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedAsMap, java.util.SortedMap, java.util.NavigableMap
        public /* bridge */ /* synthetic */ SortedMap headMap(Object obj) {
            return headMap((NavigableAsMap) obj);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, Collection<V>> higherEntry(K k2) {
            Map.Entry<K, V> higherEntry = d().higherEntry(k2);
            if (higherEntry == null) {
                return null;
            }
            return b(higherEntry);
        }

        @Override // java.util.NavigableMap
        public K higherKey(K k2) {
            return (K) d().higherKey(k2);
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedAsMap, com.google.common.collect.AbstractMapBasedMultimap.AsMap, com.google.common.collect.Maps.ViewCachingAbstractMap, java.util.AbstractMap, java.util.Map
        public NavigableSet<K> keySet() {
            return (NavigableSet) super.keySet();
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, Collection<V>> lastEntry() {
            Map.Entry<K, V> lastEntry = d().lastEntry();
            if (lastEntry == null) {
                return null;
            }
            return b(lastEntry);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, Collection<V>> lowerEntry(K k2) {
            Map.Entry<K, V> lowerEntry = d().lowerEntry(k2);
            if (lowerEntry == null) {
                return null;
            }
            return b(lowerEntry);
        }

        @Override // java.util.NavigableMap
        public K lowerKey(K k2) {
            return (K) d().lowerKey(k2);
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> navigableKeySet() {
            return keySet();
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, Collection<V>> pollFirstEntry() {
            return f(entrySet().iterator());
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, Collection<V>> pollLastEntry() {
            return f(descendingMap().entrySet().iterator());
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedAsMap, java.util.SortedMap, java.util.NavigableMap
        public NavigableMap<K, Collection<V>> subMap(K k2, K k3) {
            return subMap(k2, true, k3, false);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, Collection<V>> subMap(K k2, boolean z, K k3, boolean z2) {
            return new NavigableAsMap(d().subMap(k2, z, k3, z2));
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedAsMap, java.util.SortedMap, java.util.NavigableMap
        public NavigableMap<K, Collection<V>> tailMap(K k2) {
            return tailMap(k2, true);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, Collection<V>> tailMap(K k2, boolean z) {
            return new NavigableAsMap(d().tailMap(k2, z));
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedAsMap, java.util.SortedMap, java.util.NavigableMap
        public /* bridge */ /* synthetic */ SortedMap tailMap(Object obj) {
            return tailMap((NavigableAsMap) obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class NavigableKeySet extends AbstractMapBasedMultimap<K, V>.SortedKeySet implements NavigableSet<K> {
        NavigableKeySet(NavigableMap navigableMap) {
            super(navigableMap);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedKeySet
        /* renamed from: c */
        public NavigableMap b() {
            return (NavigableMap) super.b();
        }

        @Override // java.util.NavigableSet
        public K ceiling(K k2) {
            return (K) b().ceilingKey(k2);
        }

        @Override // java.util.NavigableSet
        public Iterator<K> descendingIterator() {
            return descendingSet().iterator();
        }

        @Override // java.util.NavigableSet
        public NavigableSet<K> descendingSet() {
            return new NavigableKeySet(b().descendingMap());
        }

        @Override // java.util.NavigableSet
        public K floor(K k2) {
            return (K) b().floorKey(k2);
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedKeySet, java.util.SortedSet, java.util.NavigableSet
        public NavigableSet<K> headSet(K k2) {
            return headSet(k2, false);
        }

        @Override // java.util.NavigableSet
        public NavigableSet<K> headSet(K k2, boolean z) {
            return new NavigableKeySet(b().headMap(k2, z));
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedKeySet, java.util.SortedSet, java.util.NavigableSet
        public /* bridge */ /* synthetic */ SortedSet headSet(Object obj) {
            return headSet((NavigableKeySet) obj);
        }

        @Override // java.util.NavigableSet
        public K higher(K k2) {
            return (K) b().higherKey(k2);
        }

        @Override // java.util.NavigableSet
        public K lower(K k2) {
            return (K) b().lowerKey(k2);
        }

        @Override // java.util.NavigableSet
        public K pollFirst() {
            return (K) Iterators.i(iterator());
        }

        @Override // java.util.NavigableSet
        public K pollLast() {
            return (K) Iterators.i(descendingIterator());
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedKeySet, java.util.SortedSet, java.util.NavigableSet
        public NavigableSet<K> subSet(K k2, K k3) {
            return subSet(k2, true, k3, false);
        }

        @Override // java.util.NavigableSet
        public NavigableSet<K> subSet(K k2, boolean z, K k3, boolean z2) {
            return new NavigableKeySet(b().subMap(k2, z, k3, z2));
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedKeySet, java.util.SortedSet, java.util.NavigableSet
        public NavigableSet<K> tailSet(K k2) {
            return tailSet(k2, true);
        }

        @Override // java.util.NavigableSet
        public NavigableSet<K> tailSet(K k2, boolean z) {
            return new NavigableKeySet(b().tailMap(k2, z));
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedKeySet, java.util.SortedSet, java.util.NavigableSet
        public /* bridge */ /* synthetic */ SortedSet tailSet(Object obj) {
            return tailSet((NavigableKeySet) obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class RandomAccessWrappedList extends AbstractMapBasedMultimap<K, V>.WrappedList implements RandomAccess {
        RandomAccessWrappedList(@NullableDecl AbstractMapBasedMultimap abstractMapBasedMultimap, Object obj, @NullableDecl List list, WrappedCollection wrappedCollection) {
            super(obj, list, wrappedCollection);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class SortedAsMap extends AbstractMapBasedMultimap<K, V>.AsMap implements SortedMap<K, Collection<V>> {
        @NullableDecl

        /* renamed from: c  reason: collision with root package name */
        SortedSet f8394c;

        SortedAsMap(SortedMap sortedMap) {
            super(sortedMap);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap
        /* renamed from: c */
        public SortedSet createKeySet() {
            return new SortedKeySet(d());
        }

        @Override // java.util.SortedMap
        public Comparator<? super K> comparator() {
            return d().comparator();
        }

        SortedMap d() {
            return (SortedMap) this.f8377a;
        }

        @Override // java.util.SortedMap
        public K firstKey() {
            return (K) d().firstKey();
        }

        public SortedMap<K, Collection<V>> headMap(K k2) {
            return new SortedAsMap(d().headMap(k2));
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.AsMap, com.google.common.collect.Maps.ViewCachingAbstractMap, java.util.AbstractMap, java.util.Map
        public SortedSet<K> keySet() {
            SortedSet<K> sortedSet = this.f8394c;
            if (sortedSet == null) {
                SortedSet<K> createKeySet = createKeySet();
                this.f8394c = createKeySet;
                return createKeySet;
            }
            return sortedSet;
        }

        @Override // java.util.SortedMap
        public K lastKey() {
            return (K) d().lastKey();
        }

        public SortedMap<K, Collection<V>> subMap(K k2, K k3) {
            return new SortedAsMap(d().subMap(k2, k3));
        }

        public SortedMap<K, Collection<V>> tailMap(K k2) {
            return new SortedAsMap(d().tailMap(k2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class SortedKeySet extends AbstractMapBasedMultimap<K, V>.KeySet implements SortedSet<K> {
        SortedKeySet(SortedMap sortedMap) {
            super(sortedMap);
        }

        SortedMap b() {
            return (SortedMap) super.a();
        }

        @Override // java.util.SortedSet
        public Comparator<? super K> comparator() {
            return b().comparator();
        }

        @Override // java.util.SortedSet
        public K first() {
            return (K) b().firstKey();
        }

        public SortedSet<K> headSet(K k2) {
            return new SortedKeySet(b().headMap(k2));
        }

        @Override // java.util.SortedSet
        public K last() {
            return (K) b().lastKey();
        }

        public SortedSet<K> subSet(K k2, K k3) {
            return new SortedKeySet(b().subMap(k2, k3));
        }

        public SortedSet<K> tailSet(K k2) {
            return new SortedKeySet(b().tailMap(k2));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class WrappedCollection extends AbstractCollection<V> {
        @NullableDecl

        /* renamed from: a  reason: collision with root package name */
        final Object f8397a;

        /* renamed from: b  reason: collision with root package name */
        Collection f8398b;
        @NullableDecl

        /* renamed from: c  reason: collision with root package name */
        final WrappedCollection f8399c;
        @NullableDecl

        /* renamed from: d  reason: collision with root package name */
        final Collection f8400d;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes2.dex */
        public class WrappedIterator implements Iterator<V> {

            /* renamed from: a  reason: collision with root package name */
            final Iterator f8402a;

            /* renamed from: b  reason: collision with root package name */
            final Collection f8403b;

            WrappedIterator() {
                Collection collection = WrappedCollection.this.f8398b;
                this.f8403b = collection;
                this.f8402a = AbstractMapBasedMultimap.iteratorOrListIterator(collection);
            }

            WrappedIterator(Iterator it) {
                this.f8403b = WrappedCollection.this.f8398b;
                this.f8402a = it;
            }

            Iterator a() {
                b();
                return this.f8402a;
            }

            void b() {
                WrappedCollection.this.e();
                if (WrappedCollection.this.f8398b != this.f8403b) {
                    throw new ConcurrentModificationException();
                }
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                b();
                return this.f8402a.hasNext();
            }

            @Override // java.util.Iterator
            public V next() {
                b();
                return (V) this.f8402a.next();
            }

            @Override // java.util.Iterator
            public void remove() {
                this.f8402a.remove();
                AbstractMapBasedMultimap.m(AbstractMapBasedMultimap.this);
                WrappedCollection.this.f();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public WrappedCollection(@NullableDecl Object obj, Collection collection, @NullableDecl WrappedCollection wrappedCollection) {
            this.f8397a = obj;
            this.f8398b = collection;
            this.f8399c = wrappedCollection;
            this.f8400d = wrappedCollection == null ? null : wrappedCollection.c();
        }

        /* JADX WARN: Multi-variable type inference failed */
        void a() {
            WrappedCollection wrappedCollection = this.f8399c;
            if (wrappedCollection != null) {
                wrappedCollection.a();
            } else {
                AbstractMapBasedMultimap.this.map.put(this.f8397a, this.f8398b);
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean add(V v) {
            e();
            boolean isEmpty = this.f8398b.isEmpty();
            boolean add = this.f8398b.add(v);
            if (add) {
                AbstractMapBasedMultimap.l(AbstractMapBasedMultimap.this);
                if (isEmpty) {
                    a();
                }
            }
            return add;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean addAll(Collection<? extends V> collection) {
            if (collection.isEmpty()) {
                return false;
            }
            int size = size();
            boolean addAll = this.f8398b.addAll(collection);
            if (addAll) {
                int size2 = this.f8398b.size();
                AbstractMapBasedMultimap.this.totalSize += size2 - size;
                if (size == 0) {
                    a();
                }
            }
            return addAll;
        }

        WrappedCollection b() {
            return this.f8399c;
        }

        Collection c() {
            return this.f8398b;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            int size = size();
            if (size == 0) {
                return;
            }
            this.f8398b.clear();
            AbstractMapBasedMultimap.this.totalSize -= size;
            f();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            e();
            return this.f8398b.contains(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean containsAll(Collection<?> collection) {
            e();
            return this.f8398b.containsAll(collection);
        }

        Object d() {
            return this.f8397a;
        }

        void e() {
            Collection collection;
            WrappedCollection wrappedCollection = this.f8399c;
            if (wrappedCollection != null) {
                wrappedCollection.e();
                if (this.f8399c.c() != this.f8400d) {
                    throw new ConcurrentModificationException();
                }
            } else if (!this.f8398b.isEmpty() || (collection = (Collection) AbstractMapBasedMultimap.this.map.get(this.f8397a)) == null) {
            } else {
                this.f8398b = collection;
            }
        }

        @Override // java.util.Collection
        public boolean equals(@NullableDecl Object obj) {
            if (obj == this) {
                return true;
            }
            e();
            return this.f8398b.equals(obj);
        }

        void f() {
            WrappedCollection wrappedCollection = this.f8399c;
            if (wrappedCollection != null) {
                wrappedCollection.f();
            } else if (this.f8398b.isEmpty()) {
                AbstractMapBasedMultimap.this.map.remove(this.f8397a);
            }
        }

        @Override // java.util.Collection
        public int hashCode() {
            e();
            return this.f8398b.hashCode();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            e();
            return new WrappedIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean remove(Object obj) {
            e();
            boolean remove = this.f8398b.remove(obj);
            if (remove) {
                AbstractMapBasedMultimap.m(AbstractMapBasedMultimap.this);
                f();
            }
            return remove;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean removeAll(Collection<?> collection) {
            if (collection.isEmpty()) {
                return false;
            }
            int size = size();
            boolean removeAll = this.f8398b.removeAll(collection);
            if (removeAll) {
                int size2 = this.f8398b.size();
                AbstractMapBasedMultimap.this.totalSize += size2 - size;
                f();
            }
            return removeAll;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean retainAll(Collection<?> collection) {
            Preconditions.checkNotNull(collection);
            int size = size();
            boolean retainAll = this.f8398b.retainAll(collection);
            if (retainAll) {
                int size2 = this.f8398b.size();
                AbstractMapBasedMultimap.this.totalSize += size2 - size;
                f();
            }
            return retainAll;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            e();
            return this.f8398b.size();
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            e();
            return this.f8398b.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class WrappedList extends AbstractMapBasedMultimap<K, V>.WrappedCollection implements List<V> {

        /* loaded from: classes2.dex */
        private class WrappedListIterator extends AbstractMapBasedMultimap<K, V>.WrappedCollection.WrappedIterator implements ListIterator<V> {
            WrappedListIterator() {
                super();
            }

            public WrappedListIterator(int i2) {
                super(WrappedList.this.g().listIterator(i2));
            }

            private ListIterator<V> getDelegateListIterator() {
                return (ListIterator) a();
            }

            @Override // java.util.ListIterator
            public void add(V v) {
                boolean isEmpty = WrappedList.this.isEmpty();
                getDelegateListIterator().add(v);
                AbstractMapBasedMultimap.l(AbstractMapBasedMultimap.this);
                if (isEmpty) {
                    WrappedList.this.a();
                }
            }

            @Override // java.util.ListIterator
            public boolean hasPrevious() {
                return getDelegateListIterator().hasPrevious();
            }

            @Override // java.util.ListIterator
            public int nextIndex() {
                return getDelegateListIterator().nextIndex();
            }

            @Override // java.util.ListIterator
            public V previous() {
                return getDelegateListIterator().previous();
            }

            @Override // java.util.ListIterator
            public int previousIndex() {
                return getDelegateListIterator().previousIndex();
            }

            @Override // java.util.ListIterator
            public void set(V v) {
                getDelegateListIterator().set(v);
            }
        }

        WrappedList(@NullableDecl Object obj, List list, @NullableDecl WrappedCollection wrappedCollection) {
            super(obj, list, wrappedCollection);
        }

        @Override // java.util.List
        public void add(int i2, V v) {
            e();
            boolean isEmpty = c().isEmpty();
            g().add(i2, v);
            AbstractMapBasedMultimap.l(AbstractMapBasedMultimap.this);
            if (isEmpty) {
                a();
            }
        }

        @Override // java.util.List
        public boolean addAll(int i2, Collection<? extends V> collection) {
            if (collection.isEmpty()) {
                return false;
            }
            int size = size();
            boolean addAll = g().addAll(i2, collection);
            if (addAll) {
                int size2 = c().size();
                AbstractMapBasedMultimap.this.totalSize += size2 - size;
                if (size == 0) {
                    a();
                }
            }
            return addAll;
        }

        List g() {
            return (List) c();
        }

        @Override // java.util.List
        public V get(int i2) {
            e();
            return (V) g().get(i2);
        }

        @Override // java.util.List
        public int indexOf(Object obj) {
            e();
            return g().indexOf(obj);
        }

        @Override // java.util.List
        public int lastIndexOf(Object obj) {
            e();
            return g().lastIndexOf(obj);
        }

        @Override // java.util.List
        public ListIterator<V> listIterator() {
            e();
            return new WrappedListIterator();
        }

        @Override // java.util.List
        public ListIterator<V> listIterator(int i2) {
            e();
            return new WrappedListIterator(i2);
        }

        @Override // java.util.List
        public V remove(int i2) {
            e();
            V v = (V) g().remove(i2);
            AbstractMapBasedMultimap.m(AbstractMapBasedMultimap.this);
            f();
            return v;
        }

        @Override // java.util.List
        public V set(int i2, V v) {
            e();
            return (V) g().set(i2, v);
        }

        @Override // java.util.List
        public List<V> subList(int i2, int i3) {
            e();
            return AbstractMapBasedMultimap.this.x(d(), g().subList(i2, i3), b() == null ? this : b());
        }
    }

    /* loaded from: classes2.dex */
    class WrappedNavigableSet extends AbstractMapBasedMultimap<K, V>.WrappedSortedSet implements NavigableSet<V> {
        /* JADX INFO: Access modifiers changed from: package-private */
        public WrappedNavigableSet(@NullableDecl Object obj, NavigableSet navigableSet, @NullableDecl WrappedCollection wrappedCollection) {
            super(obj, navigableSet, wrappedCollection);
        }

        private NavigableSet<V> wrap(NavigableSet<V> navigableSet) {
            return new WrappedNavigableSet(this.f8397a, navigableSet, b() == null ? this : b());
        }

        @Override // java.util.NavigableSet
        public V ceiling(V v) {
            return (V) g().ceiling(v);
        }

        @Override // java.util.NavigableSet
        public Iterator<V> descendingIterator() {
            return new WrappedCollection.WrappedIterator(g().descendingIterator());
        }

        @Override // java.util.NavigableSet
        public NavigableSet<V> descendingSet() {
            return wrap(g().descendingSet());
        }

        @Override // java.util.NavigableSet
        public V floor(V v) {
            return (V) g().floor(v);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.AbstractMapBasedMultimap.WrappedSortedSet
        /* renamed from: h */
        public NavigableSet g() {
            return (NavigableSet) super.g();
        }

        @Override // java.util.NavigableSet
        public NavigableSet<V> headSet(V v, boolean z) {
            return wrap(g().headSet(v, z));
        }

        @Override // java.util.NavigableSet
        public V higher(V v) {
            return (V) g().higher(v);
        }

        @Override // java.util.NavigableSet
        public V lower(V v) {
            return (V) g().lower(v);
        }

        @Override // java.util.NavigableSet
        public V pollFirst() {
            return (V) Iterators.i(iterator());
        }

        @Override // java.util.NavigableSet
        public V pollLast() {
            return (V) Iterators.i(descendingIterator());
        }

        @Override // java.util.NavigableSet
        public NavigableSet<V> subSet(V v, boolean z, V v2, boolean z2) {
            return wrap(g().subSet(v, z, v2, z2));
        }

        @Override // java.util.NavigableSet
        public NavigableSet<V> tailSet(V v, boolean z) {
            return wrap(g().tailSet(v, z));
        }
    }

    /* loaded from: classes2.dex */
    class WrappedSet extends AbstractMapBasedMultimap<K, V>.WrappedCollection implements Set<V> {
        /* JADX INFO: Access modifiers changed from: package-private */
        public WrappedSet(@NullableDecl Object obj, Set set) {
            super(obj, set, null);
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.WrappedCollection, java.util.AbstractCollection, java.util.Collection
        public boolean removeAll(Collection<?> collection) {
            if (collection.isEmpty()) {
                return false;
            }
            int size = size();
            boolean c2 = Sets.c((Set) this.f8398b, collection);
            if (c2) {
                int size2 = this.f8398b.size();
                AbstractMapBasedMultimap.this.totalSize += size2 - size;
                f();
            }
            return c2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class WrappedSortedSet extends AbstractMapBasedMultimap<K, V>.WrappedCollection implements SortedSet<V> {
        /* JADX INFO: Access modifiers changed from: package-private */
        public WrappedSortedSet(@NullableDecl Object obj, SortedSet sortedSet, @NullableDecl WrappedCollection wrappedCollection) {
            super(obj, sortedSet, wrappedCollection);
        }

        @Override // java.util.SortedSet
        public Comparator<? super V> comparator() {
            return g().comparator();
        }

        @Override // java.util.SortedSet
        public V first() {
            e();
            return (V) g().first();
        }

        SortedSet g() {
            return (SortedSet) c();
        }

        @Override // java.util.SortedSet
        public SortedSet<V> headSet(V v) {
            e();
            return new WrappedSortedSet(d(), g().headSet(v), b() == null ? this : b());
        }

        @Override // java.util.SortedSet
        public V last() {
            e();
            return (V) g().last();
        }

        @Override // java.util.SortedSet
        public SortedSet<V> subSet(V v, V v2) {
            e();
            return new WrappedSortedSet(d(), g().subSet(v, v2), b() == null ? this : b());
        }

        @Override // java.util.SortedSet
        public SortedSet<V> tailSet(V v) {
            e();
            return new WrappedSortedSet(d(), g().tailSet(v), b() == null ? this : b());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractMapBasedMultimap(Map map) {
        Preconditions.checkArgument(map.isEmpty());
        this.map = map;
    }

    private Collection<V> getOrCreateCollection(@NullableDecl K k2) {
        Collection<V> collection = this.map.get(k2);
        if (collection == null) {
            Collection<V> q2 = q(k2);
            this.map.put(k2, q2);
            return q2;
        }
        return collection;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <E> Iterator<E> iteratorOrListIterator(Collection<E> collection) {
        return collection instanceof List ? ((List) collection).listIterator() : collection.iterator();
    }

    static /* synthetic */ int l(AbstractMapBasedMultimap abstractMapBasedMultimap) {
        int i2 = abstractMapBasedMultimap.totalSize;
        abstractMapBasedMultimap.totalSize = i2 + 1;
        return i2;
    }

    static /* synthetic */ int m(AbstractMapBasedMultimap abstractMapBasedMultimap) {
        int i2 = abstractMapBasedMultimap.totalSize;
        abstractMapBasedMultimap.totalSize = i2 - 1;
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeValuesForKey(Object obj) {
        Collection collection = (Collection) Maps.w(this.map, obj);
        if (collection != null) {
            int size = collection.size();
            collection.clear();
            this.totalSize -= size;
        }
    }

    @Override // com.google.common.collect.AbstractMultimap
    Map a() {
        return new AsMap(this.map);
    }

    @Override // com.google.common.collect.AbstractMultimap
    Collection b() {
        return this instanceof SetMultimap ? new AbstractMultimap.EntrySet(this) : new AbstractMultimap.Entries();
    }

    @Override // com.google.common.collect.AbstractMultimap
    Set c() {
        return new KeySet(this.map);
    }

    @Override // com.google.common.collect.Multimap
    public void clear() {
        for (Collection<V> collection : this.map.values()) {
            collection.clear();
        }
        this.map.clear();
        this.totalSize = 0;
    }

    @Override // com.google.common.collect.Multimap
    public boolean containsKey(@NullableDecl Object obj) {
        return this.map.containsKey(obj);
    }

    @Override // com.google.common.collect.AbstractMultimap
    Multiset d() {
        return new Multimaps.Keys(this);
    }

    @Override // com.google.common.collect.AbstractMultimap
    Collection e() {
        return new AbstractMultimap.Values();
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public Collection<Map.Entry<K, V>> entries() {
        return super.entries();
    }

    @Override // com.google.common.collect.AbstractMultimap
    Iterator f() {
        return new AbstractMapBasedMultimap<K, V>.Itr<Map.Entry<K, V>>(this) { // from class: com.google.common.collect.AbstractMapBasedMultimap.2
            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.google.common.collect.AbstractMapBasedMultimap.Itr
            /* renamed from: b */
            public Map.Entry a(Object obj, Object obj2) {
                return Maps.immutableEntry(obj, obj2);
            }
        };
    }

    @Override // com.google.common.collect.AbstractMultimap
    Iterator g() {
        return new AbstractMapBasedMultimap<K, V>.Itr<V>(this) { // from class: com.google.common.collect.AbstractMapBasedMultimap.1
            @Override // com.google.common.collect.AbstractMapBasedMultimap.Itr
            Object a(Object obj, Object obj2) {
                return obj2;
            }
        };
    }

    @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public Collection<V> get(@NullableDecl K k2) {
        Collection<V> collection = this.map.get(k2);
        if (collection == null) {
            collection = q(k2);
        }
        return w(k2, collection);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Map o() {
        return this.map;
    }

    abstract Collection p();

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public boolean put(@NullableDecl K k2, @NullableDecl V v) {
        Collection<V> collection = this.map.get(k2);
        if (collection != null) {
            if (collection.add(v)) {
                this.totalSize++;
                return true;
            }
            return false;
        }
        Collection<V> q2 = q(k2);
        if (q2.add(v)) {
            this.totalSize++;
            this.map.put(k2, q2);
            return true;
        }
        throw new AssertionError("New Collection violated the Collection spec");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Collection q(@NullableDecl Object obj) {
        return p();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Map r() {
        Map<K, Collection<V>> map = this.map;
        return map instanceof NavigableMap ? new NavigableAsMap((NavigableMap) this.map) : map instanceof SortedMap ? new SortedAsMap((SortedMap) this.map) : new AsMap(this.map);
    }

    @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public Collection<V> removeAll(@NullableDecl Object obj) {
        Collection<V> remove = this.map.remove(obj);
        if (remove == null) {
            return t();
        }
        Collection p2 = p();
        p2.addAll(remove);
        this.totalSize -= remove.size();
        remove.clear();
        return v(p2);
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public Collection<V> replaceValues(@NullableDecl K k2, Iterable<? extends V> iterable) {
        Iterator<? extends V> it = iterable.iterator();
        if (it.hasNext()) {
            Collection<V> orCreateCollection = getOrCreateCollection(k2);
            Collection p2 = p();
            p2.addAll(orCreateCollection);
            this.totalSize -= orCreateCollection.size();
            orCreateCollection.clear();
            while (it.hasNext()) {
                if (orCreateCollection.add(it.next())) {
                    this.totalSize++;
                }
            }
            return v(p2);
        }
        return removeAll(k2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Set s() {
        Map<K, Collection<V>> map = this.map;
        return map instanceof NavigableMap ? new NavigableKeySet((NavigableMap) this.map) : map instanceof SortedMap ? new SortedKeySet((SortedMap) this.map) : new KeySet(this.map);
    }

    @Override // com.google.common.collect.Multimap
    public int size() {
        return this.totalSize;
    }

    Collection t() {
        return v(p());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void u(Map map) {
        this.map = map;
        this.totalSize = 0;
        for (V v : map.values()) {
            Preconditions.checkArgument(!v.isEmpty());
            this.totalSize += v.size();
        }
    }

    Collection v(Collection collection) {
        return Collections.unmodifiableCollection(collection);
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public Collection<V> values() {
        return super.values();
    }

    Collection w(@NullableDecl Object obj, Collection collection) {
        return new WrappedCollection(obj, collection, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final List x(@NullableDecl Object obj, List list, @NullableDecl WrappedCollection wrappedCollection) {
        return list instanceof RandomAccess ? new RandomAccessWrappedList(this, obj, list, wrappedCollection) : new WrappedList(obj, list, wrappedCollection);
    }
}
