package androidx.camera.view;

import android.graphics.Bitmap;
import android.util.Size;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.SurfaceRequest;
import com.google.common.util.concurrent.ListenableFuture;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class PreviewViewImplementation {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    Size f1386a;
    @NonNull

    /* renamed from: b  reason: collision with root package name */
    FrameLayout f1387b;
    @NonNull
    private final PreviewTransformation mPreviewTransform;
    private boolean mWasSurfaceProvided = false;

    /* loaded from: classes.dex */
    interface OnSurfaceNotInUseListener {
        void onSurfaceNotInUse();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PreviewViewImplementation(@NonNull FrameLayout frameLayout, @NonNull PreviewTransformation previewTransformation) {
        this.f1387b = frameLayout;
        this.mPreviewTransform = previewTransformation;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public Bitmap a() {
        Bitmap c2 = c();
        if (c2 == null) {
            return null;
        }
        return this.mPreviewTransform.a(c2, new Size(this.f1387b.getWidth(), this.f1387b.getHeight()), this.f1387b.getLayoutDirection());
    }

    @Nullable
    abstract View b();

    @Nullable
    abstract Bitmap c();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void d();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void e();

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f() {
        this.mWasSurfaceProvided = true;
        h();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void g(@NonNull SurfaceRequest surfaceRequest, @Nullable OnSurfaceNotInUseListener onSurfaceNotInUseListener);

    /* JADX INFO: Access modifiers changed from: package-private */
    public void h() {
        View b2 = b();
        if (b2 == null || !this.mWasSurfaceProvided) {
            return;
        }
        this.mPreviewTransform.k(new Size(this.f1387b.getWidth(), this.f1387b.getHeight()), this.f1387b.getLayoutDirection(), b2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public abstract ListenableFuture i();
}
