package androidx.car.app.hardware.common;

import androidx.annotation.NonNull;
import androidx.car.app.annotations.RequiresCarApi;
@RequiresCarApi(3)
/* loaded from: classes.dex */
public interface OnCarDataAvailableListener<T> {
    void onCarDataAvailable(@NonNull T t2);
}
