package com.google.android.gms.internal.measurement;
/* loaded from: classes.dex */
public final class zzng implements zznf {
    public static final zzhy zza;
    public static final zzhy zzb;
    public static final zzhy zzc;
    public static final zzhy zzd;
    public static final zzhy zze;
    public static final zzhy zzf;

    static {
        zzhv zza2 = new zzhv(zzho.zza("com.google.android.gms.measurement")).zzb().zza();
        zza = zza2.zzf("measurement.adid_zero.app_instance_id_fix", true);
        zzb = zza2.zzf("measurement.adid_zero.service", true);
        zzc = zza2.zzf("measurement.adid_zero.adid_uid", true);
        zzd = zza2.zzf("measurement.adid_zero.remove_lair_if_adidzero_false", true);
        zze = zza2.zzf("measurement.adid_zero.remove_lair_if_userid_cleared", true);
        zzf = zza2.zzf("measurement.adid_zero.remove_lair_on_id_value_change_only", true);
    }

    @Override // com.google.android.gms.internal.measurement.zznf
    public final boolean zza() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zznf
    public final boolean zzb() {
        return ((Boolean) zza.zzb()).booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznf
    public final boolean zzc() {
        return ((Boolean) zzb.zzb()).booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznf
    public final boolean zzd() {
        return ((Boolean) zzc.zzb()).booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznf
    public final boolean zze() {
        return ((Boolean) zzd.zzb()).booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznf
    public final boolean zzf() {
        return ((Boolean) zze.zzb()).booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznf
    public final boolean zzg() {
        return ((Boolean) zzf.zzb()).booleanValue();
    }
}
