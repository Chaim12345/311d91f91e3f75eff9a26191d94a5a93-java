package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import android.os.Handler;
import android.view.Surface;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.camera2.internal.compat.params.SessionConfigurationCompat;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.AsyncFunction;
import androidx.camera.core.impl.utils.futures.FutureChain;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class SynchronizedCaptureSessionImpl extends SynchronizedCaptureSessionBaseImpl {
    private static final String TAG = "SyncCaptureSessionImpl";

    /* renamed from: i  reason: collision with root package name */
    CallbackToFutureAdapter.Completer f734i;
    @Nullable
    @GuardedBy("mObjectLock")

    /* renamed from: j  reason: collision with root package name */
    ListenableFuture f735j;
    @Nullable
    @GuardedBy("mObjectLock")

    /* renamed from: k  reason: collision with root package name */
    ListenableFuture f736k;
    private final CameraCaptureSession.CaptureCallback mCaptureCallback;
    @Nullable
    @GuardedBy("mObjectLock")
    private List<DeferrableSurface> mDeferrableSurfaces;
    @NonNull
    private final Set<String> mEnabledFeature;
    @GuardedBy("mObjectLock")
    private boolean mHasSubmittedRepeating;
    private final Object mObjectLock;
    @NonNull
    private final ListenableFuture<Void> mStartStreamingFuture;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SynchronizedCaptureSessionImpl(@NonNull Set set, @NonNull CaptureSessionRepository captureSessionRepository, @NonNull Executor executor, @NonNull ScheduledExecutorService scheduledExecutorService, @NonNull Handler handler) {
        super(captureSessionRepository, executor, scheduledExecutorService, handler);
        this.mObjectLock = new Object();
        this.mCaptureCallback = new CameraCaptureSession.CaptureCallback() { // from class: androidx.camera.camera2.internal.SynchronizedCaptureSessionImpl.1
            @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
            public void onCaptureSequenceAborted(@NonNull CameraCaptureSession cameraCaptureSession, int i2) {
                CallbackToFutureAdapter.Completer completer = SynchronizedCaptureSessionImpl.this.f734i;
                if (completer != null) {
                    completer.setCancelled();
                    SynchronizedCaptureSessionImpl.this.f734i = null;
                }
            }

            @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
            public void onCaptureStarted(@NonNull CameraCaptureSession cameraCaptureSession, @NonNull CaptureRequest captureRequest, long j2, long j3) {
                CallbackToFutureAdapter.Completer completer = SynchronizedCaptureSessionImpl.this.f734i;
                if (completer != null) {
                    completer.set(null);
                    SynchronizedCaptureSessionImpl.this.f734i = null;
                }
            }
        };
        this.mEnabledFeature = set;
        this.mStartStreamingFuture = set.contains("wait_for_request") ? CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.camera2.internal.e1
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                Object lambda$new$0;
                lambda$new$0 = SynchronizedCaptureSessionImpl.this.lambda$new$0(completer);
                return lambda$new$0;
            }
        }) : Futures.immediateFuture(null);
    }

    private void forceOnConfigureFailed(@NonNull Set<SynchronizedCaptureSession> set) {
        for (SynchronizedCaptureSession synchronizedCaptureSession : set) {
            synchronizedCaptureSession.getStateCallback().onConfigureFailed(synchronizedCaptureSession);
        }
    }

    private List<ListenableFuture<Void>> getBlockerFuture(@NonNull String str, List<SynchronizedCaptureSession> list) {
        ArrayList arrayList = new ArrayList();
        for (SynchronizedCaptureSession synchronizedCaptureSession : list) {
            arrayList.add(synchronizedCaptureSession.getSynchronizedBlocker(str));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$close$2() {
        n("Session call super.close()");
        super.close();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object lambda$new$0(CallbackToFutureAdapter.Completer completer) {
        this.f734i = completer;
        return "StartStreamingFuture[session=" + this + "]";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ListenableFuture lambda$openCaptureSession$1(CameraDevice cameraDevice, SessionConfigurationCompat sessionConfigurationCompat, List list, List list2) {
        return super.openCaptureSession(cameraDevice, sessionConfigurationCompat, list);
    }

    static void o(@NonNull Set set) {
        Iterator it = set.iterator();
        while (it.hasNext()) {
            SynchronizedCaptureSession synchronizedCaptureSession = (SynchronizedCaptureSession) it.next();
            synchronizedCaptureSession.getStateCallback().onClosed(synchronizedCaptureSession);
        }
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSessionBaseImpl, androidx.camera.camera2.internal.SynchronizedCaptureSession
    public void close() {
        n("Session call close()");
        if (this.mEnabledFeature.contains("wait_for_request")) {
            synchronized (this.mObjectLock) {
                if (!this.mHasSubmittedRepeating) {
                    this.mStartStreamingFuture.cancel(true);
                }
            }
        }
        this.mStartStreamingFuture.addListener(new Runnable() { // from class: androidx.camera.camera2.internal.f1
            @Override // java.lang.Runnable
            public final void run() {
                SynchronizedCaptureSessionImpl.this.lambda$close$2();
            }
        }, getExecutor());
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSessionBaseImpl, androidx.camera.camera2.internal.SynchronizedCaptureSession
    @NonNull
    public ListenableFuture<Void> getSynchronizedBlocker(@NonNull String str) {
        str.hashCode();
        return !str.equals("wait_for_request") ? super.getSynchronizedBlocker(str) : Futures.nonCancellationPropagating(this.mStartStreamingFuture);
    }

    void m() {
        synchronized (this.mObjectLock) {
            if (this.mDeferrableSurfaces == null) {
                n("deferrableSurface == null, maybe forceClose, skip close");
                return;
            }
            if (this.mEnabledFeature.contains("deferrableSurface_close")) {
                for (DeferrableSurface deferrableSurface : this.mDeferrableSurfaces) {
                    deferrableSurface.close();
                }
                n("deferrableSurface closed");
            }
        }
    }

    void n(String str) {
        Logger.d(TAG, "[" + this + "] " + str);
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSessionBaseImpl, androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
    public void onClosed(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
        m();
        n("onClosed()");
        super.onClosed(synchronizedCaptureSession);
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSessionBaseImpl, androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
    public void onConfigured(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
        SynchronizedCaptureSession synchronizedCaptureSession2;
        SynchronizedCaptureSession synchronizedCaptureSession3;
        n("Session onConfigured()");
        if (this.mEnabledFeature.contains("force_close")) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            Iterator it = this.f725b.e().iterator();
            while (it.hasNext() && (synchronizedCaptureSession3 = (SynchronizedCaptureSession) it.next()) != synchronizedCaptureSession) {
                linkedHashSet.add(synchronizedCaptureSession3);
            }
            forceOnConfigureFailed(linkedHashSet);
        }
        super.onConfigured(synchronizedCaptureSession);
        if (this.mEnabledFeature.contains("force_close")) {
            LinkedHashSet linkedHashSet2 = new LinkedHashSet();
            Iterator it2 = this.f725b.c().iterator();
            while (it2.hasNext() && (synchronizedCaptureSession2 = (SynchronizedCaptureSession) it2.next()) != synchronizedCaptureSession) {
                linkedHashSet2.add(synchronizedCaptureSession2);
            }
            o(linkedHashSet2);
        }
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSessionBaseImpl, androidx.camera.camera2.internal.SynchronizedCaptureSessionOpener.OpenerImpl
    @NonNull
    public ListenableFuture<Void> openCaptureSession(@NonNull final CameraDevice cameraDevice, @NonNull final SessionConfigurationCompat sessionConfigurationCompat, @NonNull final List<DeferrableSurface> list) {
        ListenableFuture<Void> nonCancellationPropagating;
        synchronized (this.mObjectLock) {
            FutureChain transformAsync = FutureChain.from(Futures.successfulAsList(getBlockerFuture("wait_for_request", this.f725b.d()))).transformAsync(new AsyncFunction() { // from class: androidx.camera.camera2.internal.d1
                @Override // androidx.camera.core.impl.utils.futures.AsyncFunction
                public final ListenableFuture apply(Object obj) {
                    ListenableFuture lambda$openCaptureSession$1;
                    lambda$openCaptureSession$1 = SynchronizedCaptureSessionImpl.this.lambda$openCaptureSession$1(cameraDevice, sessionConfigurationCompat, list, (List) obj);
                    return lambda$openCaptureSession$1;
                }
            }, CameraXExecutors.directExecutor());
            this.f735j = transformAsync;
            nonCancellationPropagating = Futures.nonCancellationPropagating(transformAsync);
        }
        return nonCancellationPropagating;
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSessionBaseImpl, androidx.camera.camera2.internal.SynchronizedCaptureSession
    public int setSingleRepeatingRequest(@NonNull CaptureRequest captureRequest, @NonNull CameraCaptureSession.CaptureCallback captureCallback) {
        int singleRepeatingRequest;
        if (this.mEnabledFeature.contains("wait_for_request")) {
            synchronized (this.mObjectLock) {
                this.mHasSubmittedRepeating = true;
                singleRepeatingRequest = super.setSingleRepeatingRequest(captureRequest, Camera2CaptureCallbacks.createComboCallback(this.mCaptureCallback, captureCallback));
            }
            return singleRepeatingRequest;
        }
        return super.setSingleRepeatingRequest(captureRequest, captureCallback);
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSessionBaseImpl, androidx.camera.camera2.internal.SynchronizedCaptureSessionOpener.OpenerImpl
    @NonNull
    public ListenableFuture<List<Surface>> startWithDeferrableSurface(@NonNull List<DeferrableSurface> list, long j2) {
        ListenableFuture<List<Surface>> nonCancellationPropagating;
        synchronized (this.mObjectLock) {
            this.mDeferrableSurfaces = list;
            nonCancellationPropagating = Futures.nonCancellationPropagating(super.startWithDeferrableSurface(list, j2));
        }
        return nonCancellationPropagating;
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSessionBaseImpl, androidx.camera.camera2.internal.SynchronizedCaptureSessionOpener.OpenerImpl
    public boolean stop() {
        boolean stop;
        synchronized (this.mObjectLock) {
            if (h()) {
                m();
            } else {
                ListenableFuture listenableFuture = this.f735j;
                if (listenableFuture != null) {
                    listenableFuture.cancel(true);
                }
                ListenableFuture listenableFuture2 = this.f736k;
                if (listenableFuture2 != null) {
                    listenableFuture2.cancel(true);
                }
            }
            stop = super.stop();
        }
        return stop;
    }
}
