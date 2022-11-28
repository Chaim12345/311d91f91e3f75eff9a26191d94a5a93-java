package kotlinx.serialization.internal;

import java.util.Iterator;
import java.util.Map;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt___RangesKt;
import kotlinx.serialization.InternalSerializationApi;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Encoder;
import org.jetbrains.annotations.NotNull;
@InternalSerializationApi
/* loaded from: classes3.dex */
public abstract class MapLikeSerializer<Key, Value, Collection, Builder extends Map<Key, Value>> extends AbstractCollectionSerializer<Map.Entry<? extends Key, ? extends Value>, Collection, Builder> {
    @NotNull
    private final KSerializer<Key> keySerializer;
    @NotNull
    private final KSerializer<Value> valueSerializer;

    private MapLikeSerializer(KSerializer<Key> kSerializer, KSerializer<Value> kSerializer2) {
        super(null);
        this.keySerializer = kSerializer;
        this.valueSerializer = kSerializer2;
    }

    public /* synthetic */ MapLikeSerializer(KSerializer kSerializer, KSerializer kSerializer2, DefaultConstructorMarker defaultConstructorMarker) {
        this(kSerializer, kSerializer2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    /* renamed from: a */
    public final void readAll(@NotNull CompositeDecoder decoder, @NotNull Map builder, int i2, int i3) {
        IntRange until;
        IntProgression step;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        Intrinsics.checkNotNullParameter(builder, "builder");
        if (!(i3 >= 0)) {
            throw new IllegalArgumentException("Size must be known in advance when using READ_ALL".toString());
        }
        until = RangesKt___RangesKt.until(0, i3 * 2);
        step = RangesKt___RangesKt.step(until, 2);
        int first = step.getFirst();
        int last = step.getLast();
        int step2 = step.getStep();
        if ((step2 <= 0 || first > last) && (step2 >= 0 || last > first)) {
            return;
        }
        while (true) {
            int i4 = first + step2;
            readElement(decoder, i2 + first, builder, false);
            if (first == last) {
                return;
            }
            first = i4;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    /* renamed from: b */
    public final void readElement(@NotNull CompositeDecoder decoder, int i2, @NotNull Map builder, boolean z) {
        int i3;
        Object decodeSerializableElement$default;
        Object value;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        Intrinsics.checkNotNullParameter(builder, "builder");
        Object decodeSerializableElement$default2 = CompositeDecoder.DefaultImpls.decodeSerializableElement$default(decoder, getDescriptor(), i2, this.keySerializer, null, 8, null);
        if (z) {
            i3 = decoder.decodeElementIndex(getDescriptor());
            if (!(i3 == i2 + 1)) {
                throw new IllegalArgumentException(("Value must follow key in a map, index for key: " + i2 + ", returned index for value: " + i3).toString());
            }
        } else {
            i3 = i2 + 1;
        }
        int i4 = i3;
        if (!builder.containsKey(decodeSerializableElement$default2) || (this.valueSerializer.getDescriptor().getKind() instanceof PrimitiveKind)) {
            decodeSerializableElement$default = CompositeDecoder.DefaultImpls.decodeSerializableElement$default(decoder, getDescriptor(), i4, this.valueSerializer, null, 8, null);
        } else {
            SerialDescriptor descriptor = getDescriptor();
            KSerializer<Value> kSerializer = this.valueSerializer;
            value = MapsKt__MapsKt.getValue(builder, decodeSerializableElement$default2);
            decodeSerializableElement$default = decoder.decodeSerializableElement(descriptor, i4, kSerializer, value);
        }
        builder.put(decodeSerializableElement$default2, decodeSerializableElement$default);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public abstract SerialDescriptor getDescriptor();

    @NotNull
    public final KSerializer<Key> getKeySerializer() {
        return this.keySerializer;
    }

    @NotNull
    public final KSerializer<Value> getValueSerializer() {
        return this.valueSerializer;
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer, kotlinx.serialization.SerializationStrategy
    public void serialize(@NotNull Encoder encoder, Collection collection) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        int collectionSize = collectionSize(collection);
        SerialDescriptor descriptor = getDescriptor();
        CompositeEncoder beginCollection = encoder.beginCollection(descriptor, collectionSize);
        Iterator collectionIterator = collectionIterator(collection);
        int i2 = 0;
        while (collectionIterator.hasNext()) {
            Map.Entry entry = (Map.Entry) collectionIterator.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            int i3 = i2 + 1;
            beginCollection.encodeSerializableElement(getDescriptor(), i2, getKeySerializer(), key);
            beginCollection.encodeSerializableElement(getDescriptor(), i3, getValueSerializer(), value);
            i2 = i3 + 1;
        }
        beginCollection.endStructure(descriptor);
    }
}
