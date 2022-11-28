package androidx.camera.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Size;
import android.view.Display;
import androidx.annotation.DoNotInline;
import androidx.annotation.FloatRange;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.OptIn;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.arch.core.util.Function;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraControl;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.CameraInfoUnavailableException;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ExperimentalUseCaseGroup;
import androidx.camera.core.FocusMeteringAction;
import androidx.camera.core.FocusMeteringResult;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.Logger;
import androidx.camera.core.MeteringPoint;
import androidx.camera.core.MeteringPointFactory;
import androidx.camera.core.Preview;
import androidx.camera.core.UseCaseGroup;
import androidx.camera.core.VideoCapture;
import androidx.camera.core.ViewPort;
import androidx.camera.core.ZoomState;
import androidx.camera.core.impl.ImageOutputConfig;
import androidx.camera.core.impl.utils.Threads;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.RotationProvider;
import androidx.camera.view.video.ExperimentalVideo;
import androidx.camera.view.video.OnVideoSavedCallback;
import androidx.camera.view.video.OutputFileOptions;
import androidx.camera.view.video.OutputFileResults;
import androidx.core.util.Preconditions;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.firebase.messaging.Constants;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes.dex */
public abstract class CameraController {
    private static final float AE_SIZE = 0.25f;
    private static final float AF_SIZE = 0.16666667f;
    private static final String CAMERA_NOT_ATTACHED = "Use cases not attached to camera.";
    private static final String CAMERA_NOT_INITIALIZED = "Camera not initialized.";
    public static final int IMAGE_ANALYSIS = 2;
    public static final int IMAGE_CAPTURE = 1;
    private static final String IMAGE_CAPTURE_DISABLED = "ImageCapture disabled.";
    private static final String PREVIEW_VIEW_NOT_ATTACHED = "PreviewView not attached.";
    private static final String TAG = "CameraController";
    public static final int TAP_TO_FOCUS_FAILED = 4;
    public static final int TAP_TO_FOCUS_FOCUSED = 2;
    public static final int TAP_TO_FOCUS_NOT_FOCUSED = 3;
    public static final int TAP_TO_FOCUS_NOT_STARTED = 0;
    public static final int TAP_TO_FOCUS_STARTED = 1;
    @ExperimentalVideo
    public static final int VIDEO_CAPTURE = 4;
    private static final String VIDEO_CAPTURE_DISABLED = "VideoCapture disabled.";
    @NonNull

    /* renamed from: b  reason: collision with root package name */
    Preview f1346b;
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    OutputSize f1347c;
    @NonNull

    /* renamed from: d  reason: collision with root package name */
    ImageCapture f1348d;
    @Nullable

    /* renamed from: e  reason: collision with root package name */
    OutputSize f1349e;
    @Nullable

    /* renamed from: f  reason: collision with root package name */
    Executor f1350f;
    @NonNull

    /* renamed from: g  reason: collision with root package name */
    ImageAnalysis f1351g;
    @Nullable

    /* renamed from: h  reason: collision with root package name */
    OutputSize f1352h;
    @NonNull

    /* renamed from: i  reason: collision with root package name */
    VideoCapture f1353i;
    @Nullable

    /* renamed from: k  reason: collision with root package name */
    OutputSize f1355k;
    @Nullable

    /* renamed from: l  reason: collision with root package name */
    Camera f1356l;
    @Nullable

    /* renamed from: m  reason: collision with root package name */
    ProcessCameraProvider f1357m;
    @Nullable
    private ImageAnalysis.Analyzer mAnalysisAnalyzer;
    @Nullable
    private Executor mAnalysisBackgroundExecutor;
    @Nullable
    private Executor mAnalysisExecutor;
    private final Context mAppContext;
    @Nullable
    private final DisplayRotationListener mDisplayRotationListener;
    @NonNull
    private final ListenableFuture<Void> mInitializationFuture;
    private final RotationProvider mRotationProvider;
    @Nullable

    /* renamed from: n  reason: collision with root package name */
    ViewPort f1358n;
    @Nullable

    /* renamed from: o  reason: collision with root package name */
    Preview.SurfaceProvider f1359o;
    @Nullable

