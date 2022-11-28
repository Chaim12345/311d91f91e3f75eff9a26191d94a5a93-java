package androidx.car.app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.car.app.serialization.Bundleable;
/* loaded from: classes.dex */
public interface OnDoneCallback {
    default void onFailure(@NonNull Bundleable bundleable) {
    }

    default void onSuccess(@Nullable Bundleable bundleable) {
    }
}
