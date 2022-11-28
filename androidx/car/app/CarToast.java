package androidx.car.app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.StringRes;
import androidx.annotation.VisibleForTesting;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;
/* loaded from: classes.dex */
public final class CarToast {
    public static final int LENGTH_LONG = 1;
    public static final int LENGTH_SHORT = 0;
    private final CarContext mCarContext;
    private int mDuration;
    @Nullable
    private CharSequence mText;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public @interface Duration {
    }

    @VisibleForTesting
    CarToast(@NonNull CarContext carContext) {
        Objects.requireNonNull(carContext);
        this.mCarContext = carContext;
    }

    @NonNull
    public static CarToast makeText(@NonNull CarContext carContext, @StringRes int i2, int i3) {
        Objects.requireNonNull(carContext);
        return makeText(carContext, i2 == 0 ? "" : carContext.getString(i2), i3);
    }

    @NonNull
    public static CarToast makeText(@NonNull CarContext carContext, @NonNull CharSequence charSequence, int i2) {
        Objects.requireNonNull(carContext);
        CarToast carToast = new CarToast(carContext);
        Objects.requireNonNull(charSequence);
        carToast.mText = charSequence;
        carToast.mDuration = i2;
        return carToast;
    }

    public void setDuration(int i2) {
        this.mDuration = i2;
    }

    public void setText(@StringRes int i2) {
        this.mText = i2 == 0 ? "" : this.mCarContext.getString(i2);
    }

    public void setText(@NonNull CharSequence charSequence) {
        Objects.requireNonNull(charSequence);
        this.mText = charSequence;
    }

    public void show() {
        CharSequence charSequence = this.mText;
        if (charSequence == null) {
            throw new IllegalStateException("setText must have been called");
        }
        ((AppManager) this.mCarContext.getCarService(AppManager.class)).showToast(charSequence, this.mDuration);
    }
}
