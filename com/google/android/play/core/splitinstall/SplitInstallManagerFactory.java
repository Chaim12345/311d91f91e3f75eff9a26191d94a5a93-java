package com.google.android.play.core.splitinstall;

import android.content.Context;
import androidx.annotation.NonNull;
/* loaded from: classes2.dex */
public final class SplitInstallManagerFactory {
    private SplitInstallManagerFactory() {
    }

    @NonNull
    public static SplitInstallManager create(@NonNull Context context) {
        return zzu.zza(context).zza();
    }
}
