package androidx.car.app.model;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Objects;
/* loaded from: classes.dex */
public final class Metadata {
    public static final Metadata EMPTY_METADATA = new Builder().build();
    @Nullable
    @Keep
    private final Place mPlace;

    /* loaded from: classes.dex */
    public static final class Builder {
        @Nullable
        Place mPlace;

        public Builder() {
        }

        public Builder(@NonNull Metadata metadata) {
            Objects.requireNonNull(metadata);
            this.mPlace = metadata.getPlace();
        }

        @NonNull
        public Metadata build() {
            return new Metadata(this);
        }

        @NonNull
        public Builder setPlace(@NonNull Place place) {
            Objects.requireNonNull(place);
            this.mPlace = place;
            return this;
        }
    }

    private Metadata() {
        this.mPlace = null;
    }

    Metadata(Builder builder) {
        this.mPlace = builder.mPlace;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Metadata) {
            return Objects.equals(this.mPlace, ((Metadata) obj).mPlace);
        }
        return false;
    }

    @Nullable
    public Place getPlace() {
        return this.mPlace;
    }

    public int hashCode() {
        return Objects.hashCode(this.mPlace);
    }
}
