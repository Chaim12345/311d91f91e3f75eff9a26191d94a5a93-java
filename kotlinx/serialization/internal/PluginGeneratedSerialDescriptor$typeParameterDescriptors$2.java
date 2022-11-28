package kotlinx.serialization.internal;

import java.util.ArrayList;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class PluginGeneratedSerialDescriptor$typeParameterDescriptors$2 extends Lambda implements Function0<SerialDescriptor[]> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ PluginGeneratedSerialDescriptor f12441a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PluginGeneratedSerialDescriptor$typeParameterDescriptors$2(PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor) {
        super(0);
        this.f12441a = pluginGeneratedSerialDescriptor;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    public final SerialDescriptor[] invoke() {
        GeneratedSerializer generatedSerializer;
        KSerializer<?>[] typeParametersSerializers;
        generatedSerializer = this.f12441a.generatedSerializer;
        ArrayList arrayList = null;
        if (generatedSerializer != null && (typeParametersSerializers = generatedSerializer.typeParametersSerializers()) != null) {
            arrayList = new ArrayList(typeParametersSerializers.length);
            int i2 = 0;
            int length = typeParametersSerializers.length;
            while (i2 < length) {
                KSerializer<?> kSerializer = typeParametersSerializers[i2];
                i2++;
                arrayList.add(kSerializer.getDescriptor());
            }
        }
        return Platform_commonKt.compactArray(arrayList);
    }
}
