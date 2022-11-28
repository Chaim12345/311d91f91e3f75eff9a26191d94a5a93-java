package androidx.camera.camera2.interop;

import android.hardware.camera2.TotalCaptureResult;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.camera.camera2.impl.Camera2ImplConfig;
import androidx.camera.camera2.internal.Camera2CameraControlImpl;
import androidx.camera.camera2.interop.Camera2CameraControl;
import androidx.camera.camera2.interop.CaptureRequestOptions;
import androidx.camera.core.CameraControl;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.TagBundle;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;
@ExperimentalCamera2Interop
/* loaded from: classes.dex */
public final class Camera2CameraControl {
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String TAG_KEY = "Camera2CameraControl";

    /* renamed from: a  reason: collision with root package name */
    final Executor f954a;

    /* renamed from: c  reason: collision with root package name */
    CallbackToFutureAdapter.Completer f956c;
    private final Camera2CameraControlImpl mCamera2CameraControlImpl;
    private boolean mIsActive = false;
    private boolean mPendingUpdate = false;

    /* renamed from: b  reason: collision with root package name */
    final Object f955b = new Object();
    @GuardedBy("mLock")
    private Camera2ImplConfig.Builder mBuilder = new Camera2ImplConfig.Builder();
    private final Camera2CameraControlImpl.CaptureResultListener mCaptureResultListener = new Camera2CameraControlImpl.CaptureResultListener() { // from class: b.a
        @Override // androidx.camera.camera2.internal.Camera2CameraControlImpl.CaptureResultListener
        public final boolean onCaptureResult(TotalCaptureResult totalCaptureResult) {
            boolean lambda$new$0;
            lambda$new$0 = Camera2CameraControl.this.lambda$new$0(totalCaptureResult);
            return lambda$new$0;
        }
    };

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public Camera2CameraControl(@NonNull Camera2CameraControlImpl camera2CameraControlImpl, @NonNull Executor executor) {
        this.mCamera2CameraControlImpl = camera2CameraControlImpl;
        this.f954a = executor;
    }

    private void addCaptureRequestOptionsInternal(@NonNull CaptureRequestOptions captureRequestOptions) {
        synchronized (this.f955b) {
            for (Config.Option<?> option : captureRequestOptions.listOptions()) {
                this.mBuilder.getMutableConfig().insertOption(option, captureRequestOptions.retrieveOption(option));
            }
        }
    }

    private void clearCaptureRequestOptionsInternal() {
        synchronized (this.f955b) {
            this.mBuilder = new Camera2ImplConfig.Builder();
        }
    }

