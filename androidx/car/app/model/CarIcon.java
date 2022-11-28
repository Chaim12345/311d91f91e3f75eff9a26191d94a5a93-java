package androidx.car.app.model;

import android.annotation.SuppressLint;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.camera.core.CameraInfo;
import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.model.constraints.CarColorConstraints;
import androidx.car.app.model.constraints.CarIconConstraints;
import androidx.core.graphics.drawable.IconCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;
/* loaded from: classes.dex */
public final class CarIcon {
    public static final int TYPE_ALERT = 4;
    public static final int TYPE_APP_ICON = 5;
    public static final int TYPE_BACK = 3;
    public static final int TYPE_CUSTOM = 1;
    public static final int TYPE_ERROR = 6;
    public static final int TYPE_PAN = 7;
    private static final int TYPE_RESOURCE = 2;
    private static final int TYPE_URI = 4;
    @Nullable
    @Keep
    private final IconCompat mIcon;
    @Nullable
    @Keep
    private final CarColor mTint;
    @Keep
    private final int mType;
    @NonNull
    public static final CarIcon APP_ICON = forStandardType(5);
    @NonNull
    public static final CarIcon BACK = forStandardType(3);
    @NonNull
    public static final CarIcon ALERT = forStandardType(4);
    @NonNull
    public static final CarIcon ERROR = forStandardType(6);
    @NonNull
    @RequiresCarApi(2)
    public static final CarIcon PAN = forStandardType(7);

    /* loaded from: classes.dex */
    public static final class Builder {
        @Nullable
        private IconCompat mIcon;
        @Nullable
        private CarColor mTint;
        private int mType;

        public Builder(@NonNull CarIcon carIcon) {
            Objects.requireNonNull(carIcon);
            this.mType = carIcon.getType();
            this.mIcon = carIcon.getIcon();
            this.mTint = carIcon.getTint();
        }

        public Builder(@NonNull IconCompat iconCompat) {
            CarIconConstraints carIconConstraints = CarIconConstraints.UNCONSTRAINED;
            Objects.requireNonNull(iconCompat);
            carIconConstraints.checkSupportedIcon(iconCompat);
            this.mType = 1;
            this.mIcon = iconCompat;
            this.mTint = null;
        }

        @NonNull
        public CarIcon build() {
            return new CarIcon(this.mIcon, this.mTint, this.mType);
        }

        @NonNull
        public Builder setTint(@NonNull CarColor carColor) {
            CarColorConstraints carColorConstraints = CarColorConstraints.UNCONSTRAINED;
            Objects.requireNonNull(carColor);
            carColorConstraints.validateOrThrow(carColor);
            this.mTint = carColor;
            return this;
        }
    }

    @SuppressLint({"UniqueConstants"})
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public @interface CarIconType {
    }

    private CarIcon() {
        this.mType = 1;
        this.mIcon = null;
        this.mTint = null;
    }

    CarIcon(@Nullable IconCompat iconCompat, @Nullable CarColor carColor, int i2) {
        this.mType = i2;
        this.mIcon = iconCompat;
        this.mTint = carColor;
    }

    private static CarIcon forStandardType(int i2) {
        return forStandardType(i2, CarColor.DEFAULT);
    }

    private static CarIcon forStandardType(int i2, @Nullable CarColor carColor) {
        return new CarIcon(null, carColor, i2);
    }

    private boolean iconCompatEquals(@Nullable IconCompat iconCompat) {
        int type;
        IconCompat iconCompat2 = this.mIcon;
        if (iconCompat2 == null) {
            return iconCompat == null;
        } else if (iconCompat != null && (type = iconCompat2.getType()) == iconCompat.getType()) {
            if (type == 2) {
                return Objects.equals(this.mIcon.getResPackage(), iconCompat.getResPackage()) && this.mIcon.getResId() == iconCompat.getResId();
            } else if (type == 4) {
                return Objects.equals(this.mIcon.getUri(), iconCompat.getUri());
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    @Nullable
    private Object iconCompatHash() {
        IconCompat iconCompat = this.mIcon;
        if (iconCompat == null) {
            return null;
        }
        int type = iconCompat.getType();
        if (type != 2) {
            return type == 4 ? this.mIcon.getUri() : Integer.valueOf(type);
        }
        return this.mIcon.getResPackage() + this.mIcon.getResId();
    }

    private static String typeToString(int i2) {
        return i2 != 1 ? i2 != 3 ? i2 != 4 ? i2 != 5 ? i2 != 6 ? i2 != 7 ? CameraInfo.IMPLEMENTATION_TYPE_UNKNOWN : "PAN" : "ERROR" : "APP" : "ALERT" : "BACK" : "CUSTOM";
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CarIcon) {
            CarIcon carIcon = (CarIcon) obj;
            return this.mType == carIcon.mType && Objects.equals(this.mTint, carIcon.mTint) && iconCompatEquals(carIcon.mIcon);
        }
        return false;
    }

    @Nullable
    public IconCompat getIcon() {
        return this.mIcon;
    }

    @Nullable
    public CarColor getTint() {
        return this.mTint;
    }

    public int getType() {
        return this.mType;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mType), this.mTint, iconCompatHash());
    }

    public String toString() {
        return "[type: " + typeToString(this.mType) + ", tint: " + this.mTint + "]";
    }
}
