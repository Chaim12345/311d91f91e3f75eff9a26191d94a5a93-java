package com.google.android.gms.internal.measurement;

import android.app.Activity;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
/* loaded from: classes.dex */
final class zzeb extends zzdt {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ Activity f6041e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ zzbz f6042f;

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ zzed f6043g;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzeb(zzed zzedVar, Activity activity, zzbz zzbzVar) {
        super(zzedVar.f6046a, true);
        this.f6043g = zzedVar;
        this.f6041e = activity;
        this.f6042f = zzbzVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    final void zza() {
        zzcc zzccVar;
        zzccVar = this.f6043g.f6046a.zzj;
        ((zzcc) Preconditions.checkNotNull(zzccVar)).onActivitySaveInstanceState(ObjectWrapper.wrap(this.f6041e), this.f6042f, this.f6027b);
    }
}
