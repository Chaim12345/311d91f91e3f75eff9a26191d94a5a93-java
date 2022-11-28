package androidx.car.app.hardware.common;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.car.app.serialization.Bundleable;
import androidx.car.app.serialization.BundlerException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class CarResultStubMap<T, U> {
    private final CarHardwareHostDispatcher mHostDispatcher;
    private final int mResultType;
    private final HashMap<U, CarResultStub<T>> mStubMap = new HashMap<>();
    private final T mUnsupportedValue;

    public CarResultStubMap(int i2, @NonNull T t2, @NonNull CarHardwareHostDispatcher carHardwareHostDispatcher) {
        this.mResultType = i2;
        Objects.requireNonNull(t2);
        this.mUnsupportedValue = t2;
        Objects.requireNonNull(carHardwareHostDispatcher);
        this.mHostDispatcher = carHardwareHostDispatcher;
    }

    public void addListener(@Nullable U u, @NonNull Executor executor, @NonNull OnCarDataAvailableListener<T> onCarDataAvailableListener) {
        Bundleable create;
        Objects.requireNonNull(executor);
        Objects.requireNonNull(onCarDataAvailableListener);
        CarResultStub<T> carResultStub = this.mStubMap.get(u);
        if (carResultStub != null) {
            carResultStub.addListener(executor, onCarDataAvailableListener);
            return;
        }
        if (u == null) {
            create = null;
        } else {
            try {
                create = Bundleable.create(u);
            } catch (BundlerException unused) {
                throw new IllegalArgumentException("Invalid params");
            }
        }
        CarResultStub<T> carResultStub2 = new CarResultStub<>(this.mResultType, create, false, this.mUnsupportedValue, this.mHostDispatcher);
        carResultStub2.addListener(executor, onCarDataAvailableListener);
        this.mStubMap.put(u, carResultStub2);
    }

    public void removeListener(@NonNull OnCarDataAvailableListener<T> onCarDataAvailableListener) {
        Objects.requireNonNull(onCarDataAvailableListener);
        Iterator<Map.Entry<U, CarResultStub<T>>> it = this.mStubMap.entrySet().iterator();
        while (it.hasNext()) {
            if (it.next().getValue().removeListener(onCarDataAvailableListener)) {
                it.remove();
            }
        }
    }
}
