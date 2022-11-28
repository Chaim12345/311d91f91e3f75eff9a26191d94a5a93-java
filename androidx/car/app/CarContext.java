package androidx.car.app;

import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import androidx.activity.OnBackPressedDispatcher;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.car.app.IOnRequestPermissionsListener;
import androidx.car.app.IStartCarApp;
import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.constraints.ConstraintManager;
import androidx.car.app.hardware.CarHardwareManager;
import androidx.car.app.managers.Manager;
import androidx.car.app.managers.ManagerCache;
import androidx.car.app.managers.ManagerFactory;
import androidx.car.app.managers.ResultManager;
import androidx.car.app.navigation.NavigationManager;
import androidx.car.app.utils.LogTags;
import androidx.car.app.utils.RemoteUtils;
import androidx.car.app.utils.ThreadUtils;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import com.google.firebase.messaging.Constants;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public class CarContext extends ContextWrapper {
    public static final String ACTION_NAVIGATE = "androidx.car.app.action.NAVIGATE";
    public static final String APP_SERVICE = "app";
    public static final String CAR_SERVICE = "car";
    @RequiresCarApi(2)
    public static final String CONSTRAINT_SERVICE = "constraints";
    static final String EXTRA_ON_REQUEST_PERMISSIONS_RESULT_LISTENER_KEY = "androidx.car.app.action.EXTRA_ON_REQUEST_PERMISSIONS_RESULT_LISTENER_KEY";
    static final String EXTRA_PERMISSIONS_KEY = "androidx.car.app.action.EXTRA_PERMISSIONS_KEY";
    public static final String EXTRA_START_CAR_APP_BINDER_KEY = "androidx.car.app.extra.START_CAR_APP_BINDER_KEY";
    @RequiresCarApi(3)
    public static final String HARDWARE_SERVICE = "hardware";
    public static final String NAVIGATION_SERVICE = "navigation";
    static final String REQUEST_PERMISSIONS_ACTION = "androidx.car.app.action.REQUEST_PERMISSIONS";
    public static final String SCREEN_SERVICE = "screen";
    private int mCarAppApiLevel;
    private final HostDispatcher mHostDispatcher;
    @Nullable
    private HostInfo mHostInfo;
    private final Lifecycle mLifecycle;
    private final ManagerCache mManagers;
    private final OnBackPressedDispatcher mOnBackPressedDispatcher;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: androidx.car.app.CarContext$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass1 extends IOnRequestPermissionsListener.Stub {
        final /* synthetic */ Executor val$executor;
        final /* synthetic */ Lifecycle val$lifecycle;
        final /* synthetic */ OnRequestPermissionsListener val$listener;

        AnonymousClass1(Lifecycle lifecycle, Executor executor, OnRequestPermissionsListener onRequestPermissionsListener) {
            this.val$lifecycle = lifecycle;
            this.val$executor = executor;
            this.val$listener = onRequestPermissionsListener;
        }

        @Override // androidx.car.app.IOnRequestPermissionsListener
        public void onRequestPermissionsResult(String[] strArr, String[] strArr2) {
            if (this.val$lifecycle.getCurrentState().isAtLeast(Lifecycle.State.CREATED)) {
                final List asList = Arrays.asList(strArr);
                final List asList2 = Arrays.asList(strArr2);
                Executor executor = this.val$executor;
                final OnRequestPermissionsListener onRequestPermissionsListener = this.val$listener;
                executor.execute(new Runnable() { // from class: androidx.car.app.b0
                    @Override // java.lang.Runnable
                    public final void run() {
                        OnRequestPermissionsListener.this.onRequestPermissionsResult(asList, asList2);
                    }
                });
            }
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public @interface CarServiceType {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    protected CarContext(@NonNull final Lifecycle lifecycle, @NonNull final HostDispatcher hostDispatcher) {
        super(null);
        ManagerCache managerCache = new ManagerCache();
        this.mManagers = managerCache;
        this.mCarAppApiLevel = 0;
        this.mHostInfo = null;
        this.mHostDispatcher = hostDispatcher;
        managerCache.addFactory(AppManager.class, APP_SERVICE, new ManagerFactory() { // from class: androidx.car.app.w
            @Override // androidx.car.app.managers.ManagerFactory
            public final Manager create() {
                AppManager lambda$new$3;
                lambda$new$3 = CarContext.this.lambda$new$3(hostDispatcher, lifecycle);
                return lambda$new$3;
            }
        });
        managerCache.addFactory(NavigationManager.class, "navigation", new ManagerFactory() { // from class: androidx.car.app.v
            @Override // androidx.car.app.managers.ManagerFactory
            public final Manager create() {
                NavigationManager lambda$new$4;
                lambda$new$4 = CarContext.this.lambda$new$4(hostDispatcher, lifecycle);
                return lambda$new$4;
            }
        });
        managerCache.addFactory(ScreenManager.class, SCREEN_SERVICE, new ManagerFactory() { // from class: androidx.car.app.x
            @Override // androidx.car.app.managers.ManagerFactory
            public final Manager create() {
                ScreenManager lambda$new$5;
                lambda$new$5 = CarContext.this.lambda$new$5(lifecycle);
                return lambda$new$5;
            }
        });
        managerCache.addFactory(ConstraintManager.class, CONSTRAINT_SERVICE, new ManagerFactory() { // from class: androidx.car.app.t
            @Override // androidx.car.app.managers.ManagerFactory
            public final Manager create() {
                ConstraintManager lambda$new$6;
                lambda$new$6 = CarContext.this.lambda$new$6(hostDispatcher);
                return lambda$new$6;
            }
        });
        managerCache.addFactory(CarHardwareManager.class, HARDWARE_SERVICE, new ManagerFactory() { // from class: androidx.car.app.u
            @Override // androidx.car.app.managers.ManagerFactory
            public final Manager create() {
                CarHardwareManager lambda$new$7;
                lambda$new$7 = CarContext.this.lambda$new$7(hostDispatcher);
                return lambda$new$7;
            }
        });
        managerCache.addFactory(ResultManager.class, null, y.f1569a);
        this.mOnBackPressedDispatcher = new OnBackPressedDispatcher(new Runnable() { // from class: androidx.car.app.a0
            @Override // java.lang.Runnable
            public final void run() {
                CarContext.this.lambda$new$8();
            }
        });
        this.mLifecycle = lifecycle;
        lifecycle.addObserver(new DefaultLifecycleObserver() { // from class: androidx.car.app.CarContext.2
            @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
            public void onDestroy(@NonNull LifecycleOwner lifecycleOwner) {
                hostDispatcher.resetHosts();
                lifecycleOwner.getLifecycle().removeObserver(this);
            }
        });
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static CarContext create(@NonNull Lifecycle lifecycle) {
        return new CarContext(lifecycle, new HostDispatcher());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ AppManager lambda$new$3(HostDispatcher hostDispatcher, Lifecycle lifecycle) {
        return AppManager.create(this, hostDispatcher, lifecycle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ NavigationManager lambda$new$4(HostDispatcher hostDispatcher, Lifecycle lifecycle) {
        return NavigationManager.create(this, hostDispatcher, lifecycle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ScreenManager lambda$new$5(Lifecycle lifecycle) {
        return ScreenManager.create(this, lifecycle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ConstraintManager lambda$new$6(HostDispatcher hostDispatcher) {
        return ConstraintManager.create(this, hostDispatcher);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ CarHardwareManager lambda$new$7(HostDispatcher hostDispatcher) {
        return CarHardwareManager.create(this, hostDispatcher);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$8() {
        ((ScreenManager) getCarService(ScreenManager.class)).pop();
    }

    @Deprecated
    public static void startCarApp(@NonNull Intent intent, @NonNull final Intent intent2) {
        Objects.requireNonNull(intent);
        Objects.requireNonNull(intent2);
        Bundle extras = intent.getExtras();
        IBinder binder = extras != null ? extras.getBinder(EXTRA_START_CAR_APP_BINDER_KEY) : null;
        if (binder == null) {
            throw new IllegalArgumentException("Notification intent missing expected extra");
        }
        IStartCarApp asInterface = IStartCarApp.Stub.asInterface(binder);
        Objects.requireNonNull(asInterface);
        final IStartCarApp iStartCarApp = asInterface;
        RemoteUtils.dispatchCallToHost("startCarApp from notification", new RemoteUtils.RemoteCall() { // from class: androidx.car.app.z
            @Override // androidx.car.app.utils.RemoteUtils.RemoteCall
            public final Object call() {
                Object startCarApp;
                startCarApp = IStartCarApp.this.startCarApp(intent2);
                return startCarApp;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @MainThread
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void attachBaseContext(@NonNull Context context, @NonNull Configuration configuration) {
        ThreadUtils.checkMainThread();
        if (getBaseContext() == null) {
            Object systemService = context.getSystemService(Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION);
            Objects.requireNonNull(systemService);
            attachBaseContext(context.createDisplayContext(((DisplayManager) systemService).createVirtualDisplay("CarAppService", configuration.screenWidthDp, configuration.screenHeightDp, configuration.densityDpi, null, 8).getDisplay()).createConfigurationContext(configuration));
        }
        onCarConfigurationChanged(configuration);
    }

    public void finishCarApp() {
        this.mHostDispatcher.dispatch(CAR_SERVICE, "finish", s.f1522a);
    }

    @Nullable
    @RequiresCarApi(2)
    public ComponentName getCallingComponent() {
        try {
            return ((ResultManager) getCarService(ResultManager.class)).getCallingComponent();
        } catch (IllegalStateException unused) {
            return null;
        }
    }

    public int getCarAppApiLevel() {
        int i2 = this.mCarAppApiLevel;
        if (i2 != 0) {
            return i2;
        }
        throw new IllegalStateException("Car App API level hasn't been established yet");
    }

    @NonNull
    public <T extends Manager> T getCarService(@NonNull Class<T> cls) {
        Objects.requireNonNull(cls);
        return (T) this.mManagers.getOrCreate(cls);
    }

    @NonNull
    public Object getCarService(@NonNull String str) {
        Objects.requireNonNull(str);
        return this.mManagers.getOrCreate(str);
    }

    @NonNull
    public String getCarServiceName(@NonNull Class<? extends Manager> cls) {
        Objects.requireNonNull(cls);
        return this.mManagers.getName(cls);
    }

    @Nullable
    public HostInfo getHostInfo() {
        return this.mHostInfo;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    ManagerCache getManagers() {
        return this.mManagers;
    }

    @NonNull
    public OnBackPressedDispatcher getOnBackPressedDispatcher() {
        return this.mOnBackPressedDispatcher;
    }

    public boolean isDarkMode() {
        return (getResources().getConfiguration().uiMode & 48) == 32;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @MainThread
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void onCarConfigurationChanged(@NonNull Configuration configuration) {
        ThreadUtils.checkMainThread();
        if (Log.isLoggable(LogTags.TAG, 3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Car configuration changed, configuration: ");
            sb.append(configuration);
            sb.append(", displayMetrics: ");
            sb.append(getResources().getDisplayMetrics());
        }
        Resources resources = getResources();
        Objects.requireNonNull(configuration);
        resources.updateConfiguration(configuration, getResources().getDisplayMetrics());
    }

    public void requestPermissions(@NonNull List<String> list, @NonNull OnRequestPermissionsListener onRequestPermissionsListener) {
        requestPermissions(list, ContextCompat.getMainExecutor(this), onRequestPermissionsListener);
    }

    public void requestPermissions(@NonNull List<String> list, @NonNull Executor executor, @NonNull OnRequestPermissionsListener onRequestPermissionsListener) {
        Objects.requireNonNull(executor);
        Objects.requireNonNull(list);
        Objects.requireNonNull(onRequestPermissionsListener);
        ComponentName componentName = new ComponentName(this, CarAppInternalActivity.class);
        Lifecycle lifecycle = this.mLifecycle;
        Bundle bundle = new Bundle(2);
        bundle.putBinder(EXTRA_ON_REQUEST_PERMISSIONS_RESULT_LISTENER_KEY, new AnonymousClass1(lifecycle, executor, onRequestPermissionsListener).asBinder());
        bundle.putStringArray(EXTRA_PERMISSIONS_KEY, (String[]) list.toArray(new String[0]));
        startActivity(new Intent(REQUEST_PERMISSIONS_ACTION).setComponent(componentName).putExtras(bundle).addFlags(268435456));
    }

    @RequiresCarApi(2)
    public void setCarAppResult(int i2, @Nullable Intent intent) {
        ((ResultManager) getCarService(ResultManager.class)).setCarAppResult(i2, intent);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @MainThread
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setCarHost(@NonNull ICarHost iCarHost) {
        ThreadUtils.checkMainThread();
        HostDispatcher hostDispatcher = this.mHostDispatcher;
        Objects.requireNonNull(iCarHost);
        hostDispatcher.setCarHost(iCarHost);
    }

    public void startCarApp(@NonNull final Intent intent) {
        Objects.requireNonNull(intent);
        this.mHostDispatcher.dispatch(CAR_SERVICE, "startCarApp", new HostCall() { // from class: androidx.car.app.q
            @Override // androidx.car.app.HostCall
            public final Object dispatch(Object obj) {
                Object startCarApp;
                startCarApp = ((ICarHost) obj).startCarApp(intent);
                return startCarApp;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @MainThread
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void updateHandshakeInfo(@NonNull HandshakeInfo handshakeInfo) {
        this.mCarAppApiLevel = handshakeInfo.getHostCarAppApiLevel();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @MainThread
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void updateHostInfo(@NonNull HostInfo hostInfo) {
        this.mHostInfo = hostInfo;
    }
}
