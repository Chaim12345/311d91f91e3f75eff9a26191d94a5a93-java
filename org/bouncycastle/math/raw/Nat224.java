package org.bouncycastle.math.raw;

import java.math.BigInteger;
import org.bouncycastle.util.Pack;
/* loaded from: classes4.dex */
public abstract class Nat224 {
    private static final long M = 4294967295L;

    public static int add(int[] iArr, int i2, int[] iArr2, int i3, int[] iArr3, int i4) {
        long j2 = (iArr[i2 + 0] & 4294967295L) + (iArr2[i3 + 0] & 4294967295L) + 0;
        iArr3[i4 + 0] = (int) j2;
        long j3 = (j2 >>> 32) + (iArr[i2 + 1] & 4294967295L) + (iArr2[i3 + 1] & 4294967295L);
        iArr3[i4 + 1] = (int) j3;
        long j4 = (j3 >>> 32) + (iArr[i2 + 2] & 4294967295L) + (iArr2[i3 + 2] & 4294967295L);
        iArr3[i4 + 2] = (int) j4;
        long j5 = (j4 >>> 32) + (iArr[i2 + 3] & 4294967295L) + (iArr2[i3 + 3] & 4294967295L);
        iArr3[i4 + 3] = (int) j5;
        long j6 = (j5 >>> 32) + (iArr[i2 + 4] & 4294967295L) + (iArr2[i3 + 4] & 4294967295L);
        iArr3[i4 + 4] = (int) j6;
        long j7 = (j6 >>> 32) + (iArr[i2 + 5] & 4294967295L) + (iArr2[i3 + 5] & 4294967295L);
        iArr3[i4 + 5] = (int) j7;
        long j8 = (j7 >>> 32) + (iArr[i2 + 6] & 4294967295L) + (iArr2[i3 + 6] & 4294967295L);
        iArr3[i4 + 6] = (int) j8;
        return (int) (j8 >>> 32);
    }

    public static int add(int[] iArr, int[] iArr2, int[] iArr3) {
        long j2 = (iArr[0] & 4294967295L) + (iArr2[0] & 4294967295L) + 0;
        iArr3[0] = (int) j2;
        long j3 = (j2 >>> 32) + (iArr[1] & 4294967295L) + (iArr2[1] & 4294967295L);
        iArr3[1] = (int) j3;
        long j4 = (j3 >>> 32) + (iArr[2] & 4294967295L) + (iArr2[2] & 4294967295L);
        iArr3[2] = (int) j4;
        long j5 = (j4 >>> 32) + (iArr[3] & 4294967295L) + (iArr2[3] & 4294967295L);
        iArr3[3] = (int) j5;
        long j6 = (j5 >>> 32) + (iArr[4] & 4294967295L) + (iArr2[4] & 4294967295L);
        iArr3[4] = (int) j6;
        long j7 = (j6 >>> 32) + (iArr[5] & 4294967295L) + (iArr2[5] & 4294967295L);
        iArr3[5] = (int) j7;
        long j8 = (j7 >>> 32) + (iArr[6] & 4294967295L) + (iArr2[6] & 4294967295L);
        iArr3[6] = (int) j8;
        return (int) (j8 >>> 32);
    }

    public static int addBothTo(int[] iArr, int i2, int[] iArr2, int i3, int[] iArr3, int i4) {
        int i5 = i4 + 0;
        long j2 = (iArr[i2 + 0] & 4294967295L) + (iArr2[i3 + 0] & 4294967295L) + (iArr3[i5] & 4294967295L) + 0;
        iArr3[i5] = (int) j2;
        int i6 = i4 + 1;
        long j3 = (j2 >>> 32) + (iArr[i2 + 1] & 4294967295L) + (iArr2[i3 + 1] & 4294967295L) + (iArr3[i6] & 4294967295L);
        iArr3[i6] = (int) j3;
        int i7 = i4 + 2;
        long j4 = (j3 >>> 32) + (iArr[i2 + 2] & 4294967295L) + (iArr2[i3 + 2] & 4294967295L) + (iArr3[i7] & 4294967295L);
        iArr3[i7] = (int) j4;
        int i8 = i4 + 3;
        long j5 = (j4 >>> 32) + (iArr[i2 + 3] & 4294967295L) + (iArr2[i3 + 3] & 4294967295L) + (iArr3[i8] & 4294967295L);
        iArr3[i8] = (int) j5;
        int i9 = i4 + 4;
        long j6 = (j5 >>> 32) + (iArr[i2 + 4] & 4294967295L) + (iArr2[i3 + 4] & 4294967295L) + (iArr3[i9] & 4294967295L);
        iArr3[i9] = (int) j6;
        int i10 = i4 + 5;
        long j7 = (j6 >>> 32) + (iArr[i2 + 5] & 4294967295L) + (iArr2[i3 + 5] & 4294967295L) + (iArr3[i10] & 4294967295L);
        iArr3[i10] = (int) j7;
        int i11 = i4 + 6;
        long j8 = (j7 >>> 32) + (iArr[i2 + 6] & 4294967295L) + (iArr2[i3 + 6] & 4294967295L) + (iArr3[i11] & 4294967295L);
        iArr3[i11] = (int) j8;
        return (int) (j8 >>> 32);
    }

    public static int addBothTo(int[] iArr, int[] iArr2, int[] iArr3) {
        long j2 = (iArr[0] & 4294967295L) + (iArr2[0] & 4294967295L) + (iArr3[0] & 4294967295L) + 0;
        iArr3[0] = (int) j2;
        long j3 = (j2 >>> 32) + (iArr[1] & 4294967295L) + (iArr2[1] & 4294967295L) + (iArr3[1] & 4294967295L);
        iArr3[1] = (int) j3;
        long j4 = (j3 >>> 32) + (iArr[2] & 4294967295L) + (iArr2[2] & 4294967295L) + (iArr3[2] & 4294967295L);
        iArr3[2] = (int) j4;
        long j5 = (j4 >>> 32) + (iArr[3] & 4294967295L) + (iArr2[3] & 4294967295L) + (iArr3[3] & 4294967295L);
        iArr3[3] = (int) j5;
        long j6 = (j5 >>> 32) + (iArr[4] & 4294967295L) + (iArr2[4] & 4294967295L) + (iArr3[4] & 4294967295L);
        iArr3[4] = (int) j6;
        long j7 = (j6 >>> 32) + (iArr[5] & 4294967295L) + (iArr2[5] & 4294967295L) + (iArr3[5] & 4294967295L);
        iArr3[5] = (int) j7;
        long j8 = (j7 >>> 32) + (iArr[6] & 4294967295L) + (iArr2[6] & 4294967295L) + (iArr3[6] & 4294967295L);
        iArr3[6] = (int) j8;
        return (int) (j8 >>> 32);
    }

