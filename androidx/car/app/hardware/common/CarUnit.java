package androidx.car.app.hardware.common;

import androidx.annotation.RestrictTo;
import androidx.car.app.annotations.RequiresCarApi;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@RequiresCarApi(3)
/* loaded from: classes.dex */
public final class CarUnit {
    public static final int KILOMETER = 3;
    public static final int KILOMETERS_PER_HOUR = 102;
    public static final int METER = 2;
    public static final int METERS_PER_SEC = 101;
    public static final int MILE = 4;
    public static final int MILES_PER_HOUR = 103;
    public static final int MILLIMETER = 1;

    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public @interface CarDistanceUnit {
    }

    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public @interface CarSpeedUnit {
    }

    private CarUnit() {
    }
}
