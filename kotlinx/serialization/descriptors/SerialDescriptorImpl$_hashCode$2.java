package kotlinx.serialization.descriptors;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptorKt;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class SerialDescriptorImpl$_hashCode$2 extends Lambda implements Function0<Integer> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ SerialDescriptorImpl f12418a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SerialDescriptorImpl$_hashCode$2(SerialDescriptorImpl serialDescriptorImpl) {
        super(0);
        this.f12418a = serialDescriptorImpl;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    @NotNull
    public final Integer invoke() {
        SerialDescriptor[] serialDescriptorArr;
        SerialDescriptorImpl serialDescriptorImpl = this.f12418a;
        serialDescriptorArr = serialDescriptorImpl.typeParametersDescriptors;
        return Integer.valueOf(PluginGeneratedSerialDescriptorKt.hashCodeImpl(serialDescriptorImpl, serialDescriptorArr));
    }
}
