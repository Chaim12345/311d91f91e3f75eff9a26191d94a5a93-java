package com.google.maps.android.data.geojson;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes2.dex */
public class BiMultiMap<K> extends HashMap<K, Object> {
    private final Map<Object, K> mValuesToKeys = new HashMap();

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public void clear() {
        super.clear();
        this.mValuesToKeys.clear();
    }

    @Override // java.util.HashMap, java.util.AbstractMap
    public BiMultiMap<K> clone() {
        BiMultiMap<K> biMultiMap = new BiMultiMap<>();
        biMultiMap.putAll((Map) super.clone());
        return biMultiMap;
    }

    public K getKey(Object obj) {
        return this.mValuesToKeys.get(obj);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public Object put(K k2, Object obj) {
        if (obj instanceof Collection) {
            return put((BiMultiMap<K>) k2, (Collection) obj);
        }
        this.mValuesToKeys.put(obj, k2);
        return super.put((BiMultiMap<K>) k2, (K) obj);
    }

    public Object put(K k2, Collection collection) {
        for (Object obj : collection) {
            this.mValuesToKeys.put(obj, k2);
        }
        return super.put((BiMultiMap<K>) k2, (K) collection);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public void putAll(Map<? extends K, ?> map) {
        for (Map.Entry<? extends K, ?> entry : map.entrySet()) {
            put((BiMultiMap<K>) entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public Object remove(Object obj) {
        Object remove = super.remove(obj);
        if (remove instanceof Collection) {
            for (Object obj2 : (Collection) remove) {
                this.mValuesToKeys.remove(obj2);
            }
        } else {
            this.mValuesToKeys.remove(remove);
        }
        return remove;
    }
}
