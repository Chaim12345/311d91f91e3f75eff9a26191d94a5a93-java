package kotlin.collections;

import java.util.Iterator;
import kotlin.jvm.internal.ArrayIteratorsKt;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class ArraysKt___ArraysKt$asIterable$$inlined$Iterable$6 implements Iterable<Float>, KMappedMarker {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ float[] f11040a;

    public ArraysKt___ArraysKt$asIterable$$inlined$Iterable$6(float[] fArr) {
        this.f11040a = fArr;
    }

    @Override // java.lang.Iterable
    @NotNull
    public Iterator<Float> iterator() {
        return ArrayIteratorsKt.iterator(this.f11040a);
    }
}
