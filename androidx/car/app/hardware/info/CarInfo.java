package androidx.car.app.hardware.info;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.hardware.common.OnCarDataAvailableListener;
import java.util.concurrent.Executor;
@RequiresCarApi(3)
@MainThread
/* loaded from: classes.dex */
public interface CarInfo {
    void addEnergyLevelListener(@NonNull Executor executor, @NonNull OnCarDataAvailableListener<EnergyLevel> onCarDataAvailableListener);

    void addMileageListener(@NonNull Executor executor, @NonNull OnCarDataAvailableListener<Mileage> onCarDataAvailableListener);

    void addSpeedListener(@NonNull Executor executor, @NonNull OnCarDataAvailableListener<Speed> onCarDataAvailableListener);

    void addTollListener(@NonNull Executor executor, @NonNull OnCarDataAvailableListener<TollCard> onCarDataAvailableListener);

    void fetchEnergyProfile(@NonNull Executor executor, @NonNull OnCarDataAvailableListener<EnergyProfile> onCarDataAvailableListener);

    void fetchModel(@NonNull Executor executor, @NonNull OnCarDataAvailableListener<Model> onCarDataAvailableListener);

    void removeEnergyLevelListener(@NonNull OnCarDataAvailableListener<EnergyLevel> onCarDataAvailableListener);

    void removeMileageListener(@NonNull OnCarDataAvailableListener<Mileage> onCarDataAvailableListener);

    void removeSpeedListener(@NonNull OnCarDataAvailableListener<Speed> onCarDataAvailableListener);

    void removeTollListener(@NonNull OnCarDataAvailableListener<TollCard> onCarDataAvailableListener);
}
