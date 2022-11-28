package io.opencensus.common;

import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes3.dex */
public abstract class Timestamp implements Comparable<Timestamp> {
    public static Timestamp create(long j2, int i2) {
        if (j2 < -315576000000L) {
            throw new IllegalArgumentException("'seconds' is less than minimum (-315576000000): " + j2);
        } else if (j2 > 315576000000L) {
            throw new IllegalArgumentException("'seconds' is greater than maximum (315576000000): " + j2);
        } else if (i2 < 0) {
            throw new IllegalArgumentException("'nanos' is less than zero: " + i2);
        } else if (i2 <= 999999999) {
            return new AutoValue_Timestamp(j2, i2);
        } else {
            throw new IllegalArgumentException("'nanos' is greater than maximum (999999999): " + i2);
        }
    }

    private static long floorDiv(long j2, long j3) {
        return BigDecimal.valueOf(j2).divide(BigDecimal.valueOf(j3), 0, RoundingMode.FLOOR).longValue();
    }

    private static long floorMod(long j2, long j3) {
        return j2 - (floorDiv(j2, j3) * j3);
    }

    public static Timestamp fromMillis(long j2) {
        return create(floorDiv(j2, 1000L), (int) (((int) floorMod(j2, 1000L)) * 1000000));
    }

    private static Timestamp ofEpochSecond(long j2, long j3) {
        return create(TimeUtils.a(j2, floorDiv(j3, 1000000000L)), (int) floorMod(j3, 1000000000L));
    }

    private Timestamp plus(long j2, long j3) {
        if ((j2 | j3) == 0) {
            return this;
        }
        return ofEpochSecond(TimeUtils.a(TimeUtils.a(getSeconds(), j2), j3 / 1000000000), getNanos() + (j3 % 1000000000));
    }

    public Timestamp addDuration(Duration duration) {
        return plus(duration.getSeconds(), duration.getNanos());
    }

    public Timestamp addNanos(long j2) {
        return plus(0L, j2);
    }

    @Override // java.lang.Comparable
    public int compareTo(Timestamp timestamp) {
        int b2 = TimeUtils.b(getSeconds(), timestamp.getSeconds());
        return b2 != 0 ? b2 : TimeUtils.b(getNanos(), timestamp.getNanos());
    }

    public abstract int getNanos();

    public abstract long getSeconds();

    public Duration subtractTimestamp(Timestamp timestamp) {
        long j2;
        long seconds = getSeconds() - timestamp.getSeconds();
        int nanos = getNanos() - timestamp.getNanos();
        int i2 = (seconds > 0L ? 1 : (seconds == 0L ? 0 : -1));
        if (i2 >= 0 || nanos <= 0) {
            if (i2 > 0 && nanos < 0) {
                seconds--;
                j2 = nanos + 1000000000;
            }
            return Duration.create(seconds, nanos);
        }
        seconds++;
        j2 = nanos - 1000000000;
        nanos = (int) j2;
        return Duration.create(seconds, nanos);
    }
}
