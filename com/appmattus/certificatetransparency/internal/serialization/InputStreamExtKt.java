package com.appmattus.certificatetransparency.internal.serialization;

import java.io.IOException;
import java.io.InputStream;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public final class InputStreamExtKt {
    private static final int BITS_IN_BYTE = 8;
    private static final int MAX_NUMBER_BYTE_LENGTH = 8;

    @NotNull
    public static final byte[] readFixedLength(@NotNull InputStream inputStream, int i2) {
        Intrinsics.checkNotNullParameter(inputStream, "<this>");
        byte[] bArr = new byte[i2];
        int read = inputStream.read(bArr);
        if (read >= i2) {
            return bArr;
        }
        throw new IOException("Not enough bytes: Expected " + i2 + ", got " + read + '.');
    }

    public static final long readNumber(@NotNull InputStream inputStream, int i2) {
        Intrinsics.checkNotNullParameter(inputStream, "<this>");
        if (i2 <= 8) {
            long j2 = 0;
            for (int i3 = 0; i3 < i2; i3++) {
                int read = inputStream.read();
                if (read < 0) {
                    throw new IOException("Missing length bytes: Expected " + i2 + ", got " + i3 + '.');
                }
                j2 = (j2 << 8) | read;
            }
            return j2;
        }
        throw new IllegalArgumentException("Could not read a number of more than 8 bytes.".toString());
    }

    @NotNull
    public static final byte[] readVariableLength(@NotNull InputStream inputStream, int i2) {
        Intrinsics.checkNotNullParameter(inputStream, "<this>");
        int readNumber = (int) readNumber(inputStream, Deserializer.INSTANCE.bytesForDataLength(i2));
        byte[] bArr = new byte[readNumber];
        try {
            int read = inputStream.read(bArr);
            if (read == readNumber) {
                return bArr;
            }
            throw new IOException("Incomplete data. Expected " + readNumber + " bytes, had " + read + '.');
        } catch (IOException e2) {
            throw new IOException("Error while reading variable-length data", e2);
        }
    }
}
