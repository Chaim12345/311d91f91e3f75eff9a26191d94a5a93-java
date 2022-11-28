package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzcz extends zzdt {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ String f5973e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ zzee f5974f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzcz(zzee zzeeVar, String str) {
        super(zzeeVar, true);
        this.f5974f = zzeeVar;
        this.f5973e = str;
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    final void zza() {
        zzcc zzccVar;
        zzccVar = this.f5974f.zzj;
        ((zzcc) Preconditions.checkNotNull(zzccVar)).endAdUnitExposure(this.f5973e, this.f6027b);
    }
}
