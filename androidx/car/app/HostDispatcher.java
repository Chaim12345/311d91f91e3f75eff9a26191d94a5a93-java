package androidx.car.app;

import android.os.IInterface;
import android.util.Log;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.car.app.IAppHost;
import androidx.car.app.constraints.IConstraintHost;
import androidx.car.app.navigation.INavigationHost;
import androidx.car.app.utils.LogTags;
import androidx.car.app.utils.RemoteUtils;
import androidx.car.app.utils.ThreadUtils;
import java.security.InvalidParameterException;
import java.util.Objects;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public final class HostDispatcher {
    @Nullable
    private IAppHost mAppHost;
    @Nullable
    private ICarHost mCarHost;
    @Nullable
    private IConstraintHost mConstraintHost;
    @Nullable
    private INavigationHost mNavigationHost;

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object lambda$dispatch$1(String str, String str2, HostCall hostCall) {
        IInterface host = getHost(str);
        if (host != null) {
            hostCall.dispatch(host);
            return null;
        }
        Log.e(LogTags.TAG_DISPATCH, "Could not retrieve host while dispatching call " + str2);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object lambda$dispatchForResult$0(String str, String str2, HostCall hostCall) {
        IInterface host = getHost(str);
        if (host == null) {
            Log.e(LogTags.TAG_DISPATCH, "Could not retrieve host while dispatching call " + str2);
            return null;
        }
        return hostCall.dispatch(host);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ IAppHost lambda$getHost$2() {
        ICarHost iCarHost = this.mCarHost;
        Objects.requireNonNull(iCarHost);
        return IAppHost.Stub.asInterface(iCarHost.getHost(CarContext.APP_SERVICE));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ IConstraintHost lambda$getHost$3() {
        ICarHost iCarHost = this.mCarHost;
        Objects.requireNonNull(iCarHost);
        return IConstraintHost.Stub.asInterface(iCarHost.getHost(CarContext.CONSTRAINT_SERVICE));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ INavigationHost lambda$getHost$4() {
        ICarHost iCarHost = this.mCarHost;
        Objects.requireNonNull(iCarHost);
        return INavigationHost.Stub.asInterface(iCarHost.getHost("navigation"));
    }

    public <ServiceT, ReturnT> void dispatch(@NonNull final String str, @NonNull final String str2, @NonNull final HostCall<ServiceT, ReturnT> hostCall) {
        RemoteUtils.dispatchCallToHost(str2, new RemoteUtils.RemoteCall() { // from class: androidx.car.app.f0
            @Override // androidx.car.app.utils.RemoteUtils.RemoteCall
            public final Object call() {
                Object lambda$dispatch$1;
                lambda$dispatch$1 = HostDispatcher.this.lambda$dispatch$1(str, str2, hostCall);
                return lambda$dispatch$1;
            }
        });
    }

    @Nullable
    public <ServiceT, ReturnT> ReturnT dispatchForResult(@NonNull final String str, @NonNull final String str2, @NonNull final HostCall<ServiceT, ReturnT> hostCall) {
        return (ReturnT) RemoteUtils.dispatchCallToHostForResult(str2, new RemoteUtils.RemoteCall() { // from class: androidx.car.app.g0
            @Override // androidx.car.app.utils.RemoteUtils.RemoteCall
            public final Object call() {
                Object lambda$dispatchForResult$0;
                lambda$dispatchForResult$0 = HostDispatcher.this.lambda$dispatchForResult$0(str, str2, hostCall);
                return lambda$dispatchForResult$0;
            }
        });
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    IInterface getHost(String str) {
        if (this.mCarHost == null) {
            Log.e(LogTags.TAG_DISPATCH, "Host is not bound when attempting to retrieve host service");
            return null;
        }
        str.hashCode();
        char c2 = 65535;
        switch (str.hashCode()) {
            case -1606703562:
                if (str.equals(CarContext.CONSTRAINT_SERVICE)) {
                    c2 = 0;
                    break;
                }
                break;
            case 96801:
                if (str.equals(CarContext.APP_SERVICE)) {
                    c2 = 1;
                    break;
                }
                break;
            case 98260:
                if (str.equals(CarContext.CAR_SERVICE)) {
                    c2 = 2;
                    break;
                }
                break;
            case 1862666772:
                if (str.equals("navigation")) {
                    c2 = 3;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                if (this.mConstraintHost == null) {
                    this.mConstraintHost = (IConstraintHost) RemoteUtils.dispatchCallToHostForResult("getHost(Constraints)", new RemoteUtils.RemoteCall() { // from class: androidx.car.app.d0
                        @Override // androidx.car.app.utils.RemoteUtils.RemoteCall
                        public final Object call() {
                            IConstraintHost lambda$getHost$3;
                            lambda$getHost$3 = HostDispatcher.this.lambda$getHost$3();
                            return lambda$getHost$3;
                        }
                    });
                }
                return this.mConstraintHost;
            case 1:
                if (this.mAppHost == null) {
                    this.mAppHost = (IAppHost) RemoteUtils.dispatchCallToHostForResult("getHost(App)", new RemoteUtils.RemoteCall() { // from class: androidx.car.app.e0
                        @Override // androidx.car.app.utils.RemoteUtils.RemoteCall
                        public final Object call() {
                            IAppHost lambda$getHost$2;
                            lambda$getHost$2 = HostDispatcher.this.lambda$getHost$2();
                            return lambda$getHost$2;
                        }
                    });
                }
                return this.mAppHost;
            case 2:
                return this.mCarHost;
            case 3:
                if (this.mNavigationHost == null) {
                    this.mNavigationHost = (INavigationHost) RemoteUtils.dispatchCallToHostForResult("getHost(Navigation)", new RemoteUtils.RemoteCall() { // from class: androidx.car.app.c0
                        @Override // androidx.car.app.utils.RemoteUtils.RemoteCall
                        public final Object call() {
                            INavigationHost lambda$getHost$4;
                            lambda$getHost$4 = HostDispatcher.this.lambda$getHost$4();
                            return lambda$getHost$4;
                        }
                    });
                }
                return this.mNavigationHost;
            default:
                throw new InvalidParameterException("Invalid host type: " + str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @MainThread
    public void resetHosts() {
        ThreadUtils.checkMainThread();
        this.mCarHost = null;
        this.mAppHost = null;
        this.mNavigationHost = null;
    }

    @MainThread
    public void setCarHost(@NonNull ICarHost iCarHost) {
        ThreadUtils.checkMainThread();
        resetHosts();
        this.mCarHost = iCarHost;
    }
}
