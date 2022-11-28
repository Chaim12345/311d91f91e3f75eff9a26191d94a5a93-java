package kotlinx.serialization.json;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.ExperimentalSerializationApi;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Encoder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public interface JsonEncoder extends Encoder, CompositeEncoder {

    /* loaded from: classes3.dex */
    public static final class DefaultImpls {
        @NotNull
        public static CompositeEncoder beginCollection(@NotNull JsonEncoder jsonEncoder, @NotNull SerialDescriptor descriptor, int i2) {
            Intrinsics.checkNotNullParameter(jsonEncoder, "this");
            Intrinsics.checkNotNullParameter(descriptor, "descriptor");
            return Encoder.DefaultImpls.beginCollection(jsonEncoder, descriptor, i2);
        }

        @ExperimentalSerializationApi
        public static void encodeNotNullMark(@NotNull JsonEncoder jsonEncoder) {
            Intrinsics.checkNotNullParameter(jsonEncoder, "this");
            Encoder.DefaultImpls.encodeNotNullMark(jsonEncoder);
        }

        @ExperimentalSerializationApi
        public static <T> void encodeNullableSerializableValue(@NotNull JsonEncoder jsonEncoder, @NotNull SerializationStrategy<? super T> serializer, @Nullable T t2) {
            Intrinsics.checkNotNullParameter(jsonEncoder, "this");
            Intrinsics.checkNotNullParameter(serializer, "serializer");
            Encoder.DefaultImpls.encodeNullableSerializableValue(jsonEncoder, serializer, t2);
        }

        public static <T> void encodeSerializableValue(@NotNull JsonEncoder jsonEncoder, @NotNull SerializationStrategy<? super T> serializer, T t2) {
            Intrinsics.checkNotNullParameter(jsonEncoder, "this");
            Intrinsics.checkNotNullParameter(serializer, "serializer");
            Encoder.DefaultImpls.encodeSerializableValue(jsonEncoder, serializer, t2);
        }

        @ExperimentalSerializationApi
        public static boolean shouldEncodeElementDefault(@NotNull JsonEncoder jsonEncoder, @NotNull SerialDescriptor descriptor, int i2) {
            Intrinsics.checkNotNullParameter(jsonEncoder, "this");
            Intrinsics.checkNotNullParameter(descriptor, "descriptor");
            return CompositeEncoder.DefaultImpls.shouldEncodeElementDefault(jsonEncoder, descriptor, i2);
        }
    }

    void encodeJsonElement(@NotNull JsonElement jsonElement);

    @NotNull
    Json getJson();
}
