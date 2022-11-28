package androidx.camera.core;

import android.media.ImageReader;
import android.util.Size;
import android.view.Surface;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.ProcessingImageReader;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.CaptureBundle;
import androidx.camera.core.impl.CaptureProcessor;
import androidx.camera.core.impl.CaptureStage;
import androidx.camera.core.impl.ImageReaderProxy;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ProcessingImageReader implements ImageReaderProxy {
    private static final String TAG = "ProcessingImageReader";

    /* renamed from: a  reason: collision with root package name */
    final Object f1030a;
    @GuardedBy("mLock")

    /* renamed from: b  reason: collision with root package name */
    boolean f1031b;
    @GuardedBy("mLock")

    /* renamed from: c  reason: collision with root package name */
    boolean f1032c;
    @GuardedBy("mLock")

    /* renamed from: d  reason: collision with root package name */
    final MetadataImageReader f1033d;
    @GuardedBy("mLock")

    /* renamed from: e  reason: collision with root package name */
    final ImageReaderProxy f1034e;
    @Nullable
    @GuardedBy("mLock")

    /* renamed from: f  reason: collision with root package name */
    ImageReaderProxy.OnImageAvailableListener f1035f;
    @Nullable
    @GuardedBy("mLock")

    /* renamed from: g  reason: collision with root package name */
    Executor f1036g;
    @GuardedBy("mLock")

    /* renamed from: h  reason: collision with root package name */
    CallbackToFutureAdapter.Completer f1037h;
    @NonNull

    /* renamed from: i  reason: collision with root package name */
    final Executor f1038i;
    @NonNull

    /* renamed from: j  reason: collision with root package name */
    final CaptureProcessor f1039j;
    @NonNull
    @GuardedBy("mLock")

    /* renamed from: k  reason: collision with root package name */
    SettableImageProxyBundle f1040k;
    private final List<Integer> mCaptureIdList;
    private FutureCallback<List<ImageProxy>> mCaptureStageReadyCallback;
    @GuardedBy("mLock")
    private ListenableFuture<Void> mCloseFuture;
    private ImageReaderProxy.OnImageAvailableListener mImageProcessedListener;
    private String mTagBundleKey;
    private ImageReaderProxy.OnImageAvailableListener mTransformedListener;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: androidx.camera.core.ProcessingImageReader$2  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass2 implements ImageReaderProxy.OnImageAvailableListener {
        AnonymousClass2() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onImageAvailable$0(ImageReaderProxy.OnImageAvailableListener onImageAvailableListener) {
            onImageAvailableListener.onImageAvailable(ProcessingImageReader.this);
        }

        @Override // androidx.camera.core.impl.ImageReaderProxy.OnImageAvailableListener
        public void onImageAvailable(@NonNull ImageReaderProxy imageReaderProxy) {
            final ImageReaderProxy.OnImageAvailableListener onImageAvailableListener;
            Executor executor;
            synchronized (ProcessingImageReader.this.f1030a) {
                ProcessingImageReader processingImageReader = ProcessingImageReader.this;
                onImageAvailableListener = processingImageReader.f1035f;
                executor = processingImageReader.f1036g;
                processingImageReader.f1040k.c();
                ProcessingImageReader.this.e();
            }
            if (onImageAvailableListener != null) {
                if (executor != null) {
                    executor.execute(new Runnable() { // from class: androidx.camera.core.d1
                        @Override // java.lang.Runnable
                        public final void run() {
                            ProcessingImageReader.AnonymousClass2.this.lambda$onImageAvailable$0(onImageAvailableListener);
                        }
                    });
                } else {
                    onImageAvailableListener.onImageAvailable(ProcessingImageReader.this);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProcessingImageReader(int i2, int i3, int i4, int i5, @NonNull Executor executor, @NonNull CaptureBundle captureBundle, @NonNull CaptureProcessor captureProcessor, int i6) {
        this(new MetadataImageReader(i2, i3, i4, i5), executor, captureBundle, captureProcessor, i6);
    }

    ProcessingImageReader(@NonNull MetadataImageReader metadataImageReader, @NonNull Executor executor, @NonNull CaptureBundle captureBundle, @NonNull CaptureProcessor captureProcessor, int i2) {
        this.f1030a = new Object();
        this.mTransformedListener = new ImageReaderProxy.OnImageAvailableListener() { // from class: androidx.camera.core.ProcessingImageReader.1
            @Override // androidx.camera.core.impl.ImageReaderProxy.OnImageAvailableListener
            public void onImageAvailable(@NonNull ImageReaderProxy imageReaderProxy) {
                ProcessingImageReader.this.d(imageReaderProxy);
            }
        };
        this.mImageProcessedListener = new AnonymousClass2();
        this.mCaptureStageReadyCallback = new FutureCallback<List<ImageProxy>>() { // from class: androidx.camera.core.ProcessingImageReader.3
            @Override // androidx.camera.core.impl.utils.futures.FutureCallback
            public void onFailure(Throwable th) {
            }

            @Override // androidx.camera.core.impl.utils.futures.FutureCallback
            public void onSuccess(@Nullable List<ImageProxy> list) {
                synchronized (ProcessingImageReader.this.f1030a) {
                    ProcessingImageReader processingImageReader = ProcessingImageReader.this;
                    if (processingImageReader.f1031b) {
                        return;
                    }
                    processingImageReader.f1032c = true;
                    processingImageReader.f1039j.process(processingImageReader.f1040k);
                    synchronized (ProcessingImageReader.this.f1030a) {
                        ProcessingImageReader processingImageReader2 = ProcessingImageReader.this;
                        processingImageReader2.f1032c = false;
                        if (processingImageReader2.f1031b) {
                            processingImageReader2.f1033d.close();
                            ProcessingImageReader.this.f1040k.b();
                            ProcessingImageReader.this.f1034e.close();
                            CallbackToFutureAdapter.Completer completer = ProcessingImageReader.this.f1037h;
                            if (completer != null) {
                                completer.set(null);
                            }
                        }
                    }
                }
            }
        };
        this.f1031b = false;
        this.f1032c = false;
        this.mTagBundleKey = new String();
        this.f1040k = new SettableImageProxyBundle(Collections.emptyList(), this.mTagBundleKey);
        this.mCaptureIdList = new ArrayList();
        if (metadataImageReader.getMaxImages() < captureBundle.getCaptureStages().size()) {
            throw new IllegalArgumentException("MetadataImageReader is smaller than CaptureBundle.");
        }
        this.f1033d = metadataImageReader;
        int width = metadataImageReader.getWidth();
        int height = metadataImageReader.getHeight();
        if (i2 == 256) {
            width = metadataImageReader.getWidth() * metadataImageReader.getHeight();
            height = 1;
        }
        AndroidImageReaderProxy androidImageReaderProxy = new AndroidImageReaderProxy(ImageReader.newInstance(width, height, i2, metadataImageReader.getMaxImages()));
        this.f1034e = androidImageReaderProxy;
        this.f1038i = executor;
        this.f1039j = captureProcessor;
        captureProcessor.onOutputSurface(androidImageReaderProxy.getSurface(), i2);
        captureProcessor.onResolutionUpdate(new Size(metadataImageReader.getWidth(), metadataImageReader.getHeight()));
        setCaptureBundle(captureBundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object lambda$getCloseFuture$0(CallbackToFutureAdapter.Completer completer) {
        synchronized (this.f1030a) {
            this.f1037h = completer;
        }
        return "ProcessingImageReader-close";
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    @Nullable
    public ImageProxy acquireLatestImage() {
        ImageProxy acquireLatestImage;
        synchronized (this.f1030a) {
            acquireLatestImage = this.f1034e.acquireLatestImage();
        }
        return acquireLatestImage;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    @Nullable
    public ImageProxy acquireNextImage() {
        ImageProxy acquireNextImage;
        synchronized (this.f1030a) {
            acquireNextImage = this.f1034e.acquireNextImage();
        }
        return acquireNextImage;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public CameraCaptureCallback b() {
        CameraCaptureCallback c2;
        synchronized (this.f1030a) {
            c2 = this.f1033d.c();
        }
        return c2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public ListenableFuture c() {
        ListenableFuture nonCancellationPropagating;
        synchronized (this.f1030a) {
            if (!this.f1031b || this.f1032c) {
                if (this.mCloseFuture == null) {
                    this.mCloseFuture = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.core.c1
                        @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
                        public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                            Object lambda$getCloseFuture$0;
                            lambda$getCloseFuture$0 = ProcessingImageReader.this.lambda$getCloseFuture$0(completer);
                            return lambda$getCloseFuture$0;
                        }
                    });
                }
                nonCancellationPropagating = Futures.nonCancellationPropagating(this.mCloseFuture);
            } else {
                nonCancellationPropagating = Futures.immediateFuture(null);
            }
        }
        return nonCancellationPropagating;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public void clearOnImageAvailableListener() {
        synchronized (this.f1030a) {
            this.f1035f = null;
            this.f1036g = null;
            this.f1033d.clearOnImageAvailableListener();
            this.f1034e.clearOnImageAvailableListener();
            if (!this.f1032c) {
                this.f1040k.b();
            }
        }
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public void close() {
        synchronized (this.f1030a) {
            if (this.f1031b) {
                return;
            }
            this.f1034e.clearOnImageAvailableListener();
            if (!this.f1032c) {
                this.f1033d.close();
                this.f1040k.b();
                this.f1034e.close();
                CallbackToFutureAdapter.Completer completer = this.f1037h;
                if (completer != null) {
                    completer.set(null);
                }
            }
            this.f1031b = true;
        }
    }

    void d(ImageReaderProxy imageReaderProxy) {
        synchronized (this.f1030a) {
            if (this.f1031b) {
                return;
            }
            try {
                ImageProxy acquireNextImage = imageReaderProxy.acquireNextImage();
                if (acquireNextImage != null) {
                    Integer tag = acquireNextImage.getImageInfo().getTagBundle().getTag(this.mTagBundleKey);
                    if (this.mCaptureIdList.contains(tag)) {
                        this.f1040k.a(acquireNextImage);
                    } else {
                        Logger.w(TAG, "ImageProxyBundle does not contain this id: " + tag);
                        acquireNextImage.close();
                    }
                }
            } catch (IllegalStateException e2) {
                Logger.e(TAG, "Failed to acquire latest image.", e2);
            }
        }
    }

    @GuardedBy("mLock")
    void e() {
        ArrayList arrayList = new ArrayList();
        for (Integer num : this.mCaptureIdList) {
            arrayList.add(this.f1040k.getImageProxy(num.intValue()));
        }
        Futures.addCallback(Futures.allAsList(arrayList), this.mCaptureStageReadyCallback, this.f1038i);
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public int getHeight() {
        int height;
        synchronized (this.f1030a) {
            height = this.f1033d.getHeight();
        }
        return height;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public int getImageFormat() {
        int imageFormat;
        synchronized (this.f1030a) {
            imageFormat = this.f1034e.getImageFormat();
        }
        return imageFormat;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public int getMaxImages() {
        int maxImages;
        synchronized (this.f1030a) {
            maxImages = this.f1033d.getMaxImages();
        }
        return maxImages;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    @Nullable
    public Surface getSurface() {
        Surface surface;
        synchronized (this.f1030a) {
            surface = this.f1033d.getSurface();
        }
        return surface;
    }

    @NonNull
    public String getTagBundleKey() {
        return this.mTagBundleKey;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public int getWidth() {
        int width;
        synchronized (this.f1030a) {
            width = this.f1033d.getWidth();
        }
        return width;
    }

    public void setCaptureBundle(@NonNull CaptureBundle captureBundle) {
        synchronized (this.f1030a) {
            if (captureBundle.getCaptureStages() != null) {
                if (this.f1033d.getMaxImages() < captureBundle.getCaptureStages().size()) {
                    throw new IllegalArgumentException("CaptureBundle is larger than InputImageReader.");
                }
                this.mCaptureIdList.clear();
                for (CaptureStage captureStage : captureBundle.getCaptureStages()) {
                    if (captureStage != null) {
                        this.mCaptureIdList.add(Integer.valueOf(captureStage.getId()));
                    }
                }
            }
            String num = Integer.toString(captureBundle.hashCode());
            this.mTagBundleKey = num;
            this.f1040k = new SettableImageProxyBundle(this.mCaptureIdList, num);
            e();
        }
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public void setOnImageAvailableListener(@NonNull ImageReaderProxy.OnImageAvailableListener onImageAvailableListener, @NonNull Executor executor) {
        synchronized (this.f1030a) {
            this.f1035f = (ImageReaderProxy.OnImageAvailableListener) Preconditions.checkNotNull(onImageAvailableListener);
            this.f1036g = (Executor) Preconditions.checkNotNull(executor);
            this.f1033d.setOnImageAvailableListener(this.mTransformedListener, executor);
            this.f1034e.setOnImageAvailableListener(this.mImageProcessedListener, executor);
        }
    }
}
