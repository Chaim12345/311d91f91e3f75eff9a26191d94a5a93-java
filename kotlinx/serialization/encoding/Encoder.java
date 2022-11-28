package kotlinx.serialization.encoding;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.ExperimentalSerializationApi;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.modules.SerializersModule;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public interface Encoder {

    /* loaded from: classes3.dex */
    public static final class DefaultImpls {
        @NotNull
        public static CompositeEncoder beginCollection(@NotNull Encoder encoder, @NotNull SerialDescriptor descriptor, int i2) {
            Intrinsics.checkNotNullParameter(encoder, "this");
            Intrinsics.checkNotNullParameter(descriptor, "descriptor");
            return encoder.beginStructure(descriptor);
        }

        @ExperimentalSerializationApi
        public static void encodeNotNullMark(@NotNull Encoder encoder) {
            Intrinsics.checkNotNullParameter(encoder, "this");
        }

        @ExperimentalSerializationApi
        public static <T> void encodeNullableSerializableValue(@NotNull Encoder encoder, @NotNull SerializationStrategy<? super T> serializer, @Nullable T t2) {
            Intrinsics.checkNotNullParameter(encoder, "this");
            Intrinsics.checkNotNullParameter(serializer, "serializer");
            if (serializer.getDescriptor().isNullable()) {
                encoder.encodeSerializableValue(serializer, t2);
            } else if (t2 == null) {
                encoder.encodeNull();
            } else {
                encoder.encodeNotNullMark();
                encoder.encodeSerializableValue(serializer, t2);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static <T> void encodeSerializableValue(@NotNull Encoder encoder, @NotNull SerializationStrategy<? super T> serializer, T t2) {
            Intrinsics.checkNotNullParameter(encoder, "this");
            Intrinsics.checkNotNullParameter(serializer, "serializer");
            serializer.serialize(encoder, t2);
        }
    }

    @NotNull
    CompositeEncoder beginCollection(@NotNull SerialDescriptor serialDescriptor, int i2);

    @NotNull
    CompositeEncoder beginStructure(@NotNull SerialDescriptor serialDescriptor);

    void encodeBoolean(boolean z);

    void encodeByte(byte b2);

    void encodeChar(char c2);

    void encodeDouble(double d2);

    void encodeEnum(@NotNull SerialDescriptor serialDescriptor, int i2);

    void encodeFloat(float f2);

    @ExperimentalSerializationApi
    @NotNull
    Encoder encodeInline(@NotNull SerialDescriptor serialDescriptor);

    void encodeInt(int i2);

    void encodeLong(long j2);

    @ExperimentalSerializationApi
    void encodeNotNullMark();

    @ExperimentalSerializationApi
    void encodeNull();

    @ExperimentalSerializationApi
    <T> void encodeNullableSerializableValue(@NotNull SerializationStrategy<? super T> serializationStrategy, @Nullable T t2);

    <T> void encodeSerializableValue(@NotNull SerializationStrategy<? super T> serializationStrategy, T t2);

    void encodeShort(short s2);

    void encodeString(@NotNull String str);

    @NotNull
    SerializersModule getSerializersModule();
}
