package androidx.car.app.hardware.info;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.hardware.common.CarValue;
import java.util.Objects;
@RequiresCarApi(3)
/* loaded from: classes.dex */
public final class Model {
    @NonNull
    @Keep
    private final CarValue<String> mManufacturer;
    @NonNull
    @Keep
    private final CarValue<String> mName;
    @NonNull
    @Keep
    private final CarValue<Integer> mYear;

    /* loaded from: classes.dex */
    public static final class Builder {
        CarValue<String> mManufacturer;
        CarValue<String> mName;
        CarValue<Integer> mYear;

        public Builder() {
            CarValue<String> carValue = CarValue.UNIMPLEMENTED_STRING;
            this.mName = carValue;
            this.mYear = CarValue.UNIMPLEMENTED_INTEGER;
            this.mManufacturer = carValue;
        }

        @NonNull
        public Model build() {
            return new Model(this);
        }

        @NonNull
        public Builder setManufacturer(@NonNull CarValue<String> carValue) {
            Objects.requireNonNull(carValue);
            this.mManufacturer = carValue;
            return this;
        }

        @NonNull
        public Builder setName(@NonNull CarValue<String> carValue) {
            Objects.requireNonNull(carValue);
            this.mName = carValue;
            return this;
        }

        @NonNull
        public Builder setYear(@NonNull CarValue<Integer> carValue) {
            Objects.requireNonNull(carValue);
            this.mYear = carValue;
            return this;
        }
    }

    private Model() {
        CarValue<String> carValue = CarValue.UNIMPLEMENTED_STRING;
        this.mName = carValue;
        this.mManufacturer = carValue;
        this.mYear = CarValue.UNIMPLEMENTED_INTEGER;
    }

    Model(Builder builder) {
        CarValue<String> carValue = builder.mName;
        Objects.requireNonNull(carValue);
        this.mName = carValue;
        CarValue<String> carValue2 = builder.mManufacturer;
        Objects.requireNonNull(carValue2);
        this.mManufacturer = carValue2;
        CarValue<Integer> carValue3 = builder.mYear;
        Objects.requireNonNull(carValue3);
        this.mYear = carValue3;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Model) {
            Model model = (Model) obj;
            return Objects.equals(this.mName, model.mName) && Objects.equals(this.mYear, model.mYear) && Objects.equals(this.mManufacturer, model.mManufacturer);
        }
        return false;
    }

    @NonNull
    public CarValue<String> getManufacturer() {
        CarValue<String> carValue = this.mManufacturer;
        Objects.requireNonNull(carValue);
        return carValue;
    }

    @NonNull
    public CarValue<String> getName() {
        CarValue<String> carValue = this.mName;
        Objects.requireNonNull(carValue);
        return carValue;
    }

    @NonNull
    public CarValue<Integer> getYear() {
        CarValue<Integer> carValue = this.mYear;
        Objects.requireNonNull(carValue);
        return carValue;
    }

    public int hashCode() {
        return Objects.hash(this.mName, this.mYear, this.mManufacturer);
    }

    @NonNull
    public String toString() {
        return "[ name: " + this.mName + ", year: " + this.mYear + ", manufacturer: " + this.mManufacturer + "]";
    }
}
