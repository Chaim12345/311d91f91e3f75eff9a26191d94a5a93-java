package androidx.car.app.hardware.info;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.car.app.hardware.common.CarHardwareHostDispatcher;
import androidx.car.app.hardware.common.CarResultStub;
import androidx.car.app.hardware.common.OnCarDataAvailableListener;
import androidx.car.app.hardware.info.EnergyLevel;
import androidx.car.app.hardware.info.EnergyProfile;
import androidx.car.app.hardware.info.Mileage;
import androidx.car.app.hardware.info.Model;
import androidx.car.app.hardware.info.Speed;
import androidx.car.app.hardware.info.TollCard;
import java.util.concurrent.Executor;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public class ProjectedCarInfo implements CarInfo {
    private final CarResultStub<EnergyLevel> mEnergyLevelCarResultStub;
    private final CarResultStub<EnergyProfile> mEnergyProfileCarResultStub;
    private final CarResultStub<Mileage> mMileageCarResultStub;
    private final CarResultStub<Model> mModelCarResultStub;
    private final CarResultStub<Speed> mSpeedCarResultStub;
    private final CarResultStub<TollCard> mTollCarResultStub;

    public ProjectedCarInfo(@NonNull CarHardwareHostDispatcher carHardwareHostDispatcher) {
        this.mModelCarResultStub = new CarResultStub<>(1, null, true, new Model.Builder().build(), carHardwareHostDispatcher);
        this.mEnergyProfileCarResultStub = new CarResultStub<>(2, null, true, new EnergyProfile.Builder().build(), carHardwareHostDispatcher);
        this.mTollCarResultStub = new CarResultStub<>(3, null, false, new TollCard.Builder().build(), carHardwareHostDispatcher);
        this.mEnergyLevelCarResultStub = new CarResultStub<>(4, null, false, new EnergyLevel.Builder().build(), carHardwareHostDispatcher);
        this.mSpeedCarResultStub = new CarResultStub<>(5, null, false, new Speed.Builder().build(), carHardwareHostDispatcher);
        this.mMileageCarResultStub = new CarResultStub<>(6, null, false, new Mileage.Builder().build(), carHardwareHostDispatcher);
    }

    @Override // androidx.car.app.hardware.info.CarInfo
    public void addEnergyLevelListener(@NonNull Executor executor, @NonNull OnCarDataAvailableListener<EnergyLevel> onCarDataAvailableListener) {
        this.mEnergyLevelCarResultStub.addListener(executor, onCarDataAvailableListener);
    }

    @Override // androidx.car.app.hardware.info.CarInfo
    public void addMileageListener(@NonNull Executor executor, @NonNull OnCarDataAvailableListener<Mileage> onCarDataAvailableListener) {
        this.mMileageCarResultStub.addListener(executor, onCarDataAvailableListener);
    }

    @Override // androidx.car.app.hardware.info.CarInfo
    public void addSpeedListener(@NonNull Executor executor, @NonNull OnCarDataAvailableListener<Speed> onCarDataAvailableListener) {
        this.mSpeedCarResultStub.addListener(executor, onCarDataAvailableListener);
    }

    @Override // androidx.car.app.hardware.info.CarInfo
    public void addTollListener(@NonNull Executor executor, @NonNull OnCarDataAvailableListener<TollCard> onCarDataAvailableListener) {
        this.mTollCarResultStub.addListener(executor, onCarDataAvailableListener);
    }

    @Override // androidx.car.app.hardware.info.CarInfo
    public void fetchEnergyProfile(@NonNull Executor executor, @NonNull OnCarDataAvailableListener<EnergyProfile> onCarDataAvailableListener) {
        this.mEnergyProfileCarResultStub.addListener(executor, onCarDataAvailableListener);
    }

    @Override // androidx.car.app.hardware.info.CarInfo
    public void fetchModel(@NonNull Executor executor, @NonNull OnCarDataAvailableListener<Model> onCarDataAvailableListener) {
        this.mModelCarResultStub.addListener(executor, onCarDataAvailableListener);
    }

    @Override // androidx.car.app.hardware.info.CarInfo
    public void removeEnergyLevelListener(@NonNull OnCarDataAvailableListener<EnergyLevel> onCarDataAvailableListener) {
        this.mEnergyLevelCarResultStub.removeListener(onCarDataAvailableListener);
    }

    @Override // androidx.car.app.hardware.info.CarInfo
    public void removeMileageListener(@NonNull OnCarDataAvailableListener<Mileage> onCarDataAvailableListener) {
        this.mMileageCarResultStub.removeListener(onCarDataAvailableListener);
    }

    @Override // androidx.car.app.hardware.info.CarInfo
    public void removeSpeedListener(@NonNull OnCarDataAvailableListener<Speed> onCarDataAvailableListener) {
        this.mSpeedCarResultStub.removeListener(onCarDataAvailableListener);
    }

    @Override // androidx.car.app.hardware.info.CarInfo
    public void removeTollListener(@NonNull OnCarDataAvailableListener<TollCard> onCarDataAvailableListener) {
        this.mTollCarResultStub.removeListener(onCarDataAvailableListener);
    }
}
