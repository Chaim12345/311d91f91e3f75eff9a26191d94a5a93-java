package kotlinx.serialization.json.internal;

import java.util.Set;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.ExperimentalSerializationApi;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class StreamingJsonEncoderKt {
    @NotNull
    private static final Set<SerialDescriptor> unsignedNumberDescriptors;

    static {
        Set<SerialDescriptor> of;
        of = SetsKt__SetsKt.setOf((Object[]) new SerialDescriptor[]{BuiltinSerializersKt.serializer(UInt.Companion).getDescriptor(), BuiltinSerializersKt.serializer(ULong.Companion).getDescriptor(), BuiltinSerializersKt.serializer(UByte.Companion).getDescriptor(), BuiltinSerializersKt.serializer(UShort.Companion).getDescriptor()});
        unsignedNumberDescriptors = of;
    }

    @ExperimentalSerializationApi
    private static /* synthetic */ void getUnsignedNumberDescriptors$annotations() {
    }

    public static final boolean isUnsignedNumber(@NotNull SerialDescriptor serialDescriptor) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        return serialDescriptor.isInline() && unsignedNumberDescriptors.contains(serialDescriptor);
    }

    @ExperimentalSerializationApi
    public static /* synthetic */ void isUnsignedNumber$annotations(SerialDescriptor serialDescriptor) {
    }
}
