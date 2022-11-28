package androidx.car.app.hardware;

import android.content.Context;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.car.app.CarContext;
import androidx.car.app.HostDispatcher;
import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.hardware.info.CarInfo;
import androidx.car.app.hardware.info.CarSensors;
import androidx.car.app.managers.Manager;
@RequiresCarApi(3)
@MainThread
/* loaded from: classes.dex */
public interface CarHardwareManager extends Manager {
    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    static CarHardwareManager create(@NonNull CarContext carContext, @NonNull HostDispatcher hostDispatcher) {
        try {
            try {
                return (CarHardwareManager) Class.forName("androidx.car.app.hardware.AutomotiveCarHardwareManager").getConstructor(Context.class).newInstance(carContext);
            } catch (ClassNotFoundException unused) {
                throw new IllegalStateException("Vehicle Manager not configured. Did you forget to add a dependency on app-automotive or app-projected artifacts?");
            } catch (ReflectiveOperationException e2) {
                throw new IllegalStateException("Mismatch with app-projected artifact", e2);
            }
        } catch (ClassNotFoundException unused2) {
            return (CarHardwareManager) ProjectedCarHardwareManager.class.getConstructor(HostDispatcher.class).newInstance(hostDispatcher);
        } catch (ReflectiveOperationException e3) {
            throw new IllegalStateException("Mismatch with app-automotive artifact", e3);
        }
    }

    @NonNull
    default CarInfo getCarInfo() {
        throw new UnsupportedOperationException();
    }

    @NonNull
    default CarSensors getCarSensors() {
        throw new UnsupportedOperationException();
    }
}
