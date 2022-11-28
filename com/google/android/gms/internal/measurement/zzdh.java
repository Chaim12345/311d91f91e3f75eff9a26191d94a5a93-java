package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzdh extends zzdt {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ Bundle f5993e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ zzbz f5994f;

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ zzee f5995g;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzdh(zzee zzeeVar, Bundle bundle, zzbz zzbzVar) {
        super(zzeeVar, true);
        this.f5995g = zzeeVar;
        this.f5993e = bundle;
        this.f5994f = zzbzVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    protected final void a() {
        this.f5994f.zzd(null);
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    final void zza() {
        zzcc zzccVar;
        zzccVar = this.f5995g.zzj;
        ((zzcc) Preconditions.checkNotNull(zzccVar)).performAction(this.f5993e, this.f5994f, this.f6026a);
    }
}
