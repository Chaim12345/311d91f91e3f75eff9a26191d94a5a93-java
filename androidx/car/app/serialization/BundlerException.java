package androidx.car.app.serialization;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes.dex */
public class BundlerException extends Exception {
    public BundlerException(@Nullable String str) {
        super(str);
    }

    public BundlerException(@Nullable String str, @NonNull Throwable th) {
        super(str, th);
    }
}
