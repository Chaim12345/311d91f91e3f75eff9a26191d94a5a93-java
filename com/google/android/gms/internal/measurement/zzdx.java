package com.google.android.gms.internal.measurement;

import android.app.Activity;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
/* loaded from: classes.dex */
final class zzdx extends zzdt {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ Activity f6033e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ zzed f6034f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzdx(zzed zzedVar, Activity activity) {
        super(zzedVar.f6046a, true);
        this.f6034f = zzedVar;
        this.f6033e = activity;
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    final void zza() {
        zzcc zzccVar;
        zzccVar = this.f6034f.f6046a.zzj;
        ((zzcc) Preconditions.checkNotNull(zzccVar)).onActivityStarted(ObjectWrapper.wrap(this.f6033e), this.f6027b);
    }
}
