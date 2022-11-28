package kotlinx.serialization.internal;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
final class PluginGeneratedSerialDescriptor$toString$1 extends Lambda implements Function1<Integer, CharSequence> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ PluginGeneratedSerialDescriptor f12440a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PluginGeneratedSerialDescriptor$toString$1(PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor) {
        super(1);
        this.f12440a = pluginGeneratedSerialDescriptor;
    }

    @NotNull
    public final CharSequence invoke(int i2) {
        return this.f12440a.getElementName(i2) + ": " + this.f12440a.getElementDescriptor(i2).getSerialName();
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ CharSequence invoke(Integer num) {
        return invoke(num.intValue());
    }
}
