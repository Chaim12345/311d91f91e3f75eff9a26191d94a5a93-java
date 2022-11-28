package kotlinx.serialization.internal;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class PluginGeneratedSerialDescriptor$childSerializers$2 extends Lambda implements Function0<KSerializer<?>[]> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ PluginGeneratedSerialDescriptor f12439a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PluginGeneratedSerialDescriptor$childSerializers$2(PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor) {
        super(0);
        this.f12439a = pluginGeneratedSerialDescriptor;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    public final KSerializer<?>[] invoke() {
        GeneratedSerializer generatedSerializer;
        generatedSerializer = this.f12439a.generatedSerializer;
        KSerializer<?>[] childSerializers = generatedSerializer == null ? null : generatedSerializer.childSerializers();
        return childSerializers == null ? PluginHelperInterfacesKt.EMPTY_SERIALIZER_ARRAY : childSerializers;
    }
}
