package com.appmattus.certificatetransparency.internal.loglist.deserializer;

import com.appmattus.certificatetransparency.internal.loglist.model.v2.State;
import java.util.Map;
import java.util.Objects;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonTransformingSerializer;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public final class StateDeserializer extends JsonTransformingSerializer<State> {
    public StateDeserializer() {
        super(State.Companion.serializer());
    }

    @Override // kotlinx.serialization.json.JsonTransformingSerializer
    @NotNull
    protected JsonElement a(@NotNull JsonElement element) {
        Object first;
        Map plus;
        Intrinsics.checkNotNullParameter(element, "element");
        if (element instanceof JsonObject) {
            JsonObject jsonObject = (JsonObject) element;
            first = CollectionsKt___CollectionsKt.first(jsonObject.keySet());
            String str = (String) first;
            Object obj = jsonObject.get((Object) str);
            Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlinx.serialization.json.JsonObject");
            plus = MapsKt__MapsKt.plus((JsonObject) obj, TuplesKt.to("type", JsonElementKt.JsonPrimitive(str)));
            return new JsonObject(plus);
        }
        return element;
    }
}
