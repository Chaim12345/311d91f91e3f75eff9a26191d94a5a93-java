package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzcw extends zzdt {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ long f5964e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ zzee f5965f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzcw(zzee zzeeVar, long j2) {
        super(zzeeVar, true);
        this.f5965f = zzeeVar;
        this.f5964e = j2;
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    final void zza() {
        zzcc zzccVar;
        zzccVar = this.f5965f.zzj;
        ((zzcc) Preconditions.checkNotNull(zzccVar)).setSessionTimeoutDuration(this.f5964e);
    }
}
