package androidx.camera.camera2.internal;

import android.annotation.SuppressLint;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Rational;
import android.util.Size;
import android.view.Surface;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.camera2.internal.Camera2CameraImpl;
import androidx.camera.camera2.internal.SynchronizedCaptureSessionOpener;
import androidx.camera.camera2.internal.compat.CameraAccessExceptionCompat;
import androidx.camera.camera2.internal.compat.CameraManagerCompat;
import androidx.camera.core.Logger;
import androidx.camera.core.Preview;
import androidx.camera.core.UseCase;
import androidx.camera.core.impl.CameraControlInternal;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.CameraStateRegistry;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.ImmediateSurface;
import androidx.camera.core.impl.LiveDataObservable;
import androidx.camera.core.impl.Observable;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.UseCaseAttachState;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class Camera2CameraImpl implements CameraInternal {
    private static final int ERROR_NONE = 0;
    private static final String TAG = "Camera2CameraImpl";

    /* renamed from: a  reason: collision with root package name */
    volatile InternalState f663a = InternalState.INITIALIZED;
    @NonNull

    /* renamed from: b  reason: collision with root package name */
    final Camera2CameraInfoImpl f664b;
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    CameraDevice f665c;

    /* renamed from: d  reason: collision with root package name */
    int f666d;

    /* renamed from: e  reason: collision with root package name */
    CaptureSession f667e;

    /* renamed from: f  reason: collision with root package name */
    SessionConfig f668f;

    /* renamed from: g  reason: collision with root package name */
    final AtomicInteger f669g;

    /* renamed from: h  reason: collision with root package name */
    ListenableFuture f670h;

    /* renamed from: i  reason: collision with root package name */
    CallbackToFutureAdapter.Completer f671i;

    /* renamed from: j  reason: collision with root package name */
    final Map f672j;

    /* renamed from: k  reason: collision with root package name */
    final Set f673k;
    private final CameraAvailability mCameraAvailability;
    private final Camera2CameraControlImpl mCameraControlInternal;
    private final CameraManagerCompat mCameraManager;
    private final CameraStateRegistry mCameraStateRegistry;
    @NonNull
    private final SynchronizedCaptureSessionOpener.Builder mCaptureSessionOpenerBuilder;
    @NonNull
    private final CaptureSessionRepository mCaptureSessionRepository;
    private final Executor mExecutor;
    private MeteringRepeatingSession mMeteringRepeatingSession;
    private final Set<String> mNotifyStateAttachedSet;
    private final LiveDataObservable<CameraInternal.State> mObservableState;
    private final StateCallback mStateCallback;
    private final UseCaseAttachState mUseCaseAttachState;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: androidx.camera.camera2.internal.Camera2CameraImpl$3  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass3 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f677a;

        static {
            int[] iArr = new int[InternalState.values().length];
            f677a = iArr;
            try {
                iArr[InternalState.INITIALIZED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f677a[InternalState.CLOSING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f677a[InternalState.OPENED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f677a[InternalState.OPENING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f677a[InternalState.REOPENING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f677a[InternalState.PENDING_OPEN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f677a[InternalState.RELEASING.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f677a[InternalState.RELEASED.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public final class CameraAvailability extends CameraManager.AvailabilityCallback implements CameraStateRegistry.OnOpenAvailableListener {
        private boolean mCameraAvailable = true;
        private final String mCameraId;

        CameraAvailability(String str) {
            this.mCameraId = str;
        }

        boolean a() {
            return this.mCameraAvailable;
        }

        @Override // android.hardware.camera2.CameraManager.AvailabilityCallback
        public void onCameraAvailable(@NonNull String str) {
            if (this.mCameraId.equals(str)) {
                this.mCameraAvailable = true;
                if (Camera2CameraImpl.this.f663a == InternalState.PENDING_OPEN) {
                    Camera2CameraImpl.this.v(false);
                }
            }
        }

        @Override // android.hardware.camera2.CameraManager.AvailabilityCallback
        public void onCameraUnavailable(@NonNull String str) {
            if (this.mCameraId.equals(str)) {
                this.mCameraAvailable = false;
            }
        }

        @Override // androidx.camera.core.impl.CameraStateRegistry.OnOpenAvailableListener
        public void onOpenAvailable() {
            if (Camera2CameraImpl.this.f663a == InternalState.PENDING_OPEN) {
                Camera2CameraImpl.this.v(false);
            }
        }
    }

    /* loaded from: classes.dex */
    final class ControlUpdateListenerInternal implements CameraControlInternal.ControlUpdateCallback {
        ControlUpdateListenerInternal() {
        }

        @Override // androidx.camera.core.impl.CameraControlInternal.ControlUpdateCallback
        public void onCameraControlCaptureRequests(@NonNull List<CaptureConfig> list) {
            Camera2CameraImpl.this.C((List) Preconditions.checkNotNull(list));
        }

        @Override // androidx.camera.core.impl.CameraControlInternal.ControlUpdateCallback
        public void onCameraControlUpdateSessionConfig(@NonNull SessionConfig sessionConfig) {
            Camera2CameraImpl.this.f668f = (SessionConfig) Preconditions.checkNotNull(sessionConfig);
            Camera2CameraImpl.this.D();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public enum InternalState {
        INITIALIZED,
        PENDING_OPEN,
        OPENING,
        OPENED,
        CLOSING,
        REOPENING,
        RELEASING,
        RELEASED
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public final class StateCallback extends CameraDevice.StateCallback {

        /* renamed from: a  reason: collision with root package name */
        ScheduledFuture f680a;
        @NonNull
        private final CameraReopenMonitor mCameraReopenMonitor = new CameraReopenMonitor(this);
        private final Executor mExecutor;
        private ScheduledReopen mScheduledReopenRunnable;
        private final ScheduledExecutorService mScheduler;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class CameraReopenMonitor {
            private long mFirstReopenTime = -1;

            CameraReopenMonitor(StateCallback stateCallback) {
            }

            boolean a() {
                long uptimeMillis = SystemClock.uptimeMillis();
                long j2 = this.mFirstReopenTime;
                if (j2 == -1) {
                    this.mFirstReopenTime = uptimeMillis;
                    return true;
                }
                if (uptimeMillis - j2 >= 10000) {
                    b();
                    return false;
                }
                return true;
            }

            void b() {
                this.mFirstReopenTime = -1L;
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class ScheduledReopen implements Runnable {
            private boolean mCancelled = false;
            private Executor mExecutor;

            ScheduledReopen(@NonNull Executor executor) {
                this.mExecutor = executor;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public /* synthetic */ void lambda$run$0() {
                if (this.mCancelled) {
                    return;
                }
                Preconditions.checkState(Camera2CameraImpl.this.f663a == InternalState.REOPENING);
                Camera2CameraImpl.this.v(true);
            }

            void b() {
                this.mCancelled = true;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.f0
                    @Override // java.lang.Runnable
                    public final void run() {
                        Camera2CameraImpl.StateCallback.ScheduledReopen.this.lambda$run$0();
                    }
                });
            }
        }

        StateCallback(@NonNull Executor executor, @NonNull ScheduledExecutorService scheduledExecutorService) {
            this.mExecutor = executor;
            this.mScheduler = scheduledExecutorService;
        }

        private void handleErrorOnOpen(@NonNull CameraDevice cameraDevice, int i2) {
            boolean z = Camera2CameraImpl.this.f663a == InternalState.OPENING || Camera2CameraImpl.this.f663a == InternalState.OPENED || Camera2CameraImpl.this.f663a == InternalState.REOPENING;
            Preconditions.checkState(z, "Attempt to handle open error from non open state: " + Camera2CameraImpl.this.f663a);
            if (i2 == 1 || i2 == 2 || i2 == 4) {
                Logger.d(Camera2CameraImpl.TAG, String.format("Attempt to reopen camera[%s] after error[%s]", cameraDevice.getId(), Camera2CameraImpl.t(i2)));
                reopenCameraAfterError();
                return;
            }
            Logger.e(Camera2CameraImpl.TAG, "Error observed on open (or opening) camera device " + cameraDevice.getId() + ": " + Camera2CameraImpl.t(i2) + " closing camera.");
            Camera2CameraImpl.this.B(InternalState.CLOSING);
            Camera2CameraImpl.this.p(false);
        }

        private void reopenCameraAfterError() {
            Preconditions.checkState(Camera2CameraImpl.this.f666d != 0, "Can only reopen camera device after error if the camera device is actually in an error state.");
            Camera2CameraImpl.this.B(InternalState.REOPENING);
            Camera2CameraImpl.this.p(false);
        }

        boolean a() {
            if (this.f680a != null) {
                Camera2CameraImpl camera2CameraImpl = Camera2CameraImpl.this;
                camera2CameraImpl.q("Cancelling scheduled re-open: " + this.mScheduledReopenRunnable);
                this.mScheduledReopenRunnable.b();
                this.mScheduledReopenRunnable = null;
                this.f680a.cancel(false);
                this.f680a = null;
                return true;
            }
            return false;
        }

        void b() {
            this.mCameraReopenMonitor.b();
        }

        void c() {
            Preconditions.checkState(this.mScheduledReopenRunnable == null);
            Preconditions.checkState(this.f680a == null);
            if (!this.mCameraReopenMonitor.a()) {
                Logger.e(Camera2CameraImpl.TAG, "Camera reopening attempted for 10000ms without success.");
                Camera2CameraImpl.this.B(InternalState.INITIALIZED);
                return;
            }
            this.mScheduledReopenRunnable = new ScheduledReopen(this.mExecutor);
            Camera2CameraImpl camera2CameraImpl = Camera2CameraImpl.this;
            camera2CameraImpl.q("Attempting camera re-open in 700ms: " + this.mScheduledReopenRunnable);
            this.f680a = this.mScheduler.schedule(this.mScheduledReopenRunnable, 700L, TimeUnit.MILLISECONDS);
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onClosed(@NonNull CameraDevice cameraDevice) {
            Camera2CameraImpl.this.q("CameraDevice.onClosed()");
            boolean z = Camera2CameraImpl.this.f665c == null;
            Preconditions.checkState(z, "Unexpected onClose callback on camera device: " + cameraDevice);
            int i2 = AnonymousClass3.f677a[Camera2CameraImpl.this.f663a.ordinal()];
            if (i2 != 2) {
                if (i2 == 5) {
                    Camera2CameraImpl camera2CameraImpl = Camera2CameraImpl.this;
                    if (camera2CameraImpl.f666d == 0) {
                        camera2CameraImpl.v(false);
                        return;
                    }
                    camera2CameraImpl.q("Camera closed due to error: " + Camera2CameraImpl.t(Camera2CameraImpl.this.f666d));
                    c();
                    return;
                } else if (i2 != 7) {
                    throw new IllegalStateException("Camera closed while in state: " + Camera2CameraImpl.this.f663a);
                }
            }
            Preconditions.checkState(Camera2CameraImpl.this.u());
            Camera2CameraImpl.this.s();
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onDisconnected(@NonNull CameraDevice cameraDevice) {
            Camera2CameraImpl.this.q("CameraDevice.onDisconnected()");
            onError(cameraDevice, 1);
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onError(@NonNull CameraDevice cameraDevice, int i2) {
            Camera2CameraImpl camera2CameraImpl = Camera2CameraImpl.this;
            camera2CameraImpl.f665c = cameraDevice;
            camera2CameraImpl.f666d = i2;
            int i3 = AnonymousClass3.f677a[camera2CameraImpl.f663a.ordinal()];
            if (i3 != 2) {
                if (i3 == 3 || i3 == 4 || i3 == 5) {
                    Logger.d(Camera2CameraImpl.TAG, String.format("CameraDevice.onError(): %s failed with %s while in %s state. Will attempt recovering from error.", cameraDevice.getId(), Camera2CameraImpl.t(i2), Camera2CameraImpl.this.f663a.name()));
                    handleErrorOnOpen(cameraDevice, i2);
                    return;
                } else if (i3 != 7) {
                    throw new IllegalStateException("onError() should not be possible from state: " + Camera2CameraImpl.this.f663a);
                }
            }
            Logger.e(Camera2CameraImpl.TAG, String.format("CameraDevice.onError(): %s failed with %s while in %s state. Will finish closing camera.", cameraDevice.getId(), Camera2CameraImpl.t(i2), Camera2CameraImpl.this.f663a.name()));
            Camera2CameraImpl.this.p(false);
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onOpened(@NonNull CameraDevice cameraDevice) {
            Camera2CameraImpl.this.q("CameraDevice.onOpened()");
            Camera2CameraImpl camera2CameraImpl = Camera2CameraImpl.this;
            camera2CameraImpl.f665c = cameraDevice;
            camera2CameraImpl.E(cameraDevice);
            Camera2CameraImpl camera2CameraImpl2 = Camera2CameraImpl.this;
            camera2CameraImpl2.f666d = 0;
            int i2 = AnonymousClass3.f677a[camera2CameraImpl2.f663a.ordinal()];
            if (i2 == 2 || i2 == 7) {
                Preconditions.checkState(Camera2CameraImpl.this.u());
                Camera2CameraImpl.this.f665c.close();
                Camera2CameraImpl.this.f665c = null;
            } else if (i2 == 4 || i2 == 5) {
                Camera2CameraImpl.this.B(InternalState.OPENED);
                Camera2CameraImpl.this.w();
            } else {
                throw new IllegalStateException("onOpened() should not be possible from state: " + Camera2CameraImpl.this.f663a);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Camera2CameraImpl(@NonNull CameraManagerCompat cameraManagerCompat, @NonNull String str, @NonNull Camera2CameraInfoImpl camera2CameraInfoImpl, @NonNull CameraStateRegistry cameraStateRegistry, @NonNull Executor executor, @NonNull Handler handler) {
        LiveDataObservable<CameraInternal.State> liveDataObservable = new LiveDataObservable<>();
        this.mObservableState = liveDataObservable;
        this.f666d = 0;
        this.f668f = SessionConfig.defaultEmptySessionConfig();
        this.f669g = new AtomicInteger(0);
        this.f672j = new LinkedHashMap();
        this.f673k = new HashSet();
        this.mNotifyStateAttachedSet = new HashSet();
        this.mCameraManager = cameraManagerCompat;
        this.mCameraStateRegistry = cameraStateRegistry;
        ScheduledExecutorService newHandlerExecutor = CameraXExecutors.newHandlerExecutor(handler);
        Executor newSequentialExecutor = CameraXExecutors.newSequentialExecutor(executor);
        this.mExecutor = newSequentialExecutor;
        this.mStateCallback = new StateCallback(newSequentialExecutor, newHandlerExecutor);
        this.mUseCaseAttachState = new UseCaseAttachState(str);
        liveDataObservable.postValue(CameraInternal.State.CLOSED);
        CaptureSessionRepository captureSessionRepository = new CaptureSessionRepository(newSequentialExecutor);
        this.mCaptureSessionRepository = captureSessionRepository;
        this.f667e = new CaptureSession();
        try {
            Camera2CameraControlImpl camera2CameraControlImpl = new Camera2CameraControlImpl(cameraManagerCompat.getCameraCharacteristicsCompat(str), newHandlerExecutor, newSequentialExecutor, new ControlUpdateListenerInternal(), camera2CameraInfoImpl.getCameraQuirks());
            this.mCameraControlInternal = camera2CameraControlImpl;
            this.f664b = camera2CameraInfoImpl;
            camera2CameraInfoImpl.c(camera2CameraControlImpl);
            this.mCaptureSessionOpenerBuilder = new SynchronizedCaptureSessionOpener.Builder(newSequentialExecutor, newHandlerExecutor, handler, captureSessionRepository, camera2CameraInfoImpl.b());
            CameraAvailability cameraAvailability = new CameraAvailability(str);
            this.mCameraAvailability = cameraAvailability;
            cameraStateRegistry.registerCamera(this, newSequentialExecutor, cameraAvailability);
            cameraManagerCompat.registerAvailabilityCallback(newSequentialExecutor, cameraAvailability);
        } catch (CameraAccessExceptionCompat e2) {
            throw CameraUnavailableExceptionHelper.createFrom(e2);
        }
    }

    private void addMeteringRepeating() {
        if (this.mMeteringRepeatingSession != null) {
            UseCaseAttachState useCaseAttachState = this.mUseCaseAttachState;
            useCaseAttachState.setUseCaseAttached(this.mMeteringRepeatingSession.c() + this.mMeteringRepeatingSession.hashCode(), this.mMeteringRepeatingSession.d());
            UseCaseAttachState useCaseAttachState2 = this.mUseCaseAttachState;
            useCaseAttachState2.setUseCaseActive(this.mMeteringRepeatingSession.c() + this.mMeteringRepeatingSession.hashCode(), this.mMeteringRepeatingSession.d());
        }
    }

    private void addOrRemoveMeteringRepeatingUseCase() {
        SessionConfig build = this.mUseCaseAttachState.getAttachedBuilder().build();
        CaptureConfig repeatingCaptureConfig = build.getRepeatingCaptureConfig();
        int size = repeatingCaptureConfig.getSurfaces().size();
        int size2 = build.getSurfaces().size();
        if (build.getSurfaces().isEmpty()) {
            return;
        }
        if (repeatingCaptureConfig.getSurfaces().isEmpty()) {
            if (this.mMeteringRepeatingSession == null) {
                this.mMeteringRepeatingSession = new MeteringRepeatingSession(this.f664b.getCameraCharacteristicsCompat());
            }
            addMeteringRepeating();
        } else if ((size2 == 1 && size == 1) || size >= 2) {
            removeMeteringRepeating();
        } else {
            Logger.d(TAG, "mMeteringRepeating is ATTACHED, SessionConfig Surfaces: " + size2 + ", CaptureConfig Surfaces: " + size);
        }
    }

    private boolean checkAndAttachRepeatingSurface(CaptureConfig.Builder builder) {
        String str;
        if (builder.getSurfaces().isEmpty()) {
            for (SessionConfig sessionConfig : this.mUseCaseAttachState.getActiveAndAttachedSessionConfigs()) {
                List<DeferrableSurface> surfaces = sessionConfig.getRepeatingCaptureConfig().getSurfaces();
                if (!surfaces.isEmpty()) {
                    for (DeferrableSurface deferrableSurface : surfaces) {
                        builder.addSurface(deferrableSurface);
                    }
                }
            }
            if (!builder.getSurfaces().isEmpty()) {
                return true;
            }
            str = "Unable to find a repeating surface to attach to CaptureConfig";
        } else {
            str = "The capture config builder already has surface inside.";
        }
        Logger.w(TAG, str);
        return false;
    }

    private void clearCameraControlPreviewAspectRatio(Collection<UseCase> collection) {
        for (UseCase useCase : collection) {
            if (useCase instanceof Preview) {
                this.mCameraControlInternal.setPreviewAspectRatio(null);
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeInternal() {
        q("Closing camera.");
        int i2 = AnonymousClass3.f677a[this.f663a.ordinal()];
        if (i2 == 3) {
            B(InternalState.CLOSING);
            p(false);
        } else if (i2 == 4 || i2 == 5) {
            boolean a2 = this.mStateCallback.a();
            B(InternalState.CLOSING);
            if (a2) {
                Preconditions.checkState(u());
                s();
            }
        } else if (i2 == 6) {
            Preconditions.checkState(this.f665c == null);
            B(InternalState.INITIALIZED);
        } else {
            q("close() ignored due to being in state: " + this.f663a);
        }
    }

    private void configAndClose(boolean z) {
        final CaptureSession captureSession = new CaptureSession();
        this.f673k.add(captureSession);
        A(z);
        final SurfaceTexture surfaceTexture = new SurfaceTexture(0);
        surfaceTexture.setDefaultBufferSize(640, 480);
        final Surface surface = new Surface(surfaceTexture);
        final Runnable runnable = new Runnable() { // from class: androidx.camera.camera2.internal.x
            @Override // java.lang.Runnable
            public final void run() {
                Camera2CameraImpl.lambda$configAndClose$0(surface, surfaceTexture);
            }
        };
        SessionConfig.Builder builder = new SessionConfig.Builder();
        final ImmediateSurface immediateSurface = new ImmediateSurface(surface);
        builder.addNonRepeatingSurface(immediateSurface);
        builder.setTemplateType(1);
        q("Start configAndClose.");
        captureSession.m(builder.build(), (CameraDevice) Preconditions.checkNotNull(this.f665c), this.mCaptureSessionOpenerBuilder.a()).addListener(new Runnable() { // from class: androidx.camera.camera2.internal.a0
            @Override // java.lang.Runnable
            public final void run() {
                Camera2CameraImpl.this.lambda$configAndClose$1(captureSession, immediateSurface, runnable);
            }
        }, this.mExecutor);
    }

    private CameraDevice.StateCallback createDeviceStateCallback() {
        ArrayList arrayList = new ArrayList(this.mUseCaseAttachState.getAttachedBuilder().build().getDeviceStateCallbacks());
        arrayList.add(this.mCaptureSessionRepository.b());
        arrayList.add(this.mStateCallback);
        return CameraDeviceStateCallbacks.createComboCallback(arrayList);
    }

    private void debugLog(@NonNull String str, @Nullable Throwable th) {
        Logger.d(TAG, String.format("{%s} %s", toString(), str), th);
    }

    private ListenableFuture<Void> getOrCreateUserReleaseFuture() {
        if (this.f670h == null) {
            this.f670h = this.f663a != InternalState.RELEASED ? CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.camera2.internal.q
                @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
                public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                    Object lambda$getOrCreateUserReleaseFuture$4;
                    lambda$getOrCreateUserReleaseFuture$4 = Camera2CameraImpl.this.lambda$getOrCreateUserReleaseFuture$4(completer);
                    return lambda$getOrCreateUserReleaseFuture$4;
                }
            }) : Futures.immediateFuture(null);
        }
        return this.f670h;
    }

    private boolean isLegacyDevice() {
        return ((Camera2CameraInfoImpl) getCameraInfoInternal()).b() == 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$attachUseCases$11(Collection collection) {
        try {
            tryAttachUseCases(collection);
        } finally {
            this.mCameraControlInternal.n();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$configAndClose$0(Surface surface, SurfaceTexture surfaceTexture) {
        surface.release();
        surfaceTexture.release();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object lambda$getOrCreateUserReleaseFuture$4(CallbackToFutureAdapter.Completer completer) {
        Preconditions.checkState(this.f671i == null, "Camera can only be released once, so release completer should be null on creation.");
        this.f671i = completer;
        return "Release[camera=" + this + "]";
    }

    private /* synthetic */ Object lambda$isUseCaseAttached$10(final UseCase useCase, final CallbackToFutureAdapter.Completer completer) {
        try {
            this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.s
                @Override // java.lang.Runnable
                public final void run() {
                    Camera2CameraImpl.this.lambda$isUseCaseAttached$9(completer, useCase);
                }
            });
            return "isUseCaseAttached";
        } catch (RejectedExecutionException unused) {
            completer.setException(new RuntimeException("Unable to check if use case is attached. Camera executor shut down."));
            return "isUseCaseAttached";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$isUseCaseAttached$9(CallbackToFutureAdapter.Completer completer, UseCase useCase) {
        UseCaseAttachState useCaseAttachState = this.mUseCaseAttachState;
        completer.set(Boolean.valueOf(useCaseAttachState.isUseCaseAttached(useCase.getName() + useCase.hashCode())));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onUseCaseActive$5(UseCase useCase) {
        q("Use case " + useCase + " ACTIVE");
        try {
            UseCaseAttachState useCaseAttachState = this.mUseCaseAttachState;
            useCaseAttachState.setUseCaseActive(useCase.getName() + useCase.hashCode(), useCase.getSessionConfig());
            UseCaseAttachState useCaseAttachState2 = this.mUseCaseAttachState;
            useCaseAttachState2.updateUseCase(useCase.getName() + useCase.hashCode(), useCase.getSessionConfig());
            D();
        } catch (NullPointerException unused) {
            q("Failed to set already detached use case active");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onUseCaseInactive$6(UseCase useCase) {
        q("Use case " + useCase + " INACTIVE");
        UseCaseAttachState useCaseAttachState = this.mUseCaseAttachState;
        useCaseAttachState.setUseCaseInactive(useCase.getName() + useCase.hashCode());
        D();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onUseCaseReset$8(UseCase useCase) {
        q("Use case " + useCase + " RESET");
        UseCaseAttachState useCaseAttachState = this.mUseCaseAttachState;
        useCaseAttachState.updateUseCase(useCase.getName() + useCase.hashCode(), useCase.getSessionConfig());
        A(false);
        D();
        if (this.f663a == InternalState.OPENED) {
            w();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onUseCaseUpdated$7(UseCase useCase) {
        q("Use case " + useCase + " UPDATED");
        UseCaseAttachState useCaseAttachState = this.mUseCaseAttachState;
        useCaseAttachState.updateUseCase(useCase.getName() + useCase.hashCode(), useCase.getSessionConfig());
        D();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$postSurfaceClosedError$13(SessionConfig.ErrorListener errorListener, SessionConfig sessionConfig) {
        errorListener.onError(sessionConfig, SessionConfig.SessionError.SESSION_ERROR_SURFACE_NEEDS_RESET);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$release$2(CallbackToFutureAdapter.Completer completer) {
        Futures.propagate(releaseInternal(), completer);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object lambda$release$3(final CallbackToFutureAdapter.Completer completer) {
        this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.r
            @Override // java.lang.Runnable
            public final void run() {
                Camera2CameraImpl.this.lambda$release$2(completer);
            }
        });
        return "Release[request=" + this.f669g.getAndIncrement() + "]";
    }

    private void notifyStateAttachedToUseCases(List<UseCase> list) {
        for (UseCase useCase : list) {
            Set<String> set = this.mNotifyStateAttachedSet;
            if (!set.contains(useCase.getName() + useCase.hashCode())) {
                Set<String> set2 = this.mNotifyStateAttachedSet;
                set2.add(useCase.getName() + useCase.hashCode());
                useCase.onStateAttached();
            }
        }
    }

    private void notifyStateDetachedToUseCases(List<UseCase> list) {
        for (UseCase useCase : list) {
            Set<String> set = this.mNotifyStateAttachedSet;
            if (set.contains(useCase.getName() + useCase.hashCode())) {
                useCase.onStateDetached();
                Set<String> set2 = this.mNotifyStateAttachedSet;
                set2.remove(useCase.getName() + useCase.hashCode());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openInternal() {
        int i2 = AnonymousClass3.f677a[this.f663a.ordinal()];
        if (i2 == 1) {
            v(false);
        } else if (i2 != 2) {
            q("open() ignored due to being in state: " + this.f663a);
        } else {
            B(InternalState.REOPENING);
            if (u() || this.f666d != 0) {
                return;
            }
            Preconditions.checkState(this.f665c != null, "Camera Device should be open if session close is not complete");
            B(InternalState.OPENED);
            w();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x003d, code lost:
        if (r1 != false) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private ListenableFuture<Void> releaseInternal() {
        ListenableFuture<Void> orCreateUserReleaseFuture = getOrCreateUserReleaseFuture();
        switch (AnonymousClass3.f677a[this.f663a.ordinal()]) {
            case 1:
            case 6:
                Preconditions.checkState(this.f665c == null);
                B(InternalState.RELEASING);
                Preconditions.checkState(u());
                s();
                break;
            case 2:
            case 4:
            case 5:
            case 7:
                boolean a2 = this.mStateCallback.a();
                B(InternalState.RELEASING);
                break;
            case 3:
                B(InternalState.RELEASING);
                p(false);
                break;
            default:
                q("release() ignored due to being in state: " + this.f663a);
                break;
        }
        return orCreateUserReleaseFuture;
    }

    private void removeMeteringRepeating() {
        if (this.mMeteringRepeatingSession != null) {
            UseCaseAttachState useCaseAttachState = this.mUseCaseAttachState;
            useCaseAttachState.setUseCaseDetached(this.mMeteringRepeatingSession.c() + this.mMeteringRepeatingSession.hashCode());
            UseCaseAttachState useCaseAttachState2 = this.mUseCaseAttachState;
            useCaseAttachState2.setUseCaseInactive(this.mMeteringRepeatingSession.c() + this.mMeteringRepeatingSession.hashCode());
            this.mMeteringRepeatingSession.b();
            this.mMeteringRepeatingSession = null;
        }
    }

    static String t(int i2) {
        return i2 != 0 ? i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? i2 != 5 ? "UNKNOWN ERROR" : "ERROR_CAMERA_SERVICE" : "ERROR_CAMERA_DEVICE" : "ERROR_CAMERA_DISABLED" : "ERROR_MAX_CAMERAS_IN_USE" : "ERROR_CAMERA_IN_USE" : "ERROR_NONE";
    }

    private void tryAttachUseCases(@NonNull Collection<UseCase> collection) {
        boolean isEmpty = this.mUseCaseAttachState.getAttachedSessionConfigs().isEmpty();
        ArrayList arrayList = new ArrayList();
        for (UseCase useCase : collection) {
            UseCaseAttachState useCaseAttachState = this.mUseCaseAttachState;
            if (!useCaseAttachState.isUseCaseAttached(useCase.getName() + useCase.hashCode())) {
                try {
                    UseCaseAttachState useCaseAttachState2 = this.mUseCaseAttachState;
                    useCaseAttachState2.setUseCaseAttached(useCase.getName() + useCase.hashCode(), useCase.getSessionConfig());
                    arrayList.add(useCase);
                } catch (NullPointerException unused) {
                    q("Failed to attach a detached use case");
                }
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        q("Use cases [" + TextUtils.join(", ", arrayList) + "] now ATTACHED");
        if (isEmpty) {
            this.mCameraControlInternal.A(true);
            this.mCameraControlInternal.x();
        }
        addOrRemoveMeteringRepeatingUseCase();
        D();
        A(false);
        if (this.f663a == InternalState.OPENED) {
            w();
        } else {
            openInternal();
        }
        updateCameraControlPreviewAspectRatio(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: tryDetachUseCases */
    public void lambda$detachUseCases$12(@NonNull Collection<UseCase> collection) {
        ArrayList arrayList = new ArrayList();
        for (UseCase useCase : collection) {
            UseCaseAttachState useCaseAttachState = this.mUseCaseAttachState;
            if (useCaseAttachState.isUseCaseAttached(useCase.getName() + useCase.hashCode())) {
                UseCaseAttachState useCaseAttachState2 = this.mUseCaseAttachState;
                useCaseAttachState2.removeUseCase(useCase.getName() + useCase.hashCode());
                arrayList.add(useCase);
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        q("Use cases [" + TextUtils.join(", ", arrayList) + "] now DETACHED for camera");
        clearCameraControlPreviewAspectRatio(arrayList);
        addOrRemoveMeteringRepeatingUseCase();
        if (this.mUseCaseAttachState.getAttachedSessionConfigs().isEmpty()) {
            this.mCameraControlInternal.n();
            A(false);
            this.mCameraControlInternal.A(false);
            this.f667e = new CaptureSession();
            closeInternal();
            return;
        }
        D();
        A(false);
        if (this.f663a == InternalState.OPENED) {
            w();
        }
    }

    private void updateCameraControlPreviewAspectRatio(Collection<UseCase> collection) {
        for (UseCase useCase : collection) {
            if (useCase instanceof Preview) {
                Size attachedSurfaceResolution = useCase.getAttachedSurfaceResolution();
                if (attachedSurfaceResolution != null) {
                    this.mCameraControlInternal.setPreviewAspectRatio(new Rational(attachedSurfaceResolution.getWidth(), attachedSurfaceResolution.getHeight()));
                    return;
                }
                return;
            }
        }
    }

    void A(boolean z) {
        Preconditions.checkState(this.f667e != null);
        q("Resetting Capture Session");
        CaptureSession captureSession = this.f667e;
        SessionConfig h2 = captureSession.h();
        List g2 = captureSession.g();
        CaptureSession captureSession2 = new CaptureSession();
        this.f667e = captureSession2;
        captureSession2.o(h2);
        this.f667e.j(g2);
        z(captureSession, z);
    }

    void B(@NonNull InternalState internalState) {
        CameraInternal.State state;
        q("Transitioning camera internal state: " + this.f663a + " --> " + internalState);
        this.f663a = internalState;
        switch (AnonymousClass3.f677a[internalState.ordinal()]) {
            case 1:
                state = CameraInternal.State.CLOSED;
                break;
            case 2:
                state = CameraInternal.State.CLOSING;
                break;
            case 3:
                state = CameraInternal.State.OPEN;
                break;
            case 4:
            case 5:
                state = CameraInternal.State.OPENING;
                break;
            case 6:
                state = CameraInternal.State.PENDING_OPEN;
                break;
            case 7:
                state = CameraInternal.State.RELEASING;
                break;
            case 8:
                state = CameraInternal.State.RELEASED;
                break;
            default:
                throw new IllegalStateException("Unknown state: " + internalState);
        }
        this.mCameraStateRegistry.markCameraState(this, state);
        this.mObservableState.postValue(state);
    }

    void C(@NonNull List list) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            CaptureConfig captureConfig = (CaptureConfig) it.next();
            CaptureConfig.Builder from = CaptureConfig.Builder.from(captureConfig);
            if (!captureConfig.getSurfaces().isEmpty() || !captureConfig.isUseRepeatingSurface() || checkAndAttachRepeatingSurface(from)) {
                arrayList.add(from.build());
            }
        }
        q("Issue capture request");
        this.f667e.j(arrayList);
    }

    void D() {
        SessionConfig.ValidatingBuilder activeAndAttachedBuilder = this.mUseCaseAttachState.getActiveAndAttachedBuilder();
        if (!activeAndAttachedBuilder.isValid()) {
            this.f667e.o(this.f668f);
            return;
        }
        activeAndAttachedBuilder.add(this.f668f);
        this.f667e.o(activeAndAttachedBuilder.build());
    }

    void E(@NonNull CameraDevice cameraDevice) {
        try {
            this.mCameraControlInternal.setDefaultRequestBuilder(cameraDevice.createCaptureRequest(this.mCameraControlInternal.q()));
        } catch (CameraAccessException e2) {
            Logger.e(TAG, "fail to create capture request.", e2);
        }
    }

    @Override // androidx.camera.core.impl.CameraInternal
    public void attachUseCases(@NonNull final Collection<UseCase> collection) {
        if (collection.isEmpty()) {
            return;
        }
        this.mCameraControlInternal.x();
        notifyStateAttachedToUseCases(new ArrayList(collection));
        try {
            this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.u
                @Override // java.lang.Runnable
                public final void run() {
                    Camera2CameraImpl.this.lambda$attachUseCases$11(collection);
                }
            });
        } catch (RejectedExecutionException e2) {
            debugLog("Unable to attach use cases.", e2);
            this.mCameraControlInternal.n();
        }
    }

    @Override // androidx.camera.core.impl.CameraInternal
    public void close() {
        this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.z
            @Override // java.lang.Runnable
            public final void run() {
                Camera2CameraImpl.this.closeInternal();
            }
        });
    }

    @Override // androidx.camera.core.impl.CameraInternal
    public void detachUseCases(@NonNull final Collection<UseCase> collection) {
        if (collection.isEmpty()) {
            return;
        }
        notifyStateDetachedToUseCases(new ArrayList(collection));
        this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.t
            @Override // java.lang.Runnable
            public final void run() {
                Camera2CameraImpl.this.lambda$detachUseCases$12(collection);
            }
        });
    }

    @Override // androidx.camera.core.impl.CameraInternal
    @NonNull
    public CameraControlInternal getCameraControlInternal() {
        return this.mCameraControlInternal;
    }

    @Override // androidx.camera.core.impl.CameraInternal
    @NonNull
    public CameraInfoInternal getCameraInfoInternal() {
        return this.f664b;
    }

    @Override // androidx.camera.core.impl.CameraInternal
    @NonNull
    public Observable<CameraInternal.State> getCameraState() {
        return this.mObservableState;
    }

    @Override // androidx.camera.core.UseCase.StateChangeCallback
    public void onUseCaseActive(@NonNull final UseCase useCase) {
        Preconditions.checkNotNull(useCase);
        this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.b0
            @Override // java.lang.Runnable
            public final void run() {
                Camera2CameraImpl.this.lambda$onUseCaseActive$5(useCase);
            }
        });
    }

    @Override // androidx.camera.core.UseCase.StateChangeCallback
    public void onUseCaseInactive(@NonNull final UseCase useCase) {
        Preconditions.checkNotNull(useCase);
        this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.d0
            @Override // java.lang.Runnable
            public final void run() {
                Camera2CameraImpl.this.lambda$onUseCaseInactive$6(useCase);
            }
        });
    }

    @Override // androidx.camera.core.UseCase.StateChangeCallback
    public void onUseCaseReset(@NonNull final UseCase useCase) {
        Preconditions.checkNotNull(useCase);
        this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.e0
            @Override // java.lang.Runnable
            public final void run() {
                Camera2CameraImpl.this.lambda$onUseCaseReset$8(useCase);
            }
        });
    }

    @Override // androidx.camera.core.UseCase.StateChangeCallback
    public void onUseCaseUpdated(@NonNull final UseCase useCase) {
        Preconditions.checkNotNull(useCase);
        this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.c0
            @Override // java.lang.Runnable
            public final void run() {
                Camera2CameraImpl.this.lambda$onUseCaseUpdated$7(useCase);
            }
        });
    }

    @Override // androidx.camera.core.impl.CameraInternal
    public void open() {
        this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.y
            @Override // java.lang.Runnable
            public final void run() {
                Camera2CameraImpl.this.openInternal();
            }
        });
    }

    void p(boolean z) {
        boolean z2 = this.f663a == InternalState.CLOSING || this.f663a == InternalState.RELEASING || (this.f663a == InternalState.REOPENING && this.f666d != 0);
        Preconditions.checkState(z2, "closeCamera should only be called in a CLOSING, RELEASING or REOPENING (with error) state. Current state: " + this.f663a + " (error: " + t(this.f666d) + ")");
        int i2 = Build.VERSION.SDK_INT;
        if (i2 <= 23 || i2 >= 29 || !isLegacyDevice() || this.f666d != 0) {
            A(z);
        } else {
            configAndClose(z);
        }
        this.f667e.d();
    }

    void q(@NonNull String str) {
        debugLog(str, null);
    }

    @Nullable
    SessionConfig r(@NonNull DeferrableSurface deferrableSurface) {
        for (SessionConfig sessionConfig : this.mUseCaseAttachState.getAttachedSessionConfigs()) {
            if (sessionConfig.getSurfaces().contains(deferrableSurface)) {
                return sessionConfig;
            }
        }
        return null;
    }

    @Override // androidx.camera.core.impl.CameraInternal
    @NonNull
    public ListenableFuture<Void> release() {
        return CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.camera2.internal.w
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                Object lambda$release$3;
                lambda$release$3 = Camera2CameraImpl.this.lambda$release$3(completer);
                return lambda$release$3;
            }
        });
    }

    void s() {
        Preconditions.checkState(this.f663a == InternalState.RELEASING || this.f663a == InternalState.CLOSING);
        Preconditions.checkState(this.f672j.isEmpty());
        this.f665c = null;
        if (this.f663a == InternalState.CLOSING) {
            B(InternalState.INITIALIZED);
            return;
        }
        this.mCameraManager.unregisterAvailabilityCallback(this.mCameraAvailability);
        B(InternalState.RELEASED);
        CallbackToFutureAdapter.Completer completer = this.f671i;
        if (completer != null) {
            completer.set(null);
            this.f671i = null;
        }
    }

    @NonNull
    public String toString() {
        return String.format(Locale.US, "Camera@%x[id=%s]", Integer.valueOf(hashCode()), this.f664b.getCameraId());
    }

    boolean u() {
        return this.f672j.isEmpty() && this.f673k.isEmpty();
    }

    @SuppressLint({"MissingPermission"})
    void v(boolean z) {
        if (!z) {
            this.mStateCallback.b();
        }
        this.mStateCallback.a();
        if (!this.mCameraAvailability.a() || !this.mCameraStateRegistry.tryOpenCamera(this)) {
            q("No cameras available. Waiting for available camera before opening camera.");
            B(InternalState.PENDING_OPEN);
            return;
        }
        B(InternalState.OPENING);
        q("Opening camera.");
        try {
            this.mCameraManager.openCamera(this.f664b.getCameraId(), this.mExecutor, createDeviceStateCallback());
        } catch (CameraAccessExceptionCompat e2) {
            q("Unable to open camera due to " + e2.getMessage());
            if (e2.getReason() != 10001) {
                return;
            }
            B(InternalState.INITIALIZED);
        } catch (SecurityException e3) {
            q("Unable to open camera due to " + e3.getMessage());
            B(InternalState.REOPENING);
            this.mStateCallback.c();
        }
    }

    void w() {
        Preconditions.checkState(this.f663a == InternalState.OPENED);
        SessionConfig.ValidatingBuilder attachedBuilder = this.mUseCaseAttachState.getAttachedBuilder();
        if (attachedBuilder.isValid()) {
            Futures.addCallback(this.f667e.m(attachedBuilder.build(), (CameraDevice) Preconditions.checkNotNull(this.f665c), this.mCaptureSessionOpenerBuilder.a()), new FutureCallback<Void>() { // from class: androidx.camera.camera2.internal.Camera2CameraImpl.2
                @Override // androidx.camera.core.impl.utils.futures.FutureCallback
                public void onFailure(Throwable th) {
                    if (th instanceof CameraAccessException) {
                        Camera2CameraImpl camera2CameraImpl = Camera2CameraImpl.this;
                        camera2CameraImpl.q("Unable to configure camera due to " + th.getMessage());
                    } else if (th instanceof CancellationException) {
                        Camera2CameraImpl.this.q("Unable to configure camera cancelled");
                    } else if (th instanceof DeferrableSurface.SurfaceClosedException) {
                        SessionConfig r2 = Camera2CameraImpl.this.r(((DeferrableSurface.SurfaceClosedException) th).getDeferrableSurface());
                        if (r2 != null) {
                            Camera2CameraImpl.this.x(r2);
                        }
                    } else if (!(th instanceof TimeoutException)) {
                        throw new RuntimeException(th);
                    } else {
                        Logger.e(Camera2CameraImpl.TAG, "Unable to configure camera " + Camera2CameraImpl.this.f664b.getCameraId() + ", timeout!");
                    }
                }

                @Override // androidx.camera.core.impl.utils.futures.FutureCallback
                public void onSuccess(@Nullable Void r1) {
                }
            }, this.mExecutor);
        } else {
            q("Unable to create capture session due to conflicting configurations");
        }
    }

    void x(@NonNull final SessionConfig sessionConfig) {
        ScheduledExecutorService mainThreadExecutor = CameraXExecutors.mainThreadExecutor();
        List<SessionConfig.ErrorListener> errorListeners = sessionConfig.getErrorListeners();
        if (errorListeners.isEmpty()) {
            return;
        }
        final SessionConfig.ErrorListener errorListener = errorListeners.get(0);
        debugLog("Posting surface closed", new Throwable());
        mainThreadExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.v
            @Override // java.lang.Runnable
            public final void run() {
                Camera2CameraImpl.lambda$postSurfaceClosedError$13(SessionConfig.ErrorListener.this, sessionConfig);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: y */
    public void lambda$configAndClose$1(@NonNull CaptureSession captureSession, @NonNull DeferrableSurface deferrableSurface, @NonNull Runnable runnable) {
        this.f673k.remove(captureSession);
        ListenableFuture z = z(captureSession, false);
        deferrableSurface.close();
        Futures.successfulAsList(Arrays.asList(z, deferrableSurface.getTerminationFuture())).addListener(runnable, CameraXExecutors.directExecutor());
    }

    ListenableFuture z(@NonNull final CaptureSession captureSession, boolean z) {
        captureSession.e();
        ListenableFuture n2 = captureSession.n(z);
        q("Releasing session in state " + this.f663a.name());
        this.f672j.put(captureSession, n2);
        Futures.addCallback(n2, new FutureCallback<Void>() { // from class: androidx.camera.camera2.internal.Camera2CameraImpl.1
            @Override // androidx.camera.core.impl.utils.futures.FutureCallback
            public void onFailure(Throwable th) {
            }

            @Override // androidx.camera.core.impl.utils.futures.FutureCallback
            public void onSuccess(@Nullable Void r2) {
                CameraDevice cameraDevice;
                Camera2CameraImpl.this.f672j.remove(captureSession);
                int i2 = AnonymousClass3.f677a[Camera2CameraImpl.this.f663a.ordinal()];
                if (i2 != 2) {
                    if (i2 != 5) {
                        if (i2 != 7) {
                            return;
                        }
                    } else if (Camera2CameraImpl.this.f666d == 0) {
                        return;
                    }
                }
                if (!Camera2CameraImpl.this.u() || (cameraDevice = Camera2CameraImpl.this.f665c) == null) {
                    return;
                }
                cameraDevice.close();
                Camera2CameraImpl.this.f665c = null;
            }
        }, CameraXExecutors.directExecutor());
        return n2;
    }
}
