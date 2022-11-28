package androidx.camera.camera2.internal;

import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.util.Range;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.experimental.UseExperimental;
import androidx.camera.camera2.impl.Camera2ImplConfig;
import androidx.camera.camera2.internal.Camera2CameraControlImpl;
import androidx.camera.camera2.internal.compat.CameraCharacteristicsCompat;
import androidx.camera.core.CameraControl;
import androidx.camera.core.ExperimentalExposureCompensation;
import androidx.camera.core.ExposureState;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;
@UseExperimental(markerClass = ExperimentalExposureCompensation.class)
/* loaded from: classes.dex */
public class ExposureControl {
    private static final int DEFAULT_EXPOSURE_COMPENSATION = 0;
    @NonNull
    private final Camera2CameraControlImpl mCameraControl;
    @NonNull
    private final Executor mExecutor;
    @NonNull
    private final ExposureStateImpl mExposureStateImpl;
    private boolean mIsActive = false;
    @Nullable
    private Camera2CameraControlImpl.CaptureResultListener mRunningCaptureResultListener;
    @Nullable
    private CallbackToFutureAdapter.Completer<Integer> mRunningCompleter;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ExposureControl(@NonNull Camera2CameraControlImpl camera2CameraControlImpl, @NonNull CameraCharacteristicsCompat cameraCharacteristicsCompat, @NonNull Executor executor) {
        this.mCameraControl = camera2CameraControlImpl;
        this.mExposureStateImpl = new ExposureStateImpl(cameraCharacteristicsCompat, 0);
        this.mExecutor = executor;
    }

    private void clearRunningTask() {
        CallbackToFutureAdapter.Completer<Integer> completer = this.mRunningCompleter;
        if (completer != null) {
            completer.setException(new CameraControl.OperationCanceledException("Cancelled by another setExposureCompensationIndex()"));
            this.mRunningCompleter = null;
        }
        Camera2CameraControlImpl.CaptureResultListener captureResultListener = this.mRunningCaptureResultListener;
        if (captureResultListener != null) {
            this.mCameraControl.y(captureResultListener);
            this.mRunningCaptureResultListener = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ExposureState d(CameraCharacteristicsCompat cameraCharacteristicsCompat) {
        return new ExposureStateImpl(cameraCharacteristicsCompat, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setExposureCompensationIndex$0(int i2, CallbackToFutureAdapter.Completer completer, TotalCaptureResult totalCaptureResult) {
        Integer num = (Integer) totalCaptureResult.get(CaptureResult.CONTROL_AE_STATE);
        Integer num2 = (Integer) totalCaptureResult.get(CaptureResult.CONTROL_AE_EXPOSURE_COMPENSATION);
        if (num != null && num2 != null) {
            int intValue = num.intValue();
            if ((intValue != 2 && intValue != 3 && intValue != 4) || num2.intValue() != i2) {
                return false;
            }
        } else if (num2 == null || num2.intValue() != i2) {
            return false;
        }
        completer.set(Integer.valueOf(i2));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setExposureCompensationIndex$1(final CallbackToFutureAdapter.Completer completer, final int i2) {
        if (!this.mIsActive) {
            this.mExposureStateImpl.a(0);
            completer.setException(new CameraControl.OperationCanceledException("Camera is not active."));
            return;
        }
        clearRunningTask();
        Preconditions.checkState(this.mRunningCompleter == null, "mRunningCompleter should be null when starting set a new exposure compensation value");
        Preconditions.checkState(this.mRunningCaptureResultListener == null, "mRunningCaptureResultListener should be null when starting set a new exposure compensation value");
        Camera2CameraControlImpl.CaptureResultListener captureResultListener = new Camera2CameraControlImpl.CaptureResultListener() { // from class: androidx.camera.camera2.internal.m0
            @Override // androidx.camera.camera2.internal.Camera2CameraControlImpl.CaptureResultListener
            public final boolean onCaptureResult(TotalCaptureResult totalCaptureResult) {
                boolean lambda$setExposureCompensationIndex$0;
                lambda$setExposureCompensationIndex$0 = ExposureControl.lambda$setExposureCompensationIndex$0(i2, completer, totalCaptureResult);
                return lambda$setExposureCompensationIndex$0;
            }
        };
        this.mRunningCaptureResultListener = captureResultListener;
        this.mRunningCompleter = completer;
        this.mCameraControl.l(captureResultListener);
        this.mCameraControl.C();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object lambda$setExposureCompensationIndex$2(final int i2, final CallbackToFutureAdapter.Completer completer) {
        this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.o0
            @Override // java.lang.Runnable
            public final void run() {
                ExposureControl.this.lambda$setExposureCompensationIndex$1(completer, i2);
            }
        });
        return "setExposureCompensationIndex[" + i2 + "]";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public ExposureState e() {
        return this.mExposureStateImpl;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f(boolean z) {
        if (z == this.mIsActive) {
            return;
        }
        this.mIsActive = z;
        if (z) {
            return;
        }
        this.mExposureStateImpl.a(0);
        clearRunningTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g(@NonNull Camera2ImplConfig.Builder builder) {
        builder.setCaptureRequestOption(CaptureRequest.CONTROL_AE_EXPOSURE_COMPENSATION, Integer.valueOf(this.mExposureStateImpl.getExposureCompensationIndex()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public ListenableFuture h(final int i2) {
        if (this.mExposureStateImpl.isExposureCompensationSupported()) {
            Range<Integer> exposureCompensationRange = this.mExposureStateImpl.getExposureCompensationRange();
            if (exposureCompensationRange.contains((Range<Integer>) Integer.valueOf(i2))) {
                this.mExposureStateImpl.a(i2);
                return Futures.nonCancellationPropagating(CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.camera2.internal.n0
                    @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
                    public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                        Object lambda$setExposureCompensationIndex$2;
                        lambda$setExposureCompensationIndex$2 = ExposureControl.this.lambda$setExposureCompensationIndex$2(i2, completer);
                        return lambda$setExposureCompensationIndex$2;
                    }
                }));
            }
            return Futures.immediateFailedFuture(new IllegalArgumentException("Requested ExposureCompensation " + i2 + " is not within valid range [" + exposureCompensationRange.getUpper() + ".." + exposureCompensationRange.getLower() + "]"));
        }
        return Futures.immediateFailedFuture(new IllegalArgumentException("ExposureCompensation is not supported"));
    }
}
