package androidx.car.app.model;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Locale;
import java.util.Objects;
/* loaded from: classes.dex */
public final class Distance {
    public static final int UNIT_FEET = 6;
    public static final int UNIT_KILOMETERS = 2;
    public static final int UNIT_KILOMETERS_P1 = 3;
    public static final int UNIT_METERS = 1;
    public static final int UNIT_MILES = 4;
    public static final int UNIT_MILES_P1 = 5;
    public static final int UNIT_YARDS = 7;
    @Keep
    private final double mDisplayDistance;
    @Keep
    private final int mDisplayUnit;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public @interface Unit {
    }

    private Distance() {
        this.mDisplayDistance = 0.0d;
        this.mDisplayUnit = 1;
    }

    private Distance(double d2, int i2) {
        this.mDisplayDistance = d2;
        this.mDisplayUnit = i2;
    }

    @NonNull
    public static Distance create(double d2, int i2) {
        if (d2 >= 0.0d) {
            return new Distance(d2, i2);
        }
        throw new IllegalArgumentException("displayDistance must be a positive value");
    }

    private static String unitToString(int i2) {
        switch (i2) {
            case 1:
                return "m";
            case 2:
                return "km";
            case 3:
                return "km_p1";
            case 4:
                return "mi";
            case 5:
                return "mi_p1";
            case 6:
                return "ft";
            case 7:
                return "yd";
            default:
                return "?";
        }
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Distance) {
            Distance distance = (Distance) obj;
            return this.mDisplayUnit == distance.mDisplayUnit && this.mDisplayDistance == distance.mDisplayDistance;
        }
        return false;
    }

    public double getDisplayDistance() {
        return this.mDisplayDistance;
    }

    public int getDisplayUnit() {
        return this.mDisplayUnit;
    }

    public int hashCode() {
        return Objects.hash(Double.valueOf(this.mDisplayDistance), Integer.valueOf(this.mDisplayUnit));
    }

    @NonNull
    public String toString() {
        return String.format(Locale.US, "%.04f%s", Double.valueOf(this.mDisplayDistance), unitToString(this.mDisplayUnit));
    }
}
