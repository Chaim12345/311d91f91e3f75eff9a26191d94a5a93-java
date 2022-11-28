package androidx.camera.core.impl;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes.dex */
public interface UseCaseConfigFactory {

    /* loaded from: classes.dex */
    public enum CaptureType {
        IMAGE_CAPTURE,
        PREVIEW,
        IMAGE_ANALYSIS,
        VIDEO_CAPTURE
    }

    /* loaded from: classes.dex */
    public interface Provider {
        @NonNull
        UseCaseConfigFactory newInstance(@NonNull Context context);
    }

    @Nullable
    Config getConfig(@NonNull CaptureType captureType);
}
