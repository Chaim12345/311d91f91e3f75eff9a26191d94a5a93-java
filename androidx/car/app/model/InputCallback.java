package androidx.car.app.model;

import androidx.annotation.NonNull;
import androidx.car.app.annotations.RequiresCarApi;
@RequiresCarApi(2)
/* loaded from: classes.dex */
public interface InputCallback {
    default void onInputSubmitted(@NonNull String str) {
    }

    default void onInputTextChanged(@NonNull String str) {
    }
}
