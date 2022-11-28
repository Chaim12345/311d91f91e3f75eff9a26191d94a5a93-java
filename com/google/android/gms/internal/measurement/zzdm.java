package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzdm extends zzdt {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ boolean f6004e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ zzee f6005f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzdm(zzee zzeeVar, boolean z) {
        super(zzeeVar, true);
        this.f6005f = zzeeVar;
        this.f6004e = z;
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    final void zza() {
        zzcc zzccVar;
        zzccVar = this.f6005f.zzj;
        ((zzcc) Preconditions.checkNotNull(zzccVar)).setDataCollectionEnabled(this.f6004e);
    }
}
