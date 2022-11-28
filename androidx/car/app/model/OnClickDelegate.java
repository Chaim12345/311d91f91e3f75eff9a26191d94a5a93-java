package androidx.car.app.model;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.car.app.OnDoneCallback;
/* loaded from: classes.dex */
public interface OnClickDelegate {
    boolean isParkedOnly();

    @SuppressLint({"ExecutorRegistration"})
    void sendClick(@NonNull OnDoneCallback onDoneCallback);
}