    @NonNull
    public static Camera2CameraControl from(@NonNull CameraControl cameraControl) {
        Preconditions.checkArgument(cameraControl instanceof Camera2CameraControlImpl, "CameraControl doesn't contain Camera2 implementation.");
        return ((Camera2CameraControlImpl) cameraControl).getCamera2CameraControl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object lambda$addCaptureRequestOptions$4(final CallbackToFutureAdapter.Completer completer) {
        this.f954a.execute(new Runnable() { // from class: b.g
            @Override // java.lang.Runnable
            public final void run() {
                Camera2CameraControl.this.lambda$addCaptureRequestOptions$3(completer);
            }
        });
        return "addCaptureRequestOptions";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object lambda$clearCaptureRequestOptions$6(final CallbackToFutureAdapter.Completer completer) {
        this.f954a.execute(new Runnable() { // from class: b.e
            @Override // java.lang.Runnable
            public final void run() {
                Camera2CameraControl.this.lambda$clearCaptureRequestOptions$5(completer);
            }
        });
        return "clearCaptureRequestOptions";
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public /* synthetic */ boolean lambda$new$0(TotalCaptureResult totalCaptureResult) {
        CallbackToFutureAdapter.Completer completer;
        Integer tag;
        if (this.f956c != null) {
            Object tag2 = totalCaptureResult.getRequest().getTag();
            if ((tag2 instanceof TagBundle) && (tag = ((TagBundle) tag2).getTag(TAG_KEY)) != null && tag.equals(Integer.valueOf(this.f956c.hashCode()))) {
                completer = this.f956c;
                this.f956c = null;
                if (completer == null) {
                    completer.set(null);
                    return false;
                }
                return false;
            }
        }
        completer = null;
        if (completer == null) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object lambda$setCaptureRequestOptions$2(final CallbackToFutureAdapter.Completer completer) {
        this.f954a.execute(new Runnable() { // from class: b.f
            @Override // java.lang.Runnable
            public final void run() {
                Camera2CameraControl.this.lambda$setCaptureRequestOptions$1(completer);
            }
        });
        return "setCaptureRequestOptions";
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setActiveInternal */
    public void lambda$setActive$7(boolean z) {
        if (this.mIsActive == z) {
            return;
        }
        this.mIsActive = z;
        if (z) {
            if (this.mPendingUpdate) {
                updateSession();
                return;
            }
            return;
        }
        clearCaptureRequestOptionsInternal();
        CallbackToFutureAdapter.Completer completer = this.f956c;
        if (completer != null) {
            completer.setException(new CameraControl.OperationCanceledException("The camera control has became inactive."));
            this.f956c = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: updateConfig */
    public void lambda$setCaptureRequestOptions$1(CallbackToFutureAdapter.Completer<Void> completer) {
        this.mPendingUpdate = true;
        CallbackToFutureAdapter.Completer completer2 = this.f956c;
        if (completer2 == null) {
            completer2 = null;
        }
        this.f956c = completer;
        if (this.mIsActive) {
            updateSession();
        }
        if (completer2 != null) {
            completer2.setException(new CameraControl.OperationCanceledException("Camera2CameraControl was updated with new options."));
        }
    }

    private void updateSession() {
        this.mCamera2CameraControlImpl.updateSessionConfig();
        this.mPendingUpdate = false;
    }

    @NonNull
    public ListenableFuture<Void> addCaptureRequestOptions(@NonNull CaptureRequestOptions captureRequestOptions) {
        addCaptureRequestOptionsInternal(captureRequestOptions);
        return Futures.nonCancellationPropagating(CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: b.d
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                Object lambda$addCaptureRequestOptions$4;
                lambda$addCaptureRequestOptions$4 = Camera2CameraControl.this.lambda$addCaptureRequestOptions$4(completer);
                return lambda$addCaptureRequestOptions$4;
            }
        }));
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public ListenableFuture<Void> clearCaptureRequestOptions() {
        clearCaptureRequestOptionsInternal();
        return Futures.nonCancellationPropagating(CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: b.b
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                Object lambda$clearCaptureRequestOptions$6;
                lambda$clearCaptureRequestOptions$6 = Camera2CameraControl.this.lambda$clearCaptureRequestOptions$6(completer);
                return lambda$clearCaptureRequestOptions$6;
            }
        }));
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public Camera2ImplConfig getCamera2ImplConfig() {
        Camera2ImplConfig build;
        synchronized (this.f955b) {
            if (this.f956c != null) {
                this.mBuilder.getMutableConfig().insertOption(Camera2ImplConfig.CAPTURE_REQUEST_TAG_OPTION, Integer.valueOf(this.f956c.hashCode()));
            }
            build = this.mBuilder.build();
        }
        return build;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public Camera2CameraControlImpl.CaptureResultListener getCaptureRequestListener() {
        return this.mCaptureResultListener;
    }

    @NonNull
    public CaptureRequestOptions getCaptureRequestOptions() {
        CaptureRequestOptions build;
        synchronized (this.f955b) {
            build = CaptureRequestOptions.Builder.from(this.mBuilder.build()).build();
        }
        return build;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void setActive(final boolean z) {
        this.f954a.execute(new Runnable() { // from class: b.h
            @Override // java.lang.Runnable
            public final void run() {
                Camera2CameraControl.this.lambda$setActive$7(z);
            }
        });
    }

    @NonNull
    public ListenableFuture<Void> setCaptureRequestOptions(@NonNull CaptureRequestOptions captureRequestOptions) {
        clearCaptureRequestOptionsInternal();
        addCaptureRequestOptionsInternal(captureRequestOptions);
        return Futures.nonCancellationPropagating(CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: b.c
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                Object lambda$setCaptureRequestOptions$2;
                lambda$setCaptureRequestOptions$2 = Camera2CameraControl.this.lambda$setCaptureRequestOptions$2(completer);
                return lambda$setCaptureRequestOptions$2;
            }
        }));
    }
}
