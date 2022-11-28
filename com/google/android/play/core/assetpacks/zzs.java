package com.google.android.play.core.assetpacks;

import android.content.ComponentName;
import android.content.Context;
/* loaded from: classes2.dex */
public final class zzs implements com.google.android.play.core.internal.zzcs {
    private final com.google.android.play.core.internal.zzcs zza;
    private final com.google.android.play.core.internal.zzcs zzb;

    public zzs(com.google.android.play.core.internal.zzcs zzcsVar, com.google.android.play.core.internal.zzcs zzcsVar2) {
        this.zza = zzcsVar;
        this.zzb = zzcsVar2;
    }

    @Override // com.google.android.play.core.internal.zzcs
    public final /* bridge */ /* synthetic */ Object zza() {
        Object zza = this.zza.zza();
        Context zzb = ((zzu) this.zzb).zzb();
        zzl zzlVar = (zzl) zza;
        com.google.android.play.core.internal.zzcd.zza(zzb.getPackageManager(), new ComponentName(zzb.getPackageName(), "com.google.android.play.core.assetpacks.AssetPackExtractionService"), 4);
        com.google.android.play.core.internal.zzcd.zza(zzb.getPackageManager(), new ComponentName(zzb.getPackageName(), "com.google.android.play.core.assetpacks.ExtractionForegroundService"), 4);
        com.google.android.play.core.internal.zzcr.zza(zzlVar);
        return zzlVar;
    }
}
