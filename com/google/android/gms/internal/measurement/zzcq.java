package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzcq extends zzdt {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ String f5951e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ zzee f5952f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzcq(zzee zzeeVar, String str) {
        super(zzeeVar, true);
        this.f5952f = zzeeVar;
        this.f5951e = str;
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    final void zza() {
        zzcc zzccVar;
        zzccVar = this.f5952f.zzj;
        ((zzcc) Preconditions.checkNotNull(zzccVar)).setUserId(this.f5951e, this.f6026a);
    }
}
