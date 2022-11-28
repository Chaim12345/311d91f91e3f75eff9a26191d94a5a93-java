package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.math.raw.Mod;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat384;
import org.bouncycastle.util.Pack;
/* loaded from: classes4.dex */
public class SecP384R1Field {
    private static final long M = 4294967295L;
    private static final int P11 = -1;
    private static final int PExt23 = -1;

    /* renamed from: a  reason: collision with root package name */
    static final int[] f14219a = {-1, 0, 0, -1, -2, -1, -1, -1, -1, -1, -1, -1};
    private static final int[] PExt = {1, -2, 0, 2, 0, -2, 0, 2, 1, 0, 0, 0, -2, 1, 0, -2, -3, -1, -1, -1, -1, -1, -1, -1};
    private static final int[] PExtInv = {-1, 1, -1, -3, -1, 1, -1, -3, -2, -1, -1, -1, 1, -2, -1, 1, 2};

    public static void add(int[] iArr, int[] iArr2, int[] iArr3) {
        if (Nat.add(12, iArr, iArr2, iArr3) != 0 || (iArr3[11] == -1 && Nat.gte(12, iArr3, f14219a))) {
            addPInvTo(iArr3);
        }
    }

    public static void addExt(int[] iArr, int[] iArr2, int[] iArr3) {
        if (Nat.add(24, iArr, iArr2, iArr3) != 0 || (iArr3[23] == -1 && Nat.gte(24, iArr3, PExt))) {
            int[] iArr4 = PExtInv;
            if (Nat.addTo(iArr4.length, iArr4, iArr3) != 0) {
                Nat.incAt(24, iArr3, iArr4.length);
            }
        }
    }

    public static void addOne(int[] iArr, int[] iArr2) {
        if (Nat.inc(12, iArr, iArr2) != 0 || (iArr2[11] == -1 && Nat.gte(12, iArr2, f14219a))) {
            addPInvTo(iArr2);
        }
    }

    private static void addPInvTo(int[] iArr) {
        long j2 = (iArr[0] & 4294967295L) + 1;
        iArr[0] = (int) j2;
        long j3 = (j2 >> 32) + ((iArr[1] & 4294967295L) - 1);
        iArr[1] = (int) j3;
        long j4 = j3 >> 32;
        if (j4 != 0) {
            long j5 = j4 + (iArr[2] & 4294967295L);
            iArr[2] = (int) j5;
            j4 = j5 >> 32;
        }
        long j6 = j4 + (iArr[3] & 4294967295L) + 1;
        iArr[3] = (int) j6;
        long j7 = (j6 >> 32) + (4294967295L & iArr[4]) + 1;
        iArr[4] = (int) j7;
        if ((j7 >> 32) != 0) {
            Nat.incAt(12, iArr, 5);
        }
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        int[] fromBigInteger = Nat.fromBigInteger(384, bigInteger);
        if (fromBigInteger[11] == -1) {
            int[] iArr = f14219a;
            if (Nat.gte(12, fromBigInteger, iArr)) {
                Nat.subFrom(12, iArr, fromBigInteger);
            }
        }
        return fromBigInteger;
    }

    public static void half(int[] iArr, int[] iArr2) {
        if ((iArr[0] & 1) == 0) {
            Nat.shiftDownBit(12, iArr, 0, iArr2);
        } else {
            Nat.shiftDownBit(12, iArr2, Nat.add(12, iArr, f14219a, iArr2));
        }
    }

    public static void inv(int[] iArr, int[] iArr2) {
        Mod.checkedModOddInverse(f14219a, iArr, iArr2);
    }

    public static int isZero(int[] iArr) {
        int i2 = 0;
        for (int i3 = 0; i3 < 12; i3++) {
            i2 |= iArr[i3];
        }
        return (((i2 >>> 1) | (i2 & 1)) - 1) >> 31;
    }

