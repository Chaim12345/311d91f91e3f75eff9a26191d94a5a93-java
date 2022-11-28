package androidx.camera.core;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import androidx.annotation.GuardedBy;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.experimental.UseExperimental;
import androidx.arch.core.util.Function;
import androidx.camera.core.CameraXConfig;
import androidx.camera.core.impl.CameraDeviceSurfaceManager;
import androidx.camera.core.impl.CameraFactory;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.CameraRepository;
import androidx.camera.core.impl.CameraThreadConfig;
import androidx.camera.core.impl.CameraValidator;
import androidx.camera.core.impl.UseCaseConfigFactory;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.AsyncFunction;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.FutureChain;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.camera.core.internal.compat.quirk.DeviceQuirks;
import androidx.camera.core.internal.compat.quirk.IncompleteCameraListQuirk;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.os.HandlerCompat;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
@MainThread
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public final class CameraX {
    private static final long RETRY_SLEEP_MILLIS = 500;
    private static final String RETRY_TOKEN = "retry_token";
    private static final String TAG = "CameraX";
    private static final long WAIT_INITIALIZED_TIMEOUT_MILLIS = 3000;
    @GuardedBy("INSTANCE_LOCK")

    /* renamed from: c  reason: collision with root package name */
    static CameraX f959c;
    @GuardedBy("INSTANCE_LOCK")
    private static CameraXConfig.Provider sConfigProvider;
    private Context mAppContext;
    private final Executor mCameraExecutor;
    private CameraFactory mCameraFactory;
    private final CameraXConfig mCameraXConfig;
    private UseCaseConfigFactory mDefaultConfigFactory;
    private final Handler mSchedulerHandler;
    @Nullable
    private final HandlerThread mSchedulerThread;
    private CameraDeviceSurfaceManager mSurfaceManager;

    /* renamed from: b  reason: collision with root package name */
    static final Object f958b = new Object();
    @GuardedBy("INSTANCE_LOCK")
    private static ListenableFuture<Void> sInitializeFuture = Futures.immediateFailedFuture(new IllegalStateException("CameraX is not initialized."));
    @GuardedBy("INSTANCE_LOCK")
    private static ListenableFuture<Void> sShutdownFuture = Futures.immediateFuture(null);

    /* renamed from: a  reason: collision with root package name */
    final CameraRepository f960a = new CameraRepository();
    private final Object mInitializeLock = new Object();
    @GuardedBy("mInitializeLock")
    private InternalInitState mInitState = InternalInitState.UNINITIALIZED;
    @GuardedBy("mInitializeLock")
    private ListenableFuture<Void> mShutdownInternalFuture = Futures.immediateFuture(null);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: androidx.camera.core.CameraX$2  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass2 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f963a;

        static {
            int[] iArr = new int[InternalInitState.values().length];
            f963a = iArr;
            try {
                iArr[InternalInitState.UNINITIALIZED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f963a[InternalInitState.INITIALIZING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f963a[InternalInitState.INITIALIZED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f963a[InternalInitState.SHUTDOWN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public enum InternalInitState {
        UNINITIALIZED,
        INITIALIZING,
        INITIALIZED,
        SHUTDOWN
    }

    CameraX(@NonNull CameraXConfig cameraXConfig) {
        this.mCameraXConfig = (CameraXConfig) Preconditions.checkNotNull(cameraXConfig);
        Executor cameraExecutor = cameraXConfig.getCameraExecutor(null);
        Handler schedulerHandler = cameraXConfig.getSchedulerHandler(null);
        this.mCameraExecutor = cameraExecutor == null ? new CameraExecutor() : cameraExecutor;
        if (schedulerHandler == null) {
            HandlerThread handlerThread = new HandlerThread("CameraX-scheduler", 10);
            this.mSchedulerThread = handlerThread;
            handlerThread.start();
            schedulerHandler = HandlerCompat.createAsync(handlerThread.getLooper());
        } else {
            this.mSchedulerThread = null;
        }
        this.mSchedulerHandler = schedulerHandler;
    }

    @NonNull
    private static CameraX checkInitialized() {
        CameraX waitInitialized = waitInitialized();
        Preconditions.checkState(waitInitialized.isInitializedInternal(), "Must call CameraX.initialize() first");
        return waitInitialized;
    }

    public static void configureInstance(@NonNull final CameraXConfig cameraXConfig) {
        synchronized (f958b) {
            configureInstanceLocked(new CameraXConfig.Provider() { // from class: androidx.camera.core.f
                @Override // androidx.camera.core.CameraXConfig.Provider
                public final CameraXConfig getCameraXConfig() {
                    CameraXConfig lambda$configureInstance$1;
                    lambda$configureInstance$1 = CameraX.lambda$configureInstance$1(CameraXConfig.this);
                    return lambda$configureInstance$1;
                }
            });
        }
    }

    @GuardedBy("INSTANCE_LOCK")
    private static void configureInstanceLocked(@NonNull CameraXConfig.Provider provider) {
        Preconditions.checkNotNull(provider);
        Preconditions.checkState(sConfigProvider == null, "CameraX has already been configured. To use a different configuration, shutdown() must be called.");
        sConfigProvider = provider;
        Integer num = (Integer) provider.getCameraXConfig().retrieveOption(CameraXConfig.f969f, null);
        if (num != null) {
            Logger.b(num.intValue());
        }
    }

    @Nullable
    private static Application getApplicationFromContext(@NonNull Context context) {
        for (Context applicationContext = context.getApplicationContext(); applicationContext instanceof ContextWrapper; applicationContext = ((ContextWrapper) applicationContext).getBaseContext()) {
            if (applicationContext instanceof Application) {
                return (Application) applicationContext;
            }
        }
        return null;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static CameraInternal getCameraWithCameraSelector(@NonNull CameraSelector cameraSelector) {
        return cameraSelector.select(checkInitialized().getCameraRepository().getCameras());
    }

    @Nullable
    private static CameraXConfig.Provider getConfigProvider(@NonNull Context context) {
        Application applicationFromContext = getApplicationFromContext(context);
        if (applicationFromContext instanceof CameraXConfig.Provider) {
            return (CameraXConfig.Provider) applicationFromContext;
        }
        try {
            return (CameraXConfig.Provider) Class.forName(context.getApplicationContext().getResources().getString(R.string.androidx_camera_default_config_provider)).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Resources.NotFoundException | ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | NullPointerException | InvocationTargetException e2) {
            Logger.e(TAG, "Failed to retrieve default CameraXConfig.Provider from resources", e2);
            return null;
        }
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Deprecated
    public static Context getContext() {
        return checkInitialized().mAppContext;
    }

    @NonNull
    private static ListenableFuture<CameraX> getInstance() {
        ListenableFuture<CameraX> instanceLocked;
        synchronized (f958b) {
            instanceLocked = getInstanceLocked();
        }
        return instanceLocked;
    }

    @NonNull
    @GuardedBy("INSTANCE_LOCK")
    private static ListenableFuture<CameraX> getInstanceLocked() {
        final CameraX cameraX = f959c;
        return cameraX == null ? Futures.immediateFailedFuture(new IllegalStateException("Must call CameraX.initialize() first")) : Futures.transform(sInitializeFuture, new Function() { // from class: androidx.camera.core.c
            @Override // androidx.arch.core.util.Function
            public final Object apply(Object obj) {
                CameraX lambda$getInstanceLocked$6;
                lambda$getInstanceLocked$6 = CameraX.lambda$getInstanceLocked$6(CameraX.this, (Void) obj);
                return lambda$getInstanceLocked$6;
            }
        }, CameraXExecutors.directExecutor());
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static ListenableFuture<CameraX> getOrCreateInstance(@NonNull Context context) {
        ListenableFuture<CameraX> instanceLocked;
        Preconditions.checkNotNull(context, "Context must not be null.");
        synchronized (f958b) {
            boolean z = sConfigProvider != null;
            instanceLocked = getInstanceLocked();
            if (instanceLocked.isDone()) {
                try {
                    instanceLocked.get();
                } catch (InterruptedException e2) {
                    throw new RuntimeException("Unexpected thread interrupt. Should not be possible since future is already complete.", e2);
                } catch (ExecutionException unused) {
                    m();
                    instanceLocked = null;
                }
            }
            if (instanceLocked == null) {
                if (!z) {
                    CameraXConfig.Provider configProvider = getConfigProvider(context);
                    if (configProvider == null) {
                        throw new IllegalStateException("CameraX is not configured properly. The most likely cause is you did not include a default implementation in your build such as 'camera-camera2'.");
                    }
                    configureInstanceLocked(configProvider);
                }
                initializeInstanceLocked(context);
                instanceLocked = getInstanceLocked();
            }
        }
        return instanceLocked;
    }

    @UseExperimental(markerClass = ExperimentalAvailableCamerasLimiter.class)
    private void initAndRetryRecursively(@NonNull final Executor executor, final long j2, @NonNull final Context context, @NonNull final CallbackToFutureAdapter.Completer<Void> completer) {
        executor.execute(new Runnable() { // from class: androidx.camera.core.m
            @Override // java.lang.Runnable
            public final void run() {
                CameraX.this.lambda$initAndRetryRecursively$9(context, executor, completer, j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ListenableFuture<Void> initInternal(@NonNull final Context context) {
        ListenableFuture<Void> future;
        synchronized (this.mInitializeLock) {
            Preconditions.checkState(this.mInitState == InternalInitState.UNINITIALIZED, "CameraX.initInternal() should only be called once per instance");
            this.mInitState = InternalInitState.INITIALIZING;
            future = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.core.k
                @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
                public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                    Object lambda$initInternal$7;
                    lambda$initInternal$7 = CameraX.this.lambda$initInternal$7(context, completer);
                    return lambda$initInternal$7;
                }
            });
        }
        return future;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.TESTS})
    public static ListenableFuture<Void> initialize(@NonNull Context context, @NonNull final CameraXConfig cameraXConfig) {
        ListenableFuture<Void> listenableFuture;
        synchronized (f958b) {
            Preconditions.checkNotNull(context);
            configureInstanceLocked(new CameraXConfig.Provider() { // from class: androidx.camera.core.g
                @Override // androidx.camera.core.CameraXConfig.Provider
                public final CameraXConfig getCameraXConfig() {
                    CameraXConfig lambda$initialize$0;
                    lambda$initialize$0 = CameraX.lambda$initialize$0(CameraXConfig.this);
                    return lambda$initialize$0;
                }
            });
            initializeInstanceLocked(context);
            listenableFuture = sInitializeFuture;
        }
        return listenableFuture;
    }

    @GuardedBy("INSTANCE_LOCK")
    private static void initializeInstanceLocked(@NonNull final Context context) {
        Preconditions.checkNotNull(context);
        Preconditions.checkState(f959c == null, "CameraX already initialized.");
        Preconditions.checkNotNull(sConfigProvider);
        final CameraX cameraX = new CameraX(sConfigProvider.getCameraXConfig());
        f959c = cameraX;
        sInitializeFuture = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.core.l
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                Object lambda$initializeInstanceLocked$3;
                lambda$initializeInstanceLocked$3 = CameraX.lambda$initializeInstanceLocked$3(CameraX.this, context, completer);
                return lambda$initializeInstanceLocked$3;
            }
        });
    }

    @RestrictTo({RestrictTo.Scope.TESTS})
    public static boolean isInitialized() {
        boolean z;
        synchronized (f958b) {
            CameraX cameraX = f959c;
            z = cameraX != null && cameraX.isInitializedInternal();
        }
        return z;
    }

    private boolean isInitializedInternal() {
        boolean z;
        synchronized (this.mInitializeLock) {
            z = this.mInitState == InternalInitState.INITIALIZED;
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ CameraXConfig lambda$configureInstance$1(CameraXConfig cameraXConfig) {
        return cameraXConfig;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ CameraX lambda$getInstanceLocked$6(CameraX cameraX, Void r1) {
        return cameraX;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initAndRetryRecursively$8(Executor executor, long j2, CallbackToFutureAdapter.Completer completer) {
        initAndRetryRecursively(executor, j2, this.mAppContext, completer);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initAndRetryRecursively$9(Context context, final Executor executor, final CallbackToFutureAdapter.Completer completer, final long j2) {
        try {
            Application applicationFromContext = getApplicationFromContext(context);
            this.mAppContext = applicationFromContext;
            if (applicationFromContext == null) {
                this.mAppContext = context.getApplicationContext();
            }
            CameraFactory.Provider cameraFactoryProvider = this.mCameraXConfig.getCameraFactoryProvider(null);
            if (cameraFactoryProvider == null) {
                throw new InitializationException(new IllegalArgumentException("Invalid app configuration provided. Missing CameraFactory."));
            }
            CameraThreadConfig create = CameraThreadConfig.create(this.mCameraExecutor, this.mSchedulerHandler);
            CameraSelector availableCamerasLimiter = this.mCameraXConfig.getAvailableCamerasLimiter(null);
            this.mCameraFactory = cameraFactoryProvider.newInstance(this.mAppContext, create, availableCamerasLimiter);
            CameraDeviceSurfaceManager.Provider deviceSurfaceManagerProvider = this.mCameraXConfig.getDeviceSurfaceManagerProvider(null);
            if (deviceSurfaceManagerProvider == null) {
                throw new InitializationException(new IllegalArgumentException("Invalid app configuration provided. Missing CameraDeviceSurfaceManager."));
            }
            this.mSurfaceManager = deviceSurfaceManagerProvider.newInstance(this.mAppContext, this.mCameraFactory.getCameraManager(), this.mCameraFactory.getAvailableCameraIds());
            UseCaseConfigFactory.Provider useCaseConfigFactoryProvider = this.mCameraXConfig.getUseCaseConfigFactoryProvider(null);
            if (useCaseConfigFactoryProvider == null) {
                throw new InitializationException(new IllegalArgumentException("Invalid app configuration provided. Missing UseCaseConfigFactory."));
            }
            this.mDefaultConfigFactory = useCaseConfigFactoryProvider.newInstance(this.mAppContext);
            if (executor instanceof CameraExecutor) {
                ((CameraExecutor) executor).b(this.mCameraFactory);
            }
            this.f960a.init(this.mCameraFactory);
            if (DeviceQuirks.get(IncompleteCameraListQuirk.class) != null) {
                CameraValidator.validateCameras(this.mAppContext, this.f960a, availableCamerasLimiter);
            }
            setStateToInitialized();
            completer.set(null);
        } catch (InitializationException | CameraValidator.CameraIdListIncorrectException | RuntimeException e2) {
            if (SystemClock.elapsedRealtime() - j2 < 2500) {
                Logger.w(TAG, "Retry init. Start time " + j2 + " current time " + SystemClock.elapsedRealtime(), e2);
                HandlerCompat.postDelayed(this.mSchedulerHandler, new Runnable() { // from class: androidx.camera.core.e
                    @Override // java.lang.Runnable
                    public final void run() {
                        CameraX.this.lambda$initAndRetryRecursively$8(executor, j2, completer);
                    }
                }, RETRY_TOKEN, RETRY_SLEEP_MILLIS);
                return;
            }
            setStateToInitialized();
            if (e2 instanceof CameraValidator.CameraIdListIncorrectException) {
                Logger.e(TAG, "The device might underreport the amount of the cameras. Finish the initialize task since we are already reaching the maximum number of retries.");
                completer.set(null);
            } else if (e2 instanceof InitializationException) {
                completer.setException(e2);
            } else {
                completer.setException(new InitializationException(e2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object lambda$initInternal$7(Context context, CallbackToFutureAdapter.Completer completer) {
        initAndRetryRecursively(this.mCameraExecutor, SystemClock.elapsedRealtime(), context, completer);
        return "CameraX initInternal";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ CameraXConfig lambda$initialize$0(CameraXConfig cameraXConfig) {
        return cameraXConfig;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Object lambda$initializeInstanceLocked$3(final CameraX cameraX, final Context context, final CallbackToFutureAdapter.Completer completer) {
        synchronized (f958b) {
            Futures.addCallback(FutureChain.from(sShutdownFuture).transformAsync(new AsyncFunction() { // from class: androidx.camera.core.h
                @Override // androidx.camera.core.impl.utils.futures.AsyncFunction
                public final ListenableFuture apply(Object obj) {
                    ListenableFuture initInternal;
                    Void r3 = (Void) obj;
                    initInternal = CameraX.this.initInternal(context);
                    return initInternal;
                }
            }, CameraXExecutors.directExecutor()), new FutureCallback<Void>() { // from class: androidx.camera.core.CameraX.1
                @Override // androidx.camera.core.impl.utils.futures.FutureCallback
                public void onFailure(Throwable th) {
                    Logger.w(CameraX.TAG, "CameraX initialize() failed", th);
                    synchronized (CameraX.f958b) {
                        if (CameraX.f959c == cameraX) {
                            CameraX.m();
                        }
                    }
                    CallbackToFutureAdapter.Completer.this.setException(th);
                }

                @Override // androidx.camera.core.impl.utils.futures.FutureCallback
                public void onSuccess(@Nullable Void r2) {
                    CallbackToFutureAdapter.Completer.this.set(null);
                }
            }, CameraXExecutors.directExecutor());
        }
        return "CameraX-initialize";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$shutdownInternal$10(CallbackToFutureAdapter.Completer completer) {
        if (this.mSchedulerThread != null) {
            Executor executor = this.mCameraExecutor;
            if (executor instanceof CameraExecutor) {
                ((CameraExecutor) executor).a();
            }
            this.mSchedulerThread.quit();
            completer.set(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object lambda$shutdownInternal$11(final CallbackToFutureAdapter.Completer completer) {
        this.f960a.deinit().addListener(new Runnable() { // from class: androidx.camera.core.n
            @Override // java.lang.Runnable
            public final void run() {
                CameraX.this.lambda$shutdownInternal$10(completer);
            }
        }, this.mCameraExecutor);
        return "CameraX shutdownInternal";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$shutdownLocked$4(CameraX cameraX, CallbackToFutureAdapter.Completer completer) {
        Futures.propagate(cameraX.shutdownInternal(), completer);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Object lambda$shutdownLocked$5(final CameraX cameraX, final CallbackToFutureAdapter.Completer completer) {
        synchronized (f958b) {
            sInitializeFuture.addListener(new Runnable() { // from class: androidx.camera.core.d
                @Override // java.lang.Runnable
                public final void run() {
                    CameraX.lambda$shutdownLocked$4(CameraX.this, completer);
                }
            }, CameraXExecutors.directExecutor());
        }
        return "CameraX shutdown";
    }

    @NonNull
    @GuardedBy("INSTANCE_LOCK")
    static ListenableFuture m() {
        final CameraX cameraX = f959c;
        if (cameraX == null) {
            return sShutdownFuture;
        }
        f959c = null;
        ListenableFuture<Void> future = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.core.i
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                Object lambda$shutdownLocked$5;
                lambda$shutdownLocked$5 = CameraX.lambda$shutdownLocked$5(CameraX.this, completer);
                return lambda$shutdownLocked$5;
            }
        });
        sShutdownFuture = future;
        return future;
    }

    private void setStateToInitialized() {
        synchronized (this.mInitializeLock) {
            this.mInitState = InternalInitState.INITIALIZED;
        }
    }

    @NonNull
    public static ListenableFuture<Void> shutdown() {
        ListenableFuture<Void> m2;
        synchronized (f958b) {
            sConfigProvider = null;
            Logger.a();
            m2 = m();
        }
        return m2;
    }

    @NonNull
    private ListenableFuture<Void> shutdownInternal() {
        synchronized (this.mInitializeLock) {
            this.mSchedulerHandler.removeCallbacksAndMessages(RETRY_TOKEN);
            int i2 = AnonymousClass2.f963a[this.mInitState.ordinal()];
            if (i2 == 1) {
                this.mInitState = InternalInitState.SHUTDOWN;
                return Futures.immediateFuture(null);
            } else if (i2 != 2) {
                if (i2 == 3) {
                    this.mInitState = InternalInitState.SHUTDOWN;
                    this.mShutdownInternalFuture = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.core.j
                        @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
                        public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                            Object lambda$shutdownInternal$11;
                            lambda$shutdownInternal$11 = CameraX.this.lambda$shutdownInternal$11(completer);
                            return lambda$shutdownInternal$11;
                        }
                    });
                }
                return this.mShutdownInternalFuture;
            } else {
                throw new IllegalStateException("CameraX could not be shutdown when it is initializing.");
            }
        }
    }

    @NonNull
    private static CameraX waitInitialized() {
        try {
            return getInstance().get(WAIT_INITIALIZED_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e2) {
            throw new IllegalStateException(e2);
        }
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public CameraDeviceSurfaceManager getCameraDeviceSurfaceManager() {
        CameraDeviceSurfaceManager cameraDeviceSurfaceManager = this.mSurfaceManager;
        if (cameraDeviceSurfaceManager != null) {
            return cameraDeviceSurfaceManager;
        }
        throw new IllegalStateException("CameraX not initialized yet.");
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public CameraFactory getCameraFactory() {
        CameraFactory cameraFactory = this.mCameraFactory;
        if (cameraFactory != null) {
            return cameraFactory;
        }
        throw new IllegalStateException("CameraX not initialized yet.");
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public CameraRepository getCameraRepository() {
        return this.f960a;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public UseCaseConfigFactory getDefaultConfigFactory() {
        UseCaseConfigFactory useCaseConfigFactory = this.mDefaultConfigFactory;
        if (useCaseConfigFactory != null) {
            return useCaseConfigFactory;
        }
        throw new IllegalStateException("CameraX not initialized yet.");
    }
}
