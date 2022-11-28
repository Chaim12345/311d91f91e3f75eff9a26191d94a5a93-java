package org.bouncycastle.math.raw;

import java.math.BigInteger;
import org.bouncycastle.util.Pack;
/* loaded from: classes4.dex */
public abstract class Nat448 {
    public static void copy64(long[] jArr, int i2, long[] jArr2, int i3) {
        jArr2[i3 + 0] = jArr[i2 + 0];
        jArr2[i3 + 1] = jArr[i2 + 1];
        jArr2[i3 + 2] = jArr[i2 + 2];
        jArr2[i3 + 3] = jArr[i2 + 3];
        jArr2[i3 + 4] = jArr[i2 + 4];
        jArr2[i3 + 5] = jArr[i2 + 5];
        jArr2[i3 + 6] = jArr[i2 + 6];
    }

    public static void copy64(long[] jArr, long[] jArr2) {
        jArr2[0] = jArr[0];
        jArr2[1] = jArr[1];
        jArr2[2] = jArr[2];
        jArr2[3] = jArr[3];
        jArr2[4] = jArr[4];
        jArr2[5] = jArr[5];
        jArr2[6] = jArr[6];
    }

    public static long[] create64() {
        return new long[7];
    }

    public static long[] createExt64() {
        return new long[14];
    }

    public static boolean eq64(long[] jArr, long[] jArr2) {
        for (int i2 = 6; i2 >= 0; i2--) {
            if (jArr[i2] != jArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public static long[] fromBigInteger64(BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > 448) {
            throw new IllegalArgumentException();
        }
        long[] create64 = create64();
        for (int i2 = 0; i2 < 7; i2++) {
            create64[i2] = bigInteger.longValue();
            bigInteger = bigInteger.shiftRight(64);
        }
        return create64;
    }

    public static boolean isOne64(long[] jArr) {
        if (jArr[0] != 1) {
            return false;
        }
        for (int i2 = 1; i2 < 7; i2++) {
            if (jArr[i2] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isZero64(long[] jArr) {
        for (int i2 = 0; i2 < 7; i2++) {
            if (jArr[i2] != 0) {
                return false;
            }
        }
        return true;
    }

    public static void mul(int[] iArr, int[] iArr2, int[] iArr3) {
        Nat224.mul(iArr, iArr2, iArr3);
        Nat224.mul(iArr, 7, iArr2, 7, iArr3, 14);
        int addToEachOther = Nat224.addToEachOther(iArr3, 7, iArr3, 14);
        int addTo = addToEachOther + Nat224.addTo(iArr3, 21, iArr3, 14, Nat224.addTo(iArr3, 0, iArr3, 7, 0) + addToEachOther);
        int[] create = Nat224.create();
        int[] create2 = Nat224.create();
        boolean z = Nat224.diff(iArr, 7, iArr, 0, create, 0) != Nat224.diff(iArr2, 7, iArr2, 0, create2, 0);
        int[] createExt = Nat224.createExt();
        Nat224.mul(create, create2, createExt);
        Nat.addWordAt(28, addTo + (z ? Nat.addTo(14, createExt, 0, iArr3, 7) : Nat.subFrom(14, createExt, 0, iArr3, 7)), iArr3, 21);
    }

    public static void square(int[] iArr, int[] iArr2) {
        Nat224.square(iArr, iArr2);
        Nat224.square(iArr, 7, iArr2, 14);
        int addToEachOther = Nat224.addToEachOther(iArr2, 7, iArr2, 14);
        int addTo = addToEachOther + Nat224.addTo(iArr2, 21, iArr2, 14, Nat224.addTo(iArr2, 0, iArr2, 7, 0) + addToEachOther);
        int[] create = Nat224.create();
        Nat224.diff(iArr, 7, iArr, 0, create, 0);
        int[] createExt = Nat224.createExt();
        Nat224.square(create, createExt);
        Nat.addWordAt(28, addTo + Nat.subFrom(14, createExt, 0, iArr2, 7), iArr2, 21);
    }

    public static BigInteger toBigInteger64(long[] jArr) {
        byte[] bArr = new byte[56];
        for (int i2 = 0; i2 < 7; i2++) {
            long j2 = jArr[i2];
            if (j2 != 0) {
                Pack.longToBigEndian(j2, bArr, (6 - i2) << 3);
            }
        }
        return new BigInteger(1, bArr);
    }
}
