package com.google.android.material.shape;

import androidx.annotation.NonNull;
/* loaded from: classes2.dex */
public class EdgeTreatment {
    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a() {
        return false;
    }

    public void getEdgePath(float f2, float f3, float f4, @NonNull ShapePath shapePath) {
        shapePath.lineTo(f2, 0.0f);
    }

    @Deprecated
    public void getEdgePath(float f2, float f3, @NonNull ShapePath shapePath) {
        getEdgePath(f2, f2 / 2.0f, f3, shapePath);
    }
}
