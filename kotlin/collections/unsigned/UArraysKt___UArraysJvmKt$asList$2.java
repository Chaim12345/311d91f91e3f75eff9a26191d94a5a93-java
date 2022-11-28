package kotlin.collections.unsigned;

import java.util.RandomAccess;
import kotlin.ULong;
import kotlin.ULongArray;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt___ArraysKt;
/* loaded from: classes3.dex */
public final class UArraysKt___UArraysJvmKt$asList$2 extends AbstractList<ULong> implements RandomAccess {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ long[] f11089a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public UArraysKt___UArraysJvmKt$asList$2(long[] jArr) {
        this.f11089a = jArr;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof ULong) {
            return m678containsVKZWuLQ(((ULong) obj).m410unboximpl());
        }
        return false;
    }

    /* renamed from: contains-VKZWuLQ  reason: not valid java name */
    public boolean m678containsVKZWuLQ(long j2) {
        return ULongArray.m414containsVKZWuLQ(this.f11089a, j2);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public /* bridge */ /* synthetic */ Object get(int i2) {
        return ULong.m353boximpl(m679getsVKNKU(i2));
    }

    /* renamed from: get-s-VKNKU  reason: not valid java name */
    public long m679getsVKNKU(int i2) {
        return ULongArray.m418getsVKNKU(this.f11089a, i2);
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
    public int getSize() {
        return ULongArray.m419getSizeimpl(this.f11089a);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof ULong) {
            return m680indexOfVKZWuLQ(((ULong) obj).m410unboximpl());
        }
        return -1;
    }

    /* renamed from: indexOf-VKZWuLQ  reason: not valid java name */
    public int m680indexOfVKZWuLQ(long j2) {
        int indexOf;
        indexOf = ArraysKt___ArraysKt.indexOf(this.f11089a, j2);
        return indexOf;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return ULongArray.m421isEmptyimpl(this.f11089a);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof ULong) {
            return m681lastIndexOfVKZWuLQ(((ULong) obj).m410unboximpl());
        }
        return -1;
    }

    /* renamed from: lastIndexOf-VKZWuLQ  reason: not valid java name */
    public int m681lastIndexOfVKZWuLQ(long j2) {
        int lastIndexOf;
        lastIndexOf = ArraysKt___ArraysKt.lastIndexOf(this.f11089a, j2);
        return lastIndexOf;
    }
}
