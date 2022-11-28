package androidx.car.app.model;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.car.app.OnDoneCallback;
import androidx.car.app.annotations.RequiresCarApi;
@RequiresCarApi(2)
/* loaded from: classes.dex */
public interface InputCallbackDelegate {
    @SuppressLint({"ExecutorRegistration"})
    void sendInputSubmitted(@NonNull String str, @NonNull OnDoneCallback onDoneCallback);

    @SuppressLint({"ExecutorRegistration"})
    void sendInputTextChanged(@NonNull String str, @NonNull OnDoneCallback onDoneCallback);
}
