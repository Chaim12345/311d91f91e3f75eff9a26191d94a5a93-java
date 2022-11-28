package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.math.raw.Mod;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat512;
import org.bouncycastle.util.Pack;
/* loaded from: classes4.dex */
public class SecP521R1Field {
    private static final int P16 = 511;

    /* renamed from: a  reason: collision with root package name */
    static final int[] f14226a = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 511};

    protected static void a(int[] iArr, int[] iArr2, int[] iArr3) {
        Nat512.mul(iArr, iArr2, iArr3);
        int i2 = iArr[16];
        int i3 = iArr2[16];
        iArr3[32] = Nat.mul31BothAdd(16, i2, iArr2, i3, iArr, iArr3, 16) + (i2 * i3);
    }

    public static void add(int[] iArr, int[] iArr2, int[] iArr3) {
        int add = Nat.add(16, iArr, iArr2, iArr3) + iArr[16] + iArr2[16];
        if (add > 511 || (add == 511 && Nat.eq(16, iArr3, f14226a))) {
            add = (add + Nat.inc(16, iArr3)) & 511;
        }
        iArr3[16] = add;
    }

    public static void addOne(int[] iArr, int[] iArr2) {
        int inc = Nat.inc(16, iArr, iArr2) + iArr[16];
        if (inc > 511 || (inc == 511 && Nat.eq(16, iArr2, f14226a))) {
            inc = (inc + Nat.inc(16, iArr2)) & 511;
        }
        iArr2[16] = inc;
    }

    protected static void b(int[] iArr, int[] iArr2) {
        Nat512.square(iArr, iArr2);
        int i2 = iArr[16];
        iArr2[32] = Nat.mulWordAddTo(16, i2 << 1, iArr, 0, iArr2, 16) + (i2 * i2);
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        int[] fromBigInteger = Nat.fromBigInteger(521, bigInteger);
        if (Nat.eq(17, fromBigInteger, f14226a)) {
            Nat.zero(17, fromBigInteger);
        }
        return fromBigInteger;
    }

    public static void half(int[] iArr, int[] iArr2) {
        int i2 = iArr[16];
        iArr2[16] = (Nat.shiftDownBit(16, iArr, i2, iArr2) >>> 23) | (i2 >>> 1);
    }

    public static void inv(int[] iArr, int[] iArr2) {
        Mod.checkedModOddInverse(f14226a, iArr, iArr2);
    }

    public static int isZero(int[] iArr) {
        int i2 = 0;
        for (int i3 = 0; i3 < 17; i3++) {
            i2 |= iArr[i3];
        }
        return (((i2 >>> 1) | (i2 & 1)) - 1) >> 31;
    }

    public static void multiply(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] create = Nat.create(33);
        a(iArr, iArr2, create);
        reduce(create, iArr3);
    }

    public static void negate(int[] iArr, int[] iArr2) {
        if (isZero(iArr) == 0) {
            Nat.sub(17, f14226a, iArr, iArr2);
            return;
        }
        int[] iArr3 = f14226a;
        Nat.sub(17, iArr3, iArr3, iArr2);
    }

    public static void random(SecureRandom secureRandom, int[] iArr) {
        byte[] bArr = new byte[68];
        do {
            secureRandom.nextBytes(bArr);
            Pack.littleEndianToInt(bArr, 0, iArr, 0, 17);
            iArr[16] = iArr[16] & 511;
        } while (Nat.lessThan(17, iArr, f14226a) == 0);
    }

    public static void randomMult(SecureRandom secureRandom, int[] iArr) {
        do {
            random(secureRandom, iArr);
        } while (isZero(iArr) != 0);
    }

    public static void reduce(int[] iArr, int[] iArr2) {
        int i2 = iArr[32];
        int shiftDownBits = (Nat.shiftDownBits(16, iArr, 16, 9, i2, iArr2, 0) >>> 23) + (i2 >>> 9) + Nat.addTo(16, iArr, iArr2);
        if (shiftDownBits > 511 || (shiftDownBits == 511 && Nat.eq(16, iArr2, f14226a))) {
            shiftDownBits = (shiftDownBits + Nat.inc(16, iArr2)) & 511;
        }
        iArr2[16] = shiftDownBits;
    }

    public static void reduce23(int[] iArr) {
        int i2 = iArr[16];
        int addWordTo = Nat.addWordTo(16, i2 >>> 9, iArr) + (i2 & 511);
        if (addWordTo > 511 || (addWordTo == 511 && Nat.eq(16, iArr, f14226a))) {
            addWordTo = (addWordTo + Nat.inc(16, iArr)) & 511;
        }
        iArr[16] = addWordTo;
    }

    public static void square(int[] iArr, int[] iArr2) {
        int[] create = Nat.create(33);
        b(iArr, create);
        reduce(create, iArr2);
    }

    public static void squareN(int[] iArr, int i2, int[] iArr2) {
        int[] create = Nat.create(33);
        b(iArr, create);
        while (true) {
            reduce(create, iArr2);
            i2--;
            if (i2 <= 0) {
                return;
            }
            b(iArr2, create);
        }
    }

    public static void subtract(int[] iArr, int[] iArr2, int[] iArr3) {
        int sub = (Nat.sub(16, iArr, iArr2, iArr3) + iArr[16]) - iArr2[16];
        if (sub < 0) {
            sub = (sub + Nat.dec(16, iArr3)) & 511;
        }
        iArr3[16] = sub;
    }

    public static void twice(int[] iArr, int[] iArr2) {
        int i2 = iArr[16];
        iArr2[16] = (Nat.shiftUpBit(16, iArr, i2 << 23, iArr2) | (i2 << 1)) & 511;
    }
}
