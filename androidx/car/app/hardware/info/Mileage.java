package androidx.car.app.hardware.info;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.hardware.common.CarValue;
import java.util.Objects;
@RequiresCarApi(3)
/* loaded from: classes.dex */
public final class Mileage {
    @NonNull
    @Keep
    private final CarValue<Integer> mDistanceDisplayUnit;
    @Nullable
    @Keep
    private final CarValue<Float> mOdometerMeters;

    /* loaded from: classes.dex */
    public static final class Builder {
        CarValue<Float> mOdometerMeters = CarValue.UNIMPLEMENTED_FLOAT;
        CarValue<Integer> mDistanceDisplayUnit = CarValue.UNIMPLEMENTED_INTEGER;

        @NonNull
        public Mileage build() {
            return new Mileage(this);
        }

        @NonNull
        public Builder setDistanceDisplayUnit(@NonNull CarValue<Integer> carValue) {
            Objects.requireNonNull(carValue);
            this.mDistanceDisplayUnit = carValue;
            return this;
        }

        @NonNull
        public Builder setOdometerMeters(@NonNull CarValue<Float> carValue) {
            Objects.requireNonNull(carValue);
            this.mOdometerMeters = carValue;
            return this;
        }
    }

    private Mileage() {
        this.mOdometerMeters = CarValue.UNIMPLEMENTED_FLOAT;
        this.mDistanceDisplayUnit = CarValue.UNIMPLEMENTED_INTEGER;
    }

    Mileage(Builder builder) {
        CarValue<Float> carValue = builder.mOdometerMeters;
        Objects.requireNonNull(carValue);
        this.mOdometerMeters = carValue;
        CarValue<Integer> carValue2 = builder.mDistanceDisplayUnit;
        Objects.requireNonNull(carValue2);
        this.mDistanceDisplayUnit = carValue2;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Mileage) {
            Mileage mileage = (Mileage) obj;
            return Objects.equals(getOdometerMeters(), mileage.getOdometerMeters()) && Objects.equals(this.mDistanceDisplayUnit, mileage.mDistanceDisplayUnit);
        }
        return false;
    }

    @NonNull
    public CarValue<Integer> getDistanceDisplayUnit() {
        CarValue<Integer> carValue = this.mDistanceDisplayUnit;
        Objects.requireNonNull(carValue);
        return carValue;
    }

    @NonNull
    public CarValue<Float> getOdometerMeters() {
        CarValue<Float> carValue = this.mOdometerMeters;
        Objects.requireNonNull(carValue);
        return carValue;
    }

    public int hashCode() {
        return Objects.hash(getOdometerMeters(), this.mDistanceDisplayUnit);
    }

    @NonNull
    public String toString() {
        return "[ odometer: " + getOdometerMeters() + ", distance display unit: " + this.mDistanceDisplayUnit + "]";
    }
}
