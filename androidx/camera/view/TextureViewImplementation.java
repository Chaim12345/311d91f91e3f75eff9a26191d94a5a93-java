package androidx.camera.view;

import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.util.Size;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.Logger;
import androidx.camera.core.SurfaceRequest;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.camera.view.PreviewViewImplementation;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.content.ContextCompat;
import androidx.core.util.Consumer;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class TextureViewImplementation extends PreviewViewImplementation {
    private static final String TAG = "TextureViewImpl";

    /* renamed from: c  reason: collision with root package name */
    TextureView f1398c;

    /* renamed from: d  reason: collision with root package name */
    SurfaceTexture f1399d;

    /* renamed from: e  reason: collision with root package name */
    ListenableFuture f1400e;

    /* renamed from: f  reason: collision with root package name */
    SurfaceRequest f1401f;

    /* renamed from: g  reason: collision with root package name */
    boolean f1402g;

    /* renamed from: h  reason: collision with root package name */
    SurfaceTexture f1403h;

    /* renamed from: i  reason: collision with root package name */
    AtomicReference f1404i;
    @Nullable

    /* renamed from: j  reason: collision with root package name */
    PreviewViewImplementation.OnSurfaceNotInUseListener f1405j;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TextureViewImplementation(@NonNull FrameLayout frameLayout, @NonNull PreviewTransformation previewTransformation) {
        super(frameLayout, previewTransformation);
        this.f1402g = false;
        this.f1404i = new AtomicReference();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onSurfaceRequested$0(SurfaceRequest surfaceRequest) {
        SurfaceRequest surfaceRequest2 = this.f1401f;
        if (surfaceRequest2 != null && surfaceRequest2 == surfaceRequest) {
            this.f1401f = null;
            this.f1400e = null;
        }
        notifySurfaceNotInUse();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object lambda$tryToProvidePreviewSurface$1(Surface surface, final CallbackToFutureAdapter.Completer completer) {
        Logger.d(TAG, "Surface set on Preview.");
        SurfaceRequest surfaceRequest = this.f1401f;
        Executor directExecutor = CameraXExecutors.directExecutor();
        Objects.requireNonNull(completer);
        surfaceRequest.provideSurface(surface, directExecutor, new Consumer() { // from class: androidx.camera.view.u
            @Override // androidx.core.util.Consumer
            public final void accept(Object obj) {
                CallbackToFutureAdapter.Completer.this.set((SurfaceRequest.Result) obj);
            }
        });
        return "provideSurface[request=" + this.f1401f + " surface=" + surface + "]";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$tryToProvidePreviewSurface$2(Surface surface, ListenableFuture listenableFuture, SurfaceRequest surfaceRequest) {
        Logger.d(TAG, "Safe to release surface.");
        notifySurfaceNotInUse();
        surface.release();
        if (this.f1400e == listenableFuture) {
            this.f1400e = null;
        }
        if (this.f1401f == surfaceRequest) {
            this.f1401f = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object lambda$waitForNextFrame$3(CallbackToFutureAdapter.Completer completer) {
        this.f1404i.set(completer);
        return "textureViewImpl_waitForNextFrame";
    }

    private void notifySurfaceNotInUse() {
        PreviewViewImplementation.OnSurfaceNotInUseListener onSurfaceNotInUseListener = this.f1405j;
        if (onSurfaceNotInUseListener != null) {
            onSurfaceNotInUseListener.onSurfaceNotInUse();
            this.f1405j = null;
        }
    }

    private void reattachSurfaceTexture() {
        if (!this.f1402g || this.f1403h == null) {
            return;
        }
        SurfaceTexture surfaceTexture = this.f1398c.getSurfaceTexture();
        SurfaceTexture surfaceTexture2 = this.f1403h;
        if (surfaceTexture != surfaceTexture2) {
            this.f1398c.setSurfaceTexture(surfaceTexture2);
            this.f1403h = null;
            this.f1402g = false;
        }
    }

    @Override // androidx.camera.view.PreviewViewImplementation
    @Nullable
    View b() {
        return this.f1398c;
    }

    @Override // androidx.camera.view.PreviewViewImplementation
    @Nullable
    Bitmap c() {
        TextureView textureView = this.f1398c;
        if (textureView == null || !textureView.isAvailable()) {
            return null;
        }
        return this.f1398c.getBitmap();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.camera.view.PreviewViewImplementation
    public void d() {
        reattachSurfaceTexture();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.camera.view.PreviewViewImplementation
    public void e() {
        this.f1402g = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.camera.view.PreviewViewImplementation
    public void g(@NonNull final SurfaceRequest surfaceRequest, @Nullable PreviewViewImplementation.OnSurfaceNotInUseListener onSurfaceNotInUseListener) {
        this.f1386a = surfaceRequest.getResolution();
        this.f1405j = onSurfaceNotInUseListener;
        initializePreview();
        SurfaceRequest surfaceRequest2 = this.f1401f;
        if (surfaceRequest2 != null) {
            surfaceRequest2.willNotProvideSurface();
        }
        this.f1401f = surfaceRequest;
        surfaceRequest.addRequestCancellationListener(ContextCompat.getMainExecutor(this.f1398c.getContext()), new Runnable() { // from class: androidx.camera.view.w
            @Override // java.lang.Runnable
            public final void run() {
                TextureViewImplementation.this.lambda$onSurfaceRequested$0(surfaceRequest);
            }
        });
        n();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.camera.view.PreviewViewImplementation
    @NonNull
    public ListenableFuture i() {
        return CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.view.s
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                Object lambda$waitForNextFrame$3;
                lambda$waitForNextFrame$3 = TextureViewImplementation.this.lambda$waitForNextFrame$3(completer);
                return lambda$waitForNextFrame$3;
            }
        });
    }

    public void initializePreview() {
        Preconditions.checkNotNull(this.f1387b);
        Preconditions.checkNotNull(this.f1386a);
        TextureView textureView = new TextureView(this.f1387b.getContext());
        this.f1398c = textureView;
        textureView.setLayoutParams(new FrameLayout.LayoutParams(this.f1386a.getWidth(), this.f1386a.getHeight()));
        this.f1398c.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() { // from class: androidx.camera.view.TextureViewImplementation.1
            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surfaceTexture, int i2, int i3) {
                Logger.d(TextureViewImplementation.TAG, "SurfaceTexture available. Size: " + i2 + "x" + i3);
                TextureViewImplementation textureViewImplementation = TextureViewImplementation.this;
                textureViewImplementation.f1399d = surfaceTexture;
                if (textureViewImplementation.f1400e == null) {
                    textureViewImplementation.n();
                    return;
                }
                Preconditions.checkNotNull(textureViewImplementation.f1401f);
                Logger.d(TextureViewImplementation.TAG, "Surface invalidated " + TextureViewImplementation.this.f1401f);
                TextureViewImplementation.this.f1401f.getDeferrableSurface().close();
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public boolean onSurfaceTextureDestroyed(@NonNull final SurfaceTexture surfaceTexture) {
                TextureViewImplementation textureViewImplementation = TextureViewImplementation.this;
                textureViewImplementation.f1399d = null;
                ListenableFuture listenableFuture = textureViewImplementation.f1400e;
                if (listenableFuture == null) {
                    Logger.d(TextureViewImplementation.TAG, "SurfaceTexture about to be destroyed");
                    return true;
                }
                Futures.addCallback(listenableFuture, new FutureCallback<SurfaceRequest.Result>() { // from class: androidx.camera.view.TextureViewImplementation.1.1
                    @Override // androidx.camera.core.impl.utils.futures.FutureCallback
                    public void onFailure(Throwable th) {
                        throw new IllegalStateException("SurfaceReleaseFuture did not complete nicely.", th);
                    }

                    @Override // androidx.camera.core.impl.utils.futures.FutureCallback
                    public void onSuccess(SurfaceRequest.Result result) {
                        Preconditions.checkState(result.getResultCode() != 3, "Unexpected result from SurfaceRequest. Surface was provided twice.");
                        Logger.d(TextureViewImplementation.TAG, "SurfaceTexture about to manually be destroyed");
                        surfaceTexture.release();
                        TextureViewImplementation textureViewImplementation2 = TextureViewImplementation.this;
                        if (textureViewImplementation2.f1403h != null) {
                            textureViewImplementation2.f1403h = null;
                        }
                    }
                }, ContextCompat.getMainExecutor(TextureViewImplementation.this.f1398c.getContext()));
                TextureViewImplementation.this.f1403h = surfaceTexture;
                return false;
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureSizeChanged(@NonNull SurfaceTexture surfaceTexture, int i2, int i3) {
                Logger.d(TextureViewImplementation.TAG, "SurfaceTexture size changed: " + i2 + "x" + i3);
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureUpdated(@NonNull SurfaceTexture surfaceTexture) {
                CallbackToFutureAdapter.Completer completer = (CallbackToFutureAdapter.Completer) TextureViewImplementation.this.f1404i.getAndSet(null);
                if (completer != null) {
                    completer.set(null);
                }
            }
        });
        this.f1387b.removeAllViews();
        this.f1387b.addView(this.f1398c);
    }

    void n() {
        SurfaceTexture surfaceTexture;
        Size size = this.f1386a;
        if (size == null || (surfaceTexture = this.f1399d) == null || this.f1401f == null) {
            return;
        }
        surfaceTexture.setDefaultBufferSize(size.getWidth(), this.f1386a.getHeight());
        final Surface surface = new Surface(this.f1399d);
        final SurfaceRequest surfaceRequest = this.f1401f;
        final ListenableFuture future = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.view.t
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                Object lambda$tryToProvidePreviewSurface$1;
                lambda$tryToProvidePreviewSurface$1 = TextureViewImplementation.this.lambda$tryToProvidePreviewSurface$1(surface, completer);
                return lambda$tryToProvidePreviewSurface$1;
            }
        });
        this.f1400e = future;
        future.addListener(new Runnable() { // from class: androidx.camera.view.v
            @Override // java.lang.Runnable
            public final void run() {
                TextureViewImplementation.this.lambda$tryToProvidePreviewSurface$2(surface, future, surfaceRequest);
            }
        }, ContextCompat.getMainExecutor(this.f1398c.getContext()));
        f();
    }
}
