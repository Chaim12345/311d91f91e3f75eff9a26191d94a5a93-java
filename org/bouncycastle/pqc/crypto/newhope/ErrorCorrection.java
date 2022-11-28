package org.bouncycastle.pqc.crypto.newhope;

import org.bouncycastle.tls.CipherSuite;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
class ErrorCorrection {
    static short a(int i2, int i3, int i4, int i5) {
        return (short) (((((d(i2) + d(i3)) + d(i4)) + d(i5)) - 98312) >>> 31);
    }

    static int b(int i2) {
        int i3 = i2 >> 31;
        return (i2 ^ i3) - i3;
    }

    static int c(int[] iArr, int i2, int i3, int i4) {
        int i5 = (i4 * 2730) >> 25;
        int i6 = i5 - ((12288 - (i4 - (i5 * 12289))) >> 31);
        iArr[i2] = (i6 >> 1) + (i6 & 1);
        int i7 = i6 - 1;
        iArr[i3] = (i7 >> 1) + (i7 & 1);
        return b(i4 - ((iArr[i2] * 2) * 12289));
    }

    static int d(int i2) {
        int i3 = (i2 * 2730) >> 27;
        int i4 = i3 - ((CipherSuite.TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA - (i2 - (CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA * i3))) >> 31);
        return b((((i4 >> 1) + (i4 & 1)) * 98312) - i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void e(short[] sArr, short[] sArr2, byte[] bArr, byte b2) {
        short s2 = 8;
        byte[] bArr2 = new byte[8];
        bArr2[0] = b2;
        byte[] bArr3 = new byte[32];
        ChaCha20.a(bArr, bArr2, bArr3, 0, 32);
        int[] iArr = new int[8];
        int[] iArr2 = new int[4];
        int i2 = 0;
        while (i2 < 256) {
            int i3 = i2 + 0;
            int i4 = ((bArr3[i2 >>> 3] >>> (i2 & 7)) & 1) * 4;
            int i5 = i2 + 256;
            int i6 = i2 + 512;
            int i7 = i2 + 768;
            int c2 = (24577 - (((c(iArr, 0, 4, (sArr2[i3] * s2) + i4) + c(iArr, 1, 5, (sArr2[i5] * s2) + i4)) + c(iArr, 2, 6, (sArr2[i6] * s2) + i4)) + c(iArr, 3, 7, (sArr2[i7] * 8) + i4))) >> 31;
            int i8 = ~c2;
            iArr2[0] = (i8 & iArr[0]) ^ (c2 & iArr[4]);
            iArr2[1] = (i8 & iArr[1]) ^ (c2 & iArr[5]);
            iArr2[2] = (i8 & iArr[2]) ^ (c2 & iArr[6]);
            iArr2[3] = (i8 & iArr[3]) ^ (iArr[7] & c2);
            sArr[i3] = (short) ((iArr2[0] - iArr2[3]) & 3);
            sArr[i5] = (short) ((iArr2[1] - iArr2[3]) & 3);
            sArr[i6] = (short) ((iArr2[2] - iArr2[3]) & 3);
            sArr[i7] = (short) (3 & ((-c2) + (iArr2[3] * 2)));
            i2++;
            s2 = 8;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void f(byte[] bArr, short[] sArr, short[] sArr2) {
        Arrays.fill(bArr, (byte) 0);
        int[] iArr = new int[4];
        for (int i2 = 0; i2 < 256; i2++) {
            int i3 = i2 + 0;
            int i4 = i2 + 768;
            iArr[0] = ((sArr[i3] * 8) + 196624) - (((sArr2[i3] * 2) + sArr2[i4]) * 12289);
            int i5 = i2 + 256;
            iArr[1] = ((sArr[i5] * 8) + 196624) - (((sArr2[i5] * 2) + sArr2[i4]) * 12289);
            int i6 = i2 + 512;
            iArr[2] = ((sArr[i6] * 8) + 196624) - (((sArr2[i6] * 2) + sArr2[i4]) * 12289);
            iArr[3] = ((sArr[i4] * 8) + 196624) - (sArr2[i4] * 12289);
            int i7 = i2 >>> 3;
            bArr[i7] = (byte) ((a(iArr[0], iArr[1], iArr[2], iArr[3]) << (i2 & 7)) | bArr[i7]);
        }
    }
}
