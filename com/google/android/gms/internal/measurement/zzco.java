package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzco extends zzdt {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ String f5943e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ String f5944f;

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ Bundle f5945g;

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ zzee f5946h;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzco(zzee zzeeVar, String str, String str2, Bundle bundle) {
        super(zzeeVar, true);
        this.f5946h = zzeeVar;
        this.f5943e = str;
        this.f5944f = str2;
        this.f5945g = bundle;
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    final void zza() {
        zzcc zzccVar;
        zzccVar = this.f5946h.zzj;
        ((zzcc) Preconditions.checkNotNull(zzccVar)).clearConditionalUserProperty(this.f5943e, this.f5944f, this.f5945g);
    }
}
