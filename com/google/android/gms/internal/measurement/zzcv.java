package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzcv extends zzdt {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ zzee f5963e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzcv(zzee zzeeVar) {
        super(zzeeVar, true);
        this.f5963e = zzeeVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    final void zza() {
        zzcc zzccVar;
        zzccVar = this.f5963e.zzj;
        ((zzcc) Preconditions.checkNotNull(zzccVar)).resetAnalyticsData(this.f6026a);
    }
}
