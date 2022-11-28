package androidx.core.database;

import android.database.CursorWindow;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes.dex */
public final class CursorWindowCompat {
    private CursorWindowCompat() {
    }

    @NonNull
    public static CursorWindow create(@Nullable String str, long j2) {
        int i2 = Build.VERSION.SDK_INT;
        return i2 >= 28 ? new CursorWindow(str, j2) : i2 >= 15 ? new CursorWindow(str) : new CursorWindow(false);
    }
}
