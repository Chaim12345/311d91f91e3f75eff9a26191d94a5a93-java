package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.bouncycastle.math.raw.Interleave;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat256;
import org.bouncycastle.tls.CipherSuite;
/* loaded from: classes4.dex */
public class SecT233Field {
    private static final long M41 = 2199023255551L;
    private static final long M59 = 576460752303423487L;

    protected static void a(long[] jArr) {
        long j2 = jArr[0];
        long j3 = jArr[1];
        long j4 = jArr[2];
        long j5 = jArr[3];
        long j6 = jArr[4];
        long j7 = jArr[5];
        long j8 = jArr[6];
        long j9 = jArr[7];
        jArr[0] = j2 ^ (j3 << 59);
        jArr[1] = (j3 >>> 5) ^ (j4 << 54);
        jArr[2] = (j4 >>> 10) ^ (j5 << 49);
        jArr[3] = (j5 >>> 15) ^ (j6 << 44);
        jArr[4] = (j6 >>> 20) ^ (j7 << 39);
        jArr[5] = (j7 >>> 25) ^ (j8 << 34);
        jArr[6] = (j8 >>> 30) ^ (j9 << 29);
        jArr[7] = j9 >>> 35;
    }

    public static void add(long[] jArr, long[] jArr2, long[] jArr3) {
        jArr3[0] = jArr[0] ^ jArr2[0];
        jArr3[1] = jArr[1] ^ jArr2[1];
        jArr3[2] = jArr[2] ^ jArr2[2];
        jArr3[3] = jArr2[3] ^ jArr[3];
    }

    public static void addExt(long[] jArr, long[] jArr2, long[] jArr3) {
        jArr3[0] = jArr[0] ^ jArr2[0];
        jArr3[1] = jArr[1] ^ jArr2[1];
        jArr3[2] = jArr[2] ^ jArr2[2];
        jArr3[3] = jArr[3] ^ jArr2[3];
        jArr3[4] = jArr[4] ^ jArr2[4];
        jArr3[5] = jArr[5] ^ jArr2[5];
        jArr3[6] = jArr[6] ^ jArr2[6];
        jArr3[7] = jArr2[7] ^ jArr[7];
    }

    public static void addOne(long[] jArr, long[] jArr2) {
        jArr2[0] = jArr[0] ^ 1;
        jArr2[1] = jArr[1];
        jArr2[2] = jArr[2];
        jArr2[3] = jArr[3];
    }

    private static void addTo(long[] jArr, long[] jArr2) {
        jArr2[0] = jArr2[0] ^ jArr[0];
        jArr2[1] = jArr2[1] ^ jArr[1];
        jArr2[2] = jArr2[2] ^ jArr[2];
        jArr2[3] = jArr2[3] ^ jArr[3];
    }

    protected static void b(long[] jArr, long[] jArr2) {
        long j2 = jArr[0];
        long j3 = jArr[1];
        long j4 = jArr[2];
        long j5 = jArr[3];
        jArr2[0] = j2 & M59;
        jArr2[1] = ((j2 >>> 59) ^ (j3 << 5)) & M59;
        jArr2[2] = ((j3 >>> 54) ^ (j4 << 10)) & M59;
        jArr2[3] = (j4 >>> 49) ^ (j5 << 15);
    }

    protected static void c(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArr4 = new long[4];
        long[] jArr5 = new long[4];
        b(jArr, jArr4);
        b(jArr2, jArr5);
        long[] jArr6 = new long[8];
        d(jArr6, jArr4[0], jArr5[0], jArr3, 0);
        d(jArr6, jArr4[1], jArr5[1], jArr3, 1);
        d(jArr6, jArr4[2], jArr5[2], jArr3, 2);
        d(jArr6, jArr4[3], jArr5[3], jArr3, 3);
        for (int i2 = 5; i2 > 0; i2--) {
            jArr3[i2] = jArr3[i2] ^ jArr3[i2 - 1];
        }
        d(jArr6, jArr4[0] ^ jArr4[1], jArr5[0] ^ jArr5[1], jArr3, 1);
        d(jArr6, jArr4[2] ^ jArr4[3], jArr5[2] ^ jArr5[3], jArr3, 3);
        for (int i3 = 7; i3 > 1; i3--) {
            jArr3[i3] = jArr3[i3] ^ jArr3[i3 - 2];
        }
        long j2 = jArr4[0] ^ jArr4[2];
        long j3 = jArr4[1] ^ jArr4[3];
        long j4 = jArr5[0] ^ jArr5[2];
        long j5 = jArr5[1] ^ jArr5[3];
        d(jArr6, j2 ^ j3, j4 ^ j5, jArr3, 3);
        long[] jArr7 = new long[3];
        d(jArr6, j2, j4, jArr7, 0);
        d(jArr6, j3, j5, jArr7, 1);
        long j6 = jArr7[0];
        long j7 = jArr7[1];
        long j8 = jArr7[2];
        jArr3[2] = jArr3[2] ^ j6;
        jArr3[3] = (j6 ^ j7) ^ jArr3[3];
        jArr3[4] = jArr3[4] ^ (j8 ^ j7);
        jArr3[5] = jArr3[5] ^ j8;
        a(jArr3);
    }

