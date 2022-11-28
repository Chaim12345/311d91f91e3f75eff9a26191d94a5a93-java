package kotlin.collections.unsigned;

import java.util.RandomAccess;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt___ArraysKt;
/* loaded from: classes3.dex */
public final class UArraysKt___UArraysJvmKt$asList$3 extends AbstractList<UByte> implements RandomAccess {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ byte[] f11090a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public UArraysKt___UArraysJvmKt$asList$3(byte[] bArr) {
        this.f11090a = bArr;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof UByte) {
            return m682contains7apg3OU(((UByte) obj).m254unboximpl());
        }
        return false;
    }

    /* renamed from: contains-7apg3OU  reason: not valid java name */
    public boolean m682contains7apg3OU(byte b2) {
        return UByteArray.m258contains7apg3OU(this.f11090a, b2);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public /* bridge */ /* synthetic */ Object get(int i2) {
        return UByte.m199boximpl(m683getw2LRezQ(i2));
    }

    /* renamed from: get-w2LRezQ  reason: not valid java name */
    public byte m683getw2LRezQ(int i2) {
        return UByteArray.m262getw2LRezQ(this.f11090a, i2);
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
    public int getSize() {
        return UByteArray.m263getSizeimpl(this.f11090a);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof UByte) {
            return m684indexOf7apg3OU(((UByte) obj).m254unboximpl());
        }
        return -1;
    }

    /* renamed from: indexOf-7apg3OU  reason: not valid java name */
    public int m684indexOf7apg3OU(byte b2) {
        int indexOf;
        indexOf = ArraysKt___ArraysKt.indexOf(this.f11090a, b2);
        return indexOf;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return UByteArray.m265isEmptyimpl(this.f11090a);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof UByte) {
            return m685lastIndexOf7apg3OU(((UByte) obj).m254unboximpl());
        }
        return -1;
    }

    /* renamed from: lastIndexOf-7apg3OU  reason: not valid java name */
    public int m685lastIndexOf7apg3OU(byte b2) {
        int lastIndexOf;
        lastIndexOf = ArraysKt___ArraysKt.lastIndexOf(this.f11090a, b2);
        return lastIndexOf;
    }
}
