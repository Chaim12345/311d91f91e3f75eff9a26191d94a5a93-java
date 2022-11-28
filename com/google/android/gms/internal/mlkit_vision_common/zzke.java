package com.google.android.gms.internal.mlkit_vision_common;

import android.os.SystemClock;
import androidx.annotation.WorkerThread;
/* loaded from: classes2.dex */
public final class zzke {
    @WorkerThread
    public static void zza(zzjt zzjtVar, int i2, int i3, long j2, int i4, int i5, int i6, int i7) {
        zzjtVar.zzb(zzc(i2, i3, j2, i4, i5, i6, i7), zzgz.INPUT_IMAGE_CONSTRUCTION);
    }

    @WorkerThread
    public static void zzb(zzjt zzjtVar, int i2, int i3, long j2, int i4, int i5, int i6, int i7) {
        zzjtVar.zzb(zzc(i2, i3, j2, i4, i5, i6, i7), zzgz.ODML_IMAGE);
    }

    private static zzkd zzc(int i2, int i3, long j2, int i4, int i5, int i6, int i7) {
        return new zzkd(i2, i3, i6, i4, i5, SystemClock.elapsedRealtime() - j2, i7);
    }
}
