package kotlinx.serialization.encoding;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.ExperimentalSerializationApi;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.modules.SerializersModule;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public interface CompositeDecoder {
    @NotNull
    public static final Companion Companion = Companion.f12424a;
    public static final int DECODE_DONE = -1;
    public static final int UNKNOWN_NAME = -3;

    /* loaded from: classes3.dex */
    public static final class Companion {
        public static final int DECODE_DONE = -1;
        public static final int UNKNOWN_NAME = -3;

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ Companion f12424a = new Companion();

        private Companion() {
        }
    }

    /* loaded from: classes3.dex */
    public static final class DefaultImpls {
        public static int decodeCollectionSize(@NotNull CompositeDecoder compositeDecoder, @NotNull SerialDescriptor descriptor) {
            Intrinsics.checkNotNullParameter(compositeDecoder, "this");
            Intrinsics.checkNotNullParameter(descriptor, "descriptor");
            return -1;
        }

        public static /* synthetic */ Object decodeNullableSerializableElement$default(CompositeDecoder compositeDecoder, SerialDescriptor serialDescriptor, int i2, DeserializationStrategy deserializationStrategy, Object obj, int i3, Object obj2) {
            if (obj2 == null) {
                if ((i3 & 8) != 0) {
                    obj = null;
                }
                return compositeDecoder.decodeNullableSerializableElement(serialDescriptor, i2, deserializationStrategy, obj);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: decodeNullableSerializableElement");
        }

        @ExperimentalSerializationApi
        public static boolean decodeSequentially(@NotNull CompositeDecoder compositeDecoder) {
            Intrinsics.checkNotNullParameter(compositeDecoder, "this");
            return false;
        }

        public static /* synthetic */ Object decodeSerializableElement$default(CompositeDecoder compositeDecoder, SerialDescriptor serialDescriptor, int i2, DeserializationStrategy deserializationStrategy, Object obj, int i3, Object obj2) {
            if (obj2 == null) {
                if ((i3 & 8) != 0) {
                    obj = null;
                }
                return compositeDecoder.decodeSerializableElement(serialDescriptor, i2, deserializationStrategy, obj);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: decodeSerializableElement");
        }
    }

    boolean decodeBooleanElement(@NotNull SerialDescriptor serialDescriptor, int i2);

    byte decodeByteElement(@NotNull SerialDescriptor serialDescriptor, int i2);

    char decodeCharElement(@NotNull SerialDescriptor serialDescriptor, int i2);

    int decodeCollectionSize(@NotNull SerialDescriptor serialDescriptor);

    double decodeDoubleElement(@NotNull SerialDescriptor serialDescriptor, int i2);

    int decodeElementIndex(@NotNull SerialDescriptor serialDescriptor);

    float decodeFloatElement(@NotNull SerialDescriptor serialDescriptor, int i2);

    @ExperimentalSerializationApi
    @NotNull
    Decoder decodeInlineElement(@NotNull SerialDescriptor serialDescriptor, int i2);

    int decodeIntElement(@NotNull SerialDescriptor serialDescriptor, int i2);

    long decodeLongElement(@NotNull SerialDescriptor serialDescriptor, int i2);

    @ExperimentalSerializationApi
    @Nullable
    <T> T decodeNullableSerializableElement(@NotNull SerialDescriptor serialDescriptor, int i2, @NotNull DeserializationStrategy<T> deserializationStrategy, @Nullable T t2);

    @ExperimentalSerializationApi
    boolean decodeSequentially();

    <T> T decodeSerializableElement(@NotNull SerialDescriptor serialDescriptor, int i2, @NotNull DeserializationStrategy<T> deserializationStrategy, @Nullable T t2);

    short decodeShortElement(@NotNull SerialDescriptor serialDescriptor, int i2);

    @NotNull
    String decodeStringElement(@NotNull SerialDescriptor serialDescriptor, int i2);

    void endStructure(@NotNull SerialDescriptor serialDescriptor);

    @NotNull
    SerializersModule getSerializersModule();
}
