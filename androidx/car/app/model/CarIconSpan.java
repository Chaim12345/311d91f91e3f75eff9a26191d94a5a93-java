package androidx.car.app.model;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.car.app.model.constraints.CarIconConstraints;
import androidx.core.os.EnvironmentCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;
/* loaded from: classes.dex */
public final class CarIconSpan extends CarSpan {
    public static final int ALIGN_BASELINE = 1;
    public static final int ALIGN_BOTTOM = 0;
    public static final int ALIGN_CENTER = 2;
    @Keep
    private final int mAlignment;
    @Nullable
    @Keep
    private final CarIcon mIcon;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public @interface Alignment {
    }

    private CarIconSpan() {
        this.mIcon = null;
        this.mAlignment = 1;
    }

    private CarIconSpan(@Nullable CarIcon carIcon, int i2) {
        this.mIcon = carIcon;
        this.mAlignment = i2;
    }

    private static String alignmentToString(int i2) {
        return i2 != 0 ? i2 != 1 ? i2 != 2 ? EnvironmentCompat.MEDIA_UNKNOWN : "center" : "baseline" : "bottom";
    }

    @NonNull
    public static CarIconSpan create(@NonNull CarIcon carIcon) {
        return create(carIcon, 1);
    }

    @NonNull
    public static CarIconSpan create(@NonNull CarIcon carIcon, int i2) {
        CarIconConstraints.DEFAULT.validateOrThrow(carIcon);
        if (i2 == 1 || i2 == 0 || i2 == 2) {
            Objects.requireNonNull(carIcon);
            return new CarIconSpan(carIcon, i2);
        }
        throw new IllegalStateException("Invalid alignment value: " + i2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CarIconSpan) {
            return Objects.equals(this.mIcon, ((CarIconSpan) obj).mIcon);
        }
        return false;
    }

    public int getAlignment() {
        return this.mAlignment;
    }

    @NonNull
    public CarIcon getIcon() {
        CarIcon carIcon = this.mIcon;
        Objects.requireNonNull(carIcon);
        return carIcon;
    }

    public int hashCode() {
        return Objects.hashCode(this.mIcon);
    }

    @NonNull
    public String toString() {
        return "[icon: " + this.mIcon + ", alignment: " + alignmentToString(this.mAlignment) + "]";
    }
}
