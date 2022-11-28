package androidx.car.app.navigation.model;

import androidx.annotation.IntRange;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.car.app.model.CarIcon;
import androidx.car.app.model.constraints.CarIconConstraints;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;
/* loaded from: classes.dex */
public final class Maneuver {
    public static final int TYPE_DEPART = 1;
    public static final int TYPE_DESTINATION = 39;
    public static final int TYPE_DESTINATION_LEFT = 41;
    public static final int TYPE_DESTINATION_RIGHT = 42;
    public static final int TYPE_DESTINATION_STRAIGHT = 40;
    public static final int TYPE_FERRY_BOAT = 37;
    public static final int TYPE_FERRY_BOAT_LEFT = 47;
    public static final int TYPE_FERRY_BOAT_RIGHT = 48;
    public static final int TYPE_FERRY_TRAIN = 38;
    public static final int TYPE_FERRY_TRAIN_LEFT = 49;
    public static final int TYPE_FERRY_TRAIN_RIGHT = 50;
    public static final int TYPE_FORK_LEFT = 25;
    public static final int TYPE_FORK_RIGHT = 26;
    public static final int TYPE_KEEP_LEFT = 3;
    public static final int TYPE_KEEP_RIGHT = 4;
    public static final int TYPE_MERGE_LEFT = 27;
    public static final int TYPE_MERGE_RIGHT = 28;
    public static final int TYPE_MERGE_SIDE_UNSPECIFIED = 29;
    public static final int TYPE_NAME_CHANGE = 2;
    public static final int TYPE_OFF_RAMP_NORMAL_LEFT = 23;
    public static final int TYPE_OFF_RAMP_NORMAL_RIGHT = 24;
    public static final int TYPE_OFF_RAMP_SLIGHT_LEFT = 21;
    public static final int TYPE_OFF_RAMP_SLIGHT_RIGHT = 22;
    public static final int TYPE_ON_RAMP_NORMAL_LEFT = 15;
    public static final int TYPE_ON_RAMP_NORMAL_RIGHT = 16;
    public static final int TYPE_ON_RAMP_SHARP_LEFT = 17;
    public static final int TYPE_ON_RAMP_SHARP_RIGHT = 18;
    public static final int TYPE_ON_RAMP_SLIGHT_LEFT = 13;
    public static final int TYPE_ON_RAMP_SLIGHT_RIGHT = 14;
    public static final int TYPE_ON_RAMP_U_TURN_LEFT = 19;
    public static final int TYPE_ON_RAMP_U_TURN_RIGHT = 20;
    public static final int TYPE_ROUNDABOUT_ENTER_AND_EXIT_CCW = 34;
    public static final int TYPE_ROUNDABOUT_ENTER_AND_EXIT_CCW_WITH_ANGLE = 35;
    public static final int TYPE_ROUNDABOUT_ENTER_AND_EXIT_CW = 32;
    public static final int TYPE_ROUNDABOUT_ENTER_AND_EXIT_CW_WITH_ANGLE = 33;
    public static final int TYPE_ROUNDABOUT_ENTER_CCW = 45;
    public static final int TYPE_ROUNDABOUT_ENTER_CW = 43;
    public static final int TYPE_ROUNDABOUT_EXIT_CCW = 46;
    public static final int TYPE_ROUNDABOUT_EXIT_CW = 44;
    public static final int TYPE_STRAIGHT = 36;
    public static final int TYPE_TURN_NORMAL_LEFT = 7;
    public static final int TYPE_TURN_NORMAL_RIGHT = 8;
    public static final int TYPE_TURN_SHARP_LEFT = 9;
    public static final int TYPE_TURN_SHARP_RIGHT = 10;
    public static final int TYPE_TURN_SLIGHT_LEFT = 5;
    public static final int TYPE_TURN_SLIGHT_RIGHT = 6;
    public static final int TYPE_UNKNOWN = 0;
    public static final int TYPE_U_TURN_LEFT = 11;
    public static final int TYPE_U_TURN_RIGHT = 12;
    @Nullable
    @Keep
    private final CarIcon mIcon;
    @Keep
    private final int mRoundaboutExitAngle;
    @Keep
    private final int mRoundaboutExitNumber;
    @Keep
    private final int mType;

