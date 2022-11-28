package com.google.crypto.tink.subtle;

import com.google.common.base.Ascii;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import org.bouncycastle.asn1.cmc.BodyPartID;
/* loaded from: classes2.dex */
class Poly1305 {
    public static final int MAC_KEY_SIZE_IN_BYTES = 32;
    public static final int MAC_TAG_SIZE_IN_BYTES = 16;

    private Poly1305() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] a(byte[] bArr, byte[] bArr2) {
        if (bArr.length == 32) {
            int i2 = 0;
            long load26 = load26(bArr, 0, 0) & 67108863;
            int i3 = 3;
            long load262 = load26(bArr, 3, 2) & 67108611;
            long load263 = load26(bArr, 6, 4) & 67092735;
            long load264 = load26(bArr, 9, 6) & 66076671;
            long load265 = load26(bArr, 12, 8) & 1048575;
            long j2 = load262 * 5;
            long j3 = load263 * 5;
            long j4 = load264 * 5;
            long j5 = load265 * 5;
            byte[] bArr3 = new byte[17];
            long j6 = 0;
            int i4 = 0;
            long j7 = 0;
            long j8 = 0;
            long j9 = 0;
            long j10 = 0;
            while (i4 < bArr2.length) {
                copyBlockSize(bArr3, bArr2, i4);
                long load266 = j10 + load26(bArr3, i2, i2);
                long load267 = j6 + load26(bArr3, i3, 2);
                long load268 = j7 + load26(bArr3, 6, 4);
                long load269 = j8 + load26(bArr3, 9, 6);
                long load2610 = j9 + (load26(bArr3, 12, 8) | (bArr3[16] << Ascii.CAN));
                long j11 = (load266 * load26) + (load267 * j5) + (load268 * j4) + (load269 * j3) + (load2610 * j2);
                long j12 = (load266 * load262) + (load267 * load26) + (load268 * j5) + (load269 * j4) + (load2610 * j3) + (j11 >> 26);
                long j13 = (load266 * load263) + (load267 * load262) + (load268 * load26) + (load269 * j5) + (load2610 * j4) + (j12 >> 26);
                long j14 = (load266 * load264) + (load267 * load263) + (load268 * load262) + (load269 * load26) + (load2610 * j5) + (j13 >> 26);
                long j15 = (load266 * load265) + (load267 * load264) + (load268 * load263) + (load269 * load262) + (load2610 * load26) + (j14 >> 26);
                long j16 = (j11 & 67108863) + ((j15 >> 26) * 5);
                j6 = (j12 & 67108863) + (j16 >> 26);
                i4 += 16;
                j7 = j13 & 67108863;
                j8 = j14 & 67108863;
                j9 = j15 & 67108863;
                i3 = 3;
                j10 = j16 & 67108863;
                i2 = 0;
            }
            long j17 = j7 + (j6 >> 26);
            long j18 = j17 & 67108863;
            long j19 = j8 + (j17 >> 26);
            long j20 = j19 & 67108863;
            long j21 = j9 + (j19 >> 26);
            long j22 = j21 & 67108863;
            long j23 = j10 + ((j21 >> 26) * 5);
            long j24 = j23 & 67108863;
            long j25 = (j6 & 67108863) + (j23 >> 26);
            long j26 = j24 + 5;
            long j27 = j26 & 67108863;
            long j28 = (j26 >> 26) + j25;
            long j29 = j18 + (j28 >> 26);
            long j30 = j20 + (j29 >> 26);
            long j31 = (j22 + (j30 >> 26)) - 67108864;
            long j32 = j31 >> 63;
            long j33 = j24 & j32;
            long j34 = j25 & j32;
            long j35 = j18 & j32;
            long j36 = j20 & j32;
            long j37 = j22 & j32;
            long j38 = ~j32;
            long j39 = (j28 & 67108863 & j38) | j34;
            long j40 = (j29 & 67108863 & j38) | j35;
            long j41 = (j30 & 67108863 & j38) | j36;
            long j42 = (j33 | (j27 & j38) | (j39 << 26)) & BodyPartID.bodyIdMax;
            long j43 = ((j39 >> 6) | (j40 << 20)) & BodyPartID.bodyIdMax;
            long j44 = ((j40 >> 12) | (j41 << 14)) & BodyPartID.bodyIdMax;
            long j45 = ((j41 >> 18) | (((j31 & j38) | j37) << 8)) & BodyPartID.bodyIdMax;
            long load32 = j42 + load32(bArr, 16);
            long j46 = load32 & BodyPartID.bodyIdMax;
            long load322 = j43 + load32(bArr, 20) + (load32 >> 32);
            long j47 = load322 & BodyPartID.bodyIdMax;
            long load323 = j44 + load32(bArr, 24) + (load322 >> 32);
            long j48 = load323 & BodyPartID.bodyIdMax;
            long load324 = (j45 + load32(bArr, 28) + (load323 >> 32)) & BodyPartID.bodyIdMax;
            byte[] bArr4 = new byte[16];
            toByteArray(bArr4, j46, 0);
            toByteArray(bArr4, j47, 4);
            toByteArray(bArr4, j48, 8);
            toByteArray(bArr4, load324, 12);
            return bArr4;
        }
        throw new IllegalArgumentException("The key length in bytes must be 32.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (!Bytes.equal(a(bArr, bArr2), bArr3)) {
            throw new GeneralSecurityException("invalid MAC");
        }
    }

    private static void copyBlockSize(byte[] bArr, byte[] bArr2, int i2) {
        int min = Math.min(16, bArr2.length - i2);
        System.arraycopy(bArr2, i2, bArr, 0, min);
        bArr[min] = 1;
        if (min != 16) {
            Arrays.fill(bArr, min + 1, bArr.length, (byte) 0);
        }
    }

    private static long load26(byte[] bArr, int i2, int i3) {
        return (load32(bArr, i2) >> i3) & 67108863;
    }

    private static long load32(byte[] bArr, int i2) {
        return (((bArr[i2 + 3] & 255) << 24) | (bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8) | ((bArr[i2 + 2] & 255) << 16)) & BodyPartID.bodyIdMax;
    }

    private static void toByteArray(byte[] bArr, long j2, int i2) {
        int i3 = 0;
        while (i3 < 4) {
            bArr[i2 + i3] = (byte) (255 & j2);
            i3++;
            j2 >>= 8;
        }
    }
}
