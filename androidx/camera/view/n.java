package androidx.camera.view;

import android.view.PixelCopy;
/* loaded from: classes.dex */
public final /* synthetic */ class n implements PixelCopy.OnPixelCopyFinishedListener {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ n f1432a = new n();

    private /* synthetic */ n() {
    }

    @Override // android.view.PixelCopy.OnPixelCopyFinishedListener
    public final void onPixelCopyFinished(int i2) {
        SurfaceViewImplementation.lambda$getPreviewBitmap$1(i2);
    }
}
