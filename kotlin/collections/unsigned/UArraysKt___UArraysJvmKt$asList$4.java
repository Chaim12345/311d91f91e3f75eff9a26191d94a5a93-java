package kotlin.collections.unsigned;

import java.util.RandomAccess;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt___ArraysKt;
/* loaded from: classes3.dex */
public final class UArraysKt___UArraysJvmKt$asList$4 extends AbstractList<UShort> implements RandomAccess {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ short[] f11091a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public UArraysKt___UArraysJvmKt$asList$4(short[] sArr) {
        this.f11091a = sArr;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof UShort) {
            return m686containsxj2QHRw(((UShort) obj).m514unboximpl());
        }
        return false;
    }

    /* renamed from: contains-xj2QHRw  reason: not valid java name */
    public boolean m686containsxj2QHRw(short s2) {
        return UShortArray.m518containsxj2QHRw(this.f11091a, s2);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public /* bridge */ /* synthetic */ Object get(int i2) {
        return UShort.m459boximpl(m687getMh2AYeg(i2));
    }

    /* renamed from: get-Mh2AYeg  reason: not valid java name */
    public short m687getMh2AYeg(int i2) {
        return UShortArray.m522getMh2AYeg(this.f11091a, i2);
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
    public int getSize() {
        return UShortArray.m523getSizeimpl(this.f11091a);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof UShort) {
            return m688indexOfxj2QHRw(((UShort) obj).m514unboximpl());
        }
        return -1;
    }

    /* renamed from: indexOf-xj2QHRw  reason: not valid java name */
    public int m688indexOfxj2QHRw(short s2) {
        int indexOf;
        indexOf = ArraysKt___ArraysKt.indexOf(this.f11091a, s2);
        return indexOf;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return UShortArray.m525isEmptyimpl(this.f11091a);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof UShort) {
            return m689lastIndexOfxj2QHRw(((UShort) obj).m514unboximpl());
        }
        return -1;
    }

    /* renamed from: lastIndexOf-xj2QHRw  reason: not valid java name */
    public int m689lastIndexOfxj2QHRw(short s2) {
        int lastIndexOf;
        lastIndexOf = ArraysKt___ArraysKt.lastIndexOf(this.f11091a, s2);
        return lastIndexOf;
    }
}
