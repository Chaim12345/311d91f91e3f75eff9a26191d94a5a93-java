package kotlinx.serialization.internal;

import java.util.Arrays;
import java.util.Iterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorKt;
import kotlinx.serialization.descriptors.SerialKind;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class PluginGeneratedSerialDescriptorKt {
    public static final /* synthetic */ <SD extends SerialDescriptor> boolean equalsImpl(SD sd, Object obj, Function1<? super SD, Boolean> typeParamsAreEqual) {
        Intrinsics.checkNotNullParameter(sd, "<this>");
        Intrinsics.checkNotNullParameter(typeParamsAreEqual, "typeParamsAreEqual");
        if (sd == obj) {
            return true;
        }
        Intrinsics.reifiedOperationMarker(3, "SD");
        if (obj instanceof SerialDescriptor) {
            SerialDescriptor serialDescriptor = (SerialDescriptor) obj;
            if (Intrinsics.areEqual(sd.getSerialName(), serialDescriptor.getSerialName()) && typeParamsAreEqual.invoke(obj).booleanValue() && sd.getElementsCount() == serialDescriptor.getElementsCount()) {
                int elementsCount = sd.getElementsCount();
                int i2 = 0;
                while (i2 < elementsCount) {
                    int i3 = i2 + 1;
                    if (!Intrinsics.areEqual(sd.getElementDescriptor(i2).getSerialName(), serialDescriptor.getElementDescriptor(i2).getSerialName()) || !Intrinsics.areEqual(sd.getElementDescriptor(i2).getKind(), serialDescriptor.getElementDescriptor(i2).getKind())) {
                        return false;
                    }
                    i2 = i3;
                }
                return true;
            }
            return false;
        }
        return false;
    }

    public static final int hashCodeImpl(@NotNull SerialDescriptor serialDescriptor, @NotNull SerialDescriptor[] typeParams) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        Intrinsics.checkNotNullParameter(typeParams, "typeParams");
        int hashCode = (serialDescriptor.getSerialName().hashCode() * 31) + Arrays.hashCode(typeParams);
        Iterable<SerialDescriptor> elementDescriptors = SerialDescriptorKt.getElementDescriptors(serialDescriptor);
        Iterator<SerialDescriptor> it = elementDescriptors.iterator();
        int i2 = 1;
        int i3 = 1;
        while (true) {
            int i4 = 0;
            if (!it.hasNext()) {
                break;
            }
            int i5 = i3 * 31;
            String serialName = it.next().getSerialName();
            if (serialName != null) {
                i4 = serialName.hashCode();
            }
            i3 = i5 + i4;
        }
        for (SerialDescriptor serialDescriptor2 : elementDescriptors) {
            int i6 = i2 * 31;
            SerialKind kind = serialDescriptor2.getKind();
            i2 = i6 + (kind == null ? 0 : kind.hashCode());
        }
        return (((hashCode * 31) + i3) * 31) + i2;
    }
}
