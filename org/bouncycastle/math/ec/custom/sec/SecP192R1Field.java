package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.math.raw.Mod;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat192;
import org.bouncycastle.util.Pack;
/* loaded from: classes4.dex */
public class SecP192R1Field {
    private static final long M = 4294967295L;
    private static final int P5 = -1;
    private static final int PExt11 = -1;

    /* renamed from: a  reason: collision with root package name */
    static final int[] f14184a = {-1, -1, -2, -1, -1, -1};
    private static final int[] PExt = {1, 0, 2, 0, 1, 0, -2, -1, -3, -1, -1, -1};
    private static final int[] PExtInv = {-1, -1, -3, -1, -2, -1, 1, 0, 2};

    public static void add(int[] iArr, int[] iArr2, int[] iArr3) {
        if (Nat192.add(iArr, iArr2, iArr3) != 0 || (iArr3[5] == -1 && Nat192.gte(iArr3, f14184a))) {
            addPInvTo(iArr3);
        }
    }

    public static void addExt(int[] iArr, int[] iArr2, int[] iArr3) {
        if (Nat.add(12, iArr, iArr2, iArr3) != 0 || (iArr3[11] == -1 && Nat.gte(12, iArr3, PExt))) {
            int[] iArr4 = PExtInv;
            if (Nat.addTo(iArr4.length, iArr4, iArr3) != 0) {
                Nat.incAt(12, iArr3, iArr4.length);
            }
        }
    }

    public static void addOne(int[] iArr, int[] iArr2) {
        if (Nat.inc(6, iArr, iArr2) != 0 || (iArr2[5] == -1 && Nat192.gte(iArr2, f14184a))) {
            addPInvTo(iArr2);
        }
    }

    private static void addPInvTo(int[] iArr) {
        long j2 = (iArr[0] & 4294967295L) + 1;
        iArr[0] = (int) j2;
        long j3 = j2 >> 32;
        if (j3 != 0) {
            long j4 = j3 + (iArr[1] & 4294967295L);
            iArr[1] = (int) j4;
            j3 = j4 >> 32;
        }
        long j5 = j3 + (4294967295L & iArr[2]) + 1;
        iArr[2] = (int) j5;
        if ((j5 >> 32) != 0) {
            Nat.incAt(6, iArr, 3);
        }
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        int[] fromBigInteger = Nat192.fromBigInteger(bigInteger);
        if (fromBigInteger[5] == -1) {
            int[] iArr = f14184a;
            if (Nat192.gte(fromBigInteger, iArr)) {
                Nat192.subFrom(iArr, fromBigInteger);
            }
        }
        return fromBigInteger;
    }

    public static void half(int[] iArr, int[] iArr2) {
        if ((iArr[0] & 1) == 0) {
            Nat.shiftDownBit(6, iArr, 0, iArr2);
        } else {
            Nat.shiftDownBit(6, iArr2, Nat192.add(iArr, f14184a, iArr2));
        }
    }

    public static void inv(int[] iArr, int[] iArr2) {
        Mod.checkedModOddInverse(f14184a, iArr, iArr2);
    }

    public static int isZero(int[] iArr) {
        int i2 = 0;
        for (int i3 = 0; i3 < 6; i3++) {
            i2 |= iArr[i3];
        }
        return (((i2 >>> 1) | (i2 & 1)) - 1) >> 31;
    }

