package androidx.car.app.model;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.car.app.model.constraints.CarColorConstraints;
import androidx.car.app.model.constraints.CarIconConstraints;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;
/* loaded from: classes.dex */
public final class PlaceMarker {
    private static final int MAX_LABEL_LENGTH = 3;
    public static final int TYPE_ICON = 0;
    public static final int TYPE_IMAGE = 1;
    @Nullable
    @Keep
    private final CarColor mColor;
    @Nullable
    @Keep
    private final CarIcon mIcon;
    @Keep
    private final int mIconType;
    @Nullable
    @Keep
    private final CarText mLabel;

    /* loaded from: classes.dex */
    public static final class Builder {
        @Nullable
        CarColor mColor;
        @Nullable
        CarIcon mIcon;
        int mIconType = 0;
        @Nullable
        CarText mLabel;

        @NonNull
        public PlaceMarker build() {
            if (this.mColor == null || this.mIcon == null || this.mIconType != 1) {
                return new PlaceMarker(this);
            }
            throw new IllegalStateException("Color cannot be set for icon set with TYPE_IMAGE");
        }

        @NonNull
        public Builder setColor(@NonNull CarColor carColor) {
            CarColorConstraints carColorConstraints = CarColorConstraints.UNCONSTRAINED;
            Objects.requireNonNull(carColor);
            carColorConstraints.validateOrThrow(carColor);
            this.mColor = carColor;
            return this;
        }

        @NonNull
        public Builder setIcon(@NonNull CarIcon carIcon, int i2) {
            CarIconConstraints carIconConstraints = CarIconConstraints.DEFAULT;
            Objects.requireNonNull(carIcon);
            carIconConstraints.validateOrThrow(carIcon);
            this.mIcon = carIcon;
            this.mIconType = i2;
            return this;
        }

        @NonNull
        public Builder setLabel(@NonNull CharSequence charSequence) {
            Objects.requireNonNull(charSequence);
            if (charSequence.length() <= 3) {
                this.mLabel = CarText.create(charSequence);
                return this;
            }
            throw new IllegalArgumentException("Marker label cannot contain more than 3 characters");
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public @interface MarkerIconType {
    }

    private PlaceMarker() {
        this.mIcon = null;
        this.mIconType = 0;
        this.mLabel = null;
        this.mColor = null;
    }

    PlaceMarker(@NonNull Builder builder) {
        this.mIcon = builder.mIcon;
        this.mIconType = builder.mIconType;
        this.mLabel = builder.mLabel;
        this.mColor = builder.mColor;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PlaceMarker) {
            PlaceMarker placeMarker = (PlaceMarker) obj;
            return Objects.equals(this.mIcon, placeMarker.mIcon) && Objects.equals(this.mLabel, placeMarker.mLabel) && Objects.equals(this.mColor, placeMarker.mColor) && this.mIconType == placeMarker.mIconType;
        }
        return false;
    }

    @Nullable
    public CarColor getColor() {
        return this.mColor;
    }

    @Nullable
    public CarIcon getIcon() {
        return this.mIcon;
    }

    public int getIconType() {
        return this.mIconType;
    }

    @Nullable
    public CarText getLabel() {
        return this.mLabel;
    }

    public int hashCode() {
        return Objects.hash(this.mIcon, this.mLabel, this.mColor, Integer.valueOf(this.mIconType));
    }

    @NonNull
    public String toString() {
        String shortString;
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        CarIcon carIcon = this.mIcon;
        if (carIcon != null) {
            shortString = carIcon.toString();
        } else {
            CarText carText = this.mLabel;
            shortString = carText != null ? CarText.toShortString(carText) : super.toString();
        }
        sb.append(shortString);
        sb.append("]");
        return sb.toString();
    }
}
