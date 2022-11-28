package kotlinx.serialization.descriptors;

import java.util.Iterator;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class SerialDescriptorKt$elementNames$1$1 implements Iterator<String>, KMappedMarker {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ SerialDescriptor f12423a;
    private int elementsLeft;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SerialDescriptorKt$elementNames$1$1(SerialDescriptor serialDescriptor) {
        this.f12423a = serialDescriptor;
        this.elementsLeft = serialDescriptor.getElementsCount();
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.elementsLeft > 0;
    }

    @Override // java.util.Iterator
    @NotNull
    public String next() {
        SerialDescriptor serialDescriptor = this.f12423a;
        int elementsCount = serialDescriptor.getElementsCount();
        int i2 = this.elementsLeft;
        this.elementsLeft = i2 - 1;
        return serialDescriptor.getElementName(elementsCount - i2);
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
