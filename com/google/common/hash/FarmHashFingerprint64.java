package com.google.common.hash;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import org.bouncycastle.asn1.cmc.BodyPartID;
/* loaded from: classes2.dex */
final class FarmHashFingerprint64 extends AbstractNonStreamingHashFunction {
    private static final long K0 = -4348849565147123417L;
    private static final long K1 = -5435081209227447693L;
    private static final long K2 = -7286425919675154353L;

    /* renamed from: a  reason: collision with root package name */
    static final HashFunction f9230a = new FarmHashFingerprint64();

    FarmHashFingerprint64() {
    }

    @VisibleForTesting
    static long a(byte[] bArr, int i2, int i3) {
        return i3 <= 32 ? i3 <= 16 ? hashLength0to16(bArr, i2, i3) : hashLength17to32(bArr, i2, i3) : i3 <= 64 ? hashLength33To64(bArr, i2, i3) : hashLength65Plus(bArr, i2, i3);
    }

    private static long hashLength0to16(byte[] bArr, int i2, int i3) {
        if (i3 >= 8) {
            long j2 = (i3 * 2) + K2;
            long b2 = LittleEndianByteArray.b(bArr, i2) + K2;
            long b3 = LittleEndianByteArray.b(bArr, (i2 + i3) - 8);
            return hashLength16((Long.rotateRight(b3, 37) * j2) + b2, (Long.rotateRight(b2, 25) + b3) * j2, j2);
        } else if (i3 >= 4) {
            return hashLength16(i3 + ((LittleEndianByteArray.a(bArr, i2) & BodyPartID.bodyIdMax) << 3), LittleEndianByteArray.a(bArr, (i2 + i3) - 4) & BodyPartID.bodyIdMax, (i3 * 2) + K2);
        } else if (i3 > 0) {
            return shiftMix((((bArr[i2] & 255) + ((bArr[(i3 >> 1) + i2] & 255) << 8)) * K2) ^ ((i3 + ((bArr[i2 + (i3 - 1)] & 255) << 2)) * K0)) * K2;
        } else {
            return K2;
        }
    }

    private static long hashLength16(long j2, long j3, long j4) {
        long j5 = (j2 ^ j3) * j4;
        long j6 = ((j5 ^ (j5 >>> 47)) ^ j3) * j4;
        return (j6 ^ (j6 >>> 47)) * j4;
    }

    private static long hashLength17to32(byte[] bArr, int i2, int i3) {
        long j2 = (i3 * 2) + K2;
        long b2 = LittleEndianByteArray.b(bArr, i2) * K1;
        long b3 = LittleEndianByteArray.b(bArr, i2 + 8);
        int i4 = i2 + i3;
        long b4 = LittleEndianByteArray.b(bArr, i4 - 8) * j2;
        return hashLength16((LittleEndianByteArray.b(bArr, i4 - 16) * K2) + Long.rotateRight(b2 + b3, 43) + Long.rotateRight(b4, 30), b2 + Long.rotateRight(b3 + K2, 18) + b4, j2);
    }

    private static long hashLength33To64(byte[] bArr, int i2, int i3) {
        long j2 = (i3 * 2) + K2;
        long b2 = LittleEndianByteArray.b(bArr, i2) * K2;
        long b3 = LittleEndianByteArray.b(bArr, i2 + 8);
        int i4 = i2 + i3;
        long b4 = LittleEndianByteArray.b(bArr, i4 - 8) * j2;
        long rotateRight = Long.rotateRight(b2 + b3, 43) + Long.rotateRight(b4, 30) + (LittleEndianByteArray.b(bArr, i4 - 16) * K2);
        long hashLength16 = hashLength16(rotateRight, b4 + Long.rotateRight(b3 + K2, 18) + b2, j2);
        long b5 = LittleEndianByteArray.b(bArr, i2 + 16) * j2;
        long b6 = LittleEndianByteArray.b(bArr, i2 + 24);
        long b7 = (rotateRight + LittleEndianByteArray.b(bArr, i4 - 32)) * j2;
        return hashLength16(((hashLength16 + LittleEndianByteArray.b(bArr, i4 - 24)) * j2) + Long.rotateRight(b5 + b6, 43) + Long.rotateRight(b7, 30), b5 + Long.rotateRight(b6 + b2, 18) + b7, j2);
    }

