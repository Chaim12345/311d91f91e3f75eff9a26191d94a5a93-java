package com.google.android.play.core.assetpacks;
/* loaded from: classes2.dex */
public final class zzt implements com.google.android.play.core.internal.zzcs {
    private final com.google.android.play.core.internal.zzcs zza;
    private final com.google.android.play.core.internal.zzcs zzb;
    private final com.google.android.play.core.internal.zzcs zzc;

    public zzt(com.google.android.play.core.internal.zzcs zzcsVar, com.google.android.play.core.internal.zzcs zzcsVar2, com.google.android.play.core.internal.zzcs zzcsVar3) {
        this.zza = zzcsVar;
        this.zzb = zzcsVar2;
        this.zzc = zzcsVar3;
    }

    @Override // com.google.android.play.core.internal.zzcs
    public final /* bridge */ /* synthetic */ Object zza() {
        zzy zzyVar = (zzy) (zzp.b(((zzu) this.zza).zzb()) == null ? com.google.android.play.core.internal.zzcq.zzb(this.zzb).zza() : com.google.android.play.core.internal.zzcq.zzb(this.zzc).zza());
        com.google.android.play.core.internal.zzcr.zza(zzyVar);
        return zzyVar;
    }
}
