package com.google.common.hash;

import com.google.common.annotations.Beta;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.hash.BloomFilterStrategies;
import com.google.common.math.DoubleMath;
import com.google.common.primitives.SignedBytes;
import com.google.common.primitives.UnsignedBytes;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.RoundingMode;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@Beta
/* loaded from: classes2.dex */
public final class BloomFilter<T> implements Predicate<T>, Serializable {
    private final BloomFilterStrategies.LockFreeBitArray bits;
    private final Funnel<? super T> funnel;
    private final int numHashFunctions;
    private final Strategy strategy;

    /* loaded from: classes2.dex */
    private static class SerialForm<T> implements Serializable {
        private static final long serialVersionUID = 1;

        /* renamed from: a  reason: collision with root package name */
        final Funnel f9224a;

        /* renamed from: b  reason: collision with root package name */
        final Strategy f9225b;

        SerialForm(BloomFilter bloomFilter) {
            BloomFilterStrategies.LockFreeBitArray.toPlainArray(bloomFilter.bits.f9226a);
            int unused = bloomFilter.numHashFunctions;
            this.f9224a = bloomFilter.funnel;
            this.f9225b = bloomFilter.strategy;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public interface Strategy extends Serializable {
        <T> boolean mightContain(T t2, Funnel<? super T> funnel, int i2, BloomFilterStrategies.LockFreeBitArray lockFreeBitArray);

        int ordinal();

        <T> boolean put(T t2, Funnel<? super T> funnel, int i2, BloomFilterStrategies.LockFreeBitArray lockFreeBitArray);
    }

    private BloomFilter(BloomFilterStrategies.LockFreeBitArray lockFreeBitArray, int i2, Funnel<? super T> funnel, Strategy strategy) {
        Preconditions.checkArgument(i2 > 0, "numHashFunctions (%s) must be > 0", i2);
        Preconditions.checkArgument(i2 <= 255, "numHashFunctions (%s) must be <= 255", i2);
        this.bits = (BloomFilterStrategies.LockFreeBitArray) Preconditions.checkNotNull(lockFreeBitArray);
        this.numHashFunctions = i2;
        this.funnel = (Funnel) Preconditions.checkNotNull(funnel);
        this.strategy = (Strategy) Preconditions.checkNotNull(strategy);
    }

    public static <T> BloomFilter<T> create(Funnel<? super T> funnel, int i2) {
        return create(funnel, i2);
    }

    public static <T> BloomFilter<T> create(Funnel<? super T> funnel, int i2, double d2) {
        return create(funnel, i2, d2);
    }

    public static <T> BloomFilter<T> create(Funnel<? super T> funnel, long j2) {
        return create(funnel, j2, 0.03d);
    }

    public static <T> BloomFilter<T> create(Funnel<? super T> funnel, long j2, double d2) {
        return f(funnel, j2, d2, BloomFilterStrategies.MURMUR128_MITZ_64);
    }

    @VisibleForTesting
    static BloomFilter f(Funnel funnel, long j2, double d2, Strategy strategy) {
        Preconditions.checkNotNull(funnel);
        int i2 = (j2 > 0L ? 1 : (j2 == 0L ? 0 : -1));
        Preconditions.checkArgument(i2 >= 0, "Expected insertions (%s) must be >= 0", j2);
        Preconditions.checkArgument(d2 > 0.0d, "False positive probability (%s) must be > 0.0", Double.valueOf(d2));
        Preconditions.checkArgument(d2 < 1.0d, "False positive probability (%s) must be < 1.0", Double.valueOf(d2));
        Preconditions.checkNotNull(strategy);
        if (i2 == 0) {
            j2 = 1;
        }
        long g2 = g(j2, d2);
        try {
            return new BloomFilter(new BloomFilterStrategies.LockFreeBitArray(g2), h(j2, g2), funnel, strategy);
        } catch (IllegalArgumentException e2) {
            throw new IllegalArgumentException("Could not create BloomFilter of " + g2 + " bits", e2);
        }
    }

    @VisibleForTesting
    static long g(long j2, double d2) {
        if (d2 == 0.0d) {
            d2 = Double.MIN_VALUE;
        }
        return (long) (((-j2) * Math.log(d2)) / (Math.log(2.0d) * Math.log(2.0d)));
    }

    @VisibleForTesting
    static int h(long j2, long j3) {
        return Math.max(1, (int) Math.round((j3 / j2) * Math.log(2.0d)));
    }

    public static <T> BloomFilter<T> readFrom(InputStream inputStream, Funnel<? super T> funnel) {
        int i2;
        int i3;
        Preconditions.checkNotNull(inputStream, "InputStream");
        Preconditions.checkNotNull(funnel, "Funnel");
        int i4 = -1;
        try {
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            byte readByte = dataInputStream.readByte();
            try {
                i3 = UnsignedBytes.toInt(dataInputStream.readByte());
            } catch (RuntimeException e2) {
                e = e2;
                i3 = -1;
                i4 = readByte;
                i2 = -1;
            }
            try {
                i4 = dataInputStream.readInt();
                BloomFilterStrategies bloomFilterStrategies = BloomFilterStrategies.values()[readByte];
                long[] jArr = new long[i4];
                for (int i5 = 0; i5 < i4; i5++) {
                    jArr[i5] = dataInputStream.readLong();
                }
                return new BloomFilter<>(new BloomFilterStrategies.LockFreeBitArray(jArr), i3, funnel, bloomFilterStrategies);
            } catch (RuntimeException e3) {
                e = e3;
                int i6 = i4;
                i4 = readByte;
                i2 = i6;
                throw new IOException("Unable to deserialize BloomFilter from InputStream. strategyOrdinal: " + i4 + " numHashFunctions: " + i3 + " dataLength: " + i2, e);
            }
        } catch (RuntimeException e4) {
            e = e4;
            i2 = -1;
            i3 = -1;
        }
    }

    private Object writeReplace() {
        return new SerialForm(this);
    }

    @Override // com.google.common.base.Predicate
    @Deprecated
    public boolean apply(T t2) {
        return mightContain(t2);
    }

    public long approximateElementCount() {
        double b2 = this.bits.b();
        return DoubleMath.roundToLong(((-Math.log1p(-(this.bits.a() / b2))) * b2) / this.numHashFunctions, RoundingMode.HALF_UP);
    }

    public BloomFilter<T> copy() {
        return new BloomFilter<>(this.bits.c(), this.numHashFunctions, this.funnel, this.strategy);
    }

    @VisibleForTesting
    long e() {
        return this.bits.b();
    }

    @Override // com.google.common.base.Predicate
    public boolean equals(@NullableDecl Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof BloomFilter) {
            BloomFilter bloomFilter = (BloomFilter) obj;
            return this.numHashFunctions == bloomFilter.numHashFunctions && this.funnel.equals(bloomFilter.funnel) && this.bits.equals(bloomFilter.bits) && this.strategy.equals(bloomFilter.strategy);
        }
        return false;
    }

