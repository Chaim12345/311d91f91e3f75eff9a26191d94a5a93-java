package androidx.car.app.managers;

import android.content.ComponentName;
import android.content.Intent;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
@MainThread
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public interface ResultManager extends Manager {
    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    static ResultManager create() {
        ResultManager resultManager = (ResultManager) Manager.create(ResultManager.class, "androidx.car.app.activity.ResultManagerAutomotive", new Object[0]);
        if (resultManager != null) {
            return resultManager;
        }
        throw new IllegalStateException("Unable to instantiate " + ResultManager.class + ". Did you forget to add a dependency on app-automotive artifact?");
    }

    @Nullable
    ComponentName getCallingComponent();

    void setCarAppResult(int i2, @Nullable Intent intent);
}
