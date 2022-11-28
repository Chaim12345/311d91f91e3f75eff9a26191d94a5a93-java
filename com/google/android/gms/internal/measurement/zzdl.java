package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzdl extends zzdt {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ zzbz f6001e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ int f6002f;

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ zzee f6003g;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzdl(zzee zzeeVar, zzbz zzbzVar, int i2) {
        super(zzeeVar, true);
        this.f6003g = zzeeVar;
        this.f6001e = zzbzVar;
        this.f6002f = i2;
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    protected final void a() {
        this.f6001e.zzd(null);
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    final void zza() {
        zzcc zzccVar;
        zzccVar = this.f6003g.zzj;
        ((zzcc) Preconditions.checkNotNull(zzccVar)).getTestFlag(this.f6001e, this.f6002f);
    }
}
