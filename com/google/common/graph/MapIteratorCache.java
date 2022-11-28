package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.UnmodifiableIterator;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class MapIteratorCache<K, V> {
    private final Map<K, V> backingMap;
    @NullableDecl
    private volatile transient Map.Entry<K, V> cacheEntry;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MapIteratorCache(Map map) {
        this.backingMap = (Map) Preconditions.checkNotNull(map);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void c() {
        this.cacheEntry = null;
    }

    public final void clear() {
        c();
        this.backingMap.clear();
    }

    public final boolean containsKey(@NullableDecl Object obj) {
        return d(obj) != null || this.backingMap.containsKey(obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object d(@NullableDecl Object obj) {
        Map.Entry<K, V> entry = this.cacheEntry;
        if (entry == null || entry.getKey() != obj) {
            return null;
        }
        return entry.getValue();
    }

    public V get(@NullableDecl Object obj) {
        V v = (V) d(obj);
        return v != null ? v : getWithoutCaching(obj);
    }

    public final V getWithoutCaching(@NullableDecl Object obj) {
        return this.backingMap.get(obj);
    }

    @CanIgnoreReturnValue
    public final V put(@NullableDecl K k2, @NullableDecl V v) {
        c();
        return this.backingMap.put(k2, v);
    }

    @CanIgnoreReturnValue
    public final V remove(@NullableDecl Object obj) {
        c();
        return this.backingMap.remove(obj);
    }

    public final Set<K> unmodifiableKeySet() {
        return new AbstractSet<K>() { // from class: com.google.common.graph.MapIteratorCache.1
            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(@NullableDecl Object obj) {
                return MapIteratorCache.this.containsKey(obj);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public UnmodifiableIterator<K> iterator() {
                final Iterator<Map.Entry<K, V>> it = MapIteratorCache.this.backingMap.entrySet().iterator();
                return new UnmodifiableIterator<K>() { // from class: com.google.common.graph.MapIteratorCache.1.1
                    @Override // java.util.Iterator
                    public boolean hasNext() {
                        return it.hasNext();
                    }

                    @Override // java.util.Iterator
                    public K next() {
                        Map.Entry entry = (Map.Entry) it.next();
                        MapIteratorCache.this.cacheEntry = entry;
                        return (K) entry.getKey();
                    }
                };
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return MapIteratorCache.this.backingMap.size();
            }
        };
    }
}
