package androidx.car.app;

import androidx.annotation.NonNull;
/* loaded from: classes.dex */
public final class HostException extends RuntimeException {
    public HostException(@NonNull String str) {
        super(str);
    }

    public HostException(@NonNull String str, @NonNull Throwable th) {
        super(str, th);
    }

    public HostException(@NonNull Throwable th) {
        super(th);
    }
}
