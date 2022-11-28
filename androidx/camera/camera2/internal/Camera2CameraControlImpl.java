package androidx.camera.camera2.internal;

import android.graphics.Rect;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.util.ArrayMap;
import android.util.Rational;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.experimental.UseExperimental;
import androidx.camera.camera2.impl.Camera2ImplConfig;
import androidx.camera.camera2.internal.Camera2CameraControlImpl;
import androidx.camera.camera2.internal.compat.CameraCharacteristicsCompat;
import androidx.camera.camera2.internal.compat.workaround.AeFpsRange;
import androidx.camera.camera2.internal.compat.workaround.AutoFlashAEModeDisabler;
import androidx.camera.camera2.interop.Camera2CameraControl;
import androidx.camera.camera2.interop.CaptureRequestOptions;
import androidx.camera.camera2.interop.ExperimentalCamera2Interop;
import androidx.camera.core.CameraControl;
import androidx.camera.core.ExperimentalExposureCompensation;
import androidx.camera.core.FocusMeteringAction;
import androidx.camera.core.FocusMeteringResult;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.CameraCaptureFailure;
import androidx.camera.core.impl.CameraCaptureResult;
import androidx.camera.core.impl.CameraControlInternal;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.Quirks;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
@UseExperimental(markerClass = ExperimentalCamera2Interop.class)
/* loaded from: classes.dex */
public class Camera2CameraControlImpl implements CameraControlInternal {
    private static final String TAG = "Camera2CameraControlImp";
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    final CameraControlSessionCallback f657a;

    /* renamed from: b  reason: collision with root package name */
    final Executor f658b;