    protected static void d(long[] jArr, long j2, long j3, long[] jArr2, int i2) {
        jArr[1] = j3;
        jArr[2] = jArr[1] << 1;
        jArr[3] = jArr[2] ^ j3;
        jArr[4] = jArr[2] << 1;
        jArr[5] = jArr[4] ^ j3;
        jArr[6] = jArr[3] << 1;
        jArr[7] = jArr[6] ^ j3;
        int i3 = (int) j2;
        long j4 = (jArr[(i3 >>> 3) & 7] << 3) ^ jArr[i3 & 7];
        long j5 = 0;
        int i4 = 54;
        do {
            int i5 = (int) (j2 >>> i4);
            long j6 = jArr[i5 & 7] ^ (jArr[(i5 >>> 3) & 7] << 3);
            j4 ^= j6 << i4;
            j5 ^= j6 >>> (-i4);
            i4 -= 6;
        } while (i4 > 0);
        jArr2[i2] = jArr2[i2] ^ (M59 & j4);
        int i6 = i2 + 1;
        jArr2[i6] = jArr2[i6] ^ ((j4 >>> 59) ^ (j5 << 5));
    }

    protected static void e(long[] jArr, long[] jArr2) {
        Interleave.expand64To128(jArr, 0, 4, jArr2, 0);
    }

    public static long[] fromBigInteger(BigInteger bigInteger) {
        return Nat.fromBigInteger64(233, bigInteger);
    }

    public static void halfTrace(long[] jArr, long[] jArr2) {
        long[] createExt64 = Nat256.createExt64();
        Nat256.copy64(jArr, jArr2);
        for (int i2 = 1; i2 < 233; i2 += 2) {
            e(jArr2, createExt64);
            reduce(createExt64, jArr2);
            e(jArr2, createExt64);
            reduce(createExt64, jArr2);
            addTo(jArr, jArr2);
        }
    }

    public static void invert(long[] jArr, long[] jArr2) {
        if (Nat256.isZero64(jArr)) {
            throw new IllegalStateException();
        }
        long[] create64 = Nat256.create64();
        long[] create642 = Nat256.create64();
        square(jArr, create64);
        multiply(create64, jArr, create64);
        square(create64, create64);
        multiply(create64, jArr, create64);
        squareN(create64, 3, create642);
        multiply(create642, create64, create642);
        square(create642, create642);
        multiply(create642, jArr, create642);
        squareN(create642, 7, create64);
        multiply(create64, create642, create64);
        squareN(create64, 14, create642);
        multiply(create642, create64, create642);
        square(create642, create642);
        multiply(create642, jArr, create642);
        squareN(create642, 29, create64);
        multiply(create64, create642, create64);
        squareN(create64, 58, create642);
        multiply(create642, create64, create642);
        squareN(create642, 116, create64);
        multiply(create64, create642, create64);
        square(create64, jArr2);
    }

