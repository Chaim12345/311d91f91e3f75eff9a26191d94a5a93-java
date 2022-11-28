package com.appmattus.certificatetransparency.internal.serialization;

import java.io.OutputStream;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public final class OutputStreamExtKt {
    private static final int BITS_IN_BYTE = 8;

    public static final void writeUint(@NotNull OutputStream outputStream, long j2, int i2) {
        Intrinsics.checkNotNullParameter(outputStream, "<this>");
        if (!(j2 >= 0)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (((double) j2) < Math.pow(256.0d, (double) i2)) {
            while (i2 > 0) {
                int i3 = (i2 - 1) * 8;
                outputStream.write((byte) (((255 << i3) & j2) >> i3));
                i2--;
            }
            return;
        }
        throw new IllegalArgumentException(("Value " + j2 + " cannot be stored in " + i2 + " bytes").toString());
    }

    public static final void writeVariableLength(@NotNull OutputStream outputStream, @NotNull byte[] data, int i2) {
        Intrinsics.checkNotNullParameter(outputStream, "<this>");
        Intrinsics.checkNotNullParameter(data, "data");
        if (!(data.length <= i2)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        writeUint(outputStream, data.length, Deserializer.INSTANCE.bytesForDataLength(i2));
        outputStream.write(data, 0, data.length);
    }
}
