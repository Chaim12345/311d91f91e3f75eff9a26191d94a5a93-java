package com.google.common.math;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Ascii;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Longs;
import com.google.common.primitives.UnsignedLongs;
import java.math.RoundingMode;
import kotlin.jvm.internal.LongCompanionObject;
import okhttp3.internal.connection.RealConnection;
import org.apache.http.HttpStatus;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.bouncycastle.tls.CipherSuite;
@GwtCompatible(emulated = true)
/* loaded from: classes2.dex */
public final class LongMath {
    private static final int SIEVE_30 = -545925251;
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    static final byte[] f9332a = {19, Ascii.DC2, Ascii.DC2, Ascii.DC2, Ascii.DC2, 17, 17, 17, 16, 16, 16, Ascii.SI, Ascii.SI, Ascii.SI, Ascii.SI, Ascii.SO, Ascii.SO, Ascii.SO, Ascii.CR, Ascii.CR, Ascii.CR, Ascii.FF, Ascii.FF, Ascii.FF, Ascii.FF, Ascii.VT, Ascii.VT, Ascii.VT, 10, 10, 10, 9, 9, 9, 9, 8, 8, 8, 7, 7, 7, 6, 6, 6, 6, 5, 5, 5, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 1, 1, 1, 0, 0, 0};
    @VisibleForTesting
    @GwtIncompatible

