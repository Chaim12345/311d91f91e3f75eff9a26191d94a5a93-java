package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.bouncycastle.math.raw.Interleave;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat192;
/* loaded from: classes4.dex */
public class SecT131Field {
    private static final long M03 = 7;
    private static final long M44 = 17592186044415L;
    private static final long[] ROOT_Z = {2791191049453778211L, 2791191049453778402L, 6};

    protected static void a(long[] jArr) {
        long j2 = jArr[0];
        long j3 = jArr[1];
        long j4 = jArr[2];
        long j5 = jArr[3];
        long j6 = jArr[4];
        long j7 = jArr[5];
        jArr[0] = j2 ^ (j3 << 44);
        jArr[1] = (j3 >>> 20) ^ (j4 << 24);
        jArr[2] = ((j4 >>> 40) ^ (j5 << 4)) ^ (j6 << 48);
        jArr[3] = ((j5 >>> 60) ^ (j7 << 28)) ^ (j6 >>> 16);
        jArr[4] = j7 >>> 36;
        jArr[5] = 0;
    }

    public static void add(long[] jArr, long[] jArr2, long[] jArr3) {
        jArr3[0] = jArr[0] ^ jArr2[0];
        jArr3[1] = jArr[1] ^ jArr2[1];
        jArr3[2] = jArr2[2] ^ jArr[2];
    }

    public static void addExt(long[] jArr, long[] jArr2, long[] jArr3) {
        jArr3[0] = jArr[0] ^ jArr2[0];
        jArr3[1] = jArr[1] ^ jArr2[1];
        jArr3[2] = jArr[2] ^ jArr2[2];
        jArr3[3] = jArr[3] ^ jArr2[3];
        jArr3[4] = jArr2[4] ^ jArr[4];
    }

    public static void addOne(long[] jArr, long[] jArr2) {
        jArr2[0] = jArr[0] ^ 1;
        jArr2[1] = jArr[1];
        jArr2[2] = jArr[2];
    }

    private static void addTo(long[] jArr, long[] jArr2) {
        jArr2[0] = jArr2[0] ^ jArr[0];
        jArr2[1] = jArr2[1] ^ jArr[1];
        jArr2[2] = jArr2[2] ^ jArr[2];
    }

    protected static void b(long[] jArr, long[] jArr2, long[] jArr3) {
        long j2 = jArr[0];
        long j3 = jArr[1];
        long j4 = ((jArr[2] << 40) ^ (j3 >>> 24)) & M44;
        long j5 = ((j3 << 20) ^ (j2 >>> 44)) & M44;
        long j6 = j2 & M44;
        long j7 = jArr2[0];
        long j8 = jArr2[1];
        long j9 = ((j8 >>> 24) ^ (jArr2[2] << 40)) & M44;
        long j10 = ((j8 << 20) ^ (j7 >>> 44)) & M44;
        long j11 = j7 & M44;
        long[] jArr4 = new long[10];
        c(jArr3, j6, j11, jArr4, 0);
        c(jArr3, j4, j9, jArr4, 2);
        long j12 = (j6 ^ j5) ^ j4;
        long j13 = (j11 ^ j10) ^ j9;
        c(jArr3, j12, j13, jArr4, 4);
        long j14 = (j5 << 1) ^ (j4 << 2);
        long j15 = (j10 << 1) ^ (j9 << 2);
        c(jArr3, j6 ^ j14, j11 ^ j15, jArr4, 6);
        c(jArr3, j12 ^ j14, j13 ^ j15, jArr4, 8);
        long j16 = jArr4[6] ^ jArr4[8];
        long j17 = jArr4[7] ^ jArr4[9];
        long j18 = (j16 << 1) ^ jArr4[6];
        long j19 = (j16 ^ (j17 << 1)) ^ jArr4[7];
        long j20 = jArr4[0];
        long j21 = (jArr4[1] ^ jArr4[0]) ^ jArr4[4];
        long j22 = jArr4[1] ^ jArr4[5];
        long j23 = ((j20 ^ j18) ^ (jArr4[2] << 4)) ^ (jArr4[2] << 1);
        long j24 = (((j21 ^ j19) ^ (jArr4[3] << 4)) ^ (jArr4[3] << 1)) ^ (j23 >>> 44);
        long j25 = j23 & M44;
        long j26 = (j22 ^ j17) ^ (j24 >>> 44);
        long j27 = j24 & M44;
        long j28 = (j25 >>> 1) ^ ((j27 & 1) << 43);
        long j29 = j28 ^ (j28 << 1);
        long j30 = j29 ^ (j29 << 2);
        long j31 = j30 ^ (j30 << 4);
        long j32 = j31 ^ (j31 << 8);
        long j33 = j32 ^ (j32 << 16);
        long j34 = (j33 ^ (j33 << 32)) & M44;
        long j35 = ((j27 >>> 1) ^ ((j26 & 1) << 43)) ^ (j34 >>> 43);
        long j36 = j35 ^ (j35 << 1);
        long j37 = j36 ^ (j36 << 2);
        long j38 = j37 ^ (j37 << 4);
        long j39 = j38 ^ (j38 << 8);
        long j40 = j39 ^ (j39 << 16);
        long j41 = (j40 ^ (j40 << 32)) & M44;
        long j42 = (j26 >>> 1) ^ (j41 >>> 43);
        long j43 = j42 ^ (j42 << 1);
        long j44 = j43 ^ (j43 << 2);
        long j45 = j44 ^ (j44 << 4);
        long j46 = j45 ^ (j45 << 8);
        long j47 = j46 ^ (j46 << 16);
        long j48 = j47 ^ (j47 << 32);
        jArr3[0] = j20;
        jArr3[1] = (j21 ^ j34) ^ jArr4[2];
        jArr3[2] = ((j22 ^ j41) ^ j34) ^ jArr4[3];
        jArr3[3] = j41 ^ j48;
        jArr3[4] = jArr4[2] ^ j48;
        jArr3[5] = jArr4[3];
        a(jArr3);
    }

