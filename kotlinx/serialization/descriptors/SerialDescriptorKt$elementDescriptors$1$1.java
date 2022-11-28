package kotlinx.serialization.descriptors;

import java.util.Iterator;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class SerialDescriptorKt$elementDescriptors$1$1 implements Iterator<SerialDescriptor>, KMappedMarker {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ SerialDescriptor f12422a;
    private int elementsLeft;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SerialDescriptorKt$elementDescriptors$1$1(SerialDescriptor serialDescriptor) {
        this.f12422a = serialDescriptor;
        this.elementsLeft = serialDescriptor.getElementsCount();
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.elementsLeft > 0;
    }

    @Override // java.util.Iterator
    @NotNull
    public SerialDescriptor next() {
        SerialDescriptor serialDescriptor = this.f12422a;
        int elementsCount = serialDescriptor.getElementsCount();
        int i2 = this.elementsLeft;
        this.elementsLeft = i2 - 1;
        return serialDescriptor.getElementDescriptor(elementsCount - i2);
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