    /* renamed from: b  reason: collision with root package name */
    static final long[] f9333b = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000, RealConnection.IDLE_CONNECTION_HEALTHY_NS, 100000000000L, 1000000000000L, 10000000000000L, 100000000000000L, 1000000000000000L, 10000000000000000L, 100000000000000000L, 1000000000000000000L};
    @VisibleForTesting
    @GwtIncompatible

    /* renamed from: c  reason: collision with root package name */
    static final long[] f9334c = {3, 31, 316, 3162, 31622, 316227, 3162277, 31622776, 316227766, 3162277660L, 31622776601L, 316227766016L, 3162277660168L, 31622776601683L, 316227766016837L, 3162277660168379L, 31622776601683793L, 316227766016837933L, 3162277660168379331L};

    /* renamed from: d  reason: collision with root package name */
    static final long[] f9335d = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800, 39916800, 479001600, 6227020800L, 87178291200L, 1307674368000L, 20922789888000L, 355687428096000L, 6402373705728000L, 121645100408832000L, 2432902008176640000L};

    /* renamed from: e  reason: collision with root package name */
    static final int[] f9336e = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 3810779, 121977, 16175, 4337, 1733, 887, 534, 361, 265, 206, CipherSuite.TLS_PSK_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_DHE_PSK_WITH_3DES_EDE_CBC_SHA, 125, 111, 101, 94, 88, 83, 79, 76, 74, 72, 70, 69, 68, 67, 67, 66, 66, 66, 66};
    @VisibleForTesting

    /* renamed from: f  reason: collision with root package name */
    static final int[] f9337f = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 2642246, 86251, 11724, 3218, 1313, 684, HttpStatus.SC_INSUFFICIENT_SPACE_ON_RESOURCE, 287, 214, CipherSuite.TLS_PSK_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_PSK_WITH_3DES_EDE_CBC_SHA, 119, 105, 95, 87, 81, 76, 73, 70, 68, 66, 64, 63, 62, 62, 61, 61, 61};
    private static final long[][] millerRabinBaseSets = {new long[]{291830, 126401071349994536L}, new long[]{885594168, 725270293939359937L, 3569819667048198375L}, new long[]{273919523040L, 15, 7363882082L, 992620450144556L}, new long[]{47636622961200L, 2, 2570940, 211991001, 3749873356L}, new long[]{7999252175582850L, 2, 4130806001517L, 149795463772692060L, 186635894390467037L, 3967304179347715805L}, new long[]{585226005592931976L, 2, 123635709730000L, 9233062284813009L, 43835965440333360L, 761179012939631437L, 1263739024124850375L}, new long[]{LongCompanionObject.MAX_VALUE, 2, 325, 9375, 28178, 450775, 9780504, 1795265022}};

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.common.math.LongMath$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f9338a;

        static {
            int[] iArr = new int[RoundingMode.values().length];
            f9338a = iArr;
            try {
                iArr[RoundingMode.UNNECESSARY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9338a[RoundingMode.DOWN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9338a[RoundingMode.FLOOR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f9338a[RoundingMode.UP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f9338a[RoundingMode.CEILING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f9338a[RoundingMode.HALF_DOWN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f9338a[RoundingMode.HALF_UP.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f9338a[RoundingMode.HALF_EVEN.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public enum MillerRabinTester {
        SMALL { // from class: com.google.common.math.LongMath.MillerRabinTester.1
            @Override // com.google.common.math.LongMath.MillerRabinTester
            long a(long j2, long j3, long j4) {
                return (j2 * j3) % j4;
            }

            @Override // com.google.common.math.LongMath.MillerRabinTester
            long b(long j2, long j3) {
                return (j2 * j2) % j3;
            }
        },
        LARGE { // from class: com.google.common.math.LongMath.MillerRabinTester.2
            private long plusMod(long j2, long j3, long j4) {
                int i2 = (j2 > (j4 - j3) ? 1 : (j2 == (j4 - j3) ? 0 : -1));
                long j5 = j2 + j3;
                return i2 >= 0 ? j5 - j4 : j5;
            }

            private long times2ToThe32Mod(long j2, long j3) {
                int i2 = 32;
                do {
                    int min = Math.min(i2, Long.numberOfLeadingZeros(j2));
                    j2 = UnsignedLongs.remainder(j2 << min, j3);
                    i2 -= min;
                } while (i2 > 0);
                return j2;
            }

            @Override // com.google.common.math.LongMath.MillerRabinTester
            long a(long j2, long j3, long j4) {
                long j5 = j2 >>> 32;
                long j6 = j3 >>> 32;
                long j7 = j2 & BodyPartID.bodyIdMax;
                long j8 = j3 & BodyPartID.bodyIdMax;
                long times2ToThe32Mod = times2ToThe32Mod(j5 * j6, j4) + (j5 * j8);
                if (times2ToThe32Mod < 0) {
                    times2ToThe32Mod = UnsignedLongs.remainder(times2ToThe32Mod, j4);
                }
                return plusMod(times2ToThe32Mod(times2ToThe32Mod + (j6 * j7), j4), UnsignedLongs.remainder(j7 * j8, j4), j4);
            }

            @Override // com.google.common.math.LongMath.MillerRabinTester
            long b(long j2, long j3) {
                long j4 = j2 >>> 32;
                long j5 = j2 & BodyPartID.bodyIdMax;
                long times2ToThe32Mod = times2ToThe32Mod(j4 * j4, j3);
                long j6 = j4 * j5 * 2;
                if (j6 < 0) {
                    j6 = UnsignedLongs.remainder(j6, j3);
                }
                return plusMod(times2ToThe32Mod(times2ToThe32Mod + j6, j3), UnsignedLongs.remainder(j5 * j5, j3), j3);
            }
        };

        /* synthetic */ MillerRabinTester(AnonymousClass1 anonymousClass1) {
            this();
        }

        static boolean c(long j2, long j3) {
            return (j3 <= 3037000499L ? SMALL : LARGE).testWitness(j2, j3);
        }

        private long powMod(long j2, long j3, long j4) {
            long j5 = 1;
            while (j3 != 0) {
                if ((j3 & 1) != 0) {
                    j5 = a(j5, j2, j4);
                }
                j2 = b(j2, j4);
                j3 >>= 1;
            }
            return j5;
        }

        private boolean testWitness(long j2, long j3) {
            long j4 = j3 - 1;
            int numberOfTrailingZeros = Long.numberOfTrailingZeros(j4);
            long j5 = j4 >> numberOfTrailingZeros;
            long j6 = j2 % j3;
            if (j6 == 0) {
                return true;
            }
            long powMod = powMod(j6, j5, j3);
            if (powMod == 1) {
                return true;
            }
            int i2 = 0;
            while (powMod != j4) {
                i2++;
                if (i2 == numberOfTrailingZeros) {
                    return false;
                }
                powMod = b(powMod, j3);
            }
            return true;
        }

        abstract long a(long j2, long j3, long j4);

        abstract long b(long j2, long j3);
    }

    private LongMath() {
    }

    static boolean a(long j2) {
        return ((long) ((int) j2)) == j2;
    }

    @VisibleForTesting
    static int b(long j2, long j3) {
        return (int) ((~(~(j2 - j3))) >>> 63);
    }

    public static long binomial(int i2, int i3) {
        MathPreconditions.e("n", i2);
        MathPreconditions.e("k", i3);
        Preconditions.checkArgument(i3 <= i2, "k (%s) > n (%s)", i3, i2);
        if (i3 > (i2 >> 1)) {
            i3 = i2 - i3;
        }
        long j2 = 1;
        if (i3 != 0) {
            if (i3 != 1) {
                long[] jArr = f9335d;
                if (i2 < jArr.length) {
                    return jArr[i2] / (jArr[i3] * jArr[i2 - i3]);
                }
                int[] iArr = f9336e;
                if (i3 >= iArr.length || i2 > iArr[i3]) {
                    return LongCompanionObject.MAX_VALUE;
                }
                int[] iArr2 = f9337f;
                if (i3 < iArr2.length && i2 <= iArr2[i3]) {
                    int i4 = i2 - 1;
                    long j3 = i2;
                    for (int i5 = 2; i5 <= i3; i5++) {
                        j3 = (j3 * i4) / i5;
                        i4--;
                    }
                    return j3;
                }
                long j4 = i2;
                int log2 = log2(j4, RoundingMode.CEILING);
                int i6 = i2 - 1;
                int i7 = log2;
                int i8 = 2;
                long j5 = j4;
                long j6 = 1;
                while (i8 <= i3) {
                    i7 += log2;
                    if (i7 < 63) {
                        j5 *= i6;
                        j6 *= i8;
                    } else {
                        j2 = d(j2, j5, j6);
                        j5 = i6;
                        j6 = i8;
                        i7 = log2;
                    }
                    i8++;
                    i6--;
                }
                return d(j2, j5, j6);
            }
            return i2;
        }
        return 1L;
    }

    @GwtIncompatible
    static int c(long j2) {
        byte b2 = f9332a[Long.numberOfLeadingZeros(j2)];
        return b2 - b(j2, f9333b[b2]);
    }

    @Beta
    public static long ceilingPowerOfTwo(long j2) {
        MathPreconditions.i("x", j2);
        if (j2 <= Longs.MAX_POWER_OF_TWO) {
            return 1 << (-Long.numberOfLeadingZeros(j2 - 1));
        }
        throw new ArithmeticException("ceilingPowerOfTwo(" + j2 + ") is not representable as a long");
    }

    @GwtIncompatible
    public static long checkedAdd(long j2, long j3) {
        long j4 = j2 + j3;
        MathPreconditions.c(((j2 ^ j3) < 0) | ((j2 ^ j4) >= 0), "checkedAdd", j2, j3);
        return j4;
    }

    public static long checkedMultiply(long j2, long j3) {
        int numberOfLeadingZeros = Long.numberOfLeadingZeros(j2) + Long.numberOfLeadingZeros(~j2) + Long.numberOfLeadingZeros(j3) + Long.numberOfLeadingZeros(~j3);
        if (numberOfLeadingZeros > 65) {
            return j2 * j3;
        }
        MathPreconditions.c(numberOfLeadingZeros >= 64, "checkedMultiply", j2, j3);
        int i2 = (j2 > 0L ? 1 : (j2 == 0L ? 0 : -1));
        MathPreconditions.c((i2 >= 0) | (j3 != Long.MIN_VALUE), "checkedMultiply", j2, j3);
        long j4 = j2 * j3;
        MathPreconditions.c(i2 == 0 || j4 / j2 == j3, "checkedMultiply", j2, j3);
        return j4;
    }

    @GwtIncompatible
    public static long checkedPow(long j2, int i2) {
        MathPreconditions.e("exponent", i2);
        long j3 = 1;
        if ((j2 >= -2) && (j2 <= 2)) {
            int i3 = (int) j2;
            if (i3 == -2) {
                MathPreconditions.c(i2 < 64, "checkedPow", j2, i2);
                return (i2 & 1) == 0 ? 1 << i2 : (-1) << i2;
            } else if (i3 == -1) {
                return (i2 & 1) == 0 ? 1L : -1L;
            } else if (i3 == 0) {
                return i2 == 0 ? 1L : 0L;
            } else if (i3 != 1) {
                if (i3 == 2) {
                    MathPreconditions.c(i2 < 63, "checkedPow", j2, i2);
                    return 1 << i2;
                }
                throw new AssertionError();
            } else {
                return 1L;
            }
        }
        long j4 = j2;
        int i4 = i2;
        while (i4 != 0) {
            if (i4 == 1) {
                return checkedMultiply(j3, j4);
            }
            if ((i4 & 1) != 0) {
                j3 = checkedMultiply(j3, j4);
            }
            long j5 = j3;
            int i5 = i4 >> 1;
            if (i5 > 0) {
                MathPreconditions.c(-3037000499L <= j4 && j4 <= 3037000499L, "checkedPow", j4, i5);
                j4 *= j4;
            }
            i4 = i5;
            j3 = j5;
        }
        return j3;
    }

    @GwtIncompatible
    public static long checkedSubtract(long j2, long j3) {
        long j4 = j2 - j3;
        MathPreconditions.c(((j2 ^ j3) >= 0) | ((j2 ^ j4) >= 0), "checkedSubtract", j2, j3);
        return j4;
    }

    static long d(long j2, long j3, long j4) {
        if (j2 == 1) {
            return j3 / j4;
        }
        long gcd = gcd(j2, j4);
        return (j2 / gcd) * (j3 / (j4 / gcd));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0051, code lost:
        if (r11 > 0) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0054, code lost:
        if (r9 > 0) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0057, code lost:
        if (r9 < 0) goto L30;
     */
    @GwtIncompatible
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static long divide(long j2, long j3, RoundingMode roundingMode) {
        Preconditions.checkNotNull(roundingMode);
        long j4 = j2 / j3;
        long j5 = j2 - (j3 * j4);
        int i2 = (j5 > 0L ? 1 : (j5 == 0L ? 0 : -1));
        if (i2 == 0) {
            return j4;
        }
        int i3 = ((int) ((j2 ^ j3) >> 63)) | 1;
        switch (AnonymousClass1.f9338a[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.k(i2 == 0);
                r10 = false;
                break;
            case 2:
                r10 = false;
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
            case 7:
            case 8:
                long abs = Math.abs(j5);
                int i4 = ((abs - (Math.abs(j3) - abs)) > 0L ? 1 : ((abs - (Math.abs(j3) - abs)) == 0L ? 0 : -1));
                if (i4 == 0) {
                    r10 = (((1 & j4) != 0) & (roundingMode == RoundingMode.HALF_EVEN)) | (roundingMode == RoundingMode.HALF_UP);
                    break;
                }
                break;
            default:
                throw new AssertionError();
        }
        return r10 ? j4 + i3 : j4;
    }

    @GwtIncompatible
    public static long factorial(int i2) {
        MathPreconditions.e("n", i2);
        long[] jArr = f9335d;
        return i2 < jArr.length ? jArr[i2] : LongCompanionObject.MAX_VALUE;
    }

    @Beta
    public static long floorPowerOfTwo(long j2) {
        MathPreconditions.i("x", j2);
        return 1 << (63 - Long.numberOfLeadingZeros(j2));
    }

    public static long gcd(long j2, long j3) {
        MathPreconditions.f("a", j2);
        MathPreconditions.f("b", j3);
        if (j2 == 0) {
            return j3;
        }
        if (j3 == 0) {
            return j2;
        }
        int numberOfTrailingZeros = Long.numberOfTrailingZeros(j2);
        long j4 = j2 >> numberOfTrailingZeros;
        int numberOfTrailingZeros2 = Long.numberOfTrailingZeros(j3);
        long j5 = j3 >> numberOfTrailingZeros2;
        while (j4 != j5) {
            long j6 = j4 - j5;
            long j7 = (j6 >> 63) & j6;
            long j8 = (j6 - j7) - j7;
            j5 += j7;
            j4 = j8 >> Long.numberOfTrailingZeros(j8);
        }
        return j4 << Math.min(numberOfTrailingZeros, numberOfTrailingZeros2);
    }

    public static boolean isPowerOfTwo(long j2) {
        return (j2 > 0) & ((j2 & (j2 - 1)) == 0);
    }

    @Beta
    @GwtIncompatible
    public static boolean isPrime(long j2) {
        long[][] jArr;
        int i2 = (j2 > 2L ? 1 : (j2 == 2L ? 0 : -1));
        if (i2 < 0) {
            MathPreconditions.f("n", j2);
            return false;
        } else if (i2 == 0 || j2 == 3 || j2 == 5 || j2 == 7 || j2 == 11 || j2 == 13) {
            return true;
        } else {
            if ((SIEVE_30 & (1 << ((int) (j2 % 30)))) != 0 || j2 % 7 == 0 || j2 % 11 == 0 || j2 % 13 == 0) {
                return false;
            }
            if (j2 < 289) {
                return true;
            }
            for (long[] jArr2 : millerRabinBaseSets) {
                if (j2 <= jArr2[0]) {
                    for (int i3 = 1; i3 < jArr2.length; i3++) {
                        if (!MillerRabinTester.c(jArr2[i3], j2)) {
                            return false;
                        }
                    }
                    return true;
                }
            }
            throw new AssertionError();
        }
    }

    @GwtIncompatible
    public static int log10(long j2, RoundingMode roundingMode) {
        int b2;
        MathPreconditions.i("x", j2);
        int c2 = c(j2);
        long j3 = f9333b[c2];
        switch (AnonymousClass1.f9338a[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.k(j2 == j3);
                return c2;
            case 2:
            case 3:
                return c2;
            case 4:
            case 5:
                b2 = b(j3, j2);
                return c2 + b2;
            case 6:
            case 7:
            case 8:
                b2 = b(f9334c[c2], j2);
                return c2 + b2;
            default:
                throw new AssertionError();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static int log2(long j2, RoundingMode roundingMode) {
        MathPreconditions.i("x", j2);
        switch (AnonymousClass1.f9338a[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.k(isPowerOfTwo(j2));
                break;
            case 2:
            case 3:
                break;
            case 4:
            case 5:
                return 64 - Long.numberOfLeadingZeros(j2 - 1);
            case 6:
            case 7:
            case 8:
                int numberOfLeadingZeros = Long.numberOfLeadingZeros(j2);
                return (63 - numberOfLeadingZeros) + b((-5402926248376769404) >>> numberOfLeadingZeros, j2);
            default:
                throw new AssertionError("impossible");
        }
        return 63 - Long.numberOfLeadingZeros(j2);
    }

    public static long mean(long j2, long j3) {
        return (j2 & j3) + ((j2 ^ j3) >> 1);
    }

    @GwtIncompatible
    public static int mod(long j2, int i2) {
        return (int) mod(j2, i2);
    }

    @GwtIncompatible
    public static long mod(long j2, long j3) {
        if (j3 > 0) {
            long j4 = j2 % j3;
            return j4 >= 0 ? j4 : j4 + j3;
        }
        throw new ArithmeticException("Modulus must be positive");
    }

    @GwtIncompatible
    public static long pow(long j2, int i2) {
        MathPreconditions.e("exponent", i2);
        if (-2 > j2 || j2 > 2) {
            long j3 = 1;
            while (i2 != 0) {
                if (i2 == 1) {
                    return j3 * j2;
                }
                j3 *= (i2 & 1) == 0 ? 1L : j2;
                j2 *= j2;
                i2 >>= 1;
            }
            return j3;
        }
        int i3 = (int) j2;
        if (i3 == -2) {
            if (i2 < 64) {
                return (i2 & 1) == 0 ? 1 << i2 : -(1 << i2);
            }
            return 0L;
        } else if (i3 == -1) {
            return (i2 & 1) == 0 ? 1L : -1L;
        } else if (i3 == 0) {
            return i2 == 0 ? 1L : 0L;
        } else if (i3 != 1) {
            if (i3 == 2) {
                if (i2 < 64) {
                    return 1 << i2;
                }
                return 0L;
            }
            throw new AssertionError();
        } else {
            return 1L;
        }
    }

    @Beta
    public static long saturatedAdd(long j2, long j3) {
        long j4 = j2 + j3;
        return (((j3 ^ j2) > 0L ? 1 : ((j3 ^ j2) == 0L ? 0 : -1)) < 0) | ((j2 ^ j4) >= 0) ? j4 : ((j4 >>> 63) ^ 1) + LongCompanionObject.MAX_VALUE;
    }

    @Beta
    public static long saturatedMultiply(long j2, long j3) {
        int numberOfLeadingZeros = Long.numberOfLeadingZeros(j2) + Long.numberOfLeadingZeros(~j2) + Long.numberOfLeadingZeros(j3) + Long.numberOfLeadingZeros(~j3);
        if (numberOfLeadingZeros > 65) {
            return j2 * j3;
        }
        long j4 = ((j2 ^ j3) >>> 63) + LongCompanionObject.MAX_VALUE;
        int i2 = (j2 > 0L ? 1 : (j2 == 0L ? 0 : -1));
        if ((numberOfLeadingZeros < 64) || ((j3 == Long.MIN_VALUE) & (i2 < 0))) {
            return j4;
        }
        long j5 = j2 * j3;
        return (i2 == 0 || j5 / j2 == j3) ? j5 : j4;
    }

    @Beta
    public static long saturatedPow(long j2, int i2) {
        MathPreconditions.e("exponent", i2);
        long j3 = 1;
        if ((j2 >= -2) && (j2 <= 2)) {
            int i3 = (int) j2;
            if (i3 == -2) {
                return i2 >= 64 ? (i2 & 1) + LongCompanionObject.MAX_VALUE : (i2 & 1) == 0 ? 1 << i2 : (-1) << i2;
            } else if (i3 == -1) {
                return (i2 & 1) == 0 ? 1L : -1L;
            } else if (i3 == 0) {
                return i2 == 0 ? 1L : 0L;
            } else if (i3 != 1) {
                if (i3 == 2) {
                    return i2 >= 63 ? LongCompanionObject.MAX_VALUE : 1 << i2;
                }
                throw new AssertionError();
            } else {
                return 1L;
            }
        }
        long j4 = ((j2 >>> 63) & i2 & 1) + LongCompanionObject.MAX_VALUE;
        while (i2 != 0) {
            if (i2 == 1) {
                return saturatedMultiply(j3, j2);
            }
            if ((i2 & 1) != 0) {
                j3 = saturatedMultiply(j3, j2);
            }
            i2 >>= 1;
            if (i2 > 0) {
                if ((-3037000499L > j2) || (j2 > 3037000499L)) {
                    return j4;
                }
                j2 *= j2;
            }
        }
        return j3;
    }

    @Beta
    public static long saturatedSubtract(long j2, long j3) {
        long j4 = j2 - j3;
        return (((j3 ^ j2) > 0L ? 1 : ((j3 ^ j2) == 0L ? 0 : -1)) >= 0) | ((j2 ^ j4) >= 0) ? j4 : ((j4 >>> 63) ^ 1) + LongCompanionObject.MAX_VALUE;
    }

    @GwtIncompatible
    public static long sqrt(long j2, RoundingMode roundingMode) {
        MathPreconditions.f("x", j2);
        if (a(j2)) {
            return IntMath.sqrt((int) j2, roundingMode);
        }
        long sqrt = (long) Math.sqrt(j2);
        long j3 = sqrt * sqrt;
        switch (AnonymousClass1.f9338a[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.k(j3 == j2);
                return sqrt;
            case 2:
            case 3:
                return j2 < j3 ? sqrt - 1 : sqrt;
            case 4:
            case 5:
                return j2 > j3 ? sqrt + 1 : sqrt;
            case 6:
            case 7:
            case 8:
                long j4 = sqrt - (j2 >= j3 ? 0 : 1);
                return j4 + b((j4 * j4) + j4, j2);
            default:
                throw new AssertionError();
        }
    }
}
