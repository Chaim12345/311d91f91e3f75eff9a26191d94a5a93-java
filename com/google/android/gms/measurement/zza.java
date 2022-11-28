package com.google.android.gms.measurement;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.measurement.internal.zzgk;
import com.google.android.gms.measurement.internal.zzhk;
import com.google.android.gms.measurement.internal.zzhl;
import com.google.android.gms.measurement.internal.zzip;
import com.google.android.gms.measurement.internal.zzlo;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
final class zza extends zzd {
    private final zzgk zza;
    private final zzip zzb;

    public zza(@NonNull zzgk zzgkVar) {
        super(null);
        Preconditions.checkNotNull(zzgkVar);
        this.zza = zzgkVar;
        this.zzb = zzgkVar.zzq();
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    public final int zza(String str) {
        this.zzb.zzh(str);
        return 25;
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    public final long zzb() {
        return this.zza.zzv().zzq();
    }

    @Override // com.google.android.gms.measurement.zzd
    public final Boolean zzc() {
        return this.zzb.zzi();
    }

    @Override // com.google.android.gms.measurement.zzd
    public final Double zzd() {
        return this.zzb.zzj();
    }

    @Override // com.google.android.gms.measurement.zzd
    public final Integer zze() {
        return this.zzb.zzl();
    }

    @Override // com.google.android.gms.measurement.zzd
    public final Long zzf() {
        return this.zzb.zzm();
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    public final Object zzg(int i2) {
        return i2 != 0 ? i2 != 1 ? i2 != 2 ? i2 != 3 ? this.zzb.zzi() : this.zzb.zzl() : this.zzb.zzj() : this.zzb.zzm() : this.zzb.zzr();
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    public final String zzh() {
        return this.zzb.zzo();
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    public final String zzi() {
        return this.zzb.zzp();
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    public final String zzj() {
        return this.zzb.zzq();
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    public final String zzk() {
        return this.zzb.zzo();
    }

    @Override // com.google.android.gms.measurement.zzd
    public final String zzl() {
        return this.zzb.zzr();
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    public final List zzm(String str, String str2) {
        return this.zzb.zzs(str, str2);
    }

    @Override // com.google.android.gms.measurement.zzd
    public final Map zzn(boolean z) {
        List<zzlo> zzt = this.zzb.zzt(z);
        ArrayMap arrayMap = new ArrayMap(zzt.size());
        for (zzlo zzloVar : zzt) {
            Object zza = zzloVar.zza();
            if (zza != null) {
                arrayMap.put(zzloVar.zzb, zza);
            }
        }
        return arrayMap;
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    public final Map zzo(String str, String str2, boolean z) {
        return this.zzb.zzu(str, str2, z);
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    public final void zzp(String str) {
        this.zza.zzd().zzd(str, this.zza.zzav().elapsedRealtime());
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    public final void zzq(String str, String str2, Bundle bundle) {
        this.zza.zzq().zzA(str, str2, bundle);
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    public final void zzr(String str) {
        this.zza.zzd().zze(str, this.zza.zzav().elapsedRealtime());
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    public final void zzs(String str, String str2, Bundle bundle) {
        this.zzb.zzE(str, str2, bundle);
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    public final void zzt(String str, String str2, Bundle bundle, long j2) {
        this.zzb.zzF(str, str2, bundle, true, false, j2);
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    public final void zzu(zzhl zzhlVar) {
        this.zzb.zzK(zzhlVar);
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    public final void zzv(Bundle bundle) {
        this.zzb.zzQ(bundle);
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    public final void zzw(zzhk zzhkVar) {
        this.zzb.zzV(zzhkVar);
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    public final void zzx(zzhl zzhlVar) {
        this.zzb.zzab(zzhlVar);
    }
}
