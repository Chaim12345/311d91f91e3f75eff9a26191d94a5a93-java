package kotlinx.serialization.json.internal;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.json.JsonElement;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
final class AbstractJsonTreeEncoder$beginStructure$consumer$1 extends Lambda implements Function1<JsonElement, Unit> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ AbstractJsonTreeEncoder f12454a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractJsonTreeEncoder$beginStructure$consumer$1(AbstractJsonTreeEncoder abstractJsonTreeEncoder) {
        super(1);
        this.f12454a = abstractJsonTreeEncoder;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(JsonElement jsonElement) {
        invoke2(jsonElement);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull JsonElement node) {
        Intrinsics.checkNotNullParameter(node, "node");
        AbstractJsonTreeEncoder abstractJsonTreeEncoder = this.f12454a;
        abstractJsonTreeEncoder.putElement(AbstractJsonTreeEncoder.access$getCurrentTag(abstractJsonTreeEncoder), node);
    }
}