    protected static void c(long[] jArr, long j2, long j3, long[] jArr2, int i2) {
        jArr[1] = j3;
        jArr[2] = jArr[1] << 1;
        jArr[3] = jArr[2] ^ j3;
        jArr[4] = jArr[2] << 1;
        jArr[5] = jArr[4] ^ j3;
        jArr[6] = jArr[3] << 1;
        jArr[7] = jArr[6] ^ j3;
        int i3 = (int) j2;
        long j4 = (((jArr[i3 & 7] ^ (jArr[(i3 >>> 3) & 7] << 3)) ^ (jArr[(i3 >>> 6) & 7] << 6)) ^ (jArr[(i3 >>> 9) & 7] << 9)) ^ (jArr[(i3 >>> 12) & 7] << 12);
        long j5 = 0;
        int i4 = 30;
        do {
            int i5 = (int) (j2 >>> i4);
            long j6 = (((jArr[i5 & 7] ^ (jArr[(i5 >>> 3) & 7] << 3)) ^ (jArr[(i5 >>> 6) & 7] << 6)) ^ (jArr[(i5 >>> 9) & 7] << 9)) ^ (jArr[(i5 >>> 12) & 7] << 12);
            j4 ^= j6 << i4;
            j5 ^= j6 >>> (-i4);
            i4 -= 15;
        } while (i4 > 0);
        jArr2[i2] = M44 & j4;
        jArr2[i2 + 1] = (j4 >>> 44) ^ (j5 << 20);
    }

    protected static void d(long[] jArr, long[] jArr2) {
        Interleave.expand64To128(jArr, 0, 2, jArr2, 0);
        jArr2[4] = Interleave.expand8to16((int) jArr[2]) & BodyPartID.bodyIdMax;
    }

    public static long[] fromBigInteger(BigInteger bigInteger) {
        return Nat.fromBigInteger64(131, bigInteger);
    }

    public static void halfTrace(long[] jArr, long[] jArr2) {
        long[] create64 = Nat.create64(5);
        Nat192.copy64(jArr, jArr2);
        for (int i2 = 1; i2 < 131; i2 += 2) {
            d(jArr2, create64);
            reduce(create64, jArr2);
            d(jArr2, create64);
            reduce(create64, jArr2);
            addTo(jArr, jArr2);
        }
    }

