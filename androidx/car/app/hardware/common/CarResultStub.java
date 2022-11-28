package androidx.car.app.hardware.common;

import android.os.IBinder;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.car.app.IOnDoneCallback;
import androidx.car.app.hardware.ICarHardwareResult;
import androidx.car.app.hardware.common.CarResultStub;
import androidx.car.app.serialization.Bundleable;
import androidx.car.app.utils.RemoteUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class CarResultStub<T> extends ICarHardwareResult.Stub {
    @Nullable
    private final Bundleable mBundle;
    private final CarHardwareHostDispatcher mHostDispatcher;
    private final boolean mIsSingleShot;
    private final Map<OnCarDataAvailableListener<T>, Executor> mListeners = new HashMap();
    private final int mResultType;
    private final T mUnsupportedValue;

    public CarResultStub(int i2, @Nullable Bundleable bundleable, boolean z, @NonNull T t2, @NonNull CarHardwareHostDispatcher carHardwareHostDispatcher) {
        Objects.requireNonNull(carHardwareHostDispatcher);
        this.mHostDispatcher = carHardwareHostDispatcher;
        this.mResultType = i2;
        this.mBundle = bundleable;
        this.mIsSingleShot = z;
        Objects.requireNonNull(t2);
        this.mUnsupportedValue = t2;
    }

    private T convertAndRecast(@NonNull Bundleable bundleable) {
        return (T) bundleable.get();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$notifyResults$1(Map.Entry entry, Object obj) {
        ((OnCarDataAvailableListener) entry.getKey()).onCarDataAvailable(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object lambda$onCarHardwareResult$0(boolean z, Bundleable bundleable) {
        notifyResults(z, bundleable);
        return null;
    }

    private void notifyResults(boolean z, @NonNull Bundleable bundleable) {
        final T convertAndRecast = z ? convertAndRecast(bundleable) : this.mUnsupportedValue;
        for (final Map.Entry<OnCarDataAvailableListener<T>, Executor> entry : this.mListeners.entrySet()) {
            entry.getValue().execute(new Runnable() { // from class: c.f
                @Override // java.lang.Runnable
                public final void run() {
                    CarResultStub.lambda$notifyResults$1(entry, convertAndRecast);
                }
            });
        }
        if (this.mIsSingleShot) {
            this.mListeners.clear();
        }
    }

    public void addListener(@NonNull Executor executor, @NonNull OnCarDataAvailableListener<T> onCarDataAvailableListener) {
        boolean z = !this.mListeners.isEmpty();
        Map<OnCarDataAvailableListener<T>, Executor> map = this.mListeners;
        Objects.requireNonNull(onCarDataAvailableListener);
        map.put(onCarDataAvailableListener, executor);
        if (z) {
            return;
        }
        if (this.mIsSingleShot) {
            this.mHostDispatcher.dispatchGetCarHardwareResult(this.mResultType, this.mBundle, this);
        } else {
            this.mHostDispatcher.dispatchSubscribeCarHardwareResult(this.mResultType, this.mBundle, this);
        }
    }

    @Override // androidx.car.app.hardware.ICarHardwareResult
    public void onCarHardwareResult(int i2, final boolean z, @NonNull final Bundleable bundleable, @NonNull IBinder iBinder) {
        RemoteUtils.dispatchCallFromHost(IOnDoneCallback.Stub.asInterface(iBinder), "onCarHardwareResult", new RemoteUtils.HostCall() { // from class: c.e
            @Override // androidx.car.app.utils.RemoteUtils.HostCall
            public final Object dispatch() {
                Object lambda$onCarHardwareResult$0;
                lambda$onCarHardwareResult$0 = CarResultStub.this.lambda$onCarHardwareResult$0(z, bundleable);
                return lambda$onCarHardwareResult$0;
            }
        });
    }

    public boolean removeListener(@NonNull OnCarDataAvailableListener<T> onCarDataAvailableListener) {
        Map<OnCarDataAvailableListener<T>, Executor> map = this.mListeners;
        Objects.requireNonNull(onCarDataAvailableListener);
        map.remove(onCarDataAvailableListener);
        if (this.mListeners.isEmpty()) {
            if (this.mIsSingleShot) {
                return true;
            }
            this.mHostDispatcher.dispatchUnsubscribeCarHardwareResult(this.mResultType, this.mBundle);
            return true;
        }
        return false;
    }
}
