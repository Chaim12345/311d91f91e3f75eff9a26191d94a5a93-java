package androidx.car.app.model;

import androidx.annotation.ColorInt;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.camera.core.CameraInfo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;
/* loaded from: classes.dex */
public final class CarColor {
    public static final int TYPE_BLUE = 6;
    public static final int TYPE_CUSTOM = 0;
    public static final int TYPE_DEFAULT = 1;
    public static final int TYPE_GREEN = 5;
    public static final int TYPE_PRIMARY = 2;
    public static final int TYPE_RED = 4;
    public static final int TYPE_SECONDARY = 3;
    public static final int TYPE_YELLOW = 7;
    @ColorInt
    @Keep
    private final int mColor;
    @ColorInt
    @Keep
    private final int mColorDark;
    @Keep
    private final int mType;
    @NonNull
    public static final CarColor DEFAULT = create(1);
    @NonNull
    public static final CarColor PRIMARY = create(2);
    @NonNull
    public static final CarColor SECONDARY = create(3);
    @NonNull
    public static final CarColor RED = create(4);
    @NonNull
    public static final CarColor GREEN = create(5);
    @NonNull
    public static final CarColor BLUE = create(6);
    @NonNull
    public static final CarColor YELLOW = create(7);

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public @interface CarColorType {
    }

    private CarColor() {
        this.mType = 1;
        this.mColor = 0;
        this.mColorDark = 0;
    }

    private CarColor(int i2, @ColorInt int i3, @ColorInt int i4) {
        this.mType = i2;
        this.mColor = i3;
        this.mColorDark = i4;
    }

    private static CarColor create(int i2) {
        return new CarColor(i2, 0, 0);
    }

    @NonNull
    public static CarColor createCustom(@ColorInt int i2, @ColorInt int i3) {
        return new CarColor(0, i2, i3);
    }

    private static String typeToString(int i2) {
        switch (i2) {
            case 0:
                return "CUSTOM";
            case 1:
                return "DEFAULT";
            case 2:
                return "PRIMARY";
            case 3:
                return "SECONDARY";
            case 4:
                return "RED";
            case 5:
                return "GREEN";
            case 6:
                return "BLUE";
            case 7:
                return "YELLOW";
            default:
                return CameraInfo.IMPLEMENTATION_TYPE_UNKNOWN;
        }
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CarColor) {
            CarColor carColor = (CarColor) obj;
            return this.mColor == carColor.mColor && this.mColorDark == carColor.mColorDark && this.mType == carColor.mType;
        }
        return false;
    }

    @ColorInt
    public int getColor() {
        return this.mColor;
    }

    @ColorInt
    public int getColorDark() {
        return this.mColorDark;
    }

    public int getType() {
        return this.mType;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mType), Integer.valueOf(this.mColor), Integer.valueOf(this.mColorDark));
    }

    public String toString() {
        return "[type: " + typeToString(this.mType) + ", color: " + this.mColor + ", dark: " + this.mColorDark + "]";
    }
}
