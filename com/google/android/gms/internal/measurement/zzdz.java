package com.google.android.gms.internal.measurement;

import android.app.Activity;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
/* loaded from: classes.dex */
final class zzdz extends zzdt {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ Activity f6037e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ zzed f6038f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzdz(zzed zzedVar, Activity activity) {
        super(zzedVar.f6046a, true);
        this.f6038f = zzedVar;
        this.f6037e = activity;
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    final void zza() {
        zzcc zzccVar;
        zzccVar = this.f6038f.f6046a.zzj;
        ((zzcc) Preconditions.checkNotNull(zzccVar)).onActivityPaused(ObjectWrapper.wrap(this.f6037e), this.f6027b);
    }
}
