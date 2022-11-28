package com.appmattus.certificatetransparency.internal.loglist.model.v2;

import com.appmattus.certificatetransparency.internal.loglist.deserializer.Rfc3339Deserializer;
import com.appmattus.certificatetransparency.internal.loglist.model.v2.State;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import org.jetbrains.annotations.NotNull;
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* loaded from: classes.dex */
public final class State$Pending$$serializer implements GeneratedSerializer<State.Pending> {
    @NotNull
    public static final State$Pending$$serializer INSTANCE;
    public static final /* synthetic */ SerialDescriptor descriptor;

    static {
        State$Pending$$serializer state$Pending$$serializer = new State$Pending$$serializer();
        INSTANCE = state$Pending$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor(AppConstants.SECONDARY_USER_STATE_PENDING, state$Pending$$serializer, 1);
        pluginGeneratedSerialDescriptor.addElement("timestamp", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private State$Pending$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    @NotNull
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{new Rfc3339Deserializer()};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    public State.Pending deserialize(@NotNull Decoder decoder) {
        long j2;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeDecoder beginStructure = decoder.beginStructure(descriptor2);
        int i2 = 1;
        long j3 = 0;
        if (beginStructure.decodeSequentially()) {
            j2 = ((Number) beginStructure.decodeSerializableElement(descriptor2, 0, new Rfc3339Deserializer(), 0L)).longValue();
        } else {
            int i3 = 0;
            while (i2 != 0) {
                int decodeElementIndex = beginStructure.decodeElementIndex(descriptor2);
                if (decodeElementIndex == -1) {
                    i2 = 0;
                } else if (decodeElementIndex != 0) {
                    throw new UnknownFieldException(decodeElementIndex);
                } else {
                    j3 = ((Number) beginStructure.decodeSerializableElement(descriptor2, 0, new Rfc3339Deserializer(), Long.valueOf(j3))).longValue();
                    i3 |= 1;
                }
            }
            i2 = i3;
            j2 = j3;
        }
        beginStructure.endStructure(descriptor2);
        return new State.Pending(i2, j2, null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(@NotNull Encoder encoder, @NotNull State.Pending value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder beginStructure = encoder.beginStructure(descriptor2);
        State.Pending.write$Self(value, beginStructure, descriptor2);
        beginStructure.endStructure(descriptor2);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    @NotNull
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
