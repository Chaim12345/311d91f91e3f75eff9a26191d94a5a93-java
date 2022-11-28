package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzdk extends zzdt {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ zzbz f5999e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ zzee f6000f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzdk(zzee zzeeVar, zzbz zzbzVar) {
        super(zzeeVar, true);
        this.f6000f = zzeeVar;
        this.f5999e = zzbzVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    protected final void a() {
        this.f5999e.zzd(null);
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    final void zza() {
        zzcc zzccVar;
        zzccVar = this.f6000f.zzj;
        ((zzcc) Preconditions.checkNotNull(zzccVar)).getAppInstanceId(this.f5999e);
    }
}
