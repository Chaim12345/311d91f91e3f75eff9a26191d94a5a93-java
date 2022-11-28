package com.google.crypto.tink.subtle;

import java.util.Arrays;
import org.bouncycastle.tls.CipherSuite;
/* loaded from: classes2.dex */
class AesUtil {
    public static final int BLOCK_SIZE = 16;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] a(byte[] bArr) {
        if (bArr.length < 16) {
            byte[] copyOf = Arrays.copyOf(bArr, 16);
            copyOf[bArr.length] = Byte.MIN_VALUE;
            return copyOf;
        }
        throw new IllegalArgumentException("x must be smaller than a block.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] b(byte[] bArr) {
        if (bArr.length == 16) {
            byte[] bArr2 = new byte[16];
            for (int i2 = 0; i2 < 16; i2++) {
                bArr2[i2] = (byte) ((bArr[i2] << 1) & 254);
                if (i2 < 15) {
                    bArr2[i2] = (byte) (bArr2[i2] | ((byte) ((bArr[i2 + 1] >> 7) & 1)));
                }
            }
            bArr2[15] = (byte) (((byte) ((bArr[0] >> 7) & CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA)) ^ bArr2[15]);
            return bArr2;
        }
        throw new IllegalArgumentException("value must be a block.");
    }
}
