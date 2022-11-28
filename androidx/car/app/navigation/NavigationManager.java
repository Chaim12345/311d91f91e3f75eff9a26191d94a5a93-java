package androidx.car.app.navigation;

import android.annotation.SuppressLint;
import android.util.Log;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.car.app.CarContext;
import androidx.car.app.HostCall;
import androidx.car.app.HostDispatcher;
import androidx.car.app.IOnDoneCallback;
import androidx.car.app.managers.Manager;
import androidx.car.app.navigation.INavigationManager;
import androidx.car.app.navigation.NavigationManager;
import androidx.car.app.navigation.model.Trip;
import androidx.car.app.serialization.Bundleable;
import androidx.car.app.serialization.BundlerException;
import androidx.car.app.utils.LogTags;
import androidx.car.app.utils.RemoteUtils;
import androidx.car.app.utils.ThreadUtils;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import java.util.Objects;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public class NavigationManager implements Manager {
    private final CarContext mCarContext;
    private final HostDispatcher mHostDispatcher;
    private boolean mIsAutoDriveEnabled;
    private boolean mIsNavigating;
    private final INavigationManager.Stub mNavigationManager;
    @Nullable
    private NavigationManagerCallback mNavigationManagerCallback;
    @Nullable
    private Executor mNavigationManagerCallbackExecutor;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: androidx.car.app.navigation.NavigationManager$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass1 extends INavigationManager.Stub {
        final /* synthetic */ Lifecycle val$lifecycle;

        AnonymousClass1(Lifecycle lifecycle) {
            this.val$lifecycle = lifecycle;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ Object lambda$onStopNavigation$0() {
            NavigationManager.this.onStopNavigation();
            return null;
        }

        @Override // androidx.car.app.navigation.INavigationManager
        public void onStopNavigation(IOnDoneCallback iOnDoneCallback) {
            RemoteUtils.dispatchCallFromHost(this.val$lifecycle, iOnDoneCallback, "onStopNavigation", new RemoteUtils.HostCall() { // from class: androidx.car.app.navigation.f
                @Override // androidx.car.app.utils.RemoteUtils.HostCall
                public final Object dispatch() {
                    Object lambda$onStopNavigation$0;
                    lambda$onStopNavigation$0 = NavigationManager.AnonymousClass1.this.lambda$onStopNavigation$0();
                    return lambda$onStopNavigation$0;
                }
            });
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    protected NavigationManager(@NonNull CarContext carContext, @NonNull HostDispatcher hostDispatcher, @NonNull final Lifecycle lifecycle) {
        Objects.requireNonNull(carContext);
        this.mCarContext = carContext;
        Objects.requireNonNull(hostDispatcher);
        this.mHostDispatcher = hostDispatcher;
        this.mNavigationManager = new AnonymousClass1(lifecycle);
        lifecycle.addObserver(new DefaultLifecycleObserver() { // from class: androidx.car.app.navigation.NavigationManager.2
            @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
            public void onDestroy(@NonNull LifecycleOwner lifecycleOwner) {
                NavigationManager.this.onStopNavigation();
                lifecycle.removeObserver(this);
            }
        });
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static NavigationManager create(@NonNull CarContext carContext, @NonNull HostDispatcher hostDispatcher, @NonNull Lifecycle lifecycle) {
        Objects.requireNonNull(carContext);
        Objects.requireNonNull(hostDispatcher);
        Objects.requireNonNull(lifecycle);
        return new NavigationManager(carContext, hostDispatcher, lifecycle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onStopNavigation$3() {
        NavigationManagerCallback navigationManagerCallback = this.mNavigationManagerCallback;
        if (navigationManagerCallback != null) {
            navigationManagerCallback.onStopNavigation();
        }
    }

    @MainThread
    public void clearNavigationManagerCallback() {
        ThreadUtils.checkMainThread();
        if (this.mIsNavigating) {
            throw new IllegalStateException("Removing callback while navigating");
        }
        this.mNavigationManagerCallbackExecutor = null;
        this.mNavigationManagerCallback = null;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public INavigationManager.Stub getIInterface() {
        return this.mNavigationManager;
    }

    @MainThread
    public void navigationEnded() {
        ThreadUtils.checkMainThread();
        if (this.mIsNavigating) {
            this.mIsNavigating = false;
            this.mHostDispatcher.dispatch("navigation", "navigationEnded", c.f1505a);
        }
    }

    @MainThread
    public void navigationStarted() {
        ThreadUtils.checkMainThread();
        if (this.mIsNavigating) {
            return;
        }
        if (this.mNavigationManagerCallback == null) {
            throw new IllegalStateException("No callback has been set");
        }
        this.mIsNavigating = true;
        this.mHostDispatcher.dispatch("navigation", "navigationStarted", b.f1504a);
    }

    @MainThread
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void onAutoDriveEnabled() {
        ThreadUtils.checkMainThread();
        Log.isLoggable(LogTags.TAG_NAVIGATION_MANAGER, 3);
        this.mIsAutoDriveEnabled = true;
        final NavigationManagerCallback navigationManagerCallback = this.mNavigationManagerCallback;
        Executor executor = this.mNavigationManagerCallbackExecutor;
        if (navigationManagerCallback == null || executor == null) {
            return;
        }
        executor.execute(new Runnable() { // from class: androidx.car.app.navigation.e
            @Override // java.lang.Runnable
            public final void run() {
                NavigationManagerCallback.this.onAutoDriveEnabled();
            }
        });
    }

    @MainThread
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void onStopNavigation() {
        ThreadUtils.checkMainThread();
        if (this.mIsNavigating) {
            this.mIsNavigating = false;
            Executor executor = this.mNavigationManagerCallbackExecutor;
            if (executor == null) {
                return;
            }
            executor.execute(new Runnable() { // from class: androidx.car.app.navigation.d
                @Override // java.lang.Runnable
                public final void run() {
                    NavigationManager.this.lambda$onStopNavigation$3();
                }
            });
        }
    }

    @SuppressLint({"ExecutorRegistration"})
    @MainThread
    public void setNavigationManagerCallback(@NonNull NavigationManagerCallback navigationManagerCallback) {
        ThreadUtils.checkMainThread();
        setNavigationManagerCallback(ContextCompat.getMainExecutor(this.mCarContext), navigationManagerCallback);
    }

    @MainThread
    public void setNavigationManagerCallback(@NonNull Executor executor, @NonNull NavigationManagerCallback navigationManagerCallback) {
        ThreadUtils.checkMainThread();
        this.mNavigationManagerCallbackExecutor = executor;
        this.mNavigationManagerCallback = navigationManagerCallback;
        if (this.mIsAutoDriveEnabled) {
            onAutoDriveEnabled();
        }
    }

    @MainThread
    public void updateTrip(@NonNull Trip trip) {
        ThreadUtils.checkMainThread();
        if (!this.mIsNavigating) {
            throw new IllegalStateException("Navigation is not started");
        }
        try {
            final Bundleable create = Bundleable.create(trip);
            this.mHostDispatcher.dispatch("navigation", "updateTrip", new HostCall() { // from class: androidx.car.app.navigation.a
                @Override // androidx.car.app.HostCall
                public final Object dispatch(Object obj) {
                    Object updateTrip;
                    updateTrip = ((INavigationHost) obj).updateTrip(Bundleable.this);
                    return updateTrip;
                }
            });
        } catch (BundlerException e2) {
            throw new IllegalArgumentException("Serialization failure", e2);
        }
    }
}
