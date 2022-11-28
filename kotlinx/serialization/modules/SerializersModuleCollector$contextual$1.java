package kotlinx.serialization.modules;

import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
final class SerializersModuleCollector$contextual$1 extends Lambda implements Function1<List<? extends KSerializer<?>>, KSerializer<?>> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ KSerializer f12468a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SerializersModuleCollector$contextual$1(KSerializer kSerializer) {
        super(1);
        this.f12468a = kSerializer;
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    public final KSerializer<?> invoke(@NotNull List<? extends KSerializer<?>> it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return this.f12468a;
    }
}
