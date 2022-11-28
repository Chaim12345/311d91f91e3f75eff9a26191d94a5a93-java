package androidx.car.app.hardware.info;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.hardware.common.OnCarDataAvailableListener;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.Executor;
@RequiresCarApi(3)
@MainThread
/* loaded from: classes.dex */
public interface CarSensors {
    public static final int UPDATE_RATE_FASTEST = 3;
    public static final int UPDATE_RATE_NORMAL = 1;
    public static final int UPDATE_RATE_UI = 2;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public @interface UpdateRate {
    }

    void addAccelerometerListener(int i2, @NonNull Executor executor, @NonNull OnCarDataAvailableListener<Accelerometer> onCarDataAvailableListener);

    void addCarHardwareLocationListener(int i2, @NonNull Executor executor, @NonNull OnCarDataAvailableListener<CarHardwareLocation> onCarDataAvailableListener);

    void addCompassListener(int i2, @NonNull Executor executor, @NonNull OnCarDataAvailableListener<Compass> onCarDataAvailableListener);

    void addGyroscopeListener(int i2, @NonNull Executor executor, @NonNull OnCarDataAvailableListener<Gyroscope> onCarDataAvailableListener);

    void removeAccelerometerListener(@NonNull OnCarDataAvailableListener<Accelerometer> onCarDataAvailableListener);

    void removeCarHardwareLocationListener(@NonNull OnCarDataAvailableListener<CarHardwareLocation> onCarDataAvailableListener);

    void removeCompassListener(@NonNull OnCarDataAvailableListener<Compass> onCarDataAvailableListener);

    void removeGyroscopeListener(@NonNull OnCarDataAvailableListener<Gyroscope> onCarDataAvailableListener);
}
