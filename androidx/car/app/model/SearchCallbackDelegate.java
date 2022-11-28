package androidx.car.app.model;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.car.app.OnDoneCallback;
/* loaded from: classes.dex */
public interface SearchCallbackDelegate {
    @SuppressLint({"ExecutorRegistration"})
    void sendSearchSubmitted(@NonNull String str, @NonNull OnDoneCallback onDoneCallback);

    @SuppressLint({"ExecutorRegistration"})
    void sendSearchTextChanged(@NonNull String str, @NonNull OnDoneCallback onDoneCallback);
}
