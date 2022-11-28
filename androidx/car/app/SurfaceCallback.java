package androidx.car.app;

import android.graphics.Rect;
import androidx.annotation.NonNull;
import androidx.car.app.annotations.RequiresCarApi;
/* loaded from: classes.dex */
public interface SurfaceCallback {
    @RequiresCarApi(2)
    default void onFling(float f2, float f3) {
    }

    @RequiresCarApi(2)
    default void onScale(float f2, float f3, float f4) {
    }

    @RequiresCarApi(2)
    default void onScroll(float f2, float f3) {
    }

    default void onStableAreaChanged(@NonNull Rect rect) {
    }

    default void onSurfaceAvailable(@NonNull SurfaceContainer surfaceContainer) {
    }

    default void onSurfaceDestroyed(@NonNull SurfaceContainer surfaceContainer) {
    }

    default void onVisibleAreaChanged(@NonNull Rect rect) {
    }
}
