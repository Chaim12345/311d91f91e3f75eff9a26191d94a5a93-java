package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.view.Surface;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.experimental.UseExperimental;
import androidx.camera.camera2.impl.Camera2ImplConfig;
import androidx.camera.camera2.impl.CameraEventCallbacks;
import androidx.camera.camera2.internal.CameraBurstCaptureCallback;
import androidx.camera.camera2.internal.SynchronizedCaptureSession;
import androidx.camera.camera2.internal.SynchronizedCaptureSessionStateCallbacks;
import androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat;
import androidx.camera.camera2.internal.compat.params.SessionConfigurationCompat;
import androidx.camera.camera2.internal.compat.workaround.StillCaptureFlow;
import androidx.camera.camera2.interop.ExperimentalCamera2Interop;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.MutableOptionsBundle;
import androidx.camera.core.impl.OptionsBundle;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.utils.futures.AsyncFunction;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.FutureChain;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CancellationException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class CaptureSession {
    private static final String TAG = "CaptureSession";
    private static final long TIMEOUT_GET_SURFACE_IN_MS = 5000;
    @Nullable

    /* renamed from: b  reason: collision with root package name */
    SynchronizedCaptureSessionOpener f690b;
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    SynchronizedCaptureSession f691c;
    @Nullable

    /* renamed from: d  reason: collision with root package name */
    volatile SessionConfig f692d;
    @GuardedBy("mStateLock")

    /* renamed from: h  reason: collision with root package name */
    State f696h;
    @GuardedBy("mStateLock")

    /* renamed from: i  reason: collision with root package name */
    ListenableFuture f697i;
    @GuardedBy("mStateLock")

    /* renamed from: j  reason: collision with root package name */
    CallbackToFutureAdapter.Completer f698j;

    /* renamed from: a  reason: collision with root package name */
    final Object f689a = new Object();
    private final List<CaptureConfig> mCaptureConfigs = new ArrayList();
    private final CameraCaptureSession.CaptureCallback mCaptureCallback = new CameraCaptureSession.CaptureCallback(this) { // from class: androidx.camera.camera2.internal.CaptureSession.1
        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureCompleted(@NonNull CameraCaptureSession cameraCaptureSession, @NonNull CaptureRequest captureRequest, @NonNull TotalCaptureResult totalCaptureResult) {
        }
    };
    @NonNull

    /* renamed from: e  reason: collision with root package name */
    volatile Config f693e = OptionsBundle.emptyBundle();
    @NonNull

    /* renamed from: f  reason: collision with root package name */
    CameraEventCallbacks f694f = CameraEventCallbacks.createEmptyCallback();
    private Map<DeferrableSurface, Surface> mConfiguredSurfaceMap = new HashMap();
    @GuardedBy("mStateLock")

    /* renamed from: g  reason: collision with root package name */
    List f695g = Collections.emptyList();

    /* renamed from: k  reason: collision with root package name */
    final StillCaptureFlow f699k = new StillCaptureFlow();
    private final StateCallback mCaptureSessionStateCallback = new StateCallback();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: androidx.camera.camera2.internal.CaptureSession$3  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass3 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f701a;

        static {
            int[] iArr = new int[State.values().length];
            f701a = iArr;
            try {
                iArr[State.UNINITIALIZED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f701a[State.INITIALIZED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f701a[State.GET_SURFACE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f701a[State.OPENING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f701a[State.OPENED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f701a[State.CLOSED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f701a[State.RELEASING.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f701a[State.RELEASED.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public enum State {
        UNINITIALIZED,
        INITIALIZED,
        GET_SURFACE,
        OPENING,
        OPENED,
        CLOSED,
        RELEASING,
        RELEASED
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public final class StateCallback extends SynchronizedCaptureSession.StateCallback {
        StateCallback() {
        }

        @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
        public void onConfigureFailed(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
            synchronized (CaptureSession.this.f689a) {
                switch (AnonymousClass3.f701a[CaptureSession.this.f696h.ordinal()]) {
                    case 1:
                    case 2:
                    case 3:
                    case 5:
                    case 8:
                        throw new IllegalStateException("onConfigureFailed() should not be possible in state: " + CaptureSession.this.f696h);
                    case 4:
                    case 6:
                    case 7:
                        CaptureSession.this.f();
                        break;
                }
                Logger.e(CaptureSession.TAG, "CameraCaptureSession.onConfigureFailed() " + CaptureSession.this.f696h);
            }
        }

        @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
        public void onConfigured(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
            synchronized (CaptureSession.this.f689a) {
                switch (AnonymousClass3.f701a[CaptureSession.this.f696h.ordinal()]) {
                    case 1:
                    case 2:
                    case 3:
                    case 5:
                    case 8:
                        throw new IllegalStateException("onConfigured() should not be possible in state: " + CaptureSession.this.f696h);
                    case 4:
                        CaptureSession captureSession = CaptureSession.this;
                        captureSession.f696h = State.OPENED;
                        captureSession.f691c = synchronizedCaptureSession;
                        if (captureSession.f692d != null) {
                            List<CaptureConfig> onEnableSession = CaptureSession.this.f694f.createComboCallback().onEnableSession();
                            if (!onEnableSession.isEmpty()) {
                                CaptureSession captureSession2 = CaptureSession.this;
                                captureSession2.i(captureSession2.p(onEnableSession));
                            }
                        }
                        Logger.d(CaptureSession.TAG, "Attempting to send capture request onConfigured");
                        CaptureSession.this.l();
                        CaptureSession.this.k();
                        break;
                    case 6:
                        CaptureSession.this.f691c = synchronizedCaptureSession;
                        break;
                    case 7:
                        synchronizedCaptureSession.close();
                        break;
                }
                Logger.d(CaptureSession.TAG, "CameraCaptureSession.onConfigured() mState=" + CaptureSession.this.f696h);
            }
        }

        @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
        public void onReady(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
            synchronized (CaptureSession.this.f689a) {
                if (AnonymousClass3.f701a[CaptureSession.this.f696h.ordinal()] == 1) {
                    throw new IllegalStateException("onReady() should not be possible in state: " + CaptureSession.this.f696h);
                }
                Logger.d(CaptureSession.TAG, "CameraCaptureSession.onReady() " + CaptureSession.this.f696h);
            }
        }

        @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
        public void onSessionFinished(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
            synchronized (CaptureSession.this.f689a) {
                if (CaptureSession.this.f696h == State.UNINITIALIZED) {
                    throw new IllegalStateException("onSessionFinished() should not be possible in state: " + CaptureSession.this.f696h);
                }
                Logger.d(CaptureSession.TAG, "onSessionFinished()");
                CaptureSession.this.f();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CaptureSession() {
        this.f696h = State.UNINITIALIZED;
        this.f696h = State.INITIALIZED;
    }

    private CameraCaptureSession.CaptureCallback createCamera2CaptureCallback(List<CameraCaptureCallback> list, CameraCaptureSession.CaptureCallback... captureCallbackArr) {
        ArrayList arrayList = new ArrayList(list.size() + captureCallbackArr.length);
        for (CameraCaptureCallback cameraCaptureCallback : list) {
            arrayList.add(CaptureCallbackConverter.a(cameraCaptureCallback));
        }
        Collections.addAll(arrayList, captureCallbackArr);
        return Camera2CaptureCallbacks.a(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$issueBurstCaptureRequest$2(CameraCaptureSession cameraCaptureSession, int i2, boolean z) {
        synchronized (this.f689a) {
            if (this.f696h == State.OPENED) {
                l();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object lambda$release$1(CallbackToFutureAdapter.Completer completer) {
        String str;
        synchronized (this.f689a) {
            Preconditions.checkState(this.f698j == null, "Release completer expected to be null");
            this.f698j = completer;
            str = "Release[session=" + this + "]";
        }
        return str;
    }

    @NonNull
    private static Config mergeOptions(List<CaptureConfig> list) {
        MutableOptionsBundle create = MutableOptionsBundle.create();
        for (CaptureConfig captureConfig : list) {
            Config implementationOptions = captureConfig.getImplementationOptions();
            for (Config.Option<?> option : implementationOptions.listOptions()) {
                Object retrieveOption = implementationOptions.retrieveOption(option, null);
                if (create.containsOption(option)) {
                    Object retrieveOption2 = create.retrieveOption(option, null);
                    if (!Objects.equals(retrieveOption2, retrieveOption)) {
                        Logger.d(TAG, "Detect conflicting option " + option.getId() + " : " + retrieveOption + " != " + retrieveOption2);
                    }
                } else {
                    create.insertOption(option, retrieveOption);
                }
            }
        }
        return create;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @NonNull
    @UseExperimental(markerClass = ExperimentalCamera2Interop.class)
    /* renamed from: openCaptureSession */
    public ListenableFuture<Void> lambda$open$0(@NonNull List<Surface> list, @NonNull SessionConfig sessionConfig, @NonNull CameraDevice cameraDevice) {
        synchronized (this.f689a) {
            int i2 = AnonymousClass3.f701a[this.f696h.ordinal()];
            if (i2 != 1 && i2 != 2) {
                if (i2 == 3) {
                    this.mConfiguredSurfaceMap.clear();
                    for (int i3 = 0; i3 < list.size(); i3++) {
                        this.mConfiguredSurfaceMap.put((DeferrableSurface) this.f695g.get(i3), list.get(i3));
                    }
                    ArrayList<Surface> arrayList = new ArrayList(new HashSet(list));
                    this.f696h = State.OPENING;
                    Logger.d(TAG, "Opening capture session.");
                    SynchronizedCaptureSession.StateCallback a2 = SynchronizedCaptureSessionStateCallbacks.a(this.mCaptureSessionStateCallback, new SynchronizedCaptureSessionStateCallbacks.Adapter(sessionConfig.getSessionStateCallbacks()));
                    CameraEventCallbacks cameraEventCallback = new Camera2ImplConfig(sessionConfig.getImplementationOptions()).getCameraEventCallback(CameraEventCallbacks.createEmptyCallback());
                    this.f694f = cameraEventCallback;
                    List<CaptureConfig> onPresetSession = cameraEventCallback.createComboCallback().onPresetSession();
                    CaptureConfig.Builder from = CaptureConfig.Builder.from(sessionConfig.getRepeatingCaptureConfig());
                    for (CaptureConfig captureConfig : onPresetSession) {
                        from.addImplementationOptions(captureConfig.getImplementationOptions());
                    }
                    ArrayList arrayList2 = new ArrayList();
                    for (Surface surface : arrayList) {
                        arrayList2.add(new OutputConfigurationCompat(surface));
                    }
                    SessionConfigurationCompat a3 = this.f690b.a(0, arrayList2, a2);
                    try {
                        CaptureRequest buildWithoutTarget = Camera2CaptureRequestBuilder.buildWithoutTarget(from.build(), cameraDevice);
                        if (buildWithoutTarget != null) {
                            a3.setSessionParameters(buildWithoutTarget);
                        }
                        return this.f690b.b(cameraDevice, a3, this.f695g);
                    } catch (CameraAccessException e2) {
                        return Futures.immediateFailedFuture(e2);
                    }
                } else if (i2 != 5) {
                    return Futures.immediateFailedFuture(new CancellationException("openCaptureSession() not execute in state: " + this.f696h));
                }
            }
            return Futures.immediateFailedFuture(new IllegalStateException("openCaptureSession() should not be possible in state: " + this.f696h));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d() {
        if (this.mCaptureConfigs.isEmpty()) {
            return;
        }
        for (CaptureConfig captureConfig : this.mCaptureConfigs) {
            for (CameraCaptureCallback cameraCaptureCallback : captureConfig.getCameraCaptureCallbacks()) {
                cameraCaptureCallback.onCaptureCancelled();
            }
        }
        this.mCaptureConfigs.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e() {
        synchronized (this.f689a) {
            int i2 = AnonymousClass3.f701a[this.f696h.ordinal()];
            if (i2 == 1) {
                throw new IllegalStateException("close() should not be possible in state: " + this.f696h);
            }
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        if (i2 == 5) {
                            if (this.f692d != null) {
                                List<CaptureConfig> onDisableSession = this.f694f.createComboCallback().onDisableSession();
                                if (!onDisableSession.isEmpty()) {
                                    try {
                                        j(p(onDisableSession));
                                    } catch (IllegalStateException e2) {
                                        Logger.e(TAG, "Unable to issue the request before close the capture session", e2);
                                    }
                                }
                            }
                        }
                    }
                    SynchronizedCaptureSessionOpener synchronizedCaptureSessionOpener = this.f690b;
                    Preconditions.checkNotNull(synchronizedCaptureSessionOpener, "The Opener shouldn't null in state:" + this.f696h);
                    this.f690b.d();
                    this.f696h = State.CLOSED;
                    this.f692d = null;
                } else {
                    SynchronizedCaptureSessionOpener synchronizedCaptureSessionOpener2 = this.f690b;
                    Preconditions.checkNotNull(synchronizedCaptureSessionOpener2, "The Opener shouldn't null in state:" + this.f696h);
                    this.f690b.d();
                }
            }
            this.f696h = State.RELEASED;
        }
    }

    @GuardedBy("mStateLock")
    void f() {
        State state = this.f696h;
        State state2 = State.RELEASED;
        if (state == state2) {
            Logger.d(TAG, "Skipping finishClose due to being state RELEASED.");
            return;
        }
        this.f696h = state2;
        this.f691c = null;
        CallbackToFutureAdapter.Completer completer = this.f698j;
        if (completer != null) {
            completer.set(null);
            this.f698j = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List g() {
        List unmodifiableList;
        synchronized (this.f689a) {
            unmodifiableList = Collections.unmodifiableList(this.mCaptureConfigs);
        }
        return unmodifiableList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public SessionConfig h() {
        SessionConfig sessionConfig;
        synchronized (this.f689a) {
            sessionConfig = this.f692d;
        }
        return sessionConfig;
    }

    void i(List list) {
        boolean z;
        if (list.isEmpty()) {
            return;
        }
        try {
            CameraBurstCaptureCallback cameraBurstCaptureCallback = new CameraBurstCaptureCallback();
            ArrayList arrayList = new ArrayList();
            Logger.d(TAG, "Issuing capture request.");
            Iterator it = list.iterator();
            boolean z2 = false;
            while (it.hasNext()) {
                CaptureConfig captureConfig = (CaptureConfig) it.next();
                if (captureConfig.getSurfaces().isEmpty()) {
                    Logger.d(TAG, "Skipping issuing empty capture request.");
                } else {
                    Iterator<DeferrableSurface> it2 = captureConfig.getSurfaces().iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            z = true;
                            break;
                        }
                        DeferrableSurface next = it2.next();
                        if (!this.mConfiguredSurfaceMap.containsKey(next)) {
                            Logger.d(TAG, "Skipping capture request with invalid surface: " + next);
                            z = false;
                            break;
                        }
                    }
                    if (z) {
                        if (captureConfig.getTemplateType() == 2) {
                            z2 = true;
                        }
                        CaptureConfig.Builder from = CaptureConfig.Builder.from(captureConfig);
                        if (this.f692d != null) {
                            from.addImplementationOptions(this.f692d.getRepeatingCaptureConfig().getImplementationOptions());
                        }
                        from.addImplementationOptions(this.f693e);
                        from.addImplementationOptions(captureConfig.getImplementationOptions());
                        CaptureRequest build = Camera2CaptureRequestBuilder.build(from.build(), this.f691c.getDevice(), this.mConfiguredSurfaceMap);
                        if (build == null) {
                            Logger.d(TAG, "Skipping issuing request without surface.");
                            return;
                        }
                        ArrayList arrayList2 = new ArrayList();
                        for (CameraCaptureCallback cameraCaptureCallback : captureConfig.getCameraCaptureCallbacks()) {
                            CaptureCallbackConverter.b(cameraCaptureCallback, arrayList2);
                        }
                        cameraBurstCaptureCallback.a(build, arrayList2);
                        arrayList.add(build);
                    }
                }
            }
            if (arrayList.isEmpty()) {
                Logger.d(TAG, "Skipping issuing burst request due to no valid request elements");
                return;
            }
            if (this.f699k.shouldStopRepeatingBeforeCapture(arrayList, z2)) {
                this.f691c.stopRepeating();
                cameraBurstCaptureCallback.setCaptureSequenceCallback(new CameraBurstCaptureCallback.CaptureSequenceCallback() { // from class: androidx.camera.camera2.internal.i0
                    @Override // androidx.camera.camera2.internal.CameraBurstCaptureCallback.CaptureSequenceCallback
                    public final void onCaptureSequenceCompletedOrAborted(CameraCaptureSession cameraCaptureSession, int i2, boolean z3) {
                        CaptureSession.this.lambda$issueBurstCaptureRequest$2(cameraCaptureSession, i2, z3);
                    }
                });
            }
            this.f691c.captureBurstRequests(arrayList, cameraBurstCaptureCallback);
        } catch (CameraAccessException e2) {
            Logger.e(TAG, "Unable to access camera: " + e2.getMessage());
            Thread.dumpStack();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void j(List list) {
        synchronized (this.f689a) {
            switch (AnonymousClass3.f701a[this.f696h.ordinal()]) {
                case 1:
                    throw new IllegalStateException("issueCaptureRequests() should not be possible in state: " + this.f696h);
                case 2:
                case 3:
                case 4:
                    this.mCaptureConfigs.addAll(list);
                    break;
                case 5:
                    this.mCaptureConfigs.addAll(list);
                    k();
                    break;
                case 6:
                case 7:
                case 8:
                    throw new IllegalStateException("Cannot issue capture request on a closed/released session.");
            }
        }
    }

    void k() {
        if (this.mCaptureConfigs.isEmpty()) {
            return;
        }
        try {
            i(this.mCaptureConfigs);
        } finally {
            this.mCaptureConfigs.clear();
        }
    }

    @GuardedBy("mStateLock")
    void l() {
        if (this.f692d == null) {
            Logger.d(TAG, "Skipping issueRepeatingCaptureRequests for no configuration case.");
            return;
        }
        CaptureConfig repeatingCaptureConfig = this.f692d.getRepeatingCaptureConfig();
        if (repeatingCaptureConfig.getSurfaces().isEmpty()) {
            Logger.d(TAG, "Skipping issueRepeatingCaptureRequests for no surface.");
            try {
                this.f691c.stopRepeating();
                return;
            } catch (CameraAccessException e2) {
                Logger.e(TAG, "Unable to access camera: " + e2.getMessage());
                Thread.dumpStack();
                return;
            }
        }
        try {
            Logger.d(TAG, "Issuing request for session.");
            CaptureConfig.Builder from = CaptureConfig.Builder.from(repeatingCaptureConfig);
            this.f693e = mergeOptions(this.f694f.createComboCallback().onRepeating());
            from.addImplementationOptions(this.f693e);
            CaptureRequest build = Camera2CaptureRequestBuilder.build(from.build(), this.f691c.getDevice(), this.mConfiguredSurfaceMap);
            if (build == null) {
                Logger.d(TAG, "Skipping issuing empty request for session.");
            } else {
                this.f691c.setSingleRepeatingRequest(build, createCamera2CaptureCallback(repeatingCaptureConfig.getCameraCaptureCallbacks(), this.mCaptureCallback));
            }
        } catch (CameraAccessException e3) {
            Logger.e(TAG, "Unable to access camera: " + e3.getMessage());
            Thread.dumpStack();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public ListenableFuture m(@NonNull final SessionConfig sessionConfig, @NonNull final CameraDevice cameraDevice, @NonNull SynchronizedCaptureSessionOpener synchronizedCaptureSessionOpener) {
        synchronized (this.f689a) {
            if (AnonymousClass3.f701a[this.f696h.ordinal()] == 2) {
                this.f696h = State.GET_SURFACE;
                ArrayList arrayList = new ArrayList(sessionConfig.getSurfaces());
                this.f695g = arrayList;
                this.f690b = synchronizedCaptureSessionOpener;
                FutureChain transformAsync = FutureChain.from(synchronizedCaptureSessionOpener.c(arrayList, 5000L)).transformAsync(new AsyncFunction() { // from class: androidx.camera.camera2.internal.j0
                    @Override // androidx.camera.core.impl.utils.futures.AsyncFunction
                    public final ListenableFuture apply(Object obj) {
                        ListenableFuture lambda$open$0;
                        lambda$open$0 = CaptureSession.this.lambda$open$0(sessionConfig, cameraDevice, (List) obj);
                        return lambda$open$0;
                    }
                }, this.f690b.getExecutor());
                Futures.addCallback(transformAsync, new FutureCallback<Void>() { // from class: androidx.camera.camera2.internal.CaptureSession.2
                    @Override // androidx.camera.core.impl.utils.futures.FutureCallback
                    public void onFailure(Throwable th) {
                        CaptureSession.this.f690b.d();
                        synchronized (CaptureSession.this.f689a) {
                            int i2 = AnonymousClass3.f701a[CaptureSession.this.f696h.ordinal()];
                            if ((i2 == 4 || i2 == 6 || i2 == 7) && !(th instanceof CancellationException)) {
                                Logger.w(CaptureSession.TAG, "Opening session with fail " + CaptureSession.this.f696h, th);
                                CaptureSession.this.f();
                            }
                        }
                    }

                    @Override // androidx.camera.core.impl.utils.futures.FutureCallback
                    public void onSuccess(@Nullable Void r1) {
                    }
                }, this.f690b.getExecutor());
                return Futures.nonCancellationPropagating(transformAsync);
            }
            Logger.e(TAG, "Open not allowed in state: " + this.f696h);
            return Futures.immediateFailedFuture(new IllegalStateException("open() should not allow the state: " + this.f696h));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x004d A[Catch: all -> 0x00a6, TryCatch #1 {, blocks: (B:4:0x0003, B:5:0x000d, B:28:0x009f, B:7:0x0012, B:10:0x0018, B:14:0x0024, B:13:0x001d, B:15:0x0029, B:17:0x004d, B:18:0x0051, B:20:0x0055, B:21:0x0060, B:22:0x0062, B:24:0x0064, B:25:0x0081, B:26:0x0086, B:27:0x009e), top: B:36:0x0003, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0055 A[Catch: all -> 0x00a6, TryCatch #1 {, blocks: (B:4:0x0003, B:5:0x000d, B:28:0x009f, B:7:0x0012, B:10:0x0018, B:14:0x0024, B:13:0x001d, B:15:0x0029, B:17:0x004d, B:18:0x0051, B:20:0x0055, B:21:0x0060, B:22:0x0062, B:24:0x0064, B:25:0x0081, B:26:0x0086, B:27:0x009e), top: B:36:0x0003, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public ListenableFuture n(boolean z) {
        synchronized (this.f689a) {
            switch (AnonymousClass3.f701a[this.f696h.ordinal()]) {
                case 1:
                    throw new IllegalStateException("release() should not be possible in state: " + this.f696h);
                case 2:
                    this.f696h = State.RELEASED;
                    return Futures.immediateFuture(null);
                case 3:
                    SynchronizedCaptureSessionOpener synchronizedCaptureSessionOpener = this.f690b;
                    Preconditions.checkNotNull(synchronizedCaptureSessionOpener, "The Opener shouldn't null in state:" + this.f696h);
                    this.f690b.d();
                    this.f696h = State.RELEASED;
                    return Futures.immediateFuture(null);
                case 4:
                    this.f696h = State.RELEASING;
                    SynchronizedCaptureSessionOpener synchronizedCaptureSessionOpener2 = this.f690b;
                    Preconditions.checkNotNull(synchronizedCaptureSessionOpener2, "The Opener shouldn't null in state:" + this.f696h);
                    if (this.f690b.d()) {
                        f();
                        return Futures.immediateFuture(null);
                    }
                    if (this.f697i == null) {
                        this.f697i = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.camera2.internal.k0
                            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
                            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                                Object lambda$release$1;
                                lambda$release$1 = CaptureSession.this.lambda$release$1(completer);
                                return lambda$release$1;
                            }
                        });
                    }
                    return this.f697i;
                case 5:
                case 6:
                    SynchronizedCaptureSession synchronizedCaptureSession = this.f691c;
                    if (synchronizedCaptureSession != null) {
                        if (z) {
                            try {
                                synchronizedCaptureSession.abortCaptures();
                            } catch (CameraAccessException e2) {
                                Logger.e(TAG, "Unable to abort captures.", e2);
                            }
                        }
                        this.f691c.close();
                    }
                    this.f696h = State.RELEASING;
                    SynchronizedCaptureSessionOpener synchronizedCaptureSessionOpener22 = this.f690b;
                    Preconditions.checkNotNull(synchronizedCaptureSessionOpener22, "The Opener shouldn't null in state:" + this.f696h);
                    if (this.f690b.d()) {
                    }
                    if (this.f697i == null) {
                    }
                    return this.f697i;
                case 7:
                    if (this.f697i == null) {
                    }
                    return this.f697i;
                default:
                    return Futures.immediateFuture(null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void o(SessionConfig sessionConfig) {
        synchronized (this.f689a) {
            switch (AnonymousClass3.f701a[this.f696h.ordinal()]) {
                case 1:
                    throw new IllegalStateException("setSessionConfig() should not be possible in state: " + this.f696h);
                case 2:
                case 3:
                case 4:
                    this.f692d = sessionConfig;
                    break;
                case 5:
                    this.f692d = sessionConfig;
                    if (!this.mConfiguredSurfaceMap.keySet().containsAll(sessionConfig.getSurfaces())) {
                        Logger.e(TAG, "Does not have the proper configured lists");
                        return;
                    }
                    Logger.d(TAG, "Attempting to submit CaptureRequest after setting");
                    l();
                    break;
                case 6:
                case 7:
                case 8:
                    throw new IllegalStateException("Session configuration cannot be set on a closed/released session.");
            }
        }
    }

    List p(List list) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            CaptureConfig.Builder from = CaptureConfig.Builder.from((CaptureConfig) it.next());
            from.setTemplateType(1);
            for (DeferrableSurface deferrableSurface : this.f692d.getRepeatingCaptureConfig().getSurfaces()) {
                from.addSurface(deferrableSurface);
            }
            arrayList.add(from.build());
        }
        return arrayList;
    }
}
