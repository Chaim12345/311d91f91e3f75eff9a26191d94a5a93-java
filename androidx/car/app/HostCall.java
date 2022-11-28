package androidx.car.app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public interface HostCall<ServiceT, ReturnT> {
    @Nullable
    ReturnT dispatch(@NonNull ServiceT servicet);
}
