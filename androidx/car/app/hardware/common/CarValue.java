package androidx.car.app.hardware.common;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.car.app.annotations.RequiresCarApi;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.Objects;
@RequiresCarApi(3)
/* loaded from: classes.dex */
public final class CarValue<T> {
    public static final int STATUS_SUCCESS = 1;
    public static final int STATUS_UNAVAILABLE = 3;
    public static final int STATUS_UNIMPLEMENTED = 2;
    public static final int STATUS_UNKNOWN = 0;
    @Keep
    private final int mStatus;
    @Keep
    private final long mTimestampMillis;
    @Nullable
    @Keep
    private final T mValue;
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final CarValue<Integer> UNIMPLEMENTED_INTEGER = unimplemented();
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final CarValue<Boolean> UNIMPLEMENTED_BOOLEAN = unimplemented();
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final CarValue<Float> UNIMPLEMENTED_FLOAT = unimplemented();
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final CarValue<String> UNIMPLEMENTED_STRING = unimplemented();
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final CarValue<List<Float>> UNIMPLEMENTED_FLOAT_LIST = unimplemented();
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final CarValue<List<Integer>> UNIMPLEMENTED_INTEGER_LIST = unimplemented();

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public @interface StatusCode {
    }

    private CarValue() {
        this.mValue = null;
        this.mTimestampMillis = 0L;
        this.mStatus = 0;
    }

    public CarValue(@Nullable T t2, long j2, int i2) {
        this.mValue = t2;
        this.mTimestampMillis = j2;
        this.mStatus = i2;
    }

    private static <T> CarValue<T> unimplemented() {
        return new CarValue<>(null, 0L, 2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CarValue) {
            CarValue carValue = (CarValue) obj;
            return Objects.equals(this.mValue, carValue.mValue) && this.mTimestampMillis == carValue.mTimestampMillis && this.mStatus == carValue.mStatus;
        }
        return false;
    }

    public int getStatus() {
        return this.mStatus;
    }

    public long getTimestampMillis() {
        return this.mTimestampMillis;
    }

    @Nullable
    public T getValue() {
        return this.mValue;
    }

    public int hashCode() {
        return Objects.hash(this.mValue, Long.valueOf(this.mTimestampMillis), Integer.valueOf(this.mStatus));
    }

    @NonNull
    public String toString() {
        return "[value: " + this.mValue + ", timestamp: " + this.mTimestampMillis + ", Status: " + this.mStatus + "]";
    }
}
