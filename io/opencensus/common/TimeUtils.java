package io.opencensus.common;

import java.math.BigInteger;
import kotlin.jvm.internal.LongCompanionObject;
/* loaded from: classes3.dex */
final class TimeUtils {
    private static final BigInteger MAX_LONG_VALUE = BigInteger.valueOf(LongCompanionObject.MAX_VALUE);
    private static final BigInteger MIN_LONG_VALUE = BigInteger.valueOf(Long.MIN_VALUE);

    private TimeUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long a(long j2, long j3) {
        BigInteger add = BigInteger.valueOf(j2).add(BigInteger.valueOf(j3));
        if (add.compareTo(MAX_LONG_VALUE) > 0 || add.compareTo(MIN_LONG_VALUE) < 0) {
            throw new ArithmeticException("Long sum overflow: x=" + j2 + ", y=" + j3);
        }
        return j2 + j3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int b(long j2, long j3) {
        int i2 = (j2 > j3 ? 1 : (j2 == j3 ? 0 : -1));
        if (i2 < 0) {
            return -1;
        }
        return i2 == 0 ? 0 : 1;
    }
}
