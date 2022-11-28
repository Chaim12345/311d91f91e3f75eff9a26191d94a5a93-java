package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzda extends zzdt {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ zzbz f5975e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ zzee f5976f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzda(zzee zzeeVar, zzbz zzbzVar) {
        super(zzeeVar, true);
        this.f5976f = zzeeVar;
        this.f5975e = zzbzVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    protected final void a() {
        this.f5975e.zzd(null);
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    final void zza() {
        zzcc zzccVar;
        zzccVar = this.f5976f.zzj;
        ((zzcc) Preconditions.checkNotNull(zzccVar)).getGmpAppId(this.f5975e);
    }
}
