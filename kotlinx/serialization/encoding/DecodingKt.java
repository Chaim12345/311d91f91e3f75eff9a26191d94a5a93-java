package kotlinx.serialization.encoding;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.descriptors.SerialDescriptor;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class DecodingKt {
    @NotNull
    private static final String decodeMethodDeprecated = "Please migrate to decodeElement method which accepts old value.Feel free to ignore it if your format does not support updates.";

    public static final <T> T decodeStructure(@NotNull Decoder decoder, @NotNull SerialDescriptor descriptor, @NotNull Function1<? super CompositeDecoder, ? extends T> block) {
        Intrinsics.checkNotNullParameter(decoder, "<this>");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(block, "block");
        CompositeDecoder beginStructure = decoder.beginStructure(descriptor);
        try {
            T invoke = block.invoke(beginStructure);
            InlineMarker.finallyStart(1);
            beginStructure.endStructure(descriptor);
            InlineMarker.finallyEnd(1);
            return invoke;
        } finally {
        }
    }
}
