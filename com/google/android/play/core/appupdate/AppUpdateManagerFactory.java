package com.google.android.play.core.appupdate;

import android.content.Context;
import androidx.annotation.NonNull;
/* loaded from: classes2.dex */
public final class AppUpdateManagerFactory {
    private AppUpdateManagerFactory() {
    }

    @NonNull
    public static AppUpdateManager create(@NonNull Context context) {
        return zza.a(context).zza();
    }
}