    public static int addTo(int[] iArr, int i2, int[] iArr2, int i3, int i4) {
        int i5 = i3 + 0;
        long j2 = (i4 & 4294967295L) + (iArr[i2 + 0] & 4294967295L) + (iArr2[i5] & 4294967295L);
        iArr2[i5] = (int) j2;
        int i6 = i3 + 1;
        long j3 = (j2 >>> 32) + (iArr[i2 + 1] & 4294967295L) + (iArr2[i6] & 4294967295L);
        iArr2[i6] = (int) j3;
        int i7 = i3 + 2;
        long j4 = (j3 >>> 32) + (iArr[i2 + 2] & 4294967295L) + (iArr2[i7] & 4294967295L);
        iArr2[i7] = (int) j4;
        int i8 = i3 + 3;
        long j5 = (j4 >>> 32) + (iArr[i2 + 3] & 4294967295L) + (iArr2[i8] & 4294967295L);
        iArr2[i8] = (int) j5;
        int i9 = i3 + 4;
        long j6 = (j5 >>> 32) + (iArr[i2 + 4] & 4294967295L) + (iArr2[i9] & 4294967295L);
        iArr2[i9] = (int) j6;
        int i10 = i3 + 5;
        long j7 = (j6 >>> 32) + (iArr[i2 + 5] & 4294967295L) + (iArr2[i10] & 4294967295L);
        iArr2[i10] = (int) j7;
        int i11 = i3 + 6;
        long j8 = (j7 >>> 32) + (iArr[i2 + 6] & 4294967295L) + (4294967295L & iArr2[i11]);
        iArr2[i11] = (int) j8;
        return (int) (j8 >>> 32);
    }

    public static int addTo(int[] iArr, int[] iArr2) {
        long j2 = (iArr[0] & 4294967295L) + (iArr2[0] & 4294967295L) + 0;
        iArr2[0] = (int) j2;
        long j3 = (j2 >>> 32) + (iArr[1] & 4294967295L) + (iArr2[1] & 4294967295L);
        iArr2[1] = (int) j3;
        long j4 = (j3 >>> 32) + (iArr[2] & 4294967295L) + (iArr2[2] & 4294967295L);
        iArr2[2] = (int) j4;
        long j5 = (j4 >>> 32) + (iArr[3] & 4294967295L) + (iArr2[3] & 4294967295L);
        iArr2[3] = (int) j5;
        long j6 = (j5 >>> 32) + (iArr[4] & 4294967295L) + (iArr2[4] & 4294967295L);
        iArr2[4] = (int) j6;
        long j7 = (j6 >>> 32) + (iArr[5] & 4294967295L) + (iArr2[5] & 4294967295L);
        iArr2[5] = (int) j7;
        long j8 = (j7 >>> 32) + (iArr[6] & 4294967295L) + (4294967295L & iArr2[6]);
        iArr2[6] = (int) j8;
        return (int) (j8 >>> 32);
    }

    public static int addToEachOther(int[] iArr, int i2, int[] iArr2, int i3) {
        int i4 = i2 + 0;
        int i5 = i3 + 0;
        long j2 = (iArr[i4] & 4294967295L) + (iArr2[i5] & 4294967295L) + 0;
        int i6 = (int) j2;
        iArr[i4] = i6;
        iArr2[i5] = i6;
        int i7 = i2 + 1;
        int i8 = i3 + 1;
        long j3 = (j2 >>> 32) + (iArr[i7] & 4294967295L) + (iArr2[i8] & 4294967295L);
        int i9 = (int) j3;
        iArr[i7] = i9;
        iArr2[i8] = i9;
        int i10 = i2 + 2;
        int i11 = i3 + 2;
        long j4 = (j3 >>> 32) + (iArr[i10] & 4294967295L) + (iArr2[i11] & 4294967295L);
        int i12 = (int) j4;
        iArr[i10] = i12;
        iArr2[i11] = i12;
        int i13 = i2 + 3;
        int i14 = i3 + 3;
        long j5 = (j4 >>> 32) + (iArr[i13] & 4294967295L) + (iArr2[i14] & 4294967295L);
        int i15 = (int) j5;
        iArr[i13] = i15;
        iArr2[i14] = i15;
        int i16 = i2 + 4;
        int i17 = i3 + 4;
        long j6 = (j5 >>> 32) + (iArr[i16] & 4294967295L) + (iArr2[i17] & 4294967295L);
        int i18 = (int) j6;
        iArr[i16] = i18;
        iArr2[i17] = i18;
        int i19 = i2 + 5;
        int i20 = i3 + 5;
        long j7 = (j6 >>> 32) + (iArr[i19] & 4294967295L) + (iArr2[i20] & 4294967295L);
        int i21 = (int) j7;
        iArr[i19] = i21;
        iArr2[i20] = i21;
        int i22 = i2 + 6;
        int i23 = i3 + 6;
        long j8 = (j7 >>> 32) + (iArr[i22] & 4294967295L) + (4294967295L & iArr2[i23]);
        int i24 = (int) j8;
        iArr[i22] = i24;
        iArr2[i23] = i24;
        return (int) (j8 >>> 32);
    }

    public static void copy(int[] iArr, int i2, int[] iArr2, int i3) {
        iArr2[i3 + 0] = iArr[i2 + 0];
        iArr2[i3 + 1] = iArr[i2 + 1];
        iArr2[i3 + 2] = iArr[i2 + 2];
        iArr2[i3 + 3] = iArr[i2 + 3];
        iArr2[i3 + 4] = iArr[i2 + 4];
        iArr2[i3 + 5] = iArr[i2 + 5];
        iArr2[i3 + 6] = iArr[i2 + 6];
    }

    public static void copy(int[] iArr, int[] iArr2) {
        iArr2[0] = iArr[0];
        iArr2[1] = iArr[1];
        iArr2[2] = iArr[2];
        iArr2[3] = iArr[3];
        iArr2[4] = iArr[4];
        iArr2[5] = iArr[5];
        iArr2[6] = iArr[6];
    }

    public static int[] create() {
        return new int[7];
    }

    public static int[] createExt() {
        return new int[14];
    }

    public static boolean diff(int[] iArr, int i2, int[] iArr2, int i3, int[] iArr3, int i4) {
        boolean gte = gte(iArr, i2, iArr2, i3);
        if (gte) {
            sub(iArr, i2, iArr2, i3, iArr3, i4);
        } else {
            sub(iArr2, i3, iArr, i2, iArr3, i4);
        }
        return gte;
    }

