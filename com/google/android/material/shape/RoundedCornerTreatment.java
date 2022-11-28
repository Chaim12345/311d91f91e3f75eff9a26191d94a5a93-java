package com.google.android.material.shape;

import androidx.annotation.NonNull;
/* loaded from: classes2.dex */
public class RoundedCornerTreatment extends CornerTreatment {

    /* renamed from: a  reason: collision with root package name */
    float f7458a;

    public RoundedCornerTreatment() {
        this.f7458a = -1.0f;
    }

    @Deprecated
    public RoundedCornerTreatment(float f2) {
        this.f7458a = -1.0f;
        this.f7458a = f2;
    }

    @Override // com.google.android.material.shape.CornerTreatment
    public void getCornerPath(@NonNull ShapePath shapePath, float f2, float f3, float f4) {
        shapePath.reset(0.0f, f4 * f3, 180.0f, 180.0f - f2);
        float f5 = f4 * 2.0f * f3;
        shapePath.addArc(0.0f, 0.0f, f5, f5, 180.0f, f2);
    }
}
