package androidx.car.app.model;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.car.app.OnDoneCallback;
/* loaded from: classes.dex */
public interface OnCheckedChangeDelegate {
    @SuppressLint({"ExecutorRegistration"})
    void sendCheckedChange(boolean z, @NonNull OnDoneCallback onDoneCallback);
}
