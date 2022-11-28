package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzcp extends zzdt {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ String f5947e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ String f5948f;

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ zzbz f5949g;

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ zzee f5950h;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzcp(zzee zzeeVar, String str, String str2, zzbz zzbzVar) {
        super(zzeeVar, true);
        this.f5950h = zzeeVar;
        this.f5947e = str;
        this.f5948f = str2;
        this.f5949g = zzbzVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    protected final void a() {
        this.f5949g.zzd(null);
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    final void zza() {
        zzcc zzccVar;
        zzccVar = this.f5950h.zzj;
        ((zzcc) Preconditions.checkNotNull(zzccVar)).getConditionalUserProperties(this.f5947e, this.f5948f, this.f5949g);
    }
}
