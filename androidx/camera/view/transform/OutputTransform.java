package androidx.camera.view.transform;

import android.graphics.Matrix;
import android.util.Size;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.camera.view.TransformExperimental;
@TransformExperimental
/* loaded from: classes.dex */
public final class OutputTransform {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    final Matrix f1440a;
    @NonNull

    /* renamed from: b  reason: collision with root package name */
    final Size f1441b;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public OutputTransform(@NonNull Matrix matrix, @NonNull Size size) {
        this.f1440a = matrix;
        this.f1441b = size;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public Matrix a() {
        return this.f1440a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public Size b() {
        return this.f1441b;
    }
}
