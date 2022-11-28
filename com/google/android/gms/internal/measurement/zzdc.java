package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzdc extends zzdt {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ zzbz f5979e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ zzee f5980f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzdc(zzee zzeeVar, zzbz zzbzVar) {
        super(zzeeVar, true);
        this.f5980f = zzeeVar;
        this.f5979e = zzbzVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    protected final void a() {
        this.f5979e.zzd(null);
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    final void zza() {
        zzcc zzccVar;
        zzccVar = this.f5980f.zzj;
        ((zzcc) Preconditions.checkNotNull(zzccVar)).generateEventId(this.f5979e);
    }
}
