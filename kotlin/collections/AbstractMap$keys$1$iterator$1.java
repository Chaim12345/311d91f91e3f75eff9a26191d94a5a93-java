package kotlin.collections;

import java.util.Iterator;
import java.util.Map;
import kotlin.jvm.internal.markers.KMappedMarker;
/* loaded from: classes3.dex */
public final class AbstractMap$keys$1$iterator$1 implements Iterator<K>, KMappedMarker {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Iterator f11023a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractMap$keys$1$iterator$1(Iterator it) {
        this.f11023a = it;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f11023a.hasNext();
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Object, K] */
    @Override // java.util.Iterator
    public K next() {
        return ((Map.Entry) this.f11023a.next()).getKey();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