    public static void multiply(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] create = Nat.create(24);
        Nat384.mul(iArr, iArr2, create);
        reduce(create, iArr3);
    }

    public static void negate(int[] iArr, int[] iArr2) {
        if (isZero(iArr) == 0) {
            Nat.sub(12, f14219a, iArr, iArr2);
            return;
        }
        int[] iArr3 = f14219a;
        Nat.sub(12, iArr3, iArr3, iArr2);
    }

    public static void random(SecureRandom secureRandom, int[] iArr) {
        byte[] bArr = new byte[48];
        do {
            secureRandom.nextBytes(bArr);
            Pack.littleEndianToInt(bArr, 0, iArr, 0, 12);
        } while (Nat.lessThan(12, iArr, f14219a) == 0);
    }

    public static void randomMult(SecureRandom secureRandom, int[] iArr) {
        do {
            random(secureRandom, iArr);
        } while (isZero(iArr) != 0);
    }

    public static void reduce(int[] iArr, int[] iArr2) {
        long j2 = iArr[16] & 4294967295L;
        long j3 = iArr[17] & 4294967295L;
        long j4 = iArr[18] & 4294967295L;
        long j5 = iArr[19] & 4294967295L;
        long j6 = iArr[20] & 4294967295L;
        long j7 = iArr[21] & 4294967295L;
        long j8 = iArr[22] & 4294967295L;
        long j9 = iArr[23] & 4294967295L;
        long j10 = ((iArr[12] & 4294967295L) + j6) - 1;
        long j11 = (iArr[13] & 4294967295L) + j8;
        long j12 = (iArr[14] & 4294967295L) + j8 + j9;
        long j13 = (iArr[15] & 4294967295L) + j9;
        long j14 = j3 + j7;
        long j15 = j7 - j9;
        long j16 = j8 - j9;
        long j17 = j10 + j15;
        long j18 = (iArr[0] & 4294967295L) + j17 + 0;
        iArr2[0] = (int) j18;
        long j19 = (j18 >> 32) + (((iArr[1] & 4294967295L) + j9) - j10) + j11;
        iArr2[1] = (int) j19;
        long j20 = (j19 >> 32) + (((iArr[2] & 4294967295L) - j7) - j11) + j12;
        iArr2[2] = (int) j20;
        long j21 = (j20 >> 32) + ((iArr[3] & 4294967295L) - j12) + j13 + j17;
        iArr2[3] = (int) j21;
        long j22 = (j21 >> 32) + (((((iArr[4] & 4294967295L) + j2) + j7) + j11) - j13) + j17;
        iArr2[4] = (int) j22;
        long j23 = (j22 >> 32) + ((iArr[5] & 4294967295L) - j2) + j11 + j12 + j14;
        iArr2[5] = (int) j23;
        long j24 = (j23 >> 32) + (((iArr[6] & 4294967295L) + j4) - j3) + j12 + j13;
        iArr2[6] = (int) j24;
        long j25 = (j24 >> 32) + ((((iArr[7] & 4294967295L) + j2) + j5) - j4) + j13;
        iArr2[7] = (int) j25;
        long j26 = (j25 >> 32) + (((((iArr[8] & 4294967295L) + j2) + j3) + j6) - j5);
        iArr2[8] = (int) j26;
        long j27 = (j26 >> 32) + (((iArr[9] & 4294967295L) + j4) - j6) + j14;
        iArr2[9] = (int) j27;
        long j28 = (j27 >> 32) + ((((iArr[10] & 4294967295L) + j4) + j5) - j15) + j16;
        iArr2[10] = (int) j28;
        long j29 = (j28 >> 32) + ((((iArr[11] & 4294967295L) + j5) + j6) - j16);
        iArr2[11] = (int) j29;
        reduce32((int) ((j29 >> 32) + 1), iArr2);
    }

    public static void reduce32(int i2, int[] iArr) {
        long j2;
        if (i2 != 0) {
            long j3 = i2 & 4294967295L;
            long j4 = (iArr[0] & 4294967295L) + j3 + 0;
            iArr[0] = (int) j4;
            long j5 = (j4 >> 32) + ((iArr[1] & 4294967295L) - j3);
            iArr[1] = (int) j5;
            long j6 = j5 >> 32;
            if (j6 != 0) {
                long j7 = j6 + (iArr[2] & 4294967295L);
                iArr[2] = (int) j7;
                j6 = j7 >> 32;
            }
            long j8 = j6 + (iArr[3] & 4294967295L) + j3;
            iArr[3] = (int) j8;
            long j9 = (j8 >> 32) + (4294967295L & iArr[4]) + j3;
            iArr[4] = (int) j9;
            j2 = j9 >> 32;
        } else {
            j2 = 0;
        }
        if ((j2 == 0 || Nat.incAt(12, iArr, 5) == 0) && !(iArr[11] == -1 && Nat.gte(12, iArr, f14219a))) {
            return;
        }
        addPInvTo(iArr);
    }

    public static void square(int[] iArr, int[] iArr2) {
        int[] create = Nat.create(24);
        Nat384.square(iArr, create);
        reduce(create, iArr2);
    }

    public static void squareN(int[] iArr, int i2, int[] iArr2) {
        int[] create = Nat.create(24);
        Nat384.square(iArr, create);
        while (true) {
            reduce(create, iArr2);
            i2--;
            if (i2 <= 0) {
                return;
            }
            Nat384.square(iArr2, create);
        }
    }

    private static void subPInvFrom(int[] iArr) {
        long j2 = (iArr[0] & 4294967295L) - 1;
        iArr[0] = (int) j2;
        long j3 = (j2 >> 32) + (iArr[1] & 4294967295L) + 1;
        iArr[1] = (int) j3;
        long j4 = j3 >> 32;
        if (j4 != 0) {
            long j5 = j4 + (iArr[2] & 4294967295L);
            iArr[2] = (int) j5;
            j4 = j5 >> 32;
        }
        long j6 = j4 + ((iArr[3] & 4294967295L) - 1);
        iArr[3] = (int) j6;
        long j7 = (j6 >> 32) + ((4294967295L & iArr[4]) - 1);
        iArr[4] = (int) j7;
        if ((j7 >> 32) != 0) {
            Nat.decAt(12, iArr, 5);
        }
    }

    public static void subtract(int[] iArr, int[] iArr2, int[] iArr3) {
        if (Nat.sub(12, iArr, iArr2, iArr3) != 0) {
            subPInvFrom(iArr3);
        }
    }

    public static void subtractExt(int[] iArr, int[] iArr2, int[] iArr3) {
        if (Nat.sub(24, iArr, iArr2, iArr3) != 0) {
            int[] iArr4 = PExtInv;
            if (Nat.subFrom(iArr4.length, iArr4, iArr3) != 0) {
                Nat.decAt(24, iArr3, iArr4.length);
            }
        }
    }

    public static void twice(int[] iArr, int[] iArr2) {
        if (Nat.shiftUpBit(12, iArr, 0, iArr2) != 0 || (iArr2[11] == -1 && Nat.gte(12, iArr2, f14219a))) {
            addPInvTo(iArr2);
        }
    }
}