    public static void multiply(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] createExt64 = Nat256.createExt64();
        c(jArr, jArr2, createExt64);
        reduce(createExt64, jArr3);
    }

    public static void multiplyAddToExt(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] createExt64 = Nat256.createExt64();
        c(jArr, jArr2, createExt64);
        addExt(jArr3, createExt64, jArr3);
    }

    public static void reduce(long[] jArr, long[] jArr2) {
        long j2 = jArr[0];
        long j3 = jArr[1];
        long j4 = jArr[2];
        long j5 = jArr[3];
        long j6 = jArr[4];
        long j7 = jArr[5];
        long j8 = jArr[6];
        long j9 = jArr[7];
        long j10 = j7 ^ (j9 >>> 31);
        long j11 = (j6 ^ ((j9 >>> 41) ^ (j9 << 33))) ^ (j8 >>> 31);
        long j12 = ((j5 ^ (j9 << 23)) ^ ((j8 >>> 41) ^ (j8 << 33))) ^ (j10 >>> 31);
        long j13 = j2 ^ (j11 << 23);
        long j14 = ((j4 ^ (j8 << 23)) ^ ((j10 >>> 41) ^ (j10 << 33))) ^ (j11 >>> 31);
        long j15 = j12 >>> 41;
        jArr2[0] = j13 ^ j15;
        long j16 = j15 << 10;
        jArr2[1] = j16 ^ ((j3 ^ (j10 << 23)) ^ ((j11 >>> 41) ^ (j11 << 33)));
        jArr2[2] = j14;
        jArr2[3] = M41 & j12;
    }

    public static void reduce23(long[] jArr, int i2) {
        int i3 = i2 + 3;
        long j2 = jArr[i3];
        long j3 = j2 >>> 41;
        jArr[i2] = jArr[i2] ^ j3;
        int i4 = i2 + 1;
        jArr[i4] = (j3 << 10) ^ jArr[i4];
        jArr[i3] = j2 & M41;
    }

    public static void sqrt(long[] jArr, long[] jArr2) {
        long unshuffle = Interleave.unshuffle(jArr[0]);
        long unshuffle2 = Interleave.unshuffle(jArr[1]);
        long j2 = (unshuffle & BodyPartID.bodyIdMax) | (unshuffle2 << 32);
        long j3 = (unshuffle >>> 32) | (unshuffle2 & (-4294967296L));
        long unshuffle3 = Interleave.unshuffle(jArr[2]);
        long unshuffle4 = Interleave.unshuffle(jArr[3]);
        long j4 = (BodyPartID.bodyIdMax & unshuffle3) | (unshuffle4 << 32);
        long j5 = (unshuffle3 >>> 32) | (unshuffle4 & (-4294967296L));
        long j6 = j5 >>> 27;
        long j7 = j5 ^ ((j3 >>> 27) | (j5 << 37));
        long j8 = j3 ^ (j3 << 37);
        long[] createExt64 = Nat256.createExt64();
        int[] iArr = {32, 117, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_128_CBC_SHA256};
        int i2 = 0;
        for (int i3 = 3; i2 < i3; i3 = 3) {
            int i4 = iArr[i2] >>> 6;
            int i5 = iArr[i2] & 63;
            createExt64[i4] = createExt64[i4] ^ (j8 << i5);
            int i6 = i4 + 1;
            int i7 = -i5;
            createExt64[i6] = createExt64[i6] ^ ((j7 << i5) | (j8 >>> i7));
            int i8 = i4 + 2;
            createExt64[i8] = createExt64[i8] ^ ((j6 << i5) | (j7 >>> i7));
            int i9 = i4 + 3;
            createExt64[i9] = createExt64[i9] ^ (j6 >>> i7);
            i2++;
        }
        reduce(createExt64, jArr2);
        jArr2[0] = jArr2[0] ^ j2;
        jArr2[1] = jArr2[1] ^ j4;
    }

    public static void square(long[] jArr, long[] jArr2) {
        long[] createExt64 = Nat256.createExt64();
        e(jArr, createExt64);
        reduce(createExt64, jArr2);
    }

    public static void squareAddToExt(long[] jArr, long[] jArr2) {
        long[] createExt64 = Nat256.createExt64();
        e(jArr, createExt64);
        addExt(jArr2, createExt64, jArr2);
    }

    public static void squareN(long[] jArr, int i2, long[] jArr2) {
        long[] createExt64 = Nat256.createExt64();
        e(jArr, createExt64);
        while (true) {
            reduce(createExt64, jArr2);
            i2--;
            if (i2 <= 0) {
                return;
            }
            e(jArr2, createExt64);
        }
    }

    public static int trace(long[] jArr) {
        return ((int) (jArr[0] ^ (jArr[2] >>> 31))) & 1;
    }
}