    public static void multiply(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] createExt = Nat192.createExt();
        Nat192.mul(iArr, iArr2, createExt);
        reduce(createExt, iArr3);
    }

    public static void multiplyAddToExt(int[] iArr, int[] iArr2, int[] iArr3) {
        if (Nat192.mulAddTo(iArr, iArr2, iArr3) != 0 || (iArr3[11] == -1 && Nat.gte(12, iArr3, PExt))) {
            int[] iArr4 = PExtInv;
            if (Nat.addTo(iArr4.length, iArr4, iArr3) != 0) {
                Nat.incAt(12, iArr3, iArr4.length);
            }
        }
    }

    public static void negate(int[] iArr, int[] iArr2) {
        if (isZero(iArr) == 0) {
            Nat192.sub(f14184a, iArr, iArr2);
            return;
        }
        int[] iArr3 = f14184a;
        Nat192.sub(iArr3, iArr3, iArr2);
    }

    public static void random(SecureRandom secureRandom, int[] iArr) {
        byte[] bArr = new byte[24];
        do {
            secureRandom.nextBytes(bArr);
            Pack.littleEndianToInt(bArr, 0, iArr, 0, 6);
        } while (Nat.lessThan(6, iArr, f14184a) == 0);
    }

    public static void randomMult(SecureRandom secureRandom, int[] iArr) {
        do {
            random(secureRandom, iArr);
        } while (isZero(iArr) != 0);
    }

    public static void reduce(int[] iArr, int[] iArr2) {
        long j2 = iArr[6] & 4294967295L;
        long j3 = iArr[7] & 4294967295L;
        long j4 = (iArr[10] & 4294967295L) + j2;
        long j5 = (iArr[11] & 4294967295L) + j3;
        long j6 = (iArr[0] & 4294967295L) + j4 + 0;
        int i2 = (int) j6;
        long j7 = (j6 >> 32) + (iArr[1] & 4294967295L) + j5;
        iArr2[1] = (int) j7;
        long j8 = j4 + (iArr[8] & 4294967295L);
        long j9 = j5 + (iArr[9] & 4294967295L);
        long j10 = (j7 >> 32) + (iArr[2] & 4294967295L) + j8;
        long j11 = j10 & 4294967295L;
        long j12 = (j10 >> 32) + (iArr[3] & 4294967295L) + j9;
        iArr2[3] = (int) j12;
        long j13 = (j12 >> 32) + (iArr[4] & 4294967295L) + (j8 - j2);
        iArr2[4] = (int) j13;
        long j14 = (j13 >> 32) + (iArr[5] & 4294967295L) + (j9 - j3);
        iArr2[5] = (int) j14;
        long j15 = j14 >> 32;
        long j16 = j11 + j15;
        long j17 = j15 + (i2 & 4294967295L);
        iArr2[0] = (int) j17;
        long j18 = j17 >> 32;
        if (j18 != 0) {
            long j19 = j18 + (4294967295L & iArr2[1]);
            iArr2[1] = (int) j19;
            j16 += j19 >> 32;
        }
        iArr2[2] = (int) j16;
        if (((j16 >> 32) == 0 || Nat.incAt(6, iArr2, 3) == 0) && !(iArr2[5] == -1 && Nat192.gte(iArr2, f14184a))) {
            return;
        }
        addPInvTo(iArr2);
    }

    public static void reduce32(int i2, int[] iArr) {
        long j2;
        if (i2 != 0) {
            long j3 = i2 & 4294967295L;
            long j4 = (iArr[0] & 4294967295L) + j3 + 0;
            iArr[0] = (int) j4;
            long j5 = j4 >> 32;
            if (j5 != 0) {
                long j6 = j5 + (iArr[1] & 4294967295L);
                iArr[1] = (int) j6;
                j5 = j6 >> 32;
            }
            long j7 = j5 + (4294967295L & iArr[2]) + j3;
            iArr[2] = (int) j7;
            j2 = j7 >> 32;
        } else {
            j2 = 0;
        }
        if ((j2 == 0 || Nat.incAt(6, iArr, 3) == 0) && !(iArr[5] == -1 && Nat192.gte(iArr, f14184a))) {
            return;
        }
        addPInvTo(iArr);
    }

    public static void square(int[] iArr, int[] iArr2) {
        int[] createExt = Nat192.createExt();
        Nat192.square(iArr, createExt);
        reduce(createExt, iArr2);
    }

    public static void squareN(int[] iArr, int i2, int[] iArr2) {
        int[] createExt = Nat192.createExt();
        Nat192.square(iArr, createExt);
        while (true) {
            reduce(createExt, iArr2);
            i2--;
            if (i2 <= 0) {
                return;
            }
            Nat192.square(iArr2, createExt);
        }
    }

    private static void subPInvFrom(int[] iArr) {
        long j2 = (iArr[0] & 4294967295L) - 1;
        iArr[0] = (int) j2;
        long j3 = j2 >> 32;
        if (j3 != 0) {
            long j4 = j3 + (iArr[1] & 4294967295L);
            iArr[1] = (int) j4;
            j3 = j4 >> 32;
        }
        long j5 = j3 + ((4294967295L & iArr[2]) - 1);
        iArr[2] = (int) j5;
        if ((j5 >> 32) != 0) {
            Nat.decAt(6, iArr, 3);
        }
    }

    public static void subtract(int[] iArr, int[] iArr2, int[] iArr3) {
        if (Nat192.sub(iArr, iArr2, iArr3) != 0) {
            subPInvFrom(iArr3);
        }
    }

    public static void subtractExt(int[] iArr, int[] iArr2, int[] iArr3) {
        if (Nat.sub(12, iArr, iArr2, iArr3) != 0) {
            int[] iArr4 = PExtInv;
            if (Nat.subFrom(iArr4.length, iArr4, iArr3) != 0) {
                Nat.decAt(12, iArr3, iArr4.length);
            }
        }
    }

    public static void twice(int[] iArr, int[] iArr2) {
        if (Nat.shiftUpBit(6, iArr, 0, iArr2) != 0 || (iArr2[5] == -1 && Nat192.gte(iArr2, f14184a))) {
            addPInvTo(iArr2);
        }
    }
}
