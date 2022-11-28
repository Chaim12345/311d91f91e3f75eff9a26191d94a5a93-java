package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import android.view.Surface;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.camera.camera2.internal.compat.CameraCaptureSessionCompat;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;
import java.util.concurrent.Executor;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public interface SynchronizedCaptureSession {

    /* loaded from: classes.dex */
    public static abstract class StateCallback {
        /* JADX INFO: Access modifiers changed from: package-private */
        public void onActive(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @RequiresApi(api = 26)
        public void onCaptureQueueEmpty(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void onClosed(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void onConfigureFailed(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void onConfigured(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void onReady(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void onSessionFinished(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @RequiresApi(api = 23)
        public void onSurfacePrepared(@NonNull SynchronizedCaptureSession synchronizedCaptureSession, @NonNull Surface surface) {
        }
    }

    void abortCaptures();

    int captureBurstRequests(@NonNull List<CaptureRequest> list, @NonNull CameraCaptureSession.CaptureCallback captureCallback);

    int captureBurstRequests(@NonNull List<CaptureRequest> list, @NonNull Executor executor, @NonNull CameraCaptureSession.CaptureCallback captureCallback);

    int captureSingleRequest(@NonNull CaptureRequest captureRequest, @NonNull CameraCaptureSession.CaptureCallback captureCallback);

    int captureSingleRequest(@NonNull CaptureRequest captureRequest, @NonNull Executor executor, @NonNull CameraCaptureSession.CaptureCallback captureCallback);

    void close();

    void finishClose();

    @NonNull
    CameraDevice getDevice();

    @NonNull
    StateCallback getStateCallback();

    @NonNull
    ListenableFuture<Void> getSynchronizedBlocker(@NonNull String str);

    int setRepeatingBurstRequests(@NonNull List<CaptureRequest> list, @NonNull CameraCaptureSession.CaptureCallback captureCallback);

    int setRepeatingBurstRequests(@NonNull List<CaptureRequest> list, @NonNull Executor executor, @NonNull CameraCaptureSession.CaptureCallback captureCallback);

    int setSingleRepeatingRequest(@NonNull CaptureRequest captureRequest, @NonNull CameraCaptureSession.CaptureCallback captureCallback);

    int setSingleRepeatingRequest(@NonNull CaptureRequest captureRequest, @NonNull Executor executor, @NonNull CameraCaptureSession.CaptureCallback captureCallback);

    void stopRepeating();

    @NonNull
    CameraCaptureSessionCompat toCameraCaptureSessionCompat();
}
