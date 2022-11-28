package com.google.android.play.core.internal;

import android.content.Context;
/* loaded from: classes2.dex */
public final class zzce {
    public static Context zza(Context context) {
        Context applicationContext = context.getApplicationContext();
        return applicationContext != null ? applicationContext : context;
    }
}
