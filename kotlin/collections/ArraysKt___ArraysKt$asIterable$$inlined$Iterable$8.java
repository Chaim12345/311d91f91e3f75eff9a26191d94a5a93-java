package kotlin.collections;

import java.util.Iterator;
import kotlin.jvm.internal.ArrayIteratorsKt;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class ArraysKt___ArraysKt$asIterable$$inlined$Iterable$8 implements Iterable<Boolean>, KMappedMarker {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ boolean[] f11042a;

    public ArraysKt___ArraysKt$asIterable$$inlined$Iterable$8(boolean[] zArr) {
        this.f11042a = zArr;
    }

    @Override // java.lang.Iterable
    @NotNull
    public Iterator<Boolean> iterator() {
        return ArrayIteratorsKt.iterator(this.f11042a);
    }
}
