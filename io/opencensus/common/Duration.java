package io.opencensus.common;

import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes3.dex */
public abstract class Duration implements Comparable<Duration> {
    public static Duration create(long j2, int i2) {
        if (j2 < -315576000000L) {
            throw new IllegalArgumentException("'seconds' is less than minimum (-315576000000): " + j2);
        } else if (j2 > 315576000000L) {
            throw new IllegalArgumentException("'seconds' is greater than maximum (315576000000): " + j2);
        } else if (i2 < -999999999) {
            throw new IllegalArgumentException("'nanos' is less than minimum (-999999999): " + i2);
        } else if (i2 > 999999999) {
            throw new IllegalArgumentException("'nanos' is greater than maximum (999999999): " + i2);
        } else {
            int i3 = (j2 > 0L ? 1 : (j2 == 0L ? 0 : -1));
            if ((i3 >= 0 || i2 <= 0) && (i3 <= 0 || i2 >= 0)) {
                return new AutoValue_Duration(j2, i2);
            }
            throw new IllegalArgumentException("'seconds' and 'nanos' have inconsistent sign: seconds=" + j2 + ", nanos=" + i2);
        }
    }

    public static Duration fromMillis(long j2) {
        return create(j2 / 1000, (int) ((j2 % 1000) * 1000000));
    }

    @Override // java.lang.Comparable
    public int compareTo(Duration duration) {
        int b2 = TimeUtils.b(getSeconds(), duration.getSeconds());
        return b2 != 0 ? b2 : TimeUtils.b(getNanos(), duration.getNanos());
    }

    public abstract int getNanos();

    public abstract long getSeconds();

    public long toMillis() {
        return TimeUnit.SECONDS.toMillis(getSeconds()) + TimeUnit.NANOSECONDS.toMillis(getNanos());
    }
}
