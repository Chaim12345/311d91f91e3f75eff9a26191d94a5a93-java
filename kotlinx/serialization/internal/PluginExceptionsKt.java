package kotlinx.serialization.internal;

import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.InternalSerializationApi;
import kotlinx.serialization.MissingFieldException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class PluginExceptionsKt {
    @InternalSerializationApi
    public static final void throwArrayMissingFieldException(@NotNull int[] seenArray, @NotNull int[] goldenMaskArray, @NotNull SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(seenArray, "seenArray");
        Intrinsics.checkNotNullParameter(goldenMaskArray, "goldenMaskArray");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        ArrayList arrayList = new ArrayList();
        int length = goldenMaskArray.length;
        int i2 = 0;
        while (i2 < length) {
            int i3 = i2 + 1;
            int i4 = goldenMaskArray[i2] & (~seenArray[i2]);
            if (i4 != 0) {
                int i5 = 0;
                while (i5 < 32) {
                    int i6 = i5 + 1;
                    if ((i4 & 1) != 0) {
                        arrayList.add(descriptor.getElementName((i2 * 32) + i5));
                    }
                    i4 >>>= 1;
                    i5 = i6;
                }
            }
            i2 = i3;
        }
        throw new MissingFieldException(arrayList, descriptor.getSerialName());
    }

    @InternalSerializationApi
    public static final void throwMissingFieldException(int i2, int i3, @NotNull SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        ArrayList arrayList = new ArrayList();
        int i4 = (~i2) & i3;
        int i5 = 0;
        while (i5 < 32) {
            int i6 = i5 + 1;
            if ((i4 & 1) != 0) {
                arrayList.add(descriptor.getElementName(i5));
            }
            i4 >>>= 1;
            i5 = i6;
        }
        throw new MissingFieldException(arrayList, descriptor.getSerialName());
    }
}
