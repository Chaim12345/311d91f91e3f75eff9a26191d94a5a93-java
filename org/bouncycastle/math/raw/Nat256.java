package org.bouncycastle.math.raw;

import java.math.BigInteger;
import org.bouncycastle.util.Pack;
/* loaded from: classes4.dex */
public abstract class Nat256 {
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
        long j9 = (j8 >>> 32) + (iArr[i2 + 7] & 4294967295L) + (iArr2[i3 + 7] & 4294967295L);
        iArr3[i4 + 7] = (int) j9;
        return (int) (j9 >>> 32);
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
        long j9 = (j8 >>> 32) + (iArr[7] & 4294967295L) + (iArr2[7] & 4294967295L);
        iArr3[7] = (int) j9;
        return (int) (j9 >>> 32);
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
        int i12 = i4 + 7;
        long j9 = (j8 >>> 32) + (iArr[i2 + 7] & 4294967295L) + (iArr2[i3 + 7] & 4294967295L) + (iArr3[i12] & 4294967295L);
        iArr3[i12] = (int) j9;
        return (int) (j9 >>> 32);
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
        long j9 = (j8 >>> 32) + (iArr[7] & 4294967295L) + (iArr2[7] & 4294967295L) + (iArr3[7] & 4294967295L);
        iArr3[7] = (int) j9;
        return (int) (j9 >>> 32);
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
        long j8 = (j7 >>> 32) + (iArr[i2 + 6] & 4294967295L) + (iArr2[i11] & 4294967295L);
        iArr2[i11] = (int) j8;
        int i12 = i3 + 7;
        long j9 = (j8 >>> 32) + (iArr[i2 + 7] & 4294967295L) + (4294967295L & iArr2[i12]);
        iArr2[i12] = (int) j9;
        return (int) (j9 >>> 32);
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
        long j8 = (j7 >>> 32) + (iArr[6] & 4294967295L) + (iArr2[6] & 4294967295L);
        iArr2[6] = (int) j8;
        long j9 = (j8 >>> 32) + (iArr[7] & 4294967295L) + (4294967295L & iArr2[7]);
        iArr2[7] = (int) j9;
        return (int) (j9 >>> 32);
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
        long j8 = (j7 >>> 32) + (iArr[i22] & 4294967295L) + (iArr2[i23] & 4294967295L);
        int i24 = (int) j8;
        iArr[i22] = i24;
        iArr2[i23] = i24;
        int i25 = i2 + 7;
        int i26 = i3 + 7;
        long j9 = (j8 >>> 32) + (iArr[i25] & 4294967295L) + (4294967295L & iArr2[i26]);
        int i27 = (int) j9;
        iArr[i25] = i27;
        iArr2[i26] = i27;
        return (int) (j9 >>> 32);
    }

    public static void copy(int[] iArr, int i2, int[] iArr2, int i3) {
        iArr2[i3 + 0] = iArr[i2 + 0];
        iArr2[i3 + 1] = iArr[i2 + 1];
        iArr2[i3 + 2] = iArr[i2 + 2];
        iArr2[i3 + 3] = iArr[i2 + 3];
        iArr2[i3 + 4] = iArr[i2 + 4];
        iArr2[i3 + 5] = iArr[i2 + 5];
        iArr2[i3 + 6] = iArr[i2 + 6];
        iArr2[i3 + 7] = iArr[i2 + 7];
    }

    public static void copy(int[] iArr, int[] iArr2) {
        iArr2[0] = iArr[0];
        iArr2[1] = iArr[1];
        iArr2[2] = iArr[2];
        iArr2[3] = iArr[3];
        iArr2[4] = iArr[4];
        iArr2[5] = iArr[5];
        iArr2[6] = iArr[6];
        iArr2[7] = iArr[7];
    }

    public static void copy64(long[] jArr, int i2, long[] jArr2, int i3) {
        jArr2[i3 + 0] = jArr[i2 + 0];
        jArr2[i3 + 1] = jArr[i2 + 1];
        jArr2[i3 + 2] = jArr[i2 + 2];
        jArr2[i3 + 3] = jArr[i2 + 3];
    }

    public static void copy64(long[] jArr, long[] jArr2) {
        jArr2[0] = jArr[0];
        jArr2[1] = jArr[1];
        jArr2[2] = jArr[2];
        jArr2[3] = jArr[3];
    }

    public static int[] create() {
        return new int[8];
    }

    public static long[] create64() {
        return new long[4];
    }

    public static int[] createExt() {
        return new int[16];
    }

    public static long[] createExt64() {
        return new long[8];
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
        for (int i2 = 7; i2 >= 0; i2--) {
            if (iArr[i2] != iArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public static boolean eq64(long[] jArr, long[] jArr2) {
        for (int i2 = 3; i2 >= 0; i2--) {
            if (jArr[i2] != jArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > 256) {
            throw new IllegalArgumentException();
        }
        int[] create = create();
        for (int i2 = 0; i2 < 8; i2++) {
            create[i2] = bigInteger.intValue();
            bigInteger = bigInteger.shiftRight(32);
        }
        return create;
    }

    public static long[] fromBigInteger64(BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > 256) {
            throw new IllegalArgumentException();
        }
        long[] create64 = create64();
        for (int i2 = 0; i2 < 4; i2++) {
            create64[i2] = bigInteger.longValue();
            bigInteger = bigInteger.shiftRight(64);
        }
        return create64;
    }

    public static int getBit(int[] iArr, int i2) {
        int i3;
        if (i2 == 0) {
            i3 = iArr[0];
        } else if ((i2 & 255) != i2) {
            return 0;
        } else {
            i3 = iArr[i2 >>> 5] >>> (i2 & 31);
        }
        return i3 & 1;
    }

    public static boolean gte(int[] iArr, int i2, int[] iArr2, int i3) {
        for (int i4 = 7; i4 >= 0; i4--) {
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
        for (int i2 = 7; i2 >= 0; i2--) {
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
        for (int i2 = 1; i2 < 8; i2++) {
            if (iArr[i2] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isOne64(long[] jArr) {
        if (jArr[0] != 1) {
            return false;
        }
        for (int i2 = 1; i2 < 4; i2++) {
            if (jArr[i2] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isZero(int[] iArr) {
        for (int i2 = 0; i2 < 8; i2++) {
            if (iArr[i2] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isZero64(long[] jArr) {
        for (int i2 = 0; i2 < 4; i2++) {
            if (jArr[i2] != 0) {
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
        int i12;
        long j2 = iArr2[i3 + 0] & 4294967295L;
        long j3 = iArr2[i3 + 1] & 4294967295L;
        long j4 = iArr2[i3 + 2] & 4294967295L;
        long j5 = iArr2[i3 + 3] & 4294967295L;
        long j6 = iArr2[i3 + 4] & 4294967295L;
        long j7 = iArr2[i3 + 5] & 4294967295L;
        long j8 = iArr2[i3 + 6] & 4294967295L;
        long j9 = iArr[i2 + 0] & 4294967295L;
        long j10 = (j9 * j2) + 0;
        iArr3[i4 + 0] = (int) j10;
        long j11 = (j10 >>> 32) + (j9 * j3);
        iArr3[i4 + 1] = (int) j11;
        long j12 = (j11 >>> 32) + (j9 * j4);
        iArr3[i4 + 2] = (int) j12;
        long j13 = (j12 >>> 32) + (j9 * j5);
        iArr3[i4 + 3] = (int) j13;
        long j14 = (j13 >>> 32) + (j9 * j6);
        iArr3[i4 + 4] = (int) j14;
        long j15 = (j14 >>> 32) + (j9 * j7);
        iArr3[i4 + 5] = (int) j15;
        long j16 = (j15 >>> 32) + (j9 * j8);
        iArr3[i4 + 6] = (int) j16;
        long j17 = iArr2[i3 + 7] & 4294967295L;
        long j18 = (j16 >>> 32) + (j9 * j17);
        iArr3[i4 + 7] = (int) j18;
        iArr3[i4 + 8] = (int) (j18 >>> 32);
        int i13 = 1;
        int i14 = i4;
        int i15 = 1;
        while (i15 < 8) {
            i14 += i13;
            long j19 = iArr[i2 + i15] & 4294967295L;
            long j20 = (j19 * j2) + (iArr3[i5] & 4294967295L) + 0;
            iArr3[i14 + 0] = (int) j20;
            long j21 = j17;
            long j22 = (j20 >>> 32) + (j19 * j3) + (iArr3[i6] & 4294967295L);
            iArr3[i14 + 1] = (int) j22;
            long j23 = j4;
            long j24 = (j22 >>> 32) + (j19 * j4) + (iArr3[i7] & 4294967295L);
            iArr3[i14 + 2] = (int) j24;
            long j25 = (j24 >>> 32) + (j19 * j5) + (iArr3[i8] & 4294967295L);
            iArr3[i14 + 3] = (int) j25;
            long j26 = (j25 >>> 32) + (j19 * j6) + (iArr3[i9] & 4294967295L);
            iArr3[i14 + 4] = (int) j26;
            long j27 = (j26 >>> 32) + (j19 * j7) + (iArr3[i10] & 4294967295L);
            iArr3[i14 + 5] = (int) j27;
            long j28 = (j27 >>> 32) + (j19 * j8) + (iArr3[i11] & 4294967295L);
            iArr3[i14 + 6] = (int) j28;
            long j29 = (j28 >>> 32) + (j19 * j21) + (iArr3[i12] & 4294967295L);
            iArr3[i14 + 7] = (int) j29;
            iArr3[i14 + 8] = (int) (j29 >>> 32);
            i15++;
            j4 = j23;
            j17 = j21;
            j5 = j5;
            i13 = 1;
        }
    }

    public static void mul(int[] iArr, int[] iArr2, int[] iArr3) {
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
        long j9 = iArr2[7] & 4294967295L;
        long j10 = iArr[0] & 4294967295L;
        long j11 = (j10 * j2) + 0;
        iArr3[0] = (int) j11;
        long j12 = (j11 >>> 32) + (j10 * j3);
        iArr3[1] = (int) j12;
        long j13 = (j12 >>> 32) + (j10 * j4);
        iArr3[2] = (int) j13;
        long j14 = (j13 >>> 32) + (j10 * j5);
        iArr3[3] = (int) j14;
        long j15 = (j14 >>> 32) + (j10 * j6);
        iArr3[4] = (int) j15;
        long j16 = (j15 >>> 32) + (j10 * j7);
        iArr3[5] = (int) j16;
        long j17 = (j16 >>> 32) + (j10 * j8);
        iArr3[6] = (int) j17;
        long j18 = (j17 >>> 32) + (j10 * j9);
        iArr3[7] = (int) j18;
        iArr3[8] = (int) (j18 >>> 32);
        int i9 = 1;
        for (int i10 = 8; i9 < i10; i10 = 8) {
            long j19 = iArr[i9] & 4294967295L;
            long j20 = (j19 * j2) + (iArr3[i2] & 4294967295L) + 0;
            iArr3[i9 + 0] = (int) j20;
            int i11 = i9 + 1;
            long j21 = j3;
            long j22 = (j20 >>> 32) + (j19 * j3) + (iArr3[i11] & 4294967295L);
            iArr3[i11] = (int) j22;
            long j23 = j7;
            long j24 = (j22 >>> 32) + (j19 * j4) + (iArr3[i3] & 4294967295L);
            iArr3[i9 + 2] = (int) j24;
            long j25 = (j24 >>> 32) + (j19 * j5) + (iArr3[i4] & 4294967295L);
            iArr3[i9 + 3] = (int) j25;
            long j26 = (j25 >>> 32) + (j19 * j6) + (iArr3[i5] & 4294967295L);
            iArr3[i9 + 4] = (int) j26;
            long j27 = (j26 >>> 32) + (j19 * j23) + (iArr3[i6] & 4294967295L);
            iArr3[i9 + 5] = (int) j27;
            long j28 = (j27 >>> 32) + (j19 * j8) + (iArr3[i7] & 4294967295L);
            iArr3[i9 + 6] = (int) j28;
            long j29 = (j28 >>> 32) + (j19 * j9) + (iArr3[i8] & 4294967295L);
            iArr3[i9 + 7] = (int) j29;
            iArr3[i9 + 8] = (int) (j29 >>> 32);
            i9 = i11;
            j2 = j2;
            j3 = j21;
            j7 = j23;
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
        long j17 = (j15 >>> 32) + (j2 * j16) + j14 + (iArr2[i4 + 6] & 4294967295L);
        iArr3[i5 + 6] = (int) j17;
        long j18 = iArr[i3 + 7] & 4294967295L;
        long j19 = (j17 >>> 32) + (j2 * j18) + j16 + (4294967295L & iArr2[i4 + 7]);
        iArr3[i5 + 7] = (int) j19;
        return (j19 >>> 32) + j18;
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
        return Nat.incAt(8, iArr, i3, 4);
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
        return Nat.incAt(8, iArr, i4, 3);
    }

    public static int mulAddTo(int[] iArr, int i2, int[] iArr2, int i3, int[] iArr3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        long j2 = iArr2[i3 + 0] & 4294967295L;
        long j3 = iArr2[i3 + 1] & 4294967295L;
        long j4 = iArr2[i3 + 2] & 4294967295L;
        long j5 = iArr2[i3 + 3] & 4294967295L;
        long j6 = iArr2[i3 + 4] & 4294967295L;
        long j7 = iArr2[i3 + 5] & 4294967295L;
        long j8 = iArr2[i3 + 6] & 4294967295L;
        long j9 = iArr2[i3 + 7] & 4294967295L;
        int i13 = 0;
        long j10 = 0;
        int i14 = i4;
        while (i13 < 8) {
            int i15 = i13;
            long j11 = iArr[i2 + i13] & 4294967295L;
            long j12 = j2;
            long j13 = (j11 * j2) + (iArr3[i5] & 4294967295L) + 0;
            long j14 = j9;
            iArr3[i14 + 0] = (int) j13;
            int i16 = i14 + 1;
            long j15 = (j13 >>> 32) + (j11 * j3) + (iArr3[i16] & 4294967295L);
            iArr3[i16] = (int) j15;
            long j16 = (j15 >>> 32) + (j11 * j4) + (iArr3[i6] & 4294967295L);
            iArr3[i14 + 2] = (int) j16;
            long j17 = (j16 >>> 32) + (j11 * j5) + (iArr3[i7] & 4294967295L);
            iArr3[i14 + 3] = (int) j17;
            long j18 = (j17 >>> 32) + (j11 * j6) + (iArr3[i8] & 4294967295L);
            iArr3[i14 + 4] = (int) j18;
            long j19 = (j18 >>> 32) + (j11 * j7) + (iArr3[i9] & 4294967295L);
            iArr3[i14 + 5] = (int) j19;
            long j20 = (j19 >>> 32) + (j11 * j8) + (iArr3[i10] & 4294967295L);
            iArr3[i14 + 6] = (int) j20;
            long j21 = (j20 >>> 32) + (j11 * j14) + (iArr3[i11] & 4294967295L);
            iArr3[i14 + 7] = (int) j21;
            long j22 = (j21 >>> 32) + (iArr3[i12] & 4294967295L) + j10;
            iArr3[i14 + 8] = (int) j22;
            j10 = j22 >>> 32;
            i13 = i15 + 1;
            i14 = i16;
            j9 = j14;
            j2 = j12;
            j3 = j3;
        }
        return (int) j10;
    }

    public static int mulAddTo(int[] iArr, int[] iArr2, int[] iArr3) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        long j2 = iArr2[0] & 4294967295L;
        long j3 = iArr2[1] & 4294967295L;
        long j4 = iArr2[2] & 4294967295L;
        long j5 = iArr2[3] & 4294967295L;
        long j6 = iArr2[4] & 4294967295L;
        long j7 = iArr2[5] & 4294967295L;
        long j8 = iArr2[6] & 4294967295L;
        long j9 = iArr2[7] & 4294967295L;
        long j10 = 0;
        int i10 = 0;
        while (i10 < 8) {
            long j11 = j9;
            long j12 = iArr[i10] & 4294967295L;
            long j13 = j7;
            long j14 = (j12 * j2) + (iArr3[i2] & 4294967295L) + 0;
            iArr3[i10 + 0] = (int) j14;
            int i11 = i10 + 1;
            long j15 = j3;
            long j16 = (j14 >>> 32) + (j12 * j3) + (iArr3[i11] & 4294967295L);
            iArr3[i11] = (int) j16;
            long j17 = (j16 >>> 32) + (j12 * j4) + (iArr3[i3] & 4294967295L);
            iArr3[i10 + 2] = (int) j17;
            long j18 = (j17 >>> 32) + (j12 * j5) + (iArr3[i4] & 4294967295L);
            iArr3[i10 + 3] = (int) j18;
            long j19 = (j18 >>> 32) + (j12 * j6) + (iArr3[i5] & 4294967295L);
            iArr3[i10 + 4] = (int) j19;
            long j20 = (j19 >>> 32) + (j12 * j13) + (iArr3[i6] & 4294967295L);
            iArr3[i10 + 5] = (int) j20;
            long j21 = (j20 >>> 32) + (j12 * j8) + (iArr3[i7] & 4294967295L);
            iArr3[i10 + 6] = (int) j21;
            long j22 = (j21 >>> 32) + (j12 * j11) + (iArr3[i8] & 4294967295L);
            iArr3[i10 + 7] = (int) j22;
            long j23 = (j22 >>> 32) + (iArr3[i9] & 4294967295L) + j10;
            iArr3[i10 + 8] = (int) j23;
            j10 = j23 >>> 32;
            i10 = i11;
            j9 = j11;
            j7 = j13;
            j3 = j15;
        }
        return (int) j10;
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
        long j9 = (j8 >>> 32) + ((iArr[6] & 4294967295L) * j2);
        iArr[6] = (int) j9;
        long j10 = (j9 >>> 32) + (j2 * (4294967295L & iArr[7]));
        iArr[7] = (int) j10;
        return (int) (j10 >>> 32);
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
        long j9 = (j8 >>> 32) + ((iArr2[6] & 4294967295L) * j2) + (iArr[6] & 4294967295L);
        iArr2[6] = (int) j9;
        long j10 = (j9 >>> 32) + (j2 * (iArr2[7] & 4294967295L)) + (4294967295L & iArr[7]);
        iArr2[7] = (int) j10;
        return (int) (j10 >>> 32);
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
        } while (i4 < 8);
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
        long j9 = (j8 >>> 32) + ((iArr[i3 + 6] & 4294967295L) * j2) + (iArr2[i11] & 4294967295L);
        iArr2[i11] = (int) j9;
        int i12 = i4 + 7;
        long j10 = (j9 >>> 32) + (j2 * (iArr[i3 + 7] & 4294967295L)) + (iArr2[i12] & 4294967295L);
        iArr2[i12] = (int) j10;
        return (int) (j10 >>> 32);
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
        return Nat.incAt(8, iArr, i3, 3);
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
        int i15;
        int i16;
        long j2 = iArr[i2 + 0] & 4294967295L;
        int i17 = 0;
        int i18 = 16;
        int i19 = 7;
        while (true) {
            int i20 = i19 - 1;
            long j3 = iArr[i2 + i19] & 4294967295L;
            long j4 = j3 * j3;
            int i21 = i18 - 1;
            iArr2[i3 + i21] = (i17 << 31) | ((int) (j4 >>> 33));
            i18 = i21 - 1;
            iArr2[i3 + i18] = (int) (j4 >>> 1);
            i17 = (int) j4;
            if (i20 <= 0) {
                long j5 = j2 * j2;
                iArr2[i3 + 0] = (int) j5;
                long j6 = iArr[i2 + 1] & 4294967295L;
                long j7 = (((i17 << 31) & 4294967295L) | (j5 >>> 33)) + (j6 * j2);
                int i22 = (int) j7;
                iArr2[i3 + 1] = (i22 << 1) | (((int) (j5 >>> 32)) & 1);
                int i23 = i22 >>> 31;
                long j8 = (iArr2[i4] & 4294967295L) + (j7 >>> 32);
                long j9 = iArr[i2 + 2] & 4294967295L;
                long j10 = j8 + (j9 * j2);
                int i24 = (int) j10;
                iArr2[i3 + 2] = (i24 << 1) | i23;
                int i25 = i24 >>> 31;
                long j11 = (iArr2[i5] & 4294967295L) + (j10 >>> 32) + (j9 * j6);
                long j12 = (iArr2[i6] & 4294967295L) + (j11 >>> 32);
                long j13 = iArr[i2 + 3] & 4294967295L;
                long j14 = (iArr2[i7] & 4294967295L) + (j12 >>> 32);
                long j15 = (iArr2[i8] & 4294967295L) + (j14 >>> 32);
                long j16 = (j11 & 4294967295L) + (j13 * j2);
                int i26 = (int) j16;
                iArr2[i3 + 3] = (i26 << 1) | i25;
                long j17 = (j12 & 4294967295L) + (j16 >>> 32) + (j13 * j6);
                long j18 = (j14 & 4294967295L) + (j17 >>> 32) + (j13 * j9);
                long j19 = j15 + (j18 >>> 32);
                long j20 = j18 & 4294967295L;
                long j21 = iArr[i2 + 4] & 4294967295L;
                long j22 = (iArr2[i9] & 4294967295L) + (j19 >>> 32);
                long j23 = j19 & 4294967295L;
                long j24 = (j17 & 4294967295L) + (j21 * j2);
                int i27 = (int) j24;
                iArr2[i3 + 4] = (i26 >>> 31) | (i27 << 1);
                int i28 = i27 >>> 31;
                long j25 = j20 + (j24 >>> 32) + (j21 * j6);
                long j26 = j23 + (j25 >>> 32) + (j21 * j9);
                long j27 = (j22 & 4294967295L) + (j26 >>> 32) + (j21 * j13);
                long j28 = (iArr2[i10] & 4294967295L) + (j22 >>> 32) + (j27 >>> 32);
                long j29 = j27 & 4294967295L;
                long j30 = iArr[i2 + 5] & 4294967295L;
                long j31 = (iArr2[i11] & 4294967295L) + (j28 >>> 32);
                long j32 = (j25 & 4294967295L) + (j30 * j2);
                int i29 = (int) j32;
                iArr2[i3 + 5] = i28 | (i29 << 1);
                int i30 = i29 >>> 31;
                long j33 = (j26 & 4294967295L) + (j32 >>> 32) + (j30 * j6);
                long j34 = j29 + (j33 >>> 32) + (j30 * j9);
                long j35 = (j28 & 4294967295L) + (j34 >>> 32) + (j30 * j13);
                long j36 = (j31 & 4294967295L) + (j35 >>> 32) + (j30 * j21);
                long j37 = j35 & 4294967295L;
                long j38 = (iArr2[i12] & 4294967295L) + (j31 >>> 32) + (j36 >>> 32);
                long j39 = j36 & 4294967295L;
                long j40 = iArr[i2 + 6] & 4294967295L;
                long j41 = (iArr2[i13] & 4294967295L) + (j38 >>> 32);
                long j42 = j38 & 4294967295L;
                long j43 = (iArr2[i14] & 4294967295L) + (j41 >>> 32);
                long j44 = (j33 & 4294967295L) + (j40 * j2);
                int i31 = (int) j44;
                iArr2[i3 + 6] = i30 | (i31 << 1);
                int i32 = i31 >>> 31;
                long j45 = (j34 & 4294967295L) + (j44 >>> 32) + (j40 * j6);
                long j46 = j37 + (j45 >>> 32) + (j40 * j9);
                long j47 = j39 + (j46 >>> 32) + (j40 * j13);
                long j48 = j46 & 4294967295L;
                long j49 = j42 + (j47 >>> 32) + (j40 * j21);
                long j50 = (j41 & 4294967295L) + (j49 >>> 32) + (j40 * j30);
                long j51 = j43 + (j50 >>> 32);
                long j52 = j50 & 4294967295L;
                long j53 = iArr[i2 + 7] & 4294967295L;
                long j54 = (iArr2[i15] & 4294967295L) + (j51 >>> 32);
                long j55 = (j45 & 4294967295L) + (j2 * j53);
                int i33 = (int) j55;
                iArr2[i3 + 7] = (i33 << 1) | i32;
                long j56 = j48 + (j55 >>> 32) + (j53 * j6);
                long j57 = (j47 & 4294967295L) + (j56 >>> 32) + (j53 * j9);
                long j58 = (j49 & 4294967295L) + (j57 >>> 32) + (j53 * j13);
                long j59 = j52 + (j58 >>> 32) + (j53 * j21);
                long j60 = (j51 & 4294967295L) + (j59 >>> 32) + (j53 * j30);
                long j61 = (j54 & 4294967295L) + (j60 >>> 32) + (j53 * j40);
                long j62 = (iArr2[i16] & 4294967295L) + (j54 >>> 32) + (j61 >>> 32);
                int i34 = (int) j56;
                iArr2[i3 + 8] = (i33 >>> 31) | (i34 << 1);
                int i35 = i34 >>> 31;
                int i36 = (int) j57;
                iArr2[i3 + 9] = i35 | (i36 << 1);
                int i37 = i36 >>> 31;
                int i38 = (int) j58;
                iArr2[i3 + 10] = i37 | (i38 << 1);
                int i39 = i38 >>> 31;
                int i40 = (int) j59;
                iArr2[i3 + 11] = i39 | (i40 << 1);
                int i41 = i40 >>> 31;
                int i42 = (int) j60;
                iArr2[i3 + 12] = i41 | (i42 << 1);
                int i43 = i42 >>> 31;
                int i44 = (int) j61;
                iArr2[i3 + 13] = i43 | (i44 << 1);
                int i45 = i44 >>> 31;
                int i46 = (int) j62;
                iArr2[i3 + 14] = i45 | (i46 << 1);
                int i47 = i46 >>> 31;
                int i48 = i3 + 15;
                iArr2[i48] = i47 | ((iArr2[i48] + ((int) (j62 >>> 32))) << 1);
                return;
            }
            i19 = i20;
        }
    }

    public static void square(int[] iArr, int[] iArr2) {
        long j2 = iArr[0] & 4294967295L;
        int i2 = 16;
        int i3 = 0;
        int i4 = 7;
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
                long j25 = (j19 & 4294967295L) + (j23 * j2);
                int i11 = (int) j25;
                iArr2[4] = (i10 >>> 31) | (i11 << 1);
                int i12 = i11 >>> 31;
                long j26 = j22 + (j25 >>> 32) + (j23 * j7);
                long j27 = (j21 & 4294967295L) + (j26 >>> 32) + (j23 * j10);
                long j28 = (j24 & 4294967295L) + (j27 >>> 32) + (j23 * j14);
                long j29 = (iArr2[8] & 4294967295L) + (j24 >>> 32) + (j28 >>> 32);
                long j30 = j28 & 4294967295L;
                long j31 = iArr[5] & 4294967295L;
                long j32 = (iArr2[9] & 4294967295L) + (j29 >>> 32);
                long j33 = j29 & 4294967295L;
                long j34 = (iArr2[10] & 4294967295L) + (j32 >>> 32);
                long j35 = (j26 & 4294967295L) + (j31 * j2);
                int i13 = (int) j35;
                iArr2[5] = (i13 << 1) | i12;
                long j36 = (j27 & 4294967295L) + (j35 >>> 32) + (j31 * j7);
                long j37 = j30 + (j36 >>> 32) + (j31 * j10);
                long j38 = j33 + (j37 >>> 32) + (j31 * j14);
                long j39 = (j32 & 4294967295L) + (j38 >>> 32) + (j31 * j23);
                long j40 = j34 + (j39 >>> 32);
                long j41 = iArr[6] & 4294967295L;
                long j42 = (iArr2[11] & 4294967295L) + (j40 >>> 32);
                long j43 = j40 & 4294967295L;
                long j44 = (iArr2[12] & 4294967295L) + (j42 >>> 32);
                long j45 = (j36 & 4294967295L) + (j41 * j2);
                int i14 = (int) j45;
                iArr2[6] = (i14 << 1) | (i13 >>> 31);
                long j46 = (j37 & 4294967295L) + (j45 >>> 32) + (j41 * j7);
                long j47 = (j38 & 4294967295L) + (j46 >>> 32) + (j41 * j10);
                long j48 = (j39 & 4294967295L) + (j47 >>> 32) + (j41 * j14);
                long j49 = j43 + (j48 >>> 32) + (j41 * j23);
                long j50 = (j42 & 4294967295L) + (j49 >>> 32) + (j41 * j31);
                long j51 = j44 + (j50 >>> 32);
                long j52 = j50 & 4294967295L;
                long j53 = iArr[7] & 4294967295L;
                long j54 = (iArr2[13] & 4294967295L) + (j51 >>> 32);
                long j55 = (j46 & 4294967295L) + (j2 * j53);
                int i15 = (int) j55;
                iArr2[7] = (i14 >>> 31) | (i15 << 1);
                int i16 = i15 >>> 31;
                long j56 = (j47 & 4294967295L) + (j55 >>> 32) + (j7 * j53);
                long j57 = (j48 & 4294967295L) + (j56 >>> 32) + (j53 * j10);
                long j58 = (j49 & 4294967295L) + (j57 >>> 32) + (j53 * j14);
                long j59 = j52 + (j58 >>> 32) + (j53 * j23);
                long j60 = (j51 & 4294967295L) + (j59 >>> 32) + (j53 * j31);
                long j61 = (j54 & 4294967295L) + (j60 >>> 32) + (j53 * j41);
                long j62 = (iArr2[14] & 4294967295L) + (j54 >>> 32) + (j61 >>> 32);
                int i17 = (int) j56;
                iArr2[8] = i16 | (i17 << 1);
                int i18 = i17 >>> 31;
                int i19 = (int) j57;
                iArr2[9] = i18 | (i19 << 1);
                int i20 = i19 >>> 31;
                int i21 = (int) j58;
                iArr2[10] = i20 | (i21 << 1);
                int i22 = i21 >>> 31;
                int i23 = (int) j59;
                iArr2[11] = i22 | (i23 << 1);
                int i24 = i23 >>> 31;
                int i25 = (int) j60;
                iArr2[12] = i24 | (i25 << 1);
                int i26 = i25 >>> 31;
                int i27 = (int) j61;
                iArr2[13] = i26 | (i27 << 1);
                int i28 = i27 >>> 31;
                int i29 = (int) j62;
                iArr2[14] = i28 | (i29 << 1);
                iArr2[15] = (i29 >>> 31) | ((iArr2[15] + ((int) (j62 >>> 32))) << 1);
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
        long j9 = (j8 >> 32) + ((iArr[i2 + 7] & 4294967295L) - (iArr2[i3 + 7] & 4294967295L));
        iArr3[i4 + 7] = (int) j9;
        return (int) (j9 >> 32);
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
        long j9 = (j8 >> 32) + ((iArr[7] & 4294967295L) - (iArr2[7] & 4294967295L));
        iArr3[7] = (int) j9;
        return (int) (j9 >> 32);
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
        long j9 = (j8 >> 32) + (((iArr3[7] & 4294967295L) - (iArr[7] & 4294967295L)) - (iArr2[7] & 4294967295L));
        iArr3[7] = (int) j9;
        return (int) (j9 >> 32);
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
        int i11 = i3 + 7;
        long j9 = (j8 >> 32) + ((iArr2[i11] & 4294967295L) - (iArr[i2 + 7] & 4294967295L));
        iArr2[i11] = (int) j9;
        return (int) (j9 >> 32);
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
        long j8 = (j7 >> 32) + ((iArr2[6] & 4294967295L) - (iArr[6] & 4294967295L));
        iArr2[6] = (int) j8;
        long j9 = (j8 >> 32) + ((iArr2[7] & 4294967295L) - (4294967295L & iArr[7]));
        iArr2[7] = (int) j9;
        return (int) (j9 >> 32);
    }

    public static BigInteger toBigInteger(int[] iArr) {
        byte[] bArr = new byte[32];
        for (int i2 = 0; i2 < 8; i2++) {
            int i3 = iArr[i2];
            if (i3 != 0) {
                Pack.intToBigEndian(i3, bArr, (7 - i2) << 2);
            }
        }
        return new BigInteger(1, bArr);
    }

    public static BigInteger toBigInteger64(long[] jArr) {
        byte[] bArr = new byte[32];
        for (int i2 = 0; i2 < 4; i2++) {
            long j2 = jArr[i2];
            if (j2 != 0) {
                Pack.longToBigEndian(j2, bArr, (3 - i2) << 3);
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
        iArr[7] = 0;
    }
}
