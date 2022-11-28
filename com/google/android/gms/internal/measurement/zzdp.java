package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzdp extends zzdt {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ zzdv f6010e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ zzee f6011f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzdp(zzee zzeeVar, zzdv zzdvVar) {
        super(zzeeVar, true);
        this.f6011f = zzeeVar;
        this.f6010e = zzdvVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    final void zza() {
        zzcc zzccVar;
        zzccVar = this.f6011f.zzj;
        ((zzcc) Preconditions.checkNotNull(zzccVar)).registerOnMeasurementEventListener(this.f6010e);
    }
}
