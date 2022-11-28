package androidx.collection;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public class ArrayMap<K, V> extends SimpleArrayMap<K, V> implements Map<K, V> {
    @Nullable

    /* renamed from: h  reason: collision with root package name */
    MapCollections f1580h;

    public ArrayMap() {
    }

    public ArrayMap(int i2) {
        super(i2);
    }

    public ArrayMap(SimpleArrayMap simpleArrayMap) {
        super(simpleArrayMap);
    }

    private MapCollections<K, V> getCollection() {
        if (this.f1580h == null) {
            this.f1580h = new MapCollections<K, V>() { // from class: androidx.collection.ArrayMap.1
                @Override // androidx.collection.MapCollections
                protected void a() {
                    ArrayMap.this.clear();
                }

                @Override // androidx.collection.MapCollections
                protected Object b(int i2, int i3) {
                    return ArrayMap.this.f1613b[(i2 << 1) + i3];
                }

                @Override // androidx.collection.MapCollections
                protected Map c() {
                    return ArrayMap.this;
                }

                @Override // androidx.collection.MapCollections
                protected int d() {
                    return ArrayMap.this.f1614c;
                }

                @Override // androidx.collection.MapCollections
                protected int e(Object obj) {
                    return ArrayMap.this.indexOfKey(obj);
                }

                @Override // androidx.collection.MapCollections
                protected int f(Object obj) {
                    return ArrayMap.this.c(obj);
                }

                @Override // androidx.collection.MapCollections
                protected void g(Object obj, Object obj2) {
                    ArrayMap.this.put(obj, obj2);
                }

                @Override // androidx.collection.MapCollections
                protected void h(int i2) {
                    ArrayMap.this.removeAt(i2);
                }

                @Override // androidx.collection.MapCollections
                protected Object i(int i2, Object obj) {
                    return ArrayMap.this.setValueAt(i2, obj);
                }
            };
        }
        return this.f1580h;
    }

    public boolean containsAll(@NonNull Collection<?> collection) {
        return MapCollections.containsAllHelper(this, collection);
    }

    @Override // java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        return getCollection().getEntrySet();
    }

    @Override // java.util.Map
    public Set<K> keySet() {
        return getCollection().getKeySet();
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        ensureCapacity(this.f1614c + map.size());
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public boolean removeAll(@NonNull Collection<?> collection) {
        return MapCollections.removeAllHelper(this, collection);
    }

    public boolean retainAll(@NonNull Collection<?> collection) {
        return MapCollections.retainAllHelper(this, collection);
    }

    @Override // java.util.Map
    public Collection<V> values() {
        return getCollection().getValues();
    }
}
