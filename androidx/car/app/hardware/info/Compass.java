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
public final class Compass {
    @NonNull
    @Keep
    private final CarValue<List<Float>> mOrientations;

    private Compass() {
        this.mOrientations = CarValue.UNIMPLEMENTED_FLOAT_LIST;
    }

    public Compass(@NonNull CarValue<List<Float>> carValue) {
        Objects.requireNonNull(carValue);
        this.mOrientations = carValue;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Compass) {
            return Objects.equals(this.mOrientations, ((Compass) obj).mOrientations);
        }
        return false;
    }

    @NonNull
    public CarValue<List<Float>> getOrientations() {
        return this.mOrientations;
    }

    public int hashCode() {
        return Objects.hash(this.mOrientations);
    }

    @NonNull
    public String toString() {
        return "[ orientations: " + this.mOrientations + " ]";
    }
}
