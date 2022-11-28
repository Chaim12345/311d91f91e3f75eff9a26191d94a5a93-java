package androidx.car.app.model;

import android.location.Location;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Objects;
/* loaded from: classes.dex */
public final class CarLocation {
    @Keep
    private final double mLat;
    @Keep
    private final double mLng;

    private CarLocation() {
        this(0.0d, 0.0d);
    }

    private CarLocation(double d2, double d3) {
        this.mLat = d2;
        this.mLng = d3;
    }

    @NonNull
    public static CarLocation create(double d2, double d3) {
        return new CarLocation(d2, d3);
    }

    @NonNull
    public static CarLocation create(@NonNull Location location) {
        Objects.requireNonNull(location);
        return create(location.getLatitude(), location.getLongitude());
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CarLocation) {
            CarLocation carLocation = (CarLocation) obj;
            return Double.doubleToLongBits(this.mLat) == Double.doubleToLongBits(carLocation.mLat) && Double.doubleToLongBits(this.mLng) == Double.doubleToLongBits(carLocation.mLng);
        }
        return false;
    }

    public double getLatitude() {
        return this.mLat;
    }

    public double getLongitude() {
        return this.mLng;
    }

    public int hashCode() {
        return Objects.hash(Double.valueOf(this.mLat), Double.valueOf(this.mLng));
    }

    public String toString() {
        return "[" + getLatitude() + ", " + getLongitude() + "]";
    }
}
