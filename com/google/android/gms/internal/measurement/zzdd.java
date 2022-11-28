package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzdd extends zzdt {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ zzbz f5981e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ zzee f5982f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzdd(zzee zzeeVar, zzbz zzbzVar) {
        super(zzeeVar, true);
        this.f5982f = zzeeVar;
        this.f5981e = zzbzVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    protected final void a() {
        this.f5981e.zzd(null);
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    final void zza() {
        zzcc zzccVar;
        zzccVar = this.f5982f.zzj;
        ((zzcc) Preconditions.checkNotNull(zzccVar)).getCurrentScreenName(this.f5981e);
    }
}
