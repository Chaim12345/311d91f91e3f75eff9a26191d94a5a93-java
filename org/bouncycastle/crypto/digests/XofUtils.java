package org.bouncycastle.crypto.digests;

import org.bouncycastle.util.Arrays;
/* loaded from: classes3.dex */
public class XofUtils {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] a(byte b2) {
        return Arrays.concatenate(leftEncode(8L), new byte[]{b2});
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] b(byte[] bArr, int i2, int i3) {
        return bArr.length == i3 ? Arrays.concatenate(leftEncode(i3 * 8), bArr) : Arrays.concatenate(leftEncode(i3 * 8), Arrays.copyOfRange(bArr, i2, i3 + i2));
    }

    public static byte[] leftEncode(long j2) {
        long j3 = j2;
        byte b2 = 1;
        while (true) {
            j3 >>= 8;
            if (j3 == 0) {
                break;
            }
            b2 = (byte) (b2 + 1);
        }
        byte[] bArr = new byte[b2 + 1];
        bArr[0] = b2;
        for (int i2 = 1; i2 <= b2; i2++) {
            bArr[i2] = (byte) (j2 >> ((b2 - i2) * 8));
        }
        return bArr;
    }

    public static byte[] rightEncode(long j2) {
        long j3 = j2;
        byte b2 = 1;
        while (true) {
            j3 >>= 8;
            if (j3 == 0) {
                break;
            }
            b2 = (byte) (b2 + 1);
        }
        byte[] bArr = new byte[b2 + 1];
        bArr[b2] = b2;
        for (int i2 = 0; i2 < b2; i2++) {
            bArr[i2] = (byte) (j2 >> (((b2 - i2) - 1) * 8));
        }
        return bArr;
    }
}