    /* renamed from: p  reason: collision with root package name */
    Display f1360p;
    @NonNull
    @VisibleForTesting

    /* renamed from: q  reason: collision with root package name */
    final RotationProvider.Listener f1361q;

    /* renamed from: a  reason: collision with root package name */
    CameraSelector f1345a = CameraSelector.DEFAULT_BACK_CAMERA;
    private int mEnabledUseCases = 3;
    @NonNull

    /* renamed from: j  reason: collision with root package name */
    final AtomicBoolean f1354j = new AtomicBoolean(false);
    private boolean mPinchToZoomEnabled = true;
    private boolean mTapToFocusEnabled = true;
    private final ForwardingLiveData<ZoomState> mZoomState = new ForwardingLiveData<>();
    private final ForwardingLiveData<Integer> mTorchState = new ForwardingLiveData<>();

    /* renamed from: r  reason: collision with root package name */
    final MutableLiveData f1362r = new MutableLiveData(0);

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(30)
    /* loaded from: classes.dex */
    public static class Api30Impl {
        private Api30Impl() {
        }

        @NonNull
        @DoNotInline
        static Context a(@NonNull Context context, @Nullable String str) {
            return context.createAttributionContext(str);
        }

        @Nullable
        @DoNotInline
        static String b(@NonNull Context context) {
            return context.getAttributionTag();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class DisplayRotationListener implements DisplayManager.DisplayListener {
        DisplayRotationListener() {
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayAdded(int i2) {
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        @OptIn(markerClass = {ExperimentalUseCaseGroup.class})
        @SuppressLint({"WrongConstant"})
        public void onDisplayChanged(int i2) {
            Display display = CameraController.this.f1360p;
            if (display == null || display.getDisplayId() != i2) {
                return;
            }
            CameraController cameraController = CameraController.this;
            cameraController.f1346b.setTargetRotation(cameraController.f1360p.getRotation());
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayRemoved(int i2) {
        }
    }

    /* loaded from: classes.dex */
    public static final class OutputSize {
        public static final int UNASSIGNED_ASPECT_RATIO = -1;
        private final int mAspectRatio;
        @Nullable
        private final Size mResolution;

        @Retention(RetentionPolicy.SOURCE)
        @RestrictTo({RestrictTo.Scope.LIBRARY})
        /* loaded from: classes.dex */
        public @interface OutputAspectRatio {
        }

        public OutputSize(int i2) {
            Preconditions.checkArgument(i2 != -1);
            this.mAspectRatio = i2;
            this.mResolution = null;
        }

        public OutputSize(@NonNull Size size) {
            Preconditions.checkNotNull(size);
            this.mAspectRatio = -1;
            this.mResolution = size;
        }

        public int getAspectRatio() {
            return this.mAspectRatio;
        }

        @Nullable
        public Size getResolution() {
            return this.mResolution;
        }

        @NonNull
        public String toString() {
            return "aspect ratio: " + this.mAspectRatio + " resolution: " + this.mResolution;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public @interface TapToFocusStates {
    }

    @OptIn(markerClass = {ExperimentalVideo.class})
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public @interface UseCases {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CameraController(@NonNull Context context) {
        Context applicationContext = getApplicationContext(context);
        this.mAppContext = applicationContext;
        this.f1346b = new Preview.Builder().build();
        this.f1348d = new ImageCapture.Builder().build();
        this.f1351g = new ImageAnalysis.Builder().build();
        this.f1353i = new VideoCapture.Builder().build();
        this.mInitializationFuture = Futures.transform(ProcessCameraProvider.getInstance(applicationContext), new Function() { // from class: androidx.camera.view.a
            @Override // androidx.arch.core.util.Function
            public final Object apply(Object obj) {
                Void lambda$new$0;
                lambda$new$0 = CameraController.this.lambda$new$0((ProcessCameraProvider) obj);
                return lambda$new$0;
            }
        }, CameraXExecutors.mainThreadExecutor());
        this.mDisplayRotationListener = new DisplayRotationListener();
        this.mRotationProvider = new RotationProvider(applicationContext);
        this.f1361q = new RotationProvider.Listener() { // from class: androidx.camera.view.b
            @Override // androidx.camera.view.RotationProvider.Listener
            public final void onRotationChanged(int i2) {
                CameraController.this.lambda$new$1(i2);
            }
        };
    }

    private static Context getApplicationContext(@NonNull Context context) {
        String b2;
        Context applicationContext = context.getApplicationContext();
        return (Build.VERSION.SDK_INT < 30 || (b2 = Api30Impl.b(context)) == null) ? applicationContext : Api30Impl.a(applicationContext, b2);
    }

    private DisplayManager getDisplayManager() {
        return (DisplayManager) this.mAppContext.getSystemService(Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION);
    }

    private boolean isCameraAttached() {
        return this.f1356l != null;
    }

    private boolean isCameraInitialized() {
        return this.f1357m != null;
    }

    private boolean isOutputSizeEqual(@Nullable OutputSize outputSize, @Nullable OutputSize outputSize2) {
        if (outputSize == outputSize2) {
            return true;
        }
        return outputSize != null && outputSize.equals(outputSize2);
    }

    private boolean isPreviewViewAttached() {
        return (this.f1359o == null || this.f1358n == null || this.f1360p == null) ? false : true;
    }

    private boolean isUseCaseEnabled(int i2) {
        return (i2 & this.mEnabledUseCases) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Void lambda$new$0(ProcessCameraProvider processCameraProvider) {
        this.f1357m = processCameraProvider;
        k();
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$1(int i2) {
        this.f1351g.setTargetRotation(i2);
        this.f1348d.setTargetRotation(i2);
        this.f1353i.setTargetRotation(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setCameraSelector$3(CameraSelector cameraSelector) {
        this.f1345a = cameraSelector;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setEnabledUseCases$2(int i2) {
        this.mEnabledUseCases = i2;
    }

    private void setTargetOutputSize(@NonNull ImageOutputConfig.Builder<?> builder, @Nullable OutputSize outputSize) {
        if (outputSize == null) {
            return;
        }
        if (outputSize.getResolution() != null) {
            builder.setTargetResolution(outputSize.getResolution());
        } else if (outputSize.getAspectRatio() != -1) {
            builder.setTargetAspectRatio(outputSize.getAspectRatio());
        } else {
            Logger.e(TAG, "Invalid target surface size. " + outputSize);
        }
    }

    private float speedUpZoomBy2X(float f2) {
        return f2 > 1.0f ? ((f2 - 1.0f) * 2.0f) + 1.0f : 1.0f - ((1.0f - f2) * 2.0f);
    }

    private void startListeningToRotationEvents() {
        getDisplayManager().registerDisplayListener(this.mDisplayRotationListener, new Handler(Looper.getMainLooper()));
        this.mRotationProvider.setListener(this.f1361q);
    }

    private void stopListeningToRotationEvents() {
        getDisplayManager().unregisterDisplayListener(this.mDisplayRotationListener);
        this.mRotationProvider.clearListener();
    }

    private void unbindImageAnalysisAndRecreate(int i2, int i3) {
        ImageAnalysis.Analyzer analyzer;
        if (isCameraInitialized()) {
            this.f1357m.unbind(this.f1351g);
        }
        ImageAnalysis.Builder imageQueueDepth = new ImageAnalysis.Builder().setBackpressureStrategy(i2).setImageQueueDepth(i3);
        setTargetOutputSize(imageQueueDepth, this.f1352h);
        Executor executor = this.mAnalysisBackgroundExecutor;
        if (executor != null) {
            imageQueueDepth.setBackgroundExecutor(executor);
        }
        ImageAnalysis build = imageQueueDepth.build();
        this.f1351g = build;
        Executor executor2 = this.mAnalysisExecutor;
        if (executor2 == null || (analyzer = this.mAnalysisAnalyzer) == null) {
            return;
        }
        build.setAnalyzer(executor2, analyzer);
    }

    private void unbindImageCaptureAndRecreate(int i2) {
        if (isCameraInitialized()) {
            this.f1357m.unbind(this.f1348d);
        }
        ImageCapture.Builder captureMode = new ImageCapture.Builder().setCaptureMode(i2);
        setTargetOutputSize(captureMode, this.f1349e);
        Executor executor = this.f1350f;
        if (executor != null) {
            captureMode.setIoExecutor(executor);
        }
        this.f1348d = captureMode.build();
    }

    private void unbindPreviewAndRecreate() {
        if (isCameraInitialized()) {
            this.f1357m.unbind(this.f1346b);
        }
        Preview.Builder builder = new Preview.Builder();
        setTargetOutputSize(builder, this.f1347c);
        this.f1346b = builder.build();
    }

    private void unbindVideoAndRecreate() {
        if (isCameraInitialized()) {
            this.f1357m.unbind(this.f1353i);
        }
        VideoCapture.Builder builder = new VideoCapture.Builder();
        setTargetOutputSize(builder, this.f1355k);
        this.f1353i = builder.build();
    }

    @MainThread
    public void clearImageAnalysisAnalyzer() {
        Threads.checkMainThread();
        this.mAnalysisExecutor = null;
        this.mAnalysisAnalyzer = null;
        this.f1351g.clearAnalyzer();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @SuppressLint({"MissingPermission", "WrongConstant"})
    @MainThread
    public void e(@NonNull Preview.SurfaceProvider surfaceProvider, @NonNull ViewPort viewPort, @NonNull Display display) {
        Threads.checkMainThread();
        if (this.f1359o != surfaceProvider) {
            this.f1359o = surfaceProvider;
            this.f1346b.setSurfaceProvider(surfaceProvider);
        }
        this.f1358n = viewPort;
        this.f1360p = display;
        startListeningToRotationEvents();
        k();
    }

    @NonNull
    @MainThread
    public ListenableFuture<Void> enableTorch(boolean z) {
        Threads.checkMainThread();
        if (isCameraAttached()) {
            return this.f1356l.getCameraControl().enableTorch(z);
        }
        Logger.w(TAG, CAMERA_NOT_ATTACHED);
        return Futures.immediateFuture(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @MainThread
    public void f() {
        Threads.checkMainThread();
        ProcessCameraProvider processCameraProvider = this.f1357m;
        if (processCameraProvider != null) {
            processCameraProvider.unbindAll();
        }
        this.f1346b.setSurfaceProvider(null);
        this.f1356l = null;
        this.f1359o = null;
        this.f1358n = null;
        this.f1360p = null;
        stopListeningToRotationEvents();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    @OptIn(markerClass = {ExperimentalUseCaseGroup.class, ExperimentalVideo.class})
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public UseCaseGroup g() {
        String str;
        if (!isCameraInitialized()) {
            str = CAMERA_NOT_INITIALIZED;
        } else if (isPreviewViewAttached()) {
            UseCaseGroup.Builder addUseCase = new UseCaseGroup.Builder().addUseCase(this.f1346b);
            if (isImageCaptureEnabled()) {
                addUseCase.addUseCase(this.f1348d);
            } else {
                this.f1357m.unbind(this.f1348d);
            }
            if (isImageAnalysisEnabled()) {
                addUseCase.addUseCase(this.f1351g);
            } else {
                this.f1357m.unbind(this.f1351g);
            }
            if (isVideoCaptureEnabled()) {
                addUseCase.addUseCase(this.f1353i);
            } else {
                this.f1357m.unbind(this.f1353i);
            }
            addUseCase.setViewPort(this.f1358n);
            return addUseCase.build();
        } else {
            str = PREVIEW_VIEW_NOT_ATTACHED;
        }
        Logger.d(TAG, str);
        return null;
    }

    @Nullable
    @MainThread
    public CameraControl getCameraControl() {
        Threads.checkMainThread();
        Camera camera = this.f1356l;
        if (camera == null) {
            return null;
        }
        return camera.getCameraControl();
    }

    @Nullable
    @MainThread
    public CameraInfo getCameraInfo() {
        Threads.checkMainThread();
        Camera camera = this.f1356l;
        if (camera == null) {
            return null;
        }
        return camera.getCameraInfo();
    }

    @NonNull
    @MainThread
    public CameraSelector getCameraSelector() {
        Threads.checkMainThread();
        return this.f1345a;
    }

    @Nullable
    @MainThread
    public Executor getImageAnalysisBackgroundExecutor() {
        Threads.checkMainThread();
        return this.mAnalysisBackgroundExecutor;
    }

    @MainThread
    public int getImageAnalysisBackpressureStrategy() {
        Threads.checkMainThread();
        return this.f1351g.getBackpressureStrategy();
    }

    @MainThread
    public int getImageAnalysisImageQueueDepth() {
        Threads.checkMainThread();
        return this.f1351g.getImageQueueDepth();
    }

    @Nullable
    @MainThread
    public OutputSize getImageAnalysisTargetSize() {
        Threads.checkMainThread();
        return this.f1352h;
    }

    @MainThread
    public int getImageCaptureFlashMode() {
        Threads.checkMainThread();
        return this.f1348d.getFlashMode();
    }

    @Nullable
    @MainThread
    public Executor getImageCaptureIoExecutor() {
        Threads.checkMainThread();
        return this.f1350f;
    }

    @MainThread
    public int getImageCaptureMode() {
        Threads.checkMainThread();
        return this.f1348d.getCaptureMode();
    }

    @Nullable
    @MainThread
    public OutputSize getImageCaptureTargetSize() {
        Threads.checkMainThread();
        return this.f1349e;
    }

    @NonNull
    public ListenableFuture<Void> getInitializationFuture() {
        return this.mInitializationFuture;
    }

    @Nullable
    @MainThread
    public OutputSize getPreviewTargetSize() {
        Threads.checkMainThread();
        return this.f1347c;
    }

    @NonNull
    @MainThread
    public LiveData<Integer> getTapToFocusState() {
        Threads.checkMainThread();
        return this.f1362r;
    }

    @NonNull
    @MainThread
    public LiveData<Integer> getTorchState() {
        Threads.checkMainThread();
        return this.mTorchState;
    }

    @Nullable
    @ExperimentalVideo
    @MainThread
    public OutputSize getVideoCaptureTargetSize() {
        Threads.checkMainThread();
        return this.f1355k;
    }

    @NonNull
    @MainThread
    public LiveData<ZoomState> getZoomState() {
        Threads.checkMainThread();
        return this.mZoomState;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void h(float f2) {
        if (!isCameraAttached()) {
            Logger.w(TAG, CAMERA_NOT_ATTACHED);
        } else if (!this.mPinchToZoomEnabled) {
            Logger.d(TAG, "Pinch to zoom disabled.");
        } else {
            Logger.d(TAG, "Pinch to zoom with scale: " + f2);
            ZoomState value = getZoomState().getValue();
            if (value == null) {
                return;
            }
            setZoomRatio(Math.min(Math.max(value.getZoomRatio() * speedUpZoomBy2X(f2), value.getMinZoomRatio()), value.getMaxZoomRatio()));
        }
    }

    @MainThread
    public boolean hasCamera(@NonNull CameraSelector cameraSelector) {
        Threads.checkMainThread();
        Preconditions.checkNotNull(cameraSelector);
        ProcessCameraProvider processCameraProvider = this.f1357m;
        if (processCameraProvider != null) {
            try {
                return processCameraProvider.hasCamera(cameraSelector);
            } catch (CameraInfoUnavailableException e2) {
                Logger.w(TAG, "Failed to check camera availability", e2);
                return false;
            }
        }
        throw new IllegalStateException("Camera not initialized. Please wait for the initialization future to finish. See #getInitializationFuture().");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void i(MeteringPointFactory meteringPointFactory, float f2, float f3) {
        if (!isCameraAttached()) {
            Logger.w(TAG, CAMERA_NOT_ATTACHED);
        } else if (!this.mTapToFocusEnabled) {
            Logger.d(TAG, "Tap to focus disabled. ");
        } else {
            Logger.d(TAG, "Tap to focus started: " + f2 + ", " + f3);
            this.f1362r.postValue(1);
            MeteringPoint createPoint = meteringPointFactory.createPoint(f2, f3, AF_SIZE);
            Futures.addCallback(this.f1356l.getCameraControl().startFocusAndMetering(new FocusMeteringAction.Builder(createPoint, 1).addPoint(meteringPointFactory.createPoint(f2, f3, AE_SIZE), 2).build()), new FutureCallback<FocusMeteringResult>() { // from class: androidx.camera.view.CameraController.2
                @Override // androidx.camera.core.impl.utils.futures.FutureCallback
                public void onFailure(Throwable th) {
                    if (th instanceof CameraControl.OperationCanceledException) {
                        Logger.d(CameraController.TAG, "Tap-to-focus is canceled by new action.");
                        return;
                    }
                    Logger.d(CameraController.TAG, "Tap to focus failed.", th);
                    CameraController.this.f1362r.postValue(4);
                }

                @Override // androidx.camera.core.impl.utils.futures.FutureCallback
                public void onSuccess(@Nullable FocusMeteringResult focusMeteringResult) {
                    if (focusMeteringResult == null) {
                        return;
                    }
                    Logger.d(CameraController.TAG, "Tap to focus onSuccess: " + focusMeteringResult.isFocusSuccessful());
                    CameraController.this.f1362r.postValue(Integer.valueOf(focusMeteringResult.isFocusSuccessful() ? 2 : 3));
                }
            }, CameraXExecutors.directExecutor());
        }
    }

    @MainThread
    public boolean isImageAnalysisEnabled() {
        Threads.checkMainThread();
        return isUseCaseEnabled(2);
    }

    @MainThread
    public boolean isImageCaptureEnabled() {
        Threads.checkMainThread();
        return isUseCaseEnabled(1);
    }

    @MainThread
    public boolean isPinchToZoomEnabled() {
        Threads.checkMainThread();
        return this.mPinchToZoomEnabled;
    }

    @ExperimentalVideo
    @MainThread
    public boolean isRecording() {
        Threads.checkMainThread();
        return this.f1354j.get();
    }

    @MainThread
    public boolean isTapToFocusEnabled() {
        Threads.checkMainThread();
        return this.mTapToFocusEnabled;
    }

    @ExperimentalVideo
    @MainThread
    public boolean isVideoCaptureEnabled() {
        Threads.checkMainThread();
        return isUseCaseEnabled(4);
    }

    @Nullable
    abstract Camera j();

    /* JADX INFO: Access modifiers changed from: package-private */
    public void k() {
        l(null);
    }

    void l(@Nullable Runnable runnable) {
        try {
            this.f1356l = j();
            if (!isCameraAttached()) {
                Logger.d(TAG, CAMERA_NOT_ATTACHED);
                return;
            }
            this.mZoomState.a(this.f1356l.getCameraInfo().getZoomState());
            this.mTorchState.a(this.f1356l.getCameraInfo().getTorchState());
        } catch (IllegalArgumentException e2) {
            if (runnable != null) {
                runnable.run();
            }
            throw new IllegalStateException("The selected camera does not support the enabled use cases. Please disable use case and/or select a different camera. e.g. #setVideoCaptureEnabled(false)", e2);
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @VisibleForTesting
    void m(@NonNull ImageCapture.OutputFileOptions outputFileOptions) {
        if (this.f1345a.getLensFacing() == null || outputFileOptions.getMetadata().isReversedHorizontalSet()) {
            return;
        }
        outputFileOptions.getMetadata().setReversedHorizontal(this.f1345a.getLensFacing().intValue() == 0);
    }

    @MainThread
    public void setCameraSelector(@NonNull CameraSelector cameraSelector) {
        Threads.checkMainThread();
        final CameraSelector cameraSelector2 = this.f1345a;
        if (cameraSelector2 == cameraSelector) {
            return;
        }
        this.f1345a = cameraSelector;
        ProcessCameraProvider processCameraProvider = this.f1357m;
        if (processCameraProvider == null) {
            return;
        }
        processCameraProvider.unbindAll();
        l(new Runnable() { // from class: androidx.camera.view.d
            @Override // java.lang.Runnable
            public final void run() {
                CameraController.this.lambda$setCameraSelector$3(cameraSelector2);
            }
        });
    }

    @OptIn(markerClass = {ExperimentalVideo.class})
    @MainThread
    public void setEnabledUseCases(int i2) {
        Threads.checkMainThread();
        final int i3 = this.mEnabledUseCases;
        if (i2 == i3) {
            return;
        }
        this.mEnabledUseCases = i2;
        if (!isVideoCaptureEnabled()) {
            stopRecording();
        }
        l(new Runnable() { // from class: androidx.camera.view.c
            @Override // java.lang.Runnable
            public final void run() {
                CameraController.this.lambda$setEnabledUseCases$2(i3);
            }
        });
    }

    @MainThread
    public void setImageAnalysisAnalyzer(@NonNull Executor executor, @NonNull ImageAnalysis.Analyzer analyzer) {
        Threads.checkMainThread();
        if (this.mAnalysisAnalyzer == analyzer && this.mAnalysisExecutor == executor) {
            return;
        }
        this.mAnalysisExecutor = executor;
        this.mAnalysisAnalyzer = analyzer;
        this.f1351g.setAnalyzer(executor, analyzer);
    }

    @MainThread
    public void setImageAnalysisBackgroundExecutor(@Nullable Executor executor) {
        Threads.checkMainThread();
        if (this.mAnalysisBackgroundExecutor == executor) {
            return;
        }
        this.mAnalysisBackgroundExecutor = executor;
        unbindImageAnalysisAndRecreate(this.f1351g.getBackpressureStrategy(), this.f1351g.getImageQueueDepth());
        k();
    }

    @MainThread
    public void setImageAnalysisBackpressureStrategy(int i2) {
        Threads.checkMainThread();
        if (this.f1351g.getBackpressureStrategy() == i2) {
            return;
        }
        unbindImageAnalysisAndRecreate(i2, this.f1351g.getImageQueueDepth());
        k();
    }

    @MainThread
    public void setImageAnalysisImageQueueDepth(int i2) {
        Threads.checkMainThread();
        if (this.f1351g.getImageQueueDepth() == i2) {
            return;
        }
        unbindImageAnalysisAndRecreate(this.f1351g.getBackpressureStrategy(), i2);
        k();
    }

    @MainThread
    public void setImageAnalysisTargetSize(@Nullable OutputSize outputSize) {
        Threads.checkMainThread();
        if (isOutputSizeEqual(this.f1352h, outputSize)) {
            return;
        }
        this.f1352h = outputSize;
        unbindImageAnalysisAndRecreate(this.f1351g.getBackpressureStrategy(), this.f1351g.getImageQueueDepth());
        k();
    }

    @MainThread
    public void setImageCaptureFlashMode(int i2) {
        Threads.checkMainThread();
        this.f1348d.setFlashMode(i2);
    }

    @MainThread
    public void setImageCaptureIoExecutor(@Nullable Executor executor) {
        Threads.checkMainThread();
        if (this.f1350f == executor) {
            return;
        }
        this.f1350f = executor;
        unbindImageCaptureAndRecreate(this.f1348d.getCaptureMode());
        k();
    }

    @MainThread
    public void setImageCaptureMode(int i2) {
        Threads.checkMainThread();
        if (this.f1348d.getCaptureMode() == i2) {
            return;
        }
        unbindImageCaptureAndRecreate(i2);
        k();
    }

    @MainThread
    public void setImageCaptureTargetSize(@Nullable OutputSize outputSize) {
        Threads.checkMainThread();
        if (isOutputSizeEqual(this.f1349e, outputSize)) {
            return;
        }
        this.f1349e = outputSize;
        unbindImageCaptureAndRecreate(getImageCaptureMode());
        k();
    }

    @NonNull
    @MainThread
    public ListenableFuture<Void> setLinearZoom(@FloatRange(from = 0.0d, to = 1.0d) float f2) {
        Threads.checkMainThread();
        if (isCameraAttached()) {
            return this.f1356l.getCameraControl().setLinearZoom(f2);
        }
        Logger.w(TAG, CAMERA_NOT_ATTACHED);
        return Futures.immediateFuture(null);
    }

    @MainThread
    public void setPinchToZoomEnabled(boolean z) {
        Threads.checkMainThread();
        this.mPinchToZoomEnabled = z;
    }

    @MainThread
    public void setPreviewTargetSize(@Nullable OutputSize outputSize) {
        Threads.checkMainThread();
        if (isOutputSizeEqual(this.f1347c, outputSize)) {
            return;
        }
        this.f1347c = outputSize;
        unbindPreviewAndRecreate();
        k();
    }

    @MainThread
    public void setTapToFocusEnabled(boolean z) {
        Threads.checkMainThread();
        this.mTapToFocusEnabled = z;
    }

    @ExperimentalVideo
    @MainThread
    public void setVideoCaptureTargetSize(@Nullable OutputSize outputSize) {
        Threads.checkMainThread();
        if (isOutputSizeEqual(this.f1355k, outputSize)) {
            return;
        }
        this.f1355k = outputSize;
        unbindVideoAndRecreate();
        k();
    }

    @NonNull
    @MainThread
    public ListenableFuture<Void> setZoomRatio(float f2) {
        Threads.checkMainThread();
        if (isCameraAttached()) {
            return this.f1356l.getCameraControl().setZoomRatio(f2);
        }
        Logger.w(TAG, CAMERA_NOT_ATTACHED);
        return Futures.immediateFuture(null);
    }

    @ExperimentalVideo
    @MainThread
    public void startRecording(@NonNull OutputFileOptions outputFileOptions, @NonNull Executor executor, @NonNull final OnVideoSavedCallback onVideoSavedCallback) {
        Threads.checkMainThread();
        Preconditions.checkState(isCameraInitialized(), CAMERA_NOT_INITIALIZED);
        Preconditions.checkState(isVideoCaptureEnabled(), VIDEO_CAPTURE_DISABLED);
        this.f1353i.lambda$startRecording$0(outputFileOptions.toVideoCaptureOutputFileOptions(), executor, new VideoCapture.OnVideoSavedCallback() { // from class: androidx.camera.view.CameraController.1
            @Override // androidx.camera.core.VideoCapture.OnVideoSavedCallback
            public void onError(int i2, @NonNull String str, @Nullable Throwable th) {
                CameraController.this.f1354j.set(false);
                onVideoSavedCallback.onError(i2, str, th);
            }

            @Override // androidx.camera.core.VideoCapture.OnVideoSavedCallback
            public void onVideoSaved(@NonNull VideoCapture.OutputFileResults outputFileResults) {
                CameraController.this.f1354j.set(false);
                onVideoSavedCallback.onVideoSaved(OutputFileResults.create(outputFileResults.getSavedUri()));
            }
        });
        this.f1354j.set(true);
    }

    @ExperimentalVideo
    @MainThread
    public void stopRecording() {
        Threads.checkMainThread();
        if (this.f1354j.get()) {
            this.f1353i.lambda$stopRecording$5();
        }
    }

    @MainThread
    public void takePicture(@NonNull ImageCapture.OutputFileOptions outputFileOptions, @NonNull Executor executor, @NonNull ImageCapture.OnImageSavedCallback onImageSavedCallback) {
        Threads.checkMainThread();
        Preconditions.checkState(isCameraInitialized(), CAMERA_NOT_INITIALIZED);
        Preconditions.checkState(isImageCaptureEnabled(), IMAGE_CAPTURE_DISABLED);
        m(outputFileOptions);
        this.f1348d.lambda$takePicture$5(outputFileOptions, executor, onImageSavedCallback);
    }

    @MainThread
    public void takePicture(@NonNull Executor executor, @NonNull ImageCapture.OnImageCapturedCallback onImageCapturedCallback) {
        Threads.checkMainThread();
        Preconditions.checkState(isCameraInitialized(), CAMERA_NOT_INITIALIZED);
        Preconditions.checkState(isImageCaptureEnabled(), IMAGE_CAPTURE_DISABLED);
        this.f1348d.lambda$takePicture$4(executor, onImageCapturedCallback);
    }
}
