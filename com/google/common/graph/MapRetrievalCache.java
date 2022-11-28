package com.google.common.graph;

import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* loaded from: classes2.dex */
class MapRetrievalCache<K, V> extends MapIteratorCache<K, V> {
    @NullableDecl
    private volatile transient CacheEntry<K, V> cacheEntry1;
    @NullableDecl
    private volatile transient CacheEntry<K, V> cacheEntry2;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class CacheEntry<K, V> {

        /* renamed from: a  reason: collision with root package name */
        final Object f9182a;

        /* renamed from: b  reason: collision with root package name */
        final Object f9183b;

        CacheEntry(Object obj, Object obj2) {
            this.f9182a = obj;
            this.f9183b = obj2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MapRetrievalCache(Map map) {
        super(map);
    }

    private void addToCache(CacheEntry<K, V> cacheEntry) {
        this.cacheEntry2 = this.cacheEntry1;
        this.cacheEntry1 = cacheEntry;
    }

    private void addToCache(K k2, V v) {
        addToCache(new CacheEntry<>(k2, v));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.graph.MapIteratorCache
    public void c() {
        super.c();
        this.cacheEntry1 = null;
        this.cacheEntry2 = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.graph.MapIteratorCache
    public Object d(@NullableDecl Object obj) {
        Object d2 = super.d(obj);
        if (d2 != null) {
            return d2;
        }
        CacheEntry<K, V> cacheEntry = this.cacheEntry1;
        if (cacheEntry == null || cacheEntry.f9182a != obj) {
            CacheEntry<K, V> cacheEntry2 = this.cacheEntry2;
            if (cacheEntry2 == null || cacheEntry2.f9182a != obj) {
                return null;
            }
            addToCache(cacheEntry2);
            return cacheEntry2.f9183b;
        }
        return cacheEntry.f9183b;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.MapIteratorCache
    public V get(@NullableDecl Object obj) {
        V v = (V) d(obj);
        if (v != null) {
            return v;
        }
        V withoutCaching = getWithoutCaching(obj);
        if (withoutCaching != null) {
            addToCache(obj, withoutCaching);
        }
        return withoutCaching;
    }
}
