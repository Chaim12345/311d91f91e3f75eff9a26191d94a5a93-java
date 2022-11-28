package com.google.android.gms.measurement;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.measurement.internal.zzhk;
import com.google.android.gms.measurement.internal.zzhl;
import com.google.android.gms.measurement.internal.zziq;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
final class zzb extends zzd {
    private final zziq zza;

    public zzb(zziq zziqVar) {
        super(null);
        Preconditions.checkNotNull(zziqVar);
        this.zza = zziqVar;
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    public final int zza(String str) {
        return this.zza.zza(str);
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    public final long zzb() {
        return this.zza.zzb();
    }

    @Override // com.google.android.gms.measurement.zzd
    public final Boolean zzc() {
        return (Boolean) this.zza.zzg(4);
    }

    @Override // com.google.android.gms.measurement.zzd
    public final Double zzd() {
        return (Double) this.zza.zzg(2);
    }

    @Override // com.google.android.gms.measurement.zzd
    public final Integer zze() {
        return (Integer) this.zza.zzg(3);
    }

    @Override // com.google.android.gms.measurement.zzd
    public final Long zzf() {
        return (Long) this.zza.zzg(1);
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    public final Object zzg(int i2) {
        return this.zza.zzg(i2);
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    public final String zzh() {
        return this.zza.zzh();
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    public final String zzi() {
        return this.zza.zzi();
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    public final String zzj() {
        return this.zza.zzj();
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    public final String zzk() {
        return this.zza.zzk();
    }

    @Override // com.google.android.gms.measurement.zzd
    public final String zzl() {
        return (String) this.zza.zzg(0);
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    public final List zzm(String str, String str2) {
        return this.zza.zzm(str, str2);
    }

    @Override // com.google.android.gms.measurement.zzd
    public final Map zzn(boolean z) {
        return this.zza.zzo(null, null, z);
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    public final Map zzo(String str, String str2, boolean z) {
        return this.zza.zzo(str, str2, z);
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    public final void zzp(String str) {
        this.zza.zzp(str);
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    public final void zzq(String str, String str2, Bundle bundle) {
        this.zza.zzq(str, str2, bundle);
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    public final void zzr(String str) {
        this.zza.zzr(str);
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    public final void zzs(String str, String str2, Bundle bundle) {
        this.zza.zzs(str, str2, bundle);
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    public final void zzt(String str, String str2, Bundle bundle, long j2) {
        this.zza.zzt(str, str2, bundle, j2);
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    public final void zzu(zzhl zzhlVar) {
        this.zza.zzu(zzhlVar);
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    public final void zzv(Bundle bundle) {
        this.zza.zzv(bundle);
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    public final void zzw(zzhk zzhkVar) {
        this.zza.zzw(zzhkVar);
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    public final void zzx(zzhl zzhlVar) {
        this.zza.zzx(zzhlVar);
    }
}
