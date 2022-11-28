package androidx.car.app.managers;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.car.app.managers.Manager;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public interface ManagerFactory<T extends Manager> {
    @NonNull
    T create();
}
