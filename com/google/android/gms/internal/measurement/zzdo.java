package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzdo extends zzdt {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ zzdu f6008e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ zzee f6009f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzdo(zzee zzeeVar, zzdu zzduVar) {
        super(zzeeVar, true);
        this.f6009f = zzeeVar;
        this.f6008e = zzduVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    final void zza() {
        zzcc zzccVar;
        zzccVar = this.f6009f.zzj;
        ((zzcc) Preconditions.checkNotNull(zzccVar)).setEventInterceptor(this.f6008e);
    }
}
