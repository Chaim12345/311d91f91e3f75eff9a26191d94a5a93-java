package kotlin.collections;

import java.util.Iterator;
import kotlin.jvm.internal.ArrayIteratorsKt;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class ArraysKt___ArraysKt$asIterable$$inlined$Iterable$3 implements Iterable<Short>, KMappedMarker {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ short[] f11037a;

    public ArraysKt___ArraysKt$asIterable$$inlined$Iterable$3(short[] sArr) {
        this.f11037a = sArr;
    }

    @Override // java.lang.Iterable
    @NotNull
    public Iterator<Short> iterator() {
        return ArrayIteratorsKt.iterator(this.f11037a);
    }
}
