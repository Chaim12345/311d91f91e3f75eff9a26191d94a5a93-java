package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.common.hash.BloomFilter;
import com.google.common.math.LongMath;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLongArray;
import kotlin.jvm.internal.LongCompanionObject;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public enum BloomFilterStrategies implements BloomFilter.Strategy {
    MURMUR128_MITZ_32 { // from class: com.google.common.hash.BloomFilterStrategies.1
        @Override // com.google.common.hash.BloomFilter.Strategy
        public <T> boolean mightContain(T t2, Funnel<? super T> funnel, int i2, LockFreeBitArray lockFreeBitArray) {
            long b2 = lockFreeBitArray.b();
            long asLong = Hashing.murmur3_128().hashObject(t2, funnel).asLong();
            int i3 = (int) asLong;
            int i4 = (int) (asLong >>> 32);
            for (int i5 = 1; i5 <= i2; i5++) {
                int i6 = (i5 * i4) + i3;
                if (i6 < 0) {
                    i6 = ~i6;
                }
                if (!lockFreeBitArray.d(i6 % b2)) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.google.common.hash.BloomFilter.Strategy
        public <T> boolean put(T t2, Funnel<? super T> funnel, int i2, LockFreeBitArray lockFreeBitArray) {
            long b2 = lockFreeBitArray.b();
            long asLong = Hashing.murmur3_128().hashObject(t2, funnel).asLong();
            int i3 = (int) asLong;
            int i4 = (int) (asLong >>> 32);
            boolean z = false;
            for (int i5 = 1; i5 <= i2; i5++) {
                int i6 = (i5 * i4) + i3;
                if (i6 < 0) {
                    i6 = ~i6;
                }
                z |= lockFreeBitArray.f(i6 % b2);
            }
            return z;
        }
    },
    MURMUR128_MITZ_64 { // from class: com.google.common.hash.BloomFilterStrategies.2
        private long lowerEight(byte[] bArr) {
            return Longs.fromBytes(bArr[7], bArr[6], bArr[5], bArr[4], bArr[3], bArr[2], bArr[1], bArr[0]);
        }

        private long upperEight(byte[] bArr) {
            return Longs.fromBytes(bArr[15], bArr[14], bArr[13], bArr[12], bArr[11], bArr[10], bArr[9], bArr[8]);
        }

        @Override // com.google.common.hash.BloomFilter.Strategy
        public <T> boolean mightContain(T t2, Funnel<? super T> funnel, int i2, LockFreeBitArray lockFreeBitArray) {
            long b2 = lockFreeBitArray.b();
            byte[] c2 = Hashing.murmur3_128().hashObject(t2, funnel).c();
            long lowerEight = lowerEight(c2);
            long upperEight = upperEight(c2);
            for (int i3 = 0; i3 < i2; i3++) {
                if (!lockFreeBitArray.d((LongCompanionObject.MAX_VALUE & lowerEight) % b2)) {
                    return false;
                }
                lowerEight += upperEight;
            }
            return true;
        }

        @Override // com.google.common.hash.BloomFilter.Strategy
        public <T> boolean put(T t2, Funnel<? super T> funnel, int i2, LockFreeBitArray lockFreeBitArray) {
            long b2 = lockFreeBitArray.b();
            byte[] c2 = Hashing.murmur3_128().hashObject(t2, funnel).c();
            long lowerEight = lowerEight(c2);
            long upperEight = upperEight(c2);
            boolean z = false;
            for (int i3 = 0; i3 < i2; i3++) {
                z |= lockFreeBitArray.f((LongCompanionObject.MAX_VALUE & lowerEight) % b2);
                lowerEight += upperEight;
            }
            return z;
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class LockFreeBitArray {
        private static final int LONG_ADDRESSABLE_BITS = 6;

        /* renamed from: a  reason: collision with root package name */
        final AtomicLongArray f9226a;
        private final LongAddable bitCount;

        /* JADX INFO: Access modifiers changed from: package-private */
        public LockFreeBitArray(long j2) {
            Preconditions.checkArgument(j2 > 0, "data length is zero!");
            this.f9226a = new AtomicLongArray(Ints.checkedCast(LongMath.divide(j2, 64L, RoundingMode.CEILING)));
            this.bitCount = LongAddables.create();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public LockFreeBitArray(long[] jArr) {
            Preconditions.checkArgument(jArr.length > 0, "data length is zero!");
            this.f9226a = new AtomicLongArray(jArr);
            this.bitCount = LongAddables.create();
            long j2 = 0;
            for (long j3 : jArr) {
                j2 += Long.bitCount(j3);
            }
            this.bitCount.add(j2);
        }

        public static long[] toPlainArray(AtomicLongArray atomicLongArray) {
            int length = atomicLongArray.length();
            long[] jArr = new long[length];
            for (int i2 = 0; i2 < length; i2++) {
                jArr[i2] = atomicLongArray.get(i2);
            }
            return jArr;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public long a() {
            return this.bitCount.sum();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public long b() {
            return this.f9226a.length() * 64;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public LockFreeBitArray c() {
            return new LockFreeBitArray(toPlainArray(this.f9226a));
        }

        boolean d(long j2) {
            return ((1 << ((int) j2)) & this.f9226a.get((int) (j2 >>> 6))) != 0;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void e(LockFreeBitArray lockFreeBitArray) {
            long j2;
            long j3;
            boolean z;
            Preconditions.checkArgument(this.f9226a.length() == lockFreeBitArray.f9226a.length(), "BitArrays must be of equal length (%s != %s)", this.f9226a.length(), lockFreeBitArray.f9226a.length());
            for (int i2 = 0; i2 < this.f9226a.length(); i2++) {
                long j4 = lockFreeBitArray.f9226a.get(i2);
                while (true) {
                    j2 = this.f9226a.get(i2);
                    j3 = j2 | j4;
                    if (j2 == j3) {
                        z = false;
                        break;
                    } else if (this.f9226a.compareAndSet(i2, j2, j3)) {
                        z = true;
                        break;
                    }
                }
                if (z) {
                    this.bitCount.add(Long.bitCount(j3) - Long.bitCount(j2));
                }
            }
        }

        public boolean equals(@NullableDecl Object obj) {
            if (obj instanceof LockFreeBitArray) {
                return Arrays.equals(toPlainArray(this.f9226a), toPlainArray(((LockFreeBitArray) obj).f9226a));
            }
            return false;
        }

        boolean f(long j2) {
            long j3;
            long j4;
            if (d(j2)) {
                return false;
            }
            int i2 = (int) (j2 >>> 6);
            long j5 = 1 << ((int) j2);
            do {
                j3 = this.f9226a.get(i2);
                j4 = j3 | j5;
                if (j3 == j4) {
                    return false;
                }
            } while (!this.f9226a.compareAndSet(i2, j3, j4));
            this.bitCount.increment();
            return true;
        }

        public int hashCode() {
            return Arrays.hashCode(toPlainArray(this.f9226a));
        }
    }
}
