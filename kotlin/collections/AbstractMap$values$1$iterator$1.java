package kotlin.collections;

import java.util.Iterator;
import java.util.Map;
import kotlin.jvm.internal.markers.KMappedMarker;
/* loaded from: classes3.dex */
public final class AbstractMap$values$1$iterator$1 implements Iterator<V>, KMappedMarker {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Iterator f11026a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractMap$values$1$iterator$1(Iterator it) {
        this.f11026a = it;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f11026a.hasNext();
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [V, java.lang.Object] */
    @Override // java.util.Iterator
    public V next() {
        return ((Map.Entry) this.f11026a.next()).getValue();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
