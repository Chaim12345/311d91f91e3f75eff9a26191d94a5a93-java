package kotlinx.serialization.internal;

import java.util.Iterator;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.InternalSerializationApi;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@InternalSerializationApi
/* loaded from: classes3.dex */
public abstract class AbstractCollectionSerializer<Element, Collection, Builder> implements KSerializer<Collection> {
    private AbstractCollectionSerializer() {
    }

    public /* synthetic */ AbstractCollectionSerializer(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public static /* synthetic */ void readElement$default(AbstractCollectionSerializer abstractCollectionSerializer, CompositeDecoder compositeDecoder, int i2, Object obj, boolean z, int i3, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: readElement");
        }
        if ((i3 & 8) != 0) {
            z = true;
        }
        abstractCollectionSerializer.readElement(compositeDecoder, i2, obj, z);
    }

    private final int readSize(CompositeDecoder compositeDecoder, Builder builder) {
        int decodeCollectionSize = compositeDecoder.decodeCollectionSize(getDescriptor());
        checkCapacity(builder, decodeCollectionSize);
        return decodeCollectionSize;
    }

    protected abstract Object builder();

    protected abstract int builderSize(Object obj);

    protected abstract void checkCapacity(Object obj, int i2);

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public abstract Iterator collectionIterator(Object obj);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract int collectionSize(Object obj);

    @Override // kotlinx.serialization.DeserializationStrategy
    public Collection deserialize(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        return merge(decoder, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @InternalSerializationApi
    public final Collection merge(@NotNull Decoder decoder, @Nullable Collection collection) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        Object builder = collection == null ? null : toBuilder(collection);
        if (builder == null) {
            builder = builder();
        }
        int builderSize = builderSize(builder);
        CompositeDecoder beginStructure = decoder.beginStructure(getDescriptor());
        if (beginStructure.decodeSequentially()) {
            readAll(beginStructure, builder, builderSize, readSize(beginStructure, builder));
        } else {
            while (true) {
                int decodeElementIndex = beginStructure.decodeElementIndex(getDescriptor());
                if (decodeElementIndex == -1) {
                    break;
                }
                readElement$default(this, beginStructure, builderSize + decodeElementIndex, builder, false, 8, null);
            }
        }
        beginStructure.endStructure(getDescriptor());
        return (Collection) toResult(builder);
    }

    protected abstract void readAll(@NotNull CompositeDecoder compositeDecoder, Object obj, int i2, int i3);

    protected abstract void readElement(@NotNull CompositeDecoder compositeDecoder, int i2, Object obj, boolean z);

    @Override // kotlinx.serialization.SerializationStrategy
    public abstract void serialize(@NotNull Encoder encoder, Collection collection);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Object toBuilder(Object obj);

    protected abstract Object toResult(Object obj);
}
