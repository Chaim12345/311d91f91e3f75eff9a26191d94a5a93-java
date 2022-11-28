package org.bouncycastle.math.ec.custom.djb;

import java.math.BigInteger;
import java.security.SecureRandom;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import org.bouncycastle.math.raw.Mod;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat256;
import org.bouncycastle.util.Pack;
/* loaded from: classes3.dex */
public class Curve25519Field {
    private static final long M = 4294967295L;
    private static final int P7 = Integer.MAX_VALUE;
    private static final int PInv = 19;

    /* renamed from: a  reason: collision with root package name */
    static final int[] f14137a = {-19, -1, -1, -1, -1, -1, -1, Integer.MAX_VALUE};
    private static final int[] PExt = {361, 0, 0, 0, 0, 0, 0, 0, -19, -1, -1, -1, -1, -1, -1, LockFreeTaskQueueCore.MAX_CAPACITY_MASK};

    public static void add(int[] iArr, int[] iArr2, int[] iArr3) {
        Nat256.add(iArr, iArr2, iArr3);
        if (Nat256.gte(iArr3, f14137a)) {
            subPFrom(iArr3);
        }
    }

    public static void addExt(int[] iArr, int[] iArr2, int[] iArr3) {
        Nat.add(16, iArr, iArr2, iArr3);
        if (Nat.gte(16, iArr3, PExt)) {
            subPExtFrom(iArr3);
        }
    }

    public static void addOne(int[] iArr, int[] iArr2) {
        Nat.inc(8, iArr, iArr2);
        if (Nat256.gte(iArr2, f14137a)) {
            subPFrom(iArr2);
        }
    }

    private static int addPExtTo(int[] iArr) {
        int[] iArr2 = PExt;
        long j2 = (iArr[0] & 4294967295L) + (iArr2[0] & 4294967295L);
        iArr[0] = (int) j2;
        long j3 = j2 >> 32;
        if (j3 != 0) {
            j3 = Nat.incAt(8, iArr, 1);
        }
        long j4 = j3 + ((iArr[8] & 4294967295L) - 19);
        iArr[8] = (int) j4;
        long j5 = j4 >> 32;
        if (j5 != 0) {
            j5 = Nat.decAt(15, iArr, 9);
        }
        long j6 = j5 + (iArr[15] & 4294967295L) + (4294967295L & (iArr2[15] + 1));
        iArr[15] = (int) j6;
        return (int) (j6 >> 32);
    }

    private static int addPTo(int[] iArr) {
        long j2 = (iArr[0] & 4294967295L) - 19;
        iArr[0] = (int) j2;
        long j3 = j2 >> 32;
        if (j3 != 0) {
            j3 = Nat.decAt(7, iArr, 1);
        }
        long j4 = j3 + (4294967295L & iArr[7]) + 2147483648L;
        iArr[7] = (int) j4;
        return (int) (j4 >> 32);
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        int[] fromBigInteger = Nat256.fromBigInteger(bigInteger);
        while (true) {
            int[] iArr = f14137a;
            if (!Nat256.gte(fromBigInteger, iArr)) {
                return fromBigInteger;
            }
            Nat256.subFrom(iArr, fromBigInteger);
        }
    }

    public static void half(int[] iArr, int[] iArr2) {
        if ((iArr[0] & 1) == 0) {
            Nat.shiftDownBit(8, iArr, 0, iArr2);
            return;
        }
        Nat256.add(iArr, f14137a, iArr2);
        Nat.shiftDownBit(8, iArr2, 0);
    }

    public static void inv(int[] iArr, int[] iArr2) {
        Mod.checkedModOddInverse(f14137a, iArr, iArr2);
    }

    public static int isZero(int[] iArr) {
        int i2 = 0;
        for (int i3 = 0; i3 < 8; i3++) {
            i2 |= iArr[i3];
        }
        return (((i2 >>> 1) | (i2 & 1)) - 1) >> 31;
    }

