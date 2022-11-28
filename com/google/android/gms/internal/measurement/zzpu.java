package com.google.android.gms.internal.measurement;
/* loaded from: classes.dex */
public final class zzpu implements zzpt {
    public static final zzhy zza;
    public static final zzhy zzb;

    static {
        zzhv zza2 = new zzhv(zzho.zza("com.google.android.gms.measurement")).zzb().zza();
        zza = zza2.zzf("measurement.collection.enable_session_stitching_token.client.dev", false);
        zzb = zza2.zzf("measurement.collection.enable_session_stitching_token.service", false);
    }

    @Override // com.google.android.gms.internal.measurement.zzpt
    public final boolean zza() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzpt
    public final boolean zzb() {
        return ((Boolean) zza.zzb()).booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpt
    public final boolean zzc() {
        return ((Boolean) zzb.zzb()).booleanValue();
    }
}
