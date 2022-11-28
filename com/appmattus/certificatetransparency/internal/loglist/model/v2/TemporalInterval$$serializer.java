package com.appmattus.certificatetransparency.internal.loglist.model.v2;

import com.appmattus.certificatetransparency.internal.loglist.deserializer.Rfc3339Deserializer;
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
public final class TemporalInterval$$serializer implements GeneratedSerializer<TemporalInterval> {
    @NotNull
    public static final TemporalInterval$$serializer INSTANCE;
    public static final /* synthetic */ SerialDescriptor descriptor;

    static {
        TemporalInterval$$serializer temporalInterval$$serializer = new TemporalInterval$$serializer();
        INSTANCE = temporalInterval$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.appmattus.certificatetransparency.internal.loglist.model.v2.TemporalInterval", temporalInterval$$serializer, 2);
        pluginGeneratedSerialDescriptor.addElement("start_inclusive", false);
        pluginGeneratedSerialDescriptor.addElement("end_exclusive", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private TemporalInterval$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    @NotNull
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{new Rfc3339Deserializer(), new Rfc3339Deserializer()};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    public TemporalInterval deserialize(@NotNull Decoder decoder) {
        int i2;
        long j2;
        long j3;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeDecoder beginStructure = decoder.beginStructure(descriptor2);
        long j4 = 0;
        if (beginStructure.decodeSequentially()) {
            j2 = ((Number) beginStructure.decodeSerializableElement(descriptor2, 0, new Rfc3339Deserializer(), 0L)).longValue();
            j3 = ((Number) beginStructure.decodeSerializableElement(descriptor2, 1, new Rfc3339Deserializer(), 0L)).longValue();
            i2 = 3;
        } else {
            long j5 = 0;
            int i3 = 0;
            boolean z = true;
            while (z) {
                int decodeElementIndex = beginStructure.decodeElementIndex(descriptor2);
                if (decodeElementIndex == -1) {
                    z = false;
                } else if (decodeElementIndex == 0) {
                    j4 = ((Number) beginStructure.decodeSerializableElement(descriptor2, 0, new Rfc3339Deserializer(), Long.valueOf(j4))).longValue();
                    i3 |= 1;
                } else if (decodeElementIndex != 1) {
                    throw new UnknownFieldException(decodeElementIndex);
                } else {
                    j5 = ((Number) beginStructure.decodeSerializableElement(descriptor2, 1, new Rfc3339Deserializer(), Long.valueOf(j5))).longValue();
                    i3 |= 2;
                }
            }
            i2 = i3;
            j2 = j4;
            j3 = j5;
        }
        beginStructure.endStructure(descriptor2);
        return new TemporalInterval(i2, j2, j3, null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(@NotNull Encoder encoder, @NotNull TemporalInterval value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder beginStructure = encoder.beginStructure(descriptor2);
        TemporalInterval.write$Self(value, beginStructure, descriptor2);
        beginStructure.endStructure(descriptor2);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    @NotNull
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
