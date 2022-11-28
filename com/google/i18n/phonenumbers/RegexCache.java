package com.google.i18n.phonenumbers;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;
/* loaded from: classes2.dex */
public class RegexCache {
    private LRUCache<String, Pattern> cache;

    /* loaded from: classes2.dex */
    private static class LRUCache<K, V> {
        private LinkedHashMap<K, V> map;
        private int size;

        public LRUCache(int i2) {
            this.size = i2;
            this.map = new LinkedHashMap<K, V>(((i2 * 4) / 3) + 1, 0.75f, true) { // from class: com.google.i18n.phonenumbers.RegexCache.LRUCache.1
                @Override // java.util.LinkedHashMap
                protected boolean removeEldestEntry(Map.Entry entry) {
                    return size() > LRUCache.this.size;
                }
            };
        }

        public synchronized boolean containsKey(K k2) {
            return this.map.containsKey(k2);
        }

        public synchronized V get(K k2) {
            return this.map.get(k2);
        }

        public synchronized void put(K k2, V v) {
            this.map.put(k2, v);
        }
    }

    public RegexCache(int i2) {
        this.cache = new LRUCache<>(i2);
    }

    public Pattern getPatternForRegex(String str) {
        Pattern pattern = this.cache.get(str);
        if (pattern == null) {
            Pattern compile = Pattern.compile(str);
            this.cache.put(str, compile);
            return compile;
        }
        return pattern;
    }
}
