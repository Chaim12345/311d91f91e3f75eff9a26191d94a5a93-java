package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableMap;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
@GwtCompatible(emulated = true, serializable = true)
/* loaded from: classes2.dex */
public abstract class ImmutableBiMap<K, V> extends ImmutableMap<K, V> implements BiMap<K, V> {

    /* loaded from: classes2.dex */
    public static final class Builder<K, V> extends ImmutableMap.Builder<K, V> {
        public Builder() {
        }

        Builder(int i2) {
            super(i2);
        }

        @Override // com.google.common.collect.ImmutableMap.Builder
        public ImmutableBiMap<K, V> build() {
            if (this.f8567c == 0) {
                return ImmutableBiMap.of();
            }
            a();
            this.f8568d = true;
            return new RegularImmutableBiMap(this.f8566b, this.f8567c);
        }

        @Override // com.google.common.collect.ImmutableMap.Builder
        @CanIgnoreReturnValue
        @Beta
        public Builder<K, V> orderEntriesByValue(Comparator<? super V> comparator) {
            super.orderEntriesByValue((Comparator) comparator);
            return this;
        }

        @Override // com.google.common.collect.ImmutableMap.Builder
        @CanIgnoreReturnValue
        public Builder<K, V> put(K k2, V v) {
            super.put((Builder<K, V>) k2, (K) v);
            return this;
        }

        @Override // com.google.common.collect.ImmutableMap.Builder
        @CanIgnoreReturnValue
        public Builder<K, V> put(Map.Entry<? extends K, ? extends V> entry) {
            super.put((Map.Entry) entry);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.ImmutableMap.Builder
        @CanIgnoreReturnValue
        public /* bridge */ /* synthetic */ ImmutableMap.Builder put(Object obj, Object obj2) {
            return put((Builder<K, V>) obj, obj2);
        }

        @Override // com.google.common.collect.ImmutableMap.Builder
        @CanIgnoreReturnValue
        @Beta
        public Builder<K, V> putAll(Iterable<? extends Map.Entry<? extends K, ? extends V>> iterable) {
            super.putAll((Iterable) iterable);
            return this;
        }

        @Override // com.google.common.collect.ImmutableMap.Builder
        @CanIgnoreReturnValue
        public Builder<K, V> putAll(Map<? extends K, ? extends V> map) {
            super.putAll((Map) map);
            return this;
        }
    }

    /* loaded from: classes2.dex */
    private static class SerializedForm extends ImmutableMap.SerializedForm {
        private static final long serialVersionUID = 0;
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
    public static <K, V> ImmutableBiMap<K, V> copyOf(Iterable<? extends Map.Entry<? extends K, ? extends V>> iterable) {
        return new Builder(iterable instanceof Collection ? ((Collection) iterable).size() : 4).putAll((Iterable) iterable).build();
    }

    public static <K, V> ImmutableBiMap<K, V> copyOf(Map<? extends K, ? extends V> map) {
        if (map instanceof ImmutableBiMap) {
            ImmutableBiMap<K, V> immutableBiMap = (ImmutableBiMap) map;
            if (!immutableBiMap.h()) {
                return immutableBiMap;
            }
        }
        return copyOf((Iterable) map.entrySet());
    }

    public static <K, V> ImmutableBiMap<K, V> of() {
        return RegularImmutableBiMap.f8927c;
    }

    public static <K, V> ImmutableBiMap<K, V> of(K k2, V v) {
        CollectPreconditions.a(k2, v);
        return new RegularImmutableBiMap(new Object[]{k2, v}, 1);
    }

    public static <K, V> ImmutableBiMap<K, V> of(K k2, V v, K k3, V v2) {
        CollectPreconditions.a(k2, v);
        CollectPreconditions.a(k3, v2);
        return new RegularImmutableBiMap(new Object[]{k2, v, k3, v2}, 2);
    }

    public static <K, V> ImmutableBiMap<K, V> of(K k2, V v, K k3, V v2, K k4, V v3) {
        CollectPreconditions.a(k2, v);
        CollectPreconditions.a(k3, v2);
        CollectPreconditions.a(k4, v3);
        return new RegularImmutableBiMap(new Object[]{k2, v, k3, v2, k4, v3}, 3);
    }

    public static <K, V> ImmutableBiMap<K, V> of(K k2, V v, K k3, V v2, K k4, V v3, K k5, V v4) {
        CollectPreconditions.a(k2, v);
        CollectPreconditions.a(k3, v2);
        CollectPreconditions.a(k4, v3);
        CollectPreconditions.a(k5, v4);
        return new RegularImmutableBiMap(new Object[]{k2, v, k3, v2, k4, v3, k5, v4}, 4);
    }

    public static <K, V> ImmutableBiMap<K, V> of(K k2, V v, K k3, V v2, K k4, V v3, K k5, V v4, K k6, V v5) {
        CollectPreconditions.a(k2, v);
        CollectPreconditions.a(k3, v2);
        CollectPreconditions.a(k4, v3);
        CollectPreconditions.a(k5, v4);
        CollectPreconditions.a(k6, v5);
        return new RegularImmutableBiMap(new Object[]{k2, v, k3, v2, k4, v3, k5, v4, k6, v5}, 5);
    }

    @Override // com.google.common.collect.BiMap
    @CanIgnoreReturnValue
    @Deprecated
    public V forcePut(K k2, V v) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.BiMap
    public abstract ImmutableBiMap<V, K> inverse();

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableMap
    /* renamed from: j */
    public final ImmutableSet e() {
        throw new AssertionError("should never be called");
    }

    @Override // com.google.common.collect.ImmutableMap, java.util.Map, com.google.common.collect.BiMap
    public ImmutableSet<V> values() {
        return inverse().keySet();
    }
}
