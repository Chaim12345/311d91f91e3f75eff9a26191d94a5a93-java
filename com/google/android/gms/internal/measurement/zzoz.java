package com.google.android.gms.internal.measurement;
/* loaded from: classes.dex */
public final class zzoz implements zzoy {
    public static final zzhy zza;
    public static final zzhy zzb;

    static {
        zzhv zza2 = new zzhv(zzho.zza("com.google.android.gms.measurement")).zza();
        zza = zza2.zzf("measurement.validation.internal_limits_internal_event_params", true);
        zzb = zza2.zzd("measurement.id.validation.internal_limits_internal_event_params", 0L);
    }

    @Override // com.google.android.gms.internal.measurement.zzoy
    public final boolean zza() {
        return ((Boolean) zza.zzb()).booleanValue();
    }
}