    /* renamed from: c  reason: collision with root package name */
    volatile Rational f659c;
    private final AeFpsRange mAeFpsRange;
    private final AutoFlashAEModeDisabler mAutoFlashAEModeDisabler;
    private final Camera2CameraControl mCamera2CameraControl;
    private final CameraCaptureCallbackSet mCameraCaptureCallbackSet;
    private final CameraCharacteristicsCompat mCameraCharacteristics;
    private final CameraControlInternal.ControlUpdateCallback mControlUpdateCallback;
    private final ExposureControl mExposureControl;
    private volatile int mFlashMode;
    private final FocusMeteringControl mFocusMeteringControl;
    private volatile boolean mIsTorchOn;
    private final Object mLock = new Object();
    private final SessionConfig.Builder mSessionConfigBuilder;
    private final TorchControl mTorchControl;
    @GuardedBy("mLock")
    private int mUseCount;
    private final ZoomControl mZoomControl;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class CameraCaptureCallbackSet extends CameraCaptureCallback {

        /* renamed from: a  reason: collision with root package name */
        Set f660a = new HashSet();

        /* renamed from: b  reason: collision with root package name */
        Map f661b = new ArrayMap();

        CameraCaptureCallbackSet() {
        }

        void d(@NonNull Executor executor, @NonNull CameraCaptureCallback cameraCaptureCallback) {
            this.f660a.add(cameraCaptureCallback);
            this.f661b.put(cameraCaptureCallback, executor);
        }

        void e(@NonNull CameraCaptureCallback cameraCaptureCallback) {
            this.f660a.remove(cameraCaptureCallback);
            this.f661b.remove(cameraCaptureCallback);
        }

        @Override // androidx.camera.core.impl.CameraCaptureCallback
        public void onCaptureCancelled() {
            for (final CameraCaptureCallback cameraCaptureCallback : this.f660a) {
                try {
                    ((Executor) this.f661b.get(cameraCaptureCallback)).execute(new Runnable() { // from class: androidx.camera.camera2.internal.m
                        @Override // java.lang.Runnable
                        public final void run() {
                            CameraCaptureCallback.this.onCaptureCancelled();
                        }
                    });
                } catch (RejectedExecutionException e2) {
                    Logger.e(Camera2CameraControlImpl.TAG, "Executor rejected to invoke onCaptureCancelled.", e2);
                }
            }
        }

        @Override // androidx.camera.core.impl.CameraCaptureCallback
        public void onCaptureCompleted(@NonNull final CameraCaptureResult cameraCaptureResult) {
            for (final CameraCaptureCallback cameraCaptureCallback : this.f660a) {
                try {
                    ((Executor) this.f661b.get(cameraCaptureCallback)).execute(new Runnable() { // from class: androidx.camera.camera2.internal.o
                        @Override // java.lang.Runnable
                        public final void run() {
                            CameraCaptureCallback.this.onCaptureCompleted(cameraCaptureResult);
                        }
                    });
                } catch (RejectedExecutionException e2) {
                    Logger.e(Camera2CameraControlImpl.TAG, "Executor rejected to invoke onCaptureCompleted.", e2);
                }
            }
        }

        @Override // androidx.camera.core.impl.CameraCaptureCallback
        public void onCaptureFailed(@NonNull final CameraCaptureFailure cameraCaptureFailure) {
            for (final CameraCaptureCallback cameraCaptureCallback : this.f660a) {
                try {
                    ((Executor) this.f661b.get(cameraCaptureCallback)).execute(new Runnable() { // from class: androidx.camera.camera2.internal.n
                        @Override // java.lang.Runnable
                        public final void run() {
                            CameraCaptureCallback.this.onCaptureFailed(cameraCaptureFailure);
                        }
                    });
                } catch (RejectedExecutionException e2) {
                    Logger.e(Camera2CameraControlImpl.TAG, "Executor rejected to invoke onCaptureFailed.", e2);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class CameraControlSessionCallback extends CameraCaptureSession.CaptureCallback {

        /* renamed from: a  reason: collision with root package name */
        final Set f662a = new HashSet();
        private final Executor mExecutor;

        CameraControlSessionCallback(@NonNull Executor executor) {
            this.mExecutor = executor;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onCaptureCompleted$0(TotalCaptureResult totalCaptureResult) {
            HashSet hashSet = new HashSet();
            for (CaptureResultListener captureResultListener : this.f662a) {
                if (captureResultListener.onCaptureResult(totalCaptureResult)) {
                    hashSet.add(captureResultListener);
                }
            }
            if (hashSet.isEmpty()) {
                return;
            }
            this.f662a.removeAll(hashSet);
        }

        void b(@NonNull CaptureResultListener captureResultListener) {
            this.f662a.add(captureResultListener);
        }

        void c(@NonNull CaptureResultListener captureResultListener) {
            this.f662a.remove(captureResultListener);
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureCompleted(@NonNull CameraCaptureSession cameraCaptureSession, @NonNull CaptureRequest captureRequest, @NonNull final TotalCaptureResult totalCaptureResult) {
            this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.p
                @Override // java.lang.Runnable
                public final void run() {
                    Camera2CameraControlImpl.CameraControlSessionCallback.this.lambda$onCaptureCompleted$0(totalCaptureResult);
                }
            });
        }
    }

    /* loaded from: classes.dex */
    public interface CaptureResultListener {
        boolean onCaptureResult(@NonNull TotalCaptureResult totalCaptureResult);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Camera2CameraControlImpl(@NonNull CameraCharacteristicsCompat cameraCharacteristicsCompat, @NonNull ScheduledExecutorService scheduledExecutorService, @NonNull Executor executor, @NonNull CameraControlInternal.ControlUpdateCallback controlUpdateCallback, @NonNull Quirks quirks) {
        SessionConfig.Builder builder = new SessionConfig.Builder();
        this.mSessionConfigBuilder = builder;
        this.f659c = null;
        this.mUseCount = 0;
        this.mIsTorchOn = false;
        this.mFlashMode = 2;
        this.mAutoFlashAEModeDisabler = new AutoFlashAEModeDisabler();
        CameraCaptureCallbackSet cameraCaptureCallbackSet = new CameraCaptureCallbackSet();
        this.mCameraCaptureCallbackSet = cameraCaptureCallbackSet;
        this.mCameraCharacteristics = cameraCharacteristicsCompat;
        this.mControlUpdateCallback = controlUpdateCallback;
        this.f658b = executor;
        CameraControlSessionCallback cameraControlSessionCallback = new CameraControlSessionCallback(executor);
        this.f657a = cameraControlSessionCallback;
        builder.setTemplateType(q());
        builder.addRepeatingCameraCaptureCallback(CaptureCallbackContainer.a(cameraControlSessionCallback));
        builder.addRepeatingCameraCaptureCallback(cameraCaptureCallbackSet);
        this.mExposureControl = new ExposureControl(this, cameraCharacteristicsCompat, executor);
        this.mFocusMeteringControl = new FocusMeteringControl(this, scheduledExecutorService, executor);
        this.mZoomControl = new ZoomControl(this, cameraCharacteristicsCompat, executor);
        this.mTorchControl = new TorchControl(this, cameraCharacteristicsCompat, executor);
        this.mAeFpsRange = new AeFpsRange(quirks);
        this.mCamera2CameraControl = new Camera2CameraControl(this, executor);
        executor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.f
            @Override // java.lang.Runnable
            public final void run() {
                Camera2CameraControlImpl.this.lambda$new$0();
            }
        });
        updateSessionConfig();
    }

    private int getSupportedAeMode(int i2) {
        int[] iArr = (int[]) this.mCameraCharacteristics.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_MODES);
        if (iArr == null) {
            return 0;
        }
        return isModeInList(i2, iArr) ? i2 : isModeInList(1, iArr) ? 1 : 0;
    }

    private int getSupportedAwbMode(int i2) {
        int[] iArr = (int[]) this.mCameraCharacteristics.get(CameraCharacteristics.CONTROL_AWB_AVAILABLE_MODES);
        if (iArr == null) {
            return 0;
        }
        return isModeInList(i2, iArr) ? i2 : isModeInList(1, iArr) ? 1 : 0;
    }

    private boolean isControlInUse() {
        return w() > 0;
    }

    private boolean isModeInList(int i2, int[] iArr) {
        for (int i3 : iArr) {
            if (i2 == i3) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$addInteropConfig$1() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$addSessionCameraCaptureCallback$9(Executor executor, CameraCaptureCallback cameraCaptureCallback) {
        this.mCameraCaptureCallbackSet.d(executor, cameraCaptureCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$cancelAfAeTrigger$7(boolean z, boolean z2) {
        this.mFocusMeteringControl.j(z, z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$clearInteropConfig$2() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0() {
        l(this.mCamera2CameraControl.getCaptureRequestListener());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$removeSessionCameraCaptureCallback$10(CameraCaptureCallback cameraCaptureCallback) {
        this.mCameraCaptureCallbackSet.e(cameraCaptureCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$triggerAePrecapture$5(CallbackToFutureAdapter.Completer completer) {
        this.mFocusMeteringControl.r(completer);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object lambda$triggerAePrecapture$6(final CallbackToFutureAdapter.Completer completer) {
        this.f658b.execute(new Runnable() { // from class: androidx.camera.camera2.internal.i
            @Override // java.lang.Runnable
            public final void run() {
                Camera2CameraControlImpl.this.lambda$triggerAePrecapture$5(completer);
            }
        });
        return "triggerAePrecapture";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$triggerAf$3(CallbackToFutureAdapter.Completer completer) {
        this.mFocusMeteringControl.s(completer);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object lambda$triggerAf$4(final CallbackToFutureAdapter.Completer completer) {
        this.f658b.execute(new Runnable() { // from class: androidx.camera.camera2.internal.h
            @Override // java.lang.Runnable
            public final void run() {
                Camera2CameraControlImpl.this.lambda$triggerAf$3(completer);
            }
        });
        return "triggerAf";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void A(boolean z) {
        this.mFocusMeteringControl.n(z);
        this.mZoomControl.i(z);
        this.mTorchControl.f(z);
        this.mExposureControl.f(z);
        this.mCamera2CameraControl.setActive(z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: B */
    public void lambda$submitCaptureRequests$8(List list) {
        this.mControlUpdateCallback.onCameraControlCaptureRequests(list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void C() {
        this.mSessionConfigBuilder.setImplementationOptions(u());
        Object captureRequestTag = this.mCamera2CameraControl.getCamera2ImplConfig().getCaptureRequestTag(null);
        if (captureRequestTag != null && (captureRequestTag instanceof Integer)) {
            this.mSessionConfigBuilder.addTag(Camera2CameraControl.TAG_KEY, (Integer) captureRequestTag);
        }
        this.mControlUpdateCallback.onCameraControlUpdateSessionConfig(this.mSessionConfigBuilder.build());
    }

    @Override // androidx.camera.core.impl.CameraControlInternal
    public void addInteropConfig(@NonNull Config config) {
        this.mCamera2CameraControl.addCaptureRequestOptions(CaptureRequestOptions.Builder.from(config).build()).addListener(c.f754a, CameraXExecutors.directExecutor());
    }

    @Override // androidx.camera.core.impl.CameraControlInternal
    public void cancelAfAeTrigger(final boolean z, final boolean z2) {
        if (isControlInUse()) {
            this.f658b.execute(new Runnable() { // from class: androidx.camera.camera2.internal.l
                @Override // java.lang.Runnable
                public final void run() {
                    Camera2CameraControlImpl.this.lambda$cancelAfAeTrigger$7(z, z2);
                }
            });
        } else {
            Logger.w(TAG, "Camera is not active.");
        }
    }

    @Override // androidx.camera.core.CameraControl
    @NonNull
    public ListenableFuture<Void> cancelFocusAndMetering() {
        return !isControlInUse() ? Futures.immediateFailedFuture(new CameraControl.OperationCanceledException("Camera is not active.")) : Futures.nonCancellationPropagating(this.mFocusMeteringControl.k());
    }

    @Override // androidx.camera.core.impl.CameraControlInternal
    public void clearInteropConfig() {
        this.mCamera2CameraControl.clearCaptureRequestOptions().addListener(b.f749a, CameraXExecutors.directExecutor());
    }

    @Override // androidx.camera.core.CameraControl
    @NonNull
    public ListenableFuture<Void> enableTorch(boolean z) {
        return !isControlInUse() ? Futures.immediateFailedFuture(new CameraControl.OperationCanceledException("Camera is not active.")) : Futures.nonCancellationPropagating(this.mTorchControl.c(z));
    }

    @NonNull
    public Camera2CameraControl getCamera2CameraControl() {
        return this.mCamera2CameraControl;
    }

    @NonNull
    public ExposureControl getExposureControl() {
        return this.mExposureControl;
    }

    @Override // androidx.camera.core.impl.CameraControlInternal
    public int getFlashMode() {
        return this.mFlashMode;
    }

    @Override // androidx.camera.core.impl.CameraControlInternal
    @NonNull
    public Config getInteropConfig() {
        return this.mCamera2CameraControl.getCamera2ImplConfig();
    }

    @Override // androidx.camera.core.impl.CameraControlInternal
    @NonNull
    public Rect getSensorRect() {
        return (Rect) Preconditions.checkNotNull((Rect) this.mCameraCharacteristics.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE));
    }

    @NonNull
    public TorchControl getTorchControl() {
        return this.mTorchControl;
    }

    @NonNull
    public ZoomControl getZoomControl() {
        return this.mZoomControl;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void l(@NonNull CaptureResultListener captureResultListener) {
        this.f657a.b(captureResultListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void m(@NonNull final Executor executor, @NonNull final CameraCaptureCallback cameraCaptureCallback) {
        this.f658b.execute(new Runnable() { // from class: androidx.camera.camera2.internal.k
            @Override // java.lang.Runnable
            public final void run() {
                Camera2CameraControlImpl.this.lambda$addSessionCameraCaptureCallback$9(executor, cameraCaptureCallback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void n() {
        synchronized (this.mLock) {
            int i2 = this.mUseCount;
            if (i2 == 0) {
                throw new IllegalStateException("Decrementing use count occurs more times than incrementing");
            }
            this.mUseCount = i2 - 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void o(boolean z) {
        this.mIsTorchOn = z;
        if (!z) {
            CaptureConfig.Builder builder = new CaptureConfig.Builder();
            builder.setTemplateType(q());
            builder.setUseRepeatingSurface(true);
            Camera2ImplConfig.Builder builder2 = new Camera2ImplConfig.Builder();
            builder2.setCaptureRequestOption(CaptureRequest.CONTROL_AE_MODE, Integer.valueOf(getSupportedAeMode(1)));
            builder2.setCaptureRequestOption(CaptureRequest.FLASH_MODE, 0);
            builder.addImplementationOptions(builder2.build());
            lambda$submitCaptureRequests$8(Collections.singletonList(builder.build()));
        }
        C();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public Rect p() {
        return this.mZoomControl.f();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int q() {
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int r() {
        Integer num = (Integer) this.mCameraCharacteristics.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AE);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int s() {
        Integer num = (Integer) this.mCameraCharacteristics.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AF);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    public void setDefaultRequestBuilder(@NonNull CaptureRequest.Builder builder) {
        this.mFocusMeteringControl.o(builder);
    }

    @Override // androidx.camera.core.impl.CameraControlInternal, androidx.camera.core.CameraControl
    @NonNull
    @ExperimentalExposureCompensation
    public ListenableFuture<Integer> setExposureCompensationIndex(int i2) {
        return !isControlInUse() ? Futures.immediateFailedFuture(new CameraControl.OperationCanceledException("Camera is not active.")) : this.mExposureControl.h(i2);
    }

    @Override // androidx.camera.core.impl.CameraControlInternal
    public void setFlashMode(int i2) {
        if (!isControlInUse()) {
            Logger.w(TAG, "Camera is not active.");
            return;
        }
        this.mFlashMode = i2;
        updateSessionConfig();
    }

    @Override // androidx.camera.core.CameraControl
    @NonNull
    public ListenableFuture<Void> setLinearZoom(float f2) {
        return !isControlInUse() ? Futures.immediateFailedFuture(new CameraControl.OperationCanceledException("Camera is not active.")) : Futures.nonCancellationPropagating(this.mZoomControl.j(f2));
    }

    public void setPreviewAspectRatio(@Nullable Rational rational) {
        this.f659c = rational;
    }

    @Override // androidx.camera.core.CameraControl
    @NonNull
    public ListenableFuture<Void> setZoomRatio(float f2) {
        return !isControlInUse() ? Futures.immediateFailedFuture(new CameraControl.OperationCanceledException("Camera is not active.")) : Futures.nonCancellationPropagating(this.mZoomControl.k(f2));
    }

    @Override // androidx.camera.core.CameraControl
    @NonNull
    public ListenableFuture<FocusMeteringResult> startFocusAndMetering(@NonNull FocusMeteringAction focusMeteringAction) {
        return !isControlInUse() ? Futures.immediateFailedFuture(new CameraControl.OperationCanceledException("Camera is not active.")) : Futures.nonCancellationPropagating(this.mFocusMeteringControl.p(focusMeteringAction, this.f659c));
    }

    @Override // androidx.camera.core.impl.CameraControlInternal
    public void submitCaptureRequests(@NonNull final List<CaptureConfig> list) {
        if (isControlInUse()) {
            this.f658b.execute(new Runnable() { // from class: androidx.camera.camera2.internal.j
                @Override // java.lang.Runnable
                public final void run() {
                    Camera2CameraControlImpl.this.lambda$submitCaptureRequests$8(list);
                }
            });
        } else {
            Logger.w(TAG, "Camera is not active.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int t() {
        Integer num = (Integer) this.mCameraCharacteristics.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AWB);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    @Override // androidx.camera.core.impl.CameraControlInternal
    @NonNull
    public ListenableFuture<CameraCaptureResult> triggerAePrecapture() {
        return !isControlInUse() ? Futures.immediateFailedFuture(new CameraControl.OperationCanceledException("Camera is not active.")) : Futures.nonCancellationPropagating(CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.camera2.internal.d
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                Object lambda$triggerAePrecapture$6;
                lambda$triggerAePrecapture$6 = Camera2CameraControlImpl.this.lambda$triggerAePrecapture$6(completer);
                return lambda$triggerAePrecapture$6;
            }
        }));
    }

    @Override // androidx.camera.core.impl.CameraControlInternal
    @NonNull
    public ListenableFuture<CameraCaptureResult> triggerAf() {
        return !isControlInUse() ? Futures.immediateFailedFuture(new CameraControl.OperationCanceledException("Camera is not active.")) : Futures.nonCancellationPropagating(CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.camera2.internal.a
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                Object lambda$triggerAf$4;
                lambda$triggerAf$4 = Camera2CameraControlImpl.this.lambda$triggerAf$4(completer);
                return lambda$triggerAf$4;
            }
        }));
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0070 A[LOOP:0: B:12:0x006a->B:14:0x0070, LOOP_END] */
    @VisibleForTesting
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    Config u() {
        int correctedAeMode;
        Camera2ImplConfig.Builder builder = new Camera2ImplConfig.Builder();
        builder.setCaptureRequestOption(CaptureRequest.CONTROL_MODE, 1);
        this.mFocusMeteringControl.i(builder);
        this.mAeFpsRange.addAeFpsRangeOptions(builder);
        this.mZoomControl.e(builder);
        if (!this.mIsTorchOn) {
            int i2 = this.mFlashMode;
            if (i2 == 0) {
                correctedAeMode = this.mAutoFlashAEModeDisabler.getCorrectedAeMode(2);
            } else if (i2 == 1) {
                correctedAeMode = 3;
            }
            builder.setCaptureRequestOption(CaptureRequest.CONTROL_AE_MODE, Integer.valueOf(getSupportedAeMode(correctedAeMode)));
            builder.setCaptureRequestOption(CaptureRequest.CONTROL_AWB_MODE, Integer.valueOf(getSupportedAwbMode(1)));
            this.mExposureControl.g(builder);
            Camera2ImplConfig camera2ImplConfig = this.mCamera2CameraControl.getCamera2ImplConfig();
            for (Config.Option<?> option : camera2ImplConfig.listOptions()) {
                builder.getMutableConfig().insertOption(option, Config.OptionPriority.ALWAYS_OVERRIDE, camera2ImplConfig.retrieveOption(option));
            }
            return builder.build();
        }
        builder.setCaptureRequestOption(CaptureRequest.FLASH_MODE, 2);
        correctedAeMode = 1;
        builder.setCaptureRequestOption(CaptureRequest.CONTROL_AE_MODE, Integer.valueOf(getSupportedAeMode(correctedAeMode)));
        builder.setCaptureRequestOption(CaptureRequest.CONTROL_AWB_MODE, Integer.valueOf(getSupportedAwbMode(1)));
        this.mExposureControl.g(builder);
        Camera2ImplConfig camera2ImplConfig2 = this.mCamera2CameraControl.getCamera2ImplConfig();
        while (r2.hasNext()) {
        }
        return builder.build();
    }

    public void updateSessionConfig() {
        this.f658b.execute(new Runnable() { // from class: androidx.camera.camera2.internal.e
            @Override // java.lang.Runnable
            public final void run() {
                Camera2CameraControlImpl.this.C();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int v(int i2) {
        int[] iArr = (int[]) this.mCameraCharacteristics.get(CameraCharacteristics.CONTROL_AF_AVAILABLE_MODES);
        if (iArr == null) {
            return 0;
        }
        if (isModeInList(i2, iArr)) {
            return i2;
        }
        if (isModeInList(4, iArr)) {
            return 4;
        }
        return isModeInList(1, iArr) ? 1 : 0;
    }

    @VisibleForTesting
    int w() {
        int i2;
        synchronized (this.mLock) {
            i2 = this.mUseCount;
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void x() {
        synchronized (this.mLock) {
            this.mUseCount++;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void y(@NonNull CaptureResultListener captureResultListener) {
        this.f657a.c(captureResultListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void z(@NonNull final CameraCaptureCallback cameraCaptureCallback) {
        this.f658b.execute(new Runnable() { // from class: androidx.camera.camera2.internal.g
            @Override // java.lang.Runnable
            public final void run() {
                Camera2CameraControlImpl.this.lambda$removeSessionCameraCaptureCallback$10(cameraCaptureCallback);
            }
        });
    }
}
