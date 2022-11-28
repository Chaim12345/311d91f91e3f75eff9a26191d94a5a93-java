package androidx.car.app.model;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.car.app.model.constraints.CarColorConstraints;
import java.util.Objects;
/* loaded from: classes.dex */
public final class ForegroundCarColorSpan extends CarSpan {
    @Keep
    private final CarColor mCarColor;

    private ForegroundCarColorSpan() {
        this.mCarColor = CarColor.DEFAULT;
    }

    private ForegroundCarColorSpan(CarColor carColor) {
        this.mCarColor = carColor;
    }

    @NonNull
    public static ForegroundCarColorSpan create(@NonNull CarColor carColor) {
        CarColorConstraints.UNCONSTRAINED.validateOrThrow(carColor);
        Objects.requireNonNull(carColor);
        return new ForegroundCarColorSpan(carColor);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ForegroundCarColorSpan) {
            return Objects.equals(this.mCarColor, ((ForegroundCarColorSpan) obj).mCarColor);
        }
        return false;
    }

    @NonNull
    public CarColor getColor() {
        return this.mCarColor;
    }

    public int hashCode() {
        return Objects.hashCode(this.mCarColor);
    }

    @NonNull
    public String toString() {
        return "[color: " + this.mCarColor + "]";
    }
}
