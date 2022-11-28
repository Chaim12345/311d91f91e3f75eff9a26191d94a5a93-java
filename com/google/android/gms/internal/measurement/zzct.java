package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzct extends zzdt {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ Bundle f5959e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ zzee f5960f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzct(zzee zzeeVar, Bundle bundle) {
        super(zzeeVar, true);
        this.f5960f = zzeeVar;
        this.f5959e = bundle;
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    final void zza() {
        zzcc zzccVar;
        zzccVar = this.f5960f.zzj;
        ((zzcc) Preconditions.checkNotNull(zzccVar)).setConsent(this.f5959e, this.f6026a);
    }
}
