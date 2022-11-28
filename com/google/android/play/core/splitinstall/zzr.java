package com.google.android.play.core.splitinstall;

import androidx.annotation.Nullable;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes2.dex */
public final class zzr {
    private static final AtomicReference zza = new AtomicReference(null);

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static zzq a() {
        return (zzq) zza.get();
    }

    public static void zzb(zzq zzqVar) {
        zza.compareAndSet(null, zzqVar);
    }
}
