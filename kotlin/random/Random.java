package kotlin.random;

import java.io.Serializable;
import kotlin.SinceKotlin;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.jetbrains.annotations.NotNull;
@SinceKotlin(version = "1.3")
/* loaded from: classes3.dex */
public abstract class Random {
    @NotNull
    public static final Default Default = new Default(null);
    @NotNull
    private static final Random defaultRandom = PlatformImplementationsKt.IMPLEMENTATIONS.defaultPlatformRandom();

    /* loaded from: classes3.dex */
    public static final class Default extends Random implements Serializable {

        /* loaded from: classes3.dex */
        private static final class Serialized implements Serializable {
            @NotNull
            public static final Serialized INSTANCE = new Serialized();
            private static final long serialVersionUID = 0;

            private Serialized() {
            }

            private final Object readResolve() {
                return Random.Default;
            }
        }

        private Default() {
        }

        public /* synthetic */ Default(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final Object writeReplace() {
            return Serialized.INSTANCE;
        }

        @Override // kotlin.random.Random
        public int nextBits(int i2) {
            return Random.defaultRandom.nextBits(i2);
        }

        @Override // kotlin.random.Random
        public boolean nextBoolean() {
            return Random.defaultRandom.nextBoolean();
        }

        @Override // kotlin.random.Random
        @NotNull
        public byte[] nextBytes(int i2) {
            return Random.defaultRandom.nextBytes(i2);
        }

        @Override // kotlin.random.Random
        @NotNull
        public byte[] nextBytes(@NotNull byte[] array) {
            Intrinsics.checkNotNullParameter(array, "array");
            return Random.defaultRandom.nextBytes(array);
        }

        @Override // kotlin.random.Random
        @NotNull
        public byte[] nextBytes(@NotNull byte[] array, int i2, int i3) {
            Intrinsics.checkNotNullParameter(array, "array");
            return Random.defaultRandom.nextBytes(array, i2, i3);
        }

        @Override // kotlin.random.Random
        public double nextDouble() {
            return Random.defaultRandom.nextDouble();
        }

        @Override // kotlin.random.Random
        public double nextDouble(double d2) {
            return Random.defaultRandom.nextDouble(d2);
        }

        @Override // kotlin.random.Random
        public double nextDouble(double d2, double d3) {
            return Random.defaultRandom.nextDouble(d2, d3);
        }

        @Override // kotlin.random.Random
        public float nextFloat() {
            return Random.defaultRandom.nextFloat();
        }

        @Override // kotlin.random.Random
        public int nextInt() {
            return Random.defaultRandom.nextInt();
        }

        @Override // kotlin.random.Random
        public int nextInt(int i2) {
            return Random.defaultRandom.nextInt(i2);
        }

        @Override // kotlin.random.Random
        public int nextInt(int i2, int i3) {
            return Random.defaultRandom.nextInt(i2, i3);
        }

        @Override // kotlin.random.Random
        public long nextLong() {
            return Random.defaultRandom.nextLong();
        }

        @Override // kotlin.random.Random
        public long nextLong(long j2) {
            return Random.defaultRandom.nextLong(j2);
        }

        @Override // kotlin.random.Random
        public long nextLong(long j2, long j3) {
            return Random.defaultRandom.nextLong(j2, j3);
        }
    }

