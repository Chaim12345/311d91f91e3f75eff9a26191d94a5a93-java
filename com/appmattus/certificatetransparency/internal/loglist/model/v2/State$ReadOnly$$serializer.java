package com.appmattus.certificatetransparency.internal.loglist.model.v2;

import com.appmattus.certificatetransparency.internal.loglist.deserializer.Rfc3339Deserializer;
import com.appmattus.certificatetransparency.internal.loglist.model.v2.State;
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
public final class State$ReadOnly$$serializer implements GeneratedSerializer<State.ReadOnly> {
    @NotNull
    public static final State$ReadOnly$$serializer INSTANCE;
    public static final /* synthetic */ SerialDescriptor descriptor;

    static {
        State$ReadOnly$$serializer state$ReadOnly$$serializer = new State$ReadOnly$$serializer();
        INSTANCE = state$ReadOnly$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("readonly", state$ReadOnly$$serializer, 2);
        pluginGeneratedSerialDescriptor.addElement("timestamp", false);
        pluginGeneratedSerialDescriptor.addElement("final_tree_head", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private State$ReadOnly$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    @NotNull
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{new Rfc3339Deserializer(), FinalTreeHead$$serializer.INSTANCE};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    public State.ReadOnly deserialize(@NotNull Decoder decoder) {
        Object obj;
        int i2;
        long j2;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeDecoder beginStructure = decoder.beginStructure(descriptor2);
        if (beginStructure.decodeSequentially()) {
            long longValue = ((Number) beginStructure.decodeSerializableElement(descriptor2, 0, new Rfc3339Deserializer(), 0L)).longValue();
            obj = beginStructure.decodeSerializableElement(descriptor2, 1, FinalTreeHead$$serializer.INSTANCE, null);
            j2 = longValue;
            i2 = 3;
        } else {
            boolean z = true;
            Object obj2 = null;
            long j3 = 0;
            int i3 = 0;
            while (z) {
                int decodeElementIndex = beginStructure.decodeElementIndex(descriptor2);
                if (decodeElementIndex == -1) {
                    z = false;
                } else if (decodeElementIndex == 0) {
                    j3 = ((Number) beginStructure.decodeSerializableElement(descriptor2, 0, new Rfc3339Deserializer(), Long.valueOf(j3))).longValue();
                    i3 |= 1;
                } else if (decodeElementIndex != 1) {
                    throw new UnknownFieldException(decodeElementIndex);
                } else {
                    obj2 = beginStructure.decodeSerializableElement(descriptor2, 1, FinalTreeHead$$serializer.INSTANCE, obj2);
                    i3 |= 2;
                }
            }
            obj = obj2;
            i2 = i3;
            j2 = j3;
        }
        beginStructure.endStructure(descriptor2);
        return new State.ReadOnly(i2, j2, (FinalTreeHead) obj, null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(@NotNull Encoder encoder, @NotNull State.ReadOnly value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder beginStructure = encoder.beginStructure(descriptor2);
        State.ReadOnly.write$Self(value, beginStructure, descriptor2);
        beginStructure.endStructure(descriptor2);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    @NotNull
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
