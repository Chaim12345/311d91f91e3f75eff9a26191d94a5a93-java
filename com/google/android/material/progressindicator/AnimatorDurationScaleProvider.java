package com.google.android.material.progressindicator;

import android.content.ContentResolver;
import android.os.Build;
import android.provider.Settings;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes2.dex */
public class AnimatorDurationScaleProvider {
    private static float defaultSystemAnimatorDurationScale = 1.0f;

    @VisibleForTesting
    public static void setDefaultSystemAnimatorDurationScale(float f2) {
        defaultSystemAnimatorDurationScale = f2;
    }

    public float getSystemAnimatorDurationScale(@NonNull ContentResolver contentResolver) {
        int i2 = Build.VERSION.SDK_INT;
        return i2 >= 17 ? Settings.Global.getFloat(contentResolver, "animator_duration_scale", 1.0f) : i2 == 16 ? Settings.System.getFloat(contentResolver, "animator_duration_scale", 1.0f) : defaultSystemAnimatorDurationScale;
    }
}
