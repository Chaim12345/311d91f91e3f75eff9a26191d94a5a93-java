package androidx.car.app.model;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.car.app.OnDoneCallback;
/* loaded from: classes.dex */
public interface OnItemVisibilityChangedDelegate {
    @SuppressLint({"ExecutorRegistration"})
    void sendItemVisibilityChanged(int i2, int i3, @NonNull OnDoneCallback onDoneCallback);
}
