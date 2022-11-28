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
public final class Gyroscope {
    @NonNull
    @Keep
    private final CarValue<List<Float>> mRotations;

    private Gyroscope() {
        this.mRotations = CarValue.UNIMPLEMENTED_FLOAT_LIST;
    }

    public Gyroscope(@NonNull CarValue<List<Float>> carValue) {
        Objects.requireNonNull(carValue);
        this.mRotations = carValue;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Gyroscope) {
            return Objects.equals(this.mRotations, ((Gyroscope) obj).mRotations);
        }
        return false;
    }

    @NonNull
    public CarValue<List<Float>> getRotations() {
        return this.mRotations;
    }

    public int hashCode() {
        return Objects.hash(this.mRotations);
    }

    @NonNull
    public String toString() {
        return "[ rotations: " + this.mRotations + " ]";
    }
}
