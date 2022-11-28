package androidx.car.app.hardware;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.car.app.HostDispatcher;
import androidx.car.app.hardware.common.CarHardwareHostDispatcher;
import androidx.car.app.hardware.info.CarInfo;
import androidx.car.app.hardware.info.CarSensors;
import androidx.car.app.hardware.info.ProjectedCarInfo;
import androidx.car.app.hardware.info.ProjectedCarSensors;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public class ProjectedCarHardwareManager implements CarHardwareManager {
    private final ProjectedCarInfo mVehicleInfo;
    private final ProjectedCarSensors mVehicleSensors;

    public ProjectedCarHardwareManager(@NonNull HostDispatcher hostDispatcher) {
        CarHardwareHostDispatcher carHardwareHostDispatcher = new CarHardwareHostDispatcher(hostDispatcher);
        this.mVehicleInfo = new ProjectedCarInfo(carHardwareHostDispatcher);
        this.mVehicleSensors = new ProjectedCarSensors(carHardwareHostDispatcher);
    }

    @Override // androidx.car.app.hardware.CarHardwareManager
    @NonNull
    public CarInfo getCarInfo() {
        return this.mVehicleInfo;
    }

    @Override // androidx.car.app.hardware.CarHardwareManager
    @NonNull
    public CarSensors getCarSensors() {
        return this.mVehicleSensors;
    }
}
