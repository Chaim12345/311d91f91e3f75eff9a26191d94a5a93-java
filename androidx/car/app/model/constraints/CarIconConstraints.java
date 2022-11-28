package androidx.car.app.model.constraints;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.car.app.model.CarIcon;
import androidx.core.graphics.drawable.IconCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public final class CarIconConstraints {
    private final int[] mAllowedTypes;
    @NonNull
    public static final CarIconConstraints UNCONSTRAINED = create(new int[]{1, 2, 4});
    @NonNull
    public static final CarIconConstraints DEFAULT = create(new int[]{1, 2});

    private CarIconConstraints(int[] iArr) {
        this.mAllowedTypes = iArr;
    }

    private static CarIconConstraints create(int[] iArr) {
        return new CarIconConstraints(iArr);
    }

    @NonNull
    public IconCompat checkSupportedIcon(@NonNull IconCompat iconCompat) {
        int type = iconCompat.getType();
        for (int i2 : this.mAllowedTypes) {
            if (type == i2) {
                if (type != 4 || FirebaseAnalytics.Param.CONTENT.equalsIgnoreCase(iconCompat.getUri().getScheme())) {
                    return iconCompat;
                }
                throw new IllegalArgumentException("Unsupported URI scheme for: " + iconCompat);
            }
        }
        throw new IllegalArgumentException("Custom icon type is not allowed: " + type);
    }

    public void validateOrThrow(@Nullable CarIcon carIcon) {
        if (carIcon == null || carIcon.getType() != 1) {
            return;
        }
        IconCompat icon = carIcon.getIcon();
        if (icon == null) {
            throw new IllegalStateException("Custom icon does not have a backing IconCompat");
        }
        checkSupportedIcon(icon);
    }
}
