package com.google.android.play.core.internal;

import androidx.annotation.Nullable;
/* loaded from: classes2.dex */
public abstract class zzah implements Runnable {
    @Nullable
    private final com.google.android.play.core.tasks.zzi zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzah() {
        this.zza = null;
    }

    public zzah(@Nullable com.google.android.play.core.tasks.zzi zziVar) {
        this.zza = zziVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public final com.google.android.play.core.tasks.zzi a() {
        return this.zza;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            zza();
        } catch (Exception e2) {
            zzc(e2);
        }
    }

    protected abstract void zza();

    public final void zzc(Exception exc) {
        com.google.android.play.core.tasks.zzi zziVar = this.zza;
        if (zziVar != null) {
            zziVar.zzd(exc);
        }
    }
}