    public static boolean eq(int[] iArr, int[] iArr2) {
        for (int i2 = 6; i2 >= 0; i2--) {
            if (iArr[i2] != iArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > 224) {
            throw new IllegalArgumentException();
        }
        int[] create = create();
        for (int i2 = 0; i2 < 7; i2++) {
            create[i2] = bigInteger.intValue();
            bigInteger = bigInteger.shiftRight(32);
        }
        return create;
    }

    public static int getBit(int[] iArr, int i2) {
        int i3;
        if (i2 == 0) {
            i3 = iArr[0];
        } else {
            int i4 = i2 >> 5;
            if (i4 < 0 || i4 >= 7) {
                return 0;
            }
            i3 = iArr[i4] >>> (i2 & 31);
        }
        return i3 & 1;
    }

    public static boolean gte(int[] iArr, int i2, int[] iArr2, int i3) {
        for (int i4 = 6; i4 >= 0; i4--) {
            int i5 = iArr[i2 + i4] ^ Integer.MIN_VALUE;
            int i6 = Integer.MIN_VALUE ^ iArr2[i3 + i4];
            if (i5 < i6) {
                return false;
            }
            if (i5 > i6) {
                return true;
            }
        }
        return true;
    }

    public static boolean gte(int[] iArr, int[] iArr2) {
        for (int i2 = 6; i2 >= 0; i2--) {
            int i3 = iArr[i2] ^ Integer.MIN_VALUE;
            int i4 = Integer.MIN_VALUE ^ iArr2[i2];
            if (i3 < i4) {
                return false;
            }
            if (i3 > i4) {
                return true;
            }
        }
        return true;
    }

    public static boolean isOne(int[] iArr) {
        if (iArr[0] != 1) {
            return false;
        }
        for (int i2 = 1; i2 < 7; i2++) {
            if (iArr[i2] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isZero(int[] iArr) {
        for (int i2 = 0; i2 < 7; i2++) {
            if (iArr[i2] != 0) {
                return false;
            }
        }
        return true;
    }

    public static void mul(int[] iArr, int i2, int[] iArr2, int i3, int[] iArr3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        long j2 = iArr2[i3 + 0] & 4294967295L;
        long j3 = iArr2[i3 + 1] & 4294967295L;
        long j4 = iArr2[i3 + 2] & 4294967295L;
        long j5 = iArr2[i3 + 3] & 4294967295L;
        long j6 = iArr2[i3 + 4] & 4294967295L;
        long j7 = iArr2[i3 + 5] & 4294967295L;
        long j8 = iArr[i2 + 0] & 4294967295L;
        long j9 = (j8 * j2) + 0;
        iArr3[i4 + 0] = (int) j9;
        long j10 = (j9 >>> 32) + (j8 * j3);
        iArr3[i4 + 1] = (int) j10;
        long j11 = (j10 >>> 32) + (j8 * j4);
        iArr3[i4 + 2] = (int) j11;
        long j12 = (j11 >>> 32) + (j8 * j5);
        iArr3[i4 + 3] = (int) j12;
        long j13 = (j12 >>> 32) + (j8 * j6);
        iArr3[i4 + 4] = (int) j13;
        long j14 = (j13 >>> 32) + (j8 * j7);
        iArr3[i4 + 5] = (int) j14;
        long j15 = iArr2[i3 + 6] & 4294967295L;
        long j16 = (j14 >>> 32) + (j8 * j15);
        iArr3[i4 + 6] = (int) j16;
        iArr3[i4 + 7] = (int) (j16 >>> 32);
        int i12 = 1;
        int i13 = i4;
        int i14 = 1;
        while (i14 < 7) {
            i13 += i12;
            long j17 = iArr[i2 + i14] & 4294967295L;
            long j18 = (j17 * j2) + (iArr3[i5] & 4294967295L) + 0;
            iArr3[i13 + 0] = (int) j18;
            long j19 = j15;
            long j20 = (j18 >>> 32) + (j17 * j3) + (iArr3[i6] & 4294967295L);
            iArr3[i13 + 1] = (int) j20;
            long j21 = j4;
            long j22 = (j20 >>> 32) + (j17 * j4) + (iArr3[i7] & 4294967295L);
            iArr3[i13 + 2] = (int) j22;
            long j23 = (j22 >>> 32) + (j17 * j5) + (iArr3[i8] & 4294967295L);
            iArr3[i13 + 3] = (int) j23;
            long j24 = (j23 >>> 32) + (j17 * j6) + (iArr3[i9] & 4294967295L);
            iArr3[i13 + 4] = (int) j24;
            long j25 = (j24 >>> 32) + (j17 * j7) + (iArr3[i10] & 4294967295L);
            iArr3[i13 + 5] = (int) j25;
            long j26 = (j25 >>> 32) + (j17 * j19) + (iArr3[i11] & 4294967295L);
            iArr3[i13 + 6] = (int) j26;
            iArr3[i13 + 7] = (int) (j26 >>> 32);
            i14++;
            j4 = j21;
            j15 = j19;
            j5 = j5;
            i12 = 1;
        }
    }

    public static void mul(int[] iArr, int[] iArr2, int[] iArr3) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        long j2 = iArr2[0] & 4294967295L;
        long j3 = iArr2[1] & 4294967295L;
        long j4 = iArr2[2] & 4294967295L;
        long j5 = iArr2[3] & 4294967295L;
        long j6 = iArr2[4] & 4294967295L;
        long j7 = iArr2[5] & 4294967295L;
        long j8 = iArr2[6] & 4294967295L;
        long j9 = iArr[0] & 4294967295L;
        long j10 = (j9 * j2) + 0;
        iArr3[0] = (int) j10;
        long j11 = (j10 >>> 32) + (j9 * j3);
        iArr3[1] = (int) j11;
        long j12 = (j11 >>> 32) + (j9 * j4);
        iArr3[2] = (int) j12;
        long j13 = (j12 >>> 32) + (j9 * j5);
        iArr3[3] = (int) j13;
        long j14 = (j13 >>> 32) + (j9 * j6);
        iArr3[4] = (int) j14;
        long j15 = (j14 >>> 32) + (j9 * j7);
        iArr3[5] = (int) j15;
        long j16 = (j15 >>> 32) + (j9 * j8);
        iArr3[6] = (int) j16;
        iArr3[7] = (int) (j16 >>> 32);
        int i8 = 1;
        for (int i9 = 7; i8 < i9; i9 = 7) {
            long j17 = iArr[i8] & 4294967295L;
            long j18 = (j17 * j2) + (iArr3[i2] & 4294967295L) + 0;
            iArr3[i8 + 0] = (int) j18;
            int i10 = i8 + 1;
            long j19 = j3;
            long j20 = (j18 >>> 32) + (j17 * j3) + (iArr3[i10] & 4294967295L);
            iArr3[i10] = (int) j20;
            long j21 = j7;
            long j22 = (j20 >>> 32) + (j17 * j4) + (iArr3[i3] & 4294967295L);
            iArr3[i8 + 2] = (int) j22;
            long j23 = (j22 >>> 32) + (j17 * j5) + (iArr3[i4] & 4294967295L);
            iArr3[i8 + 3] = (int) j23;
            long j24 = (j23 >>> 32) + (j17 * j6) + (iArr3[i5] & 4294967295L);
            iArr3[i8 + 4] = (int) j24;
            long j25 = (j24 >>> 32) + (j17 * j21) + (iArr3[i6] & 4294967295L);
            iArr3[i8 + 5] = (int) j25;
            long j26 = (j25 >>> 32) + (j17 * j8) + (iArr3[i7] & 4294967295L);
            iArr3[i8 + 6] = (int) j26;
            iArr3[i8 + 7] = (int) (j26 >>> 32);
            i8 = i10;
            j2 = j2;
            j3 = j19;
            j7 = j21;
        }
    }

    public static long mul33Add(int i2, int[] iArr, int i3, int[] iArr2, int i4, int[] iArr3, int i5) {
        long j2 = i2 & 4294967295L;
        long j3 = iArr[i3 + 0] & 4294967295L;
        long j4 = (j2 * j3) + (iArr2[i4 + 0] & 4294967295L) + 0;
        iArr3[i5 + 0] = (int) j4;
        long j5 = iArr[i3 + 1] & 4294967295L;
        long j6 = (j4 >>> 32) + (j2 * j5) + j3 + (iArr2[i4 + 1] & 4294967295L);
        iArr3[i5 + 1] = (int) j6;
        long j7 = j6 >>> 32;
        long j8 = iArr[i3 + 2] & 4294967295L;
        long j9 = j7 + (j2 * j8) + j5 + (iArr2[i4 + 2] & 4294967295L);
        iArr3[i5 + 2] = (int) j9;
        long j10 = iArr[i3 + 3] & 4294967295L;
        long j11 = (j9 >>> 32) + (j2 * j10) + j8 + (iArr2[i4 + 3] & 4294967295L);
        iArr3[i5 + 3] = (int) j11;
        long j12 = iArr[i3 + 4] & 4294967295L;
        long j13 = (j11 >>> 32) + (j2 * j12) + j10 + (iArr2[i4 + 4] & 4294967295L);
        iArr3[i5 + 4] = (int) j13;
        long j14 = iArr[i3 + 5] & 4294967295L;
        long j15 = (j13 >>> 32) + (j2 * j14) + j12 + (iArr2[i4 + 5] & 4294967295L);
        iArr3[i5 + 5] = (int) j15;
        long j16 = iArr[i3 + 6] & 4294967295L;
        long j17 = (j15 >>> 32) + (j2 * j16) + j14 + (4294967295L & iArr2[i4 + 6]);
        iArr3[i5 + 6] = (int) j17;
        return (j17 >>> 32) + j16;
    }

    public static int mul33DWordAdd(int i2, long j2, int[] iArr, int i3) {
        long j3 = i2 & 4294967295L;
        long j4 = j2 & 4294967295L;
        int i4 = i3 + 0;
        long j5 = (j3 * j4) + (iArr[i4] & 4294967295L) + 0;
        iArr[i4] = (int) j5;
        long j6 = j2 >>> 32;
        long j7 = (j3 * j6) + j4;
        int i5 = i3 + 1;
        long j8 = (j5 >>> 32) + j7 + (iArr[i5] & 4294967295L);
        iArr[i5] = (int) j8;
        int i6 = i3 + 2;
        long j9 = (j8 >>> 32) + j6 + (iArr[i6] & 4294967295L);
        iArr[i6] = (int) j9;
        int i7 = i3 + 3;
        long j10 = (j9 >>> 32) + (4294967295L & iArr[i7]);
        iArr[i7] = (int) j10;
        if ((j10 >>> 32) == 0) {
            return 0;
        }
        return Nat.incAt(7, iArr, i3, 4);
    }

    public static int mul33WordAdd(int i2, int i3, int[] iArr, int i4) {
        long j2 = i3 & 4294967295L;
        int i5 = i4 + 0;
        long j3 = ((i2 & 4294967295L) * j2) + (iArr[i5] & 4294967295L) + 0;
        iArr[i5] = (int) j3;
        int i6 = i4 + 1;
        long j4 = (j3 >>> 32) + j2 + (iArr[i6] & 4294967295L);
        iArr[i6] = (int) j4;
        long j5 = j4 >>> 32;
        int i7 = i4 + 2;
        long j6 = j5 + (iArr[i7] & 4294967295L);
        iArr[i7] = (int) j6;
        if ((j6 >>> 32) == 0) {
            return 0;
        }
        return Nat.incAt(7, iArr, i4, 3);
    }

    public static int mulAddTo(int[] iArr, int i2, int[] iArr2, int i3, int[] iArr3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        long j2 = iArr2[i3 + 0] & 4294967295L;
        long j3 = iArr2[i3 + 1] & 4294967295L;
        long j4 = iArr2[i3 + 2] & 4294967295L;
        long j5 = iArr2[i3 + 3] & 4294967295L;
        long j6 = iArr2[i3 + 4] & 4294967295L;
        long j7 = iArr2[i3 + 5] & 4294967295L;
        long j8 = iArr2[i3 + 6] & 4294967295L;
        long j9 = 0;
        int i12 = 0;
        int i13 = i4;
        while (i12 < 7) {
            int i14 = i12;
            long j10 = iArr[i2 + i12] & 4294967295L;
            long j11 = j2;
            long j12 = (j10 * j2) + (iArr3[i5] & 4294967295L) + 0;
            long j13 = j8;
            iArr3[i13 + 0] = (int) j12;
            int i15 = i13 + 1;
            long j14 = (j12 >>> 32) + (j10 * j3) + (iArr3[i15] & 4294967295L);
            iArr3[i15] = (int) j14;
            long j15 = (j14 >>> 32) + (j10 * j4) + (iArr3[i6] & 4294967295L);
            iArr3[i13 + 2] = (int) j15;
            long j16 = (j15 >>> 32) + (j10 * j5) + (iArr3[i7] & 4294967295L);
            iArr3[i13 + 3] = (int) j16;
            long j17 = (j16 >>> 32) + (j10 * j6) + (iArr3[i8] & 4294967295L);
            iArr3[i13 + 4] = (int) j17;
            long j18 = (j17 >>> 32) + (j10 * j7) + (iArr3[i9] & 4294967295L);
            iArr3[i13 + 5] = (int) j18;
            long j19 = (j18 >>> 32) + (j10 * j13) + (iArr3[i10] & 4294967295L);
            iArr3[i13 + 6] = (int) j19;
            long j20 = (j19 >>> 32) + (iArr3[i11] & 4294967295L) + j9;
            iArr3[i13 + 7] = (int) j20;
            j9 = j20 >>> 32;
            i12 = i14 + 1;
            i13 = i15;
            j8 = j13;
            j2 = j11;
            j3 = j3;
        }
        return (int) j9;
    }

    public static int mulAddTo(int[] iArr, int[] iArr2, int[] iArr3) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        long j2 = iArr2[0] & 4294967295L;
        long j3 = iArr2[1] & 4294967295L;
        long j4 = iArr2[2] & 4294967295L;
        long j5 = iArr2[3] & 4294967295L;
        long j6 = iArr2[4] & 4294967295L;
        long j7 = iArr2[5] & 4294967295L;
        long j8 = iArr2[6] & 4294967295L;
        long j9 = 0;
        int i9 = 0;
        while (i9 < 7) {
            long j10 = j8;
            long j11 = iArr[i9] & 4294967295L;
            long j12 = j7;
            long j13 = (j11 * j2) + (iArr3[i2] & 4294967295L) + 0;
            iArr3[i9 + 0] = (int) j13;
            int i10 = i9 + 1;
            long j14 = j3;
            long j15 = (j13 >>> 32) + (j11 * j3) + (iArr3[i10] & 4294967295L);
            iArr3[i10] = (int) j15;
            long j16 = (j15 >>> 32) + (j11 * j4) + (iArr3[i3] & 4294967295L);
            iArr3[i9 + 2] = (int) j16;
            long j17 = (j16 >>> 32) + (j11 * j5) + (iArr3[i4] & 4294967295L);
            iArr3[i9 + 3] = (int) j17;
            long j18 = (j17 >>> 32) + (j11 * j6) + (iArr3[i5] & 4294967295L);
            iArr3[i9 + 4] = (int) j18;
            long j19 = (j18 >>> 32) + (j11 * j12) + (iArr3[i6] & 4294967295L);
            iArr3[i9 + 5] = (int) j19;
            long j20 = (j19 >>> 32) + (j11 * j10) + (iArr3[i7] & 4294967295L);
            iArr3[i9 + 6] = (int) j20;
            long j21 = (j20 >>> 32) + (iArr3[i8] & 4294967295L) + j9;
            iArr3[i9 + 7] = (int) j21;
            j9 = j21 >>> 32;
            i9 = i10;
            j8 = j10;
            j7 = j12;
            j3 = j14;
        }
        return (int) j9;
    }

    public static int mulByWord(int i2, int[] iArr) {
        long j2 = i2 & 4294967295L;
        long j3 = ((iArr[0] & 4294967295L) * j2) + 0;
        iArr[0] = (int) j3;
        long j4 = (j3 >>> 32) + ((iArr[1] & 4294967295L) * j2);
        iArr[1] = (int) j4;
        long j5 = (j4 >>> 32) + ((iArr[2] & 4294967295L) * j2);
        iArr[2] = (int) j5;
        long j6 = (j5 >>> 32) + ((iArr[3] & 4294967295L) * j2);
        iArr[3] = (int) j6;
        long j7 = (j6 >>> 32) + ((iArr[4] & 4294967295L) * j2);
        iArr[4] = (int) j7;
        long j8 = (j7 >>> 32) + ((iArr[5] & 4294967295L) * j2);
        iArr[5] = (int) j8;
        long j9 = (j8 >>> 32) + (j2 * (4294967295L & iArr[6]));
        iArr[6] = (int) j9;
        return (int) (j9 >>> 32);
    }

    public static int mulByWordAddTo(int i2, int[] iArr, int[] iArr2) {
        long j2 = i2 & 4294967295L;
        long j3 = ((iArr2[0] & 4294967295L) * j2) + (iArr[0] & 4294967295L) + 0;
        iArr2[0] = (int) j3;
        long j4 = (j3 >>> 32) + ((iArr2[1] & 4294967295L) * j2) + (iArr[1] & 4294967295L);
        iArr2[1] = (int) j4;
        long j5 = (j4 >>> 32) + ((iArr2[2] & 4294967295L) * j2) + (iArr[2] & 4294967295L);
        iArr2[2] = (int) j5;
        long j6 = (j5 >>> 32) + ((iArr2[3] & 4294967295L) * j2) + (iArr[3] & 4294967295L);
        iArr2[3] = (int) j6;
        long j7 = (j6 >>> 32) + ((iArr2[4] & 4294967295L) * j2) + (iArr[4] & 4294967295L);
        iArr2[4] = (int) j7;
        long j8 = (j7 >>> 32) + ((iArr2[5] & 4294967295L) * j2) + (iArr[5] & 4294967295L);
        iArr2[5] = (int) j8;
        long j9 = (j8 >>> 32) + (j2 * (iArr2[6] & 4294967295L)) + (4294967295L & iArr[6]);
        iArr2[6] = (int) j9;
        return (int) (j9 >>> 32);
    }

    public static int mulWord(int i2, int[] iArr, int[] iArr2, int i3) {
        long j2 = i2 & 4294967295L;
        long j3 = 0;
        int i4 = 0;
        do {
            long j4 = j3 + ((iArr[i4] & 4294967295L) * j2);
            iArr2[i3 + i4] = (int) j4;
            j3 = j4 >>> 32;
            i4++;
        } while (i4 < 7);
        return (int) j3;
    }

    public static int mulWordAddTo(int i2, int[] iArr, int i3, int[] iArr2, int i4) {
        long j2 = i2 & 4294967295L;
        int i5 = i4 + 0;
        long j3 = ((iArr[i3 + 0] & 4294967295L) * j2) + (iArr2[i5] & 4294967295L) + 0;
        iArr2[i5] = (int) j3;
        int i6 = i4 + 1;
        long j4 = (j3 >>> 32) + ((iArr[i3 + 1] & 4294967295L) * j2) + (iArr2[i6] & 4294967295L);
        iArr2[i6] = (int) j4;
        int i7 = i4 + 2;
        long j5 = (j4 >>> 32) + ((iArr[i3 + 2] & 4294967295L) * j2) + (iArr2[i7] & 4294967295L);
        iArr2[i7] = (int) j5;
        int i8 = i4 + 3;
        long j6 = (j5 >>> 32) + ((iArr[i3 + 3] & 4294967295L) * j2) + (iArr2[i8] & 4294967295L);
        iArr2[i8] = (int) j6;
        int i9 = i4 + 4;
        long j7 = (j6 >>> 32) + ((iArr[i3 + 4] & 4294967295L) * j2) + (iArr2[i9] & 4294967295L);
        iArr2[i9] = (int) j7;
        int i10 = i4 + 5;
        long j8 = (j7 >>> 32) + ((iArr[i3 + 5] & 4294967295L) * j2) + (iArr2[i10] & 4294967295L);
        iArr2[i10] = (int) j8;
        int i11 = i4 + 6;
        long j9 = (j8 >>> 32) + (j2 * (iArr[i3 + 6] & 4294967295L)) + (iArr2[i11] & 4294967295L);
        iArr2[i11] = (int) j9;
        return (int) (j9 >>> 32);
    }

    public static int mulWordDwordAdd(int i2, long j2, int[] iArr, int i3) {
        long j3 = i2 & 4294967295L;
        int i4 = i3 + 0;
        long j4 = ((j2 & 4294967295L) * j3) + (iArr[i4] & 4294967295L) + 0;
        iArr[i4] = (int) j4;
        long j5 = j3 * (j2 >>> 32);
        int i5 = i3 + 1;
        long j6 = (j4 >>> 32) + j5 + (iArr[i5] & 4294967295L);
        iArr[i5] = (int) j6;
        int i6 = i3 + 2;
        long j7 = (j6 >>> 32) + (iArr[i6] & 4294967295L);
        iArr[i6] = (int) j7;
        if ((j7 >>> 32) == 0) {
            return 0;
        }
        return Nat.incAt(7, iArr, i3, 3);
    }

    public static void square(int[] iArr, int i2, int[] iArr2, int i3) {
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        long j2 = iArr[i2 + 0] & 4294967295L;
        int i15 = 0;
        int i16 = 14;
        int i17 = 6;
        while (true) {
            int i18 = i17 - 1;
            long j3 = iArr[i2 + i17] & 4294967295L;
            long j4 = j3 * j3;
            int i19 = i16 - 1;
            iArr2[i3 + i19] = (i15 << 31) | ((int) (j4 >>> 33));
            i16 = i19 - 1;
            iArr2[i3 + i16] = (int) (j4 >>> 1);
            i15 = (int) j4;
            if (i18 <= 0) {
                long j5 = j2 * j2;
                iArr2[i3 + 0] = (int) j5;
                long j6 = iArr[i2 + 1] & 4294967295L;
                long j7 = (((i15 << 31) & 4294967295L) | (j5 >>> 33)) + (j6 * j2);
                int i20 = (int) j7;
                iArr2[i3 + 1] = (i20 << 1) | (((int) (j5 >>> 32)) & 1);
                int i21 = i20 >>> 31;
                long j8 = (iArr2[i4] & 4294967295L) + (j7 >>> 32);
                long j9 = iArr[i2 + 2] & 4294967295L;
                long j10 = j8 + (j9 * j2);
                int i22 = (int) j10;
                iArr2[i3 + 2] = (i22 << 1) | i21;
                int i23 = i22 >>> 31;
                long j11 = (iArr2[i5] & 4294967295L) + (j10 >>> 32) + (j9 * j6);
                long j12 = (iArr2[i6] & 4294967295L) + (j11 >>> 32);
                long j13 = iArr[i2 + 3] & 4294967295L;
                long j14 = (iArr2[i7] & 4294967295L) + (j12 >>> 32);
                long j15 = (iArr2[i8] & 4294967295L) + (j14 >>> 32);
                long j16 = (j11 & 4294967295L) + (j13 * j2);
                int i24 = (int) j16;
                iArr2[i3 + 3] = (i24 << 1) | i23;
                long j17 = (j12 & 4294967295L) + (j16 >>> 32) + (j13 * j6);
                long j18 = (j14 & 4294967295L) + (j17 >>> 32) + (j13 * j9);
                long j19 = j15 + (j18 >>> 32);
                long j20 = j18 & 4294967295L;
                long j21 = iArr[i2 + 4] & 4294967295L;
                long j22 = (iArr2[i9] & 4294967295L) + (j19 >>> 32);
                long j23 = j19 & 4294967295L;
                long j24 = (j17 & 4294967295L) + (j21 * j2);
                int i25 = (int) j24;
                iArr2[i3 + 4] = (i24 >>> 31) | (i25 << 1);
                int i26 = i25 >>> 31;
                long j25 = j20 + (j24 >>> 32) + (j21 * j6);
                long j26 = j23 + (j25 >>> 32) + (j21 * j9);
                long j27 = (j22 & 4294967295L) + (j26 >>> 32) + (j21 * j13);
                long j28 = (iArr2[i10] & 4294967295L) + (j22 >>> 32) + (j27 >>> 32);
                long j29 = j27 & 4294967295L;
                long j30 = iArr[i2 + 5] & 4294967295L;
                long j31 = (iArr2[i11] & 4294967295L) + (j28 >>> 32);
                long j32 = (j25 & 4294967295L) + (j30 * j2);
                int i27 = (int) j32;
                iArr2[i3 + 5] = i26 | (i27 << 1);
                int i28 = i27 >>> 31;
                long j33 = (j26 & 4294967295L) + (j32 >>> 32) + (j30 * j6);
                long j34 = j29 + (j33 >>> 32) + (j30 * j9);
                long j35 = (j28 & 4294967295L) + (j34 >>> 32) + (j30 * j13);
                long j36 = (j31 & 4294967295L) + (j35 >>> 32) + (j30 * j21);
                long j37 = (iArr2[i12] & 4294967295L) + (j31 >>> 32) + (j36 >>> 32);
                long j38 = j36 & 4294967295L;
                long j39 = iArr[i2 + 6] & 4294967295L;
                long j40 = (iArr2[i13] & 4294967295L) + (j37 >>> 32);
                long j41 = (j33 & 4294967295L) + (j2 * j39);
                int i29 = (int) j41;
                iArr2[i3 + 6] = (i29 << 1) | i28;
                long j42 = (j34 & 4294967295L) + (j41 >>> 32) + (j39 * j6);
                long j43 = (j35 & 4294967295L) + (j42 >>> 32) + (j39 * j9);
                long j44 = j38 + (j43 >>> 32) + (j39 * j13);
                long j45 = (j37 & 4294967295L) + (j44 >>> 32) + (j39 * j21);
                long j46 = (j40 & 4294967295L) + (j45 >>> 32) + (j39 * j30);
                long j47 = (iArr2[i14] & 4294967295L) + (j40 >>> 32) + (j46 >>> 32);
                int i30 = (int) j42;
                iArr2[i3 + 7] = (i29 >>> 31) | (i30 << 1);
                int i31 = (int) j43;
                iArr2[i3 + 8] = (i30 >>> 31) | (i31 << 1);
                int i32 = i31 >>> 31;
                int i33 = (int) j44;
                iArr2[i3 + 9] = i32 | (i33 << 1);
                int i34 = i33 >>> 31;
                int i35 = (int) j45;
                iArr2[i3 + 10] = i34 | (i35 << 1);
                int i36 = i35 >>> 31;
                int i37 = (int) j46;
                iArr2[i3 + 11] = i36 | (i37 << 1);
                int i38 = i37 >>> 31;
                int i39 = (int) j47;
                iArr2[i3 + 12] = i38 | (i39 << 1);
                int i40 = i39 >>> 31;
                int i41 = i3 + 13;
                iArr2[i41] = i40 | ((iArr2[i41] + ((int) (j47 >>> 32))) << 1);
                return;
            }
            i17 = i18;
        }
    }

    public static void square(int[] iArr, int[] iArr2) {
        long j2 = iArr[0] & 4294967295L;
        int i2 = 14;
        int i3 = 0;
        int i4 = 6;
        while (true) {
            int i5 = i4 - 1;
            long j3 = iArr[i4] & 4294967295L;
            long j4 = j3 * j3;
            int i6 = i2 - 1;
            iArr2[i6] = (i3 << 31) | ((int) (j4 >>> 33));
            i2 = i6 - 1;
            iArr2[i2] = (int) (j4 >>> 1);
            int i7 = (int) j4;
            if (i5 <= 0) {
                long j5 = j2 * j2;
                long j6 = ((i7 << 31) & 4294967295L) | (j5 >>> 33);
                iArr2[0] = (int) j5;
                long j7 = iArr[1] & 4294967295L;
                long j8 = j6 + (j7 * j2);
                int i8 = (int) j8;
                iArr2[1] = (i8 << 1) | (((int) (j5 >>> 32)) & 1);
                long j9 = (iArr2[2] & 4294967295L) + (j8 >>> 32);
                long j10 = iArr[2] & 4294967295L;
                long j11 = j9 + (j10 * j2);
                int i9 = (int) j11;
                iArr2[2] = (i9 << 1) | (i8 >>> 31);
                long j12 = (iArr2[3] & 4294967295L) + (j11 >>> 32) + (j10 * j7);
                long j13 = (iArr2[4] & 4294967295L) + (j12 >>> 32);
                long j14 = iArr[3] & 4294967295L;
                long j15 = (iArr2[5] & 4294967295L) + (j13 >>> 32);
                long j16 = j13 & 4294967295L;
                long j17 = (iArr2[6] & 4294967295L) + (j15 >>> 32);
                long j18 = (j12 & 4294967295L) + (j14 * j2);
                int i10 = (int) j18;
                iArr2[3] = (i10 << 1) | (i9 >>> 31);
                long j19 = j16 + (j18 >>> 32) + (j14 * j7);
                long j20 = (j15 & 4294967295L) + (j19 >>> 32) + (j14 * j10);
                long j21 = j17 + (j20 >>> 32);
                long j22 = j20 & 4294967295L;
                long j23 = iArr[4] & 4294967295L;
                long j24 = (iArr2[7] & 4294967295L) + (j21 >>> 32);
                long j25 = j21 & 4294967295L;
                long j26 = (iArr2[8] & 4294967295L) + (j24 >>> 32);
                long j27 = (j19 & 4294967295L) + (j23 * j2);
                int i11 = (int) j27;
                iArr2[4] = (i10 >>> 31) | (i11 << 1);
                long j28 = j22 + (j27 >>> 32) + (j23 * j7);
                long j29 = j25 + (j28 >>> 32) + (j23 * j10);
                long j30 = (j24 & 4294967295L) + (j29 >>> 32) + (j23 * j14);
                long j31 = j26 + (j30 >>> 32);
                long j32 = iArr[5] & 4294967295L;
                long j33 = (iArr2[9] & 4294967295L) + (j31 >>> 32);
                long j34 = j31 & 4294967295L;
                long j35 = (iArr2[10] & 4294967295L) + (j33 >>> 32);
                long j36 = (j28 & 4294967295L) + (j32 * j2);
                int i12 = (int) j36;
                iArr2[5] = (i12 << 1) | (i11 >>> 31);
                long j37 = (j29 & 4294967295L) + (j36 >>> 32) + (j32 * j7);
                long j38 = (j30 & 4294967295L) + (j37 >>> 32) + (j32 * j10);
                long j39 = j34 + (j38 >>> 32) + (j32 * j14);
                long j40 = (j33 & 4294967295L) + (j39 >>> 32) + (j32 * j23);
                long j41 = j35 + (j40 >>> 32);
                long j42 = j40 & 4294967295L;
                long j43 = iArr[6] & 4294967295L;
                long j44 = (iArr2[11] & 4294967295L) + (j41 >>> 32);
                long j45 = (j37 & 4294967295L) + (j2 * j43);
                int i13 = (int) j45;
                iArr2[6] = (i12 >>> 31) | (i13 << 1);
                int i14 = i13 >>> 31;
                long j46 = (j38 & 4294967295L) + (j45 >>> 32) + (j7 * j43);
                long j47 = (j39 & 4294967295L) + (j46 >>> 32) + (j43 * j10);
                long j48 = j42 + (j47 >>> 32) + (j43 * j14);
                long j49 = (j41 & 4294967295L) + (j48 >>> 32) + (j43 * j23);
                long j50 = (j44 & 4294967295L) + (j49 >>> 32) + (j43 * j32);
                long j51 = (iArr2[12] & 4294967295L) + (j44 >>> 32) + (j50 >>> 32);
                int i15 = (int) j46;
                iArr2[7] = i14 | (i15 << 1);
                int i16 = (int) j47;
                iArr2[8] = (i15 >>> 31) | (i16 << 1);
                int i17 = i16 >>> 31;
                int i18 = (int) j48;
                iArr2[9] = i17 | (i18 << 1);
                int i19 = i18 >>> 31;
                int i20 = (int) j49;
                iArr2[10] = i19 | (i20 << 1);
                int i21 = i20 >>> 31;
                int i22 = (int) j50;
                iArr2[11] = i21 | (i22 << 1);
                int i23 = i22 >>> 31;
                int i24 = (int) j51;
                iArr2[12] = i23 | (i24 << 1);
                iArr2[13] = (i24 >>> 31) | ((iArr2[13] + ((int) (j51 >>> 32))) << 1);
                return;
            }
            i4 = i5;
            i3 = i7;
        }
    }

    public static int sub(int[] iArr, int i2, int[] iArr2, int i3, int[] iArr3, int i4) {
        long j2 = ((iArr[i2 + 0] & 4294967295L) - (iArr2[i3 + 0] & 4294967295L)) + 0;
        iArr3[i4 + 0] = (int) j2;
        long j3 = (j2 >> 32) + ((iArr[i2 + 1] & 4294967295L) - (iArr2[i3 + 1] & 4294967295L));
        iArr3[i4 + 1] = (int) j3;
        long j4 = (j3 >> 32) + ((iArr[i2 + 2] & 4294967295L) - (iArr2[i3 + 2] & 4294967295L));
        iArr3[i4 + 2] = (int) j4;
        long j5 = (j4 >> 32) + ((iArr[i2 + 3] & 4294967295L) - (iArr2[i3 + 3] & 4294967295L));
        iArr3[i4 + 3] = (int) j5;
        long j6 = (j5 >> 32) + ((iArr[i2 + 4] & 4294967295L) - (iArr2[i3 + 4] & 4294967295L));
        iArr3[i4 + 4] = (int) j6;
        long j7 = (j6 >> 32) + ((iArr[i2 + 5] & 4294967295L) - (iArr2[i3 + 5] & 4294967295L));
        iArr3[i4 + 5] = (int) j7;
        long j8 = (j7 >> 32) + ((iArr[i2 + 6] & 4294967295L) - (iArr2[i3 + 6] & 4294967295L));
        iArr3[i4 + 6] = (int) j8;
        return (int) (j8 >> 32);
    }

    public static int sub(int[] iArr, int[] iArr2, int[] iArr3) {
        long j2 = ((iArr[0] & 4294967295L) - (iArr2[0] & 4294967295L)) + 0;
        iArr3[0] = (int) j2;
        long j3 = (j2 >> 32) + ((iArr[1] & 4294967295L) - (iArr2[1] & 4294967295L));
        iArr3[1] = (int) j3;
        long j4 = (j3 >> 32) + ((iArr[2] & 4294967295L) - (iArr2[2] & 4294967295L));
        iArr3[2] = (int) j4;
        long j5 = (j4 >> 32) + ((iArr[3] & 4294967295L) - (iArr2[3] & 4294967295L));
        iArr3[3] = (int) j5;
        long j6 = (j5 >> 32) + ((iArr[4] & 4294967295L) - (iArr2[4] & 4294967295L));
        iArr3[4] = (int) j6;
        long j7 = (j6 >> 32) + ((iArr[5] & 4294967295L) - (iArr2[5] & 4294967295L));
        iArr3[5] = (int) j7;
        long j8 = (j7 >> 32) + ((iArr[6] & 4294967295L) - (iArr2[6] & 4294967295L));
        iArr3[6] = (int) j8;
        return (int) (j8 >> 32);
    }

    public static int subBothFrom(int[] iArr, int[] iArr2, int[] iArr3) {
        long j2 = (((iArr3[0] & 4294967295L) - (iArr[0] & 4294967295L)) - (iArr2[0] & 4294967295L)) + 0;
        iArr3[0] = (int) j2;
        long j3 = (j2 >> 32) + (((iArr3[1] & 4294967295L) - (iArr[1] & 4294967295L)) - (iArr2[1] & 4294967295L));
        iArr3[1] = (int) j3;
        long j4 = (j3 >> 32) + (((iArr3[2] & 4294967295L) - (iArr[2] & 4294967295L)) - (iArr2[2] & 4294967295L));
        iArr3[2] = (int) j4;
        long j5 = (j4 >> 32) + (((iArr3[3] & 4294967295L) - (iArr[3] & 4294967295L)) - (iArr2[3] & 4294967295L));
        iArr3[3] = (int) j5;
        long j6 = (j5 >> 32) + (((iArr3[4] & 4294967295L) - (iArr[4] & 4294967295L)) - (iArr2[4] & 4294967295L));
        iArr3[4] = (int) j6;
        long j7 = (j6 >> 32) + (((iArr3[5] & 4294967295L) - (iArr[5] & 4294967295L)) - (iArr2[5] & 4294967295L));
        iArr3[5] = (int) j7;
        long j8 = (j7 >> 32) + (((iArr3[6] & 4294967295L) - (iArr[6] & 4294967295L)) - (iArr2[6] & 4294967295L));
        iArr3[6] = (int) j8;
        return (int) (j8 >> 32);
    }

    public static int subFrom(int[] iArr, int i2, int[] iArr2, int i3) {
        int i4 = i3 + 0;
        long j2 = ((iArr2[i4] & 4294967295L) - (iArr[i2 + 0] & 4294967295L)) + 0;
        iArr2[i4] = (int) j2;
        int i5 = i3 + 1;
        long j3 = (j2 >> 32) + ((iArr2[i5] & 4294967295L) - (iArr[i2 + 1] & 4294967295L));
        iArr2[i5] = (int) j3;
        int i6 = i3 + 2;
        long j4 = (j3 >> 32) + ((iArr2[i6] & 4294967295L) - (iArr[i2 + 2] & 4294967295L));
        iArr2[i6] = (int) j4;
        int i7 = i3 + 3;
        long j5 = (j4 >> 32) + ((iArr2[i7] & 4294967295L) - (iArr[i2 + 3] & 4294967295L));
        iArr2[i7] = (int) j5;
        int i8 = i3 + 4;
        long j6 = (j5 >> 32) + ((iArr2[i8] & 4294967295L) - (iArr[i2 + 4] & 4294967295L));
        iArr2[i8] = (int) j6;
        int i9 = i3 + 5;
        long j7 = (j6 >> 32) + ((iArr2[i9] & 4294967295L) - (iArr[i2 + 5] & 4294967295L));
        iArr2[i9] = (int) j7;
        int i10 = i3 + 6;
        long j8 = (j7 >> 32) + ((iArr2[i10] & 4294967295L) - (iArr[i2 + 6] & 4294967295L));
        iArr2[i10] = (int) j8;
        return (int) (j8 >> 32);
    }

    public static int subFrom(int[] iArr, int[] iArr2) {
        long j2 = ((iArr2[0] & 4294967295L) - (iArr[0] & 4294967295L)) + 0;
        iArr2[0] = (int) j2;
        long j3 = (j2 >> 32) + ((iArr2[1] & 4294967295L) - (iArr[1] & 4294967295L));
        iArr2[1] = (int) j3;
        long j4 = (j3 >> 32) + ((iArr2[2] & 4294967295L) - (iArr[2] & 4294967295L));
        iArr2[2] = (int) j4;
        long j5 = (j4 >> 32) + ((iArr2[3] & 4294967295L) - (iArr[3] & 4294967295L));
        iArr2[3] = (int) j5;
        long j6 = (j5 >> 32) + ((iArr2[4] & 4294967295L) - (iArr[4] & 4294967295L));
        iArr2[4] = (int) j6;
        long j7 = (j6 >> 32) + ((iArr2[5] & 4294967295L) - (iArr[5] & 4294967295L));
        iArr2[5] = (int) j7;
        long j8 = (j7 >> 32) + ((iArr2[6] & 4294967295L) - (4294967295L & iArr[6]));
        iArr2[6] = (int) j8;
        return (int) (j8 >> 32);
    }

    public static BigInteger toBigInteger(int[] iArr) {
        byte[] bArr = new byte[28];
        for (int i2 = 0; i2 < 7; i2++) {
            int i3 = iArr[i2];
            if (i3 != 0) {
                Pack.intToBigEndian(i3, bArr, (6 - i2) << 2);
            }
        }
        return new BigInteger(1, bArr);
    }

    public static void zero(int[] iArr) {
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        iArr[3] = 0;
        iArr[4] = 0;
        iArr[5] = 0;
        iArr[6] = 0;
    }
}
