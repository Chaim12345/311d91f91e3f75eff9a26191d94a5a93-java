package androidx.camera.core;

import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.camera.core.ForwardingImageProxy;
import androidx.camera.core.ImageAnalysisNonBlockingAnalyzer;
import androidx.camera.core.impl.ImageReaderProxy;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.Futures;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class ImageAnalysisNonBlockingAnalyzer extends ImageAnalysisAbstractAnalyzer {

    /* renamed from: b  reason: collision with root package name */
    final Executor f980b;
    @Nullable
    @GuardedBy("mLock")
    @VisibleForTesting

    /* renamed from: c  reason: collision with root package name */
    ImageProxy f981c;
    private final Object mLock = new Object();
    @Nullable
    @GuardedBy("mLock")
    private CacheAnalyzingImageProxy mPostedImage;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class CacheAnalyzingImageProxy extends ForwardingImageProxy {

        /* renamed from: b  reason: collision with root package name */
        final WeakReference f983b;

        CacheAnalyzingImageProxy(ImageProxy imageProxy, ImageAnalysisNonBlockingAnalyzer imageAnalysisNonBlockingAnalyzer) {
            super(imageProxy);
            this.f983b = new WeakReference(imageAnalysisNonBlockingAnalyzer);
            a(new ForwardingImageProxy.OnImageCloseListener() { // from class: androidx.camera.core.u
                @Override // androidx.camera.core.ForwardingImageProxy.OnImageCloseListener
                public final void onImageClose(ImageProxy imageProxy2) {
                    ImageAnalysisNonBlockingAnalyzer.CacheAnalyzingImageProxy.this.lambda$new$0(imageProxy2);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$new$0(ImageProxy imageProxy) {
            final ImageAnalysisNonBlockingAnalyzer imageAnalysisNonBlockingAnalyzer = (ImageAnalysisNonBlockingAnalyzer) this.f983b.get();
            if (imageAnalysisNonBlockingAnalyzer != null) {
                imageAnalysisNonBlockingAnalyzer.f980b.execute(new Runnable() { // from class: androidx.camera.core.v
                    @Override // java.lang.Runnable
                    public final void run() {
                        ImageAnalysisNonBlockingAnalyzer.this.k();
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ImageAnalysisNonBlockingAnalyzer(Executor executor) {
        this.f980b = executor;
    }

    @Override // androidx.camera.core.ImageAnalysisAbstractAnalyzer
    @Nullable
    ImageProxy c(@NonNull ImageReaderProxy imageReaderProxy) {
        return imageReaderProxy.acquireLatestImage();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.camera.core.ImageAnalysisAbstractAnalyzer
    public void f() {
        synchronized (this.mLock) {
            ImageProxy imageProxy = this.f981c;
            if (imageProxy != null) {
                imageProxy.close();
                this.f981c = null;
            }
        }
    }

    @Override // androidx.camera.core.ImageAnalysisAbstractAnalyzer
    void h(@NonNull ImageProxy imageProxy) {
        synchronized (this.mLock) {
            if (!this.f978a) {
                imageProxy.close();
            } else if (this.mPostedImage == null) {
                final CacheAnalyzingImageProxy cacheAnalyzingImageProxy = new CacheAnalyzingImageProxy(imageProxy, this);
                this.mPostedImage = cacheAnalyzingImageProxy;
                Futures.addCallback(d(cacheAnalyzingImageProxy), new FutureCallback<Void>(this) { // from class: androidx.camera.core.ImageAnalysisNonBlockingAnalyzer.1
                    @Override // androidx.camera.core.impl.utils.futures.FutureCallback
                    public void onFailure(Throwable th) {
                        cacheAnalyzingImageProxy.close();
                    }

                    @Override // androidx.camera.core.impl.utils.futures.FutureCallback
                    public void onSuccess(Void r1) {
                    }
                }, CameraXExecutors.directExecutor());
            } else {
                if (imageProxy.getImageInfo().getTimestamp() <= this.mPostedImage.getImageInfo().getTimestamp()) {
                    imageProxy.close();
                } else {
                    ImageProxy imageProxy2 = this.f981c;
                    if (imageProxy2 != null) {
                        imageProxy2.close();
                    }
                    this.f981c = imageProxy;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void k() {
        synchronized (this.mLock) {
            this.mPostedImage = null;
            ImageProxy imageProxy = this.f981c;
            if (imageProxy != null) {
                this.f981c = null;
                h(imageProxy);
            }
        }
    }
}
