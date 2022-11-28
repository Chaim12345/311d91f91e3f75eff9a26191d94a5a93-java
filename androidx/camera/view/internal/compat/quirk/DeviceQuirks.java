package androidx.camera.view.internal.compat.quirk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.impl.Quirk;
import androidx.camera.core.impl.Quirks;
/* loaded from: classes.dex */
public class DeviceQuirks {
    @NonNull
    private static final Quirks QUIRKS = new Quirks(DeviceQuirksLoader.a());

    private DeviceQuirks() {
    }

    @Nullable
    public static <T extends Quirk> T get(@NonNull Class<T> cls) {
        return (T) QUIRKS.get(cls);
    }
}
