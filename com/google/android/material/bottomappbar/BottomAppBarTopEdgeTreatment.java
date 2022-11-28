package com.google.android.material.bottomappbar;

import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import com.google.android.material.shape.EdgeTreatment;
import com.google.android.material.shape.ShapePath;
/* loaded from: classes2.dex */
public class BottomAppBarTopEdgeTreatment extends EdgeTreatment implements Cloneable {
    private static final int ANGLE_LEFT = 180;
    private static final int ANGLE_UP = 270;
    private static final int ARC_HALF = 180;
    private static final int ARC_QUARTER = 90;
    private static final float ROUNDED_CORNER_FAB_OFFSET = 1.75f;
    private float cradleVerticalOffset;
    private float fabCornerSize = -1.0f;
    private float fabDiameter;
    private float fabMargin;
    private float horizontalOffset;
    private float roundedCornerRadius;

    public BottomAppBarTopEdgeTreatment(float f2, float f3, float f4) {
        this.fabMargin = f2;
        this.roundedCornerRadius = f3;
        e(f4);
        this.horizontalOffset = 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float b() {
        return this.cradleVerticalOffset;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float c() {
        return this.fabMargin;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float d() {
        return this.roundedCornerRadius;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(@FloatRange(from = 0.0d) float f2) {
        if (f2 < 0.0f) {
            throw new IllegalArgumentException("cradleVerticalOffset must be positive.");
        }
        this.cradleVerticalOffset = f2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f(float f2) {
        this.fabMargin = f2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g(float f2) {
        this.roundedCornerRadius = f2;
    }

    @Override // com.google.android.material.shape.EdgeTreatment
    public void getEdgePath(float f2, float f3, float f4, @NonNull ShapePath shapePath) {
        float f5;
        float f6;
        float f7;
        float f8;
        float f9;
        float f10;
        float f11;
        float f12;
        float f13 = this.fabDiameter;
        if (f13 == 0.0f) {
            shapePath.lineTo(f2, 0.0f);
            return;
        }
        float f14 = ((this.fabMargin * 2.0f) + f13) / 2.0f;
        float f15 = f4 * this.roundedCornerRadius;
        float f16 = f3 + this.horizontalOffset;
        float f17 = (this.cradleVerticalOffset * f4) + ((1.0f - f4) * f14);
        if (f17 / f14 >= 1.0f) {
            shapePath.lineTo(f2, 0.0f);
            return;
        }
        float f18 = this.fabCornerSize;
        float f19 = f18 * f4;
        boolean z = f18 == -1.0f || Math.abs((f18 * 2.0f) - f13) < 0.1f;
        if (z) {
            f5 = f17;
            f6 = 0.0f;
        } else {
            f6 = 1.75f;
            f5 = 0.0f;
        }
        float f20 = f14 + f15;
        float f21 = f5 + f15;
        float sqrt = (float) Math.sqrt((f20 * f20) - (f21 * f21));
        float f22 = f16 - sqrt;
        float f23 = f16 + sqrt;
        float degrees = (float) Math.toDegrees(Math.atan(sqrt / f21));
        float f24 = (90.0f - degrees) + f6;
        shapePath.lineTo(f22, 0.0f);
        float f25 = f15 * 2.0f;
        shapePath.addArc(f22 - f15, 0.0f, f22 + f15, f25, 270.0f, degrees);
        if (z) {
            f8 = f16 - f14;
            f9 = (-f14) - f5;
            f7 = f16 + f14;
            f10 = f14 - f5;
            f11 = 180.0f - f24;
            f12 = (f24 * 2.0f) - 180.0f;
        } else {
            float f26 = this.fabMargin;
            float f27 = f19 * 2.0f;
            float f28 = f16 - f14;
            shapePath.addArc(f28, -(f19 + f26), f28 + f26 + f27, f26 + f19, 180.0f - f24, ((f24 * 2.0f) - 180.0f) / 2.0f);
            f7 = f16 + f14;
            float f29 = this.fabMargin;
            shapePath.lineTo(f7 - ((f29 / 2.0f) + f19), f29 + f19);
            float f30 = this.fabMargin;
            f8 = f7 - (f27 + f30);
            f9 = -(f19 + f30);
            f10 = f30 + f19;
            f11 = 90.0f;
            f12 = f24 - 90.0f;
        }
        shapePath.addArc(f8, f9, f7, f10, f11, f12);
        shapePath.addArc(f23 - f15, 0.0f, f23 + f15, f25, 270.0f - degrees, degrees);
        shapePath.lineTo(f2, 0.0f);
    }

    public float getFabCornerRadius() {
        return this.fabCornerSize;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public float getFabDiameter() {
        return this.fabDiameter;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public float getHorizontalOffset() {
        return this.horizontalOffset;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void h(float f2) {
        this.horizontalOffset = f2;
    }

    public void setFabCornerSize(float f2) {
        this.fabCornerSize = f2;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setFabDiameter(float f2) {
        this.fabDiameter = f2;
    }
}
