package kotlinx.serialization.json;

import kotlin.PublishedApi;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializer;
import kotlinx.serialization.descriptors.PolymorphicKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import org.jetbrains.annotations.NotNull;
@PublishedApi
@Serializer(forClass = JsonElement.class)
/* loaded from: classes3.dex */
public final class JsonElementSerializer implements KSerializer<JsonElement> {
    @NotNull
    public static final JsonElementSerializer INSTANCE = new JsonElementSerializer();
    @NotNull
    private static final SerialDescriptor descriptor = SerialDescriptorsKt.buildSerialDescriptor("kotlinx.serialization.json.JsonElement", PolymorphicKind.SEALED.INSTANCE, new SerialDescriptor[0], JsonElementSerializer$descriptor$1.INSTANCE);

    private JsonElementSerializer() {
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    public JsonElement deserialize(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        return JsonElementSerializersKt.asJsonDecoder(decoder).decodeJsonElement();
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(@NotNull Encoder encoder, @NotNull JsonElement value) {
        DeserializationStrategy deserializationStrategy;
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        JsonElementSerializersKt.access$verify(encoder);
        if (value instanceof JsonPrimitive) {
            deserializationStrategy = JsonPrimitiveSerializer.INSTANCE;
        } else if (value instanceof JsonObject) {
            deserializationStrategy = JsonObjectSerializer.INSTANCE;
        } else if (!(value instanceof JsonArray)) {
            return;
        } else {
            deserializationStrategy = JsonArraySerializer.INSTANCE;
        }
        encoder.encodeSerializableValue(deserializationStrategy, value);
    }
}
