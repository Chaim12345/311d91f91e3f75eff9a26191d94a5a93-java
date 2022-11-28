package androidx.car.app.hardware.common;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.car.app.CarContext;
import androidx.car.app.HostDispatcher;
import androidx.car.app.ICarHost;
import androidx.car.app.hardware.ICarHardwareHost;
import androidx.car.app.hardware.ICarHardwareResult;
import androidx.car.app.hardware.common.CarHardwareHostDispatcher;
import androidx.car.app.serialization.Bundleable;
import androidx.car.app.utils.RemoteUtils;
import c.a;
import java.util.Objects;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class CarHardwareHostDispatcher {
    @NonNull
    private final HostDispatcher mHostDispatcher;
    @Nullable
    private ICarHardwareHost mICarHardwareHost;

    public CarHardwareHostDispatcher(@NonNull HostDispatcher hostDispatcher) {
        Objects.requireNonNull(hostDispatcher);
        this.mHostDispatcher = hostDispatcher;
    }

    @NonNull
    private ICarHardwareHost getHost() {
        ICarHardwareHost iCarHardwareHost = this.mICarHardwareHost;
        if (iCarHardwareHost == null) {
            ICarHardwareHost iCarHardwareHost2 = (ICarHardwareHost) this.mHostDispatcher.dispatchForResult(CarContext.CAR_SERVICE, "getHost(CarHardware)", a.f4318a);
            Objects.requireNonNull(iCarHardwareHost2);
            ICarHardwareHost iCarHardwareHost3 = iCarHardwareHost2;
            this.mICarHardwareHost = iCarHardwareHost3;
            return iCarHardwareHost3;
        }
        return iCarHardwareHost;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object lambda$dispatchGetCarHardwareResult$0(int i2, Bundleable bundleable, ICarHardwareResult iCarHardwareResult) {
        getHost().getCarHardwareResult(i2, bundleable, iCarHardwareResult);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object lambda$dispatchSubscribeCarHardwareResult$1(int i2, Bundleable bundleable, ICarHardwareResult iCarHardwareResult) {
        getHost().subscribeCarHardwareResult(i2, bundleable, iCarHardwareResult);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object lambda$dispatchUnsubscribeCarHardwareResult$2(int i2, Bundleable bundleable) {
        getHost().unsubscribeCarHardwareResult(i2, bundleable);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ ICarHardwareHost lambda$getHost$3(ICarHost iCarHost) {
        return ICarHardwareHost.Stub.asInterface(iCarHost.getHost(CarContext.HARDWARE_SERVICE));
    }

    public void dispatchGetCarHardwareResult(final int i2, @Nullable final Bundleable bundleable, @NonNull final ICarHardwareResult iCarHardwareResult) {
        Objects.requireNonNull(iCarHardwareResult);
        RemoteUtils.dispatchCallToHost("getCarHardwareResult", new RemoteUtils.RemoteCall() { // from class: c.c
            @Override // androidx.car.app.utils.RemoteUtils.RemoteCall
            public final Object call() {
                Object lambda$dispatchGetCarHardwareResult$0;
                lambda$dispatchGetCarHardwareResult$0 = CarHardwareHostDispatcher.this.lambda$dispatchGetCarHardwareResult$0(i2, bundleable, iCarHardwareResult);
                return lambda$dispatchGetCarHardwareResult$0;
            }
        });
    }

    public void dispatchSubscribeCarHardwareResult(final int i2, @Nullable final Bundleable bundleable, @NonNull final ICarHardwareResult iCarHardwareResult) {
        Objects.requireNonNull(iCarHardwareResult);
        RemoteUtils.dispatchCallToHost("subscribeCarHardwareResult", new RemoteUtils.RemoteCall() { // from class: c.d
            @Override // androidx.car.app.utils.RemoteUtils.RemoteCall
            public final Object call() {
                Object lambda$dispatchSubscribeCarHardwareResult$1;
                lambda$dispatchSubscribeCarHardwareResult$1 = CarHardwareHostDispatcher.this.lambda$dispatchSubscribeCarHardwareResult$1(i2, bundleable, iCarHardwareResult);
                return lambda$dispatchSubscribeCarHardwareResult$1;
            }
        });
    }

    public void dispatchUnsubscribeCarHardwareResult(final int i2, @Nullable final Bundleable bundleable) {
        RemoteUtils.dispatchCallToHost("unsubscribeCarHardwareResult", new RemoteUtils.RemoteCall() { // from class: c.b
            @Override // androidx.car.app.utils.RemoteUtils.RemoteCall
            public final Object call() {
                Object lambda$dispatchUnsubscribeCarHardwareResult$2;
                lambda$dispatchUnsubscribeCarHardwareResult$2 = CarHardwareHostDispatcher.this.lambda$dispatchUnsubscribeCarHardwareResult$2(i2, bundleable);
                return lambda$dispatchUnsubscribeCarHardwareResult$2;
            }
        });
    }
}
