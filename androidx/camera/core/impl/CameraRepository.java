package androidx.camera.core.impl;

import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.camera.core.CameraUnavailableException;
import androidx.camera.core.InitializationException;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public final class CameraRepository {
    private static final String TAG = "CameraRepository";
    @GuardedBy("mCamerasLock")
    private CallbackToFutureAdapter.Completer<Void> mDeinitCompleter;
    @GuardedBy("mCamerasLock")
    private ListenableFuture<Void> mDeinitFuture;
    private final Object mCamerasLock = new Object();
    @GuardedBy("mCamerasLock")
    private final Map<String, CameraInternal> mCameras = new LinkedHashMap();
    @GuardedBy("mCamerasLock")
    private final Set<CameraInternal> mReleasingCameras = new HashSet();

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object lambda$deinit$0(CallbackToFutureAdapter.Completer completer) {
        synchronized (this.mCamerasLock) {
            this.mDeinitCompleter = completer;
        }
        return "CameraRepository-deinit";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$deinit$1(CameraInternal cameraInternal) {
        synchronized (this.mCamerasLock) {
            this.mReleasingCameras.remove(cameraInternal);
            if (this.mReleasingCameras.isEmpty()) {
                Preconditions.checkNotNull(this.mDeinitCompleter);
                this.mDeinitCompleter.set(null);
                this.mDeinitCompleter = null;
                this.mDeinitFuture = null;
            }
        }
    }

    @NonNull
    public ListenableFuture<Void> deinit() {
        synchronized (this.mCamerasLock) {
            if (this.mCameras.isEmpty()) {
                ListenableFuture<Void> listenableFuture = this.mDeinitFuture;
                if (listenableFuture == null) {
                    listenableFuture = Futures.immediateFuture(null);
                }
                return listenableFuture;
            }
            ListenableFuture<Void> listenableFuture2 = this.mDeinitFuture;
            if (listenableFuture2 == null) {
                listenableFuture2 = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.core.impl.c
                    @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
                    public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                        Object lambda$deinit$0;
                        lambda$deinit$0 = CameraRepository.this.lambda$deinit$0(completer);
                        return lambda$deinit$0;
                    }
                });
                this.mDeinitFuture = listenableFuture2;
            }
            this.mReleasingCameras.addAll(this.mCameras.values());
            for (final CameraInternal cameraInternal : this.mCameras.values()) {
                cameraInternal.release().addListener(new Runnable() { // from class: androidx.camera.core.impl.d
                    @Override // java.lang.Runnable
                    public final void run() {
                        CameraRepository.this.lambda$deinit$1(cameraInternal);
                    }
                }, CameraXExecutors.directExecutor());
            }
            this.mCameras.clear();
            return listenableFuture2;
        }
    }

    @NonNull
    public CameraInternal getCamera(@NonNull String str) {
        CameraInternal cameraInternal;
        synchronized (this.mCamerasLock) {
            cameraInternal = this.mCameras.get(str);
            if (cameraInternal == null) {
                throw new IllegalArgumentException("Invalid camera: " + str);
            }
        }
        return cameraInternal;
    }

    @NonNull
    public LinkedHashSet<CameraInternal> getCameras() {
        LinkedHashSet<CameraInternal> linkedHashSet;
        synchronized (this.mCamerasLock) {
            linkedHashSet = new LinkedHashSet<>(this.mCameras.values());
        }
        return linkedHashSet;
    }

    public void init(@NonNull CameraFactory cameraFactory) {
        synchronized (this.mCamerasLock) {
            try {
                try {
                    for (String str : cameraFactory.getAvailableCameraIds()) {
                        Logger.d(TAG, "Added camera: " + str);
                        this.mCameras.put(str, cameraFactory.getCamera(str));
                    }
                } catch (CameraUnavailableException e2) {
                    throw new InitializationException(e2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
