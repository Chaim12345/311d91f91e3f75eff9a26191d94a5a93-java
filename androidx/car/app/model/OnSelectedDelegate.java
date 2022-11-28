package androidx.car.app.model;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.car.app.OnDoneCallback;
/* loaded from: classes.dex */
public interface OnSelectedDelegate {
    @SuppressLint({"ExecutorRegistration"})
    void sendSelected(int i2, @NonNull OnDoneCallback onDoneCallback);
}
