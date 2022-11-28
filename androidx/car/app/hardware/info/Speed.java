package androidx.car.app.hardware.info;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.hardware.common.CarValue;
import java.util.Objects;
@RequiresCarApi(3)
/* loaded from: classes.dex */
public final class Speed {
    @Nullable
    @Keep
    private final CarValue<Float> mDisplaySpeedMetersPerSecond;
    @Nullable
    @Keep
    private final CarValue<Float> mRawSpeedMetersPerSecond;
    @NonNull
    @Keep
    private final CarValue<Integer> mSpeedDisplayUnit;

    /* loaded from: classes.dex */
    public static final class Builder {
        CarValue<Float> mDisplaySpeedMetersPerSecond;
        CarValue<Float> mRawSpeedMetersPerSecond;
        CarValue<Integer> mSpeedDisplayUnit;

        public Builder() {
            CarValue<Float> carValue = CarValue.UNIMPLEMENTED_FLOAT;
            this.mRawSpeedMetersPerSecond = carValue;
            this.mDisplaySpeedMetersPerSecond = carValue;
            this.mSpeedDisplayUnit = CarValue.UNIMPLEMENTED_INTEGER;
        }

        @NonNull
        public Speed build() {
            return new Speed(this);
        }

        @NonNull
        public Builder setDisplaySpeedMetersPerSecond(@NonNull CarValue<Float> carValue) {
            Objects.requireNonNull(carValue);
            this.mDisplaySpeedMetersPerSecond = carValue;
            return this;
        }

        @NonNull
        public Builder setRawSpeedMetersPerSecond(@NonNull CarValue<Float> carValue) {
            Objects.requireNonNull(carValue);
            this.mRawSpeedMetersPerSecond = carValue;
            return this;
        }

        @NonNull
        public Builder setSpeedDisplayUnit(@NonNull CarValue<Integer> carValue) {
            Objects.requireNonNull(carValue);
            this.mSpeedDisplayUnit = carValue;
            return this;
        }
    }

    private Speed() {
        CarValue<Float> carValue = CarValue.UNIMPLEMENTED_FLOAT;
        this.mRawSpeedMetersPerSecond = carValue;
        this.mDisplaySpeedMetersPerSecond = carValue;
        this.mSpeedDisplayUnit = CarValue.UNIMPLEMENTED_INTEGER;
    }

    Speed(Builder builder) {
        CarValue<Float> carValue = builder.mRawSpeedMetersPerSecond;
        Objects.requireNonNull(carValue);
        this.mRawSpeedMetersPerSecond = carValue;
        CarValue<Float> carValue2 = builder.mDisplaySpeedMetersPerSecond;
        Objects.requireNonNull(carValue2);
        this.mDisplaySpeedMetersPerSecond = carValue2;
        CarValue<Integer> carValue3 = builder.mSpeedDisplayUnit;
        Objects.requireNonNull(carValue3);
        this.mSpeedDisplayUnit = carValue3;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Speed) {
            Speed speed = (Speed) obj;
            return Objects.equals(getRawSpeedMetersPerSecond(), speed.getRawSpeedMetersPerSecond()) && Objects.equals(getDisplaySpeedMetersPerSecond(), speed.getDisplaySpeedMetersPerSecond()) && Objects.equals(this.mSpeedDisplayUnit, speed.mSpeedDisplayUnit);
        }
        return false;
    }

    @NonNull
    public CarValue<Float> getDisplaySpeedMetersPerSecond() {
        CarValue<Float> carValue = this.mDisplaySpeedMetersPerSecond;
        Objects.requireNonNull(carValue);
        return carValue;
    }

    @NonNull
    public CarValue<Float> getRawSpeedMetersPerSecond() {
        CarValue<Float> carValue = this.mRawSpeedMetersPerSecond;
        Objects.requireNonNull(carValue);
        return carValue;
    }

    @NonNull
    public CarValue<Integer> getSpeedDisplayUnit() {
        CarValue<Integer> carValue = this.mSpeedDisplayUnit;
        Objects.requireNonNull(carValue);
        return carValue;
    }

    public int hashCode() {
        return Objects.hash(getRawSpeedMetersPerSecond(), getDisplaySpeedMetersPerSecond(), this.mSpeedDisplayUnit);
    }

    @NonNull
    public String toString() {
        return "[ raw speed: " + getRawSpeedMetersPerSecond() + ", display speed: " + getDisplaySpeedMetersPerSecond() + ", speed display unit: " + this.mSpeedDisplayUnit + "]";
    }
}
