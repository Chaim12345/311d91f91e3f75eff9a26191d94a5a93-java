package com.google.android.gms.internal.measurement;
/* loaded from: classes.dex */
public final class zzot implements zzos {
    public static final zzhy zza;
    public static final zzhy zzb;
    public static final zzhy zzc;
    public static final zzhy zzd;

    static {
        zzhv zza2 = new zzhv(zzho.zza("com.google.android.gms.measurement")).zza();
        zza = zza2.zzf("measurement.sdk.collection.enable_extend_user_property_size", true);
        zzb = zza2.zzf("measurement.sdk.collection.last_deep_link_referrer2", true);
        zzc = zza2.zzf("measurement.sdk.collection.last_deep_link_referrer_campaign2", false);
        zzd = zza2.zzd("measurement.id.sdk.collection.last_deep_link_referrer2", 0L);
    }

    @Override // com.google.android.gms.internal.measurement.zzos
    public final boolean zza() {
        return ((Boolean) zzc.zzb()).booleanValue();
    }
}
