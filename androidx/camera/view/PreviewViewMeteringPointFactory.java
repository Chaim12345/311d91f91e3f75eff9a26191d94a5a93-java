package androidx.camera.view;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.Size;
import androidx.annotation.AnyThread;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.UiThread;
import androidx.camera.core.MeteringPointFactory;
import androidx.camera.core.impl.utils.Threads;
/* JADX INFO: Access modifiers changed from: package-private */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class PreviewViewMeteringPointFactory extends MeteringPointFactory {

    /* renamed from: a  reason: collision with root package name */
    static final PointF f1388a = new PointF(2.0f, 2.0f);
    @Nullable
    @GuardedBy("this")
    private Matrix mMatrix;
    @NonNull
    private final PreviewTransformation mPreviewTransformation;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PreviewViewMeteringPointFactory(@NonNull PreviewTransformation previewTransformation) {
        this.mPreviewTransformation = previewTransformation;
    }

    @Override // androidx.camera.core.MeteringPointFactory
    @NonNull
    @AnyThread
    protected PointF a(float f2, float f3) {
        float[] fArr = {f2, f3};
        synchronized (this) {
            Matrix matrix = this.mMatrix;
            if (matrix == null) {
                return f1388a;
            }
            matrix.mapPoints(fArr);
            return new PointF(fArr[0], fArr[1]);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @UiThread
    public void b(@NonNull Size size, int i2) {
        Threads.checkMainThread();
        synchronized (this) {
            if (size.getWidth() != 0 && size.getHeight() != 0) {
                this.mMatrix = this.mPreviewTransformation.b(size, i2);
                return;
            }
            this.mMatrix = null;
        }
    }
}
