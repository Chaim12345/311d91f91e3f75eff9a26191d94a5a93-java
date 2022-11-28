package androidx.car.app.model;

import android.annotation.SuppressLint;
import androidx.annotation.DoNotInline;
import androidx.annotation.IntRange;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public final class DateTimeWithZone {
    private static final long MAX_ZONE_OFFSET_SECONDS = TimeUnit.HOURS.toSeconds(1) * 18;
    @Keep
    private final long mTimeSinceEpochMillis;
    @Keep
    private final int mZoneOffsetSeconds;
    @Nullable
    @Keep
    private final String mZoneShortName;

    @RequiresApi(26)
    /* loaded from: classes.dex */
    private static final class Api26Impl {
        private Api26Impl() {
        }

        @NonNull
        @DoNotInline
        public static DateTimeWithZone create(@NonNull ZonedDateTime zonedDateTime) {
            Objects.requireNonNull(zonedDateTime);
            LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();
            ZoneId zone = zonedDateTime.getZone();
            ZoneOffset offset = zone.getRules().getOffset(localDateTime);
            return DateTimeWithZone.create(TimeUnit.SECONDS.toMillis(localDateTime.toEpochSecond(offset)), offset.getTotalSeconds(), zone.getDisplayName(TextStyle.SHORT, Locale.getDefault()));
        }
    }

    private DateTimeWithZone() {
        this.mTimeSinceEpochMillis = 0L;
        this.mZoneOffsetSeconds = 0;
        this.mZoneShortName = null;
    }

    private DateTimeWithZone(long j2, int i2, @Nullable String str) {
        this.mTimeSinceEpochMillis = j2;
        this.mZoneOffsetSeconds = i2;
        this.mZoneShortName = str;
    }

    @NonNull
    public static DateTimeWithZone create(long j2, @IntRange(from = -64800, to = 64800) int i2, @NonNull String str) {
        if (j2 >= 0) {
            if (Math.abs(i2) <= MAX_ZONE_OFFSET_SECONDS) {
                Objects.requireNonNull(str);
                if (str.isEmpty()) {
                    throw new IllegalArgumentException("The time zone short name can not be null or empty");
                }
                return new DateTimeWithZone(j2, i2, str);
            }
            throw new IllegalArgumentException("Zone offset not in valid range: -18:00 to +18:00");
        }
        throw new IllegalArgumentException("Time since epoch must be greater than or equal to zero");
    }

    @NonNull
    public static DateTimeWithZone create(long j2, @NonNull TimeZone timeZone) {
        if (j2 >= 0) {
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            Objects.requireNonNull(timeZone);
            return create(j2, (int) timeUnit.toSeconds(timeZone.getOffset(j2)), timeZone.getDisplayName(false, 0));
        }
        throw new IllegalArgumentException("timeSinceEpochMillis must be greater than or equal to zero");
    }

    @NonNull
    @RequiresApi(26)
    public static DateTimeWithZone create(@NonNull ZonedDateTime zonedDateTime) {
        return Api26Impl.create(zonedDateTime);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DateTimeWithZone) {
            DateTimeWithZone dateTimeWithZone = (DateTimeWithZone) obj;
            return this.mTimeSinceEpochMillis == dateTimeWithZone.mTimeSinceEpochMillis && this.mZoneOffsetSeconds == dateTimeWithZone.mZoneOffsetSeconds && Objects.equals(this.mZoneShortName, dateTimeWithZone.mZoneShortName);
        }
        return false;
    }

    public long getTimeSinceEpochMillis() {
        return this.mTimeSinceEpochMillis;
    }

    @SuppressLint({"MethodNameUnits"})
    public int getZoneOffsetSeconds() {
        return this.mZoneOffsetSeconds;
    }

    @Nullable
    public String getZoneShortName() {
        return this.mZoneShortName;
    }

    public int hashCode() {
        return Objects.hash(Long.valueOf(this.mTimeSinceEpochMillis), Integer.valueOf(this.mZoneOffsetSeconds), this.mZoneShortName);
    }

    @NonNull
    public String toString() {
        return "[time since epoch (ms): " + this.mTimeSinceEpochMillis + "( " + new Date(this.mTimeSinceEpochMillis) + ")  zone offset (s): " + this.mZoneOffsetSeconds + ", zone: " + this.mZoneShortName + "]";
    }
}
