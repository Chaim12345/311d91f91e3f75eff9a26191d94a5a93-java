package androidx.car.app.model;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Objects;
/* loaded from: classes.dex */
public final class Place {
    @Nullable
    @Keep
    private final CarLocation mLocation;
    @Nullable
    @Keep
    private final PlaceMarker mMarker;

    /* loaded from: classes.dex */
    public static final class Builder {
        CarLocation mLocation;
        @Nullable
        PlaceMarker mMarker;

        public Builder(@NonNull CarLocation carLocation) {
            Objects.requireNonNull(carLocation);
            this.mLocation = carLocation;
        }

        public Builder(@NonNull Place place) {
            Objects.requireNonNull(place);
            this.mLocation = place.getLocation();
            this.mMarker = place.getMarker();
        }

        @NonNull
        public Place build() {
            return new Place(this);
        }

        @NonNull
        public Builder setMarker(@NonNull PlaceMarker placeMarker) {
            Objects.requireNonNull(placeMarker);
            this.mMarker = placeMarker;
            return this;
        }
    }

    private Place() {
        this.mLocation = null;
        this.mMarker = null;
    }

    Place(Builder builder) {
        this.mLocation = builder.mLocation;
        this.mMarker = builder.mMarker;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Place) {
            Place place = (Place) obj;
            return Objects.equals(this.mLocation, place.mLocation) && Objects.equals(this.mMarker, place.mMarker);
        }
        return false;
    }

    @NonNull
    public CarLocation getLocation() {
        CarLocation carLocation = this.mLocation;
        Objects.requireNonNull(carLocation);
        return carLocation;
    }

    @Nullable
    public PlaceMarker getMarker() {
        return this.mMarker;
    }

    public int hashCode() {
        return Objects.hash(this.mLocation, this.mMarker);
    }

    @NonNull
    public String toString() {
        return "[ location: " + this.mLocation + ", marker: " + this.mMarker + "]";
    }
}
