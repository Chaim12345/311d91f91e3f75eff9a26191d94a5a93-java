package kotlinx.serialization.json;

import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.functions.Function0;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import org.jetbrains.annotations.NotNull;
@Serializable(with = JsonNullSerializer.class)
/* loaded from: classes3.dex */
public final class JsonNull extends JsonPrimitive {
    private static final /* synthetic */ Lazy<KSerializer<Object>> $cachedSerializer$delegate;
    @NotNull
    public static final JsonNull INSTANCE = new JsonNull();
    @NotNull
    private static final String content = "null";

    static {
        Lazy<KSerializer<Object>> lazy;
        lazy = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) JsonNull$$cachedSerializer$delegate$1.INSTANCE);
        $cachedSerializer$delegate = lazy;
    }

    private JsonNull() {
        super(null);
    }

    private final /* synthetic */ Lazy get$cachedSerializer$delegate() {
        return $cachedSerializer$delegate;
    }

    @Override // kotlinx.serialization.json.JsonPrimitive
    @NotNull
    public String getContent() {
        return content;
    }

    @Override // kotlinx.serialization.json.JsonPrimitive
    public boolean isString() {
        return false;
    }

    @NotNull
    public final KSerializer<JsonNull> serializer() {
        return (KSerializer) get$cachedSerializer$delegate().getValue();
    }
}
