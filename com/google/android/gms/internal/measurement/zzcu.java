package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzcu extends zzdt {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ Bundle f5961e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ zzee f5962f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzcu(zzee zzeeVar, Bundle bundle) {
        super(zzeeVar, true);
        this.f5962f = zzeeVar;
        this.f5961e = bundle;
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    final void zza() {
        zzcc zzccVar;
        zzccVar = this.f5962f.zzj;
        ((zzcc) Preconditions.checkNotNull(zzccVar)).setConsentThirdParty(this.f5961e, this.f6026a);
    }
}
