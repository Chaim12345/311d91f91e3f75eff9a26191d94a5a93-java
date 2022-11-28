package kotlinx.serialization.json;

import java.util.Map;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import kotlinx.serialization.json.internal.StringOpsKt;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
final class JsonObject$toString$1 extends Lambda implements Function1<Map.Entry<? extends String, ? extends JsonElement>, CharSequence> {
    public static final JsonObject$toString$1 INSTANCE = new JsonObject$toString$1();

    JsonObject$toString$1() {
        super(1);
    }

    @NotNull
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final CharSequence invoke2(@NotNull Map.Entry<String, ? extends JsonElement> dstr$k$v) {
        Intrinsics.checkNotNullParameter(dstr$k$v, "$dstr$k$v");
        StringBuilder sb = new StringBuilder();
        StringOpsKt.printQuoted(sb, dstr$k$v.getKey());
        sb.append(AbstractJsonLexerKt.COLON);
        sb.append(dstr$k$v.getValue());
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ CharSequence invoke(Map.Entry<? extends String, ? extends JsonElement> entry) {
        return invoke2((Map.Entry<String, ? extends JsonElement>) entry);
    }
}
