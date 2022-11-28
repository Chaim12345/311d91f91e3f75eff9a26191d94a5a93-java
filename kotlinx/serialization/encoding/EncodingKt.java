package kotlinx.serialization.encoding;

import java.util.Collection;
import java.util.Iterator;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.descriptors.SerialDescriptor;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class EncodingKt {
    public static final void encodeCollection(@NotNull Encoder encoder, @NotNull SerialDescriptor descriptor, int i2, @NotNull Function1<? super CompositeEncoder, Unit> block) {
        Intrinsics.checkNotNullParameter(encoder, "<this>");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(block, "block");
        CompositeEncoder beginCollection = encoder.beginCollection(descriptor, i2);
        block.invoke(beginCollection);
        beginCollection.endStructure(descriptor);
    }

    public static final <E> void encodeCollection(@NotNull Encoder encoder, @NotNull SerialDescriptor descriptor, @NotNull Collection<? extends E> collection, @NotNull Function3<? super CompositeEncoder, ? super Integer, ? super E, Unit> block) {
        Intrinsics.checkNotNullParameter(encoder, "<this>");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(collection, "collection");
        Intrinsics.checkNotNullParameter(block, "block");
        CompositeEncoder beginCollection = encoder.beginCollection(descriptor, collection.size());
        Iterator<T> it = collection.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            Object obj = (Object) it.next();
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            block.invoke(beginCollection, Integer.valueOf(i2), obj);
            i2 = i3;
        }
        beginCollection.endStructure(descriptor);
    }

    public static final void encodeStructure(@NotNull Encoder encoder, @NotNull SerialDescriptor descriptor, @NotNull Function1<? super CompositeEncoder, Unit> block) {
        Intrinsics.checkNotNullParameter(encoder, "<this>");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(block, "block");
        CompositeEncoder beginStructure = encoder.beginStructure(descriptor);
        try {
            block.invoke(beginStructure);
            InlineMarker.finallyStart(1);
            beginStructure.endStructure(descriptor);
            InlineMarker.finallyEnd(1);
        } finally {
        }
    }
}
