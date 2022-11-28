package androidx.camera.view;

import androidx.annotation.GuardedBy;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.arch.core.util.Function;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.CameraCaptureResult;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.Observable;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.AsyncFunction;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.FutureChain;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.camera.view.PreviewView;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.lifecycle.MutableLiveData;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class PreviewStreamStateObserver implements Observable.Observer<CameraInternal.State> {
    private static final String TAG = "StreamStateObserver";

    /* renamed from: a  reason: collision with root package name */
    ListenableFuture f1367a;
    private final CameraInfoInternal mCameraInfoInternal;
    private boolean mHasStartedPreviewStreamFlow = false;
    @GuardedBy("this")
    private PreviewView.StreamState mPreviewStreamState;
    private final MutableLiveData<PreviewView.StreamState> mPreviewStreamStateLiveData;
    private final PreviewViewImplementation mPreviewViewImplementation;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PreviewStreamStateObserver(CameraInfoInternal cameraInfoInternal, MutableLiveData mutableLiveData, PreviewViewImplementation previewViewImplementation) {
        this.mCameraInfoInternal = cameraInfoInternal;
        this.mPreviewStreamStateLiveData = mutableLiveData;
        this.mPreviewViewImplementation = previewViewImplementation;
        synchronized (this) {
            this.mPreviewStreamState = (PreviewView.StreamState) mutableLiveData.getValue();
        }
    }

    private void cancelFlow() {
        ListenableFuture listenableFuture = this.f1367a;
        if (listenableFuture != null) {
            listenableFuture.cancel(false);
            this.f1367a = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ListenableFuture lambda$startPreviewStreamStateFlow$0(Void r1) {
        return this.mPreviewViewImplementation.i();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Void lambda$startPreviewStreamStateFlow$1(Void r1) {
        e(PreviewView.StreamState.STREAMING);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object lambda$waitForCaptureResult$2(final CameraInfo cameraInfo, List list, final CallbackToFutureAdapter.Completer completer) {
        CameraCaptureCallback cameraCaptureCallback = new CameraCaptureCallback(this) { // from class: androidx.camera.view.PreviewStreamStateObserver.2
            @Override // androidx.camera.core.impl.CameraCaptureCallback
            public void onCaptureCompleted(@NonNull CameraCaptureResult cameraCaptureResult) {
                completer.set(null);
                ((CameraInfoInternal) cameraInfo).removeSessionCaptureCallback(this);
            }
        };
        list.add(cameraCaptureCallback);
        ((CameraInfoInternal) cameraInfo).addSessionCaptureCallback(CameraXExecutors.directExecutor(), cameraCaptureCallback);
        return "waitForCaptureResult";
    }

    @MainThread
    private void startPreviewStreamStateFlow(final CameraInfo cameraInfo) {
        e(PreviewView.StreamState.IDLE);
        final ArrayList arrayList = new ArrayList();
        FutureChain transform = FutureChain.from(waitForCaptureResult(cameraInfo, arrayList)).transformAsync(new AsyncFunction() { // from class: androidx.camera.view.g
            @Override // androidx.camera.core.impl.utils.futures.AsyncFunction
            public final ListenableFuture apply(Object obj) {
                ListenableFuture lambda$startPreviewStreamStateFlow$0;
                lambda$startPreviewStreamStateFlow$0 = PreviewStreamStateObserver.this.lambda$startPreviewStreamStateFlow$0((Void) obj);
                return lambda$startPreviewStreamStateFlow$0;
            }
        }, CameraXExecutors.directExecutor()).transform(new Function() { // from class: androidx.camera.view.f
            @Override // androidx.arch.core.util.Function
            public final Object apply(Object obj) {
                Void lambda$startPreviewStreamStateFlow$1;
                lambda$startPreviewStreamStateFlow$1 = PreviewStreamStateObserver.this.lambda$startPreviewStreamStateFlow$1((Void) obj);
                return lambda$startPreviewStreamStateFlow$1;
            }
        }, CameraXExecutors.directExecutor());
        this.f1367a = transform;
        Futures.addCallback(transform, new FutureCallback<Void>() { // from class: androidx.camera.view.PreviewStreamStateObserver.1
            @Override // androidx.camera.core.impl.utils.futures.FutureCallback
            public void onFailure(Throwable th) {
                PreviewStreamStateObserver.this.f1367a = null;
                if (arrayList.isEmpty()) {
                    return;
                }
                for (CameraCaptureCallback cameraCaptureCallback : arrayList) {
                    ((CameraInfoInternal) cameraInfo).removeSessionCaptureCallback(cameraCaptureCallback);
                }
                arrayList.clear();
            }

            @Override // androidx.camera.core.impl.utils.futures.FutureCallback
            public void onSuccess(@Nullable Void r2) {
                PreviewStreamStateObserver.this.f1367a = null;
            }
        }, CameraXExecutors.directExecutor());
    }

    private ListenableFuture<Void> waitForCaptureResult(final CameraInfo cameraInfo, final List<CameraCaptureCallback> list) {
        return CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.view.h
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                Object lambda$waitForCaptureResult$2;
                lambda$waitForCaptureResult$2 = PreviewStreamStateObserver.this.lambda$waitForCaptureResult$2(cameraInfo, list, completer);
                return lambda$waitForCaptureResult$2;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d() {
        cancelFlow();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(PreviewView.StreamState streamState) {
        synchronized (this) {
            if (this.mPreviewStreamState.equals(streamState)) {
                return;
            }
            this.mPreviewStreamState = streamState;
            Logger.d(TAG, "Update Preview stream state to " + streamState);
            this.mPreviewStreamStateLiveData.postValue(streamState);
        }
    }

    @Override // androidx.camera.core.impl.Observable.Observer
    @MainThread
    public void onError(@NonNull Throwable th) {
        d();
        e(PreviewView.StreamState.IDLE);
    }

    @Override // androidx.camera.core.impl.Observable.Observer
    @MainThread
    public void onNewData(@Nullable CameraInternal.State state) {
        if (state == CameraInternal.State.CLOSING || state == CameraInternal.State.CLOSED || state == CameraInternal.State.RELEASING || state == CameraInternal.State.RELEASED) {
            e(PreviewView.StreamState.IDLE);
            if (this.mHasStartedPreviewStreamFlow) {
                this.mHasStartedPreviewStreamFlow = false;
                cancelFlow();
            }
        } else if ((state == CameraInternal.State.OPENING || state == CameraInternal.State.OPEN || state == CameraInternal.State.PENDING_OPEN) && !this.mHasStartedPreviewStreamFlow) {
            startPreviewStreamStateFlow(this.mCameraInfoInternal);
            this.mHasStartedPreviewStreamFlow = true;
        }
    }
}
