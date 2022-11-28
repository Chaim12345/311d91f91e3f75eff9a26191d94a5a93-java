package androidx.car.app.utils;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.util.Objects;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public final class CommonUtils {
    private CommonUtils() {
    }

    public static boolean isAutomotiveOS(@NonNull Context context) {
        Objects.requireNonNull(context);
        return context.getPackageManager().hasSystemFeature("android.hardware.type.automotive");
    }
}
