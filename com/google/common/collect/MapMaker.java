package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Ascii;
import com.google.common.base.Equivalence;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.MapMakerInternalMap;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible(emulated = true)
/* loaded from: classes2.dex */
public final class MapMaker {
    private static final int DEFAULT_CONCURRENCY_LEVEL = 4;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    /* renamed from: a  reason: collision with root package name */
    boolean f8734a;

    /* renamed from: b  reason: collision with root package name */
    int f8735b = -1;

    /* renamed from: c  reason: collision with root package name */
    int f8736c = -1;
    @NullableDecl

    /* renamed from: d  reason: collision with root package name */
    MapMakerInternalMap.Strength f8737d;
    @NullableDecl

    /* renamed from: e  reason: collision with root package name */
    MapMakerInternalMap.Strength f8738e;
    @NullableDecl

    /* renamed from: f  reason: collision with root package name */
    Equivalence f8739f;

    /* loaded from: classes2.dex */
    enum Dummy {
        VALUE
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int a() {
        int i2 = this.f8736c;
        if (i2 == -1) {
            return 4;
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int b() {
        int i2 = this.f8735b;
        if (i2 == -1) {
            return 16;
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Equivalence c() {
        return (Equivalence) MoreObjects.firstNonNull(this.f8739f, d().a());
    }

    @CanIgnoreReturnValue
    public MapMaker concurrencyLevel(int i2) {
        int i3 = this.f8736c;
        Preconditions.checkState(i3 == -1, "concurrency level was already set to %s", i3);
        Preconditions.checkArgument(i2 > 0);
        this.f8736c = i2;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MapMakerInternalMap.Strength d() {
        return (MapMakerInternalMap.Strength) MoreObjects.firstNonNull(this.f8737d, MapMakerInternalMap.Strength.STRONG);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MapMakerInternalMap.Strength e() {
        return (MapMakerInternalMap.Strength) MoreObjects.firstNonNull(this.f8738e, MapMakerInternalMap.Strength.STRONG);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @CanIgnoreReturnValue
    @GwtIncompatible
    public MapMaker f(Equivalence equivalence) {
        Equivalence equivalence2 = this.f8739f;
        Preconditions.checkState(equivalence2 == null, "key equivalence was already set to %s", equivalence2);
        this.f8739f = (Equivalence) Preconditions.checkNotNull(equivalence);
        this.f8734a = true;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MapMaker g(MapMakerInternalMap.Strength strength) {
        MapMakerInternalMap.Strength strength2 = this.f8737d;
        Preconditions.checkState(strength2 == null, "Key strength was already set to %s", strength2);
        this.f8737d = (MapMakerInternalMap.Strength) Preconditions.checkNotNull(strength);
        if (strength != MapMakerInternalMap.Strength.STRONG) {
            this.f8734a = true;
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MapMaker h(MapMakerInternalMap.Strength strength) {
        MapMakerInternalMap.Strength strength2 = this.f8738e;
        Preconditions.checkState(strength2 == null, "Value strength was already set to %s", strength2);
        this.f8738e = (MapMakerInternalMap.Strength) Preconditions.checkNotNull(strength);
        if (strength != MapMakerInternalMap.Strength.STRONG) {
            this.f8734a = true;
        }
        return this;
    }

    @CanIgnoreReturnValue
    public MapMaker initialCapacity(int i2) {
        int i3 = this.f8735b;
        Preconditions.checkState(i3 == -1, "initial capacity was already set to %s", i3);
        Preconditions.checkArgument(i2 >= 0);
        this.f8735b = i2;
        return this;
    }

    public <K, V> ConcurrentMap<K, V> makeMap() {
        return !this.f8734a ? new ConcurrentHashMap(b(), 0.75f, a()) : MapMakerInternalMap.b(this);
    }

    public String toString() {
        MoreObjects.ToStringHelper stringHelper = MoreObjects.toStringHelper(this);
        int i2 = this.f8735b;
        if (i2 != -1) {
            stringHelper.add("initialCapacity", i2);
        }
        int i3 = this.f8736c;
        if (i3 != -1) {
            stringHelper.add("concurrencyLevel", i3);
        }
        MapMakerInternalMap.Strength strength = this.f8737d;
        if (strength != null) {
            stringHelper.add("keyStrength", Ascii.toLowerCase(strength.toString()));
        }
        MapMakerInternalMap.Strength strength2 = this.f8738e;
        if (strength2 != null) {
            stringHelper.add("valueStrength", Ascii.toLowerCase(strength2.toString()));
        }
        if (this.f8739f != null) {
            stringHelper.addValue("keyEquivalence");
        }
        return stringHelper.toString();
    }

    @CanIgnoreReturnValue
    @GwtIncompatible
    public MapMaker weakKeys() {
        return g(MapMakerInternalMap.Strength.WEAK);
    }

    @CanIgnoreReturnValue
    @GwtIncompatible
    public MapMaker weakValues() {
        return h(MapMakerInternalMap.Strength.WEAK);
    }
}