    public double expectedFpp() {
        return Math.pow(this.bits.a() / e(), this.numHashFunctions);
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.numHashFunctions), this.funnel, this.strategy, this.bits);
    }

    public boolean isCompatible(BloomFilter<T> bloomFilter) {
        Preconditions.checkNotNull(bloomFilter);
        return this != bloomFilter && this.numHashFunctions == bloomFilter.numHashFunctions && e() == bloomFilter.e() && this.strategy.equals(bloomFilter.strategy) && this.funnel.equals(bloomFilter.funnel);
    }

    public boolean mightContain(T t2) {
        return this.strategy.mightContain(t2, this.funnel, this.numHashFunctions, this.bits);
    }

    @CanIgnoreReturnValue
    public boolean put(T t2) {
        return this.strategy.put(t2, this.funnel, this.numHashFunctions, this.bits);
    }

    public void putAll(BloomFilter<T> bloomFilter) {
        Preconditions.checkNotNull(bloomFilter);
        Preconditions.checkArgument(this != bloomFilter, "Cannot combine a BloomFilter with itself.");
        int i2 = this.numHashFunctions;
        int i3 = bloomFilter.numHashFunctions;
        Preconditions.checkArgument(i2 == i3, "BloomFilters must have the same number of hash functions (%s != %s)", i2, i3);
        Preconditions.checkArgument(e() == bloomFilter.e(), "BloomFilters must have the same size underlying bit arrays (%s != %s)", e(), bloomFilter.e());
        Preconditions.checkArgument(this.strategy.equals(bloomFilter.strategy), "BloomFilters must have equal strategies (%s != %s)", this.strategy, bloomFilter.strategy);
        Preconditions.checkArgument(this.funnel.equals(bloomFilter.funnel), "BloomFilters must have equal funnels (%s != %s)", this.funnel, bloomFilter.funnel);
        this.bits.e(bloomFilter.bits);
    }

    public void writeTo(OutputStream outputStream) {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeByte(SignedBytes.checkedCast(this.strategy.ordinal()));
        dataOutputStream.writeByte(UnsignedBytes.checkedCast(this.numHashFunctions));
        dataOutputStream.writeInt(this.bits.f9226a.length());
        for (int i2 = 0; i2 < this.bits.f9226a.length(); i2++) {
            dataOutputStream.writeLong(this.bits.f9226a.get(i2));
        }
    }
}
