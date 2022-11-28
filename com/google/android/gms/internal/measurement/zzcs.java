package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzcs extends zzdt {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ Boolean f5957e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ zzee f5958f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzcs(zzee zzeeVar, Boolean bool) {
        super(zzeeVar, true);
        this.f5958f = zzeeVar;
        this.f5957e = bool;
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    final void zza() {
        zzcc zzccVar;
        zzcc zzccVar2;
        if (this.f5957e != null) {
            zzccVar2 = this.f5958f.zzj;
            ((zzcc) Preconditions.checkNotNull(zzccVar2)).setMeasurementEnabled(this.f5957e.booleanValue(), this.f6026a);
            return;
        }
        zzccVar = this.f5958f.zzj;
        ((zzcc) Preconditions.checkNotNull(zzccVar)).clearMeasurementEnabled(this.f6026a);
    }
}
