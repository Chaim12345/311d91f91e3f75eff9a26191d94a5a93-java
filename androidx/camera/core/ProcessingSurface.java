package androidx.camera.core;

import android.os.Handler;
import android.os.Looper;
import android.util.Size;
import android.view.Surface;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.CaptureProcessor;
import androidx.camera.core.impl.CaptureStage;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.ImageReaderProxy;
import androidx.camera.core.impl.SingleImageProxyBundle;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.ScheduledExecutorService;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class ProcessingSurface extends DeferrableSurface {
    private static final int MAX_IMAGES = 2;
    private static final String TAG = "ProcessingSurfaceTextur";

    /* renamed from: a  reason: collision with root package name */
    final Object f1044a = new Object();
    @GuardedBy("mLock")

    /* renamed from: b  reason: collision with root package name */
    boolean f1045b;
    @GuardedBy("mLock")

    /* renamed from: c  reason: collision with root package name */
    final MetadataImageReader f1046c;
    @GuardedBy("mLock")

    /* renamed from: d  reason: collision with root package name */
    final Surface f1047d;

    /* renamed from: e  reason: collision with root package name */
    final CaptureStage f1048e;
    @NonNull
    @GuardedBy("mLock")

    /* renamed from: f  reason: collision with root package name */
    final CaptureProcessor f1049f;
    private final CameraCaptureCallback mCameraCaptureCallback;
    private final Handler mImageReaderHandler;
    private final DeferrableSurface mOutputDeferrableSurface;
    @NonNull
    private final Size mResolution;
    private String mTagBundleKey;
    private final ImageReaderProxy.OnImageAvailableListener mTransformedListener;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProcessingSurface(int i2, int i3, int i4, @Nullable Handler handler, @NonNull CaptureStage captureStage, @NonNull CaptureProcessor captureProcessor, @NonNull DeferrableSurface deferrableSurface, @NonNull String str) {
        ImageReaderProxy.OnImageAvailableListener onImageAvailableListener = new ImageReaderProxy.OnImageAvailableListener() { // from class: androidx.camera.core.e1
            @Override // androidx.camera.core.impl.ImageReaderProxy.OnImageAvailableListener
            public final void onImageAvailable(ImageReaderProxy imageReaderProxy) {
                ProcessingSurface.this.lambda$new$0(imageReaderProxy);
            }
        };
        this.mTransformedListener = onImageAvailableListener;
        this.f1045b = false;
        Size size = new Size(i2, i3);
        this.mResolution = size;
        if (handler != null) {
            this.mImageReaderHandler = handler;
        } else {
            Looper myLooper = Looper.myLooper();
            if (myLooper == null) {
                throw new IllegalStateException("Creating a ProcessingSurface requires a non-null Handler, or be created  on a thread with a Looper.");
            }
            this.mImageReaderHandler = new Handler(myLooper);
        }
        ScheduledExecutorService newHandlerExecutor = CameraXExecutors.newHandlerExecutor(this.mImageReaderHandler);
        MetadataImageReader metadataImageReader = new MetadataImageReader(i2, i3, i4, 2);
        this.f1046c = metadataImageReader;
        metadataImageReader.setOnImageAvailableListener(onImageAvailableListener, newHandlerExecutor);
        this.f1047d = metadataImageReader.getSurface();
        this.mCameraCaptureCallback = metadataImageReader.c();
        this.f1049f = captureProcessor;
        captureProcessor.onResolutionUpdate(size);
        this.f1048e = captureStage;
        this.mOutputDeferrableSurface = deferrableSurface;
        this.mTagBundleKey = str;
        Futures.addCallback(deferrableSurface.getSurface(), new FutureCallback<Surface>() { // from class: androidx.camera.core.ProcessingSurface.1
            @Override // androidx.camera.core.impl.utils.futures.FutureCallback
            public void onFailure(Throwable th) {
                Logger.e(ProcessingSurface.TAG, "Failed to extract Listenable<Surface>.", th);
            }

            @Override // androidx.camera.core.impl.utils.futures.FutureCallback
            public void onSuccess(@Nullable Surface surface) {
                synchronized (ProcessingSurface.this.f1044a) {
                    ProcessingSurface.this.f1049f.onOutputSurface(surface, 1);
                }
            }
        }, CameraXExecutors.directExecutor());
        getTerminationFuture().addListener(new Runnable() { // from class: androidx.camera.core.f1
            @Override // java.lang.Runnable
            public final void run() {
                ProcessingSurface.this.release();
            }
        }, CameraXExecutors.directExecutor());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(ImageReaderProxy imageReaderProxy) {
        synchronized (this.f1044a) {
            f(imageReaderProxy);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void release() {
        synchronized (this.f1044a) {
            if (this.f1045b) {
                return;
            }
            this.f1046c.close();
            this.f1047d.release();
            this.mOutputDeferrableSurface.close();
            this.f1045b = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public CameraCaptureCallback e() {
        CameraCaptureCallback cameraCaptureCallback;
        synchronized (this.f1044a) {
            if (this.f1045b) {
                throw new IllegalStateException("ProcessingSurface already released!");
            }
            cameraCaptureCallback = this.mCameraCaptureCallback;
        }
        return cameraCaptureCallback;
    }

    @GuardedBy("mLock")
    void f(ImageReaderProxy imageReaderProxy) {
        if (this.f1045b) {
            return;
        }
        ImageProxy imageProxy = null;
        try {
            imageProxy = imageReaderProxy.acquireNextImage();
        } catch (IllegalStateException e2) {
            Logger.e(TAG, "Failed to acquire next image.", e2);
        }
        if (imageProxy == null) {
            return;
        }
        ImageInfo imageInfo = imageProxy.getImageInfo();
        if (imageInfo == null) {
            imageProxy.close();
            return;
        }
        Integer tag = imageInfo.getTagBundle().getTag(this.mTagBundleKey);
        if (tag == null) {
            imageProxy.close();
        } else if (this.f1048e.getId() == tag.intValue()) {
            SingleImageProxyBundle singleImageProxyBundle = new SingleImageProxyBundle(imageProxy, this.mTagBundleKey);
            this.f1049f.process(singleImageProxyBundle);
            singleImageProxyBundle.close();
        } else {
            Logger.w(TAG, "ImageProxyBundle does not contain this id: " + tag);
            imageProxy.close();
        }
    }

    @Override // androidx.camera.core.impl.DeferrableSurface
    @NonNull
    public ListenableFuture<Surface> provideSurface() {
        ListenableFuture<Surface> immediateFuture;
        synchronized (this.f1044a) {
            immediateFuture = Futures.immediateFuture(this.f1047d);
        }
        return immediateFuture;
    }
}
