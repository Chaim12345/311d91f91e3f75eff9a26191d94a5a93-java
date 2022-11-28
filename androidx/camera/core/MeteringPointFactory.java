package androidx.camera.core;

import android.graphics.PointF;
import android.util.Rational;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
/* loaded from: classes.dex */
public abstract class MeteringPointFactory {
    @Nullable
    private Rational mSurfaceAspectRatio;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public MeteringPointFactory() {
        this(null);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public MeteringPointFactory(@Nullable Rational rational) {
        this.mSurfaceAspectRatio = rational;
    }

    public static float getDefaultPointSize() {
        return 0.15f;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    protected abstract PointF a(float f2, float f3);

    @NonNull
    public final MeteringPoint createPoint(float f2, float f3) {
        return createPoint(f2, f3, getDefaultPointSize());
    }

    @NonNull
    public final MeteringPoint createPoint(float f2, float f3, float f4) {
        PointF a2 = a(f2, f3);
        return new MeteringPoint(a2.x, a2.y, f4, this.mSurfaceAspectRatio);
    }
}