    private static long hashLength65Plus(byte[] bArr, int i2, int i3) {
        long shiftMix = shiftMix(-7956866745689871395L) * K2;
        long[] jArr = new long[2];
        long[] jArr2 = new long[2];
        long b2 = 95310865018149119L + LittleEndianByteArray.b(bArr, i2);
        int i4 = i3 - 1;
        int i5 = i2 + ((i4 / 64) * 64);
        int i6 = i4 & 63;
        int i7 = (i5 + i6) - 63;
        long j2 = 2480279821605975764L;
        int i8 = i2;
        while (true) {
            long rotateRight = Long.rotateRight(b2 + j2 + jArr[0] + LittleEndianByteArray.b(bArr, i8 + 8), 37) * K1;
            long rotateRight2 = Long.rotateRight(j2 + jArr[1] + LittleEndianByteArray.b(bArr, i8 + 48), 42) * K1;
            long j3 = rotateRight ^ jArr2[1];
            long b3 = rotateRight2 + jArr[0] + LittleEndianByteArray.b(bArr, i8 + 40);
            long rotateRight3 = Long.rotateRight(shiftMix + jArr2[0], 33) * K1;
            weakHashLength32WithSeeds(bArr, i8, jArr[1] * K1, j3 + jArr2[0], jArr);
            weakHashLength32WithSeeds(bArr, i8 + 32, rotateRight3 + jArr2[1], b3 + LittleEndianByteArray.b(bArr, i8 + 16), jArr2);
            i8 += 64;
            if (i8 == i5) {
                long j4 = ((j3 & 255) << 1) + K1;
                jArr2[0] = jArr2[0] + i6;
                jArr[0] = jArr[0] + jArr2[0];
                jArr2[0] = jArr2[0] + jArr[0];
                long rotateRight4 = (Long.rotateRight(((rotateRight3 + b3) + jArr[0]) + LittleEndianByteArray.b(bArr, i7 + 8), 37) * j4) ^ (jArr2[1] * 9);
                long rotateRight5 = (Long.rotateRight(b3 + jArr[1] + LittleEndianByteArray.b(bArr, i7 + 48), 42) * j4) + (jArr[0] * 9) + LittleEndianByteArray.b(bArr, i7 + 40);
                long rotateRight6 = Long.rotateRight(j3 + jArr2[0], 33) * j4;
                weakHashLength32WithSeeds(bArr, i7, jArr[1] * j4, rotateRight4 + jArr2[0], jArr);
                weakHashLength32WithSeeds(bArr, i7 + 32, rotateRight6 + jArr2[1], LittleEndianByteArray.b(bArr, i7 + 16) + rotateRight5, jArr2);
                return hashLength16(hashLength16(jArr[0], jArr2[0], j4) + (shiftMix(rotateRight5) * K0) + rotateRight4, hashLength16(jArr[1], jArr2[1], j4) + rotateRight6, j4);
            }
            shiftMix = j3;
            j2 = b3;
            b2 = rotateRight3;
        }
    }

    private static long shiftMix(long j2) {
        return j2 ^ (j2 >>> 47);
    }

    private static void weakHashLength32WithSeeds(byte[] bArr, int i2, long j2, long j3, long[] jArr) {
        long b2 = LittleEndianByteArray.b(bArr, i2);
        long b3 = LittleEndianByteArray.b(bArr, i2 + 8);
        long b4 = LittleEndianByteArray.b(bArr, i2 + 16);
        long b5 = LittleEndianByteArray.b(bArr, i2 + 24);
        long j4 = j2 + b2;
        long j5 = b3 + j4 + b4;
        jArr[0] = j5 + b5;
        jArr[1] = Long.rotateRight(j3 + j4 + b5, 21) + Long.rotateRight(j5, 44) + j4;
    }

    @Override // com.google.common.hash.HashFunction
    public int bits() {
        return 64;
    }

    @Override // com.google.common.hash.AbstractNonStreamingHashFunction, com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public HashCode hashBytes(byte[] bArr, int i2, int i3) {
        Preconditions.checkPositionIndexes(i2, i2 + i3, bArr.length);
        return HashCode.fromLong(a(bArr, i2, i3));
    }

    public String toString() {
        return "Hashing.farmHashFingerprint64()";
    }
}
