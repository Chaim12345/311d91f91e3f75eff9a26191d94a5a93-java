package kotlinx.serialization.json;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
final class JsonNull$$cachedSerializer$delegate$1 extends Lambda implements Function0<KSerializer<Object>> {
    public static final JsonNull$$cachedSerializer$delegate$1 INSTANCE = new JsonNull$$cachedSerializer$delegate$1();

    JsonNull$$cachedSerializer$delegate$1() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    public final KSerializer<Object> invoke() {
        return JsonNullSerializer.INSTANCE;
    }
}
