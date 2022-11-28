package com.google.android.libraries.places.internal;

import androidx.annotation.Nullable;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.Task;
/* loaded from: classes2.dex */
abstract class zzfy {
    private Task zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzfy(zzfx zzfxVar) {
    }

    public abstract CancellationTokenSource zza();

    @Nullable
    public final Task zzc() {
        return this.zza;
    }

    public final void zzd(Task task) {
        this.zza = task;
    }
}
