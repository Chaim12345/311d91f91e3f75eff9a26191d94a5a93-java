package com.google.firebase.analytics;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.gms.internal.measurement.zzee;
import com.google.android.gms.measurement.internal.zzhk;
import com.google.android.gms.measurement.internal.zzhl;
import com.google.android.gms.measurement.internal.zziq;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
final class zzc implements zziq {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzee f9883a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzc(zzee zzeeVar) {
        this.f9883a = zzeeVar;
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    public final int zza(String str) {
        return this.f9883a.zza(str);
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    public final long zzb() {
        return this.f9883a.zzb();
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    @Nullable
    public final Object zzg(int i2) {
        return this.f9883a.zzh(i2);
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    @Nullable
    public final String zzh() {
        return this.f9883a.zzl();
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    @Nullable
    public final String zzi() {
        return this.f9883a.zzm();
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    @Nullable
    public final String zzj() {
        return this.f9883a.zzn();
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    @Nullable
    public final String zzk() {
        return this.f9883a.zzo();
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    public final List zzm(@Nullable String str, @Nullable String str2) {
        return this.f9883a.zzp(str, str2);
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    public final Map zzo(@Nullable String str, @Nullable String str2, boolean z) {
        return this.f9883a.zzq(str, str2, z);
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    public final void zzp(String str) {
        this.f9883a.zzu(str);
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    public final void zzq(String str, @Nullable String str2, @Nullable Bundle bundle) {
        this.f9883a.zzv(str, str2, bundle);
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    public final void zzr(String str) {
        this.f9883a.zzw(str);
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    public final void zzs(String str, String str2, Bundle bundle) {
        this.f9883a.zzy(str, str2, bundle);
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    public final void zzt(String str, String str2, Bundle bundle, long j2) {
        this.f9883a.zzz(str, str2, bundle, j2);
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    public final void zzu(zzhl zzhlVar) {
        this.f9883a.zzB(zzhlVar);
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    public final void zzv(Bundle bundle) {
        this.f9883a.zzD(bundle);
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    public final void zzw(zzhk zzhkVar) {
        this.f9883a.zzJ(zzhkVar);
    }

    @Override // com.google.android.gms.measurement.internal.zziq
    public final void zzx(zzhl zzhlVar) {
        this.f9883a.zzO(zzhlVar);
    }
}
