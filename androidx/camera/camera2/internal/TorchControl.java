package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import androidx.annotation.NonNull;
import androidx.camera.camera2.internal.Camera2CameraControlImpl;
import androidx.camera.camera2.internal.compat.CameraCharacteristicsCompat;
import androidx.camera.core.CameraControl;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.utils.Threads;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class TorchControl {
    private static final String TAG = "TorchControl";

    /* renamed from: a  reason: collision with root package name */
    CallbackToFutureAdapter.Completer f738a;

    /* renamed from: b  reason: collision with root package name */
    boolean f739b;
    private final Camera2CameraControlImpl mCamera2CameraControlImpl;
    private final Camera2CameraControlImpl.CaptureResultListener mCaptureResultListener;
    private final Executor mExecutor;
    private final boolean mHasFlashUnit;
    private boolean mIsActive;
    private final MutableLiveData<Integer> mTorchState;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TorchControl(@NonNull Camera2CameraControlImpl camera2CameraControlImpl, @NonNull CameraCharacteristicsCompat cameraCharacteristicsCompat, @NonNull Executor executor) {
        Camera2CameraControlImpl.CaptureResultListener captureResultListener = new Camera2CameraControlImpl.CaptureResultListener() { // from class: androidx.camera.camera2.internal.TorchControl.1
            @Override // androidx.camera.camera2.internal.Camera2CameraControlImpl.CaptureResultListener
            public boolean onCaptureResult(@NonNull TotalCaptureResult totalCaptureResult) {
                if (TorchControl.this.f738a != null) {
                    Integer num = (Integer) totalCaptureResult.getRequest().get(CaptureRequest.FLASH_MODE);
                    boolean z = num != null && num.intValue() == 2;
                    TorchControl torchControl = TorchControl.this;
                    if (z == torchControl.f739b) {
                        torchControl.f738a.set(null);
                        TorchControl.this.f738a = null;
                    }
                }
                return false;
            }
        };
        this.mCaptureResultListener = captureResultListener;
        this.mCamera2CameraControlImpl = camera2CameraControlImpl;
        this.mExecutor = executor;
        Boolean bool = (Boolean) cameraCharacteristicsCompat.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
        this.mHasFlashUnit = bool != null && bool.booleanValue();
        this.mTorchState = new MutableLiveData<>(0);
        camera2CameraControlImpl.l(captureResultListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object lambda$enableTorch$1(final boolean z, final CallbackToFutureAdapter.Completer completer) {
        this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.h1
            @Override // java.lang.Runnable
            public final void run() {
                TorchControl.this.lambda$enableTorch$0(completer, z);
            }
        });
        return "enableTorch: " + z;
    }

    private <T> void setLiveDataValue(@NonNull MutableLiveData<T> mutableLiveData, T t2) {
        if (Threads.isMainThread()) {
            mutableLiveData.setValue(t2);
        } else {
            mutableLiveData.postValue(t2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ListenableFuture c(final boolean z) {
        if (this.mHasFlashUnit) {
            setLiveDataValue(this.mTorchState, Integer.valueOf(z ? 1 : 0));
            return CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.camera2.internal.g1
                @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
                public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                    Object lambda$enableTorch$1;
                    lambda$enableTorch$1 = TorchControl.this.lambda$enableTorch$1(z, completer);
                    return lambda$enableTorch$1;
                }
            });
        }
        Logger.d(TAG, "Unable to enableTorch due to there is no flash unit.");
        return Futures.immediateFailedFuture(new IllegalStateException("No flash unit"));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public void lambda$enableTorch$0(@NonNull CallbackToFutureAdapter.Completer completer, boolean z) {
        if (!this.mIsActive) {
            setLiveDataValue(this.mTorchState, 0);
            completer.setException(new CameraControl.OperationCanceledException("Camera is not active."));
            return;
        }
        this.f739b = z;
        this.mCamera2CameraControlImpl.o(z);
        setLiveDataValue(this.mTorchState, Integer.valueOf(z ? 1 : 0));
        CallbackToFutureAdapter.Completer completer2 = this.f738a;
        if (completer2 != null) {
            completer2.setException(new CameraControl.OperationCanceledException("There is a new enableTorch being set"));
        }
        this.f738a = completer;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public LiveData e() {
        return this.mTorchState;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f(boolean z) {
        if (this.mIsActive == z) {
            return;
        }
        this.mIsActive = z;
        if (z) {
            return;
        }
        if (this.f739b) {
            this.f739b = false;
            this.mCamera2CameraControlImpl.o(false);
            setLiveDataValue(this.mTorchState, 0);
        }
        CallbackToFutureAdapter.Completer completer = this.f738a;
        if (completer != null) {
            completer.setException(new CameraControl.OperationCanceledException("Camera is not active."));
            this.f738a = null;
        }
    }
}
