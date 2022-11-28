package androidx.car.app;

import androidx.annotation.NonNull;
import java.util.Objects;
/* loaded from: classes.dex */
public final class HostInfo {
    @NonNull
    private final String mPackageName;
    private final int mUid;

    public HostInfo(@NonNull String str, int i2) {
        Objects.requireNonNull(str);
        this.mPackageName = str;
        this.mUid = i2;
    }

    @NonNull
    public String getPackageName() {
        return this.mPackageName;
    }

    public int getUid() {
        return this.mUid;
    }

    @NonNull
    public String toString() {
        return this.mPackageName + ", uid: " + this.mUid;
    }
}
