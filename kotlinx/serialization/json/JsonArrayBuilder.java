package kotlinx.serialization.json;

import java.util.ArrayList;
import java.util.List;
import kotlin.PublishedApi;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@JsonDslMarker
/* loaded from: classes3.dex */
public final class JsonArrayBuilder {
    @NotNull
    private final List<JsonElement> content = new ArrayList();

    public final boolean add(@NotNull JsonElement element) {
        Intrinsics.checkNotNullParameter(element, "element");
        this.content.add(element);
        return true;
    }

    @PublishedApi
    @NotNull
    public final JsonArray build() {
        return new JsonArray(this.content);
    }
}
