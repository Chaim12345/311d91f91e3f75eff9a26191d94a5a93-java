package com.google.android.gms.internal.measurement;
/* loaded from: classes.dex */
public final class zzny implements zznx {
    public static final zzhy zza;
    public static final zzhy zzb;
    public static final zzhy zzc;
    public static final zzhy zzd;

    static {
        zzhv zza2 = new zzhv(zzho.zza("com.google.android.gms.measurement")).zzb().zza();
        zza = zza2.zzf("measurement.enhanced_campaign.client", true);
        zzb = zza2.zzf("measurement.enhanced_campaign.service", true);
        zzc = zza2.zzf("measurement.enhanced_campaign.srsltid.client", true);
        zzd = zza2.zzf("measurement.enhanced_campaign.srsltid.service", true);
    }

    @Override // com.google.android.gms.internal.measurement.zznx
    public final boolean zza() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zznx
    public final boolean zzb() {
        return ((Boolean) zza.zzb()).booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznx
    public final boolean zzc() {
        return ((Boolean) zzb.zzb()).booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznx
    public final boolean zzd() {
        return ((Boolean) zzc.zzb()).booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznx
    public final boolean zze() {
        return ((Boolean) zzd.zzb()).booleanValue();
    }
}
