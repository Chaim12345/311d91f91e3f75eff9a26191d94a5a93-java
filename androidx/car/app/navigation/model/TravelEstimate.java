package androidx.car.app.navigation.model;

import android.annotation.SuppressLint;
import androidx.annotation.DoNotInline;
import androidx.annotation.IntRange;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.car.app.model.CarColor;
import androidx.car.app.model.DateTimeWithZone;
import androidx.car.app.model.Distance;
import androidx.car.app.model.constraints.CarColorConstraints;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Objects;
/* loaded from: classes.dex */
public final class TravelEstimate {
    public static final long REMAINING_TIME_UNKNOWN = -1;
    @Nullable
    @Keep
    private final DateTimeWithZone mArrivalTimeAtDestination;
    @Nullable
    @Keep
    private final Distance mRemainingDistance;
    @Keep
    private final CarColor mRemainingDistanceColor;
    @Keep
    private final CarColor mRemainingTimeColor;
    @Keep
    private final long mRemainingTimeSeconds;

    /* loaded from: classes.dex */
    public static final class Builder {
        final DateTimeWithZone mArrivalTimeAtDestination;
        final Distance mRemainingDistance;
        CarColor mRemainingDistanceColor;
        CarColor mRemainingTimeColor;
        long mRemainingTimeSeconds = -1;

        @RequiresApi(26)
        /* loaded from: classes.dex */
        private static final class Api26Impl {
            private Api26Impl() {
            }

            @NonNull
            @DoNotInline
            public static Builder setRemainingTime(Builder builder, @NonNull Duration duration) {
                Objects.requireNonNull(duration);
                builder.mRemainingTimeSeconds = Builder.validateRemainingTime(duration.getSeconds());
                return builder;
            }
        }

        public Builder(@NonNull Distance distance, @NonNull DateTimeWithZone dateTimeWithZone) {
            CarColor carColor = CarColor.DEFAULT;
            this.mRemainingTimeColor = carColor;
            this.mRemainingDistanceColor = carColor;
            Objects.requireNonNull(distance);
            this.mRemainingDistance = distance;
            Objects.requireNonNull(dateTimeWithZone);
            this.mArrivalTimeAtDestination = dateTimeWithZone;
        }

        @RequiresApi(26)
        public Builder(@NonNull Distance distance, @NonNull ZonedDateTime zonedDateTime) {
            CarColor carColor = CarColor.DEFAULT;
            this.mRemainingTimeColor = carColor;
            this.mRemainingDistanceColor = carColor;
            Objects.requireNonNull(distance);
            this.mRemainingDistance = distance;
            Objects.requireNonNull(zonedDateTime);
            this.mArrivalTimeAtDestination = DateTimeWithZone.create(zonedDateTime);
        }

        static long validateRemainingTime(long j2) {
            if (j2 >= 0 || j2 == -1) {
                return j2;
            }
            throw new IllegalArgumentException("Remaining time must be a larger than or equal to zero, or set to REMAINING_TIME_UNKNOWN");
        }

        @NonNull
        public TravelEstimate build() {
            return new TravelEstimate(this);
        }

        @NonNull
        public Builder setRemainingDistanceColor(@NonNull CarColor carColor) {
            CarColorConstraints carColorConstraints = CarColorConstraints.STANDARD_ONLY;
            Objects.requireNonNull(carColor);
            carColorConstraints.validateOrThrow(carColor);
            this.mRemainingDistanceColor = carColor;
            return this;
        }

        @NonNull
        @RequiresApi(26)
        @SuppressLint({"MissingGetterMatchingBuilder"})
        public Builder setRemainingTime(@NonNull Duration duration) {
            return Api26Impl.setRemainingTime(this, duration);
        }

        @NonNull
        public Builder setRemainingTimeColor(@NonNull CarColor carColor) {
            CarColorConstraints carColorConstraints = CarColorConstraints.STANDARD_ONLY;
            Objects.requireNonNull(carColor);
            carColorConstraints.validateOrThrow(carColor);
            this.mRemainingTimeColor = carColor;
            return this;
        }

        @NonNull
        public Builder setRemainingTimeSeconds(@IntRange(from = -1) long j2) {
            this.mRemainingTimeSeconds = validateRemainingTime(j2);
            return this;
        }
    }

    private TravelEstimate() {
        this.mRemainingDistance = null;
        this.mRemainingTimeSeconds = 0L;
        this.mArrivalTimeAtDestination = null;
        CarColor carColor = CarColor.DEFAULT;
        this.mRemainingTimeColor = carColor;
        this.mRemainingDistanceColor = carColor;
    }

    TravelEstimate(Builder builder) {
        this.mRemainingDistance = builder.mRemainingDistance;
        this.mRemainingTimeSeconds = builder.mRemainingTimeSeconds;
        this.mArrivalTimeAtDestination = builder.mArrivalTimeAtDestination;
        this.mRemainingTimeColor = builder.mRemainingTimeColor;
        this.mRemainingDistanceColor = builder.mRemainingDistanceColor;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof TravelEstimate) {
            TravelEstimate travelEstimate = (TravelEstimate) obj;
            return Objects.equals(this.mRemainingDistance, travelEstimate.mRemainingDistance) && this.mRemainingTimeSeconds == travelEstimate.mRemainingTimeSeconds && Objects.equals(this.mArrivalTimeAtDestination, travelEstimate.mArrivalTimeAtDestination) && Objects.equals(this.mRemainingTimeColor, travelEstimate.mRemainingTimeColor) && Objects.equals(this.mRemainingDistanceColor, travelEstimate.mRemainingDistanceColor);
        }
        return false;
    }

    @Nullable
    public DateTimeWithZone getArrivalTimeAtDestination() {
        return this.mArrivalTimeAtDestination;
    }

    @Nullable
    public Distance getRemainingDistance() {
        return this.mRemainingDistance;
    }

    @Nullable
    public CarColor getRemainingDistanceColor() {
        return this.mRemainingDistanceColor;
    }

    @Nullable
    public CarColor getRemainingTimeColor() {
        return this.mRemainingTimeColor;
    }

    public long getRemainingTimeSeconds() {
        long j2 = this.mRemainingTimeSeconds;
        if (j2 >= 0) {
            return j2;
        }
        return -1L;
    }

    public int hashCode() {
        return Objects.hash(this.mRemainingDistance, Long.valueOf(this.mRemainingTimeSeconds), this.mArrivalTimeAtDestination, this.mRemainingTimeColor, this.mRemainingDistanceColor);
    }

    @NonNull
    public String toString() {
        return "[ remaining distance: " + this.mRemainingDistance + ", time (s): " + this.mRemainingTimeSeconds + ", ETA: " + this.mArrivalTimeAtDestination + "]";
    }
}
