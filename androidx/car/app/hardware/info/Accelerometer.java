package androidx.car.app.hardware.info;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.hardware.common.CarValue;
import java.util.List;
import java.util.Objects;
@RequiresCarApi(3)
/* loaded from: classes.dex */
public final class Accelerometer {
    @NonNull
    @Keep
    private final CarValue<List<Float>> mForces;

    private Accelerometer() {
        this.mForces = CarValue.UNIMPLEMENTED_FLOAT_LIST;
    }

    public Accelerometer(@NonNull CarValue<List<Float>> carValue) {
        Objects.requireNonNull(carValue);
        this.mForces = carValue;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Accelerometer) {
            return Objects.equals(this.mForces, ((Accelerometer) obj).mForces);
        }
        return false;
    }

    @NonNull
    public CarValue<List<Float>> getForces() {
        return this.mForces;
    }

    public int hashCode() {
        return Objects.hash(this.mForces);
    }

    @NonNull
    public String toString() {
        return "[ forces: " + this.mForces + " ]";
    }
}
