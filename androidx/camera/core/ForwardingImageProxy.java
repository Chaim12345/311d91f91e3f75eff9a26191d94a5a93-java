package androidx.camera.core;

import android.graphics.Rect;
import android.media.Image;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.ImageProxy;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes.dex */
abstract class ForwardingImageProxy implements ImageProxy {
    @GuardedBy("this")

    /* renamed from: a  reason: collision with root package name */
    protected final ImageProxy f976a;
    @GuardedBy("this")
    private final Set<OnImageCloseListener> mOnImageCloseListeners = new HashSet();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface OnImageCloseListener {
        void onImageClose(ImageProxy imageProxy);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ForwardingImageProxy(ImageProxy imageProxy) {
        this.f976a = imageProxy;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void a(OnImageCloseListener onImageCloseListener) {
        this.mOnImageCloseListeners.add(onImageCloseListener);
    }

    protected void b() {
        HashSet<OnImageCloseListener> hashSet;
        synchronized (this) {
            hashSet = new HashSet(this.mOnImageCloseListeners);
        }
        for (OnImageCloseListener onImageCloseListener : hashSet) {
            onImageCloseListener.onImageClose(this);
        }
    }

    @Override // androidx.camera.core.ImageProxy, java.lang.AutoCloseable
    public void close() {
        synchronized (this) {
            this.f976a.close();
        }
        b();
    }

    @Override // androidx.camera.core.ImageProxy
    @NonNull
    public synchronized Rect getCropRect() {
        return this.f976a.getCropRect();
    }

    @Override // androidx.camera.core.ImageProxy
    public synchronized int getFormat() {
        return this.f976a.getFormat();
    }

    @Override // androidx.camera.core.ImageProxy
    public synchronized int getHeight() {
        return this.f976a.getHeight();
    }

    @Override // androidx.camera.core.ImageProxy
    @ExperimentalGetImage
    public synchronized Image getImage() {
        return this.f976a.getImage();
    }

    @Override // androidx.camera.core.ImageProxy
    @NonNull
    public synchronized ImageInfo getImageInfo() {
        return this.f976a.getImageInfo();
    }

    @Override // androidx.camera.core.ImageProxy
    @NonNull
    public synchronized ImageProxy.PlaneProxy[] getPlanes() {
        return this.f976a.getPlanes();
    }

    @Override // androidx.camera.core.ImageProxy
    public synchronized int getWidth() {
        return this.f976a.getWidth();
    }

    @Override // androidx.camera.core.ImageProxy
    public synchronized void setCropRect(@Nullable Rect rect) {
        this.f976a.setCropRect(rect);
    }
}
