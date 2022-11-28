package androidx.car.app.hardware.info;

import android.location.Location;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.hardware.common.CarValue;
import java.util.Objects;
@RequiresCarApi(3)
/* loaded from: classes.dex */
public final class CarHardwareLocation {
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final CarValue<Location> UNIMPLEMENTED_LOCATION = new CarValue<>(null, 0, 2);
    @NonNull
    @Keep
    private final CarValue<Location> mLocation;

    private CarHardwareLocation() {
        this.mLocation = UNIMPLEMENTED_LOCATION;
    }

    public CarHardwareLocation(@NonNull CarValue<Location> carValue) {
        Objects.requireNonNull(carValue);
        this.mLocation = carValue;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CarHardwareLocation) {
            return Objects.equals(this.mLocation, ((CarHardwareLocation) obj).mLocation);
        }
        return false;
    }

    @NonNull
    public CarValue<Location> getLocation() {
        return this.mLocation;
    }

    public int hashCode() {
        return Objects.hash(this.mLocation);
    }

    @NonNull
    public String toString() {
        return "[ location: " + this.mLocation + " ]";
    }
}
