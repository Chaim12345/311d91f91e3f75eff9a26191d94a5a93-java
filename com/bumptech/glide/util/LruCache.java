package com.bumptech.glide.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class LruCache<T, Y> {
    private final Map<T, Entry<Y>> cache = new LinkedHashMap(100, 0.75f, true);
    private long currentSize;
    private final long initialMaxSize;
    private long maxSize;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class Entry<Y> {

        /* renamed from: a  reason: collision with root package name */
        final Object f4839a;

        /* renamed from: b  reason: collision with root package name */
        final int f4840b;

        Entry(Object obj, int i2) {
            this.f4839a = obj;
            this.f4840b = i2;
        }
    }

    public LruCache(long j2) {
        this.initialMaxSize = j2;
        this.maxSize = j2;
    }

    private void evict() {
        c(this.maxSize);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int a(@Nullable Object obj) {
        return 1;
    }

    protected void b(@NonNull Object obj, @Nullable Object obj2) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public synchronized void c(long j2) {
        while (this.currentSize > j2) {
            Iterator<Map.Entry<T, Entry<Y>>> it = this.cache.entrySet().iterator();
            Map.Entry<T, Entry<Y>> next = it.next();
            Entry<Y> value = next.getValue();
            this.currentSize -= value.f4840b;
            T key = next.getKey();
            it.remove();
            b(key, value.f4839a);
        }
    }

    public void clearMemory() {
        c(0L);
    }

    public synchronized boolean contains(@NonNull T t2) {
        return this.cache.containsKey(t2);
    }

    @Nullable
    public synchronized Y get(@NonNull T t2) {
        Entry<Y> entry;
        entry = this.cache.get(t2);
        return entry != null ? (Y) entry.f4839a : null;
    }

    public synchronized long getCurrentSize() {
        return this.currentSize;
    }

    public synchronized long getMaxSize() {
        return this.maxSize;
    }

    @Nullable
    public synchronized Y put(@NonNull T t2, @Nullable Y y) {
        int a2 = a(y);
        long j2 = a2;
        Y y2 = null;
        if (j2 >= this.maxSize) {
            b(t2, y);
            return null;
        }
        if (y != null) {
            this.currentSize += j2;
        }
        Entry<Y> put = this.cache.put(t2, y == null ? null : new Entry<>(y, a2));
        if (put != null) {
            this.currentSize -= put.f4840b;
            if (!put.f4839a.equals(y)) {
                b(t2, put.f4839a);
            }
        }
        evict();
        if (put != null) {
            y2 = (Y) put.f4839a;
        }
        return y2;
    }

    @Nullable
    public synchronized Y remove(@NonNull T t2) {
        Entry<Y> remove = this.cache.remove(t2);
        if (remove == null) {
            return null;
        }
        this.currentSize -= remove.f4840b;
        return (Y) remove.f4839a;
    }

    public synchronized void setSizeMultiplier(float f2) {
        if (f2 < 0.0f) {
            throw new IllegalArgumentException("Multiplier must be >= 0");
        }
        this.maxSize = Math.round(((float) this.initialMaxSize) * f2);
        evict();
    }
}
