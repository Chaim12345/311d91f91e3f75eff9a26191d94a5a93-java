package kotlinx.serialization.internal;

import java.util.Iterator;
import kotlin.PublishedApi;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Encoder;
import org.jetbrains.annotations.NotNull;
@PublishedApi
/* loaded from: classes3.dex */
public abstract class ListLikeSerializer<Element, Collection, Builder> extends AbstractCollectionSerializer<Element, Collection, Builder> {
    @NotNull
    private final KSerializer<Element> elementSerializer;

    private ListLikeSerializer(KSerializer<Element> kSerializer) {
        super(null);
        this.elementSerializer = kSerializer;
    }

    public /* synthetic */ ListLikeSerializer(KSerializer kSerializer, DefaultConstructorMarker defaultConstructorMarker) {
        this(kSerializer);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public abstract SerialDescriptor getDescriptor();

    protected abstract void insert(Object obj, int i2, Object obj2);

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    protected final void readAll(@NotNull CompositeDecoder decoder, Object obj, int i2, int i3) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        if (!(i3 >= 0)) {
            throw new IllegalArgumentException("Size must be known in advance when using READ_ALL".toString());
        }
        for (int i4 = 0; i4 < i3; i4++) {
            readElement(decoder, i4 + i2, obj, false);
        }
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    protected void readElement(@NotNull CompositeDecoder decoder, int i2, Object obj, boolean z) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        insert(obj, i2, CompositeDecoder.DefaultImpls.decodeSerializableElement$default(decoder, getDescriptor(), i2, this.elementSerializer, null, 8, null));
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer, kotlinx.serialization.SerializationStrategy
    public void serialize(@NotNull Encoder encoder, Collection collection) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        int collectionSize = collectionSize(collection);
        SerialDescriptor descriptor = getDescriptor();
        CompositeEncoder beginCollection = encoder.beginCollection(descriptor, collectionSize);
        Iterator collectionIterator = collectionIterator(collection);
        for (int i2 = 0; i2 < collectionSize; i2++) {
            beginCollection.encodeSerializableElement(getDescriptor(), i2, this.elementSerializer, collectionIterator.next());
        }
        beginCollection.endStructure(descriptor);
    }
}