    public static void invert(long[] jArr, long[] jArr2) {
        if (Nat192.isZero64(jArr)) {
            throw new IllegalStateException();
        }
        long[] create64 = Nat192.create64();
        long[] create642 = Nat192.create64();
        square(jArr, create64);
        multiply(create64, jArr, create64);
        squareN(create64, 2, create642);
        multiply(create642, create64, create642);
        squareN(create642, 4, create64);
        multiply(create64, create642, create64);
        squareN(create64, 8, create642);
        multiply(create642, create64, create642);
        squareN(create642, 16, create64);
        multiply(create64, create642, create64);
        squareN(create64, 32, create642);
        multiply(create642, create64, create642);
        square(create642, create642);
        multiply(create642, jArr, create642);
        squareN(create642, 65, create64);
        multiply(create64, create642, create64);
        square(create64, jArr2);
    }

    public static void multiply(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArr4 = new long[8];
        b(jArr, jArr2, jArr4);
        reduce(jArr4, jArr3);
    }

    public static void multiplyAddToExt(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArr4 = new long[8];
        b(jArr, jArr2, jArr4);
        addExt(jArr3, jArr4, jArr3);
    }

    public static void reduce(long[] jArr, long[] jArr2) {
        long j2 = jArr[0];
        long j3 = jArr[1];
        long j4 = jArr[2];
        long j5 = jArr[3];
        long j6 = jArr[4];
        long j7 = j5 ^ (j6 >>> 59);
        long j8 = j2 ^ ((j7 << 61) ^ (j7 << 63));
        long j9 = (j3 ^ ((j6 << 61) ^ (j6 << 63))) ^ ((((j7 >>> 3) ^ (j7 >>> 1)) ^ j7) ^ (j7 << 5));
        long j10 = (j4 ^ ((((j6 >>> 3) ^ (j6 >>> 1)) ^ j6) ^ (j6 << 5))) ^ (j7 >>> 59);
        long j11 = j10 >>> 3;
        jArr2[0] = (((j8 ^ j11) ^ (j11 << 2)) ^ (j11 << 3)) ^ (j11 << 8);
        jArr2[1] = (j11 >>> 56) ^ j9;
        jArr2[2] = M03 & j10;
    }

    public static void reduce61(long[] jArr, int i2) {
        int i3 = i2 + 2;
        long j2 = jArr[i3];
        long j3 = j2 >>> 3;
        jArr[i2] = jArr[i2] ^ ((((j3 << 2) ^ j3) ^ (j3 << 3)) ^ (j3 << 8));
        int i4 = i2 + 1;
        jArr[i4] = (j3 >>> 56) ^ jArr[i4];
        jArr[i3] = j2 & M03;
    }

    public static void sqrt(long[] jArr, long[] jArr2) {
        long[] create64 = Nat192.create64();
        long unshuffle = Interleave.unshuffle(jArr[0]);
        long unshuffle2 = Interleave.unshuffle(jArr[1]);
        create64[0] = (unshuffle >>> 32) | (unshuffle2 & (-4294967296L));
        long unshuffle3 = Interleave.unshuffle(jArr[2]);
        long j2 = unshuffle3 & BodyPartID.bodyIdMax;
        create64[1] = unshuffle3 >>> 32;
        multiply(create64, ROOT_Z, jArr2);
        jArr2[0] = jArr2[0] ^ ((unshuffle & BodyPartID.bodyIdMax) | (unshuffle2 << 32));
        jArr2[1] = jArr2[1] ^ j2;
    }

    public static void square(long[] jArr, long[] jArr2) {
        long[] create64 = Nat.create64(5);
        d(jArr, create64);
        reduce(create64, jArr2);
    }

    public static void squareAddToExt(long[] jArr, long[] jArr2) {
        long[] create64 = Nat.create64(5);
        d(jArr, create64);
        addExt(jArr2, create64, jArr2);
    }

    public static void squareN(long[] jArr, int i2, long[] jArr2) {
        long[] create64 = Nat.create64(5);
        d(jArr, create64);
        while (true) {
            reduce(create64, jArr2);
            i2--;
            if (i2 <= 0) {
                return;
            }
            d(jArr2, create64);
        }
    }

    public static int trace(long[] jArr) {
        return ((int) ((jArr[0] ^ (jArr[1] >>> 59)) ^ (jArr[2] >>> 1))) & 1;
    }
}
