package androidx.car.app.hardware.info;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.hardware.common.CarValue;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.Objects;
@RequiresCarApi(3)
/* loaded from: classes.dex */
public final class EnergyProfile {
    public static final int EVCONNECTOR_TYPE_CHADEMO = 3;
    public static final int EVCONNECTOR_TYPE_COMBO_1 = 4;
    public static final int EVCONNECTOR_TYPE_COMBO_2 = 5;
    public static final int EVCONNECTOR_TYPE_GBT = 9;
    public static final int EVCONNECTOR_TYPE_GBT_DC = 10;
    public static final int EVCONNECTOR_TYPE_J1772 = 1;
    public static final int EVCONNECTOR_TYPE_MENNEKES = 2;
    public static final int EVCONNECTOR_TYPE_OTHER = 101;
    public static final int EVCONNECTOR_TYPE_SCAME = 11;
    public static final int EVCONNECTOR_TYPE_TESLA_HPWC = 7;
    public static final int EVCONNECTOR_TYPE_TESLA_ROADSTER = 6;
    public static final int EVCONNECTOR_TYPE_TESLA_SUPERCHARGER = 8;
    public static final int EVCONNECTOR_TYPE_UNKNOWN = 0;
    public static final int FUEL_TYPE_BIODIESEL = 5;
    public static final int FUEL_TYPE_CNG = 8;
    public static final int FUEL_TYPE_DIESEL_1 = 3;
    public static final int FUEL_TYPE_DIESEL_2 = 4;
    public static final int FUEL_TYPE_E85 = 6;
    public static final int FUEL_TYPE_ELECTRIC = 10;
    public static final int FUEL_TYPE_HYDROGEN = 11;
    public static final int FUEL_TYPE_LEADED = 2;
    public static final int FUEL_TYPE_LNG = 9;
    public static final int FUEL_TYPE_LPG = 7;
    public static final int FUEL_TYPE_OTHER = 12;
    public static final int FUEL_TYPE_UNKNOWN = 0;
    public static final int FUEL_TYPE_UNLEADED = 1;
    @NonNull
    @Keep
    private final CarValue<List<Integer>> mEvConnectorTypes;
    @NonNull
    @Keep
    private final CarValue<List<Integer>> mFuelTypes;

    /* loaded from: classes.dex */
    public static final class Builder {
        CarValue<List<Integer>> mEvConnectorTypes;
        CarValue<List<Integer>> mFuelTypes;

        public Builder() {
            CarValue<List<Integer>> carValue = CarValue.UNIMPLEMENTED_INTEGER_LIST;
            this.mEvConnectorTypes = carValue;
            this.mFuelTypes = carValue;
        }

        @NonNull
        public EnergyProfile build() {
            return new EnergyProfile(this);
        }

        @NonNull
        public Builder setEvConnectorTypes(@NonNull CarValue<List<Integer>> carValue) {
            Objects.requireNonNull(carValue);
            this.mEvConnectorTypes = carValue;
            return this;
        }

        @NonNull
        public Builder setFuelTypes(@NonNull CarValue<List<Integer>> carValue) {
            Objects.requireNonNull(carValue);
            this.mFuelTypes = carValue;
            return this;
        }
    }

    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public @interface EvConnectorType {
    }

    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public @interface FuelType {
    }

    private EnergyProfile() {
        CarValue<List<Integer>> carValue = CarValue.UNIMPLEMENTED_INTEGER_LIST;
        this.mEvConnectorTypes = carValue;
        this.mFuelTypes = carValue;
    }

    EnergyProfile(Builder builder) {
        CarValue<List<Integer>> carValue = builder.mEvConnectorTypes;
        Objects.requireNonNull(carValue);
        this.mEvConnectorTypes = carValue;
        CarValue<List<Integer>> carValue2 = builder.mFuelTypes;
        Objects.requireNonNull(carValue2);
        this.mFuelTypes = carValue2;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof EnergyProfile) {
            EnergyProfile energyProfile = (EnergyProfile) obj;
            return Objects.equals(this.mEvConnectorTypes, energyProfile.mEvConnectorTypes) && Objects.equals(this.mFuelTypes, energyProfile.mFuelTypes);
        }
        return false;
    }

    @NonNull
    public CarValue<List<Integer>> getEvConnectorTypes() {
        CarValue<List<Integer>> carValue = this.mEvConnectorTypes;
        Objects.requireNonNull(carValue);
        return carValue;
    }

    @NonNull
    public CarValue<List<Integer>> getFuelTypes() {
        CarValue<List<Integer>> carValue = this.mFuelTypes;
        Objects.requireNonNull(carValue);
        return carValue;
    }

    public int hashCode() {
        return Objects.hash(this.mEvConnectorTypes, this.mFuelTypes);
    }

    @NonNull
    public String toString() {
        return "[ evConnectorTypes: " + this.mEvConnectorTypes + ", fuelTypes: " + this.mFuelTypes + "]";
    }
}
