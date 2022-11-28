package com.google.android.material.shape;

import androidx.annotation.NonNull;
/* loaded from: classes2.dex */
public class CutCornerTreatment extends CornerTreatment {

    /* renamed from: a  reason: collision with root package name */
    float f7454a;

    public CutCornerTreatment() {
        this.f7454a = -1.0f;
    }

    @Deprecated
    public CutCornerTreatment(float f2) {
        this.f7454a = -1.0f;
        this.f7454a = f2;
    }

    @Override // com.google.android.material.shape.CornerTreatment
    public void getCornerPath(@NonNull ShapePath shapePath, float f2, float f3, float f4) {
        shapePath.reset(0.0f, f4 * f3, 180.0f, 180.0f - f2);
        double d2 = f4;
        double d3 = f3;
        shapePath.lineTo((float) (Math.sin(Math.toRadians(f2)) * d2 * d3), (float) (Math.sin(Math.toRadians(90.0f - f2)) * d2 * d3));
    }
}
