package androidx.car.app;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Objects;
/* loaded from: classes.dex */
public final class HandshakeInfo {
    @Keep
    private final int mHostCarAppApiLevel;
    @Nullable
    @Keep
    private final String mHostPackageName;

    private HandshakeInfo() {
        this.mHostPackageName = null;
        this.mHostCarAppApiLevel = 0;
    }

    public HandshakeInfo(@NonNull String str, int i2) {
        this.mHostPackageName = str;
        this.mHostCarAppApiLevel = i2;
    }

    public int getHostCarAppApiLevel() {
        return this.mHostCarAppApiLevel;
    }

    @NonNull
    public String getHostPackageName() {
        String str = this.mHostPackageName;
        Objects.requireNonNull(str);
        return str;
    }
}
