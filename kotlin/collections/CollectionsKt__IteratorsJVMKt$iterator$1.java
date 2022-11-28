package kotlin.collections;

import java.util.Enumeration;
import java.util.Iterator;
import kotlin.jvm.internal.markers.KMappedMarker;
/* loaded from: classes3.dex */
public final class CollectionsKt__IteratorsJVMKt$iterator$1 implements Iterator<T>, KMappedMarker {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Enumeration f11067a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CollectionsKt__IteratorsJVMKt$iterator$1(Enumeration enumeration) {
        this.f11067a = enumeration;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f11067a.hasMoreElements();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [T, java.lang.Object] */
    @Override // java.util.Iterator
    public T next() {
        return this.f11067a.nextElement();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
