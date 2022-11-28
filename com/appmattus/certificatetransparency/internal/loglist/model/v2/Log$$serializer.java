package com.appmattus.certificatetransparency.internal.loglist.model.v2;

import com.appmattus.certificatetransparency.internal.loglist.deserializer.HttpUrlDeserializer;
import com.appmattus.certificatetransparency.internal.loglist.deserializer.StateDeserializer;
import com.google.android.gms.common.internal.ImagesContract;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import okhttp3.HttpUrl;
import org.jetbrains.annotations.NotNull;
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* loaded from: classes.dex */
public final class Log$$serializer implements GeneratedSerializer<Log> {
    @NotNull
    public static final Log$$serializer INSTANCE;
    public static final /* synthetic */ SerialDescriptor descriptor;

    static {
        Log$$serializer log$$serializer = new Log$$serializer();
        INSTANCE = log$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.appmattus.certificatetransparency.internal.loglist.model.v2.Log", log$$serializer, 9);
        pluginGeneratedSerialDescriptor.addElement("description", true);
        pluginGeneratedSerialDescriptor.addElement("key", false);
        pluginGeneratedSerialDescriptor.addElement("log_id", false);
        pluginGeneratedSerialDescriptor.addElement("mmd", false);
        pluginGeneratedSerialDescriptor.addElement(ImagesContract.URL, false);
        pluginGeneratedSerialDescriptor.addElement("dns", true);
        pluginGeneratedSerialDescriptor.addElement("temporal_interval", true);
        pluginGeneratedSerialDescriptor.addElement("log_type", true);
        pluginGeneratedSerialDescriptor.addElement("state", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private Log$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    @NotNull
    public KSerializer<?>[] childSerializers() {
        StringSerializer stringSerializer = StringSerializer.INSTANCE;
        return new KSerializer[]{BuiltinSerializersKt.getNullable(stringSerializer), stringSerializer, stringSerializer, IntSerializer.INSTANCE, new HttpUrlDeserializer(), BuiltinSerializersKt.getNullable(Hostname.Companion.serializer()), BuiltinSerializersKt.getNullable(TemporalInterval$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(LogType$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(new StateDeserializer())};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    public Log deserialize(@NotNull Decoder decoder) {
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        int i2;
        String str;
        String str2;
        int i3;
        Object obj6;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeDecoder beginStructure = decoder.beginStructure(descriptor2);
        int i4 = 7;
        int i5 = 6;
        if (beginStructure.decodeSequentially()) {
            obj6 = beginStructure.decodeNullableSerializableElement(descriptor2, 0, StringSerializer.INSTANCE, null);
            String decodeStringElement = beginStructure.decodeStringElement(descriptor2, 1);
            String decodeStringElement2 = beginStructure.decodeStringElement(descriptor2, 2);
            int decodeIntElement = beginStructure.decodeIntElement(descriptor2, 3);
            Object decodeSerializableElement = beginStructure.decodeSerializableElement(descriptor2, 4, new HttpUrlDeserializer(), null);
            obj4 = beginStructure.decodeNullableSerializableElement(descriptor2, 5, Hostname.Companion.serializer(), null);
            obj3 = beginStructure.decodeNullableSerializableElement(descriptor2, 6, TemporalInterval$$serializer.INSTANCE, null);
            obj2 = beginStructure.decodeNullableSerializableElement(descriptor2, 7, LogType$$serializer.INSTANCE, null);
            i3 = decodeIntElement;
            obj5 = decodeSerializableElement;
            obj = beginStructure.decodeNullableSerializableElement(descriptor2, 8, new StateDeserializer(), null);
            i2 = 511;
            str2 = decodeStringElement2;
            str = decodeStringElement;
        } else {
            boolean z = true;
            int i6 = 0;
            Object obj7 = null;
            obj = null;
            Object obj8 = null;
            Object obj9 = null;
            Object obj10 = null;
            String str3 = null;
            String str4 = null;
            Object obj11 = null;
            int i7 = 0;
            while (z) {
                int decodeElementIndex = beginStructure.decodeElementIndex(descriptor2);
                switch (decodeElementIndex) {
                    case -1:
                        z = false;
                        i5 = 6;
                        break;
                    case 0:
                        obj10 = beginStructure.decodeNullableSerializableElement(descriptor2, 0, StringSerializer.INSTANCE, obj10);
                        i7 |= 1;
                        i4 = 7;
                        i5 = 6;
                        break;
                    case 1:
                        i7 |= 2;
                        str3 = beginStructure.decodeStringElement(descriptor2, 1);
                        i4 = 7;
                        break;
                    case 2:
                        i7 |= 4;
                        str4 = beginStructure.decodeStringElement(descriptor2, 2);
                        i4 = 7;
                        break;
                    case 3:
                        i6 = beginStructure.decodeIntElement(descriptor2, 3);
                        i7 |= 8;
                        i4 = 7;
                        break;
                    case 4:
                        obj11 = beginStructure.decodeSerializableElement(descriptor2, 4, new HttpUrlDeserializer(), obj11);
                        i7 |= 16;
                        i4 = 7;
                        break;
                    case 5:
                        obj9 = beginStructure.decodeNullableSerializableElement(descriptor2, 5, Hostname.Companion.serializer(), obj9);
                        i7 |= 32;
                        i4 = 7;
                        continue;
                    case 6:
                        obj8 = beginStructure.decodeNullableSerializableElement(descriptor2, i5, TemporalInterval$$serializer.INSTANCE, obj8);
                        i7 |= 64;
                        break;
                    case 7:
                        obj7 = beginStructure.decodeNullableSerializableElement(descriptor2, i4, LogType$$serializer.INSTANCE, obj7);
                        i7 |= 128;
                        break;
                    case 8:
                        obj = beginStructure.decodeNullableSerializableElement(descriptor2, 8, new StateDeserializer(), obj);
                        i7 |= 256;
                        break;
                    default:
                        throw new UnknownFieldException(decodeElementIndex);
                }
            }
            obj2 = obj7;
            obj3 = obj8;
            obj4 = obj9;
            obj5 = obj11;
            i2 = i7;
            str = str3;
            str2 = str4;
            i3 = i6;
            obj6 = obj10;
        }
        beginStructure.endStructure(descriptor2);
        return new Log(i2, (String) obj6, str, str2, i3, (HttpUrl) obj5, (Hostname) obj4, (TemporalInterval) obj3, (LogType) obj2, (State) obj, (SerializationConstructorMarker) null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(@NotNull Encoder encoder, @NotNull Log value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder beginStructure = encoder.beginStructure(descriptor2);
        Log.write$Self(value, beginStructure, descriptor2);
        beginStructure.endStructure(descriptor2);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    @NotNull
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
