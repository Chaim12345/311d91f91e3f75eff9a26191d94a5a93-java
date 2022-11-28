package com.google.crypto.tink.subtle;

import com.google.crypto.tink.annotations.Alpha;
import java.util.Arrays;
@Alpha
/* loaded from: classes2.dex */
final class Field25519 {
    private static final long TWO_TO_25 = 33554432;
    private static final long TWO_TO_26 = 67108864;
    private static final int[] EXPAND_START = {0, 3, 6, 9, 12, 16, 19, 22, 25, 28};
    private static final int[] EXPAND_SHIFT = {0, 2, 3, 5, 6, 0, 1, 3, 4, 6};
    private static final int[] MASK = {67108863, 33554431};
    private static final int[] SHIFT = {26, 25};

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] a(long[] jArr) {
        int i2;
        int i3;
        int i4;
        int i5;
        int[] iArr;
        int i6;
        long[] copyOf = Arrays.copyOf(jArr, 10);
        for (int i7 = 0; i7 < 2; i7++) {
            int i8 = 0;
            while (i8 < 9) {
                int i9 = -((int) ((copyOf[i8] & (copyOf[i8] >> 31)) >> SHIFT[i8 & 1]));
                copyOf[i8] = copyOf[i8] + (i9 << iArr[i6]);
                i8++;
                copyOf[i8] = copyOf[i8] - i9;
            }
            int i10 = -((int) (((copyOf[9] >> 31) & copyOf[9]) >> 25));
            copyOf[9] = copyOf[9] + (i10 << 25);
            copyOf[0] = copyOf[0] - (i10 * 19);
        }
        int i11 = -((int) ((copyOf[0] & (copyOf[0] >> 31)) >> 26));
        copyOf[0] = copyOf[0] + (i11 << 26);
        copyOf[1] = copyOf[1] - i11;
        for (int i12 = 0; i12 < 2; i12++) {
            int i13 = 0;
            while (i13 < 9) {
                int i14 = i13 & 1;
                copyOf[i13] = copyOf[i13] & MASK[i14];
                i13++;
                copyOf[i13] = copyOf[i13] + ((int) (copyOf[i13] >> SHIFT[i14]));
            }
        }
        copyOf[9] = copyOf[9] & 33554431;
        copyOf[0] = copyOf[0] + (((int) (copyOf[9] >> 25)) * 19);
        int gte = gte((int) copyOf[0], 67108845);
        for (int i15 = 1; i15 < 10; i15++) {
            gte &= eq((int) copyOf[i15], MASK[i15 & 1]);
        }
        copyOf[0] = copyOf[0] - (67108845 & gte);
        long j2 = 33554431 & gte;
        copyOf[1] = copyOf[1] - j2;
        for (int i16 = 2; i16 < 10; i16 += 2) {
            copyOf[i16] = copyOf[i16] - (67108863 & gte);
            int i17 = i16 + 1;
            copyOf[i17] = copyOf[i17] - j2;
        }
        for (int i18 = 0; i18 < 10; i18++) {
            copyOf[i18] = copyOf[i18] << EXPAND_SHIFT[i18];
        }
        byte[] bArr = new byte[32];
        for (int i19 = 0; i19 < 10; i19++) {
            int[] iArr2 = EXPAND_START;
            bArr[iArr2[i19]] = (byte) (bArr[i2] | (copyOf[i19] & 255));
            bArr[iArr2[i19] + 1] = (byte) (bArr[i3] | ((copyOf[i19] >> 8) & 255));
            bArr[iArr2[i19] + 2] = (byte) (bArr[i4] | ((copyOf[i19] >> 16) & 255));
            bArr[iArr2[i19] + 3] = (byte) (bArr[i5] | ((copyOf[i19] >> 24) & 255));
        }
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long[] b(byte[] bArr) {
        long[] jArr = new long[10];
        for (int i2 = 0; i2 < 10; i2++) {
            int[] iArr = EXPAND_START;
            jArr[i2] = (((((bArr[iArr[i2]] & 255) | ((bArr[iArr[i2] + 1] & 255) << 8)) | ((bArr[iArr[i2] + 2] & 255) << 16)) | ((bArr[iArr[i2] + 3] & 255) << 24)) >> EXPAND_SHIFT[i2]) & MASK[i2 & 1];
        }
        return jArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void c(long[] jArr, long[] jArr2) {
        long[] jArr3 = new long[10];
        long[] jArr4 = new long[10];
        long[] jArr5 = new long[10];
        long[] jArr6 = new long[10];
        long[] jArr7 = new long[10];
        long[] jArr8 = new long[10];
        long[] jArr9 = new long[10];
        long[] jArr10 = new long[10];
        long[] jArr11 = new long[10];
        long[] jArr12 = new long[10];
        j(jArr3, jArr2);
        j(jArr12, jArr3);
        j(jArr11, jArr12);
        d(jArr4, jArr11, jArr2);
        d(jArr5, jArr4, jArr3);
        j(jArr11, jArr5);
        d(jArr6, jArr11, jArr4);
        j(jArr11, jArr6);
        j(jArr12, jArr11);
        j(jArr11, jArr12);
        j(jArr12, jArr11);
        j(jArr11, jArr12);
        d(jArr7, jArr11, jArr6);
        j(jArr11, jArr7);
        j(jArr12, jArr11);
        for (int i2 = 2; i2 < 10; i2 += 2) {
            j(jArr11, jArr12);
            j(jArr12, jArr11);
        }
        d(jArr8, jArr12, jArr7);
        j(jArr11, jArr8);
        j(jArr12, jArr11);
        for (int i3 = 2; i3 < 20; i3 += 2) {
            j(jArr11, jArr12);
            j(jArr12, jArr11);
        }
        d(jArr11, jArr12, jArr8);
        j(jArr12, jArr11);
        j(jArr11, jArr12);
        for (int i4 = 2; i4 < 10; i4 += 2) {
            j(jArr12, jArr11);
            j(jArr11, jArr12);
        }
        d(jArr9, jArr11, jArr7);
        j(jArr11, jArr9);
        j(jArr12, jArr11);
        for (int i5 = 2; i5 < 50; i5 += 2) {
            j(jArr11, jArr12);
            j(jArr12, jArr11);
        }
        d(jArr10, jArr12, jArr9);
        j(jArr12, jArr10);
        j(jArr11, jArr12);
        for (int i6 = 2; i6 < 100; i6 += 2) {
            j(jArr12, jArr11);
            j(jArr11, jArr12);
        }
        d(jArr12, jArr11, jArr10);
        j(jArr11, jArr12);
        j(jArr12, jArr11);
        for (int i7 = 2; i7 < 50; i7 += 2) {
            j(jArr11, jArr12);
            j(jArr12, jArr11);
        }
        d(jArr11, jArr12, jArr9);
        j(jArr12, jArr11);
        j(jArr11, jArr12);
        j(jArr12, jArr11);
        j(jArr11, jArr12);
        j(jArr12, jArr11);
        d(jArr, jArr12, jArr5);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void d(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArr4 = new long[19];
        e(jArr4, jArr2, jArr3);
        f(jArr4, jArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void e(long[] jArr, long[] jArr2, long[] jArr3) {
        jArr[0] = jArr2[0] * jArr3[0];
        jArr[1] = (jArr2[0] * jArr3[1]) + (jArr2[1] * jArr3[0]);
        jArr[2] = (jArr2[1] * 2 * jArr3[1]) + (jArr2[0] * jArr3[2]) + (jArr2[2] * jArr3[0]);
        jArr[3] = (jArr2[1] * jArr3[2]) + (jArr2[2] * jArr3[1]) + (jArr2[0] * jArr3[3]) + (jArr2[3] * jArr3[0]);
        jArr[4] = (jArr2[2] * jArr3[2]) + (((jArr2[1] * jArr3[3]) + (jArr2[3] * jArr3[1])) * 2) + (jArr2[0] * jArr3[4]) + (jArr2[4] * jArr3[0]);
        jArr[5] = (jArr2[2] * jArr3[3]) + (jArr2[3] * jArr3[2]) + (jArr2[1] * jArr3[4]) + (jArr2[4] * jArr3[1]) + (jArr2[0] * jArr3[5]) + (jArr2[5] * jArr3[0]);
        jArr[6] = (((jArr2[3] * jArr3[3]) + (jArr2[1] * jArr3[5]) + (jArr2[5] * jArr3[1])) * 2) + (jArr2[2] * jArr3[4]) + (jArr2[4] * jArr3[2]) + (jArr2[0] * jArr3[6]) + (jArr2[6] * jArr3[0]);
        jArr[7] = (jArr2[3] * jArr3[4]) + (jArr2[4] * jArr3[3]) + (jArr2[2] * jArr3[5]) + (jArr2[5] * jArr3[2]) + (jArr2[1] * jArr3[6]) + (jArr2[6] * jArr3[1]) + (jArr2[0] * jArr3[7]) + (jArr2[7] * jArr3[0]);
        jArr[8] = (jArr2[4] * jArr3[4]) + (((jArr2[3] * jArr3[5]) + (jArr2[5] * jArr3[3]) + (jArr2[1] * jArr3[7]) + (jArr2[7] * jArr3[1])) * 2) + (jArr2[2] * jArr3[6]) + (jArr2[6] * jArr3[2]) + (jArr2[0] * jArr3[8]) + (jArr2[8] * jArr3[0]);
        jArr[9] = (jArr2[4] * jArr3[5]) + (jArr2[5] * jArr3[4]) + (jArr2[3] * jArr3[6]) + (jArr2[6] * jArr3[3]) + (jArr2[2] * jArr3[7]) + (jArr2[7] * jArr3[2]) + (jArr2[1] * jArr3[8]) + (jArr2[8] * jArr3[1]) + (jArr2[0] * jArr3[9]) + (jArr2[9] * jArr3[0]);
        jArr[10] = (((jArr2[5] * jArr3[5]) + (jArr2[3] * jArr3[7]) + (jArr2[7] * jArr3[3]) + (jArr2[1] * jArr3[9]) + (jArr2[9] * jArr3[1])) * 2) + (jArr2[4] * jArr3[6]) + (jArr2[6] * jArr3[4]) + (jArr2[2] * jArr3[8]) + (jArr2[8] * jArr3[2]);
        jArr[11] = (jArr2[5] * jArr3[6]) + (jArr2[6] * jArr3[5]) + (jArr2[4] * jArr3[7]) + (jArr2[7] * jArr3[4]) + (jArr2[3] * jArr3[8]) + (jArr2[8] * jArr3[3]) + (jArr2[2] * jArr3[9]) + (jArr2[9] * jArr3[2]);
        jArr[12] = (jArr2[6] * jArr3[6]) + (((jArr2[5] * jArr3[7]) + (jArr2[7] * jArr3[5]) + (jArr2[3] * jArr3[9]) + (jArr2[9] * jArr3[3])) * 2) + (jArr2[4] * jArr3[8]) + (jArr2[8] * jArr3[4]);
        jArr[13] = (jArr2[6] * jArr3[7]) + (jArr2[7] * jArr3[6]) + (jArr2[5] * jArr3[8]) + (jArr2[8] * jArr3[5]) + (jArr2[4] * jArr3[9]) + (jArr2[9] * jArr3[4]);
        jArr[14] = (((jArr2[7] * jArr3[7]) + (jArr2[5] * jArr3[9]) + (jArr2[9] * jArr3[5])) * 2) + (jArr2[6] * jArr3[8]) + (jArr2[8] * jArr3[6]);
        jArr[15] = (jArr2[7] * jArr3[8]) + (jArr2[8] * jArr3[7]) + (jArr2[6] * jArr3[9]) + (jArr2[9] * jArr3[6]);
        jArr[16] = (jArr2[8] * jArr3[8]) + (((jArr2[7] * jArr3[9]) + (jArr2[9] * jArr3[7])) * 2);
        jArr[17] = (jArr2[8] * jArr3[9]) + (jArr2[9] * jArr3[8]);
        jArr[18] = jArr2[9] * 2 * jArr3[9];
    }

    private static int eq(int i2, int i3) {
        int i4 = ~(i2 ^ i3);
        int i5 = i4 & (i4 << 16);
        int i6 = i5 & (i5 << 8);
        int i7 = i6 & (i6 << 4);
        int i8 = i7 & (i7 << 2);
        return (i8 & (i8 << 1)) >> 31;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void f(long[] jArr, long[] jArr2) {
        if (jArr.length != 19) {
            long[] jArr3 = new long[19];
            System.arraycopy(jArr, 0, jArr3, 0, jArr.length);
            jArr = jArr3;
        }
        h(jArr);
        g(jArr);
        System.arraycopy(jArr, 0, jArr2, 0, 10);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void g(long[] jArr) {
        jArr[10] = 0;
        int i2 = 0;
        while (i2 < 10) {
            long j2 = jArr[i2] / TWO_TO_26;
            jArr[i2] = jArr[i2] - (j2 << 26);
            int i3 = i2 + 1;
            jArr[i3] = jArr[i3] + j2;
            long j3 = jArr[i3] / TWO_TO_25;
            jArr[i3] = jArr[i3] - (j3 << 25);
            i2 += 2;
            jArr[i2] = jArr[i2] + j3;
        }
        jArr[0] = jArr[0] + (jArr[10] << 4);
        jArr[0] = jArr[0] + (jArr[10] << 1);
        jArr[0] = jArr[0] + jArr[10];
        jArr[10] = 0;
        long j4 = jArr[0] / TWO_TO_26;
        jArr[0] = jArr[0] - (j4 << 26);
        jArr[1] = jArr[1] + j4;
    }

    private static int gte(int i2, int i3) {
        return ~((i2 - i3) >> 31);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void h(long[] jArr) {
        jArr[8] = jArr[8] + (jArr[18] << 4);
        jArr[8] = jArr[8] + (jArr[18] << 1);
        jArr[8] = jArr[8] + jArr[18];
        jArr[7] = jArr[7] + (jArr[17] << 4);
        jArr[7] = jArr[7] + (jArr[17] << 1);
        jArr[7] = jArr[7] + jArr[17];
        jArr[6] = jArr[6] + (jArr[16] << 4);
        jArr[6] = jArr[6] + (jArr[16] << 1);
        jArr[6] = jArr[6] + jArr[16];
        jArr[5] = jArr[5] + (jArr[15] << 4);
        jArr[5] = jArr[5] + (jArr[15] << 1);
        jArr[5] = jArr[5] + jArr[15];
        jArr[4] = jArr[4] + (jArr[14] << 4);
        jArr[4] = jArr[4] + (jArr[14] << 1);
        jArr[4] = jArr[4] + jArr[14];
        jArr[3] = jArr[3] + (jArr[13] << 4);
        jArr[3] = jArr[3] + (jArr[13] << 1);
        jArr[3] = jArr[3] + jArr[13];
        jArr[2] = jArr[2] + (jArr[12] << 4);
        jArr[2] = jArr[2] + (jArr[12] << 1);
        jArr[2] = jArr[2] + jArr[12];
        jArr[1] = jArr[1] + (jArr[11] << 4);
        jArr[1] = jArr[1] + (jArr[11] << 1);
        jArr[1] = jArr[1] + jArr[11];
        jArr[0] = jArr[0] + (jArr[10] << 4);
        jArr[0] = jArr[0] + (jArr[10] << 1);
        jArr[0] = jArr[0] + jArr[10];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void i(long[] jArr, long[] jArr2, long j2) {
        for (int i2 = 0; i2 < 10; i2++) {
            jArr[i2] = jArr2[i2] * j2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void j(long[] jArr, long[] jArr2) {
        long[] jArr3 = new long[19];
        squareInner(jArr3, jArr2);
        f(jArr3, jArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void k(long[] jArr, long[] jArr2) {
        l(jArr, jArr2, jArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void l(long[] jArr, long[] jArr2, long[] jArr3) {
        for (int i2 = 0; i2 < 10; i2++) {
            jArr[i2] = jArr2[i2] - jArr3[i2];
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void m(long[] jArr, long[] jArr2) {
        n(jArr, jArr, jArr2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void n(long[] jArr, long[] jArr2, long[] jArr3) {
        for (int i2 = 0; i2 < 10; i2++) {
            jArr[i2] = jArr2[i2] + jArr3[i2];
        }
    }

    private static void squareInner(long[] jArr, long[] jArr2) {
        jArr[0] = jArr2[0] * jArr2[0];
        jArr[1] = jArr2[0] * 2 * jArr2[1];
        jArr[2] = ((jArr2[1] * jArr2[1]) + (jArr2[0] * jArr2[2])) * 2;
        jArr[3] = ((jArr2[1] * jArr2[2]) + (jArr2[0] * jArr2[3])) * 2;
        jArr[4] = (jArr2[2] * jArr2[2]) + (jArr2[1] * 4 * jArr2[3]) + (jArr2[0] * 2 * jArr2[4]);
        jArr[5] = ((jArr2[2] * jArr2[3]) + (jArr2[1] * jArr2[4]) + (jArr2[0] * jArr2[5])) * 2;
        jArr[6] = ((jArr2[3] * jArr2[3]) + (jArr2[2] * jArr2[4]) + (jArr2[0] * jArr2[6]) + (jArr2[1] * 2 * jArr2[5])) * 2;
        jArr[7] = ((jArr2[3] * jArr2[4]) + (jArr2[2] * jArr2[5]) + (jArr2[1] * jArr2[6]) + (jArr2[0] * jArr2[7])) * 2;
        jArr[8] = (jArr2[4] * jArr2[4]) + (((jArr2[2] * jArr2[6]) + (jArr2[0] * jArr2[8]) + (((jArr2[1] * jArr2[7]) + (jArr2[3] * jArr2[5])) * 2)) * 2);
        jArr[9] = ((jArr2[4] * jArr2[5]) + (jArr2[3] * jArr2[6]) + (jArr2[2] * jArr2[7]) + (jArr2[1] * jArr2[8]) + (jArr2[0] * jArr2[9])) * 2;
        jArr[10] = ((jArr2[5] * jArr2[5]) + (jArr2[4] * jArr2[6]) + (jArr2[2] * jArr2[8]) + (((jArr2[3] * jArr2[7]) + (jArr2[1] * jArr2[9])) * 2)) * 2;
        jArr[11] = ((jArr2[5] * jArr2[6]) + (jArr2[4] * jArr2[7]) + (jArr2[3] * jArr2[8]) + (jArr2[2] * jArr2[9])) * 2;
        jArr[12] = (jArr2[6] * jArr2[6]) + (((jArr2[4] * jArr2[8]) + (((jArr2[5] * jArr2[7]) + (jArr2[3] * jArr2[9])) * 2)) * 2);
        jArr[13] = ((jArr2[6] * jArr2[7]) + (jArr2[5] * jArr2[8]) + (jArr2[4] * jArr2[9])) * 2;
        jArr[14] = ((jArr2[7] * jArr2[7]) + (jArr2[6] * jArr2[8]) + (jArr2[5] * 2 * jArr2[9])) * 2;
        jArr[15] = ((jArr2[7] * jArr2[8]) + (jArr2[6] * jArr2[9])) * 2;
        jArr[16] = (jArr2[8] * jArr2[8]) + (jArr2[7] * 4 * jArr2[9]);
        jArr[17] = jArr2[8] * 2 * jArr2[9];
        jArr[18] = jArr2[9] * 2 * jArr2[9];
    }
}
