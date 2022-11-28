package androidx.car.app.hardware.info;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.car.app.hardware.common.CarHardwareHostDispatcher;
import androidx.car.app.hardware.common.CarResultStubMap;
import androidx.car.app.hardware.common.CarValue;
import androidx.car.app.hardware.common.OnCarDataAvailableListener;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public class ProjectedCarSensors implements CarSensors {
    private static final CarValue<List<Float>> UNIMPLEMENTED_FLOAT_LIST = new CarValue<>(null, 0, 2);
    private final CarResultStubMap<Accelerometer, Integer> mAccelerometerCarResultStubMap;
    final CarHardwareHostDispatcher mCarHardwareHostDispatcher;
    private final CarResultStubMap<CarHardwareLocation, Integer> mCarHardwareLocationCarResultStubMap;
    private final CarResultStubMap<Compass, Integer> mCompassCarResultStubMap;
    private final CarResultStubMap<Gyroscope, Integer> mGyroscopeCarResultStubMap;

    public ProjectedCarSensors(@NonNull CarHardwareHostDispatcher carHardwareHostDispatcher) {
        Objects.requireNonNull(carHardwareHostDispatcher);
        this.mCarHardwareHostDispatcher = carHardwareHostDispatcher;
        CarValue<List<Float>> carValue = UNIMPLEMENTED_FLOAT_LIST;
        this.mAccelerometerCarResultStubMap = new CarResultStubMap<>(20, new Accelerometer(carValue), carHardwareHostDispatcher);
        this.mGyroscopeCarResultStubMap = new CarResultStubMap<>(22, new Gyroscope(carValue), carHardwareHostDispatcher);
        this.mCompassCarResultStubMap = new CarResultStubMap<>(21, new Compass(carValue), carHardwareHostDispatcher);
        this.mCarHardwareLocationCarResultStubMap = new CarResultStubMap<>(23, new CarHardwareLocation(new CarValue(null, 0L, 2)), carHardwareHostDispatcher);
    }

    @Override // androidx.car.app.hardware.info.CarSensors
    public void addAccelerometerListener(int i2, @NonNull Executor executor, @NonNull OnCarDataAvailableListener<Accelerometer> onCarDataAvailableListener) {
        CarResultStubMap<Accelerometer, Integer> carResultStubMap = this.mAccelerometerCarResultStubMap;
        Integer valueOf = Integer.valueOf(i2);
        Objects.requireNonNull(executor);
        Objects.requireNonNull(onCarDataAvailableListener);
        carResultStubMap.addListener(valueOf, executor, onCarDataAvailableListener);
    }

    @Override // androidx.car.app.hardware.info.CarSensors
    public void addCarHardwareLocationListener(int i2, @NonNull Executor executor, @NonNull OnCarDataAvailableListener<CarHardwareLocation> onCarDataAvailableListener) {
        CarResultStubMap<CarHardwareLocation, Integer> carResultStubMap = this.mCarHardwareLocationCarResultStubMap;
        Integer valueOf = Integer.valueOf(i2);
        Objects.requireNonNull(executor);
        Objects.requireNonNull(onCarDataAvailableListener);
        carResultStubMap.addListener(valueOf, executor, onCarDataAvailableListener);
    }

    @Override // androidx.car.app.hardware.info.CarSensors
    public void addCompassListener(int i2, @NonNull Executor executor, @NonNull OnCarDataAvailableListener<Compass> onCarDataAvailableListener) {
        CarResultStubMap<Compass, Integer> carResultStubMap = this.mCompassCarResultStubMap;
        Integer valueOf = Integer.valueOf(i2);
        Objects.requireNonNull(executor);
        Objects.requireNonNull(onCarDataAvailableListener);
        carResultStubMap.addListener(valueOf, executor, onCarDataAvailableListener);
    }

    @Override // androidx.car.app.hardware.info.CarSensors
    public void addGyroscopeListener(int i2, @NonNull Executor executor, @NonNull OnCarDataAvailableListener<Gyroscope> onCarDataAvailableListener) {
        CarResultStubMap<Gyroscope, Integer> carResultStubMap = this.mGyroscopeCarResultStubMap;
        Integer valueOf = Integer.valueOf(i2);
        Objects.requireNonNull(executor);
        Objects.requireNonNull(onCarDataAvailableListener);
        carResultStubMap.addListener(valueOf, executor, onCarDataAvailableListener);
    }

    @Override // androidx.car.app.hardware.info.CarSensors
    public void removeAccelerometerListener(@NonNull OnCarDataAvailableListener<Accelerometer> onCarDataAvailableListener) {
        CarResultStubMap<Accelerometer, Integer> carResultStubMap = this.mAccelerometerCarResultStubMap;
        Objects.requireNonNull(onCarDataAvailableListener);
        carResultStubMap.removeListener(onCarDataAvailableListener);
    }

    @Override // androidx.car.app.hardware.info.CarSensors
    public void removeCarHardwareLocationListener(@NonNull OnCarDataAvailableListener<CarHardwareLocation> onCarDataAvailableListener) {
        CarResultStubMap<CarHardwareLocation, Integer> carResultStubMap = this.mCarHardwareLocationCarResultStubMap;
        Objects.requireNonNull(onCarDataAvailableListener);
        carResultStubMap.removeListener(onCarDataAvailableListener);
    }

    @Override // androidx.car.app.hardware.info.CarSensors
    public void removeCompassListener(@NonNull OnCarDataAvailableListener<Compass> onCarDataAvailableListener) {
        CarResultStubMap<Compass, Integer> carResultStubMap = this.mCompassCarResultStubMap;
        Objects.requireNonNull(onCarDataAvailableListener);
        carResultStubMap.removeListener(onCarDataAvailableListener);
    }

    @Override // androidx.car.app.hardware.info.CarSensors
    public void removeGyroscopeListener(@NonNull OnCarDataAvailableListener<Gyroscope> onCarDataAvailableListener) {
        CarResultStubMap<Gyroscope, Integer> carResultStubMap = this.mGyroscopeCarResultStubMap;
        Objects.requireNonNull(onCarDataAvailableListener);
        carResultStubMap.removeListener(onCarDataAvailableListener);
    }
}
