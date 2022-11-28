package androidx.car.app.hardware.info;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.hardware.common.CarValue;
import java.util.Objects;
@RequiresCarApi(3)
/* loaded from: classes.dex */
public final class EnergyLevel {
    @NonNull
    @Keep
    private final CarValue<Float> mBatteryPercent;
    @NonNull
    @Keep
    private final CarValue<Integer> mDistanceDisplayUnit;
    @NonNull
    @Keep
    private final CarValue<Boolean> mEnergyIsLow;
    @NonNull
    @Keep
    private final CarValue<Float> mFuelPercent;
    @Nullable
    @Keep
    private final CarValue<Float> mRangeRemainingMeters;

    /* loaded from: classes.dex */
    public static final class Builder {
        CarValue<Float> mBatteryPercent;
        CarValue<Integer> mDistanceDisplayUnit;
        CarValue<Boolean> mEnergyIsLow;
        CarValue<Float> mFuelPercent;
        CarValue<Float> mRangeRemainingMeters;

        public Builder() {
            CarValue<Float> carValue = CarValue.UNIMPLEMENTED_FLOAT;
            this.mBatteryPercent = carValue;
            this.mFuelPercent = carValue;
            this.mEnergyIsLow = CarValue.UNIMPLEMENTED_BOOLEAN;
            this.mRangeRemainingMeters = carValue;
            this.mDistanceDisplayUnit = CarValue.UNIMPLEMENTED_INTEGER;
        }

        @NonNull
        public EnergyLevel build() {
            return new EnergyLevel(this);
        }

        @NonNull
        public Builder setBatteryPercent(@NonNull CarValue<Float> carValue) {
            Objects.requireNonNull(carValue);
            this.mBatteryPercent = carValue;
            return this;
        }

        @NonNull
        public Builder setDistanceDisplayUnit(@NonNull CarValue<Integer> carValue) {
            Objects.requireNonNull(carValue);
            this.mDistanceDisplayUnit = carValue;
            return this;
        }

        @NonNull
        public Builder setEnergyIsLow(@NonNull CarValue<Boolean> carValue) {
            Objects.requireNonNull(carValue);
            this.mEnergyIsLow = carValue;
            return this;
        }

        @NonNull
        public Builder setFuelPercent(@NonNull CarValue<Float> carValue) {
            Objects.requireNonNull(carValue);
            this.mFuelPercent = carValue;
            return this;
        }

        @NonNull
        public Builder setRangeRemainingMeters(@NonNull CarValue<Float> carValue) {
            Objects.requireNonNull(carValue);
            this.mRangeRemainingMeters = carValue;
            return this;
        }
    }

    private EnergyLevel() {
        CarValue<Float> carValue = CarValue.UNIMPLEMENTED_FLOAT;
        this.mBatteryPercent = carValue;
        this.mFuelPercent = carValue;
        this.mEnergyIsLow = CarValue.UNIMPLEMENTED_BOOLEAN;
        this.mRangeRemainingMeters = carValue;
        this.mDistanceDisplayUnit = CarValue.UNIMPLEMENTED_INTEGER;
    }

    EnergyLevel(Builder builder) {
        CarValue<Float> carValue = builder.mBatteryPercent;
        Objects.requireNonNull(carValue);
        this.mBatteryPercent = carValue;
        CarValue<Float> carValue2 = builder.mFuelPercent;
        Objects.requireNonNull(carValue2);
        this.mFuelPercent = carValue2;
        CarValue<Boolean> carValue3 = builder.mEnergyIsLow;
        Objects.requireNonNull(carValue3);
        this.mEnergyIsLow = carValue3;
        CarValue<Float> carValue4 = builder.mRangeRemainingMeters;
        Objects.requireNonNull(carValue4);
        this.mRangeRemainingMeters = carValue4;
        CarValue<Integer> carValue5 = builder.mDistanceDisplayUnit;
        Objects.requireNonNull(carValue5);
        this.mDistanceDisplayUnit = carValue5;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof EnergyLevel) {
            EnergyLevel energyLevel = (EnergyLevel) obj;
            return Objects.equals(this.mBatteryPercent, energyLevel.mBatteryPercent) && Objects.equals(this.mFuelPercent, energyLevel.mFuelPercent) && Objects.equals(this.mEnergyIsLow, energyLevel.mEnergyIsLow) && Objects.equals(getRangeRemainingMeters(), energyLevel.getRangeRemainingMeters()) && Objects.equals(this.mDistanceDisplayUnit, energyLevel.mDistanceDisplayUnit);
        }
        return false;
    }

    @NonNull
    public CarValue<Float> getBatteryPercent() {
        CarValue<Float> carValue = this.mBatteryPercent;
        Objects.requireNonNull(carValue);
        return carValue;
    }

    @NonNull
    public CarValue<Integer> getDistanceDisplayUnit() {
        CarValue<Integer> carValue = this.mDistanceDisplayUnit;
        Objects.requireNonNull(carValue);
        return carValue;
    }

    @NonNull
    public CarValue<Boolean> getEnergyIsLow() {
        CarValue<Boolean> carValue = this.mEnergyIsLow;
        Objects.requireNonNull(carValue);
        return carValue;
    }

    @NonNull
    public CarValue<Float> getFuelPercent() {
        CarValue<Float> carValue = this.mFuelPercent;
        Objects.requireNonNull(carValue);
        return carValue;
    }

    @NonNull
    public CarValue<Float> getRangeRemainingMeters() {
        CarValue<Float> carValue = this.mRangeRemainingMeters;
        Objects.requireNonNull(carValue);
        return carValue;
    }

    public int hashCode() {
        return Objects.hash(this.mBatteryPercent, this.mFuelPercent, this.mEnergyIsLow, getRangeRemainingMeters(), this.mDistanceDisplayUnit);
    }

    @NonNull
    public String toString() {
        return "[ battery percent: " + this.mBatteryPercent + ", fuel percent: " + this.mFuelPercent + ", energyIsLow: " + this.mEnergyIsLow + ", range remaining: " + getRangeRemainingMeters() + ", distance display unit: " + this.mDistanceDisplayUnit + "]";
    }
}
