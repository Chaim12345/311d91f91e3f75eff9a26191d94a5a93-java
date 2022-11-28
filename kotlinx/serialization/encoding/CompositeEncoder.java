package kotlinx.serialization.encoding;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.ExperimentalSerializationApi;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.modules.SerializersModule;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public interface CompositeEncoder {

    /* loaded from: classes3.dex */
    public static final class DefaultImpls {
        @ExperimentalSerializationApi
        public static boolean shouldEncodeElementDefault(@NotNull CompositeEncoder compositeEncoder, @NotNull SerialDescriptor descriptor, int i2) {
            Intrinsics.checkNotNullParameter(compositeEncoder, "this");
            Intrinsics.checkNotNullParameter(descriptor, "descriptor");
            return true;
        }
    }

    void encodeBooleanElement(@NotNull SerialDescriptor serialDescriptor, int i2, boolean z);

    void encodeByteElement(@NotNull SerialDescriptor serialDescriptor, int i2, byte b2);

    void encodeCharElement(@NotNull SerialDescriptor serialDescriptor, int i2, char c2);

    void encodeDoubleElement(@NotNull SerialDescriptor serialDescriptor, int i2, double d2);

    void encodeFloatElement(@NotNull SerialDescriptor serialDescriptor, int i2, float f2);

    @ExperimentalSerializationApi
    @NotNull
    Encoder encodeInlineElement(@NotNull SerialDescriptor serialDescriptor, int i2);

    void encodeIntElement(@NotNull SerialDescriptor serialDescriptor, int i2, int i3);

    void encodeLongElement(@NotNull SerialDescriptor serialDescriptor, int i2, long j2);

    @ExperimentalSerializationApi
    <T> void encodeNullableSerializableElement(@NotNull SerialDescriptor serialDescriptor, int i2, @NotNull SerializationStrategy<? super T> serializationStrategy, @Nullable T t2);

    <T> void encodeSerializableElement(@NotNull SerialDescriptor serialDescriptor, int i2, @NotNull SerializationStrategy<? super T> serializationStrategy, T t2);

    void encodeShortElement(@NotNull SerialDescriptor serialDescriptor, int i2, short s2);

    void encodeStringElement(@NotNull SerialDescriptor serialDescriptor, int i2, @NotNull String str);

    void endStructure(@NotNull SerialDescriptor serialDescriptor);

    @NotNull
    SerializersModule getSerializersModule();

    @ExperimentalSerializationApi
    boolean shouldEncodeElementDefault(@NotNull SerialDescriptor serialDescriptor, int i2);
}
