package androidx.car.app.navigation.model;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.car.app.OnDoneCallback;
import androidx.car.app.annotations.RequiresCarApi;
@RequiresCarApi(2)
/* loaded from: classes.dex */
public interface PanModeDelegate {
    @SuppressLint({"ExecutorRegistration"})
    void sendPanModeChanged(boolean z, @NonNull OnDoneCallback onDoneCallback);
}
