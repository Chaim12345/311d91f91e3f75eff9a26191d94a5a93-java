package androidx.car.app.hardware.info;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.hardware.common.CarValue;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Objects;
@RequiresCarApi(3)
/* loaded from: classes.dex */
public final class TollCard {
    public static final int TOLLCARD_STATE_INVALID = 2;
    public static final int TOLLCARD_STATE_NOT_INSERTED = 3;
    public static final int TOLLCARD_STATE_UNKNOWN = 0;
    public static final int TOLLCARD_STATE_VALID = 1;
    @NonNull
    @Keep
    private final CarValue<Integer> mCardState;

    /* loaded from: classes.dex */
    public static final class Builder {
        CarValue<Integer> mCardState = CarValue.UNIMPLEMENTED_INTEGER;

        @NonNull
        public TollCard build() {
            return new TollCard(this);
        }

        @NonNull
        public Builder setCardState(@NonNull CarValue<Integer> carValue) {
            Objects.requireNonNull(carValue);
            this.mCardState = carValue;
            return this;
        }
    }

    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public @interface TollCardState {
    }

    private TollCard() {
        this.mCardState = CarValue.UNIMPLEMENTED_INTEGER;
    }

    TollCard(Builder builder) {
        CarValue<Integer> carValue = builder.mCardState;
        Objects.requireNonNull(carValue);
        this.mCardState = carValue;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof TollCard) {
            return Objects.equals(this.mCardState, ((TollCard) obj).mCardState);
        }
        return false;
    }

    @NonNull
    public CarValue<Integer> getCardState() {
        CarValue<Integer> carValue = this.mCardState;
        Objects.requireNonNull(carValue);
        return carValue;
    }

    public int hashCode() {
        return Objects.hash(this.mCardState);
    }

    @NonNull
    public String toString() {
        return "[ tollcard state: " + this.mCardState + "]";
    }
}
