package androidx.car.app;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import androidx.annotation.CallSuper;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.car.app.CarAppService;
import androidx.car.app.ICarApp;
import androidx.car.app.navigation.NavigationManager;
import androidx.car.app.serialization.Bundleable;
import androidx.car.app.serialization.BundlerException;
import androidx.car.app.utils.LogTags;
import androidx.car.app.utils.RemoteUtils;
import androidx.car.app.utils.ThreadUtils;
import androidx.car.app.validation.HostValidator;
import androidx.car.app.versioning.CarAppApiLevels;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleRegistry;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.security.InvalidParameterException;
import java.util.Objects;
/* loaded from: classes.dex */
public abstract class CarAppService extends Service {
    private static final String AUTO_DRIVE = "AUTO_DRIVE";
    public static final String CATEGORY_CHARGING_APP = "androidx.car.app.category.CHARGING";
    public static final String CATEGORY_NAVIGATION_APP = "androidx.car.app.category.NAVIGATION";
    public static final String CATEGORY_PARKING_APP = "androidx.car.app.category.PARKING";
    public static final String SERVICE_INTERFACE = "androidx.car.app.CarAppService";
    @Nullable
    private AppInfo mAppInfo;
    private final ICarApp.Stub mBinder = new AnonymousClass1();
    @Nullable
    private Session mCurrentSession;
    @Nullable
    private HandshakeInfo mHandshakeInfo;
    @Nullable
    private HostInfo mHostInfo;
    @Nullable
    private HostValidator mHostValidator;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: androidx.car.app.CarAppService$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass1 extends ICarApp.Stub {
        AnonymousClass1() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$getManager$7(String str, IOnDoneCallback iOnDoneCallback) {
            CarAppService carAppService = CarAppService.this;
            Session throwIfInvalid = carAppService.throwIfInvalid(carAppService.getCurrentSession());
            str.hashCode();
            if (str.equals(CarContext.APP_SERVICE)) {
                RemoteUtils.sendSuccessResponseToHost(iOnDoneCallback, "getManager", ((AppManager) throwIfInvalid.getCarContext().getCarService(AppManager.class)).getIInterface());
            } else if (str.equals("navigation")) {
                RemoteUtils.sendSuccessResponseToHost(iOnDoneCallback, "getManager", ((NavigationManager) throwIfInvalid.getCarContext().getCarService(NavigationManager.class)).getIInterface());
            } else {
                Log.e(LogTags.TAG, str + "%s is not a valid manager");
                RemoteUtils.sendFailureResponseToHost(iOnDoneCallback, "getManager", new InvalidParameterException(str + " is not a valid manager type"));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ Object lambda$onAppCreate$0(ICarHost iCarHost, Configuration configuration, Intent intent) {
            Session currentSession = CarAppService.this.getCurrentSession();
            if (currentSession == null || CarAppService.this.getLifecycle().getCurrentState() == Lifecycle.State.DESTROYED) {
                currentSession = CarAppService.this.onCreateSession();
                CarAppService.this.setCurrentSession(currentSession);
            }
            CarAppService carAppService = CarAppService.this;
            HandshakeInfo handshakeInfo = carAppService.getHandshakeInfo();
            Objects.requireNonNull(handshakeInfo);
            HandshakeInfo handshakeInfo2 = handshakeInfo;
            HostInfo hostInfo = CarAppService.this.getHostInfo();
            Objects.requireNonNull(hostInfo);
            currentSession.configure(carAppService, handshakeInfo2, hostInfo, iCarHost, configuration);
            LifecycleRegistry lifecycle = CarAppService.this.getLifecycle();
            Lifecycle.State currentState = lifecycle.getCurrentState();
            int size = ((ScreenManager) currentSession.getCarContext().getCarService(ScreenManager.class)).getScreenStack().size();
            if (currentState.isAtLeast(Lifecycle.State.CREATED) && size >= 1) {
                Log.isLoggable(LogTags.TAG, 3);
                onNewIntentInternal(currentSession, intent);
                return null;
            }
            if (Log.isLoggable(LogTags.TAG, 3)) {
                StringBuilder sb = new StringBuilder();
                sb.append("onAppCreate the app was not yet created or the screen stack was empty state: ");
                sb.append(lifecycle.getCurrentState());
                sb.append(", stack size: ");
                sb.append(size);
            }
            lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
            ((ScreenManager) currentSession.getCarContext().getCarService(ScreenManager.class)).push(currentSession.onCreateScreen(intent));
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ Object lambda$onAppPause$3() {
            CarAppService.this.getLifecycle().handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ Object lambda$onAppResume$2() {
            CarAppService.this.getLifecycle().handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ Object lambda$onAppStart$1() {
            CarAppService.this.getLifecycle().handleLifecycleEvent(Lifecycle.Event.ON_START);
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ Object lambda$onAppStop$4() {
            CarAppService.this.getLifecycle().handleLifecycleEvent(Lifecycle.Event.ON_STOP);
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ Object lambda$onConfigurationChanged$6(Configuration configuration) {
            CarAppService carAppService = CarAppService.this;
            onConfigurationChangedInternal(carAppService.throwIfInvalid(carAppService.getCurrentSession()), configuration);
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ Object lambda$onNewIntent$5(Intent intent) {
            CarAppService carAppService = CarAppService.this;
            onNewIntentInternal(carAppService.throwIfInvalid(carAppService.getCurrentSession()), intent);
            return null;
        }

        @MainThread
        private void onConfigurationChangedInternal(Session session, Configuration configuration) {
            ThreadUtils.checkMainThread();
            if (Log.isLoggable(LogTags.TAG, 3)) {
                StringBuilder sb = new StringBuilder();
                sb.append("onCarConfigurationChanged configuration: ");
                sb.append(configuration);
            }
            session.onCarConfigurationChangedInternal(configuration);
        }

        @MainThread
        private void onNewIntentInternal(Session session, Intent intent) {
            ThreadUtils.checkMainThread();
            session.onNewIntent(intent);
        }

        @Override // androidx.car.app.ICarApp
        public void getAppInfo(IOnDoneCallback iOnDoneCallback) {
            try {
                RemoteUtils.sendSuccessResponseToHost(iOnDoneCallback, "getAppInfo", CarAppService.this.getAppInfo());
            } catch (IllegalArgumentException e2) {
                RemoteUtils.sendFailureResponseToHost(iOnDoneCallback, "getAppInfo", e2);
            }
        }

        @Override // androidx.car.app.ICarApp
        public void getManager(@NonNull final String str, final IOnDoneCallback iOnDoneCallback) {
            ThreadUtils.runOnMain(new Runnable() { // from class: androidx.car.app.p
                @Override // java.lang.Runnable
                public final void run() {
                    CarAppService.AnonymousClass1.this.lambda$getManager$7(str, iOnDoneCallback);
                }
            });
        }

        @Override // androidx.car.app.ICarApp
        public void onAppCreate(final ICarHost iCarHost, final Intent intent, final Configuration configuration, IOnDoneCallback iOnDoneCallback) {
            if (Log.isLoggable(LogTags.TAG, 3)) {
                StringBuilder sb = new StringBuilder();
                sb.append("onAppCreate intent: ");
                sb.append(intent);
            }
            RemoteUtils.dispatchCallFromHost(iOnDoneCallback, "onAppCreate", new RemoteUtils.HostCall() { // from class: androidx.car.app.o
                @Override // androidx.car.app.utils.RemoteUtils.HostCall
                public final Object dispatch() {
                    Object lambda$onAppCreate$0;
                    lambda$onAppCreate$0 = CarAppService.AnonymousClass1.this.lambda$onAppCreate$0(iCarHost, configuration, intent);
                    return lambda$onAppCreate$0;
                }
            });
            Log.isLoggable(LogTags.TAG, 3);
        }

        @Override // androidx.car.app.ICarApp
        public void onAppPause(IOnDoneCallback iOnDoneCallback) {
            RemoteUtils.dispatchCallFromHost(CarAppService.this.getLifecycleIfValid(), iOnDoneCallback, "onAppPause", new RemoteUtils.HostCall() { // from class: androidx.car.app.i
                @Override // androidx.car.app.utils.RemoteUtils.HostCall
                public final Object dispatch() {
                    Object lambda$onAppPause$3;
                    lambda$onAppPause$3 = CarAppService.AnonymousClass1.this.lambda$onAppPause$3();
                    return lambda$onAppPause$3;
                }
            });
        }

        @Override // androidx.car.app.ICarApp
        public void onAppResume(IOnDoneCallback iOnDoneCallback) {
            RemoteUtils.dispatchCallFromHost(CarAppService.this.getLifecycleIfValid(), iOnDoneCallback, "onAppResume", new RemoteUtils.HostCall() { // from class: androidx.car.app.j
                @Override // androidx.car.app.utils.RemoteUtils.HostCall
                public final Object dispatch() {
                    Object lambda$onAppResume$2;
                    lambda$onAppResume$2 = CarAppService.AnonymousClass1.this.lambda$onAppResume$2();
                    return lambda$onAppResume$2;
                }
            });
        }

        @Override // androidx.car.app.ICarApp
        public void onAppStart(IOnDoneCallback iOnDoneCallback) {
            RemoteUtils.dispatchCallFromHost(CarAppService.this.getLifecycleIfValid(), iOnDoneCallback, "onAppStart", new RemoteUtils.HostCall() { // from class: androidx.car.app.l
                @Override // androidx.car.app.utils.RemoteUtils.HostCall
                public final Object dispatch() {
                    Object lambda$onAppStart$1;
                    lambda$onAppStart$1 = CarAppService.AnonymousClass1.this.lambda$onAppStart$1();
                    return lambda$onAppStart$1;
                }
            });
        }

        @Override // androidx.car.app.ICarApp
        public void onAppStop(IOnDoneCallback iOnDoneCallback) {
            RemoteUtils.dispatchCallFromHost(CarAppService.this.getLifecycleIfValid(), iOnDoneCallback, "onAppStop", new RemoteUtils.HostCall() { // from class: androidx.car.app.k
                @Override // androidx.car.app.utils.RemoteUtils.HostCall
                public final Object dispatch() {
                    Object lambda$onAppStop$4;
                    lambda$onAppStop$4 = CarAppService.AnonymousClass1.this.lambda$onAppStop$4();
                    return lambda$onAppStop$4;
                }
            });
        }

        @Override // androidx.car.app.ICarApp
        public void onConfigurationChanged(final Configuration configuration, IOnDoneCallback iOnDoneCallback) {
            RemoteUtils.dispatchCallFromHost(CarAppService.this.getLifecycleIfValid(), iOnDoneCallback, "onConfigurationChanged", new RemoteUtils.HostCall() { // from class: androidx.car.app.n
                @Override // androidx.car.app.utils.RemoteUtils.HostCall
                public final Object dispatch() {
                    Object lambda$onConfigurationChanged$6;
                    lambda$onConfigurationChanged$6 = CarAppService.AnonymousClass1.this.lambda$onConfigurationChanged$6(configuration);
                    return lambda$onConfigurationChanged$6;
                }
            });
        }

        @Override // androidx.car.app.ICarApp
        public void onHandshakeCompleted(Bundleable bundleable, IOnDoneCallback iOnDoneCallback) {
            try {
                HandshakeInfo handshakeInfo = (HandshakeInfo) bundleable.get();
                String hostPackageName = handshakeInfo.getHostPackageName();
                int callingUid = Binder.getCallingUid();
                HostInfo hostInfo = new HostInfo(hostPackageName, callingUid);
                if (!CarAppService.this.getHostValidator().isValidHost(hostInfo)) {
                    RemoteUtils.sendFailureResponseToHost(iOnDoneCallback, "onHandshakeCompleted", new IllegalArgumentException("Unknown host '" + hostPackageName + "', uid:" + callingUid));
                    return;
                }
                int minCarAppApiLevel = CarAppService.this.getAppInfo().getMinCarAppApiLevel();
                int hostCarAppApiLevel = handshakeInfo.getHostCarAppApiLevel();
                if (minCarAppApiLevel <= hostCarAppApiLevel) {
                    CarAppService.this.setHostInfo(hostInfo);
                    CarAppService.this.setHandshakeInfo(handshakeInfo);
                    RemoteUtils.sendSuccessResponseToHost(iOnDoneCallback, "onHandshakeCompleted", null);
                    return;
                }
                RemoteUtils.sendFailureResponseToHost(iOnDoneCallback, "onHandshakeCompleted", new IllegalArgumentException("Host API level (" + hostCarAppApiLevel + ") is less than the app's min API level (" + minCarAppApiLevel + ")"));
            } catch (BundlerException | IllegalArgumentException e2) {
                CarAppService.this.setHostInfo(null);
                RemoteUtils.sendFailureResponseToHost(iOnDoneCallback, "onHandshakeCompleted", e2);
            }
        }

        @Override // androidx.car.app.ICarApp
        public void onNewIntent(final Intent intent, IOnDoneCallback iOnDoneCallback) {
            RemoteUtils.dispatchCallFromHost(CarAppService.this.getLifecycleIfValid(), iOnDoneCallback, "onNewIntent", new RemoteUtils.HostCall() { // from class: androidx.car.app.m
                @Override // androidx.car.app.utils.RemoteUtils.HostCall
                public final Object dispatch() {
                    Object lambda$onNewIntent$5;
                    lambda$onNewIntent$5 = CarAppService.AnonymousClass1.this.lambda$onNewIntent$5(intent);
                    return lambda$onNewIntent$5;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$dump$1() {
        Session session = this.mCurrentSession;
        if (session != null) {
            ((NavigationManager) session.getCarContext().getCarService(NavigationManager.class)).onAutoDriveEnabled();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onUnbind$0() {
        if (this.mCurrentSession != null) {
            LifecycleRegistry lifecycleIfValid = getLifecycleIfValid();
            if (lifecycleIfValid == null) {
                Log.e(LogTags.TAG, "Null Session when unbinding");
            } else {
                lifecycleIfValid.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
            }
        }
        this.mCurrentSession = null;
    }

    @NonNull
    public abstract HostValidator createHostValidator();

    @Override // android.app.Service
    @CallSuper
    public final void dump(@NonNull FileDescriptor fileDescriptor, @NonNull PrintWriter printWriter, @Nullable String[] strArr) {
        super.dump(fileDescriptor, printWriter, strArr);
        for (String str : strArr) {
            if (AUTO_DRIVE.equals(str)) {
                Log.isLoggable(LogTags.TAG, 3);
                ThreadUtils.runOnMain(new Runnable() { // from class: androidx.car.app.g
                    @Override // java.lang.Runnable
                    public final void run() {
                        CarAppService.this.lambda$dump$1();
                    }
                });
            }
        }
    }

    @NonNull
    AppInfo getAppInfo() {
        if (this.mAppInfo == null) {
            this.mAppInfo = AppInfo.create(this);
        }
        return this.mAppInfo;
    }

    @Nullable
    public final Session getCurrentSession() {
        return this.mCurrentSession;
    }

    @Nullable
    HandshakeInfo getHandshakeInfo() {
        return this.mHandshakeInfo;
    }

    @Nullable
    public final HostInfo getHostInfo() {
        return this.mHostInfo;
    }

    @NonNull
    HostValidator getHostValidator() {
        if (this.mHostValidator == null) {
            this.mHostValidator = createHostValidator();
        }
        return this.mHostValidator;
    }

    @NonNull
    LifecycleRegistry getLifecycle() {
        LifecycleRegistry lifecycleIfValid = getLifecycleIfValid();
        Objects.requireNonNull(lifecycleIfValid);
        return lifecycleIfValid;
    }

    @Nullable
    LifecycleRegistry getLifecycleIfValid() {
        Session currentSession = getCurrentSession();
        if (currentSession == null) {
            return null;
        }
        return (LifecycleRegistry) currentSession.getLifecycleInternal();
    }

    @Override // android.app.Service
    @NonNull
    @CallSuper
    public final IBinder onBind(@NonNull Intent intent) {
        return this.mBinder;
    }

    @NonNull
    public abstract Session onCreateSession();

    @Override // android.app.Service
    public final boolean onUnbind(@NonNull Intent intent) {
        if (Log.isLoggable(LogTags.TAG, 3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("onUnbind intent: ");
            sb.append(intent);
        }
        ThreadUtils.runOnMain(new Runnable() { // from class: androidx.car.app.h
            @Override // java.lang.Runnable
            public final void run() {
                CarAppService.this.lambda$onUnbind$0();
            }
        });
        Log.isLoggable(LogTags.TAG, 3);
        return true;
    }

    @VisibleForTesting
    void setAppInfo(@Nullable AppInfo appInfo) {
        this.mAppInfo = appInfo;
    }

    void setCurrentSession(@Nullable Session session) {
        this.mCurrentSession = session;
    }

    @VisibleForTesting
    void setHandshakeInfo(@NonNull HandshakeInfo handshakeInfo) {
        int hostCarAppApiLevel = handshakeInfo.getHostCarAppApiLevel();
        if (CarAppApiLevels.isValid(hostCarAppApiLevel)) {
            this.mHandshakeInfo = handshakeInfo;
            return;
        }
        throw new IllegalArgumentException("Invalid Car App API level received: " + hostCarAppApiLevel);
    }

    void setHostInfo(@Nullable HostInfo hostInfo) {
        this.mHostInfo = hostInfo;
    }

    Session throwIfInvalid(@Nullable Session session) {
        if (session != null) {
            return session;
        }
        throw new IllegalStateException("Null session found when non-null expected");
    }
}
