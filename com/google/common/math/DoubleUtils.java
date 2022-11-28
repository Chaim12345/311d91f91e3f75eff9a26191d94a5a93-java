package com.google.common.math;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.math.BigInteger;
@GwtIncompatible
/* loaded from: classes2.dex */
final class DoubleUtils {
    private DoubleUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double a(BigInteger bigInteger) {
        BigInteger abs = bigInteger.abs();
        boolean z = true;
        int bitLength = abs.bitLength() - 1;
        if (bitLength < 63) {
            return bigInteger.longValue();
        }
        if (bitLength > 1023) {
            return bigInteger.signum() * Double.POSITIVE_INFINITY;
        }
        int i2 = (bitLength - 52) - 1;
        long longValue = abs.shiftRight(i2).longValue();
        long j2 = (longValue >> 1) & 4503599627370495L;
        if ((longValue & 1) == 0 || ((j2 & 1) == 0 && abs.getLowestSetBit() >= i2)) {
            z = false;
        }
        if (z) {
            j2++;
        }
        return Double.longBitsToDouble((((bitLength + 1023) << 52) + j2) | (bigInteger.signum() & Long.MIN_VALUE));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double b(double d2) {
        Preconditions.checkArgument(!Double.isNaN(d2));
        return Math.max(d2, 0.0d);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long c(double d2) {
        Preconditions.checkArgument(d(d2), "not a normal value");
        int exponent = Math.getExponent(d2);
        long doubleToRawLongBits = Double.doubleToRawLongBits(d2) & 4503599627370495L;
        return exponent == -1023 ? doubleToRawLongBits << 1 : doubleToRawLongBits | 4503599627370496L;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean d(double d2) {
        return Math.getExponent(d2) <= 1023;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean e(double d2) {
        return Math.getExponent(d2) >= -1022;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double f(double d2) {
        return Double.longBitsToDouble((Double.doubleToRawLongBits(d2) & 4503599627370495L) | 4607182418800017408L);
    }
}
