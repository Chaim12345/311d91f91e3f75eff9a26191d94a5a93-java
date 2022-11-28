package kotlinx.serialization.internal;

import kotlin.PublishedApi;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import org.jetbrains.annotations.NotNull;
@PublishedApi
/* loaded from: classes3.dex */
public abstract class KeyValueSerializer<K, V, R> implements KSerializer<R> {
    @NotNull
    private final KSerializer<K> keySerializer;
    @NotNull
    private final KSerializer<V> valueSerializer;

    private KeyValueSerializer(KSerializer<K> kSerializer, KSerializer<V> kSerializer2) {
        this.keySerializer = kSerializer;
        this.valueSerializer = kSerializer2;
    }

    public /* synthetic */ KeyValueSerializer(KSerializer kSerializer, KSerializer kSerializer2, DefaultConstructorMarker defaultConstructorMarker) {
        this(kSerializer, kSerializer2);
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public R deserialize(@NotNull Decoder decoder) {
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        CompositeDecoder beginStructure = decoder.beginStructure(getDescriptor());
        if (beginStructure.decodeSequentially()) {
            return (R) toResult(CompositeDecoder.DefaultImpls.decodeSerializableElement$default(beginStructure, getDescriptor(), 0, this.keySerializer, null, 8, null), CompositeDecoder.DefaultImpls.decodeSerializableElement$default(beginStructure, getDescriptor(), 1, this.valueSerializer, null, 8, null));
        }
        obj = TuplesKt.NULL;
        obj2 = TuplesKt.NULL;
        Object obj5 = obj2;
        while (true) {
            int decodeElementIndex = beginStructure.decodeElementIndex(getDescriptor());
            if (decodeElementIndex == -1) {
                beginStructure.endStructure(getDescriptor());
                obj3 = TuplesKt.NULL;
                if (obj != obj3) {
                    obj4 = TuplesKt.NULL;
                    if (obj5 != obj4) {
                        return (R) toResult(obj, obj5);
                    }
                    throw new SerializationException("Element 'value' is missing");
                }
                throw new SerializationException("Element 'key' is missing");
            } else if (decodeElementIndex == 0) {
                obj = CompositeDecoder.DefaultImpls.decodeSerializableElement$default(beginStructure, getDescriptor(), 0, this.keySerializer, null, 8, null);
            } else if (decodeElementIndex != 1) {
                throw new SerializationException(Intrinsics.stringPlus("Invalid index: ", Integer.valueOf(decodeElementIndex)));
            } else {
                obj5 = CompositeDecoder.DefaultImpls.decodeSerializableElement$default(beginStructure, getDescriptor(), 1, this.valueSerializer, null, 8, null);
            }
        }
    }

    protected abstract Object getKey(Object obj);

    protected abstract Object getValue(Object obj);

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(@NotNull Encoder encoder, R r2) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        CompositeEncoder beginStructure = encoder.beginStructure(getDescriptor());
        beginStructure.encodeSerializableElement(getDescriptor(), 0, this.keySerializer, getKey(r2));
        beginStructure.encodeSerializableElement(getDescriptor(), 1, this.valueSerializer, getValue(r2));
        beginStructure.endStructure(getDescriptor());
    }

    protected abstract Object toResult(Object obj, Object obj2);
}
