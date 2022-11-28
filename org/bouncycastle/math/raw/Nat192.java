package org.bouncycastle.math.raw;

import java.math.BigInteger;
import org.bouncycastle.util.Pack;
/* loaded from: classes4.dex */
public abstract class Nat192 {
    private static final long M = 4294967295L;

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
        return (int) (j7 >>> 32);
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
        return (int) (j7 >>> 32);
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
        long j7 = (j6 >>> 32) + (iArr[i2 + 5] & 4294967295L) + (4294967295L & iArr2[i10]);
        iArr2[i10] = (int) j7;
        return (int) (j7 >>> 32);
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
        long j7 = (j6 >>> 32) + (iArr[5] & 4294967295L) + (4294967295L & iArr2[5]);
        iArr2[5] = (int) j7;
        return (int) (j7 >>> 32);
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
        long j7 = (j6 >>> 32) + (iArr[i19] & 4294967295L) + (4294967295L & iArr2[i20]);
        int i21 = (int) j7;
        iArr[i19] = i21;
        iArr2[i20] = i21;
        return (int) (j7 >>> 32);
    }

    public static void copy(int[] iArr, int i2, int[] iArr2, int i3) {
        iArr2[i3 + 0] = iArr[i2 + 0];
        iArr2[i3 + 1] = iArr[i2 + 1];
        iArr2[i3 + 2] = iArr[i2 + 2];
        iArr2[i3 + 3] = iArr[i2 + 3];
        iArr2[i3 + 4] = iArr[i2 + 4];
        iArr2[i3 + 5] = iArr[i2 + 5];
    }

    public static void copy(int[] iArr, int[] iArr2) {
        iArr2[0] = iArr[0];
        iArr2[1] = iArr[1];
        iArr2[2] = iArr[2];
        iArr2[3] = iArr[3];
        iArr2[4] = iArr[4];
        iArr2[5] = iArr[5];
    }

    public static void copy64(long[] jArr, int i2, long[] jArr2, int i3) {
        jArr2[i3 + 0] = jArr[i2 + 0];
        jArr2[i3 + 1] = jArr[i2 + 1];
        jArr2[i3 + 2] = jArr[i2 + 2];
    }

    public static void copy64(long[] jArr, long[] jArr2) {
        jArr2[0] = jArr[0];
        jArr2[1] = jArr[1];
        jArr2[2] = jArr[2];
    }

    public static int[] create() {
        return new int[6];
    }

    public static long[] create64() {
        return new long[3];
    }

    public static int[] createExt() {
        return new int[12];
    }

    public static long[] createExt64() {
        return new long[6];
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
        for (int i2 = 5; i2 >= 0; i2--) {
            if (iArr[i2] != iArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public static boolean eq64(long[] jArr, long[] jArr2) {
        for (int i2 = 2; i2 >= 0; i2--) {
            if (jArr[i2] != jArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > 192) {
            throw new IllegalArgumentException();
        }
        int[] create = create();
        for (int i2 = 0; i2 < 6; i2++) {
            create[i2] = bigInteger.intValue();
            bigInteger = bigInteger.shiftRight(32);
        }
        return create;
    }

    public static long[] fromBigInteger64(BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > 192) {
            throw new IllegalArgumentException();
        }
        long[] create64 = create64();
        for (int i2 = 0; i2 < 3; i2++) {
            create64[i2] = bigInteger.longValue();
            bigInteger = bigInteger.shiftRight(64);
        }
        return create64;
    }

    public static int getBit(int[] iArr, int i2) {
        int i3;
        if (i2 == 0) {
            i3 = iArr[0];
        } else {
            int i4 = i2 >> 5;
            if (i4 < 0 || i4 >= 6) {
                return 0;
            }
            i3 = iArr[i4] >>> (i2 & 31);
        }
        return i3 & 1;
    }

    public static boolean gte(int[] iArr, int i2, int[] iArr2, int i3) {
        for (int i4 = 5; i4 >= 0; i4--) {
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
        for (int i2 = 5; i2 >= 0; i2--) {
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
        for (int i2 = 1; i2 < 6; i2++) {
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
        for (int i2 = 1; i2 < 3; i2++) {
            if (jArr[i2] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isZero(int[] iArr) {
        for (int i2 = 0; i2 < 6; i2++) {
            if (iArr[i2] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isZero64(long[] jArr) {
        for (int i2 = 0; i2 < 3; i2++) {
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
        iArr3[i4 + 6] = (int) (j14 >>> 32);
        int i11 = 1;
        int i12 = i4;
        int i13 = 1;
        while (i13 < 6) {
            i12 += i11;
            long j15 = iArr[i2 + i13] & 4294967295L;
            long j16 = (j15 * j2) + (iArr3[i5] & 4294967295L) + 0;
            iArr3[i12 + 0] = (int) j16;
            long j17 = j7;
            long j18 = (j16 >>> 32) + (j15 * j3) + (iArr3[i6] & 4294967295L);
            iArr3[i12 + 1] = (int) j18;
            long j19 = (j18 >>> 32) + (j15 * j4) + (iArr3[i7] & 4294967295L);
            iArr3[i12 + 2] = (int) j19;
            long j20 = (j19 >>> 32) + (j15 * j5) + (iArr3[i8] & 4294967295L);
            iArr3[i12 + 3] = (int) j20;
            long j21 = (j20 >>> 32) + (j15 * j6) + (iArr3[i9] & 4294967295L);
            iArr3[i12 + 4] = (int) j21;
            long j22 = (j21 >>> 32) + (j15 * j17) + (iArr3[i10] & 4294967295L);
            iArr3[i12 + 5] = (int) j22;
            iArr3[i12 + 6] = (int) (j22 >>> 32);
            i13++;
            j4 = j4;
            j7 = j17;
            i11 = 1;
        }
    }

    public static void mul(int[] iArr, int[] iArr2, int[] iArr3) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        long j2 = iArr2[0] & 4294967295L;
        long j3 = iArr2[1] & 4294967295L;
        long j4 = iArr2[2] & 4294967295L;
        long j5 = iArr2[3] & 4294967295L;
        long j6 = iArr2[4] & 4294967295L;
        long j7 = iArr2[5] & 4294967295L;
        long j8 = iArr[0] & 4294967295L;
        long j9 = (j8 * j2) + 0;
        iArr3[0] = (int) j9;
        long j10 = (j9 >>> 32) + (j8 * j3);
        iArr3[1] = (int) j10;
        long j11 = (j10 >>> 32) + (j8 * j4);
        iArr3[2] = (int) j11;
        long j12 = (j11 >>> 32) + (j8 * j5);
        iArr3[3] = (int) j12;
        long j13 = (j12 >>> 32) + (j8 * j6);
        iArr3[4] = (int) j13;
        long j14 = (j13 >>> 32) + (j8 * j7);
        iArr3[5] = (int) j14;
        iArr3[6] = (int) (j14 >>> 32);
        int i7 = 1;
        for (int i8 = 6; i7 < i8; i8 = 6) {
            long j15 = iArr[i7] & 4294967295L;
            long j16 = (j15 * j2) + (iArr3[i2] & 4294967295L) + 0;
            iArr3[i7 + 0] = (int) j16;
            int i9 = i7 + 1;
            long j17 = j3;
            long j18 = (j16 >>> 32) + (j15 * j3) + (iArr3[i9] & 4294967295L);
            iArr3[i9] = (int) j18;
            long j19 = j7;
            long j20 = (j18 >>> 32) + (j15 * j4) + (iArr3[i3] & 4294967295L);
            iArr3[i7 + 2] = (int) j20;
            long j21 = (j20 >>> 32) + (j15 * j5) + (iArr3[i4] & 4294967295L);
            iArr3[i7 + 3] = (int) j21;
            long j22 = (j21 >>> 32) + (j15 * j6) + (iArr3[i5] & 4294967295L);
            iArr3[i7 + 4] = (int) j22;
            long j23 = (j22 >>> 32) + (j15 * j19) + (iArr3[i6] & 4294967295L);
            iArr3[i7 + 5] = (int) j23;
            iArr3[i7 + 6] = (int) (j23 >>> 32);
            i7 = i9;
            j2 = j2;
            j3 = j17;
            j7 = j19;
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
        long j15 = (j13 >>> 32) + (j2 * j14) + j12 + (4294967295L & iArr2[i4 + 5]);
        iArr3[i5 + 5] = (int) j15;
        return (j15 >>> 32) + j14;
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
        return Nat.incAt(6, iArr, i3, 4);
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
        return Nat.incAt(6, iArr, i4, 3);
    }

    public static int mulAddTo(int[] iArr, int i2, int[] iArr2, int i3, int[] iArr3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        long j2 = 4294967295L;
        long j3 = iArr2[i3 + 0] & 4294967295L;
        long j4 = iArr2[i3 + 1] & 4294967295L;
        long j5 = iArr2[i3 + 2] & 4294967295L;
        long j6 = iArr2[i3 + 3] & 4294967295L;
        long j7 = iArr2[i3 + 4] & 4294967295L;
        long j8 = iArr2[i3 + 5] & 4294967295L;
        int i11 = 0;
        long j9 = 0;
        int i12 = i4;
        while (i11 < 6) {
            int i13 = i11;
            long j10 = iArr[i2 + i11] & j2;
            long j11 = (j10 * j3) + (iArr3[i5] & j2) + 0;
            iArr3[i12 + 0] = (int) j11;
            int i14 = i12 + 1;
            long j12 = j4;
            long j13 = (j11 >>> 32) + (j10 * j4) + (iArr3[i14] & 4294967295L);
            iArr3[i14] = (int) j13;
            long j14 = j5;
            long j15 = (j13 >>> 32) + (j10 * j5) + (iArr3[i6] & 4294967295L);
            iArr3[i12 + 2] = (int) j15;
            long j16 = (j15 >>> 32) + (j10 * j6) + (iArr3[i7] & 4294967295L);
            iArr3[i12 + 3] = (int) j16;
            long j17 = (j16 >>> 32) + (j10 * j7) + (iArr3[i8] & 4294967295L);
            iArr3[i12 + 4] = (int) j17;
            long j18 = (j17 >>> 32) + (j10 * j8) + (iArr3[i9] & 4294967295L);
            iArr3[i12 + 5] = (int) j18;
            long j19 = (j18 >>> 32) + (iArr3[i10] & 4294967295L) + j9;
            iArr3[i12 + 6] = (int) j19;
            j9 = j19 >>> 32;
            i11 = i13 + 1;
            i12 = i14;
            j3 = j3;
            j2 = 4294967295L;
            j4 = j12;
            j5 = j14;
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
        int i8 = 0;
        long j2 = 4294967295L;
        long j3 = iArr2[0] & 4294967295L;
        long j4 = iArr2[1] & 4294967295L;
        long j5 = iArr2[2] & 4294967295L;
        long j6 = iArr2[3] & 4294967295L;
        long j7 = iArr2[4] & 4294967295L;
        long j8 = iArr2[5] & 4294967295L;
        long j9 = 0;
        while (i8 < 6) {
            long j10 = j8;
            long j11 = iArr[i8] & j2;
            long j12 = j3;
            long j13 = (j11 * j3) + (iArr3[i2] & j2) + 0;
            iArr3[i8 + 0] = (int) j13;
            int i9 = i8 + 1;
            long j14 = (j13 >>> 32) + (j11 * j4) + (iArr3[i9] & 4294967295L);
            iArr3[i9] = (int) j14;
            long j15 = (j14 >>> 32) + (j11 * j5) + (iArr3[i3] & 4294967295L);
            iArr3[i8 + 2] = (int) j15;
            long j16 = (j15 >>> 32) + (j11 * j6) + (iArr3[i4] & 4294967295L);
            iArr3[i8 + 3] = (int) j16;
            long j17 = (j16 >>> 32) + (j11 * j7) + (iArr3[i5] & 4294967295L);
            iArr3[i8 + 4] = (int) j17;
            long j18 = (j17 >>> 32) + (j11 * j10) + (iArr3[i6] & 4294967295L);
            iArr3[i8 + 5] = (int) j18;
            long j19 = (j18 >>> 32) + (iArr3[i7] & 4294967295L) + j9;
            iArr3[i8 + 6] = (int) j19;
            j9 = j19 >>> 32;
            i8 = i9;
            j2 = 4294967295L;
            j8 = j10;
            j3 = j12;
            j4 = j4;
            j5 = j5;
        }
        return (int) j9;
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
        } while (i4 < 6);
        return (int) j3;
    }

    public static int mulWordAddExt(int i2, int[] iArr, int i3, int[] iArr2, int i4) {
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
        long j8 = (j7 >>> 32) + (j2 * (iArr[i3 + 5] & 4294967295L)) + (iArr2[i10] & 4294967295L);
        iArr2[i10] = (int) j8;
        return (int) (j8 >>> 32);
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
        return Nat.incAt(6, iArr, i3, 3);
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
        long j2 = iArr[i2 + 0] & 4294967295L;
        int i13 = 0;
        int i14 = 12;
        int i15 = 5;
        while (true) {
            int i16 = i15 - 1;
            long j3 = iArr[i2 + i15] & 4294967295L;
            long j4 = j3 * j3;
            int i17 = i14 - 1;
            iArr2[i3 + i17] = (i13 << 31) | ((int) (j4 >>> 33));
            i14 = i17 - 1;
            iArr2[i3 + i14] = (int) (j4 >>> 1);
            i13 = (int) j4;
            if (i16 <= 0) {
                long j5 = j2 * j2;
                iArr2[i3 + 0] = (int) j5;
                long j6 = iArr[i2 + 1] & 4294967295L;
                long j7 = (((i13 << 31) & 4294967295L) | (j5 >>> 33)) + (j6 * j2);
                int i18 = (int) j7;
                iArr2[i3 + 1] = (i18 << 1) | (((int) (j5 >>> 32)) & 1);
                int i19 = i18 >>> 31;
                long j8 = (iArr2[i4] & 4294967295L) + (j7 >>> 32);
                long j9 = iArr[i2 + 2] & 4294967295L;
                long j10 = j8 + (j9 * j2);
                int i20 = (int) j10;
                iArr2[i3 + 2] = (i20 << 1) | i19;
                int i21 = i20 >>> 31;
                long j11 = (iArr2[i5] & 4294967295L) + (j10 >>> 32) + (j9 * j6);
                long j12 = (iArr2[i6] & 4294967295L) + (j11 >>> 32);
                long j13 = iArr[i2 + 3] & 4294967295L;
                long j14 = (iArr2[i7] & 4294967295L) + (j12 >>> 32);
                long j15 = (iArr2[i8] & 4294967295L) + (j14 >>> 32);
                long j16 = (j11 & 4294967295L) + (j13 * j2);
                int i22 = (int) j16;
                iArr2[i3 + 3] = (i22 << 1) | i21;
                long j17 = (j12 & 4294967295L) + (j16 >>> 32) + (j13 * j6);
                long j18 = (j14 & 4294967295L) + (j17 >>> 32) + (j13 * j9);
                long j19 = j15 + (j18 >>> 32);
                long j20 = j18 & 4294967295L;
                long j21 = iArr[i2 + 4] & 4294967295L;
                long j22 = (iArr2[i9] & 4294967295L) + (j19 >>> 32);
                long j23 = j19 & 4294967295L;
                long j24 = (j17 & 4294967295L) + (j21 * j2);
                int i23 = (int) j24;
                iArr2[i3 + 4] = (i22 >>> 31) | (i23 << 1);
                int i24 = i23 >>> 31;
                long j25 = j20 + (j24 >>> 32) + (j21 * j6);
                long j26 = j23 + (j25 >>> 32) + (j21 * j9);
                long j27 = (j22 & 4294967295L) + (j26 >>> 32) + (j21 * j13);
                long j28 = (iArr2[i10] & 4294967295L) + (j22 >>> 32) + (j27 >>> 32);
                long j29 = j27 & 4294967295L;
                long j30 = iArr[i2 + 5] & 4294967295L;
                long j31 = (iArr2[i11] & 4294967295L) + (j28 >>> 32);
                long j32 = (j25 & 4294967295L) + (j2 * j30);
                int i25 = (int) j32;
                iArr2[i3 + 5] = (i25 << 1) | i24;
                long j33 = (j26 & 4294967295L) + (j32 >>> 32) + (j30 * j6);
                long j34 = j29 + (j33 >>> 32) + (j30 * j9);
                long j35 = (j28 & 4294967295L) + (j34 >>> 32) + (j30 * j13);
                long j36 = (j31 & 4294967295L) + (j35 >>> 32) + (j30 * j21);
                long j37 = (iArr2[i12] & 4294967295L) + (j31 >>> 32) + (j36 >>> 32);
                int i26 = (int) j33;
                iArr2[i3 + 6] = (i25 >>> 31) | (i26 << 1);
                int i27 = (int) j34;
                iArr2[i3 + 7] = (i26 >>> 31) | (i27 << 1);
                int i28 = i27 >>> 31;
                int i29 = (int) j35;
                iArr2[i3 + 8] = i28 | (i29 << 1);
                int i30 = i29 >>> 31;
                int i31 = (int) j36;
                iArr2[i3 + 9] = i30 | (i31 << 1);
                int i32 = i31 >>> 31;
                int i33 = (int) j37;
                iArr2[i3 + 10] = i32 | (i33 << 1);
                int i34 = i33 >>> 31;
                int i35 = i3 + 11;
                iArr2[i35] = i34 | ((iArr2[i35] + ((int) (j37 >>> 32))) << 1);
                return;
            }
            i15 = i16;
        }
    }

    public static void square(int[] iArr, int[] iArr2) {
        long j2 = iArr[0] & 4294967295L;
        int i2 = 12;
        int i3 = 0;
        int i4 = 5;
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
                long j26 = j22 + (j25 >>> 32) + (j23 * j7);
                long j27 = (j21 & 4294967295L) + (j26 >>> 32) + (j23 * j10);
                long j28 = (j24 & 4294967295L) + (j27 >>> 32) + (j23 * j14);
                long j29 = (iArr2[8] & 4294967295L) + (j24 >>> 32) + (j28 >>> 32);
                long j30 = iArr[5] & 4294967295L;
                long j31 = (iArr2[9] & 4294967295L) + (j29 >>> 32);
                long j32 = (j26 & 4294967295L) + (j2 * j30);
                int i12 = (int) j32;
                iArr2[5] = (i11 >>> 31) | (i12 << 1);
                int i13 = i12 >>> 31;
                long j33 = (j27 & 4294967295L) + (j32 >>> 32) + (j7 * j30);
                long j34 = (j28 & 4294967295L) + (j33 >>> 32) + (j30 * j10);
                long j35 = (j29 & 4294967295L) + (j34 >>> 32) + (j30 * j14);
                long j36 = (j31 & 4294967295L) + (j35 >>> 32) + (j30 * j23);
                long j37 = (iArr2[10] & 4294967295L) + (j31 >>> 32) + (j36 >>> 32);
                int i14 = (int) j33;
                iArr2[6] = i13 | (i14 << 1);
                int i15 = (int) j34;
                iArr2[7] = (i14 >>> 31) | (i15 << 1);
                int i16 = i15 >>> 31;
                int i17 = (int) j35;
                iArr2[8] = i16 | (i17 << 1);
                int i18 = i17 >>> 31;
                int i19 = (int) j36;
                iArr2[9] = i18 | (i19 << 1);
                int i20 = i19 >>> 31;
                int i21 = (int) j37;
                iArr2[10] = i20 | (i21 << 1);
                iArr2[11] = (i21 >>> 31) | ((iArr2[11] + ((int) (j37 >>> 32))) << 1);
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
        return (int) (j7 >> 32);
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
        return (int) (j7 >> 32);
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
        return (int) (j7 >> 32);
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
        return (int) (j7 >> 32);
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
        long j7 = (j6 >> 32) + ((iArr2[5] & 4294967295L) - (4294967295L & iArr[5]));
        iArr2[5] = (int) j7;
        return (int) (j7 >> 32);
    }

    public static BigInteger toBigInteger(int[] iArr) {
        byte[] bArr = new byte[24];
        for (int i2 = 0; i2 < 6; i2++) {
            int i3 = iArr[i2];
            if (i3 != 0) {
                Pack.intToBigEndian(i3, bArr, (5 - i2) << 2);
            }
        }
        return new BigInteger(1, bArr);
    }

    public static BigInteger toBigInteger64(long[] jArr) {
        byte[] bArr = new byte[24];
        for (int i2 = 0; i2 < 3; i2++) {
            long j2 = jArr[i2];
            if (j2 != 0) {
                Pack.longToBigEndian(j2, bArr, (2 - i2) << 3);
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
    }
}
