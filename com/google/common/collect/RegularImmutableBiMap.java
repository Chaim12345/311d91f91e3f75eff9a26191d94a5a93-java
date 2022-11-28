package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.RegularImmutableMap;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible(emulated = true, serializable = true)
/* loaded from: classes2.dex */
final class RegularImmutableBiMap<K, V> extends ImmutableBiMap<K, V> {

    /* renamed from: c  reason: collision with root package name */
    static final RegularImmutableBiMap f8927c = new RegularImmutableBiMap();
    @VisibleForTesting

    /* renamed from: b  reason: collision with root package name */
    final transient Object[] f8928b;
    private final transient RegularImmutableBiMap<V, K> inverse;
    private final transient Object keyHashTable;
    private final transient int keyOffset;
    private final transient int size;

    /* JADX WARN: Multi-variable type inference failed */
    private RegularImmutableBiMap() {
        this.keyHashTable = null;
        this.f8928b = new Object[0];
        this.keyOffset = 0;
        this.size = 0;
        this.inverse = this;
    }

    private RegularImmutableBiMap(Object obj, Object[] objArr, int i2, RegularImmutableBiMap<V, K> regularImmutableBiMap) {
        this.keyHashTable = obj;
        this.f8928b = objArr;
        this.keyOffset = 1;
        this.size = i2;
        this.inverse = regularImmutableBiMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RegularImmutableBiMap(Object[] objArr, int i2) {
        this.f8928b = objArr;
        this.size = i2;
        this.keyOffset = 0;
        int g2 = i2 >= 2 ? ImmutableSet.g(i2) : 0;
        this.keyHashTable = RegularImmutableMap.k(objArr, i2, g2, 0);
        this.inverse = new RegularImmutableBiMap<>(RegularImmutableMap.k(objArr, i2, g2, 1), objArr, i2, this);
    }

    @Override // com.google.common.collect.ImmutableMap
    ImmutableSet c() {
        return new RegularImmutableMap.EntrySet(this, this.f8928b, this.keyOffset, this.size);
    }

    @Override // com.google.common.collect.ImmutableMap
    ImmutableSet d() {
        return new RegularImmutableMap.KeySet(this, new RegularImmutableMap.KeysOrValuesAsList(this.f8928b, this.keyOffset, this.size));
    }

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    public V get(@NullableDecl Object obj) {
        return (V) RegularImmutableMap.l(this.keyHashTable, this.f8928b, this.size, this.keyOffset, obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableMap
    public boolean h() {
        return false;
    }

    @Override // com.google.common.collect.ImmutableBiMap, com.google.common.collect.BiMap
    public ImmutableBiMap<V, K> inverse() {
        return this.inverse;
    }

    @Override // java.util.Map
    public int size() {
        return this.size;
    }
}
