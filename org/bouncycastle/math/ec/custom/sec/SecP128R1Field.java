package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.math.raw.Mod;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat128;
import org.bouncycastle.math.raw.Nat256;
import org.bouncycastle.util.Pack;
/* loaded from: classes4.dex */
public class SecP128R1Field {
    private static final long M = 4294967295L;
    private static final int P3s1 = 2147483646;
    private static final int PExt7s1 = 2147483646;

    /* renamed from: a  reason: collision with root package name */
    static final int[] f14151a = {-1, -1, -1, -3};
    private static final int[] PExt = {1, 0, 0, 4, -2, -1, 3, -4};
    private static final int[] PExtInv = {-1, -1, -1, -5, 1, 0, -4, 3};

    public static void add(int[] iArr, int[] iArr2, int[] iArr3) {
        if (Nat128.add(iArr, iArr2, iArr3) != 0 || ((iArr3[3] >>> 1) >= 2147483646 && Nat128.gte(iArr3, f14151a))) {
            addPInvTo(iArr3);
        }
    }

    public static void addExt(int[] iArr, int[] iArr2, int[] iArr3) {
        if (Nat256.add(iArr, iArr2, iArr3) != 0 || ((iArr3[7] >>> 1) >= 2147483646 && Nat256.gte(iArr3, PExt))) {
            int[] iArr4 = PExtInv;
            Nat.addTo(iArr4.length, iArr4, iArr3);
        }
    }

    public static void addOne(int[] iArr, int[] iArr2) {
        if (Nat.inc(4, iArr, iArr2) != 0 || ((iArr2[3] >>> 1) >= 2147483646 && Nat128.gte(iArr2, f14151a))) {
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
            long j5 = (j4 >> 32) + (iArr[2] & 4294967295L);
            iArr[2] = (int) j5;
            j3 = j5 >> 32;
        }
        iArr[3] = (int) (j3 + (4294967295L & iArr[3]) + 2);
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        int[] fromBigInteger = Nat128.fromBigInteger(bigInteger);
        if ((fromBigInteger[3] >>> 1) >= 2147483646) {
            int[] iArr = f14151a;
            if (Nat128.gte(fromBigInteger, iArr)) {
                Nat128.subFrom(iArr, fromBigInteger);
            }
        }
        return fromBigInteger;
    }

    public static void half(int[] iArr, int[] iArr2) {
        if ((iArr[0] & 1) == 0) {
            Nat.shiftDownBit(4, iArr, 0, iArr2);
        } else {
            Nat.shiftDownBit(4, iArr2, Nat128.add(iArr, f14151a, iArr2));
        }
    }

    public static void inv(int[] iArr, int[] iArr2) {
        Mod.checkedModOddInverse(f14151a, iArr, iArr2);
    }

    public static int isZero(int[] iArr) {
        int i2 = 0;
        for (int i3 = 0; i3 < 4; i3++) {
            i2 |= iArr[i3];
        }
        return (((i2 >>> 1) | (i2 & 1)) - 1) >> 31;
    }

