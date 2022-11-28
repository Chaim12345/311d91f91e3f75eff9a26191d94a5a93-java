package com.google.android.play.core.assetpacks;

import android.content.Context;
/* loaded from: classes2.dex */
public final class zzd {
    private static zza zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized zza a(Context context) {
        zza zzaVar;
        synchronized (zzd.class) {
            if (zza == null) {
                zzcb zzcbVar = new zzcb(null);
                zzcbVar.zzb(new zzp(com.google.android.play.core.internal.zzce.zza(context)));
                zza = zzcbVar.zza();
            }
            zzaVar = zza;
        }
        return zzaVar;
    }
}
