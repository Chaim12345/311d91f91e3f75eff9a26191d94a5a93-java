package kotlinx.serialization.descriptors;

import java.util.Iterator;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class SerialDescriptorKt$special$$inlined$Iterable$2 implements Iterable<String>, KMappedMarker {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ SerialDescriptor f12421a;

    public SerialDescriptorKt$special$$inlined$Iterable$2(SerialDescriptor serialDescriptor) {
        this.f12421a = serialDescriptor;
    }

    @Override // java.lang.Iterable
    @NotNull
    public Iterator<String> iterator() {
        return new SerialDescriptorKt$elementNames$1$1(this.f12421a);
    }
}