    public static /* synthetic */ byte[] nextBytes$default(Random random, byte[] bArr, int i2, int i3, int i4, Object obj) {
        if (obj == null) {
            if ((i4 & 2) != 0) {
                i2 = 0;
            }
            if ((i4 & 4) != 0) {
                i3 = bArr.length;
            }
            return random.nextBytes(bArr, i2, i3);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: nextBytes");
    }

    public abstract int nextBits(int i2);

    public boolean nextBoolean() {
        return nextBits(1) != 0;
    }

    @NotNull
    public byte[] nextBytes(int i2) {
        return nextBytes(new byte[i2]);
    }

    @NotNull
    public byte[] nextBytes(@NotNull byte[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        return nextBytes(array, 0, array.length);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0022  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x008c  */
    @NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public byte[] nextBytes(@NotNull byte[] array, int i2, int i3) {
        boolean z;
        Intrinsics.checkNotNullParameter(array, "array");
        if (i2 >= 0 && i2 <= array.length) {
            if (i3 >= 0 && i3 <= array.length) {
                z = true;
                if (z) {
                    throw new IllegalArgumentException(("fromIndex (" + i2 + ") or toIndex (" + i3 + ") are out of range: 0.." + array.length + '.').toString());
                }
                if (!(i2 <= i3)) {
                    throw new IllegalArgumentException(("fromIndex (" + i2 + ") must be not greater than toIndex (" + i3 + ").").toString());
                }
                int i4 = (i3 - i2) / 4;
                for (int i5 = 0; i5 < i4; i5++) {
                    int nextInt = nextInt();
                    array[i2] = (byte) nextInt;
                    array[i2 + 1] = (byte) (nextInt >>> 8);
                    array[i2 + 2] = (byte) (nextInt >>> 16);
                    array[i2 + 3] = (byte) (nextInt >>> 24);
                    i2 += 4;
                }
                int i6 = i3 - i2;
                int nextBits = nextBits(i6 * 8);
                for (int i7 = 0; i7 < i6; i7++) {
                    array[i2 + i7] = (byte) (nextBits >>> (i7 * 8));
                }
                return array;
            }
        }
        z = false;
        if (z) {
        }
    }

    public double nextDouble() {
        return PlatformRandomKt.doubleFromParts(nextBits(26), nextBits(27));
    }

    public double nextDouble(double d2) {
        return nextDouble(0.0d, d2);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public double nextDouble(double d2, double d3) {
        double nextDouble;
        RandomKt.checkRangeBounds(d2, d3);
        double d4 = d3 - d2;
        if (Double.isInfinite(d4)) {
            boolean z = true;
            if ((Double.isInfinite(d2) || Double.isNaN(d2)) ? false : true) {
                if (Double.isInfinite(d3) || Double.isNaN(d3)) {
                    z = false;
                }
                if (z) {
                    double d5 = 2;
                    double nextDouble2 = nextDouble() * ((d3 / d5) - (d2 / d5));
                    nextDouble = d2 + nextDouble2 + nextDouble2;
                    return nextDouble < d3 ? Math.nextAfter(d3, Double.NEGATIVE_INFINITY) : nextDouble;
                }
            }
        }
        nextDouble = d2 + (nextDouble() * d4);
        if (nextDouble < d3) {
        }
    }

    public float nextFloat() {
        return nextBits(24) / 1.6777216E7f;
    }

    public int nextInt() {
        return nextBits(32);
    }

    public int nextInt(int i2) {
        return nextInt(0, i2);
    }

    public int nextInt(int i2, int i3) {
        int nextInt;
        int i4;
        int i5;
        int nextInt2;
        boolean z;
        RandomKt.checkRangeBounds(i2, i3);
        int i6 = i3 - i2;
        if (i6 > 0 || i6 == Integer.MIN_VALUE) {
            if (((-i6) & i6) == i6) {
                i5 = nextBits(RandomKt.fastLog2(i6));
            } else {
                do {
                    nextInt = nextInt() >>> 1;
                    i4 = nextInt % i6;
                } while ((nextInt - i4) + (i6 - 1) < 0);
                i5 = i4;
            }
            return i2 + i5;
        }
        do {
            nextInt2 = nextInt();
            z = false;
            if (i2 <= nextInt2 && nextInt2 < i3) {
                z = true;
                continue;
            }
        } while (!z);
        return nextInt2;
    }

    public long nextLong() {
        return (nextInt() << 32) + nextInt();
    }

    public long nextLong(long j2) {
        return nextLong(0L, j2);
    }

    public long nextLong(long j2, long j3) {
        long nextLong;
        boolean z;
        long nextLong2;
        long j4;
        long j5;
        int nextInt;
        RandomKt.checkRangeBounds(j2, j3);
        long j6 = j3 - j2;
        if (j6 <= 0) {
            do {
                nextLong = nextLong();
                z = false;
                if (j2 <= nextLong && nextLong < j3) {
                    z = true;
                    continue;
                }
            } while (!z);
            return nextLong;
        }
        if (((-j6) & j6) == j6) {
            int i2 = (int) j6;
            int i3 = (int) (j6 >>> 32);
            if (i2 != 0) {
                nextInt = nextBits(RandomKt.fastLog2(i2));
            } else if (i3 == 1) {
                nextInt = nextInt();
            } else {
                j5 = (nextBits(RandomKt.fastLog2(i3)) << 32) + (nextInt() & BodyPartID.bodyIdMax);
            }
            j5 = nextInt & BodyPartID.bodyIdMax;
        } else {
            do {
                nextLong2 = nextLong() >>> 1;
                j4 = nextLong2 % j6;
            } while ((nextLong2 - j4) + (j6 - 1) < 0);
            j5 = j4;
        }
        return j2 + j5;
    }
}
