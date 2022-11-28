package androidx.car.app.model;

import android.annotation.SuppressLint;
import androidx.annotation.DoNotInline;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.time.Duration;
import java.util.Objects;
/* loaded from: classes.dex */
public final class DurationSpan extends CarSpan {
    @Keep
    private final long mDurationSeconds;

    @RequiresApi(26)
    /* loaded from: classes.dex */
    private static final class Api26Impl {
        private Api26Impl() {
        }

        @NonNull
        @DoNotInline
        public static DurationSpan create(@NonNull Duration duration) {
            Objects.requireNonNull(duration);
            return new DurationSpan(duration.getSeconds());
        }
    }

    private DurationSpan() {
        this.mDurationSeconds = 0L;
    }

    DurationSpan(long j2) {
        this.mDurationSeconds = j2;
    }

    @NonNull
    public static DurationSpan create(long j2) {
        return new DurationSpan(j2);
    }

    @NonNull
    @RequiresApi(26)
    public static DurationSpan create(@NonNull Duration duration) {
        return Api26Impl.create(duration);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof DurationSpan) && this.mDurationSeconds == ((DurationSpan) obj).mDurationSeconds;
    }

    @SuppressLint({"MethodNameUnits"})
    public long getDurationSeconds() {
        return this.mDurationSeconds;
    }

    public int hashCode() {
        long j2 = this.mDurationSeconds;
        return (int) (j2 ^ (j2 >>> 32));
    }

    @NonNull
    public String toString() {
        return "[seconds: " + this.mDurationSeconds + "]";
    }
}
