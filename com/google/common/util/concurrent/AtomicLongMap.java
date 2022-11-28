package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible
/* loaded from: classes2.dex */
public final class AtomicLongMap<K> implements Serializable {
    @NullableDecl
    private transient Map<K, Long> asMap;
    private final ConcurrentHashMap<K, AtomicLong> map;

    private AtomicLongMap(ConcurrentHashMap<K, AtomicLong> concurrentHashMap) {
        this.map = (ConcurrentHashMap) Preconditions.checkNotNull(concurrentHashMap);
    }

    public static <K> AtomicLongMap<K> create() {
        return new AtomicLongMap<>(new ConcurrentHashMap());
    }

    public static <K> AtomicLongMap<K> create(Map<? extends K, ? extends Long> map) {
        AtomicLongMap<K> create = create();
        create.putAll(map);
        return create;
    }

    private Map<K, Long> createAsMap() {
        return Collections.unmodifiableMap(Maps.transformValues(this.map, new Function<AtomicLong, Long>(this) { // from class: com.google.common.util.concurrent.AtomicLongMap.1
            @Override // com.google.common.base.Function
            public Long apply(AtomicLong atomicLong) {
                return Long.valueOf(atomicLong.get());
            }
        }));
    }

    boolean a(Object obj, long j2) {
        AtomicLong atomicLong = this.map.get(obj);
        if (atomicLong == null) {
            return false;
        }
        long j3 = atomicLong.get();
        if (j3 != j2) {
            return false;
        }
        if (j3 == 0 || atomicLong.compareAndSet(j3, 0L)) {
            this.map.remove(obj, atomicLong);
            return true;
        }
        return false;
    }

    @CanIgnoreReturnValue
    public long addAndGet(K k2, long j2) {
        AtomicLong atomicLong;
        do {
            atomicLong = this.map.get(k2);
            if (atomicLong == null && (atomicLong = this.map.putIfAbsent(k2, new AtomicLong(j2))) == null) {
                return j2;
            }
            while (true) {
                long j3 = atomicLong.get();
                if (j3 == 0) {
                    break;
                }
                long j4 = j3 + j2;
                if (atomicLong.compareAndSet(j3, j4)) {
                    return j4;
                }
            }
        } while (!this.map.replace(k2, atomicLong, new AtomicLong(j2)));
        return j2;
    }

    public Map<K, Long> asMap() {
        Map<K, Long> map = this.asMap;
        if (map == null) {
            Map<K, Long> createAsMap = createAsMap();
            this.asMap = createAsMap;
            return createAsMap;
        }
        return map;
    }

    public void clear() {
        this.map.clear();
    }

    public boolean containsKey(Object obj) {
        return this.map.containsKey(obj);
    }

    @CanIgnoreReturnValue
    public long decrementAndGet(K k2) {
        return addAndGet(k2, -1L);
    }

    public long get(K k2) {
        AtomicLong atomicLong = this.map.get(k2);
        if (atomicLong == null) {
            return 0L;
        }
        return atomicLong.get();
    }

    @CanIgnoreReturnValue
    public long getAndAdd(K k2, long j2) {
        AtomicLong atomicLong;
        do {
            atomicLong = this.map.get(k2);
            if (atomicLong == null && (atomicLong = this.map.putIfAbsent(k2, new AtomicLong(j2))) == null) {
                return 0L;
            }
            while (true) {
                long j3 = atomicLong.get();
                if (j3 == 0) {
                    break;
                } else if (atomicLong.compareAndSet(j3, j3 + j2)) {
                    return j3;
                }
            }
        } while (!this.map.replace(k2, atomicLong, new AtomicLong(j2)));
        return 0L;
    }

    @CanIgnoreReturnValue
    public long getAndDecrement(K k2) {
        return getAndAdd(k2, -1L);
    }

    @CanIgnoreReturnValue
    public long getAndIncrement(K k2) {
        return getAndAdd(k2, 1L);
    }

    @CanIgnoreReturnValue
    public long incrementAndGet(K k2) {
        return addAndGet(k2, 1L);
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @CanIgnoreReturnValue
    public long put(K k2, long j2) {
        AtomicLong atomicLong;
        do {
            atomicLong = this.map.get(k2);
            if (atomicLong == null && (atomicLong = this.map.putIfAbsent(k2, new AtomicLong(j2))) == null) {
                return 0L;
            }
            while (true) {
                long j3 = atomicLong.get();
                if (j3 == 0) {
                    break;
                } else if (atomicLong.compareAndSet(j3, j2)) {
                    return j3;
                }
            }
        } while (!this.map.replace(k2, atomicLong, new AtomicLong(j2)));
        return 0L;
    }

    public void putAll(Map<? extends K, ? extends Long> map) {
        for (Map.Entry<? extends K, ? extends Long> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue().longValue());
        }
    }

    @CanIgnoreReturnValue
    public long remove(K k2) {
        long j2;
        AtomicLong atomicLong = this.map.get(k2);
        if (atomicLong == null) {
            return 0L;
        }
        do {
            j2 = atomicLong.get();
            if (j2 == 0) {
                break;
            }
        } while (!atomicLong.compareAndSet(j2, 0L));
        this.map.remove(k2, atomicLong);
        return j2;
    }

    public void removeAllZeros() {
        Iterator<Map.Entry<K, AtomicLong>> it = this.map.entrySet().iterator();
        while (it.hasNext()) {
            AtomicLong value = it.next().getValue();
            if (value != null && value.get() == 0) {
                it.remove();
            }
        }
    }

    @CanIgnoreReturnValue
    @Beta
    public boolean removeIfZero(K k2) {
        return a(k2, 0L);
    }

    public int size() {
        return this.map.size();
    }

    public long sum() {
        long j2 = 0;
        for (AtomicLong atomicLong : this.map.values()) {
            j2 += atomicLong.get();
        }
        return j2;
    }

    public String toString() {
        return this.map.toString();
    }
}