    public static void multiply(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] createExt = Nat128.createExt();
        Nat128.mul(iArr, iArr2, createExt);
        reduce(createExt, iArr3);
    }

    public static void multiplyAddToExt(int[] iArr, int[] iArr2, int[] iArr3) {
        if (Nat128.mulAddTo(iArr, iArr2, iArr3) != 0 || ((iArr3[7] >>> 1) >= 2147483646 && Nat256.gte(iArr3, PExt))) {
            int[] iArr4 = PExtInv;
            Nat.addTo(iArr4.length, iArr4, iArr3);
        }
    }

    public static void negate(int[] iArr, int[] iArr2) {
        if (isZero(iArr) == 0) {
            Nat128.sub(f14151a, iArr, iArr2);
            return;
        }
        int[] iArr3 = f14151a;
        Nat128.sub(iArr3, iArr3, iArr2);
    }

    public static void random(SecureRandom secureRandom, int[] iArr) {
        byte[] bArr = new byte[16];
        do {
            secureRandom.nextBytes(bArr);
            Pack.littleEndianToInt(bArr, 0, iArr, 0, 4);
        } while (Nat.lessThan(4, iArr, f14151a) == 0);
    }

    public static void randomMult(SecureRandom secureRandom, int[] iArr) {
        do {
            random(secureRandom, iArr);
        } while (isZero(iArr) != 0);
    }

    public static void reduce(int[] iArr, int[] iArr2) {
        long j2 = iArr[7] & 4294967295L;
        long j3 = (iArr[3] & 4294967295L) + j2;
        long j4 = (iArr[6] & 4294967295L) + (j2 << 1);
        long j5 = (iArr[2] & 4294967295L) + j4;
        long j6 = (iArr[5] & 4294967295L) + (j4 << 1);
        long j7 = (iArr[1] & 4294967295L) + j6;
        long j8 = (iArr[4] & 4294967295L) + (j6 << 1);
        long j9 = (iArr[0] & 4294967295L) + j8;
        iArr2[0] = (int) j9;
        long j10 = j7 + (j9 >>> 32);
        iArr2[1] = (int) j10;
        long j11 = j5 + (j10 >>> 32);
        iArr2[2] = (int) j11;
        long j12 = j3 + (j8 << 1) + (j11 >>> 32);
        iArr2[3] = (int) j12;
        reduce32((int) (j12 >>> 32), iArr2);
    }

    public static void reduce32(int i2, int[] iArr) {
        while (i2 != 0) {
            long j2 = i2 & 4294967295L;
            long j3 = (iArr[0] & 4294967295L) + j2;
            iArr[0] = (int) j3;
            long j4 = j3 >> 32;
            if (j4 != 0) {
                long j5 = j4 + (iArr[1] & 4294967295L);
                iArr[1] = (int) j5;
                long j6 = (j5 >> 32) + (iArr[2] & 4294967295L);
                iArr[2] = (int) j6;
                j4 = j6 >> 32;
            }
            long j7 = j4 + (4294967295L & iArr[3]) + (j2 << 1);
            iArr[3] = (int) j7;
            i2 = (int) (j7 >> 32);
        }
        if ((iArr[3] >>> 1) < 2147483646 || !Nat128.gte(iArr, f14151a)) {
            return;
        }
        addPInvTo(iArr);
    }

    public static void square(int[] iArr, int[] iArr2) {
        int[] createExt = Nat128.createExt();
        Nat128.square(iArr, createExt);
        reduce(createExt, iArr2);
    }

    public static void squareN(int[] iArr, int i2, int[] iArr2) {
        int[] createExt = Nat128.createExt();
        Nat128.square(iArr, createExt);
        while (true) {
            reduce(createExt, iArr2);
            i2--;
            if (i2 <= 0) {
                return;
            }
            Nat128.square(iArr2, createExt);
        }
    }

    private static void subPInvFrom(int[] iArr) {
        long j2 = (iArr[0] & 4294967295L) - 1;
        iArr[0] = (int) j2;
        long j3 = j2 >> 32;
        if (j3 != 0) {
            long j4 = j3 + (iArr[1] & 4294967295L);
            iArr[1] = (int) j4;
            long j5 = (j4 >> 32) + (iArr[2] & 4294967295L);
            iArr[2] = (int) j5;
            j3 = j5 >> 32;
        }
        iArr[3] = (int) (j3 + ((4294967295L & iArr[3]) - 2));
    }

    public static void subtract(int[] iArr, int[] iArr2, int[] iArr3) {
        if (Nat128.sub(iArr, iArr2, iArr3) != 0) {
            subPInvFrom(iArr3);
        }
    }

    public static void subtractExt(int[] iArr, int[] iArr2, int[] iArr3) {
        if (Nat.sub(10, iArr, iArr2, iArr3) != 0) {
            int[] iArr4 = PExtInv;
            Nat.subFrom(iArr4.length, iArr4, iArr3);
        }
    }

    public static void twice(int[] iArr, int[] iArr2) {
        if (Nat.shiftUpBit(4, iArr, 0, iArr2) != 0 || ((iArr2[3] >>> 1) >= 2147483646 && Nat128.gte(iArr2, f14151a))) {
            addPInvTo(iArr2);
        }
    }
}
