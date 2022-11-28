package kotlin.collections.unsigned;

import java.util.RandomAccess;
import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt___ArraysKt;
/* loaded from: classes3.dex */
public final class UArraysKt___UArraysJvmKt$asList$1 extends AbstractList<UInt> implements RandomAccess {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ int[] f11088a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public UArraysKt___UArraysJvmKt$asList$1(int[] iArr) {
        this.f11088a = iArr;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof UInt) {
            return m674containsWZ4Q5Ns(((UInt) obj).m332unboximpl());
        }
        return false;
    }

    /* renamed from: contains-WZ4Q5Ns  reason: not valid java name */
    public boolean m674containsWZ4Q5Ns(int i2) {
        return UIntArray.m336containsWZ4Q5Ns(this.f11088a, i2);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public /* bridge */ /* synthetic */ Object get(int i2) {
        return UInt.m275boximpl(m675getpVg5ArA(i2));
    }

    /* renamed from: get-pVg5ArA  reason: not valid java name */
    public int m675getpVg5ArA(int i2) {
        return UIntArray.m340getpVg5ArA(this.f11088a, i2);
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
    public int getSize() {
        return UIntArray.m341getSizeimpl(this.f11088a);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof UInt) {
            return m676indexOfWZ4Q5Ns(((UInt) obj).m332unboximpl());
        }
        return -1;
    }

    /* renamed from: indexOf-WZ4Q5Ns  reason: not valid java name */
    public int m676indexOfWZ4Q5Ns(int i2) {
        int indexOf;
        indexOf = ArraysKt___ArraysKt.indexOf(this.f11088a, i2);
        return indexOf;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return UIntArray.m343isEmptyimpl(this.f11088a);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof UInt) {
            return m677lastIndexOfWZ4Q5Ns(((UInt) obj).m332unboximpl());
        }
        return -1;
    }

    /* renamed from: lastIndexOf-WZ4Q5Ns  reason: not valid java name */
    public int m677lastIndexOfWZ4Q5Ns(int i2) {
        int lastIndexOf;
        lastIndexOf = ArraysKt___ArraysKt.lastIndexOf(this.f11088a, i2);
        return lastIndexOf;
    }
}