    /* loaded from: classes.dex */
    public static final class Builder {
        @Nullable
        private CarIcon mIcon;
        private boolean mIsRoundaboutExitAngleSet;
        private boolean mIsRoundaboutExitNumberSet;
        private int mRoundaboutExitAngle;
        private int mRoundaboutExitNumber;
        private final int mType;

        public Builder(int i2) {
            if (!Maneuver.isValidType(i2)) {
                throw new IllegalArgumentException("Maneuver must have a valid type");
            }
            this.mType = i2;
        }

        @NonNull
        public Maneuver build() {
            if (!Maneuver.isValidTypeWithExitNumber(this.mType) || this.mIsRoundaboutExitNumberSet) {
                if (!Maneuver.isValidTypeWithExitAngle(this.mType) || this.mIsRoundaboutExitAngleSet) {
                    return new Maneuver(this.mType, this.mRoundaboutExitNumber, this.mRoundaboutExitAngle, this.mIcon);
                }
                throw new IllegalArgumentException("Maneuver missing roundaboutExitAngle");
            }
            throw new IllegalArgumentException("Maneuver missing roundaboutExitNumber");
        }

        @NonNull
        public Builder setIcon(@NonNull CarIcon carIcon) {
            Objects.requireNonNull(carIcon);
            this.mIcon = carIcon;
            return this;
        }

        @NonNull
        public Builder setRoundaboutExitAngle(@IntRange(from = 1, to = 360) int i2) {
            if (Maneuver.isValidTypeWithExitAngle(this.mType)) {
                if (i2 < 1 || i2 > 360) {
                    throw new IllegalArgumentException("Maneuver must include a valid exit angle");
                }
                this.mIsRoundaboutExitAngleSet = true;
                this.mRoundaboutExitAngle = i2;
                return this;
            }
            throw new IllegalArgumentException("Maneuver does not include roundaboutExitAngle");
        }

        @NonNull
        public Builder setRoundaboutExitNumber(@IntRange(from = 1) int i2) {
            if (Maneuver.isValidTypeWithExitNumber(this.mType)) {
                if (i2 >= 1) {
                    this.mIsRoundaboutExitNumberSet = true;
                    this.mRoundaboutExitNumber = i2;
                    return this;
                }
                throw new IllegalArgumentException("Maneuver must include a valid exit number");
            }
            throw new IllegalArgumentException("Maneuver does not include roundaboutExitNumber");
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public @interface Type {
    }

    private Maneuver() {
        this.mType = 0;
        this.mRoundaboutExitNumber = 0;
        this.mRoundaboutExitAngle = 0;
        this.mIcon = null;
    }

    Maneuver(int i2, int i3, int i4, @Nullable CarIcon carIcon) {
        this.mType = i2;
        this.mRoundaboutExitNumber = i3;
        this.mRoundaboutExitAngle = i4;
        CarIconConstraints.DEFAULT.validateOrThrow(carIcon);
        this.mIcon = carIcon;
    }

    static boolean isValidType(int i2) {
        return i2 >= 0 && i2 <= 50;
    }

    static boolean isValidTypeWithExitAngle(int i2) {
        return i2 == 33 || i2 == 35;
    }

    static boolean isValidTypeWithExitNumber(int i2) {
        return i2 == 32 || i2 == 34 || i2 == 33 || i2 == 35;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Maneuver) {
            Maneuver maneuver = (Maneuver) obj;
            return this.mType == maneuver.mType && this.mRoundaboutExitNumber == maneuver.mRoundaboutExitNumber && this.mRoundaboutExitAngle == maneuver.mRoundaboutExitAngle && Objects.equals(this.mIcon, maneuver.mIcon);
        }
        return false;
    }

    @Nullable
    public CarIcon getIcon() {
        return this.mIcon;
    }

    public int getRoundaboutExitAngle() {
        return this.mRoundaboutExitAngle;
    }

    public int getRoundaboutExitNumber() {
        return this.mRoundaboutExitNumber;
    }

    public int getType() {
        return this.mType;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mType), Integer.valueOf(this.mRoundaboutExitNumber), Integer.valueOf(this.mRoundaboutExitAngle), this.mIcon);
    }

    @NonNull
    public String toString() {
        return "[type: " + this.mType + ", exit #: " + this.mRoundaboutExitNumber + ", exit angle: " + this.mRoundaboutExitAngle + ", icon: " + this.mIcon + "]";
    }
}
