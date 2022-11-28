package com.google.common.math;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.math.BigInteger;
import java.math.RoundingMode;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@CanIgnoreReturnValue
@GwtCompatible
/* loaded from: classes2.dex */
final class MathPreconditions {
    private MathPreconditions() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(boolean z, double d2, RoundingMode roundingMode) {
        if (z) {
            return;
        }
        throw new ArithmeticException("rounded value is out of range for input " + d2 + " and rounding mode " + roundingMode);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(boolean z, String str, int i2, int i3) {
        if (z) {
            return;
        }
        throw new ArithmeticException("overflow: " + str + "(" + i2 + ", " + i3 + ")");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void c(boolean z, String str, long j2, long j3) {
        if (z) {
            return;
        }
        throw new ArithmeticException("overflow: " + str + "(" + j2 + ", " + j3 + ")");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double d(@NullableDecl String str, double d2) {
        if (d2 >= 0.0d) {
            return d2;
        }
        throw new IllegalArgumentException(str + " (" + d2 + ") must be >= 0");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int e(@NullableDecl String str, int i2) {
        if (i2 >= 0) {
            return i2;
        }
        throw new IllegalArgumentException(str + " (" + i2 + ") must be >= 0");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long f(@NullableDecl String str, long j2) {
        if (j2 >= 0) {
            return j2;
        }
        throw new IllegalArgumentException(str + " (" + j2 + ") must be >= 0");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BigInteger g(@NullableDecl String str, BigInteger bigInteger) {
        if (bigInteger.signum() >= 0) {
            return bigInteger;
        }
        throw new IllegalArgumentException(str + " (" + bigInteger + ") must be >= 0");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int h(@NullableDecl String str, int i2) {
        if (i2 > 0) {
            return i2;
        }
        throw new IllegalArgumentException(str + " (" + i2 + ") must be > 0");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long i(@NullableDecl String str, long j2) {
        if (j2 > 0) {
            return j2;
        }
        throw new IllegalArgumentException(str + " (" + j2 + ") must be > 0");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BigInteger j(@NullableDecl String str, BigInteger bigInteger) {
        if (bigInteger.signum() > 0) {
            return bigInteger;
        }
        throw new IllegalArgumentException(str + " (" + bigInteger + ") must be > 0");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void k(boolean z) {
        if (!z) {
            throw new ArithmeticException("mode was UNNECESSARY, but rounding was necessary");
        }
    }
}
