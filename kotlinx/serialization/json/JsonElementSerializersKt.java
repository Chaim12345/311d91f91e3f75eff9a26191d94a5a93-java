package kotlinx.serialization.json;

import java.lang.annotation.Annotation;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class JsonElementSerializersKt {
    public static final /* synthetic */ void access$verify(Decoder decoder) {
        verify(decoder);
    }

    public static final /* synthetic */ void access$verify(Encoder encoder) {
        verify(encoder);
    }

    @NotNull
    public static final JsonDecoder asJsonDecoder(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "<this>");
        JsonDecoder jsonDecoder = decoder instanceof JsonDecoder ? (JsonDecoder) decoder : null;
        if (jsonDecoder != null) {
            return jsonDecoder;
        }
        throw new IllegalStateException(Intrinsics.stringPlus("This serializer can be used only with Json format.Expected Decoder to be JsonDecoder, got ", Reflection.getOrCreateKotlinClass(decoder.getClass())));
    }

    @NotNull
    public static final JsonEncoder asJsonEncoder(@NotNull Encoder encoder) {
        Intrinsics.checkNotNullParameter(encoder, "<this>");
        JsonEncoder jsonEncoder = encoder instanceof JsonEncoder ? (JsonEncoder) encoder : null;
        if (jsonEncoder != null) {
            return jsonEncoder;
        }
        throw new IllegalStateException(Intrinsics.stringPlus("This serializer can be used only with Json format.Expected Encoder to be JsonEncoder, got ", Reflection.getOrCreateKotlinClass(encoder.getClass())));
    }

    public static final SerialDescriptor defer(Function0<? extends SerialDescriptor> function0) {
        return new SerialDescriptor() { // from class: kotlinx.serialization.json.JsonElementSerializersKt$defer$1
            @NotNull
            private final Lazy original$delegate;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                Lazy lazy;
                lazy = LazyKt__LazyJVMKt.lazy(Function0.this);
                this.original$delegate = lazy;
            }

            private final SerialDescriptor getOriginal() {
                return (SerialDescriptor) this.original$delegate.getValue();
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            @NotNull
            public List<Annotation> getAnnotations() {
                return SerialDescriptor.DefaultImpls.getAnnotations(this);
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            @NotNull
            public List<Annotation> getElementAnnotations(int i2) {
                return getOriginal().getElementAnnotations(i2);
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            @NotNull
            public SerialDescriptor getElementDescriptor(int i2) {
                return getOriginal().getElementDescriptor(i2);
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public int getElementIndex(@NotNull String name) {
                Intrinsics.checkNotNullParameter(name, "name");
                return getOriginal().getElementIndex(name);
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            @NotNull
            public String getElementName(int i2) {
                return getOriginal().getElementName(i2);
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public int getElementsCount() {
                return getOriginal().getElementsCount();
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            @NotNull
            public SerialKind getKind() {
                return getOriginal().getKind();
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            @NotNull
            public String getSerialName() {
                return getOriginal().getSerialName();
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public boolean isElementOptional(int i2) {
                return getOriginal().isElementOptional(i2);
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public boolean isInline() {
                return SerialDescriptor.DefaultImpls.isInline(this);
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public boolean isNullable() {
                return SerialDescriptor.DefaultImpls.isNullable(this);
            }
        };
    }

    public static final void verify(Decoder decoder) {
        asJsonDecoder(decoder);
    }

    public static final void verify(Encoder encoder) {
        asJsonEncoder(encoder);
    }
}
