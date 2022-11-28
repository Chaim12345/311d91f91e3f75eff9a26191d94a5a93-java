package org.bouncycastle.math.raw;

import org.apache.http.message.TokenParser;
/* loaded from: classes4.dex */
public abstract class Mont256 {
    private static final long M = 4294967295L;

    public static int inverse32(int i2) {
        int i3 = (2 - (i2 * i2)) * i2;
        int i4 = i3 * (2 - (i2 * i3));
        int i5 = i4 * (2 - (i2 * i4));
        return i5 * (2 - (i2 * i5));
    }

    public static void multAdd(int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, int i2) {
        long j2;
        char c2 = 0;
        long j3 = iArr2[0] & 4294967295L;
        int i3 = 0;
        int i4 = 0;
        while (i3 < 8) {
            long j4 = iArr[i3] & 4294967295L;
            long j5 = j4 * j3;
            long j6 = j3;
            long j7 = (((int) j2) * i2) & 4294967295L;
            int i5 = i3;
            int i6 = i4;
            long j8 = (iArr4[c2] & 4294967295L) * j7;
            char c3 = TokenParser.SP;
            long j9 = ((((j5 & 4294967295L) + (iArr3[c2] & 4294967295L)) + (j8 & 4294967295L)) >>> 32) + (j5 >>> 32) + (j8 >>> 32);
            int i7 = 1;
            while (i7 < 8) {
                long j10 = (iArr2[i7] & 4294967295L) * j4;
                long j11 = (iArr4[i7] & 4294967295L) * j7;
                long j12 = j9 + (j10 & 4294967295L) + (j11 & 4294967295L) + (iArr3[i7] & 4294967295L);
                iArr3[i7 - 1] = (int) j12;
                j9 = (j12 >>> 32) + (j10 >>> 32) + (j11 >>> 32);
                i7++;
                c3 = ' ';
                j7 = j7;
            }
            long j13 = j9 + (i6 & 4294967295L);
            iArr3[7] = (int) j13;
            i4 = (int) (j13 >>> c3);
            i3 = i5 + 1;
            j3 = j6;
            c2 = 0;
        }
        if (i4 != 0 || Nat256.gte(iArr3, iArr4)) {
            Nat256.sub(iArr3, iArr4, iArr3);
        }
    }

    public static void multAddXF(int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4) {
        char c2 = 0;
        long j2 = iArr2[0] & 4294967295L;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i2 >= 8) {
                break;
            }
            long j3 = iArr[i2] & 4294967295L;
            long j4 = (j3 * j2) + (iArr3[c2] & 4294967295L);
            long j5 = j4 & 4294967295L;
            long j6 = (j4 >>> 32) + j5;
            int i4 = 1;
            for (int i5 = 8; i4 < i5; i5 = 8) {
                long j7 = j2;
                long j8 = (iArr2[i4] & 4294967295L) * j3;
                long j9 = (iArr4[i4] & 4294967295L) * j5;
                long j10 = j6 + (j8 & 4294967295L) + (j9 & 4294967295L) + (iArr3[i4] & 4294967295L);
                iArr3[i4 - 1] = (int) j10;
                j6 = (j10 >>> 32) + (j8 >>> 32) + (j9 >>> 32);
                i4++;
                j2 = j7;
                j3 = j3;
                j5 = j5;
            }
            long j11 = j6 + (i3 & 4294967295L);
            iArr3[7] = (int) j11;
            i3 = (int) (j11 >>> 32);
            i2++;
            j2 = j2;
            c2 = 0;
        }
        if (i3 != 0 || Nat256.gte(iArr3, iArr4)) {
            Nat256.sub(iArr3, iArr4, iArr3);
        }
    }

    public static void reduce(int[] iArr, int[] iArr2, int i2) {
        int i3;
        char c2 = 0;
        int i4 = 0;
        while (i4 < 8) {
            long j2 = (i3 * i2) & 4294967295L;
            long j3 = (((iArr2[c2] & 4294967295L) * j2) + (iArr[c2] & 4294967295L)) >>> 32;
            int i5 = 1;
            while (i5 < 8) {
                long j4 = j3 + ((iArr2[i5] & 4294967295L) * j2) + (iArr[i5] & 4294967295L);
                iArr[i5 - 1] = (int) j4;
                j3 = j4 >>> 32;
                i5++;
                i4 = i4;
            }
            iArr[7] = (int) j3;
            i4++;
            c2 = 0;
        }
        if (Nat256.gte(iArr, iArr2)) {
            Nat256.sub(iArr, iArr2, iArr);
        }
    }

    public static void reduceXF(int[] iArr, int[] iArr2) {
        for (int i2 = 0; i2 < 8; i2++) {
            long j2 = iArr[0] & 4294967295L;
            long j3 = j2;
            for (int i3 = 1; i3 < 8; i3++) {
                long j4 = j3 + ((iArr2[i3] & 4294967295L) * j2) + (iArr[i3] & 4294967295L);
                iArr[i3 - 1] = (int) j4;
                j3 = j4 >>> 32;
            }
            iArr[7] = (int) j3;
        }
        if (Nat256.gte(iArr, iArr2)) {
            Nat256.sub(iArr, iArr2, iArr);
        }
    }
}