    public static void multiply(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] createExt = Nat256.createExt();
        Nat256.mul(iArr, iArr2, createExt);
        reduce(createExt, iArr3);
    }

    public static void multiplyAddToExt(int[] iArr, int[] iArr2, int[] iArr3) {
        Nat256.mulAddTo(iArr, iArr2, iArr3);
        if (Nat.gte(16, iArr3, PExt)) {
            subPExtFrom(iArr3);
        }
    }

    public static void negate(int[] iArr, int[] iArr2) {
        if (isZero(iArr) == 0) {
            Nat256.sub(f14137a, iArr, iArr2);
            return;
        }
        int[] iArr3 = f14137a;
        Nat256.sub(iArr3, iArr3, iArr2);
    }

    public static void random(SecureRandom secureRandom, int[] iArr) {
        byte[] bArr = new byte[32];
        do {
            secureRandom.nextBytes(bArr);
            Pack.littleEndianToInt(bArr, 0, iArr, 0, 8);
            iArr[7] = iArr[7] & Integer.MAX_VALUE;
        } while (Nat.lessThan(8, iArr, f14137a) == 0);
    }

    public static void randomMult(SecureRandom secureRandom, int[] iArr) {
        do {
            random(secureRandom, iArr);
        } while (isZero(iArr) != 0);
    }

    public static void reduce(int[] iArr, int[] iArr2) {
        int i2 = iArr[7];
        Nat.shiftUpBit(8, iArr, 8, i2, iArr2, 0);
        int i3 = iArr2[7];
        iArr2[7] = (i3 & Integer.MAX_VALUE) + Nat.addWordTo(7, ((Nat256.mulByWordAddTo(19, iArr, iArr2) << 1) + ((i3 >>> 31) - (i2 >>> 31))) * 19, iArr2);
        if (Nat256.gte(iArr2, f14137a)) {
            subPFrom(iArr2);
        }
    }

    public static void reduce27(int i2, int[] iArr) {
        int i3 = iArr[7];
        iArr[7] = (i3 & Integer.MAX_VALUE) + Nat.addWordTo(7, ((i2 << 1) | (i3 >>> 31)) * 19, iArr);
        if (Nat256.gte(iArr, f14137a)) {
            subPFrom(iArr);
        }
    }

    public static void square(int[] iArr, int[] iArr2) {
        int[] createExt = Nat256.createExt();
        Nat256.square(iArr, createExt);
        reduce(createExt, iArr2);
    }

    public static void squareN(int[] iArr, int i2, int[] iArr2) {
        int[] createExt = Nat256.createExt();
        Nat256.square(iArr, createExt);
        while (true) {
            reduce(createExt, iArr2);
            i2--;
            if (i2 <= 0) {
                return;
            }
            Nat256.square(iArr2, createExt);
        }
    }

    private static int subPExtFrom(int[] iArr) {
        int[] iArr2 = PExt;
        long j2 = (iArr[0] & 4294967295L) - (iArr2[0] & 4294967295L);
        iArr[0] = (int) j2;
        long j3 = j2 >> 32;
        if (j3 != 0) {
            j3 = Nat.decAt(8, iArr, 1);
        }
        long j4 = j3 + (iArr[8] & 4294967295L) + 19;
        iArr[8] = (int) j4;
        long j5 = j4 >> 32;
        if (j5 != 0) {
            j5 = Nat.incAt(15, iArr, 9);
        }
        long j6 = j5 + ((iArr[15] & 4294967295L) - (4294967295L & (iArr2[15] + 1)));
        iArr[15] = (int) j6;
        return (int) (j6 >> 32);
    }

    private static int subPFrom(int[] iArr) {
        long j2 = (iArr[0] & 4294967295L) + 19;
        iArr[0] = (int) j2;
        long j3 = j2 >> 32;
        if (j3 != 0) {
            j3 = Nat.incAt(7, iArr, 1);
        }
        long j4 = j3 + ((4294967295L & iArr[7]) - 2147483648L);
        iArr[7] = (int) j4;
        return (int) (j4 >> 32);
    }

    public static void subtract(int[] iArr, int[] iArr2, int[] iArr3) {
        if (Nat256.sub(iArr, iArr2, iArr3) != 0) {
            addPTo(iArr3);
        }
    }

    public static void subtractExt(int[] iArr, int[] iArr2, int[] iArr3) {
        if (Nat.sub(16, iArr, iArr2, iArr3) != 0) {
            addPExtTo(iArr3);
        }
    }

    public static void twice(int[] iArr, int[] iArr2) {
        Nat.shiftUpBit(8, iArr, 0, iArr2);
        if (Nat256.gte(iArr2, f14137a)) {
            subPFrom(iArr2);
        }
    }
}
