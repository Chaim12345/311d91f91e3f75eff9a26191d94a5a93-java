package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzdn extends zzdt {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ Bundle f6006e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ zzee f6007f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzdn(zzee zzeeVar, Bundle bundle) {
        super(zzeeVar, true);
        this.f6007f = zzeeVar;
        this.f6006e = bundle;
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    final void zza() {
        zzcc zzccVar;
        zzccVar = this.f6007f.zzj;
        ((zzcc) Preconditions.checkNotNull(zzccVar)).setDefaultEventParameters(this.f6006e);
    }
}
