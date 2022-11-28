package kotlin.internal;

import kotlin.PublishedApi;
/* loaded from: classes3.dex */
public final class ProgressionUtilKt {
    private static final int differenceModulo(int i2, int i3, int i4) {
        return mod(mod(i2, i4) - mod(i3, i4), i4);
    }

    private static final long differenceModulo(long j2, long j3, long j4) {
        return mod(mod(j2, j4) - mod(j3, j4), j4);
    }

    @PublishedApi
    public static final int getProgressionLastElement(int i2, int i3, int i4) {
        if (i4 > 0) {
            return i2 >= i3 ? i3 : i3 - differenceModulo(i3, i2, i4);
        } else if (i4 < 0) {
            return i2 <= i3 ? i3 : i3 + differenceModulo(i2, i3, -i4);
        } else {
            throw new IllegalArgumentException("Step is zero.");
        }
    }

    @PublishedApi
    public static final long getProgressionLastElement(long j2, long j3, long j4) {
        int i2 = (j4 > 0L ? 1 : (j4 == 0L ? 0 : -1));
        if (i2 > 0) {
            return j2 >= j3 ? j3 : j3 - differenceModulo(j3, j2, j4);
        } else if (i2 < 0) {
            return j2 <= j3 ? j3 : j3 + differenceModulo(j2, j3, -j4);
        } else {
            throw new IllegalArgumentException("Step is zero.");
        }
    }

    private static final int mod(int i2, int i3) {
        int i4 = i2 % i3;
        return i4 >= 0 ? i4 : i4 + i3;
    }

    private static final long mod(long j2, long j3) {
        long j4 = j2 % j3;
        return j4 >= 0 ? j4 : j4 + j3;
    }
}
