package kotlinx.serialization.json;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.DescriptorSchemaCache;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class JsonSchemaCacheKt {
    @NotNull
    public static final DescriptorSchemaCache getSchemaCache(@NotNull Json json) {
        Intrinsics.checkNotNullParameter(json, "<this>");
        return json.get_schemaCache$kotlinx_serialization_json();
    }

    public static /* synthetic */ void getSchemaCache$annotations(Json json) {
    }
}
