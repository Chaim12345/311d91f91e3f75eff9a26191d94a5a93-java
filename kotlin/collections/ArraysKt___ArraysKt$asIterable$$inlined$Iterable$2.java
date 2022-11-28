package kotlin.collections;

import java.util.Iterator;
import kotlin.jvm.internal.ArrayIteratorsKt;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class ArraysKt___ArraysKt$asIterable$$inlined$Iterable$2 implements Iterable<Byte>, KMappedMarker {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ byte[] f11036a;

    public ArraysKt___ArraysKt$asIterable$$inlined$Iterable$2(byte[] bArr) {
        this.f11036a = bArr;
    }

    @Override // java.lang.Iterable
    @NotNull
    public Iterator<Byte> iterator() {
        return ArrayIteratorsKt.iterator(this.f11036a);
    }
}
