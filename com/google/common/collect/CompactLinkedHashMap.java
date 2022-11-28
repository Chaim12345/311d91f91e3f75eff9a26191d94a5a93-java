package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* JADX INFO: Access modifiers changed from: package-private */
@GwtIncompatible
/* loaded from: classes2.dex */
public class CompactLinkedHashMap<K, V> extends CompactHashMap<K, V> {
    private static final int ENDPOINT = -2;
    private final boolean accessOrder;
    @VisibleForTesting
    @NullableDecl

    /* renamed from: d  reason: collision with root package name */
    transient long[] f8481d;
    private transient int firstEntry;
    private transient int lastEntry;

    CompactLinkedHashMap() {
        this(3);
    }

    CompactLinkedHashMap(int i2) {
        this(i2, false);
    }

    CompactLinkedHashMap(int i2, boolean z) {
        super(i2);
        this.accessOrder = z;
    }

    public static <K, V> CompactLinkedHashMap<K, V> create() {
        return new CompactLinkedHashMap<>();
    }

    public static <K, V> CompactLinkedHashMap<K, V> createWithExpectedSize(int i2) {
        return new CompactLinkedHashMap<>(i2);
    }

    private int getPredecessor(int i2) {
        return ((int) (this.f8481d[i2] >>> 32)) - 1;
    }

    private void setPredecessor(int i2, int i3) {
        long[] jArr = this.f8481d;
        jArr[i2] = (jArr[i2] & BodyPartID.bodyIdMax) | ((i3 + 1) << 32);
    }

    private void setSucceeds(int i2, int i3) {
        if (i2 == -2) {
            this.firstEntry = i3;
        } else {
            setSuccessor(i2, i3);
        }
        if (i3 == -2) {
            this.lastEntry = i2;
        } else {
            setPredecessor(i3, i2);
        }
    }

    private void setSuccessor(int i2, int i3) {
        long[] jArr = this.f8481d;
        jArr[i2] = (jArr[i2] & (-4294967296L)) | ((i3 + 1) & BodyPartID.bodyIdMax);
    }

    @Override // com.google.common.collect.CompactHashMap, java.util.AbstractMap, java.util.Map
    public void clear() {
        if (y()) {
            return;
        }
        this.firstEntry = -2;
        this.lastEntry = -2;
        long[] jArr = this.f8481d;
        if (jArr != null) {
            Arrays.fill(jArr, 0, size(), 0L);
        }
        super.clear();
    }

    @Override // com.google.common.collect.CompactHashMap
    void h(int i2) {
        if (this.accessOrder) {
            setSucceeds(getPredecessor(i2), s(i2));
            setSucceeds(this.lastEntry, i2);
            setSucceeds(i2, -2);
            t();
        }
    }

    @Override // com.google.common.collect.CompactHashMap
    int i(int i2, int i3) {
        return i2 >= size() ? i3 : i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.CompactHashMap
    public int j() {
        int j2 = super.j();
        this.f8481d = new long[j2];
        return j2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.CompactHashMap
    @CanIgnoreReturnValue
    public Map k() {
        Map k2 = super.k();
        this.f8481d = null;
        return k2;
    }

    @Override // com.google.common.collect.CompactHashMap
    Map m(int i2) {
        return new LinkedHashMap(i2, 1.0f, this.accessOrder);
    }

    @Override // com.google.common.collect.CompactHashMap
    int r() {
        return this.firstEntry;
    }

    @Override // com.google.common.collect.CompactHashMap
    int s(int i2) {
        return ((int) this.f8481d[i2]) - 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.CompactHashMap
    public void u(int i2) {
        super.u(i2);
        this.firstEntry = -2;
        this.lastEntry = -2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.CompactHashMap
    public void v(int i2, @NullableDecl Object obj, @NullableDecl Object obj2, int i3, int i4) {
        super.v(i2, obj, obj2, i3, i4);
        setSucceeds(this.lastEntry, i2);
        setSucceeds(i2, -2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.CompactHashMap
    public void x(int i2, int i3) {
        int size = size() - 1;
        super.x(i2, i3);
        setSucceeds(getPredecessor(i2), s(i2));
        if (i2 < size) {
            setSucceeds(getPredecessor(size), i2);
            setSucceeds(i2, s(size));
        }
        this.f8481d[size] = 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.CompactHashMap
    public void z(int i2) {
        super.z(i2);
        this.f8481d = Arrays.copyOf(this.f8481d, i2);
    }
}
