package com.appmattus.certificatetransparency.internal.serialization;

import com.appmattus.certificatetransparency.internal.exceptions.SerializationException;
import com.appmattus.certificatetransparency.internal.verifier.model.DigitallySigned;
import com.appmattus.certificatetransparency.internal.verifier.model.LogId;
import com.appmattus.certificatetransparency.internal.verifier.model.SignedCertificateTimestamp;
import com.appmattus.certificatetransparency.internal.verifier.model.Version;
import java.io.InputStream;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt__MathJVMKt;
import kotlin.text.CharsKt__CharJVMKt;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public final class Deserializer {
    private static final int BITS_IN_BYTE = 8;
    private static final int HEX_RADIX = 16;
    @NotNull
    public static final Deserializer INSTANCE = new Deserializer();

    private Deserializer() {
    }

    private final DigitallySigned parseDigitallySignedFromBinary(InputStream inputStream) {
        int checkRadix;
        int checkRadix2;
        int readNumber = (int) InputStreamExtKt.readNumber(inputStream, 1);
        DigitallySigned.HashAlgorithm forNumber = DigitallySigned.HashAlgorithm.Companion.forNumber(readNumber);
        if (forNumber == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unknown hash algorithm: ");
            checkRadix = CharsKt__CharJVMKt.checkRadix(16);
            String num = Integer.toString(readNumber, checkRadix);
            Intrinsics.checkNotNullExpressionValue(num, "toString(this, checkRadix(radix))");
            sb.append(num);
            throw new SerializationException(sb.toString());
        }
        int readNumber2 = (int) InputStreamExtKt.readNumber(inputStream, 1);
        DigitallySigned.SignatureAlgorithm forNumber2 = DigitallySigned.SignatureAlgorithm.Companion.forNumber(readNumber2);
        if (forNumber2 != null) {
            return new DigitallySigned(forNumber, forNumber2, InputStreamExtKt.readVariableLength(inputStream, 65535));
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Unknown signature algorithm: ");
        checkRadix2 = CharsKt__CharJVMKt.checkRadix(16);
        String num2 = Integer.toString(readNumber2, checkRadix2);
        Intrinsics.checkNotNullExpressionValue(num2, "toString(this, checkRadix(radix))");
        sb2.append(num2);
        throw new SerializationException(sb2.toString());
    }

    public final int bytesForDataLength(int i2) {
        double log2;
        log2 = MathKt__MathJVMKt.log2(i2);
        return (int) (Math.ceil(log2) / 8);
    }

    @NotNull
    public final SignedCertificateTimestamp parseSctFromBinary(@NotNull InputStream inputStream) {
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        Version forNumber = Version.Companion.forNumber((int) InputStreamExtKt.readNumber(inputStream, 1));
        if (forNumber != Version.V1) {
            throw new SerializationException("Unknown version: " + forNumber);
        }
        byte[] readFixedLength = InputStreamExtKt.readFixedLength(inputStream, 32);
        long readNumber = InputStreamExtKt.readNumber(inputStream, 8);
        byte[] readVariableLength = InputStreamExtKt.readVariableLength(inputStream, 65535);
        return new SignedCertificateTimestamp(forNumber, new LogId(readFixedLength), readNumber, parseDigitallySignedFromBinary(inputStream), readVariableLength);
    }
}
