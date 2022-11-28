package com.google.android.gms.internal.measurement;
/* loaded from: classes.dex */
public final class zzoq implements zzop {
    public static final zzhy zza;
    public static final zzhy zzb;
    public static final zzhy zzc;

    static {
        zzhv zza2 = new zzhv(zzho.zza("com.google.android.gms.measurement")).zza();
        zza = zza2.zzf("measurement.client.sessions.check_on_reset_and_enable2", true);
        zzb = zza2.zzf("measurement.client.sessions.check_on_startup", true);
        zzc = zza2.zzf("measurement.client.sessions.start_session_before_view_screen", true);
    }

    @Override // com.google.android.gms.internal.measurement.zzop
    public final boolean zza() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzop
    public final boolean zzb() {
        return ((Boolean) zza.zzb()).booleanValue();
    }
}
