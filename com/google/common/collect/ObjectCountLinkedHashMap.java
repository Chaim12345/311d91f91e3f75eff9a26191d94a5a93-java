package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import java.util.Arrays;
import org.bouncycastle.asn1.cmc.BodyPartID;
@GwtCompatible(emulated = true, serializable = true)
/* loaded from: classes2.dex */
class ObjectCountLinkedHashMap<K> extends ObjectCountHashMap<K> {
    private static final int ENDPOINT = -2;
    @VisibleForTesting

    /* renamed from: f  reason: collision with root package name */
    transient long[] f8911f;
    private transient int firstEntry;
    private transient int lastEntry;

    ObjectCountLinkedHashMap() {
        this(3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ObjectCountLinkedHashMap(int i2) {
        this(i2, 1.0f);
    }

    ObjectCountLinkedHashMap(int i2, float f2) {
        super(i2, f2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public ObjectCountLinkedHashMap(ObjectCountHashMap objectCountHashMap) {
        g(objectCountHashMap.o(), 1.0f);
        int b2 = objectCountHashMap.b();
        while (b2 != -1) {
            put(objectCountHashMap.d(b2), objectCountHashMap.e(b2));
            b2 = objectCountHashMap.j(b2);
        }
    }

    public static <K> ObjectCountLinkedHashMap<K> create() {
        return new ObjectCountLinkedHashMap<>();
    }

    public static <K> ObjectCountLinkedHashMap<K> createWithExpectedSize(int i2) {
        return new ObjectCountLinkedHashMap<>(i2);
    }

    private int getPredecessor(int i2) {
        return (int) (this.f8911f[i2] >>> 32);
    }

    private int getSuccessor(int i2) {
        return (int) this.f8911f[i2];
    }

    private void setPredecessor(int i2, int i3) {
        long[] jArr = this.f8911f;
        jArr[i2] = (jArr[i2] & BodyPartID.bodyIdMax) | (i3 << 32);
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
        long[] jArr = this.f8911f;
        jArr[i2] = (jArr[i2] & (-4294967296L)) | (i3 & BodyPartID.bodyIdMax);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ObjectCountHashMap
    public int b() {
        int i2 = this.firstEntry;
        if (i2 == -2) {
            return -1;
        }
        return i2;
    }

    @Override // com.google.common.collect.ObjectCountHashMap
    public void clear() {
        super.clear();
        this.firstEntry = -2;
        this.lastEntry = -2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ObjectCountHashMap
    public void g(int i2, float f2) {
        super.g(i2, f2);
        this.firstEntry = -2;
        this.lastEntry = -2;
        long[] jArr = new long[i2];
        this.f8911f = jArr;
        Arrays.fill(jArr, -1L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ObjectCountHashMap
    public void h(int i2, Object obj, int i3, int i4) {
        super.h(i2, obj, i3, i4);
        setSucceeds(this.lastEntry, i2);
        setSucceeds(i2, -2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ObjectCountHashMap
    public void i(int i2) {
        int o2 = o() - 1;
        setSucceeds(getPredecessor(i2), getSuccessor(i2));
        if (i2 < o2) {
            setSucceeds(getPredecessor(o2), i2);
            setSucceeds(i2, getSuccessor(o2));
        }
        super.i(i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ObjectCountHashMap
    public int j(int i2) {
        int successor = getSuccessor(i2);
        if (successor == -2) {
            return -1;
        }
        return successor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ObjectCountHashMap
    public int k(int i2, int i3) {
        return i2 == o() ? i3 : i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ObjectCountHashMap
    public void m(int i2) {
        super.m(i2);
        long[] jArr = this.f8911f;
        int length = jArr.length;
        long[] copyOf = Arrays.copyOf(jArr, i2);
        this.f8911f = copyOf;
        Arrays.fill(copyOf, length, i2, -1L);
    }
}
