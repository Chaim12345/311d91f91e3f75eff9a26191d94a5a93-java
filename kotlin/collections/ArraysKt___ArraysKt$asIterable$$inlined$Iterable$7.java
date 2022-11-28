package kotlin.collections;

import java.util.Iterator;
import kotlin.jvm.internal.ArrayIteratorsKt;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class ArraysKt___ArraysKt$asIterable$$inlined$Iterable$7 implements Iterable<Double>, KMappedMarker {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ double[] f11041a;

    public ArraysKt___ArraysKt$asIterable$$inlined$Iterable$7(double[] dArr) {
        this.f11041a = dArr;
    }

    @Override // java.lang.Iterable
    @NotNull
    public Iterator<Double> iterator() {
        return ArrayIteratorsKt.iterator(this.f11041a);
    }
}
