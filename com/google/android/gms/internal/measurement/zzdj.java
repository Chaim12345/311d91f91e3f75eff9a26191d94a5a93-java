package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzdj extends zzdt {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ String f5996e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ zzbz f5997f;

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ zzee f5998g;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzdj(zzee zzeeVar, String str, zzbz zzbzVar) {
        super(zzeeVar, true);
        this.f5998g = zzeeVar;
        this.f5996e = str;
        this.f5997f = zzbzVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    protected final void a() {
        this.f5997f.zzd(null);
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    final void zza() {
        zzcc zzccVar;
        zzccVar = this.f5998g.zzj;
        ((zzcc) Preconditions.checkNotNull(zzccVar)).getMaxUserProperties(this.f5996e, this.f5997f);
    }
}
