package org.bouncycastle.math.raw;

import java.util.Random;
import org.bouncycastle.util.Integers;
/* loaded from: classes4.dex */
public abstract class Mod {
    private static final int M30 = 1073741823;
    private static final long M32L = 4294967295L;

    private static int add30(int i2, int[] iArr, int[] iArr2) {
        int i3 = i2 - 1;
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            int i6 = i4 + iArr[i5] + iArr2[i5];
            iArr[i5] = 1073741823 & i6;
            i4 = i6 >> 30;
        }
        int i7 = i4 + iArr[i3] + iArr2[i3];
        iArr[i3] = i7;
        return i7 >> 30;
    }

    public static void checkedModOddInverse(int[] iArr, int[] iArr2, int[] iArr3) {
        if (modOddInverse(iArr, iArr2, iArr3) == 0) {
            throw new ArithmeticException("Inverse does not exist.");
        }
    }

    public static void checkedModOddInverseVar(int[] iArr, int[] iArr2, int[] iArr3) {
        if (!modOddInverseVar(iArr, iArr2, iArr3)) {
            throw new ArithmeticException("Inverse does not exist.");
        }
    }

    private static void cnegate30(int i2, int i3, int[] iArr) {
        int i4 = i2 - 1;
        int i5 = 0;
        for (int i6 = 0; i6 < i4; i6++) {
            int i7 = i5 + ((iArr[i6] ^ i3) - i3);
            iArr[i6] = 1073741823 & i7;
            i5 = i7 >> 30;
        }
        iArr[i4] = i5 + ((iArr[i4] ^ i3) - i3);
    }

    private static void cnormalize30(int i2, int i3, int[] iArr, int[] iArr2) {
        int i4 = i2 - 1;
        int i5 = iArr[i4] >> 31;
        int i6 = 0;
        for (int i7 = 0; i7 < i4; i7++) {
            int i8 = i6 + (((iArr[i7] + (iArr2[i7] & i5)) ^ i3) - i3);
            iArr[i7] = 1073741823 & i8;
            i6 = i8 >> 30;
        }
        iArr[i4] = i6 + (((iArr[i4] + (i5 & iArr2[i4])) ^ i3) - i3);
        int i9 = iArr[i4] >> 31;
        int i10 = 0;
        for (int i11 = 0; i11 < i4; i11++) {
            int i12 = i10 + iArr[i11] + (iArr2[i11] & i9);
            iArr[i11] = i12 & 1073741823;
            i10 = i12 >> 30;
        }
        iArr[i4] = i10 + iArr[i4] + (i9 & iArr2[i4]);
    }

    private static void decode30(int i2, int[] iArr, int i3, int[] iArr2, int i4) {
        int i5 = 0;
        long j2 = 0;
        while (i2 > 0) {
            while (i5 < Math.min(32, i2)) {
                j2 |= iArr[i3] << i5;
                i5 += 30;
                i3++;
            }
            iArr2[i4] = (int) j2;
            j2 >>>= 32;
            i5 -= 32;
            i2 -= 32;
            i4++;
        }
    }

    private static int divsteps30(int i2, int i3, int i4, int[] iArr) {
        int i5 = 0;
        int i6 = 0;
        int i7 = 1;
        int i8 = 1;
        for (int i9 = 0; i9 < 30; i9++) {
            int i10 = i2 >> 31;
            int i11 = -(i4 & 1);
            int i12 = i4 + (((i3 ^ i10) - i10) & i11);
            i6 += ((i7 ^ i10) - i10) & i11;
            i8 += ((i5 ^ i10) - i10) & i11;
            int i13 = i10 & i11;
            i2 = (i2 ^ i13) - (i13 + 1);
            i3 += i12 & i13;
            i4 = i12 >> 1;
            i7 = (i7 + (i6 & i13)) << 1;
            i5 = (i5 + (i13 & i8)) << 1;
        }
        iArr[0] = i7;
        iArr[1] = i5;
        iArr[2] = i6;
        iArr[3] = i8;
        return i2;
    }

    private static int divsteps30Var(int i2, int i3, int i4, int[] iArr) {
        int i5;
        int i6 = i3;
        int i7 = i4;
        int i8 = 0;
        int i9 = 0;
        int i10 = 1;
        int i11 = 1;
        int i12 = 30;
        int i13 = i2;
        while (true) {
            int numberOfTrailingZeros = Integers.numberOfTrailingZeros(((-1) << i12) | i7);
            int i14 = i7 >> numberOfTrailingZeros;
            i10 <<= numberOfTrailingZeros;
            i8 <<= numberOfTrailingZeros;
            i13 -= numberOfTrailingZeros;
            i12 -= numberOfTrailingZeros;
            if (i12 <= 0) {
                iArr[0] = i10;
                iArr[1] = i8;
                iArr[2] = i9;
                iArr[3] = i11;
                return i13;
            }
            if (i13 < 0) {
                i13 = -i13;
                int i15 = -i6;
                int i16 = -i10;
                int i17 = -i8;
                int i18 = i13 + 1;
                if (i18 > i12) {
                    i18 = i12;
                }
                i5 = ((-1) >>> (32 - i18)) & 63 & (i14 * i15 * ((i14 * i14) - 2));
                i14 = i15;
                i6 = i14;
                int i19 = i9;
                i9 = i16;
                i10 = i19;
                int i20 = i11;
                i11 = i17;
                i8 = i20;
            } else {
                int i21 = i13 + 1;
                if (i21 > i12) {
                    i21 = i12;
                }
                i5 = ((-1) >>> (32 - i21)) & 15 & ((-((((i6 + 1) & 4) << 1) + i6)) * i14);
            }
            i7 = i14 + (i6 * i5);
            i9 += i10 * i5;
            i11 += i5 * i8;
        }
    }

    private static void encode30(int i2, int[] iArr, int i3, int[] iArr2, int i4) {
        int i5 = 0;
        long j2 = 0;
        while (i2 > 0) {
            if (i5 < Math.min(30, i2)) {
                j2 |= (iArr[i3] & 4294967295L) << i5;
                i5 += 32;
                i3++;
            }
            iArr2[i4] = ((int) j2) & 1073741823;
            j2 >>>= 30;
            i5 -= 30;
            i2 -= 30;
            i4++;
        }
    }

    private static int getMaximumDivsteps(int i2) {
        return ((i2 * 49) + (i2 < 46 ? 80 : 47)) / 17;
    }

    public static int inverse32(int i2) {
        int i3 = (2 - (i2 * i2)) * i2;
        int i4 = i3 * (2 - (i2 * i3));
        int i5 = i4 * (2 - (i2 * i4));
        return i5 * (2 - (i2 * i5));
    }

    public static int modOddInverse(int[] iArr, int[] iArr2, int[] iArr3) {
        int length = iArr.length;
        int numberOfLeadingZeros = (length << 5) - Integers.numberOfLeadingZeros(iArr[length - 1]);
        int i2 = (numberOfLeadingZeros + 29) / 30;
        int[] iArr4 = new int[4];
        int[] iArr5 = new int[i2];
        int[] iArr6 = new int[i2];
        int[] iArr7 = new int[i2];
        int[] iArr8 = new int[i2];
        int[] iArr9 = new int[i2];
        int i3 = 0;
        iArr6[0] = 1;
        encode30(numberOfLeadingZeros, iArr2, 0, iArr8, 0);
        encode30(numberOfLeadingZeros, iArr, 0, iArr9, 0);
        System.arraycopy(iArr9, 0, iArr7, 0, i2);
        int inverse32 = inverse32(iArr9[0]);
        int i4 = -1;
        int i5 = 0;
        for (int maximumDivsteps = getMaximumDivsteps(numberOfLeadingZeros); i5 < maximumDivsteps; maximumDivsteps = maximumDivsteps) {
            int divsteps30 = divsteps30(i4, iArr7[i3], iArr8[i3], iArr4);
            updateDE30(i2, iArr5, iArr6, iArr4, inverse32, iArr9);
            updateFG30(i2, iArr7, iArr8, iArr4);
            i5 += 30;
            i3 = i3;
            i4 = divsteps30;
        }
        int i6 = i3;
        int i7 = iArr7[i2 - 1] >> 31;
        cnegate30(i2, i7, iArr7);
        cnormalize30(i2, i7, iArr5, iArr9);
        decode30(numberOfLeadingZeros, iArr5, i6, iArr3, i6);
        return Nat.equalTo(i2, iArr7, 1) & Nat.equalToZero(i2, iArr8);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v3 */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r9v3 */
    public static boolean modOddInverseVar(int[] iArr, int[] iArr2, int[] iArr3) {
        int length = iArr.length;
        int numberOfLeadingZeros = (length << 5) - Integers.numberOfLeadingZeros(iArr[length - 1]);
        int i2 = (numberOfLeadingZeros + 29) / 30;
        int[] iArr4 = new int[4];
        int[] iArr5 = new int[i2];
        int[] iArr6 = new int[i2];
        int[] iArr7 = new int[i2];
        int[] iArr8 = new int[i2];
        int[] iArr9 = new int[i2];
        ?? r9 = 0;
        iArr6[0] = 1;
        encode30(numberOfLeadingZeros, iArr2, 0, iArr8, 0);
        encode30(numberOfLeadingZeros, iArr, 0, iArr9, 0);
        System.arraycopy(iArr9, 0, iArr7, 0, i2);
        int i3 = i2 - 1;
        int numberOfLeadingZeros2 = (-1) - (Integers.numberOfLeadingZeros(iArr8[i3] | 1) - (((i2 * 30) + 2) - numberOfLeadingZeros));
        int inverse32 = inverse32(iArr9[0]);
        int maximumDivsteps = getMaximumDivsteps(numberOfLeadingZeros);
        int i4 = i2;
        int i5 = 0;
        while (!Nat.isZero(i4, iArr8)) {
            if (i5 >= maximumDivsteps) {
                return r9;
            }
            int i6 = i5 + 30;
            int divsteps30Var = divsteps30Var(numberOfLeadingZeros2, iArr7[r9], iArr8[r9], iArr4);
            int[] iArr10 = iArr6;
            int i7 = i4;
            int i8 = maximumDivsteps;
            int[] iArr11 = iArr6;
            ?? r12 = r9;
            updateDE30(i2, iArr5, iArr10, iArr4, inverse32, iArr9);
            updateFG30(i7, iArr7, iArr8, iArr4);
            int i9 = i7 - 1;
            int i10 = iArr7[i9];
            int i11 = iArr8[i9];
            int i12 = i7 - 2;
            if (((i12 >> 31) | ((i10 >> 31) ^ i10) | ((i11 >> 31) ^ i11)) == 0) {
                iArr7[i12] = (i10 << 30) | iArr7[i12];
                iArr8[i12] = iArr8[i12] | (i11 << 30);
                i4 = i7 - 1;
            } else {
                i4 = i7;
            }
            r9 = r12;
            i5 = i6;
            numberOfLeadingZeros2 = divsteps30Var;
            maximumDivsteps = i8;
            iArr6 = iArr11;
        }
        int i13 = i4;
        boolean z = r9;
        int i14 = iArr7[i13 - 1] >> 31;
        int i15 = iArr5[i3] >> 31;
        if (i15 < 0) {
            i15 = add30(i2, iArr5, iArr9);
        }
        if (i14 < 0) {
            i15 = negate30(i2, iArr5);
            negate30(i13, iArr7);
        }
        if (Nat.isOne(i13, iArr7)) {
            if (i15 < 0) {
                add30(i2, iArr5, iArr9);
            }
            decode30(numberOfLeadingZeros, iArr5, z ? 1 : 0, iArr3, z ? 1 : 0);
            return true;
        }
        return z;
    }

    private static int negate30(int i2, int[] iArr) {
        int i3 = i2 - 1;
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            int i6 = i4 - iArr[i5];
            iArr[i5] = 1073741823 & i6;
            i4 = i6 >> 30;
        }
        int i7 = i4 - iArr[i3];
        iArr[i3] = i7;
        return i7 >> 30;
    }

    public static int[] random(int[] iArr) {
        int length = iArr.length;
        Random random = new Random();
        int[] create = Nat.create(length);
        int i2 = length - 1;
        int i3 = iArr[i2];
        int i4 = i3 | (i3 >>> 1);
        int i5 = i4 | (i4 >>> 2);
        int i6 = i5 | (i5 >>> 4);
        int i7 = i6 | (i6 >>> 8);
        int i8 = i7 | (i7 >>> 16);
        do {
            for (int i9 = 0; i9 != length; i9++) {
                create[i9] = random.nextInt();
            }
            create[i2] = create[i2] & i8;
        } while (Nat.gte(length, create, iArr));
        return create;
    }

    private static void updateDE30(int i2, int[] iArr, int[] iArr2, int[] iArr3, int i3, int[] iArr4) {
        int i4 = i2;
        int i5 = iArr3[0];
        int i6 = iArr3[1];
        int i7 = iArr3[2];
        int i8 = iArr3[3];
        int i9 = i4 - 1;
        int i10 = iArr[i9] >> 31;
        int i11 = iArr2[i9] >> 31;
        int i12 = (i5 & i10) + (i6 & i11);
        int i13 = (i10 & i7) + (i11 & i8);
        int i14 = iArr4[0];
        long j2 = i5;
        long j3 = iArr[0];
        long j4 = i6;
        long j5 = iArr2[0];
        long j6 = (j2 * j3) + (j4 * j5);
        long j7 = i7;
        long j8 = i8;
        long j9 = (j3 * j7) + (j5 * j8);
        long j10 = i14;
        long j11 = i12 - (((((int) j6) * i3) + i12) & 1073741823);
        int i15 = i9;
        long j12 = i13 - (((((int) j9) * i3) + i13) & 1073741823);
        long j13 = (j9 + (j10 * j12)) >> 30;
        long j14 = (j6 + (j10 * j11)) >> 30;
        int i16 = 1;
        while (i16 < i4) {
            int i17 = iArr4[i16];
            long j15 = j13;
            long j16 = iArr[i16];
            int i18 = i16;
            long j17 = iArr2[i16];
            long j18 = j12;
            long j19 = i17;
            long j20 = j14 + (j2 * j16) + (j4 * j17) + (j19 * j11);
            long j21 = j15 + (j16 * j7) + (j17 * j8) + (j19 * j18);
            int i19 = i18 - 1;
            iArr[i19] = ((int) j20) & 1073741823;
            j14 = j20 >> 30;
            iArr2[i19] = ((int) j21) & 1073741823;
            j13 = j21 >> 30;
            i16 = i18 + 1;
            i4 = i2;
            i15 = i15;
            j12 = j18;
        }
        int i20 = i15;
        iArr[i20] = (int) j14;
        iArr2[i20] = (int) j13;
    }

    private static void updateFG30(int i2, int[] iArr, int[] iArr2, int[] iArr3) {
        int i3 = iArr3[0];
        int i4 = 1;
        int i5 = iArr3[1];
        int i6 = iArr3[2];
        int i7 = iArr3[3];
        long j2 = i3;
        long j3 = iArr[0];
        long j4 = i5;
        long j5 = iArr2[0];
        long j6 = i6;
        long j7 = i7;
        long j8 = ((j2 * j3) + (j4 * j5)) >> 30;
        long j9 = ((j3 * j6) + (j5 * j7)) >> 30;
        int i8 = 1;
        while (i8 < i2) {
            int i9 = iArr[i8];
            int i10 = iArr2[i8];
            int i11 = i8;
            long j10 = i9;
            long j11 = j2 * j10;
            long j12 = j2;
            long j13 = i10;
            long j14 = j8 + j11 + (j4 * j13);
            long j15 = j9 + (j10 * j6) + (j13 * j7);
            int i12 = i11 - 1;
            iArr[i12] = ((int) j14) & 1073741823;
            j8 = j14 >> 30;
            iArr2[i12] = 1073741823 & ((int) j15);
            j9 = j15 >> 30;
            i8 = i11 + 1;
            j2 = j12;
            i4 = 1;
        }
        int i13 = i2 - i4;
        iArr[i13] = (int) j8;
        iArr2[i13] = (int) j9;
    }
}
