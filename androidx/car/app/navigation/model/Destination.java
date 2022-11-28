package androidx.car.app.navigation.model;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.car.app.model.CarIcon;
import androidx.car.app.model.CarText;
import androidx.car.app.model.constraints.CarIconConstraints;
import java.util.Objects;
/* loaded from: classes.dex */
public final class Destination {
    @Nullable
    @Keep
    private final CarText mAddress;
    @Nullable
    @Keep
    private final CarIcon mImage;
    @Nullable
    @Keep
    private final CarText mName;

    /* loaded from: classes.dex */
    public static final class Builder {
        @Nullable
        CarText mAddress;
        @Nullable
        CarIcon mImage;
        @Nullable
        CarText mName;

        @NonNull
        public Destination build() {
            CarText carText;
            CarText carText2 = this.mName;
            if ((carText2 == null || carText2.isEmpty()) && ((carText = this.mAddress) == null || carText.isEmpty())) {
                throw new IllegalStateException("Both name and address cannot be null or empty");
            }
            return new Destination(this);
        }

        @NonNull
        public Builder setAddress(@NonNull CharSequence charSequence) {
            Objects.requireNonNull(charSequence);
            this.mAddress = CarText.create(charSequence);
            return this;
        }

        @NonNull
        public Builder setImage(@NonNull CarIcon carIcon) {
            CarIconConstraints carIconConstraints = CarIconConstraints.DEFAULT;
            Objects.requireNonNull(carIcon);
            carIconConstraints.validateOrThrow(carIcon);
            this.mImage = carIcon;
            return this;
        }

        @NonNull
        public Builder setName(@NonNull CharSequence charSequence) {
            Objects.requireNonNull(charSequence);
            this.mName = CarText.create(charSequence);
            return this;
        }
    }

    private Destination() {
        this.mName = null;
        this.mAddress = null;
        this.mImage = null;
    }

    Destination(Builder builder) {
        this.mName = builder.mName;
        this.mAddress = builder.mAddress;
        this.mImage = builder.mImage;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Destination) {
            Destination destination = (Destination) obj;
            return Objects.equals(this.mName, destination.mName) && Objects.equals(this.mAddress, destination.mAddress) && Objects.equals(this.mImage, destination.mImage);
        }
        return false;
    }

    @Nullable
    public CarText getAddress() {
        return this.mAddress;
    }

    @Nullable
    public CarIcon getImage() {
        return this.mImage;
    }

    @Nullable
    public CarText getName() {
        return this.mName;
    }

    public int hashCode() {
        return Objects.hash(this.mName, this.mAddress, this.mImage);
    }

    @NonNull
    public String toString() {
        return "[name: " + CarText.toShortString(this.mName) + ", address: " + CarText.toShortString(this.mAddress) + ", image: " + this.mImage + "]";
    }
}
