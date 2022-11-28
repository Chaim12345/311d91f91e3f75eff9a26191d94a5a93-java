package com.google.common.math;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Booleans;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Iterator;
@GwtCompatible(emulated = true)
/* loaded from: classes2.dex */
public final class DoubleMath {
    private static final double MAX_INT_AS_DOUBLE = 2.147483647E9d;
    private static final double MAX_LONG_AS_DOUBLE_PLUS_ONE = 9.223372036854776E18d;
    private static final double MIN_INT_AS_DOUBLE = -2.147483648E9d;
    private static final double MIN_LONG_AS_DOUBLE = -9.223372036854776E18d;
    private static final double LN_2 = Math.log(2.0d);
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    static final double[] f9319a = {1.0d, 2.0922789888E13d, 2.631308369336935E35d, 1.2413915592536073E61d, 1.2688693218588417E89d, 7.156945704626381E118d, 9.916779348709496E149d, 1.974506857221074E182d, 3.856204823625804E215d, 5.5502938327393044E249d, 4.7147236359920616E284d};

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.common.math.DoubleMath$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f9320a;

        static {
            int[] iArr = new int[RoundingMode.values().length];
            f9320a = iArr;
            try {
                iArr[RoundingMode.UNNECESSARY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9320a[RoundingMode.FLOOR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9320a[RoundingMode.CEILING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f9320a[RoundingMode.DOWN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f9320a[RoundingMode.UP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f9320a[RoundingMode.HALF_EVEN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f9320a[RoundingMode.HALF_UP.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f9320a[RoundingMode.HALF_DOWN.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    private DoubleMath() {
    }

    @GwtIncompatible
    static double a(double d2, RoundingMode roundingMode) {
        if (DoubleUtils.d(d2)) {
            switch (AnonymousClass1.f9320a[roundingMode.ordinal()]) {
                case 1:
                    MathPreconditions.k(isMathematicalInteger(d2));
                    return d2;
                case 2:
                    return (d2 >= 0.0d || isMathematicalInteger(d2)) ? d2 : ((long) d2) - 1;
                case 3:
                    return (d2 <= 0.0d || isMathematicalInteger(d2)) ? d2 : ((long) d2) + 1;
                case 4:
                    return d2;
                case 5:
                    if (isMathematicalInteger(d2)) {
                        return d2;
                    }
                    return ((long) d2) + (d2 > 0.0d ? 1 : -1);
                case 6:
                    return Math.rint(d2);
                case 7:
                    double rint = Math.rint(d2);
                    return Math.abs(d2 - rint) == 0.5d ? d2 + Math.copySign(0.5d, d2) : rint;
                case 8:
                    double rint2 = Math.rint(d2);
                    return Math.abs(d2 - rint2) == 0.5d ? d2 : rint2;
                default:
                    throw new AssertionError();
            }
        }
        throw new ArithmeticException("input is infinite or NaN");
    }

    @CanIgnoreReturnValue
    @GwtIncompatible
    private static double checkFinite(double d2) {
        Preconditions.checkArgument(DoubleUtils.d(d2));
        return d2;
    }

    public static double factorial(int i2) {
        MathPreconditions.e("n", i2);
        if (i2 > 170) {
            return Double.POSITIVE_INFINITY;
        }
        double d2 = 1.0d;
        int i3 = i2 & (-16);
        while (true) {
            i3++;
            if (i3 > i2) {
                return d2 * f9319a[i2 >> 4];
            }
            d2 *= i3;
        }
    }

    public static int fuzzyCompare(double d2, double d3, double d4) {
        if (fuzzyEquals(d2, d3, d4)) {
            return 0;
        }
        if (d2 < d3) {
            return -1;
        }
        if (d2 > d3) {
            return 1;
        }
        return Booleans.compare(Double.isNaN(d2), Double.isNaN(d3));
    }

    public static boolean fuzzyEquals(double d2, double d3, double d4) {
        MathPreconditions.d("tolerance", d4);
        return Math.copySign(d2 - d3, 1.0d) <= d4 || d2 == d3 || (Double.isNaN(d2) && Double.isNaN(d3));
    }

    @GwtIncompatible
    public static boolean isMathematicalInteger(double d2) {
        return DoubleUtils.d(d2) && (d2 == 0.0d || 52 - Long.numberOfTrailingZeros(DoubleUtils.c(d2)) <= Math.getExponent(d2));
    }

    @GwtIncompatible
    public static boolean isPowerOfTwo(double d2) {
        if (d2 <= 0.0d || !DoubleUtils.d(d2)) {
            return false;
        }
        long c2 = DoubleUtils.c(d2);
        return (c2 & (c2 - 1)) == 0;
    }

    public static double log2(double d2) {
        return Math.log(d2) / LN_2;
    }

    @GwtIncompatible
    public static int log2(double d2, RoundingMode roundingMode) {
        boolean z = false;
        Preconditions.checkArgument(d2 > 0.0d && DoubleUtils.d(d2), "x must be positive and finite");
        int exponent = Math.getExponent(d2);
        if (DoubleUtils.e(d2)) {
            switch (AnonymousClass1.f9320a[roundingMode.ordinal()]) {
                case 1:
                    MathPreconditions.k(isPowerOfTwo(d2));
                    break;
                case 2:
                    break;
                case 3:
                    z = !isPowerOfTwo(d2);
                    break;
                case 4:
                    if (exponent < 0) {
                        z = true;
                    }
                    z &= !isPowerOfTwo(d2);
                    break;
                case 5:
                    if (exponent >= 0) {
                        z = true;
                    }
                    z &= !isPowerOfTwo(d2);
                    break;
                case 6:
                case 7:
                case 8:
                    double f2 = DoubleUtils.f(d2);
                    if (f2 * f2 > 2.0d) {
                        z = true;
                        break;
                    }
                    break;
                default:
                    throw new AssertionError();
            }
            return z ? exponent + 1 : exponent;
        }
        return log2(d2 * 4.503599627370496E15d, roundingMode) - 52;
    }

    @GwtIncompatible
    @Deprecated
    public static double mean(Iterable<? extends Number> iterable) {
        return mean(iterable.iterator());
    }

    @GwtIncompatible
    @Deprecated
    public static double mean(Iterator<? extends Number> it) {
        Preconditions.checkArgument(it.hasNext(), "Cannot take mean of 0 values");
        double checkFinite = checkFinite(it.next().doubleValue());
        long j2 = 1;
        while (it.hasNext()) {
            j2++;
            checkFinite += (checkFinite(it.next().doubleValue()) - checkFinite) / j2;
        }
        return checkFinite;
    }

    @GwtIncompatible
    @Deprecated
    public static double mean(double... dArr) {
        Preconditions.checkArgument(dArr.length > 0, "Cannot take mean of 0 values");
        double checkFinite = checkFinite(dArr[0]);
        long j2 = 1;
        for (int i2 = 1; i2 < dArr.length; i2++) {
            checkFinite(dArr[i2]);
            j2++;
            checkFinite += (dArr[i2] - checkFinite) / j2;
        }
        return checkFinite;
    }

    @Deprecated
    public static double mean(int... iArr) {
        Preconditions.checkArgument(iArr.length > 0, "Cannot take mean of 0 values");
        long j2 = 0;
        for (int i2 : iArr) {
            j2 += i2;
        }
        return j2 / iArr.length;
    }

    @Deprecated
    public static double mean(long... jArr) {
        Preconditions.checkArgument(jArr.length > 0, "Cannot take mean of 0 values");
        double d2 = jArr[0];
        long j2 = 1;
        for (int i2 = 1; i2 < jArr.length; i2++) {
            j2++;
            d2 += (jArr[i2] - d2) / j2;
        }
        return d2;
    }

    @GwtIncompatible
    public static BigInteger roundToBigInteger(double d2, RoundingMode roundingMode) {
        double a2 = a(d2, roundingMode);
        if ((MIN_LONG_AS_DOUBLE - a2 < 1.0d) && (a2 < MAX_LONG_AS_DOUBLE_PLUS_ONE)) {
            return BigInteger.valueOf((long) a2);
        }
        BigInteger shiftLeft = BigInteger.valueOf(DoubleUtils.c(a2)).shiftLeft(Math.getExponent(a2) - 52);
        return a2 < 0.0d ? shiftLeft.negate() : shiftLeft;
    }

    @GwtIncompatible
    public static int roundToInt(double d2, RoundingMode roundingMode) {
        double a2 = a(d2, roundingMode);
        MathPreconditions.a((a2 > -2.147483649E9d) & (a2 < 2.147483648E9d), d2, roundingMode);
        return (int) a2;
    }

    @GwtIncompatible
    public static long roundToLong(double d2, RoundingMode roundingMode) {
        double a2 = a(d2, roundingMode);
        MathPreconditions.a((MIN_LONG_AS_DOUBLE - a2 < 1.0d) & (a2 < MAX_LONG_AS_DOUBLE_PLUS_ONE), d2, roundingMode);
        return (long) a2;
    }
}
