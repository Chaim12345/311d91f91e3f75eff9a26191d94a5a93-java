package kotlinx.serialization.json;

import java.lang.annotation.Annotation;
import java.util.List;
import kotlin.PublishedApi;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.ExperimentalSerializationApi;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializer;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import org.jetbrains.annotations.NotNull;
@PublishedApi
@Serializer(forClass = JsonArray.class)
/* loaded from: classes3.dex */
public final class JsonArraySerializer implements KSerializer<JsonArray> {
    @NotNull
    public static final JsonArraySerializer INSTANCE = new JsonArraySerializer();
    @NotNull
    private static final SerialDescriptor descriptor = JsonArrayDescriptor.INSTANCE;

    /* loaded from: classes3.dex */
    private static final class JsonArrayDescriptor implements SerialDescriptor {
        @NotNull
        public static final JsonArrayDescriptor INSTANCE = new JsonArrayDescriptor();
        @NotNull
        private static final String serialName = "kotlinx.serialization.json.JsonArray";
        private final /* synthetic */ SerialDescriptor $$delegate_0 = BuiltinSerializersKt.ListSerializer(JsonElementSerializer.INSTANCE).getDescriptor();

        private JsonArrayDescriptor() {
        }

        @ExperimentalSerializationApi
        public static /* synthetic */ void getSerialName$annotations() {
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        @NotNull
        public List<Annotation> getAnnotations() {
            return this.$$delegate_0.getAnnotations();
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        @ExperimentalSerializationApi
        @NotNull
        public List<Annotation> getElementAnnotations(int i2) {
            return this.$$delegate_0.getElementAnnotations(i2);
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        @ExperimentalSerializationApi
        @NotNull
        public SerialDescriptor getElementDescriptor(int i2) {
            return this.$$delegate_0.getElementDescriptor(i2);
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        @ExperimentalSerializationApi
        public int getElementIndex(@NotNull String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            return this.$$delegate_0.getElementIndex(name);
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        @ExperimentalSerializationApi
        @NotNull
        public String getElementName(int i2) {
            return this.$$delegate_0.getElementName(i2);
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        public int getElementsCount() {
            return this.$$delegate_0.getElementsCount();
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        @NotNull
        public SerialKind getKind() {
            return this.$$delegate_0.getKind();
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        @NotNull
        public String getSerialName() {
            return serialName;
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        @ExperimentalSerializationApi
        public boolean isElementOptional(int i2) {
            return this.$$delegate_0.isElementOptional(i2);
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        public boolean isInline() {
            return this.$$delegate_0.isInline();
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        public boolean isNullable() {
            return this.$$delegate_0.isNullable();
        }
    }

    private JsonArraySerializer() {
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    public JsonArray deserialize(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        JsonElementSerializersKt.access$verify(decoder);
        return new JsonArray((List) BuiltinSerializersKt.ListSerializer(JsonElementSerializer.INSTANCE).deserialize(decoder));
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(@NotNull Encoder encoder, @NotNull JsonArray value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        JsonElementSerializersKt.access$verify(encoder);
        BuiltinSerializersKt.ListSerializer(JsonElementSerializer.INSTANCE).serialize(encoder, value);
    }
}
